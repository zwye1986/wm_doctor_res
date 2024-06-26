package com.pinde.sci.dao.gyxjgl;

import com.pinde.sci.model.gyxjgl.XjEduStudentChangeExt;

import java.util.List;
import java.util.Map;

public interface GyXjEduStdentChangeExtMapper {
    /**
     * 查询学生异动扩展信息
     *
     * @param paramMap
     * @return
     */
    List<XjEduStudentChangeExt> searchStdentChangeExtsList(Map<String, Object> paramMap);
}
