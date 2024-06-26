package com.pinde.sci.dao.hbzy;


import com.pinde.sci.model.mo.JsresUserBalcklist;

import java.util.List;
import java.util.Map;

public interface HbzyResUserBalckListExtMapper {

	List<JsresUserBalcklist> selectBlackUser(Map<String, Object> map);
	List<JsresUserBalcklist> selectBlackUserOfAll(Map<String, Object> map);
	List<JsresUserBalcklist> selectBlack();

	/**
	 * 根据用户ID查询黑名单
	 * @param userIdNo
	 * @return
     */
	JsresUserBalcklist selectByIdNo(String userIdNo);
}
