package com.pinde.sci.dao.xjgl;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface XjDefenceExtMapper {
    //查询学员基本信息
    Map<String,Object> queryBaseInfo(@Param("userFlow") String userFlow);
    //查询论文期刊题目
    List<Map<String,Object>> queryPaperTitleList(Map<String,String> params);
}
