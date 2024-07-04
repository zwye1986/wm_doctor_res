package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EdcAppLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdcAppLogExample() {
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

        public Criteria andLogFlowIsNull() {
            addCriterion("LOG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andLogFlowIsNotNull() {
            addCriterion("LOG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andLogFlowEqualTo(String value) {
            addCriterion("LOG_FLOW =", value, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowNotEqualTo(String value) {
            addCriterion("LOG_FLOW <>", value, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowGreaterThan(String value) {
            addCriterion("LOG_FLOW >", value, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowGreaterThanOrEqualTo(String value) {
            addCriterion("LOG_FLOW >=", value, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowLessThan(String value) {
            addCriterion("LOG_FLOW <", value, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowLessThanOrEqualTo(String value) {
            addCriterion("LOG_FLOW <=", value, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowLike(String value) {
            addCriterion("LOG_FLOW like", value, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowNotLike(String value) {
            addCriterion("LOG_FLOW not like", value, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowIn(List<String> values) {
            addCriterion("LOG_FLOW in", values, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowNotIn(List<String> values) {
            addCriterion("LOG_FLOW not in", values, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowBetween(String value1, String value2) {
            addCriterion("LOG_FLOW between", value1, value2, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowNotBetween(String value1, String value2) {
            addCriterion("LOG_FLOW not between", value1, value2, "logFlow");
            return (Criteria) this;
        }

        public Criteria andReqCodeIsNull() {
            addCriterion("REQ_CODE is null");
            return (Criteria) this;
        }

        public Criteria andReqCodeIsNotNull() {
            addCriterion("REQ_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andReqCodeEqualTo(String value) {
            addCriterion("REQ_CODE =", value, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeNotEqualTo(String value) {
            addCriterion("REQ_CODE <>", value, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeGreaterThan(String value) {
            addCriterion("REQ_CODE >", value, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeGreaterThanOrEqualTo(String value) {
            addCriterion("REQ_CODE >=", value, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeLessThan(String value) {
            addCriterion("REQ_CODE <", value, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeLessThanOrEqualTo(String value) {
            addCriterion("REQ_CODE <=", value, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeLike(String value) {
            addCriterion("REQ_CODE like", value, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeNotLike(String value) {
            addCriterion("REQ_CODE not like", value, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeIn(List<String> values) {
            addCriterion("REQ_CODE in", values, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeNotIn(List<String> values) {
            addCriterion("REQ_CODE not in", values, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeBetween(String value1, String value2) {
            addCriterion("REQ_CODE between", value1, value2, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeNotBetween(String value1, String value2) {
            addCriterion("REQ_CODE not between", value1, value2, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeNameIsNull() {
            addCriterion("REQ_CODE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andReqCodeNameIsNotNull() {
            addCriterion("REQ_CODE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andReqCodeNameEqualTo(String value) {
            addCriterion("REQ_CODE_NAME =", value, "reqCodeName");
            return (Criteria) this;
        }

        public Criteria andReqCodeNameNotEqualTo(String value) {
            addCriterion("REQ_CODE_NAME <>", value, "reqCodeName");
            return (Criteria) this;
        }

        public Criteria andReqCodeNameGreaterThan(String value) {
            addCriterion("REQ_CODE_NAME >", value, "reqCodeName");
            return (Criteria) this;
        }

        public Criteria andReqCodeNameGreaterThanOrEqualTo(String value) {
            addCriterion("REQ_CODE_NAME >=", value, "reqCodeName");
            return (Criteria) this;
        }

        public Criteria andReqCodeNameLessThan(String value) {
            addCriterion("REQ_CODE_NAME <", value, "reqCodeName");
            return (Criteria) this;
        }

        public Criteria andReqCodeNameLessThanOrEqualTo(String value) {
            addCriterion("REQ_CODE_NAME <=", value, "reqCodeName");
            return (Criteria) this;
        }

        public Criteria andReqCodeNameLike(String value) {
            addCriterion("REQ_CODE_NAME like", value, "reqCodeName");
            return (Criteria) this;
        }

        public Criteria andReqCodeNameNotLike(String value) {
            addCriterion("REQ_CODE_NAME not like", value, "reqCodeName");
            return (Criteria) this;
        }

        public Criteria andReqCodeNameIn(List<String> values) {
            addCriterion("REQ_CODE_NAME in", values, "reqCodeName");
            return (Criteria) this;
        }

        public Criteria andReqCodeNameNotIn(List<String> values) {
            addCriterion("REQ_CODE_NAME not in", values, "reqCodeName");
            return (Criteria) this;
        }

        public Criteria andReqCodeNameBetween(String value1, String value2) {
            addCriterion("REQ_CODE_NAME between", value1, value2, "reqCodeName");
            return (Criteria) this;
        }

        public Criteria andReqCodeNameNotBetween(String value1, String value2) {
            addCriterion("REQ_CODE_NAME not between", value1, value2, "reqCodeName");
            return (Criteria) this;
        }

        public Criteria andReqParamIsNull() {
            addCriterion("REQ_PARAM is null");
            return (Criteria) this;
        }

        public Criteria andReqParamIsNotNull() {
            addCriterion("REQ_PARAM is not null");
            return (Criteria) this;
        }

        public Criteria andReqParamEqualTo(String value) {
            addCriterion("REQ_PARAM =", value, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamNotEqualTo(String value) {
            addCriterion("REQ_PARAM <>", value, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamGreaterThan(String value) {
            addCriterion("REQ_PARAM >", value, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamGreaterThanOrEqualTo(String value) {
            addCriterion("REQ_PARAM >=", value, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamLessThan(String value) {
            addCriterion("REQ_PARAM <", value, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamLessThanOrEqualTo(String value) {
            addCriterion("REQ_PARAM <=", value, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamLike(String value) {
            addCriterion("REQ_PARAM like", value, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamNotLike(String value) {
            addCriterion("REQ_PARAM not like", value, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamIn(List<String> values) {
            addCriterion("REQ_PARAM in", values, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamNotIn(List<String> values) {
            addCriterion("REQ_PARAM not in", values, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamBetween(String value1, String value2) {
            addCriterion("REQ_PARAM between", value1, value2, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqParamNotBetween(String value1, String value2) {
            addCriterion("REQ_PARAM not between", value1, value2, "reqParam");
            return (Criteria) this;
        }

        public Criteria andReqUserFlowIsNull() {
            addCriterion("REQ_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andReqUserFlowIsNotNull() {
            addCriterion("REQ_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andReqUserFlowEqualTo(String value) {
            addCriterion("REQ_USER_FLOW =", value, "reqUserFlow");
            return (Criteria) this;
        }

        public Criteria andReqUserFlowNotEqualTo(String value) {
            addCriterion("REQ_USER_FLOW <>", value, "reqUserFlow");
            return (Criteria) this;
        }

        public Criteria andReqUserFlowGreaterThan(String value) {
            addCriterion("REQ_USER_FLOW >", value, "reqUserFlow");
            return (Criteria) this;
        }

        public Criteria andReqUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("REQ_USER_FLOW >=", value, "reqUserFlow");
            return (Criteria) this;
        }

        public Criteria andReqUserFlowLessThan(String value) {
            addCriterion("REQ_USER_FLOW <", value, "reqUserFlow");
            return (Criteria) this;
        }

        public Criteria andReqUserFlowLessThanOrEqualTo(String value) {
            addCriterion("REQ_USER_FLOW <=", value, "reqUserFlow");
            return (Criteria) this;
        }

        public Criteria andReqUserFlowLike(String value) {
            addCriterion("REQ_USER_FLOW like", value, "reqUserFlow");
            return (Criteria) this;
        }

        public Criteria andReqUserFlowNotLike(String value) {
            addCriterion("REQ_USER_FLOW not like", value, "reqUserFlow");
            return (Criteria) this;
        }

        public Criteria andReqUserFlowIn(List<String> values) {
            addCriterion("REQ_USER_FLOW in", values, "reqUserFlow");
            return (Criteria) this;
        }

        public Criteria andReqUserFlowNotIn(List<String> values) {
            addCriterion("REQ_USER_FLOW not in", values, "reqUserFlow");
            return (Criteria) this;
        }

        public Criteria andReqUserFlowBetween(String value1, String value2) {
            addCriterion("REQ_USER_FLOW between", value1, value2, "reqUserFlow");
            return (Criteria) this;
        }

        public Criteria andReqUserFlowNotBetween(String value1, String value2) {
            addCriterion("REQ_USER_FLOW not between", value1, value2, "reqUserFlow");
            return (Criteria) this;
        }

        public Criteria andReqUserNameIsNull() {
            addCriterion("REQ_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andReqUserNameIsNotNull() {
            addCriterion("REQ_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andReqUserNameEqualTo(String value) {
            addCriterion("REQ_USER_NAME =", value, "reqUserName");
            return (Criteria) this;
        }

        public Criteria andReqUserNameNotEqualTo(String value) {
            addCriterion("REQ_USER_NAME <>", value, "reqUserName");
            return (Criteria) this;
        }

        public Criteria andReqUserNameGreaterThan(String value) {
            addCriterion("REQ_USER_NAME >", value, "reqUserName");
            return (Criteria) this;
        }

        public Criteria andReqUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("REQ_USER_NAME >=", value, "reqUserName");
            return (Criteria) this;
        }

        public Criteria andReqUserNameLessThan(String value) {
            addCriterion("REQ_USER_NAME <", value, "reqUserName");
            return (Criteria) this;
        }

        public Criteria andReqUserNameLessThanOrEqualTo(String value) {
            addCriterion("REQ_USER_NAME <=", value, "reqUserName");
            return (Criteria) this;
        }

        public Criteria andReqUserNameLike(String value) {
            addCriterion("REQ_USER_NAME like", value, "reqUserName");
            return (Criteria) this;
        }

        public Criteria andReqUserNameNotLike(String value) {
            addCriterion("REQ_USER_NAME not like", value, "reqUserName");
            return (Criteria) this;
        }

        public Criteria andReqUserNameIn(List<String> values) {
            addCriterion("REQ_USER_NAME in", values, "reqUserName");
            return (Criteria) this;
        }

        public Criteria andReqUserNameNotIn(List<String> values) {
            addCriterion("REQ_USER_NAME not in", values, "reqUserName");
            return (Criteria) this;
        }

        public Criteria andReqUserNameBetween(String value1, String value2) {
            addCriterion("REQ_USER_NAME between", value1, value2, "reqUserName");
            return (Criteria) this;
        }

        public Criteria andReqUserNameNotBetween(String value1, String value2) {
            addCriterion("REQ_USER_NAME not between", value1, value2, "reqUserName");
            return (Criteria) this;
        }

        public Criteria andReqProjFlowIsNull() {
            addCriterion("REQ_PROJ_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andReqProjFlowIsNotNull() {
            addCriterion("REQ_PROJ_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andReqProjFlowEqualTo(String value) {
            addCriterion("REQ_PROJ_FLOW =", value, "reqProjFlow");
            return (Criteria) this;
        }

        public Criteria andReqProjFlowNotEqualTo(String value) {
            addCriterion("REQ_PROJ_FLOW <>", value, "reqProjFlow");
            return (Criteria) this;
        }

        public Criteria andReqProjFlowGreaterThan(String value) {
            addCriterion("REQ_PROJ_FLOW >", value, "reqProjFlow");
            return (Criteria) this;
        }

        public Criteria andReqProjFlowGreaterThanOrEqualTo(String value) {
            addCriterion("REQ_PROJ_FLOW >=", value, "reqProjFlow");
            return (Criteria) this;
        }

        public Criteria andReqProjFlowLessThan(String value) {
            addCriterion("REQ_PROJ_FLOW <", value, "reqProjFlow");
            return (Criteria) this;
        }

        public Criteria andReqProjFlowLessThanOrEqualTo(String value) {
            addCriterion("REQ_PROJ_FLOW <=", value, "reqProjFlow");
            return (Criteria) this;
        }

        public Criteria andReqProjFlowLike(String value) {
            addCriterion("REQ_PROJ_FLOW like", value, "reqProjFlow");
            return (Criteria) this;
        }

        public Criteria andReqProjFlowNotLike(String value) {
            addCriterion("REQ_PROJ_FLOW not like", value, "reqProjFlow");
            return (Criteria) this;
        }

        public Criteria andReqProjFlowIn(List<String> values) {
            addCriterion("REQ_PROJ_FLOW in", values, "reqProjFlow");
            return (Criteria) this;
        }

        public Criteria andReqProjFlowNotIn(List<String> values) {
            addCriterion("REQ_PROJ_FLOW not in", values, "reqProjFlow");
            return (Criteria) this;
        }

        public Criteria andReqProjFlowBetween(String value1, String value2) {
            addCriterion("REQ_PROJ_FLOW between", value1, value2, "reqProjFlow");
            return (Criteria) this;
        }

        public Criteria andReqProjFlowNotBetween(String value1, String value2) {
            addCriterion("REQ_PROJ_FLOW not between", value1, value2, "reqProjFlow");
            return (Criteria) this;
        }

        public Criteria andReqProjNameIsNull() {
            addCriterion("REQ_PROJ_NAME is null");
            return (Criteria) this;
        }

        public Criteria andReqProjNameIsNotNull() {
            addCriterion("REQ_PROJ_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andReqProjNameEqualTo(String value) {
            addCriterion("REQ_PROJ_NAME =", value, "reqProjName");
            return (Criteria) this;
        }

        public Criteria andReqProjNameNotEqualTo(String value) {
            addCriterion("REQ_PROJ_NAME <>", value, "reqProjName");
            return (Criteria) this;
        }

        public Criteria andReqProjNameGreaterThan(String value) {
            addCriterion("REQ_PROJ_NAME >", value, "reqProjName");
            return (Criteria) this;
        }

        public Criteria andReqProjNameGreaterThanOrEqualTo(String value) {
            addCriterion("REQ_PROJ_NAME >=", value, "reqProjName");
            return (Criteria) this;
        }

        public Criteria andReqProjNameLessThan(String value) {
            addCriterion("REQ_PROJ_NAME <", value, "reqProjName");
            return (Criteria) this;
        }

        public Criteria andReqProjNameLessThanOrEqualTo(String value) {
            addCriterion("REQ_PROJ_NAME <=", value, "reqProjName");
            return (Criteria) this;
        }

        public Criteria andReqProjNameLike(String value) {
            addCriterion("REQ_PROJ_NAME like", value, "reqProjName");
            return (Criteria) this;
        }

        public Criteria andReqProjNameNotLike(String value) {
            addCriterion("REQ_PROJ_NAME not like", value, "reqProjName");
            return (Criteria) this;
        }

        public Criteria andReqProjNameIn(List<String> values) {
            addCriterion("REQ_PROJ_NAME in", values, "reqProjName");
            return (Criteria) this;
        }

        public Criteria andReqProjNameNotIn(List<String> values) {
            addCriterion("REQ_PROJ_NAME not in", values, "reqProjName");
            return (Criteria) this;
        }

        public Criteria andReqProjNameBetween(String value1, String value2) {
            addCriterion("REQ_PROJ_NAME between", value1, value2, "reqProjName");
            return (Criteria) this;
        }

        public Criteria andReqProjNameNotBetween(String value1, String value2) {
            addCriterion("REQ_PROJ_NAME not between", value1, value2, "reqProjName");
            return (Criteria) this;
        }

        public Criteria andResultIdIsNull() {
            addCriterion("RESULT_ID is null");
            return (Criteria) this;
        }

        public Criteria andResultIdIsNotNull() {
            addCriterion("RESULT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andResultIdEqualTo(String value) {
            addCriterion("RESULT_ID =", value, "resultId");
            return (Criteria) this;
        }

        public Criteria andResultIdNotEqualTo(String value) {
            addCriterion("RESULT_ID <>", value, "resultId");
            return (Criteria) this;
        }

        public Criteria andResultIdGreaterThan(String value) {
            addCriterion("RESULT_ID >", value, "resultId");
            return (Criteria) this;
        }

        public Criteria andResultIdGreaterThanOrEqualTo(String value) {
            addCriterion("RESULT_ID >=", value, "resultId");
            return (Criteria) this;
        }

        public Criteria andResultIdLessThan(String value) {
            addCriterion("RESULT_ID <", value, "resultId");
            return (Criteria) this;
        }

        public Criteria andResultIdLessThanOrEqualTo(String value) {
            addCriterion("RESULT_ID <=", value, "resultId");
            return (Criteria) this;
        }

        public Criteria andResultIdLike(String value) {
            addCriterion("RESULT_ID like", value, "resultId");
            return (Criteria) this;
        }

        public Criteria andResultIdNotLike(String value) {
            addCriterion("RESULT_ID not like", value, "resultId");
            return (Criteria) this;
        }

        public Criteria andResultIdIn(List<String> values) {
            addCriterion("RESULT_ID in", values, "resultId");
            return (Criteria) this;
        }

        public Criteria andResultIdNotIn(List<String> values) {
            addCriterion("RESULT_ID not in", values, "resultId");
            return (Criteria) this;
        }

        public Criteria andResultIdBetween(String value1, String value2) {
            addCriterion("RESULT_ID between", value1, value2, "resultId");
            return (Criteria) this;
        }

        public Criteria andResultIdNotBetween(String value1, String value2) {
            addCriterion("RESULT_ID not between", value1, value2, "resultId");
            return (Criteria) this;
        }

        public Criteria andResultNameIsNull() {
            addCriterion("RESULT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andResultNameIsNotNull() {
            addCriterion("RESULT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andResultNameEqualTo(String value) {
            addCriterion("RESULT_NAME =", value, "resultName");
            return (Criteria) this;
        }

        public Criteria andResultNameNotEqualTo(String value) {
            addCriterion("RESULT_NAME <>", value, "resultName");
            return (Criteria) this;
        }

        public Criteria andResultNameGreaterThan(String value) {
            addCriterion("RESULT_NAME >", value, "resultName");
            return (Criteria) this;
        }

        public Criteria andResultNameGreaterThanOrEqualTo(String value) {
            addCriterion("RESULT_NAME >=", value, "resultName");
            return (Criteria) this;
        }

        public Criteria andResultNameLessThan(String value) {
            addCriterion("RESULT_NAME <", value, "resultName");
            return (Criteria) this;
        }

        public Criteria andResultNameLessThanOrEqualTo(String value) {
            addCriterion("RESULT_NAME <=", value, "resultName");
            return (Criteria) this;
        }

        public Criteria andResultNameLike(String value) {
            addCriterion("RESULT_NAME like", value, "resultName");
            return (Criteria) this;
        }

        public Criteria andResultNameNotLike(String value) {
            addCriterion("RESULT_NAME not like", value, "resultName");
            return (Criteria) this;
        }

        public Criteria andResultNameIn(List<String> values) {
            addCriterion("RESULT_NAME in", values, "resultName");
            return (Criteria) this;
        }

        public Criteria andResultNameNotIn(List<String> values) {
            addCriterion("RESULT_NAME not in", values, "resultName");
            return (Criteria) this;
        }

        public Criteria andResultNameBetween(String value1, String value2) {
            addCriterion("RESULT_NAME between", value1, value2, "resultName");
            return (Criteria) this;
        }

        public Criteria andResultNameNotBetween(String value1, String value2) {
            addCriterion("RESULT_NAME not between", value1, value2, "resultName");
            return (Criteria) this;
        }

        public Criteria andSignIsNull() {
            addCriterion("SIGN is null");
            return (Criteria) this;
        }

        public Criteria andSignIsNotNull() {
            addCriterion("SIGN is not null");
            return (Criteria) this;
        }

        public Criteria andSignEqualTo(String value) {
            addCriterion("SIGN =", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotEqualTo(String value) {
            addCriterion("SIGN <>", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignGreaterThan(String value) {
            addCriterion("SIGN >", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignGreaterThanOrEqualTo(String value) {
            addCriterion("SIGN >=", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignLessThan(String value) {
            addCriterion("SIGN <", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignLessThanOrEqualTo(String value) {
            addCriterion("SIGN <=", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignLike(String value) {
            addCriterion("SIGN like", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotLike(String value) {
            addCriterion("SIGN not like", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignIn(List<String> values) {
            addCriterion("SIGN in", values, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotIn(List<String> values) {
            addCriterion("SIGN not in", values, "sign");
            return (Criteria) this;
        }

        public Criteria andSignBetween(String value1, String value2) {
            addCriterion("SIGN between", value1, value2, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotBetween(String value1, String value2) {
            addCriterion("SIGN not between", value1, value2, "sign");
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