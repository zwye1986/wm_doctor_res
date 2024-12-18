package com.pinde.res.ctrl.jswjw;

import com.pinde.app.common.InitConfig;
import com.pinde.core.common.sci.dao.SysOrgMapper;
import com.pinde.core.common.sci.dao.SysUserMapper;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jswjw.IJswjwHospitalSupervisioBiz;
import com.pinde.res.biz.stdp.IResActivityBiz;
import com.pinde.sci.dao.base.SysDeptMapper;
import com.pinde.sci.dao.base.TeachingActivityInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/hospitalSupervisio")
public class JswjwHospitalSupervisioController {
    private static Logger logger = LoggerFactory.getLogger(JswjwHospitalSupervisioController.class);

    @Autowired
    IJswjwHospitalSupervisioBiz hospitalSupervisioBiz;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private TeachingActivityInfoMapper activityInfoMapper;
    @Autowired
    private SysDeptMapper deptMapper;
    @Autowired
    private SysOrgMapper orgMapper;
    @Autowired
    private IResActivityBiz activityBiz;

    /**
     * 我的评审
     * @param model
     * @param userFlow
     * @param review    标识符：stay 已评审    not未评审
     * @return
     */
    @RequestMapping(value = {"/reviewItems"}, method = {RequestMethod.POST})
    public String leaveAuditList(Model model, String userFlow, String review) throws ParseException {
        SysUser user = userMapper.selectByPrimaryKey(userFlow);
        model.addAttribute("orgFlow",user.getOrgFlow());
        model.addAttribute("orgName",user.getOrgName());
        List<ResHospSupervSubject> list = hospitalSupervisioBiz.queryMyreviewItems(userFlow);   //该专家的所以项目
        if (null!=list && list.size()>0){
            ArrayList<ResHospSupervSubject> toBeReviewedList = new ArrayList<>();       //待评审项目
            ArrayList<ResHospSupervSubject> reviewedList = new ArrayList<>();       //已评审项目
            for (ResHospSupervSubject subject : list) {
                //判断是否完成评分
               if ((subject.getLeaderOneFlow().equals(userFlow) && StringUtil.isNotBlank(subject.getLeaderOneScore())) ||
                       (subject.getLeaderTwoFlow().equals(userFlow) && StringUtil.isNotBlank(subject.getLeaderTwoScore()))   ){
                   reviewedList.add(subject);
               }else {
                   toBeReviewedList.add(subject);
               }
            }
            model.addAttribute("reviewedNum",reviewedList.size()==0?0:reviewedList.size());
            if (review.equals("stay")){ //已评审
                if (null != reviewedList && reviewedList.size()>0){
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date nowData = sdf.parse(DateUtil.getCurrDateTime2());
                    for (ResHospSupervSubject hospSupervSubject : reviewedList) {
                        Date subData = sdf.parse(hospSupervSubject.getActivityEndTime());
                        if (nowData.compareTo(subData)>0){
                            hospSupervSubject.setModifyTime(com.pinde.core.common.GlobalConstant.FLAG_N);
                        }else {
                            hospSupervSubject.setModifyTime(com.pinde.core.common.GlobalConstant.FLAG_Y);
                        }
                    }
                }
                model.addAttribute("list",reviewedList);
            }else { //未评审
                if (null != toBeReviewedList && toBeReviewedList.size()>0){
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date nowData = sdf.parse(DateUtil.getCurrDateTime2());
                    for (ResHospSupervSubject hospSupervSubject : toBeReviewedList) {
                        Date subData = sdf.parse(hospSupervSubject.getActivityEndTime());
                        if (nowData.compareTo(subData)>0){
                            hospSupervSubject.setModifyTime(com.pinde.core.common.GlobalConstant.FLAG_N);
                        }else {
                            hospSupervSubject.setModifyTime(com.pinde.core.common.GlobalConstant.FLAG_Y);
                        }
                        if (StringUtil.isEmpty(hospSupervSubject.getLeaderOneFlow())|| StringUtil.isEmpty(hospSupervSubject.getLeaderTwoFlow())){
                            hospSupervSubject.setLeaderSubNum("1");
                        }
                        if (StringUtil.isNotEmpty(hospSupervSubject.getLeaderOneFlow()) && StringUtil.isNotEmpty(hospSupervSubject.getLeaderTwoFlow())){
                            hospSupervSubject.setLeaderSubNum("2");
                        }
                        if (StringUtil.isEmpty(hospSupervSubject.getLeaderOneFlow()) &&StringUtil.isEmpty(hospSupervSubject.getLeaderTwoFlow())){
                            hospSupervSubject.setLeaderSubNum("0");
                        }
                        if ("appoint".equals(hospSupervSubject.getOrderAction())){
                            hospSupervSubject.setModifyUserFlow(com.pinde.core.common.GlobalConstant.FLAG_N);
                        }else {
                            hospSupervSubject.setModifyUserFlow(com.pinde.core.common.GlobalConstant.FLAG_Y);
                        }
                    }
                }
                model.addAttribute("list",toBeReviewedList);
            }

        }
        model.addAttribute("num",list.size());
        model.addAttribute("userFlow",userFlow);
        return "res/jswjw/hospitalSupervisio/reviewItems";
    }


    /**
     *  项目列表
     * @param model
     * @param speId
     * @param inspectionType    检查类型
     * @param userFlow
     * @param full  已选满
     * @return
     */
    @RequestMapping(value = {"/allSubject"}, method = {RequestMethod.POST})
    public String allSubject(Model model,String activityName,String speId,String inspectionType,String userFlow,String full,String mySub) throws ParseException {
            HashMap<String, Object> params = new HashMap<>();
        params.put("nowTime", DateUtil.transDateTime(DateUtil.getCurrDateTime()));
        params.put("activityName",activityName);
        if (StringUtil.isNotBlank(speId)){
            List<String> speIds=Arrays.asList(speId.split(","));
            params.put("speId",speIds);
        }
        params.put("inspectionType",inspectionType);
        params.put("userFlow",userFlow);
        params.put("full",full);
        params.put("mySub",mySub);
        SysUser user = userMapper.selectByPrimaryKey(userFlow);
        params.put("orgFlow",user.getOrgFlow());
        params.put("matching","已匹配");
        List<ResHospSupervSubject> list = hospitalSupervisioBiz.queryAllSubject(params);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date nowTime = sdf.parse(com.pinde.core.util.DateUtil.getCurrDateTime2());
        Date devTime;
        for (ResHospSupervSubject hospSupervSubject : list) {
            devTime = sdf1.parse(hospSupervSubject.getActivityEndTime());
            if (nowTime.compareTo(devTime)<0){
                hospSupervSubject.setModifyTime(com.pinde.core.common.GlobalConstant.FLAG_Y);
            }else {
                hospSupervSubject.setModifyTime(com.pinde.core.common.GlobalConstant.FLAG_N);
            }
            if (hospSupervSubject.getLeaderOneFlow().equals(userFlow)||hospSupervSubject.getLeaderTwoFlow().equals(userFlow)){
                hospSupervSubject.setModifyTime(com.pinde.core.common.GlobalConstant.FLAG_N);
            }
            if (hospSupervSubject.getLeaderOneFlow().equals("0000") || hospSupervSubject.getLeaderTwoFlow().equals("0000")){
                hospSupervSubject.setLeaderSubNum("1");
            }
            if (!hospSupervSubject.getLeaderOneFlow().equals("0000") && !hospSupervSubject.getLeaderTwoFlow().equals("0000")){
                hospSupervSubject.setLeaderSubNum("2");
                hospSupervSubject.setModifyTime(com.pinde.core.common.GlobalConstant.FLAG_N);
            }
            if (hospSupervSubject.getLeaderOneFlow().equals("0000") &&hospSupervSubject.getLeaderTwoFlow().equals("0000")){
                hospSupervSubject.setLeaderSubNum("0");
            }
        }
        model.addAttribute("list",list);
        return "res/jswjw/hospitalSupervisio/reviewItems";
    }

    @RequestMapping(value = {"/joinReview"}, method = {RequestMethod.POST})
    public String joinReview(Model model,String subjectFlows,String userFlow) {
        SysUser user = userMapper.selectByPrimaryKey(userFlow);
        if (StringUtil.isEmpty(subjectFlows)){
            model.addAttribute("resultId","500");
            model.addAttribute("resultType","服务器出错");
        }
        
        String[] flows = subjectFlows.split(",");
        int num=0;
        for (int i = 0; i < flows.length; i++) {
            ResHospSupervSubject subject = hospitalSupervisioBiz.selectHospSupervisioBySubjectFlow(flows[i]);
            if (StringUtil.isBlank(subject.getLeaderOneFlow())){
                subject.setLeaderOneFlow(user.getUserFlow());
                subject.setLeaderOneName(user.getUserName());
            }else {
                subject.setLeaderTwoFlow(user.getUserFlow());
                subject.setLeaderTwoName(user.getUserName());
            }
            num=num+hospitalSupervisioBiz.updateHospSupervisioBySubjectFlow(subject);
        }
        if (num==flows.length){
            model.addAttribute("resultId","200");
            model.addAttribute("resultType","交易成功");
        }else {
            model.addAttribute("resultId","500");
            model.addAttribute("resultType","服务器出错");
        }
        return "res/jswjw/hospitalSupervisio/status";
    }


    @RequestMapping(value = {"/delReview"}, method = {RequestMethod.POST})
    public String delReview(Model model,String subjectFlow,String userFlow) {
        SysUser user = userMapper.selectByPrimaryKey(userFlow);
        ResHospSupervSubject subject = hospitalSupervisioBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
        if (userFlow.equals(subject.getLeaderOneFlow())){
            subject.setLeaderOneFlow("");
            subject.setLeaderOneName("");
        }else {
            subject.setLeaderTwoFlow("");
            subject.setLeaderTwoName("");
        }
        if (hospitalSupervisioBiz.updateHospSupervisioBySubjectFlow(subject)==1){
            model.addAttribute("resultId","200");
            model.addAttribute("resultType","交易成功");
        }else {
            model.addAttribute("resultId","500");
            model.addAttribute("resultType","服务器出错");
        }
        return "res/jswjw/hospitalSupervisio/status";
    }

    @RequestMapping(value = {"/hospitalSchedule"}, method = {RequestMethod.POST})
    public String hospitalSchedule(Model model, String subjectFlow,String speId,String userFlow) throws ParseException {
        SysUser user = userMapper.selectByPrimaryKey(userFlow);
        SysOrg org = orgMapper.selectByPrimaryKey(user.getOrgFlow());
        model.addAttribute("cityName",org.getOrgProvName()+" "+org.getOrgCityName());
        model.addAttribute("orgName",user.getOrgName());
        model.addAttribute("orgFlow",user.getOrgFlow());
        model.addAttribute("subjectFlow",subjectFlow);
        model.addAttribute("speId",speId);
        model.addAttribute("roleFlag","hospitalLeader");


        //查询专家签名
        if (null !=user.getUserSignUrl()){
            model.addAttribute("speSignUrl", user.getUserSignUrl());
        }

        if (StringUtil.isNotBlank(subjectFlow)){
            ResScheduleScore score=new ResScheduleScore();
            score.setSubjectFlow(subjectFlow);
            score.setSpeId(speId);
            score.setGrade("hospitalLeader");
            score.setUserFlow(user.getUserFlow());
            List<ResScheduleScore> scoreList = hospitalSupervisioBiz.queryScheduleList(score);
            for (ResScheduleScore scheduleScore : scoreList) {
                if (StringUtil.isNotBlank(scheduleScore.getItemDetailed())){
                    String detailed = scheduleScore.getItemDetailed();
                    detailed=detailed.replaceAll("<br/>","\\\n");
                    scheduleScore.setItemDetailed(detailed);
                }
            }
            model.addAttribute("scoreList",scoreList);
            String scheduleDate="";
            ResHospSupervSubject hospSupervSubject = hospitalSupervisioBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
            if (StringUtil.isNotBlank(hospSupervSubject.getLeaderOneFlow()) && hospSupervSubject.getLeaderOneFlow().equals(user.getUserFlow())){
                if (StringUtil.isNotBlank(hospSupervSubject.getLeaderOneEndTime())){
                    scheduleDate=hospSupervSubject.getLeaderOneEndTime().substring(0,10);
                }else {
                    scheduleDate=DateUtil.getCurrDate();
                }
            }else if (StringUtil.isNotBlank(hospSupervSubject.getLeaderTwoFlow()) && hospSupervSubject.getLeaderTwoFlow().equals(user.getUserFlow())){
                if (StringUtil.isNotBlank(hospSupervSubject.getLeaderTwoEndTime())){
                    scheduleDate=hospSupervSubject.getLeaderTwoEndTime().substring(0,10);
                }else {
                    scheduleDate=DateUtil.getCurrDate();
                }
            }
            if (!scheduleDate.isEmpty()){
                scheduleDate=DateUtil.parseDate(scheduleDate);
            }
            model.addAttribute("scheduleDate",scheduleDate);
            model.addAttribute("deptName",hospSupervSubject.getDeptName());
            TeachingActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(hospSupervSubject.getActivityFlow());
            if (com.pinde.core.util.StringUtil.isNotBlank(activityInfo.getDeptFlow())){
                if (StringUtil.isNotBlank(activityInfo.getSpeakerFlow())){
                    SysUser sysUser = userMapper.selectByPrimaryKey(activityInfo.getSpeakerFlow());
                    if (null!=sysUser){
                        model.addAttribute("teacherName",sysUser.getUserName());
                    }
                }
                SysDept sysDept = deptMapper.selectByPrimaryKey(activityInfo.getDeptFlow());
                if (null !=sysDept){
                    model.addAttribute("speSkillName",sysDept.getDeptName());
                }
            }
            model.addAttribute("speName",hospSupervSubject.getSpeName());
            model.addAttribute("scoreTable",hospSupervSubject.getScoreTable());
            if (userFlow.equals(hospSupervSubject.getLeaderOneFlow())){
                model.addAttribute("leaderScore",hospSupervSubject.getLeaderOneScore());
            }else if (userFlow.equals(hospSupervSubject.getLeaderTwoFlow())){
                model.addAttribute("leaderScore",hospSupervSubject.getLeaderTwoScore());
            }

            int peopleNum = activityBiz.countByActivity(hospSupervSubject.getActivityFlow());
            model.addAttribute("peopleNum",peopleNum);  //参加教学活动的人数
            SysUser activityUser = userMapper.selectByPrimaryKey(hospSupervSubject.getTeachFlow());
            model.addAttribute("activityUserDept",activityUser.getDeptName());  //主讲人专业
            model.addAttribute("activityUserName",hospSupervSubject.getTeachName());  //主讲人名称
            model.addAttribute("activityStartTime",hospSupervSubject.getActivityStartTime()); //活动开始时间
            model.addAttribute("activityEndTime",hospSupervSubject.getActivityEndTime()); //活动结束时间
            model.addAttribute("activityDept",hospSupervSubject.getDeptName()); //活动科室名称
            model.addAttribute("speAndDept",hospSupervSubject.getSpeName()+"/"+hospSupervSubject.getDeptName());  //专业基地/科室
            model.addAttribute("activityMinute",DateUtil.signMinutesBetweenTowDate(hospSupervSubject.getActivityStartTime()+":00",hospSupervSubject.getActivityEndTime()+":00"));//教学时长
        }
        return "res/jswjw/hospitalSupervisio/scoreList";
    }

    /**
     * 保存分数-得分扣分
     * @param itemId
     * @param score 评分
     * @param itemName
     * @param orgName
     * @param orgFlow
     * @param model
     * @param speId
     * @param userFlow
     * @param subjectFlow
     * @param num       总分
     * @return
     */
    @RequestMapping(value = {"/saveHospitalScheduleScore"}, method = {RequestMethod.POST})
    public String saveHospitalScheduleScore(String itemId, String score, String itemName, String orgName, String orgFlow,Model model,
                                            String speId, String userFlow, String subjectFlow, String num) {
        SysUser sysUser = userMapper.selectByPrimaryKey(userFlow);
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        resScheduleScore.setGrade("hospitalLeader");
        resScheduleScore.setUserFlow(sysUser.getUserFlow());
        resScheduleScore.setItemId(itemId);
        if (score==null || StringUtil.isBlank(score)){
            score="0";
        }
        resScheduleScore.setScore(score);
        resScheduleScore.setItemName(itemName);
        resScheduleScore.setOrgFlow(orgFlow);
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setSubjectFlow(subjectFlow);
        String itemName2 = "";
        resScheduleScore.setScoreType("K");
        if (itemName.startsWith("d")) {
            itemName2 = "k" + itemName.substring(1);

        } else {
            itemName2 = "d" + itemName.substring(1);
        }
        if (hospitalSupervisioBiz.saveSchedule(resScheduleScore,userFlow) <= 0) {
            model.addAttribute("resultId","500");
            model.addAttribute("resultType","服务器出错");
        }
        resScheduleScore.setItemName(itemName2);
        resScheduleScore.setScore(String.valueOf(new BigDecimal(num).subtract(new BigDecimal(score))));
        resScheduleScore.setScoreType("d");
        hospitalSupervisioBiz.saveSchedule(resScheduleScore,userFlow);
        ResHospSupervSubject hospSupervSubject = hospitalSupervisioBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
        //评审项目评审开始时间
        if (StringUtil.isBlank(hospSupervSubject.getStartTime())){
            hospSupervSubject.setStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime().substring(0,12)));
        }

        //记录评分开始时间
        if (hospSupervSubject.getLeaderOneFlow().equals(sysUser.getUserFlow())){
            if (StringUtil.isBlank(hospSupervSubject.getLeaderOneStartTime())){
                hospSupervSubject.setLeaderOneStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
                if (hospitalSupervisioBiz.updateHospSupervisioBySubjectFlow(hospSupervSubject)!=1){
                    model.addAttribute("resultId","500");
                    model.addAttribute("resultType","服务器出错");
                }
            }
        }else {
            if (StringUtil.isBlank(hospSupervSubject.getLeaderTwoStartTime())){
                hospSupervSubject.setLeaderTwoStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
                if (hospitalSupervisioBiz.updateHospSupervisioBySubjectFlow(hospSupervSubject)!=1){
                    model.addAttribute("resultId","500");
                    model.addAttribute("resultType","服务器出错");
                }
            }
        }
        model.addAttribute("resultId","200");
        model.addAttribute("resultType","交易成功");
        return "res/jswjw/hospitalSupervisio/status";
    }


    /**
     * 保存扣分原因
     * @param itemId
     * @param itemDetailed  扣分原因
     * @param orgName
     * @param model
     * @param userFlow
     * @param orgFlow
     * @param speId
     * @param subjectFlow
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/saveScheduleDetailed"}, method = {RequestMethod.POST})
    public String saveScheduleDetailed(String itemId, String itemDetailed, String orgName,Model model,String userFlow,
                                       String orgFlow, String speId, String subjectFlow) throws Exception {

        SysUser sysUser = userMapper.selectByPrimaryKey(userFlow);
        ResHospSupervSubject subject = hospitalSupervisioBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
        //评审项目评审开始时间
        if (StringUtil.isBlank(subject.getStartTime())){
            subject.setStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime().substring(0,12)));
        }
        //记录评分开始时间
        if (subject.getLeaderOneFlow().equals(sysUser.getUserFlow())){
            if (StringUtil.isBlank(subject.getLeaderOneStartTime())){
                subject.setLeaderOneStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
                if (hospitalSupervisioBiz.updateHospSupervisioBySubjectFlow(subject)!=1){
                    model.addAttribute("resultId","500");
                    model.addAttribute("resultType","服务器出错");
                }
            }
        }else {
            if (StringUtil.isBlank(subject.getLeaderTwoStartTime())){
                subject.setLeaderTwoStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
                if (hospitalSupervisioBiz.updateHospSupervisioBySubjectFlow(subject)!=1){
                    model.addAttribute("resultId","500");
                    model.addAttribute("resultType","服务器出错");
                }
            }
        }

        ResScheduleScore resScheduleScore = new ResScheduleScore();
        resScheduleScore.setGrade("hospitalLeader");
        resScheduleScore.setUserFlow(sysUser.getUserFlow());
        resScheduleScore.setItemId(itemId);
        resScheduleScore.setOrgFlow(orgFlow);
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setSubjectFlow(subjectFlow);
        itemDetailed=itemDetailed.replaceAll("\\n","<br/>");
        itemDetailed = java.net.URLDecoder.decode(itemDetailed, "UTF-8");
        resScheduleScore.setItemDetailed(itemDetailed);

        if (hospitalSupervisioBiz.saveScheduleDetailed(resScheduleScore,userFlow) <= 0) {
            model.addAttribute("resultId","500");
            model.addAttribute("resultType","服务器出错");
        }
        model.addAttribute("resultId","200");
        model.addAttribute("resultType","交易成功");
        return "res/jswjw/hospitalSupervisio/status";
    }


    /**
     * 保存得分
     * @param itemId
     * @param score     专家评的分数
     * @param orgName
     * @param model
     * @param userFlow
     * @param orgFlow
     * @param speId
     * @param subjectFlow
     * @return
     */
    @RequestMapping(value = {"/saveScheduleMK"}, method = {RequestMethod.POST})
    public String saveScheduleMK(String itemId, String score, String orgName,Model model,String userFlow,
                                 String orgFlow, String speId, String subjectFlow ) {
         SysUser sysUser = userMapper.selectByPrimaryKey(userFlow);
        ResHospSupervSubject subject = hospitalSupervisioBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
        //评审开始时间
        if (StringUtil.isBlank(subject.getStartTime())){
            subject.setStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime().substring(0,12)));
        }
        //记录评分开始时间
        if (subject.getLeaderOneFlow().equals(sysUser.getUserFlow())){
            if (StringUtil.isBlank(subject.getLeaderOneStartTime())){
                subject.setLeaderOneStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
                if (hospitalSupervisioBiz.updateHospSupervisioBySubjectFlow(subject)!=1){
                    model.addAttribute("resultId","500");
                    model.addAttribute("resultType","服务器出错");
                }
            }
        }else {
            if (StringUtil.isBlank(subject.getLeaderTwoStartTime())){
                subject.setLeaderTwoStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
                if (hospitalSupervisioBiz.updateHospSupervisioBySubjectFlow(subject)!=1){
                    model.addAttribute("resultId","500");
                    model.addAttribute("resultType","服务器出错");
                }
            }
        }

        ResScheduleScore resScheduleScore = new ResScheduleScore();
        resScheduleScore.setGrade("hospitalLeader");
        resScheduleScore.setOrgFlow(orgFlow);
        resScheduleScore.setUserFlow(sysUser.getUserFlow());
        resScheduleScore.setItemId(itemId);
        if (score==null || StringUtil.isBlank(score)){
            score="0";
        }
        resScheduleScore.setScore(score);
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setSubjectFlow(subjectFlow);
        resScheduleScore.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
        if (hospitalSupervisioBiz.saveSchedule(resScheduleScore,userFlow) <= 0) {
            model.addAttribute("resultId","500");
            model.addAttribute("resultType","服务器出错");
        }
        model.addAttribute("resultId","200");
        model.addAttribute("resultType","交易成功");
        return "res/jswjw/hospitalSupervisio/status";
    }

    /**
     * 保存 多个分值总和为一个
     * jsp中，子分值和总分值的itemid一样，但是itemName不一样，name也不一致
     * 子分值的js方法中需要上传总分的itemName和分数
     *
     * @param itemId
     * @param score
     * @param itemName    子分值的itemName
     * @param orgName
     * @param orgFlow
     * @param speId
     * @param itemMain    总分值的itemName
     * @param scoreAll    总分值的分值
     * @return
     */
    @RequestMapping(value = "/saveScheduleManyToAll")
    public String saveScheduleManyToAll(String itemId, String score, String itemName, String orgName,Model model,String userFlow,
                                        String orgFlow, String speId, String subjectFlow, String itemMain, String scoreAll, String fileRoute) {
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        SysUser sysUser = userMapper.selectByPrimaryKey(userFlow);

        resScheduleScore.setGrade("hospitalLeader");
        resScheduleScore.setOrgFlow(sysUser.getOrgFlow());
        ResHospSupervSubject subject = hospitalSupervisioBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
        //评审开始时间
        if (StringUtil.isBlank(subject.getStartTime())){
            subject.setStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime().substring(0,12)));
        }
        //记录评分开始时间
        if (subject.getLeaderOneFlow().equals(sysUser.getUserFlow())){
            if (StringUtil.isBlank(subject.getLeaderOneStartTime())){
                subject.setLeaderOneStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
                if (hospitalSupervisioBiz.updateHospSupervisioBySubjectFlow(subject)!=1){
                    model.addAttribute("resultId","500");
                    model.addAttribute("resultType","服务器出错");
                }
            }
        }else {
            if (StringUtil.isBlank(subject.getLeaderTwoStartTime())){
                subject.setLeaderTwoStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
                if (hospitalSupervisioBiz.updateHospSupervisioBySubjectFlow(subject)!=1){
                    model.addAttribute("resultId","500");
                    model.addAttribute("resultType","服务器出错");
                }
            }
        }


        resScheduleScore.setUserFlow(sysUser.getUserFlow());
        resScheduleScore.setItemId(itemId);
        if (score==null || StringUtil.isBlank(score)){
            score="0";
        }
        resScheduleScore.setScore(score);
        resScheduleScore.setFileRoute(fileRoute);
        resScheduleScore.setItemName(itemName);
        resScheduleScore.setOrgFlow(orgFlow);
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setSubjectFlow(subjectFlow);
        //添加多的一方数据
        if (hospitalSupervisioBiz.saveSchedule(resScheduleScore,userFlow) > 0) {
            //查询多的一方数据
            resScheduleScore.setItemName(itemMain);
            resScheduleScore.setScheduleFlow(null);
            int indexOf = resScheduleScore.getItemId().lastIndexOf(".");
            resScheduleScore.setItemId(resScheduleScore.getItemId().substring(0,indexOf));
            List<ResScheduleScore> scoreList = hospitalSupervisioBiz.queryScheduleListNotItemName(resScheduleScore);
            //累计分数
            BigDecimal itemScoreAll=new BigDecimal(0);
            BigDecimal decimal = new BigDecimal(scoreAll);
            if (scoreList.size() > 0) {
                for (ResScheduleScore scheduleScore : scoreList) {
                    BigDecimal scoreInfo=new BigDecimal(scheduleScore.getScore());
                    itemScoreAll = itemScoreAll.add(scoreInfo);
                }
                itemScoreAll=decimal.subtract(itemScoreAll);
            } else {
                BigDecimal scoreInfo=new BigDecimal(scoreAll);
                BigDecimal scoreAll2=new BigDecimal(scoreAll);
                itemScoreAll=scoreAll2.subtract(scoreInfo);
            }
            if (itemScoreAll.compareTo(BigDecimal.ZERO)==-1){
                itemScoreAll.setScale(0);
            }
            //添加少的一方数据
            resScheduleScore.setScore(String.valueOf(itemScoreAll));

            if (hospitalSupervisioBiz.saveSchedule(resScheduleScore,userFlow) > 0) {
                model.addAttribute("resultId","200");
                model.addAttribute("resultType","交易成功");
            }
        }
        return "res/jswjw/hospitalSupervisio/status";
    }



    @RequestMapping(value = {"/saveHospitalScore"}, method = {RequestMethod.POST})
    public String saveHospitalScore(String subjectFlow,String expertTotal,String userFlow,Model model) {
        SysUser user = userMapper.selectByPrimaryKey(userFlow);
        ResHospSupervSubject subject = hospitalSupervisioBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
        subject.setEndTime(DateUtil.transDateTime(DateUtil.getCurrDateTime().substring(0,12)));
        subject.setReviewConfig(com.pinde.core.common.GlobalConstant.FLAG_N);
        //判断提交人是哪一位专家，保存分数
        if (subject.getLeaderOneFlow().equals(user.getUserFlow())){
            subject.setLeaderOneScore(expertTotal);
            subject.setLeaderOneEndTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
        }else if (subject.getLeaderTwoFlow().equals(user.getUserFlow())){
            subject.setLeaderTwoScore(expertTotal);
            subject.setLeaderTwoEndTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
        }else {
            model.addAttribute("resultId","500");
            model.addAttribute("resultType","服务器出错");
        }
        //两位专家都提交分数才能计算平均分
        if (StringUtil.isNotBlank(subject.getLeaderOneScore()) && StringUtil.isNotBlank(subject.getLeaderTwoScore())){
            BigDecimal allScore = new BigDecimal(subject.getLeaderOneScore()).add(new BigDecimal(subject.getLeaderTwoScore()));
            BigDecimal avgScore = allScore.divide(new BigDecimal(2));
            subject.setAvgScore(avgScore.toString());
        }
        //已提交专家数量
        if (StringUtil.isNotBlank(subject.getLeaderOneScore()) && StringUtil.isNotBlank(subject.getLeaderTwoScore())){
            subject.setLeaderSubNum("2");
        }else if (StringUtil.isNotBlank(subject.getLeaderOneScore()) || StringUtil.isNotBlank(subject.getLeaderTwoScore())){
            subject.setLeaderSubNum("1");
        }else {
            subject.setLeaderSubNum("0");
        }
        if (hospitalSupervisioBiz.updateHospSupervisioBySubjectFlow(subject)==1){
            model.addAttribute("resultId","200");
            model.addAttribute("resultType","交易成功");
            return "res/jswjw/hospitalSupervisio/status";
        }
        model.addAttribute("resultId","500");
        model.addAttribute("resultType","服务器出错");
        return "res/jswjw/hospitalSupervisio/status";
    }

    /**
     * 保存专业专家签名图片
     */
    @RequestMapping(value = "/saveUploadFile", method = {RequestMethod.POST})
    public String saveUploadFile(String userFlow, MultipartFile uploadFile, Model model) {
        Map<String, MultipartFile> fileMap = new LinkedHashMap<String, MultipartFile>();
        fileMap.put("operType", uploadFile);
        if (uploadFile != null) {
            String resultPath = hospitalSupervisioBiz.saveFileToDirs("", uploadFile, "supersivioSign");
            SysUser user = userMapper.selectByPrimaryKey(userFlow);
            user.setUserSignUrl(resultPath);
            int i = hospitalSupervisioBiz.editSupervisioUser(user);
            if (i>0){
                model.addAttribute("resultId","200");
                model.addAttribute("resultType","交易成功");
                String showPath=com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("upload_base_url"))+"/"+resultPath;
                model.addAttribute("showPath",showPath);
            }else {
                model.addAttribute("resultId","500");
                model.addAttribute("resultType","服务器出错");
            }
        } else {
            model.addAttribute("resultId","500");
            model.addAttribute("resultType","文件不能为空！");
        }
        return "res/jswjw/hospitalSupervisio/path";
    }
}
