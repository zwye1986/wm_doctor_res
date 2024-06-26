package com.pinde.sci.biz.gzykdx;

import com.pinde.sci.form.gzykdx.GzykdxTeacherExtInfoForm;
import com.pinde.sci.model.mo.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author wangshuai
 * @Copyright njpdxx.com
 * <p/>
 * 此类主要是导师和学员的业务操作
 * @since 2017/3/8
 */
public interface IGzykdxTeaAndDocBiz {

    /**
     * 导师指标申请列表
     * @param teacherTargetApply
     * @return
     */
    List<TeacherTargetApply> selectTargetApplyList(TeacherTargetApply teacherTargetApply);

    /**
     * 修改导师指标申请
     * @param teacherTargetApply
     * @return
     */
    int editTeacherTargetApply(TeacherTargetApply teacherTargetApply);
    int addTeacherTargetApply(TeacherTargetApply teacherTargetApply);

    /**
     * 保存导师信息
     * @param sysUser
     * @param teacherExtInfoForm
     * @return
     */
    int saveTeacherInfo(SysUser sysUser, GzykdxTeacherExtInfoForm teacherExtInfoForm);

    /**
     * 编辑导师指标申请表
     * @param teacherTargetApply
     * @return
     */
//    int editTeacherTargetApply(TeacherTargetApply teacherTargetApply);

    /**
     * 编辑发表论文情况表
     * @param teacherThesisDetail
     * @return
     */
    int editTeacherThesisDetail(TeacherThesisDetail teacherThesisDetail);

    int editThesisDetail(TeacherThesisDetail teacherThesisDetail,PubFile pubFile);

    List<PubFile> selectFileList(String productFlow);

    PubFile readPubFlie(String fileFlow);
    /**
     * 查询所有二级机构
     * @return
     */
    List<SysOrg> searchOrgList();

    List<TeacherThesisDetail> detailList(TeacherThesisDetail teacherThesisDetail);

    List<DoctorTeacherRecruit> searchDoctorTeacherRecruits(DoctorTeacherRecruit recruit);
    List<DoctorTeacherRecruitBatch> searchDoctorTeacherRecruitBatchs(DoctorTeacherRecruitBatch recruitBatch);

    /**
     * 导师招录缺额列表
     * @param apply
     * @return
     */
    List<TeacherTargetApply> searchTeacherNotFull(TeacherTargetApply apply) ;
    List<Map<String,String>> searchTeacherNotFullNew(Map<String,String> map) ;

    int editDoctorTeacherRecruits(DoctorTeacherRecruit recruit);
    int editRecruitsByUserIdNo(DoctorTeacherRecruit recruit);
    int editDoctorTeacherRecruitBatch(DoctorTeacherRecruitBatch recruitBatch);

    /**
     * 缺额二级机构列表
     */
    List<Map<String,String>> notFullOrglist(String orgFlow,String degreeTypeId,String recruitYear);

    /**
     * 考生录取查询（导师）
     * @param map
     * @return
     */
    List<Map<String,Object>> selectDoctorAdmissionList(Map<String,String> map);

    void downGzykdxFile(PubFile file, final HttpServletResponse response) throws Exception;

    int delThesisDetail(List<String> recordFlows);
}
