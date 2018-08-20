package com.choice.domain.entity.reportform;

import java.sql.Timestamp;

/**
 * 签约统计报表对象
 * @author Administrator
 *
 */
public class ContractReport {
	private int areaId;
	private String areaCode;            //地区编码
	private String areaName;			//地区名称
	private String parentCode;			//父节点编码
	private String year;            	//签约年份
	private int signingNum;				//签约数
	private float signatureRate;		//签约率
	private int renewNum;				//续约数
	private float renewRate;			//续约率
	private int focusGroupsNum;		//重点人群签约数
	private float focusGroupsRate;	//重点人群签约率
	private int peopleNumber;			//人口数 
	private int level;					//等级
	private Timestamp editTime;			//修改时间
	private int isDelete;				//是否删除
	
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public int getPeopleNumber() {
		return peopleNumber;
	}
	public void setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getSigningNum() {
		return signingNum;
	}
	public void setSigningNum(int signingNum) {
		this.signingNum = signingNum;
	}
	public float getSignatureRate() {
		return signatureRate;
	}
	public void setSignatureRate(float signatureRate) {
		this.signatureRate = signatureRate;
	}
	public int getRenewNum() {
		return renewNum;
	}
	public void setRenewNum(int renewNum) {
		this.renewNum = renewNum;
	}
	public float getRenewRate() {
		return renewRate;
	}
	public void setRenewRate(float renewRate) {
		this.renewRate = renewRate;
	}
	public int getFocusGroupsNum() {
		return focusGroupsNum;
	}
	public void setFocusGroupsNum(int focusGroupsNum) {
		this.focusGroupsNum = focusGroupsNum;
	}
	public float getFocusGroupsRate() {
		return focusGroupsRate;
	}
	public void setFocusGroupsRate(float focusGroupsRate) {
		this.focusGroupsRate = focusGroupsRate;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Timestamp getEditTime() {
		return editTime;
	}
	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
