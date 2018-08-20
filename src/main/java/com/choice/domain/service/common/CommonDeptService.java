package com.choice.domain.service.common;


import java.util.List;

import com.choice.domain.entity.common.CommonDept;

/**
 * 科室接口API
 */
public interface CommonDeptService {
    int deleteByPrimaryKey(Long liushuihao);

    int insert(CommonDept record);

    int insertSelective(CommonDept record);

    CommonDept selectByPrimaryKey(Long liushuihao);

    int updateByPrimaryKeySelective(CommonDept record);

    int updateByPrimaryKey(CommonDept record);
    /**
     * 批量更新
     * @param list
     * @return
     */
    int updateKeshilb(List<CommonDept> list);

    List<CommonDept> getConsultationDeptByJiGouBH(CommonDept commonDept);
}