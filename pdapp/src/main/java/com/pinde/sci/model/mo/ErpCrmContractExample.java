package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ErpCrmContractExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ErpCrmContractExample() {
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

        public Criteria andCustomerFlowIsNull() {
            addCriterion("CUSTOMER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowIsNotNull() {
            addCriterion("CUSTOMER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowEqualTo(String value) {
            addCriterion("CUSTOMER_FLOW =", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowNotEqualTo(String value) {
            addCriterion("CUSTOMER_FLOW <>", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowGreaterThan(String value) {
            addCriterion("CUSTOMER_FLOW >", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_FLOW >=", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowLessThan(String value) {
            addCriterion("CUSTOMER_FLOW <", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_FLOW <=", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowLike(String value) {
            addCriterion("CUSTOMER_FLOW like", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowNotLike(String value) {
            addCriterion("CUSTOMER_FLOW not like", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowIn(List<String> values) {
            addCriterion("CUSTOMER_FLOW in", values, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowNotIn(List<String> values) {
            addCriterion("CUSTOMER_FLOW not in", values, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowBetween(String value1, String value2) {
            addCriterion("CUSTOMER_FLOW between", value1, value2, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_FLOW not between", value1, value2, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andContractNoIsNull() {
            addCriterion("CONTRACT_NO is null");
            return (Criteria) this;
        }

        public Criteria andContractNoIsNotNull() {
            addCriterion("CONTRACT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andContractNoEqualTo(String value) {
            addCriterion("CONTRACT_NO =", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotEqualTo(String value) {
            addCriterion("CONTRACT_NO <>", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoGreaterThan(String value) {
            addCriterion("CONTRACT_NO >", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_NO >=", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoLessThan(String value) {
            addCriterion("CONTRACT_NO <", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_NO <=", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoLike(String value) {
            addCriterion("CONTRACT_NO like", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotLike(String value) {
            addCriterion("CONTRACT_NO not like", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoIn(List<String> values) {
            addCriterion("CONTRACT_NO in", values, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotIn(List<String> values) {
            addCriterion("CONTRACT_NO not in", values, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoBetween(String value1, String value2) {
            addCriterion("CONTRACT_NO between", value1, value2, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_NO not between", value1, value2, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNameIsNull() {
            addCriterion("CONTRACT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andContractNameIsNotNull() {
            addCriterion("CONTRACT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andContractNameEqualTo(String value) {
            addCriterion("CONTRACT_NAME =", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotEqualTo(String value) {
            addCriterion("CONTRACT_NAME <>", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameGreaterThan(String value) {
            addCriterion("CONTRACT_NAME >", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_NAME >=", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameLessThan(String value) {
            addCriterion("CONTRACT_NAME <", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_NAME <=", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameLike(String value) {
            addCriterion("CONTRACT_NAME like", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotLike(String value) {
            addCriterion("CONTRACT_NAME not like", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameIn(List<String> values) {
            addCriterion("CONTRACT_NAME in", values, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotIn(List<String> values) {
            addCriterion("CONTRACT_NAME not in", values, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameBetween(String value1, String value2) {
            addCriterion("CONTRACT_NAME between", value1, value2, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_NAME not between", value1, value2, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractCategoryIdIsNull() {
            addCriterion("CONTRACT_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andContractCategoryIdIsNotNull() {
            addCriterion("CONTRACT_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andContractCategoryIdEqualTo(String value) {
            addCriterion("CONTRACT_CATEGORY_ID =", value, "contractCategoryId");
            return (Criteria) this;
        }

        public Criteria andContractCategoryIdNotEqualTo(String value) {
            addCriterion("CONTRACT_CATEGORY_ID <>", value, "contractCategoryId");
            return (Criteria) this;
        }

        public Criteria andContractCategoryIdGreaterThan(String value) {
            addCriterion("CONTRACT_CATEGORY_ID >", value, "contractCategoryId");
            return (Criteria) this;
        }

        public Criteria andContractCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_CATEGORY_ID >=", value, "contractCategoryId");
            return (Criteria) this;
        }

        public Criteria andContractCategoryIdLessThan(String value) {
            addCriterion("CONTRACT_CATEGORY_ID <", value, "contractCategoryId");
            return (Criteria) this;
        }

        public Criteria andContractCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_CATEGORY_ID <=", value, "contractCategoryId");
            return (Criteria) this;
        }

        public Criteria andContractCategoryIdLike(String value) {
            addCriterion("CONTRACT_CATEGORY_ID like", value, "contractCategoryId");
            return (Criteria) this;
        }

        public Criteria andContractCategoryIdNotLike(String value) {
            addCriterion("CONTRACT_CATEGORY_ID not like", value, "contractCategoryId");
            return (Criteria) this;
        }

        public Criteria andContractCategoryIdIn(List<String> values) {
            addCriterion("CONTRACT_CATEGORY_ID in", values, "contractCategoryId");
            return (Criteria) this;
        }

        public Criteria andContractCategoryIdNotIn(List<String> values) {
            addCriterion("CONTRACT_CATEGORY_ID not in", values, "contractCategoryId");
            return (Criteria) this;
        }

        public Criteria andContractCategoryIdBetween(String value1, String value2) {
            addCriterion("CONTRACT_CATEGORY_ID between", value1, value2, "contractCategoryId");
            return (Criteria) this;
        }

        public Criteria andContractCategoryIdNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_CATEGORY_ID not between", value1, value2, "contractCategoryId");
            return (Criteria) this;
        }

        public Criteria andContractCategoryNameIsNull() {
            addCriterion("CONTRACT_CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andContractCategoryNameIsNotNull() {
            addCriterion("CONTRACT_CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andContractCategoryNameEqualTo(String value) {
            addCriterion("CONTRACT_CATEGORY_NAME =", value, "contractCategoryName");
            return (Criteria) this;
        }

        public Criteria andContractCategoryNameNotEqualTo(String value) {
            addCriterion("CONTRACT_CATEGORY_NAME <>", value, "contractCategoryName");
            return (Criteria) this;
        }

        public Criteria andContractCategoryNameGreaterThan(String value) {
            addCriterion("CONTRACT_CATEGORY_NAME >", value, "contractCategoryName");
            return (Criteria) this;
        }

        public Criteria andContractCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_CATEGORY_NAME >=", value, "contractCategoryName");
            return (Criteria) this;
        }

        public Criteria andContractCategoryNameLessThan(String value) {
            addCriterion("CONTRACT_CATEGORY_NAME <", value, "contractCategoryName");
            return (Criteria) this;
        }

        public Criteria andContractCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_CATEGORY_NAME <=", value, "contractCategoryName");
            return (Criteria) this;
        }

        public Criteria andContractCategoryNameLike(String value) {
            addCriterion("CONTRACT_CATEGORY_NAME like", value, "contractCategoryName");
            return (Criteria) this;
        }

        public Criteria andContractCategoryNameNotLike(String value) {
            addCriterion("CONTRACT_CATEGORY_NAME not like", value, "contractCategoryName");
            return (Criteria) this;
        }

        public Criteria andContractCategoryNameIn(List<String> values) {
            addCriterion("CONTRACT_CATEGORY_NAME in", values, "contractCategoryName");
            return (Criteria) this;
        }

        public Criteria andContractCategoryNameNotIn(List<String> values) {
            addCriterion("CONTRACT_CATEGORY_NAME not in", values, "contractCategoryName");
            return (Criteria) this;
        }

        public Criteria andContractCategoryNameBetween(String value1, String value2) {
            addCriterion("CONTRACT_CATEGORY_NAME between", value1, value2, "contractCategoryName");
            return (Criteria) this;
        }

        public Criteria andContractCategoryNameNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_CATEGORY_NAME not between", value1, value2, "contractCategoryName");
            return (Criteria) this;
        }

        public Criteria andContractTypeIdIsNull() {
            addCriterion("CONTRACT_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andContractTypeIdIsNotNull() {
            addCriterion("CONTRACT_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andContractTypeIdEqualTo(String value) {
            addCriterion("CONTRACT_TYPE_ID =", value, "contractTypeId");
            return (Criteria) this;
        }

        public Criteria andContractTypeIdNotEqualTo(String value) {
            addCriterion("CONTRACT_TYPE_ID <>", value, "contractTypeId");
            return (Criteria) this;
        }

        public Criteria andContractTypeIdGreaterThan(String value) {
            addCriterion("CONTRACT_TYPE_ID >", value, "contractTypeId");
            return (Criteria) this;
        }

        public Criteria andContractTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_TYPE_ID >=", value, "contractTypeId");
            return (Criteria) this;
        }

        public Criteria andContractTypeIdLessThan(String value) {
            addCriterion("CONTRACT_TYPE_ID <", value, "contractTypeId");
            return (Criteria) this;
        }

        public Criteria andContractTypeIdLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_TYPE_ID <=", value, "contractTypeId");
            return (Criteria) this;
        }

        public Criteria andContractTypeIdLike(String value) {
            addCriterion("CONTRACT_TYPE_ID like", value, "contractTypeId");
            return (Criteria) this;
        }

        public Criteria andContractTypeIdNotLike(String value) {
            addCriterion("CONTRACT_TYPE_ID not like", value, "contractTypeId");
            return (Criteria) this;
        }

        public Criteria andContractTypeIdIn(List<String> values) {
            addCriterion("CONTRACT_TYPE_ID in", values, "contractTypeId");
            return (Criteria) this;
        }

        public Criteria andContractTypeIdNotIn(List<String> values) {
            addCriterion("CONTRACT_TYPE_ID not in", values, "contractTypeId");
            return (Criteria) this;
        }

        public Criteria andContractTypeIdBetween(String value1, String value2) {
            addCriterion("CONTRACT_TYPE_ID between", value1, value2, "contractTypeId");
            return (Criteria) this;
        }

        public Criteria andContractTypeIdNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_TYPE_ID not between", value1, value2, "contractTypeId");
            return (Criteria) this;
        }

        public Criteria andContractTypeNameIsNull() {
            addCriterion("CONTRACT_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andContractTypeNameIsNotNull() {
            addCriterion("CONTRACT_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andContractTypeNameEqualTo(String value) {
            addCriterion("CONTRACT_TYPE_NAME =", value, "contractTypeName");
            return (Criteria) this;
        }

        public Criteria andContractTypeNameNotEqualTo(String value) {
            addCriterion("CONTRACT_TYPE_NAME <>", value, "contractTypeName");
            return (Criteria) this;
        }

        public Criteria andContractTypeNameGreaterThan(String value) {
            addCriterion("CONTRACT_TYPE_NAME >", value, "contractTypeName");
            return (Criteria) this;
        }

        public Criteria andContractTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_TYPE_NAME >=", value, "contractTypeName");
            return (Criteria) this;
        }

        public Criteria andContractTypeNameLessThan(String value) {
            addCriterion("CONTRACT_TYPE_NAME <", value, "contractTypeName");
            return (Criteria) this;
        }

        public Criteria andContractTypeNameLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_TYPE_NAME <=", value, "contractTypeName");
            return (Criteria) this;
        }

        public Criteria andContractTypeNameLike(String value) {
            addCriterion("CONTRACT_TYPE_NAME like", value, "contractTypeName");
            return (Criteria) this;
        }

        public Criteria andContractTypeNameNotLike(String value) {
            addCriterion("CONTRACT_TYPE_NAME not like", value, "contractTypeName");
            return (Criteria) this;
        }

        public Criteria andContractTypeNameIn(List<String> values) {
            addCriterion("CONTRACT_TYPE_NAME in", values, "contractTypeName");
            return (Criteria) this;
        }

        public Criteria andContractTypeNameNotIn(List<String> values) {
            addCriterion("CONTRACT_TYPE_NAME not in", values, "contractTypeName");
            return (Criteria) this;
        }

        public Criteria andContractTypeNameBetween(String value1, String value2) {
            addCriterion("CONTRACT_TYPE_NAME between", value1, value2, "contractTypeName");
            return (Criteria) this;
        }

        public Criteria andContractTypeNameNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_TYPE_NAME not between", value1, value2, "contractTypeName");
            return (Criteria) this;
        }

        public Criteria andSignDateIsNull() {
            addCriterion("SIGN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSignDateIsNotNull() {
            addCriterion("SIGN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSignDateEqualTo(String value) {
            addCriterion("SIGN_DATE =", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateNotEqualTo(String value) {
            addCriterion("SIGN_DATE <>", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateGreaterThan(String value) {
            addCriterion("SIGN_DATE >", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateGreaterThanOrEqualTo(String value) {
            addCriterion("SIGN_DATE >=", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateLessThan(String value) {
            addCriterion("SIGN_DATE <", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateLessThanOrEqualTo(String value) {
            addCriterion("SIGN_DATE <=", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateLike(String value) {
            addCriterion("SIGN_DATE like", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateNotLike(String value) {
            addCriterion("SIGN_DATE not like", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateIn(List<String> values) {
            addCriterion("SIGN_DATE in", values, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateNotIn(List<String> values) {
            addCriterion("SIGN_DATE not in", values, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateBetween(String value1, String value2) {
            addCriterion("SIGN_DATE between", value1, value2, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateNotBetween(String value1, String value2) {
            addCriterion("SIGN_DATE not between", value1, value2, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowIsNull() {
            addCriterion("SIGN_DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowIsNotNull() {
            addCriterion("SIGN_DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowEqualTo(String value) {
            addCriterion("SIGN_DEPT_FLOW =", value, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowNotEqualTo(String value) {
            addCriterion("SIGN_DEPT_FLOW <>", value, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowGreaterThan(String value) {
            addCriterion("SIGN_DEPT_FLOW >", value, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SIGN_DEPT_FLOW >=", value, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowLessThan(String value) {
            addCriterion("SIGN_DEPT_FLOW <", value, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("SIGN_DEPT_FLOW <=", value, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowLike(String value) {
            addCriterion("SIGN_DEPT_FLOW like", value, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowNotLike(String value) {
            addCriterion("SIGN_DEPT_FLOW not like", value, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowIn(List<String> values) {
            addCriterion("SIGN_DEPT_FLOW in", values, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowNotIn(List<String> values) {
            addCriterion("SIGN_DEPT_FLOW not in", values, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowBetween(String value1, String value2) {
            addCriterion("SIGN_DEPT_FLOW between", value1, value2, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowNotBetween(String value1, String value2) {
            addCriterion("SIGN_DEPT_FLOW not between", value1, value2, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameIsNull() {
            addCriterion("SIGN_DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameIsNotNull() {
            addCriterion("SIGN_DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameEqualTo(String value) {
            addCriterion("SIGN_DEPT_NAME =", value, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameNotEqualTo(String value) {
            addCriterion("SIGN_DEPT_NAME <>", value, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameGreaterThan(String value) {
            addCriterion("SIGN_DEPT_NAME >", value, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("SIGN_DEPT_NAME >=", value, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameLessThan(String value) {
            addCriterion("SIGN_DEPT_NAME <", value, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameLessThanOrEqualTo(String value) {
            addCriterion("SIGN_DEPT_NAME <=", value, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameLike(String value) {
            addCriterion("SIGN_DEPT_NAME like", value, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameNotLike(String value) {
            addCriterion("SIGN_DEPT_NAME not like", value, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameIn(List<String> values) {
            addCriterion("SIGN_DEPT_NAME in", values, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameNotIn(List<String> values) {
            addCriterion("SIGN_DEPT_NAME not in", values, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameBetween(String value1, String value2) {
            addCriterion("SIGN_DEPT_NAME between", value1, value2, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameNotBetween(String value1, String value2) {
            addCriterion("SIGN_DEPT_NAME not between", value1, value2, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowIsNull() {
            addCriterion("SIGN_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowIsNotNull() {
            addCriterion("SIGN_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowEqualTo(String value) {
            addCriterion("SIGN_USER_FLOW =", value, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowNotEqualTo(String value) {
            addCriterion("SIGN_USER_FLOW <>", value, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowGreaterThan(String value) {
            addCriterion("SIGN_USER_FLOW >", value, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SIGN_USER_FLOW >=", value, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowLessThan(String value) {
            addCriterion("SIGN_USER_FLOW <", value, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowLessThanOrEqualTo(String value) {
            addCriterion("SIGN_USER_FLOW <=", value, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowLike(String value) {
            addCriterion("SIGN_USER_FLOW like", value, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowNotLike(String value) {
            addCriterion("SIGN_USER_FLOW not like", value, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowIn(List<String> values) {
            addCriterion("SIGN_USER_FLOW in", values, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowNotIn(List<String> values) {
            addCriterion("SIGN_USER_FLOW not in", values, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowBetween(String value1, String value2) {
            addCriterion("SIGN_USER_FLOW between", value1, value2, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowNotBetween(String value1, String value2) {
            addCriterion("SIGN_USER_FLOW not between", value1, value2, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserNameIsNull() {
            addCriterion("SIGN_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSignUserNameIsNotNull() {
            addCriterion("SIGN_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSignUserNameEqualTo(String value) {
            addCriterion("SIGN_USER_NAME =", value, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameNotEqualTo(String value) {
            addCriterion("SIGN_USER_NAME <>", value, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameGreaterThan(String value) {
            addCriterion("SIGN_USER_NAME >", value, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("SIGN_USER_NAME >=", value, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameLessThan(String value) {
            addCriterion("SIGN_USER_NAME <", value, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameLessThanOrEqualTo(String value) {
            addCriterion("SIGN_USER_NAME <=", value, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameLike(String value) {
            addCriterion("SIGN_USER_NAME like", value, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameNotLike(String value) {
            addCriterion("SIGN_USER_NAME not like", value, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameIn(List<String> values) {
            addCriterion("SIGN_USER_NAME in", values, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameNotIn(List<String> values) {
            addCriterion("SIGN_USER_NAME not in", values, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameBetween(String value1, String value2) {
            addCriterion("SIGN_USER_NAME between", value1, value2, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameNotBetween(String value1, String value2) {
            addCriterion("SIGN_USER_NAME not between", value1, value2, "signUserName");
            return (Criteria) this;
        }

        public Criteria andContractDueDateIsNull() {
            addCriterion("CONTRACT_DUE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andContractDueDateIsNotNull() {
            addCriterion("CONTRACT_DUE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andContractDueDateEqualTo(String value) {
            addCriterion("CONTRACT_DUE_DATE =", value, "contractDueDate");
            return (Criteria) this;
        }

        public Criteria andContractDueDateNotEqualTo(String value) {
            addCriterion("CONTRACT_DUE_DATE <>", value, "contractDueDate");
            return (Criteria) this;
        }

        public Criteria andContractDueDateGreaterThan(String value) {
            addCriterion("CONTRACT_DUE_DATE >", value, "contractDueDate");
            return (Criteria) this;
        }

        public Criteria andContractDueDateGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_DUE_DATE >=", value, "contractDueDate");
            return (Criteria) this;
        }

        public Criteria andContractDueDateLessThan(String value) {
            addCriterion("CONTRACT_DUE_DATE <", value, "contractDueDate");
            return (Criteria) this;
        }

        public Criteria andContractDueDateLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_DUE_DATE <=", value, "contractDueDate");
            return (Criteria) this;
        }

        public Criteria andContractDueDateLike(String value) {
            addCriterion("CONTRACT_DUE_DATE like", value, "contractDueDate");
            return (Criteria) this;
        }

        public Criteria andContractDueDateNotLike(String value) {
            addCriterion("CONTRACT_DUE_DATE not like", value, "contractDueDate");
            return (Criteria) this;
        }

        public Criteria andContractDueDateIn(List<String> values) {
            addCriterion("CONTRACT_DUE_DATE in", values, "contractDueDate");
            return (Criteria) this;
        }

        public Criteria andContractDueDateNotIn(List<String> values) {
            addCriterion("CONTRACT_DUE_DATE not in", values, "contractDueDate");
            return (Criteria) this;
        }

        public Criteria andContractDueDateBetween(String value1, String value2) {
            addCriterion("CONTRACT_DUE_DATE between", value1, value2, "contractDueDate");
            return (Criteria) this;
        }

        public Criteria andContractDueDateNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_DUE_DATE not between", value1, value2, "contractDueDate");
            return (Criteria) this;
        }

        public Criteria andMaintainDueDateIsNull() {
            addCriterion("MAINTAIN_DUE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andMaintainDueDateIsNotNull() {
            addCriterion("MAINTAIN_DUE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andMaintainDueDateEqualTo(String value) {
            addCriterion("MAINTAIN_DUE_DATE =", value, "maintainDueDate");
            return (Criteria) this;
        }

        public Criteria andMaintainDueDateNotEqualTo(String value) {
            addCriterion("MAINTAIN_DUE_DATE <>", value, "maintainDueDate");
            return (Criteria) this;
        }

        public Criteria andMaintainDueDateGreaterThan(String value) {
            addCriterion("MAINTAIN_DUE_DATE >", value, "maintainDueDate");
            return (Criteria) this;
        }

        public Criteria andMaintainDueDateGreaterThanOrEqualTo(String value) {
            addCriterion("MAINTAIN_DUE_DATE >=", value, "maintainDueDate");
            return (Criteria) this;
        }

        public Criteria andMaintainDueDateLessThan(String value) {
            addCriterion("MAINTAIN_DUE_DATE <", value, "maintainDueDate");
            return (Criteria) this;
        }

        public Criteria andMaintainDueDateLessThanOrEqualTo(String value) {
            addCriterion("MAINTAIN_DUE_DATE <=", value, "maintainDueDate");
            return (Criteria) this;
        }

        public Criteria andMaintainDueDateLike(String value) {
            addCriterion("MAINTAIN_DUE_DATE like", value, "maintainDueDate");
            return (Criteria) this;
        }

        public Criteria andMaintainDueDateNotLike(String value) {
            addCriterion("MAINTAIN_DUE_DATE not like", value, "maintainDueDate");
            return (Criteria) this;
        }

        public Criteria andMaintainDueDateIn(List<String> values) {
            addCriterion("MAINTAIN_DUE_DATE in", values, "maintainDueDate");
            return (Criteria) this;
        }

        public Criteria andMaintainDueDateNotIn(List<String> values) {
            addCriterion("MAINTAIN_DUE_DATE not in", values, "maintainDueDate");
            return (Criteria) this;
        }

        public Criteria andMaintainDueDateBetween(String value1, String value2) {
            addCriterion("MAINTAIN_DUE_DATE between", value1, value2, "maintainDueDate");
            return (Criteria) this;
        }

        public Criteria andMaintainDueDateNotBetween(String value1, String value2) {
            addCriterion("MAINTAIN_DUE_DATE not between", value1, value2, "maintainDueDate");
            return (Criteria) this;
        }

        public Criteria andContractFundIsNull() {
            addCriterion("CONTRACT_FUND is null");
            return (Criteria) this;
        }

        public Criteria andContractFundIsNotNull() {
            addCriterion("CONTRACT_FUND is not null");
            return (Criteria) this;
        }

        public Criteria andContractFundEqualTo(BigDecimal value) {
            addCriterion("CONTRACT_FUND =", value, "contractFund");
            return (Criteria) this;
        }

        public Criteria andContractFundNotEqualTo(BigDecimal value) {
            addCriterion("CONTRACT_FUND <>", value, "contractFund");
            return (Criteria) this;
        }

        public Criteria andContractFundGreaterThan(BigDecimal value) {
            addCriterion("CONTRACT_FUND >", value, "contractFund");
            return (Criteria) this;
        }

        public Criteria andContractFundGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CONTRACT_FUND >=", value, "contractFund");
            return (Criteria) this;
        }

        public Criteria andContractFundLessThan(BigDecimal value) {
            addCriterion("CONTRACT_FUND <", value, "contractFund");
            return (Criteria) this;
        }

        public Criteria andContractFundLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CONTRACT_FUND <=", value, "contractFund");
            return (Criteria) this;
        }

        public Criteria andContractFundIn(List<BigDecimal> values) {
            addCriterion("CONTRACT_FUND in", values, "contractFund");
            return (Criteria) this;
        }

        public Criteria andContractFundNotIn(List<BigDecimal> values) {
            addCriterion("CONTRACT_FUND not in", values, "contractFund");
            return (Criteria) this;
        }

        public Criteria andContractFundBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CONTRACT_FUND between", value1, value2, "contractFund");
            return (Criteria) this;
        }

        public Criteria andContractFundNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CONTRACT_FUND not between", value1, value2, "contractFund");
            return (Criteria) this;
        }

        public Criteria andContractFileFlowIsNull() {
            addCriterion("CONTRACT_FILE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andContractFileFlowIsNotNull() {
            addCriterion("CONTRACT_FILE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andContractFileFlowEqualTo(String value) {
            addCriterion("CONTRACT_FILE_FLOW =", value, "contractFileFlow");
            return (Criteria) this;
        }

        public Criteria andContractFileFlowNotEqualTo(String value) {
            addCriterion("CONTRACT_FILE_FLOW <>", value, "contractFileFlow");
            return (Criteria) this;
        }

        public Criteria andContractFileFlowGreaterThan(String value) {
            addCriterion("CONTRACT_FILE_FLOW >", value, "contractFileFlow");
            return (Criteria) this;
        }

        public Criteria andContractFileFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_FILE_FLOW >=", value, "contractFileFlow");
            return (Criteria) this;
        }

        public Criteria andContractFileFlowLessThan(String value) {
            addCriterion("CONTRACT_FILE_FLOW <", value, "contractFileFlow");
            return (Criteria) this;
        }

        public Criteria andContractFileFlowLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_FILE_FLOW <=", value, "contractFileFlow");
            return (Criteria) this;
        }

        public Criteria andContractFileFlowLike(String value) {
            addCriterion("CONTRACT_FILE_FLOW like", value, "contractFileFlow");
            return (Criteria) this;
        }

        public Criteria andContractFileFlowNotLike(String value) {
            addCriterion("CONTRACT_FILE_FLOW not like", value, "contractFileFlow");
            return (Criteria) this;
        }

        public Criteria andContractFileFlowIn(List<String> values) {
            addCriterion("CONTRACT_FILE_FLOW in", values, "contractFileFlow");
            return (Criteria) this;
        }

        public Criteria andContractFileFlowNotIn(List<String> values) {
            addCriterion("CONTRACT_FILE_FLOW not in", values, "contractFileFlow");
            return (Criteria) this;
        }

        public Criteria andContractFileFlowBetween(String value1, String value2) {
            addCriterion("CONTRACT_FILE_FLOW between", value1, value2, "contractFileFlow");
            return (Criteria) this;
        }

        public Criteria andContractFileFlowNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_FILE_FLOW not between", value1, value2, "contractFileFlow");
            return (Criteria) this;
        }

        public Criteria andContractStatusIdIsNull() {
            addCriterion("CONTRACT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andContractStatusIdIsNotNull() {
            addCriterion("CONTRACT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andContractStatusIdEqualTo(String value) {
            addCriterion("CONTRACT_STATUS_ID =", value, "contractStatusId");
            return (Criteria) this;
        }

        public Criteria andContractStatusIdNotEqualTo(String value) {
            addCriterion("CONTRACT_STATUS_ID <>", value, "contractStatusId");
            return (Criteria) this;
        }

        public Criteria andContractStatusIdGreaterThan(String value) {
            addCriterion("CONTRACT_STATUS_ID >", value, "contractStatusId");
            return (Criteria) this;
        }

        public Criteria andContractStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_STATUS_ID >=", value, "contractStatusId");
            return (Criteria) this;
        }

        public Criteria andContractStatusIdLessThan(String value) {
            addCriterion("CONTRACT_STATUS_ID <", value, "contractStatusId");
            return (Criteria) this;
        }

        public Criteria andContractStatusIdLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_STATUS_ID <=", value, "contractStatusId");
            return (Criteria) this;
        }

        public Criteria andContractStatusIdLike(String value) {
            addCriterion("CONTRACT_STATUS_ID like", value, "contractStatusId");
            return (Criteria) this;
        }

        public Criteria andContractStatusIdNotLike(String value) {
            addCriterion("CONTRACT_STATUS_ID not like", value, "contractStatusId");
            return (Criteria) this;
        }

        public Criteria andContractStatusIdIn(List<String> values) {
            addCriterion("CONTRACT_STATUS_ID in", values, "contractStatusId");
            return (Criteria) this;
        }

        public Criteria andContractStatusIdNotIn(List<String> values) {
            addCriterion("CONTRACT_STATUS_ID not in", values, "contractStatusId");
            return (Criteria) this;
        }

        public Criteria andContractStatusIdBetween(String value1, String value2) {
            addCriterion("CONTRACT_STATUS_ID between", value1, value2, "contractStatusId");
            return (Criteria) this;
        }

        public Criteria andContractStatusIdNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_STATUS_ID not between", value1, value2, "contractStatusId");
            return (Criteria) this;
        }

        public Criteria andContractStatusNameIsNull() {
            addCriterion("CONTRACT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andContractStatusNameIsNotNull() {
            addCriterion("CONTRACT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andContractStatusNameEqualTo(String value) {
            addCriterion("CONTRACT_STATUS_NAME =", value, "contractStatusName");
            return (Criteria) this;
        }

        public Criteria andContractStatusNameNotEqualTo(String value) {
            addCriterion("CONTRACT_STATUS_NAME <>", value, "contractStatusName");
            return (Criteria) this;
        }

        public Criteria andContractStatusNameGreaterThan(String value) {
            addCriterion("CONTRACT_STATUS_NAME >", value, "contractStatusName");
            return (Criteria) this;
        }

        public Criteria andContractStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_STATUS_NAME >=", value, "contractStatusName");
            return (Criteria) this;
        }

        public Criteria andContractStatusNameLessThan(String value) {
            addCriterion("CONTRACT_STATUS_NAME <", value, "contractStatusName");
            return (Criteria) this;
        }

        public Criteria andContractStatusNameLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_STATUS_NAME <=", value, "contractStatusName");
            return (Criteria) this;
        }

        public Criteria andContractStatusNameLike(String value) {
            addCriterion("CONTRACT_STATUS_NAME like", value, "contractStatusName");
            return (Criteria) this;
        }

        public Criteria andContractStatusNameNotLike(String value) {
            addCriterion("CONTRACT_STATUS_NAME not like", value, "contractStatusName");
            return (Criteria) this;
        }

        public Criteria andContractStatusNameIn(List<String> values) {
            addCriterion("CONTRACT_STATUS_NAME in", values, "contractStatusName");
            return (Criteria) this;
        }

        public Criteria andContractStatusNameNotIn(List<String> values) {
            addCriterion("CONTRACT_STATUS_NAME not in", values, "contractStatusName");
            return (Criteria) this;
        }

        public Criteria andContractStatusNameBetween(String value1, String value2) {
            addCriterion("CONTRACT_STATUS_NAME between", value1, value2, "contractStatusName");
            return (Criteria) this;
        }

        public Criteria andContractStatusNameNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_STATUS_NAME not between", value1, value2, "contractStatusName");
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

        public Criteria andSubCountIsNull() {
            addCriterion("SUB_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andSubCountIsNotNull() {
            addCriterion("SUB_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andSubCountEqualTo(BigDecimal value) {
            addCriterion("SUB_COUNT =", value, "subCount");
            return (Criteria) this;
        }

        public Criteria andSubCountNotEqualTo(BigDecimal value) {
            addCriterion("SUB_COUNT <>", value, "subCount");
            return (Criteria) this;
        }

        public Criteria andSubCountGreaterThan(BigDecimal value) {
            addCriterion("SUB_COUNT >", value, "subCount");
            return (Criteria) this;
        }

        public Criteria andSubCountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SUB_COUNT >=", value, "subCount");
            return (Criteria) this;
        }

        public Criteria andSubCountLessThan(BigDecimal value) {
            addCriterion("SUB_COUNT <", value, "subCount");
            return (Criteria) this;
        }

        public Criteria andSubCountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SUB_COUNT <=", value, "subCount");
            return (Criteria) this;
        }

        public Criteria andSubCountIn(List<BigDecimal> values) {
            addCriterion("SUB_COUNT in", values, "subCount");
            return (Criteria) this;
        }

        public Criteria andSubCountNotIn(List<BigDecimal> values) {
            addCriterion("SUB_COUNT not in", values, "subCount");
            return (Criteria) this;
        }

        public Criteria andSubCountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SUB_COUNT between", value1, value2, "subCount");
            return (Criteria) this;
        }

        public Criteria andSubCountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SUB_COUNT not between", value1, value2, "subCount");
            return (Criteria) this;
        }

        public Criteria andContractOwnIdIsNull() {
            addCriterion("CONTRACT_OWN_ID is null");
            return (Criteria) this;
        }

        public Criteria andContractOwnIdIsNotNull() {
            addCriterion("CONTRACT_OWN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andContractOwnIdEqualTo(String value) {
            addCriterion("CONTRACT_OWN_ID =", value, "contractOwnId");
            return (Criteria) this;
        }

        public Criteria andContractOwnIdNotEqualTo(String value) {
            addCriterion("CONTRACT_OWN_ID <>", value, "contractOwnId");
            return (Criteria) this;
        }

        public Criteria andContractOwnIdGreaterThan(String value) {
            addCriterion("CONTRACT_OWN_ID >", value, "contractOwnId");
            return (Criteria) this;
        }

        public Criteria andContractOwnIdGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_OWN_ID >=", value, "contractOwnId");
            return (Criteria) this;
        }

        public Criteria andContractOwnIdLessThan(String value) {
            addCriterion("CONTRACT_OWN_ID <", value, "contractOwnId");
            return (Criteria) this;
        }

        public Criteria andContractOwnIdLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_OWN_ID <=", value, "contractOwnId");
            return (Criteria) this;
        }

        public Criteria andContractOwnIdLike(String value) {
            addCriterion("CONTRACT_OWN_ID like", value, "contractOwnId");
            return (Criteria) this;
        }

        public Criteria andContractOwnIdNotLike(String value) {
            addCriterion("CONTRACT_OWN_ID not like", value, "contractOwnId");
            return (Criteria) this;
        }

        public Criteria andContractOwnIdIn(List<String> values) {
            addCriterion("CONTRACT_OWN_ID in", values, "contractOwnId");
            return (Criteria) this;
        }

        public Criteria andContractOwnIdNotIn(List<String> values) {
            addCriterion("CONTRACT_OWN_ID not in", values, "contractOwnId");
            return (Criteria) this;
        }

        public Criteria andContractOwnIdBetween(String value1, String value2) {
            addCriterion("CONTRACT_OWN_ID between", value1, value2, "contractOwnId");
            return (Criteria) this;
        }

        public Criteria andContractOwnIdNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_OWN_ID not between", value1, value2, "contractOwnId");
            return (Criteria) this;
        }

        public Criteria andContractOwnNameIsNull() {
            addCriterion("CONTRACT_OWN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andContractOwnNameIsNotNull() {
            addCriterion("CONTRACT_OWN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andContractOwnNameEqualTo(String value) {
            addCriterion("CONTRACT_OWN_NAME =", value, "contractOwnName");
            return (Criteria) this;
        }

        public Criteria andContractOwnNameNotEqualTo(String value) {
            addCriterion("CONTRACT_OWN_NAME <>", value, "contractOwnName");
            return (Criteria) this;
        }

        public Criteria andContractOwnNameGreaterThan(String value) {
            addCriterion("CONTRACT_OWN_NAME >", value, "contractOwnName");
            return (Criteria) this;
        }

        public Criteria andContractOwnNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_OWN_NAME >=", value, "contractOwnName");
            return (Criteria) this;
        }

        public Criteria andContractOwnNameLessThan(String value) {
            addCriterion("CONTRACT_OWN_NAME <", value, "contractOwnName");
            return (Criteria) this;
        }

        public Criteria andContractOwnNameLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_OWN_NAME <=", value, "contractOwnName");
            return (Criteria) this;
        }

        public Criteria andContractOwnNameLike(String value) {
            addCriterion("CONTRACT_OWN_NAME like", value, "contractOwnName");
            return (Criteria) this;
        }

        public Criteria andContractOwnNameNotLike(String value) {
            addCriterion("CONTRACT_OWN_NAME not like", value, "contractOwnName");
            return (Criteria) this;
        }

        public Criteria andContractOwnNameIn(List<String> values) {
            addCriterion("CONTRACT_OWN_NAME in", values, "contractOwnName");
            return (Criteria) this;
        }

        public Criteria andContractOwnNameNotIn(List<String> values) {
            addCriterion("CONTRACT_OWN_NAME not in", values, "contractOwnName");
            return (Criteria) this;
        }

        public Criteria andContractOwnNameBetween(String value1, String value2) {
            addCriterion("CONTRACT_OWN_NAME between", value1, value2, "contractOwnName");
            return (Criteria) this;
        }

        public Criteria andContractOwnNameNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_OWN_NAME not between", value1, value2, "contractOwnName");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowIsNull() {
            addCriterion("CHARGE_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowIsNotNull() {
            addCriterion("CHARGE_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowEqualTo(String value) {
            addCriterion("CHARGE_USER_FLOW =", value, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowNotEqualTo(String value) {
            addCriterion("CHARGE_USER_FLOW <>", value, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowGreaterThan(String value) {
            addCriterion("CHARGE_USER_FLOW >", value, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_USER_FLOW >=", value, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowLessThan(String value) {
            addCriterion("CHARGE_USER_FLOW <", value, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_USER_FLOW <=", value, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowLike(String value) {
            addCriterion("CHARGE_USER_FLOW like", value, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowNotLike(String value) {
            addCriterion("CHARGE_USER_FLOW not like", value, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowIn(List<String> values) {
            addCriterion("CHARGE_USER_FLOW in", values, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowNotIn(List<String> values) {
            addCriterion("CHARGE_USER_FLOW not in", values, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowBetween(String value1, String value2) {
            addCriterion("CHARGE_USER_FLOW between", value1, value2, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowNotBetween(String value1, String value2) {
            addCriterion("CHARGE_USER_FLOW not between", value1, value2, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameIsNull() {
            addCriterion("CHARGE_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameIsNotNull() {
            addCriterion("CHARGE_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameEqualTo(String value) {
            addCriterion("CHARGE_USER_NAME =", value, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameNotEqualTo(String value) {
            addCriterion("CHARGE_USER_NAME <>", value, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameGreaterThan(String value) {
            addCriterion("CHARGE_USER_NAME >", value, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_USER_NAME >=", value, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameLessThan(String value) {
            addCriterion("CHARGE_USER_NAME <", value, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_USER_NAME <=", value, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameLike(String value) {
            addCriterion("CHARGE_USER_NAME like", value, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameNotLike(String value) {
            addCriterion("CHARGE_USER_NAME not like", value, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameIn(List<String> values) {
            addCriterion("CHARGE_USER_NAME in", values, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameNotIn(List<String> values) {
            addCriterion("CHARGE_USER_NAME not in", values, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameBetween(String value1, String value2) {
            addCriterion("CHARGE_USER_NAME between", value1, value2, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameNotBetween(String value1, String value2) {
            addCriterion("CHARGE_USER_NAME not between", value1, value2, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptIsNull() {
            addCriterion("CUSTOMER_DEPT is null");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptIsNotNull() {
            addCriterion("CUSTOMER_DEPT is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptEqualTo(String value) {
            addCriterion("CUSTOMER_DEPT =", value, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptNotEqualTo(String value) {
            addCriterion("CUSTOMER_DEPT <>", value, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptGreaterThan(String value) {
            addCriterion("CUSTOMER_DEPT >", value, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_DEPT >=", value, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptLessThan(String value) {
            addCriterion("CUSTOMER_DEPT <", value, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_DEPT <=", value, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptLike(String value) {
            addCriterion("CUSTOMER_DEPT like", value, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptNotLike(String value) {
            addCriterion("CUSTOMER_DEPT not like", value, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptIn(List<String> values) {
            addCriterion("CUSTOMER_DEPT in", values, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptNotIn(List<String> values) {
            addCriterion("CUSTOMER_DEPT not in", values, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptBetween(String value1, String value2) {
            addCriterion("CUSTOMER_DEPT between", value1, value2, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_DEPT not between", value1, value2, "customerDept");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowIsNull() {
            addCriterion("CONSUMER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowIsNotNull() {
            addCriterion("CONSUMER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowEqualTo(String value) {
            addCriterion("CONSUMER_FLOW =", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowNotEqualTo(String value) {
            addCriterion("CONSUMER_FLOW <>", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowGreaterThan(String value) {
            addCriterion("CONSUMER_FLOW >", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CONSUMER_FLOW >=", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowLessThan(String value) {
            addCriterion("CONSUMER_FLOW <", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowLessThanOrEqualTo(String value) {
            addCriterion("CONSUMER_FLOW <=", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowLike(String value) {
            addCriterion("CONSUMER_FLOW like", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowNotLike(String value) {
            addCriterion("CONSUMER_FLOW not like", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowIn(List<String> values) {
            addCriterion("CONSUMER_FLOW in", values, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowNotIn(List<String> values) {
            addCriterion("CONSUMER_FLOW not in", values, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowBetween(String value1, String value2) {
            addCriterion("CONSUMER_FLOW between", value1, value2, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowNotBetween(String value1, String value2) {
            addCriterion("CONSUMER_FLOW not between", value1, value2, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerNameIsNull() {
            addCriterion("CONSUMER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andConsumerNameIsNotNull() {
            addCriterion("CONSUMER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerNameEqualTo(String value) {
            addCriterion("CONSUMER_NAME =", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotEqualTo(String value) {
            addCriterion("CONSUMER_NAME <>", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameGreaterThan(String value) {
            addCriterion("CONSUMER_NAME >", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONSUMER_NAME >=", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameLessThan(String value) {
            addCriterion("CONSUMER_NAME <", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameLessThanOrEqualTo(String value) {
            addCriterion("CONSUMER_NAME <=", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameLike(String value) {
            addCriterion("CONSUMER_NAME like", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotLike(String value) {
            addCriterion("CONSUMER_NAME not like", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameIn(List<String> values) {
            addCriterion("CONSUMER_NAME in", values, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotIn(List<String> values) {
            addCriterion("CONSUMER_NAME not in", values, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameBetween(String value1, String value2) {
            addCriterion("CONSUMER_NAME between", value1, value2, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotBetween(String value1, String value2) {
            addCriterion("CONSUMER_NAME not between", value1, value2, "consumerName");
            return (Criteria) this;
        }

        public Criteria andContractArchivesNoIsNull() {
            addCriterion("CONTRACT_ARCHIVES_NO is null");
            return (Criteria) this;
        }

        public Criteria andContractArchivesNoIsNotNull() {
            addCriterion("CONTRACT_ARCHIVES_NO is not null");
            return (Criteria) this;
        }

        public Criteria andContractArchivesNoEqualTo(String value) {
            addCriterion("CONTRACT_ARCHIVES_NO =", value, "contractArchivesNo");
            return (Criteria) this;
        }

        public Criteria andContractArchivesNoNotEqualTo(String value) {
            addCriterion("CONTRACT_ARCHIVES_NO <>", value, "contractArchivesNo");
            return (Criteria) this;
        }

        public Criteria andContractArchivesNoGreaterThan(String value) {
            addCriterion("CONTRACT_ARCHIVES_NO >", value, "contractArchivesNo");
            return (Criteria) this;
        }

        public Criteria andContractArchivesNoGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_ARCHIVES_NO >=", value, "contractArchivesNo");
            return (Criteria) this;
        }

        public Criteria andContractArchivesNoLessThan(String value) {
            addCriterion("CONTRACT_ARCHIVES_NO <", value, "contractArchivesNo");
            return (Criteria) this;
        }

        public Criteria andContractArchivesNoLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_ARCHIVES_NO <=", value, "contractArchivesNo");
            return (Criteria) this;
        }

        public Criteria andContractArchivesNoLike(String value) {
            addCriterion("CONTRACT_ARCHIVES_NO like", value, "contractArchivesNo");
            return (Criteria) this;
        }

        public Criteria andContractArchivesNoNotLike(String value) {
            addCriterion("CONTRACT_ARCHIVES_NO not like", value, "contractArchivesNo");
            return (Criteria) this;
        }

        public Criteria andContractArchivesNoIn(List<String> values) {
            addCriterion("CONTRACT_ARCHIVES_NO in", values, "contractArchivesNo");
            return (Criteria) this;
        }

        public Criteria andContractArchivesNoNotIn(List<String> values) {
            addCriterion("CONTRACT_ARCHIVES_NO not in", values, "contractArchivesNo");
            return (Criteria) this;
        }

        public Criteria andContractArchivesNoBetween(String value1, String value2) {
            addCriterion("CONTRACT_ARCHIVES_NO between", value1, value2, "contractArchivesNo");
            return (Criteria) this;
        }

        public Criteria andContractArchivesNoNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_ARCHIVES_NO not between", value1, value2, "contractArchivesNo");
            return (Criteria) this;
        }

        public Criteria andFeedBackTimeIsNull() {
            addCriterion("FEED_BACK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andFeedBackTimeIsNotNull() {
            addCriterion("FEED_BACK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andFeedBackTimeEqualTo(String value) {
            addCriterion("FEED_BACK_TIME =", value, "feedBackTime");
            return (Criteria) this;
        }

        public Criteria andFeedBackTimeNotEqualTo(String value) {
            addCriterion("FEED_BACK_TIME <>", value, "feedBackTime");
            return (Criteria) this;
        }

        public Criteria andFeedBackTimeGreaterThan(String value) {
            addCriterion("FEED_BACK_TIME >", value, "feedBackTime");
            return (Criteria) this;
        }

        public Criteria andFeedBackTimeGreaterThanOrEqualTo(String value) {
            addCriterion("FEED_BACK_TIME >=", value, "feedBackTime");
            return (Criteria) this;
        }

        public Criteria andFeedBackTimeLessThan(String value) {
            addCriterion("FEED_BACK_TIME <", value, "feedBackTime");
            return (Criteria) this;
        }

        public Criteria andFeedBackTimeLessThanOrEqualTo(String value) {
            addCriterion("FEED_BACK_TIME <=", value, "feedBackTime");
            return (Criteria) this;
        }

        public Criteria andFeedBackTimeLike(String value) {
            addCriterion("FEED_BACK_TIME like", value, "feedBackTime");
            return (Criteria) this;
        }

        public Criteria andFeedBackTimeNotLike(String value) {
            addCriterion("FEED_BACK_TIME not like", value, "feedBackTime");
            return (Criteria) this;
        }

        public Criteria andFeedBackTimeIn(List<String> values) {
            addCriterion("FEED_BACK_TIME in", values, "feedBackTime");
            return (Criteria) this;
        }

        public Criteria andFeedBackTimeNotIn(List<String> values) {
            addCriterion("FEED_BACK_TIME not in", values, "feedBackTime");
            return (Criteria) this;
        }

        public Criteria andFeedBackTimeBetween(String value1, String value2) {
            addCriterion("FEED_BACK_TIME between", value1, value2, "feedBackTime");
            return (Criteria) this;
        }

        public Criteria andFeedBackTimeNotBetween(String value1, String value2) {
            addCriterion("FEED_BACK_TIME not between", value1, value2, "feedBackTime");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserFlowIsNull() {
            addCriterion("FEED_BACK_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserFlowIsNotNull() {
            addCriterion("FEED_BACK_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserFlowEqualTo(String value) {
            addCriterion("FEED_BACK_USER_FLOW =", value, "feedBackUserFlow");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserFlowNotEqualTo(String value) {
            addCriterion("FEED_BACK_USER_FLOW <>", value, "feedBackUserFlow");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserFlowGreaterThan(String value) {
            addCriterion("FEED_BACK_USER_FLOW >", value, "feedBackUserFlow");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FEED_BACK_USER_FLOW >=", value, "feedBackUserFlow");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserFlowLessThan(String value) {
            addCriterion("FEED_BACK_USER_FLOW <", value, "feedBackUserFlow");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserFlowLessThanOrEqualTo(String value) {
            addCriterion("FEED_BACK_USER_FLOW <=", value, "feedBackUserFlow");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserFlowLike(String value) {
            addCriterion("FEED_BACK_USER_FLOW like", value, "feedBackUserFlow");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserFlowNotLike(String value) {
            addCriterion("FEED_BACK_USER_FLOW not like", value, "feedBackUserFlow");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserFlowIn(List<String> values) {
            addCriterion("FEED_BACK_USER_FLOW in", values, "feedBackUserFlow");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserFlowNotIn(List<String> values) {
            addCriterion("FEED_BACK_USER_FLOW not in", values, "feedBackUserFlow");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserFlowBetween(String value1, String value2) {
            addCriterion("FEED_BACK_USER_FLOW between", value1, value2, "feedBackUserFlow");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserFlowNotBetween(String value1, String value2) {
            addCriterion("FEED_BACK_USER_FLOW not between", value1, value2, "feedBackUserFlow");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserNameIsNull() {
            addCriterion("FEED_BACK_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserNameIsNotNull() {
            addCriterion("FEED_BACK_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserNameEqualTo(String value) {
            addCriterion("FEED_BACK_USER_NAME =", value, "feedBackUserName");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserNameNotEqualTo(String value) {
            addCriterion("FEED_BACK_USER_NAME <>", value, "feedBackUserName");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserNameGreaterThan(String value) {
            addCriterion("FEED_BACK_USER_NAME >", value, "feedBackUserName");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("FEED_BACK_USER_NAME >=", value, "feedBackUserName");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserNameLessThan(String value) {
            addCriterion("FEED_BACK_USER_NAME <", value, "feedBackUserName");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserNameLessThanOrEqualTo(String value) {
            addCriterion("FEED_BACK_USER_NAME <=", value, "feedBackUserName");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserNameLike(String value) {
            addCriterion("FEED_BACK_USER_NAME like", value, "feedBackUserName");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserNameNotLike(String value) {
            addCriterion("FEED_BACK_USER_NAME not like", value, "feedBackUserName");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserNameIn(List<String> values) {
            addCriterion("FEED_BACK_USER_NAME in", values, "feedBackUserName");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserNameNotIn(List<String> values) {
            addCriterion("FEED_BACK_USER_NAME not in", values, "feedBackUserName");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserNameBetween(String value1, String value2) {
            addCriterion("FEED_BACK_USER_NAME between", value1, value2, "feedBackUserName");
            return (Criteria) this;
        }

        public Criteria andFeedBackUserNameNotBetween(String value1, String value2) {
            addCriterion("FEED_BACK_USER_NAME not between", value1, value2, "feedBackUserName");
            return (Criteria) this;
        }

        public Criteria andFeedBackContentIsNull() {
            addCriterion("FEED_BACK_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andFeedBackContentIsNotNull() {
            addCriterion("FEED_BACK_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andFeedBackContentEqualTo(String value) {
            addCriterion("FEED_BACK_CONTENT =", value, "feedBackContent");
            return (Criteria) this;
        }

        public Criteria andFeedBackContentNotEqualTo(String value) {
            addCriterion("FEED_BACK_CONTENT <>", value, "feedBackContent");
            return (Criteria) this;
        }

        public Criteria andFeedBackContentGreaterThan(String value) {
            addCriterion("FEED_BACK_CONTENT >", value, "feedBackContent");
            return (Criteria) this;
        }

        public Criteria andFeedBackContentGreaterThanOrEqualTo(String value) {
            addCriterion("FEED_BACK_CONTENT >=", value, "feedBackContent");
            return (Criteria) this;
        }

        public Criteria andFeedBackContentLessThan(String value) {
            addCriterion("FEED_BACK_CONTENT <", value, "feedBackContent");
            return (Criteria) this;
        }

        public Criteria andFeedBackContentLessThanOrEqualTo(String value) {
            addCriterion("FEED_BACK_CONTENT <=", value, "feedBackContent");
            return (Criteria) this;
        }

        public Criteria andFeedBackContentLike(String value) {
            addCriterion("FEED_BACK_CONTENT like", value, "feedBackContent");
            return (Criteria) this;
        }

        public Criteria andFeedBackContentNotLike(String value) {
            addCriterion("FEED_BACK_CONTENT not like", value, "feedBackContent");
            return (Criteria) this;
        }

        public Criteria andFeedBackContentIn(List<String> values) {
            addCriterion("FEED_BACK_CONTENT in", values, "feedBackContent");
            return (Criteria) this;
        }

        public Criteria andFeedBackContentNotIn(List<String> values) {
            addCriterion("FEED_BACK_CONTENT not in", values, "feedBackContent");
            return (Criteria) this;
        }

        public Criteria andFeedBackContentBetween(String value1, String value2) {
            addCriterion("FEED_BACK_CONTENT between", value1, value2, "feedBackContent");
            return (Criteria) this;
        }

        public Criteria andFeedBackContentNotBetween(String value1, String value2) {
            addCriterion("FEED_BACK_CONTENT not between", value1, value2, "feedBackContent");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowIsNull() {
            addCriterion("CHARGE_USER2_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowIsNotNull() {
            addCriterion("CHARGE_USER2_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowEqualTo(String value) {
            addCriterion("CHARGE_USER2_FLOW =", value, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowNotEqualTo(String value) {
            addCriterion("CHARGE_USER2_FLOW <>", value, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowGreaterThan(String value) {
            addCriterion("CHARGE_USER2_FLOW >", value, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_USER2_FLOW >=", value, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowLessThan(String value) {
            addCriterion("CHARGE_USER2_FLOW <", value, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_USER2_FLOW <=", value, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowLike(String value) {
            addCriterion("CHARGE_USER2_FLOW like", value, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowNotLike(String value) {
            addCriterion("CHARGE_USER2_FLOW not like", value, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowIn(List<String> values) {
            addCriterion("CHARGE_USER2_FLOW in", values, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowNotIn(List<String> values) {
            addCriterion("CHARGE_USER2_FLOW not in", values, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowBetween(String value1, String value2) {
            addCriterion("CHARGE_USER2_FLOW between", value1, value2, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowNotBetween(String value1, String value2) {
            addCriterion("CHARGE_USER2_FLOW not between", value1, value2, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameIsNull() {
            addCriterion("CHARGE_USER2_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameIsNotNull() {
            addCriterion("CHARGE_USER2_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameEqualTo(String value) {
            addCriterion("CHARGE_USER2_NAME =", value, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameNotEqualTo(String value) {
            addCriterion("CHARGE_USER2_NAME <>", value, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameGreaterThan(String value) {
            addCriterion("CHARGE_USER2_NAME >", value, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_USER2_NAME >=", value, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameLessThan(String value) {
            addCriterion("CHARGE_USER2_NAME <", value, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_USER2_NAME <=", value, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameLike(String value) {
            addCriterion("CHARGE_USER2_NAME like", value, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameNotLike(String value) {
            addCriterion("CHARGE_USER2_NAME not like", value, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameIn(List<String> values) {
            addCriterion("CHARGE_USER2_NAME in", values, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameNotIn(List<String> values) {
            addCriterion("CHARGE_USER2_NAME not in", values, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameBetween(String value1, String value2) {
            addCriterion("CHARGE_USER2_NAME between", value1, value2, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameNotBetween(String value1, String value2) {
            addCriterion("CHARGE_USER2_NAME not between", value1, value2, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andPlanAcceptDateIsNull() {
            addCriterion("PLAN_ACCEPT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPlanAcceptDateIsNotNull() {
            addCriterion("PLAN_ACCEPT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPlanAcceptDateEqualTo(String value) {
            addCriterion("PLAN_ACCEPT_DATE =", value, "planAcceptDate");
            return (Criteria) this;
        }

        public Criteria andPlanAcceptDateNotEqualTo(String value) {
            addCriterion("PLAN_ACCEPT_DATE <>", value, "planAcceptDate");
            return (Criteria) this;
        }

        public Criteria andPlanAcceptDateGreaterThan(String value) {
            addCriterion("PLAN_ACCEPT_DATE >", value, "planAcceptDate");
            return (Criteria) this;
        }

        public Criteria andPlanAcceptDateGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_ACCEPT_DATE >=", value, "planAcceptDate");
            return (Criteria) this;
        }

        public Criteria andPlanAcceptDateLessThan(String value) {
            addCriterion("PLAN_ACCEPT_DATE <", value, "planAcceptDate");
            return (Criteria) this;
        }

        public Criteria andPlanAcceptDateLessThanOrEqualTo(String value) {
            addCriterion("PLAN_ACCEPT_DATE <=", value, "planAcceptDate");
            return (Criteria) this;
        }

        public Criteria andPlanAcceptDateLike(String value) {
            addCriterion("PLAN_ACCEPT_DATE like", value, "planAcceptDate");
            return (Criteria) this;
        }

        public Criteria andPlanAcceptDateNotLike(String value) {
            addCriterion("PLAN_ACCEPT_DATE not like", value, "planAcceptDate");
            return (Criteria) this;
        }

        public Criteria andPlanAcceptDateIn(List<String> values) {
            addCriterion("PLAN_ACCEPT_DATE in", values, "planAcceptDate");
            return (Criteria) this;
        }

        public Criteria andPlanAcceptDateNotIn(List<String> values) {
            addCriterion("PLAN_ACCEPT_DATE not in", values, "planAcceptDate");
            return (Criteria) this;
        }

        public Criteria andPlanAcceptDateBetween(String value1, String value2) {
            addCriterion("PLAN_ACCEPT_DATE between", value1, value2, "planAcceptDate");
            return (Criteria) this;
        }

        public Criteria andPlanAcceptDateNotBetween(String value1, String value2) {
            addCriterion("PLAN_ACCEPT_DATE not between", value1, value2, "planAcceptDate");
            return (Criteria) this;
        }

        public Criteria andMaintainCountIsNull() {
            addCriterion("MAINTAIN_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andMaintainCountIsNotNull() {
            addCriterion("MAINTAIN_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andMaintainCountEqualTo(String value) {
            addCriterion("MAINTAIN_COUNT =", value, "maintainCount");
            return (Criteria) this;
        }

        public Criteria andMaintainCountNotEqualTo(String value) {
            addCriterion("MAINTAIN_COUNT <>", value, "maintainCount");
            return (Criteria) this;
        }

        public Criteria andMaintainCountGreaterThan(String value) {
            addCriterion("MAINTAIN_COUNT >", value, "maintainCount");
            return (Criteria) this;
        }

        public Criteria andMaintainCountGreaterThanOrEqualTo(String value) {
            addCriterion("MAINTAIN_COUNT >=", value, "maintainCount");
            return (Criteria) this;
        }

        public Criteria andMaintainCountLessThan(String value) {
            addCriterion("MAINTAIN_COUNT <", value, "maintainCount");
            return (Criteria) this;
        }

        public Criteria andMaintainCountLessThanOrEqualTo(String value) {
            addCriterion("MAINTAIN_COUNT <=", value, "maintainCount");
            return (Criteria) this;
        }

        public Criteria andMaintainCountLike(String value) {
            addCriterion("MAINTAIN_COUNT like", value, "maintainCount");
            return (Criteria) this;
        }

        public Criteria andMaintainCountNotLike(String value) {
            addCriterion("MAINTAIN_COUNT not like", value, "maintainCount");
            return (Criteria) this;
        }

        public Criteria andMaintainCountIn(List<String> values) {
            addCriterion("MAINTAIN_COUNT in", values, "maintainCount");
            return (Criteria) this;
        }

        public Criteria andMaintainCountNotIn(List<String> values) {
            addCriterion("MAINTAIN_COUNT not in", values, "maintainCount");
            return (Criteria) this;
        }

        public Criteria andMaintainCountBetween(String value1, String value2) {
            addCriterion("MAINTAIN_COUNT between", value1, value2, "maintainCount");
            return (Criteria) this;
        }

        public Criteria andMaintainCountNotBetween(String value1, String value2) {
            addCriterion("MAINTAIN_COUNT not between", value1, value2, "maintainCount");
            return (Criteria) this;
        }

        public Criteria andNextMaintainDateIsNull() {
            addCriterion("NEXT_MAINTAIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andNextMaintainDateIsNotNull() {
            addCriterion("NEXT_MAINTAIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andNextMaintainDateEqualTo(String value) {
            addCriterion("NEXT_MAINTAIN_DATE =", value, "nextMaintainDate");
            return (Criteria) this;
        }

        public Criteria andNextMaintainDateNotEqualTo(String value) {
            addCriterion("NEXT_MAINTAIN_DATE <>", value, "nextMaintainDate");
            return (Criteria) this;
        }

        public Criteria andNextMaintainDateGreaterThan(String value) {
            addCriterion("NEXT_MAINTAIN_DATE >", value, "nextMaintainDate");
            return (Criteria) this;
        }

        public Criteria andNextMaintainDateGreaterThanOrEqualTo(String value) {
            addCriterion("NEXT_MAINTAIN_DATE >=", value, "nextMaintainDate");
            return (Criteria) this;
        }

        public Criteria andNextMaintainDateLessThan(String value) {
            addCriterion("NEXT_MAINTAIN_DATE <", value, "nextMaintainDate");
            return (Criteria) this;
        }

        public Criteria andNextMaintainDateLessThanOrEqualTo(String value) {
            addCriterion("NEXT_MAINTAIN_DATE <=", value, "nextMaintainDate");
            return (Criteria) this;
        }

        public Criteria andNextMaintainDateLike(String value) {
            addCriterion("NEXT_MAINTAIN_DATE like", value, "nextMaintainDate");
            return (Criteria) this;
        }

        public Criteria andNextMaintainDateNotLike(String value) {
            addCriterion("NEXT_MAINTAIN_DATE not like", value, "nextMaintainDate");
            return (Criteria) this;
        }

        public Criteria andNextMaintainDateIn(List<String> values) {
            addCriterion("NEXT_MAINTAIN_DATE in", values, "nextMaintainDate");
            return (Criteria) this;
        }

        public Criteria andNextMaintainDateNotIn(List<String> values) {
            addCriterion("NEXT_MAINTAIN_DATE not in", values, "nextMaintainDate");
            return (Criteria) this;
        }

        public Criteria andNextMaintainDateBetween(String value1, String value2) {
            addCriterion("NEXT_MAINTAIN_DATE between", value1, value2, "nextMaintainDate");
            return (Criteria) this;
        }

        public Criteria andNextMaintainDateNotBetween(String value1, String value2) {
            addCriterion("NEXT_MAINTAIN_DATE not between", value1, value2, "nextMaintainDate");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowIsNull() {
            addCriterion("AUDIT_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowIsNotNull() {
            addCriterion("AUDIT_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW =", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW <>", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowGreaterThan(String value) {
            addCriterion("AUDIT_USER_FLOW >", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW >=", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowLessThan(String value) {
            addCriterion("AUDIT_USER_FLOW <", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW <=", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowLike(String value) {
            addCriterion("AUDIT_USER_FLOW like", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotLike(String value) {
            addCriterion("AUDIT_USER_FLOW not like", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowIn(List<String> values) {
            addCriterion("AUDIT_USER_FLOW in", values, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotIn(List<String> values) {
            addCriterion("AUDIT_USER_FLOW not in", values, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_FLOW between", value1, value2, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_FLOW not between", value1, value2, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIsNull() {
            addCriterion("AUDIT_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIsNotNull() {
            addCriterion("AUDIT_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME =", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME <>", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameGreaterThan(String value) {
            addCriterion("AUDIT_USER_NAME >", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME >=", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLessThan(String value) {
            addCriterion("AUDIT_USER_NAME <", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME <=", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLike(String value) {
            addCriterion("AUDIT_USER_NAME like", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotLike(String value) {
            addCriterion("AUDIT_USER_NAME not like", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIn(List<String> values) {
            addCriterion("AUDIT_USER_NAME in", values, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotIn(List<String> values) {
            addCriterion("AUDIT_USER_NAME not in", values, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_NAME between", value1, value2, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_NAME not between", value1, value2, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditReasonIsNull() {
            addCriterion("AUDIT_REASON is null");
            return (Criteria) this;
        }

        public Criteria andAuditReasonIsNotNull() {
            addCriterion("AUDIT_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andAuditReasonEqualTo(String value) {
            addCriterion("AUDIT_REASON =", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonNotEqualTo(String value) {
            addCriterion("AUDIT_REASON <>", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonGreaterThan(String value) {
            addCriterion("AUDIT_REASON >", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_REASON >=", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonLessThan(String value) {
            addCriterion("AUDIT_REASON <", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_REASON <=", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonLike(String value) {
            addCriterion("AUDIT_REASON like", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonNotLike(String value) {
            addCriterion("AUDIT_REASON not like", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonIn(List<String> values) {
            addCriterion("AUDIT_REASON in", values, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonNotIn(List<String> values) {
            addCriterion("AUDIT_REASON not in", values, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonBetween(String value1, String value2) {
            addCriterion("AUDIT_REASON between", value1, value2, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonNotBetween(String value1, String value2) {
            addCriterion("AUDIT_REASON not between", value1, value2, "auditReason");
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