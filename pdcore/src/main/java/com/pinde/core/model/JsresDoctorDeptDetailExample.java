package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class JsresDoctorDeptDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JsresDoctorDeptDetailExample() {
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

        public Criteria andRecruitFlowIsNull() {
            addCriterion("RECRUIT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowIsNotNull() {
            addCriterion("RECRUIT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowEqualTo(String value) {
            addCriterion("RECRUIT_FLOW =", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotEqualTo(String value) {
            addCriterion("RECRUIT_FLOW <>", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowGreaterThan(String value) {
            addCriterion("RECRUIT_FLOW >", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_FLOW >=", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLessThan(String value) {
            addCriterion("RECRUIT_FLOW <", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_FLOW <=", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLike(String value) {
            addCriterion("RECRUIT_FLOW like", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotLike(String value) {
            addCriterion("RECRUIT_FLOW not like", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowIn(List<String> values) {
            addCriterion("RECRUIT_FLOW in", values, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotIn(List<String> values) {
            addCriterion("RECRUIT_FLOW not in", values, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowBetween(String value1, String value2) {
            addCriterion("RECRUIT_FLOW between", value1, value2, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_FLOW not between", value1, value2, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowIsNull() {
            addCriterion("DOCTOR_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowIsNotNull() {
            addCriterion("DOCTOR_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowEqualTo(String value) {
            addCriterion("DOCTOR_FLOW =", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowNotEqualTo(String value) {
            addCriterion("DOCTOR_FLOW <>", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowGreaterThan(String value) {
            addCriterion("DOCTOR_FLOW >", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_FLOW >=", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowLessThan(String value) {
            addCriterion("DOCTOR_FLOW <", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_FLOW <=", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowLike(String value) {
            addCriterion("DOCTOR_FLOW like", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowNotLike(String value) {
            addCriterion("DOCTOR_FLOW not like", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowIn(List<String> values) {
            addCriterion("DOCTOR_FLOW in", values, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowNotIn(List<String> values) {
            addCriterion("DOCTOR_FLOW not in", values, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowBetween(String value1, String value2) {
            addCriterion("DOCTOR_FLOW between", value1, value2, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_FLOW not between", value1, value2, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdIsNull() {
            addCriterion("CAT_SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdIsNotNull() {
            addCriterion("CAT_SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdEqualTo(String value) {
            addCriterion("CAT_SPE_ID =", value, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdNotEqualTo(String value) {
            addCriterion("CAT_SPE_ID <>", value, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdGreaterThan(String value) {
            addCriterion("CAT_SPE_ID >", value, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("CAT_SPE_ID >=", value, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdLessThan(String value) {
            addCriterion("CAT_SPE_ID <", value, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdLessThanOrEqualTo(String value) {
            addCriterion("CAT_SPE_ID <=", value, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdLike(String value) {
            addCriterion("CAT_SPE_ID like", value, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdNotLike(String value) {
            addCriterion("CAT_SPE_ID not like", value, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdIn(List<String> values) {
            addCriterion("CAT_SPE_ID in", values, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdNotIn(List<String> values) {
            addCriterion("CAT_SPE_ID not in", values, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdBetween(String value1, String value2) {
            addCriterion("CAT_SPE_ID between", value1, value2, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeIdNotBetween(String value1, String value2) {
            addCriterion("CAT_SPE_ID not between", value1, value2, "catSpeId");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameIsNull() {
            addCriterion("CAT_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameIsNotNull() {
            addCriterion("CAT_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameEqualTo(String value) {
            addCriterion("CAT_SPE_NAME =", value, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameNotEqualTo(String value) {
            addCriterion("CAT_SPE_NAME <>", value, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameGreaterThan(String value) {
            addCriterion("CAT_SPE_NAME >", value, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("CAT_SPE_NAME >=", value, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameLessThan(String value) {
            addCriterion("CAT_SPE_NAME <", value, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameLessThanOrEqualTo(String value) {
            addCriterion("CAT_SPE_NAME <=", value, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameLike(String value) {
            addCriterion("CAT_SPE_NAME like", value, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameNotLike(String value) {
            addCriterion("CAT_SPE_NAME not like", value, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameIn(List<String> values) {
            addCriterion("CAT_SPE_NAME in", values, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameNotIn(List<String> values) {
            addCriterion("CAT_SPE_NAME not in", values, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameBetween(String value1, String value2) {
            addCriterion("CAT_SPE_NAME between", value1, value2, "catSpeName");
            return (Criteria) this;
        }

        public Criteria andCatSpeNameNotBetween(String value1, String value2) {
            addCriterion("CAT_SPE_NAME not between", value1, value2, "catSpeName");
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

        public Criteria andTrainYearIsNull() {
            addCriterion("TRAIN_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andTrainYearIsNotNull() {
            addCriterion("TRAIN_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andTrainYearEqualTo(String value) {
            addCriterion("TRAIN_YEAR =", value, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearNotEqualTo(String value) {
            addCriterion("TRAIN_YEAR <>", value, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearGreaterThan(String value) {
            addCriterion("TRAIN_YEAR >", value, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_YEAR >=", value, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearLessThan(String value) {
            addCriterion("TRAIN_YEAR <", value, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_YEAR <=", value, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearLike(String value) {
            addCriterion("TRAIN_YEAR like", value, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearNotLike(String value) {
            addCriterion("TRAIN_YEAR not like", value, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearIn(List<String> values) {
            addCriterion("TRAIN_YEAR in", values, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearNotIn(List<String> values) {
            addCriterion("TRAIN_YEAR not in", values, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearBetween(String value1, String value2) {
            addCriterion("TRAIN_YEAR between", value1, value2, "trainYear");
            return (Criteria) this;
        }

        public Criteria andTrainYearNotBetween(String value1, String value2) {
            addCriterion("TRAIN_YEAR not between", value1, value2, "trainYear");
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

        public Criteria andRotationFlowIsNull() {
            addCriterion("ROTATION_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRotationFlowIsNotNull() {
            addCriterion("ROTATION_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRotationFlowEqualTo(String value) {
            addCriterion("ROTATION_FLOW =", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotEqualTo(String value) {
            addCriterion("ROTATION_FLOW <>", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowGreaterThan(String value) {
            addCriterion("ROTATION_FLOW >", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ROTATION_FLOW >=", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowLessThan(String value) {
            addCriterion("ROTATION_FLOW <", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowLessThanOrEqualTo(String value) {
            addCriterion("ROTATION_FLOW <=", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowLike(String value) {
            addCriterion("ROTATION_FLOW like", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotLike(String value) {
            addCriterion("ROTATION_FLOW not like", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowIn(List<String> values) {
            addCriterion("ROTATION_FLOW in", values, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotIn(List<String> values) {
            addCriterion("ROTATION_FLOW not in", values, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowBetween(String value1, String value2) {
            addCriterion("ROTATION_FLOW between", value1, value2, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotBetween(String value1, String value2) {
            addCriterion("ROTATION_FLOW not between", value1, value2, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andSchStandardDeptFlowIsNull() {
            addCriterion("SCH_STANDARD_DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSchStandardDeptFlowIsNotNull() {
            addCriterion("SCH_STANDARD_DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSchStandardDeptFlowEqualTo(String value) {
            addCriterion("SCH_STANDARD_DEPT_FLOW =", value, "schStandardDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchStandardDeptFlowNotEqualTo(String value) {
            addCriterion("SCH_STANDARD_DEPT_FLOW <>", value, "schStandardDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchStandardDeptFlowGreaterThan(String value) {
            addCriterion("SCH_STANDARD_DEPT_FLOW >", value, "schStandardDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchStandardDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_STANDARD_DEPT_FLOW >=", value, "schStandardDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchStandardDeptFlowLessThan(String value) {
            addCriterion("SCH_STANDARD_DEPT_FLOW <", value, "schStandardDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchStandardDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("SCH_STANDARD_DEPT_FLOW <=", value, "schStandardDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchStandardDeptFlowLike(String value) {
            addCriterion("SCH_STANDARD_DEPT_FLOW like", value, "schStandardDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchStandardDeptFlowNotLike(String value) {
            addCriterion("SCH_STANDARD_DEPT_FLOW not like", value, "schStandardDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchStandardDeptFlowIn(List<String> values) {
            addCriterion("SCH_STANDARD_DEPT_FLOW in", values, "schStandardDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchStandardDeptFlowNotIn(List<String> values) {
            addCriterion("SCH_STANDARD_DEPT_FLOW not in", values, "schStandardDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchStandardDeptFlowBetween(String value1, String value2) {
            addCriterion("SCH_STANDARD_DEPT_FLOW between", value1, value2, "schStandardDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchStandardDeptFlowNotBetween(String value1, String value2) {
            addCriterion("SCH_STANDARD_DEPT_FLOW not between", value1, value2, "schStandardDeptFlow");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdIsNull() {
            addCriterion("STANDARD_DEPT_ID is null");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdIsNotNull() {
            addCriterion("STANDARD_DEPT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdEqualTo(String value) {
            addCriterion("STANDARD_DEPT_ID =", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdNotEqualTo(String value) {
            addCriterion("STANDARD_DEPT_ID <>", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdGreaterThan(String value) {
            addCriterion("STANDARD_DEPT_ID >", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdGreaterThanOrEqualTo(String value) {
            addCriterion("STANDARD_DEPT_ID >=", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdLessThan(String value) {
            addCriterion("STANDARD_DEPT_ID <", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdLessThanOrEqualTo(String value) {
            addCriterion("STANDARD_DEPT_ID <=", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdLike(String value) {
            addCriterion("STANDARD_DEPT_ID like", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdNotLike(String value) {
            addCriterion("STANDARD_DEPT_ID not like", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdIn(List<String> values) {
            addCriterion("STANDARD_DEPT_ID in", values, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdNotIn(List<String> values) {
            addCriterion("STANDARD_DEPT_ID not in", values, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdBetween(String value1, String value2) {
            addCriterion("STANDARD_DEPT_ID between", value1, value2, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdNotBetween(String value1, String value2) {
            addCriterion("STANDARD_DEPT_ID not between", value1, value2, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameIsNull() {
            addCriterion("STANDARD_DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameIsNotNull() {
            addCriterion("STANDARD_DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameEqualTo(String value) {
            addCriterion("STANDARD_DEPT_NAME =", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameNotEqualTo(String value) {
            addCriterion("STANDARD_DEPT_NAME <>", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameGreaterThan(String value) {
            addCriterion("STANDARD_DEPT_NAME >", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("STANDARD_DEPT_NAME >=", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameLessThan(String value) {
            addCriterion("STANDARD_DEPT_NAME <", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameLessThanOrEqualTo(String value) {
            addCriterion("STANDARD_DEPT_NAME <=", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameLike(String value) {
            addCriterion("STANDARD_DEPT_NAME like", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameNotLike(String value) {
            addCriterion("STANDARD_DEPT_NAME not like", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameIn(List<String> values) {
            addCriterion("STANDARD_DEPT_NAME in", values, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameNotIn(List<String> values) {
            addCriterion("STANDARD_DEPT_NAME not in", values, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameBetween(String value1, String value2) {
            addCriterion("STANDARD_DEPT_NAME between", value1, value2, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameNotBetween(String value1, String value2) {
            addCriterion("STANDARD_DEPT_NAME not between", value1, value2, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andSchMonthIsNull() {
            addCriterion("SCH_MONTH is null");
            return (Criteria) this;
        }

        public Criteria andSchMonthIsNotNull() {
            addCriterion("SCH_MONTH is not null");
            return (Criteria) this;
        }

        public Criteria andSchMonthEqualTo(String value) {
            addCriterion("SCH_MONTH =", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthNotEqualTo(String value) {
            addCriterion("SCH_MONTH <>", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthGreaterThan(String value) {
            addCriterion("SCH_MONTH >", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_MONTH >=", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthLessThan(String value) {
            addCriterion("SCH_MONTH <", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthLessThanOrEqualTo(String value) {
            addCriterion("SCH_MONTH <=", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthLike(String value) {
            addCriterion("SCH_MONTH like", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthNotLike(String value) {
            addCriterion("SCH_MONTH not like", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthIn(List<String> values) {
            addCriterion("SCH_MONTH in", values, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthNotIn(List<String> values) {
            addCriterion("SCH_MONTH not in", values, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthBetween(String value1, String value2) {
            addCriterion("SCH_MONTH between", value1, value2, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthNotBetween(String value1, String value2) {
            addCriterion("SCH_MONTH not between", value1, value2, "schMonth");
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

        public Criteria andCompleteNumIsNull() {
            addCriterion("COMPLETE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andCompleteNumIsNotNull() {
            addCriterion("COMPLETE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteNumEqualTo(String value) {
            addCriterion("COMPLETE_NUM =", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumNotEqualTo(String value) {
            addCriterion("COMPLETE_NUM <>", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumGreaterThan(String value) {
            addCriterion("COMPLETE_NUM >", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLETE_NUM >=", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumLessThan(String value) {
            addCriterion("COMPLETE_NUM <", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumLessThanOrEqualTo(String value) {
            addCriterion("COMPLETE_NUM <=", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumLike(String value) {
            addCriterion("COMPLETE_NUM like", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumNotLike(String value) {
            addCriterion("COMPLETE_NUM not like", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumIn(List<String> values) {
            addCriterion("COMPLETE_NUM in", values, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumNotIn(List<String> values) {
            addCriterion("COMPLETE_NUM not in", values, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumBetween(String value1, String value2) {
            addCriterion("COMPLETE_NUM between", value1, value2, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumNotBetween(String value1, String value2) {
            addCriterion("COMPLETE_NUM not between", value1, value2, "completeNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumIsNull() {
            addCriterion("AUDIT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andAuditNumIsNotNull() {
            addCriterion("AUDIT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andAuditNumEqualTo(String value) {
            addCriterion("AUDIT_NUM =", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumNotEqualTo(String value) {
            addCriterion("AUDIT_NUM <>", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumGreaterThan(String value) {
            addCriterion("AUDIT_NUM >", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_NUM >=", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumLessThan(String value) {
            addCriterion("AUDIT_NUM <", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_NUM <=", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumLike(String value) {
            addCriterion("AUDIT_NUM like", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumNotLike(String value) {
            addCriterion("AUDIT_NUM not like", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumIn(List<String> values) {
            addCriterion("AUDIT_NUM in", values, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumNotIn(List<String> values) {
            addCriterion("AUDIT_NUM not in", values, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumBetween(String value1, String value2) {
            addCriterion("AUDIT_NUM between", value1, value2, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumNotBetween(String value1, String value2) {
            addCriterion("AUDIT_NUM not between", value1, value2, "auditNum");
            return (Criteria) this;
        }

        public Criteria andReqNumIsNull() {
            addCriterion("REQ_NUM is null");
            return (Criteria) this;
        }

        public Criteria andReqNumIsNotNull() {
            addCriterion("REQ_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andReqNumEqualTo(String value) {
            addCriterion("REQ_NUM =", value, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumNotEqualTo(String value) {
            addCriterion("REQ_NUM <>", value, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumGreaterThan(String value) {
            addCriterion("REQ_NUM >", value, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumGreaterThanOrEqualTo(String value) {
            addCriterion("REQ_NUM >=", value, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumLessThan(String value) {
            addCriterion("REQ_NUM <", value, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumLessThanOrEqualTo(String value) {
            addCriterion("REQ_NUM <=", value, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumLike(String value) {
            addCriterion("REQ_NUM like", value, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumNotLike(String value) {
            addCriterion("REQ_NUM not like", value, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumIn(List<String> values) {
            addCriterion("REQ_NUM in", values, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumNotIn(List<String> values) {
            addCriterion("REQ_NUM not in", values, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumBetween(String value1, String value2) {
            addCriterion("REQ_NUM between", value1, value2, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumNotBetween(String value1, String value2) {
            addCriterion("REQ_NUM not between", value1, value2, "reqNum");
            return (Criteria) this;
        }

        public Criteria andOldReqNumIsNull() {
            addCriterion("OLD_REQ_NUM is null");
            return (Criteria) this;
        }

        public Criteria andOldReqNumIsNotNull() {
            addCriterion("OLD_REQ_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andOldReqNumEqualTo(String value) {
            addCriterion("OLD_REQ_NUM =", value, "oldReqNum");
            return (Criteria) this;
        }

        public Criteria andOldReqNumNotEqualTo(String value) {
            addCriterion("OLD_REQ_NUM <>", value, "oldReqNum");
            return (Criteria) this;
        }

        public Criteria andOldReqNumGreaterThan(String value) {
            addCriterion("OLD_REQ_NUM >", value, "oldReqNum");
            return (Criteria) this;
        }

        public Criteria andOldReqNumGreaterThanOrEqualTo(String value) {
            addCriterion("OLD_REQ_NUM >=", value, "oldReqNum");
            return (Criteria) this;
        }

        public Criteria andOldReqNumLessThan(String value) {
            addCriterion("OLD_REQ_NUM <", value, "oldReqNum");
            return (Criteria) this;
        }

        public Criteria andOldReqNumLessThanOrEqualTo(String value) {
            addCriterion("OLD_REQ_NUM <=", value, "oldReqNum");
            return (Criteria) this;
        }

        public Criteria andOldReqNumLike(String value) {
            addCriterion("OLD_REQ_NUM like", value, "oldReqNum");
            return (Criteria) this;
        }

        public Criteria andOldReqNumNotLike(String value) {
            addCriterion("OLD_REQ_NUM not like", value, "oldReqNum");
            return (Criteria) this;
        }

        public Criteria andOldReqNumIn(List<String> values) {
            addCriterion("OLD_REQ_NUM in", values, "oldReqNum");
            return (Criteria) this;
        }

        public Criteria andOldReqNumNotIn(List<String> values) {
            addCriterion("OLD_REQ_NUM not in", values, "oldReqNum");
            return (Criteria) this;
        }

        public Criteria andOldReqNumBetween(String value1, String value2) {
            addCriterion("OLD_REQ_NUM between", value1, value2, "oldReqNum");
            return (Criteria) this;
        }

        public Criteria andOldReqNumNotBetween(String value1, String value2) {
            addCriterion("OLD_REQ_NUM not between", value1, value2, "oldReqNum");
            return (Criteria) this;
        }

        public Criteria andCompleteBiIsNull() {
            addCriterion("COMPLETE_BI is null");
            return (Criteria) this;
        }

        public Criteria andCompleteBiIsNotNull() {
            addCriterion("COMPLETE_BI is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteBiEqualTo(String value) {
            addCriterion("COMPLETE_BI =", value, "completeBi");
            return (Criteria) this;
        }

        public Criteria andCompleteBiNotEqualTo(String value) {
            addCriterion("COMPLETE_BI <>", value, "completeBi");
            return (Criteria) this;
        }

        public Criteria andCompleteBiGreaterThan(String value) {
            addCriterion("COMPLETE_BI >", value, "completeBi");
            return (Criteria) this;
        }

        public Criteria andCompleteBiGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLETE_BI >=", value, "completeBi");
            return (Criteria) this;
        }

        public Criteria andCompleteBiLessThan(String value) {
            addCriterion("COMPLETE_BI <", value, "completeBi");
            return (Criteria) this;
        }

        public Criteria andCompleteBiLessThanOrEqualTo(String value) {
            addCriterion("COMPLETE_BI <=", value, "completeBi");
            return (Criteria) this;
        }

        public Criteria andCompleteBiLike(String value) {
            addCriterion("COMPLETE_BI like", value, "completeBi");
            return (Criteria) this;
        }

        public Criteria andCompleteBiNotLike(String value) {
            addCriterion("COMPLETE_BI not like", value, "completeBi");
            return (Criteria) this;
        }

        public Criteria andCompleteBiIn(List<String> values) {
            addCriterion("COMPLETE_BI in", values, "completeBi");
            return (Criteria) this;
        }

        public Criteria andCompleteBiNotIn(List<String> values) {
            addCriterion("COMPLETE_BI not in", values, "completeBi");
            return (Criteria) this;
        }

        public Criteria andCompleteBiBetween(String value1, String value2) {
            addCriterion("COMPLETE_BI between", value1, value2, "completeBi");
            return (Criteria) this;
        }

        public Criteria andCompleteBiNotBetween(String value1, String value2) {
            addCriterion("COMPLETE_BI not between", value1, value2, "completeBi");
            return (Criteria) this;
        }

        public Criteria andAuditBiIsNull() {
            addCriterion("AUDIT_BI is null");
            return (Criteria) this;
        }

        public Criteria andAuditBiIsNotNull() {
            addCriterion("AUDIT_BI is not null");
            return (Criteria) this;
        }

        public Criteria andAuditBiEqualTo(String value) {
            addCriterion("AUDIT_BI =", value, "auditBi");
            return (Criteria) this;
        }

        public Criteria andAuditBiNotEqualTo(String value) {
            addCriterion("AUDIT_BI <>", value, "auditBi");
            return (Criteria) this;
        }

        public Criteria andAuditBiGreaterThan(String value) {
            addCriterion("AUDIT_BI >", value, "auditBi");
            return (Criteria) this;
        }

        public Criteria andAuditBiGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_BI >=", value, "auditBi");
            return (Criteria) this;
        }

        public Criteria andAuditBiLessThan(String value) {
            addCriterion("AUDIT_BI <", value, "auditBi");
            return (Criteria) this;
        }

        public Criteria andAuditBiLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_BI <=", value, "auditBi");
            return (Criteria) this;
        }

        public Criteria andAuditBiLike(String value) {
            addCriterion("AUDIT_BI like", value, "auditBi");
            return (Criteria) this;
        }

        public Criteria andAuditBiNotLike(String value) {
            addCriterion("AUDIT_BI not like", value, "auditBi");
            return (Criteria) this;
        }

        public Criteria andAuditBiIn(List<String> values) {
            addCriterion("AUDIT_BI in", values, "auditBi");
            return (Criteria) this;
        }

        public Criteria andAuditBiNotIn(List<String> values) {
            addCriterion("AUDIT_BI not in", values, "auditBi");
            return (Criteria) this;
        }

        public Criteria andAuditBiBetween(String value1, String value2) {
            addCriterion("AUDIT_BI between", value1, value2, "auditBi");
            return (Criteria) this;
        }

        public Criteria andAuditBiNotBetween(String value1, String value2) {
            addCriterion("AUDIT_BI not between", value1, value2, "auditBi");
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

        public Criteria andGroupFlowIsNull() {
            addCriterion("GROUP_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andGroupFlowIsNotNull() {
            addCriterion("GROUP_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andGroupFlowEqualTo(String value) {
            addCriterion("GROUP_FLOW =", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotEqualTo(String value) {
            addCriterion("GROUP_FLOW <>", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowGreaterThan(String value) {
            addCriterion("GROUP_FLOW >", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_FLOW >=", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowLessThan(String value) {
            addCriterion("GROUP_FLOW <", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowLessThanOrEqualTo(String value) {
            addCriterion("GROUP_FLOW <=", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowLike(String value) {
            addCriterion("GROUP_FLOW like", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotLike(String value) {
            addCriterion("GROUP_FLOW not like", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowIn(List<String> values) {
            addCriterion("GROUP_FLOW in", values, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotIn(List<String> values) {
            addCriterion("GROUP_FLOW not in", values, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowBetween(String value1, String value2) {
            addCriterion("GROUP_FLOW between", value1, value2, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotBetween(String value1, String value2) {
            addCriterion("GROUP_FLOW not between", value1, value2, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andIsShortIsNull() {
            addCriterion("IS_SHORT is null");
            return (Criteria) this;
        }

        public Criteria andIsShortIsNotNull() {
            addCriterion("IS_SHORT is not null");
            return (Criteria) this;
        }

        public Criteria andIsShortEqualTo(String value) {
            addCriterion("IS_SHORT =", value, "isShort");
            return (Criteria) this;
        }

        public Criteria andIsShortNotEqualTo(String value) {
            addCriterion("IS_SHORT <>", value, "isShort");
            return (Criteria) this;
        }

        public Criteria andIsShortGreaterThan(String value) {
            addCriterion("IS_SHORT >", value, "isShort");
            return (Criteria) this;
        }

        public Criteria andIsShortGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SHORT >=", value, "isShort");
            return (Criteria) this;
        }

        public Criteria andIsShortLessThan(String value) {
            addCriterion("IS_SHORT <", value, "isShort");
            return (Criteria) this;
        }

        public Criteria andIsShortLessThanOrEqualTo(String value) {
            addCriterion("IS_SHORT <=", value, "isShort");
            return (Criteria) this;
        }

        public Criteria andIsShortLike(String value) {
            addCriterion("IS_SHORT like", value, "isShort");
            return (Criteria) this;
        }

        public Criteria andIsShortNotLike(String value) {
            addCriterion("IS_SHORT not like", value, "isShort");
            return (Criteria) this;
        }

        public Criteria andIsShortIn(List<String> values) {
            addCriterion("IS_SHORT in", values, "isShort");
            return (Criteria) this;
        }

        public Criteria andIsShortNotIn(List<String> values) {
            addCriterion("IS_SHORT not in", values, "isShort");
            return (Criteria) this;
        }

        public Criteria andIsShortBetween(String value1, String value2) {
            addCriterion("IS_SHORT between", value1, value2, "isShort");
            return (Criteria) this;
        }

        public Criteria andIsShortNotBetween(String value1, String value2) {
            addCriterion("IS_SHORT not between", value1, value2, "isShort");
            return (Criteria) this;
        }

        public Criteria andOutCompleteNumIsNull() {
            addCriterion("OUT_COMPLETE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andOutCompleteNumIsNotNull() {
            addCriterion("OUT_COMPLETE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andOutCompleteNumEqualTo(String value) {
            addCriterion("OUT_COMPLETE_NUM =", value, "outCompleteNum");
            return (Criteria) this;
        }

        public Criteria andOutCompleteNumNotEqualTo(String value) {
            addCriterion("OUT_COMPLETE_NUM <>", value, "outCompleteNum");
            return (Criteria) this;
        }

        public Criteria andOutCompleteNumGreaterThan(String value) {
            addCriterion("OUT_COMPLETE_NUM >", value, "outCompleteNum");
            return (Criteria) this;
        }

        public Criteria andOutCompleteNumGreaterThanOrEqualTo(String value) {
            addCriterion("OUT_COMPLETE_NUM >=", value, "outCompleteNum");
            return (Criteria) this;
        }

        public Criteria andOutCompleteNumLessThan(String value) {
            addCriterion("OUT_COMPLETE_NUM <", value, "outCompleteNum");
            return (Criteria) this;
        }

        public Criteria andOutCompleteNumLessThanOrEqualTo(String value) {
            addCriterion("OUT_COMPLETE_NUM <=", value, "outCompleteNum");
            return (Criteria) this;
        }

        public Criteria andOutCompleteNumLike(String value) {
            addCriterion("OUT_COMPLETE_NUM like", value, "outCompleteNum");
            return (Criteria) this;
        }

        public Criteria andOutCompleteNumNotLike(String value) {
            addCriterion("OUT_COMPLETE_NUM not like", value, "outCompleteNum");
            return (Criteria) this;
        }

        public Criteria andOutCompleteNumIn(List<String> values) {
            addCriterion("OUT_COMPLETE_NUM in", values, "outCompleteNum");
            return (Criteria) this;
        }

        public Criteria andOutCompleteNumNotIn(List<String> values) {
            addCriterion("OUT_COMPLETE_NUM not in", values, "outCompleteNum");
            return (Criteria) this;
        }

        public Criteria andOutCompleteNumBetween(String value1, String value2) {
            addCriterion("OUT_COMPLETE_NUM between", value1, value2, "outCompleteNum");
            return (Criteria) this;
        }

        public Criteria andOutCompleteNumNotBetween(String value1, String value2) {
            addCriterion("OUT_COMPLETE_NUM not between", value1, value2, "outCompleteNum");
            return (Criteria) this;
        }

        public Criteria andInCompleteNumIsNull() {
            addCriterion("IN_COMPLETE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andInCompleteNumIsNotNull() {
            addCriterion("IN_COMPLETE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andInCompleteNumEqualTo(String value) {
            addCriterion("IN_COMPLETE_NUM =", value, "inCompleteNum");
            return (Criteria) this;
        }

        public Criteria andInCompleteNumNotEqualTo(String value) {
            addCriterion("IN_COMPLETE_NUM <>", value, "inCompleteNum");
            return (Criteria) this;
        }

        public Criteria andInCompleteNumGreaterThan(String value) {
            addCriterion("IN_COMPLETE_NUM >", value, "inCompleteNum");
            return (Criteria) this;
        }

        public Criteria andInCompleteNumGreaterThanOrEqualTo(String value) {
            addCriterion("IN_COMPLETE_NUM >=", value, "inCompleteNum");
            return (Criteria) this;
        }

        public Criteria andInCompleteNumLessThan(String value) {
            addCriterion("IN_COMPLETE_NUM <", value, "inCompleteNum");
            return (Criteria) this;
        }

        public Criteria andInCompleteNumLessThanOrEqualTo(String value) {
            addCriterion("IN_COMPLETE_NUM <=", value, "inCompleteNum");
            return (Criteria) this;
        }

        public Criteria andInCompleteNumLike(String value) {
            addCriterion("IN_COMPLETE_NUM like", value, "inCompleteNum");
            return (Criteria) this;
        }

        public Criteria andInCompleteNumNotLike(String value) {
            addCriterion("IN_COMPLETE_NUM not like", value, "inCompleteNum");
            return (Criteria) this;
        }

        public Criteria andInCompleteNumIn(List<String> values) {
            addCriterion("IN_COMPLETE_NUM in", values, "inCompleteNum");
            return (Criteria) this;
        }

        public Criteria andInCompleteNumNotIn(List<String> values) {
            addCriterion("IN_COMPLETE_NUM not in", values, "inCompleteNum");
            return (Criteria) this;
        }

        public Criteria andInCompleteNumBetween(String value1, String value2) {
            addCriterion("IN_COMPLETE_NUM between", value1, value2, "inCompleteNum");
            return (Criteria) this;
        }

        public Criteria andInCompleteNumNotBetween(String value1, String value2) {
            addCriterion("IN_COMPLETE_NUM not between", value1, value2, "inCompleteNum");
            return (Criteria) this;
        }

        public Criteria andApplyYearIsNull() {
            addCriterion("APPLY_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andApplyYearIsNotNull() {
            addCriterion("APPLY_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andApplyYearEqualTo(String value) {
            addCriterion("APPLY_YEAR =", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearNotEqualTo(String value) {
            addCriterion("APPLY_YEAR <>", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearGreaterThan(String value) {
            addCriterion("APPLY_YEAR >", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_YEAR >=", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearLessThan(String value) {
            addCriterion("APPLY_YEAR <", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearLessThanOrEqualTo(String value) {
            addCriterion("APPLY_YEAR <=", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearLike(String value) {
            addCriterion("APPLY_YEAR like", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearNotLike(String value) {
            addCriterion("APPLY_YEAR not like", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearIn(List<String> values) {
            addCriterion("APPLY_YEAR in", values, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearNotIn(List<String> values) {
            addCriterion("APPLY_YEAR not in", values, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearBetween(String value1, String value2) {
            addCriterion("APPLY_YEAR between", value1, value2, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearNotBetween(String value1, String value2) {
            addCriterion("APPLY_YEAR not between", value1, value2, "applyYear");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiIsNull() {
            addCriterion("OUT_COMPLETE_BI is null");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiIsNotNull() {
            addCriterion("OUT_COMPLETE_BI is not null");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiEqualTo(String value) {
            addCriterion("OUT_COMPLETE_BI =", value, "outCompleteBi");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiNotEqualTo(String value) {
            addCriterion("OUT_COMPLETE_BI <>", value, "outCompleteBi");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiGreaterThan(String value) {
            addCriterion("OUT_COMPLETE_BI >", value, "outCompleteBi");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiGreaterThanOrEqualTo(String value) {
            addCriterion("OUT_COMPLETE_BI >=", value, "outCompleteBi");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiLessThan(String value) {
            addCriterion("OUT_COMPLETE_BI <", value, "outCompleteBi");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiLessThanOrEqualTo(String value) {
            addCriterion("OUT_COMPLETE_BI <=", value, "outCompleteBi");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiLike(String value) {
            addCriterion("OUT_COMPLETE_BI like", value, "outCompleteBi");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiNotLike(String value) {
            addCriterion("OUT_COMPLETE_BI not like", value, "outCompleteBi");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiIn(List<String> values) {
            addCriterion("OUT_COMPLETE_BI in", values, "outCompleteBi");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiNotIn(List<String> values) {
            addCriterion("OUT_COMPLETE_BI not in", values, "outCompleteBi");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiBetween(String value1, String value2) {
            addCriterion("OUT_COMPLETE_BI between", value1, value2, "outCompleteBi");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiNotBetween(String value1, String value2) {
            addCriterion("OUT_COMPLETE_BI not between", value1, value2, "outCompleteBi");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiIsNull() {
            addCriterion("IN_COMPLETE_BI is null");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiIsNotNull() {
            addCriterion("IN_COMPLETE_BI is not null");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiEqualTo(String value) {
            addCriterion("IN_COMPLETE_BI =", value, "inCompleteBi");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiNotEqualTo(String value) {
            addCriterion("IN_COMPLETE_BI <>", value, "inCompleteBi");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiGreaterThan(String value) {
            addCriterion("IN_COMPLETE_BI >", value, "inCompleteBi");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiGreaterThanOrEqualTo(String value) {
            addCriterion("IN_COMPLETE_BI >=", value, "inCompleteBi");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiLessThan(String value) {
            addCriterion("IN_COMPLETE_BI <", value, "inCompleteBi");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiLessThanOrEqualTo(String value) {
            addCriterion("IN_COMPLETE_BI <=", value, "inCompleteBi");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiLike(String value) {
            addCriterion("IN_COMPLETE_BI like", value, "inCompleteBi");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiNotLike(String value) {
            addCriterion("IN_COMPLETE_BI not like", value, "inCompleteBi");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiIn(List<String> values) {
            addCriterion("IN_COMPLETE_BI in", values, "inCompleteBi");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiNotIn(List<String> values) {
            addCriterion("IN_COMPLETE_BI not in", values, "inCompleteBi");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiBetween(String value1, String value2) {
            addCriterion("IN_COMPLETE_BI between", value1, value2, "inCompleteBi");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiNotBetween(String value1, String value2) {
            addCriterion("IN_COMPLETE_BI not between", value1, value2, "inCompleteBi");
            return (Criteria) this;
        }

        public Criteria andIsAddIsNull() {
            addCriterion("IS_ADD is null");
            return (Criteria) this;
        }

        public Criteria andIsAddIsNotNull() {
            addCriterion("IS_ADD is not null");
            return (Criteria) this;
        }

        public Criteria andIsAddEqualTo(String value) {
            addCriterion("IS_ADD =", value, "isAdd");
            return (Criteria) this;
        }

        public Criteria andIsAddNotEqualTo(String value) {
            addCriterion("IS_ADD <>", value, "isAdd");
            return (Criteria) this;
        }

        public Criteria andIsAddGreaterThan(String value) {
            addCriterion("IS_ADD >", value, "isAdd");
            return (Criteria) this;
        }

        public Criteria andIsAddGreaterThanOrEqualTo(String value) {
            addCriterion("IS_ADD >=", value, "isAdd");
            return (Criteria) this;
        }

        public Criteria andIsAddLessThan(String value) {
            addCriterion("IS_ADD <", value, "isAdd");
            return (Criteria) this;
        }

        public Criteria andIsAddLessThanOrEqualTo(String value) {
            addCriterion("IS_ADD <=", value, "isAdd");
            return (Criteria) this;
        }

        public Criteria andIsAddLike(String value) {
            addCriterion("IS_ADD like", value, "isAdd");
            return (Criteria) this;
        }

        public Criteria andIsAddNotLike(String value) {
            addCriterion("IS_ADD not like", value, "isAdd");
            return (Criteria) this;
        }

        public Criteria andIsAddIn(List<String> values) {
            addCriterion("IS_ADD in", values, "isAdd");
            return (Criteria) this;
        }

        public Criteria andIsAddNotIn(List<String> values) {
            addCriterion("IS_ADD not in", values, "isAdd");
            return (Criteria) this;
        }

        public Criteria andIsAddBetween(String value1, String value2) {
            addCriterion("IS_ADD between", value1, value2, "isAdd");
            return (Criteria) this;
        }

        public Criteria andIsAddNotBetween(String value1, String value2) {
            addCriterion("IS_ADD not between", value1, value2, "isAdd");
            return (Criteria) this;
        }

        public Criteria andCompleteBiPerIsNull() {
            addCriterion("COMPLETE_BI_PER is null");
            return (Criteria) this;
        }

        public Criteria andCompleteBiPerIsNotNull() {
            addCriterion("COMPLETE_BI_PER is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteBiPerEqualTo(String value) {
            addCriterion("COMPLETE_BI_PER =", value, "completeBiPer");
            return (Criteria) this;
        }

        public Criteria andCompleteBiPerNotEqualTo(String value) {
            addCriterion("COMPLETE_BI_PER <>", value, "completeBiPer");
            return (Criteria) this;
        }

        public Criteria andCompleteBiPerGreaterThan(String value) {
            addCriterion("COMPLETE_BI_PER >", value, "completeBiPer");
            return (Criteria) this;
        }

        public Criteria andCompleteBiPerGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLETE_BI_PER >=", value, "completeBiPer");
            return (Criteria) this;
        }

        public Criteria andCompleteBiPerLessThan(String value) {
            addCriterion("COMPLETE_BI_PER <", value, "completeBiPer");
            return (Criteria) this;
        }

        public Criteria andCompleteBiPerLessThanOrEqualTo(String value) {
            addCriterion("COMPLETE_BI_PER <=", value, "completeBiPer");
            return (Criteria) this;
        }

        public Criteria andCompleteBiPerLike(String value) {
            addCriterion("COMPLETE_BI_PER like", value, "completeBiPer");
            return (Criteria) this;
        }

        public Criteria andCompleteBiPerNotLike(String value) {
            addCriterion("COMPLETE_BI_PER not like", value, "completeBiPer");
            return (Criteria) this;
        }

        public Criteria andCompleteBiPerIn(List<String> values) {
            addCriterion("COMPLETE_BI_PER in", values, "completeBiPer");
            return (Criteria) this;
        }

        public Criteria andCompleteBiPerNotIn(List<String> values) {
            addCriterion("COMPLETE_BI_PER not in", values, "completeBiPer");
            return (Criteria) this;
        }

        public Criteria andCompleteBiPerBetween(String value1, String value2) {
            addCriterion("COMPLETE_BI_PER between", value1, value2, "completeBiPer");
            return (Criteria) this;
        }

        public Criteria andCompleteBiPerNotBetween(String value1, String value2) {
            addCriterion("COMPLETE_BI_PER not between", value1, value2, "completeBiPer");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiPerIsNull() {
            addCriterion("OUT_COMPLETE_BI_PER is null");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiPerIsNotNull() {
            addCriterion("OUT_COMPLETE_BI_PER is not null");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiPerEqualTo(String value) {
            addCriterion("OUT_COMPLETE_BI_PER =", value, "outCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiPerNotEqualTo(String value) {
            addCriterion("OUT_COMPLETE_BI_PER <>", value, "outCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiPerGreaterThan(String value) {
            addCriterion("OUT_COMPLETE_BI_PER >", value, "outCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiPerGreaterThanOrEqualTo(String value) {
            addCriterion("OUT_COMPLETE_BI_PER >=", value, "outCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiPerLessThan(String value) {
            addCriterion("OUT_COMPLETE_BI_PER <", value, "outCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiPerLessThanOrEqualTo(String value) {
            addCriterion("OUT_COMPLETE_BI_PER <=", value, "outCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiPerLike(String value) {
            addCriterion("OUT_COMPLETE_BI_PER like", value, "outCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiPerNotLike(String value) {
            addCriterion("OUT_COMPLETE_BI_PER not like", value, "outCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiPerIn(List<String> values) {
            addCriterion("OUT_COMPLETE_BI_PER in", values, "outCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiPerNotIn(List<String> values) {
            addCriterion("OUT_COMPLETE_BI_PER not in", values, "outCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiPerBetween(String value1, String value2) {
            addCriterion("OUT_COMPLETE_BI_PER between", value1, value2, "outCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andOutCompleteBiPerNotBetween(String value1, String value2) {
            addCriterion("OUT_COMPLETE_BI_PER not between", value1, value2, "outCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiPerIsNull() {
            addCriterion("IN_COMPLETE_BI_PER is null");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiPerIsNotNull() {
            addCriterion("IN_COMPLETE_BI_PER is not null");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiPerEqualTo(String value) {
            addCriterion("IN_COMPLETE_BI_PER =", value, "inCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiPerNotEqualTo(String value) {
            addCriterion("IN_COMPLETE_BI_PER <>", value, "inCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiPerGreaterThan(String value) {
            addCriterion("IN_COMPLETE_BI_PER >", value, "inCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiPerGreaterThanOrEqualTo(String value) {
            addCriterion("IN_COMPLETE_BI_PER >=", value, "inCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiPerLessThan(String value) {
            addCriterion("IN_COMPLETE_BI_PER <", value, "inCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiPerLessThanOrEqualTo(String value) {
            addCriterion("IN_COMPLETE_BI_PER <=", value, "inCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiPerLike(String value) {
            addCriterion("IN_COMPLETE_BI_PER like", value, "inCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiPerNotLike(String value) {
            addCriterion("IN_COMPLETE_BI_PER not like", value, "inCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiPerIn(List<String> values) {
            addCriterion("IN_COMPLETE_BI_PER in", values, "inCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiPerNotIn(List<String> values) {
            addCriterion("IN_COMPLETE_BI_PER not in", values, "inCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiPerBetween(String value1, String value2) {
            addCriterion("IN_COMPLETE_BI_PER between", value1, value2, "inCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andInCompleteBiPerNotBetween(String value1, String value2) {
            addCriterion("IN_COMPLETE_BI_PER not between", value1, value2, "inCompleteBiPer");
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