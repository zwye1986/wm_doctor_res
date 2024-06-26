package com.pinde.sci.model.erp;

import com.pinde.sci.model.mo.ErpCrmCustomer;
import com.pinde.sci.model.mo.ErpCrmCustomerUser;

import java.util.List;

public class ErpCrmCustomerExt extends ErpCrmCustomer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -956553850322923223L;

	private List<ErpCrmCustomerUser> userList;

	public List<ErpCrmCustomerUser> getUserList() {
		return userList;
	}

	public void setUserList(List<ErpCrmCustomerUser> userList) {
		this.userList = userList;
	}
}
