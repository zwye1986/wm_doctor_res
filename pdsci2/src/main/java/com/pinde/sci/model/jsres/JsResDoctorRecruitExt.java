package com.pinde.sci.model.jsres;

import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorRecruit;
import com.pinde.sci.model.mo.ResScore;
import com.pinde.core.model.SysUser;

public class JsResDoctorRecruitExt extends ResDoctorRecruit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5675231490936611610L;
	
	private SysUser sysUser;
	private ResDoctor resDoctor;
	private ResScore resScore;
	// 审核意见
	private String admitNotice;
	private String globalNotice;
	private String orgCityId;
	private String orgCityName;
	private String localReason;
	private String cityReason;
	private String globalReason;
	private String skillTestId;
	private String theoryTestId;

	private String orgCode;
	private String rankNum;
	private String countryOrgName;

	private String theroyScoreFlow;

	private String speIdNew;

	private String speNameNew;

	private String firstIsPass;

	private String sex;

	private String age;

	private String graduateSchool;

	private String graduateTime;

	private String workAddr;

	private String workSchoolName;

	private String armyType;

	public String getWorkSchoolName() {
		return workSchoolName;
	}

	public void setWorkSchoolName(String workSchoolName) {
		this.workSchoolName = workSchoolName;
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

	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public String getGraduateTime() {
		return graduateTime;
	}

	public void setGraduateTime(String graduateTime) {
		this.graduateTime = graduateTime;
	}

	public String getWorkAddr() {
		return workAddr;
	}

	public void setWorkAddr(String workAddr) {
		this.workAddr = workAddr;
	}

	public String getFirstIsPass() {
		return firstIsPass;
	}

	public void setFirstIsPass(String firstIsPass) {
		this.firstIsPass = firstIsPass;
	}

	public String getSpeIdNew() {
		return speIdNew;
	}

	public void setSpeIdNew(String speIdNew) {
		this.speIdNew = speIdNew;
	}

	public String getSpeNameNew() {
		return speNameNew;
	}

	public void setSpeNameNew(String speNameNew) {
		this.speNameNew = speNameNew;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public ResScore getResScore() {
		return resScore;
	}

	public void setResScore(ResScore resScore) {
		this.resScore = resScore;
	}

	public ResDoctor getResDoctor() {
		return resDoctor;
	}

	public void setResDoctor(ResDoctor resDoctor) {
		this.resDoctor = resDoctor;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getAdmitNotice() {
		return admitNotice;
	}

	public void setAdmitNotice(String admitNotice) {
		this.admitNotice = admitNotice;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getOrgCityId() {
		return orgCityId;
	}

	public void setOrgCityId(String orgCityId) {
		this.orgCityId = orgCityId;
	}

	public String getOrgCityName() {
		return orgCityName;
	}

	public void setOrgCityName(String orgCityName) {
		this.orgCityName = orgCityName;
	}

	public String getLocalReason() {
		return localReason;
	}

	public void setLocalReason(String localReason) {
		this.localReason = localReason;
	}

	public String getCityReason() {
		return cityReason;
	}

	public void setCityReason(String cityReason) {
		this.cityReason = cityReason;
	}

	public String getGlobalReason() {
		return globalReason;
	}

	public void setGlobalReason(String globalReason) {
		this.globalReason = globalReason;
	}

	public String getTheoryTestId() {
		return theoryTestId;
	}

	public void setTheoryTestId(String theoryTestId) {
		this.theoryTestId = theoryTestId;
	}

	public String getSkillTestId() {
		return skillTestId;
	}

	public void setSkillTestId(String skillTestId) {
		this.skillTestId = skillTestId;
	}

	public String getRankNum() {
		return rankNum;
	}

	public void setRankNum(String rankNum) {
		this.rankNum = rankNum;
	}

	public String getCountryOrgName() {
		return countryOrgName;
	}

	public void setCountryOrgName(String countryOrgName) {
		this.countryOrgName = countryOrgName;
	}

	public String getTheroyScoreFlow() {
		return theroyScoreFlow;
	}

	public void setTheroyScoreFlow(String theroyScoreFlow) {
		this.theroyScoreFlow = theroyScoreFlow;
	}

	public String getGlobalNotice() {
		return globalNotice;
	}

	public void setGlobalNotice(String globalNotice) {
		this.globalNotice = globalNotice;
	}

	public String getArmyType() {
		return armyType;
	}

	public void setArmyType(String armyType) {
		this.armyType = armyType;
	}
}
