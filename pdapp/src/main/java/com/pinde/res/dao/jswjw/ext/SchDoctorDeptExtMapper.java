package com.pinde.res.dao.jswjw.ext;

import com.pinde.core.model.SchDoctorDept;
import com.pinde.core.model.SchDoctorDeptExample;

import java.util.List;

public interface SchDoctorDeptExtMapper {
    List<SchDoctorDept> selectByExampleTwo(SchDoctorDeptExample example);

}