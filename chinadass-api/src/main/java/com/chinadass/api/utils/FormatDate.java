package com.chinadass.api.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 处理时间的工具类
 */
public class FormatDate {

	// 通用月份格式
		public final static String SIMPLE_MONTH_FORMAT = "yyyy-MM";
		// 通用日期格式
		public final static String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
		// 时间戳格式,精确到分钟
		public final static String DATE_TIME_MIN_FORMAT = "yyyyMMddHHmm";
		// 时间戳格式，精确到秒
		public final static String DATE_TIME_SEC_FORMAT = "yyyyMMddHHmmss";
		// 给产线的时间格式
		public final static String DATE_TIME_SEC_FOR_PRO = "yyyy-MM-dd HH:mm:ss";
		
		public final static int MONTH = 5;

		/**
		 * 格式化日期为:yyyy 年 MM 月 dd 日
		 * 
		 * @param date
		 *            需要格式化的日期
		 * @return 格式化好的日期字符串
		 */
		public static String formatDate(Date date) {
			if (date == null)
				return "";
			String pattern = "yyyy年 MM月 dd日";
			return formatDate(date, pattern);
		}

		/**
		 * 格式化日期为:yyyy-MM-dd HH:mm:ss
		 * 
		 * @param date
		 *            需要格式化的日期
		 * @return 格式化好的日期字符串
		 */
		public static String formatSelTime(Date date) {
			if (date == null)
				return "";
			String pattern = "yyyy-MM-dd HH:mm:ss";
			return formatDate(date, pattern);
		}

		/**
		 * 格式化日期为:yyyy-MM-dd HH:mm
		 * 
		 * @param date
		 *            需要格式化的日期
		 * @return 格式化好的日期字符串
		 */
		public static String formatSelTime1(Date date) {
			if (date == null)
				return "";
			String pattern = "yyyy-MM-dd HH:mm";
			return formatDate(date, pattern);
		}

		/**
		 * 根据指定的模式格式化日期
		 * 
		 * @param date
		 *            需要格式化的日期
		 * @param pattern
		 *            格式模式
		 * @return 格式化好的日期字符串
		 */
		public static String formatDate(Date date, String pattern) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			try {
				return sdf.format(date);
			} catch (Exception e) {
				return null;
			}

		}

		/**
		 * 格式化日期：yyyy年MM月dd日 HH时mm分ss秒
		 * 
		 * @param date
		 *            需要格式化的日期
		 * @return 格式化好的日期字符串
		 */
		public static String formatTime(Date date) {
			String pattern = "yyyy年MM月dd日 HH时mm分ss秒";
			return formatDate(date, pattern);
		}

		/**
		 * 讲一个字符串转换成一个Date对象
		 * 
		 * @param date
		 * @return
		 */
		public static Date parse(String date) {
			date = date.replaceAll("/", "-").replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 将一个字符串转换成一个Date对象
		 * 
		 * @param date
		 * @return
		 */
		public static Date parse(String date, String format) {
			date = date.replaceAll("/", "-").replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "");
			SimpleDateFormat sdf = new SimpleDateFormat(format);

			try {

				return sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			return null;
		}

		/**
		 * yyyy-MM-dd to yyyy-MM-dd HH:mm:ss
		 * 
		 * @param timeStr
		 * @return
		 */
		public static String formatTime(String timeStr) {
			SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date dateTime = null;
			String date = null;
			try {
				dateTime = sdf.parse(timeStr);
				Timestamp time = new Timestamp(dateTime.getTime());
				date = sDateFormat.format(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}

		public static Timestamp parse2Time(String dateOrTime, String format) {

			SimpleDateFormat sdf = new SimpleDateFormat(format);

			try {
				Date date = sdf.parse(dateOrTime);
				return new Timestamp(date.getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 返回yyyy-MM-dd
		 * 
		 * @param time
		 * @return
		 */
		public static String parDateStr(Date time) {
			String pattern = "yyyy-MM-dd";
			return formatDate(time, pattern);
		}

		/**
		 * 返回yyyyMMddHHmmss
		 * 
		 * @param time
		 * @return
		 */
		public static String parDate2Str(Timestamp time) {
			String pattern = "yyyyMMddHHmmss";
			return formatDate(time, pattern);
		}

		public static String parDate2Str(Timestamp time, String pattern) {
			// String pattern = "yyyy/MM/dd HH:mm:ss";
			return formatDate(time, pattern);
		}

		public static Date parDate2Date(Timestamp time) {
			String pattern = "yyyy-MM-dd HH:mm:ss";
			return parse(formatDate(time, pattern));
		}

		public static Timestamp parStr2Date(String time) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			Date date = null;
			try {
				date = sdf.parse(time);
				return (new Timestamp(date.getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}

		/**
		 * 根据指定的格式转换timeStr
		 * 
		 * @param time
		 * @param format
		 * @return
		 */
		public static Date parStr2Date(String time, String format) {

			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = null;
			try {
				date = sdf.parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}

		public static boolean isSameDate(Date date1, Date date2) {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);

			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);

			boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
			boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
			boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

			return isSameDate;
		}

		/**
		 * 计算两个日期之间相差的月数
		 * 
		 * @param smdate
		 *            较小的时间
		 * @param bdate
		 *            较大的时间
		 * @return 相差月数
		 * @throws ParseException
		 */
		public static int monthsBetween(Date smdate, Date bdate) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				smdate = sdf.parse(sdf.format(smdate));
				bdate = sdf.parse(sdf.format(bdate));
				Calendar cal = Calendar.getInstance();
				cal.setTime(smdate);
				long month1 = cal.get(Calendar.YEAR) * 12 + cal.get(Calendar.MONTH);
				cal.setTime(bdate);
				long month2 = cal.get(Calendar.YEAR) * 12 + cal.get(Calendar.MONTH);
				long between_months = month2 - month1;
				return Integer.parseInt(String.valueOf(between_months));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

		/**
		 * 计算两个日期之间相差的天数
		 * 
		 * @param smdate
		 *            较小的时间
		 * @param bdate
		 *            较大的时间
		 * @return 相差天数
		 * @throws ParseException
		 */
		public static int daysBetween(Date smdate, Date bdate) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				smdate = sdf.parse(sdf.format(smdate));
				bdate = sdf.parse(sdf.format(bdate));
				Calendar cal = Calendar.getInstance();
				cal.setTime(smdate);
				long time1 = cal.getTimeInMillis();
				cal.setTime(bdate);
				long time2 = cal.getTimeInMillis();
				long between_days = (time2 - time1) / (1000 * 3600 * 24);
				return Integer.parseInt(String.valueOf(between_days));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

		/**
		 * 计算两个日期之间相差的天数
		 * 
		 * @param smdate
		 *            较小的时间
		 * @param bdate
		 *            较大的时间
		 * @return 相差天数
		 * @throws ParseException
		 */
		public static long timeBetween(Date smdate, Date bdate) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				smdate = sdf.parse(sdf.format(smdate));
				bdate = sdf.parse(sdf.format(bdate));
				Calendar cal = Calendar.getInstance();
				cal.setTime(smdate);
				long time1 = cal.getTimeInMillis();
				cal.setTime(bdate);
				long time2 = cal.getTimeInMillis();
				return Math.abs(time2 - time1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

		/**
		 * 字符串的日期格式的计算
		 */
		public static int daysBetween(String smdate, String bdate) throws ParseException {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(smdate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(bdate));
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);

			return Integer.parseInt(String.valueOf(between_days));
		}

		public static Date now() {
			return new Date();
		}

		/**
		 * 是否是今天
		 * 
		 * @param date
		 * @return
		 */
		public static boolean isToday(final Date date) {
			return isTheDay(date, FormatDate.now());
		}

		/**
		 * 是否是指定日期
		 * 
		 * @param date
		 * @param day
		 * @return
		 */
		public static boolean isTheDay(final Date date, final Date day) {
			return date.getTime() >= FormatDate.dayBegin(day).getTime()
					&& date.getTime() <= FormatDate.dayEnd(day).getTime();
		}


		/**
		 * 获取指定时间的那天 00:00:00.000 的时间
		 * 
		 * @param date
		 * @return
		 */
		public static Date dayBegin(final Date date) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			return c.getTime();
		}

		/**
		 * 获取指定时间的那天 23:59:59.999 的时间
		 * 
		 * @param date
		 * @return
		 */
		public static Date dayEnd(final Date date) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
			c.set(Calendar.MILLISECOND, 999);
			return c.getTime();
		}
		
		/**
		 * 获取最近月份列表
		 * 
		 * @param months
		 * @return
		 */
		public static List<String> getMonthList(int months) {
			List<String> monthList = new ArrayList<String>();
			Calendar c = Calendar.getInstance();

			if (months < 1) {
				months = 1;
			}
			for (int i = 0; i < months; i++) {
				monthList.add(0,FormatDate.formatDate(c.getTime(), "yyMM"));
				c.add(Calendar.MONTH, -1);
			}
			return monthList;
		}
		
}
