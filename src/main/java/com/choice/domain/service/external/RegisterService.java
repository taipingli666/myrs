package com.choice.domain.service.external;

import com.choice.domain.entity.common.CommonDoctorTeam;
import com.choice.domain.entity.external.RegisterInfo;
import com.choice.domain.entity.external.ScheduleInfo;
import com.choice.sign.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 预约挂号
 * Created by administer on 2018-01-11.
 * @author lingli
 */
public interface RegisterService {

    /**
     * 获取科室信息
     * @param hosCode 机构编号
     * @return json字符串
     */
    String getDeptList(String hosCode);

    /**
     * 获取排班数据
     * @param hosCode 机构编号
     * @param deptCode 科室编号
     * @return json字符串
     */
    String getScheduleList(String hosCode,String deptCode);

    /**
     * 获取号源
     * @param registerInfo
     * @return
     */
    String getRegisterSource(RegisterInfo registerInfo);

    /**
     * 预约挂号
     * @param registerInfo
     * @return
     */
    String registerReservation(RegisterInfo registerInfo);

    /**
     * 取消预约
     * @param registerInfo
     * @return
     */
    String cancelRegisterReservation(RegisterInfo registerInfo);

    /**
     * 预约状态查询
     * @param registerInfo
     * @return
     */
    String selectRegisterReservation(RegisterInfo registerInfo);

    /**
     * 保存挂号信息
     * @param registerInfo
     * @return
     */
    String saveRegisterInfo(RegisterInfo registerInfo);

    /**
     * 获取挂号分页列表
     * @param page
     * @param size
     * @param registerInfo
     * @param startDate
     * @param endDate
     * @return
     */
    Page<RegisterInfo> getRegisterInfoList(Integer page, Integer size, RegisterInfo registerInfo, Date startDate, Date endDate, String refType);

    /**
     * 更具主键选择更新
     * @param registerInfo
     * @return
     */
    int updateByPrimaryKeySelective(RegisterInfo registerInfo);

    /**
     * 更具主键查询
     * @param id
     * @return
     */
    RegisterInfo selectByPrimaryKey(String id);
}
