package com.pinde.sci.biz.gyxjgl;

import com.pinde.sci.model.mo.EduScheduleClass;
import com.pinde.sci.model.mo.EduScheduleStudent;
import com.pinde.sci.model.mo.EduScheduleTeacher;
import com.pinde.sci.model.gyxjgl.XjEduScheduleClassExt;

import java.util.List;
import java.util.Map;

public interface IGyXjEduScheduleBiz {
    /**
     * 根据主键查询
     * @param recordFlow
     * @return
     */
   EduScheduleClass readByRecordFlow(String recordFlow);
    /**
     * 查询某一堂课的上课老师
     */
    List<EduScheduleTeacher> readByClassFlow(String scheduleClassFlow);
    /**
     * 查某堂课的所有学生
     */
    List<EduScheduleStudent> readStudentsByClassFlow(String scheduleClassFlow);

    //学生只能选本专业且已选过课程下的课
    List<XjEduScheduleClassExt> seachStudentClassByMap(Map<String ,Object> param);

    //学生课表选课status选中取消此课
    int submitScheduleCourse(String recordFlow,String userFlow,String status);

    //根据某年份学期的学生查询已选的课程
    List<EduScheduleStudent> getChosedClass(Map<String,Object> map);

    //根据年份学期班级培养层次维度查询此课堂(程)已选人数
    List<EduScheduleStudent> getScheduleStuClass(String year,String termSeason,String classId,String trainTypeId,String recordFlow);
    //校验学员在该堂课是否已经选择课程
    boolean isChosedCourse(String recordFlow, String userFlow);

    int saveScheduleClass(EduScheduleClass schedule,String teacherNameList,String oldTeacherNameList);
    //批量新增
    Object saveScheduleClassPlus(List<EduScheduleClass> scheduleList, String teacherNameList) throws Exception;

    int saveOptionClass(EduScheduleClass schedule,String teacherNameList);

    List<EduScheduleTeacher> queryClassTeachers(String scheduleClassFlow);

    int delClass(String courseTypeId,String recordFlow,String courseFlow);
}
