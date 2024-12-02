package com.pinde.sci.ctrl.evaAudit;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.core.common.enums.RecStatusEnum;
import com.pinde.core.common.enums.ResRecTypeEnum;
import com.pinde.sci.form.res.ResAssessCfgItemForm;
import com.pinde.sci.form.res.ResAssessCfgTitleForm;
import com.pinde.sci.model.hbres.teacherRec;
import com.pinde.sci.model.mo.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/res/evaDoctorResult")
public class ResEvaDoctorResultController extends GeneralController {

    @Autowired
    private IResGradeBiz resGradeBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private IResAssessCfgBiz assessCfgBiz;
    @Autowired
    private IResRecBiz resRecBiz;
    @Autowired
    private IResDoctorProcessBiz procesBiz;
    @Autowired
    private ISchDeptBiz schDeptBiz;


    @RequestMapping(value="/main")
    public String main(String status,String deptFlow,String deptName,String startTime,String endTime,String startScore,
                       String recTypeId,String endScore,Integer currentPage, HttpServletRequest request, Model model){
        SysUser currentUser = GlobalContext.getCurrentUser();
        String orgFlow = currentUser.getOrgFlow();
        String userFlow = currentUser.getUserFlow();
        List<SysDept>deptList =	deptBiz.searchDeptByOrg(orgFlow);//查出当前机构的所有科室
        model.addAttribute("depts",deptList);
        //查询条件
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userFlow",userFlow);
        paramMap.put("orgFlow", orgFlow);
        paramMap.put("startScore",startScore);
        paramMap.put("endScore",endScore);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);
        paramMap.put("deptFlow",deptFlow);
        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(status)) {
            paramMap.put("schFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
            paramMap.put("isCurrentFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
        } else if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(status)) {
            paramMap.put("schFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
            paramMap.put("isCurrentFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
        }
        String doctorRoleFlow = InitConfig.getSysCfg("res_doctor_role_flow");
        paramMap.put("roleFlow",doctorRoleFlow);
        List<String> recTypeIdList = new ArrayList<>();
        if (StringUtil.isEmpty(recTypeId)){
            recTypeIdList.add(com.pinde.core.common.enums.ResRecTypeEnum.NurseDoctorGrade.getId());
            recTypeIdList.add(com.pinde.core.common.enums.ResRecTypeEnum.TeacherDoctorGrade.getId());
            recTypeIdList.add(com.pinde.core.common.enums.ResRecTypeEnum.ManageDoctorAssess360.getId());
        }else {
            recTypeIdList.add(recTypeId);
        }
//        recTypeIdList.add(com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId());
//        recTypeIdList.add(com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId());
        paramMap.put("recTypeIdList",recTypeIdList);

        model.addAttribute("userFlow",userFlow);
        model.addAttribute("deptName",deptName);
        model.addAttribute("startScore",startScore);
        model.addAttribute("endScore",endScore);
        model.addAttribute("startTime",startTime);
        model.addAttribute("endTime",endTime);
        model.addAttribute("status",status);
        model.addAttribute("deptFlow",deptFlow);
        //查出当前机构的所有带教老师
        PageHelper.startPage(currentPage,getPageSize(request));
        List<teacherRec> userList = resGradeBiz.getDoctorByRecAndAvgScore(paramMap);
        model.addAttribute("datas",userList);
        return "hbres/evaAudit/resultSearch/doctorResult";
    }


    @RequestMapping(value="/teachMain")
    public String teachMain(Integer currentPage, HttpServletRequest request, Model model,
                            String teachName,String deptFlow,String assessStatusId) throws DocumentException {
        //查询该基地的科室
        SysDept sysDept = new SysDept();
        sysDept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        sysDept.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
        List<SysDept> deptList = deptBiz.searchDept(sysDept);
        model.addAttribute("deptList",deptList);

        String userFlow = GlobalContext.getCurrentUser().getUserFlow();
        ResDoctor doctor = doctorBiz.readDoctor(userFlow);
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userFlow",userFlow);
        paramMap.put("rotationFlow",doctor.getRotationFlow());
        paramMap.put("teachName",teachName);
        paramMap.put("deptFlow",deptFlow);
        paramMap.put("assessStatusId",assessStatusId);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String, String>> resultList = resGradeBiz.getTeachResult(paramMap);
        for (Map<String, String> map : resultList) {
            if (null != map.get("recContent")){
                Document doc = DocumentHelper.parseText(map.get("recContent"));
                String totalScore = doc.getRootElement().elementText("totalScore");
                if (totalScore.contains(".")){
                    totalScore=totalScore.substring(0,totalScore.indexOf("."));
                }
                map.put("recContent",totalScore);
            }
        }

        model.addAttribute("teachName",teachName);
        model.addAttribute("deptFlow",deptFlow);
        model.addAttribute("assessStatusId",assessStatusId);
        model.addAttribute("datas",resultList);
        return "hbres/evaAudit/resultSearch/teachResult";
    }

    @RequestMapping(value = {"/grade"}, method = {RequestMethod.GET})
    public String grade(String doctorFlow, String recFlow, String recTypeId,String processFlow, Model model) throws DocumentException {
        DeptTeacherGradeInfo rec = resGradeBiz.readResGrade(recFlow);
        ResAssessCfg assessCfg = null;
        if (rec == null) {
            ResAssessCfg search = new ResAssessCfg();
            search.setCfgCodeId(recTypeId);
            search.setFormStatusId(com.pinde.core.common.GlobalConstant.FLAG_Y);
            List<ResAssessCfg> resAssessCfgList = assessCfgBiz.selectByExampleWithBLOBs(search);
            model.addAttribute("formCount",resAssessCfgList.size());
            if (resAssessCfgList != null && resAssessCfgList.size() > 0) {
                // 优先取百分制的那一条，没有就取第一条
                assessCfg = resAssessCfgList.get(0);
                for (ResAssessCfg resAssessCfg : resAssessCfgList) {
                    if("Percentile".equals(resAssessCfg.getAssessTypeId())) {
                        assessCfg = resAssessCfg;
                        break;
                    }
                }
            }

        }
        if (assessCfg != null) {
            if(assessCfg != null){
                getForm(model, assessCfg);
            }
        }
        if (rec != null) {
            String modifyTime = rec.getModifyTime();
            if(StringUtil.isNotBlank(modifyTime)){
                String yyyyMMdd = DateUtil.transDate(modifyTime, "yyyyMMdd");
                model.addAttribute("openTime",yyyyMMdd.substring(0,4)+"年"+yyyyMMdd.substring(4,6)+"月"+yyyyMMdd.substring(6,yyyyMMdd.length())+"日");
            }
            model.addAttribute("rec",rec);
            Map<String,Object> gradeMap = resRecBiz.parseGradeXml(rec.getRecContent());
            model.addAttribute("gradeMap",gradeMap);
            assessCfg = assessCfgBiz.readResAssessCfg(rec.getCfgFlow());
            getForm(model, assessCfg);
        }
        model.addAttribute("processFlow", processFlow);
        model.addAttribute("rec", rec);
        model.addAttribute("assessCfg", assessCfg);
        return "hbres/evaAudit/resultSearch/grade";
    }


    @RequestMapping(value = {"/fiveGrade"})
    public String fiveGrade(String doctorFlow, String recFlow, String recTypeId,String processFlow, Model model) throws DocumentException {
        DeptTeacherGradeInfo rec = resGradeBiz.readResGrade(recFlow);
        ResAssessCfg assessCfg = null;
        if (rec == null) {
            ResAssessCfg search = new ResAssessCfg();
            search.setCfgCodeId(recTypeId);
            search.setFormStatusId(com.pinde.core.common.GlobalConstant.FLAG_Y);
            List<ResAssessCfg> resAssessCfgList = assessCfgBiz.selectByExampleWithBLOBs(search);
            model.addAttribute("formCount",resAssessCfgList.size());
            if (resAssessCfgList != null && resAssessCfgList.size() > 0) {
                // 优先取5分制的那一条，没有就取第一条
                assessCfg = resAssessCfgList.get(0);
                for (ResAssessCfg resAssessCfg : resAssessCfgList) {
                    if("Five".equals(resAssessCfg.getAssessTypeId())) {
                        assessCfg = resAssessCfg;
                        break;
                    }
                }
            }

        }
        if (assessCfg != null) {
            if(assessCfg != null){
                getForm(model, assessCfg);
            }
        }
        if (rec != null) {
            String modifyTime = rec.getModifyTime();
            if(StringUtil.isNotBlank(modifyTime)){
                String yyyyMMdd = DateUtil.transDate(modifyTime, "yyyyMMdd");
                model.addAttribute("openTime",yyyyMMdd.substring(0,4)+"年"+yyyyMMdd.substring(4,6)+"月"+yyyyMMdd.substring(6,yyyyMMdd.length())+"日");
            }
            model.addAttribute("rec",rec);
            Map<String,Object> gradeMap = resRecBiz.parseGradeXml(rec.getRecContent());
            model.addAttribute("gradeMap",gradeMap);
            assessCfg = assessCfgBiz.readResAssessCfg(rec.getCfgFlow());
            getForm(model, assessCfg);
        }
        model.addAttribute("processFlow", processFlow);
        model.addAttribute("rec", rec);
        model.addAttribute("assessCfg", assessCfg);
        return "hbres/evaAudit/resultSearch/grade";
    }

    /**
     * 评分表单选择
     * @param recFlow
     * @param doctorFlow
     * @param model
     * @return
     */
    @RequestMapping(value="/selGrade",method={RequestMethod.GET})
    public String selGrade(String recFlow, String doctorFlow,String processFlow, Model model){
        model.addAttribute("recFlow",recFlow);
        model.addAttribute("processFlow",processFlow);
        model.addAttribute("doctorFlow",doctorFlow);
        return "hbres/evaAudit/resultSearch/selGrade";
    }

    /**
     * 保存评分
     * @param deptTeacherGradeInfo
     * @param request
     * @return
     */
    @RequestMapping(value="/saveGrade",method={RequestMethod.POST})
    @ResponseBody
    public String saveGrade(DeptTeacherGradeInfo deptTeacherGradeInfo,String subDeptFlow, HttpServletRequest request){
        ResDoctorSchProcess process = procesBiz.read(deptTeacherGradeInfo.getProcessFlow());
        deptTeacherGradeInfo.setProcessFlow(process.getProcessFlow());
        deptTeacherGradeInfo.setSchDeptFlow(process.getSchDeptFlow());
        String recContent = createGradeXml(request.getParameterMap(), "");
        if(deptTeacherGradeInfo != null){
            if(StringUtil.isBlank(deptTeacherGradeInfo.getRecFlow())){
                SysUser user = GlobalContext.getCurrentUser();
                deptTeacherGradeInfo.setOperUserFlow(user.getUserFlow());
                deptTeacherGradeInfo.setOperUserName(user.getUserName());
                deptTeacherGradeInfo.setOperTime(DateUtil.getCurrDateTime());
                deptTeacherGradeInfo.setStatusId(RecStatusEnum.Submit.getId());
                deptTeacherGradeInfo.setStatusName(RecStatusEnum.Submit.getName());
                if(StringUtil.isNotBlank(deptTeacherGradeInfo.getSchDeptFlow())){
                    SchDept schDept = schDeptBiz.readSchDept(deptTeacherGradeInfo.getSchDeptFlow());
                    if(schDept!=null){
                        deptTeacherGradeInfo.setSchDeptName(schDept.getSchDeptName());
                        deptTeacherGradeInfo.setSchDeptFlow(schDept.getSchDeptFlow());
                        deptTeacherGradeInfo.setOrgFlow(schDept.getOrgFlow());
                        deptTeacherGradeInfo.setOrgName(schDept.getOrgName());
                        deptTeacherGradeInfo.setDeptFlow(schDept.getDeptFlow());
                        deptTeacherGradeInfo.setDeptName(schDept.getDeptName());
                    }
                }

                if(StringUtil.isNotBlank(deptTeacherGradeInfo.getRecTypeId())){
                    deptTeacherGradeInfo.setRecTypeName(com.pinde.core.common.enums.ResRecTypeEnum.getNameById(deptTeacherGradeInfo.getRecTypeId()));
                }
            }

            deptTeacherGradeInfo.setRecContent(recContent);
        }
        String allScore = recContent.split("<totalScore>")[1].split("</totalScore>")[0];
        deptTeacherGradeInfo.setAllScore(allScore);
        if (resGradeBiz.edit(deptTeacherGradeInfo) != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return deptTeacherGradeInfo.getRecFlow();
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }

    private void getForm(Model model, ResAssessCfg assessCfg) throws DocumentException {
        model.addAttribute("assessCfg", assessCfg);
        Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
        String titleXpath = "//title";
        List<Element> titleElementList = dom.selectNodes(titleXpath);
        if (titleElementList != null && !titleElementList.isEmpty()) {
            List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
            for (Element te : titleElementList) {
                ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
                titleForm.setType(te.attributeValue("type"));
                titleForm.setRow(te.attributeValue("row"));
                titleForm.setId(te.attributeValue("id"));
                titleForm.setName(te.attributeValue("name"));
                List<Element> itemElementList = te.elements("item");
                List<ResAssessCfgItemForm> itemFormList = null;
                if (itemElementList != null && !itemElementList.isEmpty()) {
                    itemFormList = new ArrayList<ResAssessCfgItemForm>();
                    for (Element ie : itemElementList) {
                        ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
                        itemForm.setId(ie.attributeValue("id"));
                        itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                        itemForm.setType(ie.element("type") == null ? "" : ie.element("type").getTextTrim());
                        itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
                        itemForm.setRow(ie.element("row") == null ? "" : ie.element("row").getTextTrim());
                        itemFormList.add(itemForm);
                    }
                }
                titleForm.setItemList(itemFormList);
                titleFormList.add(titleForm);
            }
            model.addAttribute("titleFormList", titleFormList);
        }
    }
    private String createGradeXml(Map<String, String[]> gradeInfo, String roleFlag){
        Document document = DocumentHelper.createDocument();
        String name="gradeInfo";
        if(StringUtil.isNotBlank(roleFlag))
        {
            name=roleFlag+"GradeInfo";
        }
        Element rootEle = document.addElement(name);
        if(gradeInfo!=null){
            String[] assessIds = gradeInfo.get("assessId");
            String[] scores = gradeInfo.get("score");
            String[] lostReasons = gradeInfo.get("lostReason");
            if(assessIds!=null && assessIds.length>0){
                for(int i = 0 ; i<assessIds.length ; i++){
                    String assessId = assessIds[i];
                    String score = scores[i];
                    Element item = rootEle.addElement("grade");
                    item.addAttribute("assessId",assessId);

                    Element scoreElement = item.addElement("score");
                    scoreElement.setText(score);
//                    Element lostReasonElement = item.addElement("lostReason");
//                    lostReasonElement.setText(lostReason);
                }
            }

            String[] totalScore = gradeInfo.get("totalScore");
            String[] lostReason = gradeInfo.get("lostReason");
            if(totalScore!=null && totalScore.length>0 && StringUtil.isNotBlank(totalScore[0])){
                Element item = rootEle.addElement("totalScore");
                item.setText(totalScore[0]);
            }
            if(lostReason!=null && lostReason.length>0 && StringUtil.isNotBlank(lostReason[0])){
                Element item = rootEle.addElement("lostReason");
                item.setText(lostReason[0]);
            }
        }
        return document.asXML();
    }

    @RequestMapping(value="/patientMain")
    public String patientMain(Integer currentPage, HttpServletRequest request, Model model,
                            String patientName,String deptFlow){
        //查询该基地的科室
        SysDept sysDept = new SysDept();
        sysDept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        sysDept.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
        List<SysDept> deptList = deptBiz.searchDept(sysDept);
        model.addAttribute("deptList",deptList);

        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("patientName",patientName);
        paramMap.put("deptFlow",deptFlow);
        paramMap.put("userFlow",GlobalContext.getCurrentUser().getUserFlow());
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String, String>> resultList = resGradeBiz.getPatientResult(paramMap);

        model.addAttribute("patientName",patientName);
        model.addAttribute("deptFlow",deptFlow);
        model.addAttribute("list",resultList);
        return "hbres/evaAudit/resultSearch/patientResult";
    }
    @RequestMapping(value="/patientMainInfo")
    public String patientMainInfo(String recFlow,Model model) throws DocumentException {
        if (StringUtil.isNotBlank(recFlow)){
            DeptTeacherGradeInfo gradeInfo = resGradeBiz.readResGrade(recFlow);
            model.addAttribute("gradeInfo",gradeInfo);
            if (null!=gradeInfo && StringUtil.isNotBlank(gradeInfo.getRecContent())){
                Map<String,Object> gradeMap = resRecBiz.parseGradeXml(gradeInfo.getRecContent());//病人评价信息
                model.addAttribute("gradeMap",gradeMap);
                ResAssessCfg assessCfg = assessCfgBiz.readResAssessCfg(gradeInfo.getCfgFlow());
                if (null!=assessCfg){
                    Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
                    String titleXpath = "//title";
                    List<Element> titleElementList = dom.selectNodes(titleXpath);
                    if (titleElementList != null && !titleElementList.isEmpty()) {
                        List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
                        for (Element te : titleElementList) {
                            ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
                            titleForm.setType(te.attributeValue("type"));
                            titleForm.setId(te.attributeValue("id"));
                            titleForm.setName(te.attributeValue("name"));
                            titleForm.setRow(te.attributeValue("row"));
                            titleForm.setEvalTypeId(te.attributeValue("evalTypeId") == null ? "" : te.attributeValue("evalTypeId"));
                            titleForm.setEvalTypeName(te.attributeValue("evalTypeName") == null ? "" : te.attributeValue("evalTypeName"));
                            List<Element> itemElementList = te.elements("item");
                            List<ResAssessCfgItemForm> itemFormList = null;
                            if (itemElementList != null && !itemElementList.isEmpty()) {
                                itemFormList = new ArrayList<ResAssessCfgItemForm>();
                                int sum = 0;
                                for (Element ie : itemElementList) {
                                    ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
                                    itemForm.setId(ie.attributeValue("id"));
                                    itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                                    itemForm.setType(ie.element("type") == null ? "" : ie.element("type").getTextTrim());
                                    itemForm.setRow(ie.element("row") == null ? "" : ie.element("row").getTextTrim());
                                    String score = ie.element("score") == null ? "" : ie.element("score").getTextTrim();
                                    itemForm.setScore(score);
                                    itemFormList.add(itemForm);
                                    if (StringUtil.isNotBlank(score)) {
                                        sum += Integer.parseInt(score);
                                    }
                                    titleForm.setScore(String.valueOf(sum));
                                }
                            }
                            titleForm.setItemList(itemFormList);
                            titleFormList.add(titleForm);
                        }
                        model.addAttribute("titleFormList",titleFormList);
                    }
                }
            }
        }
        return "hbres/evaAudit/resultSearch/teachResultInfo";
    }



    @RequestMapping(value="/patientEvaluate")
    public String patientEvaluate(Integer currentPage, HttpServletRequest request, Model model,String roleFlag,String[] datas,
                              String doctorName,String trainingSpeId,String sessionNumber,String patientName,String deptFlow){
        //查询该基地的科室
        SysDept sysDept = new SysDept();
        sysDept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        sysDept.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
        List<SysDept> deptList = deptBiz.searchDept(sysDept);
        model.addAttribute("deptList",deptList);

        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleFlag",roleFlag);
        paramMap.put("patientName",patientName);
        paramMap.put("userFlow",GlobalContext.getCurrentUser().getUserFlow());
        paramMap.put("doctorName",doctorName);
        paramMap.put("trainingSpeId",trainingSpeId);
        paramMap.put("sessionNumber",sessionNumber);
        paramMap.put("deptFlow",deptFlow);
        paramMap.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
        if (null!=datas && datas.length>0){
            paramMap.put("docTypeList", Arrays.asList(datas));
        }
        if (null!=roleFlag && StringUtil.isNotBlank(roleFlag) && roleFlag.equals("kzr")){
            List<SysUserDept> sysUserDepts = deptBiz.searchByUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            if (null!=sysUserDepts && !sysUserDepts.isEmpty()){
                List<String> rdspDeptlist = new ArrayList<>();
                for (SysUserDept dept : sysUserDepts) {
                    rdspDeptlist.add(dept.getDeptFlow());
                }
                paramMap.put("rdspDeptlist",rdspDeptlist);
            }
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String, String>> resultList = resGradeBiz.patientEvaluate(paramMap);
        model.addAttribute("resultList",resultList);
        return "hbres/evaAudit/resultSearch/patientEvaluateList";
    }
}
