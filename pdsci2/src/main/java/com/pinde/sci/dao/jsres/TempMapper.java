package com.pinde.sci.dao.jsres;

import com.pinde.core.model.ResSchProcessExpress;
import com.pinde.core.model.SysUser;
import com.pinde.sci.model.mo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TempMapper {

	int updateRecruitAsseInfo(@Param("applyYear") String applyYear);

	int deleteUriAuditInfo();

	void insertAuditInfo();

	void callDeptDetail();

	void deleteDeptTemp();

	void updateDeptTemp();

	void deleteDeptAvgTemp();

	void updateDeptAvgTemp();

	int updateRecruitAvgTemp(@Param("applyYear") String applyYear);

	void updateRecruitAsseInfoByApplyYear(@Param("applyFlow") String applyFlow, @Param("doctorFlow") String doctorFlow);

	void deleteDeptDetailByApplyYear(@Param("applyYear") String applyYear, @Param("doctorFlow") String doctorFlow);

	void insetDeptDetailByApplyYear(@Param("applyYear") String applyYear, @Param("doctorFlow") String doctorFlow, @Param("recruitFlow") String recruitFlow);

	void deleteDeptTempByRecruitFlow(@Param("recruitFlow") String recruitFlow);

	void updateDeptTempByRecruitFlow(@Param("recruitFlow") String recruitFlow, @Param("applyYear") String applyYear);

	void deleteDeptAvgTempByRecruitFlow(@Param("recruitFlow") String recruitFlow);

	void updateDeptAvgTempByRecruitFlow(@Param("recruitFlow") String recruitFlow, @Param("applyYear") String applyYear);

	void updateRecruitAvgTempByRecruitFlow(@Param("recruitFlow") String recruitFlow, @Param("applyFlow") String applyFlow);

	void deleteDeptDetail(@Param("applyYear") String applyYear);

	void insertDeptDetail(@Param("applyYear") String applyYear);

	void saveRegisteManua(@Param("registeManua") String registeManua, @Param("recruitFlow") String recruitFlow, @Param("applyYear") String applyYear);

	List<ResDoctorSchProcess> queryProcess(@Param("userCode") String userCode);

	int delProcessType(@Param("processFlow") String processFlow, @Param("recTypeId") String recTypeId);

	int backProcessType(@Param("processFlow") String processFlow, @Param("recTypeId") String recTypeId);

	int delProcess(@Param("processFlow") String processFlow);

	int delExpressNotType(@Param("processFlow") String processFlow);

	int delRecNotType(@Param("processFlow") String processFlow);

	int delResult(@Param("processFlow") String processFlow);

	List<Map<String,String>> queryApplyList(@Param("applyYear") String applyYear);

	List<ResDoctorSchProcess> queryDelProcess(@Param("userCode") String userCode);

	int backProcess(@Param("processFlow") String processFlow);

	int backExpressNotType(@Param("processFlow") String processFlow);

	int backRecNotType(@Param("processFlow") String processFlow);

	int backResult(@Param("processFlow") String processFlow);

	void updateDeptDetailPerByApplyYear(@Param("applyYear") String applyYear, @Param("doctorFlow") String doctorFlow, @Param("recruitFlow") String recruitFlow);

	void updateDeptAvgPerTempByRecruitFlow(@Param("recruitFlow") String recruitFlow, @Param("applyYear") String applyYear);

	void updateRecruitAvgPerTempByRecruitFlow(@Param("recruitFlow") String recruitFlow, @Param("applyFlow") String applyFlow);

	void updateResultAfterPic(@Param("haveAfterPic") String haveAfterPic, @Param("processFlow") String processFlow, @Param("operUserFlow") String operUserFlow);

	List<SysUser> examTeaRole();

	int backAttend(@Param("userCode") String userCode, @Param("startDate") String startDate, @Param("endDate") String endDate);

	List<OscaTeaScanDoc> getOscaNotSubmitInfo(@Param("orgFlow") String orgFlow);

	List<OscaSkillDocScore> getTeaDocScores(@Param("userFlow") String userFlow, @Param("doctorFlow") String doctorFlow);

	List<OscaSkillRoomDoc> getOscaSkillRoomDocByDoc(Map<String, String> param);

	Map<String,Object> getDocStationAllScore(Map<String, String> param);

	List<OscaSubjectStation> getTeaDocStation(Map<String, String> paramMap);

	List<OscaSubjectStation> getTeaSubDocStation(Map<String, String> paramMap);

	List<OscaSkillDocScore> getDocScoreByParam(Map<String, String> param);

	List<OscaTeaScanDoc> getOscaTeaScanDoc(Map<String, String> param);

	void updateResultAfterPicNotHaveRec();

	void deleteMonthStatistics(@Param("month") String month);

	void deleteMonthAppStatistics(@Param("month") String month);

	void updateMonthStatistics(@Param("month") String month, @Param("type") String type);

	void updateMonthAppStatistics(@Param("month") String month, @Param("type") String type);

	void addTempNo(@Param("idNo") String idNo);

	List<ResSchProcessExpress> updateAfterEvalutaion();

	void delProcessTypeByFlow(@Param("recFlow") String recFlow);

	int backProcessTypeByFlow(@Param("recFlow") String recFlow);

	List<ResSchProcessExpress> searchRecByProcessWithBLOBsByDel(@Param("recTypeIds") List<String> recTypeIds, @Param("processFlow") String processFlow);

	List<Map<String,String>> afterAttendBackList(@Param("userCode") String userCode, @Param("startDate") String startDate, @Param("endDate") String endDate);

	int backAttendByFlow(@Param("attendanceFlow") String attendanceFlow);

	void updateRecruitAsseInfoByApplyYear2(Map<String, String> practicingMap);

	void updateStudentCourse();

	void updateStudentCourseSchedule();

}
