package com.pinde.sci.dao.jsres;

import com.pinde.core.model.*;
import com.pinde.core.model.AppUseInfoPojoParam;
import com.pinde.core.model.DoctorExamStatisticsExt;
import com.pinde.core.model.DoctorLunZhuanDataMonthReport;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SchdualTaskMapper {
    /*以下基地app使用情况统计月报*/
    int insertLocalApp(List<AppUseInfoPojoParam> list);

    int deleteAllPreviousMonthData(@Param("monthDate")String monthDate);

    List<AppUseInfoPojoParam> selectAppUserinfo(@Param("monthDate")String monthDate,@Param("orgFlow")String orgFlow,@Param("isContain")String isContain);

    /*以下基地轮转学员数据月报*/
    int insertLocalDoctorLunzhuanData(List<DoctorLunZhuanDataMonthReport> list);

    int deleteAllPreviousMonthDataByDoctorLunZhuanData(@Param("monthDate")String monthDate);

    List<DoctorLunZhuanDataMonthReport> selectDoctorLunZhuanData(@Param("monthDate")String monthDate,
                                @Param("orgFlow")String orgFlow,@Param("isContain")String isContain,
                                @Param("notGraduate")String notGraduate,@Param("graduate")String graduate);

    /*以下省市高校app使用情况统计月报*/
    int insertPCUApp(List<SysOrg> list);
    int deletePreviousMonthPCUData(@Param("monthDate")String monthDate);
    List<SysOrg> selectPCUAppInfo(Map<String,Object> map);
    /*以下省市高校教学活动情况统计月报*/
    int insertPCUTeachActive(List<TeachActiveParamPO> list);
    int deletepreviousMonthDataPCUTeachActive(@Param("monthDate")String monthDate);
    List<TeachActiveParamPO> selectPCUTeachActive (Map<String,Object> map);

    /*以下省市高校学员出科情况统计月报*/
    int insertPCUDoctorOutOffice(List<DoctorOutOfficeParamPO> list);
    int deletePreviousCurrMonthDoctorOutOffice(@Param("monthDate")String monthDate);

    List<DoctorOutOfficeParamPO>selectPCUDoctorOutOffice(Map<String,Object> map);
    /*以下省市高校学员轮转查询*/
    int insertPCUDoctorLunzhuanFind(List<SysOrg> list);
    int deletePreviousCurrMonthDoctorLunzhuanFind(@Param("monthDate")String monthDate);
    List<SysOrg> selectPCUDoctorLunzhuanFind(Map<String,Object> map);

    int deleteAllData(@Param("monthDate")String monthDate);

    int insertOrgFlowMonthData(@Param("monthDate") String monthDate,@Param("createTime") String createTime);

    int insertOrgFlowAndJointMonthData(@Param("monthDate") String monthDate,@Param("createTime") String createTime);

    int insertJointMonthData(@Param("monthDate") String monthDate,@Param("createTime") String createTime);

    List<JsresAppInfo> searchJointData(Map<String,Object> param);

    List<JsresAppInfo> searchLocalData(Map<String,Object> param);

    List<JsresAppInfo> searchAllData(Map<String, Object> paramMap);

    int deleteLunzhuanAllData(@Param("monthDate") String monthDate);

    int insertLunzhuanAllData(@Param("monthDate") String monthDate, @Param("createTime") String createTime,
                              @Param("startDate") String startDate, @Param("endDate") String endDate);

    int insertLunzhuanOrgDoctorAllData(@Param("monthDate") String monthDate, @Param("createTime") String createTime,
                                 @Param("startDate") String startDate, @Param("endDate") String endDate);

    int insertLunzhuanOrgGraduateAllData(@Param("monthDate") String monthDate, @Param("createTime") String createTime,
                                         @Param("startDate") String startDate, @Param("endDate") String endDate);

    int insertLunzhuanJointOrgAllData(@Param("monthDate") String monthDate, @Param("createTime") String createTime,
                                      @Param("startDate") String startDate, @Param("endDate") String endDate);

    int insertLunzhuanJointOrgDoctorAllData(@Param("monthDate") String monthDate, @Param("createTime") String createTime,
                                            @Param("startDate") String startDate, @Param("endDate") String endDate);

    int insertLunzhuanJointOrgGraduateAllData(@Param("monthDate") String monthDate, @Param("createTime") String createTime,
                                              @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<JsresSchDataStatistics> selectLunzhuanData(Map<String, Object> paramMap);

    List<JsresSchDataStatistics> selectOrgLunzhuanData(Map<String, Object> paramMap);

    List<JsresActivityStatistics> selectAllActivityData(@Param("monthDate") String monthDate);

    int deleteAllActivityData(@Param("monthDate") String monthDate);

    int insertOrgActivityData(@Param("monthDate") String monthDate, @Param("createTime") String createTime,
                              @Param("startDate") String startDate, @Param("endDate") String endDate);

    int insertOrgActivityDoctorData(@Param("monthDate") String monthDate, @Param("createTime") String createTime,
                                    @Param("startDate") String startDate, @Param("endDate") String endDate);

    int insertOrgActivityGraduateData(@Param("monthDate") String monthDate, @Param("createTime") String createTime,
                                      @Param("startDate") String startDate, @Param("endDate") String endDate);

    int insertJointOrgActivityData(@Param("monthDate") String monthDate, @Param("createTime") String createTime,
                                   @Param("startDate") String startDate, @Param("endDate") String endDate);

    int insertJointOrgActivityDoctorData(@Param("monthDate") String monthDate, @Param("createTime") String createTime,
                                         @Param("startDate") String startDate, @Param("endDate") String endDate);

    int insertJointOrgActivityGraduateData(@Param("monthDate") String monthDate, @Param("createTime") String createTime,
                                           @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<JsresActivityStatistics> searchLocalActivityData(Map<String, Object> param);

    int deleteAllOutDeptData(@Param("monthDate") String monthDate);

    int insertOrgOutDeptData(@Param("monthDate") String monthDate, @Param("createTime") String createTime,
                             @Param("startDate") String startDate, @Param("endDate") String endDate);

    int insertOrgOutDeptDoctorData(@Param("monthDate") String monthDate, @Param("createTime") String createTime,
                                   @Param("startDate") String startDate, @Param("endDate") String endDate);

    int insertOrgOutDeptGraduateData(@Param("monthDate") String monthDate, @Param("createTime") String createTime,
                                     @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<JsresOutDeptStatistics> searchOutDeptDate(Map<String, Object> param);

    List<Map<String,String>> selectDoctorOutDeptData(Map<String, Object> param);

    List<DoctorExamStatisticsExt> searchExamStatisticsList(Map<String, Object> param);

    List<DoctorExamStatisticsExt> searchExamStatisticsList2(Map<String, Object> param);

    List<DoctorExamStatisticsExt> searchExamStatisticsListBySpe(Map<String, Object> param);

    List<DoctorExamStatisticsExt> searchExamStatisticsListByCity(Map<String, Object> param);

    List<DoctorExamStatisticsExt> searchExamStatisticsListByCity2(Map<String, Object> param);

    int insertMonthStaticData(@Param("monthDate") String monthDate, @Param("createTime") String createTime);

    int insertMonthStaticData2(@Param("monthDate") String monthDate,@Param("monthDate2") String monthDate2, @Param("createTime") String createTime);

    List<Map<String, Object>> searchDoctorRecruit(Map<String, Object> params);

    List<Map<String, Object>> searchJointOrgDoctorRecruit(Map<String, Object> params);
}
