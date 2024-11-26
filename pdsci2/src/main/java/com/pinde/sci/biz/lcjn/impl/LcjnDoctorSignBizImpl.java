package com.pinde.sci.biz.lcjn.impl;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.lcjn.ILcjnDoctorSignBiz;
import com.pinde.sci.dao.base.LcjnDoctorSiginMapper;
import com.pinde.sci.dao.lcjn.LcjnDoctorTrainExtMapper;
import com.pinde.sci.model.mo.LcjnDoctorSigin;
import com.pinde.sci.model.mo.LcjnDoctorSiginExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class LcjnDoctorSignBizImpl implements ILcjnDoctorSignBiz{
    @Autowired
    private LcjnDoctorTrainExtMapper lcjnDoctorTrainExtMapper;
    @Autowired
    private LcjnDoctorSiginMapper lcjnDoctorSiginMapper;

    @Override
    public List<Map<String,Object>> selectDoctorSignList(Map<String, String> map) {
        return lcjnDoctorTrainExtMapper.queryDoctorSignList(map);
    }

    @Override
    public List<LcjnDoctorSigin> searchDoctorSigns(String doctorFlow, String courseFlow) {
        LcjnDoctorSiginExample example=new LcjnDoctorSiginExample();
        LcjnDoctorSiginExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(doctorFlow)){
            criteria.andDoctorFlowEqualTo(doctorFlow);
        }
        if(StringUtil.isNotBlank(courseFlow)){
            criteria.andCourseFlowEqualTo(courseFlow);
        }
        example.setOrderByClause("SIGIN_TIME DESC");
        return lcjnDoctorSiginMapper.selectByExample(example);
    }
}
