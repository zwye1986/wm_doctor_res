package com.pinde.sci.ctrl.gzzyjxres;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gzzyjxres.IGzjxDocSingupBiz;
import com.pinde.sci.biz.gzzyjxres.IGzjxDoctorAbsenceBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IStuBatchBiz;
import com.pinde.sci.biz.res.IStuUserResumeBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.form.gzzyjxres.ExtInfoForm;
import com.pinde.sci.form.gzzyjxres.SpeForm;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/res/absence")
public class ResAbsenceController extends GeneralController {
    @Autowired
    private IGzjxDoctorAbsenceBiz doctorAbsenceBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IResDoctorProcessBiz resDoctorProcessBiz;
    @Autowired
    private IStuBatchBiz stuBatchBiz;
    @Autowired
    private IStuUserResumeBiz stuUserResumeBiz;
    @Autowired
    private IGzjxDocSingupBiz docSingupBiz;


    /**
     * 请假记录
     * @param model
     * @return
     */
    @RequestMapping(value="/absenceList/{roleFlag}")
    public String absenceList(@PathVariable String roleFlag,Integer currentPage, SchDoctorAbsence doctorAbsence, HttpServletRequest request, Model model){
        model.addAttribute("roleFlag", roleFlag);
        SysUser currentUser = GlobalContext.getCurrentUser();
        String batchFlow = "";//当前系统默认 进修批次流水号
        List<StuBatch> batchLst = stuBatchBiz.getStuBatchLst();
        if (null != batchLst && batchLst.size() > 0) {
            for (StuBatch obj : batchLst) {
                if ("Y".equals(obj.getIsDefault())) {//系统默认批次设置为"Y"，反之为"N"，不存在null
                    batchFlow = obj.getBatchFlow();
                    break;
                }
            }
        }
        List<StuUserResume> doctorLst = stuUserResumeBiz.getStuUserLst(currentUser.getUserFlow(), batchFlow);
        ExtInfoForm extInfo = null;
        if (doctorLst != null && doctorLst.size() > 0) {
            StuUserResume resume = doctorLst.get(0);
             extInfo = docSingupBiz.parseExtInfoXml(resume.getResumeInfo());
        }
        if(GlobalConstant.RES_ROLE_SCOPE_SECRETARY.equals(roleFlag)){
            doctorAbsence.setDeptFlow(currentUser.getDeptFlow());
        }else{
            doctorAbsence.setDoctorFlow(currentUser.getUserFlow());
        }
        doctorAbsence.setIsRegister(GlobalConstant.FLAG_N);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<SchDoctorAbsence> doctorAbsenceList = doctorAbsenceBiz.searchSchDoctorAbsenceList(doctorAbsence);
        model.addAttribute("doctorAbsenceList", doctorAbsenceList);

        //计算已经请假的天数
        int haveLeaveDays=0;
        if(doctorAbsenceList!=null && doctorAbsenceList.size()>0){
            for(SchDoctorAbsence absence:doctorAbsenceList){
                haveLeaveDays+=Integer.parseInt(absence.getIntervalDay());
            }
        }
        String showFlag = GlobalConstant.FLAG_Y;//是否有假期flag,默认有假期
        //总共拥有的假期天数
        int holidays=0;
        if(extInfo!=null){
            if(extInfo.getSpeFormList()!=null) {
                int totalMonth = 0;
                if (extInfo.getSpeFormList().size() > 0) {
                    for (SpeForm speForm : extInfo.getSpeFormList()) {
                        totalMonth += Integer.parseInt(speForm.getStuTimeId());
                    }
                }
            //增加假期提醒功能，
            // 3个月以内的没有假期
            // 3个月至6个月有5天的假期，
            // 6个月以上有10天的假期，系统自动计算提醒当前剩余请假天数。
                if(totalMonth<3){
                    showFlag = GlobalConstant.FLAG_N;
                }else if(totalMonth>=3 && totalMonth<=6){
                    holidays = 5;
                }else if(totalMonth>6){
                    holidays= 10;
                }
            }
        }
        model.addAttribute("showFlag",showFlag);
        //剩余假期天数
        int leave = holidays - haveLeaveDays;
        model.addAttribute("leave",leave);
        return "gzzyjxres/doctor/intern/absenceList";
    }

    /**
     * 新增请假记录
     * @param model
     * @return
     */
    @RequestMapping(value="/editAbsence")
    public String editAbsence(String absenceFlow,String isRegister,String resRoleScope,String doctorFlow, Model model){
        SysUser user = GlobalContext.getCurrentUser();
        ResDoctorSchProcess doctorProcess = new ResDoctorSchProcess();

        if(StringUtil.isNotBlank(absenceFlow) || !GlobalConstant.FLAG_Y.equals(isRegister)){
            doctorProcess.setUserFlow(StringUtil.isNotBlank(doctorFlow)?doctorFlow:user.getUserFlow());
            if(StringUtil.isNotBlank(absenceFlow)){
                SchDoctorAbsence doctorAbsence = doctorAbsenceBiz.readSchDoctorAbsence(absenceFlow);
                model.addAttribute("doctorAbsence", doctorAbsence);
                if(doctorAbsence !=null && StringUtil.isNotBlank(doctorAbsence.getMakingFile())){
                    PubFile pubFile = fileBiz.readFile(doctorAbsence.getMakingFile());
                    model.addAttribute("file", pubFile);
                }
            }
        }
        //缺勤登记
        if (GlobalConstant.FLAG_Y.equals(isRegister)) {
            List<ResDoctor> doctorList = null;
            ResDoctor doctor = new ResDoctor();
            if(GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(resRoleScope) || GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(resRoleScope)){
                Map<String,Object> paramMap = new HashMap<String,Object>();
                if(GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(resRoleScope)){//带教老师
                    paramMap.put("teacherUserFlow", user.getUserFlow());
                }
                if(GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(resRoleScope)){//科主任
                    paramMap.put("headUserFlow", user.getUserFlow());
                }
                paramMap.put("doctor", doctor);
                doctorList = resDoctorBiz.searchDocByteacherJx(paramMap);
            } else {
                //PageHelper.startPage(currentPage,getPageSize(request));
                doctor.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                doctorList = resDoctorBiz.searchByDoc(doctor);
            }

//			if(!GlobalConstant.RES_ROLE_SCOPE_CHARGE.equals(resRoleScope)){
//				doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
//			}
//			List<ResDoctor> doctorList = resDoctorBiz.searchByDoc(doctor);
            model.addAttribute("doctorList",doctorList);
        }
        model.addAttribute("isRegister",isRegister);

        if(GlobalConstant.RES_ROLE_SCOPE_DOCTOR.equals(resRoleScope)){
            ResDoctor doctor = resDoctorBiz.readDoctor(user.getUserFlow());
            if(doctor!=null && StringUtil.isNotBlank(doctor.getOrgFlow())){
                doctorProcess.setOrgFlow(doctor.getOrgFlow());
            }
        }else if(GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(resRoleScope)){//带教老师
            doctorProcess.setTeacherUserFlow(user.getUserFlow());
        }else if(GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(resRoleScope)){//科主任
            doctorProcess.setHeadUserFlow(user.getUserFlow());
        }else if(GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(resRoleScope)){
            doctorProcess.setOrgFlow(user.getOrgFlow());
        }
        List<ResDoctorSchProcess> processList = resDoctorProcessBiz.searchDoctorProcess(null,null,doctorProcess);
        model.addAttribute("processList", processList);
        return "gzzyjxres/doctor/intern/editAbsence";
    }

    /**
     * 保存请假记录
     * @param doctorAbsence
     * @param file
     * @return
     */
    @RequestMapping(value="/saveDoctorAbsence")
    @ResponseBody
    public String saveDoctorAbsence(SchDoctorAbsence doctorAbsence, MultipartFile file){
        int result = doctorAbsenceBiz.editSchDoctorAbsence(doctorAbsence,file);
        if(GlobalConstant.ZERO_LINE != result){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 计算请假天数
     * @param doctorAbsence
     * @return
     */
    @RequestMapping(value="/calculateAbsenceDay")
    @ResponseBody
    public Long calculateAbsenceDay(SchDoctorAbsence doctorAbsence){
        Long intervalDay = DateUtil.signDaysBetweenTowDate(doctorAbsence.getEndDate(),doctorAbsence.getStartDate());
        return intervalDay;
    }

    /**
     * 删除请假
     * @param absenceFlow
     * @return
     */
    @RequestMapping(value="/delAbsence")
    @ResponseBody
    public String delAbsence(String absenceFlow){
        if(StringUtil.isNotBlank(absenceFlow)){
            SchDoctorAbsence doctorAbsence = new SchDoctorAbsence();
            doctorAbsence.setAbsenceFlow(absenceFlow);
            doctorAbsence.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            int result = doctorAbsenceBiz.saveSchDoctorAbsence(doctorAbsence);
            if(GlobalConstant.ZERO_LINE != result){
                return GlobalConstant.DELETE_SUCCESSED;
            }
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 销假
     * @param absenceFlow
     * @return
     */
    @RequestMapping(value="/repealAbsence")
    @ResponseBody
    public String repealAbsence(String absenceFlow){
        if(StringUtil.isNotBlank(absenceFlow)){
            SchDoctorAbsence doctorAbsence = new SchDoctorAbsence();
            doctorAbsence.setAbsenceFlow(absenceFlow);
            doctorAbsence.setRepealAbsence(GlobalConstant.RECORD_STATUS_Y);
            doctorAbsence.setRepealAbsenceDate(DateUtil.getCurrDate());
            int result = doctorAbsenceBiz.saveSchDoctorAbsence(doctorAbsence);
            if(GlobalConstant.ZERO_LINE != result){
                return GlobalConstant.OPRE_SUCCESSED;
            }
        }
        return GlobalConstant.OPRE_FAIL;
    }


}
