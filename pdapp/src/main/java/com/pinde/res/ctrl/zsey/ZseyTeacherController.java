package com.pinde.res.ctrl.zsey;


import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.common.IResPowerCfgBiz;
import com.pinde.res.biz.stdp.IInxInfoBiz;
import com.pinde.res.biz.stdp.INoticeBiz;
import com.pinde.res.biz.stdp.IResGradeBiz;
import com.pinde.res.biz.zsey.IZseyAppBiz;
import com.pinde.res.biz.zsey.IZseyStudentBiz;
import com.pinde.res.biz.zsey.IZseyTeacherBiz;
import com.pinde.res.biz.stdp.IResSchProcessExpressBiz;
import com.pinde.res.enums.hbres.ResAssessScoreTypeEnum;
import com.pinde.res.enums.hbres.ResAssessTypeEnum;
import com.pinde.res.enums.hbres.ResRecTypeEnum;
import com.pinde.res.enums.lcjn.DictTypeEnum;
import com.pinde.res.enums.stdp.RegistryTypeEnum;
import com.pinde.res.model.jswjw.mo.ResAssessCfgItemForm;
import com.pinde.res.model.jswjw.mo.ResAssessCfgTitleForm;
import com.pinde.res.model.stdp.mo.InxInfoForm;
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
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/zsey/teacher")
public class ZseyTeacherController {
    private static Logger logger = LoggerFactory.getLogger(ZseyTeacherController.class);

    @Autowired
    private IZseyStudentBiz studentBiz;
    @Autowired
    private IZseyAppBiz appbiz;
    @Autowired
    private IZseyTeacherBiz teacherBiz;
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

    @ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
        logger.error(this.getClass().getCanonicalName() + " some error happened", e);
        return "res/zsey/500";
    }

    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET})
    public String test() {
        return "res/zsey/teacher/test";
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
        return "/res/zsey/teacher/test";
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
            return "res/zsey/teacher/doctorList";
        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/zsey/teacher/doctorList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/zsey/teacher/doctorList";
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

        return "res/zsey/teacher/doctorList";
    }

    @RequestMapping(value = {"/funcList"}, method = {RequestMethod.GET})
    public String funcList(String userFlow, String doctorFlow, HttpServletRequest request, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3030201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/zsey/teacher/funcList";
        }
        if (StringUtil.isEmpty(doctorFlow)) {
            model.addAttribute("resultId", "3030202");
            model.addAttribute("resultType", "学员标识符为空");
            return "res/zsey/teacher/funcList";
        }
        return "res/zsey/teacher/funcList";
    }

    @RequestMapping(value = {"/index"}, method = {RequestMethod.GET})
    public String index(String userFlow, HttpServletRequest request, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3030201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/zsey/teacher/funcList";
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
        return "res/zsey/teacher/index";
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
    @RequestMapping(value = {"/userList"}, method = {RequestMethod.POST})
    public String userList(String userFlow, String studentName, String speId, String typeId, String sessionNumber, Integer pageIndex, Integer pageSize, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/zsey/teacher/userList";
        }

        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/zsey/teacher/userList";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/zsey/teacher/userList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/zsey/teacher/userList";
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
        return "res/zsey/teacher/userList";
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
            return "res/zsey/teacher/studentList";
        }

        if (StringUtil.isBlank(dataType)) {
            if (StringUtil.isBlank(doctorFlow)) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "学员流水号为空");
                return "res/zsey/teacher/studentList";
            }
        }
        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/zsey/teacher/studentList";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/zsey/teacher/studentList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/zsey/teacher/studentList";
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
        return "res/zsey/teacher/studentList";
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
            return "res/zsey/teacher/dataNoAudit";
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "学生标识符为空");
            return "res/zsey/teacher/dataNoAudit";
        }
        if (StringUtil.isBlank(processFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/zsey/teacher/dataNoAudit";
        }
        if (StringUtil.isBlank(resultFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "排班标识符为空");
            return "res/zsey/teacher/dataNoAudit";
        }
        if (StringUtil.isBlank(dataType)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型为空");
            return "res/zsey/teacher/dataNoAudit";
        }
        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/zsey/teacher/dataNoAudit";
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
            return "res/zsey/teacher/dataNoAudit";
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
        return "res/zsey/teacher/dataNoAudit";
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
            return "res/zsey/teacher/recDataList";
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "学生标识符为空");
            return "res/zsey/teacher/recDataList";
        }
        if (StringUtil.isBlank(resultFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转计划标识符为空");
            return "res/zsey/teacher/recDataList";
        }
        if (StringUtil.isBlank(processFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/zsey/teacher/recDataList";
        }
        if (StringUtil.isBlank(recType)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型为空");
            return "res/zsey/teacher/recDataList";
        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/zsey/teacher/recDataList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/zsey/teacher/recDataList";
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
        return "res/zsey/teacher/recDataList";
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
            return "res/zsey/teacher/resRecDetail";
        }
        if (StringUtil.isBlank(recFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/zsey/teacher/resRecDetail";
        }
        if (StringUtil.isBlank(recType)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型为空");
            return "res/zsey/teacher/resRecDetail";
        }
        if (StringUtil.isBlank(resultFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "排班流水号为空");
            return "res/zsey/teacher/resRecDetail";
        }
        //验证用户是否存在
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/zsey/teacher/resRecDetail";
        }
        ResRec rec = teacherBiz.readResRec(recFlow);
        String recContent = rec.getRecContent();
        Map<String, String> formDataMap = appbiz.parseRecContent(recContent);
        formDataMap.put("auditId", rec.getAuditStatusId());
        model.addAttribute("resultData", formDataMap);
        model.addAttribute("isOther", GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(cataFlow));
        return "res/zsey/teacher/resRecDetail";
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
            return "res/zsey/success";
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "学生标识符为空");
            return "res/zsey/success";
        }
        if (StringUtil.isBlank(processFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/zsey/success";
        }
        //rec类型转换一下
        if (StringUtil.isBlank(recType)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型为空");
            return "res/zsey/success";
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
                return "res/zsey/success";
            }
        } else {
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "无审核数据");
            return "res/zsey/success";
        }
        return "res/zsey/success";
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
            return "res/zsey/success";
        }
        if (StringUtil.isBlank(recFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/zsey/success";
        }
        if (StringUtil.isBlank(statusId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "审核状态不能为空");
            return "res/zsey/success";
        }
        if (!statusId.equals("Y") && !statusId.equals("N")) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "审核状态只能是通过或不通过");
            return "res/zsey/success";
        }
        ResRec re = teacherBiz.readResRec(recFlow);
        if (StringUtil.isNotBlank(re.getAuditStatusId())) {
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "数据已审核");
            return "res/zsey/success";
        }
        int i = teacherBiz.auditRecDate(userFlow, recFlow, statusId);
        if (i == 0) {
            model.addAttribute("resultId", "310101");
            model.addAttribute("resultType", "数据已审核");
            return "res/zsey/success";
        }
        return "res/zsey/success";
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
            return "res/zsey/viewData";
        }

        if (StringUtil.isEmpty(roleId)) {
            model.addAttribute("resultId", "3010602");
            model.addAttribute("resultType", "用户角色为空");
            return "res/zsey/viewData";
        }

        if (StringUtil.isEmpty(funcTypeId)) {
            model.addAttribute("resultId", "3010603");
            model.addAttribute("resultType", "功能类型为空");
            return "res/zsey/viewData";
        }

        if (StringUtil.isEmpty(funcFlow)) {
            model.addAttribute("resultId", "3010604");
            model.addAttribute("resultType", "功能标识为空");
            return "res/zsey/viewData";
        }

        if ("Student".equals(roleId) && StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "3010605");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/zsey/viewData";
        }

        if ("Teacher".equals(roleId) && StringUtil.isEmpty(doctorFlow)) {
            model.addAttribute("resultId", "3010606");
            model.addAttribute("resultType", "学员标识符为空");
            return "res/zsey/viewData";
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

        return "res/zsey/viewData";
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
            return "res/zsey/viewData";
        }

        if (StringUtil.isEmpty(recType)) {
            model.addAttribute("resultId", "3010604");
            model.addAttribute("resultType", "数据类型标识为空");
            return "res/zsey/viewData";
        }
        if (StringUtil.isEmpty(resultFlow)) {
            model.addAttribute("resultId", "3010606");
            model.addAttribute("resultType", "计划标识符为空");
            return "res/zsey/viewData";
        }
        String path = "teacher";
        model.addAttribute("path", path);
        //当前轮转计划信息
        SchArrangeResult result = studentBiz.searcheDocResult(null, resultFlow);
        model.addAttribute("result", result);
        SysUser tea = appbiz.readSysUser(userFlow);
        model.addAttribute("tea", tea);

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
            if(rec==null)
            {
                version="2.0";
            }
            recType=recType+"_"+version;
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

        return "res/zsey/viewData";
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
            return "res/zsey/saveData";
        }

        if (StringUtil.isEmpty(resultFlow)) {
            model.addAttribute("resultId", "3010904");
            model.addAttribute("resultType", "排班标识符为空");
            return "res/zsey/saveData";
        }
        if (StringUtil.isEmpty(recTypeId)) {
            model.addAttribute("resultId", "3010906");
            model.addAttribute("resultType", "功能标识为空");
            return "res/zsey/saveData";
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
        String version="1.0";
        if (rec != null) {
            recTypeId = rec.getRecTypeId();
            version=rec.getRecVersion();
        }else{
            if(ResRecTypeEnum.AfterEvaluation.getId().equals(recTypeId))
            {
                version="2.0";
            }
        }

        dataFlow = expressBiz.editZseyExpress(dataFlow, operUserFlow, resultFlow, recTypeId, GlobalConstant.RES_ZSEY_DEFAULT_FORM, request,GlobalConstant.RES_ROLE_SCOPE_HEAD,"",version);

        //审核数据
        expressBiz.auditDate(userFlow, dataFlow);

        return "res/zsey/saveData";
    }

    @RequestMapping(value="/docEvalFuncList",method=RequestMethod.POST)
    public String docEvalFuncList(String userFlow,String processFlow,String resultFlow,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/zsey/teacher/evalFuncList";
        }
        if(StringUtil.isBlank(resultFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "计划标识符为空");
            return "res/zsey/teacher/evalFuncList";
        }
        if(StringUtil.isBlank(processFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/zsey/teacher/evalFuncList";
        }
        SysUser userinfo = appbiz.readSysUser(userFlow);
        if(userinfo==null){
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/zsey/teacher/evalFuncList";
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
        return "res/zsey/teacher/evalFuncList";
    }

    @RequestMapping(value="/evalInfoDetail",method=RequestMethod.POST)
    public String evalInfoDetail(String userFlow,String processFlow,String typeId,String recFlow,Model model) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/zsey/teacher/evalInfoDetail";
        }
        if(StringUtil.isBlank(processFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/zsey/teacher/evalInfoDetail";
        }
        if(StringUtil.isBlank(typeId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "评价类型为空");
            return "res/zsey/teacher/evalInfoDetail";
        }
        if(!ResAssessTypeEnum.TeacherDoctorAssess.getId().equals(typeId))
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "评价类型错误，只能是TeacherDoctorAssess");
            return "res/zsey/teacher/evalInfoDetail";
        }
        DeptTeacherGradeInfo info=gradeBiz.readByFlow(recFlow);
        if(info==null&& ResAssessTypeEnum.TeacherDoctorAssess.getId().equals(typeId))
        {
            info=gradeBiz.getRecByRecType(processFlow,typeId);
        }
        boolean isAudit=false;
        if(info!=null)
            isAudit=true;
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

        return "res/zsey/teacher/evalInfoDetail";
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
            return "res/zsey/saveData";
        }

        if(StringUtil.isEmpty(roleId)){
            model.addAttribute("resultId", "3010902");
            model.addAttribute("resultType", "用户角色为空");
            return "res/zsey/saveData";
        }

        if(StringUtil.isBlank(processFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/zsey/saveData";
        }
        if(StringUtil.isBlank(resultFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转记录标识符为空");
            return "res/zsey/saveData";
        }
        if(StringUtil.isBlank(typeId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "评价类型为空");
            return "res/zsey/saveData";
        }
        if(!ResAssessTypeEnum.TeacherDoctorAssess.getId().equals(typeId))
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "评价类型错误，只能是TeacherDoctorAssess");
            return "res/zsey/saveData";
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
        gradeBiz.editKmGradeInfo(recFlow, userFlow,resultFlow,processFlow, typeId, request,GlobalConstant.RES_ZSEY_DEFAULT_FORM);

        return "res/zsey/saveData";
    }

}

