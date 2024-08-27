package com.pinde.res.ctrl.osca;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.app.common.PasswordUtil;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.hbres.IFileBiz;
import com.pinde.res.biz.osca.IOscaAppBiz;
import com.pinde.res.enums.osca.ExamStatusEnum;
import com.pinde.res.enums.osca.ScanDocStatusEnum;
import com.pinde.res.enums.osca.ScoreStatusEnum;
import com.pinde.res.enums.osca.SignStatusEnum;
import com.pinde.core.commom.enums.UserStatusEnum;
import com.pinde.res.model.jswjw.mo.FromTitle;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.util.FtpHelperUtil;
import com.pinde.sci.util.PasswordHelper;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/osca")
public class OscaAppController {    
	private static Logger logger = LoggerFactory.getLogger(OscaAppController.class);
	
	
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);

		return "res/osca/500";
    }
	@Autowired
	private IOscaAppBiz oscaAppBiz;

	@Autowired
	private IFileBiz fileBiz;



	@RequestMapping(value={"/version"},method={RequestMethod.GET})
	public String version(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "res/osca/version";
	}
	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "res/osca/test";
	}
	@RequestMapping(value={"/remember"},method={RequestMethod.GET})
	public String remember(String userFlow,String doctorFlow,String stationFlow,String clinicalFlow , String roomRecordFlow ,
						   String scoreFlow , String fromFlow ,
						   String codeInfo , HttpServletRequest request){
		HttpSession session = request.getSession();

		session.setAttribute("userFlow", userFlow);
		session.setAttribute("doctorFlow", doctorFlow);
		session.setAttribute("stationFlow", stationFlow);
		session.setAttribute("clinicalFlow", clinicalFlow);
		session.setAttribute("roomRecordFlow", roomRecordFlow);
		session.setAttribute("scoreFlow", scoreFlow);
		session.setAttribute("fromFlow", fromFlow);
		session.setAttribute("codeInfo", codeInfo);
		return "/res/osca/test";
	}
	@RequestMapping(value={"/login"},method={RequestMethod.POST})
	public String login(String userCode,String userPasswd,String isPad,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userCode)){
			model.addAttribute("resultId", "30101");
			model.addAttribute("resultType", "用户名为空");
			return "res/osca/login";
		}
		if(StringUtil.isEmpty(userPasswd)){
			model.addAttribute("resultId", "30102"); 
			model.addAttribute("resultType", "密码为空");
			return "res/osca/login";
		}
		SysUser userinfo = oscaAppBiz.findByUserCode(userCode);
		if(userinfo==null){
			model.addAttribute("resultId", "30199");
			model.addAttribute("resultType", "用户不存在");
			return "res/osca/login";
		}else{
			//超级密码
			boolean userFlag = false;
			if(PasswordUtil.isRootPass(userPasswd)){
				model.addAttribute("userinfo", userinfo);
				userFlag = true;
			}else if(userinfo.getUserPasswd().equalsIgnoreCase(PasswordHelper.encryptPassword(userinfo.getUserFlow(), userPasswd))){
				model.addAttribute("userinfo", userinfo);
				userFlag = true;
			}else{
				model.addAttribute("resultId", "30199");
				model.addAttribute("resultType", "用户名或密码错误"); 
			}

			List<SysUserRole> userRoles = oscaAppBiz.getSysUserRole(userinfo.getUserFlow(),GlobalConstant.OSCA_WS_ID);
			if(userRoles==null || userRoles.isEmpty()){
				model.addAttribute("resultId", "3010106");
				model.addAttribute("resultType", "用户未赋权，无权限登录！");
				return "res/osca/login";
			}
			String userStatus = userinfo.getStatusId();
			String examTeaRole= oscaAppBiz.getCfgCode("osca_examtea_role_flow");

			boolean isDoctor = false;
			boolean isExamTea = false;
			boolean isAdmin = false;
			//获取当前配置的学员角色
			String doctorRole = oscaAppBiz.getCfgCode("osca_doctor_role_flow");
			//获取当前配置的学员角色
			String adminRole = oscaAppBiz.getCfgCode("osca_admin_role_flow");

			//获取最先匹配到的角色,认定该用户的角色为匹配到的角色
			for(SysUserRole sur : userRoles){
				String ur = sur.getRoleFlow();
				if(doctorRole.equals(ur)){
					isDoctor = true;
					break;
				}
				if(adminRole.equals(ur)){
					isAdmin = true;
					break;
				}
				if(examTeaRole.equals(ur)){
					isExamTea = true;
					break;
				}
			}
			if(isExamTea)
			{
				if(StringUtil.isBlank(userinfo.getRecordStatus())||"N".equals(userinfo.getRecordStatus())){
					model.addAttribute("resultId", "30197");
					model.addAttribute("resultType", "您的帐号已被停用，请联系考点进行启用");
					return "res/osca/login";
				}
				if(UserStatusEnum.Locked.getId().equals(userStatus)){
					model.addAttribute("resultId", "30197");
					model.addAttribute("resultType", "您的帐号已被锁定，请联系考点进行解锁");
					return "res/osca/login";
				}
				PubFile file=fileBiz.searchByProductFlowAndType(userinfo.getUserFlow(),"ExamTeaSigin");
				String codeInfo="func://funcFlow=osceDocJoinTea&teaFlow="+userinfo.getUserFlow();
				//获取访问路径前缀
				String uploadBaseUrl = oscaAppBiz.getCfgCode("upload_base_url");
				model.addAttribute("uploadBaseUrl",uploadBaseUrl);
				model.addAttribute("file",file);
				model.addAttribute("codeInfo",codeInfo);
				model.addAttribute("roleFlow","ExamTea");
				model.addAttribute("roleName","考官");
				if("N".equals(isPad))
				{
					model.addAttribute("resultId", "30197");
					model.addAttribute("resultType", "此为学员app端，你无权限登录");
					return "res/osca/login";
				}
				return "res/osca/login";
			}else {

				//验证用户是否有角色
				if(StringUtil.isBlank(userinfo.getRecordStatus())||"N".equals(userinfo.getRecordStatus())){
					model.addAttribute("resultId", "30197");
					model.addAttribute("resultType", "您的帐号已被停用，请联系系统管理员进行启用");
					return "res/osca/login";
				}
				if(UserStatusEnum.Locked.getId().equals(userStatus)){
					model.addAttribute("resultId", "30197");
					model.addAttribute("resultType", "您的帐号已被锁定，请联系系统管理员进行解锁");
					return "res/osca/login";
				}
				if(isDoctor) {
					model.addAttribute("isNew","N");
					//如果是注册未审核通过的学员，进入个人信息填写页面
					if("OSCE_NEW".equals(userinfo.getStatusId())){
						model.addAttribute("isNew","Y");
					}
					model.addAttribute("roleFlow","Student");
					model.addAttribute("roleName","学员");

					if(!"N".equals(isPad))
					{
						model.addAttribute("resultId", "30197");
						model.addAttribute("resultType", "此为管理员pad端，你无权限登录");
						return "res/osca/login";
					}
					return "res/osca/login";
				}else if(isAdmin){
					SysOrg org = oscaAppBiz.getOrg(userinfo.getOrgFlow());
					if (org == null) {
						model.addAttribute("resultId", "30197");
						model.addAttribute("resultType", "您暂无考点信息，无权限登录");
						return "res/osca/login";
					}
					if (!"Y".equals(org.getIsExamOrg())) {
						model.addAttribute("resultId", "30197");
						model.addAttribute("resultType", "您的帐号不是考点帐号，无权限登录");
						return "res/osca/login";
					}
					model.addAttribute("roleFlow", "Admin");
					model.addAttribute("roleName", "管理员");
					if("N".equals(isPad))
					{
						model.addAttribute("resultId", "30197");
						model.addAttribute("resultType", "此为学员app端，你无权限登录");
						return "res/osca/login";
					}
					return "res/osca/login";
				}else {
					model.addAttribute("resultId", "3010106");
					model.addAttribute("resultType", "无权限登录！");
					return "res/osca/login";
				}
			}
		}
	}

	@RequestMapping(value={"/qrCode"},method={RequestMethod.POST})
	public String qrCode(String userFlow,
						 String roleId,
						 String codeInfo,
						 HttpServletRequest request,
						 Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/osca/qrCode";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色为空");
			return "res/osca/qrCode";
		}
		if(StringUtil.isBlank(codeInfo)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "二维码信息为空");
			return "res/osca/qrCode";
		}
		if(!"ExamTea".equals(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色与实际不符");
			return "res/osca/qrCode";
		}
		Map<String,String> paramMap = new HashMap<String,String>();
		transCodeInfo(paramMap, codeInfo);
		String funcFlow=paramMap.get("funcFlow");
		String clinicalFlow=paramMap.get("clinicalFlow");
		String recordFlow=paramMap.get("recordFlow");
		String doctorFlow=paramMap.get("doctorFlow");
		String tickNum=paramMap.get("tickNum");
		paramMap.put("userFlow",userFlow);
		paramMap.put("roleId",roleId);
		model.addAttribute("paramMap",paramMap);
		if(StringUtil.isEquals(funcFlow, "queryQrCode")&&StringUtil.isNotBlank(clinicalFlow)&&StringUtil.isNotBlank(recordFlow)&&StringUtil.isNotBlank(doctorFlow)){
			//考核信息
			OscaSkillsAssessment skillsAssessment=oscaAppBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
			if(skillsAssessment==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "考核信息不存在");
				return "res/osca/qrCode";
			}
			model.addAttribute("skillsAssessment",skillsAssessment);
			//学员预约信息
			OscaDoctorAssessment doctorAssessment=oscaAppBiz.getOscaDoctorAssessmentByFlow(recordFlow);
			if(doctorAssessment==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "学员预约信息不存在");
				return "res/osca/qrCode";
			}
			model.addAttribute("doctorAssessment",doctorAssessment);
			String sigin=doctorAssessment.getSiginStatusId();
			if(StringUtil.isBlank(sigin)|| SignStatusEnum.NoSignIn.getId().equals(sigin)){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "该考生尚未考核签到，无法进行考核，请学员联系管理员签到");
				return "res/osca/qrCode";
			}
			//学员信息
			SysUser docUser=oscaAppBiz.readSysUser(doctorFlow);
			if(docUser==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "学员用户信息不存在");
				return "res/osca/qrCode";
			}
			model.addAttribute("docUser",docUser);
			ResDoctor doctor=oscaAppBiz.readResDoctor(doctorFlow);
			if(doctor==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "学员医师信息不存在");
				return "res/osca/qrCode";
			}
			model.addAttribute("doctor",doctor);
			if(!tickNum.equals(doctorAssessment.getTicketNumber())){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "准考证号不正确");
				return "res/osca/qrCode";
			}
			if(StringUtil.isBlank(doctorAssessment.getExamStartTime())||StringUtil.isBlank(doctorAssessment.getExamEndTime()))
			{
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "管理员未维护考核时间");
				return "res/osca/qrCode";
			}
			//当前时间
//			String nowTime=DateUtil.transDate(DateUtil.getCurrDateTime(),"yyyy-MM-dd HH:mm");
//			if(nowTime.compareTo(doctorAssessment.getExamStartTime())<0){
//				model.addAttribute("resultId", "3011101");
//				model.addAttribute("resultType", "考核还未开始");
//				return "res/osca/qrCode";
//			}
//			if(nowTime.compareTo(doctorAssessment.getExamEndTime())>0){
//				model.addAttribute("resultId", "3011101");
//				model.addAttribute("resultType", "考核已结束");
//				return "res/osca/qrCode";
//			}
			//查询当前考官可以考核学员哪些站点
			List<OscaSubjectStation> stations=oscaAppBiz.getTeaDocStation(paramMap);
			if(stations==null||stations.size()<=0){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "该考生不在您负责的考核范围内，无法查看该考生信息哦，换一个考生扫一扫吧~");
				return "res/osca/qrCode";
			}
			model.addAttribute("stations",stations);
			//获取访问路径前缀
			String uploadBaseUrl = oscaAppBiz.getCfgCode("upload_base_url");
			model.addAttribute("uploadBaseUrl",uploadBaseUrl);
			model.addAttribute("docUser",docUser);
			Map<String,Object> stationMap=new HashMap<>();
			int count=0;
			//记录在哪个考场扫码的

			SysUser tea=oscaAppBiz.readSysUser(userFlow);
			String examRoomFlow="";
			for(int i=0;i<stations.size();i++)
			{
				OscaSubjectStation s=stations.get(i);
				Map<String,Object> bean=new HashMap<>();
				//查询当前站点考官可以考核学员的考场
				Map<String,String> param=new HashMap<>();
				param.put("userFlow",userFlow);
				param.put("doctorFlow",doctorFlow);
				param.put("stationFlow",s.getStationFlow());
				param.put("clinicalFlow",clinicalFlow);
				//该站点考官可以考核的考场
				List<OscaSkillRoomTea> roomTeas=oscaAppBiz.getTeaRooms(param);
				OscaSkillRoomTea roomTea=null;
				if(roomTeas!=null&&roomTeas.size()>0){
					roomTea=roomTeas.get(0);
				}
				String roomRecordFlow=roomTea.getRoomRecordFlow();
				bean.put("roomRecordFlow",roomRecordFlow);
				bean.put("roomFlow",roomTea.getRoomFlow());
				bean.put("roomName",roomTea.getRoomName());
				bean.put("examStatusId",ExamStatusEnum.StayAssessment.getId());
				bean.put("examStatusName",ExamStatusEnum.StayAssessment.getName());
				if(i==0){
					examRoomFlow=roomRecordFlow;
				}
				//当前站点信息学员排队的考场
				OscaSkillRoomDoc docStation=oscaAppBiz.getOscaSkillRoomDocByDoc(param);
				if(docStation!=null){
					roomRecordFlow=docStation.getRoomRecordFlow();
					if(!roomRecordFlow.equals("Unknown")) {
						bean.put("roomRecordFlow", roomRecordFlow);
						bean.put("roomFlow", docStation.getRoomFlow());
						bean.put("roomName", docStation.getRoomName());
					}
					bean.put("examStatusId",docStation.getExamStatusId());
					bean.put("examStatusName",docStation.getExamStatusName());
					if(ExamStatusEnum.Waiting.getId().equals(docStation.getExamStatusId())) {
						examRoomFlow = roomRecordFlow;
					}
				}
				String examScoreStatusId="";
				//当前站点考官是否给学员打过分
				List<OscaSkillDocScore> scores=oscaAppBiz.getDocScoreByParam(param);
				param.put("roomRecordFlow",roomRecordFlow);
				if(scores!=null&&scores.size()>0){
					bean.put("scores",scores);
					boolean isNotSubmit=false;
					int submitCount=0;
					int saveCount=0;
					for(OscaSkillDocScore score:scores) {
						if (!score.getStatusId().equals(ScoreStatusEnum.Submit.getId())) {
							isNotSubmit=true;
						}
						if (ScoreStatusEnum.Submit.getId().equals(score.getStatusId())) {
							submitCount++;
						}
						if (ScoreStatusEnum.Save.getId().equals(score.getStatusId())) {
							saveCount++;
						}
					}
					//当前考站状态
					if(submitCount==0&&saveCount==0)
					{
						examScoreStatusId="";
					}else if(submitCount>0&&submitCount==scores.size())
					{
						examScoreStatusId="Submit";
					}else if(saveCount>0)
					{
						examScoreStatusId="Save";
					}
					if(isNotSubmit)
					{
						count++;
					}
				}
				bean.put("examScoreStatusId",examScoreStatusId);
				stationMap.put(s.getStationFlow(),bean);
				//查询当前站点下，本机构是否配置了试卷
				List<PubFile> files=oscaAppBiz.findStationFiles(s.getStationFlow(),tea.getOrgFlow());
				if(files!=null&&files.size()>0) {
					stationMap.put(s.getStationFlow() + "ExamFiles","Y" );
				}else{
					stationMap.put(s.getStationFlow() + "ExamFiles","N" );
				}
			}
			//看看考场
			OscaSkillRoom room=oscaAppBiz.getOscaskillRoomByFlow(examRoomFlow);
			//先获取考官是否已经扫过学员的二维码了
			OscaTeaScanDoc b=oscaAppBiz.getOscaTeaScanDoc(paramMap);
			//添加考官扫码学员的信息
			SysUser user=oscaAppBiz.readSysUser(userFlow);
			if(b==null)
				 b=new OscaTeaScanDoc();
			b.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			b.setClinicalFlow(clinicalFlow);
			b.setClinicalName(skillsAssessment.getClinicalName());
			b.setDoctorFlow(doctorFlow);
			b.setDoctorName(docUser.getUserName());
			b.setExamTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
			b.setRoomRecordFlow(examRoomFlow);
			b.setRoomFlow(room.getRoomFlow());
			b.setRoomName(room.getRoomName());
			if(StringUtil.isBlank(b.getStatusId())) {
				b.setStatusId(ScanDocStatusEnum.StayAssessment.getId());
				b.setStatusName(ScanDocStatusEnum.StayAssessment.getName());
			}
			b.setPartnerFlow(userFlow);
			b.setPartnerName(user.getUserName());
			b.setCodeInfo(codeInfo);//保存学员二维码信息
			int num=oscaAppBiz.editTeaScanDoc(b,user);
			model.addAttribute("showSubmit","N");
			if(count<=stations.size()&&count>0){
				model.addAttribute("showSubmit","Y");
			}
			model.addAttribute("stationMap",stationMap);
			oscaAppBiz.updateTeaScanDocStatus(doctorFlow,clinicalFlow,user);
			return "res/osca/qrCode";
		}else {
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "无效二维码");
			return "res/osca/qrCode";
		}
	}
	@RequestMapping(value={"manualCode"},method={RequestMethod.POST})
	public String manualCode(String userFlow,
						 String roleId,
						 String tickNumber,
						 HttpServletRequest request,
						 Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/osca/qrCode";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色为空");
			return "res/osca/qrCode";
		}
		if(StringUtil.isBlank(tickNumber)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请输入准考证号");
			return "res/osca/qrCode";
		}

		if(!"ExamTea".equals(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色与实际不符");
			return "res/osca/qrCode";
		}

		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("userFlow",userFlow);
		paramMap.put("roleId",roleId);
		//通过准考证号查询学员预约信息
		OscaDoctorAssessment doctorAssessment = oscaAppBiz.getOscaDoctorAssessmentByTickNumber(tickNumber);
		if (doctorAssessment == null) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员预约信息不存在");
			return "res/osca/qrCode";
		}
		paramMap.put("clinicalFlow", doctorAssessment.getClinicalFlow());
		paramMap.put("doctorFlow", doctorAssessment.getDoctorFlow());
		paramMap.put("recordFlow", doctorAssessment.getRecordFlow());
		//考核信息
		OscaSkillsAssessment skillsAssessment = oscaAppBiz.getOscaSkillsAssessmentByFlow(doctorAssessment.getClinicalFlow());
		if (skillsAssessment == null) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核信息不存在");
			return "res/osca/qrCode";
		}
		model.addAttribute("paramMap",paramMap);
		model.addAttribute("skillsAssessment", skillsAssessment);
		model.addAttribute("doctorAssessment", doctorAssessment);
		String sigin = doctorAssessment.getSiginStatusId();
		if (StringUtil.isBlank(sigin) || SignStatusEnum.NoSignIn.getId().equals(sigin)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "该考生尚未考核签到，无法进行考核，请学员联系管理员签到");
			return "res/osca/qrCode";
		}
		//学员信息
		SysUser docUser = oscaAppBiz.readSysUser(doctorAssessment.getDoctorFlow());
		if (docUser == null) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员用户信息不存在");
			return "res/osca/qrCode";
		}
		model.addAttribute("docUser", docUser);
		ResDoctor doctor = oscaAppBiz.readResDoctor(doctorAssessment.getDoctorFlow());
		if (doctor == null) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员医师信息不存在");
			return "res/osca/qrCode";
		}
		model.addAttribute("doctor", doctor);
		if(StringUtil.isBlank(doctorAssessment.getExamStartTime())||StringUtil.isBlank(doctorAssessment.getExamEndTime()))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "管理员未维护考核时间");
			return "res/osca/qrCode";
		}
		//当前时间
//		String nowTime = DateUtil.transDate(DateUtil.getCurrDateTime(), "yyyy-MM-dd HH:mm");
//		if (nowTime.compareTo(doctorAssessment.getExamStartTime()) < 0) {
//			model.addAttribute("resultId", "3011101");
//			model.addAttribute("resultType", "考核还未开始");
//			return "res/osca/qrCode";
//		}
//		if (nowTime.compareTo(doctorAssessment.getExamEndTime()) > 0) {
//			model.addAttribute("resultId", "3011101");
//			model.addAttribute("resultType", "考核已结束");
//			return "res/osca/qrCode";
//		}
		//查询当前考官可以考核学员哪些站点
		List<OscaSubjectStation> stations = oscaAppBiz.getTeaDocStation(paramMap);
		if (stations == null || stations.size() <= 0) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "该考生不在您负责的考核范围内，无法查看该考生信息哦，换一个考生扫一扫吧~");
			return "res/osca/qrCode";
		}
		model.addAttribute("stations", stations);
		Map<String, Object> stationMap = new HashMap<>();
		int count = 0;
		SysUser tea=oscaAppBiz.readSysUser(userFlow);
		//记录在哪个考场扫码的
		String examRoomFlow = "";
		for (int i = 0; i < stations.size(); i++) {
			OscaSubjectStation s = stations.get(i);
			Map<String, Object> bean = new HashMap<>();
			//查询当前站点考官可以考核学员的考场
			Map<String, String> param = new HashMap<>();
			param.put("userFlow", userFlow);
			param.put("doctorFlow", doctorAssessment.getDoctorFlow());
			param.put("stationFlow", s.getStationFlow());
			param.put("clinicalFlow", doctorAssessment.getClinicalFlow());
			//该站点考官可以考核的考场
			List<OscaSkillRoomTea> roomTeas = oscaAppBiz.getTeaRooms(param);
			OscaSkillRoomTea roomTea = null;
			if (roomTeas != null && roomTeas.size() > 0) {
				roomTea = roomTeas.get(0);
			}
			String roomRecordFlow = roomTea.getRoomRecordFlow();
			bean.put("roomRecordFlow", roomRecordFlow);
			bean.put("roomFlow", roomTea.getRoomFlow());
			bean.put("roomName", roomTea.getRoomName());
			bean.put("examStatusId", ExamStatusEnum.StayAssessment.getId());
			bean.put("examStatusName", ExamStatusEnum.StayAssessment.getName());
			if (i == 0) {
				examRoomFlow = roomRecordFlow;
			}
			//当前站点信息学员排队的考场
			OscaSkillRoomDoc docStation = oscaAppBiz.getOscaSkillRoomDocByDoc(param);
			if (docStation != null) {
				roomRecordFlow = docStation.getRoomRecordFlow();
				if (!roomRecordFlow.equals("Unknown")) {
					bean.put("roomRecordFlow", roomRecordFlow);
					bean.put("roomFlow", docStation.getRoomFlow());
					bean.put("roomName", docStation.getRoomName());
				}
				bean.put("examStatusId", docStation.getExamStatusId());
				bean.put("examStatusName", docStation.getExamStatusName());
				if (ExamStatusEnum.Waiting.getId().equals(docStation.getExamStatusId())) {
					examRoomFlow = roomRecordFlow;
				}
			}
				String examScoreStatusId="";
				//当前站点考官是否给学员打过分
				List<OscaSkillDocScore> scores=oscaAppBiz.getDocScoreByParam(param);
				param.put("roomRecordFlow", roomRecordFlow);
				if(scores!=null&&scores.size()>0){
					bean.put("scores",scores);
					boolean isNotSubmit=false;
					int submitCount=0;
					int saveCount=0;
					for(OscaSkillDocScore score:scores) {
						if (!score.getStatusId().equals(ScoreStatusEnum.Submit.getId())) {
							isNotSubmit=true;
						}
						if (ScoreStatusEnum.Submit.getId().equals(score.getStatusId())) {
							submitCount++;
						}
						if (ScoreStatusEnum.Save.getId().equals(score.getStatusId())) {
							saveCount++;
						}
					}
					//当前考站状态
					if(submitCount==0&&saveCount==0)
					{
						examScoreStatusId="";
					}else if(submitCount>0&&submitCount==scores.size())
					{
						examScoreStatusId="Submit";
					}else if(saveCount>0)
					{
						examScoreStatusId="Save";
					}
					if(isNotSubmit)
					{
						count++;
					}
				}
			bean.put("examScoreStatusId",examScoreStatusId);
			stationMap.put(s.getStationFlow(), bean);
			//查询当前站点下，本机构是否配置了试卷
			List<PubFile> files=oscaAppBiz.findStationFiles(s.getStationFlow(),tea.getOrgFlow());
			if(files!=null&&files.size()>0) {
				stationMap.put(s.getStationFlow() + "ExamFiles","Y" );
			}else{
				stationMap.put(s.getStationFlow() + "ExamFiles","N" );
			}
		}
		//看看考场
		OscaSkillRoom room = oscaAppBiz.getOscaskillRoomByFlow(examRoomFlow);
		//先获取考官是否已经扫过学员的二维码了
		OscaTeaScanDoc b = oscaAppBiz.getOscaTeaScanDoc(paramMap);
		//添加考官扫码学员的信息
		SysUser user = oscaAppBiz.readSysUser(userFlow);
		if (b == null)
			b = new OscaTeaScanDoc();
		b.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		b.setClinicalFlow(doctorAssessment.getClinicalFlow());
		b.setClinicalName(skillsAssessment.getClinicalName());
		b.setDoctorFlow(doctorAssessment.getDoctorFlow());
		b.setDoctorName(docUser.getUserName());
		b.setExamTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
		b.setRoomRecordFlow(examRoomFlow);
		b.setRoomFlow(room.getRoomFlow());
		b.setRoomName(room.getRoomName());
		if(StringUtil.isBlank(b.getStatusId())) {
			b.setStatusId(ScanDocStatusEnum.StayAssessment.getId());
			b.setStatusName(ScanDocStatusEnum.StayAssessment.getName());
		}
		b.setPartnerFlow(userFlow);
		b.setPartnerName(user.getUserName());
		b.setCodeInfo(doctorAssessment.getCodeInfo());//保存学员二维码信息
		int num = oscaAppBiz.editTeaScanDoc(b, user);
		model.addAttribute("showSubmit", "N");
		if(count<=stations.size()&&count>0){
			model.addAttribute("showSubmit","Y");
		}
		model.addAttribute("stationMap", stationMap);
		//获取访问路径前缀
		String uploadBaseUrl = oscaAppBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl",uploadBaseUrl);
		model.addAttribute("docUser",docUser);
		if(docUser==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员用户信息不存在");
			return "res/osca/qrcode";
		}
		model.addAttribute("docUser",docUser);
		return "res/osca/qrCode";
	}


	@RequestMapping(value={"/assessRefresh"},method={RequestMethod.POST})
	public String assessRefresh(String userFlow,
						 String roleId,
						 String clinicalFlow,
						 String recordFlow,
						 String doctorFlow,
						 HttpServletRequest request,
						 Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/osca/assessRefresh";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色为空");
			return "res/osca/assessRefresh";
		}
		if(StringUtil.isBlank(clinicalFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核信息标识符为空");
			return "res/osca/assessRefresh";
		}
		if(StringUtil.isBlank(recordFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "预约信息标识符为空");
			return "res/osca/assessRefresh";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/osca/assessRefresh";
		}
		if(!"ExamTea".equals(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色与实际不符");
			return "res/osca/assessRefresh";
		}
		SysUser tea=oscaAppBiz.readSysUser(userFlow);
		Map<String,String> paramMap = new HashMap<String,String>();

		paramMap.put("clinicalFlow",clinicalFlow);
		paramMap.put("recordFlow",recordFlow);
		paramMap.put("doctorFlow",doctorFlow);
		paramMap.put("userFlow",userFlow);
		paramMap.put("roleId",roleId);
		model.addAttribute("paramMap",paramMap);
			//考核信息
			OscaSkillsAssessment skillsAssessment=oscaAppBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
			if(skillsAssessment==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "考核信息不存在");
				return "res/osca/assessRefresh";
			}
			model.addAttribute("skillsAssessment",skillsAssessment);
			//学员预约信息
			OscaDoctorAssessment doctorAssessment=oscaAppBiz.getOscaDoctorAssessmentByFlow(recordFlow);
			if(doctorAssessment==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "学员预约信息不存在");
				return "res/osca/assessRefresh";
			}
			model.addAttribute("doctorAssessment",doctorAssessment);
			String sigin=doctorAssessment.getSiginStatusId();
			if(StringUtil.isBlank(sigin)|| SignStatusEnum.NoSignIn.getId().equals(sigin)){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "该考生尚未考核签到，无法进行考核，请学员联系管理员签到");
				return "res/osca/assessRefresh";
			}
			//学员信息
			SysUser docUser=oscaAppBiz.readSysUser(doctorFlow);
			//获取访问路径前缀
			String uploadBaseUrl = oscaAppBiz.getCfgCode("upload_base_url");
			model.addAttribute("uploadBaseUrl",uploadBaseUrl);
			model.addAttribute("docUser",docUser);
			if(docUser==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "学员用户信息不存在");
				return "res/osca/assessRefresh";
			}
			model.addAttribute("docUser",docUser);
			ResDoctor doctor=oscaAppBiz.readResDoctor(doctorFlow);
			if(doctor==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "学员医师信息不存在");
				return "res/osca/assessRefresh";
			}
			model.addAttribute("doctor",doctor);
			if(StringUtil.isBlank(doctorAssessment.getExamStartTime())||StringUtil.isBlank(doctorAssessment.getExamEndTime()))
			{
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "管理员未维护考核时间");
				return "res/osca/assessRefresh";
			}

			//先获取考官是否已经扫过学员的二维码了
			OscaTeaScanDoc b = oscaAppBiz.getOscaTeaScanDoc(paramMap);
			if(b==null||ScanDocStatusEnum.StayAssessment.getId().equals(b.getStatusId())) {
				//当前时间
//				String nowTime = DateUtil.transDate(DateUtil.getCurrDateTime(), "yyyy-MM-dd HH:mm");
//				if (nowTime.compareTo(doctorAssessment.getExamStartTime()) < 0) {
//					model.addAttribute("resultId", "3011101");
//					model.addAttribute("resultType", "考核还未开始");
//					return "res/osca/assessRefresh";
//				}
//				if (nowTime.compareTo(doctorAssessment.getExamEndTime()) > 0) {
//					model.addAttribute("resultId", "3011101");
//					model.addAttribute("resultType", "考核已结束");
//					return "res/osca/assessRefresh";
//				}
			}
			//查询当前考官可以考核学员哪些站点
			List<OscaSubjectStation> stations=oscaAppBiz.getTeaDocStation(paramMap);
			//换考官后 再次查看学员
			if(stations==null||stations.size()<=0)
			{
				stations=oscaAppBiz.getTeaSubDocStation(paramMap);
			}
			if(stations==null||stations.size()<=0){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "该考生不在您负责的考核范围内，无法查看该考生信息哦，换一个考生扫一扫吧~");
				return "res/osca/assessRefresh";
			}
			model.addAttribute("stations",stations);
			Map<String,Object> stationMap=new HashMap<>();
			int count=0;
			for(int i=0;i<stations.size();i++)
			{
				OscaSubjectStation s=stations.get(i);
				Map<String,Object> bean=new HashMap<>();
				//查询当前站点考官可以考核学员的考场
				Map<String,String> param=new HashMap<>();
				param.put("userFlow",userFlow);
				param.put("doctorFlow",doctorFlow);
				param.put("stationFlow",s.getStationFlow());
				param.put("clinicalFlow",clinicalFlow);
				//该站点考官可以考核的考场
				List<OscaSkillRoomTea> roomTeas=oscaAppBiz.getTeaRooms(param);
				OscaSkillRoomTea roomTea=null;
				if(roomTeas!=null&&roomTeas.size()>0){
					roomTea=roomTeas.get(0);
				}
				String roomRecordFlow="";
				if(roomTea!=null)
				{
					roomRecordFlow=roomTea.getRoomRecordFlow();
					bean.put("roomRecordFlow",roomRecordFlow);
					bean.put("roomFlow",roomTea.getRoomFlow());
					bean.put("roomName",roomTea.getRoomName());
				}
				bean.put("examStatusId",ExamStatusEnum.StayAssessment.getId());
				bean.put("examStatusName",ExamStatusEnum.StayAssessment.getName());
				//当前站点信息学员排队的考场
				OscaSkillRoomDoc docStation=oscaAppBiz.getOscaSkillRoomDocByDoc(param);
				if(docStation!=null){
					roomRecordFlow=docStation.getRoomRecordFlow();
					if(!roomRecordFlow.equals("Unknown")) {
						bean.put("roomRecordFlow", roomRecordFlow);
						bean.put("roomFlow", docStation.getRoomFlow());
						bean.put("roomName", docStation.getRoomName());
					}
					bean.put("examStatusId",docStation.getExamStatusId());
					bean.put("examStatusName",docStation.getExamStatusName());
				}
				String examScoreStatusId="";
				//当前站点考官是否给学员打过分
				List<OscaSkillDocScore> scores=oscaAppBiz.getDocScoreByParam(param);
				param.put("roomRecordFlow", roomRecordFlow);
				if(scores!=null&&scores.size()>0){
					bean.put("scores",scores);
					boolean isNotSubmit=false;
					int submitCount=0;
					int saveCount=0;
					for(OscaSkillDocScore score:scores) {
						if (!score.getStatusId().equals(ScoreStatusEnum.Submit.getId())) {
							isNotSubmit=true;
						}
						if (ScoreStatusEnum.Submit.getId().equals(score.getStatusId())) {
							submitCount++;
						}
						if (ScoreStatusEnum.Save.getId().equals(score.getStatusId())) {
							saveCount++;
						}
					}
					//当前考站状态
					if(submitCount==0&&saveCount==0)
					{
						examScoreStatusId="";
					}else if(submitCount>0&&submitCount==scores.size())
					{
						examScoreStatusId="Submit";
					}else if(saveCount>0)
					{
						examScoreStatusId="Save";
					}
					if(isNotSubmit)
					{
						count++;
					}
				}
				bean.put("examScoreStatusId",examScoreStatusId);
				stationMap.put(s.getStationFlow(),bean);
				//查询当前站点下，本机构是否配置了试卷
				List<PubFile> files=oscaAppBiz.findStationFiles(s.getStationFlow(),tea.getOrgFlow());
				if(files!=null&&files.size()>0) {
					stationMap.put(s.getStationFlow() + "ExamFiles","Y" );
				}else{
					stationMap.put(s.getStationFlow() + "ExamFiles","N" );
				}
			}
			model.addAttribute("showSubmit","N");
			if(count<=stations.size()&&count>0){
				model.addAttribute("showSubmit","Y");
			}
			model.addAttribute("stationMap",stationMap);
			return "res/osca/assessRefresh";
	}


	@RequestMapping(value={"/showStationFiles"},method={RequestMethod.POST})
	public String showStationFiles(String userFlow,
						 String stationFlow,
						 HttpServletRequest request,
						 Model model)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/osca/showStationFiles";
		}
		if(StringUtil.isBlank(stationFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "站点标识符为空");
			return "res/osca/showStationFiles";
		}
		SysUser tea=oscaAppBiz.readSysUser(userFlow);
		//站点信息
		OscaSubjectStation station=oscaAppBiz.getOscaSubjectStationByFlow(stationFlow);
		if(station==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "站点信息不存在");
			return "res/osca/showStationFiles";
		}
		model.addAttribute("station",station);
		List<PubFile> files=oscaAppBiz.findStationFiles(stationFlow,tea.getOrgFlow());
		model.addAttribute("files",files);
		if(files==null||files.isEmpty())
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "站点暂无试卷信息！");
			return "res/osca/showStationFiles";
		}

		return "res/osca/showStationFiles";
	}
	@RequestMapping(value={"/showClinicalStationFiles"},method={RequestMethod.POST})
	public String showClinicalStationFiles(String userFlow,
						 String clinicalFlow,
						 String stationFlow,
						 HttpServletRequest request,
						 Model model)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/osca/showStationFiles";
		}
		if(StringUtil.isBlank(clinicalFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核方案标识符为空");
			return "res/osca/showStationFiles";
		}
		if(StringUtil.isBlank(stationFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "站点标识符为空");
			return "res/osca/showStationFiles";
		}
		SysUser tea=oscaAppBiz.readSysUser(userFlow);
		//站点信息
		OscaSkillsAssessment skillsAssessment=oscaAppBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
		if(skillsAssessment==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核信息不存在");
			return "res/osca/showStationFiles";
		}
		//站点信息
		OscaSubjectStation station=oscaAppBiz.getOscaSubjectStationByFlow(stationFlow);
		if(station==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "站点信息不存在");
			return "res/osca/showStationFiles";
		}
		model.addAttribute("station",station);//考站试卷
		List<PubFile> files = oscaAppBiz.findClinicalStationFiles(stationFlow,clinicalFlow,tea.getOrgFlow());
		model.addAttribute("files",files);
		if(files==null||files.isEmpty())
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "站点暂无试卷信息！");
			return "res/osca/showStationFiles";
		}

		return "res/osca/showStationFiles";
	}
	@RequestMapping(value={"/showClinicalStationRooms"},method={RequestMethod.POST})
	public String showClinicalStationRooms(String userFlow,
						 String clinicalFlow,
						 String stationFlow,
						 HttpServletRequest request,
						 Model model)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/osca/showClinicalStationRooms";
		}
		if(StringUtil.isBlank(clinicalFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核方案标识符为空");
			return "res/osca/showClinicalStationRooms";
		}
		if(StringUtil.isBlank(stationFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "站点标识符为空");
			return "res/osca/showClinicalStationRooms";
		}
		SysUser tea=oscaAppBiz.readSysUser(userFlow);
		//站点信息
		OscaSkillsAssessment skillsAssessment=oscaAppBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
		if(skillsAssessment==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核信息不存在");
			return "res/osca/showClinicalStationRooms";
		}
		//站点信息
		OscaSubjectStation station=oscaAppBiz.getOscaSubjectStationByFlow(stationFlow);
		if(station==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "站点信息不存在");
			return "res/osca/showClinicalStationRooms";
		}
		model.addAttribute("station",station);
		//考站房间
		List<OscaSkillRoom> rooms = oscaAppBiz.findClinicalStationRooms(stationFlow,clinicalFlow,tea.getOrgFlow());
		model.addAttribute("rooms",rooms);
		if(rooms==null||rooms.isEmpty())
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "站点暂无房间信息！");
			return "res/osca/showClinicalStationRooms";
		}

		return "res/osca/showClinicalStationRooms";
	}
	@RequestMapping(value={"/setClinicalRoomFile"},method={RequestMethod.POST})
	public String setClinicalRoomFile(String userFlow,
						 String fileFlow,
						 String roomFlow,
						 HttpServletRequest request,
						 Model model)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/osca/success";
		}
		if(StringUtil.isBlank(fileFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "试卷标识符为空");
			return "res/osca/success";
		}
		if(StringUtil.isBlank(roomFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "房间标识符为空");
			return "res/osca/success";
		}
		SysUser tea=oscaAppBiz.readSysUser(userFlow);
		PubFile file=oscaAppBiz.readFile(fileFlow);
		if(file==null)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "试卷信息不存在");
			return "res/osca/success";
		}
		//房间推送信息
		OscaRoomFile roomFile=oscaAppBiz.getRoomFile(roomFlow,tea.getOrgFlow());
		if(roomFile==null)
			roomFile=new OscaRoomFile();
		roomFile.setFileFlow(fileFlow);
		roomFile.setRoomFlow(roomFlow);
		roomFile.setOrgFlow(tea.getOrgFlow());
		roomFile.setOrgName(tea.getOrgName());
		roomFile.setOrdinal(1);
		int c=oscaAppBiz.saveRoomFile(roomFile,tea);
		if(c==0)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "推送失败！");
			return "res/osca/success";
		}
		return "res/osca/success";
	}

	@RequestMapping(value = {"/checkFile"}, method = {RequestMethod.POST})
	public String checkFile(String userFlow, String fileFlow,  HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "01000301");
			model.addAttribute("resultType", "用户流水号为空");
			return "res/osca/success";
		}

		if (StringUtil.isEmpty(fileFlow)) {
			model.addAttribute("resultId", "01000302");
			model.addAttribute("resultType", "文件流水号为空");
			return "res/osca/success";
		}
		PubFile file=fileBiz.readFile(fileFlow);
		if (file == null) {
			model.addAttribute("resultId", "01000304");
			model.addAttribute("resultType", "试卷不存在，请联系管理员！");
			return "res/osca/success";
		}
		String baseDirs = oscaAppBiz.getCfgCode("upload_base_dir");
		if (StringUtil.isBlank(baseDirs)) {
			model.addAttribute("resultId", "01000304");
			model.addAttribute("resultType", "文件保存路径未配置，请联系管理员！");
			return "res/osca/success";
		}
		String filePath = baseDirs + File.separator +file.getFilePath();
		File f = new File(filePath);
		if (!f.exists()) {
			final String ftpFileName=filePath.substring(filePath.lastIndexOf(File.separator)+1);
			final String ftpDir=filePath.substring(baseDirs.length()+1,filePath.lastIndexOf(File.separator));
			FtpHelperUtil ftpHelperUtil=new FtpHelperUtil();
			ftpHelperUtil.downloadFile(filePath,ftpDir,ftpFileName);

			model.addAttribute("resultId", "01000304");
			model.addAttribute("resultType", "试卷文件不存在，请联系稍后再试！");
			return "res/osca/success";
		}
		return "res/osca/success";
	}

	@RequestMapping(value = {"/downFile"}, method = {RequestMethod.GET})
	public  void downFile(String userFlow, String fileFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

		PubFile file=fileBiz.readFile(fileFlow);
		String baseDirs = oscaAppBiz.getCfgCode("upload_base_dir");
		String filePath = baseDirs + File.separator +file.getFilePath();
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
		String fileName=file.getFileName();
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

	@RequestMapping(value={"/exam"},method={RequestMethod.POST})
	public String exam(String userFlow,
						 String doctorFlow,
						 String clinicalFlow,
						 String stationFlow,
						 String roomRecordFlow,
						 HttpServletRequest request,
						 Model model)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/osca/exam";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/osca/exam";
		}
		if(StringUtil.isBlank(clinicalFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核信息标识符为空");
			return "res/osca/exam";
		}
		if(StringUtil.isBlank(stationFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "站点标识符为空");
			return "res/osca/exam";
		}
		if(StringUtil.isBlank(roomRecordFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考场标识符为空");
			return "res/osca/exam";
		}
		Map<String, String> param=new HashMap<>();
		param.put("userFlow", userFlow);
		param.put("doctorFlow", doctorFlow);
		param.put("clinicalFlow", clinicalFlow);
		param.put("stationFlow", stationFlow);
		SysUser tea=oscaAppBiz.readSysUser(userFlow);
		//考核信息
		OscaSkillsAssessment skillsAssessment=oscaAppBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
		if(skillsAssessment==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核信息不存在");
			return "res/osca/exam";
		}
		model.addAttribute("skillsAssessment",skillsAssessment);
		//站点信息
		OscaSubjectStation station=oscaAppBiz.getOscaSubjectStationByFlow(stationFlow);
		if(station==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "站点信息不存在");
			return "res/osca/exam";
		}
		model.addAttribute("station",station);
		//学员信息
		SysUser docUser=oscaAppBiz.readSysUser(doctorFlow);
		//获取访问路径前缀
		String uploadBaseUrl = oscaAppBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl",uploadBaseUrl);
		model.addAttribute("docUser",docUser);
		if(docUser==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员用户信息不存在");
			return "res/osca/exam";
		}

		OscaSkillRoom room=oscaAppBiz.getOscaskillRoomByFlow(roomRecordFlow);
		//查看当前站点学员是否选择了排队
		OscaSkillRoomDoc docRoom=oscaAppBiz.getOscaSkillRoomDocByDoc(param);
		if(docRoom==null)
			docRoom=new OscaSkillRoomDoc();
		docRoom.setDoctorFlow(doctorFlow);
		docRoom.setDoctorName(docUser.getUserName());
		docRoom.setRoomRecordFlow(room.getRecordFlow());
		docRoom.setRoomFlow(room.getRoomFlow());
		docRoom.setRoomName(room.getRoomName());
		docRoom.setClinicalFlow(clinicalFlow);
		docRoom.setClinicalName(skillsAssessment.getClinicalName());
		docRoom.setStationFlow(stationFlow);
		docRoom.setStationName(station.getStationName());
		if(StringUtil.isBlank(docRoom.getExamStatusId())||ExamStatusEnum.Waiting.getId().equals(docRoom.getExamStatusId())) {
			docRoom.setExamStatusId(ExamStatusEnum.AssessIng.getId());
			docRoom.setExamStatusName(ExamStatusEnum.AssessIng.getName());
		}
		docRoom.setWaitingTime(DateUtil.getCurrentTime());
		oscaAppBiz.editOscaSkillRoomDoc(docRoom,tea);
		model.addAttribute("docRoom",docRoom);

		OscaSkillDocTeaScore teaScore=oscaAppBiz.getOscaSkillRoomTeaScoreByDoc(param);
		if(teaScore==null)
			teaScore=new OscaSkillDocTeaScore();
		teaScore.setDoctorFlow(doctorFlow);
		teaScore.setDoctorName(docUser.getUserName());
		teaScore.setRoomRecordFlow(room.getRecordFlow());
		teaScore.setRoomFlow(room.getRoomFlow());
		teaScore.setRoomName(room.getRoomName());
		teaScore.setClinicalFlow(clinicalFlow);
		teaScore.setClinicalName(skillsAssessment.getClinicalName());
		teaScore.setStationFlow(stationFlow);
		teaScore.setStationName(station.getStationName());
		teaScore.setPartnerFlow(userFlow);
		teaScore.setPartnerName(tea.getUserName());
		oscaAppBiz.editOscaSkillRoomTeaScore(teaScore,tea);

		//当前站点考官是否给学员打过分
		List<OscaSkillDocScore> scores=oscaAppBiz.getDocScoreByParam(param);
		param.put("roomRecordFlow", roomRecordFlow);
		//考核的表单分数
		Map<String,OscaSkillDocScore> scoreMap=new HashMap<>();

		String examScoreStatusId="";
		int submitCount=0;
		int saveCount=0;
		if(scores!=null&&scores.size()>0) {
			for (OscaSkillDocScore score : scores) {
				if (StringUtil.isBlank(score.getFromFlow())) {
					scoreMap.put("NotHavaFrom", score);
					String baseDir = oscaAppBiz.getCfgCode("upload_base_url");
					if (StringUtil.isNotBlank(baseDir) && score != null && StringUtil.isNotBlank(score.getSiginImage())) {
						model.addAttribute("siginImage", baseDir + score.getSiginImage());
					}
					model.addAttribute("NotHavaFromScore", score);
				} else {
					scoreMap.put(score.getFromFlow(), score);
				}
				if (ScoreStatusEnum.Submit.getId().equals(score.getStatusId())) {
					submitCount++;
				}
				if (ScoreStatusEnum.Save.getId().equals(score.getStatusId())) {
					saveCount++;
				}
			}
		}
		//当前考站状态
		if(submitCount==0&&saveCount==0)
		{
			examScoreStatusId="";
		}else if(submitCount>0&&submitCount==scores.size())
		{
			examScoreStatusId="Submit";
		}else if(saveCount>0)
		{
			examScoreStatusId="Save";
		}
		model.addAttribute("scores",scores);

		//表单列表
		List<OscaSubjectStationFrom> froms=new ArrayList<>();
		//选考表单内容
		List<OscaSubjectStationFrom> notRequiredFroms=new ArrayList<>();
		//必考表单内容
		List<OscaSubjectStationFrom> requiredFroms=new ArrayList<>();

		//查询当前站点下，已经保存过的表单
		List<OscaSubjectStationFrom> froms2=oscaAppBiz.getStationExamFroms(param);
		if(froms2!=null&&froms2.size()>0)
		{
			for(OscaSubjectStationFrom from:froms2)
			{
				if(GlobalConstant.FLAG_Y.equals(from.getIsRequired()))
				{
					requiredFroms.add(from);
				}
				if(GlobalConstant.FLAG_N.equals(from.getIsRequired()))
				{
					notRequiredFroms.add(from);
				}
				if(StringUtil.isNotBlank(from.getFromFlow()))
					froms.add(from);
			}
		}
		model.addAttribute("examScoreStatusId",examScoreStatusId);
		if(!"Submit".equals(examScoreStatusId)) {
			//查询当前站点下，本机构是否配置了表单
			List<OscaSubjectStationFrom> froms1=oscaAppBiz.getFromsByOrgFlow(stationFlow,tea.getOrgFlow());
			//如果没有，则取当前站点省厅配置的表单。查询当前站点是否有表单
			if(froms1==null||froms1.size()==0) {
				froms1=oscaAppBiz.getFromsByStationFlow(stationFlow);
			}
			if(froms1!=null&&froms1.size()>0)
			{
				for(OscaSubjectStationFrom from:froms1)
				{
					if(!scoreMap.containsKey(from.getFromFlow()))
					{
						from.setRecordFlow("NotExam");
						froms.add(from);
						if(GlobalConstant.FLAG_Y.equals(from.getIsRequired()))
						{
							requiredFroms.add(from);
						}
						if(GlobalConstant.FLAG_N.equals(from.getIsRequired()))
						{
							notRequiredFroms.add(from);
						}
					}
				}
			}
		}
		model.addAttribute("scoreMap",scoreMap);
		model.addAttribute("froms",froms);
		model.addAttribute("notRequiredFroms",notRequiredFroms);
		model.addAttribute("requiredFroms",requiredFroms);

		if(froms!=null&&froms.size()>0){
			model.addAttribute("haveFrom","Y");
			if(froms.size()==1){
				model.addAttribute("haveOne","Y");
				OscaSubjectStationFrom b=froms.get(0);
				OscaFrom from=null;
				if("NotExam".equals(b.getRecordFlow()))//未考核的话取原来的表单内容
				{
					//表单内容
					from=oscaAppBiz.getFromByFlow(b.getFromFlow());
				}else{
					from=oscaAppBiz.getFromByScoreFlow(b.getRecordFlow());
				}
				if(from==null){
					model.addAttribute("resultId", "3011101");
					model.addAttribute("resultType", "表单信息不存在");
					return "res/osca/exam";
				}
				model.addAttribute("from",from);
				model.addAttribute("b",b);
				param.put("fromFlow", from.getFromFlow());

				OscaSkillDocScore score=scoreMap.get(from.getFromFlow());
				if(score!=null) {

					String baseDir=oscaAppBiz.getCfgCode("upload_base_url");
					if(StringUtil.isNotBlank(baseDir)&&StringUtil.isNotBlank(score.getSiginImage()))
					{
						model.addAttribute("siginImage",baseDir+score.getSiginImage());
					}
					model.addAttribute("NotHavaFromScore", score);
				}
				//固定表单
				if(from.getFromTypeId().equals("1")){
					String url=from.getFromUrl();
					if(StringUtil.isBlank(url)){
						model.addAttribute("resultId", "3011101");
						model.addAttribute("resultType", "表单信息不存在");
						return "res/osca/exam";
					}
					//已经保存的分数
					if(score!=null) {
						Map<String, Object> map = oscaAppBiz.parseGuDingFromXml(score.getFromContent());
						model.addAttribute("valueMap", map);
						model.addAttribute("keySet", map.keySet());
					}
					url=url.replace("\\","/");
					String jspType=url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));
					model.addAttribute("fromType","GD");
					model.addAttribute("jspType",jspType);
				}else{
					//非固定表单
					String content=from.getRromContent();
					model.addAttribute("fromType","FGD");
					List<FromTitle> titleList=oscaAppBiz.parseFromXmlForList(content);
					model.addAttribute("titleList",titleList);
					if(score!=null) {
						//已经保存的值
						Map<String, OscaSkillScoreDeatil> valueMap = new HashMap<>();
						List<OscaSkillScoreDeatil> list = oscaAppBiz.getScoreDeatilsByScoreFlow(score.getScoreFlow());
						if (list != null && list.size() > 0) {
							for (OscaSkillScoreDeatil d : list) {
								valueMap.put(d.getScoreKey(), d);
							}
						}
						model.addAttribute("valueMap", valueMap);
					}
				}
			}
		}else {
			//没有配置表单直接查询成绩
			model.addAttribute("haveFrom","N");
			model.addAttribute("haveOne","N");
			model.addAttribute("stationScore",station.getStationScore());
		}
		model.addAttribute("paramMap",param);
		return "res/osca/exam";
	}
	@RequestMapping(value={"/saveSiginImage"},method = {RequestMethod.POST})
	public  String saveSiginImage(String userFlow,String siginImage,Model model)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/osca/saveSiginImage";
		}
		if(StringUtil.isBlank(siginImage)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "签名信息为空");
			return "res/osca/saveSiginImage";
		}
		SysUser tea=oscaAppBiz.readSysUser(userFlow);
		if(tea==null)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "个人信息不存在！");
			return "res/osca/saveSiginImage";
		}
		String baseDir=oscaAppBiz.getCfgCode("upload_base_dir");
		if(StringUtil.isBlank(baseDir))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请联系管理员，设置上传签名图片路径！");
			return "res/osca/saveSiginImage";
		}

		PubFile file=fileBiz.searchByProductFlowAndType(tea.getUserFlow(),"ExamTeaSigin");
		if(file==null) file=new PubFile();
		String dateString = DateUtil.getCurrDate2();
		String imageUrl=file.getFilePath();
		String siginImageName=PkUtil.getUUID()+".png";
		if(StringUtil.isBlank(imageUrl))
		{
			imageUrl="/examTeaSigin/"+dateString+"/"+siginImageName;
		}
		file.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		file.setFileName(siginImageName);
		file.setFilePath(imageUrl);
		file.setProductFlow(tea.getUserFlow());
		file.setProductType("ExamTeaSigin");
		GenerateImage(siginImage,baseDir+imageUrl);


		FtpHelperUtil ftpHelperUtil=new FtpHelperUtil();
		String localFilePath=baseDir+imageUrl;
		String ftpDir= "/examTeaSigin/"+dateString;
		ftpHelperUtil.uploadFile(localFilePath,ftpDir,siginImageName);

		fileBiz.editFile(file);
		//获取访问路径前缀
		String uploadBaseUrl = oscaAppBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl",uploadBaseUrl);
		model.addAttribute("file",file);
		return "res/osca/saveSiginImage";
	}
	//base64字符串转化成图片
	public static boolean GenerateImage(String imgStr,String savePath) {   //对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) //图像数据为空
			return false;

		String newDir = savePath.substring(0,savePath.lastIndexOf("/"));
		File fileDir = new File(newDir);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			//Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {//调整异常数据
					b[i] += 256;
				}
			}
			//生成jpeg图片//新生成的图片
			File imageFile = new File(savePath);
			OutputStream out = new FileOutputStream(imageFile);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	//站点没有配置表单的考核成绩保存
	@RequestMapping(value={"/noFromSaveScore"},method = {RequestMethod.POST})
	public  String noFromSaveScore(String userFlow,String scoreFlow,String doctorFlow,
								   String roomRecordFlow,String clinicalFlow,String stationFlow,String siginImage,
								   String examScore,Model model)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/osca/success";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/osca/success";
		}
		if(StringUtil.isBlank(clinicalFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核信息标识符为空");
			return "res/osca/success";
		}
		if(StringUtil.isBlank(stationFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "站点标识符为空");
			return "res/osca/success";
		}
		if(StringUtil.isBlank(roomRecordFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考场标识符为空");
			return "res/osca/success";
		}
		if(StringUtil.isBlank(examScore)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核分数为空");
			return "res/osca/success";
		}
		if(StringUtil.isBlank(siginImage)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "签名信息为空");
			return "res/osca/success";
		}
		SysUser tea=oscaAppBiz.readSysUser(userFlow);
		//考核信息
		OscaSkillsAssessment skillsAssessment=oscaAppBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
		if(skillsAssessment==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核信息不存在");
			return "res/osca/success";
		}
		model.addAttribute("skillsAssessment",skillsAssessment);
		//站点信息
		OscaSubjectStation station=oscaAppBiz.getOscaSubjectStationByFlow(stationFlow);
		if(station==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "站点信息不存在");
			return "res/osca/success";
		}
		model.addAttribute("station",station);
		//学员信息
		SysUser docUser=oscaAppBiz.readSysUser(doctorFlow);
		if(docUser==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员用户信息不存在");
			return "res/osca/success";
		}
		OscaSkillRoom room=oscaAppBiz.getOscaskillRoomByFlow(roomRecordFlow);
		OscaSkillDocScore score=oscaAppBiz.getOscaSkillDocScoreByFlow(scoreFlow);
		Map<String, String> param=new HashMap<>();
		param.put("userFlow", userFlow);
		param.put("doctorFlow", doctorFlow);
		param.put("clinicalFlow", clinicalFlow);
		param.put("stationFlow", stationFlow);
		//param.put("roomRecordFlow", roomRecordFlow);
		if(score==null) {
			score = oscaAppBiz.getNoFromScoreByParam(param);
		}
		if(score==null)
			score=new OscaSkillDocScore();
		if(ScoreStatusEnum.Submit.getId().equals(score.getStatusId())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员考核成绩已提交，不得再次修改！");
			return "res/osca/success";
		}
		String baseDir=oscaAppBiz.getCfgCode("upload_base_dir");
		if(StringUtil.isBlank(baseDir))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请联系管理员，设置上传签名图片路径！");
			return "res/osca/success";
		}
		String dateString = DateUtil.getCurrDate2();
		String imageUrl=score.getSiginImage();
		String siginImageName=PkUtil.getUUID()+".png";
		if(StringUtil.isBlank(imageUrl))
		{
			imageUrl="/siginImage/"+dateString+"/"+siginImageName;
		}
		score.setSiginImage(imageUrl);
		GenerateImage(siginImage,baseDir+imageUrl);


		FtpHelperUtil ftpHelperUtil=new FtpHelperUtil();
		String localFilePath=baseDir+imageUrl;
		String ftpDir= "/siginImage/"+dateString;
		ftpHelperUtil.uploadFile(localFilePath,ftpDir,siginImageName);


		score.setDoctorFlow(doctorFlow);
		score.setDoctorName(docUser.getUserName());
		score.setRecordYear(DateUtil.getYear());
		score.setRoomRecordFlow(room.getRecordFlow());
		score.setRoomFlow(room.getRoomFlow());
		score.setRoomName(room.getRoomName());
		score.setClinicalFlow(clinicalFlow);
		score.setClinicalName(skillsAssessment.getClinicalName());
		score.setStationFlow(stationFlow);
		score.setStationName(station.getStationName());
		score.setStatusId(ScoreStatusEnum.Save.getId());
		score.setStatusName(ScoreStatusEnum.Save.getName());
		score.setIsHaveFrom("N");
		score.setFromTypeId("N");
		score.setFromUrl("NotHavaFrom");
		score.setPartnerFlow(userFlow);
		score.setPartnerName(tea.getUserName());
		double score1=Double.valueOf(examScore);
		score.setExamScore(examScore);
		////设置当前站点学员已经考核
		OscaSkillRoomDoc docRoom=oscaAppBiz.getOscaSkillRoomDocByDoc(param);
		if(docRoom==null)
			docRoom=new OscaSkillRoomDoc();
		docRoom.setDoctorFlow(doctorFlow);
		docRoom.setDoctorName(docUser.getUserName());
		docRoom.setRoomRecordFlow(room.getRecordFlow());
		docRoom.setRoomFlow(room.getRoomFlow());
		docRoom.setRoomName(room.getRoomName());
		docRoom.setClinicalFlow(clinicalFlow);
		docRoom.setClinicalName(skillsAssessment.getClinicalName());
		docRoom.setStationFlow(stationFlow);
		docRoom.setStationName(station.getStationName());
		if(ExamStatusEnum.AssessIng.getId().equals(docRoom.getExamStatusId())||StringUtil.isBlank(docRoom.getExamStatusId())) {
			docRoom.setExamStatusId(ExamStatusEnum.Assessment.getId());
			docRoom.setExamStatusName(ExamStatusEnum.Assessment.getName());
			OscaSkillDocStation docStation=oscaAppBiz.getDocSkillStation(station.getStationFlow(),doctorFlow,clinicalFlow);
			if(docStation==null)
			{
				docStation=new OscaSkillDocStation();
			}
			docStation.setClinicalFlow(clinicalFlow);
			docStation.setClinicalName(skillsAssessment.getClinicalName());
			docStation.setStationFlow(station.getStationFlow());
			docStation.setStationName(station.getStationName());
			docStation.setDoctorFlow(doctorFlow);
			docStation.setDoctorName(docUser.getUserName());
			docStation.setExamStatusId(ExamStatusEnum.Assessment.getId());
			docStation.setExamStatusName(ExamStatusEnum.Assessment.getName());
			docStation.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			oscaAppBiz.saveDocStation(docStation,tea);
			List<OscaSkillDocStation> docStations=oscaAppBiz.getDocSkillStations(doctorFlow,clinicalFlow);
			if(docStations!=null)
			{
				String date=DateUtil.getCurrDateTime2();
				for( OscaSkillDocStation ds:docStations)
				{
					if(ExamStatusEnum.StayAssessment.getId().equals(ds.getExamStatusId()))
					{
						ds.setHoukaoTime(date);
						oscaAppBiz.saveDocStation(ds,tea);
					}
				}
			}
		}
		docRoom.setWaitingTime(DateUtil.getCurrentTime());
		oscaAppBiz.editOscaSkillRoomDoc(docRoom,tea);

		OscaSkillDocTeaScore teaScore=oscaAppBiz.getOscaSkillRoomTeaScoreByDoc(param);
		if(teaScore==null)
			teaScore=new OscaSkillDocTeaScore();
		teaScore.setDoctorFlow(doctorFlow);
		teaScore.setDoctorName(docUser.getUserName());
		teaScore.setRoomRecordFlow(room.getRecordFlow());
		teaScore.setRoomFlow(room.getRoomFlow());
		teaScore.setRoomName(room.getRoomName());
		teaScore.setClinicalFlow(clinicalFlow);
		teaScore.setClinicalName(skillsAssessment.getClinicalName());
		teaScore.setStationFlow(stationFlow);
		teaScore.setStationName(station.getStationName());
		teaScore.setPartnerFlow(userFlow);
		teaScore.setPartnerName(tea.getUserName());
		oscaAppBiz.editOscaSkillRoomTeaScore(teaScore,tea);

		int count=oscaAppBiz.editOscaSkillDocScore(score,tea);
		if(count==0){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "保存失败！");
		}else{
			oscaAppBiz.updateDocSaveStatus(doctorFlow,tea);
			oscaAppBiz.updateTeaScanDocStatus(doctorFlow,clinicalFlow,tea);
		}
		return "res/osca/success";
	}
	//站点有配置表单的考核成绩保存
	//jsonData 格式 为 {"24563abd26f3ed3daw2sdf53a2dcx270":{"examScore":"70","id":"24563abd26f3ed3daw2sdf53a2dcx270"},"24563abd26f3ed3daw2sdf53a2dcx280":{"examScore":"80","id":"24563abd26f3ed3daw2sdf53a2dcx280"},"24563abd26f3ed3daw2sdf53a2dcx290":{"examScore":"90","id":"24563abd26f3ed3daw2sdf53a2dcx290"},"24563abd26f3ed3daw2sdf53a2dcx2ds":{"examScore":"100","id":"24563abd26f3ed3daw2sdf53a2dcx2ds"}}

	@RequestMapping(value={"/fromSaveScore"},method = {RequestMethod.POST})
	public  String fromSaveScore(String userFlow, String scoreFlow, String doctorFlow,
                                 String roomRecordFlow, String clinicalFlow, String stationFlow,
                                 String jsonData, String siginImage, String isRequired,
                                 String scoreSum, String fromFlow, Model model, HttpServletRequest request)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/osca/success";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/osca/success";
		}
		if(StringUtil.isBlank(clinicalFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核信息标识符为空");
			return "res/osca/success";
		}
		if(StringUtil.isBlank(stationFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "站点标识符为空");
			return "res/osca/success";
		}
		if(StringUtil.isBlank(roomRecordFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考场标识符为空");
			return "res/osca/success";
		}
		if(StringUtil.isBlank(scoreSum)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核分数为空");
			return "res/osca/success";
		}
		if(StringUtil.isBlank(fromFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "表单为空");
			return "res/osca/success";
		}
		if(StringUtil.isBlank(isRequired)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "必选标识符为空");
			return "res/osca/success";
		}
		if(!"Y".equals(isRequired)&&!"N".equals(isRequired)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "必选标识符只能是Y或N");
			return "res/osca/success";
		}
		if(StringUtil.isBlank(siginImage)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "签名信息为空");
			return "res/osca/success";
		}
		SysUser tea=oscaAppBiz.readSysUser(userFlow);
		//表单内容
		OscaFrom from=null;
		if(StringUtil.isNotBlank(scoreFlow))//未考核的话取原来的表单内容
		{
			from=oscaAppBiz.getFromByScoreFlow(scoreFlow);
		}else{
			from=oscaAppBiz.getFromByFlow(fromFlow);
		}
		if(from==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "表单信息不存在");
			return "res/osca/success";
		}
		//考核信息
		OscaSkillsAssessment skillsAssessment=oscaAppBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
		if(skillsAssessment==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核信息不存在");
			return "res/osca/success";
		}
		model.addAttribute("skillsAssessment",skillsAssessment);
		//站点信息
		OscaSubjectStation station=oscaAppBiz.getOscaSubjectStationByFlow(stationFlow);
		if(station==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "站点信息不存在");
			return "res/osca/success";
		}
		model.addAttribute("station",station);
		//学员信息
		SysUser docUser=oscaAppBiz.readSysUser(doctorFlow);
		if(docUser==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员用户信息不存在");
			return "res/osca/success";
		}
		OscaSkillRoom room=oscaAppBiz.getOscaskillRoomByFlow(roomRecordFlow);
		OscaSkillDocScore score=oscaAppBiz.getOscaSkillDocScoreByFlow(scoreFlow);
		Map<String, String> param=new HashMap<>();
		param.put("userFlow", userFlow);
		param.put("doctorFlow", doctorFlow);
		param.put("clinicalFlow", clinicalFlow);
		param.put("stationFlow", stationFlow);
		//param.put("roomRecordFlow", roomRecordFlow);
		//先判断是否已经打分无表单的分数
		if(score==null) {
			score = oscaAppBiz.getNoFromScoreByParam(param);
		}
		//如果没有打分无表单的分数，并且是选填的话，则查询非必选的
		if("N".equals(isRequired)) {
			if (score == null) {
				score = oscaAppBiz.getNotRequiredFromScoreByParam(param);
			}
		}else{
			param.put("fromFlow", fromFlow);
			if (score == null) {
				score = oscaAppBiz.getRequiredFromScoreByParam(param);
			}
		}
		//如果前面两个都没有满足则新建
		if(score==null)
			score=new OscaSkillDocScore();
		if(ScoreStatusEnum.Submit.getId().equals(score.getStatusId())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员考核成绩已提交，不得再次修改！");
			return "res/osca/success";
		}
		String baseDir=oscaAppBiz.getCfgCode("upload_base_dir");
		if(StringUtil.isBlank(baseDir))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请联系管理员，设置上传签名图片路径！");
			return "res/osca/success";
		}
		String dateString = DateUtil.getCurrDate2();
		String imageUrl=score.getSiginImage();
		String siginImageName=PkUtil.getUUID()+".png";
		if(StringUtil.isBlank(imageUrl))
		{
			imageUrl="/siginImage/"+dateString+"/"+siginImageName;
		}
		score.setSiginImage(imageUrl);
		GenerateImage(siginImage,baseDir+imageUrl);


		FtpHelperUtil ftpHelperUtil=new FtpHelperUtil();
		String localFilePath=baseDir+imageUrl;
		String ftpDir= "/siginImage/"+dateString;
		ftpHelperUtil.uploadFile(localFilePath,ftpDir,siginImageName);

		score.setDoctorFlow(doctorFlow);
		score.setDoctorName(docUser.getUserName());
		score.setRecordYear(skillsAssessment.getClinicalYear());
		score.setRoomRecordFlow(room.getRecordFlow());
		score.setRoomFlow(room.getRoomFlow());
		score.setRoomName(room.getRoomName());
		score.setClinicalFlow(clinicalFlow);
		score.setClinicalName(skillsAssessment.getClinicalName());
		score.setStationFlow(stationFlow);
		score.setStationName(station.getStationName());
		score.setStatusId(ScoreStatusEnum.Save.getId());
		score.setStatusName(ScoreStatusEnum.Save.getName());
		score.setIsHaveFrom("Y");
		score.setIsRequired(isRequired);
		score.setFromFlow(from.getFromFlow());
		score.setFromName(from.getFromName());
		score.setFromScore(from.getFromScore());
		Map<String,Map<String,Object>> dateMap = null;
		if("1".equals(from.getFromTypeId()))
		{
			score.setFromTypeId(from.getFromTypeId());
			score.setFromUrl(from.getFromUrl());
			String scoreXml=oscaAppBiz.getScoreXml(request);
			score.setFromContent(scoreXml);//固定表单的话就成绩
		}else{
			score.setFromTypeId(from.getFromTypeId());
			score.setFromUrl("自定义表单");

			if(StringUtil.isBlank(jsonData)){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "表单成绩为空！");
				return "res/osca/success";
			}

			//自定义表单，需要保存相应项目分数
			//转换json字符串为map对象
			try {
				dateMap = (Map<String, Map<String, Object>>) JSON.parse(jsonData);
			}catch (Exception e){
				logger.error("表单成绩提交格式不正确！jsonData = " + jsonData,e);
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "表单成绩提交格式不正确！");
				return "res/osca/success";
			}
			//创建xmldom对象和根节点
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("FGD_FROM");
			Document document= null;
			try {
				document = DocumentHelper.parseText(from.getRromContent());
				if(StringUtil.isNotBlank(scoreFlow))
				{
					Element fromCfg=document.getRootElement().element("fromCfg");
					if(fromCfg!=null)
					{
						root.add((Element) fromCfg.clone());
					}
				}else{
					Element baseRoot=document.getRootElement();
					root.add(baseRoot);
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			Element fromScore=root.addElement("fromScore");
			if(dateMap!=null)
			{
				for(String key:dateMap.keySet())
				{
					Map<String, Object> m=dateMap.get(key);
					Element item=fromScore.addElement("scoreItem");
					item.addAttribute("id",key);
					item.setText((String) m.get("examScore"));
				}
			}
			score.setFromContent(root.asXML());//自定义表单需要保存配置
		}
		score.setPartnerFlow(userFlow);
		score.setPartnerName(tea.getUserName());
		double score1=Double.valueOf(scoreSum);
		score.setExamScore(scoreSum);
		////设置当前站点学员已经考核
		OscaSkillRoomDoc docRoom=oscaAppBiz.getOscaSkillRoomDocByDoc(param);
		if(docRoom==null)
			docRoom=new OscaSkillRoomDoc();
		docRoom.setDoctorFlow(doctorFlow);
		docRoom.setDoctorName(docUser.getUserName());
		docRoom.setRoomRecordFlow(room.getRecordFlow());
		docRoom.setRoomFlow(room.getRoomFlow());
		docRoom.setRoomName(room.getRoomName());
		docRoom.setClinicalFlow(clinicalFlow);
		docRoom.setClinicalName(skillsAssessment.getClinicalName());
		docRoom.setStationFlow(stationFlow);
		docRoom.setStationName(station.getStationName());
		if(ExamStatusEnum.AssessIng.getId().equals(docRoom.getExamStatusId())||StringUtil.isBlank(docRoom.getExamStatusId())) {
			docRoom.setExamStatusId(ExamStatusEnum.Assessment.getId());
			docRoom.setExamStatusName(ExamStatusEnum.Assessment.getName());
			OscaSkillDocStation docStation=oscaAppBiz.getDocSkillStation(station.getStationFlow(),doctorFlow,clinicalFlow);
			if(docStation==null)
			{
				docStation=new OscaSkillDocStation();
			}
			docStation.setClinicalFlow(clinicalFlow);
			docStation.setClinicalName(skillsAssessment.getClinicalName());
			docStation.setStationFlow(station.getStationFlow());
			docStation.setStationName(station.getStationName());
			docStation.setDoctorFlow(doctorFlow);
			docStation.setDoctorName(docUser.getUserName());
			docStation.setExamStatusId(ExamStatusEnum.Assessment.getId());
			docStation.setExamStatusName(ExamStatusEnum.Assessment.getName());
			docStation.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			oscaAppBiz.saveDocStation(docStation,tea);
			List<OscaSkillDocStation> docStations=oscaAppBiz.getDocSkillStations(doctorFlow,clinicalFlow);
			if(docStations!=null)
			{
				String date=DateUtil.getCurrDateTime2();
				for( OscaSkillDocStation ds:docStations)
				{
					if(ExamStatusEnum.StayAssessment.getId().equals(ds.getExamStatusId()))
					{
						ds.setHoukaoTime(date);
						oscaAppBiz.saveDocStation(ds,tea);
					}
				}
			}
		}
		docRoom.setWaitingTime(DateUtil.getCurrentTime());
		oscaAppBiz.editOscaSkillRoomDoc(docRoom,tea);

		OscaSkillDocTeaScore teaScore=oscaAppBiz.getOscaSkillRoomTeaScoreByDoc(param);
		if(teaScore==null)
			teaScore=new OscaSkillDocTeaScore();
		teaScore.setDoctorFlow(doctorFlow);
		teaScore.setDoctorName(docUser.getUserName());
		teaScore.setRoomRecordFlow(room.getRecordFlow());
		teaScore.setRoomFlow(room.getRoomFlow());
		teaScore.setRoomName(room.getRoomName());
		teaScore.setClinicalFlow(clinicalFlow);
		teaScore.setClinicalName(skillsAssessment.getClinicalName());
		teaScore.setStationFlow(stationFlow);
		teaScore.setStationName(station.getStationName());
		teaScore.setPartnerFlow(userFlow);
		teaScore.setPartnerName(tea.getUserName());
		oscaAppBiz.editOscaSkillRoomTeaScore(teaScore,tea);

		int count=oscaAppBiz.editOscaSkillDocScore(score,tea);
		if(!"1".equals(from.getFromTypeId()))
		{
			try {
				oscaAppBiz.editOscaSkillScoreDeatil(dateMap,tea,from,score);
			}catch (Exception e){
				logger.error("editOscaSkillScoreDeatil excepetion: dateMap =  " + JSON.toJSONString(dateMap) + " , tea = " + JSON.toJSONString(tea) + " , from = " + JSON.toJSONString(from) + " , score = " + JSON.toJSONString(score),e);
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "表单成绩提交格式不正确！");
				return "res/osca/success";
			}
		}
		if(count==0){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "保存失败！");
		}else{
			oscaAppBiz.updateDocSaveStatus(doctorFlow,tea);
			oscaAppBiz.updateTeaScanDocStatus(doctorFlow,clinicalFlow,tea);
		}
		return "res/osca/success";
	}
	public static  void main(String args[])
	{
		String jsonData="{\"25bd8c75af874bb49ea9471851a93391\":{\"id\":\"25bd8c75af874bb49ea9471851a93391\",\"examScore\":\"11.1\"},\"8bfe51f5ce7544f68c169b52d103f7b1\":{\"id\":\"8bfe51f5ce7544f68c169b52d103f7b1\",\"examScore\":\"33\"}," +
				"\"ff6a5c509a804f7e8f0bb92b4f7037aa\":{\"id\":\"ff6a5c509a804f7e8f0bb92b4f7037aa\",\"examScore\":\"44\"},\"7be08e7152a04483b0ab7c1abbf0a14c\":{\"id\":\"7be08e7152a04483b0ab7c1abbf0a14c\",\"examScore\":\"22.8\"}}";
		try {
			Map<String, Map<String, Object>> dateMap = (Map<String, Map<String, Object>>) JSON.parse(jsonData);
		}catch (Exception e){
			e.printStackTrace();
		}
		List<String> datas=new ArrayList<>();
		datas.add("1");
		datas.add("2");
		datas.add("3");
		datas.add("4");
		datas.add("5");
		datas.add("6");
		System.out.println(JSON.toJSONString(datas));
	}

	//站点考核时，选择相应表单
	@RequestMapping(value={"/selectStationFrom"},method = {RequestMethod.POST})
	public  String selectStationFrom(String userFlow,String fromFlow,String doctorFlow,String scoreFlow,String isRequired,
								   String roomRecordFlow,String clinicalFlow,String stationFlow,Model model)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/osca/selectStationFrom";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/osca/selectStationFrom";
		}
		if(StringUtil.isBlank(clinicalFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核信息标识符为空");
			return "res/osca/selectStationFrom";
		}
		if(StringUtil.isBlank(stationFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "站点标识符为空");
			return "res/osca/selectStationFrom";
		}
		if(StringUtil.isBlank(roomRecordFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考场标识符为空");
			return "res/osca/selectStationFrom";
		}
		if(StringUtil.isBlank(fromFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "表单标识符为空");
			return "res/osca/selectStationFrom";
		}
		if(StringUtil.isBlank(isRequired)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "必选标识符为空");
			return "res/osca/selectStationFrom";
		}
		if(!"Y".equals(isRequired)&&!"N".equals(isRequired)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "必选标识符只能是Y或N");
			return "res/osca/selectStationFrom";
		}

		Map<String, String> param=new HashMap<>();
		param.put("userFlow", userFlow);
		param.put("doctorFlow", doctorFlow);
		param.put("clinicalFlow", clinicalFlow);
		param.put("stationFlow", stationFlow);
		param.put("roomRecordFlow", roomRecordFlow);
		param.put("fromFlow", fromFlow);
		param.put("scoreFlow", scoreFlow);
		param.put("isRequired", isRequired);
		model.addAttribute("paramMap",param);
		SysUser tea=oscaAppBiz.readSysUser(userFlow);
		//表单内容
		OscaFrom from=null;
		if(StringUtil.isBlank(scoreFlow))//未考核的话取原来的表单内容
		{
			//表单内容
			from=oscaAppBiz.getFromByFlow(fromFlow);
		}else{
			from=oscaAppBiz.getFromByScoreFlow(scoreFlow);
		}
		if(from==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "表单信息不存在");
			return "res/osca/selectStationFrom";
		}
		model.addAttribute("from",from);
		//成绩
		OscaSkillDocScore score=oscaAppBiz.getOscaSkillDocScoreByFlow(scoreFlow);
		model.addAttribute("score",score);
		String baseDir=oscaAppBiz.getCfgCode("upload_base_url");
		if(StringUtil.isNotBlank(baseDir)&&score!=null&&StringUtil.isNotBlank(score.getSiginImage()))
		{
			model.addAttribute("siginImage",baseDir+score.getSiginImage());
		}
		//固定表单
		if(from.getFromTypeId().equals("1")){
			String url=from.getFromUrl();
			if(StringUtil.isBlank(url)){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "表单信息不存在");
				return "res/osca/selectStationFrom";
			}
			//已经保存的分数
			if(score!=null) {
				Map<String, Object> map = oscaAppBiz.parseGuDingFromXml(score.getFromContent());
				model.addAttribute("valueMap", map);
				model.addAttribute("keySet", map.keySet());
			}
			url=url.replace("\\","/");
			String jspType=url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));
			model.addAttribute("fromType","GD");
			model.addAttribute("jspType",jspType);

			return "res/osca/selectStationFrom";
		}else{
			//非固定表单
			String content = from.getRromContent();
			model.addAttribute("fromType","FGD");
			List<FromTitle> titleList=oscaAppBiz.parseFromXmlForList(content);
			model.addAttribute("titleList",titleList);
			//已经保存的值
			Map<String,OscaSkillScoreDeatil> valueMap=new HashMap<>();
			List<OscaSkillScoreDeatil> list=oscaAppBiz.getScoreDeatilsByScoreFlow(scoreFlow);
			if(list!=null&&list.size()>0)
			{
				for(OscaSkillScoreDeatil d:list)
				{
					valueMap.put(d.getScoreKey(),d);
				}
			}
			model.addAttribute("valueMap", valueMap);
			return "res/osca/selectStationFrom";
		}
	}
	//查看站点成绩 包含无表单成绩，有固定表单成绩，有自定义表单成绩 无用了
	@RequestMapping(value={"/editStationScore"},method = {RequestMethod.POST})
	public  String editStationScore(String userFlow,String fromFlow,String doctorFlow,
								   String scoreFlow,Model model)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/osca/selectStationFrom";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/osca/selectStationFrom";
		}
		if(StringUtil.isBlank(scoreFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "成绩标识符为空");
			return "res/osca/selectStationFrom";
		}
		Map<String,String> param=new HashMap<>();
		param.put("userFlow", userFlow);
		param.put("doctorFlow", doctorFlow);

		SysUser tea=oscaAppBiz.readSysUser(userFlow);
		//成绩
		OscaSkillDocScore score=oscaAppBiz.getOscaSkillDocScoreByFlow(scoreFlow);
		model.addAttribute("score",score);
		String baseDir=oscaAppBiz.getCfgCode("upload_base_url");
		if(StringUtil.isNotBlank(baseDir)&&score!=null&&StringUtil.isNotBlank(score.getSiginImage()))
		{
			model.addAttribute("siginImage",baseDir+score.getSiginImage());
		}
		if(score!=null) {
			param.put("clinicalFlow", score.getClinicalFlow());
			param.put("stationFlow", score.getStationFlow());
			param.put("roomRecordFlow", score.getRoomRecordFlow());
			param.put("fromFlow", fromFlow);
			param.put("scoreFlow", scoreFlow);
			model.addAttribute("paramMap",param);
			//如果是没有表单的成绩就直接返回页面
			if(score.getIsHaveFrom().equals("N")){
	            model.addAttribute("haveFrom","N");
				return "res/osca/selectStationFrom";
			}else if(StringUtil.isBlank(fromFlow)){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "表单标识符为空！");
				return "res/osca/selectStationFrom";
			}
			//表单内容
			OscaFrom from = oscaAppBiz.getFromByFlow(fromFlow);
			model.addAttribute("from", from);
			//固定表单
            model.addAttribute("haveFrom","Y");
			//固定表单
			if(score.getFromTypeId().equals("1")){
				String url=score.getFromUrl();
				if(StringUtil.isBlank(url)){
					model.addAttribute("resultId", "3011101");
					model.addAttribute("resultType", "表单信息不存在");
					return "res/osca/selectStationFrom";
				}
				//已经保存的分数
				if(score!=null) {
					Map<String, Object> map = oscaAppBiz.parseGuDingFromXml(score.getFromContent());
					model.addAttribute("valueMap", map);
					model.addAttribute("keySet", map.keySet());
				}
				url=url.replace("\\","/");
				String jspType=url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));
				model.addAttribute("fromType","GD");
				model.addAttribute("jspType",jspType);

				return "res/osca/selectStationFrom";
			}else{
				//非固定表单
				String content = from.getRromContent();
				model.addAttribute("fromType","FGD");
				List<FromTitle> titleList=oscaAppBiz.parseFromXmlForList(content);
				model.addAttribute("titleList",titleList);
				//已经保存的值
				Map<String,OscaSkillScoreDeatil> valueMap=new HashMap<>();
				List<OscaSkillScoreDeatil> list=oscaAppBiz.getScoreDeatilsByScoreFlow(scoreFlow);
				if(list!=null&&list.size()>0)
				{
					for(OscaSkillScoreDeatil d:list)
					{
						valueMap.put(d.getScoreKey(),d);
					}
				}
				model.addAttribute("valueMap", valueMap);
				return "res/osca/selectStationFrom";
			}
		}else{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "成绩信息不存在");
			return "res/osca/selectStationFrom";
		}
	}
	//单个学员考核成绩提交
	@RequestMapping(value={"/scoreSubmit"},method = {RequestMethod.POST})
	public  String scoreSubmit(String userFlow,String doctorFlow,
								   String scoreFlows,Model model)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/osca/success";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/osca/success";
		}
		String []flows=doctorFlow.split(",");
		oscaAppBiz.scoreBatchSubmit(userFlow,flows);
		return "res/osca/success";
	}
	//已扫码学员列表
	@RequestMapping(value={"/scanStudent"},method = {RequestMethod.POST})
	public  String scanStudent(String userFlow,String userName,String statusId,String selectDate,
							   Integer pageIndex,
							   Integer pageSize,Model model)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/osca/studentList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "3030102");
			model.addAttribute("resultType", "起始页为空");
			return "res/osca/studentList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "页面大小为空");
			return "res/osca/studentList";
		}
		//包装筛选条件
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("partnerFlow",userFlow);
		paramMap.put("userName",userName);
		paramMap.put("statusId",statusId);
		if("submit".equals(statusId))
		{

			if(StringUtil.isBlank(selectDate)){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "请选择日期");
				return "res/osca/studentList";
			}
			try
			{
				SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
				dateFormat2.parse(selectDate);
			}
			catch (Exception e)
			{
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "日期格式错误");
				return "res/osca/studentList";
			}
			paramMap.put("selectDate",selectDate);
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<OscaTeaScanDoc> doctorMapList = oscaAppBiz.getScanDocList(paramMap);
		List<Map<String,Object>> list=new ArrayList<>();
		if(doctorMapList!=null&&doctorMapList.size()>0) {
			for(OscaTeaScanDoc sd:doctorMapList) {
				String codeInfo=sd.getCodeInfo();
				Map<String, String> codeMap = new HashMap<String, String>();
				transCodeInfo(codeMap, codeInfo);

				String clinicalFlow=codeMap.get("clinicalFlow");
				String recordFlow=codeMap.get("recordFlow");
				String doctorFlow=codeMap.get("doctorFlow");
				SysUser docUser=oscaAppBiz.readSysUser(doctorFlow);
				ResDoctor doctor=oscaAppBiz.readResDoctor(doctorFlow);
//				//查询学员当前状态
//				//查询当前考官可以考核当前学员学员哪些站点
//				Map<String,String> p = new HashMap<String,String>();
//				p.put("doctorFlow",doctorFlow);
//				p.put("clinicalFlow",clinicalFlow);
//				p.put("userFlow",userFlow);
//				List<OscaSubjectStation> stations=oscaAppBiz.getTeaDocStation(p);
//				//换考官后 再次查看学员
//				if(stations==null||stations.size()<=0)
//				{
//					stations=oscaAppBiz.getTeaSubDocStation(p);
//				}
//				String statusId="StayAssessment";
//				String statusName="待考核";
//				if(stations!=null&&stations.size()>0){
//					int NotHaveScore=0;
//					int SaveScore=0;
//					int SubmitScore=0;
//					for(int i=0;i<stations.size();i++)
//					{
//						OscaSubjectStation s=stations.get(i);
//						//查询当前站点考官可以考核学员的考场
//						Map<String,String> param=new HashMap<>();
//						param.put("userFlow",userFlow);
//						param.put("doctorFlow",doctorFlow);
//						param.put("stationFlow",s.getStationFlow());
//						param.put("clinicalFlow",clinicalFlow);
//						//当前站点考官是否给学员打过分
//						OscaSkillDocScore score=oscaAppBiz.getDocScoreByParam(param);
//						if(score==null){
//							NotHaveScore++;
//						}else if(StringUtil.isBlank(score.getStatusId())){
//							NotHaveScore++;
//						}else if(ScoreStatusEnum.Save.getId().equals(score.getStatusId())){
//							SaveScore++;
//						}else if(ScoreStatusEnum.Submit.getId().equals(score.getStatusId())){
//							SubmitScore++;
//						}
//					}
//					if(SaveScore>0){
//						statusId="NotSubmit";
//						statusName="未提交";
//					}
//					if(SubmitScore==stations.size()){
//						statusId="Submit";
//						statusName="已提交";
//					}
//					if(NotHaveScore>0){
//						statusId="StayAssessment";
//						statusName="待考核";
//					}
//				}

				Map<String, Object> data = new HashMap<String, Object>();
				data.put("codeInfo",codeMap);
				data.put("docUser",docUser);
				data.put("doctor",doctor);
				data.put("bean",sd);
				list.add(data);
			}
		}
		model.addAttribute("list",list);

		//获取访问路径前缀
		String uploadBaseUrl = oscaAppBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl",uploadBaseUrl);

		model.addAttribute("dataCount", PageHelper.total);
		return "res/osca/studentList";
	}
	@RequestMapping(value={"/notExamStudent"},method = {RequestMethod.POST})
	public  String notExamStudent(String userFlow,String searchStr,  Integer pageIndex,
							   Integer pageSize,Model model)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/osca/notExamStudentList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "3030102");
			model.addAttribute("resultType", "起始页为空");
			return "res/osca/notExamStudentList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "页面大小为空");
			return "res/osca/notExamStudentList";
		}
		//包装筛选条件
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("partnerFlow",userFlow);
		paramMap.put("searchStr",searchStr);
		paramMap.put("selectDate",DateUtil.getCurrDate());
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> list = oscaAppBiz.notExamStudentList(paramMap);
		model.addAttribute("list",list);

		//获取访问路径前缀
		String uploadBaseUrl = oscaAppBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl",uploadBaseUrl);

		model.addAttribute("dataCount", PageHelper.total);
		return "res/osca/notExamStudentList";
	}

	@RequestMapping(value={"/scoreBatchSumit"},method = {RequestMethod.POST})
	public  String scoreBatchSumit(String userFlow,String doctorFlows,Model model)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/osca/success";
		}
		if(StringUtil.isBlank(doctorFlows)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/osca/success";
		}
		String []flows=doctorFlows.split(",");
		if(flows==null||flows.length<=0){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/osca/success";
		}
		oscaAppBiz.scoreBatchSubmit(userFlow,flows);
		return "res/osca/success";
	}


	//解析二维码字符串为map
	private void transCodeInfo(Map<String,String> paramMap,String codeInfo){
		String[] params = StringUtil.split(codeInfo, "&");
		for(String paramStr : params){
			if(paramStr.indexOf("=")>0){
				String key = paramStr.substring(0, paramStr.indexOf("="));
				String value = paramStr.substring(paramStr.indexOf("=")+1, paramStr.length());
				paramMap.put(key, value);
			}
		}
	}
}

