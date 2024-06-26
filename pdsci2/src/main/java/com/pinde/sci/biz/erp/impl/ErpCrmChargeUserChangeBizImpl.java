package com.pinde.sci.biz.erp.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.IErpContractBiz;
import com.pinde.sci.biz.erp.IErpContractOtherPowerBiz;
import com.pinde.sci.biz.erp.IErpCrmChargeUserChangeBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ErpCrmChargeUserChangeMapper;
import com.pinde.sci.dao.base.ErpCrmChargeUserLogMapper;
import com.pinde.sci.dao.base.ErpCrmContractOtherPowerMapper;
import com.pinde.sci.enums.erp.UserChangeStatusEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ErpCrmChargeUserChangeBizImpl implements IErpCrmChargeUserChangeBiz {

    @Autowired
    private ErpCrmChargeUserChangeMapper changeMapper;
    @Autowired
    private ErpCrmChargeUserLogMapper changeLogMapper;
    @Autowired
    private IErpContractBiz contractBiz;
    @Override
    public List<ErpCrmChargeUserChange> searchByChange(ErpCrmChargeUserChange change) {
        ErpCrmChargeUserChangeExample example=new ErpCrmChargeUserChangeExample();
        ErpCrmChargeUserChangeExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(change.getChangeFlow()))
        {
            criteria.andChangeFlowEqualTo(change.getChangeFlow());
        }
        if(StringUtil.isNotBlank(change.getOldSignDeptFlow()))
        {
            criteria.andOldSignDeptFlowEqualTo(change.getOldSignDeptFlow());
        }
        if(StringUtil.isNotBlank(change.getOldChargeUserFlow()))
        {
            criteria.andOldChargeUserFlowEqualTo(change.getOldChargeUserFlow());
        }
        if(StringUtil.isNotBlank(change.getOldChargeUser2Flow()))
        {
            criteria.andOldChargeUser2FlowEqualTo(change.getOldChargeUser2Flow());
        }
        if(StringUtil.isNotBlank(change.getSignDeptFlow()))
        {
            criteria.andSignDeptFlowEqualTo(change.getSignDeptFlow());
        }
        if(StringUtil.isNotBlank(change.getChargeUserFlow()))
        {
            criteria.andChargeUserFlowEqualTo(change.getChargeUserFlow());
        }
        if(StringUtil.isNotBlank(change.getChargeUser2Flow()))
        {
            criteria.andChargeUser2FlowEqualTo(change.getChargeUser2Flow());
        }
        if(StringUtil.isNotBlank(change.getStatusId()))
        {
            criteria.andStatusIdEqualTo(change.getStatusId());
        }
        if(StringUtil.isNotBlank(change.getApplyUserFlow()))
        {
            criteria.andApplyUserFlowEqualTo(change.getApplyUserFlow());
        }
        if(StringUtil.isNotBlank(change.getApplyUserName()))
        {
            criteria.andApplyUserNameLike("%"+change.getApplyUserName()+"%");
        }
        if(StringUtil.isNotBlank(change.getAuditUserName()))
        {
            criteria.andAuditUserNameLike("%"+change.getAuditUserName()+"%");
        }
        example.setOrderByClause(" APPLY_TIME DESC ");
        return changeMapper.selectByExample(example);
    }

    @Override
    public ErpCrmChargeUserChange readByFlow(String changeFlow) {
        return changeMapper.selectByPrimaryKey(changeFlow);
    }

    @Override
    public int save(ErpCrmChargeUserChange change) {
        if(StringUtil.isNotBlank(change.getChangeFlow()))
        {
            GeneralMethod.setRecordInfo(change,false);
            return changeMapper.updateByPrimaryKeySelective(change);
        }else{
            change.setChangeFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(change,true);
            return changeMapper.insertSelective(change);
        }
    }

    @Override
    public int audit(ErpCrmChargeUserChange change) {
        if(change!=null) {
            if (UserChangeStatusEnum.ApplyBack.getId().equals(change.getStatusId())) {
                return save(change);
            }
            if (UserChangeStatusEnum.ApplyPass.getId().equals(change.getStatusId())) {
                int count = save(change);
                if (count == 1) {
                    List<String> contractFlows = new ArrayList<>();
                    ErpCrmContract contract = new ErpCrmContract();
                    contract.setChargeUserFlow(change.getOldChargeUserFlow());
                    contract.setSignDeptFlow(change.getOldSignDeptFlow());
                    List<ErpCrmContract> crmContracts = contractBiz.searchErpContracts(contract,null);
                    int count1 = 0;
                    if (crmContracts != null && !crmContracts.isEmpty()) {
                        for (ErpCrmContract contract1 : crmContracts) {
                            if (!contractFlows.contains(contract1.getContractFlow())) {
                                contractFlows.add(contract1.getContractFlow());
                            }
                            contract1.setSignDeptFlow(change.getSignDeptFlow());
                            contract1.setChargeUserFlow(change.getChargeUserFlow());
                            contract1.setChargeUserName(change.getChargeUserName());
                            count1 += contractBiz.updateContract(contract1);
                        }
                    }
                    contract = new ErpCrmContract();
                    contract.setChargeUser2Flow(change.getOldChargeUser2Flow());
                    contract.setSignDeptFlow(change.getOldSignDeptFlow());
                    List<ErpCrmContract> crmContract2s = contractBiz.searchErpContracts(contract,null);
                    if (crmContract2s != null && !crmContract2s.isEmpty()) {
                        for (ErpCrmContract contract1 : crmContract2s) {
                            if (!contractFlows.contains(contract1.getContractFlow())) {
                                contractFlows.add(contract1.getContractFlow());
                            }
                            contract1.setSignDeptFlow(change.getSignDeptFlow());
                            contract1.setChargeUser2Flow(change.getChargeUser2Flow());
                            contract1.setChargeUser2Name(change.getChargeUser2Name());
                            count1 += contractBiz.updateContract(contract1);
                        }
                    }
                    if (count1 > 0) {
                        saveLog(change,contractFlows);
                    }
                }
                return  count;

            }
        }
        return 0;
    }

    @Override
    public int checkHave(ErpCrmChargeUserChange change) {
        ErpCrmChargeUserChangeExample example=new ErpCrmChargeUserChangeExample();
        ErpCrmChargeUserChangeExample.Criteria criteria=example.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(change.getChangeFlow()))
        {
            criteria.andChangeFlowNotEqualTo(change.getChangeFlow());
        }
        if(StringUtil.isNotBlank(change.getOldSignDeptFlow()))
        {
            criteria.andOldSignDeptFlowEqualTo(change.getOldSignDeptFlow());
        }
        if(StringUtil.isNotBlank(change.getOldChargeUserFlow()))
        {
            criteria.andOldChargeUserFlowEqualTo(change.getOldChargeUserFlow());
        }
        if(StringUtil.isNotBlank(change.getOldChargeUser2Flow()))
        {
            criteria.andOldChargeUser2FlowEqualTo(change.getOldChargeUser2Flow());
        }
        if(StringUtil.isNotBlank(change.getSignDeptFlow()))
        {
            criteria.andSignDeptFlowEqualTo(change.getSignDeptFlow());
        }
        if(StringUtil.isNotBlank(change.getChargeUserFlow()))
        {
            criteria.andChargeUserFlowEqualTo(change.getChargeUserFlow());
        }
        if(StringUtil.isNotBlank(change.getChargeUser2Flow()))
        {
            criteria.andChargeUser2FlowEqualTo(change.getChargeUser2Flow());
        }
        criteria.andStatusIdEqualTo(UserChangeStatusEnum.Applying.getId());
           List<ErpCrmChargeUserChange> list= changeMapper.selectByExample(example);
        if(list!=null)
        {
            return list.size();
        }
        return 0;
    }

    private void saveLog(ErpCrmChargeUserChange change, List<String> contractFlows) {
        if(change!=null&&contractFlows!=null&&!contractFlows.isEmpty())
        {
            for(String flow:contractFlows)
            {
                ErpCrmChargeUserLog log=new ErpCrmChargeUserLog();
                log.setAuditUserFlow(change.getAuditUserFlow());
                log.setAuditUserName(change.getAuditUserName());
                log.setChangeFlow(change.getChangeFlow());
                log.setContractFlow(flow);
                log.setLogFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(log,true);
                changeLogMapper.insertSelective(log);
            }
        }
    }
}
