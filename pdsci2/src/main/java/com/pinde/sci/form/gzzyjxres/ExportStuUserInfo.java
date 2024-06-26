package com.pinde.sci.form.gzzyjxres;

import java.io.Serializable;

public class ExportStuUserInfo implements Serializable{

	private String userName;//用户姓名

	private String idNo;//身份证号

	private String schoolSpeName;//学习专业

	private String stuTimeName;//进修时间

	private String speName;//进修专业

	private String batchRegDate;//报到时间

	//结业时间
	private String certificateDate;

	private String teacherName;//带教老师

	private String education;//学历

	private String titleName;//职称

	private String isGraduation;//是否结业

	private String sendComName;//工作单位

	private String userPhone;//联系电话

	private String stuStartAndEndTime;//进修起止时间
	private String sexName;//性别
	private String userBirthday;//出生年月
	private String certifiedNo;//执业资格证号
	private String tuition;//学费
	private String hotelExpense;//住宿费
	private String beizhu;//备注
	private String userCode;//编号

	private ExtInfoForm extInfoForm;

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

	public String getCertificateDate() {
		return certificateDate;
	}

	public void setCertificateDate(String certificateDate) {
		this.certificateDate = certificateDate;
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

	public ExtInfoForm getExtInfoForm() {
		return extInfoForm;
	}

	public void setExtInfoForm(ExtInfoForm extInfoForm) {
		this.extInfoForm = extInfoForm;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getSendComName() {
		return sendComName;
	}

	public void setSendComName(String sendComName) {
		this.sendComName = sendComName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getStuStartAndEndTime() {
		return stuStartAndEndTime;
	}

	public void setStuStartAndEndTime(String stuStartAndEndTime) {
		this.stuStartAndEndTime = stuStartAndEndTime;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getCertifiedNo() {
		return certifiedNo;
	}

	public void setCertifiedNo(String certifiedNo) {
		this.certifiedNo = certifiedNo;
	}

	public String getTuition() {
		return tuition;
	}

	public void setTuition(String tuition) {
		this.tuition = tuition;
	}

	public String getHotelExpense() {
		return hotelExpense;
	}

	public void setHotelExpense(String hotelExpense) {
		this.hotelExpense = hotelExpense;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
