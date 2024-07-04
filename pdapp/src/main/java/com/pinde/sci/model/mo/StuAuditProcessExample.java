package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class StuAuditProcessExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StuAuditProcessExample() {
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

        public Criteria andResumeFlowIsNull() {
            addCriterion("RESUME_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andResumeFlowIsNotNull() {
            addCriterion("RESUME_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andResumeFlowEqualTo(String value) {
            addCriterion("RESUME_FLOW =", value, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowNotEqualTo(String value) {
            addCriterion("RESUME_FLOW <>", value, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowGreaterThan(String value) {
            addCriterion("RESUME_FLOW >", value, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RESUME_FLOW >=", value, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowLessThan(String value) {
            addCriterion("RESUME_FLOW <", value, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowLessThanOrEqualTo(String value) {
            addCriterion("RESUME_FLOW <=", value, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowLike(String value) {
            addCriterion("RESUME_FLOW like", value, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowNotLike(String value) {
            addCriterion("RESUME_FLOW not like", value, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowIn(List<String> values) {
            addCriterion("RESUME_FLOW in", values, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowNotIn(List<String> values) {
            addCriterion("RESUME_FLOW not in", values, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowBetween(String value1, String value2) {
            addCriterion("RESUME_FLOW between", value1, value2, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowNotBetween(String value1, String value2) {
            addCriterion("RESUME_FLOW not between", value1, value2, "resumeFlow");
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

        public Criteria andStuStatusIdIsNull() {
            addCriterion("STU_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdIsNotNull() {
            addCriterion("STU_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdEqualTo(String value) {
            addCriterion("STU_STATUS_ID =", value, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdNotEqualTo(String value) {
            addCriterion("STU_STATUS_ID <>", value, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdGreaterThan(String value) {
            addCriterion("STU_STATUS_ID >", value, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("STU_STATUS_ID >=", value, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdLessThan(String value) {
            addCriterion("STU_STATUS_ID <", value, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdLessThanOrEqualTo(String value) {
            addCriterion("STU_STATUS_ID <=", value, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdLike(String value) {
            addCriterion("STU_STATUS_ID like", value, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdNotLike(String value) {
            addCriterion("STU_STATUS_ID not like", value, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdIn(List<String> values) {
            addCriterion("STU_STATUS_ID in", values, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdNotIn(List<String> values) {
            addCriterion("STU_STATUS_ID not in", values, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdBetween(String value1, String value2) {
            addCriterion("STU_STATUS_ID between", value1, value2, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdNotBetween(String value1, String value2) {
            addCriterion("STU_STATUS_ID not between", value1, value2, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameIsNull() {
            addCriterion("STU_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameIsNotNull() {
            addCriterion("STU_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameEqualTo(String value) {
            addCriterion("STU_STATUS_NAME =", value, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameNotEqualTo(String value) {
            addCriterion("STU_STATUS_NAME <>", value, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameGreaterThan(String value) {
            addCriterion("STU_STATUS_NAME >", value, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("STU_STATUS_NAME >=", value, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameLessThan(String value) {
            addCriterion("STU_STATUS_NAME <", value, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameLessThanOrEqualTo(String value) {
            addCriterion("STU_STATUS_NAME <=", value, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameLike(String value) {
            addCriterion("STU_STATUS_NAME like", value, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameNotLike(String value) {
            addCriterion("STU_STATUS_NAME not like", value, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameIn(List<String> values) {
            addCriterion("STU_STATUS_NAME in", values, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameNotIn(List<String> values) {
            addCriterion("STU_STATUS_NAME not in", values, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameBetween(String value1, String value2) {
            addCriterion("STU_STATUS_NAME between", value1, value2, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameNotBetween(String value1, String value2) {
            addCriterion("STU_STATUS_NAME not between", value1, value2, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkIsNull() {
            addCriterion("PROCESS_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkIsNotNull() {
            addCriterion("PROCESS_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkEqualTo(String value) {
            addCriterion("PROCESS_REMARK =", value, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkNotEqualTo(String value) {
            addCriterion("PROCESS_REMARK <>", value, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkGreaterThan(String value) {
            addCriterion("PROCESS_REMARK >", value, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("PROCESS_REMARK >=", value, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkLessThan(String value) {
            addCriterion("PROCESS_REMARK <", value, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkLessThanOrEqualTo(String value) {
            addCriterion("PROCESS_REMARK <=", value, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkLike(String value) {
            addCriterion("PROCESS_REMARK like", value, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkNotLike(String value) {
            addCriterion("PROCESS_REMARK not like", value, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkIn(List<String> values) {
            addCriterion("PROCESS_REMARK in", values, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkNotIn(List<String> values) {
            addCriterion("PROCESS_REMARK not in", values, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkBetween(String value1, String value2) {
            addCriterion("PROCESS_REMARK between", value1, value2, "processRemark");
            return (Criteria) this;
        }

        public Criteria andProcessRemarkNotBetween(String value1, String value2) {
            addCriterion("PROCESS_REMARK not between", value1, value2, "processRemark");
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