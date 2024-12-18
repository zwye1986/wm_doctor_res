package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ScresGraduationApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ScresGraduationApplyExample() {
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

        public Criteria andDoctorNameIsNull() {
            addCriterion("DOCTOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIsNotNull() {
            addCriterion("DOCTOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorNameEqualTo(String value) {
            addCriterion("DOCTOR_NAME =", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotEqualTo(String value) {
            addCriterion("DOCTOR_NAME <>", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameGreaterThan(String value) {
            addCriterion("DOCTOR_NAME >", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_NAME >=", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLessThan(String value) {
            addCriterion("DOCTOR_NAME <", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_NAME <=", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLike(String value) {
            addCriterion("DOCTOR_NAME like", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotLike(String value) {
            addCriterion("DOCTOR_NAME not like", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIn(List<String> values) {
            addCriterion("DOCTOR_NAME in", values, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotIn(List<String> values) {
            addCriterion("DOCTOR_NAME not in", values, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_NAME between", value1, value2, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_NAME not between", value1, value2, "doctorName");
            return (Criteria) this;
        }

        public Criteria andIdNoIsNull() {
            addCriterion("ID_NO is null");
            return (Criteria) this;
        }

        public Criteria andIdNoIsNotNull() {
            addCriterion("ID_NO is not null");
            return (Criteria) this;
        }

        public Criteria andIdNoEqualTo(String value) {
            addCriterion("ID_NO =", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotEqualTo(String value) {
            addCriterion("ID_NO <>", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThan(String value) {
            addCriterion("ID_NO >", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThanOrEqualTo(String value) {
            addCriterion("ID_NO >=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThan(String value) {
            addCriterion("ID_NO <", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThanOrEqualTo(String value) {
            addCriterion("ID_NO <=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLike(String value) {
            addCriterion("ID_NO like", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotLike(String value) {
            addCriterion("ID_NO not like", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoIn(List<String> values) {
            addCriterion("ID_NO in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotIn(List<String> values) {
            addCriterion("ID_NO not in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoBetween(String value1, String value2) {
            addCriterion("ID_NO between", value1, value2, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotBetween(String value1, String value2) {
            addCriterion("ID_NO not between", value1, value2, "idNo");
            return (Criteria) this;
        }

        public Criteria andEducationIdIsNull() {
            addCriterion("EDUCATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andEducationIdIsNotNull() {
            addCriterion("EDUCATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEducationIdEqualTo(String value) {
            addCriterion("EDUCATION_ID =", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdNotEqualTo(String value) {
            addCriterion("EDUCATION_ID <>", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdGreaterThan(String value) {
            addCriterion("EDUCATION_ID >", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdGreaterThanOrEqualTo(String value) {
            addCriterion("EDUCATION_ID >=", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdLessThan(String value) {
            addCriterion("EDUCATION_ID <", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdLessThanOrEqualTo(String value) {
            addCriterion("EDUCATION_ID <=", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdLike(String value) {
            addCriterion("EDUCATION_ID like", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdNotLike(String value) {
            addCriterion("EDUCATION_ID not like", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdIn(List<String> values) {
            addCriterion("EDUCATION_ID in", values, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdNotIn(List<String> values) {
            addCriterion("EDUCATION_ID not in", values, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdBetween(String value1, String value2) {
            addCriterion("EDUCATION_ID between", value1, value2, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdNotBetween(String value1, String value2) {
            addCriterion("EDUCATION_ID not between", value1, value2, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationNameIsNull() {
            addCriterion("EDUCATION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEducationNameIsNotNull() {
            addCriterion("EDUCATION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEducationNameEqualTo(String value) {
            addCriterion("EDUCATION_NAME =", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameNotEqualTo(String value) {
            addCriterion("EDUCATION_NAME <>", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameGreaterThan(String value) {
            addCriterion("EDUCATION_NAME >", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameGreaterThanOrEqualTo(String value) {
            addCriterion("EDUCATION_NAME >=", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameLessThan(String value) {
            addCriterion("EDUCATION_NAME <", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameLessThanOrEqualTo(String value) {
            addCriterion("EDUCATION_NAME <=", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameLike(String value) {
            addCriterion("EDUCATION_NAME like", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameNotLike(String value) {
            addCriterion("EDUCATION_NAME not like", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameIn(List<String> values) {
            addCriterion("EDUCATION_NAME in", values, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameNotIn(List<String> values) {
            addCriterion("EDUCATION_NAME not in", values, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameBetween(String value1, String value2) {
            addCriterion("EDUCATION_NAME between", value1, value2, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameNotBetween(String value1, String value2) {
            addCriterion("EDUCATION_NAME not between", value1, value2, "educationName");
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

        public Criteria andTrainingSpeIdIsNull() {
            addCriterion("TRAINING_SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdIsNotNull() {
            addCriterion("TRAINING_SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdEqualTo(String value) {
            addCriterion("TRAINING_SPE_ID =", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdNotEqualTo(String value) {
            addCriterion("TRAINING_SPE_ID <>", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdGreaterThan(String value) {
            addCriterion("TRAINING_SPE_ID >", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_SPE_ID >=", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdLessThan(String value) {
            addCriterion("TRAINING_SPE_ID <", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_SPE_ID <=", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdLike(String value) {
            addCriterion("TRAINING_SPE_ID like", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdNotLike(String value) {
            addCriterion("TRAINING_SPE_ID not like", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdIn(List<String> values) {
            addCriterion("TRAINING_SPE_ID in", values, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdNotIn(List<String> values) {
            addCriterion("TRAINING_SPE_ID not in", values, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdBetween(String value1, String value2) {
            addCriterion("TRAINING_SPE_ID between", value1, value2, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdNotBetween(String value1, String value2) {
            addCriterion("TRAINING_SPE_ID not between", value1, value2, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameIsNull() {
            addCriterion("TRAINING_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameIsNotNull() {
            addCriterion("TRAINING_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameEqualTo(String value) {
            addCriterion("TRAINING_SPE_NAME =", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameNotEqualTo(String value) {
            addCriterion("TRAINING_SPE_NAME <>", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameGreaterThan(String value) {
            addCriterion("TRAINING_SPE_NAME >", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_SPE_NAME >=", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameLessThan(String value) {
            addCriterion("TRAINING_SPE_NAME <", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_SPE_NAME <=", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameLike(String value) {
            addCriterion("TRAINING_SPE_NAME like", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameNotLike(String value) {
            addCriterion("TRAINING_SPE_NAME not like", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameIn(List<String> values) {
            addCriterion("TRAINING_SPE_NAME in", values, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameNotIn(List<String> values) {
            addCriterion("TRAINING_SPE_NAME not in", values, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameBetween(String value1, String value2) {
            addCriterion("TRAINING_SPE_NAME between", value1, value2, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameNotBetween(String value1, String value2) {
            addCriterion("TRAINING_SPE_NAME not between", value1, value2, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingStartDateIsNull() {
            addCriterion("TRAINING_START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTrainingStartDateIsNotNull() {
            addCriterion("TRAINING_START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingStartDateEqualTo(String value) {
            addCriterion("TRAINING_START_DATE =", value, "trainingStartDate");
            return (Criteria) this;
        }

        public Criteria andTrainingStartDateNotEqualTo(String value) {
            addCriterion("TRAINING_START_DATE <>", value, "trainingStartDate");
            return (Criteria) this;
        }

        public Criteria andTrainingStartDateGreaterThan(String value) {
            addCriterion("TRAINING_START_DATE >", value, "trainingStartDate");
            return (Criteria) this;
        }

        public Criteria andTrainingStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_START_DATE >=", value, "trainingStartDate");
            return (Criteria) this;
        }

        public Criteria andTrainingStartDateLessThan(String value) {
            addCriterion("TRAINING_START_DATE <", value, "trainingStartDate");
            return (Criteria) this;
        }

        public Criteria andTrainingStartDateLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_START_DATE <=", value, "trainingStartDate");
            return (Criteria) this;
        }

        public Criteria andTrainingStartDateLike(String value) {
            addCriterion("TRAINING_START_DATE like", value, "trainingStartDate");
            return (Criteria) this;
        }

        public Criteria andTrainingStartDateNotLike(String value) {
            addCriterion("TRAINING_START_DATE not like", value, "trainingStartDate");
            return (Criteria) this;
        }

        public Criteria andTrainingStartDateIn(List<String> values) {
            addCriterion("TRAINING_START_DATE in", values, "trainingStartDate");
            return (Criteria) this;
        }

        public Criteria andTrainingStartDateNotIn(List<String> values) {
            addCriterion("TRAINING_START_DATE not in", values, "trainingStartDate");
            return (Criteria) this;
        }

        public Criteria andTrainingStartDateBetween(String value1, String value2) {
            addCriterion("TRAINING_START_DATE between", value1, value2, "trainingStartDate");
            return (Criteria) this;
        }

        public Criteria andTrainingStartDateNotBetween(String value1, String value2) {
            addCriterion("TRAINING_START_DATE not between", value1, value2, "trainingStartDate");
            return (Criteria) this;
        }

        public Criteria andTrainingEndDateIsNull() {
            addCriterion("TRAINING_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTrainingEndDateIsNotNull() {
            addCriterion("TRAINING_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingEndDateEqualTo(String value) {
            addCriterion("TRAINING_END_DATE =", value, "trainingEndDate");
            return (Criteria) this;
        }

        public Criteria andTrainingEndDateNotEqualTo(String value) {
            addCriterion("TRAINING_END_DATE <>", value, "trainingEndDate");
            return (Criteria) this;
        }

        public Criteria andTrainingEndDateGreaterThan(String value) {
            addCriterion("TRAINING_END_DATE >", value, "trainingEndDate");
            return (Criteria) this;
        }

        public Criteria andTrainingEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_END_DATE >=", value, "trainingEndDate");
            return (Criteria) this;
        }

        public Criteria andTrainingEndDateLessThan(String value) {
            addCriterion("TRAINING_END_DATE <", value, "trainingEndDate");
            return (Criteria) this;
        }

        public Criteria andTrainingEndDateLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_END_DATE <=", value, "trainingEndDate");
            return (Criteria) this;
        }

        public Criteria andTrainingEndDateLike(String value) {
            addCriterion("TRAINING_END_DATE like", value, "trainingEndDate");
            return (Criteria) this;
        }

        public Criteria andTrainingEndDateNotLike(String value) {
            addCriterion("TRAINING_END_DATE not like", value, "trainingEndDate");
            return (Criteria) this;
        }

        public Criteria andTrainingEndDateIn(List<String> values) {
            addCriterion("TRAINING_END_DATE in", values, "trainingEndDate");
            return (Criteria) this;
        }

        public Criteria andTrainingEndDateNotIn(List<String> values) {
            addCriterion("TRAINING_END_DATE not in", values, "trainingEndDate");
            return (Criteria) this;
        }

        public Criteria andTrainingEndDateBetween(String value1, String value2) {
            addCriterion("TRAINING_END_DATE between", value1, value2, "trainingEndDate");
            return (Criteria) this;
        }

        public Criteria andTrainingEndDateNotBetween(String value1, String value2) {
            addCriterion("TRAINING_END_DATE not between", value1, value2, "trainingEndDate");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoIsNull() {
            addCriterion("DOCTOR_LICENSE_NO is null");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoIsNotNull() {
            addCriterion("DOCTOR_LICENSE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoEqualTo(String value) {
            addCriterion("DOCTOR_LICENSE_NO =", value, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoNotEqualTo(String value) {
            addCriterion("DOCTOR_LICENSE_NO <>", value, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoGreaterThan(String value) {
            addCriterion("DOCTOR_LICENSE_NO >", value, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_LICENSE_NO >=", value, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoLessThan(String value) {
            addCriterion("DOCTOR_LICENSE_NO <", value, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_LICENSE_NO <=", value, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoLike(String value) {
            addCriterion("DOCTOR_LICENSE_NO like", value, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoNotLike(String value) {
            addCriterion("DOCTOR_LICENSE_NO not like", value, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoIn(List<String> values) {
            addCriterion("DOCTOR_LICENSE_NO in", values, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoNotIn(List<String> values) {
            addCriterion("DOCTOR_LICENSE_NO not in", values, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoBetween(String value1, String value2) {
            addCriterion("DOCTOR_LICENSE_NO between", value1, value2, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_LICENSE_NO not between", value1, value2, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andGraduationYearIsNull() {
            addCriterion("GRADUATION_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andGraduationYearIsNotNull() {
            addCriterion("GRADUATION_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationYearEqualTo(String value) {
            addCriterion("GRADUATION_YEAR =", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotEqualTo(String value) {
            addCriterion("GRADUATION_YEAR <>", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearGreaterThan(String value) {
            addCriterion("GRADUATION_YEAR >", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_YEAR >=", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearLessThan(String value) {
            addCriterion("GRADUATION_YEAR <", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_YEAR <=", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearLike(String value) {
            addCriterion("GRADUATION_YEAR like", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotLike(String value) {
            addCriterion("GRADUATION_YEAR not like", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearIn(List<String> values) {
            addCriterion("GRADUATION_YEAR in", values, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotIn(List<String> values) {
            addCriterion("GRADUATION_YEAR not in", values, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearBetween(String value1, String value2) {
            addCriterion("GRADUATION_YEAR between", value1, value2, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_YEAR not between", value1, value2, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusIdIsNull() {
            addCriterion("XT_ORG_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusIdIsNotNull() {
            addCriterion("XT_ORG_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusIdEqualTo(String value) {
            addCriterion("XT_ORG_STATUS_ID =", value, "xtOrgStatusId");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusIdNotEqualTo(String value) {
            addCriterion("XT_ORG_STATUS_ID <>", value, "xtOrgStatusId");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusIdGreaterThan(String value) {
            addCriterion("XT_ORG_STATUS_ID >", value, "xtOrgStatusId");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("XT_ORG_STATUS_ID >=", value, "xtOrgStatusId");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusIdLessThan(String value) {
            addCriterion("XT_ORG_STATUS_ID <", value, "xtOrgStatusId");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusIdLessThanOrEqualTo(String value) {
            addCriterion("XT_ORG_STATUS_ID <=", value, "xtOrgStatusId");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusIdLike(String value) {
            addCriterion("XT_ORG_STATUS_ID like", value, "xtOrgStatusId");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusIdNotLike(String value) {
            addCriterion("XT_ORG_STATUS_ID not like", value, "xtOrgStatusId");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusIdIn(List<String> values) {
            addCriterion("XT_ORG_STATUS_ID in", values, "xtOrgStatusId");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusIdNotIn(List<String> values) {
            addCriterion("XT_ORG_STATUS_ID not in", values, "xtOrgStatusId");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusIdBetween(String value1, String value2) {
            addCriterion("XT_ORG_STATUS_ID between", value1, value2, "xtOrgStatusId");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusIdNotBetween(String value1, String value2) {
            addCriterion("XT_ORG_STATUS_ID not between", value1, value2, "xtOrgStatusId");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusNameIsNull() {
            addCriterion("XT_ORG_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusNameIsNotNull() {
            addCriterion("XT_ORG_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusNameEqualTo(String value) {
            addCriterion("XT_ORG_STATUS_NAME =", value, "xtOrgStatusName");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusNameNotEqualTo(String value) {
            addCriterion("XT_ORG_STATUS_NAME <>", value, "xtOrgStatusName");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusNameGreaterThan(String value) {
            addCriterion("XT_ORG_STATUS_NAME >", value, "xtOrgStatusName");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("XT_ORG_STATUS_NAME >=", value, "xtOrgStatusName");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusNameLessThan(String value) {
            addCriterion("XT_ORG_STATUS_NAME <", value, "xtOrgStatusName");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusNameLessThanOrEqualTo(String value) {
            addCriterion("XT_ORG_STATUS_NAME <=", value, "xtOrgStatusName");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusNameLike(String value) {
            addCriterion("XT_ORG_STATUS_NAME like", value, "xtOrgStatusName");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusNameNotLike(String value) {
            addCriterion("XT_ORG_STATUS_NAME not like", value, "xtOrgStatusName");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusNameIn(List<String> values) {
            addCriterion("XT_ORG_STATUS_NAME in", values, "xtOrgStatusName");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusNameNotIn(List<String> values) {
            addCriterion("XT_ORG_STATUS_NAME not in", values, "xtOrgStatusName");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusNameBetween(String value1, String value2) {
            addCriterion("XT_ORG_STATUS_NAME between", value1, value2, "xtOrgStatusName");
            return (Criteria) this;
        }

        public Criteria andXtOrgStatusNameNotBetween(String value1, String value2) {
            addCriterion("XT_ORG_STATUS_NAME not between", value1, value2, "xtOrgStatusName");
            return (Criteria) this;
        }

        public Criteria andOrgStatusIdIsNull() {
            addCriterion("ORG_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgStatusIdIsNotNull() {
            addCriterion("ORG_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgStatusIdEqualTo(String value) {
            addCriterion("ORG_STATUS_ID =", value, "orgStatusId");
            return (Criteria) this;
        }

        public Criteria andOrgStatusIdNotEqualTo(String value) {
            addCriterion("ORG_STATUS_ID <>", value, "orgStatusId");
            return (Criteria) this;
        }

        public Criteria andOrgStatusIdGreaterThan(String value) {
            addCriterion("ORG_STATUS_ID >", value, "orgStatusId");
            return (Criteria) this;
        }

        public Criteria andOrgStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_STATUS_ID >=", value, "orgStatusId");
            return (Criteria) this;
        }

        public Criteria andOrgStatusIdLessThan(String value) {
            addCriterion("ORG_STATUS_ID <", value, "orgStatusId");
            return (Criteria) this;
        }

        public Criteria andOrgStatusIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_STATUS_ID <=", value, "orgStatusId");
            return (Criteria) this;
        }

        public Criteria andOrgStatusIdLike(String value) {
            addCriterion("ORG_STATUS_ID like", value, "orgStatusId");
            return (Criteria) this;
        }

        public Criteria andOrgStatusIdNotLike(String value) {
            addCriterion("ORG_STATUS_ID not like", value, "orgStatusId");
            return (Criteria) this;
        }

        public Criteria andOrgStatusIdIn(List<String> values) {
            addCriterion("ORG_STATUS_ID in", values, "orgStatusId");
            return (Criteria) this;
        }

        public Criteria andOrgStatusIdNotIn(List<String> values) {
            addCriterion("ORG_STATUS_ID not in", values, "orgStatusId");
            return (Criteria) this;
        }

        public Criteria andOrgStatusIdBetween(String value1, String value2) {
            addCriterion("ORG_STATUS_ID between", value1, value2, "orgStatusId");
            return (Criteria) this;
        }

        public Criteria andOrgStatusIdNotBetween(String value1, String value2) {
            addCriterion("ORG_STATUS_ID not between", value1, value2, "orgStatusId");
            return (Criteria) this;
        }

        public Criteria andOrgStatusNameIsNull() {
            addCriterion("ORG_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgStatusNameIsNotNull() {
            addCriterion("ORG_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgStatusNameEqualTo(String value) {
            addCriterion("ORG_STATUS_NAME =", value, "orgStatusName");
            return (Criteria) this;
        }

        public Criteria andOrgStatusNameNotEqualTo(String value) {
            addCriterion("ORG_STATUS_NAME <>", value, "orgStatusName");
            return (Criteria) this;
        }

        public Criteria andOrgStatusNameGreaterThan(String value) {
            addCriterion("ORG_STATUS_NAME >", value, "orgStatusName");
            return (Criteria) this;
        }

        public Criteria andOrgStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_STATUS_NAME >=", value, "orgStatusName");
            return (Criteria) this;
        }

        public Criteria andOrgStatusNameLessThan(String value) {
            addCriterion("ORG_STATUS_NAME <", value, "orgStatusName");
            return (Criteria) this;
        }

        public Criteria andOrgStatusNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_STATUS_NAME <=", value, "orgStatusName");
            return (Criteria) this;
        }

        public Criteria andOrgStatusNameLike(String value) {
            addCriterion("ORG_STATUS_NAME like", value, "orgStatusName");
            return (Criteria) this;
        }

        public Criteria andOrgStatusNameNotLike(String value) {
            addCriterion("ORG_STATUS_NAME not like", value, "orgStatusName");
            return (Criteria) this;
        }

        public Criteria andOrgStatusNameIn(List<String> values) {
            addCriterion("ORG_STATUS_NAME in", values, "orgStatusName");
            return (Criteria) this;
        }

        public Criteria andOrgStatusNameNotIn(List<String> values) {
            addCriterion("ORG_STATUS_NAME not in", values, "orgStatusName");
            return (Criteria) this;
        }

        public Criteria andOrgStatusNameBetween(String value1, String value2) {
            addCriterion("ORG_STATUS_NAME between", value1, value2, "orgStatusName");
            return (Criteria) this;
        }

        public Criteria andOrgStatusNameNotBetween(String value1, String value2) {
            addCriterion("ORG_STATUS_NAME not between", value1, value2, "orgStatusName");
            return (Criteria) this;
        }

        public Criteria andChargeStatusIdIsNull() {
            addCriterion("CHARGE_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andChargeStatusIdIsNotNull() {
            addCriterion("CHARGE_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andChargeStatusIdEqualTo(String value) {
            addCriterion("CHARGE_STATUS_ID =", value, "chargeStatusId");
            return (Criteria) this;
        }

        public Criteria andChargeStatusIdNotEqualTo(String value) {
            addCriterion("CHARGE_STATUS_ID <>", value, "chargeStatusId");
            return (Criteria) this;
        }

        public Criteria andChargeStatusIdGreaterThan(String value) {
            addCriterion("CHARGE_STATUS_ID >", value, "chargeStatusId");
            return (Criteria) this;
        }

        public Criteria andChargeStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_STATUS_ID >=", value, "chargeStatusId");
            return (Criteria) this;
        }

        public Criteria andChargeStatusIdLessThan(String value) {
            addCriterion("CHARGE_STATUS_ID <", value, "chargeStatusId");
            return (Criteria) this;
        }

        public Criteria andChargeStatusIdLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_STATUS_ID <=", value, "chargeStatusId");
            return (Criteria) this;
        }

        public Criteria andChargeStatusIdLike(String value) {
            addCriterion("CHARGE_STATUS_ID like", value, "chargeStatusId");
            return (Criteria) this;
        }

        public Criteria andChargeStatusIdNotLike(String value) {
            addCriterion("CHARGE_STATUS_ID not like", value, "chargeStatusId");
            return (Criteria) this;
        }

        public Criteria andChargeStatusIdIn(List<String> values) {
            addCriterion("CHARGE_STATUS_ID in", values, "chargeStatusId");
            return (Criteria) this;
        }

        public Criteria andChargeStatusIdNotIn(List<String> values) {
            addCriterion("CHARGE_STATUS_ID not in", values, "chargeStatusId");
            return (Criteria) this;
        }

        public Criteria andChargeStatusIdBetween(String value1, String value2) {
            addCriterion("CHARGE_STATUS_ID between", value1, value2, "chargeStatusId");
            return (Criteria) this;
        }

        public Criteria andChargeStatusIdNotBetween(String value1, String value2) {
            addCriterion("CHARGE_STATUS_ID not between", value1, value2, "chargeStatusId");
            return (Criteria) this;
        }

        public Criteria andChargeStatusNameIsNull() {
            addCriterion("CHARGE_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChargeStatusNameIsNotNull() {
            addCriterion("CHARGE_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChargeStatusNameEqualTo(String value) {
            addCriterion("CHARGE_STATUS_NAME =", value, "chargeStatusName");
            return (Criteria) this;
        }

        public Criteria andChargeStatusNameNotEqualTo(String value) {
            addCriterion("CHARGE_STATUS_NAME <>", value, "chargeStatusName");
            return (Criteria) this;
        }

        public Criteria andChargeStatusNameGreaterThan(String value) {
            addCriterion("CHARGE_STATUS_NAME >", value, "chargeStatusName");
            return (Criteria) this;
        }

        public Criteria andChargeStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_STATUS_NAME >=", value, "chargeStatusName");
            return (Criteria) this;
        }

        public Criteria andChargeStatusNameLessThan(String value) {
            addCriterion("CHARGE_STATUS_NAME <", value, "chargeStatusName");
            return (Criteria) this;
        }

        public Criteria andChargeStatusNameLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_STATUS_NAME <=", value, "chargeStatusName");
            return (Criteria) this;
        }

        public Criteria andChargeStatusNameLike(String value) {
            addCriterion("CHARGE_STATUS_NAME like", value, "chargeStatusName");
            return (Criteria) this;
        }

        public Criteria andChargeStatusNameNotLike(String value) {
            addCriterion("CHARGE_STATUS_NAME not like", value, "chargeStatusName");
            return (Criteria) this;
        }

        public Criteria andChargeStatusNameIn(List<String> values) {
            addCriterion("CHARGE_STATUS_NAME in", values, "chargeStatusName");
            return (Criteria) this;
        }

        public Criteria andChargeStatusNameNotIn(List<String> values) {
            addCriterion("CHARGE_STATUS_NAME not in", values, "chargeStatusName");
            return (Criteria) this;
        }

        public Criteria andChargeStatusNameBetween(String value1, String value2) {
            addCriterion("CHARGE_STATUS_NAME between", value1, value2, "chargeStatusName");
            return (Criteria) this;
        }

        public Criteria andChargeStatusNameNotBetween(String value1, String value2) {
            addCriterion("CHARGE_STATUS_NAME not between", value1, value2, "chargeStatusName");
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

        public Criteria andDoctorStatusIdIsNull() {
            addCriterion("DOCTOR_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdIsNotNull() {
            addCriterion("DOCTOR_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdEqualTo(String value) {
            addCriterion("DOCTOR_STATUS_ID =", value, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdNotEqualTo(String value) {
            addCriterion("DOCTOR_STATUS_ID <>", value, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdGreaterThan(String value) {
            addCriterion("DOCTOR_STATUS_ID >", value, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_STATUS_ID >=", value, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdLessThan(String value) {
            addCriterion("DOCTOR_STATUS_ID <", value, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_STATUS_ID <=", value, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdLike(String value) {
            addCriterion("DOCTOR_STATUS_ID like", value, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdNotLike(String value) {
            addCriterion("DOCTOR_STATUS_ID not like", value, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdIn(List<String> values) {
            addCriterion("DOCTOR_STATUS_ID in", values, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdNotIn(List<String> values) {
            addCriterion("DOCTOR_STATUS_ID not in", values, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdBetween(String value1, String value2) {
            addCriterion("DOCTOR_STATUS_ID between", value1, value2, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_STATUS_ID not between", value1, value2, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameIsNull() {
            addCriterion("DOCTOR_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameIsNotNull() {
            addCriterion("DOCTOR_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameEqualTo(String value) {
            addCriterion("DOCTOR_STATUS_NAME =", value, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameNotEqualTo(String value) {
            addCriterion("DOCTOR_STATUS_NAME <>", value, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameGreaterThan(String value) {
            addCriterion("DOCTOR_STATUS_NAME >", value, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_STATUS_NAME >=", value, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameLessThan(String value) {
            addCriterion("DOCTOR_STATUS_NAME <", value, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_STATUS_NAME <=", value, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameLike(String value) {
            addCriterion("DOCTOR_STATUS_NAME like", value, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameNotLike(String value) {
            addCriterion("DOCTOR_STATUS_NAME not like", value, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameIn(List<String> values) {
            addCriterion("DOCTOR_STATUS_NAME in", values, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameNotIn(List<String> values) {
            addCriterion("DOCTOR_STATUS_NAME not in", values, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_STATUS_NAME between", value1, value2, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_STATUS_NAME not between", value1, value2, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andXtOrgRemarkIsNull() {
            addCriterion("XT_ORG_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andXtOrgRemarkIsNotNull() {
            addCriterion("XT_ORG_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andXtOrgRemarkEqualTo(String value) {
            addCriterion("XT_ORG_REMARK =", value, "xtOrgRemark");
            return (Criteria) this;
        }

        public Criteria andXtOrgRemarkNotEqualTo(String value) {
            addCriterion("XT_ORG_REMARK <>", value, "xtOrgRemark");
            return (Criteria) this;
        }

        public Criteria andXtOrgRemarkGreaterThan(String value) {
            addCriterion("XT_ORG_REMARK >", value, "xtOrgRemark");
            return (Criteria) this;
        }

        public Criteria andXtOrgRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("XT_ORG_REMARK >=", value, "xtOrgRemark");
            return (Criteria) this;
        }

        public Criteria andXtOrgRemarkLessThan(String value) {
            addCriterion("XT_ORG_REMARK <", value, "xtOrgRemark");
            return (Criteria) this;
        }

        public Criteria andXtOrgRemarkLessThanOrEqualTo(String value) {
            addCriterion("XT_ORG_REMARK <=", value, "xtOrgRemark");
            return (Criteria) this;
        }

        public Criteria andXtOrgRemarkLike(String value) {
            addCriterion("XT_ORG_REMARK like", value, "xtOrgRemark");
            return (Criteria) this;
        }

        public Criteria andXtOrgRemarkNotLike(String value) {
            addCriterion("XT_ORG_REMARK not like", value, "xtOrgRemark");
            return (Criteria) this;
        }

        public Criteria andXtOrgRemarkIn(List<String> values) {
            addCriterion("XT_ORG_REMARK in", values, "xtOrgRemark");
            return (Criteria) this;
        }

        public Criteria andXtOrgRemarkNotIn(List<String> values) {
            addCriterion("XT_ORG_REMARK not in", values, "xtOrgRemark");
            return (Criteria) this;
        }

        public Criteria andXtOrgRemarkBetween(String value1, String value2) {
            addCriterion("XT_ORG_REMARK between", value1, value2, "xtOrgRemark");
            return (Criteria) this;
        }

        public Criteria andXtOrgRemarkNotBetween(String value1, String value2) {
            addCriterion("XT_ORG_REMARK not between", value1, value2, "xtOrgRemark");
            return (Criteria) this;
        }

        public Criteria andOrgRemarkIsNull() {
            addCriterion("ORG_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andOrgRemarkIsNotNull() {
            addCriterion("ORG_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andOrgRemarkEqualTo(String value) {
            addCriterion("ORG_REMARK =", value, "orgRemark");
            return (Criteria) this;
        }

        public Criteria andOrgRemarkNotEqualTo(String value) {
            addCriterion("ORG_REMARK <>", value, "orgRemark");
            return (Criteria) this;
        }

        public Criteria andOrgRemarkGreaterThan(String value) {
            addCriterion("ORG_REMARK >", value, "orgRemark");
            return (Criteria) this;
        }

        public Criteria andOrgRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_REMARK >=", value, "orgRemark");
            return (Criteria) this;
        }

        public Criteria andOrgRemarkLessThan(String value) {
            addCriterion("ORG_REMARK <", value, "orgRemark");
            return (Criteria) this;
        }

        public Criteria andOrgRemarkLessThanOrEqualTo(String value) {
            addCriterion("ORG_REMARK <=", value, "orgRemark");
            return (Criteria) this;
        }

        public Criteria andOrgRemarkLike(String value) {
            addCriterion("ORG_REMARK like", value, "orgRemark");
            return (Criteria) this;
        }

        public Criteria andOrgRemarkNotLike(String value) {
            addCriterion("ORG_REMARK not like", value, "orgRemark");
            return (Criteria) this;
        }

        public Criteria andOrgRemarkIn(List<String> values) {
            addCriterion("ORG_REMARK in", values, "orgRemark");
            return (Criteria) this;
        }

        public Criteria andOrgRemarkNotIn(List<String> values) {
            addCriterion("ORG_REMARK not in", values, "orgRemark");
            return (Criteria) this;
        }

        public Criteria andOrgRemarkBetween(String value1, String value2) {
            addCriterion("ORG_REMARK between", value1, value2, "orgRemark");
            return (Criteria) this;
        }

        public Criteria andOrgRemarkNotBetween(String value1, String value2) {
            addCriterion("ORG_REMARK not between", value1, value2, "orgRemark");
            return (Criteria) this;
        }

        public Criteria andChargeRemarkIsNull() {
            addCriterion("CHARGE_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andChargeRemarkIsNotNull() {
            addCriterion("CHARGE_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andChargeRemarkEqualTo(String value) {
            addCriterion("CHARGE_REMARK =", value, "chargeRemark");
            return (Criteria) this;
        }

        public Criteria andChargeRemarkNotEqualTo(String value) {
            addCriterion("CHARGE_REMARK <>", value, "chargeRemark");
            return (Criteria) this;
        }

        public Criteria andChargeRemarkGreaterThan(String value) {
            addCriterion("CHARGE_REMARK >", value, "chargeRemark");
            return (Criteria) this;
        }

        public Criteria andChargeRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_REMARK >=", value, "chargeRemark");
            return (Criteria) this;
        }

        public Criteria andChargeRemarkLessThan(String value) {
            addCriterion("CHARGE_REMARK <", value, "chargeRemark");
            return (Criteria) this;
        }

        public Criteria andChargeRemarkLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_REMARK <=", value, "chargeRemark");
            return (Criteria) this;
        }

        public Criteria andChargeRemarkLike(String value) {
            addCriterion("CHARGE_REMARK like", value, "chargeRemark");
            return (Criteria) this;
        }

        public Criteria andChargeRemarkNotLike(String value) {
            addCriterion("CHARGE_REMARK not like", value, "chargeRemark");
            return (Criteria) this;
        }

        public Criteria andChargeRemarkIn(List<String> values) {
            addCriterion("CHARGE_REMARK in", values, "chargeRemark");
            return (Criteria) this;
        }

        public Criteria andChargeRemarkNotIn(List<String> values) {
            addCriterion("CHARGE_REMARK not in", values, "chargeRemark");
            return (Criteria) this;
        }

        public Criteria andChargeRemarkBetween(String value1, String value2) {
            addCriterion("CHARGE_REMARK between", value1, value2, "chargeRemark");
            return (Criteria) this;
        }

        public Criteria andChargeRemarkNotBetween(String value1, String value2) {
            addCriterion("CHARGE_REMARK not between", value1, value2, "chargeRemark");
            return (Criteria) this;
        }

        public Criteria andOrgAuditorNameIsNull() {
            addCriterion("ORG_AUDITOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgAuditorNameIsNotNull() {
            addCriterion("ORG_AUDITOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAuditorNameEqualTo(String value) {
            addCriterion("ORG_AUDITOR_NAME =", value, "orgAuditorName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditorNameNotEqualTo(String value) {
            addCriterion("ORG_AUDITOR_NAME <>", value, "orgAuditorName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditorNameGreaterThan(String value) {
            addCriterion("ORG_AUDITOR_NAME >", value, "orgAuditorName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditorNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_AUDITOR_NAME >=", value, "orgAuditorName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditorNameLessThan(String value) {
            addCriterion("ORG_AUDITOR_NAME <", value, "orgAuditorName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditorNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_AUDITOR_NAME <=", value, "orgAuditorName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditorNameLike(String value) {
            addCriterion("ORG_AUDITOR_NAME like", value, "orgAuditorName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditorNameNotLike(String value) {
            addCriterion("ORG_AUDITOR_NAME not like", value, "orgAuditorName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditorNameIn(List<String> values) {
            addCriterion("ORG_AUDITOR_NAME in", values, "orgAuditorName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditorNameNotIn(List<String> values) {
            addCriterion("ORG_AUDITOR_NAME not in", values, "orgAuditorName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditorNameBetween(String value1, String value2) {
            addCriterion("ORG_AUDITOR_NAME between", value1, value2, "orgAuditorName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditorNameNotBetween(String value1, String value2) {
            addCriterion("ORG_AUDITOR_NAME not between", value1, value2, "orgAuditorName");
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