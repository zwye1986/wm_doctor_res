package com.pinde.core.common.sci.dao;

import com.pinde.core.model.PersonnelChangeReport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonnelChangeReportMapper {
    /**
     * 查询所有国家基地的专业延培人数
     * @param dateMonth
     * @return
     */
    List<PersonnelChangeReport> selectSectionByChangeTime(@Param("dateMonth") String dateMonth,
                                                          @Param("orgFlow")String orgFlow);

    /**
     * 查询专业下年级延培人数
     * @return
     */
    List<PersonnelChangeReport> selectSessionByChangeTime(@Param("dateMonth") String dateMonth,
                                                          @Param("orgFlow")String orgFlow,
                                                          @Param("trainingSpeName") String trainingSpeName);

    /**
     * 查询该年级下所有的协同基地延培人数
     * @return
     */
    List<PersonnelChangeReport> selectOrgNameByChangeTime(@Param("dateMonth") String dateMonth,
                                                          @Param("orgFlow")String orgFlow,
                                                          @Param("trainingSpeName") String trainingSpeName,
                                                          @Param("sessionNumber") String sessionNumber);

    /**
     * 查询所有国家基地的专业延培人数
     * @param dateMonth
     * @return
     */
    List<PersonnelChangeReport> selectSectionBySpeChange(@Param("dateMonth") String dateMonth,
                                                         @Param("orgFlow")String orgFlow);

    /**
     * 查询专业下年级延培人数
     * @return
     */
    List<PersonnelChangeReport> selectSessionBySpeChange(@Param("dateMonth") String dateMonth,
                                                         @Param("orgFlow")String orgFlow,
                                                         @Param("trainingSpeName") String trainingSpeName);

    /**
     * 查询该年级下所有的协同基地延培人数
     * @return
     */
    List<PersonnelChangeReport> selectOrgNameBySpeChange(@Param("dateMonth") String dateMonth,
                                                         @Param("orgFlow")String orgFlow,
                                                         @Param("trainingSpeName") String trainingSpeName,
                                                         @Param("sessionNumber") String sessionNumber);
}
