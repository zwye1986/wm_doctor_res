package com.pinde.sci.biz.recruit.impl;


import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.recruit.IRecruitExamTeacherBiz;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.RecruitExamTeacherMapper;
import com.pinde.sci.model.mo.RecruitExamTeacher;
import com.pinde.sci.model.mo.RecruitExamTeacherExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
//@Transactional(rollbackFor = Exception.class)
public class RecruitExamTeacherBizImpl implements IRecruitExamTeacherBiz {

    @Autowired
    private RecruitExamTeacherMapper recruitExamTeacherMapper;

    /**
     * 根据roomFlow查询考场是否分配监考教师
     * @param roomFlow
     * @return
     */
    @Override
    public String IsExamRoomUsed(String roomFlow,String orgFlow) {
        RecruitExamTeacherExample example = new RecruitExamTeacherExample();
        RecruitExamTeacherExample.Criteria criteria = example.createCriteria();

        criteria.andRoomFlowEqualTo(roomFlow);
        criteria.andOrgFlowEqualTo(orgFlow);
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);

        List<RecruitExamTeacher> recruitExamTeachers = recruitExamTeacherMapper.selectByExample(example);
        if (recruitExamTeachers!=null&&!recruitExamTeachers.isEmpty()) {
            return com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y;
        }else {
            return com.pinde.core.common.GlobalConstant.RECORD_STATUS_N;
        }
    }

    /**
     * 查询所有监考教师
     * @param orgFlow
     * @return
     */
    @Override
    public List<RecruitExamTeacher> searchAllExamRoom(String orgFlow) {
        RecruitExamTeacherExample example = new RecruitExamTeacherExample();
        RecruitExamTeacherExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        criteria.andOrgFlowEqualTo(orgFlow);
        return recruitExamTeacherMapper.selectByExample(example);
    }

    @Override
    public Integer addExamTeacher(RecruitExamTeacher examTeacher) {
        examTeacher.setCreateTime(DateUtil.getCurrDateTime());
        examTeacher.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        examTeacher.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        examTeacher.setTeacherFlow(PkUtil.getUUID());
        examTeacher.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        return recruitExamTeacherMapper.insert(examTeacher);
    }

    @Override
    public RecruitExamTeacher searchExamTeacherByFlow(String teacherFlow) {
        RecruitExamTeacherExample example = new RecruitExamTeacherExample();
        RecruitExamTeacherExample.Criteria criteria = example.createCriteria();
        criteria.andTeacherFlowEqualTo(teacherFlow);
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<RecruitExamTeacher> recruitExamTeachers = recruitExamTeacherMapper.selectByExample(example);
        if (recruitExamTeachers != null && recruitExamTeachers.size() > 0){
            return recruitExamTeachers.get(0);
        }else {
            return null;
        }
    }

    @Override
    public Integer updateExamTeacher(RecruitExamTeacher editInfo) {
        editInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        editInfo.setModifyTime(DateUtil.getCurrDateTime());
        editInfo.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        return recruitExamTeacherMapper.updateByPrimaryKeySelective(editInfo);
    }

    @Override
    public Integer deleteExamTeacherByFlow(String teacherFlow) {
        RecruitExamTeacher examTeacher = new RecruitExamTeacher();
        examTeacher.setTeacherFlow(teacherFlow);
        examTeacher.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        examTeacher.setModifyTime(DateUtil.getCurrDateTime());
        examTeacher.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        return recruitExamTeacherMapper.updateByPrimaryKeySelective(examTeacher);
    }

    @Override
    public List<RecruitExamTeacher> searchExamTeacherList(String orgFlow, String teaName, String roomFlow, String teaRole) {
        RecruitExamTeacherExample example = new RecruitExamTeacherExample();
        RecruitExamTeacherExample.Criteria criteria = example.createCriteria();
        criteria.andOrgFlowEqualTo(orgFlow);
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(teaName) && teaName != ""){
            criteria.andTeaNameLike("%"+teaName+"%");
        }
        if (StringUtil.isNotBlank(roomFlow) && roomFlow != ""){
            criteria.andRoomFlowEqualTo(roomFlow);
        }
        if (StringUtil.isNotBlank(teaRole) && teaRole != ""){
            criteria.andTeaRoleEqualTo(teaRole);
        }
        return recruitExamTeacherMapper.selectByExample(example);
    }
}
