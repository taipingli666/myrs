package com.choice.domain.entity.referral;

import java.sql.Timestamp;
import java.util.Date;

public class BusDualReferral {
    /**
     * 
     */
    private Long id;

    /**
     * 转诊时间
     */
    private Timestamp cmmitDate;

    /**
     * 转诊患者姓名
     */
    private String patName;

    /**
     * 性别
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
     * 转诊科室名称
     */
    private String deptNameFrom;

    /**
     * 转诊科室id
     */
    private String deptIdFrom;

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
     * 转诊原因
     */
    private String reason;

    /**
     * 转诊目的
     */
    private String purpose;

    /**
     * 转诊类型
     */
    private String refType;

    /**
     * 转诊状态
     */
    private String refStatus;

    /**
     * 
     */
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
     * 预约日期
     */
    private String orderDate;

    private String cmmitDateShow;
    private String orderDateShow;
    /**
     * 基层门诊就诊流水号
     */
    private String clinicLsh;


    //新增上传文件夹名称
    private String sysdateFileId;



    /**
     * 接诊医生id
     */
    private String drIdTo;

    /**
     * 接诊医生姓名
     */
    private String drNameTo;

    /**
     * 接诊医生电话
     */
    private String drMobelTo;
    /**
     * 接诊医生电话短号
     */
    private String drMobelCornetTo;
    /**
     * 病情描述
     */
    private String illnessDescription;
    /**
     * 内网图片查看地址
     */
    private String inUrlImg;
    /**
     * 外网图片查看地址
     */
    private String outUrlImg;
    /**
     * 意向科室
     */
    private String intentionDepart;

    /**
     * 反馈信息
     */
    private String feedback;

    /**
     * 病情描述
     * @return 返回病情描述
     */
    public String getIllnessDescription() {
        return illnessDescription;
    }

    /**
     * 病情描述
     */
    public void setIllnessDescription(String illnessDescription) {
        this.illnessDescription = illnessDescription;
    }

    /**
     * 内网图片查看地址
     * @return 返回内网图片查看地址
     */
    public String getInUrlImg() {
        return inUrlImg;
    }

    /**
     * 内网图片查看地址
     * @param inUrlImg
     */
    public void setInUrlImg(String inUrlImg) {
        this.inUrlImg = inUrlImg;
    }

    /**
     * 外网图片查看地址
     * @return 外网图片查看地址
     */
    public String getOutUrlImg() {
        return outUrlImg;
    }

    /**
     * 外网图片查看地址
     * @param outUrlImg
     */
    public void setOutUrlImg(String outUrlImg) {
        this.outUrlImg = outUrlImg;
    }

    /**
     * 意向科室
     * @return 意向科室
     */
    public String getIntentionDepart() {
        return intentionDepart;
    }

    /**
     * 意向科室
     * @param intentionDepart
     */
    public void setIntentionDepart(String intentionDepart) {
        this.intentionDepart = intentionDepart;
    }

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 转诊时间
     * @return cmmit_date 转诊时间
     */
    public Timestamp getCmmitDate() {
        return cmmitDate;
    }

    /**
     * 转诊时间
     * @param cmmitDate 转诊时间
     */
    public void setCmmitDate(Timestamp cmmitDate) {
        this.cmmitDate = cmmitDate;
    }

    /**
     * 转诊患者姓名
     * @return pat_name 转诊患者姓名
     */
    public String getPatName() {
        return patName;
    }

    /**
     * 转诊患者姓名
     * @param patName 转诊患者姓名
     */
    public void setPatName(String patName) {
        this.patName = patName == null ? null : patName.trim();
    }

    /**
     * 性别
     * @return sex 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性别
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 年龄
     * @return age 年龄
     */
    public String getAge() {
        return age;
    }

    /**
     * 年龄
     * @param age 年龄
     */
    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    /**
     * 电话
     * @return tel 电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 电话
     * @param tel 电话
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * 证件类型
     * @return card_type 证件类型
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * 证件类型
     * @param cardType 证件类型
     */
    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    /**
     * 证件号码
     * @return card_id 证件号码
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * 证件号码
     * @param cardId 证件号码
     */
    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    /**
     * 转诊机构id
     * @return org_id_from 转诊机构id
     */
    public String getOrgIdFrom() {
        return orgIdFrom;
    }

    /**
     * 转诊机构id
     * @param orgIdFrom 转诊机构id
     */
    public void setOrgIdFrom(String orgIdFrom) {
        this.orgIdFrom = orgIdFrom == null ? null : orgIdFrom.trim();
    }

    /**
     * 转诊机构名称
     * @return org_name_from 转诊机构名称
     */
    public String getOrgNameFrom() {
        return orgNameFrom;
    }

    /**
     * 转诊机构名称
     * @param orgNameFrom 转诊机构名称
     */
    public void setOrgNameFrom(String orgNameFrom) {
        this.orgNameFrom = orgNameFrom == null ? null : orgNameFrom.trim();
    }

    /**
     * 转诊科室名称
     * @return dept_name_from 转诊科室名称
     */
    public String getDeptNameFrom() {
        return deptNameFrom;
    }

    /**
     * 转诊科室名称
     * @param deptNameFrom 转诊科室名称
     */
    public void setDeptNameFrom(String deptNameFrom) {
        this.deptNameFrom = deptNameFrom == null ? null : deptNameFrom.trim();
    }

    /**
     * 转诊科室id
     * @return dept_id_from 转诊科室id
     */
    public String getDeptIdFrom() {
        return deptIdFrom;
    }

    /**
     * 转诊科室id
     * @param deptIdFrom 转诊科室id
     */
    public void setDeptIdFrom(String deptIdFrom) {
        this.deptIdFrom = deptIdFrom == null ? null : deptIdFrom.trim();
    }

    /**
     * 转诊医生id
     * @return dr_id_from 转诊医生id
     */
    public String getDrIdFrom() {
        return drIdFrom;
    }

    /**
     * 转诊医生id
     * @param drIdFrom 转诊医生id
     */
    public void setDrIdFrom(String drIdFrom) {
        this.drIdFrom = drIdFrom == null ? null : drIdFrom.trim();
    }

    /**
     * 转诊医生姓名
     * @return dr_name_from 转诊医生姓名
     */
    public String getDrNameFrom() {
        return drNameFrom;
    }

    /**
     * 转诊医生姓名
     * @param drNameFrom 转诊医生姓名
     */
    public void setDrNameFrom(String drNameFrom) {
        this.drNameFrom = drNameFrom == null ? null : drNameFrom.trim();
    }

    /**
     * 转诊医生电话
     * @return dr_tel 转诊医生电话
     */
    public String getDrTel() {
        return drTel;
    }

    /**
     * 转诊医生电话
     * @param drTel 转诊医生电话
     */
    public void setDrTel(String drTel) {
        this.drTel = drTel == null ? null : drTel.trim();
    }

    /**
     * 转诊医生电话短号
     * @return dr_tel_cornet 转诊医生电话短号
     */
    public String getDrTelCornet() {
        return drTelCornet;
    }

    /**
     * 转诊医生电话短号
     * @param drTelCornet 转诊医生电话短号
     */
    public void setDrTelCornet(String drTelCornet) { this.drTelCornet = drTelCornet == null ? null : drTelCornet.trim();  }

    /**
     * 接诊机构id
     * @return org_id_to 接诊机构id
     */
    public String getOrgIdTo() {
        return orgIdTo;
    }

    /**
     * 接诊机构id
     * @param orgIdTo 接诊机构id
     */
    public void setOrgIdTo(String orgIdTo) {
        this.orgIdTo = orgIdTo == null ? null : orgIdTo.trim();
    }

    /**
     * 接诊机构名称
     * @return org_name_to 接诊机构名称
     */
    public String getOrgNameTo() {
        return orgNameTo;
    }

    /**
     * 接诊机构名称
     * @param orgNameTo 接诊机构名称
     */
    public void setOrgNameTo(String orgNameTo) {
        this.orgNameTo = orgNameTo == null ? null : orgNameTo.trim();
    }

    /**
     * 转诊原因
     * @return reason 转诊原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 转诊原因
     * @param reason 转诊原因
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * 转诊目的
     * @return purpose 转诊目的
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * 转诊目的
     * @param purpose 转诊目的
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose == null ? null : purpose.trim();
    }

    /**
     * 转诊类型
     * @return ref_type 转诊类型
     */
    public String getRefType() {
        return refType;
    }

    /**
     * 转诊类型
     * @param refType 转诊类型
     */
    public void setRefType(String refType) {
        this.refType = refType == null ? null : refType.trim();
    }

    /**
     * 转诊状态
     * @return ref_status 转诊状态
     */
    public String getRefStatus() {
        return refStatus;
    }

    /**
     * 转诊状态
     * @param refStatus 转诊状态
     */
    public void setRefStatus(String refStatus) {
        this.refStatus = refStatus == null ? null : refStatus.trim();
    }

    /**
     * 
     * @return file_name 
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 
     * @param fileName 
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 图片保存全名称
     * @return file_full_name 图片保存全名称
     */
    public String getFileFullName() {
        return fileFullName;
    }

    /**
     * 图片保存全名称
     * @param fileFullName 图片保存全名称
     */
    public void setFileFullName(String fileFullName) {
        this.fileFullName = fileFullName == null ? null : fileFullName.trim();
    }

    /**
     * 健康卡号
     * @return health_id 健康卡号
     */
    public String getHealthId() {
        return healthId;
    }

    /**
     * 健康卡号
     * @param healthId 健康卡号
     */
    public void setHealthId(String healthId) {
        this.healthId = healthId == null ? null : healthId.trim();
    }

    /**
     * 初步诊断编码
     * @return icd10 初步诊断编码
     */
    public String getIcd10() {
        return icd10;
    }

    /**
     * 初步诊断编码
     * @param icd10 初步诊断编码
     */
    public void setIcd10(String icd10) {
        this.icd10 = icd10 == null ? null : icd10.trim();
    }

    /**
     * 初步诊断名称
     * @return diag 初步诊断名称
     */
    public String getDiag() {
        return diag;
    }

    /**
     * 初步诊断名称
     * @param diag 初步诊断名称
     */
    public void setDiag(String diag) {
        this.diag = diag == null ? null : diag.trim();
    }

    /**
     * 接诊科室id
     * @return dept_id_to 接诊科室id
     */
    public String getDeptIdTo() {
        return deptIdTo;
    }

    /**
     * 接诊科室id
     * @param deptIdTo 接诊科室id
     */
    public void setDeptIdTo(String deptIdTo) {
        this.deptIdTo = deptIdTo == null ? null : deptIdTo.trim();
    }

    /**
     * 接诊科室名称
     * @return dept_name_to 接诊科室名称
     */
    public String getDeptNameTo() {
        return deptNameTo;
    }

    /**
     * 接诊科室名称
     * @param deptNameTo 接诊科室名称
     */
    public void setDeptNameTo(String deptNameTo) {
        this.deptNameTo = deptNameTo == null ? null : deptNameTo.trim();
    }

    /**
     * 预约日期
     * @return order_date 预约日期
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * 预约日期
     * @param orderDate 预约日期
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * 基层门诊就诊流水号
     * @return clinic_lsh 基层门诊就诊流水号
     */
    public String getClinicLsh() {
        return clinicLsh;
    }

    /**
     * 基层门诊就诊流水号
     * @param clinicLsh 基层门诊就诊流水号
     */
    public void setClinicLsh(String clinicLsh) {
        this.clinicLsh = clinicLsh == null ? null : clinicLsh.trim();
    }


    public String getSysdateFileId() {
        return sysdateFileId;
    }

    public void setSysdateFileId(String sysdateFileId) {
        this.sysdateFileId = sysdateFileId;
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

    public String getDrMobelTo() {
        return drMobelTo;
    }

    public void setDrMobelTo(String drMobelTo) {
        this.drMobelTo = drMobelTo;
    }

    /**
     * 接诊医生电话短号
     * @return dr_mobile_cornet_to 接诊医生电话短号
     */
    public String getDrMobelCornetTo() {
        return drMobelCornetTo;
    }

    /**
     * 接诊医生电话短号
     * @param drMobelCornetTo 基层门诊就诊流水号
     */
    public void setDrMobelCornetTo(String drMobelCornetTo) { this.drMobelCornetTo = drMobelCornetTo == null ? null : drMobelCornetTo.trim();  }

    public String getCmmitDateShow() {
        return cmmitDateShow;
    }

    public void setCmmitDateShow(String cmmitDateShow) {
        this.cmmitDateShow = cmmitDateShow;
    }

    public String getOrderDateShow() {
        return orderDateShow;
    }

    public void setOrderDateShow(String orderDateShow) {
        this.orderDateShow = orderDateShow;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}