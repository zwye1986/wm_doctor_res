package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IGraduationDoctorStatisticsBiz;
import com.pinde.sci.dao.jsres.GraduationDoctorStatisticsMapper;
import com.pinde.sci.form.jsres.GraduationDoctorStatisticsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GraduationDoctorStatisticsBizImpl implements IGraduationDoctorStatisticsBiz {

    @Autowired
    private GraduationDoctorStatisticsMapper graduationDoctorStatisticsMapper;


    /**
     * @param paramMap
     * @Department：研发部
     * @Description 统计应结业学员信息
     * @Author fengxf
     * @Date 2025/2/13
     */
    @Override
    public List<GraduationDoctorStatisticsInfo> searchGraduationDoctorStatistics(Map<String, Object> paramMap) {
        // 考试类型 firstExam 首考 reExam 补考
        String examType = (String) paramMap.get("examType");
        // 查询类型 orgStatistics 按基地统计 speStatistics 按专业统计
        String queryType = (String) paramMap.get("queryType");
        // 按基地统计
        if("orgStatistics".equals(queryType)){
            if(StringUtil.isNotBlank(examType)) {
                if("firstExam".equals(examType)){
                    // 首考
                    return graduationDoctorStatisticsMapper.searchFirstGraduationStatisticsByOrg(paramMap);
                } else if("reExam".equals(examType)){
                    // 补考
                    return graduationDoctorStatisticsMapper.searchReExamGraduationStatisticsByOrg(paramMap);
                }
            }
            // 全部
            return graduationDoctorStatisticsMapper.searchGraduationStatisticsByOrg(paramMap);
        }else{
            if(StringUtil.isNotBlank(examType)) {
                if("firstExam".equals(examType)){
                    // 首考
                    return graduationDoctorStatisticsMapper.searchFirstGraduationStatisticsBySpe(paramMap);
                } else if("reExam".equals(examType)){
                    // 补考
                    return graduationDoctorStatisticsMapper.searchReExamGraduationStatisticsBySpe(paramMap);
                }
            }
            // 全部
            return graduationDoctorStatisticsMapper.searchGraduationStatisticsBySpe(paramMap);
        }
    }

    /**
     * @param paramMap
     * @Department：研发部
     * @Description 查询应结业学员列表信息
     * @Author fengxf
     * @Date 2025/2/15
     */
    @Override
    public List<Map<String, String>> searchGraduationDoctorList(Map<String, Object> paramMap) {
        return graduationDoctorStatisticsMapper.searchGraduationDoctorList(paramMap);
    }
}
