package com.pinde.res.biz.jswjw.impl;

import com.pinde.core.model.ResScheduleScore;
import com.pinde.core.model.ResScheduleScoreExample;
import com.pinde.core.model.SysUser;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jswjw.IResScheduleScoreBiz;
import com.pinde.sci.dao.base.ResScheduleScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor = Exception.class)
public class ResScheduleScoreBizImpl implements IResScheduleScoreBiz {
    @Autowired
    ResScheduleScoreMapper resScheduleScoreMapper;

    @Override
    public int saveSchedule(ResScheduleScore scheduleScore,String userFlow) {
        List<ResScheduleScore> scoreList = queryScheduleList(scheduleScore);
        if (scoreList.size() > 0) {
            ResScheduleScore resScheduleScore = scoreList.get(0);
            resScheduleScore.setScore(scheduleScore.getScore());
            resScheduleScore.setModifyUserFlow(userFlow);
            resScheduleScore.setModifyTime(DateUtil.getCurrDate());
            return resScheduleScoreMapper.updateByPrimaryKey(resScheduleScore);
        } else {
            scheduleScore.setCreateTime(DateUtil.getCurrDate());
            scheduleScore.setScheduleFlow(PkUtil.getUUID());
            scheduleScore.setCreateUserFlow(userFlow);
            scheduleScore.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            return resScheduleScoreMapper.insert(scheduleScore);
        }
    }

    @Override
    public int saveScheduleDetailed(ResScheduleScore scheduleScore,String userFlow) {
        List<ResScheduleScore> scoreList = queryScheduleList(scheduleScore);
        if (scoreList.size() > 0) {
            ResScheduleScore resScheduleScore = scoreList.get(0);
            resScheduleScore.setItemDetailed(scheduleScore.getItemDetailed());
            resScheduleScore.setModifyUserFlow(userFlow);
            resScheduleScore.setModifyTime(DateUtil.getCurrDate());
            return resScheduleScoreMapper.updateByPrimaryKey(resScheduleScore);
        } else {
            scheduleScore.setCreateTime(DateUtil.getCurrDate());
            scheduleScore.setEvaluationYear(DateUtil.getYear());
            scheduleScore.setScheduleFlow(PkUtil.getUUID());
            scheduleScore.setCreateUserFlow(userFlow);
            scheduleScore.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            return resScheduleScoreMapper.insert(scheduleScore);
        }
    }


    @Override
    public List<ResScheduleScore> queryScheduleList(ResScheduleScore scheduleScore) {
        ResScheduleScoreExample example = new ResScheduleScoreExample();
        ResScheduleScoreExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(scheduleScore.getScheduleFlow())) {
            criteria.andScheduleFlowEqualTo(scheduleScore.getScheduleFlow());
        }
        if (StringUtil.isNotBlank(scheduleScore.getSpeId())) {
            criteria.andSpeIdEqualTo(scheduleScore.getSpeId());
        }
        if (StringUtil.isNotBlank(scheduleScore.getOrgFlow())) {
            criteria.andOrgFlowEqualTo(scheduleScore.getOrgFlow());
        }

        if (StringUtil.isNotBlank(scheduleScore.getSubjectFlow())) {
            criteria.andSubjectFlowEqualTo(scheduleScore.getSubjectFlow());
        }
        if (StringUtil.isNotBlank(scheduleScore.getItemId())) {
            criteria.andItemIdEqualTo(scheduleScore.getItemId());
        }
        if (StringUtil.isNotBlank(scheduleScore.getItemName())) {
            criteria.andItemNameEqualTo(scheduleScore.getItemName());
        }
        if (StringUtil.isNotBlank(scheduleScore.getGrade())) {
            criteria.andGradeEqualTo(scheduleScore.getGrade());
        }
        if (StringUtil.isNotBlank(scheduleScore.getFileRoute())) {
            criteria.andFileRouteEqualTo(scheduleScore.getFileRoute());
        }
        if (StringUtil.isNotBlank(scheduleScore.getUserFlow())) {
            criteria.andUserFlowEqualTo(scheduleScore.getUserFlow());
        }
        return resScheduleScoreMapper.selectByExample(example);
    }

    @Override
    public List<ResScheduleScore> queryScheduleListNotItemName(ResScheduleScore scheduleScore) {
        ResScheduleScoreExample example = new ResScheduleScoreExample();
        ResScheduleScoreExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(scheduleScore.getScheduleFlow())) {
            criteria.andScheduleFlowEqualTo(scheduleScore.getScheduleFlow());
        }
        if (StringUtil.isNotBlank(scheduleScore.getSpeId())) {
            criteria.andSpeIdEqualTo(scheduleScore.getSpeId());
        }
        if (StringUtil.isNotBlank(scheduleScore.getOrgFlow())) {
            criteria.andOrgFlowEqualTo(scheduleScore.getOrgFlow());
        }
        if (StringUtil.isNotBlank(scheduleScore.getFileRoute())) {
            criteria.andFileRouteEqualTo(scheduleScore.getFileRoute());
        }
        if (StringUtil.isNotBlank(scheduleScore.getSubjectFlow())) {
            criteria.andSubjectFlowEqualTo(scheduleScore.getSubjectFlow());
        }
        if (StringUtil.isNotBlank(scheduleScore.getItemId())) {
            criteria.andItemIdEqualTo(scheduleScore.getItemId());
        }
        if (StringUtil.isNotBlank(scheduleScore.getItemName())) {
            criteria.andItemNameNotEqualTo(scheduleScore.getItemName());
        }
        if (StringUtil.isNotBlank(scheduleScore.getGrade())) {
            criteria.andGradeEqualTo(scheduleScore.getGrade());
        }
        if (StringUtil.isNotBlank(scheduleScore.getUserFlow())) {
            criteria.andUserFlowEqualTo(scheduleScore.getUserFlow());
        }
        return resScheduleScoreMapper.selectByExample(example);
    }

    @Override
    public List<ResScheduleScore> queryScheduleListTotalled(ResScheduleScore scheduleScore) {
        ResScheduleScoreExample example = new ResScheduleScoreExample();
        ResScheduleScoreExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(scheduleScore.getScheduleFlow())) {
            criteria.andScheduleFlowEqualTo(scheduleScore.getScheduleFlow());
        }
        if (StringUtil.isNotBlank(scheduleScore.getSpeId())) {
            criteria.andSpeIdEqualTo(scheduleScore.getSpeId());
        }
        if (StringUtil.isNotBlank(scheduleScore.getOrgFlow())) {
            criteria.andOrgFlowEqualTo(scheduleScore.getOrgFlow());
        }
        if (StringUtil.isNotBlank(scheduleScore.getFileRoute())) {
            criteria.andFileRouteEqualTo(scheduleScore.getFileRoute());
        }
        if (StringUtil.isNotBlank(scheduleScore.getSubjectFlow())) {
            criteria.andSubjectFlowEqualTo(scheduleScore.getSubjectFlow());
        }
        if (StringUtil.isNotBlank(scheduleScore.getItemId())) {
            criteria.andItemIdEqualTo(scheduleScore.getItemId());
        }
        if (StringUtil.isNotBlank(scheduleScore.getItemName())) {
            criteria.andItemNameEqualTo(scheduleScore.getItemName());
        }
        if (StringUtil.isNotBlank(scheduleScore.getGrade())) {
            criteria.andGradeEqualTo(scheduleScore.getGrade());
        }
        if (StringUtil.isNotBlank(scheduleScore.getScoreType())) {
            criteria.andScoreTypeEqualTo(scheduleScore.getScoreType());
        }
        if (StringUtil.isNotBlank(scheduleScore.getUserFlow())) {
            criteria.andUserFlowEqualTo(scheduleScore.getUserFlow());
        }
        return resScheduleScoreMapper.selectByExample(example);
    }

    @Override
    public Integer deleteScheduleList(ResScheduleScore resScheduleScore) {
        ResScheduleScoreExample example = new ResScheduleScoreExample();
        ResScheduleScoreExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotBlank(resScheduleScore.getSpeId())) {
            criteria.andSpeIdEqualTo(resScheduleScore.getSpeId());
        }
        if (StringUtil.isNotBlank(resScheduleScore.getOrgFlow())) {
            criteria.andOrgFlowEqualTo(resScheduleScore.getOrgFlow());
        }
        if (StringUtil.isNotBlank(resScheduleScore.getSubjectFlow())) {
            criteria.andSubjectFlowEqualTo(resScheduleScore.getSubjectFlow());
        }
        if (StringUtil.isNotBlank(resScheduleScore.getGrade())) {
            criteria.andGradeEqualTo(resScheduleScore.getGrade());
        }
        if (StringUtil.isNotBlank(resScheduleScore.getFileRoute())) {
            criteria.andFileRouteEqualTo(resScheduleScore.getFileRoute());
        }
        if (StringUtil.isNotBlank(resScheduleScore.getUserFlow())) {
            criteria.andUserFlowEqualTo(resScheduleScore.getUserFlow());
        }
        return resScheduleScoreMapper.deleteByExample(example);
    }
}

