package com.pinde.sci.model.portal;

import com.pinde.core.model.PortalColumn;
import com.pinde.core.model.PortalInfo;

public class PortalInfoExt extends PortalInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -213115772691048695L;
	/**
	 * 所属栏目
	 */
	private PortalColumn column;

	public PortalColumn getColumn() {
		return column;
	}

	public void setColumn(PortalColumn column) {
		this.column = column;
	}
	
}
