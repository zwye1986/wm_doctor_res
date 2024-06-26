package com.pinde.sci.model.portal;

import com.pinde.sci.model.mo.PortalColumn;

public class PortalColumnExt extends PortalColumn {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4596854929305592253L;
	/**
	 * 所属父栏目
	 */
	private PortalColumn parentColumn ;

	public PortalColumn getParentColumn() {
		return parentColumn;
	}

	public void setParentColumn(PortalColumn parentColumn) {
		this.parentColumn = parentColumn;
	}
	
	
}
