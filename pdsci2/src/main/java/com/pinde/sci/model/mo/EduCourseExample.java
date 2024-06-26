package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EduCourseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EduCourseExample() {
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

        public Criteria andCourseMajorIdIsNull() {
            addCriterion("COURSE_MAJOR_ID is null");
            return (Criteria) this;
        }

        public Criteria andCourseMajorIdIsNotNull() {
            addCriterion("COURSE_MAJOR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCourseMajorIdEqualTo(String value) {
            addCriterion("COURSE_MAJOR_ID =", value, "courseMajorId");
            return (Criteria) this;
        }

        public Criteria andCourseMajorIdNotEqualTo(String value) {
            addCriterion("COURSE_MAJOR_ID <>", value, "courseMajorId");
            return (Criteria) this;
        }

        public Criteria andCourseMajorIdGreaterThan(String value) {
            addCriterion("COURSE_MAJOR_ID >", value, "courseMajorId");
            return (Criteria) this;
        }

        public Criteria andCourseMajorIdGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_MAJOR_ID >=", value, "courseMajorId");
            return (Criteria) this;
        }

        public Criteria andCourseMajorIdLessThan(String value) {
            addCriterion("COURSE_MAJOR_ID <", value, "courseMajorId");
            return (Criteria) this;
        }

        public Criteria andCourseMajorIdLessThanOrEqualTo(String value) {
            addCriterion("COURSE_MAJOR_ID <=", value, "courseMajorId");
            return (Criteria) this;
        }

        public Criteria andCourseMajorIdLike(String value) {
            addCriterion("COURSE_MAJOR_ID like", value, "courseMajorId");
            return (Criteria) this;
        }

        public Criteria andCourseMajorIdNotLike(String value) {
            addCriterion("COURSE_MAJOR_ID not like", value, "courseMajorId");
            return (Criteria) this;
        }

        public Criteria andCourseMajorIdIn(List<String> values) {
            addCriterion("COURSE_MAJOR_ID in", values, "courseMajorId");
            return (Criteria) this;
        }

        public Criteria andCourseMajorIdNotIn(List<String> values) {
            addCriterion("COURSE_MAJOR_ID not in", values, "courseMajorId");
            return (Criteria) this;
        }

        public Criteria andCourseMajorIdBetween(String value1, String value2) {
            addCriterion("COURSE_MAJOR_ID between", value1, value2, "courseMajorId");
            return (Criteria) this;
        }

        public Criteria andCourseMajorIdNotBetween(String value1, String value2) {
            addCriterion("COURSE_MAJOR_ID not between", value1, value2, "courseMajorId");
            return (Criteria) this;
        }

        public Criteria andCourseMajorNameIsNull() {
            addCriterion("COURSE_MAJOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCourseMajorNameIsNotNull() {
            addCriterion("COURSE_MAJOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCourseMajorNameEqualTo(String value) {
            addCriterion("COURSE_MAJOR_NAME =", value, "courseMajorName");
            return (Criteria) this;
        }

        public Criteria andCourseMajorNameNotEqualTo(String value) {
            addCriterion("COURSE_MAJOR_NAME <>", value, "courseMajorName");
            return (Criteria) this;
        }

        public Criteria andCourseMajorNameGreaterThan(String value) {
            addCriterion("COURSE_MAJOR_NAME >", value, "courseMajorName");
            return (Criteria) this;
        }

        public Criteria andCourseMajorNameGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_MAJOR_NAME >=", value, "courseMajorName");
            return (Criteria) this;
        }

        public Criteria andCourseMajorNameLessThan(String value) {
            addCriterion("COURSE_MAJOR_NAME <", value, "courseMajorName");
            return (Criteria) this;
        }

        public Criteria andCourseMajorNameLessThanOrEqualTo(String value) {
            addCriterion("COURSE_MAJOR_NAME <=", value, "courseMajorName");
            return (Criteria) this;
        }

        public Criteria andCourseMajorNameLike(String value) {
            addCriterion("COURSE_MAJOR_NAME like", value, "courseMajorName");
            return (Criteria) this;
        }

        public Criteria andCourseMajorNameNotLike(String value) {
            addCriterion("COURSE_MAJOR_NAME not like", value, "courseMajorName");
            return (Criteria) this;
        }

        public Criteria andCourseMajorNameIn(List<String> values) {
            addCriterion("COURSE_MAJOR_NAME in", values, "courseMajorName");
            return (Criteria) this;
        }

        public Criteria andCourseMajorNameNotIn(List<String> values) {
            addCriterion("COURSE_MAJOR_NAME not in", values, "courseMajorName");
            return (Criteria) this;
        }

        public Criteria andCourseMajorNameBetween(String value1, String value2) {
            addCriterion("COURSE_MAJOR_NAME between", value1, value2, "courseMajorName");
            return (Criteria) this;
        }

        public Criteria andCourseMajorNameNotBetween(String value1, String value2) {
            addCriterion("COURSE_MAJOR_NAME not between", value1, value2, "courseMajorName");
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

        public Criteria andCourseImgIsNull() {
            addCriterion("COURSE_IMG is null");
            return (Criteria) this;
        }

        public Criteria andCourseImgIsNotNull() {
            addCriterion("COURSE_IMG is not null");
            return (Criteria) this;
        }

        public Criteria andCourseImgEqualTo(String value) {
            addCriterion("COURSE_IMG =", value, "courseImg");
            return (Criteria) this;
        }

        public Criteria andCourseImgNotEqualTo(String value) {
            addCriterion("COURSE_IMG <>", value, "courseImg");
            return (Criteria) this;
        }

        public Criteria andCourseImgGreaterThan(String value) {
            addCriterion("COURSE_IMG >", value, "courseImg");
            return (Criteria) this;
        }

        public Criteria andCourseImgGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_IMG >=", value, "courseImg");
            return (Criteria) this;
        }

        public Criteria andCourseImgLessThan(String value) {
            addCriterion("COURSE_IMG <", value, "courseImg");
            return (Criteria) this;
        }

        public Criteria andCourseImgLessThanOrEqualTo(String value) {
            addCriterion("COURSE_IMG <=", value, "courseImg");
            return (Criteria) this;
        }

        public Criteria andCourseImgLike(String value) {
            addCriterion("COURSE_IMG like", value, "courseImg");
            return (Criteria) this;
        }

        public Criteria andCourseImgNotLike(String value) {
            addCriterion("COURSE_IMG not like", value, "courseImg");
            return (Criteria) this;
        }

        public Criteria andCourseImgIn(List<String> values) {
            addCriterion("COURSE_IMG in", values, "courseImg");
            return (Criteria) this;
        }

        public Criteria andCourseImgNotIn(List<String> values) {
            addCriterion("COURSE_IMG not in", values, "courseImg");
            return (Criteria) this;
        }

        public Criteria andCourseImgBetween(String value1, String value2) {
            addCriterion("COURSE_IMG between", value1, value2, "courseImg");
            return (Criteria) this;
        }

        public Criteria andCourseImgNotBetween(String value1, String value2) {
            addCriterion("COURSE_IMG not between", value1, value2, "courseImg");
            return (Criteria) this;
        }

        public Criteria andCourseOrderIsNull() {
            addCriterion("COURSE_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andCourseOrderIsNotNull() {
            addCriterion("COURSE_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andCourseOrderEqualTo(Long value) {
            addCriterion("COURSE_ORDER =", value, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderNotEqualTo(Long value) {
            addCriterion("COURSE_ORDER <>", value, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderGreaterThan(Long value) {
            addCriterion("COURSE_ORDER >", value, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderGreaterThanOrEqualTo(Long value) {
            addCriterion("COURSE_ORDER >=", value, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderLessThan(Long value) {
            addCriterion("COURSE_ORDER <", value, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderLessThanOrEqualTo(Long value) {
            addCriterion("COURSE_ORDER <=", value, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderIn(List<Long> values) {
            addCriterion("COURSE_ORDER in", values, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderNotIn(List<Long> values) {
            addCriterion("COURSE_ORDER not in", values, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderBetween(Long value1, Long value2) {
            addCriterion("COURSE_ORDER between", value1, value2, "courseOrder");
            return (Criteria) this;
        }

        public Criteria andCourseOrderNotBetween(Long value1, Long value2) {
            addCriterion("COURSE_ORDER not between", value1, value2, "courseOrder");
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

        public Criteria andUploadTimeIsNull() {
            addCriterion("UPLOAD_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIsNotNull() {
            addCriterion("UPLOAD_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeEqualTo(String value) {
            addCriterion("UPLOAD_TIME =", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotEqualTo(String value) {
            addCriterion("UPLOAD_TIME <>", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThan(String value) {
            addCriterion("UPLOAD_TIME >", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThanOrEqualTo(String value) {
            addCriterion("UPLOAD_TIME >=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThan(String value) {
            addCriterion("UPLOAD_TIME <", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThanOrEqualTo(String value) {
            addCriterion("UPLOAD_TIME <=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLike(String value) {
            addCriterion("UPLOAD_TIME like", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotLike(String value) {
            addCriterion("UPLOAD_TIME not like", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIn(List<String> values) {
            addCriterion("UPLOAD_TIME in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotIn(List<String> values) {
            addCriterion("UPLOAD_TIME not in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeBetween(String value1, String value2) {
            addCriterion("UPLOAD_TIME between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotBetween(String value1, String value2) {
            addCriterion("UPLOAD_TIME not between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryIdIsNull() {
            addCriterion("COURSE_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryIdIsNotNull() {
            addCriterion("COURSE_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryIdEqualTo(String value) {
            addCriterion("COURSE_CATEGORY_ID =", value, "courseCategoryId");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryIdNotEqualTo(String value) {
            addCriterion("COURSE_CATEGORY_ID <>", value, "courseCategoryId");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryIdGreaterThan(String value) {
            addCriterion("COURSE_CATEGORY_ID >", value, "courseCategoryId");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_CATEGORY_ID >=", value, "courseCategoryId");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryIdLessThan(String value) {
            addCriterion("COURSE_CATEGORY_ID <", value, "courseCategoryId");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("COURSE_CATEGORY_ID <=", value, "courseCategoryId");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryIdLike(String value) {
            addCriterion("COURSE_CATEGORY_ID like", value, "courseCategoryId");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryIdNotLike(String value) {
            addCriterion("COURSE_CATEGORY_ID not like", value, "courseCategoryId");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryIdIn(List<String> values) {
            addCriterion("COURSE_CATEGORY_ID in", values, "courseCategoryId");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryIdNotIn(List<String> values) {
            addCriterion("COURSE_CATEGORY_ID not in", values, "courseCategoryId");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryIdBetween(String value1, String value2) {
            addCriterion("COURSE_CATEGORY_ID between", value1, value2, "courseCategoryId");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryIdNotBetween(String value1, String value2) {
            addCriterion("COURSE_CATEGORY_ID not between", value1, value2, "courseCategoryId");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNameIsNull() {
            addCriterion("COURSE_CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNameIsNotNull() {
            addCriterion("COURSE_CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNameEqualTo(String value) {
            addCriterion("COURSE_CATEGORY_NAME =", value, "courseCategoryName");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNameNotEqualTo(String value) {
            addCriterion("COURSE_CATEGORY_NAME <>", value, "courseCategoryName");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNameGreaterThan(String value) {
            addCriterion("COURSE_CATEGORY_NAME >", value, "courseCategoryName");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_CATEGORY_NAME >=", value, "courseCategoryName");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNameLessThan(String value) {
            addCriterion("COURSE_CATEGORY_NAME <", value, "courseCategoryName");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("COURSE_CATEGORY_NAME <=", value, "courseCategoryName");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNameLike(String value) {
            addCriterion("COURSE_CATEGORY_NAME like", value, "courseCategoryName");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNameNotLike(String value) {
            addCriterion("COURSE_CATEGORY_NAME not like", value, "courseCategoryName");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNameIn(List<String> values) {
            addCriterion("COURSE_CATEGORY_NAME in", values, "courseCategoryName");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNameNotIn(List<String> values) {
            addCriterion("COURSE_CATEGORY_NAME not in", values, "courseCategoryName");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNameBetween(String value1, String value2) {
            addCriterion("COURSE_CATEGORY_NAME between", value1, value2, "courseCategoryName");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNameNotBetween(String value1, String value2) {
            addCriterion("COURSE_CATEGORY_NAME not between", value1, value2, "courseCategoryName");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIdIsNull() {
            addCriterion("COURSE_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIdIsNotNull() {
            addCriterion("COURSE_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIdEqualTo(String value) {
            addCriterion("COURSE_STATUS_ID =", value, "courseStatusId");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIdNotEqualTo(String value) {
            addCriterion("COURSE_STATUS_ID <>", value, "courseStatusId");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIdGreaterThan(String value) {
            addCriterion("COURSE_STATUS_ID >", value, "courseStatusId");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_STATUS_ID >=", value, "courseStatusId");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIdLessThan(String value) {
            addCriterion("COURSE_STATUS_ID <", value, "courseStatusId");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIdLessThanOrEqualTo(String value) {
            addCriterion("COURSE_STATUS_ID <=", value, "courseStatusId");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIdLike(String value) {
            addCriterion("COURSE_STATUS_ID like", value, "courseStatusId");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIdNotLike(String value) {
            addCriterion("COURSE_STATUS_ID not like", value, "courseStatusId");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIdIn(List<String> values) {
            addCriterion("COURSE_STATUS_ID in", values, "courseStatusId");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIdNotIn(List<String> values) {
            addCriterion("COURSE_STATUS_ID not in", values, "courseStatusId");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIdBetween(String value1, String value2) {
            addCriterion("COURSE_STATUS_ID between", value1, value2, "courseStatusId");
            return (Criteria) this;
        }

        public Criteria andCourseStatusIdNotBetween(String value1, String value2) {
            addCriterion("COURSE_STATUS_ID not between", value1, value2, "courseStatusId");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNameIsNull() {
            addCriterion("COURSE_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNameIsNotNull() {
            addCriterion("COURSE_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNameEqualTo(String value) {
            addCriterion("COURSE_STATUS_NAME =", value, "courseStatusName");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNameNotEqualTo(String value) {
            addCriterion("COURSE_STATUS_NAME <>", value, "courseStatusName");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNameGreaterThan(String value) {
            addCriterion("COURSE_STATUS_NAME >", value, "courseStatusName");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_STATUS_NAME >=", value, "courseStatusName");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNameLessThan(String value) {
            addCriterion("COURSE_STATUS_NAME <", value, "courseStatusName");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNameLessThanOrEqualTo(String value) {
            addCriterion("COURSE_STATUS_NAME <=", value, "courseStatusName");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNameLike(String value) {
            addCriterion("COURSE_STATUS_NAME like", value, "courseStatusName");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNameNotLike(String value) {
            addCriterion("COURSE_STATUS_NAME not like", value, "courseStatusName");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNameIn(List<String> values) {
            addCriterion("COURSE_STATUS_NAME in", values, "courseStatusName");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNameNotIn(List<String> values) {
            addCriterion("COURSE_STATUS_NAME not in", values, "courseStatusName");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNameBetween(String value1, String value2) {
            addCriterion("COURSE_STATUS_NAME between", value1, value2, "courseStatusName");
            return (Criteria) this;
        }

        public Criteria andCourseStatusNameNotBetween(String value1, String value2) {
            addCriterion("COURSE_STATUS_NAME not between", value1, value2, "courseStatusName");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerIsNull() {
            addCriterion("COURSE_SPEAKER is null");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerIsNotNull() {
            addCriterion("COURSE_SPEAKER is not null");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerEqualTo(String value) {
            addCriterion("COURSE_SPEAKER =", value, "courseSpeaker");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerNotEqualTo(String value) {
            addCriterion("COURSE_SPEAKER <>", value, "courseSpeaker");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerGreaterThan(String value) {
            addCriterion("COURSE_SPEAKER >", value, "courseSpeaker");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_SPEAKER >=", value, "courseSpeaker");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerLessThan(String value) {
            addCriterion("COURSE_SPEAKER <", value, "courseSpeaker");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerLessThanOrEqualTo(String value) {
            addCriterion("COURSE_SPEAKER <=", value, "courseSpeaker");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerLike(String value) {
            addCriterion("COURSE_SPEAKER like", value, "courseSpeaker");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerNotLike(String value) {
            addCriterion("COURSE_SPEAKER not like", value, "courseSpeaker");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerIn(List<String> values) {
            addCriterion("COURSE_SPEAKER in", values, "courseSpeaker");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerNotIn(List<String> values) {
            addCriterion("COURSE_SPEAKER not in", values, "courseSpeaker");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerBetween(String value1, String value2) {
            addCriterion("COURSE_SPEAKER between", value1, value2, "courseSpeaker");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerNotBetween(String value1, String value2) {
            addCriterion("COURSE_SPEAKER not between", value1, value2, "courseSpeaker");
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

        public Criteria andCourseImgNameIsNull() {
            addCriterion("COURSE_IMG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCourseImgNameIsNotNull() {
            addCriterion("COURSE_IMG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCourseImgNameEqualTo(String value) {
            addCriterion("COURSE_IMG_NAME =", value, "courseImgName");
            return (Criteria) this;
        }

        public Criteria andCourseImgNameNotEqualTo(String value) {
            addCriterion("COURSE_IMG_NAME <>", value, "courseImgName");
            return (Criteria) this;
        }

        public Criteria andCourseImgNameGreaterThan(String value) {
            addCriterion("COURSE_IMG_NAME >", value, "courseImgName");
            return (Criteria) this;
        }

        public Criteria andCourseImgNameGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_IMG_NAME >=", value, "courseImgName");
            return (Criteria) this;
        }

        public Criteria andCourseImgNameLessThan(String value) {
            addCriterion("COURSE_IMG_NAME <", value, "courseImgName");
            return (Criteria) this;
        }

        public Criteria andCourseImgNameLessThanOrEqualTo(String value) {
            addCriterion("COURSE_IMG_NAME <=", value, "courseImgName");
            return (Criteria) this;
        }

        public Criteria andCourseImgNameLike(String value) {
            addCriterion("COURSE_IMG_NAME like", value, "courseImgName");
            return (Criteria) this;
        }

        public Criteria andCourseImgNameNotLike(String value) {
            addCriterion("COURSE_IMG_NAME not like", value, "courseImgName");
            return (Criteria) this;
        }

        public Criteria andCourseImgNameIn(List<String> values) {
            addCriterion("COURSE_IMG_NAME in", values, "courseImgName");
            return (Criteria) this;
        }

        public Criteria andCourseImgNameNotIn(List<String> values) {
            addCriterion("COURSE_IMG_NAME not in", values, "courseImgName");
            return (Criteria) this;
        }

        public Criteria andCourseImgNameBetween(String value1, String value2) {
            addCriterion("COURSE_IMG_NAME between", value1, value2, "courseImgName");
            return (Criteria) this;
        }

        public Criteria andCourseImgNameNotBetween(String value1, String value2) {
            addCriterion("COURSE_IMG_NAME not between", value1, value2, "courseImgName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameIsNull() {
            addCriterion("CREATE_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameIsNotNull() {
            addCriterion("CREATE_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameEqualTo(String value) {
            addCriterion("CREATE_USER_NAME =", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameNotEqualTo(String value) {
            addCriterion("CREATE_USER_NAME <>", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameGreaterThan(String value) {
            addCriterion("CREATE_USER_NAME >", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_USER_NAME >=", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameLessThan(String value) {
            addCriterion("CREATE_USER_NAME <", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameLessThanOrEqualTo(String value) {
            addCriterion("CREATE_USER_NAME <=", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameLike(String value) {
            addCriterion("CREATE_USER_NAME like", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameNotLike(String value) {
            addCriterion("CREATE_USER_NAME not like", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameIn(List<String> values) {
            addCriterion("CREATE_USER_NAME in", values, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameNotIn(List<String> values) {
            addCriterion("CREATE_USER_NAME not in", values, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameBetween(String value1, String value2) {
            addCriterion("CREATE_USER_NAME between", value1, value2, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameNotBetween(String value1, String value2) {
            addCriterion("CREATE_USER_NAME not between", value1, value2, "createUserName");
            return (Criteria) this;
        }

        public Criteria andGradationIdIsNull() {
            addCriterion("GRADATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andGradationIdIsNotNull() {
            addCriterion("GRADATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGradationIdEqualTo(String value) {
            addCriterion("GRADATION_ID =", value, "gradationId");
            return (Criteria) this;
        }

        public Criteria andGradationIdNotEqualTo(String value) {
            addCriterion("GRADATION_ID <>", value, "gradationId");
            return (Criteria) this;
        }

        public Criteria andGradationIdGreaterThan(String value) {
            addCriterion("GRADATION_ID >", value, "gradationId");
            return (Criteria) this;
        }

        public Criteria andGradationIdGreaterThanOrEqualTo(String value) {
            addCriterion("GRADATION_ID >=", value, "gradationId");
            return (Criteria) this;
        }

        public Criteria andGradationIdLessThan(String value) {
            addCriterion("GRADATION_ID <", value, "gradationId");
            return (Criteria) this;
        }

        public Criteria andGradationIdLessThanOrEqualTo(String value) {
            addCriterion("GRADATION_ID <=", value, "gradationId");
            return (Criteria) this;
        }

        public Criteria andGradationIdLike(String value) {
            addCriterion("GRADATION_ID like", value, "gradationId");
            return (Criteria) this;
        }

        public Criteria andGradationIdNotLike(String value) {
            addCriterion("GRADATION_ID not like", value, "gradationId");
            return (Criteria) this;
        }

        public Criteria andGradationIdIn(List<String> values) {
            addCriterion("GRADATION_ID in", values, "gradationId");
            return (Criteria) this;
        }

        public Criteria andGradationIdNotIn(List<String> values) {
            addCriterion("GRADATION_ID not in", values, "gradationId");
            return (Criteria) this;
        }

        public Criteria andGradationIdBetween(String value1, String value2) {
            addCriterion("GRADATION_ID between", value1, value2, "gradationId");
            return (Criteria) this;
        }

        public Criteria andGradationIdNotBetween(String value1, String value2) {
            addCriterion("GRADATION_ID not between", value1, value2, "gradationId");
            return (Criteria) this;
        }

        public Criteria andGradationNameIsNull() {
            addCriterion("GRADATION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGradationNameIsNotNull() {
            addCriterion("GRADATION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGradationNameEqualTo(String value) {
            addCriterion("GRADATION_NAME =", value, "gradationName");
            return (Criteria) this;
        }

        public Criteria andGradationNameNotEqualTo(String value) {
            addCriterion("GRADATION_NAME <>", value, "gradationName");
            return (Criteria) this;
        }

        public Criteria andGradationNameGreaterThan(String value) {
            addCriterion("GRADATION_NAME >", value, "gradationName");
            return (Criteria) this;
        }

        public Criteria andGradationNameGreaterThanOrEqualTo(String value) {
            addCriterion("GRADATION_NAME >=", value, "gradationName");
            return (Criteria) this;
        }

        public Criteria andGradationNameLessThan(String value) {
            addCriterion("GRADATION_NAME <", value, "gradationName");
            return (Criteria) this;
        }

        public Criteria andGradationNameLessThanOrEqualTo(String value) {
            addCriterion("GRADATION_NAME <=", value, "gradationName");
            return (Criteria) this;
        }

        public Criteria andGradationNameLike(String value) {
            addCriterion("GRADATION_NAME like", value, "gradationName");
            return (Criteria) this;
        }

        public Criteria andGradationNameNotLike(String value) {
            addCriterion("GRADATION_NAME not like", value, "gradationName");
            return (Criteria) this;
        }

        public Criteria andGradationNameIn(List<String> values) {
            addCriterion("GRADATION_NAME in", values, "gradationName");
            return (Criteria) this;
        }

        public Criteria andGradationNameNotIn(List<String> values) {
            addCriterion("GRADATION_NAME not in", values, "gradationName");
            return (Criteria) this;
        }

        public Criteria andGradationNameBetween(String value1, String value2) {
            addCriterion("GRADATION_NAME between", value1, value2, "gradationName");
            return (Criteria) this;
        }

        public Criteria andGradationNameNotBetween(String value1, String value2) {
            addCriterion("GRADATION_NAME not between", value1, value2, "gradationName");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodTeachIsNull() {
            addCriterion("COURSE_PERIOD_TEACH is null");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodTeachIsNotNull() {
            addCriterion("COURSE_PERIOD_TEACH is not null");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodTeachEqualTo(String value) {
            addCriterion("COURSE_PERIOD_TEACH =", value, "coursePeriodTeach");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodTeachNotEqualTo(String value) {
            addCriterion("COURSE_PERIOD_TEACH <>", value, "coursePeriodTeach");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodTeachGreaterThan(String value) {
            addCriterion("COURSE_PERIOD_TEACH >", value, "coursePeriodTeach");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodTeachGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_PERIOD_TEACH >=", value, "coursePeriodTeach");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodTeachLessThan(String value) {
            addCriterion("COURSE_PERIOD_TEACH <", value, "coursePeriodTeach");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodTeachLessThanOrEqualTo(String value) {
            addCriterion("COURSE_PERIOD_TEACH <=", value, "coursePeriodTeach");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodTeachLike(String value) {
            addCriterion("COURSE_PERIOD_TEACH like", value, "coursePeriodTeach");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodTeachNotLike(String value) {
            addCriterion("COURSE_PERIOD_TEACH not like", value, "coursePeriodTeach");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodTeachIn(List<String> values) {
            addCriterion("COURSE_PERIOD_TEACH in", values, "coursePeriodTeach");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodTeachNotIn(List<String> values) {
            addCriterion("COURSE_PERIOD_TEACH not in", values, "coursePeriodTeach");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodTeachBetween(String value1, String value2) {
            addCriterion("COURSE_PERIOD_TEACH between", value1, value2, "coursePeriodTeach");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodTeachNotBetween(String value1, String value2) {
            addCriterion("COURSE_PERIOD_TEACH not between", value1, value2, "coursePeriodTeach");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodExperIsNull() {
            addCriterion("COURSE_PERIOD_EXPER is null");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodExperIsNotNull() {
            addCriterion("COURSE_PERIOD_EXPER is not null");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodExperEqualTo(String value) {
            addCriterion("COURSE_PERIOD_EXPER =", value, "coursePeriodExper");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodExperNotEqualTo(String value) {
            addCriterion("COURSE_PERIOD_EXPER <>", value, "coursePeriodExper");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodExperGreaterThan(String value) {
            addCriterion("COURSE_PERIOD_EXPER >", value, "coursePeriodExper");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodExperGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_PERIOD_EXPER >=", value, "coursePeriodExper");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodExperLessThan(String value) {
            addCriterion("COURSE_PERIOD_EXPER <", value, "coursePeriodExper");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodExperLessThanOrEqualTo(String value) {
            addCriterion("COURSE_PERIOD_EXPER <=", value, "coursePeriodExper");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodExperLike(String value) {
            addCriterion("COURSE_PERIOD_EXPER like", value, "coursePeriodExper");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodExperNotLike(String value) {
            addCriterion("COURSE_PERIOD_EXPER not like", value, "coursePeriodExper");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodExperIn(List<String> values) {
            addCriterion("COURSE_PERIOD_EXPER in", values, "coursePeriodExper");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodExperNotIn(List<String> values) {
            addCriterion("COURSE_PERIOD_EXPER not in", values, "coursePeriodExper");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodExperBetween(String value1, String value2) {
            addCriterion("COURSE_PERIOD_EXPER between", value1, value2, "coursePeriodExper");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodExperNotBetween(String value1, String value2) {
            addCriterion("COURSE_PERIOD_EXPER not between", value1, value2, "coursePeriodExper");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodMachineIsNull() {
            addCriterion("COURSE_PERIOD_MACHINE is null");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodMachineIsNotNull() {
            addCriterion("COURSE_PERIOD_MACHINE is not null");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodMachineEqualTo(String value) {
            addCriterion("COURSE_PERIOD_MACHINE =", value, "coursePeriodMachine");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodMachineNotEqualTo(String value) {
            addCriterion("COURSE_PERIOD_MACHINE <>", value, "coursePeriodMachine");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodMachineGreaterThan(String value) {
            addCriterion("COURSE_PERIOD_MACHINE >", value, "coursePeriodMachine");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodMachineGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_PERIOD_MACHINE >=", value, "coursePeriodMachine");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodMachineLessThan(String value) {
            addCriterion("COURSE_PERIOD_MACHINE <", value, "coursePeriodMachine");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodMachineLessThanOrEqualTo(String value) {
            addCriterion("COURSE_PERIOD_MACHINE <=", value, "coursePeriodMachine");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodMachineLike(String value) {
            addCriterion("COURSE_PERIOD_MACHINE like", value, "coursePeriodMachine");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodMachineNotLike(String value) {
            addCriterion("COURSE_PERIOD_MACHINE not like", value, "coursePeriodMachine");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodMachineIn(List<String> values) {
            addCriterion("COURSE_PERIOD_MACHINE in", values, "coursePeriodMachine");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodMachineNotIn(List<String> values) {
            addCriterion("COURSE_PERIOD_MACHINE not in", values, "coursePeriodMachine");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodMachineBetween(String value1, String value2) {
            addCriterion("COURSE_PERIOD_MACHINE between", value1, value2, "coursePeriodMachine");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodMachineNotBetween(String value1, String value2) {
            addCriterion("COURSE_PERIOD_MACHINE not between", value1, value2, "coursePeriodMachine");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodOtherIsNull() {
            addCriterion("COURSE_PERIOD_OTHER is null");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodOtherIsNotNull() {
            addCriterion("COURSE_PERIOD_OTHER is not null");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodOtherEqualTo(String value) {
            addCriterion("COURSE_PERIOD_OTHER =", value, "coursePeriodOther");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodOtherNotEqualTo(String value) {
            addCriterion("COURSE_PERIOD_OTHER <>", value, "coursePeriodOther");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodOtherGreaterThan(String value) {
            addCriterion("COURSE_PERIOD_OTHER >", value, "coursePeriodOther");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodOtherGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_PERIOD_OTHER >=", value, "coursePeriodOther");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodOtherLessThan(String value) {
            addCriterion("COURSE_PERIOD_OTHER <", value, "coursePeriodOther");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodOtherLessThanOrEqualTo(String value) {
            addCriterion("COURSE_PERIOD_OTHER <=", value, "coursePeriodOther");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodOtherLike(String value) {
            addCriterion("COURSE_PERIOD_OTHER like", value, "coursePeriodOther");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodOtherNotLike(String value) {
            addCriterion("COURSE_PERIOD_OTHER not like", value, "coursePeriodOther");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodOtherIn(List<String> values) {
            addCriterion("COURSE_PERIOD_OTHER in", values, "coursePeriodOther");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodOtherNotIn(List<String> values) {
            addCriterion("COURSE_PERIOD_OTHER not in", values, "coursePeriodOther");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodOtherBetween(String value1, String value2) {
            addCriterion("COURSE_PERIOD_OTHER between", value1, value2, "coursePeriodOther");
            return (Criteria) this;
        }

        public Criteria andCoursePeriodOtherNotBetween(String value1, String value2) {
            addCriterion("COURSE_PERIOD_OTHER not between", value1, value2, "coursePeriodOther");
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

        public Criteria andCourseUserCountIsNull() {
            addCriterion("COURSE_USER_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andCourseUserCountIsNotNull() {
            addCriterion("COURSE_USER_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andCourseUserCountEqualTo(String value) {
            addCriterion("COURSE_USER_COUNT =", value, "courseUserCount");
            return (Criteria) this;
        }

        public Criteria andCourseUserCountNotEqualTo(String value) {
            addCriterion("COURSE_USER_COUNT <>", value, "courseUserCount");
            return (Criteria) this;
        }

        public Criteria andCourseUserCountGreaterThan(String value) {
            addCriterion("COURSE_USER_COUNT >", value, "courseUserCount");
            return (Criteria) this;
        }

        public Criteria andCourseUserCountGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_USER_COUNT >=", value, "courseUserCount");
            return (Criteria) this;
        }

        public Criteria andCourseUserCountLessThan(String value) {
            addCriterion("COURSE_USER_COUNT <", value, "courseUserCount");
            return (Criteria) this;
        }

        public Criteria andCourseUserCountLessThanOrEqualTo(String value) {
            addCriterion("COURSE_USER_COUNT <=", value, "courseUserCount");
            return (Criteria) this;
        }

        public Criteria andCourseUserCountLike(String value) {
            addCriterion("COURSE_USER_COUNT like", value, "courseUserCount");
            return (Criteria) this;
        }

        public Criteria andCourseUserCountNotLike(String value) {
            addCriterion("COURSE_USER_COUNT not like", value, "courseUserCount");
            return (Criteria) this;
        }

        public Criteria andCourseUserCountIn(List<String> values) {
            addCriterion("COURSE_USER_COUNT in", values, "courseUserCount");
            return (Criteria) this;
        }

        public Criteria andCourseUserCountNotIn(List<String> values) {
            addCriterion("COURSE_USER_COUNT not in", values, "courseUserCount");
            return (Criteria) this;
        }

        public Criteria andCourseUserCountBetween(String value1, String value2) {
            addCriterion("COURSE_USER_COUNT between", value1, value2, "courseUserCount");
            return (Criteria) this;
        }

        public Criteria andCourseUserCountNotBetween(String value1, String value2) {
            addCriterion("COURSE_USER_COUNT not between", value1, value2, "courseUserCount");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleIdIsNull() {
            addCriterion("COURSE_MOUDLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleIdIsNotNull() {
            addCriterion("COURSE_MOUDLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleIdEqualTo(String value) {
            addCriterion("COURSE_MOUDLE_ID =", value, "courseMoudleId");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleIdNotEqualTo(String value) {
            addCriterion("COURSE_MOUDLE_ID <>", value, "courseMoudleId");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleIdGreaterThan(String value) {
            addCriterion("COURSE_MOUDLE_ID >", value, "courseMoudleId");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleIdGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_MOUDLE_ID >=", value, "courseMoudleId");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleIdLessThan(String value) {
            addCriterion("COURSE_MOUDLE_ID <", value, "courseMoudleId");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleIdLessThanOrEqualTo(String value) {
            addCriterion("COURSE_MOUDLE_ID <=", value, "courseMoudleId");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleIdLike(String value) {
            addCriterion("COURSE_MOUDLE_ID like", value, "courseMoudleId");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleIdNotLike(String value) {
            addCriterion("COURSE_MOUDLE_ID not like", value, "courseMoudleId");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleIdIn(List<String> values) {
            addCriterion("COURSE_MOUDLE_ID in", values, "courseMoudleId");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleIdNotIn(List<String> values) {
            addCriterion("COURSE_MOUDLE_ID not in", values, "courseMoudleId");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleIdBetween(String value1, String value2) {
            addCriterion("COURSE_MOUDLE_ID between", value1, value2, "courseMoudleId");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleIdNotBetween(String value1, String value2) {
            addCriterion("COURSE_MOUDLE_ID not between", value1, value2, "courseMoudleId");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleNameIsNull() {
            addCriterion("COURSE_MOUDLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleNameIsNotNull() {
            addCriterion("COURSE_MOUDLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleNameEqualTo(String value) {
            addCriterion("COURSE_MOUDLE_NAME =", value, "courseMoudleName");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleNameNotEqualTo(String value) {
            addCriterion("COURSE_MOUDLE_NAME <>", value, "courseMoudleName");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleNameGreaterThan(String value) {
            addCriterion("COURSE_MOUDLE_NAME >", value, "courseMoudleName");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleNameGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_MOUDLE_NAME >=", value, "courseMoudleName");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleNameLessThan(String value) {
            addCriterion("COURSE_MOUDLE_NAME <", value, "courseMoudleName");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleNameLessThanOrEqualTo(String value) {
            addCriterion("COURSE_MOUDLE_NAME <=", value, "courseMoudleName");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleNameLike(String value) {
            addCriterion("COURSE_MOUDLE_NAME like", value, "courseMoudleName");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleNameNotLike(String value) {
            addCriterion("COURSE_MOUDLE_NAME not like", value, "courseMoudleName");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleNameIn(List<String> values) {
            addCriterion("COURSE_MOUDLE_NAME in", values, "courseMoudleName");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleNameNotIn(List<String> values) {
            addCriterion("COURSE_MOUDLE_NAME not in", values, "courseMoudleName");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleNameBetween(String value1, String value2) {
            addCriterion("COURSE_MOUDLE_NAME between", value1, value2, "courseMoudleName");
            return (Criteria) this;
        }

        public Criteria andCourseMoudleNameNotBetween(String value1, String value2) {
            addCriterion("COURSE_MOUDLE_NAME not between", value1, value2, "courseMoudleName");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgFlowIsNull() {
            addCriterion("ASSUME_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgFlowIsNotNull() {
            addCriterion("ASSUME_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgFlowEqualTo(String value) {
            addCriterion("ASSUME_ORG_FLOW =", value, "assumeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgFlowNotEqualTo(String value) {
            addCriterion("ASSUME_ORG_FLOW <>", value, "assumeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgFlowGreaterThan(String value) {
            addCriterion("ASSUME_ORG_FLOW >", value, "assumeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ASSUME_ORG_FLOW >=", value, "assumeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgFlowLessThan(String value) {
            addCriterion("ASSUME_ORG_FLOW <", value, "assumeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("ASSUME_ORG_FLOW <=", value, "assumeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgFlowLike(String value) {
            addCriterion("ASSUME_ORG_FLOW like", value, "assumeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgFlowNotLike(String value) {
            addCriterion("ASSUME_ORG_FLOW not like", value, "assumeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgFlowIn(List<String> values) {
            addCriterion("ASSUME_ORG_FLOW in", values, "assumeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgFlowNotIn(List<String> values) {
            addCriterion("ASSUME_ORG_FLOW not in", values, "assumeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgFlowBetween(String value1, String value2) {
            addCriterion("ASSUME_ORG_FLOW between", value1, value2, "assumeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgFlowNotBetween(String value1, String value2) {
            addCriterion("ASSUME_ORG_FLOW not between", value1, value2, "assumeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgNameIsNull() {
            addCriterion("ASSUME_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgNameIsNotNull() {
            addCriterion("ASSUME_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgNameEqualTo(String value) {
            addCriterion("ASSUME_ORG_NAME =", value, "assumeOrgName");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgNameNotEqualTo(String value) {
            addCriterion("ASSUME_ORG_NAME <>", value, "assumeOrgName");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgNameGreaterThan(String value) {
            addCriterion("ASSUME_ORG_NAME >", value, "assumeOrgName");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("ASSUME_ORG_NAME >=", value, "assumeOrgName");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgNameLessThan(String value) {
            addCriterion("ASSUME_ORG_NAME <", value, "assumeOrgName");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgNameLessThanOrEqualTo(String value) {
            addCriterion("ASSUME_ORG_NAME <=", value, "assumeOrgName");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgNameLike(String value) {
            addCriterion("ASSUME_ORG_NAME like", value, "assumeOrgName");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgNameNotLike(String value) {
            addCriterion("ASSUME_ORG_NAME not like", value, "assumeOrgName");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgNameIn(List<String> values) {
            addCriterion("ASSUME_ORG_NAME in", values, "assumeOrgName");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgNameNotIn(List<String> values) {
            addCriterion("ASSUME_ORG_NAME not in", values, "assumeOrgName");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgNameBetween(String value1, String value2) {
            addCriterion("ASSUME_ORG_NAME between", value1, value2, "assumeOrgName");
            return (Criteria) this;
        }

        public Criteria andAssumeOrgNameNotBetween(String value1, String value2) {
            addCriterion("ASSUME_ORG_NAME not between", value1, value2, "assumeOrgName");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerFlowIsNull() {
            addCriterion("COURSE_SPEAKER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerFlowIsNotNull() {
            addCriterion("COURSE_SPEAKER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerFlowEqualTo(String value) {
            addCriterion("COURSE_SPEAKER_FLOW =", value, "courseSpeakerFlow");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerFlowNotEqualTo(String value) {
            addCriterion("COURSE_SPEAKER_FLOW <>", value, "courseSpeakerFlow");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerFlowGreaterThan(String value) {
            addCriterion("COURSE_SPEAKER_FLOW >", value, "courseSpeakerFlow");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerFlowGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_SPEAKER_FLOW >=", value, "courseSpeakerFlow");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerFlowLessThan(String value) {
            addCriterion("COURSE_SPEAKER_FLOW <", value, "courseSpeakerFlow");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerFlowLessThanOrEqualTo(String value) {
            addCriterion("COURSE_SPEAKER_FLOW <=", value, "courseSpeakerFlow");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerFlowLike(String value) {
            addCriterion("COURSE_SPEAKER_FLOW like", value, "courseSpeakerFlow");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerFlowNotLike(String value) {
            addCriterion("COURSE_SPEAKER_FLOW not like", value, "courseSpeakerFlow");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerFlowIn(List<String> values) {
            addCriterion("COURSE_SPEAKER_FLOW in", values, "courseSpeakerFlow");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerFlowNotIn(List<String> values) {
            addCriterion("COURSE_SPEAKER_FLOW not in", values, "courseSpeakerFlow");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerFlowBetween(String value1, String value2) {
            addCriterion("COURSE_SPEAKER_FLOW between", value1, value2, "courseSpeakerFlow");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerFlowNotBetween(String value1, String value2) {
            addCriterion("COURSE_SPEAKER_FLOW not between", value1, value2, "courseSpeakerFlow");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerPhoneIsNull() {
            addCriterion("COURSE_SPEAKER_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerPhoneIsNotNull() {
            addCriterion("COURSE_SPEAKER_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerPhoneEqualTo(String value) {
            addCriterion("COURSE_SPEAKER_PHONE =", value, "courseSpeakerPhone");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerPhoneNotEqualTo(String value) {
            addCriterion("COURSE_SPEAKER_PHONE <>", value, "courseSpeakerPhone");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerPhoneGreaterThan(String value) {
            addCriterion("COURSE_SPEAKER_PHONE >", value, "courseSpeakerPhone");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_SPEAKER_PHONE >=", value, "courseSpeakerPhone");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerPhoneLessThan(String value) {
            addCriterion("COURSE_SPEAKER_PHONE <", value, "courseSpeakerPhone");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerPhoneLessThanOrEqualTo(String value) {
            addCriterion("COURSE_SPEAKER_PHONE <=", value, "courseSpeakerPhone");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerPhoneLike(String value) {
            addCriterion("COURSE_SPEAKER_PHONE like", value, "courseSpeakerPhone");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerPhoneNotLike(String value) {
            addCriterion("COURSE_SPEAKER_PHONE not like", value, "courseSpeakerPhone");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerPhoneIn(List<String> values) {
            addCriterion("COURSE_SPEAKER_PHONE in", values, "courseSpeakerPhone");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerPhoneNotIn(List<String> values) {
            addCriterion("COURSE_SPEAKER_PHONE not in", values, "courseSpeakerPhone");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerPhoneBetween(String value1, String value2) {
            addCriterion("COURSE_SPEAKER_PHONE between", value1, value2, "courseSpeakerPhone");
            return (Criteria) this;
        }

        public Criteria andCourseSpeakerPhoneNotBetween(String value1, String value2) {
            addCriterion("COURSE_SPEAKER_PHONE not between", value1, value2, "courseSpeakerPhone");
            return (Criteria) this;
        }

        public Criteria andCourseSessionIsNull() {
            addCriterion("COURSE_SESSION is null");
            return (Criteria) this;
        }

        public Criteria andCourseSessionIsNotNull() {
            addCriterion("COURSE_SESSION is not null");
            return (Criteria) this;
        }

        public Criteria andCourseSessionEqualTo(String value) {
            addCriterion("COURSE_SESSION =", value, "courseSession");
            return (Criteria) this;
        }

        public Criteria andCourseSessionNotEqualTo(String value) {
            addCriterion("COURSE_SESSION <>", value, "courseSession");
            return (Criteria) this;
        }

        public Criteria andCourseSessionGreaterThan(String value) {
            addCriterion("COURSE_SESSION >", value, "courseSession");
            return (Criteria) this;
        }

        public Criteria andCourseSessionGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_SESSION >=", value, "courseSession");
            return (Criteria) this;
        }

        public Criteria andCourseSessionLessThan(String value) {
            addCriterion("COURSE_SESSION <", value, "courseSession");
            return (Criteria) this;
        }

        public Criteria andCourseSessionLessThanOrEqualTo(String value) {
            addCriterion("COURSE_SESSION <=", value, "courseSession");
            return (Criteria) this;
        }

        public Criteria andCourseSessionLike(String value) {
            addCriterion("COURSE_SESSION like", value, "courseSession");
            return (Criteria) this;
        }

        public Criteria andCourseSessionNotLike(String value) {
            addCriterion("COURSE_SESSION not like", value, "courseSession");
            return (Criteria) this;
        }

        public Criteria andCourseSessionIn(List<String> values) {
            addCriterion("COURSE_SESSION in", values, "courseSession");
            return (Criteria) this;
        }

        public Criteria andCourseSessionNotIn(List<String> values) {
            addCriterion("COURSE_SESSION not in", values, "courseSession");
            return (Criteria) this;
        }

        public Criteria andCourseSessionBetween(String value1, String value2) {
            addCriterion("COURSE_SESSION between", value1, value2, "courseSession");
            return (Criteria) this;
        }

        public Criteria andCourseSessionNotBetween(String value1, String value2) {
            addCriterion("COURSE_SESSION not between", value1, value2, "courseSession");
            return (Criteria) this;
        }

        public Criteria andPreCourseIsNull() {
            addCriterion("PRE_COURSE is null");
            return (Criteria) this;
        }

        public Criteria andPreCourseIsNotNull() {
            addCriterion("PRE_COURSE is not null");
            return (Criteria) this;
        }

        public Criteria andPreCourseEqualTo(String value) {
            addCriterion("PRE_COURSE =", value, "preCourse");
            return (Criteria) this;
        }

        public Criteria andPreCourseNotEqualTo(String value) {
            addCriterion("PRE_COURSE <>", value, "preCourse");
            return (Criteria) this;
        }

        public Criteria andPreCourseGreaterThan(String value) {
            addCriterion("PRE_COURSE >", value, "preCourse");
            return (Criteria) this;
        }

        public Criteria andPreCourseGreaterThanOrEqualTo(String value) {
            addCriterion("PRE_COURSE >=", value, "preCourse");
            return (Criteria) this;
        }

        public Criteria andPreCourseLessThan(String value) {
            addCriterion("PRE_COURSE <", value, "preCourse");
            return (Criteria) this;
        }

        public Criteria andPreCourseLessThanOrEqualTo(String value) {
            addCriterion("PRE_COURSE <=", value, "preCourse");
            return (Criteria) this;
        }

        public Criteria andPreCourseLike(String value) {
            addCriterion("PRE_COURSE like", value, "preCourse");
            return (Criteria) this;
        }

        public Criteria andPreCourseNotLike(String value) {
            addCriterion("PRE_COURSE not like", value, "preCourse");
            return (Criteria) this;
        }

        public Criteria andPreCourseIn(List<String> values) {
            addCriterion("PRE_COURSE in", values, "preCourse");
            return (Criteria) this;
        }

        public Criteria andPreCourseNotIn(List<String> values) {
            addCriterion("PRE_COURSE not in", values, "preCourse");
            return (Criteria) this;
        }

        public Criteria andPreCourseBetween(String value1, String value2) {
            addCriterion("PRE_COURSE between", value1, value2, "preCourse");
            return (Criteria) this;
        }

        public Criteria andPreCourseNotBetween(String value1, String value2) {
            addCriterion("PRE_COURSE not between", value1, value2, "preCourse");
            return (Criteria) this;
        }

        public Criteria andEffectiveGradeIsNull() {
            addCriterion("EFFECTIVE_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andEffectiveGradeIsNotNull() {
            addCriterion("EFFECTIVE_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andEffectiveGradeEqualTo(String value) {
            addCriterion("EFFECTIVE_GRADE =", value, "effectiveGrade");
            return (Criteria) this;
        }

        public Criteria andEffectiveGradeNotEqualTo(String value) {
            addCriterion("EFFECTIVE_GRADE <>", value, "effectiveGrade");
            return (Criteria) this;
        }

        public Criteria andEffectiveGradeGreaterThan(String value) {
            addCriterion("EFFECTIVE_GRADE >", value, "effectiveGrade");
            return (Criteria) this;
        }

        public Criteria andEffectiveGradeGreaterThanOrEqualTo(String value) {
            addCriterion("EFFECTIVE_GRADE >=", value, "effectiveGrade");
            return (Criteria) this;
        }

        public Criteria andEffectiveGradeLessThan(String value) {
            addCriterion("EFFECTIVE_GRADE <", value, "effectiveGrade");
            return (Criteria) this;
        }

        public Criteria andEffectiveGradeLessThanOrEqualTo(String value) {
            addCriterion("EFFECTIVE_GRADE <=", value, "effectiveGrade");
            return (Criteria) this;
        }

        public Criteria andEffectiveGradeLike(String value) {
            addCriterion("EFFECTIVE_GRADE like", value, "effectiveGrade");
            return (Criteria) this;
        }

        public Criteria andEffectiveGradeNotLike(String value) {
            addCriterion("EFFECTIVE_GRADE not like", value, "effectiveGrade");
            return (Criteria) this;
        }

        public Criteria andEffectiveGradeIn(List<String> values) {
            addCriterion("EFFECTIVE_GRADE in", values, "effectiveGrade");
            return (Criteria) this;
        }

        public Criteria andEffectiveGradeNotIn(List<String> values) {
            addCriterion("EFFECTIVE_GRADE not in", values, "effectiveGrade");
            return (Criteria) this;
        }

        public Criteria andEffectiveGradeBetween(String value1, String value2) {
            addCriterion("EFFECTIVE_GRADE between", value1, value2, "effectiveGrade");
            return (Criteria) this;
        }

        public Criteria andEffectiveGradeNotBetween(String value1, String value2) {
            addCriterion("EFFECTIVE_GRADE not between", value1, value2, "effectiveGrade");
            return (Criteria) this;
        }

        public Criteria andClassApplyMemoIsNull() {
            addCriterion("CLASS_APPLY_MEMO is null");
            return (Criteria) this;
        }

        public Criteria andClassApplyMemoIsNotNull() {
            addCriterion("CLASS_APPLY_MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andClassApplyMemoEqualTo(String value) {
            addCriterion("CLASS_APPLY_MEMO =", value, "classApplyMemo");
            return (Criteria) this;
        }

        public Criteria andClassApplyMemoNotEqualTo(String value) {
            addCriterion("CLASS_APPLY_MEMO <>", value, "classApplyMemo");
            return (Criteria) this;
        }

        public Criteria andClassApplyMemoGreaterThan(String value) {
            addCriterion("CLASS_APPLY_MEMO >", value, "classApplyMemo");
            return (Criteria) this;
        }

        public Criteria andClassApplyMemoGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_APPLY_MEMO >=", value, "classApplyMemo");
            return (Criteria) this;
        }

        public Criteria andClassApplyMemoLessThan(String value) {
            addCriterion("CLASS_APPLY_MEMO <", value, "classApplyMemo");
            return (Criteria) this;
        }

        public Criteria andClassApplyMemoLessThanOrEqualTo(String value) {
            addCriterion("CLASS_APPLY_MEMO <=", value, "classApplyMemo");
            return (Criteria) this;
        }

        public Criteria andClassApplyMemoLike(String value) {
            addCriterion("CLASS_APPLY_MEMO like", value, "classApplyMemo");
            return (Criteria) this;
        }

        public Criteria andClassApplyMemoNotLike(String value) {
            addCriterion("CLASS_APPLY_MEMO not like", value, "classApplyMemo");
            return (Criteria) this;
        }

        public Criteria andClassApplyMemoIn(List<String> values) {
            addCriterion("CLASS_APPLY_MEMO in", values, "classApplyMemo");
            return (Criteria) this;
        }

        public Criteria andClassApplyMemoNotIn(List<String> values) {
            addCriterion("CLASS_APPLY_MEMO not in", values, "classApplyMemo");
            return (Criteria) this;
        }

        public Criteria andClassApplyMemoBetween(String value1, String value2) {
            addCriterion("CLASS_APPLY_MEMO between", value1, value2, "classApplyMemo");
            return (Criteria) this;
        }

        public Criteria andClassApplyMemoNotBetween(String value1, String value2) {
            addCriterion("CLASS_APPLY_MEMO not between", value1, value2, "classApplyMemo");
            return (Criteria) this;
        }

        public Criteria andClassApplyFlagIsNull() {
            addCriterion("CLASS_APPLY_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andClassApplyFlagIsNotNull() {
            addCriterion("CLASS_APPLY_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andClassApplyFlagEqualTo(String value) {
            addCriterion("CLASS_APPLY_FLAG =", value, "classApplyFlag");
            return (Criteria) this;
        }

        public Criteria andClassApplyFlagNotEqualTo(String value) {
            addCriterion("CLASS_APPLY_FLAG <>", value, "classApplyFlag");
            return (Criteria) this;
        }

        public Criteria andClassApplyFlagGreaterThan(String value) {
            addCriterion("CLASS_APPLY_FLAG >", value, "classApplyFlag");
            return (Criteria) this;
        }

        public Criteria andClassApplyFlagGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_APPLY_FLAG >=", value, "classApplyFlag");
            return (Criteria) this;
        }

        public Criteria andClassApplyFlagLessThan(String value) {
            addCriterion("CLASS_APPLY_FLAG <", value, "classApplyFlag");
            return (Criteria) this;
        }

        public Criteria andClassApplyFlagLessThanOrEqualTo(String value) {
            addCriterion("CLASS_APPLY_FLAG <=", value, "classApplyFlag");
            return (Criteria) this;
        }

        public Criteria andClassApplyFlagLike(String value) {
            addCriterion("CLASS_APPLY_FLAG like", value, "classApplyFlag");
            return (Criteria) this;
        }

        public Criteria andClassApplyFlagNotLike(String value) {
            addCriterion("CLASS_APPLY_FLAG not like", value, "classApplyFlag");
            return (Criteria) this;
        }

        public Criteria andClassApplyFlagIn(List<String> values) {
            addCriterion("CLASS_APPLY_FLAG in", values, "classApplyFlag");
            return (Criteria) this;
        }

        public Criteria andClassApplyFlagNotIn(List<String> values) {
            addCriterion("CLASS_APPLY_FLAG not in", values, "classApplyFlag");
            return (Criteria) this;
        }

        public Criteria andClassApplyFlagBetween(String value1, String value2) {
            addCriterion("CLASS_APPLY_FLAG between", value1, value2, "classApplyFlag");
            return (Criteria) this;
        }

        public Criteria andClassApplyFlagNotBetween(String value1, String value2) {
            addCriterion("CLASS_APPLY_FLAG not between", value1, value2, "classApplyFlag");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleMemoIsNull() {
            addCriterion("COURSE_SCHEDULE_MEMO is null");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleMemoIsNotNull() {
            addCriterion("COURSE_SCHEDULE_MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleMemoEqualTo(String value) {
            addCriterion("COURSE_SCHEDULE_MEMO =", value, "courseScheduleMemo");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleMemoNotEqualTo(String value) {
            addCriterion("COURSE_SCHEDULE_MEMO <>", value, "courseScheduleMemo");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleMemoGreaterThan(String value) {
            addCriterion("COURSE_SCHEDULE_MEMO >", value, "courseScheduleMemo");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleMemoGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_SCHEDULE_MEMO >=", value, "courseScheduleMemo");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleMemoLessThan(String value) {
            addCriterion("COURSE_SCHEDULE_MEMO <", value, "courseScheduleMemo");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleMemoLessThanOrEqualTo(String value) {
            addCriterion("COURSE_SCHEDULE_MEMO <=", value, "courseScheduleMemo");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleMemoLike(String value) {
            addCriterion("COURSE_SCHEDULE_MEMO like", value, "courseScheduleMemo");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleMemoNotLike(String value) {
            addCriterion("COURSE_SCHEDULE_MEMO not like", value, "courseScheduleMemo");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleMemoIn(List<String> values) {
            addCriterion("COURSE_SCHEDULE_MEMO in", values, "courseScheduleMemo");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleMemoNotIn(List<String> values) {
            addCriterion("COURSE_SCHEDULE_MEMO not in", values, "courseScheduleMemo");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleMemoBetween(String value1, String value2) {
            addCriterion("COURSE_SCHEDULE_MEMO between", value1, value2, "courseScheduleMemo");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleMemoNotBetween(String value1, String value2) {
            addCriterion("COURSE_SCHEDULE_MEMO not between", value1, value2, "courseScheduleMemo");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleFlagIsNull() {
            addCriterion("COURSE_SCHEDULE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleFlagIsNotNull() {
            addCriterion("COURSE_SCHEDULE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleFlagEqualTo(String value) {
            addCriterion("COURSE_SCHEDULE_FLAG =", value, "courseScheduleFlag");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleFlagNotEqualTo(String value) {
            addCriterion("COURSE_SCHEDULE_FLAG <>", value, "courseScheduleFlag");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleFlagGreaterThan(String value) {
            addCriterion("COURSE_SCHEDULE_FLAG >", value, "courseScheduleFlag");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleFlagGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_SCHEDULE_FLAG >=", value, "courseScheduleFlag");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleFlagLessThan(String value) {
            addCriterion("COURSE_SCHEDULE_FLAG <", value, "courseScheduleFlag");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleFlagLessThanOrEqualTo(String value) {
            addCriterion("COURSE_SCHEDULE_FLAG <=", value, "courseScheduleFlag");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleFlagLike(String value) {
            addCriterion("COURSE_SCHEDULE_FLAG like", value, "courseScheduleFlag");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleFlagNotLike(String value) {
            addCriterion("COURSE_SCHEDULE_FLAG not like", value, "courseScheduleFlag");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleFlagIn(List<String> values) {
            addCriterion("COURSE_SCHEDULE_FLAG in", values, "courseScheduleFlag");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleFlagNotIn(List<String> values) {
            addCriterion("COURSE_SCHEDULE_FLAG not in", values, "courseScheduleFlag");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleFlagBetween(String value1, String value2) {
            addCriterion("COURSE_SCHEDULE_FLAG between", value1, value2, "courseScheduleFlag");
            return (Criteria) this;
        }

        public Criteria andCourseScheduleFlagNotBetween(String value1, String value2) {
            addCriterion("COURSE_SCHEDULE_FLAG not between", value1, value2, "courseScheduleFlag");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateMemoIsNull() {
            addCriterion("COURSE_EVALUATE_MEMO is null");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateMemoIsNotNull() {
            addCriterion("COURSE_EVALUATE_MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateMemoEqualTo(String value) {
            addCriterion("COURSE_EVALUATE_MEMO =", value, "courseEvaluateMemo");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateMemoNotEqualTo(String value) {
            addCriterion("COURSE_EVALUATE_MEMO <>", value, "courseEvaluateMemo");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateMemoGreaterThan(String value) {
            addCriterion("COURSE_EVALUATE_MEMO >", value, "courseEvaluateMemo");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateMemoGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_EVALUATE_MEMO >=", value, "courseEvaluateMemo");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateMemoLessThan(String value) {
            addCriterion("COURSE_EVALUATE_MEMO <", value, "courseEvaluateMemo");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateMemoLessThanOrEqualTo(String value) {
            addCriterion("COURSE_EVALUATE_MEMO <=", value, "courseEvaluateMemo");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateMemoLike(String value) {
            addCriterion("COURSE_EVALUATE_MEMO like", value, "courseEvaluateMemo");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateMemoNotLike(String value) {
            addCriterion("COURSE_EVALUATE_MEMO not like", value, "courseEvaluateMemo");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateMemoIn(List<String> values) {
            addCriterion("COURSE_EVALUATE_MEMO in", values, "courseEvaluateMemo");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateMemoNotIn(List<String> values) {
            addCriterion("COURSE_EVALUATE_MEMO not in", values, "courseEvaluateMemo");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateMemoBetween(String value1, String value2) {
            addCriterion("COURSE_EVALUATE_MEMO between", value1, value2, "courseEvaluateMemo");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateMemoNotBetween(String value1, String value2) {
            addCriterion("COURSE_EVALUATE_MEMO not between", value1, value2, "courseEvaluateMemo");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateFlagIsNull() {
            addCriterion("COURSE_EVALUATE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateFlagIsNotNull() {
            addCriterion("COURSE_EVALUATE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateFlagEqualTo(String value) {
            addCriterion("COURSE_EVALUATE_FLAG =", value, "courseEvaluateFlag");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateFlagNotEqualTo(String value) {
            addCriterion("COURSE_EVALUATE_FLAG <>", value, "courseEvaluateFlag");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateFlagGreaterThan(String value) {
            addCriterion("COURSE_EVALUATE_FLAG >", value, "courseEvaluateFlag");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateFlagGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_EVALUATE_FLAG >=", value, "courseEvaluateFlag");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateFlagLessThan(String value) {
            addCriterion("COURSE_EVALUATE_FLAG <", value, "courseEvaluateFlag");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateFlagLessThanOrEqualTo(String value) {
            addCriterion("COURSE_EVALUATE_FLAG <=", value, "courseEvaluateFlag");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateFlagLike(String value) {
            addCriterion("COURSE_EVALUATE_FLAG like", value, "courseEvaluateFlag");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateFlagNotLike(String value) {
            addCriterion("COURSE_EVALUATE_FLAG not like", value, "courseEvaluateFlag");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateFlagIn(List<String> values) {
            addCriterion("COURSE_EVALUATE_FLAG in", values, "courseEvaluateFlag");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateFlagNotIn(List<String> values) {
            addCriterion("COURSE_EVALUATE_FLAG not in", values, "courseEvaluateFlag");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateFlagBetween(String value1, String value2) {
            addCriterion("COURSE_EVALUATE_FLAG between", value1, value2, "courseEvaluateFlag");
            return (Criteria) this;
        }

        public Criteria andCourseEvaluateFlagNotBetween(String value1, String value2) {
            addCriterion("COURSE_EVALUATE_FLAG not between", value1, value2, "courseEvaluateFlag");
            return (Criteria) this;
        }

        public Criteria andResultInputIsNull() {
            addCriterion("RESULT_INPUT is null");
            return (Criteria) this;
        }

        public Criteria andResultInputIsNotNull() {
            addCriterion("RESULT_INPUT is not null");
            return (Criteria) this;
        }

        public Criteria andResultInputEqualTo(String value) {
            addCriterion("RESULT_INPUT =", value, "resultInput");
            return (Criteria) this;
        }

        public Criteria andResultInputNotEqualTo(String value) {
            addCriterion("RESULT_INPUT <>", value, "resultInput");
            return (Criteria) this;
        }

        public Criteria andResultInputGreaterThan(String value) {
            addCriterion("RESULT_INPUT >", value, "resultInput");
            return (Criteria) this;
        }

        public Criteria andResultInputGreaterThanOrEqualTo(String value) {
            addCriterion("RESULT_INPUT >=", value, "resultInput");
            return (Criteria) this;
        }

        public Criteria andResultInputLessThan(String value) {
            addCriterion("RESULT_INPUT <", value, "resultInput");
            return (Criteria) this;
        }

        public Criteria andResultInputLessThanOrEqualTo(String value) {
            addCriterion("RESULT_INPUT <=", value, "resultInput");
            return (Criteria) this;
        }

        public Criteria andResultInputLike(String value) {
            addCriterion("RESULT_INPUT like", value, "resultInput");
            return (Criteria) this;
        }

        public Criteria andResultInputNotLike(String value) {
            addCriterion("RESULT_INPUT not like", value, "resultInput");
            return (Criteria) this;
        }

        public Criteria andResultInputIn(List<String> values) {
            addCriterion("RESULT_INPUT in", values, "resultInput");
            return (Criteria) this;
        }

        public Criteria andResultInputNotIn(List<String> values) {
            addCriterion("RESULT_INPUT not in", values, "resultInput");
            return (Criteria) this;
        }

        public Criteria andResultInputBetween(String value1, String value2) {
            addCriterion("RESULT_INPUT between", value1, value2, "resultInput");
            return (Criteria) this;
        }

        public Criteria andResultInputNotBetween(String value1, String value2) {
            addCriterion("RESULT_INPUT not between", value1, value2, "resultInput");
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