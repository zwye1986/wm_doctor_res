package com.pinde.sci.form.jsres;

import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorRecruit;
import com.pinde.sci.model.mo.SysUser;

import java.io.Serializable;

public class UserInfoExtForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7127481617315482792L;

	// 这几个字段没地方好加，加在这吧
	/**
	 * 培训开始年月
	 */
	private String trainYearMonth;

	/**
	 * 培训年限（年）
	 */
	private String trainYears;

	/**
	 * 计划培训结束年月
	 */
	private String trainEndYearMonth;

	private String armyType;

	private SysUser sysUser;
	private ResDoctor doctor;
	private UserResumeExtInfoForm userResumeExt;
	private ResDoctorRecruit recruit;
	public ResDoctorRecruit getRecruit() {
		return recruit;
	}
	public void setRecruit(ResDoctorRecruit recruit) {
		this.recruit = recruit;
	}
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	public ResDoctor getDoctor() {
		return doctor;
	}
	public void setDoctor(ResDoctor doctor) {
		this.doctor = doctor;
	}
	public UserResumeExtInfoForm getUserResumeExt() {
		return userResumeExt;
	}
	public void setUserResumeExt(UserResumeExtInfoForm userResumeExt) {
		this.userResumeExt = userResumeExt;
	}

	public String getTrainYearMonth() {
		return trainYearMonth;
	}

	public void setTrainYearMonth(String trainYearMonth) {
		this.trainYearMonth = trainYearMonth;
	}

	public String getTrainYears() {
		return trainYears;
	}

	public void setTrainYears(String trainYears) {
		this.trainYears = trainYears;
	}

	public String getTrainEndYearMonth() {
		return trainEndYearMonth;
	}

	public void setTrainEndYearMonth(String trainEndYearMonth) {
		this.trainEndYearMonth = trainEndYearMonth;
	}

	public String getArmyType() {
		return armyType;
	}

	public void setArmyType(String armyType) {
		this.armyType = armyType;
	}
}
