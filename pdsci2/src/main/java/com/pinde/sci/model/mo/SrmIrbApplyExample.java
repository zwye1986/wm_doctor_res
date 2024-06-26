package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SrmIrbApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmIrbApplyExample() {
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

        public Criteria andIrbFlowIsNull() {
            addCriterion("IRB_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andIrbFlowIsNotNull() {
            addCriterion("IRB_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andIrbFlowEqualTo(String value) {
            addCriterion("IRB_FLOW =", value, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowNotEqualTo(String value) {
            addCriterion("IRB_FLOW <>", value, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowGreaterThan(String value) {
            addCriterion("IRB_FLOW >", value, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowGreaterThanOrEqualTo(String value) {
            addCriterion("IRB_FLOW >=", value, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowLessThan(String value) {
            addCriterion("IRB_FLOW <", value, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowLessThanOrEqualTo(String value) {
            addCriterion("IRB_FLOW <=", value, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowLike(String value) {
            addCriterion("IRB_FLOW like", value, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowNotLike(String value) {
            addCriterion("IRB_FLOW not like", value, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowIn(List<String> values) {
            addCriterion("IRB_FLOW in", values, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowNotIn(List<String> values) {
            addCriterion("IRB_FLOW not in", values, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowBetween(String value1, String value2) {
            addCriterion("IRB_FLOW between", value1, value2, "irbFlow");
            return (Criteria) this;
        }

        public Criteria andIrbFlowNotBetween(String value1, String value2) {
            addCriterion("IRB_FLOW not between", value1, value2, "irbFlow");
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

        public Criteria andProjNoIsNull() {
            addCriterion("PROJ_NO is null");
            return (Criteria) this;
        }

        public Criteria andProjNoIsNotNull() {
            addCriterion("PROJ_NO is not null");
            return (Criteria) this;
        }

        public Criteria andProjNoEqualTo(String value) {
            addCriterion("PROJ_NO =", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoNotEqualTo(String value) {
            addCriterion("PROJ_NO <>", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoGreaterThan(String value) {
            addCriterion("PROJ_NO >", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_NO >=", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoLessThan(String value) {
            addCriterion("PROJ_NO <", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoLessThanOrEqualTo(String value) {
            addCriterion("PROJ_NO <=", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoLike(String value) {
            addCriterion("PROJ_NO like", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoNotLike(String value) {
            addCriterion("PROJ_NO not like", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoIn(List<String> values) {
            addCriterion("PROJ_NO in", values, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoNotIn(List<String> values) {
            addCriterion("PROJ_NO not in", values, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoBetween(String value1, String value2) {
            addCriterion("PROJ_NO between", value1, value2, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoNotBetween(String value1, String value2) {
            addCriterion("PROJ_NO not between", value1, value2, "projNo");
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

        public Criteria andIrbInfoFlowIsNull() {
            addCriterion("IRB_INFO_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andIrbInfoFlowIsNotNull() {
            addCriterion("IRB_INFO_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andIrbInfoFlowEqualTo(String value) {
            addCriterion("IRB_INFO_FLOW =", value, "irbInfoFlow");
            return (Criteria) this;
        }

        public Criteria andIrbInfoFlowNotEqualTo(String value) {
            addCriterion("IRB_INFO_FLOW <>", value, "irbInfoFlow");
            return (Criteria) this;
        }

        public Criteria andIrbInfoFlowGreaterThan(String value) {
            addCriterion("IRB_INFO_FLOW >", value, "irbInfoFlow");
            return (Criteria) this;
        }

        public Criteria andIrbInfoFlowGreaterThanOrEqualTo(String value) {
            addCriterion("IRB_INFO_FLOW >=", value, "irbInfoFlow");
            return (Criteria) this;
        }

        public Criteria andIrbInfoFlowLessThan(String value) {
            addCriterion("IRB_INFO_FLOW <", value, "irbInfoFlow");
            return (Criteria) this;
        }

        public Criteria andIrbInfoFlowLessThanOrEqualTo(String value) {
            addCriterion("IRB_INFO_FLOW <=", value, "irbInfoFlow");
            return (Criteria) this;
        }

        public Criteria andIrbInfoFlowLike(String value) {
            addCriterion("IRB_INFO_FLOW like", value, "irbInfoFlow");
            return (Criteria) this;
        }

        public Criteria andIrbInfoFlowNotLike(String value) {
            addCriterion("IRB_INFO_FLOW not like", value, "irbInfoFlow");
            return (Criteria) this;
        }

        public Criteria andIrbInfoFlowIn(List<String> values) {
            addCriterion("IRB_INFO_FLOW in", values, "irbInfoFlow");
            return (Criteria) this;
        }

        public Criteria andIrbInfoFlowNotIn(List<String> values) {
            addCriterion("IRB_INFO_FLOW not in", values, "irbInfoFlow");
            return (Criteria) this;
        }

        public Criteria andIrbInfoFlowBetween(String value1, String value2) {
            addCriterion("IRB_INFO_FLOW between", value1, value2, "irbInfoFlow");
            return (Criteria) this;
        }

        public Criteria andIrbInfoFlowNotBetween(String value1, String value2) {
            addCriterion("IRB_INFO_FLOW not between", value1, value2, "irbInfoFlow");
            return (Criteria) this;
        }

        public Criteria andIrbTypeIdIsNull() {
            addCriterion("IRB_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andIrbTypeIdIsNotNull() {
            addCriterion("IRB_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andIrbTypeIdEqualTo(String value) {
            addCriterion("IRB_TYPE_ID =", value, "irbTypeId");
            return (Criteria) this;
        }

        public Criteria andIrbTypeIdNotEqualTo(String value) {
            addCriterion("IRB_TYPE_ID <>", value, "irbTypeId");
            return (Criteria) this;
        }

        public Criteria andIrbTypeIdGreaterThan(String value) {
            addCriterion("IRB_TYPE_ID >", value, "irbTypeId");
            return (Criteria) this;
        }

        public Criteria andIrbTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("IRB_TYPE_ID >=", value, "irbTypeId");
            return (Criteria) this;
        }

        public Criteria andIrbTypeIdLessThan(String value) {
            addCriterion("IRB_TYPE_ID <", value, "irbTypeId");
            return (Criteria) this;
        }

        public Criteria andIrbTypeIdLessThanOrEqualTo(String value) {
            addCriterion("IRB_TYPE_ID <=", value, "irbTypeId");
            return (Criteria) this;
        }

        public Criteria andIrbTypeIdLike(String value) {
            addCriterion("IRB_TYPE_ID like", value, "irbTypeId");
            return (Criteria) this;
        }

        public Criteria andIrbTypeIdNotLike(String value) {
            addCriterion("IRB_TYPE_ID not like", value, "irbTypeId");
            return (Criteria) this;
        }

        public Criteria andIrbTypeIdIn(List<String> values) {
            addCriterion("IRB_TYPE_ID in", values, "irbTypeId");
            return (Criteria) this;
        }

        public Criteria andIrbTypeIdNotIn(List<String> values) {
            addCriterion("IRB_TYPE_ID not in", values, "irbTypeId");
            return (Criteria) this;
        }

        public Criteria andIrbTypeIdBetween(String value1, String value2) {
            addCriterion("IRB_TYPE_ID between", value1, value2, "irbTypeId");
            return (Criteria) this;
        }

        public Criteria andIrbTypeIdNotBetween(String value1, String value2) {
            addCriterion("IRB_TYPE_ID not between", value1, value2, "irbTypeId");
            return (Criteria) this;
        }

        public Criteria andIrbTypeNameIsNull() {
            addCriterion("IRB_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andIrbTypeNameIsNotNull() {
            addCriterion("IRB_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andIrbTypeNameEqualTo(String value) {
            addCriterion("IRB_TYPE_NAME =", value, "irbTypeName");
            return (Criteria) this;
        }

        public Criteria andIrbTypeNameNotEqualTo(String value) {
            addCriterion("IRB_TYPE_NAME <>", value, "irbTypeName");
            return (Criteria) this;
        }

        public Criteria andIrbTypeNameGreaterThan(String value) {
            addCriterion("IRB_TYPE_NAME >", value, "irbTypeName");
            return (Criteria) this;
        }

        public Criteria andIrbTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("IRB_TYPE_NAME >=", value, "irbTypeName");
            return (Criteria) this;
        }

        public Criteria andIrbTypeNameLessThan(String value) {
            addCriterion("IRB_TYPE_NAME <", value, "irbTypeName");
            return (Criteria) this;
        }

        public Criteria andIrbTypeNameLessThanOrEqualTo(String value) {
            addCriterion("IRB_TYPE_NAME <=", value, "irbTypeName");
            return (Criteria) this;
        }

        public Criteria andIrbTypeNameLike(String value) {
            addCriterion("IRB_TYPE_NAME like", value, "irbTypeName");
            return (Criteria) this;
        }

        public Criteria andIrbTypeNameNotLike(String value) {
            addCriterion("IRB_TYPE_NAME not like", value, "irbTypeName");
            return (Criteria) this;
        }

        public Criteria andIrbTypeNameIn(List<String> values) {
            addCriterion("IRB_TYPE_NAME in", values, "irbTypeName");
            return (Criteria) this;
        }

        public Criteria andIrbTypeNameNotIn(List<String> values) {
            addCriterion("IRB_TYPE_NAME not in", values, "irbTypeName");
            return (Criteria) this;
        }

        public Criteria andIrbTypeNameBetween(String value1, String value2) {
            addCriterion("IRB_TYPE_NAME between", value1, value2, "irbTypeName");
            return (Criteria) this;
        }

        public Criteria andIrbTypeNameNotBetween(String value1, String value2) {
            addCriterion("IRB_TYPE_NAME not between", value1, value2, "irbTypeName");
            return (Criteria) this;
        }

        public Criteria andIrbApplyDateIsNull() {
            addCriterion("IRB_APPLY_DATE is null");
            return (Criteria) this;
        }

        public Criteria andIrbApplyDateIsNotNull() {
            addCriterion("IRB_APPLY_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andIrbApplyDateEqualTo(String value) {
            addCriterion("IRB_APPLY_DATE =", value, "irbApplyDate");
            return (Criteria) this;
        }

        public Criteria andIrbApplyDateNotEqualTo(String value) {
            addCriterion("IRB_APPLY_DATE <>", value, "irbApplyDate");
            return (Criteria) this;
        }

        public Criteria andIrbApplyDateGreaterThan(String value) {
            addCriterion("IRB_APPLY_DATE >", value, "irbApplyDate");
            return (Criteria) this;
        }

        public Criteria andIrbApplyDateGreaterThanOrEqualTo(String value) {
            addCriterion("IRB_APPLY_DATE >=", value, "irbApplyDate");
            return (Criteria) this;
        }

        public Criteria andIrbApplyDateLessThan(String value) {
            addCriterion("IRB_APPLY_DATE <", value, "irbApplyDate");
            return (Criteria) this;
        }

        public Criteria andIrbApplyDateLessThanOrEqualTo(String value) {
            addCriterion("IRB_APPLY_DATE <=", value, "irbApplyDate");
            return (Criteria) this;
        }

        public Criteria andIrbApplyDateLike(String value) {
            addCriterion("IRB_APPLY_DATE like", value, "irbApplyDate");
            return (Criteria) this;
        }

        public Criteria andIrbApplyDateNotLike(String value) {
            addCriterion("IRB_APPLY_DATE not like", value, "irbApplyDate");
            return (Criteria) this;
        }

        public Criteria andIrbApplyDateIn(List<String> values) {
            addCriterion("IRB_APPLY_DATE in", values, "irbApplyDate");
            return (Criteria) this;
        }

        public Criteria andIrbApplyDateNotIn(List<String> values) {
            addCriterion("IRB_APPLY_DATE not in", values, "irbApplyDate");
            return (Criteria) this;
        }

        public Criteria andIrbApplyDateBetween(String value1, String value2) {
            addCriterion("IRB_APPLY_DATE between", value1, value2, "irbApplyDate");
            return (Criteria) this;
        }

        public Criteria andIrbApplyDateNotBetween(String value1, String value2) {
            addCriterion("IRB_APPLY_DATE not between", value1, value2, "irbApplyDate");
            return (Criteria) this;
        }

        public Criteria andIrbNoIsNull() {
            addCriterion("IRB_NO is null");
            return (Criteria) this;
        }

        public Criteria andIrbNoIsNotNull() {
            addCriterion("IRB_NO is not null");
            return (Criteria) this;
        }

        public Criteria andIrbNoEqualTo(String value) {
            addCriterion("IRB_NO =", value, "irbNo");
            return (Criteria) this;
        }

        public Criteria andIrbNoNotEqualTo(String value) {
            addCriterion("IRB_NO <>", value, "irbNo");
            return (Criteria) this;
        }

        public Criteria andIrbNoGreaterThan(String value) {
            addCriterion("IRB_NO >", value, "irbNo");
            return (Criteria) this;
        }

        public Criteria andIrbNoGreaterThanOrEqualTo(String value) {
            addCriterion("IRB_NO >=", value, "irbNo");
            return (Criteria) this;
        }

        public Criteria andIrbNoLessThan(String value) {
            addCriterion("IRB_NO <", value, "irbNo");
            return (Criteria) this;
        }

        public Criteria andIrbNoLessThanOrEqualTo(String value) {
            addCriterion("IRB_NO <=", value, "irbNo");
            return (Criteria) this;
        }

        public Criteria andIrbNoLike(String value) {
            addCriterion("IRB_NO like", value, "irbNo");
            return (Criteria) this;
        }

        public Criteria andIrbNoNotLike(String value) {
            addCriterion("IRB_NO not like", value, "irbNo");
            return (Criteria) this;
        }

        public Criteria andIrbNoIn(List<String> values) {
            addCriterion("IRB_NO in", values, "irbNo");
            return (Criteria) this;
        }

        public Criteria andIrbNoNotIn(List<String> values) {
            addCriterion("IRB_NO not in", values, "irbNo");
            return (Criteria) this;
        }

        public Criteria andIrbNoBetween(String value1, String value2) {
            addCriterion("IRB_NO between", value1, value2, "irbNo");
            return (Criteria) this;
        }

        public Criteria andIrbNoNotBetween(String value1, String value2) {
            addCriterion("IRB_NO not between", value1, value2, "irbNo");
            return (Criteria) this;
        }

        public Criteria andIrbAcceptedDateIsNull() {
            addCriterion("IRB_ACCEPTED_DATE is null");
            return (Criteria) this;
        }

        public Criteria andIrbAcceptedDateIsNotNull() {
            addCriterion("IRB_ACCEPTED_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andIrbAcceptedDateEqualTo(String value) {
            addCriterion("IRB_ACCEPTED_DATE =", value, "irbAcceptedDate");
            return (Criteria) this;
        }

        public Criteria andIrbAcceptedDateNotEqualTo(String value) {
            addCriterion("IRB_ACCEPTED_DATE <>", value, "irbAcceptedDate");
            return (Criteria) this;
        }

        public Criteria andIrbAcceptedDateGreaterThan(String value) {
            addCriterion("IRB_ACCEPTED_DATE >", value, "irbAcceptedDate");
            return (Criteria) this;
        }

        public Criteria andIrbAcceptedDateGreaterThanOrEqualTo(String value) {
            addCriterion("IRB_ACCEPTED_DATE >=", value, "irbAcceptedDate");
            return (Criteria) this;
        }

        public Criteria andIrbAcceptedDateLessThan(String value) {
            addCriterion("IRB_ACCEPTED_DATE <", value, "irbAcceptedDate");
            return (Criteria) this;
        }

        public Criteria andIrbAcceptedDateLessThanOrEqualTo(String value) {
            addCriterion("IRB_ACCEPTED_DATE <=", value, "irbAcceptedDate");
            return (Criteria) this;
        }

        public Criteria andIrbAcceptedDateLike(String value) {
            addCriterion("IRB_ACCEPTED_DATE like", value, "irbAcceptedDate");
            return (Criteria) this;
        }

        public Criteria andIrbAcceptedDateNotLike(String value) {
            addCriterion("IRB_ACCEPTED_DATE not like", value, "irbAcceptedDate");
            return (Criteria) this;
        }

        public Criteria andIrbAcceptedDateIn(List<String> values) {
            addCriterion("IRB_ACCEPTED_DATE in", values, "irbAcceptedDate");
            return (Criteria) this;
        }

        public Criteria andIrbAcceptedDateNotIn(List<String> values) {
            addCriterion("IRB_ACCEPTED_DATE not in", values, "irbAcceptedDate");
            return (Criteria) this;
        }

        public Criteria andIrbAcceptedDateBetween(String value1, String value2) {
            addCriterion("IRB_ACCEPTED_DATE between", value1, value2, "irbAcceptedDate");
            return (Criteria) this;
        }

        public Criteria andIrbAcceptedDateNotBetween(String value1, String value2) {
            addCriterion("IRB_ACCEPTED_DATE not between", value1, value2, "irbAcceptedDate");
            return (Criteria) this;
        }

        public Criteria andIrbStageIdIsNull() {
            addCriterion("IRB_STAGE_ID is null");
            return (Criteria) this;
        }

        public Criteria andIrbStageIdIsNotNull() {
            addCriterion("IRB_STAGE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andIrbStageIdEqualTo(String value) {
            addCriterion("IRB_STAGE_ID =", value, "irbStageId");
            return (Criteria) this;
        }

        public Criteria andIrbStageIdNotEqualTo(String value) {
            addCriterion("IRB_STAGE_ID <>", value, "irbStageId");
            return (Criteria) this;
        }

        public Criteria andIrbStageIdGreaterThan(String value) {
            addCriterion("IRB_STAGE_ID >", value, "irbStageId");
            return (Criteria) this;
        }

        public Criteria andIrbStageIdGreaterThanOrEqualTo(String value) {
            addCriterion("IRB_STAGE_ID >=", value, "irbStageId");
            return (Criteria) this;
        }

        public Criteria andIrbStageIdLessThan(String value) {
            addCriterion("IRB_STAGE_ID <", value, "irbStageId");
            return (Criteria) this;
        }

        public Criteria andIrbStageIdLessThanOrEqualTo(String value) {
            addCriterion("IRB_STAGE_ID <=", value, "irbStageId");
            return (Criteria) this;
        }

        public Criteria andIrbStageIdLike(String value) {
            addCriterion("IRB_STAGE_ID like", value, "irbStageId");
            return (Criteria) this;
        }

        public Criteria andIrbStageIdNotLike(String value) {
            addCriterion("IRB_STAGE_ID not like", value, "irbStageId");
            return (Criteria) this;
        }

        public Criteria andIrbStageIdIn(List<String> values) {
            addCriterion("IRB_STAGE_ID in", values, "irbStageId");
            return (Criteria) this;
        }

        public Criteria andIrbStageIdNotIn(List<String> values) {
            addCriterion("IRB_STAGE_ID not in", values, "irbStageId");
            return (Criteria) this;
        }

        public Criteria andIrbStageIdBetween(String value1, String value2) {
            addCriterion("IRB_STAGE_ID between", value1, value2, "irbStageId");
            return (Criteria) this;
        }

        public Criteria andIrbStageIdNotBetween(String value1, String value2) {
            addCriterion("IRB_STAGE_ID not between", value1, value2, "irbStageId");
            return (Criteria) this;
        }

        public Criteria andIrbStageNameIsNull() {
            addCriterion("IRB_STAGE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andIrbStageNameIsNotNull() {
            addCriterion("IRB_STAGE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andIrbStageNameEqualTo(String value) {
            addCriterion("IRB_STAGE_NAME =", value, "irbStageName");
            return (Criteria) this;
        }

        public Criteria andIrbStageNameNotEqualTo(String value) {
            addCriterion("IRB_STAGE_NAME <>", value, "irbStageName");
            return (Criteria) this;
        }

        public Criteria andIrbStageNameGreaterThan(String value) {
            addCriterion("IRB_STAGE_NAME >", value, "irbStageName");
            return (Criteria) this;
        }

        public Criteria andIrbStageNameGreaterThanOrEqualTo(String value) {
            addCriterion("IRB_STAGE_NAME >=", value, "irbStageName");
            return (Criteria) this;
        }

        public Criteria andIrbStageNameLessThan(String value) {
            addCriterion("IRB_STAGE_NAME <", value, "irbStageName");
            return (Criteria) this;
        }

        public Criteria andIrbStageNameLessThanOrEqualTo(String value) {
            addCriterion("IRB_STAGE_NAME <=", value, "irbStageName");
            return (Criteria) this;
        }

        public Criteria andIrbStageNameLike(String value) {
            addCriterion("IRB_STAGE_NAME like", value, "irbStageName");
            return (Criteria) this;
        }

        public Criteria andIrbStageNameNotLike(String value) {
            addCriterion("IRB_STAGE_NAME not like", value, "irbStageName");
            return (Criteria) this;
        }

        public Criteria andIrbStageNameIn(List<String> values) {
            addCriterion("IRB_STAGE_NAME in", values, "irbStageName");
            return (Criteria) this;
        }

        public Criteria andIrbStageNameNotIn(List<String> values) {
            addCriterion("IRB_STAGE_NAME not in", values, "irbStageName");
            return (Criteria) this;
        }

        public Criteria andIrbStageNameBetween(String value1, String value2) {
            addCriterion("IRB_STAGE_NAME between", value1, value2, "irbStageName");
            return (Criteria) this;
        }

        public Criteria andIrbStageNameNotBetween(String value1, String value2) {
            addCriterion("IRB_STAGE_NAME not between", value1, value2, "irbStageName");
            return (Criteria) this;
        }

        public Criteria andReviewWayIdIsNull() {
            addCriterion("REVIEW_WAY_ID is null");
            return (Criteria) this;
        }

        public Criteria andReviewWayIdIsNotNull() {
            addCriterion("REVIEW_WAY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andReviewWayIdEqualTo(String value) {
            addCriterion("REVIEW_WAY_ID =", value, "reviewWayId");
            return (Criteria) this;
        }

        public Criteria andReviewWayIdNotEqualTo(String value) {
            addCriterion("REVIEW_WAY_ID <>", value, "reviewWayId");
            return (Criteria) this;
        }

        public Criteria andReviewWayIdGreaterThan(String value) {
            addCriterion("REVIEW_WAY_ID >", value, "reviewWayId");
            return (Criteria) this;
        }

        public Criteria andReviewWayIdGreaterThanOrEqualTo(String value) {
            addCriterion("REVIEW_WAY_ID >=", value, "reviewWayId");
            return (Criteria) this;
        }

        public Criteria andReviewWayIdLessThan(String value) {
            addCriterion("REVIEW_WAY_ID <", value, "reviewWayId");
            return (Criteria) this;
        }

        public Criteria andReviewWayIdLessThanOrEqualTo(String value) {
            addCriterion("REVIEW_WAY_ID <=", value, "reviewWayId");
            return (Criteria) this;
        }

        public Criteria andReviewWayIdLike(String value) {
            addCriterion("REVIEW_WAY_ID like", value, "reviewWayId");
            return (Criteria) this;
        }

        public Criteria andReviewWayIdNotLike(String value) {
            addCriterion("REVIEW_WAY_ID not like", value, "reviewWayId");
            return (Criteria) this;
        }

        public Criteria andReviewWayIdIn(List<String> values) {
            addCriterion("REVIEW_WAY_ID in", values, "reviewWayId");
            return (Criteria) this;
        }

        public Criteria andReviewWayIdNotIn(List<String> values) {
            addCriterion("REVIEW_WAY_ID not in", values, "reviewWayId");
            return (Criteria) this;
        }

        public Criteria andReviewWayIdBetween(String value1, String value2) {
            addCriterion("REVIEW_WAY_ID between", value1, value2, "reviewWayId");
            return (Criteria) this;
        }

        public Criteria andReviewWayIdNotBetween(String value1, String value2) {
            addCriterion("REVIEW_WAY_ID not between", value1, value2, "reviewWayId");
            return (Criteria) this;
        }

        public Criteria andReviewWayNameIsNull() {
            addCriterion("REVIEW_WAY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andReviewWayNameIsNotNull() {
            addCriterion("REVIEW_WAY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andReviewWayNameEqualTo(String value) {
            addCriterion("REVIEW_WAY_NAME =", value, "reviewWayName");
            return (Criteria) this;
        }

        public Criteria andReviewWayNameNotEqualTo(String value) {
            addCriterion("REVIEW_WAY_NAME <>", value, "reviewWayName");
            return (Criteria) this;
        }

        public Criteria andReviewWayNameGreaterThan(String value) {
            addCriterion("REVIEW_WAY_NAME >", value, "reviewWayName");
            return (Criteria) this;
        }

        public Criteria andReviewWayNameGreaterThanOrEqualTo(String value) {
            addCriterion("REVIEW_WAY_NAME >=", value, "reviewWayName");
            return (Criteria) this;
        }

        public Criteria andReviewWayNameLessThan(String value) {
            addCriterion("REVIEW_WAY_NAME <", value, "reviewWayName");
            return (Criteria) this;
        }

        public Criteria andReviewWayNameLessThanOrEqualTo(String value) {
            addCriterion("REVIEW_WAY_NAME <=", value, "reviewWayName");
            return (Criteria) this;
        }

        public Criteria andReviewWayNameLike(String value) {
            addCriterion("REVIEW_WAY_NAME like", value, "reviewWayName");
            return (Criteria) this;
        }

        public Criteria andReviewWayNameNotLike(String value) {
            addCriterion("REVIEW_WAY_NAME not like", value, "reviewWayName");
            return (Criteria) this;
        }

        public Criteria andReviewWayNameIn(List<String> values) {
            addCriterion("REVIEW_WAY_NAME in", values, "reviewWayName");
            return (Criteria) this;
        }

        public Criteria andReviewWayNameNotIn(List<String> values) {
            addCriterion("REVIEW_WAY_NAME not in", values, "reviewWayName");
            return (Criteria) this;
        }

        public Criteria andReviewWayNameBetween(String value1, String value2) {
            addCriterion("REVIEW_WAY_NAME between", value1, value2, "reviewWayName");
            return (Criteria) this;
        }

        public Criteria andReviewWayNameNotBetween(String value1, String value2) {
            addCriterion("REVIEW_WAY_NAME not between", value1, value2, "reviewWayName");
            return (Criteria) this;
        }

        public Criteria andIrbReviewDateIsNull() {
            addCriterion("IRB_REVIEW_DATE is null");
            return (Criteria) this;
        }

        public Criteria andIrbReviewDateIsNotNull() {
            addCriterion("IRB_REVIEW_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andIrbReviewDateEqualTo(String value) {
            addCriterion("IRB_REVIEW_DATE =", value, "irbReviewDate");
            return (Criteria) this;
        }

        public Criteria andIrbReviewDateNotEqualTo(String value) {
            addCriterion("IRB_REVIEW_DATE <>", value, "irbReviewDate");
            return (Criteria) this;
        }

        public Criteria andIrbReviewDateGreaterThan(String value) {
            addCriterion("IRB_REVIEW_DATE >", value, "irbReviewDate");
            return (Criteria) this;
        }

        public Criteria andIrbReviewDateGreaterThanOrEqualTo(String value) {
            addCriterion("IRB_REVIEW_DATE >=", value, "irbReviewDate");
            return (Criteria) this;
        }

        public Criteria andIrbReviewDateLessThan(String value) {
            addCriterion("IRB_REVIEW_DATE <", value, "irbReviewDate");
            return (Criteria) this;
        }

        public Criteria andIrbReviewDateLessThanOrEqualTo(String value) {
            addCriterion("IRB_REVIEW_DATE <=", value, "irbReviewDate");
            return (Criteria) this;
        }

        public Criteria andIrbReviewDateLike(String value) {
            addCriterion("IRB_REVIEW_DATE like", value, "irbReviewDate");
            return (Criteria) this;
        }

        public Criteria andIrbReviewDateNotLike(String value) {
            addCriterion("IRB_REVIEW_DATE not like", value, "irbReviewDate");
            return (Criteria) this;
        }

        public Criteria andIrbReviewDateIn(List<String> values) {
            addCriterion("IRB_REVIEW_DATE in", values, "irbReviewDate");
            return (Criteria) this;
        }

        public Criteria andIrbReviewDateNotIn(List<String> values) {
            addCriterion("IRB_REVIEW_DATE not in", values, "irbReviewDate");
            return (Criteria) this;
        }

        public Criteria andIrbReviewDateBetween(String value1, String value2) {
            addCriterion("IRB_REVIEW_DATE between", value1, value2, "irbReviewDate");
            return (Criteria) this;
        }

        public Criteria andIrbReviewDateNotBetween(String value1, String value2) {
            addCriterion("IRB_REVIEW_DATE not between", value1, value2, "irbReviewDate");
            return (Criteria) this;
        }

        public Criteria andMeetingArrangeIsNull() {
            addCriterion("MEETING_ARRANGE is null");
            return (Criteria) this;
        }

        public Criteria andMeetingArrangeIsNotNull() {
            addCriterion("MEETING_ARRANGE is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingArrangeEqualTo(String value) {
            addCriterion("MEETING_ARRANGE =", value, "meetingArrange");
            return (Criteria) this;
        }

        public Criteria andMeetingArrangeNotEqualTo(String value) {
            addCriterion("MEETING_ARRANGE <>", value, "meetingArrange");
            return (Criteria) this;
        }

        public Criteria andMeetingArrangeGreaterThan(String value) {
            addCriterion("MEETING_ARRANGE >", value, "meetingArrange");
            return (Criteria) this;
        }

        public Criteria andMeetingArrangeGreaterThanOrEqualTo(String value) {
            addCriterion("MEETING_ARRANGE >=", value, "meetingArrange");
            return (Criteria) this;
        }

        public Criteria andMeetingArrangeLessThan(String value) {
            addCriterion("MEETING_ARRANGE <", value, "meetingArrange");
            return (Criteria) this;
        }

        public Criteria andMeetingArrangeLessThanOrEqualTo(String value) {
            addCriterion("MEETING_ARRANGE <=", value, "meetingArrange");
            return (Criteria) this;
        }

        public Criteria andMeetingArrangeLike(String value) {
            addCriterion("MEETING_ARRANGE like", value, "meetingArrange");
            return (Criteria) this;
        }

        public Criteria andMeetingArrangeNotLike(String value) {
            addCriterion("MEETING_ARRANGE not like", value, "meetingArrange");
            return (Criteria) this;
        }

        public Criteria andMeetingArrangeIn(List<String> values) {
            addCriterion("MEETING_ARRANGE in", values, "meetingArrange");
            return (Criteria) this;
        }

        public Criteria andMeetingArrangeNotIn(List<String> values) {
            addCriterion("MEETING_ARRANGE not in", values, "meetingArrange");
            return (Criteria) this;
        }

        public Criteria andMeetingArrangeBetween(String value1, String value2) {
            addCriterion("MEETING_ARRANGE between", value1, value2, "meetingArrange");
            return (Criteria) this;
        }

        public Criteria andMeetingArrangeNotBetween(String value1, String value2) {
            addCriterion("MEETING_ARRANGE not between", value1, value2, "meetingArrange");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowIsNull() {
            addCriterion("MEETING_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowIsNotNull() {
            addCriterion("MEETING_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowEqualTo(String value) {
            addCriterion("MEETING_FLOW =", value, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowNotEqualTo(String value) {
            addCriterion("MEETING_FLOW <>", value, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowGreaterThan(String value) {
            addCriterion("MEETING_FLOW >", value, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowGreaterThanOrEqualTo(String value) {
            addCriterion("MEETING_FLOW >=", value, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowLessThan(String value) {
            addCriterion("MEETING_FLOW <", value, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowLessThanOrEqualTo(String value) {
            addCriterion("MEETING_FLOW <=", value, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowLike(String value) {
            addCriterion("MEETING_FLOW like", value, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowNotLike(String value) {
            addCriterion("MEETING_FLOW not like", value, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowIn(List<String> values) {
            addCriterion("MEETING_FLOW in", values, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowNotIn(List<String> values) {
            addCriterion("MEETING_FLOW not in", values, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowBetween(String value1, String value2) {
            addCriterion("MEETING_FLOW between", value1, value2, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowNotBetween(String value1, String value2) {
            addCriterion("MEETING_FLOW not between", value1, value2, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingDateIsNull() {
            addCriterion("MEETING_DATE is null");
            return (Criteria) this;
        }

        public Criteria andMeetingDateIsNotNull() {
            addCriterion("MEETING_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingDateEqualTo(String value) {
            addCriterion("MEETING_DATE =", value, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateNotEqualTo(String value) {
            addCriterion("MEETING_DATE <>", value, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateGreaterThan(String value) {
            addCriterion("MEETING_DATE >", value, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateGreaterThanOrEqualTo(String value) {
            addCriterion("MEETING_DATE >=", value, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateLessThan(String value) {
            addCriterion("MEETING_DATE <", value, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateLessThanOrEqualTo(String value) {
            addCriterion("MEETING_DATE <=", value, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateLike(String value) {
            addCriterion("MEETING_DATE like", value, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateNotLike(String value) {
            addCriterion("MEETING_DATE not like", value, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateIn(List<String> values) {
            addCriterion("MEETING_DATE in", values, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateNotIn(List<String> values) {
            addCriterion("MEETING_DATE not in", values, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateBetween(String value1, String value2) {
            addCriterion("MEETING_DATE between", value1, value2, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateNotBetween(String value1, String value2) {
            addCriterion("MEETING_DATE not between", value1, value2, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionIdIsNull() {
            addCriterion("IRB_DECISION_ID is null");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionIdIsNotNull() {
            addCriterion("IRB_DECISION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionIdEqualTo(String value) {
            addCriterion("IRB_DECISION_ID =", value, "irbDecisionId");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionIdNotEqualTo(String value) {
            addCriterion("IRB_DECISION_ID <>", value, "irbDecisionId");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionIdGreaterThan(String value) {
            addCriterion("IRB_DECISION_ID >", value, "irbDecisionId");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionIdGreaterThanOrEqualTo(String value) {
            addCriterion("IRB_DECISION_ID >=", value, "irbDecisionId");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionIdLessThan(String value) {
            addCriterion("IRB_DECISION_ID <", value, "irbDecisionId");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionIdLessThanOrEqualTo(String value) {
            addCriterion("IRB_DECISION_ID <=", value, "irbDecisionId");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionIdLike(String value) {
            addCriterion("IRB_DECISION_ID like", value, "irbDecisionId");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionIdNotLike(String value) {
            addCriterion("IRB_DECISION_ID not like", value, "irbDecisionId");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionIdIn(List<String> values) {
            addCriterion("IRB_DECISION_ID in", values, "irbDecisionId");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionIdNotIn(List<String> values) {
            addCriterion("IRB_DECISION_ID not in", values, "irbDecisionId");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionIdBetween(String value1, String value2) {
            addCriterion("IRB_DECISION_ID between", value1, value2, "irbDecisionId");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionIdNotBetween(String value1, String value2) {
            addCriterion("IRB_DECISION_ID not between", value1, value2, "irbDecisionId");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionNameIsNull() {
            addCriterion("IRB_DECISION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionNameIsNotNull() {
            addCriterion("IRB_DECISION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionNameEqualTo(String value) {
            addCriterion("IRB_DECISION_NAME =", value, "irbDecisionName");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionNameNotEqualTo(String value) {
            addCriterion("IRB_DECISION_NAME <>", value, "irbDecisionName");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionNameGreaterThan(String value) {
            addCriterion("IRB_DECISION_NAME >", value, "irbDecisionName");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionNameGreaterThanOrEqualTo(String value) {
            addCriterion("IRB_DECISION_NAME >=", value, "irbDecisionName");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionNameLessThan(String value) {
            addCriterion("IRB_DECISION_NAME <", value, "irbDecisionName");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionNameLessThanOrEqualTo(String value) {
            addCriterion("IRB_DECISION_NAME <=", value, "irbDecisionName");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionNameLike(String value) {
            addCriterion("IRB_DECISION_NAME like", value, "irbDecisionName");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionNameNotLike(String value) {
            addCriterion("IRB_DECISION_NAME not like", value, "irbDecisionName");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionNameIn(List<String> values) {
            addCriterion("IRB_DECISION_NAME in", values, "irbDecisionName");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionNameNotIn(List<String> values) {
            addCriterion("IRB_DECISION_NAME not in", values, "irbDecisionName");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionNameBetween(String value1, String value2) {
            addCriterion("IRB_DECISION_NAME between", value1, value2, "irbDecisionName");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionNameNotBetween(String value1, String value2) {
            addCriterion("IRB_DECISION_NAME not between", value1, value2, "irbDecisionName");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionDateIsNull() {
            addCriterion("IRB_DECISION_DATE is null");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionDateIsNotNull() {
            addCriterion("IRB_DECISION_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionDateEqualTo(String value) {
            addCriterion("IRB_DECISION_DATE =", value, "irbDecisionDate");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionDateNotEqualTo(String value) {
            addCriterion("IRB_DECISION_DATE <>", value, "irbDecisionDate");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionDateGreaterThan(String value) {
            addCriterion("IRB_DECISION_DATE >", value, "irbDecisionDate");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionDateGreaterThanOrEqualTo(String value) {
            addCriterion("IRB_DECISION_DATE >=", value, "irbDecisionDate");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionDateLessThan(String value) {
            addCriterion("IRB_DECISION_DATE <", value, "irbDecisionDate");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionDateLessThanOrEqualTo(String value) {
            addCriterion("IRB_DECISION_DATE <=", value, "irbDecisionDate");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionDateLike(String value) {
            addCriterion("IRB_DECISION_DATE like", value, "irbDecisionDate");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionDateNotLike(String value) {
            addCriterion("IRB_DECISION_DATE not like", value, "irbDecisionDate");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionDateIn(List<String> values) {
            addCriterion("IRB_DECISION_DATE in", values, "irbDecisionDate");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionDateNotIn(List<String> values) {
            addCriterion("IRB_DECISION_DATE not in", values, "irbDecisionDate");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionDateBetween(String value1, String value2) {
            addCriterion("IRB_DECISION_DATE between", value1, value2, "irbDecisionDate");
            return (Criteria) this;
        }

        public Criteria andIrbDecisionDateNotBetween(String value1, String value2) {
            addCriterion("IRB_DECISION_DATE not between", value1, value2, "irbDecisionDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateIsNull() {
            addCriterion("APPROVE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andApproveDateIsNotNull() {
            addCriterion("APPROVE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andApproveDateEqualTo(String value) {
            addCriterion("APPROVE_DATE =", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateNotEqualTo(String value) {
            addCriterion("APPROVE_DATE <>", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateGreaterThan(String value) {
            addCriterion("APPROVE_DATE >", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateGreaterThanOrEqualTo(String value) {
            addCriterion("APPROVE_DATE >=", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateLessThan(String value) {
            addCriterion("APPROVE_DATE <", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateLessThanOrEqualTo(String value) {
            addCriterion("APPROVE_DATE <=", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateLike(String value) {
            addCriterion("APPROVE_DATE like", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateNotLike(String value) {
            addCriterion("APPROVE_DATE not like", value, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateIn(List<String> values) {
            addCriterion("APPROVE_DATE in", values, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateNotIn(List<String> values) {
            addCriterion("APPROVE_DATE not in", values, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateBetween(String value1, String value2) {
            addCriterion("APPROVE_DATE between", value1, value2, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveDateNotBetween(String value1, String value2) {
            addCriterion("APPROVE_DATE not between", value1, value2, "approveDate");
            return (Criteria) this;
        }

        public Criteria andApproveValidityIsNull() {
            addCriterion("APPROVE_VALIDITY is null");
            return (Criteria) this;
        }

        public Criteria andApproveValidityIsNotNull() {
            addCriterion("APPROVE_VALIDITY is not null");
            return (Criteria) this;
        }

        public Criteria andApproveValidityEqualTo(String value) {
            addCriterion("APPROVE_VALIDITY =", value, "approveValidity");
            return (Criteria) this;
        }

        public Criteria andApproveValidityNotEqualTo(String value) {
            addCriterion("APPROVE_VALIDITY <>", value, "approveValidity");
            return (Criteria) this;
        }

        public Criteria andApproveValidityGreaterThan(String value) {
            addCriterion("APPROVE_VALIDITY >", value, "approveValidity");
            return (Criteria) this;
        }

        public Criteria andApproveValidityGreaterThanOrEqualTo(String value) {
            addCriterion("APPROVE_VALIDITY >=", value, "approveValidity");
            return (Criteria) this;
        }

        public Criteria andApproveValidityLessThan(String value) {
            addCriterion("APPROVE_VALIDITY <", value, "approveValidity");
            return (Criteria) this;
        }

        public Criteria andApproveValidityLessThanOrEqualTo(String value) {
            addCriterion("APPROVE_VALIDITY <=", value, "approveValidity");
            return (Criteria) this;
        }

        public Criteria andApproveValidityLike(String value) {
            addCriterion("APPROVE_VALIDITY like", value, "approveValidity");
            return (Criteria) this;
        }

        public Criteria andApproveValidityNotLike(String value) {
            addCriterion("APPROVE_VALIDITY not like", value, "approveValidity");
            return (Criteria) this;
        }

        public Criteria andApproveValidityIn(List<String> values) {
            addCriterion("APPROVE_VALIDITY in", values, "approveValidity");
            return (Criteria) this;
        }

        public Criteria andApproveValidityNotIn(List<String> values) {
            addCriterion("APPROVE_VALIDITY not in", values, "approveValidity");
            return (Criteria) this;
        }

        public Criteria andApproveValidityBetween(String value1, String value2) {
            addCriterion("APPROVE_VALIDITY between", value1, value2, "approveValidity");
            return (Criteria) this;
        }

        public Criteria andApproveValidityNotBetween(String value1, String value2) {
            addCriterion("APPROVE_VALIDITY not between", value1, value2, "approveValidity");
            return (Criteria) this;
        }

        public Criteria andApproveValidityDateIsNull() {
            addCriterion("APPROVE_VALIDITY_DATE is null");
            return (Criteria) this;
        }

        public Criteria andApproveValidityDateIsNotNull() {
            addCriterion("APPROVE_VALIDITY_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andApproveValidityDateEqualTo(String value) {
            addCriterion("APPROVE_VALIDITY_DATE =", value, "approveValidityDate");
            return (Criteria) this;
        }

        public Criteria andApproveValidityDateNotEqualTo(String value) {
            addCriterion("APPROVE_VALIDITY_DATE <>", value, "approveValidityDate");
            return (Criteria) this;
        }

        public Criteria andApproveValidityDateGreaterThan(String value) {
            addCriterion("APPROVE_VALIDITY_DATE >", value, "approveValidityDate");
            return (Criteria) this;
        }

        public Criteria andApproveValidityDateGreaterThanOrEqualTo(String value) {
            addCriterion("APPROVE_VALIDITY_DATE >=", value, "approveValidityDate");
            return (Criteria) this;
        }

        public Criteria andApproveValidityDateLessThan(String value) {
            addCriterion("APPROVE_VALIDITY_DATE <", value, "approveValidityDate");
            return (Criteria) this;
        }

        public Criteria andApproveValidityDateLessThanOrEqualTo(String value) {
            addCriterion("APPROVE_VALIDITY_DATE <=", value, "approveValidityDate");
            return (Criteria) this;
        }

        public Criteria andApproveValidityDateLike(String value) {
            addCriterion("APPROVE_VALIDITY_DATE like", value, "approveValidityDate");
            return (Criteria) this;
        }

        public Criteria andApproveValidityDateNotLike(String value) {
            addCriterion("APPROVE_VALIDITY_DATE not like", value, "approveValidityDate");
            return (Criteria) this;
        }

        public Criteria andApproveValidityDateIn(List<String> values) {
            addCriterion("APPROVE_VALIDITY_DATE in", values, "approveValidityDate");
            return (Criteria) this;
        }

        public Criteria andApproveValidityDateNotIn(List<String> values) {
            addCriterion("APPROVE_VALIDITY_DATE not in", values, "approveValidityDate");
            return (Criteria) this;
        }

        public Criteria andApproveValidityDateBetween(String value1, String value2) {
            addCriterion("APPROVE_VALIDITY_DATE between", value1, value2, "approveValidityDate");
            return (Criteria) this;
        }

        public Criteria andApproveValidityDateNotBetween(String value1, String value2) {
            addCriterion("APPROVE_VALIDITY_DATE not between", value1, value2, "approveValidityDate");
            return (Criteria) this;
        }

        public Criteria andTrackFrequencyIsNull() {
            addCriterion("TRACK_FREQUENCY is null");
            return (Criteria) this;
        }

        public Criteria andTrackFrequencyIsNotNull() {
            addCriterion("TRACK_FREQUENCY is not null");
            return (Criteria) this;
        }

        public Criteria andTrackFrequencyEqualTo(String value) {
            addCriterion("TRACK_FREQUENCY =", value, "trackFrequency");
            return (Criteria) this;
        }

        public Criteria andTrackFrequencyNotEqualTo(String value) {
            addCriterion("TRACK_FREQUENCY <>", value, "trackFrequency");
            return (Criteria) this;
        }

        public Criteria andTrackFrequencyGreaterThan(String value) {
            addCriterion("TRACK_FREQUENCY >", value, "trackFrequency");
            return (Criteria) this;
        }

        public Criteria andTrackFrequencyGreaterThanOrEqualTo(String value) {
            addCriterion("TRACK_FREQUENCY >=", value, "trackFrequency");
            return (Criteria) this;
        }

        public Criteria andTrackFrequencyLessThan(String value) {
            addCriterion("TRACK_FREQUENCY <", value, "trackFrequency");
            return (Criteria) this;
        }

        public Criteria andTrackFrequencyLessThanOrEqualTo(String value) {
            addCriterion("TRACK_FREQUENCY <=", value, "trackFrequency");
            return (Criteria) this;
        }

        public Criteria andTrackFrequencyLike(String value) {
            addCriterion("TRACK_FREQUENCY like", value, "trackFrequency");
            return (Criteria) this;
        }

        public Criteria andTrackFrequencyNotLike(String value) {
            addCriterion("TRACK_FREQUENCY not like", value, "trackFrequency");
            return (Criteria) this;
        }

        public Criteria andTrackFrequencyIn(List<String> values) {
            addCriterion("TRACK_FREQUENCY in", values, "trackFrequency");
            return (Criteria) this;
        }

        public Criteria andTrackFrequencyNotIn(List<String> values) {
            addCriterion("TRACK_FREQUENCY not in", values, "trackFrequency");
            return (Criteria) this;
        }

        public Criteria andTrackFrequencyBetween(String value1, String value2) {
            addCriterion("TRACK_FREQUENCY between", value1, value2, "trackFrequency");
            return (Criteria) this;
        }

        public Criteria andTrackFrequencyNotBetween(String value1, String value2) {
            addCriterion("TRACK_FREQUENCY not between", value1, value2, "trackFrequency");
            return (Criteria) this;
        }

        public Criteria andTrackDateIsNull() {
            addCriterion("TRACK_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTrackDateIsNotNull() {
            addCriterion("TRACK_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTrackDateEqualTo(String value) {
            addCriterion("TRACK_DATE =", value, "trackDate");
            return (Criteria) this;
        }

        public Criteria andTrackDateNotEqualTo(String value) {
            addCriterion("TRACK_DATE <>", value, "trackDate");
            return (Criteria) this;
        }

        public Criteria andTrackDateGreaterThan(String value) {
            addCriterion("TRACK_DATE >", value, "trackDate");
            return (Criteria) this;
        }

        public Criteria andTrackDateGreaterThanOrEqualTo(String value) {
            addCriterion("TRACK_DATE >=", value, "trackDate");
            return (Criteria) this;
        }

        public Criteria andTrackDateLessThan(String value) {
            addCriterion("TRACK_DATE <", value, "trackDate");
            return (Criteria) this;
        }

        public Criteria andTrackDateLessThanOrEqualTo(String value) {
            addCriterion("TRACK_DATE <=", value, "trackDate");
            return (Criteria) this;
        }

        public Criteria andTrackDateLike(String value) {
            addCriterion("TRACK_DATE like", value, "trackDate");
            return (Criteria) this;
        }

        public Criteria andTrackDateNotLike(String value) {
            addCriterion("TRACK_DATE not like", value, "trackDate");
            return (Criteria) this;
        }

        public Criteria andTrackDateIn(List<String> values) {
            addCriterion("TRACK_DATE in", values, "trackDate");
            return (Criteria) this;
        }

        public Criteria andTrackDateNotIn(List<String> values) {
            addCriterion("TRACK_DATE not in", values, "trackDate");
            return (Criteria) this;
        }

        public Criteria andTrackDateBetween(String value1, String value2) {
            addCriterion("TRACK_DATE between", value1, value2, "trackDate");
            return (Criteria) this;
        }

        public Criteria andTrackDateNotBetween(String value1, String value2) {
            addCriterion("TRACK_DATE not between", value1, value2, "trackDate");
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