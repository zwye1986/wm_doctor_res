package com.pinde.sci.biz.jsres;

import com.pinde.core.model.*;

import java.util.List;
import java.util.Map;

public interface IJsResMonthlyReportBiz {
    //根据条件获取报表月份
    List<SysMonthly> getMonths(SysMonthly monthly);
    //根据条件查询SYS_MONTHLY_DOCTOR_INFO
    List<SysMonthlyDoctorInfo> getMonthlyDoctorInfo(SysMonthlyDoctorInfo sysMonthlyDoctorInfo,List<String> allOrgFlow);

    List<SysMonthlyDoctorInfo> getMonthlyDoctorInfo2(SysMonthlyDoctorInfo sysMonthlyDoctorInfo);
    //根据条件查询SYS_MONTHLY_DOCTOR_DETAIL_INFO
    List<SysMonthlyDoctorDetailInfo> getMonthlyDoctorDetailInfo(SysMonthlyDoctorDetailInfo sysMonthlyDoctorDetailInfo);
    //根据条件查询SYS_MONTHLY_CHANGE_INFO
    List<SysMonthlyChangeInfo> getSysMonthlyChangeInfo(SysMonthlyChangeInfo sysMonthlyChangeInfo);
    //根据条件查询SYS_MONTHLY_RETURN_DELAY_INFO
    List<SysMonthlyReturnDelayInfo> getSysMonthlyReturnDelayInfo(SysMonthlyReturnDelayInfo sysMonthlyReturnDelayInfo);
    //根据条件查询SYS_MONTHLY_DEPT_CYCLE_INFO
    List<SysMonthlyDeptCycleInfo> getSysMonthlyDeptCycleInfo(SysMonthlyDeptCycleInfo sysMonthlyDeptCycleInfo);
    //查询出科考核情况（图6）4个数据
    Map<String,Object> getChart6Data(Map<String,Object> paramMap);
    //根据条件查询SYS_MONTHLY_DOC_CYCLE_INFO
    List<SysMonthlyDocCycleInfo> getSysMonthlyDocCycleInfo(SysMonthlyDocCycleInfo sysMonthlyDocCycleInfo);
    //查询教学活动统计的4组数据
    Map<String,Object> getChart9Data(Map<String,Object> paramMap);
    //高校查询教学活动统计的4组数据
    Map<String,Object> getChart9Data2(Map<String,Object> paramMap);
    //根据条件查询SYS_MONTHLY_ACTIVITY_INFO
    List<SysMonthlyActivityInfo> getSysMonthlyActivityInfo(String dateMonth, List<String> orgFlows);

    //高校查询出科人数入科人数异常人数
    Map<String,Object> getUniverseChart5Data(Map<String,Object> paramMap);
    //查询黑名单
    List<SysMonthlyBlacklistInfo> getBlacklist(SysMonthlyBlacklistInfo blacklistInfo);
}
