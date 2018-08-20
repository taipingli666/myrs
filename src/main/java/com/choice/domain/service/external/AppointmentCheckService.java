package com.choice.domain.service.external;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.choice.domain.entity.external.InspectInfo;
import com.choice.domain.entity.external.ItemListParam;
import com.choice.sign.util.Page;

public interface AppointmentCheckService {
	
	/**
	 * 获取检查大类
	 * @param hosCode 医院代码
	 * @return json字符串
	 */
	String getCheckClass(String hosCode); 
	
	
	/**
	 * 获取检查小类
	 * @param inspectInfo 
	 * @return json字符串
	 */
	String getMiniCheckClass(InspectInfo inspectInfo); 
	
	
	/**
	 * 获取检查项
	 * @param inspectInfo
	 * @return
	 */
	String getCheckItem(InspectInfo inspectInfo); 
	
	/**
	 * 保存预约检查单
	 * @param inspectInfo
	 * @return 保存是否成功的结果信息
	 */
	Map<String,Object> saveCheckList(HttpServletRequest request,InspectInfo inspectInfo);
	
	/**
	 * 查看检查列表
	 */
	Page getCheckItemList(ItemListParam itemListParam,int pageNumber);
}
