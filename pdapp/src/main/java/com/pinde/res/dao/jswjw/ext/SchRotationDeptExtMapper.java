package com.pinde.res.dao.jswjw.ext;

import com.pinde.core.model.SchRotationDept;
import com.pinde.core.model.SchRotationDeptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SchRotationDeptExtMapper {

	List<Map<String, Object>> searchSchRotationDept(Map<String,Object> paramMap);
	Integer searchSchRotationDeptCount(Map<String,Object> paramMap);

	/**
	 * @Department：研发部
	 * @Description 查询学员减免信息数量
	 * @Author fengxf
	 * @Date 2021/4/7
	 */
	int countDoctorSchRotationDept(@Param("doctorFlow") String doctorFlow, @Param("rotationFlow") String rotationFlow);

	List<SchRotationDept> selectByExampleTwo(SchRotationDeptExample example);

    String searchRotationDeptName(@Param("deptFlow") String deptFlow);

	String searchByDoctorAndRotationFlow(@Param("doctorFlow")String doctorFlow,@Param("rotationFlow")String rotationFlow);
}
