package com.choice.domain.repository.contract;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.choice.domain.entity.contract.BusinessContract;

public interface BusinessContractDao {
	
	int getTotalNumber(@Param("contract")BusinessContract contract);
	
	List<Map> getPageInfo(@Param("contract")BusinessContract contract,
			@Param("StartIndex")int StartIndex,@Param("endIndex")int endIndex);
	
	BusinessContract selectByPrimaryKey(@Param("contractId")int contractId);
	
	int updateByPrimaryKeySelective(BusinessContract contract);
	
	BusinessContract getContractByUK(@Param("contract")BusinessContract contract);
	
	int InsertBusinessContract(BusinessContract contract);
	
	List<String> getAllSignyear();
	
	List<BusinessContract> selectByAddTime(@Param("contract")BusinessContract contract,
			@Param("hosIds")List<Integer> hosIds);
	
	/**
	 * 获取当前登录人所属团队的签约病人数量
	 */
	int getTotalNumberByTeamId(@Param("contract")BusinessContract contract,@Param("teamId")Integer teamId);
	
	/**
	 * 获取当前登录人所属团队的签约病人列表
	 */
	List<Map> getPageInfoByTeamId(@Param("contract")BusinessContract contract,@Param("teamId")Integer teamId,
			@Param("StartIndex")int StartIndex,@Param("endIndex")int endIndex);
	
	/**
	 * 查询病人是否有下一年的续约记录(根据身份证号和签约年份)
	 */
	int getRenewRecordCount(@Param("card")String card,@Param("signYear")String signYear);
}
