package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class GydxjInsertDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GydxjInsertDetailExample() {
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

        public Criteria andCourseNameEnIsNull() {
            addCriterion("COURSE_NAME_EN is null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnIsNotNull() {
            addCriterion("COURSE_NAME_EN is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnEqualTo(String value) {
            addCriterion("COURSE_NAME_EN =", value, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnNotEqualTo(String value) {
            addCriterion("COURSE_NAME_EN <>", value, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnGreaterThan(String value) {
            addCriterion("COURSE_NAME_EN >", value, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_NAME_EN >=", value, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnLessThan(String value) {
            addCriterion("COURSE_NAME_EN <", value, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnLessThanOrEqualTo(String value) {
            addCriterion("COURSE_NAME_EN <=", value, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnLike(String value) {
            addCriterion("COURSE_NAME_EN like", value, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnNotLike(String value) {
            addCriterion("COURSE_NAME_EN not like", value, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnIn(List<String> values) {
            addCriterion("COURSE_NAME_EN in", values, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnNotIn(List<String> values) {
            addCriterion("COURSE_NAME_EN not in", values, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnBetween(String value1, String value2) {
            addCriterion("COURSE_NAME_EN between", value1, value2, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnNotBetween(String value1, String value2) {
            addCriterion("COURSE_NAME_EN not between", value1, value2, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseHourIsNull() {
            addCriterion("COURSE_HOUR is null");
            return (Criteria) this;
        }

        public Criteria andCourseHourIsNotNull() {
            addCriterion("COURSE_HOUR is not null");
            return (Criteria) this;
        }

        public Criteria andCourseHourEqualTo(String value) {
            addCriterion("COURSE_HOUR =", value, "courseHour");
            return (Criteria) this;
        }

        public Criteria andCourseHourNotEqualTo(String value) {
            addCriterion("COURSE_HOUR <>", value, "courseHour");
            return (Criteria) this;
        }

        public Criteria andCourseHourGreaterThan(String value) {
            addCriterion("COURSE_HOUR >", value, "courseHour");
            return (Criteria) this;
        }

        public Criteria andCourseHourGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_HOUR >=", value, "courseHour");
            return (Criteria) this;
        }

        public Criteria andCourseHourLessThan(String value) {
            addCriterion("COURSE_HOUR <", value, "courseHour");
            return (Criteria) this;
        }

        public Criteria andCourseHourLessThanOrEqualTo(String value) {
            addCriterion("COURSE_HOUR <=", value, "courseHour");
            return (Criteria) this;
        }

        public Criteria andCourseHourLike(String value) {
            addCriterion("COURSE_HOUR like", value, "courseHour");
            return (Criteria) this;
        }

        public Criteria andCourseHourNotLike(String value) {
            addCriterion("COURSE_HOUR not like", value, "courseHour");
            return (Criteria) this;
        }

        public Criteria andCourseHourIn(List<String> values) {
            addCriterion("COURSE_HOUR in", values, "courseHour");
            return (Criteria) this;
        }

        public Criteria andCourseHourNotIn(List<String> values) {
            addCriterion("COURSE_HOUR not in", values, "courseHour");
            return (Criteria) this;
        }

        public Criteria andCourseHourBetween(String value1, String value2) {
            addCriterion("COURSE_HOUR between", value1, value2, "courseHour");
            return (Criteria) this;
        }

        public Criteria andCourseHourNotBetween(String value1, String value2) {
            addCriterion("COURSE_HOUR not between", value1, value2, "courseHour");
            return (Criteria) this;
        }

        public Criteria andCourseCreditIsNull() {
            addCriterion("COURSE_CREDIT is null");
            return (Criteria) this;
        }

        public Criteria andCourseCreditIsNotNull() {
            addCriterion("COURSE_CREDIT is not null");
            return (Criteria) this;
        }

        public Criteria andCourseCreditEqualTo(String value) {
            addCriterion("COURSE_CREDIT =", value, "courseCredit");
            return (Criteria) this;
        }

        public Criteria andCourseCreditNotEqualTo(String value) {
            addCriterion("COURSE_CREDIT <>", value, "courseCredit");
            return (Criteria) this;
        }

        public Criteria andCourseCreditGreaterThan(String value) {
            addCriterion("COURSE_CREDIT >", value, "courseCredit");
            return (Criteria) this;
        }

        public Criteria andCourseCreditGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_CREDIT >=", value, "courseCredit");
            return (Criteria) this;
        }

        public Criteria andCourseCreditLessThan(String value) {
            addCriterion("COURSE_CREDIT <", value, "courseCredit");
            return (Criteria) this;
        }

        public Criteria andCourseCreditLessThanOrEqualTo(String value) {
            addCriterion("COURSE_CREDIT <=", value, "courseCredit");
            return (Criteria) this;
        }

        public Criteria andCourseCreditLike(String value) {
            addCriterion("COURSE_CREDIT like", value, "courseCredit");
            return (Criteria) this;
        }

        public Criteria andCourseCreditNotLike(String value) {
            addCriterion("COURSE_CREDIT not like", value, "courseCredit");
            return (Criteria) this;
        }

        public Criteria andCourseCreditIn(List<String> values) {
            addCriterion("COURSE_CREDIT in", values, "courseCredit");
            return (Criteria) this;
        }

        public Criteria andCourseCreditNotIn(List<String> values) {
            addCriterion("COURSE_CREDIT not in", values, "courseCredit");
            return (Criteria) this;
        }

        public Criteria andCourseCreditBetween(String value1, String value2) {
            addCriterion("COURSE_CREDIT between", value1, value2, "courseCredit");
            return (Criteria) this;
        }

        public Criteria andCourseCreditNotBetween(String value1, String value2) {
            addCriterion("COURSE_CREDIT not between", value1, value2, "courseCredit");
            return (Criteria) this;
        }

        public Criteria andGradeScoreIsNull() {
            addCriterion("GRADE_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andGradeScoreIsNotNull() {
            addCriterion("GRADE_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andGradeScoreEqualTo(String value) {
            addCriterion("GRADE_SCORE =", value, "gradeScore");
            return (Criteria) this;
        }

        public Criteria andGradeScoreNotEqualTo(String value) {
            addCriterion("GRADE_SCORE <>", value, "gradeScore");
            return (Criteria) this;
        }

        public Criteria andGradeScoreGreaterThan(String value) {
            addCriterion("GRADE_SCORE >", value, "gradeScore");
            return (Criteria) this;
        }

        public Criteria andGradeScoreGreaterThanOrEqualTo(String value) {
            addCriterion("GRADE_SCORE >=", value, "gradeScore");
            return (Criteria) this;
        }

        public Criteria andGradeScoreLessThan(String value) {
            addCriterion("GRADE_SCORE <", value, "gradeScore");
            return (Criteria) this;
        }

        public Criteria andGradeScoreLessThanOrEqualTo(String value) {
            addCriterion("GRADE_SCORE <=", value, "gradeScore");
            return (Criteria) this;
        }

        public Criteria andGradeScoreLike(String value) {
            addCriterion("GRADE_SCORE like", value, "gradeScore");
            return (Criteria) this;
        }

        public Criteria andGradeScoreNotLike(String value) {
            addCriterion("GRADE_SCORE not like", value, "gradeScore");
            return (Criteria) this;
        }

        public Criteria andGradeScoreIn(List<String> values) {
            addCriterion("GRADE_SCORE in", values, "gradeScore");
            return (Criteria) this;
        }

        public Criteria andGradeScoreNotIn(List<String> values) {
            addCriterion("GRADE_SCORE not in", values, "gradeScore");
            return (Criteria) this;
        }

        public Criteria andGradeScoreBetween(String value1, String value2) {
            addCriterion("GRADE_SCORE between", value1, value2, "gradeScore");
            return (Criteria) this;
        }

        public Criteria andGradeScoreNotBetween(String value1, String value2) {
            addCriterion("GRADE_SCORE not between", value1, value2, "gradeScore");
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