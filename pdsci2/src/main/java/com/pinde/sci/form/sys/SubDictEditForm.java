package com.pinde.sci.form.sys;

import com.pinde.core.entyties.SysDict;

import java.io.Serializable;
import java.util.List;

public class SubDictEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String topDictFlow;
	
	private SysDict dict;
	
	private List<SysDict> subDicts;

	public String getTopDictFlow() {
		return topDictFlow;
	}

	public void setTopDictFlow(String topDictFlow) {
		this.topDictFlow = topDictFlow;
	}

	public SysDict getDict() {
		return dict;
	}

	public void setDict(SysDict dict) {
		this.dict = dict;
	}

	public List<SysDict> getSubDicts() {
		return subDicts;
	}

	public void setSubDicts(List<SysDict> subDicts) {
		this.subDicts = subDicts;
	}
	
	
}
