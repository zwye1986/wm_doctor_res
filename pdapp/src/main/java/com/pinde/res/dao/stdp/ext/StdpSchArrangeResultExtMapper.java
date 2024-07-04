package com.pinde.res.dao.stdp.ext;

import com.pinde.sci.model.mo.SchArrangeResult;

import java.util.List;
import java.util.Map;

public interface StdpSchArrangeResultExtMapper {
	/**
	 * 获取该医师总轮转计划的开始时间和结束时间
	 * @param paramMap
	 * @return
	 */
	Map<String,Object> getDocResultArea(Map<String,Object> paramMap);
	
	/**
	 * 获取该用户的排班及轮转登记情况
	 * @param paramMap
	 * @return
	 */
	List<Map<String,Object>> searchResult(Map<String,Object> paramMap);

	/**
	 * 验证是否存在时间重叠科室
	 * @param paramMap
	 * @return
     */
	List<SchArrangeResult> checkResultDate(Map<String,Object> paramMap);
}
