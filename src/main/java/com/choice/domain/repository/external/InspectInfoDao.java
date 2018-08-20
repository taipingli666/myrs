package com.choice.domain.repository.external;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.choice.domain.entity.external.InspectInfo;
import com.choice.domain.entity.referral.BusDualReferral;

public interface InspectInfoDao {
	//插入预约单信息
	int insertInspectInfo (InspectInfo inspectInfo);
}
