package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class JsresSignExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JsresSignExample() {
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

        public Criteria andSignFlowIsNull() {
            addCriterion("SIGN_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSignFlowIsNotNull() {
            addCriterion("SIGN_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSignFlowEqualTo(String value) {
            addCriterion("SIGN_FLOW =", value, "signFlow");
            return (Criteria) this;
        }

        public Criteria andSignFlowNotEqualTo(String value) {
            addCriterion("SIGN_FLOW <>", value, "signFlow");
            return (Criteria) this;
        }

        public Criteria andSignFlowGreaterThan(String value) {
            addCriterion("SIGN_FLOW >", value, "signFlow");
            return (Criteria) this;
        }

        public Criteria andSignFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SIGN_FLOW >=", value, "signFlow");
            return (Criteria) this;
        }

        public Criteria andSignFlowLessThan(String value) {
            addCriterion("SIGN_FLOW <", value, "signFlow");
            return (Criteria) this;
        }

        public Criteria andSignFlowLessThanOrEqualTo(String value) {
            addCriterion("SIGN_FLOW <=", value, "signFlow");
            return (Criteria) this;
        }

        public Criteria andSignFlowLike(String value) {
            addCriterion("SIGN_FLOW like", value, "signFlow");
            return (Criteria) this;
        }

        public Criteria andSignFlowNotLike(String value) {
            addCriterion("SIGN_FLOW not like", value, "signFlow");
            return (Criteria) this;
        }

        public Criteria andSignFlowIn(List<String> values) {
            addCriterion("SIGN_FLOW in", values, "signFlow");
            return (Criteria) this;
        }

        public Criteria andSignFlowNotIn(List<String> values) {
            addCriterion("SIGN_FLOW not in", values, "signFlow");
            return (Criteria) this;
        }

        public Criteria andSignFlowBetween(String value1, String value2) {
            addCriterion("SIGN_FLOW between", value1, value2, "signFlow");
            return (Criteria) this;
        }

        public Criteria andSignFlowNotBetween(String value1, String value2) {
            addCriterion("SIGN_FLOW not between", value1, value2, "signFlow");
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

        public Criteria andSignUrlIsNull() {
            addCriterion("SIGN_URL is null");
            return (Criteria) this;
        }

        public Criteria andSignUrlIsNotNull() {
            addCriterion("SIGN_URL is not null");
            return (Criteria) this;
        }

        public Criteria andSignUrlEqualTo(String value) {
            addCriterion("SIGN_URL =", value, "signUrl");
            return (Criteria) this;
        }

        public Criteria andSignUrlNotEqualTo(String value) {
            addCriterion("SIGN_URL <>", value, "signUrl");
            return (Criteria) this;
        }

        public Criteria andSignUrlGreaterThan(String value) {
            addCriterion("SIGN_URL >", value, "signUrl");
            return (Criteria) this;
        }

        public Criteria andSignUrlGreaterThanOrEqualTo(String value) {
            addCriterion("SIGN_URL >=", value, "signUrl");
            return (Criteria) this;
        }

        public Criteria andSignUrlLessThan(String value) {
            addCriterion("SIGN_URL <", value, "signUrl");
            return (Criteria) this;
        }

        public Criteria andSignUrlLessThanOrEqualTo(String value) {
            addCriterion("SIGN_URL <=", value, "signUrl");
            return (Criteria) this;
        }

        public Criteria andSignUrlLike(String value) {
            addCriterion("SIGN_URL like", value, "signUrl");
            return (Criteria) this;
        }

        public Criteria andSignUrlNotLike(String value) {
            addCriterion("SIGN_URL not like", value, "signUrl");
            return (Criteria) this;
        }

        public Criteria andSignUrlIn(List<String> values) {
            addCriterion("SIGN_URL in", values, "signUrl");
            return (Criteria) this;
        }

        public Criteria andSignUrlNotIn(List<String> values) {
            addCriterion("SIGN_URL not in", values, "signUrl");
            return (Criteria) this;
        }

        public Criteria andSignUrlBetween(String value1, String value2) {
            addCriterion("SIGN_URL between", value1, value2, "signUrl");
            return (Criteria) this;
        }

        public Criteria andSignUrlNotBetween(String value1, String value2) {
            addCriterion("SIGN_URL not between", value1, value2, "signUrl");
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

        public Criteria andPresidentNameIsNull() {
            addCriterion("PRESIDENT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPresidentNameIsNotNull() {
            addCriterion("PRESIDENT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPresidentNameEqualTo(String value) {
            addCriterion("PRESIDENT_NAME =", value, "presidentName");
            return (Criteria) this;
        }

        public Criteria andPresidentNameNotEqualTo(String value) {
            addCriterion("PRESIDENT_NAME <>", value, "presidentName");
            return (Criteria) this;
        }

        public Criteria andPresidentNameGreaterThan(String value) {
            addCriterion("PRESIDENT_NAME >", value, "presidentName");
            return (Criteria) this;
        }

        public Criteria andPresidentNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRESIDENT_NAME >=", value, "presidentName");
            return (Criteria) this;
        }

        public Criteria andPresidentNameLessThan(String value) {
            addCriterion("PRESIDENT_NAME <", value, "presidentName");
            return (Criteria) this;
        }

        public Criteria andPresidentNameLessThanOrEqualTo(String value) {
            addCriterion("PRESIDENT_NAME <=", value, "presidentName");
            return (Criteria) this;
        }

        public Criteria andPresidentNameLike(String value) {
            addCriterion("PRESIDENT_NAME like", value, "presidentName");
            return (Criteria) this;
        }

        public Criteria andPresidentNameNotLike(String value) {
            addCriterion("PRESIDENT_NAME not like", value, "presidentName");
            return (Criteria) this;
        }

        public Criteria andPresidentNameIn(List<String> values) {
            addCriterion("PRESIDENT_NAME in", values, "presidentName");
            return (Criteria) this;
        }

        public Criteria andPresidentNameNotIn(List<String> values) {
            addCriterion("PRESIDENT_NAME not in", values, "presidentName");
            return (Criteria) this;
        }

        public Criteria andPresidentNameBetween(String value1, String value2) {
            addCriterion("PRESIDENT_NAME between", value1, value2, "presidentName");
            return (Criteria) this;
        }

        public Criteria andPresidentNameNotBetween(String value1, String value2) {
            addCriterion("PRESIDENT_NAME not between", value1, value2, "presidentName");
            return (Criteria) this;
        }

        public Criteria andUseTimeIsNull() {
            addCriterion("USE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUseTimeIsNotNull() {
            addCriterion("USE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUseTimeEqualTo(String value) {
            addCriterion("USE_TIME =", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeNotEqualTo(String value) {
            addCriterion("USE_TIME <>", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeGreaterThan(String value) {
            addCriterion("USE_TIME >", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeGreaterThanOrEqualTo(String value) {
            addCriterion("USE_TIME >=", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeLessThan(String value) {
            addCriterion("USE_TIME <", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeLessThanOrEqualTo(String value) {
            addCriterion("USE_TIME <=", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeLike(String value) {
            addCriterion("USE_TIME like", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeNotLike(String value) {
            addCriterion("USE_TIME not like", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeIn(List<String> values) {
            addCriterion("USE_TIME in", values, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeNotIn(List<String> values) {
            addCriterion("USE_TIME not in", values, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeBetween(String value1, String value2) {
            addCriterion("USE_TIME between", value1, value2, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeNotBetween(String value1, String value2) {
            addCriterion("USE_TIME not between", value1, value2, "useTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeIsNull() {
            addCriterion("STOP_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStopTimeIsNotNull() {
            addCriterion("STOP_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStopTimeEqualTo(String value) {
            addCriterion("STOP_TIME =", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeNotEqualTo(String value) {
            addCriterion("STOP_TIME <>", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeGreaterThan(String value) {
            addCriterion("STOP_TIME >", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeGreaterThanOrEqualTo(String value) {
            addCriterion("STOP_TIME >=", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeLessThan(String value) {
            addCriterion("STOP_TIME <", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeLessThanOrEqualTo(String value) {
            addCriterion("STOP_TIME <=", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeLike(String value) {
            addCriterion("STOP_TIME like", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeNotLike(String value) {
            addCriterion("STOP_TIME not like", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeIn(List<String> values) {
            addCriterion("STOP_TIME in", values, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeNotIn(List<String> values) {
            addCriterion("STOP_TIME not in", values, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeBetween(String value1, String value2) {
            addCriterion("STOP_TIME between", value1, value2, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeNotBetween(String value1, String value2) {
            addCriterion("STOP_TIME not between", value1, value2, "stopTime");
            return (Criteria) this;
        }

        public Criteria andUseStatusIdIsNull() {
            addCriterion("USE_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andUseStatusIdIsNotNull() {
            addCriterion("USE_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUseStatusIdEqualTo(String value) {
            addCriterion("USE_STATUS_ID =", value, "useStatusId");
            return (Criteria) this;
        }

        public Criteria andUseStatusIdNotEqualTo(String value) {
            addCriterion("USE_STATUS_ID <>", value, "useStatusId");
            return (Criteria) this;
        }

        public Criteria andUseStatusIdGreaterThan(String value) {
            addCriterion("USE_STATUS_ID >", value, "useStatusId");
            return (Criteria) this;
        }

        public Criteria andUseStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("USE_STATUS_ID >=", value, "useStatusId");
            return (Criteria) this;
        }

        public Criteria andUseStatusIdLessThan(String value) {
            addCriterion("USE_STATUS_ID <", value, "useStatusId");
            return (Criteria) this;
        }

        public Criteria andUseStatusIdLessThanOrEqualTo(String value) {
            addCriterion("USE_STATUS_ID <=", value, "useStatusId");
            return (Criteria) this;
        }

        public Criteria andUseStatusIdLike(String value) {
            addCriterion("USE_STATUS_ID like", value, "useStatusId");
            return (Criteria) this;
        }

        public Criteria andUseStatusIdNotLike(String value) {
            addCriterion("USE_STATUS_ID not like", value, "useStatusId");
            return (Criteria) this;
        }

        public Criteria andUseStatusIdIn(List<String> values) {
            addCriterion("USE_STATUS_ID in", values, "useStatusId");
            return (Criteria) this;
        }

        public Criteria andUseStatusIdNotIn(List<String> values) {
            addCriterion("USE_STATUS_ID not in", values, "useStatusId");
            return (Criteria) this;
        }

        public Criteria andUseStatusIdBetween(String value1, String value2) {
            addCriterion("USE_STATUS_ID between", value1, value2, "useStatusId");
            return (Criteria) this;
        }

        public Criteria andUseStatusIdNotBetween(String value1, String value2) {
            addCriterion("USE_STATUS_ID not between", value1, value2, "useStatusId");
            return (Criteria) this;
        }

        public Criteria andUseStatusNameIsNull() {
            addCriterion("USE_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUseStatusNameIsNotNull() {
            addCriterion("USE_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUseStatusNameEqualTo(String value) {
            addCriterion("USE_STATUS_NAME =", value, "useStatusName");
            return (Criteria) this;
        }

        public Criteria andUseStatusNameNotEqualTo(String value) {
            addCriterion("USE_STATUS_NAME <>", value, "useStatusName");
            return (Criteria) this;
        }

        public Criteria andUseStatusNameGreaterThan(String value) {
            addCriterion("USE_STATUS_NAME >", value, "useStatusName");
            return (Criteria) this;
        }

        public Criteria andUseStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("USE_STATUS_NAME >=", value, "useStatusName");
            return (Criteria) this;
        }

        public Criteria andUseStatusNameLessThan(String value) {
            addCriterion("USE_STATUS_NAME <", value, "useStatusName");
            return (Criteria) this;
        }

        public Criteria andUseStatusNameLessThanOrEqualTo(String value) {
            addCriterion("USE_STATUS_NAME <=", value, "useStatusName");
            return (Criteria) this;
        }

        public Criteria andUseStatusNameLike(String value) {
            addCriterion("USE_STATUS_NAME like", value, "useStatusName");
            return (Criteria) this;
        }

        public Criteria andUseStatusNameNotLike(String value) {
            addCriterion("USE_STATUS_NAME not like", value, "useStatusName");
            return (Criteria) this;
        }

        public Criteria andUseStatusNameIn(List<String> values) {
            addCriterion("USE_STATUS_NAME in", values, "useStatusName");
            return (Criteria) this;
        }

        public Criteria andUseStatusNameNotIn(List<String> values) {
            addCriterion("USE_STATUS_NAME not in", values, "useStatusName");
            return (Criteria) this;
        }

        public Criteria andUseStatusNameBetween(String value1, String value2) {
            addCriterion("USE_STATUS_NAME between", value1, value2, "useStatusName");
            return (Criteria) this;
        }

        public Criteria andUseStatusNameNotBetween(String value1, String value2) {
            addCriterion("USE_STATUS_NAME not between", value1, value2, "useStatusName");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIsNull() {
            addCriterion("SESSION_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIsNotNull() {
            addCriterion("SESSION_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberEqualTo(String value) {
            addCriterion("SESSION_NUMBER =", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotEqualTo(String value) {
            addCriterion("SESSION_NUMBER <>", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThan(String value) {
            addCriterion("SESSION_NUMBER >", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER >=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThan(String value) {
            addCriterion("SESSION_NUMBER <", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER <=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLike(String value) {
            addCriterion("SESSION_NUMBER like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotLike(String value) {
            addCriterion("SESSION_NUMBER not like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIn(List<String> values) {
            addCriterion("SESSION_NUMBER in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotIn(List<String> values) {
            addCriterion("SESSION_NUMBER not in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER between", value1, value2, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER not between", value1, value2, "sessionNumber");
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