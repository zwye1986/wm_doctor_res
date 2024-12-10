package com.pinde.sci.model.osca;

import java.io.Serializable;
import java.util.List;

public class OscaFromCfgTitleExt implements java.io.Serializable {
	
	/**
	 *
	 */
	private static final long serialVersionUID = -5631934666390814967L;

	private String name;
	private String typeName;
	private String id;
	private String orderNum;

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	private List<OscaFromCfgItemExt> itemList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<OscaFromCfgItemExt> getItemList() {
		return itemList;
	}

	public void setItemList(List<OscaFromCfgItemExt> itemList) {
		this.itemList = itemList;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
