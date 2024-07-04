package com.pinde.res.biz.hzyy.impl;

import com.pinde.res.biz.hzyy.IHzyyKzrBiz;
import com.pinde.res.dao.hzyy.ext.HzyyKzrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class HzyyKzrBizImpl implements IHzyyKzrBiz {

	@Autowired
	private HzyyKzrMapper hzyyKzrMapper;

	@Override
	public List<Map<String, Object>> getStudentList(Map<String, Object> param, Integer pageIndex, Integer pageSize) {

		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		param.put("start",start);
		param.put("end",end);
		return hzyyKzrMapper.getStudentList(param);
	}

	@Override
	public List<Map<String, Object>> getDeptList(Map<String, Object> param, Integer pageIndex, Integer pageSize) {

		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		param.put("start",start);
		param.put("end",end);
		return hzyyKzrMapper.getDeptList(param);
	}

	@Override
	public List<Map<String, Object>> getDeptEvalInfo(String HosID,String hosSecID, String year) {
		return hzyyKzrMapper.getDeptEvalInfo(HosID,hosSecID,year);
	}

	@Override
	public List<Map<String, Object>> getTeacherList(Map<String, Object> param, Integer pageIndex, Integer pageSize) {

		int start = (pageIndex-1)*pageSize+1;
		int end = pageIndex*pageSize;
		param.put("start",start);
		param.put("end",end);
		return hzyyKzrMapper.getTeacherList(param);
	}

	@Override
	public List<Map<String, Object>> getTeacherEvalInfo(String hosID, String teaFlow, String startTime, String endTime) {

		return hzyyKzrMapper.getTeacherEvalInfo(hosID,teaFlow,startTime,endTime);
	}
}
