package com.choice.domain.enums;

/**
 * 操作类型
 * 
 * 
 * @author ThinkPad
 * 
 */
public enum OperTypeEnum implements BaseIntEnum {
	ADD(1, "新增"), UPDATE(2, "修改"), ADD_OR_UPDATE(3, "新增或修改"), DEL(4, "删除");

	private int value;

	private String property;

	OperTypeEnum(int value, String property) {
		this.value = value;
		this.property = property;
	}

	public int getValue() {
		return value;
	}

	public String getProperty() {
		return this.property;
	}

	public static OperTypeEnum valueOf(Integer typeValue) {
		if (typeValue == null) {
			return null;
		}
		for (OperTypeEnum a : OperTypeEnum.values()) {
			if (a.getValue() == typeValue) {
				return a;
			}
		}
		return null;
	}

}
