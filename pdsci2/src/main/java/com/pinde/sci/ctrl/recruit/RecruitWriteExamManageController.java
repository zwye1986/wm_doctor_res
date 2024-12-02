package com.pinde.sci.ctrl.recruit;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.recruit.IRecruitCfgInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitInfoBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.model.mo.RecruitInfo;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.recruit.RecruitInfoExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/recruit/writeExamManage")
public class RecruitWriteExamManageController extends GeneralController {

    @Autowired
    private IRecruitInfoBiz recruitInfoBiz;

    @Autowired
    private IRecruitCfgInfoBiz recruitCfgInfoBiz;

    @RequestMapping("/main")
    public String main(Model model, Integer currentPage, HttpServletRequest request,String userName,String idNo,
                       String recruitYear,String beginDate,String endDate){
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
        List<RecruitInfoExt> recruitInfoExts = recruitInfoBiz.searchCanExamRecruitInfo(param);

        model.addAttribute("recruitInfoExts",recruitInfoExts);

        return "recruit/writeExamManage/main";
    }

    @RequestMapping("/changeExamScore")
    public String changeExamScore(Model model,String recruitFlow, HttpServletRequest request){
        SysUser user = GlobalContext.getCurrentUser();
        String orgFlow=user.getOrgFlow();

        RecruitInfoExt recruitInfoExt = recruitInfoBiz.searchRecruitInfoByFlow(recruitFlow);
        model.addAttribute("recruitInfoExt",recruitInfoExt);
        return "recruit/writeExamManage/changeExamScore";
    }
    @RequestMapping("/importScore")
    public String importScore(Model model, HttpServletRequest request){
        return "recruit/writeExamManage/importScore";
    }
    @RequestMapping("/saveExamScore")
    @ResponseBody
    public String saveExamScore(RecruitInfo recruitInfo){
        if(StringUtil.isBlank(recruitInfo.getExamScore()))
        {
            return "请输入成绩！";
        }
        boolean f = recruitInfo.getExamScore().matches("^((0|[1-9][0-9]{0,2}+)([.]([0-9]{0,1}+))?)$");
        if (!f) {
            return "成绩格式不正确[可以为0，第一个数字不能为0，且最多一位小数]，请确认后提交！！";
        } else {
            if (Double.valueOf(recruitInfo.getExamScore()) > 100 || Double.valueOf(recruitInfo.getExamScore()) <0) {
                return "成绩不得大于100或小于0，请确认后提交！！" ;
            }
        }
        RecruitInfoExt recruitInfoExt=recruitInfoBiz.searchRecruitInfoByFlow(recruitInfo.getRecruitFlow());
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitInfoExt.getInterviewFlag()))
        {
            return "已发面试通知，无法修改成绩！";
        }
        int c= recruitInfoBiz.saveRecruitInfo(recruitInfo);
        if(c==0)
        {
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        }
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
    }
    @RequestMapping("/auditWriteExam")
    @ResponseBody
    public String auditWriteExam(String recordStatus,String recruitFlow){

        RecruitInfoExt recruitInfoExt = recruitInfoBiz.searchRecruitInfoByFlow(recruitFlow);
        if(recruitInfoExt==null)
        {
            return "未选择考试信息";
        }
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitInfoExt.getInterviewFlag()))
        {
            return "已发面试通知，无法审核！";
        }
        return recruitInfoBiz.auditWriteExam(recordStatus, recruitFlow);
    }

    @RequestMapping(value="/importExamScore")
    @ResponseBody
    public String importExamScore(MultipartFile file, String recruitYear){
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
            List<RecruitInfoExt> recruitInfoExts = recruitInfoBiz.searchCanExamRecruitInfo(param);
            if(recruitInfoExts==null||recruitInfoExts.size()==0)
            {
                return recruitYear+"年没有审核通过的人员信息，请勿导入！";
            }
            try{
                ExcelUtile result = (ExcelUtile) recruitInfoBiz.importExamScore(file,recruitYear,recruitInfoExts);
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
