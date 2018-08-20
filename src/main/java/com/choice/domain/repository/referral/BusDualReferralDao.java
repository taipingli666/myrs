 package com.choice.domain.repository.referral;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.choice.domain.entity.referral.BusDualReferral;
import com.choice.domain.entity.referral.InOUtChtCount;

public interface BusDualReferralDao {
	
	int updateByPrimaryKeySelective(BusDualReferral busDualReferral);
	//根据id查找病人
	BusDualReferral selectByPrimaryKey(Long id);
  
	List<BusDualReferral> selectByDrIdFrom(@Param("busDualReferral")BusDualReferral busDualReferral);
	
	int getTotalNumber(@Param("busDualReferral")BusDualReferral busDualReferral);
	
	List<BusDualReferral> getPageInfo(@Param("busDualReferral")BusDualReferral busDualReferral,
			@Param("startIndex")int startIndex,@Param("pageSize")int pageSize);
	
	int insertSelective(BusDualReferral busDualReferral);
	//根据身份证和医院查找转诊信息
	BusDualReferral infoByCardId(@Param("cardId")String cardId, @Param("hosId")String hosId);
	
	//转诊报表统计
	List<Map> referralReport(@Param("year")String year, @Param("refType")String refType, @Param("hosId")String hosId);
	
	//转诊报表年份
	List<Map> distinctYear();	
}