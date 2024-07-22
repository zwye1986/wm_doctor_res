package com.pinde.sci.ctrl.hbres;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.hbres.IHbResGraduationApplyBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.jsres.IJsResRecBiz;
import com.pinde.sci.biz.jsres.ISchRotationDeptAfterBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SchRotationDeptMapper;
import com.pinde.sci.enums.jsres.JsResAuditStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.model.mo.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/hbres/asse")
public class HbresDoctorAsseController extends GeneralController {
    @Autowired
    private IJsResDoctorBiz jsResDoctorBiz;
    @Autowired
    private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;
    @Autowired
    private OrgBizImpl orgBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IResRecBiz resRecBiz;
    @Autowired
    private ISchRotationDeptAfterBiz afterBiz;
    @Autowired
    private IResScoreBiz resScoreBiz;
    @Autowired
    private ISchRotationBiz rotationBiz;
    @Autowired
    private ISchArrangeResultBiz resultBiz;
    @Autowired
    private ISchRotationGroupBiz groupBiz;
    @Autowired
    private SchRotationDeptMapper rotationDeptMapper;
    @Autowired
    private IResDoctorProcessBiz processBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private ISchRotationDeptBiz schRotationDeptBiz;
    @Autowired
    private IJsResRecBiz jsResRecBiz;
    @Autowired
    private ISchDoctorDeptBiz doctorDeptBiz;
    @Autowired
    private ISchRotationDeptBiz rotationDeptBiz;
    public static final String SCOREYEAR_NOT_FIND="请选择成绩年份";
    @Autowired
    private IHbResGraduationApplyBiz graduationApplyBiz;
    @Autowired
    private IFileBiz fileBiz;

    @RequestMapping(value="/recruitDetail")
    public String recruitDetail(String recruitFlow,Model model,String applyYear) throws DocumentException {

        AsseInfo(recruitFlow,model,applyYear);
        AsseFile(recruitFlow,applyYear,model);
        return "hbres/asse/asseApplication/schDetail";
    }
    @RequestMapping(value="/showScore")
    public String showScore(String recruitFlow,String scoreFlow,Model model) throws DocumentException {

        //培训记录
        ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
        model.addAttribute("recruit", recruit);
        ResScore score=resScoreBiz.searchByScoreFlow(scoreFlow);
        model.addAttribute("score",score);
        //是否是全科、助理全科、全科方向（中医）、全科方向（西医）
        String isAssiGeneral = "";
        if("0700".equals(recruit.getSpeId())||"51".equals(recruit.getSpeId())||"52".equals(recruit.getSpeId())
                ||"18".equals(recruit.getSpeId())||"50".equals(recruit.getSpeId())){
            isAssiGeneral = "Y";
        }else {
            isAssiGeneral = "N";
        }
        model.addAttribute("isAssiGeneral",isAssiGeneral);

        Map<String,String> extScore=new HashMap<String,String>();
        String content = null==score ? "":score.getExtScore();
        if(StringUtil.isNotBlank(content)) {
            Document doc = DocumentHelper.parseText(content);
            Element root = doc.getRootElement();
            Element extScoreInfo = root.element("extScoreInfo");
            if (extScoreInfo != null) {
                List<Element> extInfoAttrEles = extScoreInfo.elements();
                if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                    for (Element attr : extInfoAttrEles) {
                        String attrName = attr.getName();
                        String attrValue = attr.getText();
                        extScore.put(attrName, attrValue);
                    }
                }
            }
        }
        model.addAttribute("extScore",extScore);
        //附件
        List<PubFile> files = fileBiz.findFileByTypeFlow("asseApplication",recruitFlow);
        PubFile file = null;
        if(files != null && files.size() > 0){
            file = files.get(0);
            model.addAttribute("file",file);
        }
        return "hbres/asse/asseApplication/showScore";
    }

    @RequestMapping(value="/showAllInfoMain")
    public String showAllInfoMain(String doctorFlow,Model model,String isShow) throws DocumentException {
        //培训记录
        ResDoctor docotr = resDoctorBiz.readDoctor(doctorFlow);
        model.addAttribute("docotr", docotr);
        return "hbres/asse/asseApplication/showAllInfo/main";
    }

    @RequestMapping(value="/AsseInfo")
    public String AsseInfo(String doctorFlow,Model model,String applyYear) throws DocumentException {

        //个人信息
        ResDoctor resDoctor = resDoctorBiz.readDoctor(doctorFlow);
        if(resDoctor!=null) {
            SysUser sysUser = userBiz.readSysUser(doctorFlow);
            PubUserResume pubUserResume = userResumeBiz.readPubUserResume(doctorFlow);
            Map<String, String> practicingMap = new HashMap<>();
            if (pubUserResume != null) {
                String xmlContent = pubUserResume.getUserResume();
                BaseUserResumeExtInfoForm userResumeExt = null;
                if (StringUtil.isNotBlank(xmlContent)) {
                    //xml转换成JavaBean
                    userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, BaseUserResumeExtInfoForm.class);
                    if (userResumeExt != null) {
                        if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                            List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
                            if (sysDictList != null && !sysDictList.isEmpty()) {
                                for (SysDict dict : sysDictList) {
                                    if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                        if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
                                            userResumeExt.setGraduatedName(dict.getDictName());
                                        }
                                    }
                                }

                            }
                        }
                        model.addAttribute("userResumeExt", userResumeExt);
                    }
                }
                JsresGraduationApply jsresGraduationApply = graduationApplyBiz.searchByRecruitFlow(resDoctor.getDoctorFlow(), applyYear);
                //保存医师培训时间
                String endTime = "";
                //培养年限
                String sessionNumber = resDoctor.getSessionNumber();
                if (StringUtil.isNotBlank(sessionNumber)) {
                    String startTime = "";
                    if(StringUtil.isNotBlank(resDoctor.getInHosDate())){
                        startTime = resDoctor.getInHosDate();
                    }else {
                        startTime = sessionNumber + "-09-01";
                    }
                    String trianYear = resDoctor.getTrainingYears();

                    int year = 0;
                    if (trianYear.equals("1")) {
                        year = 1;
                    }
                    if (trianYear.equals("2")) {
                        year = 2;
                    }
                    if (trianYear.equals("3")) {
                        year = 3;
                    }
                    if (year != 0) {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = sdf.parse(startTime);
                            //然后使用Calendar操作日期
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                            calendar.add(Calendar.DATE, -1);
                            //把得到的日期格式化成字符串类型的时间
                            endTime = sdf.format(calendar.getTime());
                        } catch (Exception e) {

                        }
                    }
                    model.addAttribute("startDate", startTime);
                    model.addAttribute("endDate", endTime);
                }

                model.addAttribute("resDoctor", resDoctor);
                model.addAttribute("user", sysUser);
                model.addAttribute("jsresGraduationApply", jsresGraduationApply);
                model.addAttribute("recruitFlow", resDoctor.getDoctorFlow());
                showMaterials(model, resDoctor, applyYear, jsresGraduationApply);
            }
        }
        model.addAttribute("doctor",resDoctor);
        return "hbres/asse/asseApplication/showAllInfo/AsseInfo";
    }
    private void showMaterials(Model model, ResDoctor recruit, String applyYear, JsresGraduationApply jsresGraduationApply) throws DocumentException {
        //培训方案
        SchRotation rotation = rotationBiz.readSchRotation(recruit.getRotationFlow());
        if(rotation!=null&&recruit!=null&&StringUtil.isNotBlank(rotation.getRotationFlow())) {
            model.addAttribute("rotation", rotation);
            String doctorFlow = recruit.getDoctorFlow();
            String rotationFlow = rotation.getRotationFlow();

            Map<String,Integer> groupRowSpan=new HashMap<>();
            Map<String,Integer> deptRowSpan=new HashMap<>();

            //方案中的组
            List<SchRotationGroup> groupList = groupBiz.searchSchRotationGroup(rotationFlow);
            model.addAttribute("groupList", groupList);
            //方案中的科室
            List<SchRotationDept> deptList = rotationDeptBiz.searchSchRotationDept(rotationFlow);
            Map<String, Float> realMonthMap = new HashMap<String, Float>();
            Map<String, Object> avgBiMap=new HashMap<>();
            float allMonth = 0;
            for (SchRotationDept dept : deptList) {
                //轮转科室
                List<SchArrangeResult> resultList = resultBiz.searchResultByGroupFlow(dept.getGroupFlow(),dept.getStandardDeptId(),doctorFlow);
                if(resultList!=null&&resultList.size()>0)
                {
                    String key = dept.getGroupFlow() + dept.getStandardDeptId();
                    for (SchArrangeResult result : resultList) {
                        String realMonth = result.getSchMonth();
                        if (StringUtil.isNotBlank(realMonth)) {
                            Float realMonthF = Float.parseFloat(realMonth);
                            allMonth += realMonthF;
                        }
                    }
                }
            }
            model.addAttribute("allMonth", allMonth);
            //平均完成比例与平均审核比例
            avgBiMap = resultBiz.doctorDeptAvgWorkDetail(recruit.getDoctorFlow(), applyYear);
            model.addAttribute("avgBiMap", avgBiMap);
            model.addAttribute("realMonthMap", realMonthMap);
        }
    }

    @RequestMapping(value="/AsseFile")
    public String AsseFile(String recruitFlow,String applyYear,Model model) throws DocumentException {

        ResDoctor resDoctor=resDoctorBiz.readDoctor(recruitFlow);
        if(resDoctor!=null) {
            //培训方案
            JsresGraduationApply jsresGraduationApply=graduationApplyBiz.searchByRecruitFlow(recruitFlow,applyYear);
            model.addAttribute("jsresGraduationApply",jsresGraduationApply);
            SchRotation rotation = rotationBiz.readSchRotation(resDoctor.getRotationFlow());
            if (rotation != null && resDoctor != null && StringUtil.isNotBlank(rotation.getRotationFlow())) {
                model.addAttribute("rotation", rotation);
                String doctorFlow = resDoctor.getDoctorFlow();
                String rotationFlow = rotation.getRotationFlow();

                Map<String, Integer> groupRowSpan = new HashMap<>();
                Map<String, Integer> deptRowSpan = new HashMap<>();

                //方案中的组
                List<SchRotationGroup> groupList = groupBiz.searchSchRotationGroup(rotationFlow);
                model.addAttribute("groupList", groupList);
                //方案中的科室
                List<SchRotationDept> deptList = rotationDeptBiz.searchSchRotationDept(rotationFlow);

                //组下面的科室
                Map<String, List<SchRotationDept>> rotationDeptMap = new HashMap<String, List<SchRotationDept>>();
                //标准科室下的记录
                Map<String, List<SchArrangeResult>> resultMap = new HashMap<String, List<SchArrangeResult>>();
                Map<String, Float> realMonthMap = new HashMap<String, Float>();
                Map<String, Object> biMap = new HashMap<>();
                Map<String, Object> avgBiMap = new HashMap<>();
                Map<String, ResDoctorSchProcess> processMap = new HashMap<>();
                List<String> resultFlows = new ArrayList<>();
                float allMonth = 0;
                for (SchRotationDept dept : deptList) {
                    List<SchRotationDept> temp = rotationDeptMap.get(dept.getGroupFlow());
                    if (temp == null) {
                        temp = new ArrayList<SchRotationDept>();
                    }
                    rotationDeptMap.put(dept.getGroupFlow(), temp);
                    Integer count = groupRowSpan.get(dept.getGroupFlow());
                    if (count == null)
                        count = 0;
                    count++;
                    groupRowSpan.put(dept.getGroupFlow(), count);
                    temp.add(dept);

                    //轮转科室
                    List<SchArrangeResult> resultList = resultBiz.searchResultByGroupFlow(dept.getGroupFlow(), dept.getStandardDeptId(), doctorFlow);
                    if (resultList != null && resultList.size() > 0) {
                        String key = dept.getGroupFlow() + dept.getStandardDeptId();
                        resultMap.put(key, resultList);
                        Integer t = groupRowSpan.get(dept.getGroupFlow());
                        if (t == null)
                            t = 0;
                        groupRowSpan.put(dept.getGroupFlow(), resultList.size() + t);
                        deptRowSpan.put(key, resultList.size());
                        for (SchArrangeResult result : resultList) {
                            resultFlows.add(result.getResultFlow());
                            processMap.put(result.getResultFlow(),processBiz.searchByResultFlow(result.getResultFlow()));
                            Float month = realMonthMap.get(key);
                            if (month == null) {
                                month = 0f;
                            }
                            String realMonth = result.getSchMonth();
                            if (StringUtil.isNotBlank(realMonth)) {
                                Float realMonthF = Float.parseFloat(realMonth);
                                month += realMonthF;
                                allMonth += realMonthF;
                            }
                            realMonthMap.put(key, month);
                        }
                    } else {

                        Integer t = groupRowSpan.get(dept.getGroupFlow());
                        if (t == null)
                            t = 0;
                        groupRowSpan.put(dept.getGroupFlow(), 1 + t);
                    }
                }
                model.addAttribute("allMonth", allMonth);
                    //完成比例与审核比例
                List<HbresDoctorDeptDetail> details = resultBiz.hbresDoctorDeptDetails(doctorFlow, applyYear);
                if (details != null && details.size() > 0) {
                    for (HbresDoctorDeptDetail d : details) {
                        biMap.put(d.getResultFlow(), d);
                    }
                }
                //平均完成比例与平均审核比例
                avgBiMap = resultBiz.doctorDeptAvgWorkDetail(doctorFlow, applyYear);
                model.addAttribute("avgBiMap", avgBiMap);
                model.addAttribute("biMap", biMap);//各科室比例
                model.addAttribute("rotationDeptMap", rotationDeptMap);
                model.addAttribute("processMap", processMap);

                List<PubFile> evaluationFiles = fileBiz.findFileByTypeFlows("AfterEvaluationFile", resultFlows);
                Map<String, PubFile> evaluationFileMap = new HashMap<>();
                if (evaluationFiles != null) {
                    for (PubFile pubFile : evaluationFiles) {
                        evaluationFileMap.put(pubFile.getProductFlow(), pubFile);
                    }
                }
                List<PubFile> summaryFiles = fileBiz.findFileByTypeFlows("AfterSummaryFile", resultFlows);
                Map<String, PubFile> summaryFileMap = new HashMap<>();
                if (summaryFiles != null) {
                    for (PubFile pubFile : summaryFiles) {
                        summaryFileMap.put(pubFile.getProductFlow(), pubFile);
                    }
                }
                model.addAttribute("resultMap", resultMap);
                model.addAttribute("evaluationFileMap", evaluationFileMap);
                model.addAttribute("summaryFileMap", summaryFileMap);
                model.addAttribute("groupRowSpan", groupRowSpan);
                model.addAttribute("deptRowSpan", deptRowSpan);
                model.addAttribute("realMonthMap", realMonthMap);
            }
        }
        return "hbres/asse/asseApplication/showAllInfo/AsseFile";
    }

    @RequestMapping(value="/main")
    public String main(Model model) {

        return "hbres/asse/city/main";
    }

    /**
     * 市局待审核查询条件
     * @param model
     * @param roleFlag
     * @return
     */
    @RequestMapping(value="/waitAuditMain")
    public String waitAuditMain(Model model,String roleFlag){
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        model.addAttribute("org",org);
        SysOrg sysorg =new  SysOrg();
        sysorg.setOrgProvId(org.getOrgProvId());

        if (roleFlag.equals(GlobalConstant.USER_LIST_CHARGE)) {//市局
            sysorg.setOrgCityId(org.getOrgCityId());
        }
        sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
        model.addAttribute("orgs", orgs);
        model.addAttribute("roleFlag", roleFlag);
        return "hbres/asse/city/waitAuditMain";
    }

    /**
     * 市局待审核列表
     * @param model
     * @param roleFlag
     * @return
     */
    @RequestMapping(value="/waitAuditList")
    public String waitAuditList(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,String applyYear,
                                 String orgFlow,String trainingTypeId,String trainingSpeId,String datas[],
                                String sessionNumber,String graduationYear,String qualificationMaterialId,String passFlag,
                                String userName,String idNo,String completeBi,String auditBi
                                ){
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String>orgFlowList=new ArrayList<String>();
        if(StringUtil.isBlank(orgFlow))
        {
            SysUser sysuser=GlobalContext.getCurrentUser();
            SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
            SysOrg sysorg =new  SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            if (roleFlag.equals(GlobalConstant.USER_LIST_CHARGE)) {//市局
                sysorg.setOrgCityId(org.getOrgCityId());
            }
            param.put("org",sysorg);
        }
        param.put("orgFlow",orgFlow);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("graduationYear",graduationYear);
        param.put("qualificationMaterialId",qualificationMaterialId);
        param.put("passFlag",passFlag);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("completeBi",completeBi);
        param.put("auditBi",auditBi);
        param.put("auditStatusId",JsResAuditStatusEnum.WaitChargePass.getId());
        param.put("applyYear",applyYear);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyList(param);
        model.addAttribute("list",list);
        String nowTime=DateUtil.transDateTime(DateUtil.getCurrentTime());
        String startDate=InitConfig.getSysCfg("charge_submit_start_time");
        String endDate=InitConfig.getSysCfg("charge_submit_end_time");
        String f="N";
        if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
            if(startDate.compareTo(nowTime)<=0&&endDate.compareTo(nowTime)>=0)
            {
                if(applyYear.equals(DateUtil.getYear())){
                    f="Y";
                }
            }
        }
        model.addAttribute("f",f);
        return "hbres/asse/city/waitAuditList";
    }
    /**
     * 市局已审核列表
     * @param model
     * @param roleFlag
     * @return
     */
    @RequestMapping(value="/AuditList")
    public String AuditList(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,String applyYear,
                                 String orgFlow,String trainingTypeId,String trainingSpeId,String datas[],
                                String sessionNumber,String graduationYear,String qualificationMaterialId,String passFlag,
                                String userName,String idNo,String completeBi,String auditBi,String auditStatusId
                                ){
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String>orgFlowList=new ArrayList<String>();
        if(StringUtil.isBlank(orgFlow))
        {
            SysUser sysuser=GlobalContext.getCurrentUser();
            SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
            SysOrg sysorg =new  SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            if (roleFlag.equals(GlobalConstant.USER_LIST_CHARGE)) {//市局
                sysorg.setOrgCityId(org.getOrgCityId());
            }
            param.put("org",sysorg);
        }
        param.put("orgFlow",orgFlow);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("graduationYear",graduationYear);
        param.put("qualificationMaterialId",qualificationMaterialId);
        param.put("passFlag",passFlag);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("completeBi",completeBi);
        param.put("auditBi",auditBi);
        param.put("auditStatusId",auditStatusId);
        param.put("applyYear",applyYear);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyList(param);
        model.addAttribute("list",list);
        String nowTime=DateUtil.transDateTime(DateUtil.getCurrentTime());
        String startDate=InitConfig.getSysCfg("charge_submit_start_time");
        String endDate=InitConfig.getSysCfg("charge_submit_end_time");
        String f="N";
        if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
            if(startDate.compareTo(nowTime)<=0&&endDate.compareTo(nowTime)>=0)
            {
                f="Y";
            }
        }
        model.addAttribute("f",f);
        return "hbres/asse/city/AuditList";
    }
    @RequestMapping(value="/exportList")
    public void exportList(Model model, String roleFlag , HttpServletResponse response, HttpServletRequest request,String applyYear,
                             String orgFlow, String trainingTypeId, String trainingSpeId, String datas[],
                             String sessionNumber, String graduationYear, String qualificationMaterialId, String passFlag,
                             String userName, String idNo, String completeBi, String auditBi, String auditStatusId, String isWaitAudit
                                ) throws IOException {
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String>orgFlowList=new ArrayList<String>();
        if(StringUtil.isBlank(orgFlow))
        {
            SysUser sysuser=GlobalContext.getCurrentUser();
            SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
            SysOrg sysorg =new  SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            if (roleFlag.equals(GlobalConstant.USER_LIST_CHARGE)) {//市局
                sysorg.setOrgCityId(org.getOrgCityId());
            }
            param.put("org",sysorg);
        }
        param.put("orgFlow",orgFlow);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("graduationYear",graduationYear);
        param.put("qualificationMaterialId",qualificationMaterialId);
        param.put("passFlag",passFlag);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("completeBi",completeBi);
        param.put("auditBi",auditBi);
        param.put("auditStatusId",auditStatusId);
        param.put("applyYear",applyYear);
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryListForExport(param);
        graduationApplyBiz.chargeExportList(list,response,isWaitAudit);
    }
    @RequestMapping(value="/chargeAuditApply")
    public String chargeAuditApply(Model model,String applyFlow){
        List<JsresGraduationApplyLog> logs=graduationApplyBiz.getAuditLogsByApplyFlow(applyFlow);
        model.addAttribute("logs",logs);
        return "hbres/asse/city/chargeAuditApply";
    }
    @RequestMapping(value="/localAuditApply")
    public String localAuditApply(Model model,String applyFlow){
        List<JsresGraduationApplyLog> logs=graduationApplyBiz.getAuditLogsByApplyFlow(applyFlow);
        model.addAttribute("logs",logs);
        return "hbres/asse/hospital/localAuditApply";
    }
    @RequestMapping(value="/chargeSaveAudit")
    @ResponseBody
    public String chargeSaveAudit(Model model,String applyFlow,String auditStatusId,String auditReason){
        JsresGraduationApply apply=graduationApplyBiz.readByFlow(applyFlow);
        if(apply!=null)
        {
            if(JsResAuditStatusEnum.NotPassed.getId().equals(apply.getLocalAuditStatusId()))
            {
                return "基地已退回该学员考核资格申请信息，无法审核！";
            }
            if(StringUtil.isNotBlank(apply.getGlobalAuditStatusId()))
            {
                return "省厅已审核该学员考核资格申请信息，无法审核！";
            }
            if(StringUtil.isBlank(auditStatusId))
            {
                return "请选择审核结果！";
            }
            if(JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId)&&StringUtil.isBlank(auditReason))
            {
                return "请输入原因！";
            }
            apply.setCityAuditStatusId(auditStatusId);
            apply.setCityAuditStatusName(JsResAuditStatusEnum.getNameById(auditStatusId));
            apply.setCityReason(auditReason);
            if(JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId))//市局审核不通过 直接不通过
            {
                apply.setAuditStatusId(JsResAuditStatusEnum.ChargeNotPassed.getId());
                apply.setAuditStatusName(JsResAuditStatusEnum.ChargeNotPassed.getName());
            }else{//市局审核通过 需要省厅审核
                apply.setAuditStatusId(JsResAuditStatusEnum.WaitGlobalPass.getId());
                apply.setAuditStatusName(JsResAuditStatusEnum.WaitGlobalPass.getName());
            }
            String nowTime=DateUtil.transDateTime(DateUtil.getCurrentTime());
            String startDate=InitConfig.getSysCfg("charge_submit_start_time");
            String endDate=InitConfig.getSysCfg("charge_submit_end_time");
            String f="N";
            if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
                if(startDate.compareTo(nowTime)<=0&&endDate.compareTo(nowTime)>=0)
                {
                    f="Y";
                }
            }
            if("N".equals(f))
            {
                return "当前时间不在审核时间段内，无法审核！";
            }
            JsresGraduationApplyLog log=new JsresGraduationApplyLog();
            log.setApplyFlow(applyFlow);
            log.setAuditRoleId(GlobalConstant.USER_LIST_CHARGE);
            log.setAuditRoleName("市局");
            log.setAuditReason(auditReason);
            log.setAuditStatusId(auditStatusId);
            log.setAuditStatusName(JsResAuditStatusEnum.getNameById(auditStatusId));
            log.setAuditTime(nowTime);
            SysUser sysuser=GlobalContext.getCurrentUser();
            log.setUserFlow(sysuser.getUserFlow());
            log.setUserName(sysuser.getUserName());
            graduationApplyBiz.saveChargeAudit(apply,log);
            return "审核成功";
        }
        return  "学员申请信息不存在，请刷新后再试！";
    }
    @RequestMapping(value="/localSaveAudit")
    @ResponseBody
    public String localSaveAudit(Model model,String applyFlow,String auditStatusId,String auditReason){
        JsresGraduationApply apply=graduationApplyBiz.readByFlow(applyFlow);
        if(apply!=null)
        {
            if(StringUtil.isNotBlank(apply.getCityAuditStatusId()))
            {
                return "市局已审核该学员考核资格申请信息，无法审核！";
            }
            if(StringUtil.isBlank(auditStatusId))
            {
                return "请选择审核结果！";
            }
            if(JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId)&&StringUtil.isBlank(auditReason))
            {
                return "请输入原因！";
            }
            apply.setLocalAuditStatusId(auditStatusId);
            apply.setLocalAuditStatusName(JsResAuditStatusEnum.getNameById(auditStatusId));
            apply.setLocalReason(auditReason);
            if(JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId))//市局审核不通过 直接不通过
            {
                apply.setAuditStatusId(JsResAuditStatusEnum.LocalNotPassed.getId());
                apply.setAuditStatusName(JsResAuditStatusEnum.LocalNotPassed.getName());
            }else{//市局审核通过 需要省厅审核
                apply.setAuditStatusId(JsResAuditStatusEnum.WaitChargePass.getId());
                apply.setAuditStatusName(JsResAuditStatusEnum.WaitChargePass.getName());
            }
            String nowTime=DateUtil.transDateTime(DateUtil.getCurrentTime());
            String startDate=InitConfig.getSysCfg("local_submit_start_time");
            String endDate=InitConfig.getSysCfg("local_submit_end_time");
            String f="N";
            if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
                if(startDate.compareTo(nowTime)<=0&&endDate.compareTo(nowTime)>=0)
                {
                    f="Y";
                }
            }
            if("N".equals(f))
            {
                return "当前时间不在审核时间段内，无法审核！";
            }
            JsresGraduationApplyLog log=new JsresGraduationApplyLog();
            log.setApplyFlow(applyFlow);
            log.setAuditRoleId(GlobalConstant.USER_LIST_LOCAL);
            log.setAuditRoleName("基地");
            log.setAuditReason(auditReason);
            log.setAuditStatusId(auditStatusId);
            log.setAuditStatusName(JsResAuditStatusEnum.getNameById(auditStatusId));
            log.setAuditTime(nowTime);
            SysUser sysuser=GlobalContext.getCurrentUser();
            log.setUserFlow(sysuser.getUserFlow());
            log.setUserName(sysuser.getUserName());
            graduationApplyBiz.saveChargeAudit(apply,log);
            return "审核成功";
        }
        return  "学员申请信息不存在，请刷新后再试！";
    }
    @RequestMapping(value="/AuditListMain")
    public String AuditListMain(Model model,String roleFlag){
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        model.addAttribute("org",org);
        SysOrg sysorg =new  SysOrg();
        sysorg.setOrgProvId(org.getOrgProvId());

        if (roleFlag.equals(GlobalConstant.USER_LIST_CHARGE)) {//市局
            sysorg.setOrgCityId(org.getOrgCityId());
        }
        sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
        model.addAttribute("orgs", orgs);
        model.addAttribute("roleFlag", roleFlag);
        return "hbres/asse/city/AuditListMain";
    }

    @RequestMapping(value="/attachmentMain")
    public String attachmentMain(Model model,String roleFlag){
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        model.addAttribute("org",org);
        SysOrg sysorg =new  SysOrg();
        sysorg.setOrgProvId(org.getOrgProvId());

        if (roleFlag.equals(GlobalConstant.USER_LIST_CHARGE)) {//市局
            sysorg.setOrgCityId(org.getOrgCityId());
        }
        sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
        model.addAttribute("orgs", orgs);
        model.addAttribute("roleFlag", roleFlag);
        return "hbres/asse/city/attachmentListMain";
    }

    @RequestMapping("/attachmentList")
    public String attachmentList(Model model,JsresGraduationAttachment attachment){
        if(StringUtil.isNotBlank(attachment.getOrgFlow())){
            String year = DateUtil.getYear();
            attachment.setGraduationYear(year);
            List<JsresGraduationAttachment> attachmentList = graduationApplyBiz.searchAttachments(attachment);
            model.addAttribute("attachmentList",attachmentList);
        }
        return "hbres/asse/city/attachmentList";
    }

}
