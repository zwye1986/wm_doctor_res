package com.pinde.sci.form.res;

public class ActivityTargetForm {
	// 指标名称
	private String targetName;
	// 活动形式ID
	private String activityTypeId;
	// 基地主键
	private String orgFlow;
	// 指标类型 JX：教学 JZ：讲座
	private String targetType;
	// 选中的指标主键
	private String[] targetFlowArrs;

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getActivityTypeId() {
		return activityTypeId;
	}

	public void setActivityTypeId(String activityTypeId) {
		this.activityTypeId = activityTypeId;
	}

	public String getOrgFlow() {
		return orgFlow;
	}

	public void setOrgFlow(String orgFlow) {
		this.orgFlow = orgFlow;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String[] getTargetFlowArrs() {
		return targetFlowArrs;
	}

	public void setTargetFlowArrs(String[] targetFlowArrs) {
		this.targetFlowArrs = targetFlowArrs;
	}
}
