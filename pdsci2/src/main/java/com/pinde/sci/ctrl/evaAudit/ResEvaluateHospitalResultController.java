package com.pinde.sci.ctrl.evaAudit;

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
import org.apache.commons.collections.CollectionUtils;
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
                recType = ResRecTypeEnum.TeacherGrade.getId();
                paramMap.put("recTypeId", recType);

                paramMap.put("userName", userName);
                paramMap.put("deptFlow", deptFlow);
                paramMap.put("deptName", deptName);

                //查出当前机构的所有带教老师
                PageHelper.startPage(currentPage, getPageSize(request));
                List<teacherRec> userList = resGradeBiz.getUserByRecAndAvgScore2(paramMap);
                model.addAttribute("datas", userList);
            } else {
                //带教flow
                recType = ResRecTypeEnum.TeacherGrade.getId();
                paramMap.put("recTypeId", recType);
                paramMap.put("userName", userName);
                paramMap.put("deptFlow", deptFlow);
                paramMap.put("deptName", deptName);

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
            String role, String tdFlag, String deptFlow, String processFlow, String recFlow,
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
            recType = ResRecTypeEnum.TeacherGrade.getId();
            paramMap.put("recTypeId", recType);
            paramMap.put("teacherFlow", keyCode);
            paramMap.put("sessionNumber", date);
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
        List<Map<String, String>> recList = resGradeBiz.getRecContentByProcess2(paramMap);
        if ("head".equals(gradeRole)) {
            recList = resGradeBiz.getRecContentByProcess2(paramMap);
        } else if ("teacher".equals(gradeRole) || "360teacher".equals(gradeRole)) {
            recList = resGradeBiz.getRecContentByProcess2(paramMap);
        } else if ("doctor".equals(gradeRole) || "360doctor".equals(gradeRole) || "ManageDoctorAssess360".equals(gradeRole)) {
            recList = resGradeBiz.getRecContentByProcess3(paramMap);
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
            recType = ResRecTypeEnum.TeacherGrade.getId();
            paramMap.put("recTypeId", recType);
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
            titles = new String[]{
                    "userName:姓名",
                    "deptName:科室",
                    "sessionNumber:年份",
                    "score:标准分",
                    "avg:总均分"
            };
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
            String fileName = "双向评价记录表.xls";
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            ExcleUtile.exportSimpleExcleByObjs(titles, userList, response.getOutputStream());
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