package com.choice.domain.entity.external;

import java.io.Serializable;
import java.util.Date;

/**
 * 患者信息
 * @author lingli
 */
public class PatientInfo implements Serializable {
    private String id;

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
    private Date patientBirthday;

    /**
     * 患者身份证号
     */
    private String patientIdCard;

    /**
     * 患者手机
     */
    private String patientPhone;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getPatientBirthday() {
        return patientBirthday;
    }

    public void setPatientBirthday(Date patientBirthday) {
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

}