/**
 * 
 */
/**
 * @author Paul.Pan
 *
 */
package com.choice.domain.service.common;

import javax.servlet.http.HttpServletRequest;

import com.choice.domain.entity.common.CommonPopulation;
import com.choice.sign.util.Page;

public interface CommonPopulationService{
	
	Page<CommonPopulation> getPage(int page,int size,String areaCode,String searchValue);
	// 删除区域人口信息
	void deleteCommonPopulation(int pId);
	// 获取区域人口信息
	CommonPopulation getInfo(int pId);
	// 批量删除区域人口信息
	void deleteCommonPopulations(String[] substring);
	
	//增加、删除行政区域信息
	void operated(HttpServletRequest request,CommonPopulation commonPopulation);
	
	//查询区域人口数
    int pnumByAreaCode(String areaCode);
	
	/**
   	 * 根据地区编码和年份查找是否已经有该地区在此年份的记录
   	 */
	int selectBySearchValue(CommonPopulation commonPopulation);
}