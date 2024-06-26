package com.pinde.sci.dao.irb;

import com.pinde.sci.form.irb.irbMeetingForm;
import com.pinde.sci.model.mo.IrbMeeting;

import java.util.List;

public interface IrbMeetingExtMapper {
	List<IrbMeeting> selectList(irbMeetingForm form);

	List<IrbMeeting> searchList(irbMeetingForm form);
}
