package com.pinde.sci.biz.sys.impl;

import com.pinde.core.common.sci.dao.SysWsConfigMapper;
import com.pinde.core.model.SysWsConfig;
import com.pinde.core.model.SysWsConfigExample;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.IWsCfgBiz;
import com.pinde.sci.common.GeneralMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor = Exception.class)
public class WsCfgBizImpl implements IWsCfgBiz {

    @Autowired
    private SysWsConfigMapper sysWsConfigMapper;
    @Override
    public List<SysWsConfig> searchList(String wsId) {
        SysWsConfigExample example=new SysWsConfigExample();
        SysWsConfigExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(wsId))
        {
            criteria.andWsIdEqualTo(wsId);
        }
        return sysWsConfigMapper.selectByExample(example);
    }

    @Override
    public SysWsConfig searchByKey(String wsId) {
        return sysWsConfigMapper.selectByPrimaryKey(wsId);
    }

    @Override
    public int save(SysWsConfig config) {
        SysWsConfig old=sysWsConfigMapper.selectByPrimaryKey(config.getWsId());
        if(old==null)
        {
            GeneralMethod.setRecordInfo(config,true);
            return sysWsConfigMapper.insertSelective(config);
        }else {
            GeneralMethod.setRecordInfo(config,false);
            return sysWsConfigMapper.updateByPrimaryKeySelective(config);
        }
    }

    @Override
    public int updateDefault(SysWsConfig config) {
        updateAllNotDefault();
        return sysWsConfigMapper.updateByPrimaryKeySelective(config);
    }

    @Override
    public SysWsConfig getDefaultCfg() {
        SysWsConfigExample example=new SysWsConfigExample();
        SysWsConfigExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andIsDefaultEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        List<SysWsConfig> list= sysWsConfigMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    private void updateAllNotDefault() {
        SysWsConfig config=new SysWsConfig();
        config.setIsDefault(com.pinde.core.common.GlobalConstant.FLAG_N);
        SysWsConfigExample e=new SysWsConfigExample();
        sysWsConfigMapper.updateByExampleSelective(config,e);
    }
}
