package com.pinde.sci.biz.sys.impl;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.biz.sys.IWsCfgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.SysWsConfigMapper;
import com.pinde.sci.model.mo.SysWsConfig;
import com.pinde.sci.model.mo.SysWsConfigExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class WsCfgBizImpl implements IWsCfgBiz {

    @Autowired
    private SysWsConfigMapper sysWsConfigMapper;
    @Override
    public List<SysWsConfig> searchList(String wsId) {
        SysWsConfigExample example=new SysWsConfigExample();
        SysWsConfigExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
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
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andIsDefaultEqualTo(GlobalConstant.FLAG_Y);
        List<SysWsConfig> list= sysWsConfigMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    private void updateAllNotDefault() {
        SysWsConfig config=new SysWsConfig();
        config.setIsDefault(GlobalConstant.FLAG_N);
        SysWsConfigExample e=new SysWsConfigExample();
        sysWsConfigMapper.updateByExampleSelective(config,e);
    }
}
