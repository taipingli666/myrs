package com.choice.domain.service.external.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choice.domain.entity.external.InspectCheckItem;
import com.choice.domain.entity.external.ItemListParam;
import com.choice.domain.repository.external.InspectCheckItemDao;
import com.choice.domain.service.external.InspectCheckItemService;
import com.choice.sign.util.Page;

/**
* @author litp
* 2018年1月25日 上午11:01:12
*
*/
@Service
public class InspectCheckItemServiceImpl implements InspectCheckItemService {

	@Autowired
	private InspectCheckItemDao inspectCheckItemDao;
	
	@Override
	public int insertInspectCheckItem(List<InspectCheckItem> inspectCheckItems) {
		// TODO Auto-generated method stub
		return inspectCheckItemDao.insertInspectCheckItem(inspectCheckItems);
	}

	@Override
	public List<InspectCheckItem> getCheckItemByInfoId(String uuid) {
		// TODO Auto-generated method stub
		return inspectCheckItemDao.getCheckItemByInfoId(uuid);
	}


}
