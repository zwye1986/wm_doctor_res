package com.pinde.sci.ctrl.inx;

import com.alibaba.fastjson.JSON;
import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.AfterRecTypeEnum;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.jsres.IJsResGraduationApplyBiz;
import com.pinde.sci.biz.jsres.IResTestConfigBiz;
import com.pinde.sci.biz.jsres.ISchRotationDeptAfterBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchRotationBiz;
import com.pinde.sci.biz.sch.ISchRotationDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.form.jsres.UserResumeExtInfoForm;
import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResDoctorSchProcess;
import com.pinde.core.model.ResJointOrg;
import com.pinde.core.model.ResTestConfig;
import com.pinde.core.model.SchArrangeResult;
import com.pinde.core.model.SchRotation;
import com.pinde.core.model.SchRotationDept;
import com.pinde.core.model.SchRotationDeptAfterWithBLOBs;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author tiger
 *
 */
@Controller
@RequestMapping("/jsres/temp")
public class JsResTempController extends GeneralController{
    private static Logger logger = LoggerFactory.getLogger(JsResTempController.class);

	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private ISchRotationDeptAfterBiz afterBiz;
	@Autowired
	private IJsResGraduationApplyBiz jsresGraduationApplyBiz;
	@Autowired
	private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private ISchRotationBiz rotationBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private IResTestConfigBiz resTestConfigBiz;
	@Autowired
	private IOrgBiz sysOrgBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;

	@RequestMapping(value={"/selectAfter"})
	@ResponseBody
	public String selectAfter(String applyYear) throws DocumentException {
		if(StringUtil.isBlank(applyYear))
		{
			return "请选择申请年份";
		}
		//出科考核表
//		List<ResRec> recs = resRecBiz.getAllAfterSummary(AfterRecTypeEnum.AfterSummary.getId());
		List<ResSchProcessExpress> recs = expressBiz.getAllAfterSummary(AfterRecTypeEnum.AfterSummary.getId());
		List<SchRotationDeptAfterWithBLOBs> afters=afterBiz.getAllByApplyYear(applyYear);
		Map<String,SchRotationDeptAfterWithBLOBs> map=new HashMap();
		if(afters!=null&&afters.size()>0)
		{
			for(SchRotationDeptAfterWithBLOBs a:afters)
			{
				map.put(a.getSchRotationDeptFlow()+a.getDoctorFlow(),a);
			}
		}
		if(recs!=null&&recs.size()>0)
		{
			for(ResSchProcessExpress rec:recs)
			{
				SchRotationDeptAfterWithBLOBs after=map.get(rec.getSchRotationDeptFlow()+rec.getOperUserFlow());
				if(after==null)
					after=new SchRotationDeptAfterWithBLOBs();

				if(StringUtil.isBlank(rec.getModifyTime())||!rec.getModifyTime().equals(after.getModfiyTime()))
				{
					String content = null == rec ? "" : rec.getRecContent();
					if (StringUtil.isNotBlank(content)) {
						Document doc = DocumentHelper.parseText(content);
						Element root = doc.getRootElement();
						List<Element> imageEles = root.elements();
						if (imageEles != null && imageEles.size() > 0) {
							StringBuffer imageUrls=new StringBuffer();
							StringBuffer thumbUrls=new StringBuffer();
							for (Element image : imageEles) {
								Element imageUrl = image.element("imageUrl");
								if(imageUrl!=null) {
									String url=imageUrl.getTextTrim().replace("http://58.213.112.250:65486", "http://js.ezhupei.com")
											.replace("http://116.62.70.138", "http://js.ezhupei.com");
									imageUrls.append( url+ ",");
									imageUrl.setText(url);
								}
								Element thumbUrl = image.element("thumbUrl");
								if(thumbUrl!=null) {
									String url = thumbUrl.getTextTrim().replace("http://58.213.112.250:65486", "http://js.ezhupei.com")
											.replace("http://116.62.70.138", "http://js.ezhupei.com");
									thumbUrls.append(url + ",");
									thumbUrl.setText(url);
								}
							}
							if(StringUtil.isNotBlank(imageUrls.toString()))
								after.setImageUrls(imageUrls.substring(0,imageUrls.length()-1));
							else
								after.setImageUrls("");

							if(StringUtil.isNotBlank(thumbUrls.toString()))
								after.setThumbUrls(thumbUrls.substring(0,thumbUrls.length()-1));
							else
								after.setThumbUrls("");
						}
					}
					after.setApplyYear(applyYear);
					after.setRecordStatus(rec.getRecordStatus());
					after.setModfiyTime("AUTO-ADD");
					after.setSchRotationDeptFlow(rec.getSchRotationDeptFlow());
					after.setDoctorFlow(rec.getOperUserFlow());
					afterBiz.edit(after);
				}
			}
		}else if(afters!=null&&afters.size()>0)
		{
			//
			afterBiz.updateNAllInfo(applyYear);
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value={"/updateResult"})
	@ResponseBody
	public String updateResult() throws DocumentException {
		List<ResSchProcessExpress> recs = expressBiz.getAllAfterSummary(AfterRecTypeEnum.AfterSummary.getId());
		if(recs!=null&&recs.size()>0)
		{
			for(ResSchProcessExpress rec:recs)
			{
				if(StringUtil.isNotBlank(rec.getSchRotationDeptFlow())) {
                    String have_after_pic = com.pinde.core.common.GlobalConstant.FLAG_N;
					String content = null == rec ? "" : rec.getRecContent();
					if (StringUtil.isNotBlank(content)) {
						Document doc = DocumentHelper.parseText(content);
						Element root = doc.getRootElement();
						List<Element> imageEles = root.elements();
						if (imageEles != null && imageEles.size() > 0) {
                            have_after_pic = com.pinde.core.common.GlobalConstant.FLAG_Y;
						}
					}
					afterBiz.updateResultAfterPic(have_after_pic, rec.getSchRotationDeptFlow(),rec.getOperUserFlow());
				}
			}
		}
		afterBiz.updateResultAfterPicNotHaveRec();
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value={"/batchOscaSubmit"})
	@ResponseBody
	public String batchOscaSubmit(String orgFlow) throws DocumentException {
		if(StringUtil.isBlank(orgFlow))
		{
			SysUser sysUser = GlobalContext.getCurrentUser();
			if(sysUser != null){
				orgFlow = sysUser.getOrgFlow();
			}
			if(StringUtil.isBlank(orgFlow)){
				return "请选择需要提交的考点数据";
			}
		}
		 afterBiz.batchOscaSubmit(orgFlow);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value={"/updateMonthStatistics"})
	@ResponseBody
	public String updateMonthStatistics(String month) throws DocumentException {
		if(StringUtil.isBlank(month))
		{
			return "请选择需要填写数据统计的月份";
		}
		 afterBiz.updateMonthStatistics(month);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value={"/updateMonthAppStatistics"})
	@ResponseBody
	public String updateMonthAppStatistics(String month) throws DocumentException {
		if(StringUtil.isBlank(month))
		{
			return "请选择需要更新App使用率的月份";
		}
		 afterBiz.updateMonthAppStatistics(month);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}

	@RequestMapping(value="/queryProcess", method={RequestMethod.POST})
	@ResponseBody
	public List<ResDoctorSchProcess> queryProcess(String userCode, Model model){
		List<ResDoctorSchProcess> processes=null;
		if(StringUtil.isNotBlank(userCode))
		{
			processes=afterBiz.queryProcess(userCode);
		}
		return processes;
	}
	@RequestMapping(value="/queryDelProcess", method={RequestMethod.POST})
	@ResponseBody
	public List<ResDoctorSchProcess> queryDelProcess(String userCode, Model model){
		List<ResDoctorSchProcess> processes=null;
		if(StringUtil.isNotBlank(userCode))
		{
			processes=afterBiz.queryDelProcess(userCode);
		}
		return processes;
	}
	@RequestMapping(value="/queryApplyList", method={RequestMethod.POST})
	@ResponseBody
	public List<Map<String,String>> queryApplyList(String applyYear, Model model){
		List<Map<String,String>> processes=null;
		if(StringUtil.isNotBlank(applyYear))
		{
			processes=afterBiz.queryApplyList(applyYear);
		}
		return processes;
	}
	@RequestMapping(value="/delProcessType", method={RequestMethod.POST})
	@ResponseBody
	public String delProcessType(String processFlow,String recTypeId, Model model){
		if(StringUtil.isBlank(processFlow))
		{
			return "请选择轮转记录";
		}
		if(StringUtil.isBlank(recTypeId))
		{
			return "请选择需要退回的表单";
		}
		int count=afterBiz.delProcessType(processFlow,recTypeId);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value="/backProcessType", method={RequestMethod.POST})
	@ResponseBody
	public String backProcessType(String processFlow,String recTypeId, Model model){
		if(StringUtil.isBlank(processFlow))
		{
			return "请选择轮转记录";
		}
		if(StringUtil.isBlank(recTypeId))
		{
			return "请选择需要恢复的表单";
		}
		ResSchProcessExpress express=expressBiz.queryResRecByProcessAndType(processFlow,recTypeId);
		if(express!=null)
		{
			return "此类型出科表已被重新审核，无法退回！";
		}
		int count=afterBiz.backProcessType(processFlow,recTypeId);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value="/backAttend", method={RequestMethod.POST})
	@ResponseBody
	public String backAttend(String userCode,String startDate,String endDate, Model model){

		int count=afterBiz.backAttend(userCode,startDate,endDate);
		if(count<=0)
		{
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value="/delProcess", method={RequestMethod.POST})
	@ResponseBody
	public String delProcess(String processFlow,Model model){
		if(StringUtil.isBlank(processFlow))
		{
			return "请选择轮转记录";
		}
		int count=afterBiz.delProcess(processFlow);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value="/backProcess", method={RequestMethod.POST})
	@ResponseBody
	public String backProcess(String processFlow,Model model){
		if(StringUtil.isBlank(processFlow))
		{
			return "请选择轮转记录";
		}
		int count=afterBiz.backProcess(processFlow);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}

	@RequestMapping(value={"/selectAfter2"})
	@ResponseBody
	public String selectAfter2(String applyYear,String doctorFlow) throws DocumentException {
		if(StringUtil.isBlank(applyYear))
		{
			return "请选择申请年份";
		}
		if(StringUtil.isBlank(doctorFlow))
		{
			return "请填写学员流水号";
		}
			List<ResSchProcessExpress> recs = expressBiz.searchByUserFlowAndTypeId( doctorFlow, AfterRecTypeEnum.AfterSummary.getId());
			List<SchRotationDeptAfterWithBLOBs> afters=afterBiz.getAfterByDoctorFlow(doctorFlow,applyYear);
			Map<String,SchRotationDeptAfterWithBLOBs> map=new HashMap();
			if(afters!=null&&afters.size()>0)
			{
				for(SchRotationDeptAfterWithBLOBs a:afters)
				{
					map.put(a.getSchRotationDeptFlow()+a.getDoctorFlow(),a);
				}
			}
			if(recs!=null&&recs.size()>0)
			{
				for(ResSchProcessExpress rec:recs)
				{
					SchRotationDeptAfterWithBLOBs after=map.get(rec.getSchRotationDeptFlow()+rec.getOperUserFlow());
					if(after==null)
						after=new SchRotationDeptAfterWithBLOBs();

					String content = null == rec ? "" : rec.getRecContent();
					if (StringUtil.isNotBlank(content)) {
						Document doc = DocumentHelper.parseText(content);
						Element root = doc.getRootElement();
						List<Element> imageEles = root.elements();
						if (imageEles != null && imageEles.size() > 0) {
							StringBuffer imageUrls=new StringBuffer();
							StringBuffer thumbUrls=new StringBuffer();
							for (Element image : imageEles) {
								Element imageUrl = image.element("imageUrl");
								if(imageUrl!=null) {
									String url=imageUrl.getTextTrim().replace("http://58.213.112.250:65486", "http://js.ezhupei.com")
											.replace("http://116.62.70.138", "http://js.ezhupei.com");
									imageUrls.append( url+ ",");
									imageUrl.setText(url);
								}
								Element thumbUrl = image.element("thumbUrl");
								if(thumbUrl!=null) {
									String url = thumbUrl.getTextTrim().replace("http://58.213.112.250:65486", "http://js.ezhupei.com")
											.replace("http://116.62.70.138", "http://js.ezhupei.com");
									thumbUrls.append(url + ",");
									thumbUrl.setText(url);
								}
							}
							if(StringUtil.isNotBlank(imageUrls.toString()))
								after.setImageUrls(imageUrls.substring(0,imageUrls.length()-1));
							else
								after.setImageUrls("");

							if(StringUtil.isNotBlank(thumbUrls.toString()))
								after.setThumbUrls(thumbUrls.substring(0,thumbUrls.length()-1));
							else
								after.setThumbUrls("");

							rec.setRecContent(doc.asXML());
							expressBiz.edit(rec);
						}

					}
						after.setApplyYear(applyYear);
						after.setRecordStatus(rec.getRecordStatus());
						after.setModfiyTime("AUTO-ADD");
						after.setSchRotationDeptFlow(rec.getSchRotationDeptFlow());
						after.setDoctorFlow(rec.getOperUserFlow());
						afterBiz.edit(after);
				}
			}
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value={"/reAsseApply"})
	@ResponseBody
	public String reAsseApply(String applyYear,String doctorFlow,String recruitFlow, String remark) throws DocumentException {
		if(StringUtil.isBlank(applyYear))
		{
			return "请选择申请年份";
		}
		if(StringUtil.isBlank(doctorFlow))
		{
			return "请填写学员流水号";
		}
		if(StringUtil.isBlank(recruitFlow))
		{
			return "请填写学员流水号";
		}

        com.pinde.core.model.ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
		if(!applyYear.equals(recruit.getGraduationYear()))
		{
			return "结业考核年份不是当前年，无法更新数据！";
		}
		JsresGraduationApply jsresGraduationApply = jsresGraduationApplyBiz.searchByRecruitFlow(recruitFlow, "");
		if(jsresGraduationApply == null) {
			return "未提交过申请，无法更新数据！";
		}
		PubUserResume pubUserResume = userResumeBiz.readPubUserResume(recruit.getDoctorFlow());
		Map<String,String> practicingMap=new HashMap<>();

		if (pubUserResume != null) {
			String xmlContent = pubUserResume.getUserResume();
			if (StringUtil.isNotBlank(xmlContent)) {
				//xml转换成JavaBean
				UserResumeExtInfoForm userResumeExt = null;
				userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
				if (userResumeExt != null) {
//					数据获取规则：
//					“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”通过学员基本信息中获取
//					1.如果“是否通过医师资格考试”为否，则“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”均显示为【暂无】，
//					2. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”、“是否获得医师执业证书”均为是，则“报考资格材料”显示【医师执业证书】，“报考资格材料编码”展示基本信息“医师执业证书编码”，“执业类型”展示基本信息中的“执业类型”，“执业范围”展示基本信息中的“执业范围”；
//					3. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”为是，“是否获得医师执业证书”为否，则“报考资格材料”显示【医师资格证书】，“报考资格材料编码”展示基本信息“医师资格证书编码”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
//					4. 如果“是否通过医师资格考试”为是、 “是否获得医师资格证书”为否，则“报考资格材料”显示基本信息的“成绩单类型”，“报考资格材料编码”展示“暂无”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
					String isPassQualifyingExamination=userResumeExt.getIsPassQualifyingExamination();//是否通过医师资格考试
					String isHaveQualificationCertificate=userResumeExt.getIsHaveQualificationCertificate();//是否获得医师资格证书
					String isHavePracticingCategory=userResumeExt.getIsHavePracticingCategory();//是否获得医师执业证书
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isPassQualifyingExamination))
					{
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isHaveQualificationCertificate))
						{

                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isHavePracticingCategory))
							{

								practicingMap.put("graduationMaterialId","176");//报考资格材料
								practicingMap.put("graduationMaterialName","医师执业证书");//报考资格材料

								practicingMap.put("graduationMaterialCode",userResumeExt.getDoctorPracticingCategoryCode());//报考资格材料编码
								practicingMap.put("graduationMaterialUri",userResumeExt.getDoctorPracticingCategoryUrl());//资格证书url

								practicingMap.put("graduationCategoryId",userResumeExt.getPracticingCategoryLevelId());//执业类型
								practicingMap.put("graduationCategoryName",userResumeExt.getPracticingCategoryLevelName());//执业类型

								practicingMap.put("graduationScopeId",userResumeExt.getPracticingCategoryScopeId());//执业范围
								practicingMap.put("graduationScopeName",userResumeExt.getPracticingCategoryScopeName());//执业范围

								practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间

							}else{

								practicingMap.put("graduationMaterialId","177");//报考资格材料
								practicingMap.put("graduationMaterialName","医师资格证书");//报考资格材料

								practicingMap.put("graduationMaterialCode",userResumeExt.getDoctorQualificationCertificateCode());//报考资格材料编码
								practicingMap.put("graduationMaterialUri",userResumeExt.getDoctorQualificationCertificateUrl());//资格证书url

								practicingMap.put("graduationCategoryId","暂无");//执业类型
								practicingMap.put("graduationCategoryName","暂无");//执业类型

								practicingMap.put("graduationScopeId","暂无");//执业范围
								practicingMap.put("graduationScopeName","暂无");//执业范围

								practicingMap.put("graduationMaterialTime", "暂无");//取得时间

							}

						}else{

							practicingMap.put("graduationMaterialId",userResumeExt.getQualificationMaterialId2());//报考资格材料
							practicingMap.put("graduationMaterialName",userResumeExt.getQualificationMaterialName2());//报考资格材料

							practicingMap.put("graduationMaterialCode","暂无");//报考资格材料编码
							practicingMap.put("graduationMaterialUri",userResumeExt.getQualificationMaterialId2Url());//资格证书url

							practicingMap.put("graduationCategoryId","暂无");//执业类型
							practicingMap.put("graduationCategoryName","暂无");//执业类型

							practicingMap.put("graduationScopeId","暂无");//执业范围
							practicingMap.put("graduationScopeName","暂无");//执业范围

							practicingMap.put("graduationMaterialTime", "暂无");//取得时间

						}

					}else{
						practicingMap.put("graduationMaterialId","暂无");//报考资格材料
						practicingMap.put("graduationMaterialName","暂无");//报考资格材料

						practicingMap.put("graduationMaterialCode","暂无");//报考资格材料编码
						practicingMap.put("graduationMaterialUri","暂无");//资格证书url

						practicingMap.put("graduationCategoryId","暂无");//执业类型
						practicingMap.put("graduationCategoryName","暂无");//执业类型

						practicingMap.put("graduationScopeId","暂无");//执业范围
						practicingMap.put("graduationScopeName","暂无");//执业范围

						practicingMap.put("graduationMaterialTime", "暂无");//取得时间

					}
				}
			}
		}
			//培训记录
			//培训方案
			SchRotation rotation=rotationBiz.getRotationByRecruit(recruit);
			jsresGraduationApply.setRemark(remark);
			jsresGraduationApply.setRecruitFlow(recruitFlow);
			if(rotation != null) {
				jsresGraduationApply.setRotationFlow(rotation.getRotationFlow());
				jsresGraduationApply.setRotationName(rotation.getRotationName());
			}
			jsresGraduationApply.setApplyYear(applyYear);
			String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
			//如果user表中没有org_flow去医师表中的
			if (StringUtil.isBlank(orgFlow)) {
				orgFlow = resDoctorBiz.readDoctor(GlobalContext.getCurrentUser().getUserFlow()).getOrgFlow();
			}
			//如果当前学员是协同基地取他主基地所在的城市
			List<ResJointOrg> resJointOrgList = jointOrgBiz.selectByJointOrgFlow(orgFlow);
			if (resJointOrgList != null && resJointOrgList.size() > 0) {
				orgFlow = resJointOrgList.get(0).getOrgFlow();
			}
			SysOrg sysOrg = sysOrgBiz.readSysOrg(orgFlow);
			List<ResTestConfig> resTestConfigList = resTestConfigBiz.findEffectiveByParam(DateUtil.getCurrDateTime2(), DateUtil.transDateTime(jsresGraduationApply.getCreateTime()), sysOrg.getOrgCityId());
			if (resTestConfigList.size() > 0) {
				ResTestConfig resTestConfig = resTestConfigList.get(0);
				jsresGraduationApply.setTestId(resTestConfig.getTestId());
				//判断需不需要基地审核，需要则是待基地审核，不要要再判断需不需要市局审核，需要则是待市局审核，都不需要则是待省厅审核
                if (StringUtil.isNotBlank(recruit.getJointOrgFlow()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getJointLocalAudit())) {
					if("DoctorTrainingSpe".equals(recruit.getCatSpeId())) {
						jsresGraduationApply.setAuditStatusId("JointAuditing");
						jsresGraduationApply.setAuditStatusName("待协同基地审核");
						jsresGraduationApply.setJointLocalAuditStatusId("");
						jsresGraduationApply.setJointLocalAuditStatusName("");
						jsresGraduationApply.setJointLocalReason("");
					}else{
                        jsresGraduationApply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getId());
                        jsresGraduationApply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getName());
					}
                } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getLocalAudit())) {
                    jsresGraduationApply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getId());
                    jsresGraduationApply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getName());
                } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getChargeAudit())) {
                    jsresGraduationApply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getId());
                    jsresGraduationApply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getName());
				} else {
                    jsresGraduationApply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
                    jsresGraduationApply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getName());
				}
				//更新之后从头开始审核，判断需不需要基地审核，需要则是待基地审核，不要要再判断需不需要市局审核，需要则是待市局审核，都不需要则是待省厅审核
//				if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getLocalAudit())) {
//					jsresGraduationApply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getId());
//					jsresGraduationApply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getName());
//				} else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getChargeAudit())) {
//					jsresGraduationApply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getId());
//					jsresGraduationApply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getName());
//				} else {
//					jsresGraduationApply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
//					jsresGraduationApply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getName());
//				}
				jsresGraduationApply.setLocalAuditStatusId("");
				jsresGraduationApply.setLocalAuditStatusName("");
				jsresGraduationApply.setLocalReason("");
				jsresGraduationApply.setCityAuditStatusId("");
				jsresGraduationApply.setCityAuditStatusName("");
				jsresGraduationApply.setCityReason("");
				jsresGraduationApply.setGlobalAuditStatusId("");
				jsresGraduationApply.setGlobalAuditStatusName("");
				jsresGraduationApply.setGlobalReason("");
			}
			int i = jsresGraduationApplyBiz.editGraduationApply2(jsresGraduationApply,recruitFlow,"",recruit.getDoctorFlow(),applyYear, practicingMap);
			return i+"";
	}
	@RequestMapping(value={"/updatePer"})//更新不超过100的百分比
	@ResponseBody
	public String updatePer(String applyYear,String doctorFlow,String recruitFlow) throws DocumentException {
		if(StringUtil.isBlank(applyYear))
		{
			return "请选择申请年份";
		}
		if(StringUtil.isBlank(doctorFlow))
		{
			return "请填写学员流水号";
		}
		if(StringUtil.isBlank(recruitFlow))
		{
			return "请填写学员流水号";
		}

        com.pinde.core.model.ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
		if(!applyYear.equals(recruit.getGraduationYear()))
		{
			return "结业考核年份不是当前年，无法更新数据！";
		}
		JsresGraduationApply jsresGraduationApply = jsresGraduationApplyBiz.searchByRecruitFlow(recruitFlow, applyYear);
		if(jsresGraduationApply == null) {
			return "未提交过申请，无法更新数据！";
		}
		int i = jsresGraduationApplyBiz.updatePer(jsresGraduationApply.getApplyFlow(),recruitFlow,"",recruit.getDoctorFlow(),applyYear);

		return i+"";
	}
	@RequestMapping(value={"/updateInfo"})
	@ResponseBody
	public String updateInfo(String applyYear) throws DocumentException {
		if(StringUtil.isBlank(applyYear))
		{
			return "请选择申请年份";
		}
		afterBiz.updateRecruitAsseInfo(applyYear);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value={"/addUser"})
	@ResponseBody
	public String addUser() throws DocumentException {
		SysUser user=userBiz.readSysUser("442f48e2d8154879ae0635be7124a5eb");
		ResDoctor doctor=doctorBiz.readDoctor("442f48e2d8154879ae0635be7124a5eb");
		for(int i=0;i<5000;i++)
		{
			user.setUserFlow(PkUtil.getUUID());
			user.setUserCode("20170926"+i);
			user.setUserName("0926"+i);
			user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(),"123456"));
			doctor.setDoctorFlow(user.getUserFlow());
            doctor.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
			userBiz.insertUser(user);
			doctorBiz.insertDoctor(doctor);
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value={"/updateAfterEvalutaion"})
	@ResponseBody
	public String updateAfterEvalutaion() throws DocumentException {

		List<ResSchProcessExpress> expresses=afterBiz.updateAfterEvalutaion();
		int all=0;
		int count=0;
		if(expresses!=null)
		{
			all=expresses.size();
			Map<String,String> recordMap=new HashMap<>();
			for(ResSchProcessExpress express:expresses)
			{
				ResDoctorSchProcess process=processBiz.read(express.getProcessFlow());
				if(process!=null) {
					SchArrangeResult schArrangeResult = schArrangeResultBiz.readSchArrangeResult(process.getSchResultFlow());
					if (schArrangeResult != null) {
						String recordFlow=recordMap.get(schArrangeResult.getStandardGroupFlow()+schArrangeResult.getStandardDeptId());
						if(StringUtil.isBlank(recordFlow))
						{
							SchRotationDept schRotationDept = rotationDeptBiz.searchGroupFlowAndStandardDeptIdQuery(schArrangeResult.getStandardGroupFlow(), schArrangeResult.getStandardDeptId());
							if (schRotationDept != null) {
								recordFlow=schRotationDept.getRecordFlow();
							}
						}
						if(StringUtil.isNotBlank(recordFlow))
						{
							express.setSchRotationDeptFlow(recordFlow);
							express.setModifyUserFlow("20180926");
							recordMap.put(schArrangeResult.getStandardGroupFlow()+schArrangeResult.getStandardDeptId(),recordFlow);
							afterBiz.updateAfter(express);
							count++;
						}

					}
				}
			}
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED + ",一共" + all + "条数据，处理了" + count + "条数据。";
	}
	@RequestMapping(value={"/updateUriAuditInfo"})
	@ResponseBody
	public String updateUriAuditInfo(String applyYear) throws DocumentException {

		afterBiz.updateUriAuditInfo();
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value={"/updateDeptDetail"})
	@ResponseBody
	public String updateDeptDetail(String applyYear) throws DocumentException {
		if(StringUtil.isBlank(applyYear))
		{
			return "请选择申请年份";
		}
		afterBiz.updateDeptDetail(applyYear);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value={"/updateDeptTemp"})
	@ResponseBody
	public String updateDeptTemp(String applyYear) throws DocumentException {
		if(StringUtil.isBlank(applyYear))
		{
			return "请选择申请年份";
		}
		afterBiz.updateDeptTemp(applyYear);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value={"/updateDeptAvgTemp"})
	@ResponseBody
	public String updateDeptAvgTemp(String applyYear) throws DocumentException {
		if(StringUtil.isBlank(applyYear))
		{
			return "请选择申请年份";
		}
		afterBiz.updateDeptAvgTemp(applyYear);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value={"/updateRecruitAvgTemp"})
	@ResponseBody
	public String updateRecruitAvgTemp(String applyYear) throws DocumentException {
		if(StringUtil.isBlank(applyYear))
		{
			return "请选择申请年份";
		}
		afterBiz.updateRecruitAvgTemp(applyYear);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value={"/saveRegisteManua"})
	@ResponseBody
	public String saveRegisteManua(String registeManua,String recruitFlow) throws DocumentException {
		if(StringUtil.isBlank(registeManua)||StringUtil.isBlank(recruitFlow))
		{
			return "";
		}
		String applyYear= DateUtil.getYear();
		afterBiz.saveRegisteManua(registeManua,recruitFlow,applyYear);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value={"/chouquPhoto"})
	@ResponseBody
	public String chouquPhoto(MultipartFile file,String dirUrl){
		if(file.getSize() > 0){
			if(StringUtil.isBlank(dirUrl))
			{
				return "请设置图片保存路径";
			}
			try{
				ExcelUtile result = (ExcelUtile) afterBiz.chouquPhoto(file,dirUrl);
				if(null!=result)
				{
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					String msg= (String) result.get("msg");
					if("1".equals(code))
					{
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;
					}else{
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "处理" + count + "条记录！";
						}else{
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
						}
					}
				}else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}
	@RequestMapping(value={"/addTempIdNo"})
	@ResponseBody
	public String addTempIdNo(MultipartFile file){
		if(file.getSize() > 0){
			try{
				ExcelUtile result = (ExcelUtile) afterBiz.addTempIdNo(file);
				if(null!=result)
				{
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					String msg= (String) result.get("msg");
					if("1".equals(code))
					{
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;
					}else{
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "处理" + count + "条记录！";
						}else{
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
						}
					}
				}else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}
	@RequestMapping(value={"/addUserInfo"})
	@ResponseBody
	public String addUserInfo(MultipartFile file){
		if(file.getSize() > 0){
			try{
				ExcelUtile result = (ExcelUtile) afterBiz.addUserInfo(file);
				if(null!=result)
				{
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					String msg= (String) result.get("msg");
					if("1".equals(code))
					{
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;
					}else{
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "处理" + count + "条记录！";
						}else{
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
						}
					}
				}else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}
	@RequestMapping(value={"/upDateRecruitInfo"})
	@ResponseBody
	public String upDateRecruitInfo(MultipartFile file){
		if(file.getSize() > 0){
			try{
				ExcelUtile result = (ExcelUtile) afterBiz.upDateRecruitInfo(file);
				if(null!=result)
				{
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					List<Map<String,Object>> list= (List<Map<String,Object>>) result.get("list");
					List<String> idNos= (List<String>) result.get("idNos");
					String msg= (String) result.get("msg");
					System.out.println(msg);
					if("1".equals(code))
					{
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;
					}else{
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "处理" + count + "条记录！==" + JSON.toJSONString(idNos);
						}else{
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
						}
					}
				}else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}
	@RequestMapping(value={"/updateRecruitOrgInfo"})
	@ResponseBody
	public String updateRecruitOrgInfo(MultipartFile file){
		if(file.getSize() > 0){
			try{
				ExcelUtile result = (ExcelUtile) afterBiz.updateRecruitOrgInfo(file);
				if(null!=result)
				{
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					List<Map<String,Object>> list= (List<Map<String,Object>>) result.get("list");
					List<String> idNos= (List<String>) result.get("idNos");
					String msg= (String) result.get("msg");
					System.out.println(msg);
					if("1".equals(code))
					{
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;
					}else{
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "处理" + count + "条记录！==" + JSON.toJSONString(idNos);
						}else{
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
						}
					}
				}else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}
	@RequestMapping(value={"/updateRecruitSpeInfo"})
	@ResponseBody
	public String updateRecruitSpeInfo(MultipartFile file){
		if(file.getSize() > 0){
			try{
				ExcelUtile result = (ExcelUtile) afterBiz.updateRecruitSpeInfo(file);
				if(null!=result)
				{
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					List<Map<String,Object>> list= (List<Map<String,Object>>) result.get("list");
					List<String> idNos= (List<String>) result.get("idNos");
					String msg= (String) result.get("msg");
					System.out.println(msg);
					if("1".equals(code))
					{
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;
					}else{
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "处理" + count + "条记录！==" + JSON.toJSONString(idNos);
						}else{
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
						}
					}
				}else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}
	@RequestMapping(value={"/updateReturenInfo"})
	@ResponseBody
	public String updateReturenInfo(MultipartFile file){
		if(file.getSize() > 0){
			try{
				ExcelUtile result = (ExcelUtile) afterBiz.updateReturenInfo(file);
				if(null!=result)
				{
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					List<Map<String,Object>> list= (List<Map<String,Object>>) result.get("list");
					List<String> idNos= (List<String>) result.get("idNos");
					String msg= (String) result.get("msg");
					System.out.println(msg);
					if("1".equals(code))
					{
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;
					}else{
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "处理" + count + "条记录！==" + JSON.toJSONString(idNos);
						}else{
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
						}
					}
				}else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}
	@RequestMapping(value={"/addExamTeaRole"})
	@ResponseBody
	public String examTeaRole(){
		afterBiz.examTeaRole();
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value={"/updateNotStudy"})
	@ResponseBody
	public String updateNotStudy(){
		afterBiz.updateNotStudy();
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	//中文转Unicode
	public static String gbEncoding(final String gbString) {   //gbString = "测试"
		char[] utfBytes = gbString.toCharArray();   //utfBytes = [测, 试]
		String unicodeBytes = "";
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);   //转换为16进制整型字符串
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			unicodeBytes = unicodeBytes + "\\u" + hexB;
		}
		System.out.println("unicodeBytes is: " + unicodeBytes);
		return unicodeBytes;
	}
	//Unicode转中文
	public static String decodeUnicode(final String dataStr) {
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			end = dataStr.indexOf("\\u", start + 2);
			String charStr = "";
			if (end == -1) {
				charStr = dataStr.substring(start + 2, dataStr.length());
			} else {
				charStr = dataStr.substring(start + 2, end);
			}
			char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
			buffer.append(new Character(letter).toString());
			start = end;
		}
		return buffer.toString();
	}

	public static   void main(String[] args) {
		String c=gbEncoding("广医住培20180809");
		String d=decodeUnicode("\\u0045\\u003a\\u002f\\u4f4f\\u9662\\u533b\\u5e08\\u8fc7\\u7a0b\\u0033\\u002e\\u0035\\u002f\\u0056\\u0033\\u002e\\u0035\\u002e\\u0030\\u002e\\u0039\\u0034\\u002f\\u0063\\u006f\\u0064\\u0065");
		System.out.println(c);
		System.out.println(d);
		System.out.println(PasswordHelper.encryptPassword("201810310077","123456"));
		System.out.println(PasswordHelper.encryptPassword("201810310998","123456"));
		System.out.println(PasswordHelper.encryptPassword("201810311099","123456"));
		System.out.println(PasswordHelper.encryptPassword("201810311100","123456"));

	}
	@RequestMapping(value={"/insertSptUserInfo"})
	@ResponseBody
	public String insertSptUserInfo(MultipartFile file){
		if(file.getSize() > 0){
			try{
                ExcelUtile result = afterBiz.insertSptUserInfo(file);
				if(null!=result)
				{
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					List<Map<String,Object>> list= (List<Map<String,Object>>) result.get("list");
					List<String> idNos= (List<String>) result.get("idNos");
					String msg= (String) result.get("msg");
					System.out.println(msg);
					if("1".equals(code))
					{
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;
					}else{
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "处理" + count + "条记录！==" + JSON.toJSONString(idNos);
						}else{
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
						}
					}
				}else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}
	@RequestMapping(value={"/insertGjptUserInfo"})
	@ResponseBody
	public String insertGjptUserInfo(MultipartFile file){
		if(file.getSize() > 0){
			try{
                ExcelUtile result = afterBiz.insertGjptUserInfo(file);
				if(null!=result)
				{
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					List<Map<String,Object>> list= (List<Map<String,Object>>) result.get("list");
					List<String> idNos= (List<String>) result.get("idNos");
					String msg= (String) result.get("msg");
					System.out.println(msg);
					if("1".equals(code))
					{
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;
					}else{
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "处理" + count + "条记录！==" + JSON.toJSONString(idNos);
						}else{
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
						}
					}
				}else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}
}
