package com.pinde.sci.biz.sys;

import com.pinde.sci.model.mo.SysLog;

import java.util.List;
import java.util.Map;


public interface ILogBiz {

	List<SysLog> searcherLog(SysLog log);

	List<SysLog> searchLog(Map<String, Object> paramMap);
	
}
