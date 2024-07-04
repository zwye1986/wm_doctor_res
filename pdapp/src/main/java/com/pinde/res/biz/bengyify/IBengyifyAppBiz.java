package com.pinde.res.biz.bengyify;

import java.util.Map;

public interface IBengyifyAppBiz {

	/**
	 * 登陆
	 * @param userCode
	 * @param password
	 * @return
	 */
	Map<String,String> login(String userCode , String password);
	
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
	
}
