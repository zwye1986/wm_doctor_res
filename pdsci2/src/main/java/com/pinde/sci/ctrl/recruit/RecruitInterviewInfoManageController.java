package com.pinde.sci.ctrl.recruit;

import com.alibaba.fastjson.JSONArray;
import com.pinde.core.common.GlobalConstant;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.recruit.IRecruitInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitInterviewInfoBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.model.mo.RecruitInfo;
import com.pinde.sci.model.mo.RecruitInterviewInfo;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.recruit.RecruitInfoExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/recruit/interviewInfoManage")
public class RecruitInterviewInfoManageController extends GeneralController {

    @Autowired
    private IRecruitInfoBiz recruitInfoBiz;

    @Autowired
    private IRecruitInterviewInfoBiz recruitInterviewInfoBiz;

    /**
     * 所有查询方法
     * @param model
     * @param currentPage
     * @param request
     * @param userName
     * @return
     */
    @RequestMapping("/main")
    public String main(Model model, Integer currentPage, HttpServletRequest request,String userName,String idNo,
                       String recruitYear,String beginDate,String endDate){
        PageHelper.startPage(currentPage,getPageSize(request));
        SysUser user = GlobalContext.getCurrentUser();
        String orgFlow=user.getOrgFlow();

        PageHelper.startPage(currentPage,getPageSize(request));
        Map<String,String> param=new HashMap<>();
        param.put("orgFlow",orgFlow);
        param.put("recruitYear",recruitYear);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("startDate",beginDate);
        param.put("endDate",endDate);
        param.put("writeExamFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
        param.put("examIsPass", com.pinde.core.common.GlobalConstant.FLAG_Y);//笔试通过
        List<RecruitInfoExt> recruitInfoExts = recruitInfoBiz.searchCanExamRecruitInfo(param);
        if(recruitInfoExts!=null)
        {
            Map<String,RecruitInterviewInfo> interviewInfoMap=new HashMap<>();
            for(RecruitInfoExt recruitInfoExt:recruitInfoExts)
            {
                RecruitInterviewInfo interviewInfo=recruitInterviewInfoBiz.readByFlow(recruitInfoExt.getRecruitFlow());
                interviewInfoMap.put(recruitInfoExt.getRecruitFlow(),interviewInfo);
            }
            model.addAttribute("interviewInfoMap",interviewInfoMap);
        }
        model.addAttribute("recruitInfoExts",recruitInfoExts);
        return "recruit/interviewInfoManage/main";
    }


    @RequestMapping("/auditInterview")
    @ResponseBody
    public String auditInterview(String recordStatus,String recruitFlow){

        RecruitInfoExt recruitInfoExt = recruitInfoBiz.searchRecruitInfoByFlow(recruitFlow);
        if(recruitInfoExt==null)
        {
            return "未选择面试信息";
        }
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitInfoExt.getAdmitFlag()))
        {
            return "已发录取通知，无法审核！";
        }
        return recruitInfoBiz.auditInterview(recordStatus, recruitFlow);
    }


    @RequestMapping("/sendInterviewForm")
    public String sendInterviewForm(String recruitFlow,Model model){
        RecruitInfoExt recruitInfoExt = recruitInfoBiz.searchRecruitInfoByFlow(recruitFlow);
        if (recruitInfoExt != null){
            model.addAttribute("recruitInfo",recruitInfoExt);
        }
        return "recruit/interviewInfoManage/interviewInfoForm";
    }

    @RequestMapping("/showInterviewForm")
    public String showInterviewForm(String recruitFlow,Model model){
        RecruitInfoExt recruitInfoExt = recruitInfoBiz.searchRecruitInfoByFlow(recruitFlow);
        model.addAttribute("recruitInfo",recruitInfoExt);
        RecruitInterviewInfo interviewInfo=recruitInterviewInfoBiz.readByFlow(recruitFlow);
        model.addAttribute("interviewInfo",interviewInfo);
        return "recruit/interviewInfoManage/showInterviewForm";
    }

    /**
     * 发送面试通知
     * @param recruitInterviewInfo
     * @return
     */
    @RequestMapping("/sendInterview")
    @ResponseBody
    public String sendInterview(RecruitInterviewInfo recruitInterviewInfo){
        String recruitFlow = recruitInterviewInfo.getRecruitFlow();
        Boolean qualifyInterview = recruitInterviewInfoBiz.isQualifyInterview(recruitFlow);
        if(!qualifyInterview)
        {
            return "已发送过面试通知，请刷新列表页面！";
        }
        RecruitInfoExt recruitInfoExt = recruitInfoBiz.searchRecruitInfoByFlow(recruitFlow);
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitInfoExt.getExamIsPass()))
        {
            return "该学员招录考试未通过，无法发送面试通知！";
        }
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitInfoExt.getInterviewFlag()))
        {
            return "已发送过面试通知，请刷新列表页面！";
        }
        if (recruitInterviewInfoBiz.sendInterview(recruitInterviewInfo)){
            return com.pinde.core.common.GlobalConstant.SEND_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.SEND_FAIL;
    }

    @RequestMapping("/sendAll")
    @ResponseBody
    public String sendAll(@RequestParam String chkList){
        List<String> list =(List<String>) JSONArray.parse(chkList);
        List<RecruitInfoExt> recruitInfoExtList = new ArrayList<RecruitInfoExt>();
        for (String recruitFlow :list) {
            RecruitInfoExt recruitInfoExt = recruitInfoBiz.searchRecruitInfoByFlow(recruitFlow);
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitInfoExt.getExamIsPass()))
            {
                return "该学员【"+recruitInfoExt.getSysUser().getUserName()+"】招录考试未通过，无法发送面试通知！";
            }
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitInfoExt.getInterviewFlag()))
            {
                return "该学员【"+recruitInfoExt.getSysUser().getUserName()+"】已发送过面试通知，请刷新列表页面！";
            }
            Boolean qualifyInterview = recruitInterviewInfoBiz.isQualifyInterview(recruitFlow);
            if(!qualifyInterview)
            {
                return "该学员【"+recruitInfoExt.getSysUser().getUserName()+"】已发送过面试通知，请刷新列表页面！";
            }
            recruitInfoExtList.add(recruitInfoExt);
        }
        recruitInterviewInfoBiz.sendAllInterview(recruitInfoExtList);
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
    }

    @RequestMapping("/importInterviewScore")
    public String importInterviewScore(Model model, HttpServletRequest request){
        return "recruit/interviewInfoManage/importInterviewScore";
    }

    @RequestMapping("/changeInterviewScore")
    public String changeExamScore(Model model,String recruitFlow, HttpServletRequest request){
        SysUser user = GlobalContext.getCurrentUser();
        String orgFlow=user.getOrgFlow();

        RecruitInfoExt recruitInfoExt = recruitInfoBiz.searchRecruitInfoByFlow(recruitFlow);
        model.addAttribute("recruitInfoExt",recruitInfoExt);
        return "recruit/interviewInfoManage/changeInterviewScore";
    }

    @RequestMapping("/saveInterviewScore")
    @ResponseBody
    public String saveInterviewScore(RecruitInfo recruitInfo){
        if(StringUtil.isBlank(recruitInfo.getInterviewScore()))
        {
            return "请输入成绩！";
        }
        boolean f = recruitInfo.getInterviewScore().matches("^((0|[1-9][0-9]{0,2}+)([.]([0-9]{0,1}+))?)$");
        if (!f) {
            return "成绩格式不正确[可以为0，第一个数字不能为0，且最多一位小数]，请确认后提交！！";
        } else {
            if (Double.valueOf(recruitInfo.getInterviewScore()) > 100 || Double.valueOf(recruitInfo.getInterviewScore()) <0) {
                return "成绩不得大于100或小于0，请确认后提交！！" ;
            }
        }
        RecruitInfoExt recruitInfoExt=recruitInfoBiz.searchRecruitInfoByFlow(recruitInfo.getRecruitFlow());
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitInfoExt.getAdmitFlag()))
        {
            return "已发录取通知，无法修改成绩！";
        }
        int c= recruitInfoBiz.saveRecruitInfo(recruitInfo);
        if(c==0)
        {
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        }
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
    }
    @RequestMapping(value="/saveImportInterviewScore")
    @ResponseBody
    public String saveImportInterviewScore(MultipartFile file, String recruitYear){
        if(file.getSize() > 0){
            if(StringUtil.isBlank(recruitYear))
            {
                return "请选择招录年份";
            }
            SysUser user = GlobalContext.getCurrentUser();
            String orgFlow=user.getOrgFlow();

            Map<String,String> param=new HashMap<>();
            param.put("orgFlow",orgFlow);
            param.put("recruitYear",recruitYear);
            param.put("writeExamFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
            param.put("examIsPass", com.pinde.core.common.GlobalConstant.FLAG_Y);//笔试通过
            List<RecruitInfoExt> recruitInfoExts = recruitInfoBiz.searchCanExamRecruitInfo(param);
            if(recruitInfoExts==null||recruitInfoExts.size()==0)
            {
                return recruitYear+"年没有考试通过的人员信息，请勿导入！";
            }
            try{
                ExcelUtile result = (ExcelUtile) recruitInfoBiz.saveImportInterviewScore(file,recruitYear,recruitInfoExts);
                if(null!=result)
                {
                    String code= (String) result.get("code");
                    int count=(Integer) result.get("count");
                    String msg= (String) result.get("msg");
                    if("1".equals(code))
                    {
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;

                    }else{
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
                        }else{
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                        }
                    }
                }else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                }
            }catch(RuntimeException re){
                re.printStackTrace();
                return re.getMessage();
            }

        }
        return "文件内容不能为空";
    }




}