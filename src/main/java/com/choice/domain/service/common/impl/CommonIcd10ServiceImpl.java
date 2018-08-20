package com.choice.domain.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.choice.domain.entity.common.CommonIcd10;
import com.choice.domain.repository.common.CommonIcd10Dao;
import com.choice.domain.service.common.CommonIcd10Service;

@Service("commonIcd10Service")
public class CommonIcd10ServiceImpl implements CommonIcd10Service {

	@Resource 
	private CommonIcd10Dao commonIcd10Dao;
	
	
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int insert(CommonIcd10 record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int insertSelective(CommonIcd10 record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public CommonIcd10 selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int updateByPrimaryKeySelective(CommonIcd10 record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateByPrimaryKey(CommonIcd10 record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<CommonIcd10> selectBySelect(String searchValue, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		
		return commonIcd10Dao.selectBySelect(searchValue, (pageNum - 1) * pageSize, pageSize);
	}

	
	public int countIcd10(String searchValue) {
		// TODO Auto-generated method stub
		return commonIcd10Dao.countIcd10(searchValue);
	}

}
