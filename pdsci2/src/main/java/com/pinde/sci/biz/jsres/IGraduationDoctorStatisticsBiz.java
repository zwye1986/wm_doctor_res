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
    List<GraduationDoctorStatisticsInfo> searchGraduationDoctorStatisticsBySpe(Map<String, Object> paramMap);
}