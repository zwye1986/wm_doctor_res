package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class JsresGraduationInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JsresGraduationInfoExample() {
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

        public Criteria andInfoFlowIsNull() {
            addCriterion("INFO_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andInfoFlowIsNotNull() {
            addCriterion("INFO_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andInfoFlowEqualTo(String value) {
            addCriterion("INFO_FLOW =", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowNotEqualTo(String value) {
            addCriterion("INFO_FLOW <>", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowGreaterThan(String value) {
            addCriterion("INFO_FLOW >", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowGreaterThanOrEqualTo(String value) {
            addCriterion("INFO_FLOW >=", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowLessThan(String value) {
            addCriterion("INFO_FLOW <", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowLessThanOrEqualTo(String value) {
            addCriterion("INFO_FLOW <=", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowLike(String value) {
            addCriterion("INFO_FLOW like", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowNotLike(String value) {
            addCriterion("INFO_FLOW not like", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowIn(List<String> values) {
            addCriterion("INFO_FLOW in", values, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowNotIn(List<String> values) {
            addCriterion("INFO_FLOW not in", values, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowBetween(String value1, String value2) {
            addCriterion("INFO_FLOW between", value1, value2, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowNotBetween(String value1, String value2) {
            addCriterion("INFO_FLOW not between", value1, value2, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowIsNull() {
            addCriterion("RECRUIT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowIsNotNull() {
            addCriterion("RECRUIT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowEqualTo(String value) {
            addCriterion("RECRUIT_FLOW =", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotEqualTo(String value) {
            addCriterion("RECRUIT_FLOW <>", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowGreaterThan(String value) {
            addCriterion("RECRUIT_FLOW >", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_FLOW >=", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLessThan(String value) {
            addCriterion("RECRUIT_FLOW <", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_FLOW <=", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLike(String value) {
            addCriterion("RECRUIT_FLOW like", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotLike(String value) {
            addCriterion("RECRUIT_FLOW not like", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowIn(List<String> values) {
            addCriterion("RECRUIT_FLOW in", values, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotIn(List<String> values) {
            addCriterion("RECRUIT_FLOW not in", values, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowBetween(String value1, String value2) {
            addCriterion("RECRUIT_FLOW between", value1, value2, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_FLOW not between", value1, value2, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowIsNull() {
            addCriterion("APPLY_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyFlowIsNotNull() {
            addCriterion("APPLY_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyFlowEqualTo(String value) {
            addCriterion("APPLY_FLOW =", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotEqualTo(String value) {
            addCriterion("APPLY_FLOW <>", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowGreaterThan(String value) {
            addCriterion("APPLY_FLOW >", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_FLOW >=", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowLessThan(String value) {
            addCriterion("APPLY_FLOW <", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_FLOW <=", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowLike(String value) {
            addCriterion("APPLY_FLOW like", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotLike(String value) {
            addCriterion("APPLY_FLOW not like", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowIn(List<String> values) {
            addCriterion("APPLY_FLOW in", values, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotIn(List<String> values) {
            addCriterion("APPLY_FLOW not in", values, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowBetween(String value1, String value2) {
            addCriterion("APPLY_FLOW between", value1, value2, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_FLOW not between", value1, value2, "applyFlow");
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

        public Criteria andTestIdIsNull() {
            addCriterion("TEST_ID is null");
            return (Criteria) this;
        }

        public Criteria andTestIdIsNotNull() {
            addCriterion("TEST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTestIdEqualTo(String value) {
            addCriterion("TEST_ID =", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotEqualTo(String value) {
            addCriterion("TEST_ID <>", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdGreaterThan(String value) {
            addCriterion("TEST_ID >", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_ID >=", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdLessThan(String value) {
            addCriterion("TEST_ID <", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdLessThanOrEqualTo(String value) {
            addCriterion("TEST_ID <=", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdLike(String value) {
            addCriterion("TEST_ID like", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotLike(String value) {
            addCriterion("TEST_ID not like", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdIn(List<String> values) {
            addCriterion("TEST_ID in", values, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotIn(List<String> values) {
            addCriterion("TEST_ID not in", values, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdBetween(String value1, String value2) {
            addCriterion("TEST_ID between", value1, value2, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotBetween(String value1, String value2) {
            addCriterion("TEST_ID not between", value1, value2, "testId");
            return (Criteria) this;
        }

        public Criteria andScoreFlowIsNull() {
            addCriterion("SCORE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andScoreFlowIsNotNull() {
            addCriterion("SCORE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andScoreFlowEqualTo(String value) {
            addCriterion("SCORE_FLOW =", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowNotEqualTo(String value) {
            addCriterion("SCORE_FLOW <>", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowGreaterThan(String value) {
            addCriterion("SCORE_FLOW >", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_FLOW >=", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowLessThan(String value) {
            addCriterion("SCORE_FLOW <", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowLessThanOrEqualTo(String value) {
            addCriterion("SCORE_FLOW <=", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowLike(String value) {
            addCriterion("SCORE_FLOW like", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowNotLike(String value) {
            addCriterion("SCORE_FLOW not like", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowIn(List<String> values) {
            addCriterion("SCORE_FLOW in", values, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowNotIn(List<String> values) {
            addCriterion("SCORE_FLOW not in", values, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowBetween(String value1, String value2) {
            addCriterion("SCORE_FLOW between", value1, value2, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowNotBetween(String value1, String value2) {
            addCriterion("SCORE_FLOW not between", value1, value2, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowIsNull() {
            addCriterion("ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andOrgFlowIsNotNull() {
            addCriterion("ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andOrgFlowEqualTo(String value) {
            addCriterion("ORG_FLOW =", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotEqualTo(String value) {
            addCriterion("ORG_FLOW <>", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGreaterThan(String value) {
            addCriterion("ORG_FLOW >", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_FLOW >=", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLessThan(String value) {
            addCriterion("ORG_FLOW <", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("ORG_FLOW <=", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLike(String value) {
            addCriterion("ORG_FLOW like", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotLike(String value) {
            addCriterion("ORG_FLOW not like", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowIn(List<String> values) {
            addCriterion("ORG_FLOW in", values, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotIn(List<String> values) {
            addCriterion("ORG_FLOW not in", values, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowBetween(String value1, String value2) {
            addCriterion("ORG_FLOW between", value1, value2, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotBetween(String value1, String value2) {
            addCriterion("ORG_FLOW not between", value1, value2, "orgFlow");
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

        public Criteria andOrgCityIdIsNull() {
            addCriterion("ORG_CITY_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdIsNotNull() {
            addCriterion("ORG_CITY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdEqualTo(String value) {
            addCriterion("ORG_CITY_ID =", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdNotEqualTo(String value) {
            addCriterion("ORG_CITY_ID <>", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdGreaterThan(String value) {
            addCriterion("ORG_CITY_ID >", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CITY_ID >=", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdLessThan(String value) {
            addCriterion("ORG_CITY_ID <", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_CITY_ID <=", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdLike(String value) {
            addCriterion("ORG_CITY_ID like", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdNotLike(String value) {
            addCriterion("ORG_CITY_ID not like", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdIn(List<String> values) {
            addCriterion("ORG_CITY_ID in", values, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdNotIn(List<String> values) {
            addCriterion("ORG_CITY_ID not in", values, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdBetween(String value1, String value2) {
            addCriterion("ORG_CITY_ID between", value1, value2, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdNotBetween(String value1, String value2) {
            addCriterion("ORG_CITY_ID not between", value1, value2, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameIsNull() {
            addCriterion("ORG_CITY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameIsNotNull() {
            addCriterion("ORG_CITY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameEqualTo(String value) {
            addCriterion("ORG_CITY_NAME =", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameNotEqualTo(String value) {
            addCriterion("ORG_CITY_NAME <>", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameGreaterThan(String value) {
            addCriterion("ORG_CITY_NAME >", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CITY_NAME >=", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameLessThan(String value) {
            addCriterion("ORG_CITY_NAME <", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_CITY_NAME <=", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameLike(String value) {
            addCriterion("ORG_CITY_NAME like", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameNotLike(String value) {
            addCriterion("ORG_CITY_NAME not like", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameIn(List<String> values) {
            addCriterion("ORG_CITY_NAME in", values, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameNotIn(List<String> values) {
            addCriterion("ORG_CITY_NAME not in", values, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameBetween(String value1, String value2) {
            addCriterion("ORG_CITY_NAME between", value1, value2, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameNotBetween(String value1, String value2) {
            addCriterion("ORG_CITY_NAME not between", value1, value2, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNull() {
            addCriterion("ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("ORG_NAME =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("ORG_NAME <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("ORG_NAME >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NAME >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("ORG_NAME <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_NAME <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("ORG_NAME like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("ORG_NAME not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("ORG_NAME in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("ORG_NAME not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("ORG_NAME between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("ORG_NAME not between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andSpeIdIsNull() {
            addCriterion("SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSpeIdIsNotNull() {
            addCriterion("SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSpeIdEqualTo(String value) {
            addCriterion("SPE_ID =", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotEqualTo(String value) {
            addCriterion("SPE_ID <>", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdGreaterThan(String value) {
            addCriterion("SPE_ID >", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_ID >=", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLessThan(String value) {
            addCriterion("SPE_ID <", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLessThanOrEqualTo(String value) {
            addCriterion("SPE_ID <=", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLike(String value) {
            addCriterion("SPE_ID like", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotLike(String value) {
            addCriterion("SPE_ID not like", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdIn(List<String> values) {
            addCriterion("SPE_ID in", values, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotIn(List<String> values) {
            addCriterion("SPE_ID not in", values, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdBetween(String value1, String value2) {
            addCriterion("SPE_ID between", value1, value2, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotBetween(String value1, String value2) {
            addCriterion("SPE_ID not between", value1, value2, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeNameIsNull() {
            addCriterion("SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSpeNameIsNotNull() {
            addCriterion("SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSpeNameEqualTo(String value) {
            addCriterion("SPE_NAME =", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotEqualTo(String value) {
            addCriterion("SPE_NAME <>", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameGreaterThan(String value) {
            addCriterion("SPE_NAME >", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_NAME >=", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLessThan(String value) {
            addCriterion("SPE_NAME <", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLessThanOrEqualTo(String value) {
            addCriterion("SPE_NAME <=", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLike(String value) {
            addCriterion("SPE_NAME like", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotLike(String value) {
            addCriterion("SPE_NAME not like", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameIn(List<String> values) {
            addCriterion("SPE_NAME in", values, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotIn(List<String> values) {
            addCriterion("SPE_NAME not in", values, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameBetween(String value1, String value2) {
            addCriterion("SPE_NAME between", value1, value2, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotBetween(String value1, String value2) {
            addCriterion("SPE_NAME not between", value1, value2, "speName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdIsNull() {
            addCriterion("CHANGE_SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdIsNotNull() {
            addCriterion("CHANGE_SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdEqualTo(String value) {
            addCriterion("CHANGE_SPE_ID =", value, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdNotEqualTo(String value) {
            addCriterion("CHANGE_SPE_ID <>", value, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdGreaterThan(String value) {
            addCriterion("CHANGE_SPE_ID >", value, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("CHANGE_SPE_ID >=", value, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdLessThan(String value) {
            addCriterion("CHANGE_SPE_ID <", value, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdLessThanOrEqualTo(String value) {
            addCriterion("CHANGE_SPE_ID <=", value, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdLike(String value) {
            addCriterion("CHANGE_SPE_ID like", value, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdNotLike(String value) {
            addCriterion("CHANGE_SPE_ID not like", value, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdIn(List<String> values) {
            addCriterion("CHANGE_SPE_ID in", values, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdNotIn(List<String> values) {
            addCriterion("CHANGE_SPE_ID not in", values, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdBetween(String value1, String value2) {
            addCriterion("CHANGE_SPE_ID between", value1, value2, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdNotBetween(String value1, String value2) {
            addCriterion("CHANGE_SPE_ID not between", value1, value2, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameIsNull() {
            addCriterion("CHANGE_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameIsNotNull() {
            addCriterion("CHANGE_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameEqualTo(String value) {
            addCriterion("CHANGE_SPE_NAME =", value, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameNotEqualTo(String value) {
            addCriterion("CHANGE_SPE_NAME <>", value, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameGreaterThan(String value) {
            addCriterion("CHANGE_SPE_NAME >", value, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHANGE_SPE_NAME >=", value, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameLessThan(String value) {
            addCriterion("CHANGE_SPE_NAME <", value, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameLessThanOrEqualTo(String value) {
            addCriterion("CHANGE_SPE_NAME <=", value, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameLike(String value) {
            addCriterion("CHANGE_SPE_NAME like", value, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameNotLike(String value) {
            addCriterion("CHANGE_SPE_NAME not like", value, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameIn(List<String> values) {
            addCriterion("CHANGE_SPE_NAME in", values, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameNotIn(List<String> values) {
            addCriterion("CHANGE_SPE_NAME not in", values, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameBetween(String value1, String value2) {
            addCriterion("CHANGE_SPE_NAME between", value1, value2, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameNotBetween(String value1, String value2) {
            addCriterion("CHANGE_SPE_NAME not between", value1, value2, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIsNull() {
            addCriterion("SESSION_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIsNotNull() {
            addCriterion("SESSION_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberEqualTo(String value) {
            addCriterion("SESSION_NUMBER =", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotEqualTo(String value) {
            addCriterion("SESSION_NUMBER <>", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThan(String value) {
            addCriterion("SESSION_NUMBER >", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER >=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThan(String value) {
            addCriterion("SESSION_NUMBER <", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER <=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLike(String value) {
            addCriterion("SESSION_NUMBER like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotLike(String value) {
            addCriterion("SESSION_NUMBER not like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIn(List<String> values) {
            addCriterion("SESSION_NUMBER in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotIn(List<String> values) {
            addCriterion("SESSION_NUMBER not in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER between", value1, value2, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER not between", value1, value2, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andTrainYearIsNull() {
            addCriterion("TRAIN_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andTrainYearIsNotNull() {
            addCriterion("TRAIN_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andTrainYearEqualTo(String value) {
            addCriterion("TRAIN_YEAR =", value, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearNotEqualTo(String value) {
            addCriterion("TRAIN_YEAR <>", value, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearGreaterThan(String value) {
            addCriterion("TRAIN_YEAR >", value, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_YEAR >=", value, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearLessThan(String value) {
            addCriterion("TRAIN_YEAR <", value, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_YEAR <=", value, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearLike(String value) {
            addCriterion("TRAIN_YEAR like", value, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearNotLike(String value) {
            addCriterion("TRAIN_YEAR not like", value, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearIn(List<String> values) {
            addCriterion("TRAIN_YEAR in", values, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearNotIn(List<String> values) {
            addCriterion("TRAIN_YEAR not in", values, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearBetween(String value1, String value2) {
            addCriterion("TRAIN_YEAR between", value1, value2, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearNotBetween(String value1, String value2) {
            addCriterion("TRAIN_YEAR not between", value1, value2, "trainYear");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(String value) {
            addCriterion("START_DATE =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(String value) {
            addCriterion("START_DATE <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(String value) {
            addCriterion("START_DATE >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("START_DATE >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(String value) {
            addCriterion("START_DATE <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(String value) {
            addCriterion("START_DATE <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLike(String value) {
            addCriterion("START_DATE like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotLike(String value) {
            addCriterion("START_DATE not like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<String> values) {
            addCriterion("START_DATE in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<String> values) {
            addCriterion("START_DATE not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(String value1, String value2) {
            addCriterion("START_DATE between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(String value1, String value2) {
            addCriterion("START_DATE not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(String value) {
            addCriterion("END_DATE =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(String value) {
            addCriterion("END_DATE <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(String value) {
            addCriterion("END_DATE >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("END_DATE >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(String value) {
            addCriterion("END_DATE <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(String value) {
            addCriterion("END_DATE <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLike(String value) {
            addCriterion("END_DATE like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotLike(String value) {
            addCriterion("END_DATE not like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<String> values) {
            addCriterion("END_DATE in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<String> values) {
            addCriterion("END_DATE not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(String value1, String value2) {
            addCriterion("END_DATE between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(String value1, String value2) {
            addCriterion("END_DATE not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEducationIdIsNull() {
            addCriterion("EDUCATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andEducationIdIsNotNull() {
            addCriterion("EDUCATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEducationIdEqualTo(String value) {
            addCriterion("EDUCATION_ID =", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdNotEqualTo(String value) {
            addCriterion("EDUCATION_ID <>", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdGreaterThan(String value) {
            addCriterion("EDUCATION_ID >", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdGreaterThanOrEqualTo(String value) {
            addCriterion("EDUCATION_ID >=", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdLessThan(String value) {
            addCriterion("EDUCATION_ID <", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdLessThanOrEqualTo(String value) {
            addCriterion("EDUCATION_ID <=", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdLike(String value) {
            addCriterion("EDUCATION_ID like", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdNotLike(String value) {
            addCriterion("EDUCATION_ID not like", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdIn(List<String> values) {
            addCriterion("EDUCATION_ID in", values, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdNotIn(List<String> values) {
            addCriterion("EDUCATION_ID not in", values, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdBetween(String value1, String value2) {
            addCriterion("EDUCATION_ID between", value1, value2, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdNotBetween(String value1, String value2) {
            addCriterion("EDUCATION_ID not between", value1, value2, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationNameIsNull() {
            addCriterion("EDUCATION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEducationNameIsNotNull() {
            addCriterion("EDUCATION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEducationNameEqualTo(String value) {
            addCriterion("EDUCATION_NAME =", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameNotEqualTo(String value) {
            addCriterion("EDUCATION_NAME <>", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameGreaterThan(String value) {
            addCriterion("EDUCATION_NAME >", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameGreaterThanOrEqualTo(String value) {
            addCriterion("EDUCATION_NAME >=", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameLessThan(String value) {
            addCriterion("EDUCATION_NAME <", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameLessThanOrEqualTo(String value) {
            addCriterion("EDUCATION_NAME <=", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameLike(String value) {
            addCriterion("EDUCATION_NAME like", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameNotLike(String value) {
            addCriterion("EDUCATION_NAME not like", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameIn(List<String> values) {
            addCriterion("EDUCATION_NAME in", values, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameNotIn(List<String> values) {
            addCriterion("EDUCATION_NAME not in", values, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameBetween(String value1, String value2) {
            addCriterion("EDUCATION_NAME between", value1, value2, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameNotBetween(String value1, String value2) {
            addCriterion("EDUCATION_NAME not between", value1, value2, "educationName");
            return (Criteria) this;
        }

        public Criteria andCertificateNoIsNull() {
            addCriterion("CERTIFICATE_NO is null");
            return (Criteria) this;
        }

        public Criteria andCertificateNoIsNotNull() {
            addCriterion("CERTIFICATE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateNoEqualTo(String value) {
            addCriterion("CERTIFICATE_NO =", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotEqualTo(String value) {
            addCriterion("CERTIFICATE_NO <>", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoGreaterThan(String value) {
            addCriterion("CERTIFICATE_NO >", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_NO >=", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoLessThan(String value) {
            addCriterion("CERTIFICATE_NO <", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_NO <=", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoLike(String value) {
            addCriterion("CERTIFICATE_NO like", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotLike(String value) {
            addCriterion("CERTIFICATE_NO not like", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoIn(List<String> values) {
            addCriterion("CERTIFICATE_NO in", values, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotIn(List<String> values) {
            addCriterion("CERTIFICATE_NO not in", values, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_NO between", value1, value2, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_NO not between", value1, value2, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlIsNull() {
            addCriterion("CERTIFICATE_URL is null");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlIsNotNull() {
            addCriterion("CERTIFICATE_URL is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlEqualTo(String value) {
            addCriterion("CERTIFICATE_URL =", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlNotEqualTo(String value) {
            addCriterion("CERTIFICATE_URL <>", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlGreaterThan(String value) {
            addCriterion("CERTIFICATE_URL >", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_URL >=", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlLessThan(String value) {
            addCriterion("CERTIFICATE_URL <", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_URL <=", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlLike(String value) {
            addCriterion("CERTIFICATE_URL like", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlNotLike(String value) {
            addCriterion("CERTIFICATE_URL not like", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlIn(List<String> values) {
            addCriterion("CERTIFICATE_URL in", values, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlNotIn(List<String> values) {
            addCriterion("CERTIFICATE_URL not in", values, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_URL between", value1, value2, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_URL not between", value1, value2, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andMaterialNameIsNull() {
            addCriterion("MATERIAL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMaterialNameIsNotNull() {
            addCriterion("MATERIAL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialNameEqualTo(String value) {
            addCriterion("MATERIAL_NAME =", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotEqualTo(String value) {
            addCriterion("MATERIAL_NAME <>", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameGreaterThan(String value) {
            addCriterion("MATERIAL_NAME >", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameGreaterThanOrEqualTo(String value) {
            addCriterion("MATERIAL_NAME >=", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameLessThan(String value) {
            addCriterion("MATERIAL_NAME <", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameLessThanOrEqualTo(String value) {
            addCriterion("MATERIAL_NAME <=", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameLike(String value) {
            addCriterion("MATERIAL_NAME like", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotLike(String value) {
            addCriterion("MATERIAL_NAME not like", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameIn(List<String> values) {
            addCriterion("MATERIAL_NAME in", values, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotIn(List<String> values) {
            addCriterion("MATERIAL_NAME not in", values, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameBetween(String value1, String value2) {
            addCriterion("MATERIAL_NAME between", value1, value2, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotBetween(String value1, String value2) {
            addCriterion("MATERIAL_NAME not between", value1, value2, "materialName");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUrlIsNull() {
            addCriterion("QUALIFICATION_MATERIAL_URL is null");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUrlIsNotNull() {
            addCriterion("QUALIFICATION_MATERIAL_URL is not null");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUrlEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URL =", value, "qualificationMaterialUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUrlNotEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URL <>", value, "qualificationMaterialUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUrlGreaterThan(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URL >", value, "qualificationMaterialUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUrlGreaterThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URL >=", value, "qualificationMaterialUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUrlLessThan(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URL <", value, "qualificationMaterialUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUrlLessThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URL <=", value, "qualificationMaterialUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUrlLike(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URL like", value, "qualificationMaterialUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUrlNotLike(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URL not like", value, "qualificationMaterialUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUrlIn(List<String> values) {
            addCriterion("QUALIFICATION_MATERIAL_URL in", values, "qualificationMaterialUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUrlNotIn(List<String> values) {
            addCriterion("QUALIFICATION_MATERIAL_URL not in", values, "qualificationMaterialUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUrlBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_MATERIAL_URL between", value1, value2, "qualificationMaterialUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUrlNotBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_MATERIAL_URL not between", value1, value2, "qualificationMaterialUrl");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeIsNull() {
            addCriterion("QUALIFICATION_MATERIAL_CODE is null");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeIsNotNull() {
            addCriterion("QUALIFICATION_MATERIAL_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE =", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeNotEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE <>", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeGreaterThan(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE >", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeGreaterThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE >=", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeLessThan(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE <", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeLessThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE <=", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeLike(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE like", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeNotLike(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE not like", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeIn(List<String> values) {
            addCriterion("QUALIFICATION_MATERIAL_CODE in", values, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeNotIn(List<String> values) {
            addCriterion("QUALIFICATION_MATERIAL_CODE not in", values, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_MATERIAL_CODE between", value1, value2, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeNotBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_MATERIAL_CODE not between", value1, value2, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameIsNull() {
            addCriterion("PRACTICING_SCOPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameIsNotNull() {
            addCriterion("PRACTICING_SCOPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_NAME =", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameNotEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_NAME <>", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameGreaterThan(String value) {
            addCriterion("PRACTICING_SCOPE_NAME >", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_NAME >=", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameLessThan(String value) {
            addCriterion("PRACTICING_SCOPE_NAME <", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameLessThanOrEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_NAME <=", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameLike(String value) {
            addCriterion("PRACTICING_SCOPE_NAME like", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameNotLike(String value) {
            addCriterion("PRACTICING_SCOPE_NAME not like", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameIn(List<String> values) {
            addCriterion("PRACTICING_SCOPE_NAME in", values, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameNotIn(List<String> values) {
            addCriterion("PRACTICING_SCOPE_NAME not in", values, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameBetween(String value1, String value2) {
            addCriterion("PRACTICING_SCOPE_NAME between", value1, value2, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameNotBetween(String value1, String value2) {
            addCriterion("PRACTICING_SCOPE_NAME not between", value1, value2, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryCodeIsNull() {
            addCriterion("PRACTICING_CATEGORY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryCodeIsNotNull() {
            addCriterion("PRACTICING_CATEGORY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryCodeEqualTo(String value) {
            addCriterion("PRACTICING_CATEGORY_CODE =", value, "practicingCategoryCode");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryCodeNotEqualTo(String value) {
            addCriterion("PRACTICING_CATEGORY_CODE <>", value, "practicingCategoryCode");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryCodeGreaterThan(String value) {
            addCriterion("PRACTICING_CATEGORY_CODE >", value, "practicingCategoryCode");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PRACTICING_CATEGORY_CODE >=", value, "practicingCategoryCode");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryCodeLessThan(String value) {
            addCriterion("PRACTICING_CATEGORY_CODE <", value, "practicingCategoryCode");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryCodeLessThanOrEqualTo(String value) {
            addCriterion("PRACTICING_CATEGORY_CODE <=", value, "practicingCategoryCode");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryCodeLike(String value) {
            addCriterion("PRACTICING_CATEGORY_CODE like", value, "practicingCategoryCode");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryCodeNotLike(String value) {
            addCriterion("PRACTICING_CATEGORY_CODE not like", value, "practicingCategoryCode");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryCodeIn(List<String> values) {
            addCriterion("PRACTICING_CATEGORY_CODE in", values, "practicingCategoryCode");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryCodeNotIn(List<String> values) {
            addCriterion("PRACTICING_CATEGORY_CODE not in", values, "practicingCategoryCode");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryCodeBetween(String value1, String value2) {
            addCriterion("PRACTICING_CATEGORY_CODE between", value1, value2, "practicingCategoryCode");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryCodeNotBetween(String value1, String value2) {
            addCriterion("PRACTICING_CATEGORY_CODE not between", value1, value2, "practicingCategoryCode");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryUrlIsNull() {
            addCriterion("PRACTICING_CATEGORY_URL is null");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryUrlIsNotNull() {
            addCriterion("PRACTICING_CATEGORY_URL is not null");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryUrlEqualTo(String value) {
            addCriterion("PRACTICING_CATEGORY_URL =", value, "practicingCategoryUrl");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryUrlNotEqualTo(String value) {
            addCriterion("PRACTICING_CATEGORY_URL <>", value, "practicingCategoryUrl");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryUrlGreaterThan(String value) {
            addCriterion("PRACTICING_CATEGORY_URL >", value, "practicingCategoryUrl");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryUrlGreaterThanOrEqualTo(String value) {
            addCriterion("PRACTICING_CATEGORY_URL >=", value, "practicingCategoryUrl");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryUrlLessThan(String value) {
            addCriterion("PRACTICING_CATEGORY_URL <", value, "practicingCategoryUrl");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryUrlLessThanOrEqualTo(String value) {
            addCriterion("PRACTICING_CATEGORY_URL <=", value, "practicingCategoryUrl");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryUrlLike(String value) {
            addCriterion("PRACTICING_CATEGORY_URL like", value, "practicingCategoryUrl");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryUrlNotLike(String value) {
            addCriterion("PRACTICING_CATEGORY_URL not like", value, "practicingCategoryUrl");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryUrlIn(List<String> values) {
            addCriterion("PRACTICING_CATEGORY_URL in", values, "practicingCategoryUrl");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryUrlNotIn(List<String> values) {
            addCriterion("PRACTICING_CATEGORY_URL not in", values, "practicingCategoryUrl");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryUrlBetween(String value1, String value2) {
            addCriterion("PRACTICING_CATEGORY_URL between", value1, value2, "practicingCategoryUrl");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryUrlNotBetween(String value1, String value2) {
            addCriterion("PRACTICING_CATEGORY_URL not between", value1, value2, "practicingCategoryUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUrlIsNull() {
            addCriterion("SPECIAL_CERTIFICATION_URL is null");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUrlIsNotNull() {
            addCriterion("SPECIAL_CERTIFICATION_URL is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUrlEqualTo(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URL =", value, "specialCertificationUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUrlNotEqualTo(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URL <>", value, "specialCertificationUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUrlGreaterThan(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URL >", value, "specialCertificationUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUrlGreaterThanOrEqualTo(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URL >=", value, "specialCertificationUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUrlLessThan(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URL <", value, "specialCertificationUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUrlLessThanOrEqualTo(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URL <=", value, "specialCertificationUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUrlLike(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URL like", value, "specialCertificationUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUrlNotLike(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URL not like", value, "specialCertificationUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUrlIn(List<String> values) {
            addCriterion("SPECIAL_CERTIFICATION_URL in", values, "specialCertificationUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUrlNotIn(List<String> values) {
            addCriterion("SPECIAL_CERTIFICATION_URL not in", values, "specialCertificationUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUrlBetween(String value1, String value2) {
            addCriterion("SPECIAL_CERTIFICATION_URL between", value1, value2, "specialCertificationUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUrlNotBetween(String value1, String value2) {
            addCriterion("SPECIAL_CERTIFICATION_URL not between", value1, value2, "specialCertificationUrl");
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

        public Criteria andLocalAuditStatusIdIsNull() {
            addCriterion("LOCAL_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusIdIsNotNull() {
            addCriterion("LOCAL_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusIdEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_STATUS_ID =", value, "localAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusIdNotEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_STATUS_ID <>", value, "localAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusIdGreaterThan(String value) {
            addCriterion("LOCAL_AUDIT_STATUS_ID >", value, "localAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_STATUS_ID >=", value, "localAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusIdLessThan(String value) {
            addCriterion("LOCAL_AUDIT_STATUS_ID <", value, "localAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_STATUS_ID <=", value, "localAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusIdLike(String value) {
            addCriterion("LOCAL_AUDIT_STATUS_ID like", value, "localAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusIdNotLike(String value) {
            addCriterion("LOCAL_AUDIT_STATUS_ID not like", value, "localAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusIdIn(List<String> values) {
            addCriterion("LOCAL_AUDIT_STATUS_ID in", values, "localAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusIdNotIn(List<String> values) {
            addCriterion("LOCAL_AUDIT_STATUS_ID not in", values, "localAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusIdBetween(String value1, String value2) {
            addCriterion("LOCAL_AUDIT_STATUS_ID between", value1, value2, "localAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("LOCAL_AUDIT_STATUS_ID not between", value1, value2, "localAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusNameIsNull() {
            addCriterion("LOCAL_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusNameIsNotNull() {
            addCriterion("LOCAL_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusNameEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_STATUS_NAME =", value, "localAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusNameNotEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_STATUS_NAME <>", value, "localAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusNameGreaterThan(String value) {
            addCriterion("LOCAL_AUDIT_STATUS_NAME >", value, "localAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_STATUS_NAME >=", value, "localAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusNameLessThan(String value) {
            addCriterion("LOCAL_AUDIT_STATUS_NAME <", value, "localAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_STATUS_NAME <=", value, "localAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusNameLike(String value) {
            addCriterion("LOCAL_AUDIT_STATUS_NAME like", value, "localAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusNameNotLike(String value) {
            addCriterion("LOCAL_AUDIT_STATUS_NAME not like", value, "localAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusNameIn(List<String> values) {
            addCriterion("LOCAL_AUDIT_STATUS_NAME in", values, "localAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusNameNotIn(List<String> values) {
            addCriterion("LOCAL_AUDIT_STATUS_NAME not in", values, "localAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusNameBetween(String value1, String value2) {
            addCriterion("LOCAL_AUDIT_STATUS_NAME between", value1, value2, "localAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("LOCAL_AUDIT_STATUS_NAME not between", value1, value2, "localAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLocalReasonIsNull() {
            addCriterion("LOCAL_REASON is null");
            return (Criteria) this;
        }

        public Criteria andLocalReasonIsNotNull() {
            addCriterion("LOCAL_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andLocalReasonEqualTo(String value) {
            addCriterion("LOCAL_REASON =", value, "localReason");
            return (Criteria) this;
        }

        public Criteria andLocalReasonNotEqualTo(String value) {
            addCriterion("LOCAL_REASON <>", value, "localReason");
            return (Criteria) this;
        }

        public Criteria andLocalReasonGreaterThan(String value) {
            addCriterion("LOCAL_REASON >", value, "localReason");
            return (Criteria) this;
        }

        public Criteria andLocalReasonGreaterThanOrEqualTo(String value) {
            addCriterion("LOCAL_REASON >=", value, "localReason");
            return (Criteria) this;
        }

        public Criteria andLocalReasonLessThan(String value) {
            addCriterion("LOCAL_REASON <", value, "localReason");
            return (Criteria) this;
        }

        public Criteria andLocalReasonLessThanOrEqualTo(String value) {
            addCriterion("LOCAL_REASON <=", value, "localReason");
            return (Criteria) this;
        }

        public Criteria andLocalReasonLike(String value) {
            addCriterion("LOCAL_REASON like", value, "localReason");
            return (Criteria) this;
        }

        public Criteria andLocalReasonNotLike(String value) {
            addCriterion("LOCAL_REASON not like", value, "localReason");
            return (Criteria) this;
        }

        public Criteria andLocalReasonIn(List<String> values) {
            addCriterion("LOCAL_REASON in", values, "localReason");
            return (Criteria) this;
        }

        public Criteria andLocalReasonNotIn(List<String> values) {
            addCriterion("LOCAL_REASON not in", values, "localReason");
            return (Criteria) this;
        }

        public Criteria andLocalReasonBetween(String value1, String value2) {
            addCriterion("LOCAL_REASON between", value1, value2, "localReason");
            return (Criteria) this;
        }

        public Criteria andLocalReasonNotBetween(String value1, String value2) {
            addCriterion("LOCAL_REASON not between", value1, value2, "localReason");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusIdIsNull() {
            addCriterion("CITY_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusIdIsNotNull() {
            addCriterion("CITY_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusIdEqualTo(String value) {
            addCriterion("CITY_AUDIT_STATUS_ID =", value, "cityAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusIdNotEqualTo(String value) {
            addCriterion("CITY_AUDIT_STATUS_ID <>", value, "cityAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusIdGreaterThan(String value) {
            addCriterion("CITY_AUDIT_STATUS_ID >", value, "cityAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_AUDIT_STATUS_ID >=", value, "cityAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusIdLessThan(String value) {
            addCriterion("CITY_AUDIT_STATUS_ID <", value, "cityAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("CITY_AUDIT_STATUS_ID <=", value, "cityAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusIdLike(String value) {
            addCriterion("CITY_AUDIT_STATUS_ID like", value, "cityAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusIdNotLike(String value) {
            addCriterion("CITY_AUDIT_STATUS_ID not like", value, "cityAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusIdIn(List<String> values) {
            addCriterion("CITY_AUDIT_STATUS_ID in", values, "cityAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusIdNotIn(List<String> values) {
            addCriterion("CITY_AUDIT_STATUS_ID not in", values, "cityAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusIdBetween(String value1, String value2) {
            addCriterion("CITY_AUDIT_STATUS_ID between", value1, value2, "cityAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("CITY_AUDIT_STATUS_ID not between", value1, value2, "cityAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusNameIsNull() {
            addCriterion("CITY_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusNameIsNotNull() {
            addCriterion("CITY_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusNameEqualTo(String value) {
            addCriterion("CITY_AUDIT_STATUS_NAME =", value, "cityAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusNameNotEqualTo(String value) {
            addCriterion("CITY_AUDIT_STATUS_NAME <>", value, "cityAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusNameGreaterThan(String value) {
            addCriterion("CITY_AUDIT_STATUS_NAME >", value, "cityAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_AUDIT_STATUS_NAME >=", value, "cityAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusNameLessThan(String value) {
            addCriterion("CITY_AUDIT_STATUS_NAME <", value, "cityAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("CITY_AUDIT_STATUS_NAME <=", value, "cityAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusNameLike(String value) {
            addCriterion("CITY_AUDIT_STATUS_NAME like", value, "cityAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusNameNotLike(String value) {
            addCriterion("CITY_AUDIT_STATUS_NAME not like", value, "cityAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusNameIn(List<String> values) {
            addCriterion("CITY_AUDIT_STATUS_NAME in", values, "cityAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusNameNotIn(List<String> values) {
            addCriterion("CITY_AUDIT_STATUS_NAME not in", values, "cityAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusNameBetween(String value1, String value2) {
            addCriterion("CITY_AUDIT_STATUS_NAME between", value1, value2, "cityAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCityAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("CITY_AUDIT_STATUS_NAME not between", value1, value2, "cityAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCityReasonIsNull() {
            addCriterion("CITY_REASON is null");
            return (Criteria) this;
        }

        public Criteria andCityReasonIsNotNull() {
            addCriterion("CITY_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andCityReasonEqualTo(String value) {
            addCriterion("CITY_REASON =", value, "cityReason");
            return (Criteria) this;
        }

        public Criteria andCityReasonNotEqualTo(String value) {
            addCriterion("CITY_REASON <>", value, "cityReason");
            return (Criteria) this;
        }

        public Criteria andCityReasonGreaterThan(String value) {
            addCriterion("CITY_REASON >", value, "cityReason");
            return (Criteria) this;
        }

        public Criteria andCityReasonGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_REASON >=", value, "cityReason");
            return (Criteria) this;
        }

        public Criteria andCityReasonLessThan(String value) {
            addCriterion("CITY_REASON <", value, "cityReason");
            return (Criteria) this;
        }

        public Criteria andCityReasonLessThanOrEqualTo(String value) {
            addCriterion("CITY_REASON <=", value, "cityReason");
            return (Criteria) this;
        }

        public Criteria andCityReasonLike(String value) {
            addCriterion("CITY_REASON like", value, "cityReason");
            return (Criteria) this;
        }

        public Criteria andCityReasonNotLike(String value) {
            addCriterion("CITY_REASON not like", value, "cityReason");
            return (Criteria) this;
        }

        public Criteria andCityReasonIn(List<String> values) {
            addCriterion("CITY_REASON in", values, "cityReason");
            return (Criteria) this;
        }

        public Criteria andCityReasonNotIn(List<String> values) {
            addCriterion("CITY_REASON not in", values, "cityReason");
            return (Criteria) this;
        }

        public Criteria andCityReasonBetween(String value1, String value2) {
            addCriterion("CITY_REASON between", value1, value2, "cityReason");
            return (Criteria) this;
        }

        public Criteria andCityReasonNotBetween(String value1, String value2) {
            addCriterion("CITY_REASON not between", value1, value2, "cityReason");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusIdIsNull() {
            addCriterion("GLOBAL_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusIdIsNotNull() {
            addCriterion("GLOBAL_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusIdEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_STATUS_ID =", value, "globalAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusIdNotEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_STATUS_ID <>", value, "globalAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusIdGreaterThan(String value) {
            addCriterion("GLOBAL_AUDIT_STATUS_ID >", value, "globalAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_STATUS_ID >=", value, "globalAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusIdLessThan(String value) {
            addCriterion("GLOBAL_AUDIT_STATUS_ID <", value, "globalAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_STATUS_ID <=", value, "globalAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusIdLike(String value) {
            addCriterion("GLOBAL_AUDIT_STATUS_ID like", value, "globalAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusIdNotLike(String value) {
            addCriterion("GLOBAL_AUDIT_STATUS_ID not like", value, "globalAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusIdIn(List<String> values) {
            addCriterion("GLOBAL_AUDIT_STATUS_ID in", values, "globalAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusIdNotIn(List<String> values) {
            addCriterion("GLOBAL_AUDIT_STATUS_ID not in", values, "globalAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusIdBetween(String value1, String value2) {
            addCriterion("GLOBAL_AUDIT_STATUS_ID between", value1, value2, "globalAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("GLOBAL_AUDIT_STATUS_ID not between", value1, value2, "globalAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusNameIsNull() {
            addCriterion("GLOBAL_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusNameIsNotNull() {
            addCriterion("GLOBAL_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusNameEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_STATUS_NAME =", value, "globalAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusNameNotEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_STATUS_NAME <>", value, "globalAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusNameGreaterThan(String value) {
            addCriterion("GLOBAL_AUDIT_STATUS_NAME >", value, "globalAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_STATUS_NAME >=", value, "globalAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusNameLessThan(String value) {
            addCriterion("GLOBAL_AUDIT_STATUS_NAME <", value, "globalAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_STATUS_NAME <=", value, "globalAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusNameLike(String value) {
            addCriterion("GLOBAL_AUDIT_STATUS_NAME like", value, "globalAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusNameNotLike(String value) {
            addCriterion("GLOBAL_AUDIT_STATUS_NAME not like", value, "globalAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusNameIn(List<String> values) {
            addCriterion("GLOBAL_AUDIT_STATUS_NAME in", values, "globalAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusNameNotIn(List<String> values) {
            addCriterion("GLOBAL_AUDIT_STATUS_NAME not in", values, "globalAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusNameBetween(String value1, String value2) {
            addCriterion("GLOBAL_AUDIT_STATUS_NAME between", value1, value2, "globalAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("GLOBAL_AUDIT_STATUS_NAME not between", value1, value2, "globalAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andGlobalReasonIsNull() {
            addCriterion("GLOBAL_REASON is null");
            return (Criteria) this;
        }

        public Criteria andGlobalReasonIsNotNull() {
            addCriterion("GLOBAL_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andGlobalReasonEqualTo(String value) {
            addCriterion("GLOBAL_REASON =", value, "globalReason");
            return (Criteria) this;
        }

        public Criteria andGlobalReasonNotEqualTo(String value) {
            addCriterion("GLOBAL_REASON <>", value, "globalReason");
            return (Criteria) this;
        }

        public Criteria andGlobalReasonGreaterThan(String value) {
            addCriterion("GLOBAL_REASON >", value, "globalReason");
            return (Criteria) this;
        }

        public Criteria andGlobalReasonGreaterThanOrEqualTo(String value) {
            addCriterion("GLOBAL_REASON >=", value, "globalReason");
            return (Criteria) this;
        }

        public Criteria andGlobalReasonLessThan(String value) {
            addCriterion("GLOBAL_REASON <", value, "globalReason");
            return (Criteria) this;
        }

        public Criteria andGlobalReasonLessThanOrEqualTo(String value) {
            addCriterion("GLOBAL_REASON <=", value, "globalReason");
            return (Criteria) this;
        }

        public Criteria andGlobalReasonLike(String value) {
            addCriterion("GLOBAL_REASON like", value, "globalReason");
            return (Criteria) this;
        }

        public Criteria andGlobalReasonNotLike(String value) {
            addCriterion("GLOBAL_REASON not like", value, "globalReason");
            return (Criteria) this;
        }

        public Criteria andGlobalReasonIn(List<String> values) {
            addCriterion("GLOBAL_REASON in", values, "globalReason");
            return (Criteria) this;
        }

        public Criteria andGlobalReasonNotIn(List<String> values) {
            addCriterion("GLOBAL_REASON not in", values, "globalReason");
            return (Criteria) this;
        }

        public Criteria andGlobalReasonBetween(String value1, String value2) {
            addCriterion("GLOBAL_REASON between", value1, value2, "globalReason");
            return (Criteria) this;
        }

        public Criteria andGlobalReasonNotBetween(String value1, String value2) {
            addCriterion("GLOBAL_REASON not between", value1, value2, "globalReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonIsNull() {
            addCriterion("AUDIT_REASON is null");
            return (Criteria) this;
        }

        public Criteria andAuditReasonIsNotNull() {
            addCriterion("AUDIT_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andAuditReasonEqualTo(String value) {
            addCriterion("AUDIT_REASON =", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonNotEqualTo(String value) {
            addCriterion("AUDIT_REASON <>", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonGreaterThan(String value) {
            addCriterion("AUDIT_REASON >", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_REASON >=", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonLessThan(String value) {
            addCriterion("AUDIT_REASON <", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_REASON <=", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonLike(String value) {
            addCriterion("AUDIT_REASON like", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonNotLike(String value) {
            addCriterion("AUDIT_REASON not like", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonIn(List<String> values) {
            addCriterion("AUDIT_REASON in", values, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonNotIn(List<String> values) {
            addCriterion("AUDIT_REASON not in", values, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonBetween(String value1, String value2) {
            addCriterion("AUDIT_REASON between", value1, value2, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonNotBetween(String value1, String value2) {
            addCriterion("AUDIT_REASON not between", value1, value2, "auditReason");
            return (Criteria) this;
        }

        public Criteria andApplyYearIsNull() {
            addCriterion("APPLY_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andApplyYearIsNotNull() {
            addCriterion("APPLY_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andApplyYearEqualTo(String value) {
            addCriterion("APPLY_YEAR =", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearNotEqualTo(String value) {
            addCriterion("APPLY_YEAR <>", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearGreaterThan(String value) {
            addCriterion("APPLY_YEAR >", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_YEAR >=", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearLessThan(String value) {
            addCriterion("APPLY_YEAR <", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearLessThanOrEqualTo(String value) {
            addCriterion("APPLY_YEAR <=", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearLike(String value) {
            addCriterion("APPLY_YEAR like", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearNotLike(String value) {
            addCriterion("APPLY_YEAR not like", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearIn(List<String> values) {
            addCriterion("APPLY_YEAR in", values, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearNotIn(List<String> values) {
            addCriterion("APPLY_YEAR not in", values, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearBetween(String value1, String value2) {
            addCriterion("APPLY_YEAR between", value1, value2, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearNotBetween(String value1, String value2) {
            addCriterion("APPLY_YEAR not between", value1, value2, "applyYear");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteIsNull() {
            addCriterion("AVG_COMPLETE is null");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteIsNotNull() {
            addCriterion("AVG_COMPLETE is not null");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteEqualTo(String value) {
            addCriterion("AVG_COMPLETE =", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteNotEqualTo(String value) {
            addCriterion("AVG_COMPLETE <>", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteGreaterThan(String value) {
            addCriterion("AVG_COMPLETE >", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_COMPLETE >=", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteLessThan(String value) {
            addCriterion("AVG_COMPLETE <", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteLessThanOrEqualTo(String value) {
            addCriterion("AVG_COMPLETE <=", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteLike(String value) {
            addCriterion("AVG_COMPLETE like", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteNotLike(String value) {
            addCriterion("AVG_COMPLETE not like", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteIn(List<String> values) {
            addCriterion("AVG_COMPLETE in", values, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteNotIn(List<String> values) {
            addCriterion("AVG_COMPLETE not in", values, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBetween(String value1, String value2) {
            addCriterion("AVG_COMPLETE between", value1, value2, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteNotBetween(String value1, String value2) {
            addCriterion("AVG_COMPLETE not between", value1, value2, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgAuditIsNull() {
            addCriterion("AVG_AUDIT is null");
            return (Criteria) this;
        }

        public Criteria andAvgAuditIsNotNull() {
            addCriterion("AVG_AUDIT is not null");
            return (Criteria) this;
        }

        public Criteria andAvgAuditEqualTo(String value) {
            addCriterion("AVG_AUDIT =", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditNotEqualTo(String value) {
            addCriterion("AVG_AUDIT <>", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditGreaterThan(String value) {
            addCriterion("AVG_AUDIT >", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_AUDIT >=", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditLessThan(String value) {
            addCriterion("AVG_AUDIT <", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditLessThanOrEqualTo(String value) {
            addCriterion("AVG_AUDIT <=", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditLike(String value) {
            addCriterion("AVG_AUDIT like", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditNotLike(String value) {
            addCriterion("AVG_AUDIT not like", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditIn(List<String> values) {
            addCriterion("AVG_AUDIT in", values, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditNotIn(List<String> values) {
            addCriterion("AVG_AUDIT not in", values, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditBetween(String value1, String value2) {
            addCriterion("AVG_AUDIT between", value1, value2, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditNotBetween(String value1, String value2) {
            addCriterion("AVG_AUDIT not between", value1, value2, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteIsNull() {
            addCriterion("AVG_IN_COMPLETE is null");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteIsNotNull() {
            addCriterion("AVG_IN_COMPLETE is not null");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteEqualTo(String value) {
            addCriterion("AVG_IN_COMPLETE =", value, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteNotEqualTo(String value) {
            addCriterion("AVG_IN_COMPLETE <>", value, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteGreaterThan(String value) {
            addCriterion("AVG_IN_COMPLETE >", value, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_IN_COMPLETE >=", value, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteLessThan(String value) {
            addCriterion("AVG_IN_COMPLETE <", value, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteLessThanOrEqualTo(String value) {
            addCriterion("AVG_IN_COMPLETE <=", value, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteLike(String value) {
            addCriterion("AVG_IN_COMPLETE like", value, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteNotLike(String value) {
            addCriterion("AVG_IN_COMPLETE not like", value, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteIn(List<String> values) {
            addCriterion("AVG_IN_COMPLETE in", values, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteNotIn(List<String> values) {
            addCriterion("AVG_IN_COMPLETE not in", values, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBetween(String value1, String value2) {
            addCriterion("AVG_IN_COMPLETE between", value1, value2, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteNotBetween(String value1, String value2) {
            addCriterion("AVG_IN_COMPLETE not between", value1, value2, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteIsNull() {
            addCriterion("AVG_OUT_COMPLETE is null");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteIsNotNull() {
            addCriterion("AVG_OUT_COMPLETE is not null");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteEqualTo(String value) {
            addCriterion("AVG_OUT_COMPLETE =", value, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteNotEqualTo(String value) {
            addCriterion("AVG_OUT_COMPLETE <>", value, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteGreaterThan(String value) {
            addCriterion("AVG_OUT_COMPLETE >", value, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_OUT_COMPLETE >=", value, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteLessThan(String value) {
            addCriterion("AVG_OUT_COMPLETE <", value, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteLessThanOrEqualTo(String value) {
            addCriterion("AVG_OUT_COMPLETE <=", value, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteLike(String value) {
            addCriterion("AVG_OUT_COMPLETE like", value, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteNotLike(String value) {
            addCriterion("AVG_OUT_COMPLETE not like", value, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteIn(List<String> values) {
            addCriterion("AVG_OUT_COMPLETE in", values, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteNotIn(List<String> values) {
            addCriterion("AVG_OUT_COMPLETE not in", values, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBetween(String value1, String value2) {
            addCriterion("AVG_OUT_COMPLETE between", value1, value2, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteNotBetween(String value1, String value2) {
            addCriterion("AVG_OUT_COMPLETE not between", value1, value2, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andSchMonthIsNull() {
            addCriterion("SCH_MONTH is null");
            return (Criteria) this;
        }

        public Criteria andSchMonthIsNotNull() {
            addCriterion("SCH_MONTH is not null");
            return (Criteria) this;
        }

        public Criteria andSchMonthEqualTo(String value) {
            addCriterion("SCH_MONTH =", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthNotEqualTo(String value) {
            addCriterion("SCH_MONTH <>", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthGreaterThan(String value) {
            addCriterion("SCH_MONTH >", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_MONTH >=", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthLessThan(String value) {
            addCriterion("SCH_MONTH <", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthLessThanOrEqualTo(String value) {
            addCriterion("SCH_MONTH <=", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthLike(String value) {
            addCriterion("SCH_MONTH like", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthNotLike(String value) {
            addCriterion("SCH_MONTH not like", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthIn(List<String> values) {
            addCriterion("SCH_MONTH in", values, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthNotIn(List<String> values) {
            addCriterion("SCH_MONTH not in", values, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthBetween(String value1, String value2) {
            addCriterion("SCH_MONTH between", value1, value2, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthNotBetween(String value1, String value2) {
            addCriterion("SCH_MONTH not between", value1, value2, "schMonth");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlIsNull() {
            addCriterion("PROVE_FILE_URL is null");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlIsNotNull() {
            addCriterion("PROVE_FILE_URL is not null");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlEqualTo(String value) {
            addCriterion("PROVE_FILE_URL =", value, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlNotEqualTo(String value) {
            addCriterion("PROVE_FILE_URL <>", value, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlGreaterThan(String value) {
            addCriterion("PROVE_FILE_URL >", value, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlGreaterThanOrEqualTo(String value) {
            addCriterion("PROVE_FILE_URL >=", value, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlLessThan(String value) {
            addCriterion("PROVE_FILE_URL <", value, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlLessThanOrEqualTo(String value) {
            addCriterion("PROVE_FILE_URL <=", value, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlLike(String value) {
            addCriterion("PROVE_FILE_URL like", value, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlNotLike(String value) {
            addCriterion("PROVE_FILE_URL not like", value, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlIn(List<String> values) {
            addCriterion("PROVE_FILE_URL in", values, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlNotIn(List<String> values) {
            addCriterion("PROVE_FILE_URL not in", values, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlBetween(String value1, String value2) {
            addCriterion("PROVE_FILE_URL between", value1, value2, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlNotBetween(String value1, String value2) {
            addCriterion("PROVE_FILE_URL not between", value1, value2, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdIsNull() {
            addCriterion("DOCTOR_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdIsNotNull() {
            addCriterion("DOCTOR_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID =", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID <>", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdGreaterThan(String value) {
            addCriterion("DOCTOR_TYPE_ID >", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID >=", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdLessThan(String value) {
            addCriterion("DOCTOR_TYPE_ID <", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID <=", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdLike(String value) {
            addCriterion("DOCTOR_TYPE_ID like", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotLike(String value) {
            addCriterion("DOCTOR_TYPE_ID not like", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdIn(List<String> values) {
            addCriterion("DOCTOR_TYPE_ID in", values, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotIn(List<String> values) {
            addCriterion("DOCTOR_TYPE_ID not in", values, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdBetween(String value1, String value2) {
            addCriterion("DOCTOR_TYPE_ID between", value1, value2, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_TYPE_ID not between", value1, value2, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdIsNull() {
            addCriterion("TRAINING_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdIsNotNull() {
            addCriterion("TRAINING_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdEqualTo(String value) {
            addCriterion("TRAINING_TYPE_ID =", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdNotEqualTo(String value) {
            addCriterion("TRAINING_TYPE_ID <>", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdGreaterThan(String value) {
            addCriterion("TRAINING_TYPE_ID >", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_TYPE_ID >=", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdLessThan(String value) {
            addCriterion("TRAINING_TYPE_ID <", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_TYPE_ID <=", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdLike(String value) {
            addCriterion("TRAINING_TYPE_ID like", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdNotLike(String value) {
            addCriterion("TRAINING_TYPE_ID not like", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdIn(List<String> values) {
            addCriterion("TRAINING_TYPE_ID in", values, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdNotIn(List<String> values) {
            addCriterion("TRAINING_TYPE_ID not in", values, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdBetween(String value1, String value2) {
            addCriterion("TRAINING_TYPE_ID between", value1, value2, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdNotBetween(String value1, String value2) {
            addCriterion("TRAINING_TYPE_ID not between", value1, value2, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andGraduationYearIsNull() {
            addCriterion("GRADUATION_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andGraduationYearIsNotNull() {
            addCriterion("GRADUATION_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationYearEqualTo(String value) {
            addCriterion("GRADUATION_YEAR =", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotEqualTo(String value) {
            addCriterion("GRADUATION_YEAR <>", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearGreaterThan(String value) {
            addCriterion("GRADUATION_YEAR >", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_YEAR >=", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearLessThan(String value) {
            addCriterion("GRADUATION_YEAR <", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_YEAR <=", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearLike(String value) {
            addCriterion("GRADUATION_YEAR like", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotLike(String value) {
            addCriterion("GRADUATION_YEAR not like", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearIn(List<String> values) {
            addCriterion("GRADUATION_YEAR in", values, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotIn(List<String> values) {
            addCriterion("GRADUATION_YEAR not in", values, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearBetween(String value1, String value2) {
            addCriterion("GRADUATION_YEAR between", value1, value2, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_YEAR not between", value1, value2, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdIsNull() {
            addCriterion("ORG_PROV_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdIsNotNull() {
            addCriterion("ORG_PROV_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdEqualTo(String value) {
            addCriterion("ORG_PROV_ID =", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdNotEqualTo(String value) {
            addCriterion("ORG_PROV_ID <>", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdGreaterThan(String value) {
            addCriterion("ORG_PROV_ID >", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_PROV_ID >=", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdLessThan(String value) {
            addCriterion("ORG_PROV_ID <", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_PROV_ID <=", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdLike(String value) {
            addCriterion("ORG_PROV_ID like", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdNotLike(String value) {
            addCriterion("ORG_PROV_ID not like", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdIn(List<String> values) {
            addCriterion("ORG_PROV_ID in", values, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdNotIn(List<String> values) {
            addCriterion("ORG_PROV_ID not in", values, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdBetween(String value1, String value2) {
            addCriterion("ORG_PROV_ID between", value1, value2, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdNotBetween(String value1, String value2) {
            addCriterion("ORG_PROV_ID not between", value1, value2, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdIsNull() {
            addCriterion("ORG_LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdIsNotNull() {
            addCriterion("ORG_LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdEqualTo(String value) {
            addCriterion("ORG_LEVEL_ID =", value, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdNotEqualTo(String value) {
            addCriterion("ORG_LEVEL_ID <>", value, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdGreaterThan(String value) {
            addCriterion("ORG_LEVEL_ID >", value, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_LEVEL_ID >=", value, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdLessThan(String value) {
            addCriterion("ORG_LEVEL_ID <", value, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_LEVEL_ID <=", value, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdLike(String value) {
            addCriterion("ORG_LEVEL_ID like", value, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdNotLike(String value) {
            addCriterion("ORG_LEVEL_ID not like", value, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdIn(List<String> values) {
            addCriterion("ORG_LEVEL_ID in", values, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdNotIn(List<String> values) {
            addCriterion("ORG_LEVEL_ID not in", values, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdBetween(String value1, String value2) {
            addCriterion("ORG_LEVEL_ID between", value1, value2, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdNotBetween(String value1, String value2) {
            addCriterion("ORG_LEVEL_ID not between", value1, value2, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdIsNull() {
            addCriterion("ORG_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdIsNotNull() {
            addCriterion("ORG_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdEqualTo(String value) {
            addCriterion("ORG_TYPE_ID =", value, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdNotEqualTo(String value) {
            addCriterion("ORG_TYPE_ID <>", value, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdGreaterThan(String value) {
            addCriterion("ORG_TYPE_ID >", value, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_TYPE_ID >=", value, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdLessThan(String value) {
            addCriterion("ORG_TYPE_ID <", value, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_TYPE_ID <=", value, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdLike(String value) {
            addCriterion("ORG_TYPE_ID like", value, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdNotLike(String value) {
            addCriterion("ORG_TYPE_ID not like", value, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdIn(List<String> values) {
            addCriterion("ORG_TYPE_ID in", values, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdNotIn(List<String> values) {
            addCriterion("ORG_TYPE_ID not in", values, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdBetween(String value1, String value2) {
            addCriterion("ORG_TYPE_ID between", value1, value2, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdNotBetween(String value1, String value2) {
            addCriterion("ORG_TYPE_ID not between", value1, value2, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andGraduationCategoryIdIsNull() {
            addCriterion("GRADUATION_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andGraduationCategoryIdIsNotNull() {
            addCriterion("GRADUATION_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationCategoryIdEqualTo(String value) {
            addCriterion("GRADUATION_CATEGORY_ID =", value, "graduationCategoryId");
            return (Criteria) this;
        }

        public Criteria andGraduationCategoryIdNotEqualTo(String value) {
            addCriterion("GRADUATION_CATEGORY_ID <>", value, "graduationCategoryId");
            return (Criteria) this;
        }

        public Criteria andGraduationCategoryIdGreaterThan(String value) {
            addCriterion("GRADUATION_CATEGORY_ID >", value, "graduationCategoryId");
            return (Criteria) this;
        }

        public Criteria andGraduationCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_CATEGORY_ID >=", value, "graduationCategoryId");
            return (Criteria) this;
        }

        public Criteria andGraduationCategoryIdLessThan(String value) {
            addCriterion("GRADUATION_CATEGORY_ID <", value, "graduationCategoryId");
            return (Criteria) this;
        }

        public Criteria andGraduationCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_CATEGORY_ID <=", value, "graduationCategoryId");
            return (Criteria) this;
        }

        public Criteria andGraduationCategoryIdLike(String value) {
            addCriterion("GRADUATION_CATEGORY_ID like", value, "graduationCategoryId");
            return (Criteria) this;
        }

        public Criteria andGraduationCategoryIdNotLike(String value) {
            addCriterion("GRADUATION_CATEGORY_ID not like", value, "graduationCategoryId");
            return (Criteria) this;
        }

        public Criteria andGraduationCategoryIdIn(List<String> values) {
            addCriterion("GRADUATION_CATEGORY_ID in", values, "graduationCategoryId");
            return (Criteria) this;
        }

        public Criteria andGraduationCategoryIdNotIn(List<String> values) {
            addCriterion("GRADUATION_CATEGORY_ID not in", values, "graduationCategoryId");
            return (Criteria) this;
        }

        public Criteria andGraduationCategoryIdBetween(String value1, String value2) {
            addCriterion("GRADUATION_CATEGORY_ID between", value1, value2, "graduationCategoryId");
            return (Criteria) this;
        }

        public Criteria andGraduationCategoryIdNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_CATEGORY_ID not between", value1, value2, "graduationCategoryId");
            return (Criteria) this;
        }

        public Criteria andGraduationScopeIdIsNull() {
            addCriterion("GRADUATION_SCOPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andGraduationScopeIdIsNotNull() {
            addCriterion("GRADUATION_SCOPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationScopeIdEqualTo(String value) {
            addCriterion("GRADUATION_SCOPE_ID =", value, "graduationScopeId");
            return (Criteria) this;
        }

        public Criteria andGraduationScopeIdNotEqualTo(String value) {
            addCriterion("GRADUATION_SCOPE_ID <>", value, "graduationScopeId");
            return (Criteria) this;
        }

        public Criteria andGraduationScopeIdGreaterThan(String value) {
            addCriterion("GRADUATION_SCOPE_ID >", value, "graduationScopeId");
            return (Criteria) this;
        }

        public Criteria andGraduationScopeIdGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_SCOPE_ID >=", value, "graduationScopeId");
            return (Criteria) this;
        }

        public Criteria andGraduationScopeIdLessThan(String value) {
            addCriterion("GRADUATION_SCOPE_ID <", value, "graduationScopeId");
            return (Criteria) this;
        }

        public Criteria andGraduationScopeIdLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_SCOPE_ID <=", value, "graduationScopeId");
            return (Criteria) this;
        }

        public Criteria andGraduationScopeIdLike(String value) {
            addCriterion("GRADUATION_SCOPE_ID like", value, "graduationScopeId");
            return (Criteria) this;
        }

        public Criteria andGraduationScopeIdNotLike(String value) {
            addCriterion("GRADUATION_SCOPE_ID not like", value, "graduationScopeId");
            return (Criteria) this;
        }

        public Criteria andGraduationScopeIdIn(List<String> values) {
            addCriterion("GRADUATION_SCOPE_ID in", values, "graduationScopeId");
            return (Criteria) this;
        }

        public Criteria andGraduationScopeIdNotIn(List<String> values) {
            addCriterion("GRADUATION_SCOPE_ID not in", values, "graduationScopeId");
            return (Criteria) this;
        }

        public Criteria andGraduationScopeIdBetween(String value1, String value2) {
            addCriterion("GRADUATION_SCOPE_ID between", value1, value2, "graduationScopeId");
            return (Criteria) this;
        }

        public Criteria andGraduationScopeIdNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_SCOPE_ID not between", value1, value2, "graduationScopeId");
            return (Criteria) this;
        }

        public Criteria andSkillScoreIsNull() {
            addCriterion("SKILL_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andSkillScoreIsNotNull() {
            addCriterion("SKILL_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andSkillScoreEqualTo(String value) {
            addCriterion("SKILL_SCORE =", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreNotEqualTo(String value) {
            addCriterion("SKILL_SCORE <>", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreGreaterThan(String value) {
            addCriterion("SKILL_SCORE >", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreGreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_SCORE >=", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreLessThan(String value) {
            addCriterion("SKILL_SCORE <", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreLessThanOrEqualTo(String value) {
            addCriterion("SKILL_SCORE <=", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreLike(String value) {
            addCriterion("SKILL_SCORE like", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreNotLike(String value) {
            addCriterion("SKILL_SCORE not like", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreIn(List<String> values) {
            addCriterion("SKILL_SCORE in", values, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreNotIn(List<String> values) {
            addCriterion("SKILL_SCORE not in", values, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreBetween(String value1, String value2) {
            addCriterion("SKILL_SCORE between", value1, value2, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreNotBetween(String value1, String value2) {
            addCriterion("SKILL_SCORE not between", value1, value2, "skillScore");
            return (Criteria) this;
        }

        public Criteria andIdNoIsNull() {
            addCriterion("ID_NO is null");
            return (Criteria) this;
        }

        public Criteria andIdNoIsNotNull() {
            addCriterion("ID_NO is not null");
            return (Criteria) this;
        }

        public Criteria andIdNoEqualTo(String value) {
            addCriterion("ID_NO =", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotEqualTo(String value) {
            addCriterion("ID_NO <>", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThan(String value) {
            addCriterion("ID_NO >", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThanOrEqualTo(String value) {
            addCriterion("ID_NO >=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThan(String value) {
            addCriterion("ID_NO <", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThanOrEqualTo(String value) {
            addCriterion("ID_NO <=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLike(String value) {
            addCriterion("ID_NO like", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotLike(String value) {
            addCriterion("ID_NO not like", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoIn(List<String> values) {
            addCriterion("ID_NO in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotIn(List<String> values) {
            addCriterion("ID_NO not in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoBetween(String value1, String value2) {
            addCriterion("ID_NO between", value1, value2, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotBetween(String value1, String value2) {
            addCriterion("ID_NO not between", value1, value2, "idNo");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdIsNull() {
            addCriterion("CAT_SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdIsNotNull() {
            addCriterion("CAT_SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdEqualTo(String value) {
            addCriterion("CAT_SPE_ID =", value, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdNotEqualTo(String value) {
            addCriterion("CAT_SPE_ID <>", value, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdGreaterThan(String value) {
            addCriterion("CAT_SPE_ID >", value, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("CAT_SPE_ID >=", value, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdLessThan(String value) {
            addCriterion("CAT_SPE_ID <", value, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdLessThanOrEqualTo(String value) {
            addCriterion("CAT_SPE_ID <=", value, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdLike(String value) {
            addCriterion("CAT_SPE_ID like", value, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdNotLike(String value) {
            addCriterion("CAT_SPE_ID not like", value, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdIn(List<String> values) {
            addCriterion("CAT_SPE_ID in", values, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdNotIn(List<String> values) {
            addCriterion("CAT_SPE_ID not in", values, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdBetween(String value1, String value2) {
            addCriterion("CAT_SPE_ID between", value1, value2, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdNotBetween(String value1, String value2) {
            addCriterion("CAT_SPE_ID not between", value1, value2, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameIsNull() {
            addCriterion("CAT_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameIsNotNull() {
            addCriterion("CAT_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameEqualTo(String value) {
            addCriterion("CAT_SPE_NAME =", value, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameNotEqualTo(String value) {
            addCriterion("CAT_SPE_NAME <>", value, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameGreaterThan(String value) {
            addCriterion("CAT_SPE_NAME >", value, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("CAT_SPE_NAME >=", value, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameLessThan(String value) {
            addCriterion("CAT_SPE_NAME <", value, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameLessThanOrEqualTo(String value) {
            addCriterion("CAT_SPE_NAME <=", value, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameLike(String value) {
            addCriterion("CAT_SPE_NAME like", value, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameNotLike(String value) {
            addCriterion("CAT_SPE_NAME not like", value, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameIn(List<String> values) {
            addCriterion("CAT_SPE_NAME in", values, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameNotIn(List<String> values) {
            addCriterion("CAT_SPE_NAME not in", values, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameBetween(String value1, String value2) {
            addCriterion("CAT_SPE_NAME between", value1, value2, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameNotBetween(String value1, String value2) {
            addCriterion("CAT_SPE_NAME not between", value1, value2, "catSpeName");
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

        public Criteria andMidifyUserFlowIsNull() {
            addCriterion("MIDIFY_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowIsNotNull() {
            addCriterion("MIDIFY_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowEqualTo(String value) {
            addCriterion("MIDIFY_USER_FLOW =", value, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowNotEqualTo(String value) {
            addCriterion("MIDIFY_USER_FLOW <>", value, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowGreaterThan(String value) {
            addCriterion("MIDIFY_USER_FLOW >", value, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("MIDIFY_USER_FLOW >=", value, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowLessThan(String value) {
            addCriterion("MIDIFY_USER_FLOW <", value, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowLessThanOrEqualTo(String value) {
            addCriterion("MIDIFY_USER_FLOW <=", value, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowLike(String value) {
            addCriterion("MIDIFY_USER_FLOW like", value, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowNotLike(String value) {
            addCriterion("MIDIFY_USER_FLOW not like", value, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowIn(List<String> values) {
            addCriterion("MIDIFY_USER_FLOW in", values, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowNotIn(List<String> values) {
            addCriterion("MIDIFY_USER_FLOW not in", values, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowBetween(String value1, String value2) {
            addCriterion("MIDIFY_USER_FLOW between", value1, value2, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowNotBetween(String value1, String value2) {
            addCriterion("MIDIFY_USER_FLOW not between", value1, value2, "midifyUserFlow");
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