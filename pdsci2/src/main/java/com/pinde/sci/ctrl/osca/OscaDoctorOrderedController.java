package com.pinde.sci.ctrl.osca;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResBaseBiz;
import com.pinde.sci.biz.jsres.IJsResGraduationApplyBiz;
import com.pinde.sci.biz.osca.IOscaBaseBiz;
import com.pinde.sci.biz.osca.IOscaDoctorOrderdeBiz;
import com.pinde.sci.biz.osca.IOscaDoctorRegistBiz;
import com.pinde.sci.biz.osca.IOscaDoctorScoreBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorRecruitBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.jsres.JsResAsseAuditListEnum;
import com.pinde.sci.enums.osca.AuditStatusEnum;
import com.pinde.sci.enums.osca.DoctorScoreEnum;
import com.pinde.sci.enums.osca.SignStatusEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.osca.OscaSkillsAssessmentExt;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/osca/oscaDoctorOrdered")
public class OscaDoctorOrderedController extends GeneralController {
    @Autowired
    private IOscaDoctorOrderdeBiz oscaDoctorOrderdeBiz;
    @Autowired
    private IOscaDoctorScoreBiz oscaDoctorScoreBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IOscaBaseBiz baseBiz;
    @Autowired
    private IJsResBaseBiz resbaseBiz;
    @Autowired
    private IOscaDoctorRegistBiz oscaDoctorRegistBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IJsResGraduationApplyBiz resGraduationApplyBiz;
    @Autowired
    private IResDoctorRecruitBiz doctorRecruitBiz;
    /**
     * 考核管理
     * @param oscaSkillsAssessmentExt
     * @param isLocal
     * @param liId
     * @param currentPage
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String doctorOrderedList(String flag,OscaSkillsAssessmentExt oscaSkillsAssessmentExt,String isLocal, String liId,
            Integer currentPage, HttpServletRequest request,Model model){
        String orgName="";
        String searchNotFull="";
        String searchFlag="N";
        if(oscaSkillsAssessmentExt!=null){
            orgName=oscaSkillsAssessmentExt.getOrgName();
            searchNotFull=oscaSkillsAssessmentExt.getSearchNotFull();
        }
        List<String> speIdList=new ArrayList<>();
        List<String> speNameList=new ArrayList<>();
        String speId="";
        String orgFlow="";
        String graduationYear="";
        String trainingSpeName ="";
        ResDoctor resDoctor=resDoctorBiz.findByFlow(GlobalContext.getCurrentUser().getUserFlow());
        if(resDoctor!=null){
            Map<String,Object> map=new HashMap<>();
            //一阶段学员
            if("WMFirst".equals(resDoctor.getTrainingTypeId())){
                trainingSpeName=resDoctor.getTrainingSpeName();
                switch(trainingSpeName)
                {
                    case "医学检验科":
                        speNameList.add("检验医学科");
                        break;
                    case "医学影像科":
                        speNameList.add("放射科");
                        speNameList.add("放射肿瘤科");
                        speNameList.add("核医学科");
                        speNameList.add("超声医学科");
                        break;
                    case "病理科":
                        speNameList.add("临床病理科");
                        break;
                    case "皮肤性病科":
                        speNameList.add("皮肤科");
                        break;
                    case "口腔科":
                        speNameList.add("口腔全科");
                        break;
                    case "全科方向（西医）":
                        speNameList.add("全科");
                        break;
                    case "助理全科":
                        speNameList.add("助理全科");
                        break;
                    default:
                        speNameList.add(trainingSpeName);
                }
                map.put("speNameList",speNameList);
                speIdList=oscaDoctorOrderdeBiz.searchTrainingSpeList(map);
                if(speNameList.size()==0){
                    speIdList=new ArrayList<>();
                }
            }else if(StringUtil.isNotBlank(resDoctor.getTrainingSpeName())
                    &&"助理全科".equals(resDoctor.getTrainingSpeName())){
                // 修改 助理全科只查助理全科 信息  需求人：徐开宏 修改时间：2020年7月17日
                speNameList.add("助理全科");
                map.put("speNameList",speNameList);
                speIdList=oscaDoctorOrderdeBiz.searchTrainingSpeList(map);
            }else {
                speId=resDoctor.getTrainingSpeId();
            }
            if(speIdList.size()==0){
                speId=resDoctor.getTrainingSpeId();
            }
            orgFlow=resDoctor.getOrgFlow();
        }
        List<SysOrg> orgList = new ArrayList<>();
        String recruitFlow = "";
        List<ResDoctorRecruit> resDoctorRecruits=oscaDoctorOrderdeBiz.selectDoctorGraduationYear(GlobalContext.getCurrentUser().getUserFlow());
        if(resDoctorRecruits!=null&&resDoctorRecruits.size()>0&&resDoctorRecruits.get(0)!=null){
            graduationYear=resDoctorRecruits.get(0).getGraduationYear();
            recruitFlow = resDoctorRecruits.get(0).getRecruitFlow();
            String userOrg="";
            if (StringUtil.isEmpty(resDoctorRecruits.get(0).getJointOrgFlow())){
                userOrg=resDoctorRecruits.get(0).getOrgFlow();
            }else {
                userOrg=resDoctorRecruits.get(0).getJointOrgFlow();
            }
            SysOrg sysOrg = orgBiz.readSysOrg(userOrg);
            SysOrg org = new SysOrg();
            org.setOrgCityId(sysOrg.getOrgCityId());
            orgList = orgBiz.queryAllSysOrg(org);
        }
        if(StringUtil.isNotBlank(graduationYear)){
            graduationYear=graduationYear.substring(0,4);
        }
        Map<String, Object> map=new HashMap<>();
        map.put("orgName",orgName);
        map.put("speId",speId);
        map.put("speIdList",speIdList);
        map.put("isLocal",isLocal);
        if("Y".equals(isLocal)){
            map.put("orgFlow",orgFlow);
        }else {
            map.put("orgList",orgList);
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<OscaSkillsAssessmentExt> skillsAssessmentList=oscaDoctorOrderdeBiz.skillsAssessmentList(map);
        if("N".equals(isLocal) && "secondLi".equals(liId)){
            if(StringUtil.isNotEmpty(recruitFlow)){
                JsresGraduationApply graduationApply = resGraduationApplyBiz.searchByRecruitFlow(recruitFlow,DateUtil.getYear());
                if(null != graduationApply && !JsResAsseAuditListEnum.GlobalPassed.getId().equals(graduationApply.getAuditStatusId())){
                    skillsAssessmentList = null;
                }
            }
            if(StringUtil.isNotEmpty(GlobalContext.getCurrentUser().getUserFlow())){
                List<JsresExamSignup> signupList = doctorRecruitBiz.readDoctorExanSignUps(GlobalContext.getCurrentUser().getUserFlow());
                if(CollectionUtils.isNotEmpty(signupList)){
                    for (JsresExamSignup jsresExamSignup : signupList) {
                        if(!JsResAsseAuditListEnum.GlobalPassed.getId().equals(jsresExamSignup.getAuditStatusId()) && !DateUtil.getYear().equals(jsresExamSignup.getSignupYear())){
                            skillsAssessmentList = null;
                        }
                    }
                }
            }

        }
        if(skillsAssessmentList!=null&&skillsAssessmentList.size()>0){
            OscaDoctorAssessment oscaDoctorAssessment=new OscaDoctorAssessment();
            for (OscaSkillsAssessmentExt osae:skillsAssessmentList) {
                if(osae!=null){
                    oscaDoctorAssessment.setClinicalFlow(osae.getClinicalFlow());
                    int doctorCount=oscaDoctorOrderdeBiz.countDoctorAssessment(oscaDoctorAssessment);
                    int overplus=osae.getAppointNum()-doctorCount;
                    osae.setOverplus(overplus+"");
                    oscaDoctorAssessment.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
                    List<OscaDoctorAssessment> doctorList=oscaDoctorOrderdeBiz.selectDoctorAssessment(oscaDoctorAssessment);
                    if(doctorList!=null&&doctorList.size()>0){
                        osae.setOscaDoctorAssessment(doctorList.get(0));
                    }
                    Map<String,String> timeList=baseBiz.queryOsaTime(osae.getClinicalFlow());
                    if(timeList!=null){
                        osae.setExamStartTimeList(timeList.get("EXAM_START_TIME"));
                        osae.setExamEndTimeList(timeList.get("EXAM_END_TIME"));
                    }
                }
            }
            if("on".equals(searchNotFull)){
                searchFlag="Y";
                List<OscaSkillsAssessmentExt> skillsAssessmentListTemp=new ArrayList<>();
                for (OscaSkillsAssessmentExt osae:skillsAssessmentList) {
                    if(osae!=null&&osae.getOverplus()!=null&&Integer.parseInt(osae.getOverplus())>0){
                        skillsAssessmentListTemp.add(osae);
                    }
                }
                skillsAssessmentList=skillsAssessmentListTemp;
            }
        }

        OscaDoctorAssessment doctor=new OscaDoctorAssessment();
        doctor.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
        List<OscaDoctorAssessment> doctorList=oscaDoctorOrderdeBiz.selectDoctorAssessment(doctor);
        OscaDoctorAssessment resultDoctor=null;
        if(doctorList!=null&&doctorList.size()>0){
            resultDoctor=doctorList.get(0);
        }
        Map<String, String> doctorMap=new HashMap<>();
        doctorMap.put("doctorFlow",GlobalContext.getCurrentUser().getUserFlow());
        OscaSkillsAssessmentExt doctorAssessmentInfo=null;
        List<OscaSkillsAssessmentExt> skillsAssessments=oscaDoctorOrderdeBiz.selectDoctorAssessmentInfo(doctorMap);
        if(skillsAssessments!=null&&skillsAssessments.size()>0){
            doctorAssessmentInfo=skillsAssessments.get(0);
        }
        String clinicalFlow="";
        String ticketNumber="";
        List<OscaSkillsAssessmentExt> osaTemp1=oscaDoctorOrderdeBiz.selectTicketInfo(doctorMap);
        if(osaTemp1!=null&&osaTemp1.size()>0){
            if(osaTemp1.get(0)!=null&&osaTemp1.get(0).getOscaDoctorAssessment()!=null){
                ticketNumber=osaTemp1.get(0).getOscaDoctorAssessment().getTicketNumber();
            }
        }
        if(doctorAssessmentInfo!=null&&doctorAssessmentInfo.getOscaDoctorAssessment()!=null){
            clinicalFlow=doctorAssessmentInfo.getClinicalFlow();
        }
        Map<String,Object> param = new HashMap<>();
        param.put("clinicalFlow",clinicalFlow);
        param.put("auditStatusId",AuditStatusEnum.Passed.getId());
        param.put("signStatusId", SignStatusEnum.SignIn.getId());
        param.put("ticketNumber", ticketNumber);
        param.put("doctorFlow",GlobalContext.getCurrentUser().getUserFlow());
        List<Map<String,Object>> gradeList = oscaDoctorScoreBiz.queryGradeList(param);
        int doctorScore=0;
        if(gradeList!=null&&gradeList.size()>0&&gradeList.get(0)!=null&&gradeList.get(0).get("EXAM_SCORE")!=null){
            String examScore=gradeList.get(0).get("EXAM_SCORE").toString();
            String[] scores=examScore.split(",");
            for (int i=0;i<scores.length;i++){
                if(scores[i]!=null&&!"*".equals(scores[i])){
                    doctorScore+=(int)(Double.parseDouble(scores[i])+0.5);
                }
            }
        }
        if(resDoctor==null||StringUtil.isBlank(resDoctor.getTrainingSpeId())|| StringUtil.isBlank(resDoctor.getTrainingSpeId())){
            skillsAssessmentList=new ArrayList<>();
        }
        int lastGraduationYear=0;
        if(StringUtil.isNotBlank(graduationYear)){
            lastGraduationYear=Integer.parseInt(graduationYear)-1;
        }
        //取近两年结业考核理论成绩
        List<ResScore> resScoreList=oscaDoctorOrderdeBiz.selectResScore(GlobalContext.getCurrentUser().getUserFlow(),graduationYear);
        List<ResScore> resScoreList1=oscaDoctorOrderdeBiz.selectResScore(GlobalContext.getCurrentUser().getUserFlow(),lastGraduationYear+"");
        if(StringUtil.isNotBlank(graduationYear)&&resScoreList!=null&&resScoreList.size()>0){
            model.addAttribute("resScore",resScoreList.get(0));
        }else{
            model.addAttribute("resScore",new ResScore());
        }
        if(lastGraduationYear!=0&&resScoreList1!=null&&resScoreList1.size()>0){
            model.addAttribute("resScore1",resScoreList1.get(0));
        }else{
            model.addAttribute("resScore1",new ResScore());
        }

        String hegeScore="60";
        //根据年份从res_pass_score_cfg取数据
        ResPassScoreCfg cfg = new ResPassScoreCfg();
        cfg.setCfgYear(graduationYear);
        ResPassScoreCfg resPassScoreCfg = resbaseBiz.readResPassScoreCfg(cfg);
        if(resPassScoreCfg!=null){
            hegeScore = resPassScoreCfg.getCfgPassScore();
            if(StringUtil.isBlank(hegeScore)){
                hegeScore="60";
            }
        }
        model.addAttribute("hegeScore",hegeScore);
        model.addAttribute("doctorScore",doctorScore+"");
        model.addAttribute("doctorAssessmentInfo",doctorAssessmentInfo);
        model.addAttribute("resultDoctor",resultDoctor);
        model.addAttribute("skillsAssessmentList",skillsAssessmentList);
        model.addAttribute("searchFlag",searchFlag);
        model.addAttribute("skillsAssessments",skillsAssessments);
        model.addAttribute("isLocal",isLocal);
        model.addAttribute("liId",liId);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("graduationYear",graduationYear);
        if(resDoctor!=null){
        model.addAttribute("oscaStudentSubmit",resDoctor.getOscaStudentSubmit());//通过OSCE注册学员
            if("Y".equals(resDoctor.getOscaStudentSubmit())){
                model.addAttribute("graduationYear",resDoctor.getGraduationYear());
            }
        }
        model.addAttribute("isOsca",GlobalContext.getCurrentUser().getIsOsca());

        String jspPath ="osca/doctor/orderedTitle_kh";

        //如果是OSCA新注册学员，首次登录提示注册成功信息
        SysUser user = GlobalContext.getCurrentUser();
        if(user!=null){
            OscaDoctorRegist search = new OscaDoctorRegist();
            search.setDoctorFlow(user.getUserFlow());
            List<OscaDoctorRegist> doctorRegistList = oscaDoctorRegistBiz.searchRegist(search);
            if(doctorRegistList!=null&&doctorRegistList.size()>0){
                OscaDoctorRegist doctorRegist = doctorRegistList.get(0);
                String showTip = doctorRegist.getShowTip();
                if(StringUtil.isBlank(showTip)){
                    doctorRegist.setShowTip("Y");
                    oscaDoctorRegistBiz.edit(doctorRegist);
                    model.addAttribute("showTip","Y");
                }
            }
        }
        if("mine".equals(flag)){
            jspPath =  "osca/doctor/orderedTitle_mine";
        }
//        else if("grade".equals(flag)){
//            jspPath =  "osca/doctor/orderedTitle_grade";
//        }
        return jspPath;
    }

    /**
     * 成绩管理查询
     */
    @RequestMapping("/gradeList")
    public String gradeList(Integer currentPage, String orgName, String clinicalYear,
                                  String speId,String ticketNumber, String actionTypeId,String gradeDoctorName, String trainCategoryId,
                                  String resultId,String order,String isLocal,HttpServletRequest request, Model model) throws IOException {
        List<OscaSkillsAssessmentExt> skillsAssessmentList=new ArrayList<>();
        Map<String, String> paramMap=new HashMap<>();
        paramMap.put("doctorFlow",GlobalContext.getCurrentUser().getUserFlow());
        paramMap.put("isLocal",isLocal);
        skillsAssessmentList=oscaDoctorOrderdeBiz.selectDoctorAssessmentList(paramMap);
        int stationNum = 0;
        if(skillsAssessmentList!=null&&skillsAssessmentList.size()>0){
            for (OscaSkillsAssessmentExt oscaSkillsAssessmentExt:skillsAssessmentList){
                String clinicalFlow = oscaSkillsAssessmentExt.getClinicalFlow();
                OscaSkillsAssessment skillsAssessment = baseBiz.queryDataByFlow(clinicalFlow);
                String subjectFlow = skillsAssessment.getSubjectFlow();
                List<OscaSubjectStation> subjectStationList = baseBiz.queryStationList(subjectFlow);
                if(subjectStationList!=null) {
                    int size = subjectStationList.size();
                    if (size > stationNum) {
                        stationNum = size;
                    }
                }
            }
        }
        model.addAttribute("stationNum",stationNum);
        Map<String,Object> paramMap2 = new HashMap<>();
        List<Integer> stationNumList = new ArrayList<>();
        for(int i=1;i<=stationNum;i++){
            stationNumList.add(i);
        }
        paramMap2.put("stationNumList",stationNumList);
        paramMap2.put("doctorFlow",GlobalContext.getCurrentUser().getUserFlow());
        paramMap2.put("isLocal",isLocal);
        paramMap2.put("auditStatusId",AuditStatusEnum.Passed.getId());
        paramMap2.put("signStatusId", SignStatusEnum.SignIn.getId());
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> resultMapList = oscaDoctorScoreBiz.getSingleGrade(paramMap2);
        for(Map<String,Object> m:resultMapList)
        {
            for(int i=1;i<=stationNum;i++){
                String v= (String) m.get("STATION"+i);
                if(StringUtil.isNotBlank(v))
                {
                    m.put("STATION"+i,v.split(","));
                }else{

                    m.put("STATION"+i,",".split(","));
                }
            }
        }
        model.addAttribute("resultMapList",resultMapList);
        model.addAttribute("stationNumList",stationNumList);
        return "osca/doctor/orderedTitle_grade";
    }

    @RequestMapping(value = {"/ordered"})
    @ResponseBody
    public String ordered(String clinicalFlow,String doctorFlow,String appointNum,String flag){
        OscaDoctorAssessment odaTemp=new OscaDoctorAssessment();
        odaTemp.setClinicalFlow(clinicalFlow);
        int doctorCount=oscaDoctorOrderdeBiz.countDoctorAssessment(odaTemp);
        int overplus=Integer.parseInt(appointNum)-doctorCount;
        if(overplus>0){
            //伪删除旧数据
//            if(!doctorFlow.equals("")){
//                OscaDoctorAssessment oda=new OscaDoctorAssessment();
//                oda.setDoctorFlow(doctorFlow);
//                List<OscaDoctorAssessment> list=oscaDoctorOrderdeBiz.selectDoctorAssessment(oda);
//                if(list!=null&&list.size()>0){
//                    oda=list.get(0);
//                }
//                oda.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
//                oscaDoctorOrderdeBiz.updateDoctorAssessment(oda);
//            }

            OscaSkillsAssessment oscaSkillsAssessment=oscaDoctorOrderdeBiz.selectSkillsAssessmentByClinicalFlow(clinicalFlow);
            OscaDoctorAssessment oda=new OscaDoctorAssessment();
            oda.setClinicalFlow(clinicalFlow);
            oda.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
            List<OscaDoctorAssessment> odaListTemp = oscaDoctorOrderdeBiz.selectDoctorAssessment(oda);
            if(oscaSkillsAssessment!=null){
                OscaDoctorAssessment oscaDoctorAssessment=new OscaDoctorAssessment();
                if(odaListTemp!=null&&odaListTemp.size()>0){
                    oscaDoctorAssessment=odaListTemp.get(0);
                    oscaDoctorAssessment.setAppointTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
                    oscaDoctorAssessment.setAuditStatusId(AuditStatusEnum.Passing.getId());
                    oscaDoctorAssessment.setAuditStatusName(AuditStatusEnum.Passing.getName());
                    GeneralMethod.setRecordInfo(oscaDoctorAssessment,false);
                    oscaDoctorOrderdeBiz.updateDoctorAssessment(oscaDoctorAssessment);
                    if("passed".equals(flag)){
                        oscaDoctorAssessment.setAppointTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
                        oscaDoctorAssessment.setAuditStatusId(AuditStatusEnum.Passed.getId());
                        oscaDoctorAssessment.setAuditStatusName(AuditStatusEnum.Passed.getName());
                        GeneralMethod.setRecordInfo(oscaDoctorAssessment,false);
                        oscaDoctorOrderdeBiz.updateDoctorAssessment(oscaDoctorAssessment);
                        baseBiz.auditAppoint(oscaDoctorAssessment.getRecordFlow(),AuditStatusEnum.Passed.getId(),null);
                    }
                }else{
                    oscaDoctorAssessment.setRecordFlow(PkUtil.getUUID());
                    oscaDoctorAssessment.setClinicalFlow(clinicalFlow);
                    oscaDoctorAssessment.setClinicalName(oscaSkillsAssessment.getClinicalName());
                    oscaDoctorAssessment.setClinicalYear(oscaSkillsAssessment.getClinicalYear());
                    oscaDoctorAssessment.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
                    oscaDoctorAssessment.setDoctorName(GlobalContext.getCurrentUser().getUserName());
                    oscaDoctorAssessment.setAppointTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
                    oscaDoctorAssessment.setCreateTime(DateUtil.getCurrentTime());
                    oscaDoctorAssessment.setAuditStatusId(AuditStatusEnum.Passing.getId());
                    oscaDoctorAssessment.setAuditStatusName(AuditStatusEnum.Passing.getName());
                    oscaDoctorAssessment.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
                    oscaDoctorAssessment.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                    oscaDoctorAssessment.setIsPass(DoctorScoreEnum.Miss.getId());
                    oscaDoctorAssessment.setIsPassName(DoctorScoreEnum.Miss.getName());
                    oscaDoctorAssessment.setIsAdminAudit(GlobalConstant.FLAG_N);
                    oscaDoctorOrderdeBiz.insertDoctorAssessment(oscaDoctorAssessment);
                    if("passed".equals(flag)){
                        oscaDoctorAssessment.setAuditStatusId(AuditStatusEnum.Passed.getId());
                        oscaDoctorAssessment.setAuditStatusName(AuditStatusEnum.Passed.getName());
                        oscaDoctorOrderdeBiz.updateDoctorAssessment(oscaDoctorAssessment);
                        baseBiz.auditAppoint(oscaDoctorAssessment.getRecordFlow(),AuditStatusEnum.Passed.getId(),null);
                    }
                }
                return GlobalConstant.SAVE_SUCCESSED;
            }else {
                return GlobalConstant.SAVE_FAIL;
            }
        }else{
            return GlobalConstant.OPERATE_FAIL;
        }
    }

    @RequestMapping(value = {"/changeOrdered"})
    @ResponseBody
    public String changeOrdered(String recordFlow){
        OscaDoctorAssessment oscaDoctorAssessment=new OscaDoctorAssessment();
        oscaDoctorAssessment=oscaDoctorOrderdeBiz.selectDoctorAssessmentByRecordFlow(recordFlow);
        oscaDoctorAssessment.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        oscaDoctorOrderdeBiz.updateDoctorAssessment(oscaDoctorAssessment);
        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping(value = {"/showTicket"})
    public String showTicket(String doctorFlow,String clinicalFlow,Model model){
        Map<String, String> doctorMap=new HashMap<>();
        doctorMap.put("doctorFlow",doctorFlow);
        if(StringUtil.isNotBlank(clinicalFlow)){
            doctorMap.put("clinicalFlow",clinicalFlow);
        }
        List<OscaSkillsAssessmentExt> oscaSkillsAssessmentExt=oscaDoctorOrderdeBiz.selectTicketInfo(doctorMap);
        String signUrl="";
        OscaSkillsAssessmentExt osaExt=new OscaSkillsAssessmentExt();
        if(oscaSkillsAssessmentExt!=null&&oscaSkillsAssessmentExt.size()>0){
            if(oscaSkillsAssessmentExt.get(0)!=null&&oscaSkillsAssessmentExt.get(0).getOscaDoctorAssessment()!=null){
                signUrl=oscaSkillsAssessmentExt.get(0).getOscaDoctorAssessment().getCodeInfo();
                osaExt=oscaSkillsAssessmentExt.get(0);
            }
        }
        model.addAttribute("signUrl",signUrl);
        model.addAttribute("oscaSkillsAssessmentExt",osaExt);
        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put("clinicalFlow",clinicalFlow);
        List<OscaSubjectStation> subjectStations = oscaDoctorOrderdeBiz.getStations(paramMap);
        List<String> partFlows = new ArrayList<>();
        if(subjectStations!=null&&subjectStations.size()>0){
            for(OscaSubjectStation station:subjectStations){
                String partFlow = station.getPartFlow();
                if(!partFlows.contains(partFlow)){
                    partFlows.add(partFlow);
                }
            }
        }
        model.addAttribute("subjectStations",subjectStations);
        model.addAttribute("partFlows",partFlows);
        DecimalFormat df = new DecimalFormat("#0.0%");
        double size = subjectStations.size();
        double w = 1/size;
        String width = df.format(w);
        model.addAttribute("width",width);
        return "/osca/doctor/doctorTicket";
    }

    @RequestMapping(value = {"/showPrintTicket"})
    public String showPrintTicket(String doctorFlow,Model model){
        Map<String, String> doctorMap=new HashMap<>();
        doctorMap.put("doctorFlow",doctorFlow);
        List<OscaSkillsAssessmentExt> oscaSkillsAssessmentExt=oscaDoctorOrderdeBiz.selectTicketInfo(doctorMap);
        String signUrl="";
        if(oscaSkillsAssessmentExt!=null&&oscaSkillsAssessmentExt.size()>0){
            if(oscaSkillsAssessmentExt.get(0)!=null){
                signUrl=oscaSkillsAssessmentExt.get(0).getOscaDoctorAssessment().getCodeInfo();
            }
        }
        model.addAttribute("signUrl",signUrl);
        model.addAttribute("oscaSkillsAssessmentExt",oscaSkillsAssessmentExt);
        return "/osca/doctor/printTicket";
    }

    @RequestMapping(value = {"/searchOldOrdered"})
    @ResponseBody
    public String searchOldOrdered(String doctorFlow,String isLocal,String clinicalYear,String orgFlow){
        if(doctorFlow!=""){
            OscaDoctorAssessment oscaDoctorAssessment=new OscaDoctorAssessment();
            oscaDoctorAssessment.setDoctorFlow(doctorFlow);
            oscaDoctorAssessment.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            List<OscaDoctorAssessment> oscaDoctorAssessments=oscaDoctorOrderdeBiz.selectDoctorAssessment(oscaDoctorAssessment);
            if(GlobalConstant.IS_EXAM_TEA_N.equals(isLocal)){
                Map<String, String> doctorMap=new HashMap<>();
                doctorMap.put("doctorFlow",doctorFlow);
                doctorMap.put("clinicalYear",clinicalYear);//预约的考核信息年份
//                doctorMap.put("orgFlow",orgFlow);//考核信息发布考点
                List<OscaSkillsAssessmentExt> osaList=oscaDoctorOrderdeBiz.selectAssessmentIsNotLocalOneYear(doctorMap);
                if(osaList!=null&&osaList.size()>0){
                    return GlobalConstant.NOT_NULL;
                }else{
                    if(oscaDoctorAssessments!=null&&oscaDoctorAssessments.size()>0){
                        return GlobalConstant.OPERATE_SUCCESSED;
                    }else {
                        return GlobalConstant.OPERATE_FAIL;
                    }
                }
            }else{
                if(oscaDoctorAssessments!=null&&oscaDoctorAssessments.size()>0){
                    return GlobalConstant.OPERATE_SUCCESSED;
                }else {
                    return GlobalConstant.OPERATE_FAIL;
                }
            }
        }else{
            doctorFlow=GlobalContext.getCurrentUser().getUserFlow();
            if(GlobalConstant.IS_EXAM_TEA_N.equals(isLocal)){
                Map<String, String> doctorMap=new HashMap<>();
                doctorMap.put("doctorFlow",doctorFlow);
                doctorMap.put("clinicalYear",clinicalYear);//预约的考核信息年份
                //doctorMap.put("orgFlow",orgFlow);//考核信息发布考点
                List<OscaSkillsAssessmentExt> osaList=oscaDoctorOrderdeBiz.selectAssessmentIsNotLocalOneYear(doctorMap);
                if(osaList!=null&&osaList.size()>0){
                    return GlobalConstant.NOT_NULL;
                }
            }else {
                return GlobalConstant.OPERATE_FAIL;
            }
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    //伪删除旧数据
    @RequestMapping(value = {"/removeOldOrdered"})
    public void removeOldOrdered(String doctorFlow){
        OscaDoctorAssessment oscaDoctorAssessment=new OscaDoctorAssessment();
        oscaDoctorAssessment.setDoctorFlow(doctorFlow);
        oscaDoctorAssessment.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        oscaDoctorOrderdeBiz.updateDoctorAssessment(oscaDoctorAssessment);
    }


    /**
     * 查看考点信息
     * @param clinicalFlow
     * @param model
     * @return
     */
    @RequestMapping("/showStationInfo")
    public String showStationInfo(String clinicalFlow,Model model){
        if(StringUtil.isNotBlank(clinicalFlow)){
            List<OscaSubjectStation> subjectStations = oscaDoctorOrderdeBiz.searchSubjectsForDoctor(clinicalFlow);
            model.addAttribute("subjectStations",subjectStations);
        }
        return "osca/doctor/subjectsInfo";
    }

    /**
     *查询我的预约信息
     * @return
     */
    @RequestMapping(value = "/searchOrderedRecord", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchOrderedRecord(String flag,String isLocal,String clinicalFlow,Integer currentPage, HttpServletRequest request,Model model){
        List<OscaSkillsAssessmentExt> skillsAssessmentList=new ArrayList<>();
        Map<String, String> map=new HashMap<>();
        map.put("doctorFlow",GlobalContext.getCurrentUser().getUserFlow());
        map.put("isLocal",isLocal);
        PageHelper.startPage(currentPage,getPageSize(request));
        skillsAssessmentList=oscaDoctorOrderdeBiz.selectDoctorAssessmentList(map);
        if(skillsAssessmentList!=null&&skillsAssessmentList.size()>0){
            OscaDoctorAssessment oscaDoctorAssessment=new OscaDoctorAssessment();
            for (OscaSkillsAssessmentExt osae:skillsAssessmentList) {
                if(osae!=null){
                    oscaDoctorAssessment.setClinicalFlow(osae.getClinicalFlow());
                    int doctorCount=oscaDoctorOrderdeBiz.countDoctorAssessment(oscaDoctorAssessment);
                    int overplus=osae.getAppointNum()-doctorCount;
                    osae.setOverplus(overplus+"");
                    oscaDoctorAssessment.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
                    List<OscaDoctorAssessment> doctorList=oscaDoctorOrderdeBiz.selectDoctorAssessment(oscaDoctorAssessment);
                    if(doctorList!=null&&doctorList.size()>0){
                        osae.setOscaDoctorAssessment(doctorList.get(0));
                    }
                }
            }
        }

        //预约信息详情展示
        OscaDoctorAssessment doctor=new OscaDoctorAssessment();
        doctor.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
        if(StringUtil.isNotBlank(clinicalFlow)){
            doctor.setClinicalFlow(clinicalFlow);
        }
        List<OscaDoctorAssessment> doctorList=oscaDoctorOrderdeBiz.selectDoctorAssessment(doctor);
        OscaDoctorAssessment resultDoctor=null;
        if(doctorList!=null&&doctorList.size()>0){
            resultDoctor=doctorList.get(0);
        }
        Map<String, String> doctorMap=new HashMap<>();
        doctorMap.put("doctorFlow",GlobalContext.getCurrentUser().getUserFlow());
        if(StringUtil.isNotBlank(clinicalFlow)){
            doctorMap.put("clinicalFlow",clinicalFlow);
        }
        OscaSkillsAssessmentExt doctorAssessmentInfo=null;
        List<OscaSkillsAssessmentExt> skillsAssessments=oscaDoctorOrderdeBiz.selectDoctorAssessmentInfo(doctorMap);
        if(skillsAssessments!=null&&skillsAssessments.size()>0){
            doctorAssessmentInfo=skillsAssessments.get(0);
        }
        clinicalFlow="";
        String ticketNumber="";
        List<OscaSkillsAssessmentExt> osaTemp1=oscaDoctorOrderdeBiz.selectTicketInfo(doctorMap);
        if(osaTemp1!=null&&osaTemp1.size()>0){
            if(osaTemp1.get(0)!=null&&osaTemp1.get(0).getOscaDoctorAssessment()!=null){
                ticketNumber=osaTemp1.get(0).getOscaDoctorAssessment().getTicketNumber();
            }
        }
        if(doctorAssessmentInfo!=null&&doctorAssessmentInfo.getOscaDoctorAssessment()!=null){
            clinicalFlow=doctorAssessmentInfo.getClinicalFlow();
        }
        Map<String,Object> param = new HashMap<>();
        param.put("clinicalFlow",clinicalFlow);
        param.put("auditStatusId",AuditStatusEnum.Passed.getId());
        param.put("signStatusId", SignStatusEnum.SignIn.getId());
        param.put("ticketNumber", ticketNumber);
        param.put("doctorFlow",GlobalContext.getCurrentUser().getUserFlow());
        List<Map<String,Object>> gradeList = oscaDoctorScoreBiz.queryGradeList(param);
        int doctorScore=0;
        if(gradeList!=null&&gradeList.size()>0&&gradeList.get(0)!=null&&gradeList.get(0).get("EXAM_SCORE")!=null){
            String examScore=gradeList.get(0).get("EXAM_SCORE").toString();
            String[] scores=examScore.split(",");
            for (int i=0;i<scores.length;i++){
                if(scores[i]!=null){
//                    doctorScore+=(int)(Double.parseDouble(scores[i])+0.5);
                }
            }
        }
        model.addAttribute("skillsAssessmentList",skillsAssessmentList);
        model.addAttribute("doctorScore",doctorScore+"");
        model.addAttribute("doctorAssessmentInfo",doctorAssessmentInfo);
        model.addAttribute("resultDoctor",resultDoctor);
//        model.addAttribute("liId",liId);
        model.addAttribute("isLocal",isLocal);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("clinicalFlow",clinicalFlow);
        return "/osca/doctor/orderedTitle_mine";
    }

    /**
     * 检查考试时间是否冲突
     * @return
     */
    @RequestMapping(value = {"/checkExamTime"})
    @ResponseBody
    public String checkExamTime(String doctorFlow,String examStartTime,String examEndTime,String clinicalFlow){
        if(StringUtil.isNotBlank(doctorFlow)&&StringUtil.isNotBlank(clinicalFlow)){
            int temp=0;
            List<OscaSkillsAssessmentTime> timeList=oscaDoctorOrderdeBiz.searchCheckTime(clinicalFlow);
            if(timeList!=null&&timeList.size()>0){
                for (OscaSkillsAssessmentTime t:timeList) {
                    examStartTime=t.getExamStartTime();
                    examEndTime=t.getExamEndTime();
                    if(StringUtil.isNotBlank(examStartTime)&&StringUtil.isNotBlank(examEndTime)){
                        Map<String, String> map=new HashMap<>();
                        map.put("doctorFlow",doctorFlow);
                        map.put("examStartTime",examStartTime);
                        map.put("examEndTime",examEndTime);
                        int count=oscaDoctorOrderdeBiz.countOrderedTime(map);
                        if(count>0){
                            temp++;
                        }
                    }
                }
            }
            if(temp>0){
                return GlobalConstant.NOT_NULL;
            }else{
                return GlobalConstant.NULL;
            }
        }
        return GlobalConstant.OPRE_FAIL;
    }

    @RequestMapping(value = {"/showScoreDetails"})
    public String showScoreDetails(String clinicalFlow,String doctorFlow,Model model){
        if(StringUtil.isNotBlank(clinicalFlow)&&StringUtil.isNotBlank(doctorFlow)){
            Map<String,Object> param = new HashMap<>();
            param.put("clinicalFlow",clinicalFlow);
            param.put("doctorFlow",doctorFlow);
            param.put("auditStatusId",AuditStatusEnum.Passed.getId());
            param.put("signStatusId", SignStatusEnum.SignIn.getId());
            List<Map<String,Object>> gradeList = oscaDoctorScoreBiz.queryGradeList(param);
            OscaSkillsAssessment osa = oscaDoctorScoreBiz.queryDataByFlow(clinicalFlow);
            String subjectFlow="";
            if(osa!=null){
                subjectFlow=osa.getSubjectFlow();
            }
            List<OscaSubjectStation> stationList = oscaDoctorScoreBiz.queryStationList(subjectFlow);
            model.addAttribute("gradeList",gradeList);
            model.addAttribute("stationList",stationList);
            model.addAttribute("clinicalFlow",clinicalFlow);
        }
        return "/osca/doctor/scoreDetails";
    }
}
