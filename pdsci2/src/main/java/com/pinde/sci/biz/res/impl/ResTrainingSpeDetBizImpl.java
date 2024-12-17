package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResTrainingSpeDeptBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.ResTrainingSpeDeptMapper;
import com.pinde.core.model.ResTrainingSpeDept;
import com.pinde.core.model.ResTrainingSpeDeptExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
//@Transactional(rollbackFor=Exception.class)
public class ResTrainingSpeDetBizImpl implements IResTrainingSpeDeptBiz {
    @Autowired
    private ResTrainingSpeDeptMapper trainingSpeDeptMapper;

    @Override
    public List<ResTrainingSpeDept> search(ResTrainingSpeDept trainingSpeDept) {
        ResTrainingSpeDeptExample example = new ResTrainingSpeDeptExample();
        ResTrainingSpeDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(trainingSpeDept!=null){
            if(StringUtil.isNotBlank(trainingSpeDept.getOrgFlow())){
                criteria.andOrgFlowEqualTo(trainingSpeDept.getOrgFlow());
            }
            if(StringUtil.isNotBlank(trainingSpeDept.getTrainingSpeId())){
                criteria.andTrainingSpeIdEqualTo(trainingSpeDept.getTrainingSpeId());
            }
            if(StringUtil.isNotBlank(trainingSpeDept.getDeptFlow())){
                criteria.andDeptFlowEqualTo(trainingSpeDept.getDeptFlow());
            }
        }
        if(StringUtil.isNotBlank(trainingSpeDept.getTrainingSpeId())){
            return trainingSpeDeptMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public int edit(ResTrainingSpeDept trainingSpeDept) {
        if(StringUtil.isNotBlank(trainingSpeDept.getRecordFlow())){
            GeneralMethod.setRecordInfo(trainingSpeDept,false);
            return trainingSpeDeptMapper.updateByPrimaryKeySelective(trainingSpeDept);
        }else {
            trainingSpeDept.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(trainingSpeDept,true);
            return trainingSpeDeptMapper.insert(trainingSpeDept);
        }
    }

    @Override
    public List<ResTrainingSpeDept> notCurrentSpe(ResTrainingSpeDept trainingSpeDept) {
        ResTrainingSpeDeptExample example = new ResTrainingSpeDeptExample();
        ResTrainingSpeDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(trainingSpeDept!=null){
            if(StringUtil.isNotBlank(trainingSpeDept.getOrgFlow())){
                criteria.andOrgFlowEqualTo(trainingSpeDept.getOrgFlow());
            }
            if(StringUtil.isNotBlank(trainingSpeDept.getTrainingSpeId())){
                criteria.andTrainingSpeIdNotEqualTo(trainingSpeDept.getTrainingSpeId());
            }
            return trainingSpeDeptMapper.selectByExample(example);
        }
        return null;
    }
}
