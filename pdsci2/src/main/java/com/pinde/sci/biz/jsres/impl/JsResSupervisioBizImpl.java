package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.PojoUtils;
import com.pinde.sci.biz.jsres.IJsResSupervisioBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.jsres.JsresSupervisioSubjectExtMapper;
import com.pinde.sci.model.mo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
//@Transactional(rollbackFor=Exception.class)
public class JsResSupervisioBizImpl implements IJsResSupervisioBiz {

	@Autowired
	private ResEvaluationScoreMapper evaluationScoreMapper;
	@Autowired
	private JsresSupervisioFileMapper supervisioFileMapper;
	@Autowired
	private ResScheduleScoreMapper scheduleScoreMapper;
	@Autowired
	private JsresSupervisioSubjectExtMapper subjectExtMapper;
	@Autowired
	private ResHospScoreTableMapper scoreTableMapper;
	@Autowired
	private ResEvaluationIndicatorsMapper indicatorsMapper;
	@Autowired
	private HospSelfAssessmentMapper assessmentMapper;
	@Autowired
	private HospSelfAssessmentCfgMapper assessmentCfgMapper;
    private static Logger logger = LoggerFactory.getLogger(JsResSupervisioBizImpl.class);



	@Override
	public ResEvaluationScore searchEvaluationOwnerScoreByItemId(ResEvaluationScore evaluationScore) {
		ResEvaluationScoreExample example = new ResEvaluationScoreExample();
        ResEvaluationScoreExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if(StringUtil.isNotBlank(evaluationScore.getItemId())){
			criteria.andItemIdEqualTo(evaluationScore.getItemId());
		}
		if(StringUtil.isNotBlank(evaluationScore.getOrgFlow())){
			criteria.andOrgFlowEqualTo(evaluationScore.getOrgFlow());
		}
		if(StringUtil.isNotBlank(evaluationScore.getSpeId())){
			criteria.andSpeIdEqualTo(evaluationScore.getSpeId());
		}
		if(StringUtil.isNotBlank(evaluationScore.getEvaluationYear())){
			criteria.andEvaluationYearEqualTo(evaluationScore.getEvaluationYear());
		}
		if(StringUtil.isNotBlank(evaluationScore.getSpeUserFlow())){
			criteria.andSpeUserFlowEqualTo(evaluationScore.getSpeUserFlow());
		}
		List<ResEvaluationScore> list = evaluationScoreMapper.selectByExample(example);
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int saveScore(ResEvaluationScore evaluationScore) {
		if(StringUtil.isNotBlank(evaluationScore.getScoreFlow())){
			GeneralMethod.setRecordInfo(evaluationScore,false);
			return evaluationScoreMapper.updateByPrimaryKey(evaluationScore);
		}else{
			evaluationScore.setScoreFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(evaluationScore,true);
			return evaluationScoreMapper.insert(evaluationScore);
		}
	}

	@Override
	public List<ResEvaluationScore> searchEvaluationScore(ResEvaluationScore evaluationScore) {
		ResEvaluationScoreExample example = new ResEvaluationScoreExample();
        ResEvaluationScoreExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if(StringUtil.isNotBlank(evaluationScore.getOrgFlow())){
			criteria.andOrgFlowEqualTo(evaluationScore.getOrgFlow());
		}
		if(StringUtil.isNotBlank(evaluationScore.getSpeId())){
			criteria.andSpeIdEqualTo(evaluationScore.getSpeId());
		}
		if(StringUtil.isNotBlank(evaluationScore.getEvaluationYear())){
			criteria.andEvaluationYearEqualTo(evaluationScore.getEvaluationYear());
		}
		if(StringUtil.isNotBlank(evaluationScore.getSpeUserFlow())){
			criteria.andSpeUserFlowEqualTo(evaluationScore.getSpeUserFlow());
		}
		return evaluationScoreMapper.selectByExample(example);
	}

	@Override
	public String checkImg(MultipartFile file) {
		List<String> mimeList = new ArrayList<String>();
		if (com.pinde.core.util.StringUtil.isNotBlank(com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_mime")))) {
			mimeList = Arrays.asList(com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_mime")).split(","));
		}
		List<String> suffixList = new ArrayList<String>();
		if (com.pinde.core.util.StringUtil.isNotBlank(com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_suffix")))) {
			suffixList = Arrays.asList(com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("res_file_support_suffix").toLowerCase()).split(","));
		}
		String fileType = file.getContentType();//MIME类型;
		String fileName = file.getOriginalFilename();//文件名
		String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
		if (!(mimeList.contains(fileType) && suffixList.contains(suffix.toLowerCase()))) {
			return "请上传 " + InitConfig.getSysCfg("res_file_support_suffix") + "格式的文件";
		}
        return com.pinde.core.common.GlobalConstant.FLAG_Y;//可执行保存
	}

	@Override
	public String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName, String orgFlow, String planYear, String itemId) {
        String path = com.pinde.core.common.GlobalConstant.FLAG_N;
		if (file.getSize() > 0) {
			//创建目录
			String dateString = DateUtil.getCurrDate2();
			String newDir = com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + folderName + File.separator + orgFlow + File.separator + planYear + File.separator + itemId;
			File fileDir = new File(newDir);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			//文件名
			String fileName = file.getOriginalFilename();
			String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
			String originalFilename = PkUtil.getGUID() + suffix;
			File newFile = new File(fileDir, originalFilename);
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
                logger.error("", e);
				throw new RuntimeException("保存文件失败！");
			}

			//删除原文件
			if (com.pinde.core.util.StringUtil.isNotBlank(oldFolderName)) {
				try {
					oldFolderName = com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + oldFolderName;
					File imgFile = new File(oldFolderName);
					if (imgFile.exists()) {
						imgFile.delete();
					}
				} catch (Exception e) {
                    logger.error("", e);
					throw new RuntimeException("删除文件失败！");
				}
			}
			path = folderName + "/" + orgFlow + "/" + planYear + "/" + itemId + "/" + originalFilename;
		}
		return path;
	}

	@Override
	public int saveJsresSupervisioFile(JsresSupervisioFile supervisioFile) {
		if (com.pinde.core.util.StringUtil.isNotBlank(supervisioFile.getRecordFlow())) {
			GeneralMethod.setRecordInfo(supervisioFile, false);
			return supervisioFileMapper.updateByPrimaryKeySelective(supervisioFile);
		} else {
			supervisioFile.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(supervisioFile, true);
			return supervisioFileMapper.insert(supervisioFile);
		}
	}

	@Override
	public int deleteFileByPrimaryKey(String recordFlow) {
		if (StringUtil.isNotBlank(recordFlow)) {
			return supervisioFileMapper.deleteByPrimaryKey(recordFlow);
		}
        return 0;
	}

	@Override
	public List<JsresSupervisioFile> searchSupervisioFile(String planYear, String orgFlow, String speId) {
		JsresSupervisioFileExample example = new JsresSupervisioFileExample();
        JsresSupervisioFileExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if (StringUtil.isNotBlank(orgFlow)) {
			criteria.andOrgFlowEqualTo(orgFlow);
		}
		if (StringUtil.isNotBlank(planYear)) {
			criteria.andPlanYearEqualTo(planYear);
		}
		if (StringUtil.isNotBlank(speId)) {
			criteria.andSpeIdEqualTo(speId);
		}
		return supervisioFileMapper.selectByExample(example);
	}

	@Override
	public JsresSupervisioFile searchJsresSupervisioFileByRecordFlow(String recordFlow) {
		JsresSupervisioFileExample example = new JsresSupervisioFileExample();
        JsresSupervisioFileExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if (StringUtil.isNotBlank(recordFlow)) {
			criteria.andRecordFlowEqualTo(recordFlow);
		}
		return supervisioFileMapper.selectByExample(example).get(0);
	}

	@Override
	public ResScheduleScore queryScheduleOne(ResScheduleScore resScheduleScore) {
		List<ResScheduleScore> list = queryScheduleList(resScheduleScore);
		if (list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<ResScheduleScore> queryScheduleList(ResScheduleScore scheduleScore) {
		ResScheduleScoreExample example = new ResScheduleScoreExample();
        ResScheduleScoreExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(scheduleScore.getScheduleFlow())) {
			criteria.andScheduleFlowEqualTo(scheduleScore.getScheduleFlow());
		}
		if (StringUtil.isNotBlank(scheduleScore.getSpeId())) {
			criteria.andSpeIdEqualTo(scheduleScore.getSpeId());
		}
		if (StringUtil.isNotBlank(scheduleScore.getOrgFlow())) {
			criteria.andOrgFlowEqualTo(scheduleScore.getOrgFlow());
		}
		if (StringUtil.isNotBlank(scheduleScore.getItemId())) {
			criteria.andItemIdEqualTo(scheduleScore.getItemId());
		}
		if (StringUtil.isNotBlank(scheduleScore.getItemName())) {
			criteria.andItemNameEqualTo(scheduleScore.getItemName());
		}
		if (StringUtil.isNotBlank(scheduleScore.getGrade())) {
			criteria.andGradeEqualTo(scheduleScore.getGrade());
		}
		if (StringUtil.isNotBlank(scheduleScore.getFileRoute())) {
			criteria.andFileRouteEqualTo(scheduleScore.getFileRoute());
		}
		if (StringUtil.isNotBlank(scheduleScore.getUserFlow())){
			criteria.andUserFlowEqualTo(scheduleScore.getUserFlow());
		}
		if(StringUtil.isNotBlank(scheduleScore.getEvaluationYear())){
			criteria.andEvaluationYearEqualTo(scheduleScore.getEvaluationYear());
		}
		if (StringUtil.isNotBlank(scheduleScore.getScoreType())){
			criteria.andScoreTypeEqualTo(scheduleScore.getScoreType());
		}
		if (StringUtil.isNotBlank(scheduleScore.getSubjectFlow())){
			criteria.andSubjectFlowEqualTo(scheduleScore.getSubjectFlow());
		}
		return scheduleScoreMapper.selectByExample(example);
	}

	@Override
	public int saveSchedule(ResScheduleScore scheduleScore) throws UnsupportedEncodingException {
		if (StringUtil.isNotBlank(scheduleScore.getItemDetailed())){
			String speContent = scheduleScore.getItemDetailed();
			speContent = java.net.URLDecoder.decode(speContent, "UTF-8");
			speContent=speContent.replaceAll("\n","\\\\n");
			scheduleScore.setItemDetailed(speContent);
		}
		ResScheduleScore resScheduleScore = queryScheduleOne(scheduleScore);
		if (null!=resScheduleScore){
			PojoUtils.mergeObject(scheduleScore,resScheduleScore);
			GeneralMethod.setRecordInfo(resScheduleScore, false);
			return scheduleScoreMapper.updateByPrimaryKey(resScheduleScore);
		} else {
			scheduleScore.setScheduleFlow(PkUtil.getUUID());
			scheduleScore.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			GeneralMethod.setRecordInfo(scheduleScore, true);
			return scheduleScoreMapper.insert(scheduleScore);
		}
	}

	@Override
	public int delSchedule(ResScheduleScore scheduleScore) {
		ResScheduleScore resScheduleScore = queryScheduleOne(scheduleScore);
		if (null != resScheduleScore){
			GeneralMethod.setRecordInfo(resScheduleScore, false);
            resScheduleScore.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
			return scheduleScoreMapper.updateByPrimaryKey(resScheduleScore);
		}
		return 0;
	}

	@Override
	public int saveScheduleDetailed(ResScheduleScore scheduleScore) {
		List<ResScheduleScore> scoreList = queryScheduleList(scheduleScore);
		if (scoreList.size() > 0) {
			ResScheduleScore resScheduleScore = scoreList.get(0);
			resScheduleScore.setItemDetailed(scheduleScore.getItemDetailed());
			GeneralMethod.setRecordInfo(resScheduleScore, false);
			return scheduleScoreMapper.updateByPrimaryKey(resScheduleScore);
		} else {
			scheduleScore.setScheduleFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(scheduleScore, true);
			return scheduleScoreMapper.insert(scheduleScore);
		}
	}

	@Override
	public List<ResScheduleScore> queryScheduleListNotItemName(ResScheduleScore scheduleScore) {
		ResScheduleScoreExample example = new ResScheduleScoreExample();
		ResScheduleScoreExample.Criteria criteria = example.createCriteria();
		if (StringUtil.isNotBlank(scheduleScore.getScheduleFlow())) {
			criteria.andScheduleFlowEqualTo(scheduleScore.getScheduleFlow());
		}
		if (StringUtil.isNotBlank(scheduleScore.getSpeId())) {
			criteria.andSpeIdEqualTo(scheduleScore.getSpeId());
		}
		if (StringUtil.isNotBlank(scheduleScore.getOrgFlow())) {
			criteria.andOrgFlowEqualTo(scheduleScore.getOrgFlow());
		}
		if (StringUtil.isNotBlank(scheduleScore.getFileRoute())) {
			criteria.andFileRouteEqualTo(scheduleScore.getFileRoute());
		}
		if (StringUtil.isNotBlank(scheduleScore.getEvaluationYear())) {
			criteria.andEvaluationYearEqualTo(scheduleScore.getEvaluationYear());
		}
		if (StringUtil.isNotBlank(scheduleScore.getItemId())) {
			criteria.andItemIdEqualTo(scheduleScore.getItemId());
		}
		if (StringUtil.isNotBlank(scheduleScore.getItemName())) {
			criteria.andItemNameNotEqualTo(scheduleScore.getItemName());
		}
		if (StringUtil.isNotBlank(scheduleScore.getGrade())) {
			criteria.andGradeEqualTo(scheduleScore.getGrade());
		}
		if (StringUtil.isNotBlank(scheduleScore.getUserFlow())) {
			criteria.andUserFlowEqualTo(scheduleScore.getUserFlow());
		}
		return scheduleScoreMapper.selectByExample(example);
	}

	@Override
	public List<ResScheduleScore> queryScheduleListTotalled(ResScheduleScore scheduleScore) {
		ResScheduleScoreExample example = new ResScheduleScoreExample();
		ResScheduleScoreExample.Criteria criteria = example.createCriteria();
		if (StringUtil.isNotBlank(scheduleScore.getScheduleFlow())) {
			criteria.andScheduleFlowEqualTo(scheduleScore.getScheduleFlow());
		}
		if (StringUtil.isNotBlank(scheduleScore.getSpeId())) {
			criteria.andSpeIdEqualTo(scheduleScore.getSpeId());
		}
		if (StringUtil.isNotBlank(scheduleScore.getOrgFlow())) {
			criteria.andOrgFlowEqualTo(scheduleScore.getOrgFlow());
		}
		if (StringUtil.isNotBlank(scheduleScore.getFileRoute())) {
			criteria.andFileRouteEqualTo(scheduleScore.getFileRoute());
		}
		if (StringUtil.isNotBlank(scheduleScore.getItemId())) {
			criteria.andItemIdEqualTo(scheduleScore.getItemId());
		}
		if (StringUtil.isNotBlank(scheduleScore.getItemName())) {
			criteria.andItemNameEqualTo(scheduleScore.getItemName());
		}
		if (StringUtil.isNotBlank(scheduleScore.getGrade())) {
			criteria.andGradeEqualTo(scheduleScore.getGrade());
		}
		if (StringUtil.isNotBlank(scheduleScore.getScoreType())) {
			criteria.andScoreTypeEqualTo(scheduleScore.getScoreType());
		}
		if (StringUtil.isNotBlank(scheduleScore.getUserFlow())) {
			criteria.andUserFlowEqualTo(scheduleScore.getUserFlow());
		}
		return scheduleScoreMapper.selectByExample(example);
	}

	@Override
	public List<ResOrgSpeAssign> searchSpeAll() {
		return subjectExtMapper.searchSpeAll();
	}

	@Override
	public List<SysDept> selectDeptByOrgFlow(String orgFlow) {
		return subjectExtMapper.selectDeptByOrgFlow(orgFlow);
	}

	@Override
	public List<ResHospScoreTable> chooseTable(String speId, String inspectionType) {
		return subjectExtMapper.chooseTable(speId,inspectionType);
	}

	@Override
	public List<ResEvaluationIndicators> searchAll() {
		ResEvaluationIndicatorsExample example = new ResEvaluationIndicatorsExample();
		ResEvaluationIndicatorsExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		example.setOrderByClause("TABLE_PATH_NAME, CAST(ORDER_NUM AS INTEGER)");
		return indicatorsMapper.selectByExample(example);
	}

	@Override
	public String saveAssessment(HospSelfAssessmentCfg cfg, String type) {
		if (null!=cfg && StringUtil.isNotBlank(type)){
			if (type.equals("add")){
				HospSelfAssessmentCfg hospSelfAssessmentCfg = new HospSelfAssessmentCfg();
				hospSelfAssessmentCfg.setSessionNumber(cfg.getSessionNumber());
				List<HospSelfAssessmentCfg> cfgList = findAllCfg(hospSelfAssessmentCfg);
				if (null!=cfgList && cfgList.size()>0){
					return "该年度已存在配置信息";
				}
				cfg.setCfgFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(cfg,true);
				if (assessmentCfgMapper.insert(cfg)>0){
                    return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
				}
			}else if (type.equals("edit") && StringUtil.isNotBlank(cfg.getCfgFlow())){
				HospSelfAssessmentCfg assessmentCfg = assessmentCfgMapper.selectByPrimaryKey(cfg.getCfgFlow());
				assessmentCfg.setStartTime(cfg.getStartTime());
				assessmentCfg.setEndTime(cfg.getEndTime());
				GeneralMethod.setRecordInfo(assessmentCfg,false);
				if (assessmentCfgMapper.updateByPrimaryKey(assessmentCfg)>0){
                    return com.pinde.core.common.GlobalConstant.UPDATE_SUCCESSED;
				}else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
			}
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	private Integer searchAssessmentBySessionNumber(String sessionNumber){
		HospSelfAssessmentExample example=new HospSelfAssessmentExample();
        HospSelfAssessmentExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if (StringUtil.isNotBlank(sessionNumber)) {
			criteria.andSessionNumberEqualTo(sessionNumber);
		}
		return assessmentMapper.countByExample(example);
	}

	@Override
	public HospSelfAssessment findHospSelfAssessment(String sessionNumber, String orgFlow, String speId, String subjectType) {
		HospSelfAssessmentExample example=new HospSelfAssessmentExample();
        HospSelfAssessmentExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(sessionNumber)){
			criteria.andSessionNumberEqualTo(sessionNumber);
		}
		if (StringUtil.isNotBlank(orgFlow)){
			criteria.andOrgFlowEqualTo(orgFlow);
		}
		if (StringUtil.isNotBlank(speId)){
			criteria.andSpeIdEqualTo(speId);
		}
		if (StringUtil.isNotBlank(subjectType)){
			criteria.andSubjectTypeEqualTo(subjectType);
		}
		example.setOrderByClause("SESSION_NUMBER DESC,START_TIME DESC");
		List<HospSelfAssessment> list = assessmentMapper.selectByExample(example);
		if (null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}


	@Override
	public List<HospSelfAssessment> findAllAssessmentBySpeAndYear(HospSelfAssessment assessment) {
		HospSelfAssessmentExample example=new HospSelfAssessmentExample();
        HospSelfAssessmentExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (null!=assessment){
			if (StringUtil.isNotBlank(assessment.getOrgFlow())){
				criteria.andOrgFlowEqualTo(assessment.getOrgFlow());
			}
			if (StringUtil.isNotBlank(assessment.getSpeId())){
				criteria.andSpeIdEqualTo(assessment.getSpeId());
			}
			if (StringUtil.isBlank(assessment.getSpeId())){
				criteria.andSpeIdIsNotNull();
			}
			if (StringUtil.isNotBlank(assessment.getSessionNumber())){
				criteria.andSessionNumberEqualTo(assessment.getSessionNumber());
			}
		}
		example.setOrderByClause("SESSION_NUMBER DESC,SUBJECT_TYPE,SPE_ID");
		return assessmentMapper.selectByExample(example);
	}

	@Override
	public List<JsresSupervisioFile> findSupervisioFile(JsresSupervisioFile supervisioFile, String subjectType) {
		JsresSupervisioFileExample example=new JsresSupervisioFileExample();
        JsresSupervisioFileExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (null!=supervisioFile){
			if (StringUtil.isNotBlank(supervisioFile.getSubjectFlow())){
				criteria.andSubjectFlowEqualTo(supervisioFile.getSubjectFlow());
			}
			if (StringUtil.isNotBlank(supervisioFile.getOrgFlow())){
				criteria.andOrgFlowEqualTo(supervisioFile.getOrgFlow());
			}
			if (StringUtil.isNotBlank(subjectType)){
				if (subjectType.equals("org")){
					criteria.andSpeIdIsNull();
				}
				if (subjectType.equals("spe") && StringUtil.isNotBlank(supervisioFile.getSpeId())){
					criteria.andSpeIdEqualTo(supervisioFile.getSpeId());
				}
			}
		}
		return supervisioFileMapper.selectByExample(example);
	}

	@Override
	public ResEvaluationScore findHospSelfAssessmentScore(ResEvaluationScore score) {

		ResEvaluationScoreExample example=new ResEvaluationScoreExample();
        ResEvaluationScoreExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if (null!=score){
			if (StringUtil.isNotBlank(score.getSubjectFlow())){
				criteria.andSubjectFlowEqualTo(score.getSubjectFlow());
			}
			if (StringUtil.isNotBlank(score.getOrgFlow())){
				criteria.andOrgFlowEqualTo(score.getOrgFlow());
			}
			if (StringUtil.isNotBlank(score.getItemId())){
				criteria.andItemIdEqualTo(score.getItemId());
			}
			if (StringUtil.isNotBlank(score.getSpeId())){
				criteria.andSpeIdEqualTo(score.getSpeId());
			}else {
				criteria.andSpeIdIsNull();
			}
		}
		List<ResEvaluationScore> list = evaluationScoreMapper.selectByExample(example);
		if (null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int saveHospSelfAssessmentScore(ResEvaluationScore score) throws UnsupportedEncodingException {
		ResEvaluationScore evaluationScore = findHospSelfAssessmentScore(score);
		if (StringUtil.isNotBlank(score.getSpeContent())){
			String speContent = score.getSpeContent();
			speContent = java.net.URLDecoder.decode(speContent, "UTF-8");
			speContent=speContent.replaceAll("\n","\\\\n");
			score.setSpeContent(speContent);
		}else {
			score.setSpeContent(" ");
		}
		if (null==evaluationScore){
			score.setScoreFlow(PkUtil.getUUID());
            score.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
			score.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			score.setOrgName(GlobalContext.getCurrentUser().getOrgName());
			GeneralMethod.setRecordInfo(score,true);
			return  evaluationScoreMapper.insert(score);
		}else {
			PojoUtils.mergeObject(score,evaluationScore);
			GeneralMethod.setRecordInfo(evaluationScore,false);
			return  evaluationScoreMapper.updateByPrimaryKey(evaluationScore);
		}
	}

	@Override
	public List<ResEvaluationScore> findHospSelfAssessmentAllScore(ResEvaluationScore score,String subjectType) {
		ResEvaluationScoreExample example=new ResEvaluationScoreExample();
        ResEvaluationScoreExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if (null!=score){
			if (StringUtil.isNotBlank(score.getSubjectFlow())){
				criteria.andSubjectFlowEqualTo(score.getSubjectFlow());
			}
			if (StringUtil.isNotBlank(score.getOrgFlow())){
				criteria.andOrgFlowEqualTo(score.getOrgFlow());
			}
			if (StringUtil.isNotBlank(score.getItemId())){
				criteria.andItemIdEqualTo(score.getItemId());
			}
			if (StringUtil.isNotBlank(subjectType)){
				if (subjectType.equals("org")){
					criteria.andSpeIdIsNull();
				}
				if (subjectType.equals("spe") && StringUtil.isNotBlank(score.getSpeId())){
					criteria.andSpeIdEqualTo(score.getSpeId());
				}
			}
		}
		return  evaluationScoreMapper.selectByExample(example);
	}


	@Override
	public int saveAssessmentAllScore(HospSelfAssessment assessment) {
		if (null!=assessment && StringUtil.isNotBlank(assessment.getRecordFlow())){
			HospSelfAssessmentCfg cfg = assessmentCfgMapper.selectByPrimaryKey(assessment.getRecordFlow());
			//查看是否存在项目，如果存在项目就修改，不存在就创建
			HospSelfAssessment selfAssessment = findHospSelfAssessment(cfg.getSessionNumber(), assessment.getOrgFlow(), assessment.getSpeId(), assessment.getSubjectType());
			if (null==selfAssessment){
				assessment.setRecordFlow(PkUtil.getUUID());
				assessment.setStartTime(cfg.getStartTime());
				assessment.setEndTime(cfg.getEndTime());
				assessment.setSessionNumber(cfg.getSessionNumber());
				assessment.setSubTime(DateUtil.getCurrDate());
				assessment.setSubUserFlow(GlobalContext.getCurrentUser().getUserFlow());
				GeneralMethod.setRecordInfo(assessment,true);
				return assessmentMapper.insert(assessment);
			}else {
				selfAssessment.setAllScore(assessment.getAllScore());
				selfAssessment.setAssessmentResult(assessment.getAssessmentResult());
				GeneralMethod.setRecordInfo(selfAssessment,false);
				return assessmentMapper.updateByPrimaryKey(selfAssessment);
			}
		}
		return 0;
	}

	@Override
	public int findCoreIndicatorsNum(String cfgFlow,String orgFlow,String speId,String subjectType) {

		ResEvaluationScoreExample example=new ResEvaluationScoreExample();
        ResEvaluationScoreExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
				.andCoreIndicatorsEqualTo("0");

		if (null!=cfgFlow && StringUtil.isNotBlank(cfgFlow)){
			criteria.andSubjectFlowEqualTo(cfgFlow);
		}
		if (StringUtil.isNotBlank(orgFlow)){
			criteria.andOrgFlowEqualTo(orgFlow);
		}
		if (StringUtil.isNotBlank(subjectType)){
			if (subjectType.equals("org")){
				criteria.andSpeIdIsNull();
			}
			if (subjectType.equals("spe")){
				criteria.andSpeIdEqualTo(speId);
			}
		}
		return evaluationScoreMapper.countByExample(example);
	}

	@Override
	public List<HospSelfAssessmentCfg> findAllCfg(HospSelfAssessmentCfg cfg) {
		HospSelfAssessmentCfgExample example = new HospSelfAssessmentCfgExample();
        HospSelfAssessmentCfgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (null!=cfg){
			if (StringUtil.isNotBlank(cfg.getSessionNumber())){
				criteria.andSessionNumberEqualTo(cfg.getSessionNumber());
			}
		}
		return  assessmentCfgMapper.selectByExample(example);
	}

	@Override
	public HospSelfAssessmentCfg findCfgBySessionNumber(String sessionNumber) {
		HospSelfAssessmentCfg cfg = new HospSelfAssessmentCfg();
		cfg.setSessionNumber(sessionNumber);
		List<HospSelfAssessmentCfg> cfgList = findAllCfg(cfg);
		if (null!=cfgList && cfgList.size()>0){
			return cfgList.get(0);
		}
		return null;
	}
}
