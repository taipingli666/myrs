package com.choice.domain.service.reportform.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.choice.domain.entity.contract.BusinessContract;
import com.choice.domain.entity.reportform.ReportStatistics;
import com.choice.domain.repository.reportform.ReportStatisticsDao;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.contract.ContMangerService;
import com.choice.domain.service.reportform.ReportStatisticsService;

@Service("reportStatisticsService")
public class ReportStatisticsServiceImpl implements ReportStatisticsService {
	
	public static SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
	
	public static SimpleDateFormat yearAndMonthFormat = new SimpleDateFormat("yyyy-MM");
	
	private static ReportStatistics oneReport = new ReportStatistics();
	
	//存储该地区下所有的hosIdList，用于在合同表查找对应所有的合同数据
	private static List<Integer> hosIdListForSelect = new ArrayList<Integer>();
	
	@Resource
	private ReportStatisticsDao reportStatisticsDao;
	@Resource
	private ContMangerService contractService;
	@Resource
	private CommonHospitalService commonHospitalService;
	
	public void insertReportStatistics(ReportStatistics reportStatistics) {
		reportStatisticsDao.insertReportStatistics(reportStatistics);
	}

	public void updateReportStatisticsSelective(ReportStatistics reportStatistics) {
		reportStatisticsDao.updateReportStatisticsSelective(reportStatistics);
	}
	
	/**
	 *  展现该年所有月份的的报表信息及最新的年汇总
	 *  年汇总及时查询：
	 *  1.容易比对出问题，更早发现问题。
	 *  2.区下所有的街道的纪录≠区的所有记录，有的医院可能挂在区下，不属于区下的任何一个街道。
	 */
	public List<ReportStatistics> displayReportOfYear(ReportStatistics reportStatistics) throws ParseException{
		//返回除总计的list
		List<ReportStatistics> yearList = reportStatisticsDao.displayReportOfYear(reportStatistics);
		ReportStatistics reportTotal = getOneReportStatistics(reportStatistics,false);
		yearList.add(reportTotal);
		return yearList;
	}
	
	/**
	 * 获取月或该年的报表信息（月用于insert表，年用于展示）
	 * 
	 * 业务：1.拿到对应级别（医生，区==）里签约的所有合同，2.统计
	 * 1.医生通过合同表里的ExpertId，医院通过合同表里的HosId，区域通过该区域下所有HosId，对应合同表里这些HosId的所有记录
	 */
	public ReportStatistics getOneReportStatistics(ReportStatistics reportStatistics,boolean isMonth) throws ParseException{
		List<Integer> hosIdList = hosIdListForSelect;
		//报表总计
		int signingNumOfLast = 0,renewNum = 0,signingNumOfKey = 0,signingNum = 0;
		//查询contractList的参数
		String yearString = yearFormat.format(reportStatistics.getSigningTime());
		String lastYearString = String.valueOf(Integer.valueOf(yearString)-1);
		BusinessContract bContract = new BusinessContract();
		switch(reportStatistics.getLevel()){
			case 1: 
				bContract.setExpertId(String.valueOf(reportStatistics.getSubordinateId()));
				break;
			case 2:
				bContract.setHosId(String.valueOf(reportStatistics.getSubordinateId()));
				break;
			default:
				//存放所有乡镇，区县，市下辖管理的所有HosIdList。	如果该地区下没有医院则不做任何数据库的操作！！！
				hosIdList = commonHospitalService.getHosIdListForSelect(String.valueOf(reportStatistics.getSubordinateId()));
				if(hosIdList.size() == 0) return null;
		}
		//获取去年签约率，用于计算续约率
		bContract.setAddTime(lastYearString);
		List<BusinessContract> contractList = contractService.selectByAddTime(bContract,hosIdList);
		signingNumOfLast = contractList.size();
		//获取该年签约率
		if(isMonth){
			bContract.setAddTime(yearAndMonthFormat.format(reportStatistics.getSigningTime()));
		}else{
			bContract.setAddTime(yearString);
		}
		contractList = contractService.selectByAddTime(bContract,hosIdList);
		signingNum = contractList.size();
		//遍历查询续约及重点人群签约数
		for(BusinessContract BusinessContract:contractList){
			if("1".equals(BusinessContract.getIsRenew())){
				renewNum++;
			}
			if("1".equals(BusinessContract.getIsKey())){
				signingNumOfKey++;
			}
		}
		//月报表用于插入，必须
		if(isMonth){
			oneReport.setSubordinateId(reportStatistics.getSubordinateId());	
			oneReport.setSigningTime(reportStatistics.getSigningTime());
			oneReport.setLevel(reportStatistics.getLevel());		
		}
		oneReport.setSigningNum(signingNum);
		oneReport.setRenewNum(renewNum);
		oneReport.setSigningNumOfKey(signingNumOfKey);
		//率计算
		//签约率=签约人数/该区域所辖总人口
		oneReport.setSignatureRate(Float.valueOf(signingNum)/20000*100);			//20000为写死数据！
		//续约率=续约人数/上一年度签约
		oneReport.setRenewRate(signingNumOfLast == 0?0.00f:(Float.valueOf(renewNum)/signingNumOfLast*100));
		//重点人群签约率=统计范围内重点人群签约人数/签约人数
		oneReport.setSignatureRateOfKey(signingNum == 0?0.00f:(Float.valueOf(signingNumOfKey)/signingNum*100));
		return oneReport;
	}
	
	/**
	 * 根据登陆用户展现所有有纪录的年份
	 */
	public List<Integer> getTotalYearsDistinct(ReportStatistics reportStatistics) {
		return reportStatisticsDao.getTotalYearsDistinct(reportStatistics);
	}
	
}
