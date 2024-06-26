package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EduScheduleClassExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EduScheduleClassExample() {
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

        public Criteria andTermFlowIsNull() {
            addCriterion("TERM_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTermFlowIsNotNull() {
            addCriterion("TERM_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTermFlowEqualTo(String value) {
            addCriterion("TERM_FLOW =", value, "termFlow");
            return (Criteria) this;
        }

        public Criteria andTermFlowNotEqualTo(String value) {
            addCriterion("TERM_FLOW <>", value, "termFlow");
            return (Criteria) this;
        }

        public Criteria andTermFlowGreaterThan(String value) {
            addCriterion("TERM_FLOW >", value, "termFlow");
            return (Criteria) this;
        }

        public Criteria andTermFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TERM_FLOW >=", value, "termFlow");
            return (Criteria) this;
        }

        public Criteria andTermFlowLessThan(String value) {
            addCriterion("TERM_FLOW <", value, "termFlow");
            return (Criteria) this;
        }

        public Criteria andTermFlowLessThanOrEqualTo(String value) {
            addCriterion("TERM_FLOW <=", value, "termFlow");
            return (Criteria) this;
        }

        public Criteria andTermFlowLike(String value) {
            addCriterion("TERM_FLOW like", value, "termFlow");
            return (Criteria) this;
        }

        public Criteria andTermFlowNotLike(String value) {
            addCriterion("TERM_FLOW not like", value, "termFlow");
            return (Criteria) this;
        }

        public Criteria andTermFlowIn(List<String> values) {
            addCriterion("TERM_FLOW in", values, "termFlow");
            return (Criteria) this;
        }

        public Criteria andTermFlowNotIn(List<String> values) {
            addCriterion("TERM_FLOW not in", values, "termFlow");
            return (Criteria) this;
        }

        public Criteria andTermFlowBetween(String value1, String value2) {
            addCriterion("TERM_FLOW between", value1, value2, "termFlow");
            return (Criteria) this;
        }

        public Criteria andTermFlowNotBetween(String value1, String value2) {
            addCriterion("TERM_FLOW not between", value1, value2, "termFlow");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIsNull() {
            addCriterion("SESSION_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIsNotNull() {
            addCriterion("SESSION_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberEqualTo(String value) {
            addCriterion("SESSION_NUMBER =", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotEqualTo(String value) {
            addCriterion("SESSION_NUMBER <>", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThan(String value) {
            addCriterion("SESSION_NUMBER >", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER >=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThan(String value) {
            addCriterion("SESSION_NUMBER <", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER <=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLike(String value) {
            addCriterion("SESSION_NUMBER like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotLike(String value) {
            addCriterion("SESSION_NUMBER not like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIn(List<String> values) {
            addCriterion("SESSION_NUMBER in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotIn(List<String> values) {
            addCriterion("SESSION_NUMBER not in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER between", value1, value2, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER not between", value1, value2, "sessionNumber");
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

        public Criteria andClassIdIsNull() {
            addCriterion("CLASS_ID is null");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNotNull() {
            addCriterion("CLASS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andClassIdEqualTo(String value) {
            addCriterion("CLASS_ID =", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotEqualTo(String value) {
            addCriterion("CLASS_ID <>", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThan(String value) {
            addCriterion("CLASS_ID >", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_ID >=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThan(String value) {
            addCriterion("CLASS_ID <", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThanOrEqualTo(String value) {
            addCriterion("CLASS_ID <=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLike(String value) {
            addCriterion("CLASS_ID like", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotLike(String value) {
            addCriterion("CLASS_ID not like", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdIn(List<String> values) {
            addCriterion("CLASS_ID in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotIn(List<String> values) {
            addCriterion("CLASS_ID not in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdBetween(String value1, String value2) {
            addCriterion("CLASS_ID between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotBetween(String value1, String value2) {
            addCriterion("CLASS_ID not between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNull() {
            addCriterion("CLASS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNotNull() {
            addCriterion("CLASS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andClassNameEqualTo(String value) {
            addCriterion("CLASS_NAME =", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotEqualTo(String value) {
            addCriterion("CLASS_NAME <>", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThan(String value) {
            addCriterion("CLASS_NAME >", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_NAME >=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThan(String value) {
            addCriterion("CLASS_NAME <", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThanOrEqualTo(String value) {
            addCriterion("CLASS_NAME <=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLike(String value) {
            addCriterion("CLASS_NAME like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotLike(String value) {
            addCriterion("CLASS_NAME not like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameIn(List<String> values) {
            addCriterion("CLASS_NAME in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotIn(List<String> values) {
            addCriterion("CLASS_NAME not in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameBetween(String value1, String value2) {
            addCriterion("CLASS_NAME between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotBetween(String value1, String value2) {
            addCriterion("CLASS_NAME not between", value1, value2, "className");
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

        public Criteria andClassCourseNameIsNull() {
            addCriterion("CLASS_COURSE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andClassCourseNameIsNotNull() {
            addCriterion("CLASS_COURSE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andClassCourseNameEqualTo(String value) {
            addCriterion("CLASS_COURSE_NAME =", value, "classCourseName");
            return (Criteria) this;
        }

        public Criteria andClassCourseNameNotEqualTo(String value) {
            addCriterion("CLASS_COURSE_NAME <>", value, "classCourseName");
            return (Criteria) this;
        }

        public Criteria andClassCourseNameGreaterThan(String value) {
            addCriterion("CLASS_COURSE_NAME >", value, "classCourseName");
            return (Criteria) this;
        }

        public Criteria andClassCourseNameGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_COURSE_NAME >=", value, "classCourseName");
            return (Criteria) this;
        }

        public Criteria andClassCourseNameLessThan(String value) {
            addCriterion("CLASS_COURSE_NAME <", value, "classCourseName");
            return (Criteria) this;
        }

        public Criteria andClassCourseNameLessThanOrEqualTo(String value) {
            addCriterion("CLASS_COURSE_NAME <=", value, "classCourseName");
            return (Criteria) this;
        }

        public Criteria andClassCourseNameLike(String value) {
            addCriterion("CLASS_COURSE_NAME like", value, "classCourseName");
            return (Criteria) this;
        }

        public Criteria andClassCourseNameNotLike(String value) {
            addCriterion("CLASS_COURSE_NAME not like", value, "classCourseName");
            return (Criteria) this;
        }

        public Criteria andClassCourseNameIn(List<String> values) {
            addCriterion("CLASS_COURSE_NAME in", values, "classCourseName");
            return (Criteria) this;
        }

        public Criteria andClassCourseNameNotIn(List<String> values) {
            addCriterion("CLASS_COURSE_NAME not in", values, "classCourseName");
            return (Criteria) this;
        }

        public Criteria andClassCourseNameBetween(String value1, String value2) {
            addCriterion("CLASS_COURSE_NAME between", value1, value2, "classCourseName");
            return (Criteria) this;
        }

        public Criteria andClassCourseNameNotBetween(String value1, String value2) {
            addCriterion("CLASS_COURSE_NAME not between", value1, value2, "classCourseName");
            return (Criteria) this;
        }

        public Criteria andClassPeriodIsNull() {
            addCriterion("CLASS_PERIOD is null");
            return (Criteria) this;
        }

        public Criteria andClassPeriodIsNotNull() {
            addCriterion("CLASS_PERIOD is not null");
            return (Criteria) this;
        }

        public Criteria andClassPeriodEqualTo(String value) {
            addCriterion("CLASS_PERIOD =", value, "classPeriod");
            return (Criteria) this;
        }

        public Criteria andClassPeriodNotEqualTo(String value) {
            addCriterion("CLASS_PERIOD <>", value, "classPeriod");
            return (Criteria) this;
        }

        public Criteria andClassPeriodGreaterThan(String value) {
            addCriterion("CLASS_PERIOD >", value, "classPeriod");
            return (Criteria) this;
        }

        public Criteria andClassPeriodGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_PERIOD >=", value, "classPeriod");
            return (Criteria) this;
        }

        public Criteria andClassPeriodLessThan(String value) {
            addCriterion("CLASS_PERIOD <", value, "classPeriod");
            return (Criteria) this;
        }

        public Criteria andClassPeriodLessThanOrEqualTo(String value) {
            addCriterion("CLASS_PERIOD <=", value, "classPeriod");
            return (Criteria) this;
        }

        public Criteria andClassPeriodLike(String value) {
            addCriterion("CLASS_PERIOD like", value, "classPeriod");
            return (Criteria) this;
        }

        public Criteria andClassPeriodNotLike(String value) {
            addCriterion("CLASS_PERIOD not like", value, "classPeriod");
            return (Criteria) this;
        }

        public Criteria andClassPeriodIn(List<String> values) {
            addCriterion("CLASS_PERIOD in", values, "classPeriod");
            return (Criteria) this;
        }

        public Criteria andClassPeriodNotIn(List<String> values) {
            addCriterion("CLASS_PERIOD not in", values, "classPeriod");
            return (Criteria) this;
        }

        public Criteria andClassPeriodBetween(String value1, String value2) {
            addCriterion("CLASS_PERIOD between", value1, value2, "classPeriod");
            return (Criteria) this;
        }

        public Criteria andClassPeriodNotBetween(String value1, String value2) {
            addCriterion("CLASS_PERIOD not between", value1, value2, "classPeriod");
            return (Criteria) this;
        }

        public Criteria andClassTimeIsNull() {
            addCriterion("CLASS_TIME is null");
            return (Criteria) this;
        }

        public Criteria andClassTimeIsNotNull() {
            addCriterion("CLASS_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andClassTimeEqualTo(String value) {
            addCriterion("CLASS_TIME =", value, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeNotEqualTo(String value) {
            addCriterion("CLASS_TIME <>", value, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeGreaterThan(String value) {
            addCriterion("CLASS_TIME >", value, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_TIME >=", value, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeLessThan(String value) {
            addCriterion("CLASS_TIME <", value, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeLessThanOrEqualTo(String value) {
            addCriterion("CLASS_TIME <=", value, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeLike(String value) {
            addCriterion("CLASS_TIME like", value, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeNotLike(String value) {
            addCriterion("CLASS_TIME not like", value, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeIn(List<String> values) {
            addCriterion("CLASS_TIME in", values, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeNotIn(List<String> values) {
            addCriterion("CLASS_TIME not in", values, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeBetween(String value1, String value2) {
            addCriterion("CLASS_TIME between", value1, value2, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassTimeNotBetween(String value1, String value2) {
            addCriterion("CLASS_TIME not between", value1, value2, "classTime");
            return (Criteria) this;
        }

        public Criteria andClassOrderIsNull() {
            addCriterion("CLASS_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andClassOrderIsNotNull() {
            addCriterion("CLASS_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andClassOrderEqualTo(String value) {
            addCriterion("CLASS_ORDER =", value, "classOrder");
            return (Criteria) this;
        }

        public Criteria andClassOrderNotEqualTo(String value) {
            addCriterion("CLASS_ORDER <>", value, "classOrder");
            return (Criteria) this;
        }

        public Criteria andClassOrderGreaterThan(String value) {
            addCriterion("CLASS_ORDER >", value, "classOrder");
            return (Criteria) this;
        }

        public Criteria andClassOrderGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_ORDER >=", value, "classOrder");
            return (Criteria) this;
        }

        public Criteria andClassOrderLessThan(String value) {
            addCriterion("CLASS_ORDER <", value, "classOrder");
            return (Criteria) this;
        }

        public Criteria andClassOrderLessThanOrEqualTo(String value) {
            addCriterion("CLASS_ORDER <=", value, "classOrder");
            return (Criteria) this;
        }

        public Criteria andClassOrderLike(String value) {
            addCriterion("CLASS_ORDER like", value, "classOrder");
            return (Criteria) this;
        }

        public Criteria andClassOrderNotLike(String value) {
            addCriterion("CLASS_ORDER not like", value, "classOrder");
            return (Criteria) this;
        }

        public Criteria andClassOrderIn(List<String> values) {
            addCriterion("CLASS_ORDER in", values, "classOrder");
            return (Criteria) this;
        }

        public Criteria andClassOrderNotIn(List<String> values) {
            addCriterion("CLASS_ORDER not in", values, "classOrder");
            return (Criteria) this;
        }

        public Criteria andClassOrderBetween(String value1, String value2) {
            addCriterion("CLASS_ORDER between", value1, value2, "classOrder");
            return (Criteria) this;
        }

        public Criteria andClassOrderNotBetween(String value1, String value2) {
            addCriterion("CLASS_ORDER not between", value1, value2, "classOrder");
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

        public Criteria andClassroomIdIsNull() {
            addCriterion("CLASSROOM_ID is null");
            return (Criteria) this;
        }

        public Criteria andClassroomIdIsNotNull() {
            addCriterion("CLASSROOM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andClassroomIdEqualTo(String value) {
            addCriterion("CLASSROOM_ID =", value, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdNotEqualTo(String value) {
            addCriterion("CLASSROOM_ID <>", value, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdGreaterThan(String value) {
            addCriterion("CLASSROOM_ID >", value, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdGreaterThanOrEqualTo(String value) {
            addCriterion("CLASSROOM_ID >=", value, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdLessThan(String value) {
            addCriterion("CLASSROOM_ID <", value, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdLessThanOrEqualTo(String value) {
            addCriterion("CLASSROOM_ID <=", value, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdLike(String value) {
            addCriterion("CLASSROOM_ID like", value, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdNotLike(String value) {
            addCriterion("CLASSROOM_ID not like", value, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdIn(List<String> values) {
            addCriterion("CLASSROOM_ID in", values, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdNotIn(List<String> values) {
            addCriterion("CLASSROOM_ID not in", values, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdBetween(String value1, String value2) {
            addCriterion("CLASSROOM_ID between", value1, value2, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomIdNotBetween(String value1, String value2) {
            addCriterion("CLASSROOM_ID not between", value1, value2, "classroomId");
            return (Criteria) this;
        }

        public Criteria andClassroomNameIsNull() {
            addCriterion("CLASSROOM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andClassroomNameIsNotNull() {
            addCriterion("CLASSROOM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andClassroomNameEqualTo(String value) {
            addCriterion("CLASSROOM_NAME =", value, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameNotEqualTo(String value) {
            addCriterion("CLASSROOM_NAME <>", value, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameGreaterThan(String value) {
            addCriterion("CLASSROOM_NAME >", value, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameGreaterThanOrEqualTo(String value) {
            addCriterion("CLASSROOM_NAME >=", value, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameLessThan(String value) {
            addCriterion("CLASSROOM_NAME <", value, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameLessThanOrEqualTo(String value) {
            addCriterion("CLASSROOM_NAME <=", value, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameLike(String value) {
            addCriterion("CLASSROOM_NAME like", value, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameNotLike(String value) {
            addCriterion("CLASSROOM_NAME not like", value, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameIn(List<String> values) {
            addCriterion("CLASSROOM_NAME in", values, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameNotIn(List<String> values) {
            addCriterion("CLASSROOM_NAME not in", values, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameBetween(String value1, String value2) {
            addCriterion("CLASSROOM_NAME between", value1, value2, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameNotBetween(String value1, String value2) {
            addCriterion("CLASSROOM_NAME not between", value1, value2, "classroomName");
            return (Criteria) this;
        }

        public Criteria andStudentMaxmunIsNull() {
            addCriterion("STUDENT_MAXMUN is null");
            return (Criteria) this;
        }

        public Criteria andStudentMaxmunIsNotNull() {
            addCriterion("STUDENT_MAXMUN is not null");
            return (Criteria) this;
        }

        public Criteria andStudentMaxmunEqualTo(String value) {
            addCriterion("STUDENT_MAXMUN =", value, "studentMaxmun");
            return (Criteria) this;
        }

        public Criteria andStudentMaxmunNotEqualTo(String value) {
            addCriterion("STUDENT_MAXMUN <>", value, "studentMaxmun");
            return (Criteria) this;
        }

        public Criteria andStudentMaxmunGreaterThan(String value) {
            addCriterion("STUDENT_MAXMUN >", value, "studentMaxmun");
            return (Criteria) this;
        }

        public Criteria andStudentMaxmunGreaterThanOrEqualTo(String value) {
            addCriterion("STUDENT_MAXMUN >=", value, "studentMaxmun");
            return (Criteria) this;
        }

        public Criteria andStudentMaxmunLessThan(String value) {
            addCriterion("STUDENT_MAXMUN <", value, "studentMaxmun");
            return (Criteria) this;
        }

        public Criteria andStudentMaxmunLessThanOrEqualTo(String value) {
            addCriterion("STUDENT_MAXMUN <=", value, "studentMaxmun");
            return (Criteria) this;
        }

        public Criteria andStudentMaxmunLike(String value) {
            addCriterion("STUDENT_MAXMUN like", value, "studentMaxmun");
            return (Criteria) this;
        }

        public Criteria andStudentMaxmunNotLike(String value) {
            addCriterion("STUDENT_MAXMUN not like", value, "studentMaxmun");
            return (Criteria) this;
        }

        public Criteria andStudentMaxmunIn(List<String> values) {
            addCriterion("STUDENT_MAXMUN in", values, "studentMaxmun");
            return (Criteria) this;
        }

        public Criteria andStudentMaxmunNotIn(List<String> values) {
            addCriterion("STUDENT_MAXMUN not in", values, "studentMaxmun");
            return (Criteria) this;
        }

        public Criteria andStudentMaxmunBetween(String value1, String value2) {
            addCriterion("STUDENT_MAXMUN between", value1, value2, "studentMaxmun");
            return (Criteria) this;
        }

        public Criteria andStudentMaxmunNotBetween(String value1, String value2) {
            addCriterion("STUDENT_MAXMUN not between", value1, value2, "studentMaxmun");
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

        public Criteria andMemoIsNull() {
            addCriterion("MEMO is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("MEMO =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("MEMO <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("MEMO >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("MEMO >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("MEMO <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("MEMO <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("MEMO like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("MEMO not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("MEMO in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("MEMO not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("MEMO between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("MEMO not between", value1, value2, "memo");
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