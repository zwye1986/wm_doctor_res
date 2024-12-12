package com.pinde.sci.ctrl.jsres;


import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResLiveTrainingBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResTarinNotice;
import com.pinde.sci.model.mo.ResTrainingOpinion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/jsres/training")
public class JsResTraningController extends GeneralController {

	@Autowired
	private IResLiveTrainingBiz resLiveTrainingBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IJsResDoctorBiz jsResDoctorBiz;
	@RequestMapping(value = "/opinions")
	public String options(Model model){
		SysUser currentUser = GlobalContext.getCurrentUser();
		String userFlow = currentUser.getUserFlow();
		List<ResTrainingOpinion> trainingOpinions=resLiveTrainingBiz.readByOpinionUserFlow(userFlow);
		model.addAttribute("trainingOpinions",trainingOpinions);
		return "jsres/doctor/opinions";
	}

	@RequestMapping(value = "/getOpinions")
	public String getOpinions(String opinionUserContent, String replayStatus, Model model, HttpServletRequest request, Integer currentPage){
		if(currentPage==null){
			currentPage=1;
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		SysUser currentUser = GlobalContext.getCurrentUser();
		String orgFlow = currentUser.getOrgFlow();
		String resTrainingSpeId = "";
//		List<ResTrainingOpinion> trainingOpinions=resLiveTrainingBiz.searchByOpinionUserContentAndReplayStatus(opinionUserContent,replayStatus,orgFlow);
		List<ResTrainingOpinion> trainingOpinions=resLiveTrainingBiz.searchByOpinionUserContentAndReplayStatusNew(opinionUserContent,replayStatus,orgFlow,resTrainingSpeId);
		model.addAttribute("trainingOpinions",trainingOpinions);
		return "jsres/hospital/trainingOpinions";
	}

	@RequestMapping(value="/saveOpinions")
	@ResponseBody
	public String saveOpinions(ResTrainingOpinion trainingOpinion){
		SysUser currentUser = GlobalContext.getCurrentUser();
		String userFlow = currentUser.getUserFlow();
		String userName = currentUser.getUserName();
		if(StringUtil.isNotBlank(userName)){
			trainingOpinion.setOpinionUserName(userName);
		}
		trainingOpinion.setOpinionUserFlow(userFlow);
		String orgFlow ="";
		String orgName ="";
		if(StringUtil.isBlank(orgFlow)){
			ResDoctor doctor = resDoctorBiz.readDoctor(userFlow);
			if(StringUtil.isNotBlank(doctor.getSecondOrgFlow()))
			{
				orgFlow = doctor.getSecondOrgFlow();
				orgName = doctor.getSecondOrgName();
			}else {
				orgFlow = doctor.getOrgFlow();
				orgName = doctor.getOrgName();
			}
		}
		if(StringUtil.isNotBlank(orgFlow)){
			trainingOpinion.setOrgFlow(orgFlow);
		}
		if(StringUtil.isNotBlank(orgName)){
			trainingOpinion.setOrgName(orgName);
		}
		String currTime = DateUtil.getCurrDateTime();
		trainingOpinion.setEvaTime(currTime);
		resLiveTrainingBiz.edit(trainingOpinion);
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
	}

	@RequestMapping(value="/replyOpinions")
	public String replyOpinions(String trainingOpinionFlow,Model model){
		ResTrainingOpinion trainingOpinion=resLiveTrainingBiz.read(trainingOpinionFlow);
		model.addAttribute("trainingOpinion",trainingOpinion);
		return "jsres/hospital/replyOpinions";
	}

	@RequestMapping(value="/editOpinions")
	public String editOpinions(String trainingOpinionFlow,Model model){
		ResTrainingOpinion trainingOpinion = resLiveTrainingBiz.read(trainingOpinionFlow);
		model.addAttribute("trainingOpinion",trainingOpinion);
		return "jsres/doctor/editOpinions";
	}

	@RequestMapping(value="/delOpinions")
	@ResponseBody
	public String delOpinions(String trainingOpinionFlow){
		ResTrainingOpinion trainingOpinion = resLiveTrainingBiz.read(trainingOpinionFlow);
        trainingOpinion.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		resLiveTrainingBiz.edit(trainingOpinion);
        return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
	}

	@RequestMapping(value="/saveOpinionReply")
	@ResponseBody
	public String saveOpinionReply(ResTrainingOpinion trainingOpinion){
		SysUser currentUser = GlobalContext.getCurrentUser();
		String userFlow = currentUser.getUserFlow();
		String userName = currentUser.getUserName();
		if(StringUtil.isNotBlank(userName)){
			trainingOpinion.setOpinionReplyName(userName);
		}
		trainingOpinion.setOpinionReplyUserFlow(userFlow);
		String currTime = DateUtil.getCurrDateTime();
		trainingOpinion.setReplyTime(currTime);
		resLiveTrainingBiz.edit(trainingOpinion);
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
	}

	@RequestMapping(value = "/getGuides/{roleFlag}")
	public String director(String resNoticeTitle, @PathVariable String roleFlag, Model model, Integer currentPage,HttpServletRequest request){

 		SysUser currUser = GlobalContext.getCurrentUser();
		String orgFlow = currUser.getOrgFlow();
		String userFlow = currUser.getUserFlow();
		ResDoctor doctor = resDoctorBiz.readDoctor(userFlow);
		if(StringUtil.isBlank(orgFlow)){
			orgFlow = doctor.getOrgFlow();
		}
		if(currentPage==null){
			currentPage=1;
		}
		List<ResTarinNotice> tarinNotices =null;
		PageHelper.startPage(currentPage,getPageSize(request));
		if("doctor".equals(roleFlag))
		{
			if(StringUtil.isNotBlank(doctor.getSecondOrgFlow()))
			{
				orgFlow = doctor.getSecondOrgFlow();
			}else {
				orgFlow = doctor.getOrgFlow();
			}
		}
        if ("doctor".equals(roleFlag) && null != doctor && doctor.getDoctorTypeId().equals(com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId())
				&&(StringUtil.isNotBlank(doctor.getWorkOrgId())||StringUtil.isNotBlank(doctor.getWorkOrgName()))){
			tarinNotices =resLiveTrainingBiz.searchByTitleOrgFlowAndSchool(resNoticeTitle, orgFlow,doctor);
		}else {
			if("global".equals(roleFlag))
			{
				orgFlow="";
			}
			tarinNotices =resLiveTrainingBiz.searchByTitleOrgFlow(resNoticeTitle, orgFlow,roleFlag);
		}
		model.addAttribute("tarinNotices",tarinNotices);
		model.addAttribute("roleFlag",roleFlag);
		return "jsres/hospital/director";
	}


	@RequestMapping(value = "/openDirector")
	public String openDirector(String recordFlow,String roleFlag,Model model){
		if(StringUtil.isNotBlank(recordFlow)){
			ResTarinNotice tarinNotice = resLiveTrainingBiz.readNotice(recordFlow);
			model.addAttribute("tarinNotice",tarinNotice);
			model.addAttribute("roleFlag",roleFlag);
			return "jsres/hospital/editDirector";
		}else{
			model.addAttribute("roleFlag",roleFlag);
			return "jsres/hospital/editDirector";
		}
	}

	@RequestMapping(value = "/saveDirector")
	@ResponseBody
	public String saveDirector(ResTarinNotice tarinNotice,String roleFlag){
		SysUser current = GlobalContext.getCurrentUser();

		if(!"global".equals(roleFlag))
		{
			String orgFlow = current.getOrgFlow();
			String orgName = current.getOrgName();
			tarinNotice.setOrgName(orgName);
			tarinNotice.setOrgFlow(orgFlow);
		}
		resLiveTrainingBiz.edit(tarinNotice);
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
	}

	@RequestMapping(value = "/delDirector")
	@ResponseBody
	public String delDirector(String recordFlow){
		ResTarinNotice tarinNotice = resLiveTrainingBiz.readNotice(recordFlow);
        tarinNotice.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		resLiveTrainingBiz.edit(tarinNotice);
        return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
	}

	@RequestMapping(value={"/uploadNoticePic"})
	@ResponseBody
	public String uploadNoticePic(MultipartFile file) throws Exception{
		if(file!=null && !file.isEmpty()){
			String checkResult = jsResDoctorBiz.checkImg(file);
			String resultPath = "";
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(checkResult)) {
				return checkResult;
			}else{
				resultPath = jsResDoctorBiz.saveFileToDirs("", file, "jsresImages_notice");
				return resultPath;
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}

	/****************************高******校******管******理******员******角******色************************************************/

	@RequestMapping(value = "/getOpinionsForUni")
	public String getOpinionsForUni(String opinionUserContent, String orgFlow, Model model, HttpServletRequest request, Integer currentPage){
		if(currentPage==null){
			currentPage=1;
		}
		SysUser currentUser = GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(currentUser.getOrgFlow());

		List<SysOrg> orgs=orgBiz.getUniOrgs(org.getSendSchoolId(),org.getSendSchoolName());
		model.addAttribute("orgs",orgs);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<ResTrainingOpinion> trainingOpinions=resLiveTrainingBiz.searchOpinionByUni(opinionUserContent,orgFlow,org);
		model.addAttribute("trainingOpinions",trainingOpinions);
		return "jsres/university/trainingOpinions";
	}

	@RequestMapping(value = "/getGuidesForUni")
	public String getGuidesForUni(String resNoticeTitle, Model model, Integer currentPage,HttpServletRequest request){

		SysUser currUser = GlobalContext.getCurrentUser();
		String orgFlow = currUser.getOrgFlow();
		if(currentPage==null){
			currentPage=1;
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<ResTarinNotice> tarinNotices = resLiveTrainingBiz.searchByTitleOrgFlow(resNoticeTitle,orgFlow, "");
		model.addAttribute("tarinNotices",tarinNotices);
		return "jsres/university/director";
	}
}
