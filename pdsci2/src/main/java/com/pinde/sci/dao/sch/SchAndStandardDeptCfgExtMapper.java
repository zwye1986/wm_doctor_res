package com.pinde.sci.dao.sch;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SchAndStandardDeptCfgExtMapper {

    List<Map<String,String>> searchByRequired( @Param("rotationFlow") String rotationFlow, @Param("isRequired") String isRequired);

    List<Map<String,String>> searchByLastRotation( @Param("orgFlow") String orgFlow);

    List<Map<String,String>> searchByLastRotationBySpe( @Param("orgFlow") String orgFlow, @Param("speId") String speId, @Param("isRequired") String isRequired);

} 
