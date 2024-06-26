package com.pinde.sci.biz.study.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.study.IStudySubjectBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.StudySubjectDetailMapper;
import com.pinde.sci.dao.base.StudySubjectMapper;
import com.pinde.sci.dao.study.StudySubjectExtMapper;
import com.pinde.sci.enums.osca.AuditStatusEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class StudySubjectBizImpl implements IStudySubjectBiz {
    @Autowired
    private StudySubjectMapper subjectMapper;
    @Autowired
    private StudySubjectExtMapper subjectExtMapper;
    @Autowired
    private StudySubjectDetailMapper subjectDetailMapper;

    /**
     * 查询发布的课程
     * @param postStatus  是否发布
     * @return
     */
    @Override
    public List<StudySubject> findAllSubject(String postStatus) {
        StudySubjectExample subjectExample = new StudySubjectExample();
        StudySubjectExample.Criteria criteria=subjectExample.createCriteria().andPostStatusEqualTo(postStatus).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        subjectExample.setOrderByClause("create_time desc");
        return subjectMapper.selectByExample(subjectExample);
    }

    /**
     * 查询预约未滿的課程
     * @param
     * @return
     */
    @Override
    public List<StudySubject> findSubject(String postStatus) throws Exception{

        return subjectExtMapper.findSubject();
    }

    /**
     * 查看
     * @param subjectFlow
     * @return
     * @throws Exception
     */
    @Override
    public StudySubject findBySubjectFlow(String subjectFlow) throws Exception {
        return subjectMapper.selectByPrimaryKey(subjectFlow);
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
             subjectDetail.setAuditStatusId(AuditStatusEnum.Passing.getId());
             subjectDetail.setAuditStatusName(AuditStatusEnum.Passing.getName());
        }
        int result = subjectDetailMapper.insert(subjectDetail);
        return result;
    }

    /**
     * 更新
     * @param studySubject
     * @return
     * @throws Exception
     *//*
    @Override
    public int updateStudySubject(StudySubject studySubject) throws Exception {
       // subjectMapper.updateByPrimaryKey(studySubject);
        return subjectMapper.updateByPrimaryKey(studySubject);
    }*/

    @Override
    public int findAppiontNum(String subjectFlow) {

        return subjectExtMapper.findAppiontNum(subjectFlow);
    }

    @Override
    public StudySubjectDetail getSubjectDetail(String subjectFlow, String userFlow) {
        StudySubjectDetailExample example=new StudySubjectDetailExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(userFlow)
                .andSubjectFlowEqualTo(subjectFlow);
        List<StudySubjectDetail> list=subjectDetailMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }


}
