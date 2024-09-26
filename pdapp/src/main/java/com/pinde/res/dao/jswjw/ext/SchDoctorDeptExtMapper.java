package com.pinde.res.dao.jswjw.ext;

import com.pinde.sci.model.mo.SchDoctorDept;
import com.pinde.sci.model.mo.SchDoctorDeptExample;

import java.util.List;

public interface SchDoctorDeptExtMapper {
    List<SchDoctorDept> selectByExampleTwo(SchDoctorDeptExample example);

}