package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class SysDeptMonthPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysDeptMonthPlanExample() {
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

        public Criteria andPlanFlowIsNull() {
            addCriterion("PLAN_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPlanFlowIsNotNull() {
            addCriterion("PLAN_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPlanFlowEqualTo(String value) {
            addCriterion("PLAN_FLOW =", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotEqualTo(String value) {
            addCriterion("PLAN_FLOW <>", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowGreaterThan(String value) {
            addCriterion("PLAN_FLOW >", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_FLOW >=", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowLessThan(String value) {
            addCriterion("PLAN_FLOW <", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowLessThanOrEqualTo(String value) {
            addCriterion("PLAN_FLOW <=", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowLike(String value) {
            addCriterion("PLAN_FLOW like", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotLike(String value) {
            addCriterion("PLAN_FLOW not like", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowIn(List<String> values) {
            addCriterion("PLAN_FLOW in", values, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotIn(List<String> values) {
            addCriterion("PLAN_FLOW not in", values, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowBetween(String value1, String value2) {
            addCriterion("PLAN_FLOW between", value1, value2, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotBetween(String value1, String value2) {
            addCriterion("PLAN_FLOW not between", value1, value2, "planFlow");
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

        public Criteria andPlanDateIsNull() {
            addCriterion("PLAN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPlanDateIsNotNull() {
            addCriterion("PLAN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDateEqualTo(String value) {
            addCriterion("PLAN_DATE =", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateNotEqualTo(String value) {
            addCriterion("PLAN_DATE <>", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateGreaterThan(String value) {
            addCriterion("PLAN_DATE >", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_DATE >=", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateLessThan(String value) {
            addCriterion("PLAN_DATE <", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateLessThanOrEqualTo(String value) {
            addCriterion("PLAN_DATE <=", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateLike(String value) {
            addCriterion("PLAN_DATE like", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateNotLike(String value) {
            addCriterion("PLAN_DATE not like", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateIn(List<String> values) {
            addCriterion("PLAN_DATE in", values, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateNotIn(List<String> values) {
            addCriterion("PLAN_DATE not in", values, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateBetween(String value1, String value2) {
            addCriterion("PLAN_DATE between", value1, value2, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateNotBetween(String value1, String value2) {
            addCriterion("PLAN_DATE not between", value1, value2, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdIsNull() {
            addCriterion("PLAN_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdIsNotNull() {
            addCriterion("PLAN_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdEqualTo(String value) {
            addCriterion("PLAN_TYPE_ID =", value, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdNotEqualTo(String value) {
            addCriterion("PLAN_TYPE_ID <>", value, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdGreaterThan(String value) {
            addCriterion("PLAN_TYPE_ID >", value, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_TYPE_ID >=", value, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdLessThan(String value) {
            addCriterion("PLAN_TYPE_ID <", value, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdLessThanOrEqualTo(String value) {
            addCriterion("PLAN_TYPE_ID <=", value, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdLike(String value) {
            addCriterion("PLAN_TYPE_ID like", value, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdNotLike(String value) {
            addCriterion("PLAN_TYPE_ID not like", value, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdIn(List<String> values) {
            addCriterion("PLAN_TYPE_ID in", values, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdNotIn(List<String> values) {
            addCriterion("PLAN_TYPE_ID not in", values, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdBetween(String value1, String value2) {
            addCriterion("PLAN_TYPE_ID between", value1, value2, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdNotBetween(String value1, String value2) {
            addCriterion("PLAN_TYPE_ID not between", value1, value2, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameIsNull() {
            addCriterion("PLAN_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameIsNotNull() {
            addCriterion("PLAN_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameEqualTo(String value) {
            addCriterion("PLAN_TYPE_NAME =", value, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameNotEqualTo(String value) {
            addCriterion("PLAN_TYPE_NAME <>", value, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameGreaterThan(String value) {
            addCriterion("PLAN_TYPE_NAME >", value, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_TYPE_NAME >=", value, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameLessThan(String value) {
            addCriterion("PLAN_TYPE_NAME <", value, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameLessThanOrEqualTo(String value) {
            addCriterion("PLAN_TYPE_NAME <=", value, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameLike(String value) {
            addCriterion("PLAN_TYPE_NAME like", value, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameNotLike(String value) {
            addCriterion("PLAN_TYPE_NAME not like", value, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameIn(List<String> values) {
            addCriterion("PLAN_TYPE_NAME in", values, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameNotIn(List<String> values) {
            addCriterion("PLAN_TYPE_NAME not in", values, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameBetween(String value1, String value2) {
            addCriterion("PLAN_TYPE_NAME between", value1, value2, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameNotBetween(String value1, String value2) {
            addCriterion("PLAN_TYPE_NAME not between", value1, value2, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanDemoIsNull() {
            addCriterion("PLAN_DEMO is null");
            return (Criteria) this;
        }

        public Criteria andPlanDemoIsNotNull() {
            addCriterion("PLAN_DEMO is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDemoEqualTo(String value) {
            addCriterion("PLAN_DEMO =", value, "planDemo");
            return (Criteria) this;
        }

        public Criteria andPlanDemoNotEqualTo(String value) {
            addCriterion("PLAN_DEMO <>", value, "planDemo");
            return (Criteria) this;
        }

        public Criteria andPlanDemoGreaterThan(String value) {
            addCriterion("PLAN_DEMO >", value, "planDemo");
            return (Criteria) this;
        }

        public Criteria andPlanDemoGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_DEMO >=", value, "planDemo");
            return (Criteria) this;
        }

        public Criteria andPlanDemoLessThan(String value) {
            addCriterion("PLAN_DEMO <", value, "planDemo");
            return (Criteria) this;
        }

        public Criteria andPlanDemoLessThanOrEqualTo(String value) {
            addCriterion("PLAN_DEMO <=", value, "planDemo");
            return (Criteria) this;
        }

        public Criteria andPlanDemoLike(String value) {
            addCriterion("PLAN_DEMO like", value, "planDemo");
            return (Criteria) this;
        }

        public Criteria andPlanDemoNotLike(String value) {
            addCriterion("PLAN_DEMO not like", value, "planDemo");
            return (Criteria) this;
        }

        public Criteria andPlanDemoIn(List<String> values) {
            addCriterion("PLAN_DEMO in", values, "planDemo");
            return (Criteria) this;
        }

        public Criteria andPlanDemoNotIn(List<String> values) {
            addCriterion("PLAN_DEMO not in", values, "planDemo");
            return (Criteria) this;
        }

        public Criteria andPlanDemoBetween(String value1, String value2) {
            addCriterion("PLAN_DEMO between", value1, value2, "planDemo");
            return (Criteria) this;
        }

        public Criteria andPlanDemoNotBetween(String value1, String value2) {
            addCriterion("PLAN_DEMO not between", value1, value2, "planDemo");
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

        public Criteria andIsReportIsNull() {
            addCriterion("IS_REPORT is null");
            return (Criteria) this;
        }

        public Criteria andIsReportIsNotNull() {
            addCriterion("IS_REPORT is not null");
            return (Criteria) this;
        }

        public Criteria andIsReportEqualTo(String value) {
            addCriterion("IS_REPORT =", value, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportNotEqualTo(String value) {
            addCriterion("IS_REPORT <>", value, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportGreaterThan(String value) {
            addCriterion("IS_REPORT >", value, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportGreaterThanOrEqualTo(String value) {
            addCriterion("IS_REPORT >=", value, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportLessThan(String value) {
            addCriterion("IS_REPORT <", value, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportLessThanOrEqualTo(String value) {
            addCriterion("IS_REPORT <=", value, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportLike(String value) {
            addCriterion("IS_REPORT like", value, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportNotLike(String value) {
            addCriterion("IS_REPORT not like", value, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportIn(List<String> values) {
            addCriterion("IS_REPORT in", values, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportNotIn(List<String> values) {
            addCriterion("IS_REPORT not in", values, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportBetween(String value1, String value2) {
            addCriterion("IS_REPORT between", value1, value2, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportNotBetween(String value1, String value2) {
            addCriterion("IS_REPORT not between", value1, value2, "isReport");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTimeIsNull() {
            addCriterion("THEORY_EXAM_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTimeIsNotNull() {
            addCriterion("THEORY_EXAM_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTimeEqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME =", value, "theoryExamTime");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTimeNotEqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME <>", value, "theoryExamTime");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTimeGreaterThan(String value) {
            addCriterion("THEORY_EXAM_TIME >", value, "theoryExamTime");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTimeGreaterThanOrEqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME >=", value, "theoryExamTime");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTimeLessThan(String value) {
            addCriterion("THEORY_EXAM_TIME <", value, "theoryExamTime");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTimeLessThanOrEqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME <=", value, "theoryExamTime");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTimeLike(String value) {
            addCriterion("THEORY_EXAM_TIME like", value, "theoryExamTime");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTimeNotLike(String value) {
            addCriterion("THEORY_EXAM_TIME not like", value, "theoryExamTime");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTimeIn(List<String> values) {
            addCriterion("THEORY_EXAM_TIME in", values, "theoryExamTime");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTimeNotIn(List<String> values) {
            addCriterion("THEORY_EXAM_TIME not in", values, "theoryExamTime");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTimeBetween(String value1, String value2) {
            addCriterion("THEORY_EXAM_TIME between", value1, value2, "theoryExamTime");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTimeNotBetween(String value1, String value2) {
            addCriterion("THEORY_EXAM_TIME not between", value1, value2, "theoryExamTime");
            return (Criteria) this;
        }

        public Criteria andSkillExamTimeIsNull() {
            addCriterion("SKILL_EXAM_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSkillExamTimeIsNotNull() {
            addCriterion("SKILL_EXAM_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSkillExamTimeEqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME =", value, "skillExamTime");
            return (Criteria) this;
        }

        public Criteria andSkillExamTimeNotEqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME <>", value, "skillExamTime");
            return (Criteria) this;
        }

        public Criteria andSkillExamTimeGreaterThan(String value) {
            addCriterion("SKILL_EXAM_TIME >", value, "skillExamTime");
            return (Criteria) this;
        }

        public Criteria andSkillExamTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME >=", value, "skillExamTime");
            return (Criteria) this;
        }

        public Criteria andSkillExamTimeLessThan(String value) {
            addCriterion("SKILL_EXAM_TIME <", value, "skillExamTime");
            return (Criteria) this;
        }

        public Criteria andSkillExamTimeLessThanOrEqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME <=", value, "skillExamTime");
            return (Criteria) this;
        }

        public Criteria andSkillExamTimeLike(String value) {
            addCriterion("SKILL_EXAM_TIME like", value, "skillExamTime");
            return (Criteria) this;
        }

        public Criteria andSkillExamTimeNotLike(String value) {
            addCriterion("SKILL_EXAM_TIME not like", value, "skillExamTime");
            return (Criteria) this;
        }

        public Criteria andSkillExamTimeIn(List<String> values) {
            addCriterion("SKILL_EXAM_TIME in", values, "skillExamTime");
            return (Criteria) this;
        }

        public Criteria andSkillExamTimeNotIn(List<String> values) {
            addCriterion("SKILL_EXAM_TIME not in", values, "skillExamTime");
            return (Criteria) this;
        }

        public Criteria andSkillExamTimeBetween(String value1, String value2) {
            addCriterion("SKILL_EXAM_TIME between", value1, value2, "skillExamTime");
            return (Criteria) this;
        }

        public Criteria andSkillExamTimeNotBetween(String value1, String value2) {
            addCriterion("SKILL_EXAM_TIME not between", value1, value2, "skillExamTime");
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

        public Criteria andSkillOrTheoryIsNull() {
            addCriterion("SKILL_OR_THEORY is null");
            return (Criteria) this;
        }

        public Criteria andSkillOrTheoryIsNotNull() {
            addCriterion("SKILL_OR_THEORY is not null");
            return (Criteria) this;
        }

        public Criteria andSkillOrTheoryEqualTo(String value) {
            addCriterion("SKILL_OR_THEORY =", value, "skillOrTheory");
            return (Criteria) this;
        }

        public Criteria andSkillOrTheoryNotEqualTo(String value) {
            addCriterion("SKILL_OR_THEORY <>", value, "skillOrTheory");
            return (Criteria) this;
        }

        public Criteria andSkillOrTheoryGreaterThan(String value) {
            addCriterion("SKILL_OR_THEORY >", value, "skillOrTheory");
            return (Criteria) this;
        }

        public Criteria andSkillOrTheoryGreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_OR_THEORY >=", value, "skillOrTheory");
            return (Criteria) this;
        }

        public Criteria andSkillOrTheoryLessThan(String value) {
            addCriterion("SKILL_OR_THEORY <", value, "skillOrTheory");
            return (Criteria) this;
        }

        public Criteria andSkillOrTheoryLessThanOrEqualTo(String value) {
            addCriterion("SKILL_OR_THEORY <=", value, "skillOrTheory");
            return (Criteria) this;
        }

        public Criteria andSkillOrTheoryLike(String value) {
            addCriterion("SKILL_OR_THEORY like", value, "skillOrTheory");
            return (Criteria) this;
        }

        public Criteria andSkillOrTheoryNotLike(String value) {
            addCriterion("SKILL_OR_THEORY not like", value, "skillOrTheory");
            return (Criteria) this;
        }

        public Criteria andSkillOrTheoryIn(List<String> values) {
            addCriterion("SKILL_OR_THEORY in", values, "skillOrTheory");
            return (Criteria) this;
        }

        public Criteria andSkillOrTheoryNotIn(List<String> values) {
            addCriterion("SKILL_OR_THEORY not in", values, "skillOrTheory");
            return (Criteria) this;
        }

        public Criteria andSkillOrTheoryBetween(String value1, String value2) {
            addCriterion("SKILL_OR_THEORY between", value1, value2, "skillOrTheory");
            return (Criteria) this;
        }

        public Criteria andSkillOrTheoryNotBetween(String value1, String value2) {
            addCriterion("SKILL_OR_THEORY not between", value1, value2, "skillOrTheory");
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