package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class FstuAuditProcessExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FstuAuditProcessExample() {
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

        public Criteria andRelTypeIdIsNull() {
            addCriterion("REL_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andRelTypeIdIsNotNull() {
            addCriterion("REL_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRelTypeIdEqualTo(String value) {
            addCriterion("REL_TYPE_ID =", value, "relTypeId");
            return (Criteria) this;
        }

        public Criteria andRelTypeIdNotEqualTo(String value) {
            addCriterion("REL_TYPE_ID <>", value, "relTypeId");
            return (Criteria) this;
        }

        public Criteria andRelTypeIdGreaterThan(String value) {
            addCriterion("REL_TYPE_ID >", value, "relTypeId");
            return (Criteria) this;
        }

        public Criteria andRelTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("REL_TYPE_ID >=", value, "relTypeId");
            return (Criteria) this;
        }

        public Criteria andRelTypeIdLessThan(String value) {
            addCriterion("REL_TYPE_ID <", value, "relTypeId");
            return (Criteria) this;
        }

        public Criteria andRelTypeIdLessThanOrEqualTo(String value) {
            addCriterion("REL_TYPE_ID <=", value, "relTypeId");
            return (Criteria) this;
        }

        public Criteria andRelTypeIdLike(String value) {
            addCriterion("REL_TYPE_ID like", value, "relTypeId");
            return (Criteria) this;
        }

        public Criteria andRelTypeIdNotLike(String value) {
            addCriterion("REL_TYPE_ID not like", value, "relTypeId");
            return (Criteria) this;
        }

        public Criteria andRelTypeIdIn(List<String> values) {
            addCriterion("REL_TYPE_ID in", values, "relTypeId");
            return (Criteria) this;
        }

        public Criteria andRelTypeIdNotIn(List<String> values) {
            addCriterion("REL_TYPE_ID not in", values, "relTypeId");
            return (Criteria) this;
        }

        public Criteria andRelTypeIdBetween(String value1, String value2) {
            addCriterion("REL_TYPE_ID between", value1, value2, "relTypeId");
            return (Criteria) this;
        }

        public Criteria andRelTypeIdNotBetween(String value1, String value2) {
            addCriterion("REL_TYPE_ID not between", value1, value2, "relTypeId");
            return (Criteria) this;
        }

        public Criteria andRelFlowIsNull() {
            addCriterion("REL_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRelFlowIsNotNull() {
            addCriterion("REL_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRelFlowEqualTo(String value) {
            addCriterion("REL_FLOW =", value, "relFlow");
            return (Criteria) this;
        }

        public Criteria andRelFlowNotEqualTo(String value) {
            addCriterion("REL_FLOW <>", value, "relFlow");
            return (Criteria) this;
        }

        public Criteria andRelFlowGreaterThan(String value) {
            addCriterion("REL_FLOW >", value, "relFlow");
            return (Criteria) this;
        }

        public Criteria andRelFlowGreaterThanOrEqualTo(String value) {
            addCriterion("REL_FLOW >=", value, "relFlow");
            return (Criteria) this;
        }

        public Criteria andRelFlowLessThan(String value) {
            addCriterion("REL_FLOW <", value, "relFlow");
            return (Criteria) this;
        }

        public Criteria andRelFlowLessThanOrEqualTo(String value) {
            addCriterion("REL_FLOW <=", value, "relFlow");
            return (Criteria) this;
        }

        public Criteria andRelFlowLike(String value) {
            addCriterion("REL_FLOW like", value, "relFlow");
            return (Criteria) this;
        }

        public Criteria andRelFlowNotLike(String value) {
            addCriterion("REL_FLOW not like", value, "relFlow");
            return (Criteria) this;
        }

        public Criteria andRelFlowIn(List<String> values) {
            addCriterion("REL_FLOW in", values, "relFlow");
            return (Criteria) this;
        }

        public Criteria andRelFlowNotIn(List<String> values) {
            addCriterion("REL_FLOW not in", values, "relFlow");
            return (Criteria) this;
        }

        public Criteria andRelFlowBetween(String value1, String value2) {
            addCriterion("REL_FLOW between", value1, value2, "relFlow");
            return (Criteria) this;
        }

        public Criteria andRelFlowNotBetween(String value1, String value2) {
            addCriterion("REL_FLOW not between", value1, value2, "relFlow");
            return (Criteria) this;
        }

        public Criteria andProStatusIdIsNull() {
            addCriterion("PRO_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andProStatusIdIsNotNull() {
            addCriterion("PRO_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProStatusIdEqualTo(String value) {
            addCriterion("PRO_STATUS_ID =", value, "proStatusId");
            return (Criteria) this;
        }

        public Criteria andProStatusIdNotEqualTo(String value) {
            addCriterion("PRO_STATUS_ID <>", value, "proStatusId");
            return (Criteria) this;
        }

        public Criteria andProStatusIdGreaterThan(String value) {
            addCriterion("PRO_STATUS_ID >", value, "proStatusId");
            return (Criteria) this;
        }

        public Criteria andProStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("PRO_STATUS_ID >=", value, "proStatusId");
            return (Criteria) this;
        }

        public Criteria andProStatusIdLessThan(String value) {
            addCriterion("PRO_STATUS_ID <", value, "proStatusId");
            return (Criteria) this;
        }

        public Criteria andProStatusIdLessThanOrEqualTo(String value) {
            addCriterion("PRO_STATUS_ID <=", value, "proStatusId");
            return (Criteria) this;
        }

        public Criteria andProStatusIdLike(String value) {
            addCriterion("PRO_STATUS_ID like", value, "proStatusId");
            return (Criteria) this;
        }

        public Criteria andProStatusIdNotLike(String value) {
            addCriterion("PRO_STATUS_ID not like", value, "proStatusId");
            return (Criteria) this;
        }

        public Criteria andProStatusIdIn(List<String> values) {
            addCriterion("PRO_STATUS_ID in", values, "proStatusId");
            return (Criteria) this;
        }

        public Criteria andProStatusIdNotIn(List<String> values) {
            addCriterion("PRO_STATUS_ID not in", values, "proStatusId");
            return (Criteria) this;
        }

        public Criteria andProStatusIdBetween(String value1, String value2) {
            addCriterion("PRO_STATUS_ID between", value1, value2, "proStatusId");
            return (Criteria) this;
        }

        public Criteria andProStatusIdNotBetween(String value1, String value2) {
            addCriterion("PRO_STATUS_ID not between", value1, value2, "proStatusId");
            return (Criteria) this;
        }

        public Criteria andProStatusNameIsNull() {
            addCriterion("PRO_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProStatusNameIsNotNull() {
            addCriterion("PRO_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProStatusNameEqualTo(String value) {
            addCriterion("PRO_STATUS_NAME =", value, "proStatusName");
            return (Criteria) this;
        }

        public Criteria andProStatusNameNotEqualTo(String value) {
            addCriterion("PRO_STATUS_NAME <>", value, "proStatusName");
            return (Criteria) this;
        }

        public Criteria andProStatusNameGreaterThan(String value) {
            addCriterion("PRO_STATUS_NAME >", value, "proStatusName");
            return (Criteria) this;
        }

        public Criteria andProStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRO_STATUS_NAME >=", value, "proStatusName");
            return (Criteria) this;
        }

        public Criteria andProStatusNameLessThan(String value) {
            addCriterion("PRO_STATUS_NAME <", value, "proStatusName");
            return (Criteria) this;
        }

        public Criteria andProStatusNameLessThanOrEqualTo(String value) {
            addCriterion("PRO_STATUS_NAME <=", value, "proStatusName");
            return (Criteria) this;
        }

        public Criteria andProStatusNameLike(String value) {
            addCriterion("PRO_STATUS_NAME like", value, "proStatusName");
            return (Criteria) this;
        }

        public Criteria andProStatusNameNotLike(String value) {
            addCriterion("PRO_STATUS_NAME not like", value, "proStatusName");
            return (Criteria) this;
        }

        public Criteria andProStatusNameIn(List<String> values) {
            addCriterion("PRO_STATUS_NAME in", values, "proStatusName");
            return (Criteria) this;
        }

        public Criteria andProStatusNameNotIn(List<String> values) {
            addCriterion("PRO_STATUS_NAME not in", values, "proStatusName");
            return (Criteria) this;
        }

        public Criteria andProStatusNameBetween(String value1, String value2) {
            addCriterion("PRO_STATUS_NAME between", value1, value2, "proStatusName");
            return (Criteria) this;
        }

        public Criteria andProStatusNameNotBetween(String value1, String value2) {
            addCriterion("PRO_STATUS_NAME not between", value1, value2, "proStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditContentIsNull() {
            addCriterion("AUDIT_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andAuditContentIsNotNull() {
            addCriterion("AUDIT_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andAuditContentEqualTo(String value) {
            addCriterion("AUDIT_CONTENT =", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentNotEqualTo(String value) {
            addCriterion("AUDIT_CONTENT <>", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentGreaterThan(String value) {
            addCriterion("AUDIT_CONTENT >", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_CONTENT >=", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentLessThan(String value) {
            addCriterion("AUDIT_CONTENT <", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_CONTENT <=", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentLike(String value) {
            addCriterion("AUDIT_CONTENT like", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentNotLike(String value) {
            addCriterion("AUDIT_CONTENT not like", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentIn(List<String> values) {
            addCriterion("AUDIT_CONTENT in", values, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentNotIn(List<String> values) {
            addCriterion("AUDIT_CONTENT not in", values, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentBetween(String value1, String value2) {
            addCriterion("AUDIT_CONTENT between", value1, value2, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentNotBetween(String value1, String value2) {
            addCriterion("AUDIT_CONTENT not between", value1, value2, "auditContent");
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

        public Criteria andOperOrgFlowIsNull() {
            addCriterion("OPER_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andOperOrgFlowIsNotNull() {
            addCriterion("OPER_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andOperOrgFlowEqualTo(String value) {
            addCriterion("OPER_ORG_FLOW =", value, "operOrgFlow");
            return (Criteria) this;
        }

        public Criteria andOperOrgFlowNotEqualTo(String value) {
            addCriterion("OPER_ORG_FLOW <>", value, "operOrgFlow");
            return (Criteria) this;
        }

        public Criteria andOperOrgFlowGreaterThan(String value) {
            addCriterion("OPER_ORG_FLOW >", value, "operOrgFlow");
            return (Criteria) this;
        }

        public Criteria andOperOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_ORG_FLOW >=", value, "operOrgFlow");
            return (Criteria) this;
        }

        public Criteria andOperOrgFlowLessThan(String value) {
            addCriterion("OPER_ORG_FLOW <", value, "operOrgFlow");
            return (Criteria) this;
        }

        public Criteria andOperOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("OPER_ORG_FLOW <=", value, "operOrgFlow");
            return (Criteria) this;
        }

        public Criteria andOperOrgFlowLike(String value) {
            addCriterion("OPER_ORG_FLOW like", value, "operOrgFlow");
            return (Criteria) this;
        }

        public Criteria andOperOrgFlowNotLike(String value) {
            addCriterion("OPER_ORG_FLOW not like", value, "operOrgFlow");
            return (Criteria) this;
        }

        public Criteria andOperOrgFlowIn(List<String> values) {
            addCriterion("OPER_ORG_FLOW in", values, "operOrgFlow");
            return (Criteria) this;
        }

        public Criteria andOperOrgFlowNotIn(List<String> values) {
            addCriterion("OPER_ORG_FLOW not in", values, "operOrgFlow");
            return (Criteria) this;
        }

        public Criteria andOperOrgFlowBetween(String value1, String value2) {
            addCriterion("OPER_ORG_FLOW between", value1, value2, "operOrgFlow");
            return (Criteria) this;
        }

        public Criteria andOperOrgFlowNotBetween(String value1, String value2) {
            addCriterion("OPER_ORG_FLOW not between", value1, value2, "operOrgFlow");
            return (Criteria) this;
        }

        public Criteria andOperOrgNameIsNull() {
            addCriterion("OPER_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOperOrgNameIsNotNull() {
            addCriterion("OPER_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOperOrgNameEqualTo(String value) {
            addCriterion("OPER_ORG_NAME =", value, "operOrgName");
            return (Criteria) this;
        }

        public Criteria andOperOrgNameNotEqualTo(String value) {
            addCriterion("OPER_ORG_NAME <>", value, "operOrgName");
            return (Criteria) this;
        }

        public Criteria andOperOrgNameGreaterThan(String value) {
            addCriterion("OPER_ORG_NAME >", value, "operOrgName");
            return (Criteria) this;
        }

        public Criteria andOperOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_ORG_NAME >=", value, "operOrgName");
            return (Criteria) this;
        }

        public Criteria andOperOrgNameLessThan(String value) {
            addCriterion("OPER_ORG_NAME <", value, "operOrgName");
            return (Criteria) this;
        }

        public Criteria andOperOrgNameLessThanOrEqualTo(String value) {
            addCriterion("OPER_ORG_NAME <=", value, "operOrgName");
            return (Criteria) this;
        }

        public Criteria andOperOrgNameLike(String value) {
            addCriterion("OPER_ORG_NAME like", value, "operOrgName");
            return (Criteria) this;
        }

        public Criteria andOperOrgNameNotLike(String value) {
            addCriterion("OPER_ORG_NAME not like", value, "operOrgName");
            return (Criteria) this;
        }

        public Criteria andOperOrgNameIn(List<String> values) {
            addCriterion("OPER_ORG_NAME in", values, "operOrgName");
            return (Criteria) this;
        }

        public Criteria andOperOrgNameNotIn(List<String> values) {
            addCriterion("OPER_ORG_NAME not in", values, "operOrgName");
            return (Criteria) this;
        }

        public Criteria andOperOrgNameBetween(String value1, String value2) {
            addCriterion("OPER_ORG_NAME between", value1, value2, "operOrgName");
            return (Criteria) this;
        }

        public Criteria andOperOrgNameNotBetween(String value1, String value2) {
            addCriterion("OPER_ORG_NAME not between", value1, value2, "operOrgName");
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