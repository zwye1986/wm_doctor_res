package com.pinde.sci.dao.xjgl;

import java.util.List;
import java.util.Map;

/**
 * @Copyright njpdxx.com
 * @since 2018/3/9
 */
public interface XjCourseQuestionExtMapper {
    List<Map<String,String>> queryQuestionList(Map<String,String> map);
    List<Map<String,String>> searchStudentCourseInfos(Map<String,String> map);
    //评价记录
    List<Map<String,String>> queryEvaluateDetails(Map<String,String> map);
}
