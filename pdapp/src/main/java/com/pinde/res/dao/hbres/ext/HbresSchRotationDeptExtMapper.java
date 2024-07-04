package com.pinde.res.dao.hbres.ext;

import com.pinde.sci.model.mo.SysUser;

import java.util.List;
import java.util.Map;

public interface HbresSchRotationDeptExtMapper {
	List<Map<String,Object>> getDoctorRotationDept(Map<String,Object> paramMap);
}
