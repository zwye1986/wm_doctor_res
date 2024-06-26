package com.pinde.sci.dao.hbzy;

import com.pinde.sci.model.hbzy.JszyResBaseExt;

import java.util.List;
import java.util.Map;


public interface HbzyResBaseExtMapper {
	 List<JszyResBaseExt> searchResBaseExtList(Map<String, Object> paramMap);
}
