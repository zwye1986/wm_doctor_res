package com.pinde.sci.biz.lcjn.impl;


import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.lcjn.ILcjnEvaluateBiz;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor = Exception.class)
public class LcjnEvaluateBizImpl implements ILcjnEvaluateBiz {
    @Autowired
    private LcjnCourseEvaluateMapper courseEvaluateMapper;
    @Autowired
    private LcjnCourseEvaluateDetailMapper courseEvaluateDetailMapper;
    @Autowired
    private LcjnTeaEvaluateMapper teaEvaluateMapper;
    @Autowired
    private LcjnTeaEvaluateDetailMapper teaEvaluateDetailMapper;
    @Autowired
    private LcjnCourseTeaMapper courseTeaMapper;
    @Autowired
    private LcjnCourseInfoMapper courseInfoMapper;

    @Override
    public LcjnCourseEvaluate read(String evaluateFlow) {
        if(StringUtil.isNotBlank(evaluateFlow)){
            return courseEvaluateMapper.selectByPrimaryKey(evaluateFlow);
        }else{
            return null;
        }
    }

    @Override
    public List<LcjnCourseEvaluate> searchByCourseFlow(String courseFlow) {
        if(StringUtil.isNotBlank(courseFlow)){
            LcjnCourseEvaluateExample example = new LcjnCourseEvaluateExample();
            LcjnCourseEvaluateExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andCourseFlowEqualTo(courseFlow);
            return courseEvaluateMapper.selectByExample(example);
        }else {
            return null;
        }
    }

    @Override
    public List<LcjnCourseTea> searchTeaByCourseFlow(String courseFlow) {
        if(StringUtil.isNotBlank(courseFlow)){
            LcjnCourseTeaExample example = new LcjnCourseTeaExample();
            LcjnCourseTeaExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andCourseFlowEqualTo(courseFlow);
            return courseTeaMapper.selectByExample(example);
        }else{
            return null;
        }
    }

    @Override
    public List<LcjnTeaEvaluateDetail> searchTeaDetailByEvaluateFlow(String teaEvaluateFlow) {
        if(StringUtil.isNotBlank(teaEvaluateFlow)){
            LcjnTeaEvaluateDetailExample example = new LcjnTeaEvaluateDetailExample();
            LcjnTeaEvaluateDetailExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andTeaEvaluateFlowEqualTo(teaEvaluateFlow);
            return teaEvaluateDetailMapper.selectByExample(example);
        }else {
            return null;
        }
    }

    @Override
    public List<LcjnCourseEvaluateDetail> searchDetailByEvaluateFlow(String evaluateFlow) {
        if(StringUtil.isNotBlank(evaluateFlow)){
            LcjnCourseEvaluateDetailExample example = new LcjnCourseEvaluateDetailExample();
            LcjnCourseEvaluateDetailExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andEvaluateFlowEqualTo(evaluateFlow);
            return courseEvaluateDetailMapper.selectByExample(example);
        }else {
            return null;
        }
    }

    @Override
    public List<LcjnTeaEvaluate> searchByTeacher(String courseFlow, String teacherFlow) {
        LcjnTeaEvaluateExample example = new LcjnTeaEvaluateExample();
        LcjnTeaEvaluateExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(courseFlow)){
            criteria.andCourseFlowEqualTo(courseFlow);
        }
        if(StringUtil.isNotBlank(teacherFlow)){
            criteria.andUserFlowEqualTo(teacherFlow);
        }
        if(StringUtil.isBlank(courseFlow)||StringUtil.isBlank(teacherFlow)){
            return null;
        }
        return teaEvaluateMapper.selectByExample(example);
    }

    @Override
    public LcjnCourseInfo readCourseInfo(String courseFlow) {
        if(StringUtil.isNotBlank(courseFlow)){
            return courseInfoMapper.selectByPrimaryKey(courseFlow);
        }
        return null;
    }
}
