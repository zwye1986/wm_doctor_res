package com.pinde.sci.ctrl.hbres;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.hbres.ExamManageBiz;
import com.pinde.sci.biz.hbres.GradeManageBiz;
import com.pinde.sci.biz.hbres.IHbResDoctorRecruitBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IRecruitCfgBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorRecruitBiz;
import com.pinde.sci.biz.res.IResOrgSpeAssignBiz;
import com.pinde.sci.biz.sch.ISchRotationBiz;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.hbres.HbResDegreeCategoryEnum;
import com.pinde.sci.enums.res.HBRecDocTypeEnum;
import com.pinde.sci.enums.res.RecDocTypeEnum;
import com.pinde.sci.enums.res.RecruitTypeEnum;
import com.pinde.sci.enums.res.ResBaseStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.DateCfgMsg;
import com.pinde.sci.model.res.HospitalDoctorExportInfo;
import com.pinde.sci.model.res.ResDoctorRecruitExt;
import com.pinde.sci.model.res.ResExamDoctorExt;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/hbres/singup/hospital")
public class HospitalController extends GeneralController {

	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResOrgSpeAssignBiz speAssignBiz;
	@Autowired
	private IHbResDoctorRecruitBiz doctorRecruitBiz;
	@Autowired
	private IResDoctorRecruitBiz resDoctorRecruitBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private GradeManageBiz gradeManage;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private ISchRotationBiz schRotationBiz;
	@Autowired
	private IRecruitCfgBiz recruitCfgBiz;
	@Autowired
	private ExamManageBiz examManageBiz;
	@Autowired
	private ICfgBiz cfgBiz;
	
	/**
	 * 招录主页
	 * @return
	 */
	@RequestMapping(value="main")
	public String main(Model model,Integer currentPage,HttpServletRequest request){
		Map<String , Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dictTypeId", DictTypeEnum.DoctorTrainingSpe.getId());
		PageHelper.startPage(currentPage,getPageSize(request));
		List<SysDict> doctorTrainingSpeList = this.doctorRecruitBiz.searchTrainSpeList(paramMap);
		model.addAttribute("doctorTrainingSpeList",doctorTrainingSpeList);
		
		int totalAssignPlan = 0;
		int totalFillNum = 0;
		int totalConfirmCount = 0;
		
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		String orgFlow = user.getOrgFlow();
		if (StringUtil.isNotBlank(orgFlow)) {
			String regYear = InitConfig.getSysCfg("res_reg_year");
			//组织专业对应的招录计划数map
			List<ResOrgSpeAssign> speAssignList = speAssignBiz.searchSpeAssign(orgFlow,regYear);
			if(speAssignList!=null && speAssignList.size()>0){
				Map<String,ResOrgSpeAssign> speAssignMap = new HashMap<String,ResOrgSpeAssign>();
				for(ResOrgSpeAssign rosa : speAssignList){
					speAssignMap.put(rosa.getSpeId(),rosa);
					if (rosa.getAssignPlan() != null) {
						totalAssignPlan += rosa.getAssignPlan().intValue();
					}
				}
				model.addAttribute("speAssignMap",speAssignMap);
			}
			
			//组织专业对应的填报志愿数和复试数map
			ResDoctorRecruit recruit = new ResDoctorRecruit();
			recruit.setOrgFlow(orgFlow);
			recruit.setRecruitYear(regYear);
			Map<String,Object> paramMap2 = new HashMap<>();
			paramMap2.put("orgFlow",orgFlow);
			paramMap2.put("recruitYear",regYear);
			List<ResDoctorRecruit> doctorRecruit = resDoctorRecruitBiz.searchDoctorRecruitWithLine(paramMap2);
//			List<ResDoctorRecruit> doctorRecruit = resDoctorRecruitBiz.searchDoctorRecruit(recruit);
			if(doctorRecruit!=null && doctorRecruit.size()>0){
				//填报志愿人数
				Map<String,Integer> recruitNumMap = new HashMap<String,Integer>();
				//发送复试通知人数
				Map<String,Integer> retestNumMap = new HashMap<String,Integer>();
				//调剂人数(别的专业调剂到本专业的人数)
				Map<String , Integer> swapCountMap = new HashMap<String, Integer>();
				//确认录取人数
				Map<String, Integer> confirmCountMap = new HashMap<String, Integer>();
				for(ResDoctorRecruit rdr : doctorRecruit){
					//填报志愿数
					if (RecruitTypeEnum.Fill.getId().equals(rdr.getRecruitTypeId())&&(!"N".equals(rdr.getReturnBackFlag()))) {
						totalFillNum++;
						Integer recruitNum = recruitNumMap.get(rdr.getSpeId());
						if(recruitNum==null){recruitNum=0;}
						recruitNum++;
						recruitNumMap.put(rdr.getSpeId(),recruitNum);
					}
					//已发送复试通知人数
					if(GlobalConstant.FLAG_Y.equals(rdr.getRetestFlag())){
						Integer retestCount = retestNumMap.get(rdr.getSpeId());
						if(retestCount==null){retestCount=0;}
						retestCount++;
						retestNumMap.put(rdr.getSpeId(), retestCount);
					}
					//调剂人数
					if(RecruitTypeEnum.Swap.getId().equals(rdr.getRecruitTypeId())){
						Integer swapNum = swapCountMap.get(rdr.getSpeId());
						if(swapNum==null){swapNum=0;}
						swapNum++;
						swapCountMap.put(rdr.getSpeId(), swapNum);
					}
					
					//确认录取人数
					if(GlobalConstant.FLAG_Y.equals(rdr.getConfirmFlag())){
						totalConfirmCount++;
						Integer confirmCount = confirmCountMap.get(rdr.getSpeId());
						if(confirmCount==null){confirmCount=0;}
						confirmCount++;
						confirmCountMap.put(rdr.getSpeId(), confirmCount);
					}
				}
				model.addAttribute("recruitNumMap",recruitNumMap);
				model.addAttribute("retestNumMap",retestNumMap);
				model.addAttribute("swapCountMap",swapCountMap);
				model.addAttribute("confirmCountMap",confirmCountMap);
			}

			paramMap.put("orgFlow",orgFlow);
			paramMap.put("assignYear",regYear);
			Integer sum = 0;
			List<Map<String,Object>> adminSwapNum = doctorRecruitBiz.getAdminSwapCount(paramMap);
			if(adminSwapNum!=null && !adminSwapNum.isEmpty()){
				Map<String,Object> adminSwapMap = new HashMap<String,Object>();
				for(Map<String,Object> map : adminSwapNum){
					Object count = map.get("v");
					adminSwapMap.put((String)map.get("k"),count);
					if(count!=null){
						Integer countInt = Integer.valueOf(count.toString());
						sum+=countInt;
					}
				}
				model.addAttribute("adminSwapMap",adminSwapMap);
			}
			model.addAttribute("adminSwapTotle",sum);
		}
		model.addAttribute("totalAssignPlan",totalAssignPlan);
		model.addAttribute("totalFillNum",totalFillNum);
		model.addAttribute("totalConfirmCount",totalConfirmCount);



		return "hbres/hospital/main";
	}

	//报到审核页面
	@RequestMapping("/registAudit")
	public String registAudit(String stuType,String userName,String idNo,String speId,Integer currentPage, Model model,
							  String sessionNumber,String auditStatusId,HttpServletRequest request,String datas[]){
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		String orgFlow = user.getOrgFlow();
		String regYear = InitConfig.getSysCfg("res_reg_year");
		String recruitYear = regYear;
		List<String>docTypeList=new ArrayList<String>();
		ResDoctor doctor=new ResDoctor();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}else{
			datas=new String[HBRecDocTypeEnum.values().length];
			int i=0;
			for(HBRecDocTypeEnum e:HBRecDocTypeEnum.values())
			{
				docTypeList.add(e.getId());
				datas[i++]=e.getId();
			}
		}
		model.addAttribute("datas", datas);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("orgFlow", orgFlow);
		if(StringUtil.isNotBlank(sessionNumber)){
			recruitYear = sessionNumber;
		}
		model.addAttribute("recruitYear", recruitYear);
		paramMap.put("recruitYear", recruitYear);
		paramMap.put("userName", userName);
		paramMap.put("idNo", idNo);
		paramMap.put("speId", speId);
//		paramMap.put("sessionNumber", sessionNumber);
		paramMap.put("auditStatusId", auditStatusId);
		paramMap.put("docTypeList",docTypeList);
		if(StringUtil.isNotBlank(stuType)){
			if("Graduate".equals(stuType)){
				paramMap.put("doctorTypeId",stuType);
			}else {
				paramMap.put("isUnderLine",stuType);
			}
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,String>> registList = doctorRecruitBiz.searchRegistList(paramMap);
		model.addAttribute("registList",registList);
		model.addAttribute("regYear",regYear);
		return "hbres/hospital/registAuditList";
	}

	//批量审核报到
	@RequestMapping("/auditForBatch")
	@ResponseBody
	public int auditForBatch(String[] recordFlows){
		for(String recordFlow:recordFlows){
			ResRecruitRegister recruitRegister = new ResRecruitRegister();
			recruitRegister.setRecordFlow(recordFlow);
			recruitRegister.setAuditStatusId(ResBaseStatusEnum.Passed.getId());
			recruitRegister.setAuditStatusName(ResBaseStatusEnum.Passed.getName());
			auditRegist(recruitRegister);
		}
		return 1;
	}


	//报到审核
	@RequestMapping("/auditRegist")
	@ResponseBody
	public int auditRegist(ResRecruitRegister recruitRegister){
		if(StringUtil.isNotBlank(recruitRegister.getAuditStatusId())){
			recruitRegister.setAuditStatusName(ResBaseStatusEnum.getNameById(recruitRegister.getAuditStatusId()));
		}
		int result = doctorRecruitBiz.editRegister(recruitRegister);
		if(recruitRegister.getAuditStatusId().equals(ResBaseStatusEnum.Passed.getId())){
			ResRecruitRegister register = doctorRecruitBiz.readRecruitRegister(recruitRegister.getRecordFlow());//接受报到后，同步医师培训专业，培养年限，医师状态到过程
			ResDoctor doctor2 = doctorBiz.findByFlow(register.getDoctorFlow());
			ResDoctor doctor = new ResDoctor();
			doctor.setDoctorFlow(register.getDoctorFlow());
			SysUser user = new SysUser();
			user.setUserFlow(register.getDoctorFlow());
			doctor.setOrgFlow(register.getOrgFlow());
			doctor.setOrgName(register.getOrgName());
			if(StringUtil.isBlank(doctor2.getTrainingYears())){
				doctor.setTrainingYears("3");
			}else{
				doctor.setTrainingYears(doctor2.getTrainingYears());
			}
			doctor.setTrainingSpeId(register.getSpeId());
			doctor.setTrainingSpeName(register.getSpeName());
			doctor.setDoctorStatusId("Training");//过程初始状态
			doctor.setDoctorStatusName("在培");

			String graduationYear = String.valueOf(Integer.parseInt(doctor2.getSessionNumber())+Integer.parseInt(doctor.getTrainingYears()));
			doctor.setGraduationYear(graduationYear);
			user.setOrgFlow(register.getOrgFlow());
			user.setOrgName(register.getOrgName());
			doctorBiz.editDoctor(doctor);
			//完善医师轮转信息
			List<SchRotation> schRotlst = this.schRotationBiz.searchSchRotation(register.getSpeId());
			if(null != schRotlst && schRotlst.size() > 0){
				schRotlst.get(0);
				ResDoctor doc = new ResDoctor();
				doc.setDoctorFlow(register.getDoctorFlow());
				doc.setRotationFlow(schRotlst.get(0).getRotationFlow());
				doc.setRotationName(schRotlst.get(0).getRotationName());
				this.doctorBiz.editDoctor(doc);
				user.setMedicineTypeId(schRotlst.get(0).getRotationTypeId());
				user.setMedicineTypeName(schRotlst.get(0).getRotationTypeName());
			}
			userBiz.updateUser(user);
		}
		return result;
	}

	/**
	 * 获取该基地改专业下的人员名单
	 * @param speId
	 * @return
	 */
	@RequestMapping("/getdoctors")
	public String getDoctors(String speId , String graduatedId , String key , String batchOper , String assignPlan,
							 String retestFlag,String admitFlag,String confirmResult , Model model,Integer currentPage,
							 HttpServletRequest request,String order,String personType,String adminSwapFlag,String datas[]){
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		String orgFlow = user.getOrgFlow();
		String regYear = InitConfig.getSysCfg("res_reg_year");

		boolean isAdminDate =false;//是否在录取时间范围内
		String currDate = DateUtil.getCurrDate();
		ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(regYear);
		DateCfgMsg admitDateCfgMsg = new DateCfgMsg(recruitCfg);
		admitDateCfgMsg.setAdmitDateMsg(currDate);
		if (admitDateCfgMsg.getAllowSwap()) {
			isAdminDate = true;
		}
		model.addAttribute("isAdminDate",isAdminDate);

		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}else{
			datas=new String[HBRecDocTypeEnum.values().length];
			int i=0;
			for(HBRecDocTypeEnum e:HBRecDocTypeEnum.values())
			{
				docTypeList.add(e.getId());
				datas[i++]=e.getId();
			}
		}
		model.addAttribute("datas", datas);

		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("docTypeList",docTypeList);
		paramMap.put("orgFlow", orgFlow);
		paramMap.put("recruitYear", regYear);
		paramMap.put("speId", speId);
		if(StringUtil.isNotBlank(graduatedId)){
			paramMap.put("graduatedId", graduatedId);	
		}
		if(StringUtil.isNotBlank(key)){
			paramMap.put("key", key);
		}
		if(StringUtil.isNotBlank(retestFlag)){
			paramMap.put("retestFlag", retestFlag);
		}
		if(StringUtil.isNotBlank(admitFlag)){
			paramMap.put("admitFlag", admitFlag);
		}
		if(StringUtil.isNotBlank(order)){
			paramMap.put("order", order);
		}
		if(StringUtil.isNotBlank(adminSwapFlag)){
			paramMap.put("adminSwapFlag", adminSwapFlag);
		}
		paramMap.put("returnBackFlag", "N");
		ResOrgSpeAssign currSpe = speAssignBiz.searchSpeAssign(orgFlow, regYear , speId);
		model.addAttribute("currSpe" , currSpe);
		if(StringUtil.isBlank(batchOper)){
			if("signStu".equals(personType)){
				paramMap.put("signStu","Y");
			}
			if("reExamStu".equals(personType)){
				paramMap.put("retestFlag", "Y");
				paramMap.put("reExamStu", "Y");
				//复试成绩展示按钮
				String cfgCode = "hbShowScore_"+orgFlow;
				SysCfg cfg = cfgBiz.read(cfgCode);
				model.addAttribute("cfg",cfg);
			}
			if("enterStu".equals(personType)){
				paramMap.put("enterStu", "Y");
			}
		}else if("1".equals(batchOper)){
			//只查需要复试通知的
			paramMap.put("retestFlag", "N");
		}else if("2".equals(batchOper)){
			//只查需要录取通知
			paramMap.put("retestFlag", "Y");
			paramMap.put("admitFlag", "N");
			paramMap.put("not_recruit_flag", "Y");
			paramMap.put("empty_flag", "Y");
		}else if("3".equals(batchOper)){
			//待确认
			paramMap.put("enterStu", "Y");
			paramMap.put("stuType", "3");
		}else if("4".equals(batchOper)){
			//录取
			paramMap.put("enterStu", "Y");
			paramMap.put("stuType", "4");
		}else if("5".equals(batchOper)){
			//不录取
			paramMap.put("enterStu", "Y");
			paramMap.put("stuType", "5");
		}else if("6".equals(batchOper)){
			//放弃录取
			paramMap.put("enterStu", "Y");
			paramMap.put("stuType", "6");
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<ResDoctorRecruitExt> doctorRecruitExts = this.doctorRecruitBiz.selectDoctorRecruitExt(paramMap);
		model.addAttribute("doctorRecruitExts", doctorRecruitExts);
		model.addAttribute("assignPlan",assignPlan.equals("")?"0":assignPlan);//组织专业计划招收人数
		model.addAttribute("confirmResult",confirmResult.equals("")?"0":confirmResult);//已录取人数（逻辑调整，现在为已发复试通知人数）
		return "hbres/hospital/doctors";
	}

	/**
	 * 已发复试通知查看
	 * @param recruitFlow
	 * @param flag 复试或录取标识
	 * @return
     */
	@RequestMapping("/retestNoticeSearch")
	public String retestNotice(String recruitFlow,String flag,Model model){
		Map<String, Object> paraMp = new HashMap<String, Object>();
		paraMp.put("recruitFlow", recruitFlow);
		ResDoctorRecruitWithBLOBs docRecruitClob = this.doctorRecruitBiz.docRecruitClobSearch(paraMp);
		model.addAttribute("docRecruit", docRecruitClob);
		return "hbres/hospital/noticeRetestSearch";
	}

	/**
	 * 获取学员详细信息
	 * @param recruitFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="doctorInfo")
	public String doctorInfo(String recruitFlow , Model model)throws DocumentException{
		ResDoctorRecruit recruit = resDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
		ResDoctor doctor = doctorBiz.readDoctor(recruit.getDoctorFlow());
		SysUser user = userBiz.readSysUser(recruit.getDoctorFlow());
		model.addAttribute("recruit", recruit);
		model.addAttribute("doctor", doctor);
		model.addAttribute("user", user);
		PubUserResume pubUserResume = userResumeBiz.readPubUserResume(user.getUserFlow());
		if (pubUserResume != null) {
			String xmlContent = pubUserResume.getUserResume();
			if (StringUtil.isNotBlank(xmlContent)) {
				//xml转换成JavaBean
				model.addAttribute("extInfo", userResumeBiz.converyToJavaBean(xmlContent, BaseUserResumeExtInfoForm.class));
			}
		}
		//查询招录历史
		List<ResDoctorRecruit> doctorRecruits = this.resDoctorRecruitBiz.findResDoctorRecruits(recruit.getRecruitYear(), recruit.getDoctorFlow());
		Collections.reverse(doctorRecruits);
		model.addAttribute("doctorRecruits" , doctorRecruits);
		return "hbres/hospital/doctorInfo";
	}

	/**
	 * 获取学员详细信息
	 * @param doctorFlow
	 * @param model
     * @return
     */
	@RequestMapping(value="/doctorInfoByDocFlow")
	public String doctorInfoByDocFlow(String doctorFlow,String recruitFlow,Model model)throws DocumentException{
		String assignYear = InitConfig.getSysCfg("res_reg_year");

		ResExam exam = examManageBiz.findExamByCfgYearAndTypeId(assignYear,null);
		ResExamDoctorExt search = new ResExamDoctorExt();
		search.setDoctorFlow(doctorFlow);
		search.setExamFlow(exam.getExamFlow());
		List<ResExamDoctorExt> examDoctorExts = examManageBiz.findExamDoctorExts(search);
		if(examDoctorExts!=null&&examDoctorExts.size()>0){
			model.addAttribute("score",examDoctorExts.get(0).getExamResult());
		}


		ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
		model.addAttribute("doctor", doctor);

		SysUser user = userBiz.readSysUser(doctorFlow);
		model.addAttribute("user", user);
		PubUserResume pubUserResume = userResumeBiz.readPubUserResume(user.getUserFlow());
		if (pubUserResume != null) {
			String xmlContent = pubUserResume.getUserResume();
			if (StringUtil.isNotBlank(xmlContent)) {
				//xml转换成JavaBean
				model.addAttribute("extInfo", userResumeBiz.converyToJavaBean(xmlContent, BaseUserResumeExtInfoForm.class));
			}
		}
		ResDoctorRecruit recruit = resDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
		model.addAttribute("recruit", recruit);

		//查询招录历史
		List<ResDoctorRecruit> doctorRecruits = this.resDoctorRecruitBiz.findResDoctorRecruits(assignYear,doctorFlow);
		Collections.reverse(doctorRecruits);
		model.addAttribute("doctorRecruits" , doctorRecruits);
		return "hbres/hospital/doctorInfo";
	}
	
	/**
	 * 显示复试通知页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="noticeRetestMain")
	public String noticeRetestMain(Model model){
		return "hbres/hospital/noticeRetestMain";
	}
	/**
	 * 显示复试通知未发送直接退回页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="returnBackPage")
	public String returnBackPage(Model model){
		return "hbres/hospital/returnBackPage";
	}
	
	@RequestMapping("/gradeedit")
	public String gradeEdit(String recruitFlow , Model model){
		ResDoctorRecruit recruit = this.resDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
		model.addAttribute("recruit" , recruit);
		return "hbres/hospital/gradeedit";
	}
	
	/**
	 * 成绩保存
	 * @param recruit
	 * @return
	 */
	@RequestMapping("/savegrade")
	@ResponseBody
	public String saveGrade(ResDoctorRecruitWithBLOBs recruit){
		if(StringUtil.isNotBlank(recruit.getRecruitFlow())){
			ResDoctorRecruitWithBLOBs oldRecruit = this.resDoctorRecruitBiz.readResDoctorRecruitWithBLOBs(recruit.getRecruitFlow());
			BigDecimal examResult = oldRecruit.getExamResult();
		    BigDecimal operResult = recruit.getOperResult();
		    BigDecimal auditonResult = recruit.getAuditionResult();
		    //总成绩=笔试分*0.4/1.5 + 面试分*0.2 + 操作技能分*0.4
		    if(examResult != null && operResult != null && auditonResult != null){
				BigDecimal totalResult = new BigDecimal(0);
		    	totalResult = examResult.multiply(new BigDecimal(0.4)).divide(new BigDecimal(1.5),3,BigDecimal.ROUND_HALF_DOWN)
						.add(operResult.multiply(new BigDecimal(0.4)))
						.add(auditonResult.multiply(new BigDecimal(0.2)));
				totalResult = totalResult.setScale(2,BigDecimal.ROUND_HALF_UP);
				oldRecruit.setTotleResult(totalResult);
		    }
			if(null==operResult){
				oldRecruit.setOperResult(null);
				oldRecruit.setTotleResult(null);
			}else{
				oldRecruit.setOperResult(operResult);
			}
			if(null==auditonResult){
				oldRecruit.setAuditionResult(null);
				oldRecruit.setTotleResult(null);
			}else{
				oldRecruit.setAuditionResult(auditonResult);
			}
			this.resDoctorRecruitBiz.updateDocrecruit(oldRecruit);
			return GlobalConstant.OPRE_SUCCESSED_FLAG;
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
		
	}
	
	/**
	 * 显示录取通知的界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="noticeRecruitMain")
	public String noticeRecruitMain(Model model){
		String title = "录取通知内容";
		model.addAttribute("title" , title);
		return "hbres/hospital/noticeRecruitMain";
	}

	/**
	 * 确认录取
	 * @param recruitFlows
	 * @param admitNotice
	 * @return
	 */
	@RequestMapping(value="/noticeRecruit")
	@ResponseBody
	public String noticeRecruit(String[] recruitFlows,String admitNotice,String trainYear,String [] adminSwapFlags) {
		this.doctorRecruitBiz.noticeRecruit(admitNotice,recruitFlows,adminSwapFlags);
		if(recruitFlows.length > 0){
			for(int i=0;i<recruitFlows.length;i++){
				ResDoctorRecruitWithBLOBs recruit = new ResDoctorRecruitWithBLOBs();
				recruit.setRecruitFlow(recruitFlows[i]);
				recruit.setTrainYear(trainYear);
				this.resDoctorRecruitBiz.updateDocrecruitByFlow(recruit);//变更医师培养年限

				ResDoctorRecruit rdr = resDoctorRecruitBiz.readResDoctorRecruit(recruitFlows[i]);
				//更新医师填报基地的信息
				ResDoctor doctor = new ResDoctor();
				doctor.setDoctorFlow(rdr.getDoctorFlow());
				doctor.setOrgFlow(rdr.getOrgFlow());
				doctor.setOrgName(rdr.getOrgName());
				doctor.setTrainingYears(rdr.getTrainYear());
				doctor.setTrainingSpeId(rdr.getSpeId());
				doctor.setTrainingSpeName(rdr.getSpeName());
				SysUser user = new SysUser();
				user.setUserFlow(rdr.getDoctorFlow());
				user.setOrgFlow(rdr.getOrgFlow());
				user.setOrgName(rdr.getOrgName());
//				if(null != adminSwapFlags && adminSwapFlags.length>0 && GlobalConstant.FLAG_Y.equals(adminSwapFlags[i])){//省厅调剂的学员基地录取后直接（培训）进过程
//					if(null != recruit){
//						doctor.setDoctorStatusId("Training");//过程初始状态
//						doctor.setDoctorStatusName("在培");
//					}
//				}
				userBiz.updateUser(user);
				doctorBiz.editDoctor(doctor);
			}
		}
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	/**
	 * 发送录取通知时查询已发数量是否超过录取总数
	 * @return
	 */
	@RequestMapping("/checkRecruitNum")
	@ResponseBody
	public String checkRecruitNum(String recruitFlow,String assignPlan,String numBanch){
		ResDoctorRecruit recruit = resDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
		String speId = recruit.getSpeId();
		String orgFlow = recruit.getOrgFlow();
		String regYear = InitConfig.getSysCfg("res_reg_year");
		Map<String,Object> paramMap2 = new HashMap<>();
		paramMap2.put("orgFlow",orgFlow);
		paramMap2.put("recruitYear",regYear);
		paramMap2.put("speId",speId);
		List<ResDoctorRecruit> doctorRecruits = resDoctorRecruitBiz.searchDoctorRecruitWithLine(paramMap2);
		int num=0;
		if(doctorRecruits!=null&&doctorRecruits.size()>0){
			for(ResDoctorRecruit recruit1:doctorRecruits){
				if(("Y".equals(recruit1.getRecruitFlag())&&StringUtil.isBlank(recruit1.getConfirmFlag()))
						||"Y".equals(recruit1.getConfirmFlag())){
					num++;
				}
			}
		}
		int banch = 0;
		if(StringUtil.isNotBlank(numBanch)){
			banch+=Integer.parseInt(numBanch)-1;
		}
		if(Integer.parseInt(assignPlan)<=num+banch){
			return "0";
		}else {
			return "1";
		}
	}
	/**
	 * 不录取
	 * @return
	 */
	@RequestMapping("/notrecruit")
	@ResponseBody
	public String notRecruit(String recruitFlow){
		ResDoctorRecruitWithBLOBs recruit = new ResDoctorRecruitWithBLOBs();
		recruit.setRecruitFlow(recruitFlow);
		recruit.setRecruitFlag(GlobalConstant.FLAG_N);
		this.resDoctorRecruitBiz.editDoctorRecruit(recruit);
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}

	/**
	 * 不发复试通知直接退回
	 * @return
	 */
	@RequestMapping("/returnBack")
	@ResponseBody
	public String returnBack(ResDoctorRecruitWithBLOBs recruit){
		this.resDoctorRecruitBiz.updateDocrecruitByFlow(recruit);
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	
	@RequestMapping(value="/noticeSwapMain")
	public String noticeSwapMain(String recruitFlow , String speId , Model model){
		String title = "调剂通知内容";
		model.addAttribute("title" , title);
		model.addAttribute("noticeType" , "swap");
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		String orgFlow = user.getOrgFlow();
	    String regYear = InitConfig.getSysCfg("res_reg_year");
		List<ResOrgSpeAssign> speAssignList = speAssignBiz.searchSpeAssign(orgFlow,regYear);
		//参数Map
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("assignYear",regYear);
		paramMap.put("orgFlow",orgFlow);
		//获取机构各专业下招录人数
		List<Map<String,Object>> docRecruitCounts = doctorRecruitBiz.getRecruitDocCount(paramMap);
		Map<String,Object> recruitDocMap = new HashMap<String,Object>();
		if(docRecruitCounts!=null && !docRecruitCounts.isEmpty()){
			for(Map<String,Object> map : docRecruitCounts){
				recruitDocMap.put((String)map.get("k"),map.get("v"));
			}
		}
		List<ResOrgSpeAssign> spes = new ArrayList<ResOrgSpeAssign>();
		for(ResOrgSpeAssign spe:speAssignList){
			if(!spe.getSpeId().equals(speId)  && spe.getAssignPlan()!=null && spe.getAssignPlan().compareTo(new BigDecimal(0))>0
					){
				if(recruitDocMap.get(orgFlow+spe.getSpeId())==null){
					spes.add(spe);
				}else if(spe.getAssignPlan().compareTo(new BigDecimal((int)recruitDocMap.get(orgFlow+spe.getSpeId())))>0){
					spes.add(spe);
				}
			}
		}
		model.addAttribute("spes" , spes);
		return "hbres/hospital/noticeRecruitMain";
	}
	
	@RequestMapping("/swaprecruit")
	@ResponseBody
	public String swapRecruit(String recruitFlow , String speFlow , String swapNotice, String trainYear){
		this.doctorRecruitBiz.swapRecruit(recruitFlow, speFlow, swapNotice, trainYear);
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	
	/**
	 * 各专业报考志愿人员统计
	 * @param model
	 * @return
	 */
//	@RequestMapping(value="retest")
//	public String retest(String key,Model model){
//		model.addAttribute("key",key);
//		Map<String , Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("dictTypeId", DictTypeEnum.DoctorTrainingSpe.getId());
//		paramMap.put("key", key);
//		List<SysDict> doctorTrainingSpeList = this.doctorRecruitBiz.searchTrainSpeList(paramMap);
//		model.addAttribute("doctorTrainingSpeList",doctorTrainingSpeList);
//		
//		int totalAssignPlan = 0;
//		int totalRetestNum = 0;
//		
//		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
//		String orgFlow = user.getOrgFlow();
//		if (StringUtil.isNotBlank(orgFlow)) {
//			String regYear = InitConfig.getSysCfg("res_reg_year");
//			//组织专业对应的招录计划数map
//			List<ResOrgSpeAssign> speAssignList = speAssignBiz.searchSpeAssign(orgFlow,regYear);
//			if(speAssignList!=null && speAssignList.size()>0){
//				Map<String,ResOrgSpeAssign> speAssignMap = new HashMap<String,ResOrgSpeAssign>();
//				for(ResOrgSpeAssign rosa : speAssignList){
//					speAssignMap.put(rosa.getSpeId(),rosa);
//					if (rosa.getAssignPlan() != null) {
//						totalAssignPlan += rosa.getAssignPlan().intValue();
//					}
//				}
//				model.addAttribute("speAssignMap",speAssignMap);
//			}
//			
//			//组织专业对应的填报志愿数和复试数map
//			ResDoctorRecruit recruit = new ResDoctorRecruit();
//			recruit.setOrgFlow(orgFlow);
//			recruit.setRecruitYear(regYear);
//			//recruit.setOrdinal(1);//第一志愿
//			List<ResDoctorRecruit> doctorRecruit = doctorRecruitBiz.searchDoctorRecruit(recruit);
//			if(doctorRecruit!=null && doctorRecruit.size()>0){
//				Map<String,Integer> recruitNumMap = new HashMap<String,Integer>();
//				Map<String,Integer> retestNumMap = new HashMap<String,Integer>();
//				for(ResDoctorRecruit rdr : doctorRecruit){
//					//填报志愿数
//					int recruitNum = 0;
//					if (recruitNumMap.get(rdr.getSpeId()) != null) {
//						recruitNum = recruitNumMap.get(rdr.getSpeId());
//					}
//					recruitNum++;
//					recruitNumMap.put(rdr.getSpeId(),recruitNum);
//					//复试数
//					if (GlobalConstant.FLAG_Y.equals(rdr.getRetestFlag())) {
//						int retestNum = 0;
//						if (retestNumMap.get(rdr.getSpeId()) != null) {
//							retestNum = retestNumMap.get(rdr.getSpeId());
//						}
//						retestNum++;
//						retestNumMap.put(rdr.getSpeId(),retestNum);
//						totalRetestNum++;
//					}
//				}
//				model.addAttribute("recruitNumMap",recruitNumMap);
//				model.addAttribute("retestNumMap",retestNumMap);
//			}
//		}
//		model.addAttribute("totalAssignPlan",totalAssignPlan);
//		model.addAttribute("totalRetestNum",totalRetestNum);
//		
//		return "hbres/hospital/retest";
//	}
	
//	@RequestMapping(value="retestUsers")
//	public String retestUsers(String speId,String assignPlan,String userName,String graduatedId,
//			Integer currentPage,Model model){
//		String speName = DictTypeEnum.DoctorTrainingSpe.getDictNameById(speId);
//		model.addAttribute("speName", speName);
//		model.addAttribute("speId", speId);
//		model.addAttribute("assignPlan", assignPlan);
//		model.addAttribute("currPage", currentPage==null?1:currentPage);
//		
//		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
//		String orgFlow = user.getOrgFlow();
//		String regYear = InitConfig.getSysCfg("res_reg_year");
//		
//		//复试人数
//		ResDoctorRecruit recruit = new ResDoctorRecruit();
//		recruit.setOrgFlow(orgFlow);
//		recruit.setRecruitYear(regYear);
//		recruit.setSpeId(speId);
//		//recruit.setOrdinal(1);//第一志愿
//		recruit.setRetestFlag(GlobalConstant.FLAG_Y);
//		int retestNum = doctorRecruitBiz.searchDoctorNum(recruit);
//		model.addAttribute("retestNum", retestNum);
//		
//		PageHelper.startPage(currentPage,10);
//		Map<String,Object> paramMap = new HashMap<String,Object>();
//		paramMap.put("orgFlow", orgFlow);
//		paramMap.put("recruitYear", regYear);
//		paramMap.put("speId", speId);
//		paramMap.put("ordinal", 1);//第一志愿
//		paramMap.put("userName", userName);
//		paramMap.put("graduatedId", graduatedId);
//		List<ResDoctorRecruitExt> doctorRecruitExts = doctorRecruitBiz.searchDoctorRecruitExt(paramMap);
//		model.addAttribute("doctorRecruitExts", doctorRecruitExts);
//		return "hbres/hospital/retestUsers";
//	}


	/**
	 * 通知复试（单个操作）
	 * @param recruitFlow
	 * @param retestNotice
	 * @return
	 */
	@RequestMapping(value="/noticeRetestSingle")
	@ResponseBody
	public String noticeRetestSingle(String recruitFlow,String retestNotice) {
		this.resDoctorRecruitBiz.noticeRetest(recruitFlow, retestNotice);
		return GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value="/noticeBackOpt")
	@ResponseBody
	public String noticeBackOpt(String recruitFlow, String flag) {
		this.resDoctorRecruitBiz.noticeBackOpt(recruitFlow,flag);
		return GlobalConstant.OPERATE_SUCCESSED;
	}
	
	/**
	@RequestMapping(value="/noticeRetestConfirm")
	@ResponseBody
	public String noticeRetestConfirm(String[] speIds) {
		String recruitYear = InitConfig.getSysCfg("res_reg_year");
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		String orgFlow = user.getOrgFlow();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("orgFlow", orgFlow);
		paramMap.put("recruitYear", recruitYear);
		paramMap.put("speIds", speIds);
		paramMap.put("ordinal", 1);//第一志愿
		String noticeNum = this.doctorRecruitBiz.searchNoticeDoctorNum(paramMap);
		return noticeNum;
	}*/
	
	/**
	 * 通知复试（批量操作）
	 * @param retestNotice
	 * @return
	 */
	@RequestMapping(value="/noticeRetestBatch")
	@ResponseBody
	public String noticeRetestBatch(String[] recruitFlows,String retestNotice) {
		this.doctorRecruitBiz.noticeRetestBatch(recruitFlows,retestNotice);
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	
//	@RequestMapping(value="gradeinput")
//	public String gradeinput(String key,
//			Integer currentPage,Model model){
//		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
//		String orgFlow = user.getOrgFlow();
//		String regYear = InitConfig.getSysCfg("res_reg_year");
//		
//		//复试人员列表
//		PageHelper.startPage(currentPage,10);
//		Map<String,Object> paramMap = new HashMap<String,Object>();
//		paramMap.put("orgFlow", orgFlow);
//		paramMap.put("recruitYear", regYear);
//		paramMap.put("retestFlag", GlobalConstant.FLAG_Y);
//		paramMap.put("ordinal", 1);//第一志愿
//		paramMap.put("key", key);
//		List<ResDoctorRecruitExt> doctorRecruitExts = doctorRecruitBiz.selectDoctorRecruitExt(paramMap);
//		model.addAttribute("doctorRecruitExts", doctorRecruitExts);
//		model.addAttribute("key", key);
//		return "hbres/hospital/gradeinput";
//	}
	
//	@RequestMapping("/inputGrade")
//	@ResponseBody
//	public String inputGrade(String doctorFlow,String result,String resultType){
//		String regYear = InitConfig.getSysCfg("res_reg_year");
//		ResDoctorRecruit docRecruit = null;
//		ResDoctorRecruit recruit = new ResDoctorRecruit();
//		recruit.setDoctorFlow(doctorFlow);
//		recruit.setRecruitYear(regYear);
//		List<ResDoctorRecruit> docRecruits = doctorRecruitBiz.searchDoctorRecruit(recruit);
//		if (docRecruits != null && docRecruits.size() > 0) {
//			docRecruit = docRecruits.get(0);
//		}
//		if (docRecruit != null) {
//			BigDecimal totalResult = docRecruit.getTotleResult();
//			BigDecimal examResult = docRecruit.getExamResult();
//			BigDecimal operResult = docRecruit.getOperResult();
//			BigDecimal auditonResult = docRecruit.getAuditionResult();
//			BigDecimal res = null;
//			if (StringUtil.isNotBlank(result)) {
//				res = new BigDecimal(result);
//			}
//			//更新数据
//			if ("operResult".equals(resultType)) {
//				operResult = res;
//				docRecruit.setOperResult(operResult);
//			}
//			if ("auditionResult".equals(resultType)) {
//				auditonResult = res;
//				docRecruit.setAuditionResult(auditonResult);
//			}
//			
//			//计算总成绩（笔试成绩*40%+技能操作考试*40%+面试成绩*20%）
//			if (examResult != null && operResult != null && auditonResult != null) {
//				totalResult = examResult.multiply(new BigDecimal(0.4))
//						.add(operResult.multiply(new BigDecimal(0.4)))
//						.add(auditonResult.multiply(new BigDecimal(0.2)));
//				totalResult = totalResult.setScale(1,BigDecimal.ROUND_HALF_UP);
//				docRecruit.setTotleResult(totalResult);
//			} else {
//				docRecruit.setTotleResult(null);
//			}
//			this.doctorRecruitBiz.editRecruitUnSelective(docRecruit);
//			return GlobalConstant.OPRE_SUCCESSED;
//		}
//		return GlobalConstant.OPRE_FAIL;
//	}
	
//	@RequestMapping(value="recruitNotice")
//	public String recruitNotice(Model model){
//		Map<String , Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("dictTypeId", DictTypeEnum.DoctorTrainingSpe.getId());
//		List<SysDict> doctorTrainingSpeList = this.doctorRecruitBiz.searchTrainSpeList(paramMap);
//		model.addAttribute("doctorTrainingSpeList",doctorTrainingSpeList);
//		
//		int totalAssignPlan = 0;
//		int totalRetestNum = 0;
//		int totalRecruitNum = 0;
//		
//		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
//		String orgFlow = user.getOrgFlow();
//		if (StringUtil.isNotBlank(orgFlow)) {
//			String regYear = InitConfig.getSysCfg("res_reg_year");
//			//组织专业对应的招录计划数map
//			List<ResOrgSpeAssign> speAssignList = speAssignBiz.searchSpeAssign(orgFlow,regYear);
//			if(speAssignList!=null && speAssignList.size()>0){
//				Map<String,ResOrgSpeAssign> speAssignMap = new HashMap<String,ResOrgSpeAssign>();
//				for(ResOrgSpeAssign rosa : speAssignList){
//					speAssignMap.put(rosa.getSpeId(),rosa);
//					if (rosa.getAssignPlan() != null) {
//						totalAssignPlan += rosa.getAssignPlan().intValue();
//					}
//				}
//				model.addAttribute("speAssignMap",speAssignMap);
//			}
//			
//			//组织专业对应的填报志愿数和复试数map
//			ResDoctorRecruit recruit = new ResDoctorRecruit();
//			recruit.setOrgFlow(orgFlow);
//			recruit.setRecruitYear(regYear);
//			//recruit.setOrdinal(1);//第一志愿
//			List<ResDoctorRecruit> doctorRecruit = doctorRecruitBiz.searchDoctorRecruit(recruit);
//			if(doctorRecruit!=null && doctorRecruit.size()>0){
//				Map<String,Integer> recruitNumMap = new HashMap<String,Integer>();
//				Map<String,Integer> retestNumMap = new HashMap<String,Integer>();
//				Map<String,Integer> recruitYNumMap = new HashMap<String,Integer>();
//				for(ResDoctorRecruit rdr : doctorRecruit){
//					//填报志愿数
//					int recruitNum = 0;
//					if (recruitNumMap.get(rdr.getSpeId()) != null) {
//						recruitNum = recruitNumMap.get(rdr.getSpeId());
//					}
//					recruitNum++;
//					recruitNumMap.put(rdr.getSpeId(),recruitNum);
//					//复试数
//					if (GlobalConstant.FLAG_Y.equals(rdr.getRetestFlag())) {
//						int retestNum = 0;
//						if (retestNumMap.get(rdr.getSpeId()) != null) {
//							retestNum = retestNumMap.get(rdr.getSpeId());
//						}
//						retestNum++;
//						retestNumMap.put(rdr.getSpeId(),retestNum);
//						totalRetestNum++;
//					}
//					//预录取数
//					if (GlobalConstant.FLAG_Y.equals(rdr.getRecruitFlag())) {
//						int recruitYNum = 0;
//						if (recruitYNumMap.get(rdr.getSpeId()) != null) {
//							recruitYNum = recruitYNumMap.get(rdr.getSpeId());
//						}
//						recruitYNum++;
//						recruitYNumMap.put(rdr.getSpeId(),recruitYNum);
//						totalRecruitNum++;
//					}
//				}
//				model.addAttribute("recruitNumMap",recruitNumMap);
//				model.addAttribute("retestNumMap",retestNumMap);
//				model.addAttribute("recruitYNumMap",recruitYNumMap);
//			}
//		}
//		model.addAttribute("totalAssignPlan",totalAssignPlan);
//		model.addAttribute("totalRetestNum",totalRetestNum);
//		model.addAttribute("totalRecruitNum",totalRecruitNum);
//		
//		return "hbres/hospital/recruitNotice";
//	}
//	
//	@RequestMapping(value="recruitUsers")
//	public String recruitUsers(String speId,String assignPlan,String key,Model model){
//		String speName = DictTypeEnum.DoctorTrainingSpe.getDictNameById(speId);
//		model.addAttribute("speName", speName);
//		model.addAttribute("speId", speId);
//		model.addAttribute("assignPlan", assignPlan);
//		
//		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
//		String orgFlow = user.getOrgFlow();
//		String regYear = InitConfig.getSysCfg("res_reg_year");
//		
//		//复试人数
//		ResDoctorRecruit recruit = new ResDoctorRecruit();
//		recruit.setOrgFlow(orgFlow);
//		recruit.setRecruitYear(regYear);
//		recruit.setSpeId(speId);
//		//recruit.setOrdinal(1);//第一志愿
//		recruit.setRetestFlag(GlobalConstant.FLAG_Y);
//		int retestNum = doctorRecruitBiz.searchDoctorNum(recruit);
//		model.addAttribute("retestNum", retestNum);
//		
//		//预录取人数
//		ResDoctorRecruit recruit1 = new ResDoctorRecruit();
//		recruit1.setOrgFlow(orgFlow);
//		recruit1.setRecruitYear(regYear);
//		recruit1.setSpeId(speId);
//		//recruit1.setOrdinal(1);//第一志愿
//		recruit1.setRetestFlag(GlobalConstant.FLAG_Y);
//		recruit1.setRecruitFlag(GlobalConstant.FLAG_Y);
//		int recruitNum = doctorRecruitBiz.searchDoctorNum(recruit1);
//		model.addAttribute("recruitNum", recruitNum);
//		
//		Map<String,Object> paramMap = new HashMap<String,Object>();
//		paramMap.put("orgFlow", orgFlow);
//		paramMap.put("recruitYear", regYear);
//		paramMap.put("speId", speId);
//		paramMap.put("ordinal", 1);//第一志愿
//		paramMap.put("retestFlag", GlobalConstant.FLAG_Y);
//		paramMap.put("key", key);
//		List<ResDoctorRecruitExt> doctorRecruitExts = doctorRecruitBiz.readDoctorRecruitExt(paramMap);
//		model.addAttribute("doctorRecruitExts", doctorRecruitExts);
//		model.addAttribute("key", key);
//		return "hbres/hospital/recruitUsers";
//	}
	
	
	
//	/**
//	 * 录取结果
//	 * @return
//	 */
//	@RequestMapping(value={"/recruitResultList"})
//	public String recruitResultList(Model model){
//		Map<String , Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("dictTypeId", DictTypeEnum.DoctorTrainingSpe.getId());
//		List<SysDict> doctorTrainingSpeList = this.doctorRecruitBiz.searchTrainSpeList(paramMap);
//		model.addAttribute("doctorTrainingSpeList", doctorTrainingSpeList);
//		
//		//计划招收人数map
//		int assignPlanCount = 0;
//		int recruitCount = 0;
//		int confirmCount = 0;
//		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
//		String regYear = InitConfig.getSysCfg("res_reg_year");
//		List<ResOrgSpeAssign> speAssignList = speAssignBiz.searchSpeAssign(orgFlow,regYear);
//		if(speAssignList != null && !speAssignList.isEmpty()){
//			Map<String, ResOrgSpeAssign> assignPlanMap = new HashMap<String, ResOrgSpeAssign>();
//			for(ResOrgSpeAssign rosa : speAssignList){
//				assignPlanMap.put(rosa.getSpeId(), rosa);
//				if(rosa.getAssignPlan() != null) {//计划招收总人数
//					assignPlanCount += rosa.getAssignPlan().intValue();
//				}
//			}
//			model.addAttribute("assignPlanMap", assignPlanMap);
//		}
//		model.addAttribute("assignPlanCount", assignPlanCount);
//		//预录取
//		ResDoctorRecruit recruit = new ResDoctorRecruit();
//		recruit.setOrgFlow(orgFlow);
//		recruit.setRecruitYear(regYear);
//		//recruit.setOrdinal(1);//第一志愿
//		recruit.setRecruitFlag(GlobalConstant.RECORD_STATUS_Y);
//		List<ResDoctorRecruit> doctorRecruitList = doctorRecruitBiz.searchDoctorRecruit(recruit);
//		if(doctorRecruitList != null && !doctorRecruitList.isEmpty()){
//			Map<String, Integer> recruitCountMap = new HashMap<String, Integer>();
//			Map<String, Integer> confirmCountMap = new HashMap<String, Integer>();
//			for(ResDoctorRecruit rdr : doctorRecruitList){
//				//预录取人数
//				if(StringUtil.isNotBlank("")){//调剂录取   rdr.getSwapSpeId()
////					if(recruitCountMap.get(rdr.getSwapSpeId()) != null){
////						recruitCount = recruitCountMap.get(rdr.getSwapSpeId());
////					}
////					recruitCount++;
////					recruitCountMap.put(rdr.getSwapSpeId(), recruitCount);
////					//确认录取人数
////					if(GlobalConstant.FLAG_Y.equals(rdr.getConfirmFlag())){
////						if(confirmCountMap.get(rdr.getSwapSpeId()) != null) {
////							confirmCount = confirmCountMap.get(rdr.getSwapSpeId());
////						}
////						confirmCount++;
////						confirmCountMap.put(rdr.getSwapSpeId(), confirmCount);
////					}
//				}else{//第一志愿录取
//					if(recruitCountMap.get(rdr.getSpeId()) != null) {
//						recruitCount = recruitCountMap.get(rdr.getSpeId());
//					}
//					recruitCount++;
//					recruitCountMap.put(rdr.getSpeId(), recruitCount);
//					//确认录取人数
//					if(GlobalConstant.FLAG_Y.equals(rdr.getConfirmFlag())){
//						if(confirmCountMap.get(rdr.getSpeId()) != null) {
//							confirmCount = confirmCountMap.get(rdr.getSpeId());
//						}
//						confirmCount++;
//						confirmCountMap.put(rdr.getSpeId(), confirmCount);
//					}
//				}
//			}
//			model.addAttribute("recruitCountMap", recruitCountMap);
//			model.addAttribute("confirmCountMap", confirmCountMap);
//		}
//		model.addAttribute("recruitCount", recruitCount);
//		model.addAttribute("confirmCount", confirmCount);
//		
//		return "hbres/hospital/recruitResultList";
//	}
	
	
//	/**
//	 * 确认录取--人员名单
//	 * @param speId
//	 * @param assignPlan
//	 * @param key
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value="/confirmUserList")
//	public String confirmUserList(String speId, String key, Model model){
//		model.addAttribute("speName", DictTypeEnum.DoctorTrainingSpe.getDictNameById(speId));
//		
//		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
//		String regYear = InitConfig.getSysCfg("res_reg_year");
//		//人员名单
//		Map<String,Object> paramMap = new HashMap<String,Object>();
//		paramMap.put("orgFlow", orgFlow);
//		paramMap.put("recruitYear", regYear);
//		paramMap.put("speId", speId);
//		paramMap.put("ordinal", 1);//第一志愿录取
//		paramMap.put("swapFlag", GlobalConstant.FLAG_Y);//调剂录取
//		paramMap.put("swapSpeId", speId);
//		paramMap.put("recruitFlag", GlobalConstant.FLAG_Y);
//		paramMap.put("key", key);
//		//paramMap.put("confirmFlag", GlobalConstant.FLAG_Y);
//		List<ResDoctorRecruitExt> doctorRecruitExts = doctorRecruitBiz.readDoctorRecruitExt(paramMap);
//		model.addAttribute("doctorRecruitExts", doctorRecruitExts);
//		return "hbres/hospital/confirmUserList";
//	}
//	
	@RequestMapping("/exportdoctor/{type}")
	public void exportDoctor(@PathVariable String type, HttpServletResponse response) throws Exception {
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		String orgFlow = user.getOrgFlow();
		String regYear = InitConfig.getSysCfg("res_reg_year");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("orgFlow", orgFlow);
		paramMap.put("recruitYear", regYear);
		paramMap.put("returnBackFlag", "N");
		String typeCN="";
		if("fill".equals(type)){
			paramMap.put("signStu","Y");
			typeCN = "填报";
		}
		if("retest".equals(type)){
			paramMap.put("retestFlag", "Y");
			typeCN = "复试通知";
		}
		if("reutrnBack".equals(type)){
			paramMap.put("returnBackFlag", "Y");
			typeCN = "退回";
		}
		if("hasResult".equals(type)){
			paramMap.put("hasResult", "Y");
			typeCN = "已导入成绩";
		}
		List<ResDoctorRecruitExt> doctorRecruitExts = this.doctorRecruitBiz.selectDoctorRecruitExt(paramMap);
		String[] titles = new String[]{
				"userName:姓名",
				"sexName:性别",
				"userBirthday:出生日期",
				"cretTypeName:证件类型",//new
				"idNo:证件号码",
				"nationName:民族",//new
				"userPhone:手机号",
				"userEmail:邮箱",
				"userAddress:通讯地址",//new
				"emergencyName:紧急联系人",//new
				"emergencyPhone:紧急联系联系方式",//new
				"emergencyRelation:与本人关系",//new
				"doctorLicenseFlag:是否取得执业医师资格证",//new
				"qualifiedNo:医师资格证号",
				"doctorTypeName:人员类型",
				"workOrgName:工作单位名称",
				"medicalHeaithOrgName:医疗卫生机构",//newblob
				"hospitalAttrName:医院属性",//newblob
				"hospitalCategoryName:医院类别",//newblob
				"baseAttributeName:单位性质",//newblob
				"basicHealthOrgName:基层医疗卫生机构类型",//newblob
				"isYearGraduate:是否为应届生",//new
				"isGeneralOrderOrientationTrainee:是否全科订单定向学员",//newblob
				"graduatedName:本科毕业院校",
				"graduationTime:本科毕业时间",
				"specialized:本科毕业专业",
				"educationName:本科学位",
				"hasTakenMasterExam:是否参加研究生考试",//newblob
				"masterExamResult:研究生考试成绩",//newblob
				"isMaster:是否为硕士",//newblob
				"masterGraSchoolName:硕士毕业院校",//newblob
				"masterGraTime:硕士毕业时间",//newblob
				"masterMajor:硕士毕业专业",//newblob
				"masterDegreeName:硕士学位",//newblob
				"masterDegreeTypeName:硕士学位类型",//newblob
				"isDoctor:是否博士",//newblob
				"doctorGraSchoolName:博士毕业院校",//newblob
				"doctorGraTime:博士毕业时间",//newblob
				"doctorMajor:博士毕业专业",//newblob
				"doctorDegreeName:博士学位",//newblob
				"doctorDegreeTypeName:博士学位类型",//newblob
				"educationName:最高学历",
				"highestMajor:最高学历毕业专业",//new
//				"graduatedName:毕业院校",
//				"specialized:毕业专业",
//				"graduationTime:毕业时间",
//				"educationName:学历",
				"examResult:笔试成绩",
				"speName:填报专业",
				"speId:填报专业代码",
				"swap:服从调剂"
		};
		List<HospitalDoctorExportInfo> dataList = new ArrayList<HospitalDoctorExportInfo>();
		HospitalDoctorExportInfo hdei = null;
		for(ResDoctorRecruitExt rdre:doctorRecruitExts){
			if(RecruitTypeEnum.Fill.getId().equals(rdre.getRecruitTypeId())){
				hdei = new HospitalDoctorExportInfo();
				String userName = rdre.getSysUser().getUserName();
				if(RecDocTypeEnum.Graduate.getId().equals(rdre.getDoctor().getDoctorTypeId())){
					userName = "*"+userName;
				}else if(HbResDegreeCategoryEnum.ClinicMaster.getId().equals(rdre.getDoctor().getDegreeCategoryId()) || HbResDegreeCategoryEnum.ClinicDoctor.getId().equals(rdre.getDoctor().getDegreeCategoryId())){
					userName = "*"+userName;
				}
				hdei.setUserName(userName);
				hdei.setSexName(rdre.getSysUser().getSexName());
				hdei.setUserBirthday(rdre.getSysUser().getUserBirthday());
				hdei.setCretTypeName(rdre.getSysUser().getCretTypeName());
				hdei.setIdNo(rdre.getSysUser().getIdNo());
				hdei.setNationName(rdre.getSysUser().getNationName());
				hdei.setUserAddress(rdre.getSysUser().getUserAddress());
				hdei.setEmergencyName(rdre.getDoctor().getEmergencyName());
				hdei.setEmergencyPhone(rdre.getDoctor().getEmergencyPhone());
				hdei.setEmergencyRelation(rdre.getDoctor().getEmergencyRelation());
				hdei.setDoctorLicenseFlag(rdre.getDoctor().getDoctorLicenseFlag());
				hdei.setIsYearGraduate(rdre.getDoctor().getIsYearGraduate());
				hdei.setUserPhone(rdre.getSysUser().getUserPhone());
				hdei.setUserEmail(rdre.getSysUser().getUserEmail());
				hdei.setGraduatedName(rdre.getDoctor().getGraduatedName());
				hdei.setGraduationTime(rdre.getDoctor().getGraduationTime());
				hdei.setEducationName(rdre.getSysUser().getDegreeName());
				hdei.setDegreeCategoryName(rdre.getDoctor().getDegreeCategoryName());
				hdei.setDoctorTypeName(rdre.getDoctor().getDoctorTypeName());
				hdei.setWorkOrgName(rdre.getDoctor().getWorkOrgName());
				hdei.setQualifiedNo(rdre.getDoctor().getDoctorLicenseNo());
				hdei.setExamResult(String.valueOf(rdre.getExamResult()==null? "":rdre.getExamResult()));
				hdei.setSpeName(rdre.getSpeName());
				hdei.setSpeId(rdre.getSpeId());
				hdei.setSwap(rdre.getSwapFlag());
				hdei.setEducationName(rdre.getSysUser().getEducationName());
				hdei.setHighestMajor(DictTypeEnum.GraduateMajor.getDictNameById(rdre.getDoctor().getSpecialized()));
				//大字段信息
				PubUserResume pubUserResume = userResumeBiz.readPubUserResume(rdre.getSysUser().getUserFlow());
				if(pubUserResume != null){
					String xmlContent =  pubUserResume.getUserResume();
					if(StringUtil.isNotBlank(xmlContent)){
						//xml转换成JavaBean
						BaseUserResumeExtInfoForm extInfo=null;
						extInfo=userResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class);
						hdei.setMedicalHeaithOrgName(extInfo.getMedicalHeaithOrgName());
						hdei.setHospitalAttrName(extInfo.getHospitalAttrName());
						hdei.setHospitalCategoryName(extInfo.getHospitalCategoryName());
						hdei.setBaseAttributeName(extInfo.getBaseAttributeName());
						hdei.setBasicHealthOrgName(extInfo.getBasicHealthOrgName());
						hdei.setIsGeneralOrderOrientationTrainee(extInfo.getIsGeneralOrderOrientationTrainee());
						hdei.setHasTakenMasterExam("Y".equals(extInfo.getHasTakenMasterExam())?"是":"否");
						hdei.setMasterExamResult(extInfo.getMasterExamResult());
						hdei.setIsMaster(extInfo.getIsMaster());
						hdei.setMasterGraSchoolName(extInfo.getMasterGraSchoolName());
						hdei.setMasterGraTime(extInfo.getMasterGraTime());
						hdei.setMasterMajor(extInfo.getMasterMajor());
						hdei.setMasterDegreeName(extInfo.getMasterDegreeName());
						hdei.setMasterDegreeTypeName("Y".equals(extInfo.getIsMaster())?extInfo.getMasterDegreeTypeName():"");
						hdei.setIsDoctor(extInfo.getIsDoctor());
						hdei.setDoctorGraSchoolName(extInfo.getDoctorGraSchoolName());
						hdei.setDoctorGraTime(extInfo.getDoctorGraTime());
						hdei.setDoctorMajor(extInfo.getDoctorMajor());
						hdei.setDoctorDegreeName(extInfo.getDoctorDegreeName());
						hdei.setDoctorDegreeTypeName("Y".equals(extInfo.getIsDoctor())?("1".equals(extInfo.getDoctorDegreeType())?"专业型":"科学型"):"");
						hdei.setSpecialized(extInfo.getZbkbySpe());
					}
				}
				dataList.add(hdei);
			}
		}
	    String fileName = "基地"+typeCN+"学员名单.xls";
	    fileName = URLEncoder.encode(fileName, "UTF-8");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");  
	    response.setContentType("application/octet-stream;charset=UTF-8");  
	    ExcleUtile.exportSimpleExcle(titles, dataList, HospitalDoctorExportInfo.class, response.getOutputStream());
	}

	@RequestMapping("/exportdoctorHasResult")
	public void exportdoctorHasResult(HttpServletResponse response) throws Exception {
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		String orgFlow = user.getOrgFlow();
		String regYear = InitConfig.getSysCfg("res_reg_year");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("orgFlow", orgFlow);
		paramMap.put("recruitYear", regYear);
		paramMap.put("returnBackFlag", "N");
		String typeCN="";
		paramMap.put("hasResult", "Y");
		typeCN = "已导入成绩";
		List<ResDoctorRecruitExt> doctorRecruitExts = this.doctorRecruitBiz.selectDoctorRecruitExt(paramMap);
		String[] titles = new String[]{
				"userName:姓名",
				"idNo:证件号码",
				"examResult:笔试成绩",
				"auditionResult:面试成绩",
				"operResult:技能成绩",
				"sumResult:总成绩",
				"speName:填报专业",
				"speId:填报专业代码"
		};
		List<HospitalDoctorExportInfo> dataList = new ArrayList<HospitalDoctorExportInfo>();
		HospitalDoctorExportInfo hdei = null;
		for(ResDoctorRecruitExt rdre:doctorRecruitExts){
			if(RecruitTypeEnum.Fill.getId().equals(rdre.getRecruitTypeId())){
				hdei = new HospitalDoctorExportInfo();
				String userName = rdre.getSysUser().getUserName();
				if(RecDocTypeEnum.Graduate.getId().equals(rdre.getDoctor().getDoctorTypeId())){
					userName = "*"+userName;
				}else if(HbResDegreeCategoryEnum.ClinicMaster.getId().equals(rdre.getDoctor().getDegreeCategoryId()) || HbResDegreeCategoryEnum.ClinicDoctor.getId().equals(rdre.getDoctor().getDegreeCategoryId())){
					userName = "*"+userName;
				}
				hdei.setUserName(userName);
				hdei.setIdNo(rdre.getSysUser().getIdNo());
				hdei.setExamResult(String.valueOf(rdre.getExamResult()==null? "":rdre.getExamResult()));
				hdei.setAuditionResult(String.valueOf(rdre.getAuditionResult()==null? "":rdre.getAuditionResult()));
				hdei.setOperResult(String.valueOf(rdre.getOperResult()==null? "":rdre.getOperResult()));
				hdei.setSumResult(String.valueOf(rdre.getTotleResult()==null? "":rdre.getTotleResult()));
				hdei.setSpeName(rdre.getSpeName());
				hdei.setSpeId(rdre.getSpeId());
				dataList.add(hdei);
			}
		}
		String fileName = typeCN+"学员名单.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcle(titles, dataList, HospitalDoctorExportInfo.class, response.getOutputStream());
	}

	@RequestMapping("/exportRecruit")
	public void exportRecruit(HttpServletResponse response,HttpServletRequest request,String charge,String regYear) throws Exception {
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		String orgFlow = user.getOrgFlow();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(!"charge".equals(charge)){
			paramMap.put("orgFlow", orgFlow);
		}
		if(StringUtil.isBlank(regYear)){
			regYear = InitConfig.getSysCfg("res_reg_year");
		}
		paramMap.put("recruitYear", regYear);
		List<ResDoctorRecruitExt> doctorRecruitExts = this.doctorRecruitBiz.selectDoctorRecruitExt(paramMap);
		List<HospitalDoctorExportInfo> dataList = new ArrayList<HospitalDoctorExportInfo>();
		HospitalDoctorExportInfo hdei = null;
		if(null != doctorRecruitExts && doctorRecruitExts.size()>0){
			for(ResDoctorRecruitExt rdre:doctorRecruitExts){
				if (GlobalConstant.FLAG_Y.equals(rdre.getAdmitFlag()) && GlobalConstant.FLAG_Y.equals(rdre.getRecruitFlag()) && GlobalConstant.FLAG_Y.equals(rdre.getConfirmFlag()) && (RecruitTypeEnum.Fill.getId().equals(rdre.getRecruitTypeId())
						|| RecruitTypeEnum.Swap.getId().equals(rdre.getRecruitTypeId()))){//医院录取且 学员确认或者愿意调剂
					hdei = new HospitalDoctorExportInfo();
					String userName = rdre.getSysUser().getUserName();
					if(RecDocTypeEnum.Graduate.getId().equals(rdre.getDoctor().getDoctorTypeId())){
						userName = "*"+userName;
					}else if(HbResDegreeCategoryEnum.ClinicMaster.getId().equals(rdre.getDoctor().getDegreeCategoryId()) || HbResDegreeCategoryEnum.ClinicDoctor.getId().equals(rdre.getDoctor().getDegreeCategoryId())){
						userName = "*"+userName;
					}
					hdei.setUserName(userName);
					hdei.setSexName(rdre.getSysUser().getSexName());
					hdei.setUserBirthday(rdre.getSysUser().getUserBirthday());
					hdei.setCretTypeName(rdre.getSysUser().getCretTypeName());
					hdei.setIdNo(rdre.getSysUser().getIdNo());
					hdei.setNationName(rdre.getSysUser().getNationName());
					hdei.setUserAddress(rdre.getSysUser().getUserAddress());
					hdei.setEmergencyName(rdre.getDoctor().getEmergencyName());
					hdei.setEmergencyPhone(rdre.getDoctor().getEmergencyPhone());
					hdei.setEmergencyRelation(rdre.getDoctor().getEmergencyRelation());
					hdei.setDoctorLicenseFlag(rdre.getDoctor().getDoctorLicenseFlag());
					hdei.setIsYearGraduate(rdre.getDoctor().getIsYearGraduate());
					hdei.setUserPhone(rdre.getSysUser().getUserPhone());
					hdei.setUserEmail(rdre.getSysUser().getUserEmail());
					hdei.setGraduatedName(rdre.getDoctor().getGraduatedName());
					hdei.setGraduationTime(rdre.getDoctor().getGraduationTime());
					hdei.setEducationName(rdre.getSysUser().getDegreeName());
					hdei.setDegreeCategoryName(rdre.getDoctor().getDegreeCategoryName());
					hdei.setDoctorTypeName(rdre.getDoctor().getDoctorTypeName());
					hdei.setWorkOrgName(rdre.getDoctor().getWorkOrgName());
					hdei.setQualifiedNo(rdre.getDoctor().getDoctorLicenseNo());
					hdei.setSessionNumber(rdre.getDoctor().getSessionNumber());
					hdei.setOrgName(rdre.getOrgName());
					hdei.setSpeName(rdre.getSpeName());
					hdei.setSpeId(rdre.getSpeId());
					hdei.setSwap(rdre.getSwapFlag());
					hdei.setEducationName(rdre.getSysUser().getEducationName());
					hdei.setHighestMajor(DictTypeEnum.GraduateMajor.getDictNameById(rdre.getDoctor().getSpecialized()));
					//大字段信息
					PubUserResume pubUserResume = userResumeBiz.readPubUserResume(rdre.getSysUser().getUserFlow());
					if(pubUserResume != null){
						String xmlContent =  pubUserResume.getUserResume();
						if(StringUtil.isNotBlank(xmlContent)){
							//xml转换成JavaBean
							BaseUserResumeExtInfoForm extInfo=null;
							extInfo=userResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class);
							hdei.setMedicalHeaithOrgName(extInfo.getMedicalHeaithOrgName());
							hdei.setHospitalAttrName(extInfo.getHospitalAttrName());
							hdei.setHospitalCategoryName(extInfo.getHospitalCategoryName());
							hdei.setBaseAttributeName(extInfo.getBaseAttributeName());
							hdei.setBasicHealthOrgName(extInfo.getBasicHealthOrgName());
							hdei.setIsGeneralOrderOrientationTrainee(extInfo.getIsGeneralOrderOrientationTrainee());
							hdei.setHasTakenMasterExam("Y".equals(extInfo.getHasTakenMasterExam())?"是":"否");
							hdei.setMasterExamResult(extInfo.getMasterExamResult());
							hdei.setIsMaster(extInfo.getIsMaster());
							hdei.setMasterGraSchoolName(extInfo.getMasterGraSchoolName());
							hdei.setMasterGraTime(extInfo.getMasterGraTime());
							hdei.setMasterMajor(extInfo.getMasterMajor());
							hdei.setMasterDegreeName(extInfo.getMasterDegreeName());
							hdei.setMasterDegreeTypeName("Y".equals(extInfo.getIsMaster())?extInfo.getMasterDegreeTypeName():"");
							hdei.setIsDoctor(extInfo.getIsDoctor());
							hdei.setDoctorGraSchoolName(extInfo.getDoctorGraSchoolName());
							hdei.setDoctorGraTime(extInfo.getDoctorGraTime());
							hdei.setDoctorMajor(extInfo.getDoctorMajor());
							hdei.setDoctorDegreeName(extInfo.getDoctorDegreeName());
							hdei.setDoctorDegreeTypeName("Y".equals(extInfo.getIsDoctor())?("1".equals(extInfo.getDoctorDegreeType())?"专业型":"科学型"):"");
							hdei.setSpecialized(extInfo.getZbkbySpe());
						}
					}
					Double examResult = (rdre.getExamResult()==null? 0 :rdre.getExamResult()).doubleValue();
					Double auditionResult = (rdre.getAuditionResult()==null? 0 :rdre.getAuditionResult()).doubleValue();
					Double operResult = (rdre.getOperResult()==null? 0 :rdre.getOperResult()).doubleValue();
					Double sumResult = (examResult*0.4 + auditionResult * 0.2 + operResult * 0.4);
					hdei.setExamResult(String.valueOf(examResult));
					hdei.setAuditionResult(String.valueOf(auditionResult));
					hdei.setOperResult(String.valueOf(operResult));
					hdei.setSumResult(String.format("%.2f", sumResult));
					dataList.add(hdei);
				}
			}
		}
		String[] titles = new String[]{
				"userName:姓名",
				"sexName:性别",
				"userBirthday:出生日期",
				"cretTypeName:证件类型",//new
				"idNo:证件号码",
				"nationName:民族",//new
				"userPhone:手机号",
				"userEmail:邮箱",
				"userAddress:通讯地址",//new
				"emergencyName:紧急联系人",//new
				"emergencyPhone:紧急联系联系方式",//new
				"emergencyRelation:与本人关系",//new
				"doctorLicenseFlag:是否取得执业医师资格证",//new
				"qualifiedNo:医师资格证号",
				"doctorTypeName:人员类型",
				"workOrgName:工作单位名称",
				"medicalHeaithOrgName:医疗卫生机构",//newblob
				"hospitalAttrName:医院属性",//newblob
				"hospitalCategoryName:医院类别",//newblob
				"baseAttributeName:单位性质",//newblob
				"basicHealthOrgName:基层医疗卫生机构类型",//newblob
				"isYearGraduate:是否为应届生",//new
				"isGeneralOrderOrientationTrainee:是否全科订单定向学员",//newblob
				"graduatedName:本科毕业院校",
				"graduationTime:本科毕业时间",
				"specialized:本科毕业专业",
				"educationName:本科学位",
				"hasTakenMasterExam:是否参加研究生考试",//newblob
				"masterExamResult:研究生考试成绩",//newblob
				"isMaster:是否为硕士",//newblob
				"masterGraSchoolName:硕士毕业院校",//newblob
				"masterGraTime:硕士毕业时间",//newblob
				"masterMajor:硕士毕业专业",//newblob
				"masterDegreeName:硕士学位",//newblob
				"masterDegreeTypeName:硕士学位类型",//newblob
				"isDoctor:是否博士",//newblob
				"doctorGraSchoolName:博士毕业院校",//newblob
				"doctorGraTime:博士毕业时间",//newblob
				"doctorMajor:博士毕业专业",//newblob
				"doctorDegreeName:博士学位",//newblob
				"doctorDegreeTypeName:博士学位类型",//newblob
				"educationName:最高学历",
				"highestMajor:最高学历毕业专业",//new
				"sessionNumber:培训届别",
				"orgName:录取基地",
				"speName:录取专业",
				"speId:录取专业代码",
				"swap:服从调剂",
				"examResult:笔试成绩",
				"operResult:操作成绩",
				"auditionResult:面试成绩",
				"sumResult:总成绩"
		};
		String fileName = user.getOrgName()+"录取学员名单";
		String userAgent = request.getHeader("User-Agent");
		if (userAgent.indexOf("Firefox") != -1){
			response.addHeader("Content-Disposition", "attachment; filename="
					+ new String(fileName.getBytes("UTF-8"), "iso8859-1")+".xls");
		}else{
			response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "utf-8")+".xls");
		}
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcle(titles, dataList, HospitalDoctorExportInfo.class, response.getOutputStream());
	}

	/**
	 * 开放给基地的特殊操作
	 * @param recruitFlow
	 * @param model
	 * @return
	 */
	@RequestMapping("/getSpecifiedOper")
	public String getSpecifiedOper(String recruitFlow , Model model){
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		String orgFlow = user.getOrgFlow();
		String regYear = InitConfig.getSysCfg("res_reg_year");
		List<ResOrgSpeAssign> spes = this.speAssignBiz.searchSpeAssign(orgFlow, regYear);
		model.addAttribute("spes", spes);
		return "hbres/hospital/specifiedoper";
	}
	
	/**
	 * 基地帮学员确认
	 * @param recruitFlow
	 * @return
	 */
	@RequestMapping("/hospitalOperDoctorConfirm")
	@ResponseBody
	public String hospitalOperDoctorConfirm(String recruitFlow){
		ResDoctorRecruitWithBLOBs recruit = new ResDoctorRecruitWithBLOBs();
		recruit.setRecruitFlow(recruitFlow);
		recruit.setConfirmFlag(GlobalConstant.FLAG_Y);
		this.doctorRecruitBiz.modResDoctorRecruitByRecruitFlow(recruit , false);
		return GlobalConstant.OPRE_SUCCESSED;
	}
	
	/**
	 * 基地帮学员调专业
	 * @param recruitFlow
	 * @param speId
	 * @return
	 */
	@RequestMapping("/hospitalOperChangeSpe")
	@ResponseBody
	public String hospitalOperChangeSpe(String recruitFlow , String speId){
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		String orgFlow = user.getOrgFlow();
		String regYear = InitConfig.getSysCfg("res_reg_year");
		ResOrgSpeAssign currSpe = speAssignBiz.searchSpeAssign(orgFlow, regYear , speId);
		ResDoctorRecruitWithBLOBs recruit = new ResDoctorRecruitWithBLOBs();
		recruit.setRecruitFlow(recruitFlow);
		recruit.setSpeId(speId);
		recruit.setSpeName(currSpe.getSpeName());
		this.doctorRecruitBiz.modResDoctorRecruitByRecruitFlow(recruit , false);
		return GlobalConstant.OPRE_SUCCESSED;
	}
	
	/**
	 * 基地帮学员回到不录取状态
	 * @param recruitFlow
	 * @return
	 */
	@RequestMapping("/hospitalOperNoRecruit")
	@ResponseBody
	public String hospitalOperNoRecruit(String recruitFlow){
		ResDoctorRecruitWithBLOBs recruit = (ResDoctorRecruitWithBLOBs) this.resDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
		recruit.setRecruitFlag(GlobalConstant.FLAG_N);
		recruit.setAdmitFlag(GlobalConstant.FLAG_N);
		recruit.setAdmitNotice("");
		recruit.setConfirmFlag("");
		this.doctorRecruitBiz.modResDoctorRecruitByRecruitFlow(recruit , true);
		return GlobalConstant.OPRE_SUCCESSED;
	}

	//修改学员端是否展示复试分数
	@RequestMapping("/showScore")
	@ResponseBody
	public String showScore(String value){
		SysCfg cfg = new SysCfg();
		SysUser user = GlobalContext.getCurrentUser();
		String orgFlow = user.getOrgFlow();
		String cfgCode = "hbShowScore_"+orgFlow;
		cfg.setCfgCode(cfgCode);
		cfg.setCfgValue(value);
		SysCfg sc = cfgBiz.read(cfgCode);
		if(null==sc){
			cfgBiz.add(cfg);
		}else {
			cfgBiz.mod(cfg);
		}
		return "1";
	}
}
