package com.pinde.res.ctrl.osca;

import com.pinde.app.common.GlobalConstant;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.hbres.IFileBiz;
import com.pinde.res.biz.osca.IOscaAdminAppBiz;
import com.pinde.res.biz.osca.IOscaAppBiz;
import com.pinde.res.model.jswjw.mo.FromTitle;
import com.pinde.res.model.osca.mo.*;
import com.pinde.sci.model.mo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/osca/admin")
public class OscaAdminController {    
	private static Logger logger = LoggerFactory.getLogger(OscaAdminController.class);
	
	
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);

		return "res/osca/admin/500";
    }
	@Autowired
	private IOscaAppBiz oscaAppBiz;
	@Autowired
	private IOscaAdminAppBiz oscaAdminAppBiz;

	@Autowired
	private IFileBiz fileBiz;



	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "res/osca/admin/test";
	}
	@RequestMapping(value={"/remember"},method={RequestMethod.GET})
	public String remember(String userFlow,String doctorFlow,String stationFlow,String clinicalFlow , String roomRecordFlow ,
						   String scoreFlow , String fromFlow ,
						   String selectDate , HttpServletRequest request){
		HttpSession session = request.getSession();

		session.setAttribute("userFlow", userFlow);
		session.setAttribute("doctorFlow", doctorFlow);
		session.setAttribute("stationFlow", stationFlow);
		session.setAttribute("clinicalFlow", clinicalFlow);
		session.setAttribute("roomRecordFlow", roomRecordFlow);
		session.setAttribute("scoreFlow", scoreFlow);
		session.setAttribute("fromFlow", fromFlow);
		session.setAttribute("selectDate", selectDate);
		return "/res/osca/admin/test";
	}
	@RequestMapping(value={"/examInfo"},method={RequestMethod.POST})
	public String examInfo(String userFlow,String selectDate, HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/admin/examInfo";
		}
		if(StringUtil.isBlank(selectDate)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请选择日期");
			return "/res/osca/admin/examInfo";
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
			return "/res/osca/admin/examInfo";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户不存在");
			return "/res/osca/admin/examInfo";
		}
		SysOrg org=oscaAppBiz.getOrg(user.getOrgFlow());
		if(org==null)
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您暂无考点信息，无权限使用");
			return "/res/osca/admin/examInfo";
		}
		if(!"Y".equals(org.getIsExamOrg()))
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您的帐号不是考点帐号，无权限使用");
			return "/res/osca/admin/examInfo";
		}
		//签到及考核
		List<OscaSkillsAssessment> skillsAssessmentList=oscaAdminAppBiz.getSelectDateSkills(user.getOrgFlow(),selectDate);
		if(skillsAssessmentList!=null&&skillsAssessmentList.size()>0) {
			List<Map<String,String>> speList=getSpeList(skillsAssessmentList);
			model.addAttribute("speList", speList);
			List<OscaDoctorSkillsExt> doctorAssessmentList = oscaAdminAppBiz.getSelectDateDocSkills(user.getOrgFlow(), selectDate, "");
			Map<String,Integer> signinMap=new HashMap<>();
			int allExamNum=0;
			int passExamNum=0;
			int unPassExamNum=0;
			if(doctorAssessmentList!=null&&doctorAssessmentList.size()>0)
			{
				for(OscaDoctorSkillsExt doctorSkillsExt:doctorAssessmentList)
				{
					if(doctorSkillsExt.getDoctorAssessments()!=null) {
						allExamNum+=doctorSkillsExt.getDoctorAssessments().size();
						for(OscaDoctorAssessment doctorAssessment:doctorSkillsExt.getDoctorAssessments()) {
							if (GlobalConstant.FLAG_Y.equals(doctorAssessment.getIsSavePass())) {
								passExamNum++;
							} else {
								unPassExamNum++;
							}
						}
					}
				}
			}
			float per=0;
			if(allExamNum>0)
			{
				per=getPer(passExamNum,allExamNum);
			}
			model.addAttribute("per", per);
			model.addAttribute("allExamNum", allExamNum);
			model.addAttribute("passExamNum", passExamNum);
			model.addAttribute("unPassExamNum", unPassExamNum);

			List<OscaDoctorSkillsExt> doctorSignInList = oscaAdminAppBiz.getSelectDateDocSignIns(user.getOrgFlow(), selectDate,"");
			int allSignInNum=0;
			HashSet<String> set = new HashSet<String>();
			if(doctorSignInList!=null&&doctorSignInList.size()>0)
			{
				for(OscaDoctorSkillsExt doctorSkillsExt:doctorSignInList)
				{

					if(doctorSkillsExt.getDoctorAssessments()!=null) {
						for(OscaDoctorAssessment doctorAssessment:doctorSkillsExt.getDoctorAssessments()) {
							if("SignIn".equals(doctorAssessment.getSiginStatusId()))
							{
								setCount(signinMap,doctorSkillsExt.getSpeId());
								set.add(doctorAssessment.getDoctorFlow());
							}
						}
					}

				}
				allSignInNum=set.size();
			}
			model.addAttribute("signinMap", signinMap);
			model.addAttribute("allSignInNum", allSignInNum);
			//考场分布
			List<OscaSkillsRoomExt> rooms = oscaAdminAppBiz.getSelectDateSkillRooms(user.getOrgFlow(), selectDate);
			Map<String,Integer> roomMap=new HashMap<>();
			Map<String,List<String>> flowsMap=new HashMap<>();
			int allRoomNum=0;
			set.clear();
			if(rooms!=null&&rooms.size()>0)
			{
				for(OscaSkillsRoomExt roomExt:rooms)
				{
					if(roomExt.getSkillRooms()!=null) {

						List<String> list=flowsMap.get(roomExt.getSpeId());
						if(list==null) list=new ArrayList<>();
						for(OscaSkillRoom r:roomExt.getSkillRooms()) {
							if(!list.contains(r.getRoomFlow()))
							{
								list.add(r.getRoomFlow());
								setCount(roomMap,roomExt.getSpeId());
							}
							set.add(r.getRoomFlow());
						}
						flowsMap.put(roomExt.getSpeId(),list);
					}
				}
				allRoomNum=set.size();
			}
			model.addAttribute("roomMap", roomMap);
			model.addAttribute("allRoomNum", allRoomNum);

			//考官分布
			List<OscaSkillsPartnerExt> partners = oscaAdminAppBiz.getSelectDateSkillPartners(user.getOrgFlow(), selectDate);
			Map<String,Integer> partnerMap=new HashMap<>();
			flowsMap.clear();
			int allPartnerNum=0;
			set.clear();
			if(rooms!=null&&rooms.size()>0)
			{
				for(OscaSkillsPartnerExt partnerExt:partners) {
					if (partnerExt.getRoomTeas() != null) {

						List<String> list=flowsMap.get(partnerExt.getSpeId());
						if(list==null) list=new ArrayList<>();
						for (OscaSkillRoomTea r : partnerExt.getRoomTeas()) {
							if(!list.contains(r.getPartnerFlow()))
							{
								list.add(r.getPartnerFlow());
								setCount(partnerMap,partnerExt.getSpeId());
							}
							set.add(r.getPartnerFlow());
						}
						flowsMap.put(partnerExt.getSpeId(),list);
					}
				}
				allPartnerNum=set.size();
			}
			model.addAttribute("partnerMap", partnerMap);
			model.addAttribute("allPartnerNum", allPartnerNum);


			return "/res/osca/admin/examInfo";
		}else{

			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", selectDate+"无考核信息");
			return "/res/osca/admin/examInfo";
		}
	}
	@RequestMapping(value={"/perInfo"},method={RequestMethod.POST})
	public String perInfo(String userFlow,String selectDate,String speId, HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/admin/perInfo";
		}
		if(StringUtil.isBlank(selectDate)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请选择日期");
			return "/res/osca/admin/perInfo";
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
			return "/res/osca/admin/perInfo";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户不存在");
			return "/res/osca/admin/perInfo";
		}
		SysOrg org=oscaAppBiz.getOrg(user.getOrgFlow());
		if(org==null)
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您暂无考点信息，无权限使用");
			return "/res/osca/admin/perInfo";
		}
		if(!"Y".equals(org.getIsExamOrg()))
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您的帐号不是考点帐号，无权限使用");
			return "/res/osca/admin/perInfo";
		}
		//签到及考核
		List<OscaSkillsAssessment> skillsAssessmentList=oscaAdminAppBiz.getSelectDateSkills(user.getOrgFlow(),selectDate);
		if(skillsAssessmentList!=null&&skillsAssessmentList.size()>0) {
			List<Map<String,String>> speList=getSpeList(skillsAssessmentList);
			model.addAttribute("speList", speList);
			if(StringUtil.isBlank(speId))
			{
				speId=speList.get(0).get("speId");
			}
			model.addAttribute("speId", speId);
			List<OscaSkillsAssessment> list=new ArrayList<>();
			for(OscaSkillsAssessment s:skillsAssessmentList)
			{
				if(speId.equals(s.getSpeId()))
				{
					list.add(s);
				}
			}
			model.addAttribute("skillsAssessmentList", list);
			List<OscaDoctorSkillsExt> doctorAssessmentList = oscaAdminAppBiz.getSelectDateDocSkills(user.getOrgFlow(), selectDate,speId);
//			List<OscaDoctorSkillsExt> doctorAssessmentList = oscaAdminAppBiz.getSelectDateDocSignIns(user.getOrgFlow(), selectDate,speId);

			int allExamNum=0;
			int passExamNum=0;
			int unPassExamNum=0;
			Double maxScore=0.0;
			Double minScore=0.0;
			Map<String,Double> skillMaxScoreMap=new HashMap<>();//每一个考核信息的最大考核分数
			Map<String,Double> skillMinScoreMap=new HashMap<>();//每一个考核信息的最大考核分数
			Map<String,Integer> allExamNumMap=new HashMap<>();//每一个考核信息的总人数
			Map<String,Integer> passExamNumMap=new HashMap<>();//每一个考核信息的通过人数
			Map<String,Integer> unPassExamNumMap=new HashMap<>();//每一个考核信息的不通过总人数

			Map<String,Integer> partPassExamNumMap=new HashMap<>();//每一个部分通过人数
			Map<String,Integer> partUnPassExamNumMap=new HashMap<>();//每一个部分不通过总人数
			Map<String,Integer> stationPassExamNumMap=new HashMap<>();//每一个站通过人数
			Map<String,Integer> stationUnPassExamNumMap=new HashMap<>();//每一个站不通过总人数


			Map<String,Float> perMap=new HashMap<>();//每一个考核信息的通过率
			Map<String,OscaSkillRoomDoc> docExamStationMap=new HashMap<>();//学员考核的信息
			Map<String,Double> allScoreMap=new HashMap<>();//学员考核总分
			Map<String,Map<String,Integer>> partPassScoreAllMap=new HashMap<>();//每一部分考核的标准分
			Map<String,Map<String,Integer>> stationPassScoreAllMap=new HashMap<>();//每一站考核的标准分
			Map<String,Map<String, List<OscaSubjectStation>>> partAllMap=new HashMap<>();//学员考核的信息
			Map<String,Object> subjectStationsMap=new HashMap<>();
			Map<String,Object> doctorAssessMap=new HashMap<>();
			Map<String,String> passCfg=new HashMap<>();
			for(OscaSkillsAssessment assessment:skillsAssessmentList)
			{
				List<OscaSubjectStation> stations = (List<OscaSubjectStation>) subjectStationsMap.get(assessment.getSubjectFlow());
				OscaSubjectMain main =(OscaSubjectMain) subjectStationsMap.get(assessment.getSubjectFlow()+"Main");
				List<OscaSubjectPartScore> partScores = (List<OscaSubjectPartScore>) subjectStationsMap.get(assessment.getSubjectFlow()+"Part");
				List<OscaSubjectStationScore> stationScores = (List<OscaSubjectStationScore>) subjectStationsMap.get(assessment.getSubjectFlow()+"Station");
				//每一部分的分数
				Map<String,Integer> partPassScoreMap=partPassScoreAllMap.get(assessment.getSubjectFlow());
				//每一站的合格分
				Map<String,Integer> stationPassScoreMap=stationPassScoreAllMap.get(assessment.getSubjectFlow());

				//每部分的站点数量
				Map<String, List<OscaSubjectStation>> partMap=partAllMap.get(assessment.getSubjectFlow());

				Double skillMaxScore= (Double) skillMaxScoreMap.get(assessment.getClinicalFlow());
				Double skillMinScore= (Double) skillMinScoreMap.get(assessment.getClinicalFlow());
				if(skillMaxScore==null) skillMaxScore=0.0;
				if(skillMinScore==null) skillMinScore=999999.0;
				if(stations==null)
				{
					skillMaxScore=0.0;
					skillMinScore=0.0;
					stations=oscaAppBiz.getOscaSubjectStations(assessment.getSubjectFlow());
					subjectStationsMap.put(assessment.getSubjectFlow(),stations);
					if(partMap==null) {
						partMap=new HashMap<>();
						for (OscaSubjectStation s : stations) {
							List<OscaSubjectStation> temp = partMap.get(s.getPartFlow());
							if (temp == null)
								temp = new ArrayList<>();
							temp.add(s);
							partMap.put(s.getPartFlow(), temp);
							if(s.getStationScore()!=null) {
								skillMaxScore += s.getStationScore();
								skillMinScore += s.getStationScore();
							}
						}
						partAllMap.put(assessment.getSubjectFlow(),partMap);
					}
				}
				String type=passCfg.get(assessment.getSubjectFlow());
				if(StringUtil.isBlank(type)) {
					if(main==null) {
						main =oscaAppBiz.getOscaSubjectMain(assessment.getSubjectFlow());
						subjectStationsMap.put(assessment.getSubjectFlow()+"Main",main);
					}
					if(partScores==null) {
						partScores = oscaAppBiz.getOscaSubjectPartScores(assessment.getSubjectFlow());
						subjectStationsMap.put(assessment.getSubjectFlow()+"Part",partScores);
					}
					if(stationScores==null) {
						stationScores =oscaAppBiz.getOscaSubjectStationScores(assessment.getSubjectFlow());
						subjectStationsMap.put(assessment.getSubjectFlow() + "Station",stationScores);
					}
					int partScoreNum=0;
					if(partPassScoreMap==null) {
						partPassScoreMap=new HashMap<>();
						if (partScores != null) {
							for (OscaSubjectPartScore partScore : partScores) {
								partPassScoreMap.put(partScore.getPartFlow(), partScore.getPartScore());
								if(partScore.getPartScore()!=null) partScoreNum++;
							}
						}
						partPassScoreAllMap.put(assessment.getSubjectFlow(),partPassScoreMap);
					}
					int stationScoreNum=0;
					if(stationPassScoreMap==null) {
						stationPassScoreMap=new HashMap<>();
						if (stationScores != null) {
							for (OscaSubjectStationScore stationScore : stationScores) {
								stationPassScoreMap.put(stationScore.getStationFlow(), stationScore.getStationScore());
								if(stationScore.getStationScore()!=null) stationScoreNum++;
							}
						}
						stationPassScoreAllMap.put(assessment.getSubjectFlow(),stationPassScoreMap);
					}
					if (main != null && main.getAllScore() != null) {
						type = "ALL";
					} else if (partScores != null && partScores.size() > 0 && partScoreNum>0) {
						type = "PART";
					} else if (stationScores != null && stationScores.size() > 0 && stationScoreNum>0) {
						type = "STATION";
					} else {
						type = "ALLNOTCFG";
					}
					passCfg.put(assessment.getSubjectFlow(), type);
				}
			}
			if(doctorAssessmentList!=null&&doctorAssessmentList.size()>0)
			{
				 maxScore=0.0;
				 minScore=999999.0;
				for(OscaDoctorSkillsExt doctorSkillsExt:doctorAssessmentList) {
					doctorAssessMap.put(doctorSkillsExt.getClinicalFlow(),doctorSkillsExt);
					List<OscaSubjectStation> stations = (List<OscaSubjectStation>) subjectStationsMap.get(doctorSkillsExt.getSubjectFlow());
					OscaSubjectMain main =(OscaSubjectMain) subjectStationsMap.get(doctorSkillsExt.getSubjectFlow()+"Main");
					List<OscaSubjectPartScore> partScores = (List<OscaSubjectPartScore>) subjectStationsMap.get(doctorSkillsExt.getSubjectFlow()+"Part");
					List<OscaSubjectStationScore> stationScores = (List<OscaSubjectStationScore>) subjectStationsMap.get(doctorSkillsExt.getSubjectFlow()+"Station");
					//每一部分的分数
					Map<String,Integer> partPassScoreMap=partPassScoreAllMap.get(doctorSkillsExt.getSubjectFlow());
					//每一站的合格分
					Map<String,Integer> stationPassScoreMap=stationPassScoreAllMap.get(doctorSkillsExt.getSubjectFlow());

					//每部分的站点数量
					Map<String, List<OscaSubjectStation>> partMap=partAllMap.get(doctorSkillsExt.getSubjectFlow());

					Double skillMaxScore= (Double) skillMaxScoreMap.get(doctorSkillsExt.getClinicalFlow());
					Double skillMinScore= (Double) skillMinScoreMap.get(doctorSkillsExt.getClinicalFlow());
					if(skillMaxScore==null) skillMaxScore=0.0;
					if(skillMinScore==null) skillMinScore=999999.0;
					if(stations==null)
					{
						skillMaxScore=0.0;
						skillMinScore=0.0;
						stations=oscaAppBiz.getOscaSubjectStations(doctorSkillsExt.getSubjectFlow());
						subjectStationsMap.put(doctorSkillsExt.getSubjectFlow(),stations);
						if(partMap==null) {
							partMap=new HashMap<>();
							for (OscaSubjectStation s : stations) {
								List<OscaSubjectStation> temp = partMap.get(s.getPartFlow());
								if (temp == null)
									temp = new ArrayList<>();
								temp.add(s);
								partMap.put(s.getPartFlow(), temp);
								if(s.getStationScore()!=null) {
									skillMaxScore += s.getStationScore();
									skillMinScore += s.getStationScore();
								}
							}
							partAllMap.put(doctorSkillsExt.getSubjectFlow(),partMap);
						}
					}
					String type=passCfg.get(doctorSkillsExt.getSubjectFlow());
					if(StringUtil.isBlank(type)) {
						if(main==null) {
							main =oscaAppBiz.getOscaSubjectMain(doctorSkillsExt.getSubjectFlow());
							subjectStationsMap.put(doctorSkillsExt.getSubjectFlow()+"Main",main);
						}
						if(partScores==null) {
							partScores = oscaAppBiz.getOscaSubjectPartScores(doctorSkillsExt.getSubjectFlow());
							subjectStationsMap.put(doctorSkillsExt.getSubjectFlow()+"Part",partScores);
						}
						if(stationScores==null) {
							stationScores =oscaAppBiz.getOscaSubjectStationScores(doctorSkillsExt.getSubjectFlow());
							subjectStationsMap.put(doctorSkillsExt.getSubjectFlow() + "Station",stationScores);
						}
						int partScoreNum=0;
						if(partPassScoreMap==null) {
							partPassScoreMap=new HashMap<>();
							if (partScores != null) {
								for (OscaSubjectPartScore partScore : partScores) {
									partPassScoreMap.put(partScore.getPartFlow(), partScore.getPartScore());
									if(partScore.getPartScore()!=null) partScoreNum++;
								}
							}
							partPassScoreAllMap.put(doctorSkillsExt.getSubjectFlow(),partPassScoreMap);
						}
						int stationScoreNum=0;
						if(stationPassScoreMap==null) {
							stationPassScoreMap=new HashMap<>();
							if (stationScores != null) {
								for (OscaSubjectStationScore stationScore : stationScores) {
									stationPassScoreMap.put(stationScore.getStationFlow(), stationScore.getStationScore());
									if(stationScore.getStationScore()!=null) stationScoreNum++;
								}
							}
							stationPassScoreAllMap.put(doctorSkillsExt.getSubjectFlow(),stationPassScoreMap);
						}
						if (main != null && main.getAllScore() != null) {
							type = "ALL";
						} else if (partScores != null && partScores.size() > 0 && partScoreNum>0) {
							type = "PART";
						} else if (stationScores != null && stationScores.size() > 0 && stationScoreNum>0) {
							type = "STATION";
						} else {
							type = "ALLNOTCFG";
						}
						passCfg.put(doctorSkillsExt.getSubjectFlow(), type);
					}
					int allNum=0;
					int passNum=0;
					int unPassNum=0;
					if(doctorSkillsExt.getDoctorAssessments()!=null) {
						allExamNum+=doctorSkillsExt.getDoctorAssessments().size();
						allNum+=doctorSkillsExt.getDoctorAssessments().size();
						for(OscaDoctorAssessment doctorAssessment:doctorSkillsExt.getDoctorAssessments()) {
							if (GlobalConstant.FLAG_Y.equals(doctorAssessment.getIsSavePass())) {
								passExamNum++;
								passNum++;
							} else {
								unPassExamNum++;
								unPassNum++;
							}
							Double allScore =0.0;
							if("ALL".equals(type)||"ALLNOTCFG".equals(type))
							{
								allScore=oscaAdminAppBiz.getDoctorExamScore(doctorAssessment.getClinicalFlow(),doctorAssessment.getDoctorFlow());
							}else {
								//取学员所有考核信息
								List<OscaSkillRoomDoc> skillRoomDocs = oscaAppBiz.getOscaSkillRoomDocs(doctorAssessment.getClinicalFlow(), doctorAssessment.getDoctorFlow());

								//考核分数
								Map<String, Double> examScoreMap = new HashMap<>();
								if (skillRoomDocs != null && skillRoomDocs.size() > 0) {
									for (OscaSkillRoomDoc roomDoc : skillRoomDocs) {
										docExamStationMap.put(roomDoc.getClinicalFlow() + roomDoc.getDoctorFlow() + roomDoc.getStationFlow(), roomDoc);
										if (StringUtil.isNotBlank(roomDoc.getExamSaveScore()))
											allScore += Double.valueOf(roomDoc.getExamSaveScore());
										Double examScore = 0.0;
										if (StringUtil.isNotBlank(roomDoc.getExamSaveScore())) {
											examScore=Double.valueOf(roomDoc.getExamSaveScore());
										}
										examScoreMap.put(roomDoc.getStationFlow(), examScore);
									}
								}
								if("PART".equals(type))
								{
									//计算每一部分的总分，考核总分，以及是否通过
									for (String key : partMap.keySet()) {
										List<OscaSubjectStation> temp = partMap.get(key);
										double examAll = 0;
										for (OscaSubjectStation s : temp) {
											Double examScore = examScoreMap.get(s.getStationFlow());
											if(examScore==null) examScore=0.0;
											examAll += examScore;
										}
										Integer partPassScore=partPassScoreMap.get(key);
										//如果未配置 或 每一部分部分大于每一部分考核总分 就算合格
										if(partPassScore==null||examAll>=partPassScore)
										{
											setCount(partPassExamNumMap,doctorAssessment.getClinicalFlow()+key);
										}else{
											setCount(partUnPassExamNumMap,doctorAssessment.getClinicalFlow()+key);
										}
									}
								}else{

									for (OscaSubjectStation s : stations) {
										Integer stationPassScore=stationPassScoreMap.get(s.getStationFlow());
										Double examScore = examScoreMap.get(s.getStationFlow());
										//如果未配置 或 每一部分部分大于每一部分考核总分 就算合格
										if (examScore!=null&&(stationPassScore == null || examScore >= stationPassScore) ){
											setCount(stationPassExamNumMap, doctorAssessment.getClinicalFlow() + s.getStationFlow());
										} else {
											setCount(stationUnPassExamNumMap, doctorAssessment.getClinicalFlow() + s.getStationFlow());
										}
									}
								}
							}
							if(allScore==null) allScore=0.0;
							if(skillMinScore==null) skillMinScore=0.0;
								allScoreMap.put(doctorAssessment.getClinicalFlow()+doctorAssessment.getDoctorFlow(),allScore);
							if (allScore > maxScore) maxScore = allScore;
							if (allScore > skillMaxScore) skillMaxScore = allScore;
							if (allScore < skillMinScore) skillMinScore = allScore;
							if (minScore > allScore) minScore = allScore;
						}
					}
					float per=0;
					if(allNum>0)
					{
						per=getPer(passNum,allNum);
					}
					if(main!=null&&main.getAllScore()!=null&&main.getAllScore()>skillMaxScore)
					{
						skillMaxScore=main.getAllScore()+0.0;
					}
					if(main!=null&&main.getAllScore()!=null&&main.getAllScore()<skillMinScore)
					{
						skillMinScore=main.getAllScore()+0.0;
					}
					skillMaxScoreMap.put(doctorSkillsExt.getClinicalFlow(),skillMaxScore);
					skillMinScoreMap.put(doctorSkillsExt.getClinicalFlow(),skillMinScore);
					allExamNumMap.put(doctorSkillsExt.getClinicalFlow(),allNum);
					passExamNumMap.put(doctorSkillsExt.getClinicalFlow(),passNum);
					unPassExamNumMap.put(doctorSkillsExt.getClinicalFlow(),unPassNum);
					perMap.put(doctorSkillsExt.getClinicalFlow(),per);
				}
			}
			float per=0;
			if(allExamNum>0)
			{
				per=getPer(passExamNum,allExamNum);
			}
			model.addAttribute("per", per);
			model.addAttribute("allExamNum", allExamNum);
			model.addAttribute("passExamNum", passExamNum);
			model.addAttribute("unPassExamNum", unPassExamNum);
			model.addAttribute("minScore", minScore);
			model.addAttribute("maxScore", maxScore);
			model.addAttribute("doctorAssessmentList", doctorAssessmentList);
			model.addAttribute("subjectStationsMap", subjectStationsMap);


			model.addAttribute("skillMaxScoreMap", skillMaxScoreMap);
			model.addAttribute("skillMinScoreMap", skillMinScoreMap);
			model.addAttribute("allExamNumMap", allExamNumMap);
			model.addAttribute("passExamNumMap", passExamNumMap);
			model.addAttribute("unPassExamNumMap", unPassExamNumMap);

			model.addAttribute("partPassExamNumMap",partPassExamNumMap);
			model.addAttribute("partUnPassExamNumMap",partUnPassExamNumMap);
			model.addAttribute("stationPassExamNumMap",stationPassExamNumMap);
			model.addAttribute("stationUnPassExamNumMap",stationUnPassExamNumMap);

			model.addAttribute("passCfg", passCfg);
			model.addAttribute("perMap", perMap);
			model.addAttribute("docExamStationMap", docExamStationMap);
			model.addAttribute("allScoreMap", allScoreMap);
			model.addAttribute("partPassScoreAllMap", partPassScoreAllMap);
			model.addAttribute("stationPassScoreAllMap", stationPassScoreAllMap);
			model.addAttribute("partAllMap", partAllMap);
			model.addAttribute("doctorAssessMap", doctorAssessMap);

			return "/res/osca/admin/perInfo";
		}else{

			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", selectDate+"无考核信息");
			return "/res/osca/admin/perInfo";
		}
	}
	@RequestMapping(value={"/calendarInfo"},method={RequestMethod.POST})
	public String calendarInfo(String userFlow,String month, HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/admin/calendarInfo";
		}
		if(StringUtil.isBlank(month)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请选择日期");
			return "/res/osca/admin/calendarInfo";
		}
		try
		{
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
			dateFormat2.parse(month);
		}
		catch (Exception e)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "日期格式错误");
			return "/res/osca/admin/calendarInfo";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户不存在");
			return "/res/osca/admin/calendarInfo";
		}
		SysOrg org=oscaAppBiz.getOrg(user.getOrgFlow());
		if(org==null)
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您暂无考点信息，无权限使用");
			return "/res/osca/admin/calendarInfo";
		}
		if(!"Y".equals(org.getIsExamOrg()))
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您的帐号不是考点帐号，无权限使用");
			return "/res/osca/admin/calendarInfo";
		}
		List<Map<String,Object>> list=oscaAdminAppBiz.getCalendarInfo(user.getOrgFlow(),month);
		model.addAttribute("list",list);
		Map<String,Object> typeInfo=oscaAdminAppBiz.getTypeInfoByMonth(user.getOrgFlow(),month);
		model.addAttribute("typeInfo",typeInfo);
		return "/res/osca/admin/calendarInfo";
	}
	@RequestMapping(value={"/monthInfo"},method={RequestMethod.POST})
	public String monthInfo(String userFlow,String year, HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/admin/monthInfo";
		}
		if(StringUtil.isBlank(year)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请选择日期");
			return "/res/osca/admin/monthInfo";
		}
		try
		{
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy");
			dateFormat2.parse(year);
		}
		catch (Exception e)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "日期格式错误");
			return "/res/osca/admin/monthInfo";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户不存在");
			return "/res/osca/admin/monthInfo";
		}
		SysOrg org=oscaAppBiz.getOrg(user.getOrgFlow());
		if(org==null)
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您暂无考点信息，无权限使用");
			return "/res/osca/admin/monthInfo";
		}
		if(!"Y".equals(org.getIsExamOrg()))
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您的帐号不是考点帐号，无权限使用");
			return "/res/osca/admin/monthInfo";
		}
		List<Map<String,Object>> list=oscaAdminAppBiz.getMonthInfo(user.getOrgFlow(),year);
		model.addAttribute("list",list);

		Map<String,Object> typeInfo=oscaAdminAppBiz.getTypeInfoByYear(user.getOrgFlow(),year);
		model.addAttribute("typeInfo",typeInfo);
		return "/res/osca/admin/monthInfo";
	}

	private float getPer(int a, int b) {

		//这里的数后面加“D”是表明它是Double类型，否则相除的话取整，无法正常使用
		float percent = (a+0.0f) / b*100f;
		percent = new BigDecimal(percent).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		//输出一下，确认你的小数无误
		//System.out.println("小数：" + percent);
		return  percent;
	}

	@RequestMapping(value={"/roomInfo"},method={RequestMethod.POST})
	public String roomInfo(String userFlow,String selectDate,Integer pageIndex,String searchStr,
						   Integer pageSize, HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/admin/roomInfo";
		}
		if(StringUtil.isBlank(selectDate)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请选择日期");
			return "/res/osca/admin/roomInfo";
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
			return "/res/osca/admin/roomInfo";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "3030102");
			model.addAttribute("resultType", "起始页为空");
			return "/res/osca/admin/roomInfo";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "页面大小为空");
			return "/res/osca/admin/roomInfo";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户不存在");
			return "/res/osca/admin/roomInfo";
		}
		SysOrg org=oscaAppBiz.getOrg(user.getOrgFlow());
		if(org==null)
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您暂无考点信息，无权限使用");
			return "/res/osca/admin/roomInfo";
		}
		if(!"Y".equals(org.getIsExamOrg()))
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您的帐号不是考点帐号，无权限使用");
			return "/res/osca/admin/roomInfo";
		}

		Map<String,Object> paramMap = new HashMap<String, Object>();
		Map<String,Object> itemsMap = new HashMap<String, Object>();
		paramMap.put("orgFlow",user.getOrgFlow());
		paramMap.put("selectDate",selectDate);
		paramMap.put("searchStr",searchStr);
		PageHelper.startPage(pageIndex, pageSize);
		List<SysDict> roomList = oscaAdminAppBiz.getOrgRoomList(paramMap);
		if(roomList!=null&&roomList.size()>0)
		{
			for(SysDict dict:roomList) {
				paramMap.put("roomFLow", dict.getDictId());
				List<Map<String, String>> items = oscaAdminAppBiz.getRoomInfoByRoomFlow(paramMap);
				if (items != null && items.size() > 0)
				{
					for(Map<String, String> item:items){
						paramMap.put("partnerFlow", item.get("partnerFlow"));
						Integer notExamNum=null;
						if(StringUtil.isNotBlank( item.get("partnerFlow"))) {
							List<Map<String, String>> list = oscaAdminAppBiz.getRoomPartnerExam(paramMap);
							if (list != null) {
								Map<String, String> map = new HashMap<>();
								for (Map<String, String> m : list) {
									item.put(m.get("EXAM_STATUS_ID"), m.get("EXAM_NUM"));
								}
							}
							notExamNum = oscaAdminAppBiz.getRoomPartnerNotExam(paramMap);
						}
						if (notExamNum == null) notExamNum = 0;
						item.put("notExamNum", notExamNum + "");
					}
				}
				itemsMap.put(dict.getDictId(),items);
			}
		}
		model.addAttribute("roomList", roomList);
		model.addAttribute("itemsMap", itemsMap);
		model.addAttribute("dataCount", PageHelper.total);
		return "/res/osca/admin/roomInfo";
	}
	@RequestMapping(value={"/singinInfo"},method={RequestMethod.POST})
	public String singinInfo(String userFlow,String selectDate,String speId, HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/admin/singinInfo";
		}
		if(StringUtil.isBlank(selectDate)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请选择日期");
			return "/res/osca/admin/singinInfo";
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
			return "/res/osca/admin/singinInfo";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户信息不存在");
			return "/res/osca/admin/singinInfo";
		}
		SysOrg org=oscaAppBiz.getOrg(user.getOrgFlow());
		if(org==null)
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您暂无考点信息，无权限使用");
			return "/res/osca/admin/singinInfo";
		}
		if(!"Y".equals(org.getIsExamOrg()))
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您的帐号不是考点帐号，无权限使用");
			return "/res/osca/admin/singinInfo";
		}
		//签到及考核
		List<OscaSkillsAssessment> skillsAssessmentList=oscaAdminAppBiz.getSelectDateSkills(user.getOrgFlow(),selectDate);
		if(skillsAssessmentList!=null&&skillsAssessmentList.size()>0) {

			List<Map<String,String>> speList=getSpeList(skillsAssessmentList);
			model.addAttribute("speList", speList);
			List<OscaSkillsTimeExt> skillsTimeExts = oscaAdminAppBiz.getSelectDateSkillTimes(user.getOrgFlow(), selectDate,speId);

			Map<String,Integer> countMap=new HashMap<>();
			int all=0;
			int allSignInNum=0;
			int allNoSignInNum=0;
			List<OscaDoctorSkillsExt> auditedList = oscaAdminAppBiz.getSelectDateAudited(user.getOrgFlow(), selectDate,speId);

			if(auditedList!=null&&auditedList.size()>0)
			{
				for(OscaDoctorSkillsExt doctorSkillsExt:auditedList)
				{
					if(doctorSkillsExt.getDoctorAssessments()!=null) {
						for(OscaDoctorAssessment doctorAssessment:doctorSkillsExt.getDoctorAssessments()) {
							String baseKey=doctorAssessment.getClinicalFlow()+doctorAssessment.getExamStartTime()+doctorAssessment.getExamEndTime();
							all++;
							setCount(countMap,baseKey+"ALL");
							if("SignIn".equals(doctorAssessment.getSiginStatusId()))
							{
								allSignInNum++;
								setCount(countMap,baseKey+"SignIn");
							}else {
								allNoSignInNum++;
								setCount(countMap,baseKey+"NoSignIn");
							}
						}
					}

				}
			}

			float per=0;
			if(all>0)
			{
				per=getPer(allSignInNum,all);
			}
			model.addAttribute("per", per);
			model.addAttribute("all", all);
			model.addAttribute("allSignInNum", allSignInNum);
			model.addAttribute("allNoSignInNum", allNoSignInNum);

			model.addAttribute("countMap", countMap);
			model.addAttribute("skillsTimeExts", skillsTimeExts);
			return "/res/osca/admin/singinInfo";
		}else{

			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", selectDate+"无考核信息");
			return "/res/osca/admin/singinInfo";
		}
	}
	@RequestMapping(value={"/scoreDetailList"},method={RequestMethod.POST})
	public String scoreDetailList(String userFlow,String selectDate,String speId,String isPass,
								  Integer pageIndex,String searchStr,
								  Integer pageSize, HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/admin/scoreDetailList";
		}
		if(StringUtil.isBlank(selectDate)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请选择日期");
			return "/res/osca/admin/scoreDetailList";
		}
		if(StringUtil.isNotBlank(isPass)&&!"Y".equals(isPass)&&!"N".equals(isPass)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "只能输入合格或不合格");
			return "/res/osca/admin/scoreDetailList";
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
			return "/res/osca/admin/scoreDetailList";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户信息不存在");
			return "/res/osca/admin/scoreDetailList";
		}
		SysOrg org=oscaAppBiz.getOrg(user.getOrgFlow());
		if(org==null)
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您暂无考点信息，无权限使用");
			return "/res/osca/admin/scoreDetailList";
		}
		if(!"Y".equals(org.getIsExamOrg()))
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您的帐号不是考点帐号，无权限使用");
			return "/res/osca/admin/scoreDetailList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "3030102");
			model.addAttribute("resultType", "起始页为空");
			return "/res/osca/admin/scoreDetailList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "页面大小为空");
			return "/res/osca/admin/scoreDetailList";
		}
		Map<String,String> maxTimeMap=oscaAdminAppBiz.getSelectDateMaxExamTime(user.getOrgFlow(),selectDate,speId);
		model.addAttribute("maxTimeMap",maxTimeMap);
		Integer differ=0;
		OscaExamDifferScore differScore=oscaAppBiz.readDiffierScoreByOrgFlow(org.getOrgFlow());
		if(differScore!=null&&differScore.getDifferScore()!=null)
		{
			differ=differScore.getDifferScore();
		}
		//签到及考核
		List<OscaSkillsAssessment> skillsAssessmentList=oscaAdminAppBiz.getSelectDateSkills(user.getOrgFlow(),selectDate);
		if(skillsAssessmentList!=null&&skillsAssessmentList.size()>0) {

			List<Map<String, String>> speList=new ArrayList<>();
			int maxStationSize=0;
			Map<String, OscaSubjectStation> assessStationMap=new HashMap<>();
			List<String> speIds=new ArrayList<>();
			if(skillsAssessmentList!=null&&skillsAssessmentList.size()>0) {
				for(OscaSkillsAssessment assessment:skillsAssessmentList)
				{
					if(!speIds.contains(assessment.getSpeId()))
					{
						speIds.add(assessment.getSpeId());
						Map<String, String> map=new HashMap<>();
						map.put("speId",assessment.getSpeId());
						map.put("speName",assessment.getSpeName());
						speList.add(map);
					}
					if(StringUtil.isBlank(speId)||assessment.getSpeId().equals(speId))
					{
						List<OscaSubjectStation> stations=oscaAppBiz.getOscaSubjectStations(assessment.getSubjectFlow());
						if(stations!=null&&stations.size()>0)
						{
							if(maxStationSize<stations.size())
								maxStationSize=stations.size();
							for(OscaSubjectStation s:stations) {
								assessStationMap.put(assessment.getClinicalFlow()+s.getOrdinal(),s);
							}
						}
					}
				}
			}
			model.addAttribute("assessStationMap", assessStationMap);//考核有几站
			model.addAttribute("maxStationSize", maxStationSize);
			model.addAttribute("speList", speList);

			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("orgFlow",user.getOrgFlow());
			paramMap.put("speId",speId);
			paramMap.put("selectDate",selectDate);
			paramMap.put("searchStr",searchStr);
			paramMap.put("isPass",isPass);
			PageHelper.startPage(pageIndex, pageSize);
			List<OscaDoctorExamBean> doctorSignInList = oscaAdminAppBiz.getOscaDoctorExamList(paramMap);
			Map<String,Object> examScoreMap = new HashMap<String, Object>();
			Map<String,Object> isDifferMap = new HashMap<String, Object>();
			if(doctorSignInList!=null)
			{
				for(OscaDoctorExamBean b:doctorSignInList)
				{
					List<OscaSkillRoomDoc> docRooms=oscaAppBiz.getOscaSkillRoomDocs(b.getClinicalFlow(),b.getDoctorFlow());
					if(docRooms!=null&&docRooms.size()>0)
					{
						for(OscaSkillRoomDoc doc:docRooms)
						{
							examScoreMap.put(doc.getDoctorFlow()+doc.getClinicalFlow()+doc.getStationFlow(),doc);
							boolean isDiffer=false;
							if(differ>0)
							{
								List<OscaSkillDocTeaScore> skillDocTeaScores=oscaAdminAppBiz.getOscaSkillDocTeaScores(doc.getDoctorFlow(),doc.getClinicalFlow(),doc.getStationFlow());

								if(skillDocTeaScores!=null&&skillDocTeaScores.size()>1)
								{
									isDiffer=getIsDiffer(skillDocTeaScores,differ);
								}
							}
							isDifferMap.put(doc.getDoctorFlow()+doc.getClinicalFlow()+doc.getStationFlow(),isDiffer);
						}
					}else{
						b.setIsSavePass("");
					}
				}
			}
			//获取访问路径前缀
			String uploadBaseUrl = oscaAppBiz.getCfgCode("upload_base_url");
			model.addAttribute("uploadBaseUrl",uploadBaseUrl);
			model.addAttribute("examScoreMap", examScoreMap);
			model.addAttribute("doctorSignInList", doctorSignInList);
			model.addAttribute("isDifferMap", isDifferMap);
			model.addAttribute("dataCount", PageHelper.total);
			return "/res/osca/admin/scoreDetailList";
		}else{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", selectDate+"无考核信息");
			return "/res/osca/admin/scoreDetailList";
		}
	}
	@RequestMapping(value={"/differDetail"},method={RequestMethod.POST})
	public String differDetail(String userFlow,String doctorFlow,String clinicalFlow,String stationFlow, HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/admin/differDetail";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "/res/osca/admin/differDetail";
		}
		if(StringUtil.isBlank(clinicalFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核方案标识符为空");
			return "/res/osca/admin/differDetail";
		}
		if(StringUtil.isBlank(stationFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核站点标识符为空");
			return "/res/osca/admin/differDetail";
		}
		List<OscaSkillDocTeaScore> skillDocTeaScores=oscaAdminAppBiz.getOscaSkillDocTeaScores(doctorFlow,clinicalFlow,stationFlow);
		Map<String,List<OscaSkillDocScore>> socreMap=new HashMap<>();
		if(skillDocTeaScores!=null&&skillDocTeaScores.size()>0)
		{

			for(int i=0;i<skillDocTeaScores.size();i++)
			{
				OscaSkillDocTeaScore perScore=skillDocTeaScores.get(i);
				Map<String,String> param=new HashMap<>();
				param.put("userFlow",perScore.getPartnerFlow());
				param.put("doctorFlow",doctorFlow);
				param.put("stationFlow",stationFlow);
				param.put("clinicalFlow",clinicalFlow);
				List<OscaSkillDocScore> scores=oscaAppBiz.getDocScoreByParam(param);
				socreMap.put(perScore.getPartnerFlow(),scores);
			}
		}
		model.addAttribute("socreMap",socreMap);
		model.addAttribute("skillDocTeaScores",skillDocTeaScores);
		return "/res/osca/admin/differDetail";
	}
	@RequestMapping(value={"/showStationFromScore"},method = {RequestMethod.POST})
	public  String showStationFromScore(String userFlow,String doctorFlow,String partnerFlow,String scoreFlow,Model model)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/osca/admin/selectStationFrom";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/osca/admin/selectStationFrom";
		}
		if(StringUtil.isBlank(partnerFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考官标识符为空");
			return "res/osca/admin/selectStationFrom";
		}
		if(StringUtil.isBlank(scoreFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "成绩标识符为空");
			return "res/osca/admin/selectStationFrom";
		}
		Map<String,String> param=new HashMap<>();
		param.put("userFlow", partnerFlow);
		param.put("doctorFlow", doctorFlow);
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
			param.put("scoreFlow", scoreFlow);
			model.addAttribute("paramMap",param);
			//如果是没有表单的成绩就直接返回页面
			if(score.getIsHaveFrom().equals("N")){
				model.addAttribute("haveFrom","N");
				return "res/osca/admin/selectStationFrom";
			}
			//表单内容
			OscaFrom from = oscaAppBiz.getFromByScoreFlow(scoreFlow);
			model.addAttribute("from", from);
			//固定表单
			model.addAttribute("haveFrom","Y");
			//固定表单
			if(score.getFromTypeId().equals("1")){
				String url=score.getFromUrl();
				if(StringUtil.isBlank(url)){
					model.addAttribute("resultId", "3011101");
					model.addAttribute("resultType", "表单信息不存在");
					return "res/osca/admin/selectStationFrom";
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

				return "res/osca/admin/selectStationFrom";
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
				return "res/osca/admin/selectStationFrom";
			}
		}else{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "成绩信息不存在");
			return "res/osca/admin/selectStationFrom";
		}
	}

	private boolean getIsDiffer(List<OscaSkillDocTeaScore> skillDocTeaScores, Integer differ) {
		if(skillDocTeaScores!=null&&skillDocTeaScores.size()>1&&differ>0)
		{
			for(int i=1;i<=skillDocTeaScores.size();i++)
			{
				OscaSkillDocTeaScore perScore=null;
				OscaSkillDocTeaScore nowScore=null;
				if(i==skillDocTeaScores.size())
				{
					 perScore=skillDocTeaScores.get(i-1);
					 nowScore=skillDocTeaScores.get(0);
				}else {
					 perScore = skillDocTeaScores.get(i - 1);
					 nowScore = skillDocTeaScores.get(i);
				}
				if(perScore!=null&&nowScore!=null&&StringUtil.isNotBlank(perScore.getExamScore())&&StringUtil.isNotBlank(nowScore.getExamScore()))
				{
					if(Double.valueOf(perScore.getExamScore())-Double.valueOf(nowScore.getExamScore())>=differ)
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	@RequestMapping(value={"/stationDetailList"},method={RequestMethod.POST})
	public String stationDetailList(String userFlow,String selectDate,String speId,String isPass,
								  Integer pageIndex,String searchStr,
								  Integer pageSize, HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/admin/stationDetailList";
		}
		if(StringUtil.isBlank(selectDate)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请选择日期");
			return "/res/osca/admin/stationDetailList";
		}
		if(StringUtil.isNotBlank(isPass)&&!"Y".equals(isPass)&&!"N".equals(isPass)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "只能输入合格或不合格");
			return "/res/osca/admin/stationDetailList";
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
			return "/res/osca/admin/stationDetailList";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户信息不存在");
			return "/res/osca/admin/stationDetailList";
		}
		SysOrg org=oscaAppBiz.getOrg(user.getOrgFlow());
		if(org==null)
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您暂无考点信息，无权限使用");
			return "/res/osca/admin/stationDetailList";
		}
		if(!"Y".equals(org.getIsExamOrg()))
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您的帐号不是考点帐号，无权限使用");
			return "/res/osca/admin/stationDetailList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "3030102");
			model.addAttribute("resultType", "起始页为空");
			return "/res/osca/admin/stationDetailList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "页面大小为空");
			return "/res/osca/admin/stationDetailList";
		}
		Map<String,String> maxTimeMap=oscaAdminAppBiz.getSelectDateMaxExamTime(user.getOrgFlow(),selectDate,speId);
		model.addAttribute("maxTimeMap",maxTimeMap);
		//签到及考核
		List<OscaSkillsAssessment> skillsAssessmentList=oscaAdminAppBiz.getSelectDateSkills(user.getOrgFlow(),selectDate);
		if(skillsAssessmentList!=null&&skillsAssessmentList.size()>0) {

			List<Map<String, String>> speList=new ArrayList<>();
			int maxStationSize=0;
			Map<String, OscaSubjectStation> assessStationMap=new HashMap<>();
			List<String> speIds=new ArrayList<>();
			if(skillsAssessmentList!=null&&skillsAssessmentList.size()>0) {
				for(OscaSkillsAssessment assessment:skillsAssessmentList)
				{
					if(!speIds.contains(assessment.getSpeId()))
					{
						speIds.add(assessment.getSpeId());
						Map<String, String> map=new HashMap<>();
						map.put("speId",assessment.getSpeId());
						map.put("speName",assessment.getSpeName());
						speList.add(map);
					}
					if(StringUtil.isBlank(speId)||assessment.getSpeId().equals(speId))
					{
						List<OscaSubjectStation> stations=oscaAppBiz.getOscaSubjectStations(assessment.getSubjectFlow());
						if(stations!=null&&stations.size()>0)
						{
							if(maxStationSize<stations.size())
								maxStationSize=stations.size();
							for(OscaSubjectStation s:stations) {
								assessStationMap.put(assessment.getClinicalFlow()+s.getOrdinal(),s);
							}
						}
					}
				}
			}
			model.addAttribute("assessStationMap", assessStationMap);//考核有几站
			model.addAttribute("maxStationSize", maxStationSize);
			model.addAttribute("speList", speList);

			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("orgFlow",user.getOrgFlow());
			paramMap.put("speId",speId);
			paramMap.put("selectDate",selectDate);
			paramMap.put("searchStr",searchStr);
			paramMap.put("isPass",isPass);
			PageHelper.startPage(pageIndex, pageSize);
			List<OscaDoctorExamBean> doctorSignInList = oscaAdminAppBiz.getOscaDoctorExamList(paramMap);
			Map<String,Object> examScoreMap = new HashMap<String, Object>();
			if(doctorSignInList!=null)
			{
				for(OscaDoctorExamBean b:doctorSignInList)
				{
					List<OscaSkillRoomDoc> docRooms=oscaAppBiz.getOscaSkillRoomDocs(b.getClinicalFlow(),b.getDoctorFlow());
					if(docRooms!=null)
					{
						for(OscaSkillRoomDoc doc:docRooms)
						{
							examScoreMap.put(doc.getDoctorFlow()+doc.getClinicalFlow()+doc.getStationFlow(),doc);
						}
					}
				}
			}
			//获取访问路径前缀
			String uploadBaseUrl = oscaAppBiz.getCfgCode("upload_base_url");
			model.addAttribute("uploadBaseUrl",uploadBaseUrl);
			model.addAttribute("examScoreMap", examScoreMap);
			model.addAttribute("doctorSignInList", doctorSignInList);
			model.addAttribute("dataCount", PageHelper.total);

			model.addAttribute("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
			return "/res/osca/admin/stationDetailList";
		}else{

			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", selectDate+"无考核信息");
			return "/res/osca/admin/stationDetailList";
		}
	}
	@RequestMapping(value={"/noSiginList"},method={RequestMethod.POST})
	public String noSiginList(String userFlow,String selectDate,String searchStr,
						  Integer pageIndex,String clinicalFlow,String recordFlow,
						  Integer pageSize, HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/admin/noSiginList";
		}
		if(StringUtil.isBlank(selectDate)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请选择日期");
			return "/res/osca/admin/noSiginList";
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
			return "/res/osca/admin/noSiginList";
		}
		if(StringUtil.isBlank(clinicalFlow)&&StringUtil.isNotBlank(recordFlow)||StringUtil.isBlank(recordFlow)&&StringUtil.isNotBlank(clinicalFlow))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请选择需要显示未签到学员信息的考核场次");
			return "/res/osca/admin/noSiginList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "3030102");
			model.addAttribute("resultType", "起始页为空");
			return "/res/osca/admin/noSiginList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "页面大小为空");
			return "/res/osca/admin/noSiginList";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户信息不存在");
			return "/res/osca/admin/noSiginList";
		}
		SysOrg org=oscaAppBiz.getOrg(user.getOrgFlow());
		if(org==null)
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您暂无考点信息，无权限使用");
			return "/res/osca/admin/noSiginList";
		}
		if(!"Y".equals(org.getIsExamOrg()))
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您的帐号不是考点帐号，无权限使用");
			return "/res/osca/admin/noSiginList";
		}

		//包装筛选条件
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgFlow",user.getOrgFlow());
		paramMap.put("clinicalFlow",clinicalFlow);
		paramMap.put("recordFlow",recordFlow);
		paramMap.put("selectDate",selectDate);
		paramMap.put("searchStr",searchStr);
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,String>> doctList = oscaAdminAppBiz.getNoSiginList(paramMap);
		model.addAttribute("doctList", doctList);
		model.addAttribute("dataCount", PageHelper.total);
		return "/res/osca/admin/noSiginList";

	}
	@RequestMapping(value={"/teaInfo"},method={RequestMethod.POST})
	public String teaInfo(String userFlow,String selectDate,String searchStr,
						  Integer pageIndex,
						  Integer pageSize, HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/admin/teaInfo";
		}
		if(StringUtil.isBlank(selectDate)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请选择日期");
			return "/res/osca/admin/teaInfo";
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
			return "/res/osca/admin/teaInfo";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "3030102");
			model.addAttribute("resultType", "起始页为空");
			return "/res/osca/admin/teaInfo";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "页面大小为空");
			return "/res/osca/admin/teaInfo";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户信息不存在");
			return "/res/osca/admin/teaInfo";
		}
		SysOrg org=oscaAppBiz.getOrg(user.getOrgFlow());
		if(org==null)
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您暂无考点信息，无权限使用");
			return "/res/osca/admin/teaInfo";
		}
		if(!"Y".equals(org.getIsExamOrg()))
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您的帐号不是考点帐号，无权限使用");
			return "/res/osca/admin/teaInfo";
		}

		//包装筛选条件
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgFlow",user.getOrgFlow());
		paramMap.put("selectDate",selectDate);
		paramMap.put("searchStr",searchStr);
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,String>> teaList = oscaAdminAppBiz.getTeaList(paramMap);
		model.addAttribute("teaList", teaList);
		model.addAttribute("dataCount", PageHelper.total);
		return "/res/osca/admin/teaInfo";

	}

	@RequestMapping(value={"/rooms"},method={RequestMethod.POST})
	public String rooms(String userFlow, HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/admin/rooms";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户信息不存在");
			return "/res/osca/admin/rooms";
		}
		SysOrg org=oscaAppBiz.getOrg(user.getOrgFlow());
		if(org==null)
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您暂无考点信息，无权限使用");
			return "/res/osca/admin/rooms";
		}
		if(!"Y".equals(org.getIsExamOrg()))
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您的帐号不是考点帐号，无权限使用");
			return "/res/osca/admin/rooms";
		}
		List<SysDict> rooms=oscaAdminAppBiz.getOrgRooms(org.getOrgFlow());
		model.addAttribute("rooms",rooms);
		return "/res/osca/admin/rooms";
	}
	@RequestMapping(value={"/getRoomFile"},method={RequestMethod.POST})
	public String getRoomFile(String userFlow,String roomFlow, HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "/res/osca/admin/getRoomFile";
		}
		if(StringUtil.isBlank(roomFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "房间标识符为空");
			return "/res/osca/admin/getRoomFile";
		}
		SysUser user=oscaAppBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户信息不存在");
			return "/res/osca/admin/getRoomFile";
		}
		SysOrg org=oscaAppBiz.getOrg(user.getOrgFlow());
		if(org==null)
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您暂无考点信息，无权限使用");
			return "/res/osca/admin/getRoomFile";
		}
		if(!"Y".equals(org.getIsExamOrg()))
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "您的帐号不是考点帐号，无权限使用");
			return "/res/osca/admin/getRoomFile";
		}
		OscaRoomFile roomFile=oscaAppBiz.getRoomFile(roomFlow,user.getOrgFlow());
		if(roomFile==null||StringUtil.isBlank(roomFile.getFileFlow()))
		{
			model.addAttribute("resultId", "30197");
			model.addAttribute("resultType", "未设置屏显");
			return "/res/osca/admin/getRoomFile";
		}
		PubFile pubFile=oscaAppBiz.readFile(roomFile.getFileFlow());

		String uploadBaseUrl = oscaAppBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl",uploadBaseUrl);
		model.addAttribute("file",pubFile);
		return "/res/osca/admin/getRoomFile";
	}

	private void setCount(Map<String, Integer> countMap, String key) {
		if(countMap!=null)
		{
			Integer count=countMap.get(key);
			if(count==null)
				count=0;
			count++;
			countMap.put(key,count);
		}
	}

	private List<Map<String, String>> getSpeList(List<OscaSkillsAssessment> skillsAssessmentList) {

		List<Map<String, String>> list=new ArrayList<>();
		List<String> speIds=new ArrayList<>();
		if(skillsAssessmentList!=null&&skillsAssessmentList.size()>0) {
			for(OscaSkillsAssessment assessment:skillsAssessmentList)
			{
				if(!speIds.contains(assessment.getSpeId()))
				{
					speIds.add(assessment.getSpeId());
					Map<String, String> map=new HashMap<>();
					map.put("speId",assessment.getSpeId());
					map.put("speName",assessment.getSpeName());
					list.add(map);
				}
			}
		}
		return list;
	}
}

