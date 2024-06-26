package com.pinde.sci.biz.sczyres.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sczyres.DoctorReductionBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ResDoctorMapper;
import com.pinde.sci.dao.base.ResDoctorRecruitMapper;
import com.pinde.sci.dao.base.ResDoctorReductionMapper;
import com.pinde.sci.dao.sczyres.SczyResReductionExtMapper;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorRecruit;
import com.pinde.sci.model.mo.ResDoctorReduction;
import com.pinde.sci.model.mo.ResDoctorReductionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2017/11/28.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DoctorReductionBizImpl implements DoctorReductionBiz {

    @Autowired
    private ResDoctorReductionMapper reductionMapper;
    @Autowired
    private SczyResReductionExtMapper resReductionExtMapper;
    @Autowired
    private ResDoctorRecruitMapper recruitMapper;
    @Autowired
    private ResDoctorMapper resDoctorMapper;

    @Override
    public ResDoctorReduction findReductionByRecruitFlow(String recruitFlow) {
        ResDoctorReductionExample example = new ResDoctorReductionExample();
        ResDoctorReductionExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecruitFlowEqualTo(recruitFlow);
        List<ResDoctorReduction> reductionList = reductionMapper.selectByExample(example);
        if (reductionList != null && reductionList.size() > 0) {
            return reductionList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<ResDoctorReduction> findReductionByMap(Map<String, Object> paramMap) {
        ResDoctorReductionExample example = new ResDoctorReductionExample();
        ResDoctorReductionExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        List<ResDoctorReduction> reductionList = reductionMapper.selectByExample(example);
        return reductionList;
    }

    @Override
    public int edit(ResDoctorReduction resDoctorReduction) {
        if (StringUtil.isNotBlank(resDoctorReduction.getRecordFlow())) {
            GeneralMethod.setRecordInfo(resDoctorReduction, false);
            return reductionMapper.updateByPrimaryKeySelective(resDoctorReduction);
        } else {
            resDoctorReduction.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(resDoctorReduction, true);
            return reductionMapper.insertSelective(resDoctorReduction);
        }
    }

    @Override
    public ResDoctorReduction findReductionByPK(String recordFlow) {
        return reductionMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public List<Map<String, String>> findReductionExtByMap(Map<String, Object> paramMap) {
        return resReductionExtMapper.findReductionExtByMap(paramMap);
    }

    @Override
    public int updateRecruitAndDoctorInfo(ResDoctorReduction reduction) {
        if (reduction != null) {
            ResDoctorRecruit recruit = recruitMapper.selectByPrimaryKey(reduction.getRecruitFlow());
            String afterReduceTrainYear = reduction.getAfterReduceTrainYear();
            if (recruit != null) {
                String sessionNumber = recruit.getSessionNumber();
                Integer graduationYear = Integer.parseInt(sessionNumber) + Integer.parseInt(afterReduceTrainYear);
                recruit.setGraduationYear(graduationYear + "");
                recruit.setTrainYear(afterReduceTrainYear);
                recruitMapper.updateByPrimaryKey(recruit);
            }
            ResDoctor resDoctor = resDoctorMapper.selectByPrimaryKey(reduction.getDoctorFlow());
            if (resDoctor != null) {
                String sessionNumber = resDoctor.getSessionNumber();
                Integer graduationYear = Integer.parseInt(sessionNumber) + Integer.parseInt(afterReduceTrainYear);
                resDoctor.setGraduationYear(graduationYear+"");
                resDoctor.setTrainingYears(afterReduceTrainYear);
                resDoctorMapper.updateByPrimaryKey(resDoctor);
            }
        }
        return 1;
    }
}
