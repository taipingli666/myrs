package com.choice.domain.service.common.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.choice.domain.entity.common.CommonMenu;
import com.choice.domain.repository.common.CommonMenuDao;
import com.choice.domain.service.common.CommonMenuService;
import com.choice.sign.util.Page;
import com.github.pagehelper.PageHelper;

@Service("commonMenuService")
public class CommonMenuServiceImpl implements CommonMenuService {

	@Resource
	private CommonMenuDao commonMenuDao;
	
	
	public Page<CommonMenu> getPage(Integer page, Integer size, CommonMenu commonMenu,String contents){
		
		com.github.pagehelper.Page startPage = PageHelper.startPage(page, size);
		List<CommonMenu> list = commonMenuDao.getMenus(contents);
		//PageInfo<ChannelUser> info = new PageInfo<ChannelUser>(list);
		Long total = startPage.getTotal();
		return new Page<CommonMenu>(size, page, total.intValue(), list);
	}

	public void insertMenu(CommonMenu commonMenu) {
		// TODO Auto-generated method stub
		commonMenuDao.insertMenu(commonMenu);

	}

	public void updateMenu(CommonMenu commonMenu) {
		// TODO Auto-generated method stub
		commonMenuDao.updateMenu(commonMenu);
	}

	public void delMenu(String[] menuIds) {
		// TODO Auto-generated method stub
		int i = commonMenuDao.delMenu(menuIds);
		if(i == 0){
			throw new RuntimeException();
		}
		
	}

	public	List<Map> getMenusByRoleId(@Param(value="roleId")int roleId){
			
		return commonMenuDao.getMenusByRoleId(roleId);
	}
	
	//获取一级菜单
	public List<CommonMenu> getFristMenus(){
		return commonMenuDao.getFristMenus();
	}
	
	//通过menuId获取commonmenu对象
	public CommonMenu getMenuById(Integer menuId){
		return commonMenuDao.getMenuById(menuId);
	}
	
	//获取系统左侧菜单，delete=0 and display=0
	public List<CommonMenu> getDisplayMenus(){
		return commonMenuDao.getDisplayMenus();
	}
	
	//用户登录筛选菜单
	public List<Map> getMenuForLogin(int roleId,String[] roleString){
		return commonMenuDao.getMenuForLogin(roleId, roleString);
	};
	
	// 通过url找到menuId 
	public Integer getMenuIdFromURL(String url){
		return commonMenuDao.getMenuIdFromURL(url);
	};

}
