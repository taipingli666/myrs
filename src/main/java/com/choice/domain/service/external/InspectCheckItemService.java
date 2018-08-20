package com.choice.domain.service.external;

import java.util.List;

import com.choice.domain.entity.external.InspectCheckItem;
import com.choice.domain.entity.external.ItemListParam;
import com.choice.sign.util.Page;

/**
* @author litp
* 2018年1月25日 上午10:16:41
* 保存检查/化验项
*/
public interface InspectCheckItemService {

	int insertInspectCheckItem(List<InspectCheckItem> inspectCheckItems);
	
	List<InspectCheckItem> getCheckItemByInfoId(String uuid);
	
}
