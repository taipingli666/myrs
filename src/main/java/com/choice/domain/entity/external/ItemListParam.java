package com.choice.domain.entity.external;
/**
* @author litp
* 2018年1月25日 下午4:20:53
* 化验列表参数对象
*/
public class ItemListParam {
	//当前登录人所属医院id
	private String hosId;
	//转入/转出
	private String refType;
	//化验-assay/检查-check
	private String type;
	//预约人姓名
	private String name;
	//电话
	private String tel;
	//身份证
	private String idCard;
	//预约开始时间
	private String startTime;
	//预约结束时间
	private String endTime;
	//预约时间-化验
	private String appointmentTime;
	public String getHosId() {
		return hosId;
	}
	public void setHosId(String hosId) {
		this.hosId = hosId;
	}
	public String getRefType() {
		return refType;
	}
	public void setRefType(String refType) {
		this.refType = refType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
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
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	
	
}
