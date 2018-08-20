package com.choice.sign.util;
import java.io.File;
import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static final String FORMAT_PATTERN_COMM = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_PATTERN_COMM_SSS = "yyyyMMddHHmmssSSS";
	public static final String FORMAT_PATTERN_yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
	public static final String FORMAT_PATTERN_yyyy_MM_dd = "yyyy-MM-dd";
	public static final String FORMAT_PATTERN_yyyyMMdd = "yyyyMMdd";
	public static final String FORMAT_PATTERN_UNBLANK = "yyyyMMddHHmmss";
	public static final String FORMAT_PATTERN_TIME_HH_mm_ss = "HH:mm:ss";
	public static final String FORMAT_PATTERN_TIME_HHmmss = "HHmmss";
	public static final String FORMAT_PATTERN_TIME_HH_mm = "HH:mm";
	public static final String FORMAT_PATTERN_TIME_HHmm = "HHmm";
	public static final String FORMAT_PATTERN_TIME_GMT = "EEE, dd MMM yyyy HH:mm:ss 'GMT'";
	public static final String FORMAT_PATTERN_YYYYMM = "YYYYMM";
	public static final String FORMAT_PATTERN_MMdd = "MMdd";

	public static Date stringToDate(String str, String format){
		if(str==null||"".equals(str)){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		try{
			return sdf.parse(str);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public static String dateToString(Date date, String format){
		if(date==null){
			return "";
		}
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		try{
			return sdf.format(date);
		}catch(Exception e){
			return "";
		}
	}
	public static Long getDaysBetween(Date startDate, Date endDate) {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		startCalendar.set(Calendar.HOUR_OF_DAY, 0);
		startCalendar.set(Calendar.MINUTE, 0);
		startCalendar.set(Calendar.SECOND, 0);
		startCalendar.set(Calendar.MILLISECOND, 0);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		endCalendar.set(Calendar.HOUR_OF_DAY, 0);
		endCalendar.set(Calendar.MINUTE, 0);
		endCalendar.set(Calendar.SECOND, 0);
		endCalendar.set(Calendar.MILLISECOND, 0);

		return (endCalendar.getTime().getTime() - startCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);
	}
	
	//long类型的字符串转化为指定格式的时间字符串
	public static String strlongTodatestr(String lonstr, String format){
		if(lonstr==null||"".equals(lonstr)){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		try{
			Long lontime= Long.parseLong(lonstr);
	    	Date dt1=new Date(lontime);
	    	String datestr=sdf.format(dt1);
	    	return datestr;
	    	
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/**
     * 获取某个日期后多少天的全部日期,返回字符串  由,符号拼接
     * @param startDay
     * @param count
     * @return
     */
    public static String getAfterDay(String startDay, int count){
        StringBuffer buffer = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        for (int i = 0;i<=count;i++){
            Date date = sdf.parse(startDay, new ParsePosition(0));
            calendar.setTime(date);
            // add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
            calendar.add(Calendar.DATE, i);
            Date date1 = calendar.getTime();
            String out = sdf.format(date1);
            if(i==count){
                buffer.append("'"+out+"'");
            }else{
                buffer.append("'"+out+"',");
            }
        }
        return buffer.toString();
    }
    
    /**
     * 根据路径加上yyyyMM生成文件夹
     */
    public static String createYYYYMMDir(String path){
        SimpleDateFormat fds = new SimpleDateFormat("yyyyMM");
        String date = fds.format(new Date());
        path = path + date;//C:/m/d/201732
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();//如果不存在,就创建该文件夹
        }
        return date;
    }

	public static void main(String[] args) {
		System.out.println(dateToString(new Date(),"hh"));
	}

	/**
	 * 返回两个时间段相差的分钟和小时数
	 * @param startTime 起始时间
	 * @param endTime 结束时间
	 * @param format 时间格式
	 * @param str h或m
	 * @return
	 */
	public static Long dateDiff(String startTime, String endTime,
                                String format, String str) {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		// 获得两个时间的毫秒时间差异
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			day = diff / nd;// 计算差多少天
			hour = diff /nh;// 计算差多少小时
			min = diff /nm;// 计算差多少分钟
			sec = diff / ns;// 计算差多少秒
			// 输出结果
			System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时"
					+ (min - day * 24 * 60) + "分钟" + sec + "秒。");
			System.out.println("hour=" + hour + ",min=" + min);
			if (str.equalsIgnoreCase("h")) {
				return hour;
			} else if(str.equalsIgnoreCase("m")){
				return min;
			}else if(str.equalsIgnoreCase("s")){
				return sec;
			}else {
				return day;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取当前日期
	 *
	 * @return
	 */
	public static String getCurrentDate() {
		return getCurrentDate(FORMAT_PATTERN_COMM);
	}
	public static String getSSSCurrentDate() {
		return getCurrentDate(FORMAT_PATTERN_COMM_SSS);
	}

	public static String getCurrentDate(String formatPattern) {
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		return format.format(new Date());
	}

	/**
	 * 获取指定日期几天前（后）的日期
	 * @param startDate 起始日期，可为空
	 * @param addDay
	 * @return
	 */
	public static Date getAddDate(Date startDate,int addDay){
		if( startDate == null ){
			startDate = new Date();
		}
		try{
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(startDate);
			calendar.add( Calendar.DAY_OF_MONTH ,addDay );
			startDate = calendar.getTime();
			return startDate;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取指定日期几小时前（后）的日期
	 * @param startDate 起始日期，可为空
	 * @param hour
	 * @return
	 */
	public static Date getAddHour(Date startDate,int hour){
		if( startDate == null ){
			startDate = new Date();
		}
		try{
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(startDate);
			calendar.add( Calendar.HOUR_OF_DAY ,hour );
			startDate = calendar.getTime();
			return startDate;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取指定日期几月前（后）的日期
	 * @param startDate
	 * @param addMonth
	 * @return
	 */
	public static Date getAddMonth(Date startDate,int addMonth){
		if( startDate == null ){
			startDate = new Date();
		}
		try{
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(startDate);
			calendar.add( Calendar.MONTH ,addMonth );
			startDate = calendar.getTime();
			return startDate;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取日期所在第几周
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear( Date date){
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setFirstDayOfWeek(Calendar.SUNDAY);
			calendar.setTime(date);
			int v= calendar.get(Calendar.WEEK_OF_YEAR);
			return calendar.get(Calendar.WEEK_OF_YEAR);
		}catch (Exception e){
			e.printStackTrace();
			return 0;
		}

	}


	/**
	 * 获取本周的开始时间
	 * @return yyyy-MM-dd HH:mm:ss  格式
	 */
	public static Date getBeginDayOfWeek() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayofweek == 1) {
			dayofweek += 7;
		}
		cal.add(Calendar.DATE, 1 - dayofweek);
		return DateUtils.getDayStartTime(cal.getTime());
	}

	/**
	 * 获取本周的结束时间
	 * @return yyyy-MM-dd HH:mm:ss  格式
	 */
	public static Date getEndDayOfWeek(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(getBeginDayOfWeek());
		cal.add(Calendar.DAY_OF_WEEK, 6);
		Date weekEndSta = cal.getTime();
		return DateUtils.getDayEndTime(weekEndSta);
	}


}
