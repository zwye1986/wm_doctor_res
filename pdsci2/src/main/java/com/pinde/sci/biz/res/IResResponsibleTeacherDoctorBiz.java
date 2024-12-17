package com.pinde.sci.biz.res;

import com.pinde.core.model.ResResponsibleteacherDoctor;

import java.util.List;

public interface IResResponsibleTeacherDoctorBiz {

    //根据条件查询责任导师
    List<ResResponsibleteacherDoctor> search(ResResponsibleteacherDoctor resResponsibleteacherDoctor);
    //新增、修改
    int edit(ResResponsibleteacherDoctor resResponsibleteacherDoctor);
}
