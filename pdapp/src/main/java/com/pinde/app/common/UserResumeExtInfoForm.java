package com.pinde.app.common;

import com.pinde.core.model.PubUserResume;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class UserResumeExtInfoForm extends PubUserResume implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5225098729470455347L;

	//**************** 基本信息   ********************
	/**
	 * 固定电话
	 */
	private String telephone;

	/**
	 *婚姻状态
	 */
	private String maritalStatus;

	/**
	 *工作单位统一信用代码
	 */
	private String workUniteCreditCode;

	/**
	 *户口所在地省行政区划
	 */
	private String locationOfProvince;

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

	//**************** 本科   ****************************************
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
	 * 本科毕业专业id
	 */
	private String specializedId;

	/**
	 * 本科毕业时间
	 */
	private String graduationTime;

	/**
	 * 是否获得毕业证书
	 */
	private String isCollegeHaveDiploma;

	/**
	 * 是否获得学位证书
	 */
	private String isCollegeDegreeCertificate;

	/**
	 * 学历证书编号
	 */
	private String collegeDiplomaNo;

	/**
	 * 学历证书取得时间
	 */
	private String collegeDiplomaTime;

	/**
	 * 学位证书编号
	 */
	private String collegeDegreeNo;

	/**
	 * 学位证书取得时间
	 */
	private String collegeDegreeTime;

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
	 * 是否为硕士研究生
	 */
	private String isMaster;
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
	 * 硕士毕业院校时间
	 */
	private String 	masterGraTime;
	/**
	 * 硕士毕业专业id
	 */
	private String masterMajorId;
	/**
	 * 硕士毕业专业
	 */
	private String masterMajor;

	/**
	 * 是否获得毕业证书
	 */
	private String isMasterHaveDiploma;

	/**
	 * 是否获得学位证书
	 */
	private String isMasterDegreeCertificate;

	/**
	 * 学历证书编号
	 */
	private String masterDiplomaNo;

	/**
	 * 学历证书取得时间
	 */
	private String masterDiplomaTime;

	/**
	 * 学位证书编号
	 */
	private String masterDegreeNo;

	/**
	 * 学位证书取得时间
	 */
	private String masterDegreeTime;

	/**
	 * 是否为博士研究生
	 */
	private String isDoctor;
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
	 * 博士毕业院校时间
	 */
	private String 	doctorGraTime;
	/**
	 * 博士毕业专业
	 */
	private String doctorMajorId;
	/**
	 * 博士毕业专业
	 */
	private String doctorMajor;

	/**
	 * 是否获得毕业证书
	 */
	private String isDoctorHaveDiploma;

	/**
	 * 是否获得学位证书
	 */
	private String isDoctorDegreeCertificate;

	/**
	 * 学历证书编号
	 */
	private String doctorDiplomaNo;

	/**
	 * 学历证书取得时间
	 */
	private String doctorDiplomaTime;

	/**
	 * 学位证书编号
	 */
	private String doctorDegreeNo;

	/**
	 * 学位证书取得时间
	 */
	private String doctorDegreeTime;

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
	 * 特殊岗位证明材料
	 */
	private String specialCertificationUri;

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
	//是否全科订单定向学员
	private String isGeneralOrderOrientationTrainee;

	//培训登记手册完成情况
	private String registeManua;

	//*****************   医师资格信息     *********************

	/**
	 *是否通过医师资格考试
	 */
	private String isPassQualifyingExamination;

	/**
	 *通过医师资格考试时间
	 */
	private String passQualifyingExaminationTime;

	/**
	 *是否获得医师资格证书
	 */
	private String isHaveQualificationCertificate;

	/**
	 *取得医师资格证书时间
	 */
	private String haveQualificationCertificateTime;

	/**
	 * 医师资格级别
	 */
	private String physicianQualificationLevel;

	/**
	 * 医师资格类别
	 */
	private String physicianQualificationClass;

	/**
	 * 医师资格证书编码
	 */
	private String doctorQualificationCertificateCode;

	/**
	 * 医师资格证书材料
	 */
	private String doctorQualificationCertificateUrl;

	/**
	 *是否获得医师执业证书
	 */
	private String isHavePracticingCategory;

	/**
	 *取得医师执业证书时间
	 */
	private String havePracticingCategoryTime;

	/**
	 * 执业类别
	 */
	private String practicingCategoryLevelId;
	private String practicingCategoryLevelName;

	/**
	 * 执业范围
	 */
	private String practicingCategoryScopeId;
	private String practicingCategoryScopeName;
	private String practicingCategoryScope;//职业范围(医师基本信息所需字段)

	/**
	 * 医师执业证书编码
	 */
	private String doctorPracticingCategoryCode;

	/**
	 * 医师资格证书材料
	 */
	private String doctorPracticingCategoryUrl;

	/**
	 * 成绩单类型
	 */
	private String qualificationMaterialId2;
	private String qualificationMaterialName2;
	/**
	 * 成绩单附件
	 */
	private String qualificationMaterialId2Url;

	//*****************   西部支援情况     *********************

	/**
	 *是否为西部支援行动住院医师
	 */
	private String westernSupportResidents;

	/**
	 *西部支援行动住院医师送出省份
	 */
	private String westernSupportResidentsSendProvince;

	/**
	 *西部支援行动住院医师送出单位统一社会信用代码
	 */
	private String westernSupportResidentsSendWorkUnitCode;

	/**
	 *西部支援行动住院医师送出单位
	 */
	private String westernSupportResidentsSendWorkUnit;

	/**
	 *西部支援行动住院医师接收省份
	 */
	private String westernSupportResidentsReceiveProvince;

	/**

	 *西部支援行动住院医师接收省份培训基地(医院)统一社会信用代码
	 */
	private String westernSupportResidentsReceiveHospitalCode;

	/**
	 *西部支援行动住院医师接收省份培训基地(医院)
	 */
	private String westernSupportResidentsReceiveHospital;

	/** 是否在读      */
	private String  isReading 	        ;
	/** 在读学历      */
	private String  readingCollege       ;
	/** 预计毕业时间  */
	private String  readingDate          ;
	/** 在读专业      */
	private String  readingProfession    ;
	/** 在读专业id      */
	private String  readingProfessionId    ;
	/** 在读院校      */
	private String  readingSchoolName    ;
	/** 在读院校      */
	private String  readingSchoolId    ;

	/**  是否大专：       */
	private String      isJuniorCollege            ;
	/**  是否全日制:      */
	private String      juniorCollegeFullTime      ;
	/**  大专毕业时间:	    */
	private String       juniorCollegeGradate       ;
	/**  大专毕业院校编码:   */
	private String      juniorCollegeSchoolId      ;
	/**  大专毕业院校   :   */
	private String      juniorCollegeSchoolName    ;
	/**  大专毕业专业:        */
	private String      juniorCollegeProfessionId    ;
	/**  大专毕业专业:        */
	private String      juniorCollegeProfession    ;
	/**  大专是否获得毕业证书:*/
	private String      isJuniorCollegeCertificate ;
	/**  大专学历证书照片: 	*/
	private String       juniorCollegeUrl           ;
	/**  大专证书编码： 		*/
	private String       juniorCollegeCode          ;
	/**  大专证书取得时间:	*/
	private String       juniorCollegeDate          ;

	/**  是否本科：       */
	private String     isUndergraduate             ;
	/**  本科是否全日制：     */
	private String     undergraduateFullTime       ;
	/**  本科毕业时间         */
	private String     undergraduateGradate        ;
	/**  毕业院校         */
	private String     undergraduateSchoolName     ;
	/**  毕业专业         */
	private String     undergraduateProfession     ;
	/**  是否获得毕业证书 */
	private String     isUndergraduateCertificate  ;
	/**  学历证书照片     */
	private String     undergraduateUrl            ;
	/**  学位证书照片     */
	private String     undergraduateDegreeUrl            ;
	/**  证书编码：       */
	private String     undergraduateCode           ;
	/**  证书取得时间     */
	private String     undergraduateDate           ;

	/** 硕士 是否全日制： */
	private String isMasterFullTime     ;
	/** 学位类型     */
	private String isMasterScienceType  ;
	/** 学位证书照片 */
	private String masterCertificateUrl ;
	/** 学历证书照片 */
	private String masterEducationUrl   ;

	/** 博士 是否全日制： */
	private String isDoctorFullTime     ;
	/** 学位证书照片 */
	private String doctorCertificateUrl ;
	/** 学历证书照片 */
	private String doctorEducationUrl   ;
	/** 学位类型     */
	private String isDoctorScienceType  ;

	/** 其他在读院校   */
	private String      readingOtherSchoolsName;
	/** 其他大专院校   */
	private String      juniorOtherSchoolsName;
	/** 其他本科院校   */
	private String      undergraOtherSchoolsName;
	/** 其他硕士院校   */
	private String      masterOtherSchoolsName;
	/** 其他博士院校   */
	private String      doctorOtherSchoolsName;

	/** 其他在读专业  */
	private String   readingOtherProfession    ;
	/** 其他大专专业  */
	private String   juniorOtherProfession     ;
	/** 其他本科专业  */
	private String  undergraOtherProfession    ;
	/** 其他硕士专业  */
	private String  masterOtherProfession      ;
	/** 其他博士专业  */
	private String  doctorOtherProfession      ;
	/** 工作单位  */
	private String  workUnit      ;

	private String  armyHospital      ;
    /** 是否军队人员  */
	private String  armyType      ;

    public String getArmyType() {
        return armyType;
    }

    public void setArmyType(String armyType) {
        this.armyType = armyType;
    }

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getArmyHospital() {
		return armyHospital;
	}

	public void setArmyHospital(String armyHospital) {
		this.armyHospital = armyHospital;
	}

	public String getReadingSchoolId() {
		return readingSchoolId;
	}

	public void setReadingSchoolId(String readingSchoolId) {
		this.readingSchoolId = readingSchoolId;
	}

	public String getSpecializedId() {
		return specializedId;
	}

	public void setSpecializedId(String specializedId) {
		this.specializedId = specializedId;
	}

	public String getMasterMajorId() {
		return masterMajorId;
	}

	public void setMasterMajorId(String masterMajorId) {
		this.masterMajorId = masterMajorId;
	}

	public String getDoctorMajorId() {
		return doctorMajorId;
	}

	public void setDoctorMajorId(String doctorMajorId) {
		this.doctorMajorId = doctorMajorId;
	}

	public String getReadingProfessionId() {
		return readingProfessionId;
	}

	public void setReadingProfessionId(String readingProfessionId) {
		this.readingProfessionId = readingProfessionId;
	}

	public String getJuniorCollegeSchoolId() {
		return juniorCollegeSchoolId;
	}

	public void setJuniorCollegeSchoolId(String juniorCollegeSchoolId) {
		this.juniorCollegeSchoolId = juniorCollegeSchoolId;
	}

	public String getJuniorCollegeProfessionId() {
		return juniorCollegeProfessionId;
	}

	public void setJuniorCollegeProfessionId(String juniorCollegeProfessionId) {
		this.juniorCollegeProfessionId = juniorCollegeProfessionId;
	}

	public String getUndergraduateDegreeUrl() {
		return undergraduateDegreeUrl;
	}

	public void setUndergraduateDegreeUrl(String undergraduateDegreeUrl) {
		this.undergraduateDegreeUrl = undergraduateDegreeUrl;
	}

	public String getReadingOtherProfession() {
		return readingOtherProfession;
	}

	public void setReadingOtherProfession(String readingOtherProfession) {
		this.readingOtherProfession = readingOtherProfession;
	}

	public String getJuniorOtherProfession() {
		return juniorOtherProfession;
	}

	public void setJuniorOtherProfession(String juniorOtherProfession) {
		this.juniorOtherProfession = juniorOtherProfession;
	}

	public String getUndergraOtherProfession() {
		return undergraOtherProfession;
	}

	public void setUndergraOtherProfession(String undergraOtherProfession) {
		this.undergraOtherProfession = undergraOtherProfession;
	}

	public String getMasterOtherProfession() {
		return masterOtherProfession;
	}

	public void setMasterOtherProfession(String masterOtherProfession) {
		this.masterOtherProfession = masterOtherProfession;
	}

	public String getDoctorOtherProfession() {
		return doctorOtherProfession;
	}

	public void setDoctorOtherProfession(String doctorOtherProfession) {
		this.doctorOtherProfession = doctorOtherProfession;
	}

	public String getReadingCollege() {
		return readingCollege;
	}

	public void setReadingCollege(String readingCollege) {
		this.readingCollege = readingCollege;
	}

	public String getReadingDate() {
		return readingDate;
	}

	public void setReadingDate(String readingDate) {
		this.readingDate = readingDate;
	}

	public String getReadingProfession() {
		return readingProfession;
	}

	public void setReadingProfession(String readingProfession) {
		this.readingProfession = readingProfession;
	}

	public String getReadingSchoolName() {
		return readingSchoolName;
	}

	public void setReadingSchoolName(String readingSchoolName) {
		this.readingSchoolName = readingSchoolName;
	}

	public String getReadingOtherSchoolsName() {
		return readingOtherSchoolsName;
	}

	public void setReadingOtherSchoolsName(String readingOtherSchoolsName) {
		this.readingOtherSchoolsName = readingOtherSchoolsName;
	}

	public String getJuniorOtherSchoolsName() {
		return juniorOtherSchoolsName;
	}

	public void setJuniorOtherSchoolsName(String juniorOtherSchoolsName) {
		this.juniorOtherSchoolsName = juniorOtherSchoolsName;
	}

	public String getUndergraOtherSchoolsName() {
		return undergraOtherSchoolsName;
	}

	public void setUndergraOtherSchoolsName(String undergraOtherSchoolsName) {
		this.undergraOtherSchoolsName = undergraOtherSchoolsName;
	}

	public String getMasterOtherSchoolsName() {
		return masterOtherSchoolsName;
	}

	public void setMasterOtherSchoolsName(String masterOtherSchoolsName) {
		this.masterOtherSchoolsName = masterOtherSchoolsName;
	}

	public String getDoctorOtherSchoolsName() {
		return doctorOtherSchoolsName;
	}

	public void setDoctorOtherSchoolsName(String doctorOtherSchoolsName) {
		this.doctorOtherSchoolsName = doctorOtherSchoolsName;
	}

	public String getIsReading() {
		return isReading;
	}

	public void setIsReading(String isReading) {
		this.isReading = isReading;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getIsMasterFullTime() {
		return isMasterFullTime;
	}

	public void setIsMasterFullTime(String isMasterFullTime) {
		this.isMasterFullTime = isMasterFullTime;
	}

	public String getIsMasterScienceType() {
		return isMasterScienceType;
	}

	public void setIsMasterScienceType(String isMasterScienceType) {
		this.isMasterScienceType = isMasterScienceType;
	}

	public String getMasterCertificateUrl() {
		return masterCertificateUrl;
	}

	public void setMasterCertificateUrl(String masterCertificateUrl) {
		this.masterCertificateUrl = masterCertificateUrl;
	}

	public String getMasterEducationUrl() {
		return masterEducationUrl;
	}

	public void setMasterEducationUrl(String masterEducationUrl) {
		this.masterEducationUrl = masterEducationUrl;
	}

	public String getIsDoctorFullTime() {
		return isDoctorFullTime;
	}

	public void setIsDoctorFullTime(String isDoctorFullTime) {
		this.isDoctorFullTime = isDoctorFullTime;
	}

	public String getDoctorCertificateUrl() {
		return doctorCertificateUrl;
	}

	public void setDoctorCertificateUrl(String doctorCertificateUrl) {
		this.doctorCertificateUrl = doctorCertificateUrl;
	}

	public String getDoctorEducationUrl() {
		return doctorEducationUrl;
	}

	public void setDoctorEducationUrl(String doctorEducationUrl) {
		this.doctorEducationUrl = doctorEducationUrl;
	}

	public String getIsDoctorScienceType() {
		return isDoctorScienceType;
	}

	public void setIsDoctorScienceType(String isDoctorScienceType) {
		this.isDoctorScienceType = isDoctorScienceType;
	}

	public String getIsJuniorCollege() {
		return isJuniorCollege;
	}

	public void setIsJuniorCollege(String isJuniorCollege) {
		this.isJuniorCollege = isJuniorCollege;
	}

	public String getJuniorCollegeFullTime() {
		return juniorCollegeFullTime;
	}

	public void setJuniorCollegeFullTime(String juniorCollegeFullTime) {
		this.juniorCollegeFullTime = juniorCollegeFullTime;
	}

	public String getJuniorCollegeGradate() {
		return juniorCollegeGradate;
	}

	public void setJuniorCollegeGradate(String juniorCollegeGradate) {
		this.juniorCollegeGradate = juniorCollegeGradate;
	}

	public String getJuniorCollegeSchoolName() {
		return juniorCollegeSchoolName;
	}

	public void setJuniorCollegeSchoolName(String juniorCollegeSchoolName) {
		this.juniorCollegeSchoolName = juniorCollegeSchoolName;
	}

	public String getJuniorCollegeProfession() {
		return juniorCollegeProfession;
	}

	public void setJuniorCollegeProfession(String juniorCollegeProfession) {
		this.juniorCollegeProfession = juniorCollegeProfession;
	}

	public String getIsJuniorCollegeCertificate() {
		return isJuniorCollegeCertificate;
	}

	public void setIsJuniorCollegeCertificate(String isJuniorCollegeCertificate) {
		this.isJuniorCollegeCertificate = isJuniorCollegeCertificate;
	}

	public String getJuniorCollegeUrl() {
		return juniorCollegeUrl;
	}

	public void setJuniorCollegeUrl(String juniorCollegeUrl) {
		this.juniorCollegeUrl = juniorCollegeUrl;
	}

	public String getJuniorCollegeCode() {
		return juniorCollegeCode;
	}

	public void setJuniorCollegeCode(String juniorCollegeCode) {
		this.juniorCollegeCode = juniorCollegeCode;
	}

	public String getJuniorCollegeDate() {
		return juniorCollegeDate;
	}

	public void setJuniorCollegeDate(String juniorCollegeDate) {
		this.juniorCollegeDate = juniorCollegeDate;
	}

	public String getIsUndergraduate() {
		return isUndergraduate;
	}

	public void setIsUndergraduate(String isUndergraduate) {
		this.isUndergraduate = isUndergraduate;
	}

	public String getUndergraduateFullTime() {
		return undergraduateFullTime;
	}

	public void setUndergraduateFullTime(String undergraduateFullTime) {
		this.undergraduateFullTime = undergraduateFullTime;
	}

	public String getUndergraduateGradate() {
		return undergraduateGradate;
	}

	public void setUndergraduateGradate(String undergraduateGradate) {
		this.undergraduateGradate = undergraduateGradate;
	}

	public String getUndergraduateSchoolName() {
		return undergraduateSchoolName;
	}

	public void setUndergraduateSchoolName(String undergraduateSchoolName) {
		this.undergraduateSchoolName = undergraduateSchoolName;
	}

	public String getUndergraduateProfession() {
		return undergraduateProfession;
	}

	public void setUndergraduateProfession(String undergraduateProfession) {
		this.undergraduateProfession = undergraduateProfession;
	}

	public String getIsUndergraduateCertificate() {
		return isUndergraduateCertificate;
	}

	public void setIsUndergraduateCertificate(String isUndergraduateCertificate) {
		this.isUndergraduateCertificate = isUndergraduateCertificate;
	}

	public String getUndergraduateUrl() {
		return undergraduateUrl;
	}

	public void setUndergraduateUrl(String undergraduateUrl) {
		this.undergraduateUrl = undergraduateUrl;
	}

	public String getUndergraduateCode() {
		return undergraduateCode;
	}

	public void setUndergraduateCode(String undergraduateCode) {
		this.undergraduateCode = undergraduateCode;
	}

	public String getUndergraduateDate() {
		return undergraduateDate;
	}

	public void setUndergraduateDate(String undergraduateDate) {
		this.undergraduateDate = undergraduateDate;
	}

	public String getWesternSupportResidentsReceiveHospitalCode() {
		return westernSupportResidentsReceiveHospitalCode;
	}

	public void setWesternSupportResidentsReceiveHospitalCode(String westernSupportResidentsReceiveHospitalCode) {
		this.westernSupportResidentsReceiveHospitalCode = westernSupportResidentsReceiveHospitalCode;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getWorkUniteCreditCode() {
		return workUniteCreditCode;
	}

	public void setWorkUniteCreditCode(String workUniteCreditCode) {
		this.workUniteCreditCode = workUniteCreditCode;
	}

	public String getLocationOfProvince() {
		return locationOfProvince;
	}

	public void setLocationOfProvince(String locationOfProvince) {
		this.locationOfProvince = locationOfProvince;
	}

	public String getIsCollegeHaveDiploma() {
		return isCollegeHaveDiploma;
	}

	public void setIsCollegeHaveDiploma(String isCollegeHaveDiploma) {
		this.isCollegeHaveDiploma = isCollegeHaveDiploma;
	}

	public String getIsCollegeDegreeCertificate() {
		return isCollegeDegreeCertificate;
	}

	public void setIsCollegeDegreeCertificate(String isCollegeDegreeCertificate) {
		this.isCollegeDegreeCertificate = isCollegeDegreeCertificate;
	}

	public String getCollegeDiplomaNo() {
		return collegeDiplomaNo;
	}

	public void setCollegeDiplomaNo(String collegeDiplomaNo) {
		this.collegeDiplomaNo = collegeDiplomaNo;
	}

	public String getCollegeDiplomaTime() {
		return collegeDiplomaTime;
	}

	public void setCollegeDiplomaTime(String collegeDiplomaTime) {
		this.collegeDiplomaTime = collegeDiplomaTime;
	}

	public String getCollegeDegreeNo() {
		return collegeDegreeNo;
	}

	public void setCollegeDegreeNo(String collegeDegreeNo) {
		this.collegeDegreeNo = collegeDegreeNo;
	}

	public String getCollegeDegreeTime() {
		return collegeDegreeTime;
	}

	public void setCollegeDegreeTime(String collegeDegreeTime) {
		this.collegeDegreeTime = collegeDegreeTime;
	}

	public String getIsMasterHaveDiploma() {
		return isMasterHaveDiploma;
	}

	public void setIsMasterHaveDiploma(String isMasterHaveDiploma) {
		this.isMasterHaveDiploma = isMasterHaveDiploma;
	}

	public String getIsMasterDegreeCertificate() {
		return isMasterDegreeCertificate;
	}

	public void setIsMasterDegreeCertificate(String isMasterDegreeCertificate) {
		this.isMasterDegreeCertificate = isMasterDegreeCertificate;
	}

	public String getMasterDiplomaNo() {
		return masterDiplomaNo;
	}

	public void setMasterDiplomaNo(String masterDiplomaNo) {
		this.masterDiplomaNo = masterDiplomaNo;
	}

	public String getMasterDiplomaTime() {
		return masterDiplomaTime;
	}

	public void setMasterDiplomaTime(String masterDiplomaTime) {
		this.masterDiplomaTime = masterDiplomaTime;
	}

	public String getMasterDegreeNo() {
		return masterDegreeNo;
	}

	public void setMasterDegreeNo(String masterDegreeNo) {
		this.masterDegreeNo = masterDegreeNo;
	}

	public String getMasterDegreeTime() {
		return masterDegreeTime;
	}

	public void setMasterDegreeTime(String masterDegreeTime) {
		this.masterDegreeTime = masterDegreeTime;
	}

	public String getIsDoctorHaveDiploma() {
		return isDoctorHaveDiploma;
	}

	public void setIsDoctorHaveDiploma(String isDoctorHaveDiploma) {
		this.isDoctorHaveDiploma = isDoctorHaveDiploma;
	}

	public String getIsDoctorDegreeCertificate() {
		return isDoctorDegreeCertificate;
	}

	public void setIsDoctorDegreeCertificate(String isDoctorDegreeCertificate) {
		this.isDoctorDegreeCertificate = isDoctorDegreeCertificate;
	}

	public String getDoctorDiplomaNo() {
		return doctorDiplomaNo;
	}

	public void setDoctorDiplomaNo(String doctorDiplomaNo) {
		this.doctorDiplomaNo = doctorDiplomaNo;
	}

	public String getDoctorDiplomaTime() {
		return doctorDiplomaTime;
	}

	public void setDoctorDiplomaTime(String doctorDiplomaTime) {
		this.doctorDiplomaTime = doctorDiplomaTime;
	}

	public String getDoctorDegreeNo() {
		return doctorDegreeNo;
	}

	public void setDoctorDegreeNo(String doctorDegreeNo) {
		this.doctorDegreeNo = doctorDegreeNo;
	}

	public String getDoctorDegreeTime() {
		return doctorDegreeTime;
	}

	public void setDoctorDegreeTime(String doctorDegreeTime) {
		this.doctorDegreeTime = doctorDegreeTime;
	}

	public String getIsPassQualifyingExamination() {
		return isPassQualifyingExamination;
	}

	public void setIsPassQualifyingExamination(String isPassQualifyingExamination) {
		this.isPassQualifyingExamination = isPassQualifyingExamination;
	}

	public String getPassQualifyingExaminationTime() {
		return passQualifyingExaminationTime;
	}

	public void setPassQualifyingExaminationTime(String passQualifyingExaminationTime) {
		this.passQualifyingExaminationTime = passQualifyingExaminationTime;
	}

	public String getIsHaveQualificationCertificate() {
		return isHaveQualificationCertificate;
	}

	public void setIsHaveQualificationCertificate(String isHaveQualificationCertificate) {
		this.isHaveQualificationCertificate = isHaveQualificationCertificate;
	}

	public String getHaveQualificationCertificateTime() {
		return haveQualificationCertificateTime;
	}

	public void setHaveQualificationCertificateTime(String haveQualificationCertificateTime) {
		this.haveQualificationCertificateTime = haveQualificationCertificateTime;
	}

	public String getPhysicianQualificationLevel() {
		return physicianQualificationLevel;
	}

	public void setPhysicianQualificationLevel(String physicianQualificationLevel) {
		this.physicianQualificationLevel = physicianQualificationLevel;
	}

	public String getPhysicianQualificationClass() {
		return physicianQualificationClass;
	}

	public void setPhysicianQualificationClass(String physicianQualificationClass) {
		this.physicianQualificationClass = physicianQualificationClass;
	}

	public String getDoctorQualificationCertificateCode() {
		return doctorQualificationCertificateCode;
	}

	public void setDoctorQualificationCertificateCode(String doctorQualificationCertificateCode) {
		this.doctorQualificationCertificateCode = doctorQualificationCertificateCode;
	}

	public String getDoctorQualificationCertificateUrl() {
		return doctorQualificationCertificateUrl;
	}

	public void setDoctorQualificationCertificateUrl(String doctorQualificationCertificateUrl) {
		this.doctorQualificationCertificateUrl = doctorQualificationCertificateUrl;
	}

	public String getIsHavePracticingCategory() {
		return isHavePracticingCategory;
	}

	public void setIsHavePracticingCategory(String isHavePracticingCategory) {
		this.isHavePracticingCategory = isHavePracticingCategory;
	}

	public String getHavePracticingCategoryTime() {
		return havePracticingCategoryTime;
	}

	public void setHavePracticingCategoryTime(String havePracticingCategoryTime) {
		this.havePracticingCategoryTime = havePracticingCategoryTime;
	}

	public String getDoctorPracticingCategoryCode() {
		return doctorPracticingCategoryCode;
	}

	public void setDoctorPracticingCategoryCode(String doctorPracticingCategoryCode) {
		this.doctorPracticingCategoryCode = doctorPracticingCategoryCode;
	}

	public String getDoctorPracticingCategoryUrl() {
		return doctorPracticingCategoryUrl;
	}

	public void setDoctorPracticingCategoryUrl(String doctorPracticingCategoryUrl) {
		this.doctorPracticingCategoryUrl = doctorPracticingCategoryUrl;
	}

	public String getWesternSupportResidents() {
		return westernSupportResidents;
	}

	public void setWesternSupportResidents(String westernSupportResidents) {
		this.westernSupportResidents = westernSupportResidents;
	}

	public String getWesternSupportResidentsSendProvince() {
		return westernSupportResidentsSendProvince;
	}

	public void setWesternSupportResidentsSendProvince(String westernSupportResidentsSendProvince) {
		this.westernSupportResidentsSendProvince = westernSupportResidentsSendProvince;
	}

	public String getWesternSupportResidentsSendWorkUnitCode() {
		return westernSupportResidentsSendWorkUnitCode;
	}

	public void setWesternSupportResidentsSendWorkUnitCode(String westernSupportResidentsSendWorkUnitCode) {
		this.westernSupportResidentsSendWorkUnitCode = westernSupportResidentsSendWorkUnitCode;
	}

	public String getWesternSupportResidentsSendWorkUnit() {
		return westernSupportResidentsSendWorkUnit;
	}

	public void setWesternSupportResidentsSendWorkUnit(String westernSupportResidentsSendWorkUnit) {
		this.westernSupportResidentsSendWorkUnit = westernSupportResidentsSendWorkUnit;
	}

	public String getWesternSupportResidentsReceiveProvince() {
		return westernSupportResidentsReceiveProvince;
	}

	public void setWesternSupportResidentsReceiveProvince(String westernSupportResidentsReceiveProvince) {
		this.westernSupportResidentsReceiveProvince = westernSupportResidentsReceiveProvince;
	}

	public String getWesternSupportResidentsReceiveHospital() {
		return westernSupportResidentsReceiveHospital;
	}

	public void setWesternSupportResidentsReceiveHospital(String westernSupportResidentsReceiveHospital) {
		this.westernSupportResidentsReceiveHospital = westernSupportResidentsReceiveHospital;
	}

	public String getRegisteManua() {
		return registeManua;
	}

	public void setRegisteManua(String registeManua) {
		this.registeManua = registeManua;
	}

	public String getIsGeneralOrderOrientationTrainee() {
		return isGeneralOrderOrientationTrainee;
	}

	public void setIsGeneralOrderOrientationTrainee(String isGeneralOrderOrientationTrainee) {
		this.isGeneralOrderOrientationTrainee = isGeneralOrderOrientationTrainee;
	}

	//***************************************************************
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

	public String getSpecialCertificationUri() {
		return specialCertificationUri;
	}

	public void setSpecialCertificationUri(String specialCertificationUri) {
		this.specialCertificationUri = specialCertificationUri;
	}

	public String getPracticingCategoryLevelId() {
		return practicingCategoryLevelId;
	}

	public void setPracticingCategoryLevelId(String practicingCategoryLevelId) {
		this.practicingCategoryLevelId = practicingCategoryLevelId;
	}

	public String getPracticingCategoryLevelName() {
		return practicingCategoryLevelName;
	}

	public void setPracticingCategoryLevelName(String practicingCategoryLevelName) {
		this.practicingCategoryLevelName = practicingCategoryLevelName;
	}

	public String getPracticingCategoryScopeId() {
		return practicingCategoryScopeId;
	}

	public void setPracticingCategoryScopeId(String practicingCategoryScopeId) {
		this.practicingCategoryScopeId = practicingCategoryScopeId;
	}

	public String getPracticingCategoryScopeName() {
		return practicingCategoryScopeName;
	}

	public void setPracticingCategoryScopeName(String practicingCategoryScopeName) {
		this.practicingCategoryScopeName = practicingCategoryScopeName;
	}

	public String getPracticingCategoryScope() {
		return practicingCategoryScope;
	}

	public void setPracticingCategoryScope(String practicingCategoryScope) {
		this.practicingCategoryScope = practicingCategoryScope;
	}

	public String getQualificationMaterialId2() {
		return qualificationMaterialId2;
	}

	public void setQualificationMaterialId2(String qualificationMaterialId2) {
		this.qualificationMaterialId2 = qualificationMaterialId2;
	}

	public String getQualificationMaterialName2() {
		return qualificationMaterialName2;
	}

	public void setQualificationMaterialName2(String qualificationMaterialName2) {
		this.qualificationMaterialName2 = qualificationMaterialName2;
	}

	public String getQualificationMaterialId2Url() {
		return qualificationMaterialId2Url;
	}

	public void setQualificationMaterialId2Url(String qualificationMaterialId2Url) {
		this.qualificationMaterialId2Url = qualificationMaterialId2Url;
	}
}
