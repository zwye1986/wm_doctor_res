package com.pinde.sci.dao.gyxjgl;

import com.pinde.sci.model.mo.EduLog;
import com.pinde.sci.model.mo.EduUser;
import com.pinde.sci.model.gyxjgl.XjEduCourseMajorExt;
import com.pinde.sci.model.gyxjgl.XjEduGraduateInfoExt;
import com.pinde.sci.model.gyxjgl.XjEduUserExt;
import com.pinde.sci.model.gyxjgl.XjEduUserInfoExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GyXjEduUserExtMapper {

    List<XjEduUserExt> selectList(XjEduUserExt userExt);

    /**
     * 管理员报表--查询人员信息
     *
     * @param paramMap
     * @return
     */
    List<XjEduUserExt> searchEduUserForManage(Map<String, Object> paramMap);

    /**
     * 课程学习情况统计--查询学习该门课的人员信息
     *
     * @param paramMap
     * @return
     */
    List<XjEduUserExt> searchEduUserForCourseDetail(Map<String, Object> paramMap);

    /**
     * 获取用户信息
     *
     * @param userFlow
     * @return
     */
    XjEduUserExt readEduUserInfo(String userFlow);

    /**
     * 获取老师及其所任教的课程信息
     *
     * @param paramMap
     * @return
     */
    List<XjEduUserExt> searchEduAndCourseList(Map<String, Object> paramMap);
    /**
     * 根据流水号查询用户
     * @param paramMap
     * @return
     */
//	List<EduUserExt> searchEduUserList(Map<String,Object> paramMap);


    /**
     * 查询EduUser,resDoctor,SysUser
     *
     * @param paramMap
     * @return
     */
    List<EduUser> searchEduUserBySysUser(Map<String, Object> paramMap);

    /**
     * @param paramMap
     * @return
     */
    List<XjEduUserInfoExt> searchEduUserInfoExtBySysUser(Map<String, Object> paramMap);

    List<XjEduUserInfoExt> searchXjbData(Map<String, Object> paramMap);


    /**
     * 查询和记录表相关联的学生记录
     * @param paramMap
     * @return
     */
//	public List<EduUserExt> selectEduUserStudentCourse(Map<String, Object> paramMap);

    /**
     * 查询学员
     *
     * @param paramMap
     * @return
     */
    List<XjEduUserExt> searchEduUserExtList(Map<String, Object> paramMap);

    /**
     * 根据学员信息查询对应的课程
     *
     * @param paramMap
     * @return
     */
    List<XjEduUserExt> searchEduUserCourseExtMajorList(Map<String, Object> paramMap);

    /**
     * 查询某个学员当前专业和届别的课程信息
     *
     * @param paramMap
     * @return
     */
    List<XjEduCourseMajorExt> searchEduUserCourseList(Map<String, Object> paramMap);
    //查询证明所需要的信息
    List<Map<String,Object>> searchCerInfoLstByUserFlow(@Param(value="userFlow") String userFlow);
    //查询操作日志
    List<EduLog> searchEduLogList(Map<String,Object> paramMap);
    //查询学生工号
    Map<String, Object> queryStuInfo(@Param(value="sid") String sid);
    //学生毕业信息查询
    List<XjEduGraduateInfoExt> searchEduGraduateInfo(Map<String, Object> paramMap);
    //查询广医大人事系统推送导师信息
    List<Map<String,Object>> queryOaTutorList();
    //信息核准与补录
    List<Map<String,Object>> supplementList(Map<String, Object> paramMap);
    //证明管理查询统计
    List<Map<String,Object>> searchPubFileByEduUser(Map<String, Object> paramMap);
}
