package com.pinde.sci.ctrl.recruit;

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

import java.util.List;

@Controller
@RequestMapping("/recruit/interviewInfo")
public class RecruitInterviewInfoController extends GeneralController {

    @Autowired
    private IRecruitInfoBiz recruitInfoBiz;

    @Autowired
    private IRecruitInterviewInfoBiz recruitInterviewInfoBiz;

    @RequestMapping("/main")
    public String main(Model model){
        SysUser currentUser = GlobalContext.getCurrentUser();
        List<RecruitInfoExt> recruitInfoExts = recruitInfoBiz.selectRecruitByUserFlow(currentUser.getUserFlow(), currentUser.getOrgFlow());
        if (recruitInfoExts != null && recruitInfoExts.size() > 0){
            for (int i = recruitInfoExts.size() - 1 ; i >= 0 ; i--){
                if (recruitInfoExts.get(i).getInterviewFlag().equals(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N)) {
                    recruitInfoExts.remove(i);
                }
            }
        }
        model.addAttribute("recruitInfoExts",recruitInfoExts);
        return "recruit/doctor/interviewInfo";
    }

    @RequestMapping("/examineInterviewInfo")
    public String examineInterviewInfo(Model model,String recruitFlow){
        SysUser user = GlobalContext.getCurrentUser();
        String orgFlow=user.getOrgFlow();
        RecruitInterviewInfo recruitInterviewInfo = recruitInterviewInfoBiz.searchByExample(recruitFlow, orgFlow);
        model.addAttribute("user",user);
        model.addAttribute("recruitInterviewInfo",recruitInterviewInfo);
        return "recruit/doctor/interviewAdvise";
    }


}
