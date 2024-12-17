package com.pinde.sci.dao.sch;


import com.pinde.core.model.SchArrangeResult;
import com.pinde.core.model.SchOrgArrangeResult;
import com.pinde.core.model.SchRotationDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SchOrgArrangeResultExtMapper {

	void delSchInfoByCycle(@Param("doctorFlow") String doctorFlow, @Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber, @Param("rotationFlow") String rotationFlow, @Param("cycleType") String cycleType);

	void updateDoctorSchFlag(@Param("doctorFlow") String doctorFlow, @Param("cycleYear") String cycleYear);

	int findDeptMonthUseNum(@Param("deptFlow") String deptFlow, @Param("startDate") String startDate, @Param("endDate") String endDate);

	Double getRotationCycleMonthNum(@Param("rotationFlow") String rotationFlow, @Param("selectYear") String selectYear, @Param("cycleYear") String cycleYear, @Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber);

	List<SchOrgArrangeResult> readOrgArrangeResult(Map<String, String> param);

	List<SchOrgArrangeResult> readArrangeResultsByMap(Map<String, String> param);

	int findCycleResultCount(@Param("doctorFlow") String doctorFlow, @Param("cycleYear") String cycleYear);

	int findSchResultCount(@Param("doctorFlow") String doctorFlow, @Param("cycleYear") String cycleYear);

	void delCycleResultByYear(@Param("doctorFlow") String doctorFlow, @Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber, @Param("rotationFlow") String rotationFlow, @Param("cycleYear") String cycleYear);

	void AddCycleResultByYear(@Param("doctorFlow") String doctorFlow, @Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber, @Param("rotationFlow") String rotationFlow, @Param("cycleYear") String cycleYear);

	List<SchArrangeResult> getCycleResults(@Param("doctorFlow") String doctorFlow, @Param("sessionNumber") String sessionNumber, @Param("rotationFlow") String rotationFlow, @Param("orgFlow") String orgFlow);

	void delCycleProcessByResult(@Param("doctorFlow") String userFlow, @Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber, @Param("rotationFlow") String rotationFlow, @Param("cycleYear") String cycleYear);

	void updateDoctorIsSch(@Param("userFlow") String userFlow);

	List<Map<String,String>> readOrgArrangeStartDate(@Param("doctorFlow") String doctorFlow, @Param("sessionNumber") String sessionNumber, @Param("rotationFlow") String rotationFlow, @Param("orgFlow") String orgFlow);

	List<SchArrangeResult> readArrangeResultsByTimeMap(Map<String, String> d);

	int findRecCount(@Param("resultFlow") String resultFlow);

	List<SchRotationDept> readRotationDeptBySchDept(@Param("schDeptFlow") String schDeptFlow, @Param("rotationFlow") String rotationFlow, @Param("secondRotationFlow") String secondRotationFlow);
}