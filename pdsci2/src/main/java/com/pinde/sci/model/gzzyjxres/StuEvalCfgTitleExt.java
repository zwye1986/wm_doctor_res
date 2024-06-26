package com.pinde.sci.model.gzzyjxres;

import java.io.Serializable;
import java.util.List;

public class StuEvalCfgTitleExt implements Serializable{

	private String id;
	private String name;
	private String orderNum;
	private List<StuEvalCfgItemExt> itemList;

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

	public List<StuEvalCfgItemExt> getItemList() {
		return itemList;
	}

	public void setItemList(List<StuEvalCfgItemExt> itemList) {
		this.itemList = itemList;
	}
}
