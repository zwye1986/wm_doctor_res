package com.pinde.sci.ctrl.res;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.Docx4jUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jszy.IJszyDoctorAuthBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sch.ISchDoctorAbsenceBiz;
import com.pinde.sci.biz.sch.ISchRotationDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.ResScoreMapper;
import com.pinde.sci.enums.res.JszyTCMPracticEnum;
import com.pinde.sci.enums.res.Rec360TypeEnum;
import com.pinde.sci.enums.res.RecSkillTypeEnum;
import com.pinde.sci.enums.res.ResRecTypeEnum;
import com.pinde.sci.model.mo.*;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/res/fengxianPj")
public class ResFengXianPjController extends GeneralController {
	final static String JXCF = "1";
	final static String YN = "2";
	final static String WZBLTL = "3";
	final static String XSJZ = "4";
	final static String SWBLTL = "5";
	private static Logger logger = LoggerFactory.getLogger(ResFengXianPjController.class);
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResEvaluationCfgBiz evaluationCfgBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private IJszyDoctorAuthBiz doctorAuthBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private IResAssessCfgBiz assessCfgBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IResPowerCfgBiz resPowerCfgBiz;
	@Autowired
	private IResDoctorProcessBiz resDoctorProcessBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResScoreBiz scoreBiz;
	@Autowired
	private ISchDoctorAbsenceBiz doctorAbsenceBiz;
	@Autowired
	private IResGradeBiz resGradeBiz;
	@Autowired
	private ResScoreMapper resScoreMapper;
	@Autowired
	private IExamResultsBiz resultsBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;
	@Autowired
	private IFileBiz fileBiz;
	/**
	 * 学员评估带教/科室列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/doctorProcessList/{roleFlag}"},method={RequestMethod.GET,RequestMethod.POST})
	public String doctorProcessList(@PathVariable String roleFlag, String recTypeId, Integer currentPage, HttpServletRequest request,  Model model){
		List<ResDoctorSchProcess> dataList=new ArrayList<>();
		PageHelper.startPage(currentPage,getPageSize(request));
		if("doctor".equals(roleFlag)){
			dataList=resDoctorProcessBiz.searchProcessByDoctor(GlobalContext.getCurrentUser().getUserFlow());
		}
		if("head".equals(roleFlag)){

		}
		if("teacher".equals(roleFlag)){

		}
		if("manager".equals(roleFlag)){

		}

		if(dataList!=null && dataList.size()>0){
			model.addAttribute("processList",dataList);
			List<SchArrangeResult> resultList = new ArrayList<SchArrangeResult>();
			Map<String,String> finishPreMap = new HashMap<String, String>();
			Map<String,DeptTeacherGradeInfo> gradeInfoMap = new HashMap<String, DeptTeacherGradeInfo>();
			List<String> resultFlows = new ArrayList<String>();

			List<String> userFlows = new ArrayList<String>();
			List<String> processFlows = new ArrayList<String>();
			for(ResDoctorSchProcess processTemp : dataList){
				String resultFlow = processTemp.getSchResultFlow();
				if(!userFlows.contains(processTemp.getUserFlow())){
					userFlows.add(processTemp.getUserFlow());
				}
				if(!processFlows.contains(processTemp.getProcessFlow())){
					processFlows.add(processTemp.getProcessFlow());
				}
				resultFlows.add(resultFlow);
				if(GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)){
					resultList.clear();
					SchArrangeResult result = resultBiz.readSchArrangeResult(resultFlow);
					if(result!=null){
						resultList.add(result);
						Map<String,String> finishPre = resRecBiz.getFinishPer(resultList,processTemp.getUserFlow());
						if(finishPre!=null){
							finishPreMap.put(processTemp.getUserFlow(),finishPre.get(resultFlow));
						}
					}
				}
				//评价带教老师,评价科室
				List<String> recTypeIds = new ArrayList<String>();
				recTypeIds.add(recTypeId);
				Map<String, Object> paramMap = new HashMap();
				paramMap.put("recTypeIds", recTypeIds);
				paramMap.put("processFlow", processTemp.getProcessFlow());
				paramMap.put("currentUserFlow", processTemp.getUserFlow());
				List<DeptTeacherGradeInfo> gradeInfoList = resGradeBiz.searchResGradeByItems(paramMap);
				if (gradeInfoList != null && gradeInfoList.size() > 0) {
					gradeInfoMap.put(processTemp.getUserFlow()+processTemp.getProcessFlow()+recTypeId, gradeInfoList.get(0));
				}
			}
			model.addAttribute("finishPreMap",finishPreMap);
			model.addAttribute("gradeInfoMap",gradeInfoMap);

			List<ResDoctor> doctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
			if(doctorList!=null && doctorList.size()>0){
				Map<String,ResDoctor> doctorMap = new HashMap<String,ResDoctor>();
				for(ResDoctor doctor : doctorList){
					SysUser user = userBiz.readSysUser(doctor.getDoctorFlow());
					doctor.setDoctorName(user.getUserName());
					doctorMap.put(doctor.getDoctorFlow(),doctor);
				}
				model.addAttribute("doctorMap",doctorMap);

				//用户信息
				List<SysUser> userList = userBiz.searchSysUserByuserFlows(userFlows);
				if(userList!=null && userList.size()>0){
					Map<String,SysUser> userMap = new HashMap<String, SysUser>();
					for(SysUser user : userList){
						userMap.put(user.getUserFlow(),user);
					}
					model.addAttribute("userMap",userMap);
				}

				List<SchArrangeResult> searchReqResultList = resultBiz.searchArrangeResultByResultFlow(resultFlows);
				if(searchReqResultList!=null && searchReqResultList.size()>0){
					Map<String,SchArrangeResult> resultMap = new HashMap<String, SchArrangeResult>();
					Map<String,Map<String,String>> finishMap = new HashMap<String, Map<String,String>>();
					List<String> rotationFlows = new ArrayList<String>();
					List<String> standardDeptId = new ArrayList<String>();
					for(SchArrangeResult result : searchReqResultList){
						if(StringUtil.isNotBlank(result.getRotationFlow())){
							rotationFlows.add(result.getRotationFlow());
						}
						standardDeptId.add(StringUtil.defaultIfEmpty(result.getStandardDeptId(),""));
						resultMap.put(result.getResultFlow(),result);
						resultList.clear();
						resultList.add(result);
						Map<String,String> finishPre = resRecBiz.getFinishPer(resultList,result.getDoctorFlow());
						finishMap.put(result.getResultFlow(),finishPre);
					}
					model.addAttribute("resultMap",resultMap);
					model.addAttribute("finishMap",finishMap);

				}
			}
		}
		model.addAttribute("recTypeId", recTypeId);
		model.addAttribute("roleFlag", roleFlag);
		return "/res/fengxianPj/doctorProcessList";
	}

	/**
	 * 360考核带教/科秘评估学员
	 * @param roleFlag
	 * @param model
	 * @param doctor
	 * @param process
	 * @return
	 */
	@RequestMapping(value="/khpj360Audit/{roleFlag}",method={RequestMethod.GET,RequestMethod.POST})
	public String khpj360Audit(@PathVariable String roleFlag,Model model,ResDoctor doctor,ResDoctorSchProcess process, String recTypeId,Integer currentPage, HttpServletRequest request,String [] datas,String dshStatus){
		String dataStr = "";
		List<String> docTypeList = new ArrayList<>();
		if(datas!=null&&datas.length>0){
			docTypeList = Arrays.asList(datas);
			for(String d : datas){
				dataStr += d+",";
			}
		}
		model.addAttribute("dataStr",dataStr);
		model.addAttribute("roleFlag",roleFlag);
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		if(currentPage==null){
			currentPage=1;
		}
		Map<String,String> roleFlagMap = new HashMap<String, String>();
		roleFlagMap.put("roleFlag",roleFlag);
		if(GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)){
			process.setTeacherUserFlow(userFlow);
		}else if(GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)){
			roleFlagMap.put("val",userFlow);
		}else if(GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(roleFlag)){
			roleFlagMap.put("val",userFlow);
		}else if(GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE.equals(roleFlag)){
			roleFlagMap.put("val",userFlow);
		}else if(GlobalConstant.RES_ROLE_SCOPE_ADMIN.equals(roleFlag)){
			roleFlagMap.put("val",GlobalContext.getCurrentUser().getOrgFlow());
		}

		List<String> recTypeIds = new ArrayList<String>();
		List<String> recTypeIds2 = new ArrayList<String>();
		List<String> recTypeIds3 = new ArrayList<String>();
		List<String> recTypeIds4 = new ArrayList<String>();

		if(GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)){
			recTypeIds.add(Rec360TypeEnum.Teacher_360.getId());
			recTypeIds2.add(Rec360TypeEnum.Teacher_360.getId());
			recTypeIds.add(Rec360TypeEnum.TeacherGrade.getId());
			recTypeIds2.add(Rec360TypeEnum.TeacherGrade.getId());
		}else{
			recTypeIds.add(Rec360TypeEnum.Teacher_360.getId());
			recTypeIds2.add(Rec360TypeEnum.Teacher_360.getId());
			recTypeIds.add(Rec360TypeEnum.Paramedic_360.getId());
			recTypeIds2.add(Rec360TypeEnum.Paramedic_360.getId());
			recTypeIds.add(Rec360TypeEnum.Patient_360.getId());
			recTypeIds2.add(Rec360TypeEnum.Patient_360.getId());
			recTypeIds.add(Rec360TypeEnum.TeacherGrade.getId());
			recTypeIds2.add(Rec360TypeEnum.TeacherGrade.getId());
			recTypeIds.add(Rec360TypeEnum.DeptGrade.getId());
			recTypeIds2.add(Rec360TypeEnum.DeptGrade.getId());
		}
		if(StringUtil.isNotBlank(recTypeId)){
			recTypeIds = new ArrayList<String>();
			recTypeIds2 = new ArrayList<String>();
			recTypeIds.add(recTypeId);
			recTypeIds2.add(recTypeId);
			model.addAttribute("recTypeId",recTypeId);
		}
		//权限期间是否开通
		String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
		List<ResDoctorSchProcess> processList = null;
		Map<String,Object> param = new HashMap<>();
		param.put("isOpen",isOpen);
		param.put("doctor",doctor);
		param.put("process",process);
		param.put("roleFlagMap",roleFlagMap);
		param.put("docTypeList",docTypeList);
		param.put("dshStatus",dshStatus);
		param.put("recTypeIds",recTypeIds);
		param.put("recTypeIds3",recTypeIds3);
		param.put("recTypeIds4",recTypeIds4);
		PageHelper.startPage(currentPage, getPageSize(request));
		processList = resDoctorProcessBiz.searchProcessByDoctorNew(param);

		if(processList!=null && processList.size()>0){
			model.addAttribute("processList",processList);
			List<String> userFlows = new ArrayList<String>();
			Map<String,SchArrangeResult> resultMapMap = new HashMap<String, SchArrangeResult>();
			Map<String,PubFile> fileMap = new HashMap<String, PubFile>();
			for(ResDoctorSchProcess rdsp : processList){
				if(!userFlows.contains(rdsp.getUserFlow()))
					userFlows.add(rdsp.getUserFlow());

				String resultFlow = rdsp.getSchResultFlow();
				SchArrangeResult result = resultBiz.readSchArrangeResult(resultFlow);
				resultMapMap.put(rdsp.getProcessFlow(), result);
				if (result != null) {
					PubFile file = fileBiz.readFile(result.getAfterFileFlow());
					fileMap.put(rdsp.getProcessFlow(), file);
				}
			}

			model.addAttribute("resultMap",resultMapMap);
			model.addAttribute("fileMap",fileMap);

			List<SysUser> userList = userBiz.searchSysUserByuserFlows(userFlows);
			if(userList!=null && userList.size()>0){
				Map<String,SysUser> userMap = new HashMap<String, SysUser>();
				for(SysUser su : userList){
					userMap.put(su.getUserFlow(),su);
				}
				model.addAttribute("userMap",userMap);
			}

			List<ResDoctor> doctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
			if(doctorList!=null && doctorList.size()>0){
				Map<String,ResDoctor> doctorMap = new HashMap<String, ResDoctor>();
				for(ResDoctor rd : doctorList){
					doctorMap.put(rd.getDoctorFlow(),rd);
				}
				model.addAttribute("doctorMap",doctorMap);
			}
		}

		if(recTypeIds2.size()>0){
			List<DeptTeacherGradeInfo> recList = resGradeBiz.searchEvalFormGradeInfo(process,null,recTypeIds2,roleFlagMap);
			if(recList!=null && recList.size()>0){
				Map<String,DeptTeacherGradeInfo> recMap = new HashMap<String, DeptTeacherGradeInfo>();
				for(DeptTeacherGradeInfo rec : recList){
					String key = rec.getProcessFlow()+rec.getRecTypeId();
					recMap.put(key,rec);
				}
				model.addAttribute("recMap",recMap);
			}
		}
		return "/res/fengxianPj/360PjAuditList";
	}
	/**
	 * 获取360评价表单
	 * @param recFlow 登记表流水号
	 * @param recTypeId 登记类型
	 * @param rotationFlow 轮转方案流水号
	 * @param schDeptFlow 轮转科室流水号
	 * @param operUserFlow
	 * @param roleFlag
	 * @param resultFlow 轮转计划流水号
	 * @param processFlow 轮转进度流水号
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/show360EvlForm",method={RequestMethod.GET,RequestMethod.POST})
	public String show360EvlForm(String recFlow, String recTypeId, String rotationFlow, String schDeptFlow,
								 String operUserFlow, String roleFlag, String resultFlow, String processFlow, Model model){
		model.addAttribute("roleFlag", roleFlag);
		//查询轮转进度
		ResDoctorSchProcess process = null;
		if(StringUtil.isNotBlank(processFlow)){
			process = resDoctorProcessBiz.read(processFlow);
		}
		String medicineTypeId = "";
		//根据传入用户或当前用户获取医师信息和用户信息
		operUserFlow = StringUtil.defaultIfEmpty(operUserFlow, GlobalContext.getCurrentUser().getUserFlow());
		if(StringUtil.isNotBlank(operUserFlow)){
			ResDoctor doctor  = doctorBiz.readDoctor(operUserFlow);
			model.addAttribute("doctor", doctor);

			String cfg13=InitConfig.getSysCfg("jswjw_"+doctor.getOrgFlow()+"_P013");
			model.addAttribute("cfg13",cfg13);

			SysUser operUser  = userBiz.readSysUser(operUserFlow);
			if(operUser!=null)
				medicineTypeId=operUser.getMedicineTypeId();
			model.addAttribute("operUser",operUser);
		}
		//查询轮转科室信息
		if(StringUtil.isNotBlank(schDeptFlow)){
			SchDept schDept = schDeptBiz.readSchDept(schDeptFlow);
			model.addAttribute("schDept",schDept);
		}
		//查询排班信息
		SchArrangeResult result = null;
		if(StringUtil.isNotBlank(resultFlow)){
			result = resultBiz.readSchArrangeResult(resultFlow);
			model.addAttribute("sresult",result);
			model.addAttribute("arrangeResult", result);
			model.addAttribute("result", result);
			if(process==null){
				//根据轮转计划流水号查询轮转进度
				process = resDoctorProcessBiz.searchByResultFlow(resultFlow);
			}
		}
		model.addAttribute("doctorSchProcess", process);
		model.addAttribute("currRegProcess", process);

		DeptTeacherGradeInfo express = null;
		//根据登记表流水号获取登记表信息
		if(StringUtil.isNotBlank(recFlow)){
			express = resGradeBiz.readResGrade(recFlow);
			if(express!=null){
				recTypeId = express.getRecTypeId();
			}
		}
		if(express!=null){
			model.addAttribute("rec",express);
			if(!StringUtil.isNotBlank(rotationFlow)){
				ResDoctor doctor = resDoctorBiz.readDoctor(express.getOperUserFlow());
				rotationFlow = doctor.getRotationFlow();
			}
			if(ResRecTypeEnum.AfterEvaluation.getId().equals(recTypeId)){
				Map<String,Integer> recFinishMap = new HashMap<String, Integer>();
				List<String> recTypeIds = new ArrayList<String>();
				recTypeIds.add(ResRecTypeEnum.CaseRegistry.getId());
				recTypeIds.add(ResRecTypeEnum.DiseaseRegistry.getId());
				recTypeIds.add(ResRecTypeEnum.OperationRegistry.getId());
				recTypeIds.add(ResRecTypeEnum.SkillRegistry.getId());
				List<ResRec> recList = resRecBiz.searchFinishRec(recTypeIds,express.getOperUserFlow());
//				List<ResSchProcessExpress> expressList = expressBiz.searchFinishRec(recTypeIds,express.getOperUserFlow());
				if(recList!=null && recList.size()>0){
					for(ResRec recTemp : recList){
						String recTypeIdTemp = recTemp.getRecTypeId();
						if(recFinishMap.get(recTypeIdTemp)==null){
							recFinishMap.put(recTypeIdTemp,1);
						}else{
							recFinishMap.put(recTypeIdTemp,recFinishMap.get(recTypeIdTemp)+1);
						}
					}
				}
				List<SchRotationDeptReq> deptReqList = schRotationDeptBiz.searchDeptReq(rotationFlow,schDeptFlow);
				if(deptReqList!=null && deptReqList.size()>0){
					for(SchRotationDeptReq deptReq : deptReqList){
						if(recFinishMap.get(deptReq.getRecTypeId()+"req")==null){
							recFinishMap.put(deptReq.getRecTypeId()+"req",(deptReq.getReqNum().intValue()));
						}else{
							recFinishMap.put(deptReq.getRecTypeId()+"req",recFinishMap.get(deptReq.getRecTypeId()+"req")+(deptReq.getReqNum().intValue()));
						}
					}
				}
				model.addAttribute("recFinishMap",recFinishMap);
			}
		}
		if(result!=null){
			List<SchRotationDeptReq> deptReqList = schRotationDeptBiz.searchStandardReqByResult(result, recTypeId);
			model.addAttribute("deptReqList",deptReqList);
		}
		String currVer = null;
		String recForm = null;
		if(express!=null){
			currVer = express.getRecVersion();
			String recContent = express.getRecContent();
			recForm = express.getRecForm();
			//是否是实习生的六个表单
			boolean isSix = ResRecTypeEnum.Ethics.getId().equals(recTypeId) || ResRecTypeEnum.Document.getId().equals(recTypeId)
					|| ResRecTypeEnum.NursingSkills.getId().equals(recTypeId) || ResRecTypeEnum.ClinicalEnglish.getId().equals(recTypeId)
					|| ResRecTypeEnum.Appraisal.getId().equals(recTypeId) || ResRecTypeEnum.CourseScore.getId().equals(recTypeId);
			if(isSix) {
				Map<String, Object> formDataMap = null;
				formDataMap = resRecBiz.parseContent(recContent);
				model.addAttribute("formDataMap", formDataMap);
			}else{
				Map<String,Object> formDataMap = null;
				formDataMap = resRecBiz.parseRecContent(recContent);
				model.addAttribute("formDataMap", formDataMap);
			}
			operUserFlow = express.getOperUserFlow();
		}
		model.addAttribute("formFileName", recTypeId);
		rotationFlow = "";
		if(StringUtil.isNotBlank(operUserFlow)){
			ResDoctor doctor = doctorBiz.readDoctor(operUserFlow);
			if(doctor!=null){
				rotationFlow = doctor.getRotationFlow();
			}
		}
		//查询当前用户计划内轮转科室
		List<SchDept> deptList = schDeptBiz.searchrotationDept(operUserFlow);
		model.addAttribute("deptList",deptList);
		model.addAttribute("process",process);
		SchRotationDept dept=schRotationDeptBiz.readStandardRotationDept(resultFlow);
		String type="";
		String reocrdFlow="";
		if(dept!=null)
		{
			if(JszyTCMPracticEnum.BasicPractice.getId().equals(dept.getPracticOrTheory()))
			{
				type="B";
			}
			reocrdFlow=dept.getRecordFlow();
		}
		String jspPath = resRecBiz.getFormPath(rotationFlow,recTypeId,currVer,recForm,type,medicineTypeId,reocrdFlow);
		model.addAttribute("jspPath", jspPath);
		return jspPath;
	}

	/**
	 * 保存360评价表单
	 * @param formFileName 登记类型
	 * @param recFlow 登记表流水号
	 * @param schDeptFlow 轮转科室流水号
	 * @param operUserFlow
	 * @param roleFlag
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/save360EvlForm",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String save360EvlForm(String formFileName,String recFlow,String schDeptFlow,String operUserFlow,String roleFlag,HttpServletRequest req){
		operUserFlow = StringUtil.defaultIfEmpty(operUserFlow,GlobalContext.getCurrentUser().getUserFlow());
		String rotationFlow = "";
		String orgFlow = "";
		//校验上传文件大小及格式
		String checkResult=checkFiles(req);
		if(!"1".equals(checkResult))
		{
			return checkResult;
		}
		ResDoctor doctor = doctorBiz.readDoctor(operUserFlow);
		if(doctor!=null){
			rotationFlow = doctor.getRotationFlow();
			orgFlow = doctor.getOrgFlow();
		}
		SysUser user=userBiz.readSysUser(operUserFlow);
		int result = this.resGradeBiz.saveEvaluateForm(formFileName,recFlow,schDeptFlow,rotationFlow,req,orgFlow,user.getMedicineTypeId());
		if(result != GlobalConstant.ZERO_LINE){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	public String checkFiles(HttpServletRequest request) {
		String result="1";
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//判断 request 是否有文件上传,即多部分请求
		if(multipartResolver.isMultipart(request)){
			List<String> fileSuffix=new ArrayList<>();
			fileSuffix.add(".DOC");
			fileSuffix.add(".DOCX");
			fileSuffix.add(".PPT");
			fileSuffix.add(".PDF");
			fileSuffix.add(".JPG");
			fileSuffix.add(".PNG");
			//转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			//取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while(iter.hasNext()){
				//记录上传过程起始时的时间，用来计算上传时间
				//int pre = (int) System.currentTimeMillis();
				//取得上传文件
				List<MultipartFile> files = multiRequest.getFiles(iter.next());
				if(files != null&&files.size()>0){
					for(MultipartFile file:files) {
						if(!file.isEmpty()) {
							//取得当前上传文件的文件名称
							String fileName = file.getOriginalFilename();
							String suffix = fileName.substring(fileName.lastIndexOf(".")).toUpperCase();
							if (!fileSuffix.contains(suffix)) {
								return "附件【" + fileName + "】的文件格式不正确，只能上传pdf,word,jpg,png格式的文件。";
							}
							if (file.getSize() > 10 * 1024 * 1024) {
								return "附件【" + fileName + "】的大小超过10M，不得保存";
							}
						}
					}
				}
			}
		}
		return result;
	}
	/**
	 * skillAuditList
	 * @param roleFlag
	 * @param model
	 * @param doctor
	 * @param process
	 * @return
	 */
	@RequestMapping(value="/skillAuditList/{roleFlag}",method={RequestMethod.GET,RequestMethod.POST})
	public String skillAuditList(@PathVariable String roleFlag,Model model,ResDoctor doctor,ResDoctorSchProcess process, String recTypeId,String recForm,Integer currentPage, HttpServletRequest request,String [] datas,String dshStatus){
		String dataStr = "";
		List<String> docTypeList = new ArrayList<>();
		if(datas!=null&&datas.length>0){
			docTypeList = Arrays.asList(datas);
			for(String d : datas){
				dataStr += d+",";
			}
		}
		model.addAttribute("dataStr",dataStr);
		model.addAttribute("roleFlag",roleFlag);
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		if(currentPage==null){
			currentPage=1;
		}
		Map<String,String> roleFlagMap = new HashMap<String, String>();
		roleFlagMap.put("roleFlag",roleFlag);
		if(GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)){
			process.setTeacherUserFlow(userFlow);
		}else if(GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)){
			roleFlagMap.put("val",userFlow);
		}else if(GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(roleFlag)){
			roleFlagMap.put("val",userFlow);
		}else if(GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE.equals(roleFlag)){
			roleFlagMap.put("val",userFlow);
		}else if(GlobalConstant.RES_ROLE_SCOPE_ADMIN.equals(roleFlag)){
			roleFlagMap.put("val",GlobalContext.getCurrentUser().getOrgFlow());
		}

		List<String> recTypeIds = new ArrayList<String>();
		List<String> recTypeIds2 = new ArrayList<String>();
		List<String> recTypeIds3 = new ArrayList<String>();
		List<String> recTypeIds4 = new ArrayList<String>();
		if(StringUtil.isNotBlank(recForm)){
			for (RecSkillTypeEnum en:RecSkillTypeEnum.values()) {
				if(recForm.equals(en.getType())){
					recTypeIds.add(en.getId());
					recTypeIds2.add(en.getId());
				}
			}
			model.addAttribute("recForm",recForm);
		}
		if(StringUtil.isNotBlank(recTypeId)){
			recTypeIds = new ArrayList<String>();
			recTypeIds2 = new ArrayList<String>();
			recTypeIds.add(recTypeId);
			recTypeIds2.add(recTypeId);
			model.addAttribute("recTypeId",recTypeId);
		}
		//权限期间是否开通
		String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
		List<ResDoctorSchProcess> processList = null;
		Map<String,Object> param = new HashMap<>();
		param.put("isOpen",isOpen);
		param.put("doctor",doctor);
		param.put("process",process);
		param.put("roleFlagMap",roleFlagMap);
		param.put("docTypeList",docTypeList);
		param.put("dshStatus",dshStatus);
		param.put("recTypeIds",recTypeIds);
		param.put("recTypeIds3",recTypeIds3);
		param.put("recTypeIds4",recTypeIds4);
		PageHelper.startPage(currentPage, getPageSize(request));
		processList = resDoctorProcessBiz.searchProcessByDoctorNew(param);

		if(processList!=null && processList.size()>0){
			model.addAttribute("processList",processList);
			List<String> userFlows = new ArrayList<String>();
			Map<String,SchArrangeResult> resultMapMap = new HashMap<String, SchArrangeResult>();
			Map<String,PubFile> fileMap = new HashMap<String, PubFile>();
			for(ResDoctorSchProcess rdsp : processList){
				if(!userFlows.contains(rdsp.getUserFlow()))
					userFlows.add(rdsp.getUserFlow());

				String resultFlow = rdsp.getSchResultFlow();
				SchArrangeResult result = resultBiz.readSchArrangeResult(resultFlow);
				resultMapMap.put(rdsp.getProcessFlow(), result);
				if (result != null) {
					PubFile file = fileBiz.readFile(result.getAfterFileFlow());
					fileMap.put(rdsp.getProcessFlow(), file);
				}
			}

			model.addAttribute("resultMap",resultMapMap);
			model.addAttribute("fileMap",fileMap);

			List<SysUser> userList = userBiz.searchSysUserByuserFlows(userFlows);
			if(userList!=null && userList.size()>0){
				Map<String,SysUser> userMap = new HashMap<String, SysUser>();
				for(SysUser su : userList){
					userMap.put(su.getUserFlow(),su);
				}
				model.addAttribute("userMap",userMap);
			}

			List<ResDoctor> doctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
			if(doctorList!=null && doctorList.size()>0){
				Map<String,ResDoctor> doctorMap = new HashMap<String, ResDoctor>();
				for(ResDoctor rd : doctorList){
					doctorMap.put(rd.getDoctorFlow(),rd);
				}
				model.addAttribute("doctorMap",doctorMap);
			}
		}

		if(recTypeIds2.size()>0){
			List<DeptTeacherGradeInfo> recList = resGradeBiz.searchEvalFormGradeInfo(process,null,recTypeIds2,roleFlagMap);
			if(recList!=null && recList.size()>0){
				Map<String,DeptTeacherGradeInfo> recMap = new HashMap<String, DeptTeacherGradeInfo>();
				for(DeptTeacherGradeInfo rec : recList){
					String key = rec.getProcessFlow()+rec.getRecTypeId();
					recMap.put(key,rec);
				}
				model.addAttribute("recMap",recMap);
			}
		}
		return "/res/fengxianPj/skillAuditList";
	}
	/**
	 * skillEvalList
	 * @param roleFlag
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/pjDetails",method={RequestMethod.GET,RequestMethod.POST})
	public String pjDetails(String operUserFlow,String roleFlag,Model model,String processFlow,String recForm,String schDeptFlow,Integer currentPage, HttpServletRequest request){
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		if(currentPage==null){
			currentPage=1;
		}
		Map<String, Object> itemsMap=new HashMap<>();
		itemsMap.put("processFlow",processFlow);
		itemsMap.put("recFormId",recForm);
		if(GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)){
			itemsMap.put("createUserFlow",userFlow);
		}else if(GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)){
			itemsMap.put("createUserFlow",userFlow);
		}
//		else if(GlobalConstant.RES_ROLE_SCOPE_ADMIN.equals(roleFlag)){
//			itemsMap.put("recForm",recForm);
//		}
		List<DeptTeacherGradeInfo> dataList=resGradeBiz.searchResGradeByItems(itemsMap);
		model.addAttribute("roleFlag",roleFlag);
		model.addAttribute("recForm",recForm);
		model.addAttribute("processFlow",processFlow);
		model.addAttribute("operUserFlow",operUserFlow);
		model.addAttribute("schDeptFlow",schDeptFlow);
		model.addAttribute("dataList",dataList);
		return "/res/fengxianPj/pjDetails";
	}

	@RequestMapping("/printForm")
	public void printForm(String recFlow,HttpServletRequest request, HttpServletResponse response)throws Exception{
		String recTypeId="";
		//根据登记表流水号获取登记表信息
		DeptTeacherGradeInfo express = null;
		if(StringUtil.isNotBlank(recFlow)){
			express = resGradeBiz.readResGrade(recFlow);
			if(express!=null){
				recTypeId = express.getRecTypeId();
			}
		}
		if(express!=null){
			String recContent = express.getRecContent();
			Map<String,Object> formDataMap = null;
			formDataMap = resRecBiz.parseRecContent(recContent);
			List<WordprocessingMLPackage> addTemplates = new ArrayList<WordprocessingMLPackage>();
			WordprocessingMLPackage temeplete1 = new WordprocessingMLPackage();
			String path1 = "";
			if(StringUtil.isNotBlank(recTypeId)){
				path1 = "/jsp/res/fengxianPj/template/"+recTypeId+".docx";
			}
			ServletContext context = request.getServletContext();
			String watermark = "";
			temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path1)), formDataMap, watermark, true);
			addTemplates.add(temeplete1);

			WordprocessingMLPackage temeplete = Docx4jUtil.mergeDocx(addTemplates);
			if(temeplete!=null){
				String name = RecSkillTypeEnum.getNameById(recTypeId)+".docx";
				response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
				response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
				ServletOutputStream out = response.getOutputStream ();
				(new SaveToZipFile (temeplete)).save (out);
				out.flush ();
			}
		}
	}
}