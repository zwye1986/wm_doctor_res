package com.pinde.sci.biz.osca.impl;

import com.alibaba.fastjson.JSONArray;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.osca.IOscaProvincialBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.osca.OscaDoctorAssessmentExtMapper;
import com.pinde.sci.dao.osca.OscaSubjectMainExtMapper;
import com.pinde.sci.enums.osca.AuditStatusEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class OscaProvinvalBizImpl implements IOscaProvincialBiz{
    @Autowired
    OscaSkillsAssessmentMapper skillsAssessmentMapper;
    @Autowired
    OscaDoctorAssessmentMapper doctorAssessmentMapper;
    @Autowired
    OscaSubjectStationFileMapper stationFileMapper;
    @Autowired
    OscaSkillsStationFileMapper skillsStationFileMapper;
    @Autowired
    OscaDoctorAssessmentExtMapper doctorAssessmentExtMapper;
    @Autowired
    OscaSubjectMainExtMapper subjectMainExtMapper;
    @Autowired
    OscaSubjectPartScoreMapper partScoreMapper;
    @Autowired
    OscaSubjectStationScoreMapper stationScoreMapper;
    @Autowired
    OscaSkillRoomDocMapper skillRoomDocMapper;
    @Autowired
    OscaSubjectMainMapper subjectMainMapper;
    @Autowired
    OscaSubjectStationMapper subjectStationMapper;
    @Autowired
    OscaSubjectStationFromMapper subjectStationFromMapper;
    @Autowired
    IFileBiz fileBiz;


    @Override
    public List<OscaSkillsAssessment> searchSkillsAssessment(OscaSkillsAssessment skillsAssessment,List<SysDict> dictList) {
        OscaSkillsAssessmentExample example = new OscaSkillsAssessmentExample();
        OscaSkillsAssessmentExample.Criteria criteria = example.createCriteria().
                andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andIsReleasedEqualTo("Y");
        if(skillsAssessment!=null){
            if(StringUtil.isNotBlank(skillsAssessment.getSpeId())){
                criteria.andSpeIdEqualTo(skillsAssessment.getSpeId());
            }
            if(StringUtil.isNotBlank(skillsAssessment.getClinicalYear())){
                criteria.andClinicalYearEqualTo(skillsAssessment.getClinicalYear());
            }
            if(StringUtil.isNotBlank(skillsAssessment.getOrgFlow())){
                criteria.andOrgFlowEqualTo(skillsAssessment.getOrgFlow());
            }
                criteria.andIsLocalEqualTo("N");
        }
        if(dictList!=null&&dictList.size()>0){
        List<String> speList = new ArrayList<>();
            for(SysDict dict:dictList){
                String dictId = dict.getDictId();
                speList.add(dictId);
            }
            criteria.andSpeIdIn(speList);
        }
        example.setOrderByClause("APPOINT_START_TIME DESC");
        return skillsAssessmentMapper.selectByExample(example);
    }

    @Override
    public int queryDoctorNum(String clinicalFlow) {
        if(StringUtil.isNotBlank(clinicalFlow)){
            OscaDoctorAssessmentExample example = new OscaDoctorAssessmentExample();
            OscaDoctorAssessmentExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            criteria.andClinicalFlowEqualTo(clinicalFlow).andAuditStatusIdEqualTo(AuditStatusEnum.Passed.getId());
            return doctorAssessmentMapper.countByExample(example);
        }else{
            return 0;
        }
    }

    @Override
    public List<Map<String, Object>> searchDoctorScoreList(Map<String, Object> paramMap) {
        return doctorAssessmentExtMapper.searchDoctorScoreList(paramMap);
    }

    @Override
    public List<Map<String, Object>> searchDoctorList(String clinicalFlow) {
        return doctorAssessmentExtMapper.searchDoctorList(clinicalFlow);
    }

    @Override
    public List<OscaSkillRoomDoc> queryDocScores(String doctorFlow, String clinicalFlow) {
        OscaSkillRoomDocExample example = new OscaSkillRoomDocExample();
        OscaSkillRoomDocExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(doctorFlow)){
            criteria.andDoctorFlowEqualTo(doctorFlow);
        }
        if(StringUtil.isNotBlank(clinicalFlow)){
            criteria.andClinicalFlowEqualTo(clinicalFlow);
        }
        example.setOrderByClause("");
        return skillRoomDocMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, Object>> queryDocScoresByOrder(String doctorFlow, String clinicalFlow) {
        return doctorAssessmentExtMapper.queryDocScoresByOrder(doctorFlow,clinicalFlow);
    }

    @Override
    public List<OscaSubjectMain> searchSubjects(OscaSubjectMain subjectMain) {
        OscaSubjectMainExample example = new OscaSubjectMainExample();
        OscaSubjectMainExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(subjectMain!=null){
            if(StringUtil.isNotBlank(subjectMain.getTrainingSpeId())){
                criteria.andTrainingSpeIdEqualTo(subjectMain.getTrainingSpeId());
            }
            if(StringUtil.isNotBlank(subjectMain.getIsReleased())){
                criteria.andIsReleasedEqualTo(subjectMain.getIsReleased());
            }
            if(StringUtil.isNotBlank(subjectMain.getActionTypeId())){
                criteria.andActionTypeIdEqualTo(subjectMain.getActionTypeId());
            }
            if(StringUtil.isNotBlank(subjectMain.getOrgFlow())){
                List<String> orgFlows = new ArrayList<>();
                orgFlows.add(subjectMain.getOrgFlow());
                orgFlows.add("jsst");
                criteria.andOrgFlowIn(orgFlows);
            }
            if(StringUtil.isNotBlank(subjectMain.getTrainingTypeId())){
                criteria.andTrainingTypeIdEqualTo(subjectMain.getTrainingTypeId());
            }
        }
        example.setOrderByClause("create_time DESC");
        return subjectMainMapper.selectByExample(example);
    }

    @Override
    public OscaSubjectMain readSubject(String subjectFlow) {
        if(StringUtil.isNotBlank(subjectFlow)){
            return subjectMainMapper.selectByPrimaryKey(subjectFlow);
        }else{
            return null;
        }
    }

    @Override
    public int edit(OscaSubjectMain subjectMain) {
        if(subjectMain!=null){
                String SubjectFlow = subjectMain.getSubjectFlow();
                if(StringUtil.isNotBlank(SubjectFlow)){
                    GeneralMethod.setRecordInfo(subjectMain,false);
                    return subjectMainMapper.updateByPrimaryKeySelective(subjectMain);
                }else{
                    subjectMain.setSubjectFlow(PkUtil.getUUID());
                    GeneralMethod.setRecordInfo(subjectMain,true);
                    return subjectMainMapper.insertSelective(subjectMain);
                }
            }else {
                return 0;
        }
    }

    @Override
    public int editStationOnly(OscaSubjectStation subjectStation) {
        if(subjectStation!=null){
            String stationFlow = subjectStation.getStationFlow();
            if(StringUtil.isNotBlank(stationFlow)){
                GeneralMethod.setRecordInfo(subjectStation,false);
                return subjectStationMapper.updateByPrimaryKeySelective(subjectStation);
            }else{
                subjectStation.setSubjectFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(subjectStation,true);
                return subjectStationMapper.insertSelective(subjectStation);
            }
        }else {
            return 0;
        }
    }

    @Override
    public List<OscaSubjectStationFrom> searchFromByStaion(String stationFlow,String orgFlow) {
        OscaSubjectStationFromExample example = new OscaSubjectStationFromExample();
        OscaSubjectStationFromExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(orgFlow)){
            criteria.andOrgFlowEqualTo(orgFlow);
        }
        if(StringUtil.isNotBlank(stationFlow)){
            criteria.andStationFlowEqualTo(stationFlow);
        }else{
            return null;
        }
        return subjectStationFromMapper.selectByExample(example);
    }

    @Override
    public List<OscaSubjectStation> searchSubjectStationByMain(String subjectStationFlow) {
        OscaSubjectStationExample example = new OscaSubjectStationExample();
        OscaSubjectStationExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(subjectStationFlow)){
            criteria.andSubjectFlowEqualTo(subjectStationFlow);
        }else{
            return null;
        }
        example.setOrderByClause("ORDINAL");
        return subjectStationMapper.selectByExample(example);
    }

    @Override
    public int editSubjectStationFrom(OscaSubjectStationFrom subjectStationFrom) {
        if(subjectStationFrom!=null){
            String recordFlow = subjectStationFrom.getRecordFlow();
            if(StringUtil.isNotBlank(recordFlow)){
                GeneralMethod.setRecordInfo(subjectStationFrom,false);
                return subjectStationFromMapper.updateByPrimaryKeySelective(subjectStationFrom);
            }else{
                subjectStationFrom.setRecordFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(subjectStationFrom,true);
                return subjectStationFromMapper.insertSelective(subjectStationFrom);
            }
        }else {
            return 0;
        }
    }
    public int editSubjectStationFile(OscaSubjectStationFile subjectStationFile) {
        if(subjectStationFile!=null){
            String recordFlow = subjectStationFile.getRecordFlow();
            if(StringUtil.isNotBlank(recordFlow)){
                GeneralMethod.setRecordInfo(subjectStationFile,false);
                return stationFileMapper.updateByPrimaryKeySelective(subjectStationFile);
            }else{
                subjectStationFile.setRecordFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(subjectStationFile,true);
                return stationFileMapper.insertSelective(subjectStationFile);
            }
        }else {
            return 0;
        }
    }

    @Override
    public int saveHospitalForms(String stationFlow, String stationName, List<Map<String, Object>> fromFlowMaps, List<String> fileFlows) {
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        String orgName = GlobalContext.getCurrentUser().getOrgName();

        List<OscaSubjectStationFrom> subjectStationFroms = searchFromByStaion(stationFlow,orgFlow);
        List<String> oldFlows = new ArrayList<>();
        List<String> newflows = new ArrayList<>();
        //有效的from表单
        if(fromFlowMaps!=null&&fromFlowMaps.size()>0){
            for(Map<String,Object> map:fromFlowMaps){
                String fromFlow = (String)map.get("fromFlow");
                newflows.add(fromFlow);
            }
        }
        Map<String,String> updateFlows=new HashMap<>();
        //删除无效表单
        if(subjectStationFroms!=null&&subjectStationFroms.size()>0){
            for(OscaSubjectStationFrom subjectStationFrom:subjectStationFroms){
                String oldFlow = subjectStationFrom.getFromFlow();
                oldFlows.add(oldFlow);
                if(!newflows.contains(oldFlow)){
                    OscaSubjectStationFrom newFrom = new OscaSubjectStationFrom();
                    newFrom.setRecordFlow(subjectStationFrom.getRecordFlow());
                    newFrom.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                    editSubjectStationFrom(newFrom);
                }else{
                    //保留原来的
                    updateFlows.put(oldFlow,subjectStationFrom.getRecordFlow());
                }
            }
        }
        //清除被删除的文件
        delStationFile(stationFlow,fileFlows,orgFlow);
        saveStationFile(stationFlow, stationName,fileFlows,orgFlow,orgName);
        if(fromFlowMaps!=null&&fromFlowMaps.size()>0){
            for(Map<String,Object> map:fromFlowMaps){
                String fromFlow = (String)map.get("fromFlow");
                OscaSubjectStationFrom subjectStationFrom = new OscaSubjectStationFrom();
                String fromName = (String) map.get("fromName");
                if(oldFlows.contains(fromFlow)){
                    //有效的from表单并且是更新
                    if(newflows.contains(fromFlow)&&StringUtil.isNotBlank(updateFlows.get(fromFlow))) {
                        String isRequired = (String) map.get("isRequired");
                        subjectStationFrom.setRecordFlow(updateFlows.get(fromFlow));
                        subjectStationFrom.setIsRequired(isRequired);
                        subjectStationFrom.setFromName(fromName);
                        int result = editSubjectStationFrom(subjectStationFrom);
                        if (result == 0) {
                            throw new RuntimeException("保存失败");
                        }
                    }
                }else {
                    String isRequired = (String) map.get("isRequired");

                    subjectStationFrom.setStationFlow(stationFlow);
                    subjectStationFrom.setStationName(stationName);
                    subjectStationFrom.setFromFlow(fromFlow);
                    subjectStationFrom.setFromName(fromName);
                    subjectStationFrom.setIsRequired(isRequired);
                    subjectStationFrom.setOrgFlow(orgFlow);
                    subjectStationFrom.setOrgName(orgName);
                    int result = editSubjectStationFrom(subjectStationFrom);
                    if(result == 0){
                        throw new RuntimeException("保存失败");
                    }
                }
            }
        }
        return 1;
    }

    private void saveStationFile(String stationFlow, String stationName, List<String> fileFlows, String orgFlow, String orgName) {
        if(fileFlows!=null&&StringUtil.isNotBlank(orgFlow))
        {

            OscaSubjectStationFileExample example=new OscaSubjectStationFileExample();
            for(String fileFlow:fileFlows)
            {
                example.clear();
                example.createCriteria().andStationFlowEqualTo(stationFlow).andOrgFlowEqualTo(orgFlow).andFileFlowEqualTo(fileFlow);
                List<OscaSubjectStationFile> files=stationFileMapper.selectByExample(example);
                OscaSubjectStationFile stationFile=null;
                if(files!=null&&files.size()>0)
                {
                    stationFile=files.get(0);
                }
                if(stationFile==null) stationFile=new OscaSubjectStationFile();
                stationFile.setStationFlow(stationFlow);
                stationFile.setStationName(stationName);
                stationFile.setFileFlow(fileFlow);
                stationFile.setOrgFlow(orgFlow);
                stationFile.setOrgName(orgName);
                stationFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                editSubjectStationFile(stationFile);
            }
        }
    }

    private void delStationFile(String stationFlow, List<String> fileFlows,String orgFlow) {
        if(StringUtil.isNotBlank(orgFlow)&&StringUtil.isNotBlank(stationFlow))
             doctorAssessmentExtMapper.delStationFile(stationFlow,fileFlows,orgFlow);
    }

    @Override
    public List<PubFile> findStationFiles(String stationFlow, String orgFlow) {
        if(StringUtil.isBlank(orgFlow)) {
            return null;
        }
        return  doctorAssessmentExtMapper.findStationFiles(stationFlow,orgFlow);
    }

    @Override
    public List<PubFile> findClinicalStationFiles(String stationFlow, String clinicalFlow, String orgFlow) {
        if(StringUtil.isBlank(orgFlow)) {
            return null;
        }
        return  doctorAssessmentExtMapper.findClinicalStationFiles(stationFlow,clinicalFlow,orgFlow);
    }

    @Override
    public int saveClinicalFiles(String stationFlow, String clinicalFlow, String orgFlow, List<String> fileFlows) {
        //清除被删除的文件
        delClinicalStationFile(clinicalFlow,stationFlow,fileFlows,orgFlow);
        saveClinicalStationFile(clinicalFlow,stationFlow,fileFlows,orgFlow);
        return 1;
    }

    private void saveClinicalStationFile(String clinicalFlow, String stationFlow, List<String> fileFlows, String orgFlow) {
        if(fileFlows!=null&&StringUtil.isNotBlank(orgFlow))
        {
            OscaSkillsAssessment skillsAssessment=skillsAssessmentMapper.selectByPrimaryKey(clinicalFlow);
            OscaSubjectStation station=subjectStationMapper.selectByPrimaryKey(stationFlow);
            OscaSkillsStationFileExample example=new OscaSkillsStationFileExample();
            for(String fileFlow:fileFlows)
            {
                example.clear();
                example.createCriteria().andClinicalFlowEqualTo(clinicalFlow).andStationFlowEqualTo(stationFlow)
                        .andOrgFlowEqualTo(orgFlow).andFileFlowEqualTo(fileFlow);
                List<OscaSkillsStationFile> files=skillsStationFileMapper.selectByExample(example);
                OscaSkillsStationFile stationFile=null;
                if(files!=null&&files.size()>0)
                {
                    stationFile=files.get(0);
                }
                if(stationFile==null) stationFile=new OscaSkillsStationFile();
                stationFile.setStationFlow(stationFlow);
                stationFile.setStationName(station.getStationName());
                stationFile.setFileFlow(fileFlow);
                stationFile.setOrgFlow(orgFlow);
                stationFile.setOrdinal(1);
                stationFile.setClinicalFlow(clinicalFlow);
                stationFile.setClinicalName(skillsAssessment.getClinicalName());
                stationFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                editSkillsStationFile(stationFile);
            }
        }
    }

    private int editSkillsStationFile(OscaSkillsStationFile subjectStationFile) {
        if(subjectStationFile!=null){
            String recordFlow = subjectStationFile.getRecordFlow();
            if(StringUtil.isNotBlank(recordFlow)){
                GeneralMethod.setRecordInfo(subjectStationFile,false);
                return skillsStationFileMapper.updateByPrimaryKeySelective(subjectStationFile);
            }else{
                subjectStationFile.setRecordFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(subjectStationFile,true);
                return skillsStationFileMapper.insertSelective(subjectStationFile);
            }
        }else {
            return 0;
        }
    }

    private void delClinicalStationFile(String clinicalFlow, String stationFlow, List<String> fileFlows, String orgFlow) {


        if(StringUtil.isNotBlank(orgFlow)&&StringUtil.isNotBlank(stationFlow)&&StringUtil.isNotBlank(clinicalFlow))
            doctorAssessmentExtMapper.delSkillsStationFile(clinicalFlow,stationFlow,fileFlows,orgFlow);

    }

    @Override
    public Map<String, String> uploadFile(MultipartFile file, String fileAddress) {
        Map<String, String> map=new HashMap<String, String>();
        map.put("status", GlobalConstant.OPRE_FAIL_FLAG);
        map.put("error", "上传文件不能为空！") ;
        if(file!=null){
            String fileName = file.getOriginalFilename();//文件名
            map.put("fileName",fileName);
            if (file.isEmpty()) {
                map.put("error", "上传文件不能为空！") ;
                return  map;
            }
            try {
                String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
                    String fileFlow = PkUtil.getUUID();
                    PubFile pubFile = new PubFile();
                    pubFile.setFileFlow(fileFlow);
                    pubFile.setFileName(fileName);
                    pubFile.setFileSuffix(suffix);
                    pubFile.setFileUpType("1");
                    String dateString = DateUtil.getCurrDate2();
                    String filePath = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator+fileAddress+File.separator+dateString;
                    pubFile.setCreateTime(DateUtil.getCurrentTime());
                    File fileDir = new File(filePath);
                    if(!fileDir.exists()){
                        fileDir.mkdirs();
                    }
                    String saveFileName = PkUtil.getUUID() + suffix;
                    File newFile = new File(fileDir,saveFileName);
                    file.transferTo(newFile);
                    String uploadFile = File.separator+fileAddress+File.separator+dateString+File.separator+saveFileName;
                    pubFile.setFilePath(uploadFile);
                    pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                    GeneralMethod.setRecordInfo(pubFile, true);
                    fileBiz.addFile(pubFile);
                    map.put("fileFlow",pubFile.getFileFlow());
                    map.put("status",GlobalConstant.OPRE_SUCCESSED_FLAG);
            } catch (Exception e) {
                map.put("error", "上传文件失败！") ;
                return  map;
            }
        }
        return map;
    }

    @Override
    public List<Map<String, String>> getSubjectParts(String subjectFlow) {
        return subjectMainExtMapper.getSubjectParts(subjectFlow);
    }

    @Override
    public OscaSubjectPartScore getSubjectPartScore(String subjectFlow, String partFlow) {
        OscaSubjectPartScoreExample example=new OscaSubjectPartScoreExample();
        OscaSubjectPartScoreExample.Criteria criteria =example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSubjectFlowEqualTo(subjectFlow);
        if(StringUtil.isNotBlank(partFlow))
        {
            criteria.andPartFlowEqualTo(partFlow);
        }else{
            criteria.andPartFlowIsNull();
        }
        List<OscaSubjectPartScore> list=partScoreMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public OscaSubjectStationScore getSubjectStationScore(String subjectFlow, String stationFlow) {
        OscaSubjectStationScoreExample example=new OscaSubjectStationScoreExample();
        OscaSubjectStationScoreExample.Criteria criteria =example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSubjectFlowEqualTo(subjectFlow);
        if(StringUtil.isNotBlank(stationFlow))
        {
            criteria.andStationFlowEqualTo(stationFlow);
        }else{
            criteria.andStationFlowIsNull();
        }
        List<OscaSubjectStationScore> list=stationScoreMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int savePassedInfo(OscaSubjectMain m, String allScore, JSONArray stationScoreList, JSONArray partScoreList) {
        if(m!=null)
        {
            m.setIsResave("N");
            if(StringUtil.isNotBlank(allScore)) {
                m.setAllScore(Integer.valueOf(allScore));
            }else
                m.setAllScore(null);

            String subjectFlow = m.getSubjectFlow();
            int result=0;
            if(StringUtil.isNotBlank(subjectFlow)){
                GeneralMethod.setRecordInfo(m,false);
                result=subjectMainMapper.updateByPrimaryKey(m);
            }
            if(result==1)
            {
                delSubjectPartScore(subjectFlow);
                delSubjectStationScore(subjectFlow);
                saveSubjectPartScore(subjectFlow,partScoreList);
                saveSubjectStationtScore(subjectFlow,stationScoreList);
            }
            return result;
        }
        return 0;
    }

    @Override
    public List<OscaSubjectPartScore> getOscaSubjectPartScores(String subjectFlow) {
        OscaSubjectPartScoreExample example=new OscaSubjectPartScoreExample();
        OscaSubjectPartScoreExample.Criteria criteria =example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSubjectFlowEqualTo(subjectFlow);
        List<OscaSubjectPartScore> list=partScoreMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list;
        }
        return null;
    }

    @Override
    public List<OscaSubjectStationScore> getOscaSubjectStationScores(String subjectFlow) {
        OscaSubjectStationScoreExample example=new OscaSubjectStationScoreExample();
        OscaSubjectStationScoreExample.Criteria criteria =example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSubjectFlowEqualTo(subjectFlow);
        List<OscaSubjectStationScore> list=stationScoreMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list;
        }
        return null;
    }

    private void saveSubjectStationtScore(String subjectFlow, JSONArray stationScoreList) {
        if(StringUtil.isNotBlank(subjectFlow)&&stationScoreList!=null&&stationScoreList.size()>0)
        {
            for(Object json:stationScoreList)
            {
                Map<String,Object> m= (Map<String, Object>) json;
                String stationFlow= (String) (m.get("stationFlow")==null?"":m.get("stationFlow"));
                Integer stationScore= (Integer) m.get("stationScore");
                if(StringUtil.isNotBlank(stationFlow))
                {
                    OscaSubjectStationScore score=getSubjectStationScore(subjectFlow,stationFlow);
                    if(score==null)
                        score=new OscaSubjectStationScore();
                    score.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                    score.setStationFlow(stationFlow);
                    score.setSubjectFlow(subjectFlow);
                    score.setStationScore(stationScore);
                    saveStationScoreByPrimaryKey(score);
                }
            }
        }
    }

    private int saveStationScoreByPrimaryKey(OscaSubjectStationScore score) {
        if(score!=null){
            String SubjectFlow = score.getRecordFlow();
            if(StringUtil.isNotBlank(SubjectFlow)){
                GeneralMethod.setRecordInfo(score,false);
                return stationScoreMapper.updateByPrimaryKey(score);
            }else{
                score.setRecordFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(score,true);
                return stationScoreMapper.insertSelective(score);
            }
        }else {
            return 0;
        }
    }

    private int savePartScoreByPrimaryKey(OscaSubjectPartScore score) {
        if(score!=null){
            String SubjectFlow = score.getRecordFlow();
            if(StringUtil.isNotBlank(SubjectFlow)){
                GeneralMethod.setRecordInfo(score,false);
                return partScoreMapper.updateByPrimaryKey(score);
            }else{
                score.setRecordFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(score,true);
                return partScoreMapper.insertSelective(score);
            }
        }else {
            return 0;
        }
    }
    private void saveSubjectPartScore(String subjectFlow, JSONArray partScoreList) {
        if(StringUtil.isNotBlank(subjectFlow)&&partScoreList!=null&&partScoreList.size()>0)
        {
            for(Object json:partScoreList)
            {
                Map<String,Object> m= (Map<String, Object>) json;
                String partFlow= (String) (m.get("partFlow")==null?"":m.get("partFlow"));
                Integer partScore= (Integer) m.get("partScore");
                if(StringUtil.isNotBlank(partFlow))
                {
                    OscaSubjectPartScore score=getSubjectPartScore(subjectFlow,partFlow);
                    if(score==null)
                        score=new OscaSubjectPartScore();
                    score.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                    score.setPartFlow(partFlow);
                    score.setSubjectFlow(subjectFlow);
                    score.setPartScore(partScore);
                    savePartScoreByPrimaryKey(score);
                }
            }
            }
        }




    private void delSubjectStationScore(String subjectFlow) {
        subjectMainExtMapper.delSubjectStationScore(subjectFlow);
    }

    private void delSubjectPartScore(String subjectFlow) {
        subjectMainExtMapper.delSubjectPartScore(subjectFlow);

    }

    @Override
    public int editStation(OscaSubjectStation subjectStation, List<Map<String, Object>> fromFlows, List<String> fileFlows) {
        if(subjectStation!=null){
            String stationFlow = subjectStation.getStationFlow();
            if(StringUtil.isNotBlank(stationFlow)){
                GeneralMethod.setRecordInfo(subjectStation,false);
                int result = subjectStationMapper.updateByPrimaryKeySelective(subjectStation);
                if(result == 0){
                    return 0;
                }
                List<OscaSubjectStationFrom> subjectStationFroms = searchFromByStaion(stationFlow,"sign");
                List<String> oldFlows = new ArrayList<>();
                List<String> newflows = new ArrayList<>();
                if(fromFlows!=null&&fromFlows.size()>0){
                    for(Map<String,Object> map:fromFlows){
                        String fromFlow = (String)map.get("fromFlow");
                        newflows.add(fromFlow);
                    }
                }
                Map<String,String> updateFlows=new HashMap<>();
                //删除无效表单
                if(subjectStationFroms!=null&&subjectStationFroms.size()>0){
                    for(OscaSubjectStationFrom subjectStationFrom:subjectStationFroms){
                        String oldFlow = subjectStationFrom.getFromFlow();
                        oldFlows.add(oldFlow);
                        if(!newflows.contains(oldFlow)){
                            OscaSubjectStationFrom newFrom = new OscaSubjectStationFrom();
                            newFrom.setRecordFlow(subjectStationFrom.getRecordFlow());
                            newFrom.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                            editSubjectStationFrom(newFrom);
                        }else{
                            //保留原来的
                            updateFlows.put(oldFlow,subjectStationFrom.getRecordFlow());
                        }
                    }
                }
                String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
                String orgName = GlobalContext.getCurrentUser().getOrgName();
                //清除被删除的文件
                delStationFile(stationFlow,fileFlows,orgFlow);
                saveStationFile(stationFlow, subjectStation.getStationName(),fileFlows,orgFlow,orgName);
                if(fromFlows!=null&&fromFlows.size()>0){
                    for(Map<String,Object> map:fromFlows){
                        String fromFlow = (String)map.get("fromFlow");
                        OscaSubjectStationFrom subjectStationFrom = new OscaSubjectStationFrom();
                        if(oldFlows.contains(fromFlow)){
                            //有效的from表单并且是更新
                            if(newflows.contains(fromFlow)&&StringUtil.isNotBlank(updateFlows.get(fromFlow))) {
                                String isRequired = (String) map.get("isRequired");
                                subjectStationFrom.setRecordFlow(updateFlows.get(fromFlow));
                                subjectStationFrom.setIsRequired(isRequired);
                                int result2 = editSubjectStationFrom(subjectStationFrom);
                                if (result2 == 0) {
                                    return 0;
                                }
                            }
                        }else{
                            String fromName = (String)map.get("fromName");
                            String isRequired = (String) map.get("isRequired");
                            String stationf =  subjectStation.getStationFlow();
                            String stationName = subjectStation.getStationName();
                            System.err.println(fromName+"+"+stationf+"+"+stationName);
                            subjectStationFrom.setStationFlow(stationf);
                            subjectStationFrom.setStationName(stationName);
                            subjectStationFrom.setFromFlow(fromFlow);
                            subjectStationFrom.setFromName(fromName);
                            subjectStationFrom.setIsRequired(isRequired);
                            subjectStationFrom.setOrgFlow("sign");
                            int result2 = editSubjectStationFrom(subjectStationFrom);
                            if(result2==0){
                                return 0;
                            }
                        }
                    }
                }
                return 1;
            }else{
                subjectStation.setStationFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(subjectStation,true);
                int result = subjectStationMapper.insertSelective(subjectStation);
                if(result == 0){
                    return 0;
                }else {
                    String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
                    String orgName = GlobalContext.getCurrentUser().getOrgName();
                    //清除被删除的文件
                    delStationFile(subjectStation.getStationFlow(),fileFlows,orgFlow);
                    saveStationFile(subjectStation.getStationFlow(), subjectStation.getStationName(),fileFlows,orgFlow,orgName);
                    if(fromFlows!=null&&fromFlows.size()>0){
                        String stationf =  subjectStation.getStationFlow();
                        String stationName = subjectStation.getStationName();
                        for(Map<String,Object> map:fromFlows){
                            String fromFlow = (String)map.get("fromFlow");
                            String fromName = (String)map.get("fromName");
                            String isRequired = (String) map.get("isRequired");
                            OscaSubjectStationFrom subjectStationFrom = new OscaSubjectStationFrom();
                            subjectStationFrom.setStationFlow(stationf);
                            subjectStationFrom.setStationName(stationName);
                            subjectStationFrom.setFromFlow(fromFlow);
                            subjectStationFrom.setFromName(fromName);
                            subjectStationFrom.setIsRequired(isRequired);
                            subjectStationFrom.setOrgFlow("sign");
                            int result2 = editSubjectStationFrom(subjectStationFrom);
                            if(result2==0){
                                return 0;
                            }
                        }
                        return 1;
                    }else{
                        return 1;
                    }
                }
            }
        }else {
            return 0;
        }
    }

    @Override
    public int edit2(OscaSubjectMain subjectMain) {
        if(subjectMain!=null){
                GeneralMethod.setRecordInfo(subjectMain,true);
                return subjectMainMapper.insertSelective(subjectMain);
        }else {
            return 0;
        }
    }
}
