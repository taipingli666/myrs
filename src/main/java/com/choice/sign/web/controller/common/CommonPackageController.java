package com.choice.sign.web.controller.common;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.common.CommonPackage;
import com.choice.domain.entity.dictionary.CommonDictionaryValue;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.service.common.CommonPackageService;
import com.choice.domain.service.dictionary.DictValueService;
import com.choice.sign.web.controller.base.BaseController;
import com.choice.sign.web.controller.user.AuthPassport;

@Controller
@RequestMapping("/commonpackage")
public class CommonPackageController extends BaseController{
	@Resource
	private CommonPackageService commonPackageService;
	@Resource
	private DictValueService dictValueService;
	
	@AuthPassport
	@RequestMapping(value="/display",produces="text/html;charset=UTF-8")
	public ModelAndView display(HttpServletRequest request,HttpServletResponse response,ModelMap modelmap)
			throws Exception {
		String pageNum = request.getParameter("pageNumber");
		int pageNumber = pageNum == null?1:Integer.valueOf(pageNum);
		String className = request.getParameter("className");
		className = className == null?"":className;	
		page = commonPackageService.getPage(pageNumber,URLDecoder.decode(className, "UTF-8"));
		modelmap.put("page", page);
		modelmap.put("className", URLDecoder.decode(className, "UTF-8"));
		List<CommonDictionaryValue> list =  dictValueService.selectValuesByCode(10009);
		modelmap.put("list", list);
		return new ModelAndView("commonpackage/commonpackageList",modelmap);
	}
	
	

	//操作科室维护界面（新增，编辑）
	@RequestMapping(value="/operating",produces="text/html;charset=UTF-8")
	public ModelAndView operating(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws Exception{
		String operationType = request.getParameter("operationType");
		pageNumber = 1;
		if(request.getParameter("pageNumber") != null && !(request.getParameter("pageNumber")).equals("")){
			pageNumber = Integer.valueOf(request.getParameter("pageNumber"));
		}
		model.put("pageNumber", pageNumber);
		model.put("operationType", operationType);
		if("update".equals(operationType)){
			long id = Integer.valueOf(request.getParameter("id"));
			CommonPackage info = commonPackageService.getInfo(id);
			model.put("packageId",id);
			model.put("info",info);
		}
		List<CommonDictionaryValue> list =  dictValueService.selectValuesByCode(10009);
		model.put("list", list);
		return new ModelAndView("commonpackage/commonpackageOperate",model);
	}
	
	//增改
	@ResponseBody 
	@RequestMapping(value="/operated")
	public String operated(HttpServletRequest request, HttpServletResponse response,CommonPackage commonpackage)
			throws Exception {
		String operationType = request.getParameter("operationType");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowTime = new Date();
		ChannelUser nowUser = (ChannelUser)request.getSession().getAttribute("loginUser");
		try {
			if(commonpackage.getPackageId()!=null){
				commonpackage.setEditPerson(nowUser.getUserId());
				commonpackage.setEditTime(nowTime);
				commonPackageService.updateCommonPackage(commonpackage);
			}else{
				commonpackage.setIsDelete(0);
				commonpackage.setAddPerson(nowUser.getUserId());
				commonpackage.setAddTime(nowTime);
				commonPackageService.insertCommonPackage(commonpackage);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "succ";
	}
	/**
	 * 批量删除
	 */
	@ResponseBody 
	@RequestMapping(value="/delete",produces="text/html;charset=UTF-8")
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String ids = request.getParameter("ids");
			String[] idsar = ids.split(",");
			commonPackageService.deleteCommonPackage(idsar);
		} catch (Exception e) {
			e.printStackTrace();
			//日志记录???
		}
		return "succ";
	}
}
