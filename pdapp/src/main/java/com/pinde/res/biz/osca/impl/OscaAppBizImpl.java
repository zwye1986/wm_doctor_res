package com.pinde.res.biz.osca.impl;


import com.pinde.app.common.GlobalUtil;
import com.pinde.core.common.GlobalConstant;
import com.pinde.core.common.PasswordHelper;
import com.pinde.core.model.*;
import com.pinde.core.model.SysUserExample.Criteria;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.osca.IOscaAppBiz;
import com.pinde.res.biz.osca.IOscaDoctorRegistBiz;
import com.pinde.res.ctrl.osca.OscaCompartor;
import com.pinde.res.dao.jswjw.ext.OscaDoctorAssessmentExtMapper;
import com.pinde.res.dao.jswjw.ext.OscaSkillRoomDocExtMapper;
import com.pinde.res.dao.jswjw.ext.OscaSkillRoomExtMapper;
import com.pinde.res.dao.jswjw.ext.OscaSubjectMainExtMapper;
import com.pinde.res.dao.osca.ext.OscaAppMapper;
import com.pinde.res.enums.osca.AuditStatusEnum;
import com.pinde.res.enums.osca.DoctorScoreEnum;
import com.pinde.res.enums.osca.ScanDocStatusEnum;
import com.pinde.res.enums.osca.ScoreStatusEnum;
import com.pinde.res.model.jswjw.mo.FromItem;
import com.pinde.res.model.jswjw.mo.FromTitle;
import com.pinde.sci.dao.base.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.*;

@Service
//@Transactional(rollbackFor=Exception.class)
public class OscaAppBizImpl implements IOscaAppBiz {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private OscaSubjectStationFromMapper stationFromMapper;
	@Autowired
	private OscaSkillScoreDeatilMapper scoreDeatilMapper;
	@Autowired
	private ResDoctorMapper doctorMapper;
	@Autowired
	private SysCfgMapper cfgMapper;
	@Autowired
	private SysOrgMapper orgMapper;
	@Autowired
	private OscaDoctorAssessmentMapper doctorAssessmentMapper;
	@Autowired
	private OscaAppMapper oscaAppMapper;
	@Autowired
	private SysUserRoleMapper userRoleMapper;
	@Autowired
	private InxInfoMapper inxInfoMapper;
	@Autowired
	private OscaTeaScanDocMapper scanDocMapper;
	@Autowired
	private OscaSkillDocTeaScoreMapper teaDocScoreMapper;
	@Autowired
	private OscaExamDifferScoreMapper differScoreMapper;
	@Autowired
	private OscaSkillDocScoreMapper scoreMapper;
	@Autowired
	private OscaFromMapper fromMapper;
	@Autowired
	private OscaSubjectPartScoreMapper partScoreMapper;
	@Autowired
	private OscaSubjectStationScoreMapper stationScoreMapper;
	@Autowired
	private SysOrgMapper sysOrgMapper;
	@Autowired
	private SysDictMapper sysDictMapper;
	@Autowired
	private OscaDoctorRegistMapper oscaDoctorRegistMapper;
	@Autowired
	private IOscaDoctorRegistBiz oscaDoctorRegistBiz;

	@Autowired
	private OscaDoctorAssessmentMapper oscaDoctorAssessmentMapper;
	@Autowired
	private OscaDoctorAssessmentExtMapper oscaDoctorAssessmentExtMapper;
	@Autowired
	private OscaSkillsAssessmentMapper skillsAssessmentMapper;
	@Autowired
	private OscaSubjectMainExtMapper oscaSubjectMainExtMapper;
	@Autowired
	private OscaSubjectMainMapper oscaSubjectMainMapper;
	@Autowired
	private OscaSubjectStationMapper stationMapper;
	@Autowired
	private OscaSkillRoomMapper roomMapper;
	@Autowired
	private OscaRoomFileMapper roomFileMapper;
	@Autowired
	private OscaSkillDocStationMapper docStationMapper;
	@Autowired
	private OscaSkillRoomExtMapper roomExtMapper;

	@Autowired
	private OscaSkillRoomDocExtMapper roomDocExtMapper;
	@Autowired
	private OscaSkillRoomDocMapper roomDocMapper;
	@Autowired
	private PubFileMapper fileMapper;
	@Override
	public SysUser findByUserCode(String userCode) {
		SysUserExample sysUserExample=new SysUserExample();
		Criteria criteria=sysUserExample.createCriteria();
		criteria.andUserCodeEqualTo(userCode);
		List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}		
		return null;
	}
	@Override
	public ResDoctor readResDoctor(String userFlow) {
		return doctorMapper.selectByPrimaryKey(userFlow);
	}
	@Override
	public SysUser readSysUser(String userFlow) {
		return sysUserMapper.selectByPrimaryKey(userFlow);
	}


	//获取系统配置信息
	@Override
	public String getCfgCode(String code){
		if(StringUtil.isNotBlank(code)){
			String v= GlobalUtil.getLocalCfgMap().get(code);
			if(StringUtil.isNotBlank(v))
				return v;
			SysCfg cfg = cfgMapper.selectByPrimaryKey(code);
			if(cfg!=null){
				String val = cfg.getCfgValue();
				if(!StringUtil.isNotBlank(val)){
					val = cfg.getCfgBigValue();
				}
				return val;
			}
		}
		return null;
	}
	@Override
	public SysOrg getOrg(String orgFlow) {
		return orgMapper.selectByPrimaryKey(orgFlow);
	}

	@Override
	public OscaSkillsAssessment getOscaSkillsAssessmentByFlow(String clinicalFlow) {
		return skillsAssessmentMapper.selectByPrimaryKey(clinicalFlow);
	}

	@Override
	public OscaDoctorAssessment getOscaDoctorAssessmentByFlow(String recordFlow) {
		return doctorAssessmentMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public List<OscaSubjectStation> getTeaDocStation(Map<String, String> paramMap) {
		return oscaAppMapper.getTeaDocStation(paramMap);
	}
	@Override
	public List<OscaSubjectStation> getTeaSubDocStation(Map<String, String> paramMap) {
		return oscaAppMapper.getTeaSubDocStation(paramMap);
	}
	@Override
	public void updateDoctorAssessment(String clinicalFlow, String doctorFlow,SysUser user) {
		OscaDoctorAssessment doctorAssessment=getOscaDoctorAssessment(clinicalFlow,doctorFlow);
		OscaSkillsAssessment skillAssess=getOscaSkillsAssessmentByFlow(clinicalFlow);
		//学员当前考核信息的所有考站信息
		List<OscaSkillRoomDoc> roomDocs=getOscaSkillRoomDocs(clinicalFlow,doctorFlow);
		boolean f=false;
		if(doctorAssessment!=null&&skillAssess!=null){
			if(roomDocs!=null&&roomDocs.size()>0) {
				OscaSubjectMain main = getOscaSubjectMain(skillAssess.getSubjectFlow());
				//总分
				Integer allScore=main.getAllScore();
				//每一部分的分数
				Map<String,Integer> partPassScoreMap=new HashMap<>();
				List<OscaSubjectPartScore> partScores=getOscaSubjectPartScores(skillAssess.getSubjectFlow());
				if(partScores!=null)
				{
					for(OscaSubjectPartScore partScore:partScores)
					{
						partPassScoreMap.put(partScore.getPartFlow(),partScore.getPartScore());
					}
				}
				//每一站的合格分
				Map<String,Integer> stationPassScoreMap=new HashMap<>();
				List<OscaSubjectStationScore> stationScores=getOscaSubjectStationScores(skillAssess.getSubjectFlow());
				if(stationScores!=null)
				{
					for(OscaSubjectStationScore stationScore:stationScores)
					{
						stationPassScoreMap.put(stationScore.getStationFlow(),stationScore.getStationScore());
					}
				}

				//获取考核方案下的站点
				List<OscaSubjectStation> stations = getOscaSubjectStations(skillAssess.getSubjectFlow());

				if (stations != null) {
					//如果学员所有站点都没有考核完，直接是不通过
					if (roomDocs.size() < stations.size()) {
                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
						doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
						doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
						f=true;
					}else {
						//每部分的站点数量
						Map<String, List<OscaSubjectStation>> partMap = new HashMap<>();
						for (OscaSubjectStation s : stations) {
							List<OscaSubjectStation> temp = partMap.get(s.getPartFlow());
							if (temp == null)
								temp = new ArrayList<>();
							temp.add(s);
							partMap.put(s.getPartFlow(), temp);
						}
						//考核总分
						double examAllScore = 0;
						//考核分数
						Map<String, Double> examScoreMap = new HashMap<>();
						for (OscaSkillRoomDoc d : roomDocs) {
							Double examScore = 0.0;
							if (StringUtil.isNotBlank(d.getExamScore())) {
								examScore=Double.valueOf(d.getExamScore());
							}
							examAllScore += examScore;
							examScoreMap.put(d.getStationFlow(), examScore);
						}
						//如果合格总分配置了 并且 考核 总分小于合格总分 直接不通过
						if(allScore!=null&&allScore>examAllScore)
						{
                            doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
							doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
							doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
							f=true;
						}else {
							//有几部分
							int partCount = partMap.size();
							int partPassCount = 0;
							if (partCount > 0) {
								//计算每一部分的总分，考核总分，以及是否通过
								for (String key : partMap.keySet()) {
									List<OscaSubjectStation> temp = partMap.get(key);
									double examAll = 0;
									for (OscaSubjectStation s : temp) {
										Double examScore = examScoreMap.get(s.getStationFlow());
										examAll += examScore;
									}
									Integer partPassScore=partPassScoreMap.get(key);
									//如果未配置 或 每一部分部分大于每一部分考核总分 就算合格
									if(partPassScore==null||examAll>=partPassScore)
									{
										partPassCount++;
									}
								}
								//所有部分都合格 则计算每一站是否都合格
								if (partCount == partPassCount) {
									int stationPassCount=0;
									for (OscaSubjectStation s : stations) {
										Integer stationPassScore=stationPassScoreMap.get(s.getStationFlow());
										Double examScore = examScoreMap.get(s.getStationFlow());
										//如果未配置 或 每一部分部分大于每一部分考核总分 就算合格
										if (examScore!=null&&(stationPassScore == null || examScore >= stationPassScore) )
										{
											stationPassCount++;
										}
									}
									if(stationPassCount==stations.size()) {
                                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_Y);
										doctorAssessment.setIsPass(DoctorScoreEnum.Passed.getId());
										doctorAssessment.setIsPassName(DoctorScoreEnum.Passed.getName());
										f = true;
									}else{
                                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
										doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
										doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
										f=true;
									}
								}else{
                                    doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
									doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
									doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
									f=true;
								}
							}
						}

					}
				}
			}
		}
		if(f){
			editOscaDoctorAssessment(doctorAssessment,user);
		}
	}
	@Override
	public void updateDoctorAssessmentSavePass(String clinicalFlow, String doctorFlow,SysUser user) {
		OscaDoctorAssessment doctorAssessment=getOscaDoctorAssessment(clinicalFlow,doctorFlow);
		OscaSkillsAssessment skillAssess=getOscaSkillsAssessmentByFlow(clinicalFlow);
		//学员当前考核信息的所有考站信息
		List<OscaSkillRoomDoc> roomDocs=getOscaSkillRoomDocs(clinicalFlow,doctorFlow);
		boolean f=false;
		if(doctorAssessment!=null&&skillAssess!=null){
			if(roomDocs!=null&&roomDocs.size()>0) {
				OscaSubjectMain main = getOscaSubjectMain(skillAssess.getSubjectFlow());
				//总分
				Integer allScore=main.getAllScore();
				//每一部分的分数
				Map<String,Integer> partPassScoreMap=new HashMap<>();
				List<OscaSubjectPartScore> partScores=getOscaSubjectPartScores(skillAssess.getSubjectFlow());
				if(partScores!=null)
				{
					for(OscaSubjectPartScore partScore:partScores)
					{
						partPassScoreMap.put(partScore.getPartFlow(),partScore.getPartScore());
					}
				}
				//每一站的合格分
				Map<String,Integer> stationPassScoreMap=new HashMap<>();
				List<OscaSubjectStationScore> stationScores=getOscaSubjectStationScores(skillAssess.getSubjectFlow());
				if(stationScores!=null)
				{
					for(OscaSubjectStationScore stationScore:stationScores)
					{
						stationPassScoreMap.put(stationScore.getStationFlow(),stationScore.getStationScore());
					}
				}

				//获取考核方案下的站点
				List<OscaSubjectStation> stations = getOscaSubjectStations(skillAssess.getSubjectFlow());

				if (stations != null) {
					//如果学员所有站点都没有考核完，直接是不通过
					if (roomDocs.size() < stations.size()) {
                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
						f=true;
					}else {
						//每部分的站点数量
						Map<String, List<OscaSubjectStation>> partMap = new HashMap<>();
						for (OscaSubjectStation s : stations) {
							List<OscaSubjectStation> temp = partMap.get(s.getPartFlow());
							if (temp == null)
								temp = new ArrayList<>();
							temp.add(s);
							partMap.put(s.getPartFlow(), temp);
						}
						//考核总分
						double examAllScore = 0;
						//考核分数
						Map<String, Double> examScoreMap = new HashMap<>();
						for (OscaSkillRoomDoc d : roomDocs) {
							Double examScore = 0.0;
							if (StringUtil.isNotBlank(d.getExamSaveScore())) {
								examScore=Double.valueOf(d.getExamSaveScore());
							}
							examAllScore += examScore;
							examScoreMap.put(d.getStationFlow(), examScore);
						}
						//如果合格总分配置了 并且 考核 总分小于合格总分 直接不通过
						if(allScore!=null&&allScore>examAllScore)
						{
                            doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
							f=true;
						}else {
							//有几部分
							int partCount = partMap.size();
							int partPassCount = 0;
							if (partCount > 0) {
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
										partPassCount++;
									}
								}
								//所有部分都合格 则计算每一站是否都合格
								if (partCount == partPassCount) {
									int stationPassCount=0;
									for (OscaSubjectStation s : stations) {
										Integer stationPassScore=stationPassScoreMap.get(s.getStationFlow());
										Double examScore = examScoreMap.get(s.getStationFlow());
										if(examScore==null) examScore=0.0;
										//如果未配置 或 每一部分部分大于每一部分考核总分 就算合格
										if (examScore!=null&&(stationPassScore == null || examScore >= stationPassScore) )
										{
											stationPassCount++;
										}
									}
									if(stationPassCount==stations.size()) {
                                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_Y);
										f = true;
									}else{
                                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
										f=true;
									}
								}else{
                                    doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
									f=true;
								}
							}
						}

					}
				}
			}
		}
		if(f){
			editOscaDoctorAssessment(doctorAssessment,user);
		}
	}
	@Override
	public List<OscaSubjectStationScore> getOscaSubjectStationScores(String subjectFlow) {
		if(StringUtil.isNotBlank(subjectFlow))
		{
			OscaSubjectStationScoreExample example=new OscaSubjectStationScoreExample();
			OscaSubjectStationScoreExample.Criteria criteria =example.createCriteria();
            criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSubjectFlowEqualTo(subjectFlow);
			List<OscaSubjectStationScore> list=stationScoreMapper.selectByExample(example);
			return list;
		}
		return null;
	}

	@Override
	public List<OscaSubjectPartScore> getOscaSubjectPartScores(String subjectFlow) {
		if(StringUtil.isNotBlank(subjectFlow))
		{
			OscaSubjectPartScoreExample example=new OscaSubjectPartScoreExample();
			OscaSubjectPartScoreExample.Criteria criteria =example.createCriteria();
            criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSubjectFlowEqualTo(subjectFlow);
			List<OscaSubjectPartScore> list=partScoreMapper.selectByExample(example);
			return list;
		}
		return null;
	}

	public void updateDoctorAssessmentOld(String clinicalFlow, String doctorFlow,SysUser user) {
		OscaDoctorAssessment doctorAssessment=getOscaDoctorAssessment(clinicalFlow,doctorFlow);
		OscaSkillsAssessment skillAssess=getOscaSkillsAssessmentByFlow(clinicalFlow);
		//学员当前考核信息的所有考站信息
		List<OscaSkillRoomDoc> roomDocs=getOscaSkillRoomDocs(clinicalFlow,doctorFlow);
		boolean f=false;
		if(doctorAssessment!=null&&skillAssess!=null){
			if(roomDocs!=null&&roomDocs.size()>0) {
				OscaSubjectMain main = getOscaSubjectMain(skillAssess.getSubjectFlow());
				//根据部分去计算是否通过考核
				//百分比取 main中的百分比
				Integer passPer=main.getPassPer();
				if(passPer==null||passPer==0)
				{
					passPer=60;
				}
				List<OscaSubjectStation> stations = getOscaSubjectStations(skillAssess.getSubjectFlow());

				if (stations != null) {
					if (roomDocs.size() < stations.size()) {
                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
						doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
						doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
						f=true;
					}
					//拆分部分 每个部分有几个站 总分
					Map<String, Object> stationScoreMap = new HashMap<>();
					Map<String,List<OscaSubjectStation>> partMap=new HashMap<>();
					for (OscaSubjectStation s : stations) {
						stationScoreMap.put(s.getStationFlow(), s.getStationScore());
						List<OscaSubjectStation> temp=partMap.get(s.getPartFlow());
						if(temp==null)
							temp=new ArrayList<>();
						temp.add(s);
						partMap.put(s.getPartFlow(),temp);
					}
					//考核分数
					Map<String,String> examScoreMap=new HashMap<>();
					for (OscaSkillRoomDoc d : roomDocs) {
						examScoreMap.put(d.getStationFlow(), d.getExamScore());
					}
					//有几部分
					int partCount=partMap.size();
					int partPassCount=0;
					if(partCount>0){
						//计算每一部分的总分，考核总分，以及是否通过
						for(String key:partMap.keySet())
						{
							List<OscaSubjectStation> temp=partMap.get(key);
							double scoreAll=0;
							double examAll=0;
							for(OscaSubjectStation s:temp)
							{
								int stationScore = (int) stationScoreMap.get(s.getStationFlow());
								scoreAll+=stationScore;
								String examScore=examScoreMap.get(s.getStationFlow());
								if(StringUtil.isNotBlank(examScore))
								{
									double score = Double.valueOf(examScore);
									examAll+=score;
								}
								else
								{
									examAll+=0;
								}
							}
							if(examAll!=0)
							{
								if(examAll>=scoreAll*passPer/100)
								{
									partPassCount++;
								}
							}
						}
					}
					//通过的部分为0
					if(partPassCount==0)
					{
                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
						doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
						doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
						f=true;
					}else if(partCount==partPassCount)
					{
                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_Y);
						doctorAssessment.setIsPass(DoctorScoreEnum.Passed.getId());
						doctorAssessment.setIsPassName(DoctorScoreEnum.Passed.getName());
						f=true;
					}else if(partCount>partPassCount)
					{
                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
						doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
						doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
						f=true;
					}
				}
			}
		}
		if(f){
			editOscaDoctorAssessment(doctorAssessment,user);
		}
	}

	@Override
	public void scoreBatchSubmit(String []flow,String userFlow,String doctorFlow) {

		SysUser tea=readSysUser(userFlow);
		for(String scoreFlow:flow) {
			//提交成绩
			OscaSkillDocScore score = getOscaSkillDocScoreByFlow(scoreFlow);
			if(score!=null&&score.getStatusId().equals(ScoreStatusEnum.Save.getId())){
				score.setStatusId(ScoreStatusEnum.Submit.getId());
				score.setStatusName(ScoreStatusEnum.Submit.getName());
				editOscaSkillDocScore(score,tea);
				//计算站点的平均值
				String clinicalFlow=score.getClinicalFlow();
				String stationFlow=score.getStationFlow();
				Map<String, String> param=new HashMap<>();
				param.put("userFlow", userFlow);
				param.put("doctorFlow", doctorFlow);
				param.put("clinicalFlow", clinicalFlow);
				param.put("stationFlow", stationFlow);
				////设置当前站点学员已经考核
				OscaSkillRoomDoc docRoom=getOscaSkillRoomDocByDoc(param);
                if (docRoom != null && docRoom.getIsAdminAudit().equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
					//获取所有站点老师提交的总成绩，以及有多少老师提交了成绩
					Map<String,Object> map=getDocStationAllScore(param);
					String avgScore=(String) map.get("STATION_AVG_SCORE");
					if(StringUtil.isNotBlank(avgScore))
					{
						docRoom.setExamScore(avgScore);
					}else{
						docRoom.setExamScore("0");
					}
					editOscaSkillRoomDoc(docRoom,tea);
					//修改预约信息表的状态
					updateDoctorAssessment(clinicalFlow,score.getDoctorFlow(),tea);
				}
				updateTeaScanDocStatus(doctorFlow,clinicalFlow,tea);
			}
		}
	}

	@Override
	public List<PubFile> findStationFiles(String stationFlow, String orgFlow) {
		if(StringUtil.isBlank(orgFlow)) {
			return null;
		}
		return  oscaAppMapper.findStationFiles(stationFlow,orgFlow);
	}

	@Override
	public List<PubFile> findClinicalStationFiles(String stationFlow, String clinicalFlow, String orgFlow) {
		if(StringUtil.isBlank(orgFlow)) {
			return null;
		}
		return  oscaAppMapper.findClinicalStationFiles(stationFlow,clinicalFlow,orgFlow);
	}

	@Override
	public List<OscaSkillRoom> findClinicalStationRooms(String stationFlow, String clinicalFlow, String orgFlow) {
		OscaSkillRoomExample example=new OscaSkillRoomExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andStationFlowEqualTo(stationFlow)
				.andClinicalFlowEqualTo(clinicalFlow);
		example.setOrderByClause("room_flow");
		return roomMapper.selectByExample(example);
	}

	@Override
	public PubFile readFile(String fileFlow) {
		return fileMapper.selectByPrimaryKey(fileFlow);
	}

	@Override
	public OscaRoomFile getRoomFile(String roomFlow, String orgFlow) {
		OscaRoomFileExample example=new OscaRoomFileExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRoomFlowEqualTo(roomFlow)
				.andOrgFlowEqualTo(orgFlow);
		List<OscaRoomFile> files=roomFileMapper.selectByExample(example);
		if(files!=null&&files.size()>0)
		{
			return files.get(0);
		}
		return null;
	}

    @Override
    public OscaSkillDocStation getDocSkillStation(String stationFlow, String userFlow, String clinicalFlow) {
		OscaSkillDocStationExample example=new OscaSkillDocStationExample();

        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andStationFlowEqualTo(stationFlow)
				.andDoctorFlowEqualTo(userFlow).andClinicalFlowEqualTo(clinicalFlow);
		List<OscaSkillDocStation> files=docStationMapper.selectByExample(example);
		if(files!=null&&files.size()>0)
		{
			return files.get(0);
		}
		return null;
    }
    @Override
    public List<OscaSkillDocStation> getDocSkillStations(String userFlow, String clinicalFlow) {
		OscaSkillDocStationExample example=new OscaSkillDocStationExample();

        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(userFlow).andClinicalFlowEqualTo(clinicalFlow);
		return docStationMapper.selectByExample(example);
    }

	@Override
	public void saveDocSkillStations(List<OscaSkillDocStation> docStations, SysUser user) {
		if(docStations!=null)
		{
			for(OscaSkillDocStation docStation:docStations)
			{
				saveDocStation(docStation,user);
			}
		}
	}

	@Override
	public void saveDocStation(OscaSkillDocStation docStation, SysUser saveuser) {
		if(StringUtil.isNotBlank(docStation.getRecordFlow())){
			docStation.setModifyUserFlow(saveuser.getUserFlow());
			docStation.setModifyTime(DateUtil.getCurrDateTime());
			docStationMapper.updateByPrimaryKeySelective(docStation);
		}else{
			docStation.setRecordFlow(PkUtil.getUUID());
            docStation.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			docStation.setCreateUserFlow(saveuser.getUserFlow());
			docStation.setCreateTime(DateUtil.getCurrDateTime());
			docStation.setModifyUserFlow(saveuser.getUserFlow());
			docStation.setModifyTime(DateUtil.getCurrDateTime());
			docStationMapper.insertSelective(docStation);
		}
	}

    @Override
    public int saveRoomFile(OscaRoomFile roomFile, SysUser user) {
		if(roomFile!=null){
			if(StringUtil.isNotBlank(roomFile.getRecordFlow())){//修改
                roomFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				roomFile.setModifyUserFlow(user.getUserFlow());
				roomFile.setModifyTime(DateUtil.getCurrDateTime());
				return this.roomFileMapper.updateByPrimaryKeySelective(roomFile);
			}else{//新增
				roomFile.setRecordFlow(PkUtil.getUUID());
                roomFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				roomFile.setCreateUserFlow(user.getUserFlow());
				roomFile.setCreateTime(DateUtil.getCurrDateTime());
				roomFile.setModifyUserFlow(user.getUserFlow());
				roomFile.setModifyTime(DateUtil.getCurrDateTime());
				return this.roomFileMapper.insertSelective(roomFile);
			}
		}
		return 0;
    }

    @Override
	public List<OscaSubjectStationFrom> getStationExamFroms(Map<String, String> param) {
		return oscaAppMapper.getStationExamFroms(param);
	}

	@Override
	public OscaFrom getFromByScoreFlow(String recordFlow) {
		return oscaAppMapper.getFromByScoreFlow(recordFlow);
	}

	@Override
	public void updateDocSaveStatus(String doctorFlow, SysUser tea) {
		List<String> list=new ArrayList<>();
		list.add(doctorFlow);
		List<OscaSkillDocScore> scores=getTeaDocScores(tea.getUserFlow(),list);
		if(scores!=null&&scores.size()>0){
			for(OscaSkillDocScore score:scores){
				//提交成绩
				if(score!=null){
					//计算站点的平均值
					String clinicalFlow=score.getClinicalFlow();
					String stationFlow=score.getStationFlow();
					Map<String, String> param=new HashMap<>();
					param.put("userFlow", tea.getUserFlow());
					param.put("doctorFlow", score.getDoctorFlow());
					param.put("clinicalFlow", clinicalFlow);
					param.put("stationFlow", stationFlow);
					////设置当前站点学员已经考核
					OscaSkillRoomDoc docRoom=getOscaSkillRoomDocByDoc(param);
                    if (docRoom != null && docRoom.getIsAdminAudit().equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
						//获取所有站点老师保存的总成绩，以及有多少老师保存了成绩
						Map<String,Object> map=getDocStationAllSaveScore(param);
						String avgScore=(String) map.get("STATION_AVG_SCORE");
						if(StringUtil.isNotBlank(avgScore))
						{
							docRoom.setExamSaveScore(avgScore);
						}else{
							docRoom.setExamSaveScore("");
						}
						editOscaSkillRoomDoc(docRoom,tea);
						updateDoctorAssessmentSavePass(clinicalFlow,score.getDoctorFlow(),tea);
					}
					//当前站点
					OscaSkillDocTeaScore teaScore=getOscaSkillRoomTeaScoreByDoc(param);
					if(teaScore!=null)
					{

						Map<String,Object> map=getDocStationAllSaveScoreByParenterFlow(param);
						String avgScore=(String) map.get("STATION_AVG_SCORE");
						if(StringUtil.isNotBlank(avgScore))
						{
							teaScore.setExamScore(avgScore);
						}else{
							teaScore.setExamScore("");
						}
						editOscaSkillRoomTeaScore(teaScore,tea);
					}
				}
			}
		}
	}

	private Map<String,Object> getDocStationAllSaveScoreByParenterFlow(Map<String, String> param) {

		return roomDocExtMapper.getDocStationAllSaveScoreByParenterFlow(param);
	}

	@Override
	public OscaSkillDocTeaScore getOscaSkillRoomTeaScoreByDoc(Map<String, String> param) {
		List<OscaSkillDocTeaScore> scores=oscaAppMapper.getOscaSkillRoomTeaScoreByDoc(param);
		if(scores!=null&&!scores.isEmpty())
		{
			return scores.get(0);
		}
		return null;
	}

	@Override
	public void scoreBatchSubmit(String userFlow,String []flows) {

		List<String> list=new ArrayList<>();
		for(String key:flows)
		{
			list.add(key);
		}
		SysUser tea=readSysUser(userFlow);
		List<OscaSkillDocScore> scores=getTeaDocScores(userFlow,list);
		if(scores!=null&&scores.size()>0){
			for(OscaSkillDocScore score:scores){
				//提交成绩
				if(score!=null&&score.getStatusId().equals(ScoreStatusEnum.Save.getId())){
					score.setStatusId(ScoreStatusEnum.Submit.getId());
					score.setStatusName(ScoreStatusEnum.Submit.getName());
					editOscaSkillDocScore(score,tea);
					//计算站点的平均值
					String clinicalFlow=score.getClinicalFlow();
					String stationFlow=score.getStationFlow();
					Map<String, String> param=new HashMap<>();
					param.put("userFlow", userFlow);
					param.put("doctorFlow", score.getDoctorFlow());
					param.put("clinicalFlow", clinicalFlow);
					param.put("stationFlow", stationFlow);
					////设置当前站点学员已经考核
					OscaSkillRoomDoc docRoom=getOscaSkillRoomDocByDoc(param);
                    if (docRoom != null && docRoom.getIsAdminAudit().equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
						//获取所有站点老师提交的总成绩，以及有多少老师提交了成绩,无表单的成绩不折算
						Map<String,Object> map=getDocStationAllScore(param);
						String avgScore=(String) map.get("STATION_AVG_SCORE");
						if(StringUtil.isNotBlank(avgScore))
						{
							docRoom.setExamScore(avgScore);
							docRoom.setExamSaveScore(avgScore);
						}else{
							docRoom.setExamScore("0");
							docRoom.setExamSaveScore("0");
						}
						editOscaSkillRoomDoc(docRoom,tea);
						//修改预约信息表的状态
						updateDoctorAssessment(clinicalFlow,score.getDoctorFlow(),tea);
					}
					updateTeaScanDocStatus( score.getDoctorFlow(),clinicalFlow,tea);
				}
			}
		}
	}
	@Override
	public void updateTeaScanDocStatus(String doctorFlow, String clinicalFlow,SysUser user) {
		//查询学员当前状态
		//查询当前考官可以考核当前学员学员哪些站点
		Map<String,String> p = new HashMap<String,String>();
		p.put("doctorFlow",doctorFlow);
		p.put("clinicalFlow",clinicalFlow);
		p.put("userFlow",user.getUserFlow());
		List<OscaSubjectStation> stations=getTeaDocStation(p);
		//换考官后 再次查看学员
		if(stations==null||stations.size()<=0)
		{
			stations=getTeaSubDocStation(p);
		}
		String statusId= ScanDocStatusEnum.StayAssessment.getId();
		String statusName=ScanDocStatusEnum.StayAssessment.getName();
		if(stations!=null&&stations.size()>0) {
			int NotHaveScore = 0;
			int SaveScore = 0;
			int SubmitScore = 0;
			for (int i = 0; i < stations.size(); i++) {
				OscaSubjectStation s = stations.get(i);
				//查询当前站点考官可以考核学员的考场
				Map<String, String> param = new HashMap<>();
				param.put("userFlow", user.getUserFlow());
				param.put("doctorFlow", doctorFlow);
				param.put("stationFlow", s.getStationFlow());
				param.put("clinicalFlow", clinicalFlow);
				//当前站点考官是否给学员打过分
				List<OscaSkillDocScore> scores=getDocScoreByParam(param);
				if(scores!=null&&!scores.isEmpty())
				{
					String examScoreStatusId="";
					int submitCount=0;
					int saveCount=0;
					for(OscaSkillDocScore score:scores) {
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
					if (StringUtil.isBlank(examScoreStatusId)) {
						NotHaveScore++;
					} else if (ScoreStatusEnum.Save.getId().equals(examScoreStatusId)) {
						SaveScore++;
					} else if (ScoreStatusEnum.Submit.getId().equals(examScoreStatusId)) {
						SubmitScore++;
					}
				}else{
					NotHaveScore++;
				}
			}
			if (SaveScore > 0) {
				statusId=ScanDocStatusEnum.NotSubmit.getId();
				statusName=ScanDocStatusEnum.NotSubmit.getName();
			}
			if (SubmitScore == stations.size()) {
				statusId=ScanDocStatusEnum.Submit.getId();
				statusName=ScanDocStatusEnum.Submit.getName();
			}
			if (NotHaveScore > 0) {
				statusId=ScanDocStatusEnum.StayAssessment.getId();
				statusName=ScanDocStatusEnum.StayAssessment.getName();
			}
		}

		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("userFlow",user.getUserFlow());
		paramMap.put("clinicalFlow", clinicalFlow);
		paramMap.put("doctorFlow", doctorFlow);
		OscaTeaScanDoc doc=getOscaTeaScanDoc(paramMap);
		if(doc!=null)
		{
			doc.setStatusId(statusId);
			doc.setStatusName(statusName);
			editTeaScanDoc(doc,user);
		}
	}

	@Override
	public List<OscaSkillRoomTea> getTeaRooms(Map<String, String> param) {
		return oscaAppMapper.getTeaRooms(param);
	}

	@Override
	public OscaSkillRoomDoc getOscaSkillRoomDocByDoc(Map<String, String> param) {
		List<OscaSkillRoomDoc> scores=oscaAppMapper.getOscaSkillRoomDocByDoc(param);
		if(scores!=null&&!scores.isEmpty())
		{
			return scores.get(0);
		}
		return null;
	}

	@Override
	public List<OscaSkillDocScore> getDocScoreByParam(Map<String, String> param) {
		List<OscaSkillDocScore> scores=oscaAppMapper.getDocScoreByParam(param);
		return scores;
	}
	@Override
	public OscaSkillDocScore getNoFromScoreByParam(Map<String, String> param) {
		List<OscaSkillDocScore> scores=oscaAppMapper.getNoFromScoreByParam(param);
		if(scores!=null&&!scores.isEmpty())
		{
			return scores.get(0);
		}
		return null;
	}
	@Override
	public OscaSkillDocScore getNotRequiredFromScoreByParam(Map<String, String> param) {
		List<OscaSkillDocScore> scores=oscaAppMapper.getNotRequiredFromScoreByParam(param);
		if(scores!=null&&!scores.isEmpty())
		{
			return scores.get(0);
		}
		return null;
	}
	@Override
	public OscaSkillDocScore getRequiredFromScoreByParam(Map<String, String> param) {
		List<OscaSkillDocScore> scores=oscaAppMapper.getRequiredFromScoreByParam(param);
		if(scores!=null&&!scores.isEmpty())
		{
			return scores.get(0);
		}
		return null;
	}

	@Override
	public List<SysUserRole> getSysUserRole(String userFlow, String wsId) {

		SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andUserFlowEqualTo(userFlow).andWsIdEqualTo(wsId);
		return userRoleMapper.selectByExample(example);
	}

	@Override
	public SysUser findByIdNo(String idNo) {
		SysUserExample sysUserExample=new SysUserExample();
		Criteria criteria=sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andIdNoEqualTo(idNo);
		List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public SysUser findByUserPhone(String userPhone) {
		SysUserExample sysUserExample=new SysUserExample();
		Criteria criteria=sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserPhoneEqualTo(userPhone);
		List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public SysUser findByUserEmail(String userEmail) {
		SysUserExample sysUserExample=new SysUserExample();
		Criteria criteria=sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserEmailEqualTo(userEmail);
		List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public int saveOsceRegistUser(SysUser user) {
		user.setUserFlow(PkUtil.getUUID());
		user.setUserCode(user.getUserEmail().trim());//默认将邮件做为登录名
		user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), user.getUserPasswd()));
		user.setStatusId("OSCE_NEW");
		user.setStatusDesc("OSCE未完善信息学员");
        user.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		user.setCreateUserFlow(user.getUserFlow());
		user.setCreateTime(DateUtil.getCurrDateTime());
		user.setModifyUserFlow(user.getUserFlow());
		user.setModifyTime(DateUtil.getCurrDateTime());
		int c=sysUserMapper.insert(user);

		//增加学员角色
		String doctorRole = getCfgCode("osca_doctor_role_flow");
		if(StringUtil.isNotBlank(doctorRole))
		{
			SysUserRole userRole = new SysUserRole();
			userRole.setUserFlow(user.getUserFlow());
			userRole.setWsId("osca");
			userRole.setRoleFlow(doctorRole);
			userRole.setAuthTime(DateUtil.getCurrDate());
            userRole.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			userRole.setCreateUserFlow(user.getUserFlow());
			userRole.setCreateTime(DateUtil.getCurrDateTime());
			saveSysUserRole(userRole,user);
		}
		return c;
	}

	@Override
	public int edit(SysUser user, SysUser saveuser) {

		if(user != null){
			if(StringUtil.isNotBlank(user.getUserFlow())){
				user.setModifyUserFlow(saveuser.getUserFlow());
				user.setModifyTime(DateUtil.getCurrDateTime());
				return sysUserMapper.updateByPrimaryKeySelective(user);
			}else{
				user.setUserFlow(PkUtil.getUUID());
                user.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				user.setCreateUserFlow(saveuser.getUserFlow());
				user.setCreateTime(DateUtil.getCurrDateTime());
				user.setModifyUserFlow(saveuser.getUserFlow());
				user.setModifyTime(DateUtil.getCurrDateTime());
				return sysUserMapper.insertSelective(user);
			}
		}
		return 0;
	}

	@Override
	public void editDoctor(ResDoctor doctor, SysUser saveuser) {

		if(doctor != null){
			if(StringUtil.isNotBlank(doctor.getDoctorFlow())){
				doctor.setModifyUserFlow(saveuser.getUserFlow());
				doctor.setModifyTime(DateUtil.getCurrDateTime());
				doctorMapper.updateByPrimaryKeySelective(doctor);
			}else{
				doctor.setDoctorFlow(PkUtil.getUUID());
                doctor.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				doctor.setCreateUserFlow(saveuser.getUserFlow());
				doctor.setCreateTime(DateUtil.getCurrDateTime());
				doctor.setModifyUserFlow(saveuser.getUserFlow());
				doctor.setModifyTime(DateUtil.getCurrDateTime());
				doctorMapper.insertSelective(doctor);
			}
		}
	}

	@Override
	public List<SysOrg> queryAllSysOrg(SysOrg sysorg) {
		SysOrgExample example = new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(sysorg!=null){
			if(StringUtil.isNotBlank(sysorg.getOrgName())){
				criteria.andOrgNameLike("%"+sysorg.getOrgName()+"%");
			}
			if(StringUtil.isNotBlank(sysorg.getIsExamOrg())){
				criteria.andIsExamOrgEqualTo(sysorg.getIsExamOrg());
			}
			if(StringUtil.isNotBlank(sysorg.getOrgCityId())){
				criteria.andOrgCityIdEqualTo(sysorg.getOrgCityId());
			}
			if(StringUtil.isNotBlank(sysorg.getOrgTypeId())){
				criteria.andOrgTypeIdEqualTo(sysorg.getOrgTypeId());
			}
		}
		example.setOrderByClause("NLSSORT(ORG_NAME,'NLS_SORT = SCHINESE_PINYIN_M')");
		return sysOrgMapper.selectByExample(example);
	}

	@Override
	public List<SysDict> getDictListByDictId(String doctorTrainingSpe) {
		if(StringUtil.isNotBlank(doctorTrainingSpe))
		{
			SysDictExample example = new SysDictExample();
            example.createCriteria().andDictTypeIdEqualTo(doctorTrainingSpe).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			List<SysDict> sysDictList = sysDictMapper.selectByExample(example);

			return sysDictList;
		}
		return null;
	}

	@Override
	public int completeOsceRegistUser(SysUser user, ResDoctor doctor) {

		user.setUserCode(user.getUserEmail().trim());//默认将邮件做为登录名
		user.setModifyUserFlow(user.getUserFlow());
		user.setModifyTime(DateUtil.getCurrDateTime());
		sysUserMapper.updateByPrimaryKeySelective(user);
		doctor.setDoctorFlow(user.getUserFlow());
        doctor.setOscaStudentSubmit(com.pinde.core.common.GlobalConstant.FLAG_Y);
		ResDoctorExample example = new ResDoctorExample();
        ResDoctorExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andDoctorFlowEqualTo(user.getUserFlow());
		int count = doctorMapper.countByExample(example);
		if(count>0){
			doctor.setModifyUserFlow(user.getUserFlow());
			doctor.setModifyTime(DateUtil.getCurrDateTime());
			doctorMapper.updateByPrimaryKeySelective(doctor);
		}else{
            doctor.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			doctor.setCreateUserFlow(user.getUserFlow());
			doctor.setCreateTime(DateUtil.getCurrDateTime());
			doctor.setModifyUserFlow(user.getUserFlow());
			doctor.setModifyTime(DateUtil.getCurrDateTime());
			doctorMapper.insertSelective(doctor);
		}
		OscaDoctorRegist search = new OscaDoctorRegist();
		search.setDoctorFlow(user.getUserFlow());
		List<OscaDoctorRegist> registList = oscaDoctorRegistBiz.searchRegist(search);
		if(registList!=null&&registList.size()>0){
			OscaDoctorRegist doctorRegist = registList.get(0);
			doctorRegist.setStatusId(AuditStatusEnum.Passing.getId());
			doctorRegist.setStatusName(AuditStatusEnum.Passing.getName());
			doctorRegist.setModifyUserFlow(user.getUserFlow());
			doctorRegist.setModifyTime(DateUtil.getCurrDateTime());
			oscaDoctorRegistMapper.updateByPrimaryKeySelective(doctorRegist);
		}else{
			OscaDoctorRegist doctorRegist = new OscaDoctorRegist();
			doctorRegist.setRecordFlow(PkUtil.getUUID());
			doctorRegist.setDoctorFlow(user.getUserFlow());
			doctorRegist.setStatusId(AuditStatusEnum.Passing.getId());
			doctorRegist.setStatusName(AuditStatusEnum.Passing.getName());
            doctorRegist.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			doctorRegist.setCreateUserFlow(user.getUserFlow());
			doctorRegist.setCreateTime(DateUtil.getCurrDateTime());
			doctorRegist.setModifyUserFlow(user.getUserFlow());
			doctorRegist.setModifyTime(DateUtil.getCurrDateTime());
			oscaDoctorRegistMapper.insertSelective(doctorRegist);
		}
		return 1;
	}

	@Override
	public OscaDoctorAssessment getAuditOscaDocInfo(String userFlow) {

		List<OscaDoctorAssessment> list=oscaDoctorAssessmentExtMapper.getAuditOscaDocInfo(userFlow);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<InxInfo> findNotice(InxInfo info) {

		InxInfoExample example = new InxInfoExample();
		example.setOrderByClause(" IS_TOP DESC, MODIFY_TIME DESC, INFO_TIME DESC");
        InxInfoExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(info.getColumnId())) {
			criteria.andColumnIdEqualTo(info.getColumnId());
		}
		if (StringUtil.isNotBlank(info.getRoleFlow())) {
			criteria.andRoleFlowEqualTo(info.getRoleFlow());
		}
		return inxInfoMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public InxInfo readInxInfo(String infoFlow) {
		return inxInfoMapper.selectByPrimaryKey(infoFlow);
	}

	public int saveSysUserRole(SysUserRole userRole, SysUser user){
		if(userRole != null){
			if(StringUtil.isNotBlank(userRole.getRecordFlow())){
				user.setModifyUserFlow(user.getUserFlow());
				user.setModifyTime(DateUtil.getCurrDateTime());
				return userRoleMapper.updateByPrimaryKeySelective(userRole);
			}else{
				userRole.setRecordFlow(PkUtil.getUUID());
                user.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				user.setCreateUserFlow(user.getUserFlow());
				user.setCreateTime(DateUtil.getCurrDateTime());
				user.setModifyUserFlow(user.getUserFlow());
				user.setModifyTime(DateUtil.getCurrDateTime());
				return userRoleMapper.insertSelective(userRole);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<Map<String, Object>> notExamStudentList(Map<String, Object> paramMap) {
		return oscaAppMapper.notExamStudentList(paramMap);
	}


	@Override
	public OscaSkillRoom getOscaskillRoomByFlow(String examRoomFlow) {
		return roomMapper.selectByPrimaryKey(examRoomFlow);
	}

	@Override
	public OscaTeaScanDoc getOscaTeaScanDoc(Map<String, String> param) {
		List<OscaTeaScanDoc> scores=oscaAppMapper.getOscaTeaScanDoc(param);
		if(scores!=null&&!scores.isEmpty())
		{
			return scores.get(0);
		}
		return null;
	}

	@Override
	public int editTeaScanDoc(OscaTeaScanDoc b, SysUser user) {
		if(b!=null){
			if(StringUtil.isNotBlank(b.getRecordFlow())){//修改
                b.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				b.setModifyUserFlow(user.getUserFlow());
				b.setModifyTime(DateUtil.getCurrDateTime());
				return this.scanDocMapper.updateByPrimaryKeySelective(b);
			}else{//新增
				b.setRecordFlow(PkUtil.getUUID());
                b.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				b.setCreateUserFlow(user.getUserFlow());
				b.setCreateTime(DateUtil.getCurrDateTime());
				b.setModifyUserFlow(user.getUserFlow());
				b.setModifyTime(DateUtil.getCurrDateTime());
				return this.scanDocMapper.insertSelective(b);
			}
		}
		return 0;
	}

	@Override
	public OscaSubjectStation getOscaSubjectStationByFlow(String stationFlow) {
		return stationMapper.selectByPrimaryKey(stationFlow);
	}
	@Override
	public int editOscaSkillRoomDoc(OscaSkillRoomDoc docStation, SysUser user) {
		if(docStation!=null){
			if(StringUtil.isNotBlank(docStation.getDocRoomFlow())){//修改
                docStation.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				docStation.setModifyUserFlow(user.getUserFlow());
				docStation.setModifyTime(DateUtil.getCurrDateTime());
				return this.roomDocMapper.updateByPrimaryKeySelective(docStation);
			}else{//新增
				docStation.setDocRoomFlow(PkUtil.getUUID());
                docStation.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				docStation.setCreateUserFlow(user.getUserFlow());
				docStation.setCreateTime(DateUtil.getCurrDateTime());
				docStation.setModifyUserFlow(user.getUserFlow());
				docStation.setModifyTime(DateUtil.getCurrDateTime());
				return this.roomDocMapper.insertSelective(docStation);
			}
		}
		return 0;
	}

	@Override
	public int editOscaSkillRoomTeaScore(OscaSkillDocTeaScore teaScore, SysUser user) {
		if(teaScore!=null){
			if(StringUtil.isNotBlank(teaScore.getRecordFlow())){//修改
                teaScore.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				teaScore.setModifyUserFlow(user.getUserFlow());
				teaScore.setModifyTime(DateUtil.getCurrDateTime());
				return this.teaDocScoreMapper.updateByPrimaryKeySelective(teaScore);
			}else{//新增
				teaScore.setRecordFlow(PkUtil.getUUID());
                teaScore.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				teaScore.setCreateUserFlow(user.getUserFlow());
				teaScore.setCreateTime(DateUtil.getCurrDateTime());
				teaScore.setModifyUserFlow(user.getUserFlow());
				teaScore.setModifyTime(DateUtil.getCurrDateTime());
				return this.teaDocScoreMapper.insertSelective(teaScore);
			}
		}
		return 0;
	}

	@Override
	public OscaExamDifferScore readDiffierScoreByOrgFlow(String orgFlow) {

		if(StringUtil.isNotBlank(orgFlow)) {
			OscaExamDifferScoreExample example = new OscaExamDifferScoreExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
					.andOrgFlowEqualTo(orgFlow);
			List<OscaExamDifferScore> list=differScoreMapper.selectByExample(example);
			if(list!=null&&list.size()>0)
			{
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public List<OscaSubjectStationFrom> getFromsByStationFlow(String stationFlow) {
		OscaSubjectStationFromExample fromExample=new OscaSubjectStationFromExample();
		OscaSubjectStationFromExample.Criteria criteria=fromExample.createCriteria();
        criteria.andStationFlowEqualTo(stationFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
		.andOrgFlowEqualTo("sign");
		List<OscaSubjectStationFrom> list = stationFromMapper.selectByExample(fromExample);

		return list;
	}
	@Override
	public List<OscaSubjectStationFrom> getFromsByOrgFlow(String stationFlow, String orgFlow) {
		OscaSubjectStationFromExample fromExample=new OscaSubjectStationFromExample();
		OscaSubjectStationFromExample.Criteria criteria=fromExample.createCriteria();
        criteria.andStationFlowEqualTo(stationFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
		.andOrgFlowEqualTo(orgFlow);
		List<OscaSubjectStationFrom> list = stationFromMapper.selectByExample(fromExample);
		return list;
	}

	@Override
	public OscaSkillDocScore getOscaSkillDocScoreByFlow(String scoreFlow) {
		return scoreMapper.selectByPrimaryKey(scoreFlow);
	}

	@Override
	public OscaFrom getFromByFlow(String fromFlow) {
		return fromMapper.selectByPrimaryKey(fromFlow);
	}

    @Override
    public Map<String, Object> parseFromXml(String content) {
        if(content!=null){
            try
            {
                Map<String, Object> map=new HashMap<String, Object>();
                Document dom;
                dom = DocumentHelper.parseText(content);
               
                String titleXpath = "//title";
                List<Element> titleElementList = dom.selectNodes(titleXpath);
                if(titleElementList != null && !titleElementList.isEmpty()){
                    int titleSum = 0;
                    for(Element te :titleElementList) {
                        Map<String, Object> titleMap=new HashMap<String, Object>();
                        titleMap.put("id",te.attributeValue("id"));
                        titleMap.put("name",te.attributeValue("name"));
                        titleMap.put("typeName",te.attributeValue("typeName"));
						titleMap.put("orderNum",te.attributeValue("orderNum"));
                        List<Element> itemElementList = te.elements("item");
                        List<Map<String, Object>> itemFormList = new ArrayList<>();
						if (itemElementList != null && !itemElementList.isEmpty()){
                            for (Element ie : itemElementList) {
                                Map<String, Object> iteMap=new HashMap<String, Object>();
                                iteMap.put("id",ie.attributeValue("id"));
                                iteMap.put("name",ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                                iteMap.put("score",ie.element("score") == null ? "" : ie.element("score").getTextTrim());
                                iteMap.put("order",ie.element("order") == null ? "" : ie.element("order").getTextTrim());
                                itemFormList.add(iteMap);
                            }
                        }
						OscaCompartor mc = new OscaCompartor();
						Collections.sort(itemFormList,mc);
                        titleMap.put("itemList", itemFormList);
                        map.put(te.attributeValue("id"), titleMap);
                    }
                }
                return map;
            }
            catch (DocumentException e)
            {
                // 
                return null;
            }
        }
        return null;
    }
	@Override
	public List<FromTitle> parseFromXmlForList(String content) {
		if(content!=null){
			try
			{

				List<FromTitle> titleList=new ArrayList<>();
				Document dom;
				dom = DocumentHelper.parseText(content);
				String titleXpath = "//title";
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				if(titleElementList != null && !titleElementList.isEmpty()){
					int titleSum = 0;
					for(Element te :titleElementList) {
						FromTitle fromTitle=new FromTitle();
						fromTitle.setId(te.attributeValue("id"));
						fromTitle.setName(te.attributeValue("name"));
						fromTitle.setTypeName(te.attributeValue("typeName"));
						fromTitle.setOrderNum(te.attributeValue("orderNum"));
						List<FromItem> items=new ArrayList<>();
						List<Element> itemElementList = te.elements("item");
						if (itemElementList != null && !itemElementList.isEmpty()){
							for (Element ie : itemElementList) {
								FromItem item=new FromItem();
								item.setId(ie.attributeValue("id"));
								item.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
								item.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
								item.setOrder(ie.element("order") == null ? "" : ie.element("order").getTextTrim());
								items.add(item);
							}
						}
						if(items.size()>0)
						{

							Collections.sort(items,new Comparator<FromItem>(){
								@Override
								public int compare(FromItem f1, FromItem f2) {
									String order1=f1.getOrder();
									String order2=f2.getOrder();

									if(StringUtil.isBlank(order1)){
										return -1;
									}else if(StringUtil.isBlank(order2)){
										return 1;
									}else if(StringUtil.isNotBlank(order1) && StringUtil.isNotBlank(order2)){
										int c1=Integer.valueOf(order1);
										int c2=Integer.valueOf(order2);
										return c1-c2;
									}
									return 0;
								}
							});
						}
						fromTitle.setItems(items);
						titleList.add(fromTitle);
					}
				}
				return titleList;
			}
			catch (DocumentException e)
			{
				//
				return null;
			}
		}
		return null;
	}

	@Override
	public OscaDoctorAssessment getOscaDoctorAssessmentByTickNumber(String tickNumber) {
		if(StringUtil.isNotBlank(tickNumber))
		{
			OscaDoctorAssessmentExample example=new OscaDoctorAssessmentExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
					.andTicketNumberEqualTo(tickNumber);
			List<OscaDoctorAssessment> list=doctorAssessmentMapper.selectByExample(example);
			if(list!=null&&list.size()>0)
			{
				return  list.get(0);
			}
		}
		return null;
	}

	@Override
    public Map<String, Object> parseGuDingFromXml(String content) {
		Map<String, Object> map=new HashMap<String, Object>();
        if(content!=null){
            try
            {
                Document dom;
                dom = DocumentHelper.parseText(content);
                Element root=dom.getRootElement();
                List<Element> itemsElements=root.elements();
                if(itemsElements!=null&&itemsElements.size()>0)
                {
                    for(Element el:itemsElements)
                    {
                        map.put(el.getName(), el.getText());
                    }
                }
                return map;
            }
            catch (DocumentException e)
            {
                
                return map;
            }
        }
        return map;
    }

	@Override
	public List<OscaSkillScoreDeatil> getScoreDeatilsByScoreFlow(String scoreFlow) {
		OscaSkillScoreDeatilExample fromExample=new OscaSkillScoreDeatilExample();
		OscaSkillScoreDeatilExample.Criteria criteria=fromExample.createCriteria();
        criteria.andScoreFlowEqualTo(scoreFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<OscaSkillScoreDeatil> list = scoreDeatilMapper.selectByExample(fromExample);
		return list;
	}

	@Override
	public Map<String, Object> getDocStationAllScore(Map<String, String> param) {
		return roomDocExtMapper.getDocStationAllScore(param);
	}

	@Override
	public Map<String, Object> getDocStationAllSaveScore(Map<String, String> param) {
		return roomDocExtMapper.getDocStationAllSaveScore(param);
	}

	@Override
	public List<OscaTeaScanDoc> getScanDocList(Map<String, Object> paramMap) {

		return oscaAppMapper.getScanDocList(paramMap);
	}

	@Override
	public List<OscaSkillDocScore> getTeaDocScores(String userFlow, List<String> list) {
		return oscaAppMapper.getTeaDocScores(userFlow,list);
	}

	@Override
	public OscaDoctorAssessment getOscaDoctorAssessment(String clinicalFlow, String doctorFlow) {
		OscaDoctorAssessmentExample example=new OscaDoctorAssessmentExample();
		OscaDoctorAssessmentExample.Criteria criteria=example.createCriteria();
        criteria.andDoctorFlowEqualTo(doctorFlow).andClinicalFlowEqualTo(clinicalFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<OscaDoctorAssessment> list=doctorAssessmentMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return  list.get(0);
		}
		return null;
	}

	@Override
	public List<OscaSkillRoomDoc> getOscaSkillRoomDocs(String clinicalFlow, String doctorFlow) {
		OscaSkillRoomDocExample example=new OscaSkillRoomDocExample();
		OscaSkillRoomDocExample.Criteria criteria=example.createCriteria();
        criteria.andClinicalFlowEqualTo(clinicalFlow).andDoctorFlowEqualTo(doctorFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		return roomDocMapper.selectByExample(example);
	}

	@Override
	public int editOscaDoctorAssessment(OscaDoctorAssessment doctorAssessment, SysUser user) {
		if(doctorAssessment!=null){
			if(StringUtil.isNotBlank(doctorAssessment.getRecordFlow())){//修改
                doctorAssessment.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				doctorAssessment.setModifyUserFlow(user.getUserFlow());
				doctorAssessment.setModifyTime(DateUtil.getCurrDateTime());
				return this.doctorAssessmentMapper.updateByPrimaryKeySelective(doctorAssessment);
			}else{//新增
				doctorAssessment.setRecordFlow(PkUtil.getUUID());
                doctorAssessment.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				doctorAssessment.setCreateUserFlow(user.getUserFlow());
				doctorAssessment.setCreateTime(DateUtil.getCurrDateTime());
				doctorAssessment.setModifyUserFlow(user.getUserFlow());
				doctorAssessment.setModifyTime(DateUtil.getCurrDateTime());
				return this.doctorAssessmentMapper.insertSelective(doctorAssessment);
			}
		}
		return 0;
	}

	@Override
	public OscaSubjectMain getOscaSubjectMain(String subjectFlow) {

		return oscaSubjectMainMapper.selectByPrimaryKey(subjectFlow);
	}
	@Override
	public List<OscaSubjectStation> getOscaSubjectStations(String subjectFlow) {
		OscaSubjectStationExample station=new OscaSubjectStationExample();
		OscaSubjectStationExample.Criteria criteria=station.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSubjectFlowEqualTo(subjectFlow);
		station.setOrderByClause(" ORDINAL ASC ");
		return stationMapper.selectByExample(station);
	}

	@Override
    public Map<String, Object> parseFromAllItemXml(String content) {
        if(content!=null){
            try
            {
                Map<String, Object> map=new HashMap<String, Object>();
                Document dom;
                dom = DocumentHelper.parseText(content);
               Element root=dom.getRootElement();
				Element baseFrom=root.element("fromCfg");
				if(baseFrom!=null) {
					String titleXpath = "//title";
					List<Element> titleElementList = baseFrom.selectNodes(titleXpath);
					if (titleElementList != null && !titleElementList.isEmpty()) {
						int titleSum = 0;
						for (Element te : titleElementList) {
							List<Element> itemElementList = te.elements("item");
							if (itemElementList != null && !itemElementList.isEmpty()) {
								for (Element ie : itemElementList) {
									Map<String, Object> iteMap = new HashMap<String, Object>();
									iteMap.put("id", ie.attributeValue("id"));
									iteMap.put("name", ie.element("name") == null ? "" : ie.element("name").getTextTrim());
									iteMap.put("score", ie.element("score") == null ? "" : ie.element("score").getTextTrim());
									map.put(ie.attributeValue("id"), iteMap);
								}
							}
						}
					}
				}
                return map;
            }
            catch (DocumentException e)
            {
                
                return null;
            }
        }
        return null;
    }

	@Override
	public String getScoreXml(HttpServletRequest request) {
		if(request!=null){
			String rootName ="FromScore";
			Map<String,String[]> paramsMap = request.getParameterMap();
			if(paramsMap!=null && !paramsMap.isEmpty()){
				//创建xmldom对象和根节点
				Document doc = DocumentHelper.createDocument();
				Element root = doc.addElement(rootName);

				for(String key : paramsMap.keySet()){
					String[] vs = paramsMap.get(key);
					String vss = "";
					if(vs!=null && vs.length>0){
						vss = vs[0];
					}
					try {
						if(vss!=null) vss = URLDecoder.decode(vss,"UTF-8") ;
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					//开始创建xml子节点
					Element currEle = root.addElement(key);
					currEle.setText(vss);
				}
				return doc.asXML();
			}
		}
		return null;
	}

	@Override
	public void editOscaSkillScoreDeatil(Map<String, Map<String, Object>> dateMap, SysUser tea, OscaFrom from, OscaSkillDocScore score) {
		String scoreFlow=score.getScoreFlow();
		String partnerFlow=tea.getUserFlow();
		String partnerName=tea.getUserName();
		String fromContent=score.getFromContent();
		Map<String,Object> fromMap=parseFromAllItemXml(fromContent);
		if(fromMap!=null){
    		for(String key:dateMap.keySet())
    		{
    			OscaSkillScoreDeatil deatil=getScoreDetail(key,scoreFlow);
    			if(deatil==null)
    				deatil=new OscaSkillScoreDeatil();
    			//获取所有的打分项
    			Map<String,Object> fromItem=(Map<String,Object>)fromMap.get(key);
    			Map<String,Object> dataItem=(Map<String,Object>)dateMap.get(key);
    			deatil.setScoreFlow(scoreFlow);
    			deatil.setScoreName((String) fromItem.get("name"));
    			deatil.setScoreKey(key);
    			deatil.setInitScore((String) fromItem.get("score"));
    			deatil.setExamScore(new BigDecimal(String.valueOf(dataItem.get("examScore"))));
    			deatil.setPartnerFlow(partnerFlow);
    			deatil.setPartnerName(partnerName);
    			int count=editOneOscaSkillScoreDeatil(deatil,tea);
    		}
		}
	}


	@Override
	public OscaSkillScoreDeatil getScoreDetail(String key, String scoreFlow) {
		OscaSkillScoreDeatilExample fromExample=new OscaSkillScoreDeatilExample();
		OscaSkillScoreDeatilExample.Criteria criteria=fromExample.createCriteria();
        criteria.andScoreFlowEqualTo(scoreFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
		.andScoreKeyEqualTo(key);
		List<OscaSkillScoreDeatil> list = scoreDeatilMapper.selectByExample(fromExample);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Override
	public int editOneOscaSkillScoreDeatil(OscaSkillScoreDeatil deatil, SysUser user) {
		if(deatil!=null){
			if(StringUtil.isNotBlank(deatil.getRecordFlow())){//修改
                deatil.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				deatil.setModifyUserFlow(user.getUserFlow());
				deatil.setModifyTime(DateUtil.getCurrDateTime());
				return this.scoreDeatilMapper.updateByPrimaryKeySelective(deatil);
			}else{//新增
				deatil.setRecordFlow(PkUtil.getUUID());
                deatil.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				deatil.setCreateUserFlow(user.getUserFlow());
				deatil.setCreateTime(DateUtil.getCurrDateTime());
				deatil.setModifyUserFlow(user.getUserFlow());
				deatil.setModifyTime(DateUtil.getCurrDateTime());
				return this.scoreDeatilMapper.insertSelective(deatil);
			}
		}
		return 0;
	}

	@Override
	public int editOscaSkillDocScore(OscaSkillDocScore score, SysUser user) {
		if(score!=null){
			if(StringUtil.isNotBlank(score.getScoreFlow())){//修改
                score.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				score.setModifyUserFlow(user.getUserFlow());
				score.setModifyTime(DateUtil.getCurrDateTime());
				return this.scoreMapper.updateByPrimaryKeySelective(score);
			}else{//新增
				score.setScoreFlow(PkUtil.getUUID());
                score.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				score.setCreateUserFlow(user.getUserFlow());
				score.setCreateTime(DateUtil.getCurrDateTime());
				score.setModifyUserFlow(user.getUserFlow());
				score.setModifyTime(DateUtil.getCurrDateTime());
				return this.scoreMapper.insertSelective(score);
			}
		}
		return 0;
	}

	@Value("#{configProperties['osca.submit.job.switch']}")
	private String haveNotRegSwitch;
	//每天1点开始执行
	@Scheduled(cron="0 0 1 * * ?")
	public void doJob() throws Exception {
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(haveNotRegSwitch)) {
			return;
		}

		List<OscaTeaScanDoc> infos = roomDocExtMapper.getOscaNotSubmitInfo();
		if(infos!=null&&infos.size()>0)
		{
			String flows[]=new String[1];
			for(OscaTeaScanDoc oscaTeaScanDoc:infos)
			{
				flows[0]=oscaTeaScanDoc.getDoctorFlow();
				scoreBatchSubmit(oscaTeaScanDoc.getPartnerFlow(),flows);
			}
		}
	}
}  
  