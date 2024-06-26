package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.PersonnelStatisticsByName;

import java.util.List;
import java.util.Map;

public interface ResPersonnelStatisticsBiz {

    List<PersonnelStatisticsByName> selectCollaborativeBaseFlow(String orgName);

    Integer inTheNumOf(Map<String,Object> sessionNum);

    /**
     * 判断该登录角色是否属于协同基地
     * @param orgFlow
     * @return
     */
    boolean isCollaborativelBase(String orgFlow);

    List<PersonnelStatisticsByName> selectSection(String lastDateMonth,String dateMonth,String strDateMonth,String orgFlow);

    List<PersonnelStatisticsByName> selectGrade(String lastDateMonth,String dateMonth,String strDateMonth,String trainingSpeName,String orgFlow);

    List<PersonnelStatisticsByName> selectCollaborativeBase(String lastDateMonth, String dateMonth, String strDateMonth, String trainingSpeName, String sessionNumber, String orgFlow);

}
