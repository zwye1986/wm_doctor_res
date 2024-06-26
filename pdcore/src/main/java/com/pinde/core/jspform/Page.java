package com.pinde.core.jspform;

import java.util.List;

public class Page{
	private String name;
	private String remark;
	private int order;
	private String jsp;
	
	private List<ItemGroup> itemGroupList;
	
	private List<Item> itemList;
	
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

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
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

	public List<ItemGroup> getItemGroupList() {
		return itemGroupList;
	}

	public void setItemGroupList(List<ItemGroup> itemGroupList) {
		this.itemGroupList = itemGroupList;
	}
	
	
}
