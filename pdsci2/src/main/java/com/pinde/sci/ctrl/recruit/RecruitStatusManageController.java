package com.pinde.sci.ctrl.recruit;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.sci.biz.recruit.IRecruitCfgInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitInfoLogBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.recruit.RecruitOperEnum;
import com.pinde.sci.enums.recruit.RecruitStatusEnum;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

import static com.pinde.core.util.DateUtil.defDtPtn02;
import static com.pinde.core.util.DateUtil.defDtPtn04;

@Controller
@RequestMapping("/recruit/auditStatus")
public class RecruitStatusManageController extends GeneralController {

    @Autowired
    private IRecruitInfoLogBiz recruitInfoLogBiz;

    @Autowired
    private IRecruitInfoBiz recruitInfoBiz;

    @Autowired
    private IRecruitCfgInfoBiz recruitCfgInfoBiz;

    @Autowired
    private IUserBiz userBiz;

    @RequestMapping("/main")
    public String main(Model model, Integer currentPage, HttpServletRequest request,String userName,String beginDate,String endDate,String recruitYear,String auditStatusId){
        //获取当前配置信息
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        RecruitCfgInfo currYearCfgInfo = recruitCfgInfoBiz.getCurrYearCfgInfo(orgFlow);
        //当前是否配置招录
        if (currYearCfgInfo == null){
            return "recruit/doctor/noCfg";
        }
        //判断是否在配置时间内
//        String recruitStartDate = currYearCfgInfo.getRecruitStartDate();
//        String recruitEndDate = currYearCfgInfo.getRecruitEndDate();
//        String currDate = DateUtil.getCurrDate();
//        if (currDate.compareTo(recruitStartDate) < 0 || currDate.compareTo(recruitEndDate) > 0){
//        if (currDate.compareTo(recruitStartDate) < 0){
//            return "recruit/doctor/noCfg";
//        }
        HashMap<String, String> map = new HashMap<>();
        map.put("userName",userName);
        map.put("recruitYear",recruitYear);
        map.put("auditStatusId",auditStatusId);
        map.put("beginDate",beginDate);
        map.put("endDate",endDate);
        map.put("orgFlow",orgFlow);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<RecruitInfoExt> recruitInfoExts = recruitInfoBiz.selectRecruitInfoAboutAudit(map);
        model.addAttribute("userName",userName);
        model.addAttribute("recruitYear",recruitYear);
        model.addAttribute("auditStatusId",auditStatusId);
        model.addAttribute("beginDate",beginDate);
        model.addAttribute("endDate",endDate);
        if (recruitInfoExts != null && recruitInfoExts.size() > 0){
            for (RecruitInfoExt r:recruitInfoExts) {
                r.setAuditTime(DateUtil.transDate(r.getAuditTime()));
                r.setSaveTime(DateUtil.transDateTime(r.getSaveTime(),defDtPtn02,defDtPtn04));
                r.setSubmitTime(DateUtil.transDateTime(r.getSubmitTime(),defDtPtn02,defDtPtn04));
            }
        }
        model.addAttribute("recruitInfoExts",recruitInfoExts);
        return "recruit/manage/auditStatusManage";
    }


    @RequestMapping("/auditRecruitInfo")
    public String auditRecruitInfo(String recruitFlow,Model model){
        RecruitInfo recruitInfo = recruitInfoBiz.searchByRecruitFlow(recruitFlow);
        model.addAttribute("recruitInfo",recruitInfo);
        SysUser currentUser = userBiz.findByFlow(recruitInfo.getDoctorFlow());
        model.addAttribute("currentUser",currentUser);
        return "recruit/manage/auditRecruitInfoForm";
    }

    @RequestMapping("/examineRecruitInfo")
    public String examineRecruitInfo(String recruitFlow,Model model){
        RecruitInfo recruitInfo = recruitInfoBiz.searchByRecruitFlow(recruitFlow);
        model.addAttribute("recruitInfo",recruitInfo);
        SysUser currentUser = userBiz.findByFlow(recruitInfo.getDoctorFlow());
        model.addAttribute("currentUser",currentUser);
        return "recruit/manage/examineRecruitInfoForm";
    }

    @RequestMapping("/approve")
    @ResponseBody
    public synchronized String approve(String recruitFlow,String auditContent){
        RecruitInfoExt recruitInfoExt=recruitInfoBiz.searchRecruitInfoByFlow(recruitFlow);
        if(RecruitStatusEnum.Passed.getId().equals(recruitInfoExt.getAuditStatusId()))
        {
            return "已审核通过";
        }
        //先批准infoLog再批准info并且发放准考证号
        RecruitInfoLog recruitInfoLog = new RecruitInfoLog();
        recruitInfoLog.setRecruitFlow(recruitFlow);
        recruitInfoLog.setAuditStatusId(RecruitStatusEnum.Passed.getId());
        recruitInfoLog.setAuditStatusName(RecruitStatusEnum.Passed.getName());
        recruitInfoLog.setOperTypeId(RecruitOperEnum.Audit.getId());
        recruitInfoLog.setOperTypeName(RecruitOperEnum.Audit.getName());
        recruitInfoLog.setAuditTime(DateUtil.getCurrDateTime2());
        recruitInfoLog.setOperTime(DateUtil.getCurrDateTime2());
        if (recruitInfoLogBiz.updateRecruitInfoLog(recruitInfoLog,auditContent) == 1 && recruitInfoBiz.approve(recruitFlow,auditContent) == 1){
            return GlobalConstant.OPRE_SUCCESSED;
        }else {
            return GlobalConstant.OPRE_FAIL;
        }
    }

    @RequestMapping("/disapprove")
    @ResponseBody
    public String disapprove(String recruitFlow,String auditContent){
        RecruitInfoLog recruitInfoLog = new RecruitInfoLog();
        recruitInfoLog.setRecruitFlow(recruitFlow);
        recruitInfoLog.setAuditStatusId(RecruitStatusEnum.NoPassed.getId());
        recruitInfoLog.setAuditStatusName(RecruitStatusEnum.NoPassed.getName());
        recruitInfoLog.setOperTypeId(RecruitOperEnum.Audit.getId());
        recruitInfoLog.setOperTypeName(RecruitOperEnum.Audit.getName());
        recruitInfoLog.setAuditTime(DateUtil.getCurrDate());
        recruitInfoLog.setOperTime(DateUtil.getCurrDateTime2());
        if (recruitInfoBiz.disapprove(recruitFlow,auditContent) == 1 && recruitInfoLogBiz.updateRecruitInfoLog(recruitInfoLog,auditContent) == 1){
            return GlobalConstant.OPRE_SUCCESSED;
        }else {
            return GlobalConstant.OPRE_FAIL;
        }
    }
}
