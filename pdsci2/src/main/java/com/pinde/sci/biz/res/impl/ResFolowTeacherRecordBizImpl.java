package com.pinde.sci.biz.res.impl;

import com.pinde.core.common.enums.DiscipleStatusEnum;
import com.pinde.core.model.ResDiscipleRecordInfo;
import com.pinde.core.model.ResDiscipleRecordInfoExample;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResFolowTeacherRecordBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.ResDiscipleRecordInfoMapper;
import com.pinde.sci.dao.base.ResStudentDiscipleTeacherMapper;
import com.pinde.sci.dao.res.ResFollowTeacherRecordExtMapper;
import com.pinde.sci.model.mo.ResStudentDiscipleTeacher;
import com.pinde.sci.model.mo.ResStudentDiscipleTeacherExample;
import com.pinde.sci.model.res.ResDoctorExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2016/10/13.
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class ResFolowTeacherRecordBizImpl implements IResFolowTeacherRecordBiz {

    @Autowired
    private ResStudentDiscipleTeacherMapper resStudentDiscipleTeacherMapper;
    @Autowired
    private ResDiscipleRecordInfoMapper resDiscipleRecordInfoMapper;
    @Autowired
    private ResFollowTeacherRecordExtMapper resFollowTeacherRecordExtMapper;
    @Override
    public List<ResStudentDiscipleTeacher> searchResStudentDiscipleTeacher(ResStudentDiscipleTeacher resStudentDiscipleTeacher) {
        ResStudentDiscipleTeacherExample example = new ResStudentDiscipleTeacherExample();
        ResStudentDiscipleTeacherExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(resStudentDiscipleTeacher!=null){
            if(StringUtil.isNotBlank(resStudentDiscipleTeacher.getDoctorFlow())){
                criteria.andDoctorFlowEqualTo(resStudentDiscipleTeacher.getDoctorFlow());
            }
            if(StringUtil.isNotBlank(resStudentDiscipleTeacher.getDoctorName())){
                criteria.andDoctorNameLike("%"+resStudentDiscipleTeacher.getDoctorName()+"%");
            }
            if(StringUtil.isNotBlank(resStudentDiscipleTeacher.getTeacherFlow())){
                criteria.andTeacherFlowEqualTo(resStudentDiscipleTeacher.getTeacherFlow());
            }

        }
        example.setOrderByClause("DOCTOR_NAME desc");
        return resStudentDiscipleTeacherMapper.selectByExample(example);
    }

    @Override
    public int saveResDiscipleRecordInfo(ResDiscipleRecordInfo resDiscipleRecordInfo) {
        if(resDiscipleRecordInfo!=null){
            if(StringUtil.isNotBlank(resDiscipleRecordInfo.getRecordFlow())){
                GeneralMethod.setRecordInfo(resDiscipleRecordInfo, false);
               return resDiscipleRecordInfoMapper.updateByPrimaryKeySelective(resDiscipleRecordInfo);
            }else{
                resDiscipleRecordInfo.setRecordFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(resDiscipleRecordInfo, true);
                return resDiscipleRecordInfoMapper.insert(resDiscipleRecordInfo);
            }
        }
        return 0;
    }

    @Override
    public List<ResDiscipleRecordInfo> searchFolowTeacherRecord(ResDiscipleRecordInfo resDiscipleRecordInfo) {
        ResDiscipleRecordInfoExample example = new ResDiscipleRecordInfoExample();
        ResDiscipleRecordInfoExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(resDiscipleRecordInfo!=null){
            if(StringUtil.isNotBlank(resDiscipleRecordInfo.getDoctorFlow())){
                criteria.andDoctorFlowEqualTo(resDiscipleRecordInfo.getDoctorFlow());
            }
            if(StringUtil.isNotBlank(resDiscipleRecordInfo.getTeacherFlow())){
                criteria.andTeacherFlowEqualTo(resDiscipleRecordInfo.getTeacherFlow());
            }
            if(StringUtil.isNotBlank(resDiscipleRecordInfo.getRecordFlow())){
                criteria.andRecordFlowEqualTo(resDiscipleRecordInfo.getRecordFlow());
            }
            if(StringUtil.isNotBlank(resDiscipleRecordInfo.getAuditStatusId())&& DiscipleStatusEnum.Submit.getId().equals(resDiscipleRecordInfo.getAuditStatusId())){
                List<String> values= new ArrayList<>();
                values.add(DiscipleStatusEnum.Submit.getId());
                values.add(DiscipleStatusEnum.DiscipleAudit.getId());
                values.add(DiscipleStatusEnum.DiscipleBack.getId());
                values.add(DiscipleStatusEnum.AdminAudit.getId());
                values.add(DiscipleStatusEnum.AdminBack.getId());
                values.add(DiscipleStatusEnum.PendingAudit.getId());
                values.add(DiscipleStatusEnum.Qualified.getId());
                values.add(DiscipleStatusEnum.UnQualified.getId());
                criteria.andAuditStatusIdIn(values);
                resDiscipleRecordInfo.setAuditStatusId("");
                resDiscipleRecordInfo.setAuditStatusName("");
            }
            if(StringUtil.isNotBlank(resDiscipleRecordInfo.getAuditStatusId())){
                criteria.andAuditStatusIdEqualTo(resDiscipleRecordInfo.getAuditStatusId());
            }
            if(StringUtil.isNotBlank(resDiscipleRecordInfo.getAuditStatusName())){
                criteria.andAuditStatusNameEqualTo(resDiscipleRecordInfo.getAuditStatusName());
            }
            if(StringUtil.isNotBlank(resDiscipleRecordInfo.getDoctorName())){
                criteria.andDoctorNameLike("%"+resDiscipleRecordInfo.getDoctorName()+"%");
            }
        }
        example.setOrderByClause("CREATE_TIME desc");
        return resDiscipleRecordInfoMapper.selectByExample(example);
    }

    @Override
    public List<ResDoctorExt> searchDisciplePendingAudit(Map<String,Object> map) {
        return resFollowTeacherRecordExtMapper.selectPendingAudit(map);
    }

    @Override
    public int batchAgreeResDiscipleRecordInfo(Map<String,String> map) {
        return resFollowTeacherRecordExtMapper.batchAgreeResDiscipleRecordInfo(map);
    }
}
