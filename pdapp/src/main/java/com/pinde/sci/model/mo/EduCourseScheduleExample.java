package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EduCourseScheduleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EduCourseScheduleExample() {
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

        public Criteria andChapterFlowIsNull() {
            addCriterion("CHAPTER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andChapterFlowIsNotNull() {
            addCriterion("CHAPTER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andChapterFlowEqualTo(String value) {
            addCriterion("CHAPTER_FLOW =", value, "chapterFlow");
            return (Criteria) this;
        }

        public Criteria andChapterFlowNotEqualTo(String value) {
            addCriterion("CHAPTER_FLOW <>", value, "chapterFlow");
            return (Criteria) this;
        }

        public Criteria andChapterFlowGreaterThan(String value) {
            addCriterion("CHAPTER_FLOW >", value, "chapterFlow");
            return (Criteria) this;
        }

        public Criteria andChapterFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CHAPTER_FLOW >=", value, "chapterFlow");
            return (Criteria) this;
        }

        public Criteria andChapterFlowLessThan(String value) {
            addCriterion("CHAPTER_FLOW <", value, "chapterFlow");
            return (Criteria) this;
        }

        public Criteria andChapterFlowLessThanOrEqualTo(String value) {
            addCriterion("CHAPTER_FLOW <=", value, "chapterFlow");
            return (Criteria) this;
        }

        public Criteria andChapterFlowLike(String value) {
            addCriterion("CHAPTER_FLOW like", value, "chapterFlow");
            return (Criteria) this;
        }

        public Criteria andChapterFlowNotLike(String value) {
            addCriterion("CHAPTER_FLOW not like", value, "chapterFlow");
            return (Criteria) this;
        }

        public Criteria andChapterFlowIn(List<String> values) {
            addCriterion("CHAPTER_FLOW in", values, "chapterFlow");
            return (Criteria) this;
        }

        public Criteria andChapterFlowNotIn(List<String> values) {
            addCriterion("CHAPTER_FLOW not in", values, "chapterFlow");
            return (Criteria) this;
        }

        public Criteria andChapterFlowBetween(String value1, String value2) {
            addCriterion("CHAPTER_FLOW between", value1, value2, "chapterFlow");
            return (Criteria) this;
        }

        public Criteria andChapterFlowNotBetween(String value1, String value2) {
            addCriterion("CHAPTER_FLOW not between", value1, value2, "chapterFlow");
            return (Criteria) this;
        }

        public Criteria andStudyStatusIdIsNull() {
            addCriterion("STUDY_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andStudyStatusIdIsNotNull() {
            addCriterion("STUDY_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStudyStatusIdEqualTo(String value) {
            addCriterion("STUDY_STATUS_ID =", value, "studyStatusId");
            return (Criteria) this;
        }

        public Criteria andStudyStatusIdNotEqualTo(String value) {
            addCriterion("STUDY_STATUS_ID <>", value, "studyStatusId");
            return (Criteria) this;
        }

        public Criteria andStudyStatusIdGreaterThan(String value) {
            addCriterion("STUDY_STATUS_ID >", value, "studyStatusId");
            return (Criteria) this;
        }

        public Criteria andStudyStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("STUDY_STATUS_ID >=", value, "studyStatusId");
            return (Criteria) this;
        }

        public Criteria andStudyStatusIdLessThan(String value) {
            addCriterion("STUDY_STATUS_ID <", value, "studyStatusId");
            return (Criteria) this;
        }

        public Criteria andStudyStatusIdLessThanOrEqualTo(String value) {
            addCriterion("STUDY_STATUS_ID <=", value, "studyStatusId");
            return (Criteria) this;
        }

        public Criteria andStudyStatusIdLike(String value) {
            addCriterion("STUDY_STATUS_ID like", value, "studyStatusId");
            return (Criteria) this;
        }

        public Criteria andStudyStatusIdNotLike(String value) {
            addCriterion("STUDY_STATUS_ID not like", value, "studyStatusId");
            return (Criteria) this;
        }

        public Criteria andStudyStatusIdIn(List<String> values) {
            addCriterion("STUDY_STATUS_ID in", values, "studyStatusId");
            return (Criteria) this;
        }

        public Criteria andStudyStatusIdNotIn(List<String> values) {
            addCriterion("STUDY_STATUS_ID not in", values, "studyStatusId");
            return (Criteria) this;
        }

        public Criteria andStudyStatusIdBetween(String value1, String value2) {
            addCriterion("STUDY_STATUS_ID between", value1, value2, "studyStatusId");
            return (Criteria) this;
        }

        public Criteria andStudyStatusIdNotBetween(String value1, String value2) {
            addCriterion("STUDY_STATUS_ID not between", value1, value2, "studyStatusId");
            return (Criteria) this;
        }

        public Criteria andStudyStatusNameIsNull() {
            addCriterion("STUDY_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStudyStatusNameIsNotNull() {
            addCriterion("STUDY_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStudyStatusNameEqualTo(String value) {
            addCriterion("STUDY_STATUS_NAME =", value, "studyStatusName");
            return (Criteria) this;
        }

        public Criteria andStudyStatusNameNotEqualTo(String value) {
            addCriterion("STUDY_STATUS_NAME <>", value, "studyStatusName");
            return (Criteria) this;
        }

        public Criteria andStudyStatusNameGreaterThan(String value) {
            addCriterion("STUDY_STATUS_NAME >", value, "studyStatusName");
            return (Criteria) this;
        }

        public Criteria andStudyStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("STUDY_STATUS_NAME >=", value, "studyStatusName");
            return (Criteria) this;
        }

        public Criteria andStudyStatusNameLessThan(String value) {
            addCriterion("STUDY_STATUS_NAME <", value, "studyStatusName");
            return (Criteria) this;
        }

        public Criteria andStudyStatusNameLessThanOrEqualTo(String value) {
            addCriterion("STUDY_STATUS_NAME <=", value, "studyStatusName");
            return (Criteria) this;
        }

        public Criteria andStudyStatusNameLike(String value) {
            addCriterion("STUDY_STATUS_NAME like", value, "studyStatusName");
            return (Criteria) this;
        }

        public Criteria andStudyStatusNameNotLike(String value) {
            addCriterion("STUDY_STATUS_NAME not like", value, "studyStatusName");
            return (Criteria) this;
        }

        public Criteria andStudyStatusNameIn(List<String> values) {
            addCriterion("STUDY_STATUS_NAME in", values, "studyStatusName");
            return (Criteria) this;
        }

        public Criteria andStudyStatusNameNotIn(List<String> values) {
            addCriterion("STUDY_STATUS_NAME not in", values, "studyStatusName");
            return (Criteria) this;
        }

        public Criteria andStudyStatusNameBetween(String value1, String value2) {
            addCriterion("STUDY_STATUS_NAME between", value1, value2, "studyStatusName");
            return (Criteria) this;
        }

        public Criteria andStudyStatusNameNotBetween(String value1, String value2) {
            addCriterion("STUDY_STATUS_NAME not between", value1, value2, "studyStatusName");
            return (Criteria) this;
        }

        public Criteria andExamResultsIsNull() {
            addCriterion("EXAM_RESULTS is null");
            return (Criteria) this;
        }

        public Criteria andExamResultsIsNotNull() {
            addCriterion("EXAM_RESULTS is not null");
            return (Criteria) this;
        }

        public Criteria andExamResultsEqualTo(String value) {
            addCriterion("EXAM_RESULTS =", value, "examResults");
            return (Criteria) this;
        }

        public Criteria andExamResultsNotEqualTo(String value) {
            addCriterion("EXAM_RESULTS <>", value, "examResults");
            return (Criteria) this;
        }

        public Criteria andExamResultsGreaterThan(String value) {
            addCriterion("EXAM_RESULTS >", value, "examResults");
            return (Criteria) this;
        }

        public Criteria andExamResultsGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_RESULTS >=", value, "examResults");
            return (Criteria) this;
        }

        public Criteria andExamResultsLessThan(String value) {
            addCriterion("EXAM_RESULTS <", value, "examResults");
            return (Criteria) this;
        }

        public Criteria andExamResultsLessThanOrEqualTo(String value) {
            addCriterion("EXAM_RESULTS <=", value, "examResults");
            return (Criteria) this;
        }

        public Criteria andExamResultsLike(String value) {
            addCriterion("EXAM_RESULTS like", value, "examResults");
            return (Criteria) this;
        }

        public Criteria andExamResultsNotLike(String value) {
            addCriterion("EXAM_RESULTS not like", value, "examResults");
            return (Criteria) this;
        }

        public Criteria andExamResultsIn(List<String> values) {
            addCriterion("EXAM_RESULTS in", values, "examResults");
            return (Criteria) this;
        }

        public Criteria andExamResultsNotIn(List<String> values) {
            addCriterion("EXAM_RESULTS not in", values, "examResults");
            return (Criteria) this;
        }

        public Criteria andExamResultsBetween(String value1, String value2) {
            addCriterion("EXAM_RESULTS between", value1, value2, "examResults");
            return (Criteria) this;
        }

        public Criteria andExamResultsNotBetween(String value1, String value2) {
            addCriterion("EXAM_RESULTS not between", value1, value2, "examResults");
            return (Criteria) this;
        }

        public Criteria andPraiseStatusIsNull() {
            addCriterion("PRAISE_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andPraiseStatusIsNotNull() {
            addCriterion("PRAISE_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andPraiseStatusEqualTo(String value) {
            addCriterion("PRAISE_STATUS =", value, "praiseStatus");
            return (Criteria) this;
        }

        public Criteria andPraiseStatusNotEqualTo(String value) {
            addCriterion("PRAISE_STATUS <>", value, "praiseStatus");
            return (Criteria) this;
        }

        public Criteria andPraiseStatusGreaterThan(String value) {
            addCriterion("PRAISE_STATUS >", value, "praiseStatus");
            return (Criteria) this;
        }

        public Criteria andPraiseStatusGreaterThanOrEqualTo(String value) {
            addCriterion("PRAISE_STATUS >=", value, "praiseStatus");
            return (Criteria) this;
        }

        public Criteria andPraiseStatusLessThan(String value) {
            addCriterion("PRAISE_STATUS <", value, "praiseStatus");
            return (Criteria) this;
        }

        public Criteria andPraiseStatusLessThanOrEqualTo(String value) {
            addCriterion("PRAISE_STATUS <=", value, "praiseStatus");
            return (Criteria) this;
        }

        public Criteria andPraiseStatusLike(String value) {
            addCriterion("PRAISE_STATUS like", value, "praiseStatus");
            return (Criteria) this;
        }

        public Criteria andPraiseStatusNotLike(String value) {
            addCriterion("PRAISE_STATUS not like", value, "praiseStatus");
            return (Criteria) this;
        }

        public Criteria andPraiseStatusIn(List<String> values) {
            addCriterion("PRAISE_STATUS in", values, "praiseStatus");
            return (Criteria) this;
        }

        public Criteria andPraiseStatusNotIn(List<String> values) {
            addCriterion("PRAISE_STATUS not in", values, "praiseStatus");
            return (Criteria) this;
        }

        public Criteria andPraiseStatusBetween(String value1, String value2) {
            addCriterion("PRAISE_STATUS between", value1, value2, "praiseStatus");
            return (Criteria) this;
        }

        public Criteria andPraiseStatusNotBetween(String value1, String value2) {
            addCriterion("PRAISE_STATUS not between", value1, value2, "praiseStatus");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("SCORE is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(BigDecimal value) {
            addCriterion("SCORE =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(BigDecimal value) {
            addCriterion("SCORE <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(BigDecimal value) {
            addCriterion("SCORE >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SCORE >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(BigDecimal value) {
            addCriterion("SCORE <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SCORE <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<BigDecimal> values) {
            addCriterion("SCORE in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<BigDecimal> values) {
            addCriterion("SCORE not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SCORE between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SCORE not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andEvaluateIsNull() {
            addCriterion("EVALUATE is null");
            return (Criteria) this;
        }

        public Criteria andEvaluateIsNotNull() {
            addCriterion("EVALUATE is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluateEqualTo(String value) {
            addCriterion("EVALUATE =", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateNotEqualTo(String value) {
            addCriterion("EVALUATE <>", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateGreaterThan(String value) {
            addCriterion("EVALUATE >", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateGreaterThanOrEqualTo(String value) {
            addCriterion("EVALUATE >=", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateLessThan(String value) {
            addCriterion("EVALUATE <", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateLessThanOrEqualTo(String value) {
            addCriterion("EVALUATE <=", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateLike(String value) {
            addCriterion("EVALUATE like", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateNotLike(String value) {
            addCriterion("EVALUATE not like", value, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateIn(List<String> values) {
            addCriterion("EVALUATE in", values, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateNotIn(List<String> values) {
            addCriterion("EVALUATE not in", values, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateBetween(String value1, String value2) {
            addCriterion("EVALUATE between", value1, value2, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateNotBetween(String value1, String value2) {
            addCriterion("EVALUATE not between", value1, value2, "evaluate");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeIsNull() {
            addCriterion("EVALUATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeIsNotNull() {
            addCriterion("EVALUATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeEqualTo(String value) {
            addCriterion("EVALUATE_TIME =", value, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeNotEqualTo(String value) {
            addCriterion("EVALUATE_TIME <>", value, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeGreaterThan(String value) {
            addCriterion("EVALUATE_TIME >", value, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("EVALUATE_TIME >=", value, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeLessThan(String value) {
            addCriterion("EVALUATE_TIME <", value, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeLessThanOrEqualTo(String value) {
            addCriterion("EVALUATE_TIME <=", value, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeLike(String value) {
            addCriterion("EVALUATE_TIME like", value, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeNotLike(String value) {
            addCriterion("EVALUATE_TIME not like", value, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeIn(List<String> values) {
            addCriterion("EVALUATE_TIME in", values, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeNotIn(List<String> values) {
            addCriterion("EVALUATE_TIME not in", values, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeBetween(String value1, String value2) {
            addCriterion("EVALUATE_TIME between", value1, value2, "evaluateTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateTimeNotBetween(String value1, String value2) {
            addCriterion("EVALUATE_TIME not between", value1, value2, "evaluateTime");
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

        public Criteria andCurrentTimeIsNull() {
            addCriterion("CURRENT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCurrentTimeIsNotNull() {
            addCriterion("CURRENT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentTimeEqualTo(String value) {
            addCriterion("CURRENT_TIME =", value, "currentTime");
            return (Criteria) this;
        }

        public Criteria andCurrentTimeNotEqualTo(String value) {
            addCriterion("CURRENT_TIME <>", value, "currentTime");
            return (Criteria) this;
        }

        public Criteria andCurrentTimeGreaterThan(String value) {
            addCriterion("CURRENT_TIME >", value, "currentTime");
            return (Criteria) this;
        }

        public Criteria andCurrentTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CURRENT_TIME >=", value, "currentTime");
            return (Criteria) this;
        }

        public Criteria andCurrentTimeLessThan(String value) {
            addCriterion("CURRENT_TIME <", value, "currentTime");
            return (Criteria) this;
        }

        public Criteria andCurrentTimeLessThanOrEqualTo(String value) {
            addCriterion("CURRENT_TIME <=", value, "currentTime");
            return (Criteria) this;
        }

        public Criteria andCurrentTimeLike(String value) {
            addCriterion("CURRENT_TIME like", value, "currentTime");
            return (Criteria) this;
        }

        public Criteria andCurrentTimeNotLike(String value) {
            addCriterion("CURRENT_TIME not like", value, "currentTime");
            return (Criteria) this;
        }

        public Criteria andCurrentTimeIn(List<String> values) {
            addCriterion("CURRENT_TIME in", values, "currentTime");
            return (Criteria) this;
        }

        public Criteria andCurrentTimeNotIn(List<String> values) {
            addCriterion("CURRENT_TIME not in", values, "currentTime");
            return (Criteria) this;
        }

        public Criteria andCurrentTimeBetween(String value1, String value2) {
            addCriterion("CURRENT_TIME between", value1, value2, "currentTime");
            return (Criteria) this;
        }

        public Criteria andCurrentTimeNotBetween(String value1, String value2) {
            addCriterion("CURRENT_TIME not between", value1, value2, "currentTime");
            return (Criteria) this;
        }

        public Criteria andChapterFinishFlagIsNull() {
            addCriterion("CHAPTER_FINISH_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andChapterFinishFlagIsNotNull() {
            addCriterion("CHAPTER_FINISH_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andChapterFinishFlagEqualTo(String value) {
            addCriterion("CHAPTER_FINISH_FLAG =", value, "chapterFinishFlag");
            return (Criteria) this;
        }

        public Criteria andChapterFinishFlagNotEqualTo(String value) {
            addCriterion("CHAPTER_FINISH_FLAG <>", value, "chapterFinishFlag");
            return (Criteria) this;
        }

        public Criteria andChapterFinishFlagGreaterThan(String value) {
            addCriterion("CHAPTER_FINISH_FLAG >", value, "chapterFinishFlag");
            return (Criteria) this;
        }

        public Criteria andChapterFinishFlagGreaterThanOrEqualTo(String value) {
            addCriterion("CHAPTER_FINISH_FLAG >=", value, "chapterFinishFlag");
            return (Criteria) this;
        }

        public Criteria andChapterFinishFlagLessThan(String value) {
            addCriterion("CHAPTER_FINISH_FLAG <", value, "chapterFinishFlag");
            return (Criteria) this;
        }

        public Criteria andChapterFinishFlagLessThanOrEqualTo(String value) {
            addCriterion("CHAPTER_FINISH_FLAG <=", value, "chapterFinishFlag");
            return (Criteria) this;
        }

        public Criteria andChapterFinishFlagLike(String value) {
            addCriterion("CHAPTER_FINISH_FLAG like", value, "chapterFinishFlag");
            return (Criteria) this;
        }

        public Criteria andChapterFinishFlagNotLike(String value) {
            addCriterion("CHAPTER_FINISH_FLAG not like", value, "chapterFinishFlag");
            return (Criteria) this;
        }

        public Criteria andChapterFinishFlagIn(List<String> values) {
            addCriterion("CHAPTER_FINISH_FLAG in", values, "chapterFinishFlag");
            return (Criteria) this;
        }

        public Criteria andChapterFinishFlagNotIn(List<String> values) {
            addCriterion("CHAPTER_FINISH_FLAG not in", values, "chapterFinishFlag");
            return (Criteria) this;
        }

        public Criteria andChapterFinishFlagBetween(String value1, String value2) {
            addCriterion("CHAPTER_FINISH_FLAG between", value1, value2, "chapterFinishFlag");
            return (Criteria) this;
        }

        public Criteria andChapterFinishFlagNotBetween(String value1, String value2) {
            addCriterion("CHAPTER_FINISH_FLAG not between", value1, value2, "chapterFinishFlag");
            return (Criteria) this;
        }

        public Criteria andExamPassFlagIsNull() {
            addCriterion("EXAM_PASS_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andExamPassFlagIsNotNull() {
            addCriterion("EXAM_PASS_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andExamPassFlagEqualTo(String value) {
            addCriterion("EXAM_PASS_FLAG =", value, "examPassFlag");
            return (Criteria) this;
        }

        public Criteria andExamPassFlagNotEqualTo(String value) {
            addCriterion("EXAM_PASS_FLAG <>", value, "examPassFlag");
            return (Criteria) this;
        }

        public Criteria andExamPassFlagGreaterThan(String value) {
            addCriterion("EXAM_PASS_FLAG >", value, "examPassFlag");
            return (Criteria) this;
        }

        public Criteria andExamPassFlagGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_PASS_FLAG >=", value, "examPassFlag");
            return (Criteria) this;
        }

        public Criteria andExamPassFlagLessThan(String value) {
            addCriterion("EXAM_PASS_FLAG <", value, "examPassFlag");
            return (Criteria) this;
        }

        public Criteria andExamPassFlagLessThanOrEqualTo(String value) {
            addCriterion("EXAM_PASS_FLAG <=", value, "examPassFlag");
            return (Criteria) this;
        }

        public Criteria andExamPassFlagLike(String value) {
            addCriterion("EXAM_PASS_FLAG like", value, "examPassFlag");
            return (Criteria) this;
        }

        public Criteria andExamPassFlagNotLike(String value) {
            addCriterion("EXAM_PASS_FLAG not like", value, "examPassFlag");
            return (Criteria) this;
        }

        public Criteria andExamPassFlagIn(List<String> values) {
            addCriterion("EXAM_PASS_FLAG in", values, "examPassFlag");
            return (Criteria) this;
        }

        public Criteria andExamPassFlagNotIn(List<String> values) {
            addCriterion("EXAM_PASS_FLAG not in", values, "examPassFlag");
            return (Criteria) this;
        }

        public Criteria andExamPassFlagBetween(String value1, String value2) {
            addCriterion("EXAM_PASS_FLAG between", value1, value2, "examPassFlag");
            return (Criteria) this;
        }

        public Criteria andExamPassFlagNotBetween(String value1, String value2) {
            addCriterion("EXAM_PASS_FLAG not between", value1, value2, "examPassFlag");
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