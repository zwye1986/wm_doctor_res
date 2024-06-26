package com.pinde.sci.biz.zseyjxres.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.zseyjxres.IZseyScholarSchArrangeBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ScholarSchArrangeMapper;
import com.pinde.sci.model.mo.ScholarSchArrange;
import com.pinde.sci.model.mo.ScholarSchArrangeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class ZseyScholarSchArrangeBizImpl implements IZseyScholarSchArrangeBiz {
    @Autowired
    private ScholarSchArrangeMapper arrangeMapper;

    @Override
    public int save(ScholarSchArrange arrange) {
        int count=0;
        if(StringUtil.isBlank(arrange.getArrangeFlow())){
            arrange.setArrangeFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(arrange, true);
            count = arrangeMapper.insertSelective(arrange);
        }else{
            GeneralMethod.setRecordInfo(arrange, false);
            count = arrangeMapper.updateByPrimaryKeySelective(arrange);
        }
        return count;
    }

    @Override
    public List<ScholarSchArrange> searchArrange(ScholarSchArrange arrange) {
        List<ScholarSchArrange> arranges =null;
        ScholarSchArrangeExample example  = new ScholarSchArrangeExample();
        ScholarSchArrangeExample.Criteria create=example.createCriteria();

        create.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);

        if(StringUtil.isNotBlank(arrange.getDeptFlow())){
            create.andDeptFlowEqualTo(arrange.getDeptFlow());
        }
        if(StringUtil.isNotBlank(arrange.getDoctorFlow())){
            create.andDoctorFlowEqualTo(arrange.getDoctorFlow());
        }
        if(StringUtil.isNotBlank(arrange.getDoctorName())){
            create.andDoctorNameLike("%"+arrange.getDoctorName()+"%");
        }
        if(StringUtil.isNotBlank(arrange.getSchStartDate())){
            create.andSchStartDateGreaterThanOrEqualTo(arrange.getSchStartDate());
        }
        if(StringUtil.isNotBlank(arrange.getSchEndDate())){
            create.andSchEndDateLessThanOrEqualTo(arrange.getSchEndDate());
        }
        arranges = arrangeMapper.selectByExample(example);
        return arranges;
    }

    @Override
    public ScholarSchArrange selectByArrangeFlow(String arrangeFlow) {
        ScholarSchArrange arrange =null;
        if(StringUtil.isNotBlank(arrangeFlow)){
            arrange = arrangeMapper.selectByPrimaryKey(arrangeFlow);
        }
        return arrange;
    }

    @Override
    public int delbyFlows(List<String> arrangeFlowList) {
        int count=0;
        if(arrangeFlowList!=null && arrangeFlowList.size()>0){
            ScholarSchArrange arrange = new ScholarSchArrange();
            arrange.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            GeneralMethod.setRecordInfo(arrange,false);
            ScholarSchArrangeExample example = new ScholarSchArrangeExample();
            example.createCriteria().andArrangeFlowIn(arrangeFlowList);
            count = arrangeMapper.updateByExampleSelective(arrange,example);
        }
        return count;
    }
}
