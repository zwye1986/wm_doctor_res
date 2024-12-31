package com.pinde.sci.biz.res.impl;

import com.pinde.core.common.sci.dao.ResDiscipleInfoMapper;
import com.pinde.core.common.sci.dao.ResDiscipleReqMapper;
import com.pinde.core.model.ResDiscipleInfo;
import com.pinde.core.model.ResDiscipleInfoExample;
import com.pinde.core.model.ResDiscipleReq;
import com.pinde.core.model.ResDiscipleReqExample;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDiscipleInfoBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.res.ResDiscipleInfoExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/11.
 */
@Service
//@Transactional(rollbackFor=Exception.class)
public class ResDiscipleInfoBizImpl implements IResDiscipleInfoBiz {
    @Autowired
    private ResDiscipleInfoMapper resDiscipleInfoMapper;
    @Autowired
    private ResDiscipleInfoExtMapper resDiscipleInfoExtMapper;
    @Autowired
    private ResDiscipleReqMapper resDiscipleReqMapper;

    @Override
    public ResDiscipleInfo readResDiscipleInfo(String userFlow) {
        ResDiscipleInfoExample example=new ResDiscipleInfoExample();
        example.createCriteria().andDoctorFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<ResDiscipleInfo>list= resDiscipleInfoMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<ResDiscipleInfo> readResDiscipleInfos(List<String> userFlows) {
        ResDiscipleInfoExample example=new ResDiscipleInfoExample();
        ResDiscipleInfoExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(userFlows!=null&&userFlows.size()>0){
            criteria.andDoctorFlowIn(userFlows);
            return resDiscipleInfoMapper.selectByExample(example);
        }else {
            return null;
        }
    }

    @Override
    public String getTeacherName(String userFlow) {
        Map<String,String> map=resDiscipleInfoExtMapper.getTeacherNames(userFlow);
        if(map!=null)
        {
            return map.get("TEACHER_NAMES");
        }
        return null;
    }

    @Override
    public int savaResDiscipleInfo(ResDiscipleInfo bean) {
        if(StringUtil.isBlank(bean.getDiscipleFlow())) {
            GeneralMethod.setRecordInfo(bean, true);
            bean.setDiscipleFlow(PkUtil.getUUID());
            return resDiscipleInfoMapper.insert(bean);
        }else{
            GeneralMethod.setRecordInfo(bean, false);
            return resDiscipleInfoMapper.updateByPrimaryKeySelective(bean);
        }
    }

    @Override
    public List<ResDiscipleReq> findResDiscipleReqList(ResDiscipleReq resDiscipleReq) {
        ResDiscipleReqExample example = new ResDiscipleReqExample();
        ResDiscipleReqExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(resDiscipleReq.getOrgFlow())){
            criteria.andOrgFlowEqualTo(resDiscipleReq.getOrgFlow());
        }
        if(StringUtil.isNotBlank(resDiscipleReq.getDiscipleTypeId())){
            criteria.andDiscipleTypeIdEqualTo(resDiscipleReq.getDiscipleTypeId());
        }
        if(StringUtil.isNotBlank(resDiscipleReq.getSessionNumber())){
            criteria.andSessionNumberEqualTo(resDiscipleReq.getSessionNumber());
        }
        if(StringUtil.isNotBlank(resDiscipleReq.getTrainingTypeId())){
            criteria.andTrainingTypeIdEqualTo(resDiscipleReq.getTrainingTypeId());
        }
        example.setOrderByClause("TRAINING_TYPE_ID,SESSION_NUMBER,DISCIPLE_TYPE_ID");
        return resDiscipleReqMapper.selectByExample(example);
    }

    @Override
    public ResDiscipleReq findResDiscipleReqByFlow(String recordFlow) {
        if(StringUtil.isNotBlank(recordFlow))
        {
            return resDiscipleReqMapper.selectByPrimaryKey(recordFlow);
        }
        return null;
    }

    @Override
    public int updateDiscipleReq(ResDiscipleReq resDiscipleReq) {
        if(StringUtil.isNotBlank(resDiscipleReq.getRecordFlow())){
            GeneralMethod.setRecordInfo(resDiscipleReq, false);
            return resDiscipleReqMapper.updateByPrimaryKeySelective(resDiscipleReq);
        }else{
            resDiscipleReq.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(resDiscipleReq, true);
            return resDiscipleReqMapper.insertSelective(resDiscipleReq);
        }
    }

    @Override
    public List<Map<String,String>> findDiscipleInfoFinbyDocFlow(List<String> discipleDocFlows) {
        return resDiscipleInfoExtMapper.searchDiscipleInfoFinbyDocFlow(discipleDocFlows);
    }

    @Override
    public List<Map<String,String>> findEveDiscipleInfoReq() {
        return resDiscipleInfoExtMapper.findEveDiscipleInfoReq();
    }
}
