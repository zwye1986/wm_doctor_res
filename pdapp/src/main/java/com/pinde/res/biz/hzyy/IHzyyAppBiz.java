package com.pinde.res.biz.hzyy;

import java.util.List;
import java.util.Map;

public interface IHzyyAppBiz {

	/**
	 * 登陆
	 * @param userCode
	 * @param password
	 * @return
	 */
	Map<String,String> login(String userCode, String password);
	
	/**
	 * 查询用户
	 * @param userFlow
	 * @return
	 */
	Map<String,String> readUser(String userFlow);

	/**
	 * 查询学员
	 * @param userFlow
	 * @return
	 */
	Map<String, String> readDoctor(String doctorFlow);

	List<Map<String,String>> readUserRoles(String userFlow);
}
