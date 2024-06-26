package com.pinde.sci.dao.srm;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2016/12/22.
 */
public interface AchScoreExtMapper {

    List<Map<String ,String >> searchByAuthor(Map<String ,String > map);

    List<Map<String ,String >> searchByDept(Map<String ,String > map);

    List<Map<String,String>> searchScoreListByDept(Map<String ,String > map);

    List<Map<String,String>> searchScoreListByAuthor(Map<String ,String > map);

    List<Map<String,String>> searchAchScoreList(Map<String ,String > map);

    int achScoreIsUse(@Param(value = "scoreFlowList") List<String> scoreFlowList);


}
