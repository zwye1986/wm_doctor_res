package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubMeeting;
import com.pinde.sci.model.mo.PubMeetingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PubMeetingMapper {
    int countByExample(PubMeetingExample example);

    int deleteByExample(PubMeetingExample example);

    int deleteByPrimaryKey(String meetingFlow);

    int insert(PubMeeting record);

    int insertSelective(PubMeeting record);

    List<PubMeeting> selectByExample(PubMeetingExample example);

    PubMeeting selectByPrimaryKey(String meetingFlow);

    int updateByExampleSelective(@Param("record") PubMeeting record, @Param("example") PubMeetingExample example);

    int updateByExample(@Param("record") PubMeeting record, @Param("example") PubMeetingExample example);

    int updateByPrimaryKeySelective(PubMeeting record);

    int updateByPrimaryKey(PubMeeting record);
}