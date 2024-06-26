package com.pinde.sci.dao.edu;

import com.pinde.sci.form.edu.OneCourseCreditForm;
import com.pinde.sci.form.edu.StudentCourseNumForm;
import com.pinde.sci.model.edu.EduStudentCourseExt;

import java.util.List;
import java.util.Map;

/**
 * @author tiger
 *
 */
public interface EduStudentCourseExtMapper {

	/**
	 * 查询某学生选择的所有课程及其学习情况
	 * @param paramMap
	 * @return
	 */
	List<EduStudentCourseExt> searchEduStudentCourseExtByUserFlow(Map<String, Object> paramMap);
	/**
	 * 查询某学生选择的所有课程(resedu)
	 * @param paramMap
	 * @return
	 */
//	public List<EduStudentCourseExt> searchEduStudentCourseExt(Map<String,Object> paramMap);
	/**
	 * 根据条件统计课程相关信息
	 * @param paramMap
	 * @return
	 */
	int countCourseInfoByCondition(Map<String, Object> paramMap);
	/**
     * 查询选修了某节课的学生的学分
     * @param paramMap
     * @return
     */
	List<OneCourseCreditForm> searchCourseCreditForm(Map<String, Object> paramMap);
	/**
	 * 查询某学生获得的学分情况(resedu)
	 * @param paramMap
	 * @return
	 */
	int sumUserCredit(Map<String, Object> paramMap);

//	List<StudentCourseNumForm> selectStudentCourse(Map<String, Object> paramMap);
	
	/**
	 * 查询已选课程记录
	 * @param paramMap
	 * @return
	 */
	List<StudentCourseNumForm> searchStudentCourseChooseCount(Map<String, Object> paramMap);
	
	
	/**
	 * 获取该年度该学员的所有选择课程
	 * @param studentCourseExt
	 * @return
	 */
//	List<EduStudentCourseExt> searchStudentCourseExtList(EduStudentCourseExt studentCourseExt);

//	List<EduStudentCourseExt> searchStudentCourseExtListByUserFlowList(List<String> userFlowList);

	/**
	 * 查询课程人数
	 * @param paramMap
	 * @return
	 */
//	List<StudentCourseNumForm> searchCourseCountByCourseFlowList(Map<String, Object> paramMap);

	List<EduStudentCourseExt> searchStudentCourseExtWithUserList(Map<String, Object> paramMap);

	List<EduStudentCourseExt> searchStudentCourse(Map<String, Object> paramMap);
	
}
