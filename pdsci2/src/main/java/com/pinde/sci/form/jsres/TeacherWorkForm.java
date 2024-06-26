package com.pinde.sci.form.jsres;

import com.pinde.sci.model.mo.SysUser;

import java.util.List;

/**
 * @ClassName TeacherWorkForm
 * @Description 带教工作量查询
 * @Author fengxf
 * @Date 2020/10/12
 */
public class TeacherWorkForm {
	// 用户
	private SysUser sysUser;
	// 人员类型
	private String[] doctorTypeIdList;
	// 查询开始日期
	private String operStartDate;
	// 查询结束日期
	private String operEndDate;
	// 排序项
	private String orderItem;
	// 逆序或正序
	private String sortName;
	// 基地flow
	private String orgFlow;
	// 类型
	private List<String> typeList;
	// 角色flow
	private String roleFlow;

	private String newStartDate;

	private String newEndDate;

	private String startDate;

	private String endDate;
	// 基地flow列表
	private List<String> orgFlowList;
	// 派送学校
	private String workOrgId;

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String[] getDoctorTypeIdList() {
		return doctorTypeIdList;
	}

	public void setDoctorTypeIdList(String[] doctorTypeIdList) {
		this.doctorTypeIdList = doctorTypeIdList;
	}

	public String getOperStartDate() {
		return operStartDate;
	}

	public void setOperStartDate(String operStartDate) {
		this.operStartDate = operStartDate;
	}

	public String getOperEndDate() {
		return operEndDate;
	}

	public void setOperEndDate(String operEndDate) {
		this.operEndDate = operEndDate;
	}

	public String getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(String orderItem) {
		this.orderItem = orderItem;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getOrgFlow() {
		return orgFlow;
	}

	public void setOrgFlow(String orgFlow) {
		this.orgFlow = orgFlow;
	}

	public List<String> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
	}

	public String getRoleFlow() {
		return roleFlow;
	}

	public void setRoleFlow(String roleFlow) {
		this.roleFlow = roleFlow;
	}

	public String getNewStartDate() {
		return newStartDate;
	}

	public void setNewStartDate(String newStartDate) {
		this.newStartDate = newStartDate;
	}

	public String getNewEndDate() {
		return newEndDate;
	}

	public void setNewEndDate(String newEndDate) {
		this.newEndDate = newEndDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<String> getOrgFlowList() {
		return orgFlowList;
	}

	public void setOrgFlowList(List<String> orgFlowList) {
		this.orgFlowList = orgFlowList;
	}

	public String getWorkOrgId() {
		return workOrgId;
	}

	public void setWorkOrgId(String workOrgId) {
		this.workOrgId = workOrgId;
	}
}
