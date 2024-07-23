package com.pinde.sci.ctrl.jszy;


import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.pdf.DocumentVo;
import com.pinde.core.pdf.PdfDocumentGenerator;
import com.pinde.core.pdf.utils.ResourceLoader;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.jszy.*;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.ISchDeptExternalRelBiz;
import com.pinde.sci.biz.sch.ISchDoctorDeptBiz;
import com.pinde.sci.biz.sch.ISchRotationDeptBiz;
import com.pinde.sci.biz.sch.ISchRotationGroupBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.enums.jszy.*;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.res.ResBaseStatusEnum;
import com.pinde.sci.enums.res.ResDoctorAuditStatusEnum;
import com.pinde.sci.enums.res.ResRecTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.model.jszy.JszyResDoctorOrgHistoryExt;
import com.pinde.sci.model.jszy.JszyResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("/jszy/manage")
public class JszyResManageController extends GeneralController {
	final static String JXCF = "1";
	final static String YN = "2";
	final static String WZBLTL = "3";
	final static String XSJZ = "4";
	final static String SWBLTL = "5";
	@Autowired
	private SysLogMapper logMapper;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private IJszyResDoctorRecruitBiz jsResDoctorRecruitBiz;
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private IResDoctorRecruitBiz doctorRecruitBiz;
	@Autowired
	private IOrgBiz orgBiz;

	@Autowired
	private IJszyResOrgSpeBiz resOrgSpeBiz;
	@Autowired
	private IJszyResDoctorOrgHistoryBiz jsDocOrgHistoryBiz;
	@Autowired
	private IJszyResStatisticBiz resStatisticBiz;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
	@Autowired
	private ISchDoctorDeptBiz doctorDeptBiz;
	@Autowired
	private ISchRotationGroupBiz groupBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IJszyResRecBiz jsResRecBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private IResAssessCfgBiz assessCfgBiz;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private ISchDeptExternalRelBiz deptExternalRelBiz;
	@Autowired
	private IJszyResDoctorBiz jsResDoctorBiz;
	@Autowired
	private IFileBiz pubFileBiz;
	@Autowired
	private IResDoctorDelayTeturnBiz resDoctorDelayTeturnBiz;

	/**
	 * 管理员主界面
	 * @throws Exception
	 */
	@RequestMapping(value="/{role}")
	public String index(@PathVariable String role,String more,Model model,Integer currentPage,HttpServletRequest request) throws Exception{
		int countryOrgCount=0;//主基地的国家基地
		int jointOrgCount=0;//协同基地的国家基地
		List<String>orgFlowList=new ArrayList<String>();
		List<String> list=new ArrayList<String>();
		//省厅
		if (GlobalConstant.USER_LIST_GLOBAL.equals(role)) {
			SysOrg searchOrg=new SysOrg();
			searchOrg.setOrgProvId("320000");
			searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
			for(SysOrg g:exitOrgs){
				List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
				if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgList){
						list.add(jointOrg.getJointOrgFlow());
					}
				}
				list.add(g.getOrgFlow());
			}
		}
		//市局
		if (GlobalConstant.USER_LIST_CHARGE.equals(role)) {
			SysUser sysuser=GlobalContext.getCurrentUser();
			SysOrg searchOrg=new SysOrg();
			searchOrg.setOrgProvId("320000");
			searchOrg.setOrgCityId(sysuser.getNativePlaceCityId());
			searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
			for(SysOrg g:exitOrgs){
				List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
				if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgList){
						list.add(jointOrg.getJointOrgFlow());
					}
				}
				list.add(g.getOrgFlow());
			}
		}
		//基地
		if (GlobalConstant.USER_LIST_LOCAL.equals(role)) {
			SysUser sysuser=GlobalContext.getCurrentUser();
			SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
			if(OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
				List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(sysuser.getOrgFlow());
				if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgList){
						list.add(jointOrg.getJointOrgFlow());
					}
				}
			}
			list.add(sysuser.getOrgFlow());
		}
		setSessionAttribute(GlobalConstant.USER_LIST_SCOPE, role);
		ResDoctorRecruit recruit=new ResDoctorRecruit();
		//当前用户
		SysUser user=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(user.getOrgFlow());
		PageHelper.startPage(currentPage,getPageSize(request));
		List<InxInfo> infos = this.noticeBiz.searchInfoByOrgBeforeDate(null,null, GlobalConstant.RES_NOTICE_TYPE_ID, GlobalConstant.RES_NOTICE_SYS_ID, GlobalContext.getCurrentUser().getUserFlow(),"");
		model.addAttribute("infos",infos);
		String date = DateUtil.getCurrDateTime("yyyy-MM");
		List<Map<String, Object>> trainLst = processBiz.searTrainingQuery(user.getOrgFlow(),date);//存储过滤出的即将出科学员
		if(null != trainLst && trainLst.size() > 0){
			for (int i = 0; i < trainLst.size(); i++) {
				if(i < trainLst.size()-1 && trainLst.get(i).get("doctorFlow").equals(trainLst.get(i+1).get("doctorFlow"))
						&& !(trainLst.get(i).get("recTypeId").equals(trainLst.get(i+1).get("recTypeId")) && "AfterSummary".equals(trainLst.get(i).get("recTypeId")))){
					if("AfterSummary".equals(trainLst.get(i+1).get("recTypeId"))){
						trainLst.remove(i);
					}else{
						trainLst.remove(i+1);
					}
					i=i-1;
				}
			}
		}
		Map<String, String> stateMap=new HashMap<String, String>();
		if(null != trainLst && trainLst.size() > 0){
			for (Map<String, Object> map : trainLst) {
				String recTypeId = (String)map.get("recTypeId");
				String recContent=(String) map.get("recContent");
				String key=(String)map.get("doctorFlow")+map.get("schDeptFlow");
				String endDate = (String)map.get("schEndDate");
				if(DateUtil.getCurrDate().compareTo(endDate) >= 0){
					stateMap.put(key, "已出科");
				}else if("AfterSummary".equals(recTypeId) && StringUtil.isNotBlank(recContent)){
					List<Map<String, String>> imageList = resRecBiz.parseImageXml(recContent);
					if (imageList!=null && !imageList.isEmpty()) {
						stateMap.put(key, "已出科");
					}else{
						stateMap.put(key, "待出科");
					}
				}else{
					stateMap.put(key, "待出科");
				}
			}
		}
		model.addAttribute("stateMap", stateMap);
		model.addAttribute("trainingList", trainLst);
		//当前orgFlow
		if(org!=null){
			model.addAttribute("orgFlow",org.getOrgFlow());
		}

		int speFlag=0;
		int changeOrgFlag = 0;
		int delayFlag=0;
		int backFlag=0;
		ResDoctorOrgHistory history =new ResDoctorOrgHistory();
		ResDoctorOrgHistory history4ChangeOrg =new ResDoctorOrgHistory();
		ResDocotrDelayTeturn delay = new ResDocotrDelayTeturn();
		ResDocotrDelayTeturn back = new ResDocotrDelayTeturn();

		if (GlobalConstant.USER_LIST_GLOBAL.equals(role)) { //省级管理员
			int provinceOrgCount=0;//省级基地
			int doctorCount=0;//医师总数
			int yearConDocCount=0;//本年度国家基地的医师数
			String year=DateUtil.getCurrDate().substring(0, 4);//当前的年份
			SysOrg sysOrg=new SysOrg();
			sysOrg.setOrgProvId(org.getOrgProvId());
			sysOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
			sysOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			//所有国家基地
			List<SysOrg>sysOrgs=orgBiz.searchAllSysOrg(sysOrg);
			//本季度的国家基地的医师数
			ResDoctor d=new ResDoctor();
			d.setSessionNumber(year);
			yearConDocCount=resStatisticBiz.statisticYearCondocCount(d, sysOrgs);
			//主基地的国家基地
			countryOrgCount=resStatisticBiz.statisticCountyOrgCount(sysOrg);
			sysOrg.setOrgLevelId(OrgLevelEnum.ProvinceOrg.getId());
			//省级基地
			provinceOrgCount=resStatisticBiz.statisticCountyOrgCount(sysOrg);
			ResDoctorRecruit re=new ResDoctorRecruit();
			re.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
			List<String> cityIdList =InitConfig.getCityMap(org.getOrgProvId());
			//培训医师总数
			if(StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))){
				re.setSessionNumber(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"));
			}
			//待结业审核
			int waitPassCount=jsResDoctorBiz.findWaitPassCountByOrgFlow(1,list);
			model.addAttribute("waitPassCount", waitPassCount);
			doctorCount=resStatisticBiz.statisticDoctorCount(re,cityIdList);
			//
			init(model,cityIdList,null,list);
			//协同基地的国家基地
			jointOrgCount=resStatisticBiz.statisticJointOrgCount(null);

			history.setChangeTypeId(JszyResChangeTypeEnum.SpeChange.getId());
			history.setChangeStatusId(JszyResChangeApplySpeEnum.GlobalWaitingAudit.getId());
			List<ResDoctorOrgHistory> spe=jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);

			history4ChangeOrg.setChangeTypeId(JszyResChangeTypeEnum.BaseChange.getId());
			history4ChangeOrg.setChangeStatusId(JszyResChangeApplyStatusEnum.GlobalApplyWaiting.getId());
			List<ResDoctorOrgHistory> changeOrg = jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history4ChangeOrg);

			delay.setTypeId(ResRecTypeEnum.Delay.getId());
			delay.setAuditStatusId(ResBaseStatusEnum.Auditing.getId());
			List<ResDocotrDelayTeturn> delays = resDoctorDelayTeturnBiz.searchDelayInfo(delay);

			back.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
			back.setAuditStatusId(ResBaseStatusEnum.Auditing.getId());
			List<ResDocotrDelayTeturn> backDocs = resDoctorDelayTeturnBiz.searchDelayInfo(back);
			if(spe!=null && !spe.isEmpty()){
				speFlag=1;
			}
			if (changeOrg != null && !changeOrg.isEmpty()) {
				changeOrgFlag = 1;
			}
			if(delays!=null && !delays.isEmpty()){
				delayFlag = 1;
			}
			if (backDocs != null && !backDocs.isEmpty()) {
				backFlag = 1;
			}
			SysOrg sysOrg2=new SysOrg();
			sysOrg2.setOrgProvId(org.getOrgProvId());
			sysOrg2.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			List<SysOrg> orgs=orgBiz.queryAllSysOrg(sysOrg2);
			model.addAttribute("orgs", orgs);
			model.addAttribute("countryOrgCount", countryOrgCount);
			model.addAttribute("jointOrgCount", jointOrgCount);
			model.addAttribute("doctorCount", doctorCount);
			model.addAttribute("provinceOrgCount", provinceOrgCount);
			model.addAttribute("yearConDocCount", yearConDocCount);
			model.addAttribute("speFlag",speFlag );
			model.addAttribute("changeOrgFlag",changeOrgFlag);
			model.addAttribute("delayFlag",delayFlag);
			model.addAttribute("backFlag",backFlag);
			return "jszy/global/globalIndex";
		}else if (GlobalConstant.USER_LIST_CHARGE.equals(role)) { //市局
			//培训医师数
			recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
			List<String> cityIdList =new ArrayList<String>();
			cityIdList.add(org.getOrgCityId());
			if(StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))){
				recruit.setSessionNumber(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"));
			}
			int passCount=resStatisticBiz.statisticDoctorCount(recruit,cityIdList);
			model.addAttribute("passCount", passCount);
			//待审核的数量
			recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Auditing.getId());
			int waitCount=jsResDoctorRecruitBiz.searchBasePassCount(recruit,orgFlowList);
			model.addAttribute("waitCount", waitCount);
			int waitPassCount=jsResDoctorBiz.findWaitPassCountByOrgFlow(2,list);
			model.addAttribute("waitPassCount", waitPassCount);
			//国家基地的数量
			SysOrg sysOrg=new SysOrg();
			sysOrg.setOrgCityId(org.getOrgCityId());
			sysOrg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
			sysOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			countryOrgCount=resStatisticBiz.statisticCountyOrgCount(sysOrg);
			model.addAttribute("countryOrgCount", countryOrgCount);
			//协同基地的数量
			jointOrgCount=resStatisticBiz.statisticJointOrgCount(org);
			SysOrg sysOrg2=new SysOrg();
			sysOrg2.setOrgProvId(org.getOrgProvId());
			sysOrg2.setOrgCityId(org.getOrgCityId());
			sysOrg2.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			List<SysOrg> orgs=orgBiz.searchOrg(sysOrg2);
			model.addAttribute("orgs", orgs);
			model.addAttribute("jointOrgCount", jointOrgCount);
			int provinceOrgCount=0;//省级基地
			sysOrg.setOrgLevelId(OrgLevelEnum.ProvinceOrg.getId());
			provinceOrgCount=resStatisticBiz.statisticCountyOrgCount(sysOrg);
			model.addAttribute("provinceOrgCount", provinceOrgCount);
			String cfgCode="jswjw_"+user.getOrgFlow()+"_P003";
			SysCfg cfg=cfgBiz.read(cfgCode);
//			if(cfg==null||GlobalConstant.RECORD_STATUS_N.equals(cfg.getCfgValue())){
//				return "jszy/city/index";
//			}
			init(model,cityIdList,null,list);
			return "jszy/city/cityIndex";
		} else if (GlobalConstant.USER_LIST_LOCAL.equals(role)) { //培训基地
			//培训医师数
			recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
			recruit.setOrgFlow(user.getOrgFlow());
			if(StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))){
				recruit.setSessionNumber(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"));
			}
			int passCount=resStatisticBiz.statisticDoctorCount(recruit,null);
			model.addAttribute("passCount", passCount);
			recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Auditing.getId());
			int waitCount=jsResDoctorRecruitBiz.searchBasePassCount(recruit,null);
			model.addAttribute("waitCount", waitCount);
			int waitPassCount=jsResDoctorBiz.findWaitPassCountByOrgFlow(2,list);
			model.addAttribute("waitPassCount", waitPassCount);
			//专业和基地变更待办事项
			int baseFlag=0;
			history.setOrgFlow(user.getOrgFlow());
			history.setChangeTypeId(JszyResChangeTypeEnum.BaseChange.getId());
			history.setChangeStatusId(JszyResChangeApplyStatusEnum.InApplyWaiting.getId());
			List<ResDoctorOrgHistory> baseOne=jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
			history.setOrgFlow("");
			history.setHistoryOrgFlow(user.getOrgFlow());
			history.setChangeStatusId(JszyResChangeApplyStatusEnum.OutApplyWaiting.getId());
			List<ResDoctorOrgHistory> baseTwo=jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
			if((baseOne!=null&&!baseOne.isEmpty())||(baseTwo!=null&&!baseTwo.isEmpty())){
				baseFlag=1;
			}

			history.setChangeTypeId(JszyResChangeTypeEnum.SpeChange.getId());
			history.setChangeStatusId(JszyResChangeApplySpeEnum.BaseWaitingAudit.getId());
			List<ResDoctorOrgHistory> spe=jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
			if(spe!=null && !spe.isEmpty()){
				speFlag=1;
			}
			model.addAttribute("speFlag",speFlag );
			model.addAttribute("baseFlag", baseFlag);
			String cfgCode="jswjw_"+user.getOrgFlow()+"_P001";
			SysCfg cfg=cfgBiz.read(cfgCode);
			if(cfg==null||GlobalConstant.RECORD_STATUS_N.equals(cfg.getCfgValue())){
				return "jszy/hospital/index";
			}
			List<String> orgFlows = new ArrayList<>();
			orgFlows.add(user.getOrgFlow());
			init(model,orgFlows,"hosipital",list);
			if(OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())){
				List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
				List<String> jointOrgFlowList =new ArrayList<>();
				if(jointOrgs!=null&&jointOrgs.size()>0){
					for (ResJointOrg join: jointOrgs) {
						jointOrgFlowList.add(join.getJointOrgFlow());
					}
				}
				List<SysOrg> orgs = new ArrayList<>();
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					orgs =orgBiz.searchOrgFlowIn(jointOrgFlowList);
				}
				orgs.add(org);
				model.addAttribute("orgs", orgs);
				model.addAttribute("countryOrg", "countryOrg");
			}
			return "jszy/hospital/hospitalIndex";
		}
		return "/inx/jszy/login";
	}
	private void init(Model model,List<String> cityIdList,String roleId,List<String> orgFlows){
		//待结业审核各人员类型数
		Map<String,Map> doctorCountExtMap = new HashMap<>();
		Map<String,String> doctorCountMap = new HashMap<>();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		for( int i=0;i<3;i++){
			String sessionNummber =DateUtil.getYear();
			sessionNummber=Integer.parseInt(sessionNummber)- i +"";
			paramMap.put("sessionNumber", sessionNummber);
			if("hosipital".equals(roleId)){
				if(cityIdList!=null&&cityIdList.size()>0){
					paramMap.put("orgFlow",cityIdList.get(0));
				}
			}else {
				paramMap.put("cityIdList", cityIdList);
			}
			doctorCountMap = resStatisticBiz.statisticDoctorCountMap(paramMap);
			doctorCountExtMap.put(sessionNummber+"pl",doctorCountMap);
		}
		//当前住培情况
		List<Map<String,Object>> currDocDetails = new ArrayList<>();
		Map<String,String> currDocSumBef2014 = new HashMap<>();
		paramMap = new HashMap<>();
		if("hosipital".equals(roleId)){
			if(cityIdList!=null&&cityIdList.size()>0){
				paramMap.put("orgFlow",cityIdList.get(0));
			}
		}else {
			paramMap.put("cityIdList", cityIdList);
		}
		currDocDetails = resStatisticBiz.getCurrDocDetails(paramMap);
		int _20count=0;//在培
		int _21count=0;//结业
		int _22count=0;//考核通过带结业
		List<Map<String,Object>> currDocDetailMaps = new ArrayList<>();
		Map<String,Object> currDocDetailMap = new HashMap<String, Object>();
		String year ="";
		for (Map<String,Object> demap:currDocDetails) {
			if(Integer.parseInt(demap.get("SESSIONNUMBER").toString())>=2014){
				if("20".equals(demap.get("STATUSID"))){
					_20count+=Integer.parseInt(demap.get("NUM").toString());
				}else if("21".equals(demap.get("STATUSID"))){
					_21count+=Integer.parseInt(demap.get("NUM").toString());
				}else if("22".equals(demap.get("STATUSID"))){
					_22count+=Integer.parseInt(demap.get("NUM").toString());
				}
			}
			if(!year.equals(demap.get("SESSIONNUMBER"))){
				year=demap.get("SESSIONNUMBER").toString();
				currDocDetailMap=new HashMap<>();
				currDocDetailMap.put("SESSIONNUMBER",demap.get("SESSIONNUMBER"));
			}
			if("20".equals(demap.get("STATUSID"))){
				currDocDetailMap.put("20",demap.get("NUM"));
			}else if("21".equals(demap.get("STATUSID"))){
				currDocDetailMap.put("21",demap.get("NUM"));
			}else if("22".equals(demap.get("STATUSID"))){
				currDocDetailMap.put("22",demap.get("NUM"));
			}
			if(currDocDetailMaps!=null&&currDocDetailMaps.size()>0){
				for(Iterator<Map<String,Object>> it = currDocDetailMaps.iterator();it.hasNext();){
					Map<String,Object> bmap = it.next();
					if(year.equals(bmap.get("SESSIONNUMBER"))){
						it.remove();
					}
				}
			}
			currDocDetailMaps.add(currDocDetailMap);
		}
		currDocSumBef2014.put("_20count",_20count+"");
		currDocSumBef2014.put("_21count",_21count+"");
		currDocSumBef2014.put("_22count",_22count+"");
		model.addAttribute("currDocDetailMaps", currDocDetailMaps);
		model.addAttribute("currDocSumBef2014", currDocSumBef2014);
		model.addAttribute("doctorCountExtMap", doctorCountExtMap);
	}

	/**
	 * 医师信息审核
	 * @param role
	 * @return
	 */
	@RequestMapping(value={"/{role}/doctor/recruitAuditSearch"})
	public String recruitAuditList(@PathVariable String role){
		return "jszy/hospital/doctor/recruitAuditSearch";
	}

	/**
	 * 减免学员列表
	 * @param currentPage
	 * @param degreeType
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/reductionRotationOper")
	public String reductionRotationOper(Integer currentPage,String degreeType,String viewJoint,String doctorName,String [] datas, Model model,HttpServletRequest request){
		List<String> docTypeList = new ArrayList<>();
		String dataStr = "";
		if(datas!=null&&datas.length>0){
			docTypeList = Arrays.asList(datas);
			for(String d : datas){
				dataStr += d+",";
			}
		}
		model.addAttribute("dataStr",dataStr);
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();

		String sessionNumber = "2016";
		//String trainType = JszyTrainCategoryEnum.DoctorTrainingSpe.getId();
		List<String> trainingYears = new ArrayList<String>();
		trainingYears.add(JszyResTrainYearEnum.OneYear.getId());
		trainingYears.add(JszyResTrainYearEnum.TwoYear.getId());
		List<String> degreeTypes = new ArrayList<String>();
		if(GlobalConstant.FLAG_Y.equals(degreeType)){
			degreeTypes.add(JszyResDegreeCategoryEnum.ClinicMaster.getId());
			degreeTypes.add(JszyResDegreeCategoryEnum.ClinicDoctor.getId());
		}else{
			degreeTypes.add(JszyResDegreeCategoryEnum.Bachelor.getId());
			degreeTypes.add(JszyResDegreeCategoryEnum.AcademicMaster.getId());
			degreeTypes.add(JszyResDegreeCategoryEnum.AcademicDoctor.getId());
			degreeTypes.add(JszyResDegreeCategoryEnum.NoDegree.getId());
		}

		List<String> orgFlows = new ArrayList<String>();
		orgFlows.add(orgFlow);

		if(GlobalConstant.FLAG_Y.equals(viewJoint)){
			List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
			if(jointOrgList!=null && !jointOrgList.isEmpty()){
				for(ResJointOrg rjo : jointOrgList){
					orgFlows.add(rjo.getJointOrgFlow());
				}
			}
		}

		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("degreeTypes",degreeTypes);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("trainingYears",trainingYears);
		//paramMap.put("trainType",trainType);
		paramMap.put("orgFlows",orgFlows);
		paramMap.put("doctorName",doctorName);
		paramMap.put("docTypeList",docTypeList);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResDoctor> doctorList = jsResDoctorBiz.searchReductionDoc(paramMap);
		if(doctorList!=null && !doctorList.isEmpty()){
			model.addAttribute("doctorList",doctorList);

			List<String> doctorFlows = new ArrayList<String>();
			Map<String,Integer> operFlagMap = new HashMap<String, Integer>();
			Map<String,ResDoctorRecruit> recruitMap = new HashMap<String, ResDoctorRecruit>();
			Map<String,BaseUserResumeExtInfoForm> userResumeExtMap = new HashMap<String,BaseUserResumeExtInfoForm>();
			Map<String,List<PubFile>> reductionFilesMap = new HashMap<String,List<PubFile>>();
			for(ResDoctor doc : doctorList){
				String doctorFlow = doc.getDoctorFlow();

				doctorFlows.add(doctorFlow);

				int result = doctorDeptBiz.countSchDoctorDeptIgnoreStatus(doctorFlow,doc.getRotationFlow(),doc.getOrgFlow());
				operFlagMap.put(doctorFlow,result);

				ResDoctorRecruit recruit = doctorRecruitBiz.getNewRecruit(doctorFlow);
				recruitMap.put(doctorFlow,recruit);

				if(GlobalConstant.FLAG_Y.equals(degreeType)){
					PubUserResume resume = userResumeBiz.readPubUserResume(doctorFlow);
					if(null != resume && StringUtil.isNotBlank(resume.getUserResume())){
						BaseUserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(resume.getUserResume(), BaseUserResumeExtInfoForm.class);
						userResumeExtMap.put(doctorFlow,userResumeExt);
					}
				}
				//在培状态志愿记录减免证明材料
				List<PubFile> reductionFiles = pubFileBiz.findFileByDoctorFlow(doctorFlow);
				reductionFilesMap.put(doctorFlow,reductionFiles);
			}
			model.addAttribute("reductionFilesMap",reductionFilesMap);
			model.addAttribute("operFlagMap",operFlagMap);
			model.addAttribute("recruitMap",recruitMap);
			model.addAttribute("userResumeExtMap",userResumeExtMap);

			List<SysUser> userList = userBiz.searchSysUserByuserFlows(doctorFlows);
			if(userList!=null && !userList.isEmpty()){
				Map<String,SysUser> userMap = new HashMap<String,SysUser>();
				for(SysUser su : userList){
					userMap.put(su.getUserFlow(),su);
				}
				model.addAttribute("userMap",userMap);
			}
		}
		return  "jszy/hospital/reductionRotationDocList";
	}
	@RequestMapping(value={"/zpReductionFiles"})
	public String zpReductionFiles(String doctorFlow,Model model){
		if(StringUtil.isNotBlank(doctorFlow)){
			//在培状态志愿记录减免证明材料
			List<PubFile> fileList = pubFileBiz.findFileByDoctorFlow(doctorFlow);
			model.addAttribute("fileList",fileList);
		}
		return "jszy/hospital/doctor/zpReductionFiles";
	}
	@RequestMapping(value={"/saveAudit"})
	@ResponseBody
	public String saveAudit(Model model,ResDoctorOrgHistory history,String flag,ResDoctorRecruit recruit){
		String msgContent = "";
		if(StringUtil.isNotBlank(history.getRecordFlow())){
			ResDoctorOrgHistory orgHistory=jsDocOrgHistoryBiz.readDocOrgHistory(history.getRecordFlow());
			if(orgHistory!=null){
				if(StringUtil.isNotBlank(flag)){
					if(GlobalConstant.FLAG_Y.equals(flag)){
						if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)){
							msgContent="省厅审核通过！";
							orgHistory.setChangeStatusId(JszyResChangeApplySpeEnum.GlobalAuditPass.getId());
							orgHistory.setChangeStatusName(JszyResChangeApplySpeEnum.GlobalAuditPass.getName());
						}
						if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)){
							msgContent="基地审核通过！";
							orgHistory.setChangeStatusId(JszyResChangeApplySpeEnum.GlobalWaitingAudit.getId());
							orgHistory.setChangeStatusName(JszyResChangeApplySpeEnum.GlobalWaitingAudit.getName());
						}
					}else{
						if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)){
							msgContent="省厅审核不通过！";
							orgHistory.setChangeStatusId(JszyResChangeApplySpeEnum.GlobalAuditunPass.getId());
							orgHistory.setChangeStatusName(JszyResChangeApplySpeEnum.GlobalAuditunPass.getName());
						}
						if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)){
							msgContent="基地审核不通过！";
							orgHistory.setChangeStatusId(JszyResChangeApplySpeEnum.BaseAuditUnPass.getId());
							orgHistory.setChangeStatusName(JszyResChangeApplySpeEnum.BaseAuditUnPass.getName());
						}
					}
				}
				if(StringUtil.isNotBlank(history.getAuditOpinion())){
					orgHistory.setAuditOpinion(history.getAuditOpinion());
				}
				if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)){
					orgHistory.setOutDate(DateUtil.getCurrDateTime());
				}
				if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)){
					orgHistory.setInDate(DateUtil.getCurrDateTime());
				}
				int result=jsDocOrgHistoryBiz.saveDocOrgHistory(orgHistory);
				int recResult=0; String mark="";
				if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)&&result!=GlobalConstant.ZERO_LINE&&GlobalConstant.FLAG_Y.equals(flag)){
					recResult=jsDocOrgHistoryBiz.changeStatus(orgHistory,recruit);
					mark=GlobalConstant.FLAG_Y;
				}
				if(result==GlobalConstant.ZERO_LINE||(recResult==GlobalConstant.ZERO_LINE&&StringUtil.isBlank(mark)&&getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)&&GlobalConstant.FLAG_Y.equals(flag))){
					return GlobalConstant.OPRE_FAIL;
				}else{
					String msgTitle = "变更专业审核结果";
					msgBiz.addSysMsg(orgHistory.getDoctorFlow(), msgTitle , msgContent);
					return GlobalConstant.OPERATE_SUCCESSED;
				}
			}else{
				return GlobalConstant.OPRE_FAIL;
			}
		}else{
			return GlobalConstant.OPRE_FAIL;
		}
	}
	/**
	 * 保存医师信息审核
	 * @param role
	 * @param recruitWithBLOBs
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/{role}/doctor/saveAuditRecruit"})
	@ResponseBody
	public String saveAuditRecruit(@PathVariable String role, ResDoctorRecruitWithBLOBs recruitWithBLOBs, Model model){
		if(StringUtil.isNotBlank(recruitWithBLOBs.getRecruitFlow()) && StringUtil.isNotBlank(recruitWithBLOBs.getAuditStatusId()) && StringUtil.isNotBlank(recruitWithBLOBs.getDoctorFlow())){
			int result = jsResDoctorRecruitBiz.saveAuditRecruit(recruitWithBLOBs);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.OPRE_SUCCESSED;
			}
		}
		return GlobalConstant.OPRE_FAIL;
	}
	@RequestMapping(value={"/changeSpeTab"})
	public String changeSpeTab(String roleId,Model model){
		model.addAttribute("roleId",roleId);
		return "jszy/changeSpe/tab";
	}

	@RequestMapping(value = {"/changeSpeManage"})
	public String changeSpeManage(Integer currentPage, ResDoctor doctor, HttpServletRequest request,
								  Model model, String statusFlag, String operType, String roleId, String [] datas) {
		List<String> docTypeList = new ArrayList<>();
		String dataStr = "";
		if(datas!=null&&datas.length>0){
			docTypeList = Arrays.asList(datas);
			for(String d : datas){
				dataStr += d+",";
			}
		}
		model.addAttribute("dataStr",dataStr);
		//当前用户
		SysUser currentUser = GlobalContext.getCurrentUser();
		//当前机构
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
		//查询时用于存放审核状态
		List<String> changeStatusIdList = new ArrayList<String>();
		//查询时用于存放机构流水号
		List<String> orgFlowList = new ArrayList<String>();
		//用于查询
		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
		orgHistory.setChangeTypeId(JszyResChangeTypeEnum.SpeChange.getId());
		//查询时用于存放条件的map
		Map<String, Object> paramMap = new HashMap<>();
		List<SysOrg> orgs = new ArrayList<>();
		if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
			SysOrg sysorg4Search = new SysOrg();
			sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
			sysorg4Search.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			//省里所有医院
			orgs = orgBiz.searchOrg(sysorg4Search);
			if(orgs != null && orgs.size() > 0){
				for(SysOrg tempOrg : orgs){
					orgFlowList.add(tempOrg.getOrgFlow());
				}
			}
			if ("isQuery".equals(operType)) {
				//查询
				if ("pass".equals(statusFlag)) {
					//省厅审核通过
					changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalAuditPass.getId());
				} else if ("notPass".equals(statusFlag)) {
					//省厅审核不通过
					changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalAuditunPass.getId());
				} else if ("all".equals(statusFlag)){
					//省厅查询全部
					changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalAuditPass.getId());
					changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalAuditunPass.getId());
					//仅变更二级专业，基地审核通过的状态
					changeStatusIdList.add(JszyResChangeApplySpeEnum.BaseAuditPass.getId());
				}
			} else if ("isCheck".equals(operType)) {
				//审核
				changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalWaitingAudit.getId());
			}
		}
		if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
			//如果含有协同基地也要查询
			orgFlowList = searchJointOrgList(currentUser.getOrgFlow());
			orgFlowList.add(currentUser.getOrgFlow());
			if ("isQuery".equals(operType)) {
				//查询
				if ("pass".equals(statusFlag)) {
					//省厅审核通过
					changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalAuditPass.getId());
				} else if ("notPass".equals(statusFlag)) {
					//省厅审核不通过
					changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalAuditunPass.getId());
				} else if ("all".equals(statusFlag)){
					//省厅查询全部转出
					changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalAuditPass.getId());
					changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalAuditunPass.getId());
					changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalWaitingAudit.getId());
					changeStatusIdList.add(JszyResChangeApplySpeEnum.BaseAuditUnPass.getId());
					//仅变更二级专业，基地审核通过的状态
					changeStatusIdList.add(JszyResChangeApplySpeEnum.BaseAuditPass.getId());
				}
			} else if ("isCheck".equals(operType)) {
				//审核
				changeStatusIdList.add(JszyResChangeApplySpeEnum.BaseWaitingAudit.getId());
			}
		}
		paramMap.put("orgHistory", orgHistory);
		paramMap.put("changeStatusIdList", changeStatusIdList);
		paramMap.put("doctor", doctor);
		paramMap.put("docTypeList", docTypeList);
		if (!orgFlowList.isEmpty()) {
			paramMap.put("orgFlowList", orgFlowList);
		}
		PageHelper.startPage(currentPage, getPageSize(request));
//		List<JszyResDoctorOrgHistoryExt> historyExts=jsDocOrgHistoryBiz.seearchInfoByFlow(orgHistory, changeStatusIdList, doctor,orgFlowList);
		List<Map<String,String>> historyExts = jsDocOrgHistoryBiz.searchChangeSpeInfoByParamMap(paramMap);
		Map<String,List<JszyResDoctorOrgHistoryExt>> historysMap = new HashMap<>();
		if(historyExts != null && historyExts.size() >0){
			for(Map<String,String> temp : historyExts){
				String userFlow = temp.get("userFlow");
				ResDoctorOrgHistory history4Search = new ResDoctorOrgHistory();
				history4Search.setDoctorFlow(userFlow);
				history4Search.setChangeTypeId(JszyResChangeTypeEnum.SpeChange.getId());
				List<JszyResDoctorOrgHistoryExt> historys = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList(history4Search,null,null,null);
				historysMap.put(userFlow,historys);
			}
		}
		model.addAttribute("historyExts", historyExts);
		model.addAttribute("historysMap", historysMap);
		model.addAttribute("operType", operType);
		model.addAttribute("roleId", roleId);


		//页面跳转控制
		if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
			return "jszy/changeSpe/changeSpeInfo4global";
		} else if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
			return "jszy/changeSpe/changeSpeInfo4Local";
		}
		return "";
	}
//	@RequestMapping(value = {"/changeSpeRecords"})
//	public String changeSpeRecords(String recordFlow, String roleId, Model model) {
//
//		return "jszy/changeSpe/changeSpeRecords";
//	}
	@RequestMapping(value = {"/showCheckChangeSpe"})
	public String showCheckChangeSpe(String recordFlow, String roleId, Model model) {
		ResDoctorOrgHistory history = jsDocOrgHistoryBiz.readDocOrgHistory(recordFlow);
		//当前用户
		SysUser currentUser = GlobalContext.getCurrentUser();
		//当前机构
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
		ResDoctorRecruit recruit = doctorRecruitBiz.readResDoctorRecruit(history.getRecruitFlow());
		model.addAttribute("recruit",recruit);
		if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
			model.addAttribute("fileName", "localCheckChangeSpeFile");
			if(GlobalConstant.FLAG_Y.equals(history.getIsOnlySecond())){
				model.addAttribute("agree", JszyResChangeApplySpeEnum.BaseAuditPass.getId());
			}else {
				model.addAttribute("agree", JszyResChangeApplySpeEnum.GlobalWaitingAudit.getId());
			}
			model.addAttribute("disAgree", JszyResChangeApplySpeEnum.BaseAuditUnPass.getId());
		} else {
			model.addAttribute("fileName", "globalCheckChangeBaseFile");
			model.addAttribute("agree", JszyResChangeApplySpeEnum.GlobalAuditPass.getId());
			model.addAttribute("disAgree", JszyResChangeApplySpeEnum.GlobalAuditunPass.getId());
		}

		model.addAttribute("roleId", roleId);
		model.addAttribute("history", history);
		return "jszy/changeSpe/checkChangeSpe";
	}

	@RequestMapping(value = {"/checkChangeSpe"})
	@ResponseBody
	public String checkChangeSpe(String changeStatusId, String recordFlow, String auditOpinion,String roleId,ResDoctorRecruit doctorRecruit,
								 MultipartFile localCheckChangeSpeFile, MultipartFile globalCheckChangeBaseFile) {
		ResDoctorOrgHistory history = jsDocOrgHistoryBiz.readDocOrgHistory(recordFlow);
		history.setChangeStatusId(changeStatusId);
		history.setChangeStatusName(JszyResChangeApplySpeEnum.getNameById(changeStatusId));
		history.setRecordFlow(recordFlow);
		history.setAuditOpinion(auditOpinion);
		if(GlobalConstant.USER_LIST_LOCAL.equals(roleId)){
			history.setOutDate(DateUtil.getCurrDateTime());
		}
		if(GlobalConstant.USER_LIST_GLOBAL.equals(roleId)){
			history.setInDate(DateUtil.getCurrDateTime());
		}
		//保存附件，每次只会有一个MultipartFile
		String fileResult = "";
		if (localCheckChangeSpeFile != null && !localCheckChangeSpeFile.isEmpty()) {
			fileResult = saveChangeCheckFile("localCheckChangeSpeFile", localCheckChangeSpeFile, history);
		}
		if (globalCheckChangeBaseFile != null && !globalCheckChangeBaseFile.isEmpty()) {
			fileResult = saveChangeCheckFile("globalCheckChangeBaseFile", globalCheckChangeBaseFile, history);
		}
		if (GlobalConstant.VALIDATE_FILE_FAIL.equals(fileResult)) {
			return GlobalConstant.VALIDATE_FILE_FAIL;
		}
		int result = 0;
		if (JszyResChangeApplySpeEnum.GlobalAuditPass.getId().equals(changeStatusId)
				|| JszyResChangeApplySpeEnum.GlobalAuditunPass.getId().equals(changeStatusId)) {
			SysUser currentUser = GlobalContext.getCurrentUser();
			history.setAuditUserFlow(currentUser.getUserFlow());
			history.setAuditUserName(currentUser.getUserName());
			result = jsDocOrgHistoryBiz.auditChangeSpe(history,doctorRecruit);
		} else if(GlobalConstant.FLAG_Y.equals(history.getIsOnlySecond())){
			SysUser currentUser = GlobalContext.getCurrentUser();
			history.setAuditUserFlow(currentUser.getUserFlow());
			history.setAuditUserName(currentUser.getUserName());
			result = jsDocOrgHistoryBiz.auditChangeSpe(history,doctorRecruit);
		}else {
			result = jsDocOrgHistoryBiz.saveDocOrgHistory(history);
		}
		if (result > 0) {
			return GlobalConstant.OPRE_SUCCESSED;
		} else {
			return GlobalConstant.OPRE_FAIL;
		}
	}

	@RequestMapping(value={"/changeSpeMain"})
	public String changeSpeMain(Integer currentPage, ResDoctor doctor, HttpServletRequest request, Model model){
		ResDoctorOrgHistory orgHistory =new ResDoctorOrgHistory();
		orgHistory.setChangeTypeId(JszyResChangeTypeEnum.SpeChange.getId());
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
			SysUser sysUser=GlobalContext.getCurrentUser();
			orgHistory.setOrgFlow(sysUser.getOrgFlow());
			orgHistory.setChangeStatusId(JszyResChangeApplySpeEnum.BaseWaitingAudit.getId());
		}
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)) {
			orgHistory.setChangeStatusId(JszyResChangeApplySpeEnum.GlobalWaitingAudit.getId());
			List<SysOrg> orgs=new ArrayList<SysOrg>();
			SysOrg org=new SysOrg();
			SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			org.setOrgProvId(s.getOrgProvId());
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			orgs=orgBiz.searchAllSysOrg(org);
			model.addAttribute("orgs", orgs);
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JszyResDoctorOrgHistoryExt> historyExts=jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList(orgHistory, null, doctor,null);
		model.addAttribute("historyExts", historyExts);
		System.err.println(JSON.toJSON(historyExts));
		ResDoctorOrgHistory history =new ResDoctorOrgHistory();
		int speFlag =0;
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
			history.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			history.setChangeStatusId(JszyResChangeApplySpeEnum.BaseWaitingAudit.getId());
		}else{
			history.setChangeStatusId(JszyResChangeApplySpeEnum.GlobalWaitingAudit.getId());
		}
		history.setChangeTypeId(JszyResChangeTypeEnum.SpeChange.getId());
		List<ResDoctorOrgHistory> spe=jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		if(spe!=null && !spe.isEmpty()){
			speFlag=1;
		}
		model.addAttribute("speFlag",speFlag );
		return "jszy/changeSpeMain";
	}
	@RequestMapping(value = "/getNeedReducation",method={RequestMethod.POST})
	@ResponseBody
	public int getNeedReducation(String orgFlow){
		int result = 0;
		//获取需要减免维护的人员数量
		if(StringUtil.isNotBlank(orgFlow)){
			result = doctorDeptBiz.jszyCountReducation(orgFlow);
		}
		return result;
	}

	@RequestMapping(value={"/changeBaseMain"})
	public String changeBaseMain(){
		return "jszy/hospital/doctor/changeBaseMain";
	}

	@RequestMapping(value={"/speMain"})
	public String speMain(){
		return "jszy/hospital/speMain";
	}

	@RequestMapping(value={"/searchChangeSpe"})
	public String searchChangeSpe(Integer currentPage, ResDoctor doctor, HttpServletRequest request, Model model,String passFlag,String jointOrg){
		ResDoctorOrgHistory orgHistory =new ResDoctorOrgHistory();
		orgHistory.setChangeTypeId(JszyResChangeTypeEnum.SpeChange.getId());
		List<String> changeStatusIdList = new ArrayList<String>();
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
			SysUser sysUser=GlobalContext.getCurrentUser();
			orgHistory.setOrgFlow(sysUser.getOrgFlow());
			if(StringUtil.isNotBlank(passFlag)){
				if(GlobalConstant.FLAG_Y.equals(passFlag)){
					changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalAuditPass.getId());
				}else{
					changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalAuditunPass.getId());
				}
			}else{
				changeStatusIdList.add(JszyResChangeApplySpeEnum.BaseAuditUnPass.getId());
				changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalAuditPass.getId());
				changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalAuditunPass.getId());
				changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalWaitingAudit.getId());
			}
		}
		List<String> orgFlowList = new ArrayList<String>();
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
			List<SysOrg> orgs=new ArrayList<SysOrg>();
			SysOrg org=new SysOrg();
			SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			org.setOrgProvId(s.getOrgProvId());
			if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
				org.setOrgCityId(s.getOrgCityId());
			}
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			orgs=orgBiz.searchAllSysOrg(org);
			model.addAttribute("orgs", orgs);
			if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
				if(orgs!=null && !orgs.isEmpty()){
					for(SysOrg sysOrg :orgs){
						orgFlowList.add(sysOrg.getOrgFlow());
					}
				}
			}
			if(StringUtil.isNotBlank(passFlag)){
				if(GlobalConstant.FLAG_Y.equals(passFlag)){
					changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalAuditPass.getId());
				}else{
					changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalAuditunPass.getId());
				}
			}else{
				changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalAuditPass.getId());
				changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalAuditunPass.getId());
			}
		}
		SysOrg org =orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		if(null != org && null != org.getOrgLevelId() && org.getOrgLevelId().equals("CountryOrg")){
			model.addAttribute("countryOrgFlag","Y");
			if(null != jointOrg && jointOrg.equals("checked")){
				orgFlowList.add(orgHistory.getOrgFlow());
				orgHistory.setOrgFlow("");
				List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
				if(null != jointOrgs && jointOrgs.size() > 0){
					for(ResJointOrg so : jointOrgs){
						orgFlowList.add(so.getJointOrgFlow());
					}
				}
			}
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JszyResDoctorOrgHistoryExt> historyExts=jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList(orgHistory, changeStatusIdList, doctor,orgFlowList);
		model.addAttribute("historyExts", historyExts);
		return "jszy/changeSpeMain";
	}
	@RequestMapping(value={"/changeBaseTab"})
	public String changeBaseTab(String roleId,Model model){
		model.addAttribute("roleId",roleId);
		return "jszy/changeBase/tab";
	}

	/**
	 * 基地变更管理相关查询
	 *
	 * @param currentPage
	 * @param doctor
	 * @param request
	 * @param model
	 * @param statusFlag  记录所处的状态
	 * @param operType    查询（isQuery） 审核（isCheck）
	 * @param roleId      省厅、基地
	 * @param outOrIn     转入还是转出（基地角色筛选用）
	 * @return
	 */
	@RequestMapping(value = {"/changeBaseManage"})
	public String changeBaseManage(Integer currentPage, ResDoctor doctor, HttpServletRequest request, String outOrIn,
								   Model model, String statusFlag, String operType, String roleId, String [] datas) {
		List<String> docTypeList = new ArrayList<>();
		String dataStr = "";
		if(datas!=null&&datas.length>0){
			docTypeList = Arrays.asList(datas);
			for(String d : datas){
				dataStr += d+",";
			}
		}
		model.addAttribute("dataStr",dataStr);
		//当前用户
		SysUser currentUser = GlobalContext.getCurrentUser();
		//当前机构
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
		//查询时用于存放审核状态
		List<String> changeStatusIdList = new ArrayList<String>();
		//查询时用于存放机构流水号
		List<String> orgFlowList = new ArrayList<String>();
		//用于查询
		ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
		orgHistory.setChangeTypeId(JszyResChangeTypeEnum.BaseChange.getId());
		//查询时用于存放条件的map
		Map<String, Object> paramMap = new HashMap<>();
		List<SysOrg> orgs = new ArrayList<>();
		if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
			SysOrg sysorg4Search = new SysOrg();
			sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
			sysorg4Search.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			//省里所有医院
			orgs = orgBiz.searchOrg(sysorg4Search);
			if(orgs != null && orgs.size() > 0){
				for(SysOrg tempOrg : orgs){
					orgFlowList.add(tempOrg.getOrgFlow());
				}
			}
			if ("isQuery".equals(operType)) {
				//查询
				if ("pass".equals(statusFlag)) {
					//省厅审核通过
					changeStatusIdList.add(JszyResChangeApplyStatusEnum.GlobalApplyPass.getId());
				} else if ("notPass".equals(statusFlag)) {
					//省厅审核不通过
					changeStatusIdList.add(JszyResChangeApplyStatusEnum.GlobalApplyUnPass.getId());
				} else if ("all".equals(statusFlag)){
					//省厅查询全部转出
					changeStatusIdList.add(JszyResChangeApplyStatusEnum.GlobalApplyPass.getId());
					changeStatusIdList.add(JszyResChangeApplyStatusEnum.GlobalApplyUnPass.getId());
				}
			} else if ("isCheck".equals(operType)) {
				//审核
				changeStatusIdList.add(JszyResChangeApplyStatusEnum.GlobalApplyWaiting.getId());
			}
		}
		if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
			//如果含有协同基地也要查询
			orgFlowList = searchJointOrgList(currentUser.getOrgFlow());
			orgFlowList.add(currentUser.getOrgFlow());
			if ("isQuery".equals(operType)) {
				//查询
				if ("changeOut".equals(outOrIn)) {
					//转出
					if ("pass".equals(statusFlag)) {
						//省厅审核通过
						changeStatusIdList.add(JszyResChangeApplyStatusEnum.GlobalApplyPass.getId());
					} else if ("notPass".equals(statusFlag)) {
						//省厅审核不通过
						changeStatusIdList.add(JszyResChangeApplyStatusEnum.GlobalApplyUnPass.getId());
					} else if ("all".equals(statusFlag)){
						//查询全部转出
						getChangeStatusIdList(changeStatusIdList,outOrIn);
					}

				} else if ("changeIn".equals(outOrIn)) {
					//转入
					if ("pass".equals(statusFlag)) {
						//省厅审核通过
						changeStatusIdList.add(JszyResChangeApplyStatusEnum.GlobalApplyPass.getId());
					} else if ("notPass".equals(statusFlag)) {
						//省厅审核不通过
						changeStatusIdList.add(JszyResChangeApplyStatusEnum.GlobalApplyUnPass.getId());
					} else if ("all".equals(statusFlag)) {
						//查询全部转入
						getChangeStatusIdList(changeStatusIdList,outOrIn);
					}
				} else if ("all".equals(outOrIn)) {
					//全部（转入或转出）
					if ("pass".equals(statusFlag)) {
						//省厅审核通过（转入或转出）
						//查询时用于存放转入审核状态
						List<String> changeStatusIdList4In = new ArrayList<String>();
						changeStatusIdList4In.add(JszyResChangeApplyStatusEnum.GlobalApplyPass.getId());
						paramMap.put("changeStatusIdList4In",changeStatusIdList4In);
						//查询时用于存放转出审核状态
						List<String> changeStatusIdList4Out = new ArrayList<String>();
						changeStatusIdList4Out.add(JszyResChangeApplyStatusEnum.GlobalApplyPass.getId());
						paramMap.put("changeStatusIdList4Out",changeStatusIdList4Out);

					} else if ("notPass".equals(statusFlag)) {
						//省厅审核不通过（转入或转出）
						//查询时用于存放转入审核状态
						List<String> changeStatusIdList4In = new ArrayList<String>();
						changeStatusIdList4In.add(JszyResChangeApplyStatusEnum.GlobalApplyUnPass.getId());
						paramMap.put("changeStatusIdList4In",changeStatusIdList4In);
						//查询时用于存放转出审核状态
						List<String> changeStatusIdList4Out = new ArrayList<String>();
						changeStatusIdList4Out.add(JszyResChangeApplyStatusEnum.GlobalApplyUnPass.getId());
						paramMap.put("changeStatusIdList4Out",changeStatusIdList4Out);
					} else if ("all".equals(statusFlag)) {
						//查询全部记录状态（转入或转出）
						//查询时用于存放转入审核状态
						List<String> changeStatusIdList4In = new ArrayList<String>();
						getChangeStatusIdList(changeStatusIdList4In,"changeIn");
						paramMap.put("changeStatusIdList4In",changeStatusIdList4In);
						//查询时用于存放转出审核状态
						List<String> changeStatusIdList4Out = new ArrayList<String>();
						getChangeStatusIdList(changeStatusIdList4Out,"changeOut");
						paramMap.put("changeStatusIdList4Out",changeStatusIdList4Out);
					}
				}
			} else if ("isCheck".equals(operType)) {
				//审核
				if ("changeOut".equals(outOrIn)) {
					//转出————待转出审核
					changeStatusIdList.add(JszyResChangeApplyStatusEnum.OutApplyWaiting.getId());
				} else if ("changeIn".equals(outOrIn)) {
					//转入————待转入审核
					changeStatusIdList.add(JszyResChangeApplyStatusEnum.InApplyWaiting.getId());
				} else if ("all".equals(outOrIn)) {
					//全部（待转出审核或待转入审核）
					//查询时用于存放待转入审核
					List<String> changeStatusIdList4In = new ArrayList<String>();
					changeStatusIdList4In.add(JszyResChangeApplyStatusEnum.InApplyWaiting.getId());
					paramMap.put("changeStatusIdList4In",changeStatusIdList4In);
					//查询时用于存放待转出审核
					List<String> changeStatusIdList4Out = new ArrayList<String>();
					changeStatusIdList4Out.add(JszyResChangeApplyStatusEnum.OutApplyWaiting.getId());
					paramMap.put("changeStatusIdList4Out",changeStatusIdList4Out);
				}
			}
		}
		paramMap.put("orgHistory", orgHistory);
		paramMap.put("changeStatusIdList", changeStatusIdList);
		paramMap.put("doctor", doctor);
		paramMap.put("outOrIn", outOrIn);
		paramMap.put("docTypeList", docTypeList);
		if (!orgFlowList.isEmpty()) {
			paramMap.put("orgFlowList", orgFlowList);
		}
		PageHelper.startPage(currentPage, getPageSize(request));
//		List<JszyResDoctorOrgHistoryExt> historyExts=jsDocOrgHistoryBiz.seearchInfoByFlow(orgHistory, changeStatusIdList, doctor,orgFlowList);
		List<Map<String,String>> historyExts = jsDocOrgHistoryBiz.searchChangeOrgInfoByParamMap(paramMap);
		Map<String,List<JszyResDoctorOrgHistoryExt>> historysMap = new HashMap<>();
		if(historyExts != null && historyExts.size() >0){
			for(Map<String,String> temp : historyExts){
				String userFlow = temp.get("userFlow");
				ResDoctorOrgHistory history4Search = new ResDoctorOrgHistory();
				history4Search.setDoctorFlow(userFlow);
				history4Search.setChangeTypeId(JszyResChangeTypeEnum.BaseChange.getId());
				List<JszyResDoctorOrgHistoryExt> historys = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList(history4Search,null,null,null);
				historysMap.put(userFlow,historys);
			}
		}
		model.addAttribute("historyExts", historyExts);
		model.addAttribute("historysMap", historysMap);
		model.addAttribute("outOrIn", outOrIn);
		model.addAttribute("operType", operType);
		model.addAttribute("roleId", roleId);


		//页面跳转控制
		if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
			return "jszy/changeBase/changeBaseInfo4global";
		} else if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
			return "jszy/changeBase/changeBaseInfo4Local";
		}
		return "";
	}

	/**
	 * 查询当前基地下所有协同基地
	 *
	 * @param Flow
	 * @return
	 */
	public List<String> searchJointOrgList(String Flow) {
		List<String> jointOrgFlowList = new ArrayList<String>();
		List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(Flow);
		if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
			for (ResJointOrg jointOrg : resJointOrgList) {
				jointOrgFlowList.add(jointOrg.getJointOrgFlow());
			}
		}
		return jointOrgFlowList;
	}

	public List<String> getChangeStatusIdList(List<String> changeStatusIdList,String outOrIn){
		if("changeOut".equals(outOrIn)){
			changeStatusIdList.add(JszyResChangeApplyStatusEnum.OutApplyUnPass.getId());
			changeStatusIdList.add(JszyResChangeApplyStatusEnum.InApplyWaiting.getId());
			changeStatusIdList.add(JszyResChangeApplyStatusEnum.InApplyUnPass.getId());
			changeStatusIdList.add(JszyResChangeApplyStatusEnum.GlobalApplyWaiting.getId());
			changeStatusIdList.add(JszyResChangeApplyStatusEnum.GlobalApplyUnPass.getId());
			changeStatusIdList.add(JszyResChangeApplyStatusEnum.GlobalApplyPass.getId());
		} else if ("changeIn".equals(outOrIn)) {
			changeStatusIdList.add(JszyResChangeApplyStatusEnum.InApplyUnPass.getId());
			changeStatusIdList.add(JszyResChangeApplyStatusEnum.GlobalApplyWaiting.getId());
			changeStatusIdList.add(JszyResChangeApplyStatusEnum.GlobalApplyUnPass.getId());
			changeStatusIdList.add(JszyResChangeApplyStatusEnum.GlobalApplyPass.getId());
		}
		return changeStatusIdList;
	}

//	@RequestMapping(value = {"/changeOrgRecords"})
//	public String changeOrgRecords(String recordFlow, String roleId, Model model) {
//
//		return "jszy/changeBase/changeOrgRecords";
//	}

	@RequestMapping(value={"/audit"})
	public String audit(Model model,String recordFlow,String doctorFlow){
		if(StringUtil.isNotBlank(recordFlow)){
			ResDoctorOrgHistory orgHistory=jsDocOrgHistoryBiz.readDocOrgHistory(recordFlow);
			model.addAttribute("orgHistory", orgHistory);
			if(StringUtil.isNotBlank(doctorFlow)){
				ResDoctor doctor =resDoctorBiz.readDoctor(doctorFlow);
				model.addAttribute("doctor", doctor);
				ResDoctorRecruit doctorRecruit=new ResDoctorRecruit();
				doctorRecruit.setDoctorFlow(doctorFlow);
				doctorRecruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				List<ResDoctorRecruit> recruits=jsResDoctorRecruitBiz.searchResDoctorRecruitList(doctorRecruit, "CREATE_TIME DESC");
				if(recruits!=null){
					ResDoctorRecruit recruit=recruits.get(0);
					model.addAttribute("recruit", recruit);
				}
			}

		}
		return "jszy/hospital/doctor/audit";
	}
	/**
	 * 变更详情
	 * @param doctorFlow
	 * @param recordFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/getChangeOrgDetail"})
	public String getChangeOrgDetail(String doctorFlow, String recordFlow,String jointOrg, Model model){
		ResDoctorRecruit recruit=new ResDoctorRecruit();
		recruit.setDoctorFlow(doctorFlow);
		recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<ResDoctorRecruit> recruitsList=jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
		model.addAttribute("recruitsList", recruitsList);
		ResDocotrDelayTeturn docotrBackTeturn =new ResDocotrDelayTeturn();
		docotrBackTeturn.setDoctorFlow(doctorFlow);
		docotrBackTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
		List<ResDocotrDelayTeturn>  backList = resDoctorDelayTeturnBiz.searchInfo(docotrBackTeturn,null,null,null);
		model.addAttribute("recList", backList);
//		List<ResRec> delayList = resRecBiz.searchByRecWithBLOBs(ResRecTypeEnum.Delay.getId(), doctorFlow);
		ResDocotrDelayTeturn docotrDelayTeturn = new ResDocotrDelayTeturn();
		docotrDelayTeturn.setDoctorFlow(doctorFlow);
		docotrDelayTeturn.setTypeId(ResRecTypeEnum.Delay.getId());
		List<ResDocotrDelayTeturn>  delayList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn,null,null,null);
		model.addAttribute("delayList", delayList);
		return "jszy/hospital/changOrgInfo";
	}






//	update res_rec t set t.MODIFY_TIME = '20171023000000',t.dept_name = (SELECT sd.dept_name FROM sys_dept sd where sd.dept_flow = t.dept_flow);

	/**
	 * 展示审核页面
	 *
	 * @return
	 */
	@RequestMapping(value = {"/showCheckChangeOrg"})
	public String showCheckChangeOrg(String recordFlow, String roleId, Model model) {
		ResDoctorOrgHistory history = jsDocOrgHistoryBiz.readDocOrgHistory(recordFlow);

		if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
			//当前用户
			SysUser currentUser = GlobalContext.getCurrentUser();
			//当前机构
			SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
			String checkType = "";
			//如果当前登录基地的流水号和变更记录记录里的学员历史机构流水号相等且不是待转入基地审核--用于解决协同基地互转的问题。
			if (currentOrg.getOrgFlow().equals(history.getHistoryOrgFlow())
					&& !JszyResChangeApplyStatusEnum.InApplyWaiting.getId().equals(history.getChangeStatusId())) {
				checkType = "outCheck";
				model.addAttribute("checkType", checkType);
			} else {
				model.addAttribute("checkType", "inCheck");
				ResDoctorRecruit recruit = doctorRecruitBiz.readResDoctorRecruit(history.getRecruitFlow());
				model.addAttribute("recruit",recruit);
			}
			if ("outCheck".equals(checkType)) {
				model.addAttribute("fileName", "outCheckChangeBaseFile");
				model.addAttribute("agree", JszyResChangeApplyStatusEnum.InApplyWaiting.getId());
				model.addAttribute("disAgree", JszyResChangeApplyStatusEnum.OutApplyUnPass.getId());
			} else {
				model.addAttribute("fileName", "inCheckChangeBaseFile");
				model.addAttribute("agree", JszyResChangeApplyStatusEnum.GlobalApplyWaiting.getId());
				model.addAttribute("disAgree", JszyResChangeApplyStatusEnum.InApplyUnPass.getId());
			}
		} else {
			model.addAttribute("fileName", "globalCheckChangeBaseFile");
			model.addAttribute("agree", JszyResChangeApplyStatusEnum.GlobalApplyPass.getId());
			model.addAttribute("disAgree", JszyResChangeApplyStatusEnum.GlobalApplyUnPass.getId());
		}

		model.addAttribute("roleId", roleId);
		model.addAttribute("history", history);
		return "jszy/changeBase/checkChangeBase";
	}

	/**
	 * 转出基地审核，转入基地审核，省厅审核
	 *
	 * @param changeStatusId            审核状态
	 * @param recordFlow
	 * @param outCheckChangeBaseFile    转出基地审核附件
	 * @param changeInGraduationYear    转入同意后如果不认可原培训基地的培训记录需要重新安排结业考核年份
	 * @param auditOpinion              如果审核不通过需要填写意见
	 * @param inCheckChangeBaseFile     转入基地审核附件
	 * @param globalCheckChangeBaseFile 省厅审核附件
	 * @param isAgreeOldTrain           是否认可原培训基地的培训记录
	 * @param checkType                 是转入审核（inCheck）还是转出审核（outCheck）
	 * @return
	 */
	@RequestMapping(value = {"/checkChangeOrg"})
	@ResponseBody
	public String checkChangeOrg(String changeStatusId, String recordFlow, MultipartFile outCheckChangeBaseFile, String changeInGraduationYear, String auditOpinion,
								 MultipartFile inCheckChangeBaseFile, MultipartFile globalCheckChangeBaseFile, String isAgreeOldTrain, String checkType) {
		ResDoctorOrgHistory history = jsDocOrgHistoryBiz.readDocOrgHistory(recordFlow);
		history.setChangeStatusId(changeStatusId);
		history.setChangeStatusName(JszyResChangeApplyStatusEnum.getNameById(changeStatusId));
		history.setAuditOpinion(auditOpinion);
		if ("outCheck".equals(checkType)) {
			history.setOutDate(DateUtil.getCurrDateTime());
		}
		if ("inCheck".equals(checkType)) {
			history.setInDate(DateUtil.getCurrDateTime());
		}
		if (StringUtil.isNotBlank(isAgreeOldTrain)) {
			history.setIsAgreeOldTrain(isAgreeOldTrain);
		}
		if (StringUtil.isNotBlank(changeInGraduationYear)) {
			history.setChangeInGraduationYear(changeInGraduationYear);
		}
		//保存附件，每次只会有一个MultipartFile
		String fileResult = "";
		if (outCheckChangeBaseFile != null && !outCheckChangeBaseFile.isEmpty()) {
			fileResult = saveChangeCheckFile("outCheckChangeBaseFile", outCheckChangeBaseFile, history);
		}
		if (inCheckChangeBaseFile != null && !inCheckChangeBaseFile.isEmpty()) {
			fileResult = saveChangeCheckFile("inCheckChangeBaseFile", inCheckChangeBaseFile, history);
		}
		if (globalCheckChangeBaseFile != null && !globalCheckChangeBaseFile.isEmpty()) {
			fileResult = saveChangeCheckFile("globalCheckChangeBaseFile", globalCheckChangeBaseFile, history);
		}
		if (GlobalConstant.VALIDATE_FILE_FAIL.equals(fileResult)) {
			return GlobalConstant.VALIDATE_FILE_FAIL;
		}
		int result = 0;
		if (JszyResChangeApplyStatusEnum.GlobalApplyPass.getId().equals(changeStatusId)
				|| JszyResChangeApplyStatusEnum.GlobalApplyUnPass.getId().equals(changeStatusId)) {
			SysUser currentUser = GlobalContext.getCurrentUser();
			history.setAuditUserFlow(currentUser.getUserFlow());
			history.setAuditUserName(currentUser.getUserName());
			result = jsDocOrgHistoryBiz.auditChangeOrg(history);
		} else {
			result = jsDocOrgHistoryBiz.saveDocOrgHistory(history);
		}
		if (result > 0) {
			return GlobalConstant.OPRE_SUCCESSED;
		} else {
			return GlobalConstant.OPRE_FAIL;
		}
	}

	private String saveChangeCheckFile(String filePropertyName, MultipartFile file, ResDoctorOrgHistory history) {
		String fileResult = jsDocOrgHistoryBiz.checkFile(file);
		if (!GlobalConstant.FLAG_Y.equals(fileResult)) {
			return GlobalConstant.VALIDATE_FILE_FAIL;
		} else {
			String resultPath = jsDocOrgHistoryBiz.saveCheckFileToDirs(file, "changeRecruitFile", history.getChangeTypeId());
			if ("outCheckChangeBaseFile".equals(filePropertyName)) {
				history.setOutCheckChangeBaseFile(resultPath);
			}
			if ("inCheckChangeBaseFile".equals(filePropertyName)) {
				history.setInCheckChangeBaseFile(resultPath);
			}
			if ("globalCheckChangeBaseFile".equals(filePropertyName)) {
				history.setGlobalCheckChangeBaseFile(resultPath);
			}
			if ("localCheckChangeSpeFile".equals(filePropertyName)) {
				history.setLocalCheckChangeSpeFile(resultPath);
			}
			return resultPath;
		}
	}
	@RequestMapping(value = "/doctorNumSearch")
	public String doctorNumSearch(String cityId,String orgLevel,String orgFlow,String trainTypeId,String sessionNumber,String datas[],Model model){

		List<String>orgFlowList=new ArrayList<String>();
		List<String>docTypeList=new ArrayList<String>();
		List<SysOrg> orgs=new ArrayList<>();
		ResDoctor doctor=new ResDoctor();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		SysOrg org =new SysOrg();
		if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)){
			SysUser user=GlobalContext.getCurrentUser();
			org=orgBiz.readSysOrg(user.getOrgFlow());
			if(StringUtil.isBlank(orgFlow)){
				if(OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())){
					List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
					List<String> jointOrgFlowList =new ArrayList<>();
					if(jointOrgs!=null&&jointOrgs.size()>0){
						for (ResJointOrg join: jointOrgs) {
							jointOrgFlowList.add(join.getJointOrgFlow());
						}
					}
					if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
						orgs =orgBiz.searchOrgFlowIn(jointOrgFlowList);
					}
					orgs.add(org);
				}else {
					orgs.add(org);
				}
			}else {
				SysOrg org2=orgBiz.readSysOrg(orgFlow);
				orgs.add(org2);
			}
		}else if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
			if(StringUtil.isNotBlank(orgFlow)){
				org=orgBiz.readSysOrg(orgFlow);
				orgs.add(org);
			}else{
				SysUser user=GlobalContext.getCurrentUser();
				org=orgBiz.readSysOrg(user.getOrgFlow());
				SysOrg sysOrg2=new SysOrg();
				sysOrg2.setOrgProvId("320000");
				sysOrg2.setOrgCityId(org.getOrgCityId());
				sysOrg2.setOrgTypeId(OrgTypeEnum.Hospital.getId());
				orgs=orgBiz.searchOrg(sysOrg2);
//				if(StringUtil.isNotBlank(orgLevel)){
//					sysOrg2.setOrgLevelId(orgLevel);
//				}
//				sysOrg2.setOrgCityId(org.getOrgCityId());
//				sysOrg2.setOrgTypeId(OrgTypeEnum.Hospital.getId());
//				if(StringUtil.isNotBlank(orgLevel)){
//					orgs=orgBiz.searchOrg(sysOrg2);
//				}else {
//					orgs=jointOrgBiz.searchCouAndProList(sysOrg2);
//				}
			}
		}else {
			//查询所有国家基地
			List<SysOrg> sysOrgList=new ArrayList<SysOrg>();
			SysOrg org2=new SysOrg();
			org2.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			org2.setOrgProvId(s.getOrgProvId());
			if(StringUtil.isNotBlank(orgFlow))
			{
				orgFlowList.add(orgFlow);
			}else{
				if(StringUtil.isNotBlank(cityId))
				{
					org2.setOrgCityId(cityId);
				}
				if(StringUtil.isNotBlank(orgLevel)){
					org2.setOrgLevelId(orgLevel);
					org2.setOrgTypeId(OrgTypeEnum.Hospital.getId());
					sysOrgList=orgBiz.searchAllSysOrg(org2);
				}else{
					sysOrgList=jointOrgBiz.searchCouAndProList(org2);
				}
				if(sysOrgList!=null&&sysOrgList.size()>0)
				{
					for(SysOrg o:sysOrgList){
						orgFlowList.add(o.getOrgFlow());
					}
				}
			}
			if(orgFlowList!=null&&orgFlowList.size()>0){
				orgs=orgBiz.searchOrgFlowIn(orgFlowList);
			}
		}
		ResDoctorRecruit recruit=new ResDoctorRecruit();
		recruit.setSessionNumber(sessionNumber);
		recruit.setCatSpeId(trainTypeId);
		recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
		if(orgFlowList!=null&&orgFlowList.size()>0){
			orgs=orgBiz.searchOrgFlowIn(orgFlowList);
		}
		//每家基地每个专业的医师培训记录总数
		Map<Object, Object> totalCountMap=new HashMap<Object,Object>();//保存每家基地的培训记录总数
		Map<Object, Object> orgSpeFlagMap=new HashMap<Object,Object>();//基地专业标志的的map
		if(orgs!=null&&orgs.size()>0) {
			if(StringUtil.isBlank(orgFlow)&&StringUtil.isNotBlank(orgLevel)){
				List<Map<String,Object>> doctorCountList = new ArrayList<>();
				for (SysOrg o : orgs) {
					List<Map<String,Object>> countList = new ArrayList<>();
					orgFlowList=new ArrayList<String>();
					orgFlowList.add(o.getOrgFlow());
					if(OrgLevelEnum.CountryOrg.getId().equals(orgLevel)) {//查询协同基地人数
						List<ResJointOrg>jointOrgList=jointOrgBiz.searchResJointByOrgFlow(o.getOrgFlow());//查询每家基地的协同基地
						if(jointOrgList!=null&&!jointOrgList.isEmpty()){
							for(ResJointOrg resJointOrg:jointOrgList){
								orgFlowList.add(resJointOrg.getJointOrgFlow());
							}
						}
						countList = resStatisticBiz.statisticJointCountByOrg(recruit,orgFlowList,trainTypeId,docTypeList);
						if(jointOrgList!=null&&!jointOrgList.isEmpty()&&countList!=null&&countList.size()>1){
							String orgFlow2 = o.getOrgFlow();
							int count =0;
							for (int i=0;i<countList.size();i++){
								count+=Integer.parseInt(countList.get(i).get("COUNT").toString());
							}
							Map<String,Object> bemap = new HashMap<>();
							bemap.put("ORGFLOW",orgFlow2);
							bemap.put("COUNT",count);
							countList.clear();
							countList.add(bemap);
						}
					}else {
						countList = resStatisticBiz.statisticJointCountByOrg(recruit,orgFlowList,trainTypeId,docTypeList);
					}
					doctorCountList.addAll(countList);
				}
				Map<String,String> countMap = new HashMap<>();
				for (Map<String,Object> bemap:doctorCountList) {
					countMap.put(bemap.get("ORGFLOW").toString(),bemap.get("COUNT").toString());
				}
				model.addAttribute("countMap",countMap);
				model.addAttribute("orgs",orgs);
				model.addAttribute("seeFlag","Y");
			}else {
				for (SysOrg o : orgs) {
					orgFlowList=new ArrayList<String>();
					ResOrgSpe resOrgSpe=new ResOrgSpe();
					resOrgSpe.setOrgFlow(o.getOrgFlow());
					resOrgSpe.setRecordStatus(GlobalConstant.FLAG_Y);
					List<ResOrgSpe> orgSpeList=resOrgSpeBiz.searchResOrgSpeList(resOrgSpe);
					if(orgSpeList!=null&&!orgSpeList.isEmpty()){//每家主基地的专业
						for(ResOrgSpe r:orgSpeList){
							orgSpeFlagMap.put(o.getOrgFlow()+r.getSpeTypeId()+r.getSpeId(),GlobalConstant.FLAG_Y);
						}
					}
					orgFlowList.add(o.getOrgFlow());//查询每家基地及其协同基地的总数
					if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)) {
//					if(StringUtil.isBlank(cityId)) {//如果地市不为空时，只查询当前地市的基地人数。否则需要查询协同基地人数
						List<ResJointOrg>jointOrgList=jointOrgBiz.searchResJointByOrgFlow(o.getOrgFlow());//查询每家基地的协同基地
						if(jointOrgList!=null&&!jointOrgList.isEmpty()){
							for(ResJointOrg resJointOrg:jointOrgList){
								orgFlowList.add(resJointOrg.getJointOrgFlow());
							}
						}
//					}
					}
					//每家基地每个专业的医师培训记录总数
					List<Map<String,Object>> doctorCountList = new ArrayList<>();
					//只有当基地orgLevel为空且trainTypeId为空时才统计图内展示三类学员数量
					if(StringUtil.isBlank(orgLevel)&&StringUtil.isBlank(trainTypeId)){
						recruit.setCatSpeId("countByTrainTypeId");
						model.addAttribute("countByTrainTypeId","Y");
						doctorCountList=resStatisticBiz.statisticJointCount(recruit,orgFlowList,doctor,docTypeList);
					}else {
						doctorCountList=resStatisticBiz.statisticJointCount(recruit,orgFlowList,doctor,docTypeList);
					}
					if(doctorCountList!=null&&!doctorCountList.isEmpty()){
						for(Map<String,Object> en:doctorCountList){
							Object key = null;
							//只有当基地orgLevel为空且trainTypeId为空时才统计图内展示三类学员数量
							if(StringUtil.isBlank(orgLevel)&&StringUtil.isBlank(trainTypeId)){
								key=en.get("key");
							}else {
								key=o.getOrgFlow()+en.get("key");
							}
							Object value= en.get("value");
							totalCountMap.put(key, value);
						}
					}
				}
				model.addAttribute("seeFlag","N");
			}
		}
		model.addAttribute("totalCountMap", totalCountMap);
		model.addAttribute("orgSpeFlagMap", orgSpeFlagMap);
		return "jszy/global/report/doctorNumSearch";
	}
	@RequestMapping(value = "/graduateNumSearch")
	public String graduateNumSearch(String sessionNumber,String workOrgName,Model model) throws UnsupportedEncodingException {
		workOrgName = java.net.URLDecoder.decode(workOrgName,"UTF-8");
		List<Map<String,Object>> graduates =new ArrayList<>();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		if(StringUtil.isBlank(sessionNumber)){
			sessionNumber=DateUtil.getYear();
		}
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("workOrgName",workOrgName);
		if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
			SysUser user=GlobalContext.getCurrentUser();
			SysOrg org=orgBiz.readSysOrg(user.getOrgFlow());
			SysOrg sysOrg2=new SysOrg();
			sysOrg2.setOrgProvId("320000");
			sysOrg2.setOrgCityId(org.getOrgCityId());
			sysOrg2.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			paramMap.put("cityOrg",sysOrg2);
		}
		Map<String, Object> graduatesMap=new HashMap<String, Object>();
		if(StringUtil.isBlank(workOrgName)){
			List<SysDict> SendSchoolList = DictTypeEnum.SendSchool.getSysDictList();
			List<String> schools = new ArrayList<>();
			for (SysDict dict:SendSchoolList) {
				schools.add(dict.getDictName());
			}
			paramMap.put("SendSchoolList",schools);
			graduates=jsResDoctorBiz.searchGraduatesByOrg(paramMap);
			if(graduates!=null&&graduates.size()>0){
				for (Map<String,Object> map:graduates) {
					graduatesMap.put(map.get("SCHOOLNAME").toString(),map.get("GRADUATESCOUNT"));
				}
			}
			model.addAttribute("schools",schools);
			model.addAttribute("seeFlag","Y");
		}else {
			graduates=jsResDoctorBiz.searchGraduates(paramMap);
			if(graduates!=null&&graduates.size()>0){
				for (Map<String,Object> map:graduates) {
					graduatesMap.put(map.get("TRAININGSPEID").toString(),map.get("GRADUATESCOUNT"));
				}
			}
			model.addAttribute("seeFlag","N");
		}
		model.addAttribute("graduatesMap",graduatesMap);
		return "jszy/global/report/graduateNumSearch";
	}

	@RequestMapping(value = "/orgSpe")
	public String orgSpe(String cityId,String orgLevel,Model model,String orgFlow){

		List<String>orgFlowList=new ArrayList<String>();
		SysOrg sysOrg2=new SysOrg();
		if(StringUtil.isNotBlank(cityId))
		{
			sysOrg2.setOrgCityId(cityId);
		}
		if(StringUtil.isNotBlank(orgLevel))
		{
			sysOrg2.setOrgLevelId(orgLevel);
		}
		sysOrg2.setOrgProvId("320000");
		sysOrg2.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		SysOrg org= new SysOrg();
		if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
			if(StringUtil.isNotBlank(orgFlow)){
				org=orgBiz.readSysOrg(orgFlow);
			}else {
				SysUser user=GlobalContext.getCurrentUser();
				org=orgBiz.readSysOrg(user.getOrgFlow());
			}
			sysOrg2.setOrgCityId(org.getOrgCityId());
		}
		List<SysOrg> orgs=orgBiz.searchAllSysOrg(sysOrg2);
		if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
			if(StringUtil.isNotBlank(orgFlow)){
				orgs = new ArrayList<>();
				orgs.add(org);
			}
		}
		if(orgs!=null&&orgs.size()>0)
		{
			for(SysOrg o:orgs){
				orgFlowList.add(o.getOrgFlow());
			}
		}
		//每家基地每个专业的医师培训记录总数
		Map<Object, Object> orgSpeFlagMap=new HashMap<Object,Object>();//基地专业标志的的map
		if(orgs!=null&&orgs.size()>0) {
			for (SysOrg o : orgs) {
				orgFlowList=new ArrayList<String>();
				ResOrgSpe resOrgSpe=new ResOrgSpe();
				resOrgSpe.setOrgFlow(o.getOrgFlow());
				resOrgSpe.setRecordStatus(GlobalConstant.FLAG_Y);
				List<ResOrgSpe> orgSpeList=resOrgSpeBiz.searchResOrgSpeList(resOrgSpe);
				if(orgSpeList!=null&&!orgSpeList.isEmpty()){//每家主基地的专业
					for(ResOrgSpe r:orgSpeList){
						orgSpeFlagMap.put(o.getOrgFlow()+r.getSpeId(),GlobalConstant.FLAG_Y);
					}
				}
			}
		}
		model.addAttribute("orgSpeFlagMap", orgSpeFlagMap);
		return "jszy/global/report/orgSpe";
	}

	/**
	 * 加载医师审核列表
	 * @param resDoctorRecruit
	 * @param sysUser
	 * @param currentPage
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/recruitList")
	public String doctorRecruitList(ResDoctorRecruit resDoctorRecruit,SysUser sysUser,String []datas, Integer currentPage, HttpServletRequest request, Model model){
		Map<String,Object> param = new HashMap<>();
		List<String> docTypeList = new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			docTypeList = Arrays.asList(datas);
		}
		SysUser currUser=GlobalContext.getCurrentUser();
		resDoctorRecruit.setOrgFlow(currUser.getOrgFlow());
		resDoctorRecruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Auditing.getId());
		param.put("docTypeList",docTypeList);
		param.put("resDoctorRecruit",resDoctorRecruit);
		param.put("user",sysUser);
		PageHelper.startPage(currentPage, getPageSize(request));
//		List<JszyResDoctorRecruitExt> recruitList=jsResDoctorRecruitBiz.resDoctorRecruitExtList(resDoctorRecruit, sysUser, null);
		List<JszyResDoctorRecruitExt> recruitList=jsResDoctorRecruitBiz.resDoctorRecruitExtList(param);
		model.addAttribute("recruitList",recruitList);
		return  "jszy/recruitList";
	}

	/**
	 * 获取单条医师审核信息
	 * @param role
	 * @param doctorFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/{role}/doctor/auditRecruit"})
	public String getRecruitAudit(@PathVariable String role, String recruitFlow,String doctorFlow, Model model){
		ResDoctorRecruit recruit=new ResDoctorRecruit();
		recruit.setDoctorFlow(doctorFlow);
		recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<ResDoctorRecruit> recruitsList=jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
		model.addAttribute("recruitsList", recruitsList);
		return "jszy/hospital/doctor/auditRecruit";
	}

	/**
	 * 加载转出
	 * @return
	 */
	@RequestMapping(value={"/turnOutMain"})
	public String turnOutMain(Integer currentPage, ResDoctor resDoctor, HttpServletRequest request, String jointOrg, Model model){
		SysUser currUser = GlobalContext.getCurrentUser();
		ResDoctorOrgHistory docOrgHistory = new ResDoctorOrgHistory();
		docOrgHistory.setHistoryOrgFlow(currUser.getOrgFlow());
		docOrgHistory.setChangeTypeId(JszyResChangeTypeEnum.BaseChange.getId());
		List<String> orgFlowList = new ArrayList<String>();
		SysOrg org =orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		if(null != org && null != org.getOrgLevelId() && org.getOrgLevelId().equals("CountryOrg")){
			model.addAttribute("countryOrgFlag","Y");
			if(null != jointOrg && jointOrg.equals("checked")){
				orgFlowList.add(org.getOrgFlow());
				docOrgHistory.setHistoryOrgFlow("");
				List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
				if(null != jointOrgs && jointOrgs.size() > 0){
					for(ResJointOrg so : jointOrgs){
						orgFlowList.add(so.getJointOrgFlow());
					}
				}
			}
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JszyResDoctorOrgHistoryExt> docOrgHistoryExtList = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList(docOrgHistory, null, resDoctor,orgFlowList);
		model.addAttribute("docOrgHistoryExtList", docOrgHistoryExtList);
		ResDoctorOrgHistory history =new ResDoctorOrgHistory();
		int baseFlag=0;
		history.setOrgFlow(currUser.getOrgFlow());
		history.setChangeTypeId(JszyResChangeTypeEnum.BaseChange.getId());
		history.setChangeStatusId(JszyResChangeApplyStatusEnum.InApplyWaiting.getId());
		List<ResDoctorOrgHistory> baseOne=jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		history.setOrgFlow("");
		history.setHistoryOrgFlow(currUser.getOrgFlow());
		history.setChangeStatusId(JszyResChangeApplyStatusEnum.OutApplyWaiting.getId());
		List<ResDoctorOrgHistory> baseTwo=jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		if((baseOne!=null&&!baseOne.isEmpty())||(baseTwo!=null&&!baseTwo.isEmpty())){
			baseFlag=1;
		}
		model.addAttribute("baseFlag", baseFlag);
		return "jszy/hospital/doctor/turnOutMain";
	}

	/**
	 * 加载转入
	 * @return
	 */
	@RequestMapping(value={"/turnInMain"})
	public String turnInMain(Integer currentPage, ResDoctor resDoctor, String jointOrg, HttpServletRequest request, Model model){
		SysUser currUser = GlobalContext.getCurrentUser();
		ResDoctorOrgHistory docOrgHistory = new ResDoctorOrgHistory();
		docOrgHistory.setOrgFlow(currUser.getOrgFlow());
		docOrgHistory.setChangeTypeId(JszyResChangeTypeEnum.BaseChange.getId());
		List<String> changeStatusIdList = new ArrayList<String>();
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
			changeStatusIdList.add(JszyResChangeApplyStatusEnum.InApplyWaiting.getId());//待转入审核
			changeStatusIdList.add(JszyResChangeApplyStatusEnum.GlobalApplyPass.getId());//转入审核通过
			changeStatusIdList.add(JszyResChangeApplyStatusEnum.InApplyUnPass.getId());//转入审核不通过
		}
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)) {
			changeStatusIdList.add(JszyResChangeApplyStatusEnum.GlobalApplyPass.getId());
			changeStatusIdList.add(JszyResChangeApplyStatusEnum.InApplyUnPass.getId());
		}
		List<String> orgFlowList = new ArrayList<String>();
		SysOrg org =orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		if(null != org && null != org.getOrgLevelId() && org.getOrgLevelId().equals("CountryOrg")){
			model.addAttribute("countryOrgFlag","Y");
			if(null != jointOrg && jointOrg.equals("checked")){
				orgFlowList.add(docOrgHistory.getOrgFlow());
				docOrgHistory.setOrgFlow("");
				List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
				if(null != jointOrgs && jointOrgs.size() > 0){
					for(ResJointOrg so : jointOrgs){
						orgFlowList.add(so.getJointOrgFlow());
					}
				}
			}
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JszyResDoctorOrgHistoryExt> docOrgHistoryExtList = jsDocOrgHistoryBiz.searchDoctorOrgHistoryExtList(docOrgHistory, changeStatusIdList, resDoctor,orgFlowList);
		model.addAttribute("docOrgHistoryExtList", docOrgHistoryExtList);
		ResDoctorOrgHistory history =new ResDoctorOrgHistory();
		int baseFlag=0;
		history.setOrgFlow(currUser.getOrgFlow());
		history.setChangeTypeId(JszyResChangeTypeEnum.BaseChange.getId());
		history.setChangeStatusId(JszyResChangeApplyStatusEnum.InApplyWaiting.getId());
		List<ResDoctorOrgHistory> baseOne=jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		history.setOrgFlow("");
		history.setHistoryOrgFlow(currUser.getOrgFlow());
		history.setChangeStatusId(JszyResChangeApplyStatusEnum.OutApplyWaiting.getId());
		List<ResDoctorOrgHistory> baseTwo=jsDocOrgHistoryBiz.searchDoctorOrgHistoryList(history);
		if((baseOne!=null&&!baseOne.isEmpty())||(baseTwo!=null&&!baseTwo.isEmpty())){
			baseFlag=1;
		}
		model.addAttribute("baseFlag", baseFlag);
		return "jszy/hospital/doctor/turnInMain";
	}








	/**
	 * 在培医师花名册
	 */
	@RequestMapping(value="/exTrainingDocPdf")
	public void exTrainingDocPdf(String orgFlow,final HttpServletResponse response) throws Exception{
		if(StringUtil.isBlank(orgFlow)){
			throw new Exception("机构标识符为空...");
		}

		SysOrg org = orgBiz.readSysOrg(orgFlow);
		if(org==null){
			throw new Exception("读取机构信息失败...");
		}

		final String fileName = org.getOrgName() + "在培医师花名册";
		String outputFileClass = ResourceLoader.getPath("");
		String outputFile = new File(outputFileClass)
				+ "/load/" + fileName + ".pdf" ;
		File file = new File(outputFile);
		//root 储存的数据
		final Map<String,Object> root=new HashMap<String,Object>();

		//基地名称
		root.put("orgName",org.getOrgName());
		//当前用户
		SysUser user = GlobalContext.getCurrentUser();
		root.put("currUser",user);
		//当前时间
		root.put("currDate",DateUtil.getCurrDate());


		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("orgFlow",orgFlow);
		//各届别各类型人员数量
		//获取当前配置届别
		String sessionNumber = InitConfig.getSysCfg("jsres_doctorCount_sessionNumber");
		List<String> sessions = new ArrayList<String>();
		if(StringUtil.isNotBlank(sessionNumber)){
			Integer sessionInt = Integer.valueOf(sessionNumber);

			paramMap.put("sessionNumber",sessionInt);
			List<Map<String,Object>> counts = jsResDoctorBiz.getDocCountBySession(paramMap);
			String k = "oneYear";
			setRootDocCount(k,counts,root);
			root.put(k,sessionInt.toString());
			sessions.add(sessionInt.toString());

			sessionInt--;
			paramMap.put("sessionNumber",sessionInt);
			k = "twoYear";
			counts = jsResDoctorBiz.getDocCountBySession(paramMap);
			setRootDocCount(k,counts,root);
			root.put(k,sessionInt.toString());
			sessions.add(sessionInt.toString());

			sessionInt--;
			paramMap.put("sessionNumber",sessionInt);
			k = "threeYear";
			counts = jsResDoctorBiz.getDocCountBySession(paramMap);
			setRootDocCount(k,counts,root);
			root.put(k,sessionInt.toString());
			sessions.add(sessionInt.toString());
		}
		//获取所有学员
		paramMap.put("sessions",sessions);
		List<Map<String,Object>> docListMap = jsResDoctorBiz.getDocByOrg(paramMap);
		root.put("docMapList",docListMap);

		// 模板数据
		DocumentVo vo = new DocumentVo() {
			@Override
			public String findPrimaryKey() {
				return fileName;
			}

			@Override
			public Map<String, Object> fillDataMap() {
				return root;
			}
		};

		String template = "jsres/trainingDocTemplate.html";
		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// 生成pdf
		pdfGenerator.generate(template, vo, outputFile);

		pubFileBiz.downFile(file,response);
	}
	private void setRootDocCount(String k,List<Map<String,Object>> counts,Map<String,Object> root){
		for(Map<String,Object> m : counts){
			Object docTypeId = m.get("doctorTypeId");
			Object docCount = m.get("docCount");
			if(docTypeId!=null){
				root.put(docTypeId.toString()+k,docCount);
			}
		}
	}
	/**
	 * 医师信息详情
	 */
	@RequestMapping(value={"/{role}/doctor/doctorPassedList"})
	public String doctorPassedList(@PathVariable String role,Model model,String doctorFlow,String recruitFlow,String studyFlag,String zlFlag){
		ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());//  页面过滤 方便判断是否允许退回
		recruit.setDoctorFlow(doctorFlow);
		recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        if(GlobalConstant.FLAG_Y.equals(zlFlag)){
            recruit.setRecruitFlow(recruitFlow);
            List<JsresRecruitDocInfo> recruitInfoList = jsResDoctorRecruitBiz.searchResDoctorRecruitInfoList(recruit, "CREATE_TIME");
            model.addAttribute("recruitList", recruitInfoList);
        }else{
            List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
		    model.addAttribute("recruitList", recruitList);
        }
		if(StringUtil.isNotBlank(studyFlag)){
			if(GlobalConstant.FLAG_Y.equals(studyFlag)){
				model.addAttribute("studyFlag", studyFlag);
			}
		}
		List<String> operUserFlowList =new ArrayList<String>();
		operUserFlowList.add(doctorFlow);ResDocotrDelayTeturn docotrBackTeturn = new ResDocotrDelayTeturn();
		docotrBackTeturn.setDoctorFlow(doctorFlow);
		docotrBackTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
		List<ResDocotrDelayTeturn>  backList = resDoctorDelayTeturnBiz.searchInfo(docotrBackTeturn,null,null,null);
//		List<ResRec> recList = resRecBiz.searchRecInfo(resRec, operUserFlowList);
		model.addAttribute("recList", backList);
		ResDocotrDelayTeturn docotrDelayTeturn = new ResDocotrDelayTeturn();
		docotrDelayTeturn.setDoctorFlow(doctorFlow);
		docotrDelayTeturn.setTypeId(ResRecTypeEnum.Delay.getId());
		List<ResDocotrDelayTeturn>  delayList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn,null,null,null);
//		List<ResRec> delayList = resRecBiz.searchByRecWithBLOBs(ResRecTypeEnum.Delay.getId(), doctorFlow);
		model.addAttribute("delayList", delayList);
		model.addAttribute("zlFlag", zlFlag);

		return "/jszy/province/doctor/doctorMain";
	}

	/**
	 * 跳转至更新医师走向
	 */
	@RequestMapping(value="/updateDoctorRecruit")
	public String updateDoctorRecruit(String recruitFlow, Model model,String doctorFlow){
		if (StringUtil.isNotBlank(recruitFlow)) {
			String isFlag = GlobalConstant.FLAG_N;
			ResDoctorRecruit recruit=jsResDoctorRecruitBiz.readRecruit(recruitFlow);
			ResDoctorRecruit doctorRecruit = new ResDoctorRecruit();
			doctorRecruit.setDoctorFlow(doctorFlow);
			doctorRecruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
			List<ResDoctorRecruitWithBLOBs> recruitList = jsResDoctorRecruitBiz.searchRecruitWithBLOBs(doctorRecruit);
			if(recruitList!=null && !recruitList.isEmpty()){
				if(recruitList.get(0).getRecruitFlow().equals(recruitFlow)){
					isFlag = GlobalConstant.FLAG_Y;
				}
			}
			model.addAttribute("isFlag", isFlag);
			model.addAttribute("doctorRecruit", recruit);
		}
		return  "jszy/hospital/doctor/editDoctorTrend";
	}
	public void operDocotrDelayTeturn(ResDocotrDelayTeturn docotrDelayTeturn,String typeId){
		String doctorFlow = docotrDelayTeturn.getDoctorFlow();
		ResDoctor doctor = resDoctorBiz.findByFlow(doctorFlow);
		ResDoctorRecruit doctorRecruit = new ResDoctorRecruit();
		doctorRecruit.setDoctorFlow(doctor.getDoctorFlow());
		doctorRecruit.setOrgFlow(doctor.getOrgFlow());
		doctorRecruit.setCatSpeId(doctor.getTrainingTypeId());
		doctorRecruit.setSpeId(doctor.getTrainingSpeId());
		doctorRecruit.setAuditStatusId(ResDoctorAuditStatusEnum.Passed.getId());
		ResDoctorRecruit recruit = doctorRecruitBiz.searchRecruitByResDoctor(doctorRecruit);
		docotrDelayTeturn.setOrgFlow(recruit.getOrgFlow());
		docotrDelayTeturn.setOrgName(recruit.getOrgName());
		docotrDelayTeturn.setSessionNumber(recruit.getSessionNumber());
		docotrDelayTeturn.setTrainingYears(recruit.getTrainYear());
		docotrDelayTeturn.setDoctorFlow(doctorFlow);
		docotrDelayTeturn.setDoctorName(doctor.getDoctorName());
		docotrDelayTeturn.setTrainingSpeId(recruit.getSpeId());
		docotrDelayTeturn.setTrainingSpeName(recruit.getSpeName());
		docotrDelayTeturn.setTrainingTypeId(recruit.getCatSpeId());
		docotrDelayTeturn.setTrainingTypeName(recruit.getCatSpeName());
		docotrDelayTeturn.setDoctorTypeId(doctor.getDoctorTypeId());
		docotrDelayTeturn.setDoctorTypeName(doctor.getDoctorTypeName());
		//注：结业考核年份graduationYear,记录类型typeId延期和退培分开考虑
		if("back".equals(typeId)){
			docotrDelayTeturn.setGraduationYear(recruit.getGraduationYear());
		}
		docotrDelayTeturn.setRecruitFlow(recruit.getRecruitFlow());
		docotrDelayTeturn.setPolicyTime(DateUtil.getCurrentTime());
		docotrDelayTeturn.setPolicyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		docotrDelayTeturn.setPolicyUserName(GlobalContext.getCurrentUser().getUserName());
	}

	@RequestMapping(value="/updateDoctorTrend")
	@ResponseBody
	public String updateDoctorTrend(ResDoctorRecruitWithBLOBs recruitWithBLOBs,ResDocotrDelayTeturn docotrDelayTeturn,HttpServletRequest request){
		int result=0;int backResult=0;int  delayResult=0; int userblack=0;
		result = jsResDoctorRecruitBiz.updateDoctorTrend(recruitWithBLOBs);
		String flag=GlobalConstant.OPERATE_SUCCESSED;
		List<PubFile> pubFiles = new ArrayList<>();
		if(StringUtil.isNotBlank(docotrDelayTeturn.getReasonId())){//退培
			//以下为多文件上传********************************************
			//创建一个通用的多部分解析器
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			//判断 request 是否有文件上传,即多部分请求
			if(multipartResolver.isMultipart(request)){
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
							//保存附件
							PubFile pubFile = new PubFile();
							if (file.getSize() > 10 * 1024 * 1024) {
								return GlobalConstant.UPLOAD_IMG_SIZE_ERROR + "10M";
							}
							//取得当前上传文件的文件名称
							String oldFileName = file.getOriginalFilename();
							//如果名称不为“”,说明该文件存在，否则说明该文件不存在
							if (StringUtil.isNotBlank(oldFileName)) {
								//定义上传路径
								String dateString = DateUtil.getCurrDate2();
								String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "returnImg" + File.separator + dateString;
								File fileDir = new File(newDir);
								if (!fileDir.exists()) {
									fileDir.mkdirs();
								}
								//重命名上传后的文件名
								String originalFilename = "";
								originalFilename = PkUtil.getUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
								File newFile = new File(fileDir, originalFilename);
								try {
									file.transferTo(newFile);
								} catch (Exception e) {
									e.printStackTrace();
									throw new RuntimeException("保存文件失败！");
								}
								String filePath = File.separator + "returnImg" + File.separator + dateString + File.separator + originalFilename;
								pubFile.setFilePath(filePath);
								pubFile.setFileName(oldFileName);
								pubFile.setProductType("退培文件");
								pubFiles.add(pubFile);
							}
						}
					}
					//记录上传该文件后的时间
					//int finaltime = (int) System.currentTimeMillis();
				}
			}
			//以上为多文件上传********************************************
			// 注：1.以前版本中退培学员由基地负责（无需经省厅同意），基地将doctorRecruit表auditStatusId置为NotSubmit，在基地退培时将医师信息置为空，
			//	   2.现优化为基地提交退培学员由省厅审核，基地将doctorRecruit表auditStatusId置为NotSubmit，省厅审核同意时将医师信息置为空，若不同意将doctorRecruit表auditStatusId置为Passed
			//     3.经过现版本优化可能会出现与以前医师信息出现无法融合的问题
			//     4.需求经优化后，退培附件为必传，故退培信息不会出现没有附件的记录
			//     5.省厅审核状态；ResBaseStatusEnum
			//该功能因RES_REC表拆分原因再次优化将RES_REC中退培延期的数据迁移至RES_DOCOTR_DELAY_TETURN中业务逻辑未发生变化。

			//更新docotrDelayTeturn数据
			operDocotrDelayTeturn(docotrDelayTeturn,"back");
			docotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
			docotrDelayTeturn.setTypeName(ResRecTypeEnum.ReturnTraining.getName());
			docotrDelayTeturn.setAuditStatusId(ResBaseStatusEnum.Auditing.getId());
			docotrDelayTeturn.setAuditStatusName(ResBaseStatusEnum.Auditing.getName());
			docotrDelayTeturn.setPolicyName("1".equals(docotrDelayTeturn.getPolicyId())?"协议退培":"违约退培");
			docotrDelayTeturn.setReasonName("1".equals(docotrDelayTeturn.getReasonId())?"辞职":("2".equals(docotrDelayTeturn.getReasonId())?"考研":"其他"));
			backResult = resDoctorDelayTeturnBiz.edit(docotrDelayTeturn,pubFiles);
		}
		if(StringUtil.isNotBlank(docotrDelayTeturn.getDelayreason())){//延期
			//更新docotrDelayTeturn数据
			operDocotrDelayTeturn(docotrDelayTeturn,"delay");
			docotrDelayTeturn.setTypeId(ResRecTypeEnum.Delay.getId());
			docotrDelayTeturn.setTypeName(ResRecTypeEnum.Delay.getName());
			//应产品要求，延期流程由需要省厅审核，改为基地管理员直接延期不经省厅审核
//			docotrDelayTeturn.setAuditStatusId(ResBaseStatusEnum.Auditing.getId());
//			docotrDelayTeturn.setAuditStatusName(ResBaseStatusEnum.Auditing.getName());
			delayResult = jsResRecBiz.saveDelayInfo(docotrDelayTeturn);
		}
		if(result==GlobalConstant.ZERO_LINE){
			flag=GlobalConstant.OPRE_FAIL;
		}
		if((StringUtil.isNotBlank(docotrDelayTeturn.getReasonId())&&backResult==GlobalConstant.ZERO_LINE)){
			flag=GlobalConstant.OPRE_FAIL;
		}
		if((StringUtil.isNotBlank(docotrDelayTeturn.getReasonId())&&(StringUtil.isNotBlank(docotrDelayTeturn.getPolicyId())&&docotrDelayTeturn.getPolicyId().equals("2"))&&backResult==GlobalConstant.ZERO_LINE)){
			flag=GlobalConstant.OPRE_FAIL;
		}
		if((StringUtil.isNotBlank(docotrDelayTeturn.getDelayreason())&&delayResult==GlobalConstant.ZERO_LINE)){
			flag=GlobalConstant.OPRE_FAIL;
		}
		return flag;
	}
	@RequestMapping(value = "/checkStatus")
	@ResponseBody
	public String checkStatus(String doctorFlow){
		SysUser user = userBiz.readSysUser(doctorFlow);
		String flag = GlobalConstant.FLAG_Y;
		if(UserStatusEnum.Locked.getId().equals(user.getStatusId()) || UserStatusEnum.SysLocked.getId().equals(user.getStatusId())){
			flag= GlobalConstant.FLAG_N;
		}
		return flag;
	}

	/**
	 * 调整学员方案减免
	 * @param doctorFlow
	 * @param rotationFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/operReduction")
	public String operReduction(String doctorFlow,String rotationFlow,String secondRotationFlow,String orgFlow, Model model){
		if(StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(doctorFlow)&& StringUtil.isNotBlank(orgFlow)){
			ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
			if(doctor!=null){
				model.addAttribute("doctor",doctor);

				String degreeType = doctor.getDegreeCategoryId();
				if(JszyResDegreeCategoryEnum.ClinicMaster.getId().equals(degreeType) || JszyResDegreeCategoryEnum.ClinicDoctor.getId().equals(degreeType)){
					PubUserResume resume = userResumeBiz.readPubUserResume(doctorFlow);
					String content = resume.getUserResume();
					if(StringUtil.isNotBlank(content)){
						BaseUserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(content, BaseUserResumeExtInfoForm.class);
						model.addAttribute("userResumeExt", userResumeExt);
					}
				}
			}

			List<ResDoctorRecruit> recruitList = doctorRecruitBiz.searchRecruitByDoctor(doctorFlow);
			model.addAttribute("recruitList",recruitList);

			List<SchRotationDept> rotationDeptList = rotationDeptBiz.searchSchRotationDept(rotationFlow);
			if(rotationDeptList!=null && !rotationDeptList.isEmpty()){
				Map<String,List<SchRotationDept>> rotationDeptListMap = new HashMap<String, List<SchRotationDept>>();
				for(SchRotationDept srd : rotationDeptList){
					String key = srd.getGroupFlow();
					if(rotationDeptListMap.get(key)==null){
						List<SchRotationDept> srds = new ArrayList<SchRotationDept>();
						srds.add(srd);
						rotationDeptListMap.put(key,srds);
					}else{
						rotationDeptListMap.get(key).add(srd);
					}
				}
				model.addAttribute("rotationDeptListMap",rotationDeptListMap);
			}

			List<SchRotationGroup> groupList = groupBiz.searchSchRotationGroup(rotationFlow);
			model.addAttribute("groupList",groupList);
			if(StringUtil.isNotBlank(secondRotationFlow)) {

				 rotationDeptList = rotationDeptBiz.searchSchRotationDept(secondRotationFlow);
				if(rotationDeptList!=null && !rotationDeptList.isEmpty()){
					Map<String,List<SchRotationDept>> rotationDeptListMap = new HashMap<String, List<SchRotationDept>>();
					for(SchRotationDept srd : rotationDeptList){
						String key = srd.getGroupFlow();
						if(rotationDeptListMap.get(key)==null){
							List<SchRotationDept> srds = new ArrayList<SchRotationDept>();
							srds.add(srd);
							rotationDeptListMap.put(key,srds);
						}else{
							rotationDeptListMap.get(key).add(srd);
						}
					}
					model.addAttribute("rotationDeptListMap2",rotationDeptListMap);
				}
				List<SchRotationGroup> groupList2 = groupBiz.searchSchRotationGroup(secondRotationFlow);
				model.addAttribute("groupList2", groupList2);
			}

			List<SchDoctorDept> doctorDeptList = doctorDeptBiz.searchSchDoctorDeptIgnoreStatus(doctorFlow,orgFlow);
			if(doctorDeptList!=null && !doctorDeptList.isEmpty()){
				Map<String,SchDoctorDept> doctorDeptMap = new HashMap<String, SchDoctorDept>();
				for(SchDoctorDept sdd : doctorDeptList){
					String key = sdd.getGroupFlow()+sdd.getStandardDeptId();
					doctorDeptMap.put(key,sdd);
				}
				model.addAttribute("doctorDeptMap",doctorDeptMap);
			}
		}
		return  "jszy/hospital/operReduction";
	}
	@RequestMapping(value={"/{role}/doctor/doctorPass"})
	public String doctorPass(@PathVariable String role,Model model,String recruitFlow,String studyFlag){
		ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
		model.addAttribute("recruit",recruit);
		model.addAttribute("studyFlag", studyFlag);
		return "/jszy/province/doctor/doctorMain";
	}
	@RequestMapping(value="/saveReductionOper")
	@ResponseBody
	public String saveReductionOper(@RequestBody List<SchDoctorDept> doctorDeptList){
		int result = doctorDeptBiz.editDoctorDeptList(doctorDeptList);
		if(result!=GlobalConstant.ZERO_LINE){
			return GlobalConstant.OPRE_SUCCESSED_FLAG;
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}
	@RequestMapping(value={"/auditInfo"})
	public String auditInfo(Model model){
		return "jszy/hospital/doctor/auditInfo";
	}
	@RequestMapping(value={"/info"})
	public String info(Model model,String doctorFlow){
		ResDoctor doctor=resDoctorBiz.readDoctor(doctorFlow);
		model.addAttribute("doctor", doctor);
		return "jszy/hospital/doctor/info";
	}
	/**
	 * 转出
	 * @return
	 */
	@RequestMapping(value={"/turnOutOrg"})
	@ResponseBody
	public String turnOutOrg(ResDoctorOrgHistory history,String time){
		if(StringUtil.isNotBlank(history.getRecordFlow()) && StringUtil.isNotBlank(history.getChangeStatusId()) && StringUtil.isNotBlank(history.getDoctorFlow())){
			if(StringUtil.isBlank(time)){
				time="";
			}
			int result = jsDocOrgHistoryBiz.auditTurnOutOrg(history,time);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.OPRE_SUCCESSED;
			}
		}
		return GlobalConstant.OPRE_FAIL;
	}
	/**
	 * 转入
	 * @return
	 */
	@RequestMapping(value={"/turnInOrg"})
	@ResponseBody
	public String turnInOrg(String recordFlow, String changeStatusId, String doctorFlow,String time,String chooseFlag){
		if(StringUtil.isNotBlank(recordFlow)){
			int result = jsDocOrgHistoryBiz.auditTurnInOrg(recordFlow, changeStatusId, doctorFlow,time,chooseFlag);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.OPRE_SUCCESSED;
			}
		}
		return GlobalConstant.OPRE_FAIL;
	}
	//安全中心
	@RequestMapping(value="/accounts")
	public String accounts(Model model) throws Exception{
		SysUser user = (SysUser) getSessionAttribute(GlobalConstant.CURRENT_USER);
		SysLogExample example = new SysLogExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andUserFlowEqualTo(user.getUserFlow());
		example.setOrderByClause("create_time desc");
		List<SysLog> logs = logMapper.selectByExample(example);
		if(logs!=null && logs.size()>0){
			model.addAttribute("log", logs.get(0));
		}
		return "jszy/accounts";
	}
//	修改密码
	@RequestMapping(value={"/modPasswd"})
	public String modPasswd(Model model){
		return "jszy/system/modPasswd";
	}
}
