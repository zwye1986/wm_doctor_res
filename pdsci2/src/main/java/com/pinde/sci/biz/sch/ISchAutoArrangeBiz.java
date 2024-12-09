package com.pinde.sci.biz.sch;

import com.pinde.sci.model.mo.SchArrangeResult;
import com.pinde.sci.model.mo.SchAutoArrangeCfg;
import com.pinde.core.model.SysUser;

import java.util.List;


public interface ISchAutoArrangeBiz {


    int checkRotationLocal(String orgFlow, String sessionNumber);

    int checkDoctorCount(String orgFlow, String sessionNumber, String schFlag);

    int checkArrangeCount(String orgFlow, String sessionNumber);

    int checkDoctorCycleCount(String orgFlow, String sessionNumber, String schFlag);

    int checkOrgDoctorCount(String orgFlow, String sessionNumber, String schFlag);

    int checkDoctorSecondCount(String orgFlow, String sessionNumber, String schFlag);

    SchAutoArrangeCfg findSessionNumberStartDate(String sessionNumber, String orgFlow);

    int saveSessionNumberStartDate(SchAutoArrangeCfg cfg);

    String startAutoArrange(String sessionNumber, String orgFlow, String startDate, String flag);

    List<SchArrangeResult> getArrangeResult(String startDate, String endDate, String orgFlow, String sessionNumber, List<String> doctorFlows);

    List<SysUser> getUserList(String orgFlow, String sessionNumber);

    String reStartAutoArrange(String sessionNumber, String orgFlow, String startDate);

    int getLastDoctorCount(String orgFlow, String sessionNumber);
}
