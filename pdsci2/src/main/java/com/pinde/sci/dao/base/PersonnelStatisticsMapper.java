package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PersonnelStatisticsByName;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PersonnelStatisticsMapper {
    /**
     * 在培人数
     * @param sessionNum
     * @return
     */
    Integer inTheNumOf(Map<String,Object> sessionNum);

    /**
     * 查询所有国家基地的科室人数
     * @param lastDateMonth
     * @param dateMonth
     * @param strDateMonth
     * @return
     */
    List<PersonnelStatisticsByName> selectSection(@Param("lastDateMonth") String lastDateMonth,
                                            @Param("dateMonth") String dateMonth,
                                            @Param("strDateMonth") String strDateMonth,
                                            @Param("orgFlow")String orgFlow);

    /**
     * 查询科室下年级
     * @return
     */
    List<PersonnelStatisticsByName> selectGrade(@Param("lastDateMonth") String lastDateMonth,
                                                @Param("dateMonth") String dateMonth,
                                                @Param("strDateMonth") String strDateMonth,
                                                @Param("trainingSpeName") String trainingSpeName,
                                                @Param("orgFlow")String orgFlow);

    /**
     * 查询该年级下所有的协同基地人数
     * @return
     */
    List<PersonnelStatisticsByName> selectCollaborativeBase(@Param("lastDateMonth") String lastDateMonth,
                                                      @Param("dateMonth") String dateMonth,
                                                      @Param("strDateMonth") String strDateMonth,
                                                      @Param("trainingSpeName") String trainingSpeName,
                                                      @Param("sessionNumber") String sessionNumber,
                                                      @Param("orgFlow")String orgFlow);

    /**
     * 获取到协同基地的orgFlow
     * @param orgName
     * @return
     */
    String selectCollaborativeBaseFlow(String orgName);
}
