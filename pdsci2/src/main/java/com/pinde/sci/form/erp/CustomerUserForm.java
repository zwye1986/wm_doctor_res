package com.pinde.sci.form.erp;

import com.pinde.sci.model.mo.ErpCrmCustomer;
import com.pinde.sci.model.mo.ErpCrmCustomerUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomerUserForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1464420004899904247L;
	
	private List<ErpCrmCustomerUser> customerUserList = new ArrayList<ErpCrmCustomerUser>();
	private ErpCrmCustomer customer;
	
	public List<ErpCrmCustomerUser> getCustomerUserList() {
		return customerUserList;
	}
	public void setCustomerUserList(List<ErpCrmCustomerUser> customerUserList) {
		this.customerUserList = customerUserList;
	}
	public ErpCrmCustomer getCustomer() {
		return customer;
	}
	public void setCustomer(ErpCrmCustomer customer) {
		this.customer = customer;
	}
}
