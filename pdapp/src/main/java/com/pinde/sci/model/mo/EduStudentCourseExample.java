package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EduStudentCourseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EduStudentCourseExample() {
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

        public Criteria andCurrentChapterFlowIsNull() {
            addCriterion("CURRENT_CHAPTER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterFlowIsNotNull() {
            addCriterion("CURRENT_CHAPTER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterFlowEqualTo(String value) {
            addCriterion("CURRENT_CHAPTER_FLOW =", value, "currentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterFlowNotEqualTo(String value) {
            addCriterion("CURRENT_CHAPTER_FLOW <>", value, "currentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterFlowGreaterThan(String value) {
            addCriterion("CURRENT_CHAPTER_FLOW >", value, "currentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CURRENT_CHAPTER_FLOW >=", value, "currentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterFlowLessThan(String value) {
            addCriterion("CURRENT_CHAPTER_FLOW <", value, "currentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterFlowLessThanOrEqualTo(String value) {
            addCriterion("CURRENT_CHAPTER_FLOW <=", value, "currentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterFlowLike(String value) {
            addCriterion("CURRENT_CHAPTER_FLOW like", value, "currentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterFlowNotLike(String value) {
            addCriterion("CURRENT_CHAPTER_FLOW not like", value, "currentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterFlowIn(List<String> values) {
            addCriterion("CURRENT_CHAPTER_FLOW in", values, "currentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterFlowNotIn(List<String> values) {
            addCriterion("CURRENT_CHAPTER_FLOW not in", values, "currentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterFlowBetween(String value1, String value2) {
            addCriterion("CURRENT_CHAPTER_FLOW between", value1, value2, "currentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterFlowNotBetween(String value1, String value2) {
            addCriterion("CURRENT_CHAPTER_FLOW not between", value1, value2, "currentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andCourseGradeIsNull() {
            addCriterion("COURSE_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andCourseGradeIsNotNull() {
            addCriterion("COURSE_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andCourseGradeEqualTo(String value) {
            addCriterion("COURSE_GRADE =", value, "courseGrade");
            return (Criteria) this;
        }

        public Criteria andCourseGradeNotEqualTo(String value) {
            addCriterion("COURSE_GRADE <>", value, "courseGrade");
            return (Criteria) this;
        }

        public Criteria andCourseGradeGreaterThan(String value) {
            addCriterion("COURSE_GRADE >", value, "courseGrade");
            return (Criteria) this;
        }

        public Criteria andCourseGradeGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_GRADE >=", value, "courseGrade");
            return (Criteria) this;
        }

        public Criteria andCourseGradeLessThan(String value) {
            addCriterion("COURSE_GRADE <", value, "courseGrade");
            return (Criteria) this;
        }

        public Criteria andCourseGradeLessThanOrEqualTo(String value) {
            addCriterion("COURSE_GRADE <=", value, "courseGrade");
            return (Criteria) this;
        }

        public Criteria andCourseGradeLike(String value) {
            addCriterion("COURSE_GRADE like", value, "courseGrade");
            return (Criteria) this;
        }

        public Criteria andCourseGradeNotLike(String value) {
            addCriterion("COURSE_GRADE not like", value, "courseGrade");
            return (Criteria) this;
        }

        public Criteria andCourseGradeIn(List<String> values) {
            addCriterion("COURSE_GRADE in", values, "courseGrade");
            return (Criteria) this;
        }

        public Criteria andCourseGradeNotIn(List<String> values) {
            addCriterion("COURSE_GRADE not in", values, "courseGrade");
            return (Criteria) this;
        }

        public Criteria andCourseGradeBetween(String value1, String value2) {
            addCriterion("COURSE_GRADE between", value1, value2, "courseGrade");
            return (Criteria) this;
        }

        public Criteria andCourseGradeNotBetween(String value1, String value2) {
            addCriterion("COURSE_GRADE not between", value1, value2, "courseGrade");
            return (Criteria) this;
        }

        public Criteria andChooseTimeIsNull() {
            addCriterion("CHOOSE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andChooseTimeIsNotNull() {
            addCriterion("CHOOSE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andChooseTimeEqualTo(String value) {
            addCriterion("CHOOSE_TIME =", value, "chooseTime");
            return (Criteria) this;
        }

        public Criteria andChooseTimeNotEqualTo(String value) {
            addCriterion("CHOOSE_TIME <>", value, "chooseTime");
            return (Criteria) this;
        }

        public Criteria andChooseTimeGreaterThan(String value) {
            addCriterion("CHOOSE_TIME >", value, "chooseTime");
            return (Criteria) this;
        }

        public Criteria andChooseTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CHOOSE_TIME >=", value, "chooseTime");
            return (Criteria) this;
        }

        public Criteria andChooseTimeLessThan(String value) {
            addCriterion("CHOOSE_TIME <", value, "chooseTime");
            return (Criteria) this;
        }

        public Criteria andChooseTimeLessThanOrEqualTo(String value) {
            addCriterion("CHOOSE_TIME <=", value, "chooseTime");
            return (Criteria) this;
        }

        public Criteria andChooseTimeLike(String value) {
            addCriterion("CHOOSE_TIME like", value, "chooseTime");
            return (Criteria) this;
        }

        public Criteria andChooseTimeNotLike(String value) {
            addCriterion("CHOOSE_TIME not like", value, "chooseTime");
            return (Criteria) this;
        }

        public Criteria andChooseTimeIn(List<String> values) {
            addCriterion("CHOOSE_TIME in", values, "chooseTime");
            return (Criteria) this;
        }

        public Criteria andChooseTimeNotIn(List<String> values) {
            addCriterion("CHOOSE_TIME not in", values, "chooseTime");
            return (Criteria) this;
        }

        public Criteria andChooseTimeBetween(String value1, String value2) {
            addCriterion("CHOOSE_TIME between", value1, value2, "chooseTime");
            return (Criteria) this;
        }

        public Criteria andChooseTimeNotBetween(String value1, String value2) {
            addCriterion("CHOOSE_TIME not between", value1, value2, "chooseTime");
            return (Criteria) this;
        }

        public Criteria andAchieveCreditIsNull() {
            addCriterion("ACHIEVE_CREDIT is null");
            return (Criteria) this;
        }

        public Criteria andAchieveCreditIsNotNull() {
            addCriterion("ACHIEVE_CREDIT is not null");
            return (Criteria) this;
        }

        public Criteria andAchieveCreditEqualTo(String value) {
            addCriterion("ACHIEVE_CREDIT =", value, "achieveCredit");
            return (Criteria) this;
        }

        public Criteria andAchieveCreditNotEqualTo(String value) {
            addCriterion("ACHIEVE_CREDIT <>", value, "achieveCredit");
            return (Criteria) this;
        }

        public Criteria andAchieveCreditGreaterThan(String value) {
            addCriterion("ACHIEVE_CREDIT >", value, "achieveCredit");
            return (Criteria) this;
        }

        public Criteria andAchieveCreditGreaterThanOrEqualTo(String value) {
            addCriterion("ACHIEVE_CREDIT >=", value, "achieveCredit");
            return (Criteria) this;
        }

        public Criteria andAchieveCreditLessThan(String value) {
            addCriterion("ACHIEVE_CREDIT <", value, "achieveCredit");
            return (Criteria) this;
        }

        public Criteria andAchieveCreditLessThanOrEqualTo(String value) {
            addCriterion("ACHIEVE_CREDIT <=", value, "achieveCredit");
            return (Criteria) this;
        }

        public Criteria andAchieveCreditLike(String value) {
            addCriterion("ACHIEVE_CREDIT like", value, "achieveCredit");
            return (Criteria) this;
        }

        public Criteria andAchieveCreditNotLike(String value) {
            addCriterion("ACHIEVE_CREDIT not like", value, "achieveCredit");
            return (Criteria) this;
        }

        public Criteria andAchieveCreditIn(List<String> values) {
            addCriterion("ACHIEVE_CREDIT in", values, "achieveCredit");
            return (Criteria) this;
        }

        public Criteria andAchieveCreditNotIn(List<String> values) {
            addCriterion("ACHIEVE_CREDIT not in", values, "achieveCredit");
            return (Criteria) this;
        }

        public Criteria andAchieveCreditBetween(String value1, String value2) {
            addCriterion("ACHIEVE_CREDIT between", value1, value2, "achieveCredit");
            return (Criteria) this;
        }

        public Criteria andAchieveCreditNotBetween(String value1, String value2) {
            addCriterion("ACHIEVE_CREDIT not between", value1, value2, "achieveCredit");
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

        public Criteria andCourseTypeIdIsNull() {
            addCriterion("COURSE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdIsNotNull() {
            addCriterion("COURSE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdEqualTo(String value) {
            addCriterion("COURSE_TYPE_ID =", value, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdNotEqualTo(String value) {
            addCriterion("COURSE_TYPE_ID <>", value, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdGreaterThan(String value) {
            addCriterion("COURSE_TYPE_ID >", value, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_TYPE_ID >=", value, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdLessThan(String value) {
            addCriterion("COURSE_TYPE_ID <", value, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdLessThanOrEqualTo(String value) {
            addCriterion("COURSE_TYPE_ID <=", value, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdLike(String value) {
            addCriterion("COURSE_TYPE_ID like", value, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdNotLike(String value) {
            addCriterion("COURSE_TYPE_ID not like", value, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdIn(List<String> values) {
            addCriterion("COURSE_TYPE_ID in", values, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdNotIn(List<String> values) {
            addCriterion("COURSE_TYPE_ID not in", values, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdBetween(String value1, String value2) {
            addCriterion("COURSE_TYPE_ID between", value1, value2, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeIdNotBetween(String value1, String value2) {
            addCriterion("COURSE_TYPE_ID not between", value1, value2, "courseTypeId");
            return (Criteria) this;
        }

        public Criteria andCourseTypeNameIsNull() {
            addCriterion("COURSE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCourseTypeNameIsNotNull() {
            addCriterion("COURSE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCourseTypeNameEqualTo(String value) {
            addCriterion("COURSE_TYPE_NAME =", value, "courseTypeName");
            return (Criteria) this;
        }

        public Criteria andCourseTypeNameNotEqualTo(String value) {
            addCriterion("COURSE_TYPE_NAME <>", value, "courseTypeName");
            return (Criteria) this;
        }

        public Criteria andCourseTypeNameGreaterThan(String value) {
            addCriterion("COURSE_TYPE_NAME >", value, "courseTypeName");
            return (Criteria) this;
        }

        public Criteria andCourseTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_TYPE_NAME >=", value, "courseTypeName");
            return (Criteria) this;
        }

        public Criteria andCourseTypeNameLessThan(String value) {
            addCriterion("COURSE_TYPE_NAME <", value, "courseTypeName");
            return (Criteria) this;
        }

        public Criteria andCourseTypeNameLessThanOrEqualTo(String value) {
            addCriterion("COURSE_TYPE_NAME <=", value, "courseTypeName");
            return (Criteria) this;
        }

        public Criteria andCourseTypeNameLike(String value) {
            addCriterion("COURSE_TYPE_NAME like", value, "courseTypeName");
            return (Criteria) this;
        }

        public Criteria andCourseTypeNameNotLike(String value) {
            addCriterion("COURSE_TYPE_NAME not like", value, "courseTypeName");
            return (Criteria) this;
        }

        public Criteria andCourseTypeNameIn(List<String> values) {
            addCriterion("COURSE_TYPE_NAME in", values, "courseTypeName");
            return (Criteria) this;
        }

        public Criteria andCourseTypeNameNotIn(List<String> values) {
            addCriterion("COURSE_TYPE_NAME not in", values, "courseTypeName");
            return (Criteria) this;
        }

        public Criteria andCourseTypeNameBetween(String value1, String value2) {
            addCriterion("COURSE_TYPE_NAME between", value1, value2, "courseTypeName");
            return (Criteria) this;
        }

        public Criteria andCourseTypeNameNotBetween(String value1, String value2) {
            addCriterion("COURSE_TYPE_NAME not between", value1, value2, "courseTypeName");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterTimeIsNull() {
            addCriterion("CURRENT_CHAPTER_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterTimeIsNotNull() {
            addCriterion("CURRENT_CHAPTER_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterTimeEqualTo(String value) {
            addCriterion("CURRENT_CHAPTER_TIME =", value, "currentChapterTime");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterTimeNotEqualTo(String value) {
            addCriterion("CURRENT_CHAPTER_TIME <>", value, "currentChapterTime");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterTimeGreaterThan(String value) {
            addCriterion("CURRENT_CHAPTER_TIME >", value, "currentChapterTime");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CURRENT_CHAPTER_TIME >=", value, "currentChapterTime");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterTimeLessThan(String value) {
            addCriterion("CURRENT_CHAPTER_TIME <", value, "currentChapterTime");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterTimeLessThanOrEqualTo(String value) {
            addCriterion("CURRENT_CHAPTER_TIME <=", value, "currentChapterTime");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterTimeLike(String value) {
            addCriterion("CURRENT_CHAPTER_TIME like", value, "currentChapterTime");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterTimeNotLike(String value) {
            addCriterion("CURRENT_CHAPTER_TIME not like", value, "currentChapterTime");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterTimeIn(List<String> values) {
            addCriterion("CURRENT_CHAPTER_TIME in", values, "currentChapterTime");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterTimeNotIn(List<String> values) {
            addCriterion("CURRENT_CHAPTER_TIME not in", values, "currentChapterTime");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterTimeBetween(String value1, String value2) {
            addCriterion("CURRENT_CHAPTER_TIME between", value1, value2, "currentChapterTime");
            return (Criteria) this;
        }

        public Criteria andCurrentChapterTimeNotBetween(String value1, String value2) {
            addCriterion("CURRENT_CHAPTER_TIME not between", value1, value2, "currentChapterTime");
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

        public Criteria andStartStudyTimeIsNull() {
            addCriterion("START_STUDY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStartStudyTimeIsNotNull() {
            addCriterion("START_STUDY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStartStudyTimeEqualTo(String value) {
            addCriterion("START_STUDY_TIME =", value, "startStudyTime");
            return (Criteria) this;
        }

        public Criteria andStartStudyTimeNotEqualTo(String value) {
            addCriterion("START_STUDY_TIME <>", value, "startStudyTime");
            return (Criteria) this;
        }

        public Criteria andStartStudyTimeGreaterThan(String value) {
            addCriterion("START_STUDY_TIME >", value, "startStudyTime");
            return (Criteria) this;
        }

        public Criteria andStartStudyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("START_STUDY_TIME >=", value, "startStudyTime");
            return (Criteria) this;
        }

        public Criteria andStartStudyTimeLessThan(String value) {
            addCriterion("START_STUDY_TIME <", value, "startStudyTime");
            return (Criteria) this;
        }

        public Criteria andStartStudyTimeLessThanOrEqualTo(String value) {
            addCriterion("START_STUDY_TIME <=", value, "startStudyTime");
            return (Criteria) this;
        }

        public Criteria andStartStudyTimeLike(String value) {
            addCriterion("START_STUDY_TIME like", value, "startStudyTime");
            return (Criteria) this;
        }

        public Criteria andStartStudyTimeNotLike(String value) {
            addCriterion("START_STUDY_TIME not like", value, "startStudyTime");
            return (Criteria) this;
        }

        public Criteria andStartStudyTimeIn(List<String> values) {
            addCriterion("START_STUDY_TIME in", values, "startStudyTime");
            return (Criteria) this;
        }

        public Criteria andStartStudyTimeNotIn(List<String> values) {
            addCriterion("START_STUDY_TIME not in", values, "startStudyTime");
            return (Criteria) this;
        }

        public Criteria andStartStudyTimeBetween(String value1, String value2) {
            addCriterion("START_STUDY_TIME between", value1, value2, "startStudyTime");
            return (Criteria) this;
        }

        public Criteria andStartStudyTimeNotBetween(String value1, String value2) {
            addCriterion("START_STUDY_TIME not between", value1, value2, "startStudyTime");
            return (Criteria) this;
        }

        public Criteria andCompleteStudyTimeIsNull() {
            addCriterion("COMPLETE_STUDY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCompleteStudyTimeIsNotNull() {
            addCriterion("COMPLETE_STUDY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteStudyTimeEqualTo(String value) {
            addCriterion("COMPLETE_STUDY_TIME =", value, "completeStudyTime");
            return (Criteria) this;
        }

        public Criteria andCompleteStudyTimeNotEqualTo(String value) {
            addCriterion("COMPLETE_STUDY_TIME <>", value, "completeStudyTime");
            return (Criteria) this;
        }

        public Criteria andCompleteStudyTimeGreaterThan(String value) {
            addCriterion("COMPLETE_STUDY_TIME >", value, "completeStudyTime");
            return (Criteria) this;
        }

        public Criteria andCompleteStudyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLETE_STUDY_TIME >=", value, "completeStudyTime");
            return (Criteria) this;
        }

        public Criteria andCompleteStudyTimeLessThan(String value) {
            addCriterion("COMPLETE_STUDY_TIME <", value, "completeStudyTime");
            return (Criteria) this;
        }

        public Criteria andCompleteStudyTimeLessThanOrEqualTo(String value) {
            addCriterion("COMPLETE_STUDY_TIME <=", value, "completeStudyTime");
            return (Criteria) this;
        }

        public Criteria andCompleteStudyTimeLike(String value) {
            addCriterion("COMPLETE_STUDY_TIME like", value, "completeStudyTime");
            return (Criteria) this;
        }

        public Criteria andCompleteStudyTimeNotLike(String value) {
            addCriterion("COMPLETE_STUDY_TIME not like", value, "completeStudyTime");
            return (Criteria) this;
        }

        public Criteria andCompleteStudyTimeIn(List<String> values) {
            addCriterion("COMPLETE_STUDY_TIME in", values, "completeStudyTime");
            return (Criteria) this;
        }

        public Criteria andCompleteStudyTimeNotIn(List<String> values) {
            addCriterion("COMPLETE_STUDY_TIME not in", values, "completeStudyTime");
            return (Criteria) this;
        }

        public Criteria andCompleteStudyTimeBetween(String value1, String value2) {
            addCriterion("COMPLETE_STUDY_TIME between", value1, value2, "completeStudyTime");
            return (Criteria) this;
        }

        public Criteria andCompleteStudyTimeNotBetween(String value1, String value2) {
            addCriterion("COMPLETE_STUDY_TIME not between", value1, value2, "completeStudyTime");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodIsNull() {
            addCriterion("COURSE_PERIOD is null");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodIsNotNull() {
            addCriterion("COURSE_PERIOD is not null");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodEqualTo(String value) {
            addCriterion("COURSE_PERIOD =", value, "coursePeriod");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodNotEqualTo(String value) {
            addCriterion("COURSE_PERIOD <>", value, "coursePeriod");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodGreaterThan(String value) {
            addCriterion("COURSE_PERIOD >", value, "coursePeriod");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_PERIOD >=", value, "coursePeriod");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodLessThan(String value) {
            addCriterion("COURSE_PERIOD <", value, "coursePeriod");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodLessThanOrEqualTo(String value) {
            addCriterion("COURSE_PERIOD <=", value, "coursePeriod");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodLike(String value) {
            addCriterion("COURSE_PERIOD like", value, "coursePeriod");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodNotLike(String value) {
            addCriterion("COURSE_PERIOD not like", value, "coursePeriod");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodIn(List<String> values) {
            addCriterion("COURSE_PERIOD in", values, "coursePeriod");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodNotIn(List<String> values) {
            addCriterion("COURSE_PERIOD not in", values, "coursePeriod");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodBetween(String value1, String value2) {
            addCriterion("COURSE_PERIOD between", value1, value2, "coursePeriod");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodNotBetween(String value1, String value2) {
            addCriterion("COURSE_PERIOD not between", value1, value2, "coursePeriod");
            return (Criteria) this;
        }

        public Criteria andVideoFinishFlagIsNull() {
            addCriterion("VIDEO_FINISH_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andVideoFinishFlagIsNotNull() {
            addCriterion("VIDEO_FINISH_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andVideoFinishFlagEqualTo(String value) {
            addCriterion("VIDEO_FINISH_FLAG =", value, "videoFinishFlag");
            return (Criteria) this;
        }

        public Criteria andVideoFinishFlagNotEqualTo(String value) {
            addCriterion("VIDEO_FINISH_FLAG <>", value, "videoFinishFlag");
            return (Criteria) this;
        }

        public Criteria andVideoFinishFlagGreaterThan(String value) {
            addCriterion("VIDEO_FINISH_FLAG >", value, "videoFinishFlag");
            return (Criteria) this;
        }

        public Criteria andVideoFinishFlagGreaterThanOrEqualTo(String value) {
            addCriterion("VIDEO_FINISH_FLAG >=", value, "videoFinishFlag");
            return (Criteria) this;
        }

        public Criteria andVideoFinishFlagLessThan(String value) {
            addCriterion("VIDEO_FINISH_FLAG <", value, "videoFinishFlag");
            return (Criteria) this;
        }

        public Criteria andVideoFinishFlagLessThanOrEqualTo(String value) {
            addCriterion("VIDEO_FINISH_FLAG <=", value, "videoFinishFlag");
            return (Criteria) this;
        }

        public Criteria andVideoFinishFlagLike(String value) {
            addCriterion("VIDEO_FINISH_FLAG like", value, "videoFinishFlag");
            return (Criteria) this;
        }

        public Criteria andVideoFinishFlagNotLike(String value) {
            addCriterion("VIDEO_FINISH_FLAG not like", value, "videoFinishFlag");
            return (Criteria) this;
        }

        public Criteria andVideoFinishFlagIn(List<String> values) {
            addCriterion("VIDEO_FINISH_FLAG in", values, "videoFinishFlag");
            return (Criteria) this;
        }

        public Criteria andVideoFinishFlagNotIn(List<String> values) {
            addCriterion("VIDEO_FINISH_FLAG not in", values, "videoFinishFlag");
            return (Criteria) this;
        }

        public Criteria andVideoFinishFlagBetween(String value1, String value2) {
            addCriterion("VIDEO_FINISH_FLAG between", value1, value2, "videoFinishFlag");
            return (Criteria) this;
        }

        public Criteria andVideoFinishFlagNotBetween(String value1, String value2) {
            addCriterion("VIDEO_FINISH_FLAG not between", value1, value2, "videoFinishFlag");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowIsNull() {
            addCriterion("SCH_DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowIsNotNull() {
            addCriterion("SCH_DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW =", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW <>", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowGreaterThan(String value) {
            addCriterion("SCH_DEPT_FLOW >", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW >=", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowLessThan(String value) {
            addCriterion("SCH_DEPT_FLOW <", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW <=", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowLike(String value) {
            addCriterion("SCH_DEPT_FLOW like", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotLike(String value) {
            addCriterion("SCH_DEPT_FLOW not like", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowIn(List<String> values) {
            addCriterion("SCH_DEPT_FLOW in", values, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotIn(List<String> values) {
            addCriterion("SCH_DEPT_FLOW not in", values, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_FLOW between", value1, value2, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_FLOW not between", value1, value2, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameIsNull() {
            addCriterion("SCH_DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameIsNotNull() {
            addCriterion("SCH_DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME =", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME <>", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameGreaterThan(String value) {
            addCriterion("SCH_DEPT_NAME >", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME >=", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameLessThan(String value) {
            addCriterion("SCH_DEPT_NAME <", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameLessThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME <=", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameLike(String value) {
            addCriterion("SCH_DEPT_NAME like", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotLike(String value) {
            addCriterion("SCH_DEPT_NAME not like", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameIn(List<String> values) {
            addCriterion("SCH_DEPT_NAME in", values, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotIn(List<String> values) {
            addCriterion("SCH_DEPT_NAME not in", values, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_NAME between", value1, value2, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_NAME not between", value1, value2, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andAuditFlagIsNull() {
            addCriterion("AUDIT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andAuditFlagIsNotNull() {
            addCriterion("AUDIT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andAuditFlagEqualTo(String value) {
            addCriterion("AUDIT_FLAG =", value, "auditFlag");
            return (Criteria) this;
        }

        public Criteria andAuditFlagNotEqualTo(String value) {
            addCriterion("AUDIT_FLAG <>", value, "auditFlag");
            return (Criteria) this;
        }

        public Criteria andAuditFlagGreaterThan(String value) {
            addCriterion("AUDIT_FLAG >", value, "auditFlag");
            return (Criteria) this;
        }

        public Criteria andAuditFlagGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_FLAG >=", value, "auditFlag");
            return (Criteria) this;
        }

        public Criteria andAuditFlagLessThan(String value) {
            addCriterion("AUDIT_FLAG <", value, "auditFlag");
            return (Criteria) this;
        }

        public Criteria andAuditFlagLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_FLAG <=", value, "auditFlag");
            return (Criteria) this;
        }

        public Criteria andAuditFlagLike(String value) {
            addCriterion("AUDIT_FLAG like", value, "auditFlag");
            return (Criteria) this;
        }

        public Criteria andAuditFlagNotLike(String value) {
            addCriterion("AUDIT_FLAG not like", value, "auditFlag");
            return (Criteria) this;
        }

        public Criteria andAuditFlagIn(List<String> values) {
            addCriterion("AUDIT_FLAG in", values, "auditFlag");
            return (Criteria) this;
        }

        public Criteria andAuditFlagNotIn(List<String> values) {
            addCriterion("AUDIT_FLAG not in", values, "auditFlag");
            return (Criteria) this;
        }

        public Criteria andAuditFlagBetween(String value1, String value2) {
            addCriterion("AUDIT_FLAG between", value1, value2, "auditFlag");
            return (Criteria) this;
        }

        public Criteria andAuditFlagNotBetween(String value1, String value2) {
            addCriterion("AUDIT_FLAG not between", value1, value2, "auditFlag");
            return (Criteria) this;
        }

        public Criteria andReplenishFlagIsNull() {
            addCriterion("REPLENISH_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andReplenishFlagIsNotNull() {
            addCriterion("REPLENISH_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andReplenishFlagEqualTo(String value) {
            addCriterion("REPLENISH_FLAG =", value, "replenishFlag");
            return (Criteria) this;
        }

        public Criteria andReplenishFlagNotEqualTo(String value) {
            addCriterion("REPLENISH_FLAG <>", value, "replenishFlag");
            return (Criteria) this;
        }

        public Criteria andReplenishFlagGreaterThan(String value) {
            addCriterion("REPLENISH_FLAG >", value, "replenishFlag");
            return (Criteria) this;
        }

        public Criteria andReplenishFlagGreaterThanOrEqualTo(String value) {
            addCriterion("REPLENISH_FLAG >=", value, "replenishFlag");
            return (Criteria) this;
        }

        public Criteria andReplenishFlagLessThan(String value) {
            addCriterion("REPLENISH_FLAG <", value, "replenishFlag");
            return (Criteria) this;
        }

        public Criteria andReplenishFlagLessThanOrEqualTo(String value) {
            addCriterion("REPLENISH_FLAG <=", value, "replenishFlag");
            return (Criteria) this;
        }

        public Criteria andReplenishFlagLike(String value) {
            addCriterion("REPLENISH_FLAG like", value, "replenishFlag");
            return (Criteria) this;
        }

        public Criteria andReplenishFlagNotLike(String value) {
            addCriterion("REPLENISH_FLAG not like", value, "replenishFlag");
            return (Criteria) this;
        }

        public Criteria andReplenishFlagIn(List<String> values) {
            addCriterion("REPLENISH_FLAG in", values, "replenishFlag");
            return (Criteria) this;
        }

        public Criteria andReplenishFlagNotIn(List<String> values) {
            addCriterion("REPLENISH_FLAG not in", values, "replenishFlag");
            return (Criteria) this;
        }

        public Criteria andReplenishFlagBetween(String value1, String value2) {
            addCriterion("REPLENISH_FLAG between", value1, value2, "replenishFlag");
            return (Criteria) this;
        }

        public Criteria andReplenishFlagNotBetween(String value1, String value2) {
            addCriterion("REPLENISH_FLAG not between", value1, value2, "replenishFlag");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIsNull() {
            addCriterion("DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIsNotNull() {
            addCriterion("DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowEqualTo(String value) {
            addCriterion("DEPT_FLOW =", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotEqualTo(String value) {
            addCriterion("DEPT_FLOW <>", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThan(String value) {
            addCriterion("DEPT_FLOW >", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW >=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThan(String value) {
            addCriterion("DEPT_FLOW <", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW <=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLike(String value) {
            addCriterion("DEPT_FLOW like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotLike(String value) {
            addCriterion("DEPT_FLOW not like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIn(List<String> values) {
            addCriterion("DEPT_FLOW in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotIn(List<String> values) {
            addCriterion("DEPT_FLOW not in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW not between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNull() {
            addCriterion("DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNotNull() {
            addCriterion("DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNameEqualTo(String value) {
            addCriterion("DEPT_NAME =", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotEqualTo(String value) {
            addCriterion("DEPT_NAME <>", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThan(String value) {
            addCriterion("DEPT_NAME >", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME >=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThan(String value) {
            addCriterion("DEPT_NAME <", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME <=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLike(String value) {
            addCriterion("DEPT_NAME like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotLike(String value) {
            addCriterion("DEPT_NAME not like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameIn(List<String> values) {
            addCriterion("DEPT_NAME in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotIn(List<String> values) {
            addCriterion("DEPT_NAME not in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameBetween(String value1, String value2) {
            addCriterion("DEPT_NAME between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotBetween(String value1, String value2) {
            addCriterion("DEPT_NAME not between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andStudentPeriodIsNull() {
            addCriterion("STUDENT_PERIOD is null");
            return (Criteria) this;
        }

        public Criteria andStudentPeriodIsNotNull() {
            addCriterion("STUDENT_PERIOD is not null");
            return (Criteria) this;
        }

        public Criteria andStudentPeriodEqualTo(String value) {
            addCriterion("STUDENT_PERIOD =", value, "studentPeriod");
            return (Criteria) this;
        }

        public Criteria andStudentPeriodNotEqualTo(String value) {
            addCriterion("STUDENT_PERIOD <>", value, "studentPeriod");
            return (Criteria) this;
        }

        public Criteria andStudentPeriodGreaterThan(String value) {
            addCriterion("STUDENT_PERIOD >", value, "studentPeriod");
            return (Criteria) this;
        }

        public Criteria andStudentPeriodGreaterThanOrEqualTo(String value) {
            addCriterion("STUDENT_PERIOD >=", value, "studentPeriod");
            return (Criteria) this;
        }

        public Criteria andStudentPeriodLessThan(String value) {
            addCriterion("STUDENT_PERIOD <", value, "studentPeriod");
            return (Criteria) this;
        }

        public Criteria andStudentPeriodLessThanOrEqualTo(String value) {
            addCriterion("STUDENT_PERIOD <=", value, "studentPeriod");
            return (Criteria) this;
        }

        public Criteria andStudentPeriodLike(String value) {
            addCriterion("STUDENT_PERIOD like", value, "studentPeriod");
            return (Criteria) this;
        }

        public Criteria andStudentPeriodNotLike(String value) {
            addCriterion("STUDENT_PERIOD not like", value, "studentPeriod");
            return (Criteria) this;
        }

        public Criteria andStudentPeriodIn(List<String> values) {
            addCriterion("STUDENT_PERIOD in", values, "studentPeriod");
            return (Criteria) this;
        }

        public Criteria andStudentPeriodNotIn(List<String> values) {
            addCriterion("STUDENT_PERIOD not in", values, "studentPeriod");
            return (Criteria) this;
        }

        public Criteria andStudentPeriodBetween(String value1, String value2) {
            addCriterion("STUDENT_PERIOD between", value1, value2, "studentPeriod");
            return (Criteria) this;
        }

        public Criteria andStudentPeriodNotBetween(String value1, String value2) {
            addCriterion("STUDENT_PERIOD not between", value1, value2, "studentPeriod");
            return (Criteria) this;
        }

        public Criteria andGradeTermIdIsNull() {
            addCriterion("GRADE_TERM_ID is null");
            return (Criteria) this;
        }

        public Criteria andGradeTermIdIsNotNull() {
            addCriterion("GRADE_TERM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGradeTermIdEqualTo(String value) {
            addCriterion("GRADE_TERM_ID =", value, "gradeTermId");
            return (Criteria) this;
        }

        public Criteria andGradeTermIdNotEqualTo(String value) {
            addCriterion("GRADE_TERM_ID <>", value, "gradeTermId");
            return (Criteria) this;
        }

        public Criteria andGradeTermIdGreaterThan(String value) {
            addCriterion("GRADE_TERM_ID >", value, "gradeTermId");
            return (Criteria) this;
        }

        public Criteria andGradeTermIdGreaterThanOrEqualTo(String value) {
            addCriterion("GRADE_TERM_ID >=", value, "gradeTermId");
            return (Criteria) this;
        }

        public Criteria andGradeTermIdLessThan(String value) {
            addCriterion("GRADE_TERM_ID <", value, "gradeTermId");
            return (Criteria) this;
        }

        public Criteria andGradeTermIdLessThanOrEqualTo(String value) {
            addCriterion("GRADE_TERM_ID <=", value, "gradeTermId");
            return (Criteria) this;
        }

        public Criteria andGradeTermIdLike(String value) {
            addCriterion("GRADE_TERM_ID like", value, "gradeTermId");
            return (Criteria) this;
        }

        public Criteria andGradeTermIdNotLike(String value) {
            addCriterion("GRADE_TERM_ID not like", value, "gradeTermId");
            return (Criteria) this;
        }

        public Criteria andGradeTermIdIn(List<String> values) {
            addCriterion("GRADE_TERM_ID in", values, "gradeTermId");
            return (Criteria) this;
        }

        public Criteria andGradeTermIdNotIn(List<String> values) {
            addCriterion("GRADE_TERM_ID not in", values, "gradeTermId");
            return (Criteria) this;
        }

        public Criteria andGradeTermIdBetween(String value1, String value2) {
            addCriterion("GRADE_TERM_ID between", value1, value2, "gradeTermId");
            return (Criteria) this;
        }

        public Criteria andGradeTermIdNotBetween(String value1, String value2) {
            addCriterion("GRADE_TERM_ID not between", value1, value2, "gradeTermId");
            return (Criteria) this;
        }

        public Criteria andGradeTermNameIsNull() {
            addCriterion("GRADE_TERM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGradeTermNameIsNotNull() {
            addCriterion("GRADE_TERM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGradeTermNameEqualTo(String value) {
            addCriterion("GRADE_TERM_NAME =", value, "gradeTermName");
            return (Criteria) this;
        }

        public Criteria andGradeTermNameNotEqualTo(String value) {
            addCriterion("GRADE_TERM_NAME <>", value, "gradeTermName");
            return (Criteria) this;
        }

        public Criteria andGradeTermNameGreaterThan(String value) {
            addCriterion("GRADE_TERM_NAME >", value, "gradeTermName");
            return (Criteria) this;
        }

        public Criteria andGradeTermNameGreaterThanOrEqualTo(String value) {
            addCriterion("GRADE_TERM_NAME >=", value, "gradeTermName");
            return (Criteria) this;
        }

        public Criteria andGradeTermNameLessThan(String value) {
            addCriterion("GRADE_TERM_NAME <", value, "gradeTermName");
            return (Criteria) this;
        }

        public Criteria andGradeTermNameLessThanOrEqualTo(String value) {
            addCriterion("GRADE_TERM_NAME <=", value, "gradeTermName");
            return (Criteria) this;
        }

        public Criteria andGradeTermNameLike(String value) {
            addCriterion("GRADE_TERM_NAME like", value, "gradeTermName");
            return (Criteria) this;
        }

        public Criteria andGradeTermNameNotLike(String value) {
            addCriterion("GRADE_TERM_NAME not like", value, "gradeTermName");
            return (Criteria) this;
        }

        public Criteria andGradeTermNameIn(List<String> values) {
            addCriterion("GRADE_TERM_NAME in", values, "gradeTermName");
            return (Criteria) this;
        }

        public Criteria andGradeTermNameNotIn(List<String> values) {
            addCriterion("GRADE_TERM_NAME not in", values, "gradeTermName");
            return (Criteria) this;
        }

        public Criteria andGradeTermNameBetween(String value1, String value2) {
            addCriterion("GRADE_TERM_NAME between", value1, value2, "gradeTermName");
            return (Criteria) this;
        }

        public Criteria andGradeTermNameNotBetween(String value1, String value2) {
            addCriterion("GRADE_TERM_NAME not between", value1, value2, "gradeTermName");
            return (Criteria) this;
        }

        public Criteria andCourseCodeIsNull() {
            addCriterion("COURSE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCourseCodeIsNotNull() {
            addCriterion("COURSE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCourseCodeEqualTo(String value) {
            addCriterion("COURSE_CODE =", value, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeNotEqualTo(String value) {
            addCriterion("COURSE_CODE <>", value, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeGreaterThan(String value) {
            addCriterion("COURSE_CODE >", value, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_CODE >=", value, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeLessThan(String value) {
            addCriterion("COURSE_CODE <", value, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeLessThanOrEqualTo(String value) {
            addCriterion("COURSE_CODE <=", value, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeLike(String value) {
            addCriterion("COURSE_CODE like", value, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeNotLike(String value) {
            addCriterion("COURSE_CODE not like", value, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeIn(List<String> values) {
            addCriterion("COURSE_CODE in", values, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeNotIn(List<String> values) {
            addCriterion("COURSE_CODE not in", values, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeBetween(String value1, String value2) {
            addCriterion("COURSE_CODE between", value1, value2, "courseCode");
            return (Criteria) this;
        }

        public Criteria andCourseCodeNotBetween(String value1, String value2) {
            addCriterion("COURSE_CODE not between", value1, value2, "courseCode");
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

        public Criteria andStudyWayIdIsNull() {
            addCriterion("STUDY_WAY_ID is null");
            return (Criteria) this;
        }

        public Criteria andStudyWayIdIsNotNull() {
            addCriterion("STUDY_WAY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStudyWayIdEqualTo(String value) {
            addCriterion("STUDY_WAY_ID =", value, "studyWayId");
            return (Criteria) this;
        }

        public Criteria andStudyWayIdNotEqualTo(String value) {
            addCriterion("STUDY_WAY_ID <>", value, "studyWayId");
            return (Criteria) this;
        }

        public Criteria andStudyWayIdGreaterThan(String value) {
            addCriterion("STUDY_WAY_ID >", value, "studyWayId");
            return (Criteria) this;
        }

        public Criteria andStudyWayIdGreaterThanOrEqualTo(String value) {
            addCriterion("STUDY_WAY_ID >=", value, "studyWayId");
            return (Criteria) this;
        }

        public Criteria andStudyWayIdLessThan(String value) {
            addCriterion("STUDY_WAY_ID <", value, "studyWayId");
            return (Criteria) this;
        }

        public Criteria andStudyWayIdLessThanOrEqualTo(String value) {
            addCriterion("STUDY_WAY_ID <=", value, "studyWayId");
            return (Criteria) this;
        }

        public Criteria andStudyWayIdLike(String value) {
            addCriterion("STUDY_WAY_ID like", value, "studyWayId");
            return (Criteria) this;
        }

        public Criteria andStudyWayIdNotLike(String value) {
            addCriterion("STUDY_WAY_ID not like", value, "studyWayId");
            return (Criteria) this;
        }

        public Criteria andStudyWayIdIn(List<String> values) {
            addCriterion("STUDY_WAY_ID in", values, "studyWayId");
            return (Criteria) this;
        }

        public Criteria andStudyWayIdNotIn(List<String> values) {
            addCriterion("STUDY_WAY_ID not in", values, "studyWayId");
            return (Criteria) this;
        }

        public Criteria andStudyWayIdBetween(String value1, String value2) {
            addCriterion("STUDY_WAY_ID between", value1, value2, "studyWayId");
            return (Criteria) this;
        }

        public Criteria andStudyWayIdNotBetween(String value1, String value2) {
            addCriterion("STUDY_WAY_ID not between", value1, value2, "studyWayId");
            return (Criteria) this;
        }

        public Criteria andStudyWayNameIsNull() {
            addCriterion("STUDY_WAY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStudyWayNameIsNotNull() {
            addCriterion("STUDY_WAY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStudyWayNameEqualTo(String value) {
            addCriterion("STUDY_WAY_NAME =", value, "studyWayName");
            return (Criteria) this;
        }

        public Criteria andStudyWayNameNotEqualTo(String value) {
            addCriterion("STUDY_WAY_NAME <>", value, "studyWayName");
            return (Criteria) this;
        }

        public Criteria andStudyWayNameGreaterThan(String value) {
            addCriterion("STUDY_WAY_NAME >", value, "studyWayName");
            return (Criteria) this;
        }

        public Criteria andStudyWayNameGreaterThanOrEqualTo(String value) {
            addCriterion("STUDY_WAY_NAME >=", value, "studyWayName");
            return (Criteria) this;
        }

        public Criteria andStudyWayNameLessThan(String value) {
            addCriterion("STUDY_WAY_NAME <", value, "studyWayName");
            return (Criteria) this;
        }

        public Criteria andStudyWayNameLessThanOrEqualTo(String value) {
            addCriterion("STUDY_WAY_NAME <=", value, "studyWayName");
            return (Criteria) this;
        }

        public Criteria andStudyWayNameLike(String value) {
            addCriterion("STUDY_WAY_NAME like", value, "studyWayName");
            return (Criteria) this;
        }

        public Criteria andStudyWayNameNotLike(String value) {
            addCriterion("STUDY_WAY_NAME not like", value, "studyWayName");
            return (Criteria) this;
        }

        public Criteria andStudyWayNameIn(List<String> values) {
            addCriterion("STUDY_WAY_NAME in", values, "studyWayName");
            return (Criteria) this;
        }

        public Criteria andStudyWayNameNotIn(List<String> values) {
            addCriterion("STUDY_WAY_NAME not in", values, "studyWayName");
            return (Criteria) this;
        }

        public Criteria andStudyWayNameBetween(String value1, String value2) {
            addCriterion("STUDY_WAY_NAME between", value1, value2, "studyWayName");
            return (Criteria) this;
        }

        public Criteria andStudyWayNameNotBetween(String value1, String value2) {
            addCriterion("STUDY_WAY_NAME not between", value1, value2, "studyWayName");
            return (Criteria) this;
        }

        public Criteria andPacificGradeIsNull() {
            addCriterion("PACIFIC_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andPacificGradeIsNotNull() {
            addCriterion("PACIFIC_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andPacificGradeEqualTo(String value) {
            addCriterion("PACIFIC_GRADE =", value, "pacificGrade");
            return (Criteria) this;
        }

        public Criteria andPacificGradeNotEqualTo(String value) {
            addCriterion("PACIFIC_GRADE <>", value, "pacificGrade");
            return (Criteria) this;
        }

        public Criteria andPacificGradeGreaterThan(String value) {
            addCriterion("PACIFIC_GRADE >", value, "pacificGrade");
            return (Criteria) this;
        }

        public Criteria andPacificGradeGreaterThanOrEqualTo(String value) {
            addCriterion("PACIFIC_GRADE >=", value, "pacificGrade");
            return (Criteria) this;
        }

        public Criteria andPacificGradeLessThan(String value) {
            addCriterion("PACIFIC_GRADE <", value, "pacificGrade");
            return (Criteria) this;
        }

        public Criteria andPacificGradeLessThanOrEqualTo(String value) {
            addCriterion("PACIFIC_GRADE <=", value, "pacificGrade");
            return (Criteria) this;
        }

        public Criteria andPacificGradeLike(String value) {
            addCriterion("PACIFIC_GRADE like", value, "pacificGrade");
            return (Criteria) this;
        }

        public Criteria andPacificGradeNotLike(String value) {
            addCriterion("PACIFIC_GRADE not like", value, "pacificGrade");
            return (Criteria) this;
        }

        public Criteria andPacificGradeIn(List<String> values) {
            addCriterion("PACIFIC_GRADE in", values, "pacificGrade");
            return (Criteria) this;
        }

        public Criteria andPacificGradeNotIn(List<String> values) {
            addCriterion("PACIFIC_GRADE not in", values, "pacificGrade");
            return (Criteria) this;
        }

        public Criteria andPacificGradeBetween(String value1, String value2) {
            addCriterion("PACIFIC_GRADE between", value1, value2, "pacificGrade");
            return (Criteria) this;
        }

        public Criteria andPacificGradeNotBetween(String value1, String value2) {
            addCriterion("PACIFIC_GRADE not between", value1, value2, "pacificGrade");
            return (Criteria) this;
        }

        public Criteria andTeamEndGradeIsNull() {
            addCriterion("TEAM_END_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andTeamEndGradeIsNotNull() {
            addCriterion("TEAM_END_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andTeamEndGradeEqualTo(String value) {
            addCriterion("TEAM_END_GRADE =", value, "teamEndGrade");
            return (Criteria) this;
        }

        public Criteria andTeamEndGradeNotEqualTo(String value) {
            addCriterion("TEAM_END_GRADE <>", value, "teamEndGrade");
            return (Criteria) this;
        }

        public Criteria andTeamEndGradeGreaterThan(String value) {
            addCriterion("TEAM_END_GRADE >", value, "teamEndGrade");
            return (Criteria) this;
        }

        public Criteria andTeamEndGradeGreaterThanOrEqualTo(String value) {
            addCriterion("TEAM_END_GRADE >=", value, "teamEndGrade");
            return (Criteria) this;
        }

        public Criteria andTeamEndGradeLessThan(String value) {
            addCriterion("TEAM_END_GRADE <", value, "teamEndGrade");
            return (Criteria) this;
        }

        public Criteria andTeamEndGradeLessThanOrEqualTo(String value) {
            addCriterion("TEAM_END_GRADE <=", value, "teamEndGrade");
            return (Criteria) this;
        }

        public Criteria andTeamEndGradeLike(String value) {
            addCriterion("TEAM_END_GRADE like", value, "teamEndGrade");
            return (Criteria) this;
        }

        public Criteria andTeamEndGradeNotLike(String value) {
            addCriterion("TEAM_END_GRADE not like", value, "teamEndGrade");
            return (Criteria) this;
        }

        public Criteria andTeamEndGradeIn(List<String> values) {
            addCriterion("TEAM_END_GRADE in", values, "teamEndGrade");
            return (Criteria) this;
        }

        public Criteria andTeamEndGradeNotIn(List<String> values) {
            addCriterion("TEAM_END_GRADE not in", values, "teamEndGrade");
            return (Criteria) this;
        }

        public Criteria andTeamEndGradeBetween(String value1, String value2) {
            addCriterion("TEAM_END_GRADE between", value1, value2, "teamEndGrade");
            return (Criteria) this;
        }

        public Criteria andTeamEndGradeNotBetween(String value1, String value2) {
            addCriterion("TEAM_END_GRADE not between", value1, value2, "teamEndGrade");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdIsNull() {
            addCriterion("ASSESS_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdIsNotNull() {
            addCriterion("ASSESS_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdEqualTo(String value) {
            addCriterion("ASSESS_TYPE_ID =", value, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdNotEqualTo(String value) {
            addCriterion("ASSESS_TYPE_ID <>", value, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdGreaterThan(String value) {
            addCriterion("ASSESS_TYPE_ID >", value, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ASSESS_TYPE_ID >=", value, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdLessThan(String value) {
            addCriterion("ASSESS_TYPE_ID <", value, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ASSESS_TYPE_ID <=", value, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdLike(String value) {
            addCriterion("ASSESS_TYPE_ID like", value, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdNotLike(String value) {
            addCriterion("ASSESS_TYPE_ID not like", value, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdIn(List<String> values) {
            addCriterion("ASSESS_TYPE_ID in", values, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdNotIn(List<String> values) {
            addCriterion("ASSESS_TYPE_ID not in", values, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdBetween(String value1, String value2) {
            addCriterion("ASSESS_TYPE_ID between", value1, value2, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeIdNotBetween(String value1, String value2) {
            addCriterion("ASSESS_TYPE_ID not between", value1, value2, "assessTypeId");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameIsNull() {
            addCriterion("ASSESS_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameIsNotNull() {
            addCriterion("ASSESS_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameEqualTo(String value) {
            addCriterion("ASSESS_TYPE_NAME =", value, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameNotEqualTo(String value) {
            addCriterion("ASSESS_TYPE_NAME <>", value, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameGreaterThan(String value) {
            addCriterion("ASSESS_TYPE_NAME >", value, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("ASSESS_TYPE_NAME >=", value, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameLessThan(String value) {
            addCriterion("ASSESS_TYPE_NAME <", value, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameLessThanOrEqualTo(String value) {
            addCriterion("ASSESS_TYPE_NAME <=", value, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameLike(String value) {
            addCriterion("ASSESS_TYPE_NAME like", value, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameNotLike(String value) {
            addCriterion("ASSESS_TYPE_NAME not like", value, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameIn(List<String> values) {
            addCriterion("ASSESS_TYPE_NAME in", values, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameNotIn(List<String> values) {
            addCriterion("ASSESS_TYPE_NAME not in", values, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameBetween(String value1, String value2) {
            addCriterion("ASSESS_TYPE_NAME between", value1, value2, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andAssessTypeNameNotBetween(String value1, String value2) {
            addCriterion("ASSESS_TYPE_NAME not between", value1, value2, "assessTypeName");
            return (Criteria) this;
        }

        public Criteria andImpFlowIsNull() {
            addCriterion("IMP_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andImpFlowIsNotNull() {
            addCriterion("IMP_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andImpFlowEqualTo(String value) {
            addCriterion("IMP_FLOW =", value, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowNotEqualTo(String value) {
            addCriterion("IMP_FLOW <>", value, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowGreaterThan(String value) {
            addCriterion("IMP_FLOW >", value, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowGreaterThanOrEqualTo(String value) {
            addCriterion("IMP_FLOW >=", value, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowLessThan(String value) {
            addCriterion("IMP_FLOW <", value, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowLessThanOrEqualTo(String value) {
            addCriterion("IMP_FLOW <=", value, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowLike(String value) {
            addCriterion("IMP_FLOW like", value, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowNotLike(String value) {
            addCriterion("IMP_FLOW not like", value, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowIn(List<String> values) {
            addCriterion("IMP_FLOW in", values, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowNotIn(List<String> values) {
            addCriterion("IMP_FLOW not in", values, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowBetween(String value1, String value2) {
            addCriterion("IMP_FLOW between", value1, value2, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowNotBetween(String value1, String value2) {
            addCriterion("IMP_FLOW not between", value1, value2, "impFlow");
            return (Criteria) this;
        }

        public Criteria andRoleFlagIsNull() {
            addCriterion("ROLE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andRoleFlagIsNotNull() {
            addCriterion("ROLE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andRoleFlagEqualTo(String value) {
            addCriterion("ROLE_FLAG =", value, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagNotEqualTo(String value) {
            addCriterion("ROLE_FLAG <>", value, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagGreaterThan(String value) {
            addCriterion("ROLE_FLAG >", value, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_FLAG >=", value, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagLessThan(String value) {
            addCriterion("ROLE_FLAG <", value, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagLessThanOrEqualTo(String value) {
            addCriterion("ROLE_FLAG <=", value, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagLike(String value) {
            addCriterion("ROLE_FLAG like", value, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagNotLike(String value) {
            addCriterion("ROLE_FLAG not like", value, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagIn(List<String> values) {
            addCriterion("ROLE_FLAG in", values, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagNotIn(List<String> values) {
            addCriterion("ROLE_FLAG not in", values, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagBetween(String value1, String value2) {
            addCriterion("ROLE_FLAG between", value1, value2, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagNotBetween(String value1, String value2) {
            addCriterion("ROLE_FLAG not between", value1, value2, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagIsNull() {
            addCriterion("SUBMIT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagIsNotNull() {
            addCriterion("SUBMIT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagEqualTo(String value) {
            addCriterion("SUBMIT_FLAG =", value, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagNotEqualTo(String value) {
            addCriterion("SUBMIT_FLAG <>", value, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagGreaterThan(String value) {
            addCriterion("SUBMIT_FLAG >", value, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SUBMIT_FLAG >=", value, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagLessThan(String value) {
            addCriterion("SUBMIT_FLAG <", value, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagLessThanOrEqualTo(String value) {
            addCriterion("SUBMIT_FLAG <=", value, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagLike(String value) {
            addCriterion("SUBMIT_FLAG like", value, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagNotLike(String value) {
            addCriterion("SUBMIT_FLAG not like", value, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagIn(List<String> values) {
            addCriterion("SUBMIT_FLAG in", values, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagNotIn(List<String> values) {
            addCriterion("SUBMIT_FLAG not in", values, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagBetween(String value1, String value2) {
            addCriterion("SUBMIT_FLAG between", value1, value2, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagNotBetween(String value1, String value2) {
            addCriterion("SUBMIT_FLAG not between", value1, value2, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIsNull() {
            addCriterion("AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIsNotNull() {
            addCriterion("AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID =", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID <>", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdGreaterThan(String value) {
            addCriterion("AUDIT_STATUS_ID >", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID >=", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLessThan(String value) {
            addCriterion("AUDIT_STATUS_ID <", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID <=", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLike(String value) {
            addCriterion("AUDIT_STATUS_ID like", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotLike(String value) {
            addCriterion("AUDIT_STATUS_ID not like", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIn(List<String> values) {
            addCriterion("AUDIT_STATUS_ID in", values, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotIn(List<String> values) {
            addCriterion("AUDIT_STATUS_ID not in", values, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_ID between", value1, value2, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_ID not between", value1, value2, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIsNull() {
            addCriterion("AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIsNotNull() {
            addCriterion("AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME =", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME <>", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameGreaterThan(String value) {
            addCriterion("AUDIT_STATUS_NAME >", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME >=", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLessThan(String value) {
            addCriterion("AUDIT_STATUS_NAME <", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME <=", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLike(String value) {
            addCriterion("AUDIT_STATUS_NAME like", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotLike(String value) {
            addCriterion("AUDIT_STATUS_NAME not like", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIn(List<String> values) {
            addCriterion("AUDIT_STATUS_NAME in", values, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotIn(List<String> values) {
            addCriterion("AUDIT_STATUS_NAME not in", values, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_NAME between", value1, value2, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_NAME not between", value1, value2, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andDegreeCourseFlagIsNull() {
            addCriterion("DEGREE_COURSE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andDegreeCourseFlagIsNotNull() {
            addCriterion("DEGREE_COURSE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeCourseFlagEqualTo(String value) {
            addCriterion("DEGREE_COURSE_FLAG =", value, "degreeCourseFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeCourseFlagNotEqualTo(String value) {
            addCriterion("DEGREE_COURSE_FLAG <>", value, "degreeCourseFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeCourseFlagGreaterThan(String value) {
            addCriterion("DEGREE_COURSE_FLAG >", value, "degreeCourseFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeCourseFlagGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_COURSE_FLAG >=", value, "degreeCourseFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeCourseFlagLessThan(String value) {
            addCriterion("DEGREE_COURSE_FLAG <", value, "degreeCourseFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeCourseFlagLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_COURSE_FLAG <=", value, "degreeCourseFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeCourseFlagLike(String value) {
            addCriterion("DEGREE_COURSE_FLAG like", value, "degreeCourseFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeCourseFlagNotLike(String value) {
            addCriterion("DEGREE_COURSE_FLAG not like", value, "degreeCourseFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeCourseFlagIn(List<String> values) {
            addCriterion("DEGREE_COURSE_FLAG in", values, "degreeCourseFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeCourseFlagNotIn(List<String> values) {
            addCriterion("DEGREE_COURSE_FLAG not in", values, "degreeCourseFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeCourseFlagBetween(String value1, String value2) {
            addCriterion("DEGREE_COURSE_FLAG between", value1, value2, "degreeCourseFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeCourseFlagNotBetween(String value1, String value2) {
            addCriterion("DEGREE_COURSE_FLAG not between", value1, value2, "degreeCourseFlag");
            return (Criteria) this;
        }

        public Criteria andScoreModeIsNull() {
            addCriterion("SCORE_MODE is null");
            return (Criteria) this;
        }

        public Criteria andScoreModeIsNotNull() {
            addCriterion("SCORE_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andScoreModeEqualTo(String value) {
            addCriterion("SCORE_MODE =", value, "scoreMode");
            return (Criteria) this;
        }

        public Criteria andScoreModeNotEqualTo(String value) {
            addCriterion("SCORE_MODE <>", value, "scoreMode");
            return (Criteria) this;
        }

        public Criteria andScoreModeGreaterThan(String value) {
            addCriterion("SCORE_MODE >", value, "scoreMode");
            return (Criteria) this;
        }

        public Criteria andScoreModeGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_MODE >=", value, "scoreMode");
            return (Criteria) this;
        }

        public Criteria andScoreModeLessThan(String value) {
            addCriterion("SCORE_MODE <", value, "scoreMode");
            return (Criteria) this;
        }

        public Criteria andScoreModeLessThanOrEqualTo(String value) {
            addCriterion("SCORE_MODE <=", value, "scoreMode");
            return (Criteria) this;
        }

        public Criteria andScoreModeLike(String value) {
            addCriterion("SCORE_MODE like", value, "scoreMode");
            return (Criteria) this;
        }

        public Criteria andScoreModeNotLike(String value) {
            addCriterion("SCORE_MODE not like", value, "scoreMode");
            return (Criteria) this;
        }

        public Criteria andScoreModeIn(List<String> values) {
            addCriterion("SCORE_MODE in", values, "scoreMode");
            return (Criteria) this;
        }

        public Criteria andScoreModeNotIn(List<String> values) {
            addCriterion("SCORE_MODE not in", values, "scoreMode");
            return (Criteria) this;
        }

        public Criteria andScoreModeBetween(String value1, String value2) {
            addCriterion("SCORE_MODE between", value1, value2, "scoreMode");
            return (Criteria) this;
        }

        public Criteria andScoreModeNotBetween(String value1, String value2) {
            addCriterion("SCORE_MODE not between", value1, value2, "scoreMode");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusIdIsNull() {
            addCriterion("CDDW_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusIdIsNotNull() {
            addCriterion("CDDW_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusIdEqualTo(String value) {
            addCriterion("CDDW_AUDIT_STATUS_ID =", value, "cddwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusIdNotEqualTo(String value) {
            addCriterion("CDDW_AUDIT_STATUS_ID <>", value, "cddwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusIdGreaterThan(String value) {
            addCriterion("CDDW_AUDIT_STATUS_ID >", value, "cddwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("CDDW_AUDIT_STATUS_ID >=", value, "cddwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusIdLessThan(String value) {
            addCriterion("CDDW_AUDIT_STATUS_ID <", value, "cddwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("CDDW_AUDIT_STATUS_ID <=", value, "cddwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusIdLike(String value) {
            addCriterion("CDDW_AUDIT_STATUS_ID like", value, "cddwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusIdNotLike(String value) {
            addCriterion("CDDW_AUDIT_STATUS_ID not like", value, "cddwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusIdIn(List<String> values) {
            addCriterion("CDDW_AUDIT_STATUS_ID in", values, "cddwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusIdNotIn(List<String> values) {
            addCriterion("CDDW_AUDIT_STATUS_ID not in", values, "cddwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusIdBetween(String value1, String value2) {
            addCriterion("CDDW_AUDIT_STATUS_ID between", value1, value2, "cddwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("CDDW_AUDIT_STATUS_ID not between", value1, value2, "cddwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusNameIsNull() {
            addCriterion("CDDW_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusNameIsNotNull() {
            addCriterion("CDDW_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusNameEqualTo(String value) {
            addCriterion("CDDW_AUDIT_STATUS_NAME =", value, "cddwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusNameNotEqualTo(String value) {
            addCriterion("CDDW_AUDIT_STATUS_NAME <>", value, "cddwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusNameGreaterThan(String value) {
            addCriterion("CDDW_AUDIT_STATUS_NAME >", value, "cddwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("CDDW_AUDIT_STATUS_NAME >=", value, "cddwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusNameLessThan(String value) {
            addCriterion("CDDW_AUDIT_STATUS_NAME <", value, "cddwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("CDDW_AUDIT_STATUS_NAME <=", value, "cddwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusNameLike(String value) {
            addCriterion("CDDW_AUDIT_STATUS_NAME like", value, "cddwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusNameNotLike(String value) {
            addCriterion("CDDW_AUDIT_STATUS_NAME not like", value, "cddwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusNameIn(List<String> values) {
            addCriterion("CDDW_AUDIT_STATUS_NAME in", values, "cddwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusNameNotIn(List<String> values) {
            addCriterion("CDDW_AUDIT_STATUS_NAME not in", values, "cddwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusNameBetween(String value1, String value2) {
            addCriterion("CDDW_AUDIT_STATUS_NAME between", value1, value2, "cddwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCddwAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("CDDW_AUDIT_STATUS_NAME not between", value1, value2, "cddwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andGradeYearIsNull() {
            addCriterion("GRADE_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andGradeYearIsNotNull() {
            addCriterion("GRADE_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andGradeYearEqualTo(String value) {
            addCriterion("GRADE_YEAR =", value, "gradeYear");
            return (Criteria) this;
        }

        public Criteria andGradeYearNotEqualTo(String value) {
            addCriterion("GRADE_YEAR <>", value, "gradeYear");
            return (Criteria) this;
        }

        public Criteria andGradeYearGreaterThan(String value) {
            addCriterion("GRADE_YEAR >", value, "gradeYear");
            return (Criteria) this;
        }

        public Criteria andGradeYearGreaterThanOrEqualTo(String value) {
            addCriterion("GRADE_YEAR >=", value, "gradeYear");
            return (Criteria) this;
        }

        public Criteria andGradeYearLessThan(String value) {
            addCriterion("GRADE_YEAR <", value, "gradeYear");
            return (Criteria) this;
        }

        public Criteria andGradeYearLessThanOrEqualTo(String value) {
            addCriterion("GRADE_YEAR <=", value, "gradeYear");
            return (Criteria) this;
        }

        public Criteria andGradeYearLike(String value) {
            addCriterion("GRADE_YEAR like", value, "gradeYear");
            return (Criteria) this;
        }

        public Criteria andGradeYearNotLike(String value) {
            addCriterion("GRADE_YEAR not like", value, "gradeYear");
            return (Criteria) this;
        }

        public Criteria andGradeYearIn(List<String> values) {
            addCriterion("GRADE_YEAR in", values, "gradeYear");
            return (Criteria) this;
        }

        public Criteria andGradeYearNotIn(List<String> values) {
            addCriterion("GRADE_YEAR not in", values, "gradeYear");
            return (Criteria) this;
        }

        public Criteria andGradeYearBetween(String value1, String value2) {
            addCriterion("GRADE_YEAR between", value1, value2, "gradeYear");
            return (Criteria) this;
        }

        public Criteria andGradeYearNotBetween(String value1, String value2) {
            addCriterion("GRADE_YEAR not between", value1, value2, "gradeYear");
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