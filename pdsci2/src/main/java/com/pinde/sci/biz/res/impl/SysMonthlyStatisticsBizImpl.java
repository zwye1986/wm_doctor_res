package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.ISysMonthlyStatisticsBiz;
import com.pinde.sci.dao.base.SysMonthlyAppStatisticsMapper;
import com.pinde.sci.dao.base.SysMonthlyStatisticsMapper;
import com.pinde.sci.model.mo.SysMonthlyAppStatistics;
import com.pinde.sci.model.mo.SysMonthlyAppStatisticsExample;
import com.pinde.sci.model.mo.SysMonthlyStatistics;
import com.pinde.sci.model.mo.SysMonthlyStatisticsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class SysMonthlyStatisticsBizImpl implements ISysMonthlyStatisticsBiz {
    @Autowired
    private SysMonthlyStatisticsMapper sysMonthlyStatisticsMapper;
    @Autowired
    private SysMonthlyAppStatisticsMapper sysMonthlyAppStatisticsMapper;

    @Override
    public List<SysMonthlyStatistics> searchStatistics(String startMonth, String endMonth, SysMonthlyStatistics monthlyStatistics) {
        SysMonthlyStatisticsExample example = new SysMonthlyStatisticsExample();
        SysMonthlyStatisticsExample.Criteria criteria = example.createCriteria();
        if(StringUtil.isNotBlank(startMonth)){
            criteria.andDateMonthGreaterThanOrEqualTo(startMonth);
        }
        if(StringUtil.isNotBlank(endMonth)){
            criteria.andDateMonthLessThanOrEqualTo(endMonth);
        }
        if(null!=monthlyStatistics){
            if(StringUtil.isNotBlank(monthlyStatistics.getDoctorTypeId())){
                criteria.andDoctorTypeIdEqualTo(monthlyStatistics.getDoctorTypeId());
            }
        }
        return sysMonthlyStatisticsMapper.selectByExample(example);
    }

    @Override
    public List<SysMonthlyAppStatistics> searchAppStatistics(String startMonth, String endMonth, SysMonthlyAppStatistics monthlyAppStatistics) {
        SysMonthlyAppStatisticsExample example = new SysMonthlyAppStatisticsExample();
        SysMonthlyAppStatisticsExample.Criteria criteria = example.createCriteria();
        if(StringUtil.isNotBlank(endMonth)){
            criteria.andDateMonthLessThanOrEqualTo(endMonth);
        }
        if(null!=monthlyAppStatistics){
            if(StringUtil.isNotBlank(monthlyAppStatistics.getDoctorTypeId())){
                criteria.andDoctorTypeIdEqualTo(monthlyAppStatistics.getDoctorTypeId());
            }
        }
        return sysMonthlyAppStatisticsMapper.selectByExample(example);
    }
}
