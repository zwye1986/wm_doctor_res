package com.pinde.sci.ctrl.gzzyjxres;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gzzyjxres.IGzjxResDoctorEvaluationBiz;
import com.pinde.sci.biz.gzzyjxres.IGzjxStuDoctorInfoBiz;
import com.pinde.sci.biz.res.IStuBatchBiz;
import com.pinde.sci.biz.res.IStuUserResumeBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.gzzyjxres.StuRoleEnum;
import com.pinde.sci.enums.res.StuStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.gzzyjxres.StuEvalCfgExt;
import com.pinde.sci.model.gzzyjxres.StuEvalCfgItemExt;
import com.pinde.sci.model.gzzyjxres.StuEvalCfgTitleExt;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.StuUserExt;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RequestMapping("/gzzyjxres/evaluation")
@Controller
public class GzzyjxEvaluationManageController extends GeneralController {

    @Autowired
    private IGzjxResDoctorEvaluationBiz resDoctorEvaluationBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IStuBatchBiz stuBatchBiz;
    @Autowired
    private IStuUserResumeBiz stuUserBiz;
    @Autowired
    private IGzjxStuDoctorInfoBiz stuDoctorInfoBiz;

    /**
     * 评价指标维护
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/evaluationMaintenance")
    public String evaluationMaintenance(Model model, String roleId) throws DocumentException {
        if (StringUtil.isBlank(roleId)) {
            roleId = StuRoleEnum.Doctor.getId();
        }
        ResDoctorProcessEvalConfig config = resDoctorEvaluationBiz.readEvalConfig(roleId);
        if (null != config && StringUtil.isNotBlank(config.getFormCfg())) {
            Document dom = DocumentHelper.parseText(config.getFormCfg());
            String titleXpath = "//title";
            List<Element> titleElementList = dom.selectNodes(titleXpath);
            if (null != titleElementList && titleElementList.size() > 0) {
                List<StuEvalCfgTitleExt> titleFormList = new ArrayList<StuEvalCfgTitleExt>();
                for (Element te : titleElementList) {
                    StuEvalCfgTitleExt titleForm = new StuEvalCfgTitleExt();
                    titleForm.setId(te.attributeValue("id"));
                    titleForm.setName(te.attributeValue("name"));
                    titleForm.setOrderNum(te.attributeValue("orderNum"));
                    List<Element> itemElementList = te.elements("item");
                    List<StuEvalCfgItemExt> itemFormList = null;
                    if (null != itemElementList && itemElementList.size() > 0) {
                        itemFormList = new ArrayList<StuEvalCfgItemExt>();
                        for (Element ie : itemElementList) {
                            StuEvalCfgItemExt itemForm = new StuEvalCfgItemExt();
                            itemForm.setId(ie.attributeValue("id"));
                            itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                            itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
                            itemForm.setOrder(ie.element("order") == null ? "" : ie.element("order").getTextTrim());
                            itemFormList.add(itemForm);
                        }
                    }
                    titleForm.setItemList(itemFormList);
                    titleFormList.add(titleForm);
                }
                model.addAttribute("titleFormList", titleFormList);
            }
        }
        model.addAttribute("evalConfig", config);
        return "gzzyjxres/evaluation/evaluationIndex";
    }

    //保存考评项目
    @RequestMapping(value = {"/saveEvalConfig"})
    @ResponseBody
    public String saveEvalConfig(String configFlow, StuEvalCfgTitleExt title, String roleId) throws DocumentException {
        int result = resDoctorEvaluationBiz.saveEvalConfig(configFlow, title, roleId);
        if (result > GlobalConstant.ZERO_LINE) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    //修改保存评分指标
    @RequestMapping(value = "/modifyItem")
    @ResponseBody
    public String modifyItem(String configFlow, StuEvalCfgItemExt itemForm) throws DocumentException {
        int result = resDoctorEvaluationBiz.modifyItem(configFlow, itemForm);
        if (result > GlobalConstant.ZERO_LINE) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    //保存所有评分指标
    @RequestMapping(value = "/saveEvalItemList")
    @ResponseBody
    public String saveEvalItemList(@RequestBody StuEvalCfgExt form) throws DocumentException {
        int result = resDoctorEvaluationBiz.saveFormItemList(form);
        if (result > GlobalConstant.ZERO_LINE) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    //删除评分项目
    @RequestMapping(value = "/deleteTitle")
    @ResponseBody
    public String deleteTitle(String configFlow, String id) throws Exception {
        int result = resDoctorEvaluationBiz.deleteTitle(configFlow, id);
        if (result > GlobalConstant.ZERO_LINE) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    //删除评分指标
    @RequestMapping(value = "/deleteItem")
    @ResponseBody
    public String deleteItem(String configFlow, String id) throws Exception {
        int result = resDoctorEvaluationBiz.deleteItem(configFlow, id);
        if (result > GlobalConstant.ZERO_LINE) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 评价查询标签页
     *
     * @param currentPage
     * @param model
     * @return
     */
    @RequestMapping(value = "/evaluationSelect")
    public String evaluationSelect(Integer currentPage, Model model, StuFurtherStudyRegist stuFurtherStudyRegist, HttpServletRequest request) {
        return "gzzyjxres/evaluation/evaluationTap";
    }

    /**
     * 评价查询
     *
     * @param currentPage
     * @param model
     * @return
     */
    @RequestMapping(value = "/evaluationQuery")
    public String evaluationQuery(Integer currentPage, Model model, String roleId,
                                  String batchFlow, String speId, String teacherName, String doctorName,HttpServletRequest request) {
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(DictTypeEnum.DwjxSpe.getId());
        sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysDict> speLst = dictBiz.searchDictList(sysDict);
        model.addAttribute("dwjxSpeLst", speLst);
        List<StuBatch> batchLst = stuBatchBiz.getStuBatchLst();
        model.addAttribute("batchLst", batchLst);
        model.addAttribute("roleId", roleId);
        Map<String, Object> parMp = new HashMap<String, Object>();
        parMp.put("batchFlow", batchFlow);//批次
        List<String> status = new ArrayList<>();
        status.add(StuStatusEnum.Admited.getId());
        status.add(StuStatusEnum.Graduation.getId());
        status.add(StuStatusEnum.DelayGraduation.getId());
        parMp.put("speId", speId);//进修专业
        parMp.put("status", status);
        if (StringUtil.isNotBlank(teacherName)) {
            parMp.put("teacherName", teacherName);
        }
        if (StringUtil.isNotBlank(doctorName)) {
            parMp.put("userName", doctorName);
        }
        PageHelper.startPage(currentPage,  getPageSize(request));
        List<StuUserExt> stuUserLst = stuUserBiz.searchStuUser(parMp);
        model.addAttribute("infoList", stuUserLst);
        if (StuRoleEnum.Secretary.getId().equals(roleId)) {
            //对应批次号下的评价记录
            Map<String, ResDoctorSchProcessEval> evalMap = new HashMap<>();
            //存放考核人的流水号
            List<String> kaoherenFlowList = null;
            //存放被考核人的流水号
            List<String> beikaoherenFlowList = null;
            if (stuUserLst != null && stuUserLst.size() > 0) {
                for (StuUserExt tempUser : stuUserLst) {
                    kaoherenFlowList = new ArrayList<>();
                    if (StringUtil.isNotBlank(tempUser.getUserFlow())) {
                        kaoherenFlowList.add(tempUser.getUserFlow());
                    }
                    beikaoherenFlowList = new ArrayList<>();
                    if (StringUtil.isNotBlank(tempUser.getSpeId())) {
                        beikaoherenFlowList.add(tempUser.getSpeId());
                    }
                    List<ResDoctorSchProcessEval> evalList = null;
                    if (kaoherenFlowList.size() > 0 && beikaoherenFlowList.size() > 0) {
                        evalList = resDoctorEvaluationBiz.queryEvalListByFlow(tempUser.getResumeFlow(), kaoherenFlowList, beikaoherenFlowList);
                    }
                    if (evalList != null && evalList.size() > 0) {
                        evalMap.put(tempUser.getResumeFlow(), evalList.get(0));
                    }
                }
            }
            model.addAttribute("evalMap", evalMap);
            return "gzzyjxres/evaluation/secretary/evaluation4Query";
        } else if (StuRoleEnum.Teacher.getId().equals(roleId)) {
            //对应批次号下的评价记录
            Map<String, ResDoctorSchProcessEval> evalMap = new HashMap<>();
            //存放考核人的流水号
            List<String> kaoherenFlowList = null;
            //存放被考核人的流水号
            List<String> beikaoherenFlowList = null;
            if (stuUserLst != null && stuUserLst.size() > 0) {
                for (StuUserExt tempUser : stuUserLst) {
                    kaoherenFlowList = new ArrayList<>();
                    if (StringUtil.isNotBlank(tempUser.getUserFlow())) {
                        kaoherenFlowList.add(tempUser.getUserFlow());
                    }
                    beikaoherenFlowList = new ArrayList<>();
                    if (StringUtil.isNotBlank(tempUser.getTeacherFlow())) {
                        beikaoherenFlowList.add(tempUser.getTeacherFlow());
                    }
                    List<ResDoctorSchProcessEval> evalList = null;
                    if (kaoherenFlowList.size() > 0 && beikaoherenFlowList.size() > 0) {
                        evalList = resDoctorEvaluationBiz.queryEvalListByFlow(tempUser.getResumeFlow(), kaoherenFlowList, beikaoherenFlowList);
                    }
                    if (evalList != null && evalList.size() > 0) {
                        evalMap.put(tempUser.getResumeFlow(), evalList.get(0));
                    }
                }
            }
            model.addAttribute("evalMap", evalMap);
            return "gzzyjxres/evaluation/teacher/evaluation4Query";
        } else if (StuRoleEnum.Doctor.getId().equals(roleId)) {
            //对应批次号下的评价记录
            Map<String, ResDoctorSchProcessEval> evalMap = new HashMap<>();
            //存放考核人的流水号
            List<String> kaoherenFlowList = null;
            //存放被考核人的流水号
            List<String> beikaoherenFlowList = null;
            if (stuUserLst != null && stuUserLst.size() > 0) {
                for (StuUserExt tempUser : stuUserLst) {
                    kaoherenFlowList = new ArrayList<>();
                    if (StringUtil.isNotBlank(tempUser.getTeacherFlow())) {
                        kaoherenFlowList.add(tempUser.getTeacherFlow());
                    }
                    if (StringUtil.isNotBlank(tempUser.getSpeId())) {
                        kaoherenFlowList.add(tempUser.getSpeId());
                    }
                    beikaoherenFlowList = new ArrayList<>();
                    if (StringUtil.isNotBlank(tempUser.getUserFlow())) {
                        beikaoherenFlowList.add(tempUser.getUserFlow());
                    }
                    List<ResDoctorSchProcessEval> evalList = null;
                    if (kaoherenFlowList.size() > 0 && beikaoherenFlowList.size() > 0) {
                        evalList = resDoctorEvaluationBiz.queryEvalListByFlow(tempUser.getResumeFlow(), kaoherenFlowList, beikaoherenFlowList);
                    }
                    if (evalList != null && evalList.size() > 0) {
                        for (ResDoctorSchProcessEval tempEval : evalList) {
                            evalMap.put(tempUser.getResumeFlow() + "&" + tempEval.getEvalUserFlow(), tempEval);
                        }
                    }
                }
            }
            model.addAttribute("evalMap", evalMap);
            return "gzzyjxres/evaluation/doctor/evaluation4Query";
        }
        return null;
    }

    /**
     * 评价人查询待评价列表
     *
     * @param currentPage
     * @param model
     * @param roleId         被评价人角色    带教（Teacher）、教秘（Secretary）(科室)、学员（Doctor）
     * @param kaoherenRoleId 评价人角色
     * @return
     */
    @RequestMapping(value = "/evaluationManage")
    public String evaluationManage(Integer currentPage, Model model, String roleId, String evalStatueId,
                                   String kaoherenRoleId, String doctorName, String batchFlow, String speId,HttpServletRequest request) {
        SysUser currentUser = GlobalContext.getCurrentUser();
        List<String> status = new ArrayList<>();
        status.add(StuStatusEnum.Admited.getId());
        status.add(StuStatusEnum.Graduation.getId());
        status.add(StuStatusEnum.DelayGraduation.getId());
        //存放考核人的流水号
        List<String> kaoherenFlowList = new ArrayList<>();
        kaoherenFlowList.add(currentUser.getUserFlow());
        //存放被考核人的流水号
        List<String> beikaoherenFlowList = new ArrayList<>();
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(DictTypeEnum.DwjxSpe.getId());
        sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysDict> speLst = dictBiz.searchDictList(sysDict);
        model.addAttribute("dwjxSpeLst", speLst);
        List<StuBatch> batchLst = stuBatchBiz.getStuBatchLst();
        model.addAttribute("batchLst", batchLst);
        model.addAttribute("roleId", roleId);
        if (StuRoleEnum.Secretary.getId().equals(roleId)) {
            //查到学员报名记录且审核状态为报到，结业，延期结业可以评价科室
            PageHelper.startPage(currentPage,  getPageSize(request));
            List<StuUserResume> stuUserResumes = stuUserBiz.getShowGraduationLst(currentUser.getUserFlow(), status);
            //对应批次号下的评价记录
            Map<String, ResDoctorSchProcessEval> evalMap = new HashMap<>();
            if (stuUserResumes != null && stuUserResumes.size() > 0) {
                for (StuUserResume tempResume : stuUserResumes) {
                    //科室Flow即进修专业的sepId
                    String deptFlow = tempResume.getSpeId();
                    beikaoherenFlowList.add(deptFlow);
                    List<ResDoctorSchProcessEval> evalList = resDoctorEvaluationBiz.queryEvalListByFlow(tempResume.getResumeFlow(), kaoherenFlowList, beikaoherenFlowList);
                    if (evalList != null && evalList.size() > 0) {
                        evalMap.put(tempResume.getResumeFlow(), evalList.get(0));
                    }
                }
            }
            model.addAttribute("infoList", stuUserResumes);
            model.addAttribute("evalMap", evalMap);
            return "gzzyjxres/evaluation/secretary/evaluationList";
        } else if (StuRoleEnum.Teacher.getId().equals(roleId)) {
            //查到学员报名记录且审核状态为报到，结业，延期结业可以评价带教
            PageHelper.startPage(currentPage,  getPageSize(request));
            List<StuUserResume> stuUserResumes = stuUserBiz.getShowGraduationLst(currentUser.getUserFlow(), status);
            //对应批次号下的评价记录
            Map<String, ResDoctorSchProcessEval> evalMap = new HashMap<>();
            if (stuUserResumes != null && stuUserResumes.size() > 0) {
                for (StuUserResume tempResume : stuUserResumes) {
                    String teacherFlow = tempResume.getTeacherFlow();
                    if(StringUtil.isNotBlank(teacherFlow)){
                        beikaoherenFlowList.add(teacherFlow);
                    }
                    List<ResDoctorSchProcessEval> evalList = resDoctorEvaluationBiz.queryEvalListByFlow(tempResume.getResumeFlow(), kaoherenFlowList, beikaoherenFlowList);
                    if (evalList != null && evalList.size() > 0) {
                        evalMap.put(tempResume.getResumeFlow(), evalList.get(0));
                    }
                }
            }
            model.addAttribute("infoList", stuUserResumes);
            model.addAttribute("evalMap", evalMap);
            return "gzzyjxres/evaluation/teacher/evaluationList";
        } else if (StuRoleEnum.Doctor.getId().equals(roleId)) {
            if (StuRoleEnum.Teacher.getId().equals(kaoherenRoleId)) {
                //可以评价的学员的审核状态为报到，结业，延期结业
                Map<String, Object> parMp = new HashMap<String, Object>();
                parMp.put("teacherFlow", currentUser.getUserFlow());
                parMp.put("status", status);
                parMp.put("userName", doctorName);
                parMp.put("batchFlow", batchFlow);
                List<String> resumeFlows = new ArrayList<>();
                //区分是否评价
                checkEvalStatue(resumeFlows, evalStatueId, parMp, currentUser);
                PageHelper.startPage(currentPage,  getPageSize(request));
                List<StuUserExt> stuUserLst = stuUserBiz.searchStuUser(parMp);
                //对应批次号下的评价记录
                Map<String, ResDoctorSchProcessEval> evalMap = new HashMap<>();
                if (stuUserLst != null && stuUserLst.size() > 0) {
                    for (StuUserResume tempResume : stuUserLst) {
                        String doctorFlow = tempResume.getUserFlow();
                        beikaoherenFlowList.add(doctorFlow);
                        List<ResDoctorSchProcessEval> evalList = resDoctorEvaluationBiz.queryEvalListByFlow(tempResume.getResumeFlow(), kaoherenFlowList, beikaoherenFlowList);
                        if (evalList != null && evalList.size() > 0) {
                            evalMap.put(tempResume.getResumeFlow(), evalList.get(0));
                        }
                    }
                }
                model.addAttribute("evalMap", evalMap);
                model.addAttribute("infoList", stuUserLst);
                return "gzzyjxres/evaluation/doctor/evaluationList";
            } else if (StuRoleEnum.Secretary.getId().equals(kaoherenRoleId)) {
                //一个科秘可以分配多个科室
                //可以评价的学员的审核状态为报到，结业，延期结业
                Map<String, Object> parMp = new HashMap<String, Object>();
                StuDeptOfStaff stuDeptOfStaff4search = new StuDeptOfStaff();
                stuDeptOfStaff4search.setUserFlow(currentUser.getUserFlow());
                stuDeptOfStaff4search.setUserRole(StuRoleEnum.Secretary.getId());
                List<StuDeptOfStaff> stuDeptOfStaffs = stuDoctorInfoBiz.searchIfDeptHasSecretary(stuDeptOfStaff4search);
                List<String> deptFlows = new ArrayList<>();
                if (stuDeptOfStaffs != null && stuDeptOfStaffs.size() > 0) {
                    for (StuDeptOfStaff tempDept : stuDeptOfStaffs) {
                        deptFlows.add(tempDept.getDeptFlow());
                    }
                }
                parMp.put("deptFlows", deptFlows);
                parMp.put("status", status);
                parMp.put("speId", speId);
                parMp.put("userName", doctorName);
                parMp.put("batchFlow", batchFlow);
                List<String> resumeFlows = new ArrayList<>();
                //区分是否评价
                checkEvalStatue(resumeFlows, evalStatueId, parMp, currentUser);
                PageHelper.startPage(currentPage,  getPageSize(request));
                List<StuUserExt> stuUserLst = stuUserBiz.searchStuUser(parMp);
                //对应批次号下的评价记录
                Map<String, ResDoctorSchProcessEval> evalMap = new HashMap<>();
                if (stuUserLst != null && stuUserLst.size() > 0) {
                    for (StuUserExt tempResume : stuUserLst) {
                        String doctorFlow = tempResume.getUserFlow();
                        beikaoherenFlowList.add(doctorFlow);
                        kaoherenFlowList = new ArrayList<>();
                        kaoherenFlowList.add(tempResume.getSpeId());
                        List<ResDoctorSchProcessEval> evalList = resDoctorEvaluationBiz.queryEvalListByFlow(tempResume.getResumeFlow(), kaoherenFlowList, beikaoherenFlowList);
                        if (evalList != null && evalList.size() > 0) {
                            evalMap.put(tempResume.getResumeFlow(), evalList.get(0));
                        }
                    }
                }
                model.addAttribute("evalMap", evalMap);
                model.addAttribute("infoList", stuUserLst);
                return "gzzyjxres/evaluation/doctor/evaluationList4secretary";
            }
        }
        return null;
    }

    //用于教秘/带教对学员的评价查询区分状态
    private void checkEvalStatue(List<String> resumeFlows, String evalStatueId, Map<String, Object> parMp, SysUser currentUser) {
        //已评价
        if ("1".equals(evalStatueId)) {
            ResDoctorSchProcessEval schProcessEval = new ResDoctorSchProcessEval();
            schProcessEval.setEvalUserFlow(currentUser.getUserFlow());
            List<ResDoctorSchProcessEval> schProcessEvals = resDoctorEvaluationBiz.searchByItems(schProcessEval);
            if (schProcessEvals != null && schProcessEvals.size() > 0) {
                for (ResDoctorSchProcessEval tempEval : schProcessEvals) {
                    resumeFlows.add(tempEval.getProcessFlow());
                }
            }
            parMp.put("inResumeFlows", resumeFlows);
        } else if ("2".equals(evalStatueId)) {
            //未评价
            ResDoctorSchProcessEval schProcessEval = new ResDoctorSchProcessEval();
            schProcessEval.setEvalUserFlow(currentUser.getUserFlow());
            List<ResDoctorSchProcessEval> schProcessEvals = resDoctorEvaluationBiz.searchByItems(schProcessEval);
            if (schProcessEvals != null && schProcessEvals.size() > 0) {
                for (ResDoctorSchProcessEval tempEval : schProcessEvals) {
                    resumeFlows.add(tempEval.getProcessFlow());
                }
            }
            parMp.put("notInResumeFlows", resumeFlows);
        }
    }

    //考评页面
    @RequestMapping(value = "/showEvaluation", method = RequestMethod.GET)
    public String showEvaluation(Model model, String roleId, String processFlow, String kaoherenRoleId) throws DocumentException {
        SysUser user = GlobalContext.getCurrentUser();
        if (StringUtil.isNotBlank(roleId)) {
            ResDoctorProcessEvalConfig config = resDoctorEvaluationBiz.readEvalConfig(roleId);
            if (null != config && StringUtil.isNotBlank(config.getFormCfg())) {
                model.addAttribute("configFlow", config.getConfigFlow());
                Document dom = DocumentHelper.parseText(config.getFormCfg());
                String titleXpath = "//title";
                List<Element> titleElementList = dom.selectNodes(titleXpath);
                if (null != titleElementList && titleElementList.size() > 0) {
                    List<StuEvalCfgTitleExt> titleFormList = new ArrayList<StuEvalCfgTitleExt>();
                    for (Element te : titleElementList) {
                        StuEvalCfgTitleExt titleForm = new StuEvalCfgTitleExt();
                        titleForm.setId(te.attributeValue("id"));
                        titleForm.setName(te.attributeValue("name"));
                        titleForm.setOrderNum(te.attributeValue("orderNum"));
                        List<Element> itemElementList = te.elements("item");
                        List<StuEvalCfgItemExt> itemFormList = null;
                        if (null != itemElementList && itemElementList.size() > 0) {
                            itemFormList = new ArrayList<StuEvalCfgItemExt>();
                            for (Element ie : itemElementList) {
                                StuEvalCfgItemExt itemForm = new StuEvalCfgItemExt();
                                itemForm.setId(ie.attributeValue("id"));
                                itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                                itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
                                itemForm.setOrder(ie.element("order") == null ? "" : ie.element("order").getTextTrim());
                                itemFormList.add(itemForm);
                            }
                        }
                        titleForm.setItemList(itemFormList);
                        titleFormList.add(titleForm);
                    }
                    model.addAttribute("titleFormList", titleFormList);
                }
            }
        }
        model.addAttribute("roleId", roleId);
        model.addAttribute("processFlow", processFlow);
        model.addAttribute("kaoherenRoleId", kaoherenRoleId);
        return "gzzyjxres/evaluation/editEvalForm";
    }

    //考评保存
    @RequestMapping(value = "/saveForm", method = RequestMethod.POST)
    @ResponseBody
    public String saveForm(ResDoctorSchProcessEvalWithBLOBs eval, String[] scoreId, String[] score,
                           String kaoherenRoleId, String roleId, String configFlow) {
        Map<String, Object> param = new HashMap<>();
        param.put("eval", eval);
        param.put("roleId", roleId);
        param.put("configFlow", configFlow);
        param.put("kaoherenRoleId", kaoherenRoleId);
        param.put("scoreIdList", scoreId == null ? scoreId : Arrays.asList(scoreId));
        param.put("scoreList", score == null ? score : Arrays.asList(score));
        int result = resDoctorEvaluationBiz.saveForm(param);
        if (result > GlobalConstant.ZERO_LINE) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    //考评查看页面
    @RequestMapping(value = "/showEvalDetail", method = RequestMethod.GET)
    public String showEvalDetail(String evalRecordFlow, Model model) throws DocumentException {
        if (StringUtil.isNotBlank(evalRecordFlow)) {
            ResDoctorSchProcessEvalWithBLOBs eval = resDoctorEvaluationBiz.readProcessEvalByFlow(evalRecordFlow);
            model.addAttribute("eval", eval);
            if (null != eval && StringUtil.isNotBlank(eval.getFormCfg())) {
                Document dom = DocumentHelper.parseText(eval.getFormCfg());
                String titleXpath = "//title";
                List<Element> titleElementList = dom.selectNodes(titleXpath);
                if (null != titleElementList && titleElementList.size() > 0) {
                    List<StuEvalCfgTitleExt> titleFormList = new ArrayList<StuEvalCfgTitleExt>();
                    for (Element te : titleElementList) {
                        StuEvalCfgTitleExt titleForm = new StuEvalCfgTitleExt();
                        titleForm.setId(te.attributeValue("id"));
                        titleForm.setName(te.attributeValue("name"));
                        titleForm.setOrderNum(te.attributeValue("orderNum"));
                        List<Element> itemElementList = te.elements("item");
                        List<StuEvalCfgItemExt> itemFormList = null;
                        if (null != itemElementList && itemElementList.size() > 0) {
                            itemFormList = new ArrayList<StuEvalCfgItemExt>();
                            for (Element ie : itemElementList) {
                                StuEvalCfgItemExt itemForm = new StuEvalCfgItemExt();
                                itemForm.setId(ie.attributeValue("id"));
                                itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                                itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
                                itemForm.setOrder(ie.element("order") == null ? "" : ie.element("order").getTextTrim());
                                itemFormList.add(itemForm);
                            }
                        }
                        titleForm.setItemList(itemFormList);
                        titleFormList.add(titleForm);
                    }
                    model.addAttribute("titleFormList", titleFormList);
                }
                Document scoreDom = DocumentHelper.parseText(eval.getEvalResult());
                String scoreXpath = "//score";
                List<Element> scoreElementList = scoreDom.selectNodes(scoreXpath);
                if (null != scoreElementList && scoreElementList.size() > 0) {
                    Map<String, Object> scoreMap = new HashMap<>();
                    for (Element score : scoreElementList) {
                        scoreMap.put(score.attributeValue("id"), score.getTextTrim());
                    }
                    model.addAttribute("scoreMap", scoreMap);
                }
            }
            model.addAttribute("flag", "view");
        }
        return "gzzyjxres/evaluation/editEvalForm";
    }
}
