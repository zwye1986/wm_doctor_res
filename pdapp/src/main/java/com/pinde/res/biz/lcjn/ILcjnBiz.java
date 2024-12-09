package com.pinde.res.biz.lcjn;

import com.pinde.core.model.*;

import java.util.List;
import java.util.Map;

public interface ILcjnBiz {


	SysUser readUserByCode(String userCode);

	int register(SysUser user);

	List<SysUserRole> getSysUserRole(String userFlow);

	String getCfgCode(String res_doctor_role_flow);

	int addUserRole(SysUserRole userRole);

	SysUser readUserByFlow(String userFlow);

	List<SysDict> getDictListByDictId(String doctorTrainingSpe);

	List<SysDict> getDictListByDictId(String doctorTrainingSpe,String orgFlow);

	SysUser readUserByIdNo(String idNo);

	SysUser readUserByPhone(String userPhone);

	SysUser readUserByEmail(String userEmail);

	int save(SysUser user);

	List<Map<String,Object>> getLcjnCourseInfo(Map<String,String> params);

	LcjnDoctorCourse getDoctorCourseByFlow(String recordFlow);

	LcjnDoctorCourse getDoctorCourseByCourseAndDocFlow(String userFlow, String courseFlow);

	LcjnCourseInfo getLcjnCourseInfoByFlow(String courseFlow);

	List<LcjnCourseTime> getCourseTimeByFlow(String courseFlow);

	List<LcjnCourseTea> getCourseTeaByFlow(String courseFlow);

	List<LcjnCourseSkill> getCourseSkillByFlow(String courseFlow);

	int getCourseLastNumByFlow(String courseFlow);

	int checkTrainTime(String userFlow, String courseFlow);

	int saveDoctorCourse(LcjnDoctorCourse doctorCourse);

	List<Map<String,Object>> getLcjnDocCourseInfo(Map<String, String> params);

	String getCourseMinTrainStartDate(String courseFlow);

	List<LcjnDoctorSigin> getSiginListByFlow(String userFlow, String courseFlow);

	boolean checkIsEval(String userFlow, String courseFlow);

	LcjnCourseEvaluate getCourseEvaluate(String userFlow, String courseFlow);

	List<LcjnCourseEvaluateDetail> getCourseEvaluateDetail(String evaluateFlow);

	List<LcjnTeaEvaluate> getCourseTeaEvalByFlow(String userFlow, String courseFlow);

	List<LcjnTeaEvaluateDetail> getCourseTeaEvalDeatil(String teaEvaluateFlow);

	int addCourseEval(LcjnCourseEvaluate evaluate);


	LcjnCourseEvaluateDetail getCourseEvaluateDetailByFlow(String evaluateFlow, String dictId);

	int saveCourseEvalDetail(LcjnCourseEvaluateDetail d, String userFlow);

	LcjnTeaEvaluate getTeaEvalByFlow(String userFlow, String teaFlow, String courseFlow);

	int addTeacherEvl(LcjnTeaEvaluate teaEvaluate);

	LcjnTeaEvaluateDetail getTeaEvaluateDetailByFlow(String teaEvaluateFlow, String dictId);

	int saveTeaEvalDetail(LcjnTeaEvaluateDetail d, String userFlow);
}
  