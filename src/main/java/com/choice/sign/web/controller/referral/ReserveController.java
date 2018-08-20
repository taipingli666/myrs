package com.choice.sign.web.controller.referral;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.choice.domain.entity.external.PatientInfo;
import com.choice.domain.entity.external.RegisterInfo;
import com.choice.domain.service.dictionary.DepartmentService;
import com.choice.domain.service.external.PatientInfoService;
import com.choice.sign.util.UuIdUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.referral.BusDualReferral;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.common.CommonIcd10Service;

//预约
@Controller
@RequestMapping("/reserve")
public class ReserveController {

	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private CommonHospitalService commonHospitalService;

	@Resource
	private PatientInfoService patientInfoService;
	
	/**
	 * 预约检查
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("/check")
	public ModelAndView check(ModelMap map, HttpServletRequest request) {
		
		//当前登录人
		ChannelUser nowUser = (ChannelUser)request.getSession().getAttribute("loginUser");
		map.put("login_user", nowUser);
		map.put("outListTest", "outListTest");
		//获取除当前登录人所在医院的其他医院列表
		List<CommonHospital> list = commonHospitalService.selectHospitalList(nowUser.getHosId());
		map.put("hoslist", list);
		return new ModelAndView("/external/basicNews", map);
	}

	
	//获取科室列表
	@RequestMapping("/getDeptList")
	@ResponseBody
	public Map<String, Object>  getDeptList(int hosCode, String accessToken) {
		
		System.out.println(hosCode+"------"+accessToken);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> deptList = new ArrayList<Object>();
		map.put("deptList", deptList);
		map.put("resultCode", 0);
		map.put("errorMsg", "错误原因");
		return map;
	}

	/**
	 * 预约挂号患者信息
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("/registerPatientInfo")
	public ModelAndView registerPatientInfo(ModelMap map, HttpServletRequest request) {
		//当前登录人
		ChannelUser nowUser = (ChannelUser)request.getSession().getAttribute("loginUser");
		map.put("login_user", nowUser);
		//医院列表
		List<CommonHospital> list = commonHospitalService.selectHospitalList(nowUser.getHosId());
		map.put("hoslist", list);
		return new ModelAndView("/external/register/registerpatientinfo", map);
	}

	/**
	 * 预约挂号信息
	 * @param map
	 * @param request
	 * @param patientInfo
	 * @return
	 */
	@RequestMapping("/registerInfo")
	public ModelAndView registerInfo(ModelMap map, HttpServletRequest request,PatientInfo patientInfo,String hosCode,
									 String hosNum,String icd10Name) {

		ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
		if( patientInfo.getPatientIdCard() != null ){
			if( patientInfoService.selectByPatientIdCard(patientInfo.getPatientIdCard()) == null){
				patientInfo.setId(UuIdUtil.getUuid());
				patientInfoService.insertSelective(patientInfo);
			}else{
				patientInfoService.updateByPatientIdCard(patientInfo);
			}
		}else{
			return new ModelAndView("/external/register/registerpatientinfo", map);
		}
		map.put("operatorDeptName", departmentService.getInfo(Long.parseLong(user.getDeptCode())).getDepartmentname());
		map.put("hosName", commonHospitalService.getById(Integer.valueOf(hosCode)).getHosName());
		map.put("registerInfo", patientInfo);
		map.put("operator", user);
		map.put("hosCode", hosCode);
		map.put("hosNum", hosNum);
		map.put("icd10Name", icd10Name);
		return new ModelAndView("external/register/registerInfo", map);
	}

	/**
	 * 预约挂号成功
	 * @param map
	 * @param request
	 * @param registerInfo
	 * @return
	 */
	@RequestMapping("/registerCarryOut")
	public ModelAndView registerCarryOut(ModelMap map, HttpServletRequest request,RegisterInfo registerInfo) {
		map.put("registerInfo", registerInfo);
		return new ModelAndView("/external/register/registercarryout", map);
	}

}
