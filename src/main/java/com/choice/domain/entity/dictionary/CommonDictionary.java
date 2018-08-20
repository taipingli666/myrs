package com.choice.domain.entity.dictionary;

public class CommonDictionary {
	private String dictionaryId;	//id
	private String name;			//'名称'
	private String valueString;		//'值'
	private String remark;			//'备注'
	private String addTime ;		//'添加时间'
	private String addPerson;		//'添加人'
	private String editTime;		//'编辑时间'
	private String editPerson;		//'编辑人'
	private String isDelete;		//'0为删除1为正常'
	private String code;			//与value表做关联的字段
	public String getDictionaryId() {
		return dictionaryId;
	}
	public void setDictionaryId(String dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValueString() {
		return valueString;
	}
	public void setValueString(String valueString) {
		this.valueString = valueString;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAddTime() {
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
