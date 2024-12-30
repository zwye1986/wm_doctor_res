package com.pinde.core.model;

import java.util.List;

public class QingpuLectureCfgExt implements java.io.Serializable {

	private static final long serialVersionUID = -4455638586549990099L;
	
	private String recordFlow;
	private List<QingpuLectureCfgItemExt> itemFormList;

	public String getRecordFlow() {
		return recordFlow;
	}
	public void setRecordFlow(String recordFlow) {
		this.recordFlow = recordFlow;
	}
	public List<QingpuLectureCfgItemExt> getItemFormList() {
		return itemFormList;
	}
	public void setItemFormList(List<QingpuLectureCfgItemExt> itemFormList) {
		this.itemFormList = itemFormList;
	}
	
}
