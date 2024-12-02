package com.pinde.sci.biz.recruit.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.recruit.IRecruitExamRoomInfoBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.RecruitExamRoomInfoMapper;
import com.pinde.sci.model.mo.RecruitExamRoomInfo;
import com.pinde.sci.model.mo.RecruitExamRoomInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor = Exception.class)
public class RecruitExamRoomInfoBizImpl implements IRecruitExamRoomInfoBiz {

    @Autowired
    private RecruitExamRoomInfoMapper recruitExamRoomInfoMapper;

    @Override
    public String IsExamRoomUsed(String roomFlow,String orgFlow) {
        RecruitExamRoomInfoExample example = new RecruitExamRoomInfoExample();
        RecruitExamRoomInfoExample.Criteria criteria = example.createCriteria();

        criteria.andRoomFlowEqualTo(roomFlow);
        criteria.andOrgFlowEqualTo(orgFlow);
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);

        List<RecruitExamRoomInfo> recruitExamRoomInfos = recruitExamRoomInfoMapper.selectByExample(example);
        if (recruitExamRoomInfos!=null&&!recruitExamRoomInfos.isEmpty()) {
            return com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y;
        }else {
            return com.pinde.core.common.GlobalConstant.RECORD_STATUS_N;
        }
    }

    @Override
    public List<RecruitExamRoomInfo> readRoomInfosByExamFlow(String examFlow) {
        RecruitExamRoomInfoExample example = new RecruitExamRoomInfoExample();
        RecruitExamRoomInfoExample.Criteria criteria = example.createCriteria();

        criteria.andExamFlowEqualTo(examFlow);
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        return recruitExamRoomInfoMapper.selectByExample(example);
    }

    @Override
    public int saveRoomInfo(RecruitExamRoomInfo roomInfo) {
        if(StringUtil.isNotBlank(roomInfo.getRecordFlow())){
            GeneralMethod.setRecordInfo(roomInfo,false);
            return recruitExamRoomInfoMapper.updateByPrimaryKeySelective(roomInfo);
        }else{
            roomInfo.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(roomInfo,true);
            return recruitExamRoomInfoMapper.insertSelective(roomInfo);
        }
    }
}
