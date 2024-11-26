package com.pinde.res.dao.stdp.ext;

import com.pinde.core.model.ResRec;

import java.util.List;
import java.util.Map;

public interface StdpResRecExtMapper {
	/**
	 * 查询该学员的登记信息
	 * @param paramMap
	 * @return
	 */
	List<ResRec> getRecs(Map<String,Object> paramMap);
}
