package com.pinde.sci.biz.erp.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.IErpContractProductBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ErpCrmContractProductMapper;
import com.pinde.sci.model.mo.ErpCrmContractProduct;
import com.pinde.sci.model.mo.ErpCrmContractProductExample;
import com.pinde.sci.model.mo.ErpCrmContractProductExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ErpContractProductBizImpl implements IErpContractProductBiz{
	@Autowired
	private ErpCrmContractProductMapper productMapper;
	
	@Override
	public List<ErpCrmContractProduct> searchContactProductList(ErpCrmContractProduct product) {
		ErpCrmContractProductExample example=new ErpCrmContractProductExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(product.getContractFlow())){
			criteria.andContractFlowEqualTo(product.getContractFlow());
		}
		return productMapper.selectByExample(example);
	}

	@Override
	public String saveContractProduct(List<ErpCrmContractProduct> productList,String contractFlow) {
		if(productList!=null && !productList.isEmpty()){
			for(ErpCrmContractProduct product:productList){
				if(StringUtil.isBlank(product.getContractFlow())){
					product.setContractFlow(contractFlow);
				}
				if(StringUtil.isNotBlank(product.getProductFlow())){
					ErpCrmContractProduct primitiveProduct=this.readContractProduct(product.getProductFlow());
					product.setRecordStatus(primitiveProduct.getRecordStatus());
					product.setCreateTime(primitiveProduct.getCreateTime());
					product.setCreateUserFlow(primitiveProduct.getCreateUserFlow());
					product.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
					GeneralMethod.setRecordInfo(product, false);
					this.productMapper.updateByPrimaryKey(product);
				}else{
					product.setProductFlow(PkUtil.getUUID());
					GeneralMethod.setRecordInfo(product, true);
					this.productMapper.insertSelective(product);
				}
			}
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}

	@Override
	public String deleteContractProduct(String productFlows) {
		String [] flowArray;
		if(StringUtil.isNotBlank(productFlows)){
			flowArray=productFlows.split(",");
				for(String productFlow:flowArray){
					ErpCrmContractProduct product=readContractProduct(productFlow);
					if(product!=null){
						product.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
						GeneralMethod.setRecordInfo(product, false);
						int result=productMapper.updateByPrimaryKeySelective(product);
						if(result!=GlobalConstant.ONE_LINE){
							return GlobalConstant.DELETE_FAIL;
						}
					}
					
				}
			
		}
		return GlobalConstant.DELETE_SUCCESSED;
	}

	@Override
	public ErpCrmContractProduct readContractProduct(String productFlow) {
		return this.productMapper.selectByPrimaryKey(productFlow);
	}

	@Override
	public int saveContractProduct(ErpCrmContractProduct product) {
		if(StringUtil.isNotBlank(product.getProductFlow())){
			GeneralMethod.setRecordInfo(product, false);
			return productMapper.updateByPrimaryKeySelective(product);
		}else{
			product.setProductFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(product, true);
			return productMapper.insert(product);
		}
	}

	@Override
	public int deleteContractProductByContractFlow(String contractFlow) {
		if(StringUtil.isNotBlank(contractFlow)) {
			ErpCrmContractProduct p = new ErpCrmContractProduct();
			p.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			ErpCrmContractProductExample example = new ErpCrmContractProductExample();
			example.createCriteria().andContractFlowEqualTo(contractFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			return productMapper.updateByExampleSelective(p, example);
		}
		return 0;
	}
}
