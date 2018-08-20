/**
 * 
 */
/**
 * @author Paul.Pan
 *
 */
package com.choice.domain.service.common;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.choice.domain.entity.common.CommonArea;
import com.choice.sign.util.Page;

public interface CommonAreaService{
	
	Page<CommonArea> getPage(int page,int size,String areaName,String parentCode);
	// 删除行政区域
	void deleteCommonArea(int areaId);
	// 获取行政区域信息
	CommonArea getInfo(int areaId);
	// 批量删除行政区域信息
	void deleteCommonAreas(String[] substring);
	
	//增加、删除行政区域信息
	void operated(HttpServletRequest request,CommonArea commonArea);
	
	//获取父节点偶去全部行政区域信息,树调用方法
	List<Map<String,Object>> getCommonAreaAll(String parentCode);
	
	// 根据父节点获取子节点信息
	List<CommonArea> getCommonAreaByParentCode(String parentCode);
	
	//分页显示父节点所有子节点信息
	Page<CommonArea> getPageByParentCode(int page,int siez,String parentCode);
	
	//根据行政区划代码获取行政区划信息
	List<Map<String,Object>> getCommonAreaByAreaCode(String areaCode);
	
	//获取同级别地区
	List<CommonArea> getListByLevel(Integer level);

	/**
	 * 根据行政编码查询信息
	 * @param code
	 * @return
	 */
	CommonArea selectByCode(String code);
	
	List<String> allAreaCode();
}