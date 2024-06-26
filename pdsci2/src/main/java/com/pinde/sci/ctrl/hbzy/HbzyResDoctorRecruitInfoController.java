package com.pinde.sci.ctrl.hbzy;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.hbzy.IJszyResDoctorArchiveBiz;
import com.pinde.sci.biz.hbzy.IJszyResDoctorBiz;
import com.pinde.sci.biz.hbzy.IJszyResDoctorRecruitBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sch.impl.SchRotationGroupBizImpl;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SchRotationDeptMapper;
import com.pinde.sci.enums.hbzy.JszyResDoctorAuditStatusEnum;
import com.pinde.sci.enums.hbzy.JszyResTrainYearEnum;
import com.pinde.sci.enums.res.AfterRecTypeEnum;
import com.pinde.sci.enums.res.ResAssessTypeEnum;
import com.pinde.sci.enums.res.ResRecTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.res.ResAssessCfgItemForm;
import com.pinde.sci.form.res.ResAssessCfgTitleForm;
import com.pinde.sci.model.hbzy.JszyResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/hbzy/doctorRecruitInfo")
public class HbzyResDoctorRecruitInfoController extends GeneralController {
	final static String JXCF = "1";
	final static String YN = "2";
	final static String WZBLTL = "3";
	final static String XSJZ = "4";
	final static String SWBLTL = "5";
	@Autowired
	private IJszyResDoctorBiz jszyResDoctorBiz;
	@Autowired
	private IJszyResDoctorRecruitBiz jszyResDoctorRecruitBiz;

	@Autowired
	private SchRotationDeptMapper rotationDeptMapper;
	@Autowired
	private SchRotationGroupBizImpl rotationGroupBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private OrgBizImpl orgBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private IResDoctorProcessBiz resDoctorProcessBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResDoctorOrgHistoryBiz resDoctorOrgHistoryBiz;
	@Autowired
	private IResAssessCfgBiz assessCfgBiz;
	@Autowired
	private ISchRotationBiz rotationBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
	@Autowired
	private ISchDoctorDeptBiz doctorDeptBiz;
	@Autowired
	private ISchRotationGroupBiz groupBiz;
	@Autowired
	private IJszyResDoctorArchiveBiz archiveBiz;
	/**
	 * 跳到父页面，用子页面处理
	 */
	@RequestMapping(value="/doctorRecruitList")
	public String doctorRecruitList(){
		return  "hbzy/hospital/doctor/doctorTrendMain";
	}

	public SchRotationDept readStandardRotationDept(String resultFlow){
		SchRotationDept rotationDept = null;
		if(StringUtil.isNotBlank(resultFlow)){
			SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(resultFlow);
			if(result!=null){
				String rotationFlow = result.getRotationFlow();
				String standardDeptId = result.getStandardDeptId();
				SchRotationGroup group = rotationGroupBiz.readSchRotationGroup(result.getStandardGroupFlow());
				if(group!=null){
					if(StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(standardDeptId)){
						SchRotationDeptExample example = new SchRotationDeptExample();
						SchRotationDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y)
								.andRotationFlowEqualTo(rotationFlow)
								.andStandardDeptIdEqualTo(standardDeptId)
								.andOrgFlowIsNull()
								.andGroupFlowEqualTo(result.getStandardGroupFlow());
						List<SchRotationDept> rotationDeptList = rotationDeptMapper.selectByExampleWithBLOBs(example);
						if(rotationDeptList!=null && rotationDeptList.size()>0){
							rotationDept = rotationDeptList.get(0);
						}
					}
				}
			}
		}
		return rotationDept;
	}
	/**
	 * 轮转科室培训详情
     */
	@RequestMapping(value="/catalogue",method={RequestMethod.GET,RequestMethod.POST})
	public String catalogue(String resultFlow,String recTypeId,Model model) throws Exception{
		if (StringUtil.isBlank(recTypeId)) {
			return "hbzy/hospital/catalogue";
		}
		if("afterFile".equals(recTypeId))
		{
			List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>();
			SchArrangeResult result=schArrangeResultBiz.readSchArrangeResult(resultFlow);
			SchRotationDept schRotationDept=readStandardRotationDept(resultFlow);
			if (result != null&&StringUtil.isNotBlank(result.getDoctorFlow())&&schRotationDept!=null&&StringUtil.isNotBlank(schRotationDept.getRecordFlow())) {
				ResRec rec =resRecBiz.queryResRec(schRotationDept.getRecordFlow(),result.getDoctorFlow(),AfterRecTypeEnum.AfterSummary.getId());
				String content = null == rec ? "" : rec.getRecContent();
				if (StringUtil.isNotBlank(content)) {
					Document doc = DocumentHelper.parseText(content);
					Element root = doc.getRootElement();
					List<Element> imageEles = root.elements();
					if (imageEles != null && imageEles.size() > 0) {
						for (Element image : imageEles) {
							Map<String, Object> recContent = new HashMap<String, Object>();
							String imageFlow = image.attributeValue("imageFlow");
							List<Element> elements = image.elements();
							for (Element attr : elements) {
								String attrName = attr.getName();
								String attrValue = attr.getText();
								recContent.put(attrName, attrValue);
							}
							imagelist.add(recContent);
						}
					}
				}
			}
			model.addAttribute("imagelist",imagelist);
		}else {
			ResDoctorSchProcess resDoctorSchProcess = resDoctorProcessBiz.searchByResultFlow(resultFlow);
			if (resDoctorSchProcess != null) {
				String processFlow = resDoctorSchProcess.getProcessFlow();
				Map<String, Map<String, Object>> resRecMap = new HashMap<String, Map<String, Object>>();
				List<ResRec> resRecList = resRecBiz.searchByProcessFlowAndRecTypeIdClob(processFlow, recTypeId);
				if (resRecList != null) {
					for (ResRec resRec : resRecList) {
						String recContent = resRec.getRecContent();
						Map<String, Object> formDataMap = resRecBiz.parseRecContent(recContent);
						resRecMap.put(resRec.getRecFlow(), formDataMap);
					}
				}
				if(recTypeId.equals(ResRecTypeEnum.AfterEvaluation.getId())){
					String operUserFlow=resDoctorSchProcess.getUserFlow();
					Map<String, Object> dataMap = new HashMap<String, Object>();
					Map<String,Object> processPerMap=resRecBiz.getRecProgressIn(operUserFlow,processFlow,null,null);
					if(processPerMap == null){
						processPerMap = new HashMap<String, Object>();
					}
					//获取不同类型并定义接受
					if(processPerMap!=null) {
						String caseRegistryId = ResRecTypeEnum.CaseRegistry.getId();
						String diseaseRegistryId = ResRecTypeEnum.DiseaseRegistry.getId();
						String skillRegistryId = ResRecTypeEnum.SkillRegistry.getId();
						String operationRegistryId = ResRecTypeEnum.OperationRegistry.getId();

						String caseRegistry = (String) processPerMap.get(processFlow + caseRegistryId);
						String caseRegistryReqNum = (String) processPerMap.get(processFlow + caseRegistryId + "ReqNum");
						String caseRegistryFinished = (String) processPerMap.get(processFlow + caseRegistryId + "Finished");

						String diseaseRegistry = (String) processPerMap.get(processFlow + diseaseRegistryId);
						String diseaseRegistryReqNum = (String) processPerMap.get(processFlow + diseaseRegistryId + "ReqNum");
						String diseaseRegistryFinished = (String) processPerMap.get(processFlow + diseaseRegistryId + "Finished");

						String skillRegistry = (String) processPerMap.get(processFlow + skillRegistryId);
						String skillRegistryReqNum = (String) processPerMap.get(processFlow + skillRegistryId + "ReqNum");
						String skillRegistryFinished = (String) processPerMap.get(processFlow + skillRegistryId + "Finished");

						String skillAndOperationRegistry = (String) processPerMap.get(processFlow + operationRegistryId);
						String skillAndOperationRegistryReqNum = (String) processPerMap.get(processFlow + operationRegistryId + "ReqNum");
						String skillAndOperationRegistryFinished = (String) processPerMap.get(processFlow + operationRegistryId + "Finished");

						String recTypeIdt = ResRecTypeEnum.CampaignRegistry.getId();
						int teachingRounds = 0;
						int difficult = 0;
						int lecture = 0;
						int death = 0;
						List<String> recTypes = new ArrayList<String>();
						recTypes.add(recTypeIdt);
						List<ResRec> recs = resRecBiz.searchRecByProcessWithBLOBs(recTypes, processFlow, operUserFlow);
						for (ResRec resRec : recs) {
							String content = resRec.getRecContent();
							Document document = DocumentHelper.parseText(content);
							Element root = document.getRootElement();
							Element ec = root.element("activity_way");
							if (ec != null) {
								String text = ec.attributeValue("id");
								if (JXCF.equals(text)) {
									teachingRounds++;
								} else if (YN.equals(text) || WZBLTL.equals(text)) {
									difficult++;
								} else if (XSJZ.equals(text)) {
									lecture++;
								} else if (SWBLTL.equals(text)) {
									death++;
								}
							}
						}
						dataMap.put("caseRegistry", StringUtil.defaultIfEmpty(caseRegistry, "0"));
						dataMap.put("caseRegistryReqNum", StringUtil.defaultIfEmpty(caseRegistryReqNum, "0"));
						dataMap.put("caseRegistryFinished", StringUtil.defaultIfEmpty(caseRegistryFinished, "0"));

						dataMap.put("diseaseRegistry", StringUtil.defaultIfEmpty(diseaseRegistry, "0"));
						dataMap.put("diseaseRegistryReqNum", StringUtil.defaultIfEmpty(diseaseRegistryReqNum, "0"));
						dataMap.put("diseaseRegistryFinished", StringUtil.defaultIfEmpty(diseaseRegistryFinished, "0"));

						dataMap.put("skillRegistry", StringUtil.defaultIfEmpty(skillRegistry, "0"));
						dataMap.put("skillRegistryReqNum", StringUtil.defaultIfEmpty(skillRegistryReqNum, "0"));
						dataMap.put("skillRegistryFinished", StringUtil.defaultIfEmpty(skillRegistryFinished, "0"));

						dataMap.put("operationRegistry", StringUtil.defaultIfEmpty(skillAndOperationRegistry, "0"));
						dataMap.put("operationRegistryReqNum", StringUtil.defaultIfEmpty(skillAndOperationRegistryReqNum, "0"));
						dataMap.put("operationRegistryFinished", StringUtil.defaultIfEmpty(skillAndOperationRegistryFinished, "0"));

						dataMap.put("teachingRounds", String.valueOf(teachingRounds));
						dataMap.put("difficult", String.valueOf(difficult));
						dataMap.put("lecture", String.valueOf(lecture));
						dataMap.put("death", String.valueOf(death));
						model.addAttribute("dataMap", dataMap);
					}
				}
				model.addAttribute("resRecList", resRecList);
				model.addAttribute("resRecMap", resRecMap);

				if (ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId) || ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)) {
					String cfgCodeId = null;
					if (ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId)) {
						cfgCodeId = ResAssessTypeEnum.TeacherAssess.getId();
					} else if (ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)) {
						cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
					}
					if (resRecList != null && resRecList.size() > 0) {
						ResRec rec = resRecList.get(0);
						if (rec != null) {
							model.addAttribute("rec", rec);
							Map<String, Object> gradeMap = resRecBiz.parseGradeXml(rec.getRecContent());
							model.addAttribute("gradeMap", gradeMap);
						}
					}
					ResAssessCfg search = new ResAssessCfg();
					search.setCfgCodeId(cfgCodeId);
					List<ResAssessCfg> assessCfgList = assessCfgBiz.searchAssessCfgList(search);
					if (assessCfgList != null && !assessCfgList.isEmpty()) {
						ResAssessCfg assessCfg = assessCfgList.get(0);
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
				}
			}
		}
		return "hbzy/hospital/catalogue";
	}
	/**
	 * 轮转科室详细信息
	 */
	@RequestMapping(value="/cycleDeptDetails")
	public String cycleDeptDetails(Model model,String resultFlow,String schStartDate,String schEndDate){
		ResDoctorSchProcess resDoctorSchProcess = resDoctorProcessBiz.searchByResultFlow(resultFlow);
		if (resDoctorSchProcess!=null) {
			String teacherName = resDoctorSchProcess.getTeacherUserName();
			String schDeptName = resDoctorSchProcess.getSchDeptName();
			String processFlow = resDoctorSchProcess.getProcessFlow();
			model.addAttribute("schDeptName", schDeptName);
			model.addAttribute("teacherName", teacherName);
			if (StringUtil.isNotBlank(resultFlow)) {
				SchArrangeResult schArrangeResult = schArrangeResultBiz.readSchArrangeResult(resultFlow);
				String doctorFlow = schArrangeResult.getDoctorFlow();
				if (StringUtil.isNotBlank(doctorFlow)) {
					Map<String, Object> processPerMap = resRecBiz.getRecProgressIn(doctorFlow, processFlow,null,null);
					model.addAttribute("processPerMap", processPerMap);
				}
			}
		}

		if(StringUtil.isNotBlank(schStartDate)){
			model.addAttribute("schStartDate",schStartDate);
		}
		if(StringUtil.isNotBlank(schEndDate)){
			model.addAttribute("schEndDate",schEndDate);
		}
		if(StringUtil.isNotBlank(resultFlow)){
			model.addAttribute("resultFlow",resultFlow);
		}
		return "hbzy/hospital/cycleDetails";
	}
	/**
	 * 医师轮转培训查询
	 */
	@RequestMapping(value = "/cycle")
	public String cycle(String userName,String idNo,String trainingSpeId,String sessionNumber,String trainingYears,
						String graduationTime,Model model,String[] docTypes,HttpServletRequest request,
						String startDate,String endDate,Integer currentPage){
		List<String> titleDate;
		if(!StringUtil.isNotBlank(startDate)){
			startDate = DateUtil.getCurrDate();
			endDate = DateUtil.newDateOfAddMonths(startDate,12);
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
		}
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			String cycStartMonth = startDate.substring(0, 7);
			String cycEndMonth = endDate.substring(0, 7);
			titleDate = getMonthsByTwoMonth(cycStartMonth, cycEndMonth);
			model.addAttribute("titleDate", titleDate);
		}
		PageHelper.startPage(currentPage, getPageSize(request));//分页,必须放在分页sql前
		SysUser currUser=GlobalContext.getCurrentUser();
		String orgFlow = currUser.getOrgFlow();
		Map<String,Object> paramMap = new HashMap<String, Object>();
		if(docTypes!=null&&docTypes.length>0){
			paramMap.put("docTypeList",docTypes);
		}
		model.addAttribute("docTypeList",docTypes);
		if(orgFlow!=null){
			paramMap.put("orgFlow",orgFlow);
		}
		if(userName!=null ){
			paramMap.put("userName",userName);
		}
		if(idNo!=null ){
			paramMap.put("idNo",idNo);
		}
		if(trainingSpeId!=null){
			paramMap.put("trainingSpeId",trainingSpeId);
		}
		if(sessionNumber!=null){
			paramMap.put("sessionNumber",sessionNumber);
		}
		if(trainingYears!=null){
			paramMap.put("trainingYears",trainingYears);
		}
		if(graduationTime!=null){
			paramMap.put("graduationTime",graduationTime);
		}
		List<Map<String,Object>> docCycleList = schArrangeResultBiz.searchDocCycleList(paramMap);

		model.addAttribute("docCycleList",docCycleList);

		if(docCycleList!=null&&docCycleList.size()>0) {
			Map<String, List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
			Map<String, ResRec> recMap = new HashMap<String, ResRec>();
			List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchCycleArrangeResults(paramMap);
			for (SchArrangeResult sar : arrResultList) {
				String doctorFlow = sar.getDoctorFlow();
				String resultStartDate = sar.getSchStartDate();
				String resultEndDate = sar.getSchEndDate();

				String standardDeptId = sar.getStandardDeptId();//轮转部门颜色
				String standarGroupFlow = sar.getStandardGroupFlow();
				if (StringUtil.isNotBlank(standardDeptId) && StringUtil.isNotBlank(standarGroupFlow)) {
					SchRotationDept schRotationDept = schRotationDeptBiz.searchGroupFlowAndStandardDeptIdQuery(standarGroupFlow, standardDeptId);
					if (schRotationDept != null) {
						String recordFlow = schRotationDept.getRecordFlow();
						ResRec resRec = resRecBiz.queryResRec(recordFlow, doctorFlow, ResRecTypeEnum.AfterSummary.getId());
						recMap.put(sar.getResultFlow(), resRec);
					}
				}
				if (StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)) {
					resultStartDate = resultStartDate.substring(0, 7);
					resultEndDate = resultEndDate.substring(0, 7);
					List<String> months = getMonthsByTwoMonth(resultStartDate, resultEndDate);
					if (months != null) {
						for (String month : months) {
							String key = doctorFlow + month;
							List<SchArrangeResult> sarList = resultListMap.get(key);
							if (sarList == null) {
								sarList = new ArrayList<SchArrangeResult>();
								resultListMap.put(key, sarList);
							}
							sarList.add(sar);
						}
					}
				}
			}

			model.addAttribute("recMap", recMap);
			model.addAttribute("resultListMap",resultListMap);
		}

		return "hbzy/hospital/cycle";
	}

	/**
	 * 获取两个月份之间的所有月
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private List<String> getMonthsByTwoMonth(String startDate,String endDate){
		List<String> months = null;
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && startDate.compareTo(endDate)<=0){
			months = new ArrayList<String>();
			months.add(startDate);
			if(!startDate.equals(endDate)){
				String currDate = startDate;
				while(!currDate.equals(endDate)){
					currDate = DateUtil.newMonthOfAddMonths(currDate,1);
					months.add(currDate);
				}
			}
		}
		return months;
	}

	/**
	 * 医师出科成绩细节
     */
	@RequestMapping(value="/resultsDetails")
	public String resultsDetails(String resultFlow,String recTypeId,Model model)throws DocumentException {
		if (StringUtil.isBlank(recTypeId)) {
			return "hbzy/hospital/cycleResultsDetails";
		}
		if(ResRecTypeEnum.AfterSummary.getId().equals(recTypeId)) {
			SchArrangeResult schArrangeResult = schArrangeResultBiz.readSchArrangeResult(resultFlow);
			String standardDeptId = schArrangeResult.getStandardDeptId();
			String standardGroupFlow = schArrangeResult.getStandardGroupFlow();
			String doctorFlow = schArrangeResult.getDoctorFlow();
			if (standardDeptId!=null&&standardGroupFlow!=null&&doctorFlow!=null) {
				SchRotationDept schRotationDept = schRotationDeptBiz.searchGroupFlowAndStandardDeptIdQuery(standardGroupFlow, standardDeptId);
				String recordFlow = schRotationDept.getRecordFlow();
				ResRec resRec = resRecBiz.queryResRec(recordFlow, doctorFlow, recTypeId);
				if(resRec!=null) {
					String content = resRec.getRecContent();
					List<Map<String, String>> imageList = resRecBiz.parseImageXml(content);
					model.addAttribute("imageList", imageList);
				}
			}
		}else {
			ResDoctorSchProcess resDoctorSchProcess = resDoctorProcessBiz.searchByResultFlow(resultFlow);
			if (resDoctorSchProcess != null) {
				String processFlow = resDoctorSchProcess.getProcessFlow();
				Map<String, Map<String, Object>> resRecMap = new HashMap<String, Map<String, Object>>();
				List<ResRec> resRecList = resRecBiz.searchByProcessFlowAndRecTypeIdClob(processFlow, recTypeId);
				if (resRecList != null && !resRecList.isEmpty()) {
					for (ResRec resRec : resRecList) {
						String content = resRec.getRecContent();
						Map<String, Object> formDataMap = resRecBiz.parseRecContent(content);
						resRecMap.put(resRec.getRecFlow(), formDataMap);
					}
				}
				if(recTypeId.equals(ResRecTypeEnum.AfterEvaluation.getId())){
					String operUserFlow=resDoctorSchProcess.getUserFlow();
					Map<String, Object> dataMap = new HashMap<String, Object>();
					Map<String,Object> processPerMap=resRecBiz.getRecProgressIn(operUserFlow,processFlow,null,null);
					if(processPerMap == null){
						processPerMap = new HashMap<String, Object>();
					}
					//获取不同类型并定义接受
					if(processPerMap!=null) {
						String caseRegistryId = ResRecTypeEnum.CaseRegistry.getId();
						String diseaseRegistryId = ResRecTypeEnum.DiseaseRegistry.getId();
						String skillRegistryId = ResRecTypeEnum.SkillRegistry.getId();
						String operationRegistryId = ResRecTypeEnum.OperationRegistry.getId();

						String caseRegistry = (String) processPerMap.get(processFlow + caseRegistryId);
						String caseRegistryReqNum = (String) processPerMap.get(processFlow + caseRegistryId + "ReqNum");
						String caseRegistryFinished = (String) processPerMap.get(processFlow + caseRegistryId + "Finished");

						String diseaseRegistry = (String) processPerMap.get(processFlow + diseaseRegistryId);
						String diseaseRegistryReqNum = (String) processPerMap.get(processFlow + diseaseRegistryId + "ReqNum");
						String diseaseRegistryFinished = (String) processPerMap.get(processFlow + diseaseRegistryId + "Finished");

						String skillRegistry = (String) processPerMap.get(processFlow + skillRegistryId);
						String skillRegistryReqNum = (String) processPerMap.get(processFlow + skillRegistryId + "ReqNum");
						String skillRegistryFinished = (String) processPerMap.get(processFlow + skillRegistryId + "Finished");

						String skillAndOperationRegistry = (String) processPerMap.get(processFlow + operationRegistryId);
						String skillAndOperationRegistryReqNum = (String) processPerMap.get(processFlow + operationRegistryId + "ReqNum");
						String skillAndOperationRegistryFinished = (String) processPerMap.get(processFlow + operationRegistryId + "Finished");

						String recTypeIdt = ResRecTypeEnum.CampaignRegistry.getId();
						int teachingRounds = 0;
						int difficult = 0;
						int lecture = 0;
						int death = 0;
						List<String> recTypes = new ArrayList<String>();
						recTypes.add(recTypeIdt);
						List<ResRec> recs = resRecBiz.searchRecByProcessWithBLOBs(recTypes, processFlow, operUserFlow);
						for (ResRec resRec : recs) {
							String content = resRec.getRecContent();
							Document document = DocumentHelper.parseText(content);
							Element root = document.getRootElement();
							Element ec = root.element("activity_way");
							if (ec != null) {
								String text = ec.attributeValue("id");
								if (JXCF.equals(text)) {
									teachingRounds++;
								} else if (YN.equals(text) || WZBLTL.equals(text)) {
									difficult++;
								} else if (XSJZ.equals(text)) {
									lecture++;
								} else if (SWBLTL.equals(text)) {
									death++;
								}
							}
						}
						dataMap.put("caseRegistry", StringUtil.defaultIfEmpty(caseRegistry, "0"));
						dataMap.put("caseRegistryReqNum", StringUtil.defaultIfEmpty(caseRegistryReqNum, "0"));
						dataMap.put("caseRegistryFinished", StringUtil.defaultIfEmpty(caseRegistryFinished, "0"));

						dataMap.put("diseaseRegistry", StringUtil.defaultIfEmpty(diseaseRegistry, "0"));
						dataMap.put("diseaseRegistryReqNum", StringUtil.defaultIfEmpty(diseaseRegistryReqNum, "0"));
						dataMap.put("diseaseRegistryFinished", StringUtil.defaultIfEmpty(diseaseRegistryFinished, "0"));

						dataMap.put("skillRegistry", StringUtil.defaultIfEmpty(skillRegistry, "0"));
						dataMap.put("skillRegistryReqNum", StringUtil.defaultIfEmpty(skillRegistryReqNum, "0"));
						dataMap.put("skillRegistryFinished", StringUtil.defaultIfEmpty(skillRegistryFinished, "0"));

						dataMap.put("operationRegistry", StringUtil.defaultIfEmpty(skillAndOperationRegistry, "0"));
						dataMap.put("operationRegistryReqNum", StringUtil.defaultIfEmpty(skillAndOperationRegistryReqNum, "0"));
						dataMap.put("operationRegistryFinished", StringUtil.defaultIfEmpty(skillAndOperationRegistryFinished, "0"));

						dataMap.put("teachingRounds", String.valueOf(teachingRounds));
						dataMap.put("difficult", String.valueOf(difficult));
						dataMap.put("lecture", String.valueOf(lecture));
						dataMap.put("death", String.valueOf(death));
						model.addAttribute("dataMap", dataMap);
					}
				}

				model.addAttribute("resRecMap", resRecMap);
				model.addAttribute("resRecList", resRecList);
			}
		}
		return "hbzy/hospital/cycleResultsDetails";
	}


	/*
	 *医师出科成绩查询
	 */
	@RequestMapping(value="/cycleResults")
	public String cycleResults(String trainingSpeId,String sessionNumber,String userName,Model model,HttpServletRequest request,Integer currentPage,
							   String startDate,String endDate){
		if(!StringUtil.isNotBlank(startDate)){
			startDate = DateUtil.getCurrDate();
			endDate = DateUtil.newDateOfAddMonths(startDate,12);
			model.addAttribute("startDate",startDate);
			model.addAttribute("endDate",endDate);
		}
		List<String> titleDate;
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			String startMonth = startDate.substring(0, 7);
			String endMonth = endDate.substring(0, 7);
			titleDate = getMonthsByTwoMonth(startMonth, endMonth);
			model.addAttribute("titleDate", titleDate);
		}
		PageHelper.startPage(currentPage, getPageSize(request));//分页,必须放在分页sql前
		SysUser currUser=GlobalContext.getCurrentUser();
		String orgFlow = currUser.getOrgFlow();
		Map<String,Object> paramMap = new HashMap<String, Object>();
		if(orgFlow!=null){
			paramMap.put("orgFlow",orgFlow);
		}
		if(userName!=null ){
			paramMap.put("userName",userName);
		}
		if(trainingSpeId!=null){
			paramMap.put("trainingSpeId",trainingSpeId);
		}
		if(sessionNumber!=null){
			paramMap.put("sessionNumber",sessionNumber);
		}
		if(startDate!=null){
			paramMap.put("startDate",startDate);
		}
		if(endDate!=null){
			paramMap.put("endDate",endDate);
		}
		List<Map<String,Object>> docResultsList = schArrangeResultBiz.searchDocResultsList(paramMap);
		model.addAttribute("docResultsList",docResultsList);
		Map<String,List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();

		List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchCycleArrangeResults(paramMap);
		for (SchArrangeResult sar : arrResultList) {
				String doctorFlow = sar.getDoctorFlow();
				String resultStartDate = sar.getSchStartDate();
				String resultEndDate = sar.getSchEndDate();
				if(StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)){
					resultStartDate = resultStartDate.substring(0,7);
					resultEndDate = resultEndDate.substring(0,7);
					List<String> months = getMonthsByTwoMonth(resultStartDate,resultEndDate);
					if(months!=null){
						for(String month : months){
							String key = doctorFlow+month;
							List<SchArrangeResult> sarList = resultListMap.get(key);
							if(sarList==null){
								sarList = new ArrayList<SchArrangeResult>();
								resultListMap.put(key,sarList);
							}
							sarList.add(sar);
						}
					}
				}
			}
		model.addAttribute("resultListMap",resultListMap);

				return "hbzy/hospital/cycleResults";
	}
	/**
	 * 查询培训信息，上传培训记录，添加学习计划
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/trainInfo")
	public String trainInfo(Model model,SysUser user,ResDoctorRecruit doctorRecruit){
		return  "hbzy/hospital/doctor/trainList";
	}
	@RequestMapping(value="/refreshCount")
	public String refreshCount(Model model){
		int passCount=0;
		int noStudyCount=0;
		int uploadCount=0;
		SysUser currUser=GlobalContext.getCurrentUser();
		ResDoctorRecruit recruit=new ResDoctorRecruit();
		recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
		if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)){
			recruit.setOrgFlow(currUser.getOrgFlow());
		}
		//查询本基地共有多少的培训记录
		passCount=jszyResDoctorRecruitBiz.searchDoctorNum(recruit);
		//查询本基地没有学习计划的人数
		ResDoctorRecruitWithBLOBs recruitWb=new ResDoctorRecruitWithBLOBs();
		if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)){
			recruitWb.setOrgFlow(currUser.getOrgFlow());
		}
		recruitWb.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
		noStudyCount=jszyResDoctorRecruitBiz.searchCountByCondition(recruitWb, GlobalConstant.FLAG_Y);
		//查询本基地的需要上传减免证明的人数
		//recruitWb.setCatSpeId(JszyTrainCategoryEnum.DoctorTrainingSpe.getId());
		recruitWb.setTrainYear(JszyResTrainYearEnum.ThreeYear.getId());
		uploadCount=jszyResDoctorRecruitBiz.searchCountByCondition(recruitWb, GlobalConstant.FLAG_N);
		//查询所有符合条件的人
		model.addAttribute("passCount", passCount);
		model.addAttribute("noStudyCount", noStudyCount);
		model.addAttribute("uploadCount", uploadCount);
		return  "hbzy/hospital/doctor/count";
	}
	/**
	 * 查询培训记录
	 * @param model
	 * @param user
	 * @param doctorRecruit
	 * @return
	 */
	@RequestMapping(value="/trainDocInfo")
	public String trainDocInfo(Model model,SysUser user,ResDoctorRecruit doctorRecruit,String derateFlag, HttpServletRequest request,Integer currentPage,String jointOrgFlag){
		SysUser currUser=GlobalContext.getCurrentUser();
		List<String>jointOrgFlowList =new ArrayList<String>();
		doctorRecruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
		if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)){
			if(StringUtil.isBlank(jointOrgFlag) || !GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				doctorRecruit.setOrgFlow(currUser.getOrgFlow());
			}
		}
		if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
			List<ResJointOrg> jointOrgs=jointOrgBiz.searchResJointByOrgFlow(currUser.getOrgFlow());
			if(jointOrgs!=null&&!jointOrgs.isEmpty()){
				for(ResJointOrg o:jointOrgs){
					jointOrgFlowList.add(o.getJointOrgFlow());
				}
				jointOrgFlowList.add(currUser.getOrgFlow());
			}
		}
		if(StringUtil.isNotBlank(derateFlag)){
			if(GlobalConstant.FLAG_Y.equals(derateFlag)){
				//doctorRecruit.setCatSpeId(JszyTrainCategoryEnum.DoctorTrainingSpe.getId());
				doctorRecruit.setTrainYear(JszyResTrainYearEnum.ThreeYear.getId());
			}else{
				derateFlag="";
			}
		}else{
			derateFlag="";
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<JszyResDoctorRecruitExt>recruitList=jszyResDoctorRecruitBiz.searchTrainInfoList(jointOrgFlowList,doctorRecruit, user, derateFlag);
		model.addAttribute("recruitList", recruitList);
		return  "hbzy/hospital/doctor/trainInfoZi";
	}
	/**
	 * 保存基地上传的url和学习计划
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveTrainInfo")
	@ResponseBody
	public String saveTrainInfo(Model model,ResDoctorRecruitWithBLOBs dBloBs){
		if(StringUtil.isNotBlank(dBloBs.getRecruitFlow())){
			ResDoctorRecruitWithBLOBs exitBloBs=new ResDoctorRecruitWithBLOBs();
			exitBloBs.setRecruitFlow(dBloBs.getRecruitFlow());
			ResDoctorRecruitWithBLOBs doctorRecruitWb=jszyResDoctorRecruitBiz.readRecruit(dBloBs.getRecruitFlow());
			doctorRecruitWb.setProveFileUrl(dBloBs.getProveFileUrl());
			doctorRecruitWb.setProvRemark(dBloBs.getProvRemark());
			int result=jszyResDoctorRecruitBiz.saveDoctorRecruit(doctorRecruitWb);
			if(GlobalConstant.ZERO_LINE==result){
				return GlobalConstant.SAVE_FAIL;
			}else{
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return  "hbzy/hospital/doctor/trainInfoZi";
	}
	/**
	 * 学员补填二级专业
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveSecondSpe")
	@ResponseBody
	public String saveSecondSpe(Model model,ResDoctorRecruitWithBLOBs dBloBs){
		if(StringUtil.isNotBlank(dBloBs.getRecruitFlow())){

			int result=jszyResDoctorRecruitBiz.saveSecondSpe(dBloBs);
			if(GlobalConstant.ZERO_LINE==result){
				return GlobalConstant.SAVE_FAIL;
			}else{
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return  GlobalConstant.SAVE_FAIL;
	}
	/**
	 * 判断同一个级别是否存在相同的记录
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/validate")
	@ResponseBody
	public String validate(Model model,String doctorFlow,String sessionNumber){
		if(StringUtil.isNotBlank(doctorFlow)&&StringUtil.isNotBlank(sessionNumber)){
			ResDoctorRecruit recruit=new ResDoctorRecruit();
			recruit.setSessionNumber(sessionNumber);
			recruit.setDoctorFlow(doctorFlow);
			recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
			List<ResDoctorRecruit>  recruits=jszyResDoctorRecruitBiz.readDoctorRecruits(recruit);
			if(recruit!=null&&!recruits.isEmpty()){
				return GlobalConstant.FLAG_N;
			}else{
				return GlobalConstant.FLAG_Y;
			}
		}
		return  GlobalConstant.FLAG_N;
	}
	/**
	 * 当前机构下所有医师	
	 */
	@RequestMapping(value="/doctorTrendList")
	public String doctorRecruit(Model model,Integer currentPage, HttpServletRequest request,ResDoctorRecruit resDoctorRecruit,SysUser user){
		SysUser sysuser=GlobalContext.getCurrentUser();
		if (sysuser!=null) {
			resDoctorRecruit.setOrgFlow(sysuser.getOrgFlow());
			resDoctorRecruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
			PageHelper.startPage(currentPage, getPageSize(request));
			List<JszyResDoctorRecruitExt> RecruitList=jszyResDoctorRecruitBiz.resDoctorRecruitExtList(resDoctorRecruit,user,null);
			model.addAttribute("recruitList",RecruitList);
		}
		return  "hbzy/hospital/doctor/doctorTrendList";
	}
	
	@RequestMapping(value="/delDoctorRecruit")
	@ResponseBody
	public String delDoctorRecruit(String recruitFlow){
		ResDoctorRecruitWithBLOBs recruit=(ResDoctorRecruitWithBLOBs) this.jszyResDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
		if(recruit!=null){
			recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			int result=this.jszyResDoctorRecruitBiz.saveDoctorRecruit(recruit);
			if(result==GlobalConstant.ONE_LINE){
				return GlobalConstant.DELETE_SUCCESSED;
			}
		}
		return GlobalConstant.DELETE_FAIL;
	}
	public List<String> searchJointOrgList(String flag,String Flow) {
		List<String> jointOrgFlowList=new ArrayList<String>();
		if(GlobalConstant.FLAG_Y.equals(flag)){
			List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(Flow);
			if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:resJointOrgList){
					jointOrgFlowList.add(jointOrg.getJointOrgFlow());
				}
			}
		}
		return jointOrgFlowList;
	}
	//********************************************医师信息查询 开始************************************//
	@RequestMapping(value="/provinceDoctorList")
	public String provinceDoctorList(Model model, String roleFlag, String isArchive, String []datas,String zlFlag){
		SysUser sysuser= GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		SysOrg sysorg =new  SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
			sysorg.setOrgCityId(org.getOrgCityId());
		}
		sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
		sysorg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
		List<SysOrg>  countryList=orgBiz.searchOrg(sysorg);
		sysorg.setOrgLevelId(OrgLevelEnum.ProvinceOrg.getId());
		List<SysOrg>  provinceList=orgBiz.searchOrg(sysorg);

		List<ResJointOrg> jointOrgs=jointOrgBiz.searchJointOrgAll();
		List<String> orgFlowList=new ArrayList<String>();
		if(jointOrgs!=null&&!jointOrgs.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgs){
				orgFlowList.add(jointOrg.getOrgFlow());
			}
		}
		model.addAttribute("orgFlowList", orgFlowList);
		model.addAttribute("countryList", countryList);
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		List<ResJointOrg> joinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(sysuser.getOrgFlow());
		//是协同基地
		if(!joinOrgs.isEmpty()&&joinOrgs.size()>0){
			model.addAttribute("isJointOrg","1");
		}
		if(GlobalConstant.FLAG_Y.equals(isArchive)){
			List<ResArchiveSequence> archiveSequenceList = archiveBiz.allResArchiveSequence();
			model.addAttribute("archiveSequenceList",archiveSequenceList);
			return  "hbzy/archiveDoctorList";
		}
		model.addAttribute("datas",datas);
		model.addAttribute("zlFlag",zlFlag);
		return  "hbzy/doctorList";
	}

	@RequestMapping(value="/doctorTrendListSun")
	public String doctorRecruitSun(Model model, Integer currentPage, String roleFlag, HttpServletRequest request, ResDoctor doctor,
								   SysUser user, String[] trainYears, String jointOrgFlag, String derateFlag, String trainingTypeId,
								   String orgLevel, String[] datas, String graduationYear,String zlFlag){
		ResDoctorRecruit resDoctorRecruit= new ResDoctorRecruit();
		if(StringUtil.isNotBlank(graduationYear)){
			resDoctorRecruit.setGraduationYear(graduationYear);
		}
		List<String> jointOrgFlowList=new ArrayList<String>();
		List<String>docTypeList=new ArrayList<String>();
		SysOrg org=new SysOrg();
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysUser sysUser =new SysUser();
		if (StringUtil.isBlank(doctor.getOrgFlow())) {
			if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
				jointOrgFlowList=searchJointOrgList(jointOrgFlag,sysuser.getOrgFlow());
				jointOrgFlowList.add(sysuser.getOrgFlow());
			}
			if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)
					||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.RES_ROLE_SCOPE_SCHOOL)) {
				SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
				if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
					jointOrgFlowList.add(sysuser.getOrgFlow());
					SysOrg searchOrg=new SysOrg();
					searchOrg.setOrgProvId(sysOrg.getOrgProvId());
					if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
						searchOrg.setOrgCityId(sysOrg.getOrgCityId());
					}
					if(StringUtil.isNotBlank(orgLevel)){
						searchOrg.setOrgLevelId(orgLevel);
						searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
					List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
					for(SysOrg g:exitOrgs){
						List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
						if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
							for(ResJointOrg jointOrg:resJointOrgList){
								if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
									String cityId = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
									if(StringUtil.isNotBlank(cityId)&&cityId.equals(sysOrg.getOrgCityId())){
										jointOrgFlowList.add(jointOrg.getJointOrgFlow());
									}
								}else{
									jointOrgFlowList.add(jointOrg.getJointOrgFlow());
								}
							}
						}
						jointOrgFlowList.add(g.getOrgFlow());
					}
				}else{
					org.setOrgProvId(sysOrg.getOrgProvId());
					if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
						org.setOrgCityId(sysOrg.getOrgCityId());
					}
					if(StringUtil.isNotBlank(orgLevel)){
						org.setOrgLevelId(orgLevel);
						org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
				}
			}
		}else{
			jointOrgFlowList.add(doctor.getOrgFlow());
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
				if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgList){
						if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
							String cityId = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
							SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
							if(StringUtil.isNotBlank(cityId)&&cityId.equals(sysOrg.getOrgCityId())){
								String cityId2 = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
								if(StringUtil.isNotBlank(cityId2)&&cityId2.equals(sysOrg.getOrgCityId())){
									jointOrgFlowList.add(jointOrg.getJointOrgFlow());
								}
							}
						}else{
							jointOrgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			}
		}
		if(StringUtil.isNotBlank(user.getIdNo())){
			sysUser.setIdNo(user.getIdNo());
		}
		sysUser.setUserName(user.getUserName());
		if(StringUtil.isNotBlank(derateFlag)){
			if(GlobalConstant.FLAG_Y.equals(derateFlag)){
				//doctor.setTrainingTypeId(JszyTrainCategoryEnum.DoctorTrainingSpe.getId());
				resDoctorRecruit.setTrainYear(JszyResTrainYearEnum.ThreeYear.getId());
			}else{
				derateFlag="";
			}
		}else{
			derateFlag="";
		}
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		resDoctorRecruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
		List<JszyResDoctorRecruitExt> doctorList=null;
		if (GlobalConstant.RES_ROLE_SCOPE_SCHOOL.equals(roleFlag)) {
			ServletContext servletContext=request.getServletContext();
			if (servletContext!=null) {
				List<SysDict> sysDictList=(List<SysDict>) servletContext.getAttribute("dictTypeEnum"+"SendSchool"+"List");
				for (SysDict sysDict : sysDictList) {
					String dictId="send_school_"+sysDict.getDictId()+"_org_rel";
					String orgFlow= InitConfig.getSysCfg(dictId);
					doctor.setWorkOrgId(sysDict.getDictId());
					if (sysuser.getOrgFlow().equals(orgFlow)) {
						Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
						doctorRecruitMap.put("doctor", doctor);
						doctorRecruitMap.put("user", sysUser);
						doctorRecruitMap.put("derateFlag", derateFlag);
						doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
						doctorRecruitMap.put("sysOrg", org);
						doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
						doctorRecruitMap.put("docTypeList", docTypeList);
						List<String> trainTypeIdList = new ArrayList<String>();
						if (StringUtil.isNotBlank(trainingTypeId)) {
							trainTypeIdList.add(trainingTypeId);
						}
						doctorRecruitMap.put("trainTypeIdList",trainTypeIdList);
                        doctorRecruitMap.put("zlFlag",zlFlag);
                        doctorRecruitMap.put("trainYear",trainYears);
						doctorList=jszyResDoctorRecruitBiz.searchDoctorInfoExts(doctorRecruitMap);
						break;
					}else{
						continue;
					}
				}
			}
		}else{
			Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
			doctorRecruitMap.put("doctor", doctor);
			doctorRecruitMap.put("user", sysUser);
			doctorRecruitMap.put("derateFlag", derateFlag);
			doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
			doctorRecruitMap.put("sysOrg", org);
			doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
			doctorRecruitMap.put("docTypeList", docTypeList);
			List<String> trainTypeIdList = new ArrayList<String>();
			if (StringUtil.isNotBlank(trainingTypeId)) {
				trainTypeIdList.add(trainingTypeId);
			}
			doctorRecruitMap.put("trainTypeIdList",trainTypeIdList);
            doctorRecruitMap.put("zlFlag",zlFlag);
            doctorRecruitMap.put("trainYear",trainYears);
			doctorList = jszyResDoctorRecruitBiz.searchDoctorInfoExts(doctorRecruitMap);
		}
		model.addAttribute("roleFlag",roleFlag);
		model.addAttribute("doctorList",doctorList);
		model.addAttribute("datas",datas);
		model.addAttribute("zlFlag",zlFlag);
		return  "hbzy/doctorListZi";
	}
}
