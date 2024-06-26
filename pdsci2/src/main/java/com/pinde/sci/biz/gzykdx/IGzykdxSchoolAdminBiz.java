package com.pinde.sci.biz.gzykdx;

import com.pinde.sci.model.mo.*;

import java.util.List;
import java.util.Map;

public interface IGzykdxSchoolAdminBiz {

    //查询二级机构的管理员用户
    List<SysUser> queryOrgAdminList(String orgFlow);
    //保存二级机构管理员账户
    int saveAffiliatedOrg(SysUser user);
    //重置二级机构管理员账户密码
    int resetOrgAdminPwd(String userFlow);
    //申报权限变更target:机构或者导师
    int updateDeclarePower(String target, String targetFlow, String powerFlag);
    //查询二级机构下导师角色
    List<SysUser>queryTeachersInOrg(String orgFlow, String roleFlow, String userName);
    //保存招录设置
    int saveSysCfg(List<SysCfg> sysCfg);
    //招录指标计划查询
    List<RecruitTargetMain> queryRecruitTargetList(String recruitYear);
    //查询某年招生计划
    RecruitTargetMain queryRecTargetByFlow(String rargetFlow);
    //保存年招生计划
    int saveRecruitPlan(RecruitTargetMain recTarget);
    //查询有权限的二级机构
    List<SysUser> queryOrgAdminPowerList(String orgFlow);
    //查询年招生指标计划下详情
    List<RecruitTargetMainDetail> queryRecDetailByFlow(String rargetFlow);
    //招生指标计划发布操作
    int releasedInfo(String rargetFlow);
    //招生申请审核通过的导师
    List<Map<String,Object>> passedTeacherApplyList(TeacherTargetApply target);
    //发布该年下所有二级机构下的学校审核通过的导师招生指标
    int releasedPassedTarget(String recruitYear);
    //保存二级机构指标分配
    int saveOrgTarget(RecruitTargetMainDetail detail);
    //查询某年某机构某学位类型已占用指标数
    int targetNumOfOrgByYear(String recruitYear, String orgFlow, String degreeTypeId);
    //保存变更二级机构指标
    int saveChangeOrgTarge(RecruitTargetMainDetail detail, RecruitTargetMainDetailLog log);
    //查询某年已分配给二级机构的学术/专业类型总指标
    Map<String, Object> searchUseTargetSum(String rargetFlow);
    //查询指标变更记录
    List<RecruitTargetMainDetailLog> queryDetailLogs();
    //保存日志
    int saveRecruitLog(RecruitLog log);
    //查询日志
    List<Map<String,String>> searchRecruitLogList(String recruitYear,String userName,String startChangeTime,String endChangeTime);
}
