package com.pinde.res.biz.stdp.impl;

import com.pinde.app.common.GlobalConstant;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.stdp.IResOrgTimeBiz;
import com.pinde.sci.dao.base.ResOrgAddressMapper;
import com.pinde.sci.dao.base.ResOrgTimeMapper;
import com.pinde.sci.model.mo.ResOrgAddress;
import com.pinde.sci.model.mo.ResOrgAddressExample;
import com.pinde.sci.model.mo.ResOrgTime;
import com.pinde.sci.model.mo.ResOrgTimeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class ResOrgTimeBizImpl implements IResOrgTimeBiz {

    @Autowired
    private ResOrgTimeMapper orgTimeMapper;
    @Autowired
    private ResOrgAddressMapper orgAddressMapper;
    @Override
    public List<ResOrgTime> readOrgTime(String orgFlow) {
        if(StringUtil.isNotBlank(orgFlow)){
            ResOrgTimeExample example = new ResOrgTimeExample();
            example.createCriteria().andOrgFlowEqualTo(orgFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            example.setOrderByClause("start_time");
            List<ResOrgTime> list = orgTimeMapper.selectByExample(example);
            if(list.size()>0){
                return list;
            }
        }
        return null;
    }


    @Override
    public ResOrgTime readOrgTimeByFlow(String recordFlow) {
        return orgTimeMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public ResOrgTime readOrgOneTime(String orgFlow) {
        if(StringUtil.isNotBlank(orgFlow)){
            ResOrgTimeExample example = new ResOrgTimeExample();
            example.createCriteria().andOrgFlowEqualTo(orgFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            List<ResOrgTime> list = orgTimeMapper.selectByExample(example);
            if(list.size()>0){
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public List<ResOrgAddress> readOrgAddress(String orgFlow) {
        if(StringUtil.isNotBlank(orgFlow)){
            ResOrgAddressExample example = new ResOrgAddressExample();
            example.createCriteria().andOrgFlowEqualTo(orgFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            List<ResOrgAddress> list = orgAddressMapper.selectByExample(example);
            if(list.size()>0){
                return list;
            }
        }
        return null;
    }
}
