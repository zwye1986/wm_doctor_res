package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class PubRegulationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PubRegulationExample() {
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

        public Criteria andRegulationFlowIsNull() {
            addCriterion("REGULATION_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRegulationFlowIsNotNull() {
            addCriterion("REGULATION_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRegulationFlowEqualTo(String value) {
            addCriterion("REGULATION_FLOW =", value, "regulationFlow");
            return (Criteria) this;
        }

        public Criteria andRegulationFlowNotEqualTo(String value) {
            addCriterion("REGULATION_FLOW <>", value, "regulationFlow");
            return (Criteria) this;
        }

        public Criteria andRegulationFlowGreaterThan(String value) {
            addCriterion("REGULATION_FLOW >", value, "regulationFlow");
            return (Criteria) this;
        }

        public Criteria andRegulationFlowGreaterThanOrEqualTo(String value) {
            addCriterion("REGULATION_FLOW >=", value, "regulationFlow");
            return (Criteria) this;
        }

        public Criteria andRegulationFlowLessThan(String value) {
            addCriterion("REGULATION_FLOW <", value, "regulationFlow");
            return (Criteria) this;
        }

        public Criteria andRegulationFlowLessThanOrEqualTo(String value) {
            addCriterion("REGULATION_FLOW <=", value, "regulationFlow");
            return (Criteria) this;
        }

        public Criteria andRegulationFlowLike(String value) {
            addCriterion("REGULATION_FLOW like", value, "regulationFlow");
            return (Criteria) this;
        }

        public Criteria andRegulationFlowNotLike(String value) {
            addCriterion("REGULATION_FLOW not like", value, "regulationFlow");
            return (Criteria) this;
        }

        public Criteria andRegulationFlowIn(List<String> values) {
            addCriterion("REGULATION_FLOW in", values, "regulationFlow");
            return (Criteria) this;
        }

        public Criteria andRegulationFlowNotIn(List<String> values) {
            addCriterion("REGULATION_FLOW not in", values, "regulationFlow");
            return (Criteria) this;
        }

        public Criteria andRegulationFlowBetween(String value1, String value2) {
            addCriterion("REGULATION_FLOW between", value1, value2, "regulationFlow");
            return (Criteria) this;
        }

        public Criteria andRegulationFlowNotBetween(String value1, String value2) {
            addCriterion("REGULATION_FLOW not between", value1, value2, "regulationFlow");
            return (Criteria) this;
        }

        public Criteria andRegulationNameIsNull() {
            addCriterion("REGULATION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRegulationNameIsNotNull() {
            addCriterion("REGULATION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRegulationNameEqualTo(String value) {
            addCriterion("REGULATION_NAME =", value, "regulationName");
            return (Criteria) this;
        }

        public Criteria andRegulationNameNotEqualTo(String value) {
            addCriterion("REGULATION_NAME <>", value, "regulationName");
            return (Criteria) this;
        }

        public Criteria andRegulationNameGreaterThan(String value) {
            addCriterion("REGULATION_NAME >", value, "regulationName");
            return (Criteria) this;
        }

        public Criteria andRegulationNameGreaterThanOrEqualTo(String value) {
            addCriterion("REGULATION_NAME >=", value, "regulationName");
            return (Criteria) this;
        }

        public Criteria andRegulationNameLessThan(String value) {
            addCriterion("REGULATION_NAME <", value, "regulationName");
            return (Criteria) this;
        }

        public Criteria andRegulationNameLessThanOrEqualTo(String value) {
            addCriterion("REGULATION_NAME <=", value, "regulationName");
            return (Criteria) this;
        }

        public Criteria andRegulationNameLike(String value) {
            addCriterion("REGULATION_NAME like", value, "regulationName");
            return (Criteria) this;
        }

        public Criteria andRegulationNameNotLike(String value) {
            addCriterion("REGULATION_NAME not like", value, "regulationName");
            return (Criteria) this;
        }

        public Criteria andRegulationNameIn(List<String> values) {
            addCriterion("REGULATION_NAME in", values, "regulationName");
            return (Criteria) this;
        }

        public Criteria andRegulationNameNotIn(List<String> values) {
            addCriterion("REGULATION_NAME not in", values, "regulationName");
            return (Criteria) this;
        }

        public Criteria andRegulationNameBetween(String value1, String value2) {
            addCriterion("REGULATION_NAME between", value1, value2, "regulationName");
            return (Criteria) this;
        }

        public Criteria andRegulationNameNotBetween(String value1, String value2) {
            addCriterion("REGULATION_NAME not between", value1, value2, "regulationName");
            return (Criteria) this;
        }

        public Criteria andRegulationCodeIsNull() {
            addCriterion("REGULATION_CODE is null");
            return (Criteria) this;
        }

        public Criteria andRegulationCodeIsNotNull() {
            addCriterion("REGULATION_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andRegulationCodeEqualTo(String value) {
            addCriterion("REGULATION_CODE =", value, "regulationCode");
            return (Criteria) this;
        }

        public Criteria andRegulationCodeNotEqualTo(String value) {
            addCriterion("REGULATION_CODE <>", value, "regulationCode");
            return (Criteria) this;
        }

        public Criteria andRegulationCodeGreaterThan(String value) {
            addCriterion("REGULATION_CODE >", value, "regulationCode");
            return (Criteria) this;
        }

        public Criteria andRegulationCodeGreaterThanOrEqualTo(String value) {
            addCriterion("REGULATION_CODE >=", value, "regulationCode");
            return (Criteria) this;
        }

        public Criteria andRegulationCodeLessThan(String value) {
            addCriterion("REGULATION_CODE <", value, "regulationCode");
            return (Criteria) this;
        }

        public Criteria andRegulationCodeLessThanOrEqualTo(String value) {
            addCriterion("REGULATION_CODE <=", value, "regulationCode");
            return (Criteria) this;
        }

        public Criteria andRegulationCodeLike(String value) {
            addCriterion("REGULATION_CODE like", value, "regulationCode");
            return (Criteria) this;
        }

        public Criteria andRegulationCodeNotLike(String value) {
            addCriterion("REGULATION_CODE not like", value, "regulationCode");
            return (Criteria) this;
        }

        public Criteria andRegulationCodeIn(List<String> values) {
            addCriterion("REGULATION_CODE in", values, "regulationCode");
            return (Criteria) this;
        }

        public Criteria andRegulationCodeNotIn(List<String> values) {
            addCriterion("REGULATION_CODE not in", values, "regulationCode");
            return (Criteria) this;
        }

        public Criteria andRegulationCodeBetween(String value1, String value2) {
            addCriterion("REGULATION_CODE between", value1, value2, "regulationCode");
            return (Criteria) this;
        }

        public Criteria andRegulationCodeNotBetween(String value1, String value2) {
            addCriterion("REGULATION_CODE not between", value1, value2, "regulationCode");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryIdIsNull() {
            addCriterion("REGULATION_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryIdIsNotNull() {
            addCriterion("REGULATION_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryIdEqualTo(String value) {
            addCriterion("REGULATION_CATEGORY_ID =", value, "regulationCategoryId");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryIdNotEqualTo(String value) {
            addCriterion("REGULATION_CATEGORY_ID <>", value, "regulationCategoryId");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryIdGreaterThan(String value) {
            addCriterion("REGULATION_CATEGORY_ID >", value, "regulationCategoryId");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("REGULATION_CATEGORY_ID >=", value, "regulationCategoryId");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryIdLessThan(String value) {
            addCriterion("REGULATION_CATEGORY_ID <", value, "regulationCategoryId");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("REGULATION_CATEGORY_ID <=", value, "regulationCategoryId");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryIdLike(String value) {
            addCriterion("REGULATION_CATEGORY_ID like", value, "regulationCategoryId");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryIdNotLike(String value) {
            addCriterion("REGULATION_CATEGORY_ID not like", value, "regulationCategoryId");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryIdIn(List<String> values) {
            addCriterion("REGULATION_CATEGORY_ID in", values, "regulationCategoryId");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryIdNotIn(List<String> values) {
            addCriterion("REGULATION_CATEGORY_ID not in", values, "regulationCategoryId");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryIdBetween(String value1, String value2) {
            addCriterion("REGULATION_CATEGORY_ID between", value1, value2, "regulationCategoryId");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryIdNotBetween(String value1, String value2) {
            addCriterion("REGULATION_CATEGORY_ID not between", value1, value2, "regulationCategoryId");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryNameIsNull() {
            addCriterion("REGULATION_CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryNameIsNotNull() {
            addCriterion("REGULATION_CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryNameEqualTo(String value) {
            addCriterion("REGULATION_CATEGORY_NAME =", value, "regulationCategoryName");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryNameNotEqualTo(String value) {
            addCriterion("REGULATION_CATEGORY_NAME <>", value, "regulationCategoryName");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryNameGreaterThan(String value) {
            addCriterion("REGULATION_CATEGORY_NAME >", value, "regulationCategoryName");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("REGULATION_CATEGORY_NAME >=", value, "regulationCategoryName");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryNameLessThan(String value) {
            addCriterion("REGULATION_CATEGORY_NAME <", value, "regulationCategoryName");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("REGULATION_CATEGORY_NAME <=", value, "regulationCategoryName");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryNameLike(String value) {
            addCriterion("REGULATION_CATEGORY_NAME like", value, "regulationCategoryName");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryNameNotLike(String value) {
            addCriterion("REGULATION_CATEGORY_NAME not like", value, "regulationCategoryName");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryNameIn(List<String> values) {
            addCriterion("REGULATION_CATEGORY_NAME in", values, "regulationCategoryName");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryNameNotIn(List<String> values) {
            addCriterion("REGULATION_CATEGORY_NAME not in", values, "regulationCategoryName");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryNameBetween(String value1, String value2) {
            addCriterion("REGULATION_CATEGORY_NAME between", value1, value2, "regulationCategoryName");
            return (Criteria) this;
        }

        public Criteria andRegulationCategoryNameNotBetween(String value1, String value2) {
            addCriterion("REGULATION_CATEGORY_NAME not between", value1, value2, "regulationCategoryName");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeIdIsNull() {
            addCriterion("REGULATION_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeIdIsNotNull() {
            addCriterion("REGULATION_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeIdEqualTo(String value) {
            addCriterion("REGULATION_TYPE_ID =", value, "regulationTypeId");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeIdNotEqualTo(String value) {
            addCriterion("REGULATION_TYPE_ID <>", value, "regulationTypeId");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeIdGreaterThan(String value) {
            addCriterion("REGULATION_TYPE_ID >", value, "regulationTypeId");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("REGULATION_TYPE_ID >=", value, "regulationTypeId");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeIdLessThan(String value) {
            addCriterion("REGULATION_TYPE_ID <", value, "regulationTypeId");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeIdLessThanOrEqualTo(String value) {
            addCriterion("REGULATION_TYPE_ID <=", value, "regulationTypeId");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeIdLike(String value) {
            addCriterion("REGULATION_TYPE_ID like", value, "regulationTypeId");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeIdNotLike(String value) {
            addCriterion("REGULATION_TYPE_ID not like", value, "regulationTypeId");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeIdIn(List<String> values) {
            addCriterion("REGULATION_TYPE_ID in", values, "regulationTypeId");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeIdNotIn(List<String> values) {
            addCriterion("REGULATION_TYPE_ID not in", values, "regulationTypeId");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeIdBetween(String value1, String value2) {
            addCriterion("REGULATION_TYPE_ID between", value1, value2, "regulationTypeId");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeIdNotBetween(String value1, String value2) {
            addCriterion("REGULATION_TYPE_ID not between", value1, value2, "regulationTypeId");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeNameIsNull() {
            addCriterion("REGULATION_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeNameIsNotNull() {
            addCriterion("REGULATION_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeNameEqualTo(String value) {
            addCriterion("REGULATION_TYPE_NAME =", value, "regulationTypeName");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeNameNotEqualTo(String value) {
            addCriterion("REGULATION_TYPE_NAME <>", value, "regulationTypeName");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeNameGreaterThan(String value) {
            addCriterion("REGULATION_TYPE_NAME >", value, "regulationTypeName");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("REGULATION_TYPE_NAME >=", value, "regulationTypeName");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeNameLessThan(String value) {
            addCriterion("REGULATION_TYPE_NAME <", value, "regulationTypeName");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeNameLessThanOrEqualTo(String value) {
            addCriterion("REGULATION_TYPE_NAME <=", value, "regulationTypeName");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeNameLike(String value) {
            addCriterion("REGULATION_TYPE_NAME like", value, "regulationTypeName");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeNameNotLike(String value) {
            addCriterion("REGULATION_TYPE_NAME not like", value, "regulationTypeName");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeNameIn(List<String> values) {
            addCriterion("REGULATION_TYPE_NAME in", values, "regulationTypeName");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeNameNotIn(List<String> values) {
            addCriterion("REGULATION_TYPE_NAME not in", values, "regulationTypeName");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeNameBetween(String value1, String value2) {
            addCriterion("REGULATION_TYPE_NAME between", value1, value2, "regulationTypeName");
            return (Criteria) this;
        }

        public Criteria andRegulationTypeNameNotBetween(String value1, String value2) {
            addCriterion("REGULATION_TYPE_NAME not between", value1, value2, "regulationTypeName");
            return (Criteria) this;
        }

        public Criteria andFileFlowIsNull() {
            addCriterion("FILE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFileFlowIsNotNull() {
            addCriterion("FILE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFileFlowEqualTo(String value) {
            addCriterion("FILE_FLOW =", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowNotEqualTo(String value) {
            addCriterion("FILE_FLOW <>", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowGreaterThan(String value) {
            addCriterion("FILE_FLOW >", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_FLOW >=", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowLessThan(String value) {
            addCriterion("FILE_FLOW <", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowLessThanOrEqualTo(String value) {
            addCriterion("FILE_FLOW <=", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowLike(String value) {
            addCriterion("FILE_FLOW like", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowNotLike(String value) {
            addCriterion("FILE_FLOW not like", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowIn(List<String> values) {
            addCriterion("FILE_FLOW in", values, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowNotIn(List<String> values) {
            addCriterion("FILE_FLOW not in", values, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowBetween(String value1, String value2) {
            addCriterion("FILE_FLOW between", value1, value2, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowNotBetween(String value1, String value2) {
            addCriterion("FILE_FLOW not between", value1, value2, "fileFlow");
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

        public Criteria andRegulationYearIsNull() {
            addCriterion("REGULATION_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andRegulationYearIsNotNull() {
            addCriterion("REGULATION_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andRegulationYearEqualTo(String value) {
            addCriterion("REGULATION_YEAR =", value, "regulationYear");
            return (Criteria) this;
        }

        public Criteria andRegulationYearNotEqualTo(String value) {
            addCriterion("REGULATION_YEAR <>", value, "regulationYear");
            return (Criteria) this;
        }

        public Criteria andRegulationYearGreaterThan(String value) {
            addCriterion("REGULATION_YEAR >", value, "regulationYear");
            return (Criteria) this;
        }

        public Criteria andRegulationYearGreaterThanOrEqualTo(String value) {
            addCriterion("REGULATION_YEAR >=", value, "regulationYear");
            return (Criteria) this;
        }

        public Criteria andRegulationYearLessThan(String value) {
            addCriterion("REGULATION_YEAR <", value, "regulationYear");
            return (Criteria) this;
        }

        public Criteria andRegulationYearLessThanOrEqualTo(String value) {
            addCriterion("REGULATION_YEAR <=", value, "regulationYear");
            return (Criteria) this;
        }

        public Criteria andRegulationYearLike(String value) {
            addCriterion("REGULATION_YEAR like", value, "regulationYear");
            return (Criteria) this;
        }

        public Criteria andRegulationYearNotLike(String value) {
            addCriterion("REGULATION_YEAR not like", value, "regulationYear");
            return (Criteria) this;
        }

        public Criteria andRegulationYearIn(List<String> values) {
            addCriterion("REGULATION_YEAR in", values, "regulationYear");
            return (Criteria) this;
        }

        public Criteria andRegulationYearNotIn(List<String> values) {
            addCriterion("REGULATION_YEAR not in", values, "regulationYear");
            return (Criteria) this;
        }

        public Criteria andRegulationYearBetween(String value1, String value2) {
            addCriterion("REGULATION_YEAR between", value1, value2, "regulationYear");
            return (Criteria) this;
        }

        public Criteria andRegulationYearNotBetween(String value1, String value2) {
            addCriterion("REGULATION_YEAR not between", value1, value2, "regulationYear");
            return (Criteria) this;
        }

        public Criteria andOrdinalIsNull() {
            addCriterion("ORDINAL is null");
            return (Criteria) this;
        }

        public Criteria andOrdinalIsNotNull() {
            addCriterion("ORDINAL is not null");
            return (Criteria) this;
        }

        public Criteria andOrdinalEqualTo(Integer value) {
            addCriterion("ORDINAL =", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotEqualTo(Integer value) {
            addCriterion("ORDINAL <>", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThan(Integer value) {
            addCriterion("ORDINAL >", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL >=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThan(Integer value) {
            addCriterion("ORDINAL <", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL <=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalIn(List<Integer> values) {
            addCriterion("ORDINAL in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotIn(List<Integer> values) {
            addCriterion("ORDINAL not in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL between", value1, value2, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL not between", value1, value2, "ordinal");
            return (Criteria) this;
        }

        public Criteria andRegulationRemarkIsNull() {
            addCriterion("REGULATION_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRegulationRemarkIsNotNull() {
            addCriterion("REGULATION_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRegulationRemarkEqualTo(String value) {
            addCriterion("REGULATION_REMARK =", value, "regulationRemark");
            return (Criteria) this;
        }

        public Criteria andRegulationRemarkNotEqualTo(String value) {
            addCriterion("REGULATION_REMARK <>", value, "regulationRemark");
            return (Criteria) this;
        }

        public Criteria andRegulationRemarkGreaterThan(String value) {
            addCriterion("REGULATION_REMARK >", value, "regulationRemark");
            return (Criteria) this;
        }

        public Criteria andRegulationRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REGULATION_REMARK >=", value, "regulationRemark");
            return (Criteria) this;
        }

        public Criteria andRegulationRemarkLessThan(String value) {
            addCriterion("REGULATION_REMARK <", value, "regulationRemark");
            return (Criteria) this;
        }

        public Criteria andRegulationRemarkLessThanOrEqualTo(String value) {
            addCriterion("REGULATION_REMARK <=", value, "regulationRemark");
            return (Criteria) this;
        }

        public Criteria andRegulationRemarkLike(String value) {
            addCriterion("REGULATION_REMARK like", value, "regulationRemark");
            return (Criteria) this;
        }

        public Criteria andRegulationRemarkNotLike(String value) {
            addCriterion("REGULATION_REMARK not like", value, "regulationRemark");
            return (Criteria) this;
        }

        public Criteria andRegulationRemarkIn(List<String> values) {
            addCriterion("REGULATION_REMARK in", values, "regulationRemark");
            return (Criteria) this;
        }

        public Criteria andRegulationRemarkNotIn(List<String> values) {
            addCriterion("REGULATION_REMARK not in", values, "regulationRemark");
            return (Criteria) this;
        }

        public Criteria andRegulationRemarkBetween(String value1, String value2) {
            addCriterion("REGULATION_REMARK between", value1, value2, "regulationRemark");
            return (Criteria) this;
        }

        public Criteria andRegulationRemarkNotBetween(String value1, String value2) {
            addCriterion("REGULATION_REMARK not between", value1, value2, "regulationRemark");
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