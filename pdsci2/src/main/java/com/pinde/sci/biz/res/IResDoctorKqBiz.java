package com.pinde.sci.biz.res;

import com.pinde.core.model.ResDoctorKq;
import com.pinde.core.model.ResDoctorKqLog;
import com.pinde.core.model.ResDoctorSchProcess;
import com.pinde.core.model.ResDoctorSignin;
import com.pinde.sci.form.res.TimeSetFrom;
import com.pinde.sci.model.mo.*;

import java.util.List;
import java.util.Map;

public interface IResDoctorKqBiz {
    //根据主键读取一条考勤记录
    ResDoctorKq readResDoctorKq(String recordFlow);
    //编辑考勤记录
    int editResDoctorKq(ResDoctorKq kq);
    //根据条件查询考勤记录
    List<ResDoctorKq> searchResDoctorKq(ResDoctorKq kq, List<String> notStatus);
    //根据条件查询考勤记录（扩展）
    List<ResDoctorKq> searchResDoctorKq(Map<String, Object> paramMap);
    //获取当前带教老师所有轮转中学员
    List<Map<String,Object>> teacherGetStudents(Map<String, Object> paramMap);
    //获取当前教秘所有轮转中学员
    List<Map<String,Object>> headGetStudents(Map<String, Object> paramMap);
    //签到列表
    List<Map<String,String>> getSigninList(Map<String, Object> param);

    List<ResKgCfg> readKqCfgList(String orgFlow, String doctorCategoryId);

    int saveKqCfgs(TimeSetFrom timeSetFrom);

    ResKgCfg getKqCfg(String lessOrGreater, String orgFlow, String doctorTypeId);

    int checkTime(String recordFlow, String doctorFlow, String startDate, String endDate, String id);

    double readAllIntervalDays(String recordFlow, String doctorFlow, String startDate, String endDate, String id);

    List<Map<String,String>> getSigninListForMany(Map<String, Object> param);

    List<ResDoctorSignin> queryDoctorSignins(ResDoctorSignin signin);
    //学员考勤统计表
    List<Map<String,Object>> getKqStatistics(Map<String,Object> paramMap);

    List<ResDoctorKq> kqStatisticsDetail(ResDoctorKq kq);

    List<Map<String,String>> searchDoctorKqList(ResDoctorKq kq);

    List<ResDoctorSchProcess> searchProcessByDoctorFlow(String userFlow);

    int saveKqLog(ResDoctorKqLog kqLog);

    List<ResDoctorKqLog> searchKqLogList(String kqRecordFlow, String typeId);

    void updateKqLogsRecordStatusN(String kqRecordFlow, String typeId);
}
