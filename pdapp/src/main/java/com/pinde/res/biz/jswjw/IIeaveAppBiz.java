package com.pinde.res.biz.jswjw;

import com.pinde.core.model.*;
import com.pinde.res.model.jswjw.mo.ResDoctorKqExt;

import java.util.List;
import java.util.Map;

/**
 * @ClassName IIeaveAppBiz
 * @Deacription 考勤功能Biz
 * @Author shengl
 * @Date 2021-01-12 15:43
 * @Version 1.0
 **/
public interface IIeaveAppBiz {

    List<ResKgCfg> readKqCfgList(String orgFlow, String doctorCategoryId);

    double readAllIntervalDays(Map<String, String> daysMap);

    /**
     * @Author shengl
     * @Description //获取配置
     * @Date  2021-01-12
    **/
    String getCfgByCode(String code);

    List<SysUser> searchUserWithRole(Map<String, Object> paramMap);

    ResDoctorKq readResDoctorKq(String recordFlow);

    /**
     * @Author shengl
     * @Description //校验时间
     * @Date  2021-01-12
     * @Param [recordFlow, doctorFlow, startDate, endDate, kqTypeId] 
     * @return int
    **/
    int checkTime(String recordFlow, String doctorFlow, String startDate, String endDate, String kqTypeId, String sessionNumber);

    List<SysDict> getDictListByDictId(String id);

    int editResDoctorKq(ResDoctorKq kq, SysUser sysUser, ResDoctorKq old);
    
    /**
     * @Author shengl
     * @Description //更新考勤
     * @Date  2021-01-14
     * @Param [kq, sysUser, old] 
     * @return int
    **/
    int updateResDoctorKq(ResDoctorKq kq);

    /**
     * @Author shengl
     * @Description //请假列表
     * @Date  2021-01-12
    **/
    List<ResDoctorKq> leaveAndAppealList(List<String> status, ResDoctorKq kq);

    List<ResDoctorSchProcess> searchProcessByDoctor(String userFlow);

    List<ResDoctorKq> searchResDoctorKq(Map<String, Object> param);

    /**
     * @Author shengl
     * @Description // 查询数据总数
     * @Date  2021-01-14
    **/
    List<Map<String,Object>> queryResDoctorKqCount(Map<String, Object> param);

    List<Map<String, String>> searchDoctorKqList(ResDoctorKq kq);

    List<ResDoctorSchProcess> searchProcessByDoctorFlow(String userFlow);

    List<Map<String, Object>> searchDictList(String id);

    int checkLeaveTime(String recordFlow, String doctorFlow, String startDate, String endDate, String typeId);

    int addResDoctorKq(ResDoctorKqExt kqExt,SysUser user);

    List<ResDoctorKqLog> searchKqLogList(String kqRecordFlow, String typeId);

    int updateKqLogsRecordStatusN(String kqRecordFlow, String typeId);

    int editResDoctorKqNew(ResDoctorKq kq,SysUser user);

    ResDoctorKqLog searchKqLog(String kqRecordFlow, String typeId, String roleId);

    int addResDoctorKqLog(ResDoctorKqLog log, SysUser user);

    List<ResDoctorKq> searchAuditResDoctorKq(Map<String, Object> paramMap);

    int saveKqLog(ResDoctorKqLog kqLog, SysUser user);

    List<Map<String, Object>> queryResDoctorKqCountNew(Map<String, Object> paramMap);
}
