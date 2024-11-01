package com.pinde.sci.dao.sch;

import com.pinde.sci.model.mo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.*;

/**
 * proj锟斤拷展mapper
 * @author Administrator
 *
 */
public interface SchDoctorDeptExtMapper {
	
	int countRotationUse(@Param(value="rotationFlow")String rotationFlow);
	
	int cleanInvalidSelDept(Map<String,Object> paramMap);
	
	int countReducation(@Param(value="orgFlow")String orgFlow);

	int jszyCountReducation(String orgFlow);


	List<SysUser> listByNameOrIdNo(@Param("userName") Set<String> userName,
								   @Param("idNo") Set<String> idNo);
}
