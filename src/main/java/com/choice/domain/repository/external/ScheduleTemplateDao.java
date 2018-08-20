package com.choice.domain.repository.external;

import com.choice.domain.entity.external.ScheduleTemplate;

import java.util.List;

/**
 * 排班模板
 * @author lingli
 */
public interface ScheduleTemplateDao {

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
    int insert(ScheduleTemplate record);

    /**
     * 选择插入
     * @param record
     * @return
     */
    int insertSelective(ScheduleTemplate record);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    ScheduleTemplate selectByPrimaryKey(Long id);

    /**
     * 选择更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ScheduleTemplate record);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(ScheduleTemplate record);

    /**
     * 查询设置列表
     * @param record
     * @return
     */
    List<ScheduleTemplate> selectSetList(ScheduleTemplate record);

    /**
     * 根据入参查询模板
     * @param record
     * @return
     */
    List<ScheduleTemplate> selectByParameter(ScheduleTemplate record);
}