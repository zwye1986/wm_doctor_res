package com.pinde.sci.biz.recruit.impl;


import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.recruit.IRecruitExamInfoBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.RecruitExamInfoMapper;
import com.pinde.sci.dao.recruit.RecruitExamInfoExtMapper;
import com.pinde.sci.model.mo.RecruitExamInfo;
import com.pinde.sci.model.mo.RecruitExamInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor = Exception.class)
public class RecruitExamInfoBizImpl implements IRecruitExamInfoBiz {

    @Autowired
    private RecruitExamInfoMapper recruitExamInfoMapper;

    @Autowired
    private RecruitExamInfoExtMapper recruitExamInfoExtMapper;

    @Override
    public List<RecruitExamInfo> searchExamInfoByMainFlow(String mainFlow) {
        RecruitExamInfoExample example = new RecruitExamInfoExample();
        RecruitExamInfoExample.Criteria criteria = example.createCriteria();
        criteria.andMainFlowEqualTo(mainFlow);
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        criteria.andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());
        List<RecruitExamInfo> recruitExamInfos = recruitExamInfoMapper.selectByExample(example);
        if (recruitExamInfos != null && recruitExamInfos.size() > 0){
            return recruitExamInfos;
        }else {
            return null;
        }
    }

    @Override
    public RecruitExamInfo readByFlow(String examFlow) {
        return recruitExamInfoMapper.selectByPrimaryKey(examFlow);
    }

    @Override
    public int saveExamInfo(RecruitExamInfo examInfo) {
        if(StringUtil.isNotBlank(examInfo.getExamFlow())){
            GeneralMethod.setRecordInfo(examInfo,false);
            return recruitExamInfoMapper.updateByPrimaryKeySelective(examInfo);
        }else{
            examInfo.setExamFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(examInfo,true);
            return recruitExamInfoMapper.insertSelective(examInfo);
        }
    }

    @Override
    public void delExamDetail(String examFlow) {
        recruitExamInfoExtMapper.delExamRoomInfo(examFlow);
    }

}
