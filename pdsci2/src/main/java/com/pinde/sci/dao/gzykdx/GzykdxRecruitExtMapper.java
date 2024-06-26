package com.pinde.sci.dao.gzykdx;

import com.pinde.sci.model.mo.TeacherTargetApply;

import java.util.List;
import java.util.Map;

public interface GzykdxRecruitExtMapper {
    //根据条件查询复试考生信息
    List<Map<String,Object>> searchReexamineStudent(Map<String,String> map);
    //二级机构根据条件查询复试考生信息
    List<Map<String,Object>> searchSecondaryReexamineStudent(Map<String,String> map);
    //学校录取缺额查询
    List<Map<String,Object>> vacanciesQuery(Map<String,String> map);
    //导师录取查询
    List<Map<String,Object>> teacherRecruitInfo(Map<String,String> map);
    //根据机构年份查询二级机构招录信息
    List<Map<String,Object>> searchSecondaryRecriutInfo(Map<String,String> map);
    //查出所有指标没满的导师申请表
    List<TeacherTargetApply> changeTeacherList(Map<String,String> map);
    //判断导师指标剩余数量
    Integer leftStuNum(Map<String,String> map);
}
