package com.pinde.sci.biz.gzzyjxres.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gzzyjxres.IJxNationalHolidaysRegisterBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.JxNationalHolidaysRegisterMapper;
import com.pinde.sci.model.mo.JxNationalHolidaysRegister;
import com.pinde.sci.model.mo.JxNationalHolidaysRegisterExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class JxNationalHolidaysRegisterBizImpl implements IJxNationalHolidaysRegisterBiz {


    @Autowired
    private JxNationalHolidaysRegisterMapper holidaysRegisterMapper;

    @Override
    public JxNationalHolidaysRegister getHoliday(String recordFlow) {
        return holidaysRegisterMapper.selectByPrimaryKey(recordFlow);
    }


    @Override
    public int saveHoliday(JxNationalHolidaysRegister holidaysRegister) {
        int count=0;
        if(StringUtil.isNotBlank(holidaysRegister.getRecordFlow())){
            GeneralMethod.setRecordInfo(holidaysRegister,false);
            count = holidaysRegisterMapper.updateByPrimaryKeySelective(holidaysRegister);
        }else{
            holidaysRegister.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(holidaysRegister,true);
            count =  holidaysRegisterMapper.insert(holidaysRegister);
        }
        return count;
    }

    @Override
    public int deleteHoliday(JxNationalHolidaysRegister holidaysRegister) {
        holidaysRegister.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        GeneralMethod.setRecordInfo(holidaysRegister,false);
        return holidaysRegisterMapper.updateByPrimaryKeySelective(holidaysRegister);
    }

    @Override
    public List<JxNationalHolidaysRegister> listNationalHolidays() {
        JxNationalHolidaysRegisterExample example = new JxNationalHolidaysRegisterExample();
        JxNationalHolidaysRegisterExample.Criteria  criteria=  example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        return holidaysRegisterMapper.selectByExample(example);
    }
}
