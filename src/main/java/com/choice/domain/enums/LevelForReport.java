package com.choice.domain.enums;
/**
 * report_statistics表里level 与   common_dictionary_value表里parent_code=10008的code值
 * @author Administrator
 *
 */
public enum LevelForReport implements BaseIntEnum {
	DOCTOR(1,""),HOSPITAL(2,""),VILLAGE(3,"100084"),TOWN(4,"100083"),COUNTY(5,"100082"),PROVINCES(6,"100081");
	
	private int level;

	private String areaCode;
	
	LevelForReport(int level, String areaCode) {
		this.level = level;
		this.areaCode = areaCode;
	}

	public String getProperty() {
		return areaCode;
	}

	public int getValue() {
		return level;
	}
	
	public static LevelForReport valueOf(Integer typeValue) {
		if (typeValue == null) {
			return null;
		}
		for (LevelForReport a : LevelForReport.values()) {
			if (a.getValue() == typeValue) {
				return a;
			}
		}
		return null;
	}
}
