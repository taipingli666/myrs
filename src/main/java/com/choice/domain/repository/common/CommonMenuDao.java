package com.choice.domain.repository.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.choice.domain.entity.common.CommonMenu;



public interface CommonMenuDao {
	
	//获取列表
	public List<CommonMenu> getMenus(@Param(value="contents")String contents);

	//新增
	public void insertMenu(
			@Param(value="commonMenu")CommonMenu commonMenu);
	
	//修改
	public void updateMenu(
			@Param(value="commonMenu")CommonMenu commonMenu);
	
	//删除
	public int delMenu(String[] menuIds);
	
	public	List<Map> getMenusByRoleId(@Param(value="roleId")int roleId);
	
	//获取一级菜单
	public List<CommonMenu> getFristMenus();

	//通过menuId获取commonmenu对象
	public CommonMenu getMenuById(Integer menuId);
	
	//获取系统左侧菜单，delete=0 and display=0
	public List<CommonMenu> getDisplayMenus();
	
	//用户登录筛选菜单
	public	List<Map> getMenuForLogin(
			@Param(value="roleId")int roleId,
			@Param(value="roleString")String[] roleString);
	
	// 通过url找到menuId 
	public Integer getMenuIdFromURL(String url);

}
