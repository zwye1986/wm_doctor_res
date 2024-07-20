package com.pinde.sci.biz.eval.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.eval.IExpertOrgSpeBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.ExpertOrgSpeMapper;
import com.pinde.sci.dao.eval.ExpertOrgSpeExtMapper;
import com.pinde.sci.model.mo.ExpertOrgSpe;
import com.pinde.sci.model.mo.ExpertOrgSpeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExpertOrgSpeBizImpl implements IExpertOrgSpeBiz {

    @Autowired
    private ExpertOrgSpeMapper expertOrgSpeMapper;
    @Autowired
    private ExpertOrgSpeExtMapper expertOrgSpeExtMapper;

    @Override
    public List<ExpertOrgSpe> readByOrgAndYear(String orgFlow, String evalYear, String recordStatus) {
        ExpertOrgSpeExample example=new ExpertOrgSpeExample();
        ExpertOrgSpeExample.Criteria criteria=example.createCriteria();
        criteria.andEvalYearEqualTo(evalYear).andOrgFlowEqualTo(orgFlow);
        if(StringUtil.isNotBlank(recordStatus))
        {
            criteria.andRecordStatusEqualTo(recordStatus);
        }
        return expertOrgSpeMapper.selectByExample(example);
    }

    @Override
    public ExpertOrgSpe readByFlow(String recordFlow) {
        return expertOrgSpeMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public ExpertOrgSpe getByOrgAndYear(String orgFlow, String evalYear, String speId, String recordStatus) {
        ExpertOrgSpeExample example=new ExpertOrgSpeExample();
        ExpertOrgSpeExample.Criteria criteria=example.createCriteria();
        criteria.andEvalYearEqualTo(evalYear).andOrgFlowEqualTo(orgFlow).andSpeIdEqualTo(speId);
        if(StringUtil.isNotBlank(recordStatus))
        {
            criteria.andRecordStatusEqualTo(recordStatus);
        }
        List<ExpertOrgSpe> list= expertOrgSpeMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return  list.get(0);
        }
        return null;
    }

    @Override
    public int save(ExpertOrgSpe old) {
        if(StringUtil.isBlank(old.getRecordFlow()))
        {
            old.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(old,true);
            return expertOrgSpeMapper.insertSelective(old);
        }else{
            GeneralMethod.setRecordInfo(old,false);
            return expertOrgSpeMapper.updateByPrimaryKeySelective(old);
        }
    }
}
