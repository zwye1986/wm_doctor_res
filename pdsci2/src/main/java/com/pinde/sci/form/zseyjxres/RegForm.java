package com.pinde.sci.form.zseyjxres;

import java.io.Serializable;

public class RegForm implements Serializable{

	/**
	 * 资质证号
	 */
	private String regNo;

	/**
	 * 资质证图片uri
	 */
	private String regUri;

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getRegUri() {
		return regUri;
	}

	public void setRegUri(String regUri) {
		this.regUri = regUri;
	}
}
