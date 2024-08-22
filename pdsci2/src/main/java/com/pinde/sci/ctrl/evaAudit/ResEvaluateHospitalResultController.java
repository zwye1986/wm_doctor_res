package com.pinde.sci.ctrl.evaAudit;

import com.google.common.collect.Lists;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResAssessCfgBiz;
import com.pinde.sci.biz.res.IResGradeBiz;
import com.pinde.sci.biz.res.IResRecBiz;
import com.pinde.sci.biz.res.IResTrainingSpeDeptBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.res.ResAssessTypeEnum;
import com.pinde.sci.enums.res.ResRecTypeEnum;
import com.pinde.sci.form.res.ResAssessCfgItemForm;
import com.pinde.sci.form.res.ResAssessCfgTitleForm;
import com.pinde.sci.model.hbres.teacherRec;
import com.pinde.sci.model.mo.ResAssessCfg;
import com.pinde.sci.model.mo.SysDept;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.SysUserDept;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@RequestMapping("/res/evaluateHospitalResult")
public class ResEvaluateHospitalResultController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(ResEvaluateHospitalResultController.class);


    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IResAssessCfgBiz assessCfgBiz;
    @Autowired
    private IResRecBiz resRecBiz;
    @Autowired
    private IResGradeBiz resGradeBiz;
    @Autowired
    private IResTrainingSpeDeptBiz trainingSpeDeptBiz;


    @RequestMapping("/base")
    public String main(String role, String gradeRole, Model model, String trainingTypeId) {
        model.addAttribute("gradeRole", gradeRole);
        model.addAttribute("role", role);
        model.addAttribute("trainingTypeId", trainingTypeId);
        return "hbres/evaAudit/resultSearch/evaSearchMain";
    }

    @RequestMapping(value = "/main")
    public String main(String role, String studentType, String trainingSpeId, String gradeRole, String deptFlow, String userName, String deptName, String orgFlow, String trainingTypeId,
                       String startScore, String endScore, String sessionNumber, Integer currentPage, HttpServletRequest request, Model model, String startTime, String endTime,
                       String patientName,String doctorName,String[] docType) {
        model.addAttribute("gradeRole", gradeRole);
        SysUser currentUser = GlobalContext.getCurrentUser();
        if (StringUtil.isBlank(orgFlow)) {
            orgFlow = currentUser.getOrgFlow();
        }

        //登记类型
        String recType = "";
        //评分类型
        String cfgCode = "";

        List<String> keys = new ArrayList<String>();
        Object waitSort = null;
        Map<String, Object> scoreMap = new HashMap<String, Object>();
        //查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (GlobalConstant.USER_LIST_CHARGE.equals(role)) {
            List<SysUserDept> sysUserDepts = deptBiz.searchByUserFlow(currentUser.getUserFlow());
            List<String> deptFlows = new ArrayList<>();
            List<SysUserDept> deptFlows2 = new ArrayList<>();
            for (SysUserDept sysUserDept : sysUserDepts) {
                deptFlows.add(sysUserDept.getDeptFlow());
                deptFlows2.add(sysUserDept);
            }
            deptFlows.add(currentUser.getDeptFlow());
            paramMap.put("deptFlows", deptFlows);
            if ("doctor".equals(gradeRole)) {
                List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);//查出当前机构的所有科室
                model.addAttribute("depts", deptList);
            } else {
                model.addAttribute("depts", deptFlows2);
            }
        } else {
            List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);//查出当前机构的所有科室
            model.addAttribute("depts", deptList);
        }

        paramMap.put("orgFlow", orgFlow);
        paramMap.put("sessionNumber", sessionNumber);
        if (StringUtil.isNotBlank(startTime)) {
            if ("doctor".equals(gradeRole)) {
                paramMap.put("startTime", startTime);
            } else {
                paramMap.put("startTime", DateUtil.transDateTime(startTime, "yyyy-MM-dd", "yyyyMMdd"));
            }
        }
        if (StringUtil.isNotBlank(endTime)) {
            if ("doctor".equals(gradeRole)) {
                paramMap.put("endTime", endTime);
            } else {
                paramMap.put("endTime", DateUtil.transDateTime(endTime, "yyyy-MM-dd", "yyyyMMdd"));
            }

        }
        if ("ManageDoctorAssess360".equals(gradeRole)) {
            if (StringUtil.isNotBlank(startScore)) {
                paramMap.put("startScore", Double.parseDouble(startScore));
            }
            if (StringUtil.isNotBlank(endScore)) {
                paramMap.put("endScore", Double.parseDouble(endScore));
            }
        } else {
            paramMap.put("startScore", startScore);
            paramMap.put("endScore", endScore);
        }

        model.addAttribute("role", role);
        model.addAttribute("userName", userName);
        model.addAttribute("startScore", startScore);
        model.addAttribute("endScore", endScore);
        model.addAttribute("sessionNumber", sessionNumber);
        model.addAttribute("trainingSpeId", trainingSpeId);
        model.addAttribute("deptFlow", deptFlow);
        model.addAttribute("deptName", deptName);
        if ("360doctor".equals(gradeRole) || "360teacher".equals(gradeRole)) {
            model.addAttribute("allScore", "5");
        } else {
            model.addAttribute("allScore", "100");
        }
        if ("doctor".equals(gradeRole)) {
            //学员flow
            String doctorRoleFlow = InitConfig.getSysCfg("res_doctor_role_flow");
            paramMap.put("roleFlow", doctorRoleFlow);

            List<String> recTypeIdList = new ArrayList<>();
//            recTypeIdList.add(ResRecTypeEnum.NurseDoctorGrade.getId());
            recTypeIdList.add(ResRecTypeEnum.TeacherDoctorGrade.getId());
            paramMap.put("recTypeIdList", recTypeIdList);

            paramMap.put("userName", userName);
            paramMap.put("trainingSpeId", trainingSpeId);
            paramMap.put("studentType", studentType);
            //查出当前机构的所有带教老师
            PageHelper.startPage(currentPage, getPageSize(request));
            List<teacherRec> userList = resGradeBiz.getDoctorByRecAndAvgScore(paramMap);

            for (teacherRec teacherRec : userList) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("orgFlow", orgFlow);
                param.put("doctorFlow", teacherRec.getUserFlow());
                List<String> recTypeIdList1 = new ArrayList<>();
                recTypeIdList1.add(ResRecTypeEnum.TeacherDoctorGrade.getId());
                param.put("recTypeIdList", recTypeIdList1);
                param.put("deptFlow", deptFlow);
                param.put("processFlow", teacherRec.getProcessFlow());
                param.put("recFlow", null);
                List<Map<String, String>> recList = resGradeBiz.getRecContentByProcess3(param);
                teacherRec.setOperUserName(recList.get(0).get("operUserName"));
                int scoreSum = 0;
                List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.readForm(recList.get(0).get("cfgFlow"));
                if (null != assessCfgList && assessCfgList.size() > 0) {
                    for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
                        if (null != resAssessCfgTitleForm.getItemList() && resAssessCfgTitleForm.getItemList().size() > 0) {
                            for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
                                int s = Integer.parseInt("".equals(resAssessCfgItemForm.getScore()) ? "0" : resAssessCfgItemForm.getScore());
                                scoreSum += s;
                            }
                        }
                    }
                }
                teacherRec.setSumScore(String.valueOf(scoreSum));
            }

            model.addAttribute("datas", userList);
            model.addAttribute("studentTypes", studentType);

        } else if ("teacher".equals(gradeRole)) {
            if (GlobalConstant.USER_LIST_CHARGE.equals(role)) {
                //带教flow
//                recType = ResRecTypeEnum.TeacherGrade.getId();
//                paramMap.put("recTypeId", recType);

                paramMap.put("userName", userName);
                paramMap.put("deptFlow", deptFlow);
                paramMap.put("deptName", deptName);

                List<String> recTypeIds = Lists.newArrayList(ResRecTypeEnum.TeacherAssess.getId(),ResRecTypeEnum.TeacherGradeTwo.getId());
                paramMap.put("recTypeIds", recTypeIds);
                //查出当前机构的所有带教老师
                PageHelper.startPage(currentPage, getPageSize(request));
                List<teacherRec> userList = resGradeBiz.getUserByRecAndAvgScore2(paramMap);
                model.addAttribute("datas", userList);
            } else {
                //带教flow
//                recType = ResRecTypeEnum.TeacherGrade.getId();
//                paramMap.put("recTypeId", recType);
                paramMap.put("userName", userName);
                paramMap.put("deptFlow", deptFlow);
                paramMap.put("deptName", deptName);
                List<String> recTypeIds = Lists.newArrayList(ResRecTypeEnum.TeacherAssess.getId(),ResRecTypeEnum.TeacherGradeTwo.getId());
                paramMap.put("recTypeIds", recTypeIds);

                //查出当前机构的所有带教老师
                PageHelper.startPage(currentPage, getPageSize(request));
                List<teacherRec> userList = resGradeBiz.getUserByRecAndAvgScore(paramMap);
                model.addAttribute("datas", userList);
            }
        } else if ("head".equals(gradeRole)) {
            paramMap.put("userName", userName);
            paramMap.put("deptFlow", deptFlow);
            paramMap.put("deptName", deptName);
            recType = ResRecTypeEnum.DeptGrade.getId();
            paramMap.put("recTypeId", recType);
            cfgCode = ResAssessTypeEnum.DeptAssess.getId();
            List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
            model.addAttribute("assessCfgList", assessCfgList);
            PageHelper.startPage(currentPage, getPageSize(request));
            List<teacherRec> depts = resGradeBiz.getDeptByRecAndAvgScore(paramMap);
            model.addAttribute("datas", depts);
            paramMap.put("deptFlows", keys);
        }
        if ("360doctor".equals(gradeRole)) {
            //学员flow
            String doctorRoleFlow = InitConfig.getSysCfg("res_doctor_role_flow");
            paramMap.put("roleFlow", doctorRoleFlow);

            List<String> recTypeIdList = new ArrayList<>();
            recTypeIdList.add(ResRecTypeEnum.NurseDoctorGrade.getId());
            recTypeIdList.add(ResRecTypeEnum.TeacherDoctorGradeTwo.getId());
            recTypeIdList.add(ResRecTypeEnum.TeacherDoctorGrade.getId());
            paramMap.put("recTypeIdList", recTypeIdList);

            paramMap.put("userName", userName);
            paramMap.put("trainingSpeId", trainingSpeId);
            paramMap.put("studentType", studentType);
            //查出当前机构的所有带教老师
            PageHelper.startPage(currentPage, getPageSize(request));
            List<teacherRec> userList = resGradeBiz.getDoctorByRecAndAvgScore(paramMap);
            getGradeAvgs(userList);
            model.addAttribute("datas", userList);
            model.addAttribute("studentTypes", studentType);

        } else if ("360teacher".equals(gradeRole)) {
            List<String> recTypes = new ArrayList<>();
            if (GlobalConstant.USER_LIST_CHARGE.equals(role)) {
                //带教flow
                recTypes.add(ResRecTypeEnum.TeacherAssess.getId());
                recTypes.add(ResRecTypeEnum.TeacherGradeTwo.getId());
                paramMap.put("recTypeIds", recTypes);

                paramMap.put("userName", userName);
                paramMap.put("deptFlow", deptFlow);
                paramMap.put("deptName", deptName);

                //查出当前机构的所有带教老师
                PageHelper.startPage(currentPage, getPageSize(request));
                List<teacherRec> userList = resGradeBiz.getUserByRecAndAvgScore2(paramMap);
                getGradeAvgs(userList);
                model.addAttribute("datas", userList);
            } else {
                //带教flow
                recTypes.add(ResRecTypeEnum.TeacherAssess.getId());
                recTypes.add(ResRecTypeEnum.TeacherGradeTwo.getId());
                paramMap.put("recTypeIds", recTypes);
                paramMap.put("userName", userName);
                paramMap.put("deptFlow", deptFlow);
                paramMap.put("deptName", deptName);

                //查出当前机构的所有带教老师
                PageHelper.startPage(currentPage, getPageSize(request));
                List<teacherRec> userList = resGradeBiz.getUserByRecAndAvgScore(paramMap);
                getGradeAvgs(userList);
                model.addAttribute("datas", userList);
            }
        } else if ("ManageDoctorAssess360".equals(gradeRole)) {
            paramMap.put("userName", userName);
            paramMap.put("deptFlow", deptFlow);
            paramMap.put("trainingTypeId", trainingTypeId);
            PageHelper.startPage(currentPage, getPageSize(request));
            List<Map<String, String>> userList = resGradeBiz.getUserByManageScore(paramMap);
            if (null != userList && userList.size() > 0) {
                ArrayList<String> cfgList = new ArrayList<>();
                Map<String, Object> scoreSumMap = new HashMap<String, Object>();
                for (Map<String, String> map : userList) {
                    if (null != map.get("score") && StringUtil.isNotBlank(map.get("score"))) {
                        if (!cfgList.contains(map.get("cfgFlow"))) {
                            cfgList.add(map.get("cfgFlow"));
                        }
                        String processFlow = map.get("processFlow");
                        String[] scores = map.get("score").split(",");
                        String[] recFlows = map.get("recFlow").split(",");
                        String[] operUserRoleNames = map.get("operUserRoleName").split(",");
                        for (int i = 0; i < scores.length; i++) {
                            scoreMap.put(processFlow + operUserRoleNames[i], scores[i]);
                            scoreMap.put(processFlow + operUserRoleNames[i] + "recFlow", recFlows[i]);
                        }
                    }
                }
                for (String cfg : cfgList) {
                    int scoreSum = 0;
                    List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.readForm(cfg);
                    if (null != assessCfgList && assessCfgList.size() > 0) {
                        for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
                            if (null != resAssessCfgTitleForm.getItemList() && resAssessCfgTitleForm.getItemList().size() > 0) {
                                for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
                                    int s = Integer.parseInt("".equals(resAssessCfgItemForm.getScore()) ? "0" : resAssessCfgItemForm.getScore());
                                    scoreSum += s;
                                }
                            }
                        }
                    }
                    scoreSumMap.put(cfg, scoreSum);
                }
                model.addAttribute("datas", userList);
                model.addAttribute("scoreMap", scoreMap);
                model.addAttribute("scoreSumMap", scoreSumMap);
            }
        } else if ("nurse".equals(gradeRole)) {
            ResAssessCfg cfg = new ResAssessCfg();
            cfg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            cfg.setCfgCodeId("NurseDoctorAssess");
            cfg.setFormStatusId(GlobalConstant.RECORD_STATUS_Y);
            List<ResAssessCfg> cfgList = assessCfgBiz.searchAssessCfgList(cfg);
            if (null != cfgList && cfgList.size() > 0) {
                List<ResAssessCfgTitleForm> parsedGradeList = assessCfgBiz.readForm(cfgList.get(0).getCfgFlow());
                Integer nurseAllScore = 0;
                if (null != parsedGradeList && parsedGradeList.size() > 0) {
                    for (ResAssessCfgTitleForm form : parsedGradeList) {
                        List<ResAssessCfgItemForm> itemList = form.getItemList();
                        if (null != itemList && itemList.size() > 0) {
                            for (ResAssessCfgItemForm itemForm : itemList) {
                                if (StringUtil.isNotBlank(itemForm.getScore())) {
                                    nurseAllScore = nurseAllScore + Integer.parseInt(itemForm.getScore());
                                }
                            }
                        }
                    }
                    model.addAttribute("nurseAllScore", nurseAllScore);
                }
            }
            paramMap.put("deptFlow", deptFlow);
            paramMap.put("userName", userName);
            PageHelper.startPage(currentPage, getPageSize(request));
            List<Map<String, String>> dataList = resRecBiz.searchNurseAssEvaluate(paramMap);
            model.addAttribute("datas", dataList);
        }else if ("patient".equals(gradeRole)){
            //查询该基地的科室
            SysDept sysDept = new SysDept();
            sysDept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
            sysDept.setRecordStatus("Y");
            List<SysDept> deptList = deptBiz.searchDept(sysDept);
            model.addAttribute("deptList",deptList);

            paramMap.put("roleFlag",gradeRole);
            paramMap.put("patientName",patientName);
            paramMap.put("userFlow",GlobalContext.getCurrentUser().getUserFlow());
            paramMap.put("doctorName",doctorName);
            paramMap.put("trainingSpeId",trainingSpeId);
            paramMap.put("sessionNumber",sessionNumber);
            paramMap.put("deptFlow",deptFlow);
            paramMap.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
            if (null!=docType && docType.length>0){
                paramMap.put("docTypeList", Arrays.asList(docType));
            }
            model.addAttribute("docType", docType);
            PageHelper.startPage(currentPage,getPageSize(request));
            List<Map<String, String>> resultList = resGradeBiz.patientEvaluate(paramMap);
            model.addAttribute("datas", resultList);
        }
        return "hbres/evaAudit/resultSearch/evaluateSearch";
    }

    @RequestMapping(value = {"/gradeSearchDoc"})
    public String gradeSearchDoc(
            String gradeRole,
            String keyCode,
            String date,
            String role, String tdFlag, String deptFlow, String processFlow, String recFlow, String cfgFlow,
            Model model) {
        model.addAttribute("gradeRole", gradeRole);
        SysUser currentUser = GlobalContext.getCurrentUser();
        //当前用户所在机构
        String orgFlow = currentUser.getOrgFlow();
        //登记类型
        String recType = "";
        //评分类型
        String cfgCode = "";
        //查询条件
        Map<String, Object> scoreSumMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (GlobalConstant.USER_LIST_CHARGE.equals(role)) {
            List<SysUserDept> sysUserDepts = deptBiz.searchByUserFlow(currentUser.getUserFlow());
            List<String> deptFlows = new ArrayList<>();
            for (SysUserDept sysUserDept : sysUserDepts) {
                deptFlows.add(sysUserDept.getDeptFlow());
            }
            deptFlows.add(currentUser.getDeptFlow());
            paramMap.put("deptFlows", deptFlows);
        } else if (GlobalConstant.USER_LIST_GLOBAL.equals(role)) {

        }
        model.addAttribute("tdFlag", gradeRole);
        paramMap.put("orgFlow", orgFlow);
//        paramMap.put("sessionNumber",date.equals("null")?null:date);
        if ("teacher".equals(gradeRole)) {
//            recType = ResRecTypeEnum.TeacherGrade.getId();
//            paramMap.put("recTypeId", recType);
            paramMap.put("teacherFlow", keyCode);
            paramMap.put("sessionNumber", date);

            List<String> recTypeIds = Lists.newArrayList(ResRecTypeEnum.TeacherAssess.getId(),ResRecTypeEnum.TeacherGradeTwo.getId());
            paramMap.put("recTypeIdList", recTypeIds);

            paramMap.put("cfgFlow",cfgFlow);
        } else if ("head".equals(gradeRole)) {
            recType = ResRecTypeEnum.DeptGrade.getId();
            paramMap.put("recTypeId", recType);
            paramMap.put("deptFlow", keyCode);
            paramMap.put("sessionNumber", date);
        } else if ("doctor".equals(gradeRole)) {
            paramMap.put("doctorFlow", keyCode);
            List<String> recTypeIdList = new ArrayList<>();
//            recTypeIdList.add(ResRecTypeEnum.NurseDoctorGrade.getId());
            recTypeIdList.add(ResRecTypeEnum.TeacherDoctorGrade.getId());
            recTypeIdList.add(ResRecTypeEnum.ManageDoctorAssess360.getId());
//            recTypeIdList.add(ResRecTypeEnum.DeptGrade.getId());
//            recTypeIdList.add(ResRecTypeEnum.TeacherGrade.getId());
            paramMap.put("recTypeIdList", recTypeIdList);
            paramMap.put("deptFlow", deptFlow);
            paramMap.put("processFlow", processFlow);
        } else if ("360teacher".equals(gradeRole)) {
            List<String> recTypeIdList = new ArrayList<>();
            recTypeIdList.add(ResRecTypeEnum.TeacherAssess.getId());
            recTypeIdList.add(ResRecTypeEnum.TeacherGradeTwo.getId());
            paramMap.put("recTypeIdList", recTypeIdList);
            paramMap.put("teacherFlow", keyCode);
        } else if ("360doctor".equals(gradeRole)) {
            paramMap.put("doctorFlow", keyCode);
            List<String> recTypeIdList = new ArrayList<>();
            recTypeIdList.add(ResRecTypeEnum.NurseDoctorGrade.getId());
            recTypeIdList.add(ResRecTypeEnum.TeacherDoctorGrade.getId());
            recTypeIdList.add(ResRecTypeEnum.TeacherDoctorGradeTwo.getId());
            paramMap.put("recTypeIdList", recTypeIdList);
            paramMap.put("deptFlow", deptFlow);
            paramMap.put("processFlow", processFlow);
        }
        paramMap.put("recFlow", recFlow);
        //获取评分数据
        List<Map<String, String>> recList = null;
        if ("head".equals(gradeRole)) {
            recList = resGradeBiz.getRecContentByProcess2(paramMap);
        } else if ("teacher".equals(gradeRole) || "360teacher".equals(gradeRole)) {
            recList = resGradeBiz.getRecContentByProcess2(paramMap);
        } else if ("doctor".equals(gradeRole) || "360doctor".equals(gradeRole) || "ManageDoctorAssess360".equals(gradeRole)) {
            recList = resGradeBiz.getRecContentByProcess3(paramMap);
        }else{
            recList = resGradeBiz.getRecContentByProcess2(paramMap);
        }
        if (recList != null && !recList.isEmpty()) {
            Map<String, Float> scoreMap = new HashMap<String, Float>();
            Map<String, List<ResAssessCfgTitleForm>> assessCfgLists = new HashMap<String, List<ResAssessCfgTitleForm>>();
            for (Map<String, String> map : recList) {
                if (ResRecTypeEnum.NurseDoctorGrade.getId().equals(map.get("recTypeId"))) {
                    map.put("roleName", "护士");
                } else if (ResRecTypeEnum.TeacherDoctorGrade.getId().equals(map.get("recTypeId")) || ResRecTypeEnum.TeacherDoctorGradeTwo.getId().equals(map.get("recTypeId"))) {
                    map.put("roleName", "带教");
                }
                if (null != map.get("operUserRoleName") && StringUtil.isNotBlank(map.get("operUserRoleName"))) {
                    String operUserRoleName = map.get("operUserRoleName");
                    if (operUserRoleName.equals("head")) {
                        map.put("roleName", "教学主任");
                    } else if (operUserRoleName.equals("secretary")) {
                        map.put("roleName", "教学秘书");
                    } else if (operUserRoleName.equals("speAdmin")) {
                        map.put("roleName", "专业基地主任");
                    } else if (operUserRoleName.equals("local")) {
                        map.put("roleName", "基地管理员");
                    }
                }
                String operTime1 = map.get("operTime");
                if (StringUtil.isNotBlank(operTime1)) {
                    map.put("operTime", operTime1.substring(0, 4) + "-" + operTime1.substring(4, 6) + "-" + operTime1.substring(6, 8));
                }
                String operUserFlow = map.get("operUserFlow") + map.get("recFlow");
                int scoreSum = 0;
                List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.readForm(map.get("cfgFlow"));
                if (null != assessCfgList && assessCfgList.size() > 0) {
                    for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
                        if (null != resAssessCfgTitleForm.getItemList() && resAssessCfgTitleForm.getItemList().size() > 0) {
                            for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
                                int s = Integer.parseInt("".equals(resAssessCfgItemForm.getScore()) ? "0" : resAssessCfgItemForm.getScore());
                                scoreSum += s;
                            }
                        }
                    }
                }
                assessCfgLists.put(operUserFlow, assessCfgList);
                scoreSumMap.put(operUserFlow, scoreSum);
                String content = map.get("content");
                Map<String, Object> gradeMap = resRecBiz.parseGradeXml(content);
                if (gradeMap != null && !gradeMap.isEmpty()) {
                    for (String gk : gradeMap.keySet()) {
                        Object o = gradeMap.get(gk);
                        Float score = 0f;
                        if (o instanceof Map) {
                            Map<String, String> dataMap = (Map<String, String>) o;
                            if (dataMap != null) {
                                try {
                                    String scoreS = dataMap.get("score");
                                    score = Float.valueOf(scoreS);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                putMapVal(scoreMap, operUserFlow + gk, score);
                            }
                        } else {
                            try {
                                String scoreS = (String) gradeMap.get("totalScore");
                                score = Float.valueOf(scoreS);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            scoreMap.put(operUserFlow, score);
//                            putMapVal(scoreMap,operUserFlow,score);
                        }
                    }

                }
            }
            final Map<String, Float> scoreMapTemp = scoreMap;
            Collections.sort(recList, new Comparator<Map<String, String>>() {
                @Override
                public int compare(Map<String, String> m1, Map<String, String> m2) {
                    String k1 = m1.get("operUserFlow") + m1.get("recFlow");
                    String k2 = m2.get("operUserFlow") + m2.get("recFlow");
                    Float s1 = scoreMapTemp.get(k1);
                    Float s2 = scoreMapTemp.get(k2);
                    Float result = s2 - s1;
                    return result > 0 ? 1 : result == 0 ? 0 : -1;
                }
            });
            model.addAttribute("scoreMap", scoreMap);
            model.addAttribute("assessCfgLists", assessCfgLists);
        }
        model.addAttribute("recList", recList);
        model.addAttribute("scoreSumMap", scoreSumMap);
        return "hbres/evaAudit/resultSearch/gradeSearchDoc";
    }

    private Map<String, Float> avg(List<Map<String, String>> recList) {
        //均分Map
        Map<String, Float> avgMap = new HashMap<String, Float>();
        String count = "Count";
        String total = "Total";
        for (Map<String, String> map : recList) {
            String key = map.get("key");
            putMapVal(avgMap, key + count, 1f);
            String content = map.get("content");
            Map<String, Object> gradeMap = resRecBiz.parseGradeXml(content);
            if (gradeMap != null && !gradeMap.isEmpty()) {
                for (String gk : gradeMap.keySet()) {
                    Object o = gradeMap.get(gk);
                    if (o instanceof Map) {
                        Map<String, String> dataMap = (Map<String, String>) o;
                        if (dataMap != null) {
                            Float score = 0f;
                            try {
                                String scoreS = dataMap.get("score");
                                score = Float.valueOf(scoreS);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            putMapVal(avgMap, key + "_" + gk, score);
                        }
                    } else {
                        Float score = 0f;
                        try {
                            String scoreS = (String) o;
                            score = Float.valueOf(scoreS);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        putMapVal(avgMap, key + "_" + total, score);
                    }
                }
            }
        }
        if (!avgMap.isEmpty()) {
            Set<String> keySet = avgMap.keySet();
            for (String k : keySet) {
                if (k != null) {
                    int ki = k.indexOf("_");
                    if (!(ki < 0)) {
                        String dataKey = k.substring(0, ki);
                        Float itemTotal = avgMap.get(k);
                        if (itemTotal != null) {
                            Float countUser = avgMap.get(dataKey + count);
                            if (itemTotal != 0 && countUser != 0) {
                                Float result = itemTotal / countUser;
                                result = new BigDecimal(result).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                                avgMap.put(k, result);
                            }
                        }
                    }
                }
            }
        }
        return avgMap;
    }

    private void putMapVal(Map<String, Float> map, String key, Float val) {
        if (map != null) {
            Float v = map.get(key);
            if (v == null) {
                v = val;
            } else {
                v += val;
            }
            v = new BigDecimal(v).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
            map.put(key, v);
        }
    }

    @RequestMapping(value = "/exportEval")
    public void exportEval(HttpServletResponse response, String role, String studentType, String trainingSpeId, String gradeRole, String deptFlow, String userName, String deptName, String orgFlow,
                           String startScore, String endScore, String sessionNumber) throws Exception {
        SysUser currentUser = GlobalContext.getCurrentUser();
        if (StringUtil.isBlank(orgFlow)) {
            orgFlow = currentUser.getOrgFlow();
        }

        //登记类型
        String recType = "";
        //评分类型
        String cfgCode = "";

        List<String> keys = new ArrayList<String>();
        Object waitSort = null;
        Map<String, Object> scoreMap = new HashMap<String, Object>();
        //查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (GlobalConstant.USER_LIST_CHARGE.equals(role)) {
            List<SysUserDept> sysUserDepts = deptBiz.searchByUserFlow(currentUser.getUserFlow());
            List<String> deptFlows = new ArrayList<>();
            for (SysUserDept sysUserDept : sysUserDepts) {
                deptFlows.add(sysUserDept.getDeptFlow());
            }
            deptFlows.add(currentUser.getDeptFlow());
            paramMap.put("deptFlows", deptFlows);
        } else if (GlobalConstant.USER_LIST_GLOBAL.equals(role)) {

        }

        paramMap.put("orgFlow", orgFlow);
        paramMap.put("sessionNumber", sessionNumber);
        paramMap.put("startScore", startScore);
        paramMap.put("endScore", endScore);
        List<teacherRec> userList = new ArrayList<>();
        List<Map<String, String>> dataList=new ArrayList<>();

        if ("doctor".equals(gradeRole)) {
            //学员flow
            String doctorRoleFlow = InitConfig.getSysCfg("res_doctor_role_flow");
            paramMap.put("roleFlow", doctorRoleFlow);

            List<String> recTypeIdList = new ArrayList<>();
            recTypeIdList.add(ResRecTypeEnum.NurseDoctorGrade.getId());
            recTypeIdList.add(ResRecTypeEnum.TeacherDoctorGrade.getId());
            paramMap.put("recTypeIdList", recTypeIdList);

            paramMap.put("userName", userName);
            paramMap.put("trainingSpeId", trainingSpeId);
            paramMap.put("studentType", studentType);

            //查出当前机构的所有带教老师
            userList = resGradeBiz.getDoctorByRecAndAvgScore(paramMap);

            for (teacherRec teacherRec : userList) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("orgFlow", orgFlow);
                param.put("doctorFlow", teacherRec.getUserFlow());
                List<String> recTypeIdList1 = new ArrayList<>();
                recTypeIdList1.add(ResRecTypeEnum.TeacherDoctorGrade.getId());
                param.put("recTypeIdList", recTypeIdList1);
                param.put("deptFlow", deptFlow);
                param.put("processFlow", teacherRec.getProcessFlow());
                param.put("recFlow", null);
                List<Map<String, String>> recList = resGradeBiz.getRecContentByProcess3(param);
                if (CollectionUtils.isNotEmpty(recList)) {
                    teacherRec.setOperUserName(recList.get(0).get("operUserName"));
                    int scoreSum = 0;
                    List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.readForm(recList.get(0).get("cfgFlow"));
                    if (null != assessCfgList && assessCfgList.size() > 0) {
                        for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
                            if (null != resAssessCfgTitleForm.getItemList() && resAssessCfgTitleForm.getItemList().size() > 0) {
                                for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
                                    int s = Integer.parseInt("".equals(resAssessCfgItemForm.getScore()) ? "0" : resAssessCfgItemForm.getScore());
                                    scoreSum += s;
                                }
                            }
                        }
                    }
                    teacherRec.setSumScore(String.valueOf(scoreSum));
                }
            }

        } else if ("teacher".equals(gradeRole)) {
            //带教flow
//            recType = ResRecTypeEnum.TeacherGrade.getId();
//            paramMap.put("recTypeId", recType);
            List<String> recTypeIds = Lists.newArrayList(ResRecTypeEnum.TeacherAssess.getId(),ResRecTypeEnum.TeacherGradeTwo.getId());
            paramMap.put("recTypeIds", recTypeIds);
            paramMap.put("userName", userName);
            paramMap.put("deptFlow", deptFlow);
            paramMap.put("deptName", deptName);

            //查出当前机构的所有带教老师
            userList = resGradeBiz.getUserByRecAndAvgScore(paramMap);
        } else if ("head".equals(gradeRole)) {
            paramMap.put("userName", userName);
            paramMap.put("deptFlow", deptFlow);
            paramMap.put("deptName", deptName);

            recType = ResRecTypeEnum.DeptGrade.getId();
            paramMap.put("recTypeId", recType);
            cfgCode = ResAssessTypeEnum.DeptAssess.getId();
            List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
            userList = resGradeBiz.getDeptByRecAndAvgScore(paramMap);
        } else if ("nurse".equals(gradeRole)) {
            ResAssessCfg cfg = new ResAssessCfg();
            cfg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            cfg.setCfgCodeId("NurseDoctorAssess");
            cfg.setFormStatusId(GlobalConstant.RECORD_STATUS_Y);
            List<ResAssessCfg> cfgList = assessCfgBiz.searchAssessCfgList(cfg);
            Integer nurseAllScore = 0;
            if (null != cfgList && cfgList.size() > 0) {
                List<ResAssessCfgTitleForm> parsedGradeList = assessCfgBiz.readForm(cfgList.get(0).getCfgFlow());
                if (null != parsedGradeList && parsedGradeList.size() > 0) {
                    for (ResAssessCfgTitleForm form : parsedGradeList) {
                        List<ResAssessCfgItemForm> itemList = form.getItemList();
                        if (null != itemList && itemList.size() > 0) {
                            for (ResAssessCfgItemForm itemForm : itemList) {
                                if (StringUtil.isNotBlank(itemForm.getScore())) {
                                    nurseAllScore = nurseAllScore + Integer.parseInt(itemForm.getScore());
                                }
                            }
                        }
                    }
                }
            }
            paramMap.put("deptFlow", deptFlow);
            paramMap.put("userName", userName);
            dataList = resRecBiz.searchNurseAssEvaluate(paramMap);
            for (Map<String, String> map : dataList) {
                map.put("nurseAllScore", nurseAllScore.toString());
            }
        }
        if (!"nurse".equals(gradeRole)) {
            for (teacherRec teacherRec : userList) {
                teacherRec.setScore("100");
            }
        }


        String[] titles = null;
        if ("head".equals(gradeRole)) {
            titles = new String[]{
                    "deptName:科室",
                    "sessionNumber:年份",
                    "score:标准分",
                    "avg:总均分"
            };
        }
        if ("teacher".equals(gradeRole)) {
            List<Map<String,String>> resultList = new ArrayList<>();
            for(teacherRec user : userList){
                Map<String, Object> queryMap = new HashMap<String, Object>();
                queryMap.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
                queryMap.put("teacherFlow", user.getUserFlow());
                queryMap.put("sessionNumber", user.getSessionNumber());
                List<String> recTypeIds = Lists.newArrayList(ResRecTypeEnum.TeacherAssess.getId(),ResRecTypeEnum.TeacherGradeTwo.getId());
                queryMap.put("recTypeIdList", recTypeIds);
                queryMap.put("cfgFlow",user.getCfgFlow());
                List<Map<String, String>> maps = resGradeBiz.getRecContentByProcess2(queryMap);
                if(CollectionUtils.isEmpty(maps)){
                    continue;
                }
                for(Map<String, String> map : maps){
                    Map<String,String> result = new HashMap<>();
                    result.put("userName",user.getUserName());
                    result.put("deptName",user.getDeptName());
                    result.put("sessionNumber",user.getSessionNumber());
                    result.put("cfgCodeName",user.getCfgCodeName());
                    result.put("cfgFlow",user.getCfgFlow());
                    result.put("totalScore",user.getTotalScore());
                    result.put("avgScore",user.getAvg());
                    result.put("operUserName",map.get("operUserName"));
                    String operTime1 = map.get("operTime");
                    if (StringUtil.isNotBlank(operTime1)) {
                        result.put("operTime", operTime1.substring(0, 4) + "-" + operTime1.substring(4, 6) + "-" + operTime1.substring(6, 8));
                    }
                    result.put("schTime",map.get("schStartDate") + "~" + map.get("schEndDate"));

                    String content = map.get("content");
                    Map<String, Object> gradeMap = resRecBiz.parseGradeXml(content);
                    if (gradeMap != null && !gradeMap.isEmpty()) {
                        for (String gk : gradeMap.keySet()) {
                            Object o = gradeMap.get(gk);
                            if (o instanceof Map) {
                                Map<String, String> dataMap = (Map<String, String>) o;
                                if (dataMap != null) {
                                    try {
                                        String scoreS = dataMap.get("score");
                                        result.put(gk,scoreS);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                try {
                                    String scoreS = (String) gradeMap.get("totalScore");
                                    result.put("doctorTotalScore",scoreS);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    }
                    resultList.add(result);
                }
            }

            List<String> titleList = new ArrayList<>();
            titleList.add("姓名");
            titleList.add("科室");
            titleList.add("年份");
            titleList.add("评分表单");
            titleList.add("标准分");
            titleList.add("总均分");
            titleList.add("学员姓名");
            titleList.add("评价日期");
            titleList.add("轮转时间");
            titleList.add("教学热情");
            titleList.add("临床教学能力");
            titleList.add("临床工作能力");
            titleList.add("医患交流技巧");
            titleList.add("培养住院医师循证医学思维");
            titleList.add("教学反馈");
            titleList.add("其他");
            titleList.add("总得分");


            //创建工作簿
            HSSFWorkbook wb = new HSSFWorkbook();
            // 为工作簿添加sheet
            HSSFSheet sheet = wb.createSheet("sheet1");
            //定义将用到的样式
            HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
            styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            //列宽自适应
            HSSFRow rowOne = sheet.createRow(0);//第1行
            HSSFRow rowTwo = sheet.createRow(1);//第2行

            // 设计表头
            HSSFCell cellTitleOne;
            CellRangeAddress cellRangeAddress;

            for(int i =0; i<9;i++){
                cellRangeAddress = new CellRangeAddress(0, 1, i, i);
                sheet.addMergedRegion(cellRangeAddress);
                cellTitleOne = rowOne.createCell(i);
                cellTitleOne.setCellValue(titleList.get(i));
                cellTitleOne.setCellStyle(styleCenter);
            }

            cellRangeAddress = new CellRangeAddress(0, 0, 9, 11);
            sheet.addMergedRegion(cellRangeAddress);
            cellTitleOne = rowOne.createCell(9);
            cellTitleOne.setCellValue(titleList.get(9));
            cellTitleOne.setCellStyle(styleCenter);

            cellRangeAddress = new CellRangeAddress(1, 1, 9, 9);
            sheet.addMergedRegion(cellRangeAddress);
            cellTitleOne = rowTwo.createCell(9);
            cellTitleOne.setCellValue("热心教学并愿意花足够的时间参与住院医师的教学工作");
            cellTitleOne.setCellStyle(styleCenter);

            cellRangeAddress = new CellRangeAddress(1, 1, 10, 10);
            sheet.addMergedRegion(cellRangeAddress);
            cellTitleOne = rowTwo.createCell(10);
            cellTitleOne.setCellValue("能为住院医师创造良好的培训和教学");
            cellTitleOne.setCellStyle(styleCenter);

            cellRangeAddress = new CellRangeAddress(1, 1, 11, 11);
            sheet.addMergedRegion(cellRangeAddress);
            cellTitleOne = rowTwo.createCell(11);
            cellTitleOne.setCellValue("为人师表，工作努力并能与住院医师建立良好的工作关系");
            cellTitleOne.setCellStyle(styleCenter);

            cellRangeAddress = new CellRangeAddress(0, 0, 12, 13);
            sheet.addMergedRegion(cellRangeAddress);
            cellTitleOne = rowOne.createCell(12);
            cellTitleOne.setCellValue(titleList.get(10));
            cellTitleOne.setCellStyle(styleCenter);

            cellRangeAddress = new CellRangeAddress(1, 1, 12, 12);
            sheet.addMergedRegion(cellRangeAddress);
            cellTitleOne = rowTwo.createCell(12);
            cellTitleOne.setCellValue("是否有较强的教学能力");
            cellTitleOne.setCellStyle(styleCenter);

            cellRangeAddress = new CellRangeAddress(1, 1, 13, 13);
            sheet.addMergedRegion(cellRangeAddress);
            cellTitleOne = rowTwo.createCell(13);
            cellTitleOne.setCellValue("教学查房、专业讲座和其他教学活动准备充分，深入浅出，条理清楚");
            cellTitleOne.setCellStyle(styleCenter);

            cellRangeAddress = new CellRangeAddress(0, 0, 14, 15);
            sheet.addMergedRegion(cellRangeAddress);
            cellTitleOne = rowOne.createCell(14);
            cellTitleOne.setCellValue(titleList.get(11));
            cellTitleOne.setCellStyle(styleCenter);

            cellRangeAddress = new CellRangeAddress(1, 1, 14, 14);
            sheet.addMergedRegion(cellRangeAddress);
            cellTitleOne = rowTwo.createCell(14);
            cellTitleOne.setCellValue("有较好的临床经验和熟练的临床操作技能");
            cellTitleOne.setCellStyle(styleCenter);

            cellRangeAddress = new CellRangeAddress(1, 1, 15, 15);
            sheet.addMergedRegion(cellRangeAddress);
            cellTitleOne = rowTwo.createCell(15);
            cellTitleOne.setCellValue("认真督导住院医师临床工作，致力于培养住院医师的独立工作能力");
            cellTitleOne.setCellStyle(styleCenter);

            cellRangeAddress = new CellRangeAddress(0, 0, 16, 16);
            sheet.addMergedRegion(cellRangeAddress);
            cellTitleOne = rowOne.createCell(16);
            cellTitleOne.setCellValue(titleList.get(12));
            cellTitleOne.setCellStyle(styleCenter);

            cellRangeAddress = new CellRangeAddress(1, 1, 16, 16);
            sheet.addMergedRegion(cellRangeAddress);
            cellTitleOne = rowTwo.createCell(16);
            cellTitleOne.setCellValue("有较强的沟通能力，并能与患者及其家属保持健康的医患关系");
            cellTitleOne.setCellStyle(styleCenter);

            cellRangeAddress = new CellRangeAddress(0, 0, 17, 17);
            sheet.addMergedRegion(cellRangeAddress);
            cellTitleOne = rowOne.createCell(17);
            cellTitleOne.setCellValue(titleList.get(13));
            cellTitleOne.setCellStyle(styleCenter);

            cellRangeAddress = new CellRangeAddress(1, 1, 17, 17);
            sheet.addMergedRegion(cellRangeAddress);
            cellTitleOne = rowTwo.createCell(17);
            cellTitleOne.setCellValue("能够运用循证医学，规范的临床路径和标准化治疗进行教学");
            cellTitleOne.setCellStyle(styleCenter);

            cellRangeAddress = new CellRangeAddress(0, 0, 18, 18);
            sheet.addMergedRegion(cellRangeAddress);
            cellTitleOne = rowOne.createCell(18);
            cellTitleOne.setCellValue(titleList.get(14));
            cellTitleOne.setCellStyle(styleCenter);

            cellRangeAddress = new CellRangeAddress(1, 1, 18, 18);
            sheet.addMergedRegion(cellRangeAddress);
            cellTitleOne = rowTwo.createCell(18);
            cellTitleOne.setCellValue("对住院医师在培训中的表现做及时的反馈");
            cellTitleOne.setCellStyle(styleCenter);

            cellRangeAddress = new CellRangeAddress(0, 0, 19, 19);
            sheet.addMergedRegion(cellRangeAddress);
            cellTitleOne = rowOne.createCell(19);
            cellTitleOne.setCellValue(titleList.get(15));
            cellTitleOne.setCellStyle(styleCenter);

            cellRangeAddress = new CellRangeAddress(1, 1, 19, 19);
            sheet.addMergedRegion(cellRangeAddress);
            cellTitleOne = rowTwo.createCell(19);
            cellTitleOne.setCellValue("综合评分");
            cellTitleOne.setCellStyle(styleCenter);

            cellRangeAddress = new CellRangeAddress(0, 1, 20, 20);
            sheet.addMergedRegion(cellRangeAddress);
            cellTitleOne = rowOne.createCell(20);
            cellTitleOne.setCellValue(titleList.get(16));
            cellTitleOne.setCellStyle(styleCenter);

            String fileName = "带教得分.xls";
            for (int i = 0; i < resultList.size(); i++) {
                int j = 0;
                HSSFRow newRow = sheet.createRow(i + 2); //第3行
                Map<String,String> map = resultList.get(i);
                HSSFCell cellOne = newRow.createCell(j++);
                cellOne.setCellStyle(styleCenter);
                cellOne.setCellValue(map.get("userName"));
                cellOne = newRow.createCell(j++);
                cellOne.setCellStyle(styleCenter);
                cellOne.setCellValue(map.get("deptName"));
                cellOne = newRow.createCell(j++);
                cellOne.setCellStyle(styleCenter);
                cellOne.setCellValue(map.get("sessionNumber"));
                cellOne = newRow.createCell(j++);
                cellOne.setCellStyle(styleCenter);
                cellOne.setCellValue(map.get("cfgCodeName"));
                cellOne = newRow.createCell(j++);
                cellOne.setCellStyle(styleCenter);
                cellOne.setCellValue(map.get("totalScore"));
                cellOne = newRow.createCell(j++);
                cellOne.setCellStyle(styleCenter);
                cellOne.setCellValue(map.get("avgScore"));
                cellOne = newRow.createCell(j++);
                cellOne.setCellStyle(styleCenter);
                cellOne.setCellValue(map.get("operUserName"));
                cellOne = newRow.createCell(j++);
                cellOne.setCellStyle(styleCenter);
                cellOne.setCellValue(map.get("operTime"));
                cellOne = newRow.createCell(j++);
                cellOne.setCellStyle(styleCenter);
                cellOne.setCellValue(map.get("schTime"));

                String cfgFlow = map.get("cfgFlow");
                if(Objects.equals(cfgFlow,"f38b2734220c4b659ddb85f9b99b82ce")){
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("eab709b14f3f45cdb826fdbf14b1ee33"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("a315fceb3cc64f4c849e708ebece75b1"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("7b3280f45e51457995803cb52399162b"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("08193492766249e2996419fd7136f50e"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("448f379676ce4354aa1fb4fe796d749a"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("6823eb22ff504698b76e3d578e3c4f82"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("f66777254a6a4763953c769cfb1f88a3"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(null == map.get("4ff0ac2711ae4764b05b999420d07eff") ? "-" : map.get("4ff0ac2711ae4764b05b999420d07eff"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("3e7f8cca17a44ab893da22cfe059a613"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("b8baa53fffab4801a857ff1f4b1c4e28"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("0b32059b6152445b9f9e6a9eaa800cf1"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("doctorTotalScore"));
                }
                if(Objects.equals(cfgFlow,"0ddacbf047fc4beb9b9d94246237cbe3")){
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("176010b0d2b14499b4fd29cfa67142b0"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("77eba39220e24a08bd44fa830b7fe4b6"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("bdf95c62eb054dcb85e58553d3afd36b"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("8f84e4f924464409a92ac28bdfb4c5e3"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("e0c2b61066954e3c8676dfb4da245ed8"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("c179c504ff88437ead0e84f172a788a3"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("bc78d050da4e447d86c9a7906d4b93b3"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(null == map.get("4ff0ac2711ae4764b05b999420d07eff") ? "-" : map.get("4ff0ac2711ae4764b05b999420d07eff"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("d06d50d56fa44e3c9f59da2f548bbd35"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("79e15a8f747c4e3a977752d72a09e873"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("8fcdccfc6c3e4e7d9a8953b6da5cb981"));
                    cellOne = newRow.createCell(j++);
                    cellOne.setCellStyle(styleCenter);
                    cellOne.setCellValue(map.get("doctorTotalScore"));
                }
            }

            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            wb.write(response.getOutputStream());
        }
        if ("doctor".equals(gradeRole)) {
            titles = new String[]{
                    "userName:姓名",
                    "trainingSpeName:培训专业",
                    "sessionNumber:年级",
                    "deptName:科室",
                    "startTime:轮转开始时间",
                    "endTime:轮转结束时间",
                    "sumScore:总分",
                    "avg:得分",
                    "operUserName:评价人"
            };
        }
        if ("nurse".equals(gradeRole)){
            titles = new String[]{
                    "userName:姓名",
                    "trainingTypeName:人员类型",
                    "trainingSpeName:培训专业",
                    "sessionNumber:年级",
                    "schDeptName:科室",
                    "schStartDate:轮转开始时间",
                    "schEndDate:轮转结束时间",
                    "nurseAllScore:标准分",
                    "allScore:得分",
                    "operUserName:评价人"
            };
            String fileName = "护士评价记录表.xls";
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            ExcleUtile.exportSimpleExcleByObjs(titles, dataList, response.getOutputStream());
        }else {
//            String fileName = "双向评价记录表.xls";
//            fileName = URLEncoder.encode(fileName, "UTF-8");
//            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//            response.setContentType("application/octet-stream;charset=UTF-8");
//            ExcleUtile.exportSimpleExcleByObjs(titles, userList, response.getOutputStream());
        }

    }

    @RequestMapping(value = {"/exportGradeSearchDoc"})
    public void exportGradeSearchDoc(
            String gradeRole,
            String keyCode,
            String operStartDate,
            String operEndDate,
            String date,
            String recFlow,
            String role,
            HttpServletResponse response, HttpServletRequest request
    ) throws Exception {
        boolean isOneFile = false;
        //当前用户所在机构
        SysUser currentUser = GlobalContext.getCurrentUser();
        String orgFlow = currentUser.getOrgFlow();
        //登记类型
        String recType = "";
        //评分类型
        String cfgCode = "";
        //查询条件
        Map<String, Object> scoreSumMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (GlobalConstant.USER_LIST_CHARGE.equals(role)) {
            List<SysUserDept> sysUserDepts = deptBiz.searchByUserFlow(currentUser.getUserFlow());
            List<String> deptFlows = new ArrayList<>();
            for (SysUserDept sysUserDept : sysUserDepts) {
                deptFlows.add(sysUserDept.getDeptFlow());
            }
            deptFlows.add(currentUser.getDeptFlow());
            paramMap.put("deptFlows", deptFlows);
        } else if (GlobalConstant.USER_LIST_GLOBAL.equals(role)) {

        }
        paramMap.put("orgFlow", orgFlow);
        paramMap.put("sessionNumber", date.equals("null") ? null : date);
        if ("teacher".equals(gradeRole)) {
            recType = ResRecTypeEnum.TeacherGrade.getId();
            paramMap.put("recTypeId", recType);
            paramMap.put("teacherFlow", keyCode);
        } else if ("head".equals(gradeRole)) {
            recType = ResRecTypeEnum.DeptGrade.getId();
            paramMap.put("recTypeId", recType);
            paramMap.put("deptFlow", keyCode);
        } else if ("doctor".equals(gradeRole)) {
            paramMap.put("doctorFlow", keyCode);
            List<String> recTypeIdList = new ArrayList<>();
            recTypeIdList.add(ResRecTypeEnum.NurseDoctorGrade.getId());
            recTypeIdList.add(ResRecTypeEnum.TeacherDoctorGrade.getId());
//            recTypeIdList.add(ResRecTypeEnum.DeptGrade.getId());
//            recTypeIdList.add(ResRecTypeEnum.TeacherGrade.getId());
            paramMap.put("recTypeIdList", recTypeIdList);
        }
        List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
        //获取评分数据
        List<Map<String, String>> recList = resGradeBiz.getRecContentByProcess2(paramMap);
        //导出结果集合
        List<List<Map<String, String>>> exportInfoList = new ArrayList<>();
        List<String> fileNames = new ArrayList<>();

        String[] titles = {
                "itemName:评分细则",
                "itemScore:标准分",
                "avgScore:总分"
        };
        int i = 0;
        if (recList != null && !recList.isEmpty()) {
            if (recList.size() == 1) {
                isOneFile = true;
            }
            Map<String, Float> scoreMap = new HashMap<String, Float>();
            for (Map<String, String> map : recList) {
                i++;
                //单条结果导出集合
                List<Map<String, String>> oneInfoList = new ArrayList<>();

                String operUserFlow = map.get("operUserFlow") + map.get("recFlow");

                String content = map.get("content");
                Map<String, Object> gradeMap = resRecBiz.parseGradeXml(content);
                if (gradeMap != null && !gradeMap.isEmpty()) {
                    for (String gk : gradeMap.keySet()) {
                        Object o = gradeMap.get(gk);
                        Float score = 0f;
                        if (o instanceof Map) {
                            Map<String, String> dataMap = (Map<String, String>) o;
                            if (dataMap != null) {
                                try {
                                    String scoreS = dataMap.get("score");
                                    score = Float.valueOf(scoreS);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                putMapVal(scoreMap, operUserFlow + gk, score);
                            }
                        } else {
                            try {
                                String scoreS = (String) gradeMap.get("totalScore");
                                score = Float.valueOf(scoreS);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            putMapVal(scoreMap, operUserFlow, score);
                        }
                    }

                }
                int sum = 0;
                for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
                    Map<String, String> itemMap = new HashMap<>();
                    itemMap.put("itemName", resAssessCfgTitleForm.getName());
                    itemMap.put("itemScore", "");
                    itemMap.put("avgScore", "");
                    oneInfoList.add(itemMap);
                    if (null != resAssessCfgTitleForm.getItemList() && resAssessCfgTitleForm.getItemList().size() > 0) {
                        for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
                            itemMap = new HashMap<>();
                            itemMap.put("itemName", resAssessCfgItemForm.getName());
                            itemMap.put("itemScore", resAssessCfgItemForm.getScore());
                            itemMap.put("avgScore", (scoreMap.get(operUserFlow + resAssessCfgItemForm.getId()) == null ? "" : scoreMap.get(operUserFlow + resAssessCfgItemForm.getId())) + "");
                            oneInfoList.add(itemMap);
                            sum += StringUtil.isBlank(resAssessCfgItemForm.getScore()) ? 0 : Integer.valueOf(resAssessCfgItemForm.getScore());
                        }
                    }
                }
                Map<String, String> itemMap = new HashMap<>();
                itemMap.put("itemName", "总分");
                itemMap.put("itemScore", sum + "");
                itemMap.put("avgScore", (scoreMap.get(operUserFlow) == null ? "" : scoreMap.get(operUserFlow)) + "");
                oneInfoList.add(itemMap);
                String fileName = map.get("operUserName") + map.get("deptName") + "(" + map.get("schStartDate") + "~" + map.get("schEndDate") + ")";
                if (isOneFile) {
                    if (StringUtil.isBlank(fileName))
                        fileName = "双向评价明细导出.xls";
                    else
                        fileName += ".xls";
                    fileName = URLEncoder.encode(fileName, "UTF-8");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                    response.setContentType("application/octet-stream;charset=UTF-8");
                    ExcleUtile.exportSimpleExcleByObjs(titles, oneInfoList, response.getOutputStream());
                } else {
                    if (StringUtil.isBlank(fileName))
                        fileName = "双向评价明细导出-" + i;
                    fileNames.add(fileName);
                    exportInfoList.add(oneInfoList);
                }
            }
        }
        if (!isOneFile) {
            String fileName = "双向评价明细导出";
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName + ".zip", "UTF-8") + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            ExcleUtile.exportExcel(fileName, fileNames, titles, exportInfoList, response.getOutputStream(), request);
        }

    }

    public List<teacherRec> getGradeAvgs(List<teacherRec> userList) {
        userList.stream().forEach(teacherRec -> {
            if (StringUtil.isNotEmpty(teacherRec.getCfgFlow())) {
                List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.readForm(teacherRec.getCfgFlow());
                ResAssessCfgTitleForm resAssessCfgTitleForm = assessCfgList.get(0);
                Float avg = Float.parseFloat(teacherRec.getAvg());
                int size = resAssessCfgTitleForm.getItemList().size();
                float num = avg / size;
                DecimalFormat df = new DecimalFormat("0.00");//格式化小数
                teacherRec.setAvg(df.format(num));
            }
        });
        return userList;
    }
}