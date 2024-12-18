package com.pinde.sci.dao.jsres;

import com.pinde.core.model.SchExamDoctorArrangement;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JsResGraduationMapper {

	List<Map<String,Object>> userList(Map<String, Object> param);

	List<SchExamDoctorArrangement> getDoctorArrangements(Map<String, Object> param);

	List<Map<String,Object>> findExamBydoctorFlow(@Param(value = "doctorFlow")String doctorFlow);
}
