package com.pinde.sci.ctrl.res;

import com.alibaba.fastjson.JSON;
import com.pinde.core.jspform.ItemGroupData;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.jsres.IJsResUserBlackBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.biz.test.ITestResultBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.dao.sys.SysOrgExtMapper;
import com.pinde.sci.enums.jszy.JszyResTrainYearEnum;
import com.pinde.sci.enums.jszy.JszyTrainCategoryEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.res.*;
import com.pinde.sci.enums.sch.SchUnitEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.form.res.ResRecForm;
import com.pinde.sci.form.sch.SchGradeFrom;
import com.pinde.sci.form.sch.SelectDept;
import com.pinde.sci.form.sch.SelectDeptForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

@Controller
@RequestMapping("/res/doc")
public class ResDocController extends GeneralController{
	public static final String RotationCfg = "rotationCfg";
	public static final String SchRotation = "schRotation";
	public static final String SchRotationDept = "schRotationDept";
	public static final String SchRotationDeptReq = "schRotationDeptReq";
	public static final String SchRotationDeptGroup = "schRotationDeptGroup";
	public static final String Dict = "dict";
	private static Logger logger = LoggerFactory.getLogger(ResDocController.class);

	@Autowired
	private IResGradeBiz resGradeBiz;
	@Autowired
	private ISchRotationBiz schRotationtBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResEnterOpenCfgBiz enterOpenCfgBiz;
	@Autowired
	private IResPowerCfgBiz resPowerCfgBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private IResDoctorProcessBiz resDoctorProcessBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private ISchRotationGroupBiz schRotationtGroupBiz;
	@Autowired
	private ISchDoctorDeptBiz schDoctorDeptBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private ISchDoctorAbsenceBiz schDoctorAbsenceBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private ISchDeptExternalRelBiz deptExtRelBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private ITestResultBiz resultBiz;
	@Autowired
	private ISchArrangeBiz arrangeBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IResLectureEvaDetailBiz resLectureEvaDetailBiz;
    @Autowired
    private IDiscipleBiz discipleBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private IResDoctorDelayTeturnBiz resDoctorDelayTeturnBiz;
	@Autowired
	private IResDoctorRecruitBiz doctorRecruitBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IZseyHrKqMonthBiz zseyHrKqMonthBiz;
	@Autowired
	private IJsResUserBlackBiz blackBiz;
	@Autowired
	private ISchDeptRelBiz deptRelBiz;
	@Autowired
	private SysOrgExtMapper orgExtMapper;
    @Autowired
    private IResDiscipleInfoBiz discipleInfoBiz;

	/**
	 * @param d
	 * @return
	 */
	public static String _doubleTrans(double d) {
		if ((double) Math.round(d) - d == 0.0D)
			return String.valueOf((long) d);
		else
            return String.valueOf(d);
    }

	@RequestMapping(value={"/checkFile"})
	@ResponseBody
	public Object checkFile(MultipartFile file) {
		if(file.getSize()> 0){
			try{
				Map<String,Object> map = schRotationtBiz.importRoationFromExcel(file);
					return map.get("rotationInsertNumber");
			}catch(RuntimeException re){
				re.printStackTrace();
				return re.getMessage();
			}

		}
		return GlobalConstant.UPLOAD_FAIL;
	}
	
	/**
	 * 根据字典id获取字典层级数
	 * @param dictId
	 * @return
	 */
	private int getLevelById(String dictId){
		int level = 1;
		if(StringUtil.isNotBlank(dictId)){
			//通过id获取字典对象
			DictTypeEnum dictEnum = EnumUtil.getById(dictId,DictTypeEnum.class);
			if(dictEnum!=null){
				level = dictEnum.getLevel();
			}
		}
		return level;
	}
	
	/**
	 * 根据字典id获取该字典和所有子字典
	 * @param dictId
	 * @return
	 */
	private List<SysDict> searchDictById(String dictId){
		List<SysDict> dictList = null;
		if(StringUtil.isNotBlank(dictId)){
			int level = getLevelById(dictId);
			dictList = new ArrayList<SysDict>();
			makeAllDict(dictId,1,level,dictList);
		}
		return dictList;
	}
	
	/**
	 * 递归子字典
	 * @param dictId
	 * @param currLv
	 * @param lv
	 * @param dictList
	 */
	private void makeAllDict(String dictId,int currLv,int lv,List<SysDict> dictList){
		if(StringUtil.isNotBlank(dictId)){
			List<SysDict> currDictList = dictBiz.searchDictListByDictTypeId(dictId);

			if(dictList!=null && currDictList!=null && currDictList.size()>0){
				dictList.addAll(currDictList);

				if(currLv!=lv){
					for(SysDict dict : currDictList){
						String subId = dict.getDictTypeId()+"."+dict.getDictId();
						makeAllDict(subId,currLv+1,lv,dictList);
					}
				}
			}
		}
	}
	
//	public void a(String [] standardDoc){
//		for(String dictId : standardDoc){
//			System.err.println(searchDictById(dictId).size());
//		}
//	}
//
//
//导出
	@RequestMapping(value={"/schRotationExport"})
	public void schRotationExport(Model model, HttpServletResponse response, String[] standardDoc) throws Exception {
		//查询各表数据
		List<SchRotation> rotationList=schRotationtBiz.searchSchRotation();
		List<SchRotationDept> rotationDeptList=rotationDeptBiz.searchRotationDeptByFlows();
		List<SchRotationDeptReq> rotationDeptReqList=rotationDeptBiz.searchSchRotationDeptReqOrgNull();
		List<SchRotationGroup> rotationGroupList=schRotationtGroupBiz.searchSchRotationGroupOrgNull();
		//解析成xml
		String rotationXml=XmlUtil.marshaller(rotationList);
		String  rotationDeptXml=XmlUtil.marshaller(rotationDeptList);
		String  rotationDeptReqXml=XmlUtil.marshaller(rotationDeptReqList);
		String  rotationGroupXml=XmlUtil.marshaller(rotationGroupList);
		//新增节点
		Document dom = DocumentHelper.createDocument();
		Element root= dom.addElement(RotationCfg);
		Element schRotation = root.addElement(SchRotation);
		Element schRotationDept = root.addElement(SchRotationDept);
		Element schRotationDeptReq = root.addElement(SchRotationDeptReq);
		Element schRotationDeptGroup = root.addElement(SchRotationDeptGroup);
		Element sysDict = root.addElement(Dict);

		Document schRotationList= DocumentHelper.parseText(rotationXml);
		Document schRotationDeptList= DocumentHelper.parseText(rotationDeptXml);
		Document schRotationDeptReqList= DocumentHelper.parseText(rotationDeptReqXml);
		Document schRotationDeptGroupList= DocumentHelper.parseText(rotationGroupXml);

		schRotation.add(schRotationList.getRootElement());
		schRotationDept.add(schRotationDeptList.getRootElement());
		schRotationDeptReq.add(schRotationDeptReqList.getRootElement());
		schRotationDeptGroup.add(schRotationDeptGroupList.getRootElement());
		if (standardDoc!=null&&standardDoc.length>0) {
			for (String dictId : standardDoc) {
				List<SysDict> dictList=searchDictById(dictId);
				String  dictXml=XmlUtil.marshaller(dictList);
				Element dict = sysDict.addElement(dictId);
				Document dictLists= DocumentHelper.parseText(dictXml);
				dict.add(dictLists.getRootElement());
			}
		}
		String fileName = "培训方案.xml";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		XMLWriter writer=new XMLWriter(response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		writer.write(dom);
	}

	@RequestMapping(value={"/user/docList"},method={RequestMethod.GET,RequestMethod.POST})
	public String docList(ResDoctorExt resDoctorExt,SysUser user,Model model){
		resDoctorExt.setSysUser(user);
		List<ResDoctorExt> doctorExtList = resDoctorBiz.searchDocUser(resDoctorExt, "");
		model.addAttribute("doctorExtList",doctorExtList);
		return "res/doctor/user/docList";
	}

	@RequestMapping(value = {"/user/docEditDoc"}, method = {RequestMethod.GET})
	public String docEditDoc(Model model){
		String flow = GlobalContext.getCurrentUser().getUserFlow();
		model.addAttribute("doctorFlow",flow);
		model.addAttribute("userFlow",flow);
		model.addAttribute("isMenu",true);
		return "redirect:/res/doc/user/editDoc";
	}
	
	@RequestMapping(value={"/user/editDoc"},method={RequestMethod.GET})
	public String editDoc(String doctorFlow,String userFlow,Model model){
		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
			model.addAttribute("doctor",doctor);

			SysDept dept = new SysDept();
			dept.setOrgFlow(doctor.getOrgFlow());
			List<SysDept> deptList = deptBiz.searchDept(dept);
			model.addAttribute("deptList",deptList);
		}
		if(StringUtil.isNotBlank(userFlow)){
			SysUser user = userBiz.readSysUser(userFlow);
			model.addAttribute("user",user);
		}

		List<SchRotation> rotationList = schRotationtBiz.searchSchRotationByIsPublish();
		model.addAttribute("rotationList",rotationList);


//		if(StringUtil.isNotBlank(doctorFlow)){
//			ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
//			model.addAttribute("doctor",doctor);
//			SysUser user = userBiz.readSysUser(doctorFlow);
//			model.addAttribute("user",user);
//
//			SchRotation rotation = new SchRotation();
//			rotation.setDoctorCategoryId(doctor.getDoctorCategoryId());
//			rotation.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
//			List<SchRotation> rotationList = schRotationtBiz.searchRotationByRole(null,rotation);
//			model.addAttribute("rotationList",rotationList);
//		}
		return "res/doctor/user/editDoc";
	}

	/**
	 * 保存医师信息
	 * @param user
	 * @param doctor
	 * @param roleFlag
	 * @param resumeExtInfoForm
     * @return
     */
	@RequestMapping(value={"/user/saveDocSimple"},method={RequestMethod.POST})
	@ResponseBody
	public String saveDocSimple(SysUser user, ResDoctor doctor, String roleFlag, BaseUserResumeExtInfoForm resumeExtInfoForm,ResDiscipleInfo bean){
//		if(doctor!=null){
//			if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
//				String orgFlow = doctor.getOrgFlow();
//				String rotationFlow = doctor.getrotationFlow();
//				if(StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(orgFlow)){
//					SchRotation rotation = schRotationtBiz.readRotationByStandardAndOrg(orgFlow,rotationFlow);
//					if(rotation==null){
//						rotation = schRotationtBiz.readSchRotation(rotationFlow);
//						rotation.setRotationFlow(rotation.getRotationFlow());
//						rotation.setOrgFlow(orgFlow);
//						rotation.setRotationTypeId(SchRotationTypeEnum.Local.getId());
//						rotation.setRotationTypeName(SchRotationTypeEnum.Local.getName());
//						rotation.setRotationFlow(null);
//						if(GlobalConstant.ZERO_LINE==schRotationtBiz.saveSchRotation(rotation)){
//							return GlobalConstant.SAVE_FAIL;
//						}
//					}
//					doctor.setRotationFlow(rotation.getRotationFlow());
//				}
//			}
//		}
		return saveDoc(user,doctor,roleFlag,resumeExtInfoForm,bean);
	}

	@RequestMapping(value={"/user/saveDoc"},method={RequestMethod.POST})
	@ResponseBody
	public String saveDoc(SysUser user,ResDoctor doctor,String roleFlag,BaseUserResumeExtInfoForm resumeExtInfoForm,ResDiscipleInfo bean){
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
			if(StringUtil.isNotBlank(user.getSexId())){
				user.setSexName(UserSexEnum.getNameById(user.getSexId()));
			}
			SysDept dept = deptBiz.readSysDept(user.getDeptFlow());
			if(dept!=null){
				user.setDeptName(dept.getDeptName());
			}
//			user.setOrgFlow(orgFlow);
//			user.setOrgName(orgName);
			if (StringUtil.isBlank(user.getOrgFlow())) {
				if (GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
					user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow()); //人员记录所属机构与部门
					user.setOrgName(GlobalContext.getCurrentUser().getOrgName());
				}
			}
			user.setTitleName(DictTypeEnum.UserTitle.getDictNameById(user.getTitleId()));
			user.setPostName(DictTypeEnum.UserPost.getDictNameById(user.getPostId()));
//			user.setDegreeName(DictTypeEnum.UserDegree.getDictNameById(user.getDegreeId()));
			user.setEducationName(DictTypeEnum.UserEducation.getDictNameById(user.getEducationId()));
			doctorName = user.getUserName();
		}
		if(doctor!=null){
			doctor.setDoctorName(doctorName);
			doctor.setOrgFlow(orgFlow);
			doctor.setOrgName(orgName);
			//doctor.setDeptFlow(user.getDeptFlow()); //???
			//doctor.setDeptName(user.getDegreeName());//????
			if(StringUtil.isNotBlank(doctor.getDoctorTypeId())){
				doctor.setDoctorTypeName(DictTypeEnum.DoctorType.getDictNameById(doctor.getDoctorTypeId()));
				if(ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId()))
				{
					if(StringUtil.isNotBlank(doctor.getDepartMentFlow()))
					{
						SysDept dept=deptBiz.readSysDept(doctor.getDepartMentFlow());
						if(dept!=null)
							doctor.setDepartMentName(dept.getDeptName());
						else{
							doctor.setDepartMentFlow("");
							doctor.setDepartMentName("");
						}
					}else {
						doctor.setDepartMentFlow("");
						doctor.setDepartMentName("");
					}
				}else{
					doctor.setDepartMentFlow("");
					doctor.setDepartMentName("");
				}
			}else{
				doctor.setDepartMentFlow("");
				doctor.setDepartMentName("");
				doctor.setDoctorTypeName("");
			}
//			doctor.setGraduatedName(DictTypeEnum.GraduateSchool.getDictNameById(doctor.getGraduatedId()));
			doctor.setTrainingSpeName(DictTypeEnum.DoctorTrainingSpe.getDictNameById(doctor.getTrainingSpeId()));
			SchRotation rotation = schRotationtBiz.readSchRotation(doctor.getRotationFlow());
			if(rotation!=null){
				user.setMedicineTypeId(rotation.getRotationTypeId());
				user.setMedicineTypeName(rotation.getRotationTypeName());
				doctor.setRotationName(rotation.getRotationName());
			}
			if(StringUtil.isNotBlank(doctor.getDoctorStatusId())){
				doctor.setDoctorStatusName(DictTypeEnum.DoctorStatus.getDictNameById(doctor.getDoctorStatusId()));
			}else{
				doctor.setDoctorStatusName("");
			}
			if(!StringUtil.isNotBlank(doctor.getRecordStatus())){
				doctor.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			}
			if(StringUtil.isNotBlank(doctor.getDoctorCategoryId())){
				doctor.setDoctorCategoryName(RecDocCategoryEnum.getNameById(doctor.getDoctorCategoryId()));
				doctor.setTrainingTypeId(doctor.getDoctorCategoryId());
				doctor.setTrainingTypeName(doctor.getDoctorCategoryName());
			}
			//根据医师状态，清空相应字段
//			if (ResDoctorStatusEnum.Terminat.getId().equals(doctor.getDoctorStatusId())) {
//				doctor.setCompleteNo("");
//				doctor.setCompleteDate("");
//			} else if (ResDoctorStatusEnum.Graduation.getId().equals(doctor.getDoctorStatusId())) {
//				doctor.setTerminatReason("");
//				doctor.setTerminatDate("");
//			} else {
//				doctor.setCompleteNo("");
//				doctor.setCompleteDate("");
//				doctor.setTerminatReason("");
//				doctor.setTerminatDate("");
//			}
		}
		if(GlobalConstant.ZERO_LINE!=resDoctorBiz.editDocUser(doctor, user)){
            if(StringUtil.isNotBlank(doctor.getDiscipleTeacherFlow()) && StringUtil.isNotBlank(doctor.getDoctorFlow())){
                boolean discipleTeacherExist = discipleBiz.searchStudentDiscipleTeacher(doctor.getDiscipleTeacherFlow(),doctor.getDoctorFlow());
                if(!discipleTeacherExist){
                    ResStudentDiscipleTeacher studentDiscipleTeacher = new ResStudentDiscipleTeacher();
                    studentDiscipleTeacher.setDoctorFlow(doctor.getDoctorFlow());
                    studentDiscipleTeacher.setDoctorName(doctor.getDoctorName());
                    studentDiscipleTeacher.setTeacherFlow(doctor.getDiscipleTeacherFlow());
                    studentDiscipleTeacher.setTeacherName(doctor.getDiscipleTeacherName());
                    discipleBiz.saveStudentDiscipleTeacher(studentDiscipleTeacher);
                }
            }
			if(StringUtil.isNotBlank(resumeExtInfoForm.getGraduatedName())){//本科毕业院校
				List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("GraduateSchool");
				for(SysDict s:dictList){
					if (s.getDictName().equals(resumeExtInfoForm.getGraduatedName())) {
						resumeExtInfoForm.setGraduatedId(s.getDictId());
					}
				}
			}
			String xmlContent = JaxbUtil.convertToXml(resumeExtInfoForm);
			PubUserResume pubUserResume = userResumeBiz.readPubUserResume(user.getUserFlow());
			if(pubUserResume == null){
				pubUserResume = new PubUserResume();
			}
			pubUserResume.setUserResume(xmlContent);
			userResumeBiz.savePubUserResume(user, pubUserResume);
			//修改res_doctor_recruit的graduation_year
			ResDoctorRecruit doctorRecruit = new ResDoctorRecruit();
			doctorRecruit.setDoctorFlow(doctor.getDoctorFlow());
			doctorRecruit.setOrgFlow(doctor.getOrgFlow());
			doctorRecruit.setCatSpeId(doctor.getDoctorCategoryId());
			doctorRecruit.setSpeId(doctor.getTrainingSpeId());
			doctorRecruit.setAuditStatusId("Passed");
			ResDoctorRecruit recruit = doctorRecruitBiz.searchRecruitByResDoctor(doctorRecruit);
			if(null!=recruit&&StringUtil.isNotBlank(doctor.getGraduationYear())) {
				recruit.setGraduationYear(doctor.getGraduationYear());
				doctorRecruitBiz.updateDocrecruit(recruit);
			}
			discipleInfoBiz.savaResDiscipleInfo(bean);
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}
	@RequestMapping(value={"/user/saveDocSimple4jszy"},method={RequestMethod.POST})
	@ResponseBody
	public String saveDocSimple4jszy(SysUser user, ResDoctor doctor, String roleFlag, BaseUserResumeExtInfoForm resumeExtInfoForm,ResDiscipleInfo bean){

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
			SysDept dept = deptBiz.readSysDept(user.getDeptFlow());
			if(dept!=null){
				user.setDeptName(dept.getDeptName());
			}
//			user.setOrgFlow(orgFlow);
//			user.setOrgName(orgName);
			if (StringUtil.isBlank(user.getOrgFlow())) {
				if (GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
					user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow()); //人员记录所属机构与部门
					user.setOrgName(GlobalContext.getCurrentUser().getOrgName());
				}
			}
			user.setTitleName(DictTypeEnum.UserTitle.getDictNameById(user.getTitleId()));
			user.setPostName(DictTypeEnum.UserPost.getDictNameById(user.getPostId()));
//			user.setDegreeName(DictTypeEnum.UserDegree.getDictNameById(user.getDegreeId()));
//			user.setEducationName(DictTypeEnum.UserEducation.getDictNameById(user.getEducationId()));
			doctorName = user.getUserName();
		}
		if(doctor!=null){
			//人员类型
			if(StringUtil.isNotBlank(doctor.getDoctorTypeId())){
				doctor.setDoctorTypeName(ResDocTypeEnum.getNameById(doctor.getDoctorTypeId()));
				if(ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())||ResDocTypeEnum.CompanyEntrust.getId().equals(doctor.getDoctorTypeId())){
					if("1".equals(resumeExtInfoForm.getMedicalHeaithOrgId())){
						//基层医疗卫生机构
						resumeExtInfoForm.setBasicHealthOrgId("");//基层医疗卫生机构
						resumeExtInfoForm.setBasicHealthOrgName("");
						resumeExtInfoForm.setBasicHealthOrgLevelId("");//基层医疗卫生机构等级
						resumeExtInfoForm.setBasicHealthOrgLevelName("");
					}else if("2".equals(resumeExtInfoForm.getMedicalHeaithOrgId()) || "4".equals(resumeExtInfoForm.getMedicalHeaithOrgId())){
						//医院相关
						resumeExtInfoForm.setHospitalAttrId("");//医院性质
						resumeExtInfoForm.setHospitalAttrName("");
						resumeExtInfoForm.setHospitalCategoryId("");//医院类别
						resumeExtInfoForm.setHospitalCategoryName("");
						resumeExtInfoForm.setBaseAttributeId("");//单位等级
						resumeExtInfoForm.setBaseAttributeName("");
						//基层医疗卫生机构
						resumeExtInfoForm.setBasicHealthOrgId("");//基层医疗卫生机构
						resumeExtInfoForm.setBasicHealthOrgName("");
						resumeExtInfoForm.setBasicHealthOrgLevelId("");//基层医疗卫生机构等级
						resumeExtInfoForm.setBasicHealthOrgLevelName("");
					}else if("3".equals(resumeExtInfoForm.getMedicalHeaithOrgId())){
						//医院相关
						resumeExtInfoForm.setHospitalCategoryId("");//医院类别
						resumeExtInfoForm.setHospitalCategoryName("");
						resumeExtInfoForm.setBaseAttributeId("");//单位等级
						resumeExtInfoForm.setBaseAttributeName("");
					}
					if(ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())){
						doctor.setSendCityId("");
						doctor.setSendCityName("");
					}
				}else if(ResDocTypeEnum.Social.getId().equals(doctor.getDoctorTypeId())){
					//派送单位（派送学校）相关
					doctor.setWorkOrgId("");
					doctor.setWorkOrgName("");
					doctor.setSendCityId("");
					doctor.setSendCityName("");
					//单位人类型
					doctor.setCompanyType("");
					/************以下医疗卫生机构相关************/
					resumeExtInfoForm.setMedicalHeaithOrgId("");
					resumeExtInfoForm.setMedicalHeaithOrgName("");
					//医院相关
					resumeExtInfoForm.setHospitalAttrId("");//医院性质
					resumeExtInfoForm.setHospitalAttrName("");
					resumeExtInfoForm.setHospitalCategoryId("");//医院类别
					resumeExtInfoForm.setHospitalCategoryName("");
					resumeExtInfoForm.setBaseAttributeId("");//单位等级
					resumeExtInfoForm.setBaseAttributeName("");
					//基层医疗卫生机构
					resumeExtInfoForm.setBasicHealthOrgId("");//基层医疗卫生机构
					resumeExtInfoForm.setBasicHealthOrgName("");
					resumeExtInfoForm.setBasicHealthOrgLevelId("");//基层医疗卫生机构等级
					resumeExtInfoForm.setBasicHealthOrgLevelName("");
					//基层医疗卫生机构相关
					/************以上医疗卫生机构相关************/
				}else if(ResDocTypeEnum.Graduate.getId().equals(doctor.getDoctorTypeId())){
					//单位人类型
					doctor.setCompanyType("");
					/************以下医疗卫生机构相关************/
					resumeExtInfoForm.setMedicalHeaithOrgId("");
					resumeExtInfoForm.setMedicalHeaithOrgName("");
					//医院相关
					resumeExtInfoForm.setHospitalAttrId("");//医院性质
					resumeExtInfoForm.setHospitalAttrName("");
					resumeExtInfoForm.setHospitalCategoryId("");//医院类别
					resumeExtInfoForm.setHospitalCategoryName("");
					resumeExtInfoForm.setBaseAttributeId("");//单位等级
					resumeExtInfoForm.setBaseAttributeName("");
					//基层医疗卫生机构
					resumeExtInfoForm.setBasicHealthOrgId("");//基层医疗卫生机构
					resumeExtInfoForm.setBasicHealthOrgName("");
					resumeExtInfoForm.setBasicHealthOrgLevelId("");//基层医疗卫生机构等级
					resumeExtInfoForm.setBasicHealthOrgLevelName("");
					//基层医疗卫生机构相关
					/************以上医疗卫生机构相关************/
					doctor.setSendCityId("");
					doctor.setSendCityName("");
				}
			}else{
				doctor.setDoctorTypeName("");
			}
			//是否是对口支援
			if("N".equals(doctor.getIsPartner())){
				doctor.setSourceProvincesId("");//生源省份
				doctor.setSourceProvincesName("");
			}
			//是否硕士在读
			if("1".equals(resumeExtInfoForm.getMasterStatue())){
				resumeExtInfoForm.setMasterDegreeId("");
				resumeExtInfoForm.setMasterDegreeName("");
				resumeExtInfoForm.setMasterDegreeTypeId("");
				resumeExtInfoForm.setMasterDegreeTypeName("");
				resumeExtInfoForm.setMasterGraTime("");
			}
			//是否博士在读
			if("1".equals(resumeExtInfoForm.getDoctorStatue())){
				resumeExtInfoForm.setDoctorDegreeId("");
				resumeExtInfoForm.setDoctorDegreeName("");
				resumeExtInfoForm.setDoctorDegreeTypeId("");
				resumeExtInfoForm.setDoctorDegreeTypeName("");
				resumeExtInfoForm.setDoctorGraTime("");
			}
			doctor.setDoctorName(doctorName);
			doctor.setOrgFlow(orgFlow);
			doctor.setOrgName(orgName);
			//doctor.setDeptFlow(user.getDeptFlow()); //???
			//doctor.setDeptName(user.getDegreeName());//????
			if(StringUtil.isNotBlank(doctor.getDoctorTypeId())){
				doctor.setDoctorTypeName(DictTypeEnum.DoctorType.getDictNameById(doctor.getDoctorTypeId()));
			}else{
				doctor.setDoctorTypeName("");
			}
//			doctor.setGraduatedName(DictTypeEnum.GraduateSchool.getDictNameById(doctor.getGraduatedId()));
			if(StringUtil.isBlank(doctor.getTrainingTypeId())&&StringUtil.isNotBlank(doctor.getDoctorCategoryId())){
				doctor.setTrainingTypeId(doctor.getDoctorCategoryId());
				doctor.setTrainingTypeName(JszyTrainCategoryEnum.getNameById(doctor.getDoctorCategoryId()));
			}
			doctor.setTrainingSpeName(DictTypeEnum.DoctorTrainingSpe.getDictNameById(doctor.getTrainingSpeId()));
			if(StringUtil.isNotBlank(doctor.getTrainingTypeId())){
				doctor.setDoctorCategoryId(doctor.getTrainingTypeId());
			}
			if(StringUtil.isNotBlank(doctor.getDoctorCategoryId())){
				doctor.setDoctorCategoryName(JszyTrainCategoryEnum.getNameById(doctor.getDoctorCategoryId()));
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
		if(GlobalConstant.ZERO_LINE!=resDoctorBiz.editDocUser(doctor, user)){
			if(StringUtil.isNotBlank(doctor.getDiscipleTeacherFlow()) && StringUtil.isNotBlank(doctor.getDoctorFlow())){
				boolean discipleTeacherExist = discipleBiz.searchStudentDiscipleTeacher(doctor.getDiscipleTeacherFlow(),doctor.getDoctorFlow());
				if(!discipleTeacherExist){
					ResStudentDiscipleTeacher studentDiscipleTeacher = new ResStudentDiscipleTeacher();
					studentDiscipleTeacher.setDoctorFlow(doctor.getDoctorFlow());
					studentDiscipleTeacher.setDoctorName(doctor.getDoctorName());
					studentDiscipleTeacher.setTeacherFlow(doctor.getDiscipleTeacherFlow());
					studentDiscipleTeacher.setTeacherName(doctor.getDiscipleTeacherName());
					discipleBiz.saveStudentDiscipleTeacher(studentDiscipleTeacher);
				}
			}
			if(StringUtil.isNotBlank(resumeExtInfoForm.getGraduatedName())){//本科毕业院校
				List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("GraduateSchool");
				for(SysDict s:dictList){
					if (s.getDictName().equals(resumeExtInfoForm.getGraduatedName())) {
						resumeExtInfoForm.setGraduatedId(s.getDictId());
					}
				}
			}
			//JavaBean转换成xml
			if(StringUtil.isNotBlank(resumeExtInfoForm.getGraduatedName())){//本科毕业院校
				List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("GraduateSchool");
				for(SysDict s:dictList){
					if (s.getDictName().equals(resumeExtInfoForm.getGraduatedName())) {
						resumeExtInfoForm.setGraduatedId(s.getDictId());
					}
				}
			}
			if(StringUtil.isNotBlank(resumeExtInfoForm.getDegreeId())){//本科学位
				List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("UserDegree");
				for(SysDict s:dictList){
					if (s.getDictId().equals(resumeExtInfoForm.getDegreeId())) {
						resumeExtInfoForm.setDegreeName(s.getDictName());
					}
				}
			}
			if(StringUtil.isNotBlank(resumeExtInfoForm.getMasterDegreeId())){//硕士学位
				List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("UserDegree");
				for(SysDict s:dictList){
					if (s.getDictId().equals(resumeExtInfoForm.getMasterDegreeId())) {
						resumeExtInfoForm.setMasterDegreeName(s.getDictName());
					}
				}
			}
			if(StringUtil.isNotBlank(resumeExtInfoForm.getMasterGraSchoolName())){//硕士毕业院校
				List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("GraduateSchool");
				for(SysDict s:dictList){
					if (s.getDictName().equals(resumeExtInfoForm.getMasterGraSchoolName())) {
						resumeExtInfoForm.setMasterGraSchoolId(s.getDictId());
					}
				}
			}
			if(StringUtil.isNotBlank(resumeExtInfoForm.getDoctorDegreeId())){//博士学位
				List<SysDict> dictList= DictTypeEnum.sysListDictMap.get("UserDegree");
				for(SysDict s:dictList){
					if (s.getDictId().equals(resumeExtInfoForm.getDoctorDegreeId())) {
						resumeExtInfoForm.setDoctorDegreeName(s.getDictName());
					}
				}
			}
			if(StringUtil.isNotBlank(resumeExtInfoForm.getDoctorGraSchoolName())){//博士毕业院校
				List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("GraduateSchool");
				for(SysDict s:dictList){
					if (s.getDictName().equals(resumeExtInfoForm.getDoctorGraSchoolName())) {
						resumeExtInfoForm.setDoctorGraSchoolId(s.getDictId());
					}
				}
			}
			if(StringUtil.isNotBlank(resumeExtInfoForm.getMedicalHeaithOrgId())){//医疗机构
				List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("MedicalHeaithOrg");
				for(SysDict s:dictList){
					if (s.getDictId().equals(resumeExtInfoForm.getMedicalHeaithOrgId())) {
						resumeExtInfoForm.setMedicalHeaithOrgName(s.getDictName());
					}
				}
			}
			if(StringUtil.isNotBlank(resumeExtInfoForm.getHospitalAttrId())){
				List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("HospitalAttr");
				for(SysDict s:dictList){
					if (s.getDictId().equals(resumeExtInfoForm.getHospitalAttrId())) {
						resumeExtInfoForm.setHospitalAttrName(s.getDictName());
					}
				}
			}
			if(StringUtil.isNotBlank(resumeExtInfoForm.getHospitalCategoryId())){
				List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("HospitalCategory");
				for(SysDict s:dictList){
					if (s.getDictId().equals(resumeExtInfoForm.getHospitalCategoryId())) {
						resumeExtInfoForm.setHospitalCategoryName(s.getDictName());
					}
				}
			}
			if(StringUtil.isNotBlank(resumeExtInfoForm.getBasicHealthOrgId())){
				List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("BasicHealthOrg");
				for(SysDict s:dictList){
					if (s.getDictId().equals(resumeExtInfoForm.getBasicHealthOrgId())) {
						resumeExtInfoForm.setBasicHealthOrgName(s.getDictName());
					}
				}
			}
			if(StringUtil.isNotBlank(resumeExtInfoForm.getBaseAttributeId())){
				List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("BaseAttribute");
				for(SysDict s:dictList){
					if (s.getDictId().equals(resumeExtInfoForm.getBaseAttributeId())) {
						resumeExtInfoForm.setBaseAttributeName(s.getDictName());
					}
				}
			}
			if(StringUtil.isNotBlank(resumeExtInfoForm.getBasicHealthOrgLevelId())){
				List<SysDict> dictList=DictTypeEnum.sysListDictMap.get("BasicHealthOrgLevel");
				for(SysDict s:dictList){
					if (s.getDictId().equals(resumeExtInfoForm.getBasicHealthOrgLevelId())) {
						resumeExtInfoForm.setBasicHealthOrgLevelName(s.getDictName());
					}
				}
			}
			String xmlContent = JaxbUtil.convertToXml(resumeExtInfoForm);
			PubUserResume pubUserResume = userResumeBiz.readPubUserResume(user.getUserFlow());
			if(pubUserResume == null){
				pubUserResume = new PubUserResume();
			}
			pubUserResume.setUserResume(xmlContent);
			userResumeBiz.savePubUserResume(user, pubUserResume);
			//修改res_doctor_recruit的graduation_year
			ResDoctorRecruit doctorRecruit = new ResDoctorRecruit();
			doctorRecruit.setDoctorFlow(doctor.getDoctorFlow());
			doctorRecruit.setOrgFlow(doctor.getOrgFlow());
			doctorRecruit.setCatSpeId(doctor.getDoctorCategoryId());
			doctorRecruit.setSpeId(doctor.getTrainingSpeId());
			doctorRecruit.setAuditStatusId("Passed");
			ResDoctorRecruit recruit = doctorRecruitBiz.searchRecruitByResDoctor(doctorRecruit);
			if(null!=recruit&&StringUtil.isNotBlank(doctor.getGraduationYear())) {
				recruit.setGraduationYear(doctor.getGraduationYear());
				doctorRecruitBiz.updateDocrecruit(recruit);
			}
			if(StringUtil.isNotBlank(bean.getDiscipleFlow())){
				discipleInfoBiz.savaResDiscipleInfo(bean);
			}
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 医院延期
	 */
	@RequestMapping("/delayDoc")
	@ResponseBody
	public int delayDoc(ResDocotrDelayTeturn docotrDelayTeturn,MultipartFile file,String delayYear){
		//保存附件
		PubFile pubFile = new PubFile();
		if(file.getSize()>10*1024*1024){
			return 3;
		}
		if(file.getSize()<=0){
			return 4;
		}
		if(file.getSize() > 0){
			//创建目录
			String dateString = DateUtil.getCurrDate2();
			String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator + "delayImg" + File.separator+ dateString ;
			File fileDir = new File(newDir);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			//文件名
			String originalFilename = file.getOriginalFilename();
			String oldFileName=originalFilename;
			originalFilename = PkUtil.getUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			File newFile = new File(fileDir, originalFilename);
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("保存文件失败！");
			}
			String filePath=File.separator + "delayImg" + File.separator+ dateString+File.separator+originalFilename;
			pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			pubFile.setFilePath(filePath);
			pubFile.setFileName(oldFileName);
			pubFile.setProductType("延期文件");
		}
		String doctorFlow = docotrDelayTeturn.getDoctorFlow();
		ResDoctor doctor = resDoctorBiz.findByFlow(doctorFlow);
		docotrDelayTeturn.setOrgFlow(doctor.getOrgFlow());
		docotrDelayTeturn.setOrgName(doctor.getOrgName());
		docotrDelayTeturn.setSessionNumber(doctor.getSessionNumber());
		docotrDelayTeturn.setTrainingYears(doctor.getTrainingYears());
		docotrDelayTeturn.setDoctorName(doctor.getDoctorName());
		docotrDelayTeturn.setTrainingSpeId(doctor.getTrainingSpeId());
		docotrDelayTeturn.setTrainingSpeName(doctor.getTrainingSpeName());
		docotrDelayTeturn.setTrainingTypeId(doctor.getDoctorTypeId());
		docotrDelayTeturn.setTrainingTypeName(doctor.getDoctorTypeName());
		docotrDelayTeturn.setGraduationYear(delayYear);
		docotrDelayTeturn.setPolicyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		docotrDelayTeturn.setPolicyUserName(GlobalContext.getCurrentUser().getUserName());
		docotrDelayTeturn.setTypeId(ResRecTypeEnum.Delay.getId());
		docotrDelayTeturn.setTypeName(ResRecTypeEnum.Delay.getName());
		docotrDelayTeturn.setAuditStatusId(ResBaseStatusEnum.Auditing.getId());
		docotrDelayTeturn.setAuditStatusName(ResBaseStatusEnum.Auditing.getName());
		int result = resDoctorDelayTeturnBiz.edit(docotrDelayTeturn,pubFile);
		if(result==1){
			doctor.setGraduationYear(delayYear);
			int result2 = resDoctorBiz.editDoctor(doctor);
			if (result2 == 1) {
				ResDoctorRecruit doctorRecruit = new ResDoctorRecruit();
				doctorRecruit.setDoctorFlow(doctor.getDoctorFlow());
				doctorRecruit.setOrgFlow(doctor.getOrgFlow());
				doctorRecruit.setCatSpeId(doctor.getDoctorCategoryId());
				doctorRecruit.setSpeId(doctor.getTrainingSpeId());
				doctorRecruit.setAuditStatusId("Passed");
				ResDoctorRecruit recruit = doctorRecruitBiz.searchRecruitByResDoctor(doctorRecruit);
				if(null!=recruit) {
					recruit.setGraduationYear(doctor.getGraduationYear());
					doctorRecruitBiz.updateDocrecruit(recruit);
				}
				return 1;
			}
			return 0;
		}
		return 0;
	}

	/**
	 * 医院退培
	 */
	@RequestMapping("/returnDoc")
	@ResponseBody
	public int returnDoc(ResDocotrDelayTeturn docotrDelayTeturn,MultipartFile file){
		String res_return_audit = InitConfig.getSysCfg("res_return_audit");
		//保存附件
		PubFile pubFile = new PubFile();
		if(file.getSize()>10*1024*1024){
			return 3;
		}
		if(file.getSize() > 0){
			//创建目录
			String dateString = DateUtil.getCurrDate2();
			String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator + "returnImg" + File.separator+ dateString ;
			File fileDir = new File(newDir);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			//文件名
			String originalFilename = file.getOriginalFilename();
			String oldFileName=originalFilename;
			originalFilename = PkUtil.getUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			File newFile = new File(fileDir, originalFilename);
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("保存文件失败！");
			}
			String filePath=File.separator + "returnImg" + File.separator+ dateString+File.separator+originalFilename;
			pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			pubFile.setFilePath(filePath);
			pubFile.setFileName(oldFileName);
			pubFile.setProductType("退培文件");
		}

		String doctorFlow = docotrDelayTeturn.getDoctorFlow();
		ResDoctor doctor = resDoctorBiz.findByFlow(doctorFlow);
		docotrDelayTeturn.setOrgFlow(doctor.getOrgFlow());
		docotrDelayTeturn.setOrgName(doctor.getOrgName());
		docotrDelayTeturn.setSessionNumber(doctor.getSessionNumber());
		docotrDelayTeturn.setTrainingYears(doctor.getTrainingYears());
		docotrDelayTeturn.setDoctorName(doctor.getDoctorName());
		docotrDelayTeturn.setTrainingSpeId(doctor.getTrainingSpeId());
		docotrDelayTeturn.setTrainingSpeName(doctor.getTrainingSpeName());
		docotrDelayTeturn.setTrainingTypeId(doctor.getDoctorTypeId());
		docotrDelayTeturn.setTrainingTypeName(doctor.getDoctorTypeName());
		docotrDelayTeturn.setGraduationYear(doctor.getGraduationYear());
		docotrDelayTeturn.setPolicyName("1".equals(docotrDelayTeturn.getPolicyId())?"协议退培":"违约退培");
		docotrDelayTeturn.setReasonName("1".equals(docotrDelayTeturn.getReasonId())?"辞职":("2".equals(docotrDelayTeturn.getReasonId())?"考研":"其他"));
		docotrDelayTeturn.setPolicyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		docotrDelayTeturn.setPolicyUserName(GlobalContext.getCurrentUser().getUserName());

		if(GlobalConstant.RECORD_STATUS_N.equals(res_return_audit)){//不需要省厅终极审核()
			docotrDelayTeturn.setAuditStatusId(ResBaseStatusEnum.GlobalPassed.getId());
			docotrDelayTeturn.setAuditStatusName(ResBaseStatusEnum.GlobalPassed.getName());
			resDoctorDelayTeturnBiz.edit(docotrDelayTeturn,pubFile);
			SysUser sysUser = userBiz.readSysUser(docotrDelayTeturn.getDoctorFlow());
			//湖北住培 违约退培加入黑名单
			if(!"1".equals(docotrDelayTeturn.getPolicyId())){
				if(StringUtil.isBlank(sysUser.getIdNo())){
					if(StringUtil.isNotBlank(doctorFlow)) {
						sysUser.setRecordStatus(GlobalConstant.FLAG_N);
						userBiz.edit(sysUser);
						ResDoctor resDoctor=doctorBiz.searchByUserFlow(doctorFlow);
						if(resDoctor!=null) {
							resDoctor.setRecordStatus(GlobalConstant.FLAG_N);
							doctorBiz.editDoctor(resDoctor);
						}
					}
				}else{
					saveBlackList(sysUser.getIdNo(), docotrDelayTeturn.getPolicyName()+":"+docotrDelayTeturn.getReasonName(),sysUser.getUserName(),"自动");
				}
			}else{
				if(StringUtil.isNotBlank(doctorFlow)) {
					sysUser.setRecordStatus(GlobalConstant.FLAG_N);
					userBiz.edit(sysUser);
					ResDoctor resDoctor=doctorBiz.searchByUserFlow(doctorFlow);
					if(resDoctor!=null) {
						resDoctor.setRecordStatus(GlobalConstant.FLAG_N);
						doctorBiz.editDoctor(resDoctor);
					}
				}
			}
			return 1;
		}else {//需要
			docotrDelayTeturn.setAuditStatusId(ResBaseStatusEnum.Auditing.getId());
			docotrDelayTeturn.setAuditStatusName(ResBaseStatusEnum.Auditing.getName());
			int result = resDoctorDelayTeturnBiz.edit(docotrDelayTeturn,pubFile);
			return result;
		}
	}
	//下载附件
	@RequestMapping(value = {"/fileDown" }, method = RequestMethod.GET)
	public void fileDown(String fileFlow, final HttpServletResponse response) throws Exception{
		PubFile file = this.fileBiz.readFile(fileFlow);
		fileBiz.downPubFile(file,response);
	}
	/**
	 * 轮转要求
	 * @return
	 */
	@RequestMapping(value="/rotationRequire",method=RequestMethod.GET)
	public String rotationRequire(Model model,String typeId){
		model.addAttribute("typeId",typeId);
		return "res/doctor/rotationRequire";
	}

	@RequestMapping(value = {"/requireList"},method = RequestMethod.GET)
	public String requireList(SchRotationDeptReq deptReq,Model model){
		if(deptReq !=null){
			//默认添加其他项
			schRotationDeptBiz.defaultOtherItem(deptReq.getRelRecordFlow(),deptReq.getRecTypeId());

			List<SchRotationDeptReq> deptReqList = schRotationDeptBiz.searchDeptReqByRel(deptReq.getRelRecordFlow(),deptReq.getRecTypeId());
			model.addAttribute("deptReqList",deptReqList);
		}
		return "res/doctor/requireList";
	}
	
	/**
	 * 医师主页1
	 */
	@RequestMapping(value="/beforeDoctorMain",method=RequestMethod.GET)
	public String beforeDoctorMain(){
		String url = (String)getSessionAttribute("viewUrl");
		if(!StringUtil.isNotBlank(url)){
			url = "/res/doc/doctorMain";
		}
		return "redirect:"+url;
	}
	
	/**
	 * 医师主页2
	 */
	@RequestMapping(value="/doctorMain",method=RequestMethod.GET)
	public String doctorMain(Model model) {
		//ResDoctor doctor = (ResDoctor) getSessionAttribute("currDoctor");
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		ResDoctor dbDoctor = resDoctorBiz.readDoctor(userFlow);
		model.addAttribute("doctor", dbDoctor);
//		if (dbDoctor == null || !GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_doctor_category_" + dbDoctor.getDoctorCategoryId() + "_sch"))) {
//			return "redirect:/resedu/student/studyCenter";
//		}

		if(dbDoctor!=null){
//			if(!StringUtil.isNotBlank(doctor.getRotationFlow())){
//				schArrangeResultBiz.createFreeRosteringResult(doctor);
//			}
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_custom_result_flag"))){
				return "redirect:/res/doc/searchRotationList";
			}
			if(!GlobalConstant.FLAG_Y.equals(dbDoctor.getSelDeptFlag()) && GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_doc_seldept"))){
				return "redirect:/res/doc/selDeptAndRosteringHand";
			}
			if(GlobalConstant.FLAG_Y.equals(dbDoctor.getSelDeptFlag()) && !GlobalConstant.FLAG_Y.equals(dbDoctor.getSchFlag()) && GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_doc_rostering"))){
				return "redirect:/res/doc/selDeptAndRosteringHand";
			}
		}
		return "redirect:/res/doc/searchRotationList";
	}

	/**
	 * 轮转手册
	 * @param model
	 * @return
     */
	@RequestMapping(value="/evaluateView",method=RequestMethod.GET)
	public String evaluateView(Model model) {
		//ResDoctor doctor = (ResDoctor) getSessionAttribute("currDoctor");
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		ResDoctor dbDoctor = resDoctorBiz.readDoctor(userFlow);
		model.addAttribute("doctor", dbDoctor);
//		if (dbDoctor == null || !GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_doctor_category_" + dbDoctor.getDoctorCategoryId() + "_sch"))) {
//			return "redirect:/resedu/student/studyCenter";
//		}

		if(dbDoctor!=null){
//			if(!StringUtil.isNotBlank(doctor.getRotationFlow())){
//				schArrangeResultBiz.createFreeRosteringResult(doctor);
//			}
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_custom_result_flag"))){
				return "redirect:/res/doc/searchRotationList";
			}
			if(!GlobalConstant.FLAG_Y.equals(dbDoctor.getSelDeptFlag()) && GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_doc_seldept"))){
				return "redirect:/res/doc/selDeptAndRosteringHand";
			}
			if(GlobalConstant.FLAG_Y.equals(dbDoctor.getSelDeptFlag()) && !GlobalConstant.FLAG_Y.equals(dbDoctor.getSchFlag()) && GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_doc_rostering"))){
				return "redirect:/res/doc/selDeptAndRosteringHand";
			}
		}
		return "redirect:/res/doc/searchRotationList";
	}

	/**
	 * 学员操作选科
	 *
	 * @return
     */
	@RequestMapping("/operSelDept")
	@ResponseBody
	public synchronized String operSelDept(String selectData) throws ParseException {

		String doctorFlow = GlobalContext.getCurrentUser().getUserFlow();	//医师流水号
		ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);

		ResEnterOpenCfg openCfg = enterOpenCfgBiz.readResEnterOpenCfg(doctor.getOrgFlow());
		String currTime = DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
		if(openCfg==null){
			return "管理员未配置选科时间，当前时间无法进行选科！";
		}
		if(StringUtil.isBlank(openCfg.getStartMonth())){
			return "管理员未配置选科开始月份，当前时间无法进行选科！";
		}
		if(StringUtil.isBlank(openCfg.getSessionNumber())){
			return "管理员未配置当前可选科年级，当前时间无法进行选科！";
		}
		int result1 = currTime.compareTo(openCfg.getStartDate());
		int result2 = currTime.compareTo(openCfg.getEndDate());
		if(result1<0 || result2>0){
			return"选科时间为："+openCfg.getStartDate()+"~"+openCfg.getEndDate()+",当前时间无法进行选科！";
		}
		if(!openCfg.getSessionNumber().equals(doctor.getSessionNumber()))
		{
			return"当前可选科年级："+openCfg.getSessionNumber()+",你的年级为"+doctor.getSessionNumber()+",不允许选科！";
		}
		//判断当前开放时间内学员是否已经选择过科室
		int count1=schArrangeResultBiz.checkSelectResult(doctorFlow,openCfg.getStartDate(),openCfg.getEndDate());
		if(count1>0)
		{
			return"你已在"+openCfg.getStartDate()+"~"+openCfg.getEndDate()+"选科时间内选过科室了,无法再次进行选科！";
		}
		//已经选择
		int schMonth=schArrangeResultBiz.getAllSchMonth(doctorFlow);
		if(schMonth>=16)
		{
			return "轮转时间已满足16个月，无需选科！";
		}
		if(StringUtil.isBlank(selectData))
		{
			return "请选择需要轮转的科室！";
		}
		SelectDeptForm form =null;
		try {
			form=JSON.parseObject(selectData, SelectDeptForm.class);
		}catch (Exception e){
			return "保存数据格式不正确！";
		}
		List<SelectDept> selectDepts=form.getDepts();
		if(selectDepts==null||selectDepts.size()==0)
		{
			return "请选择需要轮转的科室！";
		}
		int schMonthAll=0;
		Map<String,Integer> map=new HashMap<>();
		for(SelectDept dept:selectDepts)
		{
			SchDept schDept=schDeptBiz.readSchDept(dept.getSchDeptFlow());
			if(schDept==null||GlobalConstant.RECORD_STATUS_N.equals(schDept.getRecordStatus()))
			{
				return "轮转科室【"+dept.getSchDeptName()+"】，已被删除或不存在，请重新选择！";
			}
			SchRotationGroup schRotationGroup=schRotationtGroupBiz.readSchRotationGroup(dept.getGroupFlow());
			if(schRotationGroup==null||GlobalConstant.RECORD_STATUS_N.equals(schRotationGroup.getRecordStatus()))
			{
				return "标准组【"+dept.getGroupName()+"】，已被删除或不存在，请重新选择！";
			}
			if(schRotationGroup.getDeptNum()==null)
			{
				return "标准组【"+dept.getGroupName()+"】选科数未设置，请重新选择！";
			}
			SchRotationDept schRotationDept=schRotationDeptBiz.readSchRotationDept(dept.getRecordFlow());
			if(schRotationDept==null||GlobalConstant.RECORD_STATUS_N.equals(schRotationDept.getRecordStatus()))
			{
				return "标准科室【"+dept.getStandardDeptName()+"】，已被删除或不存在，请重新选择！";
			}
			if(!dept.getSchMonth().equals(schRotationDept.getSchMonth()))
			{
				return "标准科室【"+schRotationDept.getStandardDeptName()+"】，轮转时间已被修改，请重新选择！";
			}
			int standardDeptNum=schArrangeResultBiz.checkSelectStandardDept(doctorFlow,schRotationDept.getStandardDeptId(),schRotationDept.getGroupFlow());
			if(standardDeptNum>0)
			{
				return "标准科室【"+schRotationDept.getStandardDeptName()+"】，已轮转，请重新选择！";
			}
			String schDate=dept.getSchDate();
			if(StringUtil.isBlank(schDate))
			{
				return "请为轮转科室【"+dept.getSchDeptName()+"】选择轮转时间！";
			}
			String[] schDates=schDate.split(",");
			for(String d:schDates) {
				int resultCount = schArrangeResultBiz.countArrangeResultBySchDeptFlow(d, schDept.getSchDeptFlow(), doctor.getSessionNumber());
				int deptNum = 0;
				if (schDept.getDeptNum() != null)
					deptNum = schDept.getDeptNum();
				if (deptNum - resultCount < 1) {
					return "轮转科室【" + dept.getSchDeptName() + "】，在轮转时间【"+d+"】内开放人数不足，请重新选择！";
				}
				int hasSelect=schArrangeResultBiz.countResultByDoctorAndDate(d,doctorFlow);
				if(hasSelect>0)
				{
					return "轮转科室【" + dept.getSchDeptName() + "】，在轮转时间【"+d+"】内已轮转，请重新选择！";
				}
			}
			if (StringUtil.isNotBlank(schRotationDept.getSchMonth())) {
				//时间
				schMonthAll += Integer.valueOf(schRotationDept.getSchMonth());
			}
			Integer count=map.get(dept.getGroupFlow());
			if(count==null)
				count=0;
			count++;
			int groupSelectDeptCount=	schArrangeResultBiz.countArrangeResultByGroupFlow(doctorFlow,dept.getGroupFlow());
			if(count+groupSelectDeptCount>schRotationGroup.getDeptNum())
			{
				return "标准组【"+dept.getGroupName()+"】一共只可选择"+schRotationGroup.getDeptNum()+"个科室数，请重新选择！";
			}

		}
		if(schMonthAll!=2)
		{
			return "每次选择的轮转时间必须为2个月！请重新选择！";
		}
		if(schMonth+schMonthAll>16)
		{
			return "轮转时间已超过16个月，请重新选择！";
		}

		try {
			return  schArrangeResultBiz.saveDoctorSelectDept(selectDepts);
		} catch (ParseException e) {
			return "操作失败！";
		}
	}

	/**
	 *  学员选科
	 * */
	@RequestMapping(value = {"/seldept" },method = {RequestMethod.GET,RequestMethod.POST})
	public String selDoctorDept(Model model) throws ParseException {
		String doctorFlow = GlobalContext.getCurrentUser().getUserFlow();	//医师流水号
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();		//机构流水号
		if(StringUtil.isBlank(orgFlow))
		{
			ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
			if(doctor!=null)
				orgFlow=doctor.getOrgFlow();
		}
		ResEnterOpenCfg openCfg = enterOpenCfgBiz.readResEnterOpenCfg(orgFlow);
		String currTime = DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
		if(openCfg==null){
			model.addAttribute("result","管理员未配置选科时间，当前时间无法进行选科！");
			return "res/doctor/selDeptError";
		}
		if(StringUtil.isBlank(openCfg.getStartMonth())){
			model.addAttribute("result","管理员未配置选科开始月份，当前时间无法进行选科！");
			return "res/doctor/selDeptError";
		}
		if(StringUtil.isBlank(openCfg.getSessionNumber())){
			model.addAttribute("result","管理员未配置当前可选科年级，当前时间无法进行选科！");
			return "res/doctor/selDeptError";
		}
		int schMonth=schArrangeResultBiz.getAllSchMonth(doctorFlow);
		if(schMonth>=16)
		{
			model.addAttribute("result","轮转时间已满足16个月，无需选科！");
			return "res/doctor/selDeptError";
		}
		int result1 = currTime.compareTo(openCfg.getStartDate());
		int result2 = currTime.compareTo(openCfg.getEndDate());
		if(result1<0 || result2>0){
				model.addAttribute("result","选科时间为："+openCfg.getStartDate()+"~"+openCfg.getEndDate()+",当前时间无法进行选科！");
			return "res/doctor/selDeptError";
		}
		String nextMonth= DateUtil.newMonthOfAddMonths(openCfg.getStartMonth(),0);
		String nextNextMonth= DateUtil.newMonthOfAddMonths(openCfg.getStartMonth(),1);
		model.addAttribute("nextMonth",nextMonth);
		model.addAttribute("nextNextMonth",nextNextMonth);
		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
			if(!openCfg.getSessionNumber().equals(doctor.getSessionNumber()))
			{
				model.addAttribute("result","当前可选科年级："+openCfg.getSessionNumber()+",你的年级为"+doctor.getSessionNumber()+",不允许选科！");
				return "res/doctor/selDeptError";
			}
			model.addAttribute("doctor",doctor);
			String rotationFlow = doctor.getRotationFlow();//轮转方案流水号
			//判断当前开放时间内学员是否已经选择过科室
			int count=schArrangeResultBiz.checkSelectResult(doctorFlow,openCfg.getStartDate(),openCfg.getEndDate());
			if(count>0)
			{
				model.addAttribute("result","你已在"+openCfg.getStartDate()+"~"+openCfg.getEndDate()+"选科时间内选过科室了,无法再次进行选科！");
				return "res/doctor/selDeptError";
			}
			if(StringUtil.isNotBlank(rotationFlow)){
				List<SchRotationGroup> groups=schRotationtGroupBiz.searchSchRotationGroup(rotationFlow);
				model.addAttribute("groups",groups);
				//显示轮转方案中的标准科室 去除已经选择的标准科室
				//过滤已经轮转过的标准科室（关联 sch_arrange_result和sch_arrange_period_rel）
				//查询sch_arrange_period_rel
				List<SchRotationDept> rotationDepts = schRotationDeptBiz.doctorGetNotSchDept(rotationFlow,doctorFlow);
				//model.addAttribute("rotationDepts",rotationDepts);
				if(rotationDepts==null||rotationDepts.size()==0)
				{
					model.addAttribute("result","无科室需要选择！");
					return "res/doctor/selDeptError";
				}
				//已选择的科室数量
				List<Map<String,Object>> mapList=schArrangeResultBiz.doctorSelectDeptCount(doctorFlow);
				Map<String,String> groupMap = new HashMap<>();
				if(mapList!=null&&mapList.size()>0)
				{
					for(Map<String,Object> map:mapList)
					{
						groupMap.put(String.valueOf(map.get("groupFlow")),String.valueOf(map.get("num")));
					}
				}
				model.addAttribute("groupMap",groupMap);
				//按组分类
				Map<String,List<SchRotationDept>> groupDeptMap=new HashMap<>();
				//科室开放报名人数
				Map<String,SchDept> deptMap = new HashMap<>();
				//已报名人数
				Map<String,Object> countMap = new HashMap<>();
				for(SchRotationDept schRotationDept:rotationDepts )
				{
					SchDept schDept = schDeptBiz.readSchDeptByOrgAndStandardId(orgFlow,schRotationDept.getStandardDeptId());
					deptMap.put(schRotationDept.getRecordFlow(),schDept);
					if(schDept!=null)
					{
						int resultCount=0;
						int resultCount2=0;
						if(StringUtil.isNotBlank(schDept.getSchDeptFlow())){
							resultCount = schArrangeResultBiz.countArrangeResultBySchDeptFlow(nextMonth,schDept.getSchDeptFlow(),doctor.getSessionNumber());
							resultCount2 = schArrangeResultBiz.countArrangeResultBySchDeptFlow(nextNextMonth,schDept.getSchDeptFlow(),doctor.getSessionNumber());
						}
						countMap.put(schRotationDept.getRecordFlow()+"nextMonth",resultCount);
						countMap.put(schRotationDept.getRecordFlow()+"nextNextMonth",resultCount2);
						countMap.put(schRotationDept.getStandardDeptId()+Integer.valueOf(schRotationDept.getSchMonth())+"nextMonth",resultCount);
						countMap.put(schRotationDept.getStandardDeptId()+Integer.valueOf(schRotationDept.getSchMonth())+"nextNextMonth",resultCount2);

					}
					List<SchRotationDept> depts=groupDeptMap.get(schRotationDept.getGroupFlow());
					if(depts==null)
						depts=new ArrayList<>();
					depts.add(schRotationDept);
					groupDeptMap.put(schRotationDept.getGroupFlow(),depts);
				}
				model.addAttribute("groupDeptMap",groupDeptMap);//按组分类Map
				model.addAttribute("deptMap",deptMap);//科室开放报名人数Map
				model.addAttribute("countMap",countMap);//科室已报名人数Map
			}
		}
		return "res/doctor/seldept";
	}
	/**
	 * 学习中心视图
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/studyCenter",method=RequestMethod.GET)
	public String studyCenter(Model model){
		return "res/edu/student/studyCenter";
	}

	//get方法实现
	private <T> T getMethod(String prop,Object obj) throws Exception{
		Class clazz = obj.getClass();
		T value = null;
		String firstWord = prop.substring(0,1);
		firstWord = firstWord.toUpperCase();
		String surplusWords = "";
		if(prop.length()>1){
			surplusWords = prop.substring(1);
		}
		String methodName = "get"+firstWord+surplusWords;
		Method method = clazz.getMethod(methodName);
		value = (T)method.invoke(obj);
		return value;
	}

	//组织key
	private String groupTheKey(Object obj,String[] keys){
		String key = "";
		for(String s : keys){
			String val = "";
			try {
				val = getMethod(s,obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			key+=val;
		}
		return key;
	}

	//以对象本身的属性作为key组织一个map 1-1/N
	private <T,E> Map<String,T> transListObjToMapsSoM(boolean isMany,List<E> objs,String ...keys){
        if (objs != null && !objs.isEmpty() && keys != null && keys.length > 0) {
            Map<String, T> map = new HashMap<String, T>();
            for (E e : objs) {
                String key = groupTheKey(e, keys);
                if (isMany) {
                    List<E> es = (ArrayList<E>) map.get(key);
                    if (es == null) {
                        es = new ArrayList<E>();
                    }
                    es.add(e);
                    map.put(key, (T) es);
                } else {
                    map.put(key, (T) e);
                }
            }
            return map;
        } else {
            return null;
        }
    }

	/**
	 * 个人轮转计划
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/searchRotationList",method=RequestMethod.GET)
	public String searchRotationList(Model model){
		SysUser user = GlobalContext.getCurrentUser();
		String userFlow = user.getUserFlow();

		ResDoctor doctor = resDoctorBiz.readDoctor(userFlow);
		model.addAttribute("doctor", doctor);
		//查询学员轮转过程表中的最大时间和最小时间
		Map<String,Object> processAreaMap = resDoctorBiz.getDocProcessArea(userFlow);
		if(processAreaMap!=null) {
			String minDate = (String) processAreaMap.get("minDate");
			model.addAttribute("minDate", minDate);
			doctor.setInHosDate(minDate);
			resDoctorBiz.editDoctor(doctor);
		}
		List<SchArrangeResult> arrResultList=null;
		Map<String,ResDoctorSchProcess> processMap = null;
		//判断学员是否需要减免
		//培训专业
		String trainingType = doctor.getTrainingTypeId();
		//年级
		String sessionNumber = doctor.getSessionNumber();
		//年限
		String trainingYears = doctor.getTrainingYears();
		boolean isReduction = JszyTrainCategoryEnum.ChineseMedicine.getId().equals(trainingType)||JszyTrainCategoryEnum.TCMGeneral.getId().equals(trainingType);
		isReduction = isReduction && "2015".compareTo(sessionNumber)<=0;
		isReduction = isReduction && (JszyResTrainYearEnum.OneYear.getId().equals(trainingYears) || JszyResTrainYearEnum.TwoYear.getId().equals(trainingYears));
		Map<String,SchDoctorDept> doctorDeptMap=new HashMap<>();
		if(isReduction)
		{
			//获取所有缩减的调整科室
			List<SchDoctorDept> doctorDeptList = resRecBiz.searchReductionDept(userFlow,doctor.getRotationFlow(),doctor.getSecondRotationFlow());
			if(doctorDeptList!=null&&doctorDeptList.size()>0)
			{
				//获取标准科室列表以绑定要求数据
				doctorDeptMap = transListObjToMapsSoM(false,doctorDeptList,"groupFlow","standardDeptId");
			}
		}
		if(doctor!=null && (GlobalConstant.FLAG_Y.equals(doctor.getSchFlag())||
				GlobalConstant.FLAG_Y.equals(doctor.getIsSchFlag()))){
//			arrResultList = schArrangeResultBiz.searchSchArrangeResultByDoctor(userFlow);
			arrResultList = schArrangeResultBiz.searchSchArrangeResultByDoctor4Sort(userFlow);

			Map<String,String> finishPerMap = resRecBiz.getFinishPer(arrResultList);
			model.addAttribute("finishPerMap", finishPerMap);
			if(arrResultList!=null&&!arrResultList.isEmpty()){

				model.addAttribute("arrResultList", arrResultList);

				List<ResDoctorSchProcess> processList = resDoctorProcessBiz.searchProcessByDoctor(userFlow);
				if(processList!=null && processList.size()>0){
					processMap = new HashMap<String, ResDoctorSchProcess>();
					Map<String,String> recContentMap = new HashMap<String, String>();
					for(ResDoctorSchProcess process : processList){
						processMap.put(process.getSchResultFlow(),process);
						recContentMap.put(process.getSchResultFlow(),process.getSchFlag());
					}
					model.addAttribute("processMap", processMap);
					model.addAttribute("recContentMap", recContentMap);
				}

				if(StringUtil.isNotBlank(doctor.getOrgFlow())){
					List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(doctor.getOrgFlow());
					if(schDeptList!=null && schDeptList.size()>0){
						Map<String,SchDept> schDeptMap = new HashMap<String, SchDept>();
						for(SchDept dept : schDeptList){
							schDeptMap.put(dept.getSchDeptFlow(),dept);
						}
						model.addAttribute("schDeptMap", schDeptMap);
					}
				}

				//要求数
//				Map<String,BigDecimal> reqNumMap = new HashMap<String,BigDecimal>();
//				for (SchArrangeResult sar:arrResultList) {
//					List<SchRotationDeptReq> deptReqList = schRotationDeptBiz.searchStandardReqByResult(sar,doctor);
//
//					SchRotation rotation=schRotationtBiz.readSchRotation(sar.getRotationFlow());
//					SchRotationDept srd=schRotationDeptBiz.readStandardRotationDept(sar.getResultFlow());
//					if(deptReqList!=null && deptReqList.size()>0){
//						for(SchRotationDeptReq req : deptReqList){
//							String key = sar.getResultFlow()+req.getRecTypeId()+"req";
//
//							double per=1;
//							String baseSchMonth=srd.getSchMonth();
//							if(isReduction&&srd!=null)
//							{
//								SchDoctorDept sdd=doctorDeptMap.get(srd.getGroupFlow()+srd.getStandardDeptId());
//								if(sdd!=null)
//								{
//									if(GlobalConstant.RECORD_STATUS_Y.equals(sdd.getRecordStatus()))
//									{
//										per=Double.valueOf(sdd.getSchMonth())/Double.valueOf(srd.getSchMonth());
//										baseSchMonth=sdd.getSchMonth();
//									}
//								}
//							}
//							//是否翻倍
//							if(rotation!=null&&GlobalConstant.FLAG_Y.equals(rotation.getIsDouble())&&StringUtil.isNotBlank(baseSchMonth))
//							{
//								if(StringUtil.isNotBlank(sar.getSchMonth()))
//								{
//									per=Double.valueOf(sar.getSchMonth())/Double.valueOf(baseSchMonth)*per;
//								}
//							}
//							BigDecimal num= new BigDecimal(Math.ceil(req.getReqNum().floatValue()*per));
//							if(reqNumMap.get(key)==null){
//								reqNumMap.put(key,num);
//							}else{
//								reqNumMap.put(key,reqNumMap.get(key).add(num));
//							}
//						}
//					}
//				}
//				model.addAttribute("reqNumMap",reqNumMap);
			}

			//培训手册权限
			ResPowerCfg resPowerCfg = resPowerCfgBiz.read("res_doctor_pxsc_"+doctor.getDoctorFlow());
			if(resPowerCfg!=null){
				if(GlobalConstant.FLAG_Y.equals(resPowerCfg.getCfgValue())
						&& GlobalConstant.RECORD_STATUS_Y.equals(resPowerCfg.getRecordStatus())
						){
					model.addAttribute("open",GlobalConstant.FLAG_Y);
				}else{
					model.addAttribute("open",GlobalConstant.FLAG_N);
				}
			}else {
				model.addAttribute("open",GlobalConstant.FLAG_N);
			}
		}
		if(	GlobalConstant.FLAG_Y.equals(doctor.getIsSchFlag())|| !GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_custom_result_flag")))
		{
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_doc_in_by_self"))){
				if(arrResultList!=null && !arrResultList.isEmpty()){
					Map<String, List<SchArrangeResult>> resultMap = new HashMap<String,List<SchArrangeResult>>();
					for(SchArrangeResult s:arrResultList){
						if(processMap!=null){
							String resultFlow = s.getResultFlow();
							ResDoctorSchProcess process = processMap.get(resultFlow);
							if(process!=null){
								String isCurrentFlag = process.getIsCurrentFlag();
								String schFlag = process.getSchFlag();
								if(!GlobalConstant.FLAG_Y.equals(isCurrentFlag) && !GlobalConstant.FLAG_Y.equals(schFlag)){
									String currDate = DateUtil.getCurrDate();
									String schStartDate = s.getSchStartDate();
									if(StringUtil.isNotBlank(schStartDate) && currDate.compareTo(schStartDate)>=0){
										process.setIsCurrentFlag(GlobalConstant.FLAG_Y);
										resDoctorProcessBiz.edit(process);
									}
								}
							}
						}
					}
				}
			}

			return "res/doctor/sch";
		}
		//是否让学员填写轮转计划
		else if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_custom_result_flag"))){
			if(doctor!=null){
				Map<String, List<SchRotationDept>> deptMap = new HashMap<String, List<SchRotationDept>>();
				List<SchRotationGroup> groupList=new ArrayList<>();//轮转组合科室
				List<SchRotationDept> deptList=null;	//轮转科室
				//PPbear
				if(StringUtil.isNotBlank(doctor.getRotationFlow())){
					List<SchRotationGroup> groupList1=schRotationtGroupBiz.searchSchRotationGroup(doctor.getRotationFlow());
					deptList=schRotationDeptBiz.searchSchRotationDept(doctor.getRotationFlow());
					if(deptList!=null && !deptList.isEmpty() ){
						for(SchRotationDept d:deptList){
							String key = d.getGroupFlow();
							if(isReduction&&doctorDeptMap.size()>0)
							{
								String key2 = d.getGroupFlow()+d.getStandardDeptId();
								SchDoctorDept sdd=doctorDeptMap.get(key2);
								if(sdd!=null&&GlobalConstant.RECORD_STATUS_Y.equals(sdd.getRecordStatus()))
								{
									d.setSchMonth(sdd.getSchMonth());
								}else{
									continue;
								}
							}
							 List<SchRotationDept> tempDeptList = deptMap.get(key);
							 if(tempDeptList==null){
								 tempDeptList = new ArrayList<SchRotationDept>();
								 deptMap.put(key, tempDeptList);
							 }
							 tempDeptList.add(d);
						}
					}
					groupList.addAll(groupList1);
				}
				//第二专业
				if(StringUtil.isNotBlank(doctor.getSecondRotationFlow())){
					List<SchRotationGroup> groupList1=schRotationtGroupBiz.searchSchRotationGroup(doctor.getSecondRotationFlow());
					deptList=schRotationDeptBiz.searchSchRotationDept(doctor.getSecondRotationFlow());
					if(deptList!=null && !deptList.isEmpty() ){
						for(SchRotationDept d:deptList){
							String key = d.getGroupFlow();
							if(isReduction&&doctorDeptMap.size()>0)
							{
								String key2 = d.getGroupFlow()+d.getStandardDeptId();
								SchDoctorDept sdd=doctorDeptMap.get(key2);
								if(sdd!=null&&GlobalConstant.RECORD_STATUS_Y.equals(sdd.getRecordStatus()))
								{
									d.setSchMonth(sdd.getSchMonth());
								}else{
									continue;
								}
							}
							 List<SchRotationDept> tempDeptList = deptMap.get(key);
							 if(tempDeptList==null){
								 tempDeptList = new ArrayList<SchRotationDept>();
								 deptMap.put(key, tempDeptList);
							 }
							 tempDeptList.add(d);
						}
					}
					groupList.addAll(groupList1);
				}
				model.addAttribute("groupList", groupList);
				model.addAttribute("deptMap", deptMap);

				if(arrResultList!=null && !arrResultList.isEmpty()){
					Map<String, List<SchArrangeResult>> resultMap = new HashMap<String,List<SchArrangeResult>>();
					for(SchArrangeResult s:arrResultList){
						if(processMap!=null){
							String resultFlow = s.getResultFlow();
							ResDoctorSchProcess process = processMap.get(resultFlow);
							if(process!=null){
								String isCurrentFlag = process.getIsCurrentFlag();
								String schFlag = process.getSchFlag();
								if(!GlobalConstant.FLAG_Y.equals(isCurrentFlag) && !GlobalConstant.FLAG_Y.equals(schFlag)){
									String currDate = DateUtil.getCurrDate();
									String schStartDate = s.getSchStartDate();
									if(StringUtil.isNotBlank(schStartDate) && currDate.compareTo(schStartDate)>=0){
										process.setIsCurrentFlag(GlobalConstant.FLAG_Y);
										resDoctorProcessBiz.edit(process);
									}
								}
							}
						}

						String key = s.getStandardDeptId()+s.getStandardGroupFlow();
						List<SchArrangeResult> resultList= resultMap.get(key);
						if(resultList==null){
							resultList = new ArrayList<SchArrangeResult>();
							resultMap.put(key, resultList);
						}
						resultList.add(s);
					}
					model.addAttribute("resultMap", resultMap);
				}
			}
			return "res/doctor/schTwo";
		}else{
			return "res/doctor/sch";
		}
	}
	@RequestMapping(value="/rotationFinishDetail",method=RequestMethod.GET)
	public String rotationFinishDetail(Model model,String recordFlow,String doctorFlow,String recTypeId,String typeId){
		model.addAttribute("typeId",typeId);
		ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
		model.addAttribute("doctor", doctor);
		List<SchRotationDept> deptList=new ArrayList<>();
		SchRotationDept dept=schRotationDeptBiz.readSchRotationDept(recordFlow);
		model.addAttribute("dept", dept);
		if(dept!=null)
			deptList.add(dept);
		Map<String,String> finishPerMap = resRecBiz.getStandardDeptFinishPer(deptList,doctorFlow);
		model.addAttribute("finishPerMap", finishPerMap);
		if(StringUtil.isBlank(recTypeId)) {
			List<String> recTypeIds = new ArrayList<String>();
			if(JszyTCMPracticEnum.N.getId().equals(typeId)){
				for (RegistryTypeEnum regType : RegistryTypeEnum.values()) {
					if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + regType.getId()))) {
						recTypeIds.add(regType.getId());
					}
				}
			}else if(JszyTCMPracticEnum.BasicPractice.getId().equals(typeId)){
				for (PracticRegistryTypeEnum regType : PracticRegistryTypeEnum.values()) {
					if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_" + regType.getId()))) {
						recTypeIds.add(regType.getId());
					}
				}
			}else if(JszyTCMPracticEnum.TheoreticalStudy.getId().equals(typeId)){
				for (TheoreticalRegistryTypeEnum regType : TheoreticalRegistryTypeEnum.values()) {
					if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_" + regType.getId()))) {
						recTypeIds.add(regType.getId());
					}
				}
			}

			if (recTypeIds != null && recTypeIds.size() > 0) {
				recTypeId=recTypeIds.get(0);
			}
		}

		List<SchRotationDeptReq> deptReqList = schRotationDeptBiz.searchDeptReqByRel(recordFlow, recTypeId);
		model.addAttribute("deptReqList",deptReqList);
		model.addAttribute("recTypeId", recTypeId);
		return "res/doctor/standardDeptDetailView";
	}
	
	/**
	 * 删除一条result
	 * @param resultFlow
	 * @return
	 */
	@RequestMapping(value="/delResult")
	@ResponseBody
	public String delResult(String resultFlow){
		if(StringUtil.isNotBlank(resultFlow)){
			int result = schArrangeResultBiz.delResultByResultFlow(resultFlow);
			if(result!=GlobalConstant.ZERO_LINE){
				return GlobalConstant.DELETE_SUCCESSED;
			}else{
				return GlobalConstant.DELETE_FAIL;
			}
		}else{
			return GlobalConstant.DELETE_FAIL;
		}
	}

	/**
	 * 查看评价
	 * @param model
	 * @return
     */
	@RequestMapping(value={"/viewEvaluation/{roleFlag}"},method={RequestMethod.POST,RequestMethod.GET})
	public String viewEvaluation(@PathVariable String roleFlag,String schStartDate,String schEndDate,HttpServletRequest request,Integer currentPage,Model model){

        model.addAttribute("roleFlag",roleFlag);
        SysUser user = GlobalContext.getCurrentUser();

		PageHelper.startPage(currentPage, getPageSize(request));
		List<SchGradeFrom> results = arrangeBiz.searchGradeByDoctorFlow(null,null,null,user.getUserFlow(),schStartDate,schEndDate,null);
		if(results!=null && results.size()>0){
			model.addAttribute("results",results);

			Map<String,Map<String,Object>> teacherGradeMap = new HashMap<>();
			Map<String,Map<String,Object>> headGradeMap = new HashMap<>();
			for(SchGradeFrom gradeFrom:results){
				DeptTeacherGradeInfo teacherGradeInfo = resGradeBiz.readResGrade(gradeFrom.getIsTeacher());
				if(teacherGradeInfo != null){
					model.addAttribute("teacherGradeInfo",teacherGradeInfo);
					Map<String,Object> gradeMap = resRecBiz.parseGradeXml(teacherGradeInfo.getRecContent());
					teacherGradeMap.put(gradeFrom.getIsTeacher(),gradeMap);
					model.addAttribute("teacherGradeMap",teacherGradeMap);
				}
				DeptTeacherGradeInfo headGradeInfo = resGradeBiz.readResGrade(gradeFrom.getIsHead());
				if(headGradeInfo != null){
					model.addAttribute("headGradeInfo",headGradeInfo);
					Map<String,Object> headgradeMap = resRecBiz.parseGradeXml(headGradeInfo.getRecContent());
					headGradeMap.put(gradeFrom.getIsHead(),headgradeMap);
					model.addAttribute("headGradeMap",headGradeMap);
				}
			}

		}


		return "res/doctor/viewEvaluation";
	}

	/**
	 * 公告列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/noticeList",method=RequestMethod.GET)
	public String noticeList(Integer currentPage ,HttpServletRequest request, Model model,String isDoctor){
		if(currentPage==null){
			currentPage=1;
		}


		SysUser currentUser = GlobalContext.getCurrentUser();
		String orgFlow = currentUser.getOrgFlow();
		String sessionNumber="";
		if(StringUtil.isNotBlank(isDoctor)){
			ResDoctor doctor = doctorBiz.readDoctor(currentUser.getUserFlow());
			orgFlow = doctor.getOrgFlow();
			sessionNumber=doctor.getSessionNumber();
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<InxInfo> infos = this.noticeBiz.searchInfoByOrgBeforeDate(orgFlow,null, GlobalConstant.RES_NOTICE_TYPE_ID, GlobalConstant.RES_NOTICE_SYS_ID, GlobalContext.getCurrentUser().getUserFlow(),sessionNumber);
		model.addAttribute("infos",infos);
		return "/res/notice/noticeList";
	}
	@RequestMapping(value="/newNoticeList",method=RequestMethod.GET)
	public String newNoticeList(Integer currentPage ,HttpServletRequest request, Model model,String isDoctor){
		if(currentPage==null) {
			currentPage = 1;
		}

		SysUser currentUser = GlobalContext.getCurrentUser();
		String orgFlow = currentUser.getOrgFlow();
		String sessionNumber="";
		if(StringUtil.isNotBlank(isDoctor)){
			ResDoctor doctor = doctorBiz.readDoctor(currentUser.getUserFlow());
			orgFlow = doctor.getOrgFlow();
			sessionNumber=doctor.getSessionNumber();
		}
		String beforeSevenDay = DateUtil.addHour(DateUtil.getCurrDateTime(),-7*24).substring(0,8);
		List<InxInfo> infos = this.noticeBiz.searchInfoByOrgBeforeDate(orgFlow,beforeSevenDay, GlobalConstant.RES_NOTICE_TYPE_ID, GlobalConstant.RES_NOTICE_SYS_ID, GlobalContext.getCurrentUser().getUserFlow(),sessionNumber);
		model.addAttribute("infos",infos);
		return "/res/notice/newNoticeList";
	}
	@RequestMapping(value="/newNoticeList2",method=RequestMethod.GET)
	public String newNoticeList2(Integer currentPage ,HttpServletRequest request, Model model,String isDoctor){
		if(currentPage==null) {
			currentPage = 1;
		}
		SysUser currentUser = GlobalContext.getCurrentUser();
		String orgFlow = currentUser.getOrgFlow();
		String doctorRoleFlow=InitConfig.getSysCfg("res_doctor_role_flow");
		String sessionNumber = "";
		if(StringUtil.isNotBlank(doctorRoleFlow)) {
			List<SysUserRole> roles = userRoleBiz.getByUserFlow(currentUser.getUserFlow());
			if (roles != null && roles.size() > 0) {
				for (SysUserRole r : roles) {
					if(StringUtil.isNotBlank(r.getRoleFlow()) && r.getRoleFlow().equals(doctorRoleFlow))
					{
						ResDoctor doctor = doctorBiz.readDoctor(currentUser.getUserFlow());
						orgFlow = doctor.getOrgFlow();
						sessionNumber=doctor.getSessionNumber();
						break;
					}
				}
			}
		}
		List<InxInfo> infos = this.noticeBiz.searchInfoNotRead(orgFlow, GlobalConstant.RES_NOTICE_TYPE_ID, GlobalConstant.RES_NOTICE_SYS_ID, GlobalContext.getCurrentUser().getUserFlow(),sessionNumber);
		model.addAttribute("infos",infos);
		return "/res/notice/newNoticeList2";
	}
	@RequestMapping(value="/goBack",method=RequestMethod.GET)
	public String goBack(String paramUrl ,HttpServletRequest request, Model model){

		return "redirect:"+paramUrl;
	}
	
	/**
	 * 轮转计划详情
	 * @param resultFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/rotationDetail",method=RequestMethod.GET)
	public String rotationDetail(String resultFlow,String schDeptFlow,Model model){
		/**

		if(StringUtil.isNotBlank(resultFlow)){
			ResDoctorProcess process = this.resDoctorProcessBiz.searchByResultFlow(resultFlow);
			model.addAttribute("process", process);
		}

		//参加活动数量
		List<ResRec> campaignList = resRecBiz.searchByRec(ResRecTypeEnum.CampaignRegistry.getId(),schDeptFlow,GlobalContext.getCurrentUser().getUserFlow());
		model.addAttribute("campaignList", campaignList);

		//科室考评
		List<ResRec> deptGradeList = resRecBiz.searchByRec(ResRecTypeEnum.DeptGrade.getId(),schDeptFlow,GlobalContext.getCurrentUser().getUserFlow());
		if(deptGradeList!=null && deptGradeList.size()>0){
			Map<String,Object> gradeMap = resRecBiz.parseGradeXml(deptGradeList.get(0).getRecContent());
			model.addAttribute("deptGrade", deptGradeList.get(0));
			model.addAttribute("deptGradeMap",gradeMap);
		}

		//带教老师考评
		List<ResRec> teacherGradeList = resRecBiz.searchByRec(ResRecTypeEnum.TeacherGrade.getId(),schDeptFlow,GlobalContext.getCurrentUser().getUserFlow());
		if(teacherGradeList!=null && teacherGradeList.size()>0){
			Map<String,Object> gradeMap = resRecBiz.parseGradeXml(teacherGradeList.get(0).getRecContent());
			model.addAttribute("teacherGrade", teacherGradeList.get(0));
			model.addAttribute("teacherGradeMap", gradeMap);
		}
		//return "res/doctor/main";

		*/
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();


		return goDetailView(resultFlow,schDeptFlow,userFlow,model);
	}
	
	@RequestMapping(value="/goDetailView",method=RequestMethod.GET)
	public String goDetailView(String resultFlow,String schDeptFlow,String userFlow,Model model){
//		List<String> doctorFlows = new ArrayList<String>();
//		doctorFlows.add(userFlow);
//		List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchArrangeResultByDoctorFlows(doctorFlows);
		List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByDoctor(userFlow);
		//存放resultFlow的集合用于查询arrResultList对应标准SchRotationDept
		//考虑到放入循环中用schRotationDeptBiz的readStandardRotationDept方法会多次查询数据库会降低性能
		//故新建一个results查询时用andResultFlowIn这个方法减少数据库查询次数
		List<String> resultFlows = new ArrayList<>();
		if(arrResultList!=null&&!arrResultList.isEmpty()){
			List<String> schResultFlows = new ArrayList<String>();
			for (SchArrangeResult result : arrResultList) {
				schResultFlows.add(result.getResultFlow());
			}
			model.addAttribute("arrResultList", arrResultList);

			List<ResDoctorSchProcess> processList = resDoctorProcessBiz.searchByResultFlows(schResultFlows);
			if(processList!=null && processList.size()>0){
				Map<String,ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
				for(ResDoctorSchProcess process : processList){
					processMap.put(process.getSchResultFlow(),process);
				}
				model.addAttribute("processMap", processMap);
			}
			//要求数
			if(StringUtil.isBlank(userFlow)){
				userFlow = GlobalContext.getCurrentUser().getUserFlow();
			}
			ResDoctor doctor = resDoctorBiz.readDoctor(userFlow);
			Map<String,BigDecimal> reqNumMap = new HashMap<String,BigDecimal>();
			for (SchArrangeResult sar:arrResultList) {
				resultFlows.add(sar.getResultFlow());
				List<SchRotationDeptReq> deptReqList = schRotationDeptBiz.searchStandardReqByResult(sar,doctor);
				if(deptReqList!=null && deptReqList.size()>0){
					for(SchRotationDeptReq req : deptReqList){
						String key = sar.getResultFlow()+req.getRecTypeId()+"req";
						if(reqNumMap.get(key)==null){
							reqNumMap.put(key,req.getReqNum());
						}else{
							reqNumMap.put(key,reqNumMap.get(key).add(req.getReqNum()));
						}
					}
				}
			}
			model.addAttribute("reqNumMap",reqNumMap);

			Map<String,String> finishPerMap = resRecBiz.getFinishPer(arrResultList);
			model.addAttribute("finishPerMap", finishPerMap);
		}

		//将对应的processFlow是否对应中医全科的理论学习放入map中前台页面展示用
		Map<String,String> resultIfTheoreticalStudyMap = new HashMap<>();
		//resultIfTheoreticalStudyMaps的数据格式key:practicOrTheory,resultFlow
		List<Map<String,String>> resultIfTheoreticalStudyMaps = schRotationDeptBiz.readStandardRotationDept(resultFlows);
		if (resultIfTheoreticalStudyMaps != null) {
			for(Map<String,String> tempMap : resultIfTheoreticalStudyMaps){
				resultIfTheoreticalStudyMap.put(tempMap.get("resultFlow"),tempMap.get("practicOrTheory"));
			}
			model.addAttribute("resultIfTheoreticalStudyMap",resultIfTheoreticalStudyMap);
		}

		return "res/doctor/process";
	}

	/**
	 * 显示登记信息界面
	 *
	 * @return
	 */
	@RequestMapping(value = "/registryView", method = RequestMethod.GET)
	public String registryView(String resultFlow, String schDeptFlow, Model model) {

		return "res/doctor/registryView";
	}
	
	/**
	 * 选择带教老师或科主任
	 * @param resultFlow 轮转计划流水号
	 * @return
	 
	@RequestMapping(value="/chooseTeacherOrHead",method=RequestMethod.GET)
	public String chooseTeacherOrHead(String resultFlow,String roleFlow, Model model ){
		List<SysUser> userList = this.resDoctorBiz.searchTeacherOrHead(resultFlow, roleFlow);
		model.addAttribute("userList", userList);
		return "res/doctor/chooseTeacherOrHead";
	}*/

	//查询学员是否按顺序入科
	@RequestMapping("/checkResult")
	@ResponseBody
	public Object checkResult(String resultFlow){
		SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(resultFlow);
		String startDate = result.getSchStartDate();
		String doctorFlow = result.getDoctorFlow();
		List<SchArrangeResult> resultList = schArrangeResultBiz.searchArrangeResultByDate(startDate,doctorFlow);
		ResDoctorSchProcess searchProcess = new ResDoctorSchProcess();
		searchProcess.setSchFlag("Y");
		searchProcess.setUserFlow(doctorFlow);
		searchProcess.setSchStartDate(startDate);
		List<ResDoctorSchProcess> processList = resDoctorProcessBiz.searchProcessByDoctor(searchProcess);
		if(resultList==null&&processList==null){
			return true;
		}
		if(resultList!=null&&processList!=null&&resultList.size()==processList.size()){
			return true;
		}
		return false;
	}

	/**
	 * 显示选择带教老师和科主任界面
	 *
	 * @return
	 */
	@RequestMapping(value = "/showChoose", method = RequestMethod.GET)
	public String showChoose(String schDeptFlow, Model model) {
		if (StringUtil.isNotBlank(schDeptFlow)) {
			SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
			if (dept != null) {
				String deptFlow = dept.getDeptFlow();
				if (GlobalConstant.FLAG_Y.equals(dept.getIsExternal())) {
					SchDeptExternalRel deptExtRel = deptExtRelBiz.readSchDeptExtRelBySchDept(schDeptFlow);
					if (deptExtRel != null) {
						deptFlow = deptExtRel.getRelDeptFlow();
					}
				}

				if (StringUtil.isNotBlank(deptFlow)) {
					String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
					if (StringUtil.isNotBlank(teacherRoleFlow)) {
						List<SysUser> teacherList = userBiz.searchUserByDeptAndRole(deptFlow, teacherRoleFlow);
						model.addAttribute("teacherList", teacherList);
					}

					String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
					if (StringUtil.isNotBlank(headRoleFlow)) {
						List<SysUser> headList = userBiz.searchUserByDeptAndRole(deptFlow, headRoleFlow);
						model.addAttribute("headList", headList);
					}
				}
			}
		}
		return "res/doctor/showChoose";
	}

	@RequestMapping(value="/getUserType")
	@ResponseBody
	public Object getUserType(String userFlow){
		if(StringUtil.isNotBlank(userFlow)){
			SysUser user = userBiz.readSysUser(userFlow);
			return user;
		}
		return null;
	}

	/**
	 * 保存带教老师或科主任
	 * @param process
	 * @param resultFlow
	 * @param preResultFlow
	 * @return
	 */
	@RequestMapping(value="/saveChoose",method=RequestMethod.POST)
	@ResponseBody
	public String saveChoose(ResDoctorSchProcess process,String resultFlow,String preResultFlow){
		this.resDoctorBiz.saveChoose(process, resultFlow, preResultFlow);
		if(StringUtil.isNotBlank(resultFlow)){
			SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(resultFlow);
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}

	/**
	 * 显示修改轮转时间界面
	 * @param processFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showModifySchDate",method=RequestMethod.GET)
	public String showModifySchDate(String processFlow,Model model){
		ResDoctorSchProcess process = this.resDoctorProcessBiz.read(processFlow);
		model.addAttribute("process", process);
		return "res/doctor/modSchDate";
	}

	/**
	 * 显示修改带教老师和科主任界面
	 * @param processFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/changeDeptAndTeacher",method=RequestMethod.GET)
	public String changeDeptAndTeacher(String processFlow,Model model){
		ResDoctorSchProcess process = this.resDoctorProcessBiz.read(processFlow);
		if(process!=null){
			model.addAttribute("process", process);

			String schDeptFlow = process.getSchDeptFlow();
			if(StringUtil.isNotBlank(schDeptFlow)){
				SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
				if(dept!=null){
					String deptFlow = dept.getDeptFlow();
					if(GlobalConstant.FLAG_Y.equals(dept.getIsExternal())){
						SchDeptExternalRel deptExtRel = deptExtRelBiz.readSchDeptExtRelBySchDept(schDeptFlow);
						if(deptExtRel!=null){
							deptFlow = deptExtRel.getRelDeptFlow();
						}
					}

					String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
					String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");

					List<SysUser> teacherList = userBiz.searchUserByDeptAndRole(deptFlow,teacherRoleFlow);
					model.addAttribute("teacherList",teacherList);

					List<SysUser> headList = userBiz.searchUserByDeptAndRole(deptFlow,headRoleFlow);
					model.addAttribute("headList",headList);
				}
			}
		}
		return "res/doctor/changeDeptAndTeacher";
	}
	
	/**
	 * 修改轮转时间
	 * @param process
	 * @return
	 */
	@RequestMapping(value="/modifySchDate",method=RequestMethod.POST)
	@ResponseBody
	public String modifySchDate(ResDoctorSchProcess process) throws ParseException {
		ResDoctorSchProcess process2=resDoctorProcessBiz.read(process.getProcessFlow());
		if(!GlobalConstant.FLAG_Y.equals(process.getIsExternal())) {
			process.setEndDate(process.getSchEndDate());
			process.setStartDate(process.getSchStartDate());
			if (process2 != null) {
				SchArrangeResult sar = schArrangeResultBiz.readSchArrangeResult(process2.getSchResultFlow());
				if (sar != null) {
					sar.setSchStartDate(process.getSchStartDate());
					sar.setSchEndDate(process.getSchEndDate());

					//轮转计划单位
					String unit = InitConfig.getSysCfg("res_rotation_unit");
					//默认按月计算
					int step = 30;
					if (SchUnitEnum.Week.getId().equals(unit)) {
						//如果是周按7天算/没配置或者选择月按30天
						step = 7;
						BigDecimal realMonth = BigDecimal.valueOf(0);
						long realDays = DateUtil.signDaysBetweenTowDate(sar.getSchEndDate(), sar.getSchStartDate())+1;
						if (realDays != 0) {
							//计算实际轮转的月/周数
							double realMonthF = (realDays / (step * 1.0));
							realMonth = BigDecimal.valueOf(realMonthF);
							realMonth = realMonth.setScale(1, BigDecimal.ROUND_HALF_UP);
						}
						String schMonth = String.valueOf(realMonth.doubleValue());
						sar.setSchMonth(schMonth);
					}else{
						Map<String,String> map= new HashMap<>();
						map.put("startDate",sar.getSchStartDate());
						map.put("endDate",sar.getSchEndDate());
						Double month = TimeUtil.getMonthsBetween(map);
						String schMonth = String.valueOf(Double.parseDouble(month + ""));
						sar.setSchMonth(schMonth);
					}
					schArrangeResultBiz.update(sar);
				}
			}
		}
		int result = this.resDoctorProcessBiz.edit(process);
		if(result==GlobalConstant.ONE_LINE){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}
	
	/**
	 * 登记手册
	 */
	@RequestMapping(value="/registryNoteBook",method=RequestMethod.GET)
	public String registryNoteBook(String schDeptFlow,String resultFlow,Model model){
		SysUser user = GlobalContext.getCurrentUser();
		model.addAttribute("user",user);

		String userFlow = user.getUserFlow();

		ResDoctor doctor = resDoctorBiz.readDoctor(userFlow);
		model.addAttribute("doctor",doctor);

		if(StringUtil.isNotBlank(schDeptFlow)){
			SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
			model.addAttribute("dept",dept);
		}

		List<String> doctorFlows = new ArrayList<String>();
		doctorFlows.add(userFlow);
		List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchArrangeResultByDoctorFlows(doctorFlows);
		if(arrResultList!=null&&!arrResultList.isEmpty()){
			List<String> schResultFlows = new ArrayList<String>();
			for (SchArrangeResult result : arrResultList) {
				schResultFlows.add(result.getResultFlow());
			}
			model.addAttribute("arrResultList", arrResultList);

			List<ResDoctorSchProcess> processList = resDoctorProcessBiz.searchByResultFlows(schResultFlows);
			if(processList!=null && processList.size()>0){
				Map<String,ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
				for(ResDoctorSchProcess process : processList){
					processMap.put(process.getSchResultFlow(),process);
				}
				model.addAttribute("processMap", processMap);
			}

			Map<String,String> finishPerMap = resRecBiz.getFinishPer(arrResultList);
			model.addAttribute("finishPerMap", finishPerMap);
		}

		SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(resultFlow);
		List<SchRotationDeptReq> deptReqList = rotationDeptBiz.searchStandardReqByResult(result,doctor);
		Map<String,Integer> countMap = new HashMap<String, Integer>();
		if(deptReqList!=null && deptReqList.size()>0){
			Map<String,List<SchRotationDeptReq>> reqMap = new HashMap<String, List<SchRotationDeptReq>>();
			for(SchRotationDeptReq req : deptReqList){
				String key = req.getRecTypeId()+req.getItemId()+"req";
				Integer num = req.getReqNum().intValue();
				if(countMap.get(key)==null){
					countMap.put(key,num);
				}else{
					countMap.put(key,countMap.get(key)+num);
				}

				if(reqMap.get(req.getRecTypeId())==null){
					List<SchRotationDeptReq> reqList = new ArrayList<SchRotationDeptReq>();
					reqList.add(req);
					reqMap.put(req.getRecTypeId(),reqList);
				}else{
					reqMap.get(req.getRecTypeId()).add(req);
				}
			}
			model.addAttribute("reqMap",reqMap);
		}

		List<String> recTypeIds = new ArrayList<String>();
		for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))){
				recTypeIds.add(regType.getId());
			}
		}
		recTypeIds.add(ResRecTypeEnum.AfterSummary.getId());
		List<ResRec> recList = resRecBiz.searchByRecWithBLOBs(recTypeIds, schDeptFlow, userFlow);

		if(recList!=null && recList.size()>0){
			model.addAttribute("recList",recList);

			Map<String,List<ResRec>> recMap = new HashMap<String, List<ResRec>>();
			Map<String,Map<String,Object>> recContentMap = new HashMap<String, Map<String,Object>>();
			for(ResRec rec : recList){
				String key = rec.getRecTypeId()+rec.getItemId();

				if(recMap.get(rec.getRecTypeId())==null){
					List<ResRec> recs = new ArrayList<ResRec>();
					recs.add(rec);
					recMap.put(rec.getRecTypeId(),recs);
				}else{
					recMap.get(rec.getRecTypeId()).add(rec);
				}

				Map<String,Object> content = resRecBiz.parseRecContent(rec.getRecContent());
				recContentMap.put(rec.getRecFlow(),content);

				if(RecStatusEnum.TeacherAuditY.getId().equals(rec.getAuditStatusId())){
					if(countMap.get(key+"audit")==null){
						countMap.put(key+"audit",1);
					}else{
						countMap.put(key+"audit",countMap.get(key+"audit")+1);
					}
				}

				if(countMap.get(key+"finish")==null){
					countMap.put(key+"finish",1);
				}else{
					countMap.put(key+"finish",countMap.get(key+"finish")+1);
				}
			}
			////
			model.addAttribute("recMap",recMap);
			model.addAttribute("recContentMap",recContentMap);
		}

		model.addAttribute("countMap",countMap);

		return "res/doctor/registryNoteBook";
	}
	
	/**
	 * 医师选科
	 */
	@RequestMapping(value="/selDept",method=RequestMethod.GET)
	public String selDept(Model model){
		String doctorFlow = GlobalContext.getCurrentUser().getUserFlow();

		if(StringUtil.isNotBlank(doctorFlow)){
			List<SchArrangeResult> resultList = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctorFlow);
			model.addAttribute("resultList",resultList);

			ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
			String rotationFlow = "";
			if(doctor!=null){
				model.addAttribute("doctor",doctor);
				rotationFlow = doctor.getRotationFlow();
			}
			if(StringUtil.isNotBlank(rotationFlow)){
				//科室组
				List<SchRotationGroup> rotationGroupList = schRotationtGroupBiz.searchOrgGroupOrAll(rotationFlow,doctor.getOrgFlow(),GlobalConstant.FLAG_N);
				model.addAttribute("rotationGroupList",rotationGroupList);

				//组合科室
				List<SchRotationDept> rotationDeptList = schRotationDeptBiz.searchOrgSchRotationDeptGroup(rotationFlow,doctor.getOrgFlow());
				if(rotationDeptList != null && rotationDeptList.size()>0){
					Map<String,List<SchRotationDept>> rotationDeptMap = new HashMap<String,List<SchRotationDept>>();
					for(SchRotationDept rotationDept : rotationDeptList){
						if(rotationDeptMap.get(rotationDept.getGroupFlow()) == null){
							List<SchRotationDept> rotationDeptTempList = new ArrayList<SchRotationDept>();
							rotationDeptTempList.add(rotationDept);
							rotationDeptMap.put(rotationDept.getGroupFlow(),rotationDeptTempList);
						}else{
							rotationDeptMap.get(rotationDept.getGroupFlow()).add(rotationDept);
						}
					}
					model.addAttribute("rotationDeptMap",rotationDeptMap);
				}
			}
			if(StringUtil.isNotBlank(doctorFlow)){
				//已选科室
				List<SchDoctorDept> doctorDeptList = schDoctorDeptBiz.searchSchDoctorDept(doctorFlow);
				if(doctorDeptList != null && doctorDeptList.size()>0){
					Map<String,SchDoctorDept> doctorDeptMap = new HashMap<String,SchDoctorDept>();
					for(SchDoctorDept doctorDept : doctorDeptList){
						doctorDeptMap.put(doctorDept.getGroupFlow()+doctorDept.getSchDeptFlow(),doctorDept);
					}
					model.addAttribute("doctorDeptMap",doctorDeptMap);
				}
			}
		}
		return "res/doctor/arrange/seldeptItem";
	}
	
	/**
	 * 个人手动排班
	 */
	@RequestMapping(value="/rosteringHandDept",method=RequestMethod.GET)
	public String rosteringHandDept(Model model){
		String doctorFlow = GlobalContext.getCurrentUser().getUserFlow();

		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
			model.addAttribute("doctor",doctor);

			SchRotation rotation = schRotationtBiz.readSchRotation(doctor.getRotationFlow());
			model.addAttribute("rotationYear",rotation.getRotationYear());

			List<SchRotationDept> rotationDeptList = schRotationDeptBiz.searchOrgSchRotationDeptMust(doctor.getRotationFlow(),doctor.getOrgFlow());
			model.addAttribute("rotationDeptList",rotationDeptList);

			if(GlobalConstant.FLAG_Y.equals(doctor.getSelDeptFlag())){
				List<SchRotationDept> rotationDeptTempList = schRotationDeptBiz.searchOrgSchRotationDeptGroup(doctor.getRotationFlow(),doctor.getOrgFlow());
				if(rotationDeptTempList != null && rotationDeptTempList.size()>0){
					Map<String,SchRotationDept> rotationDeptMap = new HashMap<String,SchRotationDept>();
					for(SchRotationDept rotationDept : rotationDeptTempList){
						rotationDeptMap.put(rotationDept.getGroupFlow()+rotationDept.getSchDeptFlow(),rotationDept);
					}
					model.addAttribute("rotationDeptMap",rotationDeptMap);
				}

				List<SchRotationGroup> rotationGroupList = schRotationtGroupBiz.searchOrgGroupOrAll(doctor.getRotationFlow(),doctor.getOrgFlow(),GlobalConstant.FLAG_N);
				if(rotationGroupList != null && rotationGroupList.size()>0){
					Map<String,SchRotationGroup> rotationGroupMap = new HashMap<String,SchRotationGroup>();
					for(SchRotationGroup rotationGroup : rotationGroupList){
						rotationGroupMap.put(rotationGroup.getGroupFlow(),rotationGroup);
					}
					model.addAttribute("rotationGroupMap",rotationGroupMap);
				}

				List<SchDoctorDept> doctorDeptList = schDoctorDeptBiz.searchSchDoctorDept(doctorFlow);
				model.addAttribute("doctorDeptList",doctorDeptList);

				List<SchArrangeResult> resultList = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctorFlow);
				if(resultList != null && resultList.size()>0){
					Map<String,SchArrangeResult> resultMap = new HashMap<String,SchArrangeResult>();
					for(SchArrangeResult arrResult : resultList){
						resultMap.put(arrResult.getSchDeptFlow()+arrResult.getSchYear(),arrResult);
					}
					model.addAttribute("resultMap",resultMap);
				}
			}
		}
		return "res/doctor/arrange/rosteringHand";
	}
	
	/**
	 *  选科+排班
	 */
	@RequestMapping(value="/selDeptAndRosteringHand",method=RequestMethod.GET)
	public String selDeptAndRosteringHand(Model model){
		//医师流水号
		String doctorFlow = GlobalContext.getCurrentUser().getUserFlow();
		//String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();

		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
			if(doctor!=null){
				model.addAttribute("doctor",doctor);

				//Integer sessionNumber = Integer.parseInt(doctor.getSessionNumber());
				String rotationFlow = doctor.getRotationFlow();
				String orgFlow = doctor.getOrgFlow();

				if(StringUtil.isNotBlank(rotationFlow)){
					SchRotation rotation = schRotationtBiz.readSchRotation(rotationFlow);
					model.addAttribute("rotation",rotation);
				}

				if(StringUtil.isNotBlank(orgFlow) && StringUtil.isNotBlank(rotationFlow)){
					int unRelCount = schRotationDeptBiz.getUnrelCount(orgFlow,rotationFlow);
					model.addAttribute("relFlag",unRelCount>0 && StringUtil.isNotBlank(rotationFlow));
				}

				List<SchRotationGroup> groupList = null;
				if(StringUtil.isNotBlank(orgFlow) && StringUtil.isNotBlank(rotationFlow)){
					groupList = schRotationtGroupBiz.searchOrgGroupOrAll(rotationFlow,orgFlow,null);
				}
				if(groupList!=null && groupList.size()>0){
					Map<String,SchRotationGroup> stageGroupMap = new HashMap<String, SchRotationGroup>();
					for(SchRotationGroup group : groupList){
						stageGroupMap.put(group.getGroupFlow(),group);
					}
					model.addAttribute("stageGroupMap",stageGroupMap);
				}

				List<SchArrangeResult> resultList = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctorFlow);
				if(GlobalConstant.FLAG_Y.equals(doctor.getSelDeptFlag()) && (resultList==null || resultList.size()<1)){
					createRosting();
					resultList = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctorFlow);
				}

				if(!StringUtil.isNotBlank(doctor.getSchStatusId())){
					List<SysDept> sysDeptList = null;
					if (StringUtil.isNotBlank(doctor.getOrgFlow())) {
						sysDeptList = deptBiz.searchDeptByOrg(doctor.getOrgFlow());
					}
					if(sysDeptList!=null && sysDeptList.size()>0){
						List<String> deptFlows = new ArrayList<String>();
						for(SysDept dept : sysDeptList){
							deptFlows.add(dept.getDeptFlow());
						}
						List<SchDept> deptList = schDeptBiz.searchDeptByDeotFlows(deptFlows);
						model.addAttribute("schDeptList",deptList);
					}
				}

				if(resultList!=null && resultList.size()>0){
					model.addAttribute("resultList",resultList);

					Map<String,SchArrangeResult> resultMap = new HashMap<String, SchArrangeResult>();
					for(SchArrangeResult result : resultList){
						if(!StringUtil.isNotBlank(result.getStandardDeptId())){
							resultMap.put(result.getSchDeptFlow(),result);
						}
					}
					model.addAttribute("resultMap",resultMap);

					if(GlobalConstant.FLAG_Y.equals(doctor.getSchFlag())){
//						if(resultList!=null&&!resultList.isEmpty()){
//							List<String> schResultFlows = new ArrayList<String>();
//							for (SchArrangeResult result : resultList) {
//								schResultFlows.add(result.getResultFlow());
//							}
//
//							List<ResDoctorSchProcess> processList = resDoctorProcessBiz.searchByResultFlows(schResultFlows);
//							if(processList!=null && processList.size()>0){
//								Map<String,ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
//								Map<String,String> recContentMap = new HashMap<String, String>();
//								for(ResDoctorSchProcess process : processList){
//									processMap.put(process.getSchResultFlow(),process);
////									if(GlobalConstant.FLAG_Y.equals(process.getIsCurrentFlag())){
////										List<ResRec> sRecList = resRecBiz.searchByRecWithBLOBs(ResRecTypeEnum.AfterSummary.getId(),process.getSchDeptFlow(),process.getUserFlow());
////										if(sRecList!=null && sRecList.size()>0){
////											ResRec rec = sRecList.get(0);
////											recContentMap = resRecBiz.parseRecContent(rec.getRecContent());
////											recContentMap.put(process.getSchResultFlow(),recContentMap.get("isAgaree"));
////										}
////									}
////									recContentMap.put(process.getSchResultFlow(),process.getSchFlag());
//								}
//								model.addAttribute("processMap", processMap);
//								model.addAttribute("recContentMap", recContentMap);
//							}
//
//							Map<String,String> finishPerMap = resRecBiz.getFinishPer(resultList);
//							model.addAttribute("finishPerMap", finishPerMap);
//						}

						//通知
//						InxInfo info = new InxInfo();
//						List<InxInfo> infos = this.noticeBiz.searchSevenDaysNotice(info);
//						model.addAttribute("infos",infos);
						return "redirect:/res/doc/searchRotationList";
					}else{
						if(resultList!=null&&!resultList.isEmpty()){
							List<String> schResultFlows = new ArrayList<String>();
							for (SchArrangeResult result : resultList) {
								schResultFlows.add(result.getResultFlow());
							}

							List<ResDoctorSchProcess> processList = resDoctorProcessBiz.searchByResultFlows(schResultFlows);
							if(processList!=null && processList.size()>0){
								Map<String,ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
								for(ResDoctorSchProcess process : processList){
									processMap.put(process.getSchResultFlow(),process);
								}
								model.addAttribute("processMap", processMap);
							}
						}

						//科室组
						if(StringUtil.isNotBlank(rotationFlow)){
							List<SchRotationGroup> rotationGroupList = schRotationtGroupBiz.searchOrgGroupOrAll(rotationFlow,doctor.getOrgFlow(),GlobalConstant.FLAG_N);
							if(rotationGroupList!=null && rotationGroupList.size()>0){
								model.addAttribute("rotationGroupList",rotationGroupList);
								Map<String,SchRotationGroup> groupMap = new HashMap<String, SchRotationGroup>();
								for(SchRotationGroup group : rotationGroupList){
									groupMap.put(group.getGroupFlow(),group);
								}
								model.addAttribute("groupMap",groupMap);
							}
						}
					}
				}else{
					if(StringUtil.isNotBlank(rotationFlow)){
						//科室组
						List<SchRotationGroup> rotationGroupList = schRotationtGroupBiz.searchOrgGroupOrAll(rotationFlow,doctor.getOrgFlow(),GlobalConstant.FLAG_N);
						model.addAttribute("rotationGroupList",rotationGroupList);

						//组合科室
						List<SchRotationDept> rotationDeptList = schRotationDeptBiz.searchOrgSchRotationDeptGroup(rotationFlow,doctor.getOrgFlow());
						if(rotationDeptList != null && rotationDeptList.size()>0){
							Map<String,List<SchRotationDept>> rotationDeptMap = new HashMap<String,List<SchRotationDept>>();
							for(SchRotationDept rotationDept : rotationDeptList){
								if(rotationDeptMap.get(rotationDept.getGroupFlow()) == null){
									List<SchRotationDept> rotationDeptTempList = new ArrayList<SchRotationDept>();
									rotationDeptTempList.add(rotationDept);
									rotationDeptMap.put(rotationDept.getGroupFlow(),rotationDeptTempList);
								}else{
									rotationDeptMap.get(rotationDept.getGroupFlow()).add(rotationDept);
								}
							}
							model.addAttribute("rotationDeptMap",rotationDeptMap);
						}

						//已选科室
						List<SchDoctorDept> doctorDeptList = schDoctorDeptBiz.searchSchDoctorDept(doctorFlow);
						if(doctorDeptList != null && doctorDeptList.size()>0){
							Map<String,SchDoctorDept> doctorDeptMap = new HashMap<String,SchDoctorDept>();
							for(SchDoctorDept doctorDept : doctorDeptList){
								doctorDeptMap.put(doctorDept.getGroupFlow()+doctorDept.getSchDeptFlow(),doctorDept);
							}
							model.addAttribute("doctorDeptMap",doctorDeptMap);
						}
					}
				}
			}
		}

		//取前10年和后10年的每月最后一天
		List<String> lastDays = new ArrayList<String>();
		String currDate = DateUtil.getCurrDate();
		if(StringUtil.isNotBlank(currDate) && currDate.length()>5){
			String currYear = currDate.substring(0, 4);
			Integer currYearI = Integer.parseInt(currYear);
			Integer startYear = currYearI-10;
			Integer endYear = currYearI+10;
			while(startYear<=endYear){
				for(int m = 1 ; m<=12 ; m++){
					String ld = DateUtil.newDateOfAddMonths(startYear+"-"+m+"-"+"01",1);
					ld = DateUtil.addDate(ld,-1);
					lastDays.add(ld);
				}
				startYear++;
			}
		}
		model.addAttribute("lastDays",lastDays);

		return "res/doctor/arrange/selDeptAndRosteringHand";
	}
	
	/**
	 *  保存选科
	 */
	@RequestMapping(value="/saveSelDept",method=RequestMethod.POST)
	@ResponseBody
	public String saveSelDept(String[] recordFlows,String[] schMonths){
		String doctorFlow = GlobalContext.getCurrentUser().getUserFlow();
		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);

			List<String> recordFlowList = null;
			Map<String,String> schMonthMap = null;
			if(recordFlows!=null && recordFlows.length>0){
				schMonthMap = new HashMap<String, String>();
				recordFlowList = new ArrayList<String>();

				for(int i = 0 ; i<recordFlows.length ; i++){
					String recordFlow = recordFlows[i];
					recordFlowList.add(recordFlow);
					schMonthMap.put(recordFlow,schMonths[i]);
				}
			}
			int result = schRotationDeptBiz.saveSelDeptsAndResult(recordFlowList,schMonthMap,doctor);
			if(result!=GlobalConstant.ZERO_LINE){
				return GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}
	
	/**
	 *  保存排班
	 */
	@RequestMapping(value="/createRosting",method=RequestMethod.GET)
	@ResponseBody
	public String createRosting(){
		String doctorFlow = GlobalContext.getCurrentUser().getUserFlow();
		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
//			if(doctor!=null){
//				doctor.setSchFlag(GlobalConstant.FLAG_Y);
//			}
			int result = schArrangeResultBiz.saveResultByDoctor(doctor);
			if(result!=GlobalConstant.ZERO_LINE){
				return GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}
	
	/**
	 *  排序+计算方案
	 */
	@RequestMapping(value="/sortAndCalculate",method=RequestMethod.POST)
	@ResponseBody
	public String sortAndCalculate(String[] resultFlow,String startDate,boolean clear,Integer resultNum,boolean addOneDay,String mustDate){
		boolean ismust = GlobalConstant.FLAG_Y.equals(mustDate);

		if(resultFlow!=null && resultFlow.length>0){
			List<SchArrangeResult> resultList = new ArrayList<SchArrangeResult>();
			List<String> resultFlows = new ArrayList<String>();
			int seq = resultNum-(resultFlow.length);
			for(String resultFlowTemp : resultFlow){
				resultFlows.add(resultFlowTemp);
				SchArrangeResult result = new SchArrangeResult();
				result.setResultFlow(resultFlowTemp);
				result.setSchDeptOrder(BigDecimal.valueOf(seq++));
				resultList.add(result);
				if(clear){
					result.setSchStartDate("");
					result.setSchEndDate("");
				}
			}
			if(StringUtil.isNotBlank(startDate)){
				if(addOneDay){
					startDate = DateUtil.addDate(startDate,1);
				}
				List<SchArrangeResult> resultListTemp = schArrangeResultBiz.searchArrangeResultByResultFlow(resultFlows);
				Map<String,String> schMonthMap = new HashMap<String, String>();
				for(SchArrangeResult monthResult : resultListTemp){
					schMonthMap.put(monthResult.getResultFlow(),monthResult.getSchMonth());
				}
				resultList = calculatePlan(resultList,startDate,schMonthMap,ismust);
			}
			int result = schArrangeResultBiz.saveSchArrangeResults(resultList);
			if(result!=GlobalConstant.ZERO_LINE){
				return GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}

	/**
	 *  排序+计算方案 方案二
	 */
	@RequestMapping(value = "/sortAndCalculateTwo", method = RequestMethod.POST)
	@ResponseBody
	public String sortAndCalculateTwo(String[] resultFlow, String startDate, boolean clear, Integer resultNum, boolean addOneDay, String mustDate) {
		boolean ismust = GlobalConstant.FLAG_Y.equals(mustDate);

		if (resultFlow != null && resultFlow.length > 0) {
			String st = "";
			List<String> strlist = new ArrayList();
			for (String strings : resultFlow) {
				strlist.add(strings);
			}
			for (String strs : strlist) {
				if (!strs.equals("")) {
					st += strs + ",";
				}
			}
			resultFlow = st.split("\\,");

			List<SchArrangeResult> resultList = new ArrayList<SchArrangeResult>();
			List<String> resultFlows = new ArrayList<String>();
			int seq = resultNum - (resultFlow.length);
			for (String resultFlowTemp : resultFlow) {
				resultFlows.add(resultFlowTemp);
				SchArrangeResult result = new SchArrangeResult();
				result.setResultFlow(resultFlowTemp);
				result.setSchDeptOrder(BigDecimal.valueOf(seq++));
				resultList.add(result);
				if (clear) {
					result.setSchStartDate("");
					result.setSchEndDate("");
				}
			}
			if(clear){
				int result = schArrangeResultBiz.saveSchArrangeResults(resultList);
				if (result != GlobalConstant.ZERO_LINE) {
					schArrangeResultBiz.synchronizeProcessByResults(resultList);
					return GlobalConstant.OPRE_SUCCESSED_FLAG;
				}
			}
			if (StringUtil.isNotBlank(startDate)) {
				if (addOneDay) {
					startDate = DateUtil.addDate(startDate, 1);
				}
				List<SchArrangeResult> resultListTemp = schArrangeResultBiz.searchArrangeResultByResultFlow(resultFlows);
				Map<String, String> schMonthMap = new HashMap<String, String>();
				for (SchArrangeResult monthResult : resultListTemp) {
					schMonthMap.put(monthResult.getResultFlow(), monthResult.getSchMonth());
				}
				resultList = calculatePlanTwo(resultList, startDate, schMonthMap, ismust);
			}else {
				List<SchArrangeResult> resultListTemp = schArrangeResultBiz.searchArrangeResultByResultFlow(resultFlows);
				if(!clear){
					startDate = resultListTemp.get(0).getSchStartDate();
				}
				Map<String, String> schMonthMap = new HashMap<String, String>();
				for (SchArrangeResult monthResult : resultListTemp) {
					schMonthMap.put(monthResult.getResultFlow(), monthResult.getSchMonth());
				}
				resultList = calculatePlanTwo(resultList, startDate, schMonthMap, ismust);
			}
			int result = schArrangeResultBiz.saveSchArrangeResults(resultList);
			if (result != GlobalConstant.ZERO_LINE) {
				schArrangeResultBiz.synchronizeProcessByResults(resultList);
				return GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}
	/**
	 *  保存自定义时间
	 */
	@RequestMapping(value="/saveDiyDate",method=RequestMethod.POST)
	@ResponseBody
	public String saveDiyDate(SchArrangeResult result){
		if(result!=null){
//			if(StringUtil.isNotBlank(result.getSchStartDate())){
//				String endDate = calculateDate(result.getSchStartDate(),result.getSchMonth());
//				result.setSchEndDate(endDate);
//			}
			int resultFlag = schArrangeResultBiz.saveSchArrangeResult(result);
			if(resultFlag!=GlobalConstant.ZERO_LINE){
				ResDoctorSchProcess process = resDoctorProcessBiz.searchByResultFlow(result.getResultFlow());

				if (process != null) {

					process.setSchStartDate(result.getSchStartDate());
					process.setSchEndDate(result.getSchEndDate());
					process.setStartDate(result.getSchStartDate());
					process.setEndDate(result.getSchEndDate());
					resDoctorProcessBiz.saveProcess(process);
				}
				if(StringUtil.isNotBlank(result.getSchEndDate())){
					return result.getSchEndDate();
				}else{
					return GlobalConstant.OPRE_SUCCESSED_FLAG;
				}
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}
	/**
	 *  方案二
	 *  保存自定义时间
	 */
	@RequestMapping(value="/saveDiyDate4Two",method=RequestMethod.POST)
	@ResponseBody
	public String saveDiyDate4Two(SchArrangeResult result){
		if(result!=null){
			if(GlobalConstant.RECORD_STATUS_Y.equals(result.getRecordStatus())){
				result.setSchStartDate("");
				result.setSchEndDate("");
			}
			String resultFlow = result.getResultFlow();
			ResDoctorSchProcess process = resDoctorProcessBiz.searchByResultFlow(resultFlow);

			if (process != null) {
				//停用
				if (GlobalConstant.RECORD_STATUS_D.equals(result.getRecordStatus())) {
					//若管理员移除了排班，则不再展示移除的科室；恢复后，学员需重新填写带教老师和科主任入科
					process.setTeacherUserFlow("");
					process.setTeacherUserName("");
					process.setHeadUserFlow("");
					process.setHeadUserName("");
					process.setSchFlag(GlobalConstant.FLAG_N);
					process.setIsCurrentFlag(GlobalConstant.FLAG_N);
				}
				//删除
				if(GlobalConstant.RECORD_STATUS_N.equals(result.getRecordStatus())){
					process.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
				}
				process.setSchStartDate(result.getSchStartDate());
				process.setSchEndDate(result.getSchEndDate());
				resDoctorProcessBiz.saveProcess(process);
			}
			int resultFlag = schArrangeResultBiz.saveSchArrangeResult(result);
			if (resultFlag != GlobalConstant.ZERO_LINE) {
				if (StringUtil.isNotBlank(result.getSchEndDate())) {
					return result.getSchEndDate();
				} else {
					return GlobalConstant.OPRE_SUCCESSED_FLAG;
				}
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}
	
	/**
	 * 自动计算排班日期
	 * @param startDate
	 * @param steps
	 * @return
	 */
	@RequestMapping(value="/getAutoCountResult",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String,String>> getAutoCountResult(String startDate,String[] steps){
		List<Map<String,String>> result = null;
		if(startDate!=null && startDate!=null && startDate.length()>0){
			result = new ArrayList<Map<String,String>>();
			for(String step : steps){
				Map<String,String> resultMap = new HashMap<String, String>();
				resultMap.put("start",startDate);
				String endDate = calculateDate(startDate,step);
				resultMap.put("end",endDate);
				startDate = DateUtil.addDate(endDate,1);
				result.add(resultMap);
			}
		}
		return result;
	}
	
	private List<SchArrangeResult> calculatePlan(List<SchArrangeResult> sortResult,String startDate,Map<String,String> schMonthMap,boolean ismust){
		if(sortResult!=null && sortResult.size()>0){
			for(SchArrangeResult result : sortResult){
				String step = schMonthMap.get(result.getResultFlow());
				result.setSchStartDate(startDate);
				String endDate = "";
				try{
					if(SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"))){
						endDate = calculateDate(startDate,step);
					}else{
						endDate = DateUtil.addMonthForArrange(startDate,step,ismust);
					}
				}catch(Exception e){
					endDate = calculateDate(startDate,step);
				}
				//String endDate = calculateDate(startDate,step);
				result.setSchEndDate(endDate);
				startDate = DateUtil.addDate(endDate,1);
			}
		}
		return sortResult;
	}

	/**
	 * 方案二
	 * @param sortResult
	 * @param startDate
	 * @param schMonthMap
	 * @param ismust
     * @return
     */
	private List<SchArrangeResult> calculatePlanTwo(List<SchArrangeResult> sortResult, String startDate, Map<String, String> schMonthMap, boolean ismust) {
		if (sortResult != null && sortResult.size() > 0) {
			for (SchArrangeResult result : sortResult) {
				String resultFlow = result.getResultFlow();
				ResDoctorSchProcess process = resDoctorProcessBiz.searchByResultFlow(resultFlow);

				if (process != null && GlobalConstant.FLAG_Y.equals(process.getIsExternal())) {
					//如果是外院轮转的开始时间和结束时间不可修改，但须记录开始和结束日期
					startDate = DateUtil.addDate(process.getSchEndDate(), 1);
				} else {
					//如果不是外院轮转的开始时间和结束时间可以修改
					String step = schMonthMap.get(result.getResultFlow());
					if(StringUtil.isNotBlank(step)&&Float.parseFloat(step)==0)
					{
						result.setSchStartDate("");
						result.setSchEndDate("");
					}else {
						result.setSchStartDate(startDate);
						String endDate = "";
						try {
							if (SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"))) {
								endDate = calculateDate(startDate, step);
							} else {
								endDate = DateUtil.addMonthForArrange(startDate, step, ismust);
							}
						} catch (Exception e) {
							endDate = calculateDate(startDate, step);
						}
						result.setSchEndDate(endDate);
						startDate = DateUtil.addDate(endDate, 1);
					}
				}
			}
		}
		return sortResult;
	}

	private String calculateDate(String date, String step) {
		if (StringUtil.isNotBlank(date) && StringUtil.isNotBlank(step)) {
			float stepFloat = Float.parseFloat(step);
			int stepInt = (int) stepFloat;
			float stepHalf = stepInt != 0 ? stepFloat % stepInt : stepFloat;
			if (SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"))) {
				if (stepInt != 0) {
					date = DateUtil.addDate(date, stepInt * 7);
				}
				if (stepHalf > 0) {
					date = DateUtil.addDate(date, 3);
				}
			} else {//if(SchUnitEnum.Month.getId().equals(InitConfig.getSysCfg("res_rotation_unit")))
				if (stepInt != 0) {
					date = DateUtil.newDateOfAddMonths(date, stepInt);
				}
				if (stepHalf > 0) {
					date = DateUtil.addDate(date, 15);
				}
			}
			date = DateUtil.addDate(date, -1);
		}
		return date;
	}

	/**
	 * 确认排班
	 */
	@RequestMapping(value = "/confirmRosting", method = RequestMethod.POST)
	@ResponseBody
	public String confirmRosting(ResDoctor doctor) {
		if (doctor != null) {
			int result = resDoctorBiz.editDoctor(doctor);
			if (result != GlobalConstant.ZERO_LINE) {
				return GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}

	/**
	 * 编辑ResDoctor
	 * @param doctor
	 * @return
	 */
	@RequestMapping(value="/basicInfoing",method=RequestMethod.POST)
	@ResponseBody
	public String basicInfoing(ResDoctor doctor){
		if(doctor!=null){
			resDoctorBiz.editDoctor(doctor);
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 教学登记
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/teachRegistryList",method=RequestMethod.GET)
	public String teachRegistryList(Integer currentPage, HttpServletRequest request, Model model) throws Exception {
		ResRec resRec = new ResRec();
		ResDoctor currDoctor = (ResDoctor) getSessionAttribute("currDoctor");
		//SysUser currUser = GlobalContext.getCurrentUser();
		resRec.setOrgFlow(currDoctor.getOrgFlow());
		resRec.setRecTypeId(ResRecTypeEnum.TeachRegistry.getId());
		resRec.setOperUserFlow(currDoctor.getDoctorFlow());
		PageHelper.startPage(currentPage,getPageSize(request));
		List<ResRecForm> resRecFormList = resRecBiz.searchResRecFormList(resRec);
		model.addAttribute("resRecFormList", resRecFormList);
		return "res/doctor/teachRegistryList";
	}
	
	/**
	 * 获取一条ResRec记录
	 * @param recFlow
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/getResRecByRecFlow"},method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResRecForm getResRecByRecFlow(String recFlow) throws Exception{
		ResRecForm resRec = null;
		if(StringUtil.isNotBlank(recFlow)){
			resRec = resRecBiz.getRecContentByRecFlow(recFlow);
		}
		return resRec;
	}

	/**
	 * 保存教学登记
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveResRecContent",method=RequestMethod.POST)
	@ResponseBody
	public String saveResRecContent(ResRecForm resRecForm,  Model model) throws Exception{
		int result = resRecBiz.saveResRecContent(resRecForm);
		if(GlobalConstant.ZERO_LINE != result){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}
	
	/**
	 * 删除
	 * @param recFlow
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delResRec")
	@ResponseBody
	public String delResRec(String recFlow) throws Exception{
		if(StringUtil.isNotBlank(recFlow)){
			ResRec resRec = new ResRec();
			resRec.setRecFlow(recFlow);
			resRec.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			int result = resRecBiz.edit(resRec);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.DELETE_SUCCESSED;
			}
		}
		return GlobalConstant.DELETE_FAIL;
	}
	
	/**
	 * 请假记录
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/absenceList")
	public String absenceList(Integer currentPage,SchDoctorAbsence doctorAbsence, HttpServletRequest request, Model model){
		ResDoctor resDoctor = doctorBiz.readDoctor(GlobalContext.getCurrentUser().getUserFlow());
		if(null!=resDoctor){
			model.addAttribute("resDoctor",resDoctor);
		}
		doctorAbsence.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
		doctorAbsence.setIsRegister(GlobalConstant.FLAG_N);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<SchDoctorAbsence> doctorAbsenceList = schDoctorAbsenceBiz.searchSchDoctorAbsenceList(doctorAbsence);
		model.addAttribute("doctorAbsenceList", doctorAbsenceList);
		return "res/intern/absenceList";
	}

	/**
	 * 新增请假记录
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editAbsence")
	public String editAbsence(String absenceFlow,String isRegister,String resRoleScope,String doctorFlow, Model model){
		SysUser user = GlobalContext.getCurrentUser();
		if(StringUtil.isNotBlank(absenceFlow) || !GlobalConstant.FLAG_Y.equals(isRegister)){
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
		if (GlobalConstant.FLAG_Y.equals(isRegister)) {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			if("headSecretary".equals(resRoleScope)){//规培秘书
				paramMap.put("headSecretaryFlow", user.getUserFlow());
				paramMap.put("isRegister","Y");
			}
			List<ResDoctorExt> doctorList = doctorBiz.searchDocByteacher(paramMap);
			model.addAttribute("doctorList",doctorList);
		}
		model.addAttribute("isRegister",isRegister);

		if(GlobalConstant.RES_ROLE_SCOPE_DOCTOR.equals(resRoleScope)){//学员
			model.addAttribute("doctor",doctorBiz.findByFlow(user.getUserFlow()));
		}else if(GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(resRoleScope)){//科主任

		}else if(GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(resRoleScope)){//专业基地管理员

		}else if(GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(resRoleScope)){//学校管理员

		}else if("repealAbsence".equals(resRoleScope)){//销假申请

		}
		model.addAttribute("resRoleScope",resRoleScope);
		return "res/intern/editAbsence";
	}

	/**
	 * 根据时间段找出轮转科室
	 */
	@RequestMapping("/searchSchDept")
	@ResponseBody
	public Object searchSchDept(String startDate,String endDate,String userFlow){
		if(StringUtil.isBlank(userFlow)){
			userFlow = GlobalContext.getCurrentUser().getUserFlow();
		}
		if(StringUtil.isNotBlank(startDate)&&StringUtil.isNotBlank(endDate)){
			List<Map<String,Object>> resultMapList = schArrangeResultBiz.searchArrangeResultNotInDates(startDate,endDate,userFlow);
			if(resultMapList!=null && resultMapList.size()>0){
				return -3;
			}
			List<SchArrangeResult> schArrangeResults = schArrangeResultBiz.searchArrangeResultByDateAndDoctorFlow(startDate,endDate,userFlow);
			if(schArrangeResults!=null&&schArrangeResults.size()>0){
				List<Map<String, Object>> dataList = new ArrayList<>();
				int i = 0;
				for(SchArrangeResult result:schArrangeResults){
					Map<String,Object> dataMap = new HashMap<>();
					dataMap.put("SCH_DEPT_FLOW",result.getSchDeptFlow());
					dataMap.put("SCH_DEPT_NAME",result.getSchDeptName());
					dataMap.put("startDate",result.getSchStartDate());
					dataMap.put("endDate",result.getSchEndDate());
					if(i==0){
						result.setSchStartDate(startDate);
					}
					if(i==schArrangeResults.size()-1){
						result.setSchEndDate(endDate);
					}
					Long intervalDay = DateUtil.signDaysBetweenTowDate(result.getSchStartDate(),result.getSchEndDate());
					dataMap.put("intervalDay",1-intervalDay);
					String resultFlow = result.getResultFlow();
					ResDoctorSchProcess processes = resDoctorProcessBiz.searchByResultFlow(resultFlow);
					String teacherFlow = processes.getHeadUserFlow();
					String teacherName = processes.getHeadUserName();
					dataMap.put("teacherFlow",teacherFlow);
					dataMap.put("teacherName",teacherName);
					dataMap.put("teachingFlow",processes.getTeacherUserFlow());
					dataMap.put("teachingName",processes.getTeacherUserName());
					dataMap.put("orgFlow",processes.getOrgFlow());
					dataMap.put("orgName",processes.getOrgName());
					dataList.add(dataMap);
					i++;
				}
				return dataList;
			}
			return "0";
		}
		return "-1";
	}

	/**
	 * 查询学员剩余年假天数
	 */
	@RequestMapping("/getYearLeave")
	@ResponseBody
	public int getYearLeave(String absenceFlow,String userFlow){
		String yearEndDay = InitConfig.getSysCfg("res_absence_yearEnd_day");
		if(StringUtil.isBlank(userFlow)){
			userFlow = GlobalContext.getCurrentUser().getUserFlow();
		}
		ResDoctor doctor = resDoctorBiz.readDoctor(userFlow);
		if(StringUtil.isBlank(yearEndDay)){
			return -1;
		}
		if(StringUtil.isBlank(doctor.getSessionNumber())){
			return -2;
		}
		String currentYear = DateUtil.getYear();
		String currentDate = DateUtil.getCurrDate();
		String trueEndDate = Integer.parseInt(doctor.getSessionNumber())+1+"-"+yearEndDay;
		if(currentDate.compareTo(trueEndDate)<0){//未满一年没有年假
			return 0;
		}else{
			//查询除本记录以外当年请的所有年假
			List<SchDoctorAbsence> absences = schDoctorAbsenceBiz.getCurrentYearLeaves(absenceFlow,currentYear,userFlow);
			int intervalDays = 0;
			if(absences!=null&&absences.size()>0){
				for(SchDoctorAbsence absence:absences){
					String intervalDay = absence.getIntervalDay();
					intervalDays+=Integer.parseInt(intervalDay);
				}
			}
			return 5-intervalDays;
		}
	}

	/**
	 * 查询时间段里有没有周末
	 */
	@RequestMapping(value="/getWeekends")
	@ResponseBody
	public int getWeekends(String startDate,String endDate) throws ParseException {
		//获得两个日期间所有日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dBegin = sdf.parse(startDate);
		Date dEnd = sdf.parse(endDate);
		List<Date> lDate = TimeUtil.findDates(dBegin, dEnd);
		for (Date date : lDate)	{
			String day = sdf.format(date);
			Calendar ca = Calendar.getInstance();
			Date d = sdf.parse(day);
			ca.setTime(d);//设置当前时间
			//判断日期是否是周六周日
			if(ca.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||	ca.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
				return 1;
			}
		}
		return 0;
	}

	/**
	 * 处理批量请假记录
	 */
	@RequestMapping(value="/saveDoctorAbsences")
	@ResponseBody
	public String saveDoctorAbsences(SchDoctorAbsence doctorAbsence,String dataList,MultipartFile file,String endDate2,String startDate1) throws ParseException {
		String doctorFlow = "";
		if(StringUtil.isBlank(doctorAbsence.getDoctorFlow())){
			doctorFlow = GlobalContext.getCurrentUser().getUserFlow();
		}else {
			doctorFlow = doctorAbsence.getDoctorFlow();
		}
		int count=schDoctorAbsenceBiz.checkDates(doctorFlow,startDate1,endDate2,doctorAbsence.getAbsenceFlow());
		if(count>0)
		{
			return "与其他考勤记录时间存在重复，请核查！";
		}
		ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
		String doctorTypeId = doctor.getDoctorTypeId();
		String doctorTypeName = doctor.getDoctorTypeName();
		doctorAbsence.setDoctorTypeId(doctorTypeId);
		doctorAbsence.setDoctorTypeName(doctorTypeName);
		List<Map<String,Object>> mapList = JSON.parseObject(dataList,ArrayList.class);
		int i = 0;
		for(Map<String,Object> map:mapList){
			doctorAbsence.setIntervalDay(map.get("intervalDay").toString());
			doctorAbsence.setSchDeptFlow((String)map.get("SCH_DEPT_FLOW"));
			doctorAbsence.setSchDeptName((String)map.get("SCH_DEPT_NAME"));
			doctorAbsence.setStartDate((String)map.get("startDate"));
			doctorAbsence.setEndDate((String)map.get("endDate"));
			if(i==0){
				doctorAbsence.setStartDate(startDate1);
			}
			if(i>0){
				doctorAbsence.setAbsenceFlow("");
			}
			if(i==mapList.size()-1){
				doctorAbsence.setEndDate(endDate2);
			}
			doctorAbsence.setTeacherFlow((String)map.get("teacherFlow"));
			doctorAbsence.setTeacherName((String)map.get("teacherName"));
			doctorAbsence.setTeachingFlow((String)map.get("teachingFlow"));
			doctorAbsence.setTeachingName((String)map.get("teachingName"));
			doctorAbsence.setOrgFlow((String)map.get("orgFlow"));
			doctorAbsence.setOrgName((String)map.get("orgName"));
			i++;
			try{
				saveDoctorAbsence(doctorAbsence,file);
			}catch (RuntimeException re){
				re.printStackTrace();
				return re.getMessage();
			}
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}

	/**
	 * 保存请假记录
	 * @param doctorAbsence
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/saveDoctorAbsence")
	@ResponseBody
	public String saveDoctorAbsence(SchDoctorAbsence doctorAbsence, MultipartFile file) throws ParseException {
		int result = schDoctorAbsenceBiz.editSchDoctorAbsence(doctorAbsence,file);
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
	public String delAbsence(String absenceFlow) throws ParseException {
		if(StringUtil.isNotBlank(absenceFlow)){
			SchDoctorAbsence doctorAbsence = new SchDoctorAbsence();
			doctorAbsence.setAbsenceFlow(absenceFlow);
			doctorAbsence.setRecordStatus(GlobalConstant.RECORD_STATUS_N);

			SchDoctorAbsence schDoctorAbsenceOld = schDoctorAbsenceBiz.readSchDoctorAbsence(absenceFlow);//删除旧考勤记录
			if("Y".equals(schDoctorAbsenceOld.getIsRegister())){
				String oldStartDate = schDoctorAbsenceOld.getStartDate();
				String oldEndDate = schDoctorAbsenceOld.getEndDate();
				//获得两个日期间所有日期
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date dBegin = sdf.parse(oldStartDate);
				Date dEnd = sdf.parse(oldEndDate);
				List<Date> lDate = TimeUtil.findDates(dBegin, dEnd);
				List<String> dateListAll = new ArrayList<>();
				for (Date date : lDate)	{
					dateListAll.add(sdf.format(date));
				}
				//从考勤信息表删除
				String monthStartDay = oldStartDate.substring(0,7);
				String monthEndDay = oldEndDate.substring(0,7);
				List<String> months = TimeUtil.getMonthsByTwoMonth(monthStartDay,monthEndDay);
				if(months!=null&&months.size()>0){
					for(String month:months){
						ZseyHrKqMonth zseyHrKqMonth = zseyHrKqMonthBiz.searchByKqDate(schDoctorAbsenceOld.getDoctorFlow(),month);
						ZseyHrKqMonth hrKqMonth = new ZseyHrKqMonth();
						if(null!=zseyHrKqMonth){
							String monthFlow = zseyHrKqMonth.getMonthFlow();
							hrKqMonth.setMonthFlow(monthFlow);
						}
//					hrKqMonth.setResType("2");
//					hrKqMonth.setUserFlow(user.getUserFlow());
//					hrKqMonth.setUserCode(user.getUserCode());
//					hrKqMonth.setUserName(user.getUserName());
//					hrKqMonth.setDeptFlow(user.getDeptFlow());
//					hrKqMonth.setDeptName(user.getDeptName());
//					hrKqMonth.setInDate(resDoctor.getSessionNumber());
//					hrKqMonth.setIdNo(user.getIdNo());
//					hrKqMonth.setKqDate(month);
//					hrKqMonth.setKqDeptFlow(doctorAbsence.getSchDeptFlow());
//					hrKqMonth.setKqDeptName(doctorAbsence.getSchDeptName());
						for(String date:dateListAll){
							if(month.equals(date.substring(0,7))){
								String day = date.substring(8,10);
								switch (day) {
									case  "01" : hrKqMonth.setM01("");break;
									case  "02" : hrKqMonth.setM02("");break;
									case  "03" : hrKqMonth.setM03("");break;
									case  "04" : hrKqMonth.setM04("");break;
									case  "05" : hrKqMonth.setM05("");break;
									case  "06" : hrKqMonth.setM06("");break;
									case  "07" : hrKqMonth.setM07("");break;
									case  "08" : hrKqMonth.setM08("");break;
									case  "09" : hrKqMonth.setM09("");break;
									case  "10" : hrKqMonth.setM10("");break;
									case  "11" : hrKqMonth.setM11("");break;
									case  "12" : hrKqMonth.setM12("");break;
									case  "13" : hrKqMonth.setM13("");break;
									case  "14" : hrKqMonth.setM14("");break;
									case  "15" : hrKqMonth.setM15("");break;
									case  "16" : hrKqMonth.setM16("");break;
									case  "17" : hrKqMonth.setM17("");break;
									case  "18" : hrKqMonth.setM18("");break;
									case  "19" : hrKqMonth.setM19("");break;
									case  "20" : hrKqMonth.setM20("");break;
									case  "21" : hrKqMonth.setM21("");break;
									case  "22" : hrKqMonth.setM22("");break;
									case  "23" : hrKqMonth.setM23("");break;
									case  "24" : hrKqMonth.setM24("");break;
									case  "25" : hrKqMonth.setM25("");break;
									case  "26" : hrKqMonth.setM26("");break;
									case  "27" : hrKqMonth.setM27("");break;
									case  "28" : hrKqMonth.setM28("");break;
									case  "29" : hrKqMonth.setM29("");break;
									case  "30" : hrKqMonth.setM30("");break;
									case  "31" : hrKqMonth.setM31("");break;
								}
							}
						}
						zseyHrKqMonthBiz.edit(hrKqMonth);
					}
				}
			}

			int result = schDoctorAbsenceBiz.saveSchDoctorAbsence(doctorAbsence);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.DELETE_SUCCESSED;
			}
		}
		return GlobalConstant.DELETE_FAIL;
	}
	
	@RequestMapping(value="/repealAbsence")
	@ResponseBody
	public String repealAbsence(String absenceFlow,String repealAbsenceDate,String repealAbsenceDay,String repealAbsenceInfo,String repealAbsence) throws ParseException {
		if(StringUtil.isNotBlank(absenceFlow)){
			SchDoctorAbsence doctorAbsence0 = new SchDoctorAbsence();
			doctorAbsence0.setAbsenceFlow(absenceFlow);
			doctorAbsence0.setRepealAbsence(repealAbsence);
			doctorAbsence0.setRepealAbsenceDate(repealAbsenceDate);
			if(StringUtil.isNotBlank(repealAbsenceDay)){
				doctorAbsence0.setRepealAbsenceDay(repealAbsenceDay);
				doctorAbsence0.setEndDate(repealAbsenceDay);
				doctorAbsence0.setRepealAbsenceInfo(repealAbsenceInfo);
				SchDoctorAbsence doctorAbsence = schDoctorAbsenceBiz.readSchDoctorAbsence(absenceFlow);
				String startDate = doctorAbsence.getStartDate();
				String endDate = doctorAbsence.getEndDate();
				//如果销假时间 大于 原结束时间 则新增考勤信息
				if(endDate.compareTo(repealAbsenceDay)<0){
					SysUser user = userBiz.findByFlow(doctorAbsence.getDoctorFlow());
					ResDoctor resDoctor = doctorBiz.readDoctor(doctorAbsence.getDoctorFlow());
					//获得两个日期间所有日期
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date dBegin = sdf.parse(doctorAbsence.getEndDate());
					Date dEnd = sdf.parse(repealAbsenceDay);
					List<Date> lDate = TimeUtil.findDates(dBegin, dEnd);
					List<String> dateListAll = new ArrayList<>();
					for (Date date : lDate)	{
						dateListAll.add(sdf.format(date));
					}
					//录入考勤信息表
					String monthStartDay = doctorAbsence.getEndDate().substring(0,7);
					String monthEndDay = repealAbsenceDay.substring(0,7);
					List<String> months = TimeUtil.getMonthsByTwoMonth(monthStartDay,monthEndDay);
					if(months!=null&&months.size()>0){
						for(String month:months){
							ZseyHrKqMonth zseyHrKqMonth = zseyHrKqMonthBiz.searchByKqDate(user.getUserFlow(),month);
							ZseyHrKqMonth hrKqMonth = new ZseyHrKqMonth();
							if(null!=zseyHrKqMonth){
								String monthFlow = zseyHrKqMonth.getMonthFlow();
								hrKqMonth.setMonthFlow(monthFlow);
							}
							for(String date:dateListAll){
								if(month.equals(date.substring(0,7))){
									String day = date.substring(8,10);
									AbsenceTypeEnum typeEnum = (AbsenceTypeEnum)(EnumUtil.getById(doctorAbsence.getAbsenceTypeId(), AbsenceTypeEnum.class));
									String code = typeEnum.getCode();
									switch (day) {
										case  "01" : hrKqMonth.setM01(code);break;
										case  "02" : hrKqMonth.setM02(code);break;
										case  "03" : hrKqMonth.setM03(code);break;
										case  "04" : hrKqMonth.setM04(code);break;
										case  "05" : hrKqMonth.setM05(code);break;
										case  "06" : hrKqMonth.setM06(code);break;
										case  "07" : hrKqMonth.setM07(code);break;
										case  "08" : hrKqMonth.setM08(code);break;
										case  "09" : hrKqMonth.setM09(code);break;
										case  "10" : hrKqMonth.setM10(code);break;
										case  "11" : hrKqMonth.setM11(code);break;
										case  "12" : hrKqMonth.setM12(code);break;
										case  "13" : hrKqMonth.setM13(code);break;
										case  "14" : hrKqMonth.setM14(code);break;
										case  "15" : hrKqMonth.setM15(code);break;
										case  "16" : hrKqMonth.setM16(code);break;
										case  "17" : hrKqMonth.setM17(code);break;
										case  "18" : hrKqMonth.setM18(code);break;
										case  "19" : hrKqMonth.setM19(code);break;
										case  "20" : hrKqMonth.setM20(code);break;
										case  "21" : hrKqMonth.setM21(code);break;
										case  "22" : hrKqMonth.setM22(code);break;
										case  "23" : hrKqMonth.setM23(code);break;
										case  "24" : hrKqMonth.setM24(code);break;
										case  "25" : hrKqMonth.setM25(code);break;
										case  "26" : hrKqMonth.setM26(code);break;
										case  "27" : hrKqMonth.setM27(code);break;
										case  "28" : hrKqMonth.setM28(code);break;
										case  "29" : hrKqMonth.setM29(code);break;
										case  "30" : hrKqMonth.setM30(code);break;
										case  "31" : hrKqMonth.setM31(code);break;
									}
								}
							}
							zseyHrKqMonthBiz.edit(hrKqMonth);
						}
					}
				}
				//新增完毕
				//如果销假时间小于结束时间 则去掉多余考勤记录
				if(endDate.compareTo(repealAbsenceDay)>0){
					SysUser user = userBiz.findByFlow(doctorAbsence.getDoctorFlow());
					ResDoctor resDoctor = doctorBiz.readDoctor(doctorAbsence.getDoctorFlow());
					//获得两个日期间所有日期
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = new GregorianCalendar();
					c.setTime(sdf.parse(repealAbsenceDay));
					c.add(c.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
					Date dBegin=c.getTime(); //这个时间就是日期往后推一天的结果
					Date dEnd = sdf.parse(doctorAbsence.getEndDate());
					List<Date> lDate = TimeUtil.findDates(dBegin, dEnd);
					List<String> dateListAll = new ArrayList<>();
					for (Date date : lDate)	{
						dateListAll.add(sdf.format(date));
					}
					//从考勤考勤信息表中删除
					String monthStartDay = doctorAbsence.getEndDate().substring(0,7);
					String monthEndDay = repealAbsenceDay.substring(0,7);
					List<String> months = TimeUtil.getMonthsByTwoMonth(monthStartDay,monthEndDay);
					if(months!=null&&months.size()>0){
						for(String month:months){
							ZseyHrKqMonth zseyHrKqMonth = zseyHrKqMonthBiz.searchByKqDate(user.getUserFlow(),month);
							ZseyHrKqMonth hrKqMonth = new ZseyHrKqMonth();
							if(null!=zseyHrKqMonth){
								String monthFlow = zseyHrKqMonth.getMonthFlow();
								hrKqMonth.setMonthFlow(monthFlow);
							}
							for(String date:dateListAll){
								if(month.equals(date.substring(0,7))){
									String day = date.substring(8,10);
									String code = "";
									switch (day) {
										case  "01" : hrKqMonth.setM01(code);break;
										case  "02" : hrKqMonth.setM02(code);break;
										case  "03" : hrKqMonth.setM03(code);break;
										case  "04" : hrKqMonth.setM04(code);break;
										case  "05" : hrKqMonth.setM05(code);break;
										case  "06" : hrKqMonth.setM06(code);break;
										case  "07" : hrKqMonth.setM07(code);break;
										case  "08" : hrKqMonth.setM08(code);break;
										case  "09" : hrKqMonth.setM09(code);break;
										case  "10" : hrKqMonth.setM10(code);break;
										case  "11" : hrKqMonth.setM11(code);break;
										case  "12" : hrKqMonth.setM12(code);break;
										case  "13" : hrKqMonth.setM13(code);break;
										case  "14" : hrKqMonth.setM14(code);break;
										case  "15" : hrKqMonth.setM15(code);break;
										case  "16" : hrKqMonth.setM16(code);break;
										case  "17" : hrKqMonth.setM17(code);break;
										case  "18" : hrKqMonth.setM18(code);break;
										case  "19" : hrKqMonth.setM19(code);break;
										case  "20" : hrKqMonth.setM20(code);break;
										case  "21" : hrKqMonth.setM21(code);break;
										case  "22" : hrKqMonth.setM22(code);break;
										case  "23" : hrKqMonth.setM23(code);break;
										case  "24" : hrKqMonth.setM24(code);break;
										case  "25" : hrKqMonth.setM25(code);break;
										case  "26" : hrKqMonth.setM26(code);break;
										case  "27" : hrKqMonth.setM27(code);break;
										case  "28" : hrKqMonth.setM28(code);break;
										case  "29" : hrKqMonth.setM29(code);break;
										case  "30" : hrKqMonth.setM30(code);break;
										case  "31" : hrKqMonth.setM31(code);break;
									}
								}
							}
							zseyHrKqMonthBiz.edit(hrKqMonth);
						}
					}
				}
				//删除完毕
				String intervalDay = DateUtil.signDaysBetweenTowDate(startDate,repealAbsenceDay)+"";
				doctorAbsence0.setIntervalDay(String.valueOf(1-Integer.parseInt(intervalDay)));
			}
			int result = schDoctorAbsenceBiz.saveSchDoctorAbsence(doctorAbsence0);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.OPRE_SUCCESSED;
			}
		}
		return GlobalConstant.OPRE_FAIL;
	}
	
	@RequestMapping(value={"/user/saveGroupRelated"},method={RequestMethod.POST})
	@ResponseBody
	public String saveGroupRelated(String doctorFlow,String value,String type){
		ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
		if(doctor!=null){
			if ("groupId".equals(type)) {
				if (StringUtil.isNotBlank(value)) {
					doctor.setGroupId(value);
					doctor.setGroupName(DictTypeEnum.ResGroup.getDictNameById(value));
				} else {
					doctor.setGroupId("");
					doctor.setGroupName("");
				}
			} else if ("groupRoleId".equals(type)) {
				if (StringUtil.isNotBlank(value)) {
					doctor.setGroupRoleId(value);
					doctor.setGroupRoleName(GroupRoleEnum.getNameById(value));
				} else {
					doctor.setGroupRoleId("");
					doctor.setGroupRoleName("");
				}
			}
		}
		if(GlobalConstant.ZERO_LINE!=resDoctorBiz.editDoctor(doctor)){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value = "/appraisalList")
	public String appraisalList(String doctorFlow, Model model) {
		ResDoctor doctor = (ResDoctor) getSessionAttribute("currDoctor");
		model.addAttribute("doctor", doctor);
		if (doctor != null) {
			String groupId = doctor.getGroupId();
			if (StringUtil.isNotBlank(groupId)) {
				ResDoctor temp = new ResDoctor();
				temp.setGroupId(groupId);
				temp.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				List<ResDoctor> doctorList = resDoctorBiz.searchByDocNotSelf(temp,doctor.getDoctorFlow());

				if(doctorList!=null && doctorList.size()>0){
					model.addAttribute("doctorList", doctorList);

					List<String> doctorFlows = new ArrayList<String>();
					for(ResDoctor doctorTemp : doctorList){
						doctorFlows.add(doctorTemp.getDoctorFlow());
					}

					List<SysUser> userList = userBiz.searchSysUserByuserFlows(doctorFlows);
					if(userList!=null && userList.size()>0){
						Map<String,SysUser> userMap = new HashMap<String, SysUser>();
						for(SysUser user : userList){
							userMap.put(user.getUserFlow(),user);
						}
						model.addAttribute("userMap",userMap);
					}

					List<String> newDoctorFlows = resDoctorProcessBiz.searchRotationDoctor(doctorFlows);
					if(newDoctorFlows!=null && newDoctorFlows.size()>0){
						List<SchArrangeResult> resultList = schArrangeResultBiz.cutAfterResult(newDoctorFlows);
						if(resultList!=null && resultList.size()>0){
							Map<String,SchArrangeResult> currResultMap = new HashMap<String, SchArrangeResult>();
							Map<String,SchArrangeResult> nextResultMap = new HashMap<String, SchArrangeResult>();
							for(SchArrangeResult result : resultList){
								String key = result.getDoctorFlow();
								if(currResultMap.get(key)==null){
									currResultMap.put(key,result);
								}else if(nextResultMap.get(key)==null){
									nextResultMap.put(key,result);
								}
							}
							model.addAttribute("currResultMap",currResultMap);
							model.addAttribute("nextResultMap",nextResultMap);
						}
					}

					List<Map<String,Object>> absenceSumMapList = schDoctorAbsenceBiz.countAbsenceDays(doctorFlows);
					if(absenceSumMapList!=null && absenceSumMapList.size()>0){
						Map<Object,Object> absenceSumMap = new HashMap<Object, Object>();
						for(Map<String,Object> map : absenceSumMapList){
							absenceSumMap.put(map.get("doctorFlow"),map.get("countDay"));
						}
						model.addAttribute("absenceSumMap",absenceSumMap);
					}
				}
			}
		}
		return "res/doctor/archives/appraisalList";
	}

	@RequestMapping(value="/preTrainForm")
	public String preTrainForm(String roleFlag,String resultFlow,Model model){
		model.addAttribute("roleFlag",roleFlag);
		if(StringUtil.isNotBlank(resultFlow)){
			SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(resultFlow);
			if(result!=null){
				model.addAttribute("result",result);
				List<ResRec> recList = resRecBiz.searchByRecWithBLOBs(ResRecTypeEnum.PreTrainForm.getId(),result.getSchDeptFlow(),result.getDoctorFlow());
				if(recList!=null && recList.size()>0){
					ResRec rec = recList.get(0);
					model.addAttribute("rec", rec);
					String recContent = rec.getRecContent();
					Map<String,Map<String,String>> dataMap = resRecBiz.getPreTrainFormDataMap(recContent);
					model.addAttribute("dataMap",dataMap);
				}
			}
		}
		return "res/edu/student/preTrainForm";
	}
	
	/**
	 * 打印
	 * @param processFlow
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/printPreTrainForm")
	public void printPreTrainForm(String processFlow, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(StringUtil.isNotBlank(processFlow)){
			if(StringUtil.isNotBlank(processFlow)){
				List<ResRec> recList = resRecBiz.searchRecByProcessWithBLOBs(processFlow,PreRecTypeEnum.PreTrainForm.getId());
				if(recList!=null && recList.size()>0){
					ResRec rec = recList.get(0);
					String schDeptName = rec.getSchDeptName();
					resultMap.put("schDeptName",schDeptName);
					String recContent = rec.getRecContent();
					Map<String, Object> formDataMap = resRecBiz.parseRecContent(recContent);
					//Map<String, Map<String, String>> dataMap = resRecBiz.getPreTrainFormDataMap(recContent);
//					for(Map.Entry<String, Map<String, String>> entry : dataMap.entrySet()){
						//System.err.println("key:" + entry.getKey() + "--value:" + entry.getValue());
//						Map<String, String> childMap = entry.getValue();
//						for(Map.Entry<String, String> childEntry : childMap.entrySet()){
							//System.err.println("key:" + childEntry.getKey() + "--value:" + childEntry.getValue());
//							String entryValue = childEntry.getValue();
//							if(GlobalConstant.FLAG_Y.equals(entryValue)){
//								entryValue = "是";
//							}else if(GlobalConstant.FLAG_N.equals(entryValue)){
//								entryValue = "否";
//							}
//							resultMap.put(entry.getKey()+childEntry.getKey(), entryValue);
//						}
//					}
					for(String key : formDataMap.keySet()){
						Object value = formDataMap.get(key);
						if(GlobalConstant.FLAG_Y.equals(value)){
							value = "是";
						}
						if(GlobalConstant.FLAG_N.equals(value)){
							value = "否";
						}
						resultMap.put(key,value);
					}

					WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
					String path = "/jsp/res/edu/student/print/printTemeplete.docx";//模板
					ServletContext context = request.getServletContext();
					String watermark = GeneralMethod.getWatermark(GlobalConstant.FLAG_N);
					temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), resultMap, watermark, true);
					if (temeplete != null) {
						String name = schDeptName + "岗前培训记录表.docx";
						response.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
						response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
						ServletOutputStream out = response.getOutputStream ();
						(new SaveToZipFile (temeplete)).save (out);
						out.flush ();
					}
				}
			}
		}
	}
	
	@RequestMapping(value="/annualTrainForm")
	public String annualTrainForm(String roleFlag, String doctorFlow, Model model) {
		model.addAttribute("roleFlag", roleFlag);
		ResDoctor doctor = (ResDoctor) getSessionAttribute("currDoctor");
		String orgFlow = doctor.getOrgFlow();
		if(StringUtil.isNotBlank(orgFlow)){
			List<SysDept> deptList = deptBiz.searchDeptByOrg(orgFlow);
			if(deptList!=null && deptList.size()>0){
				model.addAttribute("deptList",deptList);
				Map<String,SysDept> deptMap = new HashMap<String, SysDept>();
				for(SysDept dept : deptList){
					deptMap.put(dept.getDeptFlow(),dept);
				}
				model.addAttribute("deptMap",deptMap);
			}
		}
		List<ResRec> recList = resRecBiz.searchByRecWithBLOBs(ResRecTypeEnum.AnnualTrainForm.getId(),doctorFlow);
		if(recList!=null && recList.size()>0){
			ResRec rec = recList.get(0);
			model.addAttribute("rec", rec);
			String recContent = rec.getRecContent();
			Map<String,Object> formDataMap = resRecBiz.getAnnualTrainFormDataMap(recContent);
			model.addAttribute("formDataMap",formDataMap);
		}
		return "res/edu/student/annualTrainForm";
	}

	@RequestMapping(value="/annualtrainShow")
	public String annualtrainShow(String doctorFlow, String trainYear, Model model){
		if(StringUtil.isBlank(doctorFlow)){
			SysUser currUser = GlobalContext.getCurrentUser();
            doctorFlow = currUser.getUserFlow();
        }
        ResRec search = new ResRec();
        search.setRecTypeId(RegistryTypeEnum.AnnualTrainForm.getId());
        search.setOperUserFlow(doctorFlow);
        List<ResRec> recList = resRecBiz.searchByRecWithBLOBs(search, trainYear);
        if (recList != null && recList.size() > 0) {
            Map<String, Map<String, Object>> recContentMap = new HashMap<String, Map<String,Object>>();
            for (ResRec rec : recList) {
                Map<String, Object> content = resRecBiz.parseRecContent(rec.getRecContent());
                recContentMap.put(rec.getRecFlow(), content);
            }
            model.addAttribute("recContentMap", recContentMap);
        }
        model.addAttribute("recList", recList);
        return "res/edu/student/annuaForm";
	}

	/**
	 * 年度培训记录打印
	 * @param userFlow
	 * @param trainYear
	 * @param request
	 * @param response
	 * @throws Exception
	 */
    @RequestMapping(value = "/printAnnualTrain")
    public void printAnnualTrain(String userFlow, String trainYear, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WordprocessingMLPackage temeplete;
        List<WordprocessingMLPackage> templates = new ArrayList<WordprocessingMLPackage>();
        if (StringUtil.isBlank(userFlow)) {
            SysUser currUser = GlobalContext.getCurrentUser();
            userFlow = currUser.getUserFlow();
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("trainYear", trainYear);
        ResDoctor resDoctor = resDoctorBiz.readDoctor(userFlow);
        if (resDoctor != null) {
            resultMap.put("doctorName", resDoctor.getDoctorName());
            resultMap.put("doctorCode", resDoctor.getDoctorCode());
        }
        ResRec resRec = new ResRec();
        resRec.setRecTypeId(RegistryTypeEnum.AnnualTrainForm.getId());
        resRec.setOperUserFlow(userFlow);
        List<ResRec> recList = resRecBiz.searchByRecWithBLOBs(resRec, trainYear);
        //if(recList!=null&&recList.size()>0){
        Map<String, List<ResRec>> resRecListMap = new HashMap<String, List<ResRec>>();
        for (ResRec rec : recList) {
            String schDeptFlow = rec.getSchDeptFlow();
            List<ResRec> tempList = resRecListMap.get(schDeptFlow);
            if (tempList == null) {
                tempList = new ArrayList<ResRec>();
            }
            tempList.add(rec);
            resRecListMap.put(schDeptFlow, tempList);
        }
        List<WordprocessingMLPackage> reserveLeaderTemplates = new ArrayList<WordprocessingMLPackage>();
        ServletContext context = request.getServletContext();
		String watermark = GeneralMethod.getWatermark(GlobalConstant.FLAG_Y);
		for (Entry<String, List<ResRec>> entry : resRecListMap.entrySet()) {
            //System.err.println("key:" + entry.getKey() + "--value:" + entry.getValue());
            List<ResRec> printRecList = entry.getValue();
            Map<String, Map<String, Object>> recContentMap = new HashMap<String, Map<String, Object>>();
            List<ItemGroupData> itemGroupDataList = new ArrayList<ItemGroupData>();
            int i = 0;
            Double classHourScoreCount = new Double(0);
            Double academicSum = new Double(0);
            Double academicSum_I = new Double(0);
            Double academicSum_II = new Double(0);
            for (ResRec rec : printRecList) {
                i++;
                Map<String, Object> content = resRecBiz.parseRecContent(rec.getRecContent());
                recContentMap.put(rec.getRecFlow(), content);
                ItemGroupData igd = new ItemGroupData();
                Map<String, Object> objMap = new HashMap<String, Object>();
                objMap.put("status", StringUtil.defaultString(String.valueOf(i)));
                String studyType = StringUtil.defaultString((String) content.get("studyType"));
                objMap.put("studyType", studyType);
                objMap.put("trainContent", StringUtil.defaultString((String) content.get("trainContent")));
                objMap.put("trainDate", StringUtil.defaultString((String) content.get("trainDate")));
                String academicScore = StringUtil.defaultString((String) content.get("academicScore"));
                String classHourScore = StringUtil.defaultString((String) content.get("classHourScore"));
                objMap.put("academicScore", academicScore + "/" + classHourScore);
                objMap.put("remarks", StringUtil.defaultString((String) content.get("remarks")));
                igd.setObjMap(objMap);
                itemGroupDataList.add(igd);
                //统计学习类型
                if (StringUtil.isNotBlank(classHourScore)) {
                    classHourScoreCount += Double.valueOf(classHourScore);
                }
                if (StringUtil.isNotBlank(academicScore)) {
                    if (studyType.equals("继续教育I类")) {
                        academicSum_I += Double.valueOf(academicScore);
                    } else if (studyType.equals("继续教育II类")) {
                        academicSum_II += Double.valueOf(academicScore);
                    }
                    academicSum += Double.valueOf(academicScore);
                }
            }
            resultMap.put("itemGroupDataList", itemGroupDataList);
            resultMap.put("schDeptName", StringUtil.defaultString(printRecList.get(0).getSchDeptName()));
            resultMap.put("classHourScoreCount", _doubleTrans(classHourScoreCount));
            resultMap.put("academicSum", _doubleTrans(academicSum));
            resultMap.put("academicSum_I", _doubleTrans(academicSum_I));
            resultMap.put("academicSum_II", _doubleTrans(academicSum_II));
            resultMap.put("trainYear", trainYear);
            temeplete = new WordprocessingMLPackage();
            String path = "/jsp/res/edu/student/print/annualTrainTemeplete.docx";//模板
            WordprocessingMLPackage template2 = Docx4jUtil.convert(new File(context.getRealPath(path)), resultMap, watermark, true);
            reserveLeaderTemplates.add(template2);
        }
        //合并模板
        if (reserveLeaderTemplates.size() > 0) {
            templates.addAll(reserveLeaderTemplates);
            temeplete = Docx4jUtil.mergeDocx(templates);
        } else {
            temeplete = new WordprocessingMLPackage();
            String path = "/jsp/res/edu/student/print/annualTrainTemeplete.docx";//模板
            temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), resultMap, watermark, true);
        }
        if (temeplete != null) {
            String name = "年度培训记录.docx";
            response.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = response.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
            out.flush ();
        }
		//}
	}
	
	@RequestMapping(value="/speAbilityAssessList")
	public String speAbilityAssessList(String operUserFlow,String recTypeId,Model model){
		List<ResRec> recList=resRecBiz.searchByUserFlowAndTypeId(operUserFlow, recTypeId);
		//List<SysUser> userList=resRecBiz.searchByUserFlowAndTypeId(operUserFlow, recTypeId);
		
		if(StringUtil.isNotBlank(recTypeId)){
			String globalRecName = GlobalRecTypeEnum.getNameById(recTypeId);
			model.addAttribute("globalRecName",globalRecName);
		}
		
		if(recList!=null && recList.size()>0){
			model.addAttribute("recList", recList);
			Map<String,List<Map<String,String>>> viewListMap = new HashMap<String, List<Map<String,String>>>();
			for(ResRec recTemp : recList){
				String content = recTemp.getRecContent();
				String form = recTemp.getRecForm();
				String type = recTemp.getRecTypeId();
				String ver = recTemp.getRecVersion();
				
				List<Map<String,String>> viewInfoList = resRecBiz.parseViewValue(form,type,ver,content);
				viewListMap.put(recTemp.getRecFlow(),viewInfoList);
			}
			model.addAttribute("viewListMap",viewListMap);
		}
		return "res/edu/student/speAbilityAssess";
	}
	
	@RequestMapping(value="/assessSearch/{roleFlag}")
	public String assessSearch(@PathVariable String roleFlag,Model model){
		model.addAttribute("roleFlag",roleFlag);
		return "/res/doctor/assessSearch";
	}

	/**
	 * 学员-考试查询
	 * @param roleFlag
	 * @param userFlow
	 * @param model
	 * @param result
     * @return
     */
	@RequestMapping(value="/userAssessList")
	public String userAssessList(String roleFlag,String userFlow,Model model,Integer currentPage,TestResult result,HttpServletRequest request){
		String currUserFlow = GlobalContext.getCurrentUser().getUserFlow();

		if(currentPage==null){
			currentPage=1;
		}

		List<ResDoctorSchProcess> processList = null;
		ResDoctorSchProcess process = new ResDoctorSchProcess();
		Map<String,String> roleFlagMap = new HashMap<String, String>();
		roleFlagMap.put("roleFlag",roleFlag);
		if(GlobalConstant.RES_ROLE_SCOPE_DOCTOR.equals(roleFlag)){
			userFlow = StringUtil.defaultIfEmpty(userFlow,currUserFlow);
		}else if(GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)){
			
		}else if(GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)){
			process.setHeadUserFlow(currUserFlow);
			processList = resDoctorProcessBiz.searchProcessByDoctor(null,process,roleFlagMap);
		}else if(GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(roleFlag)){
			roleFlagMap.put("val",currUserFlow);
			processList = resDoctorProcessBiz.searchProcessByDoctor(null,process,roleFlagMap);
		}else if(GlobalConstant.RES_ROLE_SCOPE_ADMIN.equals(roleFlag)){
			roleFlagMap.put("val",GlobalContext.getCurrentUser().getOrgFlow());
			processList = resDoctorProcessBiz.searchProcessByDoctor(null,process,roleFlagMap);
		}
		
		if(processList!=null && processList.size()>0){
			List<String> userFlows = new ArrayList<String>();
			for(ResDoctorSchProcess rdsp : processList){
				String flow = rdsp.getUserFlow();
				if(!userFlows.contains(flow)){
					userFlows.add(flow);
				}
			}
			
			List<SysUser> userList = userBiz.searchSysUserByuserFlows(userFlows);
			model.addAttribute("userList",userList);
		}


		if(StringUtil.isNotBlank(userFlow)){
			PageHelper.startPage(currentPage,getPageSize(request));
			List<TestResult> resultList = resultBiz.searchResult(userFlow,result);
			model.addAttribute("resultList",resultList);
		}
		
		return "/res/doctor/userAssessList";
	}
	
	@RequestMapping(value="/getStartAndEnd")
	@ResponseBody
	public Map<String,String> getStartAndEnd(Integer weekNum){
		Map<String,String> result = null;
		if(weekNum!=null){
			String startDate = DateUtil.getYear()+"-01-01";
			if(weekNum>1){
				startDate = DateUtil.addDate(startDate,(weekNum-1)*7);
			}
			
			result = new HashMap<String, String>();
			result.put("start",startDate);
			String endDate = DateUtil.addDate(startDate,6);
			result.put("end",endDate);
		}
		return result;
	}
	
	@RequestMapping(value="/disabledDocAndUser")
	@ResponseBody
	public String disabledDocAndUser(String userFlow){
		if(StringUtil.isNotBlank(userFlow)){
			ResDoctor doc = new ResDoctor();
			SysUser user = new SysUser();
			doc.setDoctorFlow(userFlow);
			doc.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			user.setUserFlow(userFlow);
			user.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			int result = resDoctorBiz.disabledDoctorUser(doc, user);
			if(result!=GlobalConstant.ZERO_LINE){
				return GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}

	/**
	 * 新增/编辑个人轮转计划
	 * @param doctorFlow
	 * @param resultFlow
	 * @param model
     * @return
     */
	@RequestMapping(value="/editResultByStandardDept")
	public String editResultByStandardDept(String doctorFlow,String resultFlow,Model model,String standardDeptId){
		String targetPath = "/res/doctor/editResultByStandardDept";
		
		//验证参数可用性
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("error","学员标识为空！");
			return targetPath;
		}
		
		//获取医师信息
		ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
		if(doctor==null){
			model.addAttribute("error","读取学员信息失败！");
			return targetPath;
		}
		
		String rotationFlow = doctor.getRotationFlow();
		if(!StringUtil.isNotBlank(rotationFlow)){
			model.addAttribute("error","该学员还没有方案！");
			return targetPath;
		}

		String orgFlow = doctor.getSecondOrgFlow();
		if(StringUtil.isBlank(orgFlow))
		{
			orgFlow=doctor.getOrgFlow();
		}
		if(!StringUtil.isNotBlank(orgFlow)){
			model.addAttribute("error","该学员未被分配机构！");
			return targetPath;
		}

		String secondRotationFlow=doctor.getSecondRotationFlow();
		//获取所有标准科室
		List<SchRotationDept> rotationDeptList=new ArrayList<>();
		List<SchRotationDept> rotationDeptList1 = rotationDeptBiz.searchSchRotationDept(rotationFlow);
		rotationDeptList.addAll(rotationDeptList1);
		if(StringUtil.isNotBlank(secondRotationFlow)) {
			List<SchRotationDept> rotationDeptList2 = rotationDeptBiz.searchSchRotationDept(secondRotationFlow);
			rotationDeptList.addAll(rotationDeptList2);
		}
		model.addAttribute("rotationDeptList",rotationDeptList);
		
		//获取标准组
		List<SchRotationGroup> groupList=new ArrayList<>();
		List<SchRotationGroup> groupList1 = schRotationtGroupBiz.searchSchRotationGroup(rotationFlow);
		if(StringUtil.isNotBlank(secondRotationFlow)) {
			List<SchRotationGroup> groupList2 = schRotationtGroupBiz.searchSchRotationGroup(rotationFlow);
			groupList.addAll(groupList2);
		}
		groupList.addAll(groupList1);
		if(groupList!=null && !groupList.isEmpty()){
			Map<String,SchRotationGroup> groupMap = new HashMap<String, SchRotationGroup>();
			for(SchRotationGroup srg : groupList){
				String groupFlow = srg.getGroupFlow();
				groupMap.put(groupFlow,srg);
			}
			model.addAttribute("groupMap",groupMap);
		}
		
		//获取轮转科室
		List<SchDept> schDeptList = schDeptBiz.searchRelByStandard(orgFlow,standardDeptId);
		model.addAttribute("schDeptList",schDeptList);
		
		//读取计划信息
		if(StringUtil.isNotBlank(resultFlow)){
			SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(resultFlow);
			model.addAttribute("result",result);
			
			ResDoctorSchProcess process = resDoctorProcessBiz.searchByResultFlow(resultFlow);
			model.addAttribute("process",process);
		}
		
		return targetPath;
	}
	
	/**
	 * 获取带教老师和科主任
	 * @return
	 */
	@RequestMapping(value="/loadTeacherAndHead")
	@ResponseBody
	public Map<String,Object> loadTeacherAndHead(String schDeptFlow){
		Map<String,Object> data = null;
		if(StringUtil.isNotBlank(schDeptFlow)){
			data = new HashMap<String, Object>();
			SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
			if(dept!=null){
				String deptFlow = dept.getDeptFlow();
				if(GlobalConstant.FLAG_Y.equals(dept.getIsExternal())){
					SchDeptExternalRel deptExtRel = deptExtRelBiz.readSchDeptExtRelBySchDept(schDeptFlow);
					if(deptExtRel!=null){
						deptFlow = deptExtRel.getRelDeptFlow();
					}
				}
				
				if(StringUtil.isNotBlank(deptFlow)){
					String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
					if(StringUtil.isNotBlank(teacherRoleFlow)){
						List<SysUser> teacherList = userBiz.searchUserByDeptAndRole(deptFlow,teacherRoleFlow);
						data.put("teacherSeller",teacherList);
					}
					
					String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
					if(StringUtil.isNotBlank(headRoleFlow)){
						List<SysUser> headList = userBiz.searchUserByDeptAndRole(deptFlow,headRoleFlow);
						data.put("headSeller",headList);
					}
				}
			}
		}
		return data;
	}
	
	/**
	 * 保存自由添加轮转科室
	 * @return
	 */
	@RequestMapping(value="/saveResultAndProcess")
	@ResponseBody
	public String saveResultAndProcess(SchArrangeResult result,ResDoctorSchProcess process) throws ParseException {
		int operFlag = schArrangeResultBiz.editCustomResult(result,process);
		if(GlobalConstant.ZERO_LINE!=operFlag){
			return GlobalConstant.OPRE_SUCCESSED_FLAG;
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}
	
	/**
	 * 验证时间是否重叠
	 * @return
	 */
	@RequestMapping(value="/checkTheResultDate")
	@ResponseBody
	public String checkTheResultDate(String doctorFlow,String resultFlow,String startDate,String endDate){
		if(!StringUtil.isNotBlank(doctorFlow)){
			return "error:医师标识符为空！";
		}
		
		if(!StringUtil.isNotBlank(startDate)){
			return "error:开始时间为空！";
		}
		
		if(!StringUtil.isNotBlank(endDate)){
			return "error:结束时间为空！";
		}
		
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("doctorFlow",doctorFlow);
		paramMap.put("resultFlow",resultFlow);
		paramMap.put("startDate",startDate);
		paramMap.put("endDate",endDate);
		ResDoctor doctor=resDoctorBiz.readDoctor(doctorFlow);
		if(doctor!=null) {
			paramMap.put("rotationFlow", doctor.getRotationFlow());
			paramMap.put("secondRotationFlow", doctor.getSecondRotationFlow());
		}
		int result = schArrangeResultBiz.checkResultDate(paramMap);
		if(result>0){
			return GlobalConstant.FLAG_N;
		}
		
		return GlobalConstant.FLAG_Y;
	}
	
	@RequestMapping(value="/go")
	public String go(Model model,HttpServletRequest request){
		model.addAttribute("formPath","res/form/testForm");
		model.addAttribute("d",GlobalConstant.RES_ROLE_SCOPE_DOCTOR);
		model.addAttribute("t",GlobalConstant.RES_ROLE_SCOPE_TEACHER);
		model.addAttribute("h",GlobalConstant.RES_ROLE_SCOPE_HEAD);
		model.addAttribute("m",GlobalConstant.RES_ROLE_SCOPE_MANAGER);
		model.addAttribute("a",GlobalConstant.RES_ROLE_SCOPE_ADMIN);
		model.addAttribute("roleFlag",GlobalConstant.RES_ROLE_SCOPE_MANAGER);
		ResRec rec = new ResRec();
//		rec.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
		model.addAttribute("rec",rec);
		return "res/form/formMain";
	}
	@RequestMapping("/evaluate")
	public String evaluate(String lectureFlow,Model model){
		model.addAttribute("lectureFlow",lectureFlow);
		SysUser current = GlobalContext.getCurrentUser();
		String userFlow = current.getUserFlow();
		List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(userFlow,lectureFlow);
		if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0) {
			ResLectureEvaDetail resLectureEvaDetail = lectureEvaDetails.get(0);
			if(resLectureEvaDetail!=null) {
				model.addAttribute("resLectureEvaDetail", resLectureEvaDetail);
			}
		}
		return "res/doctor/addEvaluate";
	}
	@RequestMapping("/saveEvaluate")
	@ResponseBody
	public String saveEvaluate(ResLectureEvaDetail resLectureEvaDetail){
		SysUser current = GlobalContext.getCurrentUser();
		String userFlow = current.getUserFlow();
		String userName = current.getUserName();
		System.err.println("userName========"+userName);
		if(StringUtil.isNotBlank(userFlow)){
			resLectureEvaDetail.setOperUserFlow(userFlow);
		}
		if(StringUtil.isNotBlank(userName)){
			resLectureEvaDetail.setOperUserName(userName);
		}
		resLectureEvaDetailBiz.editResLectureEvaDetail(resLectureEvaDetail);
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}

	/**
	 * 退培医师管理头部标签页（退培医师审核，退培医师查询）
	 * @return
     */
	@RequestMapping(value="/backTrainManage")
	public String backTrainManage(){
		return "res/platform/doctor/backTrainManage";
	}

	/**
	 * 查询退培医师信息中待审核的医师信息(标签退培医师审核和页面查询触发此方法)
	 * @param resDocotrDelayTeturn
	 * @param model
	 * @param currentPage
	 * @param request
     * @return
     */
	@RequestMapping(value="/backTrainCheck")
	public String backTrainCheck(ResDocotrDelayTeturn resDocotrDelayTeturn,Model model,Integer currentPage, HttpServletRequest request){
		SysUser currenUser = GlobalContext.getCurrentUser();
		SysOrg currenOrg = orgBiz.readSysOrg(currenUser.getOrgFlow());
		SysOrg org = new SysOrg();
		org.setOrgProvId(currenOrg.getOrgProvId());
		org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs = orgBiz.searchOrg(org);
		List<String> orgFlowList = new ArrayList<>();
		if(orgs!=null&&orgs.size()>0){
			for (SysOrg beOrg:orgs) {
				orgFlowList.add(beOrg.getOrgFlow());
			}
		}
		model.addAttribute("orgs",orgs);
		PageHelper.startPage(currentPage,getPageSize(request));
		resDocotrDelayTeturn.setAuditStatusId(ResBaseStatusEnum.Auditing.getId());
		resDocotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
		//区分中医与西医学员
		String medicineTypeId=currenUser.getMedicineTypeId();
		//参数flags为查询通过或不通过时用
//		List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo(resDocotrDelayTeturn,orgFlowList,null,null);
		List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.backTrainCheck(resDocotrDelayTeturn,orgFlowList, null, medicineTypeId,null);
		Map<String,Object> fileMaps = new HashMap<String, Object>();
		if(resRecList!=null&&resRecList.size()>0){
			for (ResDocotrDelayTeturn back: resRecList) {
				List<PubFile> files = fileBiz.searchByProductFlow(back.getRecordFlow());
				if(files!=null&&files.size()>0){
					fileMaps.put(back.getRecordFlow(),files);
				}
			}
		}
		model.addAttribute("fileMaps",fileMaps);
		model.addAttribute("resRecList",resRecList);
		return "res/platform/doctor/backTrainCheck";
	}

	/**
	 * 在查询待审核的退培医师信息页面中点击审核按钮触发此方法
	 * 用于弹出审核窗口（同意或不同意）
	 * @param model
	 * @param recordFlow 用于追踪信息的流水号
     * @return
     */
	@RequestMapping(value="/showCheck")
	public String showCheck(Model model,String recordFlow){
		if(StringUtil.isNotBlank(recordFlow)){
			ResDocotrDelayTeturn resInfo = resDoctorDelayTeturnBiz.readInfo(recordFlow);
			model.addAttribute("resInfo",resInfo);
		}
		return "res/platform/doctor/check";
	}

	/**
	 * 审核基地提交的退培医师信息
	 * （同意或不同意为必填项）
	 * @param resDocotrDelayTeturn
	 * @return
     */
	@RequestMapping(value="/check")
	public @ResponseBody String check(ResDocotrDelayTeturn resDocotrDelayTeturn){
		ResDocotrDelayTeturn backTemp = resDoctorDelayTeturnBiz.readInfo(resDocotrDelayTeturn.getRecordFlow());
		Boolean save =false;
		//省厅同意
		if(ResBaseStatusEnum.GlobalPassed.getId().equals(resDocotrDelayTeturn.getAuditStatusId())){
			backTemp.setAuditStatusId(ResBaseStatusEnum.GlobalPassed.getId());
			backTemp.setAuditStatusName(ResBaseStatusEnum.GlobalPassed.getName());
			backTemp.setAuditOpinion(resDocotrDelayTeturn.getAuditOpinion());
			save=true;
		}
		//省厅不同意
		if(ResBaseStatusEnum.GlobalNotPassed.getId().equals(resDocotrDelayTeturn.getAuditStatusId())){
			backTemp.setAuditStatusId(ResBaseStatusEnum.GlobalNotPassed.getId());
			backTemp.setAuditStatusName(ResBaseStatusEnum.GlobalNotPassed.getName());
			backTemp.setAuditOpinion(resDocotrDelayTeturn.getAuditOpinion());
			save=false;
		}
		//填写审核人相关信息
		SysUser currUser = GlobalContext.getCurrentUser();
		backTemp.setAuditTime(DateUtil.getCurrentTime());
		backTemp.setAuditUserFlow(currUser.getUserFlow());
		backTemp.setAuditUserName(currUser.getUserName());
		resDoctorDelayTeturnBiz.save(backTemp);
		if (save) {
			//湖北住培 违约退培加入黑名单
			if(!"1".equals(backTemp.getPolicyId())){
				SysUser sysUser = userBiz.readSysUser(backTemp.getDoctorFlow());
				if(StringUtil.isBlank(sysUser.getIdNo())){
					if(StringUtil.isNotBlank(backTemp.getDoctorFlow())) {
						sysUser.setRecordStatus(GlobalConstant.FLAG_N);
						userBiz.edit(sysUser);
						ResDoctor resDoctor=doctorBiz.searchByUserFlow(backTemp.getDoctorFlow());
						if(resDoctor!=null) {
							resDoctor.setRecordStatus(GlobalConstant.FLAG_N);
							doctorBiz.editDoctor(resDoctor);
						}
					}
				}else{
					saveBlackList(sysUser.getIdNo(), backTemp.getPolicyName()+":"+backTemp.getReasonName(),sysUser.getUserName(),"自动");
				}
			}else{
				if(StringUtil.isNotBlank(backTemp.getDoctorFlow())) {
					SysUser sysUser = userBiz.readSysUser(backTemp.getDoctorFlow());
					sysUser.setRecordStatus(GlobalConstant.FLAG_N);
					userBiz.edit(sysUser);
					ResDoctor resDoctor=doctorBiz.searchByUserFlow(backTemp.getDoctorFlow());
					if(resDoctor!=null) {
						resDoctor.setRecordStatus(GlobalConstant.FLAG_N);
						doctorBiz.editDoctor(resDoctor);
					}
				}
			}
		}
		return GlobalConstant.OPERATE_SUCCESSED;
	}

	/**
	 * 导出
	 * 两个页面的导出功能共用此方法（退培医师审核，退培医师查询）
	 * @param response
	 * @param resDocotrDelayTeturn
	 * @param flag 用于区分导出的是审核前（）的信息还是审核后（Y）的信息
	 * @throws IOException
     */
	@RequestMapping(value="/exportForBack")
	public void exportForBack(HttpServletResponse response,ResDocotrDelayTeturn resDocotrDelayTeturn,String doctorCategoryId,String flag,String roleFlag) throws IOException {
		SysUser currenUser = GlobalContext.getCurrentUser();
		List<String> orgFlowList = new ArrayList<>();
		List<String> flags = new ArrayList<>();
		String workOrgId = "";
		//省厅
		if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
			//查询本省所有医院并放入orgFlowList
			SysOrg org = new SysOrg();
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId ());
			List<SysOrg> orgs = orgBiz.searchOrg(org);
			if(orgs!=null&&orgs.size()>0){
				for (SysOrg beOrg:orgs) {
					orgFlowList.add(beOrg.getOrgFlow());
				}
			}
		}
		//高校
		if(GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
			//查询本高校有医院并放入orgFlowList
			SysOrg currentOrg = orgBiz.readSysOrg(currenUser.getOrgFlow());
			workOrgId = currentOrg.getSendSchoolId();
			List<SysOrg> orgs = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			if(orgs!=null&&orgs.size()>0){
				for (SysOrg beOrg:orgs) {
					orgFlowList.add(beOrg.getOrgFlow());
				}
			}
		}


		//查询通过或不通过
		if(StringUtil.isNotBlank(flag)&&GlobalConstant.FLAG_Y.equals(flag)&&StringUtil.isBlank(resDocotrDelayTeturn.getAuditStatusId())){
			flags.add(ResBaseStatusEnum.GlobalPassed.getId());
			flags.add(ResBaseStatusEnum.GlobalNotPassed.getId());
		}else {
			//查询待审核
			resDocotrDelayTeturn.setAuditStatusId(ResBaseStatusEnum.Auditing.getId());
		}
		if(StringUtil.isNotBlank(doctorCategoryId)){
			resDocotrDelayTeturn.setTrainingTypeId(doctorCategoryId);
		}
		resDocotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
		//区分中医与西医学员
		String medicineTypeId=currenUser.getMedicineTypeId();
		//参数flags为查询通过或不通过时用
		List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.backTrainCheck(resDocotrDelayTeturn,
				orgFlowList,flags,medicineTypeId,workOrgId);
		resDoctorDelayTeturnBiz.exportForBack(resRecList,response,flag);
	}

	/**
	 * 退培医师信息查询
	 * 省厅与基地两个角色共用此方法
	 * 展示页面没有共用
	 * @param resDocotrDelayTeturn
	 * @param model
	 * @param currentPage
	 * @param request
	 * @param roleFlag （global省厅，local基地）
	 * @param jointOrg  (用于local基地角色，且当前机构为国家基地时可以查询协同机构)
     * @return
     */
	@RequestMapping(value="/backTrainInfo")
	public String backTrainInfo(ResDocotrDelayTeturn resDocotrDelayTeturn,Model model,String doctorCategoryId,Integer currentPage,
								HttpServletRequest request,String roleFlag,String jointOrg,String [] datas){
		//当前用户
		SysUser currenUser = GlobalContext.getCurrentUser();
		//当前用户所属机构
		SysOrg currenOrg = orgBiz.readSysOrg(currenUser.getOrgFlow());
		//用于存放查出的信息
		List<ResDocotrDelayTeturn> resRecList = new ArrayList<>();
		//用于存放机构流水号
		List<String> orgFlowList = new ArrayList<>();
		//省厅
		if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
			//查询本省所有医院并放入orgFlowList
			SysOrg org = new SysOrg();
			org.setOrgProvId(currenOrg.getOrgProvId());
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			List<SysOrg> orgs = orgBiz.searchOrg(org);
			if(orgs!=null&&orgs.size()>0){
				for (SysOrg beOrg:orgs) {
					orgFlowList.add(beOrg.getOrgFlow());
				}
			}
			model.addAttribute("orgs",orgs);
			PageHelper.startPage(currentPage,getPageSize(request));
			//区分中医与西医学员
			String medicineTypeId=currenUser.getMedicineTypeId();
			List<String> flags = new ArrayList<>();
			if(StringUtil.isBlank(resDocotrDelayTeturn.getAuditStatusId())){
				//当没有选择是否通过则都要查询
				flags.add(ResBaseStatusEnum.GlobalPassed.getId());
				flags.add(ResBaseStatusEnum.GlobalNotPassed.getId());
				resDocotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
				//参数flags为查询通过或不通过时用
			}else {
				//参数flags为查询通过或不通过时用
				flags = null;
			}
			if(StringUtil.isNotBlank(doctorCategoryId)){
				resDocotrDelayTeturn.setTrainingTypeId(doctorCategoryId);
			}
			resRecList = resDoctorDelayTeturnBiz.backTrainCheck(resDocotrDelayTeturn,orgFlowList,flags,medicineTypeId,null);
		}
		//高校
		if(GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
			//查询本高校有医院并放入orgFlowList
			SysUser currentUser = GlobalContext.getCurrentUser();
			SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
			String workOrgId = currentOrg.getSendSchoolId();
			List<SysOrg> orgs = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			if(orgs!=null&&orgs.size()>0){
				for (SysOrg beOrg:orgs) {
					orgFlowList.add(beOrg.getOrgFlow());
				}
			}
			model.addAttribute("orgs",orgs);
			PageHelper.startPage(currentPage,getPageSize(request));
			//区分中医与西医学员
			String medicineTypeId=currenUser.getMedicineTypeId();
			List<String> flags = new ArrayList<>();
			if(StringUtil.isBlank(resDocotrDelayTeturn.getAuditStatusId())){
				//当没有选择是否通过则都要查询
				flags.add(ResBaseStatusEnum.GlobalPassed.getId());
				flags.add(ResBaseStatusEnum.GlobalNotPassed.getId());
				resDocotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
				//参数flags为查询通过或不通过时用
			}else {
				//参数flags为查询通过或不通过时用
				flags = null;
			}
			if(StringUtil.isNotBlank(doctorCategoryId)){
				resDocotrDelayTeturn.setTrainingTypeId(doctorCategoryId);
			}
			resRecList = resDoctorDelayTeturnBiz.backTrainCheck(resDocotrDelayTeturn,orgFlowList,flags,medicineTypeId,workOrgId);
		}
		//基地管理员
		if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)){
			List<String> docTypeList = new ArrayList<>();
			String dataStr = "";
			if(datas!=null&&datas.length>0){
				docTypeList = Arrays.asList(datas);
				for(String d : datas){
					dataStr += d+",";
				}
			}
			model.addAttribute("dataStr",dataStr);
			if(OrgLevelEnum.CountryOrg.getId().equals(currenOrg.getOrgLevelId())&&jointOrg != null && jointOrg.equals("Y")){
				//查询协同基地
				List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(currenOrg.getOrgFlow());
				if(null != jointOrgs && jointOrgs.size() > 0){
					for(ResJointOrg so : jointOrgs){
						orgFlowList.add(so.getJointOrgFlow());
					}
				}
				//添加本基地
				orgFlowList.add(currenOrg.getOrgFlow());
			}else {
				//添加本基地
				orgFlowList.add(currenOrg.getOrgFlow());
			}
			resDocotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
			if(StringUtil.isNotBlank(doctorCategoryId)){
				resDocotrDelayTeturn.setTrainingTypeId(doctorCategoryId);
			}
			//参数flags为查询通过或不通过时用
			Map<String,Object> param = new HashMap<>();
			param.put("rddt",resDocotrDelayTeturn);
			param.put("orgFlowList",orgFlowList);
			param.put("docTypeList",docTypeList);
			PageHelper.startPage(currentPage,getPageSize(request));
			resRecList = resDoctorDelayTeturnBiz.searchInfo(param);
		}
		Map<String,Object> fileMaps = new HashMap<String, Object>();
		if(resRecList!=null&&resRecList.size()>0){
			for (ResDocotrDelayTeturn back: resRecList) {
				List<PubFile> files = fileBiz.searchByProductFlow(back.getRecordFlow());
				if(files!=null&&files.size()>0){
					fileMaps.put(back.getRecordFlow(),files);
				}
			}
		}
		model.addAttribute("fileMaps",fileMaps);
		model.addAttribute("resRecList",resRecList);
		if(OrgLevelEnum.CountryOrg.getId().equals(currenOrg.getOrgLevelId())){
			model.addAttribute("roleFlag",roleFlag);
		}
		if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)||GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
			model.addAttribute("roleFlag",roleFlag);
			if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
				return "res/platform/doctor/backTrainInfo4jszy";
			}
			return "res/platform/doctor/backTrainInfo";
		}
		if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)){
			if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
				return "res/platform/hospital/backTrainInfo4jszy";
			}
			return "res/platform/hospital/backTrainInfo";
		}
		return null;
	}

	/**
	 * 退培人员国家医师协会导出
	 * @param resDocotrDelayTeturn
	 * @param roleFlag
	 * @param jointOrg
	 * @param response
     * @throws Exception
     */
	@RequestMapping(value = "/exportBack")
	public void exportBack(ResDocotrDelayTeturn resDocotrDelayTeturn,String doctorCategoryId,String roleFlag,String jointOrg,HttpServletResponse response)throws Exception{
		//当前用户
		SysUser currenUser = GlobalContext.getCurrentUser();
		//当前用户所属机构
		SysOrg currenOrg = orgBiz.readSysOrg(currenUser.getOrgFlow());
		//用于存放查出的信息
		List<ResDocotrDelayTeturn> resRecList = new ArrayList<>();
		//用于存放机构流水号
		List<String> orgFlowList = new ArrayList<>();
		//基地管理员
		if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)){
			if(OrgLevelEnum.CountryOrg.getId().equals(currenOrg.getOrgLevelId())&&jointOrg != null && jointOrg.equals("Y")){
				//查询协同基地
				List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(currenOrg.getOrgFlow());
				if(null != jointOrgs && jointOrgs.size() > 0){
					for(ResJointOrg so : jointOrgs){
						orgFlowList.add(so.getJointOrgFlow());
					}
				}
				//添加本基地
				orgFlowList.add(currenOrg.getOrgFlow());
			}else {
				//添加本基地
				orgFlowList.add(currenOrg.getOrgFlow());
			}
			if(StringUtil.isNotBlank(doctorCategoryId)){
				resDocotrDelayTeturn.setTrainingTypeId(doctorCategoryId);
			}
			resDocotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
			//参数flags为查询通过或不通过时用
			resRecList = resDoctorDelayTeturnBiz.searchInfo(resDocotrDelayTeturn,orgFlowList,null,null);
		}
		List<String> doctorFlows = new ArrayList<>();
		if(resRecList!=null&&resRecList.size()>0){
			for(ResDocotrDelayTeturn tempBack : resRecList){
				if(!doctorFlows.contains(tempBack.getDoctorFlow())){
					doctorFlows.add(tempBack.getDoctorFlow());
				}
			}
		}
		List<ResDoctorExt> doctorList = resDoctorBiz.searchDoctorExt4Back(doctorFlows);
		if(doctorList != null && doctorList.size() > 0 && resRecList != null && resRecList.size() > 0){
			for(ResDoctorExt temp : doctorList){
				for(ResDocotrDelayTeturn tempBack : resRecList){
					if(temp.getDoctorFlow().equals(tempBack.getDoctorFlow())){
						temp.setOrgName(tempBack.getOrgName());
						temp.setTrainingSpeName(tempBack.getTrainingSpeName());
						temp.setSessionNumber(tempBack.getSessionNumber());
						temp.setTrainingYears(tempBack.getTrainingYears());
						continue;
					}
				}

			}
		}

		if(doctorList != null && doctorList.size()>0){
			doctorList.get(0).setIs5plus3("Back");
			resDoctorBiz.exportForDetail(doctorList,response);
		}
	}
	/**
	 * 延期医师查询
	 * 省厅、高校与基地三个角色共用此方法
	 * 展示页面没有共用
	 * @param resDocotrDelayTeturn
	 * @param model
	 * @param currentPage
	 * @param request
	 * @param jointOrg  (用于local基地角色，且当前机构为国家基地时可以查询协同机构)
	 * @param roleFlag （global省厅，local基地）
     * @return
     */
	@RequestMapping(value="/delayInfo")
	public String delayInfo(ResDocotrDelayTeturn resDocotrDelayTeturn,Model model,String doctorCategoryId,
							Integer currentPage, HttpServletRequest request,String jointOrg,String roleFlag,String [] datas){
		//当前用户
		SysUser currenUser = GlobalContext.getCurrentUser();
		//当前用户所属机构
		SysOrg currenOrg = orgBiz.readSysOrg(currenUser.getOrgFlow());
		SysOrg org = new SysOrg();
		org.setOrgProvId(currenOrg.getOrgProvId());
		org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		List<String> orgFlowList = new ArrayList<>();
		List<SysOrg> orgs = new ArrayList<>();
		String medicineTypeId="";
		//省厅
		if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
			//区分中医与西医学员
			medicineTypeId=currenUser.getMedicineTypeId();
			orgs = orgBiz.searchOrg(org);
			if(orgs!=null&&orgs.size()>0){
				for (SysOrg beOrg:orgs) {
					orgFlowList.add(beOrg.getOrgFlow());
				}
			}
			model.addAttribute("orgs",orgs);
		}
		//高校
		String workOrgId = "";
		if(GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
			//区分中医与西医学员
			medicineTypeId=currenUser.getMedicineTypeId();
			SysUser currentUser = GlobalContext.getCurrentUser();
			SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
			workOrgId = currentOrg.getSendSchoolId();
			orgs = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			if(orgs!=null&&orgs.size()>0){
				for (SysOrg beOrg:orgs) {
					orgFlowList.add(beOrg.getOrgFlow());
				}
			}
			model.addAttribute("orgs",orgs);
		}
		//基地管理员
		List<String> docTypeList = new ArrayList<>();
		String dataStr = "";
		if(datas!=null&&datas.length>0){
			docTypeList = Arrays.asList(datas);
			for(String d : datas){
				dataStr += d+",";
			}
		}
		model.addAttribute("dataStr",dataStr);
		if(StringUtil.isNotBlank(doctorCategoryId)){
			resDocotrDelayTeturn.setTrainingTypeId(doctorCategoryId);
		}
		if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)){

			if(OrgLevelEnum.CountryOrg.getId().equals(currenOrg.getOrgLevelId())&&jointOrg != null && jointOrg.equals("Y")){
				//查询协同基地
				List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(currenOrg.getOrgFlow());
				if(null != jointOrgs && jointOrgs.size() > 0){
					for(ResJointOrg so : jointOrgs){
						orgFlowList.add(so.getJointOrgFlow());
					}
				}
				//添加本基地
				orgFlowList.add(currenOrg.getOrgFlow());
			}else {
				//添加本基地
				orgFlowList.add(currenOrg.getOrgFlow());
			}
		}
		resDocotrDelayTeturn.setTypeId(ResRecTypeEnum.Delay.getId());
		Map<String,Object> param = new HashMap<>();
		param.put("b",resDocotrDelayTeturn);
		param.put("orgFlowList",orgFlowList);
		param.put("medicineTypeId",medicineTypeId);
		param.put("docTypeList",docTypeList);
		param.put("workOrgId",workOrgId);
		PageHelper.startPage(currentPage,getPageSize(request));
//		List<ResDocotrDelayTeturn> resRecList = new ArrayList<>();
//		//参数flags为查询通过或不通过时用
//		List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.backTrainCheck(resDocotrDelayTeturn,orgFlowList, null, medicineTypeId);
		List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.backTrainCheck(param);

		Map<String,Object> fileMaps = new HashMap<String, Object>();
		if(resRecList!=null&&resRecList.size()>0){
			for (ResDocotrDelayTeturn back: resRecList) {
				List<PubFile> files = fileBiz.searchByProductFlow(back.getRecordFlow());
				if(files!=null&&files.size()>0){
					fileMaps.put(back.getRecordFlow(),files);
				}
			}
		}
		model.addAttribute("fileMaps",fileMaps);
		model.addAttribute("resRecList", resRecList);
		if(OrgLevelEnum.CountryOrg.getId().equals(currenOrg.getOrgLevelId())){
			model.addAttribute("roleFlag",roleFlag);
		}
		if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)||GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
			model.addAttribute("roleFlag",roleFlag);
			if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
				return "res/platform/doctor/delayInfo4jszy";
			}
			return "res/platform/doctor/delayInfo";
		}
		if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)){
			model.addAttribute("role","hospital");
			if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
				return "res/platform/hospital/delayInfo4jszy";
			}
			return "res/platform/hospital/delayInfo";
		}
		return null;
	}
	@RequestMapping(value="/exportDelayInfo")
	public void exportDelayInfo(ResDocotrDelayTeturn resDocotrDelayTeturn,Model model,String doctorCategoryId,HttpServletRequest request,
								  HttpServletResponse response, String jointOrg,String roleFlag,String [] datas) throws Exception {
		//当前用户
		SysUser currenUser = GlobalContext.getCurrentUser();
		//当前用户所属机构
		SysOrg currenOrg = orgBiz.readSysOrg(currenUser.getOrgFlow());
		SysOrg org = new SysOrg();
		org.setOrgProvId(currenOrg.getOrgProvId());
		org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		List<String> orgFlowList = new ArrayList<>();
		List<SysOrg> orgs = new ArrayList<>();
		String medicineTypeId="";
		//省厅
		if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){
			//区分中医与西医学员
			medicineTypeId=currenUser.getMedicineTypeId();
			orgs = orgBiz.searchOrg(org);
			if(orgs!=null&&orgs.size()>0){
				for (SysOrg beOrg:orgs) {
					orgFlowList.add(beOrg.getOrgFlow());
				}
			}
			model.addAttribute("orgs",orgs);
		}
		//高校
		String workOrgId = "";
		if(GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
			//区分中医与西医学员
			medicineTypeId=currenUser.getMedicineTypeId();
			SysUser currentUser = GlobalContext.getCurrentUser();
			SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
			workOrgId = currentOrg.getSendSchoolId();
			orgs = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			if(orgs!=null&&orgs.size()>0){
				for (SysOrg beOrg:orgs) {
					orgFlowList.add(beOrg.getOrgFlow());
				}
			}
			model.addAttribute("orgs",orgs);
		}
		//基地管理员
		List<String> docTypeList = new ArrayList<>();
		if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)){
			String dataStr = "";
			if(datas!=null&&datas.length>0){
				docTypeList = Arrays.asList(datas);
				for(String d : datas){
					dataStr += d+",";
				}
			}
			model.addAttribute("dataStr",dataStr);
			if(OrgLevelEnum.CountryOrg.getId().equals(currenOrg.getOrgLevelId())&&jointOrg != null && jointOrg.equals("Y")){
				//查询协同基地
				List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(currenOrg.getOrgFlow());
				if(null != jointOrgs && jointOrgs.size() > 0){
					for(ResJointOrg so : jointOrgs){
						orgFlowList.add(so.getJointOrgFlow());
					}
				}
				//添加本基地
				orgFlowList.add(currenOrg.getOrgFlow());
			}else {
				//添加本基地
				orgFlowList.add(currenOrg.getOrgFlow());
			}
			if(StringUtil.isNotBlank(doctorCategoryId)){
				resDocotrDelayTeturn.setTrainingTypeId(doctorCategoryId);
			}
		}
		resDocotrDelayTeturn.setTypeId(ResRecTypeEnum.Delay.getId());
		Map<String,Object> param = new HashMap<>();
		param.put("b",resDocotrDelayTeturn);
		param.put("orgFlowList",orgFlowList);
		param.put("medicineTypeId",medicineTypeId);
		param.put("docTypeList",docTypeList);
		param.put("workOrgId",workOrgId);
		List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.backTrainCheck(param);

		String fileName = "延期学员统计.xls";
		String titles[] = {
				"orgName:培训基地",
				"doctorName:学员姓名",
				"trainingTypeName:培训类别",
				"trainingSpeName:专业",
				"sessionNumber:年级",
				"graduationYear:结业考核年份",
				"delayreason:延期原因"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, resRecList, response.getOutputStream());
	}
	/**
	 * 湖北过程省厅首页
	 * @param model
	 * @return
     */
	@RequestMapping(value="globalMain")
	public String globalMain(Model model){
		if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
			return "redirect:/res/teacher/searchRegisterInfo/charge?isCurrentFlag=Y&onlyOrg=Y&orgFlow=7e4eb4aa1b1b45469b74b5a8ecfcec02&orgName=Y";
		}
//		if("nfykdx".equals(InitConfig.getSysCfg("xjgl_customer"))){
//			return "center";//南方医科大学过程平台首页暂换成空白页
//		}
		//人员类型（单位人、行业人、在校专硕）
		List<String> doctorTypeList = new ArrayList();
		//医师状态（在培、结业）注：将终止状态禁用
		List<String> doctorStatusList = new ArrayList();
		List<SysDict> doctorTypeDicts = DictTypeEnum.DoctorType.getSysDictList();
		List<SysDict> doctorStatusDicts = DictTypeEnum.DoctorStatus.getSysDictList();

        if(null!=doctorTypeDicts && doctorTypeDicts.size()>0){
            for (SysDict dict:doctorTypeDicts) {
                doctorTypeList.add(dict.getDictId());
            }
        }

		if(null!=doctorStatusDicts && doctorStatusDicts.size()>0){
            for (SysDict dict:doctorStatusDicts) {
                doctorStatusList.add(dict.getDictId());
            }
        }

		//存放当前住培情况和医师信息概况所需要的数据
		Map<String,Map> doctorCountExtMap = new HashMap<>();
		//记录每年各人员类型各医师状态对应的人数
		List<Map<String,String>> doctorCountList = new ArrayList<>();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		for( int i=0;i<3;i++){
			String sessionNummber =DateUtil.getYear();
			sessionNummber=Integer.parseInt(sessionNummber)- i +"";
			paramMap.put("sessionNumber", sessionNummber);
			//人员类型（单位人、行业人、在校专硕）
			paramMap.put("doctorTypeList", doctorTypeList);
			//医师状态（在培、结业）注：将终止状态禁用
			paramMap.put("doctorStatusList",doctorStatusList);
			doctorCountList = doctorRecruitBiz.statisticDoctorCountMap(paramMap);
			//将doctorCountList转换成Map
			Map<String,String> doctorCountMap = new HashMap<>();
			if(doctorCountList!=null&&doctorCountList.size()>0){
				for(int j=0;j<doctorCountList.size();j++){
					doctorCountMap.put(doctorCountList.get(j).get("DOCKEY"),doctorCountList.get(j).get("COUNTNUM"));
				}
			}
			//按照年份把对应数据存入用于人员信息概况
			doctorCountExtMap.put(sessionNummber+"pl",doctorCountMap);
		}
		//当前住培情况
		List<Map<String,Object>> currDocDetails = new ArrayList<>();
		Map<String,String> currDocSumBef2014 = new HashMap<>();
		paramMap = new HashMap<>();
		paramMap.put("doctorStatusList",doctorStatusList);
		paramMap.put("doctorTypeList", doctorTypeList);
		currDocDetails = doctorRecruitBiz.getCurrDocDetails(paramMap);
//		currDocDetails数据格式为：
//		NUM    STATUSID    SESSIONNUMBER
//		1        20           2016
//		1        21           2016
//		1        20           2015
//		1        21           2015
//		1        20           2014
//		1        21           2014
//		需要currDocDetailMaps格式为：
//		SESSIONNUMBER    20    21
//			2016          1     1
//			2015          1     1
//			2014          1     1
//		作如下转换：
		int _20count=0;//用于统计该类医师总数量饼状图中需要
		int _21count=0;//用于统计该类医师总数量饼状图中需要
		List<Map<String,Object>> currDocDetailMaps = new ArrayList<>();
		Map<String,Object> currDocDetailMap = new HashMap<String, Object>();
		String year ="";
		for (Map<String,Object> demap:currDocDetails) {
			//按照当前人员类型ID，取得对应人员数量
			if(doctorStatusList.get(0).equals(demap.get("STATUSID"))){
				_20count+=Integer.parseInt(demap.get("NUM").toString());
			}else if(doctorStatusList.get(1).equals(demap.get("STATUSID"))){
				_21count+=Integer.parseInt(demap.get("NUM").toString());
			}
			//判断本条数据的年份与上一条是否重复，如果不同则需重新new一条map，如果重复则需将该数据继续加入上一条map中
			if(!year.equals(demap.get("SESSIONNUMBER"))){
				year=demap.get("SESSIONNUMBER").toString();
				currDocDetailMap=new HashMap<>();
				currDocDetailMap.put("SESSIONNUMBER",demap.get("SESSIONNUMBER"));
			}
			if(doctorStatusList.get(0).equals(demap.get("STATUSID"))){
				currDocDetailMap.put(doctorStatusList.get(0),demap.get("NUM"));
			}else if(doctorStatusList.get(1).equals(demap.get("STATUSID"))){
				currDocDetailMap.put(doctorStatusList.get(1),demap.get("NUM"));
			}
			//判断currDocDetailMaps中是否有与当前数据年份重复的currDocDetailMap如果有则移除原有的currDocDetailMap，然后重新加入。用于去掉重复数据
			if(currDocDetailMaps!=null&&currDocDetailMaps.size()>0){
				for(Iterator<Map<String,Object>> it = currDocDetailMaps.iterator();it.hasNext();){
					Map<String,Object> bmap = it.next();
					if(year.equals(bmap.get("SESSIONNUMBER"))){
						it.remove();
					}
				}
			}
			currDocDetailMaps.add(currDocDetailMap);
		}
		currDocSumBef2014.put("_20count",_20count+"");
		currDocSumBef2014.put("_21count",_21count+"");
		//查询当前年及下一年需要参加结业考核的医师数
		paramMap = new HashMap<>();
		List<String> yearList = new ArrayList();
		yearList.add(DateUtil.getYear());
		yearList.add(Integer.parseInt(DateUtil.getYear())+1+"");
		paramMap.put("doctorStatusList",doctorStatusList.get(0));
		paramMap.put("doctorTypeList", doctorTypeList);
		paramMap.put("yearList",yearList);
		List<Map<String,Object>> checkDocs = doctorRecruitBiz.getcheckDocs(paramMap);
		model.addAttribute("currDocDetailMaps", currDocDetailMaps);
		model.addAttribute("currDocSumBef2014", currDocSumBef2014);
		model.addAttribute("doctorCountExtMap", doctorCountExtMap);
		model.addAttribute("checkDocs",checkDocs);
		return "res/platform/globalIndex";
	}
	//湖北查询基地信息概况
	@RequestMapping(value="searchDoctorByJd")
	public String searchDoctorByJd(Model model,String sessionNumber,String graduationNumber,String trainingSpeId,String doctorStatusId){
		Map<String, Object> paramMap=new HashMap();
		if (StringUtil.isNotBlank(sessionNumber)) {
			paramMap.put("sessionNumber",sessionNumber);
		}
		if (StringUtil.isNotBlank(graduationNumber)) {
			paramMap.put("graduationNumber",graduationNumber);
		}
		if (StringUtil.isNotBlank(trainingSpeId)) {
			paramMap.put("trainingSpeId",trainingSpeId);
		}
		if (StringUtil.isNotBlank(doctorStatusId)) {
			paramMap.put("doctorStatusId",doctorStatusId);
		}
		//人员类型（单位人、行业人、在校专硕）
		List<String> doctorTypeList = new ArrayList();
		//医师状态（在培、结业）注：将终止状态禁用
		List<String> doctorStatusList = new ArrayList();
		List<SysDict> doctorTypeDicts = DictTypeEnum.DoctorType.getSysDictList();
		List<SysDict> doctorStatusDicts = DictTypeEnum.DoctorStatus.getSysDictList();
		for (SysDict dict:doctorTypeDicts) {
			doctorTypeList.add(dict.getDictId());
		}
		for (SysDict dict:doctorStatusDicts) {
			doctorStatusList.add(dict.getDictId());
		}
		paramMap.put("doctorStatusList",doctorStatusList);
		paramMap.put("doctorTypeList", doctorTypeList);
		List<ResDoctor> jdDoctors =  doctorRecruitBiz.searchDoctorByJd(paramMap);
		List<Map<String,Object>> jdDoctorMaps = new ArrayList<>();
		Map<String,Object> jdDoctorMap = new HashMap<>();
		String orgName = "";
		Integer sumCount = 0;
		if(jdDoctors!=null&&jdDoctors.size()>0){
			for(ResDoctor doc : jdDoctors){
				//判断本条数据的年份与上一条是否重复，如果不同则需重新new一条map，如果重复则需将该数据继续加入上一条map中
				if(!orgName.equals(doc.getOrgName())){
					sumCount=0;
					orgName=doc.getOrgName();
					jdDoctorMap=new HashMap<>();
					jdDoctorMap.put("orgName",doc.getOrgName());
				}
				//按照当前人员类型ID，取得对应人员数量
				if(doctorTypeDicts.get(0).getDictId().equals(doc.getDoctorTypeId())){
					jdDoctorMap.put(doctorTypeDicts.get(0).getDictId(),doc.getEmergencyName());
				}else if(doctorTypeDicts.get(1).getDictId().equals(doc.getDoctorTypeId())){
					jdDoctorMap.put(doctorTypeDicts.get(1).getDictId(),doc.getEmergencyName());
				}else if(doctorTypeDicts.get(2).getDictId().equals(doc.getDoctorTypeId())){
					jdDoctorMap.put(doctorTypeDicts.get(2).getDictId(),doc.getEmergencyName());
				}else if(doctorTypeDicts.get(3).getDictId().equals(doc.getDoctorTypeId())){
					jdDoctorMap.put(doctorTypeDicts.get(3).getDictId(),doc.getEmergencyName());
				}
				sumCount += Integer.parseInt(doc.getEmergencyName());
				jdDoctorMap.put("sumCount",sumCount+"");
				//判断currDocDetailMaps中是否有与当前数据年份重复的currDocDetailMap如果有则移除原有的currDocDetailMap，然后重新加入。用于去掉重复数据
				if(jdDoctorMaps!=null&&jdDoctorMaps.size()>0){
					for(Iterator<Map<String,Object>> it = jdDoctorMaps.iterator();it.hasNext();){
						Map<String,Object> bmap = it.next();
						if(orgName.equals(bmap.get("orgName"))){
							it.remove();
						}
					}
				}
				jdDoctorMaps.add(jdDoctorMap);
			}
		}
		model.addAttribute("jdDoctorMaps",jdDoctorMaps);
		model.addAttribute("doctorTypeDicts",doctorTypeDicts);
		model.addAttribute("seeFlag","searchDoctorByJd");
		return "res/platform/globalDocList";
	}
	//湖北查询专业信息概况
	@RequestMapping(value="searchDoctorBySpe")
	public String searchDoctorBySpe(Model model,String sessionNumber,String graduationNumber,String orgFlow,String doctorStatusId){
		Map<String, Object> paramMap=new HashMap();
		if (StringUtil.isNotBlank(sessionNumber)) {
			paramMap.put("sessionNumber",sessionNumber);
		}
		if (StringUtil.isNotBlank(graduationNumber)) {
			paramMap.put("graduationNumber",graduationNumber);
		}
		if (StringUtil.isNotBlank(orgFlow)) {
			paramMap.put("orgFlow",orgFlow);
		}
		if (StringUtil.isNotBlank(doctorStatusId)) {
			paramMap.put("doctorStatusId",doctorStatusId);
		}
		//人员类型（单位人、行业人、在校专硕）
		List<String> doctorTypeList = new ArrayList();
		//医师状态（在培、结业）注：将终止状态禁用
		List<String> doctorStatusList = new ArrayList();
		List<SysDict> doctorTypeDicts = DictTypeEnum.DoctorType.getSysDictList();
		List<SysDict> doctorStatusDicts = DictTypeEnum.DoctorStatus.getSysDictList();
		for (SysDict dict:doctorTypeDicts) {
			doctorTypeList.add(dict.getDictId());
		}
		for (SysDict dict:doctorStatusDicts) {
			doctorStatusList.add(dict.getDictId());
		}
		paramMap.put("doctorStatusList",doctorStatusList);
		paramMap.put("doctorTypeList", doctorTypeList);
		List<ResDoctor> jdDoctors =  doctorRecruitBiz.searchDoctorBySpe(paramMap);
		List<Map<String,Object>> jdDoctorMaps = new ArrayList<>();
		Map<String,Object> jdDoctorMap = new HashMap<>();
		String speName = "";
		Integer sumCount = 0;
		for(ResDoctor doc : jdDoctors){
			//判断本条数据的年份与上一条是否重复，如果不同则需重新new一条map，如果重复则需将该数据继续加入上一条map中
			if(!speName.equals(doc.getTrainingSpeName())){
				sumCount=0;
				speName=doc.getTrainingSpeName();
				jdDoctorMap=new HashMap<>();
				jdDoctorMap.put("speName",doc.getTrainingSpeName());
			}
			//按照当前人员类型ID，取得对应人员数量
			if(doctorTypeDicts.get(0).getDictId().equals(doc.getDoctorTypeId())){
				jdDoctorMap.put(doctorTypeDicts.get(0).getDictId(),doc.getEmergencyName());
			}else if(doctorTypeDicts.get(1).getDictId().equals(doc.getDoctorTypeId())){
				jdDoctorMap.put(doctorTypeDicts.get(1).getDictId(),doc.getEmergencyName());
			}else if(doctorTypeDicts.get(2).getDictId().equals(doc.getDoctorTypeId())){
				jdDoctorMap.put(doctorTypeDicts.get(2).getDictId(),doc.getEmergencyName());
			}else if(doctorTypeDicts.get(3).getDictId().equals(doc.getDoctorTypeId())){
				jdDoctorMap.put(doctorTypeDicts.get(3).getDictId(),doc.getEmergencyName());
			}
			sumCount += Integer.parseInt(doc.getEmergencyName());
			jdDoctorMap.put("sumCount",sumCount+"");
			//判断currDocDetailMaps中是否有与当前数据年份重复的currDocDetailMap如果有则移除原有的currDocDetailMap，然后重新加入。用于去掉重复数据
			if(jdDoctorMaps!=null&&jdDoctorMaps.size()>0){
				for(Iterator<Map<String,Object>> it = jdDoctorMaps.iterator();it.hasNext();){
					Map<String,Object> bmap = it.next();
					if(speName.equals(bmap.get("speName"))){
						it.remove();
					}
				}
			}
			jdDoctorMaps.add(jdDoctorMap);
		}
		model.addAttribute("jdDoctorMaps",jdDoctorMaps);
		model.addAttribute("doctorTypeDicts",doctorTypeDicts);
		model.addAttribute("seeFlag","searchDoctorBySpe");
		return "res/platform/globalDocList";
	}

	/**
	 * 导出
	 * @param sessionNumber
	 * @param graduationNumber
	 * @param trainingSpeId 基地信息概况
	 * @param orgFlow 专业信息概况
	 * @param doctorStatusId
	 * @param response
	 * @param flag hospitalSearch： 基地信息概况  speSearch： 专业信息概况
     * @throws IOException
     */
	@RequestMapping(value="/exportForHbGlobal")
	public void exportForHbGlobal(String sessionNumber,String graduationNumber,String trainingSpeId,String orgFlow,String doctorStatusId,HttpServletResponse response,String flag) throws IOException {
		Map<String, Object> paramMap=new HashMap();
		if (StringUtil.isNotBlank(sessionNumber)) {
			paramMap.put("sessionNumber",sessionNumber);
		}
		if (StringUtil.isNotBlank(graduationNumber)) {
			paramMap.put("graduationNumber",graduationNumber);
		}
		if (StringUtil.isNotBlank(trainingSpeId)) {
			paramMap.put("trainingSpeId",trainingSpeId);
		}
		if (StringUtil.isNotBlank(doctorStatusId)) {
			paramMap.put("doctorStatusId",doctorStatusId);
		}
		if (StringUtil.isNotBlank(orgFlow)) {
			paramMap.put("orgFlow",orgFlow);
		}
		//人员类型（单位人、行业人、在校专硕）
		List<String> doctorTypeList = new ArrayList();
		//医师状态（在培、结业）注：将终止状态禁用
		List<String> doctorStatusList = new ArrayList();
		List<SysDict> doctorTypeDicts = DictTypeEnum.DoctorType.getSysDictList();
		List<SysDict> doctorStatusDicts = DictTypeEnum.DoctorStatus.getSysDictList();
		for (SysDict dict:doctorTypeDicts) {
			doctorTypeList.add(dict.getDictId());
		}
		for (SysDict dict:doctorStatusDicts) {
			doctorStatusList.add(dict.getDictId());
		}
		paramMap.put("doctorStatusList",doctorStatusList);
		paramMap.put("doctorTypeList", doctorTypeList);
		List<Map<String,Object>> jdDoctorMaps = new ArrayList<>();
		Map<String,Object> jdDoctorMap = new HashMap<>();
		if("hospitalSearch".equals(flag)){
			List<ResDoctor> jdDoctors =  doctorRecruitBiz.searchDoctorByJd(paramMap);
			String orgName = "";
			Integer sumCount = 0;
			if(jdDoctors!=null&&jdDoctors.size()>0){
				for(ResDoctor doc : jdDoctors){
					//判断本条数据的年份与上一条是否重复，如果不同则需重新new一条map，如果重复则需将该数据继续加入上一条map中
					if(!orgName.equals(doc.getOrgName())){
						sumCount=0;
						orgName=doc.getOrgName();
						jdDoctorMap=new HashMap<>();
						jdDoctorMap.put("orgName",doc.getOrgName());
					}
					//按照当前人员类型ID，取得对应人员数量
					if(doctorTypeDicts.get(0).getDictId().equals(doc.getDoctorTypeId())){
						jdDoctorMap.put(doctorTypeDicts.get(0).getDictId(),doc.getEmergencyName());
					}else if(doctorTypeDicts.get(1).getDictId().equals(doc.getDoctorTypeId())){
						jdDoctorMap.put(doctorTypeDicts.get(1).getDictId(),doc.getEmergencyName());
					}else if(doctorTypeDicts.get(2).getDictId().equals(doc.getDoctorTypeId())){
						jdDoctorMap.put(doctorTypeDicts.get(2).getDictId(),doc.getEmergencyName());
					}else if(doctorTypeDicts.get(3).getDictId().equals(doc.getDoctorTypeId())){
						jdDoctorMap.put(doctorTypeDicts.get(3).getDictId(),doc.getEmergencyName());
					}
					sumCount += Integer.parseInt(doc.getEmergencyName());
					jdDoctorMap.put("sumCount",sumCount+"");
					//判断currDocDetailMaps中是否有与当前数据年份重复的currDocDetailMap如果有则移除原有的currDocDetailMap，然后重新加入。用于去掉重复数据
					if(jdDoctorMaps!=null&&jdDoctorMaps.size()>0){
						for(Iterator<Map<String,Object>> it = jdDoctorMaps.iterator();it.hasNext();){
							Map<String,Object> bmap = it.next();
							if(orgName.equals(bmap.get("orgName"))){
								it.remove();
							}
						}
					}
					jdDoctorMaps.add(jdDoctorMap);
				}
			}
		}
		if("speSearch".equals(flag)){
			List<ResDoctor> jdDoctors =  doctorRecruitBiz.searchDoctorBySpe(paramMap);
			String speName = "";
			Integer sumCount = 0;
			for(ResDoctor doc : jdDoctors){
				//判断本条数据的年份与上一条是否重复，如果不同则需重新new一条map，如果重复则需将该数据继续加入上一条map中
				if(!speName.equals(doc.getTrainingSpeName())){
					sumCount=0;
					speName=doc.getTrainingSpeName();
					jdDoctorMap=new HashMap<>();
					jdDoctorMap.put("speName",doc.getTrainingSpeName());
				}
				//按照当前人员类型ID，取得对应人员数量
				if(doctorTypeDicts.get(0).getDictId().equals(doc.getDoctorTypeId())){
					jdDoctorMap.put(doctorTypeDicts.get(0).getDictId(),doc.getEmergencyName());
				}else if(doctorTypeDicts.get(1).getDictId().equals(doc.getDoctorTypeId())){
					jdDoctorMap.put(doctorTypeDicts.get(1).getDictId(),doc.getEmergencyName());
				}else if(doctorTypeDicts.get(2).getDictId().equals(doc.getDoctorTypeId())){
					jdDoctorMap.put(doctorTypeDicts.get(2).getDictId(),doc.getEmergencyName());
				}else if(doctorTypeDicts.get(3).getDictId().equals(doc.getDoctorTypeId())){
					jdDoctorMap.put(doctorTypeDicts.get(3).getDictId(),doc.getEmergencyName());
				}
				sumCount += Integer.parseInt(doc.getEmergencyName());
				jdDoctorMap.put("sumCount",sumCount+"");
				//判断currDocDetailMaps中是否有与当前数据年份重复的currDocDetailMap如果有则移除原有的currDocDetailMap，然后重新加入。用于去掉重复数据
				if(jdDoctorMaps!=null&&jdDoctorMaps.size()>0){
					for(Iterator<Map<String,Object>> it = jdDoctorMaps.iterator();it.hasNext();){
						Map<String,Object> bmap = it.next();
						if(speName.equals(bmap.get("speName"))){
							it.remove();
						}
					}
				}
				jdDoctorMaps.add(jdDoctorMap);
			}
		}
		doctorRecruitBiz.exportForHbGlobal(jdDoctorMaps,response,doctorTypeDicts,flag);
	}

	/**
	 * 黑名单查询
	 */
	@RequestMapping(value="/blackListInfo")
	public String blackListInfo(String roleFlag,JsresUserBalcklist jsresUserBalcklist, Model model, String sessionNumber,HttpServletRequest request, Integer currentPage,String seeFlag,String jointOrg){
		List<String> userFlowList = new ArrayList<String>();
		SysUser user = GlobalContext.getCurrentUser();
		if(StringUtil.isNotBlank(sessionNumber)){
			jsresUserBalcklist.setSessionNumber(sessionNumber);
		}
		if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag) && StringUtil.isBlank(seeFlag)) {
			jsresUserBalcklist.setOrgFlow(user.getOrgFlow());
		}
		List<SysOrg> orgs=new ArrayList<SysOrg>();
		if (GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
			SysOrg s=orgBiz.readSysOrg(user.getOrgFlow());
			SysOrg org=new SysOrg();
			org.setOrgProvId(s.getOrgProvId());//省
			if(GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
				org.setOrgCityId(s.getOrgCityId());//市
			}
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			orgs=orgBiz.searchAllSysOrg(org);
			model.addAttribute("orgs", orgs);
		}
		List<String> orgFlowList=new ArrayList<String>();
		if(StringUtil.isBlank(jsresUserBalcklist.getOrgFlow())){
			if(orgs!=null&&!orgs.isEmpty()){
				for(SysOrg org:orgs){
					orgFlowList.add(org.getOrgFlow());
				}
			}
		}
		SysOrg org =orgBiz.readSysOrg(user.getOrgFlow());
		if(null != org && null != org.getOrgLevelId() && org.getOrgLevelId().equals("CountryOrg")){
			model.addAttribute("countryOrgFlag","Y");
			if(null != jointOrg && jointOrg.equals("checked")){
				orgFlowList.add(jsresUserBalcklist.getOrgFlow());
				jsresUserBalcklist.setOrgFlow("");
				List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
				if(null != jointOrgs && jointOrgs.size() > 0){
					for(ResJointOrg so : jointOrgs){
						orgFlowList.add(so.getJointOrgFlow());
					}
				}
			}
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsresUserBalcklist> blackLists = blackBiz.searchInfo(jsresUserBalcklist, userFlowList, orgFlowList);
		model.addAttribute("blackLists", blackLists);
		return "res/platform/hospital/blackListInfo";
	}

	/**
	 * 删除黑名单
	 */
	@RequestMapping(value = "/removeBlack")
	public @ResponseBody String removeBlack(JsresUserBalcklist jsresUserBalcklist) {
		blackBiz.saveBlack(jsresUserBalcklist);
		if(jsresUserBalcklist.getRecordStatus().equals(GlobalConstant.FLAG_N))
		{
			String userFlow=jsresUserBalcklist.getUserFlow();
			if(StringUtil.isNotBlank(userFlow))
			{
				SysUser sysUser=userBiz.readSysUser(userFlow);
				if(sysUser!=null)
				{
					sysUser.setRecordStatus(GlobalConstant.FLAG_Y);
					userBiz.edit(sysUser);
				}
				ResDoctor resDoctor=doctorBiz.searchByUserFlow(userFlow);
				if(resDoctor!=null)
				{
					resDoctor.setRecordStatus(GlobalConstant.FLAG_Y);
					doctorBiz.editDoctor(resDoctor);
				}
			}
		}
		return GlobalConstant.OPERATE_SUCCESSED;
	}

	/**
	 * 保存黑名单
	 * flag为操作类型标识
	 */
	@RequestMapping(value={"/saveBlackList"})
	public @ResponseBody String saveBlackList(String userIdNo, String reason,String userName,String flag){
			SysUser sysUser = null;
			JsresUserBalcklist blackUser =null;
			if(StringUtil.isNotBlank(userIdNo)) {
				sysUser = userBiz.findByIdNo(userIdNo);
				blackUser = blackBiz.searchInfoByIdNo(userIdNo);
			}
			if (blackUser == null) {
				blackUser = new JsresUserBalcklist();
			}
			if (sysUser == null) {
				sysUser = new SysUser();
				sysUser.setIdNo(userIdNo);
				sysUser.setUserName(userName);
				return "该学员证件号不存在！";
			}
			int count = doSave(sysUser, reason, blackUser, flag);
			if (sysUser != null && count == 1) {
				String userFlow = sysUser.getUserFlow();
				if (StringUtil.isNotBlank(userFlow)) {
					sysUser.setRecordStatus(GlobalConstant.FLAG_N);
					userBiz.edit(sysUser);
					ResDoctor resDoctor = doctorBiz.searchByUserFlow(userFlow);
					if (resDoctor != null) {
						resDoctor.setRecordStatus(GlobalConstant.FLAG_N);
						doctorBiz.editDoctor(resDoctor);
					}
				}
			}
		if(count==1) {
			return GlobalConstant.OPERATE_SUCCESSED;
		}else{
			return GlobalConstant.OPRE_FAIL;
		}
	}
	private int doSave(SysUser sysUser,String reason,JsresUserBalcklist black,String flag){
		ResDoctor doctor = new ResDoctor();
		doctor = doctorBiz.findByFlow(sysUser.getUserFlow());
		black.setUserFlow(sysUser.getUserFlow());
		black.setUserCode(sysUser.getUserCode());
		black.setUserName(sysUser.getUserName());
		black.setUserPhone(sysUser.getUserPhone());
		black.setUserEmail(sysUser.getUserEmail());
		if(doctor!=null) {
			black.setOrgFlow(doctor.getOrgFlow());
			black.setOrgName(doctor.getOrgName());
			black.setSessionNumber(doctor.getSessionNumber());
			black.setTrainingSpeId(doctor.getTrainingSpeId());
			black.setTrainingSpeName(doctor.getTrainingSpeName());
		}
		black.setIdNo(sysUser.getIdNo());
		black.setReason(reason);
		black.setOperTypeId("自动".equals(flag)?"1":"2");
		black.setOperTypeName("自动".equals(flag)?flag:"手动");
		black.setReasonYj("您的信息已被纳入我省医务人员诚信系统，5年内不得进入我省培训基地接受住院医师规范化培训。如有相关疑问，请与相关管理部门联系。");
		black.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		return blackBiz.saveBlack(black);
	}
}
