package com.pinde.sci.biz.res;

import com.pinde.core.model.SysDict;
import com.pinde.sci.model.mo.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/14 0014.
 */
public interface IResDoctorRecruitBiz {
    /**
     * 查询该学员最新一条审核通过的志愿
     *
     * @param doctorFlow
     * @return
     */
    ResDoctorRecruit getNewRecruit(String doctorFlow);
    ResDoctorRecruit getNewRecruitByDoctorFlow(String doctorFlow);

    ResDoctorRecruit readResDoctorRecruit(String recruitFlow);

    ResDoctorRecruitWithBLOBs readResDoctorRecruitWithBLOBs(String recruitFlow);

    /**
     * 设置复试通知
     *
     * @param recruitFlow
     * @param retestNotice
     */
    void noticeRetest(String recruitFlow, String retestNotice);
    //撤销复试/录取通知
    void noticeBackOpt(String recruitFlow, String flag);

    int editDoctorRecruit(ResDoctorRecruitWithBLOBs recruit);

    int updateDocrecruit(ResDoctorRecruit recruit);
    //选择性变更字段
    int updateDocrecruitByFlow(ResDoctorRecruitWithBLOBs recruit);

    List<ResDoctorRecruit> findResDoctorRecruits(String year, String doctorFlow);

    List<ResDoctorRecruit> searchDoctorRecruit(ResDoctorRecruit recruit);

    //查询所有线上招录的记录
    List<ResDoctorRecruit> searchDoctorRecruitWithLine(Map<String,Object> paramMap);

    List<ResDoctorRecruit> searchDoctorRecruits(ResDoctorRecruit recruit);

    /**
     * 查询该学员的所有志愿
     *
     * @param doctorFlow
     * @return
     */
    List<ResDoctorRecruit> searchRecruitByDoctor(String doctorFlow);

    /**
     * 根据RES_DOCTOR的信息查询最新的学生过审志愿
     */
    ResDoctorRecruit searchRecruitByResDoctor(ResDoctorRecruit recruit);

    List<Map<String, String>> statisticDoctorCountMap(Map<String, Object> paramMap);

    /**
     * 当前住培情况
     * @param paramMap
     * @return
     */
    List<Map<String,Object>> getCurrDocDetails(Map<String, Object> paramMap);

    List<Map<String,Object>> getcheckDocs(Map<String, Object> paramMap);

    List<ResDoctor> searchDoctorByJd(Map<String, Object> paramMap);

    List<ResDoctor> searchDoctorBySpe(Map<String, Object> paramMap);

    void exportForHbGlobal(List<Map<String, Object>> jdDoctorMaps, HttpServletResponse response, List<SysDict> doctorTypeDicts, String flag)throws IOException;
    //住培对外开放后医师已有培训基地后，查询录取是否已录取
    boolean queryIsConfirmFlag(String doctorFlow);
    //湖北招录统计
    Map<String,Object> queryPlanStatistics(String recruityYear);
    //湖北各基地招录统计
    List<Map<String,Object>> queryOrgStatistics(String recruityYear);
    //湖北招录统计(往年)
    Map<String,Object> queryPlanStatisticsBefore(String recruityYear);
    //湖北各基地招录统计(往年)
    List<Map<String,Object>> queryOrgStatisticsBefore(String recruityYear);

    ResDoctorRecruit readResDoctorRecruitBySessionNumber(String doctorFlow, String sessionNumber);

    JsresExamSignup getSignUpByYear(String year, String doctorFlow, String typeId);

    List<JsresExamSignup> getSignUpListByYear(String year, String doctorFlow, String typeId);
    //获取该医师某一年份的所有补考记录
    List<JsresExamSignup> getSignUpListByYear(String year, String doctorFlow);

    List<JsresExamSignup> readDoctorExanSignUps(String doctorFlow, String typeId);

    int saveSignUp(JsresExamSignup signup);

    List<Map<String,Object>> queryExamSignUpList(Map<String, Object> param);

    JsresExamSignup readExamSignByFlow(String signupFlow);
    //获取该医师的所有补考记录
    List<JsresExamSignup> readDoctorExanSignUps(String doctorFlow);
    //获取所有的成绩记录
    List<ResScore> selectAllScore( String userFlow,String recruitFlow);

    JsresExamSignup readByFlow(String signupFlow);

    //保存补考审核记录
    int saveChargeAudit(JsresExamSignup signup, JsresExamSignupLog log);
    //获取补考审核的详细记录
    List<JsresExamSignupLog> getAuditLogsBySignupFlow(String signupFlow);
    //批量保存补考审核记录
    int saveBatchAudit(List<JsresExamSignup> signups, List<JsresExamSignupLog> logs);
}
