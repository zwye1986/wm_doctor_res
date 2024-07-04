package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ErpOaContactOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ErpOaContactOrderExample() {
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

        public Criteria andContactFlowIsNull() {
            addCriterion("CONTACT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andContactFlowIsNotNull() {
            addCriterion("CONTACT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andContactFlowEqualTo(String value) {
            addCriterion("CONTACT_FLOW =", value, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowNotEqualTo(String value) {
            addCriterion("CONTACT_FLOW <>", value, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowGreaterThan(String value) {
            addCriterion("CONTACT_FLOW >", value, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_FLOW >=", value, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowLessThan(String value) {
            addCriterion("CONTACT_FLOW <", value, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_FLOW <=", value, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowLike(String value) {
            addCriterion("CONTACT_FLOW like", value, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowNotLike(String value) {
            addCriterion("CONTACT_FLOW not like", value, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowIn(List<String> values) {
            addCriterion("CONTACT_FLOW in", values, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowNotIn(List<String> values) {
            addCriterion("CONTACT_FLOW not in", values, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowBetween(String value1, String value2) {
            addCriterion("CONTACT_FLOW between", value1, value2, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowNotBetween(String value1, String value2) {
            addCriterion("CONTACT_FLOW not between", value1, value2, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactNoIsNull() {
            addCriterion("CONTACT_NO is null");
            return (Criteria) this;
        }

        public Criteria andContactNoIsNotNull() {
            addCriterion("CONTACT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andContactNoEqualTo(String value) {
            addCriterion("CONTACT_NO =", value, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoNotEqualTo(String value) {
            addCriterion("CONTACT_NO <>", value, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoGreaterThan(String value) {
            addCriterion("CONTACT_NO >", value, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_NO >=", value, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoLessThan(String value) {
            addCriterion("CONTACT_NO <", value, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_NO <=", value, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoLike(String value) {
            addCriterion("CONTACT_NO like", value, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoNotLike(String value) {
            addCriterion("CONTACT_NO not like", value, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoIn(List<String> values) {
            addCriterion("CONTACT_NO in", values, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoNotIn(List<String> values) {
            addCriterion("CONTACT_NO not in", values, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoBetween(String value1, String value2) {
            addCriterion("CONTACT_NO between", value1, value2, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoNotBetween(String value1, String value2) {
            addCriterion("CONTACT_NO not between", value1, value2, "contactNo");
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

        public Criteria andDemandMatterIdIsNull() {
            addCriterion("DEMAND_MATTER_ID is null");
            return (Criteria) this;
        }

        public Criteria andDemandMatterIdIsNotNull() {
            addCriterion("DEMAND_MATTER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDemandMatterIdEqualTo(String value) {
            addCriterion("DEMAND_MATTER_ID =", value, "demandMatterId");
            return (Criteria) this;
        }

        public Criteria andDemandMatterIdNotEqualTo(String value) {
            addCriterion("DEMAND_MATTER_ID <>", value, "demandMatterId");
            return (Criteria) this;
        }

        public Criteria andDemandMatterIdGreaterThan(String value) {
            addCriterion("DEMAND_MATTER_ID >", value, "demandMatterId");
            return (Criteria) this;
        }

        public Criteria andDemandMatterIdGreaterThanOrEqualTo(String value) {
            addCriterion("DEMAND_MATTER_ID >=", value, "demandMatterId");
            return (Criteria) this;
        }

        public Criteria andDemandMatterIdLessThan(String value) {
            addCriterion("DEMAND_MATTER_ID <", value, "demandMatterId");
            return (Criteria) this;
        }

        public Criteria andDemandMatterIdLessThanOrEqualTo(String value) {
            addCriterion("DEMAND_MATTER_ID <=", value, "demandMatterId");
            return (Criteria) this;
        }

        public Criteria andDemandMatterIdLike(String value) {
            addCriterion("DEMAND_MATTER_ID like", value, "demandMatterId");
            return (Criteria) this;
        }

        public Criteria andDemandMatterIdNotLike(String value) {
            addCriterion("DEMAND_MATTER_ID not like", value, "demandMatterId");
            return (Criteria) this;
        }

        public Criteria andDemandMatterIdIn(List<String> values) {
            addCriterion("DEMAND_MATTER_ID in", values, "demandMatterId");
            return (Criteria) this;
        }

        public Criteria andDemandMatterIdNotIn(List<String> values) {
            addCriterion("DEMAND_MATTER_ID not in", values, "demandMatterId");
            return (Criteria) this;
        }

        public Criteria andDemandMatterIdBetween(String value1, String value2) {
            addCriterion("DEMAND_MATTER_ID between", value1, value2, "demandMatterId");
            return (Criteria) this;
        }

        public Criteria andDemandMatterIdNotBetween(String value1, String value2) {
            addCriterion("DEMAND_MATTER_ID not between", value1, value2, "demandMatterId");
            return (Criteria) this;
        }

        public Criteria andDemandMatterNameIsNull() {
            addCriterion("DEMAND_MATTER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDemandMatterNameIsNotNull() {
            addCriterion("DEMAND_MATTER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDemandMatterNameEqualTo(String value) {
            addCriterion("DEMAND_MATTER_NAME =", value, "demandMatterName");
            return (Criteria) this;
        }

        public Criteria andDemandMatterNameNotEqualTo(String value) {
            addCriterion("DEMAND_MATTER_NAME <>", value, "demandMatterName");
            return (Criteria) this;
        }

        public Criteria andDemandMatterNameGreaterThan(String value) {
            addCriterion("DEMAND_MATTER_NAME >", value, "demandMatterName");
            return (Criteria) this;
        }

        public Criteria andDemandMatterNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEMAND_MATTER_NAME >=", value, "demandMatterName");
            return (Criteria) this;
        }

        public Criteria andDemandMatterNameLessThan(String value) {
            addCriterion("DEMAND_MATTER_NAME <", value, "demandMatterName");
            return (Criteria) this;
        }

        public Criteria andDemandMatterNameLessThanOrEqualTo(String value) {
            addCriterion("DEMAND_MATTER_NAME <=", value, "demandMatterName");
            return (Criteria) this;
        }

        public Criteria andDemandMatterNameLike(String value) {
            addCriterion("DEMAND_MATTER_NAME like", value, "demandMatterName");
            return (Criteria) this;
        }

        public Criteria andDemandMatterNameNotLike(String value) {
            addCriterion("DEMAND_MATTER_NAME not like", value, "demandMatterName");
            return (Criteria) this;
        }

        public Criteria andDemandMatterNameIn(List<String> values) {
            addCriterion("DEMAND_MATTER_NAME in", values, "demandMatterName");
            return (Criteria) this;
        }

        public Criteria andDemandMatterNameNotIn(List<String> values) {
            addCriterion("DEMAND_MATTER_NAME not in", values, "demandMatterName");
            return (Criteria) this;
        }

        public Criteria andDemandMatterNameBetween(String value1, String value2) {
            addCriterion("DEMAND_MATTER_NAME between", value1, value2, "demandMatterName");
            return (Criteria) this;
        }

        public Criteria andDemandMatterNameNotBetween(String value1, String value2) {
            addCriterion("DEMAND_MATTER_NAME not between", value1, value2, "demandMatterName");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIdIsNull() {
            addCriterion("SERVICE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIdIsNotNull() {
            addCriterion("SERVICE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIdEqualTo(String value) {
            addCriterion("SERVICE_TYPE_ID =", value, "serviceTypeId");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIdNotEqualTo(String value) {
            addCriterion("SERVICE_TYPE_ID <>", value, "serviceTypeId");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIdGreaterThan(String value) {
            addCriterion("SERVICE_TYPE_ID >", value, "serviceTypeId");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SERVICE_TYPE_ID >=", value, "serviceTypeId");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIdLessThan(String value) {
            addCriterion("SERVICE_TYPE_ID <", value, "serviceTypeId");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIdLessThanOrEqualTo(String value) {
            addCriterion("SERVICE_TYPE_ID <=", value, "serviceTypeId");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIdLike(String value) {
            addCriterion("SERVICE_TYPE_ID like", value, "serviceTypeId");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIdNotLike(String value) {
            addCriterion("SERVICE_TYPE_ID not like", value, "serviceTypeId");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIdIn(List<String> values) {
            addCriterion("SERVICE_TYPE_ID in", values, "serviceTypeId");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIdNotIn(List<String> values) {
            addCriterion("SERVICE_TYPE_ID not in", values, "serviceTypeId");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIdBetween(String value1, String value2) {
            addCriterion("SERVICE_TYPE_ID between", value1, value2, "serviceTypeId");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIdNotBetween(String value1, String value2) {
            addCriterion("SERVICE_TYPE_ID not between", value1, value2, "serviceTypeId");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNameIsNull() {
            addCriterion("SERVICE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNameIsNotNull() {
            addCriterion("SERVICE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNameEqualTo(String value) {
            addCriterion("SERVICE_TYPE_NAME =", value, "serviceTypeName");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNameNotEqualTo(String value) {
            addCriterion("SERVICE_TYPE_NAME <>", value, "serviceTypeName");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNameGreaterThan(String value) {
            addCriterion("SERVICE_TYPE_NAME >", value, "serviceTypeName");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SERVICE_TYPE_NAME >=", value, "serviceTypeName");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNameLessThan(String value) {
            addCriterion("SERVICE_TYPE_NAME <", value, "serviceTypeName");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNameLessThanOrEqualTo(String value) {
            addCriterion("SERVICE_TYPE_NAME <=", value, "serviceTypeName");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNameLike(String value) {
            addCriterion("SERVICE_TYPE_NAME like", value, "serviceTypeName");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNameNotLike(String value) {
            addCriterion("SERVICE_TYPE_NAME not like", value, "serviceTypeName");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNameIn(List<String> values) {
            addCriterion("SERVICE_TYPE_NAME in", values, "serviceTypeName");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNameNotIn(List<String> values) {
            addCriterion("SERVICE_TYPE_NAME not in", values, "serviceTypeName");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNameBetween(String value1, String value2) {
            addCriterion("SERVICE_TYPE_NAME between", value1, value2, "serviceTypeName");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNameNotBetween(String value1, String value2) {
            addCriterion("SERVICE_TYPE_NAME not between", value1, value2, "serviceTypeName");
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

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andDemandDateIsNull() {
            addCriterion("DEMAND_DATE is null");
            return (Criteria) this;
        }

        public Criteria andDemandDateIsNotNull() {
            addCriterion("DEMAND_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andDemandDateEqualTo(String value) {
            addCriterion("DEMAND_DATE =", value, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateNotEqualTo(String value) {
            addCriterion("DEMAND_DATE <>", value, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateGreaterThan(String value) {
            addCriterion("DEMAND_DATE >", value, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateGreaterThanOrEqualTo(String value) {
            addCriterion("DEMAND_DATE >=", value, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateLessThan(String value) {
            addCriterion("DEMAND_DATE <", value, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateLessThanOrEqualTo(String value) {
            addCriterion("DEMAND_DATE <=", value, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateLike(String value) {
            addCriterion("DEMAND_DATE like", value, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateNotLike(String value) {
            addCriterion("DEMAND_DATE not like", value, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateIn(List<String> values) {
            addCriterion("DEMAND_DATE in", values, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateNotIn(List<String> values) {
            addCriterion("DEMAND_DATE not in", values, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateBetween(String value1, String value2) {
            addCriterion("DEMAND_DATE between", value1, value2, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandDateNotBetween(String value1, String value2) {
            addCriterion("DEMAND_DATE not between", value1, value2, "demandDate");
            return (Criteria) this;
        }

        public Criteria andDemandStatusIdIsNull() {
            addCriterion("DEMAND_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andDemandStatusIdIsNotNull() {
            addCriterion("DEMAND_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDemandStatusIdEqualTo(String value) {
            addCriterion("DEMAND_STATUS_ID =", value, "demandStatusId");
            return (Criteria) this;
        }

        public Criteria andDemandStatusIdNotEqualTo(String value) {
            addCriterion("DEMAND_STATUS_ID <>", value, "demandStatusId");
            return (Criteria) this;
        }

        public Criteria andDemandStatusIdGreaterThan(String value) {
            addCriterion("DEMAND_STATUS_ID >", value, "demandStatusId");
            return (Criteria) this;
        }

        public Criteria andDemandStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("DEMAND_STATUS_ID >=", value, "demandStatusId");
            return (Criteria) this;
        }

        public Criteria andDemandStatusIdLessThan(String value) {
            addCriterion("DEMAND_STATUS_ID <", value, "demandStatusId");
            return (Criteria) this;
        }

        public Criteria andDemandStatusIdLessThanOrEqualTo(String value) {
            addCriterion("DEMAND_STATUS_ID <=", value, "demandStatusId");
            return (Criteria) this;
        }

        public Criteria andDemandStatusIdLike(String value) {
            addCriterion("DEMAND_STATUS_ID like", value, "demandStatusId");
            return (Criteria) this;
        }

        public Criteria andDemandStatusIdNotLike(String value) {
            addCriterion("DEMAND_STATUS_ID not like", value, "demandStatusId");
            return (Criteria) this;
        }

        public Criteria andDemandStatusIdIn(List<String> values) {
            addCriterion("DEMAND_STATUS_ID in", values, "demandStatusId");
            return (Criteria) this;
        }

        public Criteria andDemandStatusIdNotIn(List<String> values) {
            addCriterion("DEMAND_STATUS_ID not in", values, "demandStatusId");
            return (Criteria) this;
        }

        public Criteria andDemandStatusIdBetween(String value1, String value2) {
            addCriterion("DEMAND_STATUS_ID between", value1, value2, "demandStatusId");
            return (Criteria) this;
        }

        public Criteria andDemandStatusIdNotBetween(String value1, String value2) {
            addCriterion("DEMAND_STATUS_ID not between", value1, value2, "demandStatusId");
            return (Criteria) this;
        }

        public Criteria andDemandStatusNameIsNull() {
            addCriterion("DEMAND_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDemandStatusNameIsNotNull() {
            addCriterion("DEMAND_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDemandStatusNameEqualTo(String value) {
            addCriterion("DEMAND_STATUS_NAME =", value, "demandStatusName");
            return (Criteria) this;
        }

        public Criteria andDemandStatusNameNotEqualTo(String value) {
            addCriterion("DEMAND_STATUS_NAME <>", value, "demandStatusName");
            return (Criteria) this;
        }

        public Criteria andDemandStatusNameGreaterThan(String value) {
            addCriterion("DEMAND_STATUS_NAME >", value, "demandStatusName");
            return (Criteria) this;
        }

        public Criteria andDemandStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEMAND_STATUS_NAME >=", value, "demandStatusName");
            return (Criteria) this;
        }

        public Criteria andDemandStatusNameLessThan(String value) {
            addCriterion("DEMAND_STATUS_NAME <", value, "demandStatusName");
            return (Criteria) this;
        }

        public Criteria andDemandStatusNameLessThanOrEqualTo(String value) {
            addCriterion("DEMAND_STATUS_NAME <=", value, "demandStatusName");
            return (Criteria) this;
        }

        public Criteria andDemandStatusNameLike(String value) {
            addCriterion("DEMAND_STATUS_NAME like", value, "demandStatusName");
            return (Criteria) this;
        }

        public Criteria andDemandStatusNameNotLike(String value) {
            addCriterion("DEMAND_STATUS_NAME not like", value, "demandStatusName");
            return (Criteria) this;
        }

        public Criteria andDemandStatusNameIn(List<String> values) {
            addCriterion("DEMAND_STATUS_NAME in", values, "demandStatusName");
            return (Criteria) this;
        }

        public Criteria andDemandStatusNameNotIn(List<String> values) {
            addCriterion("DEMAND_STATUS_NAME not in", values, "demandStatusName");
            return (Criteria) this;
        }

        public Criteria andDemandStatusNameBetween(String value1, String value2) {
            addCriterion("DEMAND_STATUS_NAME between", value1, value2, "demandStatusName");
            return (Criteria) this;
        }

        public Criteria andDemandStatusNameNotBetween(String value1, String value2) {
            addCriterion("DEMAND_STATUS_NAME not between", value1, value2, "demandStatusName");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNull() {
            addCriterion("APPLY_DATE is null");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNotNull() {
            addCriterion("APPLY_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andApplyDateEqualTo(String value) {
            addCriterion("APPLY_DATE =", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotEqualTo(String value) {
            addCriterion("APPLY_DATE <>", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThan(String value) {
            addCriterion("APPLY_DATE >", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_DATE >=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThan(String value) {
            addCriterion("APPLY_DATE <", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThanOrEqualTo(String value) {
            addCriterion("APPLY_DATE <=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLike(String value) {
            addCriterion("APPLY_DATE like", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotLike(String value) {
            addCriterion("APPLY_DATE not like", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateIn(List<String> values) {
            addCriterion("APPLY_DATE in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotIn(List<String> values) {
            addCriterion("APPLY_DATE not in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateBetween(String value1, String value2) {
            addCriterion("APPLY_DATE between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotBetween(String value1, String value2) {
            addCriterion("APPLY_DATE not between", value1, value2, "applyDate");
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

        public Criteria andContactStatusIdIsNull() {
            addCriterion("CONTACT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andContactStatusIdIsNotNull() {
            addCriterion("CONTACT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andContactStatusIdEqualTo(String value) {
            addCriterion("CONTACT_STATUS_ID =", value, "contactStatusId");
            return (Criteria) this;
        }

        public Criteria andContactStatusIdNotEqualTo(String value) {
            addCriterion("CONTACT_STATUS_ID <>", value, "contactStatusId");
            return (Criteria) this;
        }

        public Criteria andContactStatusIdGreaterThan(String value) {
            addCriterion("CONTACT_STATUS_ID >", value, "contactStatusId");
            return (Criteria) this;
        }

        public Criteria andContactStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_STATUS_ID >=", value, "contactStatusId");
            return (Criteria) this;
        }

        public Criteria andContactStatusIdLessThan(String value) {
            addCriterion("CONTACT_STATUS_ID <", value, "contactStatusId");
            return (Criteria) this;
        }

        public Criteria andContactStatusIdLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_STATUS_ID <=", value, "contactStatusId");
            return (Criteria) this;
        }

        public Criteria andContactStatusIdLike(String value) {
            addCriterion("CONTACT_STATUS_ID like", value, "contactStatusId");
            return (Criteria) this;
        }

        public Criteria andContactStatusIdNotLike(String value) {
            addCriterion("CONTACT_STATUS_ID not like", value, "contactStatusId");
            return (Criteria) this;
        }

        public Criteria andContactStatusIdIn(List<String> values) {
            addCriterion("CONTACT_STATUS_ID in", values, "contactStatusId");
            return (Criteria) this;
        }

        public Criteria andContactStatusIdNotIn(List<String> values) {
            addCriterion("CONTACT_STATUS_ID not in", values, "contactStatusId");
            return (Criteria) this;
        }

        public Criteria andContactStatusIdBetween(String value1, String value2) {
            addCriterion("CONTACT_STATUS_ID between", value1, value2, "contactStatusId");
            return (Criteria) this;
        }

        public Criteria andContactStatusIdNotBetween(String value1, String value2) {
            addCriterion("CONTACT_STATUS_ID not between", value1, value2, "contactStatusId");
            return (Criteria) this;
        }

        public Criteria andContactStatusNameIsNull() {
            addCriterion("CONTACT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andContactStatusNameIsNotNull() {
            addCriterion("CONTACT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andContactStatusNameEqualTo(String value) {
            addCriterion("CONTACT_STATUS_NAME =", value, "contactStatusName");
            return (Criteria) this;
        }

        public Criteria andContactStatusNameNotEqualTo(String value) {
            addCriterion("CONTACT_STATUS_NAME <>", value, "contactStatusName");
            return (Criteria) this;
        }

        public Criteria andContactStatusNameGreaterThan(String value) {
            addCriterion("CONTACT_STATUS_NAME >", value, "contactStatusName");
            return (Criteria) this;
        }

        public Criteria andContactStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_STATUS_NAME >=", value, "contactStatusName");
            return (Criteria) this;
        }

        public Criteria andContactStatusNameLessThan(String value) {
            addCriterion("CONTACT_STATUS_NAME <", value, "contactStatusName");
            return (Criteria) this;
        }

        public Criteria andContactStatusNameLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_STATUS_NAME <=", value, "contactStatusName");
            return (Criteria) this;
        }

        public Criteria andContactStatusNameLike(String value) {
            addCriterion("CONTACT_STATUS_NAME like", value, "contactStatusName");
            return (Criteria) this;
        }

        public Criteria andContactStatusNameNotLike(String value) {
            addCriterion("CONTACT_STATUS_NAME not like", value, "contactStatusName");
            return (Criteria) this;
        }

        public Criteria andContactStatusNameIn(List<String> values) {
            addCriterion("CONTACT_STATUS_NAME in", values, "contactStatusName");
            return (Criteria) this;
        }

        public Criteria andContactStatusNameNotIn(List<String> values) {
            addCriterion("CONTACT_STATUS_NAME not in", values, "contactStatusName");
            return (Criteria) this;
        }

        public Criteria andContactStatusNameBetween(String value1, String value2) {
            addCriterion("CONTACT_STATUS_NAME between", value1, value2, "contactStatusName");
            return (Criteria) this;
        }

        public Criteria andContactStatusNameNotBetween(String value1, String value2) {
            addCriterion("CONTACT_STATUS_NAME not between", value1, value2, "contactStatusName");
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

        public Criteria andReceiveDeptFlowIsNull() {
            addCriterion("RECEIVE_DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptFlowIsNotNull() {
            addCriterion("RECEIVE_DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptFlowEqualTo(String value) {
            addCriterion("RECEIVE_DEPT_FLOW =", value, "receiveDeptFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptFlowNotEqualTo(String value) {
            addCriterion("RECEIVE_DEPT_FLOW <>", value, "receiveDeptFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptFlowGreaterThan(String value) {
            addCriterion("RECEIVE_DEPT_FLOW >", value, "receiveDeptFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVE_DEPT_FLOW >=", value, "receiveDeptFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptFlowLessThan(String value) {
            addCriterion("RECEIVE_DEPT_FLOW <", value, "receiveDeptFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("RECEIVE_DEPT_FLOW <=", value, "receiveDeptFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptFlowLike(String value) {
            addCriterion("RECEIVE_DEPT_FLOW like", value, "receiveDeptFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptFlowNotLike(String value) {
            addCriterion("RECEIVE_DEPT_FLOW not like", value, "receiveDeptFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptFlowIn(List<String> values) {
            addCriterion("RECEIVE_DEPT_FLOW in", values, "receiveDeptFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptFlowNotIn(List<String> values) {
            addCriterion("RECEIVE_DEPT_FLOW not in", values, "receiveDeptFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptFlowBetween(String value1, String value2) {
            addCriterion("RECEIVE_DEPT_FLOW between", value1, value2, "receiveDeptFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptFlowNotBetween(String value1, String value2) {
            addCriterion("RECEIVE_DEPT_FLOW not between", value1, value2, "receiveDeptFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptNameIsNull() {
            addCriterion("RECEIVE_DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptNameIsNotNull() {
            addCriterion("RECEIVE_DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptNameEqualTo(String value) {
            addCriterion("RECEIVE_DEPT_NAME =", value, "receiveDeptName");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptNameNotEqualTo(String value) {
            addCriterion("RECEIVE_DEPT_NAME <>", value, "receiveDeptName");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptNameGreaterThan(String value) {
            addCriterion("RECEIVE_DEPT_NAME >", value, "receiveDeptName");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVE_DEPT_NAME >=", value, "receiveDeptName");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptNameLessThan(String value) {
            addCriterion("RECEIVE_DEPT_NAME <", value, "receiveDeptName");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptNameLessThanOrEqualTo(String value) {
            addCriterion("RECEIVE_DEPT_NAME <=", value, "receiveDeptName");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptNameLike(String value) {
            addCriterion("RECEIVE_DEPT_NAME like", value, "receiveDeptName");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptNameNotLike(String value) {
            addCriterion("RECEIVE_DEPT_NAME not like", value, "receiveDeptName");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptNameIn(List<String> values) {
            addCriterion("RECEIVE_DEPT_NAME in", values, "receiveDeptName");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptNameNotIn(List<String> values) {
            addCriterion("RECEIVE_DEPT_NAME not in", values, "receiveDeptName");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptNameBetween(String value1, String value2) {
            addCriterion("RECEIVE_DEPT_NAME between", value1, value2, "receiveDeptName");
            return (Criteria) this;
        }

        public Criteria andReceiveDeptNameNotBetween(String value1, String value2) {
            addCriterion("RECEIVE_DEPT_NAME not between", value1, value2, "receiveDeptName");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeIsNull() {
            addCriterion("COMPLETE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeIsNotNull() {
            addCriterion("COMPLETE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeEqualTo(String value) {
            addCriterion("COMPLETE_TIME =", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeNotEqualTo(String value) {
            addCriterion("COMPLETE_TIME <>", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeGreaterThan(String value) {
            addCriterion("COMPLETE_TIME >", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLETE_TIME >=", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeLessThan(String value) {
            addCriterion("COMPLETE_TIME <", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeLessThanOrEqualTo(String value) {
            addCriterion("COMPLETE_TIME <=", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeLike(String value) {
            addCriterion("COMPLETE_TIME like", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeNotLike(String value) {
            addCriterion("COMPLETE_TIME not like", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeIn(List<String> values) {
            addCriterion("COMPLETE_TIME in", values, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeNotIn(List<String> values) {
            addCriterion("COMPLETE_TIME not in", values, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeBetween(String value1, String value2) {
            addCriterion("COMPLETE_TIME between", value1, value2, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeNotBetween(String value1, String value2) {
            addCriterion("COMPLETE_TIME not between", value1, value2, "completeTime");
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

        public Criteria andReceiveUserFlowIsNull() {
            addCriterion("RECEIVE_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andReceiveUserFlowIsNotNull() {
            addCriterion("RECEIVE_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveUserFlowEqualTo(String value) {
            addCriterion("RECEIVE_USER_FLOW =", value, "receiveUserFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveUserFlowNotEqualTo(String value) {
            addCriterion("RECEIVE_USER_FLOW <>", value, "receiveUserFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveUserFlowGreaterThan(String value) {
            addCriterion("RECEIVE_USER_FLOW >", value, "receiveUserFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVE_USER_FLOW >=", value, "receiveUserFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveUserFlowLessThan(String value) {
            addCriterion("RECEIVE_USER_FLOW <", value, "receiveUserFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveUserFlowLessThanOrEqualTo(String value) {
            addCriterion("RECEIVE_USER_FLOW <=", value, "receiveUserFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveUserFlowLike(String value) {
            addCriterion("RECEIVE_USER_FLOW like", value, "receiveUserFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveUserFlowNotLike(String value) {
            addCriterion("RECEIVE_USER_FLOW not like", value, "receiveUserFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveUserFlowIn(List<String> values) {
            addCriterion("RECEIVE_USER_FLOW in", values, "receiveUserFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveUserFlowNotIn(List<String> values) {
            addCriterion("RECEIVE_USER_FLOW not in", values, "receiveUserFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveUserFlowBetween(String value1, String value2) {
            addCriterion("RECEIVE_USER_FLOW between", value1, value2, "receiveUserFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveUserFlowNotBetween(String value1, String value2) {
            addCriterion("RECEIVE_USER_FLOW not between", value1, value2, "receiveUserFlow");
            return (Criteria) this;
        }

        public Criteria andReceiveUserNameIsNull() {
            addCriterion("RECEIVE_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andReceiveUserNameIsNotNull() {
            addCriterion("RECEIVE_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveUserNameEqualTo(String value) {
            addCriterion("RECEIVE_USER_NAME =", value, "receiveUserName");
            return (Criteria) this;
        }

        public Criteria andReceiveUserNameNotEqualTo(String value) {
            addCriterion("RECEIVE_USER_NAME <>", value, "receiveUserName");
            return (Criteria) this;
        }

        public Criteria andReceiveUserNameGreaterThan(String value) {
            addCriterion("RECEIVE_USER_NAME >", value, "receiveUserName");
            return (Criteria) this;
        }

        public Criteria andReceiveUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVE_USER_NAME >=", value, "receiveUserName");
            return (Criteria) this;
        }

        public Criteria andReceiveUserNameLessThan(String value) {
            addCriterion("RECEIVE_USER_NAME <", value, "receiveUserName");
            return (Criteria) this;
        }

        public Criteria andReceiveUserNameLessThanOrEqualTo(String value) {
            addCriterion("RECEIVE_USER_NAME <=", value, "receiveUserName");
            return (Criteria) this;
        }

        public Criteria andReceiveUserNameLike(String value) {
            addCriterion("RECEIVE_USER_NAME like", value, "receiveUserName");
            return (Criteria) this;
        }

        public Criteria andReceiveUserNameNotLike(String value) {
            addCriterion("RECEIVE_USER_NAME not like", value, "receiveUserName");
            return (Criteria) this;
        }

        public Criteria andReceiveUserNameIn(List<String> values) {
            addCriterion("RECEIVE_USER_NAME in", values, "receiveUserName");
            return (Criteria) this;
        }

        public Criteria andReceiveUserNameNotIn(List<String> values) {
            addCriterion("RECEIVE_USER_NAME not in", values, "receiveUserName");
            return (Criteria) this;
        }

        public Criteria andReceiveUserNameBetween(String value1, String value2) {
            addCriterion("RECEIVE_USER_NAME between", value1, value2, "receiveUserName");
            return (Criteria) this;
        }

        public Criteria andReceiveUserNameNotBetween(String value1, String value2) {
            addCriterion("RECEIVE_USER_NAME not between", value1, value2, "receiveUserName");
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

        public Criteria andConsumerAliasNameIsNull() {
            addCriterion("CONSUMER_ALIAS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameIsNotNull() {
            addCriterion("CONSUMER_ALIAS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameEqualTo(String value) {
            addCriterion("CONSUMER_ALIAS_NAME =", value, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameNotEqualTo(String value) {
            addCriterion("CONSUMER_ALIAS_NAME <>", value, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameGreaterThan(String value) {
            addCriterion("CONSUMER_ALIAS_NAME >", value, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONSUMER_ALIAS_NAME >=", value, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameLessThan(String value) {
            addCriterion("CONSUMER_ALIAS_NAME <", value, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameLessThanOrEqualTo(String value) {
            addCriterion("CONSUMER_ALIAS_NAME <=", value, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameLike(String value) {
            addCriterion("CONSUMER_ALIAS_NAME like", value, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameNotLike(String value) {
            addCriterion("CONSUMER_ALIAS_NAME not like", value, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameIn(List<String> values) {
            addCriterion("CONSUMER_ALIAS_NAME in", values, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameNotIn(List<String> values) {
            addCriterion("CONSUMER_ALIAS_NAME not in", values, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameBetween(String value1, String value2) {
            addCriterion("CONSUMER_ALIAS_NAME between", value1, value2, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameNotBetween(String value1, String value2) {
            addCriterion("CONSUMER_ALIAS_NAME not between", value1, value2, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andOrderProductNameIsNull() {
            addCriterion("ORDER_PRODUCT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrderProductNameIsNotNull() {
            addCriterion("ORDER_PRODUCT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrderProductNameEqualTo(String value) {
            addCriterion("ORDER_PRODUCT_NAME =", value, "orderProductName");
            return (Criteria) this;
        }

        public Criteria andOrderProductNameNotEqualTo(String value) {
            addCriterion("ORDER_PRODUCT_NAME <>", value, "orderProductName");
            return (Criteria) this;
        }

        public Criteria andOrderProductNameGreaterThan(String value) {
            addCriterion("ORDER_PRODUCT_NAME >", value, "orderProductName");
            return (Criteria) this;
        }

        public Criteria andOrderProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_PRODUCT_NAME >=", value, "orderProductName");
            return (Criteria) this;
        }

        public Criteria andOrderProductNameLessThan(String value) {
            addCriterion("ORDER_PRODUCT_NAME <", value, "orderProductName");
            return (Criteria) this;
        }

        public Criteria andOrderProductNameLessThanOrEqualTo(String value) {
            addCriterion("ORDER_PRODUCT_NAME <=", value, "orderProductName");
            return (Criteria) this;
        }

        public Criteria andOrderProductNameLike(String value) {
            addCriterion("ORDER_PRODUCT_NAME like", value, "orderProductName");
            return (Criteria) this;
        }

        public Criteria andOrderProductNameNotLike(String value) {
            addCriterion("ORDER_PRODUCT_NAME not like", value, "orderProductName");
            return (Criteria) this;
        }

        public Criteria andOrderProductNameIn(List<String> values) {
            addCriterion("ORDER_PRODUCT_NAME in", values, "orderProductName");
            return (Criteria) this;
        }

        public Criteria andOrderProductNameNotIn(List<String> values) {
            addCriterion("ORDER_PRODUCT_NAME not in", values, "orderProductName");
            return (Criteria) this;
        }

        public Criteria andOrderProductNameBetween(String value1, String value2) {
            addCriterion("ORDER_PRODUCT_NAME between", value1, value2, "orderProductName");
            return (Criteria) this;
        }

        public Criteria andOrderProductNameNotBetween(String value1, String value2) {
            addCriterion("ORDER_PRODUCT_NAME not between", value1, value2, "orderProductName");
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