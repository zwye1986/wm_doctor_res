package com.pinde.sci.dao.irb;

import com.pinde.sci.model.mo.IrbMeetingUser;

import java.util.List;

public interface IrbMeetingUserExtMapper {

	List<IrbMeetingUser> searchMeetingUserList(String year);
	
}
