package com.pinde.sci.ctrl.res;

import com.pinde.core.jspform.ItemGroupData;
import com.pinde.core.util.*;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.biz.test.ITestResultBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.NumTrans;
import com.pinde.sci.enums.res.*;
import com.pinde.sci.model.mo.*;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/schDept/export")
public class SchDeptInfoExportController extends GeneralController{
	final static String LING = "0";
	final static String JXCF = "1";
	final static String YN = "2";
	final static String WZBLTL = "3";
	final static String XSJZ = "4";
	final static String SWBLTL = "5";
	private static Logger logger = LoggerFactory.getLogger(SchDeptInfoExportController.class);
	@Autowired
	private ISchRotationBiz schRotationtBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private IResDoctorProcessBiz resDoctorProcessBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private ISchDoctorAbsenceBiz doctorAbsenceBiz;
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
	private IResEduCourseBiz eduCourseBiz;
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
	/**
	 * 轮转记录总览
	 * @param response
	 * @throws IOException
     */
	@RequestMapping(value="/exportInfo")
	public void exportInfo(HttpServletResponse response,String doctorFlow) throws Exception {
		ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
		List<SchArrangeResult> arrResultList=new ArrayList<>();
		Map<String,ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
		Map<String,String> finishPerMap=new HashMap<>();
		if(doctor!=null && GlobalConstant.FLAG_Y.equals(doctor.getSchFlag())){
			arrResultList = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctorFlow);
			if(arrResultList!=null&&!arrResultList.isEmpty()){
 				finishPerMap = resRecBiz.getFinishPer(arrResultList);
			}
			List<ResDoctorSchProcess> processList = resDoctorProcessBiz.searchProcessByDoctor(doctorFlow);
			if(processList!=null && processList.size()>0){
				Map<String,String> recContentMap = new HashMap<String, String>();
				for(ResDoctorSchProcess process : processList){
					processMap.put(process.getSchResultFlow(),process);
					recContentMap.put(process.getSchResultFlow(),process.getSchFlag());
				}
			}

		}
		resDoctorProcessBiz.exportInfo(response,arrResultList,processMap,finishPerMap);
	}
	/**
	 * 轮转科室详情
	 * @param response
	 * @throws IOException
     */
	@RequestMapping(value="/exportDeptInfo")
	public void exportDeptInfo(HttpServletRequest request,HttpServletResponse response,String userFlow,String schDeptFlow,String processFlow) throws Exception {

		List<WordprocessingMLPackage> addTemplates = new ArrayList<WordprocessingMLPackage>();
		ResDoctorSchProcess process=resDoctorProcessBiz.read(processFlow);

		ServletContext context = request.getServletContext();
		String name="";
		if(process!=null)
		{
			name=process.getSchDeptName();
			//大病历
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("name",name);
			List<ResRec> resRecList = resRecBiz.searchResRecWithBLOBs(RegistryTypeEnum.CaseRegistry.getId(), processFlow, userFlow);
			if (resRecList != null && !resRecList.isEmpty()) {
				List<ItemGroupData> casePlanTargetList = new ArrayList<ItemGroupData>();
				for (ResRec rec : resRecList) {
					Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
					if(formDataMap==null)
						formDataMap=new HashMap<>();
					Map<String, Object> objMap = new HashMap<String, Object>();
					objMap.put("CaseRegistry_mr_pName", formDataMap.get("mr_pName"));
					objMap.put("CaseRegistry_mr_no", formDataMap.get("mr_no"));
					objMap.put("CaseRegistry_disease_pName", formDataMap.get("disease_pName"));
					objMap.put("CaseRegistry_mr_diagType", formDataMap.get("mr_diagType"));
					objMap.put("CaseRegistry_bedNo", formDataMap.get("bedNo"));
					objMap.put("CaseRegistry_remarks", formDataMap.get("remarks"));
					ItemGroupData igd = new ItemGroupData();
					igd.setObjMap(objMap);
					casePlanTargetList.add(igd);
				}
				dataMap.put("casePlanTargetList", casePlanTargetList);
			}
			WordprocessingMLPackage temeplete1 = new WordprocessingMLPackage();
			String path1 = "/jsp/res/downForm/daochuTemeplete1.docx";//模板
			temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path1)), dataMap, "", true);

			addTemplates.add(temeplete1);

			//病种
			int seq1=0;
			Map<String, Object> dataMap9 = new HashMap<String, Object>();
			List<ResRec> diseaseRecList = resRecBiz.searchResRecWithBLOBs(RegistryTypeEnum.DiseaseRegistry.getId(), processFlow, userFlow);
			if (diseaseRecList != null && !diseaseRecList.isEmpty()) {
				List<ItemGroupData> skillsPlanTargetList = new ArrayList<ItemGroupData>();
				seq1 = 1;
				for (ResRec rec : diseaseRecList) {
					Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
					if(formDataMap==null)
						formDataMap=new HashMap<>();
					Map<String, Object> objMap = new HashMap<String, Object>();
					objMap.put("disease_seq", String.valueOf(seq1++));
					objMap.put("disease_pDate", formDataMap.get("disease_pDate"));
					objMap.put("disease_pName", formDataMap.get("disease_pName"));
					objMap.put("disease_mrNo", formDataMap.get("disease_mrNo"));
					objMap.put("disease_diagName", formDataMap.get("disease_diagName"));
					objMap.put("disease_diagType", formDataMap.get("disease_diagType"));
					objMap.put("disease_isCharge", formDataMap.get("disease_isCharge"));
					objMap.put("disease_isRescue", formDataMap.get("disease_isRescue"));
					objMap.put("disease_treatStep", formDataMap.get("disease_treatStep"));

					ItemGroupData igd = new ItemGroupData();
					igd.setObjMap(objMap);
					skillsPlanTargetList.add(igd);
				}
				dataMap9.put("skillsPlanTargetList", skillsPlanTargetList);
			}
			//病种申述
			List<ResAppeal> appeals6= resRecBiz.searchAppealForAudit(processFlow,RegistryTypeEnum.DiseaseRegistry.getId());
			if(appeals6!=null&&!appeals6.isEmpty())
			{
				seq1=1;
				List<ItemGroupData> skillsPlanTargetList = new ArrayList<ItemGroupData>();
				for(ResAppeal appeal:appeals6)
				{
					Map<String, Object> objMap = new HashMap<String, Object>();
					String num="";
					if(appeal.getAppealNum()!=null)
						num=appeal.getAppealNum().toString();
					objMap.put("seq", String.valueOf(seq1++));
					objMap.put("typeName", appeal.getItemName());
					objMap.put("appealNum",num);
					objMap.put("appealResaon", appeal.getAppealReason());
					ItemGroupData igd = new ItemGroupData();
					igd.setObjMap(objMap);
					skillsPlanTargetList.add(igd);
				}
				dataMap9.put("appeals", skillsPlanTargetList);
			}
			WordprocessingMLPackage temeplete9 = new WordprocessingMLPackage();
			String path9 = "/jsp/res/downForm/daochuTemeplete6.docx";//模板
			temeplete9 = Docx4jUtil.convert(new File(context.getRealPath(path9)), dataMap9, "", true);
			addTemplates.add(temeplete9);

			//操作技能
			int seq=0;
			Map<String, Object> dataMap2 = new HashMap<String, Object>();
			List<ResRec> skillRecList = resRecBiz.searchResRecWithBLOBs(RegistryTypeEnum.SkillRegistry.getId(), processFlow, userFlow);
			if (skillRecList != null && !skillRecList.isEmpty()) {
				List<ItemGroupData> skillsPlanTargetList = new ArrayList<ItemGroupData>();
				seq = 1;
				for (ResRec rec : skillRecList) {
					Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
					if(formDataMap==null)
						formDataMap=new HashMap<>();
					Map<String, Object> objMap = new HashMap<String, Object>();
					objMap.put("skill_seq", String.valueOf(seq++));
					objMap.put("skill_operDate", formDataMap.get("skill_operDate"));
					objMap.put("skill_pName", formDataMap.get("skill_pName"));
					objMap.put("skill_mrNo", formDataMap.get("skill_mrNo"));
					objMap.put("skill_operName", formDataMap.get("skill_operName"));
					objMap.put("skill_result", formDataMap.get("skill_result"));
					objMap.put("skill_fail_diagnosticResults", formDataMap.get("fail_diagnosticResults"));

					ItemGroupData igd = new ItemGroupData();
					igd.setObjMap(objMap);
					skillsPlanTargetList.add(igd);
				}
				dataMap2.put("skillsPlanTargetList", skillsPlanTargetList);
			}
			//操作技能申述
			List<ResAppeal> appeals= resRecBiz.searchAppealForAudit(processFlow,RegistryTypeEnum.SkillRegistry.getId());
			if(appeals!=null&&!appeals.isEmpty())
			{
				seq=1;
				List<ItemGroupData> skillsPlanTargetList = new ArrayList<ItemGroupData>();
				for(ResAppeal appeal:appeals)
				{
					Map<String, Object> objMap = new HashMap<String, Object>();
					String num="";
					if(appeal.getAppealNum()!=null)
						num=appeal.getAppealNum().toString();
					objMap.put("seq", String.valueOf(seq++));
					objMap.put("typeName", appeal.getItemName());
					objMap.put("appealNum",num);
					objMap.put("appealResaon", appeal.getAppealReason());
					ItemGroupData igd = new ItemGroupData();
					igd.setObjMap(objMap);
					skillsPlanTargetList.add(igd);
				}
				dataMap2.put("appeals", skillsPlanTargetList);
			}
			WordprocessingMLPackage temeplete2 = new WordprocessingMLPackage();
			String path2 = "/jsp/res/downForm/daochuTemeplete2.docx";//模板
			temeplete2 = Docx4jUtil.convert(new File(context.getRealPath(path2)), dataMap2, "", true);
			addTemplates.add(temeplete2);

			//危重记录

			Map<String, Object> dataMap3 = new HashMap<String, Object>();
			List<ResRec> graveRecList = resRecBiz.searchResRecWithBLOBs(RegistryTypeEnum.Grave.getId(), processFlow, userFlow);
			if (graveRecList != null && !graveRecList.isEmpty()) {
				List<ItemGroupData> skillsPlanTargetList = new ArrayList<ItemGroupData>();
				seq = 1;
				for (ResRec rec : graveRecList) {
					Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
					if(formDataMap==null)
						formDataMap=new HashMap<>();
					Map<String, Object> objMap = new HashMap<String, Object>();
					objMap.put("seq", String.valueOf(seq++));
					objMap.put("name", formDataMap.get("name"));
					objMap.put("caseNo", formDataMap.get("caseNo"));
					objMap.put("time", formDataMap.get("time"));
					objMap.put("explain", formDataMap.get("explain"));
					ItemGroupData igd = new ItemGroupData();
					igd.setObjMap(objMap);
					skillsPlanTargetList.add(igd);
				}
				dataMap3.put("skillsPlanTargetList", skillsPlanTargetList);
			}
			WordprocessingMLPackage temeplete3 = new WordprocessingMLPackage();
			String path3 = "/jsp/res/downForm/daochuTemeplete3.docx";//模板
			temeplete3 = Docx4jUtil.convert(new File(context.getRealPath(path3)), dataMap3, "", true);
			addTemplates.add(temeplete3);
			//参加活动
			Map<String, Object> dataMap4 = new HashMap<String, Object>();
			List<ResRec> campaignRecList = resRecBiz.searchResRecWithBLOBs(RegistryTypeEnum.CampaignRegistry.getId(), processFlow, userFlow);
			if (campaignRecList != null && !campaignRecList.isEmpty()) {
				List<ItemGroupData> skillsPlanTargetList = new ArrayList<ItemGroupData>();
				seq = 1;
				for (ResRec rec : campaignRecList) {
					Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
					if(formDataMap==null)
						formDataMap=new HashMap<>();
					Map<String, Object> objMap = new HashMap<String, Object>();
					objMap.put("seq", String.valueOf(seq++));
					objMap.put("activity_way", formDataMap.get("activity_way"));
					objMap.put("activity_date", formDataMap.get("activity_date"));
					objMap.put("activity_period", formDataMap.get("activity_period"));
					objMap.put("activity_speaker", formDataMap.get("activity_speaker"));
					objMap.put("activity_content", formDataMap.get("activity_content"));
					ItemGroupData igd = new ItemGroupData();
					igd.setObjMap(objMap);
					skillsPlanTargetList.add(igd);
				}
				dataMap4.put("skillsPlanTargetList", skillsPlanTargetList);
			}
			//参加活动申述
			List<ResAppeal> appeals2= resRecBiz.searchAppealForAudit(processFlow,RegistryTypeEnum.CampaignRegistry.getId());
			if(appeals2!=null&&!appeals2.isEmpty()) {
				seq = 1;
				List<ItemGroupData> skillsPlanTargetList = new ArrayList<ItemGroupData>();
				for (ResAppeal appeal : appeals2) {
					Map<String, Object> objMap = new HashMap<String, Object>();
					String num="";
					if(appeal.getAppealNum()!=null)
						num=appeal.getAppealNum().toString();
					objMap.put("seq", String.valueOf(seq++));
					objMap.put("typeName", appeal.getItemName());
					objMap.put("appealNum",num);
					objMap.put("appealResaon", appeal.getAppealReason());
					ItemGroupData igd = new ItemGroupData();
					igd.setObjMap(objMap);
					skillsPlanTargetList.add(igd);
				}
				dataMap4.put("appeals", skillsPlanTargetList);
			}
			WordprocessingMLPackage temeplete4 = new WordprocessingMLPackage();
			String path4 = "/jsp/res/downForm/daochuTemeplete4.docx";//模板
			temeplete4 = Docx4jUtil.convert(new File(context.getRealPath(path4)), dataMap4, "", true);
			addTemplates.add(temeplete4);
			//门诊病历
			Map<String, Object> dataMap5 = new HashMap<String, Object>();
			List<ResRec> recordRecList = resRecBiz.searchResRecWithBLOBs(RegistryTypeEnum.CaseRecord.getId(), processFlow, userFlow);
			if (recordRecList != null && !recordRecList.isEmpty()) {
				List<ItemGroupData> skillsPlanTargetList = new ArrayList<ItemGroupData>();
				seq = 1;
				for (ResRec rec : recordRecList) {
					Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
					if(formDataMap==null)
						formDataMap=new HashMap<>();
					Map<String, Object> objMap = new HashMap<String, Object>();
					objMap.put("seq", String.valueOf(seq++));
					objMap.put("case", formDataMap.get("case"));
					objMap.put("diseaseName", formDataMap.get("diseaseName"));
					objMap.put("caseNumber", formDataMap.get("caseNumber"));
					objMap.put("date", formDataMap.get("date"));
					objMap.put("teacherSignature", formDataMap.get("teacherSignature"));
					objMap.put("summary", formDataMap.get("summary"));
					ItemGroupData igd = new ItemGroupData();
					igd.setObjMap(objMap);
					skillsPlanTargetList.add(igd);
				}
				dataMap5.put("skillsPlanTargetList", skillsPlanTargetList);
			}
			WordprocessingMLPackage temeplete5 = new WordprocessingMLPackage();
			String path5 = "/jsp/res/downForm/daochuTemeplete5.docx";//模板
			temeplete5 = Docx4jUtil.convert(new File(context.getRealPath(path5)), dataMap5, "", true);
			addTemplates.add(temeplete5);
			//出科考核表
			Map<String, Object> dataMap6 = new HashMap<String, Object>();
			List<ResSchProcessExpress> resRecs = expressBiz.searchResRecExpressWithBLOBs(AfterRecTypeEnum.AfterEvaluation.getId(), processFlow);
			if (resRecs != null && !resRecs.isEmpty()) {
				ResSchProcessExpress resRec=resRecs.get(0);
				Map<String, Object> formDataMap = resRecBiz.parseRecContent(resRec.getRecContent());
				if(formDataMap==null)
					formDataMap=new HashMap<>();

				//考勤情况
				List<SchDoctorAbsence> doctorAbsences=doctorAbsenceBiz.searchSchDoctorAbsenceByDoctorDept(schDeptFlow, userFlow);
				String kaoqin="";
				if(doctorAbsences!=null&&!doctorAbsences.isEmpty())
				{
					kaoqin+="非全勤  事假："+formDataMap.get("leave")+"  病假："+formDataMap.get("sickLeave")+
							"  产假："+formDataMap.get("materLeave")+"  婚假："+formDataMap.get("absenteeism");
				}else{
					kaoqin="全勤";
				}
				dataMap6.put("kaoqin",kaoqin);

				//获取个人基础信息
				ResDoctor doctor=resDoctorBiz.searchByUserFlow(userFlow);
				SysUser user=userBiz.readSysUser(userFlow);
				dataMap6.put("userName",user.getUserName());
				dataMap6.put("sessionNumber",doctor.getSessionNumber());
				dataMap6.put("trainingSpeName",doctor.getTrainingSpeName());

				//轮转科室名称
				SchArrangeResult schArrangeResult=schArrangeResultBiz.readSchArrangeResult(process.getSchResultFlow());
				if (schArrangeResult!=null) {
					dataMap6.put("startDate", StringUtil.defaultIfEmpty(schArrangeResult.getSchStartDate(), " "));
					dataMap6.put("endDate", StringUtil.defaultIfEmpty(schArrangeResult.getSchEndDate(), " "));
					dataMap6.put("standardDeptName",schArrangeResult.getDeptName());
				}
				//打分情况
				dataMap6.put("zsgjflfg", StringUtil.defaultIfEmpty((String)formDataMap.get("zsgjflfg"), " "));
				dataMap6.put("lxgwzz", StringUtil.defaultIfEmpty((String)formDataMap.get("lxgwzz"), " "));
				dataMap6.put("ybrwzx", StringUtil.defaultIfEmpty((String)formDataMap.get("ybrwzx"), " "));
				dataMap6.put("rjgtbdnl", StringUtil.defaultIfEmpty((String)formDataMap.get("rjgtbdnl"), " "));
				dataMap6.put("tjxzjsxm", StringUtil.defaultIfEmpty((String)formDataMap.get("tjxzjsxm"), " "));
				dataMap6.put("jbllzwcd", StringUtil.defaultIfEmpty((String)formDataMap.get("jbllzwcd"), " "));
				dataMap6.put("njbjnzwcd", StringUtil.defaultIfEmpty((String)formDataMap.get("njbjnzwcd"), " "));
				dataMap6.put("lcswnl", StringUtil.defaultIfEmpty((String)formDataMap.get("lcswnl"), " "));
				dataMap6.put("lczlnl", StringUtil.defaultIfEmpty((String)formDataMap.get("lczlnl"), " "));
				dataMap6.put("jjclnl", StringUtil.defaultIfEmpty((String)formDataMap.get("jjclnl"), " "));

				//完成情况
				dataMap6.put("blsywc", StringUtil.defaultIfEmpty((String)formDataMap.get("blsywc"), "0"));
				dataMap6.put("blsyjwc", StringUtil.defaultIfEmpty((String)formDataMap.get("blsyjwc"), "0"));
				dataMap6.put("blswcbl", StringUtil.defaultIfEmpty((String)formDataMap.get("blswcbl"), "100"));

				dataMap6.put("mzsywc", StringUtil.defaultIfEmpty((String)formDataMap.get("mzsywc"), "0"));
				dataMap6.put("mzsyjwc", StringUtil.defaultIfEmpty((String)formDataMap.get("mzsyjwc"), "0"));
				dataMap6.put("mzswcbl", StringUtil.defaultIfEmpty((String)formDataMap.get("mzswcbl"), "100"));

				dataMap6.put("czsywc", StringUtil.defaultIfEmpty((String)formDataMap.get("czsywc"), "0"));
				dataMap6.put("czsyjwc", StringUtil.defaultIfEmpty((String)formDataMap.get("czsyjwc"), "0"));
				dataMap6.put("czswcbl", StringUtil.defaultIfEmpty((String)formDataMap.get("czswcbl"), "100"));

				dataMap6.put("hdsywc", StringUtil.defaultIfEmpty((String)formDataMap.get("hdsywc"), "0"));
				dataMap6.put("hdsyjwc", StringUtil.defaultIfEmpty((String)formDataMap.get("hdsyjwc"), "0"));
				dataMap6.put("hdswcbl", StringUtil.defaultIfEmpty((String)formDataMap.get("hdswcbl"), "100"));


				//活动形式
				String hdxs="";
				if(StringUtil.isBlank((String)formDataMap.get("hdxs"))) {
					String recTypeId = ResRecTypeEnum.CampaignRegistry.getId();
					List<ResRec> resRecList2 = resRecBiz.searchResRecWithBLOBs(recTypeId, processFlow, userFlow);
					SchRotationDept schRotationDept = new SchRotationDept();
					List<SchRotationDeptReq> rotationDeptReqList = new ArrayList<SchRotationDeptReq>();
					if (StringUtil.isNotBlank(schArrangeResult.getStandardGroupFlow()) && StringUtil.isNotBlank(schArrangeResult.getStandardDeptId())) {
						schRotationDept = schRotationDeptBiz.searchGroupFlowAndStandardDeptIdQuery(schArrangeResult.getStandardGroupFlow(), schArrangeResult.getStandardDeptId());
					}
					if (StringUtil.isNotBlank(schRotationDept.getRecordFlow())) {
						rotationDeptReqList = schRotationDeptBiz.searchDeptReqByRel(schRotationDept.getRecordFlow(), recTypeId);
					}
					List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
					Map<String, String> datasMap = new HashMap<String, String>();
					for (ResRec resRec2 : resRecList2) {
						String s = datasMap.get(resRec2.getItemId());
						int c = 0;
						if (s == null) {
							c = 0;
						} else {
							c = Integer.valueOf(s).intValue();
						}
						c++;
						s = String.valueOf(c);
						datasMap.put(resRec.getItemId(), s);
					}
					for (SchRotationDeptReq schRotationDeptReq : rotationDeptReqList) {
						hdxs+=StringUtil.defaultIfEmpty(schRotationDeptReq.getItemName(), "")+":"+StringUtil.defaultIfEmpty(datasMap.get(schRotationDeptReq.getItemId()), "0")+"次";
						maps.add(dataMap);
					}
				}else{
					hdxs=(String)formDataMap.get("hdxs");
				}
				dataMap6.put("hdxs",hdxs);

				dataMap6.put("totalScore", StringUtil.defaultIfEmpty((String)formDataMap.get("totalScore"), " "));
				dataMap6.put("skillName", StringUtil.defaultIfEmpty((String)formDataMap.get("skillName"), " "));
				dataMap6.put("score", StringUtil.defaultIfEmpty((String)formDataMap.get("score"), " "));
				dataMap6.put("examiner1", StringUtil.defaultIfEmpty((String)formDataMap.get("examiner1"), " "));
				dataMap6.put("examiner2", StringUtil.defaultIfEmpty((String)formDataMap.get("examiner2"), " "));
				dataMap6.put("szkskhxzztpj", StringUtil.defaultIfEmpty((String)formDataMap.get("szkskhxzztpj"), " "));
				dataMap6.put("teacherName", StringUtil.defaultIfEmpty((String)formDataMap.get("teacherName"), " "));
				dataMap6.put("teacherDate", StringUtil.defaultIfEmpty((String)formDataMap.get("teacherDate"), " "));
				dataMap6.put("directorName", StringUtil.defaultIfEmpty((String)formDataMap.get("directorName"), " "));
				dataMap6.put("directorDate", StringUtil.defaultIfEmpty((String)formDataMap.get("directorDate"), " "));

				WordprocessingMLPackage temeplete6 = new WordprocessingMLPackage();
				String path6 = "/jsp/res/downForm/ckkhb.docx";//模板
				temeplete6 = Docx4jUtil.convert(new File(context.getRealPath(path6)), dataMap6, "", true);
				addTemplates.add(temeplete6);
			}
			//出科小结

			Map<String, Object> dataMap7 = new HashMap<String, Object>();

			List<ResSchProcessExpress> rrs = expressBiz.searchResRecExpressWithBLOBs(AfterRecTypeEnum.AfterSummary.getId(), processFlow);
			if (rrs != null && !rrs.isEmpty()) {

				ResSchProcessExpress resRec=rrs.get(0);
				Map<String, Object> formDataMap = resRecBiz.parseRecContent(resRec.getRecContent());
				if(formDataMap==null)
					formDataMap=new HashMap<>();

				dataMap7.put("personalSummary", StringUtil.defaultIfEmpty((String) formDataMap.get("personalSummary"), " "));

				WordprocessingMLPackage temeplete7 = new WordprocessingMLPackage();
				String path7 = "/jsp/res/downForm/summary.docx";//模板
				temeplete7 = Docx4jUtil.convert(new File(context.getRealPath(path7)), dataMap7, "", true);
				addTemplates.add(temeplete7);
			}

			//门诊跟师个人小结
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_"+AfterRecTypeEnum.DiscipleSummary.getId()+"_form_flag"))) {
				Map<String, Object> dataMap8 = new HashMap<String, Object>();

				List<ResSchProcessExpress> ds = expressBiz.searchResRecExpressWithBLOBs(AfterRecTypeEnum.DiscipleSummary.getId(), processFlow);
				if (ds != null && !ds.isEmpty()) {

					ResSchProcessExpress resRec = ds.get(0);
					Map<String, Object> formDataMap = resRecBiz.parseRecContent(resRec.getRecContent());
					if (formDataMap == null)
						formDataMap = new HashMap<>();

					dataMap8.put("personalSummary", StringUtil.defaultIfEmpty((String) formDataMap.get("personalSummary"), " "));

					WordprocessingMLPackage temeplete7 = new WordprocessingMLPackage();
					String path7 = "/jsp/res/downForm/disciple.docx";//模板
					temeplete7 = Docx4jUtil.convert(new File(context.getRealPath(path7)), dataMap8, "", true);
					addTemplates.add(temeplete7);
				}
			}

		}

		WordprocessingMLPackage temeplete = Docx4jUtil.mergeDocx(addTemplates);
		if(temeplete!=null){
			 name += "科室轮转详情.docx";
			response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
			response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			ServletOutputStream out = response.getOutputStream ();
			(new SaveToZipFile (temeplete)).save (out);
			out.flush ();
		}
	}

	/**
	 * 按轮转记录（res_doctor_sch_process）导出（配置的表单数据）
	 * @param request
	 * @param response
	 * @param doctorFlow
	 * @param processFlow
	 * @throws Exception
	 */
	@RequestMapping(value="/registryDaochuByDept")
	public void registryDaochuByDept(HttpServletRequest request,HttpServletResponse response,
									 String doctorFlow,String processFlow)throws Exception{
		doctorFlow = "7fab6138375c485a8a843747a343e5f4";
		processFlow = "bf9a8867f89542b7af6f973e4b6e0e5a";
		List<WordprocessingMLPackage> addTemplates = new ArrayList<WordprocessingMLPackage>();
		Map<String, Object> dataMap = null;
		ResDoctorSchProcess process = resDoctorProcessBiz.read(processFlow);
		String name = "";
		if(process != null) {
			name = process.getSchDeptName();
			ServletContext application = request.getServletContext();
			Map<String, String> sysCfgMap = (Map<String, String>)application.getAttribute("sysCfgMap");
			//学员登记类型的所有枚举
			RegistryTypeEnum[] registryTypes = RegistryTypeEnum.values();
			int size = 1;
			for(RegistryTypeEnum tempEnum : registryTypes){
				String registryTypeId = tempEnum.getId(); //登记类型 ID  (如：CaseRegistry...)
				String key = "res_registry_type_";
				key += registryTypeId;
				//取得后台配置对应的学员登记类型（Y）
				if(GlobalConstant.FLAG_Y.equals(sysCfgMap.get(key))){
					String recTypeName = NumTrans.transNum(String.valueOf(size))+"、"+tempEnum.getName();
					//获取对应模板
					String titlePath = "/jsp/res/downForm/title.docx";
					Map<String,Object> titleMap = new HashMap<>();
					titleMap.put("recTypeName", recTypeName);
					WordprocessingMLPackage titleTemeplete = Docx4jUtil.convert(new File(application.getRealPath(titlePath)), titleMap, "", true);
					addTemplates.add(titleTemeplete);
					size++;

					int seq1 = 0;//序号
					List<ResRec> resRecList = resRecBiz.searchResRecWithBLOBs(registryTypeId, processFlow, doctorFlow);
					if (resRecList != null && !resRecList.isEmpty()) {
						List<ItemGroupData> targetList = null;
						Map<String,List<ItemGroupData>> targetListMap = new HashMap<>();
						for (ResRec tempRec : resRecList) {
							targetList = new ArrayList<>();
							XmlParse irbFormXp = null;
							List<Element> productTypeElements = null;
							String productType = null;
							List<Element> pageElements = null;
							List<Element> itemElements = null;
							//根据登记类型ID  获了XML文件
							irbFormXp = new XmlParse(SpringUtil.getResource("classpath:" + GlobalConstant.RES_FORM_CONFIG_PATH + "/" + registryTypeId + ".xml").getFile());

							productTypeElements = irbFormXp.getRootElement().elements();//XML文件根节点下的子节点列表

							String currVer = "";
							currVer = tempRec.getRecVersion();//版本号
							String recForm = "";
							recForm = tempRec.getRecForm();//表单配置
							//循环子节点列表
							for (Element productEle : productTypeElements) {
								productType = productEle.getName();//子节点名
								//找到与系统默认表单来源名相同的子节点
								if (productType.equals(recForm)) {
									pageElements = productEle.elements();

									//循环子节点下的page列表
									Map<String, Object> formDataMap = resRecBiz.parseRecContent(tempRec.getRecContent());
									Map<String, Object> objMap = new HashMap<String, Object>();
									String recTypeSeq1 = registryTypeId + "_seq";
									for (Element pageEle : pageElements) {
										if(currVer.equals(pageEle.attributeValue("ver"))){
											itemElements = pageEle.elements();
											for (Element itemEle : itemElements) {
												String itemName = itemEle.attributeValue("name");
												String tempKey = registryTypeId + "_" + itemName;
												objMap.put(tempKey, formDataMap.get(itemName));
											}
											objMap.put(recTypeSeq1, String.valueOf(seq1++));
											ItemGroupData igd = new ItemGroupData();
											igd.setObjMap(objMap);
											targetList.add(igd);
										}
									}
								}
							}
							//下面代码用于处理resRec的recForm不同的情况类似于
							//第一条记录recFrom为jzzy第二条记录为jsst第三条记录recFrom为jzzy
							//此时第一第三条要合并到同一模板中
							List<ItemGroupData> tempTargetList = targetListMap.get(recForm + "_" + currVer);
							if(tempTargetList == null){
								targetListMap.put(recForm + "_" + currVer,targetList);
							}else {
								targetListMap.get(recForm + "_" + currVer).addAll(targetList);
							}
						}
						WordprocessingMLPackage temeplete = null;
						for (Map.Entry<String, List<ItemGroupData>> entry : targetListMap.entrySet()) {
							String keyPath = entry.getKey();
							List<ItemGroupData> tempList = entry.getValue();
							String[] tempArr = keyPath.split("_");
							String recForm =  tempArr[0];//表单配置
							String currVer = tempArr[1];//版本号
							//获取对应模板
							String temepletePath = "/jsp/res/downForm/"  + recForm + "/" + registryTypeId + "_" + currVer + ".docx";
							dataMap = new HashMap<>();
							dataMap.put("targetList", tempList);
							temeplete = Docx4jUtil.convert(new File(application.getRealPath(temepletePath)), dataMap, "", true);
							addTemplates.add(temeplete);
						}
						//有申述
						List<ItemGroupData> appealsTargetList = null;
						if(GlobalConstant.FLAG_Y.equals(tempEnum.getHaveAppeal())){
							List<ResAppeal> appeals = resRecBiz.searchAppealForAudit(processFlow,tempEnum.getId());
							int seq2 = 0;//序号
							if(appeals!=null&&!appeals.isEmpty())
							{
								seq2 = 1;
								appealsTargetList = new ArrayList<ItemGroupData>();
								for(ResAppeal appeal:appeals)
								{
									Map<String, Object> objMap = new HashMap<String, Object>();
									String num = "";
									if(appeal.getAppealNum() != null)
										num = appeal.getAppealNum().toString();
									objMap.put("seq", String.valueOf(seq2++));
									objMap.put("typeName", appeal.getItemName());
									objMap.put("appealNum",num);
									objMap.put("appealResaon", appeal.getAppealReason());
									ItemGroupData igd = new ItemGroupData();
									igd.setObjMap(objMap);
									appealsTargetList.add(igd);
								}
								dataMap = new HashMap<>();
								dataMap.put("appealsTargetList", appealsTargetList);
							}
							//获取申诉模板
							String temepletePath = "/jsp/res/downForm/appeal.docx";
							temeplete = Docx4jUtil.convert(new File(application.getRealPath(temepletePath)), dataMap, "", true);
							addTemplates.add(temeplete);
						}
					}
				}
			}
		}
		WordprocessingMLPackage temeplete = Docx4jUtil.mergeDocx(addTemplates);
		if(temeplete!=null){
			name += "科室轮转详情.docx";
			response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
			response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			ServletOutputStream out = response.getOutputStream ();
			(new SaveToZipFile (temeplete)).save (out);
			out.flush ();
		}
	}
}
