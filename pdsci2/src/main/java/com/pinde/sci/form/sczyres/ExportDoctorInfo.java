package com.pinde.sci.form.sczyres;

import java.io.Serializable;

public class ExportDoctorInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String orgName;//基地名称
	
	private String doctorName;//姓名
	
	private String sex;//性别

	private String age;//年龄
	
	private String idNo;//省份证号

	private String nationName;//民族

	private String userPhone;//手机号

	private String email;//邮箱

	private String qqCode;//QQ

	private String catSpeName;//专业

	private String speName;//对应专业

	public String getSecondSpeName() {
		return secondSpeName;
	}

	public void setSecondSpeName(String secondSpeName) {
		this.secondSpeName = secondSpeName;
	}

	public String getCatSpeName() {
		return catSpeName;
	}

	public void setCatSpeName(String catSpeName) {
		this.catSpeName = catSpeName;
	}

	private String secondSpeName;//对应专业

	private String doctorLicenseFlag;//是否执业医师

	private String qualifiedNo;//执业医师资格证号

	private String doctorType;//人员类型

	private String educationName;//最高学历

	public String getEducationName() {
		return educationName;
	}

	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}

	private String graduatedName;//毕业院校（本科）

	private String graduationTime;//毕业年份（本科）

	private String specialized;//毕业专业（本科）

	private String degreeName;//学位（本科）

	private String maSchool;//毕业院校（硕士）

	private String maDate;//毕业年份（硕士）

	private String maMajor;//毕业专业（硕士）

	private String maDegreeName;//学位（硕士）

	private String maDegreeTypeName;//学位类型（硕士）

	private String workOrgName;//工作单位

	private String medicalHeaithOrgName;//医疗卫生机构

	private String hospitalAttrName;//医院属性

	private String hospitalCategoryName;//医院类别

	private String baseAttributeName;//单位性质

	private String basicHealthOrgName;//基层医疗卫生机构

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getNationName() {
		return nationName;
	}

	public void setNationName(String nationName) {
		this.nationName = nationName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQqCode() {
		return qqCode;
	}

	public void setQqCode(String qqCode) {
		this.qqCode = qqCode;
	}

	public String getSpeName() {
		return speName;
	}

	public void setSpeName(String speName) {
		this.speName = speName;
	}

	public String getDoctorLicenseFlag() {
		return doctorLicenseFlag;
	}

	public void setDoctorLicenseFlag(String doctorLicenseFlag) {
		this.doctorLicenseFlag = doctorLicenseFlag;
	}

	public String getQualifiedNo() {
		return qualifiedNo;
	}

	public void setQualifiedNo(String qualifiedNo) {
		this.qualifiedNo = qualifiedNo;
	}

	public String getDoctorType() {
		return doctorType;
	}

	public void setDoctorType(String doctorType) {
		this.doctorType = doctorType;
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

	public String getSpecialized() {
		return specialized;
	}

	public void setSpecialized(String specialized) {
		this.specialized = specialized;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public String getMaSchool() {
		return maSchool;
	}

	public void setMaSchool(String maSchool) {
		this.maSchool = maSchool;
	}

	public String getMaDate() {
		return maDate;
	}

	public void setMaDate(String maDate) {
		this.maDate = maDate;
	}

	public String getMaMajor() {
		return maMajor;
	}

	public void setMaMajor(String maMajor) {
		this.maMajor = maMajor;
	}

	public String getMaDegreeName() {
		return maDegreeName;
	}

	public void setMaDegreeName(String maDegreeName) {
		this.maDegreeName = maDegreeName;
	}

	public String getMaDegreeTypeName() {
		return maDegreeTypeName;
	}

	public void setMaDegreeTypeName(String maDegreeTypeName) {
		this.maDegreeTypeName = maDegreeTypeName;
	}

	public String getWorkOrgName() {
		return workOrgName;
	}

	public void setWorkOrgName(String workOrgName) {
		this.workOrgName = workOrgName;
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
}
