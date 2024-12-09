package com.pinde.sci.form.res;

import com.pinde.sci.model.mo.SysDeptMonthExamInfo;
import com.pinde.sci.model.mo.SysDeptMonthPlan;
import com.pinde.sci.model.mo.SysDeptMonthPlanItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResDeptPlanForm extends SysDeptMonthPlan implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8611093669119469121L;

	private String chargeName;
	private String chargeFlow;
	private String groupLeaderName;
	private String groupLeaderFlow;
	private List<SysDeptMonthPlanItem> jxcfs = new ArrayList<SysDeptMonthPlanItem>();
	private List<SysDeptMonthPlanItem> bltlaps = new ArrayList<SysDeptMonthPlanItem>();
	private List<SysDeptMonthPlanItem> xjkaps = new ArrayList<SysDeptMonthPlanItem>();
	private List<SysDeptMonthPlanItem> qthds = new ArrayList<SysDeptMonthPlanItem>();
	private List<SysDeptMonthPlanItem> dsbghaps = new ArrayList<SysDeptMonthPlanItem>();
	private List<SysDeptMonthPlanItem> jtbkaps = new ArrayList<SysDeptMonthPlanItem>();
	private List<SysDeptMonthPlanItem> tkaps = new ArrayList<SysDeptMonthPlanItem>();
	private List<SysDeptMonthPlanItem> ddaps = new ArrayList<SysDeptMonthPlanItem>();
	private List<SysDeptMonthPlanItem> sqthds = new ArrayList<SysDeptMonthPlanItem>();
	private List<SysDeptMonthExamInfo> invigilators = new ArrayList<SysDeptMonthExamInfo>();
	private List<SysDeptMonthExamInfo> members = new ArrayList<SysDeptMonthExamInfo>();

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public String getChargeFlow() {
		return chargeFlow;
	}

	public void setChargeFlow(String chargeFlow) {
		this.chargeFlow = chargeFlow;
	}

	public String getGroupLeaderName() {
		return groupLeaderName;
	}

	public void setGroupLeaderName(String groupLeaderName) {
		this.groupLeaderName = groupLeaderName;
	}

	public String getGroupLeaderFlow() {
		return groupLeaderFlow;
	}

	public void setGroupLeaderFlow(String groupLeaderFlow) {
		this.groupLeaderFlow = groupLeaderFlow;
	}

	public List<SysDeptMonthPlanItem> getJxcfs() {
		return jxcfs;
	}

	public void setJxcfs(List<SysDeptMonthPlanItem> jxcfs) {
		this.jxcfs = jxcfs;
	}

	public List<SysDeptMonthPlanItem> getBltlaps() {
		return bltlaps;
	}

	public void setBltlaps(List<SysDeptMonthPlanItem> bltlaps) {
		this.bltlaps = bltlaps;
	}

	public List<SysDeptMonthPlanItem> getXjkaps() {
		return xjkaps;
	}

	public void setXjkaps(List<SysDeptMonthPlanItem> xjkaps) {
		this.xjkaps = xjkaps;
	}

	public List<SysDeptMonthPlanItem> getQthds() {
		return qthds;
	}

	public void setQthds(List<SysDeptMonthPlanItem> qthds) {
		this.qthds = qthds;
	}

	public List<SysDeptMonthPlanItem> getDsbghaps() {
		return dsbghaps;
	}

	public void setDsbghaps(List<SysDeptMonthPlanItem> dsbghaps) {
		this.dsbghaps = dsbghaps;
	}

	public List<SysDeptMonthPlanItem> getJtbkaps() {
		return jtbkaps;
	}

	public void setJtbkaps(List<SysDeptMonthPlanItem> jtbkaps) {
		this.jtbkaps = jtbkaps;
	}

	public List<SysDeptMonthPlanItem> getTkaps() {
		return tkaps;
	}

	public void setTkaps(List<SysDeptMonthPlanItem> tkaps) {
		this.tkaps = tkaps;
	}

	public List<SysDeptMonthPlanItem> getDdaps() {
		return ddaps;
	}

	public void setDdaps(List<SysDeptMonthPlanItem> ddaps) {
		this.ddaps = ddaps;
	}

	public List<SysDeptMonthPlanItem> getSqthds() {
		return sqthds;
	}

	public void setSqthds(List<SysDeptMonthPlanItem> sqthds) {
		this.sqthds = sqthds;
	}

	public List<SysDeptMonthExamInfo> getInvigilators() {
		return invigilators;
	}

	public void setInvigilators(List<SysDeptMonthExamInfo> invigilators) {
		this.invigilators = invigilators;
	}

	public List<SysDeptMonthExamInfo> getMembers() {
		return members;
	}

	public void setMembers(List<SysDeptMonthExamInfo> members) {
		this.members = members;
	}
}
