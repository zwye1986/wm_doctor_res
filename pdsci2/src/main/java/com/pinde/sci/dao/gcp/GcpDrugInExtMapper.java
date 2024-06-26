package com.pinde.sci.dao.gcp;

import com.pinde.sci.model.mo.GcpDrugIn;

import java.util.List;
import java.util.Map;

public interface GcpDrugInExtMapper {
//	public List<GcpDrugInExt> searchDrugInList(Map<String,Object> paramMap);

	List<GcpDrugIn> searchDrugIns(Map<String, Object> paramMap);
}
