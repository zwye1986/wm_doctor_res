package com.pinde.sci.ctrl.sch;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.res.RecDocCategoryEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sch/manager")
public class SchManagerController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(SchManagerController.class);
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private ISchRotationBiz schRotationtBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private ISchDoctorSelectDeptBiz doctorDeptBiz;
	@Autowired
	private IDictBiz dictBiz;
    private IDiscipleBiz iDiscipleBiz;

	/**
	 * 人员维护
	 */
	@RequestMapping(value = "/userList")
	public String userList(ResDoctorExt doctor, Integer currentPage, HttpServletRequest request, Model model) {
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
		if (currentPage == null) {
			currentPage = 1;
		}

		PageHelper.startPage(currentPage, getPageSize(request));
		doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<ResDoctorExt> doctorList = doctorBiz.searchDocUser(doctor, "");

		//复选框勾选标识
		Map<String,String> doctorTypeSelectMap = new HashMap<>();
		List<String> doctorTypeIdList = doctor.getDoctorTypeIdList();
		SysDict sysDict = new SysDict();
		sysDict.setDictTypeId(DictTypeEnum.DoctorType.getId());
		sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysDict> dictList = dictBiz.searchDictList(sysDict);
		if(dictList!=null&&dictList.size()>0&&doctorTypeIdList!=null&&doctorTypeIdList.size()>0){
			for (SysDict dict:dictList){
				if(doctorTypeIdList.contains(dict.getDictId())){
					doctorTypeSelectMap.put(dict.getDictId(),"checked");
				}
			}
		}
		//结束
		model.addAttribute("doctorTypeSelectMap",doctorTypeSelectMap);
		model.addAttribute("doctorList", doctorList);
		return "/sch/manager/studentlist";
	}
	@RequestMapping(value = "/exportDoc")
	public void exportDoc(ResDoctorExt doctor, HttpServletResponse response)throws Exception{
		doctor.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		doctor.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<ResDoctorExt> doctorList = doctorBiz.searchDocUser(doctor, "");
		doctorBiz.exportSchDocDetail(doctorList,response);
	}

	/**
	 * 用户编辑
	 */
	@RequestMapping(value = {"/editDocSimple"}, method = {RequestMethod.GET})
	public String editDocSimple(String roleFlag, String doctorFlow, Model model,String role) throws DocumentException {
		model.addAttribute("role",role);
		List<SysDept> depts=null;
		if (StringUtil.isNotBlank(doctorFlow)) {
			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
			model.addAttribute("doctor", doctor);
			SysUser user = userBiz.readSysUser(doctorFlow);
			model.addAttribute("user", user);
			model.addAttribute("rotationInUse", false);
			List<SchDoctorSelectDept> doctorDeptList = doctorDeptBiz.findSelectDepts(doctorFlow);
			if (doctorDeptList != null && doctorDeptList.size() > 0) {
				model.addAttribute("rotationInUse", true);
			} else {
				List<SchOrgArrangeResult> resultList = doctorDeptBiz.findSchResults(doctorFlow);
				if (resultList != null && resultList.size() > 0) {
					model.addAttribute("rotationInUse", true);
				}
			}
		}
		SysUser user = GlobalContext.getCurrentUser();
		List<SchRotation> rotationList = schRotationtBiz.searchSchRotationByIsPublish();
		if (!GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
			rotationList = schRotationtBiz.schRotations(rotationList, user.getOrgFlow());
		}
		model.addAttribute("rotationList", rotationList);
		return "sch/manager/editDocSimple";
	}

	@RequestMapping(value = {"/showDocSimple"}, method = {RequestMethod.GET})
	public String showDocSimple(String roleFlag, String doctorFlow, Model model,String role) throws DocumentException {
		model.addAttribute("role",role);
		if (StringUtil.isNotBlank(doctorFlow)) {
			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
			model.addAttribute("doctor", doctor);
			SysUser user = userBiz.readSysUser(doctorFlow);
			model.addAttribute("user", user);
		}
		if("Y".equals(InitConfig.getSysCfg("sch_inRes_flag")))
		{
			model.addAttribute("roleFlag", roleFlag);
			model.addAttribute("doctorFlow", doctorFlow);
			model.addAttribute("role", "hospital");
			model.addAttribute("isFormSch", true);
			return "redirect:/res/manager/editDocSimple";
		}
		return "sch/manager/showDocSimple";
	}
	@RequestMapping(value = {"/user/view"}, method = {RequestMethod.GET})
	public String userView(String roleFlag, String doctorFlow, Model model,String role) throws DocumentException {
		model.addAttribute("role",role);
		doctorFlow=GlobalContext.getCurrentUser().getUserFlow();
		if (StringUtil.isNotBlank(doctorFlow)) {
			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
			model.addAttribute("doctor", doctor);
			SysUser user = userBiz.readSysUser(doctorFlow);
			model.addAttribute("user", user);
		}
		if("Y".equals(InitConfig.getSysCfg("sch_inRes_flag")))
		{
			return "redirect:/sys/user/view?editFlag=Y";
		}
		return "sch/manager/view";
	}

	@RequestMapping(value={"/saveDocSimple"},method={RequestMethod.POST})
	@ResponseBody
	public String saveDocSimple(SysUser user, ResDoctor doctor){

		String orgFlow = "";
		String orgName = "";
		if(StringUtil.isNotBlank(doctor.getOrgFlow())){
			SysOrg org = orgBiz.readSysOrg(doctor.getOrgFlow());
			if(org!=null){
				orgFlow = org.getOrgFlow();
				orgName = org.getOrgName();
			}
		}else{
			orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
			orgName = GlobalContext.getCurrentUser().getOrgName();
		}

		String doctorName = "";
		if(user!=null){
			if(StringUtil.isBlank(user.getUserFlow())){
				SysUser old = userBiz.findByUserCode(user.getUserCode());
				if(old!=null){
					return GlobalConstant.USER_CODE_REPETE;
				}
				old = userBiz.findByIdNo(user.getIdNo());
				if(old!=null){
					return GlobalConstant.USER_ID_NO_REPETE;
				}
				old = userBiz.findByUserPhone(user.getUserPhone());
				if(old!=null){
					return GlobalConstant.USER_PHONE_REPETE;
				}
				old = userBiz.findByUserEmail(user.getUserEmail());
				if(old!=null){
					return GlobalConstant.USER_EMAIL_REPETE;
				}
			}else{
				String userFlow = user.getUserFlow();
				SysUser old = userBiz.findByUserCodeNotSelf(userFlow,user.getUserCode());
				if(old!=null){
					return GlobalConstant.USER_CODE_REPETE;
				}
				old = userBiz.findByIdNoNotSelf(userFlow,user.getIdNo());
				if(old!=null){
					return GlobalConstant.USER_ID_NO_REPETE;
				}
				old = userBiz.findByUserPhoneNotSelf(userFlow,user.getUserPhone());
				if(old!=null){
					return GlobalConstant.USER_PHONE_REPETE;
				}
				old = userBiz.findByUserEmailNotSelf(userFlow,user.getUserEmail());
				if(old!=null){
					return GlobalConstant.USER_EMAIL_REPETE;
				}
			}
			user.setSexName(UserSexEnum.getNameById(user.getSexId()));
			doctorName = user.getUserName();
		}
		if(doctor!=null){
			doctor.setDoctorName(doctorName);
			doctor.setOrgFlow(orgFlow);
			doctor.setOrgName(orgName);
			if(StringUtil.isNotBlank(doctor.getDoctorTypeId())){
				doctor.setDoctorTypeName(DictTypeEnum.DoctorType.getDictNameById(doctor.getDoctorTypeId()));
			}else{
				doctor.setDoctorTypeName("");
			}
			doctor.setTrainingSpeName(DictTypeEnum.DoctorTrainingSpe.getDictNameById(doctor.getTrainingSpeId()));
			if(StringUtil.isNotBlank(doctor.getTrainingTypeId())){
				doctor.setDoctorCategoryId(doctor.getTrainingTypeId());
				doctor.setDoctorCategoryName(RecDocCategoryEnum.getNameById(doctor.getTrainingTypeId()));
				doctor.setTrainingTypeName(RecDocCategoryEnum.getNameById(doctor.getTrainingTypeId()));
			}
			if(StringUtil.isNotBlank(doctor.getDoctorCategoryId())){
				doctor.setDoctorCategoryName(RecDocCategoryEnum.getNameById(doctor.getDoctorCategoryId()));
				doctor.setTrainingTypeId(doctor.getDoctorCategoryId());
				doctor.setTrainingTypeName(RecDocCategoryEnum.getNameById(doctor.getDoctorCategoryId()));
			}

			if(StringUtil.isNotBlank(doctor.getSecondSpeId())) {
				doctor.setSecondSpeName(DictTypeEnum.SecondTrainingSpe.getDictNameById(doctor.getSecondSpeId()));
			}
			SchRotation rotation = schRotationtBiz.readSchRotation(doctor.getRotationFlow());
			SchRotation rotation2 = schRotationtBiz.readSchRotation(doctor.getSecondRotationFlow());
			if(rotation!=null){
				user.setMedicineTypeId(rotation.getRotationTypeId());
				user.setMedicineTypeName(rotation.getRotationTypeName());
				doctor.setRotationName(rotation.getRotationName());
			}
			if(StringUtil.isNotBlank(user.getWorkOrgName()))
			{
				doctor.setWorkOrgName(user.getWorkOrgName());
			}
			if(rotation2!=null){
				doctor.setSecondRotationName(rotation2.getRotationName());
			}
			if(StringUtil.isNotBlank(doctor.getDoctorStatusId())){
				doctor.setDoctorStatusName(DictTypeEnum.DoctorStatus.getDictNameById(doctor.getDoctorStatusId()));
			}else{
				doctor.setDoctorStatusName("");
			}
			if(!StringUtil.isNotBlank(doctor.getRecordStatus())){
				doctor.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			}

		}
		if(GlobalConstant.ZERO_LINE!=doctorBiz.editSchDocUser(doctor, user)){

			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value={"/delete"})
	@ResponseBody
	public String delete(String userFlow){

		if(GlobalConstant.ZERO_LINE!=doctorBiz.deleteSchDocUser(userFlow)){

			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 学员维护导入
	 */
	@RequestMapping(value="/importStudentExcel")
	@ResponseBody
	public String importStudentExcel(MultipartFile file, String orgFlow){
		if(file.getSize() > 0){
			try{
				int result = doctorBiz.importStudentExcel(file,orgFlow);
				if(GlobalConstant.ZERO_LINE != result){
					return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
				}else{
					return GlobalConstant.UPLOAD_FAIL;
				}
			}catch(RuntimeException re){
				re.printStackTrace();
				return re.getMessage();
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}
}


