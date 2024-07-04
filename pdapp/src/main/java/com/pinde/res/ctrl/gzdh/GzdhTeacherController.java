package com.pinde.res.ctrl.gzdh;


import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.common.IResPowerCfgBiz;
import com.pinde.res.biz.hbres.IHbresAppBiz;
import com.pinde.res.biz.stdp.*;
import com.pinde.res.biz.gzdh.IGzdhAppBiz;
import com.pinde.res.biz.gzdh.IGzdhStudentBiz;
import com.pinde.res.biz.gzdh.IGzdhTeacherBiz;
import com.pinde.res.ctrl.hbres.ActivityImageFileForm;
import com.pinde.res.enums.hbres.ResAssessScoreTypeEnum;
import com.pinde.res.enums.hbres.ResAssessTypeEnum;
import com.pinde.res.enums.hbres.ResRecTypeEnum;
import com.pinde.res.enums.lcjn.DictTypeEnum;
import com.pinde.res.enums.stdp.ActivityTypeEnum;
import com.pinde.res.enums.stdp.RegistryTypeEnum;
import com.pinde.res.model.jswjw.mo.ResAssessCfgItemForm;
import com.pinde.res.model.jswjw.mo.ResAssessCfgTitleForm;
import com.pinde.sci.model.mo.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/gzdh/teacher")
public class GzdhTeacherController {
    private static Logger logger = LoggerFactory.getLogger(GzdhTeacherController.class);

    @Autowired
    private IGzdhStudentBiz studentBiz;
    @Autowired
    private IGzdhAppBiz appbiz;
    @Autowired
    private IGzdhTeacherBiz teacherBiz;
    @Autowired
    private IResGradeBiz gradeBiz;
    @Autowired
    private IResPowerCfgBiz resPowerCfgBiz;
    @Autowired
    private IResSchProcessExpressBiz expressBiz;
    @Autowired
    private IInxInfoBiz inxInfoBiz;
    @Autowired
    private INoticeBiz noticeBiz;
    @Autowired
    private IResActivityBiz activityBiz;
    @Autowired
    private IHbresAppBiz hbresBiz;

    @ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
        logger.error(this.getClass().getCanonicalName() + " some error happened", e);
        return "res/gzdh/500";
    }

    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET})
    public String test() {
        return "res/gzdh/teacher/test";
    }

    @RequestMapping(value = {"/remember"}, method = {RequestMethod.GET})
    public String remember(String userFlow, String doctorFlow, String cataFlow, String dataFlow, String funcTypeId, String funcFlow, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userFlow", userFlow);
        session.setAttribute("doctorFlow", doctorFlow);
        session.setAttribute("cataFlow", cataFlow);
        session.setAttribute("dataFlow", dataFlow);
        session.setAttribute("funcTypeId", funcTypeId);
        session.setAttribute("funcFlow", funcFlow);
        return "/res/gzdh/teacher/test";
    }

    @RequestMapping(value = {"/doctorList"}, method = {RequestMethod.GET})
    public String doctorList(String userFlow,
                             String searchData,
                             String deptFlow,
                             Integer pageIndex,
                             Integer pageSize,
                             HttpServletRequest request,
                             Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "3030101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/teacher/doctorList";
        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/gzdh/teacher/doctorList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/gzdh/teacher/doctorList";
        }

        //包装筛选条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("teacherUserFlow", userFlow);
        paramMap.put("deptFlow", deptFlow);

        //解析查询条件json字符串  statusId:Entering(已入科) , NotEntered(未入科) , Exited(已出科) doctorName:
        Map<String, String> searchMap = null;
        String statusId = "";
        String doctorName = "";
        if (StringUtil.isNotBlank(searchData)) {
            try {
                //为json字符串转码
                System.err.println(searchData);
                searchData = new String(searchData.getBytes("ISO8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //转换json字符串为map对象
            searchMap = (Map<String, String>) JSON.parse(searchData);
            if (searchMap != null && !searchMap.isEmpty()) {
                statusId = searchMap.get("statusId");
                doctorName = searchMap.get("doctorName");
                paramMap.put("statusId", statusId);
                paramMap.put("doctorName", doctorName);
            }
        }

        //筛选出该教师带过的所有学员
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String, Object>> doctorMapList = teacherBiz.getDocListByTeacher(paramMap);

        for (Map<String, Object> map : doctorMapList) {
            Map<String,Object> perMap = studentBiz.getRegPer(0,String.valueOf( map.get("doctorFlow")),String.valueOf( map.get("resultFlow")),null);
            if(perMap!=null)
                map.put("per", String.valueOf(perMap.get(map.get("processFlow"))));
        }
        model.addAttribute("doctorMapList", doctorMapList);

        //获取访问路径前缀
        String uploadBaseUrl = appbiz.getCfgByCode("upload_base_url");
        model.addAttribute("uploadBaseUrl", uploadBaseUrl);

        //数据数量
        model.addAttribute("dataCount", PageHelper.total);

        return "res/gzdh/teacher/doctorList";
    }

    @RequestMapping(value = {"/funcList"}, method = {RequestMethod.GET})
    public String funcList(String userFlow, String doctorFlow, HttpServletRequest request, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3030201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/teacher/funcList";
        }
        if (StringUtil.isEmpty(doctorFlow)) {
            model.addAttribute("resultId", "3030202");
            model.addAttribute("resultType", "学员标识符为空");
            return "res/gzdh/teacher/funcList";
        }
        return "res/gzdh/teacher/funcList";
    }

    @RequestMapping(value = {"/index"}, method = {RequestMethod.GET})
    public String index(String userFlow, HttpServletRequest request, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3030201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/teacher/funcList";
        }
        SysUser user = appbiz.readSysUser(userFlow);
        model.addAttribute("userinfo", user);
        //包装筛选条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("teacherUserFlow", userFlow);
        paramMap.put("statusId", "Entering");//轮转中
        Integer enteringDocs = teacherBiz.getCountDocListByTeacher(paramMap);
        if (enteringDocs != null) {
            model.addAttribute("isCurrent", enteringDocs);
        }
        List<SysDept> depts=appbiz.getHeadDeptList(userFlow,user.getDeptFlow());
        model.addAttribute("depts", depts);
        paramMap = new HashMap<String, Object>();
        paramMap.put("teacherUserFlow", userFlow);
        paramMap.put("statusId", "Exited");//已出科
        Integer exitedDocs = teacherBiz.getCountDocListByTeacher(paramMap);
        if (exitedDocs != null) {
            model.addAttribute("isSch", exitedDocs);
        }
        List<SysUserRole> userRoles = appbiz.getSysUserRole(userFlow);
        if(userRoles!=null&&userRoles.size()>0) {
            //获取当前配置的老师角色
            String teacherRole = appbiz.getCfgCode("res_teacher_role_flow");
            //获取当前配置的科主任角色
            String headRole = appbiz.getCfgCode("res_head_role_flow");
            //获取当前配置的教学秘书角色
            String secretaryRole = appbiz.getCfgCode("res_secretary_role_flow");
            List<Map<String,String>> roles=new ArrayList<>();
            for (SysUserRole sur : userRoles) {
                Map<String,String> map=new HashMap<>();
                String ur = sur.getRoleFlow();
                if (headRole.equals(ur)) {
                    map.put("roleId", "Head");
                    map.put("roleName", "教学主任");
                    roles.add(map);
                }
                if (secretaryRole.equals(ur)) {
                    map.put("roleId", "Secretary");
                    map.put("roleName", "教学秘书");
                    roles.add(map);
                }
                if (teacherRole.equals(ur)) {
                    map.put("roleId", "Teacher");
                    map.put("roleName", "带教老师");
                    roles.add(map);
                }
            }
            model.addAttribute("roles",roles);
        }
        List<SysDict> dicts=appbiz.getDictListByDictId(DictTypeEnum.DoctorTrainingSpe.getId());
        model.addAttribute("dicts", dicts);

        List<Map<String,String>> infos = this.noticeBiz.searchInfoByOrgNotRead(user.getOrgFlow(), GlobalConstant.RES_NOTICE_TYPE5_ID, GlobalConstant.RES_NOTICE_SYS_ID, userFlow);
        if(infos!=null)
        {
            model.addAttribute("hasNotReadInfo",infos.size());
        }else{
            model.addAttribute("hasNotReadInfo",0);
        }
        return "res/gzdh/teacher/index";
    }

    /**
     * 学员列表
     *
     * @param userFlow
     * @param studentName
     * @param pageIndex
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = {"/userList"}, method = {RequestMethod.POST})
    public String userList(String userFlow, String studentName, String speId, String typeId, String sessionNumber, Integer pageIndex, Integer pageSize, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/teacher/userList";
        }

        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/gzdh/teacher/userList";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/gzdh/teacher/userList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/gzdh/teacher/userList";
        }
        List<Map<String, String>> resDoctorSchProcess = null;
        Map<String, Object> schArrangeResultMap = new HashMap<String, Object>();
        schArrangeResultMap.put("teacherUserFlow", userFlow);
        schArrangeResultMap.put("userName", studentName);
        schArrangeResultMap.put("speId", speId);
        schArrangeResultMap.put("sessionNumber", sessionNumber);
        schArrangeResultMap.put("typeId", typeId);
//		后台配置是否校验权限时间
        String isPermitOpen = appbiz.getCfgByCode("res_permit_open_doc");
        schArrangeResultMap.put("isPermitOpen",isPermitOpen);
        PageHelper.startPage(pageIndex, pageSize);
        resDoctorSchProcess = teacherBiz.teacherDocUserList(schArrangeResultMap);
        //获取访问路径前缀
        String uploadBaseUrl = appbiz.getCfgByCode("upload_base_url");
        model.addAttribute("uploadBaseUrl", uploadBaseUrl);
        model.addAttribute("list", resDoctorSchProcess);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/gzdh/teacher/userList";
    }

    /**
     * 学员列表
     *
     * @param userFlow
     * @param studentName
     * @param pageIndex
     * @param pageSize
     * @param model
     * @param biaoJi
     * @return
     */
    @RequestMapping(value = {"/studentList"}, method = {RequestMethod.POST})
    public String studentList(String userFlow, String doctorFlow, String studentName, String speId, String sessionNumber, String statusId, String dataType, Integer pageIndex, Integer pageSize, Model model, String biaoJi) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/teacher/studentList";
        }

        if (StringUtil.isBlank(dataType)) {
            if (StringUtil.isBlank(doctorFlow)) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "学员流水号为空");
                return "res/gzdh/teacher/studentList";
            }
        }
        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/gzdh/teacher/studentList";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/gzdh/teacher/studentList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/gzdh/teacher/studentList";
        }

        List<Map<String, String>> resDoctorSchProcess = null;
        Map<String, Object> schArrangeResultMap = new HashMap<String, Object>();
        schArrangeResultMap.put("teacherUserFlow", userFlow);
        schArrangeResultMap.put("userName", studentName);
        schArrangeResultMap.put("speId", speId);
        schArrangeResultMap.put("sessionNumber", sessionNumber);
        schArrangeResultMap.put("statusId", statusId);
        schArrangeResultMap.put("biaoJi", biaoJi);//如果为Y就是查询带审核的学员列表
//		后台配置是否校验权限时间
        String isPermitOpen = appbiz.getCfgByCode("res_permit_open_doc");
        schArrangeResultMap.put("isPermitOpen",isPermitOpen);
        //湖北现在已经需要填写的数据类型
        List<String> typeId = new ArrayList<>();
        List<GeneralEnum> enumList = new ArrayList<>();
        if (StringUtil.isBlank(dataType) || dataType.equals("fiveData")) {
            for (ResRecTypeEnum e : ResRecTypeEnum.values()) {
                String cfgValue = appbiz.getCfgByCode("res_registry_type_" + e.getId());
                if (StringUtil.isNotBlank(cfgValue) && cfgValue.equals(GlobalConstant.FLAG_Y)) {
                    if (StringUtil.isNotBlank(dataType) && dataType.equals("fiveData")) {
                        typeId.add(e.getId());
                    }
                    enumList.add(e);
                }
            }
            if (StringUtil.isBlank(dataType)) {
                schArrangeResultMap.put("doctorFlow", doctorFlow);
                enumList.add(ResRecTypeEnum.DOPS);
                enumList.add(ResRecTypeEnum.Mini_CEX);
                enumList.add(ResRecTypeEnum.AfterSummary);
                enumList.add(ResRecTypeEnum.AfterEvaluation);
            }
            model.addAttribute("enumList", enumList);
            schArrangeResultMap.put("recTypes", typeId);
            PageHelper.startPage(pageIndex, pageSize);
            resDoctorSchProcess = teacherBiz.schDoctorSchProcessQuery(schArrangeResultMap);
        } else {
            String dataTypes[] = dataType.split(",");
            for (String t : dataTypes) {
                String recTypeId = t;
                if (StringUtil.isNotBlank(recTypeId)) {
                    typeId.add(recTypeId);
                    for (ResRecTypeEnum e : ResRecTypeEnum.values()) {
                        if (e.getId().equals(recTypeId)) {
                            enumList.add(e);
                        }
                    }

                }
            }
            model.addAttribute("enumList", enumList);
            schArrangeResultMap.put("recTypes", typeId);
            PageHelper.startPage(pageIndex, pageSize);
            resDoctorSchProcess = teacherBiz.schDoctorSchProcessInfoQuery(schArrangeResultMap);
        }
        Map<String, Object> resRecMap = new HashMap<String, Object>();
        for (Map<String, String> map : resDoctorSchProcess) {
            Map<String,Object> perMap = studentBiz.getRegPer(0, map.get("doctorFlow"), map.get("resultFlow"),null);
            if(perMap!=null)
                map.put("per", String.valueOf(perMap.get(map.get("resultFlow"))));
//            if("fiveData".equals(dataType)) {
//                List<ResRec> resRecList = teacherBiz.getDocRecs(map.get("processFlow"), map.get("doctorFlow"), null);
//                for (ResRec resRec : resRecList) {
//                    String k = map.get("processFlow") + resRec.getRecTypeId();
//                    String k2 = k + "notAudit";
//                    Integer i = resRecCountMap.get(k);
//                    if (i == null) {
//                        i = 0;
//                    }
//                    i++;
//                    resRecCountMap.put(k, i);
//                    if (!StringUtil.isNotBlank(resRec.getAuditStatusId())) {
//                        Integer j = resRecCountMap.get(k2);
//                        if (j == null) {
//                            j = 0;
//                        }
//                        j++;
//                        resRecCountMap.put(k2, j);
//                    }
//                }
//            }
            List<String> recTypeIds=new ArrayList<>();
            recTypeIds.add(ResRecTypeEnum.DOPS.getId());
            recTypeIds.add(ResRecTypeEnum.Mini_CEX.getId());
            recTypeIds.add(ResRecTypeEnum.AfterSummary.getId());
            recTypeIds.add(ResRecTypeEnum.AfterEvaluation.getId());
            List<ResSchProcessExpress> expressList = expressBiz.getDocexpressList(map.get("processFlow"), recTypeIds);
            for(ResSchProcessExpress express:expressList)
            {
                String key =express.getProcessFlow()+express.getRecTypeId();
                resRecMap.put(key, express);
            }
        }
        model.addAttribute("resRecMap", resRecMap);
        //获取访问路径前缀
        String uploadBaseUrl = appbiz.getCfgByCode("upload_base_url");
        model.addAttribute("uploadBaseUrl", uploadBaseUrl);
        model.addAttribute("list", resDoctorSchProcess);
        model.addAttribute("dataCount", PageHelper.total);
        model.addAttribute("biaoJi", biaoJi);
        return "res/gzdh/teacher/studentList";
    }

    /**
     * 数据待审核和出科考核待审核
     *
     * @param userFlow
     * @param docFlow
     * @param dataType
     * @param processFlow
     * @param resultFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/dataNoAudit", method = RequestMethod.GET)
    public String dataNoAudit(String userFlow, String docFlow, String dataType, String processFlow, String resultFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/teacher/dataNoAudit";
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "学生标识符为空");
            return "res/gzdh/teacher/dataNoAudit";
        }
        if (StringUtil.isBlank(processFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/gzdh/teacher/dataNoAudit";
        }
        if (StringUtil.isBlank(resultFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "排班标识符为空");
            return "res/gzdh/teacher/dataNoAudit";
        }
        if (StringUtil.isBlank(dataType)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型为空");
            return "res/gzdh/teacher/dataNoAudit";
        }
        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/gzdh/teacher/dataNoAudit";
        }
        //湖北现在已经需要填写的数据类型
        List<String> typeId = new ArrayList<>();
        List<GeneralEnum> enumList = new ArrayList<>();
        if (dataType.equals("fiveData")) {
            for (ResRecTypeEnum e : ResRecTypeEnum.values()) {
                String cfgValue = appbiz.getCfgByCode("res_registry_type_" + e.getId());
                if (StringUtil.isNotBlank(cfgValue) && cfgValue.equals(GlobalConstant.FLAG_Y)) {
                    if (dataType.equals("fiveData")) {
                        typeId.add(e.getId());
                    }
                    enumList.add(e);
                }
            }
            model.addAttribute("enumList", enumList);
        } else if (dataType.equals("after")) {
            enumList.add(ResRecTypeEnum.AfterSummary);
            enumList.add(ResRecTypeEnum.AfterEvaluation);
            typeId.add(ResRecTypeEnum.AfterSummary.getId());
            typeId.add(ResRecTypeEnum.AfterEvaluation.getId());
            model.addAttribute("enumList", enumList);
        } else {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型不正确");
            return "res/gzdh/teacher/dataNoAudit";
        }
        Map<String, Object> param = new HashMap<>();
        param.put("teaFlow", userFlow);
        param.put("docFlow", docFlow);
        param.put("processFlow", processFlow);
        param.put("recTypes", typeId);
        param.put("resultFlow", resultFlow);
        param.put("dataType", dataType);
        List<Map<String, Object>> list = teacherBiz.findDataNoAudit(param);
        for (Map<String, Object> map : list) {
            map.put("recTypeName", ResRecTypeEnum.getNameById((String) map.get("recTypeId")));
        }
        model.addAttribute("list", list);
        return "res/gzdh/teacher/dataNoAudit";
    }

    /**
     * 数据列表
     *
     * @param
     * @param docFlow
     * @param processFlow
     * @param recType
     * @return
     */
    @RequestMapping(value = "/recDataList", method = RequestMethod.GET)
    public String recDataList(String userFlow, String docFlow, String processFlow, String recType, String biaoJi, String resultFlow, Integer pageIndex, Integer pageSize, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/teacher/recDataList";
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "学生标识符为空");
            return "res/gzdh/teacher/recDataList";
        }
        if (StringUtil.isBlank(resultFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转计划标识符为空");
            return "res/gzdh/teacher/recDataList";
        }
        if (StringUtil.isBlank(processFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/gzdh/teacher/recDataList";
        }
        if (StringUtil.isBlank(recType)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型为空");
            return "res/gzdh/teacher/recDataList";
        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/gzdh/teacher/recDataList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/gzdh/teacher/recDataList";
        }

        Map<String, Object> processPerMap = studentBiz.getRegPer(0, docFlow, resultFlow);
        model.addAttribute("processPerMap", processPerMap);
        PageHelper.startPage(pageIndex, pageSize);
        List<ResRec> recList = teacherBiz.searchRecByProcessAndRecType(processFlow, docFlow, recType, biaoJi);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        for (ResRec rec : recList) {
            String recContent = rec.getRecContent();
            Map<String, String> formDataMap = appbiz.parseRecContent(recContent);
            formDataMap.put("dataFlow", rec.getRecFlow());
            formDataMap.put("auditId", rec.getAuditStatusId());
            formDataMap.put("operTime", DateUtil.transDate(rec.getOperTime()));
            dataList.add(formDataMap);
            System.err.println(JSON.toJSON(formDataMap));
        }
        model.addAttribute("dataList", dataList);
        model.addAttribute("dataCount", PageHelper.total);
        List<ResRec> noAuditList = teacherBiz.searchRecByProcessAndRecType(processFlow, docFlow, recType, "Y");
        int count = 0;
        if (noAuditList != null) {
            count = noAuditList.size();
        }
        model.addAttribute("notAuditNum", count);
        return "res/gzdh/teacher/recDataList";
    }

    /**
     * 单条数据详情
     *
     * @param
     * @param recType
     * @return
     */
    @RequestMapping(value = "/resRecDeatil", method = RequestMethod.GET)
    public String resRecDeatil(String userFlow, String recFlow, String resultFlow, String cataFlow, String recType, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/teacher/resRecDetail";
        }
        if (StringUtil.isBlank(recFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/gzdh/teacher/resRecDetail";
        }
        if (StringUtil.isBlank(recType)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型为空");
            return "res/gzdh/teacher/resRecDetail";
        }
        if (StringUtil.isBlank(resultFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "排班流水号为空");
            return "res/gzdh/teacher/resRecDetail";
        }
        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/gzdh/teacher/resRecDetail";
        }
        ResRec rec = teacherBiz.readResRec(recFlow);
        String recContent = rec.getRecContent();
        Map<String, String> formDataMap = appbiz.parseRecContent(recContent);
        formDataMap.put("auditId", rec.getAuditStatusId());
        model.addAttribute("resultData", formDataMap);
        model.addAttribute("isOther", GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(cataFlow));
        return "res/gzdh/teacher/resRecDetail";
    }

    /**
     * 一键审核
     *
     * @param
     * @param docFlow
     * @param processFlow
     * @param recType
     * @return
     */
    @RequestMapping(value = "/batchAudit", method = RequestMethod.POST)
    public String batchAudit(String userFlow, String docFlow, String processFlow, String recType, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "审核成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/success";
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "学生标识符为空");
            return "res/gzdh/success";
        }
        if (StringUtil.isBlank(processFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/gzdh/success";
        }
        //rec类型转换一下
        if (StringUtil.isBlank(recType)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型为空");
            return "res/gzdh/success";
        }
        List<ResRec> recList = teacherBiz.searchRecByProcessAndRecType(processFlow, docFlow, recType, "Y");
        if (null != recList && recList.size() > 0) {
            int i = 0;
            for (ResRec rec : recList) {
                i += teacherBiz.auditRecDate(userFlow, rec.getRecFlow(), "Y");
            }
            if (i == 0) {
                model.addAttribute("resultId", "3010103");
                model.addAttribute("resultType", "批量审核失败");
                return "res/gzdh/success";
            }
        } else {
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "无审核数据");
            return "res/gzdh/success";
        }
        return "res/gzdh/success";
    }

    /**
     * 单条数据审核
     *
     * @param
     */
    @RequestMapping(value = "/oneAudit", method = RequestMethod.POST)
    public String oneAudit(String userFlow, String recFlow, String statusId, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "审核成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/success";
        }
        if (StringUtil.isBlank(recFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/gzdh/success";
        }
        if (StringUtil.isBlank(statusId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "审核状态不能为空");
            return "res/gzdh/success";
        }
        if (!statusId.equals("Y") && !statusId.equals("N")) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "审核状态只能是通过或不通过");
            return "res/gzdh/success";
        }
        ResRec re = teacherBiz.readResRec(recFlow);
        if (StringUtil.isNotBlank(re.getAuditStatusId())) {
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "数据已审核");
            return "res/gzdh/success";
        }
        int i = teacherBiz.auditRecDate(userFlow, recFlow, statusId);
        if (i == 0) {
            model.addAttribute("resultId", "310101");
            model.addAttribute("resultType", "数据已审核");
            return "res/gzdh/success";
        }
        return "res/gzdh/success";
    }

    private void _inputList(String userFlow, String deptFlow, String dataType, String cataFlow, Model model) {
        switch (dataType) {
            case "CaseRegistry":
                break;
            case "DiseaseRegistry":
                List<Map<String, Object>> diseaseDiagNameList = appbiz.commReqOptionNameList(userFlow, deptFlow, "DiseaseRegistry", cataFlow);
                model.addAttribute("diseaseDiagNameList", diseaseDiagNameList);
                break;
            case "SkillRegistry":
                List<Map<String, Object>> skillOperNameList = appbiz.commReqOptionNameList(userFlow, deptFlow, "SkillRegistry", cataFlow);
                model.addAttribute("skillOperNameList", skillOperNameList);
                break;
            case "OperationRegistry":
                List<Map<String, Object>> operationOperNameList = appbiz.commReqOptionNameList(userFlow, deptFlow, "OperationRegistry", cataFlow);
                model.addAttribute("operationOperNameList", operationOperNameList);
                break;
            case "CampaignNoItemRegistry":
                break;
            case "AfterSummary":
                break;
            default:
                break;
        }
    }

    private String getRecTypeId(String recType) {
        String recTypeId = "";
        switch (recType) {
            case "mr":
                recTypeId = ResRecTypeEnum.CaseRegistry.getId();
                break;
            case "disease":
                recTypeId = ResRecTypeEnum.DiseaseRegistry.getId();
                break;
            case "skill":
                recTypeId = ResRecTypeEnum.SkillRegistry.getId();
                break;
            case "operation":
                recTypeId = ResRecTypeEnum.OperationRegistry.getId();
                break;
            case "activity":
                recTypeId = ResRecTypeEnum.CampaignNoItemRegistry.getId();
                break;
            case "afterSum":
                recTypeId = ResRecTypeEnum.AfterSummary.getId();
                break;
            case "dops":
                recTypeId = ResRecTypeEnum.DOPS.getId();
                break;
            case "miniCex":
                recTypeId = ResRecTypeEnum.Mini_CEX.getId();
                break;
            case "afterEva":
                recTypeId = ResRecTypeEnum.AfterEvaluation.getId();
                break;
            default:
                break;
        }
        return recTypeId;
    }

    private static final String COMMON_PATH = "student";

    //11
    @RequestMapping(value = {"/viewData"}, method = {RequestMethod.GET})
    public String viewData(
            String userFlow,
            String roleId,
            String deptFlow,
            String doctorFlow,
            String funcTypeId,
            String funcFlow,
            String dataFlow,
            String cataFlow,
            HttpServletRequest request,
            Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "3010601");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/viewData";
        }

        if (StringUtil.isEmpty(roleId)) {
            model.addAttribute("resultId", "3010602");
            model.addAttribute("resultType", "用户角色为空");
            return "res/gzdh/viewData";
        }

        if (StringUtil.isEmpty(funcTypeId)) {
            model.addAttribute("resultId", "3010603");
            model.addAttribute("resultType", "功能类型为空");
            return "res/gzdh/viewData";
        }

        if (StringUtil.isEmpty(funcFlow)) {
            model.addAttribute("resultId", "3010604");
            model.addAttribute("resultType", "功能标识为空");
            return "res/gzdh/viewData";
        }

        if ("Student".equals(roleId) && StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "3010605");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/gzdh/viewData";
        }

        if ("Teacher".equals(roleId) && StringUtil.isEmpty(doctorFlow)) {
            model.addAttribute("resultId", "3010606");
            model.addAttribute("resultType", "学员标识符为空");
            return "res/gzdh/viewData";
        }

        //科室流水号
        String resultFlow = deptFlow;

        //判断角色
        boolean sSwitch = "Student".equals(roleId);
        boolean tSwitch = "Teacher".equals(roleId);

        String path = COMMON_PATH;
        //定义dataList路径
        if (sSwitch) {
            path = COMMON_PATH;
        } else if (tSwitch) {
            path = "teacher";
            //所有1n模式的数据取学员的viewData
            if ("dataInput1N".equals(funcTypeId)) {
                path = COMMON_PATH;
            }

            resultFlow = doctorFlow;
        }
        model.addAttribute("path", path);

        //要求数据
        SchRotationDeptReq req = null;

        //当前轮转计划信息
        SchArrangeResult result = studentBiz.searcheDocResult(null, resultFlow);
        if (result != null) {
            model.addAttribute("result", result);

            //读取要求
            if (StringUtil.isNotBlank(cataFlow)) {
                String standardDeptId = result.getStandardDeptId();
                String standardGroupFlow = result.getStandardGroupFlow();
                SchRotationDept rotationDept = appbiz.getRotationDeptByResult(standardGroupFlow, standardDeptId);
                if (rotationDept != null) {
                    String relRecordFlow = rotationDept.getRecordFlow();
                    req = appbiz.readReq(null, relRecordFlow, cataFlow);
                }
            }
        }

        //获取当前这条登记信息
        ResRec rec = null;
        if (StringUtil.isNotBlank(dataFlow)) {
            rec = appbiz.getRecByRecFlow(dataFlow);
            //如果是11模式根据类型和科室查询
        } else if ("dataInput11".equals(funcTypeId)) {
            String processFlow = "";
            ResDoctorSchProcess process = studentBiz.getProcessByResult(resultFlow);
            model.addAttribute("currRegProcess",process);
            if (process != null) {
                //登记表单的科室
                processFlow = process.getProcessFlow();
            }
            rec = appbiz.getRecByRecType(processFlow, funcFlow);
        }

        String itemId = cataFlow;
        if (rec != null) {
            model.addAttribute("rec", rec);
            itemId = rec.getItemId();

            //为子项选项赋值
            if (req == null) {
                req = new SchRotationDeptReq();
                req.setItemId(rec.getItemId());
                req.setItemName(rec.getItemName());
            }
        }

        //是否是其他
        model.addAttribute("isOther", GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(itemId));

        //要求数据
        model.addAttribute("req", req);

        //是否是评分表单
        String assessType = "";
        boolean isGrade = false;
        if (ResRecTypeEnum.TeacherGrade.getId().equals(funcFlow)) {
            assessType = ResAssessTypeEnum.TeacherAssess.getId();
            isGrade = true;
        } else if (ResRecTypeEnum.DeptGrade.getId().equals(funcFlow)) {
            assessType = ResAssessTypeEnum.DeptAssess.getId();
            isGrade = true;
        }
        if (isGrade && StringUtil.isNotBlank(assessType)) {
            //读取评分配置
            ResAssessCfg assessCfg = appbiz.getGradeTemplate(assessType);
            model.addAttribute("assessCfg", assessCfg);

            if (assessCfg != null) {
                String content = assessCfg.getCfgBigValue();
                //解析评分xml
                List<Map<String, Object>> assessMaps = appbiz.parseAssessCfg(content);
                model.addAttribute("assessMaps", assessMaps);
            }
        }
        String version="2.0";
        if (rec != null) {
            String content = rec.getRecContent();
            //解析登记信息的xml
            Object formDataMap = null;
            if (!isGrade) {
                formDataMap = appbiz.parseRecContent(content);
            } else {
                formDataMap = appbiz.parseDocGradeXml(content);
            }
            model.addAttribute("formDataMap", formDataMap);

            //如果读取到数据就使用数据本身的funcFlow
            funcFlow = rec.getRecTypeId();
            version = rec.getRecVersion();

        }
        if(ResRecTypeEnum.AfterEvaluation.getId().equals(funcFlow))
        {
            funcFlow=funcFlow+"_"+version;
        }
        model.addAttribute("funcFlow", funcFlow);

        //老师和学生的控件开关
        model.addAttribute("sSwitch", sSwitch);
        model.addAttribute("tSwitch", tSwitch);
        //不可操作开关
        boolean isAudit = rec != null && StringUtil.isNotBlank(rec.getAuditStatusId());
        model.addAttribute("cannotOperSwitch", (tSwitch || isAudit));
        model.addAttribute("isAudit", isAudit);

        return "res/gzdh/viewData";
    }

    /**
     * 出科小结,迷你临床演练评估量化表,临床操作技能评估量化表,出科考核表
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = {"/viewFourTable"}, method = {RequestMethod.GET})
    public String viewFourTable(
            String userFlow,
            String resultFlow,
            String recType,
            String recFlow,
            HttpServletRequest request,
            Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "3010601");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/viewData";
        }

        if (StringUtil.isEmpty(recType)) {
            model.addAttribute("resultId", "3010604");
            model.addAttribute("resultType", "数据类型标识为空");
            return "res/gzdh/viewData";
        }
        if (StringUtil.isEmpty(resultFlow)) {
            model.addAttribute("resultId", "3010606");
            model.addAttribute("resultType", "计划标识符为空");
            return "res/gzdh/viewData";
        }
        String path = "teacher";
        model.addAttribute("path", path);
        //当前轮转计划信息
        SchArrangeResult result = studentBiz.searcheDocResult(null, resultFlow);
        model.addAttribute("result", result);
        SysUser tea = appbiz.readSysUser(userFlow);
        model.addAttribute("tea", tea);

        ResDoctor doctor=appbiz.readResDoctor(result.getDoctorFlow());
        model.addAttribute("doctor", doctor);
        //获取当前这条登记信息
        ResSchProcessExpress rec = null;
        if (StringUtil.isNotBlank(recFlow)) {
            rec = expressBiz.getExpressByRecFlow(recFlow);
        } else {
            String processFlow = "";
            ResDoctorSchProcess process = studentBiz.getProcessByResult(resultFlow);
            if (process != null) {
                //登记表单的科室
                processFlow = process.getProcessFlow();
            }
            rec = expressBiz.getExpressByRecType(processFlow, recType);
        }
        String version="1.0";
        if (rec != null) {
            String content = rec.getRecContent();
            //解析登记信息的xml
            Object formDataMap = null;
            formDataMap = appbiz.parseRecContent(content);
            model.addAttribute("formDataMap", formDataMap);
            //如果读取到数据就使用数据本身的funcFlow
            recType = rec.getRecTypeId();
            version=rec.getRecVersion();
        }
        if (recType.equals(ResRecTypeEnum.AfterEvaluation.getId())) {
            ResDoctorSchProcess process = appbiz.readSchProcessByResultFlow(resultFlow);
            if (process != null) {
                String res_after_test_switch = appbiz.getCfgByCode("res_after_test_switch");
                String res_after_url_cfg = appbiz.getCfgByCode("res_after_url_cfg");
//				String p004=appbiz.getCfgByCode("jswjw_"+process.getOrgFlow()+"_P004");
//				String docTestSwitch=appbiz.getCfgByCode("doc_test_switch_"+process.getOrgFlow()+"_"+process.getUserFlow());
                //学员开通出科考试权限
                Map<String, String> paramMap = new HashMap();
                String cfgCode = "res_doctor_ckks_" + process.getUserFlow();
                paramMap.put("cfgCode", cfgCode);
                String isCkksFlag = resPowerCfgBiz.getResPowerCfg(paramMap);
                boolean testTypeFlag = false;
                if (GlobalConstant.FLAG_Y.equals(res_after_test_switch) && StringUtil.isNotBlank(res_after_url_cfg)) {
                    testTypeFlag = true;
                }
                model.addAttribute("testTypeFlag", testTypeFlag);
                ResScore score = appbiz.getScoreByProcess(process.getProcessFlow());
                model.addAttribute("outScore", score);
                model.addAttribute("doctorSchProcess", process);
                model.addAttribute("currRegProcess", process);

                //百分比算法
                Map<String, Object> perMap = studentBiz.getRegPer(0, process.getUserFlow(), resultFlow, null, null, true);
                model.addAttribute("perMap", perMap);
                //logger.debug("===================" + JSON.toJSON(perMap));
                //参加活动的信息
                Map<String, Object> capData = new HashMap<>();
                List<Map<String, Object>> capDatas = teacherBiz.getDocCapDatas(RegistryTypeEnum.CampaignNoItemRegistry.getId(), process.getProcessFlow());
                for (Map<String, Object> m : capDatas) {
                    capData.put((String) m.get("INFO_KEY"), m);
                }
                model.addAttribute("capData", capData);
            }
        }
        model.addAttribute("funcFlow", recType);
        //不可操作开关
        boolean isAudit = rec != null && StringUtil.isNotBlank(rec.getAuditStatusId());
        model.addAttribute("cannotOperSwitch", (true || isAudit));
        model.addAttribute("isAudit", isAudit);

        return "res/gzdh/viewData";
    }

    @RequestMapping(value = {"/saveData"}, method = {RequestMethod.POST})
    public String saveData(String userFlow,
                           String resultFlow,
                           String recTypeId,
                           String dataFlow,
                           HttpServletRequest request,
                           Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "3010901");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/saveData";
        }

        if (StringUtil.isEmpty(resultFlow)) {
            model.addAttribute("resultId", "3010904");
            model.addAttribute("resultType", "排班标识符为空");
            return "res/gzdh/saveData";
        }
        if (StringUtil.isEmpty(recTypeId)) {
            model.addAttribute("resultId", "3010906");
            model.addAttribute("resultType", "功能标识为空");
            return "res/gzdh/saveData";
        }
        SysUser user = appbiz.readSysUser(userFlow);
        model.addAttribute("user", user);

        //获取当前操作人
        String operUserFlow = "";
        //如果是11查出dataFlow
        ResSchProcessExpress rec = null;
        if (StringUtil.isEmpty(dataFlow)) {
            String processFlow = "";
            ResDoctorSchProcess process = studentBiz.getProcessByResult(resultFlow);
            if (process != null) {
                //登记表单的科室
                processFlow = process.getProcessFlow();

                operUserFlow = process.getUserFlow();
            }
            rec = expressBiz.getExpressByRecType(processFlow, recTypeId);
            if (rec != null) {
                dataFlow = rec.getRecFlow();
            }
        } else {
            rec = expressBiz.getExpressByRecFlow(dataFlow);
        }
        String version="";
        if (rec != null) {
            recTypeId = rec.getRecTypeId();
            version=rec.getRecVersion();
        }
        request.setAttribute("recTypeId",recTypeId);
        dataFlow = expressBiz.editZseyExpress(dataFlow, operUserFlow, resultFlow, recTypeId, GlobalConstant.RES_DGDH_DEFAULT_FORM, request,GlobalConstant.RES_ROLE_SCOPE_HEAD,"",version);

        //审核数据
        expressBiz.auditDate(userFlow, dataFlow);

        return "res/gzdh/saveData";
    }

    @RequestMapping(value="/docEvalFuncList",method=RequestMethod.POST)
    public String docEvalFuncList(String userFlow,String processFlow,String resultFlow,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/teacher/evalFuncList";
        }
        if(StringUtil.isBlank(resultFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "计划标识符为空");
            return "res/gzdh/teacher/evalFuncList";
        }
        if(StringUtil.isBlank(processFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/gzdh/teacher/evalFuncList";
        }
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if(userinfo==null){
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/gzdh/teacher/evalFuncList";
        }

        List<Map<String,Object>> list=new ArrayList<>();

        //带教
        List<DeptTeacherGradeInfo> infos=gradeBiz.getRecListByRecType(processFlow, com.pinde.res.enums.stdp.ResAssessTypeEnum.TeacherDoctorAssess.getId());
        Map<String,Object>data=new HashMap<>();
        if(infos!=null&&infos.size()>0)
        {
            data.put("recFlow","");
            data.put("typeId", com.pinde.res.enums.stdp.ResAssessTypeEnum.TeacherDoctorAssess.getId());
            data.put("resultFlow",resultFlow);
            data.put("openId","Y");
            data.put("statusId","Audit");
            data.put("statusName","带教已审核");
            double totalScore=0;
            for(DeptTeacherGradeInfo gradeInfo:infos)
            {
                Map<String,Object> gradeMap = gradeBiz.parseGradeInfoXml(gradeInfo.getRecContent());
                if(gradeMap!=null&&StringUtil.isNotBlank((String) gradeMap.get("totalScore")))
                {
                    totalScore+=Double.valueOf((String) gradeMap.get("totalScore"));
                }
            }
            totalScore=totalScore/infos.size();
            if(totalScore!=0)
            {
                data.put("totalScore", new BigDecimal(totalScore).setScale(1,BigDecimal.ROUND_HALF_UP));
            }else {
                data.put("totalScore", "暂无");
            }
        }else{
            data.put("recFlow","");
            data.put("typeId", com.pinde.res.enums.stdp.ResAssessTypeEnum.TeacherDoctorAssess.getId());
            data.put("resultFlow",resultFlow);
            data.put("openId","Y");
            data.put("statusId","NotAudit");
            data.put("statusName","带教未审核");
            data.put("totalScore","暂无");
        }
        data.put("evalName", "带教老师");
        list.add(data);
        model.addAttribute("list",list);
        return "res/gzdh/teacher/evalFuncList";
    }

    @RequestMapping(value="/evalInfoDetail",method=RequestMethod.POST)
    public String evalInfoDetail(String userFlow,String processFlow,String typeId,String recFlow,Model model) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/teacher/evalInfoDetail";
        }
        if(StringUtil.isBlank(processFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/gzdh/teacher/evalInfoDetail";
        }
        if(StringUtil.isBlank(typeId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "评价类型为空");
            return "res/gzdh/teacher/evalInfoDetail";
        }
        if(!ResAssessTypeEnum.TeacherDoctorAssess.getId().equals(typeId))
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "评价类型错误，只能是TeacherDoctorAssess");
            return "res/gzdh/teacher/evalInfoDetail";
        }
        DeptTeacherGradeInfo info=gradeBiz.readByFlow(recFlow);
        if(info==null&& ResAssessTypeEnum.TeacherDoctorAssess.getId().equals(typeId))
        {
            info=gradeBiz.getRecByRecType(processFlow,typeId);
        }
        boolean isAudit=false;
        if(info!=null){
            isAudit=true;
        }
        model.addAttribute("isAudit",isAudit);
        model.addAttribute("typeId",typeId);
        //读取评分配置
        ResAssessCfg assessCfg = gradeBiz.getGradeTemplate(typeId);
        model.addAttribute("assessCfg",assessCfg);
        if(assessCfg!=null){
            String content = assessCfg.getCfgBigValue();

            List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
            Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
            String titleXpath = "//title";
            List<Element> titleElementList = dom.selectNodes(titleXpath);
            if (titleElementList != null && !titleElementList.isEmpty()) {
                for (Element te : titleElementList) {
                    ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
                    titleForm.setId(te.attributeValue("id"));
                    titleForm.setName(te.attributeValue("name"));
                    titleForm.setEvalTypeId(te.attributeValue("evalTypeId")== null ? "" : te.attributeValue("evalTypeId"));
                    titleForm.setEvalTypeName(te.attributeValue("evalTypeName")== null ? "" : te.attributeValue("evalTypeName"));
                    List<Element> itemElementList = te.elements("item");
                    List<ResAssessCfgItemForm> itemFormList = null;
                    int score=0;
                    if (itemElementList != null && !itemElementList.isEmpty()) {
                        itemFormList = new ArrayList<ResAssessCfgItemForm>();
                        for (Element ie : itemElementList) {
                            ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
                            itemForm.setId(ie.attributeValue("id"));
                            itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                            itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
                            if(ResAssessScoreTypeEnum.Nine.getId().equals(assessCfg.getAssessTypeId()))
                            {
                                score +=9;
                            }else {
                                if (StringUtil.isNotBlank(itemForm.getScore())) {
                                    score += Integer.valueOf(itemForm.getScore());
                                }
                            }
                            itemFormList.add(itemForm);
                        }
                    }
                    titleForm.setItemList(itemFormList);
                    titleForm.setScore(String.valueOf(score));
                    titleFormList.add(titleForm);
                }
                model.addAttribute("titleFormList", titleFormList);
            }
            //解析评分xml
            List<Map<String,Object>> assessMaps = gradeBiz.parseAssessCfg(content);
            model.addAttribute("assessMaps",assessMaps);
        }
        if(info!=null&&StringUtil.isNotBlank(info.getRecContent()))
        {
            //解析登记信息的xml
            Map<String,Object> formDataMap = gradeBiz.parseGradeInfoXml(info.getRecContent());
            model.addAttribute("gradeMap",formDataMap);
        }
        SysUser user=appbiz.readSysUser(userFlow);
        model.addAttribute("user",user);

        return "res/gzdh/teacher/evalInfoDetail";
    }

    @RequestMapping(value={"/saveEvalData"},method={RequestMethod.POST})
    public String saveEvalData(String userFlow,
                           String roleId,
                           String processFlow,
                           String resultFlow,
                           String typeId,
                           String recFlow,
                           HttpServletRequest request,
                           Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "3010901");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/saveData";
        }

        if(StringUtil.isEmpty(roleId)){
            model.addAttribute("resultId", "3010902");
            model.addAttribute("resultType", "用户角色为空");
            return "res/gzdh/saveData";
        }

        if(StringUtil.isBlank(processFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/gzdh/saveData";
        }
        if(StringUtil.isBlank(resultFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转记录标识符为空");
            return "res/gzdh/saveData";
        }
        if(StringUtil.isBlank(typeId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "评价类型为空");
            return "res/gzdh/saveData";
        }
        if(!ResAssessTypeEnum.TeacherDoctorAssess.getId().equals(typeId))
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "评价类型错误，只能是TeacherDoctorAssess");
            return "res/gzdh/saveData";
        }
        DeptTeacherGradeInfo info=gradeBiz.readByFlow(recFlow);
        if(info==null&& ResAssessTypeEnum.TeacherDoctorAssess.getId().equals(typeId))
        {
            info=gradeBiz.getRecByRecType(processFlow,typeId);
        }
        if(info!=null)
        {
            recFlow=info.getRecFlow();
        }
        gradeBiz.editKmGradeInfo(recFlow, userFlow,resultFlow,processFlow, typeId, request,GlobalConstant.RES_DGDH_DEFAULT_FORM);

        return "res/gzdh/saveData";
    }
    @RequestMapping(value = {"/activityList"}, method = {RequestMethod.POST})
    public String activityList(String userFlow,Integer pageIndex,Integer pageSize,Model model,String roleId) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/teacher/activityList";
        }
        if(StringUtil.isBlank(roleId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/gzdh/teacher/activityList";
        }
        if(!roleId.equals("Teacher")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/gzdh/teacher/activityList";
        }

        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/gzdh/teacher/activityList";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/gzdh/teacher/activityList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/gzdh/teacher/activityList";
        }
        Map<String,String> param=new HashMap<>();
        param.put("roleFlag","teach");
        param.put("userFlow",userinfo.getUserFlow());
        model.addAttribute("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
        param.put("orgFlow", userinfo.getOrgFlow());
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String,Object>> list=activityBiz.findActivityList(param);
        Map<String,Object> resultMap=new HashMap<>();
        if(list!=null) {
            for (Map<String,Object> info:list )
            {
                List<Map<String,Object>>  results=activityBiz.readActivityResults((String) info.get("activityFlow"),"");
                resultMap.put((String) info.get("activityFlow"),results);
            }
        }
        //获取访问路径前缀
        String uploadBaseUrl = appbiz.getCfgCode("upload_base_url");
        model.addAttribute("uploadBaseUrl", uploadBaseUrl);
        model.addAttribute("list", list);
        model.addAttribute("resultMap", resultMap);
        model.addAttribute("dataCount", PageHelper.total);
        model.addAttribute("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
        String key="res_"+userinfo.getOrgFlow()+"_org_activity_code_type";
        String cfgv=appbiz.getResCfgCode(key);
        model.addAttribute("codeType", cfgv);
        return "res/gzdh/teacher/activityList";
    }
    @RequestMapping(value = {"/qrCode"}, method = {RequestMethod.POST})
    public String qrCode(String userFlow ,String activityFlow,Model model,String roleId) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/teacher/qrCode";
        }
        if(StringUtil.isBlank(roleId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/gzdh/teacher/qrCode";
        }
        if(StringUtil.isBlank(activityFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "活动标识符为空");
            return "res/gzdh/teacher/qrCode";
        }
        if(!roleId.equals("Teacher")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/gzdh/teacher/qrCode";
        }

        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/gzdh/teacher/qrCode";
        }
        TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
        if(info==null||!GlobalConstant.RECORD_STATUS_Y.equals(info.getRecordStatus()))
        {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "活动信息不存在");
            return "res/gzdh/teacher/qrCode";
        }
        String url = "func://funcFlow=activitySigin&activityFlow="+activityFlow;
        String url2 = "func://funcFlow=activityOutSigin&activityFlow="+activityFlow;
        model.addAttribute("url", url);
        model.addAttribute("url2", url2);
        return "res/gzdh/teacher/qrCode";
    }
    @RequestMapping(value = {"/delActivity"}, method = {RequestMethod.POST})
    public String delActivity(String userFlow,String activityFlow,Model model,String roleId) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/success";
        }
        if(StringUtil.isBlank(roleId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/gzdh/success";
        }
        if(StringUtil.isBlank(activityFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "活动标识符为空");
            return "res/gzdh/success";
        }
        if(!roleId.equals("Teacher")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/gzdh/success";
        }

        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/gzdh/success";
        }
        TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
        if(activity==null)
        {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "活动信息不存在");
            return "res/gzdh/success";
        }

        if(!GlobalConstant.RECORD_STATUS_Y.equals(activity.getRecordStatus()))
        {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "活动信息已被删除，请刷新列表！");
            return "res/gzdh/success";
        }

        List<Map<String,Object>>  results=activityBiz.readActivityResults(activityFlow,"");
        if(results!=null&&results.size()>0)
        {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "此活动已有学员扫码，无法删除！");
            return "res/gzdh/success";
        }
        activity.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        model.addAttribute("activity", activity);
        int c=activityBiz.saveActivityInfo(activity,userinfo);
        if(c==0)
        {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "删除失败！");
            return "res/gzdh/success";
        }
        return "res/gzdh/success";
    }
    @RequestMapping(value = {"/showActivity"}, method = {RequestMethod.POST})
    public String showActivity(String userFlow,String activityFlow,Model model,String roleId) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/teacher/showActivity";
        }
        if(StringUtil.isBlank(roleId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/gzdh/teacher/showActivity";
        }
        if(StringUtil.isBlank(activityFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "活动标识符为空");
            return "res/gzdh/teacher/showActivity";
        }
        if(!roleId.equals("Teacher")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/gzdh/teacher/showActivity";
        }
        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/gzdh/teacher/showActivity";
        }
        Map<String, Object> activity=activityBiz.readActivity(activityFlow);
        if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
        {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "活动信息不存在");
            return "res/gzdh/teacher/showActivity";
        }
        model.addAttribute("activity", activity);
        model.addAttribute("user",userinfo);
        List<SysDict> activityList=appbiz.getDictListByDictId("ActivityType");
        model.addAttribute("activityList",activityList);
        Map<String,List<DictForm>> formMap=new HashMap<>();
        if(activityList!=null)
        {
            for(SysDict sd:activityList)
            {
                List<DictForm> dictForms = hbresBiz.readDictFormsByFlow(sd.getDictFlow());
                formMap.put(sd.getDictId(),dictForms);
            }
        }
        model.addAttribute("formMap",formMap);
        List<Map<String,Object>>  results=activityBiz.readActivityResults(activityFlow,"");
        if(!(results!=null&&results.size()>0))
        {
            List<SysDept> depts=appbiz.getHeadDeptList(userFlow,userinfo.getDeptFlow());
            model.addAttribute("depts",depts);
            return "res/gzdh/teacher/editActivity";
        }
        int imgCount=0;
        if(activity!=null)
        {
            String content= (String) activity.get("imageUrl");
            List<Map<String, String>> imageList=activityBiz.parseImageXml(content);
            model.addAttribute("imageList", imageList);
            if(imageList!=null)
            {
                imgCount=imageList.size();
            }
        }
        model.addAttribute("imgCount",imgCount+"张");
        return "res/gzdh/teacher/showActivity";
    }
    @RequestMapping(value = {"/addActivity"}, method = {RequestMethod.POST})
    public String addActivity(String userFlow,Model model,String roleId) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/teacher/editActivity";
        }
        if(StringUtil.isBlank(roleId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/gzdh/teacher/editActivity";
        }
        if(!roleId.equals("Teacher")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/gzdh/teacher/editActivity";
        }
        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/gzdh/teacher/editActivity";
        }
       /* List<TeachingActivityTarget> targets=activityBiz.readByOrg(userinfo.getOrgFlow());
        if(targets==null||targets.size()<=0)
        {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "暂未配置活动指标，请联系基地管理员");
            return "res/gzdh/success";
        }*/
        model.addAttribute("user",userinfo);
        List<SysDept> depts=appbiz.getHeadDeptList(userFlow,userinfo.getDeptFlow());
        model.addAttribute("depts",depts);
        List<SysDict> activityList=hbresBiz.getDictListByDictId("ActivityType");
        model.addAttribute("activityList",activityList);
        Map<String,List<DictForm>> formMap=new HashMap<>();
        if(activityList!=null)
        {
            for(SysDict sd:activityList)
            {
                List<DictForm> dictForms = hbresBiz.readDictFormsByFlow(sd.getDictFlow());
                formMap.put(sd.getDictId(),dictForms);
            }
        }
        model.addAttribute("formMap",formMap);
        return "res/gzdh/teacher/editActivity";
    }
    @RequestMapping(value = {"/saveActivity"}, method = {RequestMethod.POST})
    public String saveActivity(String userFlow,TeachingActivityInfo activity,HttpServletRequest request,Model model,String roleId) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/success";
        }
        if(StringUtil.isBlank(roleId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/gzdh/success";
        }
        if(StringUtil.isBlank(activity.getSpeakerFlow())){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "主讲人标识符为空");
            return "res/gzdh/success";
        }
        if(StringUtil.isBlank(activity.getDeptFlow())){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/gzdh/success";
        }
        if(StringUtil.isBlank(activity.getActivityTypeId())){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "活动形式为空");
            return "res/gzdh/success";
        }
        if(StringUtil.isBlank(activity.getActivityName())){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "活动名称为空");
            return "res/gzdh/success";
        }
        if(StringUtil.isBlank(activity.getActivityAddress())){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "活动地点为空");
            return "res/gzdh/success";
        }
        if(StringUtil.isBlank(activity.getSpeakerPhone())){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "联系方式为空");
            return "res/gzdh/success";
        }
        if(StringUtil.isBlank(activity.getStartTime())){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "活动开始时间为空");
            return "res/gzdh/success";
        }

        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            dateFormat2.parse(activity.getStartTime());
        } catch (Exception e) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "活动开始时间格式有误");
            return "res/gzdh/success";
        }
        if(StringUtil.isBlank(activity.getEndTime())){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "活动结束时间为空");
            return "res/gzdh/success";
        }
        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            dateFormat2.parse(activity.getEndTime());
        } catch (Exception e) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "活动结束时间格式有误");
            return "res/gzdh/success";
        }
        if(activity.getStartTime().compareTo(activity.getEndTime())>=0)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "开始时间不得大于等于结束时间");
            return "res/gzdh/success";
        }
        if(StringUtil.isBlank(activity.getActivityRemark())){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "活动简介为空");
            return "res/gzdh/success";
        }
        if(!roleId.equals("Teacher")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/gzdh/success";
        }
        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/gzdh/success";
        }
        model.addAttribute("user",userinfo);
        List<TeachingActivityTarget> targets=activityBiz.readByOrg(activity.getActivityTypeId(),userinfo.getOrgFlow());
        if(targets==null||targets.size()<=0)
        {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "暂未配置活动指标，请联系基地管理员");
            return "res/gzdh/success";
        }
        //校验活动时间是否重复
        int count=checkTime(activity);
        if(count>0)
        {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "该主讲人在时间段内已开展其他活动");
            return "res/gzdh/success";
        }
        List<TeachingActivityFormValue> formValues=null;
        if(StringUtil.isNotBlank(activity.getActivityTypeId()))
        {
            activity.setActivityTypeName(hbresBiz.getDictNameByTypeId(activity.getActivityTypeId(),"ActivityType"));
            SysDict dict=hbresBiz.getDictByTypeId("ActivityType",activity.getActivityTypeId());
            List<DictForm> dictForms=hbresBiz.readDictFormsByFlow(dict.getDictFlow());
            if(dictForms!=null&&dictForms.size()>0)
            {
                formValues=new ArrayList<>();
                for(DictForm dictForm:dictForms)
                {
                    String v="";
                    String []vs=request.getParameterValues(dictForm.getRecordFlow());
                    if(vs==null||vs.length==0)
                    {
                        model.addAttribute("resultId", "3010103");
                        model.addAttribute("resultType", "请填写【"+dictForm.getDetailKey()+"】内容");
                        return "res/hbres/success";
                    }
                    v=vs[0];
                    TeachingActivityFormValue save = new TeachingActivityFormValue();
                    save.setFormFlow(dictForm.getRecordFlow());
                    save.setDetailValue(v);
                    save.setActivityFlow(activity.getActivityFlow());
                    save.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                    formValues.add(save);
                }
            }
        }
        activity.setOrgFlow(userinfo.getOrgFlow());
        int c= activityBiz.addActivityInfo(activity,userinfo,targets, formValues);
        if(c==0)
        {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "保存失败");
            return "res/gzdh/success";
        }
        return "res/gzdh/success";
    }
    private int checkTime(TeachingActivityInfo activity) {
        return activityBiz.checkTime(activity);
    }
    @RequestMapping(value={"/viewActivityImage"},method={RequestMethod.POST})
    public String viewActivityImage(String userFlow,String activityFlow,Model model,String roleId) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/teacher/viewActivityImage";
        }
        if(StringUtil.isBlank(roleId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/gzdh/teacher/viewActivityImage";
        }
        if(StringUtil.isBlank(activityFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "活动标识符为空");
            return "res/gzdh/teacher/viewActivityImage";
        }
        if(!roleId.equals("Teacher")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/gzdh/teacher/viewActivityImage";
        }
        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/gzdh/teacher/viewActivityImage";
        }
        Map<String, Object> activity=activityBiz.readActivity(activityFlow);
        if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
        {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "活动信息不存在");
            return "res/gzdh/teacher/viewActivityImage";
        }
        if(activity!=null)
        {
            String content= (String) activity.get("imageUrl");
            List<Map<String, String>> imageList=activityBiz.parseImageXml(content);
            model.addAttribute("imageList", imageList);
        }
        return "res/gzdh/teacher/viewActivityImage";
    }
    @RequestMapping(value={"/addActivityImage"},method={RequestMethod.POST})
    public String addActivityImage(ActivityImageFileForm form, HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(form.getUserFlow())){
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/success";
        }
        if(StringUtil.isEmpty(form.getActivityFlow())){
            model.addAttribute("resultId", "31602");
            model.addAttribute("resultType", "教学活动标识符为空");
            return "res/gzdh/success";
        }
        if(StringUtil.isEmpty(form.getFileName())){
            model.addAttribute("resultId", "31603");
            model.addAttribute("resultType", "文件名为空");
            return "res/gzdh/success";
        }
        if(form.getUploadFile()==null && StringUtil.isBlank(form.getUploadFileData())){
            model.addAttribute("resultId", "31703");
            model.addAttribute("resultType", "上传文件不能为空");
            return "res/gzdh/success";
        }
        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(form.getUserFlow());
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/gzdh/success";
        }

        Map<String, Object> activity=activityBiz.readActivity(form.getActivityFlow());
        if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
        {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "活动信息不存在");
            return "res/gzdh/success";
        }
        activityBiz.addActivityImage(form,userinfo);
        return "res/gzdh/success";
    }
    @RequestMapping(value={"/deleteActivityImage"},method={RequestMethod.GET})
    public String deleteActivityImage(String userFlow,String activityFlow,String imageFlow, HttpServletRequest request,HttpServletResponse response,Model model) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "31701");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/success";
        }
        if(StringUtil.isEmpty(activityFlow)){
            model.addAttribute("resultId", "31702");
            model.addAttribute("resultType", "活动标识符为空");
            return "res/gzdh/success";
        }
        if(StringUtil.isEmpty(imageFlow)){
            model.addAttribute("resultId", "31702");
            model.addAttribute("resultType", "图片标识符为空");
            return "res/gzdh/success";
        }
        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/gzdh/success";
        }
        Map<String, Object> activity=activityBiz.readActivity(activityFlow);
        if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
        {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "活动信息不存在");
            return "res/gzdh/success";
        }
        activityBiz.deleteActivityImage(userinfo,activityFlow,imageFlow);
        return "res/gzdh/success";
    }
    @RequestMapping(value = {"/activityEval"}, method = {RequestMethod.POST})
    public String activityEval(String userFlow,String activityFlow,Model model,String roleId) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/teacher/activityEval";
        }
        if(StringUtil.isBlank(roleId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/gzdh/teacher/activityEval";
        }
        if(StringUtil.isBlank(activityFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "活动标识符为空");
            return "res/gzdh/teacher/activityEval";
        }
        if(!roleId.equals("Teacher")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/gzdh/teacher/activityEval";
        }
        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/gzdh/teacher/activityEval";
        }
        Map<String, Object> activity=activityBiz.readActivity(activityFlow);
        if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
        {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "活动信息不存在");
            return "res/gzdh/teacher/activityEval";
        }
        //评价的指标
        List<Map<String,Object>> targets=activityBiz.readActivityTargetEvals(activityFlow);
        model.addAttribute("targets",targets);
        return "res/gzdh/teacher/activityEval";
    }
    @RequestMapping(value = {"/activityEvalList"}, method = {RequestMethod.POST})
    public String activityEvalList(String userFlow,String activityFlow,String searchStr,Integer pageIndex,Integer pageSize,Model model,String roleId) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/teacher/activityEvalList";
        }
        if(StringUtil.isBlank(roleId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/gzdh/teacher/activityEvalList";
        }
        if(StringUtil.isBlank(activityFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "活动标识符为空");
            return "res/gzdh/teacher/activityEvalList";
        }
        if(!roleId.equals("Teacher")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/gzdh/teacher/activityEvalList";
        }
        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/gzdh/teacher/activityEvalList";
        }
        Map<String, Object> activity=activityBiz.readActivity(activityFlow);
        if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
        {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "活动信息不存在");
            return "res/gzdh/teacher/activityEvalList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/gzdh/teacher/activityEvalList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/gzdh/teacher/activityEvalList";
        }
        TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
        model.addAttribute("activity",info);
        //评价人员
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,Object>> results=activityBiz.readActivityResults(activityFlow,searchStr);
        model.addAttribute("results",results);
        model.addAttribute("user",userinfo);
        model.addAttribute("dataCount", PageHelper.total);

        //评价的指标
        List<Map<String,Object>> targets=activityBiz.readActivityTargetEvals(activityFlow);
        model.addAttribute("targets",targets);
        //评价人员评分详情
        Map<String,Object> evalDetailMap=new HashMap<>();
        if(results!=null)
        {
            for(Map<String,Object> r:results)
            {
                List<TeachingActivityEval> evals=activityBiz.readActivityResultEvals(String.valueOf(r.get("resultFlow")));
                if(evals!=null)
                {
                    for(TeachingActivityEval e:evals)
                    {
                        evalDetailMap.put(e.getResultFlow()+e.getTargetFlow(),e.getEvalScore());
                    }
                }
            }
        }
        model.addAttribute("evalDetailMap",evalDetailMap);
        return "res/gzdh/teacher/activityEvalList";
    }
    @RequestMapping(value = {"/activityStuList"}, method = {RequestMethod.POST})
    public String activityStuList(String userFlow,String activityFlow,String searchStr,Integer pageIndex,String typeId,Integer pageSize,Model model,String roleId) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/gzdh/teacher/activityStuList";
        }
        if(StringUtil.isBlank(roleId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/gzdh/teacher/activityStuList";
        }
        if(StringUtil.isBlank(activityFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "活动标识符为空");
            return "res/gzdh/teacher/activityStuList";
        }
        if(StringUtil.isBlank(typeId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "typeId标识符为空");
            return "res/gzdh/teacher/activityStuList";
        }
        if(!"Y".equals(typeId)&&!"N".equals(typeId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "typeId只能是Y或N");
            return "res/gzdh/teacher/activityStuList";
        }
        if(!roleId.equals("Teacher")){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID与角色不符");
            return "res/gzdh/teacher/activityStuList";
        }
        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/gzdh/teacher/activityStuList";
        }
        Map<String, Object> activity=activityBiz.readActivity(activityFlow);
        if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
        {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "活动信息不存在");
            return "res/gzdh/teacher/activityStuList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/gzdh/teacher/activityStuList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/gzdh/teacher/activityStuList";
        }
        //评价人员
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,Object>> results=activityBiz.readActivityResultsByType(activityFlow,searchStr,typeId);
        model.addAttribute("results",results);
        model.addAttribute("user",userinfo);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/gzdh/teacher/activityStuList";
    }
}

