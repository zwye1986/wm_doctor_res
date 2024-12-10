package com.pinde.sci.model.res;

import com.pinde.core.model.SysUser;
import com.pinde.sci.model.mo.*;

import java.util.List;

public class ResDoctorExt extends ResDoctor {
	private static final long serialVersionUID = -438010526424360596L;

    private ResAnnualAssessmentRecord assessmentRecord;

	private SysUser sysUser;

    private StuUserResume userResume;
	
	private ResDoctorRecruit doctorRecruit;

	private String is5plus3;

	private String graduationYear;


	private String medicineTypeId;

	private List<String> doctorTypeIdList;

	private List<String> doctorFlows;

    private List<String> deptFlows;

	private String linkXjFlag;

	// 轮转开始时间
	private String schStart;
	// 轮转结束时间
	private String schEnd;
	// 当前基地flow
	private String currOrgFlow;
	// 科主任flow
    private String kzrFlow;

	//pubUserResume clob字段
	private PubUserResume pubUserResume;
	public List<String> getDoctorFlows() {
		return doctorFlows;
	}

	public void setDoctorFlows(List<String> doctorFlows) {
		this.doctorFlows = doctorFlows;
	}

    public List<String> getDeptFlows() {
        return deptFlows;
    }

    public void setDeptFlows(List<String> deptFlows) {
        this.deptFlows = deptFlows;
    }

    public List<String> getDoctorTypeIdList() {
		return doctorTypeIdList;
	}

	public void setDoctorTypeIdList(List<String> doctorTypeIdList) {
		this.doctorTypeIdList = doctorTypeIdList;
	}

	public String getOrgCityId() {
		return orgCityId;
	}

	public void setOrgCityId(String orgCityId) {
		this.orgCityId = orgCityId;
	}

	public String getOrgAreaId() {
		return orgAreaId;
	}

	public void setOrgAreaId(String orgAreaId) {
		this.orgAreaId = orgAreaId;
	}

	public String getOrgProvId() {
		return orgProvId;
	}

	public void setOrgProvId(String orgProvId) {
		this.orgProvId = orgProvId;
	}

	private String orgProvId;

	private String orgCityId;

	private String orgAreaId;

    public ResAnnualAssessmentRecord getAssessmentRecord() {
        return assessmentRecord;
    }

    public void setAssessmentRecord(ResAnnualAssessmentRecord assessmentRecord) {
        this.assessmentRecord = assessmentRecord;
    }

    @Override
	public String getGraduationYear() {
		return graduationYear;
	}

	@Override
	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
	}

	public String getIs5plus3(){ return is5plus3; }

	public void setIs5plus3(String is5plus3){ this.is5plus3 = is5plus3; }

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

    public StuUserResume getUserResume() {
        return userResume;
    }

    public void setUserResume(StuUserResume userResume) {
        this.userResume = userResume;
    }

    public ResDoctorRecruit getDoctorRecruit() {
		return doctorRecruit;
	}

	public void setDoctorRecruit(ResDoctorRecruit doctorRecruit) {
		this.doctorRecruit = doctorRecruit;
	}

	public String getLinkXjFlag() {
		return linkXjFlag;
	}

	public void setLinkXjFlag(String linkXjFlag) {
		this.linkXjFlag = linkXjFlag;
	}

	public String getMedicineTypeId() {
		return medicineTypeId;
	}

	public void setMedicineTypeId(String medicineTypeId) {
		this.medicineTypeId = medicineTypeId;
	}

	public PubUserResume getPubUserResume() {
		return pubUserResume;
	}

	public void setPubUserResume(PubUserResume pubUserResume) {
		this.pubUserResume = pubUserResume;
	}

	public String getSchStart() {
		return schStart;
	}

	public void setSchStart(String schStart) {
		this.schStart = schStart;
	}

	public String getSchEnd() {
		return schEnd;
	}

	public void setSchEnd(String schEnd) {
		this.schEnd = schEnd;
	}

	public String getCurrOrgFlow() {
		return currOrgFlow;
	}

	public void setCurrOrgFlow(String currOrgFlow) {
		this.currOrgFlow = currOrgFlow;
	}

    public String getKzrFlow() {
        return kzrFlow;
    }

    public void setKzrFlow(String kzrFlow) {
        this.kzrFlow = kzrFlow;
    }
}
