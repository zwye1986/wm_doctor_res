package com.pinde.sci.biz.erp;

import com.pinde.sci.model.mo.ErpCrmContractProduct;

import java.util.List;


public interface IErpContractProductBiz {
    /**
     * 查询合同产品
     * @param product
     * @return
     */
	List<ErpCrmContractProduct> searchContactProductList(
			ErpCrmContractProduct product);
   
	/**
	 * 保存合同产品
	 * @param productList
	 * @return
	 */
	public String saveContractProduct(List<ErpCrmContractProduct> productList,String contractFlow);
	
	/**
	 * 删除合同产品
	 * @param productFlows
	 * @return
	 */
	public String deleteContractProduct(String productFlows);
	/**
	 * 查询一条产品的详细信息
	 * @param productFlow
	 * @return
	 */
	public ErpCrmContractProduct readContractProduct(String productFlow);

	int saveContractProduct(ErpCrmContractProduct product);

	int deleteContractProductByContractFlow(String contractFlow);
}
