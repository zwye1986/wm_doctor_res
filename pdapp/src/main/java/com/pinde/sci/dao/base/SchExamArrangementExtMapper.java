package com.pinde.sci.dao.base;


import com.pinde.core.model.SchExamArrangement;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SchExamArrangementExtMapper {

	int findDocExamCount(@Param("userFlow") String userFlow, @Param("arrangeFlow") String arrangeFlow);

	Map<String,Object> getSuiJiConfig(@Param("ment") SchExamArrangement ment, @Param("userFlow") String userFlow);
	Map<String,Object> getGuDingConfig(@Param("ment") SchExamArrangement ment);
	/**
	 * 根据arrangeFlow和doctorFlow查询医师考试的记录
	 * @param paramMap
	 * @return
	 */
	List<Map<String,String>> searchExamLogByItems(Map<String, String> paramMap);

	int deleteSchExamStandardDeptByDeptId(@Param("deptFlow") String deptFlow);

	int checkHaveExam(@Param("arrangeFlow") String arrangeFlow);
}