package com.pinde.sci.ctrl.recruit;


import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.recruit.IRecruitInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitInterviewInfoBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.RecruitInterviewInfo;
import com.pinde.core.model.SysUser;
import com.pinde.sci.model.recruit.RecruitInfoExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/recruit/examCard")
@Controller
public class RecruitExamCardController extends GeneralController {

    @Autowired
    private IRecruitInfoBiz recruitInfoBiz;

    @Autowired
    private IRecruitInterviewInfoBiz interviewInfoBiz;

    @RequestMapping("/main")
    public String main(Model model, Integer currentPage,String recruitYear,String userName,String idNo,
                       String startDate,String endDate, HttpServletRequest request){
        SysUser user = GlobalContext.getCurrentUser();
        String orgFlow=user.getOrgFlow();

        PageHelper.startPage(currentPage,getPageSize(request));
        Map<String,String> param=new HashMap<>();
        param.put("orgFlow",orgFlow);
        param.put("recruitYear",recruitYear);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("startDate",startDate);
        param.put("endDate",endDate);
        param.put("writeExamFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
        List<RecruitInfoExt> recruitInfoExts = recruitInfoBiz.searchCanExamRecruitInfo(param);
        model.addAttribute("recruitInfoExts",recruitInfoExts);
        return "recruit/examCard/main";
    }

    @RequestMapping("/showExamCard")
    public String showExamCard(Model model,String recruitFlow, HttpServletRequest request){
        SysUser user = GlobalContext.getCurrentUser();
        String orgFlow=user.getOrgFlow();

        RecruitInfoExt recruitInfoExt = recruitInfoBiz.searchRecruitInfoByFlow(recruitFlow);
        model.addAttribute("recruitInfoExt",recruitInfoExt);
        RecruitInterviewInfo interviewInfo=interviewInfoBiz.readByFlow(recruitFlow);
        model.addAttribute("interviewInfo",interviewInfo);
        return "recruit/examCard/showExamCard";
    }
}
