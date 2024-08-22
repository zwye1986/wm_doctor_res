package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IQingPuKqBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.QingpuDoctorKqMapper;
import com.pinde.sci.dao.res.QingpuKqExtMapper;
import com.pinde.sci.dao.res.ResDoctorSchProcessExtMapper;
import com.pinde.sci.model.mo.QingpuDoctorKq;
import com.pinde.sci.model.mo.QingpuDoctorKqExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class QingPuKqBizImpl implements IQingPuKqBiz {
    @Autowired
    private QingpuDoctorKqMapper qingpuDoctorKqMapper;
    @Autowired
    private ResDoctorSchProcessExtMapper processExtMapper;
    @Autowired
    private QingpuKqExtMapper qingpuKqExtMapper;

    @Override
    public QingpuDoctorKq readQingpuDoctorKq(String recordFlow) {
        return qingpuDoctorKqMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public int editQingpuDoctorKq(QingpuDoctorKq kq) {
        String recordFlow = kq.getRecordFlow();
        if(StringUtil.isBlank(recordFlow)){
            kq.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(kq,true);
            return qingpuDoctorKqMapper.insert(kq);
        }else {
            GeneralMethod.setRecordInfo(kq,false);
            return qingpuDoctorKqMapper.updateByPrimaryKeySelective(kq);
        }
    }

    @Override
    public List<QingpuDoctorKq> searchQingpuDoctorKq(QingpuDoctorKq kq) {
        QingpuDoctorKqExample example = new QingpuDoctorKqExample();
        QingpuDoctorKqExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(kq.getDoctorFlow())){
            criteria.andDoctorFlowEqualTo(kq.getDoctorFlow());
        }
        if(StringUtil.isNotBlank(kq.getQingpuKqTypeId())){
            criteria.andQingpuKqTypeIdEqualTo(kq.getQingpuKqTypeId());
        }
        if(StringUtil.isNotBlank(kq.getQingpuKqTypeDetailId())){
            criteria.andQingpuKqTypeDetailIdEqualTo(kq.getQingpuKqTypeDetailId());
        }
        if(StringUtil.isNotBlank(kq.getStartDate())){
            criteria.andEndDateGreaterThanOrEqualTo(kq.getStartDate());
        }
        if(StringUtil.isNotBlank(kq.getEndDate())){
            criteria.andStartDateLessThanOrEqualTo(kq.getEndDate());
        }
        if(StringUtil.isNotBlank(kq.getTeacherFlow())){
            criteria.andTeacherFlowEqualTo(kq.getTeacherFlow());
        }
        if(StringUtil.isNotBlank(kq.getHeadFlow())){
            criteria.andHeadFlowEqualTo(kq.getHeadFlow());
        }
        if(StringUtil.isNotBlank(kq.getManagerFlow())){
            criteria.andManagerFlowEqualTo(kq.getManagerFlow());
        }
        if(StringUtil.isNotBlank(kq.getDoctorName())){
            criteria.andDoctorNameLike("%"+kq.getDoctorName()+"%");
        }
        if(StringUtil.isNotBlank(kq.getOrgFlow())){
            criteria.andOrgFlowEqualTo(kq.getOrgFlow());
        }
        return qingpuDoctorKqMapper.selectByExample(example);
    }

    @Override
    public List<Map<String,Object>> teacherGetStudents(Map<String, Object> paramMap) {
        return processExtMapper.teacherGetStudents(paramMap);
    }

    @Override
    public List<Map<String,Object>> headGetStudents(Map<String, Object> paramMap) {
        return processExtMapper.headGetStudents(paramMap);
    }

    @Override
    public List<Map<String, Object>> getKqStatistics(Map<String, Object> paramMap) {
        return qingpuKqExtMapper.getKqStatistics(paramMap);
    }

    @Override
    public List<Map<String, String>> getSigninList(Map<String, Object> param) {
        return qingpuKqExtMapper.getSigninList(param);
    }
}
