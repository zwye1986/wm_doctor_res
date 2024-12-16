package com.pinde.sci.ctrl.recruit;

import com.pinde.core.model.RecruitInfo;
import com.pinde.core.model.SysUser;
import com.pinde.core.util.DateUtil;
import com.pinde.sci.biz.recruit.IRecruitCfgInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitInfoLogBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.recruit.RecruitInfoExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.pinde.core.util.DateUtil.*;


@Controller
@RequestMapping("/recruit/infoLog")
public class RecruitInfoLogController extends GeneralController {

    @Autowired
    private IRecruitInfoLogBiz recruitInfoLogBiz;

    @Autowired
    private IRecruitInfoBiz recruitInfoBiz;

    @Autowired
    private IRecruitCfgInfoBiz recruitCfgInfoBiz;

    @RequestMapping("/main")
    public String main(Model model){
        SysUser currentUser = GlobalContext.getCurrentUser();
        List<RecruitInfoExt> recruitInfoExts = recruitInfoBiz.selectRecruitByUserFlow(currentUser.getUserFlow(), currentUser.getOrgFlow());
        if(recruitInfoExts != null && recruitInfoExts.size() > 0){
            for (RecruitInfoExt r:recruitInfoExts) {
                r.setAuditTime(DateUtil.transDateTime(r.getAuditTime(),defDtPtn01,defDtPtn02));
                r.setSaveTime(DateUtil.transDateTime(r.getSaveTime(),defDtPtn02,defDtPtn04));
                r.setSubmitTime(DateUtil.transDateTime(r.getSubmitTime(),defDtPtn02,defDtPtn04));
            }
        }
        model.addAttribute("recruitInfoExts",recruitInfoExts);
        return "recruit/doctor/recruitInfoLog";
    }


    @RequestMapping("/examineRecruitInfo")
    public String examineRecruitInfo(String recruitFlow,Model model){
        //获取当前登录用户信息
        SysUser currentUser = GlobalContext.getCurrentUser();
        RecruitInfo recruitInfo = recruitInfoBiz.searchByRecruitFlow(recruitFlow);
        model.addAttribute("currentUser",currentUser);
        model.addAttribute("recruitInfo",recruitInfo);
        return "recruit/doctor/examineRecruitInfoForm";
    }
}
