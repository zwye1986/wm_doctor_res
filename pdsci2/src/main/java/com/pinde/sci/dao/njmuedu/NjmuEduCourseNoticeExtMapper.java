package com.pinde.sci.dao.njmuedu;

import com.pinde.sci.model.njmuedu.EduCourseNoticeExt;

import java.util.List;

public interface NjmuEduCourseNoticeExtMapper {
	List<EduCourseNoticeExt> searchCourseNoticeListByCourseFlow(String courseFlow);
}
