package com.pinde.sci.ctrl.recruit;

import com.alibaba.fastjson.JSON;
import com.pinde.core.model.RecruitCfgInfo;
import com.pinde.sci.biz.recruit.IRecruitCfgInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitExamMainBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/recruit/cfgInfo")
public class RecruitCfgInfoController extends GeneralController {

    @Autowired
    private IRecruitCfgInfoBiz recruitCfgInfoBiz;

    @Autowired
    private IRecruitExamMainBiz recruitExamMainBiz;

    @RequestMapping("/main")
    public String main(Model model){
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        //查询出已存在的 招考年份
        List<String> recruitYearList = recruitCfgInfoBiz.searchAllRecruitYear(orgFlow);
        model.addAttribute("recruitYearList",recruitYearList);
        RecruitCfgInfo recruitCfgInfo = null;
        if (recruitYearList != null){
            recruitCfgInfo = recruitCfgInfoBiz.searchCfgInfoByYear(recruitYearList.get(0),orgFlow);
        }
        model.addAttribute("recruitCfgInfo",recruitCfgInfo);
        return "recruit/cfg/recruitCfgInfo";
    }

    @RequestMapping("/updateCfgInfo")
    @ResponseBody
    public String updateCfgInfo(RecruitCfgInfo recruitCfgInfo){
        int i = recruitCfgInfoBiz.updateRecCfgInfo(recruitCfgInfo);
        if (i == 1){
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
        }else {
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        }
    }

    @RequestMapping("/addCfgForm")
    public String addCfgForm(){
        return "recruit/cfg/addCfgInfo";
    }

    @RequestMapping("/addCfgInfo")
    @ResponseBody
    public String addCfgInfo(RecruitCfgInfo recruitCfgInfo){
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        RecruitCfgInfo searchResult = recruitCfgInfoBiz.searchCfgInfoByYear(recruitCfgInfo.getRecruitYear(),orgFlow);
        if (searchResult == null ){
            int i = recruitCfgInfoBiz.addRecCfgInfo(recruitCfgInfo);
            if (i == 1){
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
            }else{
                return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
            }
        }else{
            return "该年份已设置招录时间！";
        }
    }

    @RequestMapping("/searchCfgInfo")
    @ResponseBody
    public String searchCfgInfo(RecruitCfgInfo recruitCfgInfo){
        RecruitCfgInfo searchResult = recruitCfgInfoBiz.searchCfgInfoByYear(recruitCfgInfo.getRecruitYear(),GlobalContext.getCurrentUser().getOrgFlow());
        return JSON.toJSONString(searchResult);
    }

    @RequestMapping("/setCurrYear")
    @ResponseBody
    public String setCurrYear(RecruitCfgInfo recruitCfgInfo){
        int i = recruitCfgInfoBiz.RecruitCfgInfo(recruitCfgInfo.getCfgFlow(),GlobalContext.getCurrentUser().getOrgFlow());
        if (i == 1){
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
        }else {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
        }
    }
}
