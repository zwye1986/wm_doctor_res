package com.pinde.sci.biz.lcjn;

import com.pinde.core.model.LcjnCourseEvaluate;
import com.pinde.core.model.LcjnCourseEvaluateDetail;
import com.pinde.core.model.LcjnCourseInfo;
import com.pinde.core.model.LcjnCourseTea;
import com.pinde.core.model.LcjnTeaEvaluate;
import com.pinde.core.model.LcjnTeaEvaluateDetail;

import java.util.List;

public interface ILcjnEvaluateBiz {
    //根据主键查询学员评价课程记录
    LcjnCourseEvaluate read(String evaluateFlow);
    //根据课程FLOW查询学员课程评价记录
    List<LcjnCourseEvaluate> searchByCourseFlow(String courseFlow);
    //根据evaluateFlow查询某一课程的所有评价指标详情记录
    List<LcjnCourseEvaluateDetail> searchDetailByEvaluateFlow(String evaluateFlow);
    //根据课程FLOW和教师FLOW查询学员教师评价记录
    List<LcjnTeaEvaluate> searchByTeacher(String courseFlow,String teacherFlow);
    //根据TeacherEvaluateFlow查询某一课程某一老师的所有评价指标详情记录
    List<LcjnTeaEvaluateDetail> searchTeaDetailByEvaluateFlow(String teaEvaluateFlow);
    //根据courseFlow查询该门课程所有老师
    List<LcjnCourseTea> searchTeaByCourseFlow(String courseFlow);
    //根据主键查询一门课程
    LcjnCourseInfo readCourseInfo(String courseFlow);
}
