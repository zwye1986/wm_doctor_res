package com.pinde.sci.biz.eval.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.eval.IExpertEvalOrgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.eval.ExpertEvalOrgExtMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExpertEvalOrgBizImpl implements IExpertEvalOrgBiz {
    @Autowired
    private SysOrgMapper orgMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private ExpertEvalOrgExtMapper expertEvalOrgExtMapper;
    @Autowired
    private ExpertEvalOrgMapper expertEvalOrgMapper;
    @Autowired
    private ExpertEvalOrgCfgMapper expertEvalOrgCfgMapper;

    @Override
    public List<ExpertEvalOrg> readByUserFlowAndYear(String userFlow, String evalYear,String recordStatus) {
        ExpertEvalOrgExample example=new ExpertEvalOrgExample();
        ExpertEvalOrgExample.Criteria criteria=  example.createCriteria().andEvalYearEqualTo(evalYear).andExpertUserFlowEqualTo(userFlow);
        if(StringUtil.isNotBlank(recordStatus))
        {
            criteria.andRecordStatusEqualTo(recordStatus);
        }
        return expertEvalOrgMapper.selectByExample(example);
    }
    @Override
    public List<ExpertEvalOrgCfg> getCfgByUserAndOrgAndYear(String userFlow, String orgFlow,  String evalYear,String recordStatus) {
        ExpertEvalOrgCfgExample example=new ExpertEvalOrgCfgExample();
        ExpertEvalOrgCfgExample.Criteria criteria=  example.createCriteria().
                andEvalYearEqualTo(evalYear).andExpertUserFlowEqualTo(userFlow).andOrgFlowEqualTo(orgFlow);
        if(StringUtil.isNotBlank(recordStatus))
        {
            criteria.andRecordStatusEqualTo(recordStatus);
        }
        return expertEvalOrgCfgMapper.selectByExample(example);
    }

    @Override
    public ExpertEvalOrgCfg readExpertOrgCfgByFlow(String recordFlow) {
        return expertEvalOrgCfgMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public int checkCfgHasResult(String recordFlow) {
        return expertEvalOrgExtMapper.checkCfgHasResult(recordFlow);
    }
    @Override
    public int checkSpeCfgHasResult(String recordFlow, String orgFlow, String speId) {
        return expertEvalOrgExtMapper.checkSpeCfgHasResult(recordFlow,orgFlow,speId);
    }

    @Override
    public int saveEvalOrg(ExpertEvalOrg old) {
        if(GlobalConstant.RECORD_STATUS_N.equals(old.getRecordStatus())){

            ExpertEvalOrgCfgExample example = new ExpertEvalOrgCfgExample();
            ExpertEvalOrgCfgExample.Criteria criteria = example.createCriteria().
                    andEvalYearEqualTo(old.getEvalYear()).andExpertUserFlowEqualTo(old.getExpertUserFlow()).andOrgFlowEqualTo(old.getOrgFlow());
            ExpertEvalOrgCfg update = new ExpertEvalOrgCfg();
            update.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            GeneralMethod.setRecordInfo(update, false);
            expertEvalOrgCfgMapper.updateByExampleSelective(update, example);
        }
        return save(old);
    }

    @Override
    public void saveExpertEvalOrgCfg(String evalYear,String expertUserFlow, String orgFlow, String[] cfgFlows, String[] recordFlows) {
       if(recordFlows!=null) {
           ExpertEvalOrgCfgExample example = new ExpertEvalOrgCfgExample();
           ExpertEvalOrgCfgExample.Criteria criteria = example.createCriteria().andRecordFlowIn(Arrays.asList(recordFlows));
           ExpertEvalOrgCfg update = new ExpertEvalOrgCfg();
           update.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
           GeneralMethod.setRecordInfo(update, false);
           expertEvalOrgCfgMapper.updateByExampleSelective(update, example);
       }
        SysOrg org=orgMapper.selectByPrimaryKey(orgFlow);
        SysUser u=userMapper.selectByPrimaryKey(expertUserFlow);
        if(cfgFlows!=null) {
            for (String cfgFlow : cfgFlows) {
                ExpertEvalOrgCfg old = readExpertOrgCfg(evalYear, expertUserFlow, orgFlow, cfgFlow);
                if (old == null)
                    old = new ExpertEvalOrgCfg();
                old.setCfgFlow(cfgFlow);
                old.setOrgFlow(orgFlow);
                if (org != null)
                    old.setOrgName(org.getOrgName());
                old.setExpertUserFlow(expertUserFlow);
                if (u != null)
                    old.setExpertUserName(u.getUserName());
                old.setEvalYear(evalYear);
                old.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                saveCfg(old);
            }
        }
    }

    @Override
    public List<SysUser> getOrgExpertByYearAndOrgFlow(String evalYear, String orgFlow) {
        return expertEvalOrgExtMapper.getOrgExpertByYearAndOrgFlow(evalYear,orgFlow);
    }

    @Override
    public int saveCfg(ExpertEvalOrgCfg old) {
        if(StringUtil.isBlank(old.getRecordFlow()))
        {
            old.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(old,true);
            return expertEvalOrgCfgMapper.insertSelective(old);
        }else{
            GeneralMethod.setRecordInfo(old,false);
            return expertEvalOrgCfgMapper.updateByPrimaryKeySelective(old);
        }
    }
    private ExpertEvalOrgCfg readExpertOrgCfg(String evalYear,String expertUserFlow, String orgFlow, String cfgFlow) {
        ExpertEvalOrgCfgExample example=new ExpertEvalOrgCfgExample();
        ExpertEvalOrgCfgExample.Criteria criteria=example.createCriteria();
        criteria.andEvalYearEqualTo(evalYear).andOrgFlowEqualTo(orgFlow).andExpertUserFlowEqualTo(expertUserFlow)
        .andCfgFlowEqualTo(cfgFlow);
        List<ExpertEvalOrgCfg> list=expertEvalOrgCfgMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public ExpertEvalOrg readByFlow(String recordFlow) {
        return expertEvalOrgMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public ExpertEvalOrg getByUserAndOrgAndYear(String expertUserFlow, String orgFlow, String evalYear, String recordStatus) {
        ExpertEvalOrgExample example=new ExpertEvalOrgExample();
        ExpertEvalOrgExample.Criteria criteria=example.createCriteria();
        criteria.andEvalYearEqualTo(evalYear).andOrgFlowEqualTo(orgFlow).andExpertUserFlowEqualTo(expertUserFlow);
        if(StringUtil.isNotBlank(recordStatus))
        {
            criteria.andRecordStatusEqualTo(recordStatus);
        }
        List<ExpertEvalOrg> list=expertEvalOrgMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }
    @Override
    public List<ExpertEvalOrg> readByOrgFlowAndYear(String orgFlow, String evalYear, String recordStatus) {
        ExpertEvalOrgExample example=new ExpertEvalOrgExample();
        ExpertEvalOrgExample.Criteria criteria=example.createCriteria();
        criteria.andEvalYearEqualTo(evalYear).andOrgFlowEqualTo(orgFlow);
        if(StringUtil.isNotBlank(recordStatus))
        {
            criteria.andRecordStatusEqualTo(recordStatus);
        }
        return expertEvalOrgMapper.selectByExample(example);
    }

    @Override
    public int save(ExpertEvalOrg old) {
        if(StringUtil.isBlank(old.getRecordFlow()))
        {
            old.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(old,true);
            return expertEvalOrgMapper.insertSelective(old);
        }else{
            GeneralMethod.setRecordInfo(old,false);
            return expertEvalOrgMapper.updateByPrimaryKeySelective(old);
        }
    }
}
