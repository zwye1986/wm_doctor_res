package com.pinde.sci.ctrl.jszy;

import com.pinde.core.model.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jszy.*;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.jsres.JsResTrainYearEnum;
import com.pinde.sci.enums.jszy.*;
import com.pinde.sci.enums.pub.UserNationEnum;
import com.pinde.core.common.enums.ResDoctorAuditStatusEnum;
import com.pinde.core.common.enums.ResRecTypeEnum;
import com.pinde.core.common.enums.ResScoreTypeEnum;
import com.pinde.sci.enums.sys.*;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.form.jszy.JszyResDoctorInfoForm;
import com.pinde.sci.form.jszy.JszyUserInfoExtForm;
import com.pinde.sci.model.jszy.JszyDoctorInfoExt;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author tiger
 *
 *
 */
@Controller
@RequestMapping("/jszy/doctor")
public class JszyResDoctorController extends GeneralController {
	@Autowired
	private IJszyResDoctorBiz jsResDoctorBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IJszyResOrgSpeBiz resBaseSpeBiz;
	@Autowired
	private IJszyResDoctorRecruitBiz jsResDoctorRecruitBiz;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private ISchRotationBiz rotationBiz;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
    private IResRecBiz resRecBiz;
    @Autowired
	private ISchArrangeResultBiz resultBiz;
	@Autowired
	private ISchRotationGroupBiz groupBiz;
	@Autowired
	private IJszyResDoctorOrgHistoryBiz resDoctorOrgHistoryBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
    private IJszyResRecBiz jsResRecBiz;
    @Autowired
	private ISchDoctorDeptBiz doctorDeptBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IResScoreBiz resScoreBiz;
	@Autowired
	private IJszyDoctorAuthBiz doctorAuthBiz;
	@Autowired
	private IResDoctorDelayTeturnBiz resDoctorDelayTeturnBiz;
	/**
	 * 住院医师主界面
	 */
	@RequestMapping(value="/index")
	public String index(Model model,Integer currentPage, HttpServletRequest request){
		setSessionAttribute(GlobalConstant.USER_LIST_SCOPE, GlobalConstant.USER_LIST_PERSONAL);
		SysUser user = GlobalContext.getCurrentUser();
		PubMsgExample example = new PubMsgExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andReceiverEqualTo(user.getUserFlow()).andMsgTypeIdEqualTo(MsgTypeEnum.Sys.getId());
		example.setOrderByClause("MSG_TIME desc");
		PageHelper.startPage(currentPage,getPageSize(request));
		List<PubMsg> msgList = msgBiz.searchMessageWithBLOBs(example);
		List<PubMsg> msgs = msgBiz.searchMessageWithBLOBs(example);
		if(msgList!=null && msgList.size()>0){
			model.addAttribute("msgList",msgList);
			int newMsg = 0;
			if(msgs!=null && !msgs.isEmpty()){
				for(PubMsg msg : msgs){
					if(!GlobalConstant.FLAG_Y.equals(msg.getReceiveFlag())){
						newMsg++;
					}
				}
				model.addAttribute("newMsg",newMsg);
			}
		}
		ResDoctor doctor=resDoctorBiz.searchByUserFlow(user.getUserFlow());
		model.addAttribute("doctor", doctor);
		PubUserResume userResume=userResumeBiz.readPubUserResume(user.getUserFlow());
		BaseUserResumeExtInfoForm userResumeExt=null;
		if(userResume != null){
			String xmlContent =  userResume.getUserResume();
			if(StringUtil.isNotBlank(xmlContent)){
				//xml转换成JavaBean
				  userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, BaseUserResumeExtInfoForm.class);
				model.addAttribute("userResumeExt", userResumeExt);
			}
		}
		List<SysDict> sysDictList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
		String benkeResult=GlobalConstant.FLAG_N;String result=GlobalConstant.FLAG_N;
		if(sysDictList!=null&&!sysDictList.isEmpty()){
			if(userResumeExt!=null){
				for(SysDict dict :sysDictList){
					if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
						if(dict.getDictId().equals(userResumeExt.getGraduatedId())){
							benkeResult=GlobalConstant.FLAG_Y;
						}
					}
				}
			}
		}
		String school=GlobalConstant.FLAG_N;
		List<SysDict> schoolList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.SendSchool.getId());
		if(doctor!=null){
			if(StringUtil.isNotBlank(doctor.getDoctorTypeId())&& JszyResDocTypeEnum.Graduate.getId().equals(doctor.getDoctorTypeId())){
				if(schoolList!=null&&!schoolList.isEmpty()){
					for(SysDict dic:schoolList){
						if(dic.getDictId().equals(doctor.getWorkOrgId())){
							school=GlobalConstant.FLAG_Y;
						}
					}
				}
			}
		}
		model.addAttribute("school", school);
		model.addAttribute("benkeResult", benkeResult);
		int recordCount=0;
		ResDoctorRecruit recruit=new ResDoctorRecruit();
		recruit.setDoctorFlow(user.getUserFlow());
		recordCount=jsResDoctorRecruitBiz.searchDoctorNum(recruit);
		//当前用户为在校研究生且派送学校已付费
		if(doctor!=null&&doctor.getDoctorTypeId()!=null && doctor.getDoctorTypeId().equals(JszyResDocTypeEnum.Graduate.getId())){
			String cfgCode = "jswjw_sendSchool_"+doctor.getWorkOrgId()+"_P007";
			SysCfg cfg=cfgBiz.read(cfgCode);
			if(cfg==null||GlobalConstant.RECORD_STATUS_Y.equals(cfg.getCfgValue())){
				String cfgCode2="jswjw_"+user.getOrgFlow()+"_P001";
				SysCfg cfg2=cfgBiz.read(cfgCode2);
				if((cfg2!=null&&GlobalConstant.RECORD_STATUS_Y.equals(cfg2.getCfgValue()))){
					model.addAttribute("schoolPerm",true);
				}else {
					model.addAttribute("schoolPerm",false);
				}
			}
			if((cfg!=null&&GlobalConstant.RECORD_STATUS_N.equals(cfg.getCfgValue()))){
				model.addAttribute("schoolPerm",false);
			}
		}
		model.addAttribute("recordCount", recordCount);
		
		model.addAttribute("isPassed", jsResDoctorRecruitBiz.getRecruitStatus(user.getUserFlow()));
		return "jszy/doctor/index";
	}
	/**
	 * 主页面
	 */
	@RequestMapping(value="/main")
	public String main(Model model){
		//获取培训记录
		SysUser currUser = GlobalContext.getCurrentUser();
		ResDoctor resDoctor = resDoctorBiz.readDoctor(currUser.getUserFlow());
		if(resDoctor != null){
			model.addAttribute("doctorFlow",resDoctor.getDoctorFlow());
		}
		String doctorFlow = currUser.getUserFlow();
		ResDoctorRecruit recruit =new ResDoctorRecruit();
		recruit.setDoctorFlow(doctorFlow);
		recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
		if(recruitList != null && !recruitList.isEmpty()){
			model.addAttribute("recruitList", recruitList);
			for(ResDoctorRecruit rec : recruitList){
				if(JszyResDoctorAuditStatusEnum.NotSubmit.getId().equals(rec.getAuditStatusId())
						|| JszyResDoctorAuditStatusEnum.Auditing.getId().equals(rec.getAuditStatusId())
						|| JszyResDoctorAuditStatusEnum.NotPassed.getId().equals(rec.getAuditStatusId())){
					model.addAttribute("unPassed", true);
				}
			}
		}
		ResDocotrDelayTeturn docotrBackTeturn = new ResDocotrDelayTeturn();
		docotrBackTeturn.setDoctorFlow(doctorFlow);
		docotrBackTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
		List<ResDocotrDelayTeturn>  backList = resDoctorDelayTeturnBiz.searchInfo(docotrBackTeturn,null,null,null);
//        List<ResRec> resRecList = resRecBiz.searchByRecWithBLOBs(ResRecTypeEnum.ReturnTraining.getId(), currUser.getUserFlow());
		model.addAttribute("resRecList", backList);

		ResDocotrDelayTeturn docotrDelayTeturn = new ResDocotrDelayTeturn();
		docotrDelayTeturn.setDoctorFlow(doctorFlow);
		docotrDelayTeturn.setTypeId(ResRecTypeEnum.Delay.getId());
		List<ResDocotrDelayTeturn>  delayList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn,null,null,null);
//        List<ResRec> delayList = resRecBiz.searchByRecWithBLOBs(ResRecTypeEnum.Delay.getId(), currUser.getUserFlow());
		model.addAttribute("delayList", delayList);
		return "jszy/doctor/main";
	}

	@RequestMapping(value="/exportForBack")
	public void exportForBack(HttpServletResponse response,ResDocotrDelayTeturn docotrDelayTeturn,Model model,String userFlow,String sessionNumber)throws Exception{
		Map<Object, Object> backInfoMap =new HashMap<Object,Object>();
		List<String> userFlowList = new ArrayList<String>();
		SysUser user = GlobalContext.getCurrentUser();
		if(StringUtil.isNotBlank(userFlow)){
			userFlowList.add(userFlow);
		}
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
			docotrDelayTeturn.setOrgFlow(user.getOrgFlow());
		}
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)) {
			List<SysOrg> orgs=new ArrayList<SysOrg>();
			SysOrg org=new SysOrg();
			SysOrg s=orgBiz.readSysOrg(user.getOrgFlow());
			org.setOrgProvId(s.getOrgProvId());
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			orgs=orgBiz.searchAllSysOrg(org);
			model.addAttribute("orgs", orgs);
		}
		docotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
		List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn,null,null,null);
		DecimalFormat df = new DecimalFormat("0.00%");
		ResDoctor doctor =new  ResDoctor();
		if(StringUtil.isNotBlank(docotrDelayTeturn.getOrgName())){
			doctor.setOrgName(docotrDelayTeturn.getOrgName());
		}
		if(StringUtil.isNotBlank(docotrDelayTeturn.getOrgFlow())){
			doctor.setOrgFlow(docotrDelayTeturn.getOrgFlow());
		}
		doctor.setSessionNumber(sessionNumber);
		int count=resDoctorBiz.findDoctorCountInOrg(doctor);
		float percent=0;String per="";
		if(resRecList!=null&&!resRecList.isEmpty()&&count!=0){
			percent=(float)resRecList.size()/(float)count;
			per= df.format(percent);
		}else{
			per= 0+"%";
		}
		String flag=GlobalConstant.FLAG_Y;
		if(StringUtil.isBlank(docotrDelayTeturn.getOrgFlow())||StringUtil.isBlank(sessionNumber)){
			flag=GlobalConstant.FLAG_N;
		}
		backInfoMap.put(GlobalConstant.FLAG_N, per);
		jsResDoctorBiz.exportForBack2(resRecList,backInfoMap,response,flag);
	}
	/**
	 * 获取退培信息
	 * @param model
	 * @return
	 * @throws DocumentException
	 */
	@RequestMapping(value="/backTrainInfo")
	public String backTrainInfo(ResDocotrDelayTeturn docotrDelayTeturn,Model model,String userFlow, Integer currentPage, HttpServletRequest request,String sessionNumber,String speName,String reasonId,String policyId,String seeFlag,String jointOrg) throws DocumentException{
		Map<Object, Object> backInfoMap =null;
		List<String> userFlowList = new ArrayList<String>();
		SysUser user = GlobalContext.getCurrentUser();
		if(StringUtil.isNotBlank(userFlow)){
			userFlowList.add(userFlow);
		}
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)&&StringUtil.isBlank(seeFlag)) {
			docotrDelayTeturn.setOrgFlow(user.getOrgFlow());
		}
		List<SysOrg> orgs=new ArrayList<SysOrg>();
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
			SysOrg org=new SysOrg();
			SysOrg s=orgBiz.readSysOrg(user.getOrgFlow());
			org.setOrgProvId(s.getOrgProvId());
			if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
				org.setOrgCityId(s.getOrgCityId());
			}
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			orgs=orgBiz.searchAllSysOrg(org);
			model.addAttribute("orgs", orgs);
		}
		List<String> orgFlowList=new ArrayList<String>();
		if(StringUtil.isBlank(docotrDelayTeturn.getOrgFlow())&&getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
			if(orgs!=null&&!orgs.isEmpty()){
				for(SysOrg org:orgs){
					orgFlowList.add(org.getOrgFlow());
				}
			}
		}
		docotrDelayTeturn.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
		String tpOrg = docotrDelayTeturn.getOrgFlow();
		SysOrg org =orgBiz.readSysOrg(user.getOrgFlow());
		if(null != org && null != org.getOrgLevelId() && org.getOrgLevelId().equals("CountryOrg")){
            model.addAttribute("countryOrgFlag", GlobalConstant.FLAG_Y);
			if(null != jointOrg && jointOrg.equals("checked")){
				orgFlowList.add(docotrDelayTeturn.getOrgFlow());
				docotrDelayTeturn.setOrgFlow("");
				List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
				if(null != jointOrgs && jointOrgs.size() > 0){
					for(ResJointOrg so : jointOrgs){
						orgFlowList.add(so.getJointOrgFlow());
					}
				}
			}
		}

		PageHelper.startPage(currentPage,getPageSize(request));
		if(StringUtil.isNotBlank(userFlow)){
			docotrDelayTeturn.setDoctorFlow(userFlow);
		}
		//参数flags为查询通过或不通过时用
		List<ResDocotrDelayTeturn> resRecList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn,orgFlowList,null,null);
//		List<Map<String,String>> resRecList = resRecBiz.searchInfo2(resRec, currdoctor, userFlowList, orgFlowList);
		model.addAttribute("resRecList",resRecList);
		//退培比例
		if(StringUtil.isNotBlank(tpOrg)&&StringUtil.isNotBlank(sessionNumber)){
			List<Map<String,String>> list = new ArrayList<>();
			if(null !=orgFlowList && orgFlowList.size() > 0) {//有协同基地
//				list=resRecBiz.findTrainCharts(orgFlowList,sessionNumber,null);
				list = resDoctorDelayTeturnBiz.findJszyTrainCharts(orgFlowList,sessionNumber,null);
			}else {
				List<String> orgFlowList2 = new ArrayList<>();
				orgFlowList2.add(tpOrg);
//				list=resRecBiz.findTrainCharts(orgFlowList2,sessionNumber,null);
				list = resDoctorDelayTeturnBiz.findJszyTrainCharts(orgFlowList2,sessionNumber,null);
			}
			DecimalFormat df = new DecimalFormat("0.00%");
			ResDoctor doctor =new  ResDoctor();
			if(StringUtil.isNotBlank(docotrDelayTeturn.getOrgFlow())){
				doctor.setOrgFlow(docotrDelayTeturn.getOrgFlow());
			}
			doctor.setSessionNumber(sessionNumber);
			int count = 0;
			if(null !=orgFlowList && orgFlowList.size() > 0){//有协同基地
				for (int i = 0; i < orgFlowList.size(); i++) {
					doctor.setOrgFlow(orgFlowList.get(i));
					count += resDoctorBiz.findDoctorCountInOrg(doctor);
				}
			}else{
				count = resDoctorBiz.findDoctorCountInOrg(doctor);
			}
			float percent=0;
			int resRecListSize=0;
			if(list!=null&&!list.isEmpty()&&count!=0){
				for(Map<String,String> recMap : list){
					String temp = recMap.get("countNum");
					resRecListSize+=Integer.parseInt(temp);
				}
			}
			if(resRecList!=null&&!resRecList.isEmpty()&&count!=0){
				percent=(float)resRecListSize/(float)count;
				model.addAttribute("percent", df.format(percent));
				model.addAttribute("resRecListSize",resRecListSize);
			}else{
				model.addAttribute("percent", 0+"%");
			}
		}
		return "jszy/backTrainInfo";
	}

	/**
	 * 延期操作
	 * @throws DocumentException
	 */
	@RequestMapping(value="/delay")
	public String delay(Model model,ResDocotrDelayTeturn docotrDelayTeturn,Integer currentPage,HttpServletRequest request,String userFlow,String jointOrg) throws DocumentException{
		List<String> orgFlowList = new ArrayList<String>();
		List<SysOrg> orgs=new ArrayList<SysOrg>();
		List<String> operUserFlowList = new ArrayList<String>();
		if(StringUtil.isNotBlank(userFlow)){
			operUserFlowList.add(userFlow);
		}
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)) {
			SysOrg org=new SysOrg();
			SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			org.setOrgProvId(s.getOrgProvId());
			if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
				org.setOrgCityId(s.getOrgCityId());
			}
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			orgs=orgBiz.searchAllSysOrg(org);
			model.addAttribute("orgs", orgs);
		}
		if(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)){
			if(orgs!=null && !orgs.isEmpty()){
				for(SysOrg o : orgs){
					orgFlowList.add(o.getOrgFlow());
				}
			}
		}
		docotrDelayTeturn.setTypeId(ResRecTypeEnum.Delay.getId());
		docotrDelayTeturn.setTypeName(ResRecTypeEnum.Delay.getName());
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
			docotrDelayTeturn.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		}

		SysOrg org =orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		if(null != org && null != org.getOrgLevelId() && org.getOrgLevelId().equals("CountryOrg")){
            model.addAttribute("countryOrgFlag", GlobalConstant.FLAG_Y);
			if(null != jointOrg && jointOrg.equals("checked")){
				orgFlowList.add(docotrDelayTeturn.getOrgFlow());
				docotrDelayTeturn.setOrgFlow("");
				List<ResJointOrg> jointOrgs =jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
				if(null != jointOrgs && jointOrgs.size() > 0){
					for(ResJointOrg so : jointOrgs){
						orgFlowList.add(so.getJointOrgFlow());
					}
				}
			}
		}
		PageHelper.startPage(currentPage,getPageSize(request));
//		List<ResRec> resRecList = resRecBiz.searchInfo(resRec, operUserFlowList, orgFlowList);
		if(StringUtil.isNotBlank(userFlow)){
			docotrDelayTeturn.setDoctorFlow(userFlow);
		}
		//参数flags为查询通过或不通过时用
		List<ResDocotrDelayTeturn>  resRecList = resDoctorDelayTeturnBiz.searchInfo(docotrDelayTeturn,orgFlowList,null,null);
		model.addAttribute("resRecList", resRecList);
		return "jszy/global/doctor/delayInfo";
	}
	//**************************************   变更培训申请    ******************************************
	@RequestMapping(value="/applyChangeBase",method={RequestMethod.GET})
	public String applyChangeBase(String recruitFlow,Model model){
		if(StringUtil.isNotBlank(recruitFlow)){
			ResDoctorRecruitWithBLOBs recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
			model.addAttribute("recruit", recruit);
		}
		return "jszy/doctor/applyChangeBase";
	}
	//**************************************  专业变更申请   ******************************************
	@RequestMapping(value="/applyChangeSpe",method={RequestMethod.GET})
	public String applyChangeSpe(String doctorFlow,String recruitFlow,String orgFlow,Model model){
		if(StringUtil.isNotBlank(recruitFlow)){
			List<ResOrgSpe> speList = new ArrayList<ResOrgSpe>();
			ResDoctorRecruit recruit=jsResDoctorRecruitBiz.readRecruit(recruitFlow);
			model.addAttribute("recruit", recruit);
			if(recruit!=null){
				if(StringUtil.isNotBlank(recruit.getSessionNumber())){
					if(Integer.parseInt(recruit.getSessionNumber())>2014){
						ResOrgSpe spe = new ResOrgSpe();
						spe.setOrgFlow(recruit.getOrgFlow());
						spe.setSpeTypeId(recruit.getCatSpeId());
						spe.setRecordStatus(GlobalConstant.FLAG_Y);
						speList=resBaseSpeBiz.searchResOrgSpeList(spe);
					}else{
						List<SysDict> dictList=DictTypeEnum.sysListDictMap.get(recruit.getCatSpeId());
						if(dictList!=null&&!dictList.isEmpty()){
							ResOrgSpe spe =null;
							for(SysDict dict:dictList){
								spe=new ResOrgSpe();
								spe.setSpeId(dict.getDictId());
								spe.setSpeName(dict.getDictName());
								speList.add(spe);
							}
						}
					}
					model.addAttribute("speList", speList);
				}
			}
		}
		return "jszy/doctor/applyChangeSpe";
	}
	/**
	 * 上传变更专业申请图片
	 */
	@RequestMapping(value={"/uploadChangeSpeFile"})
	@ResponseBody
	public String uploadChangeSpeFile(MultipartFile file) throws Exception{
		if(file!=null && !file.isEmpty()){
			String checkResult = jsResDoctorBiz.checkImg(file);
			String resultPath = "";
			if(!GlobalConstant.FLAG_Y.equals(checkResult)){
				return checkResult;
			}else{
				resultPath = jsResDoctorBiz.saveFileToDirs("", file, "jsresImages");
				return resultPath;
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}
	/**
	 * 提交变更基地申请
	 * @param recruitFlow
	 * @param docOrgHistory
	 * @return
	 */
	@RequestMapping(value="/submitDoctorOrgHistory",method={RequestMethod.POST})
	@ResponseBody
	public String submitDoctorOrgHistory(String recruitFlow, ResDoctorOrgHistory docOrgHistory){
		if(StringUtil.isNotBlank(recruitFlow)){
			String doctorFlow = docOrgHistory.getDoctorFlow();
			ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
			if(doctor != null){
				docOrgHistory.setDoctorName(doctor.getDoctorName());
			}
			ResDoctorRecruit docRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
			if(docRecruit != null){
				String changeStatusId = docOrgHistory.getChangeStatusId();
				docOrgHistory.setHistoryOrgFlow(docRecruit.getOrgFlow());
				docOrgHistory.setHistoryOrgName(docRecruit.getOrgName());
				if(StringUtil.isNotBlank(changeStatusId)){
					docOrgHistory.setChangeStatusName(JszyResChangeApplyStatusEnum.getNameById(changeStatusId));
				}
				docOrgHistory.setChangeTypeId(JszyResChangeTypeEnum.BaseChange.getId());
				docOrgHistory.setChangeTypeName(JszyResChangeTypeEnum.BaseChange.getName());
				docOrgHistory.setTrainingSpeId(docRecruit.getSpeId());
				docOrgHistory.setTrainingSpeName(docRecruit.getSpeName());
				docOrgHistory.setHistoryTrainingSpeId(docRecruit.getSpeId());
				docOrgHistory.setHistoryTrainingSpeName(docRecruit.getSpeName());
				docOrgHistory.setHistorySessionNumber(docRecruit.getSessionNumber());
				docOrgHistory.setTrainingTypeId(docRecruit.getCatSpeId());
				docOrgHistory.setTrainingTypeName(docRecruit.getCatSpeName());
				docOrgHistory.setHistoryTrainingTypeId(docRecruit.getCatSpeId());
				docOrgHistory.setHistoryTrainingTypeName(docRecruit.getCatSpeName());
				docOrgHistory.setRecruitFlow(recruitFlow);//将变更信息绑定到相应的规培记录中
				int result = resDoctorOrgHistoryBiz.saveDocOrgHistory(docOrgHistory);
				if(GlobalConstant.ZERO_LINE != result){
					return GlobalConstant.SAVE_SUCCESSED;
				}
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 查询专业
	 * @return
	 */
	@RequestMapping(value="/changeType", method={RequestMethod.GET})
	@ResponseBody
	public List<ResOrgSpe> changeType(ResDoctorRecruit recruit){
		List<ResOrgSpe> speList = new ArrayList<ResOrgSpe>();
		if(StringUtil.isNotBlank(recruit.getSessionNumber())){
			if(Integer.parseInt(recruit.getSessionNumber())>2014){
				ResOrgSpe spe = new ResOrgSpe();
				spe.setOrgFlow(recruit.getOrgFlow());
				spe.setSpeTypeId(recruit.getCatSpeId());
				spe.setRecordStatus(GlobalConstant.FLAG_Y);
				speList=resBaseSpeBiz.searchResOrgSpeList(spe);
			}else{
				List<SysDict> dictList=DictTypeEnum.sysListDictMap.get(recruit.getCatSpeId());
				if(dictList!=null&&!dictList.isEmpty()){
					ResOrgSpe spe =null;
					for(SysDict dict:dictList){
						spe=new ResOrgSpe();
						spe.setSpeId(dict.getDictId());
						spe.setSpeName(dict.getDictName());
						speList.add(spe);
					}
				}
			}
		}
		return speList;
	}
	/**
	 *
	 * @param resOrgSpe
	 * @return
	 */
	@RequestMapping(value="/checkResOrgSpe", method={RequestMethod.GET})
	@ResponseBody
	public String checkResOrgSpe(ResOrgSpe resOrgSpe){
		resOrgSpe.setRecordStatus(GlobalConstant.FLAG_Y);
		List<ResOrgSpe> orgSpeList = resBaseSpeBiz.searchResOrgSpeList(resOrgSpe, null);
		if(orgSpeList != null && !orgSpeList.isEmpty()){
			return GlobalConstant.FLAG_Y;
		}else{
			return GlobalConstant.FLAG_N;
		}
	}
	/**
	 * 提交变更专业申请
	 *
	 * @param recruit
	 * @param speChangeApplyFile
	 * @return
	 */
	@RequestMapping(value="/submitChangeSpe",method={RequestMethod.POST})
	@ResponseBody
	public String submitChangeSpe(ResDoctorRecruit recruit,String speChangeApplyFile){
		ResDoctorOrgHistory history =new ResDoctorOrgHistory();
		if(StringUtil.isNotBlank(recruit.getRecruitFlow())){
			ResDoctor doctor = resDoctorBiz.readDoctor(recruit.getDoctorFlow());
			if(doctor != null){
				history.setDoctorFlow(doctor.getDoctorFlow());
				history.setDoctorName(doctor.getDoctorName());
			}
			ResDoctorRecruit docRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(recruit.getRecruitFlow());
			if(docRecruit != null){
				history.setOrgFlow(docRecruit.getOrgFlow());
				history.setOrgName(docRecruit.getOrgName());
				history.setHistoryOrgFlow(docRecruit.getOrgFlow());
				history.setHistoryOrgName(docRecruit.getOrgName());
				history.setChangeStatusId(JszyResChangeApplySpeEnum.BaseWaitingAudit.getId());
				history.setChangeStatusName(JszyResChangeApplySpeEnum.BaseWaitingAudit.getName());
				history.setTrainingSpeId(recruit.getSpeId());
				history.setTrainingSpeName(recruit.getSpeName());
				history.setHistoryTrainingSpeId(docRecruit.getSpeId());
				history.setHistoryTrainingSpeName(docRecruit.getSpeName());
				history.setSpeChangeApplyFile(speChangeApplyFile);
				history.setChangeTypeId(JszyResChangeTypeEnum.SpeChange.getId());
				history.setChangeTypeName(JszyResChangeTypeEnum.SpeChange.getName());
				history.setRecruitFlow(recruit.getRecruitFlow());//将变更信息绑定到相应的规培记录中
				if(StringUtil.isNotBlank(recruit.getCatSpeId())){
					history.setTrainingTypeId(recruit.getCatSpeId());
					history.setTrainingTypeName(JszyTrainCategoryEnum.getNameById(recruit.getCatSpeId()));
					history.setHistoryTrainingTypeId(docRecruit.getCatSpeId());
					history.setHistoryTrainingTypeName(docRecruit.getCatSpeName());
				}
				if(StringUtil.isNotBlank(recruit.getSecondSpeId())){
					history.setSecondSpeId(recruit.getSecondSpeId());
					history.setSecondSpeName(recruit.getSecondSpeName());
				}
				history.setHistorySecondSpeId(docRecruit.getSecondSpeId());
				history.setHistorySecondSpeName(docRecruit.getSecondSpeName());
				//江苏中医专业变更优化：如果仅变更了二级专业则不需要经省厅审核，基地管理员审核即可，
				//修改状态为GlobalAuditPass或GlobalAuditunPass
				//当前培训专业
				String historyTrainingTypeId = history.getHistoryTrainingTypeId();
				//意向培训专业
				String trainingTypeId = history.getTrainingTypeId();
				//当前对应专业
				String historyTrainingSpeId = history.getHistoryTrainingSpeId();
				//意向对应专业
				String trainingSpeId = history.getTrainingSpeId();
				//当前二级专业
				String historySecondSpeId = history.getHistorySecondSpeId();
				//意向二级专业
				String secondSpeId = history.getSecondSpeId();
				//如果仅变更了二级专业,基地管理员审核即可
				if(historyTrainingTypeId.equals(trainingTypeId)
						&& historyTrainingSpeId.equals(trainingSpeId)
						&& !historySecondSpeId.equals(secondSpeId)){
					history.setIsOnlySecond(GlobalConstant.FLAG_Y);
				}
				int result = resDoctorOrgHistoryBiz.saveDocOrgHistory(history);
				if(GlobalConstant.ZERO_LINE != result){
					return GlobalConstant.SAVE_SUCCESSED;
				}
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}
//*************************************   培训信息    **************************************************

	/**
	 * 编辑培训信息
	 * @return
	 */
	@RequestMapping("/editDoctorRecruit")
	public String editDoctorRecruit(String recruitFlow, Model model){
		String doctorFlow = GlobalContext.getCurrentUser().getUserFlow();
		ResDoctorRecruit doctorRecruit = null;
		if(StringUtil.isNotBlank(recruitFlow)){
			doctorRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
			model.addAttribute("doctorRecruit", doctorRecruit);
            model.addAttribute("addRecord", GlobalConstant.FLAG_Y);//添加新的培训记录标识
		}
		//已审核通过
		ResDoctorRecruit passedRec = new ResDoctorRecruit();
		passedRec.setDoctorFlow(doctorFlow);
		passedRec.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		passedRec.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
		List<ResDoctorRecruit> passedRecruitList = this.jsResDoctorRecruitBiz.searchResDoctorRecruitList(passedRec, "MODIFY_TIME DESC");
		//其中一阶段、住院医师审核通过（选二阶段使用）
		List<ResDoctorRecruit> prevPassedList = new ArrayList<ResDoctorRecruit>();
		if(passedRecruitList != null && !passedRecruitList.isEmpty()){
			model.addAttribute("passedRecruitList", passedRecruitList);
			//记录审核通过的培训类别(不包含首条为二阶段自动生成的一阶段)
			List<String> catSpeIdList = new ArrayList<String>();
			for(ResDoctorRecruit rec : passedRecruitList){
				String catSpeId = rec.getCatSpeId();
				if(!catSpeIdList.contains(catSpeId) && StringUtil.isNotBlank(rec.getSpeId())){
					catSpeIdList.add(catSpeId);
				}
				if(StringUtil.isBlank(rec.getSpeId())){//首条为二阶段
					model.addAttribute("firstRecIsWMSecond", true);
				}
//				if(JszyTrainCategoryEnum.WMFirst.getId().equals(rec.getCatSpeId()) || JszyTrainCategoryEnum.DoctorTrainingSpe.getId().equals(rec.getCatSpeId())){
//					prevPassedList.add(rec);
//				}
//				if(JszyTrainCategoryEnum.WMSecond.getId().equals(rec.getCatSpeId())){//二阶段审核通过
//					model.addAttribute("isWMSecondRecPassed", true);
//				}
			}
			model.addAttribute("catSpeIdList", catSpeIdList);
		}
		if(prevPassedList != null && !prevPassedList.isEmpty()){
			model.addAttribute("prevPassedList", prevPassedList);
			if(StringUtil.isNotBlank(recruitFlow)){//回显上一阶段  结业证书
				model.addAttribute("latestPrevPassed", prevPassedList.get(0));
			}
		}
		return "jszy/doctor/editTrainInfo";
	}

	/**
	 * 查看培训信息
	 * @return
	 */
	@RequestMapping("/getDoctorRecruit")
	public String getDoctorRecruit(String recruitFlow, String doctorFlow, Model model,String zlFlag){
		boolean isLatest = false;//是否最新记录
		if(StringUtil.isNotBlank(recruitFlow)){
			ResDoctorRecruit doctorRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
			SysUser sysUser=userBiz.readSysUser(doctorFlow);
			model.addAttribute("user",sysUser);
			if(doctorRecruit!=null){
				model.addAttribute("doctorRecruit", doctorRecruit);
				if(StringUtil.isNotBlank(doctorRecruit.getDoctorFlow()) && !StringUtil.isNotBlank(doctorRecruit.getProveFileUrl())){
					ResDoctor doctor = resDoctorBiz.readDoctor(doctorRecruit.getDoctorFlow());
					if(doctor!=null){
						model.addAttribute("doctor",doctor);
						SysUser user=userBiz.readSysUser(doctorFlow);
						model.addAttribute("user",user);
						String degreeType = doctor.getDegreeCategoryId();
						if(JszyResDegreeCategoryEnum.ClinicMaster.getId().equals(degreeType) || JszyResDegreeCategoryEnum.ClinicDoctor.getId().equals(degreeType)){
							PubUserResume resume = userResumeBiz.readPubUserResume(doctorRecruit.getDoctorFlow());
							String content = resume.getUserResume();
							if(StringUtil.isNotBlank(content)){
								BaseUserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(content, BaseUserResumeExtInfoForm.class);
								model.addAttribute("userResumeExt", userResumeExt);

								doctorRecruit.setProveFileUrl(userResumeExt.getDegreeUri());
							}
						}
					}
				}
			}

			ResDoctorRecruit lastRecruit = new ResDoctorRecruit();
			lastRecruit.setDoctorFlow(doctorFlow);
			lastRecruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(lastRecruit, "CREATE_TIME DESC");
			if(recruitList != null && !recruitList.isEmpty()){
				lastRecruit = recruitList.get(0);
				if(lastRecruit.getRecruitFlow().equals(recruitFlow)){
					isLatest = true;
				}
			}
			String resRecType=ResRecTypeEnum.DoctorAuth.getId();
			List<ResRec> resRec=resRecBiz.searchByRecWithBLOBs(resRecType,doctorFlow);
			if (resRec!=null&!resRec.isEmpty()) {
				model.addAttribute("resRec", resRec.get(0));
			}
			DoctorAuth doctorAuth = doctorAuthBiz.searchAuthByOperUserFlow(doctorFlow);
			if(doctorAuth != null){
				model.addAttribute("imageUrl",doctorAuth.getImageUrl());
			}
			//最新记录 && 审核通过
			if(JszyResDoctorAuditStatusEnum.Passed.getId().equals(doctorRecruit.getAuditStatusId()) ){
				ResDoctorOrgHistory docOrgHistory = new ResDoctorOrgHistory();
				docOrgHistory.setDoctorFlow(doctorFlow);
				docOrgHistory.setChangeTypeId(JszyResChangeTypeEnum.BaseChange.getId());
				docOrgHistory.setRecruitFlow(recruitFlow);
				//List<String> changeStatusIdList = new ArrayList<String>();
				//changeStatusIdList.add(JszyResChangeApplyStatusEnum.OutApplyWaiting.getId());//待转出审核
				//changeStatusIdList.add(JszyResChangeApplyStatusEnum.InApplyWaiting.getId());//待转入审核
				List<ResDoctorOrgHistory> docOrgHistoryList = resDoctorOrgHistoryBiz.searchWaitingChangeOrgHistoryList(docOrgHistory, null);
				if(docOrgHistoryList != null && !docOrgHistoryList.isEmpty()){
					model.addAttribute("docOrgHistoryList", docOrgHistoryList);
					model.addAttribute("latestDocOrgHistory", docOrgHistoryList.get(0));//最新变更记录
				}
				docOrgHistory.setChangeTypeId(JszyResChangeTypeEnum.SpeChange.getId());
				List<ResDoctorOrgHistory> changeSpeList = resDoctorOrgHistoryBiz.searchWaitingChangeOrgHistoryList(docOrgHistory, null);
				if(changeSpeList != null && !changeSpeList.isEmpty()){
					model.addAttribute("changeSpeList", changeSpeList);
					model.addAttribute("lastChangeSpe",changeSpeList.get(0) );
				}
			}
		}
		model.addAttribute("isLatest", isLatest);
		model.addAttribute("zlFlag", zlFlag);
		return "jszy/doctor/trainInfo";
	}

	/**
	 * 查看培训信息
	 * @return
	 */
	@RequestMapping("/getDoctorRecruitInfo")
	public String getDoctorRecruitInfo(String recruitFlow, String doctorFlow, Model model,String zlFlag){
        boolean isLatest = false;//是否最新记录
        if(StringUtil.isNotBlank(recruitFlow)){
            JsresRecruitDocInfoWithBLOBs doctorRecruit = jsResDoctorRecruitBiz.readResDoctorRecruitInfo(recruitFlow);
            SysUser sysUser=userBiz.readSysUser(doctorFlow);
            model.addAttribute("user",sysUser);
            if(doctorRecruit!=null){
                model.addAttribute("doctorRecruit", doctorRecruit);
                if(StringUtil.isNotBlank(doctorRecruit.getDoctorFlow()) && !StringUtil.isNotBlank(doctorRecruit.getProveFileUrl())){
                    ResDoctor doctor = resDoctorBiz.readDoctor(doctorRecruit.getDoctorFlow());
                    if(doctor!=null){
                        model.addAttribute("doctor",doctor);
                        SysUser user=userBiz.readSysUser(doctorFlow);
                        model.addAttribute("user",user);
                        String degreeType = doctor.getDegreeCategoryId();
                        if(JszyResDegreeCategoryEnum.ClinicMaster.getId().equals(degreeType) || JszyResDegreeCategoryEnum.ClinicDoctor.getId().equals(degreeType)){
                            PubUserResume resume = userResumeBiz.readPubUserResume(doctorRecruit.getDoctorFlow());
                            String content = resume.getUserResume();
                            if(StringUtil.isNotBlank(content)){
                                BaseUserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(content, BaseUserResumeExtInfoForm.class);
                                model.addAttribute("userResumeExt", userResumeExt);
                                doctorRecruit.setProveFileUrl(userResumeExt.getDegreeUri());
                            }
                        }
                    }
                }
            }

            ResDoctorRecruit lastRecruit = new ResDoctorRecruit();
            lastRecruit.setDoctorFlow(doctorFlow);
            lastRecruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(lastRecruit, "CREATE_TIME DESC");
            if(recruitList != null && !recruitList.isEmpty()){
                lastRecruit = recruitList.get(0);
                if(lastRecruit.getRecruitFlow().equals(recruitFlow)){
                    isLatest = true;
                }
            }
            String resRecType=ResRecTypeEnum.DoctorAuth.getId();
            List<ResRec> resRec=resRecBiz.searchByRecWithBLOBs(resRecType,doctorFlow);
            if (resRec!=null&!resRec.isEmpty()) {
                model.addAttribute("resRec", resRec.get(0));
            }
            DoctorAuth doctorAuth = doctorAuthBiz.searchAuthByOperUserFlow(doctorFlow);
            if(doctorAuth != null){
                model.addAttribute("imageUrl",doctorAuth.getImageUrl());
            }
            //最新记录 && 审核通过
            if(JszyResDoctorAuditStatusEnum.Passed.getId().equals(doctorRecruit.getAuditStatusId()) ){
                ResDoctorOrgHistory docOrgHistory = new ResDoctorOrgHistory();
                docOrgHistory.setDoctorFlow(doctorFlow);
                docOrgHistory.setChangeTypeId(JszyResChangeTypeEnum.BaseChange.getId());
                docOrgHistory.setRecruitFlow(recruitFlow);
                //List<String> changeStatusIdList = new ArrayList<String>();
                //changeStatusIdList.add(JszyResChangeApplyStatusEnum.OutApplyWaiting.getId());//待转出审核
                //changeStatusIdList.add(JszyResChangeApplyStatusEnum.InApplyWaiting.getId());//待转入审核
                List<ResDoctorOrgHistory> docOrgHistoryList = resDoctorOrgHistoryBiz.searchWaitingChangeOrgHistoryList(docOrgHistory, null);
                if(docOrgHistoryList != null && !docOrgHistoryList.isEmpty()){
                    model.addAttribute("docOrgHistoryList", docOrgHistoryList);
                    model.addAttribute("latestDocOrgHistory", docOrgHistoryList.get(0));//最新变更记录
                }
                docOrgHistory.setChangeTypeId(JszyResChangeTypeEnum.SpeChange.getId());
                List<ResDoctorOrgHistory> changeSpeList = resDoctorOrgHistoryBiz.searchWaitingChangeOrgHistoryList(docOrgHistory, null);
                if(changeSpeList != null && !changeSpeList.isEmpty()){
                    model.addAttribute("changeSpeList", changeSpeList);
                    model.addAttribute("lastChangeSpe",changeSpeList.get(0) );
                }
            }
        }
        model.addAttribute("isLatest", isLatest);
        model.addAttribute("zlFlag", zlFlag);
        return "jszy/doctor/trainInfo";
	}


	/**
	 * 查看培训信息
	 * @return
	 */
	@RequestMapping("/getDocRecruitForReduction")
	public String getDocRecruitForReduction(String recruitFlow, Model model){
		if(StringUtil.isNotBlank(recruitFlow)){
			ResDoctorRecruit doctorRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);

			if(doctorRecruit!=null){
				model.addAttribute("doctorRecruit", doctorRecruit);

				if(StringUtil.isNotBlank(doctorRecruit.getDoctorFlow()) && !StringUtil.isNotBlank(doctorRecruit.getProveFileUrl())){
					ResDoctor doctor = resDoctorBiz.readDoctor(doctorRecruit.getDoctorFlow());
					if(doctor!=null){
						model.addAttribute("doctor",doctor);

						String degreeType = doctor.getDegreeCategoryId();
						if(JszyResDegreeCategoryEnum.ClinicMaster.getId().equals(degreeType) || JszyResDegreeCategoryEnum.ClinicDoctor.getId().equals(degreeType)){
							PubUserResume resume = userResumeBiz.readPubUserResume(doctorRecruit.getDoctorFlow());
							String content = resume.getUserResume();
							if(StringUtil.isNotBlank(content)){
								BaseUserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(content, BaseUserResumeExtInfoForm.class);
								model.addAttribute("userResumeExt", userResumeExt);

								doctorRecruit.setProveFileUrl(userResumeExt.getDegreeUri());
							}
						}
					}
				}
			}
		}
		return "jszy/doctor/trainInfo";
	}
	/**
	 * 验证
	 * @param type
	 * @return
	 */
	@RequestMapping("/validateChange")
	@ResponseBody
	public String validateChange(String type,String doctorFlow,String orgFlow){
		if(StringUtil.isNotBlank(type)){
			ResDoctorOrgHistory orgHistory = new ResDoctorOrgHistory();
			orgHistory.setDoctorFlow(doctorFlow);
			orgHistory.setHistoryOrgFlow(orgFlow);
			List<String> changeStatusIdList = new ArrayList<String>();
			if(JszyResChangeTypeEnum.SpeChange.getId().equals(type)){
				orgHistory.setChangeTypeId(JszyResChangeTypeEnum.BaseChange.getId());
				changeStatusIdList.add(JszyResChangeApplyStatusEnum.OutApplyWaiting.getId());//待转出审核
				changeStatusIdList.add(JszyResChangeApplyStatusEnum.InApplyWaiting.getId());//待转入审核
			}
			if(JszyResChangeTypeEnum.BaseChange.getId().equals(type)){
				orgHistory.setChangeTypeId(JszyResChangeTypeEnum.SpeChange.getId());
				changeStatusIdList.add(JszyResChangeApplySpeEnum.BaseWaitingAudit.getId());
				changeStatusIdList.add(JszyResChangeApplySpeEnum.GlobalWaitingAudit.getId());
			}
			if(StringUtil.isNotBlank(doctorFlow)){
				orgHistory.setDoctorFlow(doctorFlow);
			}
			List<ResDoctorOrgHistory> docOrgHistoryList = resDoctorOrgHistoryBiz.searchWaitingChangeOrgHistoryList(orgHistory, changeStatusIdList);
			if(docOrgHistoryList!=null && !docOrgHistoryList.isEmpty()){
				return GlobalConstant.FLAG_N;
			}else{
				return GlobalConstant.FLAG_Y;
			}
		}else{
			return GlobalConstant.FLAG_N;
		}
	}


	/**
	 * 加载培训基地
	 * @param sysOrg
	 * @return
	 */
	@RequestMapping(value="/searchOrgList", method={RequestMethod.GET})
	@ResponseBody
	public List<SysOrg> searchOrgList(SysOrg sysOrg, String jointFlag){
		List<SysOrg> orgList = null;
		sysOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		if(StringUtil.isNotBlank(jointFlag)){
			List<String> orgLevelList = new ArrayList<String>();
			orgLevelList.add(OrgLevelEnum.CountryOrg.getId());
			orgLevelList.add(OrgLevelEnum.ProvinceOrg.getId());
			orgList = orgBiz.searchJsResOrgWithJointOrg(sysOrg, orgLevelList);
		}else{
			sysOrg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			orgList = orgBiz.searchOrg(sysOrg);
		}
		return orgList;
	}


	/**
	 * 保存培训信息
	 * @param docRecWithBLOBs
	 * @param prevRecruitFlow
	 * @param prevCompleteFileUrl
	 * @param prevCompleteCertNo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveResDoctorRecruit", method={RequestMethod.POST})
	@ResponseBody
	public String saveResDoctorRecruit(ResDoctorRecruitWithBLOBs docRecWithBLOBs, String prevRecruitFlow, String prevCompleteFileUrl, String prevCompleteCertNo, Model model){
		ResDoctorRecruitWithBLOBs prevDocRec = new ResDoctorRecruitWithBLOBs();//选择的结业阶段
		prevDocRec.setRecruitFlow(prevRecruitFlow);
		prevDocRec.setCompleteFileUrl(prevCompleteFileUrl);
		prevDocRec.setCompleteCertNo(prevCompleteCertNo);
		int result = jsResDoctorRecruitBiz.editResDoctorRecruit(docRecWithBLOBs, prevDocRec);
		if(GlobalConstant.ZERO_LINE != result){
			return docRecWithBLOBs.getRecruitFlow();
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 提交培训信息
	 * @param recruitWithBLOBs
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/submitResDoctorRecruit",method={RequestMethod.POST})
	@ResponseBody
	public String submitResDoctorRecruit(ResDoctorRecruitWithBLOBs recruitWithBLOBs, Model model){
		String auditStatusId = recruitWithBLOBs.getAuditStatusId();
		if(StringUtil.isNotBlank(auditStatusId)){
			recruitWithBLOBs.setAuditStatusName(JszyResDoctorAuditStatusEnum.getNameById(auditStatusId));
		}
		int result = jsResDoctorRecruitBiz.saveDoctorRecruit(recruitWithBLOBs);
		if(GlobalConstant.ZERO_LINE != result){
			return recruitWithBLOBs.getRecruitFlow();
		}
		return GlobalConstant.OPRE_FAIL;
	}

	/**
	 * 验证黑名单中信息
	 * @param userCode
	 * @param userEmail
	 * @param userFlow
	 * @param userPhone
	 * @param idNo
	 * @return
	 */
	@RequestMapping(value="/checkUserInfoInBlack",method={RequestMethod.POST})
	@ResponseBody
	public String checkUserInfoInBlack(String userCode,String userEmail,String userFlow,String userPhone,String idNo)
	{
		Map<String,Object> userInfoMap=new HashMap<String,Object>();
		userInfoMap.put("userCode",userCode);
		userInfoMap.put("userEmail",userEmail);
		userInfoMap.put("userFlow",userFlow);
		userInfoMap.put("userPhone",userPhone);
		userInfoMap.put("idNo",idNo);
		List<JsresUserBalcklist> userBalckList=jsResDoctorBiz.checkBlackList(userInfoMap);
		if (userBalckList.size()>0)
		{
//			setSessionAttribute("reason",userBalckList.get(0).getReason());
//			setSessionAttribute("reasonYj",userBalckList.get(0).getReasonYj());
			String returnMsg=userBalckList.get(0).getReason()+"&pdnpc;"+userBalckList.get(0).getReasonYj()+"&pdnpc;"+GlobalConstant.USER_INFO_IN_BLACK;
			return returnMsg;
		}
		return "";
	}

	/**
	 * 查找审核理论考试信息
	 * @param model
	 * @param currentPage
	 * @param roleFlag
	 * @param request
	 * @param doctor
	 * @param user
	 * @param baseId
	 * @param jointOrgFlag
	 * @param derateFlag
	 * @param orgLevel
	 * @param datas
	 * @param graduationYear
	 * @return
	 */
	@RequestMapping(value="/searchExamInfo",method={RequestMethod.POST})
	public String searchExamInfo(Model model,Integer currentPage,String roleFlag, HttpServletRequest request,ResDoctor doctor,SysUser user,String baseId,String jointOrgFlag,String derateFlag,String orgLevel,String[] datas,String graduationYear){
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
		List<JszyDoctorInfoExt> doctorList=null;
		PageHelper.startPage(currentPage, getPageSize(request));
		Map<String, Object> doctorResumeMap=new HashMap<String, Object>();
		doctorResumeMap.put("doctor", doctor);
		doctorResumeMap.put("user", user);
		doctorResumeMap.put("derateFlag", derateFlag);
		doctorResumeMap.put("jointOrgFlowList", jointOrgFlowList);
		doctorResumeMap.put("sysOrg", org);
		doctorResumeMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorResumeMap.put("docTypeList", docTypeList);
		List<JszyDoctorInfoExt> doctorInfoExts=jsResDoctorRecruitBiz.searchDoctorInfoResume(doctorResumeMap);
//		doctorList=jsResDoctorRecruitBiz.searchDoctorInfoResume(resDoctorRecruit, doctor, user, org, jointOrgFlowList, derateFlag, docTypeList);
		//解析xml
		Map<String, Object> userResumeInfoMap = new HashMap<String, Object>();
		if(doctorList!=null && !doctorList.isEmpty()){
			for(JszyDoctorInfoExt d:doctorList){
				String content=d.getUserResume().getUserResume();
				if(StringUtil.isNotBlank(content)){
					BaseUserResumeExtInfoForm form=JaxbUtil.converyToJavaBean(content, BaseUserResumeExtInfoForm.class);
					String key = d.getRecruitFlow();
					userResumeInfoMap.put(key, form);
				}
			}
		}
		model.addAttribute("userResumeInfoMap", userResumeInfoMap);
		model.addAttribute("roleFlag",roleFlag);
		model.addAttribute("doctorList",doctorList);
		model.addAttribute("datas",datas);

		return "jszy/examInfo";
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

	@RequestMapping(value="/backResDoctorRecruit",method={RequestMethod.POST})
	@ResponseBody
	public String backResDoctorRecruit(ResDoctorRecruitWithBLOBs recruitWithBLOBs, Model model){
		String auditStatusId = recruitWithBLOBs.getAuditStatusId();
		if(StringUtil.isNotBlank(auditStatusId)){
			recruitWithBLOBs.setAuditStatusName(JszyResDoctorAuditStatusEnum.getNameById(auditStatusId));
		}
		int result = jsResDoctorRecruitBiz.saveDoctorRecruit(recruitWithBLOBs);
		if(GlobalConstant.ZERO_LINE != result){
			String doctorFlow = recruitWithBLOBs.getDoctorFlow();
			if(StringUtil.isNotBlank(doctorFlow)){
				ResDoctor resDoctor=this.resDoctorBiz.readDoctor(doctorFlow);
				if(resDoctor!=null){
					ResDoctorRecruit recruit=new ResDoctorRecruit();
					recruit.setDoctorFlow(doctorFlow);
					recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
					List<ResDoctorRecruit> recruitList=this.jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "create_time desc");
					if(recruitList!=null && !recruitList.isEmpty()){
						resDoctor.setOrgFlow(recruitList.get(0).getOrgFlow());
						resDoctor.setOrgName(recruitList.get(0).getOrgName());
						resDoctor.setTrainingTypeId(recruitList.get(0).getCatSpeId());
						resDoctor.setTrainingTypeName(recruitList.get(0).getCatSpeName());
						resDoctor.setTrainingSpeId(recruitList.get(0).getSpeId());
						resDoctor.setTrainingSpeName(recruitList.get(0).getSpeName());
						resDoctor.setDoctorStatusId(recruitList.get(0).getDoctorStatusId());
						resDoctor.setDoctorStatusName(recruitList.get(0).getDoctorStatusName());
						resDoctor.setTrainingYears(recruitList.get(0).getTrainYear());
						resDoctor.setDegreeCategoryId(recruitList.get(0).getCurrDegreeCategoryId());
						resDoctor.setDegreeCategoryName(recruitList.get(0).getCurrDegreeCategoryName());
						resDoctor.setSessionNumber(recruitList.get(0).getSessionNumber());
						if(StringUtil.isBlank(recruitList.get(0).getOrgFlow())){
							resDoctor.setOrgFlow("");
							resDoctor.setOrgName("");
							resDoctor.setTrainingTypeId("");
							resDoctor.setTrainingTypeName("");
							resDoctor.setTrainingSpeId("");
							resDoctor.setTrainingSpeName("");
							resDoctor.setDoctorStatusId("");
							resDoctor.setDoctorStatusName("");
							resDoctor.setTrainingYears("");
							resDoctor.setDegreeCategoryId("");
							resDoctor.setDegreeCategoryName("");
							resDoctor.setSessionNumber("");
							resDoctor.setRotationFlow("");
							resDoctor.setRotationName("");
						}
					}else{
						resDoctor.setOrgFlow("");
						resDoctor.setOrgName("");
						resDoctor.setTrainingTypeId("");
						resDoctor.setTrainingTypeName("");
						resDoctor.setTrainingSpeId("");
						resDoctor.setTrainingSpeName("");
						resDoctor.setDoctorStatusId("");
						resDoctor.setDoctorStatusName("");
						resDoctor.setTrainingYears("");
						resDoctor.setDegreeCategoryId("");
						resDoctor.setDegreeCategoryName("");
						resDoctor.setSessionNumber("");
						resDoctor.setRotationFlow("");
						resDoctor.setRotationName("");
					}
					int docResult=resDoctorBiz.editDoctor(resDoctor, null);
					if(docResult==GlobalConstant.ONE_LINE){
						resDoctor=rotationBiz.updateDocRotation(resDoctor);
						resDoctorBiz.editDoctor(resDoctor, null);
					}
				}
			}
            ResDoctorRecruit resDoctorRecruit=jsResDoctorRecruitBiz.readResDoctorRecruit(recruitWithBLOBs.getRecruitFlow());
            if(resDoctorRecruit!=null) {
                String sessionNumber = resDoctorRecruit.getSessionNumber();
                doctorFlow = resDoctorRecruit.getDoctorFlow();
                JsresRecruitDocInfoWithBLOBs info2 = resDoctorBiz.getRecruitDocInfoBySessionNumber(doctorFlow, sessionNumber);
                JsresRecruitInfo recruitInfo2 = resDoctorBiz.getRecruitInfoBysessionNumber(doctorFlow, sessionNumber);
                //删除备份记录
                if (info2 != null) {
                    resDoctorBiz.delJsresRecruitDocInfo(info2.getRecruitFlow());
                }
                if (recruitInfo2 != null) {
                    resDoctorBiz.delJsresRecruitInfo(recruitInfo2.getRecordFlow());
                }
            }
			return recruitWithBLOBs.getRecruitFlow();
		}
		return GlobalConstant.OPRE_FAIL;
	}

	/**
	 * 上传减免培训年限证明
	 * @param file
	 * @param courseFlow
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/uploadTrainYearFile"})
	@ResponseBody
	public String uploadTrainYearFile(MultipartFile file, String courseFlow) throws Exception{
		if(file!=null && !file.isEmpty()){
			String checkResult = jsResDoctorBiz.checkImg(file);
			String resultPath = "";
			if(!GlobalConstant.FLAG_Y.equals(checkResult)){
				return checkResult;
			}else{
				resultPath = jsResDoctorBiz.saveFileToDirs("", file, "jsresImages");
				return resultPath;
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}
	@RequestMapping(value="/saveForLack")
	public String saveForLack(String userFlow, String viewFlag, Model model){
		SysUser sysUser=GlobalContext.getCurrentUser();
		ResDoctor doctor=resDoctorBiz.readDoctor(sysUser.getUserFlow());
		PubUserResume resume=userResumeBiz.readPubUserResume(sysUser.getUserFlow());
		BaseUserResumeExtInfoForm  userResumeExt=null;
		if(resume != null){
			String xmlContent =  resume.getUserResume();
			if(StringUtil.isNotBlank(xmlContent)){
				//xml转换成JavaBean
				userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, BaseUserResumeExtInfoForm.class);
				model.addAttribute("userResumeExt", userResumeExt);
			}
		}
		List<SysDict> sysDictList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
		int benkeResult=0;int result=0;
		if(sysDictList!=null&&!sysDictList.isEmpty()){
			for(SysDict dict :sysDictList){
				if(userResumeExt!=null){
					if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
						if(dict.getDictId().equals(userResumeExt.getGraduatedId())){
							benkeResult=1;
						}
					}
				}
				if(doctor!=null){
					if(StringUtil.isNotBlank(doctor.getGraduatedId())){
						if(dict.getDictId().equals(doctor.getGraduatedId())){
							result=1;
						}
					}
				}
			}
		}
		String school=GlobalConstant.FLAG_N;
		List<SysDict> schoolList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.SendSchool.getId());
		if(doctor!=null){
			if(StringUtil.isNotBlank(doctor.getDoctorTypeId())&&JszyResDocTypeEnum.Graduate.getId().equals(doctor.getDoctorTypeId())){
				if(schoolList!=null&&!schoolList.isEmpty()){
					for(SysDict dic:schoolList){
						if(dic.getDictId().equals(doctor.getWorkOrgId())){
							school=GlobalConstant.FLAG_Y;
						}
					}
				}
			}
		}
		model.addAttribute("school", school);
		model.addAttribute("benkeResult", benkeResult);
		model.addAttribute("result", result);
		model.addAttribute("doctor", doctor);
//		return "jszy/doctor/addInfo";
		return "jszy/doctor/addInfo2";
	}

	/**
	 * 加载基地专业
	 * @return
	 */
	@RequestMapping(value="/searchResOrgSpeList", method={RequestMethod.GET})
	@ResponseBody
	public List<ResOrgSpe> searchResOrgSpeList(String sessionNumber,ResOrgSpe resOrgSpe, Model model){
		List<ResOrgSpe> speList=null;
		resOrgSpe.setRecordStatus(GlobalConstant.FLAG_Y);
		speList = resBaseSpeBiz.searchResOrgSpeList(resOrgSpe,null);
		return speList;
	}
	/**
	 * 添加信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/addInfo"})
	@ResponseBody
	public String addInfo(Model model,JszyResDoctorInfoForm doctorInfoForm){
		ResDoctor doctor=doctorInfoForm.getDoctor();
		BaseUserResumeExtInfoForm userResumeExt=doctorInfoForm.getUserResumeExt();
		int result=0;int resumeResult=0;
		if(StringUtil.isNotBlank(doctor.getDoctorFlow())){
			if(StringUtil.isNotBlank(doctor.getWorkOrgLevelId())){
				doctor.setWorkOrgLevelName(DictTypeEnum.getDictName(DictTypeEnum.BaseLevel, doctor.getWorkOrgLevelId()));
			}
			//毕业院校
			if(StringUtil.isNotBlank(userResumeExt.getGraduatedName())){
				Map<String,List<SysDict>>  sysListDictMap = DictTypeEnum.sysListDictMap;
				List<SysDict> dictList=sysListDictMap.get("GraduateSchool");
				for(SysDict s:dictList){
					if (s.getDictName().equals(userResumeExt.getGraduatedName())) {
						doctor.setGraduatedId(s.getDictId());
						doctor.setGraduatedName(userResumeExt.getGraduatedName());
					}
				}
			}
			//派送单位或者学校
			if(StringUtil.isNotBlank(doctor.getWorkOrgName())){
				if(JszyResDocTypeEnum.Graduate.getId().equals(doctor.getDoctorTypeId())){
					List<SysDict> sysDictList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.SendSchool.getId());
					if(sysDictList!=null && !sysDictList.isEmpty()){
						for(SysDict dict:sysDictList){
							if(dict.getDictName().equals(doctor.getWorkOrgName())){
								doctor.setWorkOrgId(dict.getDictId());
							}
						}
					}
				}
			}
			result=resDoctorBiz.editDoctor(doctor);
			PubUserResume pubUserResume = userResumeBiz.readPubUserResume(doctor.getDoctorFlow());
			if(pubUserResume != null){
				//JavaBean转换成xml
				BaseUserResumeExtInfoForm infoForm = JaxbUtil.converyToJavaBean(pubUserResume.getUserResume(),BaseUserResumeExtInfoForm.class);
				if(userResumeExt!=null){
					if(StringUtil.isNotBlank(userResumeExt.getGraduatedName())){
						if(infoForm==null){
							infoForm=new BaseUserResumeExtInfoForm();
						}
						infoForm.setGraduatedName(userResumeExt.getGraduatedName());
						Map<String,List<SysDict>>  sysListDictMap = DictTypeEnum.sysListDictMap;
						List<SysDict> dictList=sysListDictMap.get("GraduateSchool");
						for(SysDict s:dictList){
							if (s.getDictName().equals(userResumeExt.getGraduatedName())) {
								infoForm.setGraduatedId(s.getDictId());
							}
						}
					}
					String xmlContent=JaxbUtil.convertToXml(infoForm);
					pubUserResume.setUserResume(xmlContent);
				}
				resumeResult=userResumeBiz.savePubUserResume(null, pubUserResume);
			}
		}
		if(result!=0||resumeResult!=0){
			return GlobalConstant.SAVE_SUCCESSED;
		}else{
			return GlobalConstant.SAVE_FAIL;
		}
	}
	@RequestMapping(value={"/addInfo2"})
	@ResponseBody
	public String addInfo2(Model model,JszyResDoctorInfoForm doctorInfoForm){
		ResDoctor doctor=doctorInfoForm.getDoctor();
		int result=0;
		if(StringUtil.isNotBlank(doctor.getDoctorFlow())){
			String msg="";
			if(StringUtil.isNotBlank(doctor.getDoctorTypeId())){
				doctor.setDoctorTypeName(JszyResDocTypeEnum.getNameById(doctor.getDoctorTypeId()));
			}else{
				msg= "请选择人员类型！";
			}
			//派送单位或者学校
			if(StringUtil.isNotBlank(doctor.getWorkOrgName())){
				List<SysDict> sysDictList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.WorkOrg.getId());
				if(sysDictList!=null && !sysDictList.isEmpty()){
					boolean f=false;
					for(SysDict dict:sysDictList){
						if(dict.getDictName().equals(doctor.getWorkOrgName())){
							f=true;
							doctor.setWorkOrgId(dict.getDictId());
						}
					}
					if(!f)
						msg= "派送单位的值与字典不符,请重新输入";
				}else{
					msg= "请联系系统管理员，维护派送单位信息！";
				}
			}else{
				msg= "请填写派送单位！";
			}
			if(StringUtil.isBlank(msg))
				result=resDoctorBiz.editDoctor(doctor);
			else return msg;
		}
		if(result!=0){
			return GlobalConstant.SAVE_SUCCESSED;
		}else{
			return GlobalConstant.SAVE_FAIL;
		}
	}
	/**
	 * 个人基本信息
	 * @param model
	 * @return
	 * @throws DocumentException
	 */
	@RequestMapping(value="/doctorInfo")
	public String doctorInfo(String userFlow, String viewFlag, Model model) throws DocumentException{
		SysUser sysUser = userBiz.readSysUser(userFlow);
		ResDoctor resDoctor = resDoctorBiz.readDoctor(userFlow);
		if(resDoctor != null){
			model.addAttribute("doctorFlow",resDoctor.getDoctorFlow());
		}
		if(resDoctor!=null){
			if(StringUtil.isNotBlank(resDoctor.getGraduatedId())){
				List<SysDict> sysDictList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.GraduateSchool.getId());
				if(sysDictList!=null && !sysDictList.isEmpty()){
					for(SysDict dict:sysDictList){
						if(dict.getDictId().equals(resDoctor.getGraduatedId())){
							resDoctor.setGraduatedName(dict.getDictName());
						}
					}
				}
			}
			if(StringUtil.isNotBlank(resDoctor.getDoctorTypeId())&& JszyResDocTypeEnum.Graduate.getId().equals(resDoctor.getDoctorTypeId())){
				if(StringUtil.isNotBlank(resDoctor.getWorkOrgId())){
					List<SysDict> sysDictList=dictBiz.searchDictListByDictTypeId(DictTypeEnum.SendSchool.getId());
					if(sysDictList!=null && !sysDictList.isEmpty()){
						for(SysDict dict:sysDictList){
							if(dict.getDictId().equals(resDoctor.getWorkOrgId())){
								resDoctor.setWorkOrgName(dict.getDictName());
							}
						}
					}
				}
			}
		}
		String resRecType=ResRecTypeEnum.DoctorAuth.getId();
		List<ResRec> resRec=resRecBiz.searchByRecWithBLOBs(resRecType,userFlow);
		if (resRec!=null&!resRec.isEmpty()) {
			model.addAttribute("resRec", resRec.get(0));
		}
		model.addAttribute("user", sysUser);
		model.addAttribute("doctor", resDoctor);
		PubUserResume pubUserResume = userResumeBiz.readPubUserResume(userFlow);
		if(pubUserResume != null){
			String xmlContent =  pubUserResume.getUserResume();
			if(StringUtil.isNotBlank(xmlContent)){
				//xml转换成JavaBean
				BaseUserResumeExtInfoForm  userResumeExt=null;
				userResumeExt=userResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class);
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
					model.addAttribute("userResumeExt", userResumeExt);
				}
			}
		}

		if(GlobalConstant.FLAG_Y.equals(viewFlag)){
			return "jszy/doctorInfo";
		}
		return "jszy/doctor/editDoctorInfo";
	}


	/**
	 * 保存上传扫描件
	 * @param operType
	 * @param uploadFile
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/checkUploadFile",method={RequestMethod.POST})
	public String checkUploadFile(String operType, MultipartFile uploadFile, Model model){
		if(uploadFile!=null && !uploadFile.isEmpty()){
			String fileResult = jsResDoctorBiz.checkImg(uploadFile);
			String resultPath = "";
			if(!GlobalConstant.FLAG_Y.equals(fileResult)){
				model.addAttribute("fileErrorMsg", fileResult);
			}else{
				resultPath = jsResDoctorBiz.saveFileToDirs("", uploadFile, "jsresImages");
				model.addAttribute("filePath" , resultPath);
			}
			model.addAttribute("result", fileResult);
		}
		return "jszy/doctor/uploadFile";
	}
	/**
	 * 保存个人基本信息
	 * @param doctorInfoForm
	 * @return
	 */
	@RequestMapping(value="/saveDoctorInfo")
	@ResponseBody
	public String saveDoctorInfo(JszyResDoctorInfoForm doctorInfoForm){
		SysUser sysUser = doctorInfoForm.getUser();
		String checkResult = checkUserUnique(sysUser, sysUser.getUserFlow());
		if(!GlobalConstant.FLAG_Y.equals(checkResult)){
			return checkResult;
		}
		ResDoctor doctor = doctorInfoForm.getDoctor();
        if(doctor.getSendProvName().equals("请选择")){
            doctor.setSendProvName("");
        }
        if(doctor.getSendCityName().equals("请选择")){
            doctor.setSendCityName("");
        }
		BaseUserResumeExtInfoForm userResumeExt = doctorInfoForm.getUserResumeExt();
		int result = jsResDoctorBiz.saveDoctorInfo(sysUser, doctor, userResumeExt);
		if(GlobalConstant.ZERO_LINE != result){
			return GlobalContext.getCurrentUser().getUserName();
		}
		return GlobalConstant.SAVE_FAIL;
	}
	@RequestMapping(value="/searchJointOrgFlag")
	@ResponseBody
	public String searchJointOrgFlag(String orgFlow){
		int result=0;
		if(StringUtil.isNotBlank(orgFlow)){
			List<ResJointOrg>jointOrgs=jointOrgBiz.searchResJointByOrgFlow(orgFlow);
			if(jointOrgs!=null&&!jointOrgs.isEmpty()){
				result=1;
			}
		}
		if(GlobalConstant.ZERO_LINE==result){
			return GlobalConstant.FLAG_N;
		}else{
			return GlobalConstant.FLAG_Y;
		}
	}

	/**
	 * 跳转至上传扫描件
	 * @return
	 */
	@RequestMapping("/uploadFile")
	public String uploadFile(){
		return "jszy/doctor/uploadFile";
	}
	/**
	 * 检查身份证号、手机号唯一
	 * @param sysUser
	 * @param userFlow 检查流水号
	 * @return
	 */
	@RequestMapping(value="/checkUserUnique")
	@ResponseBody
	public String checkUserUnique(SysUser sysUser, String userFlow){
		SysUser exitUser = null;
		//身份证号唯一
		String idNo = sysUser.getIdNo().toUpperCase();////身份证X大写
		if(StringUtil.isNotBlank(idNo)){
			exitUser = userBiz.findByNotBlackIdNo(idNo.trim());
			if(exitUser != null){
				if(StringUtil.isNotBlank(userFlow)){
					if(!exitUser.getUserFlow().equals(userFlow)){
						return GlobalConstant.USER_ID_NO_REPETE;
					}
				}else{
					return GlobalConstant.USER_ID_NO_REPETE;
				}
			}
		}
		//手机号唯一
		String userPhone = sysUser.getUserPhone();
		if(StringUtil.isNotBlank(userPhone)){
			exitUser = userBiz.findByUserPhone(userPhone.trim());
			if(exitUser != null){
				if(StringUtil.isNotBlank(userFlow)){
					if(!exitUser.getUserFlow().equals(userFlow)){
						return GlobalConstant.USER_PHONE_REPETE;
					}
				}else{
					return GlobalConstant.USER_PHONE_REPETE;
				}
			}
		}
		return GlobalConstant.FLAG_Y;
	}

	/**
	 * 检查黑名单中身份证号、手机号
	 * @param sysUser
	 * @param userFlow
	 * @return
	 */
	@RequestMapping(value="/checkBlackUser")
	@ResponseBody
	public String checkBlackUser(SysUser sysUser, String userFlow){
		JsresUserBalcklist userBalcklist =null;
		String idNo=sysUser.getIdNo().toUpperCase();
		if(StringUtil.isNotBlank(idNo)) {
			userBalcklist = jsResDoctorBiz.findByIdNo4Black(idNo);
			if (userBalcklist != null){
				if(StringUtil.isNotBlank(userFlow)){
					if (!userBalcklist.getUserFlow().equals(userFlow))
					{
						return GlobalConstant.ID_NO_IN_BLACK;
					}
				}else{
					return GlobalConstant.ID_NO_IN_BLACK;
				}
			}
		}
		String userPhone=sysUser.getUserPhone();
		if(StringUtil.isNotBlank(userPhone)){
			userBalcklist = jsResDoctorBiz.findByUserPhone4Black(userPhone);
			if (userBalcklist != null)
			{
				if(StringUtil.isNotBlank(userFlow))
				{
					if (!userBalcklist.getUserFlow().equals(userFlow))
					{
						return GlobalConstant.USER_PHONE_IN_BLACK;
					}
				}else{
					return GlobalConstant.USER_PHONE_IN_BLACK;
				}
			}
		}
		return GlobalConstant.FLAG_Y;
	}

	@RequestMapping(value={"/uploadDelayFile"})
	@ResponseBody
	public String uploadDelayFile(MultipartFile file) throws Exception{
		if(file!=null && !file.isEmpty()){
			String checkResult = jsResDoctorBiz.checkImg(file);
			String resultPath = "";
			if(!GlobalConstant.FLAG_Y.equals(checkResult)){
				return checkResult;
			}else{
				resultPath = jsResDoctorBiz.saveFileToDirs("", file, "jsresImages_delay");
				return resultPath;
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}



	/**
	 * 导出花名册
	 */
	@RequestMapping(value="/exportDoctor")
	public void exportDoctor(HttpServletRequest request, HttpServletResponse response,String sessionNumber,ResDoctor doctor,
							 SysUser user,String baseId,String jointOrgFlag,String derateFlag,String orgLevel,String[] trainYears,
							 String[] datas,String graduationYear,String TCMAssiGeneral,String trainingTypeId,String zlFlag)throws Exception{
		String[] headLines = null;
		headLines = new String[]{
				"住院医师规范化培训"+sessionNumber+"级招收对象花名册",
				"省（区、市）卫生计生行政部门（盖章）：江苏省中医药局        填报人：黄奇骏      联系方式：025-83620518",
		};
		String[] titles = new String[]{
				":编号",
				"sysUser.userName:姓名",
				"sysUser.sexName:性别",
				"sysUser.userBirthday:出生年月",
				"sysUser.nationName:民族",
				"sysUser.idNo:身份证号码（若为其他证件，需注明）",
				"sysUser.userPhone:联系方式（手机）",
				"sysUser.userEmail:联系方式（邮箱）",
				"userResumeExt.graduatedName:本科毕业院校及专业",
				"userResumeExt.specialized:本科毕业院校及专业",
				"userResumeExt.graduationTime:毕业时间",
				"sysUser.educationName:最高学历",
				"doctor.graduatedName:最高学历毕业院校",
				"doctor.specialized:最高学历毕业专业",
				"doctor.graduationTime:获得最高学历时间",
				"doctor.doctorTypeName:身份类型（单位人/社会人）",
                "doctor.sendCityName:派出地市（限“委培单位人”填写）",
				"doctor.workOrgName:派出单位（限“单位人”填写）",
				"doctor.orgName:培训基地（若在协同单位，需注明）",
				"recruit.catSpeName:培训专业",
				"recruit.recruitDate:参训时间",
				"recruit.trainYear:计划参训时限",
		};
		List<String>docTypeList=new ArrayList<String>();
		SysOrg org=new SysOrg();
		SysUser exSysUser=GlobalContext.getCurrentUser();
		List<String>jointOrgFlowList=new ArrayList<String>();
		List<JszyUserInfoExtForm> userExtForms=new ArrayList<JszyUserInfoExtForm>();
		ResDoctorRecruit  recruit=new ResDoctorRecruit();
		if(StringUtil.isNotBlank(graduationYear)){
			recruit.setGraduationYear(graduationYear);
		}
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				List<ResJointOrg> resJointOrgsList=jointOrgBiz.searchResJointByOrgFlow(exSysUser.getOrgFlow());
				if(resJointOrgsList!=null&&!resJointOrgsList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgsList){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
				jointOrgFlowList.add(exSysUser.getOrgFlow());
			}else{
				jointOrgFlowList.add(exSysUser.getOrgFlow());
			}
		}
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)){
			if(StringUtil.isNotBlank(doctor.getOrgFlow())){
				if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
					List<ResJointOrg> resJointOrgsList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
					for(ResJointOrg jointOrg:resJointOrgsList){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
				jointOrgFlowList.add(doctor.getOrgFlow());
			}else{
				SysOrg sysOrg=orgBiz.readSysOrg(exSysUser.getOrgFlow());
				if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
					jointOrgFlowList.add(exSysUser.getOrgFlow());
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
								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
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
		}
		if(StringUtil.isNotBlank(derateFlag)){
			if(GlobalConstant.FLAG_Y.equals(derateFlag)){
				//doctor.setTrainingTypeId(JszyTrainCategoryEnum.DoctorTrainingSpe.getId());
				recruit.setTrainYear(JszyResTrainYearEnum.ThreeYear.getId());
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
		recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
		ResRec resRec =new ResRec();
		resRec.setRecTypeId(ResRecTypeEnum.ReturnTraining.getId());
		Map<String, Object> doctorResumeMap=new HashMap<String, Object>();
		doctorResumeMap.put("doctor", doctor);
		doctorResumeMap.put("user", user);
		doctorResumeMap.put("derateFlag", derateFlag);
		doctorResumeMap.put("jointOrgFlowList", jointOrgFlowList);
		doctorResumeMap.put("sysOrg", org);
		doctorResumeMap.put("resDoctorRecruit", recruit);
		doctorResumeMap.put("docTypeList", docTypeList);
		doctorResumeMap.put("trainYears", trainYears);
		doctorResumeMap.put("zlFlag", zlFlag);
		List<String> trainTypeIdList = new ArrayList<String>();
		if (StringUtil.isBlank(trainingTypeId)) {
			for (JszyTrainCategoryEnum e : JszyTrainCategoryEnum.values()) {
                trainTypeIdList.add(e.getId());
			}
		} else {
			if(GlobalConstant.FLAG_Y.equals(TCMAssiGeneral)){
				trainTypeIdList.add(JszyTrainCategoryEnum.TCMAssiGeneral.getId());
			}else {
				trainTypeIdList.add(trainingTypeId);
			}
		}
		doctorResumeMap.put("trainTypeIdList",trainTypeIdList);
		if(trainTypeIdList.size() > 0){
			doctor.setTrainingTypeId("");
		}
		List<JszyDoctorInfoExt> doctorInfoExts = jsResDoctorRecruitBiz.searchDoctorInfoResume(doctorResumeMap);
//		List<JszyDoctorInfoExt> doctorInfoExts=jsResDoctorRecruitBiz.searchDoctorInfoResume(recruit, doctor, user, org, jointOrgFlowList, derateFlag, docTypeList);
		List<Map<String, Object>> jointOrgs = jsResDoctorRecruitBiz.searchJointOrgList();
		Map<Object, Object> orgAndJointNameMap=new HashMap<Object, Object>();
		if(jointOrgs!=null&&!jointOrgs.isEmpty()){
			for(Map<String,Object> en:jointOrgs){
				Object key=en.get("key");
				Object value=en.get("value");
				orgAndJointNameMap.put(key, value);
			}
		}
		if(doctorInfoExts!=null&&!doctorInfoExts.isEmpty()){
			for(JszyDoctorInfoExt d:doctorInfoExts){
				if(JszyResDocTypeEnum.CompanyEntrust.equals(d.getResDoctor().getDoctorTypeId())) {
					d.getResDoctor().setSendCityName(d.getResDoctor().getSendProvName() + "-" + d.getResDoctor().getSendCityName());
				}
				JszyUserInfoExtForm JszyUserInfoExtForm=new JszyUserInfoExtForm();
				if(orgAndJointNameMap.containsKey(d.getOrgFlow())){
					d.getResDoctor().setOrgName(orgAndJointNameMap.get(d.getOrgFlow())+"("+d.getOrgName()+")");
				}
				String content=d.getUserResume().getUserResume();
				if(StringUtil.isNotBlank(content)){
					BaseUserResumeExtInfoForm form=JaxbUtil.converyToJavaBean(content, BaseUserResumeExtInfoForm.class);
					JszyUserInfoExtForm.setUserResumeExt(form);
				}
				SysUser su = d.getSysUser();
				String cretTypeId = su.getCretTypeId();
				if(StringUtil.isNotBlank(cretTypeId)){
					if(!CertificateTypeEnum.Shenfenzheng.getId().equals(cretTypeId) ){
						su.setIdNo(su.getIdNo() +"(" + CertificateTypeEnum.getNameById(cretTypeId) +")");
					}
				}
				//处理中医导出生日、民族、最高学历等为空的问题
				if(StringUtil.isBlank(su.getUserBirthday())&&StringUtil.isNotBlank(su.getIdNo())){
					su.setUserBirthday(DateUtil.transDate(su.getIdNo().substring(6, 14),"yyyy-MM-dd"));
				}
				if(StringUtil.isBlank(su.getNationName())&&StringUtil.isNotBlank(su.getNationId())){
					su.setNationName(UserNationEnum.getNameById(su.getNationId()));
				}
				if(StringUtil.isBlank(su.getEducationName())&&StringUtil.isNotBlank(su.getEducationId())){
					su.setEducationName(DictTypeEnum.UserEducation.getDictNameById(su.getEducationId()));
				}
				//获得最高学历时间
				if(JszyUserInfoExtForm.getUserResumeExt()!=null){
					BaseUserResumeExtInfoForm userResumeExt=JszyUserInfoExtForm.getUserResumeExt();
					String isMaster=userResumeExt.getIsMaster();
					String isDoctor=userResumeExt.getIsDoctor();
					String doctorTypeId=d.getResDoctor().getDoctorTypeId();
					if(GlobalConstant.FLAG_Y.equals(isDoctor)){
						d.getResDoctor().setGraduationTime(userResumeExt.getDoctorGraTime());
					}else if(GlobalConstant.FLAG_Y.equals(isMaster)&&!JszyResDocTypeEnum.Graduate.getId().equals(doctorTypeId)){
						d.getResDoctor().setGraduationTime(userResumeExt.getMasterGraTime());
					}else{
						d.getResDoctor().setGraduationTime(userResumeExt.getGraduationTime());
					}
				}
				JszyUserInfoExtForm.setSysUser(su);
				JszyUserInfoExtForm.setDoctor(d.getResDoctor());
				ResDoctorRecruit recruit3 = d;
				JszyUserInfoExtForm.setRecruit(recruit3);
				if(StringUtil.isNotBlank(d.getTrainYear())){
					d.setTrainYear(JszyResTrainYearEnum.getNameById(d.getTrainYear()));
				}
				userExtForms.add(JszyUserInfoExtForm);
			}
		}
		String fileName = "住院医师规范化培训"+sessionNumber+"级招收对象花名册.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		ExcleUtile.exportSimpleExcleWithHeadlin(headLines, titles, userExtForms, response.getOutputStream());
		response.setContentType("application/octet-stream;charset=UTF-8");
	}
	/**
	 * 导出国家医师协会名称
	 */
	@RequestMapping(value="/exportForDetail")
	public void exportForDetail(HttpServletRequest request, HttpServletResponse response,String sessionNumber,ResDoctor doctor,SysUser user,String[] trainYears,
                                String baseId,String jointOrgFlag,String derateFlag,String orgLevel,String[] datas,String graduationYear,String zlFlag)throws Exception{
		List<String>docTypeList=new ArrayList<String>();
		SysOrg org=new SysOrg();
		SysUser exSysUser=GlobalContext.getCurrentUser();
		List<String>jointOrgFlowList=new ArrayList<String>();
		ResDoctorRecruit  recruit=new ResDoctorRecruit();
		if(StringUtil.isNotBlank(graduationYear)){
			recruit.setGraduationYear(graduationYear);
		}
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_LOCAL)) {
			if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				List<ResJointOrg> resJointOrgsList=jointOrgBiz.searchResJointByOrgFlow(exSysUser.getOrgFlow());
				if(resJointOrgsList!=null&&!resJointOrgsList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgsList){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
				jointOrgFlowList.add(exSysUser.getOrgFlow());
			}else{
				jointOrgFlowList.add(exSysUser.getOrgFlow());
			}
		}
		if (getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_CHARGE)||getSessionAttribute(GlobalConstant.USER_LIST_SCOPE).equals(GlobalConstant.USER_LIST_GLOBAL)){
			if(StringUtil.isNotBlank(doctor.getOrgFlow())){
				if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
					List<ResJointOrg> resJointOrgsList=jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
					for(ResJointOrg jointOrg:resJointOrgsList){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
				jointOrgFlowList.add(doctor.getOrgFlow());
			}else{
				SysOrg sysOrg=orgBiz.readSysOrg(exSysUser.getOrgFlow());
				if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
					jointOrgFlowList.add(exSysUser.getOrgFlow());
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
								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
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
		}
		if(StringUtil.isNotBlank(derateFlag)){
			if(GlobalConstant.FLAG_Y.equals(derateFlag)){
				//doctor.setTrainingTypeId(JszyTrainCategoryEnum.DoctorTrainingSpe.getId());
				recruit.setTrainYear(JszyResTrainYearEnum.ThreeYear.getId());
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
		recruit.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
		Map<String, Object> doctorResumeMap=new HashMap<String, Object>();
		doctorResumeMap.put("doctor", doctor);
		doctorResumeMap.put("user", user);
		doctorResumeMap.put("derateFlag", derateFlag);
		doctorResumeMap.put("jointOrgFlowList", jointOrgFlowList);
		doctorResumeMap.put("sysOrg", org);
		doctorResumeMap.put("resDoctorRecruit", recruit);
		doctorResumeMap.put("docTypeList", docTypeList);
		doctorResumeMap.put("trainYears", trainYears);
		doctorResumeMap.put("zlFlag", zlFlag);
		List<JszyDoctorInfoExt> doctorInfoExts=jsResDoctorRecruitBiz.searchDoctorInfoResume(doctorResumeMap);
//		List<JszyDoctorInfoExt> doctorInfoExts=jsResDoctorRecruitBiz.searchDoctorInfoResume(recruit, doctor, user, org, jointOrgFlowList, derateFlag, docTypeList);
		jsResDoctorBiz.exportForDetail(doctorInfoExts, response);
	}
	/**
	 * 是否上传学生说明
	 */
	@RequestMapping(value={"/checkDoctorAuth"})
	@ResponseBody
	public String checkDoctorAuth(String doctorFlow,String roleFlag){
		String recTypeId = ResRecTypeEnum.DoctorAuth.getId();
		List<ResRec> resRecList = resRecBiz.searchByUserFlowAndTypeId(doctorFlow, recTypeId);
		if(resRecList != null && !resRecList.isEmpty()){
			return GlobalConstant.FLAG_Y;
		}
		return GlobalConstant.FLAG_N;
	}
	/**
	 * 培训登记
	 */
	@RequestMapping(value="/trainRegister")
	public String trainRegister(String hideApprove,String doctorFlow,String roleFlag,String search,Model model){
		SysUser currUser = GlobalContext.getCurrentUser();
		String operUserFlow = currUser.getUserFlow();
		String recTypeId = ResRecTypeEnum.DoctorAuth.getId();
		List<ResRec> resRecList = resRecBiz.searchByUserFlowAndTypeId(operUserFlow, recTypeId);
//		if(StringUtil.isNotBlank(doctorFlow)){
//			return "redirect:/jszy/doctor/process?hideApprove="+hideApprove+"&doctorFlow="+doctorFlow+"&roleFlag="+roleFlag+"&search="+search;
//		}
//		if(resRecList != null && !resRecList.isEmpty()){
//			return "redirect:/jszy/doctor/process?hideApprove="+hideApprove+"&roleFlag="+roleFlag+"&search="+search;
//		}else{
		DoctorAuth doctorAuth = doctorAuthBiz.searchAuthByOperUserFlow(operUserFlow);
		if(doctorAuth != null){
			model.addAttribute("doctorAuth",doctorAuth);
		}
		model.addAttribute("resRecList",resRecList);
			return "jszy/doctor/recordHandbook";
//		}
	}
	/**
	 * 培训登记过程
	 */
	@RequestMapping(value="/process",method={RequestMethod.GET})
	public String process( Model model,String hideApprove,String doctorFlow,String roleFlag,String search){
		model.addAttribute("hideApprove",hideApprove);
		model.addAttribute("search",search);
		ResDoctor doctor=new ResDoctor();
		SysUser sysUser=new SysUser();
		if (StringUtil.isNotBlank(doctorFlow)) {
			sysUser=userBiz.readSysUser(doctorFlow);
			doctor= resDoctorBiz.searchByUserFlow(doctorFlow);
		}else{
			sysUser = GlobalContext.getCurrentUser();
			doctor= resDoctorBiz.searchByUserFlow(sysUser.getUserFlow());
		}
		String resRecType=ResRecTypeEnum.DoctorAuth.getId();
		List<ResRec> resRec=resRecBiz.searchByRecWithBLOBs(resRecType,sysUser.getUserFlow());
		if (resRec!=null && !resRec.isEmpty()) {
			model.addAttribute("resRec",resRec.get(0));
		}
		model.addAttribute("doctor", doctor);
		if(doctor!=null && StringUtil.isNotBlank(doctor.getRotationFlow())){

			List<SchDoctorDept> doctorDeptList = doctorDeptBiz.searchDoctorDeptForReductionIgnoreStatus(doctor.getDoctorFlow(),doctor.getRotationFlow());
			Map<String,SchDoctorDept> doctorDeptMap = new HashMap<String, SchDoctorDept>();
			if(doctorDeptList!=null && !doctorDeptList.isEmpty()){
				for(SchDoctorDept sdd : doctorDeptList){
					String key = sdd.getGroupFlow()+sdd.getStandardDeptId();
					doctorDeptMap.put(key,sdd);
				}
			}

			SchRotation rotation = rotationBiz.readSchRotation(doctor.getRotationFlow());
			model.addAttribute("rotation", rotation);

			List<SchRotationGroup> groupList =  groupBiz.searchSchRotationGroup(doctor.getRotationFlow());
			model.addAttribute("groupList", groupList);

			List<SchRotationDept> deptList = rotationDeptBiz.searchSchRotationDept(rotation.getRotationFlow());

			Map<String,List<SchRotationDept>> rotationDeptMap = new HashMap<String, List<SchRotationDept>>();

			//计算轮转时间内登记进度
			List<ResRec> recList = new ArrayList<ResRec>();
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("doctorFlow",sysUser.getUserFlow());

			for(SchRotationDept dept :deptList){
				List<SchRotationDept> temp = rotationDeptMap.get(dept.getGroupFlow());
				if(temp == null){
					temp = new ArrayList<SchRotationDept>();
				}
				rotationDeptMap.put(dept.getGroupFlow(), temp);

				String reductionKey = dept.getGroupFlow()+dept.getStandardDeptId();
				SchDoctorDept reductionDept = doctorDeptMap.get(reductionKey);
				if(reductionDept!=null){
					if(!GlobalConstant.RECORD_STATUS_Y.equals(reductionDept.getRecordStatus())){
						continue;
					}
					String reductionSchMonth = reductionDept.getSchMonth();
					dept.setSchMonth(reductionSchMonth);
				}
				temp.add(dept);

				String groupFlow = dept.getGroupFlow();
				String standardDeptId = dept.getStandardDeptId();
				paramMap.put("standardGroupFlow",groupFlow);
				paramMap.put("standardDeptId",standardDeptId);
				paramMap.put("processFlow",dept.getRecordFlow());
				List<ResRec> recs = resRecBiz.searchProssingRec(paramMap);
				if(recs!=null && !recs.isEmpty()){
					recList.addAll(recs);
				}
			}
			model.addAttribute("rotationDeptMap", rotationDeptMap);

			List<SchArrangeResult> resultList = resultBiz.searchSchArrangeResultByDoctor(doctor.getDoctorFlow());
			Map<String,List<SchArrangeResult>> resultMap = new HashMap<String, List<SchArrangeResult>>();
			Map<String,Float> realMonthMap = new HashMap<String,Float>();
			for(SchArrangeResult result : resultList){
				String key = result.getStandardGroupFlow()+result.getStandardDeptId();

				List<SchArrangeResult> temp = resultMap.get(key);
				if(temp == null){
					temp = new ArrayList<SchArrangeResult>();
					resultMap.put(key, temp);
				}
				temp.add(result);

				Float month = realMonthMap.get(key);
				if(month == null){
					month = 0f;
				}
				String realMonth = result.getSchMonth();
				if(StringUtil.isNotBlank(realMonth)){
					Float realMonthF = Float.parseFloat(realMonth);
					month+=realMonthF;
				}

				realMonthMap.put(key,month);
			}
			model.addAttribute("resultMap", resultMap);
			model.addAttribute("realMonthMap", realMonthMap);

			//计算登记进度
			Map<String, String> processPerMap = resRecBiz.getProcessPer(doctor.getDoctorFlow(), doctor.getRotationFlow());
			model.addAttribute("processPerMap", processPerMap);

			//计算轮转时间内登记进度
			Map<String, String> processingPerMap = resRecBiz.getProcessPer(doctor.getDoctorFlow(), doctor.getRotationFlow(), null, 0, recList);
			model.addAttribute("processingPerMap", processingPerMap);
			model.addAttribute("sysUser", sysUser);
		}
		return "jsres/doctor/process";
	}
	@RequestMapping(value={"/uploadTrainYearFileAndSaveRecruit"})
	@ResponseBody
	public String uploadTrainYearFileAndSaveRecruit(MultipartFile file, String recruitFlow) throws Exception{
		String result = uploadTrainYearFile(file,null);
		if(StringUtil.isNotBlank(result)){
			ResDoctorRecruitWithBLOBs recruit = new ResDoctorRecruitWithBLOBs();
			recruit.setRecruitFlow(recruitFlow);
			recruit.setProveFileUrl(result);
			int saveResult = jsResDoctorRecruitBiz.saveDoctorRecruit(recruit);
			if(GlobalConstant.ZERO_LINE!=saveResult){
				return result;
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}
	/**
	 * 保存ResRec文件路径
	 * @return
	 */
	@RequestMapping(value="/saveRecFileUri")
	@ResponseBody
	public String saveRecFileUri(DoctorAuth doctorAuth){
		SysUser currUser = GlobalContext.getCurrentUser();
		doctorAuth.setRecTypeId(ResRecTypeEnum.DoctorAuth.getId());
		doctorAuth.setRecTypeName(ResRecTypeEnum.DoctorAuth.getName());
		doctorAuth.setOperUserFlow(currUser.getUserFlow());
		doctorAuth.setOperUserName(currUser.getUserName());
		doctorAuth.setOperTime(DateUtil.getCurrDateTime());
		int result = doctorAuthBiz.edit(doctorAuth);
		if(GlobalConstant.ZERO_LINE != result){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 上传培训考核记录手册电子版诚信声明
	 * @param file
	 * @param courseFlow
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/uploadResRecFile"})
	@ResponseBody
	public String uploadResRecFile(MultipartFile file, String courseFlow) throws Exception{
		if(file!=null && !file.isEmpty()){
			String checkResult = jsResDoctorBiz.checkImg(file);
			String resultPath = "";
			if(!GlobalConstant.FLAG_Y.equals(checkResult)){
				return checkResult;
			}else{
				resultPath = jsResDoctorBiz.saveFileToDirs("", file, "jsresImages");
				return resultPath;
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

    /**
     * 成绩查询
     * @return
     */
    @RequestMapping(value="/owenScore")
    public String owenScore(Model model,String hideApprove,String doctorFlow,String roleFlag) throws DocumentException {
        SysUser currUser = GlobalContext.getCurrentUser();
        String operUserFlow = currUser.getUserFlow();
        //List<Map<String,Object>> datas=resScoreBiz.getALLScoreByDoctoFlow(operUserFlow);
        List<ResScore> scorelist=resScoreBiz.selectByExampleWithBLOBs(operUserFlow);
        //理论成绩
        ResScore theoryScore=new ResScore();
        //技能成绩
        ResScore skillScore=new ResScore();
        //公共成绩
        ResScore publicScore=new ResScore();
        if(null!=scorelist&&scorelist.size()>0)
        {
            int theoryYear=0;
            int skillYear=0;
            for(ResScore resScore:scorelist)
            {
                int socreYear=Integer.valueOf(resScore.getScorePhaseId()==null? "-1":resScore.getScorePhaseId());

                if(ResScoreTypeEnum.TheoryScore.getId().equals(resScore.getScoreTypeId()))
                {
                    if(StringUtil.isNotBlank(String.valueOf(socreYear)))
                    {
                        if(socreYear>theoryYear)
                        {
                            theoryYear=socreYear;
                            theoryScore=resScore;
                        }
                    }

                }
                if(ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId()))
                {
                    if(StringUtil.isNotBlank(String.valueOf(socreYear)))
                    {
                        if(socreYear>skillYear)
                        {
                            skillYear=socreYear;
                            skillScore=resScore;
                        }
                    }
                }
                if(ResScoreTypeEnum.PublicScore.getId().equals(resScore.getScoreTypeId()))
                {
                    publicScore=resScore;
                }
            }
        }
        //当前年份
        model.addAttribute("thisYear",DateUtil.getYear());

        model.addAttribute("theoryScore",theoryScore);
        model.addAttribute("skillScore",skillScore);
        model.addAttribute("publicScore",publicScore);

        //技能成绩
        List<ResScore> skillList=new ArrayList<ResScore>();
        List<ResScore> theoryList=new ArrayList<ResScore>();

        if(null!=scorelist&&scorelist.size()>0)
        {
            for(ResScore resScore:scorelist)
            {
                if(ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId()))
                {
                    //只取最新年份成绩
                    if(skillList.size() == 0 ){
                        skillList.add(resScore);
                    }else{
                        int oldYear = Integer.parseInt(skillList.get(0).getScorePhaseId());
                        int newYear =  Integer.parseInt(resScore.getScorePhaseId());
                        if(newYear > oldYear){
                            skillList.remove(0);
                            skillList.add(resScore);
                        }
                    }

                }else if(ResScoreTypeEnum.TheoryScore.getId().equals(resScore.getScoreTypeId()))
                {
                    //只取最新年份成绩
                    if(theoryList.size() == 0 ){
                        theoryList.add(resScore);
                    }else{
                        int oldYear = Integer.parseInt(theoryList.get(0).getScorePhaseId());
                        int newYear =  Integer.parseInt(resScore.getScorePhaseId());
                        if(newYear > oldYear){
                            theoryList.remove(0);
                            theoryList.add(resScore);
                        }
                    }
                }
            }
        }
        //所有技能科目详情
        Map<String,Map<String,String>> skillExtScoreMap=new HashMap<String,Map<String,String>>();
        for(int i=0;i<skillList.size();i++)
        {
            Map<String,String> extScore=new HashMap<String,String>();
            ResScore resScore=skillList.get(i);
            String content = null==resScore ? "":resScore.getExtScore();
            if(StringUtil.isNotBlank(content)) {
                Document doc = DocumentHelper.parseText(content);
                Element root = doc.getRootElement();
                Element extScoreInfo = root.element("extScoreInfo");
                if (extScoreInfo != null) {
                    List<Element> extInfoAttrEles = extScoreInfo.elements();
                    if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                        for (Element attr : extInfoAttrEles) {
                            String attrName = attr.getName();
                            String attrValue = attr.getText();
                            extScore.put(attrName, attrValue);
                        }
                    }
                }
            }
            skillExtScoreMap.put(resScore.getScoreFlow(),extScore);
        }
        model.addAttribute("skillList",skillList);
        model.addAttribute("theoryList",theoryList);
        model.addAttribute("skillExtScoreMap",skillExtScoreMap);
        //公共科目成绩详情
        String content = null==publicScore ? "":publicScore.getExtScore();
        Map<String,String> extScoreMap=new HashMap<String,String>();
        if(StringUtil.isNotBlank(content)) {
            Document doc = DocumentHelper.parseText(content);
            Element root = doc.getRootElement();
            Element extScoreInfo = root.element("extScoreInfo");
            if (extScoreInfo != null) {
                List<Element> extInfoAttrEles = extScoreInfo.elements();
                if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                    for (Element attr : extInfoAttrEles) {
                        String attrName = attr.getName();
                        String attrValue = attr.getText();
                        extScoreMap.put(attrName, attrValue);
                    }
                }
            }
        }
        model.addAttribute("extScore",extScoreMap);
        //个人信息
        ResDoctor resDoctor=resDoctorBiz.searchByUserFlow(operUserFlow);
        SysUser sysUser=userBiz.readSysUser(operUserFlow);
        PubUserResume pubUserResume = userResumeBiz.readPubUserResume(operUserFlow);
        if(pubUserResume != null){
            String xmlContent =  pubUserResume.getUserResume();
            if(StringUtil.isNotBlank(xmlContent)){
                //xml转换成JavaBean
				BaseUserResumeExtInfoForm userResumeExt=null;
                userResumeExt=userResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class);
//				UserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
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
                    model.addAttribute("userResumeExt", userResumeExt);
                }
            }
        }
        ResDoctorRecruit recruit =new ResDoctorRecruit();
        recruit.setDoctorFlow(operUserFlow);
        recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        recruit.setAuditStatusId(ResDoctorAuditStatusEnum.Passed.getId());
        List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
        ResDoctorRecruit resDoctorRecruit=null;
        if(recruitList != null && !recruitList.isEmpty()){
            resDoctorRecruit=recruitList.get(0);
            model.addAttribute("recruitList", recruitList);
            model.addAttribute("doctorRecruit",recruitList.get(0));
        }
        //保存医师培训时间
        if(resDoctorRecruit!=null)
        {
            String endTime="";String startTime="";
            //开始时间
            String recruitDate=resDoctorRecruit.getRecruitDate();
            //培养年限
            String trianYear=resDoctorRecruit.getTrainYear();
            String graudationYear=resDoctorRecruit.getGraduationYear();
            if(StringUtil.isNotBlank(recruitDate)&&StringUtil.isNotBlank(graudationYear))
            {

                try {
                    int year=0;
                    year=Integer.valueOf(graudationYear)-Integer.valueOf(recruitDate.substring(0,4));
                    if(year!=0) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse(recruitDate);
                        startTime = recruitDate;
                        //然后使用Calendar操作日期
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                        calendar.add(Calendar.DATE, -1);
                        //把得到的日期格式化成字符串类型的时间
                        endTime = sdf.format(calendar.getTime());
                    }
                } catch (Exception e) {
                    endTime="";
                }
            }
            //如果没有结业考核年份，按照届别与培养年限计算
            if(StringUtil.isNotBlank(recruitDate)&&StringUtil.isNotBlank(trianYear)&&StringUtil.isBlank(endTime))
            {
                int year=0;
                if(trianYear.equals(JsResTrainYearEnum.OneYear.getId()))
                {
                    year=1;
                }
                if(trianYear.equals(JsResTrainYearEnum.TwoYear.getId()))
                {
                    year=2;
                }
                if(trianYear.equals(JsResTrainYearEnum.ThreeYear.getId()))
                {
                    year=3;
                }
                if(year!=0) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse(recruitDate);
                        startTime = recruitDate;
                        //然后使用Calendar操作日期
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                        calendar.add(Calendar.DATE,-1);

                        //把得到的日期格式化成字符串类型的时间
                        endTime = sdf.format(calendar.getTime());
                    } catch (Exception e) {

                    }
                }
            }
            if (!startTime.equals(resDoctor.getCompleteStartDate()) || !endTime.equals(resDoctor.getCompleteEndDate())) {
                resDoctor.setCompleteStartDate(startTime);
                resDoctor.setCompleteEndDate(endTime);
                resDoctorBiz.editDoctor(resDoctor);
            }
        }
        model.addAttribute("resDoctor",resDoctor);
        model.addAttribute("user",sysUser);
        return "jszy/doctor/owenScore";
    }
}
