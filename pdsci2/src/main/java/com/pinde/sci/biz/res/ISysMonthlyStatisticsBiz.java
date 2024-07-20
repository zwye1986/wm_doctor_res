package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.SysMonthlyAppStatistics;
import com.pinde.sci.model.mo.SysMonthlyStatistics;

import java.util.List;

public interface ISysMonthlyStatisticsBiz {
    //根据条件查询报表数据
    List<SysMonthlyStatistics> searchStatistics(String startMonth,String endMonth,SysMonthlyStatistics monthlyStatistics);
    //根据条件查询APP报表数据
    List<SysMonthlyAppStatistics> searchAppStatistics(String startMonth, String endMonth, SysMonthlyAppStatistics monthlyAppStatistics);
}
