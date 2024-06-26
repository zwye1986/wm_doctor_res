package com.pinde.sci.dao.gyxjgl;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GyXjEmployTutorExtMapper {
    //查询论文期刊题目
    List<Map<String,String>> queryEmployTutorList(Map<String, String> params);
}
