package com.pinde.res.ctrl.sctcm120;


import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.commom.enums.ResRecTypeEnum;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.common.IResPowerCfgBiz;
import com.pinde.res.biz.hbres.IFileBiz;
import com.pinde.res.biz.sctcm120.ISctcm120AppBiz;
import com.pinde.res.biz.sctcm120.ISctcm120StudentBiz;
import com.pinde.res.biz.sctcm120.ISctcm120TeacherBiz;
import com.pinde.res.biz.stdp.IResSchProcessExpressBiz;
import com.pinde.core.commom.enums.ResAssessTypeEnum;
import com.pinde.core.commom.enums.AbsenceTypeEnum;
import com.pinde.core.commom.enums.ResDoctorKqStatusEnum;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/sctcm120/teacher")
public class Sctcm120TeacherController {
    private static Logger logger = LoggerFactory.getLogger(Sctcm120TeacherController.class);

    @Autowired
    private ISctcm120StudentBiz sctcm120StudentBiz;
    @Autowired
    private ISctcm120AppBiz sctcm120AppBiz;
    @Autowired
    private ISctcm120TeacherBiz teacherBiz;
    @Autowired
    private IResPowerCfgBiz resPowerCfgBiz;
    @Autowired
    private IResSchProcessExpressBiz expressBiz;
    @Autowired
    private IFileBiz fileBiz;

    @ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
        logger.error(this.getClass().getCanonicalName() + " some error happened", e);
        return "res/sctcm120/500";
    }

    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET})
    public String test() {
        return "res/sctcm120/teacher/test";
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
        return "/res/sctcm120/teacher/test";
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
            return "res/sctcm120/teacher/doctorList";
        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "3030102");
            model.addAttribute("resultType", "起始页为空");
            return "res/sctcm120/teacher/doctorList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "3030103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/sctcm120/teacher/doctorList";
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
        String uploadBaseUrl = sctcm120AppBiz.getCfgByCode("upload_base_url");
        model.addAttribute("uploadBaseUrl", uploadBaseUrl);

        //数据数量
        model.addAttribute("dataCount", PageHelper.total);

        return "res/sctcm120/teacher/doctorList";
    }

    @RequestMapping(value = {"/funcList"}, method = {RequestMethod.GET})
    public String funcList(String userFlow, String doctorFlow, HttpServletRequest request, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3030201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/sctcm120/teacher/funcList";
        }
        if (StringUtil.isEmpty(doctorFlow)) {
            model.addAttribute("resultId", "3030202");
            model.addAttribute("resultType", "学员标识符为空");
            return "res/sctcm120/teacher/funcList";
        }
        return "res/sctcm120/teacher/funcList";
    }

    @RequestMapping(value = {"/index"}, method = {RequestMethod.GET})
    public String index(String userFlow, HttpServletRequest request, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3030201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/sctcm120/teacher/index";
        }
        SysUser user = sctcm120AppBiz.readSysUser(userFlow);
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
        List<SysUserRole> userRoles = sctcm120AppBiz.getSysUserRole(userFlow);
        if(userRoles!=null&&userRoles.size()>0) {
            //获取当前配置的老师角色
            String teacherRole = sctcm120AppBiz.getCfgCode("res_teacher_role_flow");
            //获取当前配置的科主任角色
            String headRole = sctcm120AppBiz.getCfgCode("res_head_role_flow");
            List<Map<String,String>> roles=new ArrayList<>();
            for (SysUserRole sur : userRoles) {
                Map<String,String> map=new HashMap<>();
                String ur = sur.getRoleFlow();
                if (headRole.equals(ur)) {
                    map.put("roleId", "Head");
                    map.put("roleName", "科主任");
                    roles.add(map);
                }
//                if (teacherRole.equals(ur)) {
//                    map.put("roleId", "Teacher");
//                    map.put("roleName", "老师");
//                    roles.add(map);
//                }
            }
            model.addAttribute("roles",roles);
        }

        String miniFlag = sctcm120AppBiz.getCfgByCode("res_Mini_CEX_form_flag");
        String dopsFlag = sctcm120AppBiz.getCfgByCode("res_DOPS_form_flag");

        model.addAttribute("miniFlag",miniFlag);
        model.addAttribute("dopsFlag",dopsFlag);

        return "res/sctcm120/teacher/index";
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
            return "res/sctcm120/teacher/studentList";
        }
        //验证用户是否存在
        SysUser userinfo = sctcm120AppBiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/sctcm120/teacher/studentList";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/sctcm120/teacher/studentList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/sctcm120/teacher/studentList";
        }

        List<Map<String, String>> resDoctorSchProcess = null;
        Map<String, Object> schArrangeResultMap = new HashMap<String, Object>();
        schArrangeResultMap.put("teacherUserFlow", userFlow);
        schArrangeResultMap.put("userName", studentName);
        schArrangeResultMap.put("biaoJi", biaoJi);//如果为Y就是查询带审核的学员列表
//		后台配置是否校验权限时间
        String isPermitOpen = sctcm120AppBiz.getCfgByCode("res_permit_open_doc");
        schArrangeResultMap.put("isPermitOpen",isPermitOpen);
        //湖北现在已经需要填写的数据类型
        List<String> typeId = new ArrayList<>();
        List<GeneralEnum> enumList = new ArrayList<>();
        if (StringUtil.isBlank(dataType) || dataType.equals("fiveData")) {
            for (ResRecTypeEnum e : ResRecTypeEnum.values()) {
                String cfgValue = sctcm120AppBiz.getCfgByCode("res_registry_type_" + e.getId());
                if (StringUtil.isNotBlank(cfgValue) && cfgValue.equals(GlobalConstant.FLAG_Y)) {
                    if (StringUtil.isNotBlank(dataType) && dataType.equals("fiveData")) {
                        typeId.add(e.getId());
                    }
                    enumList.add(e);
                }
            }
            if (StringUtil.isBlank(dataType)) {
                String miniFlag = sctcm120AppBiz.getCfgByCode("res_Mini_CEX_form_flag");
                String dopsFlag = sctcm120AppBiz.getCfgByCode("res_DOPS_form_flag");
                if(GlobalConstant.FLAG_Y.equals(dopsFlag)) {
                    enumList.add(ResRecTypeEnum.DOPS);
                }
                if(GlobalConstant.FLAG_Y.equals(miniFlag)) {
                    enumList.add(ResRecTypeEnum.Mini_CEX);
                }
                enumList.add(ResRecTypeEnum.AfterSummary);
                enumList.add(ResRecTypeEnum.AfterEvaluation);
                enumList.add(ResRecTypeEnum.DiscipleSummary);
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
            }
            List<String> recTypeIds=new ArrayList<>();
            recTypeIds.add(ResRecTypeEnum.DOPS.getId());
            recTypeIds.add(ResRecTypeEnum.Mini_CEX.getId());
            recTypeIds.add(ResRecTypeEnum.AfterSummary.getId());
            recTypeIds.add(ResRecTypeEnum.AfterEvaluation.getId());
            recTypeIds.add(ResRecTypeEnum.DiscipleSummary.getId());
            List<ResSchProcessExpress> expressList = expressBiz.getDocexpressList(map.get("processFlow"),  recTypeIds);
            for(ResSchProcessExpress express:expressList)
            {
                String key =express.getProcessFlow()+express.getRecTypeId();
                resRecMap.put(key, express);
            }
        }
        model.addAttribute("resRecMap", resRecMap);
        //获取访问路径前缀
        String uploadBaseUrl = sctcm120AppBiz.getCfgByCode("upload_base_url");
        model.addAttribute("uploadBaseUrl", uploadBaseUrl);
        model.addAttribute("list", resDoctorSchProcess);
        model.addAttribute("dataCount", PageHelper.total);
        model.addAttribute("biaoJi", biaoJi);
        return "res/sctcm120/teacher/studentList";
    }

    @RequestMapping(value = {"/leaveAuditList"}, method = {RequestMethod.POST})
    public String leaveAuditList(String userFlow, String statusId, Integer pageIndex, Integer pageSize, Model model, String biaoJi) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/sctcm120/teacher/leaveAuditList";
        }
        if (StringUtil.isBlank(statusId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "状态标识符为空");
            return "res/sctcm120/teacher/leaveAuditList";
        }
        if (!"Auditing".equals(statusId)&&!"Audited".equals(statusId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "状态标识符错误只能是Auditing或Audited");
            return "res/sctcm120/teacher/leaveAuditList";
        }
        //验证用户是否存在
        SysUser userinfo = sctcm120AppBiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/sctcm120/teacher/leaveAuditList";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/sctcm120/teacher/leaveAuditList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/sctcm120/teacher/leaveAuditList";
        }

        List<String> notStatus=new ArrayList<>();
        notStatus.add(ResDoctorKqStatusEnum.Revoke.getId());
        Map<String,Object> param=new HashMap<>();
        param.put("teacherFlow",userFlow);
        param.put("statusId",statusId);
        param.put("orgFlow",userinfo.getOrgFlow());
        param.put("notStatus",notStatus);
        PageHelper.startPage(pageIndex, pageSize);
        List<ResDoctorKq> kqList = teacherBiz.searchResDoctorKq(param);
        model.addAttribute("kqList",kqList);
        model.addAttribute("dataCount", PageHelper.total);

        param.put("statusId","");
        List<ResDoctorKq> list = teacherBiz.searchResDoctorKq(param);
        int auditing=0;
        int audited=0;
        if(list!=null&&list.size()>0)
        {
            for(ResDoctorKq kq:list)
            {
                if(StringUtil.isNotBlank(kq.getTeacherAgreeFlag()))
                {
                    audited++;
                }else{
                    auditing++;
                }
            }
        }
        model.addAttribute("audited",audited);
        model.addAttribute("auditing",auditing);
        return "res/sctcm120/teacher/leaveAuditList";
    }

    @RequestMapping(value={"/showLeave"},method = {RequestMethod.POST})
    public  String showLeave(String userFlow, String recordFlow,Model model)
    {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/sctcm120/teacher/showLeave";
        }
        if(StringUtil.isBlank(recordFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/sctcm120/teacher/showLeave";
        }

        ResDoctorKq kq = sctcm120StudentBiz.readResDoctorKq(recordFlow);
        if(kq==null)
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为错误");
            return "res/sctcm120/teacher/showLeave";
        }
        model.addAttribute("kq",kq);
        List<PubFile> files=fileBiz.findFileByTypeFlow("ResDoctorKqFile",recordFlow);
        model.addAttribute("files",files);

        //获取访问路径前缀
        String uploadBaseUrl = sctcm120AppBiz.getCfgCode("upload_base_url");
        model.addAttribute("uploadBaseUrl",uploadBaseUrl);

        return "res/sctcm120/teacher/showLeave";
    }
    @RequestMapping(value={"/auditLeave"},method = {RequestMethod.POST})
    public  String auditLeave(String userFlow, String recordFlow,String teacherAgreeFlag,String auditInfo,Model model)
    {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/sctcm120/success";
        }
        //验证用户是否存在
        SysUser userinfo = sctcm120AppBiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/sctcm120/success";
        }
        if(StringUtil.isBlank(recordFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/sctcm120/success";
        }
        if(StringUtil.isBlank(teacherAgreeFlag)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "审核状态为空");
            return "res/sctcm120/success";
        }
        if (!"Y".equals(teacherAgreeFlag)&&!"N".equals(teacherAgreeFlag)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "审核状态只能是Y通过或N不通过");
            return "res/sctcm120/success";
        }
        if("N".equals(teacherAgreeFlag)&&StringUtil.isBlank(auditInfo)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "审核信息为空");
            return "res/sctcm120/success";
        }
        ResDoctorKq kq = sctcm120StudentBiz.readResDoctorKq(recordFlow);
        if(kq==null)
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为错误");
            return "res/sctcm120/success";
        }
        if(StringUtil.isNotBlank(kq.getTeacherAgreeFlag()))
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "此请假信息已审核，请刷新列表页");
            return "res/sctcm120/success";
        }
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = sdf0.format(new Date());
        kq.setTeacherAuditTime(time);
        kq.setTeacherAgreeFlag(teacherAgreeFlag);
        kq.setTeacherAuditInfo(auditInfo);
        if("Y".equals(kq.getTeacherAgreeFlag())){
            kq.setAuditStatusId(ResDoctorKqStatusEnum.TeacherPass.getId());
            kq.setAuditStatusName(ResDoctorKqStatusEnum.TeacherPass.getName());
        }else{
            kq.setAuditStatusId(ResDoctorKqStatusEnum.TeacherUnPass.getId());
            kq.setAuditStatusName(ResDoctorKqStatusEnum.TeacherUnPass.getName());
        }

        int n=sctcm120StudentBiz.editResDoctorKq(kq,userinfo,kq);
        if(n==0)
        {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "审核失败");
            return "res/sctcm120/success";
        }

        return "res/sctcm120/success";
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
            return "res/sctcm120/teacher/dataNoAudit";
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "学生标识符为空");
            return "res/sctcm120/teacher/dataNoAudit";
        }
        if (StringUtil.isBlank(processFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/sctcm120/teacher/dataNoAudit";
        }
        if (StringUtil.isBlank(resultFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "排班标识符为空");
            return "res/sctcm120/teacher/dataNoAudit";
        }
        if (StringUtil.isBlank(dataType)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型为空");
            return "res/sctcm120/teacher/dataNoAudit";
        }
        //验证用户是否存在
        SysUser userinfo = sctcm120AppBiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/sctcm120/teacher/dataNoAudit";
        }
        //湖北现在已经需要填写的数据类型
        List<String> typeId = new ArrayList<>();
        List<GeneralEnum> enumList = new ArrayList<>();
        if (dataType.equals("fiveData")) {
            for (ResRecTypeEnum e : ResRecTypeEnum.values()) {
                String cfgValue = sctcm120AppBiz.getCfgByCode("res_registry_type_" + e.getId());
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
            enumList.add(ResRecTypeEnum.DiscipleSummary);
            typeId.add(ResRecTypeEnum.AfterSummary.getId());
            typeId.add(ResRecTypeEnum.AfterEvaluation.getId());
            typeId.add(ResRecTypeEnum.DiscipleSummary.getId());
            model.addAttribute("enumList", enumList);
        } else {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型不正确");
            return "res/sctcm120/teacher/dataNoAudit";
        }
        Map<String, Object> param = new HashMap<>();
        param.put("teaFlow", userFlow);
        param.put("docFlow", docFlow);
        param.put("processFlow", processFlow);
        param.put("recTypes", typeId);
        param.put("resultFlow", resultFlow);
        param.put("dataType", dataType);
        param.put("isSctcm120", "Y");
        List<Map<String, Object>> list = teacherBiz.findDataNoAudit(param);
        for (Map<String, Object> map : list) {
            map.put("recTypeName", ResRecTypeEnum.getNameById((String) map.get("recTypeId")));
        }
        model.addAttribute("list", list);
        return "res/sctcm120/teacher/dataNoAudit";
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
            return "res/sctcm120/teacher/recDataList";
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "学生标识符为空");
            return "res/sctcm120/teacher/recDataList";
        }
        if (StringUtil.isBlank(resultFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转计划标识符为空");
            return "res/sctcm120/teacher/recDataList";
        }
        if (StringUtil.isBlank(processFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/sctcm120/teacher/recDataList";
        }
        if (StringUtil.isBlank(recType)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型为空");
            return "res/sctcm120/teacher/recDataList";
        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/sctcm120/teacher/recDataList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/sctcm120/teacher/recDataList";
        }

        Map<String, Object> processPerMap = sctcm120StudentBiz.getRegPer(0, docFlow, resultFlow);
        model.addAttribute("processPerMap", processPerMap);
        PageHelper.startPage(pageIndex, pageSize);
        List<ResRec> recList = teacherBiz.searchRecByProcessAndRecType(processFlow, docFlow, recType, biaoJi);
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        for (ResRec rec : recList) {
            String recContent = rec.getRecContent();
            Map<String, String> formDataMap = sctcm120AppBiz.parseRecContent(recContent);
            formDataMap.put("dataFlow", rec.getRecFlow());
            formDataMap.put("auditId", rec.getAuditStatusId());
            formDataMap.put("operTime", DateUtil.transDate(rec.getOperTime()));
            dataList.add(formDataMap);
        }
        model.addAttribute("dataList", dataList);
        model.addAttribute("dataCount", PageHelper.total);
        List<ResRec> noAuditList = teacherBiz.searchRecByProcessAndRecType(processFlow, docFlow, recType, "Y");
        int count = 0;
        if (noAuditList != null) {
            count = noAuditList.size();
        }
        model.addAttribute("notAuditNum", count);
        return "res/sctcm120/teacher/recDataList";
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
            return "res/sctcm120/teacher/resRecDetail";
        }
        if (StringUtil.isBlank(recFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/sctcm120/teacher/resRecDetail";
        }
        if (StringUtil.isBlank(recType)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型为空");
            return "res/sctcm120/teacher/resRecDetail";
        }
        if (StringUtil.isBlank(resultFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "排班流水号为空");
            return "res/sctcm120/teacher/resRecDetail";
        }
        //验证用户是否存在
        SysUser userinfo = sctcm120AppBiz.readSysUser(userFlow);
        if (userinfo == null) {
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "用户不存在");
            return "res/sctcm120/teacher/resRecDetail";
        }
        ResRec rec = teacherBiz.readResRec(recFlow);
        String recContent = rec.getRecContent();
        //获取当前排班计划的科室 所有子项
        //		SchArrangeResult result = sctcm120StudentBiz.searcheDocResult(null,resultFlow);
        //		String deptFlow="";
        //		if(result!=null){
        //			String standardDeptId = result.getStandardDeptId();
        //			String standardGroupFlow = result.getStandardGroupFlow();
        //			SchRotationDept rotationDept = sctcm120AppBiz.getRotationDeptByResult(standardGroupFlow,standardDeptId);
        //			if(rotationDept!=null){
        //				deptFlow = rotationDept.getRecordFlow();
        //			}
        //		}
        //_inputList(userFlow, deptFlow, recType,cataFlow, model);
        Map<String, String> formDataMap = sctcm120AppBiz.parseRecContent(recContent);
        formDataMap.put("auditId", rec.getAuditStatusId());
        model.addAttribute("resultData", formDataMap);
        model.addAttribute("isOther", GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(cataFlow));
        return "res/sctcm120/teacher/resRecDetail";
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
            return "res/sctcm120/success";
        }
        if (StringUtil.isBlank(docFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "学生标识符为空");
            return "res/sctcm120/success";
        }
        if (StringUtil.isBlank(processFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "轮转标识符为空");
            return "res/sctcm120/success";
        }
        //rec类型转换一下
        if (StringUtil.isBlank(recType)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型为空");
            return "res/sctcm120/success";
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
                return "res/sctcm120/success";
            }
        } else {
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "无审核数据");
            return "res/sctcm120/success";
        }
        return "res/sctcm120/success";
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
            return "res/sctcm120/success";
        }
        if (StringUtil.isBlank(recFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/sctcm120/success";
        }
        if (StringUtil.isBlank(statusId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "审核状态不能为空");
            return "res/sctcm120/success";
        }
        if (!statusId.equals("Y") && !statusId.equals("N")) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "审核状态只能是通过或不通过");
            return "res/sctcm120/success";
        }
        ResRec re = teacherBiz.readResRec(recFlow);
        if (StringUtil.isNotBlank(re.getAuditStatusId())) {
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "数据已审核");
            return "res/sctcm120/success";
        }
        int i = teacherBiz.auditRecDate(userFlow, recFlow, statusId);
        if (i == 0) {
            model.addAttribute("resultId", "310101");
            model.addAttribute("resultType", "数据已审核");
            return "res/sctcm120/success";
        }
        return "res/sctcm120/success";
    }


    private static final String COMMON_PATH = "teacher";

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
            return "res/sctcm120/viewData";
        }

        if (StringUtil.isEmpty(roleId)) {
            model.addAttribute("resultId", "3010602");
            model.addAttribute("resultType", "用户角色为空");
            return "res/sctcm120/viewData";
        }

        if (StringUtil.isEmpty(funcTypeId)) {
            model.addAttribute("resultId", "3010603");
            model.addAttribute("resultType", "功能类型为空");
            return "res/sctcm120/viewData";
        }

        if (StringUtil.isEmpty(funcFlow)) {
            model.addAttribute("resultId", "3010604");
            model.addAttribute("resultType", "功能标识为空");
            return "res/sctcm120/viewData";
        }

        if ("teacher".equals(roleId) && StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "3010605");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/sctcm120/viewData";
        }

        if ("Teacher".equals(roleId) && StringUtil.isEmpty(doctorFlow)) {
            model.addAttribute("resultId", "3010606");
            model.addAttribute("resultType", "学员标识符为空");
            return "res/sctcm120/viewData";
        }

        //科室流水号
        String resultFlow = deptFlow;

        //判断角色
        boolean sSwitch = "teacher".equals(roleId);
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
        SchArrangeResult result = sctcm120StudentBiz.searcheDocResult(null, resultFlow);
        if (result != null) {
            model.addAttribute("result", result);

            //读取要求
            if (StringUtil.isNotBlank(cataFlow)) {
                String standardDeptId = result.getStandardDeptId();
                String standardGroupFlow = result.getStandardGroupFlow();
                SchRotationDept rotationDept = sctcm120AppBiz.getRotationDeptByResult(standardGroupFlow, standardDeptId);
                if (rotationDept != null) {
                    String relRecordFlow = rotationDept.getRecordFlow();
                    req = sctcm120AppBiz.readReq(null, relRecordFlow, cataFlow);
                }
            }
        }

        //获取当前这条登记信息
        ResRec rec = null;
        if (StringUtil.isNotBlank(dataFlow)) {
            rec = sctcm120AppBiz.getRecByRecFlow(dataFlow);
            //如果是11模式根据类型和科室查询
        } else if ("dataInput11".equals(funcTypeId)) {
            String processFlow = "";
            ResDoctorSchProcess process = sctcm120StudentBiz.getProcessByResult(resultFlow);
            if (process != null) {
                //登记表单的科室
                processFlow = process.getProcessFlow();
            }
            rec = sctcm120AppBiz.getRecByRecType(processFlow, funcFlow);
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
            ResAssessCfg assessCfg = sctcm120AppBiz.getGradeTemplate(assessType);
            model.addAttribute("assessCfg", assessCfg);

            if (assessCfg != null) {
                String content = assessCfg.getCfgBigValue();
                //解析评分xml
                List<Map<String, Object>> assessMaps = sctcm120AppBiz.parseAssessCfg(content);
                model.addAttribute("assessMaps", assessMaps);
            }
        }

        String version="2.0";
        if (rec != null) {
            String content = rec.getRecContent();
            //解析登记信息的xml
            Object formDataMap = null;
            if (!isGrade) {
                formDataMap = sctcm120AppBiz.parseRecContent(content);
            } else {
                formDataMap = sctcm120AppBiz.parseDocGradeXml(content);
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

        return "res/sctcm120/viewData";
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
            return "res/sctcm120/viewData";
        }

        if (StringUtil.isEmpty(recType)) {
            model.addAttribute("resultId", "3010604");
            model.addAttribute("resultType", "数据类型标识为空");
            return "res/sctcm120/viewData";
        }
        if (StringUtil.isEmpty(resultFlow)) {
            model.addAttribute("resultId", "3010606");
            model.addAttribute("resultType", "计划标识符为空");
            return "res/sctcm120/viewData";
        }
        String path = "teacher";
        model.addAttribute("path", path);
        //当前轮转计划信息
        SchArrangeResult result = sctcm120StudentBiz.searcheDocResult(null, resultFlow);
        model.addAttribute("result", result);
        SysUser tea = sctcm120AppBiz.readSysUser(userFlow);
        model.addAttribute("tea", tea);

        //获取当前这条登记信息
        ResSchProcessExpress rec = null;
        if (StringUtil.isNotBlank(recFlow)) {
            rec = expressBiz.getExpressByRecFlow(recFlow);
        } else {
            String processFlow = "";
            ResDoctorSchProcess process = sctcm120StudentBiz.getProcessByResult(resultFlow);
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
            formDataMap = sctcm120AppBiz.parseRecContent(content);
            model.addAttribute("formDataMap", formDataMap);
            //如果读取到数据就使用数据本身的funcFlow
            recType = rec.getRecTypeId();
            version=rec.getRecVersion();
        }
        if (recType.equals(ResRecTypeEnum.AfterEvaluation.getId())) {
            Map<String, Object> maps=new HashMap<String, Object>();
            List<SchDoctorAbsence> doctorAbsences=sctcm120AppBiz.searchSchDoctorAbsenceByDoctorDept(result.getSchDeptFlow(), result.getDoctorFlow());
            model.addAttribute("schDoctorAbsenceList", doctorAbsences.size());
            for (SchDoctorAbsence schDoctorAbsence : doctorAbsences) {
                int Leave=0;
                int Sickleave=0;
                int Maternityleave=0;
                int Marriage=0;
                maps.put("leave", Leave);
                maps.put("sickleave", Sickleave);
                maps.put("maternityleave", Maternityleave);
                maps.put("marriage", Marriage);
                String intervalDay=schDoctorAbsence.getIntervalDay();
                int i=Integer.valueOf(intervalDay);
                if (AbsenceTypeEnum.Leave.getId().equals(schDoctorAbsence.getAbsenceTypeId())) {
                    Leave+=i;
                    maps.put("leave", Leave);
                }
                if (AbsenceTypeEnum.Sickleave.getId().equals(schDoctorAbsence.getAbsenceTypeId())) {
                    Sickleave+=i;
                    maps.put("sickleave", Sickleave);
                }
                if (AbsenceTypeEnum.Maternityleave.getId().equals(schDoctorAbsence.getAbsenceTypeId())) {
                    Maternityleave+=i;
                    maps.put("maternityleave", Maternityleave);
                }
                if (AbsenceTypeEnum.Marriage.getId().equals(schDoctorAbsence.getAbsenceTypeId())) {
                    Marriage+=i;
                    maps.put("marriage", Marriage);
                }
            }
            model.addAttribute("maps",maps);
            if(rec==null)
            {
                version="2.0";
            }
            recType=recType+"_"+version;
            ResDoctorSchProcess process = sctcm120AppBiz.readSchProcessByResultFlow(resultFlow);
            if (process != null) {

                ResSchProcessExpress aummary = expressBiz.getExpressByRecType(process.getProcessFlow(), ResRecTypeEnum.AfterSummary.getId());
                if(aummary==null||StringUtil.isBlank(aummary.getAuditStatusId()))
                {
                    model.addAttribute("resultId", "3010606");
                    model.addAttribute("resultType", "请先审核出科小结表！");
                    return "res/sctcm120/viewData";
                }
                String res_after_test_switch = sctcm120AppBiz.getCfgByCode("res_after_test_switch");
                String res_after_url_cfg = sctcm120AppBiz.getCfgByCode("res_after_url_cfg");
                //学员开通出科考试权限
                Map<String, String> paramMap = new HashMap();
                String cfgCode = "res_doctor_ckks_" + process.getUserFlow();
                paramMap.put("cfgCode", cfgCode);
                String isCkksFlag = resPowerCfgBiz.getResPowerCfg(paramMap);
                boolean testTypeFlag = false;
                if (GlobalConstant.FLAG_Y.equals(res_after_test_switch) && StringUtil.isNotBlank(res_after_url_cfg)
                        && GlobalConstant.FLAG_Y.equals(isCkksFlag)) {
                    testTypeFlag = true;
                }
                model.addAttribute("testTypeFlag", testTypeFlag);
                ResScore score = sctcm120AppBiz.getScoreByProcess(process.getProcessFlow());
                model.addAttribute("outScore", score);
                model.addAttribute("doctorSchProcess", process);
                model.addAttribute("currRegProcess", process);

                //百分比算法
                Map<String, Object> perMap = sctcm120StudentBiz.getRegPer(0, process.getUserFlow(), resultFlow, null, null, true);
                //System.out.println("======"+JSON.toJSON( perMap));
                model.addAttribute("perMap", perMap);

                String recTypeId=ResRecTypeEnum.CampaignRegistry.getId();
                List<ResRec> resRecList=sctcm120AppBiz.getRecByRecsType(process.getProcessFlow(),recTypeId);
                SchRotationDept schRotationDept=new SchRotationDept();
                List<SchRotationDeptReq> rotationDeptReqList=new ArrayList<SchRotationDeptReq>();
                    if (StringUtil.isNotBlank(result.getStandardGroupFlow())&& StringUtil.isNotBlank(result.getStandardDeptId())) {
                        schRotationDept= sctcm120AppBiz.searchGroupFlowAndStandardDeptIdQuery(result.getStandardGroupFlow(),result.getStandardDeptId());
                    }
                    if (StringUtil.isNotBlank(schRotationDept.getRecordFlow())) {
                        rotationDeptReqList=sctcm120AppBiz.searchDeptReqByRel(schRotationDept.getRecordFlow(),recTypeId);
                    }
                String hdxs="";
                Map<String, String> datasMap=new HashMap<String, String>();
                if(resRecList!=null) {
                    for (ResRec resRec : resRecList) {
                        String s = datasMap.get(resRec.getItemId());
                        int c = 0;
                        if (s == null) {
                            c = 0;
                        } else {
                            c = Integer.valueOf(s).intValue();
                        }
                        c++;
                        s = String.valueOf(c);
                        datasMap.put(resRec.getItemId(), s);
                    }
                }
                for (SchRotationDeptReq schRotationDeptReq : rotationDeptReqList) {
                    hdxs+= StringUtil.defaultIfEmpty(schRotationDeptReq.getItemName(), "")+"：" ;
                    hdxs+= StringUtil.defaultIfEmpty(datasMap.get(schRotationDeptReq.getItemId()),"0")+"次;";
                }
                model.addAttribute("hdxs", hdxs);
            }
        }
        model.addAttribute("funcFlow", recType);
        //不可操作开关
        boolean isAudit = rec != null && StringUtil.isNotBlank(rec.getAuditStatusId());
        model.addAttribute("cannotOperSwitch", (true || isAudit));
        model.addAttribute("isAudit", isAudit);

        return "res/sctcm120/viewData";
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
            return "res/sctcm120/saveData";
        }

        if (StringUtil.isEmpty(resultFlow)) {
            model.addAttribute("resultId", "3010904");
            model.addAttribute("resultType", "排班标识符为空");
            return "res/sctcm120/saveData";
        }
        if (StringUtil.isEmpty(recTypeId)) {
            model.addAttribute("resultId", "3010906");
            model.addAttribute("resultType", "功能标识为空");
            return "res/sctcm120/saveData";
        }
        SysUser user = sctcm120AppBiz.readSysUser(userFlow);
        model.addAttribute("user", user);

        //获取当前操作人
        String operUserFlow = "";
        //如果是11查出dataFlow
        ResSchProcessExpress rec = null;
        if (StringUtil.isEmpty(dataFlow)) {
            String processFlow = "";
            ResDoctorSchProcess process = sctcm120StudentBiz.getProcessByResult(resultFlow);
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

        dataFlow = expressBiz.editExpress(dataFlow, operUserFlow, resultFlow, recTypeId, GlobalConstant.RES_SCTCM123_DEFAULT_FORM, request,GlobalConstant.RES_ROLE_SCOPE_TEACHER,null,version);

        //审核数据
        expressBiz.auditDate(userFlow, dataFlow);

        return "res/sctcm120/saveData";
    }
}

