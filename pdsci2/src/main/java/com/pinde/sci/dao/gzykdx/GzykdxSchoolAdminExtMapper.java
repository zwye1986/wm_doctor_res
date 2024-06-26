package com.pinde.sci.dao.gzykdx;

import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.TeacherTargetApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GzykdxSchoolAdminExtMapper {

    //查询二级机构下导师角色
    List<SysUser> queryTeachersInOrg(@Param("orgFlow")String orgFlow, @Param("roleFlow")String roleFlow, @Param("userName")String userName);
    //二级机构申报权限控制着所有导师申报权限
    int updateTeachersInOrg(@Param("orgFlow")String orgFlow, @Param("powerFlag")String powerFlag, @Param("roleFlow")String roleFlow);
    //招生申请审核通过的导师
    List<Map<String,Object>> queryPassedTeacherApply(TeacherTargetApply target);
    //发布该年下所有二级机构下的学校审核通过的导师招生指标
    int releasedPassedTarget(String recruitYear);
    //查询某年某机构某学位类型已占用指标数
    String targetNumOfOrgByYear(@Param("recruitYear")String recruitYear, @Param("orgFlow")String orgFlow, @Param("degreeTypeId")String degreeTypeId);
    //查询某年已分配给二级机构的学术/专业类型总指标
    Map<String, Object> targetNumByYear(@Param("rargetFlow")String rargetFlow);
    //查询招生目录修改日志
    List<Map<String,String>> queryRecruitLogList(Map<String,String> map);
    //查询二级机构招生指标信息
    List<Map<String,Object>> queryOrgRecruitInfo(@Param("recruitYear") String recruitYear);
    //查询学校管理员手机号
    List<Map<String,String>> querySchoolPhones(@Param("roleFlow") String roleFlow);
}
