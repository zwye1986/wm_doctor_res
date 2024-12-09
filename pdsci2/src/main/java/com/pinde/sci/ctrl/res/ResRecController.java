package com.pinde.sci.ctrl.res;

import com.pinde.core.common.enums.*;
import com.pinde.core.jspform.ItemGroupData;
import com.pinde.core.util.DateUtil;
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
import com.pinde.sci.common.*;
import com.pinde.sci.dao.base.ResScoreMapper;
import com.pinde.sci.form.res.ResAssessCfgItemForm;
import com.pinde.sci.form.res.ResAssessCfgTitleForm;
import com.pinde.sci.form.res.ResEvaluationCfgItemForm;
import com.pinde.sci.form.res.ResEvaluationCfgTitleForm;
import com.pinde.sci.model.mo.*;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.dom4j.*;
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
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;
import java.util.Map.Entry;
@Controller
@RequestMapping("/res/rec")
public class ResRecController extends GeneralController {
	final static String JXCF = "1";
	final static String YN = "2";
	final static String WZBLTL = "3";
	final static String XSJZ = "4";
	final static String SWBLTL = "5";
	private static Logger logger = LoggerFactory.getLogger(ResRecController.class);
	@Autowired
	private IFileBiz pubFileBiz;
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
	private ISchArrangeResultBiz schArrangeResultBiz;
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
	
	@RequestMapping(value="/upload")
	public String upload(String recordFlow,String userFlow,String hideApprove,Model model) throws DocumentException{
//		ResRec resRec=new ResRec();
		ResSchProcessExpress express = new ResSchProcessExpress();
		SysUser user=new SysUser();
		if (StringUtil.isNotBlank(userFlow)) {
			user=userBiz.readSysUser(userFlow);
		}else{
			user=GlobalContext.getCurrentUser();
		}
        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId();
        String recTypeName = com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getName();
//		ResRec rec= resRecBiz.queryResRec(recordFlow,user.getUserFlow(),recTypeId);
		ResSchProcessExpress rec = expressBiz.queryResRec(recordFlow,user.getUserFlow(),recTypeId);
		if (rec==null) {
			express.setOrgFlow(user.getOrgFlow());
			express.setOrgName(user.getOrgName());
			express.setRecTypeId(recTypeId);
			express.setRecTypeName(recTypeName);
			String year=DateUtil.getCurrDateTime("yyyyMMddHHmmssSSS");
			express.setOperTime(year);
			express.setOperUserFlow(user.getUserFlow());
			express.setOperUserName(user.getUserName());
			express.setSchRotationDeptFlow(recordFlow);
			Document dom = DocumentHelper.createDocument();
			Element root= dom.addElement("AfterSummary");
			express.setRecContent(root.asXML());
			expressBiz.edit(express);
//			rec= resRecBiz.querySun(recordFlow,user.getUserFlow(),recTypeId);
			rec= express;
		}
		String content=rec.getRecContent();
		List<Map<String, String>> imageList=resRecBiz.parseImageXml(content);
		model.addAttribute("rec", rec);
		model.addAttribute("hideApprove", hideApprove);
		model.addAttribute("imageList", imageList);
		return "jsres/doctor/uploadCkkhb";
	}

	@RequestMapping(value="/resRecImg")
	@ResponseBody
	public Map<String,String> resRecImg(String recFlow,MultipartFile checkFile){
		Map<String, String> map = null;
		SysUser user=GlobalContext.getCurrentUser();
        String resRecType = com.pinde.core.common.enums.ResRecTypeEnum.DoctorAuth.getId();
//		List<ResRec> resRec=resRecBiz.searchByRecWithBLOBs(resRecType,user.getUserFlow());
		List<DoctorAuth> resRec=doctorAuthBiz.searchAuthsByOperUserFlow(user.getUserFlow());
		if(checkFile!=null && checkFile.getSize() > 0){
			String fileAddress="jsresImages";
			map=doctorAuthBiz.saveInfo(resRec.get(0).getRecordFlow(),checkFile,fileAddress);
		}
		return map;
	}

	@RequestMapping(value="/resRecImgUpload")
	@ResponseBody
	public Map<String,String> resRecImgUpload(String recFlow,MultipartFile checkFile){
		Map<String, String> map = null;
		if(checkFile!=null && checkFile.getSize() > 0){
			String fileAddress="summary";
			map=resRecBiz.resRecImg(recFlow,checkFile,fileAddress);
		}
		return map;
	}

	@RequestMapping(value="/uploads")
	@ResponseBody
	public Map<String,String> uploads(MultipartFile checkFile){
		Map<String, String> map = null;
		if(checkFile!=null && checkFile.getSize() > 0){
			String fileAddress="formFile";
			map=resRecBiz.upload(checkFile,fileAddress);
		}
		return map;
	}

	@RequestMapping(value="/formFileUpload")
	@ResponseBody
	public Map<String,String> formFileUpload(String pathKey,MultipartFile formFile){
		Map<String, String> map = null;
		if(formFile!=null && formFile.getSize() > 0){
			pathKey = StringUtil.defaultIfEmpty(pathKey,"formFile");
			map=resRecBiz.upload(formFile,pathKey);
		}
		return map;
	}

	@RequestMapping(value="/auditTheForm")
	@ResponseBody
	public String auditTheForm(ResRec rec){
		if(rec!=null){
			String auditUserFlow = GlobalContext.getCurrentUser().getUserFlow();//审核人flow
			String auditUserName = GlobalContext.getCurrentUser().getUserName();//审核人name
			String currTime = DateUtil.getCurrDateTime();//审核时间

			String statusId = rec.getAuditStatusId();//审核状态
			if(StringUtil.isNotBlank(statusId)){//是否是带教老师审核
				rec.setAuditStatusName(RecStatusEnum.getNameById(statusId));
				rec.setAuditUserFlow(auditUserFlow);
				rec.setAuditUserName(auditUserName);
				rec.setAuditTime(currTime);
			}

			statusId = rec.getHeadAuditStatusId();
			if(StringUtil.isNotBlank(statusId)){//是否是科主任审核
				rec.setHeadAuditStatusName(RecStatusEnum.getNameById(statusId));
				rec.setHeadAuditUserFlow(auditUserFlow);
				rec.setHeadAuditUserName(auditUserName);
				rec.setHeadAuditTime(currTime);
			}

			statusId = rec.getManagerAuditStatusId();
			if(StringUtil.isNotBlank(statusId)){//是否是基地主任审核
				rec.setManagerAuditStatusName(RecStatusEnum.getNameById(statusId));
				rec.setManagerAuditUserFlow(auditUserFlow);
				rec.setManagerAuditUserName(auditUserName);
				rec.setManagerAuditTime(currTime);
			}

			statusId = rec.getAdminAuditStatusId();//审核状态
			if(StringUtil.isNotBlank(statusId)){//是否是医院管理员审核
				rec.setAdminAuditStatusName(RecStatusEnum.getNameById(statusId));
				rec.setAdminAuditUserFlow(auditUserFlow);
				rec.setAdminAuditUserName(auditUserName);
				rec.setAdminAuditTime(currTime);
			}

			int result = resRecBiz.edit(rec);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
	}

	/**
	 * 删除出科考核表
	 * @param recFlow
	 * @param imageFlow
	 * @return
	 * @throws DocumentException
     */
	@RequestMapping(value="/resRecImgDelete")
	@ResponseBody
	public String resRecImgDelete(String recFlow, String imageFlow) throws DocumentException{
//		ResRec resRec=resRecBiz.readResRec(recFlow);
		ResSchProcessExpress resRec = expressBiz.readResExpress(recFlow);
		String content=resRec.getRecContent();
		Document document=DocumentHelper.parseText(content);
		Element elem=document.getRootElement();
		Node delNode = elem.selectSingleNode("image[@imageFlow='"+imageFlow+"']");
		if (delNode!=null) {
			delNode.detach();
			resRec.setRecContent(document.asXML());
			expressBiz.edit(resRec);
			resRecBiz.updateResultHaveAfter(resRec.getSchRotationDeptFlow(),resRec.getOperUserFlow(),resRec.getRecContent());
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
		}else{
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
		}
	}



	/**
	 * 获取登记表表单(新)
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
	@RequestMapping(value="/showRegistryFormNew",method={RequestMethod.GET,RequestMethod.POST})
	public String showRegistryFormNew(String recFlow,
								   String recTypeId,
								   String rotationFlow,
								   String schDeptFlow,
								   String operUserFlow,
								   String roleFlag,
								   String resultFlow,
								   String processFlow,
								   Model model){
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
			result = schArrangeResultBiz.readSchArrangeResult(resultFlow);
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

//		ResRec rec = null;
		ResSchProcessExpress express = null;
		//根据登记表流水号获取登记表信息
		if(StringUtil.isNotBlank(recFlow)){
//			rec = this.resRecBiz.readResRec(recFlow);
//			recTypeId = rec.getRecTypeId();
			express = expressBiz.readResExpress(recFlow);
			if(express!=null){
				recTypeId = express.getRecTypeId();
			}
		}

		//是否出科小结或出科考核表
        boolean isAfter = com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId().equals(recTypeId) || com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId().equals(recTypeId) || com.pinde.core.common.enums.ResRecTypeEnum.DiscipleSummary.getId().equals(recTypeId);

		//是出科小结或出科考核表且登记表流水为空
		if(isAfter && StringUtil.isBlank(recFlow)){
			//List<ResRec> recList = resRecBiz.searchByRec(recTypeId,schDeptFlow,operUserFlow);
//			List<ResRec> recList = resRecBiz.searchByRecAndProcess(recTypeId,schDeptFlow,operUserFlow,processFlow);
			List<ResSchProcessExpress> expressList = expressBiz.searchByRecAndProcess(recTypeId,processFlow);

			if(expressList!=null && expressList.size()>0){
				recFlow = expressList.get(0).getRecFlow();
//				rec = resRecBiz.readResRec(recFlow);
				express = expressBiz.readResExpress(recFlow);
			}
			//出科小结
            if (com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId().equals(recTypeId)) {
                //List<ResRec> evaluationList = resRecBiz.searchByRec(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId(),schDeptFlow,operUserFlow);
                List<ResSchProcessExpress> evaluationList = expressBiz.searchByRecAndProcess(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId(), processFlow);
				if(evaluationList!=null && evaluationList.size()>0){
					model.addAttribute("evaluation",evaluationList.get(0));
				}
			}
		}
		//出科考核表
        if (com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId().equals(recTypeId)) {
            List<ResSchProcessExpress> evaluationList = expressBiz.searchByRecAndProcess(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId(), processFlow);
			if(evaluationList!=null && evaluationList.size()>0){
				model.addAttribute("summary",evaluationList.get(0));
			}
            List<ResSchProcessExpress> dopslist = expressBiz.searchByRecAndProcess(com.pinde.core.common.enums.ResRecTypeEnum.DOPS.getId(), processFlow);
			if(dopslist!=null && dopslist.size()>0){
				ResSchProcessExpress dops=dopslist.get(0);
				if("gzzyyy".equals(dops.getRecForm()) && StringUtil.isNotBlank(dops.getRecContent()))
				{
					Map<String,Object> formDataMap= resRecBiz.parseRecContent(dops.getRecContent());
					if(formDataMap!=null&&formDataMap.size()>0)
					{
						List<Double> scoreList = new ArrayList<>();
						scoreList.add(Double.parseDouble(formDataMap.get("skillLevel").toString()));
						scoreList.add(Double.parseDouble(formDataMap.get("consentForm").toString()));
						scoreList.add(Double.parseDouble(formDataMap.get("readyToWork").toString()));
						scoreList.add(Double.parseDouble(formDataMap.get("painAndStabilization").toString()));
						scoreList.add(Double.parseDouble(formDataMap.get("SkillAbility").toString()));
						scoreList.add(Double.parseDouble(formDataMap.get("asepticTechnique").toString()));
						scoreList.add(Double.parseDouble(formDataMap.get("seekAssistance").toString()));
						scoreList.add(Double.parseDouble(formDataMap.get("relatedDisposal").toString()));
						scoreList.add(Double.parseDouble(formDataMap.get("communicationSkills").toString()));
						scoreList.add(Double.parseDouble(formDataMap.get("feelProfessionalDegree").toString()));
						scoreList.add(Double.parseDouble(formDataMap.get("overallPerformance").toString()));
						double sum = 0;
						if(!scoreList.isEmpty()){
							for(double a:scoreList){
								sum+=a;
							}
						}
						model.addAttribute("dopsScore", Math.round(sum/99*10));
					}
				}
				model.addAttribute("dops",dopslist.get(0));
			}
            List<ResSchProcessExpress> minilist = expressBiz.searchByRecAndProcess(com.pinde.core.common.enums.ResRecTypeEnum.Mini_CEX.getId(), processFlow);
			if(minilist!=null && minilist.size()>0){
				ResSchProcessExpress mini=minilist.get(0);
				if("gzzyyy".equals(mini.getRecForm()) && StringUtil.isNotBlank(mini.getRecContent()))
				{
					Map<String,Object> formDataMap= resRecBiz.parseRecContent(mini.getRecContent());
					if(formDataMap!=null&&formDataMap.size()>0)
					{
						List<Double> scoreList = new ArrayList<>();
						scoreList.add(Double.parseDouble("-".equals(formDataMap.get("medicalInterview").toString())?"0":formDataMap.get("medicalInterview").toString()));
						scoreList.add(Double.parseDouble("-".equals(formDataMap.get("physicalExamination").toString())?"0":formDataMap.get("physicalExamination").toString()));
						scoreList.add(Double.parseDouble("-".equals(formDataMap.get("humanisticCare").toString())?"0":formDataMap.get("humanisticCare").toString()));
						scoreList.add(Double.parseDouble("-".equals(formDataMap.get("clinicalJudgment").toString())?"0":formDataMap.get("clinicalJudgment").toString()));
						scoreList.add(Double.parseDouble("-".equals(formDataMap.get("communicationSkills").toString())?"0":formDataMap.get("communicationSkills").toString()));
						scoreList.add(Double.parseDouble("-".equals(formDataMap.get("organization").toString())?"0":formDataMap.get("organization").toString()));
						scoreList.add(Double.parseDouble("-".equals(formDataMap.get("holisticCare").toString())?"0":formDataMap.get("holisticCare").toString()));
						double sum = 0;
						if(!scoreList.isEmpty()){
							for(double a:scoreList){
								sum+=a;
							}
						}
						model.addAttribute("miniScore",Math.round(sum/63*20));
					}
				}
				model.addAttribute("mini",minilist.get(0));
			}
			//执行保存
			ResScoreExample example2 = new ResScoreExample();
			example2.createCriteria().andDoctorFlowEqualTo(operUserFlow).andResultFlowEqualTo(resultFlow).
                    andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andScoreTypeIdEqualTo("SkillUpload");
			List<ResScore> scoreList = resScoreMapper.selectByExample(example2);
			if(!scoreList.isEmpty()){
				model.addAttribute("score",scoreList.get(0));
			}
			String cfg13=InitConfig.getSysCfg("jswjw_"+GlobalContext.getCurrentUser().getOrgFlow()+"_P013");
			model.addAttribute("cfg13",cfg13);
			String formTypeId = StringUtil.defaultIfEmpty(InitConfig.getSysCfg("res_form_category_"+rotationFlow),InitConfig.getSysCfg("res_form_category"));
			if("shfx".equals(formTypeId)){
				Map<String, Object> itemsMap=new HashMap<>();
				itemsMap.put("processFlow",processFlow);
				itemsMap.put("recFormId","Physique");
				List<DeptTeacherGradeInfo> PhysiqueList=resGradeBiz.searchResGradeByItems(itemsMap);
				if(PhysiqueList!=null&&PhysiqueList.size()>0){
					List<Double> scoreP=new ArrayList<>();
					for(DeptTeacherGradeInfo dg:PhysiqueList){
						scoreP.add(StringUtil.isBlank(dg.getAllScore())?0:Double.parseDouble(dg.getAllScore()));
					}
					if(scoreP!=null&&scoreP.size()>0){
						model.addAttribute("PhysiqueScore",Collections.max(scoreP));
					}
				}
				itemsMap.put("recFormId","Clinical");
				List<DeptTeacherGradeInfo> ClinicalList=resGradeBiz.searchResGradeByItems(itemsMap);
				if(ClinicalList!=null&&ClinicalList.size()>0){
					List<Double> scoreP=new ArrayList<>();
					for(DeptTeacherGradeInfo dg:ClinicalList){
						scoreP.add(StringUtil.isBlank(dg.getAllScore())?0:Double.parseDouble(dg.getAllScore()));
					}
					if(scoreP!=null&&scoreP.size()>0){
						model.addAttribute("ClinicalScore",Collections.max(scoreP));
					}
				}
				itemsMap.put("recFormId","MedicalHistory");
				List<DeptTeacherGradeInfo> MedicalHistoryList=resGradeBiz.searchResGradeByItems(itemsMap);
				if(MedicalHistoryList!=null&&MedicalHistoryList.size()>0){
					List<Double> scoreP=new ArrayList<>();
					for(DeptTeacherGradeInfo dg:MedicalHistoryList){
						scoreP.add(StringUtil.isBlank(dg.getAllScore())?0:Double.parseDouble(dg.getAllScore()));
					}
					if(scoreP!=null&&scoreP.size()>0){
						model.addAttribute("MedicalHistoryScore",Collections.max(scoreP));
					}
				}
				itemsMap.put("recFormId","CaseAnalysis");
				List<DeptTeacherGradeInfo> CaseAnalysisList=resGradeBiz.searchResGradeByItems(itemsMap);
				if(CaseAnalysisList!=null&&CaseAnalysisList.size()>0){
					List<Double> scoreP=new ArrayList<>();
					for(DeptTeacherGradeInfo dg:CaseAnalysisList){
						scoreP.add(StringUtil.isBlank(dg.getAllScore())?0:Double.parseDouble(dg.getAllScore()));
					}
					if(scoreP!=null&&scoreP.size()>0){
						model.addAttribute("CaseAnalysisScore",Collections.max(scoreP));
					}
				}
			}

		}
		if(express!=null){

			model.addAttribute("rec",express);

			if(!StringUtil.isNotBlank(rotationFlow)){
				ResDoctor doctor = resDoctorBiz.readDoctor(express.getOperUserFlow());
				rotationFlow = doctor.getRotationFlow();
			}

            if (com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId().equals(recTypeId)) {
				Map<String,Integer> recFinishMap = new HashMap<String, Integer>();
				List<String> recTypeIds = new ArrayList<String>();
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.CaseRegistry.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.DiseaseRegistry.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.OperationRegistry.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.SkillRegistry.getId());
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

		if(!isAfter && result!=null){
			List<SchRotationDeptReq> deptReqList = schRotationDeptBiz.searchStandardReqByResult(result, recTypeId);
			model.addAttribute("deptReqList",deptReqList);
		}

		String currVer = null;
		String recForm = null;

		if(express!=null){
			currVer = express.getRecVersion();
			String recContent = express.getRecContent();
			recForm = express.getRecForm();
            if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)) {
				//recContent = cutHeadScore(recContent);
			}


			//是否是实习生的六个表单
            boolean isSix = com.pinde.core.common.enums.ResRecTypeEnum.Ethics.getId().equals(recTypeId) || com.pinde.core.common.enums.ResRecTypeEnum.Document.getId().equals(recTypeId)
                    || com.pinde.core.common.enums.ResRecTypeEnum.NursingSkills.getId().equals(recTypeId) || com.pinde.core.common.enums.ResRecTypeEnum.ClinicalEnglish.getId().equals(recTypeId)
                    || com.pinde.core.common.enums.ResRecTypeEnum.Appraisal.getId().equals(recTypeId) || com.pinde.core.common.enums.ResRecTypeEnum.CourseScore.getId().equals(recTypeId);
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

		ResScore score = scoreBiz.getScoreByProcess(processFlow);
		model.addAttribute("outScore",score);
		//出科考试权限
      ResPowerCfg resPowerCfg = resPowerCfgBiz.read("res_doctor_ckks_"+operUserFlow);
      if(resPowerCfg!=null){
          if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resPowerCfg.getCfgValue())
                  && com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(resPowerCfg.getRecordStatus())
            ){
              model.addAttribute("open", com.pinde.core.common.GlobalConstant.FLAG_Y);
        }else{
              model.addAttribute("open", com.pinde.core.common.GlobalConstant.FLAG_N);
		}
      }else {
          model.addAttribute("open", com.pinde.core.common.GlobalConstant.FLAG_N);
      }
		return jspPath;
	}
	public static void main(String args [] )
	{
		String jspPath="res/form/{3}/{0}/evaluation_{1}{2}";
		String jspPath2="res/form/{3}/{0}/annualActivity_{1}";
		jspPath = MessageFormat.format(jspPath,"B","2.0","D","C");
		jspPath2 = MessageFormat.format(jspPath2,"B","2.0","D","C");
		System.out.println(jspPath);
		System.out.println(jspPath2);
	}

	/**
	 * 获取登记表表单
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
	@RequestMapping(value="/showRegistryForm",method={RequestMethod.GET,RequestMethod.POST})
	public String showRegistryForm(String recFlow,
			String recTypeId,
			String rotationFlow,
			String schDeptFlow,
			String operUserFlow,
			String roleFlag,
			String resultFlow,
			String processFlow,
			Model model){
		model.addAttribute("roleFlag", roleFlag);

		//查询轮转进度
		ResDoctorSchProcess process = null;
		if(StringUtil.isNotBlank(processFlow)){
			process = resDoctorProcessBiz.read(processFlow);
		}

		String medicineTypeId="";
		//根据传入用户或当前用户获取医师信息和用户信息
		operUserFlow = StringUtil.defaultIfEmpty(operUserFlow, GlobalContext.getCurrentUser().getUserFlow());
		if(StringUtil.isNotBlank(operUserFlow)){
			ResDoctor doctor  = doctorBiz.readDoctor(operUserFlow);
			model.addAttribute("doctor", doctor);

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
			result = schArrangeResultBiz.readSchArrangeResult(resultFlow);
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

		ResRec rec = null;
		//根据登记表流水号获取登记表信息
		if(StringUtil.isNotBlank(recFlow)){
			rec = this.resRecBiz.readResRec(recFlow);
			recTypeId = rec.getRecTypeId();
		}

		//是否出科小结或出科考核表
        boolean isAfter = com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId().equals(recTypeId) || com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId().equals(recTypeId) || com.pinde.core.common.enums.ResRecTypeEnum.DiscipleSummary.getId().equals(recTypeId);

		//是出科小结或出科考核表且登记表流水为空
		if(isAfter && StringUtil.isBlank(recFlow)){
			//List<ResRec> recList = resRecBiz.searchByRec(recTypeId,schDeptFlow,operUserFlow);
			List<ResRec> recList = resRecBiz.searchByRecAndProcess(recTypeId,operUserFlow,processFlow);
			if(recList!=null && recList.size()>0){
				recFlow = recList.get(0).getRecFlow();
				rec = resRecBiz.readResRec(recFlow);
			}
			//出科小结
            if (com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId().equals(recTypeId)) {
                //List<ResRec> evaluationList = resRecBiz.searchByRec(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId(),schDeptFlow,operUserFlow);
                List<ResRec> evaluationList = resRecBiz.searchByRecAndProcess(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId(), operUserFlow, processFlow);
				if(evaluationList!=null && evaluationList.size()>0){
					model.addAttribute("evaluation",evaluationList.get(0));
				}
			}
		}
		if(rec!=null){
			model.addAttribute("rec",rec);

			if(!StringUtil.isNotBlank(rotationFlow)){
				ResDoctor doctor = resDoctorBiz.readDoctor(rec.getOperUserFlow());
				rotationFlow = doctor.getRotationFlow();
			}

            if (com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId().equals(recTypeId)) {
				Map<String,Integer> recFinishMap = new HashMap<String, Integer>();
				List<String> recTypeIds = new ArrayList<String>();
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.CaseRegistry.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.DiseaseRegistry.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.OperationRegistry.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.SkillRegistry.getId());
				List<ResRec> recList = resRecBiz.searchFinishRec(recTypeIds,rec.getOperUserFlow());
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

		if(!isAfter && result!=null){
			List<SchRotationDeptReq> deptReqList = schRotationDeptBiz.searchStandardReqByResult(result, recTypeId);
			model.addAttribute("deptReqList",deptReqList);
		}

		String currVer = null;
		String recForm = null;

		if(rec!=null){
			currVer = rec.getRecVersion();
			medicineTypeId = rec.getMedicineType();
			String recContent = rec.getRecContent();
			recForm = rec.getRecForm();
            if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)) {
				//recContent = cutHeadScore(recContent);
			}


			//是否是实习生的六个表单
            boolean isSix = com.pinde.core.common.enums.ResRecTypeEnum.Ethics.getId().equals(recTypeId) || com.pinde.core.common.enums.ResRecTypeEnum.Document.getId().equals(recTypeId)
                    || com.pinde.core.common.enums.ResRecTypeEnum.NursingSkills.getId().equals(recTypeId) || com.pinde.core.common.enums.ResRecTypeEnum.ClinicalEnglish.getId().equals(recTypeId)
                    || com.pinde.core.common.enums.ResRecTypeEnum.Appraisal.getId().equals(recTypeId) || com.pinde.core.common.enums.ResRecTypeEnum.CourseScore.getId().equals(recTypeId);
			if(isSix) {
				Map<String, Object> formDataMap = null;
				formDataMap = resRecBiz.parseContent(recContent);
				model.addAttribute("formDataMap", formDataMap);
			}else{
				Map<String,Object> formDataMap = null;
				formDataMap = resRecBiz.parseRecContent(recContent);
				model.addAttribute("formDataMap", formDataMap);
			}
			operUserFlow = rec.getOperUserFlow();
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

		SysUser user=new SysUser();
		user.setUserFlow(operUserFlow);
		ResDoctorSchProcess currProcess = resDoctorProcessBiz.searchCurrDept(user);
		model.addAttribute("process",currProcess);
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
		String jspPath = resRecBiz.getFormPath(rotationFlow,recTypeId,currVer,recForm, "",medicineTypeId,reocrdFlow);
		model.addAttribute("jspPath", jspPath);

		ResScore score = scoreBiz.getScoreByProcess(processFlow);
		model.addAttribute("outScore",score);
		return jspPath;
	}

	@RequestMapping(value="/evaluationSun")
	@ResponseBody
	public Map<String, Object> evaluationSun(String operUserFlow,
			String processFlow,
			Model model) throws Exception{
		ResDoctor doctor=null;
		SysUser operUser=null;
		if(StringUtil.isNotBlank(operUserFlow)){
			doctor  = doctorBiz.readDoctor(operUserFlow);
			operUser  = userBiz.readSysUser(operUserFlow);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, String> processPerMap=null;
		ResDoctorSchProcess process=resDoctorProcessBiz.read(processFlow);
		if(process!=null) {
			SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(process.getSchResultFlow());
			processFlow=result.getResultFlow();
					//百分比Map
			List < SchArrangeResult > results = new ArrayList<SchArrangeResult>();
			results.add(result);
			 processPerMap = resRecBiz.getFinishPer(results,operUserFlow);
		}
		if(processPerMap == null){
			processPerMap = new HashMap<String, String>();
		}
		//获取不同类型并定义接受
		if(processPerMap!=null){
            String caseRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.CaseRegistry.getId();
            String caseRecordId = com.pinde.core.common.enums.ResRecTypeEnum.CaseRecord.getId();
            String skillRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.SkillRegistry.getId();
            String campaignRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.CampaignRegistry.getId();
			
			String caseRegistry=(String)processPerMap.get(processFlow+caseRegistryId);
			String caseRegistryReqNum=(String)processPerMap.get(processFlow+caseRegistryId+"req");
			String caseRegistryFinished=(String)processPerMap.get(processFlow+caseRegistryId+"finish");
			 
			String caseRecord=(String)processPerMap.get(processFlow+caseRecordId);
			String caseRecordNum=(String)processPerMap.get(processFlow+caseRecordId+"req");
			String caseRecordFinished=(String)processPerMap.get(processFlow+caseRecordId+"finish");
			
			String skillRegistry=(String)processPerMap.get(processFlow+skillRegistryId);
			String skillRegistryReqNum=(String)processPerMap.get(processFlow+skillRegistryId+"req");
			String skillRegistryFinished=(String)processPerMap.get(processFlow+skillRegistryId+"finish");
			
			String campaignRegistry=(String)processPerMap.get(processFlow+campaignRegistryId);
			String campaignRegistryNum=(String)processPerMap.get(processFlow+campaignRegistryId+"req");
			String campaignRegistryFinished=(String)processPerMap.get(processFlow+campaignRegistryId+"finish");

            String recTypeIdt = com.pinde.core.common.enums.ResRecTypeEnum.CampaignRegistry.getId();
			int teachingRounds=0;
			int difficult=0;
			int lecture=0;
			int death=0;
			List<String> recTypes=new ArrayList<String>();
			recTypes.add(recTypeIdt);
			if (recTypes!=null && recTypes.size()>0&&StringUtil.isNotBlank(processFlow)&&StringUtil.isNotBlank(operUser.getUserFlow())) {
				List<ResRec> recs= resRecBiz.searchRecByProcessWithBLOBs(recTypes,processFlow,operUser.getUserFlow());
				for (ResRec resRec : recs) {
					String content=resRec.getRecContent();
					Document document=DocumentHelper.parseText(content);
					Element root=document.getRootElement();
					Element ec = root.element("activity_way");
					if (ec!=null) {
						String text=ec.attributeValue("id");
						if(JXCF.equals(text)){
							teachingRounds++;
						}else if(YN.equals(text) || WZBLTL.equals(text)){
							difficult++;
						}else if(XSJZ.equals(text)){
							lecture++;
						}else if(SWBLTL.equals(text)){
							death++;
						}
					}
				}
			}
			if(StringUtil.isNotBlank(operUserFlow)){
				dataMap.put("userName",operUser.getUserName());
				dataMap.put("sessionNumber",doctor.getSessionNumber());
				dataMap.put("trainingSpeName",doctor.getTrainingSpeName());
			}
			dataMap.put("caseRegistry",StringUtil.defaultIfEmpty(caseRegistry, "0"));
			dataMap.put("caseRegistryReqNum", StringUtil.defaultIfEmpty(caseRegistryReqNum, "0"));
			dataMap.put("caseRegistryFinished", StringUtil.defaultIfEmpty(caseRegistryFinished, "0"));
			
			dataMap.put("caseRecord",StringUtil.defaultIfEmpty(caseRecord, "0"));
			dataMap.put("caseRecordNum",StringUtil.defaultIfEmpty(caseRecordNum, "0"));
			dataMap.put("caseRecordFinished", StringUtil.defaultIfEmpty(caseRecordFinished, "0"));
			
			dataMap.put("skillRegistry", StringUtil.defaultIfEmpty(skillRegistry, "0"));
			dataMap.put("skillRegistryReqNum", StringUtil.defaultIfEmpty(skillRegistryReqNum, "0"));
			dataMap.put("skillRegistryFinished", StringUtil.defaultIfEmpty(skillRegistryFinished, "0"));
			
			dataMap.put("campaignRegistry", StringUtil.defaultIfEmpty(campaignRegistry, "0"));
			dataMap.put("campaignRegistryNum", StringUtil.defaultIfEmpty(campaignRegistryNum, "0"));
			dataMap.put("campaignRegistryFinished", StringUtil.defaultIfEmpty(campaignRegistryFinished, "0"));
			
			dataMap.put("teachingRounds",String.valueOf(teachingRounds));
			dataMap.put("difficult",String.valueOf(difficult));
			dataMap.put("lecture",String.valueOf(lecture));
			dataMap.put("death",String.valueOf(death));
		}
		return dataMap;
	}

	/**
	 * 打印
	 * @param recFlow
	 * @param request
	 * @param response
	 * @throws Exception
     */
	@RequestMapping(value="/printRegistryCheck")
	public void printRegistryCheck(String recFlow, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(StringUtil.isNotBlank(recFlow)){
//			ResRec rec  = resRecBiz.readResRec(recFlow);
			ResSchProcessExpress rec = expressBiz.readResExpress(recFlow);
			Map<String,Object> formDataMap = null;
			if(rec!=null){
				String recContent = rec.getRecContent();
				formDataMap = resRecBiz.parseRecContent(recContent);
				for(Entry<String, Object> entry : formDataMap.entrySet()){
					//System.err.println("key:" + entry.getKey() + "--value:" + entry.getValue());
					String key = entry.getKey();
					Object value = entry.getValue();
					resultMap.put(key, value);
					String rowNo = new String();
					String cellNo = new String();
					boolean addFlag = false;
					if("teamwork".equals(key)){//团队协作、奉献精神
						rowNo="1";
						addFlag = true;
					}else if("quantity".equals(key)){//完成工作数量
						rowNo="2";
						addFlag = true;
					}else if("quality".equals(key)){//完成工作质量
						rowNo="3";
						addFlag = true;
					}else if("complyWith".equals(key)){//遵守劳动纪律、规章制度
						rowNo="4";
						addFlag = true;
					}else if("Level".equals(key)){//考核等级
						rowNo="5";
						addFlag = true;
					}
					if(addFlag){
						if("优秀".equals(value)){
							cellNo = "1";
						}else if("良好".equals(value)){
							cellNo = "2";
						}else if("达标".equals(value) || "合格".equals(value)){
							cellNo = "3";
						}else if("尚可".equals(value) || "基本合格".equals(value)){
							cellNo = "4";
						}else if("欠缺".equals(value) || "不合格".equals(value)){
							cellNo = "5";
						}
						resultMap.put("row" + rowNo + "cell" + cellNo, value);
					}
				}
				String year = (String)formDataMap.get("year");
				WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
				String path = "/jsp/res/edu/student/print/annualRegistryCheckTemeplete.docx";//模板
				ServletContext context =  request.getServletContext();
                String watermark = GeneralMethod.getWatermark(com.pinde.core.common.GlobalConstant.FLAG_N);
				temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), resultMap,watermark,true);
				if(temeplete!=null){
					String name = "年度考核登记表（"+ year +"年度）.docx"; 
					response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +""); 
					response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
					ServletOutputStream out = response.getOutputStream ();
					(new SaveToZipFile (temeplete)).save (out);
					out.flush ();
				}
			}
		}
	}
	
	//带教老师评分去除科主任评分以正确展示
	private String cutHeadScore(String content){
		if(StringUtil.isNotBlank(content)){
			try {
				Document doc = DocumentHelper.parseText(content);
				Element root = doc.getRootElement();
                Element headNode = root.element(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD + com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
				if(headNode!=null){
					headNode.detach();
				}
				content = doc.asXML();
			} catch (Exception e) {
                logger.error("", e);
			}
		}
		return content;
	}








	/**
	 * 保存登记表单(新)
	 * @param formFileName 登记类型
	 * @param recFlow 登记表流水号
	 * @param schDeptFlow 轮转科室流水号
	 * @param operUserFlow
	 * @param roleFlag
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/saveRegistryFormNew",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveRegistryFormNew(String formFileName,String recFlow,String schDeptFlow,String operUserFlow,String roleFlag,HttpServletRequest req){
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
		int result = this.expressBiz.saveRegistryForm(formFileName,recFlow,schDeptFlow,rotationFlow,req,orgFlow,user.getMedicineTypeId());
        if (result != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}



	/**
	 * 保存登记表单
	 * @param formFileName 登记类型
	 * @param recFlow 登记表流水号
	 * @param schDeptFlow 轮转科室流水号
	 * @param operUserFlow
     * @param req
     * @return
     */
	@RequestMapping(value="/saveRegistryForm",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveRegistryForm(String formFileName,String recFlow,String schDeptFlow,String operUserFlow,String canEditAppendix,HttpServletRequest req){
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
		int result = this.resRecBiz.saveRegistryForm(formFileName,recFlow,schDeptFlow,rotationFlow,req,orgFlow,user.getMedicineTypeId(),canEditAppendix);
        if (result != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
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
	//下载附件
	@RequestMapping(value = {"/fileDown" }, method = RequestMethod.GET)
	public void fileDown(String fileFlow, final HttpServletResponse response) throws Exception{
		PubFile file = this.pubFileBiz.readFile(fileFlow);
		pubFileBiz.downPubFile(file,response);
	}
	@RequestMapping(value="/appraiseList",method={RequestMethod.GET,RequestMethod.POST})
	public String appraiseList(String recFlow,Model model){
		if(StringUtil.isNotBlank(recFlow)){
			ResRec rec = resRecBiz.readResRec(recFlow);
			if(rec!=null){
				Map<String,List<Map<String,String>>> appraiseMap = resRecBiz.parseRecContentAppraise(rec.getRecContent());
				model.addAttribute("appraiseMap",appraiseMap);
			}
		}
		return "res/doctor/appraiseList";
	}

	/**
	 * 删除
	 * @param rec
	 * @return
     */
	@RequestMapping(value="/opreResRec",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String delResRec(ResRec rec){
        String deleteS = com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
        String deleteF = com.pinde.core.common.GlobalConstant.DELETE_FAIL;
		if(rec!=null){
			if(StringUtil.isNotBlank(rec.getStatusId())){
				rec.setStatusName(RecStatusEnum.getNameById(rec.getStatusId()));
				rec.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
				rec.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());
				rec.setHeadAuditStatusId(RecStatusEnum.HeadAuditY.getId());
				rec.setHeadAuditStatusName(RecStatusEnum.HeadAuditY.getName());
                deleteS = com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
                deleteF = com.pinde.core.common.GlobalConstant.OPRE_FAIL;
			}
            if (resRecBiz.edit(rec) != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
				return deleteS;
			}
		}
		return deleteF;
	}

	/**
	 * 提交评分
	 * @param deptTeacherGradeInfo
	 * @return
     */
	@RequestMapping(value="/opreResRecForGrade",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String opreResRecForGrade(DeptTeacherGradeInfo deptTeacherGradeInfo){
        String deleteS = com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
        String deleteF = com.pinde.core.common.GlobalConstant.DELETE_FAIL;
		if(deptTeacherGradeInfo != null){
			if(StringUtil.isNotBlank(deptTeacherGradeInfo.getStatusId())){
				deptTeacherGradeInfo.setStatusName(RecStatusEnum.getNameById(deptTeacherGradeInfo.getStatusId()));
                deleteS = com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
                deleteF = com.pinde.core.common.GlobalConstant.OPRE_FAIL;
			}
            if (resGradeBiz.edit(deptTeacherGradeInfo) != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
				return deleteS;
			}
		}
		return deleteF;
	}
	
	@RequestMapping(value="/requireData",method={RequestMethod.GET,RequestMethod.POST})
	public String requireData(String resTypeId,String schDeptFlow,Model model){
		if(StringUtil.isNotBlank(schDeptFlow)){
			SchDept schDept = schDeptBiz.readSchDept(schDeptFlow);
			model.addAttribute("schDept",schDept);
		}
		return "res/doctor/registry/data";
	}
	
	
	/**
	 * 登记数据列表
	 * @param schDeptFlow
	 * @param rotationFlow
	 * @param recTypeId
	 * @param resultFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/requireDataList",method={RequestMethod.GET,RequestMethod.POST})
	public String requireDataList(String schDeptFlow,String rotationFlow,String recTypeId,String resultFlow,String processFlow,Model model){
		Map<String,Integer> recCountMap = new HashMap<String, Integer>();
		
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();

		if(StringUtil.isBlank(processFlow))
		{
			ResDoctorSchProcess process=resDoctorProcessBiz.searchByResultFlow(resultFlow);
			if(process!=null)
				processFlow=process.getProcessFlow();
		}
		SchRotationDept schRotationDept = schRotationDeptBiz.readStandardRotationDept(resultFlow);
		if(schRotationDept != null){
			model.addAttribute("typeId",schRotationDept.getPracticOrTheory());
		}
		if(StringUtil.isNotBlank(recTypeId) && StringUtil.isNotBlank(schDeptFlow)&& StringUtil.isNotBlank(processFlow)){//&& StringUtil.isNotBlank(rotationFlow)
//			List<ResRec> recList = resRecBiz.searchByRecWithBLOBs(recTypeId,schDeptFlow,userFlow);
			List<ResRec> recList = resRecBiz.searchByRecAndProcess(recTypeId, userFlow,processFlow);
			if(recList!=null && recList.size()>0){
				model.addAttribute("recList",recList);
				
				//Map<String,Map<String,String>> recMap = new HashMap<String, Map<String,String>>();
				Map<String,List<Map<String,String>>> viewListMap = new HashMap<String, List<Map<String,String>>>();
				Map<String,List<ResRec>> recListMap = new HashMap<String,List<ResRec>>();
				for(ResRec recTemp : recList){
					//Map<String,String> formDataMap = resRecBiz.parseRecContent(recTemp.getRecContent());
					//recMap.put(recTemp.getRecFlow(),formDataMap);
					/////////////////////////////////////////////////
					
					String content = recTemp.getRecContent();
					String form = recTemp.getRecForm();
					String type = recTemp.getRecTypeId();
					String ver = recTemp.getRecVersion();
					
					List<Map<String,String>> viewInfoList = resRecBiz.parseViewValue(form,type,ver,content);
					viewListMap.put(recTemp.getRecFlow(),viewInfoList);
					
//					String itemName = StringUtil.defaultIfEmpty(recTemp.getItemName(),"other");
					String itemId = recTemp.getItemId();
					
					if(recListMap.get(itemId)==null){
						List<ResRec> recTempList = new ArrayList<ResRec>();
						recTempList.add(recTemp);
						recListMap.put(itemId,recTempList);
					}else{
						recListMap.get(itemId).add(recTemp);
					}
					
					if(RecStatusEnum.TeacherAuditY.getId().equals(recTemp.getAuditStatusId())){
						if(recCountMap.get("auditCount")==null){
							recCountMap.put("auditCount",1);
						}else{
							recCountMap.put("auditCount",recCountMap.get("auditCount")+1);
						}
						
						if(recCountMap.get(itemId+"auditCount")==null){
							recCountMap.put(itemId+"auditCount",1);
						}else{
							recCountMap.put(itemId+"auditCount",recCountMap.get(itemId+"auditCount")+1);
						}
					}
					
					if(recCountMap.get("finishCount")==null){
						recCountMap.put("finishCount",1);
					}else{
						recCountMap.put("finishCount",recCountMap.get("finishCount")+1);
					}
					
					if(recCountMap.get(itemId+"finishCount")==null){
						recCountMap.put(itemId+"finishCount",1);
					}else{
						recCountMap.put(itemId+"finishCount",recCountMap.get(itemId+"finishCount")+1);
					}
					
					if(recCountMap.get(recTemp.getRecTypeId()+"finish")==null){
						recCountMap.put(recTemp.getRecTypeId()+"finish",1);
					}else{
						recCountMap.put(recTemp.getRecTypeId()+"finish",recCountMap.get(recTemp.getRecTypeId()+"finish")+1);
					}
				}
				
				//model.addAttribute("recMap",recMap);
				model.addAttribute("viewListMap",viewListMap);
				model.addAttribute("recListMap",recListMap);
			}
			
			List<ResAppeal> appealList = resRecBiz.searchAppeal(recTypeId,schDeptFlow,userFlow,processFlow);
			if(appealList!=null && appealList.size()>0){
				Map<String,ResAppeal> appealMap = new HashMap<String,ResAppeal>();
				for(ResAppeal appeal : appealList){
					appealMap.put(appeal.getItemId(),appeal);
					
//					if(recCountMap.get("appealCount")==null){
//						recCountMap.put("appealCount",appeal.getAppealNum().intValue());
//					}else{
//						recCountMap.put("appealCount",recCountMap.get("appealCount")+appeal.getAppealNum().intValue());
//					}
//					
//					if(recCountMap.get(appeal.getItemName()+"appealCount")==null){
//						recCountMap.put(appeal.getItemName()+"appealCount",appeal.getAppealNum().intValue());
//					}else{
//						recCountMap.put(appeal.getItemName()+"appealCount",recCountMap.get(appeal.getItemName()+"appealCount")+appeal.getAppealNum().intValue());
//					}
				}
				model.addAttribute("appealMap",appealMap);
			}
		}
		
		//计算要求数
		if(StringUtil.isNotBlank(resultFlow)){
			SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(resultFlow);

			ResDoctor doctor=doctorBiz.readDoctor(result.getDoctorFlow());
			List<SchRotationDeptReq> deptReqList = schRotationDeptBiz.searchStandardReqByResult(result, recTypeId);
			if(deptReqList!=null && deptReqList.size()>0){
				model.addAttribute("deptReqList",deptReqList);
				
				Map<String,SchRotationDeptReq> deptReqMap = new HashMap<String, SchRotationDeptReq>();
				for(SchRotationDeptReq deptReqTemp : deptReqList){
					deptReqMap.put(deptReqTemp.getReqFlow(),deptReqTemp);
					if(recCountMap.get(deptReqTemp.getRecTypeId()+"reqNum")==null){
						recCountMap.put(deptReqTemp.getRecTypeId()+"reqNum",deptReqTemp.getReqNum().intValue());
					}else{
						recCountMap.put(deptReqTemp.getRecTypeId()+"reqNum",recCountMap.get(deptReqTemp.getRecTypeId()+"reqNum")+deptReqTemp.getReqNum().intValue());
					}
				}
				
				model.addAttribute("deptReqMap",deptReqMap);
			}
			
			List<SchArrangeResult> results = new ArrayList<SchArrangeResult>();
			results.add(result);
			Map<String,String> recFinishMap = resRecBiz.getFinishPer(results);
			model.addAttribute("recFinishMap",recFinishMap);
		}
		
		model.addAttribute("recCountMap",recCountMap);
		
		return "res/doctor/registryView";
	}
	
	/**
	 * 培训视图
	 * */
	@RequestMapping(value="/processView",method={RequestMethod.GET})
	public String processView(String resultFlow,String processFlow,Model model){
		SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(resultFlow);
		SchRotationDept schRotationDept = schRotationDeptBiz.readStandardRotationDept(resultFlow);
		if(schRotationDept != null){
			model.addAttribute("typeId",schRotationDept.getPracticOrTheory());
		}
		if(result!=null){
			model.addAttribute("result",result);
			List<SchArrangeResult> results = new ArrayList<SchArrangeResult>();
			results.add(result);
			Map<String,String> recFinishMap = resRecBiz.getFinishPer(results);
			model.addAttribute("recFinishMap",recFinishMap);
			SysUser docUser=userBiz.readSysUser(result.getDoctorFlow());
			model.addAttribute("docUser",docUser);
		}
		
		ResDoctorSchProcess process = resDoctorProcessBiz.read(processFlow);
		if(process!=null){
			model.addAttribute("process",process);
			
			String schDeptFlow = process.getSchDeptFlow();
			
			//申述数
			List<Map<String,Object>> appealCountList = resRecBiz.searchAppealCount(schDeptFlow,GlobalContext.getCurrentUser().getUserFlow());
			if(appealCountList!=null && appealCountList.size()>0){
				Map<Object,Object> appealCount = new HashMap<Object, Object>();
				for(Map<String,Object> map : appealCountList){
					appealCount.put(map.get("appealKey"),map.get("appealSum"));
				}
				model.addAttribute("appealCount",appealCount);
			}
		}
		
		return "res/doctor/processView";
	}

	/**
	 * 评分
	 * @param recFlow
	 * @param recTypeId
	 * @param model
	 * @return
     * @throws Exception
     */
	@RequestMapping(value="/grade",method={RequestMethod.GET})
	public String grade(String recFlow,String recTypeId,Model model) throws Exception{
		SysUser currUser = GlobalContext.getCurrentUser();
		if(StringUtil.isNotBlank(recTypeId) && currUser!=null){
			String cfgCodeId = null;
            if (com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId)) {
				cfgCodeId = ResAssessTypeEnum.TeacherAssess.getId();
            } else if (com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)) {
				cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
            } else if (com.pinde.core.common.enums.ResRecTypeEnum.ManagerGrade.getId().equals(recTypeId)) {
                cfgCodeId = ResAssessTypeEnum.ManagerAssess.getId();
            } else if (com.pinde.core.common.enums.ResRecTypeEnum.NurseDoctorGrade.getId().equals(recTypeId)) {
				cfgCodeId = ResAssessTypeEnum.NurseDoctorAssess.getId();
			}
			
			DeptTeacherGradeInfo deptTeacherGradeInfo = resGradeBiz.readResGrade(recFlow);
			if(deptTeacherGradeInfo != null){
				model.addAttribute("rec",deptTeacherGradeInfo);
				Map<String,Object> gradeMap = resRecBiz.parseGradeXml(deptTeacherGradeInfo.getRecContent());
				model.addAttribute("gradeMap",gradeMap);
				ResAssessCfg assessCfg = assessCfgBiz.readResAssessCfg(deptTeacherGradeInfo.getCfgFlow());
				getForm(model, assessCfg);
			}else {
				ResAssessCfg search = new ResAssessCfg();
				search.setCfgCodeId(cfgCodeId);
                search.setFormStatusId(com.pinde.core.common.GlobalConstant.FLAG_Y);
				List<ResAssessCfg> resAssessCfgList = assessCfgBiz.selectByExampleWithBLOBs(search);
				model.addAttribute("formCount",resAssessCfgList.size());
				if(resAssessCfgList != null && !resAssessCfgList.isEmpty()){
					ResAssessCfg assessCfg = resAssessCfgList.get(0);
					getForm(model, assessCfg);
				}
			}
			
			/*ResAssessCfg search = new ResAssessCfg();
			search.setCfgCodeId(cfgCodeId);
			List<ResAssessCfg> assessCfgList = assessCfgBiz.searchAssessCfgList(search);
			if(assessCfgList != null && !assessCfgList.isEmpty()){
				ResAssessCfg assessCfg = assessCfgList.get(0);
				model.addAttribute("assessCfg",assessCfg);
				Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
				String titleXpath = "//title";
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				if(titleElementList != null && !titleElementList.isEmpty()){
					List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
					for(Element te :titleElementList){
						ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
						titleForm.setId(te.attributeValue("id"));
						titleForm.setName(te.attributeValue("name"));
						List<Element> itemElementList = te.elements("item");
						List<ResAssessCfgItemForm> itemFormList = null;
						if(itemElementList != null && !itemElementList.isEmpty()){
							itemFormList = new ArrayList<ResAssessCfgItemForm>();
							for(Element ie : itemElementList){
								ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
								itemForm.setId(ie.attributeValue("id"));
								itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
								itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
								itemFormList.add(itemForm);
							}
						}
						titleForm.setItemList(itemFormList);
						titleFormList.add(titleForm);
					}
					model.addAttribute("titleFormList", titleFormList);
				}
			}*/
		}
		return "res/doctor/score/grade";
	}


	private void getForm(Model model, ResAssessCfg assessCfg) throws DocumentException {
		model.addAttribute("assessCfg", assessCfg);
		Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
		String titleXpath = "//title";
		List<Element> titleElementList = dom.selectNodes(titleXpath);
		if (titleElementList != null && !titleElementList.isEmpty()) {
			List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
			for (Element te : titleElementList) {
				ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
				titleForm.setId(te.attributeValue("id"));
				titleForm.setName(te.attributeValue("name"));
				List<Element> itemElementList = te.elements("item");
				List<ResAssessCfgItemForm> itemFormList = null;
				if (itemElementList != null && !itemElementList.isEmpty()) {
					itemFormList = new ArrayList<ResAssessCfgItemForm>();
					for (Element ie : itemElementList) {
						ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
						itemForm.setId(ie.attributeValue("id"));
						itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
						itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
						itemFormList.add(itemForm);
					}
				}
				titleForm.setItemList(itemFormList);
				titleFormList.add(titleForm);
			}
			model.addAttribute("titleFormList", titleFormList);
		}
	}


	@RequestMapping(value="/doctorEval",method={RequestMethod.GET})
	public String doctorEval(String recFlow,String cfgFlow,String recTypeId,Model model,String processFlow) throws Exception{
		SysUser currUser = GlobalContext.getCurrentUser();

		//根据processFlow查找轮转科室信息
		ResDoctorSchProcess doctorSchProcess = resDoctorProcessBiz.read(processFlow);
		model.addAttribute("doctorSchProcess",doctorSchProcess);
		//获取科室下带教老师
		List<SysUser> teacherList = null;
		if(StringUtil.isNotBlank(doctorSchProcess.getDeptFlow())){
			teacherList = new ArrayList<>();
			String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
			if(StringUtil.isNotBlank(teacherRoleFlow)){
				teacherList = userBiz.searchUserByDeptAndRole(doctorSchProcess.getDeptFlow(),teacherRoleFlow);
			}
		}
		model.addAttribute("teacherList",teacherList);

		if(StringUtil.isNotBlank(recTypeId) && currUser!=null){
			String cfgContent="";

			ResEvaluationCfg cfg=evaluationCfgBiz.readResEvaluationCfg(cfgFlow);
			if(cfg != null){
				cfgContent=cfg.getCfgBigValue();
			}
			DeptTeacherGradeInfo deptTeacherGradeInfo = resGradeBiz.readResGrade(recFlow);
			if(deptTeacherGradeInfo != null){
				model.addAttribute("rec",deptTeacherGradeInfo);
				Map<String,Object> gradeMap = resRecBiz.parseDocotrGradeXml(deptTeacherGradeInfo.getRecContent());
				model.addAttribute("gradeMap",gradeMap);
				cfgContent=deptTeacherGradeInfo.getRecContent();
			}
			if(StringUtil.isNotBlank(cfgContent)){
				Document dom = DocumentHelper.parseText(cfgContent);
				String titleXpath = "//title";
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				if(titleElementList != null && !titleElementList.isEmpty()){
					List<ResEvaluationCfgTitleForm> titleFormList = new ArrayList<ResEvaluationCfgTitleForm>();
					for(Element te :titleElementList){
						ResEvaluationCfgTitleForm titleForm = new ResEvaluationCfgTitleForm();
						titleForm.setId(te.attributeValue("id"));
						titleForm.setName(te.attributeValue("name"));
						List<Element> itemElementList = te.elements("item");
						List<ResEvaluationCfgItemForm> itemFormList = null;
						if(itemElementList != null && !itemElementList.isEmpty()){
							itemFormList = new ArrayList<ResEvaluationCfgItemForm>();
							for(Element ie : itemElementList){
								ResEvaluationCfgItemForm itemForm = new ResEvaluationCfgItemForm();
								itemForm.setId(ie.attributeValue("id"));
								itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
								itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
								itemFormList.add(itemForm);
							}
						}
						titleForm.setItemList(itemFormList);
						titleFormList.add(titleForm);
					}
					model.addAttribute("titleFormList", titleFormList);
				}
			}
		}
		return "res/doctor/score/doctorEval";
	}
	@RequestMapping(value="/deptEval",method={RequestMethod.GET})
	public String deptEval(String recFlow,String processFlow,String recTypeId,Model model) throws Exception{
		SysUser currUser = GlobalContext.getCurrentUser();
		if(StringUtil.isNotBlank(recTypeId) && currUser!=null){
			String cfgContent="";
			ResEvaluationCfg search = new ResEvaluationCfg();
			search.setAssessTypeId(EvaluationTypeEnum.DeptEval.getId());
			search.setOrgFlow(currUser.getOrgFlow());
			List<ResEvaluationCfg> evaluationCfgList = evaluationCfgBiz.searchEvaluationCfgList(search);
			if(evaluationCfgList!=null&&evaluationCfgList.size()>0)
			{
				ResEvaluationCfg cfg=evaluationCfgList.get(0);
				if(cfg!=null)
					cfgContent=cfg.getCfgBigValue();
				model.addAttribute("cfg",cfg);
				ResSchProcessExpress deptEval = expressBiz.readResExpress(recFlow);
				if(deptEval==null)
					deptEval = expressBiz.queryResRecByProcessAndType(processFlow, recTypeId);
				if(deptEval != null){
					model.addAttribute("rec",deptEval);
					Map<String,Object> gradeMap = resRecBiz.parseDeptGradeXml(deptEval.getRecContent());
					model.addAttribute("gradeMap",gradeMap);
					cfgContent=deptEval.getRecContent();
				}
				if(StringUtil.isNotBlank(cfgContent)){
					Document dom = DocumentHelper.parseText(cfgContent);
					String titleXpath = "//title";
					List<Element> titleElementList = dom.selectNodes(titleXpath);
					if(titleElementList != null && !titleElementList.isEmpty()){
						List<ResEvaluationCfgTitleForm> titleFormList = new ArrayList<ResEvaluationCfgTitleForm>();
						for(Element te :titleElementList){
							ResEvaluationCfgTitleForm titleForm = new ResEvaluationCfgTitleForm();
							titleForm.setId(te.attributeValue("id"));
							titleForm.setName(te.attributeValue("name"));
							List<Element> itemElementList = te.elements("item");
							List<ResEvaluationCfgItemForm> itemFormList = null;
							if(itemElementList != null && !itemElementList.isEmpty()){
								itemFormList = new ArrayList<ResEvaluationCfgItemForm>();
								for(Element ie : itemElementList){
									ResEvaluationCfgItemForm itemForm = new ResEvaluationCfgItemForm();
									itemForm.setId(ie.attributeValue("id"));
									itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
									itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
									itemFormList.add(itemForm);
								}
							}
							titleForm.setItemList(itemFormList);
							titleFormList.add(titleForm);
						}
						model.addAttribute("titleFormList", titleFormList);
					}
				}
			}
		}
		return "res/doctor/score/deptEval";
	}

	/**
	 * 保存评分
	 * @param deptTeacherGradeInfo
	 * @param request
     * @return
     */
	@RequestMapping(value="/saveGrade",method={RequestMethod.POST})
	@ResponseBody
	public String saveGrade(DeptTeacherGradeInfo deptTeacherGradeInfo,HttpServletRequest request){
		String recContent = createGradeXml(request.getParameterMap(), "");
		if(deptTeacherGradeInfo != null){
			if(StringUtil.isBlank(deptTeacherGradeInfo.getRecFlow())){
				SysUser user = GlobalContext.getCurrentUser();
				deptTeacherGradeInfo.setOperUserFlow(user.getUserFlow());
				deptTeacherGradeInfo.setOperUserName(user.getUserName());
				deptTeacherGradeInfo.setOperTime(DateUtil.getCurrDateTime());
				if(StringUtil.isNotBlank(deptTeacherGradeInfo.getSchDeptFlow())){
					SchDept schDept = schDeptBiz.readSchDept(deptTeacherGradeInfo.getSchDeptFlow());
					if(schDept!=null){
						deptTeacherGradeInfo.setSchDeptName(schDept.getSchDeptName());
						deptTeacherGradeInfo.setSchDeptFlow(schDept.getSchDeptFlow());
						deptTeacherGradeInfo.setOrgFlow(schDept.getOrgFlow());
						deptTeacherGradeInfo.setOrgName(schDept.getOrgName());
						deptTeacherGradeInfo.setDeptFlow(schDept.getDeptFlow());
						deptTeacherGradeInfo.setDeptName(schDept.getDeptName());
					}
				}
				if(StringUtil.isNotBlank(deptTeacherGradeInfo.getRecTypeId())){
                    deptTeacherGradeInfo.setRecTypeName(com.pinde.core.common.enums.ResRecTypeEnum.getNameById(deptTeacherGradeInfo.getRecTypeId()));
				}
			}
			deptTeacherGradeInfo.setRecContent(recContent);
		}
		String allScore = recContent.split("<totalScore>")[1].split("</totalScore>")[0];
		deptTeacherGradeInfo.setAllScore(allScore);
        if (resGradeBiz.edit(deptTeacherGradeInfo) != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
			return deptTeacherGradeInfo.getRecFlow();
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}
	@RequestMapping(value="/saveDoctorEval",method={RequestMethod.POST})
	@ResponseBody
	public String saveDoctorEval(DeptTeacherGradeInfo deptTeacherGradeInfo,String cfgFlow,HttpServletRequest request) throws DocumentException {
		String recContent = createGradeXml2(request.getParameterMap(), "");
		if(deptTeacherGradeInfo != null){

			if(StringUtil.isBlank(deptTeacherGradeInfo.getRecFlow())) {
				DeptTeacherGradeInfo old=resGradeBiz.readResGradeByProcessAndType(deptTeacherGradeInfo.getProcessFlow(),deptTeacherGradeInfo.getRecTypeId());
				if(old!=null)
					deptTeacherGradeInfo.setRecFlow(old.getRecFlow());
			}
			if(StringUtil.isBlank(deptTeacherGradeInfo.getRecFlow())){
				SysUser user = GlobalContext.getCurrentUser();
				deptTeacherGradeInfo.setCfgFlow(cfgFlow);
				deptTeacherGradeInfo.setOperUserFlow(user.getUserFlow());
				deptTeacherGradeInfo.setOperUserName(user.getUserName());
				deptTeacherGradeInfo.setOperTime(DateUtil.getCurrDateTime());
				if(StringUtil.isNotBlank(deptTeacherGradeInfo.getSchDeptFlow())){
					SchDept schDept = schDeptBiz.readSchDept(deptTeacherGradeInfo.getSchDeptFlow());
					if(schDept!=null){
						deptTeacherGradeInfo.setSchDeptName(schDept.getSchDeptName());
						deptTeacherGradeInfo.setSchDeptFlow(schDept.getSchDeptFlow());
						deptTeacherGradeInfo.setOrgFlow(schDept.getOrgFlow());
						deptTeacherGradeInfo.setOrgName(schDept.getOrgName());
						deptTeacherGradeInfo.setDeptFlow(schDept.getDeptFlow());
						deptTeacherGradeInfo.setDeptName(schDept.getDeptName());
					}
				}

				if(StringUtil.isNotBlank(deptTeacherGradeInfo.getRecTypeId())){
                    deptTeacherGradeInfo.setRecTypeName(com.pinde.core.common.enums.ResRecTypeEnum.getNameById(deptTeacherGradeInfo.getRecTypeId()));
				}
				ResEvaluationCfg cfg=evaluationCfgBiz.readResEvaluationCfg(cfgFlow);
				if(cfg!=null)
				{
					Document dom = DocumentHelper.parseText(cfg.getCfgBigValue());
					Element domRoot=dom.getRootElement();
					Document evalDom = DocumentHelper.parseText(recContent);
					Element evalDomRoot=evalDom.getRootElement();

					Document document = DocumentHelper.createDocument();
					Element rootEle = document.addElement("doctorEval");
					domRoot.setParent(null);
					evalDomRoot.setParent(null);
					rootEle.add(domRoot);
					rootEle.add(evalDomRoot);
					recContent=document.asXML();
				}
			}else{
				DeptTeacherGradeInfo old=resGradeBiz.readResGrade(deptTeacherGradeInfo.getRecFlow());
				Document dom = DocumentHelper.parseText(old.getRecContent());
				Element domRoot=dom.getRootElement().element("evaluationCfg");
				Document evalDom = DocumentHelper.parseText(recContent);
				Element evalDomRoot=evalDom.getRootElement();
				Document document = DocumentHelper.createDocument();
				Element rootEle = document.addElement("doctorEval");
				domRoot.setParent(null);
				evalDomRoot.setParent(null);
				rootEle.add(domRoot);
				rootEle.add(evalDomRoot);
				recContent=document.asXML();
			}

			deptTeacherGradeInfo.setRecContent(recContent);
		}
        if (resGradeBiz.edit(deptTeacherGradeInfo) != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
			return deptTeacherGradeInfo.getRecFlow();
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value="/saveDeptEval",method={RequestMethod.POST})
	@ResponseBody
	public String saveDeptEval(ResSchProcessExpress deptEval,String cfgFlow,String operUserFlow,String roleFlag,HttpServletRequest request) throws DocumentException {
		String recContent = createGradeXml(request.getParameterMap(),roleFlag);
		String currTime = DateUtil.getCurrDateTime();
		SysUser user = GlobalContext.getCurrentUser();
		if(deptEval != null){

			if(StringUtil.isBlank(deptEval.getRecFlow())) {

				ResSchProcessExpress old = expressBiz.queryResRecByProcessAndType(deptEval.getProcessFlow(), deptEval.getRecTypeId());
				if(old!=null)
					deptEval.setRecFlow(old.getRecFlow());
			}
			if(StringUtil.isBlank(deptEval.getRecFlow())){
				SysUser operUser=userBiz.readSysUser(operUserFlow);
				deptEval.setOperUserFlow(operUserFlow);
				if(operUser!=null)
					deptEval.setOperUserName(operUser.getUserName());
				deptEval.setOperTime(DateUtil.getCurrDateTime());
				if(StringUtil.isNotBlank(deptEval.getProcessFlow())){
					ResDoctorSchProcess process=resDoctorProcessBiz.read(deptEval.getProcessFlow());
					if(process!=null){
						deptEval.setSchDeptName(process.getSchDeptName());
						deptEval.setSchDeptFlow(process.getSchDeptFlow());
						deptEval.setOrgFlow(process.getOrgFlow());
						deptEval.setOrgName(process.getOrgName());
						deptEval.setDeptFlow(process.getDeptFlow());
						deptEval.setDeptName(process.getDeptName());
					}
				}

				if(StringUtil.isNotBlank(deptEval.getRecTypeId())){
                    deptEval.setRecTypeName(com.pinde.core.common.enums.ResRecTypeEnum.getNameById(deptEval.getRecTypeId()));
				}
				ResEvaluationCfg cfg=evaluationCfgBiz.readResEvaluationCfg(cfgFlow);
				if(cfg!=null)
				{
					Document dom = DocumentHelper.parseText(cfg.getCfgBigValue());
					Element domRoot=dom.getRootElement();
					Document evalDom = DocumentHelper.parseText(recContent);
					Element evalDomRoot=evalDom.getRootElement();

					Document document = DocumentHelper.createDocument();
					Element rootEle = document.addElement("deptEval");
					domRoot.setParent(null);
					evalDomRoot.setParent(null);
					rootEle.add(domRoot);
					rootEle.add(evalDomRoot);
					recContent=document.asXML();
				}
			}else{
				ResSchProcessExpress old=expressBiz.readResExpress(deptEval.getRecFlow());
				Document dom = DocumentHelper.parseText(old.getRecContent());
				Element domRoot=dom.getRootElement().element("evaluationCfg");
				Document evalDom = DocumentHelper.parseText(recContent);
				Element evalDomRoot=evalDom.getRootElement();

				Document document = DocumentHelper.createDocument();
				Element rootEle = document.addElement("deptEval");
				domRoot.setParent(null);
				evalDomRoot.setParent(null);
				rootEle.add(domRoot);
				rootEle.add(evalDomRoot);
				if("head".equals(roleFlag))
				{
					Element teaRoot=dom.getRootElement().element("teacherGradeInfo");
					Element managerRoot=dom.getRootElement().element("managerGradeInfo");
					if(teaRoot!=null)
					{
						teaRoot.setParent(null);
						rootEle.add(teaRoot);
					}
					if(managerRoot!=null)
					{
						managerRoot.setParent(null);
						rootEle.add(managerRoot);
					}
				}
				if("teacher".equals(roleFlag))
				{
					Element headRoot=dom.getRootElement().element("headGradeInfo");
					Element managerRoot=dom.getRootElement().element("managerGradeInfo");
					if(headRoot!=null)
					{
						headRoot.setParent(null);
						rootEle.add(headRoot);
					}
					if(managerRoot!=null)
					{
						managerRoot.setParent(null);
						rootEle.add(managerRoot);
					}
				}
				if("manager".equals(roleFlag))
				{
					Element headRoot=dom.getRootElement().element("headGradeInfo");
					Element teaRoot=dom.getRootElement().element("teacherGradeInfo");
					if(teaRoot!=null)
					{
						teaRoot.setParent(null);
						rootEle.add(teaRoot);
					}
					if(headRoot!=null)
					{
						headRoot.setParent(null);
						rootEle.add(headRoot);
					}
				}
				recContent=document.asXML();
			}

			deptEval.setRecContent(recContent);
		}


		//老师审核状态
		if(request.getParameter("auditStatusId")!=null){
			deptEval.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
			deptEval.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());
			deptEval.setAuditTime(currTime);
			deptEval.setAuditUserFlow(user.getUserFlow());
			deptEval.setAuditUserName(user.getUserName());
		}

		//科主任审核状态
		if(request.getParameter("headAuditStatusId")!=null){
			deptEval.setHeadAuditStatusId(RecStatusEnum.HeadAuditY.getId());
			deptEval.setHeadAuditStatusName(RecStatusEnum.HeadAuditY.getName());
			deptEval.setHeadAuditTime(currTime);
			deptEval.setHeadAuditUserFlow(user.getUserFlow());
			deptEval.setHeadAuditUserName(user.getUserName());
		}

		//基地主任审核状态
		if(request.getParameter("managerAuditStatusId")!=null){
			deptEval.setManagerAuditStatusId(RecStatusEnum.ManagerAuditY.getId());
			deptEval.setManagerAuditStatusName(RecStatusEnum.ManagerAuditY.getName());
			deptEval.setManagerAuditStatusId(RecStatusEnum.ManagerAuditN.getId());
			deptEval.setManagerAuditStatusName(RecStatusEnum.ManagerAuditN.getName());
			deptEval.setManagerAuditTime(currTime);
			deptEval.setManagerAuditUserFlow(user.getUserFlow());
			deptEval.setManagerAuditUserName(user.getUserName());
		}

		//医院管理员审核状态
		if(request.getParameter("adminAuditStatusId")!=null){
			deptEval.setAdminAuditStatusId(RecStatusEnum.AdminAuditY.getId());
			deptEval.setAdminAuditStatusName(RecStatusEnum.AdminAuditY.getName());
			deptEval.setAdminAuditTime(currTime);
			deptEval.setAdminAuditUserFlow(user.getUserFlow());
			deptEval.setAdminAuditUserName(user.getUserName());
		}
        if (expressBiz.edit(deptEval) != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
			return deptEval.getRecFlow();
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	private String createGradeXml(Map<String, String[]> gradeInfo, String roleFlag){
		Document document = DocumentHelper.createDocument();
		String name="gradeInfo";
		if(StringUtil.isNotBlank(roleFlag))
		{
			name=roleFlag+"GradeInfo";
		}
		Element rootEle = document.addElement(name);
		if(gradeInfo!=null){
			String[] assessIds = gradeInfo.get("assessId");
			String[] scores = gradeInfo.get("score");
			String[] lostReasons = gradeInfo.get("lostReason");
			if(assessIds!=null && assessIds.length>0){
				for(int i = 0 ; i<assessIds.length ; i++){
					String assessId = assessIds[i];
					String score = scores[i];
					String lostReason = lostReasons[i];
					Element item = rootEle.addElement("grade");
					item.addAttribute("assessId",assessId);

					Element scoreElement = item.addElement("score");
					scoreElement.setText(score);
					Element lostReasonElement = item.addElement("lostReason");
					lostReasonElement.setText(lostReason);
				}
			}
			
			String[] totalScore = gradeInfo.get("totalScore");
			if(totalScore!=null && totalScore.length>0 && StringUtil.isNotBlank(totalScore[0])){
				Element item = rootEle.addElement("totalScore");
				item.setText(totalScore[0]);
			}
		}
		return document.asXML();
	}

	private String createGradeXml2(Map<String, String[]> gradeInfo, String roleFlag){
		Document document = DocumentHelper.createDocument();
		String name="gradeInfo";
		if(StringUtil.isNotBlank(roleFlag))
		{
			name=roleFlag+"GradeInfo";
		}
		Element rootEle = document.addElement(name);
		if(gradeInfo!=null){
			String[] assessIds = gradeInfo.get("assessId");
			//String[] scores = gradeInfo.get("score");
			Map<Object,String[]> map = new HashMap<>();
			if(assessIds!=null && assessIds.length>0){
				for(int i = 0 ; i<assessIds.length ; i++) {
					String[] scores = gradeInfo.get(assessIds[i]);
					map.put(i,scores);
				}
			}

			String[] lostReasons = gradeInfo.get("lostReason");
			if(assessIds!=null && assessIds.length>0){
				for(int i = 0 ; i<assessIds.length ; i++){
					String assessId = assessIds[i];
					//String score = scores[i];
					String[] scores = map.get(i);
					String score = scores[0];

					String lostReason = lostReasons[i];
					Element item = rootEle.addElement("grade");
					item.addAttribute("assessId",assessId);

					Element scoreElement = item.addElement("score");
					scoreElement.setText(score);
					Element lostReasonElement = item.addElement("lostReason");
					lostReasonElement.setText(lostReason);
				}
			}

			String[] totalScore = gradeInfo.get("totalScore");
			if(totalScore!=null && totalScore.length>0 && StringUtil.isNotBlank(totalScore[0])){
				Element item = rootEle.addElement("totalScore");
				item.setText(totalScore[0]);
			}
			String[] teach = gradeInfo.get("teach");
			if(teach!=null && teach.length>0 && StringUtil.isNotBlank(teach[0])){
				Element item = rootEle.addElement("teach");
				item.setText(teach[0]);
			}
			String[] activty = gradeInfo.get("activty");
			if(activty!=null && activty.length>0 && StringUtil.isNotBlank(activty[0])){
				Element item = rootEle.addElement("activty");
				item.setText(activty[0]);
			}
			String[] jianyi = gradeInfo.get("jianyi");
			if(jianyi!=null && jianyi.length>0 && StringUtil.isNotBlank(jianyi[0])){
				Element item = rootEle.addElement("jianyi");
				item.setText(jianyi[0]);
			}
		}
		return document.asXML();
	}

	@RequestMapping(value="/sopView",method={RequestMethod.GET})
	public String sopView(String resultFlow,Model model){
		if(StringUtil.isNotBlank(resultFlow)){
			SchRotationDept rotationDept = schRotationDeptBiz.readStandardRotationDept(resultFlow);
			model.addAttribute("rotationDept",rotationDept);
		}
		return "res/doctor/sop";
	}
	// 申述
	@RequestMapping(value="/editAppeal",method={RequestMethod.GET})
	public String editAppeal(String processFlow,String recTypeId,String userFlow,String itemId,String resultFlow,Model model){
		userFlow=StringUtil.defaultIfEmpty(userFlow, GlobalContext.getCurrentUser().getUserFlow());
		if(StringUtil.isNotBlank(resultFlow)){
			SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(resultFlow);
			ResDoctor doctor=doctorBiz.readDoctor(result.getDoctorFlow());
			List<SchRotationDeptReq> deptReqList = schRotationDeptBiz.searchStandardReqByResult(result, recTypeId);
			RegistryTypeEnum recTypeEnum =RegistryTypeEnum.valueOf(recTypeId);
            String haveItem = com.pinde.core.common.GlobalConstant.FLAG_N;
			if(recTypeEnum!=null)
			{
				haveItem=recTypeEnum.getHaveItem();
			}
			model.addAttribute("enum",haveItem);
			if(deptReqList!=null && deptReqList.size()>0){
				if (StringUtil.isNotBlank(itemId)) {
					for(SchRotationDeptReq deptReq : deptReqList){
						if (StringUtil.isNotBlank(deptReq.getItemId())&&deptReq.getItemId().equals(itemId)) {
							model.addAttribute("req", deptReq);
							break;
						}
					}
				}
				List<String> recTypeIds = new ArrayList<String>();
				for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + regType.getId()))) {
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(regType.getHaveAppeal())) {
							recTypeIds.add(regType.getId());
						}
					}
				}
				model.addAttribute("deptReqList",deptReqList);
			}
		}
		
		if (StringUtil.isNotBlank(itemId)) {
			List<ResAppeal> appeal= resRecBiz.searResAppeal(processFlow,recTypeId,userFlow,itemId);
			if (appeal!=null&&!appeal.isEmpty()) {
				model.addAttribute("appeal",appeal.get(0));
			}
			int count=resRecBiz.searResRecWan(processFlow,recTypeId,itemId);
			model.addAttribute("count",count);
		}
		
		return "res/doctor/appeal/editAppeal";
	}
	
	@RequestMapping(value="/saveAppeal",method={RequestMethod.POST})
	@ResponseBody
	public String saveAppeal(ResAppeal appeal){
		if(appeal!=null){
			if(StringUtil.isNotBlank(appeal.getRecTypeId())){
				appeal.setRecTypeName(RegistryTypeEnum.getNameById(appeal.getRecTypeId()));
			}
			if(StringUtil.isNotBlank(appeal.getStatusId())){
				appeal.setStatusName(RecStatusEnum.getNameById(appeal.getStatusId()));
			}
			if(!StringUtil.isNotBlank(appeal.getAppealFlow())){
				appeal.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
				appeal.setOrgName(GlobalContext.getCurrentUser().getOrgName());
				appeal.setStatusId(RecStatusEnum.Edit.getId());
				appeal.setStatusName(RecStatusEnum.Edit.getName());
				if(StringUtil.isNotBlank(appeal.getSchDeptFlow())){
					SchDept schDept = schDeptBiz.readSchDept(appeal.getSchDeptFlow());
					if(schDept!=null){
						appeal.setDeptFlow(schDept.getDeptFlow());
						appeal.setDeptName(schDept.getDeptName());
						appeal.setSchDeptFlow(schDept.getSchDeptFlow());
						appeal.setSchDeptName(schDept.getSchDeptName());
					}
				}
			}
			appeal.setAuditStatusId("");
			appeal.setAuditStatusName("");
            if (resRecBiz.editAppeal(appeal) != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}
	
	@RequestMapping(value="/operAppeal",method={RequestMethod.POST})
	@ResponseBody
	public String operAppeal(ResAppeal appeal){
        String deleteS = com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
        String deleteF = com.pinde.core.common.GlobalConstant.OPRE_FAIL;
		if(appeal!=null){
//			if(StringUtil.isNotBlank(appeal.getStatusId())){
//				appeal.setStatusName(RecStatusEnum.getNameById(appeal.getStatusId()));
//			}
			
			if(StringUtil.isNotBlank(appeal.getAuditStatusId())){
				appeal.setAuditStatusName(RecStatusEnum.getNameById(appeal.getAuditStatusId()));
				appeal.setAuditTime(DateUtil.getCurrDateTime());
				appeal.setAuditUserFlow(GlobalContext.getCurrentUser().getUserFlow());
				appeal.setAuditUserName(GlobalContext.getCurrentUser().getUserName());
//				if(RecStatusEnum.TeacherAuditN.getId().equals(appeal.getAuditStatusId())){
//					appeal.setStatusId(RecStatusEnum.Edit.getId());
//					appeal.setStatusName(RecStatusEnum.getNameById(appeal.getStatusId()));
//				}
			}
			
//			if(StringUtil.isNotBlank(appeal.getHeadAuditStatusId())){
//				appeal.setHeadAuditStatusName(RecStatusEnum.getNameById(appeal.getHeadAuditStatusId()));
//				appeal.setHeadAuditTime(DateUtil.getCurrDateTime());
//				appeal.setHeadAuditUserFlow(GlobalContext.getCurrentUser().getUserFlow());
//				appeal.setHeadAuditUserName(GlobalContext.getCurrentUser().getUserName());
//				if(RecStatusEnum.HeadAuditN.getId().equals(appeal.getAuditStatusId())){
//					appeal.setStatusId(RecStatusEnum.Edit.getId());
//					appeal.setStatusName(RecStatusEnum.getNameById(appeal.getStatusId()));
//				}
//			}
			
			if(StringUtil.isNotBlank(appeal.getRecordStatus())){
                deleteS = com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
                deleteF = com.pinde.core.common.GlobalConstant.DELETE_FAIL;
			}

            if (resRecBiz.editAppeal(appeal) != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
				return deleteS;
			}
		}
		return deleteF;
	}
	
	@RequestMapping(value="/appealList",method={RequestMethod.GET,RequestMethod.POST})
	public String appealList(ResAppeal appeal,String startTime,String endTime,Model model){
		if(appeal == null){
			appeal = new ResAppeal();
		}
		appeal.setOperUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		List<ResAppeal> appealList = resRecBiz.searchAppeal(appeal,startTime,endTime);
		model.addAttribute("appealList",appealList);
		
		return "res/doctor/appeal/appealList";
	}
	
	// 评分列表
	@RequestMapping(value="/score/dept",method={RequestMethod.GET,RequestMethod.POST})
	public String dept(Model model){
        _setModelAttribute(com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId(), model);
		return "res/doctor/score/dept";
	}
	
	@RequestMapping(value="/score/teacher",method={RequestMethod.GET,RequestMethod.POST})
	public String teacher(Model model){
        _setModelAttribute(com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId(), model);
		return "res/doctor/score/teacher";
	}
	
	private void _setModelAttribute(String recTypeId,Model model){
		List<String> doctorFlows  = new ArrayList<String>();
		doctorFlows.add(GlobalContext.getCurrentUser().getUserFlow());
		List<SchArrangeResult> arrResultList =  schArrangeResultBiz.searchArrangeResultByDoctorFlows(doctorFlows);
		if(arrResultList!=null&&!arrResultList.isEmpty()){
			List<String> schResultFlows = new ArrayList<String>();
			List<String> schDeptFLows = new ArrayList<String>();
			for (SchArrangeResult result : arrResultList) {
				schResultFlows.add(result.getResultFlow());
				schDeptFLows.add(result.getSchDeptFlow());
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
			
			List<ResRec> recList = resRecBiz.searchByRec(recTypeId,schDeptFLows,GlobalContext.getCurrentUser().getUserFlow());
			if(recList!=null && recList.size()>0){
				Map<String,ResRec> recMap = new HashMap<String, ResRec>();
				Map<String,Map<String,Object>> recContentMap = new HashMap<String, Map<String,Object>>();
				
				for(ResRec rec : recList){
					recMap.put(rec.getSchDeptFlow(),rec);
					
					Map<String,Object> contentMap = resRecBiz.parseGradeXml(rec.getRecContent());
					recContentMap.put(rec.getRecFlow(),contentMap);
				}
				
				model.addAttribute("recMap",recMap);
				model.addAttribute("recContentMap",recContentMap);
			}
		}
	}
	
	/**
	 * 入科视图
	 * */
	@RequestMapping(value="/inDeptView",method={RequestMethod.GET})
	public String inDeptView(String processFlow,Model model){
		SysUser currentUser = GlobalContext.getCurrentUser();
		ResDoctorSchProcess process = resDoctorProcessBiz.read(processFlow);
		model.addAttribute("process",process);
		model.addAttribute("docUser",currentUser);
		if(process!=null){
			model.addAttribute("process",process);

            List<ResRec> recList = resRecBiz.searchByRec(com.pinde.core.common.enums.ResRecTypeEnum.PreTrainForm.getId(), process.getSchDeptFlow(), process.getUserFlow());
			if(recList!=null && recList.size()>0){
				model.addAttribute("preTrainFormRec",recList.get(0));
			}
		}
		
		return "res/edu/student/inDeptView";
	}
	
	/**
	 * 出科视图
	 * */
	@RequestMapping(value="/outDeptView",method={RequestMethod.GET})
	public String outDeptView(String processFlow,Model model){
		SysUser currentUser = GlobalContext.getCurrentUser();
		ResDoctorSchProcess process = resDoctorProcessBiz.read(processFlow);
		model.addAttribute("process",process);
		model.addAttribute("docUser",currentUser);
		List<ExamResults> resultses = resultsBiz.getByProcessFlow(processFlow);
		model.addAttribute("resultses",resultses);
		if (process != null) {
			//出科考核成绩
			ResScore score = scoreBiz.getScoreByProcess(processFlow);
			model.addAttribute("outScore",score);
			ResDoctor doctor=doctorBiz.readDoctor(currentUser.getUserFlow());
			model.addAttribute("doctor",doctor);

			String schDeptFlow = process.getSchDeptFlow();
			SchDept schDept=schDeptBiz.readSchDept(schDeptFlow);
            String haveMonth = com.pinde.core.common.GlobalConstant.FLAG_N;
			if(schDept!=null)
			{
				String monthFlag = InitConfig.getSysCfg("jswjw_"+schDept.getOrgFlow()+"_M001");
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(monthFlag))
				{
                    haveMonth = com.pinde.core.common.GlobalConstant.FLAG_Y;
				}
			}
			model.addAttribute("isMonthOpen",haveMonth);
			String schResultFlow = process.getSchResultFlow();
			if(StringUtil.isNotBlank(schResultFlow)){
				SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(schResultFlow);
				model.addAttribute("result",result);
			}
			String cfg13=InitConfig.getSysCfg("jswjw_"+doctor.getOrgFlow()+"_P013");
			String f=InitConfig.getSysCfg("res_isGlobalSch_flag");
			model.addAttribute("cfg13",cfg13);
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(cfg13) && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(f) || com.pinde.core.common.GlobalConstant.FLAG_Y.equals(f)) {
				//评价带教老师,评价科室
				List<String> recTypeIds = new ArrayList<String>();
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId());
				//添加评价管理员
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.ManagerGrade.getId());

				Map<String, Object> paramMap = new HashMap();
				paramMap.put("recTypeIds", recTypeIds);
				paramMap.put("processFlow", processFlow);
				paramMap.put("currentUserFlow", currentUser.getUserFlow());
				List<DeptTeacherGradeInfo> gradeInfoList = resGradeBiz.searchResGradeByItems(paramMap);
				if (gradeInfoList != null && gradeInfoList.size() > 0) {
					for (DeptTeacherGradeInfo tempGradeInfo : gradeInfoList) {
						String recTypeId = tempGradeInfo.getRecTypeId();
						model.addAttribute(recTypeId, tempGradeInfo);
						Map<String, Object> gradeMap = resRecBiz.parseGradeXml(tempGradeInfo.getRecContent());
						model.addAttribute(recTypeId + "Map", gradeMap);
					}
				}
			}else{
				String deptFlow=process.getDeptFlow();

				ResEvaluationCfg evaluation = evaluationCfgBiz.readResEvaluationCfgByDept(deptFlow);
				model.addAttribute("EvaluationCfg", evaluation);

				List<String> recTypeIds = new ArrayList<String>();
				recTypeIds.add(EvaluationTypeEnum.DoctorEval.getId());
				Map<String, Object> paramMap = new HashMap();
				paramMap.put("recTypeIds", recTypeIds);
				paramMap.put("processFlow", processFlow);
				paramMap.put("currentUserFlow", currentUser.getUserFlow());
				List<DeptTeacherGradeInfo> gradeInfoList = resGradeBiz.searchResGradeByItems(paramMap);
				if (gradeInfoList != null && gradeInfoList.size() > 0) {
					for (DeptTeacherGradeInfo tempGradeInfo : gradeInfoList) {
						String recTypeId = tempGradeInfo.getRecTypeId();
						model.addAttribute(recTypeId, tempGradeInfo);
						Map<String, Object> dataMap = resRecBiz.parseDocotrGradeXml(tempGradeInfo.getRecContent());
						model.addAttribute(recTypeId + "Map", dataMap);
					}
				}
			}

			//除评价带教老师,评价科室之外的其他出科内容
			List<String> recTypeIds = new ArrayList<String>();
			for (AfterRecTypeEnum after : AfterRecTypeEnum.values()) {
				String typeId = after.getId();
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_" + typeId + "_form_flag"))) {
					recTypeIds.add(typeId);
				}
			}
			List<ResSchProcessExpress> deptGradeList = expressBiz.searchRecByProcessWithBLOBs(recTypeIds, processFlow);
			if (deptGradeList != null && deptGradeList.size() > 0) {
				for (ResSchProcessExpress rec : deptGradeList) {
					String recTypeId = rec.getRecTypeId();
					model.addAttribute(recTypeId, rec);
					Map<String, Object> dataMap = resRecBiz.parseContent(rec.getRecContent());
					model.addAttribute(recTypeId + "Map", dataMap);
				}
			}
			//后台出科考试权限
			ResPowerCfg resPowerCfg = resPowerCfgBiz.read("res_doctor_ckks_"+currentUser.getUserFlow());
			if(resPowerCfg!=null){
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resPowerCfg.getCfgValue())
                        && com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(resPowerCfg.getRecordStatus())
//						&& com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_permit_open_doc"))
						){
                    model.addAttribute("open", com.pinde.core.common.GlobalConstant.FLAG_Y);
				}else{
                    model.addAttribute("open", com.pinde.core.common.GlobalConstant.FLAG_N);
				}
			}else {
                model.addAttribute("open", com.pinde.core.common.GlobalConstant.FLAG_N);
			}
		}
		return "res/edu/student/outDeptView";
	}

    /**
     *
     * @param recTypeId
     * @param operUserFlow
     * @param model
     * @return
     */
	@RequestMapping(value="/showRecForm",method={RequestMethod.GET,RequestMethod.POST})
	public String showRecForm(String recTypeId,String operUserFlow, Model model){
		ResRec rec = resRecBiz.searchRecWithBLOBs(recTypeId,operUserFlow);
		if(rec!=null){
			String recContent = rec.getRecContent();
			Map<String,Object> formDataMap = resRecBiz.parseContent(recContent);
			
			model.addAttribute("rec",rec);
			model.addAttribute("formDataMap", formDataMap);	
			model.addAttribute("formFileName", recTypeId);
			String currVer = rec.getRecVersion();
			String recForm = rec.getRecForm();
			String medicineTypeId = rec.getMedicineType();
			
			return resRecBiz.getFormPath(recTypeId,currVer,recForm, "",medicineTypeId);
		}else{
			ResDoctor doctor=doctorBiz.readDoctor(operUserFlow);
			SysUser user=userBiz.readSysUser(operUserFlow);
			String rotationFlow=null;
			if(doctor!=null){
				rotationFlow=doctor.getRotationFlow();
			}
			String medicineTypeId = "";
			if(user!=null)
				medicineTypeId=user.getMedicineTypeId();
			model.addAttribute("formFileName", recTypeId);
			return resRecBiz.getFormPath(rotationFlow,recTypeId,null,null, "",medicineTypeId,"");
		}
		//return "error/404";
	}

	/**
	 * 删除Resrec
	 * @param rec
	 * @return
     */
	@RequestMapping("/delResrec")
	@ResponseBody
	public String delResrec(ResRec rec){
		int result=resRecBiz.editRec(rec);
        if (result == com.pinde.core.common.GlobalConstant.ONE_LINE) {
            return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
	}
	@RequestMapping(value="/viewForm",method={RequestMethod.GET,RequestMethod.POST})
	public String viewForm(String recTypeId,String formId, Model model){
		if(StringUtil.isNotBlank(formId) && StringUtil.isNotBlank(recTypeId)){
			String currVer = InitConfig.resFormRequestUtil.getVersionMap().get(formId+"_"+recTypeId);
			if(StringUtil.isBlank(currVer)){
                currVer = InitConfig.resFormRequestUtil.getVersionMap().get(com.pinde.core.common.GlobalConstant.RES_FORM_PRODUCT + "_" + recTypeId);
			}
			if(StringUtil.isBlank(currVer)){
                currVer = com.pinde.core.common.GlobalConstant.RES_FORM_PRODUCT_VER;
			}
			Map<String,IrbSingleForm> singleFormMap = InitConfig.resFormRequestUtil.getFormMap().get(recTypeId);
			
			IrbSingleForm singleForm = singleFormMap.get(formId+"_"+currVer);
			
			if(singleForm == null){
                singleForm = singleFormMap.get(com.pinde.core.common.GlobalConstant.RES_FORM_PRODUCT + "_" + currVer);
			}
			
			if(singleForm == null){
                throw new RuntimeException("未发现表单 模版类型:" + formId + ",表单类型:" + com.pinde.core.common.enums.ResRecTypeEnum.getNameById(recTypeId) + ",版本号:" + currVer);
			}
			
			String jspPath = singleForm.getJspPath();
			
			if(StringUtil.isNotBlank(jspPath)){
//				jspPath = MessageFormat.format(jspPath,singleForm.getProductType(),singleForm.getVersion());
                jspPath = MessageFormat.format(jspPath,singleForm.getProductType(),singleForm.getVersion(),"");
			}
			model.addAttribute("jspPath",jspPath);
			return jspPath;
		}
		return "error/404";
	}
	
	@RequestMapping(value="/savePreTrainForm",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String savePreTrainForm(String recFlow,String resultFlow,String roleFlag,HttpServletRequest req){
		int result = resRecBiz.editPreTrainForm(recFlow,resultFlow,roleFlag,req);
        if (result != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
	}
	
	@RequestMapping(value="/saveAnnualTrainForm",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveAnnualTrainForm(String recFlow,String roleFlag,HttpServletRequest req){
		int result = resRecBiz.editAnnualTrainForm(recFlow,roleFlag,req);
        if (result != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
	}

	
	/**
	 * 学员操作出科
	 * @param userFlow
	 * @param schDeptFlow
	 * @param processFlow
	 * @return
	 */
	@RequestMapping(value="/docOperAfter",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String docOperAfter(String userFlow,String schDeptFlow,String processFlow){
		if(StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(schDeptFlow)){
            List<ResRec> preTrainList = resRecBiz.searchByRec(com.pinde.core.common.enums.ResRecTypeEnum.PreTrainForm.getId(), schDeptFlow, userFlow);
			if(preTrainList!=null && preTrainList.size()>0){
				ResRec preTrain = preTrainList.get(0);
				if(preTrain!=null && RecStatusEnum.TeacherAuditY.getId().equals(preTrain.getAuditStatusId())){
                    List<ResRec> teacherGradeList = resRecBiz.searchByRec(com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId(), schDeptFlow, userFlow);
					if(teacherGradeList!=null && teacherGradeList.size()>0){
						if(StringUtil.isNotBlank(processFlow)){
							ResDoctorSchProcess process = new ResDoctorSchProcess();
							process.setProcessFlow(processFlow);
                            process.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
                            process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
							int result = resDoctorProcessBiz.edit(process);
                            if (result != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
                                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
							}
						}
					}
				}
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
	}
	
	/**
	 * 培训登记
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/speRegistry/{roleFlag}")
	public String speRegistry(@PathVariable String roleFlag,String doctorFlow,Model model){
		model.addAttribute("roleFlag",roleFlag);
		
		doctorFlow = StringUtil.defaultIfEmpty(doctorFlow,GlobalContext.getCurrentUser().getUserFlow());
		
		ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
		model.addAttribute("doctor",doctor);
		return "res/doctor/speRegistry";
	}
	
	/**
	 * 获取多个表单内的属性值
	 * @param processFlow
	 * @return
	 */
	@RequestMapping(value="/getRecDatas")
	@ResponseBody
	public Object getRecDatas(String[] recTypeId,String processFlow){
		Map<String, Object> resRecJson = null;
		if(recTypeId!=null && recTypeId.length>0){
			List<String> recTypeIds = Arrays.asList(recTypeId);
			
			resRecJson = new HashMap<String, Object>();
			Map<String, Object> formDataMap = null;
			Map<String, Object> nMap = null;
			List<ResSchProcessExpress> recs=resRecBiz.searchRecByProcessWithBLOBs(processFlow,recTypeIds);
			for (ResSchProcessExpress resRec : recs) {
				if (resRecJson.get(resRec.getRecTypeId())==null){
					formDataMap = resRecBiz.parseRecContent(resRec.getRecContent());
					resRec.setRecContent("");
					nMap=new HashMap<String, Object>();
					nMap.put("formDataMap", formDataMap);	
					nMap.put("rec",resRec);
					resRecJson.put(resRec.getRecTypeId(), nMap);
				}
			}
		}
		return resRecJson;
	}
	
	/**
	 * 获取模版需要使用的数据
	 * @param doctorFlow
	 * @return
	 */
	private Map<String,Object> getRegInfo(String doctorFlow){
		Map<String,Object> tempDataMap = new HashMap<String, Object>();
		return tempDataMap;
	}
	
	
	private WordprocessingMLPackage getDocx(HttpServletRequest request,Map<String,Object> map)throws Exception{
		List<WordprocessingMLPackage> addTemplates = new ArrayList<WordprocessingMLPackage>();
		ResDoctor doctor=(ResDoctor) map.get("doctor");
		Map<String, Object> dataMap1 = new HashMap<String,Object>();
		if (doctor!=null) {
			dataMap1.put("doctorName", doctor.getDoctorName());
			dataMap1.put("orgName", doctor.getOrgName());
		}
		WordprocessingMLPackage jszy = new WordprocessingMLPackage();
		String path1 = "/jsp/res/form/njzyykdxzs/njzyykdxzsTemeplete.docx";//模板
		ServletContext context =  request.getServletContext();
		String watermark = "";
		jszy = Docx4jUtil.convert(new File(context.getRealPath(path1)),dataMap1,watermark,true);
		addTemplates.add(jszy);
		List<SchArrangeResult> resultList=(List<SchArrangeResult>) map.get("resultList");
		Map<String, ResDoctorSchProcess> processMap=(Map<String, ResDoctorSchProcess>) map.get("processMap");
		Map<String,List<Map<String,String>>> mapDataList=(Map<String, List<Map<String, String>>>) map.get("mapDataList");
        String recTypeIdAfterSummary = com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId();
        String recTypeIdAfterEvaluation = com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId();
        String recTypeIdCaseRegistry = com.pinde.core.common.enums.ResRecTypeEnum.CaseRegistry.getId();
        String recTypeIdEmergencyCase = com.pinde.core.common.enums.ResRecTypeEnum.EmergencyCase.getId();
        String recTypeIdSkillAndOperationRegistry = com.pinde.core.common.enums.ResRecTypeEnum.SkillAndOperationRegistry.getId();
        String recTypeIdCampaignRegistry = com.pinde.core.common.enums.ResRecTypeEnum.CampaignRegistry.getId();
		for (SchArrangeResult schArrangeResult : resultList) {
			if (processMap==null) {
				continue;
			}
			ResDoctorSchProcess doctorSchProcess=processMap.get(schArrangeResult.getResultFlow());
			if (doctorSchProcess==null) {
				continue;
			}
			Map<String, Object> dataMap2 = new HashMap<String,Object>();
			String processFlow=doctorSchProcess.getProcessFlow();
			String key=processFlow+recTypeIdAfterSummary;
			List<Map<String,String>> mapList=mapDataList.get(key);
			if (mapList!=null&&mapList.size()>0) {
				Map<String,String> AfterSummaryMap=mapList.get(0);
				dataMap2.put("AfterSummary_basicCheckBranch",AfterSummaryMap.get("basicCheckBranch"));
				dataMap2.put("AfterSummary_basicSelfratingBranch",AfterSummaryMap.get("basicSelfratingBranch"));
				dataMap2.put("AfterSummary_disciplineCheckBranch",AfterSummaryMap.get("disciplineCheckBranch"));
				dataMap2.put("AfterSummary_disciplineSelfratingBranch",AfterSummaryMap.get("disciplineSelfratingBranch"));
				dataMap2.put("AfterSummary_uniteCheckBranch",AfterSummaryMap.get("uniteCheckBranch"));
				dataMap2.put("AfterSummary_uniteSelfratingBranch",AfterSummaryMap.get("uniteSelfratingBranch"));
				dataMap2.put("AfterSummary_activityCheckBranch",AfterSummaryMap.get("activityCheckBranch"));
				dataMap2.put("Summary_activitySelfratingBranch",AfterSummaryMap.get("activitySelfratingBranch"));
				dataMap2.put("AfterSummary_attendanceCheckBranch",AfterSummaryMap.get("attendanceCheckBranch"));
				dataMap2.put("AfterSummary_attendanceSelfratingBranch",AfterSummaryMap.get("attendanceSelfratingBranch"));
				dataMap2.put("AfterSummary_attitudeCheckBranch",AfterSummaryMap.get("attitudeCheckBranch"));
				dataMap2.put("AfterSummary_attitudeSelfratingBranch",AfterSummaryMap.get("attitudeSelfratingBranch"));
				dataMap2.put("AfterSummary_technologyCheckBranch",AfterSummaryMap.get("technologyCheckBranch"));
				dataMap2.put("AfterSummary_technologySelfratingBranch",AfterSummaryMap.get("technologySelfratingBranch"));
				dataMap2.put("AfterSummary_studyCheckBranch",AfterSummaryMap.get("studyCheckBranch"));
				dataMap2.put("AfterSummary_studySelfratingBranch",AfterSummaryMap.get("studySelfratingBranch"));
				dataMap2.put("AfterSummary_checkBranchThetotalscore",AfterSummaryMap.get("checkBranchThetotalscore"));
				dataMap2.put("AfterSummary_selfratingBranchThetotalscore",AfterSummaryMap.get("selfratingBranchThetotalscore"));
				dataMap2.put("AfterSummary_accident",AfterSummaryMap.get("accident"));
				dataMap2.put("AfterSummary_error",AfterSummaryMap.get("error"));
				dataMap2.put("AfterSummary_wrong",AfterSummaryMap.get("wrong"));
			}
			String afterEvaluationKey=processFlow+recTypeIdAfterEvaluation;
			List<Map<String,String>> afterEvaluationMapList=mapDataList.get(afterEvaluationKey);
			if (afterEvaluationMapList!=null&&afterEvaluationMapList.size()>0) {
				Map<String,String> afterEvaluationMap=afterEvaluationMapList.get(0);
				dataMap2.put("Evaluation_departmentName",afterEvaluationMap.get("departmentName"));
				dataMap2.put("Evaluation_april",afterEvaluationMap.get("april"));
				dataMap2.put("Evaluation_responsibility",afterEvaluationMap.get("responsibility"));
				dataMap2.put("Evaluation_attitude",afterEvaluationMap.get("attitude"));
				dataMap2.put("Evaluation_doctor",afterEvaluationMap.get("doctor"));
				dataMap2.put("Evaluation_unite",afterEvaluationMap.get("unite"));
				dataMap2.put("Evaluation_subject",afterEvaluationMap.get("subject"));
				dataMap2.put("Evaluation_disease",afterEvaluationMap.get("disease"));
				dataMap2.put("Evaluation_diseases",afterEvaluationMap.get("diseases"));
				dataMap2.put("Evaluation_quality",afterEvaluationMap.get("quality"));
				dataMap2.put("Evaluation_skill",afterEvaluationMap.get("skill"));
				dataMap2.put("Evaluation_theoryAssessment",afterEvaluationMap.get("theoryAssessment"));
				dataMap2.put("Evaluation_readingNotes",afterEvaluationMap.get("readingNotes"));
				dataMap2.put("Evaluation_activity",afterEvaluationMap.get("activity"));
				dataMap2.put("Evaluation_attendance",afterEvaluationMap.get("attendance"));
				dataMap2.put("Evaluation_ability",afterEvaluationMap.get("ability"));
				dataMap2.put("Evaluation_situation",afterEvaluationMap.get("situation"));
				dataMap2.put("Evaluation_opinion",afterEvaluationMap.get("opinion"));
				dataMap2.put("Evaluation_guideTeacher",afterEvaluationMap.get("guideTeacher"));
				dataMap2.put("Evaluation_branchDirector",afterEvaluationMap.get("branchDirector"));
				dataMap2.put("Evaluation_date",afterEvaluationMap.get("date"));
			}
			String caseRegistryKey=processFlow+recTypeIdCaseRegistry;
			List<Map<String,String>> caseRegistryMapList=mapDataList.get(caseRegistryKey);
			List<ItemGroupData> caseRegistryList = new ArrayList<ItemGroupData>();
			if (caseRegistryMapList!=null&&caseRegistryMapList.size()>0) {
				for (Map<String, String> caseRegistryMap : caseRegistryMapList) {
					Map<String, Object> objMap=new HashMap<String, Object>();
					objMap.put("CaseRegistry_diseaseName",caseRegistryMap.get("diseaseName"));
					objMap.put("CaseRegistry_hospitalNumbers",caseRegistryMap.get("hospitalNumbers"));
					objMap.put("CaseRegistry_type",caseRegistryMap.get("type"));
					objMap.put("CaseRegistry_result",caseRegistryMap.get("result"));
					objMap.put("CaseRegistry_treatmentWay",caseRegistryMap.get("treatmentWay"));
					objMap.put("CaseRegistry_comprehensiveWay",caseRegistryMap.get("comprehensiveWay"));
					ItemGroupData  igd = new ItemGroupData();
					igd.setObjMap(objMap);
					caseRegistryList.add(igd);
				}
			}
			dataMap2.put("caseRegistryList", caseRegistryList);
			
			String emergencyCaseKey=processFlow+recTypeIdEmergencyCase;
			List<Map<String,String>> emergencyCaseMapList=mapDataList.get(emergencyCaseKey);
			List<ItemGroupData> emergencyCaseList = new ArrayList<ItemGroupData>();
			if (emergencyCaseMapList!=null&&emergencyCaseMapList.size()>0) {
				for (Map<String, String> emergencyCaseMap : emergencyCaseMapList) {
					Map<String, Object> objMap=new HashMap<String, Object>();
					objMap.put("emergencyCase_case",emergencyCaseMap.get("case"));
					objMap.put("emergencyCase_diseaseName",emergencyCaseMap.get("diseaseName"));
					objMap.put("emergencyCase_cases",emergencyCaseMap.get("cases"));
					objMap.put("emergencyCase_date",emergencyCaseMap.get("date"));
					objMap.put("emergencyCase_teacherSignature",emergencyCaseMap.get("teacherSignature"));
					ItemGroupData  igd = new ItemGroupData();
					igd.setObjMap(objMap);
					emergencyCaseList.add(igd);
				}
			}
			dataMap2.put("emergencyCaseList", emergencyCaseList);
			
			String SkillAndOperationRegistryKey=processFlow+recTypeIdSkillAndOperationRegistry;
			List<Map<String,String>> OperationRegistryListMapList=mapDataList.get(SkillAndOperationRegistryKey);
			List<ItemGroupData> OperationRegistryListList = new ArrayList<ItemGroupData>();
			if (OperationRegistryListMapList!=null&&OperationRegistryListMapList.size()>0) {
				for (Map<String, String> OperationRegistryMap : OperationRegistryListMapList) {
					Map<String, Object> objMap=new HashMap<String, Object>();
					objMap.put("sperationRegistry_date",OperationRegistryMap.get("date"));
					objMap.put("sperationRegistry_skillName",OperationRegistryMap.get("skillName"));
					objMap.put("sperationRegistry_status",OperationRegistryMap.get("status"));
					objMap.put("sperationRegistry_firstAssistant",OperationRegistryMap.get("firstAssistant"));
					objMap.put("sperationRegistry_secondAssistant",OperationRegistryMap.get("secondAssistant"));
					objMap.put("sperationRegistry_assessment",OperationRegistryMap.get("assessment"));
					ItemGroupData  igd = new ItemGroupData();
					igd.setObjMap(objMap);
					OperationRegistryListList.add(igd);
				}
			}
			dataMap2.put("OperationRegistryListList", OperationRegistryListList);
			
			String campaignRegistryKey=processFlow+recTypeIdCampaignRegistry;
			List<Map<String,String>> campaignRegistryMapList=mapDataList.get(campaignRegistryKey);
			List<ItemGroupData> campaignRegistryList = new ArrayList<ItemGroupData>();
			if (campaignRegistryMapList!=null&&campaignRegistryMapList.size()>0) {
				for (Map<String, String> campaignRegistryMap : campaignRegistryMapList) {
					Map<String, Object> objMap=new HashMap<String, Object>();
					objMap.put("campaignRegistry_date",campaignRegistryMap.get("title"));
					objMap.put("campaignRegistry_skillName",campaignRegistryMap.get("articleTitle"));
					objMap.put("campaignRegistry_status",campaignRegistryMap.get("teaching"));
					objMap.put("campaignRegistry_firstAssistant",campaignRegistryMap.get("lectureTitle"));
					objMap.put("campaignRegistry_assessment",campaignRegistryMap.get("assessment"));
					ItemGroupData  igd = new ItemGroupData();
					igd.setObjMap(objMap);
					campaignRegistryList.add(igd);
				}
			}
			dataMap2.put("campaignRegistryList", campaignRegistryList);
			
			dataMap2.put("schDeptName", schArrangeResult.getSchDeptName());
			WordprocessingMLPackage jszysc = new WordprocessingMLPackage();
			String path2 = "/jsp/res/form/njzyykdxzs/njzyykdxzsTemepleteSub.docx";//模板
			jszysc = Docx4jUtil.convert(new File(context.getRealPath(path2)),dataMap2,watermark,true);
			addTemplates.add(jszysc);
		}
		return Docx4jUtil.mergeDocx(addTemplates);
	}
	
	private Map<String,Object> mapData(String doctorFlow) throws Exception{
		Map<String,Object> map=new HashMap<String, Object>();
		//模板一所需数据
		ResDoctor doctor=resDoctorBiz.searchByUserFlow(doctorFlow);
		if (doctor!=null) {
			map.put("doctor", doctor);
		}
		//某科
		List<SchArrangeResult> resultList=schArrangeResultBiz.searchSchArrangeResultByDoctor(doctorFlow);
		if (resultList!=null) {
			map.put("resultList", resultList);
		}
		//组织ResDoctorSchProcessMap，key为SchResultFlow(RESULT_FLOW主键)值为resDoctorSchProcess
		List<ResDoctorSchProcess>  doctorSchProcessList=resDoctorProcessBiz.searchProcessByDoctor(doctorFlow);
		Map<String, ResDoctorSchProcess> processMap=new HashMap<String, ResDoctorSchProcess>();
		for (ResDoctorSchProcess resDoctorSchProcess : doctorSchProcessList) {
			processMap.put(resDoctorSchProcess.getSchResultFlow(), resDoctorSchProcess);
		}
		map.put("processMap", processMap);
		//获取该用户下所有resrec记录
		List<ResRec> resRecList= resRecBiz.searchByUserFlow(doctorFlow);
		
		Map<String,List<Map<String,Object>>> mapDataList=new HashMap<String, List<Map<String,Object>>>();
		for (ResRec resRec : resRecList) {
			String key=resRec.getProcessFlow()+resRec.getRecTypeId();
			String content=resRec.getRecContent();
			Map<String,Object> formDataMap=resRecBiz.parseRecContent(content);
			if (mapDataList.get(key)!=null) {
				mapDataList.get(key).add(formDataMap);
			}else{
				List<Map<String,Object>> mapList=new ArrayList<Map<String,Object>>();
				mapList.add(formDataMap);
				mapDataList.put(key, mapList);
			}
		}
		map.put("mapDataList", mapDataList);
		return map;
	}
	@RequestMapping(value="/dengJi")
	public void dengJi(HttpServletRequest request, HttpServletResponse response,String doctorFlow)throws Exception{
		SysUser sysUser=GlobalContext.getCurrentUser();
		doctorFlow=StringUtil.defaultIfEmpty(doctorFlow, sysUser.getUserFlow());
		Map<String,Object> map=mapData(doctorFlow);
		WordprocessingMLPackage temeplete = getDocx(request,map);
		
		if(temeplete!=null){
			String name = "江苏省中医类别住院医师规范化培训记录暨考核手册(试行).docx"; 
			response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +""); 
			response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			ServletOutputStream out = response.getOutputStream ();
			(new SaveToZipFile (temeplete)).save (out);
			out.flush ();
		}
	}
	@RequestMapping(value="/hdxsevaluationSun")
	@ResponseBody
	public List<Map<String, Object>> hdxsevaluationSun(String operUserFlow,
			String processFlow,
			Model model){

        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.CampaignRegistry.getId();
			List<ResRec> resRecList=resRecBiz.searchResRecWithBLOBs(recTypeId, processFlow, operUserFlow);
			ResDoctorSchProcess doctorSchProcess= resDoctorProcessBiz.read(processFlow);
			SchArrangeResult schArrangeResult=new SchArrangeResult();
			SchRotationDept schRotationDept=new SchRotationDept();
			List<SchRotationDeptReq> rotationDeptReqList=new ArrayList<SchRotationDeptReq>();
			if (doctorSchProcess!=null) {
				if (StringUtil.isNotBlank(doctorSchProcess.getSchResultFlow())) {
					 schArrangeResult=schArrangeResultBiz.readSchArrangeResult(doctorSchProcess.getSchResultFlow());
				}
				if (StringUtil.isNotBlank(schArrangeResult.getStandardGroupFlow())&& StringUtil.isNotBlank(schArrangeResult.getStandardDeptId())) {
					schRotationDept= schRotationDeptBiz.searchGroupFlowAndStandardDeptIdQuery(schArrangeResult.getStandardGroupFlow(),schArrangeResult.getStandardDeptId());
				}
				if (StringUtil.isNotBlank(schRotationDept.getRecordFlow())) {
					 rotationDeptReqList=schRotationDeptBiz.searchDeptReqByRel(schRotationDept.getRecordFlow(),recTypeId);
				}
			}
			List<Map<String, Object>> maps=new ArrayList<Map<String,Object>>();
			Map<String, String> datasMap=new HashMap<String, String>();
			for (ResRec resRec : resRecList) {
				String s= datasMap.get(resRec.getItemId());
				int c=0;
				 if (s==null) {
					c=0;
				 }else{
					 c=Integer.valueOf(s).intValue();
				 }
				 c++;
				 s=String.valueOf(c);
				 datasMap.put(resRec.getItemId(), s);
			}
			for (SchRotationDeptReq schRotationDeptReq : rotationDeptReqList) {
				Map<String, Object> dataMap=new HashMap<String, Object>();
					dataMap.put("itemName", StringUtil.defaultIfEmpty(schRotationDeptReq.getItemName(), "") );
					dataMap.put("count", StringUtil.defaultIfEmpty(datasMap.get(schRotationDeptReq.getItemId()),"0"));
				maps.add(dataMap);
			}	
			return maps;
	}
	@RequestMapping(value="/leaveRecordSun")
	@ResponseBody
	public Map<String, Object> leaveRecordSun(String schDeptFlow,String userFlow){
		Map<String, Object> maps=new HashMap<String, Object>();
		List<SchDoctorAbsence> doctorAbsences=doctorAbsenceBiz.searchSchDoctorAbsenceByDoctorDept(schDeptFlow, userFlow);
		maps.put("schDoctorAbsenceList", doctorAbsences.size());
		for (SchDoctorAbsence schDoctorAbsence : doctorAbsences) {
			int Leave=0;
			int Sickleave=0;
			int Maternityleave=0;
			int Marriage=0;
			maps.put("leave", Leave);
			maps.put("sickleave", Sickleave);
			maps.put("maternityleave", Maternityleave);
			maps.put("marriage", Marriage);
			String intervalDay=schDoctorAbsence.getIntervalDay();
			int i=Integer.valueOf(intervalDay);
			if (AbsenceTypeEnum.Leave.getId().equals(schDoctorAbsence.getAbsenceTypeId())) {
				Leave+=i;
				maps.put("leave", Leave);
			}
			if (AbsenceTypeEnum.Sickleave.getId().equals(schDoctorAbsence.getAbsenceTypeId())) {
				Sickleave+=i;
				maps.put("sickleave", Sickleave);
			}
			if (AbsenceTypeEnum.Maternityleave.getId().equals(schDoctorAbsence.getAbsenceTypeId())) {
				Maternityleave+=i;
				maps.put("maternityleave", Maternityleave);
			}
			if (AbsenceTypeEnum.Marriage.getId().equals(schDoctorAbsence.getAbsenceTypeId())) {
				Marriage+=i;
				maps.put("marriage", Marriage);
			}
		}
		return maps;
	}

	@RequestMapping(value="/getProgressByProcess")
	@ResponseBody
	public Object getProgressByProcess(String doctorFlow,String processFlow){
		Map<String, String> processPerMap=null;
		ResDoctorSchProcess process=resDoctorProcessBiz.read(processFlow);
		if(process!=null) {
			SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(process.getSchResultFlow());
			processFlow=result.getResultFlow();
			//百分比Map
			List < SchArrangeResult > results = new ArrayList<SchArrangeResult>();
			results.add(result);
			processPerMap = resRecBiz.getFinishPer(results,doctorFlow);
		}
		return processPerMap;
	}

    @RequestMapping("/printInfo")
    public void printInfo(String recFlow,HttpServletRequest request, HttpServletResponse response) throws Exception {
        //   PubProj proj = this.projBiz.readProject(projFlow);
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        if (StringUtil.isNotBlank(recFlow)) {
            ResRec rec = resRecBiz.readResRec(recFlow);
           // PageGroup pageGroup = this.projPageBiz.getPageGroup(rec.getRecTypeId(), proj.getProjTypeId());
            if (rec != null) {
                String content = rec.getRecContent();
                if (StringUtil.isNotBlank(content)) {
                    Map<String, Object> resultMap = resRecBiz.parseContent(content);//JspFormUtil.parseXmlStr(content);
                    ServletOutputStream out = null;
                    try {
                        //DocxUtil.convert(SpringUtil.getResource("classpath:srm/product/print/lczdzzjs-sbs.xml").getFile(), "D:\\"+DateUtil.getCurrDateTime()+".docx", resultMap);
                        ServletContext context = request.getServletContext();
                        String path = "/jsp/res/form/"+rec.getRecForm()+"/"+rec.getRecTypeId()+".docx";
                        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), resultMap, null, true);
                        if (temeplete != null) {
                            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                            String name = rec.getRecTypeName() + ".docx";
                            response.setHeader("Content-disposition", "attachment; filename="
                                    + new String(name.getBytes("gbk"),
                                    "ISO8859-1") + "");

                            out = response.getOutputStream();
                            (new SaveToZipFile(temeplete)).save(out);

                            out.flush();

                        }

                    } catch (Exception e) {
                        logger.error("", e);
                        throw new RuntimeException(e);
                    } finally {
                        try {
                            if (out != null) {
                                out.close();
                            }
                        } catch (IOException e) {
                            logger.error("", e);
                        }
                    }
                }

            }

        }
    }
}