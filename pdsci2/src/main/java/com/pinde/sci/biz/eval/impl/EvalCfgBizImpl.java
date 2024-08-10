package com.pinde.sci.biz.eval.impl;

import com.pinde.core.entyties.SysDict;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.eval.IEvalCfgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ExpertEvalCfgMapper;
import com.pinde.sci.dao.base.ExpertEvalResultMapper;
import com.pinde.sci.dao.eval.ExpertEvalCfgExtMapper;
import com.pinde.sci.model.mo.ExpertEvalCfg;
import com.pinde.sci.model.mo.ExpertEvalCfgExample;
import com.pinde.sci.model.mo.ExpertEvalResult;
import com.pinde.sci.model.mo.ExpertEvalResultExample;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.DefaultAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/25.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EvalCfgBizImpl implements IEvalCfgBiz {

    @Autowired
    private ExpertEvalCfgMapper evalCfgMapper;
    @Autowired
    private ExpertEvalResultMapper resultMapper;
    @Autowired
    private ExpertEvalCfgExtMapper evalCfgExtMapper;
    @Override
    public List<ExpertEvalCfg> searchParentList(Map<String, Object> paramMap) {
        return evalCfgExtMapper.searchParentList(paramMap);
    }

    @Override
    public int save(ExpertEvalCfg cfg) {
        if(StringUtil.isBlank(cfg.getCfgFlow()))
        {
            cfg.setCfgFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(cfg,true);
            return evalCfgMapper.insertSelective(cfg);
        }else{
            GeneralMethod.setRecordInfo(cfg,false);
            return evalCfgMapper.updateByPrimaryKeySelective(cfg);
        }
    }

    @Override
    public List<ExpertEvalCfg> searchChildrenList(String cfgFlow) {
        if(StringUtil.isNotBlank(cfgFlow))
        {
            ExpertEvalCfgExample example=new ExpertEvalCfgExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andParentCfgFlowEqualTo(cfgFlow);
            example.setOrderByClause(" LEVEL_ID ,ORDINAL ");
            return evalCfgMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public ExpertEvalCfg readByFlow(String cfgFlow) {
        return evalCfgMapper.selectByPrimaryKey(cfgFlow);
    }

    @Override
    public ExpertEvalCfg findByEvalYear(String evalYear) {
        if(StringUtil.isNotBlank(evalYear))
        {

            ExpertEvalCfgExample example=new ExpertEvalCfgExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andEvalYearEqualTo(evalYear);
            List<ExpertEvalCfg>list=evalCfgMapper.selectByExample(example);
            if(list!=null&&list.size()>0)
            {
                return list.get(0);
            }
        }
        return null;
    }
    @Override
    public ExpertEvalCfg findByEvalYearFirst(String evalYear) {
        if(StringUtil.isNotBlank(evalYear))
        {
            ExpertEvalCfgExample example=new ExpertEvalCfgExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andEvalYearEqualTo(evalYear).andParentCfgFlowIsNull().andIsPublishEqualTo(GlobalConstant.FLAG_Y);
            List<ExpertEvalCfg>list=evalCfgMapper.selectByExample(example);
            if(list!=null&&list.size()>0)
            {
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public ExpertEvalCfg findByEvalYearNotSelf(String cfgFlow, String evalYear) {
        ExpertEvalCfgExample example=new ExpertEvalCfgExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andEvalYearEqualTo(evalYear).andCfgFlowNotEqualTo(cfgFlow).andParentCfgFlowIsNull();
        List<ExpertEvalCfg>list=evalCfgMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return  null;
    }
    @Override
    public ExpertEvalCfg findByCfgName(String cfgName, String evalYear) {
        if(StringUtil.isNotBlank(cfgName))
        {

            ExpertEvalCfgExample example=new ExpertEvalCfgExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andCfgNameEqualTo(cfgName).andEvalYearEqualTo(evalYear);
            List<ExpertEvalCfg>list=evalCfgMapper.selectByExample(example);
            if(list!=null&&list.size()>0)
            {
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public ExpertEvalCfg findByCfgNameNotSelf(String cfgFlow, String cfgName, String evalYear) {
        ExpertEvalCfgExample example=new ExpertEvalCfgExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andCfgNameEqualTo(cfgName).andCfgFlowNotEqualTo(cfgFlow).andEvalYearEqualTo(evalYear);
        List<ExpertEvalCfg>list=evalCfgMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return  null;
    }
    @Override
    public ExpertEvalCfg findByCfgNameNotSelfByParent(String cfgFlow, String cfgName, String evalYear,String parentCfgFlow) {
        ExpertEvalCfgExample example=new ExpertEvalCfgExample();
        ExpertEvalCfgExample.Criteria criteria= example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andCfgNameEqualTo(cfgName).andCfgFlowNotEqualTo(cfgFlow).andEvalYearEqualTo(evalYear);
        if(StringUtil.isNotBlank(parentCfgFlow))
        {
            criteria.andParentCfgFlowEqualTo(parentCfgFlow);
        }
        List<ExpertEvalCfg>list=evalCfgMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return  null;
    }

    @Override
    public void publishChildrenCfg(String cfgFlow) {
        ExpertEvalCfgExample example=new ExpertEvalCfgExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andParentCfgFlowEqualTo(cfgFlow);
        ExpertEvalCfg cfg=new ExpertEvalCfg();
        cfg.setIsPublish("Y");
        evalCfgMapper.updateByExampleSelective(cfg,example);
    }
    @Override
    public void delChildrenCfg(String cfgFlow) {
        ExpertEvalCfgExample example=new ExpertEvalCfgExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andParentCfgFlowEqualTo(cfgFlow);
        ExpertEvalCfg cfg=new ExpertEvalCfg();
        cfg.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        evalCfgMapper.updateByExampleSelective(cfg,example);
    }
    @Override
    public Integer getMaxOrdinal(String cfgFlow) {
        return  evalCfgExtMapper.getMaxOrdinal(cfgFlow);
    }

    @Override
    public List<ExpertEvalCfg> getChildrenListNotInCfg(String cfgFlow, String orgFlow, String expertUserFlow) {
        return evalCfgExtMapper.getChildrenListNotInCfg(cfgFlow,orgFlow,expertUserFlow);
    }
    @Override
    public List<ExpertEvalCfg> getChildrenListNotInCfgNotManage(String cfgFlow, String orgFlow, String expertUserFlow, String speId) {
        return evalCfgExtMapper.getChildrenListNotInCfgNotManage(cfgFlow,orgFlow,expertUserFlow,speId);
    }

    @Override
    public ExpertEvalCfg getFirstChildByType(String cfgFlow, String id) {
        ExpertEvalCfgExample example=new ExpertEvalCfgExample();
        ExpertEvalCfgExample.Criteria criteria= example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andParentCfgFlowEqualTo(cfgFlow);
        if(StringUtil.isNotBlank(id))
        {
            criteria.andTypeIdEqualTo(id);
        }
        List<ExpertEvalCfg>list=evalCfgMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return  null;
    }

    @Override
    public ExpertEvalResult getOrgCfgEvalReustl(String evalYear, String orgFlow, String cfgFlow) {
        ExpertEvalResultExample example=new ExpertEvalResultExample();
        ExpertEvalResultExample.Criteria criteria=example.createCriteria().andEvalYearEqualTo(evalYear)
                .andOrgFlowEqualTo(orgFlow).andCfgFlowEqualTo(cfgFlow);
        List<ExpertEvalResult> list=resultMapper.selectByExampleWithBLOBs(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }
    @Override
    public Map<String,Object> parseContent(String content) {
        Map<String,Object> formDataMap = null;
        if(StringUtil.isNotBlank(content)){
            formDataMap = new HashMap<String, Object>();
            try {
                Document document = DocumentHelper.parseText(content);
                Element rootElement = document.getRootElement();
                List<Element> elements = rootElement.elements();
                for(Element element : elements){
                    String name=element.getName();
                    if("scores".equals(name))
                    {
                        List<Map<String,String>> scores=new ArrayList<>();
                        List<Element> subs = element.elements();
                        for(Element sub:subs) {
                            Map<String, String> score = new HashMap<>();
                            List<DefaultAttribute> attrs=sub.attributes();
                            for(DefaultAttribute attr:attrs)
                            {
                                score.put(attr.getName(),attr.getStringValue());
                            }
                            score.put(sub.getName(),sub.getTextTrim());
                            if(score.size()>0)
                                scores.add(score);
                        }
                        formDataMap.put(element.getName(), scores);
                    }else{
                        formDataMap.put(element.getName(), element.getTextTrim());
                    }
                }
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        return formDataMap;
    }

    @Override
    public ExpertEvalCfg getSpeCfg(String evalYear, String speId) {
        ExpertEvalCfgExample example=new ExpertEvalCfgExample();
        ExpertEvalCfgExample.Criteria criteria= example.createCriteria()
                .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andEvalYearEqualTo(evalYear)
                .andSpeIdEqualTo(speId);
        List<ExpertEvalCfg>list=evalCfgMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return  null;
    }

    @Override
    public List<SysDict> getSpeListNotSelf(String cfgFlow) {
        return evalCfgExtMapper.getSpeListNotSelf(cfgFlow);
    }

    @Override
    public ExpertEvalCfg checkSpeCfgNotSelf(String cfgFlow, String speId) {
        List<ExpertEvalCfg> list=evalCfgExtMapper.checkSpeCfgNotSelf(cfgFlow,speId);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return  null;
    }

    @Override
    public ExpertEvalCfg findByCfgNameByParent(String cfgName, String evalYear, String parentCfgFlow) {
        if(StringUtil.isNotBlank(cfgName))
        {
            ExpertEvalCfgExample example=new ExpertEvalCfgExample();
            ExpertEvalCfgExample.Criteria criteria= example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andCfgNameEqualTo(cfgName).andEvalYearEqualTo(evalYear);
            if(StringUtil.isNotBlank(parentCfgFlow))
            {
                criteria.andParentCfgFlowEqualTo(parentCfgFlow);
            }
            List<ExpertEvalCfg>list=evalCfgMapper.selectByExample(example);
            if(list!=null&&list.size()>0)
            {
                return list.get(0);
            }
        }
        return null;
    }
}
