package com.pinde.sci.biz.res.impl;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResOrgTimeBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.ResOrgTimeMapper;
import com.pinde.sci.form.res.ResOrgTimeForm;
import com.pinde.sci.model.mo.ResOrgTime;
import com.pinde.sci.model.mo.ResOrgTimeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class ResOrgTimeBizImpl implements IResOrgTimeBiz {

    @Autowired
    private ResOrgTimeMapper orgTimeMapper;
    @Override
    public List<ResOrgTime> readOrgTime(String orgFlow) {
        if(StringUtil.isNotBlank(orgFlow)){
            ResOrgTimeExample example = new ResOrgTimeExample();
            example.createCriteria().andOrgFlowEqualTo(orgFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            example.setOrderByClause("start_time");
            List<ResOrgTime> list = orgTimeMapper.selectByExample(example);
            if(list.size()>0){
                return list;
            }
        }
        return null;
    }

    @Override
    public int saveOrgTime(ResOrgTime time) {
        int result=0;
        if(time!=null){
            if(StringUtil.isBlank(time.getRecordFlow())){//新增
                time.setRecordFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(time,true);
                result = orgTimeMapper.insert(time);
            }else{//修改
                GeneralMethod.setRecordInfo(time,false);
                result = orgTimeMapper.updateByPrimaryKeySelective(time);
            }
        }
        return result;
    }

    @Override
    public ResOrgTime readOrgTimeByFlow(String recordFlow) {
        return orgTimeMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public ResOrgTime readOrgOneTime(String orgFlow) {
        if(StringUtil.isNotBlank(orgFlow)){
            ResOrgTimeExample example = new ResOrgTimeExample();
            example.createCriteria().andOrgFlowEqualTo(orgFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            List<ResOrgTime> list = orgTimeMapper.selectByExample(example);
            if(list.size()>0){
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public int saveOrgTimes(ResOrgTimeForm bean) {
        if(bean!=null&&bean.getTimes()!=null&&bean.getTimes().size()>0)
        {
            int c=0;
            List<ResOrgTime> Timees=bean.getTimes();
            List<String> recordFlows=new ArrayList<>();
            for(ResOrgTime time:Timees)
            {
                time.setOrgFlow(bean.getOrgFlow());
                c+=saveOrgTime(time);
                recordFlows.add(time.getRecordFlow());
            }
            if(c>0)
            {
                ResOrgTimeExample example = new ResOrgTimeExample();
                example.createCriteria().andOrgFlowEqualTo(bean.getOrgFlow())
                        .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRecordFlowNotIn(recordFlows);
                ResOrgTime time=new ResOrgTime();
                time.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
                orgTimeMapper.updateByExampleSelective(time,example);

            }
            return c;

        }
        return 0;
    }
}
