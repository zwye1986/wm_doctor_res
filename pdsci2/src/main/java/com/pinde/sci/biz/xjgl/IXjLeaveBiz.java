package com.pinde.sci.biz.xjgl;

import com.pinde.sci.model.mo.NyqjLeaveMain;

import java.util.List;
import java.util.Map;

public interface IXjLeaveBiz {

    List<NyqjLeaveMain> searchNyqjLeaveList(NyqjLeaveMain nyqjLeaveMain);

    int saveNyqjLeaveMain(NyqjLeaveMain nyqjLeaveMain);

    int delLeave(String recordFlow);

    NyqjLeaveMain searchNyqjLeaveByFlow(String recordFlow);

    /**
     * 请假审核列表
     * @param leaveMain
     * @return
     */
    List<NyqjLeaveMain> searchLeaveApplyList(NyqjLeaveMain leaveMain);

    List<NyqjLeaveMain> searchNyqjListByTutor(Map<String, Object> paramMap);

    List<NyqjLeaveMain> searchNyqjListByPydw(Map<String, Object> paramMap);

    List<NyqjLeaveMain> searchNyqjListBySzk(Map<String, Object> paramMap);
}
