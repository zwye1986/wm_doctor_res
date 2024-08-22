package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResBookStudyRecordBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ResBookStudyRecordMapper;
import com.pinde.sci.model.mo.ResBookStudyRecord;
import com.pinde.sci.model.mo.ResBookStudyRecordExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class ResBookStudyRecordBizImpl implements IResBookStudyRecordBiz {
    @Autowired
    private ResBookStudyRecordMapper bookStudyRecordMapper;

    @Override
    public List<ResBookStudyRecord> getBookStudyRecords(String doctorFlow,String teacherFlow) {
        ResBookStudyRecordExample example=new ResBookStudyRecordExample();
        ResBookStudyRecordExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow);
        if(StringUtil.isNotBlank(teacherFlow))
        {
            criteria.andTeacherFlowEqualTo(teacherFlow);
        }
        example.setOrderByClause("create_time desc");
        return bookStudyRecordMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public ResBookStudyRecord getBookStudyRecord(String recordFlow) {
        return bookStudyRecordMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public int savaRecord(ResBookStudyRecord record) {
        if(StringUtil.isBlank(record.getRecordFlow())){
            GeneralMethod.setRecordInfo(record, true);
            record.setRecordFlow(PkUtil.getUUID());
            return bookStudyRecordMapper.insert(record);
        }else{
            GeneralMethod.setRecordInfo(record, false);
            return bookStudyRecordMapper.updateByPrimaryKeySelective(record);
        }
    }
}
