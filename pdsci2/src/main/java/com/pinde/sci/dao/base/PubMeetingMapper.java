package com.pinde.sci.dao.base;

import com.pinde.core.model.PubMeeting;
import com.pinde.core.model.PubMeetingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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