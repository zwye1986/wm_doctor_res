package com.pinde.res.dao.zsyk.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ZsykAppMapper {
	/**
	 * 登录系统
	 * @param userCode 用户名
	 * @return
	 */
	List<Map<String,String>> login(@Param("userCode") String userCode);

	Map<String,String> readUser(@Param("userFlow") String userFlow);

	Map<String,String> readDoctor(@Param("doctorFlow") String doctorFlow);
	
}
