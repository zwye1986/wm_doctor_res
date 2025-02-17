package com.pinde.sci.biz.jsres;

import com.pinde.sci.form.jsres.GraduationDoctorStatisticsInfo;

import java.util.List;
import java.util.Map;

public interface IGraduationDoctorStatisticsBiz {

    /**
     * @Department：研发部
     * @Description 按专业统计应结业学员信息
     * @Author fengxf
     * @Date 2025/2/13
     */
    List<GraduationDoctorStatisticsInfo> searchGraduationDoctorStatistics(Map<String, Object> paramMap);

    /**
     * @Department：研发部
     * @Description 查询应结业学员列表信息
     * @Author fengxf
     * @Date 2025/2/15
     */
    List<Map<String, String>> searchGraduationDoctorList(Map<String, Object> paramMap);
}