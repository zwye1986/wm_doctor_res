package com.pinde.sci.dao.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PhyAssExtMapper {
	List<Map<String,Object>> searchPlanInfoList(Map<String, Object> paramMap);

	List<Map<String,Object>> searchOrgPlanInfoList(Map<String, Object> paramMap);

	List<Map<String,Object>> searchAllIntroduce();

	List<Map<String,Object>> searchAllByTime(@Param("nowData") String nowData);

	List<Map<String,Object>> seachUser(@Param("orgFlow") String orgFlow, @Param("planFlow") String planFlow);

	void delBaseListEntryUser(@Param("planFlow") String planFlow, @Param("speId") String speId, @Param("orgFlow") String orgFlow);

	List<Map<String,Object>> searchSpeNum(@Param("planFlow") String planFlow, @Param("orgFlow") String orgFlow);

	int appearUser(@Param("planFlow") String planFlow, @Param("orgFlow") String orgFlow);

	List<Map<String,Object>> searchPlanUserList(@Param("orgFlow") String orgFlow, @Param("planFlow") String planFlow, @Param("type") String type);

	List<Map<String,Object>> plannedMaintainList(Map<String, Object> paramMap);

	void delUserDept(@Param("userFlow") String userFlow);

	Map<String,Object> searchSysUserRoleName(String userFlow);

	void delplanDoctor(Map<String, Object> paramMap);

	List<Map<String,Object>> searchPlanScoreList(Map<String, Object> paramMap);

	List<Map<String,Object>> planApperList(Map<String, Object> paramMap);

	List<Map<String,Object>> phyAssCertificateList(Map<String, Object> paramMap);

	List<Map<String,Object>> planUserOrgList(Map<String, Object> paramMap);

	String gainPlanCertificateNo(@Param("planFlow") String planFlow);

	List<Map<String,Object>> phyAssDoctorList(Map<String, Object> paramMap);


}

