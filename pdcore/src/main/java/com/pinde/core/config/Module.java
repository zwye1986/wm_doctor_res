package com.pinde.core.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Module implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5186058987815802050L;
	private String moduleId;
	private String moduleName;
	private String remark;
	private String view;
	private String version;
	private String hiddenLeft;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	private List<MenuSet> menuSetList = new ArrayList<MenuSet>();

	public List<MenuSet> getMenuSetList() {
		return menuSetList;
	}

	public void setMenuSetList(List<MenuSet> menuSetList) {
		this.menuSetList = menuSetList;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public void addMenuSet(MenuSet menuSet) {
		this.menuSetList.add(menuSet);
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getHiddenLeft() {
		return hiddenLeft;
	}

	public void setHiddenLeft(String hiddenLeft) {
		this.hiddenLeft = hiddenLeft;
	}
}
