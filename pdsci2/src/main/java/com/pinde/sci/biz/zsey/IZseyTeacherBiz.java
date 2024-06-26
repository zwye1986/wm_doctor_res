package com.pinde.sci.biz.zsey;

import com.pinde.sci.model.mo.ZseyAppointMain;

import java.util.List;
import java.util.Map;

public interface IZseyTeacherBiz {

    //培训预约信息查询
    List<ZseyAppointMain> queryAppointList(String trainDate);
}
