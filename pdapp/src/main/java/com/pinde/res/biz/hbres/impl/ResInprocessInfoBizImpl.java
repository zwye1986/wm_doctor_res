package com.pinde.res.biz.hbres.impl;

import com.pinde.core.model.ResInprocessInfo;
import com.pinde.core.model.ResInprocessInfoExample;
import com.pinde.core.model.ResInprocessInfoMember;
import com.pinde.core.model.ResInprocessInfoMemberExample;
import com.pinde.res.biz.hbres.IResInprocessInfoBiz;
import com.pinde.res.dao.hbres.ext.ResInprocessInfoMemberExtMapper;
import com.pinde.sci.dao.base.ResInprocessInfoMapper;
import com.pinde.sci.dao.base.ResInprocessInfoMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by www.0001.Ga on 2016-10-12.
 */
@Service
//@Transactional(rollbackFor=Exception.class)
public class ResInprocessInfoBizImpl implements IResInprocessInfoBiz {
    @Autowired
    private ResInprocessInfoMapper inprocessInfoMapper;
    @Autowired
    private ResInprocessInfoMemberMapper memberMapper;
    @Autowired
    private ResInprocessInfoMemberExtMapper memberExtMapper;
    @Override
    public ResInprocessInfo readByDeptFlowAndOrgFlow(String deptFlow, String orgFlow) {
        ResInprocessInfoExample example=new ResInprocessInfoExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andOrgFlowEqualTo(orgFlow).andDeptFlowEqualTo(deptFlow);
        List<ResInprocessInfo> list=inprocessInfoMapper.selectByExampleWithBLOBs(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }
    @Override
    public ResInprocessInfo readByDeptFlow(String deptFlow) {
        ResInprocessInfoExample example=new ResInprocessInfoExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andDeptFlowEqualTo(deptFlow);
        List<ResInprocessInfo> list=inprocessInfoMapper.selectByExampleWithBLOBs(example);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<ResInprocessInfoMember> readMembersByRecordFlow(String recordFlow) {
        ResInprocessInfoMemberExample example=new ResInprocessInfoMemberExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andInfoRecordFlowEqualTo(recordFlow);

        return memberMapper.selectByExample(example);
    }

    @Override
    public int deleteMemberNotInFlow(String recordFlow, List<String> memberFlows) {

        return memberExtMapper.deleteMemberNotInFlow(recordFlow, memberFlows);
    }

    @Override
    public List<ResInprocessInfo> readInfoList(String orgFlow) {
        return memberExtMapper.readInfoList(orgFlow);
    }
}
