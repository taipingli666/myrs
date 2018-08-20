package com.choice.domain.service.dictionary;

import com.choice.domain.entity.dictionary.CommonDictionary;
import com.choice.sign.util.Page;

public interface DictMangerService {
	
	Page getPage(int currentPage,String contents);
	
	void deleteDicts(String ids);
	
	void insertDict(CommonDictionary dictionary,String codes);
	
	void updateDict(CommonDictionary dictionary,String codes);
	
	CommonDictionary getInfo(int id);
	
	int checkCode(int code);
}
