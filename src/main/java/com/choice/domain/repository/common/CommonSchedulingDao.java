package com.choice.domain.repository.common;


import java.util.List;

import com.choice.domain.entity.common.CommonScheduling;

/**
 * 排班接口
 */
public interface CommonSchedulingDao {
    int deleteByPrimaryKey(Long liushuihao);

    int insert(CommonScheduling record);

    int insertSelective(CommonScheduling record);

    CommonScheduling selectByPrimaryKey(Long liushuihao);

    int updateByPrimaryKeySelective(CommonScheduling record);

    int updateByPrimaryKey(CommonScheduling record);

    int countScheduling(CommonScheduling record);

    List getPaiBan(CommonScheduling scheduling);
    //得到几天后的排版
    List getPaiBanByAfterDay(CommonScheduling scheduling);

    /**
     * 根据排班id 和 机构编号封装对象
     * @return
     */
    CommonScheduling getPaiBanById(CommonScheduling commonSchedulin);
}