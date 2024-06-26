package com.pinde.sci.form.dwjxres;

import java.io.Serializable;

public class ExportStuUserInfo implements Serializable{

	private String userName;//用户姓名

	private String idNo;//身份证号

	private String schoolSpeName;//学习专业

	private String stuTimeName;//进修时间

	private String speName;//进修专业

	private String batchRegDate;//报到时间

	private String teacherName;//带教老师

	private String isGraduation;//是否结业

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getSchoolSpeName() {
		return schoolSpeName;
	}

	public void setSchoolSpeName(String schoolSpeName) {
		this.schoolSpeName = schoolSpeName;
	}

	public String getStuTimeName() {
		return stuTimeName;
	}

	public void setStuTimeName(String stuTimeName) {
		this.stuTimeName = stuTimeName;
	}

	public String getSpeName() {
		return speName;
	}

	public void setSpeName(String speName) {
		this.speName = speName;
	}

	public String getBatchRegDate() {
		return batchRegDate;
	}

	public void setBatchRegDate(String batchRegDate) {
		this.batchRegDate = batchRegDate;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getIsGraduation() {
		return isGraduation;
	}

	public void setIsGraduation(String isGraduation) {
		this.isGraduation = isGraduation;
	}
}
