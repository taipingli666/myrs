package com.choice.domain.service.dictionary.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.choice.domain.entity.dictionary.CommonDictionaryValue;
import com.choice.domain.repository.dictionary.CommonDictionaryValueDao;
import com.choice.domain.service.dictionary.DictValueService;

@Service("dictValueService")
public class DictValueServiceImpl implements DictValueService {
	@Resource
	private CommonDictionaryValueDao commonDictionaryValueDao;
	
	public List<CommonDictionaryValue> selectValuesByCode(int code) {
		return commonDictionaryValueDao.getInfoByParentCode(code);
	}
	
}
