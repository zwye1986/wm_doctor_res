package com.pinde.sci.dao.jszy;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JszyResRecruitDoctorInfoExtMapper {
    List<Map<String,Object>> zltjSecondSpe(Map<String,Object> param);
}
