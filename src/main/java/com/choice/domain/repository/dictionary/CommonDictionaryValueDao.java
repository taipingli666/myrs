package com.choice.domain.repository.dictionary;

import java.util.List;

import com.choice.domain.entity.dictionary.CommonDictionaryValue;

public interface CommonDictionaryValueDao {
	
	int countByPcodeAndcode(CommonDictionaryValue commonDictionaryValue);
	
	List<CommonDictionaryValue> getInfoByParentCode(int dictId);
	
	void insertValue(CommonDictionaryValue commonDictionaryValue);
	
	void deleteByParentCode(int dictId);
	
	void deleteByPrimaryKey(int id);
	
	void  updateValue(CommonDictionaryValue commonDictionaryValue);
}
