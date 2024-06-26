package com.pinde.sci.biz.res.impl;


import com.pinde.sci.biz.res.IResEntryReportBiz;
import com.pinde.sci.dao.res.ResEntryReportExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class ResEntryReportBizImpl implements IResEntryReportBiz {
	@Autowired
	private ResEntryReportExtMapper reportExtMapper;
	@Override
	public List<Map<String, String>> getReportList(Map<String, Object> param) {
		return reportExtMapper.getReportList(param);
	}
}
