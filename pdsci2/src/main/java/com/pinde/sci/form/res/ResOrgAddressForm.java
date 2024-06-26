package com.pinde.sci.form.res;

import com.pinde.sci.model.mo.ResOrgAddress;

import java.io.Serializable;
import java.util.List;

public class ResOrgAddressForm implements Serializable{

	private static final long serialVersionUID = -2420819931570058734L;
	private String orgFlow;
	private String addressType;
	private List<ResOrgAddress> addresses;

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getOrgFlow() {
		return orgFlow;
	}

	public void setOrgFlow(String orgFlow) {
		this.orgFlow = orgFlow;
	}

	public List<ResOrgAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<ResOrgAddress> addresses) {
		this.addresses = addresses;
	}
}
