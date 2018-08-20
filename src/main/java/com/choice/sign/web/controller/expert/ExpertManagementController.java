package com.choice.sign.web.controller.expert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.choice.domain.entity.common.CommonDoctorTeam;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.user.UserService;
import com.choice.sign.util.Page;
import com.choice.sign.web.controller.base.BaseController;
import com.choice.sign.web.controller.user.AuthPassport;

/**
 * expert相关处理controller
 * @author yanDong
 *
 */
@Controller
@RequestMapping("/expert")
public class ExpertManagementController extends BaseController{
	@Autowired
	private UserService userService;
	
	@Resource
	private CommonHospitalService commonHospitalService;
	/**
	 * 分页展示
	 * @param page
	 * @param size
	 * @param user
	 * @param modelmap
	 * @return
	 * @throws Exception
	 */
	@AuthPassport
	@RequestMapping(value="/display",produces="text/html;charset=UTF-8")
	public ModelAndView display(@RequestParam(value="page",defaultValue="1") Integer page,
								@RequestParam(value="size",defaultValue="10")Integer size,ChannelUser user,ModelMap modelmap
								,HttpServletRequest request)throws Exception {
		ChannelUser loginUser = (ChannelUser) request.getSession().getAttribute("loginUser");
		if(loginUser!=null){
			Integer hosId = Integer.parseInt(loginUser.getHosId().trim());
			String areaCode = commonHospitalService.getById(hosId).getAreaCode();
			char[] ss = areaCode.toCharArray(); 
			int n =ss.length-1;
			for (int i = ss.length-1; i > 0; i--) {
				if(ss[i]!='0'){
					n = i;
					break;
				}
			}
			String subAreaCode= areaCode.substring(0, n+1);
			List<Integer> hosidList = commonHospitalService.getHosIdListForSelect(subAreaCode);//查询该用户所能看到的hosId集合
			String temp = "";
			for (int i = 0; i < hosidList.size(); i++) {
				if(temp == ""){
					temp = String.valueOf(hosidList.get(i));
				}else{
					temp += ","+String.valueOf(hosidList.get(i));
				}
			}
			Page<ChannelUser> info = null;
			if(temp!=""){
				info = userService.getPageForExpert(page,size,user,temp.split(","));
			}
			modelmap.put("page", info);
			modelmap.put("user", user);
			return new ModelAndView("expert/expertList",modelmap);
		}else{
			return new ModelAndView("login",modelmap);
		}
		
		
	}
	/**
	 * 增改
	 */
	@RequestMapping("operated")
	@ResponseBody
	public Map<String, Object> operated(ChannelUser user){
		Map<String, Object> result = this.getResult();
		try {
			userService.operated(user);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
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
			return result;
		}
		return result;
	}
	/**
	 * 去新增或者修改的界面
	 * @param ids
	 * @return
	 */
	@RequestMapping("operatedui")
	public ModelAndView operatedUI(Integer id,Model model){
		try {
			ChannelUser user = new ChannelUser();
			if(id != null && id != 0){
				//修改界面
				user = userService.getById(id);
			}
			model.addAttribute("info", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("expert/expertOperate");
	}
	
	
	private Map<String, Object> getResult(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		return map;
	}
}

