package com.pinde.sci.biz.sch.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sch.IZseyHrKqMonthBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ZseyHrKqMonthMapper;
import com.pinde.sci.dao.res.ResDoctorSchProcessExtMapper;
import com.pinde.sci.dao.res.ZseyHrKqMonthExtMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class ZseyHrKqMonthBizImpl implements IZseyHrKqMonthBiz {
	@Autowired
	private ZseyHrKqMonthMapper zseyHrKqMonthMapper;
	@Autowired
	private ZseyHrKqMonthExtMapper zseyHrKqMonthExtMapper;
	@Autowired
	private ResDoctorSchProcessExtMapper doctorSchProcessExtMapper;

	@Override
	public ZseyHrKqMonth read(String monthFlow) {
		if(StringUtil.isNotBlank(monthFlow)){
			return zseyHrKqMonthMapper.selectByPrimaryKey(monthFlow);
		}else {
			return null;
		}
	}

	@Override
	public int edit(ZseyHrKqMonth zseyHrKqMonth) {
		if(zseyHrKqMonth != null){
			if(StringUtil.isNotBlank(zseyHrKqMonth.getMonthFlow())){
				GeneralMethod.setRecordInfo(zseyHrKqMonth, false);
				return zseyHrKqMonthMapper.updateByPrimaryKeySelective(zseyHrKqMonth);
			}else{
				zseyHrKqMonth.setMonthFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(zseyHrKqMonth, true);
				return zseyHrKqMonthMapper.insertSelective(zseyHrKqMonth);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public ZseyHrKqMonth searchByKqDate(String userFLow,String kqDate) {
		ZseyHrKqMonthExample example = new ZseyHrKqMonthExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andKqDateEqualTo(kqDate).andUserFlowEqualTo(userFLow);
		if(zseyHrKqMonthMapper.selectByExample(example)!=null&&zseyHrKqMonthMapper.selectByExample(example).size()>0){
			return zseyHrKqMonthMapper.selectByExample(example).get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, String>> searchKq(String startDate, String endDate, String userFlow) {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("startDate",startDate);
		paramMap.put("endDate",endDate);
		paramMap.put("userFlow",userFlow);
		return zseyHrKqMonthExtMapper.searchKq(paramMap);
	}

	@Override
	public List<Map<String, Object>> searchProcessByMonth(Map<String, Object> paramMap) {
		return doctorSchProcessExtMapper.searchProcessByMonth(paramMap);
	}

	@Override
	public List<Map<String, Object>> searchProcessByTime(Map<String, Object> paramMap) {
		return doctorSchProcessExtMapper.searchProcessByTime(paramMap);
	}

	@Override
	public List<Map<String, Object>> searchProcessByDept(Map<String, Object> paramMap) {
		return doctorSchProcessExtMapper.searchProcessByDept(paramMap);
	}

	@Override
	public List<ZseyHrKqMonth> searchKqByDates(List<String> dates,String userFlow) {
		ZseyHrKqMonthExample example = new ZseyHrKqMonthExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andKqDateIn(dates).andUserFlowEqualTo(userFlow);
		return zseyHrKqMonthMapper.selectByExample(example);
	}
}
