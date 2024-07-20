package com.pinde.sci.biz.eval.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.eval.IExpertEvalOrgSpeBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ExpertEvalOrgSpeCfgMapper;
import com.pinde.sci.dao.base.ExpertEvalOrgSpeMapper;
import com.pinde.sci.dao.base.SysOrgMapper;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.dao.eval.ExpertOrgSpeExtMapper;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/25.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExpertEvalOrgSpeBizImpl implements IExpertEvalOrgSpeBiz {
    @Autowired
    private SysOrgMapper orgMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private ExpertOrgSpeExtMapper expertOrgSpeExtMapper;
    @Autowired
    private ExpertEvalOrgSpeMapper expertEvalOrgSpeMapper;
    @Autowired
    private ExpertEvalOrgSpeCfgMapper expertEvalOrgSpeCfgMapper;

    @Override
    public List<Map<String, String>> expertOrgSpeList(Map<String, Object> paramMap) {
        return expertOrgSpeExtMapper.expertOrgSpeList(paramMap);
    }

    @Override
    public List<SysUser> getSpeExpertByYearAndOrgFlow(String evalYear, String orgFlow, String speId) {
        return expertOrgSpeExtMapper.getSpeExpertByYearAndOrgFlow(evalYear,orgFlow,speId);
    }

    @Override
    public List<ExpertEvalOrgSpe> readByOrgFlowAndYearAndSpeId(String evalYear, String orgFlow, String speId, String recordStatus) {
        ExpertEvalOrgSpeExample example=new ExpertEvalOrgSpeExample();
        ExpertEvalOrgSpeExample.Criteria criteria=example.createCriteria();
        criteria.andEvalYearEqualTo(evalYear).andOrgFlowEqualTo(orgFlow).andSpeIdEqualTo(speId);
        if(StringUtil.isNotBlank(recordStatus))
        {
            criteria.andRecordStatusEqualTo(recordStatus);
        }
        return expertEvalOrgSpeMapper.selectByExample(example);
    }

    @Override
    public ExpertEvalOrgSpe readByFlow(String recordFlow) {
        return expertEvalOrgSpeMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public ExpertEvalOrgSpe getByUserAndOrgAndYearAndSpe(String expertUserFlow, String orgFlow, String speId, String evalYear, String recordStatus) {
        ExpertEvalOrgSpeExample example=new ExpertEvalOrgSpeExample();
        ExpertEvalOrgSpeExample.Criteria criteria=example.createCriteria();
        criteria.andEvalYearEqualTo(evalYear).andOrgFlowEqualTo(orgFlow).andSpeIdEqualTo(speId).andExpertUserFlowEqualTo(expertUserFlow);
        if(StringUtil.isNotBlank(recordStatus))
        {
            criteria.andRecordStatusEqualTo(recordStatus);
        }
        List<ExpertEvalOrgSpe> list= expertEvalOrgSpeMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }
    @Override
    public List<ExpertEvalOrgSpeCfg> getCfgByUserAndOrgAndYear(String expertUserFlow, String orgFlow,String evalYear, String speId,  String recordStatus) {
        ExpertEvalOrgSpeCfgExample example=new ExpertEvalOrgSpeCfgExample();
        ExpertEvalOrgSpeCfgExample.Criteria criteria=example.createCriteria();
        criteria.andEvalYearEqualTo(evalYear).andOrgFlowEqualTo(orgFlow).andSpeIdEqualTo(speId).andExpertUserFlowEqualTo(expertUserFlow);
        if(StringUtil.isNotBlank(recordStatus))
        {
            criteria.andRecordStatusEqualTo(recordStatus);
        }
        List<ExpertEvalOrgSpeCfg> list= expertEvalOrgSpeCfgMapper.selectByExample(example);
        return list;
    }

    @Override
    public int saveEvalOrgSpe(ExpertEvalOrgSpe old) {
        if(GlobalConstant.RECORD_STATUS_N.equals(old.getRecordStatus()))
        {
            ExpertEvalOrgSpeCfgExample example = new ExpertEvalOrgSpeCfgExample();
            ExpertEvalOrgSpeCfgExample.Criteria criteria = example.createCriteria();
            criteria.andEvalYearEqualTo(old.getEvalYear()).andOrgFlowEqualTo(old.getOrgFlow()).andSpeIdEqualTo(old.getSpeId())
                    .andExpertUserFlowEqualTo(old.getExpertUserFlow());
            ExpertEvalOrgSpeCfg update = new ExpertEvalOrgSpeCfg();
            update.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            GeneralMethod.setRecordInfo(update, false);
            expertEvalOrgSpeCfgMapper.updateByExampleSelective(update, example);
        }
        return save(old);
    }

    @Override
    public int save(ExpertEvalOrgSpe old) {
        if(StringUtil.isBlank(old.getRecordFlow()))
        {
            old.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(old,true);
            return expertEvalOrgSpeMapper.insertSelective(old);
        }else{
            GeneralMethod.setRecordInfo(old,false);
            return expertEvalOrgSpeMapper.updateByPrimaryKeySelective(old);
        }
    }

    @Override
    public ExpertEvalOrgSpeCfg readExpertOrgSpeCfgByFlow(String recordFlow) {
        return expertEvalOrgSpeCfgMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public void saveExpertEvalOrgSpeCfg(String evalYear, String expertUserFlow, String orgFlow, String speId, String[] cfgFlows, String[] recordFlows) {
        if(recordFlows!=null) {
            ExpertEvalOrgSpeCfgExample example = new ExpertEvalOrgSpeCfgExample();
            ExpertEvalOrgSpeCfgExample.Criteria criteria = example.createCriteria().andRecordFlowIn(Arrays.asList(recordFlows));
            ExpertEvalOrgSpeCfg update = new ExpertEvalOrgSpeCfg();
            update.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            GeneralMethod.setRecordInfo(update, false);
            expertEvalOrgSpeCfgMapper.updateByExampleSelective(update, example);
        }
        SysOrg org=orgMapper.selectByPrimaryKey(orgFlow);
        SysUser u=userMapper.selectByPrimaryKey(expertUserFlow);
        if(cfgFlows!=null) {
            for (String cfgFlow : cfgFlows) {
                ExpertEvalOrgSpeCfg old = readExpertOrgSpeCfg(evalYear, expertUserFlow, orgFlow, cfgFlow, speId);
                if (old == null)
                    old = new ExpertEvalOrgSpeCfg();
                old.setCfgFlow(cfgFlow);
                old.setOrgFlow(orgFlow);
                if (org != null)
                    old.setOrgName(org.getOrgName());
                old.setExpertUserFlow(expertUserFlow);
                if (u != null)
                    old.setExpertUserName(u.getUserName());
                old.setEvalYear(evalYear);
                old.setSpeId(speId);
                old.setSpeName(DictTypeEnum.getDictName(DictTypeEnum.DoctorTrainingSpe, speId));
                old.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                saveCfg(old);
            }
        }
    }
    private ExpertEvalOrgSpeCfg readExpertOrgSpeCfg(String evalYear,String expertUserFlow, String orgFlow, String cfgFlow, String speId) {
        ExpertEvalOrgSpeCfgExample example=new ExpertEvalOrgSpeCfgExample();
        ExpertEvalOrgSpeCfgExample.Criteria criteria=example.createCriteria();
        criteria.andEvalYearEqualTo(evalYear).andOrgFlowEqualTo(orgFlow).andExpertUserFlowEqualTo(expertUserFlow)
                .andCfgFlowEqualTo(cfgFlow).andSpeIdEqualTo(speId);
        List<ExpertEvalOrgSpeCfg> list=expertEvalOrgSpeCfgMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    private int saveCfg(ExpertEvalOrgSpeCfg old) {
        if(StringUtil.isBlank(old.getRecordFlow()))
        {
            old.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(old,true);
            return expertEvalOrgSpeCfgMapper.insertSelective(old);
        }else{
            GeneralMethod.setRecordInfo(old,false);
            return expertEvalOrgSpeCfgMapper.updateByPrimaryKeySelective(old);
        }
    }
}
