package com.pinde.sci.biz.res.impl;

import com.pinde.core.model.SysOrg;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResLiveTrainingBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.ResTarinNoticeMapper;
import com.pinde.sci.dao.base.ResTrainingOpinionMapper;
import com.pinde.sci.dao.res.ResTrainNoticeExtMapper;
import com.pinde.sci.dao.res.ResTrainingOpinionExtMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class ResLiveTrainingBizImpl implements IResLiveTrainingBiz{
    @Autowired
    private ResTrainingOpinionMapper trainingOpinionMapper;
    @Autowired
    private ResTarinNoticeMapper resTarinNoticeMapper;
    @Autowired
    private ResTrainNoticeExtMapper noticeExtMapper;
    @Autowired
    private ResTrainingOpinionExtMapper trainingOpinionExtMapper;

    @Override
    public ResTrainingOpinion read(String trainingOpinionFlow){
        ResTrainingOpinion trainingOpinion = null;
        if(StringUtil.isNotBlank(trainingOpinionFlow)){
            trainingOpinion = trainingOpinionMapper.selectByPrimaryKey(trainingOpinionFlow);
        }
        return trainingOpinion;
    }

    @Override
    public int edit(ResTrainingOpinion trainingOpinion) {
        if(trainingOpinion!=null){
            if(StringUtil.isNotBlank(trainingOpinion.getTrainingOpinionFlow())){
                GeneralMethod.setRecordInfo(trainingOpinion, false);
                return trainingOpinionMapper.updateByPrimaryKeySelective(trainingOpinion);
            }else{
                trainingOpinion.setTrainingOpinionFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(trainingOpinion, true);
                return trainingOpinionMapper.insertSelective(trainingOpinion);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    @Override
    public List<ResTrainingOpinion> readByOpinionUserFlow(String opinionUserFlow) {
        ResTrainingOpinionExample example = new ResTrainingOpinionExample();
        if(StringUtil.isNotBlank(opinionUserFlow)){
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOpinionUserFlowEqualTo(opinionUserFlow);
            example.setOrderByClause("EVA_TIME DESC");
        }
        return trainingOpinionMapper.selectByExample(example);
    }

    @Override
    public List<ResTrainingOpinion> searchByOpinionUserContentAndReplayStatus(String opinionUserContent, String replayStatus,String orgFlow) {
        ResTrainingOpinionExample example = new ResTrainingOpinionExample();
        ResTrainingOpinionExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(opinionUserContent)){
            criteria.andOpinionUserContentLike("%"+opinionUserContent+"%");
        }
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(replayStatus)) {
            criteria.andReplyTimeIsNull();
        }
        if(StringUtil.isNotBlank(orgFlow)){
            criteria.andOrgFlowEqualTo(orgFlow);
        }
        example.setOrderByClause("EVA_TIME DESC");
        return trainingOpinionMapper.selectByExample(example);
    }

    @Override
    public List<ResTrainingOpinion> searchOpinionByUni4hb(Map<String, Object> paramMap) {
        return trainingOpinionExtMapper.searchOpinionByUni4hb(paramMap);
    }

    @Override
    public ResTarinNotice readNotice(String recordFlow) {
        ResTarinNotice resTarinNotice = null;
        if(StringUtil.isNotBlank(recordFlow)){
            resTarinNotice = resTarinNoticeMapper.selectByPrimaryKey(recordFlow);
        }
        return resTarinNotice;
    }


    @Override
    public int edit(ResTarinNotice tarinNotice) {
        if(tarinNotice!=null){
            if(StringUtil.isNotBlank(tarinNotice.getRecordFlow())){
                GeneralMethod.setRecordInfo(tarinNotice, false);
                return resTarinNoticeMapper.updateByPrimaryKeySelective(tarinNotice);
            }else{
                tarinNotice.setRecordFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(tarinNotice, true);
                return resTarinNoticeMapper.insertSelective(tarinNotice);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    @Override
    public List<ResTarinNotice> searchByTitleOrgFlow(String resNoticeTitle, String orgFlow,String roleFlag) {
        if("doctor".equals(roleFlag))
        {
            ResTarinNoticeExample example = new ResTarinNoticeExample();
            ResTarinNoticeExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            ResTarinNoticeExample.Criteria criteria2 = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            if(StringUtil.isNotBlank(orgFlow)){
                criteria.andOrgFlowEqualTo(orgFlow);
            }
            criteria2.andOrgFlowIsNull();
            if(StringUtil.isNotBlank(resNoticeTitle)){
                criteria.andResNoticeTitleLike("%"+resNoticeTitle+"%");
                criteria2.andResNoticeTitleLike("%"+resNoticeTitle+"%");
            }
            example.or(criteria2);
            example.setOrderByClause("CREATE_TIME DESC");
            return resTarinNoticeMapper.selectByExampleWithBLOBs(example);

        }else {
            ResTarinNoticeExample example = new ResTarinNoticeExample();
            ResTarinNoticeExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            if (StringUtil.isNotBlank(orgFlow)) {
                criteria.andOrgFlowEqualTo(orgFlow);
            } else {
                criteria.andOrgFlowIsNull();
            }
            if (StringUtil.isNotBlank(resNoticeTitle)) {
                criteria.andResNoticeTitleLike("%" + resNoticeTitle + "%");
            }
            example.setOrderByClause("CREATE_TIME DESC");
            return resTarinNoticeMapper.selectByExampleWithBLOBs(example);
        }
    }

    @Override
    public List<ResTarinNotice> searchByTitleOrgFlow4University(Map<String, Object> paramMap) {
        return noticeExtMapper.searchNotices4University(paramMap);
    }

    @Override
    public List<ResTrainingOpinion> searchOpinionByUni(String opinionUserContent, String orgFlow, SysOrg org) {
        if(org==null||(StringUtil.isBlank(org.getSendSchoolId())&&StringUtil.isBlank(org.getSendSchoolName())))
        {
            return null;
        }
        return trainingOpinionExtMapper.searchOpinionByUni(opinionUserContent,  orgFlow, org);
    }

    @Override
    public List<ResTarinNotice> searchByTitleOrgFlowAndSchool(String resNoticeTitle, String orgFlow, ResDoctor doctor) {
        return noticeExtMapper.searchByTitleOrgFlowAndSchool( resNoticeTitle,  orgFlow,  doctor) ;
    }

    @Override
    public List<ResTrainingOpinion> searchByOpinionUserContentAndReplayStatusNew(String opinionUserContent, String replayStatus, String orgFlow, String resTrainingSpeId) {
        return trainingOpinionExtMapper.searchByOpinionUserContentAndReplayStatusNew(opinionUserContent,  replayStatus, orgFlow,resTrainingSpeId);
    }


}
