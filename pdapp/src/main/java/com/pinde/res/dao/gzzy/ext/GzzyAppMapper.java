package com.pinde.res.dao.gzzy.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GzzyAppMapper {
	/**
	 * 登录系统
	 * @param userCode 用户名
	 * @return
	 */
	List<Map<String,String>> login(@Param("userCode") String userCode);

	List<Map<String,String>> readUser(@Param("userFlow") String userFlow);
	
	Map<String,String> readDoctor(@Param("doctorFlow") String doctorFlow);

	List<Map<String,String>> readUserRoles(@Param("userFlow") String userFlow);

	List<Map<String,Object>> getItemMap();

	Map<String,String> readActivityInfo(@Param("activityFlow") String activityFlow);

	Map<String,String> readRegistInfo(@Param("activityFlow") String activityFlow, @Param("userFlow") String userFlow);

	int saveSign(@Param("activityFlow") String activityFlow, @Param("userFlow") String userFlow);
}
