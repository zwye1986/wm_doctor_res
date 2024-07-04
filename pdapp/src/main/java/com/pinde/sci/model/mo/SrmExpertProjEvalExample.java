package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SrmExpertProjEvalExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmExpertProjEvalExample() {
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

        public Criteria andEvalSetFlowIsNull() {
            addCriterion("EVAL_SET_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowIsNotNull() {
            addCriterion("EVAL_SET_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowEqualTo(String value) {
            addCriterion("EVAL_SET_FLOW =", value, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowNotEqualTo(String value) {
            addCriterion("EVAL_SET_FLOW <>", value, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowGreaterThan(String value) {
            addCriterion("EVAL_SET_FLOW >", value, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowGreaterThanOrEqualTo(String value) {
            addCriterion("EVAL_SET_FLOW >=", value, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowLessThan(String value) {
            addCriterion("EVAL_SET_FLOW <", value, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowLessThanOrEqualTo(String value) {
            addCriterion("EVAL_SET_FLOW <=", value, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowLike(String value) {
            addCriterion("EVAL_SET_FLOW like", value, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowNotLike(String value) {
            addCriterion("EVAL_SET_FLOW not like", value, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowIn(List<String> values) {
            addCriterion("EVAL_SET_FLOW in", values, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowNotIn(List<String> values) {
            addCriterion("EVAL_SET_FLOW not in", values, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowBetween(String value1, String value2) {
            addCriterion("EVAL_SET_FLOW between", value1, value2, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowNotBetween(String value1, String value2) {
            addCriterion("EVAL_SET_FLOW not between", value1, value2, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowIsNull() {
            addCriterion("GROUP_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andGroupFlowIsNotNull() {
            addCriterion("GROUP_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andGroupFlowEqualTo(String value) {
            addCriterion("GROUP_FLOW =", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotEqualTo(String value) {
            addCriterion("GROUP_FLOW <>", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowGreaterThan(String value) {
            addCriterion("GROUP_FLOW >", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_FLOW >=", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowLessThan(String value) {
            addCriterion("GROUP_FLOW <", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowLessThanOrEqualTo(String value) {
            addCriterion("GROUP_FLOW <=", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowLike(String value) {
            addCriterion("GROUP_FLOW like", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotLike(String value) {
            addCriterion("GROUP_FLOW not like", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowIn(List<String> values) {
            addCriterion("GROUP_FLOW in", values, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotIn(List<String> values) {
            addCriterion("GROUP_FLOW not in", values, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowBetween(String value1, String value2) {
            addCriterion("GROUP_FLOW between", value1, value2, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotBetween(String value1, String value2) {
            addCriterion("GROUP_FLOW not between", value1, value2, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNull() {
            addCriterion("GROUP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("GROUP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("GROUP_NAME =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("GROUP_NAME <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("GROUP_NAME >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_NAME >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("GROUP_NAME <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("GROUP_NAME <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("GROUP_NAME like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("GROUP_NAME not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("GROUP_NAME in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("GROUP_NAME not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("GROUP_NAME between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("GROUP_NAME not between", value1, value2, "groupName");
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

        public Criteria andLockStatusIsNull() {
            addCriterion("LOCK_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andLockStatusIsNotNull() {
            addCriterion("LOCK_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andLockStatusEqualTo(String value) {
            addCriterion("LOCK_STATUS =", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusNotEqualTo(String value) {
            addCriterion("LOCK_STATUS <>", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusGreaterThan(String value) {
            addCriterion("LOCK_STATUS >", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusGreaterThanOrEqualTo(String value) {
            addCriterion("LOCK_STATUS >=", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusLessThan(String value) {
            addCriterion("LOCK_STATUS <", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusLessThanOrEqualTo(String value) {
            addCriterion("LOCK_STATUS <=", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusLike(String value) {
            addCriterion("LOCK_STATUS like", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusNotLike(String value) {
            addCriterion("LOCK_STATUS not like", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusIn(List<String> values) {
            addCriterion("LOCK_STATUS in", values, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusNotIn(List<String> values) {
            addCriterion("LOCK_STATUS not in", values, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusBetween(String value1, String value2) {
            addCriterion("LOCK_STATUS between", value1, value2, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusNotBetween(String value1, String value2) {
            addCriterion("LOCK_STATUS not between", value1, value2, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNull() {
            addCriterion("BEGIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNotNull() {
            addCriterion("BEGIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBeginDateEqualTo(String value) {
            addCriterion("BEGIN_DATE =", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotEqualTo(String value) {
            addCriterion("BEGIN_DATE <>", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThan(String value) {
            addCriterion("BEGIN_DATE >", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThanOrEqualTo(String value) {
            addCriterion("BEGIN_DATE >=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThan(String value) {
            addCriterion("BEGIN_DATE <", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThanOrEqualTo(String value) {
            addCriterion("BEGIN_DATE <=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLike(String value) {
            addCriterion("BEGIN_DATE like", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotLike(String value) {
            addCriterion("BEGIN_DATE not like", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateIn(List<String> values) {
            addCriterion("BEGIN_DATE in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotIn(List<String> values) {
            addCriterion("BEGIN_DATE not in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateBetween(String value1, String value2) {
            addCriterion("BEGIN_DATE between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotBetween(String value1, String value2) {
            addCriterion("BEGIN_DATE not between", value1, value2, "beginDate");
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

        public Criteria andSchemeFlowIsNull() {
            addCriterion("SCHEME_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowIsNotNull() {
            addCriterion("SCHEME_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowEqualTo(String value) {
            addCriterion("SCHEME_FLOW =", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowNotEqualTo(String value) {
            addCriterion("SCHEME_FLOW <>", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowGreaterThan(String value) {
            addCriterion("SCHEME_FLOW >", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCHEME_FLOW >=", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowLessThan(String value) {
            addCriterion("SCHEME_FLOW <", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowLessThanOrEqualTo(String value) {
            addCriterion("SCHEME_FLOW <=", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowLike(String value) {
            addCriterion("SCHEME_FLOW like", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowNotLike(String value) {
            addCriterion("SCHEME_FLOW not like", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowIn(List<String> values) {
            addCriterion("SCHEME_FLOW in", values, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowNotIn(List<String> values) {
            addCriterion("SCHEME_FLOW not in", values, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowBetween(String value1, String value2) {
            addCriterion("SCHEME_FLOW between", value1, value2, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowNotBetween(String value1, String value2) {
            addCriterion("SCHEME_FLOW not between", value1, value2, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeNameIsNull() {
            addCriterion("SCHEME_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSchemeNameIsNotNull() {
            addCriterion("SCHEME_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSchemeNameEqualTo(String value) {
            addCriterion("SCHEME_NAME =", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameNotEqualTo(String value) {
            addCriterion("SCHEME_NAME <>", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameGreaterThan(String value) {
            addCriterion("SCHEME_NAME >", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCHEME_NAME >=", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameLessThan(String value) {
            addCriterion("SCHEME_NAME <", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameLessThanOrEqualTo(String value) {
            addCriterion("SCHEME_NAME <=", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameLike(String value) {
            addCriterion("SCHEME_NAME like", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameNotLike(String value) {
            addCriterion("SCHEME_NAME not like", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameIn(List<String> values) {
            addCriterion("SCHEME_NAME in", values, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameNotIn(List<String> values) {
            addCriterion("SCHEME_NAME not in", values, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameBetween(String value1, String value2) {
            addCriterion("SCHEME_NAME between", value1, value2, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameNotBetween(String value1, String value2) {
            addCriterion("SCHEME_NAME not between", value1, value2, "schemeName");
            return (Criteria) this;
        }

        public Criteria andEvaluationIdIsNull() {
            addCriterion("EVALUATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationIdIsNotNull() {
            addCriterion("EVALUATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationIdEqualTo(String value) {
            addCriterion("EVALUATION_ID =", value, "evaluationId");
            return (Criteria) this;
        }

        public Criteria andEvaluationIdNotEqualTo(String value) {
            addCriterion("EVALUATION_ID <>", value, "evaluationId");
            return (Criteria) this;
        }

        public Criteria andEvaluationIdGreaterThan(String value) {
            addCriterion("EVALUATION_ID >", value, "evaluationId");
            return (Criteria) this;
        }

        public Criteria andEvaluationIdGreaterThanOrEqualTo(String value) {
            addCriterion("EVALUATION_ID >=", value, "evaluationId");
            return (Criteria) this;
        }

        public Criteria andEvaluationIdLessThan(String value) {
            addCriterion("EVALUATION_ID <", value, "evaluationId");
            return (Criteria) this;
        }

        public Criteria andEvaluationIdLessThanOrEqualTo(String value) {
            addCriterion("EVALUATION_ID <=", value, "evaluationId");
            return (Criteria) this;
        }

        public Criteria andEvaluationIdLike(String value) {
            addCriterion("EVALUATION_ID like", value, "evaluationId");
            return (Criteria) this;
        }

        public Criteria andEvaluationIdNotLike(String value) {
            addCriterion("EVALUATION_ID not like", value, "evaluationId");
            return (Criteria) this;
        }

        public Criteria andEvaluationIdIn(List<String> values) {
            addCriterion("EVALUATION_ID in", values, "evaluationId");
            return (Criteria) this;
        }

        public Criteria andEvaluationIdNotIn(List<String> values) {
            addCriterion("EVALUATION_ID not in", values, "evaluationId");
            return (Criteria) this;
        }

        public Criteria andEvaluationIdBetween(String value1, String value2) {
            addCriterion("EVALUATION_ID between", value1, value2, "evaluationId");
            return (Criteria) this;
        }

        public Criteria andEvaluationIdNotBetween(String value1, String value2) {
            addCriterion("EVALUATION_ID not between", value1, value2, "evaluationId");
            return (Criteria) this;
        }

        public Criteria andEvaluationNameIsNull() {
            addCriterion("EVALUATION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationNameIsNotNull() {
            addCriterion("EVALUATION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationNameEqualTo(String value) {
            addCriterion("EVALUATION_NAME =", value, "evaluationName");
            return (Criteria) this;
        }

        public Criteria andEvaluationNameNotEqualTo(String value) {
            addCriterion("EVALUATION_NAME <>", value, "evaluationName");
            return (Criteria) this;
        }

        public Criteria andEvaluationNameGreaterThan(String value) {
            addCriterion("EVALUATION_NAME >", value, "evaluationName");
            return (Criteria) this;
        }

        public Criteria andEvaluationNameGreaterThanOrEqualTo(String value) {
            addCriterion("EVALUATION_NAME >=", value, "evaluationName");
            return (Criteria) this;
        }

        public Criteria andEvaluationNameLessThan(String value) {
            addCriterion("EVALUATION_NAME <", value, "evaluationName");
            return (Criteria) this;
        }

        public Criteria andEvaluationNameLessThanOrEqualTo(String value) {
            addCriterion("EVALUATION_NAME <=", value, "evaluationName");
            return (Criteria) this;
        }

        public Criteria andEvaluationNameLike(String value) {
            addCriterion("EVALUATION_NAME like", value, "evaluationName");
            return (Criteria) this;
        }

        public Criteria andEvaluationNameNotLike(String value) {
            addCriterion("EVALUATION_NAME not like", value, "evaluationName");
            return (Criteria) this;
        }

        public Criteria andEvaluationNameIn(List<String> values) {
            addCriterion("EVALUATION_NAME in", values, "evaluationName");
            return (Criteria) this;
        }

        public Criteria andEvaluationNameNotIn(List<String> values) {
            addCriterion("EVALUATION_NAME not in", values, "evaluationName");
            return (Criteria) this;
        }

        public Criteria andEvaluationNameBetween(String value1, String value2) {
            addCriterion("EVALUATION_NAME between", value1, value2, "evaluationName");
            return (Criteria) this;
        }

        public Criteria andEvaluationNameNotBetween(String value1, String value2) {
            addCriterion("EVALUATION_NAME not between", value1, value2, "evaluationName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdIsNull() {
            addCriterion("EVALUATION_WAY_ID is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdIsNotNull() {
            addCriterion("EVALUATION_WAY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdEqualTo(String value) {
            addCriterion("EVALUATION_WAY_ID =", value, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdNotEqualTo(String value) {
            addCriterion("EVALUATION_WAY_ID <>", value, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdGreaterThan(String value) {
            addCriterion("EVALUATION_WAY_ID >", value, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdGreaterThanOrEqualTo(String value) {
            addCriterion("EVALUATION_WAY_ID >=", value, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdLessThan(String value) {
            addCriterion("EVALUATION_WAY_ID <", value, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdLessThanOrEqualTo(String value) {
            addCriterion("EVALUATION_WAY_ID <=", value, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdLike(String value) {
            addCriterion("EVALUATION_WAY_ID like", value, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdNotLike(String value) {
            addCriterion("EVALUATION_WAY_ID not like", value, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdIn(List<String> values) {
            addCriterion("EVALUATION_WAY_ID in", values, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdNotIn(List<String> values) {
            addCriterion("EVALUATION_WAY_ID not in", values, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdBetween(String value1, String value2) {
            addCriterion("EVALUATION_WAY_ID between", value1, value2, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdNotBetween(String value1, String value2) {
            addCriterion("EVALUATION_WAY_ID not between", value1, value2, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameIsNull() {
            addCriterion("EVALUATION_WAY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameIsNotNull() {
            addCriterion("EVALUATION_WAY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameEqualTo(String value) {
            addCriterion("EVALUATION_WAY_NAME =", value, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameNotEqualTo(String value) {
            addCriterion("EVALUATION_WAY_NAME <>", value, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameGreaterThan(String value) {
            addCriterion("EVALUATION_WAY_NAME >", value, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameGreaterThanOrEqualTo(String value) {
            addCriterion("EVALUATION_WAY_NAME >=", value, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameLessThan(String value) {
            addCriterion("EVALUATION_WAY_NAME <", value, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameLessThanOrEqualTo(String value) {
            addCriterion("EVALUATION_WAY_NAME <=", value, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameLike(String value) {
            addCriterion("EVALUATION_WAY_NAME like", value, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameNotLike(String value) {
            addCriterion("EVALUATION_WAY_NAME not like", value, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameIn(List<String> values) {
            addCriterion("EVALUATION_WAY_NAME in", values, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameNotIn(List<String> values) {
            addCriterion("EVALUATION_WAY_NAME not in", values, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameBetween(String value1, String value2) {
            addCriterion("EVALUATION_WAY_NAME between", value1, value2, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameNotBetween(String value1, String value2) {
            addCriterion("EVALUATION_WAY_NAME not between", value1, value2, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdIsNull() {
            addCriterion("EVAL_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdIsNotNull() {
            addCriterion("EVAL_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdEqualTo(String value) {
            addCriterion("EVAL_STATUS_ID =", value, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdNotEqualTo(String value) {
            addCriterion("EVAL_STATUS_ID <>", value, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdGreaterThan(String value) {
            addCriterion("EVAL_STATUS_ID >", value, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("EVAL_STATUS_ID >=", value, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdLessThan(String value) {
            addCriterion("EVAL_STATUS_ID <", value, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdLessThanOrEqualTo(String value) {
            addCriterion("EVAL_STATUS_ID <=", value, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdLike(String value) {
            addCriterion("EVAL_STATUS_ID like", value, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdNotLike(String value) {
            addCriterion("EVAL_STATUS_ID not like", value, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdIn(List<String> values) {
            addCriterion("EVAL_STATUS_ID in", values, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdNotIn(List<String> values) {
            addCriterion("EVAL_STATUS_ID not in", values, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdBetween(String value1, String value2) {
            addCriterion("EVAL_STATUS_ID between", value1, value2, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdNotBetween(String value1, String value2) {
            addCriterion("EVAL_STATUS_ID not between", value1, value2, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameIsNull() {
            addCriterion("EVAL_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameIsNotNull() {
            addCriterion("EVAL_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameEqualTo(String value) {
            addCriterion("EVAL_STATUS_NAME =", value, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameNotEqualTo(String value) {
            addCriterion("EVAL_STATUS_NAME <>", value, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameGreaterThan(String value) {
            addCriterion("EVAL_STATUS_NAME >", value, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("EVAL_STATUS_NAME >=", value, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameLessThan(String value) {
            addCriterion("EVAL_STATUS_NAME <", value, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameLessThanOrEqualTo(String value) {
            addCriterion("EVAL_STATUS_NAME <=", value, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameLike(String value) {
            addCriterion("EVAL_STATUS_NAME like", value, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameNotLike(String value) {
            addCriterion("EVAL_STATUS_NAME not like", value, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameIn(List<String> values) {
            addCriterion("EVAL_STATUS_NAME in", values, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameNotIn(List<String> values) {
            addCriterion("EVAL_STATUS_NAME not in", values, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameBetween(String value1, String value2) {
            addCriterion("EVAL_STATUS_NAME between", value1, value2, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameNotBetween(String value1, String value2) {
            addCriterion("EVAL_STATUS_NAME not between", value1, value2, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalOpinionIsNull() {
            addCriterion("EVAL_OPINION is null");
            return (Criteria) this;
        }

        public Criteria andEvalOpinionIsNotNull() {
            addCriterion("EVAL_OPINION is not null");
            return (Criteria) this;
        }

        public Criteria andEvalOpinionEqualTo(String value) {
            addCriterion("EVAL_OPINION =", value, "evalOpinion");
            return (Criteria) this;
        }

        public Criteria andEvalOpinionNotEqualTo(String value) {
            addCriterion("EVAL_OPINION <>", value, "evalOpinion");
            return (Criteria) this;
        }

        public Criteria andEvalOpinionGreaterThan(String value) {
            addCriterion("EVAL_OPINION >", value, "evalOpinion");
            return (Criteria) this;
        }

        public Criteria andEvalOpinionGreaterThanOrEqualTo(String value) {
            addCriterion("EVAL_OPINION >=", value, "evalOpinion");
            return (Criteria) this;
        }

        public Criteria andEvalOpinionLessThan(String value) {
            addCriterion("EVAL_OPINION <", value, "evalOpinion");
            return (Criteria) this;
        }

        public Criteria andEvalOpinionLessThanOrEqualTo(String value) {
            addCriterion("EVAL_OPINION <=", value, "evalOpinion");
            return (Criteria) this;
        }

        public Criteria andEvalOpinionLike(String value) {
            addCriterion("EVAL_OPINION like", value, "evalOpinion");
            return (Criteria) this;
        }

        public Criteria andEvalOpinionNotLike(String value) {
            addCriterion("EVAL_OPINION not like", value, "evalOpinion");
            return (Criteria) this;
        }

        public Criteria andEvalOpinionIn(List<String> values) {
            addCriterion("EVAL_OPINION in", values, "evalOpinion");
            return (Criteria) this;
        }

        public Criteria andEvalOpinionNotIn(List<String> values) {
            addCriterion("EVAL_OPINION not in", values, "evalOpinion");
            return (Criteria) this;
        }

        public Criteria andEvalOpinionBetween(String value1, String value2) {
            addCriterion("EVAL_OPINION between", value1, value2, "evalOpinion");
            return (Criteria) this;
        }

        public Criteria andEvalOpinionNotBetween(String value1, String value2) {
            addCriterion("EVAL_OPINION not between", value1, value2, "evalOpinion");
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