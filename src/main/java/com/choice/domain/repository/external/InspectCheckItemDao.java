package com.choice.domain.repository.external;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.choice.domain.entity.external.InspectCheckItem;
import com.choice.domain.entity.external.InspectInfo;
import com.choice.domain.entity.external.ItemListParam;

/**
* @author litp
* 2018年1月25日 上午9:33:26
* 检查项
*/
public interface InspectCheckItemDao {

	int insertInspectCheckItem(@Param("inspectCheckItems")List<InspectCheckItem> inspectCheckItems);
	
	//预约化验/检查列表
	List<Map> itemList(@Param("itemListParam")ItemListParam itemListParam,
			@Param("startIndex")int startIndex, @Param("pageSize")int pageSize);
	
	//列表总数 
	int totalCount(@Param("itemListParam")ItemListParam itemListParam);
	
	//根据预约单编号获取检查项列表
	List<InspectCheckItem> getCheckItemByInfoId(String uuid);
}
