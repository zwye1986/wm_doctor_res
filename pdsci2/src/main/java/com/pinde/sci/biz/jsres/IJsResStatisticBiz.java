package com.pinde.sci.biz.jsres;

import com.pinde.core.model.SysDict;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.model.jsres.JsDoctorInfoExt;
import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface IJsResStatisticBiz {
    /**
     * 统计国家基地的数量
     *
     * @param org
     * @return
     */
    int statisticCountyOrgCount(SysOrg org);

    /**
     * 统计某个城市的医师数
     *
     * @param recruit
     * @param cityIdList
     * @return
     */
    int statisticDoctorCount(ResDoctorRecruit recruit, List<String> cityIdList);

    /**
     * 统计协同基地的数量
     *
     * @param sysOrg
     * @return
     */
    int statisticJointOrgCount(SysOrg sysOrg);

    /**
     * 统计本年度的医师数量
     *
     * @param doctor
     * @param sysOrgs
     * @return
     */
    int statisticYearCondocCount(ResDoctor doctor, List<SysOrg> sysOrgs);

    /**
     * 统计每个国家基地的总数和协同基地的总数
     */
    List<Map<String, Object>> statisticDocCouByType(ResDoctorRecruit recruit, List<String> orgFlowList, ResDoctor doctor, List<String> docTypeList);

    /**
     * 统计每家基地的协同基地的每个专业的培训记录数
     */
    List<Map<String, Object>> statisticJointCount(ResDoctorRecruit recruit, List<String> orgFlowList, ResDoctor doctor, List<String> dicTypeList);

    /**
     * 导出excel
     *
     * @throws IOException
     */
    void export(List<SysOrg> sysOrgList, List<SysDict> typeSpeList, String trainTypeId, Map<Object, Object> totalCountMap, Map<Object, Object> zongjiMap, Map<Object, Object> orgSpeFlagMap, Map<Object, Object> joingCountMap, HttpServletResponse response) throws IOException;

    /**
     * 计算每家医院的的人数
     */
    List<Map<String, Object>> statisticDocCountByOrg(ResDoctorRecruit recruit, List<String> orgFlowList,String graduate);

    /**
     * 统计每家医院使用APP的人数 return time
     */
    List<Map<String, Object>> statisticAppCountByOrg(ResDoctorRecruit recruit, ResRec resRec, String endTime, String startTime, List<String> delTypeList,String graduate);

    /**
     * 统计每家医院使用APP的人数 return orgFlow
     */
    List<Map<String, Object>> statisticRealAppCount(List<String> delTypeList, ResDoctorRecruit recruit, String endTime, String startTime, List<String> orgFlowList, String jointFlag, String month);

    /**
     * 计算每家医院的的人数 for time
     */
    List<Map<String, Object>> statisticDocCountByOrgForTime(String jointFlag, List<String> orgFlowList, String startTime, String endTime, ResDoctorRecruit recruit);

    /**
     * 统计登录的人数详细信息
     */
    List<JsDoctorInfoExt> statisticNoAppUser(ResDoctorRecruit recruit, ResRec resRec, List<String> delTypeList, String startDate, String endDate,String graduate);

    /**
     * 导入医师信息
     */
    ExcelUtile importTeacherExcel(MultipartFile file);

    /**
     * 保存和更新resTeacher表
     */
    int save(ResTeacherTraining teacherTraining);

    /**
     * 查询resTeacherInfo
     */
    List<ResTeacherTraining> searchTeacherInfo(ResTeacherTraining resTeacherTraining);

    ResTeacherTraining searchTeacherInfoByPK(String recordFlow);

    int editTeacherInfo(ResTeacherTraining teacherTraining);

    Map<String,String> statisticDoctorCountMap(Map paramMap);

    /**
     * 当前住培情况
     * @param paramMap
     * @return
     */
    List<Map<String,Object>> getCurrDocDetails(Map<String, Object> paramMap);

    Map<String,Object> sumCountAudit(List<String> orgFlows);

    Map<String,Object> sumCountAuditRes(List<String> orgFlows);

    /**
     *查询各基地住院医师总人数（不含在校专硕，含协同医院住院医师人数）
     * @param recruit
     * @param orgFlowList
     * @param trainTypeId
     * @param docTypeList
     * @return
     */
    List<Map<String,Object>> statisticJointCountByOrg(ResDoctorRecruit recruit, List<String> orgFlowList, String trainTypeId, List<String> docTypeList);

    List<Map<String,String>> findCountryDocCount(String sessionNumber);

    List<Map<String,String>> findProDocCount(String sessionNumber);
    List<Map<String,String>> findCountrySpeDocCount(String sessionNumber);

    List<Map<String,String>> findProSpeDocCount(String sessionNumber);

    List<Map<String,String>> findDocTypeCount(String sessionNumber);
    List<Map<String,String>> findOrgTypeCount(String sessionNumber);
    List<Map<String,String>> findCityDocCount(String sessionNumber);
    List<Map<String,String>> findOrgAssiCount(String sessionNumber);
    List<Map<String,String>> findSpeGraduateCount(String sessionNumber);
    List<Map<String,String>> findOrgGraduateCount(String sessionNumber);

    Map<String,String> statisticDoctorCountMapForUni(Map<String, Object> paramMap);

    List<Map<String,Object>> getCurrDocDetailsForUni(Map<String, Object> paramMap);

    List<Map<String,String>> doctorNumForUni1(ResDoctorRecruit recruit, List<String> orgFlows,SysOrg org);
    List<Map<String,String>> doctorNumForUni2(ResDoctorRecruit recruit, List<String> orgFlows,SysOrg org);

    List<Map<String,String>> doctorNumForUni1DaoChu(ResDoctorRecruit recruit, List<String> orgFlows, SysOrg sysorg);
    List<Map<String,String>> doctorNumForUni2DaoChu(ResDoctorRecruit recruit, List<String> orgFlows, SysOrg sysorg);

    List<SysOrg> getRotationData(Map<String,Object> paramMap);

    //人员统计数据
    List<PersonStaticExample> getPersonStaticData(Map<String,Object> paramMap);
    //人员统计数据new
    List<PersonStaticExample> getPersonStaticDataNewyuh(Map<String,Object> paramMap);
    Integer residentRecruitCount(Map<String,Object> paramMap);
    Integer inSchoolRecruitCount(Map<String,Object> paramMap);

    String saveFileToDirs(String oldFolderName, MultipartFile uploadFile, String teacherTrain);

    int deleteCerfiticateImg(String recordFlow);

    int deleteAchievementImg(String recordFlow);

    int importTeacherExcelNew(MultipartFile file,String roleFlag);

    void downImg(ResTeacherTraining training, HttpServletResponse response) throws Exception;

    ResTeacherTraining searchTeacherInfoByCertificateNoNotSelf(String certificateNo, String doctorName);

    List<ResTeacherTraining> searchTeacherInfo2(ResTeacherTraining teacherTraining, String dataFlag);

    List<ResTeacherTraining> searchTeacherInfo3(ResTeacherTraining teacherTraining, String dataFlag);

    List<ResTeacherTraining> searchTeacherInfoByCharge(ResTeacherTraining teacherTraining, String dataFlag, List<String> orgFlows);

    List<ResTeacherTraining> searchTeacherInfoByCharge2(ResTeacherTraining teacherTraining, String dataFlag, List<String> orgNames);

    List<Map<String, Object>> searchDoctorData(Map<String, Object> param);

//    void autoAddData();

    List<Map<String, Object>> searchDoctorDataNew(Map<String, Object> param);

    List<Map<String, Object>> searchDoctorDataNew2(Map<String, Object> param);

    List<Map<String, Object>> searchTeacherUserList(Map<String, Object> param);

    List<Map<String, Object>> searchTeacherAuditList(Map<String, Object> param);
}
