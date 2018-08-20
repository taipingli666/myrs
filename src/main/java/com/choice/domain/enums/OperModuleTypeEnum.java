package com.choice.domain.enums;

/**
 * 操作模块类型
 * 
 * @author ThinkPad
 * 
 */
public enum OperModuleTypeEnum implements BaseIntEnum {
	GOODS(1, "商品"), 
	CUSTOMER(2, "顾客"), 
	MARKETING(3, "营销"), 
	CONTENT(4, "内容"), 
	CUSTOMER_SERVICE(5, "客服"),
	SYSTEM(6, "系统"), 
	CONSTANTS_CODE(7, "释义表"),
	SUPPLIER ( 8, "第三方商户"),
	ORDER ( 9, "订单管理"),
	INDEX_MANAGE ( 10, "首页管理"),
	SALE_SUPPORT ( 11,"售后管理"),
	SALE_COMMENT ( 12,"评论");

	private int value;

	private String property;

	OperModuleTypeEnum(int value, String property) {
		this.value = value;
		this.property = property;
	}

	public int getValue() {
		return value;
	}

	public String getProperty() {
		return this.property;
	}

	public static OperModuleTypeEnum valueOf(Integer typeValue) {
		if (typeValue == null) {
			return null;
		}
		for (OperModuleTypeEnum a : OperModuleTypeEnum.values()) {
			if (a.getValue() == typeValue) {
				return a;
			}
		}
		return null;
	}

}
