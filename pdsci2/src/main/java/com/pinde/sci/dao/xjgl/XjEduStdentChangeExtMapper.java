package com.pinde.sci.dao.xjgl;

import com.pinde.sci.model.xjgl.XjEduStudentChangeExt;

import java.util.List;
import java.util.Map;

public interface XjEduStdentChangeExtMapper {
    /**
     * 查询学生异动扩展信息
     *
     * @param paramMap
     * @return
     */
    List<XjEduStudentChangeExt> searchStdentChangeExtsList(Map<String, Object> paramMap);
}
