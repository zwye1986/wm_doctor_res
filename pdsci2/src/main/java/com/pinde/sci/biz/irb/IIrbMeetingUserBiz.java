package com.pinde.sci.biz.irb;

import com.pinde.sci.model.mo.IrbMeetingUser;

import java.util.List;


public interface IIrbMeetingUserBiz {

    List<IrbMeetingUser> searchMeetingUserList(IrbMeetingUser meetingUser);

    /**
     * 查询参会人员记录数
     *
     * @param meetingFlows
     * @return
     */
    long queryMeetingUserCount(List<String> meetingFlows);

    List<IrbMeetingUser> searchMeetingUserList(String year);
}
