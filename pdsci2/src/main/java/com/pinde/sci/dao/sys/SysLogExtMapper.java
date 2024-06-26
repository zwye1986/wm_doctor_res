package com.pinde.sci.dao.sys;

import com.pinde.sci.model.mo.SysLog;

import java.util.List;
import java.util.Map;

public interface SysLogExtMapper {

	List<SysLog> searchLog(Map<String,Object> paramMap);
}
