package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class JsresGraduationApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JsresGraduationApplyExample() {
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

        public Criteria andRotationFlowIsNull() {
            addCriterion("ROTATION_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRotationFlowIsNotNull() {
            addCriterion("ROTATION_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRotationFlowEqualTo(String value) {
            addCriterion("ROTATION_FLOW =", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotEqualTo(String value) {
            addCriterion("ROTATION_FLOW <>", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowGreaterThan(String value) {
            addCriterion("ROTATION_FLOW >", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ROTATION_FLOW >=", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowLessThan(String value) {
            addCriterion("ROTATION_FLOW <", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowLessThanOrEqualTo(String value) {
            addCriterion("ROTATION_FLOW <=", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowLike(String value) {
            addCriterion("ROTATION_FLOW like", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotLike(String value) {
            addCriterion("ROTATION_FLOW not like", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowIn(List<String> values) {
            addCriterion("ROTATION_FLOW in", values, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotIn(List<String> values) {
            addCriterion("ROTATION_FLOW not in", values, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowBetween(String value1, String value2) {
            addCriterion("ROTATION_FLOW between", value1, value2, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotBetween(String value1, String value2) {
            addCriterion("ROTATION_FLOW not between", value1, value2, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationNameIsNull() {
            addCriterion("ROTATION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRotationNameIsNotNull() {
            addCriterion("ROTATION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRotationNameEqualTo(String value) {
            addCriterion("ROTATION_NAME =", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameNotEqualTo(String value) {
            addCriterion("ROTATION_NAME <>", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameGreaterThan(String value) {
            addCriterion("ROTATION_NAME >", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameGreaterThanOrEqualTo(String value) {
            addCriterion("ROTATION_NAME >=", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameLessThan(String value) {
            addCriterion("ROTATION_NAME <", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameLessThanOrEqualTo(String value) {
            addCriterion("ROTATION_NAME <=", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameLike(String value) {
            addCriterion("ROTATION_NAME like", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameNotLike(String value) {
            addCriterion("ROTATION_NAME not like", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameIn(List<String> values) {
            addCriterion("ROTATION_NAME in", values, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameNotIn(List<String> values) {
            addCriterion("ROTATION_NAME not in", values, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameBetween(String value1, String value2) {
            addCriterion("ROTATION_NAME between", value1, value2, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameNotBetween(String value1, String value2) {
            addCriterion("ROTATION_NAME not between", value1, value2, "rotationName");
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

        public Criteria andMaterialIdIsNull() {
            addCriterion("MATERIAL_ID is null");
            return (Criteria) this;
        }

        public Criteria andMaterialIdIsNotNull() {
            addCriterion("MATERIAL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialIdEqualTo(String value) {
            addCriterion("MATERIAL_ID =", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotEqualTo(String value) {
            addCriterion("MATERIAL_ID <>", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdGreaterThan(String value) {
            addCriterion("MATERIAL_ID >", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdGreaterThanOrEqualTo(String value) {
            addCriterion("MATERIAL_ID >=", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdLessThan(String value) {
            addCriterion("MATERIAL_ID <", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdLessThanOrEqualTo(String value) {
            addCriterion("MATERIAL_ID <=", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdLike(String value) {
            addCriterion("MATERIAL_ID like", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotLike(String value) {
            addCriterion("MATERIAL_ID not like", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdIn(List<String> values) {
            addCriterion("MATERIAL_ID in", values, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotIn(List<String> values) {
            addCriterion("MATERIAL_ID not in", values, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdBetween(String value1, String value2) {
            addCriterion("MATERIAL_ID between", value1, value2, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotBetween(String value1, String value2) {
            addCriterion("MATERIAL_ID not between", value1, value2, "materialId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdIsNull() {
            addCriterion("PRACTICING_SCOPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdIsNotNull() {
            addCriterion("PRACTICING_SCOPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_ID =", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdNotEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_ID <>", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdGreaterThan(String value) {
            addCriterion("PRACTICING_SCOPE_ID >", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdGreaterThanOrEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_ID >=", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdLessThan(String value) {
            addCriterion("PRACTICING_SCOPE_ID <", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdLessThanOrEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_ID <=", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdLike(String value) {
            addCriterion("PRACTICING_SCOPE_ID like", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdNotLike(String value) {
            addCriterion("PRACTICING_SCOPE_ID not like", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdIn(List<String> values) {
            addCriterion("PRACTICING_SCOPE_ID in", values, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdNotIn(List<String> values) {
            addCriterion("PRACTICING_SCOPE_ID not in", values, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdBetween(String value1, String value2) {
            addCriterion("PRACTICING_SCOPE_ID between", value1, value2, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdNotBetween(String value1, String value2) {
            addCriterion("PRACTICING_SCOPE_ID not between", value1, value2, "practicingScopeId");
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

        public Criteria andCertificateUriIsNull() {
            addCriterion("CERTIFICATE_URI is null");
            return (Criteria) this;
        }

        public Criteria andCertificateUriIsNotNull() {
            addCriterion("CERTIFICATE_URI is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateUriEqualTo(String value) {
            addCriterion("CERTIFICATE_URI =", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriNotEqualTo(String value) {
            addCriterion("CERTIFICATE_URI <>", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriGreaterThan(String value) {
            addCriterion("CERTIFICATE_URI >", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_URI >=", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriLessThan(String value) {
            addCriterion("CERTIFICATE_URI <", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_URI <=", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriLike(String value) {
            addCriterion("CERTIFICATE_URI like", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriNotLike(String value) {
            addCriterion("CERTIFICATE_URI not like", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriIn(List<String> values) {
            addCriterion("CERTIFICATE_URI in", values, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriNotIn(List<String> values) {
            addCriterion("CERTIFICATE_URI not in", values, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_URI between", value1, value2, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_URI not between", value1, value2, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriIsNull() {
            addCriterion("QUALIFICATION_MATERIAL_URI is null");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriIsNotNull() {
            addCriterion("QUALIFICATION_MATERIAL_URI is not null");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI =", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriNotEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI <>", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriGreaterThan(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI >", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriGreaterThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI >=", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriLessThan(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI <", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriLessThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI <=", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriLike(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI like", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriNotLike(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI not like", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriIn(List<String> values) {
            addCriterion("QUALIFICATION_MATERIAL_URI in", values, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriNotIn(List<String> values) {
            addCriterion("QUALIFICATION_MATERIAL_URI not in", values, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_MATERIAL_URI between", value1, value2, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriNotBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_MATERIAL_URI not between", value1, value2, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriIsNull() {
            addCriterion("SPECIAL_CERTIFICATION_URI is null");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriIsNotNull() {
            addCriterion("SPECIAL_CERTIFICATION_URI is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriEqualTo(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI =", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriNotEqualTo(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI <>", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriGreaterThan(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI >", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriGreaterThanOrEqualTo(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI >=", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriLessThan(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI <", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriLessThanOrEqualTo(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI <=", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriLike(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI like", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriNotLike(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI not like", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriIn(List<String> values) {
            addCriterion("SPECIAL_CERTIFICATION_URI in", values, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriNotIn(List<String> values) {
            addCriterion("SPECIAL_CERTIFICATION_URI not in", values, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriBetween(String value1, String value2) {
            addCriterion("SPECIAL_CERTIFICATION_URI between", value1, value2, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriNotBetween(String value1, String value2) {
            addCriterion("SPECIAL_CERTIFICATION_URI not between", value1, value2, "specialCertificationUri");
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

        public Criteria andTypeIdIsNull() {
            addCriterion("TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(String value) {
            addCriterion("TYPE_ID =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(String value) {
            addCriterion("TYPE_ID <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(String value) {
            addCriterion("TYPE_ID >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_ID >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(String value) {
            addCriterion("TYPE_ID <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(String value) {
            addCriterion("TYPE_ID <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLike(String value) {
            addCriterion("TYPE_ID like", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotLike(String value) {
            addCriterion("TYPE_ID not like", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<String> values) {
            addCriterion("TYPE_ID in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<String> values) {
            addCriterion("TYPE_ID not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(String value1, String value2) {
            addCriterion("TYPE_ID between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(String value1, String value2) {
            addCriterion("TYPE_ID not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaIsNull() {
            addCriterion("REGISTE_MANUA is null");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaIsNotNull() {
            addCriterion("REGISTE_MANUA is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaEqualTo(String value) {
            addCriterion("REGISTE_MANUA =", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaNotEqualTo(String value) {
            addCriterion("REGISTE_MANUA <>", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaGreaterThan(String value) {
            addCriterion("REGISTE_MANUA >", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaGreaterThanOrEqualTo(String value) {
            addCriterion("REGISTE_MANUA >=", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaLessThan(String value) {
            addCriterion("REGISTE_MANUA <", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaLessThanOrEqualTo(String value) {
            addCriterion("REGISTE_MANUA <=", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaLike(String value) {
            addCriterion("REGISTE_MANUA like", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaNotLike(String value) {
            addCriterion("REGISTE_MANUA not like", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaIn(List<String> values) {
            addCriterion("REGISTE_MANUA in", values, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaNotIn(List<String> values) {
            addCriterion("REGISTE_MANUA not in", values, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaBetween(String value1, String value2) {
            addCriterion("REGISTE_MANUA between", value1, value2, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaNotBetween(String value1, String value2) {
            addCriterion("REGISTE_MANUA not between", value1, value2, "registeManua");
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

        public Criteria andAvgCompleteBiPerIsNull() {
            addCriterion("AVG_COMPLETE_BI_PER is null");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerIsNotNull() {
            addCriterion("AVG_COMPLETE_BI_PER is not null");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerEqualTo(String value) {
            addCriterion("AVG_COMPLETE_BI_PER =", value, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerNotEqualTo(String value) {
            addCriterion("AVG_COMPLETE_BI_PER <>", value, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerGreaterThan(String value) {
            addCriterion("AVG_COMPLETE_BI_PER >", value, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_COMPLETE_BI_PER >=", value, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerLessThan(String value) {
            addCriterion("AVG_COMPLETE_BI_PER <", value, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerLessThanOrEqualTo(String value) {
            addCriterion("AVG_COMPLETE_BI_PER <=", value, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerLike(String value) {
            addCriterion("AVG_COMPLETE_BI_PER like", value, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerNotLike(String value) {
            addCriterion("AVG_COMPLETE_BI_PER not like", value, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerIn(List<String> values) {
            addCriterion("AVG_COMPLETE_BI_PER in", values, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerNotIn(List<String> values) {
            addCriterion("AVG_COMPLETE_BI_PER not in", values, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerBetween(String value1, String value2) {
            addCriterion("AVG_COMPLETE_BI_PER between", value1, value2, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerNotBetween(String value1, String value2) {
            addCriterion("AVG_COMPLETE_BI_PER not between", value1, value2, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerIsNull() {
            addCriterion("AVG_OUT_COMPLETE_BI_PER is null");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerIsNotNull() {
            addCriterion("AVG_OUT_COMPLETE_BI_PER is not null");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerEqualTo(String value) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER =", value, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerNotEqualTo(String value) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER <>", value, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerGreaterThan(String value) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER >", value, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER >=", value, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerLessThan(String value) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER <", value, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerLessThanOrEqualTo(String value) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER <=", value, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerLike(String value) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER like", value, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerNotLike(String value) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER not like", value, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerIn(List<String> values) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER in", values, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerNotIn(List<String> values) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER not in", values, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerBetween(String value1, String value2) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER between", value1, value2, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerNotBetween(String value1, String value2) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER not between", value1, value2, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerIsNull() {
            addCriterion("AVG_IN_COMPLETE_BI_PER is null");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerIsNotNull() {
            addCriterion("AVG_IN_COMPLETE_BI_PER is not null");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerEqualTo(String value) {
            addCriterion("AVG_IN_COMPLETE_BI_PER =", value, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerNotEqualTo(String value) {
            addCriterion("AVG_IN_COMPLETE_BI_PER <>", value, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerGreaterThan(String value) {
            addCriterion("AVG_IN_COMPLETE_BI_PER >", value, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_IN_COMPLETE_BI_PER >=", value, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerLessThan(String value) {
            addCriterion("AVG_IN_COMPLETE_BI_PER <", value, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerLessThanOrEqualTo(String value) {
            addCriterion("AVG_IN_COMPLETE_BI_PER <=", value, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerLike(String value) {
            addCriterion("AVG_IN_COMPLETE_BI_PER like", value, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerNotLike(String value) {
            addCriterion("AVG_IN_COMPLETE_BI_PER not like", value, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerIn(List<String> values) {
            addCriterion("AVG_IN_COMPLETE_BI_PER in", values, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerNotIn(List<String> values) {
            addCriterion("AVG_IN_COMPLETE_BI_PER not in", values, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerBetween(String value1, String value2) {
            addCriterion("AVG_IN_COMPLETE_BI_PER between", value1, value2, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerNotBetween(String value1, String value2) {
            addCriterion("AVG_IN_COMPLETE_BI_PER not between", value1, value2, "avgInCompleteBiPer");
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