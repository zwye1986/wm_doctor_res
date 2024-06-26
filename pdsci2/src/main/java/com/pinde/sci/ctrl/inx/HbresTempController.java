package com.pinde.sci.ctrl.inx;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.hbres.IHbResGraduationApplyBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.jsres.ISchRotationDeptAfterBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResRecBiz;
import com.pinde.sci.biz.res.IResSchProcessExpressBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchRotationBiz;
import com.pinde.sci.biz.sch.ISchRotationDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.model.mo.*;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author tiger
 *
 */
@Controller
@RequestMapping("/hbres/temp")
public class HbresTempController extends GeneralController{
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
	private IHbResGraduationApplyBiz graduationApplyBiz;
	@Autowired
	private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private ISchRotationBiz rotationBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;

	@RequestMapping(value={"/reAsseApply"})
	@ResponseBody
	public String reAsseApply(String applyYear,String doctorFlow,String recruitFlow) throws DocumentException {
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

		ResDoctor resDoctor=doctorBiz.readDoctor(recruitFlow);
		if(resDoctor==null)
		{
			return "无医师信息，无法提交申请！";
		}
		if(!applyYear.equals(resDoctor.getGraduationYear()))
		{
			return "结业考核年份不是当前年，无法更新数据！";
		}
		JsresGraduationApply jsresGraduationApply = graduationApplyBiz.searchByRecruitFlow(recruitFlow, applyYear);
		if(jsresGraduationApply == null) {
			return "未提交过申请，无法更新数据！";
		}
		PubUserResume pubUserResume = userResumeBiz.readPubUserResume(resDoctor.getDoctorFlow());
		Map<String,String> practicingMap=new HashMap<>();

		if (pubUserResume != null) {
			String xmlContent = pubUserResume.getUserResume();
			if (StringUtil.isNotBlank(xmlContent)) {
				//xml转换成JavaBean
				BaseUserResumeExtInfoForm userResumeExt = null;
				userResumeExt = userResumeBiz.converyToJavaBean(xmlContent, BaseUserResumeExtInfoForm.class);
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
			//培训方案
			SchRotation rotation=rotationBiz.readSchRotation(jsresGraduationApply.getRotationFlow());
			jsresGraduationApply.setRecruitFlow(recruitFlow);
			List<String> resultFlows=new ArrayList<>();
			if(rotation != null) {
				jsresGraduationApply.setRotationFlow(rotation.getRotationFlow());
				jsresGraduationApply.setRotationName(rotation.getRotationName());//方案中的科室
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

			int i = graduationApplyBiz.editGraduationApply2(jsresGraduationApply,recruitFlow,resDoctor.getDoctorFlow(),applyYear, practicingMap, resultFlows);

			return i+"";
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
		return GlobalConstant.OPERATE_SUCCESSED;
	}
}
