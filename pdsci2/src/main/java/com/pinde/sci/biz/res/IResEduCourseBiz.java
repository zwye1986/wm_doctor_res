package com.pinde.sci.biz.res;

import com.pinde.sci.form.res.EduCourseSearchConditionForm;
import com.pinde.sci.form.res.EduFileForm;
import com.pinde.sci.model.mo.EduCourse;
import com.pinde.sci.model.mo.EduCourseSchedule;
import com.pinde.sci.model.mo.EduStudentCourse;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.res.EduCourseExt;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IResEduCourseBiz {
	/**
	 * 查询某学生所选择的课程
	 * @param eduCourse
	 * @param sysUser
	 * @param studyStatus
	 * @return
     */
	List<EduCourse> searchStuCourseList(EduCourse eduCourse, SysUser sysUser, String studyStatus);

	/**
	 * 查询课程包含所有章节
	 *
	 * @param courseFlow 课程流水号
	 * @return
	 */
	EduCourseExt searchOneWithChapters(String courseFlow);


	int editCourse(EduCourse course, MultipartFile file) throws Exception;

	/**
	 * 保存课程
	 *
	 * @param course
	 * @return
	 */
	int saveCourse(EduCourse course);

	/**
	 * 查询课程
	 *
	 * @param course
	 * @return
	 */
	List<EduCourse> searchCourseList(EduCourse course);

	/**
	 * 查询课程
	 *
	 * @param course
	 * @return
	 */
	List<EduCourse> searchCourseList(EduCourse course, EduCourseSearchConditionForm form);

	/**
	 * 获取一条课程记录
	 *
	 * @param courseFlow
	 * @return
	 */
	EduCourse readCourse(String courseFlow);

	/**
	 * 删除课程图片
	 *
	 * @param courseFlow
	 * @return
	 */
	int deleteCourseImg(String courseFlow);

	/**
	 * 删除课程
	 *
	 * @param courseFlow
	 * @return
	 */
	int delCourse(String courseFlow);

	/**
	 * 查询某教师任教的课程
	 *
	 * @param eduCourse
	 * @param sysUser
	 * @return
	 */
	List<EduCourse> searchTchCourseList(EduCourse eduCourse, SysUser sysUser);

	/**
	 * 查找选择某教师任教课程的学生
	 *
	 * @param sysUser
	 * @return
	 */
	List<SysUser> searchUserByTch(SysUser sysUser);

	/**
	 * 查询学生某一门课的学习情况
	 *
	 * @param schedule
	 * @return
	 */
	List<EduCourseSchedule> searchScheduleList(EduCourseSchedule schedule);

	/**
	 * 查询某用户的全部学分总和
	 * @param userFlow
	 * @return
	 */
	int countUserAllCredit(String userFlow);

	Map<String, Object> countUserByStudyStatus(String courseFlow);

	/**
	 * @return
	 */
	List<EduStudentCourse> searchStudentCourse(EduStudentCourse eduStudentCourse);

	/**
	 * 选择课程
	 * @param userFlow
	 * @param courseFlow
	 * @return
	 */
	int chooseCourse(String userFlow, String courseFlow);

	/**
	 * 根据条件查询课程
	 * @param condition
	 * @return
	 */
	List<EduCourse> searchCourseByCondition(String condition);

	/**
	 * 保存文件
	 * @param file
	 * @param type
	 * @return
	 * @throws Exception
	 */
	EduFileForm saveFile(MultipartFile file, String type) throws Exception;
	/**
	 * 判断角色
	 * @return
	 */
	String checkRole();

	/**
	 * 按条件查询课程信息
	 */
	List<EduCourse> readCourse(EduCourse course);
}
