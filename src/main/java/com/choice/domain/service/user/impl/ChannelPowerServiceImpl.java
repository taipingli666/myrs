package com.choice.domain.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choice.domain.entity.user.ChannelPower;
import com.choice.domain.repository.user.ChannelPowerDao;
import com.choice.domain.service.user.ChannelPowerService;
import com.choice.sign.exception.ParameterException;
/**
 * 角色对应菜单
 * @author duhuo
 *
 */
@Service
public class ChannelPowerServiceImpl implements ChannelPowerService {
	@Resource
	private ChannelPowerDao channelPowerDao;
	/**
	 * 操作角色对应菜单
	 */
	@Transactional
	public void operationRoleMenu(String ids,int roleId) {
		if(ids == null||"".equals(ids)){
			throw new ParameterException("参数错误");
		}
		//先删除全部
		channelPowerDao.delMenusByRoleId(roleId);
		if(ids.equals("st")){
			//没有勾选
			return;
		}
		//循环插入新数据
		String[] st = ids.split(",");
		ChannelPower power = null;
		List<ChannelPower> list = new ArrayList<ChannelPower>();
		for(String s : st){
			//s为menuid
			power = new ChannelPower();
			power.setMenuId(Integer.parseInt(s));
			power.setRoleId(roleId);
			list.add(power);
		}
		int i2 = channelPowerDao.operationRoleMenu(list);
		if(i2==0){
			//插入失败
			throw new ParameterException("操作错误:操作权限失败");
		}
	}
}
