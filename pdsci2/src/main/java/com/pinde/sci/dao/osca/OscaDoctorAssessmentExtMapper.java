package com.pinde.sci.dao.osca;

import com.pinde.core.model.PubFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface OscaDoctorAssessmentExtMapper {
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

    List<PubFile> findClinicalStationFiles(@Param("stationFlow") String stationFlow, @Param("clinicalFlow") String clinicalFlow, @Param("orgFlow") String orgFlow);

    void delSkillsStationFile(@Param("clinicalFlow") String clinicalFlow, @Param("stationFlow") String stationFlow, @Param("fileFlows") List<String> fileFlows, @Param("orgFlow") String orgFlow);
}
