package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduCourseNotice;
import com.pinde.sci.model.mo.EduCourseNoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduCourseNoticeMapper {
    int countByExample(EduCourseNoticeExample example);

    int deleteByExample(EduCourseNoticeExample example);

    int deleteByPrimaryKey(String noticeFlow);

    int insert(EduCourseNotice record);

    int insertSelective(EduCourseNotice record);

    List<EduCourseNotice> selectByExample(EduCourseNoticeExample example);

    EduCourseNotice selectByPrimaryKey(String noticeFlow);

    int updateByExampleSelective(@Param("record") EduCourseNotice record, @Param("example") EduCourseNoticeExample example);

    int updateByExample(@Param("record") EduCourseNotice record, @Param("example") EduCourseNoticeExample example);

    int updateByPrimaryKeySelective(EduCourseNotice record);

    int updateByPrimaryKey(EduCourseNotice record);
}