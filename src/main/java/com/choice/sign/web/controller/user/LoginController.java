package com.choice.sign.web.controller.user;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.common.CommonMenu;
import com.choice.domain.entity.dictionary.CommonDictionaryValue;
import com.choice.domain.entity.external.BussConsultationSingle;
import com.choice.domain.entity.external.HisPatientInfo;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.common.CommonMenuService;
import com.choice.domain.service.dictionary.DictValueService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.service.user.UserService;


@RestController
@RequestMapping("/")
public class LoginController {
	
	@Resource
	UserService userService;

	@Resource
	CommonHospitalService commonHospitalService;

	@Resource
    CommonMenuService commonMenuService;

	@Resource
	private DictValueService dictValueService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response)
	{
		return new ModelAndView("login");
		
	}
	@ResponseBody//注解，返回json
	@RequestMapping(value = "/checkUser",produces = "application/json;charset=UTF-8")	
	public Map<String, Object> checkUser(HttpServletRequest request,HttpServletResponse response)
	{
		response.setContentType("text/html;charset=utf-8");
		String userName = request.getParameter("userName")==null?"":request.getParameter("userName");
		String password = request.getParameter("password")==null?"":request.getParameter("password");
		password = DigestUtils.md5Hex(password.getBytes());
		Map<String, Object> result = new HashMap();
		ChannelUser user = userService.login(userName, password);
		if(user != null)
		{
			request.getSession().setAttribute("loginUser",user);
			//session过期时间 单位/s
			request.getSession().setMaxInactiveInterval(18000);
			result.put("success", true);
			
		}else{
			result.put("success", false);
		}
		return result;
	}
	
	@RequestMapping(value = "/loginOut", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")	
	public void loginOut(HttpServletRequest request,HttpServletResponse response)
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		request.getSession().removeAttribute("loginUser");
		String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/login.do";
		try {
			resp.sendRedirect(basePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    //his
    @RequestMapping(value = "/hisLogin", produces = "application/json; charset=utf-8")
    public ModelAndView hslogin(HisPatientInfo hisPatientInfo, HttpServletRequest request) throws UnsupportedEncodingException {

        //解码
		try {
			hisPatientInfo.setDoctorCode(URLDecoder.decode(hisPatientInfo.getDoctorCode(),"UTF-8"));
			hisPatientInfo.setMedicalCard(URLDecoder.decode(hisPatientInfo.getMedicalCard(),"UTF-8"));
			hisPatientInfo.setBusinessType(URLDecoder.decode(hisPatientInfo.getBusinessType(),"UTF-8"));
			hisPatientInfo.setPatientAge(URLDecoder.decode(hisPatientInfo.getPatientAge(),"UTF-8"));
			hisPatientInfo.setPatientHisId(URLDecoder.decode(hisPatientInfo.getPatientHisId(),"UTF-8"));
			hisPatientInfo.setPatientName(URLDecoder.decode(hisPatientInfo.getPatientName(),"UTF-8"));
			hisPatientInfo.setPatientIdCard(URLDecoder.decode(hisPatientInfo.getPatientIdCard(),"UTF-8"));
			hisPatientInfo.setPatientSex(URLDecoder.decode(hisPatientInfo.getPatientSex(),"UTF-8"));
		}catch (Exception e) {

		}
		ChannelUser  channelUser = new ChannelUser();
		channelUser.setUserName(hisPatientInfo.getDoctorCode());
        ChannelUser user = userService.selectByUserInfo(channelUser).get(0);
        //左侧菜单
        List<Integer> roleList;

        if (user != null) {
			request.getSession().setAttribute("hospitalHeader", commonHospitalService.getById(Integer.valueOf(user.getHosId())));
            request.getSession().setAttribute("loginUser", user);
            roleList = userService.getRoleIdList(user.getUserId());
        } else {
            roleList = userService.getRoleIdList(5878);
        }

        //左侧菜单
		//List<Integer> roleList = userService.getRoleIdList(user.getUserId());
		String[] strs = null;
		String temp = "";
		List<Map> list = null;
		if(roleList.size()==1){
			list = commonMenuService.getMenuForLogin(roleList.get(0), strs);
		}else{
			for (int i = 0; i < roleList.size(); i++) {
				if(temp==""){
					temp = String.valueOf(roleList.get(i));
				}else{
					temp +="," + String.valueOf(roleList.get(i));
				}
			}
			list = commonMenuService.getMenuForLogin(roleList.get(0), temp.split(","));
		}
		request.getSession().setAttribute("smenu", list);

        ModelMap modelMap = new ModelMap();
        modelMap.put("hisPatientInfo", hisPatientInfo);
		//医院列表
		List<CommonHospital> hospitals = commonHospitalService.selectHospitalList(user.getHosId());
		modelMap.put("hoslist", hospitals);
		//住院5 门诊6   远程7
		if (hisPatientInfo.getBusinessType() != null && hisPatientInfo.getBusinessType().equals("5")) {
			return new ModelAndView("/referral/outcht", modelMap);
		}else if(hisPatientInfo.getBusinessType() != null && hisPatientInfo.getBusinessType().equals("6")){
			return new ModelAndView("/external/register/registerpatientinfo", modelMap);
		}else {
//			ModelMap modelMap = new ModelMap();
			try {
				ChannelUser o = (ChannelUser) request.getSession().getAttribute("loginUser");
				List<CommonHospital> channelHospitals = commonHospitalService.getAllHospitalWithoutNull();
				modelMap.addAttribute("channelHospitals",channelHospitals);
				SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd");
				modelMap.addAttribute("day",s.format(new Date()));
				String erweimaurl = "";
				List<CommonDictionaryValue> listap = dictValueService.selectValuesByCode(10030);
				Iterator<CommonDictionaryValue> iterator=listap.iterator();
				while(iterator.hasNext()) {
					CommonDictionaryValue cdv=iterator.next();
					if(cdv.getCode()==100301) {
						erweimaurl=cdv.getWord();
					}
				}
				modelMap.addAttribute("erweimaurl", erweimaurl);
				modelMap.addAttribute("single",new BussConsultationSingle());
				modelMap.addAttribute("operId",o.getUserId());
			} catch (Exception e) {
				logger.info("获取发起预约失败"+e.getMessage());
				e.printStackTrace();
			}
			return new ModelAndView("/external/consultationSingle/consultationregister",modelMap);
		}
    }

}
