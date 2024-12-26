package com.pinde.res.dao.jswjw.ext;

import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2016/10/8.
 */
public interface ResAttendanceExtMapper {

    //江苏西医
    List<Map<String,String>> attendanceList2(Map<String, Object> paramMap);
    List<Map<String,String>> attendanceList3(Map<String, Object> paramMap);
}
