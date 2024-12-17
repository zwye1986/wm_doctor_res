package com.pinde.sci.biz.res;

import com.pinde.core.model.JsresExamSignup;
import com.pinde.core.model.JsresExamSignupLog;
import com.pinde.core.model.ResDoctorRecruit;
import com.pinde.core.model.ResDoctorRecruitWithBLOBs;
import com.pinde.sci.model.mo.ResScore;

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

    ResDoctorRecruit readResDoctorRecruit(String recruitFlow);


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

    int updateDocrecruit(com.pinde.core.model.ResDoctorRecruit recruit);

    List<com.pinde.core.model.ResDoctorRecruit> findResDoctorRecruits(String year, String doctorFlow);

    List<com.pinde.core.model.ResDoctorRecruit> searchDoctorRecruit(com.pinde.core.model.ResDoctorRecruit recruit);

    List<com.pinde.core.model.ResDoctorRecruit> searchDoctorRecruits(com.pinde.core.model.ResDoctorRecruit recruit);

    /**
     * 查询该学员的所有志愿
     *
     * @param doctorFlow
     * @return
     */
    List<com.pinde.core.model.ResDoctorRecruit> searchRecruitByDoctor(String doctorFlow);

    /**
     * 根据RES_DOCTOR的信息查询最新的学生过审志愿
     */
    ResDoctorRecruit searchRecruitByResDoctor(com.pinde.core.model.ResDoctorRecruit recruit);

    List<Map<String, String>> statisticDoctorCountMap(Map<String, Object> paramMap);

    /**
     * 当前住培情况
     * @param paramMap
     * @return
     */
    List<Map<String,Object>> getCurrDocDetails(Map<String, Object> paramMap);


    ResDoctorRecruit readResDoctorRecruitBySessionNumber(String doctorFlow, String sessionNumber);


    List<JsresExamSignup> getSignUpListByYear(String year, String doctorFlow, String typeId);

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
