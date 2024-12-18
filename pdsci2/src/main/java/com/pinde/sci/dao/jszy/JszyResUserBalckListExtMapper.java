package com.pinde.sci.dao.jszy;


import com.pinde.core.model.JsresUserBalcklist;

import java.util.List;
import java.util.Map;

public interface JszyResUserBalckListExtMapper {

	List<JsresUserBalcklist> selectBlackUser(Map<String, Object> map);
	List<JsresUserBalcklist> selectBlackUserOfAll(Map<String, Object> map);
	List<JsresUserBalcklist> selectBlack();

	/**
	 * 根据用户ID查询黑名单
	 * @param userIdNo
	 * @return
     */
	JsresUserBalcklist selectByIdNo(String userIdNo);

	List<JsresUserBalcklist> searchInfo(Map<String, Object> map);
}
