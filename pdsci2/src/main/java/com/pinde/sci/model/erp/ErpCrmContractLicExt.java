package com.pinde.sci.model.erp;

import com.pinde.sci.model.mo.*;

import java.util.List;

public class ErpCrmContractLicExt extends ErpCrmContract{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 2568696814216395650L;

	private ErpCrmCustomer customer;
	private ErpOaLicKey licKey;

	public ErpOaLicKey getLicKey() {
		return licKey;
	}

	public void setLicKey(ErpOaLicKey licKey) {
		this.licKey = licKey;
	}

	public ErpCrmCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(ErpCrmCustomer customer) {
		this.customer = customer;
	}

	
}
