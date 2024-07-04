package com.pinde.res.dao.bengyify.ext;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BengyifyAppMapper {
	/**
	 * 登录系统
	 * @param userCode 用户名
	 * @return
	 */
	List<Map<String,String>> login(@Param("userCode")String userCode);
	
	Map<String,String> readUser(@Param("userFlow")String userFlow);
	
	Map<String,String> readDoctor(@Param("doctorFlow")String doctorFlow);
	
}
