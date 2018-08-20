
package com.choice.domain.repository.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.choice.domain.entity.common.CommonPackage;

/**
 * 
 * @ClassName: CommonPackageDao 
 * @Description: TODO
 * @author: shenhf
 * @date: 2017年11月9日 下午2:13:40
 */
public interface CommonPackageDao {
	
	/**
	 * 根据名称或类型id获取记录总数
	 * @Title: getTotalNumber 
	 * @Description: TODO
	 * @param name
	 * @param id
	 * @return int
	 * @author shenhf 
	 * @created 2017年11月9日 下午2:12:25
	 */
	Integer getTotalNumber(@Param("className")String className);
	
	/**
	 * 根据条件返回数据
	 * @Title: selectByQuery 
	 * @Description: TODO
	 * @param name
	 * @param StartIndex
	 * @param endIndex
	 * @return List<CommonPackage>
	 * @author shenhf 
	 * @created 2017年11月9日 下午4:10:17
	 */
	List<CommonPackage> selectByQuery(@Param("className")String className,@Param("StartIndex")int StartIndex,@Param("endIndex")int endIndex);
	
//	CommonPackage selectByPrimaryKey(@Param("packageId") long packageId);
	
	/**
	 * 根据条件返回数据
	 * @Title: selectByAll 
	 * @Description: TODO
	 * @return List<CommonPackage>
	 * @author shenhf 
	 * @created 2017年11月9日 下午4:10:17
	 */
	List<CommonPackage> selectByAll();
	
	
	
	/**
	 * 根据id删除记录
	 * @Title: deleteCommonPackage 
	 * @Description: TODO
	 * @param id void
	 * @author shenhf 
	 * @created 2017年11月9日 下午2:14:23
	 */
	void deleteCommonPackage(String[] ids);
	
	/**
	 * 根据id新增记录
	 * @Title: insertCommonPackage 
	 * @Description: TODO
	 * @param value void
	 * @author shenhf 
	 * @created 2017年11月9日 下午2:14:33
	 */
	void insertCommonPackage(CommonPackage value);
	
	/**
	 * 根据主键获取记录
	 * @Title: getInfo 
	 * @Description: TODO
	 * @param id
	 * @return CommonPackage
	 * @author shenhf 
	 * @created 2017年11月9日 下午2:14:41
	 */
	CommonPackage selectByPrimaryKey(@Param("packageId")long packageId);
	
	/**
	 * 根据传入值更新记录
	 * @Title: updateCommonPackage 
	 * @Description: TODO
	 * @param value void
	 * @author shenhf 
	 * @created 2017年11月9日 下午2:14:54
	 */
	void updateCommonPackage(CommonPackage value);
}
