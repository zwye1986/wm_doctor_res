package com.pinde.sci.model.erp;

import com.pinde.sci.model.mo.*;

import java.util.List;

public class ErpCrmContractBillBalanceExt extends ErpCrmContractBillBalance{

	private static final long serialVersionUID = 2568696814216395650L;

	private String createUserName;

	private String modifyUserName;

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}
}