package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.ResTrainingSpeDept;

import java.util.List;

public interface IResTrainingSpeDeptBiz {

    //根据条件查询科室
    List<ResTrainingSpeDept> search(ResTrainingSpeDept trainingSpeDept);
    //查询所有非当前专业基地的科室
    List<ResTrainingSpeDept> notCurrentSpe(ResTrainingSpeDept trainingSpeDept);
    //新增、修改
    int edit(ResTrainingSpeDept trainingSpeDept);

}
