package com.pinde.sci.biz.gyxjgl;


import com.pinde.sci.model.mo.EduCourse;
import com.pinde.sci.model.mo.EduStudentCourse;
import com.pinde.sci.model.mo.EduUser;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.gyxjgl.XjEduStudentCourseExt;

import java.util.List;
import java.util.Map;

public interface IGyXjEduSecondaryOrgBiz {
    //查询二级机构的管理员用户
    List<SysUser> querySecondaryOrgAdminList(String orgFlow);
    //保存二级机构管理员账户
    int saveSecondaryAdmin(SysUser sysUser);
    //重置二级机构管理员账户密码
    int resetOrgAdminPwd(String userFlow);
    //二级机构-成绩管理
    List<XjEduStudentCourseExt> searchStudentCourse(SysUser sysUser, EduUser eduUser, EduStudentCourse studentCourse);
    //查询二级机构的课程
    List<EduCourse> querySecondaryOrgEduCourse(EduCourse eduCourse);
    //学校/二级机构成绩管理详情
    List<Map<String,Object>> getGradeAuditStus(Map<String,String> parMp);
}
