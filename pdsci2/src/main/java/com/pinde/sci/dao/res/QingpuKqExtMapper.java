package com.pinde.sci.dao.res;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2016-10-18.
 */
public interface QingpuKqExtMapper {
    List<Map<String,Object>> getKqStatistics(Map<String,Object> paramMap);

    List<Map<String,String>> getSigninList(Map<String, Object> param);
}
