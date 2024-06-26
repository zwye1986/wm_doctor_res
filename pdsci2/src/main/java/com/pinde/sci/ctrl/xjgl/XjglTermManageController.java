package com.pinde.sci.ctrl.xjgl;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.edu.IEduUserBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.xjgl.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.xjgl.ClassOrderEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.xjgl.XjEduScheduleClassExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/xjgl/term/manage")
public class XjglTermManageController extends GeneralController {
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IXjEduCourseBiz eduCourseBiz;
    @Autowired
    private IXjEduTermBiz eduTermBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IXjEduScheduleBiz scheduleBiz;
    @Autowired
    private IEduUserBiz eduBiz;

    @RequestMapping(value = "/list")
    public String list(String roleFlag, String weekNumber, String assumeOrgFlow, String courseFlow, EduTerm term, String classTeacherName, Model model) {
        model.addAttribute("roleFlag",roleFlag);
        if("doctor".equals(roleFlag)){
            EduUser eduUser = eduBiz.readEduUser(GlobalContext.getCurrentUser().getUserFlow());
            term.setGradationId(eduUser.getTrainTypeId());
            term.setClassId(eduUser.getClassId());
            model.addAttribute("trainTypeId",eduUser.getTrainTypeId());
            model.addAttribute("className",eduUser.getClassName());
        }
        if(StringUtil.isBlank(term.getSessionNumber())){
            term.setSessionNumber(DateUtil.getYear());
        }
        if("skz".equals(roleFlag)){
            //查询授课组对应课程信息
            List<Map<String,Object>> list2 = eduCourseBiz.queryOrgOrCourse(GlobalContext.getCurrentUser().getUserFlow());
            model.addAttribute("courseFlow2",null != list2 && list2.size()>0?list2.get(0).get("COURSE_FLOW"):"");
            model.addAttribute("courseTypeId",null != list2 && list2.size()>0?list2.get(0).get("COURSE_TYPE_ID"):"");
        }
        List<EduCourse> records = eduCourseBiz.searchCourseList(new EduCourse());
        Map<String, String> orgMap = new HashMap<String, String>();
        for (EduCourse temp : records) {
            if (StringUtil.isNotBlank(temp.getAssumeOrgFlow())) {
                orgMap.put(temp.getAssumeOrgFlow(), temp.getAssumeOrgName());
            }
        }
        model.addAttribute("orgMap", orgMap);
        List<EduTerm> terms = eduTermBiz.seachlistByTerm(term);
        if (terms != null && terms.size() > 0) {
            Map<String, Object> param = new HashMap<>();
            Map<String, Object> weekNuMap = new HashMap<>();
            param.put("weekNumber", weekNumber);
            param.put("assumeOrgFlow", assumeOrgFlow);
            param.put("courseFlow", courseFlow);
            param.put("classTeacherName", classTeacherName);
            for (EduTerm temp : terms) {
                List<Integer> weekNum = getWeekNum(temp.getTermStartTime(), temp.getTermEndTime());//页面的周数
                weekNuMap.put(temp.getRecordFlow() + "_week", weekNum);
            }
            model.addAttribute("weekNuMap", weekNuMap);
        }
        model.addAttribute("terms", terms);
        return "xjgl/term/manage/list";
    }

    @RequestMapping(value = "/loadDetail")
    public String loadDetail(String roleFlag,String weekNumber, String assumeOrgFlow, String courseFlow, String recordFlow, String classTeacherName, Model model) {
        if (StringUtil.isNotBlank(recordFlow)) {
            EduTerm temp = eduTermBiz.getEduTermByFlow(recordFlow);
            Map<String, Object> param = new HashMap<>();
            Map<String, Object> weekNuMap = new HashMap<>();
            param.put("weekNumber", weekNumber);
            param.put("assumeOrgFlow", assumeOrgFlow);
            param.put("courseFlow", courseFlow);
            if(StringUtil.isNotBlank(classTeacherName))
            {
                try {
                    classTeacherName=java.net.URLDecoder.decode(classTeacherName,"UTF-8");
                }catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            param.put("classTeacherName", classTeacherName);
            param.put("term", temp);
            setSearchTime(temp, param);

            Map<String, Object> info = new HashMap<>();
            //首课查询（现去除）
//            List<Map<String,String>> maps= new ArrayList<>();
//            maps = eduTermBiz.getFirstClassByFlow(recordFlow);
//            Map<String,Object> firstClassMap=new HashMap<>();
//            if(maps!=null&&maps.size()>0)
//            {
//                for(Map<String,String> map:maps)
//                {
//                    map.put("classOrder",getClassOrderIdByName(map.get("classOrder")));
//                    String key =map.get("courseCode")+"_"+map.get("classTime")+"_"+map.get("classOrder");
//                    firstClassMap.put(key,map);
//                }
//            }
//            model.addAttribute("firstClassMap",firstClassMap);
            List<XjEduScheduleClassExt> list = eduTermBiz.seachClassByMap(param);
            if (list != null && list.size() > 0) {
                for (XjEduScheduleClassExt b : list) {

                    String key = b.getClassTime() + "_" + getClassOrderIdByName(b.getClassOrder());
                    List<XjEduScheduleClassExt> list2 = (List<XjEduScheduleClassExt>) info.get(key);
                    if (list2 == null) {
                        list2 = new ArrayList<>();
                        info.put(key, list2);
                    }
                    list2.add(b);
                }
            }
            //页面课表信息
            weekNuMap = getWeekMap2(param);
            model.addAttribute("weekNuMap", weekNuMap);
            System.out.println(JSON.toJSONString(weekNuMap));
            System.out.println(JSON.toJSONString(info));
            model.addAttribute("info", info);
            model.addAttribute("termFlow", recordFlow);
            model.addAttribute("term",temp);
            List<EduScheduleLimit> limitList = eduCourseBiz.queryScheduleLimit(temp.getSessionNumber());
            model.addAttribute("limitList",limitList);
        }
        return "xjgl/term/manage/loadDetail";
    }
    /**
     * 查询course
     * */
    @RequestMapping(value="/currCourse")
    public String currCourse(Model model,String courseFlow,String year,String termSeason,String classId,String trainTypeId,String userFlow,String recordFlow,String studMaxNum,String isChosedCourseName,String flag,String lastWeek){
        if (StringUtil.isNotBlank(isChosedCourseName)) {
            try {
                isChosedCourseName=new String(isChosedCourseName.getBytes("ISO-8859-1"), "UTF-8");
                model.addAttribute("isChosedCourseName",isChosedCourseName);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotBlank(courseFlow)) {
            EduCourse course=eduCourseBiz.readCourse(courseFlow);
            List<EduCourseTeacher> teacherLst = eduCourseBiz.readCourseTeacherLst(courseFlow);
            model.addAttribute("course",course);
            model.addAttribute("teacherLst",teacherLst);

            if(StringUtil.isNotBlank(year) && StringUtil.isNotBlank(termSeason) && StringUtil.isNotBlank(classId) && StringUtil.isNotBlank(trainTypeId)){
                //计算上此堂课程的总人数
                List<EduScheduleStudent> stuClassLst = scheduleBiz.getScheduleStuClass(year,termSeason,classId,trainTypeId,recordFlow);
                if(null != stuClassLst && stuClassLst.size() > 0 && StringUtil.isNotBlank(studMaxNum)){
                    if(stuClassLst.size() >= Integer.valueOf(studMaxNum)){
                        model.addAttribute("studentNumFlag",studMaxNum);
                    }
                }
                //前置课程必须完成学时方可选此课程
                if(StringUtil.isNotBlank(course.getPreCourse())){
                    EduCourse preCode = new EduCourse();
                    preCode.setCourseCode(course.getPreCourse());
                    List<EduCourse>  courseLst = eduCourseBiz.readCourse(preCode);
                    if(null != courseLst && courseLst.size() > 0){
                        int period = courseLst.get(0).getCoursePeriod()==null?0:Integer.valueOf(courseLst.get(0).getCoursePeriod());
                        Map<String,Object> param = new HashMap<>();
                        param.put("year",year);
                        param.put("termSeason",termSeason);
                        param.put("userFlow",userFlow);
                        int periodSum = 0;//学时
                        List<EduScheduleStudent> schStuLst = scheduleBiz.getChosedClass(param);
                        if(null != schStuLst && schStuLst.size() > 0){
                            for (int i = 0; i < schStuLst.size(); i++) {
                                if(course.getPreCourse().equals(schStuLst.get(i).getCourseCode())){
                                    periodSum += schStuLst.get(i).getClassPeriod()==null?0:Integer.valueOf(schStuLst.get(i).getClassPeriod());
                                }
                            }
                        }
                        if(periodSum < period){
                            model.addAttribute("preCodeFlag",courseLst.get(0).getCourseName());
                        }
                    }
                }
            }
        }
        List<SysOrg> sysOrg=orgBiz.queryAllSysOrg(null);
        model.addAttribute("sysOrg",sysOrg);

        List<String> roleList = new ArrayList<String>();
        String tutorRoleFlow = InitConfig.getSysCfg("xjgl_teacher_role_flow");
        if(StringUtil.isNotBlank(tutorRoleFlow)){
            roleList.add(tutorRoleFlow);
            List<SysUser> userList = userBiz.searchResManageUser(new SysUser(),roleList,null);
            model.addAttribute("userList",userList);
        }
        String masterFlow = InitConfig.getSysCfg("xjgl_master_role_flow");
        roleList.clear();
        if(StringUtil.isNotBlank(masterFlow)){
            roleList.add(masterFlow);
            List<SysUser> masterList = userBiz.searchResManageUser(new SysUser(),roleList,null);
            model.addAttribute("masterList",masterList);
        }
        return "xjgl/term/manage/courseInfo";
    }
    public static String getClassOrderIdByName(String name){
        String orderId = "";
        for (ClassOrderEnum e : ClassOrderEnum.values()) {
            if (e.getName().equals(name)) {
                orderId = e.getId();
            }
        }
        return orderId;
    }
    public static Map<String, Object> getWeekMap2(Map<String, Object> param) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String termStartTime = (String) param.get("termStartTime");//查询条件开始时间 不一是所在周的第一天
            String termEndTime = (String) param.get("termEndTime");//查询条件结束时间 不一是所在周的最后一天
            //获得起始日期和结束日期之间的所有日期 并且补齐头尾周
            Calendar startCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            termStartTime = getWeekFirstDate(termStartTime);//设置所在周的第一天
            termEndTime = getWeekLastDate(termEndTime);//设置所在周的最后一天


            Date startDate = sdf.parse(termStartTime);
            startCalendar.setFirstDayOfWeek(Calendar.SUNDAY);
            startCalendar.setTime(startDate);

            Date endDate = sdf.parse(termEndTime);
            endCalendar.setFirstDayOfWeek(Calendar.SUNDAY);
            endCalendar.setTime(endDate);

            List<String> days = new ArrayList();
            while (startCalendar.compareTo(endCalendar) <= 0) {
                days.add(sdf.format(startCalendar.getTime()));
                startCalendar.add(Calendar.DATE, 1);
            }
            map.put("days", days);//页面中所有日期

            //获取完毕
            //获取日期对应的星期几的MAP
            Map<String, String> weekMaps = new HashMap<String, String>();
            Calendar c = Calendar.getInstance();
            String dayForWeek = "";
            for (String day : days) {
                c.setTime(sdf.parse(day));
                int i = c.get(Calendar.DAY_OF_WEEK) - 1;
                switch (i) {
                    case 0: {
                        dayForWeek = "日";
                        break;
                    }
                    case 1: {
                        dayForWeek = "一";
                        break;
                    }
                    case 2: {
                        dayForWeek = "二";
                        break;
                    }
                    case 3: {
                        dayForWeek = "三";
                        break;
                    }
                    case 4: {
                        dayForWeek = "四";
                        break;
                    }
                    case 5: {
                        dayForWeek = "五";
                        break;
                    }
                    case 6: {
                        dayForWeek = "六";
                        break;
                    }
                }
                weekMaps.put(day, dayForWeek);
            }
            map.put("weekMaps", weekMaps);//每一在对应的星期

            EduTerm temp = (EduTerm) param.get("term");//获取学期开始时间
            String initStartTime = temp.getTermStartTime();
            Map<Integer, List<String>> map2 = new HashMap<Integer, List<String>>();
            for (String day : days) {
                List<String> list = map2.get(getStartWeek(day, initStartTime));
                if (list == null) {
                    list = new ArrayList<String>();
                    map2.put(getStartWeek(day, initStartTime), list);
                }
                list.add(day);
            }

            List<Integer> keyList = new ArrayList<Integer>();
            for (Integer key : map2.keySet()) {
                List<String> list = map2.get(key);
                keyList.add(key);
                Arrays.sort(list.toArray());
            }
            Arrays.sort(keyList.toArray());
            map.put("keyList", keyList);//所有的周数
            map.put("weekDayListMap", map2);//每一周对应的天数
            //周次MAP获取完毕
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取两个时间之间的周数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Integer> getWeekNum(String startDate, String endDate) {
        List<Integer> list = new ArrayList<>();
        int i = 1;
        startDate = getWeekFirstDate(startDate);
        try {
            if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && startDate.compareTo(endDate) <= 0) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dateStart = sdf.parse(startDate);
                Date dateEnd = sdf.parse(endDate);
                while ((dateEnd.getTime() - dateStart.getTime()) >= 0) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    try {
                        date = format.parse(startDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setFirstDayOfWeek(Calendar.SUNDAY);
                    calendar.setTime(date);
                    list.add(i);
                    i++;
                    calendar.add(Calendar.DATE, 7);
                    startDate = sdf.format(calendar.getTime());
                    dateStart = format.parse(startDate);
                }
            }
        } catch (Exception e) {

        }
        return list;
    }

    /**
     * 获取所在周的第一天
     *
     * @param date
     * @return
     */
    public static String getWeekFirstDate(String date) {
        String firstDate = date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateStart = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.SUNDAY);
            calendar.setTime(dateStart);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            firstDate = sdf.format(calendar.getTime());
        } catch (Exception e) {
        }
        return firstDate;
    }

    /**
     * 获取所在周的最后一天
     *
     * @param date
     * @return
     */
    public static String getWeekLastDate(String date) {
        String lastDate = date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateStart = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.SUNDAY);
            calendar.setTime(dateStart);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            lastDate = sdf.format(calendar.getTime());
        } catch (Exception e) {

        }
        return lastDate;
    }

    /**
     * 计算查询开始时间距离 学期开始时间是第几周
     *
     * @param startTime 查询条件开始时间
     * @param string    学期开始时间
     * @return
     */
    private static int getStartWeek(String startTime, String string) {
        String initStartDate = getWeekFirstDate(string);
        startTime = getWeekLastDate(startTime);
        int i = 0;
        while (initStartDate.compareTo(startTime) <= 0) {
            try {
                i++;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate;
                startDate = sdf.parse(initStartDate);
                Calendar termStartCalendar = Calendar.getInstance();
                termStartCalendar.setFirstDayOfWeek(Calendar.SUNDAY);
                termStartCalendar.setTime(startDate);
                termStartCalendar.add(Calendar.DATE, 7);
                initStartDate = sdf.format(termStartCalendar.getTime());
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return i;
    }

    /**
     * 根据查询的周数 生成 开始时间与结束时间
     *
     * @param temp
     * @param param
     */
    public static void setSearchTime(EduTerm temp, Map<String, Object> param) {
        String weekNumber = (String) param.get("weekNumber");//第几周
        try {
            String termStartTime = temp.getTermStartTime();//学期开始时间
            termStartTime = getWeekFirstDate(termStartTime);//获取学期开始时间所在周的第一天
            String termEndTime = temp.getTermEndTime();//学期结束时间
            //如果周数为空，则默认取开始的两周
            int days = 14 - 1;
            if (StringUtil.isNotBlank(weekNumber)) {
                int week = Integer.valueOf(weekNumber);
                days = 7 * week - 1;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateStart = sdf.parse(termStartTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.SUNDAY);
            calendar.setTime(dateStart);
            calendar.add(Calendar.DATE, days);

            String endDate = sdf.format(calendar.getTime());//查询的结束时间

            if (termEndTime.compareTo(endDate) > 0) {//新的结束时间比规定的结束时间大，则取原来的结束时间
                termEndTime = endDate;
            }
            if (StringUtil.isNotBlank(weekNumber)) {//周不为空时，需要重置一下查询的开始时间
                String end = getWeekLastDate(termEndTime);
                Date newdateStart = sdf.parse(end);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setFirstDayOfWeek(Calendar.SUNDAY);
                calendar2.setTime(newdateStart);
                calendar2.add(Calendar.DATE, -6);
                termStartTime = sdf.format(calendar2.getTime());//查询的结束时间
                if (temp.getTermStartTime().compareTo(termStartTime) > 0) {//新的开始时间比学期开始时间小时，取学期开始时间
                    termStartTime = temp.getTermStartTime();
                }
            }
            param.put("termStartTime", termStartTime);
            param.put("termEndTime", termEndTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/edit")
    public String add(String flow, Model model) {
        if (StringUtil.isNotBlank(flow)) {
            EduTerm term = eduTermBiz.getEduTermByFlow(flow);

            model.addAttribute("term", term);
        }
        return "xjgl/term/manage/add";
    }

    @RequestMapping(value = "/saveTerm")
    @ResponseBody
    public String saveTerm(EduTerm term, Model model) {
        if (StringUtil.isNotBlank(term.getClassId())) {
            term.setClassName(DictTypeEnum.XjClass.getDictNameById(term.getClassId()));
        }
        if (StringUtil.isNotBlank(term.getGradationId())) {
            term.setGradationName(DictTypeEnum.TrainType.getDictNameById(term.getGradationId()));
        }
        if (StringUtil.isNotBlank(term.getGradeTermId())) {
            term.setGradeTermName(DictTypeEnum.TermSeason.getDictNameById(term.getGradeTermId()));
        }
        if (StringUtil.isBlank(term.getRecordFlow())) {
            EduTerm newTerm = new EduTerm();
            newTerm.setClassId(term.getClassId());
            newTerm.setSessionNumber(term.getSessionNumber());
            newTerm.setGradeTermId(term.getGradeTermId());
            newTerm.setGradationId(term.getGradationId());
            newTerm.setRecordStatus(GlobalConstant.FLAG_Y);
            newTerm.setTermStartTime(null);
            newTerm.setTermEndTime(null);
            List<EduTerm> list = eduTermBiz.seachlistByTerm(newTerm);
            if (list != null && list.size() > 0) {
                return "课表信息已存在，请修改后提交！";
            }
        }
        int num = eduTermBiz.saveTerm(term);
        if (num == GlobalConstant.ONE_LINE) {
            return GlobalConstant.SAVE_SUCCESSED;
        } else {
            return GlobalConstant.SAVE_FAIL;
        }
    }
    @RequestMapping(value = "/courseAdd")
    public String courseAdd(EduScheduleClass schedule,String courseTypeId,Model model) throws Exception{
        //课堂序号解码
        schedule.setClassOrder(java.net.URLDecoder.decode(schedule.getClassOrder(),"UTF-8"));
        if(StringUtil.isNotBlank(schedule.getRecordFlow())){
            schedule = scheduleBiz.readByRecordFlow(schedule.getRecordFlow());
            List<EduScheduleTeacher> teaList = scheduleBiz.queryClassTeachers(schedule.getRecordFlow());
            model.addAttribute("teaList",teaList);
        }else{
            EduTerm term = eduTermBiz.getEduTermByFlow(schedule.getTermFlow());
            model.addAttribute("term",term);
            List<Map<String,Object>> list = eduCourseBiz.queryOrgOrCourse(GlobalContext.getCurrentUser().getUserFlow());
            model.addAttribute("course",null != list && list.size()>0 ? list.get(0):list);
        }
        model.addAttribute("schedule",schedule);
        return "xjgl/term/manage/courseAdd";
    }
    /**
     * 必修课 排课针对班级
     */
    @RequestMapping(value = "/saveScheduleClass")
    @ResponseBody
    public String saveScheduleClass(EduScheduleClass schedule,String teacherNameList,String oldTeacherNameList,String searchCourseFlag) {
        if(GlobalConstant.FLAG_Y.equals(searchCourseFlag)){
            EduCourse course = eduCourseBiz.readCourse(schedule.getCourseFlow());
            schedule.setCourseCode(course.getCourseCode());
            schedule.setClassCourseName(course.getCourseName());
            schedule.setClassPeriod(course.getCoursePeriod());
            schedule.setAssumeOrgFlow(course.getAssumeOrgFlow());
            schedule.setAssumeOrgName(course.getAssumeOrgName());
        }
        int num = scheduleBiz.saveScheduleClass(schedule,teacherNameList,oldTeacherNameList);
        if(num == -1){
            return "无法安排同一老师同一时间上课!";
        }else if(num > 0){
            return GlobalConstant.SAVE_SUCCESSED;
        }else{
            return GlobalConstant.SAVE_FAIL;
        }
    }
    @RequestMapping(value = "/delClass")
    @ResponseBody
    public String delClass(String courseTypeId,String recordFlow,String courseFlow) {
        int num = scheduleBiz.delClass(courseTypeId,recordFlow,courseFlow);
        if(num > 0){
            return GlobalConstant.DELETE_SUCCESSED;
        }else{
            return GlobalConstant.DELETE_FAIL;
        }
    }
    @RequestMapping(value = "/scheduleSetting")
    public String scheduleSetting(String sessionNumber, Integer currentPage, HttpServletRequest request, Model model) {
        if (StringUtil.isNotBlank(sessionNumber)) {
            PageHelper.startPage(currentPage,  getPageSize(request));
            List<EduScheduleLimit> limitList = eduCourseBiz.queryScheduleLimit(sessionNumber);
            model.addAttribute("limitList", limitList);
        }
        return "xjgl/term/manage/scheduleSetting";
    }
    @RequestMapping(value = "/settingAdd")
    public String settingAdd(String recordFlow,Model model) {
        if (StringUtil.isNotBlank(recordFlow)) {
            EduScheduleLimit limit = eduCourseBiz.readScheduleLimitByFlow(recordFlow);
            model.addAttribute("limit", limit);
        }
        return "gyxjgl/term/manage/settingAdd";
    }
    @RequestMapping(value = "/saveSetting")
    @ResponseBody
    public String saveSetting(EduScheduleLimit limit) {
        int num = eduCourseBiz.updateScheduleLimit(limit);
        if(num>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }else if(num == -1){
            return "排课限制时间存在交叉！";
        }
        return GlobalConstant.SAVE_FAIL;
    }
    @RequestMapping(value = "/delSetting")
    @ResponseBody
    public String settingAdd(String recordFlow) {
        int num = eduCourseBiz.delScheduleLimit(recordFlow);
        if(num>0){
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }
    @RequestMapping(value="/exportClass")
    public void exportClass(String recordFlow, HttpServletResponse response) throws Exception {
        EduTerm term = new EduTerm();
        term.setRecordFlow(recordFlow);
        Map<String, Object> param = new HashMap<>();
        param.put("term",term);
        List<XjEduScheduleClassExt> list = eduTermBiz.seachClassByMap(param);
        if(null != list && !list.isEmpty()){
            for(XjEduScheduleClassExt ext : list){
                //存储 授课老师
                String teaStr = "";
                List<EduScheduleTeacher> teachers = ext.getScheduleTeacherList();
                if(null != teachers && !teachers.isEmpty()){
                    for(int i=0; i<teachers.size(); i++){
                        teaStr +=teachers.get(i).getClassTeacherName();
                        if(i < teachers.size()-1){
                            teaStr+=",";
                        }
                    }
                }
                ext.setRecordFlow(teaStr);
            }
        }
        String[] titles = new String[]{
                "sessionNumber:所属学年",
                "gradeTermName:学期",
                "className:班级",
                "gradationName:授课层次",
                "courseCode:课程代码",
                "classCourseName:课堂名称",
                "classTime:上课日期",
                "classOrder:第几节课",
                "assumeOrgName:承担单位",
                "recordFlow:授课老师",
                "classroomName:上课地点"
        };
        String fileName = "课表信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream(),new String[]{"4","5"});
    }
}
