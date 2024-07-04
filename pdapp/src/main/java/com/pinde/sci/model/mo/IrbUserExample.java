package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class IrbUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IrbUserExample() {
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

        public Criteria andProjFlowIsNull() {
            addCriterion("PROJ_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andProjFlowIsNotNull() {
            addCriterion("PROJ_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andProjFlowEqualTo(String value) {
            addCriterion("PROJ_FLOW =", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotEqualTo(String value) {
            addCriterion("PROJ_FLOW <>", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowGreaterThan(String value) {
            addCriterion("PROJ_FLOW >", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_FLOW >=", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowLessThan(String value) {
            addCriterion("PROJ_FLOW <", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowLessThanOrEqualTo(String value) {
            addCriterion("PROJ_FLOW <=", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowLike(String value) {
            addCriterion("PROJ_FLOW like", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotLike(String value) {
            addCriterion("PROJ_FLOW not like", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowIn(List<String> values) {
            addCriterion("PROJ_FLOW in", values, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotIn(List<String> values) {
            addCriterion("PROJ_FLOW not in", values, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowBetween(String value1, String value2) {
            addCriterion("PROJ_FLOW between", value1, value2, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotBetween(String value1, String value2) {
            addCriterion("PROJ_FLOW not between", value1, value2, "projFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowIsNull() {
            addCriterion("IRB_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andIrbFlowIsNotNull() {
            addCriterion("IRB_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andIrbFlowEqualTo(String value) {
            addCriterion("IRB_FLOW =", value, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowNotEqualTo(String value) {
            addCriterion("IRB_FLOW <>", value, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowGreaterThan(String value) {
            addCriterion("IRB_FLOW >", value, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowGreaterThanOrEqualTo(String value) {
            addCriterion("IRB_FLOW >=", value, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowLessThan(String value) {
            addCriterion("IRB_FLOW <", value, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowLessThanOrEqualTo(String value) {
            addCriterion("IRB_FLOW <=", value, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowLike(String value) {
            addCriterion("IRB_FLOW like", value, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowNotLike(String value) {
            addCriterion("IRB_FLOW not like", value, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowIn(List<String> values) {
            addCriterion("IRB_FLOW in", values, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowNotIn(List<String> values) {
            addCriterion("IRB_FLOW not in", values, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowBetween(String value1, String value2) {
            addCriterion("IRB_FLOW between", value1, value2, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowNotBetween(String value1, String value2) {
            addCriterion("IRB_FLOW not between", value1, value2, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowIsNull() {
            addCriterion("USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andUserFlowIsNotNull() {
            addCriterion("USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andUserFlowEqualTo(String value) {
            addCriterion("USER_FLOW =", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotEqualTo(String value) {
            addCriterion("USER_FLOW <>", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowGreaterThan(String value) {
            addCriterion("USER_FLOW >", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("USER_FLOW >=", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLessThan(String value) {
            addCriterion("USER_FLOW <", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLessThanOrEqualTo(String value) {
            addCriterion("USER_FLOW <=", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLike(String value) {
            addCriterion("USER_FLOW like", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotLike(String value) {
            addCriterion("USER_FLOW not like", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowIn(List<String> values) {
            addCriterion("USER_FLOW in", values, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotIn(List<String> values) {
            addCriterion("USER_FLOW not in", values, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowBetween(String value1, String value2) {
            addCriterion("USER_FLOW between", value1, value2, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotBetween(String value1, String value2) {
            addCriterion("USER_FLOW not between", value1, value2, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andAuthIdIsNull() {
            addCriterion("AUTH_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuthIdIsNotNull() {
            addCriterion("AUTH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuthIdEqualTo(String value) {
            addCriterion("AUTH_ID =", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdNotEqualTo(String value) {
            addCriterion("AUTH_ID <>", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdGreaterThan(String value) {
            addCriterion("AUTH_ID >", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdGreaterThanOrEqualTo(String value) {
            addCriterion("AUTH_ID >=", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdLessThan(String value) {
            addCriterion("AUTH_ID <", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdLessThanOrEqualTo(String value) {
            addCriterion("AUTH_ID <=", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdLike(String value) {
            addCriterion("AUTH_ID like", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdNotLike(String value) {
            addCriterion("AUTH_ID not like", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdIn(List<String> values) {
            addCriterion("AUTH_ID in", values, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdNotIn(List<String> values) {
            addCriterion("AUTH_ID not in", values, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdBetween(String value1, String value2) {
            addCriterion("AUTH_ID between", value1, value2, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdNotBetween(String value1, String value2) {
            addCriterion("AUTH_ID not between", value1, value2, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthNameIsNull() {
            addCriterion("AUTH_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuthNameIsNotNull() {
            addCriterion("AUTH_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuthNameEqualTo(String value) {
            addCriterion("AUTH_NAME =", value, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameNotEqualTo(String value) {
            addCriterion("AUTH_NAME <>", value, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameGreaterThan(String value) {
            addCriterion("AUTH_NAME >", value, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUTH_NAME >=", value, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameLessThan(String value) {
            addCriterion("AUTH_NAME <", value, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameLessThanOrEqualTo(String value) {
            addCriterion("AUTH_NAME <=", value, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameLike(String value) {
            addCriterion("AUTH_NAME like", value, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameNotLike(String value) {
            addCriterion("AUTH_NAME not like", value, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameIn(List<String> values) {
            addCriterion("AUTH_NAME in", values, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameNotIn(List<String> values) {
            addCriterion("AUTH_NAME not in", values, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameBetween(String value1, String value2) {
            addCriterion("AUTH_NAME between", value1, value2, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthNameNotBetween(String value1, String value2) {
            addCriterion("AUTH_NAME not between", value1, value2, "authName");
            return (Criteria) this;
        }

        public Criteria andAuthDecisionIsNull() {
            addCriterion("AUTH_DECISION is null");
            return (Criteria) this;
        }

        public Criteria andAuthDecisionIsNotNull() {
            addCriterion("AUTH_DECISION is not null");
            return (Criteria) this;
        }

        public Criteria andAuthDecisionEqualTo(String value) {
            addCriterion("AUTH_DECISION =", value, "authDecision");
            return (Criteria) this;
        }

        public Criteria andAuthDecisionNotEqualTo(String value) {
            addCriterion("AUTH_DECISION <>", value, "authDecision");
            return (Criteria) this;
        }

        public Criteria andAuthDecisionGreaterThan(String value) {
            addCriterion("AUTH_DECISION >", value, "authDecision");
            return (Criteria) this;
        }

        public Criteria andAuthDecisionGreaterThanOrEqualTo(String value) {
            addCriterion("AUTH_DECISION >=", value, "authDecision");
            return (Criteria) this;
        }

        public Criteria andAuthDecisionLessThan(String value) {
            addCriterion("AUTH_DECISION <", value, "authDecision");
            return (Criteria) this;
        }

        public Criteria andAuthDecisionLessThanOrEqualTo(String value) {
            addCriterion("AUTH_DECISION <=", value, "authDecision");
            return (Criteria) this;
        }

        public Criteria andAuthDecisionLike(String value) {
            addCriterion("AUTH_DECISION like", value, "authDecision");
            return (Criteria) this;
        }

        public Criteria andAuthDecisionNotLike(String value) {
            addCriterion("AUTH_DECISION not like", value, "authDecision");
            return (Criteria) this;
        }

        public Criteria andAuthDecisionIn(List<String> values) {
            addCriterion("AUTH_DECISION in", values, "authDecision");
            return (Criteria) this;
        }

        public Criteria andAuthDecisionNotIn(List<String> values) {
            addCriterion("AUTH_DECISION not in", values, "authDecision");
            return (Criteria) this;
        }

        public Criteria andAuthDecisionBetween(String value1, String value2) {
            addCriterion("AUTH_DECISION between", value1, value2, "authDecision");
            return (Criteria) this;
        }

        public Criteria andAuthDecisionNotBetween(String value1, String value2) {
            addCriterion("AUTH_DECISION not between", value1, value2, "authDecision");
            return (Criteria) this;
        }

        public Criteria andAuthNoteIsNull() {
            addCriterion("AUTH_NOTE is null");
            return (Criteria) this;
        }

        public Criteria andAuthNoteIsNotNull() {
            addCriterion("AUTH_NOTE is not null");
            return (Criteria) this;
        }

        public Criteria andAuthNoteEqualTo(String value) {
            addCriterion("AUTH_NOTE =", value, "authNote");
            return (Criteria) this;
        }

        public Criteria andAuthNoteNotEqualTo(String value) {
            addCriterion("AUTH_NOTE <>", value, "authNote");
            return (Criteria) this;
        }

        public Criteria andAuthNoteGreaterThan(String value) {
            addCriterion("AUTH_NOTE >", value, "authNote");
            return (Criteria) this;
        }

        public Criteria andAuthNoteGreaterThanOrEqualTo(String value) {
            addCriterion("AUTH_NOTE >=", value, "authNote");
            return (Criteria) this;
        }

        public Criteria andAuthNoteLessThan(String value) {
            addCriterion("AUTH_NOTE <", value, "authNote");
            return (Criteria) this;
        }

        public Criteria andAuthNoteLessThanOrEqualTo(String value) {
            addCriterion("AUTH_NOTE <=", value, "authNote");
            return (Criteria) this;
        }

        public Criteria andAuthNoteLike(String value) {
            addCriterion("AUTH_NOTE like", value, "authNote");
            return (Criteria) this;
        }

        public Criteria andAuthNoteNotLike(String value) {
            addCriterion("AUTH_NOTE not like", value, "authNote");
            return (Criteria) this;
        }

        public Criteria andAuthNoteIn(List<String> values) {
            addCriterion("AUTH_NOTE in", values, "authNote");
            return (Criteria) this;
        }

        public Criteria andAuthNoteNotIn(List<String> values) {
            addCriterion("AUTH_NOTE not in", values, "authNote");
            return (Criteria) this;
        }

        public Criteria andAuthNoteBetween(String value1, String value2) {
            addCriterion("AUTH_NOTE between", value1, value2, "authNote");
            return (Criteria) this;
        }

        public Criteria andAuthNoteNotBetween(String value1, String value2) {
            addCriterion("AUTH_NOTE not between", value1, value2, "authNote");
            return (Criteria) this;
        }

        public Criteria andAuthTimeIsNull() {
            addCriterion("AUTH_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAuthTimeIsNotNull() {
            addCriterion("AUTH_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAuthTimeEqualTo(String value) {
            addCriterion("AUTH_TIME =", value, "authTime");
            return (Criteria) this;
        }

        public Criteria andAuthTimeNotEqualTo(String value) {
            addCriterion("AUTH_TIME <>", value, "authTime");
            return (Criteria) this;
        }

        public Criteria andAuthTimeGreaterThan(String value) {
            addCriterion("AUTH_TIME >", value, "authTime");
            return (Criteria) this;
        }

        public Criteria andAuthTimeGreaterThanOrEqualTo(String value) {
            addCriterion("AUTH_TIME >=", value, "authTime");
            return (Criteria) this;
        }

        public Criteria andAuthTimeLessThan(String value) {
            addCriterion("AUTH_TIME <", value, "authTime");
            return (Criteria) this;
        }

        public Criteria andAuthTimeLessThanOrEqualTo(String value) {
            addCriterion("AUTH_TIME <=", value, "authTime");
            return (Criteria) this;
        }

        public Criteria andAuthTimeLike(String value) {
            addCriterion("AUTH_TIME like", value, "authTime");
            return (Criteria) this;
        }

        public Criteria andAuthTimeNotLike(String value) {
            addCriterion("AUTH_TIME not like", value, "authTime");
            return (Criteria) this;
        }

        public Criteria andAuthTimeIn(List<String> values) {
            addCriterion("AUTH_TIME in", values, "authTime");
            return (Criteria) this;
        }

        public Criteria andAuthTimeNotIn(List<String> values) {
            addCriterion("AUTH_TIME not in", values, "authTime");
            return (Criteria) this;
        }

        public Criteria andAuthTimeBetween(String value1, String value2) {
            addCriterion("AUTH_TIME between", value1, value2, "authTime");
            return (Criteria) this;
        }

        public Criteria andAuthTimeNotBetween(String value1, String value2) {
            addCriterion("AUTH_TIME not between", value1, value2, "authTime");
            return (Criteria) this;
        }

        public Criteria andAuthUserFlowIsNull() {
            addCriterion("AUTH_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAuthUserFlowIsNotNull() {
            addCriterion("AUTH_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAuthUserFlowEqualTo(String value) {
            addCriterion("AUTH_USER_FLOW =", value, "authUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuthUserFlowNotEqualTo(String value) {
            addCriterion("AUTH_USER_FLOW <>", value, "authUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuthUserFlowGreaterThan(String value) {
            addCriterion("AUTH_USER_FLOW >", value, "authUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuthUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("AUTH_USER_FLOW >=", value, "authUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuthUserFlowLessThan(String value) {
            addCriterion("AUTH_USER_FLOW <", value, "authUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuthUserFlowLessThanOrEqualTo(String value) {
            addCriterion("AUTH_USER_FLOW <=", value, "authUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuthUserFlowLike(String value) {
            addCriterion("AUTH_USER_FLOW like", value, "authUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuthUserFlowNotLike(String value) {
            addCriterion("AUTH_USER_FLOW not like", value, "authUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuthUserFlowIn(List<String> values) {
            addCriterion("AUTH_USER_FLOW in", values, "authUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuthUserFlowNotIn(List<String> values) {
            addCriterion("AUTH_USER_FLOW not in", values, "authUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuthUserFlowBetween(String value1, String value2) {
            addCriterion("AUTH_USER_FLOW between", value1, value2, "authUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuthUserFlowNotBetween(String value1, String value2) {
            addCriterion("AUTH_USER_FLOW not between", value1, value2, "authUserFlow");
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