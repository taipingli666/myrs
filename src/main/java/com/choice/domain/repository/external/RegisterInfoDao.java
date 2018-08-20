package com.choice.domain.repository.external;

import com.choice.domain.entity.external.RegisterInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author lingli
 */
public interface RegisterInfoDao {

	/**
	 * 更具主键查询
	 * @param id
	 * @return
	 */
	RegisterInfo selectByPrimaryKey(@Param("id") String id);

	/**
	 * 更具主键选择更新
	 * @param registerInfo
	 * @return
	 */
	int updateByPrimaryKeySelective(RegisterInfo registerInfo);

	/**
	 * 插入
	 * @param registerInfo
	 * @return
	 */
	int insertSelective(RegisterInfo registerInfo);

	/**
	 * 获取挂号分页列表
	 * @param registerInfo
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<RegisterInfo> getRegisterInfoList(@Param("registerInfo") RegisterInfo registerInfo,
										   @Param("startDate") Date startDate,@Param("endDate") Date endDate,
										   @Param("refType") String refType);

	/**
	 * 获取使用中的排班预约
	 * @param subString
	 * @return
	 */
	List<RegisterInfo> getUsingSchedule(String[] subString);

	/**
	 * 取消排班状态
	 * @param subString
	 * @return
	 */
	int closeUsingSchedule(String[] subString);


}
