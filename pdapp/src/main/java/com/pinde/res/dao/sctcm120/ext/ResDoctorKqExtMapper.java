package com.pinde.res.dao.sctcm120.ext;

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

    List<ResDoctorKq> searchResDoctorKq(Map<String, Object> param);

    List<Map<String, String>> searchDoctorKqList(Map<String, Object> paramMap);

    List<Map<String, Object>> searchDictList(@Param("dictTypeId") String dictTypeId);

    int checkLeaveTime(@Param("recordFlow") String recordFlow, @Param("doctorFlow") String doctorFlow, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("typeId") String typeId);

    int updateKqLogsRecordStatusN(@Param("kqRecordFlow") String kqRecordFlow, @Param("typeId") String typeId);

    List<Map<String, Object>> getKqStatistics(Map<String, Object> paramMap);

    List<ResDoctorKq> kqStatisticsDetail(ResDoctorKq kq);
}
