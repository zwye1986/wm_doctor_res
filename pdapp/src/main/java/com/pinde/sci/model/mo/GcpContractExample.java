package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GcpContractExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GcpContractExample() {
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

        public Criteria andProjNameIsNull() {
            addCriterion("PROJ_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjNameIsNotNull() {
            addCriterion("PROJ_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjNameEqualTo(String value) {
            addCriterion("PROJ_NAME =", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotEqualTo(String value) {
            addCriterion("PROJ_NAME <>", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameGreaterThan(String value) {
            addCriterion("PROJ_NAME >", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_NAME >=", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameLessThan(String value) {
            addCriterion("PROJ_NAME <", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_NAME <=", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameLike(String value) {
            addCriterion("PROJ_NAME like", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotLike(String value) {
            addCriterion("PROJ_NAME not like", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameIn(List<String> values) {
            addCriterion("PROJ_NAME in", values, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotIn(List<String> values) {
            addCriterion("PROJ_NAME not in", values, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameBetween(String value1, String value2) {
            addCriterion("PROJ_NAME between", value1, value2, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_NAME not between", value1, value2, "projName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameIsNull() {
            addCriterion("PROJ_SHORT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjShortNameIsNotNull() {
            addCriterion("PROJ_SHORT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjShortNameEqualTo(String value) {
            addCriterion("PROJ_SHORT_NAME =", value, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameNotEqualTo(String value) {
            addCriterion("PROJ_SHORT_NAME <>", value, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameGreaterThan(String value) {
            addCriterion("PROJ_SHORT_NAME >", value, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SHORT_NAME >=", value, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameLessThan(String value) {
            addCriterion("PROJ_SHORT_NAME <", value, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SHORT_NAME <=", value, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameLike(String value) {
            addCriterion("PROJ_SHORT_NAME like", value, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameNotLike(String value) {
            addCriterion("PROJ_SHORT_NAME not like", value, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameIn(List<String> values) {
            addCriterion("PROJ_SHORT_NAME in", values, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameNotIn(List<String> values) {
            addCriterion("PROJ_SHORT_NAME not in", values, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameBetween(String value1, String value2) {
            addCriterion("PROJ_SHORT_NAME between", value1, value2, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_SHORT_NAME not between", value1, value2, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdIsNull() {
            addCriterion("PROJ_SUB_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdIsNotNull() {
            addCriterion("PROJ_SUB_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdEqualTo(String value) {
            addCriterion("PROJ_SUB_TYPE_ID =", value, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdNotEqualTo(String value) {
            addCriterion("PROJ_SUB_TYPE_ID <>", value, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdGreaterThan(String value) {
            addCriterion("PROJ_SUB_TYPE_ID >", value, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SUB_TYPE_ID >=", value, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdLessThan(String value) {
            addCriterion("PROJ_SUB_TYPE_ID <", value, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SUB_TYPE_ID <=", value, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdLike(String value) {
            addCriterion("PROJ_SUB_TYPE_ID like", value, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdNotLike(String value) {
            addCriterion("PROJ_SUB_TYPE_ID not like", value, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdIn(List<String> values) {
            addCriterion("PROJ_SUB_TYPE_ID in", values, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdNotIn(List<String> values) {
            addCriterion("PROJ_SUB_TYPE_ID not in", values, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdBetween(String value1, String value2) {
            addCriterion("PROJ_SUB_TYPE_ID between", value1, value2, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdNotBetween(String value1, String value2) {
            addCriterion("PROJ_SUB_TYPE_ID not between", value1, value2, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameIsNull() {
            addCriterion("PROJ_SUB_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameIsNotNull() {
            addCriterion("PROJ_SUB_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameEqualTo(String value) {
            addCriterion("PROJ_SUB_TYPE_NAME =", value, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameNotEqualTo(String value) {
            addCriterion("PROJ_SUB_TYPE_NAME <>", value, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameGreaterThan(String value) {
            addCriterion("PROJ_SUB_TYPE_NAME >", value, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SUB_TYPE_NAME >=", value, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameLessThan(String value) {
            addCriterion("PROJ_SUB_TYPE_NAME <", value, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SUB_TYPE_NAME <=", value, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameLike(String value) {
            addCriterion("PROJ_SUB_TYPE_NAME like", value, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameNotLike(String value) {
            addCriterion("PROJ_SUB_TYPE_NAME not like", value, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameIn(List<String> values) {
            addCriterion("PROJ_SUB_TYPE_NAME in", values, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameNotIn(List<String> values) {
            addCriterion("PROJ_SUB_TYPE_NAME not in", values, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameBetween(String value1, String value2) {
            addCriterion("PROJ_SUB_TYPE_NAME between", value1, value2, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_SUB_TYPE_NAME not between", value1, value2, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerIsNull() {
            addCriterion("PROJ_DECLARER is null");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerIsNotNull() {
            addCriterion("PROJ_DECLARER is not null");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerEqualTo(String value) {
            addCriterion("PROJ_DECLARER =", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerNotEqualTo(String value) {
            addCriterion("PROJ_DECLARER <>", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerGreaterThan(String value) {
            addCriterion("PROJ_DECLARER >", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_DECLARER >=", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerLessThan(String value) {
            addCriterion("PROJ_DECLARER <", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerLessThanOrEqualTo(String value) {
            addCriterion("PROJ_DECLARER <=", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerLike(String value) {
            addCriterion("PROJ_DECLARER like", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerNotLike(String value) {
            addCriterion("PROJ_DECLARER not like", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerIn(List<String> values) {
            addCriterion("PROJ_DECLARER in", values, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerNotIn(List<String> values) {
            addCriterion("PROJ_DECLARER not in", values, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerBetween(String value1, String value2) {
            addCriterion("PROJ_DECLARER between", value1, value2, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerNotBetween(String value1, String value2) {
            addCriterion("PROJ_DECLARER not between", value1, value2, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerIsNull() {
            addCriterion("PROJ_SHORT_DECLARER is null");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerIsNotNull() {
            addCriterion("PROJ_SHORT_DECLARER is not null");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerEqualTo(String value) {
            addCriterion("PROJ_SHORT_DECLARER =", value, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerNotEqualTo(String value) {
            addCriterion("PROJ_SHORT_DECLARER <>", value, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerGreaterThan(String value) {
            addCriterion("PROJ_SHORT_DECLARER >", value, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SHORT_DECLARER >=", value, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerLessThan(String value) {
            addCriterion("PROJ_SHORT_DECLARER <", value, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SHORT_DECLARER <=", value, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerLike(String value) {
            addCriterion("PROJ_SHORT_DECLARER like", value, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerNotLike(String value) {
            addCriterion("PROJ_SHORT_DECLARER not like", value, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerIn(List<String> values) {
            addCriterion("PROJ_SHORT_DECLARER in", values, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerNotIn(List<String> values) {
            addCriterion("PROJ_SHORT_DECLARER not in", values, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerBetween(String value1, String value2) {
            addCriterion("PROJ_SHORT_DECLARER between", value1, value2, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerNotBetween(String value1, String value2) {
            addCriterion("PROJ_SHORT_DECLARER not between", value1, value2, "projShortDeclarer");
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

        public Criteria andCaseNumberIsNull() {
            addCriterion("CASE_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andCaseNumberIsNotNull() {
            addCriterion("CASE_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andCaseNumberEqualTo(Integer value) {
            addCriterion("CASE_NUMBER =", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberNotEqualTo(Integer value) {
            addCriterion("CASE_NUMBER <>", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberGreaterThan(Integer value) {
            addCriterion("CASE_NUMBER >", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("CASE_NUMBER >=", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberLessThan(Integer value) {
            addCriterion("CASE_NUMBER <", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberLessThanOrEqualTo(Integer value) {
            addCriterion("CASE_NUMBER <=", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberIn(List<Integer> values) {
            addCriterion("CASE_NUMBER in", values, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberNotIn(List<Integer> values) {
            addCriterion("CASE_NUMBER not in", values, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberBetween(Integer value1, Integer value2) {
            addCriterion("CASE_NUMBER between", value1, value2, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("CASE_NUMBER not between", value1, value2, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andContractCopiesIsNull() {
            addCriterion("CONTRACT_COPIES is null");
            return (Criteria) this;
        }

        public Criteria andContractCopiesIsNotNull() {
            addCriterion("CONTRACT_COPIES is not null");
            return (Criteria) this;
        }

        public Criteria andContractCopiesEqualTo(Integer value) {
            addCriterion("CONTRACT_COPIES =", value, "contractCopies");
            return (Criteria) this;
        }

        public Criteria andContractCopiesNotEqualTo(Integer value) {
            addCriterion("CONTRACT_COPIES <>", value, "contractCopies");
            return (Criteria) this;
        }

        public Criteria andContractCopiesGreaterThan(Integer value) {
            addCriterion("CONTRACT_COPIES >", value, "contractCopies");
            return (Criteria) this;
        }

        public Criteria andContractCopiesGreaterThanOrEqualTo(Integer value) {
            addCriterion("CONTRACT_COPIES >=", value, "contractCopies");
            return (Criteria) this;
        }

        public Criteria andContractCopiesLessThan(Integer value) {
            addCriterion("CONTRACT_COPIES <", value, "contractCopies");
            return (Criteria) this;
        }

        public Criteria andContractCopiesLessThanOrEqualTo(Integer value) {
            addCriterion("CONTRACT_COPIES <=", value, "contractCopies");
            return (Criteria) this;
        }

        public Criteria andContractCopiesIn(List<Integer> values) {
            addCriterion("CONTRACT_COPIES in", values, "contractCopies");
            return (Criteria) this;
        }

        public Criteria andContractCopiesNotIn(List<Integer> values) {
            addCriterion("CONTRACT_COPIES not in", values, "contractCopies");
            return (Criteria) this;
        }

        public Criteria andContractCopiesBetween(Integer value1, Integer value2) {
            addCriterion("CONTRACT_COPIES between", value1, value2, "contractCopies");
            return (Criteria) this;
        }

        public Criteria andContractCopiesNotBetween(Integer value1, Integer value2) {
            addCriterion("CONTRACT_COPIES not between", value1, value2, "contractCopies");
            return (Criteria) this;
        }

        public Criteria andStampDateIsNull() {
            addCriterion("STAMP_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStampDateIsNotNull() {
            addCriterion("STAMP_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStampDateEqualTo(String value) {
            addCriterion("STAMP_DATE =", value, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateNotEqualTo(String value) {
            addCriterion("STAMP_DATE <>", value, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateGreaterThan(String value) {
            addCriterion("STAMP_DATE >", value, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateGreaterThanOrEqualTo(String value) {
            addCriterion("STAMP_DATE >=", value, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateLessThan(String value) {
            addCriterion("STAMP_DATE <", value, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateLessThanOrEqualTo(String value) {
            addCriterion("STAMP_DATE <=", value, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateLike(String value) {
            addCriterion("STAMP_DATE like", value, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateNotLike(String value) {
            addCriterion("STAMP_DATE not like", value, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateIn(List<String> values) {
            addCriterion("STAMP_DATE in", values, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateNotIn(List<String> values) {
            addCriterion("STAMP_DATE not in", values, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateBetween(String value1, String value2) {
            addCriterion("STAMP_DATE between", value1, value2, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateNotBetween(String value1, String value2) {
            addCriterion("STAMP_DATE not between", value1, value2, "stampDate");
            return (Criteria) this;
        }

        public Criteria andContractFileIsNull() {
            addCriterion("CONTRACT_FILE is null");
            return (Criteria) this;
        }

        public Criteria andContractFileIsNotNull() {
            addCriterion("CONTRACT_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andContractFileEqualTo(String value) {
            addCriterion("CONTRACT_FILE =", value, "contractFile");
            return (Criteria) this;
        }

        public Criteria andContractFileNotEqualTo(String value) {
            addCriterion("CONTRACT_FILE <>", value, "contractFile");
            return (Criteria) this;
        }

        public Criteria andContractFileGreaterThan(String value) {
            addCriterion("CONTRACT_FILE >", value, "contractFile");
            return (Criteria) this;
        }

        public Criteria andContractFileGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_FILE >=", value, "contractFile");
            return (Criteria) this;
        }

        public Criteria andContractFileLessThan(String value) {
            addCriterion("CONTRACT_FILE <", value, "contractFile");
            return (Criteria) this;
        }

        public Criteria andContractFileLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_FILE <=", value, "contractFile");
            return (Criteria) this;
        }

        public Criteria andContractFileLike(String value) {
            addCriterion("CONTRACT_FILE like", value, "contractFile");
            return (Criteria) this;
        }

        public Criteria andContractFileNotLike(String value) {
            addCriterion("CONTRACT_FILE not like", value, "contractFile");
            return (Criteria) this;
        }

        public Criteria andContractFileIn(List<String> values) {
            addCriterion("CONTRACT_FILE in", values, "contractFile");
            return (Criteria) this;
        }

        public Criteria andContractFileNotIn(List<String> values) {
            addCriterion("CONTRACT_FILE not in", values, "contractFile");
            return (Criteria) this;
        }

        public Criteria andContractFileBetween(String value1, String value2) {
            addCriterion("CONTRACT_FILE between", value1, value2, "contractFile");
            return (Criteria) this;
        }

        public Criteria andContractFileNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_FILE not between", value1, value2, "contractFile");
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