package com.choice.domain.entity.external;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *排班模板
 * @author lingli
 */
public class ScheduleTemplate implements Serializable {

    private Long id;

    /**
     * 排班类型
     */
    private String scheduleType;

    /**
     * 医院编码
     */
    private String hosCode;

    /**
     * 医院名称
     */
    private String hosName;

    /**
     * 科室编码
     */
    private String deptCode;

    /**
     * 科室名称
     */
    private String deptName;

    /**
     * 医生编码
     */
    private String doctorCode;

    /**
     * 医生名称
     */
    private String doctorName;

    /**
     * 星期
     */
    private String weekDay;

    /**
     * 坐诊时段 am pm
     */
    private String workPeriod;

    /**
     * 坐诊开始时间
     */
    private String workTimeStart;

    /**
     * 坐诊结束时间
     */
    private String workTimeEnd;

    /**
     * 挂号类型
     */
    private String registerType;

    /**
     * 挂号金额
     */
    private BigDecimal registerCost;

    /**
     * 号源数量
     */
    private Integer registerQuantity;

    /**
     * 创建人
     */
    private String createOper;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String changeOper;

    /**
     * 修改时间
     */
    private Date changeTime;

    /**
     * 删除标志 0 未删除，1删除
     */
    private String deleteFlag;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getHosCode() {
        return hosCode;
    }

    public void setHosCode(String hosCode) {
        this.hosCode = hosCode;
    }

    public String getHosName() {
        return hosName;
    }

    public void setHosName(String hosName) {
        this.hosName = hosName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(String workPeriod) {
        this.workPeriod = workPeriod;
    }

    public String getWorkTimeStart() {
        return workTimeStart;
    }

    public void setWorkTimeStart(String workTimeStart) {
        this.workTimeStart = workTimeStart;
    }

    public String getWorkTimeEnd() {
        return workTimeEnd;
    }

    public void setWorkTimeEnd(String workTimeEnd) {
        this.workTimeEnd = workTimeEnd;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public BigDecimal getRegisterCost() {
        return registerCost;
    }

    public void setRegisterCost(BigDecimal registerCost) {
        this.registerCost = registerCost;
    }

    public Integer getRegisterQuantity() {
        return registerQuantity;
    }

    public void setRegisterQuantity(Integer registerQuantity) {
        this.registerQuantity = registerQuantity;
    }

    public String getCreateOper() {
        return createOper;
    }

    public void setCreateOper(String createOper) {
        this.createOper = createOper;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getChangeOper() {
        return changeOper;
    }

    public void setChangeOper(String changeOper) {
        this.changeOper = changeOper;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}