package com.choice.domain.repository.common;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.choice.domain.entity.common.CommonPopulation;

public interface CommonPopulationDao {
    int deleteByPrimaryKey(Integer pId);

    int insert(CommonPopulation record);

    int insertSelective(CommonPopulation record);

    CommonPopulation selectByPrimaryKey(Integer pId);

    int updateByPrimaryKeySelective(CommonPopulation record);

    int updateByPrimaryKey(CommonPopulation record);
    
    void deleteCommonPopulations(String[] substring);
    
    int getTotalNumber(@Param("contents")String contents,@Param("parentCode")String parentCode);
    
    //查询区域人口数
    int pnumByAreaCode(String areaCode);
	
   	List<CommonPopulation> getListByPage(@Param("areaCode")String areaCode,@Param("searchValue")String searchValue);
   	
   	/**
   	 * 根据地区编码和年份查找是否已经有该地区在此年份的记录
   	 */
   int selectBySearchValue(CommonPopulation commonPopulation);
}