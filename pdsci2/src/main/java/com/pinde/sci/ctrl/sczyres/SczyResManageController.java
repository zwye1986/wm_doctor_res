package com.pinde.sci.ctrl.sczyres;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.jsres.IJsResUserBlackBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sczyres.DoctorGraduationBiz;
import com.pinde.sci.biz.sczyres.DoctorRecruitBiz;
import com.pinde.sci.biz.sczyres.DoctorSingupBiz;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.ResBaseMapper;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.enums.jszy.JszyResTrainYearEnum;
import com.pinde.sci.enums.res.RegStatusEnum;
import com.pinde.sci.enums.sczyres.SczyRecDocTypeEnum;
import com.pinde.sci.enums.sczyres.SczyResOrgLevelEnum;
import com.pinde.sci.enums.sczyres.SpeCatEnum;
import com.pinde.sci.enums.sys.CertificateTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.sczyres.ExtInfoForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.DateCfgMsg;
import com.pinde.sci.model.res.ResDoctorRecruitExt;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/sczyres/manage")
public class SczyResManageController extends GeneralController{

	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private IRecruitCfgBiz recruitCfgBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IResOrgSpeAssignBiz speAssignBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private DoctorRecruitBiz doctorRecruitBiz;
	@Autowired
	private SysLogMapper logMapper;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private DoctorGraduationBiz doctorGraduationBiz;
	@Autowired
	private IResJointOrgBiz resJointBiz;
	@Autowired
	private ICfgBiz cfgBiz;
	@Autowired
	private DoctorSingupBiz doctorSingupBiz;
	@Autowired
	private ResBaseMapper resBaseMapper;
	@Autowired
	private IJsResUserBlackBiz blackBiz;
	@Autowired
	private IResDoctorRecruitBiz recruitBiz;
	@Autowired
	private DoctorSingupBiz docSinupBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	
	@RequestMapping("/home")
	public String home(Integer currentPage , Model model){
		String regYear = InitConfig.getSysCfg("res_reg_year");
		InxInfo info = new InxInfo();
		PageHelper.startPage(currentPage,10);
		List<InxInfo> messages = this.noticeBiz.findNotice(info);
		model.addAttribute("messages",messages);
		ResDoctor doctor = new ResDoctor();
		//统计已报名
		doctor.setDoctorStatusId("");
		doctor.setSessionNumber(regYear);
		int singupCount = resDoctorBiz.findDoctorCountInOrg(doctor);
		model.addAttribute("singupCount" , singupCount);
		//统计审核通过
		doctor.setDoctorStatusId(RegStatusEnum.Passed.getId());
		int passedCount = resDoctorBiz.findDoctorCountInOrg(doctor);
		model.addAttribute("passedCount" , passedCount);
		//统计录取人数
		ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setRecruitFlag(GlobalConstant.FLAG_Y);
		recruit.setRecruitYear(regYear);
		int recruitCount = this.doctorRecruitBiz.findCountByRecruit(recruit);
		model.addAttribute("recruitCount" , recruitCount);
		return "sczyres/manage/index";
	}
	
	/**
	 * 招录设置
	 */
	@RequestMapping(value="/datecfg")
	public String dateCfg(Model model){
		String regYear = InitConfig.getSysCfg("res_reg_year");
		ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(regYear);
		model.addAttribute("recruitCfg" , recruitCfg);
		
		String currDate = DateUtil.getCurrDate();
		DateCfgMsg regDateCfgMsg = new DateCfgMsg(recruitCfg);
		regDateCfgMsg.setRegDateMsg(currDate);
		
		DateCfgMsg swapDateCfgMsg = new DateCfgMsg(recruitCfg);
		swapDateCfgMsg.setSwapDateMsg(currDate);

//		DateCfgMsg wishDateCfgMsg = new DateCfgMsg(recruitCfg);
//		wishDateCfgMsg.setWishDateMsg(currDate);

		DateCfgMsg admitDateCfgMsg = new DateCfgMsg(recruitCfg);
		admitDateCfgMsg.setAdmitDateMsg(currDate);

		DateCfgMsg graduationDateCfgMsg = new DateCfgMsg(recruitCfg);
		graduationDateCfgMsg.setGraduationDateMsg(currDate);
		
		model.addAttribute("regDateCfgMsg" , regDateCfgMsg);
		model.addAttribute("swapDateCfgMsg" , swapDateCfgMsg);
//		model.addAttribute("wishDateCfgMsg" , wishDateCfgMsg);
		model.addAttribute("admitDateCfgMsg" , admitDateCfgMsg);
		model.addAttribute("graduationDateCfgMsg" , graduationDateCfgMsg);

		//可选专业
		SysCfg cfg = cfgBiz.read("scres_spe_allow");
		if(cfg!=null){
			String spes = cfg.getCfgValue();
			if(StringUtil.isNotBlank(spes)){
				String[] speList = spes.split(",");
				model.addAttribute("speList",speList);
			}
		}

		//学员减免默认设置
		SysCfg cfg2 = cfgBiz.read("scres_allow_reduction");
		if(cfg2!=null){
			model.addAttribute("allowReduction",cfg2.getCfgValue());
		}
		return "sczyres/manage/dateCfg";
	}
	
	
	@RequestMapping("/savedatecfg")
	@ResponseBody
	public String saveDateCfg(ResRecruitCfg recruitCfg){
		this.recruitCfgBiz.saveRecruitCfg(recruitCfg);
	    return GlobalConstant.OPERATE_SUCCESSED;	
	}
	/**
	 * 招录计划（不包括指标维护）
	 */
	@RequestMapping(value="/plan")
	public String plan(String assignYear , String source , String orgFlow , Model model){
		SysOrgExample example = new SysOrgExample();
		SysOrgExample.Criteria criteria =  example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId()).andOrgLevelIdIsNotNull();
		List<String> types = new ArrayList<String>();
		types.add(OrgTypeEnum.Hospital.getId());
		criteria.andOrgTypeIdIn(types);
		example.setOrderByClause("ORDINAL");
		List<SysOrg> orgList = orgBiz.searchOrgByExample(example);
		model.addAttribute("orgList",orgList);
		
		int totalNum = 0;
		if(StringUtil.isNotBlank(assignYear)){
			List<ResOrgSpeAssign> speAssignList = speAssignBiz.searchSpeAssign(assignYear);
			if(speAssignList!=null && speAssignList.size()>0){
				Map<String,Integer> speAssignNumMap = new HashMap<String,Integer>();
				for(ResOrgSpeAssign rosa : speAssignList){
					if (rosa.getAssignPlan() != null) {
						int num = 0;
						if (speAssignNumMap.get(rosa.getOrgFlow()) != null) {
							num = speAssignNumMap.get(rosa.getOrgFlow());
						}
						num += rosa.getAssignPlan().intValue();
						speAssignNumMap.put(rosa.getOrgFlow(),num);
						if(!GlobalConstant.USER_LIST_LOCAL.equals(source) || rosa.getOrgFlow().equals(orgFlow)){
							totalNum += rosa.getAssignPlan().intValue();	
						}
					}
				}
				model.addAttribute("speAssignNumMap",speAssignNumMap);
			}
		}
		ResDoctorRecruit recruit=new ResDoctorRecruit();
		List<SysOrg>orgList2=orgBiz.searchSysOrg();
		Map<String,Integer> recruitCountMap = new HashMap<String,Integer>();
		for(SysOrg rdr : orgList2){
			recruit.setOrgFlow(rdr.getOrgFlow());
			recruit.setConfirmFlag(GlobalConstant.FLAG_Y);
			recruit.setRecruitYear(assignYear);
			recruit.setReturnBackFlag("Y");
			int recruitCount=doctorRecruitBiz.findCountByRecruit(recruit);
			recruitCountMap.put(rdr.getOrgFlow(), recruitCount);
		}
		model.addAttribute("recruitCountMap", recruitCountMap);
		Map<String,Integer> recruitCountAllMap = new HashMap<String,Integer>();
		ResDoctorRecruit recruit2=new ResDoctorRecruit();
		for(SysOrg rdr : orgList2){
			recruit2.setOrgFlow(rdr.getOrgFlow());
			recruit2.setConfirmFlag(GlobalConstant.FLAG_Y);
			recruit2.setRecruitYear(assignYear);
			int recruitCount=doctorRecruitBiz.findCountByRecruit(recruit2);
			recruitCountAllMap.put(rdr.getOrgFlow(), recruitCount);
		}
		model.addAttribute("recruitCountAllMap", recruitCountAllMap);
		model.addAttribute("totalNum",totalNum);
		
		return "sczyres/manage/plan";
	}
	/**
	 * 招录指标维护
	 */
	@RequestMapping(value="/planEdit")
	public String planEdit(String assignYear , String source , String orgFlow , Model model){
		SysOrgExample example = new SysOrgExample();
		SysOrgExample.Criteria criteria =  example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<String> types = new ArrayList<String>();
		types.add(OrgTypeEnum.Hospital.getId());
		criteria.andOrgTypeIdIn(types);
		example.setOrderByClause("ORDINAL");
		List<SysOrg> orgList = orgBiz.searchOrgByExample(example);
		model.addAttribute("orgList",orgList);
		int totalNum = 0;
		if(StringUtil.isNotBlank(assignYear)){
			List<ResOrgSpeAssign> speAssignList = speAssignBiz.searchSpeAssign(assignYear);
			if(speAssignList!=null && speAssignList.size()>0){
				Map<String,Integer> speAssignNumMap = new HashMap<String,Integer>();
				for(ResOrgSpeAssign rosa : speAssignList){
					if (rosa.getAssignPlan() != null) {
						int num = 0;
						if (speAssignNumMap.get(rosa.getOrgFlow()) != null) {
							num = speAssignNumMap.get(rosa.getOrgFlow());
						}
						num += rosa.getAssignPlan().intValue();
						speAssignNumMap.put(rosa.getOrgFlow(),num);
						if(!GlobalConstant.USER_LIST_LOCAL.equals(source) || rosa.getOrgFlow().equals(orgFlow)){
							totalNum += rosa.getAssignPlan().intValue();
						}
					}
				}
				model.addAttribute("speAssignNumMap",speAssignNumMap);
			}
		}
//		ResDoctorRecruit recruit=new ResDoctorRecruit();
//		List<SysOrg>orgList2=orgBiz.searchSysOrg();
//		Map<String,Integer> recruitCountMap = new HashMap<String,Integer>();
//		for(SysOrg rdr : orgList2){
//			recruit.setOrgFlow(rdr.getOrgFlow());
//			recruit.setRecruitFlag(GlobalConstant.FLAG_Y);
//			recruit.setRecruitYear(assignYear);
//			int recruitCount=doctorRecruitBiz.findCountByRecruit(recruit);
//			recruitCountMap.put(rdr.getOrgFlow(), recruitCount);
//		}
//		model.addAttribute("recruitCountMap", recruitCountMap);
		model.addAttribute("totalNum",totalNum);

		return "sczyres/manage/planEdit";
	}
	@RequestMapping(value="/planInfo")
	public String planInfo(String assignYear,String orgFlow,Model model){
		SysOrgExample example = new SysOrgExample();
		SysOrgExample.Criteria criteria =  example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<String> types = new ArrayList<String>();
		types.add(OrgTypeEnum.Hospital.getId());
		criteria.andOrgTypeIdIn(types);
		example.setOrderByClause("ORDINAL");
		List<SysOrg> orgList = orgBiz.searchOrgByExample(example);
		model.addAttribute("orgList",orgList);
		model.addAttribute("orgFlow",orgFlow);
		
		if(StringUtil.isNotBlank(assignYear)){
			List<ResOrgSpeAssign> speAssignList = speAssignBiz.searchSpeAssign(orgFlow,assignYear);
			if(speAssignList!=null && speAssignList.size()>0){
				Map<String,ResOrgSpeAssign> speAssignMap = new HashMap<String,ResOrgSpeAssign>();
				for(ResOrgSpeAssign rosa : speAssignList){
					speAssignMap.put(rosa.getSpeId(),rosa);
				}
				model.addAttribute("speAssignMap",speAssignMap);
			}
		}
		
		return "sczyres/manage/planInfo";
	}
	
	/**
	 * 招录计划保存
	 */
	@RequestMapping(value="/savePlan")
	@ResponseBody
	public String savePlan(ResOrgSpeAssign speAssign){
		if(speAssign!=null){
			if(StringUtil.isNotBlank(speAssign.getSpeId())){
				speAssign.setSpeName(SpeCatEnum.getNameById(speAssign.getSpeId()));
			}
			if (StringUtil.isNotBlank(speAssign.getRecordFlow())) {
				ResOrgSpeAssign assign = speAssignBiz.findSpeAssignByFlow(speAssign.getRecordFlow());
				assign.setAssignPlan(speAssign.getAssignPlan());
				speAssign = assign;
			}
			if(GlobalConstant.ZERO_LINE!=speAssignBiz.editSpeAssignUnSelective(speAssign)){
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}
	@RequestMapping(value="/spe")
	public String spe(Model model) throws Exception{
		return "sczyres/manage/spe";
	}
	
	/**
	 * 基地维护
	 */
	@RequestMapping(value="/org")
	public String orgCfg(String isCountry,Model model){
		SysOrgExample example = new SysOrgExample();
		SysOrgExample.Criteria criteria =  example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<String> types = new ArrayList<String>();
		types.add(OrgTypeEnum.Hospital.getId());
		criteria.andOrgTypeIdIn(types);
		example.setOrderByClause("ORDINAL");
		List<SysOrg> orgList = orgBiz.searchOrgByExample(example);
		model.addAttribute("orgList",orgList);
		return "sczyres/manage/org";
	}

	/**
	 *  医院维护
	 * */
	@RequestMapping(value="/hospitalList",method={RequestMethod.GET,RequestMethod.POST})
	public String hospitalList(SysOrg sysOrg, Integer currentPage, HttpServletRequest request, Model model){
//		PageHelper.startPage(currentPage,getPageSize(request));
		SysOrgExample orgExample = new SysOrgExample();
		SysOrgExample.Criteria criteria = orgExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).
				andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId()).andOrgLevelIdIsNotNull();
		if(StringUtil.isNotBlank(sysOrg.getOrgName())){
			criteria.andOrgNameLike("%"+sysOrg.getOrgName()+"%");
		}
		orgExample.setOrderByClause("ORDINAL");
		List<SysOrg> orgList = this.orgBiz.searchOrgByExample(orgExample);
//		List<SysOrg> orgList = orgBiz.searchOrderBy(sysOrg);
		if(orgList!=null && orgList.size()>0){
			model.addAttribute("orgList",orgList);
			List<String> orgFlows = new ArrayList<String>();
			for(SysOrg org : orgList){
				orgFlows.add(org.getOrgFlow());
			}
			String roleFlow = InitConfig.getSysCfg("res_admin_role_flow");
			List<SysUser> userList = userBiz.searchUserByRoleAndOrgFlows(roleFlow,orgFlows);
			if(userList!=null && userList.size()>0){
				Map<String,SysUser> userMap = new HashMap<String, SysUser>();
				for(SysUser user : userList){
					if(userMap.get(user.getOrgFlow())==null){
						userMap.put(user.getOrgFlow(),user);
					}
				}
				model.addAttribute("userMap",userMap);
			}
		}
		//协同医院
		List<ResJointOrg> jointList = resJointBiz.searchJointOrgAll();
		if(jointList!=null &&jointList.size()>0){
			Map<String,List<ResJointOrg>> jointOrgMap = new HashMap<String,List<ResJointOrg>>();
			for(ResJointOrg jointOrg : jointList){
				String key = jointOrg.getOrgFlow();
				if(jointOrgMap.get(key)==null){
					List<ResJointOrg> jointOrgList = new ArrayList<ResJointOrg>();
					jointOrgList.add(jointOrg);
					jointOrgMap.put(key,jointOrgList);
				}else{
					jointOrgMap.get(key).add(jointOrg);
				}
			}
			model.addAttribute("jointOrgMap",jointOrgMap);
		}

		return "sczyres/manage/org";
	}

	@RequestMapping("/noticemanage")
	public String noticeManage(Integer currentPage , Model model){
		InxInfo info = new InxInfo();
		PageHelper.startPage(currentPage,5);
		List<InxInfo> infos = this.noticeBiz.findNotice(info);
		model.addAttribute("infos",infos);
		return "sczyres/manage/notice";
	}
	
	
	@RequestMapping("/showDocRecruit")
	public String showDocRecruit(String orgFlow , String confirmFlag , Model model,String roleFlag,String assignYear,String planFlag){
//		String regYear = InitConfig.getSysCfg("res_reg_year");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("recruitYear", assignYear);
		paramMap.put("recruitFlag", GlobalConstant.FLAG_Y);
		if (StringUtil.isNotBlank(orgFlow)) {
			paramMap.put("orgFlow", orgFlow);
		}
		if (StringUtil.isNotBlank(planFlag)) {
			paramMap.put("planFlag", planFlag);
		}
//		if(GlobalConstant.FLAG_Y.equals(confirmFlag)){
			paramMap.put("confirmFlag", "Y");
//		}
//		if(GlobalConstant.NULL.equals(confirmFlag)){
//			paramMap.put("confirmFlagIsNull", confirmFlag);
//		}
		List<ResDoctorRecruitExt> doctorRecruitExts = this.doctorRecruitBiz.selectDoctorRecruitExt(paramMap);
		model.addAttribute("doctorRecruitExts" , doctorRecruitExts);
		ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setOrgFlow(orgFlow);
		recruit .setRecruitFlag(GlobalConstant.FLAG_Y);
		recruit.setRecruitYear(assignYear);
		int recruitCount=doctorRecruitBiz.findCountByRecruit(recruit);
		//录取人数
		model.addAttribute("recruitCount", recruitCount);
		recruit .setConfirmFlag(GlobalConstant.FLAG_Y);
		int confirmCount=doctorRecruitBiz.findCountByRecruit(recruit);
		//确认录取人数
		model.addAttribute("confirmCount", confirmCount);
		SysOrgExample orgExample = new SysOrgExample();
		orgExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId()).andOrgLevelIdIsNotNull();;
		orgExample.setOrderByClause("ORDINAL");
		List<SysOrg> orgs = this.orgBiz.searchOrgByExample(orgExample);
		model.addAttribute("hospitals", orgs);
	    return "sczyres/manage/showRecruitDoctor";
	}
	
	/**
	 * 查看招录的整体情况
	 * @param orgFlow
	 * @param speId
	 * @param model
	 * @return
	 */
	@RequestMapping("/showRecruit")
	public String showRecruit(String orgFlow , String speId , Model model){
		String regYear = InitConfig.getSysCfg("res_reg_year");
		//查询出所有基地
		SysOrgExample orgExample = new SysOrgExample();
		orgExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId());
		orgExample.setOrderByClause("ORDINAL");
		List<SysOrg> orgs = this.orgBiz.searchOrgByExample(orgExample);
		model.addAttribute("hospitals", orgs);
		List<SysDict> spes = new ArrayList<SysDict>();
		//查询出所有专业
		List<SysDict> zySpes = DictTypeEnum.sysListDictMap.get(DictTypeEnum.ZySpe.getId());
		for(SysDict zyspe:zySpes){
			SysDict spe = new SysDict();
			spe.setDictId(SpeCatEnum.ChineseMedicine.getId()+"."+zyspe.getDictId());
			spe.setDictName(zyspe.getDictName());
			spes.add(spe);
		}
//		List<SysDict> zyqkSpes = DictTypeEnum.sysListDictMap.get(DictTypeEnum.ZyqkSpe.getId());
//		for(SysDict zyqkspe:zyqkSpes){
//			SysDict spe = new SysDict();
//			spe.setDictId(SpeCatEnum.TCMGeneral.getId()+"."+zyqkspe.getDictId());
//			spe.setDictName(zyqkspe.getDictName());
//			spes.add(spe);
//		}
		model.addAttribute("spes" , spes);
		ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setRecruitFlag(GlobalConstant.FLAG_Y);
		Map<String , String> countResultMap = new HashMap<String, String>();
		//如果orgFlow不为空，speId为空 查询该基地下所有专业的录取人数
		if(StringUtil.isNotBlank(orgFlow)&&StringUtil.isBlank(speId)){
			recruit.setOrgFlow(orgFlow);
			for(SysDict spe:spes){
				String[] spedata = spe.getDictId().split("\\.");
				recruit.setCatSpeId(spedata[0]);
				recruit.setSpeId(spedata[1]);
				recruit.setRecruitYear(regYear);
				int count = this.doctorRecruitBiz.findCountByRecruit(recruit);
				countResultMap.put(spe.getDictId(), String.valueOf(count));
			}
			model.addAttribute("countResultMap", countResultMap);
			
		}
		//如果orgFlow为空，speId不为空 ， 查询所有基地该专业的录取人数
		if(StringUtil.isBlank(orgFlow)&&StringUtil.isNotBlank(speId)){
			String[] spedata = speId.split("\\.");
			recruit.setCatSpeId(spedata[0]);
			recruit.setSpeId(spedata[1]);
			for(SysOrg org:orgs){
				recruit.setOrgFlow(org.getOrgFlow());
				recruit.setRecruitYear(regYear);
				int count = this.doctorRecruitBiz.findCountByRecruit(recruit);
				countResultMap.put(org.getOrgFlow(), String.valueOf(count));
			}
			model.addAttribute("countResultMap", countResultMap);
		}
		//如果orgFlow,speId都不为空，查询该基地该专业的录取人数
		if(StringUtil.isNotBlank(orgFlow)&&StringUtil.isNotBlank(speId)){
			String[] spedata = speId.split("\\.");
			recruit.setCatSpeId(spedata[0]);
			recruit.setSpeId(spedata[1]);
			recruit.setOrgFlow(orgFlow);
			recruit.setRecruitYear(regYear);
			int count = this.doctorRecruitBiz.findCountByRecruit(recruit);
			model.addAttribute("recruitCount" , count);
		}
		return "sczyres/manage/jidirecruitinfo";
	}
	
	/**
	 * 学员调剂
	 * @return
	 */
	@RequestMapping("/swap")
	public String swap(String key,Model model){
		String regYear = InitConfig.getSysCfg("res_reg_year");
		ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(regYear);
		String currDate = DateUtil.getCurrDate();
		DateCfgMsg regDateCfgMsg = new DateCfgMsg(recruitCfg);
		regDateCfgMsg.setSwapDateMsg(currDate);
		if(!regDateCfgMsg.getAllowSwap()){
			model.addAttribute("msg" , "当前调剂"+regDateCfgMsg.getMsg()) ;
			return "sczyres/doctor/notallowswap";
		}
		
		//首先查询出未被录取的学员
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("swapFlag","Y");
		paramMap.put("recruitTypeId","Fill");
		paramMap.put("recruitYear", regYear);
		paramMap.put("doctorStatusId", RegStatusEnum.Passed.getId());
		paramMap.put("scresSwap", "Y");
		if(StringUtil.isNotBlank(key)){
			paramMap.put("key", key);
		}
		List<ResDoctorRecruitExt> doctorRecruitExts = this.doctorRecruitBiz.selectDoctorRecruitExt(paramMap);
		model.addAttribute("doctorRecruitExts", doctorRecruitExts);	
		if(!doctorRecruitExts.isEmpty()){
			List<String> noRecruitDoctors = new ArrayList<String>();
			for(ResDoctorRecruitExt dre:doctorRecruitExts){
				noRecruitDoctors.add(dre.getDoctorFlow());
			}
			Map<String , ResDoctorRecruit> swapRecruitMap = this.doctorRecruitBiz.findSwapDoctorRecruit(noRecruitDoctors);
			model.addAttribute("swapRecruitMap" , swapRecruitMap);
		}
		return "sczyres/manage/swaprecruit";
	}
	
	@RequestMapping("/openSwapPage")
	public String openSwapPage(String doctorFlow , String recruitFlow , Model model){
		ResDoctorRecruit recruit = this.doctorRecruitBiz.readResDoctorRecruit(recruitFlow);
		model.addAttribute("recruit" , recruit);
		ScresRecuritMoreSpe search = new ScresRecuritMoreSpe();
		search.setRecruitFlow(recruitFlow);
		List<ScresRecuritMoreSpe> moreSpeList = doctorRecruitBiz.searchMoreSpe(search);
		model.addAttribute("moreSpeList",moreSpeList);
		ScresRecuritMoreSpe defaultSpe = new ScresRecuritMoreSpe();
		if(moreSpeList!=null&&moreSpeList.size()>0){
			for(ScresRecuritMoreSpe spe:moreSpeList){
				if(spe.getOrderNum().equals("2")){
					defaultSpe = spe;
					break ;
				}
			}
		}
		model.addAttribute("defaultSpe",defaultSpe);
		SysUser user = this.userBiz.readSysUser(recruit.getDoctorFlow());
		model.addAttribute("user" , user);
		SysOrgExample orgExample = new SysOrgExample();
		orgExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).
				andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId()).andOrgLevelIdIsNotNull();
		orgExample.setOrderByClause("ORDINAL");
		List<SysOrg> orgs = this.orgBiz.searchOrgByExample(orgExample);
		//过滤有名额的基地
		String regYear = InitConfig.getSysCfg("res_reg_year");
		List<ResOrgSpeAssign> allCatspes = this.doctorRecruitBiz.findSpeAssign(null, regYear);
		Map<String,Object> allOrgMap = new HashMap<>();
		if(allCatspes!=null&&allCatspes.size()>0){
			for(ResOrgSpeAssign catspe:allCatspes){
				String orgFlow = catspe.getOrgFlow();
				if(catspe.getAssignPlan()!=null&&catspe.getAssignPlan().compareTo(new BigDecimal(0))>0&&
						catspe.getAssignPlan().compareTo(catspe.getAssignReal()==null?new BigDecimal(0):catspe.getAssignReal())>0){
					allOrgMap.put(orgFlow,true);
				}
			}
		}
		List<SysOrg> hospitals = new ArrayList<>();
		for(SysOrg org:orgs){
			if(allOrgMap.get(org.getOrgFlow())!=null&&(boolean)allOrgMap.get(org.getOrgFlow())){
				hospitals.add(org);
			}
		}
		model.addAttribute("hospitals", hospitals);
		//可选专业
		SysCfg cfg = cfgBiz.read("scres_spe_allow");
		List<String> allowSpes = null;
		if(cfg!=null){
			String spes = cfg.getCfgValue();
			if(StringUtil.isNotBlank(spes)){
				String[] speList = spes.split(",");
				allowSpes = Arrays.asList(speList);
			}
		}
		List<ResOrgSpeAssign> allCatspes2 = this.doctorRecruitBiz.findSpeAssign(recruit.getOrgFlow(), regYear);
		List<ResOrgSpeAssign> catspes = new ArrayList<>();
		if(allCatspes2!=null&&allCatspes2.size()>0){
			for(ResOrgSpeAssign assign:allCatspes2){
				if(allowSpes!=null&&allowSpes.contains(assign.getSpeId())&&
						(assign.getAssignPlan()!=null&&assign.getAssignPlan().compareTo(new BigDecimal(0))>0&&
								assign.getAssignPlan().compareTo(assign.getAssignReal()==null?new BigDecimal(0):assign.getAssignReal())>0)
						){
					catspes.add(assign);
				}
			}
		}
		model.addAttribute("catspes" , catspes);
		return "sczyres/manage/swapRecruitMain";
	}
	
	@RequestMapping("/swapRecruit")
	@ResponseBody
	public String swapRecruit(ResDoctorRecruitWithBLOBs recruit,String oldRecruitFlow){
		recruit.setDoctorStatusName("在培");
		recruit.setSwapFlag("Y");
		this.doctorRecruitBiz.swapRecruit(recruit,oldRecruitFlow);
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}

	@RequestMapping("/auditApply")
	@ResponseBody
	public String auditApply(String flag,ScresGraduationApply apply,String remarkContent){
		apply.setChargeStatusId(flag);
		apply.setChargeRemark(remarkContent);
		if("0".equals(flag)){
			apply.setDoctorStatusId("0");
			apply.setOrgStatusId("-1");
			apply.setXtOrgStatusId("-1");
			apply.setChargeStatusId("0");
		}
		doctorGraduationBiz.saveApply(apply);
		return "1";
	}

	@RequestMapping("/exportExl")
	void exportExl(HttpServletResponse response,String role,String catSpeId,String isMakeUp) throws Exception {
		SysUser currentUser = GlobalContext.getCurrentUser();
		String orgFlow = currentUser.getOrgFlow();
		String graduationYear = InitConfig.getSysCfg("res_graduation_year");

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("graduationYear",graduationYear);
		paramMap.put("role",role);

		List<String> orgFlows = new ArrayList<>();
		if("hospital".equals(role)){
			SysOrg org = orgBiz.readSysOrg(orgFlow);
			if(SczyResOrgLevelEnum.Main.getId().equals(org.getOrgLevelId())){//主基地
				List<ResJointOrg> jointOrgs = resJointBiz.searchResJointByOrgFlow(orgFlow);
				if(jointOrgs!=null&&jointOrgs.size()>0){
					for(ResJointOrg jointOrg:jointOrgs){
						String jointOrgFlow = jointOrg.getJointOrgFlow();
						orgFlows.add(jointOrgFlow);
					}
				}
				orgFlows.add(orgFlow);
				paramMap.put("orgFlows",orgFlows);
			}
			if(SczyResOrgLevelEnum.Joint.getId().equals(org.getOrgLevelId())){//协同基地
				paramMap.put("orgFlow",orgFlow);
			}
		}
		paramMap.put("catSpeId",catSpeId);
		paramMap.put("isMakeUp",isMakeUp);
		List<Map<String,Object>> resultMapList = doctorGraduationBiz.searchDocrtor4Exl(paramMap);
		if(resultMapList!=null)
		{
			int c=1;
			for(Map<String,Object> r:resultMapList)
			{
				String doctorFlow= (String) r.get("DOCTOR_FLOW");
				String TRAINING_SPE_ID= (String) r.get("TRAINING_SPE_ID");
				r.put("ISBU","否");
				r.put("ISTHEORY","否");
				r.put("ISSKILL","否");
				r.put("RN",c++);
				PubUserResume resume = userResumeBiz.readPubUserResume(doctorFlow);
				ExtInfoForm extInfo=null;
				if(resume!=null){
					String resumeXml = resume.getUserResume();
					extInfo= docSinupBiz.parseExtInfoXml(resumeXml);
					if(extInfo!=null)
					{
						if("Y".equals(extInfo.getIsMakeUp()))
						{
							r.put("ISBU","是");
							if("1".equals(extInfo.getMakeUpTypeId()))
							{
								r.put("ISTHEORY","是");
							}
							if("2".equals(extInfo.getMakeUpTypeId()))
							{
								r.put("ISSKILL","是");
							}
							if("3".equals(extInfo.getMakeUpTypeId()))
							{
								r.put("ISTHEORY","是");
								r.put("ISSKILL","是");
							}
						}
					}
				}
				String DOCTOR_LICENSE_NO="";
				if("1".equals(TRAINING_SPE_ID)||"2".equals(TRAINING_SPE_ID))
				{
					DOCTOR_LICENSE_NO= (String) r.get("DOCTOR_LICENSE_NO");
				}else if("3".equals(TRAINING_SPE_ID))
				{
					if(extInfo!=null)
					{

						DOCTOR_LICENSE_NO=extInfo.getAssistantQualificationCertificateCode();
					}
				}
				r.put("DOCTOR_LICENSE_NO",DOCTOR_LICENSE_NO);
			}
		}
		String[] headLines = null;
		headLines = new String[]{
				graduationYear+"年培训基地考生结业信息汇总表",
				"培训基地（盖章）："+currentUser.getOrgName()+"        年   月   日          填报人：        联系电话：          ",
		};
		String fileName = graduationYear+"年培训基地考生结业考核信息汇总表.xls";
		String titles[] = {
				"RN:序号",
				"ORG_NAME:培训基地",
				"DOCTOR_NAME:姓名",
				"SEX_NAME:性别",
				"CRET_TYPE_NAME:证件类型",
				"ID_NO:身份证号",
				"TRAINING_SPE_NAME:报考专业",
				"EDUCATION_NAME:学历",
				"TRAINING_START_DATE:培训开始时间",
				"TRAINING_END_DATE:培训结束时间",
				"DOCTOR_LICENSE_NO:医师资格证号",
				"ISBU:是否补考",
				"ISTHEORY:补考理论",
				"ISSKILL:补考技能",
				"REMARK:备注"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleWithHeadlin(headLines, titles, resultMapList, response.getOutputStream());
	}

	@RequestMapping("/exportExl2")
	void exportExl2(HttpServletResponse response,String role) throws Exception {
		SysUser currentUser = GlobalContext.getCurrentUser();
		String orgFlow = currentUser.getOrgFlow();
		String graduationYear = InitConfig.getSysCfg("res_graduation_year");

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("graduationYear",graduationYear);
		paramMap.put("role",role);

		List<String> orgFlows = new ArrayList<>();
		if("hospital".equals(role)){
			SysOrg org = orgBiz.readSysOrg(orgFlow);
			if(SczyResOrgLevelEnum.Main.getId().equals(org.getOrgLevelId())){//主基地
				List<ResJointOrg> jointOrgs = resJointBiz.searchResJointByOrgFlow(orgFlow);
				if(jointOrgs!=null&&jointOrgs.size()>0){
					for(ResJointOrg jointOrg:jointOrgs){
						String jointOrgFlow = jointOrg.getJointOrgFlow();
						orgFlows.add(jointOrgFlow);
					}
				}
				orgFlows.add(orgFlow);
				paramMap.put("orgFlows",orgFlows);
			}
			if(SczyResOrgLevelEnum.Joint.getId().equals(org.getOrgLevelId())){//协同基地
				paramMap.put("orgFlow",orgFlow);
			}
		}
		List<Map<String,Object>> resultMapList = doctorGraduationBiz.searchDocrtor4Exl(paramMap);

		String[] headLines = null;
		headLines = new String[]{
				graduationYear+"年住培考生导入信息模板",
				"培训基地（盖章）："+currentUser.getOrgName()+"        填表时间：          填报人：        联系电话：          ",
		};
		String fileName = graduationYear+"年住培考生导入信息模板.xls";
		String titles[] = {
				"KAODIAN:考区",
				"KAOQU:考点",
				"ORG_NAME:住培基地",
				"DOCTOR_NAME:姓名",
				"SEX_NAME:性别",
				"CRET_TYPE_NAME:证件类型",
				"ID_NO:证件编号",
				"DOCTOR_TYPE_NAME:报考级别",
				"TRAINING_SPE_NAME:报考专业"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleWithHeadlin(headLines, titles, resultMapList, response.getOutputStream());
	}

	//中管局结业统计
	@RequestMapping("/graduationStatistics")
	public String graduationStatistics(String graduationYear,Model model){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("graduationYear",graduationYear);
		List<Map<String,Object>> resultMapList = doctorGraduationBiz.graduationStatistics(paramMap);
		model.addAttribute("resultMapList",resultMapList);
		int totalNum = 0;
		if(resultMapList!=null&&resultMapList.size()>0){
			for(Map<String,Object> map:resultMapList){
				String single = (String)map.get("ORG_SUM").toString();
				totalNum+=Integer.parseInt(single);
			}
		}
		model.addAttribute("totalNum",totalNum);
		return "sczyres/manage/graduationStatistics";
	}

	//基地结业学员详情列表
	@RequestMapping("/graduationListOrg")
	public String graduationListOrg(Model model,ScresGraduationApply searchApply,String role){
		SysUser currentUser = GlobalContext.getCurrentUser();
		String orgFlow = currentUser.getOrgFlow();
		String graduationYear = InitConfig.getSysCfg("res_graduation_year");
		List<String> orgFlows = new ArrayList<>();
		List<ScresGraduationApply> graduationApplies = new ArrayList<>();
		if("hospital".equals(role)){
			SysOrg org = orgBiz.readSysOrg(orgFlow);
			if(SczyResOrgLevelEnum.Main.getId().equals(org.getOrgLevelId())){//主基地
				Map<String,Object> paramMap = new HashMap<>();
				List<ResJointOrg> jointOrgs = resJointBiz.searchResJointByOrgFlow(orgFlow);
				if(jointOrgs!=null&&jointOrgs.size()>0){
					for(ResJointOrg jointOrg:jointOrgs){
						String jointOrgFlow = jointOrg.getJointOrgFlow();
						orgFlows.add(jointOrgFlow);
					}
				}
				orgFlows.add(orgFlow);
				searchApply.setGraduationYear(graduationYear);
				searchApply.setChargeStatusId("1");
				graduationApplies = doctorGraduationBiz.searchApply(searchApply,orgFlows);
			}
			if(SczyResOrgLevelEnum.Joint.getId().equals(org.getOrgLevelId())){//协同基地
				searchApply.setGraduationYear(graduationYear);
				searchApply.setChargeStatusId("1");
				searchApply.setOrgFlow(orgFlow);
				graduationApplies = doctorGraduationBiz.searchApply(searchApply,null);
			}
		}else{//中管局
			searchApply.setGraduationYear(graduationYear);
			searchApply.setChargeStatusId("1");
			graduationApplies = doctorGraduationBiz.searchApply(searchApply,null);
			model.addAttribute("orgFlow",searchApply.getOrgFlow());
			SysOrgExample orgExample = new SysOrgExample();
			orgExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId());
			orgExample.setOrderByClause("ORDINAL");
			List<SysOrg> orgs = this.orgBiz.searchOrgByExample(orgExample);
			model.addAttribute("hospitals", orgs);
		}
		model.addAttribute("graduationApplies",graduationApplies);
		return "sczyres/manage/graduationListOrg";
	}

	//准考证管理首页
	@RequestMapping("/ticket")
	public String ticket(ScresGraduationApply searchApply,Model model){
		String graduationYear = InitConfig.getSysCfg("res_graduation_year");
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("graduationYear",graduationYear);
		paramMap.put("doctorName",searchApply.getDoctorName());
		paramMap.put("orgFlow",searchApply.getOrgFlow());
		List<Map<String,Object>> resultMapList = doctorGraduationBiz.ticketList(paramMap);
		model.addAttribute("resultMapList",resultMapList);

		SysOrgExample orgExample = new SysOrgExample();
		orgExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgTypeIdEqualTo(OrgTypeEnum.Hospital.getId());
		orgExample.setOrderByClause("ORDINAL");
		List<SysOrg> orgs = this.orgBiz.searchOrgByExample(orgExample);
		model.addAttribute("hospitals", orgs);

		SysCfg cfg = cfgBiz.read("scres_graduation_printSwith");
		model.addAttribute("cfg",cfg);
		return "sczyres/manage/ticket";
	}

	//准考证详情
	@RequestMapping("/ticketDetail")
	public String ticketDetail(String recordFlow,String userFlow,String applyFlow,Model model,String role){
		if("doctor".equals(role)){
			userFlow=GlobalContext.getCurrentUser().getUserFlow();
		}
		ScresGraduationTicket ticket = doctorGraduationBiz.readTicket(recordFlow);
		model.addAttribute("ticket",ticket);
		SysUser user = userBiz.readSysUser(userFlow);
		model.addAttribute("user",user);
		ResDoctor doctor = resDoctorBiz.readDoctor(userFlow);
		model.addAttribute("doctor",doctor);
		PubUserResume resume = userResumeBiz.readPubUserResume(userFlow);
		ExtInfoForm extInfo=null;
		if(resume!=null){
			String resumeXml = resume.getUserResume();
			extInfo= docSinupBiz.parseExtInfoXml(resumeXml);
			if(extInfo!=null){
				String isMakeUp = extInfo.getIsMakeUp();
				String makeUpTypeId = extInfo.getMakeUpTypeId();
				model.addAttribute("isMakeUp",isMakeUp);
				model.addAttribute("makeUpTypeId",makeUpTypeId);
			}
		}
		model.addAttribute("applyFlow",applyFlow);
		return "sczyres/manage/ticketDetail";
	}
	//保存准考证
	@RequestMapping("/saveTicket")
	@ResponseBody
	public int saveTicket(ScresGraduationTicket ticket){
		List<ScresGraduationTicket> ticketList = doctorGraduationBiz.searchTicketNoRepeat(ticket);
		if(ticketList!=null&&ticketList.size()>0){
			return -1;
		}
		int result = doctorGraduationBiz.editTicket(ticket);
		return result;
	}
	//管理打印
	@RequestMapping("/openPrint")
	@ResponseBody
	public int openPrint(String flag){
		return doctorGraduationBiz.openPrint(flag);
	}
	//管理可用培训专业
	@RequestMapping("/changSpeAllow")
	@ResponseBody
	public int changSpeAllow(String datas){
		return doctorGraduationBiz.changSpeAllow(datas);
	}
	//管理省厅是否自动过学员减免年限的审
	@RequestMapping("/changAllowReduction")
	@ResponseBody
	public int changAllowReduction(String data){
		return doctorGraduationBiz.changAllowReduction(data);
	}
	//准考证导入
	@RequestMapping(value="/importTickets")
	@ResponseBody
	public String importTickets(MultipartFile file){
		if(file.getSize() > 0){
			try{
				int result = doctorGraduationBiz.importTickets(file);
				if(GlobalConstant.ZERO_LINE != result){
					return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
				}else{
					return GlobalConstant.UPLOAD_FAIL;
				}
			}catch(RuntimeException re){
				re.printStackTrace();
				return re.getMessage();
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

	//********************************************医师信息查询 开始************************************//
	@RequestMapping(value="/provinceDoctorList")
	public String provinceDoctorList(Model model, String roleFlag, String isArchive, String []datas){
		SysUser sysuser= GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		SysOrg sysorg =new  SysOrg();
//		sysorg.setOrgProvId(org.getOrgProvId());
//		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
//			sysorg.setOrgCityId(org.getOrgCityId());
//		}
		sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
		sysorg.setOrgLevelId(SczyResOrgLevelEnum.Main.getId());
		List<SysOrg>  countryList=orgBiz.searchOrg(sysorg);
		sysorg.setOrgLevelId(OrgLevelEnum.ProvinceOrg.getId());
		List<SysOrg>  provinceList=orgBiz.searchOrg(sysorg);

		List<ResJointOrg> jointOrgs=resJointBiz.searchJointOrgAll();
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
		List<ResJointOrg> joinOrgs = resJointBiz.searchResJointByJointOrgFlow(sysuser.getOrgFlow());
		//是协同基地
		if(!joinOrgs.isEmpty()&&joinOrgs.size()>0){
			model.addAttribute("isJointOrg","1");
		}
//		if(GlobalConstant.FLAG_Y.equals(isArchive)){
//			List<ResArchiveSequence> archiveSequenceList = archiveBiz.allResArchiveSequence();
//			model.addAttribute("archiveSequenceList",archiveSequenceList);
//			return  "jszy/archiveDoctorList";
//		}
		model.addAttribute("datas",datas);
		return  "sczyres/doctorList";
	}

	public List<String> searchJointOrgList(String flag,String Flow) {
		List<String> jointOrgFlowList=new ArrayList<String>();
		if(GlobalConstant.FLAG_Y.equals(flag)){
			List<ResJointOrg> resJointOrgList=resJointBiz.searchResJointByOrgFlow(Flow);
			if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:resJointOrgList){
					jointOrgFlowList.add(jointOrg.getJointOrgFlow());
				}
			}
		}
		return jointOrgFlowList;
	}
	@RequestMapping(value="/doctorTrendListSun")
	public String doctorRecruitSun(Model model, Integer currentPage, String roleFlag, HttpServletRequest request, ResDoctor doctor,
								   SysUser user, String baseId, String jointOrgFlag, String derateFlag, String catSpeId,String speId,
								   String secondSpeId, String orgLevel, String[] datas, String graduationYear,String planFlag){
		ResDoctorRecruit resDoctorRecruit= new ResDoctorRecruit();
		resDoctorRecruit.setCatSpeId(catSpeId);
		resDoctorRecruit.setSpeId(speId);
		resDoctorRecruit.setSecondSpeId(secondSpeId);
		if(StringUtil.isNotBlank(graduationYear)){
			resDoctorRecruit.setGraduationYear(graduationYear);
		}
		List<String> jointOrgFlowList=new ArrayList<String>();
		List<String>docTypeList=new ArrayList<String>();
		SysOrg org=new SysOrg();
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysUser sysUser =new SysUser();
		if (StringUtil.isBlank(doctor.getOrgFlow())) {
			if ("hospital".equals(roleFlag)) {
				jointOrgFlowList=searchJointOrgList(jointOrgFlag,sysuser.getOrgFlow());
				jointOrgFlowList.add(sysuser.getOrgFlow());
			}else{
				SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
				if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
					jointOrgFlowList.add(sysuser.getOrgFlow());
					SysOrg searchOrg=new SysOrg();
					if(StringUtil.isNotBlank(orgLevel)){
						searchOrg.setOrgLevelId(orgLevel);
						searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
					List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
					for(SysOrg g:exitOrgs){
						List<ResJointOrg> resJointOrgList=resJointBiz.searchResJointByOrgFlow(g.getOrgFlow());
						if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
							for(ResJointOrg jointOrg:resJointOrgList){
								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}
						jointOrgFlowList.add(g.getOrgFlow());
					}
				}else{
					if(StringUtil.isNotBlank(orgLevel)){
						org.setOrgLevelId(orgLevel);
						org.setOrgTypeId(SczyResOrgLevelEnum.Main.getId());
					}
				}
			}
		}else{
			jointOrgFlowList.add(doctor.getOrgFlow());
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				List<ResJointOrg> resJointOrgList=resJointBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
				if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgList){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
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
		resDoctorRecruit.setRecruitFlag("Y");
		resDoctorRecruit.setConfirmFlag("Y");
		List<Map<String,Object>> doctorList=null;
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctor.setDoctorStatusId(RegStatusEnum.Passed.getId());
		doctorRecruitMap.put("doctor", doctor);
		doctorRecruitMap.put("user", sysUser);
		doctorRecruitMap.put("derateFlag", derateFlag);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
		doctorRecruitMap.put("sysOrg", org);
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("planFlag", planFlag);
		List<String> trainTypeIdList = new ArrayList<String>();
		doctorRecruitMap.put("trainTypeIdList",trainTypeIdList);
		doctorList = doctorRecruitBiz.searchDoctorRecruitExtList(doctorRecruitMap);
		model.addAttribute("roleFlag",roleFlag);
		model.addAttribute("doctorList",doctorList);
		model.addAttribute("datas",datas);
		return  "sczyres/doctorListZi";
	}

	/**
	 * 导出国家医师协会名称
	 */
	@RequestMapping(value="/exportForDetail")
	public void exportForDetail(Model model, Integer currentPage, String roleFlag, HttpServletResponse response, ResDoctor doctor,
								   SysUser user, String baseId, String jointOrgFlag, String derateFlag, String catSpeId,String speId,
								   String secondSpeId, String orgLevel, String[] datas, String graduationYear,String planFlag) throws IOException {
		ResDoctorRecruit resDoctorRecruit= new ResDoctorRecruit();
		resDoctorRecruit.setCatSpeId(catSpeId);
		resDoctorRecruit.setSpeId(speId);
		resDoctorRecruit.setSecondSpeId(secondSpeId);
		if(StringUtil.isNotBlank(graduationYear)){
			resDoctorRecruit.setGraduationYear(graduationYear);
		}
		List<String> jointOrgFlowList=new ArrayList<String>();
		List<String>docTypeList=new ArrayList<String>();
		SysOrg org=new SysOrg();
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysUser sysUser =new SysUser();
		if (StringUtil.isBlank(doctor.getOrgFlow())) {
			if ("hospital".equals(roleFlag)) {
				jointOrgFlowList=searchJointOrgList(jointOrgFlag,sysuser.getOrgFlow());
				jointOrgFlowList.add(sysuser.getOrgFlow());
			}else{
				SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
				if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
					jointOrgFlowList.add(sysuser.getOrgFlow());
					SysOrg searchOrg=new SysOrg();
					if(StringUtil.isNotBlank(orgLevel)){
						searchOrg.setOrgLevelId(orgLevel);
						searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					}
					List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
					for(SysOrg g:exitOrgs){
						List<ResJointOrg> resJointOrgList=resJointBiz.searchResJointByOrgFlow(g.getOrgFlow());
						if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
							for(ResJointOrg jointOrg:resJointOrgList){
								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}
						jointOrgFlowList.add(g.getOrgFlow());
					}
				}else{
					if(StringUtil.isNotBlank(orgLevel)){
						org.setOrgLevelId(orgLevel);
						org.setOrgTypeId(SczyResOrgLevelEnum.Main.getId());
					}
				}
			}
		}else{
			jointOrgFlowList.add(doctor.getOrgFlow());
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				List<ResJointOrg> resJointOrgList=resJointBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
				if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgList){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
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
//		PageHelper.startPage(currentPage,getPageSize(request));
		resDoctorRecruit.setRecruitFlag("Y");
		resDoctorRecruit.setConfirmFlag("Y");
		List<Map<String,Object>> doctorList=null;
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctor.setDoctorStatusId(RegStatusEnum.Passed.getId());
		doctorRecruitMap.put("doctor", doctor);
		doctorRecruitMap.put("user", sysUser);
		doctorRecruitMap.put("derateFlag", derateFlag);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
		doctorRecruitMap.put("sysOrg", org);
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("planFlag", planFlag);
		List<String> trainTypeIdList = new ArrayList<String>();
		doctorRecruitMap.put("trainTypeIdList",trainTypeIdList);
		doctorList = doctorRecruitBiz.searchDoctorRecruitExtListWithClob(doctorRecruitMap);

		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setColor(HSSFFont.COLOR_NORMAL);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter2 = wb.createCellStyle(); //居中
		styleCenter2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleCenter.setFont(font);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		styleLeft.setFont(font);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		stylevwc.setFont(font);
		//列宽自适应
		HSSFRow rowDep = sheet.createRow(0);//第一行
        sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 0));//合并单元格
        HSSFCell cellZero = rowDep.createCell(0);
        cellZero.setCellStyle(stylevwc);
        cellZero.setCellValue("省");

		sheet.addMergedRegion(new CellRangeAddress(0, 2, 1, 1));//合并单元格
		HSSFCell cellOne = rowDep.createCell(1);
		cellOne.setCellStyle(stylevwc);
		cellOne.setCellValue("培训基地");

		sheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 24));//合并单元格
		HSSFCell cell = rowDep.createCell(2);
		cell.setCellStyle(stylevwc);
		cell.setCellValue("学员基本信息");

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 25, 40));
		HSSFCell cellTwo = rowDep.createCell(25);
		cellTwo.setCellStyle(styleCenter);
		cellTwo.setCellValue("学历信息");

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 41, 48));
		HSSFCell cellThree = rowDep.createCell(41);
		cellThree.setCellStyle(styleCenter);
		cellThree.setCellValue("单位信息");

        sheet.addMergedRegion(new CellRangeAddress(0, 1, 49, 51));
        HSSFCell cellThree2 = rowDep.createCell(49);
        cellThree2.setCellStyle(styleCenter);
        cellThree2.setCellValue("协同单位");


		HSSFRow rowTwo = sheet.createRow(1);//第二行
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 25, 28));//合并单元格
		HSSFCell cellFour = rowTwo.createCell(25);
		cellFour.setCellStyle(styleCenter);
		cellFour.setCellValue("本科");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 29, 34));//合并单元格
		HSSFCell cellFive = rowTwo.createCell(29);
		cellFive.setCellStyle(styleCenter);
		cellFive.setCellValue("硕士研究生");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 35, 40));//合并单元格
		HSSFCell cellSix = rowTwo.createCell(35);
		cellSix.setCellStyle(styleCenter);
		cellSix.setCellValue("博士研究生");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 41, 42));//合并单元格
		HSSFCell cellSeven = rowTwo.createCell(41);
		cellSeven.setCellStyle(styleCenter);
		cellSeven.setCellValue("所在单位");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 43, 45));//合并单元格
		HSSFCell cellEight = rowTwo.createCell(43);
		cellEight.setCellStyle(styleCenter);
		cellEight.setCellValue("医院");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 46, 48));//合并单元格
		HSSFCell cellNine = rowTwo.createCell(46);
		cellNine.setCellStyle(styleCenter);
		cellNine.setCellValue("基层医疗卫生机构");

		HSSFRow rowThree = sheet.createRow(2);//第三行
		String[] titles = new String[]{
//				"基地名称",
				"姓名",
				"性别",
				"出生年月",
				"证件类型",
				"证件号",
				"民族",
				"手机号",
				"邮箱",
				"QQ（非必填）",
				"往届/应届",
				"培训专业",
				"对应专业",
				"二级专业",
				"执业医师资格",
				"医师资格证书编号",
				"参培年份",
				"培训状态",
				"实际培训开始时间",
				"培训年限核定",
				"学员类型",
				"订单定向培养",
				"是否是对口支援",
				"生源省份",
				"毕业院校",
				"毕业时间",
				"毕业专业",
				"学位",
				"状态",
				"就读院校",
				"入学时间",
				"所学专业",
				"学位",
				"学位类型",
				"状态",
				"就读院校",
				"入学时间",
				"所学专业",
				"学位",
				"学位类型",
				"名称",
				"医疗卫生机构",
				"性质",
				"类别",
				"等级",
				"性质",
				"类别",
				"等级",
                "在协同单位培训",
                "协同单位名称",
                "协同单位等级"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowThree.createCell(i+2);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
		}

		int rowNum = 3;
		String[] dataList = null;
		if (doctorList != null && !doctorList.isEmpty()) {
			for (int i = 0; i < doctorList.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第四行

				Map<String,Object> map= doctorList.get(i);
				//培训基地
				String orgName = "";
				String joinName = "";
				String jointFlag = "";
				String property = "";
				String orgFlow = (String)map.get("ORG_FLOW");
				SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
				if (sysOrg!=null) {
					if(SczyResOrgLevelEnum.Main.getId().equals(sysOrg.getOrgLevelId())){
						orgName = sysOrg.getOrgName();
						jointFlag = "否";
					}
					if(SczyResOrgLevelEnum.Joint.getId().equals(sysOrg.getOrgLevelId())){
						List<ResJointOrg> resJointOrgs = resJointBiz.searchResJointByJointOrgFlow(orgFlow);
						if(resJointOrgs!=null&&resJointOrgs.size()>0){
							orgName = resJointOrgs.get(0).getOrgName();
						}
						joinName = sysOrg.getOrgName();
						jointFlag = "是";
						property = sysOrg.getSendSchoolName();
					}
				}

				String CretType = "";
				if (!CertificateTypeEnum.Shenfenzheng.getId().equals(map.get("CRET_TYPE_ID"))) {
					CretType = "其他";
				} else {
					CretType = "身份证";
				}
				String trainYear="";
				if(StringUtil.isNotBlank((String)map.get("TRAINING_YEARS")))
				{
					switch ((String)map.get("TRAINING_YEARS"))
					{
						case  "1" : trainYear="一年";break;
						case  "2" : trainYear="二年";break;
						case  "3" : trainYear="三年";break;
					}
				}
				String recruitDate = (String)map.get("RECRUIT_DATE");
				//解析xml
				String xml = (String)map.get("USER_RESUME");
				ExtInfoForm extInfo = null;
				extInfo = doctorSingupBiz.parseExtInfoXml(xml);
                //应届往届
                String isYearGraduate = "";
                if (GlobalConstant.FLAG_Y.equals(extInfo.getYearGraduateFlag())) {
                    isYearGraduate = "应届";
                } else {
                    isYearGraduate = "往届";
                }

				//是否是执业医师和编号
				String qualificationFlag = "";
				if ("Y".equals((String)map.get("DOCTOR_LICENSE_FLAG"))) {
					qualificationFlag = "是";
				} else {
					qualificationFlag = "否";
				}
				//研究生
				//研究生状态
				String yjszt="";
				if("1".equals(extInfo.getMasterStatue()))
				{
					yjszt="在读";
				}
				if("2".equals(extInfo.getMasterStatue()))
				{
					yjszt="已毕业";
				}
				//博士状态
				String bszt="";
				if("1".equals(extInfo.getDoctorStatue()))
				{
					bszt="在读";
				}
				if("2".equals(extInfo.getDoctorStatue()))
				{
					bszt="已毕业";
				}
				String masterFlag = "";
				if (!GlobalConstant.FLAG_Y.equals(extInfo.getIsMaster())) {
					extInfo.setMasterDegreeName("");
					extInfo.setMasterDegreeTypeName("");
					extInfo.setMaDate("");
					extInfo.setMaSchool("");
					extInfo.setMaMajor("");
					extInfo.setMasterStartTime("");
					yjszt="";
//					masterDegreeName=""
					masterFlag = "否";
				} else {
					masterFlag = "是";
				}
				//博士
				String doctorFlag = "";
				if (!GlobalConstant.FLAG_Y.equals(extInfo.getIsDoctor()) ) {
					extInfo.setDoctorDegreeName("");
					extInfo.setDoctorDegreeTypeName("");
					extInfo.setPhdSchool("");
					extInfo.setPhdDate("");
					extInfo.setPhdMajor("");
					extInfo.setDoctorStartTime("");
//					doctorDegreeName="";
					bszt="";
					doctorFlag = "否";
				} else {
					doctorFlag = "是";
				}
				//订单定向培养
				String dingxiang="";
				if(GlobalConstant.FLAG_Y.equals(extInfo.getIsGeneralOrderOrientationTrainee()))
				{
					dingxiang="是";
				}
				if(GlobalConstant.FLAG_N.equals(extInfo.getIsGeneralOrderOrientationTrainee()))
				{
					dingxiang="否";
				}
				//是否是对口支援
				String duiKou="";
				if(GlobalConstant.FLAG_Y.equals((String)map.get("IS_PARTNER")))
				{
					duiKou="是";
				}
				if(GlobalConstant.FLAG_N.equals((String)map.get("IS_PARTNER")))
				{
					duiKou="否";
				}
				//学员类型
				String doctorTypeName=(String)map.get("DOCTOR_TYPE_NAME");
				String getMedicalHeaithOrgName="";
				String getHospitalAttrName="";
				String getHospitalCategoryName="";
				String getBaseAttributeName="";
				String getNextHospitalAttrName="";
				String getBasicHealthOrgName="";
				String getBasicHealthOrgLevelName="";
				//工作单位

				if (SczyRecDocTypeEnum.Agency.getId().equals((String)map.get("DOCTOR_TYPE_ID"))) {
					if ("1".equals(extInfo.getMedicalHeaithOrgId())) {
						getHospitalAttrName=extInfo.getHospitalAttrName();
						getHospitalCategoryName=extInfo.getHospitalCategoryName();
						getBaseAttributeName=extInfo.getBaseAttributeName();
					}
					if ("3".equals(extInfo.getMedicalHeaithOrgId())) {
						getNextHospitalAttrName=extInfo.getHospitalAttrName();
						getBasicHealthOrgName=extInfo.getBasicHealthOrgName();
						getBasicHealthOrgLevelName=extInfo.getBasicHealthOrgLevelName();
					}
				}
				String workOrgName=(String)map.get("WORK_ORG_NAME");
				dataList = new String[]{
                        "四川省",
						orgName,
						(String)map.get("USER_NAME"),
						(String)map.get("SEX_NAME"),
						(String)map.get("USER_BIRTHDAY"),
						CretType,
						(String)map.get("ID_NO"),
						(String)map.get("NATION_NAME"),
						(String)map.get("USER_PHONE"),
						(String)map.get("USER_EMAIL"),
                        extInfo.getQqCode(),
						isYearGraduate,
                        (String)map.get("CAT_SPE_NAME"),
                        (String)map.get("SPE_NAME"),
                        (String)map.get("SECOND_SPE_NAME"),
						qualificationFlag,
						(String)map.get("QUALIFIED_NO"),
						(String)map.get("SESSION_NUMBER"),
						(String)map.get("DOCTOR_STATUS_NAME"),//医师状态
						recruitDate,
						trainYear,
						doctorTypeName,
						dingxiang,
						duiKou,
                        (String)map.get("SOURCE_PROVINCES_NAME"),
						(String)map.get("GRADUATED_NAME"),
						(String)map.get("GRADUATION_TIME"),
						(String)map.get("SPECIALIZED"),
                        extInfo.getDegreeName(),

						yjszt,
                        extInfo.getMaSchool(),
                        extInfo.getMasterStartTime(),
                        extInfo.getMaMajor(),
                        extInfo.getMasterDegreeName(),
                        extInfo.getMasterDegreeTypeName(),

						bszt,
                        extInfo.getPhdSchool(),
                        extInfo.getDoctorStartTime(),
                        extInfo.getPhdMajor(),
                        extInfo.getDoctorDegreeName(),
                        extInfo.getDoctorDegreeTypeName(),

						workOrgName,
                        extInfo.getMedicalHeaithOrgName(),

						getHospitalAttrName,
						getHospitalCategoryName,
						getBaseAttributeName,

						getNextHospitalAttrName,
						getBasicHealthOrgName,
						getBasicHealthOrgLevelName,

						jointFlag,
						joinName,
						property
				};
				for (int j = 0; j < dataList.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter2);
					cellFirst.setCellValue(dataList[j]);
					sheet.setColumnWidth(j, titles.length * 1 * 156);
				}
			}
		}
		String fileName = "学生信息一览表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());

	}
	/**
	 * 获取黑名单信息
	 *
	 * @param model
	 * @return
	 * @throws DocumentException
	 */
	@RequestMapping(value = "/blackListInfo")
	public String blackListInfo(String roleFlag,JsresUserBalcklist jsresUserBalcklist, Model model, String sessionNumber,HttpServletRequest request,
								Integer currentPage,String seeFlag,String orgFlow0) throws DocumentException {
		setSessionAttribute(GlobalConstant.USER_LIST_SCOPE, roleFlag);
		List<String> userFlowList = new ArrayList<String>();
		SysUser user = GlobalContext.getCurrentUser();
		if(StringUtil.isNotBlank(sessionNumber)){
			jsresUserBalcklist.setSessionNumber(sessionNumber);
		}
		if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)&&StringUtil.isBlank(seeFlag)) {
			jsresUserBalcklist.setOrgFlow(user.getOrgFlow());
		}
		List<SysOrg> orgs=new ArrayList<SysOrg>();
		if (GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)||GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
			SysOrg org=new SysOrg();
			SysOrg s=orgBiz.readSysOrg(user.getOrgFlow());
			org.setOrgProvId(s.getOrgProvId());
			if(GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
				org.setOrgCityId(s.getOrgCityId());
			}
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			orgs=orgBiz.searchAllSysOrg(org);
			model.addAttribute("orgs", orgs);
		}
		List<String> orgFlowList=new ArrayList<String>();
		if(StringUtil.isBlank(jsresUserBalcklist.getOrgFlow())&&GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)){
			if(orgs!=null&&!orgs.isEmpty()){
				for(SysOrg org:orgs){
					orgFlowList.add(org.getOrgFlow());
				}
			}
		}
		List<SysOrg> orgList=new ArrayList<>();
		orgList=orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("JointOrgCount",orgList.size());
		SysOrg org =orgBiz.readSysOrg(user.getOrgFlow());
		orgList.add(0,org);
		if(StringUtil.isBlank(orgFlow0)){
			orgFlow0 = org.getOrgFlow();
		}else if("all".equals(orgFlow0)){
			orgFlowList.add(jsresUserBalcklist.getOrgFlow());
			jsresUserBalcklist.setOrgFlow("");
			if(orgList!=null&&orgList.size()>0){
				for (SysOrg so:orgList) {
					orgFlowList.add(so.getOrgFlow());
				}
			}
		}else{
			jsresUserBalcklist.setOrgFlow(orgFlow0);
		}
		model.addAttribute("orgFlow",orgFlow0);
		model.addAttribute("orgList",orgList);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsresUserBalcklist> blackLists = blackBiz.searchInfo(jsresUserBalcklist, userFlowList, orgFlowList);
		model.addAttribute("blackLists", blackLists);
		return "sczyres/manage/blackListInfo";
	}
	/**
	 * 删除黑名单
	 *
	 * @param jsresUserBalcklist
	 * @return
	 * @throws DocumentException
	 */
	@RequestMapping(value = "/removeBlack")
	public @ResponseBody String removeBlack(JsresUserBalcklist jsresUserBalcklist) throws DocumentException {
		blackBiz.saveBlack(jsresUserBalcklist);
		if(jsresUserBalcklist.getRecordStatus().equals(GlobalConstant.FLAG_N))
		{
			String userFlow=jsresUserBalcklist.getUserFlow();
			if(StringUtil.isNotBlank(userFlow))
			{
				SysUser sysUser=userBiz.readSysUser(userFlow);
				if(sysUser!=null)
				{
					sysUser.setRecordStatus(GlobalConstant.FLAG_Y);
					userBiz.edit(sysUser);
				}
				ResDoctor resDoctor=resDoctorBiz.searchByUserFlow(userFlow);
				if(resDoctor!=null)
				{
					resDoctor.setRecordStatus(GlobalConstant.FLAG_Y);
					resDoctorBiz.editDoctor(resDoctor);
				}
			}
		}
		return GlobalConstant.OPERATE_SUCCESSED;
	}
	/**
	 * 跳转添加黑名单界面
	 *
	 * @param
	 * @return mav
	 * @throws
	 */
	@RequestMapping(value={"/addBlackList"},method= RequestMethod.GET)
	public ModelAndView addBlackList(){
		ModelAndView mav=new ModelAndView("sczyres/manage/addBlackList");
		return mav;
	}
	/**
	 * 保存黑名单
	 *
	 * @param
	 * @return
	 * @throws
	 */
	@RequestMapping(value={"/saveBlackList"})
	public @ResponseBody String saveBlackList(String userIdNo, String reason,String userName,String roleFlag){
		SysUser currentUser = GlobalContext.getCurrentUser();
		SysUser sysUser = new SysUser();
		sysUser = userBiz.findByIdNo(userIdNo);
		if("city".equals(roleFlag)){
			//市局不能添加系统中不存在学员
			if(sysUser==null){
				return "不能添加系统中不存在学员";
			}
			//市局不能添加非本市学员
			String orgFlow = currentUser.getOrgFlow();
			if(StringUtil.isNotBlank(orgFlow)){
				SysOrg currentOrg = orgBiz.readSysOrg(orgFlow);
				String currentCityId = currentOrg.getOrgCityId();
				SysOrg userOrg = orgBiz.readSysOrg(sysUser.getOrgFlow());
				if(userOrg!=null){
					String userCityId = userOrg.getOrgCityId();
					if(StringUtil.isNotBlank(userCityId)){
						if(!userCityId.equals(currentCityId)){
							return "不能添加非本市学员";
						}
					}else {
						return "不能添加非本市学员";
					}
				}else {
					return "不能添加非本市学员";
				}
			}else{
				return GlobalConstant.OPRE_FAIL;
			}
		}
		JsresUserBalcklist blackUser = blackBiz.searchInfoByIdNo(userIdNo);
		if(blackUser==null)
			blackUser=new JsresUserBalcklist();
		if(sysUser==null)
		{
			sysUser=new SysUser();
			sysUser.setIdNo(userIdNo);
			sysUser.setUserName(userName);
		}
		reason = reason.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		int count=doSave( sysUser,reason,blackUser);
		if(sysUser!=null&&count==1)
		{
			String userFlow=sysUser.getUserFlow();
			if(StringUtil.isNotBlank(userFlow))
			{
				sysUser.setRecordStatus(GlobalConstant.FLAG_N);
				userBiz.edit(sysUser);
				ResDoctor resDoctor=resDoctorBiz.searchByUserFlow(userFlow);
				if(resDoctor!=null)
				{
					resDoctor.setRecordStatus(GlobalConstant.FLAG_N);
					resDoctorBiz.editDoctor(resDoctor);
				}
			}
		}
		if(count==1)
		{
			return GlobalConstant.OPERATE_SUCCESSED;
		}else{
			return GlobalConstant.OPRE_FAIL;
		}
	}
	private int doSave(SysUser sysUser,String reason,JsresUserBalcklist black){
		ResDoctor doctor = new ResDoctor();
		doctor = resDoctorBiz.findByFlow(sysUser.getUserFlow());
		black.setUserFlow(sysUser.getUserFlow());
		black.setUserCode(sysUser.getUserCode());
		black.setUserName(sysUser.getUserName());
		black.setUserPhone(sysUser.getUserPhone());
		black.setUserEmail(sysUser.getUserEmail());
		if(doctor!=null)
		{
			black.setOrgFlow(doctor.getOrgFlow());
			black.setOrgName(doctor.getOrgName());
			black.setSessionNumber(doctor.getSessionNumber());
			//四川中医，黑名单原培训专业为 （基地角色)学员录取中培训专业
			ResDoctorRecruit  recruit =  recruitBiz.getNewRecruitByDoctorFlow(doctor.getDoctorFlow());
			if(null != recruit){
				black.setTrainingSpeId(recruit.getCatSpeId());
				black.setTrainingSpeName(recruit.getCatSpeName());
			}
		}
		black.setIdNo(sysUser.getIdNo());
		black.setReason(reason);
		black.setOperTypeId("2");
		black.setOperTypeName("手动");
		black.setReasonYj("该身份证号“"+sysUser.getIdNo()+"”已被加入本系统黑名单中，无法进行注册报名！");
		black.setRecordStatus("Y");
		return blackBiz.saveBlack(black);
	}
}
