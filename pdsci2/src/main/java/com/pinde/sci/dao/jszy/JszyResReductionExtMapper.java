package com.pinde.sci.dao.jszy;

import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2017/11/29.
 */
public interface JszyResReductionExtMapper {
    List<Map<String,String>> findReductionExtByMap(Map<String, Object> paramMap);
    List<Map<String,String>> findReductionExtByMapAcc(Map<String, Object> paramMap);
}
