package com.pinde.res.ctrl.hzyy;

import com.pinde.app.common.NfyyGlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.hzyy.IHzyyAppBiz;
import com.pinde.res.biz.hzyy.IHzyyKzrBiz;
import com.pinde.res.biz.hzyy.IHzyySecretarieBiz;
import com.pinde.res.biz.hzyy.IHzyyTeacherBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/res/hzyy/kzr")
public class HzyyKzrController {

	private static Logger logger = LoggerFactory.getLogger(HzyySecretarieController.class);

	private boolean alert = true;

	@Autowired
	private IHzyyAppBiz hzyyAppBiz;
	@Autowired
	private IHzyySecretarieBiz hzyySecretarieBiz;
	@Autowired
	private IHzyyKzrBiz hzyyKzrBiz;
	@Autowired
	private IHzyyTeacherBiz hzyyTeacherBiz;

	@Value("#{configProperties['hzyy.upload.base.dir']}")
	public  String baseDir;
	@Value("#{configProperties['hzyy.upload.dir']}")
	public  String uploadDir;
	@Value("#{configProperties['hzyy.upload.base.url']}")
	public  String baseUrl;
	@Value("#{configProperties['hzyy.upload.all.suffix']}")
	public  String allFileSuf;
	@Value("#{configProperties['hzyy.upload.img.suffix']}")
	public  String imgSuf;
	@Value("#{configProperties['hzyy.upload.file.suffix']}")
	public  String fileSuf;

	@ExceptionHandler(Throwable.class)
	public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "/res/hzyy/500";
	}
	//首页学员列表接口
	@RequestMapping(value={"/studentList"},method={RequestMethod.POST})
	public String studentList(String userFlow , String roleId , Integer pageIndex , Integer pageSize , String searhStr, Model model){
		String result = "/res/hzyy/kzr/studentList";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_ROLEID_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_ROLEID_NULL);
			return result;
		}
		if(pageIndex==null){
			pageIndex = NfyyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = NfyyGlobalConstant.DEFAULT_PAGE_SIZE;
		}

		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);

		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		List<String> hosSecIds=hzyySecretarieBiz.getHosSecId(userFlow);
		if(hosSecIds==null||hosSecIds.size()<=0)
		{	model.addAttribute("resultId", "300210");
			model.addAttribute("resultType", "用户无所在科室");
			return result;
		}
			int dataCount = 0;
			Map<String,Object> param=new HashMap<>();
			param.put("userFlow",userFlow);
			param.put("HosID",user.get("hosID"));
			param.put("HosSecID",hosSecIds);
			param.put("searhStr",searhStr);
			List<Map<String,Object>> studentList = hzyyKzrBiz.getStudentList(param,pageIndex,pageSize);
			model.addAttribute("studentList" , studentList);
			List<Map<String,Object>> all =hzyyKzrBiz.getStudentList(param,1,Integer.MAX_VALUE);
			if(all!=null)
				dataCount=all.size();

		model.addAttribute("dataCount", dataCount);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping(value={"/funcList"},method={RequestMethod.POST})
	public String funcList(String userFlow , String UCSID, Model model){
		String result = "/res/hzyy/kzr/funcList";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		if(StringUtil.isBlank(UCSID)){
			model.addAttribute("resultId", "310023");
			model.addAttribute("resultType","轮转标识为空");
			return result;
		}
		List<Map<String,String>> funcList=new ArrayList<>();
		Map<String,String> d1=new HashMap<>();
		d1.put("funcId","dataAudit");
		d1.put("funcName","数据审核");
		d1.put("img","dataAudit");
		funcList.add(d1);

		d1=new HashMap<>();
		d1.put("funcId","skillExam");
		d1.put("funcName","技能考核");
		d1.put("img","skillExam");
		funcList.add(d1);
		String isNeed=hzyyTeacherBiz.isNeedPatient(UCSID);
		model.addAttribute("isNeed" , isNeed);
		if("Y".equals(isNeed)) {
			d1 = new HashMap<>();
			d1.put("funcId", "receivePatient");
			d1.put("funcName", "接诊病人");
			d1.put("img", "receivePatient");
			funcList.add(d1);
		}
		d1=new HashMap<>();
		d1.put("funcId","cycleEval");
		d1.put("funcName","过程评价");
		d1.put("img","resEval");
		funcList.add(d1);

		d1=new HashMap<>();
		d1.put("funcId","newAfterEvaluation");
		d1.put("funcName","出科考核表");
		d1.put("img","after");
		funcList.add(d1);
		model.addAttribute("funcList",funcList);
		return result;
	}

	@RequestMapping(value={"/dataAudit"},method={RequestMethod.GET,RequestMethod.POST})
	public String dataAudit(String userFlow,String UCSID,String cysecId ,Model model){

		String result = "res/hzyy/kzr/dataAudit";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(UCSID)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		List<Map<String,String>> datas=new ArrayList<>();
			Map<String, String> data = new HashMap<>();
			data.put("dataTypeName", "大病历");
			data.put("dataTypeId", "mr");
			datas.add(data);
			data = new HashMap<>();
			data.put("dataTypeName", "病种");
			data.put("dataTypeId", "disease");
			datas.add(data);
			data = new HashMap<>();
			data.put("dataTypeName", "操作技能");
			data.put("dataTypeId", "skill");
			datas.add(data);
			data = new HashMap<>();
			data.put("dataTypeName", "手术");
			data.put("dataTypeId", "operation");
			datas.add(data);
			data = new HashMap<>();
			data.put("dataTypeName", "门诊病历");
			data.put("dataTypeId", "disciple");
			datas.add(data);
		model.addAttribute("datas", datas);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/dataAuditList"},method={RequestMethod.GET})
	public String dataAuditList(String userFlow,String UserID,
								Integer pageIndex,Integer pageSize,String dataTypeId ,
								String cysecId ,Model model){

		String result = "res/hzyy/kzr/dataAuditList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(UserID)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(cysecId)){
			model.addAttribute("resultId","231212");
			model.addAttribute("resultType", "cysecId为空");
			return result;
		}
		if(StringUtil.isEmpty(dataTypeId)||
				!("mr".equals(dataTypeId)||"disease".equals(dataTypeId)||"skill".equals(dataTypeId)
						||"operation".equals(dataTypeId)||"disciple".equals(dataTypeId))){
			model.addAttribute("resultId", "040301");
			model.addAttribute("resultType", "数据类型为空或错误");
			return result;
		}

		if(pageIndex==null){
			pageIndex = NfyyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = NfyyGlobalConstant.DEFAULT_PAGE_SIZE;
		}
		Map<String , Object> searchMap =new HashMap<>();
		searchMap.put("doctorFlow",UserID);
		searchMap.put("schStatusId","Exited");
		searchMap.put("cySecId",cysecId);
		searchMap.put("dataTypeId",dataTypeId);
		searchMap.put("pageSize",pageSize);
		searchMap.put("pageIndex",pageIndex);

		model.addAttribute("dataCount", 0);
		//大病历
		if("mr".equals(dataTypeId)){
			List<Map<String,String>> datas = hzyyTeacherBiz.getMrDatas(searchMap);
			Map<String,String> finishMap = hzyyTeacherBiz.getMrFinishMap(searchMap);
			model.addAttribute("finishMap" , finishMap);
			model.addAttribute("datas" , datas);

			searchMap.put("pageIndex",1);
			searchMap.put("pageSize",Integer.MAX_VALUE);
			model.addAttribute("dataCount", hzyyTeacherBiz.getMrDatas(searchMap).size());
		}
		//病种
		if("disease".equals(dataTypeId)){
			List<Map<String,String>> datas = hzyyTeacherBiz.getDiseaseDatas(searchMap);
			Map<String,String> finishMap = hzyyTeacherBiz.getDiseaseFinishMap(searchMap);
			model.addAttribute("finishMap" , finishMap);
			model.addAttribute("datas" , datas);

			searchMap.put("pageIndex",1);
			searchMap.put("pageSize",Integer.MAX_VALUE);
			int count = hzyyTeacherBiz.getDiseaseDatas(searchMap).size();
			model.addAttribute("dataCount" , count);
		}
		//操作技能
		if("skill".equals(dataTypeId)){
			List<Map<String,String>> datas = hzyyTeacherBiz.getSkillDatas(searchMap);
			Map<String,String> finishMap = hzyyTeacherBiz.getSkillFinishMap(searchMap);
			model.addAttribute("finishMap" , finishMap);
			model.addAttribute("datas" , datas);

			searchMap.put("pageIndex",1);
			searchMap.put("pageSize",Integer.MAX_VALUE);
			int count =  hzyyTeacherBiz.getSkillDatas(searchMap).size();
			model.addAttribute("dataCount" , count);
		}
		//手术
		if("operation".equals(dataTypeId)){
			List<Map<String,String>> datas = hzyyTeacherBiz.getOperationDatas(searchMap);
			Map<String,String> finishMap = hzyyTeacherBiz.getOperationFinishMap(searchMap);
			model.addAttribute("finishMap" , finishMap);
			model.addAttribute("datas" , datas);

			searchMap.put("pageIndex",1);
			searchMap.put("pageSize",Integer.MAX_VALUE);
			int count =  hzyyTeacherBiz.getOperationDatas(searchMap).size();
			model.addAttribute("dataCount" , count);
		}
		//门诊病历
		if("disciple".equals(dataTypeId)){
			List<Map<String,String>> datas = hzyyTeacherBiz.getDiscipleDatas(searchMap);
			Map<String,String> finishMap = hzyyTeacherBiz.getDiscipleFinishMap(searchMap);
			model.addAttribute("finishMap" , finishMap);
			model.addAttribute("datas" , datas);

			searchMap.put("pageIndex",1);
			searchMap.put("pageSize",Integer.MAX_VALUE);
			int count =  hzyyTeacherBiz.getDiscipleDatas(searchMap).size();
			model.addAttribute("dataCount" , count);
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/dataDetail"},method={RequestMethod.GET})
	public String dataDetail(String userFlow,String dataFlow,String dataTypeId ,Model model){

		String result = "res/hzyy/kzr/dataDetail";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(dataTypeId)||
				!("mr".equals(dataTypeId)||"disease".equals(dataTypeId)||"skill".equals(dataTypeId)
						||"operation".equals(dataTypeId)||"disciple".equals(dataTypeId))){
			model.addAttribute("resultId", "040301");
			model.addAttribute("resultType", "数据类型为空或错误");
			return result;
		}
		if(StringUtil.isEmpty(dataFlow)){
			model.addAttribute("resultId", "040301");
			model.addAttribute("resultType", "数据标识符为空或错误");
			return result;
		}
		Map<String,String>data=null;
		//大病历
		if("mr".equals(dataTypeId)){
			data=hzyyTeacherBiz.getMrData(dataFlow);

		}
		//病种
		if("disease".equals(dataTypeId)){
			data=hzyyTeacherBiz.getDiseaseData(dataFlow);

		}
		//操作技能
		if("skill".equals(dataTypeId)){
			data=hzyyTeacherBiz.getSkillData(dataFlow);

		}
		//手术
		if("operation".equals(dataTypeId)){
			data=hzyyTeacherBiz.getOperationData(dataFlow);

		}
		//门诊病历
		if("disciple".equals(dataTypeId)){
			data=hzyyTeacherBiz.getDiscipleData(dataFlow);

		}
		model.addAttribute("data", data);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping("/selectSkill")
	public String selectSkill(String userFlow , String UCSID, Model model) throws UnsupportedEncodingException {

		String result = "res/hzyy/kzr/selectSkill";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(UCSID)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}

		Map<String,String> outSkillMap=hzyyTeacherBiz.getOutSkill(UCSID);
		if(outSkillMap==null||outSkillMap.isEmpty())
		{
			model.addAttribute("resultId", "0302021");
			model.addAttribute("resultType", "带教未审核");
			return result;
		}
		Map<String,String> outSecBrief=hzyyTeacherBiz.getOutSecBrief(UCSID);
		if(outSecBrief!=null&&("2".equals(outSecBrief.get("VerifyState"))||"1".equals(outSecBrief.get("CheckStatus"))))
		{
			model.addAttribute("read", true);
		}else{
			model.addAttribute("read", false);
		}
		Map<String,String> cycleInfo=hzyyTeacherBiz.getCycleInfo(UCSID);
		model.addAttribute("cycleInfo", cycleInfo);
		String skillFlow=outSkillMap.get("Skill_TFlow");
		model.addAttribute("skillFlow", skillFlow);
		//头部病人信息、底部总分数信息
		Map<String,List<Map<String,String>>> itemsMap=new HashMap<>();
		model.addAttribute("outSkillMap", outSkillMap);
		//评分表主表
		List<Map<String,String>> items=hzyyTeacherBiz.getSkillItems(skillFlow,"Y");
		if(items!=null)
		{
			for(Map<String,String> item:items)
			{
				List<Map<String,String>> subItems=hzyyTeacherBiz.getSkillItems(item.get("TItemFlow"), "N");
				itemsMap.put(item.get("TItemFlow"),subItems);
			}
		}
		//获取评分结果
		if(outSkillMap!=null) {
			Map<String, String> scoreMap = hzyyTeacherBiz.getSkillScorelMap(outSkillMap.get("Skill_ID"));
			model.addAttribute("itemScoreMap", scoreMap);
			List<Map<String, String>> fileList = hzyyTeacherBiz.getFileList(String.valueOf(outSkillMap.get("Skill_AttachID")));
			model.addAttribute("fileList", fileList);
		}
		model.addAttribute("baseUrl",baseUrl);
		model.addAttribute("itemsMap", itemsMap);
		model.addAttribute("items", items);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping("/selectPatient")
	public String selectPatient(String userFlow , String UCSID, Model model) throws UnsupportedEncodingException{

		String result = "res/hzyy/kzr/selectPatient";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(UCSID)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}

		Map<String,String> outPatientMap=hzyyTeacherBiz.getOutPatient(UCSID);
		model.addAttribute("outPatientMap", outPatientMap);
		if(outPatientMap==null||outPatientMap.isEmpty())
		{
			model.addAttribute("resultId", "0302021");
			model.addAttribute("resultType", "带教未审核");
			return result;
		}
		Map<String,String> outSecBrief=hzyyTeacherBiz.getOutSecBrief(UCSID);
		if(outSecBrief!=null&&("2".equals(outSecBrief.get("VerifyState"))||"1".equals(outSecBrief.get("CheckStatus"))))
		{
			model.addAttribute("read", true);
		}else{
			model.addAttribute("read", false);
		}
		Map<String,String> cycleInfo=hzyyTeacherBiz.getCycleInfo(UCSID);
		model.addAttribute("cycleInfo", cycleInfo);
		model.addAttribute("patientTypeId", outPatientMap.get("Patient_Type"));
		//头部病人信息、底部总分数信息
		Map<String,List<Map<String,String>>> itemsMap=new HashMap<>();
		//获取评分结果
		if(outPatientMap!=null) {
			Map<String, String> scoreMap = hzyyTeacherBiz.getPatientScorelMap(outPatientMap.get("Patient_ID"));
			model.addAttribute("itemScoreMap", scoreMap);
			List<Map<String, String>> fileList = hzyyTeacherBiz.getFileList(String.valueOf(outPatientMap.get("Patient_AttachID")));
			model.addAttribute("fileList", fileList);
		}
		model.addAttribute("baseUrl",baseUrl);
		model.addAttribute("itemsMap", itemsMap);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/newAfterEvaluation"},method={RequestMethod.POST,RequestMethod.GET})
	public String newAfterEvaluation(String userFlow,String UCSID, Model model){

		String result = "/res/hzyy/kzr/newAfterEvaluation";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(UCSID)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}

		Map<String,String> cycleOutInfo = hzyyTeacherBiz.getCycleOutInfo(UCSID);
		model.addAttribute("cycleOutInfo" , cycleOutInfo);
		if(cycleOutInfo==null||cycleOutInfo.isEmpty())
		{
			model.addAttribute("resultId", "30201021");
			model.addAttribute("resultType", "带教暂未审核");
			return result;
		}
		Map<String,String> afterEva = hzyyTeacherBiz.readAfterEva(UCSID);
		model.addAttribute("afterEva" , afterEva);
		if(afterEva!=null)
		{
			if("0".equals(String.valueOf(afterEva.get("SectionType"))))
			{
				if(StringUtil.isNotBlank(String.valueOf(afterEva.get("TheoryScore")))){
					Double TheoryScore=Double.valueOf(afterEva.get("TheoryScore"));
					BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.4).setScale(1, BigDecimal.ROUND_HALF_UP);
					afterEva.put("TheoryScore",String.valueOf(bigDecimal.doubleValue()));
				}
				if(StringUtil.isNotBlank(String.valueOf(afterEva.get("Patient_Score")))){
					Double TheoryScore=Double.valueOf(afterEva.get("Patient_Score"));
					BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.2).setScale(1, BigDecimal.ROUND_HALF_UP);
					afterEva.put("Patient_Score",String.valueOf(bigDecimal.doubleValue()));
				}
				if(StringUtil.isNotBlank(String.valueOf(afterEva.get("Skill_Score")))){
					Double TheoryScore=Double.valueOf(afterEva.get("Skill_Score"));
					BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.2).setScale(1, BigDecimal.ROUND_HALF_UP);
					afterEva.put("Skill_Score",String.valueOf(bigDecimal.doubleValue()));
				}
			}
			if("1".equals(String.valueOf(afterEva.get("SectionType"))))
			{

				if(StringUtil.isNotBlank(String.valueOf(afterEva.get("TheoryScore")))){
					Double TheoryScore=Double.valueOf(afterEva.get("TheoryScore"));
					BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.5).setScale(1, BigDecimal.ROUND_HALF_UP);
					afterEva.put("TheoryScore",String.valueOf(bigDecimal.doubleValue()));
				}
				if(StringUtil.isNotBlank(String.valueOf(afterEva.get("Skill_Score")))){
					Double TheoryScore=Double.valueOf(afterEva.get("Skill_Score"));
					BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.3).setScale(1, BigDecimal.ROUND_HALF_UP);
					afterEva.put("Skill_Score",String.valueOf(bigDecimal.doubleValue()));
				}
			}
			if("2".equals(String.valueOf(afterEva.get("SectionType"))))
			{
				if(StringUtil.isNotBlank(String.valueOf(afterEva.get("TheoryScore")))){
					Double TheoryScore=Double.valueOf(afterEva.get("TheoryScore"));
					BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.8).setScale(1, BigDecimal.ROUND_HALF_UP);
					afterEva.put("TheoryScore",String.valueOf(bigDecimal.doubleValue()));
				}
			}
		}
		int jxhd=hzyyTeacherBiz.getJxhd(UCSID);
		model.addAttribute("jxhd" , jxhd);
		Map<String,String> outSecBrief=hzyyTeacherBiz.getOutSecBrief(UCSID);
		if(outSecBrief!=null&&("2".equals(outSecBrief.get("VerifyState"))||"1".equals(outSecBrief.get("CheckStatus"))))
		{
			model.addAttribute("read", true);
		}else{
			model.addAttribute("read", false);
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		String isNeed=hzyyTeacherBiz.isNeedPatient(UCSID);
		model.addAttribute("isNeed" , isNeed);
		return result;
	}
	@RequestMapping(value={"/cycleEval"},method={RequestMethod.POST,RequestMethod.GET})
	public String cycleEval(String userFlow,String UCSID, Model model){

		String result = "/res/hzyy/kzr/cycleEval";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(UCSID)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}

		Map<String,Object> evalInfo = hzyyTeacherBiz.readEvalInfo(userFlow, UCSID);
		if(evalInfo==null||evalInfo.isEmpty()||StringUtil.isBlank((String) evalInfo.get("Eval")))
		{
			model.addAttribute("resultId", "30201021");
			model.addAttribute("resultType", "带教暂未评价");
			return result;
		}
		model.addAttribute("evalInfo", evalInfo);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/ownerInfo"},method={RequestMethod.GET})
	public String ownerInfo(String userFlow , Model model){
		String result = "/res/hzyy/kzr/ownerInfo";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		Map<String,String> user = hzyySecretarieBiz.readUserInfo(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		List<Map<String,String>> roles=hzyyAppBiz.readUserRoles(userFlow);
		model.addAttribute("roles", roles);
		return result;
	}

	@RequestMapping(value={"/deptList"},method={RequestMethod.POST})
	public String deptList(String userFlow , String roleId , Integer pageIndex , Integer pageSize , String searhStr, Model model){
		String result = "/res/hzyy/kzr/deptList";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_ROLEID_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_ROLEID_NULL);
			return result;
		}
		if(pageIndex==null){
			pageIndex = NfyyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = NfyyGlobalConstant.DEFAULT_PAGE_SIZE;
		}

		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);

		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		int dataCount = 0;
		Map<String,Object> param=new HashMap<>();
		param.put("userFlow",userFlow);
		param.put("searhStr",searhStr);
		List<Map<String,Object>> deptList = hzyyKzrBiz.getDeptList(param,pageIndex,pageSize);
		model.addAttribute("deptList" , deptList);
		List<Map<String,Object>> all =hzyyKzrBiz.getDeptList(param,1,Integer.MAX_VALUE);
		if(all!=null)
			dataCount=all.size();

		model.addAttribute("dataCount", dataCount);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping(value={"/deptEvalInfo"},method={RequestMethod.POST})
	public String deptEvalInfo(String userFlow , String HosSecID , String year, Model model){
		String result = "/res/hzyy/kzr/deptEvalInfo";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(HosSecID)){
			model.addAttribute("resultId", "20120121");
			model.addAttribute("resultType", "科室标识符为空！");
			return result;
		}
		if(StringUtil.isBlank(year)){
			year= DateUtil.getYear();
		}
		model.addAttribute("year",year);
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);

		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		List<Map<String,Object>> evals = hzyyKzrBiz.getDeptEvalInfo(user.get("hosID"),HosSecID,year);

		model.addAttribute("evals", evals);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping(value={"/teacherList"},method={RequestMethod.POST})
	public String teacherList(String userFlow , String roleId , Integer pageIndex , Integer pageSize , String searhStr, Model model){
		String result = "/res/hzyy/kzr/teacherList";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_ROLEID_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_ROLEID_NULL);
			return result;
		}
		if(pageIndex==null){
			pageIndex = NfyyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = NfyyGlobalConstant.DEFAULT_PAGE_SIZE;
		}

		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);

		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String userType = user.get("roleId");
		if(StringUtil.isBlank(userType)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		int dataCount = 0;
		Map<String,Object> param=new HashMap<>();
		param.put("userFlow",userFlow);
		param.put("searhStr",searhStr);
		param.put("hosID",user.get("hosID"));
		param.put("szks",user.get("szks"));
		List<Map<String,Object>> teacherList = hzyyKzrBiz.getTeacherList(param,pageIndex,pageSize);
		model.addAttribute("teacherList" , teacherList);
		List<Map<String,Object>> all =hzyyKzrBiz.getTeacherList(param,1,Integer.MAX_VALUE);
		if(all!=null)
			dataCount=all.size();

		model.addAttribute("dataCount", dataCount);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping(value={"/teacherEvalInfo"},method={RequestMethod.POST})
	public String teacherEvalInfo(String userFlow , String teaFlow , String startTime,String endTime, Model model){
		String result = "/res/hzyy/kzr/teacherEvalInfo";
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(teaFlow)){
			model.addAttribute("resultId", "20120121");
			model.addAttribute("resultType", "带教标识符为空！");
			return result;
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);

		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		List<Map<String,Object>> evals = hzyyKzrBiz.getTeacherEvalInfo(user.get("hosID"),teaFlow,startTime,endTime);

		model.addAttribute("evals", evals);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

}
