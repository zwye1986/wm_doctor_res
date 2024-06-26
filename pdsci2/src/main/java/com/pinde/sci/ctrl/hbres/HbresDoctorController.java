package com.pinde.sci.ctrl.hbres;


import com.pinde.core.util.*;
import com.pinde.sci.biz.hbres.IHbResGraduationApplyBiz;
import com.pinde.sci.biz.jsres.*;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.jsres.*;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.res.*;
import com.pinde.sci.enums.sys.CertificateTypeEnum;
import com.pinde.sci.enums.sys.*;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.model.mo.*;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author tiger
 *
 *
 */
@Controller
@RequestMapping("/hbres/doctor")
public class HbresDoctorController extends GeneralController {
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
	@Autowired
	private ISchRotationBiz rotationBiz;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
    @Autowired
	private ISchArrangeResultBiz resultBiz;
	@Autowired
	private ISchRotationGroupBiz groupBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private IHbResGraduationApplyBiz graduationApplyBiz;

	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	/**
	 * 学员考核申请展示页
	 * @param model
	 * @return
	 * @throws DocumentException
     */
	@RequestMapping(value="/getAsseApplication")
	public String getAsseApplication(Model model, String applyYear) throws DocumentException {
		//当前年
		applyYear=DateUtil.getYear();

		SysUser currUser = GlobalContext.getCurrentUser();
		model.addAttribute("currUser", currUser);

		String operUserFlow = currUser.getUserFlow();
		//个人信息
		ResDoctor resDoctor = resDoctorBiz.readDoctor(operUserFlow);
		if(resDoctor!=null) {
			SysUser sysUser = userBiz.readSysUser(operUserFlow);
			PubUserResume pubUserResume = userResumeBiz.readPubUserResume(operUserFlow);
			Map<String, String> practicingMap = new HashMap<>();
			if (pubUserResume != null) {
				String xmlContent = pubUserResume.getUserResume();
				BaseUserResumeExtInfoForm userResumeExt = null;
				if (StringUtil.isNotBlank(xmlContent)) {
					//xml转换成JavaBean
					userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, BaseUserResumeExtInfoForm.class);
					if (userResumeExt != null) {
						if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
							List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
							if (sysDictList != null && !sysDictList.isEmpty()) {
								for (SysDict dict : sysDictList) {
									if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
										if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
											userResumeExt.setGraduatedName(dict.getDictName());
										}
									}
								}

							}
						}
						model.addAttribute("userResumeExt", userResumeExt);
					}
				}
				if(userResumeExt==null)
					userResumeExt=new BaseUserResumeExtInfoForm();
				String isHavePracticingCategory=resDoctor.getPractPhysicianFlag();//是否取得执业证
				String isHaveQualificationCertificate=resDoctor.getDoctorLicenseFlag();//是否取得执业资格证
				practicingMap.put("isHavePracticingCategory",isHavePracticingCategory);
				practicingMap.put("isHaveQualificationCertificate",isHaveQualificationCertificate);
				if("Y".equals(isHaveQualificationCertificate))
				{
					practicingMap.put("graduationMaterialId","177");//报考资格材料
					practicingMap.put("graduationMaterialName","医师资格证书");//报考资格材料
					practicingMap.put("graduationMaterialCode",resDoctor.getDoctorLicenseNo());//报考资格材料编码
					practicingMap.put("graduationMaterialUri",resDoctor.getQualifiedFile());//资格证书url
					practicingMap.put("graduationMaterialTime",userResumeExt.getHaveQualificationCertificateTime());//取得时间
				}else{
					practicingMap.put("graduationMaterialId","暂无");//报考资格材料
					practicingMap.put("graduationMaterialName","暂无");//报考资格材料
					practicingMap.put("graduationMaterialCode","暂无");//报考资格材料编码
					practicingMap.put("graduationMaterialUri","暂无");//资格证书url
					practicingMap.put("graduationMaterialTime","暂无");//取得时间
				}

				if("Y".equals(isHavePracticingCategory))
				{
					practicingMap.put("graduationCategoryId",userResumeExt.getPracticingCategoryId());//执业类型
					practicingMap.put("graduationCategoryName",userResumeExt.getPracticingCategoryName());//执业类型

					practicingMap.put("graduationScopeId",userResumeExt.getPracticingScopeId());//执业范围
					practicingMap.put("graduationScopeName",userResumeExt.getPracticingScopeName());//执业范围
				}else{

					practicingMap.put("graduationCategoryId","暂无");//执业类型
					practicingMap.put("graduationCategoryName","暂无");//执业类型

					practicingMap.put("graduationScopeId","暂无");//执业范围
					practicingMap.put("graduationScopeName","暂无");//执业范围
				}
				model.addAttribute("practicingMap", practicingMap);
				//结业考核年份不空且小于当前年份的
				if (StringUtil.isNotBlank(resDoctor.getGraduationYear()) && resDoctor.getGraduationYear().compareTo(applyYear) < 0) {
					applyYear = resDoctor.getGraduationYear();
				}
				JsresGraduationApply jsresGraduationApply = graduationApplyBiz.searchByRecruitFlow(resDoctor.getDoctorFlow(), applyYear);
				//保存医师培训时间
				String endTime = "";
				//培养年限
				String sessionNumber = resDoctor.getSessionNumber();
				if (StringUtil.isNotBlank(sessionNumber)) {
					String startTime = "";
					if(StringUtil.isNotBlank(resDoctor.getInHosDate())){
						startTime = resDoctor.getInHosDate();
					}else {
						startTime = sessionNumber + "-09-01";
					}
					String trianYear = resDoctor.getTrainingYears();

					int year = 0;
					if (trianYear.equals("1")) {
						year = 1;
					}
					if (trianYear.equals("2")) {
						year = 2;
					}
					if (trianYear.equals("3")) {
						year = 3;
					}
					if (year != 0) {
						try {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date date = sdf.parse(startTime);
							//然后使用Calendar操作日期
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(date);
							calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
							calendar.add(Calendar.DATE, -1);
							//把得到的日期格式化成字符串类型的时间
							endTime = sdf.format(calendar.getTime());
						} catch (Exception e) {

						}
					}
					model.addAttribute("startDate", startTime);
					model.addAttribute("endDate", endTime);
				}
				String apply = "N";
				//判断为当前年，当前提交时间段内，且未提交的可以提交
				String currYear = DateUtil.getYear();
				String currTime = DateUtil.getCurrDateTime();
				currTime = DateUtil.transDateTime(currTime);
				String startDate = InitConfig.getSysCfg("doctor_submit_start_time");
				String endDate = InitConfig.getSysCfg("doctor_submit_end_time");
				Boolean inTime = startDate.compareTo(currTime) <= 0 && endDate.compareTo(currTime) >= 0;
				Boolean isWaitAudit = false;//是否待审核
				if (jsresGraduationApply != null && StringUtil.isNotBlank(jsresGraduationApply.getAuditStatusId())) {
					isWaitAudit = true;
				}
				if (currYear.equals(resDoctor.getGraduationYear()) && inTime && !isWaitAudit) {
					apply = "Y";
				}
				model.addAttribute("apply", apply);
				model.addAttribute("resDoctor", resDoctor);
				model.addAttribute("user", sysUser);
				model.addAttribute("jsresGraduationApply", jsresGraduationApply);
				model.addAttribute("recruitFlow", resDoctor.getDoctorFlow());
				showMaterials(model, resDoctor, applyYear, jsresGraduationApply);
			}
		}
		model.addAttribute("doctor",resDoctor);
		return "hbres/asse/doctor/asseApplication";
	}

	@RequestMapping(value="/imgUpload")
	@ResponseBody
	public Map<String,String> resRecImgUpload(String resultFlow,String fileType,MultipartFile checkFile){
		Map<String, String> map = null;
		if(checkFile!=null && checkFile.getSize() > 0){
			map=resultBiz.imgUpload(resultFlow,checkFile,fileType);
		}
		return map;
	}

	private void showMaterials(Model model, ResDoctor recruit, String applyYear, JsresGraduationApply jsresGraduationApply) throws DocumentException {
		//培训方案
		SchRotation rotation = rotationBiz.readSchRotation(recruit.getRotationFlow());
		if(rotation!=null&&recruit!=null&&StringUtil.isNotBlank(rotation.getRotationFlow())) {
			model.addAttribute("rotation", rotation);
			String doctorFlow = recruit.getDoctorFlow();
			String rotationFlow = rotation.getRotationFlow();

			Map<String,Integer> groupRowSpan=new HashMap<>();
			Map<String,Integer> deptRowSpan=new HashMap<>();

			//方案中的组
			List<SchRotationGroup> groupList = groupBiz.searchSchRotationGroup(rotationFlow);
			model.addAttribute("groupList", groupList);
			//方案中的科室
			List<SchRotationDept> deptList = rotationDeptBiz.searchSchRotationDept(rotationFlow);

			//组下面的科室
			Map<String, List<SchRotationDept>> rotationDeptMap = new HashMap<String, List<SchRotationDept>>();
			//标准科室下的记录
			Map<String, List<SchArrangeResult>> resultMap = new HashMap<String, List<SchArrangeResult>>();
			Map<String, Float> realMonthMap = new HashMap<String, Float>();
			Map<String,Object> biMap=new HashMap<>();
			Map<String, Object> avgBiMap=new HashMap<>();
			List<String> resultFlows=new ArrayList<>();
			float allMonth = 0;
			for (SchRotationDept dept : deptList) {
				List<SchRotationDept> temp = rotationDeptMap.get(dept.getGroupFlow());
				if (temp == null) {
					temp = new ArrayList<SchRotationDept>();
				}
				rotationDeptMap.put(dept.getGroupFlow(), temp);
				Integer count= groupRowSpan.get(dept.getGroupFlow());
				if(count==null)
					count=0;
				count++;
				groupRowSpan.put(dept.getGroupFlow(),count);
				temp.add(dept);

				//轮转科室
				List<SchArrangeResult> resultList = resultBiz.searchResultByGroupFlow(dept.getGroupFlow(),dept.getStandardDeptId(),doctorFlow);
				if(resultList!=null&&resultList.size()>0)
				{
					String key = dept.getGroupFlow() + dept.getStandardDeptId();
					resultMap.put(key, resultList);
					Integer t=groupRowSpan.get(dept.getGroupFlow());
					if(t==null)
						t=0;
					groupRowSpan.put(dept.getGroupFlow(),resultList.size()+t);
					deptRowSpan.put(key,resultList.size());
					for (SchArrangeResult result : resultList) {
						resultFlows.add(result.getResultFlow());
						Float month = realMonthMap.get(key);
						if (month == null) {
							month = 0f;
						}
						String realMonth = result.getSchMonth();
						if (StringUtil.isNotBlank(realMonth)) {
							Float realMonthF = Float.parseFloat(realMonth);
							month += realMonthF;
							allMonth += realMonthF;
						}
						realMonthMap.put(key, month);
					}
				}else{

					Integer t=groupRowSpan.get(dept.getGroupFlow());
					if(t==null)
						t=0;
					groupRowSpan.put(dept.getGroupFlow(),1+t);
				}
			}

			if(jsresGraduationApply==null)
			{
				//完成比例与审核比例
				List<HbresDoctorDeptDetail> details = resultBiz.hbresDoctorSchResults(resultFlows);
				if (details != null && details.size() > 0) {
					int shortYCount=0;
					double shortYCBSum=0;//完成比例
					double shortYOCBSum=0;//补填比例
					double shortYABSum=0;//审核 比例
					double avgComBi=0;//平均完成比例
					double avgOutComBi=0;//平均补填比例
					double avgAuditComBi=0;//平均审核比例
					for (HbresDoctorDeptDetail d : details) {
						d.setApplyYear(applyYear);
						biMap.put(d.getResultFlow(), d);
						if (!"-".equals(d.getCompleteBi())) {
							shortYCount++;
							shortYCBSum += StringUtil.isBlank(d.getCompleteBi()) ? 0 : "-".equals(d.getCompleteBi()) ? 0 : Double.valueOf(d.getCompleteBi());
							shortYOCBSum += StringUtil.isBlank(d.getOutCompleteBi()) ? 0 : "-".equals(d.getOutCompleteBi()) ? 0 : Double.valueOf(d.getOutCompleteBi());
							shortYABSum += StringUtil.isBlank(d.getAuditBi()) ? 0 : "-".equals(d.getAuditBi()) ? 0 : Double.valueOf(d.getAuditBi());
						}
					}
					//平均完成比例与平均审核比例
					if ((shortYCount) != 0) {
						avgComBi = new BigDecimal(shortYCBSum / shortYCount).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
						avgOutComBi = new BigDecimal(shortYOCBSum / shortYCount).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
						avgAuditComBi = new BigDecimal(shortYABSum / shortYCount).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
					}
					avgBiMap.put("avgComplete",avgComBi);
					avgBiMap.put("avgOutComplete",avgOutComBi);
					avgBiMap.put("avgAudit",avgAuditComBi);
				}
			}
			model.addAttribute("allMonth", allMonth);
			if(jsresGraduationApply!=null) {
				//完成比例与审核比例
				List<HbresDoctorDeptDetail> details = resultBiz.hbresDoctorDeptDetails(doctorFlow, applyYear);
				if (details != null && details.size() > 0) {
					for (HbresDoctorDeptDetail d : details) {
						biMap.put(d.getResultFlow(), d);
					}
				}
				//平均完成比例与平均审核比例
				avgBiMap = resultBiz.doctorDeptAvgWorkDetail(recruit.getDoctorFlow(), applyYear);
			}
			model.addAttribute("avgBiMap", avgBiMap);
			model.addAttribute("biMap", biMap);//各科室比例
			model.addAttribute("rotationDeptMap", rotationDeptMap);


			List<PubFile> evaluationFiles=fileBiz.findFileByTypeFlows("AfterEvaluationFile",resultFlows);
			Map<String,PubFile>evaluationFileMap=new HashMap<>();
			if(evaluationFiles!=null)
			{
				for(PubFile pubFile:evaluationFiles)
				{
					evaluationFileMap.put(pubFile.getProductFlow(),pubFile);
				}
			}
			List<PubFile> summaryFiles=fileBiz.findFileByTypeFlows("AfterSummaryFile",resultFlows);
			Map<String,PubFile> summaryFileMap=new HashMap<>();
			if(summaryFiles!=null)
			{
				for(PubFile pubFile:summaryFiles)
				{
					summaryFileMap.put(pubFile.getProductFlow(),pubFile);
				}
			}

			model.addAttribute("resultMap", resultMap);
			model.addAttribute("evaluationFileMap", evaluationFileMap);
			model.addAttribute("summaryFileMap", summaryFileMap);
			model.addAttribute("groupRowSpan", groupRowSpan);
			model.addAttribute("deptRowSpan", deptRowSpan);
			model.addAttribute("realMonthMap", realMonthMap);

		}
	}
	@RequestMapping(value="/saveRegisteManua")
	@ResponseBody
	public String saveRegisteManua(String registeManua) throws DocumentException {
		SysUser currentUser  = GlobalContext.getCurrentUser();
		PubUserResume pubUserResume = userResumeBiz.readPubUserResume(currentUser.getUserFlow());
		if(pubUserResume != null){
			//旧的数据中xmlContent没有registeManua节点
			String xmlContent =  pubUserResume.getUserResume();
			if(StringUtil.isNotBlank(xmlContent)){
				//xml转换成JavaBean拓展类
				BaseUserResumeExtInfoForm  userResumeExt=null;
				userResumeExt=userResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class);
				if(userResumeExt!=null){
					userResumeExt.setRegisteManua(registeManua);
					String newXmlContent = JaxbUtil.convertToXml(userResumeExt);
					pubUserResume.setUserResume(newXmlContent);
					userResumeBiz.savePubUserResume(currentUser, pubUserResume);
				}
			}
			return GlobalConstant.OPRE_SUCCESSED;
		}else {
			return "请到培训信息填写个人基本信息！";
		}
	}
	/**
	 * 考核申请
	 * @param recruitFlow
	 * @param doctorFlow
     * @return
     */
	@RequestMapping(value={"/asseApply"})
	@ResponseBody
	 public synchronized String asseApply(String recruitFlow, String doctorFlow,String applyYear) throws DocumentException {

		ResDoctor resDoctor=resDoctorBiz.readDoctor(recruitFlow);
		if(resDoctor==null)
		{
			return "无医师信息，无法提交申请！";
		}
		if(!applyYear.equals(resDoctor.getGraduationYear()))
		{
			return "结业考核年份不是当前年，无法申请！";
		}
		JsresGraduationApply jsresGraduationApply = graduationApplyBiz.searchByRecruitFlow(recruitFlow, applyYear);
		if(jsresGraduationApply == null){
			PubUserResume pubUserResume = userResumeBiz.readPubUserResume(resDoctor.getDoctorFlow());
			Map<String,String> practicingMap=new HashMap<>();

			if (pubUserResume != null) {
				String xmlContent = pubUserResume.getUserResume();
				if (StringUtil.isNotBlank(xmlContent)) {
					//xml转换成JavaBean
					BaseUserResumeExtInfoForm userResumeExt = null;
					userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, BaseUserResumeExtInfoForm.class);

					if (StringUtil.isNotBlank(xmlContent)) {
						//xml转换成JavaBean
						userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, BaseUserResumeExtInfoForm.class);
						if (userResumeExt != null) {
							if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
								List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
								if (sysDictList != null && !sysDictList.isEmpty()) {
									for (SysDict dict : sysDictList) {
										if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
											if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
												userResumeExt.setGraduatedName(dict.getDictName());
											}
										}
									}

								}
							}
						}
					}
					if(userResumeExt==null)
						userResumeExt=new BaseUserResumeExtInfoForm();
					String isHavePracticingCategory=resDoctor.getPractPhysicianFlag();//是否取得执业证
					String isHaveQualificationCertificate=resDoctor.getDoctorLicenseFlag();//是否取得执业资格证
					practicingMap.put("isHavePracticingCategory",isHavePracticingCategory);
					practicingMap.put("isHaveQualificationCertificate",isHaveQualificationCertificate);
					if("Y".equals(isHaveQualificationCertificate))
					{
						practicingMap.put("graduationMaterialId","177");//报考资格材料
						practicingMap.put("graduationMaterialName","医师资格证书");//报考资格材料
						practicingMap.put("graduationMaterialCode",resDoctor.getDoctorLicenseNo());//报考资格材料编码
						practicingMap.put("graduationMaterialUri",resDoctor.getQualifiedFile());//资格证书url
						practicingMap.put("graduationMaterialTime",userResumeExt.getHaveQualificationCertificateTime());//取得时间
					}else{
						practicingMap.put("graduationMaterialId","暂无");//报考资格材料
						practicingMap.put("graduationMaterialName","暂无");//报考资格材料
						practicingMap.put("graduationMaterialCode","暂无");//报考资格材料编码
						practicingMap.put("graduationMaterialUri","暂无");//资格证书url
						practicingMap.put("graduationMaterialTime","暂无");//取得时间
					}

					if("Y".equals(isHavePracticingCategory))
					{
						practicingMap.put("graduationCategoryId",userResumeExt.getPracticingCategoryId());//执业类型
						practicingMap.put("graduationCategoryName",userResumeExt.getPracticingCategoryName());//执业类型

						practicingMap.put("graduationScopeId",userResumeExt.getPracticingScopeId());//执业范围
						practicingMap.put("graduationScopeName",userResumeExt.getPracticingScopeName());//执业范围
					}else{

						practicingMap.put("graduationCategoryId","暂无");//执业类型
						practicingMap.put("graduationCategoryName","暂无");//执业类型

						practicingMap.put("graduationScopeId","暂无");//执业范围
						practicingMap.put("graduationScopeName","暂无");//执业范围
					}
				}
			}
			if(practicingMap.size()==0)
			{
				return "请先完善个人信息后，再申请结业！";
			}

			//培训记录
			//培训方案
			SchRotation rotation=rotationBiz.readSchRotation(resDoctor.getRotationFlow());
			jsresGraduationApply = new JsresGraduationApply();
			jsresGraduationApply.setRecruitFlow(recruitFlow);
			List<String> resultFlows=new ArrayList<>();
			if(rotation != null) {
				jsresGraduationApply.setRotationFlow(rotation.getRotationFlow());
				jsresGraduationApply.setRotationName(rotation.getRotationName());
				//方案中的科室
				List<SchRotationDept> deptList = rotationDeptBiz.searchSchRotationDept(rotation.getRotationFlow());
				for (SchRotationDept dept : deptList) {
					//轮转科室
					List<SchArrangeResult> resultList = resultBiz.searchResultByGroupFlow(dept.getGroupFlow(),dept.getStandardDeptId(),doctorFlow);
					for (SchArrangeResult result : resultList) {
						resultFlows.add(result.getResultFlow());
					}
				}
			}
			jsresGraduationApply.setApplyYear(applyYear);
			jsresGraduationApply.setAuditStatusId(JsResAuditStatusEnum.Auditing.getId());
			jsresGraduationApply.setAuditStatusName(JsResAuditStatusEnum.Auditing.getName());

			int i = graduationApplyBiz.editGraduationApply2(jsresGraduationApply,recruitFlow,resDoctor.getDoctorFlow(),applyYear,practicingMap,resultFlows);

			return i+"";
		}else{
			return "-1";
		}
	}

	@RequestMapping(value="/printDoc")
	public String printDoc(Model model,String recruitFlow, String doctorFlow, String applyYear) throws DocumentException {

		SysUser currUser = GlobalContext.getCurrentUser();
		model.addAttribute("currUser", currUser);

		String operUserFlow = currUser.getUserFlow();
		//个人信息
		ResDoctor resDoctor = resDoctorBiz.readDoctor(doctorFlow);
		if(resDoctor!=null) {
			SysUser sysUser = userBiz.readSysUser(doctorFlow);
			PubUserResume pubUserResume = userResumeBiz.readPubUserResume(doctorFlow);
			Map<String, String> practicingMap = new HashMap<>();
			if (pubUserResume != null) {
				String xmlContent = pubUserResume.getUserResume();
				BaseUserResumeExtInfoForm userResumeExt = null;
				if (StringUtil.isNotBlank(xmlContent)) {
					//xml转换成JavaBean
					userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, BaseUserResumeExtInfoForm.class);
					if (userResumeExt != null) {
						if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
							List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
							if (sysDictList != null && !sysDictList.isEmpty()) {
								for (SysDict dict : sysDictList) {
									if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
										if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
											userResumeExt.setGraduatedName(dict.getDictName());
										}
									}
								}

							}
						}
						model.addAttribute("userResumeExt", userResumeExt);
					}
				}
				if(userResumeExt==null)
					userResumeExt=new BaseUserResumeExtInfoForm();
				String isHavePracticingCategory=resDoctor.getPractPhysicianFlag();//是否取得执业证
				String isHaveQualificationCertificate=resDoctor.getDoctorLicenseFlag();//是否取得执业资格证
				practicingMap.put("isHavePracticingCategory",isHavePracticingCategory);
				practicingMap.put("isHaveQualificationCertificate",isHaveQualificationCertificate);
				if("Y".equals(isHaveQualificationCertificate))
				{
					practicingMap.put("graduationMaterialId","177");//报考资格材料
					practicingMap.put("graduationMaterialName","医师资格证书");//报考资格材料
					practicingMap.put("graduationMaterialCode",resDoctor.getDoctorLicenseNo());//报考资格材料编码
					practicingMap.put("graduationMaterialUri",resDoctor.getQualifiedFile());//资格证书url
					practicingMap.put("graduationMaterialTime",userResumeExt.getHaveQualificationCertificateTime());//取得时间
				}else{
					practicingMap.put("graduationMaterialId","暂无");//报考资格材料
					practicingMap.put("graduationMaterialName","暂无");//报考资格材料
					practicingMap.put("graduationMaterialCode","暂无");//报考资格材料编码
					practicingMap.put("graduationMaterialUri","暂无");//资格证书url
					practicingMap.put("graduationMaterialTime","暂无");//取得时间
				}

				if("Y".equals(isHavePracticingCategory))
				{
					practicingMap.put("graduationCategoryId",userResumeExt.getPracticingCategoryId());//执业类型
					practicingMap.put("graduationCategoryName",userResumeExt.getPracticingCategoryName());//执业类型

					practicingMap.put("graduationScopeId",userResumeExt.getPracticingScopeId());//执业范围
					practicingMap.put("graduationScopeName",userResumeExt.getPracticingScopeName());//执业范围
				}else{

					practicingMap.put("graduationCategoryId","暂无");//执业类型
					practicingMap.put("graduationCategoryName","暂无");//执业类型

					practicingMap.put("graduationScopeId","暂无");//执业范围
					practicingMap.put("graduationScopeName","暂无");//执业范围
				}
				model.addAttribute("practicingMap", practicingMap);
				//结业考核年份不空且小于当前年份的
				if (StringUtil.isNotBlank(resDoctor.getGraduationYear()) && resDoctor.getGraduationYear().compareTo(applyYear) < 0) {
					applyYear = resDoctor.getGraduationYear();
				}
				JsresGraduationApply jsresGraduationApply = graduationApplyBiz.searchByRecruitFlow(resDoctor.getDoctorFlow(), applyYear);
				//保存医师培训时间
				String endTime = "";
				//培养年限
				String sessionNumber = resDoctor.getSessionNumber();
				if (StringUtil.isNotBlank(sessionNumber)) {
					String startTime = "";
					if(StringUtil.isNotBlank(resDoctor.getInHosDate())){
						startTime = resDoctor.getInHosDate();
					}else {
						startTime = sessionNumber + "-09-01";
					}
					String trianYear = resDoctor.getTrainingYears();

					int year = 0;
					if (trianYear.equals("1")) {
						year = 1;
					}
					if (trianYear.equals("2")) {
						year = 2;
					}
					if (trianYear.equals("3")) {
						year = 3;
					}
					if (year != 0) {
						try {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date date = sdf.parse(startTime);
							//然后使用Calendar操作日期
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(date);
							calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
							calendar.add(Calendar.DATE, -1);
							//把得到的日期格式化成字符串类型的时间
							endTime = sdf.format(calendar.getTime());
						} catch (Exception e) {

						}
					}
					model.addAttribute("startDate", startTime);
					model.addAttribute("endDate", endTime);
				}
				String apply = "N";
				//判断为当前年，当前提交时间段内，且未提交的可以提交
				String currYear = DateUtil.getYear();
				String currTime = DateUtil.getCurrDateTime();
				currTime = DateUtil.transDateTime(currTime);
				String startDate = InitConfig.getSysCfg("doctor_submit_start_time");
				String endDate = InitConfig.getSysCfg("doctor_submit_end_time");
				Boolean inTime = startDate.compareTo(currTime) <= 0 && endDate.compareTo(currTime) >= 0;
				Boolean isWaitAudit = false;//是否待审核
				if (jsresGraduationApply != null && StringUtil.isNotBlank(jsresGraduationApply.getAuditStatusId())) {
					isWaitAudit = true;
				}
				if (currYear.equals(resDoctor.getGraduationYear()) && inTime && !isWaitAudit) {
					apply = "Y";
				}
				model.addAttribute("apply", apply);
				model.addAttribute("resDoctor", resDoctor);
				model.addAttribute("user", sysUser);
				model.addAttribute("jsresGraduationApply", jsresGraduationApply);
				model.addAttribute("recruitFlow", resDoctor.getDoctorFlow());
				showMaterials(model, resDoctor, applyYear, jsresGraduationApply);
			}
		}
		model.addAttribute("doctor",resDoctor);
		return "hbres/asse/hospital/printApplication";
	}

	@RequestMapping(value="/applyDownload")
	public String applyDownload(HttpServletRequest request, HttpServletResponse response,String doctorFlow,String applyYear) throws Exception {
		//定义数据容器
		Map<String, Object> dataMap = new HashMap<String, Object>();

		//获取当前用户
		SysUser currentUser = userBiz.readSysUser(doctorFlow);

		//新建word模板
		WordprocessingMLPackage temeplete = new WordprocessingMLPackage();

		//context获取路径
		ServletContext context =  request.getServletContext();

		//水印
		String watermark = "";

		//文件名称
		String name="";

		//个人信息
		ResDoctor resDoctor = resDoctorBiz.readDoctor(doctorFlow);

		//培训方案
		SchRotation rotation=rotationBiz.readSchRotation(resDoctor.getRotationFlow());
		PubUserResume pubUserResume = userResumeBiz.readPubUserResume(doctorFlow);
		BaseUserResumeExtInfoForm  userResumeExt=null;
		if(pubUserResume != null){
			String xmlContent =  pubUserResume.getUserResume();
			if(StringUtil.isNotBlank(xmlContent)){
				//xml转换成JavaBean
				userResumeExt=userResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class);
//				BaseUserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, BaseUserResumeExtInfoForm.class);
				if(userResumeExt!=null){
					if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
						List<SysDict> sysDictList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
						if(sysDictList!=null && !sysDictList.isEmpty()){
							for(SysDict dict:sysDictList){
								if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
									if(dict.getDictId().equals(userResumeExt.getGraduatedId())){
										userResumeExt.setGraduatedName(dict.getDictName());
									}
								}
							}

						}
					}
				}
			}
		}
		JsresGraduationApply jsresGraduationApply = graduationApplyBiz.searchByRecruitFlow(doctorFlow,applyYear);
		//保存医师培训时间
		String startTime = "";
		String endTime = "";
		//培养年限
		String sessionNumber = resDoctor.getSessionNumber();
		if (StringUtil.isNotBlank(sessionNumber)) {
			if(StringUtil.isNotBlank(resDoctor.getInHosDate())){
				startTime = resDoctor.getInHosDate();
			}else {
				startTime = sessionNumber + "-09-01";
			}
			String trianYear = resDoctor.getTrainingYears();

			int year = 0;
			if (trianYear.equals("1")) {
				year = 1;
			}
			if (trianYear.equals("2")) {
				year = 2;
			}
			if (trianYear.equals("3")) {
				year = 3;
			}
			if (year != 0) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = sdf.parse(startTime);
					//然后使用Calendar操作日期
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
					calendar.add(Calendar.DATE, -1);
					//把得到的日期格式化成字符串类型的时间
					endTime = sdf.format(calendar.getTime());
				} catch (Exception e) {

				}
			}
		}
		float allMonth=0;
		//方案中的科室
		List<SchRotationDept> deptList = rotationDeptBiz.searchSchRotationDept(rotation.getRotationFlow());
		for (SchRotationDept dept : deptList) {
			String reductionKey = dept.getGroupFlow() + dept.getStandardDeptId();
			//轮转科室
			List<SchArrangeResult> resultList = resultBiz.searchResultByGroupFlow(dept.getGroupFlow(),dept.getStandardDeptId(),doctorFlow);
			if(resultList!=null&&resultList.size()>0)
			{
				for (SchArrangeResult result : resultList) {
					String realMonth = result.getSchMonth();
					if (StringUtil.isNotBlank(realMonth)) {
						Float realMonthF = Float.parseFloat(realMonth);
						allMonth += realMonthF;
					}
				}
			}

		}
		dataMap.put("userName",currentUser.getUserName());//姓名
		String sexName = UserSexEnum.getNameById(currentUser.getSexId());
		dataMap.put("sexName",sexName);//性别
		String cretTypeName = CertificateTypeEnum.getNameById(currentUser.getCretTypeId());
		dataMap.put("cretTypeName",cretTypeName);//证件类型
		dataMap.put("idNum",currentUser.getIdNo());//证件号
		dataMap.put("orgName",resDoctor.getOrgName());//培训基地
		String speName=resDoctor.getTrainingSpeName();
		dataMap.put("speName",speName);//培训专业
		dataMap.put("sessionNumber",resDoctor.getSessionNumber());//入培年级
		dataMap.put("graduationYear",resDoctor.getGraduationYear());//结业考核年份
		String degreeName = "";
		if(StringUtil.isNotBlank(userResumeExt.getDoctorDegreeName())){
			degreeName = userResumeExt.getDoctorDegreeName();
		}else if(StringUtil.isNotBlank(userResumeExt.getMasterDegreeName())){
			degreeName = userResumeExt.getMasterDegreeName();
		}else {
			degreeName = userResumeExt.getDegreeName();
		}
		dataMap.put("degreeName",degreeName);//学位
		dataMap.put("userPhone",currentUser.getUserPhone());//联系方式
		dataMap.put("educationName",currentUser.getEducationName());//学历
		dataMap.put("collegeCertificateNo",userResumeExt.getCertificateCode());//毕业证书编号
		String trainYear = "";
		if("1".equals(resDoctor.getTrainingYears())){
			trainYear = "一年";
		}
		if("2".equals(resDoctor.getTrainingYears())){
			trainYear = "两年";
		}
		if("3".equals(resDoctor.getTrainingYears())){
			trainYear = "三年";
		}
		dataMap.put("trainYear",trainYear);//培训年限
		dataMap.put("startDate",startTime);//培训起止日期
		dataMap.put("endDate",endTime);
		dataMap.put("qualifiName",jsresGraduationApply.getGraduationMaterialName());//执业资格材料
		String aqualifiNo = "";
			aqualifiNo = jsresGraduationApply.getGraduationMaterialCode();
		dataMap.put("qualifiNo",aqualifiNo);//执业资格材料编号
		String qualifiTime = "";
		qualifiTime = jsresGraduationApply.getGraduationMaterialTime();
		dataMap.put("qualifiTime",qualifiTime);//执业资格材料编号
		String practName = "";
			practName = jsresGraduationApply.getGraduationCategoryName();
		dataMap.put("practName",practName);//执业类型
		String practScope = "";
			practScope = jsresGraduationApply.getGraduationScopeName();
		dataMap.put("practScope",practScope);//执业范围
		String registeManua = "";
		if((GlobalConstant.PASS+"").equals(userResumeExt.getRegisteManua())){
			registeManua = "已完成";
		}
		if((GlobalConstant.UNPASS+"").equals(userResumeExt.getRegisteManua())){
			registeManua = "未完成";
		}
		dataMap.put("registeManua",registeManua);//培训登记手册完成情况
		if(StringUtil.isNotBlank(currentUser.getUserHeadImg())) {
			String headImg = InitConfig.getSysCfg("upload_base_url") + "/" + currentUser.getUserHeadImg();
			headImg = "<img  src='" + headImg + "' width='60' height='100'  alt='证件照'/>";
			dataMap.put("headImg", headImg);//学员照片
		}else{
			String strBackUrl = "http://" + request.getServerName() //服务器地址
					+ ":"		+ request.getServerPort();
			String getPath = strBackUrl+"/pdsci/css/skin/up-pic.jpg";
			dataMap.put("headImg", "<img src='" + getPath + "' width='60' height='100'  alt='证件照'/>");//学员照片
		}
		String path = "";//模板
		path = "/jsp/hbres/asse/isGeneral.docx";
		name = "湖北省住院医师规范化培训（西医）理论省统考资格审核情况表.docx";
		temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
		if(temeplete!=null){
			response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
			response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			ServletOutputStream out = response.getOutputStream ();
			(new SaveToZipFile (temeplete)).save (out);
			out.flush ();
		}
		return null;
	}


	@RequestMapping(value = "/doctorRecruit/catalogue/detail")
	public String detail(String processFlow,String recTypeId,String f,Model model)
	{
		model.addAttribute("processFlow",processFlow);
		model.addAttribute("recTypeId",recTypeId);
		model.addAttribute("f",f);
		if(StringUtil.isBlank(processFlow))
		{
			return "hbres/asse/cycle/404";
		}
		ResDoctorSchProcess resDoctorSchProcess = processBiz.read(processFlow);
		if(resDoctorSchProcess!=null) {
			String doctorFlow = resDoctorSchProcess.getUserFlow();
			if(StringUtil.isBlank(f)||f.equals("1")) {
				if (StringUtil.isBlank(recTypeId)) {
					return "hbres/asse/cycle/registrationRecord";
				}
				Map<String, Map<String, Object>> resRecMap = new HashMap<String, Map<String, Object>>();
				List<Map<String, String>> titleMap = new ArrayList<Map<String, String>>();
				List<ResRec> resRecList = resRecBiz.searchResRecWithBLOBs(recTypeId, processFlow, doctorFlow);
				if (resRecList != null && !resRecList.isEmpty()) {
					Map<String, Object> contentMap = null;
					ResRec rec = resRecList.get(0);
					titleMap = resRecBiz.parseTitle(rec.getRecForm(), rec.getRecTypeId(), rec.getRecVersion());
					for (ResRec r : resRecList) {
						if (titleMap != null && !titleMap.isEmpty()) {
							contentMap = new HashMap<String, Object>();
							contentMap = resRecBiz.parseContent(r.getRecContent());
							String key = r.getRecFlow();
							resRecMap.put(key, contentMap);
						}

					}
				}
				model.addAttribute("titleMap", titleMap);
				model.addAttribute("resRecMap", resRecMap);
				model.addAttribute("resRecList", resRecList);
				return "hbres/asse/cycle/registrationRecord";

			}else if(f.equals("2")){
				ResDoctor resDoctor=resDoctorBiz.findByFlow(doctorFlow);
				model.addAttribute("doctor", resDoctor);
				model.addAttribute("doctorSchProcess", resDoctorSchProcess);
				model.addAttribute("currRegProcess", resDoctorSchProcess);
				String rotationFlow=resDoctor.getRotationFlow();
//				List<ResRec> resRecList = resRecBiz.searchResRecWithBLOBs(recTypeId, processFlow, doctorFlow);
				String medicineTypeId="";

				SysUser operUser  = userBiz.readSysUser(doctorFlow);
				if(operUser!=null)
					medicineTypeId=operUser.getMedicineTypeId();

				List<ResSchProcessExpress> resRecList = expressBiz.searchResRecExpressWithBLOBs(recTypeId, processFlow);

				if (resRecList != null && !resRecList.isEmpty()) {
					Map<String, Object> contentMap = null;
					ResSchProcessExpress rec = resRecList.get(0);
					String currVer = null;
					String recForm = null;

					Map<String,Object> formDataMap = null;
					if(rec!=null){
						model.addAttribute("rec",rec);
						currVer = rec.getRecVersion();
						medicineTypeId = rec.getMedicineType();
						String recContent = rec.getRecContent();
						recForm = rec.getRecForm();
						formDataMap = resRecBiz.parseRecContent(recContent);
						model.addAttribute("formDataMap", formDataMap);
					}
					model.addAttribute("formFileName", recTypeId);
					SchRotationDept dept=rotationDeptBiz.readStandardRotationDept(resDoctorSchProcess.getSchResultFlow());
					String type="";
					String recordFlow="";
					if(dept!=null)
					{
						if(JszyTCMPracticEnum.BasicPractice.getId().equals(dept.getPracticOrTheory()))
						{
							type="B";
						}
						recordFlow=dept.getRecordFlow();
					}
					String jspPath = resRecBiz.getFormPath(rotationFlow,recTypeId,currVer,recForm, type,medicineTypeId,recordFlow);
					model.addAttribute("jspPath", jspPath);
					return jspPath;
				}
				return "hbres/asse/cycle/404";
			}
		}
		return "hbres/asse/cycle/404";
	}

	/**
	 * 轮转科室培训详情
	 */
	@RequestMapping(value="/doctorRecruit/catalogue",method={RequestMethod.GET,RequestMethod.POST})
	public String catalogue(String processFlow,String recTypeId,String f,Model model,String resultFlow,String userFlow) throws Exception{
		SchRotationDept schRotationDept = rotationDeptBiz.readStandardRotationDept(resultFlow);
		model.addAttribute("typeId",schRotationDept.getPracticOrTheory());
		model.addAttribute("processFlow",processFlow);
		model.addAttribute("recTypeId",recTypeId);
		model.addAttribute("f",f);
		SysUser user=userBiz.readSysUser(userFlow);;
		model.addAttribute("user",user);
		return "hbres/asse/cycle/catalogue";
	}
}
