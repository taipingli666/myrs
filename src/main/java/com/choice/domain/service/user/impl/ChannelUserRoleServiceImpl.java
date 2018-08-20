package com.choice.domain.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choice.domain.entity.user.ChannelUserRole;
import com.choice.domain.repository.user.ChannelUserRoleDao;
import com.choice.domain.service.user.ChannelUserRoleService;
import com.choice.sign.exception.ParameterException;
/**
 * 用户对应角色实现类
 * @author duhuo
 *
 */
@Service
public class ChannelUserRoleServiceImpl implements ChannelUserRoleService{
	@Resource 
	private ChannelUserRoleDao channelUserRoleDao;
	/**
	 * 操作角色对应菜单
	 */
	@Transactional
	public void operationRoleMenu(String ids,Integer userId) {
		if(ids == null||"".equals(ids)||userId == null){
			throw new ParameterException("错误:参数错误");
		}
		//先删除全部
		channelUserRoleDao.delRolesByUserId(userId);
		if(ids.equals("st")){
			//没有勾选
			return;
		}
		//循环插入新数据
		String[] st = ids.split(",");
		ChannelUserRole roles = null;
		List<ChannelUserRole> list = new ArrayList<ChannelUserRole>();
		for(String s : st){
			//s为roleid
			roles = new ChannelUserRole();
			roles.setRoleId(Integer.parseInt(s));
			roles.setUserId(userId);
			list.add(roles);
		}
		int i2 = channelUserRoleDao.operationUserRole(list);
		if(i2==0){
			//插入失败
			throw new ParameterException("操作错误:操作角色失败");
		}
	}
	/**
	 * 删除id为参数的中间表数据
	 */
	public void delRoles(String[] split) {
		channelUserRoleDao.delRoles(split);
	}
	
	//根据用户iD查权限
	public int roleIdByUserId(Integer userId) {
		return channelUserRoleDao.roleIdByUserId(userId);
	}
}
