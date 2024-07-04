package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EdcQueryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdcQueryExample() {
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

        public Criteria andQueryFlowIsNull() {
            addCriterion("QUERY_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andQueryFlowIsNotNull() {
            addCriterion("QUERY_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andQueryFlowEqualTo(String value) {
            addCriterion("QUERY_FLOW =", value, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowNotEqualTo(String value) {
            addCriterion("QUERY_FLOW <>", value, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowGreaterThan(String value) {
            addCriterion("QUERY_FLOW >", value, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowGreaterThanOrEqualTo(String value) {
            addCriterion("QUERY_FLOW >=", value, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowLessThan(String value) {
            addCriterion("QUERY_FLOW <", value, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowLessThanOrEqualTo(String value) {
            addCriterion("QUERY_FLOW <=", value, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowLike(String value) {
            addCriterion("QUERY_FLOW like", value, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowNotLike(String value) {
            addCriterion("QUERY_FLOW not like", value, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowIn(List<String> values) {
            addCriterion("QUERY_FLOW in", values, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowNotIn(List<String> values) {
            addCriterion("QUERY_FLOW not in", values, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowBetween(String value1, String value2) {
            addCriterion("QUERY_FLOW between", value1, value2, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowNotBetween(String value1, String value2) {
            addCriterion("QUERY_FLOW not between", value1, value2, "queryFlow");
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

        public Criteria andPatientFlowIsNull() {
            addCriterion("PATIENT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPatientFlowIsNotNull() {
            addCriterion("PATIENT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPatientFlowEqualTo(String value) {
            addCriterion("PATIENT_FLOW =", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowNotEqualTo(String value) {
            addCriterion("PATIENT_FLOW <>", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowGreaterThan(String value) {
            addCriterion("PATIENT_FLOW >", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_FLOW >=", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowLessThan(String value) {
            addCriterion("PATIENT_FLOW <", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_FLOW <=", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowLike(String value) {
            addCriterion("PATIENT_FLOW like", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowNotLike(String value) {
            addCriterion("PATIENT_FLOW not like", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowIn(List<String> values) {
            addCriterion("PATIENT_FLOW in", values, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowNotIn(List<String> values) {
            addCriterion("PATIENT_FLOW not in", values, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowBetween(String value1, String value2) {
            addCriterion("PATIENT_FLOW between", value1, value2, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowNotBetween(String value1, String value2) {
            addCriterion("PATIENT_FLOW not between", value1, value2, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientCodeIsNull() {
            addCriterion("PATIENT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPatientCodeIsNotNull() {
            addCriterion("PATIENT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPatientCodeEqualTo(String value) {
            addCriterion("PATIENT_CODE =", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeNotEqualTo(String value) {
            addCriterion("PATIENT_CODE <>", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeGreaterThan(String value) {
            addCriterion("PATIENT_CODE >", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_CODE >=", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeLessThan(String value) {
            addCriterion("PATIENT_CODE <", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_CODE <=", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeLike(String value) {
            addCriterion("PATIENT_CODE like", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeNotLike(String value) {
            addCriterion("PATIENT_CODE not like", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeIn(List<String> values) {
            addCriterion("PATIENT_CODE in", values, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeNotIn(List<String> values) {
            addCriterion("PATIENT_CODE not in", values, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeBetween(String value1, String value2) {
            addCriterion("PATIENT_CODE between", value1, value2, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeNotBetween(String value1, String value2) {
            addCriterion("PATIENT_CODE not between", value1, value2, "patientCode");
            return (Criteria) this;
        }

        public Criteria andQuerySeqIsNull() {
            addCriterion("QUERY_SEQ is null");
            return (Criteria) this;
        }

        public Criteria andQuerySeqIsNotNull() {
            addCriterion("QUERY_SEQ is not null");
            return (Criteria) this;
        }

        public Criteria andQuerySeqEqualTo(Integer value) {
            addCriterion("QUERY_SEQ =", value, "querySeq");
            return (Criteria) this;
        }

        public Criteria andQuerySeqNotEqualTo(Integer value) {
            addCriterion("QUERY_SEQ <>", value, "querySeq");
            return (Criteria) this;
        }

        public Criteria andQuerySeqGreaterThan(Integer value) {
            addCriterion("QUERY_SEQ >", value, "querySeq");
            return (Criteria) this;
        }

        public Criteria andQuerySeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("QUERY_SEQ >=", value, "querySeq");
            return (Criteria) this;
        }

        public Criteria andQuerySeqLessThan(Integer value) {
            addCriterion("QUERY_SEQ <", value, "querySeq");
            return (Criteria) this;
        }

        public Criteria andQuerySeqLessThanOrEqualTo(Integer value) {
            addCriterion("QUERY_SEQ <=", value, "querySeq");
            return (Criteria) this;
        }

        public Criteria andQuerySeqIn(List<Integer> values) {
            addCriterion("QUERY_SEQ in", values, "querySeq");
            return (Criteria) this;
        }

        public Criteria andQuerySeqNotIn(List<Integer> values) {
            addCriterion("QUERY_SEQ not in", values, "querySeq");
            return (Criteria) this;
        }

        public Criteria andQuerySeqBetween(Integer value1, Integer value2) {
            addCriterion("QUERY_SEQ between", value1, value2, "querySeq");
            return (Criteria) this;
        }

        public Criteria andQuerySeqNotBetween(Integer value1, Integer value2) {
            addCriterion("QUERY_SEQ not between", value1, value2, "querySeq");
            return (Criteria) this;
        }

        public Criteria andQueryStatusIdIsNull() {
            addCriterion("QUERY_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andQueryStatusIdIsNotNull() {
            addCriterion("QUERY_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andQueryStatusIdEqualTo(String value) {
            addCriterion("QUERY_STATUS_ID =", value, "queryStatusId");
            return (Criteria) this;
        }

        public Criteria andQueryStatusIdNotEqualTo(String value) {
            addCriterion("QUERY_STATUS_ID <>", value, "queryStatusId");
            return (Criteria) this;
        }

        public Criteria andQueryStatusIdGreaterThan(String value) {
            addCriterion("QUERY_STATUS_ID >", value, "queryStatusId");
            return (Criteria) this;
        }

        public Criteria andQueryStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("QUERY_STATUS_ID >=", value, "queryStatusId");
            return (Criteria) this;
        }

        public Criteria andQueryStatusIdLessThan(String value) {
            addCriterion("QUERY_STATUS_ID <", value, "queryStatusId");
            return (Criteria) this;
        }

        public Criteria andQueryStatusIdLessThanOrEqualTo(String value) {
            addCriterion("QUERY_STATUS_ID <=", value, "queryStatusId");
            return (Criteria) this;
        }

        public Criteria andQueryStatusIdLike(String value) {
            addCriterion("QUERY_STATUS_ID like", value, "queryStatusId");
            return (Criteria) this;
        }

        public Criteria andQueryStatusIdNotLike(String value) {
            addCriterion("QUERY_STATUS_ID not like", value, "queryStatusId");
            return (Criteria) this;
        }

        public Criteria andQueryStatusIdIn(List<String> values) {
            addCriterion("QUERY_STATUS_ID in", values, "queryStatusId");
            return (Criteria) this;
        }

        public Criteria andQueryStatusIdNotIn(List<String> values) {
            addCriterion("QUERY_STATUS_ID not in", values, "queryStatusId");
            return (Criteria) this;
        }

        public Criteria andQueryStatusIdBetween(String value1, String value2) {
            addCriterion("QUERY_STATUS_ID between", value1, value2, "queryStatusId");
            return (Criteria) this;
        }

        public Criteria andQueryStatusIdNotBetween(String value1, String value2) {
            addCriterion("QUERY_STATUS_ID not between", value1, value2, "queryStatusId");
            return (Criteria) this;
        }

        public Criteria andQueryStatusNameIsNull() {
            addCriterion("QUERY_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andQueryStatusNameIsNotNull() {
            addCriterion("QUERY_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andQueryStatusNameEqualTo(String value) {
            addCriterion("QUERY_STATUS_NAME =", value, "queryStatusName");
            return (Criteria) this;
        }

        public Criteria andQueryStatusNameNotEqualTo(String value) {
            addCriterion("QUERY_STATUS_NAME <>", value, "queryStatusName");
            return (Criteria) this;
        }

        public Criteria andQueryStatusNameGreaterThan(String value) {
            addCriterion("QUERY_STATUS_NAME >", value, "queryStatusName");
            return (Criteria) this;
        }

        public Criteria andQueryStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("QUERY_STATUS_NAME >=", value, "queryStatusName");
            return (Criteria) this;
        }

        public Criteria andQueryStatusNameLessThan(String value) {
            addCriterion("QUERY_STATUS_NAME <", value, "queryStatusName");
            return (Criteria) this;
        }

        public Criteria andQueryStatusNameLessThanOrEqualTo(String value) {
            addCriterion("QUERY_STATUS_NAME <=", value, "queryStatusName");
            return (Criteria) this;
        }

        public Criteria andQueryStatusNameLike(String value) {
            addCriterion("QUERY_STATUS_NAME like", value, "queryStatusName");
            return (Criteria) this;
        }

        public Criteria andQueryStatusNameNotLike(String value) {
            addCriterion("QUERY_STATUS_NAME not like", value, "queryStatusName");
            return (Criteria) this;
        }

        public Criteria andQueryStatusNameIn(List<String> values) {
            addCriterion("QUERY_STATUS_NAME in", values, "queryStatusName");
            return (Criteria) this;
        }

        public Criteria andQueryStatusNameNotIn(List<String> values) {
            addCriterion("QUERY_STATUS_NAME not in", values, "queryStatusName");
            return (Criteria) this;
        }

        public Criteria andQueryStatusNameBetween(String value1, String value2) {
            addCriterion("QUERY_STATUS_NAME between", value1, value2, "queryStatusName");
            return (Criteria) this;
        }

        public Criteria andQueryStatusNameNotBetween(String value1, String value2) {
            addCriterion("QUERY_STATUS_NAME not between", value1, value2, "queryStatusName");
            return (Criteria) this;
        }

        public Criteria andSendUserFlowIsNull() {
            addCriterion("SEND_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSendUserFlowIsNotNull() {
            addCriterion("SEND_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSendUserFlowEqualTo(String value) {
            addCriterion("SEND_USER_FLOW =", value, "sendUserFlow");
            return (Criteria) this;
        }

        public Criteria andSendUserFlowNotEqualTo(String value) {
            addCriterion("SEND_USER_FLOW <>", value, "sendUserFlow");
            return (Criteria) this;
        }

        public Criteria andSendUserFlowGreaterThan(String value) {
            addCriterion("SEND_USER_FLOW >", value, "sendUserFlow");
            return (Criteria) this;
        }

        public Criteria andSendUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_USER_FLOW >=", value, "sendUserFlow");
            return (Criteria) this;
        }

        public Criteria andSendUserFlowLessThan(String value) {
            addCriterion("SEND_USER_FLOW <", value, "sendUserFlow");
            return (Criteria) this;
        }

        public Criteria andSendUserFlowLessThanOrEqualTo(String value) {
            addCriterion("SEND_USER_FLOW <=", value, "sendUserFlow");
            return (Criteria) this;
        }

        public Criteria andSendUserFlowLike(String value) {
            addCriterion("SEND_USER_FLOW like", value, "sendUserFlow");
            return (Criteria) this;
        }

        public Criteria andSendUserFlowNotLike(String value) {
            addCriterion("SEND_USER_FLOW not like", value, "sendUserFlow");
            return (Criteria) this;
        }

        public Criteria andSendUserFlowIn(List<String> values) {
            addCriterion("SEND_USER_FLOW in", values, "sendUserFlow");
            return (Criteria) this;
        }

        public Criteria andSendUserFlowNotIn(List<String> values) {
            addCriterion("SEND_USER_FLOW not in", values, "sendUserFlow");
            return (Criteria) this;
        }

        public Criteria andSendUserFlowBetween(String value1, String value2) {
            addCriterion("SEND_USER_FLOW between", value1, value2, "sendUserFlow");
            return (Criteria) this;
        }

        public Criteria andSendUserFlowNotBetween(String value1, String value2) {
            addCriterion("SEND_USER_FLOW not between", value1, value2, "sendUserFlow");
            return (Criteria) this;
        }

        public Criteria andSendUserNameIsNull() {
            addCriterion("SEND_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSendUserNameIsNotNull() {
            addCriterion("SEND_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSendUserNameEqualTo(String value) {
            addCriterion("SEND_USER_NAME =", value, "sendUserName");
            return (Criteria) this;
        }

        public Criteria andSendUserNameNotEqualTo(String value) {
            addCriterion("SEND_USER_NAME <>", value, "sendUserName");
            return (Criteria) this;
        }

        public Criteria andSendUserNameGreaterThan(String value) {
            addCriterion("SEND_USER_NAME >", value, "sendUserName");
            return (Criteria) this;
        }

        public Criteria andSendUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_USER_NAME >=", value, "sendUserName");
            return (Criteria) this;
        }

        public Criteria andSendUserNameLessThan(String value) {
            addCriterion("SEND_USER_NAME <", value, "sendUserName");
            return (Criteria) this;
        }

        public Criteria andSendUserNameLessThanOrEqualTo(String value) {
            addCriterion("SEND_USER_NAME <=", value, "sendUserName");
            return (Criteria) this;
        }

        public Criteria andSendUserNameLike(String value) {
            addCriterion("SEND_USER_NAME like", value, "sendUserName");
            return (Criteria) this;
        }

        public Criteria andSendUserNameNotLike(String value) {
            addCriterion("SEND_USER_NAME not like", value, "sendUserName");
            return (Criteria) this;
        }

        public Criteria andSendUserNameIn(List<String> values) {
            addCriterion("SEND_USER_NAME in", values, "sendUserName");
            return (Criteria) this;
        }

        public Criteria andSendUserNameNotIn(List<String> values) {
            addCriterion("SEND_USER_NAME not in", values, "sendUserName");
            return (Criteria) this;
        }

        public Criteria andSendUserNameBetween(String value1, String value2) {
            addCriterion("SEND_USER_NAME between", value1, value2, "sendUserName");
            return (Criteria) this;
        }

        public Criteria andSendUserNameNotBetween(String value1, String value2) {
            addCriterion("SEND_USER_NAME not between", value1, value2, "sendUserName");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("SEND_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("SEND_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(String value) {
            addCriterion("SEND_TIME =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(String value) {
            addCriterion("SEND_TIME <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(String value) {
            addCriterion("SEND_TIME >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_TIME >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(String value) {
            addCriterion("SEND_TIME <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(String value) {
            addCriterion("SEND_TIME <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLike(String value) {
            addCriterion("SEND_TIME like", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotLike(String value) {
            addCriterion("SEND_TIME not like", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<String> values) {
            addCriterion("SEND_TIME in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<String> values) {
            addCriterion("SEND_TIME not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(String value1, String value2) {
            addCriterion("SEND_TIME between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(String value1, String value2) {
            addCriterion("SEND_TIME not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendWayIdIsNull() {
            addCriterion("SEND_WAY_ID is null");
            return (Criteria) this;
        }

        public Criteria andSendWayIdIsNotNull() {
            addCriterion("SEND_WAY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSendWayIdEqualTo(String value) {
            addCriterion("SEND_WAY_ID =", value, "sendWayId");
            return (Criteria) this;
        }

        public Criteria andSendWayIdNotEqualTo(String value) {
            addCriterion("SEND_WAY_ID <>", value, "sendWayId");
            return (Criteria) this;
        }

        public Criteria andSendWayIdGreaterThan(String value) {
            addCriterion("SEND_WAY_ID >", value, "sendWayId");
            return (Criteria) this;
        }

        public Criteria andSendWayIdGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_WAY_ID >=", value, "sendWayId");
            return (Criteria) this;
        }

        public Criteria andSendWayIdLessThan(String value) {
            addCriterion("SEND_WAY_ID <", value, "sendWayId");
            return (Criteria) this;
        }

        public Criteria andSendWayIdLessThanOrEqualTo(String value) {
            addCriterion("SEND_WAY_ID <=", value, "sendWayId");
            return (Criteria) this;
        }

        public Criteria andSendWayIdLike(String value) {
            addCriterion("SEND_WAY_ID like", value, "sendWayId");
            return (Criteria) this;
        }

        public Criteria andSendWayIdNotLike(String value) {
            addCriterion("SEND_WAY_ID not like", value, "sendWayId");
            return (Criteria) this;
        }

        public Criteria andSendWayIdIn(List<String> values) {
            addCriterion("SEND_WAY_ID in", values, "sendWayId");
            return (Criteria) this;
        }

        public Criteria andSendWayIdNotIn(List<String> values) {
            addCriterion("SEND_WAY_ID not in", values, "sendWayId");
            return (Criteria) this;
        }

        public Criteria andSendWayIdBetween(String value1, String value2) {
            addCriterion("SEND_WAY_ID between", value1, value2, "sendWayId");
            return (Criteria) this;
        }

        public Criteria andSendWayIdNotBetween(String value1, String value2) {
            addCriterion("SEND_WAY_ID not between", value1, value2, "sendWayId");
            return (Criteria) this;
        }

        public Criteria andSendWayNameIsNull() {
            addCriterion("SEND_WAY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSendWayNameIsNotNull() {
            addCriterion("SEND_WAY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSendWayNameEqualTo(String value) {
            addCriterion("SEND_WAY_NAME =", value, "sendWayName");
            return (Criteria) this;
        }

        public Criteria andSendWayNameNotEqualTo(String value) {
            addCriterion("SEND_WAY_NAME <>", value, "sendWayName");
            return (Criteria) this;
        }

        public Criteria andSendWayNameGreaterThan(String value) {
            addCriterion("SEND_WAY_NAME >", value, "sendWayName");
            return (Criteria) this;
        }

        public Criteria andSendWayNameGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_WAY_NAME >=", value, "sendWayName");
            return (Criteria) this;
        }

        public Criteria andSendWayNameLessThan(String value) {
            addCriterion("SEND_WAY_NAME <", value, "sendWayName");
            return (Criteria) this;
        }

        public Criteria andSendWayNameLessThanOrEqualTo(String value) {
            addCriterion("SEND_WAY_NAME <=", value, "sendWayName");
            return (Criteria) this;
        }

        public Criteria andSendWayNameLike(String value) {
            addCriterion("SEND_WAY_NAME like", value, "sendWayName");
            return (Criteria) this;
        }

        public Criteria andSendWayNameNotLike(String value) {
            addCriterion("SEND_WAY_NAME not like", value, "sendWayName");
            return (Criteria) this;
        }

        public Criteria andSendWayNameIn(List<String> values) {
            addCriterion("SEND_WAY_NAME in", values, "sendWayName");
            return (Criteria) this;
        }

        public Criteria andSendWayNameNotIn(List<String> values) {
            addCriterion("SEND_WAY_NAME not in", values, "sendWayName");
            return (Criteria) this;
        }

        public Criteria andSendWayNameBetween(String value1, String value2) {
            addCriterion("SEND_WAY_NAME between", value1, value2, "sendWayName");
            return (Criteria) this;
        }

        public Criteria andSendWayNameNotBetween(String value1, String value2) {
            addCriterion("SEND_WAY_NAME not between", value1, value2, "sendWayName");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIdIsNull() {
            addCriterion("QUERY_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIdIsNotNull() {
            addCriterion("QUERY_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIdEqualTo(String value) {
            addCriterion("QUERY_TYPE_ID =", value, "queryTypeId");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIdNotEqualTo(String value) {
            addCriterion("QUERY_TYPE_ID <>", value, "queryTypeId");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIdGreaterThan(String value) {
            addCriterion("QUERY_TYPE_ID >", value, "queryTypeId");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("QUERY_TYPE_ID >=", value, "queryTypeId");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIdLessThan(String value) {
            addCriterion("QUERY_TYPE_ID <", value, "queryTypeId");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIdLessThanOrEqualTo(String value) {
            addCriterion("QUERY_TYPE_ID <=", value, "queryTypeId");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIdLike(String value) {
            addCriterion("QUERY_TYPE_ID like", value, "queryTypeId");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIdNotLike(String value) {
            addCriterion("QUERY_TYPE_ID not like", value, "queryTypeId");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIdIn(List<String> values) {
            addCriterion("QUERY_TYPE_ID in", values, "queryTypeId");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIdNotIn(List<String> values) {
            addCriterion("QUERY_TYPE_ID not in", values, "queryTypeId");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIdBetween(String value1, String value2) {
            addCriterion("QUERY_TYPE_ID between", value1, value2, "queryTypeId");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIdNotBetween(String value1, String value2) {
            addCriterion("QUERY_TYPE_ID not between", value1, value2, "queryTypeId");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNameIsNull() {
            addCriterion("QUERY_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNameIsNotNull() {
            addCriterion("QUERY_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNameEqualTo(String value) {
            addCriterion("QUERY_TYPE_NAME =", value, "queryTypeName");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNameNotEqualTo(String value) {
            addCriterion("QUERY_TYPE_NAME <>", value, "queryTypeName");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNameGreaterThan(String value) {
            addCriterion("QUERY_TYPE_NAME >", value, "queryTypeName");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("QUERY_TYPE_NAME >=", value, "queryTypeName");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNameLessThan(String value) {
            addCriterion("QUERY_TYPE_NAME <", value, "queryTypeName");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNameLessThanOrEqualTo(String value) {
            addCriterion("QUERY_TYPE_NAME <=", value, "queryTypeName");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNameLike(String value) {
            addCriterion("QUERY_TYPE_NAME like", value, "queryTypeName");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNameNotLike(String value) {
            addCriterion("QUERY_TYPE_NAME not like", value, "queryTypeName");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNameIn(List<String> values) {
            addCriterion("QUERY_TYPE_NAME in", values, "queryTypeName");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNameNotIn(List<String> values) {
            addCriterion("QUERY_TYPE_NAME not in", values, "queryTypeName");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNameBetween(String value1, String value2) {
            addCriterion("QUERY_TYPE_NAME between", value1, value2, "queryTypeName");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNameNotBetween(String value1, String value2) {
            addCriterion("QUERY_TYPE_NAME not between", value1, value2, "queryTypeName");
            return (Criteria) this;
        }

        public Criteria andQueryContentIsNull() {
            addCriterion("QUERY_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andQueryContentIsNotNull() {
            addCriterion("QUERY_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andQueryContentEqualTo(String value) {
            addCriterion("QUERY_CONTENT =", value, "queryContent");
            return (Criteria) this;
        }

        public Criteria andQueryContentNotEqualTo(String value) {
            addCriterion("QUERY_CONTENT <>", value, "queryContent");
            return (Criteria) this;
        }

        public Criteria andQueryContentGreaterThan(String value) {
            addCriterion("QUERY_CONTENT >", value, "queryContent");
            return (Criteria) this;
        }

        public Criteria andQueryContentGreaterThanOrEqualTo(String value) {
            addCriterion("QUERY_CONTENT >=", value, "queryContent");
            return (Criteria) this;
        }

        public Criteria andQueryContentLessThan(String value) {
            addCriterion("QUERY_CONTENT <", value, "queryContent");
            return (Criteria) this;
        }

        public Criteria andQueryContentLessThanOrEqualTo(String value) {
            addCriterion("QUERY_CONTENT <=", value, "queryContent");
            return (Criteria) this;
        }

        public Criteria andQueryContentLike(String value) {
            addCriterion("QUERY_CONTENT like", value, "queryContent");
            return (Criteria) this;
        }

        public Criteria andQueryContentNotLike(String value) {
            addCriterion("QUERY_CONTENT not like", value, "queryContent");
            return (Criteria) this;
        }

        public Criteria andQueryContentIn(List<String> values) {
            addCriterion("QUERY_CONTENT in", values, "queryContent");
            return (Criteria) this;
        }

        public Criteria andQueryContentNotIn(List<String> values) {
            addCriterion("QUERY_CONTENT not in", values, "queryContent");
            return (Criteria) this;
        }

        public Criteria andQueryContentBetween(String value1, String value2) {
            addCriterion("QUERY_CONTENT between", value1, value2, "queryContent");
            return (Criteria) this;
        }

        public Criteria andQueryContentNotBetween(String value1, String value2) {
            addCriterion("QUERY_CONTENT not between", value1, value2, "queryContent");
            return (Criteria) this;
        }

        public Criteria andSolveStatusIdIsNull() {
            addCriterion("SOLVE_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andSolveStatusIdIsNotNull() {
            addCriterion("SOLVE_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSolveStatusIdEqualTo(String value) {
            addCriterion("SOLVE_STATUS_ID =", value, "solveStatusId");
            return (Criteria) this;
        }

        public Criteria andSolveStatusIdNotEqualTo(String value) {
            addCriterion("SOLVE_STATUS_ID <>", value, "solveStatusId");
            return (Criteria) this;
        }

        public Criteria andSolveStatusIdGreaterThan(String value) {
            addCriterion("SOLVE_STATUS_ID >", value, "solveStatusId");
            return (Criteria) this;
        }

        public Criteria andSolveStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("SOLVE_STATUS_ID >=", value, "solveStatusId");
            return (Criteria) this;
        }

        public Criteria andSolveStatusIdLessThan(String value) {
            addCriterion("SOLVE_STATUS_ID <", value, "solveStatusId");
            return (Criteria) this;
        }

        public Criteria andSolveStatusIdLessThanOrEqualTo(String value) {
            addCriterion("SOLVE_STATUS_ID <=", value, "solveStatusId");
            return (Criteria) this;
        }

        public Criteria andSolveStatusIdLike(String value) {
            addCriterion("SOLVE_STATUS_ID like", value, "solveStatusId");
            return (Criteria) this;
        }

        public Criteria andSolveStatusIdNotLike(String value) {
            addCriterion("SOLVE_STATUS_ID not like", value, "solveStatusId");
            return (Criteria) this;
        }

        public Criteria andSolveStatusIdIn(List<String> values) {
            addCriterion("SOLVE_STATUS_ID in", values, "solveStatusId");
            return (Criteria) this;
        }

        public Criteria andSolveStatusIdNotIn(List<String> values) {
            addCriterion("SOLVE_STATUS_ID not in", values, "solveStatusId");
            return (Criteria) this;
        }

        public Criteria andSolveStatusIdBetween(String value1, String value2) {
            addCriterion("SOLVE_STATUS_ID between", value1, value2, "solveStatusId");
            return (Criteria) this;
        }

        public Criteria andSolveStatusIdNotBetween(String value1, String value2) {
            addCriterion("SOLVE_STATUS_ID not between", value1, value2, "solveStatusId");
            return (Criteria) this;
        }

        public Criteria andSolveStatusNameIsNull() {
            addCriterion("SOLVE_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSolveStatusNameIsNotNull() {
            addCriterion("SOLVE_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSolveStatusNameEqualTo(String value) {
            addCriterion("SOLVE_STATUS_NAME =", value, "solveStatusName");
            return (Criteria) this;
        }

        public Criteria andSolveStatusNameNotEqualTo(String value) {
            addCriterion("SOLVE_STATUS_NAME <>", value, "solveStatusName");
            return (Criteria) this;
        }

        public Criteria andSolveStatusNameGreaterThan(String value) {
            addCriterion("SOLVE_STATUS_NAME >", value, "solveStatusName");
            return (Criteria) this;
        }

        public Criteria andSolveStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("SOLVE_STATUS_NAME >=", value, "solveStatusName");
            return (Criteria) this;
        }

        public Criteria andSolveStatusNameLessThan(String value) {
            addCriterion("SOLVE_STATUS_NAME <", value, "solveStatusName");
            return (Criteria) this;
        }

        public Criteria andSolveStatusNameLessThanOrEqualTo(String value) {
            addCriterion("SOLVE_STATUS_NAME <=", value, "solveStatusName");
            return (Criteria) this;
        }

        public Criteria andSolveStatusNameLike(String value) {
            addCriterion("SOLVE_STATUS_NAME like", value, "solveStatusName");
            return (Criteria) this;
        }

        public Criteria andSolveStatusNameNotLike(String value) {
            addCriterion("SOLVE_STATUS_NAME not like", value, "solveStatusName");
            return (Criteria) this;
        }

        public Criteria andSolveStatusNameIn(List<String> values) {
            addCriterion("SOLVE_STATUS_NAME in", values, "solveStatusName");
            return (Criteria) this;
        }

        public Criteria andSolveStatusNameNotIn(List<String> values) {
            addCriterion("SOLVE_STATUS_NAME not in", values, "solveStatusName");
            return (Criteria) this;
        }

        public Criteria andSolveStatusNameBetween(String value1, String value2) {
            addCriterion("SOLVE_STATUS_NAME between", value1, value2, "solveStatusName");
            return (Criteria) this;
        }

        public Criteria andSolveStatusNameNotBetween(String value1, String value2) {
            addCriterion("SOLVE_STATUS_NAME not between", value1, value2, "solveStatusName");
            return (Criteria) this;
        }

        public Criteria andSolveUserFlowIsNull() {
            addCriterion("SOLVE_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSolveUserFlowIsNotNull() {
            addCriterion("SOLVE_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSolveUserFlowEqualTo(String value) {
            addCriterion("SOLVE_USER_FLOW =", value, "solveUserFlow");
            return (Criteria) this;
        }

        public Criteria andSolveUserFlowNotEqualTo(String value) {
            addCriterion("SOLVE_USER_FLOW <>", value, "solveUserFlow");
            return (Criteria) this;
        }

        public Criteria andSolveUserFlowGreaterThan(String value) {
            addCriterion("SOLVE_USER_FLOW >", value, "solveUserFlow");
            return (Criteria) this;
        }

        public Criteria andSolveUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SOLVE_USER_FLOW >=", value, "solveUserFlow");
            return (Criteria) this;
        }

        public Criteria andSolveUserFlowLessThan(String value) {
            addCriterion("SOLVE_USER_FLOW <", value, "solveUserFlow");
            return (Criteria) this;
        }

        public Criteria andSolveUserFlowLessThanOrEqualTo(String value) {
            addCriterion("SOLVE_USER_FLOW <=", value, "solveUserFlow");
            return (Criteria) this;
        }

        public Criteria andSolveUserFlowLike(String value) {
            addCriterion("SOLVE_USER_FLOW like", value, "solveUserFlow");
            return (Criteria) this;
        }

        public Criteria andSolveUserFlowNotLike(String value) {
            addCriterion("SOLVE_USER_FLOW not like", value, "solveUserFlow");
            return (Criteria) this;
        }

        public Criteria andSolveUserFlowIn(List<String> values) {
            addCriterion("SOLVE_USER_FLOW in", values, "solveUserFlow");
            return (Criteria) this;
        }

        public Criteria andSolveUserFlowNotIn(List<String> values) {
            addCriterion("SOLVE_USER_FLOW not in", values, "solveUserFlow");
            return (Criteria) this;
        }

        public Criteria andSolveUserFlowBetween(String value1, String value2) {
            addCriterion("SOLVE_USER_FLOW between", value1, value2, "solveUserFlow");
            return (Criteria) this;
        }

        public Criteria andSolveUserFlowNotBetween(String value1, String value2) {
            addCriterion("SOLVE_USER_FLOW not between", value1, value2, "solveUserFlow");
            return (Criteria) this;
        }

        public Criteria andSolveUserNameIsNull() {
            addCriterion("SOLVE_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSolveUserNameIsNotNull() {
            addCriterion("SOLVE_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSolveUserNameEqualTo(String value) {
            addCriterion("SOLVE_USER_NAME =", value, "solveUserName");
            return (Criteria) this;
        }

        public Criteria andSolveUserNameNotEqualTo(String value) {
            addCriterion("SOLVE_USER_NAME <>", value, "solveUserName");
            return (Criteria) this;
        }

        public Criteria andSolveUserNameGreaterThan(String value) {
            addCriterion("SOLVE_USER_NAME >", value, "solveUserName");
            return (Criteria) this;
        }

        public Criteria andSolveUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("SOLVE_USER_NAME >=", value, "solveUserName");
            return (Criteria) this;
        }

        public Criteria andSolveUserNameLessThan(String value) {
            addCriterion("SOLVE_USER_NAME <", value, "solveUserName");
            return (Criteria) this;
        }

        public Criteria andSolveUserNameLessThanOrEqualTo(String value) {
            addCriterion("SOLVE_USER_NAME <=", value, "solveUserName");
            return (Criteria) this;
        }

        public Criteria andSolveUserNameLike(String value) {
            addCriterion("SOLVE_USER_NAME like", value, "solveUserName");
            return (Criteria) this;
        }

        public Criteria andSolveUserNameNotLike(String value) {
            addCriterion("SOLVE_USER_NAME not like", value, "solveUserName");
            return (Criteria) this;
        }

        public Criteria andSolveUserNameIn(List<String> values) {
            addCriterion("SOLVE_USER_NAME in", values, "solveUserName");
            return (Criteria) this;
        }

        public Criteria andSolveUserNameNotIn(List<String> values) {
            addCriterion("SOLVE_USER_NAME not in", values, "solveUserName");
            return (Criteria) this;
        }

        public Criteria andSolveUserNameBetween(String value1, String value2) {
            addCriterion("SOLVE_USER_NAME between", value1, value2, "solveUserName");
            return (Criteria) this;
        }

        public Criteria andSolveUserNameNotBetween(String value1, String value2) {
            addCriterion("SOLVE_USER_NAME not between", value1, value2, "solveUserName");
            return (Criteria) this;
        }

        public Criteria andSolveTimeIsNull() {
            addCriterion("SOLVE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSolveTimeIsNotNull() {
            addCriterion("SOLVE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSolveTimeEqualTo(String value) {
            addCriterion("SOLVE_TIME =", value, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeNotEqualTo(String value) {
            addCriterion("SOLVE_TIME <>", value, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeGreaterThan(String value) {
            addCriterion("SOLVE_TIME >", value, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SOLVE_TIME >=", value, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeLessThan(String value) {
            addCriterion("SOLVE_TIME <", value, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeLessThanOrEqualTo(String value) {
            addCriterion("SOLVE_TIME <=", value, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeLike(String value) {
            addCriterion("SOLVE_TIME like", value, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeNotLike(String value) {
            addCriterion("SOLVE_TIME not like", value, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeIn(List<String> values) {
            addCriterion("SOLVE_TIME in", values, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeNotIn(List<String> values) {
            addCriterion("SOLVE_TIME not in", values, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeBetween(String value1, String value2) {
            addCriterion("SOLVE_TIME between", value1, value2, "solveTime");
            return (Criteria) this;
        }

        public Criteria andSolveTimeNotBetween(String value1, String value2) {
            addCriterion("SOLVE_TIME not between", value1, value2, "solveTime");
            return (Criteria) this;
        }

        public Criteria andDcfFlowIsNull() {
            addCriterion("DCF_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDcfFlowIsNotNull() {
            addCriterion("DCF_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDcfFlowEqualTo(String value) {
            addCriterion("DCF_FLOW =", value, "dcfFlow");
            return (Criteria) this;
        }

        public Criteria andDcfFlowNotEqualTo(String value) {
            addCriterion("DCF_FLOW <>", value, "dcfFlow");
            return (Criteria) this;
        }

        public Criteria andDcfFlowGreaterThan(String value) {
            addCriterion("DCF_FLOW >", value, "dcfFlow");
            return (Criteria) this;
        }

        public Criteria andDcfFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DCF_FLOW >=", value, "dcfFlow");
            return (Criteria) this;
        }

        public Criteria andDcfFlowLessThan(String value) {
            addCriterion("DCF_FLOW <", value, "dcfFlow");
            return (Criteria) this;
        }

        public Criteria andDcfFlowLessThanOrEqualTo(String value) {
            addCriterion("DCF_FLOW <=", value, "dcfFlow");
            return (Criteria) this;
        }

        public Criteria andDcfFlowLike(String value) {
            addCriterion("DCF_FLOW like", value, "dcfFlow");
            return (Criteria) this;
        }

        public Criteria andDcfFlowNotLike(String value) {
            addCriterion("DCF_FLOW not like", value, "dcfFlow");
            return (Criteria) this;
        }

        public Criteria andDcfFlowIn(List<String> values) {
            addCriterion("DCF_FLOW in", values, "dcfFlow");
            return (Criteria) this;
        }

        public Criteria andDcfFlowNotIn(List<String> values) {
            addCriterion("DCF_FLOW not in", values, "dcfFlow");
            return (Criteria) this;
        }

        public Criteria andDcfFlowBetween(String value1, String value2) {
            addCriterion("DCF_FLOW between", value1, value2, "dcfFlow");
            return (Criteria) this;
        }

        public Criteria andDcfFlowNotBetween(String value1, String value2) {
            addCriterion("DCF_FLOW not between", value1, value2, "dcfFlow");
            return (Criteria) this;
        }

        public Criteria andDcfNoIsNull() {
            addCriterion("DCF_NO is null");
            return (Criteria) this;
        }

        public Criteria andDcfNoIsNotNull() {
            addCriterion("DCF_NO is not null");
            return (Criteria) this;
        }

        public Criteria andDcfNoEqualTo(String value) {
            addCriterion("DCF_NO =", value, "dcfNo");
            return (Criteria) this;
        }

        public Criteria andDcfNoNotEqualTo(String value) {
            addCriterion("DCF_NO <>", value, "dcfNo");
            return (Criteria) this;
        }

        public Criteria andDcfNoGreaterThan(String value) {
            addCriterion("DCF_NO >", value, "dcfNo");
            return (Criteria) this;
        }

        public Criteria andDcfNoGreaterThanOrEqualTo(String value) {
            addCriterion("DCF_NO >=", value, "dcfNo");
            return (Criteria) this;
        }

        public Criteria andDcfNoLessThan(String value) {
            addCriterion("DCF_NO <", value, "dcfNo");
            return (Criteria) this;
        }

        public Criteria andDcfNoLessThanOrEqualTo(String value) {
            addCriterion("DCF_NO <=", value, "dcfNo");
            return (Criteria) this;
        }

        public Criteria andDcfNoLike(String value) {
            addCriterion("DCF_NO like", value, "dcfNo");
            return (Criteria) this;
        }

        public Criteria andDcfNoNotLike(String value) {
            addCriterion("DCF_NO not like", value, "dcfNo");
            return (Criteria) this;
        }

        public Criteria andDcfNoIn(List<String> values) {
            addCriterion("DCF_NO in", values, "dcfNo");
            return (Criteria) this;
        }

        public Criteria andDcfNoNotIn(List<String> values) {
            addCriterion("DCF_NO not in", values, "dcfNo");
            return (Criteria) this;
        }

        public Criteria andDcfNoBetween(String value1, String value2) {
            addCriterion("DCF_NO between", value1, value2, "dcfNo");
            return (Criteria) this;
        }

        public Criteria andDcfNoNotBetween(String value1, String value2) {
            addCriterion("DCF_NO not between", value1, value2, "dcfNo");
            return (Criteria) this;
        }

        public Criteria andCraStatusIsNull() {
            addCriterion("CRA_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andCraStatusIsNotNull() {
            addCriterion("CRA_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andCraStatusEqualTo(String value) {
            addCriterion("CRA_STATUS =", value, "craStatus");
            return (Criteria) this;
        }

        public Criteria andCraStatusNotEqualTo(String value) {
            addCriterion("CRA_STATUS <>", value, "craStatus");
            return (Criteria) this;
        }

        public Criteria andCraStatusGreaterThan(String value) {
            addCriterion("CRA_STATUS >", value, "craStatus");
            return (Criteria) this;
        }

        public Criteria andCraStatusGreaterThanOrEqualTo(String value) {
            addCriterion("CRA_STATUS >=", value, "craStatus");
            return (Criteria) this;
        }

        public Criteria andCraStatusLessThan(String value) {
            addCriterion("CRA_STATUS <", value, "craStatus");
            return (Criteria) this;
        }

        public Criteria andCraStatusLessThanOrEqualTo(String value) {
            addCriterion("CRA_STATUS <=", value, "craStatus");
            return (Criteria) this;
        }

        public Criteria andCraStatusLike(String value) {
            addCriterion("CRA_STATUS like", value, "craStatus");
            return (Criteria) this;
        }

        public Criteria andCraStatusNotLike(String value) {
            addCriterion("CRA_STATUS not like", value, "craStatus");
            return (Criteria) this;
        }

        public Criteria andCraStatusIn(List<String> values) {
            addCriterion("CRA_STATUS in", values, "craStatus");
            return (Criteria) this;
        }

        public Criteria andCraStatusNotIn(List<String> values) {
            addCriterion("CRA_STATUS not in", values, "craStatus");
            return (Criteria) this;
        }

        public Criteria andCraStatusBetween(String value1, String value2) {
            addCriterion("CRA_STATUS between", value1, value2, "craStatus");
            return (Criteria) this;
        }

        public Criteria andCraStatusNotBetween(String value1, String value2) {
            addCriterion("CRA_STATUS not between", value1, value2, "craStatus");
            return (Criteria) this;
        }

        public Criteria andDmStatusIsNull() {
            addCriterion("DM_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andDmStatusIsNotNull() {
            addCriterion("DM_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andDmStatusEqualTo(String value) {
            addCriterion("DM_STATUS =", value, "dmStatus");
            return (Criteria) this;
        }

        public Criteria andDmStatusNotEqualTo(String value) {
            addCriterion("DM_STATUS <>", value, "dmStatus");
            return (Criteria) this;
        }

        public Criteria andDmStatusGreaterThan(String value) {
            addCriterion("DM_STATUS >", value, "dmStatus");
            return (Criteria) this;
        }

        public Criteria andDmStatusGreaterThanOrEqualTo(String value) {
            addCriterion("DM_STATUS >=", value, "dmStatus");
            return (Criteria) this;
        }

        public Criteria andDmStatusLessThan(String value) {
            addCriterion("DM_STATUS <", value, "dmStatus");
            return (Criteria) this;
        }

        public Criteria andDmStatusLessThanOrEqualTo(String value) {
            addCriterion("DM_STATUS <=", value, "dmStatus");
            return (Criteria) this;
        }

        public Criteria andDmStatusLike(String value) {
            addCriterion("DM_STATUS like", value, "dmStatus");
            return (Criteria) this;
        }

        public Criteria andDmStatusNotLike(String value) {
            addCriterion("DM_STATUS not like", value, "dmStatus");
            return (Criteria) this;
        }

        public Criteria andDmStatusIn(List<String> values) {
            addCriterion("DM_STATUS in", values, "dmStatus");
            return (Criteria) this;
        }

        public Criteria andDmStatusNotIn(List<String> values) {
            addCriterion("DM_STATUS not in", values, "dmStatus");
            return (Criteria) this;
        }

        public Criteria andDmStatusBetween(String value1, String value2) {
            addCriterion("DM_STATUS between", value1, value2, "dmStatus");
            return (Criteria) this;
        }

        public Criteria andDmStatusNotBetween(String value1, String value2) {
            addCriterion("DM_STATUS not between", value1, value2, "dmStatus");
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