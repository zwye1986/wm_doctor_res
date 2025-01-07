package com.pinde.sci.dao.jsres;

import com.pinde.core.model.ResBaseExt;

import java.util.List;
import java.util.Map;


public interface ResBaseExtMapper {
	 List<ResBaseExt> searchResBaseExtList(Map<String, Object> paramMap);
}
