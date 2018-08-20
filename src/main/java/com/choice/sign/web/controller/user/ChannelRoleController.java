package com.choice.sign.web.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.choice.domain.entity.common.CommonMenu;
import com.choice.domain.entity.user.ChannelRole;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.service.user.ChannelPowerService;
import com.choice.domain.service.user.ChannelRoleService;
import com.choice.sign.exception.ParameterException;
import com.choice.sign.util.Page;
import com.choice.sign.web.controller.base.BaseController;
/**
 * 角色对应的控制层
 * @author duhuo
 *
 */
@Controller
@RequestMapping("role")
public class ChannelRoleController extends BaseController<ChannelRole>{
	@Resource
	private ChannelRoleService channelRoleService;
	@Resource
	private ChannelPowerService channelPowerService;
	/**
	 * 分页获取角色
	 * @param page
	 * @param size
	 * @param user
	 * @param modelmap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/display",produces="text/html;charset=UTF-8")
	public ModelAndView display(@RequestParam(value="page",defaultValue="1") Integer page,
								ChannelRole channelRole,ModelMap modelmap)
			throws Exception {
		Page<ChannelRole> info = channelRoleService.getPage(page,this.pageSize,channelRole);
		modelmap.put("page", info);
		modelmap.put("info", channelRole);
		return new ModelAndView("role/roleOperate",modelmap);
	}
	/**
	 * 获取该角色下的所有菜单
	 */
	@RequestMapping("getmenusbyroleId")
	@ResponseBody
	public Map<String, Object> getMenusByRoleId(Integer id){
		Map<String, Object> result = this.getResult();
		try {
			List<Map> list = channelRoleService.getMenusByRoleId(id);
			if(list != null && list.size()>0){
				result.put("success", true);
				result.put("data", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//打印日志?????
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
			ChannelRole role = new ChannelRole();
			if(id != null && id != 0){
				//修改界面
				role = channelRoleService.getById(id);
			}
			model.addAttribute("info", role);
		} catch (Exception e) {
			e.printStackTrace();
			//???日志记录
		}
		return new ModelAndView("role/operate");
	}
	
	/**
	 * 增改
	 */
	@RequestMapping("operated")
	@ResponseBody
	public Map<String, Object> operated(ChannelRole channelRole){
		Map<String, Object> result = this.getResult();
		try {
			channelRoleService.operated(channelRole);
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
	@RequestMapping("deleteroles")
	@ResponseBody
	public Map<String, Object> deleteUsers(String ids){
		Map<String, Object> result = this.getResult();
		try {
			channelRoleService.deleteRoles(ids.split(","));
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			//日志记录???
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
