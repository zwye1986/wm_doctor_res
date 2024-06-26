package com.pinde.sci.biz.xjgl.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.xjgl.IXjQuestionnaireBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.NywjAnswerDetailMapper;
import com.pinde.sci.dao.base.NywjCourseQuestionMapper;
import com.pinde.sci.dao.base.NywjQuestionDetailMapper;
import com.pinde.sci.dao.base.NywjStudentEvaluateMapper;
import com.pinde.sci.dao.xjgl.XjCourseQuestionExtMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.xjgl.XjQuestionDetailExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Copyright njpdxx.com
 * @since 2018/3/7
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class XjQuestionnaireBizImpl implements IXjQuestionnaireBiz{
    @Autowired
    private NywjCourseQuestionMapper courseQuestionMapper;
    @Autowired
    private NywjQuestionDetailMapper questionDetailMapper;
    @Autowired
    private NywjAnswerDetailMapper answerDetailMapper;
    @Autowired
    private NywjStudentEvaluateMapper studentEvaluateMapper;
    @Autowired
    private XjCourseQuestionExtMapper courseQuestionExtMapper;

    @Override
    public List<NywjCourseQuestion> searchQuestionList(NywjCourseQuestion courseQuestion) {
        NywjCourseQuestionExample example=new NywjCourseQuestionExample();
        NywjCourseQuestionExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(courseQuestion.getRecordFlow())){
            criteria.andRecordFlowEqualTo(courseQuestion.getRecordFlow());
        }
        if(StringUtil.isNotBlank(courseQuestion.getQuestionName())){
            criteria.andQuestionNameLike("%"+courseQuestion.getQuestionName()+"%");
        }
        if(StringUtil.isNotBlank(courseQuestion.getCourseTypeId())){
            criteria.andCourseTypeIdEqualTo(courseQuestion.getCourseTypeId());
        }
        if(StringUtil.isNotBlank(courseQuestion.getCreateTime())){
            criteria.andCreateTimeLike(courseQuestion.getCreateTime()+"%");
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return courseQuestionMapper.selectByExample(example);
    }

    @Override
    public int saveCourseQuestion(NywjCourseQuestion courseQuestion, List<XjQuestionDetailExt> detailExts) {
        if(StringUtil.isBlank(courseQuestion.getRecordFlow())){
            if(StringUtil.isNotBlank(courseQuestion.getCourseTypeId())){//判断是否已维护过该课程类型
                NywjCourseQuestion cq=new NywjCourseQuestion();
                cq.setCourseTypeId(courseQuestion.getCourseTypeId());
                List<NywjCourseQuestion> tempList=searchQuestionList(cq);
                if(tempList!=null&&tempList.size()>0){
                    return -1;
                }
            }
            String questionFlow=PkUtil.getUUID();
            courseQuestion.setRecordFlow(questionFlow);
            GeneralMethod.setRecordInfo(courseQuestion,true);
            int num=courseQuestionMapper.insert(courseQuestion);
            if(num>0&&detailExts.size()>0){
                for (XjQuestionDetailExt ext:detailExts) {
                    String detailFlow=PkUtil.getUUID();
                    NywjQuestionDetail que = ext.getQuestionDetail();
                    if(que!=null){
                        que.setRecordFlow(detailFlow);
                        que.setQuestionFlow(questionFlow);
                        GeneralMethod.setRecordInfo(que,true);
                        int num1=questionDetailMapper.insert(que);
                        List<NywjAnswerDetail> answerDetailList=ext.getAnswerDetailList();
                        if(num1>0&&answerDetailList!=null&&answerDetailList.size()>0){
                            for (NywjAnswerDetail ad:answerDetailList) {
                                if(ad!=null){
                                    ad.setRecordFlow(PkUtil.getUUID());
                                    ad.setDetailFlow(detailFlow);
                                    GeneralMethod.setRecordInfo(ad,true);
                                    answerDetailMapper.insert(ad);
                                }
                            }
                        }
                    }
                }
                return 1;
            }
        }else if(StringUtil.isNotBlank(courseQuestion.getRecordFlow())){//修改问卷
            if(StringUtil.isNotBlank(courseQuestion.getCourseTypeId())){//判断是否已维护过该课程类型
                NywjCourseQuestionExample example=new NywjCourseQuestionExample();
                NywjCourseQuestionExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                criteria.andRecordFlowNotEqualTo(courseQuestion.getRecordFlow());
                criteria.andCourseTypeIdEqualTo(courseQuestion.getCourseTypeId());
                List<NywjCourseQuestion> tempList=courseQuestionMapper.selectByExample(example);
                if(tempList!=null&&tempList.size()>0){
                    return -1;
                }
            }
            GeneralMethod.setRecordInfo(courseQuestion,false);
            int num=courseQuestionMapper.updateByPrimaryKeySelective(courseQuestion);
            if(num>0&&detailExts.size()>0){
                //删除旧的题目和选项信息
                delQuestionAndAnswerDetailByFlow(courseQuestion.getRecordFlow());
                //重新插入新的题目和选项信息
                for (XjQuestionDetailExt ext:detailExts) {
                    String detailFlow=PkUtil.getUUID();
                    NywjQuestionDetail que = ext.getQuestionDetail();
                    if(que!=null){
                        que.setRecordFlow(detailFlow);
                        que.setQuestionFlow(courseQuestion.getRecordFlow());
                        GeneralMethod.setRecordInfo(que,true);
                        int num1=questionDetailMapper.insert(que);
                        List<NywjAnswerDetail> answerDetailList=ext.getAnswerDetailList();
                        if(num1>0&&answerDetailList!=null&&answerDetailList.size()>0){
                            for (NywjAnswerDetail ad:answerDetailList) {
                                if(ad!=null){
                                    ad.setRecordFlow(PkUtil.getUUID());
                                    ad.setDetailFlow(detailFlow);
                                    GeneralMethod.setRecordInfo(ad,true);
                                    answerDetailMapper.insert(ad);
                                }
                            }
                        }
                    }
                }
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int delCourseQuestion(String recordFlow) {
        int num=0;
        if(StringUtil.isNotBlank(recordFlow)){
            NywjCourseQuestionExample example=new NywjCourseQuestionExample();
            NywjCourseQuestionExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            criteria.andRecordFlowEqualTo(recordFlow);
            num=courseQuestionMapper.deleteByExample(example);
        }
        if(num>0){
            delQuestionAndAnswerDetailByFlow(recordFlow);
        }
        return num;
    }

    public int delQuestionAndAnswerDetailByFlow(String recordFlow){
        NywjQuestionDetailExample questionDetailExample=new NywjQuestionDetailExample();
        NywjQuestionDetailExample.Criteria criteria=questionDetailExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        criteria.andQuestionFlowEqualTo(recordFlow);
        List<NywjQuestionDetail> detailList=questionDetailMapper.selectByExample(questionDetailExample);
        List<String> detailFlowList=new ArrayList<>();
        if(detailList!=null&&detailList.size()>0){
            for (NywjQuestionDetail qd:detailList) {
                detailFlowList.add(qd.getRecordFlow());
            }
            if(detailFlowList.size()>0){
                NywjAnswerDetailExample answerDetailExample=new NywjAnswerDetailExample();
                NywjAnswerDetailExample.Criteria answerDetailCriteria=answerDetailExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
                answerDetailCriteria.andDetailFlowIn(detailFlowList);
                answerDetailMapper.deleteByExample(answerDetailExample);
            }
        }
        return questionDetailMapper.deleteByExample(questionDetailExample);
    }
    @Override
    public List<Map<String, String>> searchQuestions(Map<String, String> map) {
        return courseQuestionExtMapper.queryQuestionList(map);
    }

    @Override
    public List<NywjQuestionDetail> searchQuestionDetailList(NywjQuestionDetail questionDetail) {
        NywjQuestionDetailExample example=new NywjQuestionDetailExample();
        NywjQuestionDetailExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(questionDetail.getRecordFlow())){
            criteria.andRecordFlowEqualTo(questionDetail.getRecordFlow());
        }
        if(StringUtil.isNotBlank(questionDetail.getQuestionFlow())){
            criteria.andQuestionFlowEqualTo(questionDetail.getQuestionFlow());
        }
        example.setOrderByClause("to_number(SERIAL)");
        return questionDetailMapper.selectByExample(example);
    }

    @Override
    public List<NywjAnswerDetail> searchAnswerDetailList(NywjAnswerDetail answerDetail) {
        NywjAnswerDetailExample example=new NywjAnswerDetailExample();
        NywjAnswerDetailExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(answerDetail.getRecordFlow())){
            criteria.andRecordFlowEqualTo(answerDetail.getRecordFlow());
        }
        if(StringUtil.isNotBlank(answerDetail.getDetailFlow())){
            criteria.andDetailFlowEqualTo(answerDetail.getDetailFlow());
        }
        example.setOrderByClause("SERIAL");
        return answerDetailMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, String>> searchStudentCourseInfos(Map<String, String> map) {
        return courseQuestionExtMapper.searchStudentCourseInfos(map);
    }

    @Override
    public List<NywjStudentEvaluate> searchStudentEvaluateList(NywjStudentEvaluate studentEvaluate) {
        NywjStudentEvaluateExample example=new NywjStudentEvaluateExample();
        NywjStudentEvaluateExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(studentEvaluate.getRecordFlow())){
            criteria.andRecordFlowEqualTo(studentEvaluate.getRecordFlow());
        }
        if(StringUtil.isNotBlank(studentEvaluate.getStudentFlow())){
            criteria.andStudentFlowEqualTo(studentEvaluate.getStudentFlow());
        }
        if(StringUtil.isNotBlank(studentEvaluate.getQuestionFlow())){
            criteria.andQuestionFlowEqualTo(studentEvaluate.getQuestionFlow());
        }
        if(StringUtil.isNotBlank(studentEvaluate.getCourseFlow())){
            criteria.andCourseFlowEqualTo(studentEvaluate.getCourseFlow());
        }
        if(StringUtil.isNotBlank(studentEvaluate.getCourseTypeId())){
            criteria.andCourseTypeIdEqualTo(studentEvaluate.getCourseTypeId());
        }
        if(StringUtil.isNotBlank(studentEvaluate.getSessionNumber())){
            criteria.andSessionNumberEqualTo(studentEvaluate.getSessionNumber());
        }
        return studentEvaluateMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public int saveStuEvaluate(NywjStudentEvaluate studentEvaluate) {
        if(StringUtil.isNotBlank(studentEvaluate.getRecordFlow())){
            GeneralMethod.setRecordInfo(studentEvaluate,false);
            return studentEvaluateMapper.updateByPrimaryKeySelective(studentEvaluate);
        }else{
            studentEvaluate.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(studentEvaluate,true);
            return studentEvaluateMapper.insertSelective(studentEvaluate);
        }
    }

    @Override
    public List<Map<String, String>> searchEvaluateDetails(Map<String, String> map) {
        return courseQuestionExtMapper.queryEvaluateDetails(map);
    }
}
