package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class GcpQcRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GcpQcRecordExample() {
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

        public Criteria andQcFlowIsNull() {
            addCriterion("QC_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andQcFlowIsNotNull() {
            addCriterion("QC_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andQcFlowEqualTo(String value) {
            addCriterion("QC_FLOW =", value, "qcFlow");
            return (Criteria) this;
        }

        public Criteria andQcFlowNotEqualTo(String value) {
            addCriterion("QC_FLOW <>", value, "qcFlow");
            return (Criteria) this;
        }

        public Criteria andQcFlowGreaterThan(String value) {
            addCriterion("QC_FLOW >", value, "qcFlow");
            return (Criteria) this;
        }

        public Criteria andQcFlowGreaterThanOrEqualTo(String value) {
            addCriterion("QC_FLOW >=", value, "qcFlow");
            return (Criteria) this;
        }

        public Criteria andQcFlowLessThan(String value) {
            addCriterion("QC_FLOW <", value, "qcFlow");
            return (Criteria) this;
        }

        public Criteria andQcFlowLessThanOrEqualTo(String value) {
            addCriterion("QC_FLOW <=", value, "qcFlow");
            return (Criteria) this;
        }

        public Criteria andQcFlowLike(String value) {
            addCriterion("QC_FLOW like", value, "qcFlow");
            return (Criteria) this;
        }

        public Criteria andQcFlowNotLike(String value) {
            addCriterion("QC_FLOW not like", value, "qcFlow");
            return (Criteria) this;
        }

        public Criteria andQcFlowIn(List<String> values) {
            addCriterion("QC_FLOW in", values, "qcFlow");
            return (Criteria) this;
        }

        public Criteria andQcFlowNotIn(List<String> values) {
            addCriterion("QC_FLOW not in", values, "qcFlow");
            return (Criteria) this;
        }

        public Criteria andQcFlowBetween(String value1, String value2) {
            addCriterion("QC_FLOW between", value1, value2, "qcFlow");
            return (Criteria) this;
        }

        public Criteria andQcFlowNotBetween(String value1, String value2) {
            addCriterion("QC_FLOW not between", value1, value2, "qcFlow");
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

        public Criteria andQcTypeIdIsNull() {
            addCriterion("QC_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andQcTypeIdIsNotNull() {
            addCriterion("QC_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andQcTypeIdEqualTo(String value) {
            addCriterion("QC_TYPE_ID =", value, "qcTypeId");
            return (Criteria) this;
        }

        public Criteria andQcTypeIdNotEqualTo(String value) {
            addCriterion("QC_TYPE_ID <>", value, "qcTypeId");
            return (Criteria) this;
        }

        public Criteria andQcTypeIdGreaterThan(String value) {
            addCriterion("QC_TYPE_ID >", value, "qcTypeId");
            return (Criteria) this;
        }

        public Criteria andQcTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("QC_TYPE_ID >=", value, "qcTypeId");
            return (Criteria) this;
        }

        public Criteria andQcTypeIdLessThan(String value) {
            addCriterion("QC_TYPE_ID <", value, "qcTypeId");
            return (Criteria) this;
        }

        public Criteria andQcTypeIdLessThanOrEqualTo(String value) {
            addCriterion("QC_TYPE_ID <=", value, "qcTypeId");
            return (Criteria) this;
        }

        public Criteria andQcTypeIdLike(String value) {
            addCriterion("QC_TYPE_ID like", value, "qcTypeId");
            return (Criteria) this;
        }

        public Criteria andQcTypeIdNotLike(String value) {
            addCriterion("QC_TYPE_ID not like", value, "qcTypeId");
            return (Criteria) this;
        }

        public Criteria andQcTypeIdIn(List<String> values) {
            addCriterion("QC_TYPE_ID in", values, "qcTypeId");
            return (Criteria) this;
        }

        public Criteria andQcTypeIdNotIn(List<String> values) {
            addCriterion("QC_TYPE_ID not in", values, "qcTypeId");
            return (Criteria) this;
        }

        public Criteria andQcTypeIdBetween(String value1, String value2) {
            addCriterion("QC_TYPE_ID between", value1, value2, "qcTypeId");
            return (Criteria) this;
        }

        public Criteria andQcTypeIdNotBetween(String value1, String value2) {
            addCriterion("QC_TYPE_ID not between", value1, value2, "qcTypeId");
            return (Criteria) this;
        }

        public Criteria andQcTypeNameIsNull() {
            addCriterion("QC_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andQcTypeNameIsNotNull() {
            addCriterion("QC_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andQcTypeNameEqualTo(String value) {
            addCriterion("QC_TYPE_NAME =", value, "qcTypeName");
            return (Criteria) this;
        }

        public Criteria andQcTypeNameNotEqualTo(String value) {
            addCriterion("QC_TYPE_NAME <>", value, "qcTypeName");
            return (Criteria) this;
        }

        public Criteria andQcTypeNameGreaterThan(String value) {
            addCriterion("QC_TYPE_NAME >", value, "qcTypeName");
            return (Criteria) this;
        }

        public Criteria andQcTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("QC_TYPE_NAME >=", value, "qcTypeName");
            return (Criteria) this;
        }

        public Criteria andQcTypeNameLessThan(String value) {
            addCriterion("QC_TYPE_NAME <", value, "qcTypeName");
            return (Criteria) this;
        }

        public Criteria andQcTypeNameLessThanOrEqualTo(String value) {
            addCriterion("QC_TYPE_NAME <=", value, "qcTypeName");
            return (Criteria) this;
        }

        public Criteria andQcTypeNameLike(String value) {
            addCriterion("QC_TYPE_NAME like", value, "qcTypeName");
            return (Criteria) this;
        }

        public Criteria andQcTypeNameNotLike(String value) {
            addCriterion("QC_TYPE_NAME not like", value, "qcTypeName");
            return (Criteria) this;
        }

        public Criteria andQcTypeNameIn(List<String> values) {
            addCriterion("QC_TYPE_NAME in", values, "qcTypeName");
            return (Criteria) this;
        }

        public Criteria andQcTypeNameNotIn(List<String> values) {
            addCriterion("QC_TYPE_NAME not in", values, "qcTypeName");
            return (Criteria) this;
        }

        public Criteria andQcTypeNameBetween(String value1, String value2) {
            addCriterion("QC_TYPE_NAME between", value1, value2, "qcTypeName");
            return (Criteria) this;
        }

        public Criteria andQcTypeNameNotBetween(String value1, String value2) {
            addCriterion("QC_TYPE_NAME not between", value1, value2, "qcTypeName");
            return (Criteria) this;
        }

        public Criteria andQcCategoryIdIsNull() {
            addCriterion("QC_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andQcCategoryIdIsNotNull() {
            addCriterion("QC_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andQcCategoryIdEqualTo(String value) {
            addCriterion("QC_CATEGORY_ID =", value, "qcCategoryId");
            return (Criteria) this;
        }

        public Criteria andQcCategoryIdNotEqualTo(String value) {
            addCriterion("QC_CATEGORY_ID <>", value, "qcCategoryId");
            return (Criteria) this;
        }

        public Criteria andQcCategoryIdGreaterThan(String value) {
            addCriterion("QC_CATEGORY_ID >", value, "qcCategoryId");
            return (Criteria) this;
        }

        public Criteria andQcCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("QC_CATEGORY_ID >=", value, "qcCategoryId");
            return (Criteria) this;
        }

        public Criteria andQcCategoryIdLessThan(String value) {
            addCriterion("QC_CATEGORY_ID <", value, "qcCategoryId");
            return (Criteria) this;
        }

        public Criteria andQcCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("QC_CATEGORY_ID <=", value, "qcCategoryId");
            return (Criteria) this;
        }

        public Criteria andQcCategoryIdLike(String value) {
            addCriterion("QC_CATEGORY_ID like", value, "qcCategoryId");
            return (Criteria) this;
        }

        public Criteria andQcCategoryIdNotLike(String value) {
            addCriterion("QC_CATEGORY_ID not like", value, "qcCategoryId");
            return (Criteria) this;
        }

        public Criteria andQcCategoryIdIn(List<String> values) {
            addCriterion("QC_CATEGORY_ID in", values, "qcCategoryId");
            return (Criteria) this;
        }

        public Criteria andQcCategoryIdNotIn(List<String> values) {
            addCriterion("QC_CATEGORY_ID not in", values, "qcCategoryId");
            return (Criteria) this;
        }

        public Criteria andQcCategoryIdBetween(String value1, String value2) {
            addCriterion("QC_CATEGORY_ID between", value1, value2, "qcCategoryId");
            return (Criteria) this;
        }

        public Criteria andQcCategoryIdNotBetween(String value1, String value2) {
            addCriterion("QC_CATEGORY_ID not between", value1, value2, "qcCategoryId");
            return (Criteria) this;
        }

        public Criteria andQcCategoryNameIsNull() {
            addCriterion("QC_CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andQcCategoryNameIsNotNull() {
            addCriterion("QC_CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andQcCategoryNameEqualTo(String value) {
            addCriterion("QC_CATEGORY_NAME =", value, "qcCategoryName");
            return (Criteria) this;
        }

        public Criteria andQcCategoryNameNotEqualTo(String value) {
            addCriterion("QC_CATEGORY_NAME <>", value, "qcCategoryName");
            return (Criteria) this;
        }

        public Criteria andQcCategoryNameGreaterThan(String value) {
            addCriterion("QC_CATEGORY_NAME >", value, "qcCategoryName");
            return (Criteria) this;
        }

        public Criteria andQcCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("QC_CATEGORY_NAME >=", value, "qcCategoryName");
            return (Criteria) this;
        }

        public Criteria andQcCategoryNameLessThan(String value) {
            addCriterion("QC_CATEGORY_NAME <", value, "qcCategoryName");
            return (Criteria) this;
        }

        public Criteria andQcCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("QC_CATEGORY_NAME <=", value, "qcCategoryName");
            return (Criteria) this;
        }

        public Criteria andQcCategoryNameLike(String value) {
            addCriterion("QC_CATEGORY_NAME like", value, "qcCategoryName");
            return (Criteria) this;
        }

        public Criteria andQcCategoryNameNotLike(String value) {
            addCriterion("QC_CATEGORY_NAME not like", value, "qcCategoryName");
            return (Criteria) this;
        }

        public Criteria andQcCategoryNameIn(List<String> values) {
            addCriterion("QC_CATEGORY_NAME in", values, "qcCategoryName");
            return (Criteria) this;
        }

        public Criteria andQcCategoryNameNotIn(List<String> values) {
            addCriterion("QC_CATEGORY_NAME not in", values, "qcCategoryName");
            return (Criteria) this;
        }

        public Criteria andQcCategoryNameBetween(String value1, String value2) {
            addCriterion("QC_CATEGORY_NAME between", value1, value2, "qcCategoryName");
            return (Criteria) this;
        }

        public Criteria andQcCategoryNameNotBetween(String value1, String value2) {
            addCriterion("QC_CATEGORY_NAME not between", value1, value2, "qcCategoryName");
            return (Criteria) this;
        }

        public Criteria andQcStartDateIsNull() {
            addCriterion("QC_START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andQcStartDateIsNotNull() {
            addCriterion("QC_START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andQcStartDateEqualTo(String value) {
            addCriterion("QC_START_DATE =", value, "qcStartDate");
            return (Criteria) this;
        }

        public Criteria andQcStartDateNotEqualTo(String value) {
            addCriterion("QC_START_DATE <>", value, "qcStartDate");
            return (Criteria) this;
        }

        public Criteria andQcStartDateGreaterThan(String value) {
            addCriterion("QC_START_DATE >", value, "qcStartDate");
            return (Criteria) this;
        }

        public Criteria andQcStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("QC_START_DATE >=", value, "qcStartDate");
            return (Criteria) this;
        }

        public Criteria andQcStartDateLessThan(String value) {
            addCriterion("QC_START_DATE <", value, "qcStartDate");
            return (Criteria) this;
        }

        public Criteria andQcStartDateLessThanOrEqualTo(String value) {
            addCriterion("QC_START_DATE <=", value, "qcStartDate");
            return (Criteria) this;
        }

        public Criteria andQcStartDateLike(String value) {
            addCriterion("QC_START_DATE like", value, "qcStartDate");
            return (Criteria) this;
        }

        public Criteria andQcStartDateNotLike(String value) {
            addCriterion("QC_START_DATE not like", value, "qcStartDate");
            return (Criteria) this;
        }

        public Criteria andQcStartDateIn(List<String> values) {
            addCriterion("QC_START_DATE in", values, "qcStartDate");
            return (Criteria) this;
        }

        public Criteria andQcStartDateNotIn(List<String> values) {
            addCriterion("QC_START_DATE not in", values, "qcStartDate");
            return (Criteria) this;
        }

        public Criteria andQcStartDateBetween(String value1, String value2) {
            addCriterion("QC_START_DATE between", value1, value2, "qcStartDate");
            return (Criteria) this;
        }

        public Criteria andQcStartDateNotBetween(String value1, String value2) {
            addCriterion("QC_START_DATE not between", value1, value2, "qcStartDate");
            return (Criteria) this;
        }

        public Criteria andQcEndDateIsNull() {
            addCriterion("QC_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andQcEndDateIsNotNull() {
            addCriterion("QC_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andQcEndDateEqualTo(String value) {
            addCriterion("QC_END_DATE =", value, "qcEndDate");
            return (Criteria) this;
        }

        public Criteria andQcEndDateNotEqualTo(String value) {
            addCriterion("QC_END_DATE <>", value, "qcEndDate");
            return (Criteria) this;
        }

        public Criteria andQcEndDateGreaterThan(String value) {
            addCriterion("QC_END_DATE >", value, "qcEndDate");
            return (Criteria) this;
        }

        public Criteria andQcEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("QC_END_DATE >=", value, "qcEndDate");
            return (Criteria) this;
        }

        public Criteria andQcEndDateLessThan(String value) {
            addCriterion("QC_END_DATE <", value, "qcEndDate");
            return (Criteria) this;
        }

        public Criteria andQcEndDateLessThanOrEqualTo(String value) {
            addCriterion("QC_END_DATE <=", value, "qcEndDate");
            return (Criteria) this;
        }

        public Criteria andQcEndDateLike(String value) {
            addCriterion("QC_END_DATE like", value, "qcEndDate");
            return (Criteria) this;
        }

        public Criteria andQcEndDateNotLike(String value) {
            addCriterion("QC_END_DATE not like", value, "qcEndDate");
            return (Criteria) this;
        }

        public Criteria andQcEndDateIn(List<String> values) {
            addCriterion("QC_END_DATE in", values, "qcEndDate");
            return (Criteria) this;
        }

        public Criteria andQcEndDateNotIn(List<String> values) {
            addCriterion("QC_END_DATE not in", values, "qcEndDate");
            return (Criteria) this;
        }

        public Criteria andQcEndDateBetween(String value1, String value2) {
            addCriterion("QC_END_DATE between", value1, value2, "qcEndDate");
            return (Criteria) this;
        }

        public Criteria andQcEndDateNotBetween(String value1, String value2) {
            addCriterion("QC_END_DATE not between", value1, value2, "qcEndDate");
            return (Criteria) this;
        }

        public Criteria andQcOperatorIsNull() {
            addCriterion("QC_OPERATOR is null");
            return (Criteria) this;
        }

        public Criteria andQcOperatorIsNotNull() {
            addCriterion("QC_OPERATOR is not null");
            return (Criteria) this;
        }

        public Criteria andQcOperatorEqualTo(String value) {
            addCriterion("QC_OPERATOR =", value, "qcOperator");
            return (Criteria) this;
        }

        public Criteria andQcOperatorNotEqualTo(String value) {
            addCriterion("QC_OPERATOR <>", value, "qcOperator");
            return (Criteria) this;
        }

        public Criteria andQcOperatorGreaterThan(String value) {
            addCriterion("QC_OPERATOR >", value, "qcOperator");
            return (Criteria) this;
        }

        public Criteria andQcOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("QC_OPERATOR >=", value, "qcOperator");
            return (Criteria) this;
        }

        public Criteria andQcOperatorLessThan(String value) {
            addCriterion("QC_OPERATOR <", value, "qcOperator");
            return (Criteria) this;
        }

        public Criteria andQcOperatorLessThanOrEqualTo(String value) {
            addCriterion("QC_OPERATOR <=", value, "qcOperator");
            return (Criteria) this;
        }

        public Criteria andQcOperatorLike(String value) {
            addCriterion("QC_OPERATOR like", value, "qcOperator");
            return (Criteria) this;
        }

        public Criteria andQcOperatorNotLike(String value) {
            addCriterion("QC_OPERATOR not like", value, "qcOperator");
            return (Criteria) this;
        }

        public Criteria andQcOperatorIn(List<String> values) {
            addCriterion("QC_OPERATOR in", values, "qcOperator");
            return (Criteria) this;
        }

        public Criteria andQcOperatorNotIn(List<String> values) {
            addCriterion("QC_OPERATOR not in", values, "qcOperator");
            return (Criteria) this;
        }

        public Criteria andQcOperatorBetween(String value1, String value2) {
            addCriterion("QC_OPERATOR between", value1, value2, "qcOperator");
            return (Criteria) this;
        }

        public Criteria andQcOperatorNotBetween(String value1, String value2) {
            addCriterion("QC_OPERATOR not between", value1, value2, "qcOperator");
            return (Criteria) this;
        }

        public Criteria andQcDepartmentIsNull() {
            addCriterion("QC_DEPARTMENT is null");
            return (Criteria) this;
        }

        public Criteria andQcDepartmentIsNotNull() {
            addCriterion("QC_DEPARTMENT is not null");
            return (Criteria) this;
        }

        public Criteria andQcDepartmentEqualTo(String value) {
            addCriterion("QC_DEPARTMENT =", value, "qcDepartment");
            return (Criteria) this;
        }

        public Criteria andQcDepartmentNotEqualTo(String value) {
            addCriterion("QC_DEPARTMENT <>", value, "qcDepartment");
            return (Criteria) this;
        }

        public Criteria andQcDepartmentGreaterThan(String value) {
            addCriterion("QC_DEPARTMENT >", value, "qcDepartment");
            return (Criteria) this;
        }

        public Criteria andQcDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("QC_DEPARTMENT >=", value, "qcDepartment");
            return (Criteria) this;
        }

        public Criteria andQcDepartmentLessThan(String value) {
            addCriterion("QC_DEPARTMENT <", value, "qcDepartment");
            return (Criteria) this;
        }

        public Criteria andQcDepartmentLessThanOrEqualTo(String value) {
            addCriterion("QC_DEPARTMENT <=", value, "qcDepartment");
            return (Criteria) this;
        }

        public Criteria andQcDepartmentLike(String value) {
            addCriterion("QC_DEPARTMENT like", value, "qcDepartment");
            return (Criteria) this;
        }

        public Criteria andQcDepartmentNotLike(String value) {
            addCriterion("QC_DEPARTMENT not like", value, "qcDepartment");
            return (Criteria) this;
        }

        public Criteria andQcDepartmentIn(List<String> values) {
            addCriterion("QC_DEPARTMENT in", values, "qcDepartment");
            return (Criteria) this;
        }

        public Criteria andQcDepartmentNotIn(List<String> values) {
            addCriterion("QC_DEPARTMENT not in", values, "qcDepartment");
            return (Criteria) this;
        }

        public Criteria andQcDepartmentBetween(String value1, String value2) {
            addCriterion("QC_DEPARTMENT between", value1, value2, "qcDepartment");
            return (Criteria) this;
        }

        public Criteria andQcDepartmentNotBetween(String value1, String value2) {
            addCriterion("QC_DEPARTMENT not between", value1, value2, "qcDepartment");
            return (Criteria) this;
        }

        public Criteria andQcPatientCodesIsNull() {
            addCriterion("QC_PATIENT_CODES is null");
            return (Criteria) this;
        }

        public Criteria andQcPatientCodesIsNotNull() {
            addCriterion("QC_PATIENT_CODES is not null");
            return (Criteria) this;
        }

        public Criteria andQcPatientCodesEqualTo(String value) {
            addCriterion("QC_PATIENT_CODES =", value, "qcPatientCodes");
            return (Criteria) this;
        }

        public Criteria andQcPatientCodesNotEqualTo(String value) {
            addCriterion("QC_PATIENT_CODES <>", value, "qcPatientCodes");
            return (Criteria) this;
        }

        public Criteria andQcPatientCodesGreaterThan(String value) {
            addCriterion("QC_PATIENT_CODES >", value, "qcPatientCodes");
            return (Criteria) this;
        }

        public Criteria andQcPatientCodesGreaterThanOrEqualTo(String value) {
            addCriterion("QC_PATIENT_CODES >=", value, "qcPatientCodes");
            return (Criteria) this;
        }

        public Criteria andQcPatientCodesLessThan(String value) {
            addCriterion("QC_PATIENT_CODES <", value, "qcPatientCodes");
            return (Criteria) this;
        }

        public Criteria andQcPatientCodesLessThanOrEqualTo(String value) {
            addCriterion("QC_PATIENT_CODES <=", value, "qcPatientCodes");
            return (Criteria) this;
        }

        public Criteria andQcPatientCodesLike(String value) {
            addCriterion("QC_PATIENT_CODES like", value, "qcPatientCodes");
            return (Criteria) this;
        }

        public Criteria andQcPatientCodesNotLike(String value) {
            addCriterion("QC_PATIENT_CODES not like", value, "qcPatientCodes");
            return (Criteria) this;
        }

        public Criteria andQcPatientCodesIn(List<String> values) {
            addCriterion("QC_PATIENT_CODES in", values, "qcPatientCodes");
            return (Criteria) this;
        }

        public Criteria andQcPatientCodesNotIn(List<String> values) {
            addCriterion("QC_PATIENT_CODES not in", values, "qcPatientCodes");
            return (Criteria) this;
        }

        public Criteria andQcPatientCodesBetween(String value1, String value2) {
            addCriterion("QC_PATIENT_CODES between", value1, value2, "qcPatientCodes");
            return (Criteria) this;
        }

        public Criteria andQcPatientCodesNotBetween(String value1, String value2) {
            addCriterion("QC_PATIENT_CODES not between", value1, value2, "qcPatientCodes");
            return (Criteria) this;
        }

        public Criteria andQcStatusIdIsNull() {
            addCriterion("QC_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andQcStatusIdIsNotNull() {
            addCriterion("QC_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andQcStatusIdEqualTo(String value) {
            addCriterion("QC_STATUS_ID =", value, "qcStatusId");
            return (Criteria) this;
        }

        public Criteria andQcStatusIdNotEqualTo(String value) {
            addCriterion("QC_STATUS_ID <>", value, "qcStatusId");
            return (Criteria) this;
        }

        public Criteria andQcStatusIdGreaterThan(String value) {
            addCriterion("QC_STATUS_ID >", value, "qcStatusId");
            return (Criteria) this;
        }

        public Criteria andQcStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("QC_STATUS_ID >=", value, "qcStatusId");
            return (Criteria) this;
        }

        public Criteria andQcStatusIdLessThan(String value) {
            addCriterion("QC_STATUS_ID <", value, "qcStatusId");
            return (Criteria) this;
        }

        public Criteria andQcStatusIdLessThanOrEqualTo(String value) {
            addCriterion("QC_STATUS_ID <=", value, "qcStatusId");
            return (Criteria) this;
        }

        public Criteria andQcStatusIdLike(String value) {
            addCriterion("QC_STATUS_ID like", value, "qcStatusId");
            return (Criteria) this;
        }

        public Criteria andQcStatusIdNotLike(String value) {
            addCriterion("QC_STATUS_ID not like", value, "qcStatusId");
            return (Criteria) this;
        }

        public Criteria andQcStatusIdIn(List<String> values) {
            addCriterion("QC_STATUS_ID in", values, "qcStatusId");
            return (Criteria) this;
        }

        public Criteria andQcStatusIdNotIn(List<String> values) {
            addCriterion("QC_STATUS_ID not in", values, "qcStatusId");
            return (Criteria) this;
        }

        public Criteria andQcStatusIdBetween(String value1, String value2) {
            addCriterion("QC_STATUS_ID between", value1, value2, "qcStatusId");
            return (Criteria) this;
        }

        public Criteria andQcStatusIdNotBetween(String value1, String value2) {
            addCriterion("QC_STATUS_ID not between", value1, value2, "qcStatusId");
            return (Criteria) this;
        }

        public Criteria andQcStatusNameIsNull() {
            addCriterion("QC_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andQcStatusNameIsNotNull() {
            addCriterion("QC_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andQcStatusNameEqualTo(String value) {
            addCriterion("QC_STATUS_NAME =", value, "qcStatusName");
            return (Criteria) this;
        }

        public Criteria andQcStatusNameNotEqualTo(String value) {
            addCriterion("QC_STATUS_NAME <>", value, "qcStatusName");
            return (Criteria) this;
        }

        public Criteria andQcStatusNameGreaterThan(String value) {
            addCriterion("QC_STATUS_NAME >", value, "qcStatusName");
            return (Criteria) this;
        }

        public Criteria andQcStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("QC_STATUS_NAME >=", value, "qcStatusName");
            return (Criteria) this;
        }

        public Criteria andQcStatusNameLessThan(String value) {
            addCriterion("QC_STATUS_NAME <", value, "qcStatusName");
            return (Criteria) this;
        }

        public Criteria andQcStatusNameLessThanOrEqualTo(String value) {
            addCriterion("QC_STATUS_NAME <=", value, "qcStatusName");
            return (Criteria) this;
        }

        public Criteria andQcStatusNameLike(String value) {
            addCriterion("QC_STATUS_NAME like", value, "qcStatusName");
            return (Criteria) this;
        }

        public Criteria andQcStatusNameNotLike(String value) {
            addCriterion("QC_STATUS_NAME not like", value, "qcStatusName");
            return (Criteria) this;
        }

        public Criteria andQcStatusNameIn(List<String> values) {
            addCriterion("QC_STATUS_NAME in", values, "qcStatusName");
            return (Criteria) this;
        }

        public Criteria andQcStatusNameNotIn(List<String> values) {
            addCriterion("QC_STATUS_NAME not in", values, "qcStatusName");
            return (Criteria) this;
        }

        public Criteria andQcStatusNameBetween(String value1, String value2) {
            addCriterion("QC_STATUS_NAME between", value1, value2, "qcStatusName");
            return (Criteria) this;
        }

        public Criteria andQcStatusNameNotBetween(String value1, String value2) {
            addCriterion("QC_STATUS_NAME not between", value1, value2, "qcStatusName");
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