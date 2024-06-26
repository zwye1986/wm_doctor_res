package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.ResSignin;

import java.util.List;
import java.util.Map;



public interface IResSignInBiz {
	public List<Map<String, Object>> searchSignInfo(ResSignin signin, String medicineTypeId);

	List<Map<String, Object>> searchSignInfoByParamMap(
			Map<String, Object> paramMap);

	/**
	 * 查询用户及签到信息
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> searchSign(Map<String, Object> paramMap);
}
