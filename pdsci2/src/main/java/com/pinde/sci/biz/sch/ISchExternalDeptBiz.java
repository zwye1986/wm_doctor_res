package com.pinde.sci.biz.sch;

import com.pinde.core.model.SchDept;
import com.pinde.core.model.SchExternalDept;
import com.pinde.core.model.SchRotation;
import com.pinde.core.model.SchRotationDept;
import com.pinde.sci.model.res.ResDoctorExt;

import java.util.List;
import java.util.Map;


public interface ISchExternalDeptBiz {

	List<SchExternalDept> getSchDeptExtDepts(String orgFlow, String schDeptFlow);

	int checkTime(SchExternalDept schExternalDept);

	int save(SchExternalDept schExternalDept);

	int saveTimes(List<Map<String, String>> moreTimes, SchExternalDept schExternalDept);

	SchExternalDept readByFlow(String flow);


	int isHaveSelect(Map<String, Object> time);

	int delExternalDept(String[] recordFlows);

	List<SchRotation> changRotation(String trainingSpeId);

	List<SchRotationDept> getSchRotationDepts(String rotationFlow);

	List<SchDept> getNotSelfSchDeptByDeptIdAndExternal(Map<String, String> params);

	List<ResDoctorExt> getStudents(Map<String, String> params);

	List<SchExternalDept> getStandardSchDeptExtDepts(String standardDeptId, String schDeptFlow);

	List<Map<String,Object>> getExtStudents(Map<String, String> params);

	String delDoctorProcess(String resultFlow, String processFlow);

	String addDoctorProcess(String recordFlow, String schDeptFlow, String[] recordFlows, String[] doctorFlows) throws Exception;

	List<SchRotationDept> getSchRotationDepts(String rotationFlow, String orgFlow, String sessionNumber);
}
