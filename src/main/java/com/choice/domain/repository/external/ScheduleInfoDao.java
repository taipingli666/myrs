package com.choice.domain.repository.external;

import com.choice.domain.entity.external.ScheduleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 排班信息
 * @author lingli
 */
public interface ScheduleInfoDao {

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
    int insert(ScheduleInfo record);

    /**
     * 选择插入
     * @param record
     * @return
     */
    int insertSelective(ScheduleInfo record);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    ScheduleInfo selectByPrimaryKey(Long id);

    /**
     * 根据主键选择跟新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ScheduleInfo record);

    /**
     * 根据主键跟新
     * @param record
     * @return
     */
    int updateByPrimaryKey(ScheduleInfo record);

    /**
     * 分页查询
     * @param scheduleInfo
     * @param startDate
     * @param endDate
     * @return
     */
    List<ScheduleInfo> getScheduleInfoList(@Param("scheduleInfo") ScheduleInfo scheduleInfo, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 获取排班下的所有号源
     * @param scheduleInfo
     * @return
     */
    List getSchedulingSource(ScheduleInfo scheduleInfo);
    
    /**
     * 挂号成功,剩余号源数要减1
     * @return
     */
    int useHaoYuan(Long id);

    /**
     * 释放号源
     * @param id
     * @return
     */
    int releaseSource(Long id);

    /**
     * 根据id删除
     * @param subString
     * @return
     */
    int deleteSchedule(String[] subString);

    /**
     * 获取列表
     * @param scheduleInfo
     * @return
     */
    List<ScheduleInfo> getList(ScheduleInfo scheduleInfo);
}