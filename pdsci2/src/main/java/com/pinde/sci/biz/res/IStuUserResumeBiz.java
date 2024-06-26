package com.pinde.sci.biz.res;

import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.form.zseyjxres.SpeForm;
import com.pinde.sci.model.mo.StuAuditProcess;
import com.pinde.sci.model.mo.StuHeadAuditStatus;
import com.pinde.sci.model.mo.StuUserResume;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.res.StuUserExt;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IStuUserResumeBiz {

    List<StuHeadAuditStatus> getStuHeadAudit(Map<String,Object> mp);

    List<StuHeadAuditStatus> getStuHeadAudits(Map<String,String> mp);

    //根据用户流水号及报名批次查询相关信息
    List<StuUserResume> getStuUserLst(String userFlow,String batchFlow);

    //审核信息查询
    List<StuUserExt> searchStuUser(Map<String,Object> mp);

    List<StuUserExt> searchStuUserByrosteringHand(Map<String,Object> mp);



    //结业查询 因为涉及到 境外学员不需要上传结业鉴定表

    List<StuUserExt> searchStuUserByForeign(Map<String,Object> mp);


    //查询中英文学生信息
    List<StuUserExt> searchStuUserAll(Map<String,Object> mp);


    //科室审核查询
    List<StuUserExt> searchStuUserByHead(Map<String,Object> mp);

    List<StuUserExt> searchStuUserAndDoc(Map<String,Object> mp);

    //录取信息查询
    StuUserExt searchAdmitedInfo(String resumeFlow);

    //根据stu_User_Resume表key查询记录
    StuUserResume getStuUserByKey(String resumeFlow);
    //保存登记老师
    void saveRegisterTeacher(String resumeFlow,String teacherName);
    //保存结业信息
    void saveGraduation(Map<String, Object> map, MultipartFile file, String isSctcm);
    //查询所有已提交报名批次信息
    List<StuUserResume> getPassingBatchLst(String userFlow,String statusId);

    int save(StuUserResume stuUser);

    int saveResumeAndHead(StuUserResume stuUser,Map<String, String> mp,List<SpeForm> speFormList,List<StuHeadAuditStatus> statuses);

    List<SysUser> getTeas(Map<String,String> paramMap);

    void saveStuFile(MultipartFile file, String productFlow);

    List<StuUserExt> queryStuList(Map<String, Object> parMp);

    List<Map<String,Object>> queryStuListForExport(Map<String, Object> parMp);

    List<StuUserExt> teaQueryStuList(Map<String, Object> parMp);
    ExcelUtile importStaffFromExcel(MultipartFile file);

    List<StuAuditProcess> searchProcessByResumeFlow(String resumeFlow);
    int updateSpeNameById(String dictId, String dictName);

    int updateUserDeptNameById(String dictId, String dictName);

    int updateRegistDeptNameById(String dictId, String dictName);

    /**
     * 用于查询在某些状态下的学员记录
     * @param userFlow
     * @param paramList
     * @return
     */
    List<StuUserResume> getShowGraduationLst(String userFlow, List<String> paramList);

    /**
     * 用于查询在某些状态下的学员记录
     * @param userFlow
     * @param statues
     * @return
     */
    List<StuUserResume> getPassStuUserLst(String userFlow, List<String> statues);

    StuUserResume searchStuUserResume(String resumeFlow);

    /**
     * 保存 报到时间和更改报到状态
     * @param statusId
     * @param userResume
     * @return
     */
    int saveDateAndStatus(String statusId,StuUserResume userResume);

    List<StuUserExt> searchStuNurseUser(Map<String, Object> mp);

    List<StuUserExt> searchStuUser2(Map<String, Object> mp);

    List<StuUserExt> searchUser(Map<String, Object> mp);

    List<StuUserExt> queryNurseList(Map<String, Object> parMp);

    List<Map<String, Object>> queryNurseListForExport(Map<String, Object> parMp);
}
