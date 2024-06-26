package com.pinde.sci.biz.czyyjxres.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.czyyjxres.ICzJxTuitionManagementBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.JxTuitionManagementMapper;
import com.pinde.sci.model.mo.JxTuitionManagement;
import com.pinde.sci.model.mo.JxTuitionManagementExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class CzJxTuitionManagementBizImpl implements ICzJxTuitionManagementBiz {
    @Autowired
    private JxTuitionManagementMapper tuitionManagementMapper;

    @Override
    public JxTuitionManagement getTuition(String recordFlow) {
        return tuitionManagementMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public List<JxTuitionManagement> getTuitionByCategory(String costCategory) {
        JxTuitionManagementExample example = new JxTuitionManagementExample();
        JxTuitionManagementExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andCostCategoryEqualTo(costCategory);
        return tuitionManagementMapper.selectByExample(example);
    }


    @Override
    public List<JxTuitionManagement> getTuitionByCategoryAndValue(String costCategory,String costValue) {
        JxTuitionManagementExample example = new JxTuitionManagementExample();
        JxTuitionManagementExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(costCategory)){
            criteria.andCostCategoryEqualTo(costCategory);
        }
        if(StringUtil.isNotBlank(costValue)){
            criteria.andCostValueEqualTo(costValue);
        }
        return tuitionManagementMapper.selectByExample(example);
    }

    @Override
    public int saveTuition(JxTuitionManagement tuitionManagement) {
        int count=0;
        if(StringUtil.isNotBlank(tuitionManagement.getRecordFlow())){
            GeneralMethod.setRecordInfo(tuitionManagement,false);
            count = tuitionManagementMapper.updateByPrimaryKeySelective(tuitionManagement);
        }else{
            tuitionManagement.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(tuitionManagement,true);
            count =  tuitionManagementMapper.insert(tuitionManagement);
        }
        return count;
    }

    @Override
    public int deleteTuition(JxTuitionManagement tuitionManagement) {
        tuitionManagement.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        GeneralMethod.setRecordInfo(tuitionManagement,false);
        return tuitionManagementMapper.updateByPrimaryKeySelective(tuitionManagement);
    }

    @Override
    public List<JxTuitionManagement> listTuitionManagements() {
        JxTuitionManagementExample example = new JxTuitionManagementExample();
        JxTuitionManagementExample.Criteria  criteria=  example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        return tuitionManagementMapper.selectByExample(example);
    }


}
