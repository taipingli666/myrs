package com.choice.domain.service.external;

import com.choice.domain.entity.external.ScheduleTemplate;
import com.choice.domain.entity.user.ChannelUser;

import java.util.Date;
import java.util.List;

/**
 * 排班模板
 * @@author lingli
 */
public interface ScheduleTemplateService {

    /**
     * 查询设置列表
     * @param record
     * @return
     */
    List<ScheduleTemplate> selectSetList(ScheduleTemplate record);

    /**
     * 列表插入
     * @param scheduleTemplateList
     * @param user
     * @return
     */
    int insertList(List<ScheduleTemplate> scheduleTemplateList,ChannelUser user);

    /**
     * 模板构建排班
     * @param date
     * @param hosCode
     * @return
     */
    boolean buildScheduleInfo(Date date,String hosCode);
}
