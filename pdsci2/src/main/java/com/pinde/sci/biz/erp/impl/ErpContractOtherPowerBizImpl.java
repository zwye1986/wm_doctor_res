package com.pinde.sci.biz.erp.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.IErpContractOtherPowerBiz;
import com.pinde.sci.biz.erp.IErpContractPowerBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.ErpCrmContractOtherPowerMapper;
import com.pinde.sci.dao.base.ErpCrmContractPowerMapper;
import com.pinde.sci.model.erp.ErpCrmContractPowerExt;
import com.pinde.sci.model.mo.ErpCrmContractOtherPower;
import com.pinde.sci.model.mo.ErpCrmContractOtherPowerExample;
import com.pinde.sci.model.mo.ErpCrmContractPowerExample;
import com.pinde.sci.model.mo.PubFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ErpContractOtherPowerBizImpl implements IErpContractOtherPowerBiz {

	@Autowired
	private ErpCrmContractOtherPowerMapper powerMapper;
	@Autowired
	private IFileBiz fileBiz;
	@Override
	public List<ErpCrmContractOtherPower> searchContractPowerList(
			ErpCrmContractOtherPower power) {
		ErpCrmContractOtherPowerExample example=new ErpCrmContractOtherPowerExample();
		ErpCrmContractOtherPowerExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(power.getContractFlow())){
			criteria.andContractFlowEqualTo(power.getContractFlow());
		}
		return this.powerMapper.selectByExample(example);
	}

	@Override
	public String saveContractPower(List<ErpCrmContractOtherPower> powerList, String contractFlow) {
		if(powerList!=null && !powerList.isEmpty()){
			for(ErpCrmContractOtherPower power:powerList){
				if(StringUtil.isBlank(power.getContractFlow())){
					power.setContractFlow(contractFlow);
				}
				if(StringUtil.isNotBlank(power.getOtherPowerFlow())){
					power.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				}
				save(power);
			}
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}

	@Override
	public int save(ErpCrmContractOtherPower power) {
		if(StringUtil.isNotBlank(power.getOtherPowerFlow())){
			GeneralMethod.setRecordInfo(power, false);
			return powerMapper.updateByPrimaryKeySelective(power);
		}else{
			power.setOtherPowerFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(power, true);
			return powerMapper.insertSelective(power);
		}
	}

	@Override
	public ErpCrmContractOtherPower readpower(String powerFlow) {
		return powerMapper.selectByPrimaryKey(powerFlow);
	}

	@Override
	public int deleteOtherPowerByContractFlow(String contractFlow) {
		if (StringUtil.isNotBlank(contractFlow)) {
			ErpCrmContractOtherPowerExample example = new ErpCrmContractOtherPowerExample();
			ErpCrmContractOtherPowerExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			criteria.andContractFlowEqualTo(contractFlow);
			ErpCrmContractOtherPower p=new ErpCrmContractOtherPower();
			p.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			return  powerMapper.updateByExampleSelective(p,example);
		}
		return 0;
	}

}
