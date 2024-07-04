package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class NyzlAdmissionStudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NyzlAdmissionStudentExample() {
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

        public Criteria andRecruitYearIsNull() {
            addCriterion("RECRUIT_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andRecruitYearIsNotNull() {
            addCriterion("RECRUIT_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitYearEqualTo(String value) {
            addCriterion("RECRUIT_YEAR =", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearNotEqualTo(String value) {
            addCriterion("RECRUIT_YEAR <>", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearGreaterThan(String value) {
            addCriterion("RECRUIT_YEAR >", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_YEAR >=", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearLessThan(String value) {
            addCriterion("RECRUIT_YEAR <", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_YEAR <=", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearLike(String value) {
            addCriterion("RECRUIT_YEAR like", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearNotLike(String value) {
            addCriterion("RECRUIT_YEAR not like", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearIn(List<String> values) {
            addCriterion("RECRUIT_YEAR in", values, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearNotIn(List<String> values) {
            addCriterion("RECRUIT_YEAR not in", values, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearBetween(String value1, String value2) {
            addCriterion("RECRUIT_YEAR between", value1, value2, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_YEAR not between", value1, value2, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andStuSignIsNull() {
            addCriterion("STU_SIGN is null");
            return (Criteria) this;
        }

        public Criteria andStuSignIsNotNull() {
            addCriterion("STU_SIGN is not null");
            return (Criteria) this;
        }

        public Criteria andStuSignEqualTo(String value) {
            addCriterion("STU_SIGN =", value, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignNotEqualTo(String value) {
            addCriterion("STU_SIGN <>", value, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignGreaterThan(String value) {
            addCriterion("STU_SIGN >", value, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignGreaterThanOrEqualTo(String value) {
            addCriterion("STU_SIGN >=", value, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignLessThan(String value) {
            addCriterion("STU_SIGN <", value, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignLessThanOrEqualTo(String value) {
            addCriterion("STU_SIGN <=", value, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignLike(String value) {
            addCriterion("STU_SIGN like", value, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignNotLike(String value) {
            addCriterion("STU_SIGN not like", value, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignIn(List<String> values) {
            addCriterion("STU_SIGN in", values, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignNotIn(List<String> values) {
            addCriterion("STU_SIGN not in", values, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignBetween(String value1, String value2) {
            addCriterion("STU_SIGN between", value1, value2, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignNotBetween(String value1, String value2) {
            addCriterion("STU_SIGN not between", value1, value2, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuNoIsNull() {
            addCriterion("STU_NO is null");
            return (Criteria) this;
        }

        public Criteria andStuNoIsNotNull() {
            addCriterion("STU_NO is not null");
            return (Criteria) this;
        }

        public Criteria andStuNoEqualTo(String value) {
            addCriterion("STU_NO =", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotEqualTo(String value) {
            addCriterion("STU_NO <>", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoGreaterThan(String value) {
            addCriterion("STU_NO >", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoGreaterThanOrEqualTo(String value) {
            addCriterion("STU_NO >=", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoLessThan(String value) {
            addCriterion("STU_NO <", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoLessThanOrEqualTo(String value) {
            addCriterion("STU_NO <=", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoLike(String value) {
            addCriterion("STU_NO like", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotLike(String value) {
            addCriterion("STU_NO not like", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoIn(List<String> values) {
            addCriterion("STU_NO in", values, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotIn(List<String> values) {
            addCriterion("STU_NO not in", values, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoBetween(String value1, String value2) {
            addCriterion("STU_NO between", value1, value2, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotBetween(String value1, String value2) {
            addCriterion("STU_NO not between", value1, value2, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNameIsNull() {
            addCriterion("STU_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStuNameIsNotNull() {
            addCriterion("STU_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStuNameEqualTo(String value) {
            addCriterion("STU_NAME =", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotEqualTo(String value) {
            addCriterion("STU_NAME <>", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameGreaterThan(String value) {
            addCriterion("STU_NAME >", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameGreaterThanOrEqualTo(String value) {
            addCriterion("STU_NAME >=", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameLessThan(String value) {
            addCriterion("STU_NAME <", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameLessThanOrEqualTo(String value) {
            addCriterion("STU_NAME <=", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameLike(String value) {
            addCriterion("STU_NAME like", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotLike(String value) {
            addCriterion("STU_NAME not like", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameIn(List<String> values) {
            addCriterion("STU_NAME in", values, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotIn(List<String> values) {
            addCriterion("STU_NAME not in", values, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameBetween(String value1, String value2) {
            addCriterion("STU_NAME between", value1, value2, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotBetween(String value1, String value2) {
            addCriterion("STU_NAME not between", value1, value2, "stuName");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNull() {
            addCriterion("CARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNotNull() {
            addCriterion("CARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCardNoEqualTo(String value) {
            addCriterion("CARD_NO =", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotEqualTo(String value) {
            addCriterion("CARD_NO <>", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThan(String value) {
            addCriterion("CARD_NO >", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_NO >=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThan(String value) {
            addCriterion("CARD_NO <", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThanOrEqualTo(String value) {
            addCriterion("CARD_NO <=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLike(String value) {
            addCriterion("CARD_NO like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotLike(String value) {
            addCriterion("CARD_NO not like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoIn(List<String> values) {
            addCriterion("CARD_NO in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotIn(List<String> values) {
            addCriterion("CARD_NO not in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoBetween(String value1, String value2) {
            addCriterion("CARD_NO between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotBetween(String value1, String value2) {
            addCriterion("CARD_NO not between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andSpeIdIsNull() {
            addCriterion("SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSpeIdIsNotNull() {
            addCriterion("SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSpeIdEqualTo(String value) {
            addCriterion("SPE_ID =", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotEqualTo(String value) {
            addCriterion("SPE_ID <>", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdGreaterThan(String value) {
            addCriterion("SPE_ID >", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_ID >=", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLessThan(String value) {
            addCriterion("SPE_ID <", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLessThanOrEqualTo(String value) {
            addCriterion("SPE_ID <=", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLike(String value) {
            addCriterion("SPE_ID like", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotLike(String value) {
            addCriterion("SPE_ID not like", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdIn(List<String> values) {
            addCriterion("SPE_ID in", values, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotIn(List<String> values) {
            addCriterion("SPE_ID not in", values, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdBetween(String value1, String value2) {
            addCriterion("SPE_ID between", value1, value2, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotBetween(String value1, String value2) {
            addCriterion("SPE_ID not between", value1, value2, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeNameIsNull() {
            addCriterion("SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSpeNameIsNotNull() {
            addCriterion("SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSpeNameEqualTo(String value) {
            addCriterion("SPE_NAME =", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotEqualTo(String value) {
            addCriterion("SPE_NAME <>", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameGreaterThan(String value) {
            addCriterion("SPE_NAME >", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_NAME >=", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLessThan(String value) {
            addCriterion("SPE_NAME <", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLessThanOrEqualTo(String value) {
            addCriterion("SPE_NAME <=", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLike(String value) {
            addCriterion("SPE_NAME like", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotLike(String value) {
            addCriterion("SPE_NAME not like", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameIn(List<String> values) {
            addCriterion("SPE_NAME in", values, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotIn(List<String> values) {
            addCriterion("SPE_NAME not in", values, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameBetween(String value1, String value2) {
            addCriterion("SPE_NAME between", value1, value2, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotBetween(String value1, String value2) {
            addCriterion("SPE_NAME not between", value1, value2, "speName");
            return (Criteria) this;
        }

        public Criteria andTestGradeIsNull() {
            addCriterion("TEST_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andTestGradeIsNotNull() {
            addCriterion("TEST_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andTestGradeEqualTo(String value) {
            addCriterion("TEST_GRADE =", value, "testGrade");
            return (Criteria) this;
        }

        public Criteria andTestGradeNotEqualTo(String value) {
            addCriterion("TEST_GRADE <>", value, "testGrade");
            return (Criteria) this;
        }

        public Criteria andTestGradeGreaterThan(String value) {
            addCriterion("TEST_GRADE >", value, "testGrade");
            return (Criteria) this;
        }

        public Criteria andTestGradeGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_GRADE >=", value, "testGrade");
            return (Criteria) this;
        }

        public Criteria andTestGradeLessThan(String value) {
            addCriterion("TEST_GRADE <", value, "testGrade");
            return (Criteria) this;
        }

        public Criteria andTestGradeLessThanOrEqualTo(String value) {
            addCriterion("TEST_GRADE <=", value, "testGrade");
            return (Criteria) this;
        }

        public Criteria andTestGradeLike(String value) {
            addCriterion("TEST_GRADE like", value, "testGrade");
            return (Criteria) this;
        }

        public Criteria andTestGradeNotLike(String value) {
            addCriterion("TEST_GRADE not like", value, "testGrade");
            return (Criteria) this;
        }

        public Criteria andTestGradeIn(List<String> values) {
            addCriterion("TEST_GRADE in", values, "testGrade");
            return (Criteria) this;
        }

        public Criteria andTestGradeNotIn(List<String> values) {
            addCriterion("TEST_GRADE not in", values, "testGrade");
            return (Criteria) this;
        }

        public Criteria andTestGradeBetween(String value1, String value2) {
            addCriterion("TEST_GRADE between", value1, value2, "testGrade");
            return (Criteria) this;
        }

        public Criteria andTestGradeNotBetween(String value1, String value2) {
            addCriterion("TEST_GRADE not between", value1, value2, "testGrade");
            return (Criteria) this;
        }

        public Criteria andRetestGradeIsNull() {
            addCriterion("RETEST_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andRetestGradeIsNotNull() {
            addCriterion("RETEST_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andRetestGradeEqualTo(String value) {
            addCriterion("RETEST_GRADE =", value, "retestGrade");
            return (Criteria) this;
        }

        public Criteria andRetestGradeNotEqualTo(String value) {
            addCriterion("RETEST_GRADE <>", value, "retestGrade");
            return (Criteria) this;
        }

        public Criteria andRetestGradeGreaterThan(String value) {
            addCriterion("RETEST_GRADE >", value, "retestGrade");
            return (Criteria) this;
        }

        public Criteria andRetestGradeGreaterThanOrEqualTo(String value) {
            addCriterion("RETEST_GRADE >=", value, "retestGrade");
            return (Criteria) this;
        }

        public Criteria andRetestGradeLessThan(String value) {
            addCriterion("RETEST_GRADE <", value, "retestGrade");
            return (Criteria) this;
        }

        public Criteria andRetestGradeLessThanOrEqualTo(String value) {
            addCriterion("RETEST_GRADE <=", value, "retestGrade");
            return (Criteria) this;
        }

        public Criteria andRetestGradeLike(String value) {
            addCriterion("RETEST_GRADE like", value, "retestGrade");
            return (Criteria) this;
        }

        public Criteria andRetestGradeNotLike(String value) {
            addCriterion("RETEST_GRADE not like", value, "retestGrade");
            return (Criteria) this;
        }

        public Criteria andRetestGradeIn(List<String> values) {
            addCriterion("RETEST_GRADE in", values, "retestGrade");
            return (Criteria) this;
        }

        public Criteria andRetestGradeNotIn(List<String> values) {
            addCriterion("RETEST_GRADE not in", values, "retestGrade");
            return (Criteria) this;
        }

        public Criteria andRetestGradeBetween(String value1, String value2) {
            addCriterion("RETEST_GRADE between", value1, value2, "retestGrade");
            return (Criteria) this;
        }

        public Criteria andRetestGradeNotBetween(String value1, String value2) {
            addCriterion("RETEST_GRADE not between", value1, value2, "retestGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeIsNull() {
            addCriterion("TOTAL_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andTotalGradeIsNotNull() {
            addCriterion("TOTAL_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andTotalGradeEqualTo(String value) {
            addCriterion("TOTAL_GRADE =", value, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeNotEqualTo(String value) {
            addCriterion("TOTAL_GRADE <>", value, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeGreaterThan(String value) {
            addCriterion("TOTAL_GRADE >", value, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeGreaterThanOrEqualTo(String value) {
            addCriterion("TOTAL_GRADE >=", value, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeLessThan(String value) {
            addCriterion("TOTAL_GRADE <", value, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeLessThanOrEqualTo(String value) {
            addCriterion("TOTAL_GRADE <=", value, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeLike(String value) {
            addCriterion("TOTAL_GRADE like", value, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeNotLike(String value) {
            addCriterion("TOTAL_GRADE not like", value, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeIn(List<String> values) {
            addCriterion("TOTAL_GRADE in", values, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeNotIn(List<String> values) {
            addCriterion("TOTAL_GRADE not in", values, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeBetween(String value1, String value2) {
            addCriterion("TOTAL_GRADE between", value1, value2, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTotalGradeNotBetween(String value1, String value2) {
            addCriterion("TOTAL_GRADE not between", value1, value2, "totalGrade");
            return (Criteria) this;
        }

        public Criteria andTeaWorkUnitIsNull() {
            addCriterion("TEA_WORK_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andTeaWorkUnitIsNotNull() {
            addCriterion("TEA_WORK_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andTeaWorkUnitEqualTo(String value) {
            addCriterion("TEA_WORK_UNIT =", value, "teaWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTeaWorkUnitNotEqualTo(String value) {
            addCriterion("TEA_WORK_UNIT <>", value, "teaWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTeaWorkUnitGreaterThan(String value) {
            addCriterion("TEA_WORK_UNIT >", value, "teaWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTeaWorkUnitGreaterThanOrEqualTo(String value) {
            addCriterion("TEA_WORK_UNIT >=", value, "teaWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTeaWorkUnitLessThan(String value) {
            addCriterion("TEA_WORK_UNIT <", value, "teaWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTeaWorkUnitLessThanOrEqualTo(String value) {
            addCriterion("TEA_WORK_UNIT <=", value, "teaWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTeaWorkUnitLike(String value) {
            addCriterion("TEA_WORK_UNIT like", value, "teaWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTeaWorkUnitNotLike(String value) {
            addCriterion("TEA_WORK_UNIT not like", value, "teaWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTeaWorkUnitIn(List<String> values) {
            addCriterion("TEA_WORK_UNIT in", values, "teaWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTeaWorkUnitNotIn(List<String> values) {
            addCriterion("TEA_WORK_UNIT not in", values, "teaWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTeaWorkUnitBetween(String value1, String value2) {
            addCriterion("TEA_WORK_UNIT between", value1, value2, "teaWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTeaWorkUnitNotBetween(String value1, String value2) {
            addCriterion("TEA_WORK_UNIT not between", value1, value2, "teaWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTeaNameIsNull() {
            addCriterion("TEA_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTeaNameIsNotNull() {
            addCriterion("TEA_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTeaNameEqualTo(String value) {
            addCriterion("TEA_NAME =", value, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameNotEqualTo(String value) {
            addCriterion("TEA_NAME <>", value, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameGreaterThan(String value) {
            addCriterion("TEA_NAME >", value, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEA_NAME >=", value, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameLessThan(String value) {
            addCriterion("TEA_NAME <", value, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameLessThanOrEqualTo(String value) {
            addCriterion("TEA_NAME <=", value, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameLike(String value) {
            addCriterion("TEA_NAME like", value, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameNotLike(String value) {
            addCriterion("TEA_NAME not like", value, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameIn(List<String> values) {
            addCriterion("TEA_NAME in", values, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameNotIn(List<String> values) {
            addCriterion("TEA_NAME not in", values, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameBetween(String value1, String value2) {
            addCriterion("TEA_NAME between", value1, value2, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaNameNotBetween(String value1, String value2) {
            addCriterion("TEA_NAME not between", value1, value2, "teaName");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionIdIsNull() {
            addCriterion("TEA_DIRECTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionIdIsNotNull() {
            addCriterion("TEA_DIRECTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionIdEqualTo(String value) {
            addCriterion("TEA_DIRECTION_ID =", value, "teaDirectionId");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionIdNotEqualTo(String value) {
            addCriterion("TEA_DIRECTION_ID <>", value, "teaDirectionId");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionIdGreaterThan(String value) {
            addCriterion("TEA_DIRECTION_ID >", value, "teaDirectionId");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionIdGreaterThanOrEqualTo(String value) {
            addCriterion("TEA_DIRECTION_ID >=", value, "teaDirectionId");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionIdLessThan(String value) {
            addCriterion("TEA_DIRECTION_ID <", value, "teaDirectionId");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionIdLessThanOrEqualTo(String value) {
            addCriterion("TEA_DIRECTION_ID <=", value, "teaDirectionId");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionIdLike(String value) {
            addCriterion("TEA_DIRECTION_ID like", value, "teaDirectionId");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionIdNotLike(String value) {
            addCriterion("TEA_DIRECTION_ID not like", value, "teaDirectionId");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionIdIn(List<String> values) {
            addCriterion("TEA_DIRECTION_ID in", values, "teaDirectionId");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionIdNotIn(List<String> values) {
            addCriterion("TEA_DIRECTION_ID not in", values, "teaDirectionId");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionIdBetween(String value1, String value2) {
            addCriterion("TEA_DIRECTION_ID between", value1, value2, "teaDirectionId");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionIdNotBetween(String value1, String value2) {
            addCriterion("TEA_DIRECTION_ID not between", value1, value2, "teaDirectionId");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionNameIsNull() {
            addCriterion("TEA_DIRECTION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionNameIsNotNull() {
            addCriterion("TEA_DIRECTION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionNameEqualTo(String value) {
            addCriterion("TEA_DIRECTION_NAME =", value, "teaDirectionName");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionNameNotEqualTo(String value) {
            addCriterion("TEA_DIRECTION_NAME <>", value, "teaDirectionName");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionNameGreaterThan(String value) {
            addCriterion("TEA_DIRECTION_NAME >", value, "teaDirectionName");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEA_DIRECTION_NAME >=", value, "teaDirectionName");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionNameLessThan(String value) {
            addCriterion("TEA_DIRECTION_NAME <", value, "teaDirectionName");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionNameLessThanOrEqualTo(String value) {
            addCriterion("TEA_DIRECTION_NAME <=", value, "teaDirectionName");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionNameLike(String value) {
            addCriterion("TEA_DIRECTION_NAME like", value, "teaDirectionName");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionNameNotLike(String value) {
            addCriterion("TEA_DIRECTION_NAME not like", value, "teaDirectionName");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionNameIn(List<String> values) {
            addCriterion("TEA_DIRECTION_NAME in", values, "teaDirectionName");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionNameNotIn(List<String> values) {
            addCriterion("TEA_DIRECTION_NAME not in", values, "teaDirectionName");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionNameBetween(String value1, String value2) {
            addCriterion("TEA_DIRECTION_NAME between", value1, value2, "teaDirectionName");
            return (Criteria) this;
        }

        public Criteria andTeaDirectionNameNotBetween(String value1, String value2) {
            addCriterion("TEA_DIRECTION_NAME not between", value1, value2, "teaDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuDirectionIdIsNull() {
            addCriterion("STU_DIRECTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andStuDirectionIdIsNotNull() {
            addCriterion("STU_DIRECTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStuDirectionIdEqualTo(String value) {
            addCriterion("STU_DIRECTION_ID =", value, "stuDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuDirectionIdNotEqualTo(String value) {
            addCriterion("STU_DIRECTION_ID <>", value, "stuDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuDirectionIdGreaterThan(String value) {
            addCriterion("STU_DIRECTION_ID >", value, "stuDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuDirectionIdGreaterThanOrEqualTo(String value) {
            addCriterion("STU_DIRECTION_ID >=", value, "stuDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuDirectionIdLessThan(String value) {
            addCriterion("STU_DIRECTION_ID <", value, "stuDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuDirectionIdLessThanOrEqualTo(String value) {
            addCriterion("STU_DIRECTION_ID <=", value, "stuDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuDirectionIdLike(String value) {
            addCriterion("STU_DIRECTION_ID like", value, "stuDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuDirectionIdNotLike(String value) {
            addCriterion("STU_DIRECTION_ID not like", value, "stuDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuDirectionIdIn(List<String> values) {
            addCriterion("STU_DIRECTION_ID in", values, "stuDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuDirectionIdNotIn(List<String> values) {
            addCriterion("STU_DIRECTION_ID not in", values, "stuDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuDirectionIdBetween(String value1, String value2) {
            addCriterion("STU_DIRECTION_ID between", value1, value2, "stuDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuDirectionIdNotBetween(String value1, String value2) {
            addCriterion("STU_DIRECTION_ID not between", value1, value2, "stuDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuDirectionNameIsNull() {
            addCriterion("STU_DIRECTION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStuDirectionNameIsNotNull() {
            addCriterion("STU_DIRECTION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStuDirectionNameEqualTo(String value) {
            addCriterion("STU_DIRECTION_NAME =", value, "stuDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuDirectionNameNotEqualTo(String value) {
            addCriterion("STU_DIRECTION_NAME <>", value, "stuDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuDirectionNameGreaterThan(String value) {
            addCriterion("STU_DIRECTION_NAME >", value, "stuDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuDirectionNameGreaterThanOrEqualTo(String value) {
            addCriterion("STU_DIRECTION_NAME >=", value, "stuDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuDirectionNameLessThan(String value) {
            addCriterion("STU_DIRECTION_NAME <", value, "stuDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuDirectionNameLessThanOrEqualTo(String value) {
            addCriterion("STU_DIRECTION_NAME <=", value, "stuDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuDirectionNameLike(String value) {
            addCriterion("STU_DIRECTION_NAME like", value, "stuDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuDirectionNameNotLike(String value) {
            addCriterion("STU_DIRECTION_NAME not like", value, "stuDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuDirectionNameIn(List<String> values) {
            addCriterion("STU_DIRECTION_NAME in", values, "stuDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuDirectionNameNotIn(List<String> values) {
            addCriterion("STU_DIRECTION_NAME not in", values, "stuDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuDirectionNameBetween(String value1, String value2) {
            addCriterion("STU_DIRECTION_NAME between", value1, value2, "stuDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuDirectionNameNotBetween(String value1, String value2) {
            addCriterion("STU_DIRECTION_NAME not between", value1, value2, "stuDirectionName");
            return (Criteria) this;
        }

        public Criteria andGroupRankingIsNull() {
            addCriterion("GROUP_RANKING is null");
            return (Criteria) this;
        }

        public Criteria andGroupRankingIsNotNull() {
            addCriterion("GROUP_RANKING is not null");
            return (Criteria) this;
        }

        public Criteria andGroupRankingEqualTo(String value) {
            addCriterion("GROUP_RANKING =", value, "groupRanking");
            return (Criteria) this;
        }

        public Criteria andGroupRankingNotEqualTo(String value) {
            addCriterion("GROUP_RANKING <>", value, "groupRanking");
            return (Criteria) this;
        }

        public Criteria andGroupRankingGreaterThan(String value) {
            addCriterion("GROUP_RANKING >", value, "groupRanking");
            return (Criteria) this;
        }

        public Criteria andGroupRankingGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_RANKING >=", value, "groupRanking");
            return (Criteria) this;
        }

        public Criteria andGroupRankingLessThan(String value) {
            addCriterion("GROUP_RANKING <", value, "groupRanking");
            return (Criteria) this;
        }

        public Criteria andGroupRankingLessThanOrEqualTo(String value) {
            addCriterion("GROUP_RANKING <=", value, "groupRanking");
            return (Criteria) this;
        }

        public Criteria andGroupRankingLike(String value) {
            addCriterion("GROUP_RANKING like", value, "groupRanking");
            return (Criteria) this;
        }

        public Criteria andGroupRankingNotLike(String value) {
            addCriterion("GROUP_RANKING not like", value, "groupRanking");
            return (Criteria) this;
        }

        public Criteria andGroupRankingIn(List<String> values) {
            addCriterion("GROUP_RANKING in", values, "groupRanking");
            return (Criteria) this;
        }

        public Criteria andGroupRankingNotIn(List<String> values) {
            addCriterion("GROUP_RANKING not in", values, "groupRanking");
            return (Criteria) this;
        }

        public Criteria andGroupRankingBetween(String value1, String value2) {
            addCriterion("GROUP_RANKING between", value1, value2, "groupRanking");
            return (Criteria) this;
        }

        public Criteria andGroupRankingNotBetween(String value1, String value2) {
            addCriterion("GROUP_RANKING not between", value1, value2, "groupRanking");
            return (Criteria) this;
        }

        public Criteria andAdmissionFlagIsNull() {
            addCriterion("ADMISSION_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andAdmissionFlagIsNotNull() {
            addCriterion("ADMISSION_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andAdmissionFlagEqualTo(String value) {
            addCriterion("ADMISSION_FLAG =", value, "admissionFlag");
            return (Criteria) this;
        }

        public Criteria andAdmissionFlagNotEqualTo(String value) {
            addCriterion("ADMISSION_FLAG <>", value, "admissionFlag");
            return (Criteria) this;
        }

        public Criteria andAdmissionFlagGreaterThan(String value) {
            addCriterion("ADMISSION_FLAG >", value, "admissionFlag");
            return (Criteria) this;
        }

        public Criteria andAdmissionFlagGreaterThanOrEqualTo(String value) {
            addCriterion("ADMISSION_FLAG >=", value, "admissionFlag");
            return (Criteria) this;
        }

        public Criteria andAdmissionFlagLessThan(String value) {
            addCriterion("ADMISSION_FLAG <", value, "admissionFlag");
            return (Criteria) this;
        }

        public Criteria andAdmissionFlagLessThanOrEqualTo(String value) {
            addCriterion("ADMISSION_FLAG <=", value, "admissionFlag");
            return (Criteria) this;
        }

        public Criteria andAdmissionFlagLike(String value) {
            addCriterion("ADMISSION_FLAG like", value, "admissionFlag");
            return (Criteria) this;
        }

        public Criteria andAdmissionFlagNotLike(String value) {
            addCriterion("ADMISSION_FLAG not like", value, "admissionFlag");
            return (Criteria) this;
        }

        public Criteria andAdmissionFlagIn(List<String> values) {
            addCriterion("ADMISSION_FLAG in", values, "admissionFlag");
            return (Criteria) this;
        }

        public Criteria andAdmissionFlagNotIn(List<String> values) {
            addCriterion("ADMISSION_FLAG not in", values, "admissionFlag");
            return (Criteria) this;
        }

        public Criteria andAdmissionFlagBetween(String value1, String value2) {
            addCriterion("ADMISSION_FLAG between", value1, value2, "admissionFlag");
            return (Criteria) this;
        }

        public Criteria andAdmissionFlagNotBetween(String value1, String value2) {
            addCriterion("ADMISSION_FLAG not between", value1, value2, "admissionFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdIsNull() {
            addCriterion("DEGREE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdIsNotNull() {
            addCriterion("DEGREE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdEqualTo(String value) {
            addCriterion("DEGREE_TYPE_ID =", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdNotEqualTo(String value) {
            addCriterion("DEGREE_TYPE_ID <>", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdGreaterThan(String value) {
            addCriterion("DEGREE_TYPE_ID >", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_TYPE_ID >=", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdLessThan(String value) {
            addCriterion("DEGREE_TYPE_ID <", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_TYPE_ID <=", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdLike(String value) {
            addCriterion("DEGREE_TYPE_ID like", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdNotLike(String value) {
            addCriterion("DEGREE_TYPE_ID not like", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdIn(List<String> values) {
            addCriterion("DEGREE_TYPE_ID in", values, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdNotIn(List<String> values) {
            addCriterion("DEGREE_TYPE_ID not in", values, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdBetween(String value1, String value2) {
            addCriterion("DEGREE_TYPE_ID between", value1, value2, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdNotBetween(String value1, String value2) {
            addCriterion("DEGREE_TYPE_ID not between", value1, value2, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameIsNull() {
            addCriterion("DEGREE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameIsNotNull() {
            addCriterion("DEGREE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameEqualTo(String value) {
            addCriterion("DEGREE_TYPE_NAME =", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameNotEqualTo(String value) {
            addCriterion("DEGREE_TYPE_NAME <>", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameGreaterThan(String value) {
            addCriterion("DEGREE_TYPE_NAME >", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_TYPE_NAME >=", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameLessThan(String value) {
            addCriterion("DEGREE_TYPE_NAME <", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_TYPE_NAME <=", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameLike(String value) {
            addCriterion("DEGREE_TYPE_NAME like", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameNotLike(String value) {
            addCriterion("DEGREE_TYPE_NAME not like", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameIn(List<String> values) {
            addCriterion("DEGREE_TYPE_NAME in", values, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameNotIn(List<String> values) {
            addCriterion("DEGREE_TYPE_NAME not in", values, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameBetween(String value1, String value2) {
            addCriterion("DEGREE_TYPE_NAME between", value1, value2, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameNotBetween(String value1, String value2) {
            addCriterion("DEGREE_TYPE_NAME not between", value1, value2, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andSwapFlagIsNull() {
            addCriterion("SWAP_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andSwapFlagIsNotNull() {
            addCriterion("SWAP_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andSwapFlagEqualTo(String value) {
            addCriterion("SWAP_FLAG =", value, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagNotEqualTo(String value) {
            addCriterion("SWAP_FLAG <>", value, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagGreaterThan(String value) {
            addCriterion("SWAP_FLAG >", value, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SWAP_FLAG >=", value, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagLessThan(String value) {
            addCriterion("SWAP_FLAG <", value, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagLessThanOrEqualTo(String value) {
            addCriterion("SWAP_FLAG <=", value, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagLike(String value) {
            addCriterion("SWAP_FLAG like", value, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagNotLike(String value) {
            addCriterion("SWAP_FLAG not like", value, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagIn(List<String> values) {
            addCriterion("SWAP_FLAG in", values, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagNotIn(List<String> values) {
            addCriterion("SWAP_FLAG not in", values, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagBetween(String value1, String value2) {
            addCriterion("SWAP_FLAG between", value1, value2, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagNotBetween(String value1, String value2) {
            addCriterion("SWAP_FLAG not between", value1, value2, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdIsNull() {
            addCriterion("SWAP_BATCH_ID is null");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdIsNotNull() {
            addCriterion("SWAP_BATCH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdEqualTo(String value) {
            addCriterion("SWAP_BATCH_ID =", value, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdNotEqualTo(String value) {
            addCriterion("SWAP_BATCH_ID <>", value, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdGreaterThan(String value) {
            addCriterion("SWAP_BATCH_ID >", value, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdGreaterThanOrEqualTo(String value) {
            addCriterion("SWAP_BATCH_ID >=", value, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdLessThan(String value) {
            addCriterion("SWAP_BATCH_ID <", value, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdLessThanOrEqualTo(String value) {
            addCriterion("SWAP_BATCH_ID <=", value, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdLike(String value) {
            addCriterion("SWAP_BATCH_ID like", value, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdNotLike(String value) {
            addCriterion("SWAP_BATCH_ID not like", value, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdIn(List<String> values) {
            addCriterion("SWAP_BATCH_ID in", values, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdNotIn(List<String> values) {
            addCriterion("SWAP_BATCH_ID not in", values, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdBetween(String value1, String value2) {
            addCriterion("SWAP_BATCH_ID between", value1, value2, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdNotBetween(String value1, String value2) {
            addCriterion("SWAP_BATCH_ID not between", value1, value2, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameIsNull() {
            addCriterion("SWAP_BATCH_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameIsNotNull() {
            addCriterion("SWAP_BATCH_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameEqualTo(String value) {
            addCriterion("SWAP_BATCH_NAME =", value, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameNotEqualTo(String value) {
            addCriterion("SWAP_BATCH_NAME <>", value, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameGreaterThan(String value) {
            addCriterion("SWAP_BATCH_NAME >", value, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameGreaterThanOrEqualTo(String value) {
            addCriterion("SWAP_BATCH_NAME >=", value, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameLessThan(String value) {
            addCriterion("SWAP_BATCH_NAME <", value, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameLessThanOrEqualTo(String value) {
            addCriterion("SWAP_BATCH_NAME <=", value, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameLike(String value) {
            addCriterion("SWAP_BATCH_NAME like", value, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameNotLike(String value) {
            addCriterion("SWAP_BATCH_NAME not like", value, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameIn(List<String> values) {
            addCriterion("SWAP_BATCH_NAME in", values, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameNotIn(List<String> values) {
            addCriterion("SWAP_BATCH_NAME not in", values, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameBetween(String value1, String value2) {
            addCriterion("SWAP_BATCH_NAME between", value1, value2, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameNotBetween(String value1, String value2) {
            addCriterion("SWAP_BATCH_NAME not between", value1, value2, "swapBatchName");
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

        public Criteria andEducationTypeIdIsNull() {
            addCriterion("EDUCATION_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdIsNotNull() {
            addCriterion("EDUCATION_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdEqualTo(String value) {
            addCriterion("EDUCATION_TYPE_ID =", value, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdNotEqualTo(String value) {
            addCriterion("EDUCATION_TYPE_ID <>", value, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdGreaterThan(String value) {
            addCriterion("EDUCATION_TYPE_ID >", value, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("EDUCATION_TYPE_ID >=", value, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdLessThan(String value) {
            addCriterion("EDUCATION_TYPE_ID <", value, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdLessThanOrEqualTo(String value) {
            addCriterion("EDUCATION_TYPE_ID <=", value, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdLike(String value) {
            addCriterion("EDUCATION_TYPE_ID like", value, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdNotLike(String value) {
            addCriterion("EDUCATION_TYPE_ID not like", value, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdIn(List<String> values) {
            addCriterion("EDUCATION_TYPE_ID in", values, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdNotIn(List<String> values) {
            addCriterion("EDUCATION_TYPE_ID not in", values, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdBetween(String value1, String value2) {
            addCriterion("EDUCATION_TYPE_ID between", value1, value2, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdNotBetween(String value1, String value2) {
            addCriterion("EDUCATION_TYPE_ID not between", value1, value2, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameIsNull() {
            addCriterion("EDUCATION_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameIsNotNull() {
            addCriterion("EDUCATION_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameEqualTo(String value) {
            addCriterion("EDUCATION_TYPE_NAME =", value, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameNotEqualTo(String value) {
            addCriterion("EDUCATION_TYPE_NAME <>", value, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameGreaterThan(String value) {
            addCriterion("EDUCATION_TYPE_NAME >", value, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("EDUCATION_TYPE_NAME >=", value, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameLessThan(String value) {
            addCriterion("EDUCATION_TYPE_NAME <", value, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameLessThanOrEqualTo(String value) {
            addCriterion("EDUCATION_TYPE_NAME <=", value, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameLike(String value) {
            addCriterion("EDUCATION_TYPE_NAME like", value, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameNotLike(String value) {
            addCriterion("EDUCATION_TYPE_NAME not like", value, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameIn(List<String> values) {
            addCriterion("EDUCATION_TYPE_NAME in", values, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameNotIn(List<String> values) {
            addCriterion("EDUCATION_TYPE_NAME not in", values, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameBetween(String value1, String value2) {
            addCriterion("EDUCATION_TYPE_NAME between", value1, value2, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameNotBetween(String value1, String value2) {
            addCriterion("EDUCATION_TYPE_NAME not between", value1, value2, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andFwhFlowIsNull() {
            addCriterion("FWH_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFwhFlowIsNotNull() {
            addCriterion("FWH_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFwhFlowEqualTo(String value) {
            addCriterion("FWH_FLOW =", value, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowNotEqualTo(String value) {
            addCriterion("FWH_FLOW <>", value, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowGreaterThan(String value) {
            addCriterion("FWH_FLOW >", value, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FWH_FLOW >=", value, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowLessThan(String value) {
            addCriterion("FWH_FLOW <", value, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowLessThanOrEqualTo(String value) {
            addCriterion("FWH_FLOW <=", value, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowLike(String value) {
            addCriterion("FWH_FLOW like", value, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowNotLike(String value) {
            addCriterion("FWH_FLOW not like", value, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowIn(List<String> values) {
            addCriterion("FWH_FLOW in", values, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowNotIn(List<String> values) {
            addCriterion("FWH_FLOW not in", values, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowBetween(String value1, String value2) {
            addCriterion("FWH_FLOW between", value1, value2, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowNotBetween(String value1, String value2) {
            addCriterion("FWH_FLOW not between", value1, value2, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhNameIsNull() {
            addCriterion("FWH_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFwhNameIsNotNull() {
            addCriterion("FWH_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFwhNameEqualTo(String value) {
            addCriterion("FWH_NAME =", value, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameNotEqualTo(String value) {
            addCriterion("FWH_NAME <>", value, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameGreaterThan(String value) {
            addCriterion("FWH_NAME >", value, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameGreaterThanOrEqualTo(String value) {
            addCriterion("FWH_NAME >=", value, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameLessThan(String value) {
            addCriterion("FWH_NAME <", value, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameLessThanOrEqualTo(String value) {
            addCriterion("FWH_NAME <=", value, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameLike(String value) {
            addCriterion("FWH_NAME like", value, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameNotLike(String value) {
            addCriterion("FWH_NAME not like", value, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameIn(List<String> values) {
            addCriterion("FWH_NAME in", values, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameNotIn(List<String> values) {
            addCriterion("FWH_NAME not in", values, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameBetween(String value1, String value2) {
            addCriterion("FWH_NAME between", value1, value2, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameNotBetween(String value1, String value2) {
            addCriterion("FWH_NAME not between", value1, value2, "fwhName");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionIdIsNull() {
            addCriterion("STU_SPE_DIRECTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionIdIsNotNull() {
            addCriterion("STU_SPE_DIRECTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionIdEqualTo(String value) {
            addCriterion("STU_SPE_DIRECTION_ID =", value, "stuSpeDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionIdNotEqualTo(String value) {
            addCriterion("STU_SPE_DIRECTION_ID <>", value, "stuSpeDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionIdGreaterThan(String value) {
            addCriterion("STU_SPE_DIRECTION_ID >", value, "stuSpeDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionIdGreaterThanOrEqualTo(String value) {
            addCriterion("STU_SPE_DIRECTION_ID >=", value, "stuSpeDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionIdLessThan(String value) {
            addCriterion("STU_SPE_DIRECTION_ID <", value, "stuSpeDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionIdLessThanOrEqualTo(String value) {
            addCriterion("STU_SPE_DIRECTION_ID <=", value, "stuSpeDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionIdLike(String value) {
            addCriterion("STU_SPE_DIRECTION_ID like", value, "stuSpeDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionIdNotLike(String value) {
            addCriterion("STU_SPE_DIRECTION_ID not like", value, "stuSpeDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionIdIn(List<String> values) {
            addCriterion("STU_SPE_DIRECTION_ID in", values, "stuSpeDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionIdNotIn(List<String> values) {
            addCriterion("STU_SPE_DIRECTION_ID not in", values, "stuSpeDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionIdBetween(String value1, String value2) {
            addCriterion("STU_SPE_DIRECTION_ID between", value1, value2, "stuSpeDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionIdNotBetween(String value1, String value2) {
            addCriterion("STU_SPE_DIRECTION_ID not between", value1, value2, "stuSpeDirectionId");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionNameIsNull() {
            addCriterion("STU_SPE_DIRECTION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionNameIsNotNull() {
            addCriterion("STU_SPE_DIRECTION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionNameEqualTo(String value) {
            addCriterion("STU_SPE_DIRECTION_NAME =", value, "stuSpeDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionNameNotEqualTo(String value) {
            addCriterion("STU_SPE_DIRECTION_NAME <>", value, "stuSpeDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionNameGreaterThan(String value) {
            addCriterion("STU_SPE_DIRECTION_NAME >", value, "stuSpeDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionNameGreaterThanOrEqualTo(String value) {
            addCriterion("STU_SPE_DIRECTION_NAME >=", value, "stuSpeDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionNameLessThan(String value) {
            addCriterion("STU_SPE_DIRECTION_NAME <", value, "stuSpeDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionNameLessThanOrEqualTo(String value) {
            addCriterion("STU_SPE_DIRECTION_NAME <=", value, "stuSpeDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionNameLike(String value) {
            addCriterion("STU_SPE_DIRECTION_NAME like", value, "stuSpeDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionNameNotLike(String value) {
            addCriterion("STU_SPE_DIRECTION_NAME not like", value, "stuSpeDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionNameIn(List<String> values) {
            addCriterion("STU_SPE_DIRECTION_NAME in", values, "stuSpeDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionNameNotIn(List<String> values) {
            addCriterion("STU_SPE_DIRECTION_NAME not in", values, "stuSpeDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionNameBetween(String value1, String value2) {
            addCriterion("STU_SPE_DIRECTION_NAME between", value1, value2, "stuSpeDirectionName");
            return (Criteria) this;
        }

        public Criteria andStuSpeDirectionNameNotBetween(String value1, String value2) {
            addCriterion("STU_SPE_DIRECTION_NAME not between", value1, value2, "stuSpeDirectionName");
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