package com.pinde.sci.ctrl.sch.plan.util;


import com.alibaba.fastjson.JSON;
import com.pinde.core.util.DateUtil;
import com.pinde.sci.ctrl.sch.plan.domain.PlanMonth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SchDateUtil {

    /**
     * 一个时间段按自然月拆分
     * @return
     * @throws ParseException
     */
    public static List<PlanMonth> getTimes(String startDate, Double months) throws ParseException {
        List<PlanMonth> list = null;
        if (StringUtil.isNotBlank(startDate)) {
            list=new ArrayList<>();
            //开始时间加1个自然月
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(startDate);
            c.setTime(date);
            int j=0;
            for(int i=0;i<months;i++)
            {
                String start = sdf.format(c.getTime());
                c.set(Calendar.DATE, 1);//把日期设置为当月第一天
                c.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
                int min = c.get(Calendar.DATE)/2;
                c.set(Calendar.DATE, min);
                PlanMonth planMonth=new PlanMonth(j++,j-1);
                String end = sdf.format(c.getTime());
                planMonth.setStartDate(start);
                planMonth.setEndDate(end);
                list.add(planMonth);
                i++;
                if(i<months) {
                    start = addDate(end, 1);
                    c.set(Calendar.DATE, 1);
                    c.roll(Calendar.DATE, -1);
                    end = sdf.format(c.getTime());
                    planMonth = new PlanMonth(j++, j - 1);
                    planMonth.setStartDate(start);
                    planMonth.setEndDate(end);
                    list.add(planMonth);
                    c.set(Calendar.DATE, 1);
                    c.add(Calendar.MONTH, 1);
                }
            }
        }
        return list;
    }
    public static boolean isContinuity(List<PlanMonth> list) throws ParseException {
        if(list!=null&&list.size()>0)
        {
            List<String> monthList=new ArrayList<>();
            list.forEach(p -> {
                        monthList.add(p.getStartDate());
                        monthList.add(p.getEndDate());
                    }
            );
            Collections.sort(monthList, new Comparator<String>() {
                @Override
                public int compare(String order1, String order2) {
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
            boolean isContinuityMonth=true;
            for(int j=0;j<monthList.size();j++)
            {
                if(j!=monthList.size()-1&&j!=0) {
                    String s = monthList.get(j);
                    String e = monthList.get(++j);
                    String newS=addDate(s,1);
                    if(!newS.equals(e))
                    {
                        isContinuityMonth=false;
                        break;
                    }
                }
            }
            return isContinuityMonth;
        }
        return false;
    }

    public static String defDtPtn04 = "yyyy-MM-dd";
    public static String addDate(String endDate,int step){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defDtPtn04);
        Date date;
        try {
            date = simpleDateFormat.parse(endDate);
            Calendar calender = Calendar.getInstance();
            calender.setTime(date);
            calender.add(Calendar.DATE, step);
            String startDate = simpleDateFormat.format(calender.getTime());
            return startDate;
        } catch (ParseException e) {
            throw new RuntimeException("endDate is not a vaild format "+defDtPtn04);
        }
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
    public static  void main(String []args) throws ParseException {
//        System.out.println("2017-12-11".substring(0,7));
//
//        Calendar a = Calendar.getInstance();
//        a.set(Calendar.YEAR, 2018);
//        a.set(Calendar.MONTH, 2 - 1);
//        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
//        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
//        int maxDate = a.get(Calendar.DATE);
//        System.out.println(maxDate);
        List<PlanMonth> getTimes=getTimes("2018-02-01",36.0);
        System.out.println(JSON.toJSONString(getTimes));

//        List<PlanMonth> getTimes2=new ArrayList<>();
//        PlanMonth planMonth=new PlanMonth(0L);
//        planMonth.setStartDate("2018-02-01");
//        planMonth.setEndDate("2018-02-14");
//        getTimes2.add(planMonth);
//        planMonth=new PlanMonth(1L);
//        planMonth.setStartDate("2018-02-15");
//        planMonth.setEndDate("2018-02-28");
//        getTimes2.add(planMonth);
//        planMonth=new PlanMonth(5L);
//        planMonth.setStartDate("2018-03-01");
//        planMonth.setEndDate("2018-03-15");
////        getTimes2.add(planMonth);
////        planMonth=new PlanMonth(3L);
////        planMonth.setStartDate("2018-03-16");
////        planMonth.setEndDate("2018-03-31");
////        getTimes2.add(planMonth);
//        System.err.println(isContinuity(getTimes2));
//        int[] studentBusy ={1,2,3,4,5,6};
//        int[] array = studentBusy;
//        ++array[2];
//        System.err.println(studentBusy[2]);
//        System.err.println(array[2]);
//        float s1[]={1,3,4,2,1.5f,2.5f,0.5f,1.5f,5,4,3,6};
//        float s2[]={4,3,4,5,3.5f,3.5f,1.5f,3.5f,5};
//        float s3[]={1,3,4,1,1.5f,2.5f,1,1,5,4,3,6};
//        float s4[]={4,5,5,3,3.5f,3.5f,1.5f,3.5f,5};
//        float s5[]={1,3,4,2,1.5f,2.5f,0.5f,1.5f,5,4,3,6};
//        float s6[]={4,3,4,5,3.5f,3.5f,1.5f,3.5f,1,1,1,1,1};
//        float s7[]={1,3,1,2,1.5f,2.5f,0.5f,1.5f,5,1,3,2,4,2,2,2};
//        float s8[]={4,3,4,1,3.5f,3.5f,1.5f,3.5f,1,2,2,2,2};

    }

    /**
     * 遍历数字 计算出对应的组合
     * @param source
     * @param index
     * @param blen
     * @return
     */
    public static List<Float> getComb(float[] source, int index, int len, long blen) {
        List<Float> list = new ArrayList<Float>();
        for (int i = 0; i < len; i++) {    //逐个遍历为禁止的位
            int tmp = index << i;
            if ((tmp & blen>>1) != 0) {    //遇到1就将数据加入组合中
                list.add(source[i]);
            }
        }
        return list;
    }
}
