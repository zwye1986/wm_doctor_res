package com.pinde.sci.biz.srm;

import com.pinde.sci.model.mo.SrmAchScore;
import com.pinde.sci.model.mo.SrmAchScoreType;

import java.util.List;
import java.util.Map;

public interface IAchScoreBiz {

    /**
     * 查询所有积分设置
     * @return
     */
    List<SrmAchScore> searchScoreSetList(SrmAchScore score);

    SrmAchScore readScoreSet(String scoreFlow);

    List<SrmAchScore> searchScoreListByprarentFlow(List<String> prarentFlowList);

    int delScoreSet(String scoreFlow);

    int delScoreSets(List<String> scoreFlowList);

    int delScoreTypes(List<String> typeFlowList);


    List<SrmAchScoreType> allScoreType();
    /**
     * 保存积分设置
     * @param score
     * @return
     */
    int saveScoreSet(SrmAchScore score);

    SrmAchScoreType readScoreType(String typeFlow);
    /**
     * 保存积分类别
     * @param scoreType
     * @return
     */
    int saveScoreType(SrmAchScoreType scoreType);

    /**
     * 积分汇总个人
     * @param map
     * @return
     */
    List<Map<String ,String >> searchAuthorScoreList(Map<String ,String > map);
    /**
     * 积分汇总科室
     * @param map
     * @return
     */
    List<Map<String ,String >> searchDeptScoreList(Map<String ,String > map);

    List<Map<String ,String >> searchScoreListByDept(Map<String ,String > map);

    List<Map<String ,String >> searchScoreListByAuthor(Map<String ,String > map);

    List<Map<String ,String >> searchAchScoreList(Map<String ,String > map);

    /**
     * 积分项是否被使用
     */
    int achScoreIsUse(List<String> scoreFlowList);

    void updadeScoreUsedAuthor(SrmAchScore achScore,String year,String isPublish);

}
