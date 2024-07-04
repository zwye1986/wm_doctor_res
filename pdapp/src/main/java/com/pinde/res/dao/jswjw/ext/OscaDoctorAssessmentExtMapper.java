package com.pinde.res.dao.jswjw.ext;

import com.pinde.sci.model.mo.OscaDoctorAssessment;
import com.pinde.sci.model.mo.OscaSkillsAssessment;
import com.pinde.sci.model.mo.PubFile;
import com.pinde.sci.model.mo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OscaDoctorAssessmentExtMapper {

	List<OscaSkillsAssessment> getAuditOscaInfo( @Param("userFlow") String userFlow);

	List<OscaDoctorAssessment> getAuditOscaDocInfo( @Param("userFlow") String userFlow);
	//查询某场考核学员信息列表
	List<Map<String,Object>> searchDoctorList(@Param("clinicalFlow")String clinicalFlow);
	//根据条件查询学员成绩列表
	List<Map<String,Object>> searchDoctorScoreList(Map<String, Object> paramMap);
	//按次序查询某学员某次考试所有站点得分
	List<Map<String,Object>> queryDocScoresByOrder(@Param("doctorFlow")String doctorFlow,@Param("clinicalFlow")String clinicalFlow);
	//查询某考点，某年份，所有专业所有学生数量
	int getStudentNumber(Map<String,Object> paramMap);

	List<PubFile> findStationFiles(@Param("stationFlow") String stationFlow, @Param("orgFlow") String orgFlow);

	void delStationFile(@Param("stationFlow") String stationFlow, @Param("fileFlows") List<String> fileFlows, @Param("orgFlow") String orgFlow);
}
