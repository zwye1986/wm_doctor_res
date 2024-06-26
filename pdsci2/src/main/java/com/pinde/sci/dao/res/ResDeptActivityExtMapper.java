package com.pinde.sci.dao.res;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface ResDeptActivityExtMapper {

	List<Map<String,Object>> searchList(Map<String, Object> param);

	List<Map<String,Object>> searchStatisticList(Map<String, Object> param);

	void updateItemCfg(@Param("planFlow") String planFlow, @Param("id") String id, @Param("recordFlow") String recordFlow);
}
