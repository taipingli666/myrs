package com.choice.domain.repository.common;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.choice.domain.entity.common.CommonIcd10;

public interface CommonIcd10Dao {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonIcd10 record);

    int insertSelective(CommonIcd10 record);

    CommonIcd10 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonIcd10 record);

    int updateByPrimaryKey(CommonIcd10 record);

    List<CommonIcd10> selectBySelect(@Param("searchValue")String searchValue, 
    		@Param("pageIndex")int pageIndex, @Param("pageSize")int pageSize);
    
    int countIcd10(@Param("searchValue")String searchValue);
}