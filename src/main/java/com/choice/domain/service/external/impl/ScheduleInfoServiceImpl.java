package com.choice.domain.service.external.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.choice.domain.entity.external.RegisterInfo;
import com.choice.domain.entity.external.RegisterSourceInfo;
import com.choice.domain.repository.external.RegisterInfoDao;
import com.choice.sign.util.DateUtil;
import org.springframework.stereotype.Service;

import com.choice.domain.entity.external.ScheduleInfo;
import com.choice.domain.repository.external.RegisterSourceInfoDao;
import com.choice.domain.repository.external.ScheduleInfoDao;
import com.choice.domain.service.external.ScheduleInfoService;
import com.choice.sign.util.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 排班信息
 * Created by administer on 2018-02-06.
 * @author lingli
 */
@Service
public class ScheduleInfoServiceImpl implements ScheduleInfoService {

    @Resource
    private ScheduleInfoDao scheduleInfoDao;

    @Resource
    private RegisterInfoDao registerInfoDao;

    @Resource
    private RegisterSourceInfoDao registerSourceInfoDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(ScheduleInfo record) {
        return 0;
    }

    /**
     * 选择插入
     * @param record
     * @return
     */
    @Override
    public int insertSelective(ScheduleInfo record) {
        return scheduleInfoDao.insertSelective(record);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    @Override
    public ScheduleInfo selectByPrimaryKey(Long id) {
        return scheduleInfoDao.selectByPrimaryKey(id);
    }

    /**
     * 选择更新
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(ScheduleInfo record) {
        return scheduleInfoDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ScheduleInfo record) {
        return 0;
    }

    /**
     * 分页查询排班信息列表
     * @param page
     * @param size
     * @param scheduleInfo
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public Page<ScheduleInfo> getScheduleInfoList(Integer page, Integer size, ScheduleInfo scheduleInfo, Date startDate, Date endDate) {
        com.github.pagehelper.Page startPage = PageHelper.startPage(page, size);
        List<ScheduleInfo> list = scheduleInfoDao.getScheduleInfoList(scheduleInfo,startDate,endDate);
        Long total = startPage.getTotal();
        return new Page<ScheduleInfo>(size, page, total.intValue(), list);
    }
    
    /**
     * 获取排班下的所有号源
     * @param scheduleInfo
     * @return
     */
    @Override
    public List getSchedulingSource(ScheduleInfo scheduleInfo) {
        return scheduleInfoDao.getSchedulingSource(scheduleInfo);
    }

    /**
     * 保存排班
     * @param scheduleInfo
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Long saveScheduleInfo(ScheduleInfo scheduleInfo) {
        Long id = scheduleInfo.getId();
        try {

            int data ;
            //存入排班
            if(id != null){
                data = scheduleInfoDao.updateByPrimaryKeySelective(scheduleInfo);
                if(data == 0){
                    throw new RuntimeException("更新失敗");
                }
                //清除号源
                data = registerSourceInfoDao.deleteByScheduleCode(scheduleInfo.getId());
                if(data == 0){
                    throw new RuntimeException("删除号源失败");
                }
            }else{
                data = scheduleInfoDao.insertSelective(scheduleInfo);
                if(data == 0){
                    throw new RuntimeException("插入失败");
                }
            }


            List<RegisterSourceInfo> registerSourceInfoList = buildRegisterSourceInfo(scheduleInfo);
            //存入号源

            for (RegisterSourceInfo registerSourceInfo : registerSourceInfoList){
                data =registerSourceInfoDao.insertSelective(registerSourceInfo);
                if(data == 0){
                    throw new RuntimeException("号源插入失败");
                }
            }

            scheduleInfo.setRegisterQuantity(registerSourceInfoList.size());
            scheduleInfo.setRemainderQuantity(registerSourceInfoList.size());
            data = scheduleInfoDao.updateByPrimaryKeySelective(scheduleInfo);
            if(data == 0){
                throw new RuntimeException("号源数量更新失败");
            }

            return scheduleInfo.getId();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 根据排班信息查询
     * @param scheduleInfo
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public List<ScheduleInfo> selectByScheduleInfo(ScheduleInfo scheduleInfo, Date startDate, Date endDate) {
        return scheduleInfoDao.getScheduleInfoList(scheduleInfo,startDate,endDate);
    }

    /**
     * 根据id删除
     * @param subString
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public String deleteSchedule(String[] subString) {
        String message = "删除成功";
        //判断排班是否有挂号
        List<RegisterInfo> registerInfoList = registerInfoDao.getUsingSchedule(subString);
        if(registerInfoList.size()>0){
            if( 0 == registerInfoDao.closeUsingSchedule(subString)){
                throw new RuntimeException();
            }
            message = "删除成功，删除的排班有预约数据，请到预约列表查看";
        }
        int i = scheduleInfoDao.deleteSchedule(subString);
        if(i == 0){
            throw new RuntimeException();
        }
        return message;
    }

    /**
     * 计算号源数据信息
     * @param scheduleInfo
     * @return
     */
    private List<RegisterSourceInfo> buildRegisterSourceInfo(ScheduleInfo scheduleInfo){

        try{
            Integer registerQuantity = scheduleInfo.getRegisterQuantity();
            Integer timeMin;
            //计算坐诊时间
            Long min = DateUtil.dateDiff(scheduleInfo.getWorkTimeStart(),scheduleInfo.getWorkTimeEnd(),
                    "HH:mm","m");
            if(null == registerQuantity){
                //没有输入号源数量通过固定时间计算数量
                //计算号源数量
                timeMin = 5;
                registerQuantity = Integer.parseInt(min.toString()) /timeMin;
            }else{
                //计算就诊时间
                timeMin = Integer.parseInt(min.toString())/registerQuantity;
            }
            //起始时间
            String start = DateUtil.dateToString(scheduleInfo.getWorkDate(),"yyyy-MM-dd") + " " + scheduleInfo.getWorkTimeStart() + ":00";
            Calendar lt = Calendar.getInstance();
            lt.setTime(DateUtil.stringToDate(start,"yyyy-MM-dd HH:mm:ss"));

            List<RegisterSourceInfo>  registerSourceInforList = new ArrayList<>();
            for(int i = 1 ; i <= registerQuantity ; i++){

                RegisterSourceInfo registerSourceInfo = new RegisterSourceInfo();
                registerSourceInfo.setScheduleCode(scheduleInfo.getId());
                registerSourceInfo.setSequenceNumber(i);
                registerSourceInfo.setVisitStart(DateUtil.dateToString(lt.getTime(),"HH:mm"));
                lt.add(Calendar.MINUTE, timeMin);
                registerSourceInfo.setVisitEnd(DateUtil.dateToString(lt.getTime(),"HH:mm"));
                registerSourceInfo.setSourceStatus("0");

                registerSourceInforList.add(registerSourceInfo);
            }
            return registerSourceInforList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
