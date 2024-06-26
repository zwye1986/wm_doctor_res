package com.pinde.core.jspform;

import java.util.List;

public class ItemGroup {

	private String name;
	
	private String remark;
	
	private String jsp;
	
	private String flow;
	
	private String modelStyle;
	
	private List<Item> itemList;

	
	public String getModelStyle() {
		return modelStyle;
	}

	public void setModelStyle(String modelStyle) {
		this.modelStyle = modelStyle;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getJsp() {
		return jsp;
	}

	public void setJsp(String jsp) {
		this.jsp = jsp;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	
	
	
	
}
