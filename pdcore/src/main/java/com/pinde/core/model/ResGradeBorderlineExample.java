package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResGradeBorderlineExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResGradeBorderlineExample() {
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

        public Criteria andBorderlineFlowIsNull() {
            addCriterion("BORDERLINE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andBorderlineFlowIsNotNull() {
            addCriterion("BORDERLINE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andBorderlineFlowEqualTo(String value) {
            addCriterion("BORDERLINE_FLOW =", value, "borderlineFlow");
            return (Criteria) this;
        }

        public Criteria andBorderlineFlowNotEqualTo(String value) {
            addCriterion("BORDERLINE_FLOW <>", value, "borderlineFlow");
            return (Criteria) this;
        }

        public Criteria andBorderlineFlowGreaterThan(String value) {
            addCriterion("BORDERLINE_FLOW >", value, "borderlineFlow");
            return (Criteria) this;
        }

        public Criteria andBorderlineFlowGreaterThanOrEqualTo(String value) {
            addCriterion("BORDERLINE_FLOW >=", value, "borderlineFlow");
            return (Criteria) this;
        }

        public Criteria andBorderlineFlowLessThan(String value) {
            addCriterion("BORDERLINE_FLOW <", value, "borderlineFlow");
            return (Criteria) this;
        }

        public Criteria andBorderlineFlowLessThanOrEqualTo(String value) {
            addCriterion("BORDERLINE_FLOW <=", value, "borderlineFlow");
            return (Criteria) this;
        }

        public Criteria andBorderlineFlowLike(String value) {
            addCriterion("BORDERLINE_FLOW like", value, "borderlineFlow");
            return (Criteria) this;
        }

        public Criteria andBorderlineFlowNotLike(String value) {
            addCriterion("BORDERLINE_FLOW not like", value, "borderlineFlow");
            return (Criteria) this;
        }

        public Criteria andBorderlineFlowIn(List<String> values) {
            addCriterion("BORDERLINE_FLOW in", values, "borderlineFlow");
            return (Criteria) this;
        }

        public Criteria andBorderlineFlowNotIn(List<String> values) {
            addCriterion("BORDERLINE_FLOW not in", values, "borderlineFlow");
            return (Criteria) this;
        }

        public Criteria andBorderlineFlowBetween(String value1, String value2) {
            addCriterion("BORDERLINE_FLOW between", value1, value2, "borderlineFlow");
            return (Criteria) this;
        }

        public Criteria andBorderlineFlowNotBetween(String value1, String value2) {
            addCriterion("BORDERLINE_FLOW not between", value1, value2, "borderlineFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowIsNull() {
            addCriterion("EXAM_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andExamFlowIsNotNull() {
            addCriterion("EXAM_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andExamFlowEqualTo(String value) {
            addCriterion("EXAM_FLOW =", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotEqualTo(String value) {
            addCriterion("EXAM_FLOW <>", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowGreaterThan(String value) {
            addCriterion("EXAM_FLOW >", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_FLOW >=", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowLessThan(String value) {
            addCriterion("EXAM_FLOW <", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowLessThanOrEqualTo(String value) {
            addCriterion("EXAM_FLOW <=", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowLike(String value) {
            addCriterion("EXAM_FLOW like", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotLike(String value) {
            addCriterion("EXAM_FLOW not like", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowIn(List<String> values) {
            addCriterion("EXAM_FLOW in", values, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotIn(List<String> values) {
            addCriterion("EXAM_FLOW not in", values, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowBetween(String value1, String value2) {
            addCriterion("EXAM_FLOW between", value1, value2, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotBetween(String value1, String value2) {
            addCriterion("EXAM_FLOW not between", value1, value2, "examFlow");
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

        public Criteria andGradeStepIsNull() {
            addCriterion("GRADE_STEP is null");
            return (Criteria) this;
        }

        public Criteria andGradeStepIsNotNull() {
            addCriterion("GRADE_STEP is not null");
            return (Criteria) this;
        }

        public Criteria andGradeStepEqualTo(String value) {
            addCriterion("GRADE_STEP =", value, "gradeStep");
            return (Criteria) this;
        }

        public Criteria andGradeStepNotEqualTo(String value) {
            addCriterion("GRADE_STEP <>", value, "gradeStep");
            return (Criteria) this;
        }

        public Criteria andGradeStepGreaterThan(String value) {
            addCriterion("GRADE_STEP >", value, "gradeStep");
            return (Criteria) this;
        }

        public Criteria andGradeStepGreaterThanOrEqualTo(String value) {
            addCriterion("GRADE_STEP >=", value, "gradeStep");
            return (Criteria) this;
        }

        public Criteria andGradeStepLessThan(String value) {
            addCriterion("GRADE_STEP <", value, "gradeStep");
            return (Criteria) this;
        }

        public Criteria andGradeStepLessThanOrEqualTo(String value) {
            addCriterion("GRADE_STEP <=", value, "gradeStep");
            return (Criteria) this;
        }

        public Criteria andGradeStepLike(String value) {
            addCriterion("GRADE_STEP like", value, "gradeStep");
            return (Criteria) this;
        }

        public Criteria andGradeStepNotLike(String value) {
            addCriterion("GRADE_STEP not like", value, "gradeStep");
            return (Criteria) this;
        }

        public Criteria andGradeStepIn(List<String> values) {
            addCriterion("GRADE_STEP in", values, "gradeStep");
            return (Criteria) this;
        }

        public Criteria andGradeStepNotIn(List<String> values) {
            addCriterion("GRADE_STEP not in", values, "gradeStep");
            return (Criteria) this;
        }

        public Criteria andGradeStepBetween(String value1, String value2) {
            addCriterion("GRADE_STEP between", value1, value2, "gradeStep");
            return (Criteria) this;
        }

        public Criteria andGradeStepNotBetween(String value1, String value2) {
            addCriterion("GRADE_STEP not between", value1, value2, "gradeStep");
            return (Criteria) this;
        }

        public Criteria andGradeBorderlineIsNull() {
            addCriterion("GRADE_BORDERLINE is null");
            return (Criteria) this;
        }

        public Criteria andGradeBorderlineIsNotNull() {
            addCriterion("GRADE_BORDERLINE is not null");
            return (Criteria) this;
        }

        public Criteria andGradeBorderlineEqualTo(String value) {
            addCriterion("GRADE_BORDERLINE =", value, "gradeBorderline");
            return (Criteria) this;
        }

        public Criteria andGradeBorderlineNotEqualTo(String value) {
            addCriterion("GRADE_BORDERLINE <>", value, "gradeBorderline");
            return (Criteria) this;
        }

        public Criteria andGradeBorderlineGreaterThan(String value) {
            addCriterion("GRADE_BORDERLINE >", value, "gradeBorderline");
            return (Criteria) this;
        }

        public Criteria andGradeBorderlineGreaterThanOrEqualTo(String value) {
            addCriterion("GRADE_BORDERLINE >=", value, "gradeBorderline");
            return (Criteria) this;
        }

        public Criteria andGradeBorderlineLessThan(String value) {
            addCriterion("GRADE_BORDERLINE <", value, "gradeBorderline");
            return (Criteria) this;
        }

        public Criteria andGradeBorderlineLessThanOrEqualTo(String value) {
            addCriterion("GRADE_BORDERLINE <=", value, "gradeBorderline");
            return (Criteria) this;
        }

        public Criteria andGradeBorderlineLike(String value) {
            addCriterion("GRADE_BORDERLINE like", value, "gradeBorderline");
            return (Criteria) this;
        }

        public Criteria andGradeBorderlineNotLike(String value) {
            addCriterion("GRADE_BORDERLINE not like", value, "gradeBorderline");
            return (Criteria) this;
        }

        public Criteria andGradeBorderlineIn(List<String> values) {
            addCriterion("GRADE_BORDERLINE in", values, "gradeBorderline");
            return (Criteria) this;
        }

        public Criteria andGradeBorderlineNotIn(List<String> values) {
            addCriterion("GRADE_BORDERLINE not in", values, "gradeBorderline");
            return (Criteria) this;
        }

        public Criteria andGradeBorderlineBetween(String value1, String value2) {
            addCriterion("GRADE_BORDERLINE between", value1, value2, "gradeBorderline");
            return (Criteria) this;
        }

        public Criteria andGradeBorderlineNotBetween(String value1, String value2) {
            addCriterion("GRADE_BORDERLINE not between", value1, value2, "gradeBorderline");
            return (Criteria) this;
        }

        public Criteria andPublishFlagIsNull() {
            addCriterion("PUBLISH_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andPublishFlagIsNotNull() {
            addCriterion("PUBLISH_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andPublishFlagEqualTo(String value) {
            addCriterion("PUBLISH_FLAG =", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagNotEqualTo(String value) {
            addCriterion("PUBLISH_FLAG <>", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagGreaterThan(String value) {
            addCriterion("PUBLISH_FLAG >", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISH_FLAG >=", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagLessThan(String value) {
            addCriterion("PUBLISH_FLAG <", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagLessThanOrEqualTo(String value) {
            addCriterion("PUBLISH_FLAG <=", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagLike(String value) {
            addCriterion("PUBLISH_FLAG like", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagNotLike(String value) {
            addCriterion("PUBLISH_FLAG not like", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagIn(List<String> values) {
            addCriterion("PUBLISH_FLAG in", values, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagNotIn(List<String> values) {
            addCriterion("PUBLISH_FLAG not in", values, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagBetween(String value1, String value2) {
            addCriterion("PUBLISH_FLAG between", value1, value2, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagNotBetween(String value1, String value2) {
            addCriterion("PUBLISH_FLAG not between", value1, value2, "publishFlag");
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