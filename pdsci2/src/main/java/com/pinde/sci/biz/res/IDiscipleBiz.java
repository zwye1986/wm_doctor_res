package com.pinde.sci.biz.res;

import com.pinde.core.model.ResDiscipleNoteInfo;
import com.pinde.core.model.ResDiscipleNoteInfoWithBLOBs;
import com.pinde.sci.model.mo.ResStudentDiscipleTeacher;
import com.pinde.sci.model.res.ResDoctorDiscioleExt;

import java.util.List;
import java.util.Map;

/**
 * 跟师学习管理业务接口
 */
public interface IDiscipleBiz {
    /**
     * 保存学员跟师记录
     */
    void saveStudentDiscipleTeacher(ResStudentDiscipleTeacher studentDiscipleTeacher);

    /**
     * 根据学员信息查询学员跟师记录
     * @return
     */
    boolean searchStudentDiscipleTeacher(String discipleFlow,String doctorFlow);

    /**
     * 查询学习笔记（心得、体会）
     * @param discipleNoteInfo
     * @return
     */
    List<ResDiscipleNoteInfo> findResDiscipleNoteInfo(ResDiscipleNoteInfo discipleNoteInfo,List<String> auditStatusList);

    ResDiscipleNoteInfoWithBLOBs findResDiscipleNoteInfoWithBLOBs(String recordFlow);

    int updateResDiscipleNoteInfoWithBLOBs(ResDiscipleNoteInfoWithBLOBs discipleNoteInfo);

    int delResDiscipleNoteInfo(String recordFlow);

    List<ResDoctorDiscioleExt> searchAuditDoctorList(Map<String,Object> map);

    /**
     * 一键审核
     * @param paramMap
     * @return
     */
    int agreeRecordBatch(Map<String, String> paramMap);

    /**
     * 跟师数据一键审核 (机构)
     * @return
     */
    int oneKeyAuditByOrg(String orgFlow ,String userFlow);
}
