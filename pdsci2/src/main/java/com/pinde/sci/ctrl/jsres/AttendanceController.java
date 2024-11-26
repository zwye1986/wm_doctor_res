package com.pinde.sci.ctrl.jsres;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorKqBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResOrgAddressBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.ResDoctorKqMapper;
import com.pinde.sci.enums.jsres.TrainCategoryEnum;
import com.pinde.sci.enums.res.ResDoctorKqStatusEnum;
import com.pinde.sci.form.res.ResOrgAddressForm;
import com.pinde.sci.form.res.TimeSetFrom;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName AttendanceController
 * @Deacription 考勤请假Controller
 * @Author shengl
 * @Date 2021-01-05 09:46
 * @Version 1.0
 **/
@Controller
@RequestMapping("/jsres/attendanceNew")
public class AttendanceController extends GeneralController {

    @Autowired
    private IResOrgAddressBiz orgAddressBiz;
    @Autowired
    private IResDoctorKqBiz resDoctorKqBiz;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private ResDoctorKqMapper resDoctorKqMapper;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorProcessBiz resDoctorProcessBiz;

    /**
     * 设置签到地址
     * @param model
     * @return
     */
    @RequestMapping("/signinSet")
    public String signinSet(Model model){
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        List<ResOrgAddress> addressList = orgAddressBiz.readOrgAddress(orgFlow);
        List<ResOrgAddress> thisAddress = new ArrayList<>();
        List<ResOrgAddress> outsideAddress = new ArrayList<>();
        if(addressList!=null&&addressList.size()>0)
        {
            thisAddress = addressList.stream().filter(d -> "this".equals(d.getAddressType())).collect(Collectors.toList());
            outsideAddress = addressList.stream().filter(d -> "outside".equals(d.getAddressType())).collect(Collectors.toList());
        }
        model.addAttribute("outsideAddress", outsideAddress);
        model.addAttribute("thisAddress", thisAddress);
        model.addAttribute("addressList",addressList);
        return "res/addressNew/signinSetNew";
    }

    /**
     * 设置单个签到地址
     * @param model
     * @return
     */
    @RequestMapping("/oneAddress")
    public String oneAddress(Model model, String recordFlow){
        if(StringUtil.isNotBlank(recordFlow)) {
            ResOrgAddress orgAddress = orgAddressBiz.readOrgAddressByFlow(recordFlow);
            model.addAttribute("orgAddress", orgAddress);
        }
        return "res/addressNew/oneAddressNew";
    }

    /**
     * @Author shengl
     * @Description  保存
     * @Date  2021-01-05
     * @return java.lang.String
    **/
    @RequestMapping("/saveSigninSetList")
    @ResponseBody
    public String saveSigninSetList(@RequestBody ResOrgAddressForm bean){

        if(StringUtil.isBlank(bean.getOrgFlow()))
        {
            return "提交格式错误！";
        }
        if(!bean.getOrgFlow().equals(GlobalContext.getCurrentUser().getOrgFlow()))
        {
            return "当前登录人已变更，刷新页面！";
        }
        int result = orgAddressBiz.saveOrgAddresses(bean);
        if(result> GlobalConstant.ZERO_LINE){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }


    /**
     * @Author shengl
     * @Description // 请假审核配置
     * @Date  2021-01-05
     * @Param [model]
     * @return java.lang.String
    **/
    @RequestMapping(value="/timeSet")
    public String timeSet(Model model) {
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        List<ResKgCfg> cfgs = resDoctorKqBiz.readKqCfgList(orgFlow,"");
        Map<String, ResKgCfg> cfgMap=new HashMap<>();
        if(cfgs!=null)
        {
            for(ResKgCfg cfg:cfgs)
            {
                cfgMap.put(cfg.getDoctorTypeId()+cfg.getLessOrGreater(),cfg);
            }
        }
        model.addAttribute("cfgMap",cfgMap);
        return "res/addressNew/timeSetNew";
    }

    /**
     * @Author shengl
     * @Description // 保存请假时间配置
     * @Date  2021-01-12
    **/
    @RequestMapping(value="/saveTimeSet")
    @ResponseBody
    public String saveTimeSet(@RequestBody TimeSetFrom timeSetFrom) {
        String orgFlow = StringUtil.defaultIfEmpty(timeSetFrom.getOrgFlow(), GlobalContext.getCurrentUser().getOrgFlow()) ;
        if(timeSetFrom.getCfgs()==null)
        {
            return "请填写请假时间配置！";
        }
        timeSetFrom.setOrgFlow(orgFlow);
        int c=resDoctorKqBiz.saveKqCfgs(timeSetFrom);
        if(c==0)
        {
            return GlobalConstant.OPERATE_FAIL;
        }
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * @Author shengl
     * @Description // 请假审批列表  住院医师
     * @Date  2021-01-12
    **/
    @RequestMapping(value={"/leaveVerifyList/{roleFlag}"},method={RequestMethod.GET, RequestMethod.POST})
    public String leaveVerifyList(@PathVariable String roleFlag, ResDoctorKq kq, Integer currentPage, String sessionNumber,
                                  HttpServletRequest request, Model model, String schDeptFlow){
        Map<String,Object> paramMap = new HashMap<>();
        SysUser currUser = GlobalContext.getCurrentUser();
        paramMap.put("doctorName",kq.getDoctorName());
        paramMap.put("typeId",kq.getTypeId());
        paramMap.put("startDate",kq.getStartDate());
        paramMap.put("endDate",kq.getEndDate());
//        paramMap.put("sessionNumber",sessionNumber);
        paramMap.put("schDeptFlow",schDeptFlow);
        paramMap.put("roleFlag",roleFlag);
//        paramMap.put("orgFlow",currUser.getOrgFlow());
        String auditStatusId = kq.getAuditStatusId();
//        if (StringUtil.isBlank(auditStatusId)) {
//            auditStatusId = ResDoctorKqStatusEnum.Auditing.getId();
//        }
        paramMap.put("auditStatusId",auditStatusId);
        boolean auditRoleFlag = ResDoctorKqStatusEnum.Auditing.getId().equals(auditStatusId);
        if("teacher".equals(roleFlag)){//带教
            paramMap.put("teacherFlow",currUser.getUserFlow());
            if(auditRoleFlag){
                //待带教审核
//                paramMap.put("auditRoleNow","teacher");
                paramMap.put("auditStatusId",ResDoctorKqStatusEnum.Auditing.getId());
            }
//            else {
//                paramMap.put("teacherAgreeFlag",auditStatusId);
//            }
        }
        if("head".equals(roleFlag)){
            paramMap.put("headFlow",currUser.getUserFlow());
            if(auditRoleFlag){
                //待科主任审核
//                paramMap.put("auditRoleNow","head");
                paramMap.put("auditStatusId",ResDoctorKqStatusEnum.HeadAuditing.getId());
            }
//            else {
//                paramMap.put("headAgreeFlag",auditStatusId);
//            }
        }
        if("manager".equals(roleFlag)){
            paramMap.put("orgFlow",currUser.getOrgFlow());
//            paramMap.put("managerFlow",currUser.getUserFlow());
            if(auditRoleFlag){
                //待管理员审核
//                paramMap.put("auditRoleNow","admin");
                paramMap.put("auditStatusId",ResDoctorKqStatusEnum.ManagerAuditing.getId());
            }
//            else {
//                paramMap.put("managerAgreeFlag",auditStatusId);
//            }
        }
        List<String> notStatus=new ArrayList<>();
        notStatus.add(ResDoctorKqStatusEnum.Revoke.getId());
        notStatus.add(ResDoctorKqStatusEnum.BackLeave.getId());
        paramMap.put("notStatus",notStatus);
        if (currentPage != null) {
            currentPage = 1;
        }
        paramMap.put("trainingTypeId", TrainCategoryEnum.DoctorTrainingSpe.getId());
        PageHelper.startPage(currentPage,getPageSize(request));
        List<ResDoctorKq> kqList = resDoctorKqBiz.searchResDoctorKq(paramMap);
        model.addAttribute("kqList", kqList);
        model.addAttribute("roleFlag", roleFlag);
        return "res/leave/leaveVerifyList";
    }

    /**
     * @Author shengl
     * @Description // 请假审批列表
     * @Date  2021-01-12
     **/
    @RequestMapping(value={"/leaveVerifyListAcc/{roleFlag}"},method={RequestMethod.GET, RequestMethod.POST})
    public String leaveVerifyListAcc(@PathVariable String roleFlag, ResDoctorKq kq, Integer currentPage, String sessionNumber,
                                  HttpServletRequest request, Model model, String schDeptFlow){
        Map<String,Object> paramMap = new HashMap<>();
        SysUser currUser = GlobalContext.getCurrentUser();
        paramMap.put("doctorName",kq.getDoctorName());
        paramMap.put("typeId",kq.getTypeId());
        paramMap.put("startDate",kq.getStartDate());
        paramMap.put("endDate",kq.getEndDate());
//        paramMap.put("sessionNumber",sessionNumber);
        paramMap.put("schDeptFlow",schDeptFlow);
        paramMap.put("roleFlag",roleFlag);
//        paramMap.put("orgFlow",currUser.getOrgFlow());
        String auditStatusId = kq.getAuditStatusId();
//        if (StringUtil.isBlank(auditStatusId)) {
//            auditStatusId = ResDoctorKqStatusEnum.Auditing.getId();
//        }
        paramMap.put("auditStatusId",auditStatusId);
        boolean auditRoleFlag = ResDoctorKqStatusEnum.Auditing.getId().equals(auditStatusId);
        if("teacher".equals(roleFlag)){//带教
            paramMap.put("teacherFlow",currUser.getUserFlow());
            if(auditRoleFlag){
                //待带教审核
//                paramMap.put("auditRoleNow","teacher");
                paramMap.put("auditStatusId",ResDoctorKqStatusEnum.Auditing.getId());
            }
//            else {
//                paramMap.put("teacherAgreeFlag",auditStatusId);
//            }
        }
        if("head".equals(roleFlag)){
            paramMap.put("headFlow",currUser.getUserFlow());
            if(auditRoleFlag){
                //待科主任审核
//                paramMap.put("auditRoleNow","head");
                paramMap.put("auditStatusId",ResDoctorKqStatusEnum.HeadAuditing.getId());
            }
//            else {
//                paramMap.put("headAgreeFlag",auditStatusId);
//            }
        }
        if("manager".equals(roleFlag)){
            paramMap.put("orgFlow",currUser.getOrgFlow());
            paramMap.put("managerFlow",currUser.getUserFlow());
            if(auditRoleFlag){
                //待管理员审核
//                paramMap.put("auditRoleNow","admin");
                paramMap.put("auditStatusId",ResDoctorKqStatusEnum.ManagerAuditing.getId());
            }
//            else {
//                paramMap.put("managerAgreeFlag",auditStatusId);
//            }
        }
        List<String> notStatus=new ArrayList<>();
        notStatus.add(ResDoctorKqStatusEnum.Revoke.getId());
        notStatus.add(ResDoctorKqStatusEnum.BackLeave.getId());
        paramMap.put("notStatus",notStatus);
        if (currentPage != null) {
            currentPage = 1;
        }
        paramMap.put("trainingTypeId", TrainCategoryEnum.AssiGeneral.getId());
        PageHelper.startPage(currentPage,getPageSize(request));
        List<ResDoctorKq> kqList = resDoctorKqBiz.searchResDoctorKq(paramMap);
        model.addAttribute("kqList", kqList);
        model.addAttribute("roleFlag", roleFlag);
        return "res/leave/leaveVerifyListAcc";
    }

    /**
     * @Author shengl
     * @Description // 查询请假信息
     * @Date  2021-01-13
    **/
    @RequestMapping(value={"/queryleaveInfoById"},method={RequestMethod.GET, RequestMethod.POST})
    public String queryleaveInfoById(Model model, String recordFlow, String roleFlag, String type){
        if (StringUtil.isNotBlank(recordFlow)) {
            ResDoctorKq resDoctorKq = resDoctorKqMapper.selectByPrimaryKey(recordFlow);
            model.addAttribute("resDoctorKq",resDoctorKq);
            if (resDoctorKq != null) {
                List<PubFile> files = fileBiz.findFileByTypeFlow("ResDoctorKqFile",recordFlow);
                model.addAttribute("files",files);
            }
        }
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("type", type);
        // 查询请假审批记录
        List<ResDoctorKqLog> leaveLogs = resDoctorKqBiz.searchKqLogList(recordFlow,"Leave");
        model.addAttribute("leaveLogs",leaveLogs);
        // 查询销假审批记录
        List<ResDoctorKqLog> cancelLogs = resDoctorKqBiz.searchKqLogList(recordFlow,"Cancel");
        model.addAttribute("cancelLogs",cancelLogs);
        return "res/leave/queryleaveInfo";
    }

    /**
     * @Author shengl
     * @Description // 请假审核
     * @Date  2021-01-13
    **/
    @RequestMapping(value="/saveIeaveInfo")
    @ResponseBody
    public String saveIeaveInfo(String recordFlow,String auditInfo,String status,String roleFlag) {
        if (StringUtil.isBlank(recordFlow) || StringUtil.isBlank(status)|| StringUtil.isBlank(roleFlag)) {
            return "请求参数不全";
        }
        boolean isTeacher = "teacher".equals(roleFlag);
        boolean isHead = "head".equals(roleFlag);
        boolean isManager = "manager".equals(roleFlag);

        ResDoctorKq kq = resDoctorKqBiz.readResDoctorKq(recordFlow);
        if (kq == null) {
            return "数据标识符为错误";
        }
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = sdf0.format(new Date());
        if (isTeacher) {
            if (StringUtil.isNotBlank(kq.getTeacherAgreeFlag())) {
                return "此请假信息已审核，请刷新列表页";
            }
            if (GlobalConstant.FLAG_Y.equals(status)) {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.HeadAuditing.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.HeadAuditing.getName());
                if ("-".equals(kq.getHeadName())) {
                    if ("-".equals(kq.getManagerName())) {
                        //不需要科主任及管理员审核，带教审核结果为最终审核结果
                        kq.setAuditRoleNow("Pass");
                        kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
                        kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
                    }else {
                        //待科主任审核
                        kq.setAuditRoleNow(GlobalConstant.RES_ROLE_SCOPE_ADMIN);
                    }
                }else {
                    kq.setAuditRoleNow(GlobalConstant.RES_ROLE_SCOPE_HEAD);
                    // 判断带教和科主任是否同一人审核，如果审批通过依次通过
                    String teacherFlow = kq.getTeacherFlow() == null ? "" : kq.getTeacherFlow();
                    if (teacherFlow.equals(kq.getHeadFlow())) {
                        kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerAuditing.getId());
                        kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerAuditing.getName());
                        if ("-".equals(kq.getManagerName())) {
                            kq.setAuditRoleNow("Pass");
                            kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
                            kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
                        } else {
                            kq.setAuditRoleNow(GlobalConstant.RES_ROLE_SCOPE_ADMIN);
                        }
                        kq.setHeadAuditTime(time);
                        kq.setHeadAgreeFlag(status);
                    }
                }
            } else {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerUnPass.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerUnPass.getName());
                kq.setAuditRoleNow("UnPass");
            }
            kq.setTeacherAuditTime(time);
            kq.setTeacherAgreeFlag(status);
            kq.setTeacherAuditInfo(auditInfo);

        } else if (isHead) {
            if (StringUtil.isNotBlank(kq.getHeadAgreeFlag())) {
                return "此请假信息已审核，请刷新列表页";
            }
            if (GlobalConstant.FLAG_Y.equals(status)) {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerAuditing.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerAuditing.getName());
                if ("-".equals(kq.getManagerName())) {
                    kq.setAuditRoleNow("Pass");
                    kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
                    kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
                }else {
                    kq.setAuditRoleNow(GlobalConstant.RES_ROLE_SCOPE_ADMIN);
                }
            } else {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerUnPass.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerUnPass.getName());
                kq.setAuditRoleNow("UnPass");
            }
            kq.setHeadAuditTime(time);
            kq.setHeadAgreeFlag(status);
            kq.setHeadAuditInfo(auditInfo);

        }else if (isManager) {
            if (StringUtil.isNotBlank(kq.getManagerAgreeFlag())) {
                return "此请假信息已审核，请刷新列表页";
            }
            if (GlobalConstant.FLAG_Y.equals(status)) {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
                kq.setAuditRoleNow("Pass");
            } else {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerUnPass.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerUnPass.getName());
                kq.setAuditRoleNow("UnPass");
            }
            kq.setManagerAuditTime(time);
            kq.setManagerAgreeFlag(status);
            kq.setManagerAuditInfo(auditInfo);

        } else {
            return "该数据无法审核！";
        }
        int count = resDoctorKqBiz.editResDoctorKq(kq);
        if(count != 0) {
            //保存审批记录
            ResDoctorKqLog kqLog = new ResDoctorKqLog();
            kqLog.setKqRecordFlow(recordFlow);

            if(isTeacher){
                kqLog.setRoleId("teacher");
                kqLog.setRoleName("带教");
                kqLog.setAuditUserFlow(kq.getTeacherFlow());
                kqLog.setAuditUserName(kq.getTeacherName());
                if(kq.getTeacherAgreeFlag().equals(GlobalConstant.FLAG_Y)){
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.TeacherPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.TeacherPass.getName());
                    kqLog.setAuditRemake(kq.getTeacherAuditInfo());
                }else{
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.TeacherUnPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.TeacherUnPass.getName());
                    kqLog.setAuditRemake(kq.getTeacherAuditInfo());
                }
                kqLog.setAuditTime(time);
                kqLog.setTypeId("Leave");
                kqLog.setTypeName("请假申请");
                resDoctorKqBiz.saveKqLog(kqLog);
                // 判断带教和科主任是否同一人审核，如果审批通过依次通过，记录审批
                String teacherFlow = kq.getTeacherFlow() == null ? "" : kq.getTeacherFlow();
                if (teacherFlow.equals(kq.getHeadFlow())) {
                    kqLog.setRecordFlow(null);
                    kqLog.setRoleId("teacher");
                    kqLog.setRoleName("带教");
                    kqLog.setAuditUserFlow(kq.getTeacherFlow());
                    kqLog.setAuditUserName(kq.getTeacherName());
                    if(kq.getTeacherAgreeFlag().equals(GlobalConstant.FLAG_Y)){
                        kqLog.setAuditStatusId(ResDoctorKqStatusEnum.HeadPass.getId());
                        kqLog.setAuditStatusName(ResDoctorKqStatusEnum.HeadPass.getName());
                        kqLog.setAuditRemake(kq.getTeacherAuditInfo());
                    }else{
                        kqLog.setAuditStatusId(ResDoctorKqStatusEnum.HeadUnPass.getId());
                        kqLog.setAuditStatusName(ResDoctorKqStatusEnum.HeadUnPass.getName());
                        kqLog.setAuditRemake(kq.getTeacherAuditInfo());
                    }
                    resDoctorKqBiz.saveKqLog(kqLog);
                }
            }

            if(isHead) {
                kqLog.setRoleId("head");
                kqLog.setRoleName("科主任");
                kqLog.setAuditUserFlow(kq.getHeadFlow());
                kqLog.setAuditUserName(kq.getHeadName());
                if (kq.getHeadAgreeFlag().equals(GlobalConstant.FLAG_Y)) {
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.HeadPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.HeadPass.getName());
                    kqLog.setAuditRemake(kq.getHeadAuditInfo());
                } else {
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.HeadUnPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.HeadUnPass.getName());
                    kqLog.setAuditRemake(kq.getHeadAuditInfo());
                }
                kqLog.setAuditTime(time);
                kqLog.setTypeId("Leave");
                kqLog.setTypeName("请假申请");
                resDoctorKqBiz.saveKqLog(kqLog);
            }

            if(isManager) {
                kqLog.setRoleId("manager");
                kqLog.setRoleName("管理员");
                kqLog.setAuditUserFlow(kq.getManagerFlow());
                kqLog.setAuditUserName(kq.getManagerName());
                if (kq.getManagerAgreeFlag().equals(GlobalConstant.FLAG_Y)) {
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
                    kqLog.setAuditRemake(kq.getManagerAuditInfo());
                } else {
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.ManagerUnPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.ManagerUnPass.getName());
                    kqLog.setAuditRemake(kq.getManagerAuditInfo());
                }
                kqLog.setAuditTime(time);
                kqLog.setTypeId("Leave");
                kqLog.setTypeName("请假申请");
                resDoctorKqBiz.saveKqLog(kqLog);
            }

            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * @Author shengl
     * @Description //考勤统计 住院医师
     **/
    @RequestMapping(value = "/kqStatisticsList/{roleFlag}")
    public String kqStatisticsList(@PathVariable String roleFlag, String startDate, String endDate, String doctorName,
                                   Integer currentPage, HttpServletRequest request, Model model) {
        SysUser currentUser = GlobalContext.getCurrentUser();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("startDate", startDate);
        paramMap.put("doctorName", doctorName);
        paramMap.put("endDate", endDate);
        paramMap.put("dictList", DictTypeEnum.sysListDictMap.get("LeaveType"));
        paramMap.put("orgFlow", currentUser.getOrgFlow());
        paramMap.put("trainingTypeId",TrainCategoryEnum.DoctorTrainingSpe.getId());
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String, Object>> resultMapList = resDoctorKqBiz.getKqStatistics(paramMap);
        model.addAttribute("resultMapList", resultMapList);
        model.addAttribute("roleFlag", roleFlag);
        return "res/leave/kqStatisticsList";
    }

    /**
     * @Author shengl
     * @Description //考勤统计
     **/
    @RequestMapping(value = "/kqStatisticsListAcc/{roleFlag}")
    public String kqStatisticsListAcc(@PathVariable String roleFlag, String startDate, String endDate, String doctorName,
                                   Integer currentPage, HttpServletRequest request, Model model) {
        SysUser currentUser = GlobalContext.getCurrentUser();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("startDate", startDate);
        paramMap.put("doctorName", doctorName);
        paramMap.put("endDate", endDate);
        paramMap.put("dictList", DictTypeEnum.sysListDictMap.get("LeaveType"));
        paramMap.put("orgFlow", currentUser.getOrgFlow());
        paramMap.put("trainingTypeId",TrainCategoryEnum.AssiGeneral.getId());
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String, Object>> resultMapList = resDoctorKqBiz.getKqStatistics(paramMap);
        model.addAttribute("resultMapList", resultMapList);
        model.addAttribute("roleFlag", roleFlag);
        return "res/leave/kqStatisticsListAcc";
    }

    /**
     * @Author shengl
     * @Description //查看详情
     **/
    @RequestMapping("/kqStatisticsDetail")
    public String kqStatisticsDetail(ResDoctorKq kq, Integer currentPage2, HttpServletRequest request, Model model){
        SysUser currentUser = GlobalContext.getCurrentUser();
        kq.setOrgFlow(currentUser.getOrgFlow());
        PageHelper.startPage(currentPage2,getPageSize(request));
        List<ResDoctorKq> kqList = resDoctorKqBiz.kqStatisticsDetail(kq);
        model.addAttribute("kqList",kqList);
        return "res/leave/kqStatisticsDetailList";
    }

    /**
     * @Description //学员请假页面
     **/
    @RequestMapping("/doctorleaveVerifyMain")
    public String doctorleaveVerifyMain(Model model){
        SysUser currentUser = GlobalContext.getCurrentUser();
        //学员轮转科室
        List<ResDoctorSchProcess> deptList = resDoctorKqBiz.searchProcessByDoctorFlow(currentUser.getUserFlow());
        model.addAttribute("deptList",deptList);
        //是否可以新增请假
        String isLeaveFlag = "N";
        if(null != deptList && deptList.size() > 0){
            isLeaveFlag = "Y";
        }
        model.addAttribute("isLeaveFlag",isLeaveFlag);
        return "jsres/doctor/leave/doctorleaveVerifyMain";
    }

    /**
     * @Description //学员请假列表
     **/
    @RequestMapping("/doctorleaveVerifyList")
    public String doctorleaveVerifyList(ResDoctorKq kq, Integer currentPage, HttpServletRequest request, Model model){
        SysUser currentUser = GlobalContext.getCurrentUser();
        kq.setOrgFlow(currentUser.getOrgFlow());
        kq.setDoctorFlow(currentUser.getUserFlow());
        kq.setKqTypeId(DictTypeEnum.LeaveType.getId());
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,String>> kqList = resDoctorKqBiz.searchDoctorKqList(kq);
        model.addAttribute("kqList",kqList);
        return "jsres/doctor/leave/doctorleaveVerifyList";
    }

    /**
     * @Description //学员请假新增、编辑
     **/
    @RequestMapping("/editLeave")
    public String editLeave(String recordFlow,Model model){
        SysUser user = GlobalContext.getCurrentUser();
        model.addAttribute("user",user);
        if(StringUtil.isNotBlank(recordFlow)) {
            ResDoctorKq doctorKq = resDoctorKqBiz.readResDoctorKq(recordFlow);
            model.addAttribute("doctorKq",doctorKq);
        }
        //学员轮转科室
        List<ResDoctorSchProcess> deptList = resDoctorKqBiz.searchProcessByDoctorFlow(user.getUserFlow());
        model.addAttribute("deptList",deptList);
        String nowDate = DateUtil.getCurrDate();
        model.addAttribute("nowDate",nowDate);
        return "jsres/doctor/leave/editLeave";
    }

    /**
     * @Description //计算请假天数（只计算工作日）
     **/
    @RequestMapping(value="/intervalDays")
    @ResponseBody
    public String intervalDays(String startDate,String endDate){
        int result = 0;
        if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)){
            //两日期相差天数
            int days = (int)DateUtil.signDaysBetweenTowDate(endDate,startDate);
            if(days == 0){
                //判断是否为周末
                String weekOfDay = DateUtil.getWeekFromDate(startDate,"3");
                if(!(weekOfDay.equals("星期六")) && !(weekOfDay.equals("星期日"))){
                    result = 1;
                }
            }else{
                for (int i = 0; i <= days; i++) {
                    String date = DateUtil.addDate(startDate,i);
                    String weekOfDay = DateUtil.getWeekFromDate(date,"3");
                    if(!(weekOfDay.equals("星期六")) && !(weekOfDay.equals("星期日"))){
                        result += 1;
                    }
                }
            }
        }
        return String.valueOf(result);
    }

    /**
     * @Description //保存请假申请
     **/
    @RequestMapping("/saveLeave")
    @ResponseBody
    public String saveLeave(ResDoctorKq kq,String deptFlow,HttpServletRequest request,String []fileFlows){
        ResDoctor doctor=doctorBiz.readDoctor(GlobalContext.getCurrentUser().getUserFlow());
        if(doctor==null) {
            return "医师信息不存在！";
        }
        if(StringUtil.isBlank(doctor.getOrgFlow())) {
            return "未进入基地参加培训！";
        }
        if(StringUtil.isBlank(kq.getDoctorFlow())){
            kq.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
            kq.setDoctorName(GlobalContext.getCurrentUser().getUserName());
        }
        kq.setSessionNumberNow(doctor.getSessionNumber());
        kq.setOrgFlow(doctor.getOrgFlow());
        kq.setOrgName(doctor.getOrgName());
        List<ResKgCfg> cfgs = resDoctorKqBiz.readKqCfgList(doctor.getOrgFlow(),doctor.getDoctorCategoryId());
        if(cfgs==null||cfgs.size()==0)
        {
            return "请联系基地管理员维护请假审核流程！";
        }
        ResKgCfg less=null;
        ResKgCfg greater=null;
        ResKgCfg midd=null;
        Integer allDays=null;
        Integer intervalDays=null;
        Integer intervalDays2=null;
        for(ResKgCfg cfg:cfgs)
        {
            if("All".equals(cfg.getLessOrGreater()))
            {
                allDays=Integer.valueOf(cfg.getAllDays());
                intervalDays=Integer.valueOf(cfg.getIntervalDays());
                intervalDays2=Integer.valueOf(cfg.getIntervalDays2());
            }
            if("Less".equals(cfg.getLessOrGreater()))
            {
                less=cfg;
            }
            if("Greater".equals(cfg.getLessOrGreater()))
            {
                greater=cfg;
            }
            if("Midd".equals(cfg.getLessOrGreater()))
            {
                midd=cfg;
            }
        }
        if(allDays==null||intervalDays==null||intervalDays2==null||less==null||greater==null||midd==null)
        {
            return "请联系基地管理员维护请假审核流程！";
        }
        ResDoctorKq old=resDoctorKqBiz.readResDoctorKq(kq.getRecordFlow());
        if(old!=null) {
            if(!ResDoctorKqStatusEnum.Auditing.getId().equals(old.getAuditStatusId())) {
                return "此请假信息已被审核，请刷新列表页！";
            }
        }
        int c=resDoctorKqBiz.checkTime(kq.getRecordFlow(),kq.getDoctorFlow(),kq.getStartDate(),kq.getEndDate(),DictTypeEnum.LeaveType.getId());
        if(c>0) {
            return "在当前请假时间内已有请假信息！";
        }
        if(StringUtil.isBlank(kq.getIntervalDays())) {
            return "请输入请假天数";
        }

//        double days = resDoctorKqBiz.readAllIntervalDays(kq.getRecordFlow(),kq.getDoctorFlow(),kq.getStartDate(),kq.getEndDate(),DictTypeEnum.LeaveType.getId());
//        if((days+Double.valueOf(kq.getIntervalDays()))>allDays) {
//            return "请假总天数大于"+allDays+",总天数为"+(days+Double.valueOf(kq.getIntervalDays()))+",你已请假"+days+"天,本次请假天数"+kq.getIntervalDays();
//        }
        //校验上传文件大小及格式
        if(null!=fileFlows && fileFlows.length>0) {
            String checkResult = checkFiles(request);
            if (!"1".equals(checkResult)) {
                return checkResult;
            }
        }
        ResDoctorSchProcess process = resDoctorProcessBiz.read(kq.getProcessFlow());
        if(process==null)
        {
            return "请选择轮转科室";
        }
        if(intervalDays2<Double.valueOf(kq.getIntervalDays()))
        {
            setKqAuditInfo(greater,kq,process,doctor);
        }else if(intervalDays2>=Double.valueOf(kq.getIntervalDays())&&intervalDays<Double.valueOf(kq.getIntervalDays())){
            setKqAuditInfo(midd,kq,process,doctor);
        }else{
            setKqAuditInfo(less,kq,process,doctor);
        }

        kq.setKqTypeId(DictTypeEnum.LeaveType.getId());
        kq.setKqTypeName(DictTypeEnum.LeaveType.getName());
        kq.setTypeName(DictTypeEnum.getDictName(DictTypeEnum.LeaveType, kq.getTypeId()));
        kq.setAuditStatusId(ResDoctorKqStatusEnum.Auditing.getId());
        kq.setAuditStatusName(ResDoctorKqStatusEnum.Auditing.getName());
        int n=resDoctorKqBiz.editResDoctorKq(kq);
        if(n==0) {
            return "保存失败";
        }
        List<String> flows=null;
        //上传文件的流水号
        if(fileFlows!=null) {
            flows = Arrays.asList(fileFlows);
        }
        //处理不在本次保存中的文件
        upadteFileInfo(kq.getRecordFlow(), flows);
        //处理上传文件
        addUploadFile(kq.getRecordFlow(), request, "ResDoctorKqFile");
        return "1";
    }

    /**
     * @Description //查看附件
     **/
    @RequestMapping("/showFilws")
    public String showFilws(String recordFlow,Model model){
        List<PubFile> files=fileBiz.findFileByTypeFlow("ResDoctorKqFile",recordFlow);
        model.addAttribute("files",files);
        return "jsres/doctor/leave/showFiles";
    }

    private String checkFiles(HttpServletRequest request) {
        String result="1";
        ServletContext application = request.getServletContext();
        String imageSuffixStr = ".jpeg,.jpg,.png";
        String[] imageSuffixArr = imageSuffixStr.split(",");
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
            List<String> fileSuffix=new ArrayList<>();
            fileSuffix.addAll(Arrays.asList(imageSuffixArr));
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while(iter.hasNext()){
                //记录上传过程起始时的时间，用来计算上传时间
                //int pre = (int) System.currentTimeMillis();
                //取得上传文件
                List<MultipartFile> files = multiRequest.getFiles(iter.next());
                if(files != null&&files.size()>0){
                    for(MultipartFile file:files) {

                        //取得当前上传文件的文件名称
                        String fileName = file.getOriginalFilename();
                        String suffix=fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                        if(!fileSuffix.contains(suffix))
                        {
                            return fileName + "的文件格式不正确，只能上传" + imageSuffixStr + "图片格式的文件。";
                        }
                        if (file.getSize() > 10 * 1024 * 1024) {
                            return fileName+ "的大小超过10M，不得保存";
                        }
                    }
                }
            }
        }
        return result;
    }

    private void setKqAuditInfo(ResKgCfg greater, ResDoctorKq kq, ResDoctorSchProcess process, ResDoctor doctor) {
        if(greater!=null) {
            String auditRoleNow  = "";
            kq.setTeacherFlow(process.getTeacherUserFlow());
            if(GlobalConstant.FLAG_Y.equals(greater.getTeacherFlag())) {
                auditRoleNow  = GlobalConstant.RES_ROLE_SCOPE_TEACHER;
                kq.setTeacherName(process.getTeacherUserName());
            }else{
                kq.setTeacherName("-");
            }
            kq.setHeadFlow(process.getHeadUserFlow());
            if(GlobalConstant.FLAG_Y.equals(greater.getHeadFlag())) {
                if (StringUtil.isBlank(auditRoleNow)) {
                    auditRoleNow  = GlobalConstant.RES_ROLE_SCOPE_HEAD;
                }
                kq.setHeadName(process.getHeadUserName());
            }else{
                kq.setHeadName("-");
            }
            kq.setTutorFlow(doctor.getTutorFlow());
            if(GlobalConstant.FLAG_Y.equals(greater.getTutorFlag())) {
                kq.setTutorName(doctor.getTutorName());
            }else{
                kq.setTutorName("-");
            }
            String adminFlow = InitConfig.getSysCfg("res_admin_role_flow");
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("wsId","res");
            paramMap.put("roleFlow",adminFlow);
            paramMap.put("orgFlow",doctor.getOrgFlow());
            List<SysUser> sysUserList = userBiz.searchUserWithRole(paramMap);
            SysUser admin=null;
            if(sysUserList!=null&&sysUserList.size()>0){
                admin=sysUserList.get(0);
            }
            if(admin!=null) {
                kq.setManagerFlow(admin.getUserFlow());
                if(GlobalConstant.FLAG_Y.equals(greater.getManagerFlag())) {
                    if (StringUtil.isBlank(auditRoleNow)) {
                        auditRoleNow  = GlobalConstant.RES_ROLE_SCOPE_ADMIN;
                    }
                    kq.setManagerName(admin.getUserName());
                }else{
                    kq.setManagerName("-");
                }
            }
            kq.setAuditRoleNow(auditRoleNow);
            kq.setSchDeptFlow(process.getSchDeptFlow());
            kq.setSchDeptName(process.getSchDeptName());
            kq.setSchDeptStartDate(process.getSchStartDate());
            kq.setSchDeptEndDate(process.getSchEndDate());
        }
    }

    //处理文件
    private void upadteFileInfo(String recordFlow, List<String> fileFlows) {
        //查询出不在本次保存中的文件信息
        List<PubFile> files=fileBiz.searchByProductFlowAndNotInFileFlows(recordFlow,fileFlows);
        //删除服务器中相应的文件
        if(files!=null&&files.size()>0)
        {
            for (PubFile pubFile : files) {
                pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                fileBiz.editFile(pubFile);
            }
        }
    }

    //保存上传的附件
    private void addUploadFile(String recordFlow, HttpServletRequest request, String noteTypeId) {
        //以下为多文件上传********************************************
        //创建一个通用的多部分解析器
        List<PubFile> pubFiles=new ArrayList<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while(iter.hasNext()){
                //记录上传过程起始时的时间，用来计算上传时间
                //int pre = (int) System.currentTimeMillis();
                //取得上传文件
                List<MultipartFile> files = multiRequest.getFiles(iter.next());
                if(files != null&&files.size()>0){
                    for(MultipartFile file:files) {
                        //保存附件
                        PubFile pubFile = new PubFile();
                        //取得当前上传文件的文件名称
                        String oldFileName = file.getOriginalFilename();
                        //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                        if (StringUtil.isNotBlank(oldFileName)) {
                            //定义上传路径
                            String dateString = DateUtil.getCurrDate2();
                            String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + noteTypeId + File.separator + dateString+ File.separator+recordFlow;
                            File fileDir = new File(newDir);
                            if (!fileDir.exists()) {
                                fileDir.mkdirs();
                            }
                            //重命名上传后的文件名
                            String originalFilename = "";
                            originalFilename = PkUtil.getUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
                            File newFile = new File(fileDir, originalFilename);
                            try {
                                file.transferTo(newFile);
                            } catch (Exception e) {
                                e.printStackTrace();
                                throw new RuntimeException("保存文件失败！");
                            }
                            String filePath =  File.separator + noteTypeId + File.separator + dateString + File.separator+recordFlow+ File.separator + originalFilename;
                            pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                            pubFile.setFilePath(filePath);
                            pubFile.setFileName(oldFileName);
                            pubFile.setFileSuffix(oldFileName.substring(oldFileName.lastIndexOf(".")));
                            pubFile.setProductType(noteTypeId);
                            pubFile.setProductFlow(recordFlow);
                            pubFiles.add(pubFile);
                        }
                    }
                }
            }
        }
        if(pubFiles.size()>0)
        {
            fileBiz.saveFiles(pubFiles);
        }
    }

    /**
     * @Description //销假申请
     **/
    @RequestMapping("/cancelLeave")
    @ResponseBody
    public String cancelLeave(String recordFlow){
        if(StringUtil.isNotBlank(recordFlow)) {
            ResDoctorKq kq = resDoctorKqBiz.readResDoctorKq(recordFlow);
            if (ResDoctorKqStatusEnum.ManagerUnPass.getId().equals(kq.getAuditStatusId())) {
                return "该条申请审核未通过，无法操作！";
            }
            if (!ResDoctorKqStatusEnum.ManagerPass.getId().equals(kq.getAuditStatusId())) {
                return "该条申请审核进行中，无法操作！";
            }
            kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeAuditing.getId());
            kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeAuditing.getName());
            kq.setTeacherAgreeFlag("");
            kq.setTeacherAuditTime("");
            kq.setTeacherAuditInfo("");
            kq.setHeadAgreeFlag("");
            kq.setHeadAuditTime("");
            kq.setHeadAuditInfo("");
            kq.setManagerAgreeFlag("");
            kq.setManagerAuditTime("");
            kq.setManagerAuditInfo("");
            resDoctorKqBiz.editResDoctorKq(kq);
            return GlobalConstant.OPRE_SUCCESSED_FLAG;
        }
        return GlobalConstant.OPRE_FAIL_FLAG;
    }

    /**
     * @Description // 销假审核
     **/
    @RequestMapping(value="/saveCancelLeaveInfo")
    @ResponseBody
    public String saveCancelLeaveInfo(String recordFlow,String auditInfo,String status,String roleFlag) {
        if (StringUtil.isBlank(recordFlow) || StringUtil.isBlank(status)|| StringUtil.isBlank(roleFlag)) {
            return "请求参数不全";
        }
        boolean isTeacher = "teacher".equals(roleFlag);
        boolean isHead = "head".equals(roleFlag);
        boolean isManager = "manager".equals(roleFlag);

        ResDoctorKq kq = resDoctorKqBiz.readResDoctorKq(recordFlow);
        if (kq == null) {
            return "数据标识符为错误";
        }
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = sdf0.format(new Date());
        if (isTeacher) {
            if (StringUtil.isNotBlank(kq.getTeacherAgreeFlag())) {
                return "此销假信息已审核，请刷新列表页";
            }
            if (GlobalConstant.FLAG_Y.equals(status)) {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeHeadAuditing.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeHeadAuditing.getName());
                if ("-".equals(kq.getHeadName())) {
                    if ("-".equals(kq.getManagerName())) {
                        //不需要科主任及管理员审核，带教审核结果为最终审核结果
                        kq.setAuditRoleNow("Pass");
                        kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerPass.getId());
                        kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerPass.getName());
                    }else {
                        //待科主任审核
                        kq.setAuditRoleNow(GlobalConstant.RES_ROLE_SCOPE_ADMIN);
                    }
                }else {
                    kq.setAuditRoleNow(GlobalConstant.RES_ROLE_SCOPE_HEAD);
                    // 判断带教和科主任是否同一人审核，如果审批通过依次通过
                    String teacherFlow = kq.getTeacherFlow() == null ? "" : kq.getTeacherFlow();
                    if (teacherFlow.equals(kq.getHeadFlow())) {
                        kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerAuditing.getId());
                        kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerAuditing.getName());
                        if ("-".equals(kq.getManagerName())) {
                            kq.setAuditRoleNow("Pass");
                            kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerPass.getId());
                            kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerPass.getName());
                        } else {
                            kq.setAuditRoleNow(GlobalConstant.RES_ROLE_SCOPE_ADMIN);
                        }
                        kq.setHeadAuditTime(time);
                        kq.setHeadAgreeFlag(status);
                    }
                }
            } else {
                kq.setAuditRoleNow("Pass");
            }
            kq.setTeacherAuditTime(time);
            kq.setTeacherAgreeFlag(status);
            kq.setTeacherAuditInfo(auditInfo);

        } else if (isHead) {
            if (StringUtil.isNotBlank(kq.getHeadAgreeFlag())) {
                return "此销假信息已审核，请刷新列表页";
            }
            if (GlobalConstant.FLAG_Y.equals(status)) {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerAuditing.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerAuditing.getName());
                if ("-".equals(kq.getManagerName())) {
                    kq.setAuditRoleNow("Pass");
                    kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerPass.getId());
                    kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerPass.getName());
                }else {
                    kq.setAuditRoleNow(GlobalConstant.RES_ROLE_SCOPE_ADMIN);
                }
            } else {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerUnPass.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerUnPass.getName());
                kq.setAuditRoleNow("UnPass");
            }
            kq.setHeadAuditTime(time);
            kq.setHeadAgreeFlag(status);
            kq.setHeadAuditInfo(auditInfo);

        }else if (isManager) {
            if (StringUtil.isNotBlank(kq.getManagerAgreeFlag())) {
                return "此请假信息已审核，请刷新列表页";
            }
            if (GlobalConstant.FLAG_Y.equals(status)) {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerPass.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerPass.getName());
                kq.setAuditRoleNow("Pass");
            } else {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerUnPass.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerUnPass.getName());
                kq.setAuditRoleNow("UnPass");
            }
            kq.setManagerAuditTime(time);
            kq.setManagerAgreeFlag(status);
            kq.setManagerAuditInfo(auditInfo);

        } else {
            return "该数据无法审核！";
        }
        if(!GlobalConstant.FLAG_Y.equals(status)) {
            // 销假审核不通过，状态变更为请假审核通过
            kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
            kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
            // 根据考勤主键与审核类型查询
            List<ResDoctorKqLog> kqLogs = resDoctorKqBiz.searchKqLogList(recordFlow, "Leave");
            if (null != kqLogs && kqLogs.size() > 0) {
                for (ResDoctorKqLog log : kqLogs) {
                    if (log.getRoleId().equals("manager")) {
                        kq.setManagerAuditTime(log.getAuditTime());
                        kq.setManagerAuditInfo(log.getAuditRemake());
                    }
                    if (log.getRoleId().equals("head")) {
                        kq.setHeadAuditTime(log.getAuditTime());
                        kq.setHeadAuditInfo(log.getAuditRemake());
                    }
                    if (log.getRoleId().equals("teacher")) {
                        kq.setTeacherAuditTime(log.getAuditTime());
                        kq.setTeacherAuditInfo(log.getAuditRemake());
                    }
                }
            }
        }
        int count = resDoctorKqBiz.editResDoctorKq(kq);
        if(count != 0) {
            //保存审批记录
            ResDoctorKqLog kqLog = new ResDoctorKqLog();
            kqLog.setKqRecordFlow(recordFlow);

            if(isTeacher){
                kqLog.setRoleId("teacher");
                kqLog.setRoleName("带教");
                kqLog.setAuditUserFlow(kq.getTeacherFlow());
                kqLog.setAuditUserName(kq.getTeacherName());
                if(kq.getTeacherAgreeFlag().equals(GlobalConstant.FLAG_Y)){
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeTeacherPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeTeacherPass.getName());
                    kqLog.setAuditRemake(auditInfo);
                }else{
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeTeacherUnPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeTeacherUnPass.getName());
                    kqLog.setAuditRemake(auditInfo);
                }
                kqLog.setAuditTime(time);
                kqLog.setTypeId("Cancel");
                kqLog.setTypeName("销假申请");
                resDoctorKqBiz.saveKqLog(kqLog);
                // 判断带教和科主任是否同一人审核，如果审批通过依次通过，记录审批
                String teacherFlow = kq.getTeacherFlow() == null ? "" : kq.getTeacherFlow();
                if (teacherFlow.equals(kq.getHeadFlow())) {
                    kqLog.setRecordFlow(null);
                    kqLog.setRoleId("teacher");
                    kqLog.setRoleName("带教");
                    kqLog.setAuditUserFlow(kq.getTeacherFlow());
                    kqLog.setAuditUserName(kq.getTeacherName());
                    if(kq.getTeacherAgreeFlag().equals(GlobalConstant.FLAG_Y)){
                        kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeHeadPass.getId());
                        kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeHeadPass.getName());
                        kqLog.setAuditRemake(auditInfo);
                    }else{
                        kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeHeadUnPass.getId());
                        kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeHeadUnPass.getName());
                        kqLog.setAuditRemake(auditInfo);
                    }
                    resDoctorKqBiz.saveKqLog(kqLog);
                }
            }

            if(isHead) {
                kqLog.setRoleId("head");
                kqLog.setRoleName("科主任");
                kqLog.setAuditUserFlow(kq.getHeadFlow());
                kqLog.setAuditUserName(kq.getHeadName());
                if (kq.getHeadAgreeFlag().equals(GlobalConstant.FLAG_Y)) {
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeHeadPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeHeadPass.getName());
                    kqLog.setAuditRemake(auditInfo);
                } else {
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeHeadUnPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeHeadUnPass.getName());
                    kqLog.setAuditRemake(auditInfo);
                }
                kqLog.setAuditTime(time);
                kqLog.setTypeId("Cancel");
                kqLog.setTypeName("销假申请");
                resDoctorKqBiz.saveKqLog(kqLog);
            }

            if(isManager) {
                kqLog.setRoleId("manager");
                kqLog.setRoleName("管理员");
                kqLog.setAuditUserFlow(kq.getManagerFlow());
                kqLog.setAuditUserName(kq.getManagerName());
                if (kq.getManagerAgreeFlag().equals(GlobalConstant.FLAG_Y)) {
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerPass.getName());
                    kqLog.setAuditRemake(auditInfo);
                } else {
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerUnPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerUnPass.getName());
                    kqLog.setAuditRemake(auditInfo);
                }
                kqLog.setAuditTime(time);
                kqLog.setTypeId("Cancel");
                kqLog.setTypeName("销假申请");
                resDoctorKqBiz.saveKqLog(kqLog);
            }

            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * @Description //撤回请假申请、销假申请
     **/
    @RequestMapping("/backLeave")
    @ResponseBody
    public String backLeave(String recordFlow,String auditStatusId){
        if(StringUtil.isNotBlank(recordFlow)) {
            ResDoctorKq kq = resDoctorKqBiz.readResDoctorKq(recordFlow);
            if (ResDoctorKqStatusEnum.ManagerPass.getId().equals(kq.getAuditStatusId()) ||
                    ResDoctorKqStatusEnum.RevokeManagerPass.getId().equals(kq.getAuditStatusId())) {
                return "该条申请记录已审核，无法操作！";
            }
            if(!auditStatusId.startsWith("Revoke")){
                // 请假申请撤回,请假状态变更已撤销
                kq.setAuditStatusId(ResDoctorKqStatusEnum.BackLeave.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.BackLeave.getName());
            }
            if(auditStatusId.startsWith("Revoke")){
                // 销假申请撤回，状态变更为请假审核通过
                kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
                // 根据考勤主键与审核类型查询
                List<ResDoctorKqLog> kqLogs = resDoctorKqBiz.searchKqLogList(recordFlow,"Leave");
                if(null != kqLogs && kqLogs.size()>0){
                    for (ResDoctorKqLog log:kqLogs) {
                        if(log.getRoleId().equals("manager")){
                            kq.setManagerAuditTime(log.getAuditTime());
                            kq.setManagerAuditInfo(log.getAuditRemake());
                            kq.setManagerAgreeFlag(GlobalConstant.FLAG_Y);
                        }
                        if(log.getRoleId().equals("head")){
                            kq.setHeadAuditTime(log.getAuditTime());
                            kq.setHeadAuditInfo(log.getAuditRemake());
                            kq.setHeadAgreeFlag(GlobalConstant.FLAG_Y);
                        }
                        if(log.getRoleId().equals("teacher")){
                            kq.setTeacherAuditTime(log.getAuditTime());
                            kq.setTeacherAuditInfo(log.getAuditRemake());
                            kq.setTeacherAgreeFlag(GlobalConstant.FLAG_Y);
                        }
                    }
                }
                //撤回销假，删除销假审批记录
                resDoctorKqBiz.updateKqLogsRecordStatusN(recordFlow,"Cancel");
            }
            resDoctorKqBiz.editResDoctorKq(kq);
            return GlobalConstant.OPRE_SUCCESSED_FLAG;
        }
        return GlobalConstant.OPRE_FAIL_FLAG;
    }

    /**
     * @Description //查看审核进度
     **/
    @RequestMapping("/searchAudit")
    public String searchAudit(String recordFlow,Model model){
        ResDoctorKq resDoctorKq = resDoctorKqBiz.readResDoctorKq(recordFlow);
        model.addAttribute("resDoctorKq",resDoctorKq);
        List<PubFile> files = fileBiz.findFileByTypeFlow("ResDoctorKqFile",recordFlow);
        model.addAttribute("files",files);
        List<ResDoctorKqLog> leaveLogs = resDoctorKqBiz.searchKqLogList(recordFlow,"Leave");
        model.addAttribute("leaveLogs",leaveLogs);
        List<ResDoctorKqLog> cancelLogs = resDoctorKqBiz.searchKqLogList(recordFlow,"Cancel");
        model.addAttribute("cancelLogs",cancelLogs);
        return "jsres/doctor/leave/kqLogs";
    }

    /**
     * @Description // 查询请假信息
     **/
    @RequestMapping(value={"/querycancelInfoById"},method={RequestMethod.GET, RequestMethod.POST})
    public String querycancelInfoById(Model model, String recordFlow, String roleFlag, String type){
        if (StringUtil.isNotBlank(recordFlow)) {
            ResDoctorKq resDoctorKq = resDoctorKqMapper.selectByPrimaryKey(recordFlow);
            model.addAttribute("resDoctorKq",resDoctorKq);
            if (resDoctorKq != null) {
                List<PubFile> files = fileBiz.findFileByTypeFlow("ResDoctorKqFile",recordFlow);
                model.addAttribute("files",files);
            }
        }
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("type", type);
        // 查询请假审批记录
        List<ResDoctorKqLog> leaveLogs = resDoctorKqBiz.searchKqLogList(recordFlow,"Leave");
        model.addAttribute("leaveLogs",leaveLogs);
        // 查询销假审批记录
        List<ResDoctorKqLog> cancelLogs = resDoctorKqBiz.searchKqLogList(recordFlow,"Cancel");
        model.addAttribute("cancelLogs",cancelLogs);
        return "jsres/doctor/leave/querycancelInfo";
    }

    /**
     * @Description // 学员请假导出
     **/
    @RequestMapping(value = "/exportExcel")
    public void exportExcel(ResDoctorKq kq, HttpServletResponse response) throws Exception{
        SysUser currentUser = GlobalContext.getCurrentUser();
        kq.setOrgFlow(currentUser.getOrgFlow());
        kq.setDoctorFlow(currentUser.getUserFlow());
        kq.setKqTypeId(DictTypeEnum.LeaveType.getId());
        List<Map<String,String>> kqList = resDoctorKqBiz.searchDoctorKqList(kq);
        String[] titles = new String[]{
                "doctorName:姓名",
                "typeName:请假类型",
                "schDeptName:轮转科室",
                "doctorRemarks:请假事由",
                "startDate:请假开始时间",
                "endDate:请假结束时间",
                "intervalDays:请假天数",
                "auditStatusName:请假状态"
        };
        String fileName = "请假信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, kqList, response.getOutputStream());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    /**
     * @Description // 带教、科主任、管理员请假导出  住院医师
     **/
    @RequestMapping(value={"/exportLeaveList/{roleFlag}"},method={RequestMethod.GET, RequestMethod.POST})
    public void exportLeaveList(@PathVariable String roleFlag, ResDoctorKq kq, HttpServletResponse response,
                                String schDeptFlow) throws Exception{
        Map<String,Object> paramMap = new HashMap<>();
        SysUser currUser = GlobalContext.getCurrentUser();
        paramMap.put("doctorName",kq.getDoctorName());
        paramMap.put("typeId",kq.getTypeId());
        paramMap.put("startDate",kq.getStartDate());
        paramMap.put("endDate",kq.getEndDate());
        paramMap.put("schDeptFlow",schDeptFlow);
        paramMap.put("roleFlag",roleFlag);
        String auditStatusId = kq.getAuditStatusId();
//        paramMap.put("auditStatusId",auditStatusId);
        boolean auditRoleFlag = ResDoctorKqStatusEnum.Auditing.getId().equals(auditStatusId);
        if("teacher".equals(roleFlag)){//带教
            paramMap.put("teacherFlow",currUser.getUserFlow());
            if(auditRoleFlag){
                //待带教审核
                paramMap.put("auditStatusId",ResDoctorKqStatusEnum.Auditing.getId());
            }
        }
        if("head".equals(roleFlag)){
            paramMap.put("headFlow",currUser.getUserFlow());
            if(auditRoleFlag){
                //待科主任审核
                paramMap.put("auditStatusId",ResDoctorKqStatusEnum.HeadAuditing.getId());
            }
        }
        if("manager".equals(roleFlag)){
            paramMap.put("orgFlow",currUser.getOrgFlow());
//            paramMap.put("managerFlow",currUser.getUserFlow());
            if(auditRoleFlag){
                //待管理员审核
                paramMap.put("auditStatusId",ResDoctorKqStatusEnum.ManagerAuditing.getId());
            }
        }
        List<String> notStatus=new ArrayList<>();
        notStatus.add(ResDoctorKqStatusEnum.Revoke.getId());
        notStatus.add(ResDoctorKqStatusEnum.BackLeave.getId());
        paramMap.put("notStatus",notStatus);
        paramMap.put("trainingTypeId",TrainCategoryEnum.DoctorTrainingSpe.getId());
        List<ResDoctorKq> kqList = resDoctorKqBiz.searchResDoctorKq(paramMap);
        String[] titles = new String[]{
                "doctorName:姓名",
                "typeName:请假类型",
                "schDeptName:轮转科室",
                "doctorRemarks:请假事由",
                "startDate:请假开始时间",
                "endDate:请假结束时间",
                "intervalDays:请假天数",
                "auditStatusName:请假状态"
        };
        String fileName = "请假信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, kqList, response.getOutputStream());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }


    /**
     * @Description // 带教、科主任、管理员请假导出 助理全科
     **/
    @RequestMapping(value={"/exportLeaveListAcc/{roleFlag}"},method={RequestMethod.GET, RequestMethod.POST})
    public void exportLeaveListAcc(@PathVariable String roleFlag, ResDoctorKq kq, HttpServletResponse response,
                                String schDeptFlow) throws Exception{
        Map<String,Object> paramMap = new HashMap<>();
        SysUser currUser = GlobalContext.getCurrentUser();
        paramMap.put("doctorName",kq.getDoctorName());
        paramMap.put("typeId",kq.getTypeId());
        paramMap.put("startDate",kq.getStartDate());
        paramMap.put("endDate",kq.getEndDate());
        paramMap.put("schDeptFlow",schDeptFlow);
        paramMap.put("roleFlag",roleFlag);
        String auditStatusId = kq.getAuditStatusId();
        paramMap.put("auditStatusId",auditStatusId);
        boolean auditRoleFlag = ResDoctorKqStatusEnum.Auditing.getId().equals(auditStatusId);
        if("teacher".equals(roleFlag)){//带教
            paramMap.put("teacherFlow",currUser.getUserFlow());
            if(auditRoleFlag){
                //待带教审核
                paramMap.put("auditStatusId",ResDoctorKqStatusEnum.Auditing.getId());
            }
        }
        if("head".equals(roleFlag)){
            paramMap.put("headFlow",currUser.getUserFlow());
            if(auditRoleFlag){
                //待科主任审核
                paramMap.put("auditStatusId",ResDoctorKqStatusEnum.HeadAuditing.getId());
            }
        }
        if("manager".equals(roleFlag)){
            paramMap.put("orgFlow",currUser.getOrgFlow());
            paramMap.put("managerFlow",currUser.getUserFlow());
            if(auditRoleFlag){
                //待管理员审核
                paramMap.put("auditStatusId",ResDoctorKqStatusEnum.ManagerAuditing.getId());
            }
        }
        List<String> notStatus=new ArrayList<>();
        notStatus.add(ResDoctorKqStatusEnum.Revoke.getId());
        notStatus.add(ResDoctorKqStatusEnum.BackLeave.getId());
        paramMap.put("notStatus",notStatus);
        paramMap.put("trainingTypeId",TrainCategoryEnum.AssiGeneral.getId());
        List<ResDoctorKq> kqList = resDoctorKqBiz.searchResDoctorKq(paramMap);
        String[] titles = new String[]{
                "doctorName:姓名",
                "typeName:请假类型",
                "schDeptName:轮转科室",
                "doctorRemarks:请假事由",
                "startDate:请假开始时间",
                "endDate:请假结束时间",
                "intervalDays:请假天数",
                "auditStatusName:请假状态"
        };
        String fileName = "请假信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, kqList, response.getOutputStream());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

}
