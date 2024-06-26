package com.pinde.sci.biz.hbres;

import com.pinde.sci.form.hbres.ResDoctorTrainingSpeForm;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorRecruitExt;

import java.util.List;
import java.util.Map;


public interface IHbResDoctorRecruitBiz {
    List<SysDict> searchTrainSpeList(Map<String, Object> paramMap);

    /**
     * 住培注册学员统计表
     *
     * @param paramMap
     * @return
     */
    List<ResDoctorTrainingSpeForm> searchRegisterStatistics(Map<String, Object> paramMap);

//	List<ResDoctorRecruitExt> searchDoctorRecruitExt(
//			Map<String, Object> paramMap);

    int searchDoctorNum(ResDoctorRecruit recruit);

    /**
     * 根据招录主键更新
     *
     * @param recruit
     */
    void modResDoctorRecruitByRecruitFlow(ResDoctorRecruitWithBLOBs recruit, boolean isModAll);

    void noticeRetestBatch(String[] recruitFlows, String retestNotice);

//	String searchNoticeDoctorNum(Map<String, Object> paramMap);

    List<ResDoctorRecruitExt> selectDoctorRecruitExt(
            Map<String, Object> paramMap);

    //int editRecruitUnSelective(ResDoctorRecruit recruit);

//	List<ResDoctorRecruitExt> readDoctorRecruitExt(Map<String, Object> paramMap);

    void noticeRecruit(String admitNotice, String[] recruitFlows, String [] adminSwapFlags);

    /**
     * 省厅调剂
     * @param recruitFlow
     * @param speFlow
     * @param swapNotice
     * @param adminSwapFlag
     */
    void swapRecruit(String recruitFlow, String speFlow, String swapNotice, String adminSwapFlag, String trainYear);

    /**
     * 专业调剂
     *
     * @param recruitFlow
     * @param speFlow
     * @param swapNotice
     */
    void swapRecruit(String recruitFlow, String speFlow, String swapNotice, String trainYear);

    /**
     * 设置学员确认录取过期标志
     *
     * @param year
     * @param doctorFlow
     */
    void setDoctorConfirmFlagForOutOfDate(String year, String doctorFlow);

    /**
     * 学员是否有过确认录取记录
     *
     * @param doctorFlow
     * @return
     */
    boolean doctorIsConfirmAdmit(String doctorFlow);

    List<ResDoctorRecruitExt> searchDoctorRecruitWithUserList(Map<String, Object> paramMap);

    ResDoctorRecruitWithBLOBs docRecruitClobSearch(Map<String, Object> paraMp);

    /**
     * 获取各基地的已招收人数
     * @param paraMap
     * @return
     */
    List<Map<String,Object>> getRecruitDocCount(Map<String, Object> paraMap);

    /**
     * 获取省厅可调剂的学员
     * @param paramMap
     * @return
     */
    List<Map<String,Object>> searchSwapDocList(Map<String, Object> paramMap);

    /**
     * 统计各基地下各专业的计划招收人数
     * @param paramMap
     * @return
     */
    List<Map<String,Object>> getSpeAssignCount(Map<String, Object> paramMap);

    /**
     * 查询省厅调剂学员人数
     * @param paramMap
     * @return
     */
    List<Map<String,Object>> getAdminSwapCount(Map<String, Object> paramMap);

    /**
     * 省厅直接调剂学员
     * @param docRecruit
     * @param msg
     * @return
     */
    int adminSwapRecruit(ResDoctorRecruitWithBLOBs docRecruit, String msg);

     //编辑/新增报到信息
    int editRegister(ResRecruitRegister recruitRegister);
    //根据主键查询报到信息
    ResRecruitRegister readRecruitRegister(String recordFlow);
    //根据条件查询报到信息
    List<ResRecruitRegister> searchRecruitRegister(ResRecruitRegister recruitRegister);
    //根据复合条件查询报到信息
    List<Map<String,String>> searchRegistList(Map<String,Object> paramMap);


    /**
     * 理论成绩查询
     * @return
     */
    List<JsResDoctorRecruitExt>  searchDoctorScoreInfoExts(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser  sysUser, SysOrg  org, List<String>  jointOrgFlowList, String flag , String  scoreYear, String  isHege, List<String> docTypeList, String hegeScore);
    List<JsResDoctorRecruitExt>  searchDoctorSkillAndTheoryScoreExts( ResDoctorRecruit resDoctorRecruit,ResDoctor doctor, SysUser  sysUser,SysOrg  org,List<String>  jointOrgFlowList,  String flag , String  scoreYear, String  isHege,String  skillIsHege,List<String> docTypeList,String hegeScore);
    /**
     * 技能成绩查询
     * @return
     */
    List<JsResDoctorRecruitExt>  searchDoctorSkillScore( ResDoctorRecruit resDoctorRecruit,ResDoctor doctor, SysUser  sysUser,SysOrg  org,List<String>  jointOrgFlowList,  String flag , String  scoreYear, String  isHege,List<String> docTypeList);
}
