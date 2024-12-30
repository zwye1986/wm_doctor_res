package com.pinde.sci.biz.res.impl;

import com.pinde.core.common.sci.dao.ResResponsibleteacherDoctorMapper;
import com.pinde.core.model.ResResponsibleteacherDoctor;
import com.pinde.core.model.ResResponsibleteacherDoctorExample;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResResponsibleTeacherDoctorBiz;
import com.pinde.sci.common.GeneralMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
//@Transactional(rollbackFor=Exception.class)
public class ResResponsibleTeacherDoctorBizImpl implements IResResponsibleTeacherDoctorBiz {
    @Autowired
    private ResResponsibleteacherDoctorMapper resResponsibleteacherDoctorMapper;

    @Override
    public List<ResResponsibleteacherDoctor> search(ResResponsibleteacherDoctor resResponsibleteacherDoctor) {
        ResResponsibleteacherDoctorExample example = new ResResponsibleteacherDoctorExample();
        ResResponsibleteacherDoctorExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(resResponsibleteacherDoctor!=null){
            if(StringUtil.isNotBlank(resResponsibleteacherDoctor.getDoctorFlow())){
                criteria.andDoctorFlowEqualTo(resResponsibleteacherDoctor.getDoctorFlow());
            }
            if(StringUtil.isNotBlank(resResponsibleteacherDoctor.getOrgFlow())){
                criteria.andOrgFlowEqualTo(resResponsibleteacherDoctor.getOrgFlow());
            }
            if(StringUtil.isNotBlank(resResponsibleteacherDoctor.getResponsibleteacherFlow())){
                criteria.andResponsibleteacherFlowEqualTo(resResponsibleteacherDoctor.getResponsibleteacherFlow());
            }
            example.setOrderByClause("create_time desc");
            return resResponsibleteacherDoctorMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public int edit(ResResponsibleteacherDoctor resResponsibleteacherDoctor) {
        String recordFlow = resResponsibleteacherDoctor.getRecordFlow();
        if(StringUtil.isNotBlank(recordFlow)){
            GeneralMethod.setRecordInfo(resResponsibleteacherDoctor,false);
            return resResponsibleteacherDoctorMapper.updateByPrimaryKey(resResponsibleteacherDoctor);
        }else {
            resResponsibleteacherDoctor.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(resResponsibleteacherDoctor,true);
            return resResponsibleteacherDoctorMapper.insertSelective(resResponsibleteacherDoctor);
        }
    }
}
