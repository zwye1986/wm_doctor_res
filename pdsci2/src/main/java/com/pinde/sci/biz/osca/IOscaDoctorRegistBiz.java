package com.pinde.sci.biz.osca;

import com.pinde.sci.model.mo.OscaDoctorRegist;

import java.util.List;
import java.util.Map;

public interface IOscaDoctorRegistBiz {
    //通过主键读取单个记录
    OscaDoctorRegist read(String recordFlow);
    //根据条件筛选记录
    List<OscaDoctorRegist> searchRegist(OscaDoctorRegist oscaDoctorRegist);
    //根据条件查询注册学员列表
    List<Map<String,Object>> search(Map<String,Object> paramMap);
    //根据条件查询学员列表
    List<Map<String,Object>> searchStudents(Map<String,Object> paramMap);
    //编辑单条记录
    int edit(OscaDoctorRegist oscaDoctorRegist);
    //审核学员注册记录
    void editAudit(OscaDoctorRegist oscaDoctorRegist);
}
