package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EduCourseMaterialExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EduCourseMaterialExample() {
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

        public Criteria andSchoolYearDescIsNull() {
            addCriterion("SCHOOL_YEAR_DESC is null");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescIsNotNull() {
            addCriterion("SCHOOL_YEAR_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescEqualTo(String value) {
            addCriterion("SCHOOL_YEAR_DESC =", value, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescNotEqualTo(String value) {
            addCriterion("SCHOOL_YEAR_DESC <>", value, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescGreaterThan(String value) {
            addCriterion("SCHOOL_YEAR_DESC >", value, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_YEAR_DESC >=", value, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescLessThan(String value) {
            addCriterion("SCHOOL_YEAR_DESC <", value, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_YEAR_DESC <=", value, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescLike(String value) {
            addCriterion("SCHOOL_YEAR_DESC like", value, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescNotLike(String value) {
            addCriterion("SCHOOL_YEAR_DESC not like", value, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescIn(List<String> values) {
            addCriterion("SCHOOL_YEAR_DESC in", values, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescNotIn(List<String> values) {
            addCriterion("SCHOOL_YEAR_DESC not in", values, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescBetween(String value1, String value2) {
            addCriterion("SCHOOL_YEAR_DESC between", value1, value2, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_YEAR_DESC not between", value1, value2, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescIsNull() {
            addCriterion("GRADE_DESC is null");
            return (Criteria) this;
        }

        public Criteria andGradeDescIsNotNull() {
            addCriterion("GRADE_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andGradeDescEqualTo(String value) {
            addCriterion("GRADE_DESC =", value, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescNotEqualTo(String value) {
            addCriterion("GRADE_DESC <>", value, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescGreaterThan(String value) {
            addCriterion("GRADE_DESC >", value, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescGreaterThanOrEqualTo(String value) {
            addCriterion("GRADE_DESC >=", value, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescLessThan(String value) {
            addCriterion("GRADE_DESC <", value, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescLessThanOrEqualTo(String value) {
            addCriterion("GRADE_DESC <=", value, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescLike(String value) {
            addCriterion("GRADE_DESC like", value, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescNotLike(String value) {
            addCriterion("GRADE_DESC not like", value, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescIn(List<String> values) {
            addCriterion("GRADE_DESC in", values, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescNotIn(List<String> values) {
            addCriterion("GRADE_DESC not in", values, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescBetween(String value1, String value2) {
            addCriterion("GRADE_DESC between", value1, value2, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescNotBetween(String value1, String value2) {
            addCriterion("GRADE_DESC not between", value1, value2, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescIsNull() {
            addCriterion("TERM_DESC is null");
            return (Criteria) this;
        }

        public Criteria andTermDescIsNotNull() {
            addCriterion("TERM_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andTermDescEqualTo(String value) {
            addCriterion("TERM_DESC =", value, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescNotEqualTo(String value) {
            addCriterion("TERM_DESC <>", value, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescGreaterThan(String value) {
            addCriterion("TERM_DESC >", value, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescGreaterThanOrEqualTo(String value) {
            addCriterion("TERM_DESC >=", value, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescLessThan(String value) {
            addCriterion("TERM_DESC <", value, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescLessThanOrEqualTo(String value) {
            addCriterion("TERM_DESC <=", value, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescLike(String value) {
            addCriterion("TERM_DESC like", value, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescNotLike(String value) {
            addCriterion("TERM_DESC not like", value, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescIn(List<String> values) {
            addCriterion("TERM_DESC in", values, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescNotIn(List<String> values) {
            addCriterion("TERM_DESC not in", values, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescBetween(String value1, String value2) {
            addCriterion("TERM_DESC between", value1, value2, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescNotBetween(String value1, String value2) {
            addCriterion("TERM_DESC not between", value1, value2, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceIsNull() {
            addCriterion("TEACHING_PLACE is null");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceIsNotNull() {
            addCriterion("TEACHING_PLACE is not null");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceEqualTo(String value) {
            addCriterion("TEACHING_PLACE =", value, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceNotEqualTo(String value) {
            addCriterion("TEACHING_PLACE <>", value, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceGreaterThan(String value) {
            addCriterion("TEACHING_PLACE >", value, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHING_PLACE >=", value, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceLessThan(String value) {
            addCriterion("TEACHING_PLACE <", value, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceLessThanOrEqualTo(String value) {
            addCriterion("TEACHING_PLACE <=", value, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceLike(String value) {
            addCriterion("TEACHING_PLACE like", value, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceNotLike(String value) {
            addCriterion("TEACHING_PLACE not like", value, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceIn(List<String> values) {
            addCriterion("TEACHING_PLACE in", values, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceNotIn(List<String> values) {
            addCriterion("TEACHING_PLACE not in", values, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceBetween(String value1, String value2) {
            addCriterion("TEACHING_PLACE between", value1, value2, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceNotBetween(String value1, String value2) {
            addCriterion("TEACHING_PLACE not between", value1, value2, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andFormTypeIsNull() {
            addCriterion("FORM_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andFormTypeIsNotNull() {
            addCriterion("FORM_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andFormTypeEqualTo(String value) {
            addCriterion("FORM_TYPE =", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeNotEqualTo(String value) {
            addCriterion("FORM_TYPE <>", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeGreaterThan(String value) {
            addCriterion("FORM_TYPE >", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeGreaterThanOrEqualTo(String value) {
            addCriterion("FORM_TYPE >=", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeLessThan(String value) {
            addCriterion("FORM_TYPE <", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeLessThanOrEqualTo(String value) {
            addCriterion("FORM_TYPE <=", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeLike(String value) {
            addCriterion("FORM_TYPE like", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeNotLike(String value) {
            addCriterion("FORM_TYPE not like", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeIn(List<String> values) {
            addCriterion("FORM_TYPE in", values, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeNotIn(List<String> values) {
            addCriterion("FORM_TYPE not in", values, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeBetween(String value1, String value2) {
            addCriterion("FORM_TYPE between", value1, value2, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeNotBetween(String value1, String value2) {
            addCriterion("FORM_TYPE not between", value1, value2, "formType");
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