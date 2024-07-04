package com.pinde.res.dao.hzyy.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HzyyAppMapper {
	/**
	 * 登录系统
	 * @param userCode 用户名
	 * @return
	 */
	List<Map<String,String>> login(@Param("userCode") String userCode);
	
	Map<String,String> readUser(@Param("userFlow") String userFlow);
	
	Map<String,String> readDoctor(@Param("doctorFlow") String doctorFlow);

	List<Map<String,String>> readUserRoles(@Param("userFlow") String userFlow);

	Map<String,String> readDicItemByName(@Param("dicName") String dicName);
}
