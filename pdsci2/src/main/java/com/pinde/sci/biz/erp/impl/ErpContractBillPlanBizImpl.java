package com.pinde.sci.biz.erp.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.IErpContractBillPlanBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ErpCrmContractBillPlanMapper;
import com.pinde.sci.dao.erp.ErpCrmContractBillPlanExtMapper;
import com.pinde.sci.enums.erp.PayPlanStatusEnum;
import com.pinde.sci.model.mo.ErpCrmContractBillPlan;
import com.pinde.sci.model.mo.ErpCrmContractBillPlanExample;
import com.pinde.sci.model.mo.ErpCrmContractPayPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ErpContractBillPlanBizImpl implements IErpContractBillPlanBiz {

	@Autowired
	private ErpCrmContractBillPlanMapper billPlanMapper;
	@Autowired
	private ErpCrmContractBillPlanExtMapper billPlanExtMapper;
	@Override
	public List<ErpCrmContractBillPlan> searchContractBillPlanList(
			ErpCrmContractBillPlan billPlan) {
		ErpCrmContractBillPlanExample example=new ErpCrmContractBillPlanExample();
		ErpCrmContractBillPlanExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(billPlan.getContractFlow())){
			criteria.andContractFlowEqualTo(billPlan.getContractFlow());
		}
		return this.billPlanMapper.selectByExample(example);
	}

	@Override
	public String saveContractBillPlan(List<ErpCrmContractBillPlan> billPlanList, String contractFlow) {
		if(billPlanList!=null && !billPlanList.isEmpty()){
			for(ErpCrmContractBillPlan billPlan:billPlanList){
				if(StringUtil.isBlank(billPlan.getContractFlow())){
					billPlan.setContractFlow(contractFlow);
				}
				save(billPlan);
			}
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}

	@Override
	public int save(ErpCrmContractBillPlan payPlan) {
		if(StringUtil.isNotBlank(payPlan.getBillPlanFlow())){
			GeneralMethod.setRecordInfo(payPlan, false);
			return billPlanMapper.updateByPrimaryKeySelective(payPlan);
		}else{
			payPlan.setBillPlanFlow(PkUtil.getUUID());
			payPlan.setBillPlanStatusId(PayPlanStatusEnum.NotStart.getId());
			payPlan.setBillPlanStatusName(PayPlanStatusEnum.NotStart.getName());
			payPlan.setBillBalanceFund(new BigDecimal(0));
			GeneralMethod.setRecordInfo(payPlan, true);
			return billPlanMapper.insertSelective(payPlan);
		}
	}

	@Override
	public ErpCrmContractBillPlan readPayPlan(String billPlanFlow) {
		return billPlanMapper.selectByPrimaryKey(billPlanFlow);
	}

	@Override
	public Integer sumBillPayFundByFlow(String contractFlow) {
		return billPlanExtMapper.sumBillPayFundByFlow(contractFlow);
	}

}
