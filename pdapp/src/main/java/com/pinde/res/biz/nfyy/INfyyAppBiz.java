package com.pinde.res.biz.nfyy;

import java.util.Map;

public interface INfyyAppBiz {

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

	int updateUserPass(String userFlow, String newPassword);
}
