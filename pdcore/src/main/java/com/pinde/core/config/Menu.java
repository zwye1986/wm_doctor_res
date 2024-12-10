package com.pinde.core.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Menu implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5959463594648718809L;
	private String menuId;
	private String menuName;
	private String menuUrl;
	private String version;
	private String openType;



	private List<Action> actionList = new ArrayList<Action>();

	public void addAction(Action action) {
		this.actionList.add(action);
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String opentype) {
		this.openType = opentype;
	}

	public List<Action> getActionList() {
		return actionList;
	}

	public void setActionList(List<Action> actionList) {
		this.actionList = actionList;
	}

}
