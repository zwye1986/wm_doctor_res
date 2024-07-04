package com.pinde.res.dao.qpres.ext;

import java.util.List;
import java.util.Map;

public interface QpresAppMapper {

	List<Map<String,Object>> searchStatisticList(Map<String, Object> param);
}
