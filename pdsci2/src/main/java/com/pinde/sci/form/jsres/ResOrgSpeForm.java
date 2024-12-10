package com.pinde.sci.form.jsres;

import com.pinde.core.model.ResOrgSpe;

import java.util.ArrayList;
import java.util.List;

public class ResOrgSpeForm implements java.io.Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1553421967232984092L;
	
	private String orgFlow;
	private String orgName;
	private List<ResOrgSpe> orgSpeList = new ArrayList<ResOrgSpe>();

	public List<ResOrgSpe> getOrgSpeList() {
		return orgSpeList;
	}

	public void setOrgSpeList(List<ResOrgSpe> orgSpeList) {
		this.orgSpeList = orgSpeList;
	}

	public String getOrgFlow() {
		return orgFlow;
	}

	public void setOrgFlow(String orgFlow) {
		this.orgFlow = orgFlow;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	
}
