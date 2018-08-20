package com.choice.sign.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class DbUtils {
	public static List<Object[]> ListMapToListObject(List<Map<String, Object>> list) {
		List<Object[]> result = new ArrayList<Object[]>();
		for (Map<String, Object> map : list) {
			Object[] objects = new Object[map.size()];
			int i = 0;
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				objects[i++] = entry.getValue();
			}
			result.add(objects);
		}
		return result;
	}

	public static String generateGets(String input, String prefix) {
		String[] fields = input.split(",");
		StringBuilder sb = new StringBuilder("");
		for (String field : fields) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			if (prefix != null && !"".equals(prefix)) {
				sb.append(prefix);
				sb.append(".");
			}
			sb.append("get");
			String s = field.toLowerCase();
			s = s.replaceFirst(s.substring(0, 1), s.substring(0, 1)
					.toUpperCase());
			sb.append(s);
			sb.append("()");
		}
		return sb.toString();
	}
	
	/**
	 * 生成一个get方法
	 * @param str
	 * @return
	 */
	private static String changeGetStr(String str){
		if(StrUtil.strIsNotEmpty(str)){
			return "get" + str.substring(0,1).toUpperCase() +str.substring(1).toLowerCase() + "()";
		}else{
			return str;
		}
	}

}
