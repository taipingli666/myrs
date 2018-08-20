package com.choice.domain.service.common;
import java.util.List;

import com.choice.domain.entity.common.CommonScheduling;

/**
 * 排版接口
 */
public interface CommonSchedulingService {

    int deleteByPrimaryKey(Long liushuihao);

    int insert(CommonScheduling record);

    int insertSelective(CommonScheduling record);

    CommonScheduling selectByPrimaryKey(Long liushuihao);

    int updateByPrimaryKeySelective(CommonScheduling record);

    int updateByPrimaryKey(CommonScheduling record);

    List getPaiBan(CommonScheduling scheduling);

    List getPaiBanByAfterDay(CommonScheduling scheduling);

    /**
     * 获得该科室下所有医生的对象
     * @param scheduling
     * @return
     */
    List doctorList(CommonScheduling scheduling);
    /**
     * 根据排班id 和机构编号封装对象
     */
    CommonScheduling getPaiBanById(CommonScheduling commonScheduling);
}