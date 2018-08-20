/**
 * 
 */
/**
 * @author Paul.Pan
 *
 */
package com.choice.domain.repository.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.choice.domain.entity.common.CommonHospital;

public interface CommonHospitalDao{
	
	int deleteByPrimaryKey(Integer userId);

	int insert(CommonHospital record);

	int insertSelective(CommonHospital record);

	CommonHospital selectByPrimaryKey(Integer hosId);

	int updateByPrimaryKeySelective(CommonHospital record);

	int updateByPrimaryKey(CommonHospital record);

	List<CommonHospital> getListByPage(CommonHospital hospital);

	void deleteHospital(String[] substring);
	
	//获取医疗机构，给用户模块
	List<CommonHospital> getHospitalForUser(String areaCode);
	
	//团队管理筛选
	List<Integer> getHosIdListForSelect(String areaCode);
	
	List<CommonHospital> getAllHospital();
	
	//预约时选择医院列表，排除当前登录人医院id
	List<CommonHospital> selectHospitalList(@Param("hosId")String hosId);
	
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