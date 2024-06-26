package com.pinde.sci.dao.xjgl;

import java.util.List;
import java.util.Map;

/**
 * @Copyright njpdxx.com
 * @since 2018/6/28
 */
public interface XjStuFeeExtMapper {
    //根据学号查询收费系统中学生缴费信息(总缴费信息)
    Map<String,Object> queryStuAllFeeInfo(Map<String, Object> paramMap);
    //根据学号查询收费系统中学生缴费信息(年度缴费信息)
    List<Map<String,Object>> queryStuFeeInfo(Map<String, Object> paramMap);
}
