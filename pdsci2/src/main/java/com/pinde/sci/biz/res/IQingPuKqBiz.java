package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.QingpuDoctorKq;
import com.pinde.sci.model.mo.SysUser;

import java.util.List;
import java.util.Map;

public interface IQingPuKqBiz {
    //根据主键读取一条考勤记录
    QingpuDoctorKq readQingpuDoctorKq(String recordFlow);
    //编辑考勤记录
    int editQingpuDoctorKq(QingpuDoctorKq kq);
    //根据条件查询考勤记录
    List<QingpuDoctorKq> searchQingpuDoctorKq(QingpuDoctorKq kq);
    //获取当前带教老师所有轮转中学员
    List<Map<String,Object>> teacherGetStudents(Map<String,Object> paramMap);
    //获取当前教秘所有轮转中学员
    List<Map<String,Object>> headGetStudents(Map<String,Object> paramMap);
    //学员考勤统计表
    List<Map<String,Object>> getKqStatistics(Map<String,Object> paramMap);
    //签到列表
    List<Map<String,String>> getSigninList(Map<String, Object> param);
}
