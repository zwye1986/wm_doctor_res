package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.IrbMeeting;
import com.pinde.sci.model.mo.IrbMeetingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IrbMeetingMapper {
    int countByExample(IrbMeetingExample example);

    int deleteByExample(IrbMeetingExample example);

    int deleteByPrimaryKey(String meetingFlow);

    int insert(IrbMeeting record);

    int insertSelective(IrbMeeting record);

    List<IrbMeeting> selectByExample(IrbMeetingExample example);

    IrbMeeting selectByPrimaryKey(String meetingFlow);

    int updateByExampleSelective(@Param("record") IrbMeeting record, @Param("example") IrbMeetingExample example);

    int updateByExample(@Param("record") IrbMeeting record, @Param("example") IrbMeetingExample example);

    int updateByPrimaryKeySelective(IrbMeeting record);

    int updateByPrimaryKey(IrbMeeting record);
}