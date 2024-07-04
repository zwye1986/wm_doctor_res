package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class DeptTeacherGradeInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeptTeacherGradeInfoExample() {
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

        public Criteria andAllScoreIsNull() {
            addCriterion("ALL_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andAllScoreIsNotNull() {
            addCriterion("ALL_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andAllScoreEqualTo(String value) {
            addCriterion("ALL_SCORE =", value, "allScore");
            return (Criteria) this;
        }

        public Criteria andAllScoreNotEqualTo(String value) {
            addCriterion("ALL_SCORE <>", value, "allScore");
            return (Criteria) this;
        }

        public Criteria andAllScoreGreaterThan(String value) {
            addCriterion("ALL_SCORE >", value, "allScore");
            return (Criteria) this;
        }

        public Criteria andAllScoreGreaterThanOrEqualTo(String value) {
            addCriterion("ALL_SCORE >=", value, "allScore");
            return (Criteria) this;
        }

        public Criteria andAllScoreLessThan(String value) {
            addCriterion("ALL_SCORE <", value, "allScore");
            return (Criteria) this;
        }

        public Criteria andAllScoreLessThanOrEqualTo(String value) {
            addCriterion("ALL_SCORE <=", value, "allScore");
            return (Criteria) this;
        }

        public Criteria andAllScoreLike(String value) {
            addCriterion("ALL_SCORE like", value, "allScore");
            return (Criteria) this;
        }

        public Criteria andAllScoreNotLike(String value) {
            addCriterion("ALL_SCORE not like", value, "allScore");
            return (Criteria) this;
        }

        public Criteria andAllScoreIn(List<String> values) {
            addCriterion("ALL_SCORE in", values, "allScore");
            return (Criteria) this;
        }

        public Criteria andAllScoreNotIn(List<String> values) {
            addCriterion("ALL_SCORE not in", values, "allScore");
            return (Criteria) this;
        }

        public Criteria andAllScoreBetween(String value1, String value2) {
            addCriterion("ALL_SCORE between", value1, value2, "allScore");
            return (Criteria) this;
        }

        public Criteria andAllScoreNotBetween(String value1, String value2) {
            addCriterion("ALL_SCORE not between", value1, value2, "allScore");
            return (Criteria) this;
        }

        public Criteria andCfgFlowIsNull() {
            addCriterion("CFG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andCfgFlowIsNotNull() {
            addCriterion("CFG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andCfgFlowEqualTo(String value) {
            addCriterion("CFG_FLOW =", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowNotEqualTo(String value) {
            addCriterion("CFG_FLOW <>", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowGreaterThan(String value) {
            addCriterion("CFG_FLOW >", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CFG_FLOW >=", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowLessThan(String value) {
            addCriterion("CFG_FLOW <", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowLessThanOrEqualTo(String value) {
            addCriterion("CFG_FLOW <=", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowLike(String value) {
            addCriterion("CFG_FLOW like", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowNotLike(String value) {
            addCriterion("CFG_FLOW not like", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowIn(List<String> values) {
            addCriterion("CFG_FLOW in", values, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowNotIn(List<String> values) {
            addCriterion("CFG_FLOW not in", values, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowBetween(String value1, String value2) {
            addCriterion("CFG_FLOW between", value1, value2, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowNotBetween(String value1, String value2) {
            addCriterion("CFG_FLOW not between", value1, value2, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherNameOneIsNull() {
            addCriterion("TEACHER_NAME_ONE is null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameOneIsNotNull() {
            addCriterion("TEACHER_NAME_ONE is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameOneEqualTo(String value) {
            addCriterion("TEACHER_NAME_ONE =", value, "teacherNameOne");
            return (Criteria) this;
        }

        public Criteria andTeacherNameOneNotEqualTo(String value) {
            addCriterion("TEACHER_NAME_ONE <>", value, "teacherNameOne");
            return (Criteria) this;
        }

        public Criteria andTeacherNameOneGreaterThan(String value) {
            addCriterion("TEACHER_NAME_ONE >", value, "teacherNameOne");
            return (Criteria) this;
        }

        public Criteria andTeacherNameOneGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_NAME_ONE >=", value, "teacherNameOne");
            return (Criteria) this;
        }

        public Criteria andTeacherNameOneLessThan(String value) {
            addCriterion("TEACHER_NAME_ONE <", value, "teacherNameOne");
            return (Criteria) this;
        }

        public Criteria andTeacherNameOneLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_NAME_ONE <=", value, "teacherNameOne");
            return (Criteria) this;
        }

        public Criteria andTeacherNameOneLike(String value) {
            addCriterion("TEACHER_NAME_ONE like", value, "teacherNameOne");
            return (Criteria) this;
        }

        public Criteria andTeacherNameOneNotLike(String value) {
            addCriterion("TEACHER_NAME_ONE not like", value, "teacherNameOne");
            return (Criteria) this;
        }

        public Criteria andTeacherNameOneIn(List<String> values) {
            addCriterion("TEACHER_NAME_ONE in", values, "teacherNameOne");
            return (Criteria) this;
        }

        public Criteria andTeacherNameOneNotIn(List<String> values) {
            addCriterion("TEACHER_NAME_ONE not in", values, "teacherNameOne");
            return (Criteria) this;
        }

        public Criteria andTeacherNameOneBetween(String value1, String value2) {
            addCriterion("TEACHER_NAME_ONE between", value1, value2, "teacherNameOne");
            return (Criteria) this;
        }

        public Criteria andTeacherNameOneNotBetween(String value1, String value2) {
            addCriterion("TEACHER_NAME_ONE not between", value1, value2, "teacherNameOne");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowOneIsNull() {
            addCriterion("TEACHER_FLOW_ONE is null");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowOneIsNotNull() {
            addCriterion("TEACHER_FLOW_ONE is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowOneEqualTo(String value) {
            addCriterion("TEACHER_FLOW_ONE =", value, "teacherFlowOne");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowOneNotEqualTo(String value) {
            addCriterion("TEACHER_FLOW_ONE <>", value, "teacherFlowOne");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowOneGreaterThan(String value) {
            addCriterion("TEACHER_FLOW_ONE >", value, "teacherFlowOne");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowOneGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_FLOW_ONE >=", value, "teacherFlowOne");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowOneLessThan(String value) {
            addCriterion("TEACHER_FLOW_ONE <", value, "teacherFlowOne");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowOneLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_FLOW_ONE <=", value, "teacherFlowOne");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowOneLike(String value) {
            addCriterion("TEACHER_FLOW_ONE like", value, "teacherFlowOne");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowOneNotLike(String value) {
            addCriterion("TEACHER_FLOW_ONE not like", value, "teacherFlowOne");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowOneIn(List<String> values) {
            addCriterion("TEACHER_FLOW_ONE in", values, "teacherFlowOne");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowOneNotIn(List<String> values) {
            addCriterion("TEACHER_FLOW_ONE not in", values, "teacherFlowOne");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowOneBetween(String value1, String value2) {
            addCriterion("TEACHER_FLOW_ONE between", value1, value2, "teacherFlowOne");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowOneNotBetween(String value1, String value2) {
            addCriterion("TEACHER_FLOW_ONE not between", value1, value2, "teacherFlowOne");
            return (Criteria) this;
        }

        public Criteria andTeacherNameTwoIsNull() {
            addCriterion("TEACHER_NAME_TWO is null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameTwoIsNotNull() {
            addCriterion("TEACHER_NAME_TWO is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameTwoEqualTo(String value) {
            addCriterion("TEACHER_NAME_TWO =", value, "teacherNameTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherNameTwoNotEqualTo(String value) {
            addCriterion("TEACHER_NAME_TWO <>", value, "teacherNameTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherNameTwoGreaterThan(String value) {
            addCriterion("TEACHER_NAME_TWO >", value, "teacherNameTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherNameTwoGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_NAME_TWO >=", value, "teacherNameTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherNameTwoLessThan(String value) {
            addCriterion("TEACHER_NAME_TWO <", value, "teacherNameTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherNameTwoLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_NAME_TWO <=", value, "teacherNameTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherNameTwoLike(String value) {
            addCriterion("TEACHER_NAME_TWO like", value, "teacherNameTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherNameTwoNotLike(String value) {
            addCriterion("TEACHER_NAME_TWO not like", value, "teacherNameTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherNameTwoIn(List<String> values) {
            addCriterion("TEACHER_NAME_TWO in", values, "teacherNameTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherNameTwoNotIn(List<String> values) {
            addCriterion("TEACHER_NAME_TWO not in", values, "teacherNameTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherNameTwoBetween(String value1, String value2) {
            addCriterion("TEACHER_NAME_TWO between", value1, value2, "teacherNameTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherNameTwoNotBetween(String value1, String value2) {
            addCriterion("TEACHER_NAME_TWO not between", value1, value2, "teacherNameTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowTwoIsNull() {
            addCriterion("TEACHER_FLOW_TWO is null");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowTwoIsNotNull() {
            addCriterion("TEACHER_FLOW_TWO is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowTwoEqualTo(String value) {
            addCriterion("TEACHER_FLOW_TWO =", value, "teacherFlowTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowTwoNotEqualTo(String value) {
            addCriterion("TEACHER_FLOW_TWO <>", value, "teacherFlowTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowTwoGreaterThan(String value) {
            addCriterion("TEACHER_FLOW_TWO >", value, "teacherFlowTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowTwoGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_FLOW_TWO >=", value, "teacherFlowTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowTwoLessThan(String value) {
            addCriterion("TEACHER_FLOW_TWO <", value, "teacherFlowTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowTwoLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_FLOW_TWO <=", value, "teacherFlowTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowTwoLike(String value) {
            addCriterion("TEACHER_FLOW_TWO like", value, "teacherFlowTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowTwoNotLike(String value) {
            addCriterion("TEACHER_FLOW_TWO not like", value, "teacherFlowTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowTwoIn(List<String> values) {
            addCriterion("TEACHER_FLOW_TWO in", values, "teacherFlowTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowTwoNotIn(List<String> values) {
            addCriterion("TEACHER_FLOW_TWO not in", values, "teacherFlowTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowTwoBetween(String value1, String value2) {
            addCriterion("TEACHER_FLOW_TWO between", value1, value2, "teacherFlowTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowTwoNotBetween(String value1, String value2) {
            addCriterion("TEACHER_FLOW_TWO not between", value1, value2, "teacherFlowTwo");
            return (Criteria) this;
        }

        public Criteria andTeacherNameThreeIsNull() {
            addCriterion("TEACHER_NAME_THREE is null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameThreeIsNotNull() {
            addCriterion("TEACHER_NAME_THREE is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameThreeEqualTo(String value) {
            addCriterion("TEACHER_NAME_THREE =", value, "teacherNameThree");
            return (Criteria) this;
        }

        public Criteria andTeacherNameThreeNotEqualTo(String value) {
            addCriterion("TEACHER_NAME_THREE <>", value, "teacherNameThree");
            return (Criteria) this;
        }

        public Criteria andTeacherNameThreeGreaterThan(String value) {
            addCriterion("TEACHER_NAME_THREE >", value, "teacherNameThree");
            return (Criteria) this;
        }

        public Criteria andTeacherNameThreeGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_NAME_THREE >=", value, "teacherNameThree");
            return (Criteria) this;
        }

        public Criteria andTeacherNameThreeLessThan(String value) {
            addCriterion("TEACHER_NAME_THREE <", value, "teacherNameThree");
            return (Criteria) this;
        }

        public Criteria andTeacherNameThreeLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_NAME_THREE <=", value, "teacherNameThree");
            return (Criteria) this;
        }

        public Criteria andTeacherNameThreeLike(String value) {
            addCriterion("TEACHER_NAME_THREE like", value, "teacherNameThree");
            return (Criteria) this;
        }

        public Criteria andTeacherNameThreeNotLike(String value) {
            addCriterion("TEACHER_NAME_THREE not like", value, "teacherNameThree");
            return (Criteria) this;
        }

        public Criteria andTeacherNameThreeIn(List<String> values) {
            addCriterion("TEACHER_NAME_THREE in", values, "teacherNameThree");
            return (Criteria) this;
        }

        public Criteria andTeacherNameThreeNotIn(List<String> values) {
            addCriterion("TEACHER_NAME_THREE not in", values, "teacherNameThree");
            return (Criteria) this;
        }

        public Criteria andTeacherNameThreeBetween(String value1, String value2) {
            addCriterion("TEACHER_NAME_THREE between", value1, value2, "teacherNameThree");
            return (Criteria) this;
        }

        public Criteria andTeacherNameThreeNotBetween(String value1, String value2) {
            addCriterion("TEACHER_NAME_THREE not between", value1, value2, "teacherNameThree");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowThreeIsNull() {
            addCriterion("TEACHER_FLOW_THREE is null");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowThreeIsNotNull() {
            addCriterion("TEACHER_FLOW_THREE is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowThreeEqualTo(String value) {
            addCriterion("TEACHER_FLOW_THREE =", value, "teacherFlowThree");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowThreeNotEqualTo(String value) {
            addCriterion("TEACHER_FLOW_THREE <>", value, "teacherFlowThree");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowThreeGreaterThan(String value) {
            addCriterion("TEACHER_FLOW_THREE >", value, "teacherFlowThree");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowThreeGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_FLOW_THREE >=", value, "teacherFlowThree");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowThreeLessThan(String value) {
            addCriterion("TEACHER_FLOW_THREE <", value, "teacherFlowThree");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowThreeLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_FLOW_THREE <=", value, "teacherFlowThree");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowThreeLike(String value) {
            addCriterion("TEACHER_FLOW_THREE like", value, "teacherFlowThree");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowThreeNotLike(String value) {
            addCriterion("TEACHER_FLOW_THREE not like", value, "teacherFlowThree");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowThreeIn(List<String> values) {
            addCriterion("TEACHER_FLOW_THREE in", values, "teacherFlowThree");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowThreeNotIn(List<String> values) {
            addCriterion("TEACHER_FLOW_THREE not in", values, "teacherFlowThree");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowThreeBetween(String value1, String value2) {
            addCriterion("TEACHER_FLOW_THREE between", value1, value2, "teacherFlowThree");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowThreeNotBetween(String value1, String value2) {
            addCriterion("TEACHER_FLOW_THREE not between", value1, value2, "teacherFlowThree");
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