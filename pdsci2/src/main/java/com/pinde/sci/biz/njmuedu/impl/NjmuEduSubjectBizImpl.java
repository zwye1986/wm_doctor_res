package com.pinde.sci.biz.njmuedu.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.njmuedu.INjmuEduSubjectBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.StudySubjectDetailMapper;
import com.pinde.sci.dao.base.StudySubjectMapper;
import com.pinde.sci.dao.njmuedu.NjmuEduSubjectExtMapper;
import com.pinde.sci.enums.njmuedu.NjmuEduAuditStatusEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NjmuEduSubjectBizImpl implements INjmuEduSubjectBiz {
    @Autowired
    private StudySubjectMapper studySubjectMapper;
    @Autowired
    private StudySubjectDetailMapper subjectDetailMapper;
    @Autowired
    private NjmuEduSubjectExtMapper studySubjectExtMapper;

    @Override
    public int addSubject(StudySubject studySubject) throws Exception {
        if(StringUtil.isBlank(studySubject.getSubjectFlow()))
        {
            studySubject.setSubjectFlow(PkUtil.getUUID());
            studySubject.setCreateTime(DateUtil.getCurrentTime());
            studySubject.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            GeneralMethod.setRecordInfo(studySubject,true);
            return  studySubjectMapper.insert(studySubject);
        }else{
            studySubject.setModifyTime(DateUtil.getCurrTime());
            studySubject.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            GeneralMethod.setRecordInfo(studySubject,false);
            return  studySubjectMapper.updateByPrimaryKeySelective(studySubject);
        }
    }

    @Override
    public StudySubject readSubject(String subjectFlow) {
        return  studySubjectMapper.selectByPrimaryKey(subjectFlow);
    }

    @Override
    public List<StudySubject> subjectList(StudySubject subject) {
        StudySubjectExample example=new StudySubjectExample();
        StudySubjectExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(subject.getSubjectName()))
        {
            criteria.andSubjectNameLike("%"+subject.getSubjectName()+"%");
        }
        if(StringUtil.isNotBlank(subject.getSubjectYear()))
        {
            criteria.andSubjectYearEqualTo(subject.getSubjectYear());
        }
        if(StringUtil.isNotBlank(subject.getSubjectType()))
        {
            criteria.andSubjectTypeEqualTo(subject.getSubjectType());
        }
        if(StringUtil.isNotBlank(subject.getPostStatus())){
            criteria.andPostStatusEqualTo(subject.getPostStatus());
        }
        example.setOrderByClause(" subject_year desc,subject_start_time desc,subject_end_time desc");
        return studySubjectMapper.selectByExample(example);
    }

    @Override
    public int selectCountByAuditStatusId(String subjectFlow) throws Exception {
            StudySubjectDetailExample subjectDetailExample = new StudySubjectDetailExample();
            StudySubjectDetailExample.Criteria criteria = subjectDetailExample.createCriteria().andSubjectFlowEqualTo(subjectFlow)
                    .andAuditStatusIdEqualTo("PassedTwo").andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            return subjectDetailMapper.countByExample(subjectDetailExample);

    }

    @Override
    public List<Map<String, String>> detailList(Map<String, String> param) {
        return studySubjectExtMapper.detailList(param);
    }

    @Override
    public void auditPassed(List<String> detailFlows) {
        studySubjectExtMapper.auditPassed(detailFlows);
    }

    @Override
    public void auditPassedTwo(List<String> detailFlows) {
        studySubjectExtMapper.auditPassedTwo(detailFlows);
    }

    @Override
    public void auditUnPassed(List<String> detailFlows) {
        studySubjectExtMapper.auditUnPassed(detailFlows);
    }

    @Override
    public void auditUnPassedTwo(List<String> detailFlows) {
        studySubjectExtMapper.auditUnPassedTwo(detailFlows);
    }

    @Override
    public void auditBack(List<String> detailFlows) {
        studySubjectExtMapper.auditBack(detailFlows);
    }

    /**
     * 查询发布的课程
     * @param postStatus  是否发布
     * @return
     */
    @Override
    public List<StudySubject> findAllSubject(String postStatus) {
        StudySubjectExample subjectExample = new StudySubjectExample();
        StudySubjectExample.Criteria criteria=subjectExample.createCriteria().andPostStatusEqualTo(postStatus).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        subjectExample.setOrderByClause("subject_year desc,create_time desc");
        return studySubjectMapper.selectByExample(subjectExample);
    }

    /**
     * 查询预约未滿的課程
     * @param
     * @return
     */
    @Override
    public List<StudySubject> findSubject(String postStatus) throws Exception{

        return studySubjectExtMapper.findSubject();
    }

    /**
     * 查看
     * @param subjectFlow
     * @return
     * @throws Exception
     */
    @Override
    public StudySubject findBySubjectFlow(String subjectFlow) throws Exception {
        return studySubjectMapper.selectByPrimaryKey(subjectFlow);
    }

    @Override
    public int findAppiontNum(String subjectFlow) {

        return studySubjectExtMapper.findAppiontNum(subjectFlow);
    }

    @Override
    public List<StudySubjectDetail> findByDoctorFlow(String doctorFlow) {
        StudySubjectDetailExample example = new StudySubjectDetailExample();
        StudySubjectDetailExample.Criteria criteria = example.createCriteria().andDoctorFlowEqualTo(doctorFlow)
                .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        return subjectDetailMapper.selectByExample(example);
    }

    @Override
    public StudySubjectDetail findBydetailFlow(String detailFlow) {
        return subjectDetailMapper.selectByPrimaryKey(detailFlow);
    }

    @Override
    public StudySubjectDetail getSubjectDetail(String subjectFlow, String userFlow) {
        StudySubjectDetailExample example=new StudySubjectDetailExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(userFlow)
                .andSubjectFlowEqualTo(subjectFlow);
        List<StudySubjectDetail> list = subjectDetailMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    /**
     * 保存
     * @param studySubject
     * @return
     */
    @Override
    public int saveSubject(StudySubject studySubject) throws Exception {
        StudySubjectDetail subjectDetail = new StudySubjectDetail();
        subjectDetail.setDetailFlow(PkUtil.getUUID());
        subjectDetail.setOrderTime(DateUtil.getCurrDateTime2());
        subjectDetail.setCreateTime(DateUtil.getCurrDateTime());
        subjectDetail.setRecordStatus("Y");
        SysUser user = GlobalContext.getCurrentUser();
        if(user != null){
            subjectDetail.setDoctorFlow(user.getUserFlow());
            subjectDetail.setCreateUserFlow(user.getUserFlow());
        }
        if(studySubject != null){
            subjectDetail.setSubjectFlow(studySubject.getSubjectFlow());
            subjectDetail.setAuditStatusId(NjmuEduAuditStatusEnum.Passing.getId());
            subjectDetail.setAuditStatusName(NjmuEduAuditStatusEnum.Passing.getName());
        }
        int result = subjectDetailMapper.insert(subjectDetail);
        return result;
    }

}
