package com.pinde.sci.dao.common;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommonQueryMapper {
   List<Map<String,Object>> commonQuery(@Param(value="commonSql")String commonSql);
}