package com.choice.domain.service.external;

import java.util.Date;
import java.util.List;

import com.choice.domain.entity.external.ScheduleInfo;
import com.choice.sign.util.Page;

/**
 * 排班信息
 * Created by administer on 2018-02-06.
 * @author lingli
 */
public interface ScheduleInfoService {

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
     * 分页查询排班信息列表
     * @param page
     * @param size
     * @param scheduleInfo
     * @param startDate
     * @param endDate
     * @return
     */
    Page<ScheduleInfo> getScheduleInfoList(Integer page, Integer size, ScheduleInfo scheduleInfo, Date startDate,Date endDate);
    
    /**
     * 获取排班下的所有号源
     * @param scheduleInfo
     * @return
     */
    List getSchedulingSource(ScheduleInfo scheduleInfo);

    /**
     * 保存排班
     * @param scheduleInfo
     * @return
     */
    Long saveScheduleInfo(ScheduleInfo scheduleInfo);

    /**
     * 根据排班信息查询
     * @param scheduleInfo
     * @param startDate
     * @param endDate
     * @return
     */
    List<ScheduleInfo> selectByScheduleInfo(ScheduleInfo scheduleInfo, Date startDate, Date endDate);

    /**
     * 根据id删除
     * @param subString
     * @return
     */
    String deleteSchedule(String[] subString);
}
