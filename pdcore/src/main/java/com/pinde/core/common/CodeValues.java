package com.pinde.core.common;

import java.io.Serializable;

public class CodeValues implements java.io.Serializable {
	
	private static final long serialVersionUID = -8952391575732413501L;
	
	private String text;
	private String newLine;
	private String remark;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
