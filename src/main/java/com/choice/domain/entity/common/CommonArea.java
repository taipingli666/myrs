/**
 * 
 */
/**
 * @author Paul.Pan
 *
 */
package com.choice.domain.entity.common;

import com.choice.domain.entity.base.BaseEntity;

public class CommonArea extends BaseEntity{
	
	private Integer areaId;//区域id
	
	private String areaName;//区域名称
	
	private String parentCode ;//父节点
	
	private String code;
	
	private Integer level; //等级

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getlevel() {
		return level;
	}

	public void setlevel(Integer level) {
		this.level = level;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	
}