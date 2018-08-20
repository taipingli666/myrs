package com.choice.sign.util;

import org.dom4j.Document;
import org.dom4j.Element;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目名称：ROOT 类名称：Functions 类描述:【方法类函数 公共类】 创建人：Administrator 创建时间：2015-12-15
 * 上午09:34:20 修改人：Administrator 修改时间：2015-12-15 上午09:34:20 修改备注：
 */

public class Functions {
	/**
	 * @方法描述:ISO-8859-1 编码转UTF-8 编码
	 * @【后台接受的String 乱码可以通过转换】
	 * @创建日期:2015-12-15 时间：上午09:35:00
	 * @入参:@param str
	 * @返回类型:String
	 * @版本:1.0
	 */
	public static String ISO_GBK(String str) {
		if (!"".equals(str)) {
			try {
				str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
			} catch (Exception e) {
			}

		}
		return str;
	}

	/**
	 * @方法描述:过滤字符串中的null或者吧null 转为”“
	 * @【oracle 查询为空 返回的数据为null 有时需要转换下】
	 * @创建日期:2015-12-15 时间：上午09:36:17
	 * @入参:@param str
	 * @返回类型:String
	 * @版本:1.0
	 */
	public static String showstr(String str) {
		try {
			if (str == null || "null".equals(str)) {
				str = "";
			}
		} catch (Exception e) {
		}
		return str;
	}

	/**
	 * @方法描述:特殊字符过滤
	 * @【有些特殊字符导致保存失败 】
	 * @创建日期:2015-12-15 时间：上午09:38:04
	 * @入参:@param str
	 * @返回类型:String
	 * @版本:1.0
	 */
	public static String StringFilter(String str) {
		try {
			String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			str = m.replaceAll("").trim();
		} catch (Exception e) {
		}
		return str;
	}

	/**
	 * @方法描述：日期加减方法
	 * @创建日期:2015-11-30 时间：下午03:08:18
	 * @入参:string 类型的日期格式
	 * @入参:需要加的天数
	 * @返回类型:String的日期格式
	 * @版本:1.0
	 */
	public static String addDay(String date, int n) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date sdate = sdf.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdate);
		calendar.add(Calendar.DAY_OF_MONTH, n);
		String exeDate = sdf.format(calendar.getTime());
		return exeDate;
	}

	/**
	 * @方法描述:date转String
	 * @创建日期:2015-12-15 时间：上午09:40:39
	 * @入参:需要转换的格式 如'YYYY-MM-DD'
	 * @入参:@DATE
	 * @返回类型:String
	 * @版本:1.0
	 */
	public static String dateToFormat(String format, Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);

		return dateFormat.format(date);
	}

	/**
	 * @方法描述:获取当前系统时间
	 * @创建日期:2015-12-15 时间：上午09:44:22
	 * @入参:@return
	 * @返回类型:Date
	 * @版本:1.0
	 */
	public static Date getsysdate() {
		Date date = new Date();
		return date;
	}

	public static String getsysdateStr() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());
	}

	public static String getsysdateStr(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);// 设置日期格式
		return df.format(new Date());
	}

	/**
	 * @方法描述: int 类型转化Long
	 * @创建日期:2015-12-15 时间：上午09:46:18
	 * @入参:@param value
	 * @返回类型:Long
	 * @版本:1.0
	 */
	public static Long InttoLong(int value) {
		return new Long((long) value);
	}

	/**
	 * @方法描述: String 类型转化Long
	 * @创建日期:2015-12-15 时间：上午09:46:18
	 * @入参:@param value
	 * @返回类型:Long
	 * @版本:1.0
	 */
	public static Long strToLong(String str) {
		Long value = null;
		try {
			value = Long.parseLong(str);
		} catch (Exception e) {
		}

		return value;
	}

	public static long strTolong(String str) {
		long value = 0;
		try {
			value = Long.parseLong(str);
		} catch (Exception e) {
			value = -1;
		}
		return value;
	}

	public static double strDouble(String str) {
		double value = 0.00;
		try {
			value = Double.parseDouble(str);
		} catch (Exception e) {
		}
		return value;
	}

	public static Date strToDate(String format, String str) {
		str = str.replaceAll("/", "-");
		if ("yyyy-MM-dd HH:mm".equals(format)) {
			if (str.length() == 10) {
				str = str + " 00:00";
			}
		}
		if ("yyyy-MM-dd HH:mm:ss".equals(format)) {
			if (str.length() == 10) {
				str = str + " 00:00:00";
			} else if (str.length() == 16) {
				str = str + ":00";
			}
		}
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);// 小写的mm表示的是分钟
			date = sdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * @方法描述:【从xml 中获取机构编号】
	 * @创建日期:2015-12-29 时间：上午09:16:57
	 * @入参:@param doc
	 * @入参:@return
	 * @返回类型:String
	 * @版本:1.0
	 */
	public static String getJgbh(Document doc) {
		Element root = doc.getRootElement();
		return root.element("jgbh").getTextTrim();
	}

	// 获取map 的值
	public static String getMapId(Map<String, Object> map, String id, Map<String, Object> map1) {
		String str = "";
		try {
			str = map.get(id).toString();
		} catch (Exception e) {
			try {
				str = map1.get(id).toString();
			} catch (Exception e2) {
			}

		}
		return str;
	}

	public static String getMapId(Map<String, Object> map, String id) {
		String str = "";
		try {
			str = map.get(id).toString();
		} catch (Exception e) {
		}
		return str;
	}

	// 获取map 的值
	public static Long getMapNum(Map<String, Object> map, String id, Map<String, Object> map1) {
		Long str = null;
		try {
			str = Long.parseLong(map.get(id).toString());
		} catch (Exception e) {
			try {
				str = Long.parseLong(map1.get(id).toString());
			} catch (Exception e2) {
				try {
					str = (long) Double.parseDouble(map.get(id).toString());
				} catch (Exception e3) {
					// TODO: handle exception
				}
			}
		}
		return str;
	}

	// 追加StringBuffer
	public static StringBuffer StraApp(StringBuffer message, String node, String nodeval) {
		message.append("<" + node + ">").append(nodeval).append("</" + node + ">");
		return message;
	}

	/**
	 * @方法描述:map 转化为对象
	 * @创建人: GEWW
	 * @创建日期:2016-7-28 时间：下午2:30:28
	 * @入参:@param type
	 * @入参:@param map
	 * @入参:@return
	 * @返回类型:Object
	 * @版本:1.0
	 */
	@SuppressWarnings("rawtypes")
	public static Object convertMap(Class type, Map map) {
		Object obj = null;
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
			obj = type.newInstance(); // 创建 JavaBean 对象
			// 给 JavaBean 对象的属性赋值
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();

				if (map.containsKey(propertyName)) {
					try {
						Object value = map.get(propertyName);
						Object[] args = new Object[1];
						args[0] = value;
						descriptor.getWriteMethod().invoke(obj, args);
					} catch (Exception e) {
					}

				}
			}
		} catch (Exception e) {
		}
		return obj;
	}

	/**
	 * @方法描述:判断是否包含在不组织xml 的字段中
	 * @创建人: GEWW
	 * @创建日期:2016-8-1 时间：上午10:37:28
	 * @入参:@param field
	 * @入参:@return
	 * @返回类型:boolean
	 * @版本:1.0
	 */
	public static boolean contains(String field) {
		// 转换为list
		List<String> tempList = Arrays.asList(InitDate.unfield);
		// 利用list的包含方法,进行判断
		if (tempList.contains(field)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @方法描述:java 日期转为字符串日期
	 * @创建人: GEWW
	 * @创建日期:2016-8-5 时间：下午4:41:46
	 * @入参:@param value
	 * @入参:@return
	 * @返回类型:String
	 * @版本:1.0
	 */
	public static String datefmort(String value) {
		String str = "";
		SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
		try {
			Date date = sdf1.parse(value);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			str = sdf.format(date);
		} catch (Exception e) {
		}
		return str;
	}

	/**
	 * 获得一个UUID
	 * 
	 * @return String UUID
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		// 去掉“-”符号
		return uuid.replaceAll("-", "");
	}

	/**
	 * 补0 函数
	 *
	 * @param num
	 *            位数
	 * @param val
	 *            值
	 * @return String
	 * @author GEWW
	 * @date 2017年9月27日:下午4:02:59
	 */
	public static String stringFormat(int num, int val) {
		String str = String.format("%0" + num + "d", val);
		return str;
	}


	/**
	 * double 乘法 精确
	 *
	 * @param a
	 * @param b
	 * @return BigDecimal
	 * @author GEWW
	 * @date 2017年10月20日:下午3:35:35
	 */
	public static BigDecimal doubleMultply(Double a, Double b) {
		BigDecimal a1 = new BigDecimal(Double.toString(a));
		BigDecimal b1 = new BigDecimal(Double.toString(b));
		BigDecimal result1 = a1.multiply(b1);
		return result1;
	}
}
