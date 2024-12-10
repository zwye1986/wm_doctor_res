package com.pinde.sci.model.jsres;

import java.io.Serializable;
import java.util.List;

public class JsEvalCfgExt implements java.io.Serializable {
	
	private String configFlow;
	private List<JsEvalCfgItemExt> itemFormList;

	public String getConfigFlow() {
		return configFlow;
	}

	public void setConfigFlow(String configFlow) {
		this.configFlow = configFlow;
	}

	public List<JsEvalCfgItemExt> getItemFormList() {
		return itemFormList;
	}

	public void setItemFormList(List<JsEvalCfgItemExt> itemFormList) {
		this.itemFormList = itemFormList;
	}
}
