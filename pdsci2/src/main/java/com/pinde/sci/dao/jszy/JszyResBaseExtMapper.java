package com.pinde.sci.dao.jszy;

import com.pinde.sci.model.jszy.JszyResBaseExt;

import java.util.List;
import java.util.Map;


public interface JszyResBaseExtMapper {
	 List<JszyResBaseExt> searchResBaseExtList(Map<String, Object> paramMap);
}
