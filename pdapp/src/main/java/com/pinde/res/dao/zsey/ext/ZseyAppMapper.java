package com.pinde.res.dao.zsey.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ZseyAppMapper {

	List<Map<String,Object>> getDeptStudents(@Param("userName") String userName, @Param("userFlow") String userFlow, @Param("role") String role);

	List<Map<String,Object>> getCycleStudents(@Param("userName") String userName, @Param("userFlow") String userFlow, @Param("role") String role, @Param("isPermitOpen") String isPermitOpen);

	List<Map<String,Object>> getDeptTeachers(@Param("userName") String userName, @Param("userFlow") String userFlow, @Param("role") String role, @Param("teacherRoleFlow") String teacherRoleFlow);

	List<Map<String,Object>> getDeptHeads(@Param("userName") String userName, @Param("userFlow") String userFlow, @Param("role") String role, @Param("headRoleFlow") String headRoleFlow);

	List<Map<String,Object>> absenceUserList(@Param("userName") String userName, @Param("userFlow") String userFlow, @Param("role") String head);

	int checkIsDeptStudent(@Param("resultFlow") String resultFlow, @Param("userFlow") String userFlow, @Param("deptFlow") String deptFlow);

	List<Map<String,Object>> getSpeUsers(@Param("userName") String userName, @Param("userFlow") String userFlow, @Param("role") String role, @Param("baseRoleFlow") String baseRoleFlow);
}
