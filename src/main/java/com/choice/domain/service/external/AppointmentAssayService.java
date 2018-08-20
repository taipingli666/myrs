package com.choice.domain.service.external;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.choice.domain.entity.external.InspectInfo;
import com.choice.domain.entity.external.ItemListParam;
import com.choice.domain.entity.referral.BusDualReferral;
import com.choice.sign.util.Page;

/**
 * 预约化验接口
 * @author litp
 * 2018-1-10 14:47:25
 */
public interface AppointmentAssayService {

	//化验大类
	String getAssayClass(String hosCode);
	
	//化验小类
	String getMiniAssayClass(InspectInfo info);
	
	//化验项目
	String getAssayItem(InspectInfo info);
	
	//化验样本
	String getAssaySample(InspectInfo info);
	
	//查看化验列表
	Page assayList(Integer pageNum, ItemListParam itemListParam);
	
	//保存化验单
	int saveAssayInfo(InspectInfo inspectInfo, HttpServletRequest request);
}
