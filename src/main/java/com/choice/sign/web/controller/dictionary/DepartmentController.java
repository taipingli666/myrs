package com.choice.sign.web.controller.dictionary;

import java.util.List;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.dictionary.Department;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.repository.user.ChannelRoleDao;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.dictionary.DepartmentService;
import com.choice.sign.web.controller.base.BaseController;
import com.choice.sign.web.controller.user.AuthPassport;

@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController<T>{
	@Resource
	private DepartmentService departmentService;
	@Resource
	private CommonHospitalService commonhospitalService;
	@Resource
	private ChannelRoleDao channelRoleDao;
	
	//进入科室维护界面
	    @AuthPassport
		@RequestMapping(value="/display",produces="text/html;charset=UTF-8")
		public ModelAndView display(HttpServletRequest request,HttpServletResponse response,ModelMap modelmap)
				throws Exception {
			String pageNum = request.getParameter("pageNumber");
			int pageNumber = pageNum == null?1:Integer.valueOf(pageNum);
			String contents = request.getParameter("contents");
			contents = contents == null?"":contents;	
			String hosidstr =  request.getParameter("hosid");
			Integer hosid;
			if (hosidstr == null || hosidstr == ""){
				hosid = 0;
			}else{
				hosid = Integer.valueOf(hosidstr) ;
			};
			//获取当前登录人
			ChannelUser loginUser = (ChannelUser)request.getSession().getAttribute("loginUser");
			//获取角色id
			Integer roleId = channelRoleDao.getRoleByUserId(loginUser.getUserId()).getRoleId();
			List<CommonHospital> hospitalList = null;
			CommonHospital commonHospital = null;
			//判断角色id类型
			if(roleId == 1 || roleId ==2) {
				//说明是超级管理员或区级管理员，则查询所有科室信息，医院列表查询所有医院列表
				page = departmentService.getPage(pageNumber,hosid,URLDecoder.decode(contents, "UTF-8"));
				hospitalList = commonhospitalService.getAllHospital();
			}else {
				//说明是普通管理员，则查询当前登录人所在医院的科室信息，医院列表则为本医院
				if(hosidstr == null || hosidstr == "") {
					hosid = Integer.valueOf(loginUser.getHosId());
				}
				commonHospital = commonhospitalService.getById(hosid);
				hospitalList = new ArrayList<CommonHospital>();
				hospitalList.add(commonHospital);
				page = departmentService.getPage(pageNumber,hosid,URLDecoder.decode(contents, "UTF-8"));
			}
			modelmap.put("list",hospitalList);
			contents = URLDecoder.decode(contents, "UTF-8");
			modelmap.put("page", page);
			modelmap.put("contents", contents);
			modelmap.put("hosid", hosid);
			return new ModelAndView("department/departmentList",modelmap);
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
				Department info = departmentService.getInfo(id);
				model.put("departmentid",id);
				model.put("info",info);
			}
			String contents = request.getParameter("contents");
			contents = contents == null?"":contents;	
			//获取登录用户信息
			ChannelUser loginUser = (ChannelUser)request.getSession().getAttribute("loginUser");
			//获取角色id
			Integer roleId = channelRoleDao.getRoleByUserId(loginUser.getUserId()).getRoleId();
			List<CommonHospital> hospitalList = null;
			//判断角色类型
			if(roleId == 1 || roleId ==2) {
				//说明是超级管理员或区级管理员，则获取所有医院列表
				hospitalList = commonhospitalService.getAllHospital();
			}else {
				//说明是普通管理员，则获得本医院信息
				Integer hosId = Integer.valueOf(loginUser.getHosId());
				hospitalList = new ArrayList<CommonHospital>();
				CommonHospital commonHospital = commonhospitalService.getById(hosId);
				hospitalList.add(commonHospital);
			}
			model.put("list",hospitalList);
			return new ModelAndView("department/departmentOperate",model);
		}
		
		//增改
		@ResponseBody 
		@RequestMapping(value="/operated")
		public Map<String, Object> operated(HttpServletRequest request, HttpServletResponse response,Department department)
				throws Exception {
			Map<String, Object> map = new HashMap();
			
			String operationType = request.getParameter("operationType");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date nowTime = new Date();
		    ChannelUser nowUser = (ChannelUser)request.getSession().getAttribute("loginUser");
		    //检查是否存在此科室，并返回查到的数据条数
			int count = departmentService.checkDepartment(department);
			if (count != 0){
				map.put("success",false);
				map.put("message","\""+department.getDepartmentname()+"\"已存在");
				return map;
			}
			try {
				if(department.getDepartmentid()!=null){
					//修改科室信息
					department.setEditperson(nowUser.getUserId());
					department.setEdittime(nowTime);
					departmentService.updateDepartment(department);
				}else{
					//添加科室
					department.setIsdelete(0);
					department.setAddperson(nowUser.getUserId());
					department.setAddtime(nowTime);
					departmentService.insertDepartment(department);
				}
			} catch (Exception e) {
				e.printStackTrace();
				map.put("success", false);
				return map;
			}
			map.put("success", true);
			return map;
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
				departmentService.deleteDepartment(idsar);
			} catch (Exception e) {
				e.printStackTrace();
				//日志记录???
			}
			return "succ";
		}

	/**
	 * 查询科室
	 * @param department
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/getDepartmentList",produces = "application/json; charset=utf-8")
	public List<Department> getDepartmentList( @RequestBody Department department )throws Exception {
		List<Department> aa = departmentService.getDepartmentList(department);
		return aa;
	}

}
