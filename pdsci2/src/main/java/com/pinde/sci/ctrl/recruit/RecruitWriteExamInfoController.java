package com.pinde.sci.ctrl.recruit;

import com.pinde.core.common.enums.recruit.RecruitStatusEnum;
import com.pinde.core.model.RecruitExamMain;
import com.pinde.core.model.RecruitInterviewInfo;
import com.pinde.core.model.SysUser;
import com.pinde.sci.biz.recruit.IRecruitExamMainBiz;
import com.pinde.sci.biz.recruit.IRecruitInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitInterviewInfoBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.recruit.RecruitInfoExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/recruit/writeExamInfo")
@Controller
public class RecruitWriteExamInfoController extends GeneralController {

    @Autowired
    private IUserBiz userBiz;

    @Autowired
    private IRecruitInfoBiz recruitInfoBiz;

    @Autowired
    private IRecruitExamMainBiz recruitExamMainBiz;

    @Autowired
    private IRecruitInterviewInfoBiz interviewInfoBiz;

    @RequestMapping("/main")
    public String main(Model model){
        SysUser currentUser = GlobalContext.getCurrentUser();
        List<RecruitInfoExt> recruitInfoExts = recruitInfoBiz.selectRecruitByUserFlow(currentUser.getUserFlow(), currentUser.getOrgFlow());
        if (recruitInfoExts != null && recruitInfoExts.size() > 0){
            for (int i = recruitInfoExts.size() - 1 ; i >= 0 ; i--){
                if (!recruitInfoExts.get(i).getAuditStatusId().equals(RecruitStatusEnum.Passed.getId())){
                    recruitInfoExts.remove(i);
                }
            }
        }
        model.addAttribute("recruitInfoExts",recruitInfoExts);
        return "recruit/doctor/writeExamInfo";
    }


    /**
     * 准考证打印
     * @param model
     * @param recruitFlow
     * @return
     */
    @RequestMapping("/examineTicket")
    public String examineTicket(Model model,String recruitFlow){
        SysUser user = GlobalContext.getCurrentUser();
        String orgFlow=user.getOrgFlow();
        RecruitInfoExt recruitInfoExt = recruitInfoBiz.searchRecruitInfoByFlow(recruitFlow);
        model.addAttribute("recruitInfoExt",recruitInfoExt);
        RecruitInterviewInfo interviewInfo=interviewInfoBiz.readByFlow(recruitFlow);
        model.addAttribute("interviewInfo",interviewInfo);
        return "recruit/examCard/showExamCard";
    }


    /**
     * 考生须知
     * @param mainFlow
     * @param model
     * @return
     */
    @RequestMapping("/examineMainNote")
    public String examineMainNote(String mainFlow,Model model){
        RecruitExamMain recruitExamMain = recruitExamMainBiz.readByFlow(mainFlow);
        if (recruitExamMain != null){
            String mainNote = recruitExamMain.getMainNote();
        }
        model.addAttribute("recruitExamMain",recruitExamMain);
        return "recruit/doctor/examineMainNote";
    }


}
