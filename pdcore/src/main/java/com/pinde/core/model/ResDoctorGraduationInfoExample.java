package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResDoctorGraduationInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResDoctorGraduationInfoExample() {
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

        public Criteria andOrgCodeIsNull() {
            addCriterion("ORG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNotNull() {
            addCriterion("ORG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeEqualTo(String value) {
            addCriterion("ORG_CODE =", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotEqualTo(String value) {
            addCriterion("ORG_CODE <>", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThan(String value) {
            addCriterion("ORG_CODE >", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CODE >=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThan(String value) {
            addCriterion("ORG_CODE <", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("ORG_CODE <=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLike(String value) {
            addCriterion("ORG_CODE like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotLike(String value) {
            addCriterion("ORG_CODE not like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIn(List<String> values) {
            addCriterion("ORG_CODE in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotIn(List<String> values) {
            addCriterion("ORG_CODE not in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeBetween(String value1, String value2) {
            addCriterion("ORG_CODE between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotBetween(String value1, String value2) {
            addCriterion("ORG_CODE not between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andNationalBaseFlowIsNull() {
            addCriterion("NATIONAL_BASE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andNationalBaseFlowIsNotNull() {
            addCriterion("NATIONAL_BASE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andNationalBaseFlowEqualTo(String value) {
            addCriterion("NATIONAL_BASE_FLOW =", value, "nationalBaseFlow");
            return (Criteria) this;
        }

        public Criteria andNationalBaseFlowNotEqualTo(String value) {
            addCriterion("NATIONAL_BASE_FLOW <>", value, "nationalBaseFlow");
            return (Criteria) this;
        }

        public Criteria andNationalBaseFlowGreaterThan(String value) {
            addCriterion("NATIONAL_BASE_FLOW >", value, "nationalBaseFlow");
            return (Criteria) this;
        }

        public Criteria andNationalBaseFlowGreaterThanOrEqualTo(String value) {
            addCriterion("NATIONAL_BASE_FLOW >=", value, "nationalBaseFlow");
            return (Criteria) this;
        }

        public Criteria andNationalBaseFlowLessThan(String value) {
            addCriterion("NATIONAL_BASE_FLOW <", value, "nationalBaseFlow");
            return (Criteria) this;
        }

        public Criteria andNationalBaseFlowLessThanOrEqualTo(String value) {
            addCriterion("NATIONAL_BASE_FLOW <=", value, "nationalBaseFlow");
            return (Criteria) this;
        }

        public Criteria andNationalBaseFlowLike(String value) {
            addCriterion("NATIONAL_BASE_FLOW like", value, "nationalBaseFlow");
            return (Criteria) this;
        }

        public Criteria andNationalBaseFlowNotLike(String value) {
            addCriterion("NATIONAL_BASE_FLOW not like", value, "nationalBaseFlow");
            return (Criteria) this;
        }

        public Criteria andNationalBaseFlowIn(List<String> values) {
            addCriterion("NATIONAL_BASE_FLOW in", values, "nationalBaseFlow");
            return (Criteria) this;
        }

        public Criteria andNationalBaseFlowNotIn(List<String> values) {
            addCriterion("NATIONAL_BASE_FLOW not in", values, "nationalBaseFlow");
            return (Criteria) this;
        }

        public Criteria andNationalBaseFlowBetween(String value1, String value2) {
            addCriterion("NATIONAL_BASE_FLOW between", value1, value2, "nationalBaseFlow");
            return (Criteria) this;
        }

        public Criteria andNationalBaseFlowNotBetween(String value1, String value2) {
            addCriterion("NATIONAL_BASE_FLOW not between", value1, value2, "nationalBaseFlow");
            return (Criteria) this;
        }

        public Criteria andNationalBaseNameIsNull() {
            addCriterion("NATIONAL_BASE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andNationalBaseNameIsNotNull() {
            addCriterion("NATIONAL_BASE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNationalBaseNameEqualTo(String value) {
            addCriterion("NATIONAL_BASE_NAME =", value, "nationalBaseName");
            return (Criteria) this;
        }

        public Criteria andNationalBaseNameNotEqualTo(String value) {
            addCriterion("NATIONAL_BASE_NAME <>", value, "nationalBaseName");
            return (Criteria) this;
        }

        public Criteria andNationalBaseNameGreaterThan(String value) {
            addCriterion("NATIONAL_BASE_NAME >", value, "nationalBaseName");
            return (Criteria) this;
        }

        public Criteria andNationalBaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("NATIONAL_BASE_NAME >=", value, "nationalBaseName");
            return (Criteria) this;
        }

        public Criteria andNationalBaseNameLessThan(String value) {
            addCriterion("NATIONAL_BASE_NAME <", value, "nationalBaseName");
            return (Criteria) this;
        }

        public Criteria andNationalBaseNameLessThanOrEqualTo(String value) {
            addCriterion("NATIONAL_BASE_NAME <=", value, "nationalBaseName");
            return (Criteria) this;
        }

        public Criteria andNationalBaseNameLike(String value) {
            addCriterion("NATIONAL_BASE_NAME like", value, "nationalBaseName");
            return (Criteria) this;
        }

        public Criteria andNationalBaseNameNotLike(String value) {
            addCriterion("NATIONAL_BASE_NAME not like", value, "nationalBaseName");
            return (Criteria) this;
        }

        public Criteria andNationalBaseNameIn(List<String> values) {
            addCriterion("NATIONAL_BASE_NAME in", values, "nationalBaseName");
            return (Criteria) this;
        }

        public Criteria andNationalBaseNameNotIn(List<String> values) {
            addCriterion("NATIONAL_BASE_NAME not in", values, "nationalBaseName");
            return (Criteria) this;
        }

        public Criteria andNationalBaseNameBetween(String value1, String value2) {
            addCriterion("NATIONAL_BASE_NAME between", value1, value2, "nationalBaseName");
            return (Criteria) this;
        }

        public Criteria andNationalBaseNameNotBetween(String value1, String value2) {
            addCriterion("NATIONAL_BASE_NAME not between", value1, value2, "nationalBaseName");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseFlowIsNull() {
            addCriterion("TRAINING_BASE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseFlowIsNotNull() {
            addCriterion("TRAINING_BASE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseFlowEqualTo(String value) {
            addCriterion("TRAINING_BASE_FLOW =", value, "trainingBaseFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseFlowNotEqualTo(String value) {
            addCriterion("TRAINING_BASE_FLOW <>", value, "trainingBaseFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseFlowGreaterThan(String value) {
            addCriterion("TRAINING_BASE_FLOW >", value, "trainingBaseFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_BASE_FLOW >=", value, "trainingBaseFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseFlowLessThan(String value) {
            addCriterion("TRAINING_BASE_FLOW <", value, "trainingBaseFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseFlowLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_BASE_FLOW <=", value, "trainingBaseFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseFlowLike(String value) {
            addCriterion("TRAINING_BASE_FLOW like", value, "trainingBaseFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseFlowNotLike(String value) {
            addCriterion("TRAINING_BASE_FLOW not like", value, "trainingBaseFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseFlowIn(List<String> values) {
            addCriterion("TRAINING_BASE_FLOW in", values, "trainingBaseFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseFlowNotIn(List<String> values) {
            addCriterion("TRAINING_BASE_FLOW not in", values, "trainingBaseFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseFlowBetween(String value1, String value2) {
            addCriterion("TRAINING_BASE_FLOW between", value1, value2, "trainingBaseFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseFlowNotBetween(String value1, String value2) {
            addCriterion("TRAINING_BASE_FLOW not between", value1, value2, "trainingBaseFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseNameIsNull() {
            addCriterion("TRAINING_BASE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseNameIsNotNull() {
            addCriterion("TRAINING_BASE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseNameEqualTo(String value) {
            addCriterion("TRAINING_BASE_NAME =", value, "trainingBaseName");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseNameNotEqualTo(String value) {
            addCriterion("TRAINING_BASE_NAME <>", value, "trainingBaseName");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseNameGreaterThan(String value) {
            addCriterion("TRAINING_BASE_NAME >", value, "trainingBaseName");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_BASE_NAME >=", value, "trainingBaseName");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseNameLessThan(String value) {
            addCriterion("TRAINING_BASE_NAME <", value, "trainingBaseName");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseNameLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_BASE_NAME <=", value, "trainingBaseName");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseNameLike(String value) {
            addCriterion("TRAINING_BASE_NAME like", value, "trainingBaseName");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseNameNotLike(String value) {
            addCriterion("TRAINING_BASE_NAME not like", value, "trainingBaseName");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseNameIn(List<String> values) {
            addCriterion("TRAINING_BASE_NAME in", values, "trainingBaseName");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseNameNotIn(List<String> values) {
            addCriterion("TRAINING_BASE_NAME not in", values, "trainingBaseName");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseNameBetween(String value1, String value2) {
            addCriterion("TRAINING_BASE_NAME between", value1, value2, "trainingBaseName");
            return (Criteria) this;
        }

        public Criteria andTrainingBaseNameNotBetween(String value1, String value2) {
            addCriterion("TRAINING_BASE_NAME not between", value1, value2, "trainingBaseName");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("COMPANY is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("COMPANY is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("COMPANY =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("COMPANY <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("COMPANY >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANY >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("COMPANY <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("COMPANY <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("COMPANY like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("COMPANY not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("COMPANY in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("COMPANY not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("COMPANY between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("COMPANY not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdIsNull() {
            addCriterion("TRAINING_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdIsNotNull() {
            addCriterion("TRAINING_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdEqualTo(String value) {
            addCriterion("TRAINING_TYPE_ID =", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdNotEqualTo(String value) {
            addCriterion("TRAINING_TYPE_ID <>", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdGreaterThan(String value) {
            addCriterion("TRAINING_TYPE_ID >", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_TYPE_ID >=", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdLessThan(String value) {
            addCriterion("TRAINING_TYPE_ID <", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_TYPE_ID <=", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdLike(String value) {
            addCriterion("TRAINING_TYPE_ID like", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdNotLike(String value) {
            addCriterion("TRAINING_TYPE_ID not like", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdIn(List<String> values) {
            addCriterion("TRAINING_TYPE_ID in", values, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdNotIn(List<String> values) {
            addCriterion("TRAINING_TYPE_ID not in", values, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdBetween(String value1, String value2) {
            addCriterion("TRAINING_TYPE_ID between", value1, value2, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdNotBetween(String value1, String value2) {
            addCriterion("TRAINING_TYPE_ID not between", value1, value2, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameIsNull() {
            addCriterion("TRAINING_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameIsNotNull() {
            addCriterion("TRAINING_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameEqualTo(String value) {
            addCriterion("TRAINING_TYPE_NAME =", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameNotEqualTo(String value) {
            addCriterion("TRAINING_TYPE_NAME <>", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameGreaterThan(String value) {
            addCriterion("TRAINING_TYPE_NAME >", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_TYPE_NAME >=", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameLessThan(String value) {
            addCriterion("TRAINING_TYPE_NAME <", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_TYPE_NAME <=", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameLike(String value) {
            addCriterion("TRAINING_TYPE_NAME like", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameNotLike(String value) {
            addCriterion("TRAINING_TYPE_NAME not like", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameIn(List<String> values) {
            addCriterion("TRAINING_TYPE_NAME in", values, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameNotIn(List<String> values) {
            addCriterion("TRAINING_TYPE_NAME not in", values, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameBetween(String value1, String value2) {
            addCriterion("TRAINING_TYPE_NAME between", value1, value2, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameNotBetween(String value1, String value2) {
            addCriterion("TRAINING_TYPE_NAME not between", value1, value2, "trainingTypeName");
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

        public Criteria andDoctorTypeIdIsNull() {
            addCriterion("DOCTOR_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdIsNotNull() {
            addCriterion("DOCTOR_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID =", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID <>", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdGreaterThan(String value) {
            addCriterion("DOCTOR_TYPE_ID >", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID >=", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdLessThan(String value) {
            addCriterion("DOCTOR_TYPE_ID <", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID <=", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdLike(String value) {
            addCriterion("DOCTOR_TYPE_ID like", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotLike(String value) {
            addCriterion("DOCTOR_TYPE_ID not like", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdIn(List<String> values) {
            addCriterion("DOCTOR_TYPE_ID in", values, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotIn(List<String> values) {
            addCriterion("DOCTOR_TYPE_ID not in", values, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdBetween(String value1, String value2) {
            addCriterion("DOCTOR_TYPE_ID between", value1, value2, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_TYPE_ID not between", value1, value2, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameIsNull() {
            addCriterion("DOCTOR_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameIsNotNull() {
            addCriterion("DOCTOR_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_NAME =", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameNotEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_NAME <>", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameGreaterThan(String value) {
            addCriterion("DOCTOR_TYPE_NAME >", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_NAME >=", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameLessThan(String value) {
            addCriterion("DOCTOR_TYPE_NAME <", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_NAME <=", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameLike(String value) {
            addCriterion("DOCTOR_TYPE_NAME like", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameNotLike(String value) {
            addCriterion("DOCTOR_TYPE_NAME not like", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameIn(List<String> values) {
            addCriterion("DOCTOR_TYPE_NAME in", values, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameNotIn(List<String> values) {
            addCriterion("DOCTOR_TYPE_NAME not in", values, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_TYPE_NAME between", value1, value2, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_TYPE_NAME not between", value1, value2, "doctorTypeName");
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

        public Criteria andTrainingStartTimeIsNull() {
            addCriterion("TRAINING_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeIsNotNull() {
            addCriterion("TRAINING_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeEqualTo(String value) {
            addCriterion("TRAINING_START_TIME =", value, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeNotEqualTo(String value) {
            addCriterion("TRAINING_START_TIME <>", value, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeGreaterThan(String value) {
            addCriterion("TRAINING_START_TIME >", value, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_START_TIME >=", value, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeLessThan(String value) {
            addCriterion("TRAINING_START_TIME <", value, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_START_TIME <=", value, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeLike(String value) {
            addCriterion("TRAINING_START_TIME like", value, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeNotLike(String value) {
            addCriterion("TRAINING_START_TIME not like", value, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeIn(List<String> values) {
            addCriterion("TRAINING_START_TIME in", values, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeNotIn(List<String> values) {
            addCriterion("TRAINING_START_TIME not in", values, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeBetween(String value1, String value2) {
            addCriterion("TRAINING_START_TIME between", value1, value2, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeNotBetween(String value1, String value2) {
            addCriterion("TRAINING_START_TIME not between", value1, value2, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeIsNull() {
            addCriterion("TRAINING_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeIsNotNull() {
            addCriterion("TRAINING_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeEqualTo(String value) {
            addCriterion("TRAINING_END_TIME =", value, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeNotEqualTo(String value) {
            addCriterion("TRAINING_END_TIME <>", value, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeGreaterThan(String value) {
            addCriterion("TRAINING_END_TIME >", value, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_END_TIME >=", value, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeLessThan(String value) {
            addCriterion("TRAINING_END_TIME <", value, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_END_TIME <=", value, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeLike(String value) {
            addCriterion("TRAINING_END_TIME like", value, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeNotLike(String value) {
            addCriterion("TRAINING_END_TIME not like", value, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeIn(List<String> values) {
            addCriterion("TRAINING_END_TIME in", values, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeNotIn(List<String> values) {
            addCriterion("TRAINING_END_TIME not in", values, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeBetween(String value1, String value2) {
            addCriterion("TRAINING_END_TIME between", value1, value2, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeNotBetween(String value1, String value2) {
            addCriterion("TRAINING_END_TIME not between", value1, value2, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andPassTheoryAssessmentYearIsNull() {
            addCriterion("PASS_THEORY_ASSESSMENT_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andPassTheoryAssessmentYearIsNotNull() {
            addCriterion("PASS_THEORY_ASSESSMENT_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andPassTheoryAssessmentYearEqualTo(String value) {
            addCriterion("PASS_THEORY_ASSESSMENT_YEAR =", value, "passTheoryAssessmentYear");
            return (Criteria) this;
        }

        public Criteria andPassTheoryAssessmentYearNotEqualTo(String value) {
            addCriterion("PASS_THEORY_ASSESSMENT_YEAR <>", value, "passTheoryAssessmentYear");
            return (Criteria) this;
        }

        public Criteria andPassTheoryAssessmentYearGreaterThan(String value) {
            addCriterion("PASS_THEORY_ASSESSMENT_YEAR >", value, "passTheoryAssessmentYear");
            return (Criteria) this;
        }

        public Criteria andPassTheoryAssessmentYearGreaterThanOrEqualTo(String value) {
            addCriterion("PASS_THEORY_ASSESSMENT_YEAR >=", value, "passTheoryAssessmentYear");
            return (Criteria) this;
        }

        public Criteria andPassTheoryAssessmentYearLessThan(String value) {
            addCriterion("PASS_THEORY_ASSESSMENT_YEAR <", value, "passTheoryAssessmentYear");
            return (Criteria) this;
        }

        public Criteria andPassTheoryAssessmentYearLessThanOrEqualTo(String value) {
            addCriterion("PASS_THEORY_ASSESSMENT_YEAR <=", value, "passTheoryAssessmentYear");
            return (Criteria) this;
        }

        public Criteria andPassTheoryAssessmentYearLike(String value) {
            addCriterion("PASS_THEORY_ASSESSMENT_YEAR like", value, "passTheoryAssessmentYear");
            return (Criteria) this;
        }

        public Criteria andPassTheoryAssessmentYearNotLike(String value) {
            addCriterion("PASS_THEORY_ASSESSMENT_YEAR not like", value, "passTheoryAssessmentYear");
            return (Criteria) this;
        }

        public Criteria andPassTheoryAssessmentYearIn(List<String> values) {
            addCriterion("PASS_THEORY_ASSESSMENT_YEAR in", values, "passTheoryAssessmentYear");
            return (Criteria) this;
        }

        public Criteria andPassTheoryAssessmentYearNotIn(List<String> values) {
            addCriterion("PASS_THEORY_ASSESSMENT_YEAR not in", values, "passTheoryAssessmentYear");
            return (Criteria) this;
        }

        public Criteria andPassTheoryAssessmentYearBetween(String value1, String value2) {
            addCriterion("PASS_THEORY_ASSESSMENT_YEAR between", value1, value2, "passTheoryAssessmentYear");
            return (Criteria) this;
        }

        public Criteria andPassTheoryAssessmentYearNotBetween(String value1, String value2) {
            addCriterion("PASS_THEORY_ASSESSMENT_YEAR not between", value1, value2, "passTheoryAssessmentYear");
            return (Criteria) this;
        }

        public Criteria andIfPassAnnualAssessmentIsNull() {
            addCriterion("IF_PASS_ANNUAL_ASSESSMENT is null");
            return (Criteria) this;
        }

        public Criteria andIfPassAnnualAssessmentIsNotNull() {
            addCriterion("IF_PASS_ANNUAL_ASSESSMENT is not null");
            return (Criteria) this;
        }

        public Criteria andIfPassAnnualAssessmentEqualTo(String value) {
            addCriterion("IF_PASS_ANNUAL_ASSESSMENT =", value, "ifPassAnnualAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassAnnualAssessmentNotEqualTo(String value) {
            addCriterion("IF_PASS_ANNUAL_ASSESSMENT <>", value, "ifPassAnnualAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassAnnualAssessmentGreaterThan(String value) {
            addCriterion("IF_PASS_ANNUAL_ASSESSMENT >", value, "ifPassAnnualAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassAnnualAssessmentGreaterThanOrEqualTo(String value) {
            addCriterion("IF_PASS_ANNUAL_ASSESSMENT >=", value, "ifPassAnnualAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassAnnualAssessmentLessThan(String value) {
            addCriterion("IF_PASS_ANNUAL_ASSESSMENT <", value, "ifPassAnnualAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassAnnualAssessmentLessThanOrEqualTo(String value) {
            addCriterion("IF_PASS_ANNUAL_ASSESSMENT <=", value, "ifPassAnnualAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassAnnualAssessmentLike(String value) {
            addCriterion("IF_PASS_ANNUAL_ASSESSMENT like", value, "ifPassAnnualAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassAnnualAssessmentNotLike(String value) {
            addCriterion("IF_PASS_ANNUAL_ASSESSMENT not like", value, "ifPassAnnualAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassAnnualAssessmentIn(List<String> values) {
            addCriterion("IF_PASS_ANNUAL_ASSESSMENT in", values, "ifPassAnnualAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassAnnualAssessmentNotIn(List<String> values) {
            addCriterion("IF_PASS_ANNUAL_ASSESSMENT not in", values, "ifPassAnnualAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassAnnualAssessmentBetween(String value1, String value2) {
            addCriterion("IF_PASS_ANNUAL_ASSESSMENT between", value1, value2, "ifPassAnnualAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassAnnualAssessmentNotBetween(String value1, String value2) {
            addCriterion("IF_PASS_ANNUAL_ASSESSMENT not between", value1, value2, "ifPassAnnualAssessment");
            return (Criteria) this;
        }

        public Criteria andIfCompleteTrainingTimeIsNull() {
            addCriterion("IF_COMPLETE_TRAINING_TIME is null");
            return (Criteria) this;
        }

        public Criteria andIfCompleteTrainingTimeIsNotNull() {
            addCriterion("IF_COMPLETE_TRAINING_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andIfCompleteTrainingTimeEqualTo(String value) {
            addCriterion("IF_COMPLETE_TRAINING_TIME =", value, "ifCompleteTrainingTime");
            return (Criteria) this;
        }

        public Criteria andIfCompleteTrainingTimeNotEqualTo(String value) {
            addCriterion("IF_COMPLETE_TRAINING_TIME <>", value, "ifCompleteTrainingTime");
            return (Criteria) this;
        }

        public Criteria andIfCompleteTrainingTimeGreaterThan(String value) {
            addCriterion("IF_COMPLETE_TRAINING_TIME >", value, "ifCompleteTrainingTime");
            return (Criteria) this;
        }

        public Criteria andIfCompleteTrainingTimeGreaterThanOrEqualTo(String value) {
            addCriterion("IF_COMPLETE_TRAINING_TIME >=", value, "ifCompleteTrainingTime");
            return (Criteria) this;
        }

        public Criteria andIfCompleteTrainingTimeLessThan(String value) {
            addCriterion("IF_COMPLETE_TRAINING_TIME <", value, "ifCompleteTrainingTime");
            return (Criteria) this;
        }

        public Criteria andIfCompleteTrainingTimeLessThanOrEqualTo(String value) {
            addCriterion("IF_COMPLETE_TRAINING_TIME <=", value, "ifCompleteTrainingTime");
            return (Criteria) this;
        }

        public Criteria andIfCompleteTrainingTimeLike(String value) {
            addCriterion("IF_COMPLETE_TRAINING_TIME like", value, "ifCompleteTrainingTime");
            return (Criteria) this;
        }

        public Criteria andIfCompleteTrainingTimeNotLike(String value) {
            addCriterion("IF_COMPLETE_TRAINING_TIME not like", value, "ifCompleteTrainingTime");
            return (Criteria) this;
        }

        public Criteria andIfCompleteTrainingTimeIn(List<String> values) {
            addCriterion("IF_COMPLETE_TRAINING_TIME in", values, "ifCompleteTrainingTime");
            return (Criteria) this;
        }

        public Criteria andIfCompleteTrainingTimeNotIn(List<String> values) {
            addCriterion("IF_COMPLETE_TRAINING_TIME not in", values, "ifCompleteTrainingTime");
            return (Criteria) this;
        }

        public Criteria andIfCompleteTrainingTimeBetween(String value1, String value2) {
            addCriterion("IF_COMPLETE_TRAINING_TIME between", value1, value2, "ifCompleteTrainingTime");
            return (Criteria) this;
        }

        public Criteria andIfCompleteTrainingTimeNotBetween(String value1, String value2) {
            addCriterion("IF_COMPLETE_TRAINING_TIME not between", value1, value2, "ifCompleteTrainingTime");
            return (Criteria) this;
        }

        public Criteria andIfCompleteRegisterManualIsNull() {
            addCriterion("IF_COMPLETE_REGISTER_MANUAL is null");
            return (Criteria) this;
        }

        public Criteria andIfCompleteRegisterManualIsNotNull() {
            addCriterion("IF_COMPLETE_REGISTER_MANUAL is not null");
            return (Criteria) this;
        }

        public Criteria andIfCompleteRegisterManualEqualTo(String value) {
            addCriterion("IF_COMPLETE_REGISTER_MANUAL =", value, "ifCompleteRegisterManual");
            return (Criteria) this;
        }

        public Criteria andIfCompleteRegisterManualNotEqualTo(String value) {
            addCriterion("IF_COMPLETE_REGISTER_MANUAL <>", value, "ifCompleteRegisterManual");
            return (Criteria) this;
        }

        public Criteria andIfCompleteRegisterManualGreaterThan(String value) {
            addCriterion("IF_COMPLETE_REGISTER_MANUAL >", value, "ifCompleteRegisterManual");
            return (Criteria) this;
        }

        public Criteria andIfCompleteRegisterManualGreaterThanOrEqualTo(String value) {
            addCriterion("IF_COMPLETE_REGISTER_MANUAL >=", value, "ifCompleteRegisterManual");
            return (Criteria) this;
        }

        public Criteria andIfCompleteRegisterManualLessThan(String value) {
            addCriterion("IF_COMPLETE_REGISTER_MANUAL <", value, "ifCompleteRegisterManual");
            return (Criteria) this;
        }

        public Criteria andIfCompleteRegisterManualLessThanOrEqualTo(String value) {
            addCriterion("IF_COMPLETE_REGISTER_MANUAL <=", value, "ifCompleteRegisterManual");
            return (Criteria) this;
        }

        public Criteria andIfCompleteRegisterManualLike(String value) {
            addCriterion("IF_COMPLETE_REGISTER_MANUAL like", value, "ifCompleteRegisterManual");
            return (Criteria) this;
        }

        public Criteria andIfCompleteRegisterManualNotLike(String value) {
            addCriterion("IF_COMPLETE_REGISTER_MANUAL not like", value, "ifCompleteRegisterManual");
            return (Criteria) this;
        }

        public Criteria andIfCompleteRegisterManualIn(List<String> values) {
            addCriterion("IF_COMPLETE_REGISTER_MANUAL in", values, "ifCompleteRegisterManual");
            return (Criteria) this;
        }

        public Criteria andIfCompleteRegisterManualNotIn(List<String> values) {
            addCriterion("IF_COMPLETE_REGISTER_MANUAL not in", values, "ifCompleteRegisterManual");
            return (Criteria) this;
        }

        public Criteria andIfCompleteRegisterManualBetween(String value1, String value2) {
            addCriterion("IF_COMPLETE_REGISTER_MANUAL between", value1, value2, "ifCompleteRegisterManual");
            return (Criteria) this;
        }

        public Criteria andIfCompleteRegisterManualNotBetween(String value1, String value2) {
            addCriterion("IF_COMPLETE_REGISTER_MANUAL not between", value1, value2, "ifCompleteRegisterManual");
            return (Criteria) this;
        }

        public Criteria andIfPassDiscipleAssessmentIsNull() {
            addCriterion("IF_PASS_DISCIPLE_ASSESSMENT is null");
            return (Criteria) this;
        }

        public Criteria andIfPassDiscipleAssessmentIsNotNull() {
            addCriterion("IF_PASS_DISCIPLE_ASSESSMENT is not null");
            return (Criteria) this;
        }

        public Criteria andIfPassDiscipleAssessmentEqualTo(String value) {
            addCriterion("IF_PASS_DISCIPLE_ASSESSMENT =", value, "ifPassDiscipleAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassDiscipleAssessmentNotEqualTo(String value) {
            addCriterion("IF_PASS_DISCIPLE_ASSESSMENT <>", value, "ifPassDiscipleAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassDiscipleAssessmentGreaterThan(String value) {
            addCriterion("IF_PASS_DISCIPLE_ASSESSMENT >", value, "ifPassDiscipleAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassDiscipleAssessmentGreaterThanOrEqualTo(String value) {
            addCriterion("IF_PASS_DISCIPLE_ASSESSMENT >=", value, "ifPassDiscipleAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassDiscipleAssessmentLessThan(String value) {
            addCriterion("IF_PASS_DISCIPLE_ASSESSMENT <", value, "ifPassDiscipleAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassDiscipleAssessmentLessThanOrEqualTo(String value) {
            addCriterion("IF_PASS_DISCIPLE_ASSESSMENT <=", value, "ifPassDiscipleAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassDiscipleAssessmentLike(String value) {
            addCriterion("IF_PASS_DISCIPLE_ASSESSMENT like", value, "ifPassDiscipleAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassDiscipleAssessmentNotLike(String value) {
            addCriterion("IF_PASS_DISCIPLE_ASSESSMENT not like", value, "ifPassDiscipleAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassDiscipleAssessmentIn(List<String> values) {
            addCriterion("IF_PASS_DISCIPLE_ASSESSMENT in", values, "ifPassDiscipleAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassDiscipleAssessmentNotIn(List<String> values) {
            addCriterion("IF_PASS_DISCIPLE_ASSESSMENT not in", values, "ifPassDiscipleAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassDiscipleAssessmentBetween(String value1, String value2) {
            addCriterion("IF_PASS_DISCIPLE_ASSESSMENT between", value1, value2, "ifPassDiscipleAssessment");
            return (Criteria) this;
        }

        public Criteria andIfPassDiscipleAssessmentNotBetween(String value1, String value2) {
            addCriterion("IF_PASS_DISCIPLE_ASSESSMENT not between", value1, value2, "ifPassDiscipleAssessment");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusIdIsNull() {
            addCriterion("CURRENT_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusIdIsNotNull() {
            addCriterion("CURRENT_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusIdEqualTo(String value) {
            addCriterion("CURRENT_AUDIT_STATUS_ID =", value, "currentAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusIdNotEqualTo(String value) {
            addCriterion("CURRENT_AUDIT_STATUS_ID <>", value, "currentAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusIdGreaterThan(String value) {
            addCriterion("CURRENT_AUDIT_STATUS_ID >", value, "currentAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("CURRENT_AUDIT_STATUS_ID >=", value, "currentAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusIdLessThan(String value) {
            addCriterion("CURRENT_AUDIT_STATUS_ID <", value, "currentAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("CURRENT_AUDIT_STATUS_ID <=", value, "currentAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusIdLike(String value) {
            addCriterion("CURRENT_AUDIT_STATUS_ID like", value, "currentAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusIdNotLike(String value) {
            addCriterion("CURRENT_AUDIT_STATUS_ID not like", value, "currentAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusIdIn(List<String> values) {
            addCriterion("CURRENT_AUDIT_STATUS_ID in", values, "currentAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusIdNotIn(List<String> values) {
            addCriterion("CURRENT_AUDIT_STATUS_ID not in", values, "currentAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusIdBetween(String value1, String value2) {
            addCriterion("CURRENT_AUDIT_STATUS_ID between", value1, value2, "currentAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("CURRENT_AUDIT_STATUS_ID not between", value1, value2, "currentAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusNameIsNull() {
            addCriterion("CURRENT_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusNameIsNotNull() {
            addCriterion("CURRENT_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusNameEqualTo(String value) {
            addCriterion("CURRENT_AUDIT_STATUS_NAME =", value, "currentAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusNameNotEqualTo(String value) {
            addCriterion("CURRENT_AUDIT_STATUS_NAME <>", value, "currentAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusNameGreaterThan(String value) {
            addCriterion("CURRENT_AUDIT_STATUS_NAME >", value, "currentAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("CURRENT_AUDIT_STATUS_NAME >=", value, "currentAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusNameLessThan(String value) {
            addCriterion("CURRENT_AUDIT_STATUS_NAME <", value, "currentAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("CURRENT_AUDIT_STATUS_NAME <=", value, "currentAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusNameLike(String value) {
            addCriterion("CURRENT_AUDIT_STATUS_NAME like", value, "currentAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusNameNotLike(String value) {
            addCriterion("CURRENT_AUDIT_STATUS_NAME not like", value, "currentAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusNameIn(List<String> values) {
            addCriterion("CURRENT_AUDIT_STATUS_NAME in", values, "currentAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusNameNotIn(List<String> values) {
            addCriterion("CURRENT_AUDIT_STATUS_NAME not in", values, "currentAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusNameBetween(String value1, String value2) {
            addCriterion("CURRENT_AUDIT_STATUS_NAME between", value1, value2, "currentAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCurrentAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("CURRENT_AUDIT_STATUS_NAME not between", value1, value2, "currentAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberIsNull() {
            addCriterion("CERTIFICATE_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberIsNotNull() {
            addCriterion("CERTIFICATE_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberEqualTo(String value) {
            addCriterion("CERTIFICATE_NUMBER =", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberNotEqualTo(String value) {
            addCriterion("CERTIFICATE_NUMBER <>", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberGreaterThan(String value) {
            addCriterion("CERTIFICATE_NUMBER >", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_NUMBER >=", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberLessThan(String value) {
            addCriterion("CERTIFICATE_NUMBER <", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_NUMBER <=", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberLike(String value) {
            addCriterion("CERTIFICATE_NUMBER like", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberNotLike(String value) {
            addCriterion("CERTIFICATE_NUMBER not like", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberIn(List<String> values) {
            addCriterion("CERTIFICATE_NUMBER in", values, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberNotIn(List<String> values) {
            addCriterion("CERTIFICATE_NUMBER not in", values, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_NUMBER between", value1, value2, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_NUMBER not between", value1, value2, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateFlowIsNull() {
            addCriterion("CERTIFICATE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andCertificateFlowIsNotNull() {
            addCriterion("CERTIFICATE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateFlowEqualTo(String value) {
            addCriterion("CERTIFICATE_FLOW =", value, "certificateFlow");
            return (Criteria) this;
        }

        public Criteria andCertificateFlowNotEqualTo(String value) {
            addCriterion("CERTIFICATE_FLOW <>", value, "certificateFlow");
            return (Criteria) this;
        }

        public Criteria andCertificateFlowGreaterThan(String value) {
            addCriterion("CERTIFICATE_FLOW >", value, "certificateFlow");
            return (Criteria) this;
        }

        public Criteria andCertificateFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_FLOW >=", value, "certificateFlow");
            return (Criteria) this;
        }

        public Criteria andCertificateFlowLessThan(String value) {
            addCriterion("CERTIFICATE_FLOW <", value, "certificateFlow");
            return (Criteria) this;
        }

        public Criteria andCertificateFlowLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_FLOW <=", value, "certificateFlow");
            return (Criteria) this;
        }

        public Criteria andCertificateFlowLike(String value) {
            addCriterion("CERTIFICATE_FLOW like", value, "certificateFlow");
            return (Criteria) this;
        }

        public Criteria andCertificateFlowNotLike(String value) {
            addCriterion("CERTIFICATE_FLOW not like", value, "certificateFlow");
            return (Criteria) this;
        }

        public Criteria andCertificateFlowIn(List<String> values) {
            addCriterion("CERTIFICATE_FLOW in", values, "certificateFlow");
            return (Criteria) this;
        }

        public Criteria andCertificateFlowNotIn(List<String> values) {
            addCriterion("CERTIFICATE_FLOW not in", values, "certificateFlow");
            return (Criteria) this;
        }

        public Criteria andCertificateFlowBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_FLOW between", value1, value2, "certificateFlow");
            return (Criteria) this;
        }

        public Criteria andCertificateFlowNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_FLOW not between", value1, value2, "certificateFlow");
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