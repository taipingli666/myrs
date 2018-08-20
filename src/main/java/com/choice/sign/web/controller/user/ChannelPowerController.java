package com.choice.sign.web.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choice.domain.entity.user.ChannelPower;
import com.choice.domain.service.user.ChannelPowerService;
import com.choice.sign.exception.ParameterException;
/**
 * 角色对应菜单控制层
 * @author duhuo
 *
 */
@RestController
@RequestMapping("power")
public class ChannelPowerController {
	@Resource 
	private ChannelPowerService channelPowerService;
	/**
	 * 给一个角色添加角色菜单 
	 */
	@RequestMapping("operationrolemenu")
	public Map<String, Object> operationRoleMenu(String ids,Integer roleId){
		Map<String, Object> result = this.getResult();
		try {
			channelPowerService.operationRoleMenu(ids,roleId);
		} catch (Exception e) {
			result.put("message", "保存失败");
			if(e instanceof ParameterException){
				result.put("message", e.getMessage());
			}
			e.printStackTrace();
			// TODO 打印日志????????
			return result;
		}
		result.put("success", true);
		return result;
	}
	
	
	private Map<String, Object> getResult(){
		Map<String, Object> map = new HashMap();
		map.put("success", false);
		return map;
	}
}
