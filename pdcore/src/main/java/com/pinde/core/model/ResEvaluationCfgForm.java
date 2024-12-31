package com.pinde.core.model;

import java.util.List;

public class ResEvaluationCfgForm implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5655638586544400099L;
	
	private String cfgFlow;
	private List<String > depts;
	private List<ResEvaluationCfgItemForm> itemFormList;

    public List<String> getDepts() {
        return depts;
    }

    public void setDepts(List<String> depts) {
        this.depts = depts;
    }

    public String getCfgFlow() {
		return cfgFlow;
	}
	public void setCfgFlow(String cfgFlow) {
		this.cfgFlow = cfgFlow;
	}
	public List<ResEvaluationCfgItemForm> getItemFormList() {
		return itemFormList;
	}
	public void setItemFormList(List<ResEvaluationCfgItemForm> itemFormList) {
		this.itemFormList = itemFormList;
	}
	
}
