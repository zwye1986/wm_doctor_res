package com.pinde.sci.biz.gzykdx.impl;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gzykdx.IGzykdxAuditManageBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.SysOrgMapper;
import com.pinde.sci.dao.base.TeacherTargetApplyMapper;
import com.pinde.sci.dao.base.TeacherThesisDetailMapper;
import com.pinde.sci.dao.gzykdx.GzykdxAuditManageExtMapper;
import com.pinde.sci.enums.gzykdx.GzykdxAuditStatusEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wangshuai
 * @Copyright njpdxx.com
 * <p/>
 * 此类主要是实现 XXX 功能
 * @since 2017/3/7
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GzykdxAuditManageBizImpl implements IGzykdxAuditManageBiz {
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private SysOrgMapper sysOrgMapper;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;
    @Autowired
    private GzykdxAuditManageExtMapper auditManageExtMapper;
    @Autowired
    private TeacherTargetApplyMapper targetApplyMapper;
    @Autowired
    private TeacherThesisDetailMapper thesisDetailMapper;

    @Override
    public List<Map<String, Object>> selectDoctorsRecruitList(Map<String, String> map) {
        return auditManageExtMapper.queryDoctorsRecruitList(map);
    }

    @Override
    public List<Map<String, Object>> selectTeacherTargetApplyList(Map<String, String> map) {
        return auditManageExtMapper.qureyTeacherTargetApplyList(map);
    }

    @Override
    public List<Map<String, Object>> selectTeacherApplyListOrg(Map<String, String> map) {
        return auditManageExtMapper.qureyTeacherApplyListOrg(map);
    }

    @Override
    public List<Map<String, String>> selectRecruitNum(Map<String, String> map) {
        return auditManageExtMapper.queryRecruitNum(map);
    }

    @Override
    public int selectAcademicRecruitSum(Map<String, String> map) {
        return auditManageExtMapper.queryAcademicRecruitSum(map);
    }

    @Override
    public int selectSpecializedRecruitSum(Map<String, String> map) {
        return auditManageExtMapper.querySpecializedRecruitSum(map);
    }

    @Override
    public int commitAllApply(List<String> applyFlows) {
        TeacherTargetApply apply=new TeacherTargetApply();
        apply.setIsSubmit(GlobalConstant.IS_EXAM_TEA_Y);
        apply.setAuditStatusId(GzykdxAuditStatusEnum.WaitingForSchool.getId());
        apply.setAuditStatusName(GzykdxAuditStatusEnum.WaitingForSchool.getName());
        TeacherTargetApplyExample example=new TeacherTargetApplyExample();
        TeacherTargetApplyExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(applyFlows!=null&&applyFlows.size()>0){
            criteria.andApplyFlowIn(applyFlows);
            return targetApplyMapper.updateByExampleSelective(apply,example);
        }else{
            return 0;
        }
    }

    @Override
    public List<TeacherTargetApply> selectApplyList(String orgFlow,String auditStatusId) {
        TeacherTargetApplyExample example=new TeacherTargetApplyExample();
        TeacherTargetApplyExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andRecruitYearEqualTo(new SimpleDateFormat("yyyy").format(new Date()));
        if(StringUtil.isNotBlank(orgFlow)){
            criteria.andOrgFlowEqualTo(orgFlow);
        }
        if(StringUtil.isNotBlank(auditStatusId)){
            criteria.andAuditStatusIdEqualTo(auditStatusId);
        }
        return targetApplyMapper.selectByExample(example);
    }

    @Override
    public List<TeacherTargetApply> teacherApplyList(TeacherTargetApply apply) {
        TeacherTargetApplyExample example = new TeacherTargetApplyExample();
        TeacherTargetApplyExample.Criteria criteria = example.createCriteria().
                andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andAuditStatusIdEqualTo(GzykdxAuditStatusEnum.SchoolPassed.getId());
        if(StringUtil.isNotBlank(apply.getRecruitYear())){
            criteria.andRecruitYearEqualTo(apply.getRecruitYear());
        }
        if(StringUtil.isNotBlank(apply.getSpeId())){
            criteria.andSpeIdEqualTo(apply.getSpeId());
        }
        if(StringUtil.isNotBlank(apply.getResearchDirection())){
            criteria.andResearchDirectionLike("%"+apply.getResearchDirection()+"%");
        }
        if(StringUtil.isNotBlank(apply.getResearchDirectionId())){
            criteria.andResearchDirectionIdEqualTo(apply.getResearchDirectionId());
        }
        if(StringUtil.isNotBlank(apply.getUserFlow())){
            criteria.andUserFlowEqualTo(apply.getUserFlow());
        }
        if(StringUtil.isNotBlank(apply.getUserName())){
            criteria.andUserNameLike("%"+apply.getUserName()+"%");
        }
        if(StringUtil.isNotBlank(apply.getIsAcademic())){
            criteria.andIsAcademicIsNotNull();
        }
        if(StringUtil.isNotBlank(apply.getIsSpecialized())){
            criteria.andIsSpecializedIsNotNull();
        }
        if(StringUtil.isNotBlank(apply.getOrgFlow())){
            criteria.andOrgFlowEqualTo(apply.getOrgFlow());
        }
        return targetApplyMapper.selectByExample(example);
    }

    @Override
    public int editApplies(List<String> applyFlows, TeacherTargetApply apply) {
        TeacherTargetApplyExample example=new TeacherTargetApplyExample();
        TeacherTargetApplyExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(applyFlows!=null&&applyFlows.size()>0){
            criteria.andApplyFlowIn(applyFlows);
            return targetApplyMapper.updateByExampleSelective(apply,example);
        }else{
            return 0;
        }
    }

    @Override
    public List<Map<String, Object>> selectVacanciesOrg(Map<String, String> map) {
        return auditManageExtMapper.queryVacanciesOrgNew(map);
    }

    @Override
    public List<Map<String, String>> teacherRecruitInfo(Map<String, String> map) {
        return auditManageExtMapper.teacherRecruitInfo(map);
    }
}
