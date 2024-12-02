package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResLectureRandomSignExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResLectureRandomSignExample() {
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

        public Criteria andRandomFlowIsNull() {
            addCriterion("RANDOM_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRandomFlowIsNotNull() {
            addCriterion("RANDOM_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRandomFlowEqualTo(String value) {
            addCriterion("RANDOM_FLOW =", value, "randomFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFlowNotEqualTo(String value) {
            addCriterion("RANDOM_FLOW <>", value, "randomFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFlowGreaterThan(String value) {
            addCriterion("RANDOM_FLOW >", value, "randomFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RANDOM_FLOW >=", value, "randomFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFlowLessThan(String value) {
            addCriterion("RANDOM_FLOW <", value, "randomFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFlowLessThanOrEqualTo(String value) {
            addCriterion("RANDOM_FLOW <=", value, "randomFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFlowLike(String value) {
            addCriterion("RANDOM_FLOW like", value, "randomFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFlowNotLike(String value) {
            addCriterion("RANDOM_FLOW not like", value, "randomFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFlowIn(List<String> values) {
            addCriterion("RANDOM_FLOW in", values, "randomFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFlowNotIn(List<String> values) {
            addCriterion("RANDOM_FLOW not in", values, "randomFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFlowBetween(String value1, String value2) {
            addCriterion("RANDOM_FLOW between", value1, value2, "randomFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFlowNotBetween(String value1, String value2) {
            addCriterion("RANDOM_FLOW not between", value1, value2, "randomFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowIsNull() {
            addCriterion("LECTURE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andLectureFlowIsNotNull() {
            addCriterion("LECTURE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andLectureFlowEqualTo(String value) {
            addCriterion("LECTURE_FLOW =", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowNotEqualTo(String value) {
            addCriterion("LECTURE_FLOW <>", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowGreaterThan(String value) {
            addCriterion("LECTURE_FLOW >", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_FLOW >=", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowLessThan(String value) {
            addCriterion("LECTURE_FLOW <", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_FLOW <=", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowLike(String value) {
            addCriterion("LECTURE_FLOW like", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowNotLike(String value) {
            addCriterion("LECTURE_FLOW not like", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowIn(List<String> values) {
            addCriterion("LECTURE_FLOW in", values, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowNotIn(List<String> values) {
            addCriterion("LECTURE_FLOW not in", values, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowBetween(String value1, String value2) {
            addCriterion("LECTURE_FLOW between", value1, value2, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowNotBetween(String value1, String value2) {
            addCriterion("LECTURE_FLOW not between", value1, value2, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andCodeStartTimeIsNull() {
            addCriterion("CODE_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCodeStartTimeIsNotNull() {
            addCriterion("CODE_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCodeStartTimeEqualTo(String value) {
            addCriterion("CODE_START_TIME =", value, "codeStartTime");
            return (Criteria) this;
        }

        public Criteria andCodeStartTimeNotEqualTo(String value) {
            addCriterion("CODE_START_TIME <>", value, "codeStartTime");
            return (Criteria) this;
        }

        public Criteria andCodeStartTimeGreaterThan(String value) {
            addCriterion("CODE_START_TIME >", value, "codeStartTime");
            return (Criteria) this;
        }

        public Criteria andCodeStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CODE_START_TIME >=", value, "codeStartTime");
            return (Criteria) this;
        }

        public Criteria andCodeStartTimeLessThan(String value) {
            addCriterion("CODE_START_TIME <", value, "codeStartTime");
            return (Criteria) this;
        }

        public Criteria andCodeStartTimeLessThanOrEqualTo(String value) {
            addCriterion("CODE_START_TIME <=", value, "codeStartTime");
            return (Criteria) this;
        }

        public Criteria andCodeStartTimeLike(String value) {
            addCriterion("CODE_START_TIME like", value, "codeStartTime");
            return (Criteria) this;
        }

        public Criteria andCodeStartTimeNotLike(String value) {
            addCriterion("CODE_START_TIME not like", value, "codeStartTime");
            return (Criteria) this;
        }

        public Criteria andCodeStartTimeIn(List<String> values) {
            addCriterion("CODE_START_TIME in", values, "codeStartTime");
            return (Criteria) this;
        }

        public Criteria andCodeStartTimeNotIn(List<String> values) {
            addCriterion("CODE_START_TIME not in", values, "codeStartTime");
            return (Criteria) this;
        }

        public Criteria andCodeStartTimeBetween(String value1, String value2) {
            addCriterion("CODE_START_TIME between", value1, value2, "codeStartTime");
            return (Criteria) this;
        }

        public Criteria andCodeStartTimeNotBetween(String value1, String value2) {
            addCriterion("CODE_START_TIME not between", value1, value2, "codeStartTime");
            return (Criteria) this;
        }

        public Criteria andCodeEndTimeIsNull() {
            addCriterion("CODE_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCodeEndTimeIsNotNull() {
            addCriterion("CODE_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEndTimeEqualTo(String value) {
            addCriterion("CODE_END_TIME =", value, "codeEndTime");
            return (Criteria) this;
        }

        public Criteria andCodeEndTimeNotEqualTo(String value) {
            addCriterion("CODE_END_TIME <>", value, "codeEndTime");
            return (Criteria) this;
        }

        public Criteria andCodeEndTimeGreaterThan(String value) {
            addCriterion("CODE_END_TIME >", value, "codeEndTime");
            return (Criteria) this;
        }

        public Criteria andCodeEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CODE_END_TIME >=", value, "codeEndTime");
            return (Criteria) this;
        }

        public Criteria andCodeEndTimeLessThan(String value) {
            addCriterion("CODE_END_TIME <", value, "codeEndTime");
            return (Criteria) this;
        }

        public Criteria andCodeEndTimeLessThanOrEqualTo(String value) {
            addCriterion("CODE_END_TIME <=", value, "codeEndTime");
            return (Criteria) this;
        }

        public Criteria andCodeEndTimeLike(String value) {
            addCriterion("CODE_END_TIME like", value, "codeEndTime");
            return (Criteria) this;
        }

        public Criteria andCodeEndTimeNotLike(String value) {
            addCriterion("CODE_END_TIME not like", value, "codeEndTime");
            return (Criteria) this;
        }

        public Criteria andCodeEndTimeIn(List<String> values) {
            addCriterion("CODE_END_TIME in", values, "codeEndTime");
            return (Criteria) this;
        }

        public Criteria andCodeEndTimeNotIn(List<String> values) {
            addCriterion("CODE_END_TIME not in", values, "codeEndTime");
            return (Criteria) this;
        }

        public Criteria andCodeEndTimeBetween(String value1, String value2) {
            addCriterion("CODE_END_TIME between", value1, value2, "codeEndTime");
            return (Criteria) this;
        }

        public Criteria andCodeEndTimeNotBetween(String value1, String value2) {
            addCriterion("CODE_END_TIME not between", value1, value2, "codeEndTime");
            return (Criteria) this;
        }

        public Criteria andCodeStatusTypeIsNull() {
            addCriterion("CODE_STATUS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCodeStatusTypeIsNotNull() {
            addCriterion("CODE_STATUS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCodeStatusTypeEqualTo(String value) {
            addCriterion("CODE_STATUS_TYPE =", value, "codeStatusType");
            return (Criteria) this;
        }

        public Criteria andCodeStatusTypeNotEqualTo(String value) {
            addCriterion("CODE_STATUS_TYPE <>", value, "codeStatusType");
            return (Criteria) this;
        }

        public Criteria andCodeStatusTypeGreaterThan(String value) {
            addCriterion("CODE_STATUS_TYPE >", value, "codeStatusType");
            return (Criteria) this;
        }

        public Criteria andCodeStatusTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CODE_STATUS_TYPE >=", value, "codeStatusType");
            return (Criteria) this;
        }

        public Criteria andCodeStatusTypeLessThan(String value) {
            addCriterion("CODE_STATUS_TYPE <", value, "codeStatusType");
            return (Criteria) this;
        }

        public Criteria andCodeStatusTypeLessThanOrEqualTo(String value) {
            addCriterion("CODE_STATUS_TYPE <=", value, "codeStatusType");
            return (Criteria) this;
        }

        public Criteria andCodeStatusTypeLike(String value) {
            addCriterion("CODE_STATUS_TYPE like", value, "codeStatusType");
            return (Criteria) this;
        }

        public Criteria andCodeStatusTypeNotLike(String value) {
            addCriterion("CODE_STATUS_TYPE not like", value, "codeStatusType");
            return (Criteria) this;
        }

        public Criteria andCodeStatusTypeIn(List<String> values) {
            addCriterion("CODE_STATUS_TYPE in", values, "codeStatusType");
            return (Criteria) this;
        }

        public Criteria andCodeStatusTypeNotIn(List<String> values) {
            addCriterion("CODE_STATUS_TYPE not in", values, "codeStatusType");
            return (Criteria) this;
        }

        public Criteria andCodeStatusTypeBetween(String value1, String value2) {
            addCriterion("CODE_STATUS_TYPE between", value1, value2, "codeStatusType");
            return (Criteria) this;
        }

        public Criteria andCodeStatusTypeNotBetween(String value1, String value2) {
            addCriterion("CODE_STATUS_TYPE not between", value1, value2, "codeStatusType");
            return (Criteria) this;
        }

        public Criteria andCodeStatusNameIsNull() {
            addCriterion("CODE_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCodeStatusNameIsNotNull() {
            addCriterion("CODE_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCodeStatusNameEqualTo(String value) {
            addCriterion("CODE_STATUS_NAME =", value, "codeStatusName");
            return (Criteria) this;
        }

        public Criteria andCodeStatusNameNotEqualTo(String value) {
            addCriterion("CODE_STATUS_NAME <>", value, "codeStatusName");
            return (Criteria) this;
        }

        public Criteria andCodeStatusNameGreaterThan(String value) {
            addCriterion("CODE_STATUS_NAME >", value, "codeStatusName");
            return (Criteria) this;
        }

        public Criteria andCodeStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("CODE_STATUS_NAME >=", value, "codeStatusName");
            return (Criteria) this;
        }

        public Criteria andCodeStatusNameLessThan(String value) {
            addCriterion("CODE_STATUS_NAME <", value, "codeStatusName");
            return (Criteria) this;
        }

        public Criteria andCodeStatusNameLessThanOrEqualTo(String value) {
            addCriterion("CODE_STATUS_NAME <=", value, "codeStatusName");
            return (Criteria) this;
        }

        public Criteria andCodeStatusNameLike(String value) {
            addCriterion("CODE_STATUS_NAME like", value, "codeStatusName");
            return (Criteria) this;
        }

        public Criteria andCodeStatusNameNotLike(String value) {
            addCriterion("CODE_STATUS_NAME not like", value, "codeStatusName");
            return (Criteria) this;
        }

        public Criteria andCodeStatusNameIn(List<String> values) {
            addCriterion("CODE_STATUS_NAME in", values, "codeStatusName");
            return (Criteria) this;
        }

        public Criteria andCodeStatusNameNotIn(List<String> values) {
            addCriterion("CODE_STATUS_NAME not in", values, "codeStatusName");
            return (Criteria) this;
        }

        public Criteria andCodeStatusNameBetween(String value1, String value2) {
            addCriterion("CODE_STATUS_NAME between", value1, value2, "codeStatusName");
            return (Criteria) this;
        }

        public Criteria andCodeStatusNameNotBetween(String value1, String value2) {
            addCriterion("CODE_STATUS_NAME not between", value1, value2, "codeStatusName");
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