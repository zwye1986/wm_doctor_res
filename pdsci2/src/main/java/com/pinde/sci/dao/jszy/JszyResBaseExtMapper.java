package com.pinde.sci.dao.jszy;

import com.pinde.core.model.JszyResBaseExt;

import java.util.List;
import java.util.Map;


public interface JszyResBaseExtMapper {
	 List<JszyResBaseExt> searchResBaseExtList(Map<String, Object> paramMap);
}
