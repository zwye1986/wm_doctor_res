package com.pinde.sci.biz.lcjn.impl;


import com.pinde.core.common.enums.osca.AuditStatusEnum;
import com.pinde.core.model.*;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.lcjn.ILcjnCostBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.lcjn.LcjnBaseManagerExtMapper;
import com.pinde.sci.dao.lcjn.LcjnCostManagerExtMapper;
import com.pinde.sci.model.mo.LcjnSkillCfgDetail;
import com.pinde.sci.model.mo.LcjnSkillCfgDetailExample;
import com.pinde.sci.model.mo.LcjnSupplies;
import com.pinde.sci.model.mo.LcjnSuppliesExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class LcjnCostBizImpl implements ILcjnCostBiz {
    @Autowired
    private LcjnCourseSkillMapper courseSkillMapper;
    @Autowired
    private LcjnCourseSuppliesMapper courseSuppliesMapper;
    @Autowired
    private LcjnDoctorSiginMapper doctorSiginMapper;
    @Autowired
    private LcjnSkillCfgDetailMapper skillCfgDetailMapper;
    @Autowired
    private LcjnSuppliesMapper suppliesMapper;
    @Autowired
    private LcjnCourseInfoMapper courseInfoMapper;
    @Autowired
    private LcjnBaseManagerExtMapper baseManagerExtMapper;
    @Autowired
    private LcjnCostManagerExtMapper costManagerExtMapper;
    @Autowired
    private LcjnDoctorCourseMapper doctorCourseMapper;

    @Override
    public List<LcjnCourseSupplies> searchSuppliesBySkillFlowAndCourse(String skillFlow, String courseFlow) {
        LcjnCourseSuppliesExample example = new LcjnCourseSuppliesExample();
        LcjnCourseSuppliesExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(skillFlow)&&StringUtil.isNotBlank(courseFlow)) {
            criteria.andSkillFlowEqualTo(skillFlow);
            criteria.andCourseFlowEqualTo(courseFlow);
            return courseSuppliesMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public List<LcjnCourseSupplies> searchSuppliesById(String id,String skillFlow,String courseFlow) {
        LcjnCourseSuppliesExample example = new LcjnCourseSuppliesExample();
        LcjnCourseSuppliesExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(id)) {
            criteria.andDictIdEqualTo(id);
        }
        if (StringUtil.isNotBlank(skillFlow)) {
            criteria.andSkillFlowEqualTo(skillFlow);
        }
        if (StringUtil.isNotBlank(courseFlow)) {
            criteria.andCourseFlowEqualTo(courseFlow);
        }
        return courseSuppliesMapper.selectByExample(example);
    }

    @Override
    public int countSign(String courseFlow) {
        if(StringUtil.isNotBlank(courseFlow)){
            LcjnDoctorSiginExample example = new LcjnDoctorSiginExample();
            LcjnDoctorSiginExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andCourseFlowEqualTo(courseFlow);
            return doctorSiginMapper.countByExample(example);
        }
        return 0;
    }

    @Override
    public int countNum(String courseFlow) {
        if(StringUtil.isNotBlank(courseFlow)){
            LcjnDoctorCourseExample example = new LcjnDoctorCourseExample();
            LcjnDoctorCourseExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).
                    andCourseFlowEqualTo(courseFlow).andAuditStatusIdEqualTo(AuditStatusEnum.Passed.getId());
            return doctorCourseMapper.countByExample(example);
        }
        return 0;
    }

    @Override
    public List<LcjnCourseSkill> searchByCourseFlow(String courseFlow) {
        LcjnCourseSkillExample example = new LcjnCourseSkillExample();
        LcjnCourseSkillExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(courseFlow)) {
            criteria.andCourseFlowEqualTo(courseFlow);
            return courseSkillMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public List<LcjnSkillCfgDetail> searchCfgBySkillFlow(String skillFlow) {
        LcjnSkillCfgDetailExample example = new LcjnSkillCfgDetailExample();
        LcjnSkillCfgDetailExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(skillFlow)) {
            criteria.andSkillFlowEqualTo(skillFlow);
            return skillCfgDetailMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public LcjnSupplies readSupply(String suppliesFlow) {
        if(StringUtil.isNotBlank(suppliesFlow)){
            return suppliesMapper.selectByPrimaryKey(suppliesFlow);
        }
        return null;
    }

    @Override
    public List<LcjnSupplies> searchById(String id) {
        if(StringUtil.isNotBlank(id)){
            LcjnSuppliesExample example = new LcjnSuppliesExample();
            LcjnSuppliesExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDictIdEqualTo(id);
            return suppliesMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public LcjnCourseSupplies readOtherSupply(String recordFlow) {
        if(StringUtil.isNotBlank(recordFlow)){
            return courseSuppliesMapper.selectByPrimaryKey(recordFlow);
        }
        return null;
    }

    @Override
    public int editOtherSupply(LcjnCourseSupplies courseSupplies) {
        String recordFlow = courseSupplies.getRecordFlow();
        if(StringUtil.isNotBlank(recordFlow)){
            GeneralMethod.setRecordInfo(courseSupplies,false);
            return courseSuppliesMapper.updateByPrimaryKeySelective(courseSupplies);
        }else{
            courseSupplies.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(courseSupplies,true);
            return courseSuppliesMapper.insertSelective(courseSupplies);
        }
    }

    @Override
    public List<LcjnSupplies> searchSupplyList() {
        LcjnSuppliesExample example = new LcjnSuppliesExample();
        LcjnSuppliesExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        return suppliesMapper.selectByExample(example);
    }

    @Override
    public LcjnCourseInfo readCourse(String courseFlow) {
        if(StringUtil.isNotBlank(courseFlow)){
            return courseInfoMapper.selectByPrimaryKey(courseFlow);
        }
        return null;
    }

    @Override
    public List<LcjnCourseSupplies> searchSuppliesByCourseFlow(String courseFlow) {
        LcjnCourseSuppliesExample example = new LcjnCourseSuppliesExample();
        LcjnCourseSuppliesExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(courseFlow)) {
            criteria.andCourseFlowEqualTo(courseFlow);
            return courseSuppliesMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public List<Map<String,Object>> searchCourseInfo(LcjnCourseInfo courseInfo,String startTime,String endTime) {
        Map<String,String> paramMap = new HashMap<>();
        if(StringUtil.isNotBlank(courseInfo.getCourseName())){
            paramMap.put("courseName",courseInfo.getCourseName());
        }
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);
        paramMap.put("isReleased", com.pinde.core.common.GlobalConstant.FLAG_Y);
        return baseManagerExtMapper.searchCourse(paramMap);
    }

    @Override
    public List<LcjnSkillCfgDetail> searchCfgByCourseFlow(String courseFlow) {
        return costManagerExtMapper.searchCfgByCourseFlow(courseFlow);
    }
}
