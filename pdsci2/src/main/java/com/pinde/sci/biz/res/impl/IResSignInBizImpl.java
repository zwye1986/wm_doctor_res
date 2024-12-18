package com.pinde.sci.biz.res.impl;

import com.pinde.core.model.ResSignin;
import com.pinde.sci.biz.res.IResSignInBiz;
import com.pinde.sci.dao.res.ResDoctorExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author PPbear
 *
 */
@Service
//@Transactional(rollbackFor=Exception.class)
public class IResSignInBizImpl implements IResSignInBiz{
	@Autowired
	private ResDoctorExtMapper doctorExtMapper;

	@Override
	public List<Map<String, Object>> searchSignInfo(ResSignin signin, String medicineTypeId) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("signin", signin);
		paramMap.put("medicineTypeId", medicineTypeId);
		return searchSignInfoByParamMap(paramMap);
	}
	
	@Override
	public List<Map<String, Object>> searchSignInfoByParamMap(Map<String,Object> paramMap) {
		return doctorExtMapper.searchSignInfo(paramMap);
	}

	@Override
	public List<Map<String,Object>> searchSign(Map<String,Object> paramMap){
		return doctorExtMapper.searchSign(paramMap);
	}
} 
 