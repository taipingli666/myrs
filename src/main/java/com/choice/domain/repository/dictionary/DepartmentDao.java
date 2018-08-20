package com.choice.domain.repository.dictionary;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.choice.domain.entity.dictionary.Department;

public interface DepartmentDao {
	
	int getTotalNumber(@Param("hosid")long hosid,@Param("contents")String contents);
	
	List<Department> getPageInfo(@Param("hosid")long hosid,@Param("contents")String contents,
			@Param("StartIndex")int StartIndex,@Param("endIndex")int endIndex);
	
	void deleteDepartment(String[] ids);
	
	void insertDepartment(@Param("department")Department department);
	
	Department getInfo(@Param("id")long id);
	
	
	void updateDepartment(@Param("department")Department department);

	/**
	 * 查询科室数据
	 * @param department
	 * @return
	 */
	List<Department> selectDepartment(@Param("department")Department department);
	/**
	 * 检查是否已经存在此科室
	 */
	int checkDepartment(Department department);
}
