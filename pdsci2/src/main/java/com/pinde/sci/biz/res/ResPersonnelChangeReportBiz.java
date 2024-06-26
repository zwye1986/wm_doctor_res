package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.PersonnelChangeReport;

import java.util.List;

public interface ResPersonnelChangeReportBiz {
    /**
     * 查询基地各个专业延培的人数
     * @param monthDate
     * @param orgFlow
     * @return
     */
    List<PersonnelChangeReport> selectSectionByChangeTime(String monthDate,String orgFlow);

    /**
     * 查询专业各个年级延培的人数
     * @param monthDate
     * @param orgFlow
     * @return
     */
    List<PersonnelChangeReport> selectSessionByChangeTime(String monthDate,String orgFlow,String trainingSpeName);

    /**
     * 查询基地下专业下年级对应各个协同基地延培的人数
     * @param monthDate
     * @param orgFlow
     * @return
     */
    List<PersonnelChangeReport> selectOrgNameByChangeTime(String monthDate,String orgFlow,String trainingSpeName,String sessionNumber);

    /**
     * 查询基地各个专业变更基地的人数
     * @param monthDate
     * @param orgFlow
     * @return
     */
    List<PersonnelChangeReport> selectSectionBySpeChange(String monthDate,String orgFlow);

    /**
     * 查询专业各个年级变更基地的人数
     * @param monthDate
     * @param orgFlow
     * @return
     */
    List<PersonnelChangeReport> selectSessionBySpeChange(String monthDate,String orgFlow,String trainingSpeName);

    /**
     * 查询基地下专业下年级对应各个协同基地变更基地的人数
     * @param monthDate
     * @param orgFlow
     * @return
     */
    List<PersonnelChangeReport> selectOrgNameBySpeChange(String monthDate,String orgFlow,String trainingSpeName,String sessionNumber);
}
