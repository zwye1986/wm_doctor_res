package com.pinde.sci.dao.res;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2017-04-24.
 */
public interface ResEntryReportExtMapper {

	List<Map<String,String>> getReportList(Map<String, Object> param);
}
