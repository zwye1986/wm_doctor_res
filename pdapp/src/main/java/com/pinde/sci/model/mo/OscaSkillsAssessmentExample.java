package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class OscaSkillsAssessmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OscaSkillsAssessmentExample() {
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

        public Criteria andClinicalNameIsNull() {
            addCriterion("CLINICAL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andClinicalNameIsNotNull() {
            addCriterion("CLINICAL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andClinicalNameEqualTo(String value) {
            addCriterion("CLINICAL_NAME =", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameNotEqualTo(String value) {
            addCriterion("CLINICAL_NAME <>", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameGreaterThan(String value) {
            addCriterion("CLINICAL_NAME >", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameGreaterThanOrEqualTo(String value) {
            addCriterion("CLINICAL_NAME >=", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameLessThan(String value) {
            addCriterion("CLINICAL_NAME <", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameLessThanOrEqualTo(String value) {
            addCriterion("CLINICAL_NAME <=", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameLike(String value) {
            addCriterion("CLINICAL_NAME like", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameNotLike(String value) {
            addCriterion("CLINICAL_NAME not like", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameIn(List<String> values) {
            addCriterion("CLINICAL_NAME in", values, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameNotIn(List<String> values) {
            addCriterion("CLINICAL_NAME not in", values, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameBetween(String value1, String value2) {
            addCriterion("CLINICAL_NAME between", value1, value2, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameNotBetween(String value1, String value2) {
            addCriterion("CLINICAL_NAME not between", value1, value2, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalYearIsNull() {
            addCriterion("CLINICAL_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andClinicalYearIsNotNull() {
            addCriterion("CLINICAL_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andClinicalYearEqualTo(String value) {
            addCriterion("CLINICAL_YEAR =", value, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearNotEqualTo(String value) {
            addCriterion("CLINICAL_YEAR <>", value, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearGreaterThan(String value) {
            addCriterion("CLINICAL_YEAR >", value, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearGreaterThanOrEqualTo(String value) {
            addCriterion("CLINICAL_YEAR >=", value, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearLessThan(String value) {
            addCriterion("CLINICAL_YEAR <", value, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearLessThanOrEqualTo(String value) {
            addCriterion("CLINICAL_YEAR <=", value, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearLike(String value) {
            addCriterion("CLINICAL_YEAR like", value, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearNotLike(String value) {
            addCriterion("CLINICAL_YEAR not like", value, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearIn(List<String> values) {
            addCriterion("CLINICAL_YEAR in", values, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearNotIn(List<String> values) {
            addCriterion("CLINICAL_YEAR not in", values, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearBetween(String value1, String value2) {
            addCriterion("CLINICAL_YEAR between", value1, value2, "clinicalYear");
            return (Criteria) this;
        }

        public Criteria andClinicalYearNotBetween(String value1, String value2) {
            addCriterion("CLINICAL_YEAR not between", value1, value2, "clinicalYear");
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

        public Criteria andActionTypeIdIsNull() {
            addCriterion("ACTION_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdIsNotNull() {
            addCriterion("ACTION_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdEqualTo(String value) {
            addCriterion("ACTION_TYPE_ID =", value, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdNotEqualTo(String value) {
            addCriterion("ACTION_TYPE_ID <>", value, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdGreaterThan(String value) {
            addCriterion("ACTION_TYPE_ID >", value, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ACTION_TYPE_ID >=", value, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdLessThan(String value) {
            addCriterion("ACTION_TYPE_ID <", value, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ACTION_TYPE_ID <=", value, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdLike(String value) {
            addCriterion("ACTION_TYPE_ID like", value, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdNotLike(String value) {
            addCriterion("ACTION_TYPE_ID not like", value, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdIn(List<String> values) {
            addCriterion("ACTION_TYPE_ID in", values, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdNotIn(List<String> values) {
            addCriterion("ACTION_TYPE_ID not in", values, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdBetween(String value1, String value2) {
            addCriterion("ACTION_TYPE_ID between", value1, value2, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdNotBetween(String value1, String value2) {
            addCriterion("ACTION_TYPE_ID not between", value1, value2, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameIsNull() {
            addCriterion("ACTION_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameIsNotNull() {
            addCriterion("ACTION_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameEqualTo(String value) {
            addCriterion("ACTION_TYPE_NAME =", value, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameNotEqualTo(String value) {
            addCriterion("ACTION_TYPE_NAME <>", value, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameGreaterThan(String value) {
            addCriterion("ACTION_TYPE_NAME >", value, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACTION_TYPE_NAME >=", value, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameLessThan(String value) {
            addCriterion("ACTION_TYPE_NAME <", value, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameLessThanOrEqualTo(String value) {
            addCriterion("ACTION_TYPE_NAME <=", value, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameLike(String value) {
            addCriterion("ACTION_TYPE_NAME like", value, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameNotLike(String value) {
            addCriterion("ACTION_TYPE_NAME not like", value, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameIn(List<String> values) {
            addCriterion("ACTION_TYPE_NAME in", values, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameNotIn(List<String> values) {
            addCriterion("ACTION_TYPE_NAME not in", values, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameBetween(String value1, String value2) {
            addCriterion("ACTION_TYPE_NAME between", value1, value2, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameNotBetween(String value1, String value2) {
            addCriterion("ACTION_TYPE_NAME not between", value1, value2, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowIsNull() {
            addCriterion("SUBJECT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowIsNotNull() {
            addCriterion("SUBJECT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowEqualTo(String value) {
            addCriterion("SUBJECT_FLOW =", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotEqualTo(String value) {
            addCriterion("SUBJECT_FLOW <>", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowGreaterThan(String value) {
            addCriterion("SUBJECT_FLOW >", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_FLOW >=", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowLessThan(String value) {
            addCriterion("SUBJECT_FLOW <", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_FLOW <=", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowLike(String value) {
            addCriterion("SUBJECT_FLOW like", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotLike(String value) {
            addCriterion("SUBJECT_FLOW not like", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowIn(List<String> values) {
            addCriterion("SUBJECT_FLOW in", values, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotIn(List<String> values) {
            addCriterion("SUBJECT_FLOW not in", values, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowBetween(String value1, String value2) {
            addCriterion("SUBJECT_FLOW between", value1, value2, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_FLOW not between", value1, value2, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIsNull() {
            addCriterion("SUBJECT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIsNotNull() {
            addCriterion("SUBJECT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectNameEqualTo(String value) {
            addCriterion("SUBJECT_NAME =", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotEqualTo(String value) {
            addCriterion("SUBJECT_NAME <>", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameGreaterThan(String value) {
            addCriterion("SUBJECT_NAME >", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_NAME >=", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLessThan(String value) {
            addCriterion("SUBJECT_NAME <", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_NAME <=", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLike(String value) {
            addCriterion("SUBJECT_NAME like", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotLike(String value) {
            addCriterion("SUBJECT_NAME not like", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIn(List<String> values) {
            addCriterion("SUBJECT_NAME in", values, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotIn(List<String> values) {
            addCriterion("SUBJECT_NAME not in", values, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameBetween(String value1, String value2) {
            addCriterion("SUBJECT_NAME between", value1, value2, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_NAME not between", value1, value2, "subjectName");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeIsNull() {
            addCriterion("APPOINT_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeIsNotNull() {
            addCriterion("APPOINT_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeEqualTo(String value) {
            addCriterion("APPOINT_START_TIME =", value, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeNotEqualTo(String value) {
            addCriterion("APPOINT_START_TIME <>", value, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeGreaterThan(String value) {
            addCriterion("APPOINT_START_TIME >", value, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("APPOINT_START_TIME >=", value, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeLessThan(String value) {
            addCriterion("APPOINT_START_TIME <", value, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeLessThanOrEqualTo(String value) {
            addCriterion("APPOINT_START_TIME <=", value, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeLike(String value) {
            addCriterion("APPOINT_START_TIME like", value, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeNotLike(String value) {
            addCriterion("APPOINT_START_TIME not like", value, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeIn(List<String> values) {
            addCriterion("APPOINT_START_TIME in", values, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeNotIn(List<String> values) {
            addCriterion("APPOINT_START_TIME not in", values, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeBetween(String value1, String value2) {
            addCriterion("APPOINT_START_TIME between", value1, value2, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointStartTimeNotBetween(String value1, String value2) {
            addCriterion("APPOINT_START_TIME not between", value1, value2, "appointStartTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeIsNull() {
            addCriterion("APPOINT_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeIsNotNull() {
            addCriterion("APPOINT_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeEqualTo(String value) {
            addCriterion("APPOINT_END_TIME =", value, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeNotEqualTo(String value) {
            addCriterion("APPOINT_END_TIME <>", value, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeGreaterThan(String value) {
            addCriterion("APPOINT_END_TIME >", value, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("APPOINT_END_TIME >=", value, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeLessThan(String value) {
            addCriterion("APPOINT_END_TIME <", value, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeLessThanOrEqualTo(String value) {
            addCriterion("APPOINT_END_TIME <=", value, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeLike(String value) {
            addCriterion("APPOINT_END_TIME like", value, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeNotLike(String value) {
            addCriterion("APPOINT_END_TIME not like", value, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeIn(List<String> values) {
            addCriterion("APPOINT_END_TIME in", values, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeNotIn(List<String> values) {
            addCriterion("APPOINT_END_TIME not in", values, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeBetween(String value1, String value2) {
            addCriterion("APPOINT_END_TIME between", value1, value2, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointEndTimeNotBetween(String value1, String value2) {
            addCriterion("APPOINT_END_TIME not between", value1, value2, "appointEndTime");
            return (Criteria) this;
        }

        public Criteria andAppointNumIsNull() {
            addCriterion("APPOINT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andAppointNumIsNotNull() {
            addCriterion("APPOINT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andAppointNumEqualTo(Integer value) {
            addCriterion("APPOINT_NUM =", value, "appointNum");
            return (Criteria) this;
        }

        public Criteria andAppointNumNotEqualTo(Integer value) {
            addCriterion("APPOINT_NUM <>", value, "appointNum");
            return (Criteria) this;
        }

        public Criteria andAppointNumGreaterThan(Integer value) {
            addCriterion("APPOINT_NUM >", value, "appointNum");
            return (Criteria) this;
        }

        public Criteria andAppointNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("APPOINT_NUM >=", value, "appointNum");
            return (Criteria) this;
        }

        public Criteria andAppointNumLessThan(Integer value) {
            addCriterion("APPOINT_NUM <", value, "appointNum");
            return (Criteria) this;
        }

        public Criteria andAppointNumLessThanOrEqualTo(Integer value) {
            addCriterion("APPOINT_NUM <=", value, "appointNum");
            return (Criteria) this;
        }

        public Criteria andAppointNumIn(List<Integer> values) {
            addCriterion("APPOINT_NUM in", values, "appointNum");
            return (Criteria) this;
        }

        public Criteria andAppointNumNotIn(List<Integer> values) {
            addCriterion("APPOINT_NUM not in", values, "appointNum");
            return (Criteria) this;
        }

        public Criteria andAppointNumBetween(Integer value1, Integer value2) {
            addCriterion("APPOINT_NUM between", value1, value2, "appointNum");
            return (Criteria) this;
        }

        public Criteria andAppointNumNotBetween(Integer value1, Integer value2) {
            addCriterion("APPOINT_NUM not between", value1, value2, "appointNum");
            return (Criteria) this;
        }

        public Criteria andExamAddressIsNull() {
            addCriterion("EXAM_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andExamAddressIsNotNull() {
            addCriterion("EXAM_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andExamAddressEqualTo(String value) {
            addCriterion("EXAM_ADDRESS =", value, "examAddress");
            return (Criteria) this;
        }

        public Criteria andExamAddressNotEqualTo(String value) {
            addCriterion("EXAM_ADDRESS <>", value, "examAddress");
            return (Criteria) this;
        }

        public Criteria andExamAddressGreaterThan(String value) {
            addCriterion("EXAM_ADDRESS >", value, "examAddress");
            return (Criteria) this;
        }

        public Criteria andExamAddressGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_ADDRESS >=", value, "examAddress");
            return (Criteria) this;
        }

        public Criteria andExamAddressLessThan(String value) {
            addCriterion("EXAM_ADDRESS <", value, "examAddress");
            return (Criteria) this;
        }

        public Criteria andExamAddressLessThanOrEqualTo(String value) {
            addCriterion("EXAM_ADDRESS <=", value, "examAddress");
            return (Criteria) this;
        }

        public Criteria andExamAddressLike(String value) {
            addCriterion("EXAM_ADDRESS like", value, "examAddress");
            return (Criteria) this;
        }

        public Criteria andExamAddressNotLike(String value) {
            addCriterion("EXAM_ADDRESS not like", value, "examAddress");
            return (Criteria) this;
        }

        public Criteria andExamAddressIn(List<String> values) {
            addCriterion("EXAM_ADDRESS in", values, "examAddress");
            return (Criteria) this;
        }

        public Criteria andExamAddressNotIn(List<String> values) {
            addCriterion("EXAM_ADDRESS not in", values, "examAddress");
            return (Criteria) this;
        }

        public Criteria andExamAddressBetween(String value1, String value2) {
            addCriterion("EXAM_ADDRESS between", value1, value2, "examAddress");
            return (Criteria) this;
        }

        public Criteria andExamAddressNotBetween(String value1, String value2) {
            addCriterion("EXAM_ADDRESS not between", value1, value2, "examAddress");
            return (Criteria) this;
        }

        public Criteria andIsLocalIsNull() {
            addCriterion("IS_LOCAL is null");
            return (Criteria) this;
        }

        public Criteria andIsLocalIsNotNull() {
            addCriterion("IS_LOCAL is not null");
            return (Criteria) this;
        }

        public Criteria andIsLocalEqualTo(String value) {
            addCriterion("IS_LOCAL =", value, "isLocal");
            return (Criteria) this;
        }

        public Criteria andIsLocalNotEqualTo(String value) {
            addCriterion("IS_LOCAL <>", value, "isLocal");
            return (Criteria) this;
        }

        public Criteria andIsLocalGreaterThan(String value) {
            addCriterion("IS_LOCAL >", value, "isLocal");
            return (Criteria) this;
        }

        public Criteria andIsLocalGreaterThanOrEqualTo(String value) {
            addCriterion("IS_LOCAL >=", value, "isLocal");
            return (Criteria) this;
        }

        public Criteria andIsLocalLessThan(String value) {
            addCriterion("IS_LOCAL <", value, "isLocal");
            return (Criteria) this;
        }

        public Criteria andIsLocalLessThanOrEqualTo(String value) {
            addCriterion("IS_LOCAL <=", value, "isLocal");
            return (Criteria) this;
        }

        public Criteria andIsLocalLike(String value) {
            addCriterion("IS_LOCAL like", value, "isLocal");
            return (Criteria) this;
        }

        public Criteria andIsLocalNotLike(String value) {
            addCriterion("IS_LOCAL not like", value, "isLocal");
            return (Criteria) this;
        }

        public Criteria andIsLocalIn(List<String> values) {
            addCriterion("IS_LOCAL in", values, "isLocal");
            return (Criteria) this;
        }

        public Criteria andIsLocalNotIn(List<String> values) {
            addCriterion("IS_LOCAL not in", values, "isLocal");
            return (Criteria) this;
        }

        public Criteria andIsLocalBetween(String value1, String value2) {
            addCriterion("IS_LOCAL between", value1, value2, "isLocal");
            return (Criteria) this;
        }

        public Criteria andIsLocalNotBetween(String value1, String value2) {
            addCriterion("IS_LOCAL not between", value1, value2, "isLocal");
            return (Criteria) this;
        }

        public Criteria andOrgFlowIsNull() {
            addCriterion("ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andOrgFlowIsNotNull() {
            addCriterion("ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andOrgFlowEqualTo(String value) {
            addCriterion("ORG_FLOW =", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotEqualTo(String value) {
            addCriterion("ORG_FLOW <>", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGreaterThan(String value) {
            addCriterion("ORG_FLOW >", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_FLOW >=", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLessThan(String value) {
            addCriterion("ORG_FLOW <", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("ORG_FLOW <=", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLike(String value) {
            addCriterion("ORG_FLOW like", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotLike(String value) {
            addCriterion("ORG_FLOW not like", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowIn(List<String> values) {
            addCriterion("ORG_FLOW in", values, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotIn(List<String> values) {
            addCriterion("ORG_FLOW not in", values, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowBetween(String value1, String value2) {
            addCriterion("ORG_FLOW between", value1, value2, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotBetween(String value1, String value2) {
            addCriterion("ORG_FLOW not between", value1, value2, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNull() {
            addCriterion("ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("ORG_NAME =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("ORG_NAME <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("ORG_NAME >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NAME >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("ORG_NAME <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_NAME <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("ORG_NAME like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("ORG_NAME not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("ORG_NAME in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("ORG_NAME not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("ORG_NAME between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("ORG_NAME not between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andIsShowIsNull() {
            addCriterion("IS_SHOW is null");
            return (Criteria) this;
        }

        public Criteria andIsShowIsNotNull() {
            addCriterion("IS_SHOW is not null");
            return (Criteria) this;
        }

        public Criteria andIsShowEqualTo(String value) {
            addCriterion("IS_SHOW =", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotEqualTo(String value) {
            addCriterion("IS_SHOW <>", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowGreaterThan(String value) {
            addCriterion("IS_SHOW >", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SHOW >=", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLessThan(String value) {
            addCriterion("IS_SHOW <", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLessThanOrEqualTo(String value) {
            addCriterion("IS_SHOW <=", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLike(String value) {
            addCriterion("IS_SHOW like", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotLike(String value) {
            addCriterion("IS_SHOW not like", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowIn(List<String> values) {
            addCriterion("IS_SHOW in", values, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotIn(List<String> values) {
            addCriterion("IS_SHOW not in", values, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowBetween(String value1, String value2) {
            addCriterion("IS_SHOW between", value1, value2, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotBetween(String value1, String value2) {
            addCriterion("IS_SHOW not between", value1, value2, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsReleasedIsNull() {
            addCriterion("IS_RELEASED is null");
            return (Criteria) this;
        }

        public Criteria andIsReleasedIsNotNull() {
            addCriterion("IS_RELEASED is not null");
            return (Criteria) this;
        }

        public Criteria andIsReleasedEqualTo(String value) {
            addCriterion("IS_RELEASED =", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedNotEqualTo(String value) {
            addCriterion("IS_RELEASED <>", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedGreaterThan(String value) {
            addCriterion("IS_RELEASED >", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedGreaterThanOrEqualTo(String value) {
            addCriterion("IS_RELEASED >=", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedLessThan(String value) {
            addCriterion("IS_RELEASED <", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedLessThanOrEqualTo(String value) {
            addCriterion("IS_RELEASED <=", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedLike(String value) {
            addCriterion("IS_RELEASED like", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedNotLike(String value) {
            addCriterion("IS_RELEASED not like", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedIn(List<String> values) {
            addCriterion("IS_RELEASED in", values, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedNotIn(List<String> values) {
            addCriterion("IS_RELEASED not in", values, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedBetween(String value1, String value2) {
            addCriterion("IS_RELEASED between", value1, value2, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedNotBetween(String value1, String value2) {
            addCriterion("IS_RELEASED not between", value1, value2, "isReleased");
            return (Criteria) this;
        }

        public Criteria andCodeInfoIsNull() {
            addCriterion("CODE_INFO is null");
            return (Criteria) this;
        }

        public Criteria andCodeInfoIsNotNull() {
            addCriterion("CODE_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andCodeInfoEqualTo(String value) {
            addCriterion("CODE_INFO =", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoNotEqualTo(String value) {
            addCriterion("CODE_INFO <>", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoGreaterThan(String value) {
            addCriterion("CODE_INFO >", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoGreaterThanOrEqualTo(String value) {
            addCriterion("CODE_INFO >=", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoLessThan(String value) {
            addCriterion("CODE_INFO <", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoLessThanOrEqualTo(String value) {
            addCriterion("CODE_INFO <=", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoLike(String value) {
            addCriterion("CODE_INFO like", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoNotLike(String value) {
            addCriterion("CODE_INFO not like", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoIn(List<String> values) {
            addCriterion("CODE_INFO in", values, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoNotIn(List<String> values) {
            addCriterion("CODE_INFO not in", values, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoBetween(String value1, String value2) {
            addCriterion("CODE_INFO between", value1, value2, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoNotBetween(String value1, String value2) {
            addCriterion("CODE_INFO not between", value1, value2, "codeInfo");
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

        public Criteria andIsGradeReleasedIsNull() {
            addCriterion("IS_GRADE_RELEASED is null");
            return (Criteria) this;
        }

        public Criteria andIsGradeReleasedIsNotNull() {
            addCriterion("IS_GRADE_RELEASED is not null");
            return (Criteria) this;
        }

        public Criteria andIsGradeReleasedEqualTo(String value) {
            addCriterion("IS_GRADE_RELEASED =", value, "isGradeReleased");
            return (Criteria) this;
        }

        public Criteria andIsGradeReleasedNotEqualTo(String value) {
            addCriterion("IS_GRADE_RELEASED <>", value, "isGradeReleased");
            return (Criteria) this;
        }

        public Criteria andIsGradeReleasedGreaterThan(String value) {
            addCriterion("IS_GRADE_RELEASED >", value, "isGradeReleased");
            return (Criteria) this;
        }

        public Criteria andIsGradeReleasedGreaterThanOrEqualTo(String value) {
            addCriterion("IS_GRADE_RELEASED >=", value, "isGradeReleased");
            return (Criteria) this;
        }

        public Criteria andIsGradeReleasedLessThan(String value) {
            addCriterion("IS_GRADE_RELEASED <", value, "isGradeReleased");
            return (Criteria) this;
        }

        public Criteria andIsGradeReleasedLessThanOrEqualTo(String value) {
            addCriterion("IS_GRADE_RELEASED <=", value, "isGradeReleased");
            return (Criteria) this;
        }

        public Criteria andIsGradeReleasedLike(String value) {
            addCriterion("IS_GRADE_RELEASED like", value, "isGradeReleased");
            return (Criteria) this;
        }

        public Criteria andIsGradeReleasedNotLike(String value) {
            addCriterion("IS_GRADE_RELEASED not like", value, "isGradeReleased");
            return (Criteria) this;
        }

        public Criteria andIsGradeReleasedIn(List<String> values) {
            addCriterion("IS_GRADE_RELEASED in", values, "isGradeReleased");
            return (Criteria) this;
        }

        public Criteria andIsGradeReleasedNotIn(List<String> values) {
            addCriterion("IS_GRADE_RELEASED not in", values, "isGradeReleased");
            return (Criteria) this;
        }

        public Criteria andIsGradeReleasedBetween(String value1, String value2) {
            addCriterion("IS_GRADE_RELEASED between", value1, value2, "isGradeReleased");
            return (Criteria) this;
        }

        public Criteria andIsGradeReleasedNotBetween(String value1, String value2) {
            addCriterion("IS_GRADE_RELEASED not between", value1, value2, "isGradeReleased");
            return (Criteria) this;
        }

        public Criteria andIsShowFroomIsNull() {
            addCriterion("IS_SHOW_FROOM is null");
            return (Criteria) this;
        }

        public Criteria andIsShowFroomIsNotNull() {
            addCriterion("IS_SHOW_FROOM is not null");
            return (Criteria) this;
        }

        public Criteria andIsShowFroomEqualTo(String value) {
            addCriterion("IS_SHOW_FROOM =", value, "isShowFroom");
            return (Criteria) this;
        }

        public Criteria andIsShowFroomNotEqualTo(String value) {
            addCriterion("IS_SHOW_FROOM <>", value, "isShowFroom");
            return (Criteria) this;
        }

        public Criteria andIsShowFroomGreaterThan(String value) {
            addCriterion("IS_SHOW_FROOM >", value, "isShowFroom");
            return (Criteria) this;
        }

        public Criteria andIsShowFroomGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SHOW_FROOM >=", value, "isShowFroom");
            return (Criteria) this;
        }

        public Criteria andIsShowFroomLessThan(String value) {
            addCriterion("IS_SHOW_FROOM <", value, "isShowFroom");
            return (Criteria) this;
        }

        public Criteria andIsShowFroomLessThanOrEqualTo(String value) {
            addCriterion("IS_SHOW_FROOM <=", value, "isShowFroom");
            return (Criteria) this;
        }

        public Criteria andIsShowFroomLike(String value) {
            addCriterion("IS_SHOW_FROOM like", value, "isShowFroom");
            return (Criteria) this;
        }

        public Criteria andIsShowFroomNotLike(String value) {
            addCriterion("IS_SHOW_FROOM not like", value, "isShowFroom");
            return (Criteria) this;
        }

        public Criteria andIsShowFroomIn(List<String> values) {
            addCriterion("IS_SHOW_FROOM in", values, "isShowFroom");
            return (Criteria) this;
        }

        public Criteria andIsShowFroomNotIn(List<String> values) {
            addCriterion("IS_SHOW_FROOM not in", values, "isShowFroom");
            return (Criteria) this;
        }

        public Criteria andIsShowFroomBetween(String value1, String value2) {
            addCriterion("IS_SHOW_FROOM between", value1, value2, "isShowFroom");
            return (Criteria) this;
        }

        public Criteria andIsShowFroomNotBetween(String value1, String value2) {
            addCriterion("IS_SHOW_FROOM not between", value1, value2, "isShowFroom");
            return (Criteria) this;
        }

        public Criteria andSkillOrderIsNull() {
            addCriterion("SKILL_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andSkillOrderIsNotNull() {
            addCriterion("SKILL_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andSkillOrderEqualTo(String value) {
            addCriterion("SKILL_ORDER =", value, "skillOrder");
            return (Criteria) this;
        }

        public Criteria andSkillOrderNotEqualTo(String value) {
            addCriterion("SKILL_ORDER <>", value, "skillOrder");
            return (Criteria) this;
        }

        public Criteria andSkillOrderGreaterThan(String value) {
            addCriterion("SKILL_ORDER >", value, "skillOrder");
            return (Criteria) this;
        }

        public Criteria andSkillOrderGreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_ORDER >=", value, "skillOrder");
            return (Criteria) this;
        }

        public Criteria andSkillOrderLessThan(String value) {
            addCriterion("SKILL_ORDER <", value, "skillOrder");
            return (Criteria) this;
        }

        public Criteria andSkillOrderLessThanOrEqualTo(String value) {
            addCriterion("SKILL_ORDER <=", value, "skillOrder");
            return (Criteria) this;
        }

        public Criteria andSkillOrderLike(String value) {
            addCriterion("SKILL_ORDER like", value, "skillOrder");
            return (Criteria) this;
        }

        public Criteria andSkillOrderNotLike(String value) {
            addCriterion("SKILL_ORDER not like", value, "skillOrder");
            return (Criteria) this;
        }

        public Criteria andSkillOrderIn(List<String> values) {
            addCriterion("SKILL_ORDER in", values, "skillOrder");
            return (Criteria) this;
        }

        public Criteria andSkillOrderNotIn(List<String> values) {
            addCriterion("SKILL_ORDER not in", values, "skillOrder");
            return (Criteria) this;
        }

        public Criteria andSkillOrderBetween(String value1, String value2) {
            addCriterion("SKILL_ORDER between", value1, value2, "skillOrder");
            return (Criteria) this;
        }

        public Criteria andSkillOrderNotBetween(String value1, String value2) {
            addCriterion("SKILL_ORDER not between", value1, value2, "skillOrder");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("REMARKS is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("REMARKS is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("REMARKS =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("REMARKS <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("REMARKS >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("REMARKS >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("REMARKS <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("REMARKS <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("REMARKS like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("REMARKS not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("REMARKS in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("REMARKS not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("REMARKS between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("REMARKS not between", value1, value2, "remarks");
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