package com.pinde.sci.biz.pubedu;

import com.pinde.sci.model.mo.StudyCourse;
import com.pinde.sci.model.mo.StudyCourseDetail;
import com.pinde.sci.model.mo.StudyCourseDetailInfo;

import java.util.List;

/**
 * Created by www.0001.Ga on 2017-05-02.
 */
public interface IStudyCourseBiz {
	List<StudyCourse> findStudyCourseList(StudyCourse studyCourse);

	List<StudyCourse> findStudyCourseListOrderByASC();

	StudyCourse searchCourseByFlow(String courseFlow);

	List<StudyCourseDetail> findStudyCourseDetailByFlow(String courseFlow);

	List<StudyCourseDetailInfo> findDetailInfoByFlow(String courseFlow);

	StudyCourseDetail findCourseDetailByRecordFlow(String detailFlow);

	StudyCourseDetailInfo findDetailInfoByRecordFlow(String detailInfoFlow);

	List<StudyCourseDetail> findStudyCourseByCourseFlow(String courseFlow);

	int insert(StudyCourse studyCourse);

	int deleteByCourseFlow(String courseFlow);

	int updateStudyCourse(StudyCourse studyCourse);

	int updateStudyCourseBySelect(String type,String courseFlow,String courseName );

	int updateStudyCourseDetail(StudyCourseDetail courseDetail);

	List<StudyCourseDetailInfo> findStudyCourseDetailInfoByCourseFlow(String courseFlow);

	int insertDetailInfo(StudyCourseDetailInfo detailInfo);

	int insertStudyDetail(StudyCourseDetail detail);

	int updateStudyCourseDetailInfo(StudyCourseDetailInfo studyCourseDetailInfo);

	int deleteByRecordFlow(String recordFlow);

	int deleteByDetailFlow(StudyCourseDetail studyCourseDetail);
}
