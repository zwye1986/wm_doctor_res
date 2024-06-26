package com.pinde.sci.model.erp;

import com.pinde.sci.model.mo.*;

import java.util.List;

public class ErpCrmContractExt extends ErpCrmContract{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 2568696814216395650L;
	
	private ErpCrmCustomer customer;
	
	private List<ErpCrmContractProduct> productList;
	
	private List<ErpCrmContractPayPlan> payPlanList;
	
	private List<ErpCrmContractUser> userList;
	
	private PubFile file;

	public ErpCrmCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(ErpCrmCustomer customer) {
		this.customer = customer;
	}

	public List<ErpCrmContractProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<ErpCrmContractProduct> productList) {
		this.productList = productList;
	}

	public List<ErpCrmContractPayPlan> getPayPlanList() {
		return payPlanList;
	}

	public void setPayPlanList(List<ErpCrmContractPayPlan> payPlanList) {
		this.payPlanList = payPlanList;
	}

	public List<ErpCrmContractUser> getUserList() {
		return userList;
	}

	public void setUserList(List<ErpCrmContractUser> userList) {
		this.userList = userList;
	}

	public PubFile getFile() {
		return file;
	}

	public void setFile(PubFile file) {
		this.file = file;
	}
	
	
	

	
	
	
}
