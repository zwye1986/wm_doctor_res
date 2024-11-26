package com.pinde.res.biz.common.impl;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.common.IResPowerCfgBiz;
import com.pinde.sci.dao.base.ResPowerCfgMapper;
import com.pinde.core.model.ResPowerCfg;
import com.pinde.core.model.ResPowerCfgExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2017/5/16.
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class ResPowerCfgBizImpl implements IResPowerCfgBiz {

    @Autowired
    private ResPowerCfgMapper resPowerCfgMapper;

    @Override
    public String getResPowerCfg(Map<String, String> paramMap) {
        ResPowerCfgExample example = new ResPowerCfgExample();
        ResPowerCfgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(paramMap.get("cfgCode"))) {
            criteria.andCfgCodeEqualTo(paramMap.get("cfgCode"));
        }
        if (StringUtil.isNotBlank(paramMap.get("loginDate"))) {
            criteria.andPowerEndTimeGreaterThanOrEqualTo(paramMap.get("loginDate"));
            criteria.andPowerStartTimeLessThanOrEqualTo(paramMap.get("loginDate"));
        }
        List<ResPowerCfg> resPowerCfgs = resPowerCfgMapper.selectByExample(example);
        String cfgValue = "";
        if (resPowerCfgs != null && resPowerCfgs.size() > 0) {
            cfgValue = resPowerCfgs.get(0).getCfgValue();
        }
        return cfgValue == null ? "" : cfgValue;
    }
    @Override
    public ResPowerCfg read(String cfgCode) {
        ResPowerCfg powerCfg =null;
        if(StringUtil.isNotBlank(cfgCode)){
            powerCfg =  resPowerCfgMapper.selectByPrimaryKey(cfgCode);
        }
        return powerCfg;
    }
}
