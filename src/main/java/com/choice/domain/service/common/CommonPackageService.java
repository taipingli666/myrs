package com.choice.domain.service.common;

import java.util.List;

import com.choice.domain.entity.common.CommonPackage;
import com.choice.domain.entity.dictionary.Department;
import com.choice.sign.util.Page;

public interface CommonPackageService {
	
	/**
	 * 根据条件获取记录
	 * @Title: getPage 
	 * @Description: TODO
	 * @param currentPage
	 * @param name
	 * @return Page
	 * @author shenhf 
	 * @created 2017年11月9日 下午4:47:54
	 */
	Page getPage(int currentPage,String className);
	
	/**
	 * 根据id获取数据
	 * @Title: getInfo 
	 * @Description: TODO
	 * @param id
	 * @return Dictionary
	 * @author shenhf 
	 * @created 2017年11月9日 下午4:13:01
	 */
	CommonPackage getInfo(long id);
	/**
	 * 获取所有数据
	 * @Title: selectByAll 
	 * @Description: TODO
	 * @return List<CommonPackage>
	 * @author zdd 
	 * @created 2017年11月9日 下午4:13:01
	 */
	List<CommonPackage> selectByAll();
	
	void deleteCommonPackage(String[] ids);
	
	void insertCommonPackage(CommonPackage commonpackage);
	
	
	void updateCommonPackage(CommonPackage commonpackage);
}
