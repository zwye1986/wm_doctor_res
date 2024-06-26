package com.pinde.sci.dao.hbzy;

import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2017/11/29.
 */
public interface HbzyResReductionExtMapper {
    List<Map<String,String>> findReductionExtByMap(Map<String, Object> paramMap);
}
