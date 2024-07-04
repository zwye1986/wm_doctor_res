package com.pinde.res.dao.njmu.ext;

import java.util.Map;

public interface AddDataMapper {
	
	public void addActivityData(Map<String, Object> paramMap);
	
	public String addActivityDataSub1(Map<String, Object> paramMap);
	
	public String addActivityDataSub2(Map<String, Object> paramMap);
	
	public void addSummaryData(Map<String, Object> paramMap);
	
	public String addSummaryDataSub1(Map<String, Object> paramMap);
	
	public void addWfwork(Map<String, Object> paramMap);
	
	public String addWfworkSub1(Map<String, Object> paramMap);
	
	public String addWfworkSub2(Map<String, Object> paramMap);
	
	public String addWfworkSub3(Map<String, Object> paramMap);
}
