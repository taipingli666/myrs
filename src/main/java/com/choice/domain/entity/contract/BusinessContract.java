package com.choice.domain.entity.contract;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BusinessContract {
	
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private String contractId;                  //'合约id'
	private String insuranceType;           //'医保类型'
	private String insuranceId;         //'医保编号'
	private String trueName;            //'姓名'
	private String sex;         //'性别'
	private String mobile;          //手机号码
	private String card;            //'身份证号'
	private String personType;      //'居民类型'
	private String source;              //'来源'
	private String signYear;        //'签约年份'
	private String startTime;           //'开始日期'
	private String endTime;             //'结束日期'
	private String county;			//区县
	private String town;            //'乡镇'
	private String village;         //'村'
	private String address;         // '门牌号'
	private String bClass;          //'套餐大类'
	private String sClass;          // '套餐小类'
	private String expertId;        // '签约医生'
	private String havePaper;           // '纸质留底'
	private String signType;            //'签约类型'
	private String crowdType;           //'人群分类'
	private String diseaseType;         //'疾病分类'
	private String remark;              //'备注'
	private String status;          //'状态，默认为0未审核，1签约成功，2签约失败，3，续约，4解决'
	private String addTime;         //添加时间
	private String addPerson;       //'添加人'
	private String editTime;         //编辑时间
	private String editPerson;          //'修改人'
	private String isDelete;            //'0为删除1为正常'
	private String hosId;			//签约机构
	private String isRenew;			//是否续约
	private String isKey;			//是否是重点人群
	public String getIsKey() {
		return isKey;
	}
	public void setIsKey(String isKey) {
		this.isKey = isKey;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public String getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getPersonType() {
		return personType;
	}
	public void setPersonType(String personType) {
		this.personType = personType;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSignYear() {
		return signYear;
	}
	public void setSignYear(String signYear) {
		this.signYear = signYear;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getbClass() {
		return bClass;
	}
	public void setbClass(String bClass) {
		this.bClass = bClass;
	}
	public String getsClass() {
		return sClass;
	}
	public void setsClass(String sClass) {
		this.sClass = sClass;
	}
	public String getExpertId() {
		return expertId;
	}
	public void setExpertId(String expertId) {
		this.expertId = expertId;
	}
	public String getIsRenew() {
		return isRenew;
	}
	public void setIsRenew(String isRenew) {
		this.isRenew = isRenew;
	}
	public String getHavePaper() {
		return havePaper;
	}
	public void setHavePaper(String havePaper) {
		this.havePaper = havePaper;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getCrowdType() {
		return crowdType;
	}
	public void setCrowdType(String crowdType) {
		this.crowdType = crowdType;
	}
	public String getDiseaseType() {
		return diseaseType;
	}
	public void setDiseaseType(String diseaseType) {
		this.diseaseType = diseaseType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddTime() throws ParseException {
		Date date = fmt.parse(addTime);
		addTime = fmt.format(date);
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getAddPerson() {
		return addPerson;
	}
	public void setAddPerson(String addPerson) {
		this.addPerson = addPerson;
	}
	public String getEditTime() {
		return editTime;
	}
	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}
	public String getEditPerson() {
		return editPerson;
	}
	public void setEditPerson(String editPerson) {
		this.editPerson = editPerson;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getHosId() {
		return hosId;
	}
	public void setHosId(String hosId) {
		this.hosId = hosId;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	
}
