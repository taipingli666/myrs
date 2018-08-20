package com.choice.domain.entity.base;

import java.util.Date;

public class BaseEntity{

	
	
	private Date addTime = new Date();//添加时间DATE
	
	private Integer addPerson;//添加人
	
	private Date editTime = new Date();//编辑时间
	
	private Integer editPerson;//编辑人
	
	private Integer isDelete;//是否删除

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
	
	
	

}
