package com.pinde.sci.biz.res.impl;

import com.pinde.core.model.*;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.res.IResDoctorRecruitBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.JsresExamSignupLogMapper;
import com.pinde.sci.dao.base.JsresExamSignupMapper;
import com.pinde.sci.dao.base.ResDoctorRecruitMapper;
import com.pinde.sci.dao.base.ResScoreMapper;
import com.pinde.sci.dao.res.ResDoctorRecruitExtMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.JsresExamSignup;
import com.pinde.sci.model.mo.JsresExamSignupExample;
import com.pinde.sci.model.mo.ResScore;
import com.pinde.sci.model.mo.ResScoreExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class ResDoctorRecruitBizImpl implements IResDoctorRecruitBiz {
    @Autowired
    private JsresExamSignupMapper jsresExamSignupMapper;
    @Autowired
    private ResDoctorRecruitMapper doctorRecruitMapper;
    @Autowired
    private ResDoctorRecruitExtMapper doctorRecruitExtMapper;
    @Autowired
    private IMsgBiz msgBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private ResScoreMapper resScoreMapper;
    @Autowired
    private JsresExamSignupLogMapper jsresExamSignupLogMapper;

    @Override
    public ResDoctorRecruit getNewRecruit(String doctorFlow) {
        if (StringUtil.isNotBlank(doctorFlow)) {
            ResDoctorRecruitExample example = new ResDoctorRecruitExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andDoctorFlowEqualTo(doctorFlow).andAuditStatusIdEqualTo(com.pinde.core.common.enums.ResBaseStatusEnum.Passed.getId());
            example.setOrderByClause("MODIFY_TIME DESC");
            List<com.pinde.core.model.ResDoctorRecruit> recruitList = doctorRecruitMapper.selectByExample(example);
            if (recruitList != null && !recruitList.isEmpty()) {
                return recruitList.get(0);
            }
        }
        return null;
    }

    @Override
    public void noticeRetest(String recruitFlow, String retestNotice) {
        String content = retestNotice;
        ResDoctorRecruitWithBLOBs recruit = new ResDoctorRecruitWithBLOBs();
        recruit.setRecruitFlow(recruitFlow);
        recruit.setRetestFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
        recruit.setRetestNotice(retestNotice);
        editDoctorRecruit(recruit);
        ResDoctorRecruit rdr = readResDoctorRecruit(recruitFlow);
        SysUser exitUser = userBiz.readSysUser(rdr.getDoctorFlow());
        String title = InitConfig.getSysCfg("res_retest_notice_email_title");
        this.msgBiz.addEmailMsg(exitUser.getUserEmail(), title, content);
    }

    @Override
    public void noticeBackOpt(String recruitFlow, String flag) {
        ResDoctorRecruitWithBLOBs recruit = new ResDoctorRecruitWithBLOBs();
        recruit.setRecruitFlow(recruitFlow);
        if("fs".equals(flag)){
            recruit.setRetestFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
        }
        if("lq".equals(flag)){
            recruit.setAdmitFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
            recruit.setRecruitFlag("");
        }
        editDoctorRecruit(recruit);
    }

    @Override
    public int editDoctorRecruit(ResDoctorRecruitWithBLOBs recruit) {
        if (recruit != null) {
            if (StringUtil.isNotBlank(recruit.getRecruitFlow())) {
                GeneralMethod.setRecordInfo(recruit, false);
                return doctorRecruitMapper.updateByPrimaryKeySelective(recruit);
            } else {
                recruit.setRecruitFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(recruit, true);
                return doctorRecruitMapper.insertSelective(recruit);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    @Override
    public ResDoctorRecruit readResDoctorRecruit(String recruitFlow) {
        return this.doctorRecruitMapper.selectByPrimaryKey(recruitFlow);
    }


    @Override
    public int updateDocrecruit(com.pinde.core.model.ResDoctorRecruit recruit) {
        int result = 0;
        if (recruit != null) {
            result = doctorRecruitMapper.updateByPrimaryKey(recruit);
        }
        return result;
    }

    @Override
    public List<com.pinde.core.model.ResDoctorRecruit> findResDoctorRecruits(String year,
                                                        String doctorFlow) {
        ResDoctorRecruit docRecruit = new ResDoctorRecruit();
        docRecruit.setRecruitYear(year);
        docRecruit.setDoctorFlow(doctorFlow);
        docRecruit.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
        ResDoctorRecruitExample example = createResDoctorRecruitExample(docRecruit);
        example.setOrderByClause("CREATE_TIME DESC");
        List<com.pinde.core.model.ResDoctorRecruit> recruits = this.doctorRecruitMapper.selectByExample(example);
        return recruits;

    }

    private ResDoctorRecruitExample createResDoctorRecruitExample(ResDoctorRecruit docRecruit) {
        ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        if (docRecruit != null) {
            ResDoctorRecruitExample.Criteria criteria = example.createCriteria();
            if (StringUtil.isNotBlank(docRecruit.getDoctorFlow())) {
                criteria.andDoctorFlowEqualTo(docRecruit.getDoctorFlow());
            }
            if (StringUtil.isNotBlank(docRecruit.getRecordStatus())) {
                criteria.andRecordStatusEqualTo(docRecruit.getRecordStatus());
            }
            if (StringUtil.isNotBlank(docRecruit.getRecruitYear())) {
                criteria.andRecruitYearEqualTo(docRecruit.getRecruitYear());
            }
        }
        return example;
    }

    @Override
    public List<com.pinde.core.model.ResDoctorRecruit> searchDoctorRecruit(com.pinde.core.model.ResDoctorRecruit recruit) {
        ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(recruit.getDoctorFlow())) {
            criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
        }
        if (StringUtil.isNotBlank(recruit.getOrgFlow())) {
            criteria.andOrgFlowEqualTo(recruit.getOrgFlow());
        }
        if (StringUtil.isNotBlank(recruit.getSpeId())) {
            criteria.andSpeIdEqualTo(recruit.getSpeId());
        }
        if (StringUtil.isNotBlank(recruit.getRecruitYear())) {
            criteria.andRecruitYearEqualTo(recruit.getRecruitYear());
        }
        if (StringUtil.isNotBlank(recruit.getRecruitFlag())) {
            criteria.andRecruitFlagEqualTo(recruit.getRecruitFlag());
        }
        example.setOrderByClause("EXAM_RESULT desc");
        return doctorRecruitMapper.selectByExample(example);
    }

    @Override
    public List<com.pinde.core.model.ResDoctorRecruit> searchDoctorRecruits(com.pinde.core.model.ResDoctorRecruit recruit) {
        ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(recruit.getDoctorFlow())) {
            criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
        }

        if (StringUtil.isNotBlank(recruit.getAuditStatusId())) {
            criteria.andAuditStatusIdEqualTo(recruit.getAuditStatusId());
        }

        example.setOrderByClause("create_time desc");
        return doctorRecruitMapper.selectByExample(example);
    }

    @Override
    public List<com.pinde.core.model.ResDoctorRecruit> searchRecruitByDoctor(String doctorFlow) {
        if (StringUtil.isNotBlank(doctorFlow)) {
            ResDoctorRecruitExample example = new ResDoctorRecruitExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andDoctorFlowEqualTo(doctorFlow);
            return doctorRecruitMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public ResDoctorRecruit searchRecruitByResDoctor(com.pinde.core.model.ResDoctorRecruit recruit) {
        ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(null!=recruit){
            if(StringUtil.isNotBlank(recruit.getDoctorFlow())){
                criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
            }
            if(StringUtil.isNotBlank(recruit.getOrgFlow())){
                criteria.andOrgFlowEqualTo(recruit.getOrgFlow());
                criteria.andJointOrgFlowEqualTo(recruit.getOrgFlow());
            }
            if(StringUtil.isNotBlank(recruit.getSpeId())){
                criteria.andSpeIdEqualTo(recruit.getSpeId());
            }
            if(StringUtil.isNotBlank(recruit.getAuditStatusId())){
                criteria.andAuditStatusIdEqualTo(recruit.getAuditStatusId());
            }
            if(StringUtil.isNotBlank(recruit.getSessionNumber())){
                criteria.andSessionNumberEqualTo(recruit.getSessionNumber());
            }
            if(StringUtil.isNotBlank(recruit.getCatSpeId())){
                criteria.andCatSpeIdEqualTo(recruit.getCatSpeId());
            }
            if(StringUtil.isNotBlank(recruit.getRecruitYear())){
                criteria.andRecruitYearEqualTo(recruit.getRecruitYear());
            }
            example.setOrderByClause("CREATE_TIME DESC");
            List<com.pinde.core.model.ResDoctorRecruit> list = doctorRecruitMapper.selectByExample(example);
            if(null!=list&&list.size()>0){
                return list.get(0);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    @Override
    public List<Map<String, String>> statisticDoctorCountMap(Map<String, Object> paramMap) {
        return doctorRecruitExtMapper.doctorCounMap(paramMap);
    }

    @Override
    public List<Map<String, Object>> getCurrDocDetails(Map<String, Object> paramMap) {
        return doctorRecruitExtMapper.getCurrDocDetails(paramMap);
    }

    @Override
    public ResDoctorRecruit readResDoctorRecruitBySessionNumber(String doctorFlow, String sessionNumber) {

        if (StringUtil.isNotBlank(doctorFlow)) {
            ResDoctorRecruitExample example = new ResDoctorRecruitExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                    .andDoctorFlowEqualTo(doctorFlow).andAuditStatusIdEqualTo(com.pinde.core.common.enums.ResBaseStatusEnum.Passed.getId())
            .andSessionNumberEqualTo(sessionNumber);
            example.setOrderByClause("MODIFY_TIME DESC");
            List<com.pinde.core.model.ResDoctorRecruit> recruitList = doctorRecruitMapper.selectByExample(example);
            if (recruitList != null && !recruitList.isEmpty()) {
                return recruitList.get(0);
            }
        }
        return null;
    }

    @Override
    public List<JsresExamSignup> getSignUpListByYear(String year, String doctorFlow, String typeId) {
        JsresExamSignupExample examSignupExample=new JsresExamSignupExample();
        examSignupExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andDoctorFlowEqualTo(doctorFlow).andSignupYearEqualTo(year).andSignupTypeIdEqualTo(typeId);

        List<JsresExamSignup> recruitList = jsresExamSignupMapper.selectByExample(examSignupExample);

        return recruitList;
    }

    @Override
    public List<JsresExamSignup> readDoctorExanSignUps( String doctorFlow, String typeId) {
        JsresExamSignupExample examSignupExample=new JsresExamSignupExample();
        JsresExamSignupExample.Criteria criteria = examSignupExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow);
        if(StringUtil.isNotEmpty(typeId)){
            criteria.andSignupTypeIdEqualTo(typeId);
        }
        examSignupExample.setOrderByClause("Signup_year desc");
        List<JsresExamSignup> recruitList = jsresExamSignupMapper.selectByExample(examSignupExample);
        return recruitList;
    }

    @Override
    public int saveSignUp(JsresExamSignup signup) {
        if(StringUtil.isBlank(signup.getSignupFlow()))
        {
            signup.setSignupFlow(PkUtil.getGUID());
            GeneralMethod.setRecordInfo(signup,true);
            return jsresExamSignupMapper.insertSelective(signup);
        }else {
            GeneralMethod.setRecordInfo(signup,false);
            return jsresExamSignupMapper.updateByPrimaryKeySelective(signup);
        }
    }

    @Override
    public List<Map<String, Object>> queryExamSignUpList(Map<String, Object> param) {
        return doctorRecruitExtMapper.queryExamSignUpList(param);
    }

    @Override
    public JsresExamSignup readExamSignByFlow(String signupFlow) {
        return jsresExamSignupMapper.selectByPrimaryKey(signupFlow);
    }

    //获取医师的所有补考记录
    @Override
    public List<JsresExamSignup> readDoctorExanSignUps(String doctorFlow) {
        JsresExamSignupExample examSignupExample = new JsresExamSignupExample();
        examSignupExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andDoctorFlowEqualTo(doctorFlow);
        examSignupExample.setOrderByClause("CREATE_TIME desc");
        List<JsresExamSignup> recruitList = jsresExamSignupMapper.selectByExample(examSignupExample);
        return recruitList;
    }

    //获取所有的成绩记录
    @Override
    public List<ResScore> selectAllScore(String userFlow, String recruitFlow) {
        ResScoreExample example = new ResScoreExample();
        ResScoreExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        if (StringUtil.isNotBlank(userFlow)) {
            criteria.andDoctorFlowEqualTo(userFlow);
        }
        if (StringUtil.isNotBlank(recruitFlow)) {
            criteria.andRecruitFlowEqualTo(recruitFlow);
        }
        example.setOrderByClause("SCORE_PHASE_ID DESC");
        return resScoreMapper.selectByExample(example);
    }

    @Override
    public JsresExamSignup readByFlow(String signupFlow) {
        return jsresExamSignupMapper.selectByPrimaryKey(signupFlow);
    }

    //保存补考审核记录
    @Override
    public int saveChargeAudit(JsresExamSignup signup, JsresExamSignupLog log) {
        saveSignUp(signup);
        saveSignUpLog(log);
        return 1;
    }

    //获取补考审核的详细记录
    @Override
    public List<JsresExamSignupLog> getAuditLogsBySignupFlow(String signupFlow) {
        JsresExamSignupLogExample example = new JsresExamSignupLogExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSignupFlowEqualTo(signupFlow);
        example.setOrderByClause("create_time desc");
        return jsresExamSignupLogMapper.selectByExample(example);
    }

    //批量保存补考审核记录
    @Override
    public int saveBatchAudit(List<JsresExamSignup> signups, List<JsresExamSignupLog> logs) {
        for (JsresExamSignup signup : signups) {
            saveSignUp(signup);
        }
        for (JsresExamSignupLog log : logs) {
            saveSignUpLog(log);
        }
        return 1;
    }

    private int saveSignUpLog(JsresExamSignupLog log) {
        if (StringUtil.isNotBlank(log.getRecordFlow())) {
            GeneralMethod.setRecordInfo(log, false);
            return jsresExamSignupLogMapper.updateByPrimaryKeySelective(log);
        } else {
            log.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(log, true);
            return jsresExamSignupLogMapper.insertSelective(log);
        }
    }
}
 