package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class LcjnCourseInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LcjnCourseInfoExample() {
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

        public Criteria andCourseFlowIsNull() {
            addCriterion("COURSE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andCourseFlowIsNotNull() {
            addCriterion("COURSE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andCourseFlowEqualTo(String value) {
            addCriterion("COURSE_FLOW =", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowNotEqualTo(String value) {
            addCriterion("COURSE_FLOW <>", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowGreaterThan(String value) {
            addCriterion("COURSE_FLOW >", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_FLOW >=", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowLessThan(String value) {
            addCriterion("COURSE_FLOW <", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowLessThanOrEqualTo(String value) {
            addCriterion("COURSE_FLOW <=", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowLike(String value) {
            addCriterion("COURSE_FLOW like", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowNotLike(String value) {
            addCriterion("COURSE_FLOW not like", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowIn(List<String> values) {
            addCriterion("COURSE_FLOW in", values, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowNotIn(List<String> values) {
            addCriterion("COURSE_FLOW not in", values, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowBetween(String value1, String value2) {
            addCriterion("COURSE_FLOW between", value1, value2, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowNotBetween(String value1, String value2) {
            addCriterion("COURSE_FLOW not between", value1, value2, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNull() {
            addCriterion("COURSE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNotNull() {
            addCriterion("COURSE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEqualTo(String value) {
            addCriterion("COURSE_NAME =", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotEqualTo(String value) {
            addCriterion("COURSE_NAME <>", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThan(String value) {
            addCriterion("COURSE_NAME >", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_NAME >=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThan(String value) {
            addCriterion("COURSE_NAME <", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThanOrEqualTo(String value) {
            addCriterion("COURSE_NAME <=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLike(String value) {
            addCriterion("COURSE_NAME like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotLike(String value) {
            addCriterion("COURSE_NAME not like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameIn(List<String> values) {
            addCriterion("COURSE_NAME in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotIn(List<String> values) {
            addCriterion("COURSE_NAME not in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameBetween(String value1, String value2) {
            addCriterion("COURSE_NAME between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotBetween(String value1, String value2) {
            addCriterion("COURSE_NAME not between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCoursePeopleNumIsNull() {
            addCriterion("COURSE_PEOPLE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andCoursePeopleNumIsNotNull() {
            addCriterion("COURSE_PEOPLE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andCoursePeopleNumEqualTo(String value) {
            addCriterion("COURSE_PEOPLE_NUM =", value, "coursePeopleNum");
            return (Criteria) this;
        }

        public Criteria andCoursePeopleNumNotEqualTo(String value) {
            addCriterion("COURSE_PEOPLE_NUM <>", value, "coursePeopleNum");
            return (Criteria) this;
        }

        public Criteria andCoursePeopleNumGreaterThan(String value) {
            addCriterion("COURSE_PEOPLE_NUM >", value, "coursePeopleNum");
            return (Criteria) this;
        }

        public Criteria andCoursePeopleNumGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_PEOPLE_NUM >=", value, "coursePeopleNum");
            return (Criteria) this;
        }

        public Criteria andCoursePeopleNumLessThan(String value) {
            addCriterion("COURSE_PEOPLE_NUM <", value, "coursePeopleNum");
            return (Criteria) this;
        }

        public Criteria andCoursePeopleNumLessThanOrEqualTo(String value) {
            addCriterion("COURSE_PEOPLE_NUM <=", value, "coursePeopleNum");
            return (Criteria) this;
        }

        public Criteria andCoursePeopleNumLike(String value) {
            addCriterion("COURSE_PEOPLE_NUM like", value, "coursePeopleNum");
            return (Criteria) this;
        }

        public Criteria andCoursePeopleNumNotLike(String value) {
            addCriterion("COURSE_PEOPLE_NUM not like", value, "coursePeopleNum");
            return (Criteria) this;
        }

        public Criteria andCoursePeopleNumIn(List<String> values) {
            addCriterion("COURSE_PEOPLE_NUM in", values, "coursePeopleNum");
            return (Criteria) this;
        }

        public Criteria andCoursePeopleNumNotIn(List<String> values) {
            addCriterion("COURSE_PEOPLE_NUM not in", values, "coursePeopleNum");
            return (Criteria) this;
        }

        public Criteria andCoursePeopleNumBetween(String value1, String value2) {
            addCriterion("COURSE_PEOPLE_NUM between", value1, value2, "coursePeopleNum");
            return (Criteria) this;
        }

        public Criteria andCoursePeopleNumNotBetween(String value1, String value2) {
            addCriterion("COURSE_PEOPLE_NUM not between", value1, value2, "coursePeopleNum");
            return (Criteria) this;
        }

        public Criteria andCourseAddressIsNull() {
            addCriterion("COURSE_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andCourseAddressIsNotNull() {
            addCriterion("COURSE_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andCourseAddressEqualTo(String value) {
            addCriterion("COURSE_ADDRESS =", value, "courseAddress");
            return (Criteria) this;
        }

        public Criteria andCourseAddressNotEqualTo(String value) {
            addCriterion("COURSE_ADDRESS <>", value, "courseAddress");
            return (Criteria) this;
        }

        public Criteria andCourseAddressGreaterThan(String value) {
            addCriterion("COURSE_ADDRESS >", value, "courseAddress");
            return (Criteria) this;
        }

        public Criteria andCourseAddressGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_ADDRESS >=", value, "courseAddress");
            return (Criteria) this;
        }

        public Criteria andCourseAddressLessThan(String value) {
            addCriterion("COURSE_ADDRESS <", value, "courseAddress");
            return (Criteria) this;
        }

        public Criteria andCourseAddressLessThanOrEqualTo(String value) {
            addCriterion("COURSE_ADDRESS <=", value, "courseAddress");
            return (Criteria) this;
        }

        public Criteria andCourseAddressLike(String value) {
            addCriterion("COURSE_ADDRESS like", value, "courseAddress");
            return (Criteria) this;
        }

        public Criteria andCourseAddressNotLike(String value) {
            addCriterion("COURSE_ADDRESS not like", value, "courseAddress");
            return (Criteria) this;
        }

        public Criteria andCourseAddressIn(List<String> values) {
            addCriterion("COURSE_ADDRESS in", values, "courseAddress");
            return (Criteria) this;
        }

        public Criteria andCourseAddressNotIn(List<String> values) {
            addCriterion("COURSE_ADDRESS not in", values, "courseAddress");
            return (Criteria) this;
        }

        public Criteria andCourseAddressBetween(String value1, String value2) {
            addCriterion("COURSE_ADDRESS between", value1, value2, "courseAddress");
            return (Criteria) this;
        }

        public Criteria andCourseAddressNotBetween(String value1, String value2) {
            addCriterion("COURSE_ADDRESS not between", value1, value2, "courseAddress");
            return (Criteria) this;
        }

        public Criteria andCourseDemoIsNull() {
            addCriterion("COURSE_DEMO is null");
            return (Criteria) this;
        }

        public Criteria andCourseDemoIsNotNull() {
            addCriterion("COURSE_DEMO is not null");
            return (Criteria) this;
        }

        public Criteria andCourseDemoEqualTo(String value) {
            addCriterion("COURSE_DEMO =", value, "courseDemo");
            return (Criteria) this;
        }

        public Criteria andCourseDemoNotEqualTo(String value) {
            addCriterion("COURSE_DEMO <>", value, "courseDemo");
            return (Criteria) this;
        }

        public Criteria andCourseDemoGreaterThan(String value) {
            addCriterion("COURSE_DEMO >", value, "courseDemo");
            return (Criteria) this;
        }

        public Criteria andCourseDemoGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_DEMO >=", value, "courseDemo");
            return (Criteria) this;
        }

        public Criteria andCourseDemoLessThan(String value) {
            addCriterion("COURSE_DEMO <", value, "courseDemo");
            return (Criteria) this;
        }

        public Criteria andCourseDemoLessThanOrEqualTo(String value) {
            addCriterion("COURSE_DEMO <=", value, "courseDemo");
            return (Criteria) this;
        }

        public Criteria andCourseDemoLike(String value) {
            addCriterion("COURSE_DEMO like", value, "courseDemo");
            return (Criteria) this;
        }

        public Criteria andCourseDemoNotLike(String value) {
            addCriterion("COURSE_DEMO not like", value, "courseDemo");
            return (Criteria) this;
        }

        public Criteria andCourseDemoIn(List<String> values) {
            addCriterion("COURSE_DEMO in", values, "courseDemo");
            return (Criteria) this;
        }

        public Criteria andCourseDemoNotIn(List<String> values) {
            addCriterion("COURSE_DEMO not in", values, "courseDemo");
            return (Criteria) this;
        }

        public Criteria andCourseDemoBetween(String value1, String value2) {
            addCriterion("COURSE_DEMO between", value1, value2, "courseDemo");
            return (Criteria) this;
        }

        public Criteria andCourseDemoNotBetween(String value1, String value2) {
            addCriterion("COURSE_DEMO not between", value1, value2, "courseDemo");
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

        public Criteria andIsReleasedIsNull() {
            addCriterion("IS_RELEASED is null");
            return (Criteria) this;
        }

        public Criteria andIsReleasedIsNotNull() {
            addCriterion("IS_RELEASED is not null");
            return (Criteria) this;
        }

        public Criteria andIsReleasedEqualTo(String value) {
            addCriterion("IS_RELEASED =", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedNotEqualTo(String value) {
            addCriterion("IS_RELEASED <>", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedGreaterThan(String value) {
            addCriterion("IS_RELEASED >", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedGreaterThanOrEqualTo(String value) {
            addCriterion("IS_RELEASED >=", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedLessThan(String value) {
            addCriterion("IS_RELEASED <", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedLessThanOrEqualTo(String value) {
            addCriterion("IS_RELEASED <=", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedLike(String value) {
            addCriterion("IS_RELEASED like", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedNotLike(String value) {
            addCriterion("IS_RELEASED not like", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedIn(List<String> values) {
            addCriterion("IS_RELEASED in", values, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedNotIn(List<String> values) {
            addCriterion("IS_RELEASED not in", values, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedBetween(String value1, String value2) {
            addCriterion("IS_RELEASED between", value1, value2, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedNotBetween(String value1, String value2) {
            addCriterion("IS_RELEASED not between", value1, value2, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsScoreReleasedIsNull() {
            addCriterion("IS_SCORE_RELEASED is null");
            return (Criteria) this;
        }

        public Criteria andIsScoreReleasedIsNotNull() {
            addCriterion("IS_SCORE_RELEASED is not null");
            return (Criteria) this;
        }

        public Criteria andIsScoreReleasedEqualTo(String value) {
            addCriterion("IS_SCORE_RELEASED =", value, "isScoreReleased");
            return (Criteria) this;
        }

        public Criteria andIsScoreReleasedNotEqualTo(String value) {
            addCriterion("IS_SCORE_RELEASED <>", value, "isScoreReleased");
            return (Criteria) this;
        }

        public Criteria andIsScoreReleasedGreaterThan(String value) {
            addCriterion("IS_SCORE_RELEASED >", value, "isScoreReleased");
            return (Criteria) this;
        }

        public Criteria andIsScoreReleasedGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SCORE_RELEASED >=", value, "isScoreReleased");
            return (Criteria) this;
        }

        public Criteria andIsScoreReleasedLessThan(String value) {
            addCriterion("IS_SCORE_RELEASED <", value, "isScoreReleased");
            return (Criteria) this;
        }

        public Criteria andIsScoreReleasedLessThanOrEqualTo(String value) {
            addCriterion("IS_SCORE_RELEASED <=", value, "isScoreReleased");
            return (Criteria) this;
        }

        public Criteria andIsScoreReleasedLike(String value) {
            addCriterion("IS_SCORE_RELEASED like", value, "isScoreReleased");
            return (Criteria) this;
        }

        public Criteria andIsScoreReleasedNotLike(String value) {
            addCriterion("IS_SCORE_RELEASED not like", value, "isScoreReleased");
            return (Criteria) this;
        }

        public Criteria andIsScoreReleasedIn(List<String> values) {
            addCriterion("IS_SCORE_RELEASED in", values, "isScoreReleased");
            return (Criteria) this;
        }

        public Criteria andIsScoreReleasedNotIn(List<String> values) {
            addCriterion("IS_SCORE_RELEASED not in", values, "isScoreReleased");
            return (Criteria) this;
        }

        public Criteria andIsScoreReleasedBetween(String value1, String value2) {
            addCriterion("IS_SCORE_RELEASED between", value1, value2, "isScoreReleased");
            return (Criteria) this;
        }

        public Criteria andIsScoreReleasedNotBetween(String value1, String value2) {
            addCriterion("IS_SCORE_RELEASED not between", value1, value2, "isScoreReleased");
            return (Criteria) this;
        }

        public Criteria andCodeInfoIsNull() {
            addCriterion("CODE_INFO is null");
            return (Criteria) this;
        }

        public Criteria andCodeInfoIsNotNull() {
            addCriterion("CODE_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andCodeInfoEqualTo(String value) {
            addCriterion("CODE_INFO =", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoNotEqualTo(String value) {
            addCriterion("CODE_INFO <>", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoGreaterThan(String value) {
            addCriterion("CODE_INFO >", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoGreaterThanOrEqualTo(String value) {
            addCriterion("CODE_INFO >=", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoLessThan(String value) {
            addCriterion("CODE_INFO <", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoLessThanOrEqualTo(String value) {
            addCriterion("CODE_INFO <=", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoLike(String value) {
            addCriterion("CODE_INFO like", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoNotLike(String value) {
            addCriterion("CODE_INFO not like", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoIn(List<String> values) {
            addCriterion("CODE_INFO in", values, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoNotIn(List<String> values) {
            addCriterion("CODE_INFO not in", values, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoBetween(String value1, String value2) {
            addCriterion("CODE_INFO between", value1, value2, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoNotBetween(String value1, String value2) {
            addCriterion("CODE_INFO not between", value1, value2, "codeInfo");
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