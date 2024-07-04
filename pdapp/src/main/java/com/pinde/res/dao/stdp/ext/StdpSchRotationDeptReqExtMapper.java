package com.pinde.res.dao.stdp.ext;

import java.util.List;
import java.util.Map;

import com.pinde.sci.model.mo.ResRec;

public interface StdpSchRotationDeptReqExtMapper {
	List<Map<String,Object>> getReqAndProcess(Map<String,Object> paramMap);
}
