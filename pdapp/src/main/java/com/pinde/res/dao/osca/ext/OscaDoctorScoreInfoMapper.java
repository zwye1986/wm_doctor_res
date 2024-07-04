package com.pinde.res.dao.osca.ext;


import java.util.List;
import java.util.Map;

public interface OscaDoctorScoreInfoMapper {

    //查询学员每站成绩
    List<Map<String,Object>> selectGradeList(Map<String, Object> map);
    //查询所有学员成绩
    List<Map<String,Object>> selectAllGradeList(Map<String, Object> map);
    //查询单个学员成绩（学员WEB端）
    List<Map<String,Object>> getSingleGrade(Map<String, Object> paramMap);
}
