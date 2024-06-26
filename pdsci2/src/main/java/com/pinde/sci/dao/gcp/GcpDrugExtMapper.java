package com.pinde.sci.dao.gcp;

import com.pinde.sci.model.gcp.GcpDrugExt;

import java.util.List;
import java.util.Map;

public interface GcpDrugExtMapper {
    List<GcpDrugExt> searchGcpDrugList(Map<String, Object> paramMap);

    List<String> searchDrugPacks(Map<String, Object> map);

}
