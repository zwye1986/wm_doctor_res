package com.pinde.sci.form.jsres;

import java.io.Serializable;
import java.util.List;

public class OrganizationManage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3745342978759755677L;
	private String info;
	private List<OrganizationPerson> organizationPersonList;
	private List<OrganizationTrainingSystem> organizationTrainingSystemList;
	private List<OrganizationWorkCondition> organizationWorkConditionList;

	//培训机构
	private String leaderGroup;
	private String expertCommittee;
	private String managementDepartment;
	private String managementPersonnel;
	private String directorResponsibility;
	private String secretary;
	private String orgManagement;

	//住院医师规范化培训工作经验
	private String trainees;
	private String instructor;
	private String professionalBase;
	private String employer;
	private String trainees1;
	private String superiorDepartment;
	private String peer;

	//其他相关措施
	private String livingAllowance;
	private String allowanceMoney;
	private String trainingAgreement;
	private String archivesAndAge;
	private String socialSecurity;
	private String stay;
	private String registrationForPractitioners;

	public List<OrganizationWorkCondition> getOrganizationWorkConditionList() {
		return organizationWorkConditionList;
	}

	public void setOrganizationWorkConditionList(List<OrganizationWorkCondition> organizationWorkConditionList) {
		this.organizationWorkConditionList = organizationWorkConditionList;
	}

	public List<OrganizationTrainingSystem> getOrganizationTrainingSystemList() {
		return organizationTrainingSystemList;
	}

	public void setOrganizationTrainingSystemList(List<OrganizationTrainingSystem> organizationTrainingSystemList) {
		this.organizationTrainingSystemList = organizationTrainingSystemList;
	}

	public String getLivingAllowance() {
		return livingAllowance;
	}

	public void setLivingAllowance(String livingAllowance) {
		this.livingAllowance = livingAllowance;
	}

	public String getAllowanceMoney() {
		return allowanceMoney;
	}

	public void setAllowanceMoney(String allowanceMoney) {
		this.allowanceMoney = allowanceMoney;
	}

	public String getTrainingAgreement() {
		return trainingAgreement;
	}

	public void setTrainingAgreement(String trainingAgreement) {
		this.trainingAgreement = trainingAgreement;
	}

	public String getArchivesAndAge() {
		return archivesAndAge;
	}

	public void setArchivesAndAge(String archivesAndAge) {
		this.archivesAndAge = archivesAndAge;
	}

	public String getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public String getStay() {
		return stay;
	}

	public void setStay(String stay) {
		this.stay = stay;
	}

	public String getRegistrationForPractitioners() {
		return registrationForPractitioners;
	}

	public void setRegistrationForPractitioners(String registrationForPractitioners) {
		this.registrationForPractitioners = registrationForPractitioners;
	}

	public String getTrainees1() {
		return trainees1;
	}

	public void setTrainees1(String trainees1) {
		this.trainees1 = trainees1;
	}

	public String getSuperiorDepartment() {
		return superiorDepartment;
	}

	public void setSuperiorDepartment(String superiorDepartment) {
		this.superiorDepartment = superiorDepartment;
	}

	public String getPeer() {
		return peer;
	}

	public void setPeer(String peer) {
		this.peer = peer;
	}

	public String getProfessionalBase() {
		return professionalBase;
	}

	public void setProfessionalBase(String professionalBase) {
		this.professionalBase = professionalBase;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public String getTrainees() {
		return trainees;
	}

	public void setTrainees(String trainees) {
		this.trainees = trainees;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getManagementDepartment() {
		return managementDepartment;
	}

	public void setManagementDepartment(String managementDepartment) {
		this.managementDepartment = managementDepartment;
	}

	public String getManagementPersonnel() {
		return managementPersonnel;
	}

	public void setManagementPersonnel(String managementPersonnel) {
		this.managementPersonnel = managementPersonnel;
	}

	public String getDirectorResponsibility() {
		return directorResponsibility;
	}

	public void setDirectorResponsibility(String directorResponsibility) {
		this.directorResponsibility = directorResponsibility;
	}

	public String getSecretary() {
		return secretary;
	}

	public void setSecretary(String secretary) {
		this.secretary = secretary;
	}

	public String getOrgManagement() {
		return orgManagement;
	}

	public void setOrgManagement(String orgManagement) {
		this.orgManagement = orgManagement;
	}

	public String getExpertCommittee() {
		return expertCommittee;
	}

	public void setExpertCommittee(String expertCommittee) {
		this.expertCommittee = expertCommittee;
	}

	public String getLeaderGroup() {
		return leaderGroup;
	}

	public void setLeaderGroup(String leaderGroup) {
		this.leaderGroup = leaderGroup;
	}

	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public List<OrganizationPerson> getOrganizationPersonList() {
		return organizationPersonList;
	}
	public void setOrganizationPersonList(
			List<OrganizationPerson> organizationPersonList) {
		this.organizationPersonList = organizationPersonList;
	}
	
	
	
}
