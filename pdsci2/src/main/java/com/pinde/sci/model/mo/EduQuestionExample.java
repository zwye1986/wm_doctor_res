package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EduQuestionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EduQuestionExample() {
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

        public Criteria andQuestionFlowIsNull() {
            addCriterion("QUESTION_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowIsNotNull() {
            addCriterion("QUESTION_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowEqualTo(String value) {
            addCriterion("QUESTION_FLOW =", value, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowNotEqualTo(String value) {
            addCriterion("QUESTION_FLOW <>", value, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowGreaterThan(String value) {
            addCriterion("QUESTION_FLOW >", value, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTION_FLOW >=", value, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowLessThan(String value) {
            addCriterion("QUESTION_FLOW <", value, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowLessThanOrEqualTo(String value) {
            addCriterion("QUESTION_FLOW <=", value, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowLike(String value) {
            addCriterion("QUESTION_FLOW like", value, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowNotLike(String value) {
            addCriterion("QUESTION_FLOW not like", value, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowIn(List<String> values) {
            addCriterion("QUESTION_FLOW in", values, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowNotIn(List<String> values) {
            addCriterion("QUESTION_FLOW not in", values, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowBetween(String value1, String value2) {
            addCriterion("QUESTION_FLOW between", value1, value2, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowNotBetween(String value1, String value2) {
            addCriterion("QUESTION_FLOW not between", value1, value2, "questionFlow");
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

        public Criteria andQuestionStatusIdIsNull() {
            addCriterion("QUESTION_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusIdIsNotNull() {
            addCriterion("QUESTION_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusIdEqualTo(String value) {
            addCriterion("QUESTION_STATUS_ID =", value, "questionStatusId");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusIdNotEqualTo(String value) {
            addCriterion("QUESTION_STATUS_ID <>", value, "questionStatusId");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusIdGreaterThan(String value) {
            addCriterion("QUESTION_STATUS_ID >", value, "questionStatusId");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTION_STATUS_ID >=", value, "questionStatusId");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusIdLessThan(String value) {
            addCriterion("QUESTION_STATUS_ID <", value, "questionStatusId");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusIdLessThanOrEqualTo(String value) {
            addCriterion("QUESTION_STATUS_ID <=", value, "questionStatusId");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusIdLike(String value) {
            addCriterion("QUESTION_STATUS_ID like", value, "questionStatusId");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusIdNotLike(String value) {
            addCriterion("QUESTION_STATUS_ID not like", value, "questionStatusId");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusIdIn(List<String> values) {
            addCriterion("QUESTION_STATUS_ID in", values, "questionStatusId");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusIdNotIn(List<String> values) {
            addCriterion("QUESTION_STATUS_ID not in", values, "questionStatusId");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusIdBetween(String value1, String value2) {
            addCriterion("QUESTION_STATUS_ID between", value1, value2, "questionStatusId");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusIdNotBetween(String value1, String value2) {
            addCriterion("QUESTION_STATUS_ID not between", value1, value2, "questionStatusId");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusNameIsNull() {
            addCriterion("QUESTION_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusNameIsNotNull() {
            addCriterion("QUESTION_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusNameEqualTo(String value) {
            addCriterion("QUESTION_STATUS_NAME =", value, "questionStatusName");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusNameNotEqualTo(String value) {
            addCriterion("QUESTION_STATUS_NAME <>", value, "questionStatusName");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusNameGreaterThan(String value) {
            addCriterion("QUESTION_STATUS_NAME >", value, "questionStatusName");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTION_STATUS_NAME >=", value, "questionStatusName");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusNameLessThan(String value) {
            addCriterion("QUESTION_STATUS_NAME <", value, "questionStatusName");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusNameLessThanOrEqualTo(String value) {
            addCriterion("QUESTION_STATUS_NAME <=", value, "questionStatusName");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusNameLike(String value) {
            addCriterion("QUESTION_STATUS_NAME like", value, "questionStatusName");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusNameNotLike(String value) {
            addCriterion("QUESTION_STATUS_NAME not like", value, "questionStatusName");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusNameIn(List<String> values) {
            addCriterion("QUESTION_STATUS_NAME in", values, "questionStatusName");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusNameNotIn(List<String> values) {
            addCriterion("QUESTION_STATUS_NAME not in", values, "questionStatusName");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusNameBetween(String value1, String value2) {
            addCriterion("QUESTION_STATUS_NAME between", value1, value2, "questionStatusName");
            return (Criteria) this;
        }

        public Criteria andQuestionStatusNameNotBetween(String value1, String value2) {
            addCriterion("QUESTION_STATUS_NAME not between", value1, value2, "questionStatusName");
            return (Criteria) this;
        }

        public Criteria andQuestionContentIsNull() {
            addCriterion("QUESTION_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andQuestionContentIsNotNull() {
            addCriterion("QUESTION_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionContentEqualTo(String value) {
            addCriterion("QUESTION_CONTENT =", value, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentNotEqualTo(String value) {
            addCriterion("QUESTION_CONTENT <>", value, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentGreaterThan(String value) {
            addCriterion("QUESTION_CONTENT >", value, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTION_CONTENT >=", value, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentLessThan(String value) {
            addCriterion("QUESTION_CONTENT <", value, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentLessThanOrEqualTo(String value) {
            addCriterion("QUESTION_CONTENT <=", value, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentLike(String value) {
            addCriterion("QUESTION_CONTENT like", value, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentNotLike(String value) {
            addCriterion("QUESTION_CONTENT not like", value, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentIn(List<String> values) {
            addCriterion("QUESTION_CONTENT in", values, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentNotIn(List<String> values) {
            addCriterion("QUESTION_CONTENT not in", values, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentBetween(String value1, String value2) {
            addCriterion("QUESTION_CONTENT between", value1, value2, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentNotBetween(String value1, String value2) {
            addCriterion("QUESTION_CONTENT not between", value1, value2, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionTimeIsNull() {
            addCriterion("QUESTION_TIME is null");
            return (Criteria) this;
        }

        public Criteria andQuestionTimeIsNotNull() {
            addCriterion("QUESTION_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionTimeEqualTo(String value) {
            addCriterion("QUESTION_TIME =", value, "questionTime");
            return (Criteria) this;
        }

        public Criteria andQuestionTimeNotEqualTo(String value) {
            addCriterion("QUESTION_TIME <>", value, "questionTime");
            return (Criteria) this;
        }

        public Criteria andQuestionTimeGreaterThan(String value) {
            addCriterion("QUESTION_TIME >", value, "questionTime");
            return (Criteria) this;
        }

        public Criteria andQuestionTimeGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTION_TIME >=", value, "questionTime");
            return (Criteria) this;
        }

        public Criteria andQuestionTimeLessThan(String value) {
            addCriterion("QUESTION_TIME <", value, "questionTime");
            return (Criteria) this;
        }

        public Criteria andQuestionTimeLessThanOrEqualTo(String value) {
            addCriterion("QUESTION_TIME <=", value, "questionTime");
            return (Criteria) this;
        }

        public Criteria andQuestionTimeLike(String value) {
            addCriterion("QUESTION_TIME like", value, "questionTime");
            return (Criteria) this;
        }

        public Criteria andQuestionTimeNotLike(String value) {
            addCriterion("QUESTION_TIME not like", value, "questionTime");
            return (Criteria) this;
        }

        public Criteria andQuestionTimeIn(List<String> values) {
            addCriterion("QUESTION_TIME in", values, "questionTime");
            return (Criteria) this;
        }

        public Criteria andQuestionTimeNotIn(List<String> values) {
            addCriterion("QUESTION_TIME not in", values, "questionTime");
            return (Criteria) this;
        }

        public Criteria andQuestionTimeBetween(String value1, String value2) {
            addCriterion("QUESTION_TIME between", value1, value2, "questionTime");
            return (Criteria) this;
        }

        public Criteria andQuestionTimeNotBetween(String value1, String value2) {
            addCriterion("QUESTION_TIME not between", value1, value2, "questionTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTeacherIsNull() {
            addCriterion("SUBMIT_TEACHER is null");
            return (Criteria) this;
        }

        public Criteria andSubmitTeacherIsNotNull() {
            addCriterion("SUBMIT_TEACHER is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitTeacherEqualTo(String value) {
            addCriterion("SUBMIT_TEACHER =", value, "submitTeacher");
            return (Criteria) this;
        }

        public Criteria andSubmitTeacherNotEqualTo(String value) {
            addCriterion("SUBMIT_TEACHER <>", value, "submitTeacher");
            return (Criteria) this;
        }

        public Criteria andSubmitTeacherGreaterThan(String value) {
            addCriterion("SUBMIT_TEACHER >", value, "submitTeacher");
            return (Criteria) this;
        }

        public Criteria andSubmitTeacherGreaterThanOrEqualTo(String value) {
            addCriterion("SUBMIT_TEACHER >=", value, "submitTeacher");
            return (Criteria) this;
        }

        public Criteria andSubmitTeacherLessThan(String value) {
            addCriterion("SUBMIT_TEACHER <", value, "submitTeacher");
            return (Criteria) this;
        }

        public Criteria andSubmitTeacherLessThanOrEqualTo(String value) {
            addCriterion("SUBMIT_TEACHER <=", value, "submitTeacher");
            return (Criteria) this;
        }

        public Criteria andSubmitTeacherLike(String value) {
            addCriterion("SUBMIT_TEACHER like", value, "submitTeacher");
            return (Criteria) this;
        }

        public Criteria andSubmitTeacherNotLike(String value) {
            addCriterion("SUBMIT_TEACHER not like", value, "submitTeacher");
            return (Criteria) this;
        }

        public Criteria andSubmitTeacherIn(List<String> values) {
            addCriterion("SUBMIT_TEACHER in", values, "submitTeacher");
            return (Criteria) this;
        }

        public Criteria andSubmitTeacherNotIn(List<String> values) {
            addCriterion("SUBMIT_TEACHER not in", values, "submitTeacher");
            return (Criteria) this;
        }

        public Criteria andSubmitTeacherBetween(String value1, String value2) {
            addCriterion("SUBMIT_TEACHER between", value1, value2, "submitTeacher");
            return (Criteria) this;
        }

        public Criteria andSubmitTeacherNotBetween(String value1, String value2) {
            addCriterion("SUBMIT_TEACHER not between", value1, value2, "submitTeacher");
            return (Criteria) this;
        }

        public Criteria andQuintessenceIsNull() {
            addCriterion("QUINTESSENCE is null");
            return (Criteria) this;
        }

        public Criteria andQuintessenceIsNotNull() {
            addCriterion("QUINTESSENCE is not null");
            return (Criteria) this;
        }

        public Criteria andQuintessenceEqualTo(String value) {
            addCriterion("QUINTESSENCE =", value, "quintessence");
            return (Criteria) this;
        }

        public Criteria andQuintessenceNotEqualTo(String value) {
            addCriterion("QUINTESSENCE <>", value, "quintessence");
            return (Criteria) this;
        }

        public Criteria andQuintessenceGreaterThan(String value) {
            addCriterion("QUINTESSENCE >", value, "quintessence");
            return (Criteria) this;
        }

        public Criteria andQuintessenceGreaterThanOrEqualTo(String value) {
            addCriterion("QUINTESSENCE >=", value, "quintessence");
            return (Criteria) this;
        }

        public Criteria andQuintessenceLessThan(String value) {
            addCriterion("QUINTESSENCE <", value, "quintessence");
            return (Criteria) this;
        }

        public Criteria andQuintessenceLessThanOrEqualTo(String value) {
            addCriterion("QUINTESSENCE <=", value, "quintessence");
            return (Criteria) this;
        }

        public Criteria andQuintessenceLike(String value) {
            addCriterion("QUINTESSENCE like", value, "quintessence");
            return (Criteria) this;
        }

        public Criteria andQuintessenceNotLike(String value) {
            addCriterion("QUINTESSENCE not like", value, "quintessence");
            return (Criteria) this;
        }

        public Criteria andQuintessenceIn(List<String> values) {
            addCriterion("QUINTESSENCE in", values, "quintessence");
            return (Criteria) this;
        }

        public Criteria andQuintessenceNotIn(List<String> values) {
            addCriterion("QUINTESSENCE not in", values, "quintessence");
            return (Criteria) this;
        }

        public Criteria andQuintessenceBetween(String value1, String value2) {
            addCriterion("QUINTESSENCE between", value1, value2, "quintessence");
            return (Criteria) this;
        }

        public Criteria andQuintessenceNotBetween(String value1, String value2) {
            addCriterion("QUINTESSENCE not between", value1, value2, "quintessence");
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

        public Criteria andQuesTypeIdIsNull() {
            addCriterion("QUES_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andQuesTypeIdIsNotNull() {
            addCriterion("QUES_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andQuesTypeIdEqualTo(String value) {
            addCriterion("QUES_TYPE_ID =", value, "quesTypeId");
            return (Criteria) this;
        }

        public Criteria andQuesTypeIdNotEqualTo(String value) {
            addCriterion("QUES_TYPE_ID <>", value, "quesTypeId");
            return (Criteria) this;
        }

        public Criteria andQuesTypeIdGreaterThan(String value) {
            addCriterion("QUES_TYPE_ID >", value, "quesTypeId");
            return (Criteria) this;
        }

        public Criteria andQuesTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("QUES_TYPE_ID >=", value, "quesTypeId");
            return (Criteria) this;
        }

        public Criteria andQuesTypeIdLessThan(String value) {
            addCriterion("QUES_TYPE_ID <", value, "quesTypeId");
            return (Criteria) this;
        }

        public Criteria andQuesTypeIdLessThanOrEqualTo(String value) {
            addCriterion("QUES_TYPE_ID <=", value, "quesTypeId");
            return (Criteria) this;
        }

        public Criteria andQuesTypeIdLike(String value) {
            addCriterion("QUES_TYPE_ID like", value, "quesTypeId");
            return (Criteria) this;
        }

        public Criteria andQuesTypeIdNotLike(String value) {
            addCriterion("QUES_TYPE_ID not like", value, "quesTypeId");
            return (Criteria) this;
        }

        public Criteria andQuesTypeIdIn(List<String> values) {
            addCriterion("QUES_TYPE_ID in", values, "quesTypeId");
            return (Criteria) this;
        }

        public Criteria andQuesTypeIdNotIn(List<String> values) {
            addCriterion("QUES_TYPE_ID not in", values, "quesTypeId");
            return (Criteria) this;
        }

        public Criteria andQuesTypeIdBetween(String value1, String value2) {
            addCriterion("QUES_TYPE_ID between", value1, value2, "quesTypeId");
            return (Criteria) this;
        }

        public Criteria andQuesTypeIdNotBetween(String value1, String value2) {
            addCriterion("QUES_TYPE_ID not between", value1, value2, "quesTypeId");
            return (Criteria) this;
        }

        public Criteria andQuesTypeNameIsNull() {
            addCriterion("QUES_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andQuesTypeNameIsNotNull() {
            addCriterion("QUES_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andQuesTypeNameEqualTo(String value) {
            addCriterion("QUES_TYPE_NAME =", value, "quesTypeName");
            return (Criteria) this;
        }

        public Criteria andQuesTypeNameNotEqualTo(String value) {
            addCriterion("QUES_TYPE_NAME <>", value, "quesTypeName");
            return (Criteria) this;
        }

        public Criteria andQuesTypeNameGreaterThan(String value) {
            addCriterion("QUES_TYPE_NAME >", value, "quesTypeName");
            return (Criteria) this;
        }

        public Criteria andQuesTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("QUES_TYPE_NAME >=", value, "quesTypeName");
            return (Criteria) this;
        }

        public Criteria andQuesTypeNameLessThan(String value) {
            addCriterion("QUES_TYPE_NAME <", value, "quesTypeName");
            return (Criteria) this;
        }

        public Criteria andQuesTypeNameLessThanOrEqualTo(String value) {
            addCriterion("QUES_TYPE_NAME <=", value, "quesTypeName");
            return (Criteria) this;
        }

        public Criteria andQuesTypeNameLike(String value) {
            addCriterion("QUES_TYPE_NAME like", value, "quesTypeName");
            return (Criteria) this;
        }

        public Criteria andQuesTypeNameNotLike(String value) {
            addCriterion("QUES_TYPE_NAME not like", value, "quesTypeName");
            return (Criteria) this;
        }

        public Criteria andQuesTypeNameIn(List<String> values) {
            addCriterion("QUES_TYPE_NAME in", values, "quesTypeName");
            return (Criteria) this;
        }

        public Criteria andQuesTypeNameNotIn(List<String> values) {
            addCriterion("QUES_TYPE_NAME not in", values, "quesTypeName");
            return (Criteria) this;
        }

        public Criteria andQuesTypeNameBetween(String value1, String value2) {
            addCriterion("QUES_TYPE_NAME between", value1, value2, "quesTypeName");
            return (Criteria) this;
        }

        public Criteria andQuesTypeNameNotBetween(String value1, String value2) {
            addCriterion("QUES_TYPE_NAME not between", value1, value2, "quesTypeName");
            return (Criteria) this;
        }

        public Criteria andQuesDirIsNull() {
            addCriterion("QUES_DIR is null");
            return (Criteria) this;
        }

        public Criteria andQuesDirIsNotNull() {
            addCriterion("QUES_DIR is not null");
            return (Criteria) this;
        }

        public Criteria andQuesDirEqualTo(String value) {
            addCriterion("QUES_DIR =", value, "quesDir");
            return (Criteria) this;
        }

        public Criteria andQuesDirNotEqualTo(String value) {
            addCriterion("QUES_DIR <>", value, "quesDir");
            return (Criteria) this;
        }

        public Criteria andQuesDirGreaterThan(String value) {
            addCriterion("QUES_DIR >", value, "quesDir");
            return (Criteria) this;
        }

        public Criteria andQuesDirGreaterThanOrEqualTo(String value) {
            addCriterion("QUES_DIR >=", value, "quesDir");
            return (Criteria) this;
        }

        public Criteria andQuesDirLessThan(String value) {
            addCriterion("QUES_DIR <", value, "quesDir");
            return (Criteria) this;
        }

        public Criteria andQuesDirLessThanOrEqualTo(String value) {
            addCriterion("QUES_DIR <=", value, "quesDir");
            return (Criteria) this;
        }

        public Criteria andQuesDirLike(String value) {
            addCriterion("QUES_DIR like", value, "quesDir");
            return (Criteria) this;
        }

        public Criteria andQuesDirNotLike(String value) {
            addCriterion("QUES_DIR not like", value, "quesDir");
            return (Criteria) this;
        }

        public Criteria andQuesDirIn(List<String> values) {
            addCriterion("QUES_DIR in", values, "quesDir");
            return (Criteria) this;
        }

        public Criteria andQuesDirNotIn(List<String> values) {
            addCriterion("QUES_DIR not in", values, "quesDir");
            return (Criteria) this;
        }

        public Criteria andQuesDirBetween(String value1, String value2) {
            addCriterion("QUES_DIR between", value1, value2, "quesDir");
            return (Criteria) this;
        }

        public Criteria andQuesDirNotBetween(String value1, String value2) {
            addCriterion("QUES_DIR not between", value1, value2, "quesDir");
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