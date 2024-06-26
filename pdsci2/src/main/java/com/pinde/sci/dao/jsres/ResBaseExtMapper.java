package com.pinde.sci.dao.jsres;

import com.pinde.sci.model.jsres.ResBaseExt;

import java.util.List;
import java.util.Map;


public interface ResBaseExtMapper {
	 List<ResBaseExt> searchResBaseExtList(Map<String, Object> paramMap);
}
