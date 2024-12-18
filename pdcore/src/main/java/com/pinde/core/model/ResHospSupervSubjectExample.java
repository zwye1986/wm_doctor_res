package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResHospSupervSubjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResHospSupervSubjectExample() {
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

        public Criteria andSubjectFlowIsNull() {
            addCriterion("SUBJECT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowIsNotNull() {
            addCriterion("SUBJECT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowEqualTo(String value) {
            addCriterion("SUBJECT_FLOW =", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotEqualTo(String value) {
            addCriterion("SUBJECT_FLOW <>", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowGreaterThan(String value) {
            addCriterion("SUBJECT_FLOW >", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_FLOW >=", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowLessThan(String value) {
            addCriterion("SUBJECT_FLOW <", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_FLOW <=", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowLike(String value) {
            addCriterion("SUBJECT_FLOW like", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotLike(String value) {
            addCriterion("SUBJECT_FLOW not like", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowIn(List<String> values) {
            addCriterion("SUBJECT_FLOW in", values, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotIn(List<String> values) {
            addCriterion("SUBJECT_FLOW not in", values, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowBetween(String value1, String value2) {
            addCriterion("SUBJECT_FLOW between", value1, value2, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_FLOW not between", value1, value2, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIsNull() {
            addCriterion("SUBJECT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIsNotNull() {
            addCriterion("SUBJECT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectNameEqualTo(String value) {
            addCriterion("SUBJECT_NAME =", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotEqualTo(String value) {
            addCriterion("SUBJECT_NAME <>", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameGreaterThan(String value) {
            addCriterion("SUBJECT_NAME >", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_NAME >=", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLessThan(String value) {
            addCriterion("SUBJECT_NAME <", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_NAME <=", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLike(String value) {
            addCriterion("SUBJECT_NAME like", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotLike(String value) {
            addCriterion("SUBJECT_NAME not like", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIn(List<String> values) {
            addCriterion("SUBJECT_NAME in", values, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotIn(List<String> values) {
            addCriterion("SUBJECT_NAME not in", values, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameBetween(String value1, String value2) {
            addCriterion("SUBJECT_NAME between", value1, value2, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_NAME not between", value1, value2, "subjectName");
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

        public Criteria andBaseCodeIsNull() {
            addCriterion("BASE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andBaseCodeIsNotNull() {
            addCriterion("BASE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andBaseCodeEqualTo(String value) {
            addCriterion("BASE_CODE =", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeNotEqualTo(String value) {
            addCriterion("BASE_CODE <>", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeGreaterThan(String value) {
            addCriterion("BASE_CODE >", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_CODE >=", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeLessThan(String value) {
            addCriterion("BASE_CODE <", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeLessThanOrEqualTo(String value) {
            addCriterion("BASE_CODE <=", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeLike(String value) {
            addCriterion("BASE_CODE like", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeNotLike(String value) {
            addCriterion("BASE_CODE not like", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeIn(List<String> values) {
            addCriterion("BASE_CODE in", values, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeNotIn(List<String> values) {
            addCriterion("BASE_CODE not in", values, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeBetween(String value1, String value2) {
            addCriterion("BASE_CODE between", value1, value2, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeNotBetween(String value1, String value2) {
            addCriterion("BASE_CODE not between", value1, value2, "baseCode");
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

        public Criteria andOrderActionIsNull() {
            addCriterion("ORDER_ACTION is null");
            return (Criteria) this;
        }

        public Criteria andOrderActionIsNotNull() {
            addCriterion("ORDER_ACTION is not null");
            return (Criteria) this;
        }

        public Criteria andOrderActionEqualTo(String value) {
            addCriterion("ORDER_ACTION =", value, "orderAction");
            return (Criteria) this;
        }

        public Criteria andOrderActionNotEqualTo(String value) {
            addCriterion("ORDER_ACTION <>", value, "orderAction");
            return (Criteria) this;
        }

        public Criteria andOrderActionGreaterThan(String value) {
            addCriterion("ORDER_ACTION >", value, "orderAction");
            return (Criteria) this;
        }

        public Criteria andOrderActionGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_ACTION >=", value, "orderAction");
            return (Criteria) this;
        }

        public Criteria andOrderActionLessThan(String value) {
            addCriterion("ORDER_ACTION <", value, "orderAction");
            return (Criteria) this;
        }

        public Criteria andOrderActionLessThanOrEqualTo(String value) {
            addCriterion("ORDER_ACTION <=", value, "orderAction");
            return (Criteria) this;
        }

        public Criteria andOrderActionLike(String value) {
            addCriterion("ORDER_ACTION like", value, "orderAction");
            return (Criteria) this;
        }

        public Criteria andOrderActionNotLike(String value) {
            addCriterion("ORDER_ACTION not like", value, "orderAction");
            return (Criteria) this;
        }

        public Criteria andOrderActionIn(List<String> values) {
            addCriterion("ORDER_ACTION in", values, "orderAction");
            return (Criteria) this;
        }

        public Criteria andOrderActionNotIn(List<String> values) {
            addCriterion("ORDER_ACTION not in", values, "orderAction");
            return (Criteria) this;
        }

        public Criteria andOrderActionBetween(String value1, String value2) {
            addCriterion("ORDER_ACTION between", value1, value2, "orderAction");
            return (Criteria) this;
        }

        public Criteria andOrderActionNotBetween(String value1, String value2) {
            addCriterion("ORDER_ACTION not between", value1, value2, "orderAction");
            return (Criteria) this;
        }

        public Criteria andLeaderOneNameIsNull() {
            addCriterion("LEADER_ONE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLeaderOneNameIsNotNull() {
            addCriterion("LEADER_ONE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderOneNameEqualTo(String value) {
            addCriterion("LEADER_ONE_NAME =", value, "leaderOneName");
            return (Criteria) this;
        }

        public Criteria andLeaderOneNameNotEqualTo(String value) {
            addCriterion("LEADER_ONE_NAME <>", value, "leaderOneName");
            return (Criteria) this;
        }

        public Criteria andLeaderOneNameGreaterThan(String value) {
            addCriterion("LEADER_ONE_NAME >", value, "leaderOneName");
            return (Criteria) this;
        }

        public Criteria andLeaderOneNameGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_ONE_NAME >=", value, "leaderOneName");
            return (Criteria) this;
        }

        public Criteria andLeaderOneNameLessThan(String value) {
            addCriterion("LEADER_ONE_NAME <", value, "leaderOneName");
            return (Criteria) this;
        }

        public Criteria andLeaderOneNameLessThanOrEqualTo(String value) {
            addCriterion("LEADER_ONE_NAME <=", value, "leaderOneName");
            return (Criteria) this;
        }

        public Criteria andLeaderOneNameLike(String value) {
            addCriterion("LEADER_ONE_NAME like", value, "leaderOneName");
            return (Criteria) this;
        }

        public Criteria andLeaderOneNameNotLike(String value) {
            addCriterion("LEADER_ONE_NAME not like", value, "leaderOneName");
            return (Criteria) this;
        }

        public Criteria andLeaderOneNameIn(List<String> values) {
            addCriterion("LEADER_ONE_NAME in", values, "leaderOneName");
            return (Criteria) this;
        }

        public Criteria andLeaderOneNameNotIn(List<String> values) {
            addCriterion("LEADER_ONE_NAME not in", values, "leaderOneName");
            return (Criteria) this;
        }

        public Criteria andLeaderOneNameBetween(String value1, String value2) {
            addCriterion("LEADER_ONE_NAME between", value1, value2, "leaderOneName");
            return (Criteria) this;
        }

        public Criteria andLeaderOneNameNotBetween(String value1, String value2) {
            addCriterion("LEADER_ONE_NAME not between", value1, value2, "leaderOneName");
            return (Criteria) this;
        }

        public Criteria andLeaderOneFlowIsNull() {
            addCriterion("LEADER_ONE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andLeaderOneFlowIsNotNull() {
            addCriterion("LEADER_ONE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderOneFlowEqualTo(String value) {
            addCriterion("LEADER_ONE_FLOW =", value, "leaderOneFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOneFlowNotEqualTo(String value) {
            addCriterion("LEADER_ONE_FLOW <>", value, "leaderOneFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOneFlowGreaterThan(String value) {
            addCriterion("LEADER_ONE_FLOW >", value, "leaderOneFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOneFlowGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_ONE_FLOW >=", value, "leaderOneFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOneFlowLessThan(String value) {
            addCriterion("LEADER_ONE_FLOW <", value, "leaderOneFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOneFlowLessThanOrEqualTo(String value) {
            addCriterion("LEADER_ONE_FLOW <=", value, "leaderOneFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOneFlowLike(String value) {
            addCriterion("LEADER_ONE_FLOW like", value, "leaderOneFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOneFlowNotLike(String value) {
            addCriterion("LEADER_ONE_FLOW not like", value, "leaderOneFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOneFlowIn(List<String> values) {
            addCriterion("LEADER_ONE_FLOW in", values, "leaderOneFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOneFlowNotIn(List<String> values) {
            addCriterion("LEADER_ONE_FLOW not in", values, "leaderOneFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOneFlowBetween(String value1, String value2) {
            addCriterion("LEADER_ONE_FLOW between", value1, value2, "leaderOneFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOneFlowNotBetween(String value1, String value2) {
            addCriterion("LEADER_ONE_FLOW not between", value1, value2, "leaderOneFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoNameIsNull() {
            addCriterion("LEADER_TWO_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoNameIsNotNull() {
            addCriterion("LEADER_TWO_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoNameEqualTo(String value) {
            addCriterion("LEADER_TWO_NAME =", value, "leaderTwoName");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoNameNotEqualTo(String value) {
            addCriterion("LEADER_TWO_NAME <>", value, "leaderTwoName");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoNameGreaterThan(String value) {
            addCriterion("LEADER_TWO_NAME >", value, "leaderTwoName");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoNameGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_TWO_NAME >=", value, "leaderTwoName");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoNameLessThan(String value) {
            addCriterion("LEADER_TWO_NAME <", value, "leaderTwoName");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoNameLessThanOrEqualTo(String value) {
            addCriterion("LEADER_TWO_NAME <=", value, "leaderTwoName");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoNameLike(String value) {
            addCriterion("LEADER_TWO_NAME like", value, "leaderTwoName");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoNameNotLike(String value) {
            addCriterion("LEADER_TWO_NAME not like", value, "leaderTwoName");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoNameIn(List<String> values) {
            addCriterion("LEADER_TWO_NAME in", values, "leaderTwoName");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoNameNotIn(List<String> values) {
            addCriterion("LEADER_TWO_NAME not in", values, "leaderTwoName");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoNameBetween(String value1, String value2) {
            addCriterion("LEADER_TWO_NAME between", value1, value2, "leaderTwoName");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoNameNotBetween(String value1, String value2) {
            addCriterion("LEADER_TWO_NAME not between", value1, value2, "leaderTwoName");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoFlowIsNull() {
            addCriterion("LEADER_TWO_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoFlowIsNotNull() {
            addCriterion("LEADER_TWO_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoFlowEqualTo(String value) {
            addCriterion("LEADER_TWO_FLOW =", value, "leaderTwoFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoFlowNotEqualTo(String value) {
            addCriterion("LEADER_TWO_FLOW <>", value, "leaderTwoFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoFlowGreaterThan(String value) {
            addCriterion("LEADER_TWO_FLOW >", value, "leaderTwoFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoFlowGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_TWO_FLOW >=", value, "leaderTwoFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoFlowLessThan(String value) {
            addCriterion("LEADER_TWO_FLOW <", value, "leaderTwoFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoFlowLessThanOrEqualTo(String value) {
            addCriterion("LEADER_TWO_FLOW <=", value, "leaderTwoFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoFlowLike(String value) {
            addCriterion("LEADER_TWO_FLOW like", value, "leaderTwoFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoFlowNotLike(String value) {
            addCriterion("LEADER_TWO_FLOW not like", value, "leaderTwoFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoFlowIn(List<String> values) {
            addCriterion("LEADER_TWO_FLOW in", values, "leaderTwoFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoFlowNotIn(List<String> values) {
            addCriterion("LEADER_TWO_FLOW not in", values, "leaderTwoFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoFlowBetween(String value1, String value2) {
            addCriterion("LEADER_TWO_FLOW between", value1, value2, "leaderTwoFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoFlowNotBetween(String value1, String value2) {
            addCriterion("LEADER_TWO_FLOW not between", value1, value2, "leaderTwoFlow");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNull() {
            addCriterion("ACTIVITY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNotNull() {
            addCriterion("ACTIVITY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andActivityNameEqualTo(String value) {
            addCriterion("ACTIVITY_NAME =", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotEqualTo(String value) {
            addCriterion("ACTIVITY_NAME <>", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThan(String value) {
            addCriterion("ACTIVITY_NAME >", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_NAME >=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThan(String value) {
            addCriterion("ACTIVITY_NAME <", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_NAME <=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLike(String value) {
            addCriterion("ACTIVITY_NAME like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotLike(String value) {
            addCriterion("ACTIVITY_NAME not like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameIn(List<String> values) {
            addCriterion("ACTIVITY_NAME in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotIn(List<String> values) {
            addCriterion("ACTIVITY_NAME not in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameBetween(String value1, String value2) {
            addCriterion("ACTIVITY_NAME between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotBetween(String value1, String value2) {
            addCriterion("ACTIVITY_NAME not between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityFlowIsNull() {
            addCriterion("ACTIVITY_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andActivityFlowIsNotNull() {
            addCriterion("ACTIVITY_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andActivityFlowEqualTo(String value) {
            addCriterion("ACTIVITY_FLOW =", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowNotEqualTo(String value) {
            addCriterion("ACTIVITY_FLOW <>", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowGreaterThan(String value) {
            addCriterion("ACTIVITY_FLOW >", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_FLOW >=", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowLessThan(String value) {
            addCriterion("ACTIVITY_FLOW <", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowLessThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_FLOW <=", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowLike(String value) {
            addCriterion("ACTIVITY_FLOW like", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowNotLike(String value) {
            addCriterion("ACTIVITY_FLOW not like", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowIn(List<String> values) {
            addCriterion("ACTIVITY_FLOW in", values, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowNotIn(List<String> values) {
            addCriterion("ACTIVITY_FLOW not in", values, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowBetween(String value1, String value2) {
            addCriterion("ACTIVITY_FLOW between", value1, value2, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowNotBetween(String value1, String value2) {
            addCriterion("ACTIVITY_FLOW not between", value1, value2, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andScoreTableIsNull() {
            addCriterion("SCORE_TABLE is null");
            return (Criteria) this;
        }

        public Criteria andScoreTableIsNotNull() {
            addCriterion("SCORE_TABLE is not null");
            return (Criteria) this;
        }

        public Criteria andScoreTableEqualTo(String value) {
            addCriterion("SCORE_TABLE =", value, "scoreTable");
            return (Criteria) this;
        }

        public Criteria andScoreTableNotEqualTo(String value) {
            addCriterion("SCORE_TABLE <>", value, "scoreTable");
            return (Criteria) this;
        }

        public Criteria andScoreTableGreaterThan(String value) {
            addCriterion("SCORE_TABLE >", value, "scoreTable");
            return (Criteria) this;
        }

        public Criteria andScoreTableGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_TABLE >=", value, "scoreTable");
            return (Criteria) this;
        }

        public Criteria andScoreTableLessThan(String value) {
            addCriterion("SCORE_TABLE <", value, "scoreTable");
            return (Criteria) this;
        }

        public Criteria andScoreTableLessThanOrEqualTo(String value) {
            addCriterion("SCORE_TABLE <=", value, "scoreTable");
            return (Criteria) this;
        }

        public Criteria andScoreTableLike(String value) {
            addCriterion("SCORE_TABLE like", value, "scoreTable");
            return (Criteria) this;
        }

        public Criteria andScoreTableNotLike(String value) {
            addCriterion("SCORE_TABLE not like", value, "scoreTable");
            return (Criteria) this;
        }

        public Criteria andScoreTableIn(List<String> values) {
            addCriterion("SCORE_TABLE in", values, "scoreTable");
            return (Criteria) this;
        }

        public Criteria andScoreTableNotIn(List<String> values) {
            addCriterion("SCORE_TABLE not in", values, "scoreTable");
            return (Criteria) this;
        }

        public Criteria andScoreTableBetween(String value1, String value2) {
            addCriterion("SCORE_TABLE between", value1, value2, "scoreTable");
            return (Criteria) this;
        }

        public Criteria andScoreTableNotBetween(String value1, String value2) {
            addCriterion("SCORE_TABLE not between", value1, value2, "scoreTable");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(String value) {
            addCriterion("START_TIME =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(String value) {
            addCriterion("START_TIME <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(String value) {
            addCriterion("START_TIME >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("START_TIME >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(String value) {
            addCriterion("START_TIME <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(String value) {
            addCriterion("START_TIME <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLike(String value) {
            addCriterion("START_TIME like", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotLike(String value) {
            addCriterion("START_TIME not like", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<String> values) {
            addCriterion("START_TIME in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<String> values) {
            addCriterion("START_TIME not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(String value1, String value2) {
            addCriterion("START_TIME between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(String value1, String value2) {
            addCriterion("START_TIME not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(String value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(String value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(String value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(String value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(String value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLike(String value) {
            addCriterion("END_TIME like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotLike(String value) {
            addCriterion("END_TIME not like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<String> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<String> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(String value1, String value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(String value1, String value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
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

        public Criteria andSubjectYearIsNull() {
            addCriterion("SUBJECT_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andSubjectYearIsNotNull() {
            addCriterion("SUBJECT_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectYearEqualTo(String value) {
            addCriterion("SUBJECT_YEAR =", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearNotEqualTo(String value) {
            addCriterion("SUBJECT_YEAR <>", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearGreaterThan(String value) {
            addCriterion("SUBJECT_YEAR >", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_YEAR >=", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearLessThan(String value) {
            addCriterion("SUBJECT_YEAR <", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_YEAR <=", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearLike(String value) {
            addCriterion("SUBJECT_YEAR like", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearNotLike(String value) {
            addCriterion("SUBJECT_YEAR not like", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearIn(List<String> values) {
            addCriterion("SUBJECT_YEAR in", values, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearNotIn(List<String> values) {
            addCriterion("SUBJECT_YEAR not in", values, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearBetween(String value1, String value2) {
            addCriterion("SUBJECT_YEAR between", value1, value2, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_YEAR not between", value1, value2, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andInspectionTypeIsNull() {
            addCriterion("INSPECTION_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andInspectionTypeIsNotNull() {
            addCriterion("INSPECTION_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andInspectionTypeEqualTo(String value) {
            addCriterion("INSPECTION_TYPE =", value, "inspectionType");
            return (Criteria) this;
        }

        public Criteria andInspectionTypeNotEqualTo(String value) {
            addCriterion("INSPECTION_TYPE <>", value, "inspectionType");
            return (Criteria) this;
        }

        public Criteria andInspectionTypeGreaterThan(String value) {
            addCriterion("INSPECTION_TYPE >", value, "inspectionType");
            return (Criteria) this;
        }

        public Criteria andInspectionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("INSPECTION_TYPE >=", value, "inspectionType");
            return (Criteria) this;
        }

        public Criteria andInspectionTypeLessThan(String value) {
            addCriterion("INSPECTION_TYPE <", value, "inspectionType");
            return (Criteria) this;
        }

        public Criteria andInspectionTypeLessThanOrEqualTo(String value) {
            addCriterion("INSPECTION_TYPE <=", value, "inspectionType");
            return (Criteria) this;
        }

        public Criteria andInspectionTypeLike(String value) {
            addCriterion("INSPECTION_TYPE like", value, "inspectionType");
            return (Criteria) this;
        }

        public Criteria andInspectionTypeNotLike(String value) {
            addCriterion("INSPECTION_TYPE not like", value, "inspectionType");
            return (Criteria) this;
        }

        public Criteria andInspectionTypeIn(List<String> values) {
            addCriterion("INSPECTION_TYPE in", values, "inspectionType");
            return (Criteria) this;
        }

        public Criteria andInspectionTypeNotIn(List<String> values) {
            addCriterion("INSPECTION_TYPE not in", values, "inspectionType");
            return (Criteria) this;
        }

        public Criteria andInspectionTypeBetween(String value1, String value2) {
            addCriterion("INSPECTION_TYPE between", value1, value2, "inspectionType");
            return (Criteria) this;
        }

        public Criteria andInspectionTypeNotBetween(String value1, String value2) {
            addCriterion("INSPECTION_TYPE not between", value1, value2, "inspectionType");
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

        public Criteria andTeachFlowIsNull() {
            addCriterion("TEACH_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTeachFlowIsNotNull() {
            addCriterion("TEACH_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTeachFlowEqualTo(String value) {
            addCriterion("TEACH_FLOW =", value, "teachFlow");
            return (Criteria) this;
        }

        public Criteria andTeachFlowNotEqualTo(String value) {
            addCriterion("TEACH_FLOW <>", value, "teachFlow");
            return (Criteria) this;
        }

        public Criteria andTeachFlowGreaterThan(String value) {
            addCriterion("TEACH_FLOW >", value, "teachFlow");
            return (Criteria) this;
        }

        public Criteria andTeachFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TEACH_FLOW >=", value, "teachFlow");
            return (Criteria) this;
        }

        public Criteria andTeachFlowLessThan(String value) {
            addCriterion("TEACH_FLOW <", value, "teachFlow");
            return (Criteria) this;
        }

        public Criteria andTeachFlowLessThanOrEqualTo(String value) {
            addCriterion("TEACH_FLOW <=", value, "teachFlow");
            return (Criteria) this;
        }

        public Criteria andTeachFlowLike(String value) {
            addCriterion("TEACH_FLOW like", value, "teachFlow");
            return (Criteria) this;
        }

        public Criteria andTeachFlowNotLike(String value) {
            addCriterion("TEACH_FLOW not like", value, "teachFlow");
            return (Criteria) this;
        }

        public Criteria andTeachFlowIn(List<String> values) {
            addCriterion("TEACH_FLOW in", values, "teachFlow");
            return (Criteria) this;
        }

        public Criteria andTeachFlowNotIn(List<String> values) {
            addCriterion("TEACH_FLOW not in", values, "teachFlow");
            return (Criteria) this;
        }

        public Criteria andTeachFlowBetween(String value1, String value2) {
            addCriterion("TEACH_FLOW between", value1, value2, "teachFlow");
            return (Criteria) this;
        }

        public Criteria andTeachFlowNotBetween(String value1, String value2) {
            addCriterion("TEACH_FLOW not between", value1, value2, "teachFlow");
            return (Criteria) this;
        }

        public Criteria andTeachNameIsNull() {
            addCriterion("TEACH_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTeachNameIsNotNull() {
            addCriterion("TEACH_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTeachNameEqualTo(String value) {
            addCriterion("TEACH_NAME =", value, "teachName");
            return (Criteria) this;
        }

        public Criteria andTeachNameNotEqualTo(String value) {
            addCriterion("TEACH_NAME <>", value, "teachName");
            return (Criteria) this;
        }

        public Criteria andTeachNameGreaterThan(String value) {
            addCriterion("TEACH_NAME >", value, "teachName");
            return (Criteria) this;
        }

        public Criteria andTeachNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEACH_NAME >=", value, "teachName");
            return (Criteria) this;
        }

        public Criteria andTeachNameLessThan(String value) {
            addCriterion("TEACH_NAME <", value, "teachName");
            return (Criteria) this;
        }

        public Criteria andTeachNameLessThanOrEqualTo(String value) {
            addCriterion("TEACH_NAME <=", value, "teachName");
            return (Criteria) this;
        }

        public Criteria andTeachNameLike(String value) {
            addCriterion("TEACH_NAME like", value, "teachName");
            return (Criteria) this;
        }

        public Criteria andTeachNameNotLike(String value) {
            addCriterion("TEACH_NAME not like", value, "teachName");
            return (Criteria) this;
        }

        public Criteria andTeachNameIn(List<String> values) {
            addCriterion("TEACH_NAME in", values, "teachName");
            return (Criteria) this;
        }

        public Criteria andTeachNameNotIn(List<String> values) {
            addCriterion("TEACH_NAME not in", values, "teachName");
            return (Criteria) this;
        }

        public Criteria andTeachNameBetween(String value1, String value2) {
            addCriterion("TEACH_NAME between", value1, value2, "teachName");
            return (Criteria) this;
        }

        public Criteria andTeachNameNotBetween(String value1, String value2) {
            addCriterion("TEACH_NAME not between", value1, value2, "teachName");
            return (Criteria) this;
        }

        public Criteria andLeaderOneScoreIsNull() {
            addCriterion("LEADER_ONE_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andLeaderOneScoreIsNotNull() {
            addCriterion("LEADER_ONE_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderOneScoreEqualTo(String value) {
            addCriterion("LEADER_ONE_SCORE =", value, "leaderOneScore");
            return (Criteria) this;
        }

        public Criteria andLeaderOneScoreNotEqualTo(String value) {
            addCriterion("LEADER_ONE_SCORE <>", value, "leaderOneScore");
            return (Criteria) this;
        }

        public Criteria andLeaderOneScoreGreaterThan(String value) {
            addCriterion("LEADER_ONE_SCORE >", value, "leaderOneScore");
            return (Criteria) this;
        }

        public Criteria andLeaderOneScoreGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_ONE_SCORE >=", value, "leaderOneScore");
            return (Criteria) this;
        }

        public Criteria andLeaderOneScoreLessThan(String value) {
            addCriterion("LEADER_ONE_SCORE <", value, "leaderOneScore");
            return (Criteria) this;
        }

        public Criteria andLeaderOneScoreLessThanOrEqualTo(String value) {
            addCriterion("LEADER_ONE_SCORE <=", value, "leaderOneScore");
            return (Criteria) this;
        }

        public Criteria andLeaderOneScoreLike(String value) {
            addCriterion("LEADER_ONE_SCORE like", value, "leaderOneScore");
            return (Criteria) this;
        }

        public Criteria andLeaderOneScoreNotLike(String value) {
            addCriterion("LEADER_ONE_SCORE not like", value, "leaderOneScore");
            return (Criteria) this;
        }

        public Criteria andLeaderOneScoreIn(List<String> values) {
            addCriterion("LEADER_ONE_SCORE in", values, "leaderOneScore");
            return (Criteria) this;
        }

        public Criteria andLeaderOneScoreNotIn(List<String> values) {
            addCriterion("LEADER_ONE_SCORE not in", values, "leaderOneScore");
            return (Criteria) this;
        }

        public Criteria andLeaderOneScoreBetween(String value1, String value2) {
            addCriterion("LEADER_ONE_SCORE between", value1, value2, "leaderOneScore");
            return (Criteria) this;
        }

        public Criteria andLeaderOneScoreNotBetween(String value1, String value2) {
            addCriterion("LEADER_ONE_SCORE not between", value1, value2, "leaderOneScore");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoScoreIsNull() {
            addCriterion("LEADER_TWO_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoScoreIsNotNull() {
            addCriterion("LEADER_TWO_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoScoreEqualTo(String value) {
            addCriterion("LEADER_TWO_SCORE =", value, "leaderTwoScore");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoScoreNotEqualTo(String value) {
            addCriterion("LEADER_TWO_SCORE <>", value, "leaderTwoScore");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoScoreGreaterThan(String value) {
            addCriterion("LEADER_TWO_SCORE >", value, "leaderTwoScore");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoScoreGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_TWO_SCORE >=", value, "leaderTwoScore");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoScoreLessThan(String value) {
            addCriterion("LEADER_TWO_SCORE <", value, "leaderTwoScore");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoScoreLessThanOrEqualTo(String value) {
            addCriterion("LEADER_TWO_SCORE <=", value, "leaderTwoScore");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoScoreLike(String value) {
            addCriterion("LEADER_TWO_SCORE like", value, "leaderTwoScore");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoScoreNotLike(String value) {
            addCriterion("LEADER_TWO_SCORE not like", value, "leaderTwoScore");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoScoreIn(List<String> values) {
            addCriterion("LEADER_TWO_SCORE in", values, "leaderTwoScore");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoScoreNotIn(List<String> values) {
            addCriterion("LEADER_TWO_SCORE not in", values, "leaderTwoScore");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoScoreBetween(String value1, String value2) {
            addCriterion("LEADER_TWO_SCORE between", value1, value2, "leaderTwoScore");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoScoreNotBetween(String value1, String value2) {
            addCriterion("LEADER_TWO_SCORE not between", value1, value2, "leaderTwoScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreIsNull() {
            addCriterion("AVG_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andAvgScoreIsNotNull() {
            addCriterion("AVG_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andAvgScoreEqualTo(String value) {
            addCriterion("AVG_SCORE =", value, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreNotEqualTo(String value) {
            addCriterion("AVG_SCORE <>", value, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreGreaterThan(String value) {
            addCriterion("AVG_SCORE >", value, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_SCORE >=", value, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreLessThan(String value) {
            addCriterion("AVG_SCORE <", value, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreLessThanOrEqualTo(String value) {
            addCriterion("AVG_SCORE <=", value, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreLike(String value) {
            addCriterion("AVG_SCORE like", value, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreNotLike(String value) {
            addCriterion("AVG_SCORE not like", value, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreIn(List<String> values) {
            addCriterion("AVG_SCORE in", values, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreNotIn(List<String> values) {
            addCriterion("AVG_SCORE not in", values, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreBetween(String value1, String value2) {
            addCriterion("AVG_SCORE between", value1, value2, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreNotBetween(String value1, String value2) {
            addCriterion("AVG_SCORE not between", value1, value2, "avgScore");
            return (Criteria) this;
        }

        public Criteria andLeaderOneStartTimeIsNull() {
            addCriterion("LEADER_ONE_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLeaderOneStartTimeIsNotNull() {
            addCriterion("LEADER_ONE_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderOneStartTimeEqualTo(String value) {
            addCriterion("LEADER_ONE_START_TIME =", value, "leaderOneStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneStartTimeNotEqualTo(String value) {
            addCriterion("LEADER_ONE_START_TIME <>", value, "leaderOneStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneStartTimeGreaterThan(String value) {
            addCriterion("LEADER_ONE_START_TIME >", value, "leaderOneStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_ONE_START_TIME >=", value, "leaderOneStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneStartTimeLessThan(String value) {
            addCriterion("LEADER_ONE_START_TIME <", value, "leaderOneStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneStartTimeLessThanOrEqualTo(String value) {
            addCriterion("LEADER_ONE_START_TIME <=", value, "leaderOneStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneStartTimeLike(String value) {
            addCriterion("LEADER_ONE_START_TIME like", value, "leaderOneStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneStartTimeNotLike(String value) {
            addCriterion("LEADER_ONE_START_TIME not like", value, "leaderOneStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneStartTimeIn(List<String> values) {
            addCriterion("LEADER_ONE_START_TIME in", values, "leaderOneStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneStartTimeNotIn(List<String> values) {
            addCriterion("LEADER_ONE_START_TIME not in", values, "leaderOneStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneStartTimeBetween(String value1, String value2) {
            addCriterion("LEADER_ONE_START_TIME between", value1, value2, "leaderOneStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneStartTimeNotBetween(String value1, String value2) {
            addCriterion("LEADER_ONE_START_TIME not between", value1, value2, "leaderOneStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneEndTimeIsNull() {
            addCriterion("LEADER_ONE_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLeaderOneEndTimeIsNotNull() {
            addCriterion("LEADER_ONE_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderOneEndTimeEqualTo(String value) {
            addCriterion("LEADER_ONE_END_TIME =", value, "leaderOneEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneEndTimeNotEqualTo(String value) {
            addCriterion("LEADER_ONE_END_TIME <>", value, "leaderOneEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneEndTimeGreaterThan(String value) {
            addCriterion("LEADER_ONE_END_TIME >", value, "leaderOneEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_ONE_END_TIME >=", value, "leaderOneEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneEndTimeLessThan(String value) {
            addCriterion("LEADER_ONE_END_TIME <", value, "leaderOneEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneEndTimeLessThanOrEqualTo(String value) {
            addCriterion("LEADER_ONE_END_TIME <=", value, "leaderOneEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneEndTimeLike(String value) {
            addCriterion("LEADER_ONE_END_TIME like", value, "leaderOneEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneEndTimeNotLike(String value) {
            addCriterion("LEADER_ONE_END_TIME not like", value, "leaderOneEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneEndTimeIn(List<String> values) {
            addCriterion("LEADER_ONE_END_TIME in", values, "leaderOneEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneEndTimeNotIn(List<String> values) {
            addCriterion("LEADER_ONE_END_TIME not in", values, "leaderOneEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneEndTimeBetween(String value1, String value2) {
            addCriterion("LEADER_ONE_END_TIME between", value1, value2, "leaderOneEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderOneEndTimeNotBetween(String value1, String value2) {
            addCriterion("LEADER_ONE_END_TIME not between", value1, value2, "leaderOneEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoStartTimeIsNull() {
            addCriterion("LEADER_TWO_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoStartTimeIsNotNull() {
            addCriterion("LEADER_TWO_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoStartTimeEqualTo(String value) {
            addCriterion("LEADER_TWO_START_TIME =", value, "leaderTwoStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoStartTimeNotEqualTo(String value) {
            addCriterion("LEADER_TWO_START_TIME <>", value, "leaderTwoStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoStartTimeGreaterThan(String value) {
            addCriterion("LEADER_TWO_START_TIME >", value, "leaderTwoStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_TWO_START_TIME >=", value, "leaderTwoStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoStartTimeLessThan(String value) {
            addCriterion("LEADER_TWO_START_TIME <", value, "leaderTwoStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoStartTimeLessThanOrEqualTo(String value) {
            addCriterion("LEADER_TWO_START_TIME <=", value, "leaderTwoStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoStartTimeLike(String value) {
            addCriterion("LEADER_TWO_START_TIME like", value, "leaderTwoStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoStartTimeNotLike(String value) {
            addCriterion("LEADER_TWO_START_TIME not like", value, "leaderTwoStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoStartTimeIn(List<String> values) {
            addCriterion("LEADER_TWO_START_TIME in", values, "leaderTwoStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoStartTimeNotIn(List<String> values) {
            addCriterion("LEADER_TWO_START_TIME not in", values, "leaderTwoStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoStartTimeBetween(String value1, String value2) {
            addCriterion("LEADER_TWO_START_TIME between", value1, value2, "leaderTwoStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoStartTimeNotBetween(String value1, String value2) {
            addCriterion("LEADER_TWO_START_TIME not between", value1, value2, "leaderTwoStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoEndTimeIsNull() {
            addCriterion("LEADER_TWO_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoEndTimeIsNotNull() {
            addCriterion("LEADER_TWO_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoEndTimeEqualTo(String value) {
            addCriterion("LEADER_TWO_END_TIME =", value, "leaderTwoEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoEndTimeNotEqualTo(String value) {
            addCriterion("LEADER_TWO_END_TIME <>", value, "leaderTwoEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoEndTimeGreaterThan(String value) {
            addCriterion("LEADER_TWO_END_TIME >", value, "leaderTwoEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_TWO_END_TIME >=", value, "leaderTwoEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoEndTimeLessThan(String value) {
            addCriterion("LEADER_TWO_END_TIME <", value, "leaderTwoEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoEndTimeLessThanOrEqualTo(String value) {
            addCriterion("LEADER_TWO_END_TIME <=", value, "leaderTwoEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoEndTimeLike(String value) {
            addCriterion("LEADER_TWO_END_TIME like", value, "leaderTwoEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoEndTimeNotLike(String value) {
            addCriterion("LEADER_TWO_END_TIME not like", value, "leaderTwoEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoEndTimeIn(List<String> values) {
            addCriterion("LEADER_TWO_END_TIME in", values, "leaderTwoEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoEndTimeNotIn(List<String> values) {
            addCriterion("LEADER_TWO_END_TIME not in", values, "leaderTwoEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoEndTimeBetween(String value1, String value2) {
            addCriterion("LEADER_TWO_END_TIME between", value1, value2, "leaderTwoEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderTwoEndTimeNotBetween(String value1, String value2) {
            addCriterion("LEADER_TWO_END_TIME not between", value1, value2, "leaderTwoEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderSubNumIsNull() {
            addCriterion("LEADER_SUB_NUM is null");
            return (Criteria) this;
        }

        public Criteria andLeaderSubNumIsNotNull() {
            addCriterion("LEADER_SUB_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderSubNumEqualTo(String value) {
            addCriterion("LEADER_SUB_NUM =", value, "leaderSubNum");
            return (Criteria) this;
        }

        public Criteria andLeaderSubNumNotEqualTo(String value) {
            addCriterion("LEADER_SUB_NUM <>", value, "leaderSubNum");
            return (Criteria) this;
        }

        public Criteria andLeaderSubNumGreaterThan(String value) {
            addCriterion("LEADER_SUB_NUM >", value, "leaderSubNum");
            return (Criteria) this;
        }

        public Criteria andLeaderSubNumGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_SUB_NUM >=", value, "leaderSubNum");
            return (Criteria) this;
        }

        public Criteria andLeaderSubNumLessThan(String value) {
            addCriterion("LEADER_SUB_NUM <", value, "leaderSubNum");
            return (Criteria) this;
        }

        public Criteria andLeaderSubNumLessThanOrEqualTo(String value) {
            addCriterion("LEADER_SUB_NUM <=", value, "leaderSubNum");
            return (Criteria) this;
        }

        public Criteria andLeaderSubNumLike(String value) {
            addCriterion("LEADER_SUB_NUM like", value, "leaderSubNum");
            return (Criteria) this;
        }

        public Criteria andLeaderSubNumNotLike(String value) {
            addCriterion("LEADER_SUB_NUM not like", value, "leaderSubNum");
            return (Criteria) this;
        }

        public Criteria andLeaderSubNumIn(List<String> values) {
            addCriterion("LEADER_SUB_NUM in", values, "leaderSubNum");
            return (Criteria) this;
        }

        public Criteria andLeaderSubNumNotIn(List<String> values) {
            addCriterion("LEADER_SUB_NUM not in", values, "leaderSubNum");
            return (Criteria) this;
        }

        public Criteria andLeaderSubNumBetween(String value1, String value2) {
            addCriterion("LEADER_SUB_NUM between", value1, value2, "leaderSubNum");
            return (Criteria) this;
        }

        public Criteria andLeaderSubNumNotBetween(String value1, String value2) {
            addCriterion("LEADER_SUB_NUM not between", value1, value2, "leaderSubNum");
            return (Criteria) this;
        }

        public Criteria andActualSpeakerIsNull() {
            addCriterion("ACTUAL_SPEAKER is null");
            return (Criteria) this;
        }

        public Criteria andActualSpeakerIsNotNull() {
            addCriterion("ACTUAL_SPEAKER is not null");
            return (Criteria) this;
        }

        public Criteria andActualSpeakerEqualTo(String value) {
            addCriterion("ACTUAL_SPEAKER =", value, "actualSpeaker");
            return (Criteria) this;
        }

        public Criteria andActualSpeakerNotEqualTo(String value) {
            addCriterion("ACTUAL_SPEAKER <>", value, "actualSpeaker");
            return (Criteria) this;
        }

        public Criteria andActualSpeakerGreaterThan(String value) {
            addCriterion("ACTUAL_SPEAKER >", value, "actualSpeaker");
            return (Criteria) this;
        }

        public Criteria andActualSpeakerGreaterThanOrEqualTo(String value) {
            addCriterion("ACTUAL_SPEAKER >=", value, "actualSpeaker");
            return (Criteria) this;
        }

        public Criteria andActualSpeakerLessThan(String value) {
            addCriterion("ACTUAL_SPEAKER <", value, "actualSpeaker");
            return (Criteria) this;
        }

        public Criteria andActualSpeakerLessThanOrEqualTo(String value) {
            addCriterion("ACTUAL_SPEAKER <=", value, "actualSpeaker");
            return (Criteria) this;
        }

        public Criteria andActualSpeakerLike(String value) {
            addCriterion("ACTUAL_SPEAKER like", value, "actualSpeaker");
            return (Criteria) this;
        }

        public Criteria andActualSpeakerNotLike(String value) {
            addCriterion("ACTUAL_SPEAKER not like", value, "actualSpeaker");
            return (Criteria) this;
        }

        public Criteria andActualSpeakerIn(List<String> values) {
            addCriterion("ACTUAL_SPEAKER in", values, "actualSpeaker");
            return (Criteria) this;
        }

        public Criteria andActualSpeakerNotIn(List<String> values) {
            addCriterion("ACTUAL_SPEAKER not in", values, "actualSpeaker");
            return (Criteria) this;
        }

        public Criteria andActualSpeakerBetween(String value1, String value2) {
            addCriterion("ACTUAL_SPEAKER between", value1, value2, "actualSpeaker");
            return (Criteria) this;
        }

        public Criteria andActualSpeakerNotBetween(String value1, String value2) {
            addCriterion("ACTUAL_SPEAKER not between", value1, value2, "actualSpeaker");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeIsNull() {
            addCriterion("ACTIVITY_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeIsNotNull() {
            addCriterion("ACTIVITY_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeEqualTo(String value) {
            addCriterion("ACTIVITY_START_TIME =", value, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeNotEqualTo(String value) {
            addCriterion("ACTIVITY_START_TIME <>", value, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeGreaterThan(String value) {
            addCriterion("ACTIVITY_START_TIME >", value, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_START_TIME >=", value, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeLessThan(String value) {
            addCriterion("ACTIVITY_START_TIME <", value, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeLessThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_START_TIME <=", value, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeLike(String value) {
            addCriterion("ACTIVITY_START_TIME like", value, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeNotLike(String value) {
            addCriterion("ACTIVITY_START_TIME not like", value, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeIn(List<String> values) {
            addCriterion("ACTIVITY_START_TIME in", values, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeNotIn(List<String> values) {
            addCriterion("ACTIVITY_START_TIME not in", values, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeBetween(String value1, String value2) {
            addCriterion("ACTIVITY_START_TIME between", value1, value2, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityStartTimeNotBetween(String value1, String value2) {
            addCriterion("ACTIVITY_START_TIME not between", value1, value2, "activityStartTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeIsNull() {
            addCriterion("ACTIVITY_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeIsNotNull() {
            addCriterion("ACTIVITY_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeEqualTo(String value) {
            addCriterion("ACTIVITY_END_TIME =", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeNotEqualTo(String value) {
            addCriterion("ACTIVITY_END_TIME <>", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeGreaterThan(String value) {
            addCriterion("ACTIVITY_END_TIME >", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_END_TIME >=", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeLessThan(String value) {
            addCriterion("ACTIVITY_END_TIME <", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeLessThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_END_TIME <=", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeLike(String value) {
            addCriterion("ACTIVITY_END_TIME like", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeNotLike(String value) {
            addCriterion("ACTIVITY_END_TIME not like", value, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeIn(List<String> values) {
            addCriterion("ACTIVITY_END_TIME in", values, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeNotIn(List<String> values) {
            addCriterion("ACTIVITY_END_TIME not in", values, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeBetween(String value1, String value2) {
            addCriterion("ACTIVITY_END_TIME between", value1, value2, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andActivityEndTimeNotBetween(String value1, String value2) {
            addCriterion("ACTIVITY_END_TIME not between", value1, value2, "activityEndTime");
            return (Criteria) this;
        }

        public Criteria andMatchingIsNull() {
            addCriterion("MATCHING is null");
            return (Criteria) this;
        }

        public Criteria andMatchingIsNotNull() {
            addCriterion("MATCHING is not null");
            return (Criteria) this;
        }

        public Criteria andMatchingEqualTo(String value) {
            addCriterion("MATCHING =", value, "matching");
            return (Criteria) this;
        }

        public Criteria andMatchingNotEqualTo(String value) {
            addCriterion("MATCHING <>", value, "matching");
            return (Criteria) this;
        }

        public Criteria andMatchingGreaterThan(String value) {
            addCriterion("MATCHING >", value, "matching");
            return (Criteria) this;
        }

        public Criteria andMatchingGreaterThanOrEqualTo(String value) {
            addCriterion("MATCHING >=", value, "matching");
            return (Criteria) this;
        }

        public Criteria andMatchingLessThan(String value) {
            addCriterion("MATCHING <", value, "matching");
            return (Criteria) this;
        }

        public Criteria andMatchingLessThanOrEqualTo(String value) {
            addCriterion("MATCHING <=", value, "matching");
            return (Criteria) this;
        }

        public Criteria andMatchingLike(String value) {
            addCriterion("MATCHING like", value, "matching");
            return (Criteria) this;
        }

        public Criteria andMatchingNotLike(String value) {
            addCriterion("MATCHING not like", value, "matching");
            return (Criteria) this;
        }

        public Criteria andMatchingIn(List<String> values) {
            addCriterion("MATCHING in", values, "matching");
            return (Criteria) this;
        }

        public Criteria andMatchingNotIn(List<String> values) {
            addCriterion("MATCHING not in", values, "matching");
            return (Criteria) this;
        }

        public Criteria andMatchingBetween(String value1, String value2) {
            addCriterion("MATCHING between", value1, value2, "matching");
            return (Criteria) this;
        }

        public Criteria andMatchingNotBetween(String value1, String value2) {
            addCriterion("MATCHING not between", value1, value2, "matching");
            return (Criteria) this;
        }

        public Criteria andReviewConfigIsNull() {
            addCriterion("REVIEW_CONFIG is null");
            return (Criteria) this;
        }

        public Criteria andReviewConfigIsNotNull() {
            addCriterion("REVIEW_CONFIG is not null");
            return (Criteria) this;
        }

        public Criteria andReviewConfigEqualTo(String value) {
            addCriterion("REVIEW_CONFIG =", value, "reviewConfig");
            return (Criteria) this;
        }

        public Criteria andReviewConfigNotEqualTo(String value) {
            addCriterion("REVIEW_CONFIG <>", value, "reviewConfig");
            return (Criteria) this;
        }

        public Criteria andReviewConfigGreaterThan(String value) {
            addCriterion("REVIEW_CONFIG >", value, "reviewConfig");
            return (Criteria) this;
        }

        public Criteria andReviewConfigGreaterThanOrEqualTo(String value) {
            addCriterion("REVIEW_CONFIG >=", value, "reviewConfig");
            return (Criteria) this;
        }

        public Criteria andReviewConfigLessThan(String value) {
            addCriterion("REVIEW_CONFIG <", value, "reviewConfig");
            return (Criteria) this;
        }

        public Criteria andReviewConfigLessThanOrEqualTo(String value) {
            addCriterion("REVIEW_CONFIG <=", value, "reviewConfig");
            return (Criteria) this;
        }

        public Criteria andReviewConfigLike(String value) {
            addCriterion("REVIEW_CONFIG like", value, "reviewConfig");
            return (Criteria) this;
        }

        public Criteria andReviewConfigNotLike(String value) {
            addCriterion("REVIEW_CONFIG not like", value, "reviewConfig");
            return (Criteria) this;
        }

        public Criteria andReviewConfigIn(List<String> values) {
            addCriterion("REVIEW_CONFIG in", values, "reviewConfig");
            return (Criteria) this;
        }

        public Criteria andReviewConfigNotIn(List<String> values) {
            addCriterion("REVIEW_CONFIG not in", values, "reviewConfig");
            return (Criteria) this;
        }

        public Criteria andReviewConfigBetween(String value1, String value2) {
            addCriterion("REVIEW_CONFIG between", value1, value2, "reviewConfig");
            return (Criteria) this;
        }

        public Criteria andReviewConfigNotBetween(String value1, String value2) {
            addCriterion("REVIEW_CONFIG not between", value1, value2, "reviewConfig");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeNameIsNull() {
            addCriterion("LEADER_THREE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeNameIsNotNull() {
            addCriterion("LEADER_THREE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeNameEqualTo(String value) {
            addCriterion("LEADER_THREE_NAME =", value, "leaderThreeName");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeNameNotEqualTo(String value) {
            addCriterion("LEADER_THREE_NAME <>", value, "leaderThreeName");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeNameGreaterThan(String value) {
            addCriterion("LEADER_THREE_NAME >", value, "leaderThreeName");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeNameGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_THREE_NAME >=", value, "leaderThreeName");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeNameLessThan(String value) {
            addCriterion("LEADER_THREE_NAME <", value, "leaderThreeName");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeNameLessThanOrEqualTo(String value) {
            addCriterion("LEADER_THREE_NAME <=", value, "leaderThreeName");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeNameLike(String value) {
            addCriterion("LEADER_THREE_NAME like", value, "leaderThreeName");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeNameNotLike(String value) {
            addCriterion("LEADER_THREE_NAME not like", value, "leaderThreeName");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeNameIn(List<String> values) {
            addCriterion("LEADER_THREE_NAME in", values, "leaderThreeName");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeNameNotIn(List<String> values) {
            addCriterion("LEADER_THREE_NAME not in", values, "leaderThreeName");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeNameBetween(String value1, String value2) {
            addCriterion("LEADER_THREE_NAME between", value1, value2, "leaderThreeName");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeNameNotBetween(String value1, String value2) {
            addCriterion("LEADER_THREE_NAME not between", value1, value2, "leaderThreeName");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeFlowIsNull() {
            addCriterion("LEADER_THREE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeFlowIsNotNull() {
            addCriterion("LEADER_THREE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeFlowEqualTo(String value) {
            addCriterion("LEADER_THREE_FLOW =", value, "leaderThreeFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeFlowNotEqualTo(String value) {
            addCriterion("LEADER_THREE_FLOW <>", value, "leaderThreeFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeFlowGreaterThan(String value) {
            addCriterion("LEADER_THREE_FLOW >", value, "leaderThreeFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeFlowGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_THREE_FLOW >=", value, "leaderThreeFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeFlowLessThan(String value) {
            addCriterion("LEADER_THREE_FLOW <", value, "leaderThreeFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeFlowLessThanOrEqualTo(String value) {
            addCriterion("LEADER_THREE_FLOW <=", value, "leaderThreeFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeFlowLike(String value) {
            addCriterion("LEADER_THREE_FLOW like", value, "leaderThreeFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeFlowNotLike(String value) {
            addCriterion("LEADER_THREE_FLOW not like", value, "leaderThreeFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeFlowIn(List<String> values) {
            addCriterion("LEADER_THREE_FLOW in", values, "leaderThreeFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeFlowNotIn(List<String> values) {
            addCriterion("LEADER_THREE_FLOW not in", values, "leaderThreeFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeFlowBetween(String value1, String value2) {
            addCriterion("LEADER_THREE_FLOW between", value1, value2, "leaderThreeFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeFlowNotBetween(String value1, String value2) {
            addCriterion("LEADER_THREE_FLOW not between", value1, value2, "leaderThreeFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderFourNameIsNull() {
            addCriterion("LEADER_FOUR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLeaderFourNameIsNotNull() {
            addCriterion("LEADER_FOUR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderFourNameEqualTo(String value) {
            addCriterion("LEADER_FOUR_NAME =", value, "leaderFourName");
            return (Criteria) this;
        }

        public Criteria andLeaderFourNameNotEqualTo(String value) {
            addCriterion("LEADER_FOUR_NAME <>", value, "leaderFourName");
            return (Criteria) this;
        }

        public Criteria andLeaderFourNameGreaterThan(String value) {
            addCriterion("LEADER_FOUR_NAME >", value, "leaderFourName");
            return (Criteria) this;
        }

        public Criteria andLeaderFourNameGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_FOUR_NAME >=", value, "leaderFourName");
            return (Criteria) this;
        }

        public Criteria andLeaderFourNameLessThan(String value) {
            addCriterion("LEADER_FOUR_NAME <", value, "leaderFourName");
            return (Criteria) this;
        }

        public Criteria andLeaderFourNameLessThanOrEqualTo(String value) {
            addCriterion("LEADER_FOUR_NAME <=", value, "leaderFourName");
            return (Criteria) this;
        }

        public Criteria andLeaderFourNameLike(String value) {
            addCriterion("LEADER_FOUR_NAME like", value, "leaderFourName");
            return (Criteria) this;
        }

        public Criteria andLeaderFourNameNotLike(String value) {
            addCriterion("LEADER_FOUR_NAME not like", value, "leaderFourName");
            return (Criteria) this;
        }

        public Criteria andLeaderFourNameIn(List<String> values) {
            addCriterion("LEADER_FOUR_NAME in", values, "leaderFourName");
            return (Criteria) this;
        }

        public Criteria andLeaderFourNameNotIn(List<String> values) {
            addCriterion("LEADER_FOUR_NAME not in", values, "leaderFourName");
            return (Criteria) this;
        }

        public Criteria andLeaderFourNameBetween(String value1, String value2) {
            addCriterion("LEADER_FOUR_NAME between", value1, value2, "leaderFourName");
            return (Criteria) this;
        }

        public Criteria andLeaderFourNameNotBetween(String value1, String value2) {
            addCriterion("LEADER_FOUR_NAME not between", value1, value2, "leaderFourName");
            return (Criteria) this;
        }

        public Criteria andLeaderFourFlowIsNull() {
            addCriterion("LEADER_FOUR_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andLeaderFourFlowIsNotNull() {
            addCriterion("LEADER_FOUR_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderFourFlowEqualTo(String value) {
            addCriterion("LEADER_FOUR_FLOW =", value, "leaderFourFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderFourFlowNotEqualTo(String value) {
            addCriterion("LEADER_FOUR_FLOW <>", value, "leaderFourFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderFourFlowGreaterThan(String value) {
            addCriterion("LEADER_FOUR_FLOW >", value, "leaderFourFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderFourFlowGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_FOUR_FLOW >=", value, "leaderFourFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderFourFlowLessThan(String value) {
            addCriterion("LEADER_FOUR_FLOW <", value, "leaderFourFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderFourFlowLessThanOrEqualTo(String value) {
            addCriterion("LEADER_FOUR_FLOW <=", value, "leaderFourFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderFourFlowLike(String value) {
            addCriterion("LEADER_FOUR_FLOW like", value, "leaderFourFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderFourFlowNotLike(String value) {
            addCriterion("LEADER_FOUR_FLOW not like", value, "leaderFourFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderFourFlowIn(List<String> values) {
            addCriterion("LEADER_FOUR_FLOW in", values, "leaderFourFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderFourFlowNotIn(List<String> values) {
            addCriterion("LEADER_FOUR_FLOW not in", values, "leaderFourFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderFourFlowBetween(String value1, String value2) {
            addCriterion("LEADER_FOUR_FLOW between", value1, value2, "leaderFourFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderFourFlowNotBetween(String value1, String value2) {
            addCriterion("LEADER_FOUR_FLOW not between", value1, value2, "leaderFourFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeScoreIsNull() {
            addCriterion("LEADER_THREE_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeScoreIsNotNull() {
            addCriterion("LEADER_THREE_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeScoreEqualTo(String value) {
            addCriterion("LEADER_THREE_SCORE =", value, "leaderThreeScore");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeScoreNotEqualTo(String value) {
            addCriterion("LEADER_THREE_SCORE <>", value, "leaderThreeScore");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeScoreGreaterThan(String value) {
            addCriterion("LEADER_THREE_SCORE >", value, "leaderThreeScore");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeScoreGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_THREE_SCORE >=", value, "leaderThreeScore");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeScoreLessThan(String value) {
            addCriterion("LEADER_THREE_SCORE <", value, "leaderThreeScore");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeScoreLessThanOrEqualTo(String value) {
            addCriterion("LEADER_THREE_SCORE <=", value, "leaderThreeScore");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeScoreLike(String value) {
            addCriterion("LEADER_THREE_SCORE like", value, "leaderThreeScore");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeScoreNotLike(String value) {
            addCriterion("LEADER_THREE_SCORE not like", value, "leaderThreeScore");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeScoreIn(List<String> values) {
            addCriterion("LEADER_THREE_SCORE in", values, "leaderThreeScore");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeScoreNotIn(List<String> values) {
            addCriterion("LEADER_THREE_SCORE not in", values, "leaderThreeScore");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeScoreBetween(String value1, String value2) {
            addCriterion("LEADER_THREE_SCORE between", value1, value2, "leaderThreeScore");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeScoreNotBetween(String value1, String value2) {
            addCriterion("LEADER_THREE_SCORE not between", value1, value2, "leaderThreeScore");
            return (Criteria) this;
        }

        public Criteria andLeaderFourScoreIsNull() {
            addCriterion("LEADER_FOUR_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andLeaderFourScoreIsNotNull() {
            addCriterion("LEADER_FOUR_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderFourScoreEqualTo(String value) {
            addCriterion("LEADER_FOUR_SCORE =", value, "leaderFourScore");
            return (Criteria) this;
        }

        public Criteria andLeaderFourScoreNotEqualTo(String value) {
            addCriterion("LEADER_FOUR_SCORE <>", value, "leaderFourScore");
            return (Criteria) this;
        }

        public Criteria andLeaderFourScoreGreaterThan(String value) {
            addCriterion("LEADER_FOUR_SCORE >", value, "leaderFourScore");
            return (Criteria) this;
        }

        public Criteria andLeaderFourScoreGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_FOUR_SCORE >=", value, "leaderFourScore");
            return (Criteria) this;
        }

        public Criteria andLeaderFourScoreLessThan(String value) {
            addCriterion("LEADER_FOUR_SCORE <", value, "leaderFourScore");
            return (Criteria) this;
        }

        public Criteria andLeaderFourScoreLessThanOrEqualTo(String value) {
            addCriterion("LEADER_FOUR_SCORE <=", value, "leaderFourScore");
            return (Criteria) this;
        }

        public Criteria andLeaderFourScoreLike(String value) {
            addCriterion("LEADER_FOUR_SCORE like", value, "leaderFourScore");
            return (Criteria) this;
        }

        public Criteria andLeaderFourScoreNotLike(String value) {
            addCriterion("LEADER_FOUR_SCORE not like", value, "leaderFourScore");
            return (Criteria) this;
        }

        public Criteria andLeaderFourScoreIn(List<String> values) {
            addCriterion("LEADER_FOUR_SCORE in", values, "leaderFourScore");
            return (Criteria) this;
        }

        public Criteria andLeaderFourScoreNotIn(List<String> values) {
            addCriterion("LEADER_FOUR_SCORE not in", values, "leaderFourScore");
            return (Criteria) this;
        }

        public Criteria andLeaderFourScoreBetween(String value1, String value2) {
            addCriterion("LEADER_FOUR_SCORE between", value1, value2, "leaderFourScore");
            return (Criteria) this;
        }

        public Criteria andLeaderFourScoreNotBetween(String value1, String value2) {
            addCriterion("LEADER_FOUR_SCORE not between", value1, value2, "leaderFourScore");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeStartTimeIsNull() {
            addCriterion("LEADER_THREE_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeStartTimeIsNotNull() {
            addCriterion("LEADER_THREE_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeStartTimeEqualTo(String value) {
            addCriterion("LEADER_THREE_START_TIME =", value, "leaderThreeStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeStartTimeNotEqualTo(String value) {
            addCriterion("LEADER_THREE_START_TIME <>", value, "leaderThreeStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeStartTimeGreaterThan(String value) {
            addCriterion("LEADER_THREE_START_TIME >", value, "leaderThreeStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_THREE_START_TIME >=", value, "leaderThreeStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeStartTimeLessThan(String value) {
            addCriterion("LEADER_THREE_START_TIME <", value, "leaderThreeStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeStartTimeLessThanOrEqualTo(String value) {
            addCriterion("LEADER_THREE_START_TIME <=", value, "leaderThreeStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeStartTimeLike(String value) {
            addCriterion("LEADER_THREE_START_TIME like", value, "leaderThreeStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeStartTimeNotLike(String value) {
            addCriterion("LEADER_THREE_START_TIME not like", value, "leaderThreeStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeStartTimeIn(List<String> values) {
            addCriterion("LEADER_THREE_START_TIME in", values, "leaderThreeStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeStartTimeNotIn(List<String> values) {
            addCriterion("LEADER_THREE_START_TIME not in", values, "leaderThreeStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeStartTimeBetween(String value1, String value2) {
            addCriterion("LEADER_THREE_START_TIME between", value1, value2, "leaderThreeStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeStartTimeNotBetween(String value1, String value2) {
            addCriterion("LEADER_THREE_START_TIME not between", value1, value2, "leaderThreeStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeEndTimeIsNull() {
            addCriterion("LEADER_THREE_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeEndTimeIsNotNull() {
            addCriterion("LEADER_THREE_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeEndTimeEqualTo(String value) {
            addCriterion("LEADER_THREE_END_TIME =", value, "leaderThreeEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeEndTimeNotEqualTo(String value) {
            addCriterion("LEADER_THREE_END_TIME <>", value, "leaderThreeEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeEndTimeGreaterThan(String value) {
            addCriterion("LEADER_THREE_END_TIME >", value, "leaderThreeEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_THREE_END_TIME >=", value, "leaderThreeEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeEndTimeLessThan(String value) {
            addCriterion("LEADER_THREE_END_TIME <", value, "leaderThreeEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeEndTimeLessThanOrEqualTo(String value) {
            addCriterion("LEADER_THREE_END_TIME <=", value, "leaderThreeEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeEndTimeLike(String value) {
            addCriterion("LEADER_THREE_END_TIME like", value, "leaderThreeEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeEndTimeNotLike(String value) {
            addCriterion("LEADER_THREE_END_TIME not like", value, "leaderThreeEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeEndTimeIn(List<String> values) {
            addCriterion("LEADER_THREE_END_TIME in", values, "leaderThreeEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeEndTimeNotIn(List<String> values) {
            addCriterion("LEADER_THREE_END_TIME not in", values, "leaderThreeEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeEndTimeBetween(String value1, String value2) {
            addCriterion("LEADER_THREE_END_TIME between", value1, value2, "leaderThreeEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderThreeEndTimeNotBetween(String value1, String value2) {
            addCriterion("LEADER_THREE_END_TIME not between", value1, value2, "leaderThreeEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourStartTimeIsNull() {
            addCriterion("LEADER_FOUR_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLeaderFourStartTimeIsNotNull() {
            addCriterion("LEADER_FOUR_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderFourStartTimeEqualTo(String value) {
            addCriterion("LEADER_FOUR_START_TIME =", value, "leaderFourStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourStartTimeNotEqualTo(String value) {
            addCriterion("LEADER_FOUR_START_TIME <>", value, "leaderFourStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourStartTimeGreaterThan(String value) {
            addCriterion("LEADER_FOUR_START_TIME >", value, "leaderFourStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_FOUR_START_TIME >=", value, "leaderFourStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourStartTimeLessThan(String value) {
            addCriterion("LEADER_FOUR_START_TIME <", value, "leaderFourStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourStartTimeLessThanOrEqualTo(String value) {
            addCriterion("LEADER_FOUR_START_TIME <=", value, "leaderFourStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourStartTimeLike(String value) {
            addCriterion("LEADER_FOUR_START_TIME like", value, "leaderFourStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourStartTimeNotLike(String value) {
            addCriterion("LEADER_FOUR_START_TIME not like", value, "leaderFourStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourStartTimeIn(List<String> values) {
            addCriterion("LEADER_FOUR_START_TIME in", values, "leaderFourStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourStartTimeNotIn(List<String> values) {
            addCriterion("LEADER_FOUR_START_TIME not in", values, "leaderFourStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourStartTimeBetween(String value1, String value2) {
            addCriterion("LEADER_FOUR_START_TIME between", value1, value2, "leaderFourStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourStartTimeNotBetween(String value1, String value2) {
            addCriterion("LEADER_FOUR_START_TIME not between", value1, value2, "leaderFourStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourEndTimeIsNull() {
            addCriterion("LEADER_FOUR_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLeaderFourEndTimeIsNotNull() {
            addCriterion("LEADER_FOUR_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderFourEndTimeEqualTo(String value) {
            addCriterion("LEADER_FOUR_END_TIME =", value, "leaderFourEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourEndTimeNotEqualTo(String value) {
            addCriterion("LEADER_FOUR_END_TIME <>", value, "leaderFourEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourEndTimeGreaterThan(String value) {
            addCriterion("LEADER_FOUR_END_TIME >", value, "leaderFourEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_FOUR_END_TIME >=", value, "leaderFourEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourEndTimeLessThan(String value) {
            addCriterion("LEADER_FOUR_END_TIME <", value, "leaderFourEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourEndTimeLessThanOrEqualTo(String value) {
            addCriterion("LEADER_FOUR_END_TIME <=", value, "leaderFourEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourEndTimeLike(String value) {
            addCriterion("LEADER_FOUR_END_TIME like", value, "leaderFourEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourEndTimeNotLike(String value) {
            addCriterion("LEADER_FOUR_END_TIME not like", value, "leaderFourEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourEndTimeIn(List<String> values) {
            addCriterion("LEADER_FOUR_END_TIME in", values, "leaderFourEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourEndTimeNotIn(List<String> values) {
            addCriterion("LEADER_FOUR_END_TIME not in", values, "leaderFourEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourEndTimeBetween(String value1, String value2) {
            addCriterion("LEADER_FOUR_END_TIME between", value1, value2, "leaderFourEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaderFourEndTimeNotBetween(String value1, String value2) {
            addCriterion("LEADER_FOUR_END_TIME not between", value1, value2, "leaderFourEndTime");
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