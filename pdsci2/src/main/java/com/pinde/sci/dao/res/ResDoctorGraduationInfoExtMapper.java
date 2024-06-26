package com.pinde.sci.dao.res;


import com.pinde.sci.model.mo.GraduationExamResults;
import com.pinde.sci.model.mo.ResDoctorGraduationInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2016-10-18.
 */
public interface ResDoctorGraduationInfoExtMapper {

    ResDoctorGraduationInfo getCreateCertificate(@Param("old") ResDoctorGraduationInfo old);

    List<Map<String,Object>> getExamResults(Map<String, Object> param);

    List<Map<String,Object>> findExamBydoctorFlow(@Param(value = "doctorFlow")String doctorFlow,@Param(value = "orgFlow")String orgFlow);
}
