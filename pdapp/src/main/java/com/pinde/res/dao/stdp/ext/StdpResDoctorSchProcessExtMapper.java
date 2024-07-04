package com.pinde.res.dao.stdp.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StdpResDoctorSchProcessExtMapper {
	/**
	 * 获取医师第一次入科时间和最后一次出科时间
	 * @param paramMap
	 * @return
	 */
	Map<String,Object> getDocProcessArea(Map<String,Object> paramMap);

	/**
	 * 培训学员实际轮转进度查询
	 * @param userFlows
	 * @return
     */
	List<Map<String,Object>> countProcessByUser(@Param(value="userFlows")List<String> userFlows);
}
