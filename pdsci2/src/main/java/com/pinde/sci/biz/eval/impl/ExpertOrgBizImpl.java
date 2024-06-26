package com.pinde.sci.biz.eval.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.eval.IExpertOrgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ExpertEvalResultMapper;
import com.pinde.sci.dao.base.ExpertOrgMapper;
import com.pinde.sci.dao.eval.ExpertOrgExtMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/25.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExpertOrgBizImpl implements IExpertOrgBiz {

    @Autowired
    private ExpertOrgMapper expertOrgMapper;
    @Autowired
    private ExpertOrgExtMapper expertOrgExtMapper;
    @Autowired
    private ExpertEvalResultMapper expertEvalResultMapper;

    @Override
    public List<SysOrg> searchOrg(Map<String, Object> paramMap) {
        return expertOrgExtMapper.searchOrg(paramMap);
    }

    @Override
    public ExpertOrg readByFlow(String recordFlow) {
        return expertOrgMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public int save(ExpertOrg old) {
        if(StringUtil.isBlank(old.getRecordFlow()))
        {
            old.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(old,true);
            return expertOrgMapper.insertSelective(old);
        }else{
            GeneralMethod.setRecordInfo(old,false);
            return expertOrgMapper.updateByPrimaryKeySelective(old);
        }
    }

    @Override
    public ExpertOrg getByOrgAndYear(String orgFlow, String evalYear, String recordStatus) {
        ExpertOrgExample example=new ExpertOrgExample();
        ExpertOrgExample.Criteria criteria=example.createCriteria();
        criteria.andEvalYearEqualTo(evalYear).andOrgFlowEqualTo(orgFlow);
        if(StringUtil.isNotBlank(recordStatus))
        {
            criteria.andRecordStatusEqualTo(recordStatus);
        }
        List<ExpertOrg> list=expertOrgMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int getExpertSpeByOrgAndYear(String orgFlow, String evalYear) {
        return expertOrgExtMapper.getExpertSpeByOrgAndYear(orgFlow,evalYear);
    }

    @Override
    public int findExpertOrgResult(String evalYear, String orgFlow, String speId, String expertUserFlow) {
        ExpertEvalResultExample example=new ExpertEvalResultExample();
        ExpertEvalResultExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andOrgFlowEqualTo(orgFlow).andEvalYearEqualTo(evalYear);
        if(StringUtil.isNotBlank(speId))
        {
            criteria.andSpeIdEqualTo(speId);
        }
        if(StringUtil.isNotBlank(expertUserFlow))
        {
            criteria.andExpertUserFlowEqualTo(expertUserFlow);
        }

        return expertEvalResultMapper.countByExample(example);
    }

    @Override
    public List<SysOrg> evalOrgQueryList(Map<String, Object> paramMap) {
        return expertOrgExtMapper.evalOrgQueryList(paramMap);
    }

    @Override
    public List<Map<String, Object>> showOrgClinicalList(Map<String, Object> paramMap) {
        return expertOrgExtMapper.showOrgClinicalList(paramMap);
    }
    @Override
    public List<Map<String, Object>> evalOrgQueryOrgSpePm(Map<String, Object> paramMap) {
        return expertOrgExtMapper.evalOrgQueryOrgSpePm(paramMap);
    }
    @Override
    public List<Map<String, Object>> evalOrgQueryOrgPm(Map<String, Object> paramMap) {
        return expertOrgExtMapper.evalOrgQueryOrgPm(paramMap);
    }
}
