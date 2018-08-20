package com.choice.domain.service.common;

import java.util.List;

import com.choice.domain.entity.common.CommonIcd10;

/**
 * Created with IntelliJ IDEA.
 * User: 王涛 wt2510@163.com
 * 创建时间: 2017-6-11    17:31
 * 类描述：
 */
public interface CommonIcd10Service {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonIcd10 record);

    int insertSelective(CommonIcd10 record);

    CommonIcd10 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonIcd10 record);

    int updateByPrimaryKey(CommonIcd10 record);

    List<CommonIcd10> selectBySelect(String searchValue, int pageNum, int pageSize);
    
    int countIcd10(String searchValue);
}