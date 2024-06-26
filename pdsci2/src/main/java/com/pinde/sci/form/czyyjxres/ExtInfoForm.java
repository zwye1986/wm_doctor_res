package com.pinde.sci.form.czyyjxres;

import java.io.Serializable;
import java.util.List;

public class ExtInfoForm implements Serializable{


	/**
	 * 个人专业
	 */
	private String userMajor;

	/**
	 * 姓名
	 */
	private String userName;

	/**
	 * 性别Id
	 */
	private String sexId;

	/**
	 * 性别Name
	 */
	private String sexName;

	/**
	 * 生日
	 */
	private String userBirthday;

	/**
	 * 身份证号
	 */
	private String idNo;

	/**
	 * 民族Id
	 */
	private String nationId;

	/**
	 * 民族Name
	 */
	private String nationName;

	/**
	 * 联系电话
	 */
	private String userPhone;
	/**
	 * 中国联系电话
	 */
	private String chinaPhone;
	/**
	 * 邮箱
	 */
	private String userEmail;

	/**
	 * 证件照地址
	 */
	private String userHeadImg;

	/**
	 * 工作经历
	 */
	private List<WorkResumeForm> workResumeList;

	/**
	 * 进修专业、进修时间
	 */
	private List<SpeForm> speFormList;

	/**
	 * 进修目的
	 */
	private String studyAim;

	/**
	 * 从事专业现有业务水平
	 */
	private String vocationalLevel;

	/**
	 * 身份证图片uri
	 */
	private String idNoUri;
	private String idNoUri2;
	private String idNoUri3;

	/**
	 * 毕业证号
	 */
	private String graduatedNo;

	/**
	 * 毕业证图片uri
	 */
	private String graduatedUri;

	/**
	 * 资格证号
	 */
	private String qualifiedNo;

	/**
	 * 资格证图片uri
	 */
	private String qualifiedUri;

	/**
	 * 执业证号
	 */
	private String certifiedNo;

	/**
	 * 执业证图片uri
	 */
	private String certifiedUri;
	/**
	 * 护照
	 */
	private String passportUri;
	private String passportNo;

	/**
	 * 其他资质证书
	 */
	private List<RegForm> regList;
	/**
	 * 身份证图片
	 */
//	private List<IdPicForm> idPicList;
	/**
	 * 进修生申请登记表图片uri
	 */
	private String registerFormUri;
	/**
	 * 学历Id
	 */
	private String degreeId;
	/**
	 * 学历Name
	 */
	private String degreeName;

	/**
	 * 结业评语
	 */
	private String graduationMark;

	/**
	 * 进修生申请单位介绍信uri
	 */
	private String introductionUri;

	/**
	 * 进修生申请乙肝两对半检查结果(1年内的)uri
	 */
	private String testResultUri;

	/**
	 * 进修生申请遵守纪律协议书uri
	 */
	private String agreementUri;

	/**
	 * 执业资格证号
	 */
	private String practiceNo;

	/**
	 * 是否我院教学基地或帮扶协议单位
	 */
	private String isOwnOrg;

	//进修系统优化需求
	private String titleTypeId;//职称类别

	/**
	 * 科研信息（课题）
	 */
	private List<ProjectForm> projectList;

	/**
	 * 科研信息（论文）
	 */
	private List<ThesisForm> thesisList;

	/**
	 * 政治面貌id
	 */
	private String politicsStatusId;
	/**
	 * 政治面貌name
	 */
	private String politicsStatusName;
	/**
	 * 籍贯省
	 */
	private String nativePlaceProvId;
	private String nativePlaceProvName;
	/**
	 * 籍贯市
	 */
	private String nativePlaceCityId;
	private String nativePlaceCityName;

	/**
	 * 国籍
	 */
	private String nationalityId;
	private String nationalityName;
	/**
	 * 邮编
	 */
	private String postCodes;
	/**
	 * 学历及社会经历
	 */
	private String socialExperience;
	/**
	 * 学费
	 */
	private String tuition;
	/**
	 * 住宿费
	 */
	private String hotelExpense;
	/**
	 * 押金
	 */
	private String according;
	/**
	 * 进修总费用
	 */
	private String totalFee;
	/**
	 * 工作服件数
	 */
	private String coverallNum;
	/**
	 * 工作服
	 */
	private String workClother;
	/**
	 * 学历信息
	 */
	private List<EducationForm> educationList;
	/**
	 * 工号
	 */
	private String userCode;

//	public List<IdPicForm> getIdPicList() {
//		return idPicList;
//	}
//
//	public void setIdPicList(List<IdPicForm> idPicList) {
//		this.idPicList = idPicList;
//	}

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

	public String getNationalityName() {
		return nationalityName;
	}

	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}

	public String getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(String nationalityId) {
		this.nationalityId = nationalityId;
	}

	public String getUserMajor() {
		return userMajor;
	}

	public void setUserMajor(String userMajor) {
		this.userMajor = userMajor;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getNationId() {
		return nationId;
	}

	public void setNationId(String nationId) {
		this.nationId = nationId;
	}

	public String getNationName() {
		return nationName;
	}

	public void setNationName(String nationName) {
		this.nationName = nationName;
	}

	public String getSexId() {
		return sexId;
	}

	public void setSexId(String sexId) {
		this.sexId = sexId;
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserHeadImg() {
		return userHeadImg;
	}

	public void setUserHeadImg(String userHeadImg) {
		this.userHeadImg = userHeadImg;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getChinaPhone() {
		return chinaPhone;
	}

	public void setChinaPhone(String chinaPhone) {
		this.chinaPhone = chinaPhone;
	}

	public List<WorkResumeForm> getWorkResumeList() {
		return workResumeList;
	}

	public void setWorkResumeList(List<WorkResumeForm> workResumeList) {
		this.workResumeList = workResumeList;
	}

	public List<SpeForm> getSpeFormList() {
		return speFormList;
	}

	public void setSpeFormList(List<SpeForm> speFormList) {
		this.speFormList = speFormList;
	}

	public String getStudyAim() {
		return studyAim;
	}

	public void setStudyAim(String studyAim) {
		this.studyAim = studyAim;
	}

	public String getVocationalLevel() {
		return vocationalLevel;
	}

	public void setVocationalLevel(String vocationalLevel) {
		this.vocationalLevel = vocationalLevel;
	}

	public String getIdNoUri() {
		return idNoUri;
	}

	public void setIdNoUri(String idNoUri) {
		this.idNoUri = idNoUri;
	}

	public String getIdNoUri2() {
		return idNoUri2;
	}

	public void setIdNoUri2(String idNoUri2) {
		this.idNoUri2 = idNoUri2;
	}

	public String getIdNoUri3() {
		return idNoUri3;
	}

	public void setIdNoUri3(String idNoUri3) {
		this.idNoUri3 = idNoUri3;
	}

	public String getGraduatedNo() {
		return graduatedNo;
	}

	public void setGraduatedNo(String graduatedNo) {
		this.graduatedNo = graduatedNo;
	}

	public String getGraduatedUri() {
		return graduatedUri;
	}

	public void setGraduatedUri(String graduatedUri) {
		this.graduatedUri = graduatedUri;
	}

	public String getQualifiedNo() {
		return qualifiedNo;
	}

	public void setQualifiedNo(String qualifiedNo) {
		this.qualifiedNo = qualifiedNo;
	}

	public String getQualifiedUri() {
		return qualifiedUri;
	}

	public void setQualifiedUri(String qualifiedUri) {
		this.qualifiedUri = qualifiedUri;
	}

	public String getCertifiedNo() {
		return certifiedNo;
	}

	public void setCertifiedNo(String certifiedNo) {
		this.certifiedNo = certifiedNo;
	}

	public String getCertifiedUri() {
		return certifiedUri;
	}

	public void setCertifiedUri(String certifiedUri) {
		this.certifiedUri = certifiedUri;
	}

	public List<RegForm> getRegList() {
		return regList;
	}

	public void setRegList(List<RegForm> regList) {
		this.regList = regList;
	}

	public String getRegisterFormUri() {
		return registerFormUri;
	}

	public void setRegisterFormUri(String registerFormUri) {
		this.registerFormUri = registerFormUri;
	}

	public String getGraduationMark() {
		return graduationMark;
	}

	public void setGraduationMark(String graduationMark) {
		this.graduationMark = graduationMark;
	}

	public String getTitleTypeId() {
		return titleTypeId;
	}

	public void setTitleTypeId(String titleTypeId) {
		this.titleTypeId = titleTypeId;
	}

	public String getIntroductionUri() {
		return introductionUri;
	}

	public void setIntroductionUri(String introductionUri) {
		this.introductionUri = introductionUri;
	}

	public String getTestResultUri() {
		return testResultUri;
	}

	public void setTestResultUri(String testResultUri) {
		this.testResultUri = testResultUri;
	}

	public String getAgreementUri() {
		return agreementUri;
	}

	public void setAgreementUri(String agreementUri) {
		this.agreementUri = agreementUri;
	}

	public String getPracticeNo() {
		return practiceNo;
	}

	public void setPracticeNo(String practiceNo) {
		this.practiceNo = practiceNo;
	}

	public String getIsOwnOrg() {
		return isOwnOrg;
	}

	public void setIsOwnOrg(String isOwnOrg) {
		this.isOwnOrg = isOwnOrg;
	}

	public List<ProjectForm> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<ProjectForm> projectList) {
		this.projectList = projectList;
	}

	public String getPoliticsStatusId() {
		return politicsStatusId;
	}

	public void setPoliticsStatusId(String politicsStatusId) {
		this.politicsStatusId = politicsStatusId;
	}

	public String getPoliticsStatusName() {
		return politicsStatusName;
	}

	public void setPoliticsStatusName(String politicsStatusName) {
		this.politicsStatusName = politicsStatusName;
	}

	public String getNativePlaceProvId() {
		return nativePlaceProvId;
	}

	public void setNativePlaceProvId(String nativePlaceProvId) {
		this.nativePlaceProvId = nativePlaceProvId;
	}

	public String getNativePlaceProvName() {
		return nativePlaceProvName;
	}

	public void setNativePlaceProvName(String nativePlaceProvName) {
		this.nativePlaceProvName = nativePlaceProvName;
	}

	public String getNativePlaceCityId() {
		return nativePlaceCityId;
	}

	public void setNativePlaceCityId(String nativePlaceCityId) {
		this.nativePlaceCityId = nativePlaceCityId;
	}

	public String getNativePlaceCityName() {
		return nativePlaceCityName;
	}

	public void setNativePlaceCityName(String nativePlaceCityName) {
		this.nativePlaceCityName = nativePlaceCityName;
	}

	public String getPostCodes() {
		return postCodes;
	}

	public void setPostCodes(String postCodes) {
		this.postCodes = postCodes;
	}

	public String getSocialExperience() {
		return socialExperience;
	}

	public void setSocialExperience(String socialExperience) {
		this.socialExperience = socialExperience;
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

	public String getAccording() {
		return according;
	}

	public void setAccording(String according) {
		this.according = according;
	}

	public String getCoverallNum() {
		return coverallNum;
	}

	public void setCoverallNum(String coverallNum) {
		this.coverallNum = coverallNum;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getWorkClother() {
		return workClother;
	}

	public void setWorkClother(String workClother) {
		this.workClother = workClother;
	}

	public List<EducationForm> getEducationList() {
		return educationList;
	}

	public void setEducationList(List<EducationForm> educationList) {
		this.educationList = educationList;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public List<ThesisForm> getThesisList() {
		return thesisList;
	}

	public void setThesisList(List<ThesisForm> thesisList) {
		this.thesisList = thesisList;
	}

	public String getPassportUri() {
		return passportUri;
	}

	public void setPassportUri(String passportUri) {
		this.passportUri = passportUri;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
}
