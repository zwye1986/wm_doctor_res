package com.pinde.sci.util;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by www.0001.Ga on 2016/7/7.
 */
public class DateTimeUtil {

    private static Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);

    /**
     * 获取两个月份之间的所有月
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String> getMonthsByTwoMonth(String startDate, String endDate) {
        List<String> months = null;
        if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && startDate.compareTo(endDate) <= 0) {
            months = new ArrayList<String>();
            months.add(startDate);
            if (!startDate.equals(endDate)) {
                String currDate = startDate;
                while (!currDate.equals(endDate)) {
                    currDate = com.pinde.core.util.DateUtil.newMonthOfAddMonths(currDate, 1);
                    months.add(currDate);
                }
            }
        }
        return months;
    }
    /**
     * 获取两个日期之间的所有周
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String> newGetWeeksByTwoDate(String startDate, String endDate){
        List<String> weeks = null;
        try {
            if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && startDate.compareTo(endDate) <= 0) {
                weeks = new ArrayList<String>();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dateStart = sdf.parse(startDate);
                Date dateEnd = sdf.parse(endDate);
                while ((dateEnd.getTime() - dateStart.getTime())>=0) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    try {
                        date = format.parse(startDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setFirstDayOfWeek(Calendar.MONDAY);
                    calendar.setTime(date);
                    if(calendar.get(Calendar.WEEK_OF_YEAR)==1&&(calendar.get(Calendar.MONTH) + 1)==12)
                    {
                        weeks.add((calendar.get(Calendar.YEAR)+1) + "-" + calendar.get(Calendar.WEEK_OF_YEAR));
                    }else{
                        weeks.add(calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.WEEK_OF_YEAR));
                    }

                    calendar.add(Calendar.DATE, 7);
                    int year = calendar.get(Calendar.YEAR);    //获取年
                    int month = calendar.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
                    int day = calendar.get(Calendar.DATE);    //获取当前天数
                    startDate = year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
                    dateStart = format.parse(startDate);
                }
            }
        }catch (Exception e){

        }
        return weeks;
    }

    private static long weekFormat(long days){
        long result = 1;
        if(days!=0){
            result = (long)Math.ceil(days/7.0);
        }
        return result;
    }
    public static void main(String[] args) {
        Calendar ca = Calendar.getInstance();//创建一个日期实例
        ca.setTime(new Date());//实例化一个日期
        //System.out.println(ca.get(Calendar.DAY_OF_YEAR));//获取是第多少天
        //System.out.println(ca.get(Calendar.WEEK_OF_YEAR));//获取是第几周

        //System.out.println("===============================");
        String today = "2016-08-09";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(today);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        //System.out.println(calendar.get(Calendar.YEAR));
        //System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));
        calendar.add(Calendar.DATE,7);
        //System.out.println(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE));
        int year = calendar.get(Calendar.YEAR);    //获取年
        int month = calendar.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
        int day = calendar.get(Calendar.DATE);    //获取当前天数

        //System.out.println(year+"-"+(month<10?"0"+month:month)+"-"+(day<10?"0"+day:day));


//        {"orgFlow":"","schDeptFlow":"","startDate":"2016-07-11","endDate":"2016-08-08"}
//        {"orgFlow":"","schDeptFlow":"","startDate":"2016-08-15","endDate":"2016-09-12"}
//        {"orgFlow":"","schDeptFlow":"","startDate":"2016-09-12","endDate":"2016-10-10"}

        List<String> weeks = newGetWeeksByTwoDate("2016-07-25","2016-07-25");
        for (String week:weeks  ) {
            //System.out.println(week);
        }
        //System.out.println("===============================");
        weeks = newGetWeeksByTwoDate("2016-08-09","2016-09-05");
        for (String week:weeks  ) {
            //System.out.println(week);
        }
    }

    public static int getDays(String startdate, String enddate) {
        int temp = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateStart = sdf.parse(startdate);
            Date dateEnd = sdf.parse(enddate);
            temp = (int) ((dateEnd.getTime() - dateStart.getTime()) / 86400000 / 7);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        return temp;
    }
    /**
     * 获取两个日期之间的所有周
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String> getWeeksByTwoDate(String startDate, String endDate){
        List<String> weeks = null;
        if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && startDate.compareTo(endDate)<=0){
            weeks = new ArrayList<String>();
            String startYear = startDate.substring(0,4);
            String endYear = endDate.substring(0,4);
            if(startYear.equals(endYear)){
                long startDays = DateUtil.signDaysBetweenTowDate(startDate,startYear+"-01-01");
                long endDays = DateUtil.signDaysBetweenTowDate(endDate,startYear+"-01-01");
                long startWeek = weekFormat(startDays);
                long endWeek = weekFormat(endDays);
                while(startWeek<=endWeek){
                    weeks.add(startYear+"-"+startWeek);
                    startWeek++;
                }
            }else{
                int start = Integer.parseInt(startYear);
                int end = Integer.parseInt(endYear);
                while(start<=end){
                    String currYear = String.valueOf(start);
                    long dayNum = 0;
                    if(startYear.equals(currYear)){
                        dayNum = DateUtil.signDaysBetweenTowDate(startDate,startYear+"-01-01");
                        long endNum = DateUtil.signDaysBetweenTowDate(currYear+"-12-31",currYear+"-01-01");
                        long startWeek = weekFormat(dayNum);
                        long endWeek = weekFormat(endNum);
                        while(startWeek<=endWeek){
                            weeks.add(currYear+"-"+startWeek);
                            startWeek++;
                        }
                    }else if(endYear.equals(currYear)){
                        dayNum = DateUtil.signDaysBetweenTowDate(endDate,currYear+"-01-01");
                        long startWeek = 1;
                        long endWeek = weekFormat(dayNum);
                        while(startWeek<=endWeek){
                            weeks.add(currYear+"-"+startWeek);
                            startWeek++;
                        }
                    }else{
                        dayNum = DateUtil.signDaysBetweenTowDate(currYear+"-12-31",currYear+"-01-01");
                        long startWeek = 1;
                        long endWeek = weekFormat(dayNum);
                        while(startWeek<=endWeek){
                            weeks.add(currYear+"-"+startWeek);
                            startWeek++;
                        }
                    }
                    start++;
                }
            }
        }
        return weeks;
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
        startDateTime = startDateTime.replace("-","").replace(" ","");
        endDateTime = endDateTime.replace("-","").replace(" ","");

        SimpleDateFormat tempDateFormat = new SimpleDateFormat();
        if(startDateTime.length() > 8 && endDateTime.length() > 8)
        {
            startDateTime = startDateTime.substring(0,8);
            endDateTime   = endDateTime.substring(0,8);
        }else if(startDateTime.length() < 8 && endDateTime.length() < 8)
        {
            return -1;
        }
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
     * 获取两个日期差距月份
     * @param startDateTime
     * @param endDateTime
     * @return
     * @throws ParseException
     */
    public static int getMonth(String startDateTime, String endDateTime) throws ParseException {
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
     * 获取当前时间间隔number月的日期
     * @param inputDate
     * @param number
     * @return
     * @throws Exception
     */
    public static String  getAfterDate(String inputDate,int number,String type) {
        // 获得一个日历的实例
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try{
            // 初始日期
            date = sdf.parse(inputDate);
        }catch(Exception e){
            logger.error("pdapp: DateTimeUtil.getAfterMonth Exception",e);
        }
        // 设置日历时间
        c.setTime(date);
        if("month".equals(type)){
            // 在日历的月份上增加number个月
            c.add(Calendar.MONTH, number);
        } else {
            // 在日历的月份上增加number个日
            c.add(Calendar.DATE, number);
        }
        // 得到你想要的前后number个月/日后的日期
        String strDate = sdf.format(c.getTime());
        return strDate;
    }

    /**
     * 日期比较
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static int  contrastDate(String startDate,String endDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = format.parse(startDate);
        Date d2 = format.parse(endDate);
        int date = d1.compareTo(d2);
        // 前者大于后者 返回大于0的数字反之小于0的数字，等于返回0
        System.out.println(d1.compareTo(d2));
        return date;
    }

}
