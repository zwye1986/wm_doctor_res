package com.pinde.core.util;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarUtil {
    //节假日列表
    private static List<Calendar> holidayList = new ArrayList<Calendar>();
    //周末为工作日
    private static List<Calendar> weekendList = new ArrayList<Calendar>();

    public static List<Calendar> getHolidayList() {
        return holidayList;
    }

    public static void setHolidayList(List<Calendar> holidayList) {
        CalendarUtil.holidayList = holidayList;
    }

    public static List<Calendar> getWeekendList() {
        return weekendList;
    }

    public static void setWeekendList(List<Calendar> weekendList) {
        CalendarUtil.weekendList = weekendList;
    }

    /**
     *
     * 验证日期是否是节假日
     * @param calendar  传入需要验证的日期
     * @return
     * return boolean    返回类型  返回true是节假日，返回false不是节假日
     * throws
     */
    public static boolean checkHoliday(Calendar calendar) throws Exception{

        //判断日期是否是周六周日
        if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){

            //判断日期是否是周末为工作日
            for (Calendar ca : weekendList) {
                if(ca.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
                        ca.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)&&
                        ca.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)){
                    return false;
                }
            }

            return true;
        }
        //判断日期是否是节假日
        for (Calendar ca : holidayList) {
            if(ca.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
                    ca.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)&&
                    ca.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)){
                return true;
            }
        }

        return false;
    }

    /**
     * 清空list
     */
    public static void clearList(){
        holidayList.clear();
        weekendList.clear();
    }


    /**
     *
     * 把所有节假日放入list
     * @param date  从数据库查 查出来的格式yyyy-MM-dd
     * return void    返回类型
     * throws
     */
    public static List<Calendar> initHolidayList(String date){

        String [] da = date.split("-");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.valueOf(da[0]));
        calendar.set(Calendar.MONTH, Integer.valueOf(da[1])-1);//月份比正常小1,0代表一月
        calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(da[2]));
        holidayList.add(calendar);

        return holidayList;
    }

    /**
     * 初始化周末被调整为工作日的数据
     */
    public static List<Calendar> initWeekendList(String date){
        String [] da = date.split("-");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.valueOf(da[0]));
        calendar.set(Calendar.MONTH, Integer.valueOf(da[1])-1);//月份比正常小1,0代表一月
        calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(da[2]));
        weekendList.add(calendar);

        return weekendList;
    }

    /**
     * @param args
     * return void    返回类型
     * throws
     */
//    public static void main(String[] args) {
//        try {
//
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            Calendar ca = Calendar.getInstance();
//            Date d = df.parse("2017-10-09"); //学员所选的所有日期
//                                             //遍历执行这个方法
//            ca.setTime(d);//设置当前时间
//
//            CalendarUtil ct = new CalendarUtil();
//            //所有管理员登记的日期放入到这个list中
//            ct.initHolidayList("2017-10-01"); //节假日
//            ct.initHolidayList("2017-10-02");
//            ct.initHolidayList("2017-10-03");
//            ct.initHolidayList("2017-10-04");
//            ct.initHolidayList("2017-10-05");
//            ct.initHolidayList("2017-10-06");
//            ct.initHolidayList("2017-10-07");
//            ct.initHolidayList("2017-10-08");
//
//            //登记本是周末但是为工作日的日期，放入此list中（还要新建表存此数据）
//            ct.initWeekendList("2017-09-30");//初始周末为工作日
//
//            boolean k = checkHoliday(ca);
//            System.out.println(k);
//
//        } catch ( Exception e) {
//            // TODO: handle exception
//            System.out.println(e.getClass());
//             logger.error("",e);
//        }
//
//    }


}