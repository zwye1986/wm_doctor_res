package com.pinde.sci.dao.sczyres;

import java.util.List;
import java.util.Map;

public interface SczyResReductionExtMapper {
    List<Map<String,String>> findReductionExtByMap(Map<String, Object> paramMap);
}
