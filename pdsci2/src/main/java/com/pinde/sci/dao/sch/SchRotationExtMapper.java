package com.pinde.sci.dao.sch;


import com.pinde.core.model.SchRotation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchRotationExtMapper {
//	List<SchRotation> searchRotationByRole(@Param(value="roleFlag")String roleFlag,@Param(value="rotation")SchRotation rotation);
	
	List<SchRotation> searchNotExistRotation(@Param(value="orgFlow")String orgFlow);
} 
