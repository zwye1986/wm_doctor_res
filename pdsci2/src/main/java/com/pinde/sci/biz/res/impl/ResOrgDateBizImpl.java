package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResOrgDateBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.ResOrgSigninDateMapper;
import com.pinde.sci.model.mo.ResOrgSigninDate;
import com.pinde.sci.model.mo.ResOrgSigninDateExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class ResOrgDateBizImpl implements IResOrgDateBiz {

    @Autowired
    private ResOrgSigninDateMapper orgTimeMapper;
    @Override
    public List<ResOrgSigninDate> readOrgTime(String orgFlow, String startDate, String endDate) {
        if(StringUtil.isNotBlank(orgFlow)){
            ResOrgSigninDateExample example = new ResOrgSigninDateExample();
            ResOrgSigninDateExample.Criteria c= example.createCriteria().andOrgFlowEqualTo(orgFlow).
                    andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            if(StringUtil.isNotBlank(startDate))
            {
                c.andSigninDateGreaterThanOrEqualTo(startDate);
            }
            if(StringUtil.isNotBlank(endDate))
            {
                c.andSigninDateLessThanOrEqualTo(endDate);
            }
            example.setOrderByClause("signin_date");
            List<ResOrgSigninDate> list = orgTimeMapper.selectByExample(example);
            if(list.size()>0){
                return list;
            }
        }
        return null;
    }

    @Override
    public int saveOrgTime(ResOrgSigninDate time) {
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
    public ResOrgSigninDate readOrgTimeByFlow(String recordFlow) {
        return orgTimeMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public ResOrgSigninDate readOrgTimeByDate(String orgFlow, String signinDate) {
        ResOrgSigninDateExample example = new ResOrgSigninDateExample();
        ResOrgSigninDateExample.Criteria c= example.createCriteria().andOrgFlowEqualTo(orgFlow);
            c.andSigninDateEqualTo(signinDate);
        List<ResOrgSigninDate> list = orgTimeMapper.selectByExample(example);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

}
