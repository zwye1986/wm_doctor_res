package com.pinde.res.ctrl.hzyy;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.NfyyGlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.hzyy.IHzyyAppBiz;
import com.pinde.res.biz.hzyy.IHzyyStudentBiz;
import com.pinde.res.biz.hzyy.IHzyyTeacherBiz;
import com.pinde.res.ctrl.upload.FileForm;
import com.pinde.res.enums.nfyy.DeptStatusEnum;
import com.pinde.res.model.nfyy.mo.MedicalInfo;
import com.pinde.res.model.nfyy.mo.StudyInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.*;

@Controller
@RequestMapping("/res/hzyy/teacher")
public class HzyyTeacherController {

private static Logger logger = LoggerFactory.getLogger(HzyyTeacherController.class);

	@Autowired
	private IHzyyAppBiz hzyyAppBiz;
	@Autowired
	private IHzyyStudentBiz hzyyStudentBiz;
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
		logger.error(getClass().getCanonicalName()+" some error happened",e);
		return "res/hzyy/500";
    }
	
	@RequestMapping("/schDoctorList")
	public String schDoctorList(String userFlow , String searchData , Integer pageIndex,Integer pageSize , Model model) throws UnsupportedEncodingException{
		String schStatusId = "";
		String doctorType = "";
		String doctorName = "";
		if(StringUtil.isNotBlank(searchData)){
			searchData = new String(searchData.getBytes("ISO8859-1") , "UTF-8");
			Map<String , String> searchMap = (Map<String, String>) JSON.parse(searchData);
			
			for(String key:searchMap.keySet()){
				//System.out.println(key+":"+URLDecoder.decode(searchMap.get(key), "UTF-8"));
				searchMap.put(key, URLDecoder.decode(searchMap.get(key), "UTF-8"));
			}
			schStatusId = searchMap.get("schStatusId");
			doctorType = searchMap.get("doctorType");
			doctorName = searchMap.get("doctorName");
		}
		
		String result = "res/hzyy/teacher/schDoctorList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(schStatusId)){
			schStatusId = DeptStatusEnum.Entering.getId();
		}
		if(StringUtil.isBlank(doctorType)){
			doctorType = "Trainee"; // Trainee Graduate
		}
		if(pageIndex==null){
			pageIndex = NfyyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = NfyyGlobalConstant.DEFAULT_PAGE_SIZE;
		}
		List<Map<String,Object>> doctotList = hzyyTeacherBiz.getDoctorList(userFlow, schStatusId, doctorName, doctorType, pageIndex, pageSize);
		model.addAttribute("doctotList", doctotList);
		model.addAttribute("dataCount", hzyyTeacherBiz.getDoctorList(userFlow, schStatusId, doctorName, doctorType, 1,Integer.MAX_VALUE).size());
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping("/skillList")
	public String skillList(String userFlow , String doctorFlow,String searchData , Integer pageIndex,Integer pageSize , Model model) throws UnsupportedEncodingException{
		String skillName = "";
		if(StringUtil.isNotBlank(searchData)){
			searchData = new String(searchData.getBytes("ISO8859-1") , "UTF-8");
			Map<String , String> searchMap = (Map<String, String>) JSON.parse(searchData);
			for(String key:searchMap.keySet()){
				//System.out.println(key+":"+URLDecoder.decode(searchMap.get(key), "UTF-8"));
				searchMap.put(key, URLDecoder.decode(searchMap.get(key), "UTF-8"));
			}
			skillName = searchMap.get("skillName");
		}

		String result = "res/hzyy/teacher/skillList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(pageIndex==null){
			pageIndex = NfyyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = NfyyGlobalConstant.DEFAULT_PAGE_SIZE;
		}

		//头部病人信息、底部总分数信息
		Map<String,String> outSkillMap=hzyyTeacherBiz.getOutSkill(doctorFlow);
		if(outSkillMap!=null&&!outSkillMap.isEmpty())
		{
			model.addAttribute("isAudit","Y");
			List<Map<String,String>> skillList =new ArrayList<>();
			skillList.add(outSkillMap);
			model.addAttribute("skillList", skillList);
			model.addAttribute("dataCount", 1);
		}else{
			List<Map<String,String>> skillList = hzyyTeacherBiz.getSkillList(skillName, pageIndex, pageSize);
			model.addAttribute("skillList", skillList);
			model.addAttribute("dataCount", hzyyTeacherBiz.getSkillList(skillName, 1,Integer.MAX_VALUE).size());
			model.addAttribute("isAudit","N");
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping("/patientList")
	public String patientList(String userFlow , String doctorFlow , Integer pageIndex,Integer pageSize , Model model) throws UnsupportedEncodingException{

		String result = "res/hzyy/teacher/patientList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(pageIndex==null){
			pageIndex = NfyyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = NfyyGlobalConstant.DEFAULT_PAGE_SIZE;
		}

		Map<String,String> outPatientMap=hzyyTeacherBiz.getOutPatient(doctorFlow);
		if(outPatientMap!=null&&!outPatientMap.isEmpty())
		{
			model.addAttribute("outPatientMap",outPatientMap);
			model.addAttribute("isAudit","Y");
		}else{
			model.addAttribute("isAudit","N");
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	private void setUploadUrl(Model model) {
		model.addAttribute("baseUrl",baseUrl);
	}

	private String checkUploadCfg() {
		if(StringUtil.isBlank(baseDir))
		{
			return "请联系系统管理员，配置文件上传路径！";
		}

		if(StringUtil.isBlank(uploadDir))
		{
			return "请联系系统管理员，配置文件上传保存路径！";
		}
		if(StringUtil.isBlank(baseUrl))
		{
			return "请联系系统管理员，配置访问路径！";
		}
		if(StringUtil.isBlank(fileSuf))
		{
			return "请联系系统管理员，配置上传文件后缀！";
		}
		if(StringUtil.isBlank(imgSuf))
		{
			return "请联系系统管理员，配置图片文件后缀！";
		}
		return "";
	}

	@RequestMapping("/selectSkill")
	public String selectSkill(String userFlow , String doctorFlow,String skillFlow , Model model) throws UnsupportedEncodingException{

		String result = "res/hzyy/teacher/selectSkill";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(skillFlow)){
			model.addAttribute("resultId", "3225221");
			model.addAttribute("resultType", "表单标识符为空");
			return result;
		}

		String checkResult=checkUploadCfg();
		if(StringUtil.isNotBlank(checkResult))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", checkResult);
			return result;
		}

		Map<String,String> outSecBrief=hzyyTeacherBiz.getOutSecBrief(doctorFlow);
		if(outSecBrief!=null&&("2".equals(String.valueOf(outSecBrief.get("VerifyState")))||"1".equals(String.valueOf(outSecBrief.get("CheckStatus")))))
		{
			model.addAttribute("read", true);
		}else{
			model.addAttribute("read", false);
		}
		Map<String,String> cycleInfo=hzyyTeacherBiz.getCycleInfo(doctorFlow);
		model.addAttribute("cycleInfo", cycleInfo);
		//头部病人信息、底部总分数信息
		Map<String,List<Map<String,String>>> itemsMap=new HashMap<>();
		Map<String,String> outSkillMap=hzyyTeacherBiz.getOutSkill(doctorFlow);
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
		model.addAttribute("itemsMap", itemsMap);
		model.addAttribute("items", items);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		setUploadUrl(model);
		return result;
	}
	@RequestMapping("/selectPatient")
	public String selectPatient(String userFlow , String doctorFlow,String patientTypeId , Model model) throws UnsupportedEncodingException{

		String result = "res/hzyy/teacher/selectPatient";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(patientTypeId)){
			model.addAttribute("resultId", "3225221");
			model.addAttribute("resultType", "表单类型为空");
			return result;
		}
		if(!"1".equals(patientTypeId)&&!"2".equals(patientTypeId))
		{
			model.addAttribute("resultId", "3225221");
			model.addAttribute("resultType", "表单类型错误");
			return result;
		}

		String checkResult=checkUploadCfg();
		if(StringUtil.isNotBlank(checkResult))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", checkResult);
			return result;
		}
		Map<String,String> outSecBrief=hzyyTeacherBiz.getOutSecBrief(doctorFlow);
		if(outSecBrief!=null&&("2".equals(String.valueOf(outSecBrief.get("VerifyState")))||"1".equals(String.valueOf(outSecBrief.get("CheckStatus")))))
		{
			model.addAttribute("read", true);
		}else{
			model.addAttribute("read", false);
		}
		Map<String,String> cycleInfo=hzyyTeacherBiz.getCycleInfo(doctorFlow);
		model.addAttribute("cycleInfo", cycleInfo);
		//头部病人信息、底部总分数信息
		Map<String,List<Map<String,String>>> itemsMap=new HashMap<>();
		Map<String,String> outPatientMap=hzyyTeacherBiz.getOutPatient(doctorFlow);
		model.addAttribute("outPatientMap", outPatientMap);
		//获取评分结果
		if(outPatientMap!=null) {
			Map<String, String> scoreMap = hzyyTeacherBiz.getPatientScorelMap(outPatientMap.get("Patient_ID"));
			model.addAttribute("itemScoreMap", scoreMap);
			List<Map<String, String>> fileList = hzyyTeacherBiz.getFileList(String.valueOf(outPatientMap.get("Patient_AttachID")));
			model.addAttribute("fileList", fileList);
		}
		model.addAttribute("itemsMap", itemsMap);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		setUploadUrl(model);
		return result;
	}
	@RequestMapping("/selectTheoryScore")
	public String selectTheoryScore(String userFlow , String doctorFlow ,Model model) throws UnsupportedEncodingException{

		String result = "res/hzyy/teacher/selectTheoryScore";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		Map<String,String> outSecBrief=hzyyTeacherBiz.getOutSecBrief(doctorFlow);
		if(outSecBrief==null||outSecBrief.isEmpty())
		{
			model.addAttribute("resultId", "2029302");
			model.addAttribute("resultType","尚未提交出科小结，无法录入出科理论成绩！");
			return result;
		}
		if(outSecBrief!=null&&("2".equals(String.valueOf(outSecBrief.get("VerifyState")))||"1".equals(String.valueOf(outSecBrief.get("CheckStatus")))))
		{
			model.addAttribute("read", true);
		}else{
			model.addAttribute("read", false);
		}
		model.addAttribute("outSecBrief", outSecBrief);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping("/saveTheoryScore")
	public String saveTheoryScore(String userFlow , String doctorFlow,String TheoryScore,HttpServletRequest request, Model model) throws UnsupportedEncodingException{

		String result = "res/hzyy/teacher/success";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(TheoryScore)){
			model.addAttribute("resultId", "3225221");
			model.addAttribute("resultType", "成绩不能为空");
			return result;
		}

		Map<String,String> outSecBrief=hzyyTeacherBiz.getOutSecBrief(doctorFlow);
		if(outSecBrief==null||outSecBrief.isEmpty())
		{
			model.addAttribute("resultId", "2029302");
			model.addAttribute("resultType","尚未提交出科小结，无法录入出科理论成绩！");
			return result;
		}
		int c=hzyyTeacherBiz.updateOutSecBrief(doctorFlow,TheoryScore);
		if(c==0)
		{
			model.addAttribute("resultId", "2029302");
			model.addAttribute("resultType","保存失败！");
			return result;
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping("/saveSkillScore")
	public synchronized String saveSkillScore(String userFlow , String doctorFlow,String skillFlow,HttpServletRequest request, Model model) throws UnsupportedEncodingException{

		String result = "res/hzyy/teacher/success";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(skillFlow)){
			model.addAttribute("resultId", "3225221");
			model.addAttribute("resultType", "表单标识符为空");
			return result;
		}

		Map<String,String> cycleInfo=hzyyTeacherBiz.getCycleInfo(doctorFlow);
		model.addAttribute("cycleInfo", cycleInfo);
		//头部病人信息、底部总分数信息
		Map<String,String> outSkillMap=hzyyTeacherBiz.getOutSkill(doctorFlow);
		model.addAttribute("outSkillMap", outSkillMap);

		//评分表主表
		Map<String,List<Map<String,String>>> itemsMap=new HashMap<>();
		List<Map<String,String>> items=hzyyTeacherBiz.getSkillItems(skillFlow,"Y");
		if(items!=null)
		{
			for(Map<String,String> item:items)
			{
				List<Map<String,String>> subItems=hzyyTeacherBiz.getSkillItems(item.get("TItemFlow"), "N");
				itemsMap.put(item.get("TItemFlow"),subItems);
			}
		}
		if(outSkillMap!=null&&!outSkillMap.isEmpty()&&StringUtil.isNotBlank(outSkillMap.get("Skill_ID")))
		{
			//更新
			hzyyTeacherBiz.updateSkillScore(outSkillMap.get("Skill_ID"),doctorFlow,skillFlow,items,itemsMap,request);
		}else{
			//新增
			String skillId= PkUtil.getGUID();
			Map<String,String> skill=hzyyTeacherBiz.getSkill(skillFlow);
			hzyyTeacherBiz.insertSkillScore(doctorFlow,skillId,skillFlow,skill,items,itemsMap,request);
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping("/savePatientScore")
	public synchronized String savePatientScore(String userFlow , String doctorFlow,String patientTypeId,HttpServletRequest request, Model model) throws UnsupportedEncodingException{

		String result = "res/hzyy/teacher/success";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(patientTypeId)){
			model.addAttribute("resultId", "3225221");
			model.addAttribute("resultType", "表单类型为空");
			return result;
		}
		if(!"1".equals(patientTypeId)&&!"2".equals(patientTypeId))
		{
			model.addAttribute("resultId", "3225221");
			model.addAttribute("resultType", "表单类型错误");
			return result;
		}

		Map<String,String> cycleInfo=hzyyTeacherBiz.getCycleInfo(doctorFlow);
		model.addAttribute("cycleInfo", cycleInfo);
		//头部病人信息、底部总分数信息
		Map<String,String> outPatientMap=hzyyTeacherBiz.getOutPatient(doctorFlow);
		model.addAttribute("outPatientMap", outPatientMap);

		if(outPatientMap!=null&&!outPatientMap.isEmpty()&&StringUtil.isNotBlank(outPatientMap.get("Patient_ID")))
		{
			//更新
			hzyyTeacherBiz.updatePatientScore(outPatientMap.get("Patient_ID"),doctorFlow,request);
		}else{
			//新增
			String patientId= PkUtil.getGUID();
			hzyyTeacherBiz.insertPatientScore(doctorFlow,patientId,patientTypeId,request,userFlow);
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/schFuncList"},method={RequestMethod.GET})
	public String schFuncList(String userFlow,String doctorFlow,Model model){
		String result = "res/hzyy/teacher/schFuncList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		Map<String,String> doctor = hzyyAppBiz.readDoctor(doctorFlow);
		model.addAttribute("doctor" , doctor);
		//科室状态
		Map<String,Object> deptStatus = hzyyTeacherBiz.readDoctorDeptStatus(userFlow, doctorFlow);
		model.addAttribute("deptStatus", deptStatus);
		//出科小结
		Map<String,Object> outSecBrief = hzyyTeacherBiz.readOutSecBrief(doctorFlow);
		model.addAttribute("outSecBrief", outSecBrief);
		
		Map<String,Object> outDops = hzyyTeacherBiz.readOutDops(doctorFlow);
		model.addAttribute("outDops" , outDops);
		
		Map<String,Object> outMiniCex = hzyyTeacherBiz.readOutMiniCex(doctorFlow);
		model.addAttribute("outMiniCex" , outMiniCex);
		model.addAttribute("isNeed" , hzyyTeacherBiz.isNeedPatient(doctorFlow));

		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	@RequestMapping(value={"/categoryProgress"},method={RequestMethod.GET})
	public String categoryProgress(String userFlow,String doctorFlow,String funcTypeId , String funcFlow , Integer pageIndex,Integer pageSize,String dataCount,Model model){
		
		String result = "res/hzyy/teacher/categoryProgress";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(funcTypeId)||!("dataInputNN".equals(funcTypeId)||"dataInput1N".equals(funcTypeId) || "dataInput11".equals(funcTypeId))){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCTYPE_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCTYPE_ERROR);
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCFLOW_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCFLOW_ERROR);
			return result;
		}
		
		model.addAttribute("funcTypeId", funcTypeId);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping(value={"/dataAudit"},method={RequestMethod.GET})
	public String dataAudit(String userFlow,String studentFlow,String schStatusId ,String cysecId ,Model model){

		String result = "res/hzyy/teacher/dataAudit";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(studentFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(schStatusId)||!("Entering".equals(schStatusId)||"Exited".equals(schStatusId))){
			model.addAttribute("resultId", "040301");
			model.addAttribute("resultType", "轮转状态为空或错误");
			return result;
		}
		Map<String , String> searchMap =new HashMap<>();
		searchMap.put("doctorFlow",studentFlow);
		searchMap.put("schStatusId",schStatusId);
		searchMap.put("cySecId",cysecId);
		List<Map<String,String>> datas=hzyyTeacherBiz.getDataAudit(searchMap);
		if("Exited".equals(schStatusId))
		{
			Map<String,Map<String,String>> dataMap=new HashMap<>();
			if(datas!=null&&datas.size()>0)
			{
				for(Map<String,String> d:datas)
				{
					dataMap.put(d.get("dataTypeId"),d);
				}
			}
			datas = new ArrayList<>();
			Map<String, String> data = new HashMap<>();
			data.put("dataTypeName", "大病历");
			data.put("dataTypeId", "mr");
			data.put("notAuditNum", dataMap.get("mr")==null?"0":dataMap.get("mr").get("notAuditNum"));
			datas.add(data);
			data = new HashMap<>();
			data.put("dataTypeName", "病种");
			data.put("dataTypeId", "disease");
			data.put("notAuditNum", dataMap.get("disease")==null?"0":dataMap.get("disease").get("notAuditNum"));
			datas.add(data);
			data = new HashMap<>();
			data.put("dataTypeName", "操作技能");
			data.put("dataTypeId", "skill");
			data.put("notAuditNum", dataMap.get("skill")==null?"0":dataMap.get("skill").get("notAuditNum"));
			datas.add(data);
			data = new HashMap<>();
			data.put("dataTypeName", "手术");
			data.put("dataTypeId", "operation");
			data.put("notAuditNum", dataMap.get("operation")==null?"0":dataMap.get("operation").get("notAuditNum"));
			datas.add(data);
			data = new HashMap<>();
			data.put("dataTypeName", "门诊病历");
			data.put("dataTypeId", "disciple");
			data.put("notAuditNum", dataMap.get("disciple")==null?"0":dataMap.get("disciple").get("notAuditNum"));
			datas.add(data);

		}
		model.addAttribute("datas", datas);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/dataAuditList"},method={RequestMethod.GET})
	public String dataAuditList(String userFlow,String studentFlow,String schStatusId ,
								Integer pageIndex,Integer pageSize,String dataTypeId ,
								String cysecId ,Model model){

		String result = "res/hzyy/teacher/dataAuditList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(studentFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(schStatusId)||!("Entering".equals(schStatusId)||"Exited".equals(schStatusId))){
			model.addAttribute("resultId", "040301");
			model.addAttribute("resultType", "轮转状态为空或错误");
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
		searchMap.put("doctorFlow",studentFlow);
		searchMap.put("schStatusId",schStatusId);
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

	@RequestMapping(value={"/batchAuditData"},method={RequestMethod.GET})
	public String batchAuditData(String userFlow,String studentFlow,String isPass ,String dataTypeId ,
								String cysecId ,Model model){

		String result = "res/hzyy/teacher/success";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(studentFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(isPass)||!("Y".equals(isPass)||"N".equals(isPass))){
			model.addAttribute("resultId", "040301");
			model.addAttribute("resultType", "审核状态为空或错误");
			return result;
		}
		if(StringUtil.isEmpty(dataTypeId)||
				!("mr".equals(dataTypeId)||"disease".equals(dataTypeId)||"skill".equals(dataTypeId)
						||"operation".equals(dataTypeId)||"disciple".equals(dataTypeId))){
			model.addAttribute("resultId", "040301");
			model.addAttribute("resultType", "数据类型为空或错误");
			return result;
		}

		Map<String , Object> searchMap =new HashMap<>();
		searchMap.put("doctorFlow",studentFlow);
		searchMap.put("schStatusId","Entering");
		searchMap.put("cySecId",cysecId);
		searchMap.put("dataTypeId",dataTypeId);

		model.addAttribute("dataCount", 0);
		List<Map<String,String>> datas=null;
		//大病历
		if("mr".equals(dataTypeId)){
			datas = hzyyTeacherBiz.getAuditMrDatas(searchMap);
		}
		//病种
		if("disease".equals(dataTypeId)){
			datas = hzyyTeacherBiz.getAuditDiseaseDatas(searchMap);
		}
		//操作技能
		if("skill".equals(dataTypeId)){
			datas = hzyyTeacherBiz.getAuditSkillDatas(searchMap);
		}
		//手术
		if("operation".equals(dataTypeId)){
			datas = hzyyTeacherBiz.getAuditOperationDatas(searchMap);
		}
		//门诊病历
		if("disciple".equals(dataTypeId)){
			datas = hzyyTeacherBiz.getAuditDiscipleDatas(searchMap);
		}
		if(datas==null||datas.isEmpty())
		{
			model.addAttribute("resultId","3032011");
			model.addAttribute("resultType", "无待审核数据，请刷新列表");
			return result;
		}
		int c=hzyyTeacherBiz.batchAuditData(datas,dataTypeId,isPass);
		if(c==0)
		{
			model.addAttribute("resultId","3032011");
			model.addAttribute("resultType", "审核失败");
			return result;
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping(value={"/auditData"},method={RequestMethod.GET})
	public String auditData(String userFlow,String dataFlow,String isPass ,String dataTypeId ,Model model){

		String result = "res/hzyy/teacher/success";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(dataFlow)){
			model.addAttribute("resultId","32003200");
			model.addAttribute("resultType", "数据标识符为空");
			return result;
		}
		if(StringUtil.isEmpty(isPass)||!("Y".equals(isPass)||"N".equals(isPass))){
			model.addAttribute("resultId", "040301");
			model.addAttribute("resultType", "审核状态为空或错误");
			return result;
		}
		if(StringUtil.isEmpty(dataTypeId)||
				!("mr".equals(dataTypeId)||"disease".equals(dataTypeId)||"skill".equals(dataTypeId)
						||"operation".equals(dataTypeId)||"disciple".equals(dataTypeId))){
			model.addAttribute("resultId", "040301");
			model.addAttribute("resultType", "数据类型为空或错误");
			return result;
		}
		int c=hzyyTeacherBiz.auditData(dataFlow,dataTypeId,isPass);
		if(c==0)
		{
			model.addAttribute("resultId","3032011");
			model.addAttribute("resultType", "审核失败");
			return result;
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/dataDetail"},method={RequestMethod.GET})
	public String dataDetail(String userFlow,String dataFlow,String dataTypeId ,Model model){

		String result = "res/hzyy/teacher/dataDetail";
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

	
	@RequestMapping(value={"/dataList"},method={RequestMethod.GET})
	public String dataList(String userFlow,String doctorFlow,String funcTypeId,String funcFlow , 
			String searchData , String cataFlow , Integer pageIndex,Integer pageSize,
			String dataCount,HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException{
		if(StringUtil.isNotBlank(searchData)){
			searchData = new String(searchData.getBytes("ISO8859-1") , "UTF-8");
			Map<String , String> searchMap = (Map<String, String>) JSON.parse(searchData);
			for(String key:searchMap.keySet()){
				//System.out.println(key+":"+URLDecoder.decode(searchMap.get(key), "UTF-8"));
				searchMap.put(key, URLDecoder.decode(searchMap.get(key), "UTF-8"));
			}
			model.addAttribute("searchMap" , searchMap);
		}
		String result = "res/hzyy/teacher/dataList";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(funcTypeId)||!("dataInputNN".equals(funcTypeId)||"dataInput1N".equals(funcTypeId) || "dataInput11".equals(funcTypeId))){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCTYPE_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCTYPE_ERROR);
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCFLOW_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCFLOW_ERROR);
			return result;
		}
		if(pageIndex==null){
			pageIndex = NfyyGlobalConstant.DEFAULT_INDEX_PAGE;
		}
		if(pageSize==null){
			pageSize = NfyyGlobalConstant.DEFAULT_PAGE_SIZE;
		}

		model.addAttribute("dataCount", 0);
		//学习情况
		if("00021".equals(funcFlow)){
			List<StudyInfo> studyInfos = hzyyStudentBiz.getStudyInfos(doctorFlow, pageIndex, pageSize);
			model.addAttribute("studyInfos" , studyInfos);
			model.addAttribute("dataCount", hzyyStudentBiz.getStudyInfos(doctorFlow, 1, Integer.MAX_VALUE).size());
		}
		//病例与管床
		if("00022".equals(funcFlow)){
			List<MedicalInfo> medicalInfos = hzyyStudentBiz.getMedicalInfos(doctorFlow, pageIndex, pageSize);
			int count = hzyyStudentBiz.getMedicalInfos(doctorFlow, 1, Integer.MAX_VALUE).size();
			model.addAttribute("medicalInfos" , medicalInfos);
			model.addAttribute("dataCount" , count);
		}
		//教学活动
		if("00023".equals(funcFlow)){
			List<Map<String,Object>> activitys = hzyyTeacherBiz.getActives(userFlow, doctorFlow,pageIndex, pageSize);
			model.addAttribute("activitys" , activitys);
			int count =  hzyyTeacherBiz.getActives(userFlow, doctorFlow,1 , Integer.MAX_VALUE).size();
			model.addAttribute("dataCount" , count);
		}
		//活动参加人员确认
		if("0007".equals(funcFlow)){
			List<Map<String,Object>> activitys = hzyyTeacherBiz.getNeedConfirmActives(userFlow, doctorFlow,pageIndex, pageSize);
			model.addAttribute("activitys" , activitys);
			int count =  hzyyTeacherBiz.getActives(userFlow, doctorFlow,1 , Integer.MAX_VALUE).size();
			model.addAttribute("dataCount" , count);
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	@RequestMapping(value={"/addData"},method={RequestMethod.POST})
	public String addData(String userFlow,String doctorFlow,String funcTypeId , String funcFlow , String cataFlow,HttpServletRequest request , Model model){
		
		String result = "res/hzyy/teacher/addData";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(funcTypeId)||!("dataInputNN".equals(funcTypeId)||"dataInput1N".equals(funcTypeId) || "dataInput11".equals(funcTypeId))){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCTYPE_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCTYPE_ERROR);
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCFLOW_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCFLOW_ERROR);
			return result;
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	@RequestMapping(value={"/viewData"},method={RequestMethod.GET})
	public String viewData(String userFlow,String doctorFlow,String funcTypeId,String funcFlow , String cataFlow,String dataFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		
		String result = "res/hzyy/teacher/viewData";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(funcTypeId)||!("dataInputNN".equals(funcTypeId)||"dataInput1N".equals(funcTypeId) || "dataInput11".equals(funcTypeId))){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCTYPE_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCTYPE_ERROR);
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCFLOW_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCFLOW_ERROR);
			return result;
		}
//		if(StringUtil.isBlank(dataFlow)){
//			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DATAFLOW_NULL);
//			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DATAFLOW_NULL);
//			return result;
//		}
		
		//入科确认
        if("0001".equals(funcFlow)){
        	Map<String,Object> doctorInfo = hzyyTeacherBiz.readDoctorInfo(userFlow, doctorFlow);
    		model.addAttribute("doctorInfo", doctorInfo);
		}
        //学习情况
        if("00021".equals(funcFlow)){
        	if(StringUtil.isNotBlank(dataFlow)){
            	StudyInfo studyInfo = hzyyStudentBiz.readStudyInfo(dataFlow);
            	model.addAttribute("studyInfo" , studyInfo);
        	}
        }
        //病例与管床
        if("00022".equals(funcFlow)){
        	if(StringUtil.isNotBlank(dataFlow)){
            	MedicalInfo medicalInfo = hzyyStudentBiz.readMedicalInfo(dataFlow);
            	model.addAttribute("medicalInfo" , medicalInfo);
        	}
        }
        //教学活动
        if("00023".equals(funcFlow)){
        	Map<String,Object> activity = hzyyTeacherBiz.readActivity(dataFlow, doctorFlow);
        	model.addAttribute("activity" , activity);
        }
        //过程评价
        if("0003".equals(funcFlow)){
        	Map<String,Object> evalInfo = hzyyTeacherBiz.readEvalInfo(userFlow, doctorFlow);
    		model.addAttribute("evalInfo", evalInfo);
        }
		//出科审核
		if("0004".equals(funcFlow)){
			Map<String,String> doctor = hzyyAppBiz.readDoctor(doctorFlow);

			model.addAttribute("doctor" , doctor);

			Map<String,String> afterEva = hzyyTeacherBiz.readAfterEva(doctorFlow);
			model.addAttribute("afterEva" , afterEva);
			Map<String,String> cycleOutInfo = hzyyTeacherBiz.getCycleOutInfo(doctorFlow);
			model.addAttribute("cycleOutInfo" , cycleOutInfo);
			if(cycleOutInfo!=null&&!cycleOutInfo.isEmpty())
			{
				model.addAttribute("read" , true);
			}else{
				model.addAttribute("read" , true);
			}

		}
		//DOPS评估表
		if("0005".equals(funcFlow)){
			Map<String,Object> outDops = hzyyTeacherBiz.readOutDops(doctorFlow);
			model.addAttribute("outDops" , outDops);
			
			Map<String,Object> examInfo = hzyyTeacherBiz.readExamInfo(doctorFlow,"11");
        	model.addAttribute("examInfo" , examInfo);

            List<Map<String, Object>> assessTmp = this.hzyyStudentBiz.getExamItemsDOPS(doctorFlow);
            model.addAttribute("assessTmp" , assessTmp);

        	List<Map<String,Object>> marks = hzyyTeacherBiz.getMarks(doctorFlow,"11");
        	model.addAttribute("marks" , marks);
		}
		//Mini-CEX评估表
		if("0006".equals(funcFlow)){
			Map<String,Object> outMiniCex = hzyyTeacherBiz.readOutMiniCex(doctorFlow);
			model.addAttribute("outMiniCex" , outMiniCex);
			
			Map<String,Object> examInfo = hzyyTeacherBiz.readExamInfo(doctorFlow,"12");
        	model.addAttribute("examInfo" , examInfo);
			
        	List<Map<String, Object>> assessTmp = this.hzyyStudentBiz.getExamItemsMiniCex(doctorFlow);
        	model.addAttribute("assessTmp" , assessTmp);
        	
        	List<Map<String,Object>> marks = hzyyTeacherBiz.getMarks(doctorFlow,"12");
        	model.addAttribute("marks" , marks);
        }
		
		//活动参加人员确认
		if("0007".equals(funcFlow)){
			List<Map<String,Object>> activityUsers = hzyyTeacherBiz.getNeedConfirmActiveUsers(dataFlow);
			model.addAttribute("activityUsers" , activityUsers);
		}
		
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/AfterEvaluation"},method={RequestMethod.POST,RequestMethod.GET})
	public String AfterEvaluation(String userFlow,String doctorFlow, Model model){

		String result = "/res/hzyy/teacher/AfterEvaluation";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}

		Map<String,String> afterEva = hzyyTeacherBiz.readAfterEva(doctorFlow);
		model.addAttribute("afterEva" , afterEva);
		String isNeed= hzyyTeacherBiz.isNeedPatient(doctorFlow);
		if(afterEva!=null)
		{
			if("0".equals(String.valueOf(afterEva.get("SectionType"))))
			{
				if(StringUtil.isNotBlank(String.valueOf(afterEva.get("TheoryScore")))){
					Double TheoryScore=Double.valueOf(afterEva.get("TheoryScore"));
					BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.4).setScale(1, BigDecimal.ROUND_HALF_UP);
					afterEva.put("TheoryScore",String.valueOf(bigDecimal.doubleValue()));
				}else{
					afterEva.put("TheoryScore","0");
				}

				if("N".equals(isNeed))
				{

					if(StringUtil.isNotBlank(String.valueOf(afterEva.get("Patient_Score")))){
						Double TheoryScore=Double.valueOf(afterEva.get("Patient_Score"));
						BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0).setScale(1, BigDecimal.ROUND_HALF_UP);
						afterEva.put("Patient_Score",String.valueOf(bigDecimal.doubleValue()));
					}else{
						afterEva.put("Patient_Score","0");
					}
					if(StringUtil.isNotBlank(String.valueOf(afterEva.get("Skill_Score")))){
						Double TheoryScore=Double.valueOf(afterEva.get("Skill_Score"));
						BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.4).setScale(1, BigDecimal.ROUND_HALF_UP);
						afterEva.put("Skill_Score",String.valueOf(bigDecimal.doubleValue()));
					}else{
						afterEva.put("Skill_Score","0");
					}
				}else{
					if(StringUtil.isNotBlank(String.valueOf(afterEva.get("Patient_Score")))){
						Double TheoryScore=Double.valueOf(afterEva.get("Patient_Score"));
						BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.2).setScale(1, BigDecimal.ROUND_HALF_UP);
						afterEva.put("Patient_Score",String.valueOf(bigDecimal.doubleValue()));
					}else{
						afterEva.put("Patient_Score","0");
					}
					if(StringUtil.isNotBlank(String.valueOf(afterEva.get("Skill_Score")))){
						Double TheoryScore=Double.valueOf(afterEva.get("Skill_Score"));
						BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.2).setScale(1, BigDecimal.ROUND_HALF_UP);
						afterEva.put("Skill_Score",String.valueOf(bigDecimal.doubleValue()));
					}else{
						afterEva.put("Skill_Score","0");
					}
				}
			}
			if("1".equals(String.valueOf(afterEva.get("SectionType"))))
			{

				if(StringUtil.isNotBlank(String.valueOf(afterEva.get("TheoryScore")))){
					Double TheoryScore=Double.valueOf(afterEva.get("TheoryScore"));
					BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.5).setScale(1, BigDecimal.ROUND_HALF_UP);
					afterEva.put("TheoryScore",String.valueOf(bigDecimal.doubleValue()));
				}else{
					afterEva.put("TheoryScore","0");
				}
				if(StringUtil.isNotBlank(String.valueOf(afterEva.get("Skill_Score")))){
					Double TheoryScore=Double.valueOf(afterEva.get("Skill_Score"));
					BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.3).setScale(1, BigDecimal.ROUND_HALF_UP);
					afterEva.put("Skill_Score",String.valueOf(bigDecimal.doubleValue()));
				}else{
					afterEva.put("Skill_Score","0");
				}
			}
			if("2".equals(String.valueOf(afterEva.get("SectionType"))))
			{
				if(StringUtil.isNotBlank(String.valueOf(afterEva.get("TheoryScore")))){
					Double TheoryScore=Double.valueOf(afterEva.get("TheoryScore"));
					BigDecimal bigDecimal=new BigDecimal(Double.valueOf(TheoryScore)*0.8).setScale(1, BigDecimal.ROUND_HALF_UP);
					afterEva.put("TheoryScore",String.valueOf(bigDecimal.doubleValue()));
				}else{
					afterEva.put("TheoryScore","0");
				}
			}
		}
		int jxhd=hzyyTeacherBiz.getJxhd(doctorFlow);
		model.addAttribute("jxhd" , jxhd);
		Map<String,String> cycleOutInfo = hzyyTeacherBiz.getCycleOutInfo(doctorFlow);
		model.addAttribute("cycleOutInfo" , cycleOutInfo);
		Map<String,String> outSecBrief=hzyyTeacherBiz.getOutSecBrief(doctorFlow);
		if(outSecBrief==null||outSecBrief.isEmpty())
		{
			model.addAttribute("resultId", "2029302");
			model.addAttribute("resultType","尚未提交出科小结，无法审核出科考核表！");
			return result;
		}
		if(outSecBrief!=null&&("2".equals(String.valueOf(outSecBrief.get("VerifyState")))||"1".equals(String.valueOf(outSecBrief.get("CheckStatus")))))
		{
			model.addAttribute("read", true);
		}else{
			model.addAttribute("read", false);
		}
		if(cycleOutInfo!=null&&!cycleOutInfo.isEmpty())
		{
			model.addAttribute("haveOutInfo", true);
		}else{
			model.addAttribute("haveOutInfo", false);
		}
		model.addAttribute("isNeed" ,isNeed);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping(value={"/saveAfterEvaluation"},method={RequestMethod.POST})
	public synchronized String saveAfterEvaluation(String userFlow,String doctorFlow,HttpServletRequest request, Model model){

		String result = "/res/hzyy/teacher/success";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}

		Map<String,String> tea = hzyyAppBiz.readUser(userFlow);


		Map<String,String> outSecBrief=hzyyTeacherBiz.getOutSecBrief(doctorFlow);
		if(outSecBrief==null||outSecBrief.isEmpty())
		{
			model.addAttribute("resultId", "2029302");
			model.addAttribute("resultType","尚未提交出科小结，无法审核出科考核表！");
			return result;
		}
		if(outSecBrief!=null&&("2".equals(String.valueOf(outSecBrief.get("VerifyState")))||"1".equals(String.valueOf(outSecBrief.get("CheckStatus")))))
		{
			model.addAttribute("resultId", "2029302");
			model.addAttribute("resultType","教秘已审核，请刷新页面！");
			return result;
		}
		Map<String,String> cycleOutInfo = hzyyTeacherBiz.getCycleOutInfo(doctorFlow);
		int c=0;
		if(cycleOutInfo!=null&&!cycleOutInfo.isEmpty()&&StringUtil.isNotBlank(cycleOutInfo.get("ID")))
		{
			//更新
			c=hzyyTeacherBiz.updateAfterEvaluation(cycleOutInfo.get("ID"),doctorFlow,tea,request);
		}else{
			//新增
			c=hzyyTeacherBiz.insertAfterEvaluation(doctorFlow,tea,request);
		}
		if(c==0)
		{
			model.addAttribute("resultId", "2029302");
			model.addAttribute("resultType","保存失败！");
			return result;
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping(value={"/modData"},method={RequestMethod.PUT , RequestMethod.POST})
	public String modData(String userFlow,String doctorFlow,String funcTypeId , String funcFlow , String cataFlow,String dataFlow,HttpServletRequest request , Model model){
		
		String result = "res/hzyy/teacher/modData";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(funcTypeId)||!("dataInputNN".equals(funcTypeId)||"dataInput1N".equals(funcTypeId) || "dataInput11".equals(funcTypeId))){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCTYPE_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCTYPE_ERROR);
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCFLOW_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCFLOW_ERROR);
			return result;
		}
//		if(StringUtil.isBlank(dataFlow)){
//			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DATAFLOW_NULL);
//			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DATAFLOW_NULL);
//			return result;
//		}
		//入科确认
        if("0001".equals(funcFlow)){
        	hzyyTeacherBiz.enterDeptConfirm(userFlow, doctorFlow);
		}
        //过程评价
        if("0003".equals(funcFlow)){
        	String eval = StringUtil.defaultString(request.getParameter("eval"));
        	StringBuffer sb = new StringBuffer();
        	String [] evalArray = eval.split("\n");
        	for(int i=0;i<evalArray.length;i++){
        		String s =evalArray[i];
        		if(i==evalArray.length-1){
        			if(!s.contains(DateUtil.getDateTime("yyyy"))){
            			s = s+"     "+DateUtil.getCurrDateTime(DateUtil.defDtPtn02);
        			}
        		}
        		sb.append(s).append("\n");
        	}
        	eval = sb.toString();
        	hzyyTeacherBiz.saveEvalInfo(userFlow, doctorFlow,eval);
		}
        //出科审核
        if("0004".equals(funcFlow)){
        	hzyyTeacherBiz.saveOutSecBrief(userFlow, doctorFlow,request);
		}
        //DOPS评估表
        if("0005".equals(funcFlow)){
        	hzyyTeacherBiz.saveOutDops(userFlow, doctorFlow,request);
		}
        //Mini-CEX评估表
        if("0006".equals(funcFlow)){
        	hzyyTeacherBiz.saveOutMiniCex(userFlow, doctorFlow,request);
		}
        //活动参加人员确认
  		if("0007".equals(funcFlow)){
  			hzyyTeacherBiz.confirmActiveUsers(dataFlow,request);
  		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	
	
	@RequestMapping(value={"/deleteData"},method={RequestMethod.POST , RequestMethod.DELETE})
	public String deleteData(String userFlow,String doctorFlow,String funcTypeId,String funcFlow,String dataFlow,HttpServletRequest request , Model model){
		
		String result = "res/hzyy/student/deleteData";
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DOCTORFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DOCTORFLOW_NULL);
			return result;
		}
		if(StringUtil.isEmpty(funcTypeId)||!("dataInputNN".equals(funcTypeId)||"dataInput1N".equals(funcTypeId) || "dataInput11".equals(funcTypeId))){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCTYPE_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCTYPE_ERROR);
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_FUNCFLOW_ERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_FUNCFLOW_ERROR);
			return result;
		}
		if(StringUtil.isBlank(dataFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_DATAFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_DATAFLOW_NULL);
			return result;
		}
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}

	@RequestMapping(value={"/delFile"},method={RequestMethod.POST})
	public String delFile(String userFlow , String fileFlow, Model model){
		String result = "/res/hzyy/teacher/success";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(fileFlow)){
			model.addAttribute("resultId", "33023");
			model.addAttribute("resultType", "文件流水号为空");
			return result;
		}
		int c=hzyyTeacherBiz.delFile(fileFlow);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		return result;
	}
	@RequestMapping(value = {"/checkFile"}, method = {RequestMethod.POST})
	public String checkFile(String userFlow, String fileFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		String result="/res/hzyy/teacher/success";
		String checkResult=checkUploadCfg();
		if(StringUtil.isNotBlank(checkResult))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", checkResult);
			return result;
		}
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "01000301");
			model.addAttribute("resultType", "用户流水号为空");
			return result;
		}

		Map<String,String> fileMap=hzyyTeacherBiz.readFile(fileFlow);
		if(fileMap==null|| StringUtil.isBlank(fileMap.get("AttachPath"))|| StringUtil.isBlank(fileMap.get("AttachFileName")))
		{
			model.addAttribute("resultId", "01000304");
			model.addAttribute("resultType", "文件信息不存在，请刷新列表页面！");
			return result;
		}
		String filePath = baseDir + "/" ;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		if(fileMap!=null&& StringUtil.isNotBlank(fileMap.get("AttachPath"))&& StringUtil.isNotBlank(fileMap.get("AttachFileName")))
		{
			filePath=filePath+fileMap.get("AttachPath")+ "/"+fileMap.get("AttachFileName") ;
		}
		File f = new File(filePath);
		if (!f.exists()) {
			model.addAttribute("resultId", "01000304");
			model.addAttribute("resultType", "文件信息不存在，请联系管理员！");
			return result;
		}
		return result;
	}

	@RequestMapping(value = {"/downFile"}, method = {RequestMethod.POST,RequestMethod.GET})
	public synchronized void downFile(String userFlow, String fileFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

		String filePath = baseDir + "/" ;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		Map<String,String> fileMap=hzyyTeacherBiz.readFile(fileFlow);
		String fileName="未发现文件.png";
		if(fileMap!=null&& StringUtil.isNotBlank(fileMap.get("AttachPath"))&& StringUtil.isNotBlank(fileMap.get("AttachFileName")))
		{
			filePath=filePath+fileMap.get("AttachPath")+ "/"+fileMap.get("AttachFileName") ;
			fileName=fileMap.get("AttachName");
		}

        /*文件是否存在*/
		File downLoadFile = new File(filePath);
		byte[] data = null;
		long dataLength = 0;
		/*文件是否存在*/
		if (downLoadFile.exists()) {
			InputStream fis = new BufferedInputStream(new FileInputStream(downLoadFile));
			data = new byte[fis.available()];
			dataLength = downLoadFile.length();
			fis.read(data);
			fis.close();
		}
		fileName = new String(fileName.getBytes(), "ISO-8859-1");
		try {
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
			response.addHeader("Content-Length", "" + dataLength);
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			if (data != null) {
				outputStream.write(data);
			}
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {

		}
	}

	@RequestMapping(value={"/addFile"},method={RequestMethod.POST})
	public String addFile(FileForm form, String userFlow,
						  String isActivity,HttpServletRequest request, HttpServletResponse response, Model model){
		String result = "/res/hzyy/teacher/addFile";
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_USERFLOW_NULL);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_USERFLOW_NULL);
			return result;
		}
		if(StringUtil.isBlank(isActivity)){
			model.addAttribute("resultId", "33023");
			model.addAttribute("resultType", "isActivity为空");
			return result;
		}
		if(!"Y".equals(isActivity)&&!"N".equals(isActivity)){
			model.addAttribute("resultId", "33023");
			model.addAttribute("resultType", "isActivity只能是Y或N");
			return result;
		}
		MultipartFile uploadFile = form.getUploadFile();
		if(uploadFile==null)
		{
			model.addAttribute("resultId", "33023");
			model.addAttribute("resultType", "上传附件为空");
			return result;
		}
		String fileName = form.getFileName();
		if(StringUtil.isBlank(fileName))
		{
			model.addAttribute("resultId", "33023");
			model.addAttribute("resultType", "上传附件名称为空");
			return result;
		}
		String fileSuffx = fileName.substring( fileName.lastIndexOf(".")+1);


		List<String> yFile=new ArrayList(Arrays.asList("jpg,gif,png".split(",")));
		List<String> nFile=new ArrayList(Arrays.asList("xls,xlsx,doc,docx,jpg,gif,png".split(",")));
		if("Y".equals(isActivity))
		{
			if(!yFile.contains(fileSuffx.toLowerCase()))
			{
				model.addAttribute("resultId", "33023");
				model.addAttribute("resultType", "文件格式错误只能是jpg,gif,png");
				return result;
			}
		}
		if("N".equals(isActivity))
		{
			if(!nFile.contains(fileSuffx.toLowerCase()))
			{
				model.addAttribute("resultId", "33023");
				model.addAttribute("resultType", "文件格式错误只能是xls,xlsx,doc,docx,jpg,gif,png");
				return result;
			}
		}
		Map<String,String> user = hzyyAppBiz.readUser(userFlow);
		model.addAttribute("user" , user);
		if(user==null){
			model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SERVICEERROR);
			model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SERVICEERROR);
			return result;
		}
		String checkResult=checkUploadCfg();
		if(StringUtil.isNotBlank(checkResult))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", checkResult);
			return result;
		}
		Map<String , Object> param =new HashMap<>();

		String fileUrl = fileName.substring(0, fileName.lastIndexOf("."))+ PkUtil.getUUID()+"."+fileSuffx;
		param.put("attachName",fileName);
		param.put("attachFileName",fileUrl);
		param.put("attachPath",uploadDir);
		param.put("attachDateTime",DateUtil.getCurrDateTime());
		param.put("userFlow",userFlow);
		param.put("fileName",fileName.substring(0, fileName.lastIndexOf(".")));
		param.put("fileUrl",uploadDir+fileUrl);
		int c= hzyyTeacherBiz.saveFile(form,param);
		if(c==0)
		{
			model.addAttribute("resultId", "31703");
			model.addAttribute("resultType", "保存失败！!");
			return result;
		}
		BigDecimal fileFlow = (BigDecimal) param.get("AttachId");
		Map<String , String> file=hzyyTeacherBiz.readFile(fileFlow.toString());
		model.addAttribute("file",file);
		model.addAttribute("fileFlow",fileFlow);
		model.addAttribute("fileName",fileName);
		model.addAttribute("resultId", NfyyGlobalConstant.RESULT_CODE_SUCCESS);
		model.addAttribute("resultType", NfyyGlobalConstant.RESULT_NAME_SUCCESS);
		setUploadUrl(model);
		return result;
	}


}

