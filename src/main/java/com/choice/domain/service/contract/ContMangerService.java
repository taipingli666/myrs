package com.choice.domain.service.contract;

import java.util.List;
import java.util.Map;

import com.choice.domain.entity.contract.BusinessContract;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.sign.util.Page;

public interface ContMangerService {
	
	Page getPage(int currentPage,BusinessContract contract);
	
	List<Map> ExportContents(BusinessContract contract);
	
	BusinessContract selectByPrimaryKey(int contractId);
	
	void updateByPrimaryKeySelective(BusinessContract contract);
	
	Map<String,Object> contractSigned(BusinessContract contract);
	
	List<String> getAllSignyear();
	
	List<BusinessContract> selectByAddTime(BusinessContract contract,List<Integer> hosIds);
	
	/**
	 * 获取当前登录人所属团队的签约病人
	 */
	Page getPageByTeamId(int currentPage,BusinessContract contract,Integer teamId);
	
	/**
	 * 根据id合同当前登录人续约
	 */
	public Map<String,Object> renew (Integer contractId,ChannelUser channelUser);
}
