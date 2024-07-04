package com.pinde.res.dao.jswjw.ext;

import java.util.Map;

public interface JsResUserBalckListExtMapper {

	int selectBlackUser(Map<String, Object> map);

	int selectBlackUserCount(Map<String, Object> map);

	int selectBlacklistByIdNo(String idNo);
}
