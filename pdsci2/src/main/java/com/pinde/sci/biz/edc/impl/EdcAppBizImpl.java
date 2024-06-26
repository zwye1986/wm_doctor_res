package com.pinde.sci.biz.edc.impl;

import com.pinde.sci.biz.edc.IEdcAppBiz;
import com.pinde.sci.dao.base.EdcAppLogMapper;
import com.pinde.sci.model.mo.EdcAppLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class EdcAppBizImpl implements IEdcAppBiz{

	@Autowired
	private EdcAppLogMapper appLogMapper;
	
	@Override
	public void add(EdcAppLog applog) {
		appLogMapper.insert(applog);
	}
 
}  
  