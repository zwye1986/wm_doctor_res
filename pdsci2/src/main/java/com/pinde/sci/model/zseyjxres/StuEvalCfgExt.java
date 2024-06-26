package com.pinde.sci.model.zseyjxres;

import java.io.Serializable;
import java.util.List;

public class StuEvalCfgExt implements Serializable{
	
	private String configFlow;
	private List<StuEvalCfgItemExt> itemFormList;

	public String getConfigFlow() {
		return configFlow;
	}

	public void setConfigFlow(String configFlow) {
		this.configFlow = configFlow;
	}

	public List<StuEvalCfgItemExt> getItemFormList() {
		return itemFormList;
	}

	public void setItemFormList(List<StuEvalCfgItemExt> itemFormList) {
		this.itemFormList = itemFormList;
	}
}
