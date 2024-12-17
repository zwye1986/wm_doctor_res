package com.pinde.sci.dao.sch;


import com.pinde.core.model.SchRotationDeptReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SchRotationDeptReqExtMapper {
	
	List<SchRotationDeptReq> searchReqByRotationAndSchDept(@Param(value="rotationFlows") List<String> rotationFlows,
			@Param(value="schDeptFlows") List<String> schDeptFlows,
			@Param(value="recTypeId") String recTypeId,
			@Param(value="itemName") String itemName);

	List<Map<String,Object>> countReqWithSchDept(@Param(value="rotationFlows")List<String> rotationFlows,@Param(value="standardDeptIds")List<String> standardDeptIds);
} 

