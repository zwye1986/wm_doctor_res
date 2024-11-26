package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResDoctorRecruitInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResDoctorRecruitInfoExample() {
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

        public Criteria andInJointOrgTrainIsNull() {
            addCriterion("IN_JOINT_ORG_TRAIN is null");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainIsNotNull() {
            addCriterion("IN_JOINT_ORG_TRAIN is not null");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainEqualTo(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN =", value, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainNotEqualTo(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN <>", value, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainGreaterThan(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN >", value, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainGreaterThanOrEqualTo(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN >=", value, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainLessThan(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN <", value, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainLessThanOrEqualTo(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN <=", value, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainLike(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN like", value, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainNotLike(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN not like", value, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainIn(List<String> values) {
            addCriterion("IN_JOINT_ORG_TRAIN in", values, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainNotIn(List<String> values) {
            addCriterion("IN_JOINT_ORG_TRAIN not in", values, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainBetween(String value1, String value2) {
            addCriterion("IN_JOINT_ORG_TRAIN between", value1, value2, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainNotBetween(String value1, String value2) {
            addCriterion("IN_JOINT_ORG_TRAIN not between", value1, value2, "inJointOrgTrain");
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

        public Criteria andOrgFlowTwoIsNull() {
            addCriterion("ORG_FLOW_TWO is null");
            return (Criteria) this;
        }

        public Criteria andOrgFlowTwoIsNotNull() {
            addCriterion("ORG_FLOW_TWO is not null");
            return (Criteria) this;
        }

        public Criteria andOrgFlowTwoEqualTo(String value) {
            addCriterion("ORG_FLOW_TWO =", value, "orgFlowTwo");
            return (Criteria) this;
        }

        public Criteria andOrgFlowTwoNotEqualTo(String value) {
            addCriterion("ORG_FLOW_TWO <>", value, "orgFlowTwo");
            return (Criteria) this;
        }

        public Criteria andOrgFlowTwoGreaterThan(String value) {
            addCriterion("ORG_FLOW_TWO >", value, "orgFlowTwo");
            return (Criteria) this;
        }

        public Criteria andOrgFlowTwoGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_FLOW_TWO >=", value, "orgFlowTwo");
            return (Criteria) this;
        }

        public Criteria andOrgFlowTwoLessThan(String value) {
            addCriterion("ORG_FLOW_TWO <", value, "orgFlowTwo");
            return (Criteria) this;
        }

        public Criteria andOrgFlowTwoLessThanOrEqualTo(String value) {
            addCriterion("ORG_FLOW_TWO <=", value, "orgFlowTwo");
            return (Criteria) this;
        }

        public Criteria andOrgFlowTwoLike(String value) {
            addCriterion("ORG_FLOW_TWO like", value, "orgFlowTwo");
            return (Criteria) this;
        }

        public Criteria andOrgFlowTwoNotLike(String value) {
            addCriterion("ORG_FLOW_TWO not like", value, "orgFlowTwo");
            return (Criteria) this;
        }

        public Criteria andOrgFlowTwoIn(List<String> values) {
            addCriterion("ORG_FLOW_TWO in", values, "orgFlowTwo");
            return (Criteria) this;
        }

        public Criteria andOrgFlowTwoNotIn(List<String> values) {
            addCriterion("ORG_FLOW_TWO not in", values, "orgFlowTwo");
            return (Criteria) this;
        }

        public Criteria andOrgFlowTwoBetween(String value1, String value2) {
            addCriterion("ORG_FLOW_TWO between", value1, value2, "orgFlowTwo");
            return (Criteria) this;
        }

        public Criteria andOrgFlowTwoNotBetween(String value1, String value2) {
            addCriterion("ORG_FLOW_TWO not between", value1, value2, "orgFlowTwo");
            return (Criteria) this;
        }

        public Criteria andOrgNameTwoIsNull() {
            addCriterion("ORG_NAME_TWO is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameTwoIsNotNull() {
            addCriterion("ORG_NAME_TWO is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameTwoEqualTo(String value) {
            addCriterion("ORG_NAME_TWO =", value, "orgNameTwo");
            return (Criteria) this;
        }

        public Criteria andOrgNameTwoNotEqualTo(String value) {
            addCriterion("ORG_NAME_TWO <>", value, "orgNameTwo");
            return (Criteria) this;
        }

        public Criteria andOrgNameTwoGreaterThan(String value) {
            addCriterion("ORG_NAME_TWO >", value, "orgNameTwo");
            return (Criteria) this;
        }

        public Criteria andOrgNameTwoGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NAME_TWO >=", value, "orgNameTwo");
            return (Criteria) this;
        }

        public Criteria andOrgNameTwoLessThan(String value) {
            addCriterion("ORG_NAME_TWO <", value, "orgNameTwo");
            return (Criteria) this;
        }

        public Criteria andOrgNameTwoLessThanOrEqualTo(String value) {
            addCriterion("ORG_NAME_TWO <=", value, "orgNameTwo");
            return (Criteria) this;
        }

        public Criteria andOrgNameTwoLike(String value) {
            addCriterion("ORG_NAME_TWO like", value, "orgNameTwo");
            return (Criteria) this;
        }

        public Criteria andOrgNameTwoNotLike(String value) {
            addCriterion("ORG_NAME_TWO not like", value, "orgNameTwo");
            return (Criteria) this;
        }

        public Criteria andOrgNameTwoIn(List<String> values) {
            addCriterion("ORG_NAME_TWO in", values, "orgNameTwo");
            return (Criteria) this;
        }

        public Criteria andOrgNameTwoNotIn(List<String> values) {
            addCriterion("ORG_NAME_TWO not in", values, "orgNameTwo");
            return (Criteria) this;
        }

        public Criteria andOrgNameTwoBetween(String value1, String value2) {
            addCriterion("ORG_NAME_TWO between", value1, value2, "orgNameTwo");
            return (Criteria) this;
        }

        public Criteria andOrgNameTwoNotBetween(String value1, String value2) {
            addCriterion("ORG_NAME_TWO not between", value1, value2, "orgNameTwo");
            return (Criteria) this;
        }

        public Criteria andSpeIdTwoIsNull() {
            addCriterion("SPE_ID_TWO is null");
            return (Criteria) this;
        }

        public Criteria andSpeIdTwoIsNotNull() {
            addCriterion("SPE_ID_TWO is not null");
            return (Criteria) this;
        }

        public Criteria andSpeIdTwoEqualTo(String value) {
            addCriterion("SPE_ID_TWO =", value, "speIdTwo");
            return (Criteria) this;
        }

        public Criteria andSpeIdTwoNotEqualTo(String value) {
            addCriterion("SPE_ID_TWO <>", value, "speIdTwo");
            return (Criteria) this;
        }

        public Criteria andSpeIdTwoGreaterThan(String value) {
            addCriterion("SPE_ID_TWO >", value, "speIdTwo");
            return (Criteria) this;
        }

        public Criteria andSpeIdTwoGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_ID_TWO >=", value, "speIdTwo");
            return (Criteria) this;
        }

        public Criteria andSpeIdTwoLessThan(String value) {
            addCriterion("SPE_ID_TWO <", value, "speIdTwo");
            return (Criteria) this;
        }

        public Criteria andSpeIdTwoLessThanOrEqualTo(String value) {
            addCriterion("SPE_ID_TWO <=", value, "speIdTwo");
            return (Criteria) this;
        }

        public Criteria andSpeIdTwoLike(String value) {
            addCriterion("SPE_ID_TWO like", value, "speIdTwo");
            return (Criteria) this;
        }

        public Criteria andSpeIdTwoNotLike(String value) {
            addCriterion("SPE_ID_TWO not like", value, "speIdTwo");
            return (Criteria) this;
        }

        public Criteria andSpeIdTwoIn(List<String> values) {
            addCriterion("SPE_ID_TWO in", values, "speIdTwo");
            return (Criteria) this;
        }

        public Criteria andSpeIdTwoNotIn(List<String> values) {
            addCriterion("SPE_ID_TWO not in", values, "speIdTwo");
            return (Criteria) this;
        }

        public Criteria andSpeIdTwoBetween(String value1, String value2) {
            addCriterion("SPE_ID_TWO between", value1, value2, "speIdTwo");
            return (Criteria) this;
        }

        public Criteria andSpeIdTwoNotBetween(String value1, String value2) {
            addCriterion("SPE_ID_TWO not between", value1, value2, "speIdTwo");
            return (Criteria) this;
        }

        public Criteria andSpeNameTwoIsNull() {
            addCriterion("SPE_NAME_TWO is null");
            return (Criteria) this;
        }

        public Criteria andSpeNameTwoIsNotNull() {
            addCriterion("SPE_NAME_TWO is not null");
            return (Criteria) this;
        }

        public Criteria andSpeNameTwoEqualTo(String value) {
            addCriterion("SPE_NAME_TWO =", value, "speNameTwo");
            return (Criteria) this;
        }

        public Criteria andSpeNameTwoNotEqualTo(String value) {
            addCriterion("SPE_NAME_TWO <>", value, "speNameTwo");
            return (Criteria) this;
        }

        public Criteria andSpeNameTwoGreaterThan(String value) {
            addCriterion("SPE_NAME_TWO >", value, "speNameTwo");
            return (Criteria) this;
        }

        public Criteria andSpeNameTwoGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_NAME_TWO >=", value, "speNameTwo");
            return (Criteria) this;
        }

        public Criteria andSpeNameTwoLessThan(String value) {
            addCriterion("SPE_NAME_TWO <", value, "speNameTwo");
            return (Criteria) this;
        }

        public Criteria andSpeNameTwoLessThanOrEqualTo(String value) {
            addCriterion("SPE_NAME_TWO <=", value, "speNameTwo");
            return (Criteria) this;
        }

        public Criteria andSpeNameTwoLike(String value) {
            addCriterion("SPE_NAME_TWO like", value, "speNameTwo");
            return (Criteria) this;
        }

        public Criteria andSpeNameTwoNotLike(String value) {
            addCriterion("SPE_NAME_TWO not like", value, "speNameTwo");
            return (Criteria) this;
        }

        public Criteria andSpeNameTwoIn(List<String> values) {
            addCriterion("SPE_NAME_TWO in", values, "speNameTwo");
            return (Criteria) this;
        }

        public Criteria andSpeNameTwoNotIn(List<String> values) {
            addCriterion("SPE_NAME_TWO not in", values, "speNameTwo");
            return (Criteria) this;
        }

        public Criteria andSpeNameTwoBetween(String value1, String value2) {
            addCriterion("SPE_NAME_TWO between", value1, value2, "speNameTwo");
            return (Criteria) this;
        }

        public Criteria andSpeNameTwoNotBetween(String value1, String value2) {
            addCriterion("SPE_NAME_TWO not between", value1, value2, "speNameTwo");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainTwoIsNull() {
            addCriterion("IN_JOINT_ORG_TRAIN_TWO is null");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainTwoIsNotNull() {
            addCriterion("IN_JOINT_ORG_TRAIN_TWO is not null");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainTwoEqualTo(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN_TWO =", value, "inJointOrgTrainTwo");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainTwoNotEqualTo(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN_TWO <>", value, "inJointOrgTrainTwo");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainTwoGreaterThan(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN_TWO >", value, "inJointOrgTrainTwo");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainTwoGreaterThanOrEqualTo(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN_TWO >=", value, "inJointOrgTrainTwo");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainTwoLessThan(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN_TWO <", value, "inJointOrgTrainTwo");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainTwoLessThanOrEqualTo(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN_TWO <=", value, "inJointOrgTrainTwo");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainTwoLike(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN_TWO like", value, "inJointOrgTrainTwo");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainTwoNotLike(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN_TWO not like", value, "inJointOrgTrainTwo");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainTwoIn(List<String> values) {
            addCriterion("IN_JOINT_ORG_TRAIN_TWO in", values, "inJointOrgTrainTwo");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainTwoNotIn(List<String> values) {
            addCriterion("IN_JOINT_ORG_TRAIN_TWO not in", values, "inJointOrgTrainTwo");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainTwoBetween(String value1, String value2) {
            addCriterion("IN_JOINT_ORG_TRAIN_TWO between", value1, value2, "inJointOrgTrainTwo");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainTwoNotBetween(String value1, String value2) {
            addCriterion("IN_JOINT_ORG_TRAIN_TWO not between", value1, value2, "inJointOrgTrainTwo");
            return (Criteria) this;
        }

        public Criteria andFirstIsPassIsNull() {
            addCriterion("FIRST_IS_PASS is null");
            return (Criteria) this;
        }

        public Criteria andFirstIsPassIsNotNull() {
            addCriterion("FIRST_IS_PASS is not null");
            return (Criteria) this;
        }

        public Criteria andFirstIsPassEqualTo(String value) {
            addCriterion("FIRST_IS_PASS =", value, "firstIsPass");
            return (Criteria) this;
        }

        public Criteria andFirstIsPassNotEqualTo(String value) {
            addCriterion("FIRST_IS_PASS <>", value, "firstIsPass");
            return (Criteria) this;
        }

        public Criteria andFirstIsPassGreaterThan(String value) {
            addCriterion("FIRST_IS_PASS >", value, "firstIsPass");
            return (Criteria) this;
        }

        public Criteria andFirstIsPassGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_IS_PASS >=", value, "firstIsPass");
            return (Criteria) this;
        }

        public Criteria andFirstIsPassLessThan(String value) {
            addCriterion("FIRST_IS_PASS <", value, "firstIsPass");
            return (Criteria) this;
        }

        public Criteria andFirstIsPassLessThanOrEqualTo(String value) {
            addCriterion("FIRST_IS_PASS <=", value, "firstIsPass");
            return (Criteria) this;
        }

        public Criteria andFirstIsPassLike(String value) {
            addCriterion("FIRST_IS_PASS like", value, "firstIsPass");
            return (Criteria) this;
        }

        public Criteria andFirstIsPassNotLike(String value) {
            addCriterion("FIRST_IS_PASS not like", value, "firstIsPass");
            return (Criteria) this;
        }

        public Criteria andFirstIsPassIn(List<String> values) {
            addCriterion("FIRST_IS_PASS in", values, "firstIsPass");
            return (Criteria) this;
        }

        public Criteria andFirstIsPassNotIn(List<String> values) {
            addCriterion("FIRST_IS_PASS not in", values, "firstIsPass");
            return (Criteria) this;
        }

        public Criteria andFirstIsPassBetween(String value1, String value2) {
            addCriterion("FIRST_IS_PASS between", value1, value2, "firstIsPass");
            return (Criteria) this;
        }

        public Criteria andFirstIsPassNotBetween(String value1, String value2) {
            addCriterion("FIRST_IS_PASS not between", value1, value2, "firstIsPass");
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