package com.pinde.sci.biz.srm.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.IAchScoreBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.srm.AchScoreExtMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class AchScoreBiz implements IAchScoreBiz {
    @Autowired
    private SrmAchScoreMapper srmAchScoreMapper;
    @Autowired
    private AchScoreExtMapper achScoreExtMapper;
    @Autowired
    private SrmAchScoreTypeMapper scoreTypeMapper;
    @Autowired
    private SrmAchBookAuthorMapper bookAuthorMapper;
    @Autowired
    private SrmAchSatAuthorMapper satAuthorMapper;
    @Autowired
    private SrmAchPatentAuthorMapper patenAuthorMapper;
    @Autowired
    private SrmAchThesisAuthorMapper thesisAuthorMapper;
    @Autowired
    private PubProjAuthorMapper projAuthorMapper;

    @Override
    public int saveScoreSet(SrmAchScore score) {
        if (StringUtil.isNotBlank(score.getScoreFlow())) {
            List<String> scoreFlowList = new ArrayList<>();
            scoreFlowList.add(score.getScoreFlow());
            //该项积分是否已被使用
            int a = this.achScoreIsUse(scoreFlowList);
            if (a > 0) {
                //更新所有使用该积分的项目（除已归档的）
                this.updadeScoreUsedAuthor(score, null,null);
            }
            GeneralMethod.setRecordInfo(score, false);
            return srmAchScoreMapper.updateByPrimaryKeySelective(score);
        } else {
            GeneralMethod.setRecordInfo(score, true);
            score.setScoreFlow(PkUtil.getUUID());
            return srmAchScoreMapper.insert(score);
        }
    }

    @Override
    public int delScoreSet(String scoreFlow) {
        if (StringUtil.isNotBlank(scoreFlow)) {
            SrmAchScore srmAchScore = new SrmAchScore();
            srmAchScore.setScoreFlow(scoreFlow);
            srmAchScore.setRecordStatus("N");
            return srmAchScoreMapper.updateByPrimaryKeySelective(srmAchScore);
        }
        return 0;
    }

    @Override
    public int delScoreSets(List<String> scoreFlowList) {
        if (null != scoreFlowList && scoreFlowList.size() > 0) {
            SrmAchScore score = new SrmAchScore();
            score.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            SrmAchScoreExample example = new SrmAchScoreExample();
            SrmAchScoreExample.Criteria criteria = example.createCriteria();
            criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            criteria.andScoreFlowIn(scoreFlowList);
            return srmAchScoreMapper.updateByExampleSelective(score, example);
        }
        return 0;
    }

    @Override
    public int delScoreTypes(List<String> typeFlowList) {
        if (null != typeFlowList && typeFlowList.size() > 0) {
            SrmAchScoreType scoreType = new SrmAchScoreType();
            scoreType.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            SrmAchScoreTypeExample example = new SrmAchScoreTypeExample();
            SrmAchScoreTypeExample.Criteria criteria = example.createCriteria();
            criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            criteria.andTypeFlowIn(typeFlowList);
            return scoreTypeMapper.updateByExampleSelective(scoreType, example);
        }
        return 0;
    }

    @Override
    public SrmAchScoreType readScoreType(String typeFlow) {
        return scoreTypeMapper.selectByPrimaryKey(typeFlow);
    }

    @Override
    public int saveScoreType(SrmAchScoreType scoreType) {
        if (StringUtil.isNotBlank(scoreType.getTypeFlow())) {
            GeneralMethod.setRecordInfo(scoreType, false);
            return scoreTypeMapper.updateByPrimaryKeySelective(scoreType);
        } else {
            GeneralMethod.setRecordInfo(scoreType, true);
            scoreType.setTypeFlow(PkUtil.getUUID());
            return scoreTypeMapper.insert(scoreType);
        }
    }

    @Override
    public List<SrmAchScore> searchScoreSetList(SrmAchScore score) {
        SrmAchScoreExample example = new SrmAchScoreExample();
        SrmAchScoreExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(score.getScoreStatusId())) {
            criteria.andScoreStatusIdEqualTo(score.getScoreStatusId());
        }
        if (StringUtil.isNotBlank(score.getScoreFlow())) {
            criteria.andScoreFlowEqualTo(score.getScoreFlow());
        }
        example.setOrderByClause("CREATE_TIME ASC");
        return srmAchScoreMapper.selectByExample(example);
    }

    @Override
    public SrmAchScore readScoreSet(String scoreFlow) {
        return srmAchScoreMapper.selectByPrimaryKey(scoreFlow);
    }

    @Override
    public List<SrmAchScore> searchScoreListByprarentFlow(List<String> prarentFlowList) {
        SrmAchScoreExample example = new SrmAchScoreExample();
        SrmAchScoreExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (null != prarentFlowList && prarentFlowList.size() > 0) {
            criteria.andParentScoreFlowIn(prarentFlowList);
        }
        example.setOrderByClause("CREATE_TIME ASC");
        return srmAchScoreMapper.selectByExample(example);
    }

    @Override
    public List<SrmAchScoreType> allScoreType() {
        SrmAchScoreTypeExample example = new SrmAchScoreTypeExample();
        SrmAchScoreTypeExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("CREATE_TIME ASC");
        return scoreTypeMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, String>> searchDeptScoreList(Map<String, String> map) {
        return achScoreExtMapper.searchByDept(map);
    }

    @Override
    public List<Map<String, String>> searchAuthorScoreList(Map<String, String> map) {
        return achScoreExtMapper.searchByAuthor(map);
    }

    @Override
    public List<Map<String, String>> searchScoreListByDept(Map<String, String> map) {
        return achScoreExtMapper.searchScoreListByDept(map);
    }

    @Override
    public List<Map<String, String>> searchScoreListByAuthor(Map<String, String> map) {
        return achScoreExtMapper.searchScoreListByAuthor(map);
    }

    @Override
    public List<Map<String, String>> searchAchScoreList(Map<String, String> map) {
        return achScoreExtMapper.searchAchScoreList(map);
    }

    @Override
    public int achScoreIsUse(List<String> scoreFlowList) {
        return achScoreExtMapper.achScoreIsUse(scoreFlowList);
    }

    /**
     * 积分归档，公示，更新
     * @param achScore
     * @param year
     */
    @Override
    public void updadeScoreUsedAuthor(SrmAchScore achScore, String year,String isPublish ) {
        SrmAchBookAuthorExample example1 = new SrmAchBookAuthorExample();
        SrmAchSatAuthorExample example2 = new SrmAchSatAuthorExample();
        SrmAchPatentAuthorExample example3 = new SrmAchPatentAuthorExample();
        SrmAchThesisAuthorExample example4 = new SrmAchThesisAuthorExample();
        PubProjAuthorExample example5 = new PubProjAuthorExample();

        SrmAchBookAuthorExample.Criteria criteria1 = example1.createCriteria();
        SrmAchBookAuthorExample.Criteria criteria11 = example1.createCriteria();
        criteria1.andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        criteria11.andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andIsFixedFlagIsNull();

        SrmAchSatAuthorExample.Criteria criteria2 = example2.createCriteria();
        SrmAchSatAuthorExample.Criteria criteria22 = example2.createCriteria();
        criteria2.andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        criteria22.andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andIsFixedFlagIsNull();

        SrmAchPatentAuthorExample.Criteria criteria3 = example3.createCriteria();
        SrmAchPatentAuthorExample.Criteria criteria33 = example3.createCriteria();
        criteria3.andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        criteria33.andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andIsFixedFlagIsNull();

        SrmAchThesisAuthorExample.Criteria criteria4 = example4.createCriteria();
        SrmAchThesisAuthorExample.Criteria criteria44 = example4.createCriteria();
        criteria4.andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        criteria44.andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andIsFixedFlagIsNull();

        PubProjAuthorExample.Criteria criteria5 = example5.createCriteria();
        PubProjAuthorExample.Criteria criteria55 = example5.createCriteria();
        criteria5.andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        criteria55.andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andIsFixedFlagIsNull();

        SrmAchBookAuthor bookAuthor = new SrmAchBookAuthor();
        SrmAchSatAuthor satAuthor = new SrmAchSatAuthor();
        SrmAchPatentAuthor patentAuthor = new SrmAchPatentAuthor();
        SrmAchThesisAuthor thesisAuthor = new SrmAchThesisAuthor();
        PubProjAuthor projAuthor = new PubProjAuthor();

        if (StringUtil.isNotBlank(year)) {
            criteria1.andCreateTimeLike(year + "%");
            criteria2.andCreateTimeLike(year + "%");
            criteria3.andCreateTimeLike(year + "%");
            criteria4.andCreateTimeLike(year + "%");
            criteria5.andCreateTimeLike(year + "%");
            criteria11.andCreateTimeLike(year + "%");
            criteria22.andCreateTimeLike(year + "%");
            criteria33.andCreateTimeLike(year + "%");
            criteria44.andCreateTimeLike(year + "%");
            criteria55.andCreateTimeLike(year + "%");

            bookAuthor.setIsFixedFlag(GlobalConstant.FLAG_Y);
            satAuthor.setIsFixedFlag(GlobalConstant.FLAG_Y);
            patentAuthor.setIsFixedFlag(GlobalConstant.FLAG_Y);
            thesisAuthor.setIsFixedFlag(GlobalConstant.FLAG_Y);
            projAuthor.setIsFixedFlag(GlobalConstant.FLAG_Y);
        }
        if (null != achScore) {
            if (StringUtil.isNotBlank(achScore.getScoreFlow())) {
                criteria1.andScoreFlowEqualTo(achScore.getScoreFlow());
                criteria2.andScoreFlowEqualTo(achScore.getScoreFlow());
                criteria3.andScoreFlowEqualTo(achScore.getScoreFlow());
                criteria4.andScoreFlowEqualTo(achScore.getScoreFlow());
                criteria5.andScoreFlowEqualTo(achScore.getScoreFlow());
                criteria11.andScoreFlowEqualTo(achScore.getScoreFlow());
                criteria22.andScoreFlowEqualTo(achScore.getScoreFlow());
                criteria33.andScoreFlowEqualTo(achScore.getScoreFlow());
                criteria44.andScoreFlowEqualTo(achScore.getScoreFlow());
                criteria55.andScoreFlowEqualTo(achScore.getScoreFlow());

                bookAuthor.setScoreFlow(achScore.getScoreFlow());
                satAuthor.setScoreFlow(achScore.getScoreFlow());
                patentAuthor.setScoreFlow(achScore.getScoreFlow());
                thesisAuthor.setScoreFlow(achScore.getScoreFlow());
                projAuthor.setScoreFlow(achScore.getScoreFlow());
            }

            if (StringUtil.isNotBlank(achScore.getScoreName())) {
                bookAuthor.setScoreName(achScore.getScoreName());
                satAuthor.setScoreName(achScore.getScoreName());
                patentAuthor.setScoreName(achScore.getScoreName());
                thesisAuthor.setScoreName(achScore.getScoreName());
                projAuthor.setScoreName(achScore.getScoreName());
            }
            if (null != achScore.getScoreDeptValue()) {
                bookAuthor.setAchScoreDept(achScore.getScoreDeptValue());
                satAuthor.setAchScoreDept(achScore.getScoreDeptValue());
                patentAuthor.setAchScoreDept(achScore.getScoreDeptValue());
                thesisAuthor.setAchScoreDept(achScore.getScoreDeptValue());
                projAuthor.setAchScoreDept(achScore.getScoreDeptValue());
            }
            if (null != achScore.getScorePersonalValue()) {
                bookAuthor.setAchScore(achScore.getScorePersonalValue());
                satAuthor.setAchScore(achScore.getScorePersonalValue());
                patentAuthor.setAchScore(achScore.getScorePersonalValue());
                thesisAuthor.setAchScore(achScore.getScorePersonalValue());
                projAuthor.setAchScore(achScore.getScorePersonalValue());
            }
        }
        if(GlobalConstant.FLAG_Y.equals(isPublish)){//只公示已归档的积分
            criteria1.andIsFixedFlagEqualTo(GlobalConstant.FLAG_Y);
            criteria2.andIsFixedFlagEqualTo(GlobalConstant.FLAG_Y);
            criteria3.andIsFixedFlagEqualTo(GlobalConstant.FLAG_Y);
            criteria4.andIsFixedFlagEqualTo(GlobalConstant.FLAG_Y);
            criteria5.andIsFixedFlagEqualTo(GlobalConstant.FLAG_Y);

            bookAuthor.setIsPublish(GlobalConstant.FLAG_Y);
            satAuthor.setIsPublish(GlobalConstant.FLAG_Y);
            patentAuthor.setIsPublish(GlobalConstant.FLAG_Y);
            thesisAuthor.setIsPublish(GlobalConstant.FLAG_Y);
            projAuthor.setIsPublish(GlobalConstant.FLAG_Y);
        }else{
            criteria1.andIsFixedFlagNotEqualTo(GlobalConstant.FLAG_Y);
            criteria2.andIsFixedFlagNotEqualTo(GlobalConstant.FLAG_Y);
            criteria3.andIsFixedFlagNotEqualTo(GlobalConstant.FLAG_Y);
            criteria4.andIsFixedFlagNotEqualTo(GlobalConstant.FLAG_Y);
            criteria5.andIsFixedFlagNotEqualTo(GlobalConstant.FLAG_Y);
            example1.or(criteria11);
            example2.or(criteria22);
            example3.or(criteria33);
            example4.or(criteria44);
            example5.or(criteria55);
        }
        bookAuthorMapper.updateByExampleSelective(bookAuthor, example1);
        satAuthorMapper.updateByExampleSelective(satAuthor, example2);
        patenAuthorMapper.updateByExampleSelective(patentAuthor, example3);
        thesisAuthorMapper.updateByExampleSelective(thesisAuthor, example4);
        projAuthorMapper.updateByExampleSelective(projAuthor, example5);

    }
}
