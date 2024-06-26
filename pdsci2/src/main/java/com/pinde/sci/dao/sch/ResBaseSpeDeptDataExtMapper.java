package com.pinde.sci.dao.sch;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResBaseSpeDeptDataExtMapper {

    List<Map<String,String>> searchResBaseSpeDeptInfoData(Map<String, String> paramMap);

    List<Map<String,String>> countByOrgListAndSpe(@Param("orgFlowList") List<String> orgFlowList);

    List<Map<String,Object>> countDoctorNum(Map<String, String> paramMap);

} 
