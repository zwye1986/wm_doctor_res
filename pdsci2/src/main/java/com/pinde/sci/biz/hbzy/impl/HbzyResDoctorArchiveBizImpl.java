package com.pinde.sci.biz.hbzy.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.hbzy.IJszyResDoctorArchiveBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ResArchiveSequenceMapper;
import com.pinde.sci.dao.base.ResDoctorLogMapper;
import com.pinde.sci.dao.base.ResUserResumeLogMapper;
import com.pinde.sci.dao.hbzy.HbzyResArchiveExtMapper;
import com.pinde.sci.dao.hbzy.HbzyTempMapper;
import com.pinde.sci.form.hbzy.BaseUserResumeExtInfoForm;
import com.pinde.sci.model.hbzy.JszyDoctorInfoLogExt;
import com.pinde.sci.model.hbzy.JszyResArchDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2017-05-22.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HbzyResDoctorArchiveBizImpl implements IJszyResDoctorArchiveBiz {
    @Autowired
    private ResArchiveSequenceMapper archiveSequenceMapper;
    @Autowired
    private HbzyResArchiveExtMapper archiveExtMapper;
    @Autowired
    private ResUserResumeLogMapper resumeLogMapper;
    @Autowired
    private ResDoctorLogMapper doctorLogMapper;
    @Autowired
    private HbzyTempMapper tempMapper;
    @Override
    public int checkArchive(String archiveTime, String sessionNumber) {
        int count = 0;
        if (StringUtil.isNotBlank(archiveTime)) {
            ResArchiveSequenceExample example = new ResArchiveSequenceExample();
            ResArchiveSequenceExample.Criteria criteria = example.createCriteria();
            criteria.andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andArchiveTimeEqualTo(archiveTime).andSessionNumberEqualTo(sessionNumber);
            count = archiveSequenceMapper.countByExample(example);

        }
        return count;
    }

    @Override
    public boolean saveArchiveInfo(String archiveTime, String sessionNumber) throws DocumentException {
        if(StringUtil.isNotBlank(archiveTime)) {
            //保存存档时间信息
            ResArchiveSequence archiveSequence = new ResArchiveSequence();
            archiveSequence.setArchiveFlow(PkUtil.getUUID());
            archiveSequence.setArchiveTime(archiveTime);
            archiveSequence.setSessionNumber(sessionNumber);
            GeneralMethod.setRecordInfo(archiveSequence, true);
            archiveSequenceMapper.insertSelective(archiveSequence);
            tempMapper.backups(archiveTime,sessionNumber);
            String tableName = "'DOCTOR_" + archiveTime + "_" +  sessionNumber + "'";
            return tempMapper.selectBackupTable(tableName);

//
//            int a,a2,a3,b,b2,b3,c,c2,c3,d,d2,d3,e,e2,e3;
//            Map<String,Object> paramMap = new HashMap<>();
//            paramMap.put("archiveTime",archiveTime);
//            paramMap.put("sessionNumber",sessionNumber);
//            paramMap.put("archiveFlow",archiveSequence.getArchiveFlow());
//            /****备份res_doctor********备份res_doctor********备份res_doctor********备份res_doctor****/
//            try{
//                //备份res_doctor
//                a = archiveExtMapper.createResDoctorLog(paramMap);
//                a2 = archiveExtMapper.createResDoctorLogPK(paramMap);
//                a3 = archiveExtMapper.saveResDoctorLog(paramMap);
//            }catch (Exception ex){
//                //出异常将表删除
//                tempMapper.dropTableByTableName("DOCTOR_" + archiveTime + "_" + sessionNumber);
//                return false;
//            }
//            /****备份sys_user********备份sys_user********备份sys_user********备份sys_user********备份sys_user****/
//            try{
//                //备份sys_user
//                b = archiveExtMapper.createSysUserLog(paramMap);
//                b2 = archiveExtMapper.createSysUserLogPK(paramMap);
//                b3 = archiveExtMapper.saveSysUserLog(paramMap);
//            }catch (Exception ex){
//                //出异常将表删除
//                tempMapper.dropTableByTableName("USER_" + archiveTime + "_" + sessionNumber);
//                return false;
//            }
//            /****备份res_doctor_recruit********备份res_doctor_recruit********备份res_doctor_recruit********备份res_doctor_recruit******/
//            try{
//                //备份res_doctor_recruit
//                c = archiveExtMapper.createResDoctorRecruitLog(paramMap);
//                c2 = archiveExtMapper.createResDoctorRecruitLogPK(paramMap);
//                c3 = archiveExtMapper.saveResDoctorRecruitLog(paramMap);
//            }catch (Exception ex){
//                //出异常将表删除
//                tempMapper.dropTableByTableName("RECRUIT_" + archiveTime + "_" + sessionNumber);
//                return false;
//            }
//            /****备份pub_user_resume********备份pub_user_resume********备份pub_user_resume********备份pub_user_resume******/
//            try{
//                //备份pub_user_resume
//                d = archiveExtMapper.createResumeLog(paramMap);
//                d2 = archiveExtMapper.createResumeLogPK(paramMap);
//                d3 = archiveExtMapper.saveResumeLog(paramMap);
//            }catch (Exception ex){
//                //出异常将表删除
//                tempMapper.dropTableByTableName("RESUME_" + archiveTime + "_" + sessionNumber);
//                return false;
//            }
//            /****备份doctor_auth********备份doctor_auth********备份doctor_auth********备份doctor_auth******/
//            try{
//                //备份doctor_auth
//                e = archiveExtMapper.createAuthLog(paramMap);
//                e2 = archiveExtMapper.createAuthLogPK(paramMap);
//                e3 = archiveExtMapper.saveAuthLog(paramMap);
//            }catch (Exception ex){
//                //出异常将表删除
//                tempMapper.dropTableByTableName("AUTH_" + archiveTime + "_" + sessionNumber);
//                return false;
//            }
//
//            if (a3 > 0 || b3 > 0 || c3 > 0 || d3 > 0 || e3 > 0) {
//                return true;
//            }
        }
        return false;
    }

    private List<String> convertPathTitle(List<Map<String, String>> mapList, List<String> dirs) {
        if (null == dirs) {
            dirs = new ArrayList<>();
        }
        for (Map<String, String> map : mapList) {
            if(null != map && !map.isEmpty()) {
                for (String key : map.keySet()) {
                    String value = map.get(key);
                    if (StringUtil.isNotBlank(value) && !dirs.contains(value)) {
                        dirs.add(value);
                    }
                }
            }
        }
        return dirs;
    }

    private void convertToModel(BaseUserResumeExtInfoForm userResumeExt, String archiveFlow) {
        ResUserResumeLog resumeLog = new ResUserResumeLog();
        if (null != userResumeExt) {
            resumeLog.setUserFlow(userResumeExt.getUserFlow());
            resumeLog.setTelephone(userResumeExt.getTelephone());
            resumeLog.setEmergencyAddress(userResumeExt.getEmergencyAddress());
            resumeLog.setWorkSchoolName(userResumeExt.getWorkSchoolName());
            resumeLog.setOrgRank(userResumeExt.getOrgRank());
            resumeLog.setOrgLevel(userResumeExt.getOrgLevel());
            resumeLog.setGraduatedId(userResumeExt.getGraduatedId());
            resumeLog.setGraduatedName(userResumeExt.getGraduatedName());
            resumeLog.setSpecialized(userResumeExt.getSpecialized());
            resumeLog.setGraduationTime(userResumeExt.getGraduationTime());
            resumeLog.setCollegeCertificateNo(userResumeExt.getCollegeCertificateNo());
            if(StringUtil.isNotBlank(userResumeExt.getCertificateUri())) {
                resumeLog.setCertificateUri("/" + archiveFlow + "/" + userResumeExt.getCertificateUri());
            }
            if(StringUtil.isNotBlank(userResumeExt.getDegreeUri())) {
                resumeLog.setDegreeUri("/" + archiveFlow + "/" + userResumeExt.getDegreeUri());
            }
            resumeLog.setDegreeId(userResumeExt.getDegreeId());
            resumeLog.setDegreeName(userResumeExt.getDegreeName());
            resumeLog.setIsMaster(userResumeExt.getIsMaster());
            resumeLog.setMasterDegreeId(userResumeExt.getMasterDegreeId());
            resumeLog.setMasterDegreeName(userResumeExt.getMasterDegreeName());
            resumeLog.setMasterDegreeTypeId(userResumeExt.getMasterDegreeTypeId());
            resumeLog.setMasterDegreeTypeName(userResumeExt.getMasterDegreeTypeName());
            resumeLog.setMasterGraSchoolId(userResumeExt.getMasterGraSchoolId());
            resumeLog.setMasterGraSchoolName(userResumeExt.getMasterGraSchoolName());
            resumeLog.setMasterGraTime(userResumeExt.getMasterGraTime());
            resumeLog.setMasterMajor(userResumeExt.getMasterMajor());
            resumeLog.setIsDoctor(userResumeExt.getIsDoctor());
            resumeLog.setDoctorDegreeId(userResumeExt.getDoctorDegreeId());
            resumeLog.setDoctorDegreeName(userResumeExt.getDoctorDegreeName());
            resumeLog.setDoctorDegreeTypeId(userResumeExt.getDoctorDegreeTypeId());
            resumeLog.setDoctorDegreeTypeName(userResumeExt.getDoctorDegreeTypeName());
            resumeLog.setDoctorGraSchoolId(userResumeExt.getDoctorGraSchoolId());
            resumeLog.setDoctorGraSchoolName(userResumeExt.getDoctorGraSchoolName());
            resumeLog.setDoctorGraTime(userResumeExt.getDoctorGraTime());
            resumeLog.setDoctorMajor(userResumeExt.getDoctorMajor());
            resumeLog.setMedicalHeaithOrgId(userResumeExt.getMedicalHeaithOrgId());
            resumeLog.setMedicalHeaithOrgName(userResumeExt.getMedicalHeaithOrgName());
            resumeLog.setHospitalAttrId(userResumeExt.getHospitalAttrId());
            resumeLog.setHospitalAttrName(userResumeExt.getHospitalAttrName());
            resumeLog.setHospitalCategoryId(userResumeExt.getHospitalCategoryId());
            resumeLog.setHospitalCategoryName(userResumeExt.getHospitalCategoryName());
            resumeLog.setBaseAttributeId(userResumeExt.getBaseAttributeId());
            resumeLog.setBaseAttributeName(userResumeExt.getBaseAttributeName());
            resumeLog.setBasicHealthOrgId(userResumeExt.getBasicHealthOrgId());
            resumeLog.setBasicHealthOrgName(userResumeExt.getBasicHealthOrgName());
            resumeLog.setTechnologyQualificationId(userResumeExt.getTechnologyQualificationId());
            resumeLog.setTechnologyQualificationName(userResumeExt.getTechnologyQualificationName());
            resumeLog.setTechnologyQualificationDate(userResumeExt.getGetTechnologyQualificationDate());
            resumeLog.setQualificationMaterialId(userResumeExt.getQualificationMaterialId());
            resumeLog.setQualificationMaterialName(userResumeExt.getQualificationMaterialName());
            resumeLog.setQualificationMaterialCode(userResumeExt.getQualificationMaterialCode());
            if(StringUtil.isNotBlank(userResumeExt.getQualificationMaterialUri())) {
                resumeLog.setQualificationMaterialUri("/" + archiveFlow + "/" + userResumeExt.getQualificationMaterialUri());
            }
            resumeLog.setPracticingCategoryId(userResumeExt.getPracticingCategoryId());
            resumeLog.setPracticingCategoryName(userResumeExt.getPracticingCategoryName());
            resumeLog.setPracticingScopeId(userResumeExt.getPracticingScopeId());
            resumeLog.setPracticingScopeName(userResumeExt.getPracticingScopeName());
            resumeLog.setPracticingScope(userResumeExt.getPracticingScope());
            resumeLog.setIsGeneralOrderOrientation(userResumeExt.getIsGeneralOrderOrientationTrainee());

            resumeLog.setArchiveFlow(archiveFlow);
            resumeLog.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(resumeLog, true);
            resumeLogMapper.insertSelective(resumeLog);
        }

    }

    @Override
    public List<ResArchiveSequence> allResArchiveSequence() {
        ResArchiveSequenceExample example = new ResArchiveSequenceExample();
        ResArchiveSequenceExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        example.setOrderByClause("SESSION_NUMBER DESC,CREATE_TIME DESC");
        return archiveSequenceMapper.selectByExample(example);
    }

    @Override
    public List<JszyResArchDoctorRecruitExt> searchDoctorInfoExts(Map<String, Object> doctorRecruitMap) {
        return archiveExtMapper.searchJsDoctorRecruitExtList(doctorRecruitMap);
    }

    @Override
    public List<ResDoctorRecruitWithBLOBs> searchResDoctorRecruitList(Map<String,Object> paramMap) {
        return archiveExtMapper.selectrecruitLogByMapWithBLOBs(paramMap);
    }

    @Override
    public List<SysUser> searchSysUserLogList(Map<String,Object> paramMap) {
        return archiveExtMapper.selectUserLogByMap(paramMap);
    }

    @Override
    public List<ResDoctorLog> searchResDoctorLogList(ResDoctorLog resDoctorLog) {
        ResDoctorLogExample example = new ResDoctorLogExample();
        ResDoctorLogExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        if(StringUtil.isNotBlank(resDoctorLog.getArchiveFlow())){
            criteria.andArchiveFlowEqualTo(resDoctorLog.getArchiveFlow());
        }
        if(StringUtil.isNotBlank(resDoctorLog.getDoctorFlow())){
            criteria.andDoctorFlowEqualTo(resDoctorLog.getDoctorFlow());
        }
        return doctorLogMapper.selectByExample(example);
    }

    @Override
    public List<PubUserResume> searchResUserResumeLog(Map<String,Object> paramMap) {
        return archiveExtMapper.selectResumeLogByMap(paramMap);
    }

    @Override
    public List<DoctorAuth> searchAuthLogList(Map<String,Object> paramMap) {
        return archiveExtMapper.selectAuthLogByMap(paramMap);
    }

    @Override
    public List<JszyDoctorInfoLogExt> searchDoctorInfoResume(Map<String,Object> paramMap) {
        return archiveExtMapper.searchDoctorInfoExts(paramMap);
    }

    @Override
    public ResArchiveSequence searchResArchiveSequenceByPK(String archiveFlow) {
        return archiveSequenceMapper.selectByPrimaryKey(archiveFlow);
    }
}
