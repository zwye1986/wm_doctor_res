package com.pinde.sci.ctrl.res;

import com.pinde.core.model.PubFile;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.sch.ISchDoctorAbsenceBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResDoctorSchProcess;
import com.pinde.core.model.SchDoctorAbsence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/docTwo")
public class ResDocTwoController extends GeneralController {
	public static final String SchRotation = "schRotation";
	public static final String SchRotationDept = "schRotationDept";
	public static final String SchRotationDeptReq = "schRotationDeptReq";
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IResDoctorProcessBiz resDoctorProcessBiz;
	@Autowired
	private ISchDoctorAbsenceBiz schDoctorAbsenceBiz;
	@Autowired
	private IFileBiz fileBiz;
	/**
	 * 请假记录
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/absenceList")
	public String absenceList(Integer currentPage, SchDoctorAbsence doctorAbsence, HttpServletRequest request, Model model){
//		SchDoctorAbsence doctorAbsence = new SchDoctorAbsence();
		doctorAbsence.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
        doctorAbsence.setIsRegister(com.pinde.core.common.GlobalConstant.FLAG_N);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<SchDoctorAbsence> doctorAbsenceList = schDoctorAbsenceBiz.searchSchDoctorAbsenceList(doctorAbsence);
		model.addAttribute("doctorAbsenceList", doctorAbsenceList);
		return "res/intern2/absenceList";
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

        if (StringUtil.isNotBlank(absenceFlow) || !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isRegister)) {
			doctorProcess.setUserFlow(StringUtil.isNotBlank(doctorFlow)?doctorFlow:user.getUserFlow());
			if(StringUtil.isNotBlank(absenceFlow)){
				SchDoctorAbsence doctorAbsence = schDoctorAbsenceBiz.readSchDoctorAbsence(absenceFlow);
				model.addAttribute("doctorAbsence", doctorAbsence);
				if(doctorAbsence !=null && StringUtil.isNotBlank(doctorAbsence.getMakingFile())){
					PubFile pubFile = fileBiz.readFile(doctorAbsence.getMakingFile());
					model.addAttribute("file", pubFile);
				}
			}
		}
		//缺勤登记
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isRegister)) {
			List<ResDoctor> doctorList = null;
			ResDoctor doctor = new ResDoctor();
            if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(resRoleScope) || com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(resRoleScope)) {
				Map<String,Object> paramMap = new HashMap<String,Object>();
                if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(resRoleScope)) {//带教老师
					paramMap.put("teacherUserFlow", user.getUserFlow());
				}
                if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(resRoleScope)) {//科主任
					paramMap.put("headUserFlow", user.getUserFlow());
				}
				paramMap.put("doctor", doctor);
				doctorList = resDoctorBiz.searchDocByteacher2(paramMap);
			} else {
				//PageHelper.startPage(currentPage,getPageSize(request));
                doctor.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				doctorList = resDoctorBiz.searchByDoc(doctor);
			}

//			if(!com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_CHARGE.equals(resRoleScope)){
//				doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
//			}
//			List<ResDoctor> doctorList = resDoctorBiz.searchByDoc(doctor);
			model.addAttribute("doctorList",doctorList);
		}
		model.addAttribute("isRegister",isRegister);

        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_DOCTOR.equals(resRoleScope)) {
			ResDoctor doctor = resDoctorBiz.readDoctor(user.getUserFlow());
			if(doctor!=null && StringUtil.isNotBlank(doctor.getOrgFlow())){
				doctorProcess.setOrgFlow(doctor.getOrgFlow());
			}
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(resRoleScope)) {//带教老师
			doctorProcess.setTeacherUserFlow(user.getUserFlow());
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(resRoleScope)) {//科主任
			doctorProcess.setHeadUserFlow(user.getUserFlow());
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(resRoleScope)) {
			doctorProcess.setOrgFlow(user.getOrgFlow());
		}
		List<ResDoctorSchProcess> processList = resDoctorProcessBiz.searchDoctorProcess(null,null,doctorProcess);
		model.addAttribute("processList", processList);
		return "res/intern2/editAbsence";
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
		int result = schDoctorAbsenceBiz.editSchDoctorAbsence2(doctorAbsence,file);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}
	
	/**
	 * 计算请假天数
	 * @param doctorAbsence
	 * @return
	 */
	@RequestMapping(value="/calculateAbsenceDay")
	@ResponseBody
	public Long calculateAbsenceDay(SchDoctorAbsence doctorAbsence){
		Long intervalDay = DateUtil.signDaysBetweenTowDate(doctorAbsence.getEndDate(),  doctorAbsence.getStartDate());
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
            doctorAbsence.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
			int result = schDoctorAbsenceBiz.saveSchDoctorAbsence(doctorAbsence);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
	}
	
	@RequestMapping(value="/repealAbsence")
	@ResponseBody
	public String repealAbsence(String absenceFlow){
		if(StringUtil.isNotBlank(absenceFlow)){
			SchDoctorAbsence doctorAbsence = new SchDoctorAbsence();
			doctorAbsence.setAbsenceFlow(absenceFlow);
            doctorAbsence.setRepealAbsence(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			doctorAbsence.setRepealAbsenceDate(DateUtil.getCurrDate());
			int result = schDoctorAbsenceBiz.saveSchDoctorAbsence(doctorAbsence);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}

}
