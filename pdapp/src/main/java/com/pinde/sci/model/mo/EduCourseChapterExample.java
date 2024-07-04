package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EduCourseChapterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EduCourseChapterExample() {
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

        public Criteria andChapterNameIsNull() {
            addCriterion("CHAPTER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChapterNameIsNotNull() {
            addCriterion("CHAPTER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChapterNameEqualTo(String value) {
            addCriterion("CHAPTER_NAME =", value, "chapterName");
            return (Criteria) this;
        }

        public Criteria andChapterNameNotEqualTo(String value) {
            addCriterion("CHAPTER_NAME <>", value, "chapterName");
            return (Criteria) this;
        }

        public Criteria andChapterNameGreaterThan(String value) {
            addCriterion("CHAPTER_NAME >", value, "chapterName");
            return (Criteria) this;
        }

        public Criteria andChapterNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHAPTER_NAME >=", value, "chapterName");
            return (Criteria) this;
        }

        public Criteria andChapterNameLessThan(String value) {
            addCriterion("CHAPTER_NAME <", value, "chapterName");
            return (Criteria) this;
        }

        public Criteria andChapterNameLessThanOrEqualTo(String value) {
            addCriterion("CHAPTER_NAME <=", value, "chapterName");
            return (Criteria) this;
        }

        public Criteria andChapterNameLike(String value) {
            addCriterion("CHAPTER_NAME like", value, "chapterName");
            return (Criteria) this;
        }

        public Criteria andChapterNameNotLike(String value) {
            addCriterion("CHAPTER_NAME not like", value, "chapterName");
            return (Criteria) this;
        }

        public Criteria andChapterNameIn(List<String> values) {
            addCriterion("CHAPTER_NAME in", values, "chapterName");
            return (Criteria) this;
        }

        public Criteria andChapterNameNotIn(List<String> values) {
            addCriterion("CHAPTER_NAME not in", values, "chapterName");
            return (Criteria) this;
        }

        public Criteria andChapterNameBetween(String value1, String value2) {
            addCriterion("CHAPTER_NAME between", value1, value2, "chapterName");
            return (Criteria) this;
        }

        public Criteria andChapterNameNotBetween(String value1, String value2) {
            addCriterion("CHAPTER_NAME not between", value1, value2, "chapterName");
            return (Criteria) this;
        }

        public Criteria andChapterNoIsNull() {
            addCriterion("CHAPTER_NO is null");
            return (Criteria) this;
        }

        public Criteria andChapterNoIsNotNull() {
            addCriterion("CHAPTER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andChapterNoEqualTo(String value) {
            addCriterion("CHAPTER_NO =", value, "chapterNo");
            return (Criteria) this;
        }

        public Criteria andChapterNoNotEqualTo(String value) {
            addCriterion("CHAPTER_NO <>", value, "chapterNo");
            return (Criteria) this;
        }

        public Criteria andChapterNoGreaterThan(String value) {
            addCriterion("CHAPTER_NO >", value, "chapterNo");
            return (Criteria) this;
        }

        public Criteria andChapterNoGreaterThanOrEqualTo(String value) {
            addCriterion("CHAPTER_NO >=", value, "chapterNo");
            return (Criteria) this;
        }

        public Criteria andChapterNoLessThan(String value) {
            addCriterion("CHAPTER_NO <", value, "chapterNo");
            return (Criteria) this;
        }

        public Criteria andChapterNoLessThanOrEqualTo(String value) {
            addCriterion("CHAPTER_NO <=", value, "chapterNo");
            return (Criteria) this;
        }

        public Criteria andChapterNoLike(String value) {
            addCriterion("CHAPTER_NO like", value, "chapterNo");
            return (Criteria) this;
        }

        public Criteria andChapterNoNotLike(String value) {
            addCriterion("CHAPTER_NO not like", value, "chapterNo");
            return (Criteria) this;
        }

        public Criteria andChapterNoIn(List<String> values) {
            addCriterion("CHAPTER_NO in", values, "chapterNo");
            return (Criteria) this;
        }

        public Criteria andChapterNoNotIn(List<String> values) {
            addCriterion("CHAPTER_NO not in", values, "chapterNo");
            return (Criteria) this;
        }

        public Criteria andChapterNoBetween(String value1, String value2) {
            addCriterion("CHAPTER_NO between", value1, value2, "chapterNo");
            return (Criteria) this;
        }

        public Criteria andChapterNoNotBetween(String value1, String value2) {
            addCriterion("CHAPTER_NO not between", value1, value2, "chapterNo");
            return (Criteria) this;
        }

        public Criteria andParentChapterFlowIsNull() {
            addCriterion("PARENT_CHAPTER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andParentChapterFlowIsNotNull() {
            addCriterion("PARENT_CHAPTER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andParentChapterFlowEqualTo(String value) {
            addCriterion("PARENT_CHAPTER_FLOW =", value, "parentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andParentChapterFlowNotEqualTo(String value) {
            addCriterion("PARENT_CHAPTER_FLOW <>", value, "parentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andParentChapterFlowGreaterThan(String value) {
            addCriterion("PARENT_CHAPTER_FLOW >", value, "parentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andParentChapterFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_CHAPTER_FLOW >=", value, "parentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andParentChapterFlowLessThan(String value) {
            addCriterion("PARENT_CHAPTER_FLOW <", value, "parentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andParentChapterFlowLessThanOrEqualTo(String value) {
            addCriterion("PARENT_CHAPTER_FLOW <=", value, "parentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andParentChapterFlowLike(String value) {
            addCriterion("PARENT_CHAPTER_FLOW like", value, "parentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andParentChapterFlowNotLike(String value) {
            addCriterion("PARENT_CHAPTER_FLOW not like", value, "parentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andParentChapterFlowIn(List<String> values) {
            addCriterion("PARENT_CHAPTER_FLOW in", values, "parentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andParentChapterFlowNotIn(List<String> values) {
            addCriterion("PARENT_CHAPTER_FLOW not in", values, "parentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andParentChapterFlowBetween(String value1, String value2) {
            addCriterion("PARENT_CHAPTER_FLOW between", value1, value2, "parentChapterFlow");
            return (Criteria) this;
        }

        public Criteria andParentChapterFlowNotBetween(String value1, String value2) {
            addCriterion("PARENT_CHAPTER_FLOW not between", value1, value2, "parentChapterFlow");
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

        public Criteria andChapterFileIsNull() {
            addCriterion("CHAPTER_FILE is null");
            return (Criteria) this;
        }

        public Criteria andChapterFileIsNotNull() {
            addCriterion("CHAPTER_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andChapterFileEqualTo(String value) {
            addCriterion("CHAPTER_FILE =", value, "chapterFile");
            return (Criteria) this;
        }

        public Criteria andChapterFileNotEqualTo(String value) {
            addCriterion("CHAPTER_FILE <>", value, "chapterFile");
            return (Criteria) this;
        }

        public Criteria andChapterFileGreaterThan(String value) {
            addCriterion("CHAPTER_FILE >", value, "chapterFile");
            return (Criteria) this;
        }

        public Criteria andChapterFileGreaterThanOrEqualTo(String value) {
            addCriterion("CHAPTER_FILE >=", value, "chapterFile");
            return (Criteria) this;
        }

        public Criteria andChapterFileLessThan(String value) {
            addCriterion("CHAPTER_FILE <", value, "chapterFile");
            return (Criteria) this;
        }

        public Criteria andChapterFileLessThanOrEqualTo(String value) {
            addCriterion("CHAPTER_FILE <=", value, "chapterFile");
            return (Criteria) this;
        }

        public Criteria andChapterFileLike(String value) {
            addCriterion("CHAPTER_FILE like", value, "chapterFile");
            return (Criteria) this;
        }

        public Criteria andChapterFileNotLike(String value) {
            addCriterion("CHAPTER_FILE not like", value, "chapterFile");
            return (Criteria) this;
        }

        public Criteria andChapterFileIn(List<String> values) {
            addCriterion("CHAPTER_FILE in", values, "chapterFile");
            return (Criteria) this;
        }

        public Criteria andChapterFileNotIn(List<String> values) {
            addCriterion("CHAPTER_FILE not in", values, "chapterFile");
            return (Criteria) this;
        }

        public Criteria andChapterFileBetween(String value1, String value2) {
            addCriterion("CHAPTER_FILE between", value1, value2, "chapterFile");
            return (Criteria) this;
        }

        public Criteria andChapterFileNotBetween(String value1, String value2) {
            addCriterion("CHAPTER_FILE not between", value1, value2, "chapterFile");
            return (Criteria) this;
        }

        public Criteria andChapterImgIsNull() {
            addCriterion("CHAPTER_IMG is null");
            return (Criteria) this;
        }

        public Criteria andChapterImgIsNotNull() {
            addCriterion("CHAPTER_IMG is not null");
            return (Criteria) this;
        }

        public Criteria andChapterImgEqualTo(String value) {
            addCriterion("CHAPTER_IMG =", value, "chapterImg");
            return (Criteria) this;
        }

        public Criteria andChapterImgNotEqualTo(String value) {
            addCriterion("CHAPTER_IMG <>", value, "chapterImg");
            return (Criteria) this;
        }

        public Criteria andChapterImgGreaterThan(String value) {
            addCriterion("CHAPTER_IMG >", value, "chapterImg");
            return (Criteria) this;
        }

        public Criteria andChapterImgGreaterThanOrEqualTo(String value) {
            addCriterion("CHAPTER_IMG >=", value, "chapterImg");
            return (Criteria) this;
        }

        public Criteria andChapterImgLessThan(String value) {
            addCriterion("CHAPTER_IMG <", value, "chapterImg");
            return (Criteria) this;
        }

        public Criteria andChapterImgLessThanOrEqualTo(String value) {
            addCriterion("CHAPTER_IMG <=", value, "chapterImg");
            return (Criteria) this;
        }

        public Criteria andChapterImgLike(String value) {
            addCriterion("CHAPTER_IMG like", value, "chapterImg");
            return (Criteria) this;
        }

        public Criteria andChapterImgNotLike(String value) {
            addCriterion("CHAPTER_IMG not like", value, "chapterImg");
            return (Criteria) this;
        }

        public Criteria andChapterImgIn(List<String> values) {
            addCriterion("CHAPTER_IMG in", values, "chapterImg");
            return (Criteria) this;
        }

        public Criteria andChapterImgNotIn(List<String> values) {
            addCriterion("CHAPTER_IMG not in", values, "chapterImg");
            return (Criteria) this;
        }

        public Criteria andChapterImgBetween(String value1, String value2) {
            addCriterion("CHAPTER_IMG between", value1, value2, "chapterImg");
            return (Criteria) this;
        }

        public Criteria andChapterImgNotBetween(String value1, String value2) {
            addCriterion("CHAPTER_IMG not between", value1, value2, "chapterImg");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIsNull() {
            addCriterion("TEACHER_ID is null");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIsNotNull() {
            addCriterion("TEACHER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherIdEqualTo(String value) {
            addCriterion("TEACHER_ID =", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotEqualTo(String value) {
            addCriterion("TEACHER_ID <>", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdGreaterThan(String value) {
            addCriterion("TEACHER_ID >", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_ID >=", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLessThan(String value) {
            addCriterion("TEACHER_ID <", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_ID <=", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLike(String value) {
            addCriterion("TEACHER_ID like", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotLike(String value) {
            addCriterion("TEACHER_ID not like", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIn(List<String> values) {
            addCriterion("TEACHER_ID in", values, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotIn(List<String> values) {
            addCriterion("TEACHER_ID not in", values, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdBetween(String value1, String value2) {
            addCriterion("TEACHER_ID between", value1, value2, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotBetween(String value1, String value2) {
            addCriterion("TEACHER_ID not between", value1, value2, "teacherId");
            return (Criteria) this;
        }

        public Criteria andChapterTimeIsNull() {
            addCriterion("CHAPTER_TIME is null");
            return (Criteria) this;
        }

        public Criteria andChapterTimeIsNotNull() {
            addCriterion("CHAPTER_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andChapterTimeEqualTo(String value) {
            addCriterion("CHAPTER_TIME =", value, "chapterTime");
            return (Criteria) this;
        }

        public Criteria andChapterTimeNotEqualTo(String value) {
            addCriterion("CHAPTER_TIME <>", value, "chapterTime");
            return (Criteria) this;
        }

        public Criteria andChapterTimeGreaterThan(String value) {
            addCriterion("CHAPTER_TIME >", value, "chapterTime");
            return (Criteria) this;
        }

        public Criteria andChapterTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CHAPTER_TIME >=", value, "chapterTime");
            return (Criteria) this;
        }

        public Criteria andChapterTimeLessThan(String value) {
            addCriterion("CHAPTER_TIME <", value, "chapterTime");
            return (Criteria) this;
        }

        public Criteria andChapterTimeLessThanOrEqualTo(String value) {
            addCriterion("CHAPTER_TIME <=", value, "chapterTime");
            return (Criteria) this;
        }

        public Criteria andChapterTimeLike(String value) {
            addCriterion("CHAPTER_TIME like", value, "chapterTime");
            return (Criteria) this;
        }

        public Criteria andChapterTimeNotLike(String value) {
            addCriterion("CHAPTER_TIME not like", value, "chapterTime");
            return (Criteria) this;
        }

        public Criteria andChapterTimeIn(List<String> values) {
            addCriterion("CHAPTER_TIME in", values, "chapterTime");
            return (Criteria) this;
        }

        public Criteria andChapterTimeNotIn(List<String> values) {
            addCriterion("CHAPTER_TIME not in", values, "chapterTime");
            return (Criteria) this;
        }

        public Criteria andChapterTimeBetween(String value1, String value2) {
            addCriterion("CHAPTER_TIME between", value1, value2, "chapterTime");
            return (Criteria) this;
        }

        public Criteria andChapterTimeNotBetween(String value1, String value2) {
            addCriterion("CHAPTER_TIME not between", value1, value2, "chapterTime");
            return (Criteria) this;
        }

        public Criteria andChapterPraiseIsNull() {
            addCriterion("CHAPTER_PRAISE is null");
            return (Criteria) this;
        }

        public Criteria andChapterPraiseIsNotNull() {
            addCriterion("CHAPTER_PRAISE is not null");
            return (Criteria) this;
        }

        public Criteria andChapterPraiseEqualTo(Long value) {
            addCriterion("CHAPTER_PRAISE =", value, "chapterPraise");
            return (Criteria) this;
        }

        public Criteria andChapterPraiseNotEqualTo(Long value) {
            addCriterion("CHAPTER_PRAISE <>", value, "chapterPraise");
            return (Criteria) this;
        }

        public Criteria andChapterPraiseGreaterThan(Long value) {
            addCriterion("CHAPTER_PRAISE >", value, "chapterPraise");
            return (Criteria) this;
        }

        public Criteria andChapterPraiseGreaterThanOrEqualTo(Long value) {
            addCriterion("CHAPTER_PRAISE >=", value, "chapterPraise");
            return (Criteria) this;
        }

        public Criteria andChapterPraiseLessThan(Long value) {
            addCriterion("CHAPTER_PRAISE <", value, "chapterPraise");
            return (Criteria) this;
        }

        public Criteria andChapterPraiseLessThanOrEqualTo(Long value) {
            addCriterion("CHAPTER_PRAISE <=", value, "chapterPraise");
            return (Criteria) this;
        }

        public Criteria andChapterPraiseIn(List<Long> values) {
            addCriterion("CHAPTER_PRAISE in", values, "chapterPraise");
            return (Criteria) this;
        }

        public Criteria andChapterPraiseNotIn(List<Long> values) {
            addCriterion("CHAPTER_PRAISE not in", values, "chapterPraise");
            return (Criteria) this;
        }

        public Criteria andChapterPraiseBetween(Long value1, Long value2) {
            addCriterion("CHAPTER_PRAISE between", value1, value2, "chapterPraise");
            return (Criteria) this;
        }

        public Criteria andChapterPraiseNotBetween(Long value1, Long value2) {
            addCriterion("CHAPTER_PRAISE not between", value1, value2, "chapterPraise");
            return (Criteria) this;
        }

        public Criteria andCollectionCountIsNull() {
            addCriterion("COLLECTION_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andCollectionCountIsNotNull() {
            addCriterion("COLLECTION_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionCountEqualTo(Long value) {
            addCriterion("COLLECTION_COUNT =", value, "collectionCount");
            return (Criteria) this;
        }

        public Criteria andCollectionCountNotEqualTo(Long value) {
            addCriterion("COLLECTION_COUNT <>", value, "collectionCount");
            return (Criteria) this;
        }

        public Criteria andCollectionCountGreaterThan(Long value) {
            addCriterion("COLLECTION_COUNT >", value, "collectionCount");
            return (Criteria) this;
        }

        public Criteria andCollectionCountGreaterThanOrEqualTo(Long value) {
            addCriterion("COLLECTION_COUNT >=", value, "collectionCount");
            return (Criteria) this;
        }

        public Criteria andCollectionCountLessThan(Long value) {
            addCriterion("COLLECTION_COUNT <", value, "collectionCount");
            return (Criteria) this;
        }

        public Criteria andCollectionCountLessThanOrEqualTo(Long value) {
            addCriterion("COLLECTION_COUNT <=", value, "collectionCount");
            return (Criteria) this;
        }

        public Criteria andCollectionCountIn(List<Long> values) {
            addCriterion("COLLECTION_COUNT in", values, "collectionCount");
            return (Criteria) this;
        }

        public Criteria andCollectionCountNotIn(List<Long> values) {
            addCriterion("COLLECTION_COUNT not in", values, "collectionCount");
            return (Criteria) this;
        }

        public Criteria andCollectionCountBetween(Long value1, Long value2) {
            addCriterion("COLLECTION_COUNT between", value1, value2, "collectionCount");
            return (Criteria) this;
        }

        public Criteria andCollectionCountNotBetween(Long value1, Long value2) {
            addCriterion("COLLECTION_COUNT not between", value1, value2, "collectionCount");
            return (Criteria) this;
        }

        public Criteria andChapterScoreIsNull() {
            addCriterion("CHAPTER_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andChapterScoreIsNotNull() {
            addCriterion("CHAPTER_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andChapterScoreEqualTo(BigDecimal value) {
            addCriterion("CHAPTER_SCORE =", value, "chapterScore");
            return (Criteria) this;
        }

        public Criteria andChapterScoreNotEqualTo(BigDecimal value) {
            addCriterion("CHAPTER_SCORE <>", value, "chapterScore");
            return (Criteria) this;
        }

        public Criteria andChapterScoreGreaterThan(BigDecimal value) {
            addCriterion("CHAPTER_SCORE >", value, "chapterScore");
            return (Criteria) this;
        }

        public Criteria andChapterScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CHAPTER_SCORE >=", value, "chapterScore");
            return (Criteria) this;
        }

        public Criteria andChapterScoreLessThan(BigDecimal value) {
            addCriterion("CHAPTER_SCORE <", value, "chapterScore");
            return (Criteria) this;
        }

        public Criteria andChapterScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CHAPTER_SCORE <=", value, "chapterScore");
            return (Criteria) this;
        }

        public Criteria andChapterScoreIn(List<BigDecimal> values) {
            addCriterion("CHAPTER_SCORE in", values, "chapterScore");
            return (Criteria) this;
        }

        public Criteria andChapterScoreNotIn(List<BigDecimal> values) {
            addCriterion("CHAPTER_SCORE not in", values, "chapterScore");
            return (Criteria) this;
        }

        public Criteria andChapterScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CHAPTER_SCORE between", value1, value2, "chapterScore");
            return (Criteria) this;
        }

        public Criteria andChapterScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CHAPTER_SCORE not between", value1, value2, "chapterScore");
            return (Criteria) this;
        }

        public Criteria andChapterJoinCountIsNull() {
            addCriterion("CHAPTER_JOIN_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andChapterJoinCountIsNotNull() {
            addCriterion("CHAPTER_JOIN_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andChapterJoinCountEqualTo(Long value) {
            addCriterion("CHAPTER_JOIN_COUNT =", value, "chapterJoinCount");
            return (Criteria) this;
        }

        public Criteria andChapterJoinCountNotEqualTo(Long value) {
            addCriterion("CHAPTER_JOIN_COUNT <>", value, "chapterJoinCount");
            return (Criteria) this;
        }

        public Criteria andChapterJoinCountGreaterThan(Long value) {
            addCriterion("CHAPTER_JOIN_COUNT >", value, "chapterJoinCount");
            return (Criteria) this;
        }

        public Criteria andChapterJoinCountGreaterThanOrEqualTo(Long value) {
            addCriterion("CHAPTER_JOIN_COUNT >=", value, "chapterJoinCount");
            return (Criteria) this;
        }

        public Criteria andChapterJoinCountLessThan(Long value) {
            addCriterion("CHAPTER_JOIN_COUNT <", value, "chapterJoinCount");
            return (Criteria) this;
        }

        public Criteria andChapterJoinCountLessThanOrEqualTo(Long value) {
            addCriterion("CHAPTER_JOIN_COUNT <=", value, "chapterJoinCount");
            return (Criteria) this;
        }

        public Criteria andChapterJoinCountIn(List<Long> values) {
            addCriterion("CHAPTER_JOIN_COUNT in", values, "chapterJoinCount");
            return (Criteria) this;
        }

        public Criteria andChapterJoinCountNotIn(List<Long> values) {
            addCriterion("CHAPTER_JOIN_COUNT not in", values, "chapterJoinCount");
            return (Criteria) this;
        }

        public Criteria andChapterJoinCountBetween(Long value1, Long value2) {
            addCriterion("CHAPTER_JOIN_COUNT between", value1, value2, "chapterJoinCount");
            return (Criteria) this;
        }

        public Criteria andChapterJoinCountNotBetween(Long value1, Long value2) {
            addCriterion("CHAPTER_JOIN_COUNT not between", value1, value2, "chapterJoinCount");
            return (Criteria) this;
        }

        public Criteria andChapterFinishCountIsNull() {
            addCriterion("CHAPTER_FINISH_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andChapterFinishCountIsNotNull() {
            addCriterion("CHAPTER_FINISH_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andChapterFinishCountEqualTo(Long value) {
            addCriterion("CHAPTER_FINISH_COUNT =", value, "chapterFinishCount");
            return (Criteria) this;
        }

        public Criteria andChapterFinishCountNotEqualTo(Long value) {
            addCriterion("CHAPTER_FINISH_COUNT <>", value, "chapterFinishCount");
            return (Criteria) this;
        }

        public Criteria andChapterFinishCountGreaterThan(Long value) {
            addCriterion("CHAPTER_FINISH_COUNT >", value, "chapterFinishCount");
            return (Criteria) this;
        }

        public Criteria andChapterFinishCountGreaterThanOrEqualTo(Long value) {
            addCriterion("CHAPTER_FINISH_COUNT >=", value, "chapterFinishCount");
            return (Criteria) this;
        }

        public Criteria andChapterFinishCountLessThan(Long value) {
            addCriterion("CHAPTER_FINISH_COUNT <", value, "chapterFinishCount");
            return (Criteria) this;
        }

        public Criteria andChapterFinishCountLessThanOrEqualTo(Long value) {
            addCriterion("CHAPTER_FINISH_COUNT <=", value, "chapterFinishCount");
            return (Criteria) this;
        }

        public Criteria andChapterFinishCountIn(List<Long> values) {
            addCriterion("CHAPTER_FINISH_COUNT in", values, "chapterFinishCount");
            return (Criteria) this;
        }

        public Criteria andChapterFinishCountNotIn(List<Long> values) {
            addCriterion("CHAPTER_FINISH_COUNT not in", values, "chapterFinishCount");
            return (Criteria) this;
        }

        public Criteria andChapterFinishCountBetween(Long value1, Long value2) {
            addCriterion("CHAPTER_FINISH_COUNT between", value1, value2, "chapterFinishCount");
            return (Criteria) this;
        }

        public Criteria andChapterFinishCountNotBetween(Long value1, Long value2) {
            addCriterion("CHAPTER_FINISH_COUNT not between", value1, value2, "chapterFinishCount");
            return (Criteria) this;
        }

        public Criteria andChapterOrderIsNull() {
            addCriterion("CHAPTER_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andChapterOrderIsNotNull() {
            addCriterion("CHAPTER_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andChapterOrderEqualTo(Short value) {
            addCriterion("CHAPTER_ORDER =", value, "chapterOrder");
            return (Criteria) this;
        }

        public Criteria andChapterOrderNotEqualTo(Short value) {
            addCriterion("CHAPTER_ORDER <>", value, "chapterOrder");
            return (Criteria) this;
        }

        public Criteria andChapterOrderGreaterThan(Short value) {
            addCriterion("CHAPTER_ORDER >", value, "chapterOrder");
            return (Criteria) this;
        }

        public Criteria andChapterOrderGreaterThanOrEqualTo(Short value) {
            addCriterion("CHAPTER_ORDER >=", value, "chapterOrder");
            return (Criteria) this;
        }

        public Criteria andChapterOrderLessThan(Short value) {
            addCriterion("CHAPTER_ORDER <", value, "chapterOrder");
            return (Criteria) this;
        }

        public Criteria andChapterOrderLessThanOrEqualTo(Short value) {
            addCriterion("CHAPTER_ORDER <=", value, "chapterOrder");
            return (Criteria) this;
        }

        public Criteria andChapterOrderIn(List<Short> values) {
            addCriterion("CHAPTER_ORDER in", values, "chapterOrder");
            return (Criteria) this;
        }

        public Criteria andChapterOrderNotIn(List<Short> values) {
            addCriterion("CHAPTER_ORDER not in", values, "chapterOrder");
            return (Criteria) this;
        }

        public Criteria andChapterOrderBetween(Short value1, Short value2) {
            addCriterion("CHAPTER_ORDER between", value1, value2, "chapterOrder");
            return (Criteria) this;
        }

        public Criteria andChapterOrderNotBetween(Short value1, Short value2) {
            addCriterion("CHAPTER_ORDER not between", value1, value2, "chapterOrder");
            return (Criteria) this;
        }

        public Criteria andChapterIntroIsNull() {
            addCriterion("CHAPTER_INTRO is null");
            return (Criteria) this;
        }

        public Criteria andChapterIntroIsNotNull() {
            addCriterion("CHAPTER_INTRO is not null");
            return (Criteria) this;
        }

        public Criteria andChapterIntroEqualTo(String value) {
            addCriterion("CHAPTER_INTRO =", value, "chapterIntro");
            return (Criteria) this;
        }

        public Criteria andChapterIntroNotEqualTo(String value) {
            addCriterion("CHAPTER_INTRO <>", value, "chapterIntro");
            return (Criteria) this;
        }

        public Criteria andChapterIntroGreaterThan(String value) {
            addCriterion("CHAPTER_INTRO >", value, "chapterIntro");
            return (Criteria) this;
        }

        public Criteria andChapterIntroGreaterThanOrEqualTo(String value) {
            addCriterion("CHAPTER_INTRO >=", value, "chapterIntro");
            return (Criteria) this;
        }

        public Criteria andChapterIntroLessThan(String value) {
            addCriterion("CHAPTER_INTRO <", value, "chapterIntro");
            return (Criteria) this;
        }

        public Criteria andChapterIntroLessThanOrEqualTo(String value) {
            addCriterion("CHAPTER_INTRO <=", value, "chapterIntro");
            return (Criteria) this;
        }

        public Criteria andChapterIntroLike(String value) {
            addCriterion("CHAPTER_INTRO like", value, "chapterIntro");
            return (Criteria) this;
        }

        public Criteria andChapterIntroNotLike(String value) {
            addCriterion("CHAPTER_INTRO not like", value, "chapterIntro");
            return (Criteria) this;
        }

        public Criteria andChapterIntroIn(List<String> values) {
            addCriterion("CHAPTER_INTRO in", values, "chapterIntro");
            return (Criteria) this;
        }

        public Criteria andChapterIntroNotIn(List<String> values) {
            addCriterion("CHAPTER_INTRO not in", values, "chapterIntro");
            return (Criteria) this;
        }

        public Criteria andChapterIntroBetween(String value1, String value2) {
            addCriterion("CHAPTER_INTRO between", value1, value2, "chapterIntro");
            return (Criteria) this;
        }

        public Criteria andChapterIntroNotBetween(String value1, String value2) {
            addCriterion("CHAPTER_INTRO not between", value1, value2, "chapterIntro");
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

        public Criteria andChapterFileNameIsNull() {
            addCriterion("CHAPTER_FILE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChapterFileNameIsNotNull() {
            addCriterion("CHAPTER_FILE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChapterFileNameEqualTo(String value) {
            addCriterion("CHAPTER_FILE_NAME =", value, "chapterFileName");
            return (Criteria) this;
        }

        public Criteria andChapterFileNameNotEqualTo(String value) {
            addCriterion("CHAPTER_FILE_NAME <>", value, "chapterFileName");
            return (Criteria) this;
        }

        public Criteria andChapterFileNameGreaterThan(String value) {
            addCriterion("CHAPTER_FILE_NAME >", value, "chapterFileName");
            return (Criteria) this;
        }

        public Criteria andChapterFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHAPTER_FILE_NAME >=", value, "chapterFileName");
            return (Criteria) this;
        }

        public Criteria andChapterFileNameLessThan(String value) {
            addCriterion("CHAPTER_FILE_NAME <", value, "chapterFileName");
            return (Criteria) this;
        }

        public Criteria andChapterFileNameLessThanOrEqualTo(String value) {
            addCriterion("CHAPTER_FILE_NAME <=", value, "chapterFileName");
            return (Criteria) this;
        }

        public Criteria andChapterFileNameLike(String value) {
            addCriterion("CHAPTER_FILE_NAME like", value, "chapterFileName");
            return (Criteria) this;
        }

        public Criteria andChapterFileNameNotLike(String value) {
            addCriterion("CHAPTER_FILE_NAME not like", value, "chapterFileName");
            return (Criteria) this;
        }

        public Criteria andChapterFileNameIn(List<String> values) {
            addCriterion("CHAPTER_FILE_NAME in", values, "chapterFileName");
            return (Criteria) this;
        }

        public Criteria andChapterFileNameNotIn(List<String> values) {
            addCriterion("CHAPTER_FILE_NAME not in", values, "chapterFileName");
            return (Criteria) this;
        }

        public Criteria andChapterFileNameBetween(String value1, String value2) {
            addCriterion("CHAPTER_FILE_NAME between", value1, value2, "chapterFileName");
            return (Criteria) this;
        }

        public Criteria andChapterFileNameNotBetween(String value1, String value2) {
            addCriterion("CHAPTER_FILE_NAME not between", value1, value2, "chapterFileName");
            return (Criteria) this;
        }

        public Criteria andChapterImgNameIsNull() {
            addCriterion("CHAPTER_IMG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChapterImgNameIsNotNull() {
            addCriterion("CHAPTER_IMG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChapterImgNameEqualTo(String value) {
            addCriterion("CHAPTER_IMG_NAME =", value, "chapterImgName");
            return (Criteria) this;
        }

        public Criteria andChapterImgNameNotEqualTo(String value) {
            addCriterion("CHAPTER_IMG_NAME <>", value, "chapterImgName");
            return (Criteria) this;
        }

        public Criteria andChapterImgNameGreaterThan(String value) {
            addCriterion("CHAPTER_IMG_NAME >", value, "chapterImgName");
            return (Criteria) this;
        }

        public Criteria andChapterImgNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHAPTER_IMG_NAME >=", value, "chapterImgName");
            return (Criteria) this;
        }

        public Criteria andChapterImgNameLessThan(String value) {
            addCriterion("CHAPTER_IMG_NAME <", value, "chapterImgName");
            return (Criteria) this;
        }

        public Criteria andChapterImgNameLessThanOrEqualTo(String value) {
            addCriterion("CHAPTER_IMG_NAME <=", value, "chapterImgName");
            return (Criteria) this;
        }

        public Criteria andChapterImgNameLike(String value) {
            addCriterion("CHAPTER_IMG_NAME like", value, "chapterImgName");
            return (Criteria) this;
        }

        public Criteria andChapterImgNameNotLike(String value) {
            addCriterion("CHAPTER_IMG_NAME not like", value, "chapterImgName");
            return (Criteria) this;
        }

        public Criteria andChapterImgNameIn(List<String> values) {
            addCriterion("CHAPTER_IMG_NAME in", values, "chapterImgName");
            return (Criteria) this;
        }

        public Criteria andChapterImgNameNotIn(List<String> values) {
            addCriterion("CHAPTER_IMG_NAME not in", values, "chapterImgName");
            return (Criteria) this;
        }

        public Criteria andChapterImgNameBetween(String value1, String value2) {
            addCriterion("CHAPTER_IMG_NAME between", value1, value2, "chapterImgName");
            return (Criteria) this;
        }

        public Criteria andChapterImgNameNotBetween(String value1, String value2) {
            addCriterion("CHAPTER_IMG_NAME not between", value1, value2, "chapterImgName");
            return (Criteria) this;
        }

        public Criteria andChapterCreditIsNull() {
            addCriterion("CHAPTER_CREDIT is null");
            return (Criteria) this;
        }

        public Criteria andChapterCreditIsNotNull() {
            addCriterion("CHAPTER_CREDIT is not null");
            return (Criteria) this;
        }

        public Criteria andChapterCreditEqualTo(String value) {
            addCriterion("CHAPTER_CREDIT =", value, "chapterCredit");
            return (Criteria) this;
        }

        public Criteria andChapterCreditNotEqualTo(String value) {
            addCriterion("CHAPTER_CREDIT <>", value, "chapterCredit");
            return (Criteria) this;
        }

        public Criteria andChapterCreditGreaterThan(String value) {
            addCriterion("CHAPTER_CREDIT >", value, "chapterCredit");
            return (Criteria) this;
        }

        public Criteria andChapterCreditGreaterThanOrEqualTo(String value) {
            addCriterion("CHAPTER_CREDIT >=", value, "chapterCredit");
            return (Criteria) this;
        }

        public Criteria andChapterCreditLessThan(String value) {
            addCriterion("CHAPTER_CREDIT <", value, "chapterCredit");
            return (Criteria) this;
        }

        public Criteria andChapterCreditLessThanOrEqualTo(String value) {
            addCriterion("CHAPTER_CREDIT <=", value, "chapterCredit");
            return (Criteria) this;
        }

        public Criteria andChapterCreditLike(String value) {
            addCriterion("CHAPTER_CREDIT like", value, "chapterCredit");
            return (Criteria) this;
        }

        public Criteria andChapterCreditNotLike(String value) {
            addCriterion("CHAPTER_CREDIT not like", value, "chapterCredit");
            return (Criteria) this;
        }

        public Criteria andChapterCreditIn(List<String> values) {
            addCriterion("CHAPTER_CREDIT in", values, "chapterCredit");
            return (Criteria) this;
        }

        public Criteria andChapterCreditNotIn(List<String> values) {
            addCriterion("CHAPTER_CREDIT not in", values, "chapterCredit");
            return (Criteria) this;
        }

        public Criteria andChapterCreditBetween(String value1, String value2) {
            addCriterion("CHAPTER_CREDIT between", value1, value2, "chapterCredit");
            return (Criteria) this;
        }

        public Criteria andChapterCreditNotBetween(String value1, String value2) {
            addCriterion("CHAPTER_CREDIT not between", value1, value2, "chapterCredit");
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