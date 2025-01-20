package com.pinde.core.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 外院轮转时间操作工具类
 * Created by lt on 2017/2/27.
 */

public class TimeUtil {
    private static Logger logger = LoggerFactory.getLogger(TimeUtil.class);

    /**
     * 判断两个日期之间相差是否是自然月的整数倍，如果是则返回倍数，否则返回-1
     * @param time
     * @return
     * @throws ParseException
     */
    public static int getMonths(Map<String, String> time) throws ParseException {
        int count = 0;
        if (time != null) {
            String startDate = time.get("startDate");
            String endDate = time.get("endDate");
            String NextEndDate ="";
            if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
                while (true) {
                    //开始时间加1个自然月
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = sdf.parse(startDate);
                    c.setTime(date);
                    c.add(Calendar.MONTH, 1);
                    NextEndDate = sdf.format(c.getTime());
                    NextEndDate = DateUtil.addDate(NextEndDate, -1);
                    if (NextEndDate.compareTo(endDate) >= 0 ) {
                        count++;
                        break;
                    }else{
                        startDate = DateUtil.addDate(NextEndDate, 1);
                    }
                    count++;
                }
                if (NextEndDate.compareTo(endDate) == 0 )
                {
                    return count;
                }else {
                    return -1;
                }
            }
        }
        return count;
    }

    /**
     * 判断两个日期之间相差自然月的倍数
     * @param time
     * @return
     * @throws ParseException
     */
    public static Double getMonthsBetween(Map<String, String> time) throws ParseException {
        double count = 0;
        if (time != null) {
            String startDate = time.get("startDate");
            String endDate = time.get("endDate");
            if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
                //日期相差的整数月
                SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cTemp = Calendar.getInstance();
                cTemp.setTime(sdf.parse(endDate));
                int year1 = cTemp.get(Calendar.YEAR);
                int month1 = cTemp.get(Calendar.MONTH);
                cTemp.setTime(sdf.parse(startDate));
                int year2 = cTemp.get(Calendar.YEAR);
                int month2 = cTemp.get(Calendar.MONTH);
                int result;
                if(year1 == year2) {
                    result = month1 - month2;
                } else {
                    result = 12*(year1 - year2) + month1 - month2;
                }
                count=result-1;//减去重叠的一月
                String lastDateOfStart=startDate.substring(0,7)+"-"+DateUtil.getLastDateOfMonth(startDate.substring(0,7));//开始时间当月最后一天
                int datesOfStart=DateUtil.getDaysOfMonth(sdf.parse(startDate));//开始时间当月天数
                String firstDateOfEnd=endDate.substring(0,7)+"-01";//结束时间当月第一天
                int datesOfEnd=DateUtil.getDaysOfMonth(sdf.parse(endDate));//结束时间当月天数
                long betweenDaysStart = DateUtil.signDaysBetweenTowDate(lastDateOfStart,startDate)+1;//开始时间到当月最后一天天数
                long betweenDaysEnd = DateUtil.signDaysBetweenTowDate(endDate,firstDateOfEnd)+1;//结束时间到当月第一天天数
                count+= (float)betweenDaysStart/datesOfStart + (float)betweenDaysEnd/datesOfEnd;
            }
        }
        BigDecimal bg = new BigDecimal(count).setScale(1, BigDecimal.ROUND_HALF_UP);
        return bg.doubleValue();
    }

    /**
     * 判断两个日期之间相差自然月的倍数（老方法）
     * @param time
     * @return
     * @throws ParseException
     */
    public static Double getMonthsBetweenOld(Map<String, String> time) throws ParseException {
        double count = 0;
        if (time != null) {
            String startDate = time.get("startDate");
            String endDate = time.get("endDate");
            String NextEndDate ="";
            if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
                while (true) {
                    //开始时间加1个自然月
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = sdf.parse(startDate);
                    c.setTime(date);
                    c.add(Calendar.MONTH, 1);
                    NextEndDate = sdf.format(c.getTime());
                    NextEndDate = DateUtil.addDate(NextEndDate, -1);
                    if (NextEndDate.compareTo(endDate) >0 ) {
//                        int days = c.getActualMaximum(Calendar.DATE);
                        long betweenDays = DateUtil.signDaysBetweenTowDate(endDate,startDate)+1;
                        Double dBetweenDays = Double.parseDouble(betweenDays + "");
//                            Double ddays = Double.parseDouble(days + "");
                        count += BigDecimal.valueOf(dBetweenDays / 30).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
                        break;
                    }else{
                        startDate = DateUtil.addDate(NextEndDate, 1);
                        count++;
                    }
                }
            }
        }
        BigDecimal bg = new BigDecimal(count).setScale(1, BigDecimal.ROUND_HALF_UP);
        return bg.doubleValue();
    }

    /**
     * 一个时间段按自然月拆分
     * @param time
     * @return
     * @throws ParseException
     */
    public static List<Map<String, String>> getTimes(Map<String, String> time) throws ParseException {
        List<Map<String, String>> list = null;
        if (time != null) {
            String startDate = time.get("startDate");
            String endDate = time.get("endDate");
            if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
                list = new ArrayList<>();
                while (startDate.compareTo(endDate) <= 0) {
                    //开始时间加1个自然月
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = sdf.parse(startDate);
                    c.setTime(date);
                    c.add(Calendar.MONTH, 1);
                    String NextEndDate = sdf.format(c.getTime());
                    NextEndDate = DateUtil.addDate(NextEndDate, -1);
                    if (NextEndDate.compareTo(endDate) > 0) {
                        NextEndDate = endDate;
                    }
                    Map<String, String> newTime = new HashMap<>();
                    newTime.put("startDate", startDate);
                    newTime.put("endDate", NextEndDate);
                    list.add(newTime);
                    startDate = DateUtil.addDate(NextEndDate, 1);
                }

                Collections.sort(list, new Comparator<Map<String, String>>() {
                    @Override
                    public int compare(Map<String, String> f1, Map<String, String> f2) {
                        String order1 = f1.get("startDate");
                        String order2 = f2.get("startDate");
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
            }
        }
        return list;
    }

    /**
     * 多个时间段合并
     * @param times
     * @return
     */
    public static List<Map<String, String>> getNewTimes(List<Map<String, String>> times) {
        if (times != null && times.size() > 0) {
            Collections.sort(times, new Comparator<Map<String, String>>() {
                @Override
                public int compare(Map<String, String> f1, Map<String, String> f2) {
                    String order1 = f1.get("startDate");
                    String order2 = f2.get("startDate");
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
            for (int i = 0; i < times.size(); i++) {
                Map<String, String> map = times.get(i);
                String startDate = map.get("startDate");
                String endDate = map.get("endDate");
                String endDateNextDay = DateUtil.addDate(endDate, 1);
                int count = 0;
                for (int j = i + 1; j < times.size(); j++) {
                    Map<String, String> otherMap = times.get(j);
                    String otherStartDate = otherMap.get("startDate");
                    String otherEndDate = otherMap.get("endDate");
                    if (otherStartDate.equals(endDateNextDay)) {
                        map.put("endDate", otherEndDate);
                        times.remove(otherMap);
                        j--;
                        count++;
                    }
                }
                i = i - count;
            }
        }
        return times;
    }
    /**
     * 获取两个月份之间的所有月
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String> getMonthsByTwoMonth(String startDate, String endDate){
        List<String> months = null;
        if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && startDate.compareTo(endDate)<=0){
            months = new ArrayList<String>();
            months.add(startDate);
            if(!startDate.equals(endDate)){
                String currDate = startDate;
                while(!currDate.equals(endDate)){
                    currDate = DateUtil.newMonthOfAddMonths(currDate,1);
                    months.add(currDate);
                }
            }
        }
        return months;
    }

    //获得两个日期间所有日期
    public static List<Date> findDates(Date dBegin, Date dEnd)
    {
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime()))
        {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    public static List<String> findDates2(String month)
    {
        List<String> lDate = new ArrayList();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(month);
        } catch (Exception e) {

        }
        Calendar   cal_1 = Calendar.getInstance();//获取当前日期

        cal_1.setTime(date);//设置日历时间
        cal_1.set(Calendar.DATE, cal_1.getActualMaximum(Calendar.DATE));
        String endDay=format2.format(cal_1.getTime());

        cal_1.setTime(date);//设置日历时间
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String  startDay = format2.format(cal_1.getTime());


        // 测试此日期是否在指定日期之后
        while (endDay.compareTo(startDay)>=0)
        {
            lDate.add(startDay);
            cal_1.add(Calendar.DATE,1);
            startDay= format2.format(cal_1.getTime());
        }
        return lDate;
    }
    public static List<String> findDates2(String s,String end)
    {
        List<String> lDate = new ArrayList();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        String endDay=end;
        Date date = null;
        try {
            date = format2.parse(s);
        } catch (Exception e) {

        }
        Calendar   cal_1 = Calendar.getInstance();//获取当前日期

        cal_1.setTime(date);//设置日历时间
        String  startDay = format2.format(cal_1.getTime());
        // 测试此日期是否在指定日期之后
        while (endDay.compareTo(startDay)>=0)
        {
            lDate.add(startDay);
            cal_1.add(Calendar.DATE,1);
            startDay= format2.format(cal_1.getTime());
        }
        return lDate;
    }
    public static  void main(String []args)
    {
        Map<String,String> map=new HashMap<>();
        map.put("startDate","2019-02-15");
        map.put("endDate","2019-02-19");
        try{
            System.out.println("getMonthsBetween:"+JSON.toJSONString(getMonthsBetween(map)));
            System.out.println("getMonthsBetweenNew:"+JSON.toJSONString(getMonthsBetweenOld(map)));
        }catch (Exception e){
            logger.error("", e);
        }
        System.out.println(JSON.toJSONString(findDates2("2017-12")));
        System.out.println(JSON.toJSONString(getMonthsByTwoMonth("2017-12","2017-12")));
        System.out.println(JSON.toJSONString(findDates2("2017-11-03","2017-12-12")));
        String month=DateUtil.getMonth();
        String firstDay=DateUtil.setFirstDayOfMonth(month);
        String lastDay=DateUtil.setLastDateOfMonth(month);
        System.out.println(firstDay);
        System.out.println(lastDay);
        firstDay =DateUtil.getFirstDateOfWeek(firstDay);
        lastDay =DateUtil.getLastDateOfWeek(lastDay);
        System.out.println(firstDay);
        System.out.println(lastDay);
        List<String> times= TimeUtil.findDates2(firstDay,lastDay);
        System.out.println(JSON.toJSONString(times));
        System.out.println("2017-12-11".substring(0,7));
        int trRows=times.size()/7;
        System.out.println(trRows);
        Map<String,Map<String,String>> tableMap=new HashMap<>();
        String  table[][]=new String[trRows][7];
        for(int i=0;i<trRows;i++)
            for(int j=0;j<7;j++)
            {
                int index=i*7+j;
                Map<String,String> data=new HashMap<>();
                data.put("date",times.get(index));
                data.put("num","1");
                data.put("content","01121");
                data.put("month",DateUtil.transDateTime(times.get(index),"yyyy-MM-dd","yyyy-MM"));
                data.put("day",DateUtil.transDateTime(times.get(index),"yyyy-MM-dd","dd"));
                tableMap.put(i+"-"+j,data);
            }
        System.out.println(JSON.toJSONString(tableMap));
    }

}
