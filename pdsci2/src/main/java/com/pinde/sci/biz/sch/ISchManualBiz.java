package com.pinde.sci.biz.sch;

import java.util.List;
import java.util.Map;


public interface ISchManualBiz {
	List<Map<String,Object>> userList(Map<String, Object> params);

	//	不包含退培的学员
	List<Map<String,Object>> userListNotBack(Map<String, Object> params);

	List<Map<String,Object>> teaList(Map<String, Object> params);

	/**
	 * 根据权限开通情况筛选学员
	 * 连接res_power_cfg表
	 * @param params
	 * @return
     */
	List<Map<String,Object>> userListByPower(Map<String, Object> params);
	/**
	 * 根据权限开通情况筛选学员
	 * 连接jsres_power_cfg表
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> userListByJsResPower(Map<String, Object> params);

    List<Map<String,Object>> userListByJsResPower2(Map<String, Object> params);

    List<Map<String,Object>> userList2(Map<String, Object> params);

    List<Map<String,Object>> searchStaticsList(Map<String, Object> params);
}
