package com.pinde.sci.model.res;

/**
 * 统计基地招录信息的bean
 * @author Administrator
 *
 */
public class OrgRecruitInfo {
	
	private String orgName;
	
	private String speName;
	
	/**
	 * 计划招录人数
	 */
	private Integer planCount;
	//公开招生计划
	private Integer planCountOrg;
	/**
	 * 学员确认人数
	 */
	private Integer confirmCount;
	
	/**
	 * 剩余人数
	 */
	private Integer surplusCount;
	
	public OrgRecruitInfo(){}
	
	public OrgRecruitInfo(String orgName , String speName , Integer planCount , Integer confirmCount,Integer planCountOrg){
		this.orgName = orgName;
		this.speName = speName;
		if(planCount==null){
			planCount = 0;
		}
		if(planCountOrg==null){
			planCountOrg = 0;
		}
		if(confirmCount==null){
			confirmCount = 0;
		}
		this.planCount = planCount;
		this.planCountOrg = planCountOrg;
		this.confirmCount = confirmCount;
		if(planCount!=null && confirmCount!=null){
			this.surplusCount = planCount-confirmCount;
		}
	}

	public Integer getPlanCountOrg() {
		return planCountOrg;
	}

	public void setPlanCountOrg(Integer planCountOrg) {
		this.planCountOrg = planCountOrg;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSpeName() {
		return speName;
	}

	public void setSpeName(String speName) {
		this.speName = speName;
	}

	public Integer getPlanCount() {
		return planCount;
	}

	public void setPlanCount(Integer planCount) {
		this.planCount = planCount;
	}

	public Integer getConfirmCount() {
		return confirmCount;
	}

	public void setConfirmCount(Integer confirmCount) {
		this.confirmCount = confirmCount;
	}

	public Integer getSurplusCount() {
		return surplusCount;
	}

	public void setSurplusCount(Integer surplusCount) {
		this.surplusCount = surplusCount;
	}


}
