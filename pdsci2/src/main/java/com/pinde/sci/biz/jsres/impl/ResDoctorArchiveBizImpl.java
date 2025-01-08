package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.common.sci.dao.*;
import com.pinde.core.model.*;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IResDoctorArchiveBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.FileUtil;
import com.pinde.sci.dao.jsres.ResArchiveExtMapper;
import com.pinde.core.common.form.UserResumeExtInfoForm;
import com.pinde.core.model.JsDoctorInfoLogExt;
import com.pinde.core.model.JsResArchDoctorRecruitExt;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2017-05-22.
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class ResDoctorArchiveBizImpl implements IResDoctorArchiveBiz {
    @Autowired
    private ResArchiveSequenceMapper archiveSequenceMapper;
    @Autowired
    private ResArchiveExtMapper archiveExtMapper;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;
    @Autowired
    private ResUserResumeLogMapper resumeLogMapper;
    @Autowired
    private ResDoctorRecruitLogMapper recruitLogMapper;
    @Autowired
    private ResDoctorLogMapper doctorLogMapper;
    @Autowired
    private SysUserLogMapper userLogMapper;
    @Override
    public int checkArchive(String archiveTime) {
        int count = 0;
        if (StringUtil.isNotBlank(archiveTime)) {
            ResArchiveSequenceExample example = new ResArchiveSequenceExample();
            ResArchiveSequenceExample.Criteria criteria = example.createCriteria();
            criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andArchiveTimeEqualTo(archiveTime);
            count = archiveSequenceMapper.countByExample(example);

        }
        return count;
    }

    @Override
    public boolean saveArchiveInfo(String archiveTime) throws DocumentException {
        if(StringUtil.isNotBlank(archiveTime)) {
            //保存存档时间信息
            ResArchiveSequence archiveSequence = new ResArchiveSequence();
            archiveSequence.setArchiveFlow(PkUtil.getUUID());
            archiveSequence.setArchiveTime(archiveTime);
            GeneralMethod.setRecordInfo(archiveSequence, true);
            archiveSequenceMapper.insertSelective(archiveSequence);
            int a = archiveExtMapper.saveResDoctorLog(archiveSequence.getArchiveFlow());
            int b = archiveExtMapper.saveResDoctorRecruitLog(archiveSequence.getArchiveFlow());
            int c = archiveExtMapper.saveSysUserLog(archiveSequence.getArchiveFlow());
            List<String> dirs = new ArrayList<>();
            List<Map<String, String>> resDoctorRecruitMapList = archiveExtMapper.resDoctorRecruitPathTitle();
            dirs = convertPathTitle(resDoctorRecruitMapList, dirs);
            List<Map<String, String>> resDoctorMapList = archiveExtMapper.resDoctorPathTitle();
            dirs = convertPathTitle(resDoctorMapList, dirs);
            List<Map<String, String>> sysUserMapList = archiveExtMapper.sysUserPathTitle();
            dirs = convertPathTitle(sysUserMapList, dirs);

            List<PubUserResume> userResumeList = userResumeBiz.findPubUserResume();
            for (PubUserResume userResume : userResumeList) {
                if (StringUtil.isNotBlank(userResume.getUserResume())) {
                    UserResumeExtInfoForm userResumeExt = userResumeBiz.converyToJavaBean(userResume.getUserResume(), UserResumeExtInfoForm.class);
                    if (null != userResumeExt) {
                        if (StringUtil.isNotBlank(userResumeExt.getCertificateUri())) {
                            String pathTitle = userResumeExt.getCertificateUri().split("/")[0];
                            if (StringUtil.isNotBlank(pathTitle) && !dirs.contains(pathTitle)) {
                                dirs.add(pathTitle);
                            }
                        }
                        if (StringUtil.isNotBlank(userResumeExt.getDegreeUri())) {
                            String pathTitle = userResumeExt.getDegreeUri().split("/")[0];
                            if (StringUtil.isNotBlank(pathTitle) && !dirs.contains(pathTitle)) {
                                dirs.add(pathTitle);
                            }
                        }
                        if (StringUtil.isNotBlank(userResumeExt.getQualificationMaterialUri())) {
                            String pathTitle = userResumeExt.getQualificationMaterialUri().split("/")[0];
                            if (StringUtil.isNotBlank(pathTitle) && !dirs.contains(pathTitle)) {
                                dirs.add(pathTitle);
                            }
                        }
                        if (StringUtil.isNotBlank(userResumeExt.getSpecialCertificationUri())) {
                            String pathTitle = userResumeExt.getSpecialCertificationUri().split("/")[0];
                            if (StringUtil.isNotBlank(pathTitle) && !dirs.contains(pathTitle)) {
                                dirs.add(pathTitle);
                            }
                        }
                        userResumeExt.setUserFlow(userResume.getUserFlow());
                        userResumeExt.setUserName(userResume.getUserName());
                        convertToModel(userResumeExt, archiveSequence.getArchiveFlow());
                    }
                }

            }
            String newPath = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + archiveSequence.getArchiveFlow() + File.separator;
            for (String oldPath : dirs) {
                FileUtil.copyFile(StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + oldPath, newPath + oldPath);
            }
            return a > 0 && b > 0 && c > 0;
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

    private void convertToModel(UserResumeExtInfoForm userResumeExt, String archiveFlow) {
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
            if(StringUtil.isNotBlank(userResumeExt.getSpecialCertificationUri())) {
                resumeLog.setSpecialCertificationUri("/" + archiveFlow + "/" + userResumeExt.getSpecialCertificationUri());
            }
            resumeLog.setPracticingCategoryId(userResumeExt.getPracticingCategoryId());
            resumeLog.setPracticingCategoryName(userResumeExt.getPracticingCategoryName());
            resumeLog.setPracticingScopeId(userResumeExt.getPracticingScopeId());
            resumeLog.setPracticingScopeName(userResumeExt.getPracticingScopeName());
            resumeLog.setPracticingScope(userResumeExt.getPracticingScope());
            resumeLog.setIsGeneralOrderOrientation(userResumeExt.getIsGeneralOrderOrientationTrainee());
            resumeLog.setRegisteManua(userResumeExt.getRegisteManua());

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
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        example.setOrderByClause("CREATE_TIME DESC");
        return archiveSequenceMapper.selectByExample(example);
    }

    @Override
    public List<JsResArchDoctorRecruitExt> searchDoctorInfoExts(String archiveFlow, ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, List<String> docTypeList) {
        Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
        doctorRecruitMap.put("doctor", doctor);
        doctorRecruitMap.put("user", user);
        doctorRecruitMap.put("derateFlag", flag);
        doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
        doctorRecruitMap.put("sysOrg", sysOrg);
        doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
        doctorRecruitMap.put("docTypeList", docTypeList);
        doctorRecruitMap.put("archiveFlow",archiveFlow);
        List<JsResArchDoctorRecruitExt> doctorRecruitList=archiveExtMapper.searchJsDoctorRecruitExtList(doctorRecruitMap);
        return doctorRecruitList;
    }

    @Override
    public List<ResDoctorRecruitLogWithBLOBs> searchResDoctorRecruitList(ResDoctorRecruitLog recruit, String orderByClause) {
        ResDoctorRecruitLogExample example = new ResDoctorRecruitLogExample();
        ResDoctorRecruitLogExample.Criteria criteria = example.createCriteria();
        if(StringUtil.isNotBlank(recruit.getDoctorFlow())){
            criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
        }
        if(StringUtil.isNotBlank(recruit.getCatSpeId())){
            criteria.andCatSpeIdEqualTo(recruit.getCatSpeId());
        }
        if(StringUtil.isNotBlank(recruit.getAuditStatusId())){
            criteria.andAuditStatusIdEqualTo(recruit.getAuditStatusId());
        }
        if(StringUtil.isNotBlank(recruit.getRecordStatus())){
            criteria.andRecordStatusEqualTo(recruit.getRecordStatus());
        }
        if(StringUtil.isNotBlank(orderByClause)){
            example.setOrderByClause(orderByClause);
        }
        if(StringUtil.isNotBlank(recruit.getArchiveFlow())){
            criteria.andArchiveFlowEqualTo(recruit.getArchiveFlow());
        }
        if(StringUtil.isNotBlank(recruit.getRecruitFlow())){
            criteria.andRecruitFlowEqualTo(recruit.getRecruitFlow());
        }
        return recruitLogMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<SysUserLog> searchSysUserLogList(SysUserLog sysUserLog) {
        SysUserLogExample example = new SysUserLogExample();
        SysUserLogExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        if(StringUtil.isNotBlank(sysUserLog.getArchiveFlow())){
            criteria.andArchiveFlowEqualTo(sysUserLog.getArchiveFlow());
        }
        if(StringUtil.isNotBlank(sysUserLog.getUserFlow())){
            criteria.andUserFlowEqualTo(sysUserLog.getUserFlow());
        }
        return userLogMapper.selectByExample(example);
    }

    @Override
    public List<ResDoctorLog> searchResDoctorLogList(ResDoctorLog resDoctorLog) {
        ResDoctorLogExample example = new ResDoctorLogExample();
        ResDoctorLogExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        if(StringUtil.isNotBlank(resDoctorLog.getArchiveFlow())){
            criteria.andArchiveFlowEqualTo(resDoctorLog.getArchiveFlow());
        }
        if(StringUtil.isNotBlank(resDoctorLog.getDoctorFlow())){
            criteria.andDoctorFlowEqualTo(resDoctorLog.getDoctorFlow());
        }
        return doctorLogMapper.selectByExample(example);
    }

    @Override
    public List<ResUserResumeLog> searchResUserResumeLog(ResUserResumeLog userResumeLog) {
        ResUserResumeLogExample example = new ResUserResumeLogExample();
        ResUserResumeLogExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        if(StringUtil.isNotBlank(userResumeLog.getArchiveFlow())){
            criteria.andArchiveFlowEqualTo(userResumeLog.getArchiveFlow());
        }
        if(StringUtil.isNotBlank(userResumeLog.getUserFlow())){
            criteria.andUserFlowEqualTo(userResumeLog.getUserFlow());
        }
        return resumeLogMapper.selectByExample(example);
    }

    @Override
    public List<JsDoctorInfoLogExt> searchDoctorInfoResume(com.pinde.core.model.ResDoctorRecruit recruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, List<String> docTypeList, String archiveFlow) {
        Map<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("resDoctorRecruit", recruit);
        paramMap.put("doctor", doctor);
        paramMap.put("user", user);
        paramMap.put("derateFlag", flag);
        paramMap.put("jointOrgFlowList", jointOrgFlowList);
        paramMap.put("sysOrg", sysOrg);
        paramMap.put("docTypeList", docTypeList);
        paramMap.put("archiveFlow",archiveFlow);
        return archiveExtMapper.searchDoctorInfoExts(paramMap);
    }
}
