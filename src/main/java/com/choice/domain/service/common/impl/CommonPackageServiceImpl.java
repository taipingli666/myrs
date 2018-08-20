package com.choice.domain.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.choice.domain.entity.common.CommonPackage;
import com.choice.domain.repository.common.CommonPackageDao;
import com.choice.domain.service.common.CommonPackageService;
import com.choice.sign.util.Page;

@Service("CommonPackageService")
public class CommonPackageServiceImpl implements CommonPackageService{
	
	@Resource
	CommonPackageDao commonPackageDao;
	 
	public Page getPage(int currentPage,String className){
		int pageSize = 10;
		Integer total =commonPackageDao.getTotalNumber(className);
		int pageIndex = Page.calPageIndex(currentPage, pageSize);
		List<CommonPackage> list = commonPackageDao.selectByQuery(className, pageIndex, pageIndex + pageSize);
		return new Page<CommonPackage>(pageSize, currentPage, total, list);
	}
	
	
	public CommonPackage getInfo(long id){
			return commonPackageDao.selectByPrimaryKey(id); 		
	}
	
	public List<CommonPackage> selectByAll(){
		return commonPackageDao.selectByAll(); 		
}
	
	public void deleteCommonPackage(String[] ids) {
		commonPackageDao.deleteCommonPackage(ids);
	}

	public void insertCommonPackage(CommonPackage commonpackage) {
		commonPackageDao.insertCommonPackage(commonpackage);
	}

	public void updateCommonPackage(CommonPackage commonpackage) {
		commonPackageDao.updateCommonPackage(commonpackage);
	}


	public CommonPackage getInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
