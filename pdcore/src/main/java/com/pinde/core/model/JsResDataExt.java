package com.pinde.core.model;

import java.util.List;

public class JsResDataExt  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5675231490936611610L;

	private String userFlow;
	private String resultFlow;
	private String deptFlow;
	private String cataFlow;
	private String dataType;
	private String dataFlow;
	// 大病历
	private String disease_pName; //病人姓名(病种同字段)
	private String mr_diagType;//诊断类型
	private String mr_no;//病历号
	private String mr_pName;//病人姓名

	//病种
	private String disease_diagName;//病种名称
	private String disease_diagType;//诊断类型
	private String disease_isCharge;//是否主管
	private String disease_isRescue;//是否抢救
	private String disease_mrNo ;//病历号/病理号
	private String disease_pDate;//日期
	private String disease_treatStep;//转归情况
	private String disease_caseType;//病例类型

	//操作技能
	private String fail_reason;//失败原因
	private String skill_mrNo;//病历号/病理号/检查号
	private String skill_operDate;//操作日期
	private String skill_operName;//操作名称
	private String skill_pName ;//病人姓名
	private String skill_result;//成功

	//手术
	private String operation_memo;//备注
	private String operation_mrNo;//病历号/病理号/检查号
	private String operation_operDate;//手术日期
	private String operation_operName;//手术名称
	private String operation_operRole ;//术中职务
	private String operation_pName;//病人姓名

	//活动
	private String activity_address;//活动地点
	private String activity_content;//内容
	private String activity_date;//日期
	private String activity_period;//学时
	private String activity_speaker ;//主讲人
	private String activity_way;//活动形式
	private String activity_way_optionDesc ;//轮转科室：血管外科(2020-05-01~2020-05-31)

	private List<JsResDataFile> files;

	private String filearr;

	public String getDisease_caseType() {
		return disease_caseType;
	}

	public void setDisease_caseType(String disease_caseType) {
		this.disease_caseType = disease_caseType;
	}

	public String getUserFlow() {
		return userFlow;
	}

	public void setUserFlow(String userFlow) {
		this.userFlow = userFlow;
	}

	public String getResultFlow() {
		return resultFlow;
	}

	public void setResultFlow(String resultFlow) {
		this.resultFlow = resultFlow;
	}

	public String getDeptFlow() {
		return deptFlow;
	}

	public void setDeptFlow(String deptFlow) {
		this.deptFlow = deptFlow;
	}

	public String getCataFlow() {
		return cataFlow;
	}

	public void setCataFlow(String cataFlow) {
		this.cataFlow = cataFlow;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataFlow() {
		return dataFlow;
	}

	public void setDataFlow(String dataFlow) {
		this.dataFlow = dataFlow;
	}

	public String getDisease_pName() {
		return disease_pName;
	}

	public void setDisease_pName(String disease_pName) {
		this.disease_pName = disease_pName;
	}

	public String getMr_diagType() {
		return mr_diagType;
	}

	public void setMr_diagType(String mr_diagType) {
		this.mr_diagType = mr_diagType;
	}

	public String getMr_no() {
		return mr_no;
	}

	public void setMr_no(String mr_no) {
		this.mr_no = mr_no;
	}

	public String getMr_pName() {
		return mr_pName;
	}

	public void setMr_pName(String mr_pName) {
		this.mr_pName = mr_pName;
	}

	public String getDisease_diagName() {
		return disease_diagName;
	}

	public void setDisease_diagName(String disease_diagName) {
		this.disease_diagName = disease_diagName;
	}

	public String getDisease_diagType() {
		return disease_diagType;
	}

	public void setDisease_diagType(String disease_diagType) {
		this.disease_diagType = disease_diagType;
	}

	public String getDisease_isCharge() {
		return disease_isCharge;
	}

	public void setDisease_isCharge(String disease_isCharge) {
		this.disease_isCharge = disease_isCharge;
	}

	public String getDisease_isRescue() {
		return disease_isRescue;
	}

	public void setDisease_isRescue(String disease_isRescue) {
		this.disease_isRescue = disease_isRescue;
	}

	public String getDisease_mrNo() {
		return disease_mrNo;
	}

	public void setDisease_mrNo(String disease_mrNo) {
		this.disease_mrNo = disease_mrNo;
	}

	public String getDisease_pDate() {
		return disease_pDate;
	}

	public void setDisease_pDate(String disease_pDate) {
		this.disease_pDate = disease_pDate;
	}

	public String getDisease_treatStep() {
		return disease_treatStep;
	}

	public void setDisease_treatStep(String disease_treatStep) {
		this.disease_treatStep = disease_treatStep;
	}

	public List<JsResDataFile> getFiles() {
		return files;
	}

	public void setFiles(List<JsResDataFile> files) {
		this.files = files;
	}

	public String getFail_reason() {
		return fail_reason;
	}

	public void setFail_reason(String fail_reason) {
		this.fail_reason = fail_reason;
	}

	public String getSkill_mrNo() {
		return skill_mrNo;
	}

	public void setSkill_mrNo(String skill_mrNo) {
		this.skill_mrNo = skill_mrNo;
	}

	public String getSkill_operDate() {
		return skill_operDate;
	}

	public void setSkill_operDate(String skill_operDate) {
		this.skill_operDate = skill_operDate;
	}

	public String getSkill_operName() {
		return skill_operName;
	}

	public void setSkill_operName(String skill_operName) {
		this.skill_operName = skill_operName;
	}

	public String getSkill_pName() {
		return skill_pName;
	}

	public void setSkill_pName(String skill_pName) {
		this.skill_pName = skill_pName;
	}

	public String getSkill_result() {
		return skill_result;
	}

	public void setSkill_result(String skill_result) {
		this.skill_result = skill_result;
	}

	public String getOperation_memo() {
		return operation_memo;
	}

	public void setOperation_memo(String operation_memo) {
		this.operation_memo = operation_memo;
	}

	public String getOperation_mrNo() {
		return operation_mrNo;
	}

	public void setOperation_mrNo(String operation_mrNo) {
		this.operation_mrNo = operation_mrNo;
	}

	public String getOperation_operDate() {
		return operation_operDate;
	}

	public void setOperation_operDate(String operation_operDate) {
		this.operation_operDate = operation_operDate;
	}

	public String getOperation_operName() {
		return operation_operName;
	}

	public void setOperation_operName(String operation_operName) {
		this.operation_operName = operation_operName;
	}

	public String getOperation_operRole() {
		return operation_operRole;
	}

	public void setOperation_operRole(String operation_operRole) {
		this.operation_operRole = operation_operRole;
	}

	public String getOperation_pName() {
		return operation_pName;
	}

	public void setOperation_pName(String operation_pName) {
		this.operation_pName = operation_pName;
	}

	public String getActivity_address() {
		return activity_address;
	}

	public void setActivity_address(String activity_address) {
		this.activity_address = activity_address;
	}

	public String getActivity_content() {
		return activity_content;
	}

	public void setActivity_content(String activity_content) {
		this.activity_content = activity_content;
	}

	public String getActivity_date() {
		return activity_date;
	}

	public void setActivity_date(String activity_date) {
		this.activity_date = activity_date;
	}

	public String getActivity_period() {
		return activity_period;
	}

	public void setActivity_period(String activity_period) {
		this.activity_period = activity_period;
	}

	public String getActivity_speaker() {
		return activity_speaker;
	}

	public void setActivity_speaker(String activity_speaker) {
		this.activity_speaker = activity_speaker;
	}

	public String getActivity_way() {
		return activity_way;
	}

	public void setActivity_way(String activity_way) {
		this.activity_way = activity_way;
	}

	public String getActivity_way_optionDesc() {
		return activity_way_optionDesc;
	}

	public void setActivity_way_optionDesc(String activity_way_optionDesc) {
		this.activity_way_optionDesc = activity_way_optionDesc;
	}

	public String getFilearr() {
		return filearr;
	}

	public void setFilearr(String filearr) {
		this.filearr = filearr;
	}
}
