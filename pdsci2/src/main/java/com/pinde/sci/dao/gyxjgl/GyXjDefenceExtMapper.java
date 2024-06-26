package com.pinde.sci.dao.gyxjgl;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GyXjDefenceExtMapper {
    //查询学员基本信息
    Map<String,Object> queryBaseInfo(@Param("userFlow") String userFlow);
    //查询论文期刊题目
    List<Map<String,Object>> queryPaperTitleList(Map<String, String> params);
    //查询学生对应专业学分要求
    List<Map<String,Object>> searchMajorCredit(String userFlow);
}
