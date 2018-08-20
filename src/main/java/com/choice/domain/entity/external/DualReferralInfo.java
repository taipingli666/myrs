package com.choice.domain.entity.external;

import java.io.Serializable;
import java.util.Date;

/**
 * 转诊信息
 * @author lingli
 */
public class DualReferralInfo implements Serializable {

    private Long id;

    /**
     * 转诊时间
     */
    private Date cmmitDate;

    /**
     * 转诊患者姓名
     */
    private String patName;

    /**
     * 性别0：女 1：男
     */
    private String sex;

    /**
     * 年龄
     */
    private String age;

    /**
     * 电话
     */
    private String tel;

    /**
     * 证件类型
     */
    private String cardType;

    /**
     * 证件号码
     */
    private String cardId;

    /**
     * 转诊机构id
     */
    private String orgIdFrom;

    /**
     * 转诊机构名称
     */
    private String orgNameFrom;

    /**
     * 转诊科室id
     */
    private String deptIdFrom;

    /**
     * 转诊科室名称
     */
    private String deptNameFrom;

    /**
     * 转诊医生id
     */
    private String drIdFrom;

    /**
     * 转诊医生姓名
     */
    private String drNameFrom;

    /**
     * 转诊医生电话
     */
    private String drTel;

    /**
     * 转诊医生电话短号
     */
    private String drTelCornet;

    /**
     * 接诊机构id
     */
    private String orgIdTo;

    /**
     * 接诊机构名称
     */
    private String orgNameTo;

    /**
     * 接诊医生id
     */
    private String drIdTo;

    /**
     * 接诊医生名称
     */
    private String drNameTo;

    /**
     * 接诊医生电话
     */
    private String drMobileTo;

    /**
     * 接诊医生电话短号
     */
    private String drMobileCornetTo;

    /**
     * 转诊原因
     */
    private String reason;

    /**
     * 转诊目的
     */
    private String purpose;

    /**
     * 转诊类型 1.预约挂号，2,预约住院  3.预约检查 4.预约化验
     */
    private String refType;

    /**
     * 转诊状态 1.待接诊 2.已接诊 3.已拒绝
     */
    private String refStatus;

    private String fileName;

    /**
     * 图片保存全名称
     */
    private String fileFullName;

    /**
     * 健康卡号
     */
    private String healthId;

    /**
     * 初步诊断编码
     */
    private String icd10;

    /**
     * 初步诊断名称
     */
    private String diag;

    /**
     * 接诊科室id
     */
    private String deptIdTo;

    /**
     * 接诊科室名称
     */
    private String deptNameTo;

    /**
     * 接诊日期
     */
    private Date orderDate;

    /**
     * 基层门诊就诊流水号
     */
    private String clinicLsh;

    /**
     * 时间戳
     */
    private Date shijianchuo;

    /**
     * 描述
     */
    private String illnessDescription;

    /**
     * 内网图片地址
     */
    private String inUrlImg;

    /**
     * 外网图片地址
     */
    private String outUrlImg;

    /**
     * 意向科室
     */
    private String intentionDepart;

    /**
     * 转诊对应数据id
     */
    private String refTypeId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCmmitDate() {
        return cmmitDate;
    }

    public void setCmmitDate(Date cmmitDate) {
        this.cmmitDate = cmmitDate;
    }

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getOrgIdFrom() {
        return orgIdFrom;
    }

    public void setOrgIdFrom(String orgIdFrom) {
        this.orgIdFrom = orgIdFrom;
    }

    public String getOrgNameFrom() {
        return orgNameFrom;
    }

    public void setOrgNameFrom(String orgNameFrom) {
        this.orgNameFrom = orgNameFrom;
    }

    public String getDeptIdFrom() {
        return deptIdFrom;
    }

    public void setDeptIdFrom(String deptIdFrom) {
        this.deptIdFrom = deptIdFrom;
    }

    public String getDeptNameFrom() {
        return deptNameFrom;
    }

    public void setDeptNameFrom(String deptNameFrom) {
        this.deptNameFrom = deptNameFrom;
    }

    public String getDrIdFrom() {
        return drIdFrom;
    }

    public void setDrIdFrom(String drIdFrom) {
        this.drIdFrom = drIdFrom;
    }

    public String getDrNameFrom() {
        return drNameFrom;
    }

    public void setDrNameFrom(String drNameFrom) {
        this.drNameFrom = drNameFrom;
    }

    public String getDrTel() {
        return drTel;
    }

    public void setDrTel(String drTel) {
        this.drTel = drTel;
    }

    public String getDrTelCornet() {
        return drTelCornet;
    }

    public void setDrTelCornet(String drTelCornet) {
        this.drTelCornet = drTelCornet;
    }

    public String getOrgIdTo() {
        return orgIdTo;
    }

    public void setOrgIdTo(String orgIdTo) {
        this.orgIdTo = orgIdTo;
    }

    public String getOrgNameTo() {
        return orgNameTo;
    }

    public void setOrgNameTo(String orgNameTo) {
        this.orgNameTo = orgNameTo;
    }

    public String getDrIdTo() {
        return drIdTo;
    }

    public void setDrIdTo(String drIdTo) {
        this.drIdTo = drIdTo;
    }

    public String getDrNameTo() {
        return drNameTo;
    }

    public void setDrNameTo(String drNameTo) {
        this.drNameTo = drNameTo;
    }

    public String getDrMobileTo() {
        return drMobileTo;
    }

    public void setDrMobileTo(String drMobileTo) {
        this.drMobileTo = drMobileTo;
    }

    public String getDrMobileCornetTo() {
        return drMobileCornetTo;
    }

    public void setDrMobileCornetTo(String drMobileCornetTo) {
        this.drMobileCornetTo = drMobileCornetTo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getRefStatus() {
        return refStatus;
    }

    public void setRefStatus(String refStatus) {
        this.refStatus = refStatus;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileFullName() {
        return fileFullName;
    }

    public void setFileFullName(String fileFullName) {
        this.fileFullName = fileFullName;
    }

    public String getHealthId() {
        return healthId;
    }

    public void setHealthId(String healthId) {
        this.healthId = healthId;
    }

    public String getIcd10() {
        return icd10;
    }

    public void setIcd10(String icd10) {
        this.icd10 = icd10;
    }

    public String getDiag() {
        return diag;
    }

    public void setDiag(String diag) {
        this.diag = diag;
    }

    public String getDeptIdTo() {
        return deptIdTo;
    }

    public void setDeptIdTo(String deptIdTo) {
        this.deptIdTo = deptIdTo;
    }

    public String getDeptNameTo() {
        return deptNameTo;
    }

    public void setDeptNameTo(String deptNameTo) {
        this.deptNameTo = deptNameTo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getClinicLsh() {
        return clinicLsh;
    }

    public void setClinicLsh(String clinicLsh) {
        this.clinicLsh = clinicLsh;
    }

    public Date getShijianchuo() {
        return shijianchuo;
    }

    public void setShijianchuo(Date shijianchuo) {
        this.shijianchuo = shijianchuo;
    }

    public String getIllnessDescription() {
        return illnessDescription;
    }

    public void setIllnessDescription(String illnessDescription) {
        this.illnessDescription = illnessDescription;
    }

    public String getInUrlImg() {
        return inUrlImg;
    }

    public void setInUrlImg(String inUrlImg) {
        this.inUrlImg = inUrlImg;
    }

    public String getOutUrlImg() {
        return outUrlImg;
    }

    public void setOutUrlImg(String outUrlImg) {
        this.outUrlImg = outUrlImg;
    }

    public String getIntentionDepart() {
        return intentionDepart;
    }

    public void setIntentionDepart(String intentionDepart) {
        this.intentionDepart = intentionDepart;
    }

    public String getRefTypeId() {
        return refTypeId;
    }

    public void setRefTypeId(String refTypeId) {
        this.refTypeId = refTypeId;
    }
}