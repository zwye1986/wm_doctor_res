package com.pinde.sci.dao.sch;


import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchRotationCfgExtMapper {

	int checkRotationCfg(@Param("orgFlow") String orgFlow, @Param("selectYear") String selectYear, @Param("sessionNumber") String sessionNumber, @Param("rotationFlow") String rotationFlow);

	int checkSelNum(@Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber, @Param("rotationFlow") String rotationFlow);

	int checkSchNum(@Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber, @Param("rotationFlow") String rotationFlow);

	void delSchInfo(@Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber, @Param("rotationFlow") String rotationFlow);

	void delSelInfo(@Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber, @Param("rotationFlow") String rotationFlow, @Param("selectYear") String selectYear);

	void updateSelN(@Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber, @Param("rotationFlow") String rotationFlow, @Param("selectYear") String selectYear);

	void updateSchN(@Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber, @Param("rotationFlow") String rotationFlow);

	void delOrgSchDepts(@Param("orgDeptFlows") List<String> orgDeptFlows, @Param("orgFlow") String orgFlow, @Param("selectYear") String selectYear, @Param("sessionNumber") String sessionNumber, @Param("rotationFlow") String rotationFlow);

	void delDocSelInfo(@Param("doctorFlow") String doctorFlow, @Param("rotationFlow") String rotationFlow, @Param("sessionNumber") String sessionNumber, @Param("orgFlow") String orgFlow);
}