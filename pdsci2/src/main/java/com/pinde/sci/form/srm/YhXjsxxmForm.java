package com.pinde.sci.form.srm;

import java.io.Serializable;

public class YhXjsxxmForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6755959521151328508L;

	/**
	 * 技术水平
	 */
	private String technologyLevel;
	
	/**
	 * 项目完成例数
	 */
	private String exampleCount1;
	private String exampleCount2;
	private String exampleCount3;
	
	/**
	 * 自评分总分
	 */
	private String selfGradeAmount;

	public String getTechnologyLevel() {
		return technologyLevel;
	}

	public void setTechnologyLevel(String technologyLevel) {
		this.technologyLevel = technologyLevel;
	}

	public String getExampleCount1() {
		return exampleCount1;
	}

	public void setExampleCount1(String exampleCount1) {
		this.exampleCount1 = exampleCount1;
	}

	public String getExampleCount2() {
		return exampleCount2;
	}

	public void setExampleCount2(String exampleCount2) {
		this.exampleCount2 = exampleCount2;
	}

	public String getExampleCount3() {
		return exampleCount3;
	}

	public void setExampleCount3(String exampleCount3) {
		this.exampleCount3 = exampleCount3;
	}

	public String getSelfGradeAmount() {
		return selfGradeAmount;
	}

	public void setSelfGradeAmount(String selfGradeAmount) {
		this.selfGradeAmount = selfGradeAmount;
	}
}
