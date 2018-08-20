package com.choice.domain.service.dictionary;

import java.util.List;

import com.choice.domain.entity.dictionary.Department;
import com.choice.sign.util.Page;

public interface DepartmentService {
	Page getPage(int currentPage,long hosid,String contents);
	
	void deleteDepartment(String[] ids);
	
	void insertDepartment(Department department);
	
	Department getInfo(long id);
	
	
	void updateDepartment(Department department);

	/**
	 * 根据医院查询科室
	 * @param department
	 * @return
	 */
	List<Department> getDepartmentList(Department department);

	/**
	 * 检查是否已经存在此科室
	 */
	int checkDepartment(Department department);
}
