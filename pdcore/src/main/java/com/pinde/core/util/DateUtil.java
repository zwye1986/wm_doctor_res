package com.pinde.core.util;

import com.pinde.core.common.GlobalConstant;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;

public class DateUtil { 
	//缺省日期格式
    public static String defDtPtn01 = "yyyyMMddHHmmss";
	public static String defDtPtn02 = "yyyy-MM-dd HH:mm:ss";
	public static String defDtPtn04 = "yyyy-MM-dd";
//	private static FastDateFormat ISO_DATE_FORMAT = DateFormatUtils.ISO_DATE_FORMAT;
	
	/**
	 * 格式化日期对象
	 * @param date 日期对象
	 * @param pattern 日期格式
	 * @return 格式化后的日期字符串
	 */
	public static String formatDate(java.util.Date date, String pattern) {
		SimpleDateFormat DATEFORMAT = new SimpleDateFormat(pattern);
		return DATEFORMAT.format(date);
	}

	//字符串转换成Date
//	public static Date stringFormateToDate(String str){
//		SimpleDateFormat sdf=new SimpleDateFormat(defDtPtn02);//小写的mm表示的是分钟  
//		java.util.Date date=null;
//		try {
//			date = sdf.parse(str);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} 
//		return date;
//	}
	
	//字符串转换成Date
//	public static Date stringFormateToDate2(String str){
//		SimpleDateFormat sdf=new SimpleDateFormat(defDtPtn03);//小写的mm表示的是分钟  
//		java.util.Date date=null;
//		try {
//			date = sdf.parse(str);
//			
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} 
//		return date;
//	}
	//Date转换成字符串
//	public static String dateFormateToString(Date date){
//		SimpleDateFormat sdf=new SimpleDateFormat(defDtPtn02);  
//		String str=sdf.format(date);
//		return str;
//	}
	
	//Date转换成字符串
//	public static String dateFormateToString2(Date date){
//		SimpleDateFormat sdf=new SimpleDateFormat(defDtPtn03);  
//		String str=sdf.format(date);
//		return str;
//	}
	/*
	 * 获取一周前时间：
	 */
//	public static String getBeforeOneWeekToString(){
//		Calendar cal=Calendar.getInstance(); 
//        cal.add(Calendar.WEEK_OF_MONTH, -1);
////		cal.add(Calendar.DATE, -1);    //得到前一天 
////		cal.add(Calendar.MONTH, -1);    //得到前一个月 
//		long date = cal.getTimeInMillis(); 
//		String OneWeek=DateFormatUtils.format(date,"yyyy-MM-dd");
//        return OneWeek;
//
//	}
	
	/*
	 * 获取一周前时间：
	 */
	public static int weekOfMonth(String date){
		Calendar cal=Calendar.getInstance(); 
		cal.setTime(parseDate(date, defDtPtn04));
		return cal.get(Calendar.WEEK_OF_MONTH);
	}
	

	
	/*
	 * 获取一周前时间：
	 */
	public static int weekOfYear(String date){
		Calendar cal=Calendar.getInstance(); 
		cal.setTime(parseDate(date, defDtPtn04));
		return cal.get(Calendar.WEEK_OF_YEAR);
	}
	/**
	 * 格式化日期对象，使用缺省的格式
	 * @param date 日期对象
	 * @return 格式化后的日期字符串
	 */
//	public static String formatDate(java.util.Date date) {
//		SimpleDateFormat DATEFORMAT = new SimpleDateFormat(defDtPtn);
//		return DATEFORMAT.format(date);
//	}
	/**
	 *取得当前系统时间：具体格式为yyyyMMddHHmmss
	 */
	public static String getCurrDateTime2() {
		return DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss");
	}

	/**
	 *取得当前系统时间：具体格式为yyyyMMddHHmmss
	 */
	public static String getCurrDateTime() {
		return DateFormatUtils.format(System.currentTimeMillis(),"yyyyMMddHHmmss");
	}
	
	/**
	 *取得当前系统时间：具体格式为yyyyMMddHHmmssSSS
	 */
	public static String getCurrentTime() {
		return DateFormatUtils.format(System.currentTimeMillis(),"yyyyMMddHHmmssSSS");
	}
	
	/**
	 *取得当前系统时间：具体格式为yyyyMMddHHmmss
	 */
	public static String getCurrDateTime(String ftm) {
		return DateFormatUtils.format(System.currentTimeMillis(),ftm);
	}

	/**
	 *取得当前日期 ：具体格式为yyyy-MM-dd
	 */
	public static String getCurrDate() {
		return DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd");
	}

	/**
	 * 获得当月第一天
	 */

	public static String getFirstDayOfMonth(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar   cal_1 = Calendar.getInstance();//获取当前日期
		cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		String  firstDay = format.format(cal_1.getTime());
		return firstDay;
	}
	public static String getLastDateOfCurrMonth(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar   cal_1 = Calendar.getInstance();//获取当前日期
		cal_1.set(Calendar.DAY_OF_MONTH, cal_1.getActualMaximum(Calendar.DATE));//设置最后一天
		String  firstDay = format.format(cal_1.getTime());
		return firstDay;
	}

	public static String setFirstDayOfMonth(String time){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar   cal_1 = Calendar.getInstance();//获取当前日期
		try {
			cal_1.setTime(format.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		String  firstDay = format2.format(cal_1.getTime());
		return firstDay;
	}
	public static String setLastDateOfMonth(String time){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar   cal_1 = Calendar.getInstance();//获取当前日期
		try {
			cal_1.setTime(format.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal_1.set(Calendar.DAY_OF_MONTH, cal_1.getActualMaximum(Calendar.DATE));//设置最后一天
		String  firstDay = format2.format(cal_1.getTime());
		return firstDay;
	}

	/**
	 * 给定月份的最后一天
	 * vari 格式为****-**(如：2017-01)
	 * @return
     */
	public static String getLastDateOfMonth(String vari){
		Calendar cal = Calendar.getInstance();
		int year = Integer.parseInt(vari.split("-")[0]);
		int month = Integer.parseInt(vari.split("-")[1]);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		// 某年某月的最后一天
		return cal.getActualMaximum(Calendar.DATE) + "";
	}
	//获取当前时间星期几
	public static String getDayOfWeek(){
		Calendar c = Calendar.getInstance();
		String week_str = "";
		int num = c.get(c.DAY_OF_WEEK);
		switch (num) {
			case 1:
				week_str="星期日";
				break;
			case 2:
				week_str="星期一";
				break;
			case 3:
				week_str="星期二";
				break;
			case 4:
				week_str="星期三";
				break;
			case 5:
				week_str="星期四";
				break;
			case 6:
				week_str="星期五";
				break;
			case 7:
				week_str="星期六";
				break;
		}
		return week_str;
	}
	/**
	 *取得当前日期 ：具体格式为yyyyMMdd
	 */
	public static String getCurrDate2() {
		return DateFormatUtils.format(System.currentTimeMillis(),"yyyyMMdd");
	}
	
	/**
	 *取得当前时间：具体格式为HHmmss
	 */
	public static String getCurrTime() {
		return DateFormatUtils.format(System.currentTimeMillis(),"HHmmss");
	}
	
	
	/**
	 *解析字符串
	 * @param dateStr
	 * @return 返回日期对象
	 */
	public static java.util.Date parseDate(String dateStr, String pattern) {		
		try {
			return DateUtils.parseDate(dateStr,new String[]{pattern});
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * Method calculateAge 根据出生日期计算年龄.
	 * @param birthdayStr
	 * @return String
	 */
	public static String calculateAge(String birthdayStr) {
		birthdayStr = StringUtil.defaultString(birthdayStr);
		Date birthday = null;
		if (birthdayStr.length()==8) {
			birthday = parseDate(birthdayStr, "yyyyMMdd");			
		}
		if (birthdayStr.length()==10) {
			birthday = parseDate(birthdayStr, "yyyy-MM-dd");			
		}
		if (birthdayStr.length()==7) {
			birthday = parseDate(birthdayStr, "yyyy-MM");
		}
		if (birthday != null) {
			Calendar calendar = Calendar.getInstance();
			int year1 = calendar.get(Calendar.YEAR);
			calendar.setTime(birthday);
			int year2 = calendar.get(Calendar.YEAR);
			int age = year1 - year2;
			return String.valueOf(age);
		}
		return "";
	}
	
	/**
	 * Method transDateTime.
	 * @param srcDateTime
	 * @return String
	 */
	public static String  transDateTime(String srcDateTime) {
		srcDateTime = StringUtil.defaultString(srcDateTime).trim();
		if (srcDateTime.length() == 17)
			return transDateTime(srcDateTime,"yyyyMMddHHmmssSSS","yyyy-MM-dd HH:mm:ss");
		if (srcDateTime.length() == 14)
			return transDateTime(srcDateTime,"yyyyMMddHHmmss","yyyy-MM-dd HH:mm:ss");
		if (srcDateTime.length() == 12)
			return transDateTime(srcDateTime,"yyyyMMddHHmm","yyyy-MM-dd HH:mm");
		if (srcDateTime.length() == 8)
			return transDateTime(srcDateTime,"yyyyMMdd","yyyy-MM-dd");
		if(srcDateTime.length() == 16)
			return transDateTime(srcDateTime,"yyyyMMddHHmmss.S","yyyy-MM-dd HH:mm:ss");
		return srcDateTime;
	}
	
	/**
	 * Method transDate.
	 * @param srcDate
	 * @return String
	 */
	public static String transDate(String srcDate) {
		srcDate = StringUtil.defaultString(srcDate).trim();
		if (srcDate.length() == 14)
			return transDateTime(srcDate,"yyyyMMddHHmmss","yyyy-MM-dd");
		if (srcDate.length() == 12)
			return transDateTime(srcDate,"yyyyMMddHHmm","yyyy-MM-dd");
		if (srcDate.length() == 8)
			return transDateTime(srcDate,"yyyyMMdd","yyyy-MM-dd");
		return srcDate;
	}
	
	/**
	 * Method transDate.
	 * @param srcDate
	 * @return String
	 */
	public static String transDate(String srcDate , String pattern) {
		srcDate = StringUtil.defaultString(srcDate).trim();
		if (srcDate.length() == 14)
			return transDateTime(srcDate,"yyyyMMddHHmmss",pattern);
		if (srcDate.length() == 12)
			return transDateTime(srcDate,"yyyyMMddHHmm",pattern);
		if (srcDate.length() == 8)
			return transDateTime(srcDate,"yyyyMMdd",pattern);
		return srcDate;
	}

	public static String transTime(String srcTime) {
		srcTime = StringUtil.defaultString(srcTime).trim();
		if (srcTime.length() == 14)
			return transDateTime(srcTime,"yyyyMMddHHmmss","HH:mm:ss");
		if (srcTime.length() == 12)
			return transDateTime(srcTime,"yyyyMMddHHmm","HH:mm:ss");
		if (srcTime.length() == 6)
			return transDateTime(srcTime, "HHmmss", "HH:mm:ss");
		return srcTime;
	}	
	
	/**
	 * 将日期时间从一种格式转换为另一种格式
	 * @param srcDateTime 源串
	 * @param srcPattern	源串格式
	 * @param destPattern 目标串格式
	 * @return String 目标串
	 */
	public static String transDateTime(String srcDateTime,	String srcPattern,	String destPattern) {
		srcDateTime = StringUtil.defaultString(srcDateTime).trim();
		try {
			srcDateTime = FastDateFormat.getInstance(destPattern).format(parseDate(srcDateTime, srcPattern));
		} catch (Exception exp) {
		}
		return srcDateTime;
	}

	public static String getDateTime(String s) {
		return transDateTime(s, "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss");
	}
	public static String getDate(String s) {
		return transDateTime(s, "yyyy-MM-dd", "yyyyMMdd");
	}

	public static String getTime(String time) {
		return transDateTime(time, "HH:mm:ss", "HHmmss");
	}
	/**
	 * 获取当前年份
	 * @return
	 */
	public static String getYear(){
		return DateFormatUtils.format(System.currentTimeMillis(),"yyyy");
	}

	/**
	 * 根据月份条件获取年份
	 * @param year
	 * @return
     */
	public static String getYearByMonth(){
		//当前年份
		String currentYear = getYear();
		//根据当前系统月份判断，若>9月份，则年级取当前。若<9月份，取当前年级-1。
		String m = DateUtil.getMonth().replace("-",""); //当前系统月份
		String dm = DateUtil.getYear()+"09";//指定9月份
		if(m.compareTo(dm)<0){
			int newYear = Integer.parseInt(currentYear)-1;
			currentYear =  newYear+"";
		}
		return currentYear;
	}
	/**
	 * 获取当前月份
	 * @return
	 */
	public static String getMonth(){
		return DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM");
	}
	/**
	 * @param startDate  String 格式yyyy-mm-dd dd exact=true时，只能为1 or 16
	 * @param step 天数 只能为整数或小数部分0.5
	 * @return String yyyy-MM-dd
	 * */
	public static String addMonthForArrange(String startDate,String step,boolean exact){
		if(exact==false){
			//计算出整数部分或小数部分
			float f = Float.parseFloat(step);
			int mInt = (int) f;
			BigDecimal b1 = new BigDecimal(Float.toString(f));
			BigDecimal b2 = new BigDecimal(Integer.toString(mInt));
			int mFloat = (int)(b1.subtract(b2).floatValue()*10.0f);
			if(mFloat!=0 && mFloat !=5){
				throw new RuntimeException("step is not a vaild format with float:"+mFloat);
			}
			String endDate = startDate;
			
			Calendar c = Calendar.getInstance();//获得一个日历的实例
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date date = null;
		    try{
		         date = sdf.parse(endDate);
		       }catch(Exception e){
		    	   e.printStackTrace();
		       }
		    c.setTime(date);//设置日历时间
		    c.add(Calendar.MONTH,mInt);	//增加的月数
		    endDate = sdf.format(c.getTime());
			
			if(mFloat==5){
				endDate = DateUtil.addDate(endDate,15);
			}
			endDate = addDate(endDate,-1);
			return endDate;
		
		}else{
			//计算出整数部分或小数部分
			float f = Float.parseFloat(step);
			int mInt = (int) f;
			BigDecimal b1 = new BigDecimal(Float.toString(f));
			BigDecimal b2 = new BigDecimal(Integer.toString(mInt));
			int mFloat = (int)(b1.subtract(b2).floatValue()*10.0f);
			if(mFloat!=0 && mFloat !=5){
				throw new RuntimeException("step is not a vaild format with float:"+mFloat);
			}
			if(mInt==0 && mFloat == 0){
				throw new RuntimeException("step is not a vaild format with:"+step);
			}
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.defDtPtn04);
			Date date;
			try {
				date = simpleDateFormat.parse(startDate);
		        Calendar calender = Calendar.getInstance();
		        calender.setTime(date);
		        
		        int mStartDate = calender.get(Calendar.DATE);
		        if(mStartDate!=1 && mStartDate !=16){
					throw new RuntimeException("startDate is not a vaild format with date:"+mStartDate+",date must be 01 or 16");
				}	 
		        if(mStartDate==1){
		        	if(mFloat==0){
			        	//上个月最后一天
			        	if(mInt>0){
				        	calender.add(Calendar.MONTH, mInt-1);
					        calender.roll(Calendar.DATE, -1);	        		
			        	}
			        }else if(mFloat==5){
			        	calender.add(Calendar.MONTH, mInt);
			        	calender.set(Calendar.DATE, 15);
			        }
		        }else if(mStartDate==16){
		        	if(mFloat==0){
		        		if(mInt>0){
			        		calender.add(Calendar.MONTH, mInt);
				        	calender.set(Calendar.DATE, 15);
		        		}
			        }else if(mFloat==5){
			        	if(mInt>0){
			        		calender.add(Calendar.MONTH, mInt);
			        	}
			        	calender.set(Calendar.DATE, 1);
				        calender.roll(Calendar.DATE, -1);
			        }
		        }
		        
		        String endDate = simpleDateFormat.format(calender.getTime());
				return endDate;
			} catch (ParseException e) {
				throw new RuntimeException("startDate is not a vaild format "+DateUtil.defDtPtn04);
			}
		}
	}
	
	/**
	 * @param endDate  String 格式yyyyMMddHHmmss 
	 * @param step 分钟数 只能为整数
	 * @return String yyyyMMddHHmmss
	 * */
	public static String addMinute(String endDate,int step){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.defDtPtn01);
		Date date;
		try {
			date = simpleDateFormat.parse(endDate);
	        Calendar calender = Calendar.getInstance();
	        calender.setTime(date);
	        calender.add(Calendar.MINUTE, step);		 
	        
	        String startDate = DateUtil.formatDate(calender.getTime(), DateUtil.defDtPtn01); 
	        return startDate;
		} catch (ParseException e) {
			throw new RuntimeException("endDate is not a vaild format "+DateUtil.defDtPtn01);
		}
	}
	
	/**
	 * @param endDate  String 格式yyyyMMddHHmmss 
	 * @param step 小时数 只能为整数
	 * @return String yyyyMMddHHmmss
	 * */
	public static String addHour(String endDate,int step){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.defDtPtn01);
		Date date;
		try {
			date = simpleDateFormat.parse(endDate);
	        Calendar calender = Calendar.getInstance();
	        calender.setTime(date);
	        calender.add(Calendar.HOUR, step);		 
	        
	        String startDate = DateUtil.formatDate(calender.getTime(), DateUtil.defDtPtn01); 
	        return startDate;
		} catch (ParseException e) {
			throw new RuntimeException("endDate is not a vaild format "+DateUtil.defDtPtn01);
		}
	}
	
	/**
	 * @param endDate  String 格式yyyy-mm-dd 
	 * @param step 天数 只能为整数
	 * @return String yyyy-MM-dd
	 * */
	public static String addDate(String endDate,int step){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.defDtPtn04);
		Date date;
		try {
			date = simpleDateFormat.parse(endDate);
	        Calendar calender = Calendar.getInstance();
	        calender.setTime(date);
	        calender.add(Calendar.DATE, step);		 
	        
	        String startDate = simpleDateFormat.format(calender.getTime()); 
	        return startDate;
		} catch (ParseException e) {
			throw new RuntimeException("endDate is not a vaild format "+DateUtil.defDtPtn04);
		}
	}
	/**
	 * @param startYear  String 格式yyyy-mm
	 * @param step 天数 只能为整数
	 * @return String yyyy-MM
	 * */
	public static String addYear(String startYear,int step){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.defDtPtn04);
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = simpleDateFormat.parse(startYear);
	        Calendar calender = Calendar.getInstance();
	        calender.setTime(date);
	        calender.add(Calendar.YEAR, step);		 
	        
	        String endMonth = simpleDateFormat2.format(calender.getTime()); 
	        return endMonth;
		} catch (ParseException e) {
			throw new RuntimeException("startMonth is not a vaild format yyyy-MM");
		}
	}
	
	/**
	 * @param endDate  String 格式yyyy-mm
	 * @param step 天数 只能为整数
	 * @return String yyyy-MM
	 * */
	public static String addMonth(String startMonth,int step){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.defDtPtn04);
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM");
		Date date;
		try {
			startMonth = startMonth + "-01";
			date = simpleDateFormat.parse(startMonth);
	        Calendar calender = Calendar.getInstance();
	        calender.setTime(date);
	        calender.add(Calendar.MONTH, step);		 
	        
	        String endMonth = simpleDateFormat2.format(calender.getTime()); 
	        return endMonth;
		} catch (ParseException e) {
			throw new RuntimeException("startMonth is not a vaild format yyyy-MM");
		}
	}

	/**
	 * @param dateTime  String 格式yyyy-mm-dd hh:mm:ss.fffffffff/yyyy-mm-dd
	 * @param 天数
	 * @param 格式选择 format
	 * @return String yyyy-MM-dd HH:mm:ss
	 * */
//	public static String newDateOfAddDays(String dateTime,int p_days,String format){
//		String newDateTime="";
//		if(dateTime!=null&&!"".equals(dateTime)){
//			if(dateTime.length()==10){
//				dateTime+=" 00:00:00";
//			}
//			java.sql.Timestamp dd1 = null;
//			try {
//				dd1= java.sql.Timestamp.valueOf(dateTime);
//				dd1 = newDateOfAddDays(dd1, p_days);
//				SimpleDateFormat DATEFORMAT = new SimpleDateFormat(format);
//				newDateTime=DATEFORMAT.format(dd1);
//			} catch (Exception ex) {
//				newDateTime = "";
//			}
//		}
//		return newDateTime;
//	}
	/**
	 * Method newDateOfAddDays 得到指定日期后指定天数的日期.
	 * @param p_date 当前日期 格式yyyy-mm-dd hh:mm:ss.fffffffff
	 * @param p_days 天数
	 * @return Date 得到的日期
	 */
//	public static java.sql.Timestamp newDateOfAddDays(
//		java.sql.Timestamp p_date,
//		int p_days) {
//		long dateValue = 0L;
//		long daysValue = 0L;
//		java.sql.Timestamp newDate = null;
//
//		dateValue = p_date.getTime()/1000;
//		daysValue = p_days * (3600 * 24);
//
//		dateValue += daysValue;
//		newDate = new java.sql.Timestamp(dateValue * 1000);
//
//		return newDate;
//	}

	/**
	 * Method newDateOfAddDays 得到指定日期后指定天数的日期.
	 * @param p_date 指定日期
	 * @param p_days 指定天数
	 * @return Date 得到的日期
	 */
//	public static java.util.Date newDateOfAddDays(
//		java.util.Date p_date,
//		int p_days) {
//		long dateValue = 0L;
//		long daysValue = 0L;
//		java.util.Date newDate = null;
//
//		dateValue = p_date.getTime();
//		daysValue = p_days * (3600 * 24 * 1000);
//
//		dateValue += daysValue;
//		newDate = new java.util.Date(dateValue);
//
//		return newDate;
//	}

	/** 得到指定日期前指定天数的日期.
	 * @param p_date 指定日期
	 * @param p_days 指定天数
	 * @return
	 */
//	public static java.util.Date newDateOfMinusDays(
//			java.util.Date p_date,
//			int p_days) {
//			long dateValue = 0L;
//			long daysValue = 0L;
//			java.util.Date newDate = null;
//
//			dateValue = p_date.getTime();
//			daysValue = p_days * (3600 * 24 * 1000);
//
//			dateValue -= daysValue;
//			newDate = new java.util.Date(dateValue);
//
//			return newDate;
//		}


//	public static String newDateOfAddHours(String dateTime,int p_hours){
//		String newDateTime="";
//		if(dateTime!=null&&!"".equals(dateTime)){
//
//			java.sql.Timestamp dd1 = null;
//			try {
//				dd1= java.sql.Timestamp.valueOf(dateTime);
//				dd1 = newDateOfAddHours(dd1, p_hours);
//				SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
//				newDateTime=DATEFORMAT.format(dd1);
//			} catch (Exception ex) {
//				newDateTime = "";
//			}
//		}
//		return newDateTime;
//	}
//
//	public static java.sql.Timestamp newDateOfAddHours(
//			java.sql.Timestamp p_date,
//			int p_hours) {
//		long dateValue = 0L;
//		long hoursValue = 0L;
//		java.sql.Timestamp newDate = null;
//
//		dateValue = p_date.getTime();
//		hoursValue = p_hours * (3600 * 1000);
//		dateValue += hoursValue;
//		newDate = new java.sql.Timestamp(dateValue);
//		return newDate;
//	}
//	public static String newDateOfAddMinutes(String dateTime,int p_hours){
//		String newDateTime="";
//		if(dateTime!=null&&!"".equals(dateTime)){
//			java.sql.Timestamp dd1 = null;
//			try {
//				dd1= java.sql.Timestamp.valueOf(dateTime);
//				dd1 = newDateOfAddMinutes(dd1, p_hours);
//				SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
//				newDateTime=DATEFORMAT.format(dd1);
//			} catch (Exception ex) {
//				newDateTime = "";
//			}
//		}
//		return newDateTime;
//	}
//
//	public static java.sql.Timestamp newDateOfAddMinutes(
//			java.sql.Timestamp p_date,
//			int p_minutes) {
//		long dateValue = 0L;
//		long minutesValue = 0L;
//		java.sql.Timestamp newDate = null;
//
//		dateValue = p_date.getTime();
//		minutesValue = p_minutes * (60 * 1000);
//
//		dateValue += minutesValue;
//		newDate = new java.sql.Timestamp(dateValue);
//
//		return newDate;
//	}

	/**
	 * 两日期间相差月数.
	 * @param d1	日期字符串	2018-09-01
	 * @param d2	日期字符串	2018-10-01
	 * @return
	 */
	public static long signMonthBetweenTwoMonth(String d1,String d2){
		Temporal temporal1 = LocalDate.parse(d1);
		Temporal temporal2 = LocalDate.parse(d2);
		// 方法返回为相差月份
		long num = ChronoUnit.MONTHS.between(temporal1, temporal2);
		return num;
	}

	/**
	 * Method signDaysBetweenTowDate 两日期间相差天数.
	 * @param d1 日期字符串
	 * @param d2 日期字符串
	 * @return long 天数
	 */
	public static long signDaysBetweenTowDate(String d1, String d2) {
		java.sql.Date dd1 = null;
		java.sql.Date dd2 = null;
		long result = -1l;
		try {
			dd1 = java.sql.Date.valueOf(d1);
			dd2 = java.sql.Date.valueOf(d2);
			result = signDaysBetweenTowDate(dd1, dd2);
		} catch (Exception ex) {
			result = -1;
		}
		return result;
	}



	/**
	 * Method signDaysBetweenTowDate 两日期间相差天数.
	 * @param d1 开始日期 日期型
	 * @param d2 开始日期 日期型
	 * @return long 天数
	 */
	public static long signDaysBetweenTowDate(
			java.sql.Date d1,
			java.sql.Date d2) {
		return (d1.getTime() - d2.getTime()) / (3600 * 24 * 1000);
	}
	/**
	 * Method signDaysBetweenTowDate 两日期间相差天数.
	 * @param d1 开始日期 日期型
	 * @param d2 开始日期 日期型
	 * @return long 天数
	 */
	public static long signDaysBetweenTowDate(
			Date d1,
			Date d2) {
		return (d1.getTime() - d2.getTime()) / (3600 * 24 * 1000);
	}

	/**
	 * Method signDaysBetweenTowDate 两日期间相差秒数.
	 * @param d1 日期字符串
	 * @param d2 日期字符串
	 * @return long 秒数
	 */
	public static long signSecondsBetweenTowDate(String d1, String d2) {
		java.sql.Timestamp dd1 = null;
		java.sql.Timestamp dd2 = null;
		long result = -1l;
		try {
			dd1 = java.sql.Timestamp.valueOf(d1);
			dd2 = java.sql.Timestamp.valueOf(d2);
			result = signSecondsBetweenTowDate(dd1, dd2);
		} catch (Exception ex) {
			System.err.println(ex.toString());
			result = -1;
		}
		return result;
	}

	/**
	 * Method signDaysBetweenTowDate 两日期间相差天数.
	 * @param d1 开始日期 日期型
	 * @param d2 开始日期 日期型
	 * @return long 秒数
	 */
	public static long signSecondsBetweenTowDate(
			java.sql.Timestamp d1,
			java.sql.Timestamp d2) {
		return (d1.getTime() - d2.getTime()) / (1000);
	}

	/**
	 * 计算两个日期相隔的分钟数
	 * @param d1 小的时间
	 * @param d2 大的时间
	 * @return
	 */
	public static long signMinutesBetweenTowDate(String d1,String d2) {
		java.sql.Timestamp dd1 = null;
		java.sql.Timestamp dd2 = null;
		long result = -1l;
		try {
			dd1 = java.sql.Timestamp.valueOf(d1);
			dd2 = java.sql.Timestamp.valueOf(d2);
			result = signMinutesBetweenTowDate(dd1, dd2);
		} catch (Exception ex) {
			ex.printStackTrace();
			result = -1;
		}
		return result;
	}

	/**
	 * 计算两个日期相隔的分钟数
	 * @param d1 小的时间
	 * @param d2 大的时间
	 * @return
	 */
	public static long signMinutesBetweenTowDate(Date d1,Date d2) {
		long ms = d2.getTime()-d1.getTime();
		return ms/(1000*60);
	}
	/**
	 * 格式化分钟
	 * @param minutes
	 * @return
	 */
	public static String formatMinutes(long minutes){
		long hour = minutes / 60;
		long ms = minutes % 60;
		if(ms==0){
			return hour+"小时";
		}
		return hour+"小时"+ms+"分";
	}
	/**
	 * 计算指定时间相隔的天、小时、分钟
	 * @param d1 较晚时间（格式：yyyyMMddHHmmss）
	 * @param d2 较早时间（格式：yyyyMMddHHmmss）
	 * @return
	 */
	public static String signBetweenTowDate(String s1,String s2) {
		Date d1 = parseDate(s1, "yyyyMMddHHmmss");
		Date d2 = parseDate(s2, "yyyyMMddHHmmss");
		BigDecimal time=new BigDecimal(d1.getTime() - d2.getTime());
		BigDecimal minDivisor=new BigDecimal(60000);
		BigDecimal hourDivisor=new BigDecimal(3600000);
		BigDecimal dayDivisor=new BigDecimal(86400000);
		BigDecimal monthDivisor=new BigDecimal(2592000000L);
		BigDecimal yearDivisor=new BigDecimal(31104000000L);
		BigDecimal year=time.divide(yearDivisor,0,BigDecimal.ROUND_DOWN);
		BigDecimal month=time.divide(monthDivisor,0,BigDecimal.ROUND_DOWN);
		BigDecimal day=time.divide(dayDivisor,0,BigDecimal.ROUND_DOWN);
		BigDecimal hour=time.divide(hourDivisor,0,BigDecimal.ROUND_DOWN);
		BigDecimal minutes=time.divide(minDivisor,0,BigDecimal.ROUND_DOWN);
		if(year.intValue()>0){
			return year.toString()+"年前";
		}else if(month.intValue()>0){
			return month.toString()+"个月前";
		}else if(day.intValue()>0){
			return day.toString()+"天前";
		}else if(hour.intValue()>0){
			return hour.toString()+"小时前";
		}else if(minutes.intValue()>0){
			return minutes.toString()+"分钟前";
		}else{
			return "刚刚";
		}

	}
//
//
//	public static String secondsToHourMin(int s ) {
//	 	int hour = s/3600;
//		int min = (s-(hour*3600))/60;
//		if(min == 0) min = 1;
//		return hour + ":" + min;
//	}

	/**
	 * 计算某天是星期几
	 * @param p_date 日期字符串
	 * @return
	 */
	public static int whatDayIsSpecifyDate(String p_date) {
		//2002-2-22 is friday5
		long differenceDays = 0L;
		long m = 0L;
		differenceDays = signDaysBetweenTowDate(p_date, "2002-02-22");

		m = (differenceDays % 7);
		m = m + 5;
		m = m > 7 ? m - 7 : m;
		return Integer.parseInt(m + "");
	}

	/**
	 * 根据日期计算是星期几
	 * @param date 日期格式
	 * @param style 星期显示样式 "2":周几，“3”星期几
	 * @return
	 */
	public static String getWeekFromDate(String date,String style){
		String week_str = null ;
		int week_int = DateUtil.whatDayIsSpecifyDate(date);
		if("2".equals(style)){
			switch (week_int) {
				case 1:
					week_str="周一";
					break;
				case 2:
					week_str="周二";
					break;
				case 3:
					week_str="周三";
					break;
				case 4:
					week_str="周四";
					break;
				case 5:
					week_str="周五";
					break;
				case 6:
					week_str="周六";
					break;
				case 7:
					week_str="周日";
					break;
				default:
					week_str="";
					break;
			}
		}
		if("3".equals(style)){
			switch (week_int) {
				case 1:
					week_str="星期一";
					break;
				case 2:
					week_str="星期二";
					break;
				case 3:
					week_str="星期三";
					break;
				case 4:
					week_str="星期四";
					break;
				case 5:
					week_str="星期五";
					break;
				case 6:
					week_str="星期六";
					break;
				case 7:
					week_str="星期日";
					break;
				default:
					week_str="";
					break;
			}
		}
		return week_str;
	}

	/**
	 * 日期格式化成字符串
	 * @param date 日期对象
	 * @param style 格式化的样式
	 * @return 格式化后的字符串
	 */
	public static String dateFormateToString(Date date,String style){
		SimpleDateFormat sdf=new SimpleDateFormat(style);
		String str=sdf.format(date);
		return str;
	}

	/**
	 * 字符串转换成日期
	 * @param str 要转换的字符串
	 * @param style 格式化的样式
	 * @return 日期
	 */
//	public static Date stringFormateToDate(String str,String style){
//		SimpleDateFormat sdf=new SimpleDateFormat(style);
//		java.util.Date date=null;
//		try {
//			date = sdf.parse(str);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return date;
//	}

	/**
	 * 根据日期判断是上午还是下午
	 * @param date 日期对象
	 * @return 上午 or 下午
	 */
//	public static String dateFormateToAmOrPm(Date date){
//		String strDate = dateFormateToString(date,defDtPtn02);
//		String strDateNoTime= dateFormateToString(date,defDtPtn04);
//		String midday = strDateNoTime+" 12:00:00"; //中午时间
//		if(signSecondsBetweenTowDate(strDate,midday)>=0){
//			return "下午";
//		}else {
//			return "上午";
//		}
//	}

	/**
	 * 判断时间是否介于3天前时间到现在之间
	 * @param strDate 要判断的时间
	 * @param style 原日期格式
	 * @return true 是，false 否
	 */
//	public static boolean isBefor3Date(String strDate,String style){
//		Date now = new Date();
//		String now_str = dateFormateToString(now, "yyyyMMdd");
//		now_str+="235959";
//		now = stringFormateToDate(now_str, "yyyyMMddHHmmss");
//		Date befor3Date = newDateOfMinusDays(now, 3);//3天前的日期
//		Date date = stringFormateToDate(strDate, style);
//		if(date.before(now)&&date.after(befor3Date)){
//			return true;
//		}
//		return false;
//	}

	/**
	 * 转换日期的格式
	 * @param baseDate 原始日期
	 * @param oldStyle 原日期格式
	 * @param newStyle 新日期格式
	 * @return
	 */
//	public static String formatDate(String baseDate,String oldStyle,String newStyle){
//		String newDate = null;
//		if(baseDate!=null&&!"".equals(baseDate)){
//			newDate = DateUtil.dateFormateToString(DateUtil.stringFormateToDate(baseDate, oldStyle), newStyle);
//		}
//		return newDate;
//	}
	/**
	 * 转换日期的格式
	 * @param baseDate 原始日期
	 * @param newStyle 新日期格式
	 * @return
	 */
//	public static String formatDate(Date baseDate,String newStyle){
//		String newDate = null;
//		if(baseDate!=null){
//			newDate = DateUtil.dateFormateToString(baseDate, newStyle);
//		}
//		return newDate;
//	}
	public static String newDateOfAddMonths(String curDate, int months) {
		String newDate = "";
		Calendar c = Calendar.getInstance();//获得一个日历的实例
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(curDate);
		} catch (Exception e) {

		}
		c.setTime(date);//设置日历时间
		c.add(Calendar.MONTH, months);    //增加的月数
		newDate = sdf.format(c.getTime());

		return newDate;
	}

	/**
	 * 获取指定日期所在周的第一天
	 * @param date
	 * @return
	 */
	public static String getFirstDateOfWeek(String date){
		String newDate = "";
		Calendar cal = Calendar.getInstance();
		try{
			cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		} catch (ParseException e) {
			throw new RuntimeException("startDate is not a vaild format "+DateUtil.defDtPtn04);
		}
		int d = 0;
		if(cal.get(Calendar.DAY_OF_WEEK)==1){
			d = -6;
		}else{
			d = 2-cal.get(Calendar.DAY_OF_WEEK);
		}
		cal.add(Calendar.DAY_OF_WEEK, d);
		//所在周开始日期
		newDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

		return newDate;
	}
	/**
	 * 获取指定日期所在周的最后一天
	 * @param date
	 * @return
	 */
	public static String getLastDateOfWeek(String date) {
		String newDate = "";
		Calendar cal = Calendar.getInstance();
		try{
			cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		} catch (ParseException e) {
			throw new RuntimeException("startDate is not a vaild format "+DateUtil.defDtPtn04);
		}

		int d = 0;
		if(cal.get(Calendar.DAY_OF_WEEK)==1){
			d = -6;
		}else{
			d = 2-cal.get(Calendar.DAY_OF_WEEK);
		}
		cal.add(Calendar.DAY_OF_WEEK, d);
		cal.add(Calendar.DAY_OF_WEEK, 6);
		//所在周结束日期
		newDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

		return newDate;
	}
	/**
	 * 得到指定日期后指定月数的日期
	 *
	 * @param curDate
	 * @param months
	 * @return
	 */
	public static String newMonthOfAddMonths(String curDate, int months) {
		String newDate = "";
		Calendar c = Calendar.getInstance();//获得一个日历的实例
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = sdf.parse(curDate);
		} catch (Exception e) {

		}
		c.setTime(date);//设置日历时间
		c.add(Calendar.MONTH, months);    //增加的月数
		newDate = sdf.format(c.getTime());

		return newDate;
	}
	public static String monthOfDate(String curDate) {
		String newDate = "";
		if(StringUtil.isNotBlank(curDate)) {
			Calendar c = Calendar.getInstance();//获得一个日历的实例
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Date date = null;
			try {
				date = sdf.parse(curDate);
			} catch (Exception e) {

			}
			c.setTime(date);//设置日历时间
			newDate = sdf.format(c.getTime());

		}
		return newDate;
	}

	public static String getCurrMonth() {
		Date day=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		String currMonth=df.format(day);
		return currMonth;
	}
	//将轮转开始结束时间按月单位拆分成多个时间段
	public static String[] splitDateArea(String startDate, String endDate) throws ParseException{
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)){

			List<Map<String, String>> list = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Calendar c = Calendar.getInstance();
			Date date = sdf.parse(startDate);
			c.setTime(date);
			String startMonth= sdf.format(c.getTime());
			date = sdf.parse(endDate);
			c.setTime(date);
			String endMonth= sdf.format(c.getTime());
			while (startMonth.compareTo(endMonth) <= 0) {
				Map<String, String> newTime = new HashMap<>();
				newTime.put("evlMonth", startMonth);
				//获取开始与结束时间
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c1 = Calendar.getInstance();
				date = sdf.parse(startMonth);
				c1.setTime(date);
				c1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
				String  monthFirstDay = format.format(c1.getTime());
				if(monthFirstDay.compareTo(startDate)<=0)
				{
					monthFirstDay=startDate;
				}
				c1.add(Calendar.MONTH, 1);
				c1.add(Calendar.DATE,-1);
				String endTime=format.format(c1.getTime());
				if(endTime.compareTo(endDate)>=0)
				{
					endTime=endDate;
				}
				newTime.put("startTime", monthFirstDay);
				newTime.put("endTime", endTime);
				list.add(newTime);

				//开始时间加1个自然月
				date = sdf.parse(startMonth);
				c.setTime(date);
				c.add(Calendar.MONTH, 1);
				startMonth=sdf.format(c.getTime());
			}

			Collections.sort(list, new Comparator<Map<String, String>>() {
				@Override
				public int compare(Map<String, String> f1, Map<String, String> f2) {
					String order1 = f1.get("evlMonth");
					String order2 = f2.get("evlMonth");
					if (order1 == null) {
						return -1;
					} else if (order2 == null) {
						return 1;
					} else if (order1 != null && order2 != null) {
						return order1.compareTo(order2);
					}
					return 0;
				}
			});

			String [] dataArea = new String[list.size()];
			for(int i=0;i<list.size();i++){
				Map<String, String> time=list.get(i);
				dataArea[i]=time.get("startTime")+"~"+time.get("endTime");
			}
			return dataArea;
		}
		return null;
	}



	/**
	 * 获取两个日期之间的日期(包含开始、结束)
	 * @param start 开始日期
	 * @param end 结束日期
	 * @return 日期集合
	 */
	public static List<Date> getBetweenDates(Date start, Date end) {
		List<Date> result = new ArrayList<Date>();
		result.add(start);
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(start);
		tempStart.add(Calendar.DAY_OF_YEAR, 1);

		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(end);
		while (tempStart.before(tempEnd)) {
			result.add(tempStart.getTime());
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}
		result.add(end);
		return result;
	}



	public static void main(String[] args) throws ParseException{
		System.out.println("\\");


		System.out.println(DateUtil.addYear("2019-09-01",3));

		String month=DateUtil.getMonth();
		String firstDay=DateUtil.setFirstDayOfMonth(month);
		firstDay =DateUtil.getFirstDateOfWeek(firstDay);
		String lastDay=DateUtil.setLastDateOfMonth(month);
		lastDay =DateUtil.getLastDateOfWeek(lastDay);
		System.out.println(firstDay);
		System.out.println(lastDay);
//		List<String> times=TimeUtil.getMonthsByTwoMonth();
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.defDtPtn04);
//		Date start = simpleDateFormat.parse("2017-11-02");
//		Date end = simpleDateFormat.parse("2017-11-15");
//
//		List<Date> list = getBetweenDates(start,end);
//		for(Date date:list){
//			System.err.println(formatDate(date, DateUtil.defDtPtn04));
//		}
		/*
		for(int m=1;m<2;m++){
			String month = m>9? ""+m : "0"+m;
			String startDate  = "2014-"+month+"-01";
			for(int i=1;i<25;i++){
				String d1 = DateUtil.addMonthForArrange(startDate, i+"",true);
				String d1P = DateUtil.addDate(d1,1);
				System.out.print(startDate+" add "+i+" is :"+d1);
				System.out.println(",the next arrange date is ："+d1P);
			}
			for(int i=0;i<25;i++){
				String d1 = DateUtil.addMonthForArrange(startDate, i+".5",true);
				String d1P = DateUtil.addDate(d1,1);
				System.out.print(startDate+" add "+i+".5 is :"+d1);
				System.out.println(",the next arrange date is ："+d1P);
			}
			startDate  = "2014-"+month+"-16";
			for(int i=1;i<25;i++){
				String d1 = DateUtil.addMonthForArrange(startDate, i+"",true);
				String d1P = DateUtil.addDate(d1,1);
				System.out.print(startDate+" add "+i+" is :"+d1);
				System.out.println(",the next arrange date is ："+d1P);
			}
			for(int i=0;i<25;i++){
				String d1 = DateUtil.addMonthForArrange(startDate, i+".5",true);
				String d1P = DateUtil.addDate(d1,1);
				System.out.println(startDate+" add "+i+".5 is :"+d1);
				System.out.println(",the next arrange date is ："+d1P);
			}
		}
		int signDays = (int) signDaysBetweenTowDate("2014-09-30", "2014-07-01");
		for(int s=0;s<=signDays;s++){
			//String countDate = DateUtil.addDate("2014-07-01", s);
			//System.out.println("countDate:"+countDate);
		}
		System.out.println(DateUtil.addHour("20140120050505", 2));
		System.err.println(weekOfYear("2015-01-01"));
		System.err.println(addDate("2015-01-01",6));
		*/
		long betweenDays = DateUtil.signDaysBetweenTowDate("2018-01-01","2018-01-01");
		System.out.println(betweenDays);
		// 获取Calendar
		Calendar calendar = Calendar.getInstance();
		// 设置时间,当前时间不用设置
		// calendar.setTime(new Date());
		// 设置日期为本月最大日期
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		// 打印
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(format.format(calendar.getTime()));
		System.out.println(DateUtil.addYear("2017-08-01",2));
		String []times=splitDateArea("2016-11-01","2017-01-31");
		for(String t:times)
		{
			System.out.println(t);

		}
		System.out.println(times.length);
		System.out.println( DateUtil.transDateTime("20190228000000") );
		System.out.println(DateUtil.signSecondsBetweenTowDate(DateUtil.transDate("20190228000005","yyyy-MM-dd HH:mm:ss"), DateUtil.transDate("20190228000000","yyyy-MM-dd HH:mm:ss")));

	}


    /**
     * 判断两个时间段是否重叠，如：  startdate1 — enddate1 ,startdate2 — enddate2
     * @param starDate1
     * @param endDate1
     * @param startDate2
     * @param endDate2
     * @return
     */
    public static boolean isDateOverlapped (String starDate1,String endDate1,String startDate2,String endDate2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date s1 = null;
        Date e1 = null;
        Date s2 = null;
        Date e2 = null;
        try {
            s1 = sdf.parse(starDate1);
            e1 = sdf.parse(endDate1);
            s2 = sdf.parse(startDate2);
            e2 = sdf.parse(endDate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if ((s1.compareTo(s2)<=0 && e1.compareTo(s2)>=0 )
                || (s2.compareTo(s1)<=0 && e2.compareTo(s1)>=0) )
            return true;
        return false;
    }
	public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取两个日期相差的月数
	 */
	public static int getMonthDiff(String date1, String date2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = sdf.parse(date1);
			d2 = sdf.parse(date2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c1.setTime(d1);
		c2.setTime(d2);
		int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH);
		int month2 = c2.get(Calendar.MONTH);
		int day1 = c1.get(Calendar.DAY_OF_MONTH);
		int day2 = c2.get(Calendar.DAY_OF_MONTH);
		// 获取年的差值
		int yearInterval = year1 - year2;
		// 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
		if (month1 < month2 || month1 == month2 && day1 < day2) {
			yearInterval--;
		}
		// 获取月数差值
		int monthInterval = (month1 + 12) - month2;
		if (day1 < day2) {
			monthInterval--;
		}
		monthInterval %= 12;
		int monthsDiff = Math.abs(yearInterval * 12 + monthInterval) - 1;
		return monthsDiff;
	}


	//格式转换 2022-02-02  转换成  2022年2月2日
	public static String parseDate(String dateInfO) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateInfO);
		String now = new SimpleDateFormat("yyyy年MM月dd日").format(date);

		if (now.substring(5,6).equals("0")){
			now=now.substring(0,5)+now.substring(6,now.length());
		}
		int index = now.indexOf("月");
		if (now.substring(index+1,index+2).equals("0")){
			now=now.substring(0,index+1)+now.substring(index+2,now.length());
		}
		return now;
	}

	/**
	 * 推算 n 天之后的日期
	 * @param date
	 * @param num
	 * @return
	 * @throws ParseException
	 */
	public static String getAppointDate(String date,Integer num) throws ParseException {
		if (StringUtil.isBlank(date)){
			return "";
		}
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
		Date d = s.parse(date);
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, num);//计算 n 天后的时间
		String str2=s.format(c.getTime());
		return str2;
	}

	/**
	 * 推算 n 天之前，后的日期  yyyyMMddHHmmss
	 * @param date
	 * @param num
	 * @return
	 * @throws ParseException
	 */
	public static String getAppointDate2(Date date,Integer num) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, num);//当前日期加两小时
		Date d = cal.getTime();
		return dateFormat.format(d);
	}

	/**
	 * //当前日期和指定n天后的日期相差多少天
	 * @param date
	 * @param num
	 * @return
	 * @throws ParseException
	 */
	public static Long appointDaysBetweenTowDate(String date,Integer num) throws ParseException {
		if (StringUtil.isBlank(date)){
			return 0L;
		}
		String d = getAppointDate(date,num);	//指定n天以后的日期
		long end = signDaysBetweenTowDate(d,getCurrDate());	//当前日期和指定n天后的日期相差多少天
		return end;
	}


	/**
	 * 获取两个日期差距月份
	 * @param startDateTime
	 * @param endDateTime
	 * @return
	 * @throws ParseException
	 */
	public static int getMonths(String startDateTime, String endDateTime) throws ParseException {
		startDateTime = startDateTime.replace("-","").replace(" ","").substring(0,8);
		endDateTime = endDateTime.replace("-","").replace(" ","").substring(0,8);

		SimpleDateFormat tempDateFormat = new SimpleDateFormat();
		tempDateFormat = new SimpleDateFormat("yyyyMMdd");
		Date start = tempDateFormat.parse(startDateTime);
		Date end = tempDateFormat.parse(endDateTime);
		if (start.after(end)) {
			Date t = start;
			start = end;
			end = t;
		}

		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(start);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		Calendar temp = Calendar.getInstance();
		temp.setTime(end);
		temp.add(Calendar.DATE, 1);

		int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
		int day = endCalendar.get(Calendar.DAY_OF_MONTH) - startCalendar.get(Calendar.DAY_OF_MONTH);

		if(day > 0){
			month = month + 1;
		}
		return year * 12 + month;

	}


	/**
	 * @Title: getDistanceDay
	 * @Description: 获取两个日期相差的天数
	 * @param startDateTime
	 * @param endDateTime
	 * @return
	 * @throws ParseException
	 * @return int 返回类型
	 * @throws
	 */
	public static int getDistanceDay(String startDateTime, String endDateTime) throws ParseException
	{
		/*startDateTime = startDateTime.replace("-","").replace(" ","");
		endDateTime = endDateTime.replace("-","").replace(" ","");
*/
		SimpleDateFormat tempDateFormat = new SimpleDateFormat();
		/*if(startDateTime.length() > 8 && endDateTime.length() > 8)
		{
			startDateTime = startDateTime.substring(0,8);
			endDateTime   = endDateTime.substring(0,8);
		}else if(startDateTime.length() < 8 && endDateTime.length() < 8)
		{
			return -1;
		}*/
		tempDateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date1 = tempDateFormat.parse(startDateTime);
		Date date2 = tempDateFormat.parse(endDateTime);

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1= cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		// 同一年
		if(year1 != year2)
		{
			int timeDistance = 0 ;
			for(int i = year1 ; i < year2 ; i ++)
			{
				// 闰年
				if(i%4==0 && i%100!=0 || i%400==0)
				{
					timeDistance += 366;
				}
				else
				{
					// 不是闰年
					timeDistance += 365;
				}
			}
			return timeDistance + (day2-day1) + 1 ;
		}
		else
		{
			// 不同年
			return day2-day1 + 1 ;
		}
	}

	/**
	 *
	 * @param status 是否比较日期
	 * @param day	时间
	 * @param dayNum	相差的天数
	 * @return
	 * @throws ParseException
	 */
	public static String dayCompore(String status,String day,String dayNum) throws ParseException {
        if (null == status || StringUtil.isBlank(status) || !status.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)
				|| null== day || StringUtil.isBlank(day)
				|| null==dayNum || StringUtil.isBlank(dayNum)){
            return com.pinde.core.common.GlobalConstant.FLAG_N;
		}
		SimpleDateFormat tempDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date parse = tempDateFormat.parse(day);
		int distanceDay = differentDays(parse, new Date());
		if (Integer.parseInt(dayNum)>=distanceDay){
            return com.pinde.core.common.GlobalConstant.FLAG_Y;
		}
        return com.pinde.core.common.GlobalConstant.FLAG_N;
	}

	public static int differentDays(Date date1,Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1= cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);
		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if(year1 != year2) {
			int timeDistance = 0 ;
			for(int i = year1 ; i < year2 ; i ++)
			{
				if(i%4==0 && i%100!=0 || i%400==0) //闰年
				{
					timeDistance += 366;
				}
				else //不是闰年
				{
					timeDistance += 365;
				}
			}
			return timeDistance + (day2-day1) ;
		} else {
			return day2-day1;
		}
	}

	public static String getSubDate(String text){
		if (StringUtil.isNotBlank(text)){
			String[] split = text.split("/");
			if (split.length>2){
				String date=split[split.length-2];
				if (StringUtil.isNotBlank(date) && date.length()==8){
					return 	transDate(date);
				}
			}
		}
		return "";
	}
}
