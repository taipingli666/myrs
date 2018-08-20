package com.choice.sign.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.choice.domain.entity.common.CommonArea;
import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.reportform.ReportStatistics;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.enums.LevelForReport;
import com.choice.domain.service.common.CommonAreaService;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.contract.ContMangerService;
import com.choice.domain.service.external.DualReferralReportService;
import com.choice.domain.service.external.ScheduleTemplateService;
import com.choice.domain.service.external.impl.RegisterSerivceImpl;
import com.choice.domain.service.reportform.ReportStatisticsService;
import com.choice.domain.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportFormsQuartz{
	
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static ReportStatistics reportStatistic = new ReportStatistics();
	
	//day为"yyyy-MM-dd"里的"dd"
	private static String day = "";
	
	//专家的expert_id，医疗机构的hos_id以及所有乡镇，区县，市的area_code	用于插入report_statistics表里subordinate_id
	private static List<String> subordinateIdList = new ArrayList<String>();
	
	//对应LevelForReport下所有地区（街道到省）的level
	private static final int AREALEVELSTART = 6,AREALEVELEND = 3;
	
	@Resource
	private UserService userService;
	@Resource
	private ContMangerService contractService;
	@Resource
	private ReportStatisticsService doctorStatisticsService;
	@Resource
	private CommonAreaService CommonAreaService;
	@Resource
	private CommonHospitalService commonHospitalService;

	@Resource
	private DualReferralReportService dualReferralReportService;

	@Resource
	private ScheduleTemplateService scheduleTemplateService;

	private Logger logger = LoggerFactory.getLogger(ReportFormsQuartz.class);
	
	public void doctWorkStatistics()throws ParseException{
		System.out.println("Quartz--------------");
		setParam();
		//分开：防止一个地区数据错误导致其他地方也无法统计		
		areaOpeart();
		//获取医疗机构（医院）信息	
		LevelForReport valueOf;
		try {
			valueOf = LevelForReport.valueOf(2);
			subordinateIdList.clear();
			List<CommonHospital> allHospital = commonHospitalService.getAllHospital();
			for(CommonHospital hospital:allHospital){
				subordinateIdList.add(String.valueOf(hospital.getHosId()));
			}
			opeart(subordinateIdList,valueOf.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//获取医生信息
		try {
			valueOf = LevelForReport.valueOf(1);
			subordinateIdList.clear();
			List<ChannelUser> users = userService.getJoinedExpectList();
			for(ChannelUser user:users){
				subordinateIdList.add(String.valueOf(user.getUserId()));
			}
			opeart(subordinateIdList,valueOf.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 地区（街道，区，市）操作
	 * 获取该地区下的所有subordinateIdList（eg. 市下所有区的code）
	 */
	private void areaOpeart(){
		LevelForReport valueOf;
		for(int i=AREALEVELSTART;i>=AREALEVELEND;i--){
			try {
				valueOf = LevelForReport.valueOf(i);
				subordinateIdList.clear();
				List<CommonArea> towns = CommonAreaService.getListByLevel(Integer.valueOf(valueOf.getProperty()));
				for(CommonArea commonArea:towns){
					subordinateIdList.add(commonArea.getCode());
				}
				opeart(subordinateIdList,valueOf.getValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取该等级（医院，区等）下的所有subordinateIdList，遍历处理。
	 * @param list
	 * @param level
	 * @throws ParseException
	 */
	private void opeart(List<String> list,int level) throws ParseException{
		reportStatistic.setLevel(level);
		for(int i=0;i<list.size();i++){
			reportStatistic.setSubordinateId(Long.valueOf(list.get(i)));
			ReportStatistics oneReportStatistics = doctorStatisticsService.getOneReportStatistics(reportStatistic,true);
			if(oneReportStatistics != null){
				//每个月第二天统计第一天的内容，并将其【新增】 当月的纪录到表里，否则执行【修改】
				if("02".equals(day)){
					doctorStatisticsService.insertReportStatistics(oneReportStatistics);
				}else{
					doctorStatisticsService.updateReportStatisticsSelective(oneReportStatistics);
//					doctorStatisticsService.insertReportStatistics(oneReportStatistics);
				}
			}
		}
	}
	
	/**
	 * 设置reportStatistic参数，及day。
	 */
	private void setParam(){
		Date date = new Date();
		reportStatistic.setSigningTime(date);
		String nowaday = simpleDateFormat.format(date);	
		day = nowaday.substring(nowaday.length()-2);
	}

	/**
	 * 生成转诊报表数据
	 * @throws ParseException
	 */
	public void dualReferralReport()throws ParseException{
		System.out.println("Quartz--------------dualReferralReport");

		try {
			Calendar cal= Calendar.getInstance();
			cal.add(Calendar.DATE,-1);
			dualReferralReportService.countReferralByDate(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 排班数据构建每天0点后执行
	 * @throws ParseException
	 */
	public void buildScheduleInfo()throws ParseException{
		System.out.println("Quartz--------------dualReferralReport");
		logger.info("定時排班启动");
		try {
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_MONTH,7);
			scheduleTemplateService.buildScheduleInfo(calendar.getTime(),null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
