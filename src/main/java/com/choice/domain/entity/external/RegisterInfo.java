package com.choice.domain.entity.external;

import java.util.Date;

/**
 * 预约信息
 * Created by administer on 2018-01-11.
 * @author lingli
 */
public class RegisterInfo {

    /**
     * id
     */
    private String id;
    /**
     * 机构编码
     */
    private String hosCode;
    /**
     * 机构名称
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
     * 医生名字
     */
    private String doctorName;
    /**
     * 坐诊日期
     */
    private Date workDate;
    /**
     * 坐诊时段 all,am,pm
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
     * 号源类型
     */
    private String registerType;
    /**
     * 挂号费用
     */
    private String registerCost;
    /**
     * 排班编码
     */
    private String scheduleCode;
    /**
     * 号源顺序
     */
    private String sequenceNumber;
    /**
     * 就诊开始时间
     */
    private String visitStart;
    /**
     * 就诊结束时间
     */
    private String visitEnd;
    /**
     * 患者编码
     */
    private String patientCode;
    /**
     * 患者姓名
     */
    private String patientName;
    /**
     * 患者介质类型
     */
    private String patientMediumType;
    /**
     * 患者介质号码
     */
    private String patientMediumCode;
    /**
     * 患者性别
     */
    private String patientGender;
    /**
     * 患者年龄
     */
    private String patientAge;
    /**
     * 患者生日
     */
    private String patientBirthday;
    /**
     * 患者身份证号
     */
    private String patientIdCard;
    /**
     * 患者手机
     */
    private String patientPhone;
    /**
     * icd10编码
     */
    private String icd10Code;
    /**
     * icd10名称
     */
    private String icd10Name;
    /**
     * 预约交易编码
     */
    private String transactionCode;
    /**
     * 预约时间
     */
    private Date registerTime;
    /**
     * 就诊日期
     */
    private Date visitDate;
    /**
     * 取消预约交易编码
     */
    private String cancelTransactionCode;
    /**
     *取消预约时间
     */
    private Date cancelTime;
    /**
     * 预约状态 0:待支付（预约中），2，支付成功(预约成功)，3支付失败（预约失败） 8取消（排班取消）
     */
    private String registerStatus;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 操作人机构
     */
    private String operHosCode;
    /**
     * 反馈信息
     */
    private String feedback;
    /**
     * 医生介绍
     */
    private String introduce;
    /**
     * 号源数量
     */
    private Integer registerQuantity;

    /**
     * 剩余号源
     */
    private Integer remainderQuantity;
    /**
     * 排班类型
     */
    private String scheduleType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
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

    public String getRegisterCost() {
        return registerCost;
    }

    public void setRegisterCost(String registerCost) {
        this.registerCost = registerCost;
    }

    public String getScheduleCode() {
        return scheduleCode;
    }

    public void setScheduleCode(String scheduleCode) {
        this.scheduleCode = scheduleCode;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getVisitStart() {
        return visitStart;
    }

    public void setVisitStart(String visitStart) {
        this.visitStart = visitStart;
    }

    public String getVisitEnd() {
        return visitEnd;
    }

    public void setVisitEnd(String visitEnd) {
        this.visitEnd = visitEnd;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientMediumType() {
        return patientMediumType;
    }

    public void setPatientMediumType(String patientMediumType) {
        this.patientMediumType = patientMediumType;
    }

    public String getPatientMediumCode() {
        return patientMediumCode;
    }

    public void setPatientMediumCode(String patientMediumCode) {
        this.patientMediumCode = patientMediumCode;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientBirthday() {
        return patientBirthday;
    }

    public void setPatientBirthday(String patientBirthday) {
        this.patientBirthday = patientBirthday;
    }

    public String getPatientIdCard() {
        return patientIdCard;
    }

    public void setPatientIdCard(String patientIdCard) {
        this.patientIdCard = patientIdCard;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    public String getIcd10Name() {
        return icd10Name;
    }

    public void setIcd10Name(String icd10Name) {
        this.icd10Name = icd10Name;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getCancelTransactionCode() {
        return cancelTransactionCode;
    }

    public void setCancelTransactionCode(String cancelTransactionCode) {
        this.cancelTransactionCode = cancelTransactionCode;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(String registerStatus) {
        this.registerStatus = registerStatus;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperHosCode() {
        return operHosCode;
    }

    public void setOperHosCode(String operHosCode) {
        this.operHosCode = operHosCode;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getRegisterQuantity() {
        return registerQuantity;
    }

    public void setRegisterQuantity(Integer registerQuantity) {
        this.registerQuantity = registerQuantity;
    }

    public Integer getRemainderQuantity() {
        return remainderQuantity;
    }

    public void setRemainderQuantity(Integer remainderQuantity) {
        this.remainderQuantity = remainderQuantity;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }
}
