package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class SysDeptMonthExamInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysDeptMonthExamInfoExample() {
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

        public Criteria andExamFlowIsNull() {
            addCriterion("EXAM_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andExamFlowIsNotNull() {
            addCriterion("EXAM_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andExamFlowEqualTo(String value) {
            addCriterion("EXAM_FLOW =", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotEqualTo(String value) {
            addCriterion("EXAM_FLOW <>", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowGreaterThan(String value) {
            addCriterion("EXAM_FLOW >", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_FLOW >=", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowLessThan(String value) {
            addCriterion("EXAM_FLOW <", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowLessThanOrEqualTo(String value) {
            addCriterion("EXAM_FLOW <=", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowLike(String value) {
            addCriterion("EXAM_FLOW like", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotLike(String value) {
            addCriterion("EXAM_FLOW not like", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowIn(List<String> values) {
            addCriterion("EXAM_FLOW in", values, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotIn(List<String> values) {
            addCriterion("EXAM_FLOW not in", values, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowBetween(String value1, String value2) {
            addCriterion("EXAM_FLOW between", value1, value2, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotBetween(String value1, String value2) {
            addCriterion("EXAM_FLOW not between", value1, value2, "examFlow");
            return (Criteria) this;
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

        public Criteria andUserTypeIdIsNull() {
            addCriterion("USER_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIdIsNotNull() {
            addCriterion("USER_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIdEqualTo(String value) {
            addCriterion("USER_TYPE_ID =", value, "userTypeId");
            return (Criteria) this;
        }

        public Criteria andUserTypeIdNotEqualTo(String value) {
            addCriterion("USER_TYPE_ID <>", value, "userTypeId");
            return (Criteria) this;
        }

        public Criteria andUserTypeIdGreaterThan(String value) {
            addCriterion("USER_TYPE_ID >", value, "userTypeId");
            return (Criteria) this;
        }

        public Criteria andUserTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_TYPE_ID >=", value, "userTypeId");
            return (Criteria) this;
        }

        public Criteria andUserTypeIdLessThan(String value) {
            addCriterion("USER_TYPE_ID <", value, "userTypeId");
            return (Criteria) this;
        }

        public Criteria andUserTypeIdLessThanOrEqualTo(String value) {
            addCriterion("USER_TYPE_ID <=", value, "userTypeId");
            return (Criteria) this;
        }

        public Criteria andUserTypeIdLike(String value) {
            addCriterion("USER_TYPE_ID like", value, "userTypeId");
            return (Criteria) this;
        }

        public Criteria andUserTypeIdNotLike(String value) {
            addCriterion("USER_TYPE_ID not like", value, "userTypeId");
            return (Criteria) this;
        }

        public Criteria andUserTypeIdIn(List<String> values) {
            addCriterion("USER_TYPE_ID in", values, "userTypeId");
            return (Criteria) this;
        }

        public Criteria andUserTypeIdNotIn(List<String> values) {
            addCriterion("USER_TYPE_ID not in", values, "userTypeId");
            return (Criteria) this;
        }

        public Criteria andUserTypeIdBetween(String value1, String value2) {
            addCriterion("USER_TYPE_ID between", value1, value2, "userTypeId");
            return (Criteria) this;
        }

        public Criteria andUserTypeIdNotBetween(String value1, String value2) {
            addCriterion("USER_TYPE_ID not between", value1, value2, "userTypeId");
            return (Criteria) this;
        }

        public Criteria andUserTypeNameIsNull() {
            addCriterion("USER_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeNameIsNotNull() {
            addCriterion("USER_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeNameEqualTo(String value) {
            addCriterion("USER_TYPE_NAME =", value, "userTypeName");
            return (Criteria) this;
        }

        public Criteria andUserTypeNameNotEqualTo(String value) {
            addCriterion("USER_TYPE_NAME <>", value, "userTypeName");
            return (Criteria) this;
        }

        public Criteria andUserTypeNameGreaterThan(String value) {
            addCriterion("USER_TYPE_NAME >", value, "userTypeName");
            return (Criteria) this;
        }

        public Criteria andUserTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_TYPE_NAME >=", value, "userTypeName");
            return (Criteria) this;
        }

        public Criteria andUserTypeNameLessThan(String value) {
            addCriterion("USER_TYPE_NAME <", value, "userTypeName");
            return (Criteria) this;
        }

        public Criteria andUserTypeNameLessThanOrEqualTo(String value) {
            addCriterion("USER_TYPE_NAME <=", value, "userTypeName");
            return (Criteria) this;
        }

        public Criteria andUserTypeNameLike(String value) {
            addCriterion("USER_TYPE_NAME like", value, "userTypeName");
            return (Criteria) this;
        }

        public Criteria andUserTypeNameNotLike(String value) {
            addCriterion("USER_TYPE_NAME not like", value, "userTypeName");
            return (Criteria) this;
        }

        public Criteria andUserTypeNameIn(List<String> values) {
            addCriterion("USER_TYPE_NAME in", values, "userTypeName");
            return (Criteria) this;
        }

        public Criteria andUserTypeNameNotIn(List<String> values) {
            addCriterion("USER_TYPE_NAME not in", values, "userTypeName");
            return (Criteria) this;
        }

        public Criteria andUserTypeNameBetween(String value1, String value2) {
            addCriterion("USER_TYPE_NAME between", value1, value2, "userTypeName");
            return (Criteria) this;
        }

        public Criteria andUserTypeNameNotBetween(String value1, String value2) {
            addCriterion("USER_TYPE_NAME not between", value1, value2, "userTypeName");
            return (Criteria) this;
        }

        public Criteria andExamUserFlowIsNull() {
            addCriterion("EXAM_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andExamUserFlowIsNotNull() {
            addCriterion("EXAM_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andExamUserFlowEqualTo(String value) {
            addCriterion("EXAM_USER_FLOW =", value, "examUserFlow");
            return (Criteria) this;
        }

        public Criteria andExamUserFlowNotEqualTo(String value) {
            addCriterion("EXAM_USER_FLOW <>", value, "examUserFlow");
            return (Criteria) this;
        }

        public Criteria andExamUserFlowGreaterThan(String value) {
            addCriterion("EXAM_USER_FLOW >", value, "examUserFlow");
            return (Criteria) this;
        }

        public Criteria andExamUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_USER_FLOW >=", value, "examUserFlow");
            return (Criteria) this;
        }

        public Criteria andExamUserFlowLessThan(String value) {
            addCriterion("EXAM_USER_FLOW <", value, "examUserFlow");
            return (Criteria) this;
        }

        public Criteria andExamUserFlowLessThanOrEqualTo(String value) {
            addCriterion("EXAM_USER_FLOW <=", value, "examUserFlow");
            return (Criteria) this;
        }

        public Criteria andExamUserFlowLike(String value) {
            addCriterion("EXAM_USER_FLOW like", value, "examUserFlow");
            return (Criteria) this;
        }

        public Criteria andExamUserFlowNotLike(String value) {
            addCriterion("EXAM_USER_FLOW not like", value, "examUserFlow");
            return (Criteria) this;
        }

        public Criteria andExamUserFlowIn(List<String> values) {
            addCriterion("EXAM_USER_FLOW in", values, "examUserFlow");
            return (Criteria) this;
        }

        public Criteria andExamUserFlowNotIn(List<String> values) {
            addCriterion("EXAM_USER_FLOW not in", values, "examUserFlow");
            return (Criteria) this;
        }

        public Criteria andExamUserFlowBetween(String value1, String value2) {
            addCriterion("EXAM_USER_FLOW between", value1, value2, "examUserFlow");
            return (Criteria) this;
        }

        public Criteria andExamUserFlowNotBetween(String value1, String value2) {
            addCriterion("EXAM_USER_FLOW not between", value1, value2, "examUserFlow");
            return (Criteria) this;
        }

        public Criteria andExamUserNameIsNull() {
            addCriterion("EXAM_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andExamUserNameIsNotNull() {
            addCriterion("EXAM_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andExamUserNameEqualTo(String value) {
            addCriterion("EXAM_USER_NAME =", value, "examUserName");
            return (Criteria) this;
        }

        public Criteria andExamUserNameNotEqualTo(String value) {
            addCriterion("EXAM_USER_NAME <>", value, "examUserName");
            return (Criteria) this;
        }

        public Criteria andExamUserNameGreaterThan(String value) {
            addCriterion("EXAM_USER_NAME >", value, "examUserName");
            return (Criteria) this;
        }

        public Criteria andExamUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_USER_NAME >=", value, "examUserName");
            return (Criteria) this;
        }

        public Criteria andExamUserNameLessThan(String value) {
            addCriterion("EXAM_USER_NAME <", value, "examUserName");
            return (Criteria) this;
        }

        public Criteria andExamUserNameLessThanOrEqualTo(String value) {
            addCriterion("EXAM_USER_NAME <=", value, "examUserName");
            return (Criteria) this;
        }

        public Criteria andExamUserNameLike(String value) {
            addCriterion("EXAM_USER_NAME like", value, "examUserName");
            return (Criteria) this;
        }

        public Criteria andExamUserNameNotLike(String value) {
            addCriterion("EXAM_USER_NAME not like", value, "examUserName");
            return (Criteria) this;
        }

        public Criteria andExamUserNameIn(List<String> values) {
            addCriterion("EXAM_USER_NAME in", values, "examUserName");
            return (Criteria) this;
        }

        public Criteria andExamUserNameNotIn(List<String> values) {
            addCriterion("EXAM_USER_NAME not in", values, "examUserName");
            return (Criteria) this;
        }

        public Criteria andExamUserNameBetween(String value1, String value2) {
            addCriterion("EXAM_USER_NAME between", value1, value2, "examUserName");
            return (Criteria) this;
        }

        public Criteria andExamUserNameNotBetween(String value1, String value2) {
            addCriterion("EXAM_USER_NAME not between", value1, value2, "examUserName");
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