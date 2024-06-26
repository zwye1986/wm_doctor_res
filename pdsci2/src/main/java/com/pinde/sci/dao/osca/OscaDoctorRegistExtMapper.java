package com.pinde.sci.dao.osca;

import com.pinde.sci.model.osca.OscaOrgMenuExt;
import com.pinde.sci.model.osca.OscaOrgSpeExt;

import java.util.List;
import java.util.Map;

public interface OscaDoctorRegistExtMapper {
    //根据查询条件查询学员注册信息
    List<Map<String,Object>> search(Map<String,Object> paramMap);
    //根据条件查询学员信息
    List<Map<String,Object>> searchStudents(Map<String,Object> paramMap);
}
