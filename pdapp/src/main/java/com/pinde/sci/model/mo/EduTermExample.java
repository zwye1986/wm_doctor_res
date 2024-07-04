package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EduTermExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EduTermExample() {
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

        public Criteria andTermStartTimeIsNull() {
            addCriterion("TERM_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeIsNotNull() {
            addCriterion("TERM_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeEqualTo(String value) {
            addCriterion("TERM_START_TIME =", value, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeNotEqualTo(String value) {
            addCriterion("TERM_START_TIME <>", value, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeGreaterThan(String value) {
            addCriterion("TERM_START_TIME >", value, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TERM_START_TIME >=", value, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeLessThan(String value) {
            addCriterion("TERM_START_TIME <", value, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeLessThanOrEqualTo(String value) {
            addCriterion("TERM_START_TIME <=", value, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeLike(String value) {
            addCriterion("TERM_START_TIME like", value, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeNotLike(String value) {
            addCriterion("TERM_START_TIME not like", value, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeIn(List<String> values) {
            addCriterion("TERM_START_TIME in", values, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeNotIn(List<String> values) {
            addCriterion("TERM_START_TIME not in", values, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeBetween(String value1, String value2) {
            addCriterion("TERM_START_TIME between", value1, value2, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeNotBetween(String value1, String value2) {
            addCriterion("TERM_START_TIME not between", value1, value2, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermEndTimeIsNull() {
            addCriterion("TERM_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTermEndTimeIsNotNull() {
            addCriterion("TERM_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTermEndTimeEqualTo(String value) {
            addCriterion("TERM_END_TIME =", value, "termEndTime");
            return (Criteria) this;
        }

        public Criteria andTermEndTimeNotEqualTo(String value) {
            addCriterion("TERM_END_TIME <>", value, "termEndTime");
            return (Criteria) this;
        }

        public Criteria andTermEndTimeGreaterThan(String value) {
            addCriterion("TERM_END_TIME >", value, "termEndTime");
            return (Criteria) this;
        }

        public Criteria andTermEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TERM_END_TIME >=", value, "termEndTime");
            return (Criteria) this;
        }

        public Criteria andTermEndTimeLessThan(String value) {
            addCriterion("TERM_END_TIME <", value, "termEndTime");
            return (Criteria) this;
        }

        public Criteria andTermEndTimeLessThanOrEqualTo(String value) {
            addCriterion("TERM_END_TIME <=", value, "termEndTime");
            return (Criteria) this;
        }

        public Criteria andTermEndTimeLike(String value) {
            addCriterion("TERM_END_TIME like", value, "termEndTime");
            return (Criteria) this;
        }

        public Criteria andTermEndTimeNotLike(String value) {
            addCriterion("TERM_END_TIME not like", value, "termEndTime");
            return (Criteria) this;
        }

        public Criteria andTermEndTimeIn(List<String> values) {
            addCriterion("TERM_END_TIME in", values, "termEndTime");
            return (Criteria) this;
        }

        public Criteria andTermEndTimeNotIn(List<String> values) {
            addCriterion("TERM_END_TIME not in", values, "termEndTime");
            return (Criteria) this;
        }

        public Criteria andTermEndTimeBetween(String value1, String value2) {
            addCriterion("TERM_END_TIME between", value1, value2, "termEndTime");
            return (Criteria) this;
        }

        public Criteria andTermEndTimeNotBetween(String value1, String value2) {
            addCriterion("TERM_END_TIME not between", value1, value2, "termEndTime");
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