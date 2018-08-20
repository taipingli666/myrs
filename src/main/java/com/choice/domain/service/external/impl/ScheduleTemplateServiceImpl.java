package com.choice.domain.service.external.impl;

import com.choice.domain.entity.common.CommonHospital;
import com.choice.domain.entity.dictionary.Department;
import com.choice.domain.entity.external.ScheduleInfo;
import com.choice.domain.entity.external.ScheduleTemplate;
import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.repository.external.ScheduleInfoDao;
import com.choice.domain.repository.external.ScheduleTemplateDao;
import com.choice.domain.service.common.CommonHospitalService;
import com.choice.domain.service.dictionary.DepartmentService;
import com.choice.domain.service.external.ScheduleInfoService;
import com.choice.domain.service.external.ScheduleTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 排班模板
 * @author lingli
 */
@Service
public class ScheduleTemplateServiceImpl implements ScheduleTemplateService {

    @Autowired
    private ScheduleTemplateDao scheduleTemplateDao;

    @Resource
    private ScheduleInfoService scheduleInfoService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private CommonHospitalService commonHospitalService;

    @Resource
    private ScheduleInfoDao scheduleInfoDao;

    /**
     * 查询设置列表
     * @param record
     * @return
     */
    @Override
    public List<ScheduleTemplate> selectSetList(ScheduleTemplate record) {
        if("".equals(record.getDeptCode())){
            record.setDeptCode(null);
        }
        return scheduleTemplateDao.selectSetList(record);
    }

    /**
     * 列表插入
     * @param scheduleTemplateList
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public int insertList(List<ScheduleTemplate> scheduleTemplateList, ChannelUser user) {
        int result = 0;
        //医院名称
        CommonHospital commonHospital = commonHospitalService.getById(
                Integer.valueOf(scheduleTemplateList.get(0).getHosCode()));
        for(ScheduleTemplate scheduleTemplate:scheduleTemplateList){
            scheduleTemplate.setChangeOper(user.getUserId().toString());
            scheduleTemplate.setChangeTime(new Date());
            scheduleTemplate.setHosName(commonHospital.getHosName());
            if(scheduleTemplate.getId() == null){
                scheduleTemplate.setChangeOper(user.getUserId().toString());
                scheduleTemplate.setCreateTime(new Date());
                result = scheduleTemplateDao.insertSelective(scheduleTemplate);
                if(result == 0){
                    throw new RuntimeException("插入出错");
                }
            }else{
                result = scheduleTemplateDao.updateByPrimaryKeySelective(scheduleTemplate);
                if(result == 0){
                    throw new RuntimeException("更新出错");
                }
            }
        }
        return result;
    }

    /**
     * 模板构建排班
     * @param date
     * @return
     */
    @Override
    public boolean buildScheduleInfo(Date date,String hosCode) {
        try{
            //判断星期几
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w <= 0){
                w = 7;
            }
            //查询模板
            ScheduleTemplate record = new ScheduleTemplate();
            record.setWeekDay(String.valueOf(w));
            record.setHosCode(hosCode);
            List<ScheduleTemplate> scheduleTemplateList = scheduleTemplateDao.selectByParameter(record);
            //排班数据设置值
            for (ScheduleTemplate scheduleTemplate : scheduleTemplateList){
                //判断数据是否完整
                if(scheduleTemplate.getWorkTimeStart() == null || scheduleTemplate.getWorkTimeEnd() == null ||
                        scheduleTemplate.getWorkTimeStart().length()<5 || scheduleTemplate.getWorkTimeEnd().length()<5){
                    continue;
                }

                ScheduleInfo scheduleInfo = new ScheduleInfo();
                scheduleInfo.setScheduleType(scheduleTemplate.getScheduleType());
                scheduleInfo.setHosCode(scheduleTemplate.getHosCode());
                scheduleInfo.setHosName(scheduleTemplate.getHosName());
                scheduleInfo.setDeptCode(scheduleTemplate.getDeptCode());
                scheduleInfo.setDeptName(scheduleTemplate.getDeptName());
                scheduleInfo.setDoctorCode(scheduleTemplate.getDoctorCode());
                scheduleInfo.setDoctorName(scheduleTemplate.getDoctorName());
                scheduleInfo.setWorkDate(date);
                scheduleInfo.setWorkPeriod(scheduleTemplate.getWorkPeriod());
                //判断是否已经存在相同时段排班
                List<ScheduleInfo> scheduleInfoTemp = scheduleInfoDao.getList(scheduleInfo);
                if(scheduleInfoTemp.size() >0){
                    continue;
                }

                scheduleInfo.setWorkTimeStart(scheduleTemplate.getWorkTimeStart());
                scheduleInfo.setWorkTimeEnd(scheduleTemplate.getWorkTimeEnd());
                scheduleInfo.setRegisterType(scheduleTemplate.getRegisterType());
                scheduleInfo.setRegisterCost(scheduleTemplate.getRegisterCost());
                scheduleInfo.setRegisterQuantity(scheduleTemplate.getRegisterQuantity());
                scheduleInfo.setDeleteFlag("0");


                //排班保存
                scheduleInfoService.saveScheduleInfo(scheduleInfo);
            }

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
