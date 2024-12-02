package com.pinde.res.biz.osca;

import com.pinde.core.model.*;
import com.pinde.res.model.osca.mo.OscaCheckInfoExt;

import java.util.List;
import java.util.Map;

public interface IOscaBaseBiz {
    int queryAppointCount(String orgFlow);
    //引导页数据
    List<Map<String,Object>> queryGuideInfoList(String orgFlow);
    //考核信息列表查询
    List<Map<String,Object>> queryDataList(Map<String, Object> map);
    //考核信息单条记录查询
    OscaSkillsAssessment queryDataByFlow(String clinicalFlow);
    //培训专业与考核方案的配置关系查询
    List<OscaSubjectMain> querySpeRelation(String speId, String actionTypeId,String orgFlow);
    //加载基地(考点)下结业考核信息
    List<OscaOrgSpe> queryInitSpe(String orgFlow);
    //考核信息发布
    int releasedInfo(String clinicalFlow);
    //考核信息删除
    int delCheckInfo(String clinicalFlow, String isReleased);
    //查询预约信息记录数(预约成功和预约审核中)
    int queryAppointmentCount(String clinicalFlow);
    //预约学员查询
    List<OscaCheckInfoExt> queryAppointList(Map<String, String> map);
    //预约学员审核
    int auditAppoint(String recordFlow, String auditStatusId, String reason);
    //学员签到
    int changeSign(String recordFlow, String signStatusId);
    //查询某方案下安排的考场
    List<Map<String,Object>> queryRoomList(String clinicalFlow);
    //查询考场单条信息
    OscaSkillRoom queryRoomByFlow(String recordFlow);
    //查询考场下的考官
    List<OscaSkillRoomTea> queryRoomTeaList(String recordFlow);
    //查询方案下设置的考站
    List<OscaSubjectStation> queryStationList(String subjectFlow);
    //查询学员每站考核进度
    List<Map<String,Object>> queryScheduleList(Map<String, String> map);
    //查询学员每站考核进度(屏显)
    List<Map<String,Object>> studentsProcess(Map<String, Object> paramMap);
    //清空/删除考场
    int delRoom(String recordFlow, String clinicalFlow);
    //查询学员每站成绩
    List<Map<String,Object>> queryGradeList(Map<String, String> map);
    //查询考核信息下的审核通过的学员
    List<OscaDoctorAssessment> queryDoctorList(String clinicalFlow);
    //学生能否查看考核成绩操作
    int isShowOpt(String clinicalFlow, String isShow, String flag);
    //查询学员某站成绩
    Map<String,Object> queryDoctorGrade(Map<String, String> param);
    //变更审核结果状态
    int updateIsPass(String clinicalFlow, String doctorFlow, String resultId);
    //查询学员考站不同考官form评分
    List<OscaSkillDocScore> queryDocFormScore(Map<String, String> param);
    //查询form打分成绩
    OscaSkillDocScore queryDocScoreByFlow(String scoreFlow);

    //学生能否查看评分表操作
    int isShowFroom(String clinicalFlow, String isShowFroom);
    //屏显页面内容考试查询
    List<Map<String,Object>> queryRoomDoctorList(Map<String, String> map);
    //屏显页面内容考试查询(新)
    List<Map<String,Object>> queryRoomDoctorList2(Map<String, Object> paramMap);
    //查询某个考核信息下审核通过的学员Flow
    List<String> queryDoctorFlowList(String clinicalFlow, String testNum);
    //查询某个考核信息的考核时间
    List<OscaSkillsAssessmentTime> queryOsaTimeList(String clinicalFlow);
    //通过CLINICAL_FLOW查询考试时间
    List<OscaSkillsAssessmentTime> getAssessmentTimes(String clinicalFlow);
    //通过主键查询OscaSkillAssessmentTime
    OscaSkillsAssessmentTime readAssessmentTime(String recrodFlow);
    //查询某个考核信息的考核时间(字符串拼接)
    Map<String,String> queryOsaTime(String clinicalFlow);
    //查询某个考核信息的考核开始时间
    List<String> queryOsaFirstTime(String clinicalFlow);
    //屏显页面信息
    List<Map<String,Object>> screenInfo(Map<String, Object> paramMap);
    //计算学员考核是否通过
    void updateDoctorAssessment(String clinicalFlow, String doctorFlow, SysUser user);
    //屏显信息设置
    List<Map<String,Object>> screenSelect(Map<String, Object> paramMap);

    OscaExamDifferScore readDiffierScoreByOrgFlow(String orgFlow);

}
