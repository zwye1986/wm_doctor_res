package com.pinde.sci.ctrl.recruit;

import com.pinde.core.model.RecruitAdmitInfo;
import com.pinde.core.model.RecruitInfo;
import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysUser;
import com.pinde.sci.biz.recruit.IRecruitAdmitInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitInfoBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.core.model.RecruitInfoExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/recruit/admitInfo")
public class RecruitAdmitInfoController extends GeneralController {

    @Autowired
    private IRecruitInfoBiz recruitInfoBiz;

    @Autowired
    private IRecruitAdmitInfoBiz recruitAdmitInfoBiz;

    @Autowired
    private IUserBiz userBiz;

    @Autowired
    private IOrgBiz orgBiz;

    @RequestMapping("/main")
    public String main(Model model){
        SysUser currentUser = GlobalContext.getCurrentUser();
        List<RecruitInfoExt> recruitInfoExts = recruitInfoBiz.selectRecruitByUserFlow(currentUser.getUserFlow(), currentUser.getOrgFlow());
        if (recruitInfoExts != null && recruitInfoExts.size() > 0){
            for (int i = recruitInfoExts.size() - 1 ; i >= 0 ; i--){
                if (recruitInfoExts.get(i).getAdmitFlag().equals(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N)) {
                    recruitInfoExts.remove(i);
                }
            }
        }
        model.addAttribute("recruitInfoExts",recruitInfoExts);
        return "recruit/doctor/admitInfo";
    }


    @RequestMapping("/examineAdviceInfo")
    public String examineAdviceInfo(Model model,String recruitFlow){
        RecruitInfo recruitInfo = recruitInfoBiz.searchByRecruitFlow(recruitFlow);
        String doctorFlow = recruitInfo.getDoctorFlow();
        SysUser user = userBiz.findByFlow(doctorFlow);
        String orgFlow = user.getOrgFlow();
        RecruitAdmitInfo recruitAdmitInfo = recruitAdmitInfoBiz.searchByExample(recruitFlow, orgFlow);
        model.addAttribute("user",user);
        model.addAttribute("recruitInfo",recruitInfo);
        model.addAttribute("recruitAdmitInfo",recruitAdmitInfo);
        HashMap<String, String> admitDate = new HashMap<>();
        HashMap<String, String> createDate = new HashMap<>();
        if (recruitAdmitInfo != null){
            //yyyy-MM-dd HH:mm:ss
            String admitTime = recruitAdmitInfo.getAdmitTime();
            //yyyyMMddHHmmss
            String createTime = recruitAdmitInfo.getCreateTime();
            admitDate.put("year",admitTime.substring(0,4));
            admitDate.put("month",admitTime.substring(5,7));
            admitDate.put("day",admitTime.substring(8,10));
            admitDate.put("hour",admitTime.substring(11,13));
            admitDate.put("minute",admitTime.substring(14,16));
            createDate.put("year",createTime.substring(0,4));
            createDate.put("month",createTime.substring(4,6));
            createDate.put("day",createTime.substring(6,8));
        }
        SysOrg sysOrg = orgBiz.readSysOrg(recruitInfo.getOrgFlow());
        model.addAttribute("admitDate",admitDate);
        model.addAttribute("createDate",createDate);
        model.addAttribute("orgName",sysOrg.getOrgName());
        return "recruit/doctor/adviceAdvise";
    }
}
