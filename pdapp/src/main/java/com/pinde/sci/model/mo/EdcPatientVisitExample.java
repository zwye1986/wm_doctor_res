package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EdcPatientVisitExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdcPatientVisitExample() {
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

        public Criteria andInputOper1FlowIsNull() {
            addCriterion("INPUT_OPER1_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andInputOper1FlowIsNotNull() {
            addCriterion("INPUT_OPER1_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andInputOper1FlowEqualTo(String value) {
            addCriterion("INPUT_OPER1_FLOW =", value, "inputOper1Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper1FlowNotEqualTo(String value) {
            addCriterion("INPUT_OPER1_FLOW <>", value, "inputOper1Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper1FlowGreaterThan(String value) {
            addCriterion("INPUT_OPER1_FLOW >", value, "inputOper1Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper1FlowGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER1_FLOW >=", value, "inputOper1Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper1FlowLessThan(String value) {
            addCriterion("INPUT_OPER1_FLOW <", value, "inputOper1Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper1FlowLessThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER1_FLOW <=", value, "inputOper1Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper1FlowLike(String value) {
            addCriterion("INPUT_OPER1_FLOW like", value, "inputOper1Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper1FlowNotLike(String value) {
            addCriterion("INPUT_OPER1_FLOW not like", value, "inputOper1Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper1FlowIn(List<String> values) {
            addCriterion("INPUT_OPER1_FLOW in", values, "inputOper1Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper1FlowNotIn(List<String> values) {
            addCriterion("INPUT_OPER1_FLOW not in", values, "inputOper1Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper1FlowBetween(String value1, String value2) {
            addCriterion("INPUT_OPER1_FLOW between", value1, value2, "inputOper1Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper1FlowNotBetween(String value1, String value2) {
            addCriterion("INPUT_OPER1_FLOW not between", value1, value2, "inputOper1Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper1NameIsNull() {
            addCriterion("INPUT_OPER1_NAME is null");
            return (Criteria) this;
        }

        public Criteria andInputOper1NameIsNotNull() {
            addCriterion("INPUT_OPER1_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andInputOper1NameEqualTo(String value) {
            addCriterion("INPUT_OPER1_NAME =", value, "inputOper1Name");
            return (Criteria) this;
        }

        public Criteria andInputOper1NameNotEqualTo(String value) {
            addCriterion("INPUT_OPER1_NAME <>", value, "inputOper1Name");
            return (Criteria) this;
        }

        public Criteria andInputOper1NameGreaterThan(String value) {
            addCriterion("INPUT_OPER1_NAME >", value, "inputOper1Name");
            return (Criteria) this;
        }

        public Criteria andInputOper1NameGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER1_NAME >=", value, "inputOper1Name");
            return (Criteria) this;
        }

        public Criteria andInputOper1NameLessThan(String value) {
            addCriterion("INPUT_OPER1_NAME <", value, "inputOper1Name");
            return (Criteria) this;
        }

        public Criteria andInputOper1NameLessThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER1_NAME <=", value, "inputOper1Name");
            return (Criteria) this;
        }

        public Criteria andInputOper1NameLike(String value) {
            addCriterion("INPUT_OPER1_NAME like", value, "inputOper1Name");
            return (Criteria) this;
        }

        public Criteria andInputOper1NameNotLike(String value) {
            addCriterion("INPUT_OPER1_NAME not like", value, "inputOper1Name");
            return (Criteria) this;
        }

        public Criteria andInputOper1NameIn(List<String> values) {
            addCriterion("INPUT_OPER1_NAME in", values, "inputOper1Name");
            return (Criteria) this;
        }

        public Criteria andInputOper1NameNotIn(List<String> values) {
            addCriterion("INPUT_OPER1_NAME not in", values, "inputOper1Name");
            return (Criteria) this;
        }

        public Criteria andInputOper1NameBetween(String value1, String value2) {
            addCriterion("INPUT_OPER1_NAME between", value1, value2, "inputOper1Name");
            return (Criteria) this;
        }

        public Criteria andInputOper1NameNotBetween(String value1, String value2) {
            addCriterion("INPUT_OPER1_NAME not between", value1, value2, "inputOper1Name");
            return (Criteria) this;
        }

        public Criteria andInputOper1TimeIsNull() {
            addCriterion("INPUT_OPER1_TIME is null");
            return (Criteria) this;
        }

        public Criteria andInputOper1TimeIsNotNull() {
            addCriterion("INPUT_OPER1_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andInputOper1TimeEqualTo(String value) {
            addCriterion("INPUT_OPER1_TIME =", value, "inputOper1Time");
            return (Criteria) this;
        }

        public Criteria andInputOper1TimeNotEqualTo(String value) {
            addCriterion("INPUT_OPER1_TIME <>", value, "inputOper1Time");
            return (Criteria) this;
        }

        public Criteria andInputOper1TimeGreaterThan(String value) {
            addCriterion("INPUT_OPER1_TIME >", value, "inputOper1Time");
            return (Criteria) this;
        }

        public Criteria andInputOper1TimeGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER1_TIME >=", value, "inputOper1Time");
            return (Criteria) this;
        }

        public Criteria andInputOper1TimeLessThan(String value) {
            addCriterion("INPUT_OPER1_TIME <", value, "inputOper1Time");
            return (Criteria) this;
        }

        public Criteria andInputOper1TimeLessThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER1_TIME <=", value, "inputOper1Time");
            return (Criteria) this;
        }

        public Criteria andInputOper1TimeLike(String value) {
            addCriterion("INPUT_OPER1_TIME like", value, "inputOper1Time");
            return (Criteria) this;
        }

        public Criteria andInputOper1TimeNotLike(String value) {
            addCriterion("INPUT_OPER1_TIME not like", value, "inputOper1Time");
            return (Criteria) this;
        }

        public Criteria andInputOper1TimeIn(List<String> values) {
            addCriterion("INPUT_OPER1_TIME in", values, "inputOper1Time");
            return (Criteria) this;
        }

        public Criteria andInputOper1TimeNotIn(List<String> values) {
            addCriterion("INPUT_OPER1_TIME not in", values, "inputOper1Time");
            return (Criteria) this;
        }

        public Criteria andInputOper1TimeBetween(String value1, String value2) {
            addCriterion("INPUT_OPER1_TIME between", value1, value2, "inputOper1Time");
            return (Criteria) this;
        }

        public Criteria andInputOper1TimeNotBetween(String value1, String value2) {
            addCriterion("INPUT_OPER1_TIME not between", value1, value2, "inputOper1Time");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusIdIsNull() {
            addCriterion("INPUT_OPER1_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusIdIsNotNull() {
            addCriterion("INPUT_OPER1_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusIdEqualTo(String value) {
            addCriterion("INPUT_OPER1_STATUS_ID =", value, "inputOper1StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusIdNotEqualTo(String value) {
            addCriterion("INPUT_OPER1_STATUS_ID <>", value, "inputOper1StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusIdGreaterThan(String value) {
            addCriterion("INPUT_OPER1_STATUS_ID >", value, "inputOper1StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER1_STATUS_ID >=", value, "inputOper1StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusIdLessThan(String value) {
            addCriterion("INPUT_OPER1_STATUS_ID <", value, "inputOper1StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusIdLessThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER1_STATUS_ID <=", value, "inputOper1StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusIdLike(String value) {
            addCriterion("INPUT_OPER1_STATUS_ID like", value, "inputOper1StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusIdNotLike(String value) {
            addCriterion("INPUT_OPER1_STATUS_ID not like", value, "inputOper1StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusIdIn(List<String> values) {
            addCriterion("INPUT_OPER1_STATUS_ID in", values, "inputOper1StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusIdNotIn(List<String> values) {
            addCriterion("INPUT_OPER1_STATUS_ID not in", values, "inputOper1StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusIdBetween(String value1, String value2) {
            addCriterion("INPUT_OPER1_STATUS_ID between", value1, value2, "inputOper1StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusIdNotBetween(String value1, String value2) {
            addCriterion("INPUT_OPER1_STATUS_ID not between", value1, value2, "inputOper1StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusNameIsNull() {
            addCriterion("INPUT_OPER1_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusNameIsNotNull() {
            addCriterion("INPUT_OPER1_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusNameEqualTo(String value) {
            addCriterion("INPUT_OPER1_STATUS_NAME =", value, "inputOper1StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusNameNotEqualTo(String value) {
            addCriterion("INPUT_OPER1_STATUS_NAME <>", value, "inputOper1StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusNameGreaterThan(String value) {
            addCriterion("INPUT_OPER1_STATUS_NAME >", value, "inputOper1StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER1_STATUS_NAME >=", value, "inputOper1StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusNameLessThan(String value) {
            addCriterion("INPUT_OPER1_STATUS_NAME <", value, "inputOper1StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusNameLessThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER1_STATUS_NAME <=", value, "inputOper1StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusNameLike(String value) {
            addCriterion("INPUT_OPER1_STATUS_NAME like", value, "inputOper1StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusNameNotLike(String value) {
            addCriterion("INPUT_OPER1_STATUS_NAME not like", value, "inputOper1StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusNameIn(List<String> values) {
            addCriterion("INPUT_OPER1_STATUS_NAME in", values, "inputOper1StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusNameNotIn(List<String> values) {
            addCriterion("INPUT_OPER1_STATUS_NAME not in", values, "inputOper1StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusNameBetween(String value1, String value2) {
            addCriterion("INPUT_OPER1_STATUS_NAME between", value1, value2, "inputOper1StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper1StatusNameNotBetween(String value1, String value2) {
            addCriterion("INPUT_OPER1_STATUS_NAME not between", value1, value2, "inputOper1StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper2FlowIsNull() {
            addCriterion("INPUT_OPER2_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andInputOper2FlowIsNotNull() {
            addCriterion("INPUT_OPER2_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andInputOper2FlowEqualTo(String value) {
            addCriterion("INPUT_OPER2_FLOW =", value, "inputOper2Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper2FlowNotEqualTo(String value) {
            addCriterion("INPUT_OPER2_FLOW <>", value, "inputOper2Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper2FlowGreaterThan(String value) {
            addCriterion("INPUT_OPER2_FLOW >", value, "inputOper2Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper2FlowGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER2_FLOW >=", value, "inputOper2Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper2FlowLessThan(String value) {
            addCriterion("INPUT_OPER2_FLOW <", value, "inputOper2Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper2FlowLessThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER2_FLOW <=", value, "inputOper2Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper2FlowLike(String value) {
            addCriterion("INPUT_OPER2_FLOW like", value, "inputOper2Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper2FlowNotLike(String value) {
            addCriterion("INPUT_OPER2_FLOW not like", value, "inputOper2Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper2FlowIn(List<String> values) {
            addCriterion("INPUT_OPER2_FLOW in", values, "inputOper2Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper2FlowNotIn(List<String> values) {
            addCriterion("INPUT_OPER2_FLOW not in", values, "inputOper2Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper2FlowBetween(String value1, String value2) {
            addCriterion("INPUT_OPER2_FLOW between", value1, value2, "inputOper2Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper2FlowNotBetween(String value1, String value2) {
            addCriterion("INPUT_OPER2_FLOW not between", value1, value2, "inputOper2Flow");
            return (Criteria) this;
        }

        public Criteria andInputOper2NameIsNull() {
            addCriterion("INPUT_OPER2_NAME is null");
            return (Criteria) this;
        }

        public Criteria andInputOper2NameIsNotNull() {
            addCriterion("INPUT_OPER2_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andInputOper2NameEqualTo(String value) {
            addCriterion("INPUT_OPER2_NAME =", value, "inputOper2Name");
            return (Criteria) this;
        }

        public Criteria andInputOper2NameNotEqualTo(String value) {
            addCriterion("INPUT_OPER2_NAME <>", value, "inputOper2Name");
            return (Criteria) this;
        }

        public Criteria andInputOper2NameGreaterThan(String value) {
            addCriterion("INPUT_OPER2_NAME >", value, "inputOper2Name");
            return (Criteria) this;
        }

        public Criteria andInputOper2NameGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER2_NAME >=", value, "inputOper2Name");
            return (Criteria) this;
        }

        public Criteria andInputOper2NameLessThan(String value) {
            addCriterion("INPUT_OPER2_NAME <", value, "inputOper2Name");
            return (Criteria) this;
        }

        public Criteria andInputOper2NameLessThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER2_NAME <=", value, "inputOper2Name");
            return (Criteria) this;
        }

        public Criteria andInputOper2NameLike(String value) {
            addCriterion("INPUT_OPER2_NAME like", value, "inputOper2Name");
            return (Criteria) this;
        }

        public Criteria andInputOper2NameNotLike(String value) {
            addCriterion("INPUT_OPER2_NAME not like", value, "inputOper2Name");
            return (Criteria) this;
        }

        public Criteria andInputOper2NameIn(List<String> values) {
            addCriterion("INPUT_OPER2_NAME in", values, "inputOper2Name");
            return (Criteria) this;
        }

        public Criteria andInputOper2NameNotIn(List<String> values) {
            addCriterion("INPUT_OPER2_NAME not in", values, "inputOper2Name");
            return (Criteria) this;
        }

        public Criteria andInputOper2NameBetween(String value1, String value2) {
            addCriterion("INPUT_OPER2_NAME between", value1, value2, "inputOper2Name");
            return (Criteria) this;
        }

        public Criteria andInputOper2NameNotBetween(String value1, String value2) {
            addCriterion("INPUT_OPER2_NAME not between", value1, value2, "inputOper2Name");
            return (Criteria) this;
        }

        public Criteria andInputOper2TimeIsNull() {
            addCriterion("INPUT_OPER2_TIME is null");
            return (Criteria) this;
        }

        public Criteria andInputOper2TimeIsNotNull() {
            addCriterion("INPUT_OPER2_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andInputOper2TimeEqualTo(String value) {
            addCriterion("INPUT_OPER2_TIME =", value, "inputOper2Time");
            return (Criteria) this;
        }

        public Criteria andInputOper2TimeNotEqualTo(String value) {
            addCriterion("INPUT_OPER2_TIME <>", value, "inputOper2Time");
            return (Criteria) this;
        }

        public Criteria andInputOper2TimeGreaterThan(String value) {
            addCriterion("INPUT_OPER2_TIME >", value, "inputOper2Time");
            return (Criteria) this;
        }

        public Criteria andInputOper2TimeGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER2_TIME >=", value, "inputOper2Time");
            return (Criteria) this;
        }

        public Criteria andInputOper2TimeLessThan(String value) {
            addCriterion("INPUT_OPER2_TIME <", value, "inputOper2Time");
            return (Criteria) this;
        }

        public Criteria andInputOper2TimeLessThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER2_TIME <=", value, "inputOper2Time");
            return (Criteria) this;
        }

        public Criteria andInputOper2TimeLike(String value) {
            addCriterion("INPUT_OPER2_TIME like", value, "inputOper2Time");
            return (Criteria) this;
        }

        public Criteria andInputOper2TimeNotLike(String value) {
            addCriterion("INPUT_OPER2_TIME not like", value, "inputOper2Time");
            return (Criteria) this;
        }

        public Criteria andInputOper2TimeIn(List<String> values) {
            addCriterion("INPUT_OPER2_TIME in", values, "inputOper2Time");
            return (Criteria) this;
        }

        public Criteria andInputOper2TimeNotIn(List<String> values) {
            addCriterion("INPUT_OPER2_TIME not in", values, "inputOper2Time");
            return (Criteria) this;
        }

        public Criteria andInputOper2TimeBetween(String value1, String value2) {
            addCriterion("INPUT_OPER2_TIME between", value1, value2, "inputOper2Time");
            return (Criteria) this;
        }

        public Criteria andInputOper2TimeNotBetween(String value1, String value2) {
            addCriterion("INPUT_OPER2_TIME not between", value1, value2, "inputOper2Time");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusIdIsNull() {
            addCriterion("INPUT_OPER2_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusIdIsNotNull() {
            addCriterion("INPUT_OPER2_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusIdEqualTo(String value) {
            addCriterion("INPUT_OPER2_STATUS_ID =", value, "inputOper2StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusIdNotEqualTo(String value) {
            addCriterion("INPUT_OPER2_STATUS_ID <>", value, "inputOper2StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusIdGreaterThan(String value) {
            addCriterion("INPUT_OPER2_STATUS_ID >", value, "inputOper2StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER2_STATUS_ID >=", value, "inputOper2StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusIdLessThan(String value) {
            addCriterion("INPUT_OPER2_STATUS_ID <", value, "inputOper2StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusIdLessThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER2_STATUS_ID <=", value, "inputOper2StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusIdLike(String value) {
            addCriterion("INPUT_OPER2_STATUS_ID like", value, "inputOper2StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusIdNotLike(String value) {
            addCriterion("INPUT_OPER2_STATUS_ID not like", value, "inputOper2StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusIdIn(List<String> values) {
            addCriterion("INPUT_OPER2_STATUS_ID in", values, "inputOper2StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusIdNotIn(List<String> values) {
            addCriterion("INPUT_OPER2_STATUS_ID not in", values, "inputOper2StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusIdBetween(String value1, String value2) {
            addCriterion("INPUT_OPER2_STATUS_ID between", value1, value2, "inputOper2StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusIdNotBetween(String value1, String value2) {
            addCriterion("INPUT_OPER2_STATUS_ID not between", value1, value2, "inputOper2StatusId");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusNameIsNull() {
            addCriterion("INPUT_OPER2_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusNameIsNotNull() {
            addCriterion("INPUT_OPER2_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusNameEqualTo(String value) {
            addCriterion("INPUT_OPER2_STATUS_NAME =", value, "inputOper2StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusNameNotEqualTo(String value) {
            addCriterion("INPUT_OPER2_STATUS_NAME <>", value, "inputOper2StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusNameGreaterThan(String value) {
            addCriterion("INPUT_OPER2_STATUS_NAME >", value, "inputOper2StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER2_STATUS_NAME >=", value, "inputOper2StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusNameLessThan(String value) {
            addCriterion("INPUT_OPER2_STATUS_NAME <", value, "inputOper2StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusNameLessThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER2_STATUS_NAME <=", value, "inputOper2StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusNameLike(String value) {
            addCriterion("INPUT_OPER2_STATUS_NAME like", value, "inputOper2StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusNameNotLike(String value) {
            addCriterion("INPUT_OPER2_STATUS_NAME not like", value, "inputOper2StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusNameIn(List<String> values) {
            addCriterion("INPUT_OPER2_STATUS_NAME in", values, "inputOper2StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusNameNotIn(List<String> values) {
            addCriterion("INPUT_OPER2_STATUS_NAME not in", values, "inputOper2StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusNameBetween(String value1, String value2) {
            addCriterion("INPUT_OPER2_STATUS_NAME between", value1, value2, "inputOper2StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOper2StatusNameNotBetween(String value1, String value2) {
            addCriterion("INPUT_OPER2_STATUS_NAME not between", value1, value2, "inputOper2StatusName");
            return (Criteria) this;
        }

        public Criteria andInputOperFlowIsNull() {
            addCriterion("INPUT_OPER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andInputOperFlowIsNotNull() {
            addCriterion("INPUT_OPER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andInputOperFlowEqualTo(String value) {
            addCriterion("INPUT_OPER_FLOW =", value, "inputOperFlow");
            return (Criteria) this;
        }

        public Criteria andInputOperFlowNotEqualTo(String value) {
            addCriterion("INPUT_OPER_FLOW <>", value, "inputOperFlow");
            return (Criteria) this;
        }

        public Criteria andInputOperFlowGreaterThan(String value) {
            addCriterion("INPUT_OPER_FLOW >", value, "inputOperFlow");
            return (Criteria) this;
        }

        public Criteria andInputOperFlowGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER_FLOW >=", value, "inputOperFlow");
            return (Criteria) this;
        }

        public Criteria andInputOperFlowLessThan(String value) {
            addCriterion("INPUT_OPER_FLOW <", value, "inputOperFlow");
            return (Criteria) this;
        }

        public Criteria andInputOperFlowLessThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER_FLOW <=", value, "inputOperFlow");
            return (Criteria) this;
        }

        public Criteria andInputOperFlowLike(String value) {
            addCriterion("INPUT_OPER_FLOW like", value, "inputOperFlow");
            return (Criteria) this;
        }

        public Criteria andInputOperFlowNotLike(String value) {
            addCriterion("INPUT_OPER_FLOW not like", value, "inputOperFlow");
            return (Criteria) this;
        }

        public Criteria andInputOperFlowIn(List<String> values) {
            addCriterion("INPUT_OPER_FLOW in", values, "inputOperFlow");
            return (Criteria) this;
        }

        public Criteria andInputOperFlowNotIn(List<String> values) {
            addCriterion("INPUT_OPER_FLOW not in", values, "inputOperFlow");
            return (Criteria) this;
        }

        public Criteria andInputOperFlowBetween(String value1, String value2) {
            addCriterion("INPUT_OPER_FLOW between", value1, value2, "inputOperFlow");
            return (Criteria) this;
        }

        public Criteria andInputOperFlowNotBetween(String value1, String value2) {
            addCriterion("INPUT_OPER_FLOW not between", value1, value2, "inputOperFlow");
            return (Criteria) this;
        }

        public Criteria andInputOperNameIsNull() {
            addCriterion("INPUT_OPER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andInputOperNameIsNotNull() {
            addCriterion("INPUT_OPER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andInputOperNameEqualTo(String value) {
            addCriterion("INPUT_OPER_NAME =", value, "inputOperName");
            return (Criteria) this;
        }

        public Criteria andInputOperNameNotEqualTo(String value) {
            addCriterion("INPUT_OPER_NAME <>", value, "inputOperName");
            return (Criteria) this;
        }

        public Criteria andInputOperNameGreaterThan(String value) {
            addCriterion("INPUT_OPER_NAME >", value, "inputOperName");
            return (Criteria) this;
        }

        public Criteria andInputOperNameGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER_NAME >=", value, "inputOperName");
            return (Criteria) this;
        }

        public Criteria andInputOperNameLessThan(String value) {
            addCriterion("INPUT_OPER_NAME <", value, "inputOperName");
            return (Criteria) this;
        }

        public Criteria andInputOperNameLessThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER_NAME <=", value, "inputOperName");
            return (Criteria) this;
        }

        public Criteria andInputOperNameLike(String value) {
            addCriterion("INPUT_OPER_NAME like", value, "inputOperName");
            return (Criteria) this;
        }

        public Criteria andInputOperNameNotLike(String value) {
            addCriterion("INPUT_OPER_NAME not like", value, "inputOperName");
            return (Criteria) this;
        }

        public Criteria andInputOperNameIn(List<String> values) {
            addCriterion("INPUT_OPER_NAME in", values, "inputOperName");
            return (Criteria) this;
        }

        public Criteria andInputOperNameNotIn(List<String> values) {
            addCriterion("INPUT_OPER_NAME not in", values, "inputOperName");
            return (Criteria) this;
        }

        public Criteria andInputOperNameBetween(String value1, String value2) {
            addCriterion("INPUT_OPER_NAME between", value1, value2, "inputOperName");
            return (Criteria) this;
        }

        public Criteria andInputOperNameNotBetween(String value1, String value2) {
            addCriterion("INPUT_OPER_NAME not between", value1, value2, "inputOperName");
            return (Criteria) this;
        }

        public Criteria andInputOperTimeIsNull() {
            addCriterion("INPUT_OPER_TIME is null");
            return (Criteria) this;
        }

        public Criteria andInputOperTimeIsNotNull() {
            addCriterion("INPUT_OPER_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andInputOperTimeEqualTo(String value) {
            addCriterion("INPUT_OPER_TIME =", value, "inputOperTime");
            return (Criteria) this;
        }

        public Criteria andInputOperTimeNotEqualTo(String value) {
            addCriterion("INPUT_OPER_TIME <>", value, "inputOperTime");
            return (Criteria) this;
        }

        public Criteria andInputOperTimeGreaterThan(String value) {
            addCriterion("INPUT_OPER_TIME >", value, "inputOperTime");
            return (Criteria) this;
        }

        public Criteria andInputOperTimeGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER_TIME >=", value, "inputOperTime");
            return (Criteria) this;
        }

        public Criteria andInputOperTimeLessThan(String value) {
            addCriterion("INPUT_OPER_TIME <", value, "inputOperTime");
            return (Criteria) this;
        }

        public Criteria andInputOperTimeLessThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER_TIME <=", value, "inputOperTime");
            return (Criteria) this;
        }

        public Criteria andInputOperTimeLike(String value) {
            addCriterion("INPUT_OPER_TIME like", value, "inputOperTime");
            return (Criteria) this;
        }

        public Criteria andInputOperTimeNotLike(String value) {
            addCriterion("INPUT_OPER_TIME not like", value, "inputOperTime");
            return (Criteria) this;
        }

        public Criteria andInputOperTimeIn(List<String> values) {
            addCriterion("INPUT_OPER_TIME in", values, "inputOperTime");
            return (Criteria) this;
        }

        public Criteria andInputOperTimeNotIn(List<String> values) {
            addCriterion("INPUT_OPER_TIME not in", values, "inputOperTime");
            return (Criteria) this;
        }

        public Criteria andInputOperTimeBetween(String value1, String value2) {
            addCriterion("INPUT_OPER_TIME between", value1, value2, "inputOperTime");
            return (Criteria) this;
        }

        public Criteria andInputOperTimeNotBetween(String value1, String value2) {
            addCriterion("INPUT_OPER_TIME not between", value1, value2, "inputOperTime");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusIdIsNull() {
            addCriterion("INPUT_OPER_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusIdIsNotNull() {
            addCriterion("INPUT_OPER_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusIdEqualTo(String value) {
            addCriterion("INPUT_OPER_STATUS_ID =", value, "inputOperStatusId");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusIdNotEqualTo(String value) {
            addCriterion("INPUT_OPER_STATUS_ID <>", value, "inputOperStatusId");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusIdGreaterThan(String value) {
            addCriterion("INPUT_OPER_STATUS_ID >", value, "inputOperStatusId");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER_STATUS_ID >=", value, "inputOperStatusId");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusIdLessThan(String value) {
            addCriterion("INPUT_OPER_STATUS_ID <", value, "inputOperStatusId");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusIdLessThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER_STATUS_ID <=", value, "inputOperStatusId");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusIdLike(String value) {
            addCriterion("INPUT_OPER_STATUS_ID like", value, "inputOperStatusId");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusIdNotLike(String value) {
            addCriterion("INPUT_OPER_STATUS_ID not like", value, "inputOperStatusId");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusIdIn(List<String> values) {
            addCriterion("INPUT_OPER_STATUS_ID in", values, "inputOperStatusId");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusIdNotIn(List<String> values) {
            addCriterion("INPUT_OPER_STATUS_ID not in", values, "inputOperStatusId");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusIdBetween(String value1, String value2) {
            addCriterion("INPUT_OPER_STATUS_ID between", value1, value2, "inputOperStatusId");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusIdNotBetween(String value1, String value2) {
            addCriterion("INPUT_OPER_STATUS_ID not between", value1, value2, "inputOperStatusId");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusNameIsNull() {
            addCriterion("INPUT_OPER_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusNameIsNotNull() {
            addCriterion("INPUT_OPER_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusNameEqualTo(String value) {
            addCriterion("INPUT_OPER_STATUS_NAME =", value, "inputOperStatusName");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusNameNotEqualTo(String value) {
            addCriterion("INPUT_OPER_STATUS_NAME <>", value, "inputOperStatusName");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusNameGreaterThan(String value) {
            addCriterion("INPUT_OPER_STATUS_NAME >", value, "inputOperStatusName");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER_STATUS_NAME >=", value, "inputOperStatusName");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusNameLessThan(String value) {
            addCriterion("INPUT_OPER_STATUS_NAME <", value, "inputOperStatusName");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusNameLessThanOrEqualTo(String value) {
            addCriterion("INPUT_OPER_STATUS_NAME <=", value, "inputOperStatusName");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusNameLike(String value) {
            addCriterion("INPUT_OPER_STATUS_NAME like", value, "inputOperStatusName");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusNameNotLike(String value) {
            addCriterion("INPUT_OPER_STATUS_NAME not like", value, "inputOperStatusName");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusNameIn(List<String> values) {
            addCriterion("INPUT_OPER_STATUS_NAME in", values, "inputOperStatusName");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusNameNotIn(List<String> values) {
            addCriterion("INPUT_OPER_STATUS_NAME not in", values, "inputOperStatusName");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusNameBetween(String value1, String value2) {
            addCriterion("INPUT_OPER_STATUS_NAME between", value1, value2, "inputOperStatusName");
            return (Criteria) this;
        }

        public Criteria andInputOperStatusNameNotBetween(String value1, String value2) {
            addCriterion("INPUT_OPER_STATUS_NAME not between", value1, value2, "inputOperStatusName");
            return (Criteria) this;
        }

        public Criteria andSdvOperFlowIsNull() {
            addCriterion("SDV_OPER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSdvOperFlowIsNotNull() {
            addCriterion("SDV_OPER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSdvOperFlowEqualTo(String value) {
            addCriterion("SDV_OPER_FLOW =", value, "sdvOperFlow");
            return (Criteria) this;
        }

        public Criteria andSdvOperFlowNotEqualTo(String value) {
            addCriterion("SDV_OPER_FLOW <>", value, "sdvOperFlow");
            return (Criteria) this;
        }

        public Criteria andSdvOperFlowGreaterThan(String value) {
            addCriterion("SDV_OPER_FLOW >", value, "sdvOperFlow");
            return (Criteria) this;
        }

        public Criteria andSdvOperFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SDV_OPER_FLOW >=", value, "sdvOperFlow");
            return (Criteria) this;
        }

        public Criteria andSdvOperFlowLessThan(String value) {
            addCriterion("SDV_OPER_FLOW <", value, "sdvOperFlow");
            return (Criteria) this;
        }

        public Criteria andSdvOperFlowLessThanOrEqualTo(String value) {
            addCriterion("SDV_OPER_FLOW <=", value, "sdvOperFlow");
            return (Criteria) this;
        }

        public Criteria andSdvOperFlowLike(String value) {
            addCriterion("SDV_OPER_FLOW like", value, "sdvOperFlow");
            return (Criteria) this;
        }

        public Criteria andSdvOperFlowNotLike(String value) {
            addCriterion("SDV_OPER_FLOW not like", value, "sdvOperFlow");
            return (Criteria) this;
        }

        public Criteria andSdvOperFlowIn(List<String> values) {
            addCriterion("SDV_OPER_FLOW in", values, "sdvOperFlow");
            return (Criteria) this;
        }

        public Criteria andSdvOperFlowNotIn(List<String> values) {
            addCriterion("SDV_OPER_FLOW not in", values, "sdvOperFlow");
            return (Criteria) this;
        }

        public Criteria andSdvOperFlowBetween(String value1, String value2) {
            addCriterion("SDV_OPER_FLOW between", value1, value2, "sdvOperFlow");
            return (Criteria) this;
        }

        public Criteria andSdvOperFlowNotBetween(String value1, String value2) {
            addCriterion("SDV_OPER_FLOW not between", value1, value2, "sdvOperFlow");
            return (Criteria) this;
        }

        public Criteria andSdvOperNameIsNull() {
            addCriterion("SDV_OPER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSdvOperNameIsNotNull() {
            addCriterion("SDV_OPER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSdvOperNameEqualTo(String value) {
            addCriterion("SDV_OPER_NAME =", value, "sdvOperName");
            return (Criteria) this;
        }

        public Criteria andSdvOperNameNotEqualTo(String value) {
            addCriterion("SDV_OPER_NAME <>", value, "sdvOperName");
            return (Criteria) this;
        }

        public Criteria andSdvOperNameGreaterThan(String value) {
            addCriterion("SDV_OPER_NAME >", value, "sdvOperName");
            return (Criteria) this;
        }

        public Criteria andSdvOperNameGreaterThanOrEqualTo(String value) {
            addCriterion("SDV_OPER_NAME >=", value, "sdvOperName");
            return (Criteria) this;
        }

        public Criteria andSdvOperNameLessThan(String value) {
            addCriterion("SDV_OPER_NAME <", value, "sdvOperName");
            return (Criteria) this;
        }

        public Criteria andSdvOperNameLessThanOrEqualTo(String value) {
            addCriterion("SDV_OPER_NAME <=", value, "sdvOperName");
            return (Criteria) this;
        }

        public Criteria andSdvOperNameLike(String value) {
            addCriterion("SDV_OPER_NAME like", value, "sdvOperName");
            return (Criteria) this;
        }

        public Criteria andSdvOperNameNotLike(String value) {
            addCriterion("SDV_OPER_NAME not like", value, "sdvOperName");
            return (Criteria) this;
        }

        public Criteria andSdvOperNameIn(List<String> values) {
            addCriterion("SDV_OPER_NAME in", values, "sdvOperName");
            return (Criteria) this;
        }

        public Criteria andSdvOperNameNotIn(List<String> values) {
            addCriterion("SDV_OPER_NAME not in", values, "sdvOperName");
            return (Criteria) this;
        }

        public Criteria andSdvOperNameBetween(String value1, String value2) {
            addCriterion("SDV_OPER_NAME between", value1, value2, "sdvOperName");
            return (Criteria) this;
        }

        public Criteria andSdvOperNameNotBetween(String value1, String value2) {
            addCriterion("SDV_OPER_NAME not between", value1, value2, "sdvOperName");
            return (Criteria) this;
        }

        public Criteria andSdvOperTimeIsNull() {
            addCriterion("SDV_OPER_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSdvOperTimeIsNotNull() {
            addCriterion("SDV_OPER_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSdvOperTimeEqualTo(String value) {
            addCriterion("SDV_OPER_TIME =", value, "sdvOperTime");
            return (Criteria) this;
        }

        public Criteria andSdvOperTimeNotEqualTo(String value) {
            addCriterion("SDV_OPER_TIME <>", value, "sdvOperTime");
            return (Criteria) this;
        }

        public Criteria andSdvOperTimeGreaterThan(String value) {
            addCriterion("SDV_OPER_TIME >", value, "sdvOperTime");
            return (Criteria) this;
        }

        public Criteria andSdvOperTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SDV_OPER_TIME >=", value, "sdvOperTime");
            return (Criteria) this;
        }

        public Criteria andSdvOperTimeLessThan(String value) {
            addCriterion("SDV_OPER_TIME <", value, "sdvOperTime");
            return (Criteria) this;
        }

        public Criteria andSdvOperTimeLessThanOrEqualTo(String value) {
            addCriterion("SDV_OPER_TIME <=", value, "sdvOperTime");
            return (Criteria) this;
        }

        public Criteria andSdvOperTimeLike(String value) {
            addCriterion("SDV_OPER_TIME like", value, "sdvOperTime");
            return (Criteria) this;
        }

        public Criteria andSdvOperTimeNotLike(String value) {
            addCriterion("SDV_OPER_TIME not like", value, "sdvOperTime");
            return (Criteria) this;
        }

        public Criteria andSdvOperTimeIn(List<String> values) {
            addCriterion("SDV_OPER_TIME in", values, "sdvOperTime");
            return (Criteria) this;
        }

        public Criteria andSdvOperTimeNotIn(List<String> values) {
            addCriterion("SDV_OPER_TIME not in", values, "sdvOperTime");
            return (Criteria) this;
        }

        public Criteria andSdvOperTimeBetween(String value1, String value2) {
            addCriterion("SDV_OPER_TIME between", value1, value2, "sdvOperTime");
            return (Criteria) this;
        }

        public Criteria andSdvOperTimeNotBetween(String value1, String value2) {
            addCriterion("SDV_OPER_TIME not between", value1, value2, "sdvOperTime");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusIdIsNull() {
            addCriterion("SDV_OPER_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusIdIsNotNull() {
            addCriterion("SDV_OPER_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusIdEqualTo(String value) {
            addCriterion("SDV_OPER_STATUS_ID =", value, "sdvOperStatusId");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusIdNotEqualTo(String value) {
            addCriterion("SDV_OPER_STATUS_ID <>", value, "sdvOperStatusId");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusIdGreaterThan(String value) {
            addCriterion("SDV_OPER_STATUS_ID >", value, "sdvOperStatusId");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("SDV_OPER_STATUS_ID >=", value, "sdvOperStatusId");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusIdLessThan(String value) {
            addCriterion("SDV_OPER_STATUS_ID <", value, "sdvOperStatusId");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusIdLessThanOrEqualTo(String value) {
            addCriterion("SDV_OPER_STATUS_ID <=", value, "sdvOperStatusId");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusIdLike(String value) {
            addCriterion("SDV_OPER_STATUS_ID like", value, "sdvOperStatusId");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusIdNotLike(String value) {
            addCriterion("SDV_OPER_STATUS_ID not like", value, "sdvOperStatusId");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusIdIn(List<String> values) {
            addCriterion("SDV_OPER_STATUS_ID in", values, "sdvOperStatusId");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusIdNotIn(List<String> values) {
            addCriterion("SDV_OPER_STATUS_ID not in", values, "sdvOperStatusId");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusIdBetween(String value1, String value2) {
            addCriterion("SDV_OPER_STATUS_ID between", value1, value2, "sdvOperStatusId");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusIdNotBetween(String value1, String value2) {
            addCriterion("SDV_OPER_STATUS_ID not between", value1, value2, "sdvOperStatusId");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusNameIsNull() {
            addCriterion("SDV_OPER_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusNameIsNotNull() {
            addCriterion("SDV_OPER_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusNameEqualTo(String value) {
            addCriterion("SDV_OPER_STATUS_NAME =", value, "sdvOperStatusName");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusNameNotEqualTo(String value) {
            addCriterion("SDV_OPER_STATUS_NAME <>", value, "sdvOperStatusName");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusNameGreaterThan(String value) {
            addCriterion("SDV_OPER_STATUS_NAME >", value, "sdvOperStatusName");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("SDV_OPER_STATUS_NAME >=", value, "sdvOperStatusName");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusNameLessThan(String value) {
            addCriterion("SDV_OPER_STATUS_NAME <", value, "sdvOperStatusName");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusNameLessThanOrEqualTo(String value) {
            addCriterion("SDV_OPER_STATUS_NAME <=", value, "sdvOperStatusName");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusNameLike(String value) {
            addCriterion("SDV_OPER_STATUS_NAME like", value, "sdvOperStatusName");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusNameNotLike(String value) {
            addCriterion("SDV_OPER_STATUS_NAME not like", value, "sdvOperStatusName");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusNameIn(List<String> values) {
            addCriterion("SDV_OPER_STATUS_NAME in", values, "sdvOperStatusName");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusNameNotIn(List<String> values) {
            addCriterion("SDV_OPER_STATUS_NAME not in", values, "sdvOperStatusName");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusNameBetween(String value1, String value2) {
            addCriterion("SDV_OPER_STATUS_NAME between", value1, value2, "sdvOperStatusName");
            return (Criteria) this;
        }

        public Criteria andSdvOperStatusNameNotBetween(String value1, String value2) {
            addCriterion("SDV_OPER_STATUS_NAME not between", value1, value2, "sdvOperStatusName");
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