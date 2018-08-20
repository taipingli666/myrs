package com.choice.sign.util;

import java.sql.Date;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor implements JsonValueProcessor {

	// 处理数组的值
	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return this.process(value);
	}

	// 处理对象的值
	@Override
	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		return this.process(value);
	}

	// 处理Date类型返回的Json数值
	private Object process(Object value) {
		if (value == null) {
			return "";
		} else if (value instanceof Date) {
			return JSONObject.fromObject(new java.util.Date(((Date) value).getTime()));
		} else {
			return value.toString();
		}
	}
}