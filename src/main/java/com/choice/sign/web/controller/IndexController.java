package com.choice.sign.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.common.CommonMenu;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.common.CommonMenuService;
import com.choice.domain.service.user.UserService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Resource
	CommonMenuService commonMenuService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private CommonHospitalService hospitalService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		Map map = new HashMap();
		ChannelUser user = (ChannelUser) request.getSession().getAttribute("loginUser");
		List<CommonMenu> result = null;
		if(user==null){
			result = commonMenuService.getDisplayMenus();
			request.getSession().setAttribute("smenu", result);
		}else{
			List<Integer> roleList = userService.getRoleIdList(user.getUserId());
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
			//根据当前登录用户所属医院hosId获取到该用户所属医疗机构
			CommonHospital commonHospital = hospitalService.getById(Integer.valueOf(user.getHosId()));
			request.getSession().setAttribute("hospitalHeader", commonHospital);
		}
		
		return new ModelAndView("main",map);
	}

	/**
     * 转出病人-二维码扫描打开界面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/iphoneupimg")
    public ModelAndView iphoneupimg(Model model) {
        return new ModelAndView("/external/consultationSingle/iphoneupimg");
    }
}
