package com.choice.domain.repository.common;

import java.util.List;

import com.choice.domain.entity.common.CommonDept;

/**
 * 科室接口API
 */
public interface CommonDeptDao {
    int deleteByPrimaryKey(Long liushuihao);

    int insert(CommonDept record);

    int insertSelective(CommonDept record);

    CommonDept selectByPrimaryKey(Long liushuihao);

    int updateByPrimaryKeySelective(CommonDept record);

    int updateByPrimaryKey(CommonDept record);

    /**
     * 获取当前科室在数据库中是否存在
     * @param record
     * @return
     */
    int countDept(CommonDept record);

    /**
     * 根据职工工号更新科室
     * @param record
     * @return
     */
    int updateByKeshibhSelective(CommonDept record);

    List<CommonDept> getListBySearch(CommonDept commonDept);


    int updateKeshilb(CommonDept record);

    List<CommonDept> getConsultationDeptByJiGouBH(CommonDept commonDept);
}