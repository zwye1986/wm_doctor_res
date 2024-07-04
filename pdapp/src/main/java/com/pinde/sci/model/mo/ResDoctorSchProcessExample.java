package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ResDoctorSchProcessExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResDoctorSchProcessExample() {
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

        public Criteria andSchResultFlowIsNull() {
            addCriterion("SCH_RESULT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSchResultFlowIsNotNull() {
            addCriterion("SCH_RESULT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSchResultFlowEqualTo(String value) {
            addCriterion("SCH_RESULT_FLOW =", value, "schResultFlow");
            return (Criteria) this;
        }

        public Criteria andSchResultFlowNotEqualTo(String value) {
            addCriterion("SCH_RESULT_FLOW <>", value, "schResultFlow");
            return (Criteria) this;
        }

        public Criteria andSchResultFlowGreaterThan(String value) {
            addCriterion("SCH_RESULT_FLOW >", value, "schResultFlow");
            return (Criteria) this;
        }

        public Criteria andSchResultFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_RESULT_FLOW >=", value, "schResultFlow");
            return (Criteria) this;
        }

        public Criteria andSchResultFlowLessThan(String value) {
            addCriterion("SCH_RESULT_FLOW <", value, "schResultFlow");
            return (Criteria) this;
        }

        public Criteria andSchResultFlowLessThanOrEqualTo(String value) {
            addCriterion("SCH_RESULT_FLOW <=", value, "schResultFlow");
            return (Criteria) this;
        }

        public Criteria andSchResultFlowLike(String value) {
            addCriterion("SCH_RESULT_FLOW like", value, "schResultFlow");
            return (Criteria) this;
        }

        public Criteria andSchResultFlowNotLike(String value) {
            addCriterion("SCH_RESULT_FLOW not like", value, "schResultFlow");
            return (Criteria) this;
        }

        public Criteria andSchResultFlowIn(List<String> values) {
            addCriterion("SCH_RESULT_FLOW in", values, "schResultFlow");
            return (Criteria) this;
        }

        public Criteria andSchResultFlowNotIn(List<String> values) {
            addCriterion("SCH_RESULT_FLOW not in", values, "schResultFlow");
            return (Criteria) this;
        }

        public Criteria andSchResultFlowBetween(String value1, String value2) {
            addCriterion("SCH_RESULT_FLOW between", value1, value2, "schResultFlow");
            return (Criteria) this;
        }

        public Criteria andSchResultFlowNotBetween(String value1, String value2) {
            addCriterion("SCH_RESULT_FLOW not between", value1, value2, "schResultFlow");
            return (Criteria) this;
        }

        public Criteria andSchStartDateIsNull() {
            addCriterion("SCH_START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSchStartDateIsNotNull() {
            addCriterion("SCH_START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSchStartDateEqualTo(String value) {
            addCriterion("SCH_START_DATE =", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateNotEqualTo(String value) {
            addCriterion("SCH_START_DATE <>", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateGreaterThan(String value) {
            addCriterion("SCH_START_DATE >", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_START_DATE >=", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateLessThan(String value) {
            addCriterion("SCH_START_DATE <", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateLessThanOrEqualTo(String value) {
            addCriterion("SCH_START_DATE <=", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateLike(String value) {
            addCriterion("SCH_START_DATE like", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateNotLike(String value) {
            addCriterion("SCH_START_DATE not like", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateIn(List<String> values) {
            addCriterion("SCH_START_DATE in", values, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateNotIn(List<String> values) {
            addCriterion("SCH_START_DATE not in", values, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateBetween(String value1, String value2) {
            addCriterion("SCH_START_DATE between", value1, value2, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateNotBetween(String value1, String value2) {
            addCriterion("SCH_START_DATE not between", value1, value2, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateIsNull() {
            addCriterion("SCH_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSchEndDateIsNotNull() {
            addCriterion("SCH_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSchEndDateEqualTo(String value) {
            addCriterion("SCH_END_DATE =", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateNotEqualTo(String value) {
            addCriterion("SCH_END_DATE <>", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateGreaterThan(String value) {
            addCriterion("SCH_END_DATE >", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_END_DATE >=", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateLessThan(String value) {
            addCriterion("SCH_END_DATE <", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateLessThanOrEqualTo(String value) {
            addCriterion("SCH_END_DATE <=", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateLike(String value) {
            addCriterion("SCH_END_DATE like", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateNotLike(String value) {
            addCriterion("SCH_END_DATE not like", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateIn(List<String> values) {
            addCriterion("SCH_END_DATE in", values, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateNotIn(List<String> values) {
            addCriterion("SCH_END_DATE not in", values, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateBetween(String value1, String value2) {
            addCriterion("SCH_END_DATE between", value1, value2, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateNotBetween(String value1, String value2) {
            addCriterion("SCH_END_DATE not between", value1, value2, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(String value) {
            addCriterion("START_DATE =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(String value) {
            addCriterion("START_DATE <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(String value) {
            addCriterion("START_DATE >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("START_DATE >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(String value) {
            addCriterion("START_DATE <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(String value) {
            addCriterion("START_DATE <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLike(String value) {
            addCriterion("START_DATE like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotLike(String value) {
            addCriterion("START_DATE not like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<String> values) {
            addCriterion("START_DATE in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<String> values) {
            addCriterion("START_DATE not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(String value1, String value2) {
            addCriterion("START_DATE between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(String value1, String value2) {
            addCriterion("START_DATE not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(String value) {
            addCriterion("END_DATE =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(String value) {
            addCriterion("END_DATE <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(String value) {
            addCriterion("END_DATE >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("END_DATE >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(String value) {
            addCriterion("END_DATE <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(String value) {
            addCriterion("END_DATE <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLike(String value) {
            addCriterion("END_DATE like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotLike(String value) {
            addCriterion("END_DATE not like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<String> values) {
            addCriterion("END_DATE in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<String> values) {
            addCriterion("END_DATE not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(String value1, String value2) {
            addCriterion("END_DATE between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(String value1, String value2) {
            addCriterion("END_DATE not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowIsNull() {
            addCriterion("TEACHER_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowIsNotNull() {
            addCriterion("TEACHER_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowEqualTo(String value) {
            addCriterion("TEACHER_USER_FLOW =", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowNotEqualTo(String value) {
            addCriterion("TEACHER_USER_FLOW <>", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowGreaterThan(String value) {
            addCriterion("TEACHER_USER_FLOW >", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_USER_FLOW >=", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowLessThan(String value) {
            addCriterion("TEACHER_USER_FLOW <", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_USER_FLOW <=", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowLike(String value) {
            addCriterion("TEACHER_USER_FLOW like", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowNotLike(String value) {
            addCriterion("TEACHER_USER_FLOW not like", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowIn(List<String> values) {
            addCriterion("TEACHER_USER_FLOW in", values, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowNotIn(List<String> values) {
            addCriterion("TEACHER_USER_FLOW not in", values, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowBetween(String value1, String value2) {
            addCriterion("TEACHER_USER_FLOW between", value1, value2, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowNotBetween(String value1, String value2) {
            addCriterion("TEACHER_USER_FLOW not between", value1, value2, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameIsNull() {
            addCriterion("TEACHER_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameIsNotNull() {
            addCriterion("TEACHER_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameEqualTo(String value) {
            addCriterion("TEACHER_USER_NAME =", value, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameNotEqualTo(String value) {
            addCriterion("TEACHER_USER_NAME <>", value, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameGreaterThan(String value) {
            addCriterion("TEACHER_USER_NAME >", value, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_USER_NAME >=", value, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameLessThan(String value) {
            addCriterion("TEACHER_USER_NAME <", value, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_USER_NAME <=", value, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameLike(String value) {
            addCriterion("TEACHER_USER_NAME like", value, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameNotLike(String value) {
            addCriterion("TEACHER_USER_NAME not like", value, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameIn(List<String> values) {
            addCriterion("TEACHER_USER_NAME in", values, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameNotIn(List<String> values) {
            addCriterion("TEACHER_USER_NAME not in", values, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameBetween(String value1, String value2) {
            addCriterion("TEACHER_USER_NAME between", value1, value2, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameNotBetween(String value1, String value2) {
            addCriterion("TEACHER_USER_NAME not between", value1, value2, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowIsNull() {
            addCriterion("HEAD_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowIsNotNull() {
            addCriterion("HEAD_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowEqualTo(String value) {
            addCriterion("HEAD_USER_FLOW =", value, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowNotEqualTo(String value) {
            addCriterion("HEAD_USER_FLOW <>", value, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowGreaterThan(String value) {
            addCriterion("HEAD_USER_FLOW >", value, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_USER_FLOW >=", value, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowLessThan(String value) {
            addCriterion("HEAD_USER_FLOW <", value, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowLessThanOrEqualTo(String value) {
            addCriterion("HEAD_USER_FLOW <=", value, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowLike(String value) {
            addCriterion("HEAD_USER_FLOW like", value, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowNotLike(String value) {
            addCriterion("HEAD_USER_FLOW not like", value, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowIn(List<String> values) {
            addCriterion("HEAD_USER_FLOW in", values, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowNotIn(List<String> values) {
            addCriterion("HEAD_USER_FLOW not in", values, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowBetween(String value1, String value2) {
            addCriterion("HEAD_USER_FLOW between", value1, value2, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowNotBetween(String value1, String value2) {
            addCriterion("HEAD_USER_FLOW not between", value1, value2, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameIsNull() {
            addCriterion("HEAD_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameIsNotNull() {
            addCriterion("HEAD_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameEqualTo(String value) {
            addCriterion("HEAD_USER_NAME =", value, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameNotEqualTo(String value) {
            addCriterion("HEAD_USER_NAME <>", value, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameGreaterThan(String value) {
            addCriterion("HEAD_USER_NAME >", value, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_USER_NAME >=", value, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameLessThan(String value) {
            addCriterion("HEAD_USER_NAME <", value, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameLessThanOrEqualTo(String value) {
            addCriterion("HEAD_USER_NAME <=", value, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameLike(String value) {
            addCriterion("HEAD_USER_NAME like", value, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameNotLike(String value) {
            addCriterion("HEAD_USER_NAME not like", value, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameIn(List<String> values) {
            addCriterion("HEAD_USER_NAME in", values, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameNotIn(List<String> values) {
            addCriterion("HEAD_USER_NAME not in", values, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameBetween(String value1, String value2) {
            addCriterion("HEAD_USER_NAME between", value1, value2, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameNotBetween(String value1, String value2) {
            addCriterion("HEAD_USER_NAME not between", value1, value2, "headUserName");
            return (Criteria) this;
        }

        public Criteria andSchFlagIsNull() {
            addCriterion("SCH_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andSchFlagIsNotNull() {
            addCriterion("SCH_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andSchFlagEqualTo(String value) {
            addCriterion("SCH_FLAG =", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagNotEqualTo(String value) {
            addCriterion("SCH_FLAG <>", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagGreaterThan(String value) {
            addCriterion("SCH_FLAG >", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_FLAG >=", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagLessThan(String value) {
            addCriterion("SCH_FLAG <", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagLessThanOrEqualTo(String value) {
            addCriterion("SCH_FLAG <=", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagLike(String value) {
            addCriterion("SCH_FLAG like", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagNotLike(String value) {
            addCriterion("SCH_FLAG not like", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagIn(List<String> values) {
            addCriterion("SCH_FLAG in", values, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagNotIn(List<String> values) {
            addCriterion("SCH_FLAG not in", values, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagBetween(String value1, String value2) {
            addCriterion("SCH_FLAG between", value1, value2, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagNotBetween(String value1, String value2) {
            addCriterion("SCH_FLAG not between", value1, value2, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchScoreIsNull() {
            addCriterion("SCH_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andSchScoreIsNotNull() {
            addCriterion("SCH_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andSchScoreEqualTo(BigDecimal value) {
            addCriterion("SCH_SCORE =", value, "schScore");
            return (Criteria) this;
        }

        public Criteria andSchScoreNotEqualTo(BigDecimal value) {
            addCriterion("SCH_SCORE <>", value, "schScore");
            return (Criteria) this;
        }

        public Criteria andSchScoreGreaterThan(BigDecimal value) {
            addCriterion("SCH_SCORE >", value, "schScore");
            return (Criteria) this;
        }

        public Criteria andSchScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SCH_SCORE >=", value, "schScore");
            return (Criteria) this;
        }

        public Criteria andSchScoreLessThan(BigDecimal value) {
            addCriterion("SCH_SCORE <", value, "schScore");
            return (Criteria) this;
        }

        public Criteria andSchScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SCH_SCORE <=", value, "schScore");
            return (Criteria) this;
        }

        public Criteria andSchScoreIn(List<BigDecimal> values) {
            addCriterion("SCH_SCORE in", values, "schScore");
            return (Criteria) this;
        }

        public Criteria andSchScoreNotIn(List<BigDecimal> values) {
            addCriterion("SCH_SCORE not in", values, "schScore");
            return (Criteria) this;
        }

        public Criteria andSchScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SCH_SCORE between", value1, value2, "schScore");
            return (Criteria) this;
        }

        public Criteria andSchScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SCH_SCORE not between", value1, value2, "schScore");
            return (Criteria) this;
        }

        public Criteria andIsCurrentFlagIsNull() {
            addCriterion("IS_CURRENT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andIsCurrentFlagIsNotNull() {
            addCriterion("IS_CURRENT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andIsCurrentFlagEqualTo(String value) {
            addCriterion("IS_CURRENT_FLAG =", value, "isCurrentFlag");
            return (Criteria) this;
        }

        public Criteria andIsCurrentFlagNotEqualTo(String value) {
            addCriterion("IS_CURRENT_FLAG <>", value, "isCurrentFlag");
            return (Criteria) this;
        }

        public Criteria andIsCurrentFlagGreaterThan(String value) {
            addCriterion("IS_CURRENT_FLAG >", value, "isCurrentFlag");
            return (Criteria) this;
        }

        public Criteria andIsCurrentFlagGreaterThanOrEqualTo(String value) {
            addCriterion("IS_CURRENT_FLAG >=", value, "isCurrentFlag");
            return (Criteria) this;
        }

        public Criteria andIsCurrentFlagLessThan(String value) {
            addCriterion("IS_CURRENT_FLAG <", value, "isCurrentFlag");
            return (Criteria) this;
        }

        public Criteria andIsCurrentFlagLessThanOrEqualTo(String value) {
            addCriterion("IS_CURRENT_FLAG <=", value, "isCurrentFlag");
            return (Criteria) this;
        }

        public Criteria andIsCurrentFlagLike(String value) {
            addCriterion("IS_CURRENT_FLAG like", value, "isCurrentFlag");
            return (Criteria) this;
        }

        public Criteria andIsCurrentFlagNotLike(String value) {
            addCriterion("IS_CURRENT_FLAG not like", value, "isCurrentFlag");
            return (Criteria) this;
        }

        public Criteria andIsCurrentFlagIn(List<String> values) {
            addCriterion("IS_CURRENT_FLAG in", values, "isCurrentFlag");
            return (Criteria) this;
        }

        public Criteria andIsCurrentFlagNotIn(List<String> values) {
            addCriterion("IS_CURRENT_FLAG not in", values, "isCurrentFlag");
            return (Criteria) this;
        }

        public Criteria andIsCurrentFlagBetween(String value1, String value2) {
            addCriterion("IS_CURRENT_FLAG between", value1, value2, "isCurrentFlag");
            return (Criteria) this;
        }

        public Criteria andIsCurrentFlagNotBetween(String value1, String value2) {
            addCriterion("IS_CURRENT_FLAG not between", value1, value2, "isCurrentFlag");
            return (Criteria) this;
        }

        public Criteria andSchPerIsNull() {
            addCriterion("SCH_PER is null");
            return (Criteria) this;
        }

        public Criteria andSchPerIsNotNull() {
            addCriterion("SCH_PER is not null");
            return (Criteria) this;
        }

        public Criteria andSchPerEqualTo(Short value) {
            addCriterion("SCH_PER =", value, "schPer");
            return (Criteria) this;
        }

        public Criteria andSchPerNotEqualTo(Short value) {
            addCriterion("SCH_PER <>", value, "schPer");
            return (Criteria) this;
        }

        public Criteria andSchPerGreaterThan(Short value) {
            addCriterion("SCH_PER >", value, "schPer");
            return (Criteria) this;
        }

        public Criteria andSchPerGreaterThanOrEqualTo(Short value) {
            addCriterion("SCH_PER >=", value, "schPer");
            return (Criteria) this;
        }

        public Criteria andSchPerLessThan(Short value) {
            addCriterion("SCH_PER <", value, "schPer");
            return (Criteria) this;
        }

        public Criteria andSchPerLessThanOrEqualTo(Short value) {
            addCriterion("SCH_PER <=", value, "schPer");
            return (Criteria) this;
        }

        public Criteria andSchPerIn(List<Short> values) {
            addCriterion("SCH_PER in", values, "schPer");
            return (Criteria) this;
        }

        public Criteria andSchPerNotIn(List<Short> values) {
            addCriterion("SCH_PER not in", values, "schPer");
            return (Criteria) this;
        }

        public Criteria andSchPerBetween(Short value1, Short value2) {
            addCriterion("SCH_PER between", value1, value2, "schPer");
            return (Criteria) this;
        }

        public Criteria andSchPerNotBetween(Short value1, Short value2) {
            addCriterion("SCH_PER not between", value1, value2, "schPer");
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

        public Criteria andIsExternalIsNull() {
            addCriterion("IS_EXTERNAL is null");
            return (Criteria) this;
        }

        public Criteria andIsExternalIsNotNull() {
            addCriterion("IS_EXTERNAL is not null");
            return (Criteria) this;
        }

        public Criteria andIsExternalEqualTo(String value) {
            addCriterion("IS_EXTERNAL =", value, "isExternal");
            return (Criteria) this;
        }

        public Criteria andIsExternalNotEqualTo(String value) {
            addCriterion("IS_EXTERNAL <>", value, "isExternal");
            return (Criteria) this;
        }

        public Criteria andIsExternalGreaterThan(String value) {
            addCriterion("IS_EXTERNAL >", value, "isExternal");
            return (Criteria) this;
        }

        public Criteria andIsExternalGreaterThanOrEqualTo(String value) {
            addCriterion("IS_EXTERNAL >=", value, "isExternal");
            return (Criteria) this;
        }

        public Criteria andIsExternalLessThan(String value) {
            addCriterion("IS_EXTERNAL <", value, "isExternal");
            return (Criteria) this;
        }

        public Criteria andIsExternalLessThanOrEqualTo(String value) {
            addCriterion("IS_EXTERNAL <=", value, "isExternal");
            return (Criteria) this;
        }

        public Criteria andIsExternalLike(String value) {
            addCriterion("IS_EXTERNAL like", value, "isExternal");
            return (Criteria) this;
        }

        public Criteria andIsExternalNotLike(String value) {
            addCriterion("IS_EXTERNAL not like", value, "isExternal");
            return (Criteria) this;
        }

        public Criteria andIsExternalIn(List<String> values) {
            addCriterion("IS_EXTERNAL in", values, "isExternal");
            return (Criteria) this;
        }

        public Criteria andIsExternalNotIn(List<String> values) {
            addCriterion("IS_EXTERNAL not in", values, "isExternal");
            return (Criteria) this;
        }

        public Criteria andIsExternalBetween(String value1, String value2) {
            addCriterion("IS_EXTERNAL between", value1, value2, "isExternal");
            return (Criteria) this;
        }

        public Criteria andIsExternalNotBetween(String value1, String value2) {
            addCriterion("IS_EXTERNAL not between", value1, value2, "isExternal");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserFlowIsNull() {
            addCriterion("SECRETARY_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserFlowIsNotNull() {
            addCriterion("SECRETARY_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserFlowEqualTo(String value) {
            addCriterion("SECRETARY_USER_FLOW =", value, "secretaryUserFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserFlowNotEqualTo(String value) {
            addCriterion("SECRETARY_USER_FLOW <>", value, "secretaryUserFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserFlowGreaterThan(String value) {
            addCriterion("SECRETARY_USER_FLOW >", value, "secretaryUserFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SECRETARY_USER_FLOW >=", value, "secretaryUserFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserFlowLessThan(String value) {
            addCriterion("SECRETARY_USER_FLOW <", value, "secretaryUserFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserFlowLessThanOrEqualTo(String value) {
            addCriterion("SECRETARY_USER_FLOW <=", value, "secretaryUserFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserFlowLike(String value) {
            addCriterion("SECRETARY_USER_FLOW like", value, "secretaryUserFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserFlowNotLike(String value) {
            addCriterion("SECRETARY_USER_FLOW not like", value, "secretaryUserFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserFlowIn(List<String> values) {
            addCriterion("SECRETARY_USER_FLOW in", values, "secretaryUserFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserFlowNotIn(List<String> values) {
            addCriterion("SECRETARY_USER_FLOW not in", values, "secretaryUserFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserFlowBetween(String value1, String value2) {
            addCriterion("SECRETARY_USER_FLOW between", value1, value2, "secretaryUserFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserFlowNotBetween(String value1, String value2) {
            addCriterion("SECRETARY_USER_FLOW not between", value1, value2, "secretaryUserFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserNameIsNull() {
            addCriterion("SECRETARY_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserNameIsNotNull() {
            addCriterion("SECRETARY_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserNameEqualTo(String value) {
            addCriterion("SECRETARY_USER_NAME =", value, "secretaryUserName");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserNameNotEqualTo(String value) {
            addCriterion("SECRETARY_USER_NAME <>", value, "secretaryUserName");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserNameGreaterThan(String value) {
            addCriterion("SECRETARY_USER_NAME >", value, "secretaryUserName");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("SECRETARY_USER_NAME >=", value, "secretaryUserName");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserNameLessThan(String value) {
            addCriterion("SECRETARY_USER_NAME <", value, "secretaryUserName");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserNameLessThanOrEqualTo(String value) {
            addCriterion("SECRETARY_USER_NAME <=", value, "secretaryUserName");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserNameLike(String value) {
            addCriterion("SECRETARY_USER_NAME like", value, "secretaryUserName");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserNameNotLike(String value) {
            addCriterion("SECRETARY_USER_NAME not like", value, "secretaryUserName");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserNameIn(List<String> values) {
            addCriterion("SECRETARY_USER_NAME in", values, "secretaryUserName");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserNameNotIn(List<String> values) {
            addCriterion("SECRETARY_USER_NAME not in", values, "secretaryUserName");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserNameBetween(String value1, String value2) {
            addCriterion("SECRETARY_USER_NAME between", value1, value2, "secretaryUserName");
            return (Criteria) this;
        }

        public Criteria andSecretaryUserNameNotBetween(String value1, String value2) {
            addCriterion("SECRETARY_USER_NAME not between", value1, value2, "secretaryUserName");
            return (Criteria) this;
        }

        public Criteria andMonthDateIsNull() {
            addCriterion("MONTH_DATE is null");
            return (Criteria) this;
        }

        public Criteria andMonthDateIsNotNull() {
            addCriterion("MONTH_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andMonthDateEqualTo(String value) {
            addCriterion("MONTH_DATE =", value, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateNotEqualTo(String value) {
            addCriterion("MONTH_DATE <>", value, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateGreaterThan(String value) {
            addCriterion("MONTH_DATE >", value, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateGreaterThanOrEqualTo(String value) {
            addCriterion("MONTH_DATE >=", value, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateLessThan(String value) {
            addCriterion("MONTH_DATE <", value, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateLessThanOrEqualTo(String value) {
            addCriterion("MONTH_DATE <=", value, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateLike(String value) {
            addCriterion("MONTH_DATE like", value, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateNotLike(String value) {
            addCriterion("MONTH_DATE not like", value, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateIn(List<String> values) {
            addCriterion("MONTH_DATE in", values, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateNotIn(List<String> values) {
            addCriterion("MONTH_DATE not in", values, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateBetween(String value1, String value2) {
            addCriterion("MONTH_DATE between", value1, value2, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateNotBetween(String value1, String value2) {
            addCriterion("MONTH_DATE not between", value1, value2, "monthDate");
            return (Criteria) this;
        }

        public Criteria andOutDateIsNull() {
            addCriterion("OUT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andOutDateIsNotNull() {
            addCriterion("OUT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andOutDateEqualTo(String value) {
            addCriterion("OUT_DATE =", value, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateNotEqualTo(String value) {
            addCriterion("OUT_DATE <>", value, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateGreaterThan(String value) {
            addCriterion("OUT_DATE >", value, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateGreaterThanOrEqualTo(String value) {
            addCriterion("OUT_DATE >=", value, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateLessThan(String value) {
            addCriterion("OUT_DATE <", value, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateLessThanOrEqualTo(String value) {
            addCriterion("OUT_DATE <=", value, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateLike(String value) {
            addCriterion("OUT_DATE like", value, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateNotLike(String value) {
            addCriterion("OUT_DATE not like", value, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateIn(List<String> values) {
            addCriterion("OUT_DATE in", values, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateNotIn(List<String> values) {
            addCriterion("OUT_DATE not in", values, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateBetween(String value1, String value2) {
            addCriterion("OUT_DATE between", value1, value2, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateNotBetween(String value1, String value2) {
            addCriterion("OUT_DATE not between", value1, value2, "outDate");
            return (Criteria) this;
        }

        public Criteria andTemporaryOutIsNull() {
            addCriterion("TEMPORARY_OUT is null");
            return (Criteria) this;
        }

        public Criteria andTemporaryOutIsNotNull() {
            addCriterion("TEMPORARY_OUT is not null");
            return (Criteria) this;
        }

        public Criteria andTemporaryOutEqualTo(String value) {
            addCriterion("TEMPORARY_OUT =", value, "temporaryOut");
            return (Criteria) this;
        }

        public Criteria andTemporaryOutNotEqualTo(String value) {
            addCriterion("TEMPORARY_OUT <>", value, "temporaryOut");
            return (Criteria) this;
        }

        public Criteria andTemporaryOutGreaterThan(String value) {
            addCriterion("TEMPORARY_OUT >", value, "temporaryOut");
            return (Criteria) this;
        }

        public Criteria andTemporaryOutGreaterThanOrEqualTo(String value) {
            addCriterion("TEMPORARY_OUT >=", value, "temporaryOut");
            return (Criteria) this;
        }

        public Criteria andTemporaryOutLessThan(String value) {
            addCriterion("TEMPORARY_OUT <", value, "temporaryOut");
            return (Criteria) this;
        }

        public Criteria andTemporaryOutLessThanOrEqualTo(String value) {
            addCriterion("TEMPORARY_OUT <=", value, "temporaryOut");
            return (Criteria) this;
        }

        public Criteria andTemporaryOutLike(String value) {
            addCriterion("TEMPORARY_OUT like", value, "temporaryOut");
            return (Criteria) this;
        }

        public Criteria andTemporaryOutNotLike(String value) {
            addCriterion("TEMPORARY_OUT not like", value, "temporaryOut");
            return (Criteria) this;
        }

        public Criteria andTemporaryOutIn(List<String> values) {
            addCriterion("TEMPORARY_OUT in", values, "temporaryOut");
            return (Criteria) this;
        }

        public Criteria andTemporaryOutNotIn(List<String> values) {
            addCriterion("TEMPORARY_OUT not in", values, "temporaryOut");
            return (Criteria) this;
        }

        public Criteria andTemporaryOutBetween(String value1, String value2) {
            addCriterion("TEMPORARY_OUT between", value1, value2, "temporaryOut");
            return (Criteria) this;
        }

        public Criteria andTemporaryOutNotBetween(String value1, String value2) {
            addCriterion("TEMPORARY_OUT not between", value1, value2, "temporaryOut");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusIdIsNull() {
            addCriterion("TEMPORARY_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusIdIsNotNull() {
            addCriterion("TEMPORARY_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusIdEqualTo(String value) {
            addCriterion("TEMPORARY_AUDIT_STATUS_ID =", value, "temporaryAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusIdNotEqualTo(String value) {
            addCriterion("TEMPORARY_AUDIT_STATUS_ID <>", value, "temporaryAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusIdGreaterThan(String value) {
            addCriterion("TEMPORARY_AUDIT_STATUS_ID >", value, "temporaryAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("TEMPORARY_AUDIT_STATUS_ID >=", value, "temporaryAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusIdLessThan(String value) {
            addCriterion("TEMPORARY_AUDIT_STATUS_ID <", value, "temporaryAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("TEMPORARY_AUDIT_STATUS_ID <=", value, "temporaryAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusIdLike(String value) {
            addCriterion("TEMPORARY_AUDIT_STATUS_ID like", value, "temporaryAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusIdNotLike(String value) {
            addCriterion("TEMPORARY_AUDIT_STATUS_ID not like", value, "temporaryAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusIdIn(List<String> values) {
            addCriterion("TEMPORARY_AUDIT_STATUS_ID in", values, "temporaryAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusIdNotIn(List<String> values) {
            addCriterion("TEMPORARY_AUDIT_STATUS_ID not in", values, "temporaryAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusIdBetween(String value1, String value2) {
            addCriterion("TEMPORARY_AUDIT_STATUS_ID between", value1, value2, "temporaryAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("TEMPORARY_AUDIT_STATUS_ID not between", value1, value2, "temporaryAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusNameIsNull() {
            addCriterion("TEMPORARY_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusNameIsNotNull() {
            addCriterion("TEMPORARY_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusNameEqualTo(String value) {
            addCriterion("TEMPORARY_AUDIT_STATUS_NAME =", value, "temporaryAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusNameNotEqualTo(String value) {
            addCriterion("TEMPORARY_AUDIT_STATUS_NAME <>", value, "temporaryAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusNameGreaterThan(String value) {
            addCriterion("TEMPORARY_AUDIT_STATUS_NAME >", value, "temporaryAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEMPORARY_AUDIT_STATUS_NAME >=", value, "temporaryAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusNameLessThan(String value) {
            addCriterion("TEMPORARY_AUDIT_STATUS_NAME <", value, "temporaryAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("TEMPORARY_AUDIT_STATUS_NAME <=", value, "temporaryAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusNameLike(String value) {
            addCriterion("TEMPORARY_AUDIT_STATUS_NAME like", value, "temporaryAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusNameNotLike(String value) {
            addCriterion("TEMPORARY_AUDIT_STATUS_NAME not like", value, "temporaryAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusNameIn(List<String> values) {
            addCriterion("TEMPORARY_AUDIT_STATUS_NAME in", values, "temporaryAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusNameNotIn(List<String> values) {
            addCriterion("TEMPORARY_AUDIT_STATUS_NAME not in", values, "temporaryAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusNameBetween(String value1, String value2) {
            addCriterion("TEMPORARY_AUDIT_STATUS_NAME between", value1, value2, "temporaryAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andTemporaryAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("TEMPORARY_AUDIT_STATUS_NAME not between", value1, value2, "temporaryAuditStatusName");
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