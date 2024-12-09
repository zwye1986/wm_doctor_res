package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IResLectureRandomSignBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.ResLectureRandomScanMapper;
import com.pinde.sci.dao.base.ResLectureRandomSignMapper;
import com.pinde.sci.dao.res.ResLectureInfoExtMapper;
import com.pinde.sci.model.mo.ResLectureRandomScan;
import com.pinde.sci.model.mo.ResLectureRandomScanExample;
import com.pinde.sci.model.mo.ResLectureRandomSign;
import com.pinde.sci.model.mo.ResLectureRandomSignExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor = Exception.class)
public class IResLectureRandomSignBizImpl implements IResLectureRandomSignBiz {
    @Autowired
    private ResLectureRandomSignMapper randomSignMapper;
    @Autowired
    private ResLectureRandomScanMapper randomScanMapper;
    @Autowired
    private ResLectureInfoExtMapper lectureinfoExtMapper;

    @Override
    public List<ResLectureRandomSign> searchRandomByLectureFlow(String lectureFlow) {
        ResLectureRandomSignExample example = new ResLectureRandomSignExample();
        ResLectureRandomSignExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("modify_time ASC");
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        if(StringUtil.isNotBlank(lectureFlow)) {
            criteria.andLectureFlowEqualTo(lectureFlow);
        }
        List<ResLectureRandomSign> randomSignList = randomSignMapper.selectByExample(example);
        return randomSignList;
    }

    @Override
    public int saveRandom(ResLectureRandomSign randomSign) {
        int i = 0;
        if(StringUtil.isNotBlank(randomSign.getRandomFlow())){
            GeneralMethod.setRecordInfo(randomSign, false);
            i = randomSignMapper.updateByPrimaryKeySelective(randomSign);
        }else{
            randomSign.setRandomFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(randomSign, true);
            i = randomSignMapper.insert(randomSign);
        }
        return i;
    }

    @Override
    public ResLectureRandomSign read(String randomFlow) {
        return randomSignMapper.selectByPrimaryKey(randomFlow);
    }

    @Override
    public List<ResLectureRandomScan> searchRandomScan(String operUserFlow,String lectureFlow,String randomFlow) {
        ResLectureRandomScanExample example = new ResLectureRandomScanExample();
        ResLectureRandomScanExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andIsScanEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        if(StringUtil.isNotBlank(randomFlow)){
            criteria.andRandomFlowEqualTo(randomFlow);
        }
        if(StringUtil.isNotBlank(lectureFlow)){
            criteria.andLectureFlowEqualTo(lectureFlow);
        }
        if(StringUtil.isNotBlank(operUserFlow)){
            criteria.andOperUserFlowEqualTo(operUserFlow);
        }
        return randomScanMapper.selectByExample(example);
    }

    @Override
    public List<ResLectureRandomScan> searchIsScan(String lectureFlow, List<String> roles) {
        return lectureinfoExtMapper.searchIsRandomScan(lectureFlow,roles);
    }

   /* @Override
    public List<ResLectureRandomScan> searchByOperUserFlow(String operUserFlow,String lectureFlow) {
        ResLectureRandomScanExample example = new ResLectureRandomScanExample();
        ResLectureRandomScanExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andIsScanEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        if(StringUtil.isNotBlank(operUserFlow)){
            criteria.andOperUserFlowEqualTo(operUserFlow);
        }
        if(StringUtil.isNotBlank(lectureFlow)){
            criteria.andLectureFlowEqualTo(lectureFlow);
        }
        return randomScanMapper.selectByExample(example);
    }*/

    @Override
    public List<ResLectureRandomScan> getRandomScanList(List<String> randomFlows, String lectureFlow) {
        ResLectureRandomScanExample example = new ResLectureRandomScanExample();
        ResLectureRandomScanExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andIsScanEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
            .andRandomFlowIn(randomFlows).andLectureFlowEqualTo(lectureFlow);
        return randomScanMapper.selectByExample(example);
    }
}
