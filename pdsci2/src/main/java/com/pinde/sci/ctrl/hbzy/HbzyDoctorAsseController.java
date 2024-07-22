package com.pinde.sci.ctrl.hbzy;


import com.pinde.core.util.DateUtil;
import com.pinde.sci.biz.hbzy.IJszyGraduationApplyBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.jsres.ISchRotationDeptAfterBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.hbzy.JszyResAsseAuditListEnum;
import com.pinde.sci.model.mo.*;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hbzy/asse")
public class HbzyDoctorAsseController extends GeneralController {
    @Autowired
    private IJsResDoctorBiz jsResDoctorBiz;
    @Autowired
    private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private IResDoctorRecruitBiz recruitBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;
    @Autowired
    private OrgBizImpl orgBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IResRecBiz resRecBiz;
    @Autowired
    private ISchRotationDeptAfterBiz afterBiz;
    @Autowired
    private IResScoreBiz resScoreBiz;
    @Autowired
    private ISchRotationBiz rotationBiz;
    @Autowired
    private ISchArrangeResultBiz resultBiz;
    @Autowired
    private ISchRotationGroupBiz groupBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private ISchDoctorDeptBiz doctorDeptBiz;
    @Autowired
    private ISchRotationDeptBiz rotationDeptBiz;
    @Autowired
    private IJszyGraduationApplyBiz jszyGraduationApplyBiz;
    @Autowired
    private IFileBiz fileBiz;

    /**
     * 学员角色：展示结业考核申请按钮
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/graduationRegistrate")
    public String graduationRegistrate(Model model) {
        SysUser currentUser = GlobalContext.getCurrentUser();
        ResDoctor doctor = resDoctorBiz.readDoctor(currentUser.getUserFlow());
        if (doctor != null) {
            List<ResDoctorRecruit> recruits = recruitBiz.searchRecruitByDoctor(doctor.getDoctorFlow());
            //是否允许报名
            String ifCanRegistrate = GlobalConstant.FLAG_N;
            //是否在后台配置的时间区间内
            if (DateUtil.getCurrDateTime2().compareTo(InitConfig.getSysCfg("jszy_doctor_submit_start_time")) > 0
                    && DateUtil.getCurrDateTime2().compareTo(InitConfig.getSysCfg("jszy_doctor_submit_end_time")) < 0) {
                ifCanRegistrate = GlobalConstant.FLAG_Y;
            }
            if (recruits != null && recruits.size() > 0) {
                List<String> recruitFLows = new ArrayList<>();
                for (ResDoctorRecruit tempRecruit: recruits) {
                    recruitFLows.add(tempRecruit.getRecruitFlow());
                }
                List<JsresGraduationApply> jsresGraduationApplys = jszyGraduationApplyBiz.searchByRecruitFlows(recruitFLows, DateUtil.getYear());
                if(jsresGraduationApplys != null && jsresGraduationApplys.size() > 0){
                    Map<String,JsresGraduationApply> recruit4ApplyMap = new HashMap<>();
                    for(JsresGraduationApply tempApply : jsresGraduationApplys){
                        recruit4ApplyMap.put(tempApply.getRecruitFlow(),tempApply);
                    }
                    model.addAttribute("recruit4ApplyMap", recruit4ApplyMap);
                }
            }
            model.addAttribute("ifCanRegistrate", ifCanRegistrate);
            model.addAttribute("recruits", recruits);
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("doctor", doctor);
        }
        return "hbzy/asse/doctor/registration";
    }

    /**
     * 考核申请
     *
     * @param recruitFlow
     * @param doctorFlow
     * @return
     */
    @RequestMapping(value = {"/asseApply"})
    @ResponseBody
    public synchronized String asseApply(String recruitFlow, String doctorFlow, String applyYear, String changeSpeId) throws DocumentException {

        ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
        if (!applyYear.equals(recruit.getGraduationYear())) {
            return "结业考核年份不是当前年，无法申请！";
        }
        JsresGraduationApply jsresGraduationApply = jszyGraduationApplyBiz.searchByRecruitFlow(recruitFlow, applyYear);
        if (jsresGraduationApply == null) {
            //培训记录
            //培训方案
            SchRotation rotation = rotationBiz.getRotationByRecruit(recruit);
            jsresGraduationApply = new JsresGraduationApply();
            jsresGraduationApply.setRecruitFlow(recruitFlow);
            if (rotation != null) {
                jsresGraduationApply.setRotationFlow(rotation.getRotationFlow());
                jsresGraduationApply.setRotationName(rotation.getRotationName());
            }
            jsresGraduationApply.setApplyYear(applyYear);
            jsresGraduationApply.setAuditStatusId(JszyResAsseAuditListEnum.Auditing.getId());
            jsresGraduationApply.setAuditStatusName(JszyResAsseAuditListEnum.Auditing.getName());

            int i = jszyGraduationApplyBiz.editGraduationApply(jsresGraduationApply);

            return i + "";
        } else {
            return "-1";
        }
    }

}
