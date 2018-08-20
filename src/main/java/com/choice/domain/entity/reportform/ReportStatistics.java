package com.choice.domain.entity.reportform;

import java.util.Date;

public class ReportStatistics {
	private int reportId;				//id
	private long subordinateId;			//签约专家id
	private Date signingTime;			//签约时间
	private String signingTimeStr;		//签约时间String类型，用于传参
	private int signingNum;				//签约数
	private float signatureRate;		//签约率
	private int renewNum;				//续约数
	private float renewRate;			//续约率
	private int signingNumOfKey;		//重点人群签约数
	private float signatureRateOfKey;	//重点人群签约率
	private int level;					//级别
	public int getReportId() {
		return reportId;
	}
	public int getLevel() {
		return level;
	}
	public String getSigningTimeStr() {
		return signingTimeStr;
	}
	public void setSigningTimeStr(String signingTimeStr) {
		this.signingTimeStr = signingTimeStr;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public long getSubordinateId() {
		return subordinateId;
	}
	public void setSubordinateId(long subordinateId) {
		this.subordinateId = subordinateId;
	}
	public void setSubordinateId(int subordinateId) {
		this.subordinateId = subordinateId;
	}
	public Date getSigningTime() {
		return signingTime;
	}
	public void setSigningTime(Date signingTime) {
		this.signingTime = signingTime;
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
	public int getSigningNumOfKey() {
		return signingNumOfKey;
	}
	public void setSigningNumOfKey(int signingNumOfKey) {
		this.signingNumOfKey = signingNumOfKey;
	}
	public float getSignatureRateOfKey() {
		return signatureRateOfKey;
	}
	public void setSignatureRateOfKey(float signatureRateOfKey) {
		this.signatureRateOfKey = signatureRateOfKey;
	}
}
