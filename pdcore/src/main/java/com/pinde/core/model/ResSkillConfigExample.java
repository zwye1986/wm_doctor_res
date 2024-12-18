package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResSkillConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResSkillConfigExample() {
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

        public Criteria andSkillFlowIsNull() {
            addCriterion("SKILL_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSkillFlowIsNotNull() {
            addCriterion("SKILL_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSkillFlowEqualTo(String value) {
            addCriterion("SKILL_FLOW =", value, "skillFlow");
            return (Criteria) this;
        }

        public Criteria andSkillFlowNotEqualTo(String value) {
            addCriterion("SKILL_FLOW <>", value, "skillFlow");
            return (Criteria) this;
        }

        public Criteria andSkillFlowGreaterThan(String value) {
            addCriterion("SKILL_FLOW >", value, "skillFlow");
            return (Criteria) this;
        }

        public Criteria andSkillFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_FLOW >=", value, "skillFlow");
            return (Criteria) this;
        }

        public Criteria andSkillFlowLessThan(String value) {
            addCriterion("SKILL_FLOW <", value, "skillFlow");
            return (Criteria) this;
        }

        public Criteria andSkillFlowLessThanOrEqualTo(String value) {
            addCriterion("SKILL_FLOW <=", value, "skillFlow");
            return (Criteria) this;
        }

        public Criteria andSkillFlowLike(String value) {
            addCriterion("SKILL_FLOW like", value, "skillFlow");
            return (Criteria) this;
        }

        public Criteria andSkillFlowNotLike(String value) {
            addCriterion("SKILL_FLOW not like", value, "skillFlow");
            return (Criteria) this;
        }

        public Criteria andSkillFlowIn(List<String> values) {
            addCriterion("SKILL_FLOW in", values, "skillFlow");
            return (Criteria) this;
        }

        public Criteria andSkillFlowNotIn(List<String> values) {
            addCriterion("SKILL_FLOW not in", values, "skillFlow");
            return (Criteria) this;
        }

        public Criteria andSkillFlowBetween(String value1, String value2) {
            addCriterion("SKILL_FLOW between", value1, value2, "skillFlow");
            return (Criteria) this;
        }

        public Criteria andSkillFlowNotBetween(String value1, String value2) {
            addCriterion("SKILL_FLOW not between", value1, value2, "skillFlow");
            return (Criteria) this;
        }

        public Criteria andSkillTimeFlowIsNull() {
            addCriterion("SKILL_TIME_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSkillTimeFlowIsNotNull() {
            addCriterion("SKILL_TIME_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSkillTimeFlowEqualTo(String value) {
            addCriterion("SKILL_TIME_FLOW =", value, "skillTimeFlow");
            return (Criteria) this;
        }

        public Criteria andSkillTimeFlowNotEqualTo(String value) {
            addCriterion("SKILL_TIME_FLOW <>", value, "skillTimeFlow");
            return (Criteria) this;
        }

        public Criteria andSkillTimeFlowGreaterThan(String value) {
            addCriterion("SKILL_TIME_FLOW >", value, "skillTimeFlow");
            return (Criteria) this;
        }

        public Criteria andSkillTimeFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_TIME_FLOW >=", value, "skillTimeFlow");
            return (Criteria) this;
        }

        public Criteria andSkillTimeFlowLessThan(String value) {
            addCriterion("SKILL_TIME_FLOW <", value, "skillTimeFlow");
            return (Criteria) this;
        }

        public Criteria andSkillTimeFlowLessThanOrEqualTo(String value) {
            addCriterion("SKILL_TIME_FLOW <=", value, "skillTimeFlow");
            return (Criteria) this;
        }

        public Criteria andSkillTimeFlowLike(String value) {
            addCriterion("SKILL_TIME_FLOW like", value, "skillTimeFlow");
            return (Criteria) this;
        }

        public Criteria andSkillTimeFlowNotLike(String value) {
            addCriterion("SKILL_TIME_FLOW not like", value, "skillTimeFlow");
            return (Criteria) this;
        }

        public Criteria andSkillTimeFlowIn(List<String> values) {
            addCriterion("SKILL_TIME_FLOW in", values, "skillTimeFlow");
            return (Criteria) this;
        }

        public Criteria andSkillTimeFlowNotIn(List<String> values) {
            addCriterion("SKILL_TIME_FLOW not in", values, "skillTimeFlow");
            return (Criteria) this;
        }

        public Criteria andSkillTimeFlowBetween(String value1, String value2) {
            addCriterion("SKILL_TIME_FLOW between", value1, value2, "skillTimeFlow");
            return (Criteria) this;
        }

        public Criteria andSkillTimeFlowNotBetween(String value1, String value2) {
            addCriterion("SKILL_TIME_FLOW not between", value1, value2, "skillTimeFlow");
            return (Criteria) this;
        }

        public Criteria andTestIdIsNull() {
            addCriterion("TEST_ID is null");
            return (Criteria) this;
        }

        public Criteria andTestIdIsNotNull() {
            addCriterion("TEST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTestIdEqualTo(String value) {
            addCriterion("TEST_ID =", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotEqualTo(String value) {
            addCriterion("TEST_ID <>", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdGreaterThan(String value) {
            addCriterion("TEST_ID >", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_ID >=", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdLessThan(String value) {
            addCriterion("TEST_ID <", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdLessThanOrEqualTo(String value) {
            addCriterion("TEST_ID <=", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdLike(String value) {
            addCriterion("TEST_ID like", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotLike(String value) {
            addCriterion("TEST_ID not like", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdIn(List<String> values) {
            addCriterion("TEST_ID in", values, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotIn(List<String> values) {
            addCriterion("TEST_ID not in", values, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdBetween(String value1, String value2) {
            addCriterion("TEST_ID between", value1, value2, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotBetween(String value1, String value2) {
            addCriterion("TEST_ID not between", value1, value2, "testId");
            return (Criteria) this;
        }

        public Criteria andTestNameIsNull() {
            addCriterion("TEST_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTestNameIsNotNull() {
            addCriterion("TEST_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTestNameEqualTo(String value) {
            addCriterion("TEST_NAME =", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotEqualTo(String value) {
            addCriterion("TEST_NAME <>", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameGreaterThan(String value) {
            addCriterion("TEST_NAME >", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_NAME >=", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameLessThan(String value) {
            addCriterion("TEST_NAME <", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameLessThanOrEqualTo(String value) {
            addCriterion("TEST_NAME <=", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameLike(String value) {
            addCriterion("TEST_NAME like", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotLike(String value) {
            addCriterion("TEST_NAME not like", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameIn(List<String> values) {
            addCriterion("TEST_NAME in", values, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotIn(List<String> values) {
            addCriterion("TEST_NAME not in", values, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameBetween(String value1, String value2) {
            addCriterion("TEST_NAME between", value1, value2, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotBetween(String value1, String value2) {
            addCriterion("TEST_NAME not between", value1, value2, "testName");
            return (Criteria) this;
        }

        public Criteria andSkillStartTimeIsNull() {
            addCriterion("SKILL_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSkillStartTimeIsNotNull() {
            addCriterion("SKILL_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSkillStartTimeEqualTo(String value) {
            addCriterion("SKILL_START_TIME =", value, "skillStartTime");
            return (Criteria) this;
        }

        public Criteria andSkillStartTimeNotEqualTo(String value) {
            addCriterion("SKILL_START_TIME <>", value, "skillStartTime");
            return (Criteria) this;
        }

        public Criteria andSkillStartTimeGreaterThan(String value) {
            addCriterion("SKILL_START_TIME >", value, "skillStartTime");
            return (Criteria) this;
        }

        public Criteria andSkillStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_START_TIME >=", value, "skillStartTime");
            return (Criteria) this;
        }

        public Criteria andSkillStartTimeLessThan(String value) {
            addCriterion("SKILL_START_TIME <", value, "skillStartTime");
            return (Criteria) this;
        }

        public Criteria andSkillStartTimeLessThanOrEqualTo(String value) {
            addCriterion("SKILL_START_TIME <=", value, "skillStartTime");
            return (Criteria) this;
        }

        public Criteria andSkillStartTimeLike(String value) {
            addCriterion("SKILL_START_TIME like", value, "skillStartTime");
            return (Criteria) this;
        }

        public Criteria andSkillStartTimeNotLike(String value) {
            addCriterion("SKILL_START_TIME not like", value, "skillStartTime");
            return (Criteria) this;
        }

        public Criteria andSkillStartTimeIn(List<String> values) {
            addCriterion("SKILL_START_TIME in", values, "skillStartTime");
            return (Criteria) this;
        }

        public Criteria andSkillStartTimeNotIn(List<String> values) {
            addCriterion("SKILL_START_TIME not in", values, "skillStartTime");
            return (Criteria) this;
        }

        public Criteria andSkillStartTimeBetween(String value1, String value2) {
            addCriterion("SKILL_START_TIME between", value1, value2, "skillStartTime");
            return (Criteria) this;
        }

        public Criteria andSkillStartTimeNotBetween(String value1, String value2) {
            addCriterion("SKILL_START_TIME not between", value1, value2, "skillStartTime");
            return (Criteria) this;
        }

        public Criteria andSkillEndTimeIsNull() {
            addCriterion("SKILL_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSkillEndTimeIsNotNull() {
            addCriterion("SKILL_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSkillEndTimeEqualTo(String value) {
            addCriterion("SKILL_END_TIME =", value, "skillEndTime");
            return (Criteria) this;
        }

        public Criteria andSkillEndTimeNotEqualTo(String value) {
            addCriterion("SKILL_END_TIME <>", value, "skillEndTime");
            return (Criteria) this;
        }

        public Criteria andSkillEndTimeGreaterThan(String value) {
            addCriterion("SKILL_END_TIME >", value, "skillEndTime");
            return (Criteria) this;
        }

        public Criteria andSkillEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_END_TIME >=", value, "skillEndTime");
            return (Criteria) this;
        }

        public Criteria andSkillEndTimeLessThan(String value) {
            addCriterion("SKILL_END_TIME <", value, "skillEndTime");
            return (Criteria) this;
        }

        public Criteria andSkillEndTimeLessThanOrEqualTo(String value) {
            addCriterion("SKILL_END_TIME <=", value, "skillEndTime");
            return (Criteria) this;
        }

        public Criteria andSkillEndTimeLike(String value) {
            addCriterion("SKILL_END_TIME like", value, "skillEndTime");
            return (Criteria) this;
        }

        public Criteria andSkillEndTimeNotLike(String value) {
            addCriterion("SKILL_END_TIME not like", value, "skillEndTime");
            return (Criteria) this;
        }

        public Criteria andSkillEndTimeIn(List<String> values) {
            addCriterion("SKILL_END_TIME in", values, "skillEndTime");
            return (Criteria) this;
        }

        public Criteria andSkillEndTimeNotIn(List<String> values) {
            addCriterion("SKILL_END_TIME not in", values, "skillEndTime");
            return (Criteria) this;
        }

        public Criteria andSkillEndTimeBetween(String value1, String value2) {
            addCriterion("SKILL_END_TIME between", value1, value2, "skillEndTime");
            return (Criteria) this;
        }

        public Criteria andSkillEndTimeNotBetween(String value1, String value2) {
            addCriterion("SKILL_END_TIME not between", value1, value2, "skillEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeIsNull() {
            addCriterion("APPOINT_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeIsNotNull() {
            addCriterion("APPOINT_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeEqualTo(String value) {
            addCriterion("APPOINT_START_TIME =", value, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeNotEqualTo(String value) {
            addCriterion("APPOINT_START_TIME <>", value, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeGreaterThan(String value) {
            addCriterion("APPOINT_START_TIME >", value, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("APPOINT_START_TIME >=", value, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeLessThan(String value) {
            addCriterion("APPOINT_START_TIME <", value, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeLessThanOrEqualTo(String value) {
            addCriterion("APPOINT_START_TIME <=", value, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeLike(String value) {
            addCriterion("APPOINT_START_TIME like", value, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeNotLike(String value) {
            addCriterion("APPOINT_START_TIME not like", value, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeIn(List<String> values) {
            addCriterion("APPOINT_START_TIME in", values, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeNotIn(List<String> values) {
            addCriterion("APPOINT_START_TIME not in", values, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeBetween(String value1, String value2) {
            addCriterion("APPOINT_START_TIME between", value1, value2, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeNotBetween(String value1, String value2) {
            addCriterion("APPOINT_START_TIME not between", value1, value2, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeIsNull() {
            addCriterion("APPOINT_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeIsNotNull() {
            addCriterion("APPOINT_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeEqualTo(String value) {
            addCriterion("APPOINT_END_TIME =", value, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeNotEqualTo(String value) {
            addCriterion("APPOINT_END_TIME <>", value, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeGreaterThan(String value) {
            addCriterion("APPOINT_END_TIME >", value, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("APPOINT_END_TIME >=", value, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeLessThan(String value) {
            addCriterion("APPOINT_END_TIME <", value, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeLessThanOrEqualTo(String value) {
            addCriterion("APPOINT_END_TIME <=", value, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeLike(String value) {
            addCriterion("APPOINT_END_TIME like", value, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeNotLike(String value) {
            addCriterion("APPOINT_END_TIME not like", value, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeIn(List<String> values) {
            addCriterion("APPOINT_END_TIME in", values, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeNotIn(List<String> values) {
            addCriterion("APPOINT_END_TIME not in", values, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeBetween(String value1, String value2) {
            addCriterion("APPOINT_END_TIME between", value1, value2, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeNotBetween(String value1, String value2) {
            addCriterion("APPOINT_END_TIME not between", value1, value2, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andSkillWayIsNull() {
            addCriterion("SKILL_WAY is null");
            return (Criteria) this;
        }

        public Criteria andSkillWayIsNotNull() {
            addCriterion("SKILL_WAY is not null");
            return (Criteria) this;
        }

        public Criteria andSkillWayEqualTo(String value) {
            addCriterion("SKILL_WAY =", value, "skillWay");
            return (Criteria) this;
        }

        public Criteria andSkillWayNotEqualTo(String value) {
            addCriterion("SKILL_WAY <>", value, "skillWay");
            return (Criteria) this;
        }

        public Criteria andSkillWayGreaterThan(String value) {
            addCriterion("SKILL_WAY >", value, "skillWay");
            return (Criteria) this;
        }

        public Criteria andSkillWayGreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_WAY >=", value, "skillWay");
            return (Criteria) this;
        }

        public Criteria andSkillWayLessThan(String value) {
            addCriterion("SKILL_WAY <", value, "skillWay");
            return (Criteria) this;
        }

        public Criteria andSkillWayLessThanOrEqualTo(String value) {
            addCriterion("SKILL_WAY <=", value, "skillWay");
            return (Criteria) this;
        }

        public Criteria andSkillWayLike(String value) {
            addCriterion("SKILL_WAY like", value, "skillWay");
            return (Criteria) this;
        }

        public Criteria andSkillWayNotLike(String value) {
            addCriterion("SKILL_WAY not like", value, "skillWay");
            return (Criteria) this;
        }

        public Criteria andSkillWayIn(List<String> values) {
            addCriterion("SKILL_WAY in", values, "skillWay");
            return (Criteria) this;
        }

        public Criteria andSkillWayNotIn(List<String> values) {
            addCriterion("SKILL_WAY not in", values, "skillWay");
            return (Criteria) this;
        }

        public Criteria andSkillWayBetween(String value1, String value2) {
            addCriterion("SKILL_WAY between", value1, value2, "skillWay");
            return (Criteria) this;
        }

        public Criteria andSkillWayNotBetween(String value1, String value2) {
            addCriterion("SKILL_WAY not between", value1, value2, "skillWay");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNull() {
            addCriterion("CITY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("CITY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(String value) {
            addCriterion("CITY_ID =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(String value) {
            addCriterion("CITY_ID <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(String value) {
            addCriterion("CITY_ID >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_ID >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(String value) {
            addCriterion("CITY_ID <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(String value) {
            addCriterion("CITY_ID <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLike(String value) {
            addCriterion("CITY_ID like", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotLike(String value) {
            addCriterion("CITY_ID not like", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<String> values) {
            addCriterion("CITY_ID in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<String> values) {
            addCriterion("CITY_ID not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(String value1, String value2) {
            addCriterion("CITY_ID between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(String value1, String value2) {
            addCriterion("CITY_ID not between", value1, value2, "cityId");
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