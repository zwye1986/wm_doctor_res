package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ResTestConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResTestConfigExample() {
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

        public Criteria andTestFlowIsNull() {
            addCriterion("TEST_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTestFlowIsNotNull() {
            addCriterion("TEST_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTestFlowEqualTo(String value) {
            addCriterion("TEST_FLOW =", value, "testFlow");
            return (Criteria) this;
        }

        public Criteria andTestFlowNotEqualTo(String value) {
            addCriterion("TEST_FLOW <>", value, "testFlow");
            return (Criteria) this;
        }

        public Criteria andTestFlowGreaterThan(String value) {
            addCriterion("TEST_FLOW >", value, "testFlow");
            return (Criteria) this;
        }

        public Criteria andTestFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_FLOW >=", value, "testFlow");
            return (Criteria) this;
        }

        public Criteria andTestFlowLessThan(String value) {
            addCriterion("TEST_FLOW <", value, "testFlow");
            return (Criteria) this;
        }

        public Criteria andTestFlowLessThanOrEqualTo(String value) {
            addCriterion("TEST_FLOW <=", value, "testFlow");
            return (Criteria) this;
        }

        public Criteria andTestFlowLike(String value) {
            addCriterion("TEST_FLOW like", value, "testFlow");
            return (Criteria) this;
        }

        public Criteria andTestFlowNotLike(String value) {
            addCriterion("TEST_FLOW not like", value, "testFlow");
            return (Criteria) this;
        }

        public Criteria andTestFlowIn(List<String> values) {
            addCriterion("TEST_FLOW in", values, "testFlow");
            return (Criteria) this;
        }

        public Criteria andTestFlowNotIn(List<String> values) {
            addCriterion("TEST_FLOW not in", values, "testFlow");
            return (Criteria) this;
        }

        public Criteria andTestFlowBetween(String value1, String value2) {
            addCriterion("TEST_FLOW between", value1, value2, "testFlow");
            return (Criteria) this;
        }

        public Criteria andTestFlowNotBetween(String value1, String value2) {
            addCriterion("TEST_FLOW not between", value1, value2, "testFlow");
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

        public Criteria andTestNameIsNull() {
            addCriterion("TEST_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTestNameIsNotNull() {
            addCriterion("TEST_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTestNameEqualTo(String value) {
            addCriterion("TEST_NAME =", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotEqualTo(String value) {
            addCriterion("TEST_NAME <>", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameGreaterThan(String value) {
            addCriterion("TEST_NAME >", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_NAME >=", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameLessThan(String value) {
            addCriterion("TEST_NAME <", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameLessThanOrEqualTo(String value) {
            addCriterion("TEST_NAME <=", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameLike(String value) {
            addCriterion("TEST_NAME like", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotLike(String value) {
            addCriterion("TEST_NAME not like", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameIn(List<String> values) {
            addCriterion("TEST_NAME in", values, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotIn(List<String> values) {
            addCriterion("TEST_NAME not in", values, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameBetween(String value1, String value2) {
            addCriterion("TEST_NAME between", value1, value2, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotBetween(String value1, String value2) {
            addCriterion("TEST_NAME not between", value1, value2, "testName");
            return (Criteria) this;
        }

        public Criteria andApplyStartTimeIsNull() {
            addCriterion("APPLY_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andApplyStartTimeIsNotNull() {
            addCriterion("APPLY_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyStartTimeEqualTo(String value) {
            addCriterion("APPLY_START_TIME =", value, "applyStartTime");
            return (Criteria) this;
        }

        public Criteria andApplyStartTimeNotEqualTo(String value) {
            addCriterion("APPLY_START_TIME <>", value, "applyStartTime");
            return (Criteria) this;
        }

        public Criteria andApplyStartTimeGreaterThan(String value) {
            addCriterion("APPLY_START_TIME >", value, "applyStartTime");
            return (Criteria) this;
        }

        public Criteria andApplyStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_START_TIME >=", value, "applyStartTime");
            return (Criteria) this;
        }

        public Criteria andApplyStartTimeLessThan(String value) {
            addCriterion("APPLY_START_TIME <", value, "applyStartTime");
            return (Criteria) this;
        }

        public Criteria andApplyStartTimeLessThanOrEqualTo(String value) {
            addCriterion("APPLY_START_TIME <=", value, "applyStartTime");
            return (Criteria) this;
        }

        public Criteria andApplyStartTimeLike(String value) {
            addCriterion("APPLY_START_TIME like", value, "applyStartTime");
            return (Criteria) this;
        }

        public Criteria andApplyStartTimeNotLike(String value) {
            addCriterion("APPLY_START_TIME not like", value, "applyStartTime");
            return (Criteria) this;
        }

        public Criteria andApplyStartTimeIn(List<String> values) {
            addCriterion("APPLY_START_TIME in", values, "applyStartTime");
            return (Criteria) this;
        }

        public Criteria andApplyStartTimeNotIn(List<String> values) {
            addCriterion("APPLY_START_TIME not in", values, "applyStartTime");
            return (Criteria) this;
        }

        public Criteria andApplyStartTimeBetween(String value1, String value2) {
            addCriterion("APPLY_START_TIME between", value1, value2, "applyStartTime");
            return (Criteria) this;
        }

        public Criteria andApplyStartTimeNotBetween(String value1, String value2) {
            addCriterion("APPLY_START_TIME not between", value1, value2, "applyStartTime");
            return (Criteria) this;
        }

        public Criteria andApplyEndTimeIsNull() {
            addCriterion("APPLY_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andApplyEndTimeIsNotNull() {
            addCriterion("APPLY_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyEndTimeEqualTo(String value) {
            addCriterion("APPLY_END_TIME =", value, "applyEndTime");
            return (Criteria) this;
        }

        public Criteria andApplyEndTimeNotEqualTo(String value) {
            addCriterion("APPLY_END_TIME <>", value, "applyEndTime");
            return (Criteria) this;
        }

        public Criteria andApplyEndTimeGreaterThan(String value) {
            addCriterion("APPLY_END_TIME >", value, "applyEndTime");
            return (Criteria) this;
        }

        public Criteria andApplyEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_END_TIME >=", value, "applyEndTime");
            return (Criteria) this;
        }

        public Criteria andApplyEndTimeLessThan(String value) {
            addCriterion("APPLY_END_TIME <", value, "applyEndTime");
            return (Criteria) this;
        }

        public Criteria andApplyEndTimeLessThanOrEqualTo(String value) {
            addCriterion("APPLY_END_TIME <=", value, "applyEndTime");
            return (Criteria) this;
        }

        public Criteria andApplyEndTimeLike(String value) {
            addCriterion("APPLY_END_TIME like", value, "applyEndTime");
            return (Criteria) this;
        }

        public Criteria andApplyEndTimeNotLike(String value) {
            addCriterion("APPLY_END_TIME not like", value, "applyEndTime");
            return (Criteria) this;
        }

        public Criteria andApplyEndTimeIn(List<String> values) {
            addCriterion("APPLY_END_TIME in", values, "applyEndTime");
            return (Criteria) this;
        }

        public Criteria andApplyEndTimeNotIn(List<String> values) {
            addCriterion("APPLY_END_TIME not in", values, "applyEndTime");
            return (Criteria) this;
        }

        public Criteria andApplyEndTimeBetween(String value1, String value2) {
            addCriterion("APPLY_END_TIME between", value1, value2, "applyEndTime");
            return (Criteria) this;
        }

        public Criteria andApplyEndTimeNotBetween(String value1, String value2) {
            addCriterion("APPLY_END_TIME not between", value1, value2, "applyEndTime");
            return (Criteria) this;
        }

        public Criteria andCitysIdIsNull() {
            addCriterion("CITYS_ID is null");
            return (Criteria) this;
        }

        public Criteria andCitysIdIsNotNull() {
            addCriterion("CITYS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCitysIdEqualTo(String value) {
            addCriterion("CITYS_ID =", value, "citysId");
            return (Criteria) this;
        }

        public Criteria andCitysIdNotEqualTo(String value) {
            addCriterion("CITYS_ID <>", value, "citysId");
            return (Criteria) this;
        }

        public Criteria andCitysIdGreaterThan(String value) {
            addCriterion("CITYS_ID >", value, "citysId");
            return (Criteria) this;
        }

        public Criteria andCitysIdGreaterThanOrEqualTo(String value) {
            addCriterion("CITYS_ID >=", value, "citysId");
            return (Criteria) this;
        }

        public Criteria andCitysIdLessThan(String value) {
            addCriterion("CITYS_ID <", value, "citysId");
            return (Criteria) this;
        }

        public Criteria andCitysIdLessThanOrEqualTo(String value) {
            addCriterion("CITYS_ID <=", value, "citysId");
            return (Criteria) this;
        }

        public Criteria andCitysIdLike(String value) {
            addCriterion("CITYS_ID like", value, "citysId");
            return (Criteria) this;
        }

        public Criteria andCitysIdNotLike(String value) {
            addCriterion("CITYS_ID not like", value, "citysId");
            return (Criteria) this;
        }

        public Criteria andCitysIdIn(List<String> values) {
            addCriterion("CITYS_ID in", values, "citysId");
            return (Criteria) this;
        }

        public Criteria andCitysIdNotIn(List<String> values) {
            addCriterion("CITYS_ID not in", values, "citysId");
            return (Criteria) this;
        }

        public Criteria andCitysIdBetween(String value1, String value2) {
            addCriterion("CITYS_ID between", value1, value2, "citysId");
            return (Criteria) this;
        }

        public Criteria andCitysIdNotBetween(String value1, String value2) {
            addCriterion("CITYS_ID not between", value1, value2, "citysId");
            return (Criteria) this;
        }

        public Criteria andLocalAuditIsNull() {
            addCriterion("LOCAL_AUDIT is null");
            return (Criteria) this;
        }

        public Criteria andLocalAuditIsNotNull() {
            addCriterion("LOCAL_AUDIT is not null");
            return (Criteria) this;
        }

        public Criteria andLocalAuditEqualTo(String value) {
            addCriterion("LOCAL_AUDIT =", value, "localAudit");
            return (Criteria) this;
        }

        public Criteria andLocalAuditNotEqualTo(String value) {
            addCriterion("LOCAL_AUDIT <>", value, "localAudit");
            return (Criteria) this;
        }

        public Criteria andLocalAuditGreaterThan(String value) {
            addCriterion("LOCAL_AUDIT >", value, "localAudit");
            return (Criteria) this;
        }

        public Criteria andLocalAuditGreaterThanOrEqualTo(String value) {
            addCriterion("LOCAL_AUDIT >=", value, "localAudit");
            return (Criteria) this;
        }

        public Criteria andLocalAuditLessThan(String value) {
            addCriterion("LOCAL_AUDIT <", value, "localAudit");
            return (Criteria) this;
        }

        public Criteria andLocalAuditLessThanOrEqualTo(String value) {
            addCriterion("LOCAL_AUDIT <=", value, "localAudit");
            return (Criteria) this;
        }

        public Criteria andLocalAuditLike(String value) {
            addCriterion("LOCAL_AUDIT like", value, "localAudit");
            return (Criteria) this;
        }

        public Criteria andLocalAuditNotLike(String value) {
            addCriterion("LOCAL_AUDIT not like", value, "localAudit");
            return (Criteria) this;
        }

        public Criteria andLocalAuditIn(List<String> values) {
            addCriterion("LOCAL_AUDIT in", values, "localAudit");
            return (Criteria) this;
        }

        public Criteria andLocalAuditNotIn(List<String> values) {
            addCriterion("LOCAL_AUDIT not in", values, "localAudit");
            return (Criteria) this;
        }

        public Criteria andLocalAuditBetween(String value1, String value2) {
            addCriterion("LOCAL_AUDIT between", value1, value2, "localAudit");
            return (Criteria) this;
        }

        public Criteria andLocalAuditNotBetween(String value1, String value2) {
            addCriterion("LOCAL_AUDIT not between", value1, value2, "localAudit");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStartTimeIsNull() {
            addCriterion("LOCAL_AUDIT_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStartTimeIsNotNull() {
            addCriterion("LOCAL_AUDIT_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStartTimeEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_START_TIME =", value, "localAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStartTimeNotEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_START_TIME <>", value, "localAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStartTimeGreaterThan(String value) {
            addCriterion("LOCAL_AUDIT_START_TIME >", value, "localAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_START_TIME >=", value, "localAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStartTimeLessThan(String value) {
            addCriterion("LOCAL_AUDIT_START_TIME <", value, "localAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStartTimeLessThanOrEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_START_TIME <=", value, "localAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStartTimeLike(String value) {
            addCriterion("LOCAL_AUDIT_START_TIME like", value, "localAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStartTimeNotLike(String value) {
            addCriterion("LOCAL_AUDIT_START_TIME not like", value, "localAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStartTimeIn(List<String> values) {
            addCriterion("LOCAL_AUDIT_START_TIME in", values, "localAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStartTimeNotIn(List<String> values) {
            addCriterion("LOCAL_AUDIT_START_TIME not in", values, "localAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStartTimeBetween(String value1, String value2) {
            addCriterion("LOCAL_AUDIT_START_TIME between", value1, value2, "localAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditStartTimeNotBetween(String value1, String value2) {
            addCriterion("LOCAL_AUDIT_START_TIME not between", value1, value2, "localAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditEndTimeIsNull() {
            addCriterion("LOCAL_AUDIT_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLocalAuditEndTimeIsNotNull() {
            addCriterion("LOCAL_AUDIT_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLocalAuditEndTimeEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_END_TIME =", value, "localAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditEndTimeNotEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_END_TIME <>", value, "localAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditEndTimeGreaterThan(String value) {
            addCriterion("LOCAL_AUDIT_END_TIME >", value, "localAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_END_TIME >=", value, "localAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditEndTimeLessThan(String value) {
            addCriterion("LOCAL_AUDIT_END_TIME <", value, "localAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditEndTimeLessThanOrEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_END_TIME <=", value, "localAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditEndTimeLike(String value) {
            addCriterion("LOCAL_AUDIT_END_TIME like", value, "localAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditEndTimeNotLike(String value) {
            addCriterion("LOCAL_AUDIT_END_TIME not like", value, "localAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditEndTimeIn(List<String> values) {
            addCriterion("LOCAL_AUDIT_END_TIME in", values, "localAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditEndTimeNotIn(List<String> values) {
            addCriterion("LOCAL_AUDIT_END_TIME not in", values, "localAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditEndTimeBetween(String value1, String value2) {
            addCriterion("LOCAL_AUDIT_END_TIME between", value1, value2, "localAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditEndTimeNotBetween(String value1, String value2) {
            addCriterion("LOCAL_AUDIT_END_TIME not between", value1, value2, "localAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditIsNull() {
            addCriterion("CHARGE_AUDIT is null");
            return (Criteria) this;
        }

        public Criteria andChargeAuditIsNotNull() {
            addCriterion("CHARGE_AUDIT is not null");
            return (Criteria) this;
        }

        public Criteria andChargeAuditEqualTo(String value) {
            addCriterion("CHARGE_AUDIT =", value, "chargeAudit");
            return (Criteria) this;
        }

        public Criteria andChargeAuditNotEqualTo(String value) {
            addCriterion("CHARGE_AUDIT <>", value, "chargeAudit");
            return (Criteria) this;
        }

        public Criteria andChargeAuditGreaterThan(String value) {
            addCriterion("CHARGE_AUDIT >", value, "chargeAudit");
            return (Criteria) this;
        }

        public Criteria andChargeAuditGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_AUDIT >=", value, "chargeAudit");
            return (Criteria) this;
        }

        public Criteria andChargeAuditLessThan(String value) {
            addCriterion("CHARGE_AUDIT <", value, "chargeAudit");
            return (Criteria) this;
        }

        public Criteria andChargeAuditLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_AUDIT <=", value, "chargeAudit");
            return (Criteria) this;
        }

        public Criteria andChargeAuditLike(String value) {
            addCriterion("CHARGE_AUDIT like", value, "chargeAudit");
            return (Criteria) this;
        }

        public Criteria andChargeAuditNotLike(String value) {
            addCriterion("CHARGE_AUDIT not like", value, "chargeAudit");
            return (Criteria) this;
        }

        public Criteria andChargeAuditIn(List<String> values) {
            addCriterion("CHARGE_AUDIT in", values, "chargeAudit");
            return (Criteria) this;
        }

        public Criteria andChargeAuditNotIn(List<String> values) {
            addCriterion("CHARGE_AUDIT not in", values, "chargeAudit");
            return (Criteria) this;
        }

        public Criteria andChargeAuditBetween(String value1, String value2) {
            addCriterion("CHARGE_AUDIT between", value1, value2, "chargeAudit");
            return (Criteria) this;
        }

        public Criteria andChargeAuditNotBetween(String value1, String value2) {
            addCriterion("CHARGE_AUDIT not between", value1, value2, "chargeAudit");
            return (Criteria) this;
        }

        public Criteria andChargeAuditStartTimeIsNull() {
            addCriterion("CHARGE_AUDIT_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andChargeAuditStartTimeIsNotNull() {
            addCriterion("CHARGE_AUDIT_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andChargeAuditStartTimeEqualTo(String value) {
            addCriterion("CHARGE_AUDIT_START_TIME =", value, "chargeAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditStartTimeNotEqualTo(String value) {
            addCriterion("CHARGE_AUDIT_START_TIME <>", value, "chargeAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditStartTimeGreaterThan(String value) {
            addCriterion("CHARGE_AUDIT_START_TIME >", value, "chargeAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_AUDIT_START_TIME >=", value, "chargeAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditStartTimeLessThan(String value) {
            addCriterion("CHARGE_AUDIT_START_TIME <", value, "chargeAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditStartTimeLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_AUDIT_START_TIME <=", value, "chargeAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditStartTimeLike(String value) {
            addCriterion("CHARGE_AUDIT_START_TIME like", value, "chargeAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditStartTimeNotLike(String value) {
            addCriterion("CHARGE_AUDIT_START_TIME not like", value, "chargeAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditStartTimeIn(List<String> values) {
            addCriterion("CHARGE_AUDIT_START_TIME in", values, "chargeAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditStartTimeNotIn(List<String> values) {
            addCriterion("CHARGE_AUDIT_START_TIME not in", values, "chargeAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditStartTimeBetween(String value1, String value2) {
            addCriterion("CHARGE_AUDIT_START_TIME between", value1, value2, "chargeAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditStartTimeNotBetween(String value1, String value2) {
            addCriterion("CHARGE_AUDIT_START_TIME not between", value1, value2, "chargeAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditEndTimeIsNull() {
            addCriterion("CHARGE_AUDIT_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andChargeAuditEndTimeIsNotNull() {
            addCriterion("CHARGE_AUDIT_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andChargeAuditEndTimeEqualTo(String value) {
            addCriterion("CHARGE_AUDIT_END_TIME =", value, "chargeAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditEndTimeNotEqualTo(String value) {
            addCriterion("CHARGE_AUDIT_END_TIME <>", value, "chargeAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditEndTimeGreaterThan(String value) {
            addCriterion("CHARGE_AUDIT_END_TIME >", value, "chargeAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_AUDIT_END_TIME >=", value, "chargeAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditEndTimeLessThan(String value) {
            addCriterion("CHARGE_AUDIT_END_TIME <", value, "chargeAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditEndTimeLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_AUDIT_END_TIME <=", value, "chargeAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditEndTimeLike(String value) {
            addCriterion("CHARGE_AUDIT_END_TIME like", value, "chargeAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditEndTimeNotLike(String value) {
            addCriterion("CHARGE_AUDIT_END_TIME not like", value, "chargeAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditEndTimeIn(List<String> values) {
            addCriterion("CHARGE_AUDIT_END_TIME in", values, "chargeAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditEndTimeNotIn(List<String> values) {
            addCriterion("CHARGE_AUDIT_END_TIME not in", values, "chargeAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditEndTimeBetween(String value1, String value2) {
            addCriterion("CHARGE_AUDIT_END_TIME between", value1, value2, "chargeAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andChargeAuditEndTimeNotBetween(String value1, String value2) {
            addCriterion("CHARGE_AUDIT_END_TIME not between", value1, value2, "chargeAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andTestEndTimeIsNull() {
            addCriterion("TEST_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTestEndTimeIsNotNull() {
            addCriterion("TEST_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTestEndTimeEqualTo(String value) {
            addCriterion("TEST_END_TIME =", value, "testEndTime");
            return (Criteria) this;
        }

        public Criteria andTestEndTimeNotEqualTo(String value) {
            addCriterion("TEST_END_TIME <>", value, "testEndTime");
            return (Criteria) this;
        }

        public Criteria andTestEndTimeGreaterThan(String value) {
            addCriterion("TEST_END_TIME >", value, "testEndTime");
            return (Criteria) this;
        }

        public Criteria andTestEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_END_TIME >=", value, "testEndTime");
            return (Criteria) this;
        }

        public Criteria andTestEndTimeLessThan(String value) {
            addCriterion("TEST_END_TIME <", value, "testEndTime");
            return (Criteria) this;
        }

        public Criteria andTestEndTimeLessThanOrEqualTo(String value) {
            addCriterion("TEST_END_TIME <=", value, "testEndTime");
            return (Criteria) this;
        }

        public Criteria andTestEndTimeLike(String value) {
            addCriterion("TEST_END_TIME like", value, "testEndTime");
            return (Criteria) this;
        }

        public Criteria andTestEndTimeNotLike(String value) {
            addCriterion("TEST_END_TIME not like", value, "testEndTime");
            return (Criteria) this;
        }

        public Criteria andTestEndTimeIn(List<String> values) {
            addCriterion("TEST_END_TIME in", values, "testEndTime");
            return (Criteria) this;
        }

        public Criteria andTestEndTimeNotIn(List<String> values) {
            addCriterion("TEST_END_TIME not in", values, "testEndTime");
            return (Criteria) this;
        }

        public Criteria andTestEndTimeBetween(String value1, String value2) {
            addCriterion("TEST_END_TIME between", value1, value2, "testEndTime");
            return (Criteria) this;
        }

        public Criteria andTestEndTimeNotBetween(String value1, String value2) {
            addCriterion("TEST_END_TIME not between", value1, value2, "testEndTime");
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

        public Criteria andJointLocalAuditIsNull() {
            addCriterion("JOINT_LOCAL_AUDIT is null");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditIsNotNull() {
            addCriterion("JOINT_LOCAL_AUDIT is not null");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditEqualTo(String value) {
            addCriterion("JOINT_LOCAL_AUDIT =", value, "jointLocalAudit");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditNotEqualTo(String value) {
            addCriterion("JOINT_LOCAL_AUDIT <>", value, "jointLocalAudit");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditGreaterThan(String value) {
            addCriterion("JOINT_LOCAL_AUDIT >", value, "jointLocalAudit");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditGreaterThanOrEqualTo(String value) {
            addCriterion("JOINT_LOCAL_AUDIT >=", value, "jointLocalAudit");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditLessThan(String value) {
            addCriterion("JOINT_LOCAL_AUDIT <", value, "jointLocalAudit");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditLessThanOrEqualTo(String value) {
            addCriterion("JOINT_LOCAL_AUDIT <=", value, "jointLocalAudit");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditLike(String value) {
            addCriterion("JOINT_LOCAL_AUDIT like", value, "jointLocalAudit");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditNotLike(String value) {
            addCriterion("JOINT_LOCAL_AUDIT not like", value, "jointLocalAudit");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditIn(List<String> values) {
            addCriterion("JOINT_LOCAL_AUDIT in", values, "jointLocalAudit");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditNotIn(List<String> values) {
            addCriterion("JOINT_LOCAL_AUDIT not in", values, "jointLocalAudit");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditBetween(String value1, String value2) {
            addCriterion("JOINT_LOCAL_AUDIT between", value1, value2, "jointLocalAudit");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditNotBetween(String value1, String value2) {
            addCriterion("JOINT_LOCAL_AUDIT not between", value1, value2, "jointLocalAudit");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditStartTimeIsNull() {
            addCriterion("JOINT_LOCAL_AUDIT_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditStartTimeIsNotNull() {
            addCriterion("JOINT_LOCAL_AUDIT_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditStartTimeEqualTo(String value) {
            addCriterion("JOINT_LOCAL_AUDIT_START_TIME =", value, "jointLocalAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditStartTimeNotEqualTo(String value) {
            addCriterion("JOINT_LOCAL_AUDIT_START_TIME <>", value, "jointLocalAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditStartTimeGreaterThan(String value) {
            addCriterion("JOINT_LOCAL_AUDIT_START_TIME >", value, "jointLocalAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("JOINT_LOCAL_AUDIT_START_TIME >=", value, "jointLocalAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditStartTimeLessThan(String value) {
            addCriterion("JOINT_LOCAL_AUDIT_START_TIME <", value, "jointLocalAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditStartTimeLessThanOrEqualTo(String value) {
            addCriterion("JOINT_LOCAL_AUDIT_START_TIME <=", value, "jointLocalAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditStartTimeLike(String value) {
            addCriterion("JOINT_LOCAL_AUDIT_START_TIME like", value, "jointLocalAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditStartTimeNotLike(String value) {
            addCriterion("JOINT_LOCAL_AUDIT_START_TIME not like", value, "jointLocalAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditStartTimeIn(List<String> values) {
            addCriterion("JOINT_LOCAL_AUDIT_START_TIME in", values, "jointLocalAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditStartTimeNotIn(List<String> values) {
            addCriterion("JOINT_LOCAL_AUDIT_START_TIME not in", values, "jointLocalAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditStartTimeBetween(String value1, String value2) {
            addCriterion("JOINT_LOCAL_AUDIT_START_TIME between", value1, value2, "jointLocalAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditStartTimeNotBetween(String value1, String value2) {
            addCriterion("JOINT_LOCAL_AUDIT_START_TIME not between", value1, value2, "jointLocalAuditStartTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditEndTimeIsNull() {
            addCriterion("JOINT_LOCAL_AUDIT_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditEndTimeIsNotNull() {
            addCriterion("JOINT_LOCAL_AUDIT_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditEndTimeEqualTo(String value) {
            addCriterion("JOINT_LOCAL_AUDIT_END_TIME =", value, "jointLocalAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditEndTimeNotEqualTo(String value) {
            addCriterion("JOINT_LOCAL_AUDIT_END_TIME <>", value, "jointLocalAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditEndTimeGreaterThan(String value) {
            addCriterion("JOINT_LOCAL_AUDIT_END_TIME >", value, "jointLocalAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("JOINT_LOCAL_AUDIT_END_TIME >=", value, "jointLocalAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditEndTimeLessThan(String value) {
            addCriterion("JOINT_LOCAL_AUDIT_END_TIME <", value, "jointLocalAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditEndTimeLessThanOrEqualTo(String value) {
            addCriterion("JOINT_LOCAL_AUDIT_END_TIME <=", value, "jointLocalAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditEndTimeLike(String value) {
            addCriterion("JOINT_LOCAL_AUDIT_END_TIME like", value, "jointLocalAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditEndTimeNotLike(String value) {
            addCriterion("JOINT_LOCAL_AUDIT_END_TIME not like", value, "jointLocalAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditEndTimeIn(List<String> values) {
            addCriterion("JOINT_LOCAL_AUDIT_END_TIME in", values, "jointLocalAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditEndTimeNotIn(List<String> values) {
            addCriterion("JOINT_LOCAL_AUDIT_END_TIME not in", values, "jointLocalAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditEndTimeBetween(String value1, String value2) {
            addCriterion("JOINT_LOCAL_AUDIT_END_TIME between", value1, value2, "jointLocalAuditEndTime");
            return (Criteria) this;
        }

        public Criteria andJointLocalAuditEndTimeNotBetween(String value1, String value2) {
            addCriterion("JOINT_LOCAL_AUDIT_END_TIME not between", value1, value2, "jointLocalAuditEndTime");
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