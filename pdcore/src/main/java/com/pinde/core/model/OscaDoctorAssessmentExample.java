package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class OscaDoctorAssessmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OscaDoctorAssessmentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andRecordFlowIsNull() {
            addCriterion("RECORD_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRecordFlowIsNotNull() {
            addCriterion("RECORD_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRecordFlowEqualTo(String value) {
            addCriterion("RECORD_FLOW =", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotEqualTo(String value) {
            addCriterion("RECORD_FLOW <>", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowGreaterThan(String value) {
            addCriterion("RECORD_FLOW >", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD_FLOW >=", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowLessThan(String value) {
            addCriterion("RECORD_FLOW <", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowLessThanOrEqualTo(String value) {
            addCriterion("RECORD_FLOW <=", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowLike(String value) {
            addCriterion("RECORD_FLOW like", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotLike(String value) {
            addCriterion("RECORD_FLOW not like", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowIn(List<String> values) {
            addCriterion("RECORD_FLOW in", values, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotIn(List<String> values) {
            addCriterion("RECORD_FLOW not in", values, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowBetween(String value1, String value2) {
            addCriterion("RECORD_FLOW between", value1, value2, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotBetween(String value1, String value2) {
            addCriterion("RECORD_FLOW not between", value1, value2, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowIsNull() {
            addCriterion("CLINICAL_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowIsNotNull() {
            addCriterion("CLINICAL_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowEqualTo(String value) {
            addCriterion("CLINICAL_FLOW =", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowNotEqualTo(String value) {
            addCriterion("CLINICAL_FLOW <>", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowGreaterThan(String value) {
            addCriterion("CLINICAL_FLOW >", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CLINICAL_FLOW >=", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowLessThan(String value) {
            addCriterion("CLINICAL_FLOW <", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowLessThanOrEqualTo(String value) {
            addCriterion("CLINICAL_FLOW <=", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowLike(String value) {
            addCriterion("CLINICAL_FLOW like", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowNotLike(String value) {
            addCriterion("CLINICAL_FLOW not like", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowIn(List<String> values) {
            addCriterion("CLINICAL_FLOW in", values, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowNotIn(List<String> values) {
            addCriterion("CLINICAL_FLOW not in", values, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowBetween(String value1, String value2) {
            addCriterion("CLINICAL_FLOW between", value1, value2, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowNotBetween(String value1, String value2) {
            addCriterion("CLINICAL_FLOW not between", value1, value2, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalNameIsNull() {
            addCriterion("CLINICAL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andClinicalNameIsNotNull() {
            addCriterion("CLINICAL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andClinicalNameEqualTo(String value) {
            addCriterion("CLINICAL_NAME =", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameNotEqualTo(String value) {
            addCriterion("CLINICAL_NAME <>", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameGreaterThan(String value) {
            addCriterion("CLINICAL_NAME >", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameGreaterThanOrEqualTo(String value) {
            addCriterion("CLINICAL_NAME >=", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameLessThan(String value) {
            addCriterion("CLINICAL_NAME <", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameLessThanOrEqualTo(String value) {
            addCriterion("CLINICAL_NAME <=", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameLike(String value) {
            addCriterion("CLINICAL_NAME like", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameNotLike(String value) {
            addCriterion("CLINICAL_NAME not like", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameIn(List<String> values) {
            addCriterion("CLINICAL_NAME in", values, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameNotIn(List<String> values) {
            addCriterion("CLINICAL_NAME not in", values, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameBetween(String value1, String value2) {
            addCriterion("CLINICAL_NAME between", value1, value2, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameNotBetween(String value1, String value2) {
            addCriterion("CLINICAL_NAME not between", value1, value2, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalYearIsNull() {
            addCriterion("CLINICAL_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andClinicalYearIsNotNull() {
            addCriterion("CLINICAL_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andClinicalYearEqualTo(String value) {
            addCriterion("CLINICAL_YEAR =", value, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearNotEqualTo(String value) {
            addCriterion("CLINICAL_YEAR <>", value, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearGreaterThan(String value) {
            addCriterion("CLINICAL_YEAR >", value, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearGreaterThanOrEqualTo(String value) {
            addCriterion("CLINICAL_YEAR >=", value, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearLessThan(String value) {
            addCriterion("CLINICAL_YEAR <", value, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearLessThanOrEqualTo(String value) {
            addCriterion("CLINICAL_YEAR <=", value, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearLike(String value) {
            addCriterion("CLINICAL_YEAR like", value, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearNotLike(String value) {
            addCriterion("CLINICAL_YEAR not like", value, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearIn(List<String> values) {
            addCriterion("CLINICAL_YEAR in", values, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearNotIn(List<String> values) {
            addCriterion("CLINICAL_YEAR not in", values, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearBetween(String value1, String value2) {
            addCriterion("CLINICAL_YEAR between", value1, value2, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearNotBetween(String value1, String value2) {
            addCriterion("CLINICAL_YEAR not between", value1, value2, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowIsNull() {
            addCriterion("DOCTOR_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowIsNotNull() {
            addCriterion("DOCTOR_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowEqualTo(String value) {
            addCriterion("DOCTOR_FLOW =", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowNotEqualTo(String value) {
            addCriterion("DOCTOR_FLOW <>", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowGreaterThan(String value) {
            addCriterion("DOCTOR_FLOW >", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_FLOW >=", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowLessThan(String value) {
            addCriterion("DOCTOR_FLOW <", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_FLOW <=", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowLike(String value) {
            addCriterion("DOCTOR_FLOW like", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowNotLike(String value) {
            addCriterion("DOCTOR_FLOW not like", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowIn(List<String> values) {
            addCriterion("DOCTOR_FLOW in", values, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowNotIn(List<String> values) {
            addCriterion("DOCTOR_FLOW not in", values, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowBetween(String value1, String value2) {
            addCriterion("DOCTOR_FLOW between", value1, value2, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_FLOW not between", value1, value2, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIsNull() {
            addCriterion("DOCTOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIsNotNull() {
            addCriterion("DOCTOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorNameEqualTo(String value) {
            addCriterion("DOCTOR_NAME =", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotEqualTo(String value) {
            addCriterion("DOCTOR_NAME <>", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameGreaterThan(String value) {
            addCriterion("DOCTOR_NAME >", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_NAME >=", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLessThan(String value) {
            addCriterion("DOCTOR_NAME <", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_NAME <=", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLike(String value) {
            addCriterion("DOCTOR_NAME like", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotLike(String value) {
            addCriterion("DOCTOR_NAME not like", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIn(List<String> values) {
            addCriterion("DOCTOR_NAME in", values, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotIn(List<String> values) {
            addCriterion("DOCTOR_NAME not in", values, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_NAME between", value1, value2, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_NAME not between", value1, value2, "doctorName");
            return (Criteria) this;
        }

        public Criteria andAppointTimeIsNull() {
            addCriterion("APPOINT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAppointTimeIsNotNull() {
            addCriterion("APPOINT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAppointTimeEqualTo(String value) {
            addCriterion("APPOINT_TIME =", value, "appointTime");
            return (Criteria) this;
        }

        public Criteria andAppointTimeNotEqualTo(String value) {
            addCriterion("APPOINT_TIME <>", value, "appointTime");
            return (Criteria) this;
        }

        public Criteria andAppointTimeGreaterThan(String value) {
            addCriterion("APPOINT_TIME >", value, "appointTime");
            return (Criteria) this;
        }

        public Criteria andAppointTimeGreaterThanOrEqualTo(String value) {
            addCriterion("APPOINT_TIME >=", value, "appointTime");
            return (Criteria) this;
        }

        public Criteria andAppointTimeLessThan(String value) {
            addCriterion("APPOINT_TIME <", value, "appointTime");
            return (Criteria) this;
        }

        public Criteria andAppointTimeLessThanOrEqualTo(String value) {
            addCriterion("APPOINT_TIME <=", value, "appointTime");
            return (Criteria) this;
        }

        public Criteria andAppointTimeLike(String value) {
            addCriterion("APPOINT_TIME like", value, "appointTime");
            return (Criteria) this;
        }

        public Criteria andAppointTimeNotLike(String value) {
            addCriterion("APPOINT_TIME not like", value, "appointTime");
            return (Criteria) this;
        }

        public Criteria andAppointTimeIn(List<String> values) {
            addCriterion("APPOINT_TIME in", values, "appointTime");
            return (Criteria) this;
        }

        public Criteria andAppointTimeNotIn(List<String> values) {
            addCriterion("APPOINT_TIME not in", values, "appointTime");
            return (Criteria) this;
        }

        public Criteria andAppointTimeBetween(String value1, String value2) {
            addCriterion("APPOINT_TIME between", value1, value2, "appointTime");
            return (Criteria) this;
        }

        public Criteria andAppointTimeNotBetween(String value1, String value2) {
            addCriterion("APPOINT_TIME not between", value1, value2, "appointTime");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIsNull() {
            addCriterion("AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIsNotNull() {
            addCriterion("AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID =", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID <>", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdGreaterThan(String value) {
            addCriterion("AUDIT_STATUS_ID >", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID >=", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLessThan(String value) {
            addCriterion("AUDIT_STATUS_ID <", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID <=", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLike(String value) {
            addCriterion("AUDIT_STATUS_ID like", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotLike(String value) {
            addCriterion("AUDIT_STATUS_ID not like", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIn(List<String> values) {
            addCriterion("AUDIT_STATUS_ID in", values, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotIn(List<String> values) {
            addCriterion("AUDIT_STATUS_ID not in", values, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_ID between", value1, value2, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_ID not between", value1, value2, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIsNull() {
            addCriterion("AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIsNotNull() {
            addCriterion("AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME =", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME <>", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameGreaterThan(String value) {
            addCriterion("AUDIT_STATUS_NAME >", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME >=", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLessThan(String value) {
            addCriterion("AUDIT_STATUS_NAME <", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME <=", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLike(String value) {
            addCriterion("AUDIT_STATUS_NAME like", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotLike(String value) {
            addCriterion("AUDIT_STATUS_NAME not like", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIn(List<String> values) {
            addCriterion("AUDIT_STATUS_NAME in", values, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotIn(List<String> values) {
            addCriterion("AUDIT_STATUS_NAME not in", values, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_NAME between", value1, value2, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_NAME not between", value1, value2, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("REASON is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("REASON is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("REASON =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("REASON <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("REASON >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("REASON >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("REASON <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("REASON <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("REASON like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("REASON not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("REASON in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("REASON not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("REASON between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("REASON not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andSiginTimeIsNull() {
            addCriterion("SIGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSiginTimeIsNotNull() {
            addCriterion("SIGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSiginTimeEqualTo(String value) {
            addCriterion("SIGIN_TIME =", value, "siginTime");
            return (Criteria) this;
        }

        public Criteria andSiginTimeNotEqualTo(String value) {
            addCriterion("SIGIN_TIME <>", value, "siginTime");
            return (Criteria) this;
        }

        public Criteria andSiginTimeGreaterThan(String value) {
            addCriterion("SIGIN_TIME >", value, "siginTime");
            return (Criteria) this;
        }

        public Criteria andSiginTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SIGIN_TIME >=", value, "siginTime");
            return (Criteria) this;
        }

        public Criteria andSiginTimeLessThan(String value) {
            addCriterion("SIGIN_TIME <", value, "siginTime");
            return (Criteria) this;
        }

        public Criteria andSiginTimeLessThanOrEqualTo(String value) {
            addCriterion("SIGIN_TIME <=", value, "siginTime");
            return (Criteria) this;
        }

        public Criteria andSiginTimeLike(String value) {
            addCriterion("SIGIN_TIME like", value, "siginTime");
            return (Criteria) this;
        }

        public Criteria andSiginTimeNotLike(String value) {
            addCriterion("SIGIN_TIME not like", value, "siginTime");
            return (Criteria) this;
        }

        public Criteria andSiginTimeIn(List<String> values) {
            addCriterion("SIGIN_TIME in", values, "siginTime");
            return (Criteria) this;
        }

        public Criteria andSiginTimeNotIn(List<String> values) {
            addCriterion("SIGIN_TIME not in", values, "siginTime");
            return (Criteria) this;
        }

        public Criteria andSiginTimeBetween(String value1, String value2) {
            addCriterion("SIGIN_TIME between", value1, value2, "siginTime");
            return (Criteria) this;
        }

        public Criteria andSiginTimeNotBetween(String value1, String value2) {
            addCriterion("SIGIN_TIME not between", value1, value2, "siginTime");
            return (Criteria) this;
        }

        public Criteria andSiginStatusIdIsNull() {
            addCriterion("SIGIN_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andSiginStatusIdIsNotNull() {
            addCriterion("SIGIN_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSiginStatusIdEqualTo(String value) {
            addCriterion("SIGIN_STATUS_ID =", value, "siginStatusId");
            return (Criteria) this;
        }

        public Criteria andSiginStatusIdNotEqualTo(String value) {
            addCriterion("SIGIN_STATUS_ID <>", value, "siginStatusId");
            return (Criteria) this;
        }

        public Criteria andSiginStatusIdGreaterThan(String value) {
            addCriterion("SIGIN_STATUS_ID >", value, "siginStatusId");
            return (Criteria) this;
        }

        public Criteria andSiginStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("SIGIN_STATUS_ID >=", value, "siginStatusId");
            return (Criteria) this;
        }

        public Criteria andSiginStatusIdLessThan(String value) {
            addCriterion("SIGIN_STATUS_ID <", value, "siginStatusId");
            return (Criteria) this;
        }

        public Criteria andSiginStatusIdLessThanOrEqualTo(String value) {
            addCriterion("SIGIN_STATUS_ID <=", value, "siginStatusId");
            return (Criteria) this;
        }

        public Criteria andSiginStatusIdLike(String value) {
            addCriterion("SIGIN_STATUS_ID like", value, "siginStatusId");
            return (Criteria) this;
        }

        public Criteria andSiginStatusIdNotLike(String value) {
            addCriterion("SIGIN_STATUS_ID not like", value, "siginStatusId");
            return (Criteria) this;
        }

        public Criteria andSiginStatusIdIn(List<String> values) {
            addCriterion("SIGIN_STATUS_ID in", values, "siginStatusId");
            return (Criteria) this;
        }

        public Criteria andSiginStatusIdNotIn(List<String> values) {
            addCriterion("SIGIN_STATUS_ID not in", values, "siginStatusId");
            return (Criteria) this;
        }

        public Criteria andSiginStatusIdBetween(String value1, String value2) {
            addCriterion("SIGIN_STATUS_ID between", value1, value2, "siginStatusId");
            return (Criteria) this;
        }

        public Criteria andSiginStatusIdNotBetween(String value1, String value2) {
            addCriterion("SIGIN_STATUS_ID not between", value1, value2, "siginStatusId");
            return (Criteria) this;
        }

        public Criteria andSiginStatusNameIsNull() {
            addCriterion("SIGIN_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSiginStatusNameIsNotNull() {
            addCriterion("SIGIN_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSiginStatusNameEqualTo(String value) {
            addCriterion("SIGIN_STATUS_NAME =", value, "siginStatusName");
            return (Criteria) this;
        }

        public Criteria andSiginStatusNameNotEqualTo(String value) {
            addCriterion("SIGIN_STATUS_NAME <>", value, "siginStatusName");
            return (Criteria) this;
        }

        public Criteria andSiginStatusNameGreaterThan(String value) {
            addCriterion("SIGIN_STATUS_NAME >", value, "siginStatusName");
            return (Criteria) this;
        }

        public Criteria andSiginStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("SIGIN_STATUS_NAME >=", value, "siginStatusName");
            return (Criteria) this;
        }

        public Criteria andSiginStatusNameLessThan(String value) {
            addCriterion("SIGIN_STATUS_NAME <", value, "siginStatusName");
            return (Criteria) this;
        }

        public Criteria andSiginStatusNameLessThanOrEqualTo(String value) {
            addCriterion("SIGIN_STATUS_NAME <=", value, "siginStatusName");
            return (Criteria) this;
        }

        public Criteria andSiginStatusNameLike(String value) {
            addCriterion("SIGIN_STATUS_NAME like", value, "siginStatusName");
            return (Criteria) this;
        }

        public Criteria andSiginStatusNameNotLike(String value) {
            addCriterion("SIGIN_STATUS_NAME not like", value, "siginStatusName");
            return (Criteria) this;
        }

        public Criteria andSiginStatusNameIn(List<String> values) {
            addCriterion("SIGIN_STATUS_NAME in", values, "siginStatusName");
            return (Criteria) this;
        }

        public Criteria andSiginStatusNameNotIn(List<String> values) {
            addCriterion("SIGIN_STATUS_NAME not in", values, "siginStatusName");
            return (Criteria) this;
        }

        public Criteria andSiginStatusNameBetween(String value1, String value2) {
            addCriterion("SIGIN_STATUS_NAME between", value1, value2, "siginStatusName");
            return (Criteria) this;
        }

        public Criteria andSiginStatusNameNotBetween(String value1, String value2) {
            addCriterion("SIGIN_STATUS_NAME not between", value1, value2, "siginStatusName");
            return (Criteria) this;
        }

        public Criteria andTicketNumberIsNull() {
            addCriterion("TICKET_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andTicketNumberIsNotNull() {
            addCriterion("TICKET_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andTicketNumberEqualTo(String value) {
            addCriterion("TICKET_NUMBER =", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberNotEqualTo(String value) {
            addCriterion("TICKET_NUMBER <>", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberGreaterThan(String value) {
            addCriterion("TICKET_NUMBER >", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberGreaterThanOrEqualTo(String value) {
            addCriterion("TICKET_NUMBER >=", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberLessThan(String value) {
            addCriterion("TICKET_NUMBER <", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberLessThanOrEqualTo(String value) {
            addCriterion("TICKET_NUMBER <=", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberLike(String value) {
            addCriterion("TICKET_NUMBER like", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberNotLike(String value) {
            addCriterion("TICKET_NUMBER not like", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberIn(List<String> values) {
            addCriterion("TICKET_NUMBER in", values, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberNotIn(List<String> values) {
            addCriterion("TICKET_NUMBER not in", values, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberBetween(String value1, String value2) {
            addCriterion("TICKET_NUMBER between", value1, value2, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberNotBetween(String value1, String value2) {
            addCriterion("TICKET_NUMBER not between", value1, value2, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andIsPassIsNull() {
            addCriterion("IS_PASS is null");
            return (Criteria) this;
        }

        public Criteria andIsPassIsNotNull() {
            addCriterion("IS_PASS is not null");
            return (Criteria) this;
        }

        public Criteria andIsPassEqualTo(String value) {
            addCriterion("IS_PASS =", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassNotEqualTo(String value) {
            addCriterion("IS_PASS <>", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassGreaterThan(String value) {
            addCriterion("IS_PASS >", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassGreaterThanOrEqualTo(String value) {
            addCriterion("IS_PASS >=", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassLessThan(String value) {
            addCriterion("IS_PASS <", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassLessThanOrEqualTo(String value) {
            addCriterion("IS_PASS <=", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassLike(String value) {
            addCriterion("IS_PASS like", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassNotLike(String value) {
            addCriterion("IS_PASS not like", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassIn(List<String> values) {
            addCriterion("IS_PASS in", values, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassNotIn(List<String> values) {
            addCriterion("IS_PASS not in", values, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassBetween(String value1, String value2) {
            addCriterion("IS_PASS between", value1, value2, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassNotBetween(String value1, String value2) {
            addCriterion("IS_PASS not between", value1, value2, "isPass");
            return (Criteria) this;
        }

        public Criteria andCodeInfoIsNull() {
            addCriterion("CODE_INFO is null");
            return (Criteria) this;
        }

        public Criteria andCodeInfoIsNotNull() {
            addCriterion("CODE_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andCodeInfoEqualTo(String value) {
            addCriterion("CODE_INFO =", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoNotEqualTo(String value) {
            addCriterion("CODE_INFO <>", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoGreaterThan(String value) {
            addCriterion("CODE_INFO >", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoGreaterThanOrEqualTo(String value) {
            addCriterion("CODE_INFO >=", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoLessThan(String value) {
            addCriterion("CODE_INFO <", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoLessThanOrEqualTo(String value) {
            addCriterion("CODE_INFO <=", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoLike(String value) {
            addCriterion("CODE_INFO like", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoNotLike(String value) {
            addCriterion("CODE_INFO not like", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoIn(List<String> values) {
            addCriterion("CODE_INFO in", values, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoNotIn(List<String> values) {
            addCriterion("CODE_INFO not in", values, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoBetween(String value1, String value2) {
            addCriterion("CODE_INFO between", value1, value2, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoNotBetween(String value1, String value2) {
            addCriterion("CODE_INFO not between", value1, value2, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andRecordStatusIsNull() {
            addCriterion("RECORD_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andRecordStatusIsNotNull() {
            addCriterion("RECORD_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andRecordStatusEqualTo(String value) {
            addCriterion("RECORD_STATUS =", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusNotEqualTo(String value) {
            addCriterion("RECORD_STATUS <>", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusGreaterThan(String value) {
            addCriterion("RECORD_STATUS >", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD_STATUS >=", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusLessThan(String value) {
            addCriterion("RECORD_STATUS <", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusLessThanOrEqualTo(String value) {
            addCriterion("RECORD_STATUS <=", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusLike(String value) {
            addCriterion("RECORD_STATUS like", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusNotLike(String value) {
            addCriterion("RECORD_STATUS not like", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusIn(List<String> values) {
            addCriterion("RECORD_STATUS in", values, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusNotIn(List<String> values) {
            addCriterion("RECORD_STATUS not in", values, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusBetween(String value1, String value2) {
            addCriterion("RECORD_STATUS between", value1, value2, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusNotBetween(String value1, String value2) {
            addCriterion("RECORD_STATUS not between", value1, value2, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("CREATE_TIME like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("CREATE_TIME not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<String> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<String> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowIsNull() {
            addCriterion("CREATE_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowIsNotNull() {
            addCriterion("CREATE_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowEqualTo(String value) {
            addCriterion("CREATE_USER_FLOW =", value, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowNotEqualTo(String value) {
            addCriterion("CREATE_USER_FLOW <>", value, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowGreaterThan(String value) {
            addCriterion("CREATE_USER_FLOW >", value, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_USER_FLOW >=", value, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowLessThan(String value) {
            addCriterion("CREATE_USER_FLOW <", value, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowLessThanOrEqualTo(String value) {
            addCriterion("CREATE_USER_FLOW <=", value, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowLike(String value) {
            addCriterion("CREATE_USER_FLOW like", value, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowNotLike(String value) {
            addCriterion("CREATE_USER_FLOW not like", value, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowIn(List<String> values) {
            addCriterion("CREATE_USER_FLOW in", values, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowNotIn(List<String> values) {
            addCriterion("CREATE_USER_FLOW not in", values, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowBetween(String value1, String value2) {
            addCriterion("CREATE_USER_FLOW between", value1, value2, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowNotBetween(String value1, String value2) {
            addCriterion("CREATE_USER_FLOW not between", value1, value2, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("MODIFY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("MODIFY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(String value) {
            addCriterion("MODIFY_TIME =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(String value) {
            addCriterion("MODIFY_TIME <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(String value) {
            addCriterion("MODIFY_TIME >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("MODIFY_TIME >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(String value) {
            addCriterion("MODIFY_TIME <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(String value) {
            addCriterion("MODIFY_TIME <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLike(String value) {
            addCriterion("MODIFY_TIME like", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotLike(String value) {
            addCriterion("MODIFY_TIME not like", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<String> values) {
            addCriterion("MODIFY_TIME in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<String> values) {
            addCriterion("MODIFY_TIME not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(String value1, String value2) {
            addCriterion("MODIFY_TIME between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(String value1, String value2) {
            addCriterion("MODIFY_TIME not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowIsNull() {
            addCriterion("MODIFY_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowIsNotNull() {
            addCriterion("MODIFY_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowEqualTo(String value) {
            addCriterion("MODIFY_USER_FLOW =", value, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowNotEqualTo(String value) {
            addCriterion("MODIFY_USER_FLOW <>", value, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowGreaterThan(String value) {
            addCriterion("MODIFY_USER_FLOW >", value, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("MODIFY_USER_FLOW >=", value, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowLessThan(String value) {
            addCriterion("MODIFY_USER_FLOW <", value, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowLessThanOrEqualTo(String value) {
            addCriterion("MODIFY_USER_FLOW <=", value, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowLike(String value) {
            addCriterion("MODIFY_USER_FLOW like", value, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowNotLike(String value) {
            addCriterion("MODIFY_USER_FLOW not like", value, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowIn(List<String> values) {
            addCriterion("MODIFY_USER_FLOW in", values, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowNotIn(List<String> values) {
            addCriterion("MODIFY_USER_FLOW not in", values, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowBetween(String value1, String value2) {
            addCriterion("MODIFY_USER_FLOW between", value1, value2, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowNotBetween(String value1, String value2) {
            addCriterion("MODIFY_USER_FLOW not between", value1, value2, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andIsPassNameIsNull() {
            addCriterion("IS_PASS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andIsPassNameIsNotNull() {
            addCriterion("IS_PASS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andIsPassNameEqualTo(String value) {
            addCriterion("IS_PASS_NAME =", value, "isPassName");
            return (Criteria) this;
        }

        public Criteria andIsPassNameNotEqualTo(String value) {
            addCriterion("IS_PASS_NAME <>", value, "isPassName");
            return (Criteria) this;
        }

        public Criteria andIsPassNameGreaterThan(String value) {
            addCriterion("IS_PASS_NAME >", value, "isPassName");
            return (Criteria) this;
        }

        public Criteria andIsPassNameGreaterThanOrEqualTo(String value) {
            addCriterion("IS_PASS_NAME >=", value, "isPassName");
            return (Criteria) this;
        }

        public Criteria andIsPassNameLessThan(String value) {
            addCriterion("IS_PASS_NAME <", value, "isPassName");
            return (Criteria) this;
        }

        public Criteria andIsPassNameLessThanOrEqualTo(String value) {
            addCriterion("IS_PASS_NAME <=", value, "isPassName");
            return (Criteria) this;
        }

        public Criteria andIsPassNameLike(String value) {
            addCriterion("IS_PASS_NAME like", value, "isPassName");
            return (Criteria) this;
        }

        public Criteria andIsPassNameNotLike(String value) {
            addCriterion("IS_PASS_NAME not like", value, "isPassName");
            return (Criteria) this;
        }

        public Criteria andIsPassNameIn(List<String> values) {
            addCriterion("IS_PASS_NAME in", values, "isPassName");
            return (Criteria) this;
        }

        public Criteria andIsPassNameNotIn(List<String> values) {
            addCriterion("IS_PASS_NAME not in", values, "isPassName");
            return (Criteria) this;
        }

        public Criteria andIsPassNameBetween(String value1, String value2) {
            addCriterion("IS_PASS_NAME between", value1, value2, "isPassName");
            return (Criteria) this;
        }

        public Criteria andIsPassNameNotBetween(String value1, String value2) {
            addCriterion("IS_PASS_NAME not between", value1, value2, "isPassName");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditIsNull() {
            addCriterion("IS_ADMIN_AUDIT is null");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditIsNotNull() {
            addCriterion("IS_ADMIN_AUDIT is not null");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditEqualTo(String value) {
            addCriterion("IS_ADMIN_AUDIT =", value, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditNotEqualTo(String value) {
            addCriterion("IS_ADMIN_AUDIT <>", value, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditGreaterThan(String value) {
            addCriterion("IS_ADMIN_AUDIT >", value, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditGreaterThanOrEqualTo(String value) {
            addCriterion("IS_ADMIN_AUDIT >=", value, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditLessThan(String value) {
            addCriterion("IS_ADMIN_AUDIT <", value, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditLessThanOrEqualTo(String value) {
            addCriterion("IS_ADMIN_AUDIT <=", value, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditLike(String value) {
            addCriterion("IS_ADMIN_AUDIT like", value, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditNotLike(String value) {
            addCriterion("IS_ADMIN_AUDIT not like", value, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditIn(List<String> values) {
            addCriterion("IS_ADMIN_AUDIT in", values, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditNotIn(List<String> values) {
            addCriterion("IS_ADMIN_AUDIT not in", values, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditBetween(String value1, String value2) {
            addCriterion("IS_ADMIN_AUDIT between", value1, value2, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditNotBetween(String value1, String value2) {
            addCriterion("IS_ADMIN_AUDIT not between", value1, value2, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeIsNull() {
            addCriterion("EXAM_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeIsNotNull() {
            addCriterion("EXAM_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeEqualTo(String value) {
            addCriterion("EXAM_START_TIME =", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeNotEqualTo(String value) {
            addCriterion("EXAM_START_TIME <>", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeGreaterThan(String value) {
            addCriterion("EXAM_START_TIME >", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_START_TIME >=", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeLessThan(String value) {
            addCriterion("EXAM_START_TIME <", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeLessThanOrEqualTo(String value) {
            addCriterion("EXAM_START_TIME <=", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeLike(String value) {
            addCriterion("EXAM_START_TIME like", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeNotLike(String value) {
            addCriterion("EXAM_START_TIME not like", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeIn(List<String> values) {
            addCriterion("EXAM_START_TIME in", values, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeNotIn(List<String> values) {
            addCriterion("EXAM_START_TIME not in", values, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeBetween(String value1, String value2) {
            addCriterion("EXAM_START_TIME between", value1, value2, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeNotBetween(String value1, String value2) {
            addCriterion("EXAM_START_TIME not between", value1, value2, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeIsNull() {
            addCriterion("EXAM_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeIsNotNull() {
            addCriterion("EXAM_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeEqualTo(String value) {
            addCriterion("EXAM_END_TIME =", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeNotEqualTo(String value) {
            addCriterion("EXAM_END_TIME <>", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeGreaterThan(String value) {
            addCriterion("EXAM_END_TIME >", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_END_TIME >=", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeLessThan(String value) {
            addCriterion("EXAM_END_TIME <", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeLessThanOrEqualTo(String value) {
            addCriterion("EXAM_END_TIME <=", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeLike(String value) {
            addCriterion("EXAM_END_TIME like", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeNotLike(String value) {
            addCriterion("EXAM_END_TIME not like", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeIn(List<String> values) {
            addCriterion("EXAM_END_TIME in", values, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeNotIn(List<String> values) {
            addCriterion("EXAM_END_TIME not in", values, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeBetween(String value1, String value2) {
            addCriterion("EXAM_END_TIME between", value1, value2, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeNotBetween(String value1, String value2) {
            addCriterion("EXAM_END_TIME not between", value1, value2, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andIsSavePassIsNull() {
            addCriterion("IS_SAVE_PASS is null");
            return (Criteria) this;
        }

        public Criteria andIsSavePassIsNotNull() {
            addCriterion("IS_SAVE_PASS is not null");
            return (Criteria) this;
        }

        public Criteria andIsSavePassEqualTo(String value) {
            addCriterion("IS_SAVE_PASS =", value, "isSavePass");
            return (Criteria) this;
        }

        public Criteria andIsSavePassNotEqualTo(String value) {
            addCriterion("IS_SAVE_PASS <>", value, "isSavePass");
            return (Criteria) this;
        }

        public Criteria andIsSavePassGreaterThan(String value) {
            addCriterion("IS_SAVE_PASS >", value, "isSavePass");
            return (Criteria) this;
        }

        public Criteria andIsSavePassGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SAVE_PASS >=", value, "isSavePass");
            return (Criteria) this;
        }

        public Criteria andIsSavePassLessThan(String value) {
            addCriterion("IS_SAVE_PASS <", value, "isSavePass");
            return (Criteria) this;
        }

        public Criteria andIsSavePassLessThanOrEqualTo(String value) {
            addCriterion("IS_SAVE_PASS <=", value, "isSavePass");
            return (Criteria) this;
        }

        public Criteria andIsSavePassLike(String value) {
            addCriterion("IS_SAVE_PASS like", value, "isSavePass");
            return (Criteria) this;
        }

        public Criteria andIsSavePassNotLike(String value) {
            addCriterion("IS_SAVE_PASS not like", value, "isSavePass");
            return (Criteria) this;
        }

        public Criteria andIsSavePassIn(List<String> values) {
            addCriterion("IS_SAVE_PASS in", values, "isSavePass");
            return (Criteria) this;
        }

        public Criteria andIsSavePassNotIn(List<String> values) {
            addCriterion("IS_SAVE_PASS not in", values, "isSavePass");
            return (Criteria) this;
        }

        public Criteria andIsSavePassBetween(String value1, String value2) {
            addCriterion("IS_SAVE_PASS between", value1, value2, "isSavePass");
            return (Criteria) this;
        }

        public Criteria andIsSavePassNotBetween(String value1, String value2) {
            addCriterion("IS_SAVE_PASS not between", value1, value2, "isSavePass");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}