package com.pinde.sci.dao.njmuedu;

import com.pinde.sci.model.njmuedu.EduAnswerExt;

import java.util.List;
import java.util.Map;


public interface NjmuEduAnswerExtMapper {

	/**
	 * 获取作业平均分
	 * @param paramMap
	 * @return
	 */
	double getAnsGradeSum(Map<String, Object> paramMap);

	/**
	 * 查询学生作业
	 * @param
	 * @return
	 */
	List<EduAnswerExt> searchStudentWorkAnswerList(Map<String, Object> paramMap);

}
