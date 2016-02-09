package devgo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static final String DEFAULT_FORMAT_WITH_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_FORMAT_WITH_DATE = "yyyy-MM-dd";
	private static final SimpleDateFormat DEFAULT_FORMAT_YMD_HMS = new SimpleDateFormat(DEFAULT_FORMAT_WITH_DATE_TIME);
	private static final SimpleDateFormat DEFAULT_FORMAT_YMD = new SimpleDateFormat(DEFAULT_FORMAT_WITH_DATE);

	/**
	 * 获取当前的系统时间,Format:yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		return DEFAULT_FORMAT_YMD_HMS.format(Calendar.getInstance().getTime());
	}
	
	/**
	 * 根据日期格式获取当前时间
	 * 
	 * @param pattern 日期格式
	 * @return
	 */
	public static String getCurrentDateTime(String pattern) {
		if (CommonUtil.isEmpty(pattern)) {
			return getCurrentDateTime();
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(Calendar.getInstance().getTime());
		}
	}

	/**
	 * 根据日期格式获取时间
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getDateTime(Date date, String pattern) {
		if (CommonUtil.isEmpty(pattern)) {
			return getCurrentDateTime(DEFAULT_FORMAT_WITH_DATE_TIME);
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
	}

	/**
	 * 获取时间字符串,Format:yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateTime(Date date) {
		return DEFAULT_FORMAT_YMD_HMS.format(date);
	}

	/**
	 * 获取时间字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getDateTime(long date, String pattern) {
		if (CommonUtil.isEmpty(pattern)) {
			return DEFAULT_FORMAT_YMD_HMS.format(date);
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
	}

	/**
	 * 获取时间字符串,Format:yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateTime(long date) {
		return DEFAULT_FORMAT_YMD_HMS.format(date);
	}

	/**
	 * 获取当前的系统时间,Format:yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		return DEFAULT_FORMAT_YMD.format(Calendar.getInstance().getTime());
	}
	
	
	/**
	 * 获取当前的系统时间,Format:HH:mm:ss
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		return DEFAULT_FORMAT_YMD_HMS.format(Calendar.getInstance().getTime());
	}

	/**
	 * 当前系统时间的年份
	 * 
	 * @return
	 */
	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 当前系统时间的月份
	 * 
	 * @return
	 */
	public static int getCurrentModth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/**
	 * 当前系统时间的日
	 * 
	 * @return
	 */
	public static int getCurrentDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 字符串转日期,Format:yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static Date parse(String date) {
		Date dateTime = null;
		try {
			dateTime = DEFAULT_FORMAT_YMD_HMS.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}

	/**
	 * 字符串转日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date parse(String date, String pattern) {
		try {
			if (CommonUtil.isEmpty(pattern)) {
				return DEFAULT_FORMAT_YMD_HMS.parse(date);
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				return sdf.parse(date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取当前系统时间
	 * 
	 * @return
	 */
	public static Date getTime() {
		return Calendar.getInstance().getTime();
	}
	
	public static String getBeginOfDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return getDateTime(calendar.getTime());
	}
	public static String getEndOfDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return getDateTime(calendar.getTime());
	}
	/**
	 * 获取日期的开始时间
	 * @param date 日期（只接受yyyy-MM-dd格式）
	 * @return
	 */
	public static String getBeginOfDay(String date){
		Date parseDate = parse(date, DEFAULT_FORMAT_WITH_DATE);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return getDateTime(calendar.getTime());
	}
	/**
	 * 获取日期的结束时间
	 * @param date 日期（只接受yyyy-MM-dd格式）
	 * @return
	 */
	public static String getEndOfDay(String date){
		Date parseDate = parse(date, DEFAULT_FORMAT_WITH_DATE);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return getDateTime(calendar.getTime());
	}
	
	
	
}
