package com.pinde.sci.dao.hbres;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HbresTempMapper {

	int updateRecruitAvgTemp(@Param("applyYear") String applyYear);

	void deleteDeptDetailByApplyYear(@Param("applyYear") String applyYear, @Param("doctorFlow") String doctorFlow);

	void insetDeptDetailByApplyYear(@Param("applyYear") String applyYear, @Param("doctorFlow") String doctorFlow, @Param("recruitFlow") String recruitFlow, @Param("resultFlows") List<String> resultFlows);

	void deleteDeptAvgTempByRecruitFlow(@Param("recruitFlow") String recruitFlow);

	void updateDeptAvgTempByRecruitFlow(@Param("recruitFlow") String recruitFlow, @Param("applyYear") String applyYear);

	void updateRecruitAvgTempByRecruitFlow(@Param("recruitFlow") String recruitFlow, @Param("applyFlow") String applyFlow);

	void saveRegisteManua(@Param("registeManua") String registeManua, @Param("recruitFlow") String recruitFlow, @Param("applyYear") String applyYear);

	void updateDeptDetailPerByApplyYear(@Param("applyYear") String applyYear, @Param("doctorFlow") String doctorFlow, @Param("recruitFlow") String recruitFlow);

	void updateDeptAvgPerTempByRecruitFlow(@Param("recruitFlow") String recruitFlow, @Param("applyYear") String applyYear);

	void updateRecruitAvgPerTempByRecruitFlow(@Param("recruitFlow") String recruitFlow, @Param("applyFlow") String applyFlow);

	void updateRecruitAsseInfoByApplyYear2(Map<String, String> practicingMap);


}
