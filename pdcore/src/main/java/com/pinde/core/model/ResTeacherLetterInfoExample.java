package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResTeacherLetterInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResTeacherLetterInfoExample() {
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

        public Criteria andLetterFlowIsNull() {
            addCriterion("LETTER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andLetterFlowIsNotNull() {
            addCriterion("LETTER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andLetterFlowEqualTo(String value) {
            addCriterion("LETTER_FLOW =", value, "letterFlow");
            return (Criteria) this;
        }

        public Criteria andLetterFlowNotEqualTo(String value) {
            addCriterion("LETTER_FLOW <>", value, "letterFlow");
            return (Criteria) this;
        }

        public Criteria andLetterFlowGreaterThan(String value) {
            addCriterion("LETTER_FLOW >", value, "letterFlow");
            return (Criteria) this;
        }

        public Criteria andLetterFlowGreaterThanOrEqualTo(String value) {
            addCriterion("LETTER_FLOW >=", value, "letterFlow");
            return (Criteria) this;
        }

        public Criteria andLetterFlowLessThan(String value) {
            addCriterion("LETTER_FLOW <", value, "letterFlow");
            return (Criteria) this;
        }

        public Criteria andLetterFlowLessThanOrEqualTo(String value) {
            addCriterion("LETTER_FLOW <=", value, "letterFlow");
            return (Criteria) this;
        }

        public Criteria andLetterFlowLike(String value) {
            addCriterion("LETTER_FLOW like", value, "letterFlow");
            return (Criteria) this;
        }

        public Criteria andLetterFlowNotLike(String value) {
            addCriterion("LETTER_FLOW not like", value, "letterFlow");
            return (Criteria) this;
        }

        public Criteria andLetterFlowIn(List<String> values) {
            addCriterion("LETTER_FLOW in", values, "letterFlow");
            return (Criteria) this;
        }

        public Criteria andLetterFlowNotIn(List<String> values) {
            addCriterion("LETTER_FLOW not in", values, "letterFlow");
            return (Criteria) this;
        }

        public Criteria andLetterFlowBetween(String value1, String value2) {
            addCriterion("LETTER_FLOW between", value1, value2, "letterFlow");
            return (Criteria) this;
        }

        public Criteria andLetterFlowNotBetween(String value1, String value2) {
            addCriterion("LETTER_FLOW not between", value1, value2, "letterFlow");
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

        public Criteria andLetterPeriodIsNull() {
            addCriterion("LETTER_PERIOD is null");
            return (Criteria) this;
        }

        public Criteria andLetterPeriodIsNotNull() {
            addCriterion("LETTER_PERIOD is not null");
            return (Criteria) this;
        }

        public Criteria andLetterPeriodEqualTo(String value) {
            addCriterion("LETTER_PERIOD =", value, "letterPeriod");
            return (Criteria) this;
        }

        public Criteria andLetterPeriodNotEqualTo(String value) {
            addCriterion("LETTER_PERIOD <>", value, "letterPeriod");
            return (Criteria) this;
        }

        public Criteria andLetterPeriodGreaterThan(String value) {
            addCriterion("LETTER_PERIOD >", value, "letterPeriod");
            return (Criteria) this;
        }

        public Criteria andLetterPeriodGreaterThanOrEqualTo(String value) {
            addCriterion("LETTER_PERIOD >=", value, "letterPeriod");
            return (Criteria) this;
        }

        public Criteria andLetterPeriodLessThan(String value) {
            addCriterion("LETTER_PERIOD <", value, "letterPeriod");
            return (Criteria) this;
        }

        public Criteria andLetterPeriodLessThanOrEqualTo(String value) {
            addCriterion("LETTER_PERIOD <=", value, "letterPeriod");
            return (Criteria) this;
        }

        public Criteria andLetterPeriodLike(String value) {
            addCriterion("LETTER_PERIOD like", value, "letterPeriod");
            return (Criteria) this;
        }

        public Criteria andLetterPeriodNotLike(String value) {
            addCriterion("LETTER_PERIOD not like", value, "letterPeriod");
            return (Criteria) this;
        }

        public Criteria andLetterPeriodIn(List<String> values) {
            addCriterion("LETTER_PERIOD in", values, "letterPeriod");
            return (Criteria) this;
        }

        public Criteria andLetterPeriodNotIn(List<String> values) {
            addCriterion("LETTER_PERIOD not in", values, "letterPeriod");
            return (Criteria) this;
        }

        public Criteria andLetterPeriodBetween(String value1, String value2) {
            addCriterion("LETTER_PERIOD between", value1, value2, "letterPeriod");
            return (Criteria) this;
        }

        public Criteria andLetterPeriodNotBetween(String value1, String value2) {
            addCriterion("LETTER_PERIOD not between", value1, value2, "letterPeriod");
            return (Criteria) this;
        }

        public Criteria andLetterStartTimeIsNull() {
            addCriterion("LETTER_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLetterStartTimeIsNotNull() {
            addCriterion("LETTER_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLetterStartTimeEqualTo(String value) {
            addCriterion("LETTER_START_TIME =", value, "letterStartTime");
            return (Criteria) this;
        }

        public Criteria andLetterStartTimeNotEqualTo(String value) {
            addCriterion("LETTER_START_TIME <>", value, "letterStartTime");
            return (Criteria) this;
        }

        public Criteria andLetterStartTimeGreaterThan(String value) {
            addCriterion("LETTER_START_TIME >", value, "letterStartTime");
            return (Criteria) this;
        }

        public Criteria andLetterStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LETTER_START_TIME >=", value, "letterStartTime");
            return (Criteria) this;
        }

        public Criteria andLetterStartTimeLessThan(String value) {
            addCriterion("LETTER_START_TIME <", value, "letterStartTime");
            return (Criteria) this;
        }

        public Criteria andLetterStartTimeLessThanOrEqualTo(String value) {
            addCriterion("LETTER_START_TIME <=", value, "letterStartTime");
            return (Criteria) this;
        }

        public Criteria andLetterStartTimeLike(String value) {
            addCriterion("LETTER_START_TIME like", value, "letterStartTime");
            return (Criteria) this;
        }

        public Criteria andLetterStartTimeNotLike(String value) {
            addCriterion("LETTER_START_TIME not like", value, "letterStartTime");
            return (Criteria) this;
        }

        public Criteria andLetterStartTimeIn(List<String> values) {
            addCriterion("LETTER_START_TIME in", values, "letterStartTime");
            return (Criteria) this;
        }

        public Criteria andLetterStartTimeNotIn(List<String> values) {
            addCriterion("LETTER_START_TIME not in", values, "letterStartTime");
            return (Criteria) this;
        }

        public Criteria andLetterStartTimeBetween(String value1, String value2) {
            addCriterion("LETTER_START_TIME between", value1, value2, "letterStartTime");
            return (Criteria) this;
        }

        public Criteria andLetterStartTimeNotBetween(String value1, String value2) {
            addCriterion("LETTER_START_TIME not between", value1, value2, "letterStartTime");
            return (Criteria) this;
        }

        public Criteria andLetterEndTimeIsNull() {
            addCriterion("LETTER_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLetterEndTimeIsNotNull() {
            addCriterion("LETTER_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLetterEndTimeEqualTo(String value) {
            addCriterion("LETTER_END_TIME =", value, "letterEndTime");
            return (Criteria) this;
        }

        public Criteria andLetterEndTimeNotEqualTo(String value) {
            addCriterion("LETTER_END_TIME <>", value, "letterEndTime");
            return (Criteria) this;
        }

        public Criteria andLetterEndTimeGreaterThan(String value) {
            addCriterion("LETTER_END_TIME >", value, "letterEndTime");
            return (Criteria) this;
        }

        public Criteria andLetterEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LETTER_END_TIME >=", value, "letterEndTime");
            return (Criteria) this;
        }

        public Criteria andLetterEndTimeLessThan(String value) {
            addCriterion("LETTER_END_TIME <", value, "letterEndTime");
            return (Criteria) this;
        }

        public Criteria andLetterEndTimeLessThanOrEqualTo(String value) {
            addCriterion("LETTER_END_TIME <=", value, "letterEndTime");
            return (Criteria) this;
        }

        public Criteria andLetterEndTimeLike(String value) {
            addCriterion("LETTER_END_TIME like", value, "letterEndTime");
            return (Criteria) this;
        }

        public Criteria andLetterEndTimeNotLike(String value) {
            addCriterion("LETTER_END_TIME not like", value, "letterEndTime");
            return (Criteria) this;
        }

        public Criteria andLetterEndTimeIn(List<String> values) {
            addCriterion("LETTER_END_TIME in", values, "letterEndTime");
            return (Criteria) this;
        }

        public Criteria andLetterEndTimeNotIn(List<String> values) {
            addCriterion("LETTER_END_TIME not in", values, "letterEndTime");
            return (Criteria) this;
        }

        public Criteria andLetterEndTimeBetween(String value1, String value2) {
            addCriterion("LETTER_END_TIME between", value1, value2, "letterEndTime");
            return (Criteria) this;
        }

        public Criteria andLetterEndTimeNotBetween(String value1, String value2) {
            addCriterion("LETTER_END_TIME not between", value1, value2, "letterEndTime");
            return (Criteria) this;
        }

        public Criteria andLetterTimeIsNull() {
            addCriterion("LETTER_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLetterTimeIsNotNull() {
            addCriterion("LETTER_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLetterTimeEqualTo(String value) {
            addCriterion("LETTER_TIME =", value, "letterTime");
            return (Criteria) this;
        }

        public Criteria andLetterTimeNotEqualTo(String value) {
            addCriterion("LETTER_TIME <>", value, "letterTime");
            return (Criteria) this;
        }

        public Criteria andLetterTimeGreaterThan(String value) {
            addCriterion("LETTER_TIME >", value, "letterTime");
            return (Criteria) this;
        }

        public Criteria andLetterTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LETTER_TIME >=", value, "letterTime");
            return (Criteria) this;
        }

        public Criteria andLetterTimeLessThan(String value) {
            addCriterion("LETTER_TIME <", value, "letterTime");
            return (Criteria) this;
        }

        public Criteria andLetterTimeLessThanOrEqualTo(String value) {
            addCriterion("LETTER_TIME <=", value, "letterTime");
            return (Criteria) this;
        }

        public Criteria andLetterTimeLike(String value) {
            addCriterion("LETTER_TIME like", value, "letterTime");
            return (Criteria) this;
        }

        public Criteria andLetterTimeNotLike(String value) {
            addCriterion("LETTER_TIME not like", value, "letterTime");
            return (Criteria) this;
        }

        public Criteria andLetterTimeIn(List<String> values) {
            addCriterion("LETTER_TIME in", values, "letterTime");
            return (Criteria) this;
        }

        public Criteria andLetterTimeNotIn(List<String> values) {
            addCriterion("LETTER_TIME not in", values, "letterTime");
            return (Criteria) this;
        }

        public Criteria andLetterTimeBetween(String value1, String value2) {
            addCriterion("LETTER_TIME between", value1, value2, "letterTime");
            return (Criteria) this;
        }

        public Criteria andLetterTimeNotBetween(String value1, String value2) {
            addCriterion("LETTER_TIME not between", value1, value2, "letterTime");
            return (Criteria) this;
        }

        public Criteria andLetterFileIsNull() {
            addCriterion("LETTER_FILE is null");
            return (Criteria) this;
        }

        public Criteria andLetterFileIsNotNull() {
            addCriterion("LETTER_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andLetterFileEqualTo(String value) {
            addCriterion("LETTER_FILE =", value, "letterFile");
            return (Criteria) this;
        }

        public Criteria andLetterFileNotEqualTo(String value) {
            addCriterion("LETTER_FILE <>", value, "letterFile");
            return (Criteria) this;
        }

        public Criteria andLetterFileGreaterThan(String value) {
            addCriterion("LETTER_FILE >", value, "letterFile");
            return (Criteria) this;
        }

        public Criteria andLetterFileGreaterThanOrEqualTo(String value) {
            addCriterion("LETTER_FILE >=", value, "letterFile");
            return (Criteria) this;
        }

        public Criteria andLetterFileLessThan(String value) {
            addCriterion("LETTER_FILE <", value, "letterFile");
            return (Criteria) this;
        }

        public Criteria andLetterFileLessThanOrEqualTo(String value) {
            addCriterion("LETTER_FILE <=", value, "letterFile");
            return (Criteria) this;
        }

        public Criteria andLetterFileLike(String value) {
            addCriterion("LETTER_FILE like", value, "letterFile");
            return (Criteria) this;
        }

        public Criteria andLetterFileNotLike(String value) {
            addCriterion("LETTER_FILE not like", value, "letterFile");
            return (Criteria) this;
        }

        public Criteria andLetterFileIn(List<String> values) {
            addCriterion("LETTER_FILE in", values, "letterFile");
            return (Criteria) this;
        }

        public Criteria andLetterFileNotIn(List<String> values) {
            addCriterion("LETTER_FILE not in", values, "letterFile");
            return (Criteria) this;
        }

        public Criteria andLetterFileBetween(String value1, String value2) {
            addCriterion("LETTER_FILE between", value1, value2, "letterFile");
            return (Criteria) this;
        }

        public Criteria andLetterFileNotBetween(String value1, String value2) {
            addCriterion("LETTER_FILE not between", value1, value2, "letterFile");
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

        public Criteria andLetterYearIsNull() {
            addCriterion("LETTER_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andLetterYearIsNotNull() {
            addCriterion("LETTER_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andLetterYearEqualTo(String value) {
            addCriterion("LETTER_YEAR =", value, "letterYear");
            return (Criteria) this;
        }

        public Criteria andLetterYearNotEqualTo(String value) {
            addCriterion("LETTER_YEAR <>", value, "letterYear");
            return (Criteria) this;
        }

        public Criteria andLetterYearGreaterThan(String value) {
            addCriterion("LETTER_YEAR >", value, "letterYear");
            return (Criteria) this;
        }

        public Criteria andLetterYearGreaterThanOrEqualTo(String value) {
            addCriterion("LETTER_YEAR >=", value, "letterYear");
            return (Criteria) this;
        }

        public Criteria andLetterYearLessThan(String value) {
            addCriterion("LETTER_YEAR <", value, "letterYear");
            return (Criteria) this;
        }

        public Criteria andLetterYearLessThanOrEqualTo(String value) {
            addCriterion("LETTER_YEAR <=", value, "letterYear");
            return (Criteria) this;
        }

        public Criteria andLetterYearLike(String value) {
            addCriterion("LETTER_YEAR like", value, "letterYear");
            return (Criteria) this;
        }

        public Criteria andLetterYearNotLike(String value) {
            addCriterion("LETTER_YEAR not like", value, "letterYear");
            return (Criteria) this;
        }

        public Criteria andLetterYearIn(List<String> values) {
            addCriterion("LETTER_YEAR in", values, "letterYear");
            return (Criteria) this;
        }

        public Criteria andLetterYearNotIn(List<String> values) {
            addCriterion("LETTER_YEAR not in", values, "letterYear");
            return (Criteria) this;
        }

        public Criteria andLetterYearBetween(String value1, String value2) {
            addCriterion("LETTER_YEAR between", value1, value2, "letterYear");
            return (Criteria) this;
        }

        public Criteria andLetterYearNotBetween(String value1, String value2) {
            addCriterion("LETTER_YEAR not between", value1, value2, "letterYear");
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