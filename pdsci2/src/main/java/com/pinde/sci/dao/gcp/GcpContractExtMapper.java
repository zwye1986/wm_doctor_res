package com.pinde.sci.dao.gcp;

import com.pinde.sci.model.gcp.GcpContractExt;

import java.util.List;
import java.util.Map;

public interface GcpContractExtMapper {
	List<GcpContractExt> searchContractList(Map<String, Object> paramMap);
}
