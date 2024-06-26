package com.pinde.sci.biz.recruit;

import com.pinde.sci.model.mo.RecruitExamRoom;
import com.pinde.sci.model.mo.RecruitExamTeacher;

import java.util.List;

public interface IRecruitExamTeacherBiz {
    String IsExamRoomUsed(String roomFlow,String orgFlow);

    List<RecruitExamTeacher> searchAllExamRoom(String orgFlow);

    Integer addExamTeacher(RecruitExamTeacher examTeacher);

    RecruitExamTeacher searchExamTeacherByFlow(String teacherFlow);

    Integer updateExamTeacher(RecruitExamTeacher editInfo);

    Integer deleteExamTeacherByFlow(String teacherFlow);

    List<RecruitExamTeacher> searchExamTeacherList(String orgFlow,String teaName,String roomFlow,String teaRole);
}
