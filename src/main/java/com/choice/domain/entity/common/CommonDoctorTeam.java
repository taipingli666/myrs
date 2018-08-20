package com.choice.domain.entity.common;

import java.util.Date;

public class CommonDoctorTeam {

	private Integer teamId;//团队id
	
	private Integer teamLeader;//团队长即专家
	
	private String name;//团队名称
	
	private String hosId;//所属医院
	
	private Date addTime;//添加时间
	
	private String addPerson;//添加人
	
	private Date editTime;//编辑时间
	
	private Integer editPerson;//编辑人
	
	private Integer isDelete;//0为正常1为删除

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getTeamLeader() {
		return teamLeader;
	}

	public void setTeamLeader(Integer teamLeader) {
		this.teamLeader = teamLeader;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getAddPerson() {
		return addPerson;
	}

	public void setAddPerson(String addPerson) {
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
