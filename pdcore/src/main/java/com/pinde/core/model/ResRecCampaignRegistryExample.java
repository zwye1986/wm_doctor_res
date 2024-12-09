package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResRecCampaignRegistryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResRecCampaignRegistryExample() {
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

        public Criteria andRecFlowIsNull() {
            addCriterion("REC_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRecFlowIsNotNull() {
            addCriterion("REC_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRecFlowEqualTo(String value) {
            addCriterion("REC_FLOW =", value, "recFlow");
            return (Criteria) this;
        }

        public Criteria andRecFlowNotEqualTo(String value) {
            addCriterion("REC_FLOW <>", value, "recFlow");
            return (Criteria) this;
        }

        public Criteria andRecFlowGreaterThan(String value) {
            addCriterion("REC_FLOW >", value, "recFlow");
            return (Criteria) this;
        }

        public Criteria andRecFlowGreaterThanOrEqualTo(String value) {
            addCriterion("REC_FLOW >=", value, "recFlow");
            return (Criteria) this;
        }

        public Criteria andRecFlowLessThan(String value) {
            addCriterion("REC_FLOW <", value, "recFlow");
            return (Criteria) this;
        }

        public Criteria andRecFlowLessThanOrEqualTo(String value) {
            addCriterion("REC_FLOW <=", value, "recFlow");
            return (Criteria) this;
        }

        public Criteria andRecFlowLike(String value) {
            addCriterion("REC_FLOW like", value, "recFlow");
            return (Criteria) this;
        }

        public Criteria andRecFlowNotLike(String value) {
            addCriterion("REC_FLOW not like", value, "recFlow");
            return (Criteria) this;
        }

        public Criteria andRecFlowIn(List<String> values) {
            addCriterion("REC_FLOW in", values, "recFlow");
            return (Criteria) this;
        }

        public Criteria andRecFlowNotIn(List<String> values) {
            addCriterion("REC_FLOW not in", values, "recFlow");
            return (Criteria) this;
        }

        public Criteria andRecFlowBetween(String value1, String value2) {
            addCriterion("REC_FLOW between", value1, value2, "recFlow");
            return (Criteria) this;
        }

        public Criteria andRecFlowNotBetween(String value1, String value2) {
            addCriterion("REC_FLOW not between", value1, value2, "recFlow");
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

        public Criteria andOrgNameIsNull() {
            addCriterion("ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("ORG_NAME =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("ORG_NAME <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("ORG_NAME >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NAME >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("ORG_NAME <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_NAME <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("ORG_NAME like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("ORG_NAME not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("ORG_NAME in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("ORG_NAME not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("ORG_NAME between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("ORG_NAME not between", value1, value2, "orgName");
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

        public Criteria andSchDeptFlowIsNull() {
            addCriterion("SCH_DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowIsNotNull() {
            addCriterion("SCH_DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW =", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW <>", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowGreaterThan(String value) {
            addCriterion("SCH_DEPT_FLOW >", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW >=", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowLessThan(String value) {
            addCriterion("SCH_DEPT_FLOW <", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW <=", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowLike(String value) {
            addCriterion("SCH_DEPT_FLOW like", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotLike(String value) {
            addCriterion("SCH_DEPT_FLOW not like", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowIn(List<String> values) {
            addCriterion("SCH_DEPT_FLOW in", values, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotIn(List<String> values) {
            addCriterion("SCH_DEPT_FLOW not in", values, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_FLOW between", value1, value2, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_FLOW not between", value1, value2, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameIsNull() {
            addCriterion("SCH_DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameIsNotNull() {
            addCriterion("SCH_DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME =", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME <>", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameGreaterThan(String value) {
            addCriterion("SCH_DEPT_NAME >", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME >=", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameLessThan(String value) {
            addCriterion("SCH_DEPT_NAME <", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameLessThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME <=", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameLike(String value) {
            addCriterion("SCH_DEPT_NAME like", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotLike(String value) {
            addCriterion("SCH_DEPT_NAME not like", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameIn(List<String> values) {
            addCriterion("SCH_DEPT_NAME in", values, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotIn(List<String> values) {
            addCriterion("SCH_DEPT_NAME not in", values, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_NAME between", value1, value2, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_NAME not between", value1, value2, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andRecTypeIdIsNull() {
            addCriterion("REC_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andRecTypeIdIsNotNull() {
            addCriterion("REC_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRecTypeIdEqualTo(String value) {
            addCriterion("REC_TYPE_ID =", value, "recTypeId");
            return (Criteria) this;
        }

        public Criteria andRecTypeIdNotEqualTo(String value) {
            addCriterion("REC_TYPE_ID <>", value, "recTypeId");
            return (Criteria) this;
        }

        public Criteria andRecTypeIdGreaterThan(String value) {
            addCriterion("REC_TYPE_ID >", value, "recTypeId");
            return (Criteria) this;
        }

        public Criteria andRecTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("REC_TYPE_ID >=", value, "recTypeId");
            return (Criteria) this;
        }

        public Criteria andRecTypeIdLessThan(String value) {
            addCriterion("REC_TYPE_ID <", value, "recTypeId");
            return (Criteria) this;
        }

        public Criteria andRecTypeIdLessThanOrEqualTo(String value) {
            addCriterion("REC_TYPE_ID <=", value, "recTypeId");
            return (Criteria) this;
        }

        public Criteria andRecTypeIdLike(String value) {
            addCriterion("REC_TYPE_ID like", value, "recTypeId");
            return (Criteria) this;
        }

        public Criteria andRecTypeIdNotLike(String value) {
            addCriterion("REC_TYPE_ID not like", value, "recTypeId");
            return (Criteria) this;
        }

        public Criteria andRecTypeIdIn(List<String> values) {
            addCriterion("REC_TYPE_ID in", values, "recTypeId");
            return (Criteria) this;
        }

        public Criteria andRecTypeIdNotIn(List<String> values) {
            addCriterion("REC_TYPE_ID not in", values, "recTypeId");
            return (Criteria) this;
        }

        public Criteria andRecTypeIdBetween(String value1, String value2) {
            addCriterion("REC_TYPE_ID between", value1, value2, "recTypeId");
            return (Criteria) this;
        }

        public Criteria andRecTypeIdNotBetween(String value1, String value2) {
            addCriterion("REC_TYPE_ID not between", value1, value2, "recTypeId");
            return (Criteria) this;
        }

        public Criteria andRecTypeNameIsNull() {
            addCriterion("REC_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRecTypeNameIsNotNull() {
            addCriterion("REC_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRecTypeNameEqualTo(String value) {
            addCriterion("REC_TYPE_NAME =", value, "recTypeName");
            return (Criteria) this;
        }

        public Criteria andRecTypeNameNotEqualTo(String value) {
            addCriterion("REC_TYPE_NAME <>", value, "recTypeName");
            return (Criteria) this;
        }

        public Criteria andRecTypeNameGreaterThan(String value) {
            addCriterion("REC_TYPE_NAME >", value, "recTypeName");
            return (Criteria) this;
        }

        public Criteria andRecTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("REC_TYPE_NAME >=", value, "recTypeName");
            return (Criteria) this;
        }

        public Criteria andRecTypeNameLessThan(String value) {
            addCriterion("REC_TYPE_NAME <", value, "recTypeName");
            return (Criteria) this;
        }

        public Criteria andRecTypeNameLessThanOrEqualTo(String value) {
            addCriterion("REC_TYPE_NAME <=", value, "recTypeName");
            return (Criteria) this;
        }

        public Criteria andRecTypeNameLike(String value) {
            addCriterion("REC_TYPE_NAME like", value, "recTypeName");
            return (Criteria) this;
        }

        public Criteria andRecTypeNameNotLike(String value) {
            addCriterion("REC_TYPE_NAME not like", value, "recTypeName");
            return (Criteria) this;
        }

        public Criteria andRecTypeNameIn(List<String> values) {
            addCriterion("REC_TYPE_NAME in", values, "recTypeName");
            return (Criteria) this;
        }

        public Criteria andRecTypeNameNotIn(List<String> values) {
            addCriterion("REC_TYPE_NAME not in", values, "recTypeName");
            return (Criteria) this;
        }

        public Criteria andRecTypeNameBetween(String value1, String value2) {
            addCriterion("REC_TYPE_NAME between", value1, value2, "recTypeName");
            return (Criteria) this;
        }

        public Criteria andRecTypeNameNotBetween(String value1, String value2) {
            addCriterion("REC_TYPE_NAME not between", value1, value2, "recTypeName");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNull() {
            addCriterion("ITEM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNotNull() {
            addCriterion("ITEM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andItemNameEqualTo(String value) {
            addCriterion("ITEM_NAME =", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotEqualTo(String value) {
            addCriterion("ITEM_NAME <>", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThan(String value) {
            addCriterion("ITEM_NAME >", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_NAME >=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThan(String value) {
            addCriterion("ITEM_NAME <", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThanOrEqualTo(String value) {
            addCriterion("ITEM_NAME <=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLike(String value) {
            addCriterion("ITEM_NAME like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotLike(String value) {
            addCriterion("ITEM_NAME not like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameIn(List<String> values) {
            addCriterion("ITEM_NAME in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotIn(List<String> values) {
            addCriterion("ITEM_NAME not in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameBetween(String value1, String value2) {
            addCriterion("ITEM_NAME between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotBetween(String value1, String value2) {
            addCriterion("ITEM_NAME not between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andRecVersionIsNull() {
            addCriterion("REC_VERSION is null");
            return (Criteria) this;
        }

        public Criteria andRecVersionIsNotNull() {
            addCriterion("REC_VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andRecVersionEqualTo(String value) {
            addCriterion("REC_VERSION =", value, "recVersion");
            return (Criteria) this;
        }

        public Criteria andRecVersionNotEqualTo(String value) {
            addCriterion("REC_VERSION <>", value, "recVersion");
            return (Criteria) this;
        }

        public Criteria andRecVersionGreaterThan(String value) {
            addCriterion("REC_VERSION >", value, "recVersion");
            return (Criteria) this;
        }

        public Criteria andRecVersionGreaterThanOrEqualTo(String value) {
            addCriterion("REC_VERSION >=", value, "recVersion");
            return (Criteria) this;
        }

        public Criteria andRecVersionLessThan(String value) {
            addCriterion("REC_VERSION <", value, "recVersion");
            return (Criteria) this;
        }

        public Criteria andRecVersionLessThanOrEqualTo(String value) {
            addCriterion("REC_VERSION <=", value, "recVersion");
            return (Criteria) this;
        }

        public Criteria andRecVersionLike(String value) {
            addCriterion("REC_VERSION like", value, "recVersion");
            return (Criteria) this;
        }

        public Criteria andRecVersionNotLike(String value) {
            addCriterion("REC_VERSION not like", value, "recVersion");
            return (Criteria) this;
        }

        public Criteria andRecVersionIn(List<String> values) {
            addCriterion("REC_VERSION in", values, "recVersion");
            return (Criteria) this;
        }

        public Criteria andRecVersionNotIn(List<String> values) {
            addCriterion("REC_VERSION not in", values, "recVersion");
            return (Criteria) this;
        }

        public Criteria andRecVersionBetween(String value1, String value2) {
            addCriterion("REC_VERSION between", value1, value2, "recVersion");
            return (Criteria) this;
        }

        public Criteria andRecVersionNotBetween(String value1, String value2) {
            addCriterion("REC_VERSION not between", value1, value2, "recVersion");
            return (Criteria) this;
        }

        public Criteria andOperTimeIsNull() {
            addCriterion("OPER_TIME is null");
            return (Criteria) this;
        }

        public Criteria andOperTimeIsNotNull() {
            addCriterion("OPER_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andOperTimeEqualTo(String value) {
            addCriterion("OPER_TIME =", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeNotEqualTo(String value) {
            addCriterion("OPER_TIME <>", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeGreaterThan(String value) {
            addCriterion("OPER_TIME >", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_TIME >=", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeLessThan(String value) {
            addCriterion("OPER_TIME <", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeLessThanOrEqualTo(String value) {
            addCriterion("OPER_TIME <=", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeLike(String value) {
            addCriterion("OPER_TIME like", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeNotLike(String value) {
            addCriterion("OPER_TIME not like", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeIn(List<String> values) {
            addCriterion("OPER_TIME in", values, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeNotIn(List<String> values) {
            addCriterion("OPER_TIME not in", values, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeBetween(String value1, String value2) {
            addCriterion("OPER_TIME between", value1, value2, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeNotBetween(String value1, String value2) {
            addCriterion("OPER_TIME not between", value1, value2, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowIsNull() {
            addCriterion("OPER_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowIsNotNull() {
            addCriterion("OPER_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowEqualTo(String value) {
            addCriterion("OPER_USER_FLOW =", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowNotEqualTo(String value) {
            addCriterion("OPER_USER_FLOW <>", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowGreaterThan(String value) {
            addCriterion("OPER_USER_FLOW >", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_USER_FLOW >=", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowLessThan(String value) {
            addCriterion("OPER_USER_FLOW <", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowLessThanOrEqualTo(String value) {
            addCriterion("OPER_USER_FLOW <=", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowLike(String value) {
            addCriterion("OPER_USER_FLOW like", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowNotLike(String value) {
            addCriterion("OPER_USER_FLOW not like", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowIn(List<String> values) {
            addCriterion("OPER_USER_FLOW in", values, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowNotIn(List<String> values) {
            addCriterion("OPER_USER_FLOW not in", values, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowBetween(String value1, String value2) {
            addCriterion("OPER_USER_FLOW between", value1, value2, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowNotBetween(String value1, String value2) {
            addCriterion("OPER_USER_FLOW not between", value1, value2, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserNameIsNull() {
            addCriterion("OPER_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOperUserNameIsNotNull() {
            addCriterion("OPER_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOperUserNameEqualTo(String value) {
            addCriterion("OPER_USER_NAME =", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotEqualTo(String value) {
            addCriterion("OPER_USER_NAME <>", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameGreaterThan(String value) {
            addCriterion("OPER_USER_NAME >", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_USER_NAME >=", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameLessThan(String value) {
            addCriterion("OPER_USER_NAME <", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameLessThanOrEqualTo(String value) {
            addCriterion("OPER_USER_NAME <=", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameLike(String value) {
            addCriterion("OPER_USER_NAME like", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotLike(String value) {
            addCriterion("OPER_USER_NAME not like", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameIn(List<String> values) {
            addCriterion("OPER_USER_NAME in", values, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotIn(List<String> values) {
            addCriterion("OPER_USER_NAME not in", values, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameBetween(String value1, String value2) {
            addCriterion("OPER_USER_NAME between", value1, value2, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotBetween(String value1, String value2) {
            addCriterion("OPER_USER_NAME not between", value1, value2, "operUserName");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNull() {
            addCriterion("STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNotNull() {
            addCriterion("STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStatusIdEqualTo(String value) {
            addCriterion("STATUS_ID =", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotEqualTo(String value) {
            addCriterion("STATUS_ID <>", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThan(String value) {
            addCriterion("STATUS_ID >", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_ID >=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThan(String value) {
            addCriterion("STATUS_ID <", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThanOrEqualTo(String value) {
            addCriterion("STATUS_ID <=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLike(String value) {
            addCriterion("STATUS_ID like", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotLike(String value) {
            addCriterion("STATUS_ID not like", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdIn(List<String> values) {
            addCriterion("STATUS_ID in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotIn(List<String> values) {
            addCriterion("STATUS_ID not in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdBetween(String value1, String value2) {
            addCriterion("STATUS_ID between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotBetween(String value1, String value2) {
            addCriterion("STATUS_ID not between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusNameIsNull() {
            addCriterion("STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStatusNameIsNotNull() {
            addCriterion("STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStatusNameEqualTo(String value) {
            addCriterion("STATUS_NAME =", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotEqualTo(String value) {
            addCriterion("STATUS_NAME <>", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameGreaterThan(String value) {
            addCriterion("STATUS_NAME >", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_NAME >=", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLessThan(String value) {
            addCriterion("STATUS_NAME <", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLessThanOrEqualTo(String value) {
            addCriterion("STATUS_NAME <=", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLike(String value) {
            addCriterion("STATUS_NAME like", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotLike(String value) {
            addCriterion("STATUS_NAME not like", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameIn(List<String> values) {
            addCriterion("STATUS_NAME in", values, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotIn(List<String> values) {
            addCriterion("STATUS_NAME not in", values, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameBetween(String value1, String value2) {
            addCriterion("STATUS_NAME between", value1, value2, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotBetween(String value1, String value2) {
            addCriterion("STATUS_NAME not between", value1, value2, "statusName");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNull() {
            addCriterion("AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNotNull() {
            addCriterion("AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeEqualTo(String value) {
            addCriterion("AUDIT_TIME =", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotEqualTo(String value) {
            addCriterion("AUDIT_TIME <>", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThan(String value) {
            addCriterion("AUDIT_TIME >", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_TIME >=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThan(String value) {
            addCriterion("AUDIT_TIME <", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_TIME <=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLike(String value) {
            addCriterion("AUDIT_TIME like", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotLike(String value) {
            addCriterion("AUDIT_TIME not like", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIn(List<String> values) {
            addCriterion("AUDIT_TIME in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotIn(List<String> values) {
            addCriterion("AUDIT_TIME not in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeBetween(String value1, String value2) {
            addCriterion("AUDIT_TIME between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotBetween(String value1, String value2) {
            addCriterion("AUDIT_TIME not between", value1, value2, "auditTime");
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

        public Criteria andAuditStatusIdIsNull() {
            addCriterion("AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIsNotNull() {
            addCriterion("AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID =", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID <>", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdGreaterThan(String value) {
            addCriterion("AUDIT_STATUS_ID >", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID >=", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLessThan(String value) {
            addCriterion("AUDIT_STATUS_ID <", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID <=", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLike(String value) {
            addCriterion("AUDIT_STATUS_ID like", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotLike(String value) {
            addCriterion("AUDIT_STATUS_ID not like", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIn(List<String> values) {
            addCriterion("AUDIT_STATUS_ID in", values, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotIn(List<String> values) {
            addCriterion("AUDIT_STATUS_ID not in", values, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_ID between", value1, value2, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_ID not between", value1, value2, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIsNull() {
            addCriterion("AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIsNotNull() {
            addCriterion("AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME =", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME <>", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameGreaterThan(String value) {
            addCriterion("AUDIT_STATUS_NAME >", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME >=", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLessThan(String value) {
            addCriterion("AUDIT_STATUS_NAME <", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME <=", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLike(String value) {
            addCriterion("AUDIT_STATUS_NAME like", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotLike(String value) {
            addCriterion("AUDIT_STATUS_NAME not like", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIn(List<String> values) {
            addCriterion("AUDIT_STATUS_NAME in", values, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotIn(List<String> values) {
            addCriterion("AUDIT_STATUS_NAME not in", values, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_NAME between", value1, value2, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_NAME not between", value1, value2, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeIsNull() {
            addCriterion("HEAD_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeIsNotNull() {
            addCriterion("HEAD_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeEqualTo(String value) {
            addCriterion("HEAD_AUDIT_TIME =", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeNotEqualTo(String value) {
            addCriterion("HEAD_AUDIT_TIME <>", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeGreaterThan(String value) {
            addCriterion("HEAD_AUDIT_TIME >", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_AUDIT_TIME >=", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeLessThan(String value) {
            addCriterion("HEAD_AUDIT_TIME <", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("HEAD_AUDIT_TIME <=", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeLike(String value) {
            addCriterion("HEAD_AUDIT_TIME like", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeNotLike(String value) {
            addCriterion("HEAD_AUDIT_TIME not like", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeIn(List<String> values) {
            addCriterion("HEAD_AUDIT_TIME in", values, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeNotIn(List<String> values) {
            addCriterion("HEAD_AUDIT_TIME not in", values, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeBetween(String value1, String value2) {
            addCriterion("HEAD_AUDIT_TIME between", value1, value2, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeNotBetween(String value1, String value2) {
            addCriterion("HEAD_AUDIT_TIME not between", value1, value2, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserFlowIsNull() {
            addCriterion("HEAD_AUDIT_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserFlowIsNotNull() {
            addCriterion("HEAD_AUDIT_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserFlowEqualTo(String value) {
            addCriterion("HEAD_AUDIT_USER_FLOW =", value, "headAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserFlowNotEqualTo(String value) {
            addCriterion("HEAD_AUDIT_USER_FLOW <>", value, "headAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserFlowGreaterThan(String value) {
            addCriterion("HEAD_AUDIT_USER_FLOW >", value, "headAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_AUDIT_USER_FLOW >=", value, "headAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserFlowLessThan(String value) {
            addCriterion("HEAD_AUDIT_USER_FLOW <", value, "headAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserFlowLessThanOrEqualTo(String value) {
            addCriterion("HEAD_AUDIT_USER_FLOW <=", value, "headAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserFlowLike(String value) {
            addCriterion("HEAD_AUDIT_USER_FLOW like", value, "headAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserFlowNotLike(String value) {
            addCriterion("HEAD_AUDIT_USER_FLOW not like", value, "headAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserFlowIn(List<String> values) {
            addCriterion("HEAD_AUDIT_USER_FLOW in", values, "headAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserFlowNotIn(List<String> values) {
            addCriterion("HEAD_AUDIT_USER_FLOW not in", values, "headAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserFlowBetween(String value1, String value2) {
            addCriterion("HEAD_AUDIT_USER_FLOW between", value1, value2, "headAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserFlowNotBetween(String value1, String value2) {
            addCriterion("HEAD_AUDIT_USER_FLOW not between", value1, value2, "headAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserNameIsNull() {
            addCriterion("HEAD_AUDIT_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserNameIsNotNull() {
            addCriterion("HEAD_AUDIT_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserNameEqualTo(String value) {
            addCriterion("HEAD_AUDIT_USER_NAME =", value, "headAuditUserName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserNameNotEqualTo(String value) {
            addCriterion("HEAD_AUDIT_USER_NAME <>", value, "headAuditUserName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserNameGreaterThan(String value) {
            addCriterion("HEAD_AUDIT_USER_NAME >", value, "headAuditUserName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_AUDIT_USER_NAME >=", value, "headAuditUserName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserNameLessThan(String value) {
            addCriterion("HEAD_AUDIT_USER_NAME <", value, "headAuditUserName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserNameLessThanOrEqualTo(String value) {
            addCriterion("HEAD_AUDIT_USER_NAME <=", value, "headAuditUserName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserNameLike(String value) {
            addCriterion("HEAD_AUDIT_USER_NAME like", value, "headAuditUserName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserNameNotLike(String value) {
            addCriterion("HEAD_AUDIT_USER_NAME not like", value, "headAuditUserName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserNameIn(List<String> values) {
            addCriterion("HEAD_AUDIT_USER_NAME in", values, "headAuditUserName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserNameNotIn(List<String> values) {
            addCriterion("HEAD_AUDIT_USER_NAME not in", values, "headAuditUserName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserNameBetween(String value1, String value2) {
            addCriterion("HEAD_AUDIT_USER_NAME between", value1, value2, "headAuditUserName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditUserNameNotBetween(String value1, String value2) {
            addCriterion("HEAD_AUDIT_USER_NAME not between", value1, value2, "headAuditUserName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusIdIsNull() {
            addCriterion("HEAD_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusIdIsNotNull() {
            addCriterion("HEAD_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusIdEqualTo(String value) {
            addCriterion("HEAD_AUDIT_STATUS_ID =", value, "headAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusIdNotEqualTo(String value) {
            addCriterion("HEAD_AUDIT_STATUS_ID <>", value, "headAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusIdGreaterThan(String value) {
            addCriterion("HEAD_AUDIT_STATUS_ID >", value, "headAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_AUDIT_STATUS_ID >=", value, "headAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusIdLessThan(String value) {
            addCriterion("HEAD_AUDIT_STATUS_ID <", value, "headAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("HEAD_AUDIT_STATUS_ID <=", value, "headAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusIdLike(String value) {
            addCriterion("HEAD_AUDIT_STATUS_ID like", value, "headAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusIdNotLike(String value) {
            addCriterion("HEAD_AUDIT_STATUS_ID not like", value, "headAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusIdIn(List<String> values) {
            addCriterion("HEAD_AUDIT_STATUS_ID in", values, "headAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusIdNotIn(List<String> values) {
            addCriterion("HEAD_AUDIT_STATUS_ID not in", values, "headAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusIdBetween(String value1, String value2) {
            addCriterion("HEAD_AUDIT_STATUS_ID between", value1, value2, "headAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("HEAD_AUDIT_STATUS_ID not between", value1, value2, "headAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusNameIsNull() {
            addCriterion("HEAD_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusNameIsNotNull() {
            addCriterion("HEAD_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusNameEqualTo(String value) {
            addCriterion("HEAD_AUDIT_STATUS_NAME =", value, "headAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusNameNotEqualTo(String value) {
            addCriterion("HEAD_AUDIT_STATUS_NAME <>", value, "headAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusNameGreaterThan(String value) {
            addCriterion("HEAD_AUDIT_STATUS_NAME >", value, "headAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_AUDIT_STATUS_NAME >=", value, "headAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusNameLessThan(String value) {
            addCriterion("HEAD_AUDIT_STATUS_NAME <", value, "headAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("HEAD_AUDIT_STATUS_NAME <=", value, "headAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusNameLike(String value) {
            addCriterion("HEAD_AUDIT_STATUS_NAME like", value, "headAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusNameNotLike(String value) {
            addCriterion("HEAD_AUDIT_STATUS_NAME not like", value, "headAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusNameIn(List<String> values) {
            addCriterion("HEAD_AUDIT_STATUS_NAME in", values, "headAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusNameNotIn(List<String> values) {
            addCriterion("HEAD_AUDIT_STATUS_NAME not in", values, "headAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusNameBetween(String value1, String value2) {
            addCriterion("HEAD_AUDIT_STATUS_NAME between", value1, value2, "headAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeadAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("HEAD_AUDIT_STATUS_NAME not between", value1, value2, "headAuditStatusName");
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

        public Criteria andManagerAuditTimeIsNull() {
            addCriterion("MANAGER_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeIsNotNull() {
            addCriterion("MANAGER_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_TIME =", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeNotEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_TIME <>", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeGreaterThan(String value) {
            addCriterion("MANAGER_AUDIT_TIME >", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_TIME >=", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeLessThan(String value) {
            addCriterion("MANAGER_AUDIT_TIME <", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_TIME <=", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeLike(String value) {
            addCriterion("MANAGER_AUDIT_TIME like", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeNotLike(String value) {
            addCriterion("MANAGER_AUDIT_TIME not like", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeIn(List<String> values) {
            addCriterion("MANAGER_AUDIT_TIME in", values, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeNotIn(List<String> values) {
            addCriterion("MANAGER_AUDIT_TIME not in", values, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeBetween(String value1, String value2) {
            addCriterion("MANAGER_AUDIT_TIME between", value1, value2, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeNotBetween(String value1, String value2) {
            addCriterion("MANAGER_AUDIT_TIME not between", value1, value2, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserFlowIsNull() {
            addCriterion("MANAGER_AUDIT_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserFlowIsNotNull() {
            addCriterion("MANAGER_AUDIT_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserFlowEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_USER_FLOW =", value, "managerAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserFlowNotEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_USER_FLOW <>", value, "managerAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserFlowGreaterThan(String value) {
            addCriterion("MANAGER_AUDIT_USER_FLOW >", value, "managerAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_USER_FLOW >=", value, "managerAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserFlowLessThan(String value) {
            addCriterion("MANAGER_AUDIT_USER_FLOW <", value, "managerAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserFlowLessThanOrEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_USER_FLOW <=", value, "managerAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserFlowLike(String value) {
            addCriterion("MANAGER_AUDIT_USER_FLOW like", value, "managerAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserFlowNotLike(String value) {
            addCriterion("MANAGER_AUDIT_USER_FLOW not like", value, "managerAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserFlowIn(List<String> values) {
            addCriterion("MANAGER_AUDIT_USER_FLOW in", values, "managerAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserFlowNotIn(List<String> values) {
            addCriterion("MANAGER_AUDIT_USER_FLOW not in", values, "managerAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserFlowBetween(String value1, String value2) {
            addCriterion("MANAGER_AUDIT_USER_FLOW between", value1, value2, "managerAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserFlowNotBetween(String value1, String value2) {
            addCriterion("MANAGER_AUDIT_USER_FLOW not between", value1, value2, "managerAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserNameIsNull() {
            addCriterion("MANAGER_AUDIT_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserNameIsNotNull() {
            addCriterion("MANAGER_AUDIT_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserNameEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_USER_NAME =", value, "managerAuditUserName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserNameNotEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_USER_NAME <>", value, "managerAuditUserName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserNameGreaterThan(String value) {
            addCriterion("MANAGER_AUDIT_USER_NAME >", value, "managerAuditUserName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_USER_NAME >=", value, "managerAuditUserName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserNameLessThan(String value) {
            addCriterion("MANAGER_AUDIT_USER_NAME <", value, "managerAuditUserName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserNameLessThanOrEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_USER_NAME <=", value, "managerAuditUserName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserNameLike(String value) {
            addCriterion("MANAGER_AUDIT_USER_NAME like", value, "managerAuditUserName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserNameNotLike(String value) {
            addCriterion("MANAGER_AUDIT_USER_NAME not like", value, "managerAuditUserName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserNameIn(List<String> values) {
            addCriterion("MANAGER_AUDIT_USER_NAME in", values, "managerAuditUserName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserNameNotIn(List<String> values) {
            addCriterion("MANAGER_AUDIT_USER_NAME not in", values, "managerAuditUserName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserNameBetween(String value1, String value2) {
            addCriterion("MANAGER_AUDIT_USER_NAME between", value1, value2, "managerAuditUserName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditUserNameNotBetween(String value1, String value2) {
            addCriterion("MANAGER_AUDIT_USER_NAME not between", value1, value2, "managerAuditUserName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusIdIsNull() {
            addCriterion("MANAGER_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusIdIsNotNull() {
            addCriterion("MANAGER_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusIdEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_STATUS_ID =", value, "managerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusIdNotEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_STATUS_ID <>", value, "managerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusIdGreaterThan(String value) {
            addCriterion("MANAGER_AUDIT_STATUS_ID >", value, "managerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_STATUS_ID >=", value, "managerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusIdLessThan(String value) {
            addCriterion("MANAGER_AUDIT_STATUS_ID <", value, "managerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_STATUS_ID <=", value, "managerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusIdLike(String value) {
            addCriterion("MANAGER_AUDIT_STATUS_ID like", value, "managerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusIdNotLike(String value) {
            addCriterion("MANAGER_AUDIT_STATUS_ID not like", value, "managerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusIdIn(List<String> values) {
            addCriterion("MANAGER_AUDIT_STATUS_ID in", values, "managerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusIdNotIn(List<String> values) {
            addCriterion("MANAGER_AUDIT_STATUS_ID not in", values, "managerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusIdBetween(String value1, String value2) {
            addCriterion("MANAGER_AUDIT_STATUS_ID between", value1, value2, "managerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("MANAGER_AUDIT_STATUS_ID not between", value1, value2, "managerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusNameIsNull() {
            addCriterion("MANAGER_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusNameIsNotNull() {
            addCriterion("MANAGER_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusNameEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_STATUS_NAME =", value, "managerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusNameNotEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_STATUS_NAME <>", value, "managerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusNameGreaterThan(String value) {
            addCriterion("MANAGER_AUDIT_STATUS_NAME >", value, "managerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_STATUS_NAME >=", value, "managerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusNameLessThan(String value) {
            addCriterion("MANAGER_AUDIT_STATUS_NAME <", value, "managerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_STATUS_NAME <=", value, "managerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusNameLike(String value) {
            addCriterion("MANAGER_AUDIT_STATUS_NAME like", value, "managerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusNameNotLike(String value) {
            addCriterion("MANAGER_AUDIT_STATUS_NAME not like", value, "managerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusNameIn(List<String> values) {
            addCriterion("MANAGER_AUDIT_STATUS_NAME in", values, "managerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusNameNotIn(List<String> values) {
            addCriterion("MANAGER_AUDIT_STATUS_NAME not in", values, "managerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusNameBetween(String value1, String value2) {
            addCriterion("MANAGER_AUDIT_STATUS_NAME between", value1, value2, "managerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andManagerAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("MANAGER_AUDIT_STATUS_NAME not between", value1, value2, "managerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditTimeIsNull() {
            addCriterion("ADMIN_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAdminAuditTimeIsNotNull() {
            addCriterion("ADMIN_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAdminAuditTimeEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_TIME =", value, "adminAuditTime");
            return (Criteria) this;
        }

        public Criteria andAdminAuditTimeNotEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_TIME <>", value, "adminAuditTime");
            return (Criteria) this;
        }

        public Criteria andAdminAuditTimeGreaterThan(String value) {
            addCriterion("ADMIN_AUDIT_TIME >", value, "adminAuditTime");
            return (Criteria) this;
        }

        public Criteria andAdminAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_TIME >=", value, "adminAuditTime");
            return (Criteria) this;
        }

        public Criteria andAdminAuditTimeLessThan(String value) {
            addCriterion("ADMIN_AUDIT_TIME <", value, "adminAuditTime");
            return (Criteria) this;
        }

        public Criteria andAdminAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_TIME <=", value, "adminAuditTime");
            return (Criteria) this;
        }

        public Criteria andAdminAuditTimeLike(String value) {
            addCriterion("ADMIN_AUDIT_TIME like", value, "adminAuditTime");
            return (Criteria) this;
        }

        public Criteria andAdminAuditTimeNotLike(String value) {
            addCriterion("ADMIN_AUDIT_TIME not like", value, "adminAuditTime");
            return (Criteria) this;
        }

        public Criteria andAdminAuditTimeIn(List<String> values) {
            addCriterion("ADMIN_AUDIT_TIME in", values, "adminAuditTime");
            return (Criteria) this;
        }

        public Criteria andAdminAuditTimeNotIn(List<String> values) {
            addCriterion("ADMIN_AUDIT_TIME not in", values, "adminAuditTime");
            return (Criteria) this;
        }

        public Criteria andAdminAuditTimeBetween(String value1, String value2) {
            addCriterion("ADMIN_AUDIT_TIME between", value1, value2, "adminAuditTime");
            return (Criteria) this;
        }

        public Criteria andAdminAuditTimeNotBetween(String value1, String value2) {
            addCriterion("ADMIN_AUDIT_TIME not between", value1, value2, "adminAuditTime");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserFlowIsNull() {
            addCriterion("ADMIN_AUDIT_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserFlowIsNotNull() {
            addCriterion("ADMIN_AUDIT_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserFlowEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_USER_FLOW =", value, "adminAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserFlowNotEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_USER_FLOW <>", value, "adminAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserFlowGreaterThan(String value) {
            addCriterion("ADMIN_AUDIT_USER_FLOW >", value, "adminAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_USER_FLOW >=", value, "adminAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserFlowLessThan(String value) {
            addCriterion("ADMIN_AUDIT_USER_FLOW <", value, "adminAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserFlowLessThanOrEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_USER_FLOW <=", value, "adminAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserFlowLike(String value) {
            addCriterion("ADMIN_AUDIT_USER_FLOW like", value, "adminAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserFlowNotLike(String value) {
            addCriterion("ADMIN_AUDIT_USER_FLOW not like", value, "adminAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserFlowIn(List<String> values) {
            addCriterion("ADMIN_AUDIT_USER_FLOW in", values, "adminAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserFlowNotIn(List<String> values) {
            addCriterion("ADMIN_AUDIT_USER_FLOW not in", values, "adminAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserFlowBetween(String value1, String value2) {
            addCriterion("ADMIN_AUDIT_USER_FLOW between", value1, value2, "adminAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserFlowNotBetween(String value1, String value2) {
            addCriterion("ADMIN_AUDIT_USER_FLOW not between", value1, value2, "adminAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserNameIsNull() {
            addCriterion("ADMIN_AUDIT_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserNameIsNotNull() {
            addCriterion("ADMIN_AUDIT_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserNameEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_USER_NAME =", value, "adminAuditUserName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserNameNotEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_USER_NAME <>", value, "adminAuditUserName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserNameGreaterThan(String value) {
            addCriterion("ADMIN_AUDIT_USER_NAME >", value, "adminAuditUserName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_USER_NAME >=", value, "adminAuditUserName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserNameLessThan(String value) {
            addCriterion("ADMIN_AUDIT_USER_NAME <", value, "adminAuditUserName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserNameLessThanOrEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_USER_NAME <=", value, "adminAuditUserName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserNameLike(String value) {
            addCriterion("ADMIN_AUDIT_USER_NAME like", value, "adminAuditUserName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserNameNotLike(String value) {
            addCriterion("ADMIN_AUDIT_USER_NAME not like", value, "adminAuditUserName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserNameIn(List<String> values) {
            addCriterion("ADMIN_AUDIT_USER_NAME in", values, "adminAuditUserName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserNameNotIn(List<String> values) {
            addCriterion("ADMIN_AUDIT_USER_NAME not in", values, "adminAuditUserName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserNameBetween(String value1, String value2) {
            addCriterion("ADMIN_AUDIT_USER_NAME between", value1, value2, "adminAuditUserName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditUserNameNotBetween(String value1, String value2) {
            addCriterion("ADMIN_AUDIT_USER_NAME not between", value1, value2, "adminAuditUserName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusIdIsNull() {
            addCriterion("ADMIN_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusIdIsNotNull() {
            addCriterion("ADMIN_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusIdEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_STATUS_ID =", value, "adminAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusIdNotEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_STATUS_ID <>", value, "adminAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusIdGreaterThan(String value) {
            addCriterion("ADMIN_AUDIT_STATUS_ID >", value, "adminAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_STATUS_ID >=", value, "adminAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusIdLessThan(String value) {
            addCriterion("ADMIN_AUDIT_STATUS_ID <", value, "adminAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_STATUS_ID <=", value, "adminAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusIdLike(String value) {
            addCriterion("ADMIN_AUDIT_STATUS_ID like", value, "adminAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusIdNotLike(String value) {
            addCriterion("ADMIN_AUDIT_STATUS_ID not like", value, "adminAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusIdIn(List<String> values) {
            addCriterion("ADMIN_AUDIT_STATUS_ID in", values, "adminAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusIdNotIn(List<String> values) {
            addCriterion("ADMIN_AUDIT_STATUS_ID not in", values, "adminAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusIdBetween(String value1, String value2) {
            addCriterion("ADMIN_AUDIT_STATUS_ID between", value1, value2, "adminAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("ADMIN_AUDIT_STATUS_ID not between", value1, value2, "adminAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusNameIsNull() {
            addCriterion("ADMIN_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusNameIsNotNull() {
            addCriterion("ADMIN_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusNameEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_STATUS_NAME =", value, "adminAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusNameNotEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_STATUS_NAME <>", value, "adminAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusNameGreaterThan(String value) {
            addCriterion("ADMIN_AUDIT_STATUS_NAME >", value, "adminAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_STATUS_NAME >=", value, "adminAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusNameLessThan(String value) {
            addCriterion("ADMIN_AUDIT_STATUS_NAME <", value, "adminAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("ADMIN_AUDIT_STATUS_NAME <=", value, "adminAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusNameLike(String value) {
            addCriterion("ADMIN_AUDIT_STATUS_NAME like", value, "adminAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusNameNotLike(String value) {
            addCriterion("ADMIN_AUDIT_STATUS_NAME not like", value, "adminAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusNameIn(List<String> values) {
            addCriterion("ADMIN_AUDIT_STATUS_NAME in", values, "adminAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusNameNotIn(List<String> values) {
            addCriterion("ADMIN_AUDIT_STATUS_NAME not in", values, "adminAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusNameBetween(String value1, String value2) {
            addCriterion("ADMIN_AUDIT_STATUS_NAME between", value1, value2, "adminAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("ADMIN_AUDIT_STATUS_NAME not between", value1, value2, "adminAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNull() {
            addCriterion("ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(String value) {
            addCriterion("ITEM_ID =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(String value) {
            addCriterion("ITEM_ID <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(String value) {
            addCriterion("ITEM_ID >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_ID >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(String value) {
            addCriterion("ITEM_ID <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(String value) {
            addCriterion("ITEM_ID <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLike(String value) {
            addCriterion("ITEM_ID like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotLike(String value) {
            addCriterion("ITEM_ID not like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<String> values) {
            addCriterion("ITEM_ID in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<String> values) {
            addCriterion("ITEM_ID not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(String value1, String value2) {
            addCriterion("ITEM_ID between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(String value1, String value2) {
            addCriterion("ITEM_ID not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andProcessFlowIsNull() {
            addCriterion("PROCESS_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andProcessFlowIsNotNull() {
            addCriterion("PROCESS_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andProcessFlowEqualTo(String value) {
            addCriterion("PROCESS_FLOW =", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowNotEqualTo(String value) {
            addCriterion("PROCESS_FLOW <>", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowGreaterThan(String value) {
            addCriterion("PROCESS_FLOW >", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PROCESS_FLOW >=", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowLessThan(String value) {
            addCriterion("PROCESS_FLOW <", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowLessThanOrEqualTo(String value) {
            addCriterion("PROCESS_FLOW <=", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowLike(String value) {
            addCriterion("PROCESS_FLOW like", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowNotLike(String value) {
            addCriterion("PROCESS_FLOW not like", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowIn(List<String> values) {
            addCriterion("PROCESS_FLOW in", values, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowNotIn(List<String> values) {
            addCriterion("PROCESS_FLOW not in", values, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowBetween(String value1, String value2) {
            addCriterion("PROCESS_FLOW between", value1, value2, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowNotBetween(String value1, String value2) {
            addCriterion("PROCESS_FLOW not between", value1, value2, "processFlow");
            return (Criteria) this;
        }

        public Criteria andRecFormIsNull() {
            addCriterion("REC_FORM is null");
            return (Criteria) this;
        }

        public Criteria andRecFormIsNotNull() {
            addCriterion("REC_FORM is not null");
            return (Criteria) this;
        }

        public Criteria andRecFormEqualTo(String value) {
            addCriterion("REC_FORM =", value, "recForm");
            return (Criteria) this;
        }

        public Criteria andRecFormNotEqualTo(String value) {
            addCriterion("REC_FORM <>", value, "recForm");
            return (Criteria) this;
        }

        public Criteria andRecFormGreaterThan(String value) {
            addCriterion("REC_FORM >", value, "recForm");
            return (Criteria) this;
        }

        public Criteria andRecFormGreaterThanOrEqualTo(String value) {
            addCriterion("REC_FORM >=", value, "recForm");
            return (Criteria) this;
        }

        public Criteria andRecFormLessThan(String value) {
            addCriterion("REC_FORM <", value, "recForm");
            return (Criteria) this;
        }

        public Criteria andRecFormLessThanOrEqualTo(String value) {
            addCriterion("REC_FORM <=", value, "recForm");
            return (Criteria) this;
        }

        public Criteria andRecFormLike(String value) {
            addCriterion("REC_FORM like", value, "recForm");
            return (Criteria) this;
        }

        public Criteria andRecFormNotLike(String value) {
            addCriterion("REC_FORM not like", value, "recForm");
            return (Criteria) this;
        }

        public Criteria andRecFormIn(List<String> values) {
            addCriterion("REC_FORM in", values, "recForm");
            return (Criteria) this;
        }

        public Criteria andRecFormNotIn(List<String> values) {
            addCriterion("REC_FORM not in", values, "recForm");
            return (Criteria) this;
        }

        public Criteria andRecFormBetween(String value1, String value2) {
            addCriterion("REC_FORM between", value1, value2, "recForm");
            return (Criteria) this;
        }

        public Criteria andRecFormNotBetween(String value1, String value2) {
            addCriterion("REC_FORM not between", value1, value2, "recForm");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowIsNull() {
            addCriterion("SCH_ROTATION_DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowIsNotNull() {
            addCriterion("SCH_ROTATION_DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowEqualTo(String value) {
            addCriterion("SCH_ROTATION_DEPT_FLOW =", value, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowNotEqualTo(String value) {
            addCriterion("SCH_ROTATION_DEPT_FLOW <>", value, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowGreaterThan(String value) {
            addCriterion("SCH_ROTATION_DEPT_FLOW >", value, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_ROTATION_DEPT_FLOW >=", value, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowLessThan(String value) {
            addCriterion("SCH_ROTATION_DEPT_FLOW <", value, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("SCH_ROTATION_DEPT_FLOW <=", value, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowLike(String value) {
            addCriterion("SCH_ROTATION_DEPT_FLOW like", value, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowNotLike(String value) {
            addCriterion("SCH_ROTATION_DEPT_FLOW not like", value, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowIn(List<String> values) {
            addCriterion("SCH_ROTATION_DEPT_FLOW in", values, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowNotIn(List<String> values) {
            addCriterion("SCH_ROTATION_DEPT_FLOW not in", values, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowBetween(String value1, String value2) {
            addCriterion("SCH_ROTATION_DEPT_FLOW between", value1, value2, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowNotBetween(String value1, String value2) {
            addCriterion("SCH_ROTATION_DEPT_FLOW not between", value1, value2, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andDateTimeIsNull() {
            addCriterion("DATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDateTimeIsNotNull() {
            addCriterion("DATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDateTimeEqualTo(String value) {
            addCriterion("DATE_TIME =", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeNotEqualTo(String value) {
            addCriterion("DATE_TIME <>", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeGreaterThan(String value) {
            addCriterion("DATE_TIME >", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("DATE_TIME >=", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeLessThan(String value) {
            addCriterion("DATE_TIME <", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeLessThanOrEqualTo(String value) {
            addCriterion("DATE_TIME <=", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeLike(String value) {
            addCriterion("DATE_TIME like", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeNotLike(String value) {
            addCriterion("DATE_TIME not like", value, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeIn(List<String> values) {
            addCriterion("DATE_TIME in", values, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeNotIn(List<String> values) {
            addCriterion("DATE_TIME not in", values, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeBetween(String value1, String value2) {
            addCriterion("DATE_TIME between", value1, value2, "dateTime");
            return (Criteria) this;
        }

        public Criteria andDateTimeNotBetween(String value1, String value2) {
            addCriterion("DATE_TIME not between", value1, value2, "dateTime");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeIsNull() {
            addCriterion("MEDICINE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeIsNotNull() {
            addCriterion("MEDICINE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeEqualTo(String value) {
            addCriterion("MEDICINE_TYPE =", value, "medicineType");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeNotEqualTo(String value) {
            addCriterion("MEDICINE_TYPE <>", value, "medicineType");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeGreaterThan(String value) {
            addCriterion("MEDICINE_TYPE >", value, "medicineType");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeGreaterThanOrEqualTo(String value) {
            addCriterion("MEDICINE_TYPE >=", value, "medicineType");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeLessThan(String value) {
            addCriterion("MEDICINE_TYPE <", value, "medicineType");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeLessThanOrEqualTo(String value) {
            addCriterion("MEDICINE_TYPE <=", value, "medicineType");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeLike(String value) {
            addCriterion("MEDICINE_TYPE like", value, "medicineType");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeNotLike(String value) {
            addCriterion("MEDICINE_TYPE not like", value, "medicineType");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeIn(List<String> values) {
            addCriterion("MEDICINE_TYPE in", values, "medicineType");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeNotIn(List<String> values) {
            addCriterion("MEDICINE_TYPE not in", values, "medicineType");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeBetween(String value1, String value2) {
            addCriterion("MEDICINE_TYPE between", value1, value2, "medicineType");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeNotBetween(String value1, String value2) {
            addCriterion("MEDICINE_TYPE not between", value1, value2, "medicineType");
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