/**
 * 
 */
/**
 * @author Paul.Pan
 *
 */
package com.choice.sign.web.controller.common;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.choice.domain.entity.external.DualReferralReport;
import com.choice.domain.service.external.DualReferralReportService;
import com.choice.sign.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.choice.domain.entity.common.CommonPopulation;
import com.choice.domain.entity.reportform.ContractReport;
import com.choice.domain.service.common.CommonAreaService;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.common.CommonPopulationService;
import com.choice.domain.service.reportform.ContractReportService;
import com.choice.sign.util.Page;
import com.choice.sign.web.controller.base.BaseController;
import com.choice.sign.web.controller.user.AuthPassport;
/**
 * common_area相关处理类
 * @author stone
 *
 */
@Controller
@RequestMapping("/commonpopulation")
public class CommonPopulationController extends BaseController{
	@Autowired
	CommonPopulationService commonPopulationService;
	@Autowired
	CommonAreaService commonAreaService;
	@Autowired
	CommonHospitalService commonHospitalService;
	@Autowired
	ContractReportService contractReportService;
	@Autowired
	DualReferralReportService dualReferralReportService;
	//进入字典
	@AuthPassport
	@RequestMapping(value="/show",produces="text/html;charset=UTF-8")
	public ModelAndView display(@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="size",defaultValue="10")Integer size,String areaCode,String searchValue,ModelMap modelmap)throws Exception {
	Page<CommonPopulation> info = commonPopulationService.getPage(page,size,areaCode,searchValue);
	//获取行政区域级别代码字典
	modelmap.put("page", info);
	modelmap.put("areaCode", areaCode);
	return new ModelAndView("commonpopulation/commonPopulationList",modelmap);
	}
	/**
	 * 获取iframe界面数据
	 * @param page
	 * @param size
	 * @param modelmap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/show2",produces="text/html;charset=UTF-8")
	public ModelAndView display2(@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="size",defaultValue="10")Integer size,String areaCode,String searchValue,ModelMap modelmap)throws Exception {
	Page<CommonPopulation> info = commonPopulationService.getPage(page,size,areaCode,searchValue);
	modelmap.put("page", info);
	modelmap.put("areaCode", areaCode);
	modelmap.put("searchValue",searchValue);
	return new ModelAndView("commonpopulation/commonPopulationList2",modelmap);
	}
	/**
	 * 签约统计列表
	 * @param page
	 * @param size
	 * @param modelmap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/show3",produces="text/html;charset=UTF-8")
	public ModelAndView contractReportList(@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="size",defaultValue="10")Integer size,ContractReport contractReport,ModelMap modelmap)throws Exception {
		if (contractReport.getYear() == null || contractReport.getYear().equals("")) {
			contractReport.setYear("2019");
		}
		List<ContractReport> list = contractReportService.getListBySearchValue(contractReport);
		List<String> mapList = contractReportService.distinctYear();
		modelmap.put("areaCode", contractReport.getAreaCode());
		modelmap.put("list", list);
		modelmap.put("selectYear", contractReport.getYear());
		modelmap.put("yearList", mapList);
		return new ModelAndView("commonpopulation/commonPopulationList3",modelmap);
	}
	
	/**
	 * 打开新增界面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("operatedui")
	public ModelAndView operatedUI(Integer id,String areaCode,ModelMap model){
		try {
			CommonPopulation commonPopulation = new CommonPopulation();
			if(id != null && id != 0){
				//修改界面
				commonPopulation = commonPopulationService.getInfo(id);
			}else{
				commonPopulation.setAreaCode(areaCode);
			}
			model.addAttribute("info", commonPopulation);
		} catch (Exception e) {
			e.printStackTrace();
			//???日志记录
		}
		return new ModelAndView("commonpopulation/commonPopulationOperate");
	}
	
	/**
	 * 增改
	 */
	@RequestMapping("operated")
	@ResponseBody
	public Map<String, Object> operated(HttpServletRequest request,CommonPopulation commonPopulation){
		Map<String, Object> result = this.getResult();
		try {
			if(commonPopulation.getpId() == null) {
				//如果获取到的pid为null，说明执行的添加操作，就先查询是否有该年份的数据
				int num = commonPopulationService.selectBySearchValue(commonPopulation);
				if(num!=0) {
					//说明有该年份的数据，则提醒用户
					result.put("success",false);
					result.put("errorMsg", "该地区已有"+commonPopulation.getCountYear()+"年的数据记录，请返回上一页修改该记录或选取其他年份！");
					return result;
				}
			}
			commonPopulationService.operated(request,commonPopulation);
			result.put("success", true);
		} catch (Exception e) {
			//???日志输出
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("deletepopulations")
	@ResponseBody
	public Map<String,Object> deletePopulations(String ids){
		Map<String,Object> retMap = this.getResult();
		try {
			commonPopulationService.deleteCommonPopulations(ids.split(","));
			retMap.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}

	/**
	 * 转诊统计列表
	 * @param page
	 * @param size
	 * @param dualReferralReport
	 * @param modelmap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dualreferralyearreportlist",produces="text/html;charset=UTF-8")
	public ModelAndView dualreferralyearreportlist(@RequestParam(value="page",defaultValue="1") Integer page,
								 @RequestParam(value="size",defaultValue="10")Integer size,DualReferralReport dualReferralReport,ModelMap modelmap)throws Exception {
		if(dualReferralReport.getYear() == null || dualReferralReport.getYear().equals("")){
			dualReferralReport.setYear( DateUtil.dateToString(new Date(),"yyyy"));
		}
		List<DualReferralReport> list = dualReferralReportService.selectYearReportData(dualReferralReport);
		modelmap.put("parentCode", dualReferralReport.getParentCode());
		modelmap.put("list", list);
		return new ModelAndView("report/dualreferralyearreportlist",modelmap);
	}
	
	private Map<String, Object> getResult(){
		Map<String, Object> map = new HashMap();
		map.put("success", false);
		return map;
	}
}