package com.pinde.res.biz.gzzy;

import java.util.List;
import java.util.Map;

public interface IGzzyAppBiz {

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
	 */
	Map<String, String> readDoctor(String doctorFlow);

	List<Map<String,String>> readUserRoles(String userFlow);

	/**
	 */
	void addAttendance(Map<String,Object> paramMap);

	void addAttendanceDetail(Map<String,Object> paramMap);
	/**
	 * 获取当天签到次数
	 * @return
	 */
	List<Map<String,String>> getAttendanceDetailList(Map<String,Object> paramMap);

	/**
	 * 签到信息
	 * @return
	 */
	Map<String,String> getAttendance(Map<String,Object> paramMap);

	Map<String,List<Map<String,Object>>> getItemMap();


	Map<String,String> readActivityInfo(String activityFlow);

	Map<String,String> readRegistInfo(String activityFlow, String userFlow);

	int saveSign(String activityFlow, String userFlow);
}
