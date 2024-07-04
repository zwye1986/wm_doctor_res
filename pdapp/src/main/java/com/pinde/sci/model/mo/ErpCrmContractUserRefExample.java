package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ErpCrmContractUserRefExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ErpCrmContractUserRefExample() {
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

        public Criteria andUserRecordFlowIsNull() {
            addCriterion("USER_RECORD_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andUserRecordFlowIsNotNull() {
            addCriterion("USER_RECORD_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andUserRecordFlowEqualTo(String value) {
            addCriterion("USER_RECORD_FLOW =", value, "userRecordFlow");
            return (Criteria) this;
        }

        public Criteria andUserRecordFlowNotEqualTo(String value) {
            addCriterion("USER_RECORD_FLOW <>", value, "userRecordFlow");
            return (Criteria) this;
        }

        public Criteria andUserRecordFlowGreaterThan(String value) {
            addCriterion("USER_RECORD_FLOW >", value, "userRecordFlow");
            return (Criteria) this;
        }

        public Criteria andUserRecordFlowGreaterThanOrEqualTo(String value) {
            addCriterion("USER_RECORD_FLOW >=", value, "userRecordFlow");
            return (Criteria) this;
        }

        public Criteria andUserRecordFlowLessThan(String value) {
            addCriterion("USER_RECORD_FLOW <", value, "userRecordFlow");
            return (Criteria) this;
        }

        public Criteria andUserRecordFlowLessThanOrEqualTo(String value) {
            addCriterion("USER_RECORD_FLOW <=", value, "userRecordFlow");
            return (Criteria) this;
        }

        public Criteria andUserRecordFlowLike(String value) {
            addCriterion("USER_RECORD_FLOW like", value, "userRecordFlow");
            return (Criteria) this;
        }

        public Criteria andUserRecordFlowNotLike(String value) {
            addCriterion("USER_RECORD_FLOW not like", value, "userRecordFlow");
            return (Criteria) this;
        }

        public Criteria andUserRecordFlowIn(List<String> values) {
            addCriterion("USER_RECORD_FLOW in", values, "userRecordFlow");
            return (Criteria) this;
        }

        public Criteria andUserRecordFlowNotIn(List<String> values) {
            addCriterion("USER_RECORD_FLOW not in", values, "userRecordFlow");
            return (Criteria) this;
        }

        public Criteria andUserRecordFlowBetween(String value1, String value2) {
            addCriterion("USER_RECORD_FLOW between", value1, value2, "userRecordFlow");
            return (Criteria) this;
        }

        public Criteria andUserRecordFlowNotBetween(String value1, String value2) {
            addCriterion("USER_RECORD_FLOW not between", value1, value2, "userRecordFlow");
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

        public Criteria andContractFlowIsNull() {
            addCriterion("CONTRACT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andContractFlowIsNotNull() {
            addCriterion("CONTRACT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andContractFlowEqualTo(String value) {
            addCriterion("CONTRACT_FLOW =", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowNotEqualTo(String value) {
            addCriterion("CONTRACT_FLOW <>", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowGreaterThan(String value) {
            addCriterion("CONTRACT_FLOW >", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_FLOW >=", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowLessThan(String value) {
            addCriterion("CONTRACT_FLOW <", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_FLOW <=", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowLike(String value) {
            addCriterion("CONTRACT_FLOW like", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowNotLike(String value) {
            addCriterion("CONTRACT_FLOW not like", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowIn(List<String> values) {
            addCriterion("CONTRACT_FLOW in", values, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowNotIn(List<String> values) {
            addCriterion("CONTRACT_FLOW not in", values, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowBetween(String value1, String value2) {
            addCriterion("CONTRACT_FLOW between", value1, value2, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_FLOW not between", value1, value2, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andProductFlowIsNull() {
            addCriterion("PRODUCT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andProductFlowIsNotNull() {
            addCriterion("PRODUCT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andProductFlowEqualTo(String value) {
            addCriterion("PRODUCT_FLOW =", value, "productFlow");
            return (Criteria) this;
        }

        public Criteria andProductFlowNotEqualTo(String value) {
            addCriterion("PRODUCT_FLOW <>", value, "productFlow");
            return (Criteria) this;
        }

        public Criteria andProductFlowGreaterThan(String value) {
            addCriterion("PRODUCT_FLOW >", value, "productFlow");
            return (Criteria) this;
        }

        public Criteria andProductFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_FLOW >=", value, "productFlow");
            return (Criteria) this;
        }

        public Criteria andProductFlowLessThan(String value) {
            addCriterion("PRODUCT_FLOW <", value, "productFlow");
            return (Criteria) this;
        }

        public Criteria andProductFlowLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_FLOW <=", value, "productFlow");
            return (Criteria) this;
        }

        public Criteria andProductFlowLike(String value) {
            addCriterion("PRODUCT_FLOW like", value, "productFlow");
            return (Criteria) this;
        }

        public Criteria andProductFlowNotLike(String value) {
            addCriterion("PRODUCT_FLOW not like", value, "productFlow");
            return (Criteria) this;
        }

        public Criteria andProductFlowIn(List<String> values) {
            addCriterion("PRODUCT_FLOW in", values, "productFlow");
            return (Criteria) this;
        }

        public Criteria andProductFlowNotIn(List<String> values) {
            addCriterion("PRODUCT_FLOW not in", values, "productFlow");
            return (Criteria) this;
        }

        public Criteria andProductFlowBetween(String value1, String value2) {
            addCriterion("PRODUCT_FLOW between", value1, value2, "productFlow");
            return (Criteria) this;
        }

        public Criteria andProductFlowNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_FLOW not between", value1, value2, "productFlow");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdIsNull() {
            addCriterion("PRODUCT_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdIsNotNull() {
            addCriterion("PRODUCT_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdEqualTo(String value) {
            addCriterion("PRODUCT_TYPE_ID =", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdNotEqualTo(String value) {
            addCriterion("PRODUCT_TYPE_ID <>", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdGreaterThan(String value) {
            addCriterion("PRODUCT_TYPE_ID >", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_TYPE_ID >=", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdLessThan(String value) {
            addCriterion("PRODUCT_TYPE_ID <", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_TYPE_ID <=", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdLike(String value) {
            addCriterion("PRODUCT_TYPE_ID like", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdNotLike(String value) {
            addCriterion("PRODUCT_TYPE_ID not like", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdIn(List<String> values) {
            addCriterion("PRODUCT_TYPE_ID in", values, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdNotIn(List<String> values) {
            addCriterion("PRODUCT_TYPE_ID not in", values, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdBetween(String value1, String value2) {
            addCriterion("PRODUCT_TYPE_ID between", value1, value2, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_TYPE_ID not between", value1, value2, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeNameIsNull() {
            addCriterion("PRODUCT_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeNameIsNotNull() {
            addCriterion("PRODUCT_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeNameEqualTo(String value) {
            addCriterion("PRODUCT_TYPE_NAME =", value, "productTypeName");
            return (Criteria) this;
        }

        public Criteria andProductTypeNameNotEqualTo(String value) {
            addCriterion("PRODUCT_TYPE_NAME <>", value, "productTypeName");
            return (Criteria) this;
        }

        public Criteria andProductTypeNameGreaterThan(String value) {
            addCriterion("PRODUCT_TYPE_NAME >", value, "productTypeName");
            return (Criteria) this;
        }

        public Criteria andProductTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_TYPE_NAME >=", value, "productTypeName");
            return (Criteria) this;
        }

        public Criteria andProductTypeNameLessThan(String value) {
            addCriterion("PRODUCT_TYPE_NAME <", value, "productTypeName");
            return (Criteria) this;
        }

        public Criteria andProductTypeNameLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_TYPE_NAME <=", value, "productTypeName");
            return (Criteria) this;
        }

        public Criteria andProductTypeNameLike(String value) {
            addCriterion("PRODUCT_TYPE_NAME like", value, "productTypeName");
            return (Criteria) this;
        }

        public Criteria andProductTypeNameNotLike(String value) {
            addCriterion("PRODUCT_TYPE_NAME not like", value, "productTypeName");
            return (Criteria) this;
        }

        public Criteria andProductTypeNameIn(List<String> values) {
            addCriterion("PRODUCT_TYPE_NAME in", values, "productTypeName");
            return (Criteria) this;
        }

        public Criteria andProductTypeNameNotIn(List<String> values) {
            addCriterion("PRODUCT_TYPE_NAME not in", values, "productTypeName");
            return (Criteria) this;
        }

        public Criteria andProductTypeNameBetween(String value1, String value2) {
            addCriterion("PRODUCT_TYPE_NAME between", value1, value2, "productTypeName");
            return (Criteria) this;
        }

        public Criteria andProductTypeNameNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_TYPE_NAME not between", value1, value2, "productTypeName");
            return (Criteria) this;
        }

        public Criteria andUserCategoryIdIsNull() {
            addCriterion("USER_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserCategoryIdIsNotNull() {
            addCriterion("USER_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserCategoryIdEqualTo(String value) {
            addCriterion("USER_CATEGORY_ID =", value, "userCategoryId");
            return (Criteria) this;
        }

        public Criteria andUserCategoryIdNotEqualTo(String value) {
            addCriterion("USER_CATEGORY_ID <>", value, "userCategoryId");
            return (Criteria) this;
        }

        public Criteria andUserCategoryIdGreaterThan(String value) {
            addCriterion("USER_CATEGORY_ID >", value, "userCategoryId");
            return (Criteria) this;
        }

        public Criteria andUserCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_CATEGORY_ID >=", value, "userCategoryId");
            return (Criteria) this;
        }

        public Criteria andUserCategoryIdLessThan(String value) {
            addCriterion("USER_CATEGORY_ID <", value, "userCategoryId");
            return (Criteria) this;
        }

        public Criteria andUserCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("USER_CATEGORY_ID <=", value, "userCategoryId");
            return (Criteria) this;
        }

        public Criteria andUserCategoryIdLike(String value) {
            addCriterion("USER_CATEGORY_ID like", value, "userCategoryId");
            return (Criteria) this;
        }

        public Criteria andUserCategoryIdNotLike(String value) {
            addCriterion("USER_CATEGORY_ID not like", value, "userCategoryId");
            return (Criteria) this;
        }

        public Criteria andUserCategoryIdIn(List<String> values) {
            addCriterion("USER_CATEGORY_ID in", values, "userCategoryId");
            return (Criteria) this;
        }

        public Criteria andUserCategoryIdNotIn(List<String> values) {
            addCriterion("USER_CATEGORY_ID not in", values, "userCategoryId");
            return (Criteria) this;
        }

        public Criteria andUserCategoryIdBetween(String value1, String value2) {
            addCriterion("USER_CATEGORY_ID between", value1, value2, "userCategoryId");
            return (Criteria) this;
        }

        public Criteria andUserCategoryIdNotBetween(String value1, String value2) {
            addCriterion("USER_CATEGORY_ID not between", value1, value2, "userCategoryId");
            return (Criteria) this;
        }

        public Criteria andUserCategoryNameIsNull() {
            addCriterion("USER_CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserCategoryNameIsNotNull() {
            addCriterion("USER_CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserCategoryNameEqualTo(String value) {
            addCriterion("USER_CATEGORY_NAME =", value, "userCategoryName");
            return (Criteria) this;
        }

        public Criteria andUserCategoryNameNotEqualTo(String value) {
            addCriterion("USER_CATEGORY_NAME <>", value, "userCategoryName");
            return (Criteria) this;
        }

        public Criteria andUserCategoryNameGreaterThan(String value) {
            addCriterion("USER_CATEGORY_NAME >", value, "userCategoryName");
            return (Criteria) this;
        }

        public Criteria andUserCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_CATEGORY_NAME >=", value, "userCategoryName");
            return (Criteria) this;
        }

        public Criteria andUserCategoryNameLessThan(String value) {
            addCriterion("USER_CATEGORY_NAME <", value, "userCategoryName");
            return (Criteria) this;
        }

        public Criteria andUserCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("USER_CATEGORY_NAME <=", value, "userCategoryName");
            return (Criteria) this;
        }

        public Criteria andUserCategoryNameLike(String value) {
            addCriterion("USER_CATEGORY_NAME like", value, "userCategoryName");
            return (Criteria) this;
        }

        public Criteria andUserCategoryNameNotLike(String value) {
            addCriterion("USER_CATEGORY_NAME not like", value, "userCategoryName");
            return (Criteria) this;
        }

        public Criteria andUserCategoryNameIn(List<String> values) {
            addCriterion("USER_CATEGORY_NAME in", values, "userCategoryName");
            return (Criteria) this;
        }

        public Criteria andUserCategoryNameNotIn(List<String> values) {
            addCriterion("USER_CATEGORY_NAME not in", values, "userCategoryName");
            return (Criteria) this;
        }

        public Criteria andUserCategoryNameBetween(String value1, String value2) {
            addCriterion("USER_CATEGORY_NAME between", value1, value2, "userCategoryName");
            return (Criteria) this;
        }

        public Criteria andUserCategoryNameNotBetween(String value1, String value2) {
            addCriterion("USER_CATEGORY_NAME not between", value1, value2, "userCategoryName");
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