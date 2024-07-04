package com.pinde.res.ctrl.shfx;


import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.XmlUtil;
import com.pinde.res.biz.common.IResPowerCfgBiz;
import com.pinde.res.biz.hbres.IHbresAppBiz;
import com.pinde.res.biz.hbres.IHbresStudentBiz;
import com.pinde.res.biz.hbres.IHbresTeacherBiz;
import com.pinde.res.biz.shfx.IShfxAppBiz;
import com.pinde.res.biz.shfx.IShfxStudentBiz;
import com.pinde.res.biz.shfx.IShfxTeacherBiz;
import com.pinde.res.biz.stdp.IResGradeBiz;
import com.pinde.res.biz.stdp.IResSchProcessExpressBiz;
import com.pinde.res.enums.hbres.ResAssessTypeEnum;
import com.pinde.res.enums.hbres.ResRecTypeEnum;
import com.pinde.res.enums.shfx.Rec360TypeEnum;
import com.pinde.res.enums.shfx.RecSkillTypeEnum;
import com.pinde.res.enums.stdp.RegistryTypeEnum;
import com.pinde.sci.model.mo.*;
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
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/res/shfx/teacher")
public class ShfxTeacherController {
    private static Logger logger = LoggerFactory.getLogger(ShfxTeacherController.class);

    @Autowired
    private IShfxStudentBiz shfxStudentBiz;
    @Autowired
    private IShfxAppBiz shfxAppBiz;
    @Autowired
    private IShfxTeacherBiz teacherBiz;
    @Autowired
    private IResPowerCfgBiz resPowerCfgBiz;
    @Autowired
    private IResSchProcessExpressBiz expressBiz;
    @Autowired
    private IResGradeBiz resGradeBiz;

    @ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
        logger.error(this.getClass().getCanonicalName() + " some error happened", e);
        return "res/shfx/500";
    }

    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET})
    public String test() {
        return "res/shfx/teacher/test";
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
        return "/res/shfx/teacher/test";
    }

    @RequestMapping(value = {"/doctorList"}, method = {RequestMethod.GET})
    public String doctorList(String userFlow,
                             String searchData,
                             Integer pageIndex,
                             Integer pageSize,
                             HttpServletRequest request,
                             Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "3030101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/shfx/teacher/doctorList";
        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/shfx/teacher/doctorList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/shfx/teacher/doctorList";
        }

        //包装筛选条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("teacherUserFlow", userFlow);

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
        model.addAttribute("doctorMapList", doctorMapList);

        //获取访问路径前缀
        String uploadBaseUrl = shfxAppBiz.getCfgByCode("upload_base_url");
        model.addAttribute("uploadBaseUrl", uploadBaseUrl);

        //数据数量
        model.addAttribute("dataCount", PageHelper.total);

        return "res/shfx/teacher/doctorList";
    }

    @RequestMapping(value = {"/funcList"}, method = {RequestMethod.GET})
    public String funcList(String userFlow, String doctorFlow, HttpServletRequest request, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3030201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/shfx/teacher/funcList";
        }
        if (StringUtil.isEmpty(doctorFlow)) {
            model.addAttribute("resultId", "3030202");
            model.addAttribute("resultType", "学员标识符为空");
            return "res/shfx/teacher/funcList";
        }
        return "res/shfx/teacher/funcList";
    }

    @RequestMapping(value = {"/index"}, method = {RequestMethod.GET})
    public String index(String userFlow, HttpServletRequest request, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3030201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/shfx/teacher/funcList";
        }
        SysUser user = shfxAppBiz.readSysUser(userFlow);
        model.addAttribute("userinfo", user);
        //包装筛选条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("teacherUserFlow", userFlow);
        paramMap.put("statusId", "Entering");//轮转中
        List<Map<String, Object>> enteringDocs = teacherBiz.getDocListByTeacher(paramMap);
        if (enteringDocs != null) {
            model.addAttribute("isCurrent", enteringDocs.size());
        }
        paramMap = new HashMap<String, Object>();
        paramMap.put("teacherUserFlow", userFlow);
        paramMap.put("statusId", "Exited");//已出科
        List<Map<String, Object>> exitedDocs = teacherBiz.getDocListByTeacher(paramMap);
        if (exitedDocs != null) {
            model.addAttribute("isSch", exitedDocs.size());
        }
        List<SysUserRole> userRoles = shfxAppBiz.getSysUserRole(userFlow);
        if(userRoles!=null&&userRoles.size()>0) {
            //获取当前配置的老师角色
            String teacherRole = shfxAppBiz.getCfgCode("res_teacher_role_flow");
            //获取当前配置的科主任角色
            String headRole = shfxAppBiz.getCfgCode("res_head_role_flow");
            //获取当前配置的科秘角色
            String secretaryRole = shfxAppBiz.getCfgCode("res_secretary_role_flow");
            List<Map<String,String>> roles=new ArrayList<>();
            for (SysUserRole sur : userRoles) {
                Map<String,String> map=new HashMap<>();
                String ur = sur.getRoleFlow();
                if (headRole.equals(ur)) {
                    map.put("roleId", "Head");
                    map.put("roleName", "科主任");
                    roles.add(map);
                }
                if (teacherRole.equals(ur)) {
                    map.put("roleId", "Teacher");
                    map.put("roleName", "老师");
                    roles.add(map);
                }
                if (secretaryRole.equals(ur)) {
                    map.put("roleId", "Secretary");
                    map.put("roleName", "科室秘书");
                    roles.add(map);
                }
            }
            model.addAttribute("roles",roles);
        }
        return "res/shfx/teacher/index";
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
    public String studentList(String userFlow, String studentName, String dataType, Integer pageIndex, Integer pageSize, Model model, String biaoJi) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/shfx/teacher/studentList";
        }
        //验证用户是否存在
        SysUser userinfo = shfxAppBiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/shfx/teacher/studentList";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/shfx/teacher/studentList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/shfx/teacher/studentList";
        }

        List<Map<String, String>> resDoctorSchProcess = null;
        Map<String, Object> schArrangeResultMap = new HashMap<String, Object>();
        schArrangeResultMap.put("teacherUserFlow", userFlow);
        schArrangeResultMap.put("userName", studentName);
        schArrangeResultMap.put("biaoJi", biaoJi);//如果为Y就是查询带审核的学员列表
//		后台配置是否校验权限时间
        String isPermitOpen = shfxAppBiz.getCfgByCode("res_permit_open_doc");
        schArrangeResultMap.put("isPermitOpen",isPermitOpen);
        //湖北现在已经需要填写的数据类型
        List<String> typeId = new ArrayList<>();
        List<GeneralEnum> enumList = new ArrayList<>();
        if (StringUtil.isBlank(dataType) || dataType.equals("fiveData")) {
            for (ResRecTypeEnum e : ResRecTypeEnum.values()) {
                String cfgValue = shfxAppBiz.getCfgByCode("res_registry_type_" + e.getId());
                if (StringUtil.isNotBlank(cfgValue) && cfgValue.equals(GlobalConstant.FLAG_Y)) {
                    if (StringUtil.isNotBlank(dataType) && dataType.equals("fiveData")) {
                        typeId.add(e.getId());
                    }
                    enumList.add(e);
                }
            }
            if (StringUtil.isBlank(dataType)) {
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
        Map<String, Object> schRotationDeptMap = new HashMap<String, Object>();
        Map<String, Object> readSchRotationGroupMap = new HashMap<String, Object>();
        Map<String, Object> resRecMap = new HashMap<String, Object>();
        Map<String, Integer> resRecCountMap = new HashMap<String, Integer>();
        Map<String, String> schScoreMap = new HashMap<String, String>();
        for (Map<String, String> map : resDoctorSchProcess) {
            List<ResRec> resRecList = teacherBiz.getDocRecs(map.get("processFlow"), map.get("doctorFlow"), null);
            for (ResRec resRec : resRecList) {
                String k = map.get("processFlow") + resRec.getRecTypeId();
                String k2 = k + "notAudit";
                Integer i = resRecCountMap.get(k);
                if (i == null) {
                    i = 0;
                }
                i++;
                resRecCountMap.put(k, i);
                if (!StringUtil.isNotBlank(resRec.getAuditStatusId())) {
                    Integer j = resRecCountMap.get(k2);
                    if (j == null) {
                        j = 0;
                    }
                    j++;
                    resRecCountMap.put(k2, j);
                }
//                if (ResRecTypeEnum.AfterEvaluation.getId().equals(resRec.getRecTypeId())) {
//                    String dopsKey = map.get("processFlow") + "AfterEvaluation";
//                    resRecMap.put(dopsKey, resRec);
//                }
//                if (ResRecTypeEnum.DOPS.getId().equals(resRec.getRecTypeId())) {
//                    String dopsKey = map.get("processFlow") + "DOPS";
//                    resRecMap.put(dopsKey, resRec);
//                }
//                if (ResRecTypeEnum.AfterSummary.getId().equals(resRec.getRecTypeId())) {
//                    String dopsKey = map.get("processFlow") + "AfterSummary";
//                    resRecMap.put(dopsKey, resRec);
//                }
//                if (ResRecTypeEnum.Mini_CEX.getId().equals(resRec.getRecTypeId())) {
//                    String miniKey = map.get("processFlow") + "Mini_CEX";
//                    resRecMap.put(miniKey, resRec);
//                }
            }
            List<String> recTypeIds=new ArrayList<>();
            recTypeIds.add(ResRecTypeEnum.DOPS.getId());
            recTypeIds.add(ResRecTypeEnum.Mini_CEX.getId());
            recTypeIds.add(ResRecTypeEnum.AfterSummary.getId());
            recTypeIds.add(ResRecTypeEnum.AfterEvaluation.getId());
            List<ResSchProcessExpress> expressList = expressBiz.getDocexpressList(map.get("processFlow"),  recTypeIds);
            for(ResSchProcessExpress express:expressList)
            {
                String key =express.getProcessFlow()+express.getRecTypeId();
                resRecMap.put(key, express);
            }
        }
        model.addAttribute("resRecMap", resRecMap);
        //获取访问路径前缀
        String uploadBaseUrl = shfxAppBiz.getCfgByCode("upload_base_url");
        model.addAttribute("uploadBaseUrl", uploadBaseUrl);
        model.addAttribute("list", resDoctorSchProcess);
        model.addAttribute("dataCount", PageHelper.total);
        model.addAttribute("biaoJi", biaoJi);
        return "res/shfx/teacher/studentList";
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
            return "res/shfx/teacher/dataNoAudit";
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "学生标识符为空");
            return "res/shfx/teacher/dataNoAudit";
        }
        if (StringUtil.isBlank(processFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/shfx/teacher/dataNoAudit";
        }
        if (StringUtil.isBlank(resultFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "排班标识符为空");
            return "res/shfx/teacher/dataNoAudit";
        }
        if (StringUtil.isBlank(dataType)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型为空");
            return "res/shfx/teacher/dataNoAudit";
        }
        //验证用户是否存在
        SysUser userinfo = shfxAppBiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/shfx/teacher/dataNoAudit";
        }
        //湖北现在已经需要填写的数据类型
        List<String> typeId = new ArrayList<>();
        List<GeneralEnum> enumList = new ArrayList<>();
        if (dataType.equals("fiveData")) {
            for (ResRecTypeEnum e : ResRecTypeEnum.values()) {
                String cfgValue = shfxAppBiz.getCfgByCode("res_registry_type_" + e.getId());
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
            return "res/shfx/teacher/dataNoAudit";
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
        return "res/shfx/teacher/dataNoAudit";
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
            return "res/shfx/teacher/recDataList";
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "学生标识符为空");
            return "res/shfx/teacher/recDataList";
        }
        if (StringUtil.isBlank(resultFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转计划标识符为空");
            return "res/shfx/teacher/recDataList";
        }
        if (StringUtil.isBlank(processFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/shfx/teacher/recDataList";
        }
        if (StringUtil.isBlank(recType)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型为空");
            return "res/shfx/teacher/recDataList";
        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/shfx/teacher/recDataList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/shfx/teacher/recDataList";
        }

        Map<String, Object> processPerMap = shfxStudentBiz.getRegPer(0, docFlow, resultFlow);
        model.addAttribute("processPerMap", processPerMap);
        PageHelper.startPage(pageIndex, pageSize);
        List<ResRec> recList = teacherBiz.searchRecByProcessAndRecType(processFlow, docFlow, recType, biaoJi);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        for (ResRec rec : recList) {
            String recContent = rec.getRecContent();
            Map<String, String> formDataMap = shfxAppBiz.parseRecContent(recContent);
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
        return "res/shfx/teacher/recDataList";
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
            return "res/shfx/teacher/resRecDetail";
        }
        if (StringUtil.isBlank(recFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/shfx/teacher/resRecDetail";
        }
        if (StringUtil.isBlank(recType)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型为空");
            return "res/shfx/teacher/resRecDetail";
        }
        if (StringUtil.isBlank(resultFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "排班流水号为空");
            return "res/shfx/teacher/resRecDetail";
        }
        //验证用户是否存在
        SysUser userinfo = shfxAppBiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/shfx/teacher/resRecDetail";
        }
        ResRec rec = teacherBiz.readResRec(recFlow);
        String recContent = rec.getRecContent();
        //获取当前排班计划的科室 所有子项
        //		SchArrangeResult result = shfxStudentBiz.searcheDocResult(null,resultFlow);
        //		String deptFlow="";
        //		if(result!=null){
        //			String standardDeptId = result.getStandardDeptId();
        //			String standardGroupFlow = result.getStandardGroupFlow();
        //			SchRotationDept rotationDept = shfxAppBiz.getRotationDeptByResult(standardGroupFlow,standardDeptId);
        //			if(rotationDept!=null){
        //				deptFlow = rotationDept.getRecordFlow();
        //			}
        //		}
        //_inputList(userFlow, deptFlow, recType,cataFlow, model);
        Map<String, String> formDataMap = shfxAppBiz.parseRecContent(recContent);
        formDataMap.put("auditId", rec.getAuditStatusId());
        model.addAttribute("resultData", formDataMap);
        model.addAttribute("isOther", GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(cataFlow));
        return "res/shfx/teacher/resRecDetail";
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
            return "res/shfx/success";
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "学生标识符为空");
            return "res/shfx/success";
        }
        if (StringUtil.isBlank(processFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/shfx/success";
        }
        //rec类型转换一下
        if (StringUtil.isBlank(recType)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型为空");
            return "res/shfx/success";
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
                return "res/shfx/success";
            }
        } else {
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "无审核数据");
            return "res/shfx/success";
        }
        return "res/shfx/success";
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
            return "res/shfx/success";
        }
        if (StringUtil.isBlank(recFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/shfx/success";
        }
        if (StringUtil.isBlank(statusId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "审核状态不能为空");
            return "res/shfx/success";
        }
        if (!statusId.equals("Y") && !statusId.equals("N")) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "审核状态只能是通过或不通过");
            return "res/shfx/success";
        }
        ResRec re = teacherBiz.readResRec(recFlow);
        if (StringUtil.isNotBlank(re.getAuditStatusId())) {
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "数据已审核");
            return "res/shfx/success";
        }
        int i = teacherBiz.auditRecDate(userFlow, recFlow, statusId);
        if (i == 0) {
            model.addAttribute("resultId", "310101");
            model.addAttribute("resultType", "数据已审核");
            return "res/shfx/success";
        }
        return "res/shfx/success";
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
            return "res/shfx/viewData";
        }

        if (StringUtil.isEmpty(roleId)) {
            model.addAttribute("resultId", "3010602");
            model.addAttribute("resultType", "用户角色为空");
            return "res/shfx/viewData";
        }

        if (StringUtil.isEmpty(funcTypeId)) {
            model.addAttribute("resultId", "3010603");
            model.addAttribute("resultType", "功能类型为空");
            return "res/shfx/viewData";
        }

        if (StringUtil.isEmpty(funcFlow)) {
            model.addAttribute("resultId", "3010604");
            model.addAttribute("resultType", "功能标识为空");
            return "res/shfx/viewData";
        }

        if ("Student".equals(roleId) && StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "3010605");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/shfx/viewData";
        }

        if ("Teacher".equals(roleId) && StringUtil.isEmpty(doctorFlow)) {
            model.addAttribute("resultId", "3010606");
            model.addAttribute("resultType", "学员标识符为空");
            return "res/shfx/viewData";
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
        SchArrangeResult result = shfxStudentBiz.searcheDocResult(null, resultFlow);
        if (result != null) {
            model.addAttribute("result", result);

            //读取要求
            if (StringUtil.isNotBlank(cataFlow)) {
                String standardDeptId = result.getStandardDeptId();
                String standardGroupFlow = result.getStandardGroupFlow();
                SchRotationDept rotationDept = shfxAppBiz.getRotationDeptByResult(standardGroupFlow, standardDeptId);
                if (rotationDept != null) {
                    String relRecordFlow = rotationDept.getRecordFlow();
                    req = shfxAppBiz.readReq(null, relRecordFlow, cataFlow);
                }
            }
        }

        //获取当前这条登记信息
        ResRec rec = null;
        if (StringUtil.isNotBlank(dataFlow)) {
            rec = shfxAppBiz.getRecByRecFlow(dataFlow);
            //如果是11模式根据类型和科室查询
        } else if ("dataInput11".equals(funcTypeId)) {
            String processFlow = "";
            ResDoctorSchProcess process = shfxStudentBiz.getProcessByResult(resultFlow);
            if (process != null) {
                //登记表单的科室
                processFlow = process.getProcessFlow();
            }
            rec = shfxAppBiz.getRecByRecType(processFlow, funcFlow);
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
            ResAssessCfg assessCfg = shfxAppBiz.getGradeTemplate(assessType);
            model.addAttribute("assessCfg", assessCfg);

            if (assessCfg != null) {
                String content = assessCfg.getCfgBigValue();
                //解析评分xml
                List<Map<String, Object>> assessMaps = shfxAppBiz.parseAssessCfg(content);
                model.addAttribute("assessMaps", assessMaps);
            }
        }

        if (rec != null) {
            String content = rec.getRecContent();
            //解析登记信息的xml
            Object formDataMap = null;
            if (!isGrade) {
                formDataMap = shfxAppBiz.parseRecContent(content);
            } else {
                formDataMap = shfxAppBiz.parseDocGradeXml(content);
            }
            model.addAttribute("formDataMap", formDataMap);

            //如果读取到数据就使用数据本身的funcFlow
            funcFlow = rec.getRecTypeId();
        }

        model.addAttribute("funcFlow", funcFlow);

        //老师和学生的控件开关
        model.addAttribute("sSwitch", sSwitch);
        model.addAttribute("tSwitch", tSwitch);
        //不可操作开关
        boolean isAudit = rec != null && StringUtil.isNotBlank(rec.getAuditStatusId());
        model.addAttribute("cannotOperSwitch", (tSwitch || isAudit));
        model.addAttribute("isAudit", isAudit);

        return "res/shfx/viewData";
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
            return "res/shfx/viewData";
        }

        if (StringUtil.isEmpty(recType)) {
            model.addAttribute("resultId", "3010604");
            model.addAttribute("resultType", "数据类型标识为空");
            return "res/shfx/viewData";
        }
        if (StringUtil.isEmpty(resultFlow)) {
            model.addAttribute("resultId", "3010606");
            model.addAttribute("resultType", "计划标识符为空");
            return "res/shfx/viewData";
        }
        String path = "teacher";
        model.addAttribute("path", path);
        //当前轮转计划信息
        SchArrangeResult result = shfxStudentBiz.searcheDocResult(null, resultFlow);
        model.addAttribute("result", result);
        SysUser tea = shfxAppBiz.readSysUser(userFlow);
        model.addAttribute("tea", tea);

        //获取当前这条登记信息
        ResSchProcessExpress rec = null;
        if (StringUtil.isNotBlank(recFlow)) {
            rec = expressBiz.getExpressByRecFlow(recFlow);
        } else {
            String processFlow = "";
            ResDoctorSchProcess process = shfxStudentBiz.getProcessByResult(resultFlow);
            if (process != null) {
                //登记表单的科室
                processFlow = process.getProcessFlow();
            }
            rec = expressBiz.getExpressByRecType(processFlow, recType);
        }
        if (rec != null) {
            String content = rec.getRecContent();
            //解析登记信息的xml
            Object formDataMap = null;
            formDataMap = shfxAppBiz.parseRecContent(content);
            model.addAttribute("formDataMap", formDataMap);
            //如果读取到数据就使用数据本身的funcFlow
            recType = rec.getRecTypeId();
        }
        model.addAttribute("funcFlow", recType);
        if (recType.equals(ResRecTypeEnum.AfterEvaluation.getId())) {
            ResDoctorSchProcess process = shfxAppBiz.readSchProcessByResultFlow(resultFlow);
            if (process != null) {
                model.addAttribute("doctorSchProcess", process);
                Map<String, Object> itemsMap=new HashMap<>();
                itemsMap.put("processFlow",process.getProcessFlow());
                itemsMap.put("recFormId","Physique");
                List<DeptTeacherGradeInfo> PhysiqueList=resGradeBiz.searchResGradeByItems(itemsMap);
                if(PhysiqueList!=null&&PhysiqueList.size()>0){
                    List<Double> scoreP=new ArrayList<>();
                    for(DeptTeacherGradeInfo dg:PhysiqueList){
                        scoreP.add(StringUtil.isBlank(dg.getAllScore())?0:Double.parseDouble(dg.getAllScore()));
                    }
                    if(scoreP!=null&&scoreP.size()>0){
                        model.addAttribute("PhysiqueScore", Collections.max(scoreP));
                        BigDecimal bg = new BigDecimal((Collections.max(scoreP))*0.05);
                        double db = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                        model.addAttribute("PhysiqueScore_Label", db);
                    }
                }
                itemsMap.put("recFormId","Clinical");
                List<DeptTeacherGradeInfo> ClinicalList=resGradeBiz.searchResGradeByItems(itemsMap);
                if(ClinicalList!=null&&ClinicalList.size()>0){
                    List<Double> scoreP=new ArrayList<>();
                    for(DeptTeacherGradeInfo dg:ClinicalList){
                        scoreP.add(StringUtil.isBlank(dg.getAllScore())?0:Double.parseDouble(dg.getAllScore()));
                    }
                    if(scoreP!=null&&scoreP.size()>0){
                        model.addAttribute("ClinicalScore",Collections.max(scoreP));
                        BigDecimal bg = new BigDecimal((Collections.max(scoreP))*0.1);
                        double db = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                        model.addAttribute("ClinicalScore_Label", db);
                    }
                }
                itemsMap.put("recFormId","MedicalHistory");
                List<DeptTeacherGradeInfo> MedicalHistoryList=resGradeBiz.searchResGradeByItems(itemsMap);
                if(MedicalHistoryList!=null&&MedicalHistoryList.size()>0){
                    List<Double> scoreP=new ArrayList<>();
                    for(DeptTeacherGradeInfo dg:MedicalHistoryList){
                        scoreP.add(StringUtil.isBlank(dg.getAllScore())?0:Double.parseDouble(dg.getAllScore()));
                    }
                    if(scoreP!=null&&scoreP.size()>0){
                        model.addAttribute("MedicalHistoryScore",Collections.max(scoreP));
                        BigDecimal bg = new BigDecimal((Collections.max(scoreP))*0.05);
                        double db = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                        model.addAttribute("MedicalHistoryScore_Label", db);
                    }
                }
                itemsMap.put("recFormId","CaseAnalysis");
                List<DeptTeacherGradeInfo> CaseAnalysisList=resGradeBiz.searchResGradeByItems(itemsMap);
                if(CaseAnalysisList!=null&&CaseAnalysisList.size()>0){
                    List<Double> scoreP=new ArrayList<>();
                    for(DeptTeacherGradeInfo dg:CaseAnalysisList){
                        scoreP.add(StringUtil.isBlank(dg.getAllScore())?0:Double.parseDouble(dg.getAllScore()));
                    }
                    if(scoreP!=null&&scoreP.size()>0){
                        model.addAttribute("CaseAnalysisScore",Collections.max(scoreP));
                        BigDecimal bg = new BigDecimal((Collections.max(scoreP))*0.15);
                        double db = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                        model.addAttribute("CaseAnalysisScore_Label", db);
                    }
                }
            }
        }
        //不可操作开关
        boolean isAudit = rec != null && StringUtil.isNotBlank(rec.getAuditStatusId());
        model.addAttribute("cannotOperSwitch", (true || isAudit));
        model.addAttribute("isAudit", isAudit);

        return "res/shfx/viewData";
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
            return "res/shfx/saveData";
        }

        if (StringUtil.isEmpty(resultFlow)) {
            model.addAttribute("resultId", "3010904");
            model.addAttribute("resultType", "排班标识符为空");
            return "res/shfx/saveData";
        }
        if (StringUtil.isEmpty(recTypeId)) {
            model.addAttribute("resultId", "3010906");
            model.addAttribute("resultType", "功能标识为空");
            return "res/shfx/saveData";
        }
        SysUser user = shfxAppBiz.readSysUser(userFlow);
        model.addAttribute("user", user);

        //获取当前操作人
        String operUserFlow = "";
        //如果是11查出dataFlow
        ResSchProcessExpress rec = null;
        if (StringUtil.isEmpty(dataFlow)) {
            String processFlow = "";
            ResDoctorSchProcess process = shfxStudentBiz.getProcessByResult(resultFlow);
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
        if (rec != null) {
            recTypeId = rec.getRecTypeId();
        }

        dataFlow = expressBiz.editExpress(dataFlow, operUserFlow, resultFlow, recTypeId, GlobalConstant.RES_SHFX_DEFAULT_FORM, request,GlobalConstant.RES_ROLE_SCOPE_TEACHER,null, "");

        //审核数据
        expressBiz.auditDate(userFlow, dataFlow);

        return "res/shfx/saveData";
    }

    /**
     * 评分详情
     * @return
     */
    @RequestMapping(value={"/view360SkillEvl"},method={RequestMethod.GET,RequestMethod.POST})
    public String view360SkillEvl(String userFlow,String roleId,String docFlow,String recTypeId,
                                  String recFlow,String resultFlow,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "3010601");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/shfx/success";
        }
        if(StringUtil.isEmpty(roleId)){
            model.addAttribute("resultId", "3010602");
            model.addAttribute("resultType", "用户角色为空");
            return "res/shfx/success";
        }

        if(StringUtil.isEmpty(docFlow)){
            model.addAttribute("resultId", "3010606");
            model.addAttribute("resultType", "学员标识符为空");
            return "res/shfx/success";
        }
        if(StringUtil.isEmpty(resultFlow)){
            model.addAttribute("resultId", "3010606");
            model.addAttribute("resultType", "科室流水号为空");
            return "res/shfx/success";
        }

        //判断角色
        boolean tSwitch = "Teacher".equals(roleId);
        boolean sSwitch = "Secretary".equals(roleId)||"Head".equals(roleId);

        //当前轮转计划信息
        SchArrangeResult result = shfxStudentBiz.searcheDocResult(null,resultFlow);
        if(result!=null){
            model.addAttribute("result",result);
        }
        //读取相关信息
        String processFlow = "";
        ResDoctorSchProcess process = shfxStudentBiz.getProcessByResult(resultFlow);
        if(process!=null){
            //登记表单的科室
            processFlow = process.getProcessFlow();
            model.addAttribute("processFlow",processFlow);
            model.addAttribute("doctorSchProcess",process);
        }
        DeptTeacherGradeInfo gradeInfo = resGradeBiz.readByFlow(recFlow);
        model.addAttribute("rec",gradeInfo);
        //解析登记信息的xml
        Object formDataMap = null;
        if(gradeInfo!=null&&StringUtil.isNotBlank(gradeInfo.getRecContent()))
        {
            formDataMap = XmlUtil.parseXmlStr(gradeInfo.getRecContent());
            //如果读取到数据就使用数据本身的funcFlow
            recTypeId = gradeInfo.getRecTypeId();
        }
        model.addAttribute("formDataMap",formDataMap);
        if(StringUtil.isEmpty(recTypeId)){
            model.addAttribute("resultId", "3010604");
            model.addAttribute("resultType", "评价类型标识为空");
            return "res/shfx/success";
        }
        //不可操作开关
        boolean isAudit =false;
        if(gradeInfo!=null&& StringUtil.isNotBlank(gradeInfo.getRecFlow())){
            isAudit =true;
        }else if(("Paramedic_360".equals(recTypeId)||"Patient_360".equals(recTypeId))&&tSwitch){
            isAudit =true;
        }else if("Teacher_360".equals(recTypeId)&&sSwitch){
            isAudit =true;
        }
        model.addAttribute("cannotOperSwitch",(tSwitch || isAudit));
        model.addAttribute("isAudit",isAudit);

        //老师和教秘的控件开关
        model.addAttribute("tSwitch",tSwitch);
        model.addAttribute("sSwitch",sSwitch);
        model.addAttribute("roleFlag",roleId);
        model.addAttribute("formFileName", Rec360TypeEnum.getNameById(recTypeId));
        switch (recTypeId) {
            case "Teacher_360":
                recTypeId = "360_teacher";
                break;
            case "Paramedic_360":
                recTypeId = "360_paramedic";
                break;
            case "Patient_360":
                recTypeId = "360_patient";
                break;
            default:
                break;
        }
        String path="jxms/evalForm360/"+recTypeId;
        model.addAttribute("path",path);
        return "res/shfx/skillAnd360Form";
    }

    /**
     * 保存技能评价表单
     * @param recFlow 登记表流水号
     * @param resultFlow 轮转流水号
     * @param operUserFlow
     * @return
     */
    @RequestMapping(value="/saveSkillEvlForm",method={RequestMethod.GET,RequestMethod.POST})
    public String saveSkillEvlForm(String userFlow,String recTypeId,String recFlow,String resultFlow,String operUserFlow, HttpServletRequest request,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "保存成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/shfx/success";
        }
        if (StringUtil.isBlank(resultFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识为空");
            return "res/shfx/success";
        }
        if (StringUtil.isEmpty(operUserFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "学生标识为空");
            return "res/shfx/success";
        }
        if (StringUtil.isEmpty(recTypeId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "评价类型标识为空");
            return "res/shfx/success";
        }
        String dataFlow= shfxAppBiz.saveGradeInfo(userFlow,recFlow,operUserFlow,resultFlow,recTypeId,request,GlobalConstant.RES_SHFX_DEFAULT_FORM);
        if(dataFlow!=null&&dataFlow.startsWith("error:"))
        {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", dataFlow.split(":")[1]);
            return "res/shfx/success";
        }
        return "res/shfx/success";
    }
    /**
     * 临床技能评价列表
     */
    @RequestMapping(value="/skillEvlList")
    public String skillEvlList(String roleId,String userFlow,String userName,String recForm,
                               Integer pageIndex,Integer pageSize,Model model) throws ParseException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/shfx/teacher/skillEvlList";
        }
        if(StringUtil.isBlank(roleId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/shfx/teacher/skillEvlList";
        }
        if(StringUtil.isBlank(recForm)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "评价表单类型为空");
            return "res/shfx/teacher/skillEvlList";
        }
        //验证用户是否存在
        SysUser userinfo = shfxAppBiz.readSysUser(userFlow);
        if(userinfo==null){
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/shfx/teacher/skillEvlList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/shfx/teacher/skillEvlList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/shfx/teacher/skillEvlList";
        }
        List<Map<String,String>> resDoctorSchProcess=null;
        Map<String, Object> schArrangeResultMap = new HashMap<String, Object>();
        schArrangeResultMap.put("teacherUserFlow", userFlow);
        schArrangeResultMap.put("userName", userName);
//		后台配置是否校验权限时间
        String isPermitOpen = shfxAppBiz.getCfgByCode("res_permit_open_doc");
        schArrangeResultMap.put("isPermitOpen",isPermitOpen);
        PageHelper.startPage(pageIndex, pageSize);
        resDoctorSchProcess=teacherBiz.schDoctorSchProcessQuery(schArrangeResultMap);

        Map<String,Object> recFormMap=new HashMap<String,Object>();//是否存在评价表单
        for (Map<String, String> map : resDoctorSchProcess) {
            Map<String, Object> itemsMap=new HashMap<>();
            itemsMap.put("processFlow",map.get("processFlow"));
            itemsMap.put("recFormId",recForm);
            itemsMap.put("createUserFlow",userFlow);
            List<DeptTeacherGradeInfo> dataList=resGradeBiz.searchResGradeByItems(itemsMap);
            if (dataList!=null&&dataList.size()>0) {
                recFormMap.put(map.get("processFlow"),GlobalConstant.FLAG_Y);
            }
        }
        model.addAttribute("recFormMap", recFormMap);
        model.addAttribute("list",resDoctorSchProcess);
        model.addAttribute("dataCount", PageHelper.total);
        //技能表单列表
        List<RecSkillTypeEnum> enums=new ArrayList<>();
        if(StringUtil.isNotBlank(recForm)){
            for(RecSkillTypeEnum en:RecSkillTypeEnum.values()){
                if(en.getType().equals(recForm)){
                    enums.add(en);
                }
            }
        }
        model.addAttribute("enums",enums);
        return "res/shfx/teacher/skillEvlList";
    }
    /**
     * 查看学员已有的技能评价
     */
    @RequestMapping(value="/stuSkillEvlList")
    public String stuSkillEvlList(String userFlow,String doctorFlow,String processFlow,String recForm,
                                  Integer pageIndex,Integer pageSize,Model model) throws ParseException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/shfx/teacher/stuSkillEvlList";
        }
        if(StringUtil.isBlank(doctorFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "学生标识符为空");
            return "res/shfx/teacher/stuSkillEvlList";
        }
        if(StringUtil.isBlank(processFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/shfx/teacher/stuSkillEvlList";
        }
        //验证用户是否存在
        SysUser userinfo = shfxAppBiz.readSysUser(userFlow);
        if(userinfo==null){
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/shfx/teacher/stuSkillEvlList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/shfx/teacher/stuSkillEvlList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/shfx/teacher/stuSkillEvlList";
        }
        Map<String, Object> itemsMap=new HashMap<>();
        itemsMap.put("processFlow",processFlow);
        itemsMap.put("recFormId",recForm);
        itemsMap.put("createUserFlow",userFlow);
        PageHelper.startPage(pageIndex, pageSize);
        List<DeptTeacherGradeInfo> dataList=resGradeBiz.searchResGradeByItems(itemsMap);

        model.addAttribute("list",dataList);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/shfx/teacher/stuSkillEvlList";
    }
    /**
     * 技能评价表选择列表
     */
    @RequestMapping(value="/skillTypeList")
    public String skillTypeList(String userFlow,String resultFlow,String doctorFlow,String recForm,Model model) throws ParseException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/shfx/teacher/skillTypeList";
        }
        if(StringUtil.isBlank(doctorFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "学生标识符为空");
            return "res/shfx/teacher/skillTypeList";
        }
        if(StringUtil.isBlank(resultFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/shfx/teacher/skillTypeList";
        }
        if(StringUtil.isBlank(recForm)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "评价表单类型为空");
            return "res/shfx/teacher/skillTypeList";
        }
        //验证用户是否存在
        SysUser userinfo = shfxAppBiz.readSysUser(userFlow);
        if(userinfo==null){
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/shfx/teacher/skillTypeList";
        }

        //技能表单列表
        List<RecSkillTypeEnum> enums=new ArrayList<>();
        if(StringUtil.isNotBlank(recForm)){
            for(RecSkillTypeEnum en:RecSkillTypeEnum.values()){
                if(en.getType().equals(recForm)){
                    enums.add(en);
                }
            }
        }
        model.addAttribute("enums",enums);
        model.addAttribute("recForm",recForm);
        model.addAttribute("resultFlow",resultFlow);
        model.addAttribute("doctorFlow",doctorFlow);
        return "res/shfx/teacher/skillTypeList";
    }
    /**
     * 360评价列表
     */
    @RequestMapping(value="/evl360List")
    public String evl360List(String roleId,String userFlow,String userName,String recForm,
                             Integer pageIndex,Integer pageSize,Model model) throws ParseException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/shfx/teacher/evl360List";
        }
        if(StringUtil.isBlank(roleId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色ID为空");
            return "res/shfx/teacher/evl360List";
        }
        if(StringUtil.isBlank(recForm)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "评价表单类型为空");
            return "res/shfx/teacher/skillEvlList";
        }
        //验证用户是否存在
        SysUser userinfo = shfxAppBiz.readSysUser(userFlow);
        if(userinfo==null){
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/shfx/teacher/evl360List";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/shfx/teacher/evl360List";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/shfx/teacher/evl360List";
        }
        List<Map<String,String>> resDoctorSchProcess=null;
        Map<String, Object> schArrangeResultMap = new HashMap<String, Object>();
        schArrangeResultMap.put("teacherUserFlow", userFlow);
        schArrangeResultMap.put("userName", userName);
//		后台配置是否校验权限时间
        String isPermitOpen = shfxAppBiz.getCfgByCode("res_permit_open_doc");
        schArrangeResultMap.put("isPermitOpen",isPermitOpen);
        PageHelper.startPage(pageIndex, pageSize);
        resDoctorSchProcess=teacherBiz.schDoctorSchProcessQuery(schArrangeResultMap);

        Map<String,Object> recFormMap=new HashMap<String,Object>();//是否存在评价表单
        Map<String,Object> formScoreMap=new HashMap<String,Object>();//列表总分展示
        for (Map<String, String> map : resDoctorSchProcess) {
            Map<String, Object> itemsMap=new HashMap<>();
            itemsMap.put("processFlow",map.get("processFlow"));
            itemsMap.put("recFormId",recForm);
            itemsMap.put("createUserFlow",userFlow);
            List<DeptTeacherGradeInfo> dataList=resGradeBiz.searchResGradeByItems(itemsMap);
            Map<String , String> formDataMap=new HashMap<>();
            if (dataList!=null&&dataList.size()>0) {
                recFormMap.put(map.get("processFlow"),dataList.get(0).getRecFlow());
                if(StringUtil.isNotBlank(dataList.get(0).getRecContent())){
                    formDataMap= XmlUtil.parseXmlStr(dataList.get(0).getRecContent());
                    if(Rec360TypeEnum.Teacher_360.getId().equals(recForm)){
                        formScoreMap.put(map.get("processFlow"), formDataMap.get("part10"));
                    }
                    if(Rec360TypeEnum.Paramedic_360.getId().equals(recForm)){
                        formScoreMap.put(map.get("processFlow"), formDataMap.get("part11"));
                    }
                }
            }
        }
        model.addAttribute("recFormMap", recFormMap);
        model.addAttribute("formScoreMap", formScoreMap);
        model.addAttribute("list",resDoctorSchProcess);
        model.addAttribute("dataCount", PageHelper.total);
        model.addAttribute("recForm",recForm);
        return "res/shfx/teacher/evl360List";
    }
}

