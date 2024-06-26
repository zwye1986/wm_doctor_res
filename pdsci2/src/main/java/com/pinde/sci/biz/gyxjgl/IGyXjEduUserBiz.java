package com.pinde.sci.biz.gyxjgl;

import com.pinde.sci.form.gyxjgl.*;
import com.pinde.sci.form.gyxjgl.XjRegisterDateForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.gyxjgl.XjEduCourseMajorExt;
import com.pinde.sci.model.gyxjgl.XjEduGraduateInfoExt;
import com.pinde.sci.model.gyxjgl.XjEduUserExt;
import com.pinde.sci.model.gyxjgl.XjEduUserInfoExt;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IGyXjEduUserBiz {
    int saveEduUser(EduUser eduUser);

    /**
     * 获取一条数据
     *
     * @param userFlow
     * @return
     */
    EduUser readEduUser(String userFlow);

    /**
     * 保存用户信息
     *
     * @param sysUser
     * @param eduUser
     * @return
     */
    int saveUserAndEduUser(SysUser sysUser, EduUser eduUser);

    /**
     * 上传头像
     *
     * @param file
     * @return
     */
    String uploadImg(MultipartFile file);

    /**
     * 查询用户
     *
     * @param userExt
     * @return
     */
//    List<XjEduUserExt> searchList(XjEduUserExt userExt);

    /**
     * 根据权限查询用户
     *
     * @param paramMap
     * @return
     */
    List<XjEduUserExt> searchEduUserForManage(Map<String, Object> paramMap);

    /**
     * 查询选择了某一门课的学生的详细信息
     *
     * @param paramMap
     * @return
     */
    List<XjEduUserExt> searchEduUserForCourseDetail(Map<String, Object> paramMap);


    /**
     * 获取某一个人的详细信息
     *
     * @param userFlow
     * @return
     */
    XjEduUserExt readEduUserInfo(String userFlow);

    EduUser findByFlow(String userFlow);

    /**
     * 查询
     *
     * @param eduUser
     * @return
     */
    List<EduUser> search(EduUser eduUser);


    /**
     * 导入更新学籍信息
     */
    int importCourseFromExcel(MultipartFile file);


    /**
     * 通过流水号查询
     * @param userFlowList
     * @return
     */

    /**
     * 查询EduUser,resDoctor,SysUser
     *
     * @param paramMap
     * @return
     */
    List<EduUser> searchEduUserBySysUser(Map<String, Object> paramMap);

    List<XjEduUserInfoExt> searchEduUserInfoExtBySysUser(Map<String, Object> paramMap);

    List<XjEduUserInfoExt> searchXjbData(Map<String, Object> paramMap);

    EduUser findBySid(String sid);

    EduUser findBySidNotSelf(String userFlow, String sid);


    /**
     * 保存学籍注册日期
     *
     * @param registerDateList
     * @param userFlow
     * @return
     * @throws Exception
     */
//    int saveRegisterDate(List<XjRegisterDateForm> registerDateList, String userFlow) throws Exception;

    /**
     * 查询学籍注册日期
     *
     * @param userFlow
     * @return
     * @throws Exception
     */
    List<XjRegisterDateForm> searchRegisterDateList(String userFlow) throws Exception;

    List<ResDoctor> searchDoctorByuserFlow(List<String> userFlows);

    ResDoctor findDoctorByFlow(String doctorFlow);

    /**
     * xml转javaBean
     *
     * @param content
     * @return
     * @throws Exception
     */
    XjEduUserExtInfoForm convertXmlToExtForm(String content) throws Exception;
    /**
     * 包装额外信息xml为form对象
     */
    XjEduUserExtInfoForm parseExtInfoXml(String extInfoXml);

    List<XjEduUserExt> searchEduUserCourseExtMajorList(List<String> userFlowList, EduStudentCourse studentCourse);

    /**
     * 查询学员
     *
     * @param sysUser
     * @param eduUser
     * @param doctor
     * @return
     */
    List<XjEduUserExt> searchEduUserExtList(SysUser sysUser, EduUser eduUser, ResDoctor doctor);

    int addEduUser(EduUser eduUser);
    /**
     * 保存学籍注册日期
     *
     * @param registerDateList
     * @param userFlow
     * @return
     * @throws Exception
     */
    int saveRegisterDate(List<XjRegisterDateForm> registerDateList, String userFlow) throws Exception;


    Map<String, Object> importGradeFromExcel(MultipartFile file,String roleFlag);
    int save(XjEduUserForm form) throws Exception;
    List<XjEduCourseMajorExt> searchEduUserCourseList(String majorId, String period);
    //查询证明所需要的信息
    List<Map<String,Object>> searchCerInfoLstByUserFlow(String userFlow);
    //上传证明照片
    String uploadImg(String userFlow,String certType, MultipartFile file);
    //查询学员信息操作日志
    List<EduLog> searchUserOperLog(String gradeNumber, String startStudyId, String endStudyId, String userName, String changeItem,
                                   String startChangeTime, String endChangeTime);
    //查询学生工号
    Map<String, Object> queryStuInfo(String sid);
    //上传材料证明(图片)
    String uploadMaterialCert(String userFlow, String columnName, MultipartFile file);
    //学籍登记表解析xml
    XjPollingTableForm parsePollingTableXml(String content);
    //学籍登记表保存xml
    int save(String userFlow, XjPollingTableForm form) throws Exception;
    //查找用户某类型内容记录
    PubUserGenericContent readPubUserGenericContent(String userFlow,String contentType);
    //上传头像照片
    String uploadImage(String userFlow, MultipartFile file);

    List<XjEduGraduateInfoExt> searchEduGraduateInfo(Map<String, Object> paramMap);
    //将form对象封装为xml文本
    String getEmployTutorTableXml(XjEmployTutorTableForm form);
    //证明管理-证书查询
    List<PubFile> seachPubFileByFlow(List<String> userFlows);
    //聘请导师申请表保存
    int saveEmployTutorTable(TdxlEmployTutor employTutor);
    //聘请导师申请表解析xml
    XjEmployTutorTableForm parseEmployTutorTableXml(String content);
    //查询聘请导师信息
    List<TdxlEmployTutor> searchEmployTutorList(TdxlEmployTutor et);
    //聘请审核操作
    int auditOption(String roleFlag, String statusId, TdxlEmployTutor et);

    GydxjSupplementInfo readSupplement(String userFlow);

    int saveSuppleInfo(GydxjSupplementInfo supple);

    List<Map<String,Object>> supplementList(Map<String,Object> map);

    List<GydxjInsertGrade> searchInsertGrade(Map<String,Object> map);

    GydxjInsertGrade readInsertGrade(String stuNo);

    List<GydxjInsertDetail> searchListByStuNo(String stuNo);

    int saveInsertGrade(InsertGradeForm form);
    /**
     * 查询EduUser,resDoctor,SysUser,PubFile
     *
     * @param paramMap
     * @return
     */
    List<Map<String,Object>> searchPubFileByEduUser(Map<String, Object> paramMap);

}
