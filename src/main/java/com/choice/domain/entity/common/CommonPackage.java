package com.choice.domain.entity.common;

import java.util.Date;

import com.choice.domain.entity.base.BaseEntity;

public class CommonPackage extends BaseEntity{
	
	private Integer packageId;
	
	private String className ;
	
	private Integer classID ;
	
	private String remark;

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
