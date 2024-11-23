package com.pinde.sci.biz.sch.impl;

import com.pinde.sci.biz.sch.ISchManualBiz;
import com.pinde.sci.dao.sch.SchManualExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SchManualBizImpl implements ISchManualBiz {

	@Autowired
	private SchManualExtMapper schManualExtMapper;

	@Override
	public List<Map<String, Object>> userList(Map<String, Object> params) {
		return schManualExtMapper.userList(params);
	}
	@Override
	public List<Map<String, Object>> userListNotBack(Map<String, Object> params) {
		return schManualExtMapper.userListNotBack(params);
	}
	@Override
	public List<Map<String, Object>> teaList(Map<String, Object> params) {
		return schManualExtMapper.teaList(params);
	}

	@Override
	public List<Map<String, Object>> userListByPower(Map<String, Object> params) {
		return schManualExtMapper.userListByPower(params);
	}
	@Override
	public List<Map<String, Object>> userListByJsResPower(Map<String, Object> params) {
		return schManualExtMapper.userListByJsResPower(params);
	}
	@Override
	public List<Map<String, Object>> userListByJsResPower2(Map<String, Object> params) {
		return schManualExtMapper.userListByJsResPower2(params);
	}
	@Override
	public List<Map<String, Object>> userList2(Map<String, Object> params) {
		return schManualExtMapper.userList2(params);
	}

	@Override
	public List<Map<String, Object>> searchStaticsList(Map<String, Object> params) {
		return schManualExtMapper.searchStaticsList(params);
	}
}
