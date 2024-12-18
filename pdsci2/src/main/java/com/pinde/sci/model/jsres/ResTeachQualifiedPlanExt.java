package com.pinde.sci.model.jsres;

import com.pinde.core.model.ResQualifiedPlanMsg;
import com.pinde.core.model.ResTeachQualifiedPlan;

import java.util.List;

public class ResTeachQualifiedPlanExt{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5675231490936611610L;

	private ResTeachQualifiedPlan plan;
	private List<ResQualifiedPlanMsg> planMsgList;

	public ResTeachQualifiedPlanExt() {
	}

	public ResTeachQualifiedPlanExt(ResTeachQualifiedPlan plan, List<ResQualifiedPlanMsg> planMsgList) {
		this.plan = plan;
		this.planMsgList = planMsgList;
	}

	public ResTeachQualifiedPlan getPlan() {
		return plan;
	}

	public void setPlan(ResTeachQualifiedPlan plan) {
		this.plan = plan;
	}

	public List<ResQualifiedPlanMsg> getPlanMsgList() {
		return planMsgList;
	}

	public void setPlanMsgList(List<ResQualifiedPlanMsg> planMsgList) {
		this.planMsgList = planMsgList;
	}
}
