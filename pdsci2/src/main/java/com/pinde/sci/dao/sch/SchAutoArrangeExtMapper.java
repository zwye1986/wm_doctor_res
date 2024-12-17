package com.pinde.sci.dao.sch;


import com.pinde.core.model.ResDoctor;
import com.pinde.sci.model.mo.SchRotation;
import com.pinde.sci.model.mo.SchRotationDept;
import com.pinde.core.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SchAutoArrangeExtMapper {


	int checkRotationLocal(@Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber);

	int checkDoctorCount(@Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber, @Param("schFlag") String schFlag);

	int checkOrgDoctorCount(@Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber, @Param("schFlag") String schFlag);

	int checkArrangeCount(@Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber);

	int checkDoctorCycleCount(@Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber, @Param("schFlag") String schFlag);

	int checkDoctorSecondCount(@Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber, @Param("schFlag") String schFlag);

	List<SchRotation> getSchRotations(@Param("orgFlow") String orgFlow);

	List<ResDoctor> getDoctorList(@Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber, @Param("schFlag") String schFlag);

	List<SysUser> getUserList(@Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber);

	List<SchRotationDept> getSchRotationDepts(@Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber);

	void delProcess(@Param("doctorFlow") String doctorFlow, @Param("rotationFlow") String rotationFlow);

	void delResult(@Param("doctorFlow") String doctorFlow, @Param("rotationFlow") String rotationFlow);

	List<Map<String,String>> getResultByBeginStartDate(@Param("doctorFlow") String doctorFlow, @Param("startDate") String startDate);

	int getLastDoctorCount(@Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber);

}