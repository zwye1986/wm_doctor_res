package com.pinde.sci.ctrl.recruit;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.recruit.IRecruitAdmitInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitInfoBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.model.mo.RecruitAdmitInfo;
import com.pinde.core.model.SysUser;
import com.pinde.sci.model.recruit.RecruitInfoExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/recruit/admitInfoManage")
public class RecruitAdmitInfoManageController extends GeneralController {

    @Autowired
    private IRecruitInfoBiz recruitInfoBiz;

    @Autowired
    private IRecruitAdmitInfoBiz recruitAdmitInfoBiz;

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
        param.put("interviewIsPass", com.pinde.core.common.GlobalConstant.FLAG_Y);//面试通过
        List<RecruitInfoExt> recruitInfoExts = recruitInfoBiz.searchCanExamRecruitInfo(param);
        if(recruitInfoExts!=null)
        {
            Map<String, RecruitAdmitInfo> admitInfoMap=new HashMap<>();
            for(RecruitInfoExt recruitInfoExt:recruitInfoExts)
            {
                RecruitAdmitInfo admitInfo=recruitAdmitInfoBiz.readByFlow(recruitInfoExt.getRecruitFlow());
                admitInfoMap.put(recruitInfoExt.getRecruitFlow(),admitInfo);
            }
            model.addAttribute("admitInfoMap",admitInfoMap);
        }
        model.addAttribute("recruitInfoExts",recruitInfoExts);
        return "recruit/admitInfoManage/main";
    }



    @RequestMapping("/auditAdmit")
    @ResponseBody
    public String auditAdmit(String recordStatus,String recruitFlow){
        RecruitInfoExt recruitInfoExt = recruitInfoBiz.searchRecruitInfoByFlow(recruitFlow);
        if(recruitInfoExt==null)
        {
            return "未选择录取信息";
        }
        return recruitInfoBiz.auditAdmit(recordStatus, recruitFlow);
    }

    @RequestMapping("/sendAdmitForm")
    public String sendInterviewForm(String recruitFlow,Model model){
        RecruitInfoExt recruitInfoExt = recruitInfoBiz.searchRecruitInfoByFlow(recruitFlow);
        if (recruitInfoExt != null){
            model.addAttribute("admitInfoForm",recruitInfoExt);
        }
        return "recruit/admitInfoManage/admitInfoForm";
    }
    @RequestMapping("/sendAdmitAllForm")
    public String sendAdmitAllForm(String []recruitFlows,Model model){
        model.addAttribute("recruitFlows", Arrays.asList(recruitFlows));
        model.addAttribute("size",recruitFlows.length);
        return "recruit/admitInfoManage/sendAdmitAllForm";
    }

    @RequestMapping("/showAdmitForm")
    public String showInterviewForm(String recruitFlow,Model model){
        RecruitInfoExt recruitInfoExt = recruitInfoBiz.searchRecruitInfoByFlow(recruitFlow);
        model.addAttribute("admitInfoForm",recruitInfoExt);
        RecruitAdmitInfo admitInfo=recruitAdmitInfoBiz.readByFlow(recruitFlow);
        model.addAttribute("admitInfo",admitInfo);
        return "recruit/admitInfoManage/showAdmitForm";
    }
    /**
     * 发送录取通知
     * @param recruitAdmitInfo
     * @return
     */
    @RequestMapping("/sendAdmit")
    @ResponseBody
    public String sendAdmit(RecruitAdmitInfo recruitAdmitInfo){
        String recruitFlow = recruitAdmitInfo.getRecruitFlow();
        //检验是否拥有录取资格
        Boolean qualifyAdmit = recruitAdmitInfoBiz.isQualifyAdmit(recruitFlow);
        if(!qualifyAdmit)
        {
            return "已发送过录取通知，请刷新列表页面！";
        }
        RecruitInfoExt recruitInfoExt = recruitInfoBiz.searchRecruitInfoByFlow(recruitFlow);
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitInfoExt.getInterviewIsPass()))
        {
            return "该学员招录面试未通过，无法发送录取通知！";
        }
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitInfoExt.getAdmitFlag()))
        {
            return "已发送过面试通知，请刷新列表页面！";
        }
        //执行录取
        if (recruitAdmitInfoBiz.sendAdmit(recruitAdmitInfo)){
            return com.pinde.core.common.GlobalConstant.SEND_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.SEND_FAIL;
    }
    /**
     * 发送录取通知
     * @param recruitAdmitInfo
     * @return
     */
    @RequestMapping("/sendAdmitAll")
    @ResponseBody
    public String sendAdmitAll(RecruitAdmitInfo recruitAdmitInfo,String []recruitFlows){
        if(recruitFlows==null||recruitFlows.length==0)
        {
            return "请选择需要发送录取通知的学员";
        }
        List<RecruitInfoExt> recruitInfoExts=new ArrayList<>();
        for(String recruitFlow:recruitFlows)
        {
            RecruitInfoExt recruitInfoExt = recruitInfoBiz.searchRecruitInfoByFlow(recruitFlow);
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitInfoExt.getInterviewIsPass()))
            {
                return "学员【"+recruitInfoExt.getSysUser().getUserName()+"】招录面试未通过，无法发送录取通知！";
            }
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitInfoExt.getAdmitFlag()))
            {
                return "学员【"+recruitInfoExt.getSysUser().getUserName()+"】已发送过面试通知，请刷新列表页面！";
            }
            //检验是否拥有录取资格
            Boolean qualifyAdmit = recruitAdmitInfoBiz.isQualifyAdmit(recruitFlow);
            if(!qualifyAdmit)
            {
                return "学员【"+recruitInfoExt.getSysUser().getUserName()+"】已发送过录取通知，请刷新列表页面！";
            }
            recruitInfoExts.add(recruitInfoExt);
        }
        //执行录取
        if (recruitAdmitInfoBiz.sendAdmitAll(recruitInfoExts,recruitAdmitInfo)){
            return com.pinde.core.common.GlobalConstant.SEND_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.SEND_FAIL;
    }


    @RequestMapping("/importAdmitResult")
    public String importAdmitResult(Model model, HttpServletRequest request){
        return "recruit/admitInfoManage/importAdmitResult";
    }

    @RequestMapping(value="/saveImportAdmitResult")
    @ResponseBody
    public String saveImportAdmitResult(MultipartFile file, String recruitYear){
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
            param.put("interviewIsPass", com.pinde.core.common.GlobalConstant.FLAG_Y);//面试通过
            List<RecruitInfoExt> recruitInfoExts = recruitInfoBiz.searchCanExamRecruitInfo(param);
            if(recruitInfoExts==null||recruitInfoExts.size()==0)
            {
                return recruitYear+"年没有面试通过的人员信息，请勿导入！";
            }
            try{
                ExcelUtile result = (ExcelUtile) recruitInfoBiz.saveImportAdmitResult(file,recruitYear,recruitInfoExts);
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
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
            }

        }
        return "文件内容不能为空";
    }
    @RequestMapping("/importAdmitFromExcel")
    @ResponseBody
    public String importInterviewFromExcel(MultipartFile file, String type) {
        String message = "";
        if (file != null && file.getSize() > 0) {
            try {
                int result = recruitAdmitInfoBiz.importAdmitFromExcel(file, type);
                if(result<0)
                {
                    message ="导入文件内容为空！请确认后导入！";
                    return message;
                }
                if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                    message = com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + result + "条记录！";
                }
            } catch (RuntimeException e) {
                logger.error("", e);
                message = e.getMessage();
            }
        }
        return message;
    }

    private static Logger logger = LoggerFactory.getLogger(RecruitAdmitInfoManageController.class);

}
