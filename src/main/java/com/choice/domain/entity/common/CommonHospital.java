package com.choice.domain.entity.common;

import java.util.Date;

import com.choice.domain.entity.base.BaseEntity;

/**
 * 医院信息
 * 
 * @author zjj
 *
 */
public class CommonHospital extends BaseEntity {

	private Integer hosId;// 医院id

	private String hosNum;// 医院编码

	private String parentCode;// 上级区域

	private String areaCode;// 所属区域

	private String hosName;// 医院名称

	private Integer level;// 区域等级

	private String consultation;// 会诊标识，0启用，1停用
	
	private Integer hosType;// 机构类型

	private Date addTime;// 添加时间

	private Integer addPerson;// 添加人

	private Date editTime;// 编辑时间

	private Integer editPerson;// 编辑人

	private Integer isDelete;// 删除标志

	public Integer getHosId() {
		return hosId;
	}

	public void setHosId(Integer hosId) {
		this.hosId = hosId;
	}

	public String getHosNum() {
		return hosNum;
	}

	public void setHosNum(String hosNum) {
		this.hosNum = hosNum;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getHosName() {
		return hosName;
	}

	public void setHosName(String hosName) {
		this.hosName = hosName;
	}

	public Integer getHosType() {
		return hosType;
	}

	public void setHosType(Integer hosType) {
		this.hosType = hosType;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getAddPerson() {
		return addPerson;
	}

	public void setAddPerson(Integer addPerson) {
		this.addPerson = addPerson;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public Integer getEditPerson() {
		return editPerson;
	}

	public void setEditPerson(Integer editPerson) {
		this.editPerson = editPerson;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getConsultation() {
		return consultation;
	}

	public void setConsultation(String consultation) {
		this.consultation = consultation;
	}
}
