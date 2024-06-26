package com.pinde.sci.dao.xjgl;


import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.xjgl.XjEduStudentCourseExt;

import java.util.List;
import java.util.Map;

public interface XjEduSecondaryOrgExtMapper {
    //学籍二级单位管理员
    List<SysUser> queryUserList(Map<String, String> paramMap);
    //二级单位查询 已选所属课程的（学校管理员未录入成绩的）学生课程成绩
    List<XjEduStudentCourseExt> queryStudentCourseBySecondaryOrg(Map<String, Object> paramMap);
    //学校/二级单位成绩管理详情
    List<Map<String,Object>> getGradeAuditStus(Map<String,String> parMp);
}
