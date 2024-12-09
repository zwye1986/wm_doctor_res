package com.pinde.core.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WorkStation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1861885454018848426L;
	private String workStationId;
	private String workStationName;
	
	private List<Module> moduleList = new ArrayList<Module>();

	public List<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}

	public String getWorkStationId() {
		return workStationId;
	}

	public void setWorkStationId(String workStationId) {
		this.workStationId = workStationId;
	}

	public String getWorkStationName() {
		return workStationName;
	}

	public void setWorkStationName(String workStationName) {
		this.workStationName = workStationName;
	}

	public void addModule(Module module) {
		this.moduleList.add(module);
	}

}
