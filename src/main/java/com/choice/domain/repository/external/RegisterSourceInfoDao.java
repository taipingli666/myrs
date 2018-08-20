package com.choice.domain.repository.external;


import com.choice.domain.entity.external.BussConsultationSingle;
import com.choice.domain.entity.external.RegisterSourceInfo;

/**
 * 号源
 * @author lingli
 */
public interface RegisterSourceInfoDao {

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(RegisterSourceInfo record);

    /**
     * 选择插入
     * @param record
     * @return
     */
    int insertSelective(RegisterSourceInfo record);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    RegisterSourceInfo selectByPrimaryKey(Long id);

    /**
     * 根据主键选择更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(RegisterSourceInfo record);

    /**
     * 根据主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(RegisterSourceInfo record);

    /**
     * 根据排班编号删除
     * @param scheduleCode
     * @return
     */
    int deleteByScheduleCode(Long scheduleCode);

    /**
     * 挂号成功
     * @return
     */
    int useHaoYuan(Long id);

    /**
     * 释放号源
     * @param record
     * @return
     */
    int releaseSource(RegisterSourceInfo record);
}