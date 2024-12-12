package com.pinde.sci.ctrl.evaAudit;

import com.pinde.core.common.enums.RecStatusEnum;
import com.pinde.core.common.enums.ResAssessTypeEnum;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.form.res.ResAssessCfgItemForm;
import com.pinde.sci.form.res.ResAssessCfgTitleForm;
import com.pinde.sci.model.mo.PubFile;
import com.pinde.sci.model.mo.ResAssessCfg;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorSchProcess;
import com.pinde.sci.model.mo.SchArrangeResult;
import com.pinde.sci.model.mo.SchDept;
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
@RequestMapping("/res/teacherEvaDoctor")
public class ResTeacherEvaDoctorController extends GeneralController {

    @Autowired
    private IResDoctorProcessBiz resDoctorProcessBiz;
    @Autowired
    private ISchArrangeResultBiz resultBiz;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IResGradeBiz resGradeBiz;
    @Autowired
    private IResRecBiz resRecBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private ISchDeptBiz schDeptBiz;
    @Autowired
    private IResAssessCfgBiz assessCfgBiz;
    @Autowired
    private IResDoctorRecruitBiz recruitBiz;
    @Autowired
    private IDeptBiz deptBiz;
    /**
     * 帶教评价学员
     *
     * @param model
     * @param doctor
     * @param
     * @return
     */
    @RequestMapping(value = "/teacherEvaluation", method = {RequestMethod.GET, RequestMethod.POST})
    public String teacherEvaluation(Model model, ResDoctor doctor, String assessStatusId,
                                    String recTypeId, Integer currentPage, HttpServletRequest request, String[] datas) {
        String roleFlog = "teacher";
        String dataStr = "";
        List<String> docTypeList = new ArrayList<>();
        if (datas != null && datas.length > 0) {
            docTypeList = Arrays.asList(datas);
            for (String d : datas) {
                dataStr += d + ",";
            }
        }
        model.addAttribute("dataStr", dataStr);
        String userFlow = GlobalContext.getCurrentUser().getUserFlow();
        if (currentPage == null) {
            currentPage = 1;
        }
        model.addAttribute("datas", datas);
        if (StringUtil.isNotBlank(recTypeId)) {
            model.addAttribute("recTypeId", recTypeId);
        }
        //权限期间是否开通
        String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
        List<ResDoctorSchProcess> processList = null;
        Map<String, Object> param = new HashMap<>();
        param.put("assessStatusId",assessStatusId);
        param.put("roleFlog",roleFlog);
        param.put("teacherUserFlow", GlobalContext.getCurrentUser().getUserFlow());
        param.put("isOpen", isOpen);
        param.put("doctor", doctor);
        param.put("deptFlow", GlobalContext.getCurrentUser().getDeptFlow());
        param.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
        param.put("docTypeList", docTypeList);
        param.put("userFlow", GlobalContext.getCurrentUser().getUserFlow());
        PageHelper.startPage(currentPage, getPageSize(request));
        processList = resDoctorProcessBiz.selectProcessByDoctorNew(param);

        if (processList != null && processList.size() > 0) {
            model.addAttribute("processList", processList);
            List<String> userFlows = new ArrayList<String>();
            Map<String, SchArrangeResult> resultMapMap = new HashMap<String, SchArrangeResult>();
            Map<String, PubFile> fileMap = new HashMap<String, PubFile>();
            for (ResDoctorSchProcess rdsp : processList) {
                if (!userFlows.contains(rdsp.getUserFlow()))
                    userFlows.add(rdsp.getUserFlow());

                String resultFlow = rdsp.getSchResultFlow();
                SchArrangeResult result = resultBiz.readSchArrangeResult(resultFlow);
                resultMapMap.put(rdsp.getProcessFlow(), result);
                if (result != null) {
                    PubFile file = fileBiz.readFile(result.getAfterFileFlow());
                    fileMap.put(rdsp.getProcessFlow(), file);
                }
                List<String> recTypeIds = new ArrayList<String>();
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.TeacherDoctorGrade.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.TeacherDoctorGradeTwo.getId());
                Map<String, Object> paramMap = new HashMap();
                paramMap.put("recTypeIds", recTypeIds);
                paramMap.put("processFlow", rdsp.getProcessFlow());
                paramMap.put("currentUserFlow", GlobalContext.getCurrentUser().getUserFlow());
                List<DeptTeacherGradeInfo> gradeInfoList = resGradeBiz.searchResGradeByItems(paramMap);
                if (gradeInfoList != null && gradeInfoList.size() > 0) {
                    for (DeptTeacherGradeInfo tempGradeInfo : gradeInfoList) {
                        String typeId = tempGradeInfo.getRecTypeId();
                        model.addAttribute(rdsp.getProcessFlow()+typeId, tempGradeInfo);
                        Map<String, Object> gradeMap = resRecBiz.parseGradeXml(tempGradeInfo.getRecContent());
                        model.addAttribute(typeId + rdsp.getProcessFlow(), gradeMap);
                    }
                }
            }

            model.addAttribute("resultMap", resultMapMap);
            model.addAttribute("fileMap", fileMap);

            List<SysUser> userList = userBiz.searchSysUserByuserFlows(userFlows);
            if (userList != null && userList.size() > 0) {
                Map<String, SysUser> userMap = new HashMap<String, SysUser>();
                for (SysUser su : userList) {
                    userMap.put(su.getUserFlow(), su);
                }
                model.addAttribute("userMap", userMap);
            }

            List<ResDoctor> doctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
            if (doctorList != null && doctorList.size() > 0) {
                Map<String, ResDoctor> doctorMap = new HashMap<String, ResDoctor>();
                for (ResDoctor rd : doctorList) {
                    doctorMap.put(rd.getDoctorFlow(), rd);
                }
                model.addAttribute("doctorMap", doctorMap);
            }
        }
        return "/hbres/evaAudit/teacher/teacherEvaluationList";
    }

    @RequestMapping(value = "/manageEvaluation", method = {RequestMethod.GET, RequestMethod.POST})
    public String manageEvaluation(Model model, String userName,String sessionNumber,String deptFlow,String[] datas,String assessStatusId,
                                   String roleFlag,String trainingTypeId,Integer currentPage, HttpServletRequest request) {
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("sessionNumber", sessionNumber);
        model.addAttribute("trainingTypeId", trainingTypeId);
        if (currentPage == null) {
            currentPage = 1;
        }
        Map<String, Object> param = new HashMap<>();
        String dataStr = "";
        List<String> docTypeList = new ArrayList<>();
        if (datas != null && datas.length > 0) {
            docTypeList = Arrays.asList(datas);
            for (String d : datas) {
                dataStr += d + ",";
            }
        }
        model.addAttribute("dataStr", dataStr);
        model.addAttribute("datas", datas);
        param.put("docTypeList", docTypeList);
        SysUser currentUser = GlobalContext.getCurrentUser();
        param.put("userName",userName);
        param.put("sessionNumber",sessionNumber);
        param.put("orgFlow",currentUser.getOrgFlow());
        param.put("assessStatusId",assessStatusId);
        param.put("trainingTypeId",trainingTypeId);
        if (null!=roleFlag && StringUtil.isNotBlank(roleFlag)){
            if (roleFlag.equals("head") || roleFlag.equals("secretary")){
                List<SysUserDept> sysUserDepts = deptBiz.searchByUserFlow(currentUser.getUserFlow());
                model.addAttribute("deptList",sysUserDepts);
                if (StringUtil.isEmpty(deptFlow)){
                    List<String> deptList = new ArrayList<>();
                    for (SysUserDept dept : sysUserDepts) {
                        deptList.add(dept.getDeptFlow());
                    }
                    param.put("deptList",deptList);
                }else {
                    param.put("deptFlow",deptFlow);
                }
            }else if (roleFlag.equals("speAdmin")){
                param.put("speId",currentUser.getResTrainingSpeId());
                List<SysDept>deptList =	deptBiz.searchDeptByOrg(currentUser.getOrgFlow());//查出当前机构的所有科室
                model.addAttribute("deptList",deptList);
            }else if (roleFlag.equals("local")){
                List<SysDept>deptList =	deptBiz.searchDeptByOrg(currentUser.getOrgFlow());//查出当前机构的所有科室
                model.addAttribute("deptList",deptList);
            }
        }

        param.put("recTypeId", com.pinde.core.common.enums.ResRecTypeEnum.ManageDoctorAssess360.getId());
        param.put("userFlow", GlobalContext.getCurrentUser().getUserFlow());
        param.put("roleFlag",roleFlag);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String, String>> list = resGradeBiz.getHeadManageDoctorAssess(param);
        if (list != null && list.size() > 0) {
            model.addAttribute("list", list);
            Map<String, SchArrangeResult> resultMap = new HashMap<String, SchArrangeResult>();

            for (Map<String, String> map : list) {
                String processFlow = map.get("processFlow");
                String schResultFlow = map.get("schResultFlow");
                SchArrangeResult result = resultBiz.readSchArrangeResult(schResultFlow);
                resultMap.put(processFlow, result);

                Map<String, Object> paramMap = new HashMap();
                List<String> recTypeIds = new ArrayList<String>();
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.ManageDoctorAssess360.getId());
                paramMap.put("recTypeIds", recTypeIds);
                paramMap.put("processFlow", processFlow);
                paramMap.put("roleFlag",roleFlag);
                paramMap.put("currentUserFlow", GlobalContext.getCurrentUser().getUserFlow());
                List<DeptTeacherGradeInfo> gradeInfoList = resGradeBiz.searchResGradeByItems(paramMap);
                if (gradeInfoList != null && gradeInfoList.size() > 0) {
                    for (DeptTeacherGradeInfo tempGradeInfo : gradeInfoList) {
                        String typeId = tempGradeInfo.getRecTypeId();
                        model.addAttribute(processFlow+typeId, tempGradeInfo);
                        Map<String, Object> gradeMap = resRecBiz.parseGradeXml(tempGradeInfo.getRecContent());
                        model.addAttribute(typeId + processFlow, gradeMap);
                    }
                }
            }
            model.addAttribute("resultMap", resultMap);
        }
        return "/hbres/evaAudit/teacher/manageEvaluationList";
    }

    /**
     * 评分表单选择
     * @param recFlow
     * @param doctorFlow
     * @param model
     * @return
     */
    @RequestMapping(value="/selGrade",method={RequestMethod.GET})
    public String selGrade(String recFlow, String doctorFlow, Model model){
        model.addAttribute("recFlow",recFlow);
        model.addAttribute("doctorFlow",doctorFlow);
        return "/hbres/evaAudit/teacher/selGrade";
    }


    /**
     * 评分
     * @param recFlow
     * @param recTypeId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/grade",method={RequestMethod.GET})
    public String grade(String recFlow, String recTypeId, String doctorFlow, Model model) throws Exception{
        SysUser currUser = GlobalContext.getCurrentUser();
        ResDoctor resDoctor = doctorBiz.readDoctor(doctorFlow);
        ResDoctorRecruit newRecruit = recruitBiz.getNewRecruit(doctorFlow);
        model.addAttribute("resDoctor",resDoctor);
        model.addAttribute("recruit",newRecruit);
        if(StringUtil.isNotBlank(recTypeId) && currUser!=null){
            String cfgCodeId = null;
            if (com.pinde.core.common.enums.ResRecTypeEnum.TeacherDoctorGrade.getId().equals(recTypeId)) {
                cfgCodeId = ResAssessTypeEnum.TeacherDoctorAssess360.getId();
            }
            if (com.pinde.core.common.enums.ResRecTypeEnum.TeacherDoctorGradeTwo.getId().equals(recTypeId)) {
                cfgCodeId = ResAssessTypeEnum.TeacherDoctorAssessTwo360.getId();
            }
            if (com.pinde.core.common.enums.ResRecTypeEnum.ManageDoctorAssess360.getId().equals(recTypeId)) {
                cfgCodeId = ResAssessTypeEnum.ManageDoctorAssess360.getId();
            }

            DeptTeacherGradeInfo deptTeacherGradeInfo = resGradeBiz.readResGrade(recFlow);
            if(deptTeacherGradeInfo != null){
                String modifyTime = deptTeacherGradeInfo.getModifyTime();
                if(StringUtil.isNotBlank(modifyTime)){
                    String yyyyMMdd = DateUtil.transDate(modifyTime, "yyyyMMdd");
                    model.addAttribute("openTime",yyyyMMdd.substring(0,4)+"年"+yyyyMMdd.substring(4,6)+"月"+yyyyMMdd.substring(6,yyyyMMdd.length())+"日");
                }
                model.addAttribute("rec",deptTeacherGradeInfo);
                Map<String,Object> gradeMap = resRecBiz.parseGradeXml(deptTeacherGradeInfo.getRecContent());
                model.addAttribute("gradeMap",gradeMap);
                ResAssessCfg assessCfg = assessCfgBiz.readResAssessCfg(deptTeacherGradeInfo.getCfgFlow());
                getForm(model, assessCfg);
            }else {
                ResAssessCfg search = new ResAssessCfg();
                search.setCfgCodeId(cfgCodeId);
                search.setFormStatusId(com.pinde.core.common.GlobalConstant.FLAG_Y);
                List<ResAssessCfg> resAssessCfgList = assessCfgBiz.selectByExampleWithBLOBs(search);
                model.addAttribute("formCount",resAssessCfgList.size());
                if(resAssessCfgList != null && !resAssessCfgList.isEmpty()){
                    ResAssessCfg assessCfg = resAssessCfgList.get(0);
                    getForm(model, assessCfg);
                }
            }
        }
        return "/hbres/evaAudit/teacher/grade";
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

    /**
     * 保存评分
     * @param deptTeacherGradeInfo
     * @param request
     * @return
     */
    @RequestMapping(value="/saveGrade",method={RequestMethod.POST})
    @ResponseBody
    public String saveGrade(DeptTeacherGradeInfo deptTeacherGradeInfo,String roleFlag, HttpServletRequest request){
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
        if (null!=roleFlag && StringUtil.isNotBlank(roleFlag)){
            deptTeacherGradeInfo.setOperUserRoleName(roleFlag);
        }
        if (resGradeBiz.edit(deptTeacherGradeInfo) != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return deptTeacherGradeInfo.getRecFlow();
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
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

    /**
     * 提交评分
     * @param deptTeacherGradeInfo
     * @return
     */
    @RequestMapping(value="/opreResRecForGrade",method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String opreResRecForGrade(DeptTeacherGradeInfo deptTeacherGradeInfo){
        String deleteS = com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
        String deleteF = com.pinde.core.common.GlobalConstant.DELETE_FAIL;
        if(deptTeacherGradeInfo != null){
            if(StringUtil.isNotBlank(deptTeacherGradeInfo.getStatusId())){
                deptTeacherGradeInfo.setStatusName(RecStatusEnum.getNameById(deptTeacherGradeInfo.getStatusId()));
                deleteS = com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
                deleteF = com.pinde.core.common.GlobalConstant.OPRE_FAIL;
            }
            if (resGradeBiz.edit(deptTeacherGradeInfo) != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
                return deleteS;
            }
        }
        return deleteF;
    }

}
