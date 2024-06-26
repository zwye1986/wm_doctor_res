package com.pinde.sci.ctrl.gyxjgl;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gyxjgl.IGyXjTutorBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.xjgl.XjNyqjStatusEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/gyxjgl/tutor")
public class GyXjglTutorController extends GeneralController {
    @Autowired
    private IGyXjTutorBiz tutorBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IUserBiz userBiz;

    //导师信息
    @RequestMapping("/editTutor")
    public String edit(String tutorFlow,Model model){
        SysUser user = GlobalContext.getCurrentUser();
        if(StringUtil.isBlank(tutorFlow)){
            tutorFlow=user.getUserFlow();
        }
        NydsOfficialDoctor tutor = tutorBiz.queryTutorByFlow(tutorFlow);
        List<NydsDoctorPaper> paperList = tutorBiz.queryPaperByFlow(tutorFlow);
        List<NydsDoctorTopic> topicList = tutorBiz.queryTopicByFlow(tutorFlow);
        List<SysDept> deptList = deptBiz.searchDeptByOrg(user.getOrgFlow());
        List<SysOrg> orgList = orgBiz.queryAllSysOrg(null);
        model.addAttribute("orgList",orgList);
        model.addAttribute("tutor",tutor);
        model.addAttribute("paperList",paperList);
        model.addAttribute("topicList",topicList);
        model.addAttribute("deptList", deptList);
        return "gyxjgl/tutor/editTutor";
    }

    //导师相关图片信息保存（identifyFlag特别处理上传控件标识）
    @RequestMapping(value="/imageUpload")
    @ResponseBody
    public String userHeadImgUpload(String tutorFlow,String identifyFlag,MultipartFile image){
        if(image!=null && image.getSize() > 0){
            return tutorBiz.uploadImg(tutorFlow,identifyFlag,image);
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    //保存导师信息
    @RequestMapping(value="/saveTutor")
    @ResponseBody
    public String saveTutor(String tabFlag,NydsOfficialDoctor tutor,NydsDoctorPaper paper,NydsDoctorTopic topic){
        if(StringUtil.isNotBlank(tutor.getSexId())){
            tutor.setSexName(UserSexEnum.getNameById(tutor.getSexId()));
        }
        if(StringUtil.isNotBlank(tutor.getZsSubjectId())){
            tutor.setZsSubjectId(tutor.getZsSubjectId().split(",")[1]);
        }
        if(StringUtil.isNotBlank(tutor.getRecruitSpeId())){
            tutor.setRecruitSpeName(DictTypeEnum.GyMajor.getDictNameById(tutor.getRecruitSpeId()));
        }
        if(StringUtil.isNotBlank(tutor.getSecondRecruitSpeId())){
            tutor.setSecondRecruitSpeName(DictTypeEnum.GyMajor.getDictNameById(tutor.getSecondRecruitSpeId()));
        }
        int result = tutorBiz.saveTutor(tabFlag,tutor,paper,topic);
        if(result>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }else if(result==-1){
            return "该论文已存在";
        }else if(result==-2){
            return "该课题已存在";
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 删除论文/课题信息
     */
    @RequestMapping("/delInfo")
    @ResponseBody
    public String delInfo(String tabFlag,String recordFlow){
        int num = tutorBiz.delPaperTopicByFlow(tabFlag,recordFlow);
        if (num>0) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 导师申请
     */
    @RequestMapping("/tutorApplication")
    public String tutorApplication(Model model) {
        SysUser user = GlobalContext.getCurrentUser();
        NydsOfficialDoctor tutor = tutorBiz.queryTutorByFlow(user.getUserFlow());
        model.addAttribute("tutor",tutor);
        return "gyxjgl/tutor/tutorApplication";
    }

    /**
     * 导师审核(培养单位、分委会、学位科)
     */
    @RequestMapping("/tutorAudit")
    public String tutorAudit(String role,NydsOfficialDoctor tutor,Integer currentPage,HttpServletRequest request, Model model,
                             String createTime2,String applyTime2,String xwkAuditTime2,String invalidTime2) {
        SysCfg start=cfgBiz.read("tutor_register_start_time");
        SysCfg end=cfgBiz.read("tutor_register_end_time");
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        SysUser sysUser = GlobalContext.getCurrentUser();
        List<NydsOfficialDoctor> tutorList = new ArrayList<>();
        tutor.setApplyFlag(GlobalConstant.FLAG_Y);
        if("pydw".equals(role)){
            tutor.setPydwOrgFlow(sysUser.getOrgFlow());
        }else{
            model.addAttribute("orgList",orgBiz.searchTrainOrgList());
            if("fwh".equals(role)){
                tutor.setFwhOrgFlow(sysUser.getDeptFlow());
            }else if("xwk".equals(role)){
                List<SysDept> deptList = deptBiz.searchDeptByOrg(sysUser.getOrgFlow());
                model.addAttribute("deptList",deptList);
            }
            List<SysOrg> orgList = orgBiz.queryAllSysOrg(null);
            model.addAttribute("orgList",orgList);
        }
        Map<String,String> params = new HashMap<>();
        params.put("createTime2",createTime2);
        params.put("applyTime2",applyTime2);
        params.put("xwkAuditTime2",xwkAuditTime2);
        params.put("invalidTime2",invalidTime2);
        PageHelper.startPage(currentPage, getPageSize(request));
        tutorList = tutorBiz.queryDoctorList(tutor,params);
        model.addAttribute("tutorList",tutorList);
        return "gyxjgl/tutor/tutorAudit";
    }

    /**
     * 导师详情
     */
    @RequestMapping("/detailInfo")
    public String detailInfo(String tutorFlow,Model model) {
        if(StringUtil.isNotBlank(tutorFlow)){
            NydsOfficialDoctor tutor = tutorBiz.queryTutorByFlow(tutorFlow);
            List<NydsDoctorPaper> paperList = tutorBiz.queryPaperByFlow(tutorFlow);
            List<NydsDoctorTopic> topicList = tutorBiz.queryTopicByFlow(tutorFlow);
            model.addAttribute("tutor",tutor);
            model.addAttribute("paperList",paperList);
            model.addAttribute("topicList",topicList);
        }
        return "gyxjgl/tutor/tutorDetail";
    }
    /**
     * 打印通过审核导师信息
     */
    @RequestMapping("/printDetail")
    public String printDetail(String tutorFlow,Model model) {
        if(StringUtil.isNotBlank(tutorFlow)){
            NydsOfficialDoctor tutor = tutorBiz.queryTutorByFlow(tutorFlow);
            List<NydsDoctorPaper> paperList = tutorBiz.queryPaperByFlow(tutorFlow);
            List<NydsDoctorTopic> topicList = tutorBiz.queryTopicByFlow(tutorFlow);
            model.addAttribute("tutor",tutor);
            model.addAttribute("paperList",paperList);
            model.addAttribute("topicList",topicList);
        }
        return "gyxjgl/tutor/printDetail";
    }
    /**
     * 提交导师申请
     */
    @RequestMapping("/applyOption")
    @ResponseBody
    public String applyOption(String tutorFlow){
        NydsOfficialDoctor doctor=tutorBiz.queryTutorByFlow(tutorFlow);
        if(doctor!=null&&GlobalConstant.FLAG_Y.equals(doctor.getBlockFlag())){
            return "您已失去导师申请资格！";
        }
        int num = tutorBiz.applyOption(tutorFlow);
        if (num>0) {
            return GlobalConstant.OPRE_SUCCESSED;
        }
        return GlobalConstant.OPRE_FAIL;
    }

    /**
     * 导师名录
     */
    @RequestMapping("/searchDoctorList")
    public String searchDoctorList(NydsOfficialDoctor officialDoctor,Model model,Integer currentPage, HttpServletRequest request){
        List<SysDept> deptList = new ArrayList<>();
        SysOrg sysOrg=orgBiz.readSysOrgByName("广州医科大学");
        if(sysOrg!=null&&StringUtil.isNotBlank(sysOrg.getOrgFlow())){
            deptList = deptBiz.searchDeptByOrg(sysOrg.getOrgFlow());
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        officialDoctor.setXwkAuditStatusId("Passed");
        officialDoctor.setShowFlag(GlobalConstant.FLAG_Y);
        List<NydsOfficialDoctor> doctorList=tutorBiz.queryDoctorList(officialDoctor);
        model.addAttribute("doctorList",doctorList);
        model.addAttribute("deptList",deptList);
        model.addAttribute("orgList",orgBiz.searchTrainOrgList());
        return "gyxjgl/tutor/tutorList";
    }
    /**
     * 导师信息
     */
    @RequestMapping("/doctorInfo")
    public String doctorInfo(String doctorFlow, Model model) {
        if(StringUtil.isNotBlank(doctorFlow)){
            NydsOfficialDoctor tutor = tutorBiz.queryTutorByFlow(doctorFlow);
            List<NydsDoctorPaper> paperList = tutorBiz.queryPaperByFlow(doctorFlow);
            List<NydsDoctorTopic> topicList = tutorBiz.queryTopicByFlow(doctorFlow);
            model.addAttribute("tutor",tutor);
            model.addAttribute("paperList",paperList);
            model.addAttribute("topicList",topicList);
        }
        return "gyxjgl/tutor/tutorInfo";
    }

    @RequestMapping("/showEditTopicOrPaper")
    public String showEditTopicOrPaper(String flow,String tabFlag,Model model){
        if("paper".equals(tabFlag)){
            NydsDoctorPaper paper=tutorBiz.queryPaperByRecordFlow(flow);
            model.addAttribute("paper",paper);
            return "gyxjgl/tutor/editPaper";
        }
        if("topic".equals(tabFlag)){
            NydsDoctorTopic topic=tutorBiz.queryTopicByRecordFlow(flow);
            model.addAttribute("topic",topic);
            return "gyxjgl/tutor/editTopic";
        }
        return "";
    }

    /**
     * 审核操作
     */
    @RequestMapping("/auditOpt")
    @ResponseBody
    public String auditOpt(String role,String statusId,NydsOfficialDoctor tutor){
        int num = tutorBiz.auditOption(role,statusId,tutor);
        if (num>0) {
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }
    /**
     * 退回重审，导师从新提交并审核
     */
    @RequestMapping("/backAuditOpt")
    @ResponseBody
    public String backAuditOpt(String tutorFlow){
        NydsOfficialDoctor doctor=tutorBiz.queryTutorByFlow(tutorFlow);
        if(doctor!=null&&GlobalConstant.FLAG_Y.equals(doctor.getBlockFlag())){
            return "您已失去导师申请资格！";
        }
        int num = tutorBiz.backAuditOption(tutorFlow);
        if (num>0) {
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }
    /**
     * 资格失效导师 从新提交并审核
     */
    @RequestMapping("/backAuditBatchOpt")
    @ResponseBody
    public String backAuditBatchOpt(){
        NydsOfficialDoctor officialDoctor = new NydsOfficialDoctor();
        officialDoctor.setXwkAuditStatusId("Passed");
        List<NydsOfficialDoctor> doctorList=tutorBiz.queryDoctorList(officialDoctor);
        if(null != doctorList && !doctorList.isEmpty()){
            int num = 0;
            for(NydsOfficialDoctor doc : doctorList){
                //失效时间
                String invalidTime = DateUtil.addDate(DateUtil.addYear(doc.getXwkAuditTime(),4),1);
                String currTime = DateUtil.getCurrDate();
                if(currTime.compareTo(invalidTime)>=0){
                    num += tutorBiz.backAuditOption(doc.getDoctorFlow());
                }
            }
            if (num>0) {
                return GlobalConstant.OPERATE_SUCCESSED;
            }else{
                return "未有资格已到失效时间的导师！";
            }
        }
        return GlobalConstant.OPERATE_FAIL;
    }
    /**
     * 导师列表（审核通过）
     */
    @RequestMapping("/passedTutorList")
    public String passedTutorList(NydsOfficialDoctor tutor,Integer currentPage,HttpServletRequest request, Model model,
                                  String createTime2,String applyTime2,String xwkAuditTime2,String invalidTime2,String invalidYear) {
        SysUser sysUser = GlobalContext.getCurrentUser();
        if(StringUtil.isNotBlank(sysUser.getOrgFlow())){
            List<SysDept> deptList = deptBiz.searchDeptByOrg(sysUser.getOrgFlow());
            model.addAttribute("deptList",deptList);
        }
        model.addAttribute("orgList",orgBiz.searchTrainOrgList());
        Map<String,String> params = new HashMap<>();
        params.put("createTime2",createTime2);
        params.put("applyTime2",applyTime2);
        params.put("xwkAuditTime2",xwkAuditTime2);
        params.put("invalidTime2",invalidTime2);
        params.put("invalidYear",invalidYear);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<NydsOfficialDoctor> tutorList = tutorBiz.queryDoctorList(tutor,params);
        model.addAttribute("tutorList",tutorList);
        return "gyxjgl/tutor/passedTutorList";
    }

    /**
     * 导师信息导入
     */
    @RequestMapping(value="/importTutorExcel")
    @ResponseBody
    public String importTutorExcel(MultipartFile file) throws Exception{
        if(file.getSize() > 0){
            try{
                int result = tutorBiz.importTutorExcel(file);
                if(GlobalConstant.ZERO_LINE != result){
                    return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
                }else{
                    return GlobalConstant.UPLOAD_FAIL;
                }
            }catch(Exception re){
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }
    /**
     * 删除导师信息（账号）
     */
    @RequestMapping("/delTutor")
    @ResponseBody
    public String delTutor(String tutorFlow){
        int num = tutorBiz.delTutorByFlow(tutorFlow);
        if (num>0) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 导师列表导出（审核通过）
     */
    @RequestMapping("/exportTutors")
    public void exportTutors(NydsOfficialDoctor tutor, HttpServletResponse response,
                             String createTime2,String applyTime2,String xwkAuditTime2,String invalidTime2,String invalidYear)throws Exception {
        Map<String,String> params = new HashMap<>();
        params.put("createTime2",createTime2);
        params.put("applyTime2",applyTime2);
        params.put("xwkAuditTime2",xwkAuditTime2);
        params.put("invalidTime2",invalidTime2);
        params.put("invalidYear",invalidYear);
        List<NydsOfficialDoctor> tutorList = tutorBiz.queryDoctorList(tutor,params);
        if(tutorList!=null&&tutorList.size()>0){
            for (NydsOfficialDoctor doc:tutorList) {
                if(StringUtil.isNotBlank(doc.getDoctorFlow())){
                    SysUser user=userBiz.readSysUser(doc.getDoctorFlow());
                    if(user!=null){
                        doc.setDoctorFlow(user.getUserCode());
                    }
                }
                if(!XjNyqjStatusEnum.UnPassed.getId().equals(doc.getPydwAuditStatusId())){
                    doc.setPydwAuditAdvice("");
                }
                if(!XjNyqjStatusEnum.UnPassed.getId().equals(doc.getFwhAuditStatusId())){
                    doc.setFwhAuditAdvice("");
                }
                if(!XjNyqjStatusEnum.UnPassed.getId().equals(doc.getXwkAuditStatusId())){
                    doc.setXwkAuditAdvice("");
                }
                if(GlobalConstant.FLAG_Y.equals(doc.getStopRecruit())){
                    doc.setStopRecruit("是");
                }else if(GlobalConstant.FLAG_N.equals(doc.getStopRecruit())){
                    doc.setStopRecruit("否");
                }
                doc.setCreateTime(DateUtil.transDate(doc.getCreateTime()));
                if(StringUtil.isNotBlank(doc.getXwkAuditTime())){
                    //modifyTime存储失效时间
                    doc.setModifyTime(DateUtil.addDate(DateUtil.addYear(doc.getXwkAuditTime(),4),1));
                }else{
                    doc.setModifyTime("");
                }
            }
        }
        String []titles = new String[]{
                "doctorFlow:用户名",
                "doctorName:导师姓名",
                "idNo:身份证号",
                "oneLevelSubjectName:申报学科",
                "twoLevelSubjectName:二级学科",
                "birthDay:出生年月",
                "sexName:性别",
                "technicalTitleName:技术职称",
                "degreeName:学位",
                "mobilePhone:手机",
                "workPhone:办公电话",
                "emailNo:电子邮箱",
                "workUnit:工作单位",
                "academicActivities:学术任职",
                "researchDirection:研究方向",
                "academicMonographs:学术专著",
                "awardSituation:个人荣誉",
                "researchDescribe:简介",
                "headUrl:照片",
                "stopRecruit:是否暂停招生",
                "doctorTypeName:导师类型",
                "fwhOrgName:分委员会",
                "pydwOrgName:培养单位",
                "zsCategoryName:招生门类",
                "zsSubjectName:招生学科",
                "recruitSpeName:招生专业",
                "zdyjsMasterNum:在读硕士人数",
                "zdyjsDoctorNum:在读博士人数",
                "inserviceDoctorNum:在读在职博士人数",
                "sbyjsMasterNum:申招硕士指标",
                "sbyjsDoctorNum:申招博士指标",
                "declareDoctorNum:申招在职博士指标",
                "paperMasterRate:硕士论文盲审通过率",
                "paperDoctorRate:博士论文盲审通过率",
                "pyyjsAwardDescribe:研究生论文获奖情况",
                "pyyjsOtherDescribe:研究生其他获奖情况",
                "pydwAuditStatusName:一级审核状态",
                "pydwAuditAdvice:一级审核不通过原因",
                "fwhAuditStatusName:二级审核状态",
                "fwhAuditAdvice:二级审核不通过原因",
                "xwkAuditStatusName:三级审核状态",
                "xwkAuditAdvice:三级审核不通过原因",
                "createTime:新增时间",
                "applyTime:申请时间",
                "xwkAuditTime:认定时间",
                "modifyTime:失效时间"
        };
        ExcleUtile.exportSimpleExcleByObjs(titles, tutorList, response.getOutputStream());
        String fileName = "导师信息表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
    /**
     * 学校批量审核操作
     */
    @RequestMapping("/batchAudit")
    @ResponseBody
    public String batchAudit(String role,String jsondata){
        if(StringUtil.isNotBlank(jsondata)) {
            String statusId="Passed";
            Map<String, Object> mp = JSON.parseObject(jsondata, Map.class);
            List<String> doctorFlows = (List<String>) mp.get("doctorFlows");
            if(doctorFlows!=null&&doctorFlows.size()>0){
                for (String flow:doctorFlows) {
                    NydsOfficialDoctor tutor = tutorBiz.queryTutorByFlow(flow);
                    if(tutor!=null){
                        int num = tutorBiz.auditOption(role,statusId,tutor);
                        if (num==0) {
                            return GlobalConstant.OPERATE_FAIL;
                        }
                    }else {
                        return GlobalConstant.OPERATE_FAIL;
                    }
                }
            }
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }/**
     * 异动导师
     */
    @RequestMapping("/tutorChange")
    public String tutorChange(String role,NydsOfficialDoctor tutor,Integer currentPage,HttpServletRequest request, Model model,
                              String createTime2,String applyTime2,String xwkAuditTime2,String retireTime2,String blockTime2) {
        SysUser sysUser = GlobalContext.getCurrentUser();
        List<NydsOfficialDoctor> tutorList = new ArrayList<>();
        if("pydw".equals(role)){
            tutor.setPydwOrgFlow(sysUser.getOrgFlow());
        }else{
            model.addAttribute("orgList",orgBiz.searchTrainOrgList());
            if("fwh".equals(role)){
                tutor.setFwhOrgFlow(sysUser.getDeptFlow());
            }else if("xwk".equals(role)){
                List<SysDept> deptList = deptBiz.searchDeptByOrg(sysUser.getOrgFlow());
                model.addAttribute("deptList",deptList);
            }
        }
        Map<String,String> params = new HashMap<>();
        params.put("createTime2",createTime2);
        params.put("applyTime2",applyTime2);
        params.put("xwkAuditTime2",xwkAuditTime2);
        params.put("retireTime2",retireTime2);
        params.put("blockTime2",blockTime2);
        PageHelper.startPage(currentPage, getPageSize(request));
        tutorList = tutorBiz.queryDoctorList(tutor,params);
        model.addAttribute("tutorList",tutorList);
        return "gyxjgl/tutor/tutorChange";
    }

    /**
     * 导师列表导出（异动列表）
     */
    @RequestMapping("/exportFromChange")
    public void exportFromChange(NydsOfficialDoctor tutor, HttpServletResponse response,String createTime2,String applyTime2,
                                 String xwkAuditTime2,String retireTime2,String blockTime2,String invalidYear)throws Exception {
        Map<String,String> params = new HashMap<>();
        params.put("createTime2",createTime2);
        params.put("applyTime2",applyTime2);
        params.put("xwkAuditTime2",xwkAuditTime2);
        params.put("retireTime2",retireTime2);
        params.put("blockTime2",blockTime2);
        params.put("invalidYear",invalidYear);
        List<NydsOfficialDoctor> tutorList = tutorBiz.queryDoctorList(tutor,params);
        if(tutorList!=null&&tutorList.size()>0){
            for (NydsOfficialDoctor doc:tutorList) {
                if(StringUtil.isNotBlank(doc.getDoctorFlow())){
                    SysUser user=userBiz.readSysUser(doc.getDoctorFlow());
                    if(user!=null){
                        doc.setDoctorFlow(user.getUserCode());
                    }
                }
                if(!XjNyqjStatusEnum.UnPassed.getId().equals(doc.getPydwAuditStatusId())){
                    doc.setPydwAuditAdvice("");
                }
                if(!XjNyqjStatusEnum.UnPassed.getId().equals(doc.getFwhAuditStatusId())){
                    doc.setFwhAuditAdvice("");
                }
                if(!XjNyqjStatusEnum.UnPassed.getId().equals(doc.getXwkAuditStatusId())){
                    doc.setXwkAuditAdvice("");
                }
                if(GlobalConstant.FLAG_Y.equals(doc.getStopRecruit())){
                    doc.setStopRecruit("是");
                }else if(GlobalConstant.FLAG_N.equals(doc.getStopRecruit())){
                    doc.setStopRecruit("否");
                }
                doc.setCreateTime(DateUtil.transDate(doc.getCreateTime()));
                if(StringUtil.isNotBlank(doc.getXwkAuditTime())){
                    //modifyTime存储失效时间
                    doc.setModifyTime(DateUtil.addDate(DateUtil.addYear(doc.getXwkAuditTime(),4),1));
                }else{
                    doc.setModifyTime("");
                }
                if(StringUtil.isNotBlank(doc.getBlockFlag())){
                    if(GlobalConstant.FLAG_Y.equals(doc.getBlockFlag())){
                        doc.setBlockFlag("是");
                    }else{
                        doc.setBlockFlag("否");
                    }
                }else{
                    doc.setBlockFlag("否");
                }
            }
        }
        String []titles = new String[]{
                "doctorFlow:用户名",
                "doctorName:导师姓名",
                "idNo:身份证号",
                "oneLevelSubjectName:申报学科",
                "twoLevelSubjectName:二级学科",
                "birthDay:出生年月",
                "sexName:性别",
                "technicalTitleName:技术职称",
                "degreeName:学位",
                "mobilePhone:手机",
                "workPhone:办公电话",
                "emailNo:电子邮箱",
                "workUnit:工作单位",
                "academicActivities:学术任职",
                "researchDirection:研究方向",
                "academicMonographs:学术专著",
                "awardSituation:个人荣誉",
                "researchDescribe:简介",
                "headUrl:照片",
                "stopRecruit:是否暂停招生",
                "doctorTypeName:导师类型",
                "fwhOrgName:分委员会",
                "pydwOrgName:培养单位",
                "zsCategoryName:招生门类",
                "zsSubjectName:招生学科",
                "recruitSpeName:招生专业",
                "zdyjsMasterNum:在读硕士人数",
                "zdyjsDoctorNum:在读博士人数",
                "inserviceDoctorNum:在读在职博士人数",
                "sbyjsMasterNum:申招硕士指标",
                "sbyjsDoctorNum:申招博士指标",
                "declareDoctorNum:申招在职博士指标",
                "paperMasterRate:硕士论文盲审通过率",
                "paperDoctorRate:博士论文盲审通过率",
                "pyyjsAwardDescribe:研究生论文获奖情况",
                "pyyjsOtherDescribe:研究生其他获奖情况",
                "pydwAuditStatusName:一级审核状态",
                "pydwAuditAdvice:一级审核不通过原因",
                "fwhAuditStatusName:二级审核状态",
                "fwhAuditAdvice:二级审核不通过原因",
                "xwkAuditStatusName:三级审核状态",
                "xwkAuditAdvice:三级审核不通过原因",
                "createTime:新增时间",
                "applyTime:申请时间",
                "xwkAuditTime:认定时间",
                "modifyTime:失效时间",
                "blockFlag:是否停用"
        };
        ExcleUtile.exportSimpleExcleByObjsAllString(titles, tutorList, response.getOutputStream());
        String fileName = "导师信息表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    /**
     * 批量停用/启用数据
     */
    @RequestMapping("/blockInfoBatchOpt")
    @ResponseBody
    public String blockInfoBatchOpt(String [] recordLst,String opeFlag){
        int num=0;
        List<String> doctorFlows = Arrays.asList(recordLst);
        for(int i=0;i<doctorFlows.size();i++){
            NydsOfficialDoctor tutor=tutorBiz.queryTutorByFlow(doctorFlows.get(i));
            if(tutor!=null){
                tutor.setBlockFlag(opeFlag);
                if(GlobalConstant.FLAG_Y.equals(opeFlag)){
                    tutor.setBlockTime(DateUtil.getCurrDate());
                }else{
                    tutor.setBlockTime("");
                }
                num += tutorBiz.blockTutorByFlow(tutor);
            }
        }
        if (num>0) {
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }
    //导师信息(异动)
    @RequestMapping("/changeTutor")
    public String changeTutor(String tutorFlow,String changeFlag,Model model){
        SysUser user = GlobalContext.getCurrentUser();
        if(StringUtil.isBlank(tutorFlow)){
            tutorFlow=user.getUserFlow();
        }
        NydsOfficialDoctor tutor = tutorBiz.queryTutorByFlow(tutorFlow);
        List<NydsDoctorPaper> paperList = tutorBiz.queryPaperByFlow(tutorFlow);
        List<NydsDoctorTopic> topicList = tutorBiz.queryTopicByFlow(tutorFlow);
        List<SysDept> deptList = deptBiz.searchDeptByOrg(user.getOrgFlow());
        List<SysOrg> orgList = orgBiz.searchTrainOrgList();
        model.addAttribute("orgList",orgList);
        model.addAttribute("tutor",tutor);
        model.addAttribute("paperList",paperList);
        model.addAttribute("topicList",topicList);
        model.addAttribute("deptList", deptList);
        model.addAttribute("changeFlag", changeFlag);
        return "gyxjgl/tutor/changeTutor";
    }
    /**
     * 导师状态停用/启用
     */
    @RequestMapping("/blockInfo")
    @ResponseBody
    public String blockInfo(String flag,String doctorFlow){
        int num =0;
        if(StringUtil.isNotBlank(doctorFlow)){
            NydsOfficialDoctor tutor=tutorBiz.queryTutorByFlow(doctorFlow);
            if(tutor!=null){
                tutor.setBlockFlag(flag);
                if(GlobalConstant.FLAG_Y.equals(flag)){
                    tutor.setBlockTime(DateUtil.getCurrDate());
                }else{
                    tutor.setBlockTime("");
                }
                num = tutorBiz.blockTutorByFlow(tutor);
            }
        }
        if (num>0) {
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }
    /**
     * 对接人事系统导师数据
     */
    @RequestMapping("/impOaDate")
    @ResponseBody
    public String impOaDate(){
        int num = tutorBiz.dockingTutorDate();
        if (num>0) {
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }
}
