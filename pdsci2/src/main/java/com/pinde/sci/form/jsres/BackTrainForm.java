package com.pinde.sci.form.jsres;

import java.io.Serializable;

public class BackTrainForm implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = -8391287297440107888L;
		private String reasonId;
		private String reasonName;
		private String reason;
		private String policyName;
		private String policyId;
		private String policy;
		private String dispositon;
		private String remark;
		private String sessionNumber;
		private String trainSpe;//培训专业
		private String delayReason;
		private String delayPicValueFile;
		private String graduationYear;
		private String trainType;//培训类型
		private String doctorFlow;
		private String recruitFlow;
		private String auditStatusId;//省厅审核状态ID
		private String auditStatusName;//省厅审核状态名称
		private String auditOpinion;//省厅审核意见
		
		public String getDoctorFlow() {
			return doctorFlow;
		}
		public void setDoctorFlow(String doctorFlow) {
			this.doctorFlow = doctorFlow;
		}
		public String getRecruitFlow() {
			return recruitFlow;
		}
		public void setRecruitFlow(String recruitFlow) {
			this.recruitFlow = recruitFlow;
		}
		public String getTrainType() {
			return trainType;
		}
		public void setTrainType(String trainType) {
			this.trainType = trainType;
		}
		public String getDelayReason() {
			return delayReason;
		}
		public void setDelayReason(String delayReason) {
			this.delayReason = delayReason;
		}
		public String getDelayPicValueFile() {
			return delayPicValueFile;
		}
		public void setDelayPicValueFile(String delayPicValueFile) {
			this.delayPicValueFile = delayPicValueFile;
		}
		public String getGraduationYear() {
			return graduationYear;
		}
		public void setGraduationYear(String graduationYear) {
			this.graduationYear = graduationYear;
		}
		public String getSessionNumber() {
			return sessionNumber;
		}
		public void setSessionNumber(String sessionNumber) {
			this.sessionNumber = sessionNumber;
		}
		public String getTrainSpe() {
			return trainSpe;
		}
		public void setTrainSpe(String trainSpe) {
			this.trainSpe = trainSpe;
		}
		public String getDispositon() {
			return dispositon;
		}
		public void setDispositon(String dispositon) {
			this.dispositon = dispositon;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getReasonId() {
			return reasonId;
		}
		public void setReasonId(String reasonId) {
			this.reasonId = reasonId;
		}
		public String getReasonName() {
			return reasonName;
		}
		public void setReasonName(String reasonName) {
			this.reasonName = reasonName;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public String getPolicyName() {
			return policyName;
		}
		public void setPolicyName(String policyName) {
			this.policyName = policyName;
		}
		public String getPolicyId() {
			return policyId;
		}
		public void setPolicyId(String policyId) {
			this.policyId = policyId;
		}
		public String getPolicy() {
			return policy;
		}
		public void setPolicy(String policy) {
			this.policy = policy;
		}
		public String getAuditOpinion() {
		return auditOpinion;
	}
		public void setAuditOpinion(String auditOpinion) {
			this.auditOpinion = auditOpinion;
		}
		public String getAuditStatusId() {
			return auditStatusId;
		}
		public void setAuditStatusId(String auditStatusId) {
			this.auditStatusId = auditStatusId;
		}
		public String getAuditStatusName() {
			return auditStatusName;
		}
		public void setAuditStatusName(String auditStatusName) {
		this.auditStatusName = auditStatusName;
	}
}
