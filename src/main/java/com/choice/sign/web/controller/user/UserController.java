package com.choice.sign.web.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.choice.domain.entity.dictionary.Department;
import com.choice.domain.service.dictionary.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.repository.user.ChannelRoleDao;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.user.ChannelRoleService;
import com.choice.domain.service.user.ChannelUserRoleService;
import com.choice.domain.service.user.UserService;
import com.choice.sign.exception.ParameterException;
import com.choice.sign.util.Page;
import com.choice.sign.web.controller.base.BaseController;

/**
 * user相关处理controller
 * @author duhuo
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	@Autowired
	private UserService userService;
	@Resource
	private ChannelRoleService channelRoleService;
	@Resource
	private ChannelUserRoleService channelUserRoleService;
	@Resource
	private CommonHospitalService commonhospitalService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	ChannelRoleDao channelRoleDao;
	/**
	 * 分页展示
	 * @param page
	 * @param size
	 * @param user
	 * @param modelmap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/display",produces="text/html;charset=UTF-8")
	public ModelAndView display(@RequestParam(value="page",defaultValue="1") Integer page,
								ChannelUser user,ModelMap modelmap,HttpServletRequest request)
			throws Exception {
		Page<ChannelUser> info = null;
		//获取当前登录人
		ChannelUser loginUser = (ChannelUser)request.getSession().getAttribute("loginUser");
		//获取当前登录人id
		Integer userId = loginUser.getUserId();
		//获取当前登录人的角色id
		Integer roleId = channelRoleDao.getRoleByUserId(userId).getRoleId();
		//根据roleId判断当前登录人所属的角色类型
		if(roleId == 1) {
			//说明是市级管理员（即超级管理员）则查询所有用户信息
			info = userService.getPage(page,this.pageSize,user);
		}else if(roleId == 2) {
			//说明是区级管理员，则查询除超级管理员之外的所有用户信息
			info = userService.getPageExceptAdmin(page, this.pageSize, user);
		}else {
			//说明是区级以下管理员，则只能查出本医院的所有用户信息
			//给user设置参数hosId
			String hosId = loginUser.getHosId();
			user.setHosId(hosId);
			info = userService.getPage(page,this.pageSize,user);
		}
		modelmap.put("page", info);
		modelmap.put("user", user);
		return new ModelAndView("user/userList",modelmap);
	}
	/**
	 * 增改
	 */
	@RequestMapping("operated")
	@ResponseBody
	public Map<String, Object> operated(ChannelUser user){
		Map<String, Object> result = this.getResult();
		try {
			//验证用户名是否存在
			int count = userService.checkUser(user);
			if(count != 0) {
				result.put("message", "此用户名已存在，请更改您的用户名！");
				return result;
			}
			userService.operated(user);
			result.put("success", true);
		} catch (Exception e) {
			result.put("message", "操作错误");
			if(e instanceof ParameterException){
				result.put("message", e.getMessage());
			}
			e.printStackTrace();
			//???日志输出
			return result;
		}
		return result;
	}
	/**
	 * 批量删除
	 */
	@RequestMapping("deleteusers")
	@ResponseBody
	public Map<String, Object> deleteUsers(String ids){
		Map<String, Object> result = this.getResult();
		try {
			userService.deleteUsers(ids.split(","));
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			//日志记录???
			return result;
		}
		return result;
	}
	/**
	 * 去新增或者修改的界面
	 * 如果type == 1 说明是只修改密码
	 * @param ids
	 * @return
	 */
	@RequestMapping("operatedui")
	public ModelAndView operatedUI(HttpServletRequest request,Integer id,Model model,String type){
		Map<String, Object> result = this.getResult();
		
		//获取登录用户信息
		ChannelUser loginUser = (ChannelUser)request.getSession().getAttribute("loginUser");
		//获取角色id
		Integer roleId = channelRoleDao.getRoleByUserId(loginUser.getUserId()).getRoleId();
		List<CommonHospital> hospitals = null;
		//判断角色类型
		if(roleId == 1 || roleId ==2) {
			//说明是超级管理员或区级管理员，则获取所有医院列表
			hospitals = commonhospitalService.getAllHospital();
		}else {
			//说明是普通管理员，则获得本医院信息
			Integer hosId = Integer.valueOf(loginUser.getHosId());
			hospitals = new ArrayList<CommonHospital>();
			CommonHospital commonHospital = commonhospitalService.getById(hosId);
			hospitals.add(commonHospital);
		}
		
		try {
			ChannelUser user = new ChannelUser();
//			String areaCode = "0000";
//			List<CommonHospital> hospitals = commonhospitalService.getHospitalForUser(areaCode);
			result.put("hospitals", hospitals);
			if(id != null && id != 0){
				//修改界面
				user = userService.getById(id);
				if(type != null && "1".equals(type)){
					//只修改密码
					model.addAttribute("upPassWord", true);
				}
				//查询机构科室
				Department department = new Department();
				department.setHosid(Integer.parseInt(user.getHosId()));
				List<Department> departmentList = departmentService.getDepartmentList(department);
				model.addAttribute("departmentList", departmentList);
			}
			model.addAttribute("info", user);
		} catch (Exception e) {
			e.printStackTrace();
			//???日志记录 跳转到错误页面
		}
		return new ModelAndView("user/userOperate",result);
	}
	/**
	 * 获取当前登录人所拥有的最高等级角色以及该等级以下的角色
	 * @return
	 */
	@RequestMapping("getrolesbyuserid")
	@ResponseBody
	public Map<String, Object> getRolesByUserId(HttpServletRequest request,Integer id){
		Map<String, Object> result = this.getResult();
		//获取当前登录人的id
		ChannelUser loginUser = (ChannelUser)request.getSession().getAttribute("loginUser");
		Integer loginUserId = loginUser.getUserId();
		try {
			List<Map> list = channelRoleService.getRolesByUserId(loginUserId,id);
			if(list!=null&&list.size()>0){
				result.put("success", true);
				result.put("data", list);
			}
		} catch (Exception e) {
			// 打印日志???????
			e.printStackTrace();
			return result;
		}
		return result;
	}
	/**
	 * 给用户添加一个角色
	 */
	@RequestMapping("operationuserrole")
	@ResponseBody
	public Map<String, Object> operationUserRole(String roleIds,Integer userId){
		Map<String, Object> result = this.getResult();
		try {
			channelUserRoleService.operationRoleMenu(roleIds, userId);
			result.put("success", true);
		} catch (Exception e) {
			// 打印日志
			e.printStackTrace();
			return result;
		}
		return result;
	}
	/**
	 * 去user选择role的界面
	 */
	@RequestMapping("userroleui")
	public ModelAndView userRoleUI(){
		return new ModelAndView("user/roleTree"); 
	}



	/**
	 * 根据用户信息过滤用户
	 * @param user
	 * @return
	 */
    @ResponseBody
	@RequestMapping(value="/selectByUserInfo",produces = "application/json; charset=utf-8")
	public Map<String, Object>  selectByUserInfo( @RequestBody ChannelUser user)throws Exception{
		Map<String, Object> result = this.getResult();
		try {
			result.put("success", true);
			result.put("data", userService.selectByUserInfo(user));
		} catch (Exception e) {
			result.put("success", false);
			result.put("message", "操作错误");
			if(e instanceof ParameterException){
				result.put("message", e.getMessage());
			}
			e.printStackTrace();
			//日志输出
			return result;
		}
		return result;
	}

	private Map<String, Object> getResult(){
		Map<String, Object> map = new HashMap();
		map.put("success", false);
		return map;
	}
}
