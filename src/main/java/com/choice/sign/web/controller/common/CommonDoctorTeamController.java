package com.choice.sign.web.controller.common;

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
import com.choice.domain.service.common.CommonDoctorTeamService;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.user.UserService;
import com.choice.sign.util.Page;
import com.choice.sign.web.controller.base.BaseController;
import com.choice.sign.web.controller.user.AuthPassport;

import net.sf.json.JSONArray;

/**
 * team相关处理controller
 * @author yanDong
 *
 */
@Controller
@RequestMapping("/team")
public class CommonDoctorTeamController extends BaseController{
	@Autowired
	private CommonDoctorTeamService commonDoctorTeamService;
	
	@Resource
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
								@RequestParam(value="size",defaultValue="10")Integer size,CommonDoctorTeam team,ModelMap modelmap,HttpServletRequest request)
			throws Exception {
		ChannelUser user = (ChannelUser) request.getSession().getAttribute("loginUser");
		if(user!=null){
			Integer hosId = Integer.parseInt(user.getHosId().trim());
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
			Page<CommonDoctorTeam> info = null;
			if(temp!=""){
				info = commonDoctorTeamService.getPage(page,size,team,temp.split(","));
			}
			
			modelmap.put("page", info);
			modelmap.put("team", team);
			return new ModelAndView("team/teamList",modelmap);
		}else{
			return new ModelAndView("login",modelmap);
		}
		
		
	}
	/**
	 * 增改
	 */
	@RequestMapping(value="operated")
	@ResponseBody
	public Map<String, Object> operated(CommonDoctorTeam team,
			@RequestParam(value="ids")String ids,@RequestParam(value="prevIds")String prevIds){
		Map<String, Object> result = this.getResult();
		try {
			commonDoctorTeamService.operated(team,ids,prevIds);
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
	@RequestMapping(value="deleteteams")
	@ResponseBody
	public Map<String, Object> deleteTeams(String ids){
		Map<String, Object> result = this.getResult();
		try {
			String[] subString = ids.split(",");
			commonDoctorTeamService.deleteTeams(subString);
			for (int i = 0; i < subString.length; i++) {
				String temp = "";
				List<Integer> userIdlist = userService.getUserIdsForLeave(Integer.parseInt(subString[i]));
				for (int j = 0; j < userIdlist.size(); j++) {
					if(temp == ""){
						temp = String.valueOf(userIdlist.get(j));
					}else{
						temp += ","+String.valueOf(userIdlist.get(j));
					}
				}
				userService.userForLeaveTeam(temp.split(","));
			}
			
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
	@RequestMapping(value="operatedui",produces="text/html;charset=UTF-8")
	public ModelAndView operatedUI(Integer id,HttpServletRequest request,Model model){
		ChannelUser user = (ChannelUser) request.getSession().getAttribute("loginUser");
		if(user!=null){
			Integer hosId = Integer.parseInt(user.getHosId());
			model.addAttribute("hosId", hosId);
		}
		
		List<ChannelUser> users = null;
		List<ChannelUser> members = null;
		List<Integer> prevIds = null;
		List<ChannelUser> experts = null;
		String temp ="";
		String leaderName = null;
		int pageNumber = 1;
		int size = 5;
		try {
			CommonDoctorTeam team = new CommonDoctorTeam();
			if(id != null && id != 0){
				//修改界面
				team = commonDoctorTeamService.getById(id);
				leaderName = userService.selectByPrimaryKey(team.getTeamLeader()).getUserName();
				members= userService.getTeamsTableUpdateForMember(id);
				prevIds = userService.getPrevIds(id);
				for (int j = 0; j < prevIds.size(); j++) {
					if(temp == ""){
						temp = String.valueOf(prevIds.get(j));
					}else{
						temp += ","+String.valueOf(prevIds.get(j));
					}
				}
				model.addAttribute("leaderName", leaderName);
				model.addAttribute("method", "1");
			}else{
				model.addAttribute("method", "0");
			}
			users= userService.getTeamsTableUpdateForUser(id,"",(pageNumber-1)*size,size);
			experts = userService.getExpertList();
			model.addAttribute("info", team);
			model.addAttribute("prevIds", temp);
			model.addAttribute("pageNumber", pageNumber);
			model.addAttribute("teamId", id);
			model.addAttribute("members", members);
			model.addAttribute("users", users);
			model.addAttribute("experts", experts);
		} catch (Exception e) {
			e.printStackTrace();
			//???日志记录
		}
		if(user==null){
			return new ModelAndView("login");
		}
		return new ModelAndView("team/teamOperate");
	}
	
	/**
	 * ajax分页
	 */
	@RequestMapping(value="page",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String changePage(@RequestParam(value="pageNumber")int pageNumber,
			@RequestParam(value="size",defaultValue="5")int size,
			@RequestParam(value="selectName")String selectName,
			@RequestParam(value="id")int id){
		List<ChannelUser> users = null;
		try {
			users= userService.getTeamsTableUpdateForUser(id,selectName,(pageNumber-1)*size,size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(JSONArray.fromObject(users).toString());
		return JSONArray.fromObject(users).toString();
	}
	
	
	private Map<String, Object> getResult(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		return map;
	}

}


