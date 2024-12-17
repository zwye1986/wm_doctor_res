package com.pinde.sci.ctrl.jsres;


import com.pinde.core.model.ResSchProcessExpress;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.*;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.SchRotationDeptMapper;
import com.pinde.core.common.enums.AfterRecTypeEnum;
import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResDoctorSchProcess;
import com.pinde.core.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jsres/afterDataManager")
public class JsResAfterDataManagerController extends GeneralController {
    @Autowired
    private IJsResDoctorBiz jsResDoctorBiz;
    @Autowired
    private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;
    @Autowired
    private OrgBizImpl orgBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IResRecBiz resRecBiz;
    @Autowired
    private IResScoreBiz resScoreBiz;
    @Autowired
    private ISchRotationBiz rotationBiz;
    @Autowired
    private ISchArrangeResultBiz resultBiz;
    @Autowired
    private ISchRotationGroupBiz groupBiz;
    @Autowired
    private SchRotationDeptMapper rotationDeptMapper;
    @Autowired
    private IResDoctorProcessBiz processBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private ISchRotationDeptBiz schRotationDeptBiz;
    @Autowired
    private IJsResRecBiz jsResRecBiz;
    @Autowired
    private ISchDoctorDeptBiz doctorDeptBiz;
    @Autowired
    private ISchRotationDeptBiz rotationDeptBiz;
    public static final String SCOREYEAR_NOT_FIND="请选择成绩年份";
    @Autowired
    private IJsResGraduationApplyBiz graduationApplyBiz;
    @Autowired
    private IFileBiz fileBiz;

    @Autowired
    private IResSchProcessExpressBiz expressBiz;

    @Autowired
    private ISchRotationDeptAfterBiz afterBiz;
    /**
     *删除功能页面
     * @param model
     * @param roleFlag
     * @return
     */
    @RequestMapping(value="/afterDataDel")
    public String afterDataDel(Model model){
        return "jsres/hospital/afterDataManager/afterDataDel";
    }

    /**
     *
     * @param model
     * @param
     * @return
     */
    @RequestMapping(value="/afterDataDelList")
    public String afterDataDelList(Model model,Integer currentPage ,HttpServletRequest request,
                                   String userCode,String idNo, String baseFlag){
        if(StringUtil.isNotBlank(userCode) ||  StringUtil.isNotBlank(idNo)) {
            SysUser user = null;
            if(StringUtil.isNotBlank(userCode) && StringUtil.isNotBlank(idNo)){
                SysUser user1 = new SysUser();
                user1.setUserCode(userCode);
                user1.setIdNo(idNo);
                List<SysUser> sysUsers = userBiz.searchUser(user1);
                if(null != sysUsers && sysUsers.size() > 1){
                    model.addAttribute("msg", "用户名和身份证号不匹配！");
                    return "jsres/hospital/afterDataManager/afterDataDelList";
                }else{
                    user = sysUsers.get(0);
                }
            }else if(StringUtil.isNotBlank(userCode)){
                user = userBiz.findByUserCode(userCode);
            }else if(StringUtil.isNotBlank(idNo)){
                user = userBiz.findByIdNo(idNo);
            }
            if (user != null) {
                ResDoctor doctor=resDoctorBiz.readDoctor(user.getUserFlow());
                if(doctor!=null) {
                    // 协同基地需要查看到报名在主基地实际在协同基地培训的学员
                    String orgFlow1 = GlobalContext.getCurrentUser().getOrgFlow();
                    String secondOrgFlow = doctor.getSecondOrgFlow();
                    if(StringUtil.isEmpty(doctor.getOrgFlow())){
                        model.addAttribute("msg", "该用户没有培训基地！");
                        return "jsres/hospital/afterDataManager/afterDataDelList";
                    }

//                    if((doctor.getOrgFlow().equals(orgFlow1)) || (orgFlow1.equals(secondOrgFlow) && "1".equals(baseFlag))) {
                    if((doctor.getOrgFlow().equals(orgFlow1)) || (orgFlow1.equals(secondOrgFlow))) {
                        List<ResDoctorSchProcess> processes = afterBiz.queryProcess(userCode);
                        Map<String, ResSchProcessExpress> expressMap = new HashMap<>();
                        List<String> recTypeIds=new ArrayList<>();
                        recTypeIds.add(AfterRecTypeEnum.AfterEvaluation.getId());
                        recTypeIds.add(AfterRecTypeEnum.DOPS.getId());
                        recTypeIds.add(AfterRecTypeEnum.Mini_CEX.getId());
                        if(processes!=null)
                        {
                            for(ResDoctorSchProcess p:processes)
                            {
                                List<ResSchProcessExpress> expresses=expressBiz.searchRecByProcessWithBLOBs(recTypeIds,p.getProcessFlow());
                                if(expresses!=null)
                                {
                                    for(ResSchProcessExpress e:expresses) {
                                        expressMap.put(p.getProcessFlow() +e.getRecTypeId(),e);
                                    }
                                }
                            }
                        }
                        model.addAttribute("expressMap", expressMap);
                        model.addAttribute("list", processes);
                    }else{
                        model.addAttribute("msg","此学员的培训基地非当前登录人所在的基地！");
                    }
                }else {
                    model.addAttribute("msg", "此用户名不存在学员医师信息！");
                }
            }else {
                model.addAttribute("msg", "用户名在系统中不存在！");
            }
        }else{
            model.addAttribute("msg","请输入学员查询条件！");
        }
        return "jsres/hospital/afterDataManager/afterDataDelList";
    }

    /**
     * 删除表单
     * @param processFlow
     * @param recTypeId
     * @param model
     * @return
     */
    @RequestMapping(value="/delProcessType", method={RequestMethod.POST})
    @ResponseBody
    public String delProcessType(String processFlow,String recTypeId,String recFlow, Model model){
        if(StringUtil.isBlank(processFlow))
        {
            return "请选择轮转记录";
        }
        if(StringUtil.isBlank(recTypeId))
        {
            return "请选择需要退回的表单";
        }
        if(StringUtil.isNotBlank(recFlow))
        {
            afterBiz.delProcessTypeByFlow(recFlow);
        }else{
            afterBiz.delProcessType(processFlow,recTypeId);
        }
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * 删除科室
     * @param processFlow
     * @param model
     * @return
     */
    @RequestMapping(value="/delProcess", method={RequestMethod.POST})
    @ResponseBody
    public String delProcess(String processFlow,Model model){
        if(StringUtil.isBlank(processFlow))
        {
            return "请选择轮转记录";
        }
        int count=afterBiz.delProcess(processFlow);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * 恢复删除数据功能页面
     * @param model
     * @return
     */
    @RequestMapping(value="/afterDataBack")
    public String afterDataBack(Model model){
        return "jsres/hospital/afterDataManager/afterDataBack";
    }

    @RequestMapping(value="/afterDataBackList")
    public String afterDataBackList(Model model,Integer currentPage ,HttpServletRequest request,
                                    String userCode,String baseFlag){
        if(StringUtil.isNotBlank(userCode)) {
            SysUser user = userBiz.findByUserCode(userCode);
            if (user != null) {
                ResDoctor doctor=resDoctorBiz.readDoctor(user.getUserFlow());
                if(doctor!=null) {
                    // 协同基地需要查看到报名在主基地实际在协同基地培训的学员
                    String orgFlow1 = GlobalContext.getCurrentUser().getOrgFlow();
                    String secondOrgFlow = doctor.getSecondOrgFlow();
                   if((doctor.getOrgFlow().equals(orgFlow1)) || (orgFlow1.equals(secondOrgFlow) && "1".equals(baseFlag))) {
                        List<ResDoctorSchProcess> processes  = afterBiz.queryDelProcess(userCode);
                        Map<String,ResSchProcessExpress> expressMap=new HashMap<>();
                        List<String> recTypeIds=new ArrayList<>();
                        recTypeIds.add(AfterRecTypeEnum.AfterEvaluation.getId());
                        recTypeIds.add(AfterRecTypeEnum.DOPS.getId());
                        recTypeIds.add(AfterRecTypeEnum.Mini_CEX.getId());
                        if(processes!=null)
                        {
                            for(ResDoctorSchProcess p:processes)
                            {
                                List<ResSchProcessExpress> expresses=afterBiz.searchRecByProcessWithBLOBsByDel(recTypeIds,p.getProcessFlow());
                                if(expresses!=null)
                                {
                                    for(ResSchProcessExpress e:expresses) {
                                        expressMap.put(p.getProcessFlow() +e.getRecTypeId(),e);
                                    }
                                }
                            }
                        }
                        model.addAttribute("expressMap", expressMap);
                        model.addAttribute("list", processes);
                    }else{
                        model.addAttribute("msg","此学员的培训基地非当前登录人所在的基地！");
                    }
                }else {
                    model.addAttribute("msg", "此用户名不存在学员医师信息！");
                }
            }else {
                model.addAttribute("msg", "用户名在系统中不存在！");
            }
        }else{
            model.addAttribute("msg","请输入学员用户名！");
        }
        return "jsres/hospital/afterDataManager/afterDataBackList";
    }

    @RequestMapping(value="/backProcess", method={RequestMethod.POST})
    @ResponseBody
    public String backProcess(String processFlow,Model model){
        if(StringUtil.isBlank(processFlow))
        {
            return "请选择轮转记录";
        }
        int count=afterBiz.backProcess(processFlow);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
    }

    @RequestMapping(value="/backProcessType", method={RequestMethod.POST})
    @ResponseBody
    public String backProcessType(String processFlow,String recTypeId,String recFlow, Model model){
        if(StringUtil.isBlank(processFlow))
        {
            return "请选择轮转记录";
        }
        if(StringUtil.isBlank(recTypeId))
        {
            return "请选择需要恢复的表单";
        }
        if(StringUtil.isBlank(recFlow))
        {
            return "请选择需要恢复的表单";
        }
        ResSchProcessExpress express=expressBiz.queryResRecByProcessAndType(processFlow,recTypeId);
        if(express!=null)
        {
            return "此类型出科表已被重新审核，无法恢复！";
        }
        int count=afterBiz.backProcessTypeByFlow(recFlow);

        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
    }
    /**
     *签到考勤退回审核
     * @param model
     * @return
     */
    @RequestMapping(value="/attendBack")
    public String attendBack(Model model){
        return "jsres/hospital/afterDataManager/attendBack";
    }
    @RequestMapping(value="/afterAttendBackList")
    public String afterAttendBackList(Model model,Integer currentPage ,HttpServletRequest request,
                                      String userCode,   String startDate,   String endDate,  String baseFlag){
        if(StringUtil.isNotBlank(userCode)) {
            SysUser user = userBiz.findByUserCode(userCode);
            if (user != null) {
                ResDoctor doctor=resDoctorBiz.readDoctor(user.getUserFlow());
                if(doctor!=null) {
                    // 协同基地需要查看到报名在主基地实际在协同基地培训的学员-退回日常考勤
                    String orgFlow1 = GlobalContext.getCurrentUser().getOrgFlow();
                    String secondOrgFlow = doctor.getSecondOrgFlow();

                    if((doctor.getOrgFlow().equals(orgFlow1)) || (orgFlow1.equals(secondOrgFlow) && "1".equals(baseFlag))) {
                        List<Map<String,String>> list  = afterBiz.afterAttendBackList(userCode,startDate,endDate);
                        model.addAttribute("list", list);
                    }else{
                        model.addAttribute("msg","此学员的培训基地非当前登录人所在的基地！");
                    }
                }else {
                    model.addAttribute("msg", "此用户名不存在学员医师信息！");
                }
            }else {
                model.addAttribute("msg", "用户名在系统中不存在！");
            }
        }else{
            model.addAttribute("msg","请输入学员用户名！");
        }
        return "jsres/hospital/afterDataManager/attendBackList";
    }
    @RequestMapping(value="/attendBackList")
    @ResponseBody
    public String attendBackList(Model model,HttpServletRequest request,
                                 String userCode,   String startDate,   String endDate  ,String baseFlag ){
        if(StringUtil.isNotBlank(userCode)) {
            SysUser user = userBiz.findByUserCode(userCode);
            if (user != null) {
                ResDoctor doctor=resDoctorBiz.readDoctor(user.getUserFlow());
                if(doctor!=null) {
                    // 协同基地需要查看到报名在主基地实际在协同基地培训的学员-一键退回
                    String orgFlow1 = GlobalContext.getCurrentUser().getOrgFlow();
                    String secondOrgFlow = doctor.getSecondOrgFlow();

                    if((doctor.getOrgFlow().equals(orgFlow1)) || (orgFlow1.equals(secondOrgFlow) && "1".equals(baseFlag))) {
                        int count=afterBiz.backAttend(userCode,startDate,endDate);
                        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
                    }else{
                        return"此学员的培训基地非当前登录人所在的基地！";
                    }
                }else {
                    return "此用户名不存在学员医师信息！";
                }
            }else {
                return "用户名在系统中不存在！";
            }
        }else{
            return"请输入学员用户名！";
        }

    }
    @RequestMapping(value="/backAttend")
    @ResponseBody
    public String backAttend(Model model,HttpServletRequest request,
                             String attendanceFlow   ){
        if(StringUtil.isNotBlank(attendanceFlow)) {
            int c= afterBiz.backAttendByFlow(attendanceFlow);
            return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
        }else{
            return"请选择轮需要退回的考勤记录！";
        }

    }

}
