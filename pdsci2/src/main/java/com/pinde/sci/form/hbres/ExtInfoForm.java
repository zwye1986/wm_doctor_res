package com.pinde.sci.form.hbres;

import java.io.Serializable;

public class ExtInfoForm implements java.io.Serializable {
	
	private static final long serialVersionUID = 1592918930641343959L;

	/**
	 * 是否应届生
	 */
	private String graduateFlag;

	/**
	 * 单位级别
	 */
	private String unitLevel;

	/**
	 * 单位性质
	 */
	private String unitProperty;

	/**
	 * 医生资格级别
	 */
	private String docQuaLevel;

	/**
	 * 医生资格类别
	 */
	private String docQuaType;

	public String getGraduateFlag() {
		return graduateFlag;
	}

	public void setGraduateFlag(String graduateFlag) {
		this.graduateFlag = graduateFlag;
	}

	public String getUnitLevel() {
		return unitLevel;
	}

	public void setUnitLevel(String unitLevel) {
		this.unitLevel = unitLevel;
	}

	public String getUnitProperty() {
		return unitProperty;
	}

	public void setUnitProperty(String unitProperty) {
		this.unitProperty = unitProperty;
	}

	public String getDocQuaLevel() {
		return docQuaLevel;
	}

	public void setDocQuaLevel(String docQuaLevel) {
		this.docQuaLevel = docQuaLevel;
	}

	public String getDocQuaType() {
		return docQuaType;
	}

	public void setDocQuaType(String docQuaType) {
		this.docQuaType = docQuaType;
	}
}
