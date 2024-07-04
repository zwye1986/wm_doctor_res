package com.pinde.res.biz.jszy.impl;

import com.pinde.app.common.GlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jszy.IJszyResLiveTrainingBiz;
import com.pinde.res.dao.jszy.ext.JszyRestarinNoticeExtMapper;
import com.pinde.sci.dao.base.ResTarinNoticeMapper;
import com.pinde.sci.dao.base.ResTrainingOpinionMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class JsyResLiveTrainingBizImpl implements IJszyResLiveTrainingBiz {
    @Autowired
    private ResTrainingOpinionMapper trainingOpinionMapper;
    @Autowired
    private ResTarinNoticeMapper resTarinNoticeMapper;
    @Autowired
    private JszyRestarinNoticeExtMapper resTarinNoticeExtMapper;

    @Override
    public ResTrainingOpinion read(String trainingOpinionFlow){
        ResTrainingOpinion trainingOpinion = null;
        if(StringUtil.isNotBlank(trainingOpinionFlow)){
            trainingOpinion = trainingOpinionMapper.selectByPrimaryKey(trainingOpinionFlow);
        }
        return trainingOpinion;
    }

    @Override
    public int edit(ResTrainingOpinion trainingOpinion,String userFlow) {
        if(trainingOpinion!=null){
            if(StringUtil.isNotBlank(trainingOpinion.getTrainingOpinionFlow())){
                setRecordInfo(trainingOpinion, false,userFlow);
                return trainingOpinionMapper.updateByPrimaryKeySelective(trainingOpinion);
            }else{
                trainingOpinion.setTrainingOpinionFlow(PkUtil.getUUID());
                setRecordInfo(trainingOpinion, true,userFlow);
                return trainingOpinionMapper.insertSelective(trainingOpinion);
            }
        }
        return 0;
    }

    @Override
    public List<ResTrainingOpinion> readByOpinionUserFlow(String opinionUserFlow) {
        ResTrainingOpinionExample example = new ResTrainingOpinionExample();
        if(StringUtil.isNotBlank(opinionUserFlow)){
            example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOpinionUserFlowEqualTo(opinionUserFlow);
            example.setOrderByClause("EVA_TIME DESC");
        }
        return trainingOpinionMapper.selectByExample(example);
    }

    @Override
    public List<ResTrainingOpinion> searchByOpinionUserContentAndReplayStatus(String opinionUserContent, String replayStatus,String orgFlow) {
        ResTrainingOpinionExample example = new ResTrainingOpinionExample();
        ResTrainingOpinionExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(opinionUserContent)){
            criteria.andOpinionUserContentLike("%"+opinionUserContent+"%");
        }
        if("Y".equals(replayStatus)){
            criteria.andReplyTimeIsNull();
        }
        if(StringUtil.isNotBlank(orgFlow)){
            criteria.andOrgFlowEqualTo(orgFlow);
        }
        example.setOrderByClause("EVA_TIME DESC");
        return trainingOpinionMapper.selectByExample(example);
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
    public int edit(ResTarinNotice tarinNotice,String userFlow) {
        if(tarinNotice!=null){
            if(StringUtil.isNotBlank(tarinNotice.getRecordFlow())){
                setRecordInfo(tarinNotice, false,userFlow);
                return resTarinNoticeMapper.updateByPrimaryKeySelective(tarinNotice);
            }else{
                tarinNotice.setRecordFlow(PkUtil.getUUID());
                setRecordInfo(tarinNotice, true,userFlow);
                return resTarinNoticeMapper.insertSelective(tarinNotice);
            }
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public List<ResTarinNotice> searchByTitleOrgFlow(String resNoticeTitle, String orgFlow) {
        ResTarinNoticeExample example = new ResTarinNoticeExample();
        ResTarinNoticeExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(orgFlow)){
            criteria.andOrgFlowEqualTo(orgFlow);
        }
        if(StringUtil.isNotBlank(resNoticeTitle)){
            criteria.andResNoticeTitleLike("%"+resNoticeTitle+"%");
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return resTarinNoticeMapper.selectByExampleWithBLOBs(example);
    }
    @Override
    public List<ResTarinNotice> searchByTitleOrgFlowAndWorkOrgId(String resNoticeTitle, String orgFlow, ResDoctor doctor) {
        return resTarinNoticeExtMapper.searchByTitleOrgFlowAndWorkOrgId(resNoticeTitle, orgFlow, doctor);
    }
    public static void setRecordInfo(Object obj, boolean isAdd,String userFlow) {
        Class<?> clazz = obj.getClass();
        try {
            if (isAdd) {
                Method setRecordStatusMethod = clazz.getMethod("setRecordStatus", String.class);
                setRecordStatusMethod.invoke(obj, GlobalConstant.RECORD_STATUS_Y);
                Method setCreateTime = clazz.getMethod("setCreateTime", String.class);
                setCreateTime.invoke(obj, DateUtil.getCurrDateTime());
                Method setCreateUserFlow = clazz.getMethod("setCreateUserFlow", String.class);
                if (userFlow != null) {
                    setCreateUserFlow.invoke(obj, userFlow);
                }
            }
            Method setModifyTime = clazz.getMethod("setModifyTime", String.class);
            setModifyTime.invoke(obj, DateUtil.getCurrDateTime());
            Method setModifyUserFlow = clazz.getMethod("setModifyUserFlow", String.class);
            if (userFlow != null) {
                setModifyUserFlow.invoke(obj, userFlow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
