package com.pinde.sci.ctrl.res;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResAttendanceBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sch.ISchDoctorAbsenceBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.model.mo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/teacherTwo")
public class ResTeacherTwoController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResTeacherTwoController.class);

	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private ISchDoctorAbsenceBiz schDoctorAbsenceBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IResAttendanceBiz resAttendanceBiz;

	@RequestMapping(value={"/absenceAudit"},method={RequestMethod.GET,RequestMethod.POST})
	public String absenceAudit(SchDoctorAbsence absence,Integer currentPage,HttpServletRequest request,Model model){
		if(currentPage==null){
			currentPage=1;
		}
		PageHelper.startPage(currentPage,getPageSize(request));

		List<SchDoctorAbsence> absenceList = schDoctorAbsenceBiz.searchSchDoctorAbsenceList(absence);
		model.addAttribute("absenceList",absenceList);
		return "/res/college2/absenceAudit";
	}
	/**
	 * 请假审批
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/absenceAudit/{resRoleScope}"},method={RequestMethod.GET,RequestMethod.POST})
	public String absenceAudit(@PathVariable String resRoleScope, SchDoctorAbsence doctorAbsence, Integer currentPage, HttpServletRequest request, Model model){
		SysUser currUser = GlobalContext.getCurrentUser();
		doctorAbsence.setOrgFlow(currUser.getOrgFlow());
        doctorAbsence.setIsRegister(com.pinde.core.common.GlobalConstant.FLAG_N);

        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(resRoleScope)) {//带教老师
			doctorAbsence.setTeacherFlow(currUser.getUserFlow());
		}
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(resRoleScope)) {//科主任
			doctorAbsence.setHeadFlow(currUser.getUserFlow());
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<SchDoctorAbsence> doctorAbsenceList = schDoctorAbsenceBiz.searchSchDoctorAbsenceList(doctorAbsence);
		if(doctorAbsenceList!=null && !doctorAbsenceList.isEmpty()){
			List<String> fileFlowList = new ArrayList<String>();
			for(SchDoctorAbsence da : doctorAbsenceList){
				if(StringUtil.isNotBlank(da.getMakingFile())){
					fileFlowList.add(da.getMakingFile());
				}
			}
			Map<String,PubFile> fileMap = new HashMap<String, PubFile>();
			if(fileFlowList.size() > 0){
				List<PubFile> pubFileList =	fileBiz.searchFile(fileFlowList);
				for(PubFile file :pubFileList){
					fileMap.put(file.getFileFlow(), file);
				}
				model.addAttribute("fileMap", fileMap);
			}
		}
		model.addAttribute("doctorAbsenceList", doctorAbsenceList);
		model.addAttribute("resRoleScope", resRoleScope);

		// 医院管理员
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(resRoleScope)) {
			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(currUser.getOrgFlow());
			model.addAttribute("schDeptList", schDeptList);
		}

		return "res/college2/absenceAuditList";
	}

	/**
	 * 保存请假审批
	 * @param doctorAbsence
	 * @return
	 */
	@RequestMapping(value="/saveAbsenceAudit")
	@ResponseBody
	public String saveAbsenceAudit(SchDoctorAbsence doctorAbsence){
		if(StringUtil.isNotBlank(doctorAbsence.getAbsenceFlow())){
			int result = schDoctorAbsenceBiz.saveSchDoctorAbsence(doctorAbsence);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 缺勤登记
	 */
	@RequestMapping(value={"/absentManage/{resRoleScope}"},method={RequestMethod.GET,RequestMethod.POST})
	public String absentRegist(@PathVariable String resRoleScope, String firstFlag, SchDoctorAbsence doctorAbsence, ResDoctor doctor, Integer currentPage, HttpServletRequest request, Model model){
		model.addAttribute("resRoleScope",resRoleScope);


		SysUser currUser = GlobalContext.getCurrentUser();
        if (!com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_GLOBAL.equals(resRoleScope)) {
			doctorAbsence.setOrgFlow(currUser.getOrgFlow());
			doctor.setOrgFlow(currUser.getOrgFlow());
		}

		List<ResDoctor> doctorList = null;
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(resRoleScope) || com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(resRoleScope)) {
			Map<String,Object> paramMap = new HashMap<String,Object>();
            if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(resRoleScope)) {//带教老师
				paramMap.put("teacherUserFlow", currUser.getUserFlow());
			}
            if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(resRoleScope)) {//科主任
				paramMap.put("headUserFlow", currUser.getUserFlow());
			}
			paramMap.put("doctor", doctor);
			doctorList = doctorBiz.searchDocByteacher2(paramMap);
		} else {
			//PageHelper.startPage(currentPage,getPageSize(request));
            doctor.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			doctorList = doctorBiz.searchByDoc(doctor);
		}
		model.addAttribute("doctorList",doctorList);

		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("doctorAbsence", doctorAbsence);
		paramMap.put("absenceTeacherDay", InitConfig.getSysCfg("res_absence_teacher_day"));
		paramMap.put("absenceHeadDay", InitConfig.getSysCfg("res_absence_head_day"));
		paramMap.put("absenceManageDay", InitConfig.getSysCfg("res_absence_manage_day"));
		List<SchDoctorAbsence> doctorAbsenceList = schDoctorAbsenceBiz.searchDoctorAbsence(paramMap);

		if(doctorAbsenceList != null && doctorAbsenceList.size()>0){
			Map<String,List<SchDoctorAbsence>> docAbsenceMap = new HashMap<String,List<SchDoctorAbsence>>();
			for(SchDoctorAbsence docAbsence : doctorAbsenceList){
				if(docAbsenceMap.get(docAbsence.getDoctorFlow()) == null){
					List<SchDoctorAbsence> docAbsenceTempList = new ArrayList<SchDoctorAbsence>();
					docAbsenceTempList.add(docAbsence);
					docAbsenceMap.put(docAbsence.getDoctorFlow(),docAbsenceTempList);
				}else{
					docAbsenceMap.get(docAbsence.getDoctorFlow()).add(docAbsence);
				}
			}
			model.addAttribute("docAbsenceMap",docAbsenceMap);
		}

		// 医院管理员、平台管理
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(resRoleScope) || com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_GLOBAL.equals(resRoleScope)) {
			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(currUser.getOrgFlow());
			model.addAttribute("schDeptList", schDeptList);
		}
		return "res/college2/absenceList";
	}

}
