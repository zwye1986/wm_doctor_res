package com.pinde.sci.ctrl.jszy;


import com.pinde.core.util.DateUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.jsres.ISchRotationDeptAfterBiz;
import com.pinde.sci.biz.jszy.IJszyGraduationApplyBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.jszy.JszyResAsseAuditListEnum;
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
@RequestMapping("/jszy/asse")
public class JszyDoctorAsseController extends GeneralController {
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

        //是否允许报名
        String ifCanRegistrate = GlobalConstant.FLAG_N;
        //是否在后台配置的时间区间内
        if (DateUtil.getCurrDateTime2().compareTo(InitConfig.getSysCfg("jszy_doctor_submit_start_time")) > 0
                && DateUtil.getCurrDateTime2().compareTo(InitConfig.getSysCfg("jszy_doctor_submit_end_time")) < 0) {
            ifCanRegistrate = GlobalConstant.FLAG_Y;
        }
        if (doctor != null) {
            List<ResDoctorRecruit> recruits = recruitBiz.searchRecruitByDoctor(doctor.getDoctorFlow());
            ResDoctorRecruit thisRecruit=null;
            if (recruits != null && recruits.size() > 0) {
                Map<String,ResDoctorRecruit> recruitMap=new HashMap<>();
                List<String> recruitFLows = new ArrayList<>();
                for (ResDoctorRecruit tempRecruit: recruits) {
                    recruitFLows.add(tempRecruit.getRecruitFlow());
                    recruitMap.put(tempRecruit.getRecruitFlow(),tempRecruit);
                    if(DateUtil.getYear().equals(tempRecruit.getGraduationYear()))
                    {
                        thisRecruit=tempRecruit;
                    }
                }
                model.addAttribute("recruitMap", recruitMap);
                model.addAttribute("thisRecruit", thisRecruit);
                if(thisRecruit!=null)
                {
                    JsresGraduationApply apply = jszyGraduationApplyBiz.searchByRecruitFlow(thisRecruit.getRecruitFlow(), DateUtil.getYear());

                    model.addAttribute("thisApply", apply);
                }
                List<JsresGraduationApply> jsresGraduationApplys = jszyGraduationApplyBiz.searchByRecruitFlows(recruitFLows, "");
                model.addAttribute("applys",jsresGraduationApplys);
            }
            model.addAttribute("recruits", recruits);
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("doctor", doctor);
        }
        model.addAttribute("ifCanRegistrate", ifCanRegistrate);
        return "jszy/asse/doctor/registration";
    }

    /**
     * 考核申请
     *
     * @param recruitFlow
     * @return
     */
    @RequestMapping(value = {"/addApply"})
    public String addApply(String recruitFlow, String applyYear,Model model ) throws DocumentException {

        ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
        model.addAttribute("recruit",recruit);
        if(recruit!=null) {
            SysUser currentUser = userBiz.readSysUser(recruit.getDoctorFlow());
            ResDoctor doctor = resDoctorBiz.readDoctor(recruit.getDoctorFlow());
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("doctor", doctor);
            JsresGraduationApply apply = jszyGraduationApplyBiz.searchByRecruitFlow(recruitFlow, applyYear);
            model.addAttribute("apply", apply);
        }

        //是否允许报名
        String ifCanRegistrate = GlobalConstant.FLAG_N;
        //是否在后台配置的时间区间内
        if (DateUtil.getCurrDateTime2().compareTo(InitConfig.getSysCfg("jszy_doctor_submit_start_time")) > 0
                && DateUtil.getCurrDateTime2().compareTo(InitConfig.getSysCfg("jszy_doctor_submit_end_time")) < 0) {
            ifCanRegistrate = GlobalConstant.FLAG_Y;
        }
        model.addAttribute("ifCanRegistrate", ifCanRegistrate);
        return "jszy/asse/doctor/addApply";
    }

    @RequestMapping(value = {"/reApply"})
    public String reApply(String recruitFlow, String applyFlow,Model model ) throws DocumentException {

        ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
        model.addAttribute("recruit",recruit);
        if(recruit!=null) {
            SysUser currentUser = userBiz.readSysUser(recruit.getDoctorFlow());
            ResDoctor doctor = resDoctorBiz.readDoctor(recruit.getDoctorFlow());
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("doctor", doctor);
        }
        JsresGraduationApply apply = jszyGraduationApplyBiz.readByFlow(applyFlow);
        model.addAttribute("apply", apply);
        //是否允许报名
        String ifCanRegistrate = GlobalConstant.FLAG_N;
        //是否在后台配置的时间区间内
        if (DateUtil.getCurrDateTime2().compareTo(InitConfig.getSysCfg("jszy_doctor_submit_start_time")) > 0
                && DateUtil.getCurrDateTime2().compareTo(InitConfig.getSysCfg("jszy_doctor_submit_end_time")) < 0) {
            ifCanRegistrate = GlobalConstant.FLAG_Y;
        }
        model.addAttribute("ifCanRegistrate", ifCanRegistrate);
        return "jszy/asse/doctor/reApply";
    }

    /**
     * 考核申请
     *
     * @param recruitFlow
     * @param doctorFlow
     * @return
     */
    @RequestMapping(value = {"/reAsseApply"})
    @ResponseBody
    public synchronized String reAsseApply(String recruitFlow, String applyFlow,
                                         String isTheroy,String isSkill,String isFive,String isTen,String makeUp) throws DocumentException {

        ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
        JsresGraduationApply jsresGraduationApply = jszyGraduationApplyBiz.readByFlow(applyFlow);
        if (jsresGraduationApply == null) {
            return "-1";
        } else {
            if(!JszyResAsseAuditListEnum.LocalNotPassed.getId().equals(jsresGraduationApply.getAuditStatusId())&&
                    !JszyResAsseAuditListEnum.ChargeNotPassed.getId().equals(jsresGraduationApply.getAuditStatusId())&&
                    !JszyResAsseAuditListEnum.GlobalNotPassed.getId().equals(jsresGraduationApply.getAuditStatusId())) {
             return "-2";
            }
            jsresGraduationApply.setIsTheroy(isTheroy);
            jsresGraduationApply.setIsSkill(isSkill);
            jsresGraduationApply.setIsFive(isFive);
            jsresGraduationApply.setIsTen(isTen);
            jsresGraduationApply.setMakeUp(makeUp);
            int i = jszyGraduationApplyBiz.editGraduationApply(jsresGraduationApply);
            return i + "";
        }
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
    public synchronized String asseApply(String recruitFlow, String doctorFlow, String applyYear, String changeSpeId,
                                         String isTheroy,String isSkill,String isFive,String isTen,String makeUp) throws DocumentException {

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
            jsresGraduationApply.setIsTheroy(isTheroy);
            jsresGraduationApply.setIsSkill(isSkill);
            jsresGraduationApply.setIsFive(isFive);
            jsresGraduationApply.setIsTen(isTen);
            jsresGraduationApply.setMakeUp(makeUp);
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
