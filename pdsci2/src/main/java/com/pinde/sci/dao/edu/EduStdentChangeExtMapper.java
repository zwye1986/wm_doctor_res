package com.pinde.sci.dao.edu;

import com.pinde.sci.model.edu.EduStudentChangeExt;

import java.util.List;
import java.util.Map;

public interface EduStdentChangeExtMapper {
    /**
     * 查询学生异动扩展信息
     *
     * @param paramMap
     * @return
     */
    List<EduStudentChangeExt> searchStdentChangeExtsList(Map<String, Object> paramMap);
}
