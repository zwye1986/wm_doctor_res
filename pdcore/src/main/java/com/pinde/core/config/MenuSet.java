package com.pinde.core.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuSet implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3290956577698307720L;
	private String setId;
	private String setName;	
	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	private List<Menu> menuList = new ArrayList<Menu>();

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void addMenu(Menu menu) {
		this.menuList.add(menu);
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public String getSetName() {
		return setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

	public String getSetId() {
		return setId;
	}

	public void setSetId(String setId) {
		this.setId = setId;
	}

}
