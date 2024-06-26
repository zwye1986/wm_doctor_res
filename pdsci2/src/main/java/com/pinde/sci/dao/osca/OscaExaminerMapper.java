package com.pinde.sci.dao.osca;

import com.pinde.sci.model.osca.OscaExaminerExt;

import java.util.List;
import java.util.Map;

public interface OscaExaminerMapper {

    List<OscaExaminerExt> searchAllExam(Map<String, Object> map);

}
