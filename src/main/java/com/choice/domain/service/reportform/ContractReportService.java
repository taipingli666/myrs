package com.choice.domain.service.reportform;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.choice.domain.entity.reportform.ContractReport;

public interface ContractReportService {
	/**
	 * 获取当前点击区域下一级的签约统计信息
	 * @param contractReport
	 * @return
	 */
	List<ContractReport> getListBySearchValue(ContractReport contractReport);
	
	//签约
	List<ContractReport> signingInfo(String hosId);
	//续约
	List<ContractReport> renewInfo(String hosId);
	//重点人群
	List<ContractReport> focusGroupsInfo(String hosId);
	//通过年份和区号查是否有报表信息
	ContractReport contractReportInfoByYear(String areaCode, String year);
	//新增数据
	int insert(ContractReport contractReport);
	//更新数据
	int update(ContractReport contractReport);
	//删除表数据
	void delete();
	//签约报表年份
	List<String> distinctYear();
}
