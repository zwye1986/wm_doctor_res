package com.pinde.sci.ctrl.recruit;

import com.pinde.core.util.DateUtil;
import com.pinde.sci.biz.recruit.IRecruitCfgInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitInfoLogBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.core.common.enums.recruit.RecruitOperEnum;
import com.pinde.core.common.enums.recruit.RecruitStatusEnum;
import com.pinde.sci.model.mo.RecruitCfgInfo;
import com.pinde.sci.model.mo.RecruitInfo;
import com.pinde.sci.model.mo.RecruitInfoLog;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.recruit.RecruitInfoExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/recruit/info")
public class RecruitInfoController extends GeneralController {

    @Autowired
    private IRecruitCfgInfoBiz recruitCfgInfoBiz;

    @Autowired
    private IRecruitInfoBiz recruitInfoBiz;

    @Autowired
    private IRecruitInfoLogBiz recruitInfoLogBiz;

    @Autowired
    private IUserBiz userBiz;

    @RequestMapping("/main")
    public String main(Model model){
        //获取当前登录用户信息
        SysUser currentUser = GlobalContext.getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        //获取当前配置信息
        RecruitCfgInfo currYearCfgInfo = recruitCfgInfoBiz.getCurrYearCfgInfo(currentUser.getOrgFlow());
        if (currYearCfgInfo == null){
            return "recruit/doctor/noCfg";
        }
        //是否已录取
        List<RecruitInfoExt> recruitInfoExts = recruitInfoBiz.selectRecruitByUserFlow(currentUser.getUserFlow(), currentUser.getOrgFlow());
        if (recruitInfoExts != null&& recruitInfoExts.size() > 0){
            for (RecruitInfoExt recruitInfoExt:recruitInfoExts) {
                if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(recruitInfoExt.getAdmitIsPass())) {
                    model.addAttribute("recruitInfo", recruitInfoExt);
                    return "recruit/doctor/recruitInfoReadonly";
                }
            }
        }
        //当前用户是否存在招录信息,是否第一次招录
        RecruitInfo recruitInfo = recruitInfoBiz.searchRecruitInfoByUserFlowAndRecruitYear(currentUser.getUserFlow(),currYearCfgInfo.getRecruitYear());
        if (recruitInfo != null){
            String auditStatusId = recruitInfo.getAuditStatusId();
            if (auditStatusId != null && auditStatusId != ""){
                //用户是否未审核或审核通过
                if (auditStatusId.equals(RecruitStatusEnum.Passing.getId()) || auditStatusId.equals(RecruitStatusEnum.Passed.getId())){
                    model.addAttribute("recruitInfo",recruitInfo);
                    return "recruit/doctor/recruitInfoReadonly";
                }
            }
        }else {
        //新增一个racruitInfo
            recruitInfo = new RecruitInfo();
            recruitInfo.setDoctorFlow(currentUser.getUserFlow());
            recruitInfo.setOrgFlow(currentUser.getOrgFlow());
            recruitInfo.setRecruitYear(currYearCfgInfo.getRecruitYear());
            recruitInfo.setSessionNumber(currYearCfgInfo.getRecruitYear());
            recruitInfoBiz.updateRecruitInfo(recruitInfo);
        }
        model.addAttribute("recruitInfo",recruitInfo);
        model.addAttribute("currYearCfgInfo",currYearCfgInfo);
        return "recruit/doctor/recruitInfo";
    }

    /**
     * 保存log
     * @param recruitInfo
     * @param model
     * @return
     */
    @RequestMapping("/saveRecruitInfo")
    @ResponseBody
    public String saveRecruitInfo(String sexId,RecruitInfo recruitInfo,Model model){
        //获取当前配置信息
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        RecruitCfgInfo currYearCfgInfo = recruitCfgInfoBiz.getCurrYearCfgInfo(orgFlow);
        //
        if (currYearCfgInfo == null){
            return "recruit/doctor/noCfg";
        }
        RecruitInfo currRecruitInfo = recruitInfoBiz.searchRecruitInfoByUserFlowAndRecruitYear(recruitInfo.getDoctorFlow(),recruitInfo.getRecruitYear());
        String currAuditStatusId = currRecruitInfo.getAuditStatusId();
        if (currAuditStatusId != null){
            if (currAuditStatusId.equals(RecruitStatusEnum.Passing.getId())){
                return "填报信息已提交";
            }
            if (currAuditStatusId.equals(RecruitStatusEnum.Passed.getId())){
                return "填报信息已通过审核";
            }
        }
        //获取该用户保存log
        RecruitInfoLog recruitInfoLog = recruitInfoLogBiz.checkSavedRecruitInfo(recruitInfo.getRecruitFlow());
        if (recruitInfoLog.getLogFlow() != null && recruitInfoLog.getLogFlow() != ""){

        }else{
            recruitInfoLog.setRecruitFlow(recruitInfo.getRecruitFlow());
            recruitInfoLog.setAuditStatusId(RecruitStatusEnum.Edit.getId());
            recruitInfoLog.setAuditStatusName(RecruitStatusEnum.Edit.getName());
            recruitInfoLog.setOperTypeId(RecruitOperEnum.Save.getId());
            recruitInfoLog.setOperTypeName(RecruitOperEnum.Save.getName());
            recruitInfoLog.setOperTime(DateUtil.getCurrDateTime2());
        }
        //记录保存log
        if (recruitInfoLogBiz.updateRecruitInfoLog(recruitInfoLog,null) == 1){
            //将info状态修改为保存
            recruitInfo.setSaveTime(DateUtil.getCurrDateTime2());
            if (currRecruitInfo.getAuditStatusId() != null && currRecruitInfo.getAuditStatusId().equals(RecruitStatusEnum.NoPassed.getId())){
            }else {
                recruitInfo.setAuditStatusId(RecruitStatusEnum.Edit.getId());
                recruitInfo.setAuditStatusName(RecruitStatusEnum.Edit.getName());
            }
            recruitInfoBiz.updateRecruitInfo(recruitInfo);
            System.out.println(sexId);
            if (sexId != null && sexId != ""){
                SysUser currentUser = GlobalContext.getCurrentUser();
                if ("Man".equals(sexId)){
                    currentUser.setSexId(sexId);
                    currentUser.setSexName("男");
                }else if ("Woman".equals(sexId)){
                    currentUser.setSexId(sexId);
                    currentUser.setSexName("女");
                }
                userBiz.saveUser(currentUser);
            }
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
        }else {
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        }
    }

    /**
     * 提交log
     * @param recruitInfo
     * @param model
     * @return
     */
    @RequestMapping("/commitRecruitInfo")
    @ResponseBody
    public String commitRecruitInfo(String sexId,RecruitInfo recruitInfo,Model model){
        //获取当前配置信息
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        RecruitCfgInfo currYearCfgInfo = recruitCfgInfoBiz.getCurrYearCfgInfo(orgFlow);
        //
        if (currYearCfgInfo == null){
            return "recruit/doctor/noCfg";
        }
        String recruitStartDate = currYearCfgInfo.getRecruitStartDate();
        String recruitEndDate = currYearCfgInfo.getRecruitEndDate();
        String currDate = DateUtil.getCurrDate();
        if (currDate.compareTo(recruitStartDate) < 0 || currDate.compareTo(recruitEndDate) > 0){
            return "recruit/doctor/noCfg";
        }

        RecruitInfo currRecruitInfo = recruitInfoBiz.searchRecruitInfoByUserFlowAndRecruitYear(recruitInfo.getDoctorFlow(),recruitInfo.getRecruitYear());
        String currAuditStatusId = currRecruitInfo.getAuditStatusId();
        if (currAuditStatusId != null){
            if (currAuditStatusId.equals(RecruitStatusEnum.Passing.getId())){
                return "填报信息已提交";
            }
            if (currAuditStatusId.equals(RecruitStatusEnum.Passed.getId())){
                return "填报信息已通过审核";
            }
        }
        //判断是否可以提交
        String auditStatusId = recruitInfo.getAuditStatusId();
        if (auditStatusId != null){
            if (auditStatusId.equals(RecruitStatusEnum.Passing.getId())){
                return "填报信息已提交";
            }
            if (auditStatusId.equals(RecruitStatusEnum.Passed.getId())){
                return "填报信息已通过审核";
            }
        }

        RecruitInfoLog recruitInfoLog = new RecruitInfoLog();
        recruitInfoLog.setRecruitFlow(recruitInfo.getRecruitFlow());
        recruitInfoLog.setAuditStatusId(RecruitStatusEnum.Passing.getId());
        recruitInfoLog.setAuditStatusName(RecruitStatusEnum.Passing.getName());
        recruitInfoLog.setOperTypeId(RecruitOperEnum.Commit.getId());
        recruitInfoLog.setOperTypeName(RecruitOperEnum.Commit.getName());
        recruitInfoLog.setOperTime(DateUtil.getCurrDateTime2());

        recruitInfo.setSubmitTime(DateUtil.getCurrDateTime2());
        recruitInfo.setAuditStatusId(RecruitStatusEnum.Passing.getId());
        recruitInfo.setAuditStatusName(RecruitStatusEnum.Passing.getName());
        if (recruitInfoLogBiz.updateRecruitInfoLog(recruitInfoLog,null) == 1 && recruitInfoBiz.updateRecruitInfo(recruitInfo) == 1) {
            if (sexId != null && sexId != ""){
                SysUser currentUser = GlobalContext.getCurrentUser();
                if ("Man".equals(sexId)){
                    currentUser.setSexId(sexId);
                    currentUser.setSexName("男");
                }else if ("Woman".equals(sexId)){
                    currentUser.setSexId(sexId);
                    currentUser.setSexName("女");
                }
                userBiz.saveUser(currentUser);
            }
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
        }
        return "提交失败";
    }
}
