package com.choice.domain.service.referral;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.choice.domain.entity.referral.BusDualReferral;
import com.choice.sign.util.Page;

public interface BusDualReferralService {
	
	BusDualReferral selectByPrimaryKey(Long id);

	//已有病人列表
	List<BusDualReferral> selectByDrIdFrom(BusDualReferral busDualReferral);

	Page getPage(int currentPage,BusDualReferral busDualReferral);
   
	int insertSelective(BusDualReferral busDualReferral);
	
	//根绝身份证id查询基本user信息
	BusDualReferral infoByCardId(String cardId, String hosId);
	
	int updateByPrimaryKeySelective(BusDualReferral busDualReferral);
	
	//转诊转入转出报表统计
	List<Map> referralReport(String year, String refType, String hosId);
	
	//转诊报表年份
	List<Map> distinctYear();
}
