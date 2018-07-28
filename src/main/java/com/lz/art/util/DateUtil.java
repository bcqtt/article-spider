package com.lz.art.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
	private DateUtil(){}
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATE_FORMAT_YMD = "yyyy/MM/dd";
	public static final String DATE_MOUTH_FORMAT = "yyyy-MM";
	public static final String DATE_TIME_FORMAT = DATE_FORMAT + " HH:mm:ss SSS";
	public static final String ORA_DATE_TIME_FORMAT = DATE_FORMAT + " HH:mm:ss";
	public static final String ORA_DATE_TIME_FORMAT2 = DATE_FORMAT + " HH:mm";
	
	public static final String DATE_TIME_FORMAT_YMD = "yyyyMMdd";
	public static final String DATE_TIME_FORMAT_YMDH = DATE_TIME_FORMAT_YMD + "HH";
	public static final String DATE_TIME_FORMAT_YMDHM = DATE_TIME_FORMAT_YMD + "HHmm";
	public static final String DATE_TIME_FORMAT_MMDDHHmm= "MMddHHmm";
	public static final String DATE_TIME_FORMAT_HHMM = "HHmm";
	
	/**开始*/
	public static final String DATE_START_HH_MM_SS = " 00:00:00";
	/**结束***/
	public static final String DATE_END_HH_MM_SS = " 23:59:59";
	
	/**异常信息**/
	private static final String ERRORINFO="DateUtil parseDate error";
	
	
   /** 
    * 判断当前日期是星期几 1-7
    *  
    * @param pTime 修要判断的时间 
    * @return dayForWeek 判断结果 
 * @throws ParseException 
    * @Exception 发生异常 
    */  
	public static int dayOfWeek(String pTime) throws ParseException  {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(pTime));
		int dayForWeek;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}
	
	/**
	 * 返回星期几，0代表星期日,1代表星期一 作者:847792 ...
	 */
	public static String dayOfWeekChinese(String date) throws ParseException  {
	    Map<Integer,String> weekMap = new HashMap<>();
	    weekMap.put(7, "星期日");
	    weekMap.put(1, "星期一");
	    weekMap.put(2, "星期二");
	    weekMap.put(3, "星期三");
	    weekMap.put(4, "星期四");
	    weekMap.put(5, "星期五");
	    weekMap.put(6, "星期六");
		int week = dayOfWeek(date);
		return weekMap.get(week);
	}
	
	/**
	 * @param dateBgn 开始日期
	 * @param dateEnd 结束日期
	 * @return  日期间隔天数
	 * @throws ParseException
	 */
	public static int getIntervalDays(Date dateBgn, Date dateEnd){
		 return (int) ((dateEnd.getTime() - dateBgn.getTime()) / 86400000);
	}
	
	/**
	 * @param dateBgn 开始日期
	 * @param dateEnd 结束日期
	 * @return  日期间隔天数
	 * @throws ParseException
	 */
	public static int getIntervalDays(String dateBgn, String dateEnd) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		Date dateE = format.parse(dateEnd);
		Date dateB = format.parse(dateBgn);
        return (int) ((dateE.getTime() - dateB.getTime()) / 86400000);
	}
	
	/**
	 * @param dateBgn 开始日期
	 * @param dateEnd 结束日期
	 * @return  日期间隔分钟
	 * @throws ParseException
	 */
	public static long getIntervalMin(String dateBgn, String dateEnd, String format) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		
		Date end = dateFormat.parse(dateEnd);
		Date begin = dateFormat.parse(dateBgn);
		return (end.getTime() - begin.getTime())/1000/90;
	}
	
	/**
	 * 把日期往后增加n月. 整数往后推,负数往前移动 
	 * 
	 * @param dateStr
	 * @param interval
	 * @return
	 * @throws ParseException 
	 */
	public static String calcMonth(String dateStr, int interval) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(DATE_MOUTH_FORMAT);
		Calendar   calendar   =   new   GregorianCalendar(); 
		calendar.setTime(format.parse(dateStr)); 
		calendar.add(Calendar.MONTH, interval);
		
		return format.format(calendar.getTime());
	}
	
	
	/**
	 * 把日期往后增加n月. 整数往后推,负数往前移动 
	 * 
	 * @param dateStr
	 * @param interval
	 * @return
	 * @throws ParseException 
	 */
	public static String calcMonthDate(String dateStr, int interval) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		Calendar   calendar   =   new   GregorianCalendar(); 
		calendar.setTime(format.parse(dateStr)); 
		calendar.add(Calendar.MONTH, interval);
		
		return format.format(calendar.getTime());
	}
	/**
	 * 把日期往后增加n天. 整数往后推,负数往前移动 
	 * 
	 * @param dateStr
	 * @param interval
	 * @return
	 * @throws ParseException 
	 */
	public static String calcDate(String dateStr, int interval) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		Calendar   calendar   =   new   GregorianCalendar(); 
		calendar.setTime(format.parse(dateStr)); 
		calendar.add(Calendar.DATE, interval);
		
		return format.format(calendar.getTime());
	}
	
	/**
	 * 把日期往后增加n天. 整数往后推,负数往前移动 
	 * 
	 * @param dateStr
	 * @param interval
	 * @return
	 * @throws ParseException 
	 */
	public static Date calcDate(String dataStr, String format, int interval, int intervalType) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		
		Calendar   calendar   =   new   GregorianCalendar(); 
		calendar.setTime(dateFormat.parse(dataStr)); 
		calendar.add(intervalType, interval);
		
		return calendar.getTime();
	}
	
	/**
	 * 把日期往后增加n天. 整数往后推,负数往前移动 
	 * 
	 * @param dateStr
	 * @param interval
	 * @return
	 * @throws ParseException 
	 */
	public static Date calcDate(Date date, int interval, int intervalType) {
		Calendar   calendar   =   new   GregorianCalendar(); 
		calendar.setTime(date); 
		calendar.add(intervalType, interval);
		
		return calendar.getTime();
	}
	
	/**
	 * 把日期往后增加n天. 整数往后推,负数往前移动 
	 * 
	 * @param dateStr
	 * @param interval
	 * @return
	 * @throws ParseException 
	 */
	public static Date calcDate(String dateStr, int interval, int intervalType) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		Calendar   calendar   =   new   GregorianCalendar(); 
		calendar.setTime(format.parse(dateStr)); 
		calendar.add(intervalType, interval);
		
		return calendar.getTime();
	}
	
	public static int getBetweenDays(String t1, String t2)
			   throws ParseException {
			  DateFormat format = new SimpleDateFormat(DATE_FORMAT);
			  int betweenDays;
			  Date d1 = format.parse(t1);
			  Date d2 = format.parse(t2);
			  Calendar c1 = Calendar.getInstance();
			  Calendar c2 = Calendar.getInstance();
			  c1.setTime(d1);
			  c2.setTime(d2);
			  // 保证第二个时间一定大于第一个时间
			  if (c1.after(c2)) {
			   c1 = c2;
			   c2.setTime(d1);
			  }
			  int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
			  betweenDays = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
			  for (int i = 0; i < betweenYears; i++) {
			   c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
			   betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
			  }
			  return betweenDays;
	}

	/**
	 * 把日期往后增加n天. 整数往后推,负数往前移动 
	 * 
	 * @param dateStr
	 * @param interval
	 * @return
	 * @throws ParseException 
	 */
	public static String calcDate(Date date, int interval) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		Calendar calendar = new GregorianCalendar(); 
		calendar.setTime(date); 
		calendar.add(Calendar.DATE, interval);
		
		return format.format(calendar.getTime());
	}
	
	/**
	 * 返回当前时间对应的时间段值(10分钟一段)  yyyyMMddHHmm
	 * 	201605120920代表5.12日9:20:00~9:29:59
	 *  当前时间 201605120925
	 *      返回201605120920
	 * 
	 * @param date  
	 * @return
	 */
	public static String getTimeRangeByTenMin(Date date){
		String dateStr = DateUtil.format(date, DATE_TIME_FORMAT_YMDHM);
		//最后一位替换为0
		return dateStr.substring(0, dateStr.length() - 1) + "0";
	}
	
	public static String format(Date date) {
		return DateFormatUtils.format(date, DATE_TIME_FORMAT);
	}
	
	public static String format(Date date, String format) {
		return DateFormatUtils.format(date, format);
	}
	
	public static Date parseDate(String dateStr) {
		DateFormat dateFormat = new SimpleDateFormat(ORA_DATE_TIME_FORMAT);
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			logger.error(ERRORINFO, e);
		}
		return null;
	}
	
	public static Date parseDate(String dateStr, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		
		Date result = null;
		try {
			result = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			logger.error(ERRORINFO, e);
		}
		return result;
	}
	
	public static List<String> getTimesBetween(String beginDateStr, String endDateStr, String inputFormat, 
			String outFormat, int interval, int intervalType){
		
		List<String> rtn = new ArrayList<>();
		Date beginDate = parseDate(beginDateStr, inputFormat);
		Date endDate = parseDate(endDateStr, inputFormat);
		
		if(beginDate != null && beginDate.after(endDate)){
			return rtn;
		}
		
		//加入起始日期
		rtn.add(format(beginDate, outFormat));
		
		Date curDate = calcDate(beginDate, interval, intervalType);
		
		while(!curDate.after(endDate)){
			//加入起始日期
			rtn.add(format(curDate, outFormat));
			
			curDate = calcDate(curDate, interval, intervalType);
		}

		return rtn;
	}
	//判断某一天是当年的第几周
	public static int findWeekByDay(String date){
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);  
		Date date1 = null;  
		try {  
		    date1 = format.parse(date);  
		} catch (ParseException e) {  
			logger.error(e.getMessage());
		}  
		  
		Calendar calendar = Calendar.getInstance();  
		calendar.setFirstDayOfWeek(Calendar.MONDAY);  
		calendar.setTime(date1);  
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	//
	
	/**
	 * 判断是否是上个月的数据(不是精确判断)
	 * @param ymd
	 * @param now
	 * @return
	 */
	public static boolean isLastMonthData(String ymd){
		
		Date effectDate = DateUtil.parseDate(ymd, DATE_TIME_FORMAT_YMDHM);
				
		return DateUtil.getIntervalDays(effectDate, new Date()) >= 28;
	}
	
	/**
	 * 
	 * @param dateMouth
	 * @return
	 * @throws ParseException 
	 */
	public static String getMouthStartDay(String dateMouth) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(DATE_MOUTH_FORMAT);
		SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(format.parse(dateMouth));
		calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天 
		return format2.format(calendar.getTime());
	}
	
	/**
	 * 
	 * @param dateMouth
	 * @return
	 * @throws ParseException 
	 */
	public static String getMouthEndDay(String dateMouth) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(DATE_MOUTH_FORMAT);
		SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(format.parse(dateMouth));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));//设置为1号,当前日期既为本月第一天 
		return format2.format(calendar.getTime());
	}
	/**
	 * 获取多少天的开始时间  HH:mm:ss SSS -> 00:00:00.000
	 * @param n 多少天 负数为多少天前  正数为多少天后 0 为当天
	 * @return
	 */
	public static Date getStartByInterval(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, n);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	public static boolean after(Date date1,Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		return c1.after(c2);
	} 
	public static boolean before(Date date1,Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		return c1.before(c2);
	} 
	/**
	 * 判断日期是否为同一天
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean daysEquals(Date date1,Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		return c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR) && 
				c1.get(Calendar.MONTH)==c2.get(Calendar.MONTH) &&
						c1.get(Calendar.DAY_OF_MONTH)==c2.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
    *将字符串格式yyyyMMdd的字符串转为日期，格式"yyyy-MM-dd"
    *
    * @param date 日期字符串
    * @return 返回格式化的日期
    * @throws ParseException 分析时意外地出现了错误异常
    */
	public static String strToDateFormat(String date){
       try{
		   SimpleDateFormat formatter = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT_YMD);
	       formatter.setLenient(false);
	       Date newDate= formatter.parse(date);
	       formatter = new SimpleDateFormat(DateUtil.DATE_FORMAT);
	       return formatter.format(newDate);
       }catch(Exception e){
    	   logger.error("ESMap to List: Date format exption",e);
       }
       return null;
	}
	/**
	 * 将长整型数字转换为日期格式的字符串
	 * @param time
	 * @param format
	 * @return
	 */
	public static String format(long time) {
		return format(time,ORA_DATE_TIME_FORMAT);
	}
	/**
	 * 将长整型数字转换为日期格式的字符串
	 * @param time
	 * @param format
	 * @return
	 */
	public static String format(long time, String format) {
	   if (time <= 0l) {
		  return "";
	   }
	   if(StringUtils.isEmpty(format)){
		  format = ORA_DATE_TIME_FORMAT;
	   }
	   SimpleDateFormat sf = new SimpleDateFormat(format);
	   return sf.format(new Date(time));
	}
	
	/***
	 * long字符串转为时间格式
	 * @param longStr
	 * @return
	 * Date
	 */
	public static Date longStrToDate(String longStr){
		return longStrToDate(longStr,ORA_DATE_TIME_FORMAT);
	}
	
	/***
	 * long字符串转为时间格式
	 * @param longStr
	 * @return
	 * Date
	 */
	public static Date longStrToDate(String longStr,String format){
		if(StringUtils.isEmpty(longStr)){
			return null;
		}
		long time=0L;
		try{
			time=Long.parseLong(longStr);
		}catch(Exception e){
			logger.error("DateUtil String parse to Long error", e);
			return null;
		}
		if(time<=0L){
			return null;
		}
		String strDate=format(time,format);
		if(StringUtils.isEmpty(strDate)){
			return null;
		}
		DateFormat dateFormat = new SimpleDateFormat(format);
		try {
			return dateFormat.parse(strDate);
		} catch (ParseException e) {
			logger.error(ERRORINFO, e);
		}
		return null;
	}
	
	/**
     * Java将Unix时间戳转换成指定格式日期字符串
     * @param timestampString 时间戳 如："1473048265";
     * @param formats 要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     *
     * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static String unixTimeStamp2DateStr(String timestampString, String formats) {
        if (StringUtils.isEmpty(formats)){
        	 formats = ORA_DATE_TIME_FORMAT;
        }
        Long timestamp = Long.parseLong(timestampString) * 1000;
        if(timestamp<=0L){
			return null;
		}
        return new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
    }
    
    /**
     * Java将Unix时间戳转换成指定格式日期字符串
     * @param timestampString 时间戳 如："1473048265";
     * @param formats 要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     *
     * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static String unixTimeStamp2DateStr(String timestampString) {
        return unixTimeStamp2DateStr(timestampString,ORA_DATE_TIME_FORMAT);
    }
    
    /**
     * Java将Unix时间戳转换成指定格式日期字符串
     * @param timestampString 时间戳 如："1473048265";
     * @param formats 要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     *
     * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static Date unixTimeStamp2Date(String timestampString,String formats) {
    	if (StringUtils.isEmpty(formats)){
       	 	formats = ORA_DATE_TIME_FORMAT;
        }
    	String strDate=unixTimeStamp2DateStr(timestampString,formats);
    	if(StringUtils.isEmpty(strDate)){
			return null;
		}
		DateFormat dateFormat = new SimpleDateFormat(formats);
		try {
			return dateFormat.parse(strDate);
		} catch (ParseException e) {
			logger.error(ERRORINFO, e);
		}
		return null;
    }
    
    /**
     * Java将Unix时间戳转换成指定格式日期字符串
     * @param timestampString 时间戳 如："1473048265";
     * @param formats 要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     *
     * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static Date unixTimeStamp2Date(String timestampString) {
    	return unixTimeStamp2Date(timestampString,ORA_DATE_TIME_FORMAT);
    }
    
    
    /***
     * 时间串转为long字符串
     * @param dateStr
     * @return
     * long
     */
    public static String date2UnixTimeStamp(String dateStr) {
    	return date2UnixTimeStamp(dateStr,ORA_DATE_TIME_FORMAT);
    }
    
    /***
     * 时间串转为long字符串
     * @param dateStr
     * @param format
     * @return
     * long
     */
    public static String date2UnixTimeStamp(String dateStr,String format) {
    	if(StringUtils.isEmpty(dateStr)){
    		return "";
    	}
    	if(StringUtils.isEmpty(format)){
    		format = ORA_DATE_TIME_FORMAT;
    	}
    	try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        } catch (Exception e) {
            logger.error("parse dateStr to long exception",e);
        }
    	return "";
    }
    
    /**
     * 功能：判断字符串是否为日期格式
     * 
     * @param str
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
}
