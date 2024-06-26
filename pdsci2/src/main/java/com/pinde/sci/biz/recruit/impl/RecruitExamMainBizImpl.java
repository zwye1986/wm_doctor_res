package com.pinde.sci.biz.recruit.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.recruit.IRecruitExamInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitExamMainBiz;
import com.pinde.sci.biz.recruit.IRecruitExamRoomInfoBiz;
import com.pinde.sci.biz.recruit.IRecruitInfoBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.RecruitExamMainMapper;
import com.pinde.sci.dao.base.RecruitInfoMapper;
import com.pinde.sci.dao.recruit.RecruitExamMainExtMapper;
import com.pinde.sci.enums.recruit.RecruitStatusEnum;
import com.pinde.sci.form.recruit.ExamInfoFlowForm;
import com.pinde.sci.form.recruit.ExamInfoForm;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class RecruitExamMainBizImpl implements IRecruitExamMainBiz {

    @Autowired
    private RecruitExamMainExtMapper recruitExamMainExtMapper;
    @Autowired
    private RecruitExamMainMapper recruitExamMainMapper;

    @Autowired
    private RecruitInfoMapper recruitInfoMapper;
    @Autowired
    private IRecruitInfoBiz recruitInfoBiz;

    @Autowired
    private IRecruitExamRoomInfoBiz recruitExamRoomInfoBiz;
    @Autowired
    private IRecruitExamInfoBiz recruitExamInfoBiz;

    @Override
    public List<RecruitExamMain> searchAll(RecruitExamMain recruitExamMain) {
        RecruitExamMainExample example = new RecruitExamMainExample();
        RecruitExamMainExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(recruitExamMain.getOrgFlow()))
        {
            criteria.andOrgFlowEqualTo(recruitExamMain.getOrgFlow());
        }
        if(StringUtil.isNotBlank(recruitExamMain.getIsPublish()))
        {
            criteria.andIsPublishEqualTo(recruitExamMain.getIsPublish());
        }
        if(StringUtil.isNotBlank(recruitExamMain.getRecruitYear()))
        {
            criteria.andRecruitYearEqualTo(recruitExamMain.getRecruitYear());
        }
        example.setOrderByClause("start_time");
        return recruitExamMainMapper.selectByExample(example);
    }

    @Override
    public RecruitExamMain searchExamMainByRecruitYear(String recruitYear) {
        RecruitExamMainExample example = new RecruitExamMainExample();
        RecruitExamMainExample.Criteria criteria = example.createCriteria();
        criteria.andRecruitYearEqualTo(recruitYear);
        criteria.andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        List<RecruitExamMain> recruitExamMains = recruitExamMainMapper.selectByExample(example);
        if (recruitExamMains != null && recruitExamMains.size() > 0){
            return recruitExamMains.get(0);
        }else {
            return null;
        }


    }

    @Override
    public Integer addRecExamMainByCfgInfo(RecruitCfgInfo searchResult) {
        RecruitExamMain recruitExamMain = new RecruitExamMain();
        recruitExamMain.setMainFlow(PkUtil.getUUID());
        recruitExamMain.setRecruitYear(searchResult.getRecruitYear());
        recruitExamMain.setCfgFlow(searchResult.getCfgFlow());
        recruitExamMain.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        recruitExamMain.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        recruitExamMain.setCreateTime(DateUtil.getCurrDateTime());
        recruitExamMain.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        return recruitExamMainMapper.insertSelective(recruitExamMain);
    }

    @Override
    public Integer updateExamMainByMainFlow(RecruitExamMain recruitExamMain) {
        recruitExamMain.setModifyTime(DateUtil.getCurrDateTime());
        recruitExamMain.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        return recruitExamMainMapper.updateByPrimaryKeySelective(recruitExamMain);
    }

    @Override
    public RecruitExamMain readByFlow(String mainFlow) {
        return recruitExamMainMapper.selectByPrimaryKey(mainFlow);
    }

    @Override
    public int saveExamMain(RecruitExamMain recruitExamMain) {
        if(StringUtil.isNotBlank(recruitExamMain.getMainFlow())){
            GeneralMethod.setRecordInfo(recruitExamMain,false);
            return recruitExamMainMapper.updateByPrimaryKeySelective(recruitExamMain);
        }else{
            recruitExamMain.setMainFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(recruitExamMain,true);
            return recruitExamMainMapper.insertSelective(recruitExamMain);
        }
    }

    @Override
    public int checkAuditingNum(String recruitYear, String orgFlow) {
        RecruitInfoExample example=new RecruitInfoExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andSessionNumberEqualTo(recruitYear).andOrgFlowEqualTo(orgFlow)
                .andAuditStatusIdEqualTo(RecruitStatusEnum.Passing.getId());
        return recruitInfoMapper.countByExample(example);
    }

    @Override
    public int getExamNum(String mainFlow) {
        return recruitExamMainExtMapper.getExamNum(mainFlow);
    }

    @Override
    public void delExamDetail(String mainFlow) {
        recruitExamMainExtMapper.delExamInfo(mainFlow);
        recruitExamMainExtMapper.delExamRoomInfo(mainFlow);
    }

    @Override
    public int checkExamTime(String mainFlow, String examFlow, String examStartTime, String examEndTime) {

        return recruitExamMainExtMapper.checkExamTime( mainFlow,  examFlow,  examStartTime,  examEndTime);
    }

    @Override
    public int checkInterviewTime(String mainFlow, String examFlow, String interviewStartTime, String interviewEndTime) {
        return recruitExamMainExtMapper.checkInterviewTime( mainFlow,  examFlow,  interviewStartTime,  interviewEndTime);
    }

    @Override
    public String saveExamInfo(ExamInfoForm form) {
        RecruitExamInfo examInfo=form;
        List<RecruitExamRoomInfo> roomInfos=form.getRoomInfos();
        Map<String,RecruitExamRoomInfo> roomMap=new HashMap<>();
        List<RecruitExamRoomInfo> olds=null;
        if(StringUtil.isNotBlank(examInfo.getExamFlow()))
        {
            olds=recruitExamRoomInfoBiz.readRoomInfosByExamFlow(examInfo.getExamFlow());
        }
        if(olds!=null)
        {
            for(RecruitExamRoomInfo roomInfo:olds)
            {
                roomMap.put(roomInfo.getRoomFlow(),roomInfo);
            }
        }
        int c=recruitExamInfoBiz.saveExamInfo(examInfo);
        if(c==0)
        {
            return GlobalConstant.SAVE_FAIL;
        }
        List<String> recordFlows=new ArrayList<>();
        if(roomInfos!=null)
        {
            for(RecruitExamRoomInfo roomInfo:roomInfos)
            {
                RecruitExamRoomInfo old=roomMap.get(roomInfo.getRoomFlow());
                if(old!=null)
                {
                    recordFlows.add(old.getRecordFlow());
                    roomInfo.setRecordFlow(old.getRecordFlow());
                }
                roomInfo.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                roomInfo.setExamFlow(examInfo.getExamFlow());
                recruitExamRoomInfoBiz.saveRoomInfo(roomInfo);
            }
        }
        if(olds!=null)
        {
            for(RecruitExamRoomInfo roomInfo:olds)
            {
                if(!recordFlows.contains(roomInfo.getRecordFlow())) {
                    roomInfo.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                    recruitExamRoomInfoBiz.saveRoomInfo(roomInfo);
                }
            }
        }
        return "";
    }

    @Override
    public int checkPassedNum(String recruitYear, String orgFlow) {
        RecruitInfoExample example=new RecruitInfoExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andSessionNumberEqualTo(recruitYear).andOrgFlowEqualTo(orgFlow)
                .andAuditStatusIdEqualTo(RecruitStatusEnum.Passed.getId());
        return recruitInfoMapper.countByExample(example);
    }

    @Override
    public RecruitExamMain readByCfgFlow(String cfgFlow, String orgFlow) {
        RecruitExamMainExample example=new RecruitExamMainExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andCfgFlowEqualTo(cfgFlow)
        .andOrgFlowEqualTo(orgFlow);
        List<RecruitExamMain> mains=recruitExamMainMapper.selectByExample(example);
        if(mains!=null&&mains.size()>0)
        {
            return mains.get(0);
        }
        return null;
    }
    @Override
    public List<RecruitInfo> readPassedRecruitInfos(String orgFlow, String recruitYear) {
        return recruitExamMainExtMapper.readPassedRecruitInfos(orgFlow,recruitYear);
    }

    @Override
    public List<ExamInfoFlowForm> readExamInfoFlowFormsByFlow(String mainFlow) {
        return recruitExamMainExtMapper.readExamInfoFlowFormsByFlow(mainFlow);
    }

    @Override
    public void fenpei(List<RecruitInfo> recruitInfos, List<ExamInfoFlowForm> flowForms) {
        List<ExamInfoFlowForm> flows=new ArrayList<>();
        for(ExamInfoFlowForm flowForm:flowForms)
        {
            int examNum=Integer.valueOf(flowForm.getExamNum());
            for(int i=1;i<=examNum;i++)
            {
                flows.add(flowForm);
            }
        }
        for(int j=0;j<recruitInfos.size();j++)
        {
            RecruitInfo recruitInfo=recruitInfos.get(j);
            ExamInfoFlowForm flowForm=flows.get(j);
            recruitInfo.setWriteExamFlag(GlobalConstant.FLAG_Y);
            recruitInfo.setMainFlow(flowForm.getMainFlow());
            recruitInfo.setExamFlow(flowForm.getExamFlow());
            recruitInfo.setExamRoomFlow(flowForm.getRecordFlow());
            recruitInfoBiz.saveRecruitInfo(recruitInfo);
        }
    }

    @Override
    public List<RecruitInfo> readMainRecruitInfos(String mainFlow) {
        RecruitInfoExample example=new RecruitInfoExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andMainFlowEqualTo(mainFlow);
        return recruitInfoMapper.selectByExample(example);
    }
}
