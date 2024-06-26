package com.pinde.sci.form.jszy;

import com.pinde.sci.form.jsres.BasicInfoForm;
import com.pinde.sci.form.jsres.EducationInfo;
import com.pinde.sci.form.jsres.OrganizationManage;
import com.pinde.sci.form.jsres.SupportCondition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class JszyBaseExtInfoForm {
	/**
	 * 主基地的基本信息
	 */
	private BasicInfoForm basicInfo ;
	/**
	 * 教学信息
	 */
	private EducationInfo educationInfo;
	/**
	 * 组织管理的信息
	 * @return
	 */
	private OrganizationManage organizationManage;
	/**
	 * 支撑条件
	 * @return
	 */
	private SupportCondition supportCondition;
	
	
	
	public EducationInfo getEducationInfo() {
		return educationInfo;
	}
	public void setEducationInfo(EducationInfo educationInfo) {
		this.educationInfo = educationInfo;
	}
	public void setBasicInfo(BasicInfoForm basicInfo) {
		this.basicInfo = basicInfo;
	}
	public BasicInfoForm getBasicInfo() {
		return basicInfo;
	}
	public OrganizationManage getOrganizationManage() {
		return organizationManage;
	}
	public void setOrganizationManage(OrganizationManage organizationManage) {
		this.organizationManage = organizationManage;
	}
	public SupportCondition getSupportCondition() {
		return supportCondition;
	}
	public void setSupportCondition(SupportCondition supportCondition) {
		this.supportCondition = supportCondition;
	}
	
}
