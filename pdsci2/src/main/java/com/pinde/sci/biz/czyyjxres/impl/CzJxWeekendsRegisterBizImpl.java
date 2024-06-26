package com.pinde.sci.biz.czyyjxres.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.czyyjxres.ICzJxWeekendsRegisterBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.JxWeekendsRegisterMapper;
import com.pinde.sci.model.mo.JxWeekendsRegister;
import com.pinde.sci.model.mo.JxWeekendsRegisterExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class CzJxWeekendsRegisterBizImpl implements ICzJxWeekendsRegisterBiz {


    @Autowired
    private JxWeekendsRegisterMapper weekendsRegisterMapper;

    @Override
    public JxWeekendsRegister getWeekend(String recordFlow) {
        return weekendsRegisterMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public List<JxWeekendsRegister> getWeekendByDate(String weekendDate) {
        JxWeekendsRegisterExample example = new JxWeekendsRegisterExample();
        JxWeekendsRegisterExample.Criteria criteria = example.createCriteria();
        criteria.andWeekendDateEqualTo(weekendDate).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        return weekendsRegisterMapper.selectByExample(example);
    }


    @Override
    public int saveWeekend(JxWeekendsRegister weekendsRegister) {
        int count=0;
        if(StringUtil.isNotBlank(weekendsRegister.getRecordFlow())){
            GeneralMethod.setRecordInfo(weekendsRegister,false);
            count = weekendsRegisterMapper.updateByPrimaryKeySelective(weekendsRegister);
        }else{
            weekendsRegister.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(weekendsRegister,true);
            count =  weekendsRegisterMapper.insert(weekendsRegister);
        }
        return count;
    }

    @Override
    public int deleteWeekend(JxWeekendsRegister weekendsRegister) {
        weekendsRegister.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        GeneralMethod.setRecordInfo(weekendsRegister,false);
        return weekendsRegisterMapper.updateByPrimaryKeySelective(weekendsRegister);
    }

    @Override
    public List<JxWeekendsRegister> listWeekends() {
        JxWeekendsRegisterExample example = new JxWeekendsRegisterExample();
        JxWeekendsRegisterExample.Criteria  criteria=  example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause(" weekend_date DESC");
        return weekendsRegisterMapper.selectByExample(example);
    }
}
