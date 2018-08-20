package com.choice.domain.entity.dictionary;

import java.util.Date;

public class Department {
	private Integer departmentid;	//id
	private String departmentname;			//'名称'
	private String departmentCode; //部门编码
	private String hosnum;		//'医院编码'
	private Integer hosid;		//'医院id'
	private String remark;			//'备注'
	private String distanceFlag;			//远程会诊状态 0 未启用，1启用
	private Date  addtime ;		//'添加时间'
	private Integer addperson;		//'添加人'
	private Date edittime;		//'编辑时间'
	private Integer editperson;		//'编辑人'
	private Integer isdelete;		//'0为正常1为删除'
	
	public Integer getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public String getHosnum() {
		return hosnum;
	}
	public void setHosnum(String hosnum) {
		this.hosnum = hosnum;
	}
	public Integer getHosid() {
		return hosid;
	}
	public void setHosid(Integer hosid) {
		this.hosid = hosid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDistanceFlag() {
		return distanceFlag;
	}

	public void setDistanceFlag(String distanceFlag) {
		this.distanceFlag = distanceFlag;
	}

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public Integer getAddperson() {
		return addperson;
	}
	public void setAddperson(Integer addperson) {
		this.addperson = addperson;
	}
	public Date getEdittime() {
		return edittime;
	}
	public void setEdittime(Date nowTime) {
		this.edittime = nowTime;
	}
	public Integer getEditperson() {
		return editperson;
	}
	public void setEditperson(Integer editperson) {
		this.editperson = editperson;
	}
	public Integer getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
}
