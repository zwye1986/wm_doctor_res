package com.pinde.sci.form.jsres;

import java.io.Serializable;

public class ContactorInfoForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 联系人的form
	 * @author Administrator
	 */
	/**
	 * 姓名
	 */
	private String contactorName;
	/**
	 * 科室
	 */
	private String dept;
	/**
	 * 固定电话
	 */
	private String phone;
	/**
	 * 手机电话
	 */
	private String mobilephone;

	/**
	 * 固定电话
	 */
	private String fixedPhone;
	/**
	 * 职务
	 */
	private String job;
	/**
	 * 邮箱
	 */
	private String mailAddress;
	// 职称ID
	private String titleId;
	// 职称
	private String titleName;
	// 职务ID
	private String postId;
	// 职务
	private String postName;

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getContactorName() {
		return contactorName;
	}
	public void setContactorName(String contactorName) {
		this.contactorName = contactorName;
	}

	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}

	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getFixedPhone() {
		return fixedPhone;
	}

	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}
}
