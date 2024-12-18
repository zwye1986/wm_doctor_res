package com.pinde.sci.model.res;

import com.pinde.core.model.PubUserResume;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class HbResResumeExtInfoForm extends PubUserResume implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5225098729681455347L;
	
	private String age;

	private String medicalInstitution;

	private String basicmedicalInstitutionType;

	private String hospitalProperty;

	private String hospitalType;

	private String unitProperty;

	private String graduatedCollegeName;

	private String graduatedCollegeYear;

	private String graduatedCollegeMajor;

	private String isGeneralOrderOrientationTrainee;

	private String isMaster;

	private String graduatedMasterName;

	private String graduatedMasterYear;

	private String graduatedMasterMajor;

	private String masterDegreeCategory;

	private String masterDegreeType;

	private String isDoctor;

	private String graduatedDoctorName;

	private String graduatedDoctorYear;

	private String graduatedDoctorMajor;

	private String doctorDegreeCategory;

	private String doctorDegreeType;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getMedicalInstitution() {
		return medicalInstitution;
	}

	public void setMedicalInstitution(String medicalInstitution) {
		this.medicalInstitution = medicalInstitution;
	}

	public String getBasicmedicalInstitutionType() {
		return basicmedicalInstitutionType;
	}

	public void setBasicmedicalInstitutionType(String basicmedicalInstitutionType) {
		this.basicmedicalInstitutionType = basicmedicalInstitutionType;
	}

	public String getHospitalProperty() {
		return hospitalProperty;
	}

	public void setHospitalProperty(String hospitalProperty) {
		this.hospitalProperty = hospitalProperty;
	}

	public String getHospitalType() {
		return hospitalType;
	}

	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}

	public String getUnitProperty() {
		return unitProperty;
	}

	public void setUnitProperty(String unitProperty) {
		this.unitProperty = unitProperty;
	}

	public String getGraduatedCollegeName() {
		return graduatedCollegeName;
	}

	public void setGraduatedCollegeName(String graduatedCollegeName) {
		this.graduatedCollegeName = graduatedCollegeName;
	}

	public String getGraduatedCollegeYear() {
		return graduatedCollegeYear;
	}

	public void setGraduatedCollegeYear(String graduatedCollegeYear) {
		this.graduatedCollegeYear = graduatedCollegeYear;
	}

	public String getGraduatedCollegeMajor() {
		return graduatedCollegeMajor;
	}

	public void setGraduatedCollegeMajor(String graduatedCollegeMajor) {
		this.graduatedCollegeMajor = graduatedCollegeMajor;
	}

	public String getIsGeneralOrderOrientationTrainee() {
		return isGeneralOrderOrientationTrainee;
	}

	public void setIsGeneralOrderOrientationTrainee(String isGeneralOrderOrientationTrainee) {
		this.isGeneralOrderOrientationTrainee = isGeneralOrderOrientationTrainee;
	}

	public String getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(String isMaster) {
		this.isMaster = isMaster;
	}

	public String getGraduatedMasterName() {
		return graduatedMasterName;
	}

	public void setGraduatedMasterName(String graduatedMasterName) {
		this.graduatedMasterName = graduatedMasterName;
	}

	public String getGraduatedMasterYear() {
		return graduatedMasterYear;
	}

	public void setGraduatedMasterYear(String graduatedMasterYear) {
		this.graduatedMasterYear = graduatedMasterYear;
	}

	public String getGraduatedMasterMajor() {
		return graduatedMasterMajor;
	}

	public void setGraduatedMasterMajor(String graduatedMasterMajor) {
		this.graduatedMasterMajor = graduatedMasterMajor;
	}

	public String getMasterDegreeCategory() {
		return masterDegreeCategory;
	}

	public void setMasterDegreeCategory(String masterDegreeCategory) {
		this.masterDegreeCategory = masterDegreeCategory;
	}

	public String getMasterDegreeType() {
		return masterDegreeType;
	}

	public void setMasterDegreeType(String masterDegreeType) {
		this.masterDegreeType = masterDegreeType;
	}

	public String getIsDoctor() {
		return isDoctor;
	}

	public void setIsDoctor(String isDoctor) {
		this.isDoctor = isDoctor;
	}

	public String getGraduatedDoctorName() {
		return graduatedDoctorName;
	}

	public void setGraduatedDoctorName(String graduatedDoctorName) {
		this.graduatedDoctorName = graduatedDoctorName;
	}

	public String getGraduatedDoctorYear() {
		return graduatedDoctorYear;
	}

	public void setGraduatedDoctorYear(String graduatedDoctorYear) {
		this.graduatedDoctorYear = graduatedDoctorYear;
	}

	public String getGraduatedDoctorMajor() {
		return graduatedDoctorMajor;
	}

	public void setGraduatedDoctorMajor(String graduatedDoctorMajor) {
		this.graduatedDoctorMajor = graduatedDoctorMajor;
	}

	public String getDoctorDegreeCategory() {
		return doctorDegreeCategory;
	}

	public void setDoctorDegreeCategory(String doctorDegreeCategory) {
		this.doctorDegreeCategory = doctorDegreeCategory;
	}

	public String getDoctorDegreeType() {
		return doctorDegreeType;
	}

	public void setDoctorDegreeType(String doctorDegreeType) {
		this.doctorDegreeType = doctorDegreeType;
	}
}
