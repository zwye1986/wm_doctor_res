package com.pinde.core.config;

import java.io.Serializable;


public class Action implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8322294799818288016L;
	private String actionId;
	
	private String actionName;
	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
}
