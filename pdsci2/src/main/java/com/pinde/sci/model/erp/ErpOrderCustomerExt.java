package com.pinde.sci.model.erp;

import com.pinde.core.model.MybatisObject;
import com.pinde.sci.model.mo.ErpCrmCustomer;
import com.pinde.sci.model.mo.ErpOaContactOrder;
import com.pinde.sci.model.mo.ErpOaWorkOrder;

public class ErpOrderCustomerExt extends ErpOaContactOrder{
     
	/**
	 * 
	 */
	private static final long serialVersionUID = 8950697031093934936L;
	private ErpCrmCustomer customer;

	public ErpCrmCustomer getCustomer() {
		return customer;
	}
	public void setCustomer(ErpCrmCustomer customer) {
		this.customer = customer;
	}
}
