package com.choice.domain.entity.external;


//his转诊信息
public class HisPatientInfo {

    private String businessType;//跳转类型
    private String hospitalpitalCode;//医院编码
    private String hospitalpitalName;//医院名称
    private String doctorCode;//医生编号
    private String doctorName;//医生姓名
    private String doctorIdCard;//医生身份证
    private String doctorDeptCode;//医生部门编号
    private String patientName;
    private String patientSex;
    private String patientAge;
    private String patientIdCard;//身份证
    private String medicalCard;//病人标识号
    private String patientYBType;//医保类型
    private String patientYBCard;//医保卡号
    private String mobile;//手机号
 /*   private String telephone;//电话
    private String patientAddress;//家庭住址
    private String patientCompany;//公司
    private String illnessDescription;//病情描述
    private String patientComplaint;//病人主诉
    private String patientSign;//体征
    private String checkInfo;
    private String recentDrugUse;//用药信息
    private String allergyHistory;*/

    public String getDoctorDeptCode() {
        return doctorDeptCode;
    }

    public void setDoctorDeptCode(String doctorDeptCode) {
        this.doctorDeptCode = doctorDeptCode;
    }

    private String patientHisId;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getHospitalpitalCode() {
        return hospitalpitalCode;
    }

    public void setHospitalpitalCode(String hospitalpitalCode) {
        this.hospitalpitalCode = hospitalpitalCode;
    }

    public String getHospitalpitalName() {
        return hospitalpitalName;
    }

    public void setHospitalpitalName(String hospitalpitalName) {
        this.hospitalpitalName = hospitalpitalName;
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

    public String getDoctorIdCard() {
        return doctorIdCard;
    }

    public void setDoctorIdCard(String doctorIdCard) {
        this.doctorIdCard = doctorIdCard;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientIdCard() {
        return patientIdCard;
    }

    public void setPatientIdCard(String patientIdCard) {
        this.patientIdCard = patientIdCard;
    }

    public String getMedicalCard() {
        return medicalCard;
    }

    public void setMedicalCard(String medicalCard) {
        this.medicalCard = medicalCard;
    }

    public String getPatientYBType() {
        return patientYBType;
    }

    public void setPatientYBType(String patientYBType) {
        this.patientYBType = patientYBType;
    }

    public String getPatientYBCard() {
        return patientYBCard;
    }

    public void setPatientYBCard(String patientYBCard) {
        this.patientYBCard = patientYBCard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

  /*  public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientCompany() {
        return patientCompany;
    }

    public void setPatientCompany(String patientCompany) {
        this.patientCompany = patientCompany;
    }

    public String getIllnessDescription() {
        return illnessDescription;
    }

    public void setIllnessDescription(String illnessDescription) {
        this.illnessDescription = illnessDescription;
    }

    public String getPatientComplaint() {
        return patientComplaint;
    }

    public void setPatientComplaint(String patientComplaint) {
        this.patientComplaint = patientComplaint;
    }

    public String getPatientSign() {
        return patientSign;
    }

    public void setPatientSign(String patientSign) {
        this.patientSign = patientSign;
    }

    public String getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(String checkInfo) {
        this.checkInfo = checkInfo;
    }

    public String getRecentDrugUse() {
        return recentDrugUse;
    }

    public void setRecentDrugUse(String recentDrugUse) {
        this.recentDrugUse = recentDrugUse;
    }

    public String getAllergyHistory() {
        return allergyHistory;
    }

    public void setAllergyHistory(String allergyHistory) {
        this.allergyHistory = allergyHistory;
    }*/

    public String getPatientHisId() {
        return patientHisId;
    }

    public void setPatientHisId(String patientHisId) {
        this.patientHisId = patientHisId;
    }
}
