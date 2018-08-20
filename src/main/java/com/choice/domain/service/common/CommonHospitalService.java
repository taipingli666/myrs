package com.choice.domain.service.common;

import java.util.List;

import com.choice.domain.entity.common.CommonHospital;
import com.choice.sign.util.Page;

public interface CommonHospitalService {
	//通过医院对象获得分页的医院信息
	Page<CommonHospital> getPage(Integer page, Integer size, CommonHospital hospital);
	//进行新增或者修改
	void operated(CommonHospital hospital);
	//可批量打删除标志
	void deleteHospital(String[] substring);
	//通过医院id获得医院信息
	CommonHospital getById(Integer id);
	//获得所有医院list信息
	List<CommonHospital> getAllHospital(CommonHospital hospital);
	
	//获取医疗机构，给用户模块
	List<CommonHospital> getHospitalForUser(String areaCode);
	
	//团队管理筛选
	List<Integer> getHosIdListForSelect(String areaCode);
	
	List<CommonHospital> getAllHospital();
	
	//预约时选择医院列表，排除当前登录人医院id
	List<CommonHospital> selectHospitalList(String hosId);

	//根据areacode查询医院iD
	List<Integer> hosIdByAreaCode(String areaCode);
	
	//根据hosCode获取医院
	CommonHospital selectByHosCode(String hosCode);
	/**
	 * 获取level不为null的医院机构
	 * @return
	 */
	List<CommonHospital> getAllHospitalWithoutNull();
}
