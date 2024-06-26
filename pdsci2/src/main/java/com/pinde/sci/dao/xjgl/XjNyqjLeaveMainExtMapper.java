package com.pinde.sci.dao.xjgl;

import com.pinde.sci.model.mo.NyqjLeaveMain;

import java.util.List;
import java.util.Map;

public interface XjNyqjLeaveMainExtMapper {
    //导师-请假管理
    List<NyqjLeaveMain> queryNyqjListByTutor(Map<String, Object> paramMap);
    //培养单位-请假管理
    List<NyqjLeaveMain> queryNyqjListByPydw(Map<String, Object> paramMap);
    //思政科-请假管理
    List<NyqjLeaveMain> queryNyqjListBySzk(Map<String, Object> paramMap);
}
