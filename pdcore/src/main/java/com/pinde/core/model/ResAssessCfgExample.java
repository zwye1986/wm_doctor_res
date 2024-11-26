package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResAssessCfgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResAssessCfgExample() {
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

        public Criteria andCfgCodeIdIsNull() {
            addCriterion("CFG_CODE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCfgCodeIdIsNotNull() {
            addCriterion("CFG_CODE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCfgCodeIdEqualTo(String value) {
            addCriterion("CFG_CODE_ID =", value, "cfgCodeId");
            return (Criteria) this;
        }

        public Criteria andCfgCodeIdNotEqualTo(String value) {
            addCriterion("CFG_CODE_ID <>", value, "cfgCodeId");
            return (Criteria) this;
        }

        public Criteria andCfgCodeIdGreaterThan(String value) {
            addCriterion("CFG_CODE_ID >", value, "cfgCodeId");
            return (Criteria) this;
        }

        public Criteria andCfgCodeIdGreaterThanOrEqualTo(String value) {
            addCriterion("CFG_CODE_ID >=", value, "cfgCodeId");
            return (Criteria) this;
        }

        public Criteria andCfgCodeIdLessThan(String value) {
            addCriterion("CFG_CODE_ID <", value, "cfgCodeId");
            return (Criteria) this;
        }

        public Criteria andCfgCodeIdLessThanOrEqualTo(String value) {
            addCriterion("CFG_CODE_ID <=", value, "cfgCodeId");
            return (Criteria) this;
        }

        public Criteria andCfgCodeIdLike(String value) {
            addCriterion("CFG_CODE_ID like", value, "cfgCodeId");
            return (Criteria) this;
        }

        public Criteria andCfgCodeIdNotLike(String value) {
            addCriterion("CFG_CODE_ID not like", value, "cfgCodeId");
            return (Criteria) this;
        }

        public Criteria andCfgCodeIdIn(List<String> values) {
            addCriterion("CFG_CODE_ID in", values, "cfgCodeId");
            return (Criteria) this;
        }

        public Criteria andCfgCodeIdNotIn(List<String> values) {
            addCriterion("CFG_CODE_ID not in", values, "cfgCodeId");
            return (Criteria) this;
        }

        public Criteria andCfgCodeIdBetween(String value1, String value2) {
            addCriterion("CFG_CODE_ID between", value1, value2, "cfgCodeId");
            return (Criteria) this;
        }

        public Criteria andCfgCodeIdNotBetween(String value1, String value2) {
            addCriterion("CFG_CODE_ID not between", value1, value2, "cfgCodeId");
            return (Criteria) this;
        }

        public Criteria andCfgCodeNameIsNull() {
            addCriterion("CFG_CODE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCfgCodeNameIsNotNull() {
            addCriterion("CFG_CODE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCfgCodeNameEqualTo(String value) {
            addCriterion("CFG_CODE_NAME =", value, "cfgCodeName");
            return (Criteria) this;
        }

        public Criteria andCfgCodeNameNotEqualTo(String value) {
            addCriterion("CFG_CODE_NAME <>", value, "cfgCodeName");
            return (Criteria) this;
        }

        public Criteria andCfgCodeNameGreaterThan(String value) {
            addCriterion("CFG_CODE_NAME >", value, "cfgCodeName");
            return (Criteria) this;
        }

        public Criteria andCfgCodeNameGreaterThanOrEqualTo(String value) {
            addCriterion("CFG_CODE_NAME >=", value, "cfgCodeName");
            return (Criteria) this;
        }

        public Criteria andCfgCodeNameLessThan(String value) {
            addCriterion("CFG_CODE_NAME <", value, "cfgCodeName");
            return (Criteria) this;
        }

        public Criteria andCfgCodeNameLessThanOrEqualTo(String value) {
            addCriterion("CFG_CODE_NAME <=", value, "cfgCodeName");
            return (Criteria) this;
        }

        public Criteria andCfgCodeNameLike(String value) {
            addCriterion("CFG_CODE_NAME like", value, "cfgCodeName");
            return (Criteria) this;
        }

        public Criteria andCfgCodeNameNotLike(String value) {
            addCriterion("CFG_CODE_NAME not like", value, "cfgCodeName");
            return (Criteria) this;
        }

        public Criteria andCfgCodeNameIn(List<String> values) {
            addCriterion("CFG_CODE_NAME in", values, "cfgCodeName");
            return (Criteria) this;
        }

        public Criteria andCfgCodeNameNotIn(List<String> values) {
            addCriterion("CFG_CODE_NAME not in", values, "cfgCodeName");
            return (Criteria) this;
        }

        public Criteria andCfgCodeNameBetween(String value1, String value2) {
            addCriterion("CFG_CODE_NAME between", value1, value2, "cfgCodeName");
            return (Criteria) this;
        }

        public Criteria andCfgCodeNameNotBetween(String value1, String value2) {
            addCriterion("CFG_CODE_NAME not between", value1, value2, "cfgCodeName");
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

        public Criteria andAssessTypeIdIsNull() {
            addCriterion("ASSESS_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdIsNotNull() {
            addCriterion("ASSESS_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdEqualTo(String value) {
            addCriterion("ASSESS_TYPE_ID =", value, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdNotEqualTo(String value) {
            addCriterion("ASSESS_TYPE_ID <>", value, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdGreaterThan(String value) {
            addCriterion("ASSESS_TYPE_ID >", value, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ASSESS_TYPE_ID >=", value, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdLessThan(String value) {
            addCriterion("ASSESS_TYPE_ID <", value, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ASSESS_TYPE_ID <=", value, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdLike(String value) {
            addCriterion("ASSESS_TYPE_ID like", value, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdNotLike(String value) {
            addCriterion("ASSESS_TYPE_ID not like", value, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdIn(List<String> values) {
            addCriterion("ASSESS_TYPE_ID in", values, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdNotIn(List<String> values) {
            addCriterion("ASSESS_TYPE_ID not in", values, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdBetween(String value1, String value2) {
            addCriterion("ASSESS_TYPE_ID between", value1, value2, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdNotBetween(String value1, String value2) {
            addCriterion("ASSESS_TYPE_ID not between", value1, value2, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameIsNull() {
            addCriterion("ASSESS_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameIsNotNull() {
            addCriterion("ASSESS_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameEqualTo(String value) {
            addCriterion("ASSESS_TYPE_NAME =", value, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameNotEqualTo(String value) {
            addCriterion("ASSESS_TYPE_NAME <>", value, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameGreaterThan(String value) {
            addCriterion("ASSESS_TYPE_NAME >", value, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("ASSESS_TYPE_NAME >=", value, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameLessThan(String value) {
            addCriterion("ASSESS_TYPE_NAME <", value, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameLessThanOrEqualTo(String value) {
            addCriterion("ASSESS_TYPE_NAME <=", value, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameLike(String value) {
            addCriterion("ASSESS_TYPE_NAME like", value, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameNotLike(String value) {
            addCriterion("ASSESS_TYPE_NAME not like", value, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameIn(List<String> values) {
            addCriterion("ASSESS_TYPE_NAME in", values, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameNotIn(List<String> values) {
            addCriterion("ASSESS_TYPE_NAME not in", values, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameBetween(String value1, String value2) {
            addCriterion("ASSESS_TYPE_NAME between", value1, value2, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameNotBetween(String value1, String value2) {
            addCriterion("ASSESS_TYPE_NAME not between", value1, value2, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andFormNameIsNull() {
            addCriterion("FORM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFormNameIsNotNull() {
            addCriterion("FORM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFormNameEqualTo(String value) {
            addCriterion("FORM_NAME =", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameNotEqualTo(String value) {
            addCriterion("FORM_NAME <>", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameGreaterThan(String value) {
            addCriterion("FORM_NAME >", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameGreaterThanOrEqualTo(String value) {
            addCriterion("FORM_NAME >=", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameLessThan(String value) {
            addCriterion("FORM_NAME <", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameLessThanOrEqualTo(String value) {
            addCriterion("FORM_NAME <=", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameLike(String value) {
            addCriterion("FORM_NAME like", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameNotLike(String value) {
            addCriterion("FORM_NAME not like", value, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameIn(List<String> values) {
            addCriterion("FORM_NAME in", values, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameNotIn(List<String> values) {
            addCriterion("FORM_NAME not in", values, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameBetween(String value1, String value2) {
            addCriterion("FORM_NAME between", value1, value2, "formName");
            return (Criteria) this;
        }

        public Criteria andFormNameNotBetween(String value1, String value2) {
            addCriterion("FORM_NAME not between", value1, value2, "formName");
            return (Criteria) this;
        }

        public Criteria andFormStatusIdIsNull() {
            addCriterion("FORM_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andFormStatusIdIsNotNull() {
            addCriterion("FORM_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFormStatusIdEqualTo(String value) {
            addCriterion("FORM_STATUS_ID =", value, "formStatusId");
            return (Criteria) this;
        }

        public Criteria andFormStatusIdNotEqualTo(String value) {
            addCriterion("FORM_STATUS_ID <>", value, "formStatusId");
            return (Criteria) this;
        }

        public Criteria andFormStatusIdGreaterThan(String value) {
            addCriterion("FORM_STATUS_ID >", value, "formStatusId");
            return (Criteria) this;
        }

        public Criteria andFormStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("FORM_STATUS_ID >=", value, "formStatusId");
            return (Criteria) this;
        }

        public Criteria andFormStatusIdLessThan(String value) {
            addCriterion("FORM_STATUS_ID <", value, "formStatusId");
            return (Criteria) this;
        }

        public Criteria andFormStatusIdLessThanOrEqualTo(String value) {
            addCriterion("FORM_STATUS_ID <=", value, "formStatusId");
            return (Criteria) this;
        }

        public Criteria andFormStatusIdLike(String value) {
            addCriterion("FORM_STATUS_ID like", value, "formStatusId");
            return (Criteria) this;
        }

        public Criteria andFormStatusIdNotLike(String value) {
            addCriterion("FORM_STATUS_ID not like", value, "formStatusId");
            return (Criteria) this;
        }

        public Criteria andFormStatusIdIn(List<String> values) {
            addCriterion("FORM_STATUS_ID in", values, "formStatusId");
            return (Criteria) this;
        }

        public Criteria andFormStatusIdNotIn(List<String> values) {
            addCriterion("FORM_STATUS_ID not in", values, "formStatusId");
            return (Criteria) this;
        }

        public Criteria andFormStatusIdBetween(String value1, String value2) {
            addCriterion("FORM_STATUS_ID between", value1, value2, "formStatusId");
            return (Criteria) this;
        }

        public Criteria andFormStatusIdNotBetween(String value1, String value2) {
            addCriterion("FORM_STATUS_ID not between", value1, value2, "formStatusId");
            return (Criteria) this;
        }

        public Criteria andFormStatusNameIsNull() {
            addCriterion("FORM_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFormStatusNameIsNotNull() {
            addCriterion("FORM_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFormStatusNameEqualTo(String value) {
            addCriterion("FORM_STATUS_NAME =", value, "formStatusName");
            return (Criteria) this;
        }

        public Criteria andFormStatusNameNotEqualTo(String value) {
            addCriterion("FORM_STATUS_NAME <>", value, "formStatusName");
            return (Criteria) this;
        }

        public Criteria andFormStatusNameGreaterThan(String value) {
            addCriterion("FORM_STATUS_NAME >", value, "formStatusName");
            return (Criteria) this;
        }

        public Criteria andFormStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("FORM_STATUS_NAME >=", value, "formStatusName");
            return (Criteria) this;
        }

        public Criteria andFormStatusNameLessThan(String value) {
            addCriterion("FORM_STATUS_NAME <", value, "formStatusName");
            return (Criteria) this;
        }

        public Criteria andFormStatusNameLessThanOrEqualTo(String value) {
            addCriterion("FORM_STATUS_NAME <=", value, "formStatusName");
            return (Criteria) this;
        }

        public Criteria andFormStatusNameLike(String value) {
            addCriterion("FORM_STATUS_NAME like", value, "formStatusName");
            return (Criteria) this;
        }

        public Criteria andFormStatusNameNotLike(String value) {
            addCriterion("FORM_STATUS_NAME not like", value, "formStatusName");
            return (Criteria) this;
        }

        public Criteria andFormStatusNameIn(List<String> values) {
            addCriterion("FORM_STATUS_NAME in", values, "formStatusName");
            return (Criteria) this;
        }

        public Criteria andFormStatusNameNotIn(List<String> values) {
            addCriterion("FORM_STATUS_NAME not in", values, "formStatusName");
            return (Criteria) this;
        }

        public Criteria andFormStatusNameBetween(String value1, String value2) {
            addCriterion("FORM_STATUS_NAME between", value1, value2, "formStatusName");
            return (Criteria) this;
        }

        public Criteria andFormStatusNameNotBetween(String value1, String value2) {
            addCriterion("FORM_STATUS_NAME not between", value1, value2, "formStatusName");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNull() {
            addCriterion("TOTAL_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNotNull() {
            addCriterion("TOTAL_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreEqualTo(String value) {
            addCriterion("TOTAL_SCORE =", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotEqualTo(String value) {
            addCriterion("TOTAL_SCORE <>", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThan(String value) {
            addCriterion("TOTAL_SCORE >", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThanOrEqualTo(String value) {
            addCriterion("TOTAL_SCORE >=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThan(String value) {
            addCriterion("TOTAL_SCORE <", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThanOrEqualTo(String value) {
            addCriterion("TOTAL_SCORE <=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLike(String value) {
            addCriterion("TOTAL_SCORE like", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotLike(String value) {
            addCriterion("TOTAL_SCORE not like", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIn(List<String> values) {
            addCriterion("TOTAL_SCORE in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotIn(List<String> values) {
            addCriterion("TOTAL_SCORE not in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreBetween(String value1, String value2) {
            addCriterion("TOTAL_SCORE between", value1, value2, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotBetween(String value1, String value2) {
            addCriterion("TOTAL_SCORE not between", value1, value2, "totalScore");
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