package com.choice.domain.service.dictionary;

import java.util.List;

import com.choice.domain.entity.dictionary.CommonDictionaryValue;

public interface DictValueService {
	List<CommonDictionaryValue> selectValuesByCode(int code);
}
