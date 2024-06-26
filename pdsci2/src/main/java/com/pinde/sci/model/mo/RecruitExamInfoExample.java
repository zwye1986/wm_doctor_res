package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class RecruitExamInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RecruitExamInfoExample() {
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

        public Criteria andMainFlowIsNull() {
            addCriterion("MAIN_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andMainFlowIsNotNull() {
            addCriterion("MAIN_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andMainFlowEqualTo(String value) {
            addCriterion("MAIN_FLOW =", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowNotEqualTo(String value) {
            addCriterion("MAIN_FLOW <>", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowGreaterThan(String value) {
            addCriterion("MAIN_FLOW >", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowGreaterThanOrEqualTo(String value) {
            addCriterion("MAIN_FLOW >=", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowLessThan(String value) {
            addCriterion("MAIN_FLOW <", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowLessThanOrEqualTo(String value) {
            addCriterion("MAIN_FLOW <=", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowLike(String value) {
            addCriterion("MAIN_FLOW like", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowNotLike(String value) {
            addCriterion("MAIN_FLOW not like", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowIn(List<String> values) {
            addCriterion("MAIN_FLOW in", values, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowNotIn(List<String> values) {
            addCriterion("MAIN_FLOW not in", values, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowBetween(String value1, String value2) {
            addCriterion("MAIN_FLOW between", value1, value2, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowNotBetween(String value1, String value2) {
            addCriterion("MAIN_FLOW not between", value1, value2, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andExamYearIsNull() {
            addCriterion("EXAM_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andExamYearIsNotNull() {
            addCriterion("EXAM_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andExamYearEqualTo(String value) {
            addCriterion("EXAM_YEAR =", value, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearNotEqualTo(String value) {
            addCriterion("EXAM_YEAR <>", value, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearGreaterThan(String value) {
            addCriterion("EXAM_YEAR >", value, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_YEAR >=", value, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearLessThan(String value) {
            addCriterion("EXAM_YEAR <", value, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearLessThanOrEqualTo(String value) {
            addCriterion("EXAM_YEAR <=", value, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearLike(String value) {
            addCriterion("EXAM_YEAR like", value, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearNotLike(String value) {
            addCriterion("EXAM_YEAR not like", value, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearIn(List<String> values) {
            addCriterion("EXAM_YEAR in", values, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearNotIn(List<String> values) {
            addCriterion("EXAM_YEAR not in", values, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearBetween(String value1, String value2) {
            addCriterion("EXAM_YEAR between", value1, value2, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearNotBetween(String value1, String value2) {
            addCriterion("EXAM_YEAR not between", value1, value2, "examYear");
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

        public Criteria andExamStartTimeIsNull() {
            addCriterion("EXAM_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeIsNotNull() {
            addCriterion("EXAM_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeEqualTo(String value) {
            addCriterion("EXAM_START_TIME =", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeNotEqualTo(String value) {
            addCriterion("EXAM_START_TIME <>", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeGreaterThan(String value) {
            addCriterion("EXAM_START_TIME >", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_START_TIME >=", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeLessThan(String value) {
            addCriterion("EXAM_START_TIME <", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeLessThanOrEqualTo(String value) {
            addCriterion("EXAM_START_TIME <=", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeLike(String value) {
            addCriterion("EXAM_START_TIME like", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeNotLike(String value) {
            addCriterion("EXAM_START_TIME not like", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeIn(List<String> values) {
            addCriterion("EXAM_START_TIME in", values, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeNotIn(List<String> values) {
            addCriterion("EXAM_START_TIME not in", values, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeBetween(String value1, String value2) {
            addCriterion("EXAM_START_TIME between", value1, value2, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeNotBetween(String value1, String value2) {
            addCriterion("EXAM_START_TIME not between", value1, value2, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeIsNull() {
            addCriterion("EXAM_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeIsNotNull() {
            addCriterion("EXAM_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeEqualTo(String value) {
            addCriterion("EXAM_END_TIME =", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeNotEqualTo(String value) {
            addCriterion("EXAM_END_TIME <>", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeGreaterThan(String value) {
            addCriterion("EXAM_END_TIME >", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_END_TIME >=", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeLessThan(String value) {
            addCriterion("EXAM_END_TIME <", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeLessThanOrEqualTo(String value) {
            addCriterion("EXAM_END_TIME <=", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeLike(String value) {
            addCriterion("EXAM_END_TIME like", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeNotLike(String value) {
            addCriterion("EXAM_END_TIME not like", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeIn(List<String> values) {
            addCriterion("EXAM_END_TIME in", values, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeNotIn(List<String> values) {
            addCriterion("EXAM_END_TIME not in", values, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeBetween(String value1, String value2) {
            addCriterion("EXAM_END_TIME between", value1, value2, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeNotBetween(String value1, String value2) {
            addCriterion("EXAM_END_TIME not between", value1, value2, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andInterviewStartTimeIsNull() {
            addCriterion("INTERVIEW_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andInterviewStartTimeIsNotNull() {
            addCriterion("INTERVIEW_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andInterviewStartTimeEqualTo(String value) {
            addCriterion("INTERVIEW_START_TIME =", value, "interviewStartTime");
            return (Criteria) this;
        }

        public Criteria andInterviewStartTimeNotEqualTo(String value) {
            addCriterion("INTERVIEW_START_TIME <>", value, "interviewStartTime");
            return (Criteria) this;
        }

        public Criteria andInterviewStartTimeGreaterThan(String value) {
            addCriterion("INTERVIEW_START_TIME >", value, "interviewStartTime");
            return (Criteria) this;
        }

        public Criteria andInterviewStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("INTERVIEW_START_TIME >=", value, "interviewStartTime");
            return (Criteria) this;
        }

        public Criteria andInterviewStartTimeLessThan(String value) {
            addCriterion("INTERVIEW_START_TIME <", value, "interviewStartTime");
            return (Criteria) this;
        }

        public Criteria andInterviewStartTimeLessThanOrEqualTo(String value) {
            addCriterion("INTERVIEW_START_TIME <=", value, "interviewStartTime");
            return (Criteria) this;
        }

        public Criteria andInterviewStartTimeLike(String value) {
            addCriterion("INTERVIEW_START_TIME like", value, "interviewStartTime");
            return (Criteria) this;
        }

        public Criteria andInterviewStartTimeNotLike(String value) {
            addCriterion("INTERVIEW_START_TIME not like", value, "interviewStartTime");
            return (Criteria) this;
        }

        public Criteria andInterviewStartTimeIn(List<String> values) {
            addCriterion("INTERVIEW_START_TIME in", values, "interviewStartTime");
            return (Criteria) this;
        }

        public Criteria andInterviewStartTimeNotIn(List<String> values) {
            addCriterion("INTERVIEW_START_TIME not in", values, "interviewStartTime");
            return (Criteria) this;
        }

        public Criteria andInterviewStartTimeBetween(String value1, String value2) {
            addCriterion("INTERVIEW_START_TIME between", value1, value2, "interviewStartTime");
            return (Criteria) this;
        }

        public Criteria andInterviewStartTimeNotBetween(String value1, String value2) {
            addCriterion("INTERVIEW_START_TIME not between", value1, value2, "interviewStartTime");
            return (Criteria) this;
        }

        public Criteria andInterviewEndTimeIsNull() {
            addCriterion("INTERVIEW_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andInterviewEndTimeIsNotNull() {
            addCriterion("INTERVIEW_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andInterviewEndTimeEqualTo(String value) {
            addCriterion("INTERVIEW_END_TIME =", value, "interviewEndTime");
            return (Criteria) this;
        }

        public Criteria andInterviewEndTimeNotEqualTo(String value) {
            addCriterion("INTERVIEW_END_TIME <>", value, "interviewEndTime");
            return (Criteria) this;
        }

        public Criteria andInterviewEndTimeGreaterThan(String value) {
            addCriterion("INTERVIEW_END_TIME >", value, "interviewEndTime");
            return (Criteria) this;
        }

        public Criteria andInterviewEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("INTERVIEW_END_TIME >=", value, "interviewEndTime");
            return (Criteria) this;
        }

        public Criteria andInterviewEndTimeLessThan(String value) {
            addCriterion("INTERVIEW_END_TIME <", value, "interviewEndTime");
            return (Criteria) this;
        }

        public Criteria andInterviewEndTimeLessThanOrEqualTo(String value) {
            addCriterion("INTERVIEW_END_TIME <=", value, "interviewEndTime");
            return (Criteria) this;
        }

        public Criteria andInterviewEndTimeLike(String value) {
            addCriterion("INTERVIEW_END_TIME like", value, "interviewEndTime");
            return (Criteria) this;
        }

        public Criteria andInterviewEndTimeNotLike(String value) {
            addCriterion("INTERVIEW_END_TIME not like", value, "interviewEndTime");
            return (Criteria) this;
        }

        public Criteria andInterviewEndTimeIn(List<String> values) {
            addCriterion("INTERVIEW_END_TIME in", values, "interviewEndTime");
            return (Criteria) this;
        }

        public Criteria andInterviewEndTimeNotIn(List<String> values) {
            addCriterion("INTERVIEW_END_TIME not in", values, "interviewEndTime");
            return (Criteria) this;
        }

        public Criteria andInterviewEndTimeBetween(String value1, String value2) {
            addCriterion("INTERVIEW_END_TIME between", value1, value2, "interviewEndTime");
            return (Criteria) this;
        }

        public Criteria andInterviewEndTimeNotBetween(String value1, String value2) {
            addCriterion("INTERVIEW_END_TIME not between", value1, value2, "interviewEndTime");
            return (Criteria) this;
        }

        public Criteria andInterviewAddressIsNull() {
            addCriterion("INTERVIEW_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andInterviewAddressIsNotNull() {
            addCriterion("INTERVIEW_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andInterviewAddressEqualTo(String value) {
            addCriterion("INTERVIEW_ADDRESS =", value, "interviewAddress");
            return (Criteria) this;
        }

        public Criteria andInterviewAddressNotEqualTo(String value) {
            addCriterion("INTERVIEW_ADDRESS <>", value, "interviewAddress");
            return (Criteria) this;
        }

        public Criteria andInterviewAddressGreaterThan(String value) {
            addCriterion("INTERVIEW_ADDRESS >", value, "interviewAddress");
            return (Criteria) this;
        }

        public Criteria andInterviewAddressGreaterThanOrEqualTo(String value) {
            addCriterion("INTERVIEW_ADDRESS >=", value, "interviewAddress");
            return (Criteria) this;
        }

        public Criteria andInterviewAddressLessThan(String value) {
            addCriterion("INTERVIEW_ADDRESS <", value, "interviewAddress");
            return (Criteria) this;
        }

        public Criteria andInterviewAddressLessThanOrEqualTo(String value) {
            addCriterion("INTERVIEW_ADDRESS <=", value, "interviewAddress");
            return (Criteria) this;
        }

        public Criteria andInterviewAddressLike(String value) {
            addCriterion("INTERVIEW_ADDRESS like", value, "interviewAddress");
            return (Criteria) this;
        }

        public Criteria andInterviewAddressNotLike(String value) {
            addCriterion("INTERVIEW_ADDRESS not like", value, "interviewAddress");
            return (Criteria) this;
        }

        public Criteria andInterviewAddressIn(List<String> values) {
            addCriterion("INTERVIEW_ADDRESS in", values, "interviewAddress");
            return (Criteria) this;
        }

        public Criteria andInterviewAddressNotIn(List<String> values) {
            addCriterion("INTERVIEW_ADDRESS not in", values, "interviewAddress");
            return (Criteria) this;
        }

        public Criteria andInterviewAddressBetween(String value1, String value2) {
            addCriterion("INTERVIEW_ADDRESS between", value1, value2, "interviewAddress");
            return (Criteria) this;
        }

        public Criteria andInterviewAddressNotBetween(String value1, String value2) {
            addCriterion("INTERVIEW_ADDRESS not between", value1, value2, "interviewAddress");
            return (Criteria) this;
        }

        public Criteria andInterviewDemoIsNull() {
            addCriterion("INTERVIEW_DEMO is null");
            return (Criteria) this;
        }

        public Criteria andInterviewDemoIsNotNull() {
            addCriterion("INTERVIEW_DEMO is not null");
            return (Criteria) this;
        }

        public Criteria andInterviewDemoEqualTo(String value) {
            addCriterion("INTERVIEW_DEMO =", value, "interviewDemo");
            return (Criteria) this;
        }

        public Criteria andInterviewDemoNotEqualTo(String value) {
            addCriterion("INTERVIEW_DEMO <>", value, "interviewDemo");
            return (Criteria) this;
        }

        public Criteria andInterviewDemoGreaterThan(String value) {
            addCriterion("INTERVIEW_DEMO >", value, "interviewDemo");
            return (Criteria) this;
        }

        public Criteria andInterviewDemoGreaterThanOrEqualTo(String value) {
            addCriterion("INTERVIEW_DEMO >=", value, "interviewDemo");
            return (Criteria) this;
        }

        public Criteria andInterviewDemoLessThan(String value) {
            addCriterion("INTERVIEW_DEMO <", value, "interviewDemo");
            return (Criteria) this;
        }

        public Criteria andInterviewDemoLessThanOrEqualTo(String value) {
            addCriterion("INTERVIEW_DEMO <=", value, "interviewDemo");
            return (Criteria) this;
        }

        public Criteria andInterviewDemoLike(String value) {
            addCriterion("INTERVIEW_DEMO like", value, "interviewDemo");
            return (Criteria) this;
        }

        public Criteria andInterviewDemoNotLike(String value) {
            addCriterion("INTERVIEW_DEMO not like", value, "interviewDemo");
            return (Criteria) this;
        }

        public Criteria andInterviewDemoIn(List<String> values) {
            addCriterion("INTERVIEW_DEMO in", values, "interviewDemo");
            return (Criteria) this;
        }

        public Criteria andInterviewDemoNotIn(List<String> values) {
            addCriterion("INTERVIEW_DEMO not in", values, "interviewDemo");
            return (Criteria) this;
        }

        public Criteria andInterviewDemoBetween(String value1, String value2) {
            addCriterion("INTERVIEW_DEMO between", value1, value2, "interviewDemo");
            return (Criteria) this;
        }

        public Criteria andInterviewDemoNotBetween(String value1, String value2) {
            addCriterion("INTERVIEW_DEMO not between", value1, value2, "interviewDemo");
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