package com.pinde.sci.form.sczyres;

import java.io.Serializable;
import java.util.List;

public class ExtInfoForm implements Serializable{
	
	private static final long serialVersionUID = 1592918930641343959L;

	/**
	 * 籍贯
	 */
	private String nativePlace;
	
	/**
	 * 民族
	 */
	private String nation;
	
	/**
	 * 健康状况
	 */
	private String healthStatus;
	
	/**
	 * 政治面貌
	 */
	private String political;
	
	/**
	 * 婚姻状况
	 */
	private String maritalStatus;
	
	/**
	 * 既往病史
	 */
	private String beforeCase;
	
	/**
	 * 社会工作
	 */
	private String societyWork;
	
	/**
	 * 是否应届生
	 */
	private String yearGraduateFlag;
	
	/**
	 * 家庭住址省id
	 */
	private String homeProvId;
	
	/**
	 * 家庭住址省name
	 */
	private String homeProvName;
	
	/**
	 * 家庭住址市id
	 */
	private String homeCityId;
	
	/**
	 * 家庭住址市name
	 */
	private String homeCityName;
	
	/**
	 * 家庭住址地区id
	 */
	private String homeAreaId;
	
	/**
	 * 家庭住址地区name
	 */
	private String homeAreaName;
	/**
	 * 生源地省id
	 */
	private String birthProvId;

	/**
	 * 生源地省name
	 */
	private String birthProvName;

	/**
	 * 生源地市id
	 */
	private String birthCityId;

	/**
	 * 生源地市name
	 */
	private String birthCityName;

	/**
	 * 生源地区id
	 */
	private String birthAreaId;

	/**
	 * 生源地区name
	 */
	private String birthAreaName;
	
	/**
	 * 家庭住址
	 */
	private String homeAddress;
	
	/**
	 * 家庭电话
	 */
	private String homePhome;
	
	/**
	 * 邮编
	 */
	private String zipCode;
	
	/**
	 * 其他方式
	 */
	private String otherWay;
	
	/**
	 * 身份证图片uri
	 */
	private String idNoUri;
	
	/**
	 * 毕业证书url
	 */
	private String certificateUri;
	
	/**
	 * 学位证书uri
	 */
	private String degreeUri;

	/**
	 * 医师资格证uri
	 */
	private String qualifiedUri;

	/**
	 * 医师执业证书uri
	 */
	private String regUri;

	/**
	 * QQ号
	 */
	 private String qqCode;
	/**
	 * 学历Id
	 */
	private String degreeId;

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

	/**
	 * 学历Name
	 */
	private String degreeName;
	/**
	 * 是否为硕士研究生
	 */
	private String isMaster;
	/**
	 * 硕士研究生状态1：在读，2：已毕业。
	 */
	private String masterStatue;
	 /**
     * 硕士研究生阶段 毕业学校
     */
	private String maSchool;
	/**
	 * 硕士入学时间
	 */
	private String 	masterStartTime;
	/**
	 * 硕士研究生阶段 毕业时间
	 */
	private String maDate;

	/**
	 * 硕士研究生阶段 毕业专业
	 */
	private String maMajor;
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
	 * 是否为博士研究生
	 */
	private String isDoctor;
	/**
	 * 博士研究生状态1：在读，2：已毕业。
	 */
	private String doctorStatue;
	/**
	 * 博士研究生阶段 毕业学校
	 */
	private String phdSchool;
	/**
	 * 博士入学时间
	 */
	private String doctorStartTime;
	/**
	 * 博士研究生阶段 毕业时间
	 */
	private String phdDate;

	/**
	 * 博士研究生阶段 毕业专业
	 */
	private String phdMajor;

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

//	/**
//	 * 医疗卫生机构
//	 */
//	private String medicalHeaithOrg;
//
//	/**
//	 * 医院属性
//	 */
//	private String hospitalAttr;
//
//	/**
//	 * 医院类别
//	 */
//	private String hospitalCategory;
//
//	/**
//	 * 单位性质
//	 */
//	private String baseAttribute;
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
	/**
	 * 基层医疗卫生机构等级Id
	 */
	private String basicHealthOrgLevelId;
	/**
	 * 基层医疗卫生机构等级Name
	 */
	private String basicHealthOrgLevelName;

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
	//	/**
//	 * 基层医疗卫生机构
//	 */
//	private String basicHealthOrg;

	/**
	 * 工作经历
	 */
	private List<WorkResumeForm> workResumeList;

	/**
	 * 培训开始时间
	 */
	private String trainingStartDate;

	/**
	 * 培训结束时间
	 */
	private String trainingEndDate;

	/**
	 * 培训登记手册完成情况
     */
	private String handBookInfo;
	//定向培养
	private String isGeneralOrderOrientationTrainee;
	//协同单位名称
	private String coordinationHospitalName;
	//协同单位等级
	private String coordinationBaseAttributeId;
	private String coordinationBaseAttributeName;
	//助理医师资格证书编号
	private String assistantQualificationCertificateCode;
	//是否补考人员
	private String isMakeUp;
	//是否协同单位
	private String isCoordinationBase;
	//补考类型
	private String makeUpTypeId;
	private String makeUpTypeName;

	public String getMakeUpTypeName() {
		return makeUpTypeName;
	}

	public void setMakeUpTypeName(String makeUpTypeName) {
		this.makeUpTypeName = makeUpTypeName;
	}

	public String getMakeUpTypeId() {
		return makeUpTypeId;
	}

	public void setMakeUpTypeId(String makeUpTypeId) {
		this.makeUpTypeId = makeUpTypeId;
	}

	public String getIsCoordinationBase() {
		return isCoordinationBase;
	}

	public void setIsCoordinationBase(String isCoordinationBase) {
		this.isCoordinationBase = isCoordinationBase;
	}

	public String getIsMakeUp() {
		return isMakeUp;
	}

	public void setIsMakeUp(String isMakeUp) {
		this.isMakeUp = isMakeUp;
	}

	public String getAssistantQualificationCertificateCode() {
		return assistantQualificationCertificateCode;
	}

	public void setAssistantQualificationCertificateCode(String assistantQualificationCertificateCode) {
		this.assistantQualificationCertificateCode = assistantQualificationCertificateCode;
	}

	public String getCoordinationHospitalName() {
		return coordinationHospitalName;
	}

	public void setCoordinationHospitalName(String coordinationHospitalName) {
		this.coordinationHospitalName = coordinationHospitalName;
	}

	public String getCoordinationBaseAttributeId() {
		return coordinationBaseAttributeId;
	}

	public void setCoordinationBaseAttributeId(String coordinationNaseAttributeId) {
		this.coordinationBaseAttributeId = coordinationNaseAttributeId;
	}

	public String getCoordinationBaseAttributeName() {
		return coordinationBaseAttributeName;
	}

	public void setCoordinationBaseAttributeName(String coordinationNaseAttributeName) {
		this.coordinationBaseAttributeName = coordinationNaseAttributeName;
	}

	public String getHomeProvId() {
		return homeProvId;
	}

	public void setHomeProvId(String homeProvId) {
		this.homeProvId = homeProvId;
	}

	public String getHomeProvName() {
		return homeProvName;
	}

	public void setHomeProvName(String homeProvName) {
		this.homeProvName = homeProvName;
	}

	public String getHomeCityId() {
		return homeCityId;
	}

	public void setHomeCityId(String homeCityId) {
		this.homeCityId = homeCityId;
	}

	public String getHomeCityName() {
		return homeCityName;
	}

	public void setHomeCityName(String homeCityName) {
		this.homeCityName = homeCityName;
	}

	public String getHomeAreaId() {
		return homeAreaId;
	}

	public void setHomeAreaId(String homeAreaId) {
		this.homeAreaId = homeAreaId;
	}

	public String getHomeAreaName() {
		return homeAreaName;
	}

	public void setHomeAreaName(String homeAreaName) {
		this.homeAreaName = homeAreaName;
	}

	public String getHandBookInfo() {
		return handBookInfo;
	}

	public void setHandBookInfo(String handBookInfo) {
		this.handBookInfo = handBookInfo;
	}

	public String getQqCode() {
		return qqCode;
	}

	public void setQqCode(String qqCode) {
		this.qqCode = qqCode;
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

	public String getPhdSchool() {
		return phdSchool;
	}

	public void setPhdSchool(String phdSchool) {
		this.phdSchool = phdSchool;
	}

	public String getPhdDate() {
		return phdDate;
	}

	public void setPhdDate(String phdDate) {
		this.phdDate = phdDate;
	}

	public String getPhdMajor() {
		return phdMajor;
	}

	public void setPhdMajor(String phdMajor) {
		this.phdMajor = phdMajor;
	}

	public String getIdNoUri() {
		return idNoUri;
	}

	public void setIdNoUri(String idNoUri) {
		this.idNoUri = idNoUri;
	}

	public String getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(String isMaster) {
		this.isMaster = isMaster;
	}

	public String getMasterStatue() {
		return masterStatue;
	}

	public void setMasterStatue(String masterStatue) {
		this.masterStatue = masterStatue;
	}

	public String getMasterStartTime() {
		return masterStartTime;
	}

	public void setMasterStartTime(String masterStartTime) {
		this.masterStartTime = masterStartTime;
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

	public String getIsDoctor() {
		return isDoctor;
	}

	public void setIsDoctor(String isDoctor) {
		this.isDoctor = isDoctor;
	}

	public String getDoctorStatue() {
		return doctorStatue;
	}

	public void setDoctorStatue(String doctorStatue) {
		this.doctorStatue = doctorStatue;
	}

	public String getDoctorStartTime() {
		return doctorStartTime;
	}

	public void setDoctorStartTime(String doctorStartTime) {
		this.doctorStartTime = doctorStartTime;
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

	public String getIsGeneralOrderOrientationTrainee() {
		return isGeneralOrderOrientationTrainee;
	}

	public void setIsGeneralOrderOrientationTrainee(String isGeneralOrderOrientationTrainee) {
		this.isGeneralOrderOrientationTrainee = isGeneralOrderOrientationTrainee;
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

	public String getQualifiedUri() {
		return qualifiedUri;
	}

	public void setQualifiedUri(String qualifiedUri) {
		this.qualifiedUri = qualifiedUri;
	}

	public String getRegUri() {
		return regUri;
	}

	public void setRegUri(String regUri) {
		this.regUri = regUri;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}

	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getBeforeCase() {
		return beforeCase;
	}

	public void setBeforeCase(String beforeCase) {
		this.beforeCase = beforeCase;
	}

	public String getSocietyWork() {
		return societyWork;
	}

	public void setSocietyWork(String societyWork) {
		this.societyWork = societyWork;
	}

	public String getYearGraduateFlag() {
		return yearGraduateFlag;
	}

	public void setYearGraduateFlag(String yearGraduateFlag) {
		this.yearGraduateFlag = yearGraduateFlag;
	}

	public String getBirthProvId() {
		return birthProvId;
	}

	public void setBirthProvId(String birthProvId) {
		this.birthProvId = birthProvId;
	}

	public String getBirthProvName() {
		return birthProvName;
	}

	public void setBirthProvName(String birthProvName) {
		this.birthProvName = birthProvName;
	}

	public String getBirthCityId() {
		return birthCityId;
	}

	public void setBirthCityId(String birthCityId) {
		this.birthCityId = birthCityId;
	}

	public String getBirthCityName() {
		return birthCityName;
	}

	public void setBirthCityName(String birthCityName) {
		this.birthCityName = birthCityName;
	}

	public String getBirthAreaId() {
		return birthAreaId;
	}

	public void setBirthAreaId(String birthAreaId) {
		this.birthAreaId = birthAreaId;
	}

	public String getBirthAreaName() {
		return birthAreaName;
	}

	public void setBirthAreaName(String birthAreaName) {
		this.birthAreaName = birthAreaName;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getHomePhome() {
		return homePhome;
	}

	public void setHomePhome(String homePhome) {
		this.homePhome = homePhome;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getOtherWay() {
		return otherWay;
	}

	public void setOtherWay(String otherWay) {
		this.otherWay = otherWay;
	}

	public List<WorkResumeForm> getWorkResumeList() {
		return workResumeList;
	}

	public void setWorkResumeList(List<WorkResumeForm> workResumeList) {
		this.workResumeList = workResumeList;
	}

	public String getTrainingStartDate() {
		return trainingStartDate;
	}

	public void setTrainingStartDate(String trainingStartDate) {
		this.trainingStartDate = trainingStartDate;
	}

	public String getTrainingEndDate() {
		return trainingEndDate;
	}

	public void setTrainingEndDate(String trainingEndDate) {
		this.trainingEndDate = trainingEndDate;
	}
}
