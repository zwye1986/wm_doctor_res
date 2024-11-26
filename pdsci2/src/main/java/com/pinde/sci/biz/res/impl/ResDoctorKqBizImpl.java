package com.pinde.sci.biz.res.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorKqBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.res.ResDoctorKqExtMapper;
import com.pinde.sci.dao.res.ResDoctorSchProcessExtMapper;
import com.pinde.sci.form.res.TimeSetFrom;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class ResDoctorKqBizImpl implements IResDoctorKqBiz {
    @Autowired
    private ResKgCfgMapper kgCfgMapper;
    @Autowired
    private ResDoctorKqMapper resDoctorKqMapper;
    @Autowired
    private ResDoctorSchProcessExtMapper processExtMapper;
    @Autowired
    private ResDoctorKqExtMapper resDoctorKqExtMapper;
    @Autowired
    private ResDoctorSigninMapper signinMapper;
    @Autowired
    private ResDoctorSchProcessMapper doctorSchProcessMapper;
    @Autowired
    private ResDoctorKqLogMapper kqLogMapper;

    @Override
    public ResDoctorKq readResDoctorKq(String recordFlow) {
        return resDoctorKqMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public int editResDoctorKq(ResDoctorKq kq) {
        String recordFlow = kq.getRecordFlow();
        if(StringUtil.isBlank(recordFlow)){
            kq.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(kq,true);
            return resDoctorKqMapper.insert(kq);
        }else {
            GeneralMethod.setRecordInfo(kq,false);
            return resDoctorKqMapper.updateByPrimaryKeySelective(kq);
        }
    }

    @Override
    public List<ResDoctorKq> searchResDoctorKq(ResDoctorKq kq, List<String> notStatus) {
        ResDoctorKqExample example = new ResDoctorKqExample();
        ResDoctorKqExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(kq.getDoctorFlow())){
            criteria.andDoctorFlowEqualTo(kq.getDoctorFlow());
        }
        if(StringUtil.isNotBlank(kq.getKqTypeId())){
            criteria.andKqTypeIdEqualTo(kq.getKqTypeId());
        }
        if(StringUtil.isNotBlank(kq.getTypeId())){
            criteria.andTypeIdEqualTo(kq.getTypeId());
        }
        if(StringUtil.isNotBlank(kq.getStartDate())){
            criteria.andEndDateGreaterThanOrEqualTo(kq.getStartDate());
        }
        if(StringUtil.isNotBlank(kq.getEndDate())){
            criteria.andStartDateLessThanOrEqualTo(kq.getEndDate());
        }
        if(StringUtil.isNotBlank(kq.getTeacherFlow())){
            criteria.andTeacherFlowEqualTo(kq.getTeacherFlow());
        }
        if(StringUtil.isNotBlank(kq.getHeadFlow())){
            criteria.andHeadFlowEqualTo(kq.getHeadFlow());
        }
        if(StringUtil.isNotBlank(kq.getManagerFlow())){
            criteria.andManagerFlowEqualTo(kq.getManagerFlow());
        }
        if(StringUtil.isNotBlank(kq.getTutorFlow())){
            criteria.andTutorFlowEqualTo(kq.getTutorFlow());
        }
        if(StringUtil.isNotBlank(kq.getDoctorName())){
            criteria.andDoctorNameLike("%"+kq.getDoctorName()+"%");
        }
        if(StringUtil.isNotBlank(kq.getOrgFlow())){
            criteria.andOrgFlowEqualTo(kq.getOrgFlow());
        }
        if(StringUtil.isNotBlank(kq.getAuditStatusId())){
            criteria.andAuditStatusIdEqualTo(kq.getAuditStatusId());
        }
        if(notStatus!=null&&notStatus.size()>0)
        {
            criteria.andAuditStatusIdNotIn(notStatus);
        }
        return resDoctorKqMapper.selectByExample(example);
    }

    @Override
    public List<ResDoctorKq> searchResDoctorKq(Map<String, Object> paramMap) {
        return resDoctorKqExtMapper.searchResDoctorKq(paramMap);
    }

    @Override
    public List<Map<String,Object>> teacherGetStudents(Map<String, Object> paramMap) {
        return processExtMapper.teacherGetStudents(paramMap);
    }

    @Override
    public List<Map<String,Object>> headGetStudents(Map<String, Object> paramMap) {
        return processExtMapper.headGetStudents(paramMap);
    }

    @Override
    public List<Map<String, String>> getSigninList(Map<String, Object> param) {
        return resDoctorKqExtMapper.getSigninList(param);
    }

    @Override
    public List<ResKgCfg> readKqCfgList(String orgFlow, String doctorCategoryId) {
        ResKgCfgExample example=new ResKgCfgExample();
        ResKgCfgExample.Criteria criteria= example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andOrgFlowEqualTo(orgFlow);
        if(StringUtil.isNotBlank(doctorCategoryId))
        {
            criteria.andDoctorTypeIdEqualTo(doctorCategoryId);
        }
        return kgCfgMapper.selectByExample(example);
    }

    @Override
    public int saveKqCfgs(TimeSetFrom timeSetFrom) {
        int c=0;
        if(timeSetFrom!=null&&timeSetFrom.getCfgs()!=null&&StringUtil.isNotBlank(timeSetFrom.getOrgFlow()))
        {
          for(ResKgCfg cfg:timeSetFrom.getCfgs())
          {
              ResKgCfg old=getKqCfg(cfg.getLessOrGreater(),timeSetFrom.getOrgFlow(),cfg.getDoctorTypeId());
              if(old!=null)
                  cfg.setCfgFlow(old.getCfgFlow());
              cfg.setOrgFlow(timeSetFrom.getOrgFlow());
              cfg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
              c+=saveKqCfg(cfg);
          }
        }
        return c;
    }

    private int saveKqCfg(ResKgCfg cfg) {
        if(cfg!=null)
        {
            if(StringUtil.isNotBlank(cfg.getCfgFlow()))
            {
                GeneralMethod.setRecordInfo(cfg,false);
                return kgCfgMapper.updateByPrimaryKeySelective(cfg);
            }else{
                cfg.setCfgFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(cfg,true);
                return kgCfgMapper.insertSelective(cfg);

            }
        }
        return 0;
    }

    @Override
    public ResKgCfg getKqCfg(String lessOrGreater, String orgFlow, String doctorTypeId) {
        if(StringUtil.isNotBlank(orgFlow)&&StringUtil.isNotBlank(lessOrGreater))
        { ResKgCfgExample example=new ResKgCfgExample();
            example.createCriteria().andOrgFlowEqualTo(orgFlow) .andLessOrGreaterEqualTo(lessOrGreater)
            .andDoctorTypeIdEqualTo(doctorTypeId);
            List<ResKgCfg> cfgs=kgCfgMapper.selectByExample(example);
            if(cfgs!=null&&cfgs.size()>0)
            {
                return cfgs.get(0);
            }
        }
        return null;
    }

    @Override
    public int checkTime(String recordFlow, String doctorFlow, String startDate, String endDate, String id) {
        return resDoctorKqExtMapper.checkTime( recordFlow,doctorFlow,  startDate,  endDate,  id);
    }

    @Override
    public double readAllIntervalDays(String recordFlow, String doctorFlow, String startDate, String endDate, String id) {
        return resDoctorKqExtMapper.readAllIntervalDays( recordFlow,doctorFlow,  startDate,  endDate,  id);
    }

    @Override
    public List<Map<String, String>> getSigninListForMany(Map<String, Object> param) {
        return resDoctorKqExtMapper.getSigninListForMany(param);
    }

    @Override
    public List<ResDoctorSignin> queryDoctorSignins(ResDoctorSignin signin) {
        ResDoctorSigninExample example=new ResDoctorSigninExample();
        ResDoctorSigninExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(signin.getSigninDate())){
            criteria.andSigninDateEqualTo(signin.getSigninDate());
        }
        if(StringUtil.isNotBlank(signin.getTeacherUserFlow())){
            criteria.andTeacherUserFlowEqualTo(signin.getTeacherUserFlow());
        }
        if(StringUtil.isNotBlank(signin.getDoctorFlow())){
            criteria.andDoctorFlowEqualTo(signin.getDoctorFlow());
        }
        example.setOrderByClause("DOCTOR_FLOW,RESULT_FLOW,SIGNIN_TIME ");
        return signinMapper.selectByExample(example);
    }
    @Override
    public List<Map<String, Object>> getKqStatistics(Map<String, Object> paramMap) {
        return resDoctorKqExtMapper.getKqStatistics(paramMap);
    }

    @Override
    public List<ResDoctorKq> kqStatisticsDetail(ResDoctorKq kq) {
        return resDoctorKqExtMapper.kqStatisticsDetail(kq);
    }

    @Override
    public List<Map<String,String>> searchDoctorKqList(ResDoctorKq kq) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("orgFlow",kq.getOrgFlow());
        paramMap.put("doctorFlow",kq.getDoctorFlow());
        paramMap.put("kqTypeId",kq.getKqTypeId());
        if(StringUtil.isNotBlank(kq.getTypeId())){
            paramMap.put("typeId",kq.getTypeId());
        }
        if(StringUtil.isNotBlank(kq.getSchDeptFlow())){
            paramMap.put("schDeptFlow",kq.getSchDeptFlow());
        }
        if(StringUtil.isNotBlank(kq.getAuditStatusId())){
            paramMap.put("auditStatusId",kq.getAuditStatusId());
        }
        return resDoctorKqExtMapper.searchDoctorKqList(paramMap);
    }

    @Override
    public List<ResDoctorSchProcess> searchProcessByDoctorFlow(String userFlow) {
        ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
        ResDoctorSchProcessExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        if(StringUtil.isNotBlank(userFlow)){
            criteria.andUserFlowEqualTo(userFlow);
        }
        example.setOrderByClause("SCH_START_DATE DESC");
        return doctorSchProcessMapper.selectByExample(example);
    }

    @Override
    public int saveKqLog(ResDoctorKqLog kqLog) {
        if(StringUtil.isNotBlank(kqLog.getRecordFlow())) {
            GeneralMethod.setRecordInfo(kqLog,false);
            return kqLogMapper.updateByPrimaryKeySelective(kqLog);
        }else{
            kqLog.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(kqLog,true);
            return kqLogMapper.insertSelective(kqLog);

        }
    }

    @Override
    public List<ResDoctorKqLog> searchKqLogList(String kqRecordFlow, String typeId) {
        ResDoctorKqLogExample example = new ResDoctorKqLogExample();
        ResDoctorKqLogExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        if(StringUtil.isNotBlank(kqRecordFlow)){
            criteria.andKqRecordFlowEqualTo(kqRecordFlow);
        }
        if(StringUtil.isNotBlank(typeId)){
            criteria.andTypeIdEqualTo(typeId);
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return kqLogMapper.selectByExample(example);
    }

    @Override
    public void updateKqLogsRecordStatusN(String kqRecordFlow, String typeId) {
        resDoctorKqExtMapper.updateKqLogsRecordStatusN(kqRecordFlow,typeId);
    }
}
