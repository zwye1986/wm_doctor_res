package com.pinde.sci.biz.gzykdx.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gzykdx.IGzykdxSchoolAdminBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.GyxjMASUtil;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.gzykdx.GzykdxSchoolAdminExtMapper;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class GzykdxSchoolAdminBizImpl implements IGzykdxSchoolAdminBiz {

    @Autowired
    private GzykdxSchoolAdminExtMapper schoolExtMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysCfgMapper cfgMapper;
    @Autowired
    private RecruitTargetMainMapper targeMapper;
    @Autowired
    private RecruitTargetMainDetailMapper detailMapper;
    @Autowired
    private RecruitTargetMainDetailLogMapper detailLogMapper;
    @Autowired
    private RecruitLogMapper recruitLogMapper;

    @Override
    public List<SysUser> queryOrgAdminList(String orgFlow) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andIsOrgAdminEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(orgFlow)){
            criteria.andOrgFlowEqualTo(orgFlow);
        }
        return userMapper.selectByExample(example);
    }

    @Override
    public int saveAffiliatedOrg(SysUser user) {
        SysUserExample example = new SysUserExample();
        if(StringUtil.isNotBlank(user.getUserFlow())){
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andIsOrgAdminEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(user.getOrgFlow())
            .andUserFlowNotEqualTo(user.getUserFlow());
            int exitOrgAdmin = userMapper.countByExample(example);
            if(exitOrgAdmin > 0){//二级机构下已存在管理员账户
                return -2;
            }
            return userMapper.updateByPrimaryKeySelective(user);
        }else{
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andUserCodeEqualTo(user.getUserCode());
            int exitUserCode = userMapper.countByExample(example);
            if(exitUserCode > 0){//用户名已存在
                return -1;
            }
            example.clear();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andIsOrgAdminEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(user.getOrgFlow());
            int exitOrgAdmin = userMapper.countByExample(example);
            if(exitOrgAdmin > 0){//二级机构下已存在管理员账户
                return -2;
            }
            user.setUserFlow(PkUtil.getUUID());
            user.setIsOrgAdmin(GlobalConstant.RECORD_STATUS_Y);
            user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), InitPasswordUtil.getInitPass()));
            user.setStatusId(UserStatusEnum.Activated.getId());
            user.setStatusDesc(UserStatusEnum.Activated.getName());
            user.setReportingAuthority(GlobalConstant.RECORD_STATUS_Y);
            GeneralMethod.setRecordInfo(user,true);
            //赋予管理员系统二级机构角色
            if(StringUtil.isBlank(InitConfig.getSysCfg("gzykdx_second_role_flow"))){
                return -3;
            }
            SysUserRole record = new SysUserRole();
            record.setRecordFlow(PkUtil.getUUID());
            record.setWsId("gzykdx");
            record.setUserFlow(user.getUserFlow());
            record.setRoleFlow(InitConfig.getSysCfg("gzykdx_second_role_flow"));
            GeneralMethod.setRecordInfo(record,true);
            userRoleMapper.insertSelective(record);
            return userMapper.insertSelective(user);
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
    public int updateDeclarePower(String target, String targetFlow, String powerFlag) {
        int count = 0;
        SysUserExample example = new SysUserExample();
        SysUser record = new SysUser();
        if("org".equals(target)){
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andIsOrgAdminEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(targetFlow);
            record.setReportingAuthority(powerFlag);//申报权限
            count = userMapper.updateByExampleSelective(record,example);
            schoolExtMapper.updateTeachersInOrg(targetFlow,powerFlag,InitConfig.getSysCfg("gzykdx_teacher_role_flow"));
        }else if("teacher".equals(target)){
            record.setUserFlow(targetFlow);
            record.setReportingAuthority(powerFlag);
            count = userMapper.updateByPrimaryKeySelective(record);
        }
        return count;
    }

    @Override
    public List<SysUser> queryTeachersInOrg(String orgFlow, String roleFlow, String userName) {
        return schoolExtMapper.queryTeachersInOrg(orgFlow,roleFlow,userName);
    }

    @Override
    public int saveSysCfg(List<SysCfg> sysCfgList) {
        int count = 0;
        for (SysCfg sysCfg : sysCfgList) {
            SysCfg temp = cfgMapper.selectByPrimaryKey(sysCfg.getCfgCode());
            if(temp == null){
                GeneralMethod.setRecordInfo(sysCfg, true);
                count += cfgMapper.insert(sysCfg);
            }else{
                GeneralMethod.setRecordInfo(sysCfg, false);
                count += cfgMapper.updateByPrimaryKeySelective(sysCfg);
            }
        }
        return count;
    }

    @Override
    public List<RecruitTargetMain> queryRecruitTargetList(String recruitYear) {
        RecruitTargetMainExample example = new RecruitTargetMainExample();
        RecruitTargetMainExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(recruitYear)){
            criteria.andRecruitYearEqualTo(recruitYear);
        }
        example.setOrderByClause("RECRUIT_YEAR DESC");
        return targeMapper.selectByExample(example);
    }

    @Override
    public RecruitTargetMain queryRecTargetByFlow(String rargetFlow) {
        return targeMapper.selectByPrimaryKey(rargetFlow);
    }

    @Override
    public int saveRecruitPlan(RecruitTargetMain recTarget) {
        if(StringUtil.isNotBlank(recTarget.getRargetFlow())) {
            GeneralMethod.setRecordInfo(recTarget,false);
            return targeMapper.updateByPrimaryKeySelective(recTarget);
        }else{
            RecruitTargetMainExample example = new RecruitTargetMainExample();
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                    .andRecruitYearEqualTo(recTarget.getRecruitYear());
            int exitYearPlan = targeMapper.countByExample(example);
            if(exitYearPlan > 0){//年招生计划也存在
                return -1;
            }
            recTarget.setRargetFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(recTarget,true);
            return targeMapper.insertSelective(recTarget);
        }
    }

    @Override
    public List<SysUser> queryOrgAdminPowerList(String orgFlow) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andIsOrgAdminEqualTo(GlobalConstant.RECORD_STATUS_Y).andReportingAuthorityEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(orgFlow)){
            criteria.andOrgFlowEqualTo(orgFlow);
        }
        return userMapper.selectByExample(example);
    }

    @Override
    public List<RecruitTargetMainDetail> queryRecDetailByFlow(String rargetFlow) {
        RecruitTargetMainDetailExample example = new RecruitTargetMainDetailExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andRargetFlowEqualTo(rargetFlow);
        return detailMapper.selectByExample(example);
    }

    @Override
    public int releasedInfo(String rargetFlow) {
        RecruitTargetMain target = new RecruitTargetMain();
        target.setIsReport(GlobalConstant.RECORD_STATUS_Y);
        target.setRargetFlow(rargetFlow);
        RecruitTargetMainDetail record = new RecruitTargetMainDetail();
        record.setIsReport(GlobalConstant.RECORD_STATUS_Y);
        RecruitTargetMainDetailExample example = new RecruitTargetMainDetailExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andRargetFlowEqualTo(rargetFlow);
        detailMapper.updateByExampleSelective(record,example);
        return targeMapper.updateByPrimaryKeySelective(target);
    }

    @Override
    public List<Map<String, Object>> passedTeacherApplyList(TeacherTargetApply target) {
        return schoolExtMapper.queryPassedTeacherApply(target);
    }

    @Override
    public int releasedPassedTarget(String recruitYear){
        int num = schoolExtMapper.releasedPassedTarget(recruitYear);
        if(num > 0){
            List<Map<String, Object>> dataList = schoolExtMapper.queryOrgRecruitInfo(recruitYear);
            if(null != dataList && !dataList.isEmpty()){
                for(int i=0;i<dataList.size();i++){
                    String content = "您好，广州医科大学招生目录已经发布，贵单位共有拟招学术学位"+dataList.get(i).get("ACADEMIC_SUM")+"人，拟招专业学位"+dataList.get(i).get("SPECIALIZED_SUM")+"人。";
                    GyxjMASUtil mas = new GyxjMASUtil(new String[]{(String)dataList.get(i).get("USER_PHONE")},content);
                    try {
                        mas.sendSms();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return num;
    }

    @Override
    public int saveOrgTarget(RecruitTargetMainDetail detail) {
        if(StringUtil.isNotBlank(detail.getRecordFlow())){
            GeneralMethod.setRecordInfo(detail,false);
            return detailMapper.updateByPrimaryKeySelective(detail);
        }else{
            detail.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(detail,true);
            return detailMapper.insertSelective(detail);
        }
    }

    @Override
    public int targetNumOfOrgByYear(String recruitYear, String orgFlow, String degreeTypeId) {
        String returnValue = schoolExtMapper.targetNumOfOrgByYear(recruitYear,orgFlow,degreeTypeId);
        return returnValue == null?0:Integer.valueOf(returnValue);
    }

    @Override
    public int saveChangeOrgTarge(RecruitTargetMainDetail detail, RecruitTargetMainDetailLog log) {
        int count = 0;
        if(StringUtil.isNotBlank(detail.getRecordFlow())){
            GeneralMethod.setRecordInfo(detail,false);
            count += detailMapper.updateByPrimaryKeySelective(detail);
            log.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(log,true);
            detailLogMapper.insertSelective(log);
        }else{
            detail.setRecordFlow(PkUtil.getUUID());//发布后再添加权限而机构重新分配
            detail.setIsReport(GlobalConstant.RECORD_STATUS_Y);
            GeneralMethod.setRecordInfo(detail,true);
            count += detailMapper.insertSelective(detail);
        }
        return count;
    }

    @Override
    public Map<String, Object> searchUseTargetSum(String rargetFlow) {
        return schoolExtMapper.targetNumByYear(rargetFlow);
    }

    @Override
    public List<RecruitTargetMainDetailLog> queryDetailLogs() {
        RecruitTargetMainDetailLogExample example = new RecruitTargetMainDetailLogExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("REF_RECORD_FLOW,MODIFY_TIME DESC");
        return detailLogMapper.selectByExample(example);
    }

    @Override
    public int saveRecruitLog(RecruitLog log) {
        int count=0;
        if(StringUtil.isBlank(log.getLogFlow())){
            log.setLogFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(log,true);
            count=recruitLogMapper.insert(log);
        }else {
            GeneralMethod.setRecordInfo(log,false);
            count=recruitLogMapper.updateByPrimaryKeySelective(log);
        }
        return count;
    }

    @Override
    public List<Map<String, String>> searchRecruitLogList(String recruitYear,String userName,String startChangeTime,String endChangeTime) {
        Map<String, String> paramMap=new HashMap<String, String>();
        paramMap.put("recruitYear",recruitYear);
        paramMap.put("userName",userName);
        if(StringUtil.isNotBlank(startChangeTime)) {
            startChangeTime = startChangeTime.replace("-", "");
        }
        if(StringUtil.isNotBlank(endChangeTime)) {
            endChangeTime = endChangeTime.replace("-", "");
        }
        paramMap.put("startChangeTime",startChangeTime);
        paramMap.put("endChangeTime",endChangeTime);
        return schoolExtMapper.queryRecruitLogList(paramMap);
    }
}
