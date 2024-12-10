package com.pinde.sci.form.hbzy;

import com.pinde.sci.model.mo.PubUserResume;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class BaseUserResumeExtInfoForm extends PubUserResume implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5225098729470455347L;
	
	/**
	 * 固定电话
	 */
	private String telephone;
	/**
	 * 紧急联系人关系
	 */
	private String emergencyRelation;
	/**
	 * 紧急联系人地址
	 */
	private String emergencyAddress;
	
	//**************** 教育情况   ********************
	/**
	 * 派送学校
	 */
	private String workSchoolName;
	/**
	 * 单位级别
	 */
	private String orgRank;
	/**
	 * 单位等级
	 */
	private String orgLevel;
	/**
	 * 本科毕业院校Id
	 */
	private String graduatedId;
	/**
	 * 本科毕业院校
	 */
	private String graduatedName;
	
	/**
	 * 本科毕业专业
	 */
	private String specialized;
	/**
	 * 专/本科毕业专业（湖北招录将specialized改成zbkbySpe）
	 */
	private String zbkbySpe;
	
	/**
	 * 本科毕业时间
	 */
	private String graduationTime;
	
	/**
	 * 学院证书编号
	 */
	private String collegeCertificateNo;
	
	/**
	 * 毕业证书URI
	 */
	private String certificateUri;
	
	/**
	 * 学位证书URI
	 */
	private String degreeUri;
	/**
	 * 学历Id
	 */
	private String degreeId;
	/**
	 * 学历Name
	 */
	private String degreeName;
	/**
	 * 是否参加研究生考试
	 */
	private String hasTakenMasterExam;
	/**
	 * 考试成绩证明材料uri
	 */
	private String examCertificateUri;
	//是否参加全省住培考试
	private String isJoinTest;
	/**
	 * 研究生考试成绩
	 */
	private String masterExamResult;
	/**
	 * 是否为硕士研究生
	 */
	private String isMaster;
	/**
	 * 硕士研究生状态1：在读，2：已毕业。
	 */
	private String masterStatue;
	/**
	 * 硕士学位ID
	 */
	private String masterDegreeId;
	/**
	 * 硕士学位Name
	 */
	private String masterDegreeName;
	/**
	 * 硕士学位类型Id
	 */
	private String masterDegreeTypeId;
	/**
	 * 硕士学位类型Name
	 */
	private String masterDegreeTypeName;
	/**
	 * 硕士毕业院校ID
	 */
	private String masterGraSchoolId;
	/**
	 * 硕士毕业院校Name
	 */
	private String masterGraSchoolName;
	/**
	 * 硕士入学时间
	 */
	private String 	masterStartTime;
	/**
	 * 硕士毕业院校时间
	 */
	private String 	masterGraTime;
	/**
	 * 硕士毕业专业
	 */
	private String masterMajor;
	/**
	 * 是否为博士研究生
	 */
	private String isDoctor;
	/**
	 * 博士研究生状态1：在读，2：已毕业。
	 */
	private String doctorStatue;
	/**
	 * 博士学位ID
	 */
	private String doctorDegreeId;
	/**
	 * 博士学位Name
	 */
	private String doctorDegreeName;
	/**
	 * 博士学位类型Id
	 */
	private String doctorDegreeTypeId;
	/**
	 * 博士学位类型Name
	 */
	private String doctorDegreeTypeName;
	/**
	 * 博士毕业院校ID
	 */
	private String doctorGraSchoolId;
	/**
	 * 博士毕业院校Name
	 */
	private String doctorGraSchoolName;
	/**
	 * 博士入学时间
	 */
	private String doctorStartTime;
	/**
	 * 博士毕业院校时间
	 */
	private String 	doctorGraTime;
	/**
	 * 博士毕业专业
	 */
	private String doctorMajor;
	
	/**
	 * 医疗卫生机构Id
	 */
	private String medicalHeaithOrgId;
	/**
	 * 医疗卫生机构Name
	 */
	private String medicalHeaithOrgName;
	/**
	 *医院属性Id
	 */
	private String hospitalAttrId;
	/**
	 *医院属性Name
	 */
	private String hospitalAttrName;
	/**
	 * 医院类别Id
	 */
	private String hospitalCategoryId;
	/**
	 * 医院类别Name
	 */
	private String hospitalCategoryName;
	/**
	 * 单位性质Id
	 */
	private String baseAttributeId;
	/**
	 * 单位性质Name
	 */
	private String baseAttributeName;
	/**
	 * 基层医疗卫生医院Id
	 */
	private String basicHealthOrgId;
	/**
	 * 基层医疗卫生医院Name
	 */
	private String basicHealthOrgName;
	
	//*****************   现有资格信息     *********************
	
	/**
	 * 专业技术资格
	 */
	private String technologyQualificationId;
	private String technologyQualificationName;
	
	/**
	 * 取得日期
	 */
	private String getTechnologyQualificationDate;
	
	/**
	 * 执业资格材料
	 */
	private String qualificationMaterialId;
	private String qualificationMaterialName;
	
	/**
	 * 资格材料编码
	 */
	private String qualificationMaterialCode;
	
	/**
	 * 资格材料编码 
	 */
	private String qualificationMaterialUri;
	
	/**
	 * 执业类别
	 */
	private String practicingCategoryId;
	private String practicingCategoryName;
	
	/**
	 * 执业范围
	 */
	private String practicingScopeId;
	private String practicingScopeName;
	private String practicingScope;//职业范围(医师基本信息所需字段)
	/**
	 * 基层医疗卫生机构等级Id
	 */
	private String basicHealthOrgLevelId;
	/**
	 * 基层医疗卫生机构等级Name
	 */
	private String basicHealthOrgLevelName;
	//是否取得职业医师证
	private String qualifiedNoFlag;
	//人员属性ID
	private String personnelAttributeId;
	//人员属性NAME
	private String personnelAttributeName;
	//是否为援疆援藏医师
	private String isAssistance;
	//援疆援藏住院医师送出省份
	private String assistanceProvince;
	//援疆援藏住院医师送出单位统一社会信用代码/组织机构代码
	private String assistanceCode;


	//***************************************************************


	public String getIsAssistance() {
		return isAssistance;
	}

	public void setIsAssistance(String isAssistance) {
		this.isAssistance = isAssistance;
	}

	public String getAssistanceProvince() {
		return assistanceProvince;
	}

	public void setAssistanceProvince(String assistanceProvince) {
		this.assistanceProvince = assistanceProvince;
	}

	public String getAssistanceCode() {
		return assistanceCode;
	}

	public void setAssistanceCode(String assistanceCode) {
		this.assistanceCode = assistanceCode;
	}

	public String getPersonnelAttributeId() {
		return personnelAttributeId;
	}

	public void setPersonnelAttributeId(String personnelAttributeId) {
		this.personnelAttributeId = personnelAttributeId;
	}

	public String getPersonnelAttributeName() {
		return personnelAttributeName;
	}

	public void setPersonnelAttributeName(String personnelAttributeName) {
		this.personnelAttributeName = personnelAttributeName;
	}

	public String getQualifiedNoFlag() {
		return qualifiedNoFlag;
	}

	public void setQualifiedNoFlag(String qualifiedNoFlag) {
		this.qualifiedNoFlag = qualifiedNoFlag;
	}
	public String getPracticingScopeId() {
		return practicingScopeId;
	}

	public void setPracticingScopeId(String practicingScopeId) {
		this.practicingScopeId = practicingScopeId;
	}

	public String getPracticingScopeName() {
		return practicingScopeName;
	}

	public void setPracticingScopeName(String practicingScopeName) {
		this.practicingScopeName = practicingScopeName;
	}


	

	
	public String getTelephone() {
		return telephone;
	}

	public String getMedicalHeaithOrgId() {
		return medicalHeaithOrgId;
	}

	public void setMedicalHeaithOrgId(String medicalHeaithOrgId) {
		this.medicalHeaithOrgId = medicalHeaithOrgId;
	}

	public String getMedicalHeaithOrgName() {
		return medicalHeaithOrgName;
	}

	public void setMedicalHeaithOrgName(String medicalHeaithOrgName) {
		this.medicalHeaithOrgName = medicalHeaithOrgName;
	}

	public String getHospitalAttrId() {
		return hospitalAttrId;
	}

	public void setHospitalAttrId(String hospitalAttrId) {
		this.hospitalAttrId = hospitalAttrId;
	}

	public String getHospitalAttrName() {
		return hospitalAttrName;
	}

	public void setHospitalAttrName(String hospitalAttrName) {
		this.hospitalAttrName = hospitalAttrName;
	}

	public String getHospitalCategoryId() {
		return hospitalCategoryId;
	}

	public void setHospitalCategoryId(String hospitalCategoryId) {
		this.hospitalCategoryId = hospitalCategoryId;
	}

	public String getHospitalCategoryName() {
		return hospitalCategoryName;
	}

	public void setHospitalCategoryName(String hospitalCategoryName) {
		this.hospitalCategoryName = hospitalCategoryName;
	}

	public String getBaseAttributeId() {
		return baseAttributeId;
	}

	public void setBaseAttributeId(String baseAttributeId) {
		this.baseAttributeId = baseAttributeId;
	}

	public String getBaseAttributeName() {
		return baseAttributeName;
	}

	public void setBaseAttributeName(String baseAttributeName) {
		this.baseAttributeName = baseAttributeName;
	}

	public String getBasicHealthOrgId() {
		return basicHealthOrgId;
	}

	public void setBasicHealthOrgId(String basicHealthOrgId) {
		this.basicHealthOrgId = basicHealthOrgId;
	}

	public String getBasicHealthOrgName() {
		return basicHealthOrgName;
	}

	public void setBasicHealthOrgName(String basicHealthOrgName) {
		this.basicHealthOrgName = basicHealthOrgName;
	}

	public String getMasterMajor() {
		return masterMajor;
	}

	public void setMasterMajor(String masterMajor) {
		this.masterMajor = masterMajor;
	}

	public String getDoctorMajor() {
		return doctorMajor;
	}

	public void setDoctorMajor(String doctorMajor) {
		this.doctorMajor = doctorMajor;
	}

	public String getMasterDegreeId() {
		return masterDegreeId;
	}

	public void setMasterDegreeId(String masterDegreeId) {
		this.masterDegreeId = masterDegreeId;
	}

	public String getMasterDegreeName() {
		return masterDegreeName;
	}

	public void setMasterDegreeName(String masterDegreeName) {
		this.masterDegreeName = masterDegreeName;
	}

	public String getMasterDegreeTypeId() {
		return masterDegreeTypeId;
	}

	public void setMasterDegreeTypeId(String masterDegreeTypeId) {
		this.masterDegreeTypeId = masterDegreeTypeId;
	}

	public String getMasterDegreeTypeName() {
		return masterDegreeTypeName;
	}

	public void setMasterDegreeTypeName(String masterDegreeTypeName) {
		this.masterDegreeTypeName = masterDegreeTypeName;
	}

	public String getMasterGraSchoolId() {
		return masterGraSchoolId;
	}

	public void setMasterGraSchoolId(String masterGraSchoolId) {
		this.masterGraSchoolId = masterGraSchoolId;
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

	public String getDoctorDegreeId() {
		return doctorDegreeId;
	}

	public void setDoctorDegreeId(String doctorDegreeId) {
		this.doctorDegreeId = doctorDegreeId;
	}

	public String getDoctorDegreeName() {
		return doctorDegreeName;
	}

	public void setDoctorDegreeName(String doctorDegreeName) {
		this.doctorDegreeName = doctorDegreeName;
	}

	public String getDoctorDegreeTypeId() {
		return doctorDegreeTypeId;
	}

	public void setDoctorDegreeTypeId(String doctorDegreeTypeId) {
		this.doctorDegreeTypeId = doctorDegreeTypeId;
	}

	public String getDoctorDegreeTypeName() {
		return doctorDegreeTypeName;
	}

	public void setDoctorDegreeTypeName(String doctorDegreeTypeName) {
		this.doctorDegreeTypeName = doctorDegreeTypeName;
	}

	public String getDoctorGraSchoolId() {
		return doctorGraSchoolId;
	}

	public void setDoctorGraSchoolId(String doctorGraSchoolId) {
		this.doctorGraSchoolId = doctorGraSchoolId;
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

	public String getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(String isMaster) {
		this.isMaster = isMaster;
	}

	public String getIsDoctor() {
		return isDoctor;
	}

	public void setIsDoctor(String isDoctor) {
		this.isDoctor = isDoctor;
	}

	public String getDegreeId() {
		return degreeId;
	}

	public void setDegreeId(String degreeId) {
		this.degreeId = degreeId;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public String getGraduatedId() {
		return graduatedId;
	}

	public void setGraduatedId(String graduatedId) {
		this.graduatedId = graduatedId;
	}

	public String getOrgRank() {
		return orgRank;
	}

	public void setOrgRank(String orgRank) {
		this.orgRank = orgRank;
	}

	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}
	public String getWorkSchoolName() {
		return workSchoolName;
	}

	public void setWorkSchoolName(String workSchoolName) {
		this.workSchoolName = workSchoolName;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmergencyAddress() {
		return emergencyAddress;
	}

	public void setEmergencyAddress(String emergencyAddress) {
		this.emergencyAddress = emergencyAddress;
	}

	public String getCollegeCertificateNo() {
		return collegeCertificateNo;
	}

	public void setCollegeCertificateNo(String collegeCertificateNo) {
		this.collegeCertificateNo = collegeCertificateNo;
	}


	public String getTechnologyQualificationId() {
		return technologyQualificationId;
	}

	public void setTechnologyQualificationId(String technologyQualificationId) {
		this.technologyQualificationId = technologyQualificationId;
	}

	public String getTechnologyQualificationName() {
		return technologyQualificationName;
	}

	public void setTechnologyQualificationName(String technologyQualificationName) {
		this.technologyQualificationName = technologyQualificationName;
	}

	public String getQualificationMaterialId() {
		return qualificationMaterialId;
	}

	public void setQualificationMaterialId(String qualificationMaterialId) {
		this.qualificationMaterialId = qualificationMaterialId;
	}

	public String getQualificationMaterialName() {
		return qualificationMaterialName;
	}

	public void setQualificationMaterialName(String qualificationMaterialName) {
		this.qualificationMaterialName = qualificationMaterialName;
	}

	public String getPracticingCategoryId() {
		return practicingCategoryId;
	}

	public void setPracticingCategoryId(String practicingCategoryId) {
		this.practicingCategoryId = practicingCategoryId;
	}

	public String getPracticingCategoryName() {
		return practicingCategoryName;
	}

	public void setPracticingCategoryName(String practicingCategoryName) {
		this.practicingCategoryName = practicingCategoryName;
	}

	public String getGetTechnologyQualificationDate() {
		return getTechnologyQualificationDate;
	}

	public void setGetTechnologyQualificationDate(
			String getTechnologyQualificationDate) {
		this.getTechnologyQualificationDate = getTechnologyQualificationDate;
	}


	public String getQualificationMaterialCode() {
		return qualificationMaterialCode;
	}

	public void setQualificationMaterialCode(String qualificationMaterialCode) {
		this.qualificationMaterialCode = qualificationMaterialCode;
	}

	public String getCertificateUri() {
		return certificateUri;
	}

	public void setCertificateUri(String certificateUri) {
		this.certificateUri = certificateUri;
	}

	public String getDegreeUri() {
		return degreeUri;
	}

	public void setDegreeUri(String degreeUri) {
		this.degreeUri = degreeUri;
	}

	public String getGraduatedName() {
		return graduatedName;
	}

	public void setGraduatedName(String graduatedName) {
		this.graduatedName = graduatedName;
	}

	public String getSpecialized() {
		return specialized;
	}

	public void setSpecialized(String specialized) {
		this.specialized = specialized;
	}

	public String getGraduationTime() {
		return graduationTime;
	}

	public void setGraduationTime(String graduationTime) {
		this.graduationTime = graduationTime;
	}

	public String getQualificationMaterialUri() {
		return qualificationMaterialUri;
	}

	public void setQualificationMaterialUri(String qualificationMaterialUri) {
		this.qualificationMaterialUri = qualificationMaterialUri;
	}

	public String getPracticingScope() {
		return practicingScope;
	}

	public void setPracticingScope(String practicingScope) {
		this.practicingScope = practicingScope;
	}
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


	private String graduatedMasterName;

	private String graduatedMasterYear;

	private String graduatedMasterMajor;

	private String masterDegreeCategory;

	private String masterDegreeType;


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

	public String getExamCertificateUri() {
		return examCertificateUri;
	}

	public void setExamCertificateUri(String examCertificateUri) {
		this.examCertificateUri = examCertificateUri;
	}

	public String getIsJoinTest() {
		return isJoinTest;
	}

	public void setIsJoinTest(String isJoinTest) {
		this.isJoinTest = isJoinTest;
	}

	public String getZbkbySpe() {
		return zbkbySpe;
	}

	public void setZbkbySpe(String zbkbySpe) {
		this.zbkbySpe = zbkbySpe;
	}

	public String getBasicHealthOrgLevelId() {
		return basicHealthOrgLevelId;
	}

	public void setBasicHealthOrgLevelId(String basicHealthOrgLevelId) {
		this.basicHealthOrgLevelId = basicHealthOrgLevelId;
	}

	public String getBasicHealthOrgLevelName() {
		return basicHealthOrgLevelName;
	}

	public void setBasicHealthOrgLevelName(String basicHealthOrgLevelName) {
		this.basicHealthOrgLevelName = basicHealthOrgLevelName;
	}

	public String getDoctorStartTime() {
		return doctorStartTime;
	}

	public void setDoctorStartTime(String doctorStartTime) {
		this.doctorStartTime = doctorStartTime;
	}

	public String getDoctorStatue() {
		return doctorStatue;
	}

	public void setDoctorStatue(String doctorStatue) {
		this.doctorStatue = doctorStatue;
	}

	public String getMasterStartTime() {
		return masterStartTime;
	}

	public void setMasterStartTime(String masterStartTime) {
		this.masterStartTime = masterStartTime;
	}

	public String getMasterStatue() {
		return masterStatue;
	}

	public void setMasterStatue(String masterStatue) {
		this.masterStatue = masterStatue;
	}
	public String getEmergencyRelation() {
		return emergencyRelation;
	}

	public void setEmergencyRelation(String emergencyRelation) {
		this.emergencyRelation = emergencyRelation;
	}

}
