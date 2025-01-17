package com.pinde.core.model;

import java.util.List;

public class JsEvalCfgTitleExt implements java.io.Serializable {

	private String id;
	private String name;
	private String orderNum;
	private List<JsEvalCfgItemExt> itemList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public List<JsEvalCfgItemExt> getItemList() {
		return itemList;
	}

	public void setItemList(List<JsEvalCfgItemExt> itemList) {
		this.itemList = itemList;
	}
}
