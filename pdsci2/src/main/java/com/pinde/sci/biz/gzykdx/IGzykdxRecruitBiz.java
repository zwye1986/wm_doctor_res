package com.pinde.sci.biz.gzykdx;

import com.pinde.sci.model.mo.DoctorTeacherRecruit;
import com.pinde.sci.model.mo.DoctorTeacherRecruitBatch;
import com.pinde.sci.model.mo.TeacherTargetApply;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IGzykdxRecruitBiz {
    //根据条件查询复试考生信息
    List<Map<String,Object>> searchReexamineStudent(Map<String,String> map);
    //复试学员信息导入操作
    int importReexamStudentExcel(MultipartFile file);
    //学校录取缺额查询
    List<Map<String,Object>> vacanciesQuery(Map<String,String> map);
    //导师录取查询
    List<Map<String,Object>> teacherRecruitInfo(Map<String,String> map);
    //查询二级机构指标
    List<Map<String,Object>> searchSecondaryRecriutInfo(Map<String,String> map);
    //导师申请指标查询
    List<TeacherTargetApply> searchTeacherTargetApply(TeacherTargetApply teacherTargetApply);
    //学生申请记录查询
    List<DoctorTeacherRecruit> searchDoctorTeacherRecruit(DoctorTeacherRecruit doctorTeacherRecruit);
    //二级机构根据条件查询复试考生信息
    List<Map<String,Object>> searchSecondaryReexamineStudent(Map<String,String> map);
    //doctor_teacher_recruit表修改
    int editDocTeaRec(DoctorTeacherRecruit doctorTeacherRecruit);
    //teacher_target_apply表修改
    int editTTA(TeacherTargetApply teacherTargetApply);
    //根据主键读doctor_teacher_recruit
    DoctorTeacherRecruit readDocTeaRec(String recordFlow);
    //查出所有指标没满的导师申请表
    List<TeacherTargetApply> changeTeacherList(Map<String,String> map);
    //查询审核批次轨迹
    List<DoctorTeacherRecruitBatch> searchBatches(DoctorTeacherRecruitBatch doctorTeacherRecruitBatch);
    //查询老师招生剩余人数
    int getLeftNum(Map<String,String> map);
}
