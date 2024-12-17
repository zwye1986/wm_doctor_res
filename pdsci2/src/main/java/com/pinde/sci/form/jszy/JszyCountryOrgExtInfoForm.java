package com.pinde.sci.form.jszy;

import com.pinde.core.model.PubUserResume;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class JszyCountryOrgExtInfoForm extends PubUserResume implements java.io.Serializable {

	//带教科室名称
	private String deptName;
	//床位数
	private String bedNum;
	//带教师资数
	private String teaNum;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getBedNum() {
		return bedNum;
	}

	public void setBedNum(String bedNum) {
		this.bedNum = bedNum;
	}

	public String getTeaNum() {
		return teaNum;
	}

	public void setTeaNum(String teaNum) {
		this.teaNum = teaNum;
	}
}
