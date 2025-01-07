package com.pinde.core.model;

/**
 * 通用Bean
 * id,name,qty
 */
public class GeneralIdNameModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String qty;

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

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}
}
