package com.pinde.sci.biz.jszy;

import com.pinde.core.entyties.SysDict;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.model.jszy.JszyDoctorInfoExt;
import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

//@Service
////@Transactional(rollbackFor = Exception.class)
public interface IJszyResStatisticBiz {
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
    List<Map<String, Object>> statisticDocCountByOrg(ResDoctorRecruit recruit, List<String> orgFlowList);

    /**
     * 统计每家医院使用APP的人数 return time
     */
    List<Map<String, Object>> statisticAppCountByOrg(ResDoctorRecruit recruit, ResRec resRec, String endTime, String startTime, List<String> delTypeList);

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
    List<JszyDoctorInfoExt> statisticNoAppUser(ResDoctorRecruit recruit, ResRec resRec, List<String> delTypeList, String startDate, String endDate);

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

    Map<String,String> statisticDoctorCountMap(Map<String, Object> paramMap);

    List<Map<String,Object>> getCurrDocDetails(Map<String, Object> paramMap);

    List<Map<String,Object>> statisticJointCountByOrg(ResDoctorRecruit recruit, List<String> orgFlowList, String trainTypeId, List<String> docTypeList);
}
