package com.pinde.sci.dao.base;

import com.pinde.core.model.ResDoctorExt;

import java.util.List;
import java.util.Map;

public interface ResDoctorExtMapper {

	/**
	 * 查询用户及签到信息
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> searchSign(Map<String, Object> paramMap);

	/**
	 * 培训学员查询
	 * @param resDoctorExt
	 * @return
     */
	List<ResDoctorExt> searchResDoctorUser(ResDoctorExt resDoctorExt);
}
