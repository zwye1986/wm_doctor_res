package com.pinde.sci.form.erp;

import com.pinde.sci.model.erp.ErpCrmContractPowerExt;
import com.pinde.sci.model.erp.ErpCrmCustomerUserExt;
import com.pinde.sci.model.mo.*;

import java.io.Serializable;
import java.util.List;

public class ContractForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5402190973699256795L;

    private ErpCrmContract contract;
    
    private List<ErpCrmContractProduct> productList;
    
    private List<ErpCrmCustomerUser> userList;
    private List<ErpCrmCustomerUserExt> userExtList;
    
    private List<ErpCrmContractPayPlan> payPlanList;

    private List<ErpCrmContractBillPlan> billPlanList;

    private List<ErpCrmContractRef> refList;

    private List<ErpCrmContractPower> powerList;

	private List<ErpCrmContractOtherPower> otherPowerList;

    private List<ErpCrmContractPowerExt> powerExtList;

	public List<ErpCrmCustomerUserExt> getUserExtList() {
		return userExtList;
	}

	public void setUserExtList(List<ErpCrmCustomerUserExt> userExtList) {
		this.userExtList = userExtList;
	}

	public List<ErpCrmContractOtherPower> getOtherPowerList() {
		return otherPowerList;
	}

	public void setOtherPowerList(List<ErpCrmContractOtherPower> otherPowerList) {
		this.otherPowerList = otherPowerList;
	}

	public List<ErpCrmContractPowerExt> getPowerExtList() {
		return powerExtList;
	}

	public void setPowerExtList(List<ErpCrmContractPowerExt> powerExtList) {
		this.powerExtList = powerExtList;
	}

	public List<ErpCrmContractPower> getPowerList() {
		return powerList;
	}

	public void setPowerList(List<ErpCrmContractPower> powerList) {
		this.powerList = powerList;
	}

	public ErpCrmContract getContract() {
		return contract;
	}

	public void setContract(ErpCrmContract contract) {
		this.contract = contract;
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

	public List<ErpCrmContractBillPlan> getBillPlanList() {
		return billPlanList;
	}

	public void setBillPlanList(List<ErpCrmContractBillPlan> billPlanList) {
		this.billPlanList = billPlanList;
	}

	public List<ErpCrmContractRef> getRefList() {
		return refList;
	}

	public void setRefList(List<ErpCrmContractRef> refList) {
		this.refList = refList;
	}

	public List<ErpCrmCustomerUser> getUserList() {
		return userList;
	}

	public void setUserList(List<ErpCrmCustomerUser> userList) {
		this.userList = userList;
	}
    
    
    
}
