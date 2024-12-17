package com.pinde.sci.biz.res;

import com.pinde.core.model.ResDiscipleRecordInfo;
import com.pinde.core.model.ResStudentDiscipleTeacher;
import com.pinde.sci.model.res.ResDoctorExt;

import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2016/10/13.
 */
public interface IResFolowTeacherRecordBiz {

    /**
     * 根据老师FLow或学员Flow查询学员跟师表
     * @param resStudentDiscipleTeacher
     * @return
     */
    List<ResStudentDiscipleTeacher> searchResStudentDiscipleTeacher(ResStudentDiscipleTeacher resStudentDiscipleTeacher);

    /**
     *保存或修改跟师记录
     * @param resDiscipleRecordInfo
     */
    int saveResDiscipleRecordInfo(ResDiscipleRecordInfo resDiscipleRecordInfo);

    /**
     *根据条件查询跟师记录表
     * @param resDiscipleRecordInfo
     * @return
     */
    List<ResDiscipleRecordInfo> searchFolowTeacherRecord(ResDiscipleRecordInfo resDiscipleRecordInfo);

    /**
     * 查询有待审核的记录
     * @return
     */
    List<ResDoctorExt> searchDisciplePendingAudit(Map<String,Object> map);

    /**
     * 批量通过
     * @param map
     */
    int batchAgreeResDiscipleRecordInfo(Map<String,String> map);
}
