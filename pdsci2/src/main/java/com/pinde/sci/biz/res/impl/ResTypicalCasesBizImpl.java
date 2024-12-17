package com.pinde.sci.biz.res.impl;

import com.pinde.core.common.enums.DiscipleStatusEnum;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResTypicalCasesBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.ResTypicalCasesMapper;
import com.pinde.sci.dao.res.DiscipleDoctorExtMapper;
import com.pinde.sci.dao.res.ResFollowTeacherRecordExtMapper;
import com.pinde.core.model.ResTypicalCases;
import com.pinde.core.model.ResTypicalCasesExample;
import com.pinde.sci.model.res.ResDoctorExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2016/10/14.
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class ResTypicalCasesBizImpl implements IResTypicalCasesBiz {
    @Autowired
    private ResTypicalCasesMapper resTypicalCasesMapper;
    @Autowired
    private ResFollowTeacherRecordExtMapper resFollowTeacherRecordExtMapper;
    @Autowired
    private DiscipleDoctorExtMapper doctorExtMapper;
    @Override
    public List<ResTypicalCases> searchTypicalCases(ResTypicalCases resTypicalCases) {
        ResTypicalCasesExample example = new ResTypicalCasesExample();
        ResTypicalCasesExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(resTypicalCases!=null){
            if(StringUtil.isNotBlank(resTypicalCases.getDoctorFlow())){
                criteria.andDoctorFlowEqualTo(resTypicalCases.getDoctorFlow());
            }
            if(StringUtil.isNotBlank(resTypicalCases.getTeacherFlow())){
                criteria.andTeacherFlowEqualTo(resTypicalCases.getTeacherFlow());
            }
            if(StringUtil.isNotBlank(resTypicalCases.getRecordFlow())){
                criteria.andRecordFlowEqualTo(resTypicalCases.getRecordFlow());
            }
            if(StringUtil.isNotBlank(resTypicalCases.getAuditStatusId())&& DiscipleStatusEnum.Submit.getId().equals(resTypicalCases.getAuditStatusId())){
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
                resTypicalCases.setAuditStatusId("");
                resTypicalCases.setAuditStatusName("");
            }
            if(StringUtil.isNotBlank(resTypicalCases.getAuditStatusId())){
                criteria.andAuditStatusIdEqualTo(resTypicalCases.getAuditStatusId());
            }
            if(StringUtil.isNotBlank(resTypicalCases.getAuditStatusName())){
                criteria.andAuditStatusNameEqualTo(resTypicalCases.getAuditStatusName());
            }
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return resTypicalCasesMapper.selectByExample(example);
    }

    @Override
    public int saveResTypicalCases(ResTypicalCases resTypicalCases) {
        if(resTypicalCases!=null){
            if(StringUtil.isNotBlank(resTypicalCases.getRecordFlow())){
                GeneralMethod.setRecordInfo(resTypicalCases,false);
                return resTypicalCasesMapper.updateByPrimaryKeyWithBLOBs(resTypicalCases);
            }else {
                resTypicalCases.setRecordFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(resTypicalCases,true);
                return resTypicalCasesMapper.insert(resTypicalCases);
            }

        }
        return 0;
    }

    @Override
    public ResTypicalCases findResTypicalCases(String caseFlow) {
        return resTypicalCasesMapper.selectByPrimaryKey(caseFlow);
    }

    @Override
    public List<ResDoctorExt> searchCasePendingAudit(Map<String, Object> beMap) {
        return resFollowTeacherRecordExtMapper.selectCasePendingAudit(beMap);
    }

    @Override
    public int agreeRecordBatch(Map<String, String> paramMap) {
        return doctorExtMapper.agreeTypicalCasesBatch(paramMap);
    }
}
