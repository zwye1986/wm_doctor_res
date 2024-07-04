package com.pinde.res.dao.jswjw.ext;

import com.pinde.sci.model.mo.ResDoctor;

import java.util.List;
import java.util.Map;

public interface DoctorExtMapper {
    ResDoctor selectByPrimaryKey(String doctorFlow);
    List<Map<String,String>> searchDoctorSpe();
}