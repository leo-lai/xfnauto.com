package main.com.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import main.com.frame.domain.Result;

/**
 * 日期工具类
 * 
 * 
 */
public class DateUtil {

	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @return 日期字符串
	 */
	public static String format(Date date) {
		if (date == null) {
			return null;
		}
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatCode(Date date) {
		return format(date, "yyyyMMddHHmmss");
	}

	public static String formatCodeOnleyDate(Date date) {
		return format(date, "yyyyMMdd");
	}

	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @return 日期字符串
	 */
	public static String format(Date date, boolean isOnlyDate) {
		if (isOnlyDate) {
			return format(date, "yyyy-MM-dd");
		} else {
			return format(date, "yyyy-MM-dd HH:mm:ss");
		}
	}
	
	public static Date format(String date, boolean isOnlyDate) {
		if (isOnlyDate) {
			return format(date, "yyyy-MM-dd");
		} else {
			return format(date, "yyyy-MM-dd HH:mm:ss");
		}
	}

	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @param pattern
	 *            字符串格式
	 * @return 日期字符串
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		return new java.text.SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 *            字符串类型
	 * @return 日期类型
	 */
	public static Date format(String date) {
		return format(date, null);
	}

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 *            字符串类型
	 * @param pattern
	 *            格式
	 * @return 日期类型
	 */
	public static Date format(String date, String pattern) {
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		if (date == null || date.equals("") || date.equals("null")) {
			return new Date();
		}
		Date d = null;
		try {
			d = new java.text.SimpleDateFormat(pattern).parse(date);
		} catch (ParseException pe) {
		}
		return d;
	}

	public static String getWeixinDate() {
		String pattern = "yyyy年MM月dd日 HH时mm分";
		Date d = new Date();
		try {
			return new java.text.SimpleDateFormat(pattern).format(d);
		} catch (Exception pe) {
			return "";
		}
	}

	public static String getWeixinDate(Date d) {
		if (d == null) {
			d = new Date();
		}
		String pattern = "yyyy年MM月dd日 HH时mm分";
		try {
			return new java.text.SimpleDateFormat(pattern).format(d);
		} catch (Exception pe) {
			return "";
		}
	}

	public static String getDate(Date date) {
		String pattern = "yyyy年MM月dd日";
		// Date d = new Date();
		try {
			return new java.text.SimpleDateFormat(pattern).format(date);
		} catch (Exception pe) {
			return "";
		}
	}

	/**
	 * 计算两个日期间的天数
	 * 
	 * @param fromDate
	 *            起始日期
	 * @param toDate
	 *            结束日期
	 * @return
	 * @throws ParseException
	 */
	public static int dateDiff(Date fromDate, Date toDate) {
		int days = 0;
		days = (int) Math.abs((toDate.getTime() - fromDate.getTime()) / (24 * 60 * 60 * 1000)) + 1;
		return days;
	}

	/**
	 * 计算两个日期间的时间差(秒)
	 * 
	 * @param fromDate
	 *            起始日期
	 * @param toDate
	 *            结束日期
	 * @return
	 * @throws ParseException
	 */
	public static int timeDiff(Date fromDate, Date toDate) {
		int minutes = 0;
		minutes = (int) Math.abs((toDate.getTime() - fromDate.getTime()) / 1000);
		return minutes;
	}

	/**
	 * 判断一个时间是否今天的时间
	 * 
	 * @param fromDate
	 * @return
	 */
	public static boolean isToday(Date fromDate) {
		Calendar current = Calendar.getInstance();

		Calendar today = Calendar.getInstance(); // 今天
		// Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		current.setTime(fromDate);
		if (current.after(today)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断一个时间是否昨天的时间
	 * 
	 * @param fromDate
	 * @return
	 */
	public static boolean isYesterday(Date fromDate) {
		Calendar current = Calendar.getInstance();

		Calendar today = Calendar.getInstance(); // 今天

		// Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DAY_OF_MONTH, -1); // 减一天
		// Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
		yesterday.set(Calendar.HOUR_OF_DAY, 0);
		yesterday.set(Calendar.MINUTE, 0);
		yesterday.set(Calendar.SECOND, 0);

		current.setTime(fromDate);
		if (current.after(yesterday) && current.before(today)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 当前日期添加几个月
	 * 
	 * @param month
	 * @return
	 */
	public static Date addMonth(int addMonth) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MONTH, addMonth);
		return now.getTime();
	}

	/**
	 * 当前日期添加几个月
	 * 
	 * @param month
	 * @return
	 */
	public static Date addMonth(int addMonth, Date date) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.MONTH, addMonth);
		return now.getTime();
	}

	/**
	 * 计算两个日期间的时间差(秒)
	 * 
	 * @param fromDate
	 *            起始日期
	 * @param toDate
	 *            结束日期
	 * @return
	 * @throws ParseException
	 */
	public static int compareDate(Date fromDate, Date toDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s1 = df.format(fromDate);
		String s2 = df.format(toDate);
		return s1.compareTo(s2);
	}

	public static String subMonth(Date date, int Month) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		rightNow.add(Calendar.MONTH, Month);
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
		return reStr;
	}

	/**
	 * 按月加减时间
	 * 
	 * @param date
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public static Date operMonth(Date date, int month) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		rightNow.add(Calendar.MONTH, month);
		Date dt1 = rightNow.getTime();
		return dt1;
	}

	/**
	 * 按日加减时间
	 * 
	 * @param date
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public static Date operDay(Date date, int day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		rightNow.add(Calendar.DATE, day);
		Date dt1 = rightNow.getTime();
		return dt1;
	}
	
	/**
	 * 按日加减时间
	 * 
	 * @param date
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public static Date operDayDate(Date date, int day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		rightNow.add(Calendar.DATE, day);
		Date dt1 = rightNow.getTime();
		return format(sdf.format(dt1),true);
	}

	/**
	 * 获取月份的第一天
	 * 
	 * @param date
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public static String operMonthFristDate(int month) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(GregorianCalendar.MONTH, month - 1);
		cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
		Date beginTime = cal.getTime();
		return datef.format(beginTime);
	}

	/**
	 * 获取月份的第一天
	 * 
	 * @param date
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public static Integer operMonth() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));// 获取东八区时间
		return c.get(Calendar.MONTH) + 1; // 获取月份，0表示1月份
	}

	public static boolean isToday(String str,Result result) throws Exception {
		if(StringUtil.isEmpty(str)) {
			return false;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			result.setError("预约时间选择错误");
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		int year1 = c1.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH) + 1;
		int day1 = c1.get(Calendar.DAY_OF_MONTH);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(new Date());
		int year2 = c2.get(Calendar.YEAR);
		int month2 = c2.get(Calendar.MONTH) + 1;
		int day2 = c2.get(Calendar.DAY_OF_MONTH);
		if (year1 == year2 && month1 == month2 && day1 == day2) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws ParseException {
		// String a = DateUtil.format(operMonth(new Date(),-1));
		// System.out.println(operMonthLastDate(2));
		// System.out.println(GregorianCalendar.MONTH);
		// System.out.println(Calendar.MONTH);
		// System.out.println(operMonth());
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
		// System.out.println(sdf.format(new Date()));
		// Calendar cal = Calendar.getInstance();
		// SimpleDateFormat datef=new SimpleDateFormat("yyyy-MM-dd");
		// //当前月的最后一天
		// cal.set( Calendar.DATE, 1 );
		// cal.roll(Calendar.DATE, - 1 );
		// Date endTime=cal.getTime();
		// String endTime1=datef.format(endTime)+" 23:59:59";
		// System.out.println(endTime1);
		// //当前月的第一天
		// cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
		// Date beginTime=cal.getTime();
		// String beginTime1=datef.format(beginTime)+" 00:00:00";
		// System.out.println(beginTime1);
		// System.out.println(operMonthFristDate(5));
		System.out.println(format("2017-11-11", "yyyy-MM-dd"));
	}

}
