package com.pinde.res.dao.shfx.ext;

import java.util.List;
import java.util.Map;

public interface ShfxAppMapper {

	List<Map<String,Object>> searchStatisticList(Map<String, Object> param);
}
