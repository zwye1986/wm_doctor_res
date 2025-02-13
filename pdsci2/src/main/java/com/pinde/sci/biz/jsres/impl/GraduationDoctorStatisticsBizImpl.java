package com.pinde.sci.biz.jsres.impl;

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
     * @Description 按专业统计应结业学员信息
     * @Author fengxf
     * @Date 2025/2/13
     */
    @Override
    public List<GraduationDoctorStatisticsInfo> searchGraduationDoctorStatisticsBySpe(Map<String, Object> paramMap) {
        return graduationDoctorStatisticsMapper.searchGraduationDoctorStatisticsBySpe(paramMap);
    }
}
