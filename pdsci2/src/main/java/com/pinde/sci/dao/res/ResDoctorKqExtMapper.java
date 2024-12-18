package com.pinde.sci.dao.res;

import com.pinde.core.model.ResDoctorKq;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2016-10-18.
 */
public interface ResDoctorKqExtMapper {

    List<Map<String,String>> getSigninList(Map<String, Object> param);

    int checkTime(@Param("recordFlow") String recordFlow, @Param("doctorFlow") String doctorFlow, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("id") String id);

    double readAllIntervalDays(@Param("recordFlow") String recordFlow, @Param("doctorFlow") String doctorFlow, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("id") String id);

    List<Map<String,String>> getSigninListForMany(Map<String, Object> param);
    List<Map<String,Object>> getKqStatistics(Map<String,Object> paramMap);

    List<ResDoctorKq> kqStatisticsDetail(@Param("kq") ResDoctorKq kq);

    List<ResDoctorKq> searchResDoctorKq(Map<String, Object> param);

    List<Map<String,String>> searchDoctorKqList(Map<String, Object> paramMap);

    int updateKqLogsRecordStatusN(@Param("kqRecordFlow") String kqRecordFlow, @Param("typeId") String typeId);
}
