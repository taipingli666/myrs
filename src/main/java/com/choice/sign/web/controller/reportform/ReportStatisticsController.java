package com.choice.sign.web.controller.reportform;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.alibaba.druid.stat.TableStat.Mode;
import com.choice.domain.entity.common.CommonArea;
import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.common.CommonPopulation;
import com.choice.domain.entity.referral.BusDualReferral;
import com.choice.domain.entity.reportform.ContractReport;
import com.choice.domain.entity.reportform.ReportStatistics;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.service.common.CommonAreaService;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.common.CommonPopulationService;
import com.choice.domain.service.referral.BusDualReferralService;
import com.choice.domain.service.reportform.ReportStatisticsService;
import com.choice.domain.service.user.ChannelUserRoleService;
import com.choice.domain.service.user.UserService;
import com.choice.sign.util.Page;

@Controller
@RequestMapping("/reportStatistics")
public class ReportStatisticsController{
	private static final long HangzhouCityAreaCode = 330100000000l;
	
	@Resource
	private ReportStatisticsService doctorStatisticsService;
	@Resource
	private CommonHospitalService commonHospitalService;
	@Resource
	private UserService userService;
	@Resource
	private CommonAreaService commonAreaService;
	@Resource
	private BusDualReferralService busDualReferralService;
	@Resource 
	private ChannelUserRoleService channelUserRoleService;
	/**
	 * 进入报表统计展示
	 * @param reportStatistics
	 * @param modelmap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/display/{type}",produces="text/html;charset=UTF-8")
	public ModelAndView display(HttpServletRequest request,ReportStatistics reportStatistics,@PathVariable("type")String type,ModelMap modelmap)
			throws Exception { 
		ChannelUser nowUser = (ChannelUser)request.getSession().getAttribute("loginUser");
		nowUser.setUserId(16);
		CommonHospital hospital = commonHospitalService.getById(Integer.valueOf(nowUser.getHosId()));
		int level = 0;
		level = "doct".equals(type)?1:
			"hosp".equals(type)?2:
			"village".equals(type)?3:
			"town".equals(type)?4:
			"county".equals(type)?5:0;
		if(level == 0) throw new RuntimeException("illegal type! from reportStatistics/display"); 
		reportStatistics.setLevel(level);
		switch(level){
			case 1: 
				reportStatistics.setSubordinateId(Long.valueOf(nowUser.getUserId()));
				break;			//医生
			case 2: 
				reportStatistics.setSubordinateId(Long.valueOf(nowUser.getHosId()));
				//获取医疗机构下的所有专家团队
				List<ChannelUser> experts = userService.getExpectListByHosId(Integer.parseInt(nowUser.getHosId()));
				modelmap.put("selects", experts);
				break;			//医疗机构	
			case 3: 
				reportStatistics.setSubordinateId(Long.valueOf(hospital.getAreaCode()));
				//获取医疗机构
				List<ChannelUser> userHosList = userService.getHosName(Integer.parseInt(nowUser.getHosId()));
				modelmap.put("selects", userHosList);
				break;			//街道
			case 4: 
				reportStatistics.setSubordinateId(Long.valueOf(hospital.getParentCode()));
				//获取街道信息
				List<CommonArea> towns = commonAreaService.getCommonAreaByParentCode(hospital.getParentCode());
				modelmap.put("selects", towns);
				break;			//区	
			case 5: 
				reportStatistics.setSubordinateId(HangzhouCityAreaCode);
				//获取区县信息
				List<CommonArea> countys = commonAreaService.getCommonAreaByParentCode(
						String.valueOf(HangzhouCityAreaCode));
				modelmap.put("selects", countys);
				break;			//市	
		}
		modelmap.put("level", reportStatistics.getLevel());
		modelmap.put("subordinateId", reportStatistics.getSubordinateId());
		List<Integer> distinctYears = doctorStatisticsService.getTotalYearsDistinct(reportStatistics);
		modelmap.put("distinctYears", distinctYears);
		return new ModelAndView("report/reportStatistics",modelmap);
	}
	
	/**
	 * ajax查询显示table
	 * @param reportStatistics
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("/queryTableDate")
	public Map<String, Object> queryTableDate(ReportStatistics reportStatistics) throws ParseException{
		Map<String, Object> result = this.getResult();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		reportStatistics.setSigningTime(simpleDateFormat.parse(reportStatistics.getSigningTimeStr()));
		List<ReportStatistics> yearList = doctorStatisticsService.displayReportOfYear(reportStatistics);
		result.put("yearList", yearList);
		result.put("success", true);
		return result;
	}
	
	/**
	 * excel导出
	 * @param reportStatistics
	 * @param response
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody 
	@RequestMapping("/export")
	public Map<String, Object> export(ReportStatistics reportStatistics,HttpServletResponse response,ModelMap model) throws ParseException{
		Map<String, Object> result = this.getResult();
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy");
		reportStatistics.setSigningTime(simpleDateFormat1.parse(reportStatistics.getSigningTimeStr()));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		//创建HSSFWorkbook对象(excel的文档对象)  
	    HSSFWorkbook wb = new HSSFWorkbook();  
		//建立新的sheet对象（excel的表单）  
		HSSFSheet sheet=wb.createSheet("报表列表");  
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个  
		HSSFRow row1=sheet.createRow(0);  
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个  
		HSSFCell cell=row1.createCell(0);  
		      //设置单元格内容  
		cell.setCellValue("报表一览表");  
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,8));  
		//在sheet里创建第二行  
		HSSFRow row2 = sheet.createRow(1);      
	    //创建单元格并设置单元格内容  
	    row2.createCell(0).setCellValue("序列号");
        row2.createCell(1).setCellValue("日期");
        row2.createCell(2).setCellValue("签约数");
        row2.createCell(3).setCellValue("签约率");
        row2.createCell(4).setCellValue("续约数");
        row2.createCell(5).setCellValue("续约率");
        row2.createCell(6).setCellValue("重点人群签约数");
        row2.createCell(7).setCellValue("重点人群签约率");
        List<ReportStatistics> reportStatisticList = doctorStatisticsService.displayReportOfYear(reportStatistics);
        ReportStatistics reportStatistic;
        for(int i=0;i<reportStatisticList.size();i++){
        	 row2 = sheet.createRow(i+2);  
        	 reportStatistic = reportStatisticList.get(i);
        	 row2.createCell(0).setCellValue(i+1);
	    	 row2.createCell(1).setCellValue(simpleDateFormat.format(reportStatistic.getSigningTime()));
	    	 row2.createCell(2).setCellValue(reportStatistic.getSigningNum());
	    	 row2.createCell(3).setCellValue(reportStatistic.getSignatureRate());
	    	 row2.createCell(4).setCellValue(reportStatistic.getRenewNum());
	    	 row2.createCell(5).setCellValue(reportStatistic.getRenewRate());
	    	 row2.createCell(6).setCellValue(reportStatistic.getSigningNumOfKey());
	    	 row2.createCell(7).setCellValue(reportStatistic.getSignatureRateOfKey());
        }
		//输出Excel文件     
	    try {
			OutputStream output=response.getOutputStream();  
			response.reset();  
			response.setHeader("Content-disposition", "attachment; filename=contractsList.xls");  
			response.setContentType("application/msexcel");          
			wb.write(output);  
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
			return result;
		}  
	    result.put("success", true);
		return result;
	}
	
	private Map<String, Object> getResult(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		return map;
	}
	
	//双向转诊转入转出统计报表
	/*@RequestMapping("/referralReport")
	public ModelAndView referralReport(HttpServletRequest request, ModelMap modelMap, String refType, String year) {
		//默认当前年份
		if (year == null) {
			year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		}
		//默认统计转出
		if (refType == null) {
			refType = "1";
		}
		ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
		List<Map> list = busDualReferralService.referralReport(year, refType, user.getHosId());
		//有数据的年份列表
		List<Map> years = busDualReferralService.distinctYear();
		modelMap.put("list", list);
		modelMap.put("refType", refType);
		modelMap.put("years", years);
		modelMap.put("year", year); 
		return new ModelAndView("/report/referralReport", modelMap);
	}*/
	
	@RequestMapping("/contractReport")
	public ModelAndView contractReport(@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="size",defaultValue="10")Integer size, ContractReport contractReport,ModelMap modelmap, HttpServletRequest request) {
		ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
		String hosId = user.getHosId();
		CommonHospital commonHospital = commonHospitalService.getById(Integer.parseInt(hosId));
		String hosAreaCode = commonHospital.getAreaCode();
		//初始化树
		modelmap.put("areaCode", hosAreaCode);
		return new ModelAndView("/report/contractReport", modelmap);
	}
	

	/**
	 * 转诊年度统计
	 * @param page
	 * @param size
	 * @param contractReport
	 * @param modelmap
	 * @param request
	 * @return
	 */
	@RequestMapping("/dualReferralYearReport")
	public ModelAndView dualReferralYearReport(@RequestParam(value="page",defaultValue="1") Integer page,
									   @RequestParam(value="size",defaultValue="10")Integer size, ContractReport contractReport,ModelMap modelmap, HttpServletRequest request) {
		ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
		String hosId = user.getHosId();
		CommonHospital commonHospital = commonHospitalService.getById(Integer.parseInt(hosId));
		String hosAreaCode = commonHospital.getAreaCode();
		//初始化树
		modelmap.put("parentCode", hosAreaCode);
		return new ModelAndView("/report/dualreferralyearreport", modelmap);
	}
}
