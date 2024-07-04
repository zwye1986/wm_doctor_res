package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SrmAchTopicExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmAchTopicExample() {
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

        public Criteria andTopicFlowIsNull() {
            addCriterion("TOPIC_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTopicFlowIsNotNull() {
            addCriterion("TOPIC_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTopicFlowEqualTo(String value) {
            addCriterion("TOPIC_FLOW =", value, "topicFlow");
            return (Criteria) this;
        }

        public Criteria andTopicFlowNotEqualTo(String value) {
            addCriterion("TOPIC_FLOW <>", value, "topicFlow");
            return (Criteria) this;
        }

        public Criteria andTopicFlowGreaterThan(String value) {
            addCriterion("TOPIC_FLOW >", value, "topicFlow");
            return (Criteria) this;
        }

        public Criteria andTopicFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TOPIC_FLOW >=", value, "topicFlow");
            return (Criteria) this;
        }

        public Criteria andTopicFlowLessThan(String value) {
            addCriterion("TOPIC_FLOW <", value, "topicFlow");
            return (Criteria) this;
        }

        public Criteria andTopicFlowLessThanOrEqualTo(String value) {
            addCriterion("TOPIC_FLOW <=", value, "topicFlow");
            return (Criteria) this;
        }

        public Criteria andTopicFlowLike(String value) {
            addCriterion("TOPIC_FLOW like", value, "topicFlow");
            return (Criteria) this;
        }

        public Criteria andTopicFlowNotLike(String value) {
            addCriterion("TOPIC_FLOW not like", value, "topicFlow");
            return (Criteria) this;
        }

        public Criteria andTopicFlowIn(List<String> values) {
            addCriterion("TOPIC_FLOW in", values, "topicFlow");
            return (Criteria) this;
        }

        public Criteria andTopicFlowNotIn(List<String> values) {
            addCriterion("TOPIC_FLOW not in", values, "topicFlow");
            return (Criteria) this;
        }

        public Criteria andTopicFlowBetween(String value1, String value2) {
            addCriterion("TOPIC_FLOW between", value1, value2, "topicFlow");
            return (Criteria) this;
        }

        public Criteria andTopicFlowNotBetween(String value1, String value2) {
            addCriterion("TOPIC_FLOW not between", value1, value2, "topicFlow");
            return (Criteria) this;
        }

        public Criteria andTopicNameIsNull() {
            addCriterion("TOPIC_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTopicNameIsNotNull() {
            addCriterion("TOPIC_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTopicNameEqualTo(String value) {
            addCriterion("TOPIC_NAME =", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameNotEqualTo(String value) {
            addCriterion("TOPIC_NAME <>", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameGreaterThan(String value) {
            addCriterion("TOPIC_NAME >", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameGreaterThanOrEqualTo(String value) {
            addCriterion("TOPIC_NAME >=", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameLessThan(String value) {
            addCriterion("TOPIC_NAME <", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameLessThanOrEqualTo(String value) {
            addCriterion("TOPIC_NAME <=", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameLike(String value) {
            addCriterion("TOPIC_NAME like", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameNotLike(String value) {
            addCriterion("TOPIC_NAME not like", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameIn(List<String> values) {
            addCriterion("TOPIC_NAME in", values, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameNotIn(List<String> values) {
            addCriterion("TOPIC_NAME not in", values, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameBetween(String value1, String value2) {
            addCriterion("TOPIC_NAME between", value1, value2, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameNotBetween(String value1, String value2) {
            addCriterion("TOPIC_NAME not between", value1, value2, "topicName");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIsNull() {
            addCriterion("DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIsNotNull() {
            addCriterion("DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowEqualTo(String value) {
            addCriterion("DEPT_FLOW =", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotEqualTo(String value) {
            addCriterion("DEPT_FLOW <>", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThan(String value) {
            addCriterion("DEPT_FLOW >", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW >=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThan(String value) {
            addCriterion("DEPT_FLOW <", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW <=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLike(String value) {
            addCriterion("DEPT_FLOW like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotLike(String value) {
            addCriterion("DEPT_FLOW not like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIn(List<String> values) {
            addCriterion("DEPT_FLOW in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotIn(List<String> values) {
            addCriterion("DEPT_FLOW not in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW not between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNull() {
            addCriterion("DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNotNull() {
            addCriterion("DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNameEqualTo(String value) {
            addCriterion("DEPT_NAME =", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotEqualTo(String value) {
            addCriterion("DEPT_NAME <>", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThan(String value) {
            addCriterion("DEPT_NAME >", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME >=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThan(String value) {
            addCriterion("DEPT_NAME <", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME <=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLike(String value) {
            addCriterion("DEPT_NAME like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotLike(String value) {
            addCriterion("DEPT_NAME not like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameIn(List<String> values) {
            addCriterion("DEPT_NAME in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotIn(List<String> values) {
            addCriterion("DEPT_NAME not in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameBetween(String value1, String value2) {
            addCriterion("DEPT_NAME between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotBetween(String value1, String value2) {
            addCriterion("DEPT_NAME not between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgIsNull() {
            addCriterion("CHARGE_ORG is null");
            return (Criteria) this;
        }

        public Criteria andChargeOrgIsNotNull() {
            addCriterion("CHARGE_ORG is not null");
            return (Criteria) this;
        }

        public Criteria andChargeOrgEqualTo(String value) {
            addCriterion("CHARGE_ORG =", value, "chargeOrg");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNotEqualTo(String value) {
            addCriterion("CHARGE_ORG <>", value, "chargeOrg");
            return (Criteria) this;
        }

        public Criteria andChargeOrgGreaterThan(String value) {
            addCriterion("CHARGE_ORG >", value, "chargeOrg");
            return (Criteria) this;
        }

        public Criteria andChargeOrgGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_ORG >=", value, "chargeOrg");
            return (Criteria) this;
        }

        public Criteria andChargeOrgLessThan(String value) {
            addCriterion("CHARGE_ORG <", value, "chargeOrg");
            return (Criteria) this;
        }

        public Criteria andChargeOrgLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_ORG <=", value, "chargeOrg");
            return (Criteria) this;
        }

        public Criteria andChargeOrgLike(String value) {
            addCriterion("CHARGE_ORG like", value, "chargeOrg");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNotLike(String value) {
            addCriterion("CHARGE_ORG not like", value, "chargeOrg");
            return (Criteria) this;
        }

        public Criteria andChargeOrgIn(List<String> values) {
            addCriterion("CHARGE_ORG in", values, "chargeOrg");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNotIn(List<String> values) {
            addCriterion("CHARGE_ORG not in", values, "chargeOrg");
            return (Criteria) this;
        }

        public Criteria andChargeOrgBetween(String value1, String value2) {
            addCriterion("CHARGE_ORG between", value1, value2, "chargeOrg");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNotBetween(String value1, String value2) {
            addCriterion("CHARGE_ORG not between", value1, value2, "chargeOrg");
            return (Criteria) this;
        }

        public Criteria andApproveNumberIsNull() {
            addCriterion("APPROVE_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andApproveNumberIsNotNull() {
            addCriterion("APPROVE_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andApproveNumberEqualTo(String value) {
            addCriterion("APPROVE_NUMBER =", value, "approveNumber");
            return (Criteria) this;
        }

        public Criteria andApproveNumberNotEqualTo(String value) {
            addCriterion("APPROVE_NUMBER <>", value, "approveNumber");
            return (Criteria) this;
        }

        public Criteria andApproveNumberGreaterThan(String value) {
            addCriterion("APPROVE_NUMBER >", value, "approveNumber");
            return (Criteria) this;
        }

        public Criteria andApproveNumberGreaterThanOrEqualTo(String value) {
            addCriterion("APPROVE_NUMBER >=", value, "approveNumber");
            return (Criteria) this;
        }

        public Criteria andApproveNumberLessThan(String value) {
            addCriterion("APPROVE_NUMBER <", value, "approveNumber");
            return (Criteria) this;
        }

        public Criteria andApproveNumberLessThanOrEqualTo(String value) {
            addCriterion("APPROVE_NUMBER <=", value, "approveNumber");
            return (Criteria) this;
        }

        public Criteria andApproveNumberLike(String value) {
            addCriterion("APPROVE_NUMBER like", value, "approveNumber");
            return (Criteria) this;
        }

        public Criteria andApproveNumberNotLike(String value) {
            addCriterion("APPROVE_NUMBER not like", value, "approveNumber");
            return (Criteria) this;
        }

        public Criteria andApproveNumberIn(List<String> values) {
            addCriterion("APPROVE_NUMBER in", values, "approveNumber");
            return (Criteria) this;
        }

        public Criteria andApproveNumberNotIn(List<String> values) {
            addCriterion("APPROVE_NUMBER not in", values, "approveNumber");
            return (Criteria) this;
        }

        public Criteria andApproveNumberBetween(String value1, String value2) {
            addCriterion("APPROVE_NUMBER between", value1, value2, "approveNumber");
            return (Criteria) this;
        }

        public Criteria andApproveNumberNotBetween(String value1, String value2) {
            addCriterion("APPROVE_NUMBER not between", value1, value2, "approveNumber");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIsNull() {
            addCriterion("APPLY_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIsNotNull() {
            addCriterion("APPLY_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW =", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW <>", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowGreaterThan(String value) {
            addCriterion("APPLY_USER_FLOW >", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW >=", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLessThan(String value) {
            addCriterion("APPLY_USER_FLOW <", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW <=", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLike(String value) {
            addCriterion("APPLY_USER_FLOW like", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotLike(String value) {
            addCriterion("APPLY_USER_FLOW not like", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIn(List<String> values) {
            addCriterion("APPLY_USER_FLOW in", values, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotIn(List<String> values) {
            addCriterion("APPLY_USER_FLOW not in", values, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowBetween(String value1, String value2) {
            addCriterion("APPLY_USER_FLOW between", value1, value2, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_FLOW not between", value1, value2, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIsNull() {
            addCriterion("APPLY_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIsNotNull() {
            addCriterion("APPLY_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameEqualTo(String value) {
            addCriterion("APPLY_USER_NAME =", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotEqualTo(String value) {
            addCriterion("APPLY_USER_NAME <>", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameGreaterThan(String value) {
            addCriterion("APPLY_USER_NAME >", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_NAME >=", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLessThan(String value) {
            addCriterion("APPLY_USER_NAME <", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_NAME <=", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLike(String value) {
            addCriterion("APPLY_USER_NAME like", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotLike(String value) {
            addCriterion("APPLY_USER_NAME not like", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIn(List<String> values) {
            addCriterion("APPLY_USER_NAME in", values, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotIn(List<String> values) {
            addCriterion("APPLY_USER_NAME not in", values, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameBetween(String value1, String value2) {
            addCriterion("APPLY_USER_NAME between", value1, value2, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_NAME not between", value1, value2, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andTopicStartTimeIsNull() {
            addCriterion("TOPIC_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTopicStartTimeIsNotNull() {
            addCriterion("TOPIC_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTopicStartTimeEqualTo(String value) {
            addCriterion("TOPIC_START_TIME =", value, "topicStartTime");
            return (Criteria) this;
        }

        public Criteria andTopicStartTimeNotEqualTo(String value) {
            addCriterion("TOPIC_START_TIME <>", value, "topicStartTime");
            return (Criteria) this;
        }

        public Criteria andTopicStartTimeGreaterThan(String value) {
            addCriterion("TOPIC_START_TIME >", value, "topicStartTime");
            return (Criteria) this;
        }

        public Criteria andTopicStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TOPIC_START_TIME >=", value, "topicStartTime");
            return (Criteria) this;
        }

        public Criteria andTopicStartTimeLessThan(String value) {
            addCriterion("TOPIC_START_TIME <", value, "topicStartTime");
            return (Criteria) this;
        }

        public Criteria andTopicStartTimeLessThanOrEqualTo(String value) {
            addCriterion("TOPIC_START_TIME <=", value, "topicStartTime");
            return (Criteria) this;
        }

        public Criteria andTopicStartTimeLike(String value) {
            addCriterion("TOPIC_START_TIME like", value, "topicStartTime");
            return (Criteria) this;
        }

        public Criteria andTopicStartTimeNotLike(String value) {
            addCriterion("TOPIC_START_TIME not like", value, "topicStartTime");
            return (Criteria) this;
        }

        public Criteria andTopicStartTimeIn(List<String> values) {
            addCriterion("TOPIC_START_TIME in", values, "topicStartTime");
            return (Criteria) this;
        }

        public Criteria andTopicStartTimeNotIn(List<String> values) {
            addCriterion("TOPIC_START_TIME not in", values, "topicStartTime");
            return (Criteria) this;
        }

        public Criteria andTopicStartTimeBetween(String value1, String value2) {
            addCriterion("TOPIC_START_TIME between", value1, value2, "topicStartTime");
            return (Criteria) this;
        }

        public Criteria andTopicStartTimeNotBetween(String value1, String value2) {
            addCriterion("TOPIC_START_TIME not between", value1, value2, "topicStartTime");
            return (Criteria) this;
        }

        public Criteria andTopicEndTimeIsNull() {
            addCriterion("TOPIC_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTopicEndTimeIsNotNull() {
            addCriterion("TOPIC_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTopicEndTimeEqualTo(String value) {
            addCriterion("TOPIC_END_TIME =", value, "topicEndTime");
            return (Criteria) this;
        }

        public Criteria andTopicEndTimeNotEqualTo(String value) {
            addCriterion("TOPIC_END_TIME <>", value, "topicEndTime");
            return (Criteria) this;
        }

        public Criteria andTopicEndTimeGreaterThan(String value) {
            addCriterion("TOPIC_END_TIME >", value, "topicEndTime");
            return (Criteria) this;
        }

        public Criteria andTopicEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TOPIC_END_TIME >=", value, "topicEndTime");
            return (Criteria) this;
        }

        public Criteria andTopicEndTimeLessThan(String value) {
            addCriterion("TOPIC_END_TIME <", value, "topicEndTime");
            return (Criteria) this;
        }

        public Criteria andTopicEndTimeLessThanOrEqualTo(String value) {
            addCriterion("TOPIC_END_TIME <=", value, "topicEndTime");
            return (Criteria) this;
        }

        public Criteria andTopicEndTimeLike(String value) {
            addCriterion("TOPIC_END_TIME like", value, "topicEndTime");
            return (Criteria) this;
        }

        public Criteria andTopicEndTimeNotLike(String value) {
            addCriterion("TOPIC_END_TIME not like", value, "topicEndTime");
            return (Criteria) this;
        }

        public Criteria andTopicEndTimeIn(List<String> values) {
            addCriterion("TOPIC_END_TIME in", values, "topicEndTime");
            return (Criteria) this;
        }

        public Criteria andTopicEndTimeNotIn(List<String> values) {
            addCriterion("TOPIC_END_TIME not in", values, "topicEndTime");
            return (Criteria) this;
        }

        public Criteria andTopicEndTimeBetween(String value1, String value2) {
            addCriterion("TOPIC_END_TIME between", value1, value2, "topicEndTime");
            return (Criteria) this;
        }

        public Criteria andTopicEndTimeNotBetween(String value1, String value2) {
            addCriterion("TOPIC_END_TIME not between", value1, value2, "topicEndTime");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyIsNull() {
            addCriterion("TOPIC_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyIsNotNull() {
            addCriterion("TOPIC_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyEqualTo(BigDecimal value) {
            addCriterion("TOPIC_MONEY =", value, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyNotEqualTo(BigDecimal value) {
            addCriterion("TOPIC_MONEY <>", value, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyGreaterThan(BigDecimal value) {
            addCriterion("TOPIC_MONEY >", value, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TOPIC_MONEY >=", value, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyLessThan(BigDecimal value) {
            addCriterion("TOPIC_MONEY <", value, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TOPIC_MONEY <=", value, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyIn(List<BigDecimal> values) {
            addCriterion("TOPIC_MONEY in", values, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyNotIn(List<BigDecimal> values) {
            addCriterion("TOPIC_MONEY not in", values, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOPIC_MONEY between", value1, value2, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOPIC_MONEY not between", value1, value2, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowIsNull() {
            addCriterion("APPLY_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowIsNotNull() {
            addCriterion("APPLY_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW =", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW <>", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowGreaterThan(String value) {
            addCriterion("APPLY_ORG_FLOW >", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW >=", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowLessThan(String value) {
            addCriterion("APPLY_ORG_FLOW <", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW <=", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowLike(String value) {
            addCriterion("APPLY_ORG_FLOW like", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotLike(String value) {
            addCriterion("APPLY_ORG_FLOW not like", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowIn(List<String> values) {
            addCriterion("APPLY_ORG_FLOW in", values, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotIn(List<String> values) {
            addCriterion("APPLY_ORG_FLOW not in", values, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_FLOW between", value1, value2, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_FLOW not between", value1, value2, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameIsNull() {
            addCriterion("APPLY_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameIsNotNull() {
            addCriterion("APPLY_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME =", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME <>", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameGreaterThan(String value) {
            addCriterion("APPLY_ORG_NAME >", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME >=", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameLessThan(String value) {
            addCriterion("APPLY_ORG_NAME <", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameLessThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME <=", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameLike(String value) {
            addCriterion("APPLY_ORG_NAME like", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotLike(String value) {
            addCriterion("APPLY_ORG_NAME not like", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameIn(List<String> values) {
            addCriterion("APPLY_ORG_NAME in", values, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotIn(List<String> values) {
            addCriterion("APPLY_ORG_NAME not in", values, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_NAME between", value1, value2, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_NAME not between", value1, value2, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIsNull() {
            addCriterion("OPER_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIsNotNull() {
            addCriterion("OPER_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdEqualTo(String value) {
            addCriterion("OPER_STATUS_ID =", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotEqualTo(String value) {
            addCriterion("OPER_STATUS_ID <>", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdGreaterThan(String value) {
            addCriterion("OPER_STATUS_ID >", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_ID >=", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLessThan(String value) {
            addCriterion("OPER_STATUS_ID <", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLessThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_ID <=", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLike(String value) {
            addCriterion("OPER_STATUS_ID like", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotLike(String value) {
            addCriterion("OPER_STATUS_ID not like", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIn(List<String> values) {
            addCriterion("OPER_STATUS_ID in", values, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotIn(List<String> values) {
            addCriterion("OPER_STATUS_ID not in", values, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_ID between", value1, value2, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_ID not between", value1, value2, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIsNull() {
            addCriterion("OPER_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIsNotNull() {
            addCriterion("OPER_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME =", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME <>", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameGreaterThan(String value) {
            addCriterion("OPER_STATUS_NAME >", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME >=", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLessThan(String value) {
            addCriterion("OPER_STATUS_NAME <", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLessThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME <=", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLike(String value) {
            addCriterion("OPER_STATUS_NAME like", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotLike(String value) {
            addCriterion("OPER_STATUS_NAME not like", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIn(List<String> values) {
            addCriterion("OPER_STATUS_NAME in", values, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotIn(List<String> values) {
            addCriterion("OPER_STATUS_NAME not in", values, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_NAME between", value1, value2, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_NAME not between", value1, value2, "operStatusName");
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

        public Criteria andTypeNameIsNull() {
            addCriterion("TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNotNull() {
            addCriterion("TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNameEqualTo(String value) {
            addCriterion("TYPE_NAME =", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotEqualTo(String value) {
            addCriterion("TYPE_NAME <>", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThan(String value) {
            addCriterion("TYPE_NAME >", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_NAME >=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThan(String value) {
            addCriterion("TYPE_NAME <", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThanOrEqualTo(String value) {
            addCriterion("TYPE_NAME <=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLike(String value) {
            addCriterion("TYPE_NAME like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotLike(String value) {
            addCriterion("TYPE_NAME not like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameIn(List<String> values) {
            addCriterion("TYPE_NAME in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotIn(List<String> values) {
            addCriterion("TYPE_NAME not in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameBetween(String value1, String value2) {
            addCriterion("TYPE_NAME between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotBetween(String value1, String value2) {
            addCriterion("TYPE_NAME not between", value1, value2, "typeName");
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