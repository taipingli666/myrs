package com.choice.domain.service.user.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choice.domain.entity.common.CommonMenu;
import com.choice.domain.entity.user.ChannelRole;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.repository.user.ChannelRoleDao;
import com.choice.domain.service.common.CommonMenuService;
import com.choice.domain.service.user.ChannelRoleService;
import com.choice.domain.service.user.ChannelUserRoleService;
import com.choice.sign.exception.ParameterException;
import com.choice.sign.util.Page;
import com.github.pagehelper.PageHelper;
@Service
public class ChannelRoleServiceImpl implements ChannelRoleService{
	@Resource
	private ChannelRoleDao channelRoleDao;
	
	@Resource
	private CommonMenuService commonMenuService;
	
	@Resource
	private ChannelUserRoleService channelUserRoleService;
	/**
	 * 分页获取角色对象
	 */
	public Page<ChannelRole> getPage(Integer page, Integer size, ChannelRole channelRole) {
		com.github.pagehelper.Page startPage = PageHelper.startPage(page, size);
		List<ChannelRole> list = channelRoleDao.getList(channelRole);
		//PageInfo<ChannelUser> info = new PageInfo<ChannelUser>(list);
		Long total = startPage.getTotal();
		
		return new Page<ChannelRole>(size, page, total.intValue(), list);
	}
	/**
	 * 根据角色id获取全部菜单
	 */
	public List<Map> getMenusByRoleId(Integer roleId) {
		if(roleId == null){
			throw new ParameterException("错误:参数为空");
		}
		List<Map> list = commonMenuService.getMenusByRoleId(roleId);
		Long c;
		for(Map t:list){
			c = (Long)t.get("checked");
			if(c == 1){
                //说明是属于的
                t.put("checked", true);
            }else{
            	t.put("checked", false);
            }
		}
		return list;
	}
	/**
	 * 根据id获取role对象
	 */
	public ChannelRole getById(Integer id) {
		return channelRoleDao.selectByPrimaryKey(id);
	}
	/**
	 * 增加或者修改
	 */
	public void operated(ChannelRole channelRole) {
		if(channelRole != null){
			if(channelRole.getRoleId()==null){
				//新增
				channelRole.setIsDelete(0);
				channelRole.setAddTime(new Date());
				channelRoleDao.insert(channelRole);
			}else{
				//更新
				channelRole.setEditTime(new Date());
				channelRoleDao.updateByPrimaryKeySelective(channelRole);
			}
		}else{
			throw new ParameterException("错误:参数为空");
		}
	}
	/**
	 * 批量删除
	 */
	@Transactional
	public void deleteRoles(String[] split) {
		int i =channelRoleDao.deleteRoles(split);
		if(i == 0){
			throw new RuntimeException();
		}
		//删除中间表数据
		channelUserRoleService.delRoles(split);
	}
	/**
	 * 获取全部角色,用户id已选中的
	 */
	public List<Map> getRolesByUserId(Integer loginUserId,Integer id) {
		if(id == null){
			throw new ParameterException("错误:参数为空");
		}
		List<Map> list = channelRoleDao.getRolesByUserId(loginUserId,id);
		int xt = 0;
		int zdy = 0;
		for(Map t : list){
			Integer c;
			int i;
			c = (Integer)t.get("checked");
			if(c == 1){
                //说明是属于的
                t.put("checked", true);
            }else{
            	t.put("checked", false);
            }
			i = (Integer)t.get("flag");
			if(i == 0){
				//系统角色
				t.put("parentId",-1);
				xt = 1;
			}else{
				//自定义角色
				t.put("parentId",-2);
				zdy = 1;
			}
		}
		Map map = null;
		if(xt == 1){
			//创建父菜单
			map = new HashMap<String, Object>();
			map.put("roleId", -1);
			map.put("name", "系统角色");
			list.add(map);
		}
		if(zdy == 1){
			//创建父级别菜单
			map = new HashMap<String, Object>();
			map.put("roleId", -2);
			map.put("name", "自定义角色");
			list.add(map);
		}
		return list;
	}
}
