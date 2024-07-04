package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ErpOaLicKeyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ErpOaLicKeyExample() {
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

        public Criteria andLicFlowIsNull() {
            addCriterion("LIC_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andLicFlowIsNotNull() {
            addCriterion("LIC_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andLicFlowEqualTo(String value) {
            addCriterion("LIC_FLOW =", value, "licFlow");
            return (Criteria) this;
        }

        public Criteria andLicFlowNotEqualTo(String value) {
            addCriterion("LIC_FLOW <>", value, "licFlow");
            return (Criteria) this;
        }

        public Criteria andLicFlowGreaterThan(String value) {
            addCriterion("LIC_FLOW >", value, "licFlow");
            return (Criteria) this;
        }

        public Criteria andLicFlowGreaterThanOrEqualTo(String value) {
            addCriterion("LIC_FLOW >=", value, "licFlow");
            return (Criteria) this;
        }

        public Criteria andLicFlowLessThan(String value) {
            addCriterion("LIC_FLOW <", value, "licFlow");
            return (Criteria) this;
        }

        public Criteria andLicFlowLessThanOrEqualTo(String value) {
            addCriterion("LIC_FLOW <=", value, "licFlow");
            return (Criteria) this;
        }

        public Criteria andLicFlowLike(String value) {
            addCriterion("LIC_FLOW like", value, "licFlow");
            return (Criteria) this;
        }

        public Criteria andLicFlowNotLike(String value) {
            addCriterion("LIC_FLOW not like", value, "licFlow");
            return (Criteria) this;
        }

        public Criteria andLicFlowIn(List<String> values) {
            addCriterion("LIC_FLOW in", values, "licFlow");
            return (Criteria) this;
        }

        public Criteria andLicFlowNotIn(List<String> values) {
            addCriterion("LIC_FLOW not in", values, "licFlow");
            return (Criteria) this;
        }

        public Criteria andLicFlowBetween(String value1, String value2) {
            addCriterion("LIC_FLOW between", value1, value2, "licFlow");
            return (Criteria) this;
        }

        public Criteria andLicFlowNotBetween(String value1, String value2) {
            addCriterion("LIC_FLOW not between", value1, value2, "licFlow");
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

        public Criteria andCustomerNameIsNull() {
            addCriterion("CUSTOMER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("CUSTOMER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("CUSTOMER_NAME =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("CUSTOMER_NAME <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("CUSTOMER_NAME >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_NAME >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("CUSTOMER_NAME <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_NAME <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("CUSTOMER_NAME like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("CUSTOMER_NAME not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("CUSTOMER_NAME in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("CUSTOMER_NAME not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("CUSTOMER_NAME between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_NAME not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andAliasNameIsNull() {
            addCriterion("ALIAS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAliasNameIsNotNull() {
            addCriterion("ALIAS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAliasNameEqualTo(String value) {
            addCriterion("ALIAS_NAME =", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameNotEqualTo(String value) {
            addCriterion("ALIAS_NAME <>", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameGreaterThan(String value) {
            addCriterion("ALIAS_NAME >", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameGreaterThanOrEqualTo(String value) {
            addCriterion("ALIAS_NAME >=", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameLessThan(String value) {
            addCriterion("ALIAS_NAME <", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameLessThanOrEqualTo(String value) {
            addCriterion("ALIAS_NAME <=", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameLike(String value) {
            addCriterion("ALIAS_NAME like", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameNotLike(String value) {
            addCriterion("ALIAS_NAME not like", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameIn(List<String> values) {
            addCriterion("ALIAS_NAME in", values, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameNotIn(List<String> values) {
            addCriterion("ALIAS_NAME not in", values, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameBetween(String value1, String value2) {
            addCriterion("ALIAS_NAME between", value1, value2, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameNotBetween(String value1, String value2) {
            addCriterion("ALIAS_NAME not between", value1, value2, "aliasName");
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

        public Criteria andDevelopLanguageIsNull() {
            addCriterion("DEVELOP_LANGUAGE is null");
            return (Criteria) this;
        }

        public Criteria andDevelopLanguageIsNotNull() {
            addCriterion("DEVELOP_LANGUAGE is not null");
            return (Criteria) this;
        }

        public Criteria andDevelopLanguageEqualTo(String value) {
            addCriterion("DEVELOP_LANGUAGE =", value, "developLanguage");
            return (Criteria) this;
        }

        public Criteria andDevelopLanguageNotEqualTo(String value) {
            addCriterion("DEVELOP_LANGUAGE <>", value, "developLanguage");
            return (Criteria) this;
        }

        public Criteria andDevelopLanguageGreaterThan(String value) {
            addCriterion("DEVELOP_LANGUAGE >", value, "developLanguage");
            return (Criteria) this;
        }

        public Criteria andDevelopLanguageGreaterThanOrEqualTo(String value) {
            addCriterion("DEVELOP_LANGUAGE >=", value, "developLanguage");
            return (Criteria) this;
        }

        public Criteria andDevelopLanguageLessThan(String value) {
            addCriterion("DEVELOP_LANGUAGE <", value, "developLanguage");
            return (Criteria) this;
        }

        public Criteria andDevelopLanguageLessThanOrEqualTo(String value) {
            addCriterion("DEVELOP_LANGUAGE <=", value, "developLanguage");
            return (Criteria) this;
        }

        public Criteria andDevelopLanguageLike(String value) {
            addCriterion("DEVELOP_LANGUAGE like", value, "developLanguage");
            return (Criteria) this;
        }

        public Criteria andDevelopLanguageNotLike(String value) {
            addCriterion("DEVELOP_LANGUAGE not like", value, "developLanguage");
            return (Criteria) this;
        }

        public Criteria andDevelopLanguageIn(List<String> values) {
            addCriterion("DEVELOP_LANGUAGE in", values, "developLanguage");
            return (Criteria) this;
        }

        public Criteria andDevelopLanguageNotIn(List<String> values) {
            addCriterion("DEVELOP_LANGUAGE not in", values, "developLanguage");
            return (Criteria) this;
        }

        public Criteria andDevelopLanguageBetween(String value1, String value2) {
            addCriterion("DEVELOP_LANGUAGE between", value1, value2, "developLanguage");
            return (Criteria) this;
        }

        public Criteria andDevelopLanguageNotBetween(String value1, String value2) {
            addCriterion("DEVELOP_LANGUAGE not between", value1, value2, "developLanguage");
            return (Criteria) this;
        }

        public Criteria andMachineIdIsNull() {
            addCriterion("MACHINE_ID is null");
            return (Criteria) this;
        }

        public Criteria andMachineIdIsNotNull() {
            addCriterion("MACHINE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMachineIdEqualTo(String value) {
            addCriterion("MACHINE_ID =", value, "machineId");
            return (Criteria) this;
        }

        public Criteria andMachineIdNotEqualTo(String value) {
            addCriterion("MACHINE_ID <>", value, "machineId");
            return (Criteria) this;
        }

        public Criteria andMachineIdGreaterThan(String value) {
            addCriterion("MACHINE_ID >", value, "machineId");
            return (Criteria) this;
        }

        public Criteria andMachineIdGreaterThanOrEqualTo(String value) {
            addCriterion("MACHINE_ID >=", value, "machineId");
            return (Criteria) this;
        }

        public Criteria andMachineIdLessThan(String value) {
            addCriterion("MACHINE_ID <", value, "machineId");
            return (Criteria) this;
        }

        public Criteria andMachineIdLessThanOrEqualTo(String value) {
            addCriterion("MACHINE_ID <=", value, "machineId");
            return (Criteria) this;
        }

        public Criteria andMachineIdLike(String value) {
            addCriterion("MACHINE_ID like", value, "machineId");
            return (Criteria) this;
        }

        public Criteria andMachineIdNotLike(String value) {
            addCriterion("MACHINE_ID not like", value, "machineId");
            return (Criteria) this;
        }

        public Criteria andMachineIdIn(List<String> values) {
            addCriterion("MACHINE_ID in", values, "machineId");
            return (Criteria) this;
        }

        public Criteria andMachineIdNotIn(List<String> values) {
            addCriterion("MACHINE_ID not in", values, "machineId");
            return (Criteria) this;
        }

        public Criteria andMachineIdBetween(String value1, String value2) {
            addCriterion("MACHINE_ID between", value1, value2, "machineId");
            return (Criteria) this;
        }

        public Criteria andMachineIdNotBetween(String value1, String value2) {
            addCriterion("MACHINE_ID not between", value1, value2, "machineId");
            return (Criteria) this;
        }

        public Criteria andIssueDateIsNull() {
            addCriterion("ISSUE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andIssueDateIsNotNull() {
            addCriterion("ISSUE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andIssueDateEqualTo(String value) {
            addCriterion("ISSUE_DATE =", value, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateNotEqualTo(String value) {
            addCriterion("ISSUE_DATE <>", value, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateGreaterThan(String value) {
            addCriterion("ISSUE_DATE >", value, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateGreaterThanOrEqualTo(String value) {
            addCriterion("ISSUE_DATE >=", value, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateLessThan(String value) {
            addCriterion("ISSUE_DATE <", value, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateLessThanOrEqualTo(String value) {
            addCriterion("ISSUE_DATE <=", value, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateLike(String value) {
            addCriterion("ISSUE_DATE like", value, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateNotLike(String value) {
            addCriterion("ISSUE_DATE not like", value, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateIn(List<String> values) {
            addCriterion("ISSUE_DATE in", values, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateNotIn(List<String> values) {
            addCriterion("ISSUE_DATE not in", values, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateBetween(String value1, String value2) {
            addCriterion("ISSUE_DATE between", value1, value2, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateNotBetween(String value1, String value2) {
            addCriterion("ISSUE_DATE not between", value1, value2, "issueDate");
            return (Criteria) this;
        }

        public Criteria andVaildDateIsNull() {
            addCriterion("VAILD_DATE is null");
            return (Criteria) this;
        }

        public Criteria andVaildDateIsNotNull() {
            addCriterion("VAILD_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andVaildDateEqualTo(String value) {
            addCriterion("VAILD_DATE =", value, "vaildDate");
            return (Criteria) this;
        }

        public Criteria andVaildDateNotEqualTo(String value) {
            addCriterion("VAILD_DATE <>", value, "vaildDate");
            return (Criteria) this;
        }

        public Criteria andVaildDateGreaterThan(String value) {
            addCriterion("VAILD_DATE >", value, "vaildDate");
            return (Criteria) this;
        }

        public Criteria andVaildDateGreaterThanOrEqualTo(String value) {
            addCriterion("VAILD_DATE >=", value, "vaildDate");
            return (Criteria) this;
        }

        public Criteria andVaildDateLessThan(String value) {
            addCriterion("VAILD_DATE <", value, "vaildDate");
            return (Criteria) this;
        }

        public Criteria andVaildDateLessThanOrEqualTo(String value) {
            addCriterion("VAILD_DATE <=", value, "vaildDate");
            return (Criteria) this;
        }

        public Criteria andVaildDateLike(String value) {
            addCriterion("VAILD_DATE like", value, "vaildDate");
            return (Criteria) this;
        }

        public Criteria andVaildDateNotLike(String value) {
            addCriterion("VAILD_DATE not like", value, "vaildDate");
            return (Criteria) this;
        }

        public Criteria andVaildDateIn(List<String> values) {
            addCriterion("VAILD_DATE in", values, "vaildDate");
            return (Criteria) this;
        }

        public Criteria andVaildDateNotIn(List<String> values) {
            addCriterion("VAILD_DATE not in", values, "vaildDate");
            return (Criteria) this;
        }

        public Criteria andVaildDateBetween(String value1, String value2) {
            addCriterion("VAILD_DATE between", value1, value2, "vaildDate");
            return (Criteria) this;
        }

        public Criteria andVaildDateNotBetween(String value1, String value2) {
            addCriterion("VAILD_DATE not between", value1, value2, "vaildDate");
            return (Criteria) this;
        }

        public Criteria andWorkStationIdIsNull() {
            addCriterion("WORK_STATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andWorkStationIdIsNotNull() {
            addCriterion("WORK_STATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWorkStationIdEqualTo(String value) {
            addCriterion("WORK_STATION_ID =", value, "workStationId");
            return (Criteria) this;
        }

        public Criteria andWorkStationIdNotEqualTo(String value) {
            addCriterion("WORK_STATION_ID <>", value, "workStationId");
            return (Criteria) this;
        }

        public Criteria andWorkStationIdGreaterThan(String value) {
            addCriterion("WORK_STATION_ID >", value, "workStationId");
            return (Criteria) this;
        }

        public Criteria andWorkStationIdGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_STATION_ID >=", value, "workStationId");
            return (Criteria) this;
        }

        public Criteria andWorkStationIdLessThan(String value) {
            addCriterion("WORK_STATION_ID <", value, "workStationId");
            return (Criteria) this;
        }

        public Criteria andWorkStationIdLessThanOrEqualTo(String value) {
            addCriterion("WORK_STATION_ID <=", value, "workStationId");
            return (Criteria) this;
        }

        public Criteria andWorkStationIdLike(String value) {
            addCriterion("WORK_STATION_ID like", value, "workStationId");
            return (Criteria) this;
        }

        public Criteria andWorkStationIdNotLike(String value) {
            addCriterion("WORK_STATION_ID not like", value, "workStationId");
            return (Criteria) this;
        }

        public Criteria andWorkStationIdIn(List<String> values) {
            addCriterion("WORK_STATION_ID in", values, "workStationId");
            return (Criteria) this;
        }

        public Criteria andWorkStationIdNotIn(List<String> values) {
            addCriterion("WORK_STATION_ID not in", values, "workStationId");
            return (Criteria) this;
        }

        public Criteria andWorkStationIdBetween(String value1, String value2) {
            addCriterion("WORK_STATION_ID between", value1, value2, "workStationId");
            return (Criteria) this;
        }

        public Criteria andWorkStationIdNotBetween(String value1, String value2) {
            addCriterion("WORK_STATION_ID not between", value1, value2, "workStationId");
            return (Criteria) this;
        }

        public Criteria andEncryptFlagIsNull() {
            addCriterion("ENCRYPT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andEncryptFlagIsNotNull() {
            addCriterion("ENCRYPT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andEncryptFlagEqualTo(String value) {
            addCriterion("ENCRYPT_FLAG =", value, "encryptFlag");
            return (Criteria) this;
        }

        public Criteria andEncryptFlagNotEqualTo(String value) {
            addCriterion("ENCRYPT_FLAG <>", value, "encryptFlag");
            return (Criteria) this;
        }

        public Criteria andEncryptFlagGreaterThan(String value) {
            addCriterion("ENCRYPT_FLAG >", value, "encryptFlag");
            return (Criteria) this;
        }

        public Criteria andEncryptFlagGreaterThanOrEqualTo(String value) {
            addCriterion("ENCRYPT_FLAG >=", value, "encryptFlag");
            return (Criteria) this;
        }

        public Criteria andEncryptFlagLessThan(String value) {
            addCriterion("ENCRYPT_FLAG <", value, "encryptFlag");
            return (Criteria) this;
        }

        public Criteria andEncryptFlagLessThanOrEqualTo(String value) {
            addCriterion("ENCRYPT_FLAG <=", value, "encryptFlag");
            return (Criteria) this;
        }

        public Criteria andEncryptFlagLike(String value) {
            addCriterion("ENCRYPT_FLAG like", value, "encryptFlag");
            return (Criteria) this;
        }

        public Criteria andEncryptFlagNotLike(String value) {
            addCriterion("ENCRYPT_FLAG not like", value, "encryptFlag");
            return (Criteria) this;
        }

        public Criteria andEncryptFlagIn(List<String> values) {
            addCriterion("ENCRYPT_FLAG in", values, "encryptFlag");
            return (Criteria) this;
        }

        public Criteria andEncryptFlagNotIn(List<String> values) {
            addCriterion("ENCRYPT_FLAG not in", values, "encryptFlag");
            return (Criteria) this;
        }

        public Criteria andEncryptFlagBetween(String value1, String value2) {
            addCriterion("ENCRYPT_FLAG between", value1, value2, "encryptFlag");
            return (Criteria) this;
        }

        public Criteria andEncryptFlagNotBetween(String value1, String value2) {
            addCriterion("ENCRYPT_FLAG not between", value1, value2, "encryptFlag");
            return (Criteria) this;
        }

        public Criteria andEncryptNameIsNull() {
            addCriterion("ENCRYPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEncryptNameIsNotNull() {
            addCriterion("ENCRYPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEncryptNameEqualTo(String value) {
            addCriterion("ENCRYPT_NAME =", value, "encryptName");
            return (Criteria) this;
        }

        public Criteria andEncryptNameNotEqualTo(String value) {
            addCriterion("ENCRYPT_NAME <>", value, "encryptName");
            return (Criteria) this;
        }

        public Criteria andEncryptNameGreaterThan(String value) {
            addCriterion("ENCRYPT_NAME >", value, "encryptName");
            return (Criteria) this;
        }

        public Criteria andEncryptNameGreaterThanOrEqualTo(String value) {
            addCriterion("ENCRYPT_NAME >=", value, "encryptName");
            return (Criteria) this;
        }

        public Criteria andEncryptNameLessThan(String value) {
            addCriterion("ENCRYPT_NAME <", value, "encryptName");
            return (Criteria) this;
        }

        public Criteria andEncryptNameLessThanOrEqualTo(String value) {
            addCriterion("ENCRYPT_NAME <=", value, "encryptName");
            return (Criteria) this;
        }

        public Criteria andEncryptNameLike(String value) {
            addCriterion("ENCRYPT_NAME like", value, "encryptName");
            return (Criteria) this;
        }

        public Criteria andEncryptNameNotLike(String value) {
            addCriterion("ENCRYPT_NAME not like", value, "encryptName");
            return (Criteria) this;
        }

        public Criteria andEncryptNameIn(List<String> values) {
            addCriterion("ENCRYPT_NAME in", values, "encryptName");
            return (Criteria) this;
        }

        public Criteria andEncryptNameNotIn(List<String> values) {
            addCriterion("ENCRYPT_NAME not in", values, "encryptName");
            return (Criteria) this;
        }

        public Criteria andEncryptNameBetween(String value1, String value2) {
            addCriterion("ENCRYPT_NAME between", value1, value2, "encryptName");
            return (Criteria) this;
        }

        public Criteria andEncryptNameNotBetween(String value1, String value2) {
            addCriterion("ENCRYPT_NAME not between", value1, value2, "encryptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowIsNull() {
            addCriterion("APPLY_DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowIsNotNull() {
            addCriterion("APPLY_DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowEqualTo(String value) {
            addCriterion("APPLY_DEPT_FLOW =", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowNotEqualTo(String value) {
            addCriterion("APPLY_DEPT_FLOW <>", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowGreaterThan(String value) {
            addCriterion("APPLY_DEPT_FLOW >", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_DEPT_FLOW >=", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowLessThan(String value) {
            addCriterion("APPLY_DEPT_FLOW <", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_DEPT_FLOW <=", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowLike(String value) {
            addCriterion("APPLY_DEPT_FLOW like", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowNotLike(String value) {
            addCriterion("APPLY_DEPT_FLOW not like", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowIn(List<String> values) {
            addCriterion("APPLY_DEPT_FLOW in", values, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowNotIn(List<String> values) {
            addCriterion("APPLY_DEPT_FLOW not in", values, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowBetween(String value1, String value2) {
            addCriterion("APPLY_DEPT_FLOW between", value1, value2, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_DEPT_FLOW not between", value1, value2, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameIsNull() {
            addCriterion("APPLY_DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameIsNotNull() {
            addCriterion("APPLY_DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameEqualTo(String value) {
            addCriterion("APPLY_DEPT_NAME =", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameNotEqualTo(String value) {
            addCriterion("APPLY_DEPT_NAME <>", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameGreaterThan(String value) {
            addCriterion("APPLY_DEPT_NAME >", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_DEPT_NAME >=", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameLessThan(String value) {
            addCriterion("APPLY_DEPT_NAME <", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameLessThanOrEqualTo(String value) {
            addCriterion("APPLY_DEPT_NAME <=", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameLike(String value) {
            addCriterion("APPLY_DEPT_NAME like", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameNotLike(String value) {
            addCriterion("APPLY_DEPT_NAME not like", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameIn(List<String> values) {
            addCriterion("APPLY_DEPT_NAME in", values, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameNotIn(List<String> values) {
            addCriterion("APPLY_DEPT_NAME not in", values, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameBetween(String value1, String value2) {
            addCriterion("APPLY_DEPT_NAME between", value1, value2, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameNotBetween(String value1, String value2) {
            addCriterion("APPLY_DEPT_NAME not between", value1, value2, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIsNull() {
            addCriterion("APPLY_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIsNotNull() {
            addCriterion("APPLY_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW =", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW <>", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowGreaterThan(String value) {
            addCriterion("APPLY_USER_FLOW >", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW >=", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLessThan(String value) {
            addCriterion("APPLY_USER_FLOW <", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW <=", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLike(String value) {
            addCriterion("APPLY_USER_FLOW like", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotLike(String value) {
            addCriterion("APPLY_USER_FLOW not like", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIn(List<String> values) {
            addCriterion("APPLY_USER_FLOW in", values, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotIn(List<String> values) {
            addCriterion("APPLY_USER_FLOW not in", values, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowBetween(String value1, String value2) {
            addCriterion("APPLY_USER_FLOW between", value1, value2, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_FLOW not between", value1, value2, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIsNull() {
            addCriterion("APPLY_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIsNotNull() {
            addCriterion("APPLY_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameEqualTo(String value) {
            addCriterion("APPLY_USER_NAME =", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotEqualTo(String value) {
            addCriterion("APPLY_USER_NAME <>", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameGreaterThan(String value) {
            addCriterion("APPLY_USER_NAME >", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_NAME >=", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLessThan(String value) {
            addCriterion("APPLY_USER_NAME <", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_NAME <=", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLike(String value) {
            addCriterion("APPLY_USER_NAME like", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotLike(String value) {
            addCriterion("APPLY_USER_NAME not like", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIn(List<String> values) {
            addCriterion("APPLY_USER_NAME in", values, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotIn(List<String> values) {
            addCriterion("APPLY_USER_NAME not in", values, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameBetween(String value1, String value2) {
            addCriterion("APPLY_USER_NAME between", value1, value2, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_NAME not between", value1, value2, "applyUserName");
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

        public Criteria andQuestionFlagIsNull() {
            addCriterion("QUESTION_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andQuestionFlagIsNotNull() {
            addCriterion("QUESTION_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionFlagEqualTo(String value) {
            addCriterion("QUESTION_FLAG =", value, "questionFlag");
            return (Criteria) this;
        }

        public Criteria andQuestionFlagNotEqualTo(String value) {
            addCriterion("QUESTION_FLAG <>", value, "questionFlag");
            return (Criteria) this;
        }

        public Criteria andQuestionFlagGreaterThan(String value) {
            addCriterion("QUESTION_FLAG >", value, "questionFlag");
            return (Criteria) this;
        }

        public Criteria andQuestionFlagGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTION_FLAG >=", value, "questionFlag");
            return (Criteria) this;
        }

        public Criteria andQuestionFlagLessThan(String value) {
            addCriterion("QUESTION_FLAG <", value, "questionFlag");
            return (Criteria) this;
        }

        public Criteria andQuestionFlagLessThanOrEqualTo(String value) {
            addCriterion("QUESTION_FLAG <=", value, "questionFlag");
            return (Criteria) this;
        }

        public Criteria andQuestionFlagLike(String value) {
            addCriterion("QUESTION_FLAG like", value, "questionFlag");
            return (Criteria) this;
        }

        public Criteria andQuestionFlagNotLike(String value) {
            addCriterion("QUESTION_FLAG not like", value, "questionFlag");
            return (Criteria) this;
        }

        public Criteria andQuestionFlagIn(List<String> values) {
            addCriterion("QUESTION_FLAG in", values, "questionFlag");
            return (Criteria) this;
        }

        public Criteria andQuestionFlagNotIn(List<String> values) {
            addCriterion("QUESTION_FLAG not in", values, "questionFlag");
            return (Criteria) this;
        }

        public Criteria andQuestionFlagBetween(String value1, String value2) {
            addCriterion("QUESTION_FLAG between", value1, value2, "questionFlag");
            return (Criteria) this;
        }

        public Criteria andQuestionFlagNotBetween(String value1, String value2) {
            addCriterion("QUESTION_FLAG not between", value1, value2, "questionFlag");
            return (Criteria) this;
        }

        public Criteria andWorkStationNameIsNull() {
            addCriterion("WORK_STATION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andWorkStationNameIsNotNull() {
            addCriterion("WORK_STATION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andWorkStationNameEqualTo(String value) {
            addCriterion("WORK_STATION_NAME =", value, "workStationName");
            return (Criteria) this;
        }

        public Criteria andWorkStationNameNotEqualTo(String value) {
            addCriterion("WORK_STATION_NAME <>", value, "workStationName");
            return (Criteria) this;
        }

        public Criteria andWorkStationNameGreaterThan(String value) {
            addCriterion("WORK_STATION_NAME >", value, "workStationName");
            return (Criteria) this;
        }

        public Criteria andWorkStationNameGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_STATION_NAME >=", value, "workStationName");
            return (Criteria) this;
        }

        public Criteria andWorkStationNameLessThan(String value) {
            addCriterion("WORK_STATION_NAME <", value, "workStationName");
            return (Criteria) this;
        }

        public Criteria andWorkStationNameLessThanOrEqualTo(String value) {
            addCriterion("WORK_STATION_NAME <=", value, "workStationName");
            return (Criteria) this;
        }

        public Criteria andWorkStationNameLike(String value) {
            addCriterion("WORK_STATION_NAME like", value, "workStationName");
            return (Criteria) this;
        }

        public Criteria andWorkStationNameNotLike(String value) {
            addCriterion("WORK_STATION_NAME not like", value, "workStationName");
            return (Criteria) this;
        }

        public Criteria andWorkStationNameIn(List<String> values) {
            addCriterion("WORK_STATION_NAME in", values, "workStationName");
            return (Criteria) this;
        }

        public Criteria andWorkStationNameNotIn(List<String> values) {
            addCriterion("WORK_STATION_NAME not in", values, "workStationName");
            return (Criteria) this;
        }

        public Criteria andWorkStationNameBetween(String value1, String value2) {
            addCriterion("WORK_STATION_NAME between", value1, value2, "workStationName");
            return (Criteria) this;
        }

        public Criteria andWorkStationNameNotBetween(String value1, String value2) {
            addCriterion("WORK_STATION_NAME not between", value1, value2, "workStationName");
            return (Criteria) this;
        }

        public Criteria andLicContentIsNull() {
            addCriterion("LIC_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andLicContentIsNotNull() {
            addCriterion("LIC_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andLicContentEqualTo(String value) {
            addCriterion("LIC_CONTENT =", value, "licContent");
            return (Criteria) this;
        }

        public Criteria andLicContentNotEqualTo(String value) {
            addCriterion("LIC_CONTENT <>", value, "licContent");
            return (Criteria) this;
        }

        public Criteria andLicContentGreaterThan(String value) {
            addCriterion("LIC_CONTENT >", value, "licContent");
            return (Criteria) this;
        }

        public Criteria andLicContentGreaterThanOrEqualTo(String value) {
            addCriterion("LIC_CONTENT >=", value, "licContent");
            return (Criteria) this;
        }

        public Criteria andLicContentLessThan(String value) {
            addCriterion("LIC_CONTENT <", value, "licContent");
            return (Criteria) this;
        }

        public Criteria andLicContentLessThanOrEqualTo(String value) {
            addCriterion("LIC_CONTENT <=", value, "licContent");
            return (Criteria) this;
        }

        public Criteria andLicContentLike(String value) {
            addCriterion("LIC_CONTENT like", value, "licContent");
            return (Criteria) this;
        }

        public Criteria andLicContentNotLike(String value) {
            addCriterion("LIC_CONTENT not like", value, "licContent");
            return (Criteria) this;
        }

        public Criteria andLicContentIn(List<String> values) {
            addCriterion("LIC_CONTENT in", values, "licContent");
            return (Criteria) this;
        }

        public Criteria andLicContentNotIn(List<String> values) {
            addCriterion("LIC_CONTENT not in", values, "licContent");
            return (Criteria) this;
        }

        public Criteria andLicContentBetween(String value1, String value2) {
            addCriterion("LIC_CONTENT between", value1, value2, "licContent");
            return (Criteria) this;
        }

        public Criteria andLicContentNotBetween(String value1, String value2) {
            addCriterion("LIC_CONTENT not between", value1, value2, "licContent");
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