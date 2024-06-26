package com.pinde.sci.biz.erp.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.IErpContractBiz;
import com.pinde.sci.biz.erp.IErpContractOtherPowerBiz;
import com.pinde.sci.biz.erp.IErpContractProcessBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ErpCrmContractOtherPowerMapper;
import com.pinde.sci.dao.base.ErpCrmContractProcessMapper;
import com.pinde.sci.enums.erp.ContractStatusEnum;
import com.pinde.sci.model.mo.ErpCrmContract;
import com.pinde.sci.model.mo.ErpCrmContractOtherPower;
import com.pinde.sci.model.mo.ErpCrmContractOtherPowerExample;
import com.pinde.sci.model.mo.ErpCrmContractProcess;
import org.docx4j.wml.P;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ErpContractProcessBizImpl implements IErpContractProcessBiz {

	@Autowired
	private IErpContractBiz contractBiz;
	@Autowired
	private ErpCrmContractProcessMapper processMapper;

	@Override
	public int saveProcess(ErpCrmContract contract, ErpCrmContractProcess process) {
		contractBiz.updateContract(contract);
		if(StringUtil.isNotBlank(process.getProcessFlow()))
		{
			GeneralMethod.setRecordInfo(process,false);
			return  processMapper.updateByPrimaryKeySelective(process);
		}else{
			process.setProcessFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(process,true);
			return  processMapper.insertSelective(process);
		}
	}

	@Override
	public int auditContract(ErpCrmContract old, ErpCrmContractProcess process) {
		if(StringUtil.isNotBlank(old.getContractFlow())) {
			if (ContractStatusEnum.Implement.getId().equals(old.getContractStatusId())) {
				contractBiz.updateContractSubCount(old.getContractFlow(),true);
			}
			return saveProcess(old, process);
		}
		return 0;
	}
}
