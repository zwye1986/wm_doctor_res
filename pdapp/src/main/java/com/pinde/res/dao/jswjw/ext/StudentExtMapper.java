package com.pinde.res.dao.jswjw.ext;

import com.pinde.core.model.ResDoctorKq;
import com.pinde.core.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName StudentExtMapper
 * @Deacription APP学员功能
 * @Author shengl
 * @Date 2021-01-12 15:50
 * @Version 1.0
 **/
public interface StudentExtMapper {

    //double readAllIntervalDays(@Param("recordFlow") String recordFlow, @Param("doctorFlow") String doctorFlow, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("id") String id);

    double readAllIntervalDays(Map<String, String> daysMap);

    List<SysUser> searchUserWithRole(Map<String, Object> paramMap);

    int checkTime(@Param("recordFlow") String recordFlow, @Param("doctorFlow") String doctorFlow, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("id") String id, @Param("sessionNumber") String sessionNumber);

    List<ResDoctorKq> searchResDoctorKq(Map<String, Object> param);

    /**
     * @param param
     * @Author shengl
     * @Description // 查询数据总数
     * @Date 2021-01-14
     */
    List<Map<String,Object>> queryResDoctorKqCount(Map<String, Object> param);

    List<ResDoctorKq> searchAuditResDoctorKq(Map<String, Object> paramMap);

    List<Map<String, Object>> queryResDoctorKqCountNew(Map<String, Object> param);
}
