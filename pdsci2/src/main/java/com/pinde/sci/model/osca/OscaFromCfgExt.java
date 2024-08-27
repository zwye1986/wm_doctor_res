package com.pinde.sci.model.osca;

import java.io.Serializable;
import java.util.List;

public class OscaFromCfgExt implements Serializable{

	private static final long serialVersionUID = -4455638586544400099L;
	
	private String fromFlow;
	private List<OscaFromCfgItemExt> itemFormList;

	public String getFromFlow() {
		return fromFlow;
	}
	public void setFromFlow(String fromFlow) {
		this.fromFlow = fromFlow;
	}
	public List<OscaFromCfgItemExt> getItemFormList() {
		return itemFormList;
	}
	public void setItemFormList(List<OscaFromCfgItemExt> itemFormList) {
		this.itemFormList = itemFormList;
	}
	
}
