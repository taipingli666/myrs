package com.choice.sign.web.controller.referral;


import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.choice.domain.entity.external.PatientInfo;
import com.choice.domain.service.dictionary.DepartmentService;
import com.choice.domain.service.external.PatientInfoService;
import com.choice.domain.service.user.UserService;
import com.choice.sign.util.UuIdUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.common.CommonIcd10;
import com.choice.domain.entity.referral.BusDualReferral;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.common.CommonIcd10Service;
import com.choice.domain.service.referral.BusDualReferralService;
import com.choice.sign.web.controller.base.BaseController;

@Controller
@RequestMapping("/referral")
public class ReferralController extends BaseController<T>{

	@Resource
	private BusDualReferralService busDualReferralService;
	
	@Resource
	private CommonHospitalService commonHospitalService;
	
	@Resource
	private CommonIcd10Service commonIcd10Service;

	@Resource
	private PatientInfoService patientInfoService;

	@Resource
    private UserService userService;

	@Resource
    private DepartmentService departmentService;
	//转诊单
	@RequestMapping(value = "/outpatient")
	@ResponseBody
	public ModelAndView outList(ModelMap modelMap, HttpServletRequest request) throws Exception {
		
		//当前登录人
		ChannelUser nowUser = (ChannelUser)request.getSession().getAttribute("loginUser");
		modelMap.put("login_user", nowUser);
		modelMap.put("outListTest", "outListTest");
		//医院列表
		List<CommonHospital> list = commonHospitalService.selectHospitalList(nowUser.getHosId());
		modelMap.put("hoslist", list);
		
		//已有病人列表
		BusDualReferral busDualReferral = new BusDualReferral();
        busDualReferral.setDrIdFrom(nowUser.getUserName());
        List<BusDualReferral> busList = busDualReferralService.selectByDrIdFrom(busDualReferral);
        modelMap.put("busList", busList);
        
		return new ModelAndView("referral/outcht", modelMap);
	}
	
	//转出列表
	@RequestMapping(value="/display",produces="text/html;charset=UTF-8")
	public ModelAndView display(@RequestParam(value="pageNumber",defaultValue="1") Integer pageNumber,
			HttpServletRequest request, BusDualReferral busDualReferral,ModelMap modelmap)
			throws Exception {
		//获取当前登录用户的信息
		ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
		//设置转诊医生为当前登录人
//		busDualReferral.setDrIdFrom(username);
		//设置转诊类型为转出(1.转出 2.转入)
		//busDualReferral.setRefType("1");
		busDualReferral.setOrgIdFrom(user.getHosId());
		page = busDualReferralService.getPage(pageNumber,busDualReferral);
		modelmap.put("page", page);
		modelmap.put("hosId", user.getHosId());
		modelmap.put("busDualReferral", busDualReferral);
		return new ModelAndView("referral/outChtList",modelmap);
	}
	
	//转入列表
	@RequestMapping(value="/inchtlist",produces="text/html;charset=UTF-8")
	public ModelAndView inchtlist(@RequestParam(value="pageNumber",defaultValue="1") Integer pageNumber,
			HttpServletRequest request,BusDualReferral busDualReferral,ModelMap modelmap)
			throws Exception {
		HttpSession session = request.getSession();
		//获取当前登录用户的信息
		ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
		String username = user.getUserName();
		//设置接诊医生为当前登录人
		busDualReferral.setDrIdTo(username);
		//设置转诊类型为转出(1.转出 2.转入)
		busDualReferral.setRefType("2");
		page = busDualReferralService.getPage(pageNumber,busDualReferral);
		modelmap.put("page", page);
		return new ModelAndView("referral/inChtList",modelmap);
	}
	
	//提交表单
	@RequestMapping(value = "/addoutcht")
	@ResponseBody
    public int addoutcht(BusDualReferral busDualReferral, HttpServletRequest request) {

		//获取当前登录用户的信息
		ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
		PatientInfo patientInfo = new PatientInfo();
		//根据身份证查看当前患者是否在病人表 有就更新没有就添加
		busDualReferral.setCardId(busDualReferral.getCardId().replace(" ",""));
		if(patientInfoService.selectByPatientIdCard(busDualReferral.getCardId()) != null) {
			patientInfo.setPatientIdCard(busDualReferral.getCardId());
			patientInfo.setPatientAge(busDualReferral.getAge());
			patientInfo.setPatientGender(busDualReferral.getSex());
			patientInfo.setPatientMediumCode(busDualReferral.getHealthId());
			patientInfo.setPatientName(busDualReferral.getPatName());
			patientInfo.setPatientPhone(busDualReferral.getTel());
			patientInfoService.updateByPatientIdCard(patientInfo);
		}else {
			patientInfo.setId(UuIdUtil.getUuid());
			patientInfo.setPatientAge(busDualReferral.getAge());
			patientInfo.setPatientIdCard(busDualReferral.getCardId());
			patientInfo.setPatientGender(busDualReferral.getSex());
			patientInfo.setPatientMediumCode(busDualReferral.getHealthId());
			patientInfo.setPatientName(busDualReferral.getPatName());
			patientInfo.setPatientPhone(busDualReferral.getTel());
			patientInfoService.insertSelective(patientInfo);
		}
		busDualReferral.setOrgIdFrom(user.getHosId());//转诊机构id
		busDualReferral.setDrNameFrom(user.getTrueName());//转诊医生姓名
		busDualReferral.setDeptIdFrom(user.getDeptCode());//转诊医生科室
        //转诊医生所属科室
        busDualReferral.setDeptNameFrom(departmentService.getInfo(Long.parseLong(user.getDeptCode())).getDepartmentname());
		busDualReferral.setDrTel(user.getTel());//转诊医生电话
		busDualReferral.setDrIdFrom(user.getUserId().toString());//转诊医生id
		String hospital = commonHospitalService.getById(Integer.parseInt(user.getHosId())).getHosName();
		busDualReferral.setOrgNameFrom(hospital);
		busDualReferral.setCmmitDate(new Timestamp(new Date().getTime()));
		int result = busDualReferralService.insertSelective(busDualReferral);
		return result;
		
	 }
	
	//查询病人详细信息
	@RequestMapping(value="/checkDetail",produces="text/html;charset=UTF-8")
	public ModelAndView chekDetail(Long id,ModelMap modelMap, HttpServletRequest request) {
		ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
		BusDualReferral busDualReferral = busDualReferralService.selectByPrimaryKey(id);
		modelMap.put("hosId", user.getHosId());
		modelMap.put("info",busDualReferral);
		return new ModelAndView("referral/detail");
	}
	
	//icd10列表
	@RequestMapping(value = "/icd10list")
	@ResponseBody
	public Map<String, Object> icd10List(String searchValue, int pageNum, int pageSize) {
		
		List<CommonIcd10> list = commonIcd10Service.selectBySelect(searchValue, pageNum, pageSize);
		int total = commonIcd10Service.countIcd10(searchValue);
		Map<String, Object> map = new HashMap<String,Object>(); 
		map.put("icd10List", list);
		map.put("total", total);
		return map;
	}
	
	//根据身份证号码查询已有转诊病人信息
	@RequestMapping("/infoByCard")
	@ResponseBody
	public Map<String, Object>  referralInfoByCard(HttpServletRequest request, String cardId, String refType) {
		//获取当前登录用户的信息
		ChannelUser user = (ChannelUser)request.getSession().getAttribute("loginUser");
		String hosId = user.getHosId();
		BusDualReferral busDualReferral = busDualReferralService.infoByCardId(cardId, hosId);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("info", busDualReferral);
		return map;
	}

	/**
	 * 修改信息
	 * @param busDualReferral
	 * @return
	 */
	@RequestMapping(value = "/changeDetail",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String changeRegisterInfo(@RequestBody BusDualReferral busDualReferral){
		JSONObject data = new JSONObject();
		try{
			int i = busDualReferralService.updateByPrimaryKeySelective(busDualReferral);
			if( i == 1){
				data.put("resultCode","0");
				data.put("errorMsg","操作成功！");
			}else{
				data.put("resultCode","-1");
				data.put("errorMsg","保存失败！");
			}

		}catch (Exception e){
			e.printStackTrace();
			data.put("resultCode","-1");
			data.put("errorMsg","操作失败！");
		}
		return data.toJSONString();
	}

	//住院列表打印
	@RequestMapping("/print")
	public ModelAndView  print(long id, HttpServletRequest request) {
		BusDualReferral busDualReferral = busDualReferralService.selectByPrimaryKey(id);
        //ChannelUser user = userService.getById(Integer.valueOf(busDualReferral.getDrIdFrom()));

        ModelMap modelMap = new ModelMap();
		modelMap.put("busDualReferral", busDualReferral);
		return  new ModelAndView("referral/outchtPrint", modelMap);
	}

	//下一步弹出层
	@RequestMapping("/savePrint")
	public ModelAndView  savePrint(BusDualReferral busDualReferral, HttpServletRequest request) {
		//BusDualReferral busDualReferral = busDualReferralService.selectByPrimaryKey(id);
		ChannelUser user = (ChannelUser) request.getSession().getAttribute("loginUser");
		busDualReferral.setDrTel(user.getTel());
		busDualReferral.setDrNameFrom(user.getTrueName());
		busDualReferral.setOrgNameFrom(commonHospitalService.getById(Integer.valueOf(user.getHosId())).getHosName());
		busDualReferral.setDeptNameFrom(departmentService.getInfo(Long.parseLong(user.getDeptCode())).getDepartmentname());
		ModelMap modelMap = new ModelMap();
		modelMap.put("busDualReferral", busDualReferral);
		modelMap.put("flag", "flag");
		return  new ModelAndView("referral/outchtPrint", modelMap);
	}
}
