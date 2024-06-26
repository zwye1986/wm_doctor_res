package com.pinde.sci.biz.gyxjgl.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gyxjgl.IGyXjEduSecondaryOrgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.EduCourseMapper;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.dao.base.SysUserRoleMapper;
import com.pinde.sci.dao.gyxjgl.GyXjEduSecondaryOrgExtMapper;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.gyxjgl.XjEduStudentCourseExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class GyXjEduSecondaryOrgBizImpl implements IGyXjEduSecondaryOrgBiz {
    @Autowired
    private GyXjEduSecondaryOrgExtMapper secondaryOrgExtMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private EduCourseMapper eduCourseMapper;

    @Override
    public List<SysUser> querySecondaryOrgAdminList(String orgFlow) {
        Map<String,String> map=new HashMap<>();
        map.put("roleFlow", InitConfig.getSysCfg("xjgl_secondaryOrg_role_flow"));
        map.put("orgFlow",orgFlow);
        return secondaryOrgExtMapper.queryUserList(map);
    }

    @Override
    public int saveSecondaryAdmin(SysUser sysUser) {
        Map<String,String> map=new HashMap<>();
        map.put("roleFlow", InitConfig.getSysCfg("xjgl_secondaryOrg_role_flow"));
        map.put("orgFlow",sysUser.getOrgFlow());
        SysUserExample example = new SysUserExample();
        if(StringUtil.isNotBlank(sysUser.getUserFlow())){
            List<SysUser> exitOrgAdmin = secondaryOrgExtMapper.queryUserList(map);
            if(exitOrgAdmin!=null&&exitOrgAdmin.size()>0){
                if(!sysUser.getUserFlow().equals(exitOrgAdmin.get(0).getUserFlow())){//二级机构下已存在管理员账户
                    return -2;
                }
            }
            return userMapper.updateByPrimaryKeySelective(sysUser);
        }else{
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andUserCodeEqualTo(sysUser.getUserCode());
            int exitUserCode = userMapper.countByExample(example);
            if(exitUserCode > 0){//用户名已存在
                return -1;
            }
            List<SysUser> exitOrgAdmin = secondaryOrgExtMapper.queryUserList(map);
            if(exitOrgAdmin!=null&&exitOrgAdmin.size()>0){//二级机构下已存在管理员账户
                return -2;
            }
            sysUser.setUserFlow(PkUtil.getUUID());
            sysUser.setUserPasswd(PasswordHelper.encryptPassword(sysUser.getUserFlow(),InitPasswordUtil.getInitPass()));
            sysUser.setStatusId(UserStatusEnum.Activated.getId());
            sysUser.setStatusDesc(UserStatusEnum.Activated.getName());
            sysUser.setReportingAuthority(GlobalConstant.RECORD_STATUS_Y);
            GeneralMethod.setRecordInfo(sysUser,true);
            //赋予管理员系统二级机构角色
            if(StringUtil.isBlank(InitConfig.getSysCfg("xjgl_secondaryOrg_role_flow"))){
                return -3;
            }
            SysUserRole record = new SysUserRole();
            record.setRecordFlow(PkUtil.getUUID());
            record.setWsId("cmis");
            record.setUserFlow(sysUser.getUserFlow());
            record.setRoleFlow(InitConfig.getSysCfg("xjgl_secondaryOrg_role_flow"));
            GeneralMethod.setRecordInfo(record,true);
            userRoleMapper.insertSelective(record);
            return userMapper.insertSelective(sysUser);
        }
    }
    @Override
    public int resetOrgAdminPwd(String userFlow) {
        SysUser user = new SysUser();
        user.setUserFlow(userFlow);
        user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), InitPasswordUtil.getInitPass()));
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<XjEduStudentCourseExt> searchStudentCourse(SysUser sysUser, EduUser eduUser, EduStudentCourse studentCourse) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("assumeOrgFlow",  sysUser.getOrgFlow());
        paramMap.put("userName", sysUser.getUserName());
        paramMap.put("sid", eduUser.getSid());
        paramMap.put("impFlow", studentCourse.getImpFlow());
        paramMap.put("studentPeriod", studentCourse.getStudentPeriod());
        paramMap.put("gradeTermId", studentCourse.getGradeTermId());
        paramMap.put("courseGrade", studentCourse.getCourseGrade());// 1有成绩 2没有成绩
        paramMap.put("submitFlag", studentCourse.getSubmitFlag());
        paramMap.put("auditStatusId", studentCourse.getAuditStatusId());
        paramMap.put("studyWayId", studentCourse.getStudyWayId());
        paramMap.put("courseCode", studentCourse.getCourseCode());
        paramMap.put("courseName", studentCourse.getCourseName());
        return secondaryOrgExtMapper.queryStudentCourseBySecondaryOrg(paramMap);
    }

    @Override
    public List<EduCourse> querySecondaryOrgEduCourse(EduCourse eduCourse) {
        EduCourseExample example=new EduCourseExample();
        EduCourseExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(eduCourse.getCourseSession())){
            criteria.andCourseSessionEqualTo(eduCourse.getCourseSession());
        }
        if(StringUtil.isNotBlank(eduCourse.getAssumeOrgFlow())){
            criteria.andAssumeOrgFlowEqualTo(eduCourse.getAssumeOrgFlow());
        }
        if(StringUtil.isNotBlank(eduCourse.getCourseCode())){
            criteria.andCourseCodeEqualTo(eduCourse.getCourseCode());
        }
        return eduCourseMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, Object>> getGradeAuditStus(Map<String, String> parMp) {
        return secondaryOrgExtMapper.getGradeAuditStus(parMp);
    }
}
