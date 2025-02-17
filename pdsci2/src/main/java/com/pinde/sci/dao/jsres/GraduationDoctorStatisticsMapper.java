package com.pinde.sci.dao.jsres;

import com.pinde.sci.form.jsres.GraduationDoctorStatisticsInfo;

import java.util.List;
import java.util.Map;

public interface GraduationDoctorStatisticsMapper {

    /**
     * @Department：研发部
     * @Description 按专业统计应结业学员信息-首考
     * @Author fengxf
     * @Date 2025/2/13
     */
    List<GraduationDoctorStatisticsInfo> searchFirstGraduationStatisticsBySpe(Map<String, Object> paramMap);

    /**
     * @Department：研发部
     * @Description 按专业统计应结业学员信息-补考
     * @Author fengxf
     * @Date 2025/2/13
     */
    List<GraduationDoctorStatisticsInfo> searchReExamGraduationStatisticsBySpe(Map<String, Object> paramMap);

    /**
     * @Department：研发部
     * @Description 按专业统计应结业学员信息-包含补考和首考
     * @Author fengxf
     * @Date 2025/2/14
     */
    List<GraduationDoctorStatisticsInfo> searchGraduationStatisticsBySpe(Map<String, Object> paramMap);

    /**
     * @Department：研发部
     * @Description 按基地统计应结业学员信息-首考
     * @Author fengxf
     * @Date 2025/2/13
     */
    List<GraduationDoctorStatisticsInfo> searchFirstGraduationStatisticsByOrg(Map<String, Object> paramMap);

    /**
     * @Department：研发部
     * @Description 按基地统计应结业学员信息-补考
     * @Author fengxf
     * @Date 2025/2/13
     */
    List<GraduationDoctorStatisticsInfo> searchReExamGraduationStatisticsByOrg(Map<String, Object> paramMap);

    /**
     * @Department：研发部
     * @Description 按基地统计应结业学员信息-包含补考和首考
     * @Author fengxf
     * @Date 2025/2/14
     */
    List<GraduationDoctorStatisticsInfo> searchGraduationStatisticsByOrg(Map<String, Object> paramMap);

    /**
     * @Department：研发部
     * @Description 查询应结业学员列表信息
     * @Author fengxf
     * @Date 2025/2/15
     */
    List<Map<String, String>> searchGraduationDoctorList(Map<String, Object> paramMap);

}