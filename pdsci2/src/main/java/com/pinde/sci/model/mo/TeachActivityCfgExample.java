package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class TeachActivityCfgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TeachActivityCfgExample() {
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

        public Criteria andSubmitRoleIsNull() {
            addCriterion("SUBMIT_ROLE is null");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleIsNotNull() {
            addCriterion("SUBMIT_ROLE is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleEqualTo(String value) {
            addCriterion("SUBMIT_ROLE =", value, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleNotEqualTo(String value) {
            addCriterion("SUBMIT_ROLE <>", value, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleGreaterThan(String value) {
            addCriterion("SUBMIT_ROLE >", value, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleGreaterThanOrEqualTo(String value) {
            addCriterion("SUBMIT_ROLE >=", value, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleLessThan(String value) {
            addCriterion("SUBMIT_ROLE <", value, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleLessThanOrEqualTo(String value) {
            addCriterion("SUBMIT_ROLE <=", value, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleLike(String value) {
            addCriterion("SUBMIT_ROLE like", value, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleNotLike(String value) {
            addCriterion("SUBMIT_ROLE not like", value, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleIn(List<String> values) {
            addCriterion("SUBMIT_ROLE in", values, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleNotIn(List<String> values) {
            addCriterion("SUBMIT_ROLE not in", values, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleBetween(String value1, String value2) {
            addCriterion("SUBMIT_ROLE between", value1, value2, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleNotBetween(String value1, String value2) {
            addCriterion("SUBMIT_ROLE not between", value1, value2, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubRoleNameIsNull() {
            addCriterion("SUB_ROLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSubRoleNameIsNotNull() {
            addCriterion("SUB_ROLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSubRoleNameEqualTo(String value) {
            addCriterion("SUB_ROLE_NAME =", value, "subRoleName");
            return (Criteria) this;
        }

        public Criteria andSubRoleNameNotEqualTo(String value) {
            addCriterion("SUB_ROLE_NAME <>", value, "subRoleName");
            return (Criteria) this;
        }

        public Criteria andSubRoleNameGreaterThan(String value) {
            addCriterion("SUB_ROLE_NAME >", value, "subRoleName");
            return (Criteria) this;
        }

        public Criteria andSubRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("SUB_ROLE_NAME >=", value, "subRoleName");
            return (Criteria) this;
        }

        public Criteria andSubRoleNameLessThan(String value) {
            addCriterion("SUB_ROLE_NAME <", value, "subRoleName");
            return (Criteria) this;
        }

        public Criteria andSubRoleNameLessThanOrEqualTo(String value) {
            addCriterion("SUB_ROLE_NAME <=", value, "subRoleName");
            return (Criteria) this;
        }

        public Criteria andSubRoleNameLike(String value) {
            addCriterion("SUB_ROLE_NAME like", value, "subRoleName");
            return (Criteria) this;
        }

        public Criteria andSubRoleNameNotLike(String value) {
            addCriterion("SUB_ROLE_NAME not like", value, "subRoleName");
            return (Criteria) this;
        }

        public Criteria andSubRoleNameIn(List<String> values) {
            addCriterion("SUB_ROLE_NAME in", values, "subRoleName");
            return (Criteria) this;
        }

        public Criteria andSubRoleNameNotIn(List<String> values) {
            addCriterion("SUB_ROLE_NAME not in", values, "subRoleName");
            return (Criteria) this;
        }

        public Criteria andSubRoleNameBetween(String value1, String value2) {
            addCriterion("SUB_ROLE_NAME between", value1, value2, "subRoleName");
            return (Criteria) this;
        }

        public Criteria andSubRoleNameNotBetween(String value1, String value2) {
            addCriterion("SUB_ROLE_NAME not between", value1, value2, "subRoleName");
            return (Criteria) this;
        }

        public Criteria andAuditRoleIsNull() {
            addCriterion("AUDIT_ROLE is null");
            return (Criteria) this;
        }

        public Criteria andAuditRoleIsNotNull() {
            addCriterion("AUDIT_ROLE is not null");
            return (Criteria) this;
        }

        public Criteria andAuditRoleEqualTo(String value) {
            addCriterion("AUDIT_ROLE =", value, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNotEqualTo(String value) {
            addCriterion("AUDIT_ROLE <>", value, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleGreaterThan(String value) {
            addCriterion("AUDIT_ROLE >", value, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_ROLE >=", value, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleLessThan(String value) {
            addCriterion("AUDIT_ROLE <", value, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_ROLE <=", value, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleLike(String value) {
            addCriterion("AUDIT_ROLE like", value, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNotLike(String value) {
            addCriterion("AUDIT_ROLE not like", value, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleIn(List<String> values) {
            addCriterion("AUDIT_ROLE in", values, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNotIn(List<String> values) {
            addCriterion("AUDIT_ROLE not in", values, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleBetween(String value1, String value2) {
            addCriterion("AUDIT_ROLE between", value1, value2, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNotBetween(String value1, String value2) {
            addCriterion("AUDIT_ROLE not between", value1, value2, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNameIsNull() {
            addCriterion("AUDIT_ROLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNameIsNotNull() {
            addCriterion("AUDIT_ROLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNameEqualTo(String value) {
            addCriterion("AUDIT_ROLE_NAME =", value, "auditRoleName");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNameNotEqualTo(String value) {
            addCriterion("AUDIT_ROLE_NAME <>", value, "auditRoleName");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNameGreaterThan(String value) {
            addCriterion("AUDIT_ROLE_NAME >", value, "auditRoleName");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_ROLE_NAME >=", value, "auditRoleName");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNameLessThan(String value) {
            addCriterion("AUDIT_ROLE_NAME <", value, "auditRoleName");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNameLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_ROLE_NAME <=", value, "auditRoleName");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNameLike(String value) {
            addCriterion("AUDIT_ROLE_NAME like", value, "auditRoleName");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNameNotLike(String value) {
            addCriterion("AUDIT_ROLE_NAME not like", value, "auditRoleName");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNameIn(List<String> values) {
            addCriterion("AUDIT_ROLE_NAME in", values, "auditRoleName");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNameNotIn(List<String> values) {
            addCriterion("AUDIT_ROLE_NAME not in", values, "auditRoleName");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNameBetween(String value1, String value2) {
            addCriterion("AUDIT_ROLE_NAME between", value1, value2, "auditRoleName");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNameNotBetween(String value1, String value2) {
            addCriterion("AUDIT_ROLE_NAME not between", value1, value2, "auditRoleName");
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