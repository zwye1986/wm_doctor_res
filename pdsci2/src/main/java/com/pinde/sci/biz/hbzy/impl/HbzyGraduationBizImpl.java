package com.pinde.sci.biz.hbzy.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.hbzy.IJszyGraduationBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorRecruitBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ResDoctorGraduationInfoMapper;
import com.pinde.sci.dao.res.ResDoctorGraduationInfoExtMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2017/12/27.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HbzyGraduationBizImpl implements IJszyGraduationBiz {

    @Autowired
    private ResDoctorGraduationInfoMapper graduationInfoMapper;
    @Autowired
    private ResDoctorGraduationInfoExtMapper graduationInfoExtMapper;
    @Autowired
    private IResDoctorRecruitBiz recruitBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;

    @Override
    public int editGraduationInfo(ResDoctorGraduationInfo resDoctorGraduationInfo) {
        if (StringUtil.isNotBlank(resDoctorGraduationInfo.getRecordFlow())) {
            GeneralMethod.setRecordInfo(resDoctorGraduationInfo, false);
            return graduationInfoMapper.updateByPrimaryKeySelective(resDoctorGraduationInfo);
        } else {
            GeneralMethod.setRecordInfo(resDoctorGraduationInfo, true);
            resDoctorGraduationInfo.setRecordFlow(PkUtil.getUUID());
            return graduationInfoMapper.insert(resDoctorGraduationInfo);
        }
    }

    @Override
    public ResDoctorGraduationInfo findGraduationInfo(String recordFlow) {
        return graduationInfoMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public List<ResDoctorGraduationInfo> searchGraduationInfoByMap(Map<String, Object> paramMap) {
        ResDoctorGraduationInfoExample example = new ResDoctorGraduationInfoExample();
        ResDoctorGraduationInfoExample.Criteria criteria = example.createCriteria();
        if (paramMap.get("orgFlows") != null) {
            List<String> orgFlows = (List) paramMap.get("orgFlows");
            if (orgFlows.size() > 0) {
                criteria.andTrainingBaseFlowIn(orgFlows);
            }
        } if (paramMap.get("orgFlow") != null&&StringUtil.isNotBlank((String) paramMap.get("orgFlow"))) {
                criteria.andTrainingBaseFlowEqualTo((String) paramMap.get("orgFlow"));
        }
        if (paramMap.get("docTypeList") != null) {
            List<String> docTypeList = (List) paramMap.get("docTypeList");
            if (docTypeList.size() > 0) {
                criteria.andDoctorTypeIdIn(docTypeList);
            }
        }
        if (paramMap.get("resDoctorGraduationInfo") != null) {
            ResDoctorGraduationInfo tempGraduation = (ResDoctorGraduationInfo) paramMap.get("resDoctorGraduationInfo");
            if (StringUtil.isNotBlank(tempGraduation.getDoctorName())) {
                criteria.andDoctorNameLike("%" + tempGraduation.getDoctorName() + "%");
            }
            if (StringUtil.isNotBlank(tempGraduation.getTrainingTypeId())) {
                criteria.andTrainingTypeIdEqualTo(tempGraduation.getTrainingTypeId());
            }
            if (StringUtil.isNotBlank(tempGraduation.getTrainingSpeId())) {
                criteria.andTrainingSpeIdEqualTo(tempGraduation.getTrainingSpeId());
            }
            if (StringUtil.isNotBlank(tempGraduation.getSessionNumber())) {
                criteria.andSessionNumberEqualTo(tempGraduation.getSessionNumber());
            }
            if (StringUtil.isNotBlank(tempGraduation.getGraduationYear())) {
                criteria.andGraduationYearEqualTo(tempGraduation.getGraduationYear());
            }
            if (StringUtil.isNotBlank(tempGraduation.getIdNo())) {
                criteria.andIdNoEqualTo(tempGraduation.getIdNo());
            }
            if (StringUtil.isNotBlank(tempGraduation.getCurrentAuditStatusId())) {
                criteria.andCurrentAuditStatusIdEqualTo(tempGraduation.getCurrentAuditStatusId());
            }
            if (StringUtil.isNotBlank(tempGraduation.getCertificateNumber())) {
                criteria.andCertificateNumberLike("%" +tempGraduation.getCertificateNumber() +"%");
            }
            if (StringUtil.isNotBlank(tempGraduation.getDoctorFlow())) {
                criteria.andDoctorFlowEqualTo(tempGraduation.getDoctorFlow());
            }
        }
        if (paramMap.get("certificateNumber") != null) {
            criteria.andCertificateNumberIsNotNull();
        }
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (paramMap.get("order") != null) {
            example.setOrderByClause(paramMap.get("order").toString());
        }
        return graduationInfoMapper.selectByExample(example);
    }

    @Override
    public ResDoctorGraduationInfo findGraduationInfoByIdNo(String idNo) {
        ResDoctorGraduationInfoExample example = new ResDoctorGraduationInfoExample();
        ResDoctorGraduationInfoExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andIdNoEqualTo(idNo);
        List<ResDoctorGraduationInfo> list = graduationInfoMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int modifyBatch(List<String> recordFlows, ResDoctorGraduationInfo graduationInfo) {
        ResDoctorGraduationInfoExample example = new ResDoctorGraduationInfoExample();
        ResDoctorGraduationInfoExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (recordFlows != null && recordFlows.size() > 0) {
            criteria.andRecordFlowIn(recordFlows);
        }
        return graduationInfoMapper.updateByExampleSelective(graduationInfo,example);
    }

    @Override
    public int createCertificate(ResDoctorGraduationInfo old) {
        ResDoctorGraduationInfo numInfo=graduationInfoExtMapper.getCreateCertificate(old);
        if(numInfo==null)
        {
            return 0;
        }
        if(StringUtil.isBlank(numInfo.getCertificateNumber())||StringUtil.isBlank(numInfo.getCertificateFlow()))
        {
            return 0;
        }
        int count= editGraduationInfo(numInfo);
        if(count==1)
        {
            ResDoctorRecruit recruit=recruitBiz.readResDoctorRecruit(numInfo.getRecruitFlow());
            if(recruit!=null)
            {
                recruit.setDoctorStatusId("21");
                recruit.setDoctorStatusName("结业");
                recruitBiz.updateDocrecruit(recruit);
            }
            ResDoctor doctor=doctorBiz.readDoctor(numInfo.getDoctorFlow());
            if(doctor!=null)
            {
                doctor.setDoctorStatusId("21");
                doctor.setDoctorStatusName("结业");
                doctorBiz.editDoctor(doctor);
            }
        }
        return count;
    }
}
