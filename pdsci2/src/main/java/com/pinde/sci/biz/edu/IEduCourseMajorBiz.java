package com.pinde.sci.biz.edu;

import com.pinde.sci.form.edu.CourseMajorNumForm;
import com.pinde.sci.model.edu.EduCourseExt;
import com.pinde.sci.model.edu.EduCourseMajorExt;
import com.pinde.sci.model.mo.EduCourse;
import com.pinde.sci.model.mo.EduCourseMajor;

import java.util.List;
import java.util.Map;


public interface IEduCourseMajorBiz {
	
	List<EduCourseMajor> searchCourseMajorList(EduCourseMajor eduCourseMajor);

	List<EduCourseMajor> searchCourseMajorByCourseFlowList(EduCourseMajor courseMajor, List<String> courseFlowList);
	
	List<EduCourseExt> courseMajorExts(EduCourse course,EduCourseMajor courseMajor);
	
	EduCourseMajor courseMajor(String recordFlow);
	

	List<EduCourseMajor> searchCourseMajorLists(List<String> courseFlowList);
	
	List<CourseMajorNumForm> selectCourseMajorNum(String courseFlow,String time); 
	
	EduCourseMajor readCourseMajor(String recordFlow);

	/**
	 * 查询该年度该专业所设全部课程
	 * @param planYear
	 * @param majorId
	 * @param trainTypeId	培养层次
	 * @return
	 */
	List<EduCourseMajorExt> searchCourseMajorExtList(String planYear, String majorId, String trainTypeId);

	/**
	 * 按课程类别组织Map
	 * @param period
	 * @param courseMajorExtList
	 * @return
	 */
	Map<String, Object> extractCourseMajorMap(String period, List<EduCourseMajorExt> courseMajorExtList);

	/**
	 * 查询该年度该专业  补选课程
	 * @param planYear
	 * @param majorId
	 * @param userFlow
	 * @return
	 */
	List<EduCourseMajorExt> searchReplenishCourseMajorExtList(String planYear, String majorId, String trainTypeId, String userFlow);
	/**
	 * 根据多条件查询出唯一记录
	 * 
	 */
	List<EduCourseMajor> onlyCourseMajor(EduCourseMajor courseMajor);

	

}
