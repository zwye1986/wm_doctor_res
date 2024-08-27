package com.pinde.sci.dao.sch;


import com.pinde.sci.model.mo.SchDept;
import com.pinde.sci.model.mo.SchExternalDept;
import com.pinde.sci.model.res.ResDoctorExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SchExternalDeptExtMapper {
	int checkTime(@Param("schExternalDept") SchExternalDept schExternalDept);

	int isHaveSelect(Map<String, Object> time);

	List<SchDept> getNotSelfSchDeptByDeptIdAndExternal(Map<String, String> params);

	List<ResDoctorExt> getStudents(Map<String, String> params);

	List<Map<String,Object>> getExtStudents(Map<String, String> params);
}