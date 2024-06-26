package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class TeachingActivityTargetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TeachingActivityTargetExample() {
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

        public Criteria andTargetFlowIsNull() {
            addCriterion("TARGET_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTargetFlowIsNotNull() {
            addCriterion("TARGET_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTargetFlowEqualTo(String value) {
            addCriterion("TARGET_FLOW =", value, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowNotEqualTo(String value) {
            addCriterion("TARGET_FLOW <>", value, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowGreaterThan(String value) {
            addCriterion("TARGET_FLOW >", value, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TARGET_FLOW >=", value, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowLessThan(String value) {
            addCriterion("TARGET_FLOW <", value, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowLessThanOrEqualTo(String value) {
            addCriterion("TARGET_FLOW <=", value, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowLike(String value) {
            addCriterion("TARGET_FLOW like", value, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowNotLike(String value) {
            addCriterion("TARGET_FLOW not like", value, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowIn(List<String> values) {
            addCriterion("TARGET_FLOW in", values, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowNotIn(List<String> values) {
            addCriterion("TARGET_FLOW not in", values, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowBetween(String value1, String value2) {
            addCriterion("TARGET_FLOW between", value1, value2, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowNotBetween(String value1, String value2) {
            addCriterion("TARGET_FLOW not between", value1, value2, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetNameIsNull() {
            addCriterion("TARGET_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTargetNameIsNotNull() {
            addCriterion("TARGET_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTargetNameEqualTo(String value) {
            addCriterion("TARGET_NAME =", value, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameNotEqualTo(String value) {
            addCriterion("TARGET_NAME <>", value, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameGreaterThan(String value) {
            addCriterion("TARGET_NAME >", value, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameGreaterThanOrEqualTo(String value) {
            addCriterion("TARGET_NAME >=", value, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameLessThan(String value) {
            addCriterion("TARGET_NAME <", value, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameLessThanOrEqualTo(String value) {
            addCriterion("TARGET_NAME <=", value, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameLike(String value) {
            addCriterion("TARGET_NAME like", value, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameNotLike(String value) {
            addCriterion("TARGET_NAME not like", value, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameIn(List<String> values) {
            addCriterion("TARGET_NAME in", values, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameNotIn(List<String> values) {
            addCriterion("TARGET_NAME not in", values, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameBetween(String value1, String value2) {
            addCriterion("TARGET_NAME between", value1, value2, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameNotBetween(String value1, String value2) {
            addCriterion("TARGET_NAME not between", value1, value2, "targetName");
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

        public Criteria andOrdinalIsNull() {
            addCriterion("ORDINAL is null");
            return (Criteria) this;
        }

        public Criteria andOrdinalIsNotNull() {
            addCriterion("ORDINAL is not null");
            return (Criteria) this;
        }

        public Criteria andOrdinalEqualTo(Integer value) {
            addCriterion("ORDINAL =", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotEqualTo(Integer value) {
            addCriterion("ORDINAL <>", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThan(Integer value) {
            addCriterion("ORDINAL >", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL >=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThan(Integer value) {
            addCriterion("ORDINAL <", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL <=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalIn(List<Integer> values) {
            addCriterion("ORDINAL in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotIn(List<Integer> values) {
            addCriterion("ORDINAL not in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL between", value1, value2, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL not between", value1, value2, "ordinal");
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

        public Criteria andTargetTypeIsNull() {
            addCriterion("TARGET_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTargetTypeIsNotNull() {
            addCriterion("TARGET_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTargetTypeEqualTo(String value) {
            addCriterion("TARGET_TYPE =", value, "targetType");
            return (Criteria) this;
        }

        public Criteria andTargetTypeNotEqualTo(String value) {
            addCriterion("TARGET_TYPE <>", value, "targetType");
            return (Criteria) this;
        }

        public Criteria andTargetTypeGreaterThan(String value) {
            addCriterion("TARGET_TYPE >", value, "targetType");
            return (Criteria) this;
        }

        public Criteria andTargetTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TARGET_TYPE >=", value, "targetType");
            return (Criteria) this;
        }

        public Criteria andTargetTypeLessThan(String value) {
            addCriterion("TARGET_TYPE <", value, "targetType");
            return (Criteria) this;
        }

        public Criteria andTargetTypeLessThanOrEqualTo(String value) {
            addCriterion("TARGET_TYPE <=", value, "targetType");
            return (Criteria) this;
        }

        public Criteria andTargetTypeLike(String value) {
            addCriterion("TARGET_TYPE like", value, "targetType");
            return (Criteria) this;
        }

        public Criteria andTargetTypeNotLike(String value) {
            addCriterion("TARGET_TYPE not like", value, "targetType");
            return (Criteria) this;
        }

        public Criteria andTargetTypeIn(List<String> values) {
            addCriterion("TARGET_TYPE in", values, "targetType");
            return (Criteria) this;
        }

        public Criteria andTargetTypeNotIn(List<String> values) {
            addCriterion("TARGET_TYPE not in", values, "targetType");
            return (Criteria) this;
        }

        public Criteria andTargetTypeBetween(String value1, String value2) {
            addCriterion("TARGET_TYPE between", value1, value2, "targetType");
            return (Criteria) this;
        }

        public Criteria andTargetTypeNotBetween(String value1, String value2) {
            addCriterion("TARGET_TYPE not between", value1, value2, "targetType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdIsNull() {
            addCriterion("ACTIVITY_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdIsNotNull() {
            addCriterion("ACTIVITY_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdEqualTo(String value) {
            addCriterion("ACTIVITY_TYPE_ID =", value, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdNotEqualTo(String value) {
            addCriterion("ACTIVITY_TYPE_ID <>", value, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdGreaterThan(String value) {
            addCriterion("ACTIVITY_TYPE_ID >", value, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_TYPE_ID >=", value, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdLessThan(String value) {
            addCriterion("ACTIVITY_TYPE_ID <", value, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_TYPE_ID <=", value, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdLike(String value) {
            addCriterion("ACTIVITY_TYPE_ID like", value, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdNotLike(String value) {
            addCriterion("ACTIVITY_TYPE_ID not like", value, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdIn(List<String> values) {
            addCriterion("ACTIVITY_TYPE_ID in", values, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdNotIn(List<String> values) {
            addCriterion("ACTIVITY_TYPE_ID not in", values, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdBetween(String value1, String value2) {
            addCriterion("ACTIVITY_TYPE_ID between", value1, value2, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdNotBetween(String value1, String value2) {
            addCriterion("ACTIVITY_TYPE_ID not between", value1, value2, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameIsNull() {
            addCriterion("ACTIVITY_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameIsNotNull() {
            addCriterion("ACTIVITY_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameEqualTo(String value) {
            addCriterion("ACTIVITY_TYPE_NAME =", value, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameNotEqualTo(String value) {
            addCriterion("ACTIVITY_TYPE_NAME <>", value, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameGreaterThan(String value) {
            addCriterion("ACTIVITY_TYPE_NAME >", value, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_TYPE_NAME >=", value, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameLessThan(String value) {
            addCriterion("ACTIVITY_TYPE_NAME <", value, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameLessThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_TYPE_NAME <=", value, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameLike(String value) {
            addCriterion("ACTIVITY_TYPE_NAME like", value, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameNotLike(String value) {
            addCriterion("ACTIVITY_TYPE_NAME not like", value, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameIn(List<String> values) {
            addCriterion("ACTIVITY_TYPE_NAME in", values, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameNotIn(List<String> values) {
            addCriterion("ACTIVITY_TYPE_NAME not in", values, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameBetween(String value1, String value2) {
            addCriterion("ACTIVITY_TYPE_NAME between", value1, value2, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameNotBetween(String value1, String value2) {
            addCriterion("ACTIVITY_TYPE_NAME not between", value1, value2, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andIsTextIsNull() {
            addCriterion("IS_TEXT is null");
            return (Criteria) this;
        }

        public Criteria andIsTextIsNotNull() {
            addCriterion("IS_TEXT is not null");
            return (Criteria) this;
        }

        public Criteria andIsTextEqualTo(String value) {
            addCriterion("IS_TEXT =", value, "isText");
            return (Criteria) this;
        }

        public Criteria andIsTextNotEqualTo(String value) {
            addCriterion("IS_TEXT <>", value, "isText");
            return (Criteria) this;
        }

        public Criteria andIsTextGreaterThan(String value) {
            addCriterion("IS_TEXT >", value, "isText");
            return (Criteria) this;
        }

        public Criteria andIsTextGreaterThanOrEqualTo(String value) {
            addCriterion("IS_TEXT >=", value, "isText");
            return (Criteria) this;
        }

        public Criteria andIsTextLessThan(String value) {
            addCriterion("IS_TEXT <", value, "isText");
            return (Criteria) this;
        }

        public Criteria andIsTextLessThanOrEqualTo(String value) {
            addCriterion("IS_TEXT <=", value, "isText");
            return (Criteria) this;
        }

        public Criteria andIsTextLike(String value) {
            addCriterion("IS_TEXT like", value, "isText");
            return (Criteria) this;
        }

        public Criteria andIsTextNotLike(String value) {
            addCriterion("IS_TEXT not like", value, "isText");
            return (Criteria) this;
        }

        public Criteria andIsTextIn(List<String> values) {
            addCriterion("IS_TEXT in", values, "isText");
            return (Criteria) this;
        }

        public Criteria andIsTextNotIn(List<String> values) {
            addCriterion("IS_TEXT not in", values, "isText");
            return (Criteria) this;
        }

        public Criteria andIsTextBetween(String value1, String value2) {
            addCriterion("IS_TEXT between", value1, value2, "isText");
            return (Criteria) this;
        }

        public Criteria andIsTextNotBetween(String value1, String value2) {
            addCriterion("IS_TEXT not between", value1, value2, "isText");
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