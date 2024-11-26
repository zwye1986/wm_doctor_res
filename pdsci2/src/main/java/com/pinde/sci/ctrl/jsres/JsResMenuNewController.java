package com.pinde.sci.ctrl.jsres;

import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.ResJointOrg;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/jsres/menuNew")
public class JsResMenuNewController extends GeneralController {

    @Autowired
    private IResJointOrgBiz jointOrgBiz;

    @RequestMapping(value = {"/schedulingMain"})
    public String schedulingMain() {
        return "jsres/hospital/schedulingMain";
    }

    @RequestMapping(value = {"/activityMain"})
    public String activityMain() {
        return "jsres/hospital/activityMain";
    }

    @RequestMapping(value = {"/activityQueryMain"})
    public String activityQueryMain() {
        return "jsres/hospital/activityQueryMain";
    }

    @RequestMapping(value = {"/hospitalSubject"})
    public String hospitalSubject() {
        return "jsres/hospital/hospitalSubject";
    }

    @RequestMapping(value = {"/cycleMain"})
    public String cycleMain() {
        return "jsres/hospital/cycleMain";
    }

    @RequestMapping(value = {"/rotationMainList"})
    public String rotationMainList() {
        return "jsres/hospital/rotationMainList";
    }

    @RequestMapping(value = {"/rotationMain"})
    public String rotationMain() {
        return "jsres/hospital/rotationMain";
    }

    @RequestMapping(value = {"/zlcxMain"})
    public String zlcxMain() {
        return "jsres/hospital/zlcxMain";
    }

    @RequestMapping(value = {"/szMain"})
    public String szMain() {
        return "jsres/hospital/szMain";
    }
    /**
     * 医院角色结业考核头部标签页(最新)
     * @return
     */
    @RequestMapping(value="/asseMain")
    public String asseMain(Model model, String roleFlag, String tabTag){
        SysUser currentUser = GlobalContext.getCurrentUser();
        //判断是否是协同基地
        String isJointOrg = GlobalConstant.FLAG_N;
        List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(currentUser.getOrgFlow());
        if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){
            isJointOrg = GlobalConstant.FLAG_Y;
            //   return "jsres/asse/hospital/auditMainForJoint";
        }
        model.addAttribute("isJointOrg", isJointOrg);
        model.addAttribute("tabTag",tabTag);
        model.addAttribute("roleFlag",roleFlag);
        return "jsres/asse/hospital/mainNew";
    }

    @RequestMapping(value="/asseAuditListMain")
    public String asseAuditListMain(Model model) {
        return "jsres/asse/hospital/asseAuditListMainNew";
    }
}
