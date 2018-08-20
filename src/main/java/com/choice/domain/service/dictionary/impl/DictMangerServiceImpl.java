package com.choice.domain.service.dictionary.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choice.domain.entity.dictionary.CommonDictionaryValue;
import com.choice.domain.entity.dictionary.CommonDictionary;
import com.choice.domain.repository.dictionary.CommonDictionaryValueDao;
import com.choice.domain.repository.dictionary.DictMangerDao;
import com.choice.domain.service.dictionary.DictMangerService;
import com.choice.sign.util.Page;

@Service("dictMangerService")
public class DictMangerServiceImpl implements DictMangerService {
	
	@Resource
	private DictMangerDao dictMangerDao;
	@Resource
	private CommonDictionaryValueDao commonDictionaryValueDao;
	
	public Page<CommonDictionary> getPage(int currentPage,String contents) {
		int pageSize = 10;
		Integer total = dictMangerDao.getTotalNumber(contents);
		int pageIndex = Page.calPageIndex(currentPage, pageSize);
		List<CommonDictionary> list = dictMangerDao.getPageInfo(contents, pageIndex, pageIndex + pageSize);
		return new Page<CommonDictionary>(pageSize, currentPage, total, list);
	}
	
	@Transactional
	public void deleteDicts(String ids) {
		String[] idArr = ids.split(",");
		List<CommonDictionary> list = dictMangerDao.getListByPrimaryKey(idArr);
		for(CommonDictionary dictionary:list){
			commonDictionaryValueDao.deleteByParentCode(Integer.valueOf(dictionary.getCode()));
		}
		dictMangerDao.deleteDicts(idArr);
	}
	
	@Transactional
	public void insertDict(CommonDictionary dictionary,String code) {
		CommonDictionaryValue commonDictionaryValue = new CommonDictionaryValue();
		commonDictionaryValue.setParentCode(Integer.valueOf(dictionary.getCode()));
		String[] values = dictionary.getValueString().split("\\|");
		String[] codes = code.split("\\|");
		if(codes[0] != ""){			//防止传入空。split后结果为“ ”，而进入循环
			for(int i=0;i<codes.length;i++){
				commonDictionaryValue.setCode(Integer.valueOf(codes[i]));
				commonDictionaryValue.setWord(values[i]);
				commonDictionaryValueDao.insertValue(commonDictionaryValue);
			}
		}
		dictMangerDao.insertDict(dictionary);
	}
	
	@Transactional
	public void updateDict(CommonDictionary dictionary,String code) {
		//修改到value表
		CommonDictionaryValue commonDictionaryValue = new CommonDictionaryValue();
		commonDictionaryValue.setParentCode(Integer.valueOf(dictionary.getCode()));
		String[] values = dictionary.getValueString().split("\\|");
		String[] codes = code.split("\\|");
		if(codes[0] != ""){
			for(int i=0;i<values.length;i++){
				commonDictionaryValue.setWord(values[i]);
				commonDictionaryValue.setCode(Integer.valueOf(codes[i]));
				//如果存在则修改
				if(commonDictionaryValueDao.countByPcodeAndcode(commonDictionaryValue) != 0){
					commonDictionaryValueDao.updateValue(commonDictionaryValue);
				}else{
					commonDictionaryValueDao.insertValue(commonDictionaryValue);
				}
			}
		}
		dictMangerDao.updateDict(dictionary);
	}

	public CommonDictionary getInfo(int id) {
		return dictMangerDao.getInfo(id);
	}

	public int checkCode(int code) {
		return dictMangerDao.checkCode(code);
	}
	
	
}
