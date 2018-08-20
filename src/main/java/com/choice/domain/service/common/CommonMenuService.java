package com.choice.domain.service.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.choice.domain.entity.common.CommonMenu;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.sign.util.Page;

public interface CommonMenuService {
	
	//获取列表
	Page<CommonMenu> getPage(Integer page, Integer size, CommonMenu commonMenu,String contents);

	//新增
	public void insertMenu(
			@Param(value="commonMenu")CommonMenu commonMenu);
	
	//修改
	public void updateMenu(
			@Param(value="commonMenu")CommonMenu commonMenu);
	
	//删除
	public void delMenu(
				@Param(value="menuId")String[] menuIds);
	
	public	List<Map> getMenusByRoleId(@Param(value="roleId")int roleId);
	
	//获取一级菜单
	public List<CommonMenu> getFristMenus();
	
	//通过menuId获取commonmenu对象
	public CommonMenu getMenuById(Integer menuId);

	//获取系统左侧菜单，delete=0 and display=0
	public List<CommonMenu> getDisplayMenus();
	
	//用户登录筛选菜单
	public List<Map> getMenuForLogin(int roleId,String[] roleString);
	
	// 通过url找到menuId 
	public Integer getMenuIdFromURL(String url);
}
