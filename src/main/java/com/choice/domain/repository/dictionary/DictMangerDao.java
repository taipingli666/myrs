package com.choice.domain.repository.dictionary;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.choice.domain.entity.dictionary.CommonDictionary;

public interface DictMangerDao{
	
	int getTotalNumber(@Param("contents")String contents);
	
	List<CommonDictionary> getPageInfo(@Param("contents")String contents,
			@Param("StartIndex")int StartIndex,@Param("endIndex")int endIndex);
	
	void deleteDicts(@Param("ids")String[] ids);
	
	void insertDict(CommonDictionary dictionary);
	
	CommonDictionary getInfo(int id);
	
	int checkCode(int code);
	
	void updateDict(CommonDictionary dictionary);
	
	List<CommonDictionary> getListByPrimaryKey(@Param("ids")String[] ids);
}
