/**
 * 
 */
/**
 * @author Paul.Pan
 *
 */
package com.choice.domain.repository.common;

import java.util.*;
import org.apache.ibatis.annotations.Param;

import com.choice.domain.entity.common.CommonArea;

public interface CommonAreaDao{
	int deleteByPrimaryKey(Integer areaId);

    int insert(CommonArea record);

    int insertSelective(CommonArea record);

    CommonArea selectByPrimaryKey(Integer areaId);

    int updateByPrimaryKeySelective(CommonArea record);

    int updateByPrimaryKey(CommonArea record);
    
    int getTotalNumber(@Param("contents")String contents,@Param("parentCode")String parentCode);
	
	List<CommonArea> getListByPage(@Param("areaName")String areaName,@Param("parentCode")String parentCode);
	
	List<Map<String,Object>> getCommonAreaAll(@Param("parentCode")String parentCode);
	
	void deleteCommonArea(@Param("areaId") int areaId);
	
	void deleteCommonAreas(String[] substring);
	  
	List<CommonArea> getCommonAreaByParentCode(@Param("parentCode")String parentCode);
	
	List<Map<String,Object>> getCommonAreaByAreaCode(@Param("areaCode")String areaCode);
	
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