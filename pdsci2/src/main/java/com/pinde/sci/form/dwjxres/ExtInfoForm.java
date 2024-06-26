package com.pinde.sci.form.dwjxres;

import com.pinde.sci.model.mo.SysUser;

import java.io.Serializable;
import java.util.List;

public class ExtInfoForm implements Serializable{

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
	 * 其他资质证书
	 */
	private List<RegForm> regList;
	private List<RegForm> idNoUriList;
	private List<RegForm> graduatedUriList;
	private List<RegForm> qualifiedUriList;
	private List<RegForm> certifiedUriList;

	/**
	 * 进修生申请登记表图片uri
	 */
	private String registerFormUri;

	/**
	 * 医院等级评定证明图片uri
	 */
	private String hospitalLevelProveUri;

	/**
	 * 结业评语
	 */
	private String graduationMark;

	//进修系统优化需求
	private String titleTypeId;//职称类别id

	private String titleTypeName;//职称类别name

	public String getTitleTypeName() {
		return titleTypeName;
	}

	public void setTitleTypeName(String titleTypeName) {
		this.titleTypeName = titleTypeName;
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

	public List<WorkResumeForm> getWorkResumeList() {
		return workResumeList;
	}

	public void setWorkResumeList(List<WorkResumeForm> workResumeList) {
		this.workResumeList = workResumeList;
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

	public List<RegForm> getIdNoUriList() {
		return idNoUriList;
	}

	public void setIdNoUriList(List<RegForm> idNoUriList) {
		this.idNoUriList = idNoUriList;
	}

	public List<RegForm> getGraduatedUriList() {
		return graduatedUriList;
	}

	public void setGraduatedUriList(List<RegForm> graduatedUriList) {
		this.graduatedUriList = graduatedUriList;
	}

	public List<RegForm> getQualifiedUriList() {
		return qualifiedUriList;
	}

	public void setQualifiedUriList(List<RegForm> qualifiedUriList) {
		this.qualifiedUriList = qualifiedUriList;
	}

	public List<RegForm> getCertifiedUriList() {
		return certifiedUriList;
	}

	public void setCertifiedUriList(List<RegForm> certifiedUriList) {
		this.certifiedUriList = certifiedUriList;
	}

	public String getHospitalLevelProveUri() {
		return hospitalLevelProveUri;
	}

	public void setHospitalLevelProveUri(String hospitalLevelProveUri) {
		this.hospitalLevelProveUri = hospitalLevelProveUri;
	}
}
