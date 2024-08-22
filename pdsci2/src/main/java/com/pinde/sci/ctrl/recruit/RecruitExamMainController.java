package com.pinde.sci.ctrl.recruit;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.sci.biz.recruit.IRecruitCfgInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitExamInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitExamMainBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.form.recruit.ExamInfoFlowForm;
import com.pinde.sci.model.mo.RecruitCfgInfo;
import com.pinde.sci.model.mo.RecruitExamMain;
import com.pinde.sci.model.mo.RecruitInfo;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/recruit/examMain")
@Controller
public class RecruitExamMainController extends GeneralController {

    @Autowired
    private IRecruitExamMainBiz recruitExamMainBiz;

    @Autowired
    private IRecruitCfgInfoBiz recruitCfgInfoBiz;

    @Autowired
    private IRecruitExamInfoBiz recruitExamInfoBiz;

    @RequestMapping("/main")
    public String main(Model model, Integer currentPage,RecruitExamMain recruitExamMain, HttpServletRequest request){
        SysUser user = GlobalContext.getCurrentUser();
        recruitExamMain.setOrgFlow(user.getOrgFlow());

        PageHelper.startPage(currentPage,getPageSize(request));

        Map<String,String> fenPeiMap=new HashMap<>();
        List<RecruitExamMain> examMains=recruitExamMainBiz.searchAll(recruitExamMain);
        if(examMains!=null)
        {
            for(RecruitExamMain main:examMains)
            {
                List<RecruitInfo> recruitInfos=recruitExamMainBiz.readMainRecruitInfos(main.getMainFlow());
                if(recruitInfos!=null&&recruitInfos.size()>0)
                {
                    fenPeiMap.put(main.getMainFlow(),"Y");
                }
            }
        }
        model.addAttribute("fenPeiMap",fenPeiMap);
        model.addAttribute("examMains",examMains);
        Map<String,RecruitCfgInfo> cfgMap=new HashMap<>();
        List<RecruitCfgInfo> cfgs = recruitCfgInfoBiz.cfgs(user.getOrgFlow());
        if(cfgs!=null) {
            for (RecruitCfgInfo cfgInfo : cfgs)
            {
                cfgMap.put(cfgInfo.getCfgFlow(),cfgInfo);
            }
        }
        model.addAttribute("cfgs",cfgs);
        model.addAttribute("cfgMap",cfgMap);
        return "recruit/exam/main";
    }
    @RequestMapping("/addExamMain")
    public String addExamMain(Model model,String mainFlow, HttpServletRequest request){
        RecruitExamMain recruitExamMain=recruitExamMainBiz.readByFlow(mainFlow);
        model.addAttribute("main",recruitExamMain);
        String orgFlow=GlobalContext.getCurrentUser().getOrgFlow();

        List<RecruitCfgInfo> cfgs = recruitCfgInfoBiz.cfgs(orgFlow);
        model.addAttribute("cfgs",cfgs);

        return "recruit/exam/addExamMain";
    }
    @RequestMapping("/showExamMain")
    public String showExamMain(Model model,String mainFlow, HttpServletRequest request){
        RecruitExamMain recruitExamMain=recruitExamMainBiz.readByFlow(mainFlow);
        model.addAttribute("main",recruitExamMain);
        String orgFlow=GlobalContext.getCurrentUser().getOrgFlow();

        List<RecruitCfgInfo> cfgs = recruitCfgInfoBiz.cfgs(orgFlow);
        model.addAttribute("cfgs",cfgs);

        return "recruit/exam/showExamMain";
    }
    @RequestMapping("/delExam")
    @ResponseBody
    public String delExam(Model model,String mainFlow, HttpServletRequest request){
        RecruitExamMain recruitExamMain=recruitExamMainBiz.readByFlow(mainFlow);
        if(recruitExamMain==null||GlobalConstant.RECORD_STATUS_N.equals(recruitExamMain.getRecordStatus()))
        {
            return "考试信息不存在，请刷新列表";
        }
        if(GlobalConstant.RECORD_STATUS_Y.equals(recruitExamMain.getIsPublish()))
        {
            return "考试信息已发布，无法删除，请刷新列表";
        }
        recruitExamMain.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        int c=recruitExamMainBiz.saveExamMain(recruitExamMain);
        if(c==0)
        {
            return GlobalConstant.DELETE_FAIL;
        }else{
            //删除子表配置信息
            recruitExamMainBiz.delExamDetail(recruitExamMain.getMainFlow());
        }
        return GlobalConstant.DELETE_SUCCESSED;
    }
    @RequestMapping("/publishExam")
    @ResponseBody
    public String publishExam(Model model,String mainFlow, HttpServletRequest request){
        RecruitExamMain recruitExamMain=recruitExamMainBiz.readByFlow(mainFlow);
        if(recruitExamMain==null||GlobalConstant.RECORD_STATUS_N.equals(recruitExamMain.getRecordStatus()))
        {
            return "考试信息不存在，请刷新列表";
        }
        if(GlobalConstant.RECORD_STATUS_Y.equals(recruitExamMain.getIsPublish()))
        {
            return "考试信息已发布，无法重新发布，请刷新列表";
        }
        RecruitCfgInfo cfgInfo=recruitCfgInfoBiz.readByFlow(recruitExamMain.getCfgFlow());
        if(cfgInfo==null)
        {
            return "招录配置信息不存在，请重新编辑";
        }
        if(!GlobalConstant.FLAG_Y.equals(cfgInfo.getIsRecruit()))
        {
            return "此考试信息非当前招录年份考试，无法发布！";
        }
        String nowDate=DateUtil.getCurrDate();
        if(nowDate.compareTo(cfgInfo.getRecruitEndDate())<0)
        {
            return "此招录信息还未结束，无法发布考试信息！";
        }
        //判断当前人员是否已经都审核通过
        int checkNum=recruitExamMainBiz.checkAuditingNum(recruitExamMain.getRecruitYear(),recruitExamMain.getOrgFlow());
        if(checkNum>0)
        {
            return "此招录信息还存在未审核学员信息！";
        }
        //判断考试配置的人数与审核通过的人员数量是否一致
        int passedNum=recruitExamMainBiz.checkPassedNum(recruitExamMain.getRecruitYear(),recruitExamMain.getOrgFlow());
        if(passedNum<=0)
        {
            return "此招录信息无审核通过学员信息，无法发布！";
        }
        int examNum=recruitExamMainBiz.getExamNum(recruitExamMain.getMainFlow());
        if(examNum<=0)
        {
            return "此考核信息未设置考核人数，无法发布！";
        }
        if(examNum<passedNum)
        {

            return "考核人数容量小于审核通过人数，无法发布，请重新编辑！";
        }
        recruitExamMain.setIsPublish(GlobalConstant.FLAG_Y);
        int c=recruitExamMainBiz.saveExamMain(recruitExamMain);
        if(c==0)
        {
            return GlobalConstant.OPRE_FAIL;
        }
        return GlobalConstant.OPRE_SUCCESSED;
    }
    @RequestMapping("/editMainNote")
    public String editMainNote(Model model,String mainFlow, HttpServletRequest request){
        RecruitExamMain recruitExamMain=recruitExamMainBiz.readByFlow(mainFlow);
        model.addAttribute("main",recruitExamMain);
        return "recruit/exam/editMainNote";
    }
    @RequestMapping("/saveMainNote")
    @ResponseBody
    public String saveMainNote(Model model,RecruitExamMain recruitExamMain, HttpServletRequest request){
        int c=recruitExamMainBiz.saveExamMain(recruitExamMain);
        if(c==0)
        {
            return GlobalConstant.SAVE_FAIL;
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping("/saveExamMain")
    @ResponseBody
    public String saveExamMain(RecruitExamMain recruitExamMain){
        SysUser user = GlobalContext.getCurrentUser();
        String orgFlow=user.getOrgFlow(); //获取当前登陆用户

        RecruitExamMain old=recruitExamMainBiz.readByCfgFlow(recruitExamMain.getCfgFlow(),orgFlow);
        if(old!=null&&!old.getMainFlow().equals(recruitExamMain.getMainFlow()))
        {
            return "该年招录考试信息已存在！";
        }
        RecruitCfgInfo cfgInfo=recruitCfgInfoBiz.readByFlow(recruitExamMain.getCfgFlow());
        if(cfgInfo==null)
        {
            return "该年招录配置未设置";
        }
        recruitExamMain.setRecruitYear(cfgInfo.getRecruitYear());
        recruitExamMain.setOrgFlow(orgFlow);
        int i = recruitExamMainBiz.saveExamMain(recruitExamMain);
        if (i == 1){
            return GlobalConstant.SAVE_SUCCESSED;
        }else {
            return GlobalConstant.SAVE_FAIL;
        }
    }
    @RequestMapping("/fenPei")
    @ResponseBody
    public String fenPei(String mainFlow){
        SysUser user = GlobalContext.getCurrentUser();
        String orgFlow=user.getOrgFlow(); //获取当前登陆用户

        RecruitExamMain old=recruitExamMainBiz.readByFlow(mainFlow);
        if(old==null)
        {
            return "未选择需要分配的招录信息！";
        }
        //所有审核通过的人员
        List<RecruitInfo> recruitInfos=recruitExamMainBiz.readPassedRecruitInfos(orgFlow,old.getRecruitYear());
        if(recruitInfos==null||recruitInfos.size()==0)
        {
            return  "无审核通过学员";
        }
        List<ExamInfoFlowForm> flowForms=recruitExamMainBiz.readExamInfoFlowFormsByFlow(mainFlow);
        if(flowForms==null||flowForms.size()==0)
        {
            return  "未设置考场信息";
        }
        //判断考试配置的人数与审核通过的人员数量是否一致
        int passedNum=recruitExamMainBiz.checkPassedNum(old.getRecruitYear(),old.getOrgFlow());
        if(passedNum<=0)
        {
            return "此招录信息无审核通过学员信息，无法分配！";
        }
        int examNum=recruitExamMainBiz.getExamNum(mainFlow);
        if(examNum<=0)
        {
            return "此考核信息未设置考核人数，无法分配！";
        }
        if(examNum<passedNum)
        {

            return "考核人数容量小于审核通过人数，无法发布，请重新编辑！";
        }
        recruitExamMainBiz.fenpei(recruitInfos,flowForms);

        return GlobalConstant.OPERATE_SUCCESSED;
    }
}
