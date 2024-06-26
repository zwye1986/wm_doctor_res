package com.pinde.sci.biz.fstu.impl;


import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.IFstuThesisProcessBiz;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.FstuAuditProcessMapper;
import com.pinde.sci.enums.fstu.ProStatusEnum;
import com.pinde.sci.model.mo.FstuAuditProcess;
import com.pinde.sci.model.mo.FstuAuditProcessExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
public class FstuThesisProcessBizImpl implements IFstuThesisProcessBiz{
    @Autowired
    private FstuAuditProcessMapper fstuAuditProcessMapper;
    @Override
    public int saveAchProcess(FstuAuditProcess srmAchProcess) {
        return fstuAuditProcessMapper.insert(srmAchProcess);
    }

    @Override
    public List<FstuAuditProcess> searchAchProcess(String flow, String statusId) {
        FstuAuditProcessExample example=new FstuAuditProcessExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andRelFlowEqualTo(flow).andProStatusIdEqualTo(statusId);
        example.setOrderByClause("CREATE_TIME DESC");
        return fstuAuditProcessMapper.selectByExample(example);
    }

    @Override
    public List<FstuAuditProcess> searchAchProcess(FstuAuditProcess achProcess) {
        FstuAuditProcessExample example = new FstuAuditProcessExample();
        FstuAuditProcessExample.Criteria criteria = example.createCriteria();
        criteria.andProStatusIdNotEqualTo(ProStatusEnum.Draft.getId());

        if(StringUtil.isNotBlank(achProcess.getRelFlow())){
            criteria.andRelFlowEqualTo(achProcess.getRelFlow());
        }
        if(StringUtil.isNotBlank(achProcess.getRecordStatus())){
            criteria.andRecordStatusEqualTo(achProcess.getRecordStatus());
        }
        if(StringUtil.isNotBlank(achProcess.getRelTypeId())){
            criteria.andRelTypeIdEqualTo(achProcess.getRelTypeId());
        }
        example.setOrderByClause("CREATE_TIME");
        return this.fstuAuditProcessMapper.selectByExample(example);
    }
}
