package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class OscaSkillsAssessmentTimeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OscaSkillsAssessmentTimeExample() {
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

        public Criteria andRecrodFlowIsNull() {
            addCriterion("RECROD_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRecrodFlowIsNotNull() {
            addCriterion("RECROD_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRecrodFlowEqualTo(String value) {
            addCriterion("RECROD_FLOW =", value, "recrodFlow");
            return (Criteria) this;
        }

        public Criteria andRecrodFlowNotEqualTo(String value) {
            addCriterion("RECROD_FLOW <>", value, "recrodFlow");
            return (Criteria) this;
        }

        public Criteria andRecrodFlowGreaterThan(String value) {
            addCriterion("RECROD_FLOW >", value, "recrodFlow");
            return (Criteria) this;
        }

        public Criteria andRecrodFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RECROD_FLOW >=", value, "recrodFlow");
            return (Criteria) this;
        }

        public Criteria andRecrodFlowLessThan(String value) {
            addCriterion("RECROD_FLOW <", value, "recrodFlow");
            return (Criteria) this;
        }

        public Criteria andRecrodFlowLessThanOrEqualTo(String value) {
            addCriterion("RECROD_FLOW <=", value, "recrodFlow");
            return (Criteria) this;
        }

        public Criteria andRecrodFlowLike(String value) {
            addCriterion("RECROD_FLOW like", value, "recrodFlow");
            return (Criteria) this;
        }

        public Criteria andRecrodFlowNotLike(String value) {
            addCriterion("RECROD_FLOW not like", value, "recrodFlow");
            return (Criteria) this;
        }

        public Criteria andRecrodFlowIn(List<String> values) {
            addCriterion("RECROD_FLOW in", values, "recrodFlow");
            return (Criteria) this;
        }

        public Criteria andRecrodFlowNotIn(List<String> values) {
            addCriterion("RECROD_FLOW not in", values, "recrodFlow");
            return (Criteria) this;
        }

        public Criteria andRecrodFlowBetween(String value1, String value2) {
            addCriterion("RECROD_FLOW between", value1, value2, "recrodFlow");
            return (Criteria) this;
        }

        public Criteria andRecrodFlowNotBetween(String value1, String value2) {
            addCriterion("RECROD_FLOW not between", value1, value2, "recrodFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowIsNull() {
            addCriterion("CLINICAL_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowIsNotNull() {
            addCriterion("CLINICAL_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowEqualTo(String value) {
            addCriterion("CLINICAL_FLOW =", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowNotEqualTo(String value) {
            addCriterion("CLINICAL_FLOW <>", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowGreaterThan(String value) {
            addCriterion("CLINICAL_FLOW >", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CLINICAL_FLOW >=", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowLessThan(String value) {
            addCriterion("CLINICAL_FLOW <", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowLessThanOrEqualTo(String value) {
            addCriterion("CLINICAL_FLOW <=", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowLike(String value) {
            addCriterion("CLINICAL_FLOW like", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowNotLike(String value) {
            addCriterion("CLINICAL_FLOW not like", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowIn(List<String> values) {
            addCriterion("CLINICAL_FLOW in", values, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowNotIn(List<String> values) {
            addCriterion("CLINICAL_FLOW not in", values, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowBetween(String value1, String value2) {
            addCriterion("CLINICAL_FLOW between", value1, value2, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowNotBetween(String value1, String value2) {
            addCriterion("CLINICAL_FLOW not between", value1, value2, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeIsNull() {
            addCriterion("EXAM_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeIsNotNull() {
            addCriterion("EXAM_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeEqualTo(String value) {
            addCriterion("EXAM_START_TIME =", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeNotEqualTo(String value) {
            addCriterion("EXAM_START_TIME <>", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeGreaterThan(String value) {
            addCriterion("EXAM_START_TIME >", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_START_TIME >=", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeLessThan(String value) {
            addCriterion("EXAM_START_TIME <", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeLessThanOrEqualTo(String value) {
            addCriterion("EXAM_START_TIME <=", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeLike(String value) {
            addCriterion("EXAM_START_TIME like", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeNotLike(String value) {
            addCriterion("EXAM_START_TIME not like", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeIn(List<String> values) {
            addCriterion("EXAM_START_TIME in", values, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeNotIn(List<String> values) {
            addCriterion("EXAM_START_TIME not in", values, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeBetween(String value1, String value2) {
            addCriterion("EXAM_START_TIME between", value1, value2, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeNotBetween(String value1, String value2) {
            addCriterion("EXAM_START_TIME not between", value1, value2, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeIsNull() {
            addCriterion("EXAM_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeIsNotNull() {
            addCriterion("EXAM_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeEqualTo(String value) {
            addCriterion("EXAM_END_TIME =", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeNotEqualTo(String value) {
            addCriterion("EXAM_END_TIME <>", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeGreaterThan(String value) {
            addCriterion("EXAM_END_TIME >", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_END_TIME >=", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeLessThan(String value) {
            addCriterion("EXAM_END_TIME <", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeLessThanOrEqualTo(String value) {
            addCriterion("EXAM_END_TIME <=", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeLike(String value) {
            addCriterion("EXAM_END_TIME like", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeNotLike(String value) {
            addCriterion("EXAM_END_TIME not like", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeIn(List<String> values) {
            addCriterion("EXAM_END_TIME in", values, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeNotIn(List<String> values) {
            addCriterion("EXAM_END_TIME not in", values, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeBetween(String value1, String value2) {
            addCriterion("EXAM_END_TIME between", value1, value2, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeNotBetween(String value1, String value2) {
            addCriterion("EXAM_END_TIME not between", value1, value2, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andTestNumberIsNull() {
            addCriterion("TEST_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andTestNumberIsNotNull() {
            addCriterion("TEST_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andTestNumberEqualTo(String value) {
            addCriterion("TEST_NUMBER =", value, "testNumber");
            return (Criteria) this;
        }

        public Criteria andTestNumberNotEqualTo(String value) {
            addCriterion("TEST_NUMBER <>", value, "testNumber");
            return (Criteria) this;
        }

        public Criteria andTestNumberGreaterThan(String value) {
            addCriterion("TEST_NUMBER >", value, "testNumber");
            return (Criteria) this;
        }

        public Criteria andTestNumberGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_NUMBER >=", value, "testNumber");
            return (Criteria) this;
        }

        public Criteria andTestNumberLessThan(String value) {
            addCriterion("TEST_NUMBER <", value, "testNumber");
            return (Criteria) this;
        }

        public Criteria andTestNumberLessThanOrEqualTo(String value) {
            addCriterion("TEST_NUMBER <=", value, "testNumber");
            return (Criteria) this;
        }

        public Criteria andTestNumberLike(String value) {
            addCriterion("TEST_NUMBER like", value, "testNumber");
            return (Criteria) this;
        }

        public Criteria andTestNumberNotLike(String value) {
            addCriterion("TEST_NUMBER not like", value, "testNumber");
            return (Criteria) this;
        }

        public Criteria andTestNumberIn(List<String> values) {
            addCriterion("TEST_NUMBER in", values, "testNumber");
            return (Criteria) this;
        }

        public Criteria andTestNumberNotIn(List<String> values) {
            addCriterion("TEST_NUMBER not in", values, "testNumber");
            return (Criteria) this;
        }

        public Criteria andTestNumberBetween(String value1, String value2) {
            addCriterion("TEST_NUMBER between", value1, value2, "testNumber");
            return (Criteria) this;
        }

        public Criteria andTestNumberNotBetween(String value1, String value2) {
            addCriterion("TEST_NUMBER not between", value1, value2, "testNumber");
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