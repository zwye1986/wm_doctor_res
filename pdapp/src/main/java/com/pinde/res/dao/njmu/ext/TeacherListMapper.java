package com.pinde.res.dao.njmu.ext;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TeacherListMapper {
	
	public List<Map<String, Object>> search(@Param(value="userFlow") String userFlow,@Param(value="deptFlow") String deptFlow,@Param(value="teacherName") String teacherName,@Param(value="startRow") int startRow,@Param(value="endRow") int endRow);

}
