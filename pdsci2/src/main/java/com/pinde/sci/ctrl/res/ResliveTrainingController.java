package com.pinde.sci.ctrl.res;

import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResLiveTrainingBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.sys.SysOrgExtMapper;
import com.pinde.core.model.ResDoctor;
import com.pinde.sci.model.mo.ResTarinNotice;
import com.pinde.sci.model.mo.ResTrainingOpinion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/liveTraining")
public class ResliveTrainingController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(ResliveTrainingController.class);

    @Autowired
    private IResLiveTrainingBiz resLiveTrainingBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private SysOrgExtMapper sysOrgExtMapper;

    @RequestMapping(value="/editOpinions")
    public String editOpinions(String trainingOpinionFlow,Model model){
        ResTrainingOpinion trainingOpinion = resLiveTrainingBiz.read(trainingOpinionFlow);
        model.addAttribute("trainingOpinion",trainingOpinion);
        return "res/doctor/editOpinions";
    }

    @RequestMapping(value="/delOpinions")
    @ResponseBody
    public String delOpinions(String trainingOpinionFlow){
        ResTrainingOpinion trainingOpinion = resLiveTrainingBiz.read(trainingOpinionFlow);
        trainingOpinion.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        resLiveTrainingBiz.edit(trainingOpinion);
        return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
    }

    @RequestMapping(value = "/opinions")
    public String options(Model model){
        SysUser currentUser = GlobalContext.getCurrentUser();
        String userFlow = currentUser.getUserFlow();
        List<ResTrainingOpinion> trainingOpinions=resLiveTrainingBiz.readByOpinionUserFlow(userFlow);
        model.addAttribute("trainingOpinions",trainingOpinions);
        return "res/doctor/opinions";
    }

    @RequestMapping(value = "/getOpinions")
    public String getOpinions(String opinionUserContent,String replayStatus, Model model, HttpServletRequest request, Integer currentPage){
        if(currentPage==null){
            currentPage=1;
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        SysUser currentUser = GlobalContext.getCurrentUser();
        String orgFlow = currentUser.getOrgFlow();
        List<ResTrainingOpinion> trainingOpinions=resLiveTrainingBiz.searchByOpinionUserContentAndReplayStatus(opinionUserContent,replayStatus,orgFlow);
        model.addAttribute("trainingOpinions",trainingOpinions);
        return "res/manager/getOpinions";
    }

    @RequestMapping(value = "/getOpinions4University")
    public String getOpinions4University(String opinionUserContent,String replayStatus, Model model, HttpServletRequest request,
                                         String orgFlow,String roleFlag,Integer currentPage){
        SysUser currentUser = GlobalContext.getCurrentUser();
        String currentOrgFlow = currentUser.getOrgFlow();
        if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
            //查询所有医院
            SysOrg orgTemp = new SysOrg();
            orgTemp.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
            List<SysOrg> orgList = orgBiz.searchOrg(orgTemp);
            model.addAttribute("orgList",orgList);
            if(currentPage==null){
                currentPage=1;
            }
            PageHelper.startPage(currentPage, getPageSize(request));
            List<ResTrainingOpinion> trainingOpinions=resLiveTrainingBiz.searchByOpinionUserContentAndReplayStatus(opinionUserContent,replayStatus,orgFlow);
            model.addAttribute("trainingOpinions",trainingOpinions);
            model.addAttribute("roleFlag",roleFlag);
        }else if(StringUtil.isNotBlank(currentOrgFlow)){
            SysOrg org = orgBiz.readSysOrg(currentOrgFlow);
            String workOrgId = org.getSendSchoolId();
            List<SysOrg> orgList = sysOrgExtMapper.searchOrgs4hbUniversity(workOrgId);
            model.addAttribute("orgList",orgList);

            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("opinionUserContent",opinionUserContent);
            paramMap.put("replayStatus",replayStatus);
            paramMap.put("orgFlow",orgFlow);
            paramMap.put("workOrgId",workOrgId);
            PageHelper.startPage(currentPage, getPageSize(request));
            List<ResTrainingOpinion> trainingOpinions=resLiveTrainingBiz.searchOpinionByUni4hb(paramMap);
            model.addAttribute("trainingOpinions",trainingOpinions);
        }
        return "res/manager/getOpinions4University";
    }


    @RequestMapping(value="/replyOpinions")
    public String replyOpinions(String trainingOpinionFlow,Model model){
        ResTrainingOpinion trainingOpinion=resLiveTrainingBiz.read(trainingOpinionFlow);
        model.addAttribute("trainingOpinion",trainingOpinion);
        return "res/manager/replyOpinions";
    }

    @RequestMapping(value="/saveOpinionReply")
    @ResponseBody
    public String saveOpinionReply(ResTrainingOpinion trainingOpinion){
        SysUser currentUser = GlobalContext.getCurrentUser();
        String userFlow = currentUser.getUserFlow();
        String userName = currentUser.getUserName();
        if(StringUtil.isNotBlank(userName)){
            trainingOpinion.setOpinionReplyName(userName);
        }
        trainingOpinion.setOpinionReplyUserFlow(userFlow);
        String currTime = DateUtil.getCurrDateTime();
        trainingOpinion.setReplyTime(currTime);
        resLiveTrainingBiz.edit(trainingOpinion);
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
    }

    @RequestMapping(value="/saveOpinions")
    @ResponseBody
    public String saveOpinions(ResTrainingOpinion trainingOpinion){
        SysUser currentUser = GlobalContext.getCurrentUser();
        String userFlow = currentUser.getUserFlow();
        String userName = currentUser.getUserName();
        if(StringUtil.isNotBlank(userName)){
            trainingOpinion.setOpinionUserName(userName);
        }
        trainingOpinion.setOpinionUserFlow(userFlow);
        String orgFlow ="";
        String orgName ="";
        if(StringUtil.isBlank(orgFlow)){
            ResDoctor doctor = resDoctorBiz.readDoctor(userFlow);
            if(StringUtil.isNotBlank(doctor.getSecondOrgFlow()))
            {
                orgFlow = doctor.getSecondOrgFlow();
                orgName = doctor.getSecondOrgName();
            }else {
                orgFlow = doctor.getOrgFlow();
                orgName = doctor.getOrgName();
            }
        }
        if(StringUtil.isNotBlank(orgFlow)){
            trainingOpinion.setOrgFlow(orgFlow);
        }
        if(StringUtil.isNotBlank(orgName)){
            trainingOpinion.setOrgName(orgName);
        }
        String currTime = DateUtil.getCurrDateTime();
        trainingOpinion.setEvaTime(currTime);
        resLiveTrainingBiz.edit(trainingOpinion);
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
    }

    @RequestMapping(value = "/director/{roleFlag}")
    public String director(String resNoticeTitle,String orgFlow,HttpServletRequest request, @PathVariable String roleFlag, Model model, Integer currentPage){
        if(currentPage==null){
            currentPage=1;
        }
        if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
            //查询所有医院
            SysOrg orgTemp = new SysOrg();
            orgTemp.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
            List<SysOrg> orgList = orgBiz.searchOrg(orgTemp);
            model.addAttribute("orgs",orgList);
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("resNoticeTitle",resNoticeTitle);
            paramMap.put("orgFlow",orgFlow);
            PageHelper.startPage(currentPage, getPageSize(request));
            List<ResTarinNotice> tarinNotices=resLiveTrainingBiz.searchByTitleOrgFlow4University(paramMap);
            model.addAttribute("tarinNotices",tarinNotices);
        }else{
            SysUser currUser = GlobalContext.getCurrentUser();
            orgFlow = currUser.getOrgFlow();
            ResDoctor doctor=resDoctorBiz.readDoctor(currUser.getUserFlow());
            if("doctor".equals(roleFlag) && doctor != null){
                 if(StringUtil.isNotBlank(doctor.getSecondOrgFlow()))
                 {
                     orgFlow = doctor.getSecondOrgFlow();
                 }else {
                     orgFlow = doctor.getOrgFlow();
                 }
            }
            PageHelper.startPage(currentPage, getPageSize(request));
            List<ResTarinNotice> tarinNotices = resLiveTrainingBiz.searchByTitleOrgFlow(resNoticeTitle,orgFlow, roleFlag);
            model.addAttribute("tarinNotices",tarinNotices);
        }
        model.addAttribute("roleFlag",roleFlag);
        return "/res/manager/director";
    }

    @RequestMapping(value = "/director4University")
    public String director4University(String resNoticeTitle,HttpServletRequest request,String orgFlow, Model model, Integer currentPage){
        SysUser currUser = GlobalContext.getCurrentUser();
        String currentOrgFlow = currUser.getOrgFlow();
        if(StringUtil.isNotBlank(currentOrgFlow)){
            SysOrg org = orgBiz.readSysOrg(currentOrgFlow);
            String workOrgId = org.getSendSchoolId();
            List<SysOrg> orgList = sysOrgExtMapper.searchOrgs4hbUniversity(workOrgId);
            model.addAttribute("orgList",orgList);

            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("resNoticeTitle",resNoticeTitle);
            paramMap.put("orgFlow",orgFlow);
            paramMap.put("workOrgId",workOrgId);
            PageHelper.startPage(currentPage, getPageSize(request));
            List<ResTarinNotice> tarinNotices=resLiveTrainingBiz.searchByTitleOrgFlow4University(paramMap);
            model.addAttribute("tarinNotices",tarinNotices);
        }
        return "/res/manager/director4University";
    }

    @RequestMapping(value = "/openDirector")
    public String openDirector(String recordFlow,String roleFlag,Model model){
        if(StringUtil.isNotBlank(recordFlow)){
            ResTarinNotice tarinNotice = resLiveTrainingBiz.readNotice(recordFlow);
            model.addAttribute("tarinNotice",tarinNotice);
            model.addAttribute("roleFlag",roleFlag);
            return "/res/manager/editDirector";
        }else{
            model.addAttribute("roleFlag",roleFlag);
            return "/res/manager/editDirector";
        }
    }

    @RequestMapping(value = "/saveDirector")
    @ResponseBody
    public String saveDirector(ResTarinNotice tarinNotice){
        SysUser current = GlobalContext.getCurrentUser();
        String orgFlow = current.getOrgFlow();
        String orgName = current.getOrgName();
        tarinNotice.setOrgName(orgName);
        tarinNotice.setOrgFlow(orgFlow);
        resLiveTrainingBiz.edit(tarinNotice);
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
    }

    @RequestMapping(value = "/delDirector")
    @ResponseBody
    public String delDirector(String recordFlow){
        ResTarinNotice tarinNotice = resLiveTrainingBiz.readNotice(recordFlow);
        tarinNotice.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        resLiveTrainingBiz.edit(tarinNotice);
        return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
    }

}
