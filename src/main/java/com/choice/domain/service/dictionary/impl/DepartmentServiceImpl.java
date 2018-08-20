package com.choice.domain.service.dictionary.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.choice.domain.entity.dictionary.Department;
import com.choice.domain.repository.dictionary.DepartmentDao;
import com.choice.domain.service.dictionary.DepartmentService;
import com.choice.sign.util.Page;

@Service("DepartmentService")
public class DepartmentServiceImpl implements DepartmentService{
	@Resource
	private DepartmentDao  departmentDao;
	
	public Page<Department> getPage(int currentPage,long hosid,String contents) {
		int pageSize = 10;
		Integer total = departmentDao.getTotalNumber(hosid,contents);
		int pageIndex = Page.calPageIndex(currentPage, pageSize);
		List<Department> list = departmentDao.getPageInfo(hosid,contents, pageIndex,pageSize);
		return new Page<Department>(pageSize, currentPage, total, list);
	}

	public void deleteDepartment(String[] ids) {
		departmentDao.deleteDepartment(ids);
	}

	public void insertDepartment(Department department) {
		departmentDao.insertDepartment(department);
	}

	public void updateDepartment(Department department) {
		departmentDao.updateDepartment(department);
	}

	/**
	 * 根据查询科室
	 * @param department
	 * @return
	 */
	@Override
	public List<Department> getDepartmentList(Department department) {
		try{
			return departmentDao.selectDepartment(department);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public int checkDepartment(Department department) {
		return departmentDao.checkDepartment(department);
	}

	public Department getInfo(long id) {
		return departmentDao.getInfo(id);
	}

}
