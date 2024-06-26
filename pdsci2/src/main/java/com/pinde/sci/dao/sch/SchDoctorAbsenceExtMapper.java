package com.pinde.sci.dao.sch;


import com.pinde.sci.model.mo.SchDoctorAbsence;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SchDoctorAbsenceExtMapper {

	public List<SchDoctorAbsence> searchDoctorAbsence(Map<String,Object> paramMap);
	
	/**
	 * 计算一组医师的请假总天数
	 * @param doctorFlows
	 * @return
	 */
	public List<Map<String,Object>> countAbsenceDays(@Param(value="doctorFlows")List<String> doctorFlows);

	int checkDates(@Param("userFlow") String userFlow, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("absenceFlow") String absenceFlow);
}
