package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class StudySubjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StudySubjectExample() {
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

        public Criteria andSubjectFlowIsNull() {
            addCriterion("SUBJECT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowIsNotNull() {
            addCriterion("SUBJECT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowEqualTo(String value) {
            addCriterion("SUBJECT_FLOW =", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotEqualTo(String value) {
            addCriterion("SUBJECT_FLOW <>", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowGreaterThan(String value) {
            addCriterion("SUBJECT_FLOW >", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_FLOW >=", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowLessThan(String value) {
            addCriterion("SUBJECT_FLOW <", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_FLOW <=", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowLike(String value) {
            addCriterion("SUBJECT_FLOW like", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotLike(String value) {
            addCriterion("SUBJECT_FLOW not like", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowIn(List<String> values) {
            addCriterion("SUBJECT_FLOW in", values, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotIn(List<String> values) {
            addCriterion("SUBJECT_FLOW not in", values, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowBetween(String value1, String value2) {
            addCriterion("SUBJECT_FLOW between", value1, value2, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_FLOW not between", value1, value2, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIsNull() {
            addCriterion("SUBJECT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIsNotNull() {
            addCriterion("SUBJECT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectNameEqualTo(String value) {
            addCriterion("SUBJECT_NAME =", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotEqualTo(String value) {
            addCriterion("SUBJECT_NAME <>", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameGreaterThan(String value) {
            addCriterion("SUBJECT_NAME >", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_NAME >=", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLessThan(String value) {
            addCriterion("SUBJECT_NAME <", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_NAME <=", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLike(String value) {
            addCriterion("SUBJECT_NAME like", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotLike(String value) {
            addCriterion("SUBJECT_NAME not like", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIn(List<String> values) {
            addCriterion("SUBJECT_NAME in", values, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotIn(List<String> values) {
            addCriterion("SUBJECT_NAME not in", values, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameBetween(String value1, String value2) {
            addCriterion("SUBJECT_NAME between", value1, value2, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_NAME not between", value1, value2, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectYearIsNull() {
            addCriterion("SUBJECT_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andSubjectYearIsNotNull() {
            addCriterion("SUBJECT_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectYearEqualTo(String value) {
            addCriterion("SUBJECT_YEAR =", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearNotEqualTo(String value) {
            addCriterion("SUBJECT_YEAR <>", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearGreaterThan(String value) {
            addCriterion("SUBJECT_YEAR >", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_YEAR >=", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearLessThan(String value) {
            addCriterion("SUBJECT_YEAR <", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_YEAR <=", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearLike(String value) {
            addCriterion("SUBJECT_YEAR like", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearNotLike(String value) {
            addCriterion("SUBJECT_YEAR not like", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearIn(List<String> values) {
            addCriterion("SUBJECT_YEAR in", values, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearNotIn(List<String> values) {
            addCriterion("SUBJECT_YEAR not in", values, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearBetween(String value1, String value2) {
            addCriterion("SUBJECT_YEAR between", value1, value2, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_YEAR not between", value1, value2, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIsNull() {
            addCriterion("SUBJECT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIsNotNull() {
            addCriterion("SUBJECT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeEqualTo(String value) {
            addCriterion("SUBJECT_TYPE =", value, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNotEqualTo(String value) {
            addCriterion("SUBJECT_TYPE <>", value, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeGreaterThan(String value) {
            addCriterion("SUBJECT_TYPE >", value, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_TYPE >=", value, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeLessThan(String value) {
            addCriterion("SUBJECT_TYPE <", value, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_TYPE <=", value, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeLike(String value) {
            addCriterion("SUBJECT_TYPE like", value, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNotLike(String value) {
            addCriterion("SUBJECT_TYPE not like", value, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIn(List<String> values) {
            addCriterion("SUBJECT_TYPE in", values, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNotIn(List<String> values) {
            addCriterion("SUBJECT_TYPE not in", values, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeBetween(String value1, String value2) {
            addCriterion("SUBJECT_TYPE between", value1, value2, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_TYPE not between", value1, value2, "subjectType");
            return (Criteria) this;
        }

        public Criteria andSubjectStartTimeIsNull() {
            addCriterion("SUBJECT_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSubjectStartTimeIsNotNull() {
            addCriterion("SUBJECT_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectStartTimeEqualTo(String value) {
            addCriterion("SUBJECT_START_TIME =", value, "subjectStartTime");
            return (Criteria) this;
        }

        public Criteria andSubjectStartTimeNotEqualTo(String value) {
            addCriterion("SUBJECT_START_TIME <>", value, "subjectStartTime");
            return (Criteria) this;
        }

        public Criteria andSubjectStartTimeGreaterThan(String value) {
            addCriterion("SUBJECT_START_TIME >", value, "subjectStartTime");
            return (Criteria) this;
        }

        public Criteria andSubjectStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_START_TIME >=", value, "subjectStartTime");
            return (Criteria) this;
        }

        public Criteria andSubjectStartTimeLessThan(String value) {
            addCriterion("SUBJECT_START_TIME <", value, "subjectStartTime");
            return (Criteria) this;
        }

        public Criteria andSubjectStartTimeLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_START_TIME <=", value, "subjectStartTime");
            return (Criteria) this;
        }

        public Criteria andSubjectStartTimeLike(String value) {
            addCriterion("SUBJECT_START_TIME like", value, "subjectStartTime");
            return (Criteria) this;
        }

        public Criteria andSubjectStartTimeNotLike(String value) {
            addCriterion("SUBJECT_START_TIME not like", value, "subjectStartTime");
            return (Criteria) this;
        }

        public Criteria andSubjectStartTimeIn(List<String> values) {
            addCriterion("SUBJECT_START_TIME in", values, "subjectStartTime");
            return (Criteria) this;
        }

        public Criteria andSubjectStartTimeNotIn(List<String> values) {
            addCriterion("SUBJECT_START_TIME not in", values, "subjectStartTime");
            return (Criteria) this;
        }

        public Criteria andSubjectStartTimeBetween(String value1, String value2) {
            addCriterion("SUBJECT_START_TIME between", value1, value2, "subjectStartTime");
            return (Criteria) this;
        }

        public Criteria andSubjectStartTimeNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_START_TIME not between", value1, value2, "subjectStartTime");
            return (Criteria) this;
        }

        public Criteria andSubjectEndTimeIsNull() {
            addCriterion("SUBJECT_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSubjectEndTimeIsNotNull() {
            addCriterion("SUBJECT_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectEndTimeEqualTo(String value) {
            addCriterion("SUBJECT_END_TIME =", value, "subjectEndTime");
            return (Criteria) this;
        }

        public Criteria andSubjectEndTimeNotEqualTo(String value) {
            addCriterion("SUBJECT_END_TIME <>", value, "subjectEndTime");
            return (Criteria) this;
        }

        public Criteria andSubjectEndTimeGreaterThan(String value) {
            addCriterion("SUBJECT_END_TIME >", value, "subjectEndTime");
            return (Criteria) this;
        }

        public Criteria andSubjectEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_END_TIME >=", value, "subjectEndTime");
            return (Criteria) this;
        }

        public Criteria andSubjectEndTimeLessThan(String value) {
            addCriterion("SUBJECT_END_TIME <", value, "subjectEndTime");
            return (Criteria) this;
        }

        public Criteria andSubjectEndTimeLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_END_TIME <=", value, "subjectEndTime");
            return (Criteria) this;
        }

        public Criteria andSubjectEndTimeLike(String value) {
            addCriterion("SUBJECT_END_TIME like", value, "subjectEndTime");
            return (Criteria) this;
        }

        public Criteria andSubjectEndTimeNotLike(String value) {
            addCriterion("SUBJECT_END_TIME not like", value, "subjectEndTime");
            return (Criteria) this;
        }

        public Criteria andSubjectEndTimeIn(List<String> values) {
            addCriterion("SUBJECT_END_TIME in", values, "subjectEndTime");
            return (Criteria) this;
        }

        public Criteria andSubjectEndTimeNotIn(List<String> values) {
            addCriterion("SUBJECT_END_TIME not in", values, "subjectEndTime");
            return (Criteria) this;
        }

        public Criteria andSubjectEndTimeBetween(String value1, String value2) {
            addCriterion("SUBJECT_END_TIME between", value1, value2, "subjectEndTime");
            return (Criteria) this;
        }

        public Criteria andSubjectEndTimeNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_END_TIME not between", value1, value2, "subjectEndTime");
            return (Criteria) this;
        }

        public Criteria andReservationsNumIsNull() {
            addCriterion("RESERVATIONS_NUM is null");
            return (Criteria) this;
        }

        public Criteria andReservationsNumIsNotNull() {
            addCriterion("RESERVATIONS_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andReservationsNumEqualTo(String value) {
            addCriterion("RESERVATIONS_NUM =", value, "reservationsNum");
            return (Criteria) this;
        }

        public Criteria andReservationsNumNotEqualTo(String value) {
            addCriterion("RESERVATIONS_NUM <>", value, "reservationsNum");
            return (Criteria) this;
        }

        public Criteria andReservationsNumGreaterThan(String value) {
            addCriterion("RESERVATIONS_NUM >", value, "reservationsNum");
            return (Criteria) this;
        }

        public Criteria andReservationsNumGreaterThanOrEqualTo(String value) {
            addCriterion("RESERVATIONS_NUM >=", value, "reservationsNum");
            return (Criteria) this;
        }

        public Criteria andReservationsNumLessThan(String value) {
            addCriterion("RESERVATIONS_NUM <", value, "reservationsNum");
            return (Criteria) this;
        }

        public Criteria andReservationsNumLessThanOrEqualTo(String value) {
            addCriterion("RESERVATIONS_NUM <=", value, "reservationsNum");
            return (Criteria) this;
        }

        public Criteria andReservationsNumLike(String value) {
            addCriterion("RESERVATIONS_NUM like", value, "reservationsNum");
            return (Criteria) this;
        }

        public Criteria andReservationsNumNotLike(String value) {
            addCriterion("RESERVATIONS_NUM not like", value, "reservationsNum");
            return (Criteria) this;
        }

        public Criteria andReservationsNumIn(List<String> values) {
            addCriterion("RESERVATIONS_NUM in", values, "reservationsNum");
            return (Criteria) this;
        }

        public Criteria andReservationsNumNotIn(List<String> values) {
            addCriterion("RESERVATIONS_NUM not in", values, "reservationsNum");
            return (Criteria) this;
        }

        public Criteria andReservationsNumBetween(String value1, String value2) {
            addCriterion("RESERVATIONS_NUM between", value1, value2, "reservationsNum");
            return (Criteria) this;
        }

        public Criteria andReservationsNumNotBetween(String value1, String value2) {
            addCriterion("RESERVATIONS_NUM not between", value1, value2, "reservationsNum");
            return (Criteria) this;
        }

        public Criteria andRemainderNumIsNull() {
            addCriterion("REMAINDER_NUM is null");
            return (Criteria) this;
        }

        public Criteria andRemainderNumIsNotNull() {
            addCriterion("REMAINDER_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andRemainderNumEqualTo(String value) {
            addCriterion("REMAINDER_NUM =", value, "remainderNum");
            return (Criteria) this;
        }

        public Criteria andRemainderNumNotEqualTo(String value) {
            addCriterion("REMAINDER_NUM <>", value, "remainderNum");
            return (Criteria) this;
        }

        public Criteria andRemainderNumGreaterThan(String value) {
            addCriterion("REMAINDER_NUM >", value, "remainderNum");
            return (Criteria) this;
        }

        public Criteria andRemainderNumGreaterThanOrEqualTo(String value) {
            addCriterion("REMAINDER_NUM >=", value, "remainderNum");
            return (Criteria) this;
        }

        public Criteria andRemainderNumLessThan(String value) {
            addCriterion("REMAINDER_NUM <", value, "remainderNum");
            return (Criteria) this;
        }

        public Criteria andRemainderNumLessThanOrEqualTo(String value) {
            addCriterion("REMAINDER_NUM <=", value, "remainderNum");
            return (Criteria) this;
        }

        public Criteria andRemainderNumLike(String value) {
            addCriterion("REMAINDER_NUM like", value, "remainderNum");
            return (Criteria) this;
        }

        public Criteria andRemainderNumNotLike(String value) {
            addCriterion("REMAINDER_NUM not like", value, "remainderNum");
            return (Criteria) this;
        }

        public Criteria andRemainderNumIn(List<String> values) {
            addCriterion("REMAINDER_NUM in", values, "remainderNum");
            return (Criteria) this;
        }

        public Criteria andRemainderNumNotIn(List<String> values) {
            addCriterion("REMAINDER_NUM not in", values, "remainderNum");
            return (Criteria) this;
        }

        public Criteria andRemainderNumBetween(String value1, String value2) {
            addCriterion("REMAINDER_NUM between", value1, value2, "remainderNum");
            return (Criteria) this;
        }

        public Criteria andRemainderNumNotBetween(String value1, String value2) {
            addCriterion("REMAINDER_NUM not between", value1, value2, "remainderNum");
            return (Criteria) this;
        }

        public Criteria andSubjectExplainIsNull() {
            addCriterion("SUBJECT_EXPLAIN is null");
            return (Criteria) this;
        }

        public Criteria andSubjectExplainIsNotNull() {
            addCriterion("SUBJECT_EXPLAIN is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectExplainEqualTo(String value) {
            addCriterion("SUBJECT_EXPLAIN =", value, "subjectExplain");
            return (Criteria) this;
        }

        public Criteria andSubjectExplainNotEqualTo(String value) {
            addCriterion("SUBJECT_EXPLAIN <>", value, "subjectExplain");
            return (Criteria) this;
        }

        public Criteria andSubjectExplainGreaterThan(String value) {
            addCriterion("SUBJECT_EXPLAIN >", value, "subjectExplain");
            return (Criteria) this;
        }

        public Criteria andSubjectExplainGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_EXPLAIN >=", value, "subjectExplain");
            return (Criteria) this;
        }

        public Criteria andSubjectExplainLessThan(String value) {
            addCriterion("SUBJECT_EXPLAIN <", value, "subjectExplain");
            return (Criteria) this;
        }

        public Criteria andSubjectExplainLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_EXPLAIN <=", value, "subjectExplain");
            return (Criteria) this;
        }

        public Criteria andSubjectExplainLike(String value) {
            addCriterion("SUBJECT_EXPLAIN like", value, "subjectExplain");
            return (Criteria) this;
        }

        public Criteria andSubjectExplainNotLike(String value) {
            addCriterion("SUBJECT_EXPLAIN not like", value, "subjectExplain");
            return (Criteria) this;
        }

        public Criteria andSubjectExplainIn(List<String> values) {
            addCriterion("SUBJECT_EXPLAIN in", values, "subjectExplain");
            return (Criteria) this;
        }

        public Criteria andSubjectExplainNotIn(List<String> values) {
            addCriterion("SUBJECT_EXPLAIN not in", values, "subjectExplain");
            return (Criteria) this;
        }

        public Criteria andSubjectExplainBetween(String value1, String value2) {
            addCriterion("SUBJECT_EXPLAIN between", value1, value2, "subjectExplain");
            return (Criteria) this;
        }

        public Criteria andSubjectExplainNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_EXPLAIN not between", value1, value2, "subjectExplain");
            return (Criteria) this;
        }

        public Criteria andPostStatusIsNull() {
            addCriterion("POST_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andPostStatusIsNotNull() {
            addCriterion("POST_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andPostStatusEqualTo(String value) {
            addCriterion("POST_STATUS =", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusNotEqualTo(String value) {
            addCriterion("POST_STATUS <>", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusGreaterThan(String value) {
            addCriterion("POST_STATUS >", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusGreaterThanOrEqualTo(String value) {
            addCriterion("POST_STATUS >=", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusLessThan(String value) {
            addCriterion("POST_STATUS <", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusLessThanOrEqualTo(String value) {
            addCriterion("POST_STATUS <=", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusLike(String value) {
            addCriterion("POST_STATUS like", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusNotLike(String value) {
            addCriterion("POST_STATUS not like", value, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusIn(List<String> values) {
            addCriterion("POST_STATUS in", values, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusNotIn(List<String> values) {
            addCriterion("POST_STATUS not in", values, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusBetween(String value1, String value2) {
            addCriterion("POST_STATUS between", value1, value2, "postStatus");
            return (Criteria) this;
        }

        public Criteria andPostStatusNotBetween(String value1, String value2) {
            addCriterion("POST_STATUS not between", value1, value2, "postStatus");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIsNull() {
            addCriterion("OPER_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIsNotNull() {
            addCriterion("OPER_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdEqualTo(String value) {
            addCriterion("OPER_STATUS_ID =", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotEqualTo(String value) {
            addCriterion("OPER_STATUS_ID <>", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdGreaterThan(String value) {
            addCriterion("OPER_STATUS_ID >", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_ID >=", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLessThan(String value) {
            addCriterion("OPER_STATUS_ID <", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLessThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_ID <=", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLike(String value) {
            addCriterion("OPER_STATUS_ID like", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotLike(String value) {
            addCriterion("OPER_STATUS_ID not like", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIn(List<String> values) {
            addCriterion("OPER_STATUS_ID in", values, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotIn(List<String> values) {
            addCriterion("OPER_STATUS_ID not in", values, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_ID between", value1, value2, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_ID not between", value1, value2, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIsNull() {
            addCriterion("OPER_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIsNotNull() {
            addCriterion("OPER_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME =", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME <>", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameGreaterThan(String value) {
            addCriterion("OPER_STATUS_NAME >", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME >=", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLessThan(String value) {
            addCriterion("OPER_STATUS_NAME <", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLessThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME <=", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLike(String value) {
            addCriterion("OPER_STATUS_NAME like", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotLike(String value) {
            addCriterion("OPER_STATUS_NAME not like", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIn(List<String> values) {
            addCriterion("OPER_STATUS_NAME in", values, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotIn(List<String> values) {
            addCriterion("OPER_STATUS_NAME not in", values, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_NAME between", value1, value2, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_NAME not between", value1, value2, "operStatusName");
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