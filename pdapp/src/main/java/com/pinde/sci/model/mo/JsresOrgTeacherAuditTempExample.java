package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class JsresOrgTeacherAuditTempExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JsresOrgTeacherAuditTempExample() {
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

        public Criteria andRecordFlowIsNull() {
            addCriterion("RECORD_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRecordFlowIsNotNull() {
            addCriterion("RECORD_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRecordFlowEqualTo(String value) {
            addCriterion("RECORD_FLOW =", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotEqualTo(String value) {
            addCriterion("RECORD_FLOW <>", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowGreaterThan(String value) {
            addCriterion("RECORD_FLOW >", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD_FLOW >=", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowLessThan(String value) {
            addCriterion("RECORD_FLOW <", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowLessThanOrEqualTo(String value) {
            addCriterion("RECORD_FLOW <=", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowLike(String value) {
            addCriterion("RECORD_FLOW like", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotLike(String value) {
            addCriterion("RECORD_FLOW not like", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowIn(List<String> values) {
            addCriterion("RECORD_FLOW in", values, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotIn(List<String> values) {
            addCriterion("RECORD_FLOW not in", values, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowBetween(String value1, String value2) {
            addCriterion("RECORD_FLOW between", value1, value2, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotBetween(String value1, String value2) {
            addCriterion("RECORD_FLOW not between", value1, value2, "recordFlow");
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

        public Criteria andWorkOrgIdIsNull() {
            addCriterion("WORK_ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdIsNotNull() {
            addCriterion("WORK_ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdEqualTo(String value) {
            addCriterion("WORK_ORG_ID =", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdNotEqualTo(String value) {
            addCriterion("WORK_ORG_ID <>", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdGreaterThan(String value) {
            addCriterion("WORK_ORG_ID >", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_ID >=", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdLessThan(String value) {
            addCriterion("WORK_ORG_ID <", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdLessThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_ID <=", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdLike(String value) {
            addCriterion("WORK_ORG_ID like", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdNotLike(String value) {
            addCriterion("WORK_ORG_ID not like", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdIn(List<String> values) {
            addCriterion("WORK_ORG_ID in", values, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdNotIn(List<String> values) {
            addCriterion("WORK_ORG_ID not in", values, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdBetween(String value1, String value2) {
            addCriterion("WORK_ORG_ID between", value1, value2, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdNotBetween(String value1, String value2) {
            addCriterion("WORK_ORG_ID not between", value1, value2, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameIsNull() {
            addCriterion("WORK_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameIsNotNull() {
            addCriterion("WORK_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameEqualTo(String value) {
            addCriterion("WORK_ORG_NAME =", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameNotEqualTo(String value) {
            addCriterion("WORK_ORG_NAME <>", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameGreaterThan(String value) {
            addCriterion("WORK_ORG_NAME >", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_NAME >=", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameLessThan(String value) {
            addCriterion("WORK_ORG_NAME <", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameLessThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_NAME <=", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameLike(String value) {
            addCriterion("WORK_ORG_NAME like", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameNotLike(String value) {
            addCriterion("WORK_ORG_NAME not like", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameIn(List<String> values) {
            addCriterion("WORK_ORG_NAME in", values, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameNotIn(List<String> values) {
            addCriterion("WORK_ORG_NAME not in", values, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameBetween(String value1, String value2) {
            addCriterion("WORK_ORG_NAME between", value1, value2, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameNotBetween(String value1, String value2) {
            addCriterion("WORK_ORG_NAME not between", value1, value2, "workOrgName");
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

        public Criteria andCompleteNumIsNull() {
            addCriterion("COMPLETE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andCompleteNumIsNotNull() {
            addCriterion("COMPLETE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteNumEqualTo(String value) {
            addCriterion("COMPLETE_NUM =", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumNotEqualTo(String value) {
            addCriterion("COMPLETE_NUM <>", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumGreaterThan(String value) {
            addCriterion("COMPLETE_NUM >", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLETE_NUM >=", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumLessThan(String value) {
            addCriterion("COMPLETE_NUM <", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumLessThanOrEqualTo(String value) {
            addCriterion("COMPLETE_NUM <=", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumLike(String value) {
            addCriterion("COMPLETE_NUM like", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumNotLike(String value) {
            addCriterion("COMPLETE_NUM not like", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumIn(List<String> values) {
            addCriterion("COMPLETE_NUM in", values, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumNotIn(List<String> values) {
            addCriterion("COMPLETE_NUM not in", values, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumBetween(String value1, String value2) {
            addCriterion("COMPLETE_NUM between", value1, value2, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumNotBetween(String value1, String value2) {
            addCriterion("COMPLETE_NUM not between", value1, value2, "completeNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumIsNull() {
            addCriterion("AUDIT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andAuditNumIsNotNull() {
            addCriterion("AUDIT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andAuditNumEqualTo(String value) {
            addCriterion("AUDIT_NUM =", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumNotEqualTo(String value) {
            addCriterion("AUDIT_NUM <>", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumGreaterThan(String value) {
            addCriterion("AUDIT_NUM >", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_NUM >=", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumLessThan(String value) {
            addCriterion("AUDIT_NUM <", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_NUM <=", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumLike(String value) {
            addCriterion("AUDIT_NUM like", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumNotLike(String value) {
            addCriterion("AUDIT_NUM not like", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumIn(List<String> values) {
            addCriterion("AUDIT_NUM in", values, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumNotIn(List<String> values) {
            addCriterion("AUDIT_NUM not in", values, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumBetween(String value1, String value2) {
            addCriterion("AUDIT_NUM between", value1, value2, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumNotBetween(String value1, String value2) {
            addCriterion("AUDIT_NUM not between", value1, value2, "auditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumIsNull() {
            addCriterion("NOT_AUDIT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumIsNotNull() {
            addCriterion("NOT_AUDIT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumEqualTo(String value) {
            addCriterion("NOT_AUDIT_NUM =", value, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumNotEqualTo(String value) {
            addCriterion("NOT_AUDIT_NUM <>", value, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumGreaterThan(String value) {
            addCriterion("NOT_AUDIT_NUM >", value, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumGreaterThanOrEqualTo(String value) {
            addCriterion("NOT_AUDIT_NUM >=", value, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumLessThan(String value) {
            addCriterion("NOT_AUDIT_NUM <", value, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumLessThanOrEqualTo(String value) {
            addCriterion("NOT_AUDIT_NUM <=", value, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumLike(String value) {
            addCriterion("NOT_AUDIT_NUM like", value, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumNotLike(String value) {
            addCriterion("NOT_AUDIT_NUM not like", value, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumIn(List<String> values) {
            addCriterion("NOT_AUDIT_NUM in", values, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumNotIn(List<String> values) {
            addCriterion("NOT_AUDIT_NUM not in", values, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumBetween(String value1, String value2) {
            addCriterion("NOT_AUDIT_NUM between", value1, value2, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumNotBetween(String value1, String value2) {
            addCriterion("NOT_AUDIT_NUM not between", value1, value2, "notAuditNum");
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