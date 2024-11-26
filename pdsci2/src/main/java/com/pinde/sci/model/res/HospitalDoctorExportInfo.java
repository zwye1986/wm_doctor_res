package com.pinde.sci.model.res;

import java.io.Serializable;

public class HospitalDoctorExportInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;
	
	private String sexName;
	
	private String idNo;
	
	private String userPhone;
	
	private String userEmail;
	
	private String graduatedName;
	
	private String specialized;
	
	private String graduationTime;
	
	private String educationName;
	
	private String examResult;
	
	private String speName;
	private String speId;

	public String getSpeId() {
		return speId;
	}

	public void setSpeId(String speId) {
		this.speId = speId;
	}

	/* 培训基地录取学员导出新增字段扩展 */
	private String sessionNumber;
	private String orgName;
	private String doctorTypeName;
	private String userAddress;
	private String degreeName;
	private String degreeCategoryName;
	private String auditionResult;
	private String operResult;
	private String sumResult;
	private String workOrgName;
	private String userBirthday;
	private String qualifiedNo;

	//20170224扩展字段
	private String cretTypeName;
	private String nationName;
	private String emergencyName;
	private String emergencyPhone;
	private String emergencyRelation;
	private String doctorLicenseFlag;
	private String medicalHeaithOrgName;
	private String hospitalAttrName;
	private String hospitalCategoryName;
	private String baseAttributeName;
	private String basicHealthOrgName;
	private String isYearGraduate;
	private String isGeneralOrderOrientationTrainee;
	private String hasTakenMasterExam;
	private String masterExamResult;
	private String isMaster;
	private String masterGraSchoolName;
	private String masterGraTime;
	private String masterMajor;
	private String masterDegreeName;
	private String masterDegreeTypeName;
	private String isDoctor;
	private String doctorGraSchoolName;
	private String doctorGraTime;
	private String doctorMajor;
	private String doctorDegreeName;
	private String doctorDegreeTypeName;
	private String highestMajor;


	public String getCretTypeName() {
		return cretTypeName;
	}

	public void setCretTypeName(String cretTypeName) {
		this.cretTypeName = cretTypeName;
	}

	public String getNationName() {
		return nationName;
	}

	public void setNationName(String nationName) {
		this.nationName = nationName;
	}

	public String getEmergencyName() {
		return emergencyName;
	}

	public void setEmergencyName(String emergencyName) {
		this.emergencyName = emergencyName;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getEmergencyRelation() {
		return emergencyRelation;
	}

	public void setEmergencyRelation(String emergencyRelation) {
		this.emergencyRelation = emergencyRelation;
	}

	public String getDoctorLicenseFlag() {
		return doctorLicenseFlag;
	}

	public void setDoctorLicenseFlag(String doctorLicenseFlag) {
		this.doctorLicenseFlag = GlobalConstant.FLAG_Y.equals(doctorLicenseFlag)?"是":"否";
	}

	public String getMedicalHeaithOrgName() {
		return medicalHeaithOrgName;
	}

	public void setMedicalHeaithOrgName(String medicalHeaithOrgName) {
		this.medicalHeaithOrgName = medicalHeaithOrgName;
	}

	public String getHospitalAttrName() {
		return hospitalAttrName;
	}

	public void setHospitalAttrName(String hospitalAttrName) {
		this.hospitalAttrName = hospitalAttrName;
	}

	public String getHospitalCategoryName() {
		return hospitalCategoryName;
	}

	public void setHospitalCategoryName(String hospitalCategoryName) {
		this.hospitalCategoryName = hospitalCategoryName;
	}

	public String getBaseAttributeName() {
		return baseAttributeName;
	}

	public void setBaseAttributeName(String baseAttributeName) {
		this.baseAttributeName = baseAttributeName;
	}

	public String getBasicHealthOrgName() {
		return basicHealthOrgName;
	}

	public void setBasicHealthOrgName(String basicHealthOrgName) {
		this.basicHealthOrgName = basicHealthOrgName;
	}

	public String getIsYearGraduate() {
		return isYearGraduate;
	}

	public void setIsYearGraduate(String isYearGraduate) {
		this.isYearGraduate = GlobalConstant.FLAG_Y.equals(isYearGraduate)?"是":"否";
	}

	public String getIsGeneralOrderOrientationTrainee() {
		return isGeneralOrderOrientationTrainee;
	}

	public void setIsGeneralOrderOrientationTrainee(String isGeneralOrderOrientationTrainee) {
		this.isGeneralOrderOrientationTrainee = GlobalConstant.FLAG_Y.equals(isGeneralOrderOrientationTrainee)?"是":"否";
	}

	public String getHasTakenMasterExam() {
		return hasTakenMasterExam;
	}

	public void setHasTakenMasterExam(String hasTakenMasterExam) {
		this.hasTakenMasterExam = hasTakenMasterExam;
	}

	public String getMasterExamResult() {
		return masterExamResult;
	}

	public void setMasterExamResult(String masterExamResult) {
		this.masterExamResult = masterExamResult;
	}

	public String getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(String isMaster) {
		this.isMaster = GlobalConstant.FLAG_Y.equals(isMaster)?"是":"否";
	}

	public String getMasterGraSchoolName() {
		return masterGraSchoolName;
	}

	public void setMasterGraSchoolName(String masterGraSchoolName) {
		this.masterGraSchoolName = masterGraSchoolName;
	}

	public String getMasterGraTime() {
		return masterGraTime;
	}

	public void setMasterGraTime(String masterGraTime) {
		this.masterGraTime = masterGraTime;
	}

	public String getMasterMajor() {
		return masterMajor;
	}

	public void setMasterMajor(String masterMajor) {
		this.masterMajor = masterMajor;
	}

	public String getMasterDegreeName() {
		return masterDegreeName;
	}

	public void setMasterDegreeName(String masterDegreeName) {
		this.masterDegreeName = masterDegreeName;
	}

	public String getMasterDegreeTypeName() {
		return masterDegreeTypeName;
	}

	public void setMasterDegreeTypeName(String masterDegreeTypeName) {
		this.masterDegreeTypeName = masterDegreeTypeName;
	}

	public String getIsDoctor() {
		return isDoctor;
	}

	public void setIsDoctor(String isDoctor) {
		this.isDoctor = GlobalConstant.FLAG_Y.equals(isDoctor)?"是":"否";
	}

	public String getDoctorGraSchoolName() {
		return doctorGraSchoolName;
	}

	public void setDoctorGraSchoolName(String doctorGraSchoolName) {
		this.doctorGraSchoolName = doctorGraSchoolName;
	}

	public String getDoctorGraTime() {
		return doctorGraTime;
	}

	public void setDoctorGraTime(String doctorGraTime) {
		this.doctorGraTime = doctorGraTime;
	}

	public String getDoctorMajor() {
		return doctorMajor;
	}

	public void setDoctorMajor(String doctorMajor) {
		this.doctorMajor = doctorMajor;
	}

	public String getDoctorDegreeName() {
		return doctorDegreeName;
	}

	public void setDoctorDegreeName(String doctorDegreeName) {
		this.doctorDegreeName = doctorDegreeName;
	}

	public String getDoctorDegreeTypeName() {
		return doctorDegreeTypeName;
	}

	public void setDoctorDegreeTypeName(String doctorDegreeTypeName) {
		this.doctorDegreeTypeName = doctorDegreeTypeName;
	}

	public String getHighestMajor() {
		return highestMajor;
	}

	public void setHighestMajor(String highestMajor) {
		this.highestMajor = highestMajor;
	}

	public String getEducationName() {
		return educationName;
	}

	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}

	private String swap;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getSpecialized() {
		return specialized;
	}

	public void setSpecialized(String specialized) {
		this.specialized = specialized;
	}

	public String getGraduatedName() {
		return graduatedName;
	}

	public void setGraduatedName(String graduatedName) {
		this.graduatedName = graduatedName;
	}

	public String getGraduationTime() {
		return graduationTime;
	}

	public void setGraduationTime(String graduationTime) {
		this.graduationTime = graduationTime;
	}

	public String getExamResult() {
		return examResult;
	}

	public void setExamResult(String examResult) {
		this.examResult = examResult;
	}

	public String getSpeName() {
		return speName;
	}

	public void setSpeName(String speName) {
		this.speName = speName;
	}

	public String getSwap() {
		return swap;
	}

	public void setSwap(String swap) {
		if(GlobalConstant.FLAG_Y.equals(swap)){
			this.swap = "是";	
		}else{
			this.swap = "否";
		}
	}

	public String getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(String sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDoctorTypeName() {
		return doctorTypeName;
	}

	public void setDoctorTypeName(String doctorTypeName) {
		this.doctorTypeName = doctorTypeName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public String getDegreeCategoryName() {
		return degreeCategoryName;
	}

	public void setDegreeCategoryName(String degreeCategoryName) {
		this.degreeCategoryName = degreeCategoryName;
	}

	public String getAuditionResult() {
		return auditionResult;
	}

	public void setAuditionResult(String auditionResult) {
		this.auditionResult = auditionResult;
	}

	public String getOperResult() {
		return operResult;
	}

	public void setOperResult(String operResult) {
		this.operResult = operResult;
	}

	public String getSumResult() {
		return sumResult;
	}

	public void setSumResult(String sumResult) {
		this.sumResult = sumResult;
	}

	public String getWorkOrgName() {
		return workOrgName;
	}

	public void setWorkOrgName(String workOrgName) {
		this.workOrgName = workOrgName;
	}

	public String getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getQualifiedNo() {
		return qualifiedNo;
	}

	public void setQualifiedNo(String qualifiedNo) {
		this.qualifiedNo = qualifiedNo;
	}
}
