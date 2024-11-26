package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class DoctorTeacherRecruitBatchExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DoctorTeacherRecruitBatchExample() {
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

        public Criteria andRefRecordFlowIsNull() {
            addCriterion("REF_RECORD_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowIsNotNull() {
            addCriterion("REF_RECORD_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowEqualTo(String value) {
            addCriterion("REF_RECORD_FLOW =", value, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowNotEqualTo(String value) {
            addCriterion("REF_RECORD_FLOW <>", value, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowGreaterThan(String value) {
            addCriterion("REF_RECORD_FLOW >", value, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowGreaterThanOrEqualTo(String value) {
            addCriterion("REF_RECORD_FLOW >=", value, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowLessThan(String value) {
            addCriterion("REF_RECORD_FLOW <", value, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowLessThanOrEqualTo(String value) {
            addCriterion("REF_RECORD_FLOW <=", value, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowLike(String value) {
            addCriterion("REF_RECORD_FLOW like", value, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowNotLike(String value) {
            addCriterion("REF_RECORD_FLOW not like", value, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowIn(List<String> values) {
            addCriterion("REF_RECORD_FLOW in", values, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowNotIn(List<String> values) {
            addCriterion("REF_RECORD_FLOW not in", values, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowBetween(String value1, String value2) {
            addCriterion("REF_RECORD_FLOW between", value1, value2, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowNotBetween(String value1, String value2) {
            addCriterion("REF_RECORD_FLOW not between", value1, value2, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowIsNull() {
            addCriterion("USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andUserFlowIsNotNull() {
            addCriterion("USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andUserFlowEqualTo(String value) {
            addCriterion("USER_FLOW =", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotEqualTo(String value) {
            addCriterion("USER_FLOW <>", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowGreaterThan(String value) {
            addCriterion("USER_FLOW >", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("USER_FLOW >=", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLessThan(String value) {
            addCriterion("USER_FLOW <", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLessThanOrEqualTo(String value) {
            addCriterion("USER_FLOW <=", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLike(String value) {
            addCriterion("USER_FLOW like", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotLike(String value) {
            addCriterion("USER_FLOW not like", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowIn(List<String> values) {
            addCriterion("USER_FLOW in", values, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotIn(List<String> values) {
            addCriterion("USER_FLOW not in", values, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowBetween(String value1, String value2) {
            addCriterion("USER_FLOW between", value1, value2, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotBetween(String value1, String value2) {
            addCriterion("USER_FLOW not between", value1, value2, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
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

        public Criteria andRecruitYearIsNull() {
            addCriterion("RECRUIT_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andRecruitYearIsNotNull() {
            addCriterion("RECRUIT_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitYearEqualTo(String value) {
            addCriterion("RECRUIT_YEAR =", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearNotEqualTo(String value) {
            addCriterion("RECRUIT_YEAR <>", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearGreaterThan(String value) {
            addCriterion("RECRUIT_YEAR >", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_YEAR >=", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearLessThan(String value) {
            addCriterion("RECRUIT_YEAR <", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_YEAR <=", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearLike(String value) {
            addCriterion("RECRUIT_YEAR like", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearNotLike(String value) {
            addCriterion("RECRUIT_YEAR not like", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearIn(List<String> values) {
            addCriterion("RECRUIT_YEAR in", values, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearNotIn(List<String> values) {
            addCriterion("RECRUIT_YEAR not in", values, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearBetween(String value1, String value2) {
            addCriterion("RECRUIT_YEAR between", value1, value2, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_YEAR not between", value1, value2, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdIsNull() {
            addCriterion("DEGREE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdIsNotNull() {
            addCriterion("DEGREE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdEqualTo(String value) {
            addCriterion("DEGREE_TYPE_ID =", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdNotEqualTo(String value) {
            addCriterion("DEGREE_TYPE_ID <>", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdGreaterThan(String value) {
            addCriterion("DEGREE_TYPE_ID >", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_TYPE_ID >=", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdLessThan(String value) {
            addCriterion("DEGREE_TYPE_ID <", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_TYPE_ID <=", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdLike(String value) {
            addCriterion("DEGREE_TYPE_ID like", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdNotLike(String value) {
            addCriterion("DEGREE_TYPE_ID not like", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdIn(List<String> values) {
            addCriterion("DEGREE_TYPE_ID in", values, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdNotIn(List<String> values) {
            addCriterion("DEGREE_TYPE_ID not in", values, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdBetween(String value1, String value2) {
            addCriterion("DEGREE_TYPE_ID between", value1, value2, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdNotBetween(String value1, String value2) {
            addCriterion("DEGREE_TYPE_ID not between", value1, value2, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameIsNull() {
            addCriterion("DEGREE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameIsNotNull() {
            addCriterion("DEGREE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameEqualTo(String value) {
            addCriterion("DEGREE_TYPE_NAME =", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameNotEqualTo(String value) {
            addCriterion("DEGREE_TYPE_NAME <>", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameGreaterThan(String value) {
            addCriterion("DEGREE_TYPE_NAME >", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_TYPE_NAME >=", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameLessThan(String value) {
            addCriterion("DEGREE_TYPE_NAME <", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_TYPE_NAME <=", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameLike(String value) {
            addCriterion("DEGREE_TYPE_NAME like", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameNotLike(String value) {
            addCriterion("DEGREE_TYPE_NAME not like", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameIn(List<String> values) {
            addCriterion("DEGREE_TYPE_NAME in", values, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameNotIn(List<String> values) {
            addCriterion("DEGREE_TYPE_NAME not in", values, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameBetween(String value1, String value2) {
            addCriterion("DEGREE_TYPE_NAME between", value1, value2, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameNotBetween(String value1, String value2) {
            addCriterion("DEGREE_TYPE_NAME not between", value1, value2, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeIdIsNull() {
            addCriterion("RECRUIT_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeIdIsNotNull() {
            addCriterion("RECRUIT_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeIdEqualTo(String value) {
            addCriterion("RECRUIT_TYPE_ID =", value, "recruitTypeId");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeIdNotEqualTo(String value) {
            addCriterion("RECRUIT_TYPE_ID <>", value, "recruitTypeId");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeIdGreaterThan(String value) {
            addCriterion("RECRUIT_TYPE_ID >", value, "recruitTypeId");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_TYPE_ID >=", value, "recruitTypeId");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeIdLessThan(String value) {
            addCriterion("RECRUIT_TYPE_ID <", value, "recruitTypeId");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeIdLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_TYPE_ID <=", value, "recruitTypeId");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeIdLike(String value) {
            addCriterion("RECRUIT_TYPE_ID like", value, "recruitTypeId");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeIdNotLike(String value) {
            addCriterion("RECRUIT_TYPE_ID not like", value, "recruitTypeId");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeIdIn(List<String> values) {
            addCriterion("RECRUIT_TYPE_ID in", values, "recruitTypeId");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeIdNotIn(List<String> values) {
            addCriterion("RECRUIT_TYPE_ID not in", values, "recruitTypeId");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeIdBetween(String value1, String value2) {
            addCriterion("RECRUIT_TYPE_ID between", value1, value2, "recruitTypeId");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeIdNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_TYPE_ID not between", value1, value2, "recruitTypeId");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNameIsNull() {
            addCriterion("RECRUIT_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNameIsNotNull() {
            addCriterion("RECRUIT_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNameEqualTo(String value) {
            addCriterion("RECRUIT_TYPE_NAME =", value, "recruitTypeName");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNameNotEqualTo(String value) {
            addCriterion("RECRUIT_TYPE_NAME <>", value, "recruitTypeName");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNameGreaterThan(String value) {
            addCriterion("RECRUIT_TYPE_NAME >", value, "recruitTypeName");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_TYPE_NAME >=", value, "recruitTypeName");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNameLessThan(String value) {
            addCriterion("RECRUIT_TYPE_NAME <", value, "recruitTypeName");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNameLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_TYPE_NAME <=", value, "recruitTypeName");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNameLike(String value) {
            addCriterion("RECRUIT_TYPE_NAME like", value, "recruitTypeName");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNameNotLike(String value) {
            addCriterion("RECRUIT_TYPE_NAME not like", value, "recruitTypeName");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNameIn(List<String> values) {
            addCriterion("RECRUIT_TYPE_NAME in", values, "recruitTypeName");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNameNotIn(List<String> values) {
            addCriterion("RECRUIT_TYPE_NAME not in", values, "recruitTypeName");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNameBetween(String value1, String value2) {
            addCriterion("RECRUIT_TYPE_NAME between", value1, value2, "recruitTypeName");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNameNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_TYPE_NAME not between", value1, value2, "recruitTypeName");
            return (Criteria) this;
        }

        public Criteria andResearchAreaIdIsNull() {
            addCriterion("RESEARCH_AREA_ID is null");
            return (Criteria) this;
        }

        public Criteria andResearchAreaIdIsNotNull() {
            addCriterion("RESEARCH_AREA_ID is not null");
            return (Criteria) this;
        }

        public Criteria andResearchAreaIdEqualTo(String value) {
            addCriterion("RESEARCH_AREA_ID =", value, "researchAreaId");
            return (Criteria) this;
        }

        public Criteria andResearchAreaIdNotEqualTo(String value) {
            addCriterion("RESEARCH_AREA_ID <>", value, "researchAreaId");
            return (Criteria) this;
        }

        public Criteria andResearchAreaIdGreaterThan(String value) {
            addCriterion("RESEARCH_AREA_ID >", value, "researchAreaId");
            return (Criteria) this;
        }

        public Criteria andResearchAreaIdGreaterThanOrEqualTo(String value) {
            addCriterion("RESEARCH_AREA_ID >=", value, "researchAreaId");
            return (Criteria) this;
        }

        public Criteria andResearchAreaIdLessThan(String value) {
            addCriterion("RESEARCH_AREA_ID <", value, "researchAreaId");
            return (Criteria) this;
        }

        public Criteria andResearchAreaIdLessThanOrEqualTo(String value) {
            addCriterion("RESEARCH_AREA_ID <=", value, "researchAreaId");
            return (Criteria) this;
        }

        public Criteria andResearchAreaIdLike(String value) {
            addCriterion("RESEARCH_AREA_ID like", value, "researchAreaId");
            return (Criteria) this;
        }

        public Criteria andResearchAreaIdNotLike(String value) {
            addCriterion("RESEARCH_AREA_ID not like", value, "researchAreaId");
            return (Criteria) this;
        }

        public Criteria andResearchAreaIdIn(List<String> values) {
            addCriterion("RESEARCH_AREA_ID in", values, "researchAreaId");
            return (Criteria) this;
        }

        public Criteria andResearchAreaIdNotIn(List<String> values) {
            addCriterion("RESEARCH_AREA_ID not in", values, "researchAreaId");
            return (Criteria) this;
        }

        public Criteria andResearchAreaIdBetween(String value1, String value2) {
            addCriterion("RESEARCH_AREA_ID between", value1, value2, "researchAreaId");
            return (Criteria) this;
        }

        public Criteria andResearchAreaIdNotBetween(String value1, String value2) {
            addCriterion("RESEARCH_AREA_ID not between", value1, value2, "researchAreaId");
            return (Criteria) this;
        }

        public Criteria andResearchAreaNameIsNull() {
            addCriterion("RESEARCH_AREA_NAME is null");
            return (Criteria) this;
        }

        public Criteria andResearchAreaNameIsNotNull() {
            addCriterion("RESEARCH_AREA_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andResearchAreaNameEqualTo(String value) {
            addCriterion("RESEARCH_AREA_NAME =", value, "researchAreaName");
            return (Criteria) this;
        }

        public Criteria andResearchAreaNameNotEqualTo(String value) {
            addCriterion("RESEARCH_AREA_NAME <>", value, "researchAreaName");
            return (Criteria) this;
        }

        public Criteria andResearchAreaNameGreaterThan(String value) {
            addCriterion("RESEARCH_AREA_NAME >", value, "researchAreaName");
            return (Criteria) this;
        }

        public Criteria andResearchAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("RESEARCH_AREA_NAME >=", value, "researchAreaName");
            return (Criteria) this;
        }

        public Criteria andResearchAreaNameLessThan(String value) {
            addCriterion("RESEARCH_AREA_NAME <", value, "researchAreaName");
            return (Criteria) this;
        }

        public Criteria andResearchAreaNameLessThanOrEqualTo(String value) {
            addCriterion("RESEARCH_AREA_NAME <=", value, "researchAreaName");
            return (Criteria) this;
        }

        public Criteria andResearchAreaNameLike(String value) {
            addCriterion("RESEARCH_AREA_NAME like", value, "researchAreaName");
            return (Criteria) this;
        }

        public Criteria andResearchAreaNameNotLike(String value) {
            addCriterion("RESEARCH_AREA_NAME not like", value, "researchAreaName");
            return (Criteria) this;
        }

        public Criteria andResearchAreaNameIn(List<String> values) {
            addCriterion("RESEARCH_AREA_NAME in", values, "researchAreaName");
            return (Criteria) this;
        }

        public Criteria andResearchAreaNameNotIn(List<String> values) {
            addCriterion("RESEARCH_AREA_NAME not in", values, "researchAreaName");
            return (Criteria) this;
        }

        public Criteria andResearchAreaNameBetween(String value1, String value2) {
            addCriterion("RESEARCH_AREA_NAME between", value1, value2, "researchAreaName");
            return (Criteria) this;
        }

        public Criteria andResearchAreaNameNotBetween(String value1, String value2) {
            addCriterion("RESEARCH_AREA_NAME not between", value1, value2, "researchAreaName");
            return (Criteria) this;
        }

        public Criteria andRecruitBatchIsNull() {
            addCriterion("RECRUIT_BATCH is null");
            return (Criteria) this;
        }

        public Criteria andRecruitBatchIsNotNull() {
            addCriterion("RECRUIT_BATCH is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitBatchEqualTo(String value) {
            addCriterion("RECRUIT_BATCH =", value, "recruitBatch");
            return (Criteria) this;
        }

        public Criteria andRecruitBatchNotEqualTo(String value) {
            addCriterion("RECRUIT_BATCH <>", value, "recruitBatch");
            return (Criteria) this;
        }

        public Criteria andRecruitBatchGreaterThan(String value) {
            addCriterion("RECRUIT_BATCH >", value, "recruitBatch");
            return (Criteria) this;
        }

        public Criteria andRecruitBatchGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_BATCH >=", value, "recruitBatch");
            return (Criteria) this;
        }

        public Criteria andRecruitBatchLessThan(String value) {
            addCriterion("RECRUIT_BATCH <", value, "recruitBatch");
            return (Criteria) this;
        }

        public Criteria andRecruitBatchLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_BATCH <=", value, "recruitBatch");
            return (Criteria) this;
        }

        public Criteria andRecruitBatchLike(String value) {
            addCriterion("RECRUIT_BATCH like", value, "recruitBatch");
            return (Criteria) this;
        }

        public Criteria andRecruitBatchNotLike(String value) {
            addCriterion("RECRUIT_BATCH not like", value, "recruitBatch");
            return (Criteria) this;
        }

        public Criteria andRecruitBatchIn(List<String> values) {
            addCriterion("RECRUIT_BATCH in", values, "recruitBatch");
            return (Criteria) this;
        }

        public Criteria andRecruitBatchNotIn(List<String> values) {
            addCriterion("RECRUIT_BATCH not in", values, "recruitBatch");
            return (Criteria) this;
        }

        public Criteria andRecruitBatchBetween(String value1, String value2) {
            addCriterion("RECRUIT_BATCH between", value1, value2, "recruitBatch");
            return (Criteria) this;
        }

        public Criteria andRecruitBatchNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_BATCH not between", value1, value2, "recruitBatch");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIsNull() {
            addCriterion("AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIsNotNull() {
            addCriterion("AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID =", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID <>", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdGreaterThan(String value) {
            addCriterion("AUDIT_STATUS_ID >", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID >=", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLessThan(String value) {
            addCriterion("AUDIT_STATUS_ID <", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID <=", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLike(String value) {
            addCriterion("AUDIT_STATUS_ID like", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotLike(String value) {
            addCriterion("AUDIT_STATUS_ID not like", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIn(List<String> values) {
            addCriterion("AUDIT_STATUS_ID in", values, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotIn(List<String> values) {
            addCriterion("AUDIT_STATUS_ID not in", values, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_ID between", value1, value2, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_ID not between", value1, value2, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIsNull() {
            addCriterion("AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIsNotNull() {
            addCriterion("AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME =", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME <>", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameGreaterThan(String value) {
            addCriterion("AUDIT_STATUS_NAME >", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME >=", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLessThan(String value) {
            addCriterion("AUDIT_STATUS_NAME <", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME <=", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLike(String value) {
            addCriterion("AUDIT_STATUS_NAME like", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotLike(String value) {
            addCriterion("AUDIT_STATUS_NAME not like", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIn(List<String> values) {
            addCriterion("AUDIT_STATUS_NAME in", values, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotIn(List<String> values) {
            addCriterion("AUDIT_STATUS_NAME not in", values, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_NAME between", value1, value2, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_NAME not between", value1, value2, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditMemoIsNull() {
            addCriterion("ORG_AUDIT_MEMO is null");
            return (Criteria) this;
        }

        public Criteria andOrgAuditMemoIsNotNull() {
            addCriterion("ORG_AUDIT_MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAuditMemoEqualTo(String value) {
            addCriterion("ORG_AUDIT_MEMO =", value, "orgAuditMemo");
            return (Criteria) this;
        }

        public Criteria andOrgAuditMemoNotEqualTo(String value) {
            addCriterion("ORG_AUDIT_MEMO <>", value, "orgAuditMemo");
            return (Criteria) this;
        }

        public Criteria andOrgAuditMemoGreaterThan(String value) {
            addCriterion("ORG_AUDIT_MEMO >", value, "orgAuditMemo");
            return (Criteria) this;
        }

        public Criteria andOrgAuditMemoGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_AUDIT_MEMO >=", value, "orgAuditMemo");
            return (Criteria) this;
        }

        public Criteria andOrgAuditMemoLessThan(String value) {
            addCriterion("ORG_AUDIT_MEMO <", value, "orgAuditMemo");
            return (Criteria) this;
        }

        public Criteria andOrgAuditMemoLessThanOrEqualTo(String value) {
            addCriterion("ORG_AUDIT_MEMO <=", value, "orgAuditMemo");
            return (Criteria) this;
        }

        public Criteria andOrgAuditMemoLike(String value) {
            addCriterion("ORG_AUDIT_MEMO like", value, "orgAuditMemo");
            return (Criteria) this;
        }

        public Criteria andOrgAuditMemoNotLike(String value) {
            addCriterion("ORG_AUDIT_MEMO not like", value, "orgAuditMemo");
            return (Criteria) this;
        }

        public Criteria andOrgAuditMemoIn(List<String> values) {
            addCriterion("ORG_AUDIT_MEMO in", values, "orgAuditMemo");
            return (Criteria) this;
        }

        public Criteria andOrgAuditMemoNotIn(List<String> values) {
            addCriterion("ORG_AUDIT_MEMO not in", values, "orgAuditMemo");
            return (Criteria) this;
        }

        public Criteria andOrgAuditMemoBetween(String value1, String value2) {
            addCriterion("ORG_AUDIT_MEMO between", value1, value2, "orgAuditMemo");
            return (Criteria) this;
        }

        public Criteria andOrgAuditMemoNotBetween(String value1, String value2) {
            addCriterion("ORG_AUDIT_MEMO not between", value1, value2, "orgAuditMemo");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditMemoIsNull() {
            addCriterion("SCHOOL_AUDIT_MEMO is null");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditMemoIsNotNull() {
            addCriterion("SCHOOL_AUDIT_MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditMemoEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_MEMO =", value, "schoolAuditMemo");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditMemoNotEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_MEMO <>", value, "schoolAuditMemo");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditMemoGreaterThan(String value) {
            addCriterion("SCHOOL_AUDIT_MEMO >", value, "schoolAuditMemo");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditMemoGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_MEMO >=", value, "schoolAuditMemo");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditMemoLessThan(String value) {
            addCriterion("SCHOOL_AUDIT_MEMO <", value, "schoolAuditMemo");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditMemoLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_MEMO <=", value, "schoolAuditMemo");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditMemoLike(String value) {
            addCriterion("SCHOOL_AUDIT_MEMO like", value, "schoolAuditMemo");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditMemoNotLike(String value) {
            addCriterion("SCHOOL_AUDIT_MEMO not like", value, "schoolAuditMemo");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditMemoIn(List<String> values) {
            addCriterion("SCHOOL_AUDIT_MEMO in", values, "schoolAuditMemo");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditMemoNotIn(List<String> values) {
            addCriterion("SCHOOL_AUDIT_MEMO not in", values, "schoolAuditMemo");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditMemoBetween(String value1, String value2) {
            addCriterion("SCHOOL_AUDIT_MEMO between", value1, value2, "schoolAuditMemo");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditMemoNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_AUDIT_MEMO not between", value1, value2, "schoolAuditMemo");
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

        public Criteria andUserIdNoIsNull() {
            addCriterion("USER_ID_NO is null");
            return (Criteria) this;
        }

        public Criteria andUserIdNoIsNotNull() {
            addCriterion("USER_ID_NO is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdNoEqualTo(String value) {
            addCriterion("USER_ID_NO =", value, "userIdNo");
            return (Criteria) this;
        }

        public Criteria andUserIdNoNotEqualTo(String value) {
            addCriterion("USER_ID_NO <>", value, "userIdNo");
            return (Criteria) this;
        }

        public Criteria andUserIdNoGreaterThan(String value) {
            addCriterion("USER_ID_NO >", value, "userIdNo");
            return (Criteria) this;
        }

        public Criteria andUserIdNoGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID_NO >=", value, "userIdNo");
            return (Criteria) this;
        }

        public Criteria andUserIdNoLessThan(String value) {
            addCriterion("USER_ID_NO <", value, "userIdNo");
            return (Criteria) this;
        }

        public Criteria andUserIdNoLessThanOrEqualTo(String value) {
            addCriterion("USER_ID_NO <=", value, "userIdNo");
            return (Criteria) this;
        }

        public Criteria andUserIdNoLike(String value) {
            addCriterion("USER_ID_NO like", value, "userIdNo");
            return (Criteria) this;
        }

        public Criteria andUserIdNoNotLike(String value) {
            addCriterion("USER_ID_NO not like", value, "userIdNo");
            return (Criteria) this;
        }

        public Criteria andUserIdNoIn(List<String> values) {
            addCriterion("USER_ID_NO in", values, "userIdNo");
            return (Criteria) this;
        }

        public Criteria andUserIdNoNotIn(List<String> values) {
            addCriterion("USER_ID_NO not in", values, "userIdNo");
            return (Criteria) this;
        }

        public Criteria andUserIdNoBetween(String value1, String value2) {
            addCriterion("USER_ID_NO between", value1, value2, "userIdNo");
            return (Criteria) this;
        }

        public Criteria andUserIdNoNotBetween(String value1, String value2) {
            addCriterion("USER_ID_NO not between", value1, value2, "userIdNo");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusIdIsNull() {
            addCriterion("SCHOOL_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusIdIsNotNull() {
            addCriterion("SCHOOL_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusIdEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_STATUS_ID =", value, "schoolAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusIdNotEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_STATUS_ID <>", value, "schoolAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusIdGreaterThan(String value) {
            addCriterion("SCHOOL_AUDIT_STATUS_ID >", value, "schoolAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_STATUS_ID >=", value, "schoolAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusIdLessThan(String value) {
            addCriterion("SCHOOL_AUDIT_STATUS_ID <", value, "schoolAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_STATUS_ID <=", value, "schoolAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusIdLike(String value) {
            addCriterion("SCHOOL_AUDIT_STATUS_ID like", value, "schoolAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusIdNotLike(String value) {
            addCriterion("SCHOOL_AUDIT_STATUS_ID not like", value, "schoolAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusIdIn(List<String> values) {
            addCriterion("SCHOOL_AUDIT_STATUS_ID in", values, "schoolAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusIdNotIn(List<String> values) {
            addCriterion("SCHOOL_AUDIT_STATUS_ID not in", values, "schoolAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusIdBetween(String value1, String value2) {
            addCriterion("SCHOOL_AUDIT_STATUS_ID between", value1, value2, "schoolAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_AUDIT_STATUS_ID not between", value1, value2, "schoolAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusNameIsNull() {
            addCriterion("SCHOOL_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusNameIsNotNull() {
            addCriterion("SCHOOL_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusNameEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_STATUS_NAME =", value, "schoolAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusNameNotEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_STATUS_NAME <>", value, "schoolAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusNameGreaterThan(String value) {
            addCriterion("SCHOOL_AUDIT_STATUS_NAME >", value, "schoolAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_STATUS_NAME >=", value, "schoolAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusNameLessThan(String value) {
            addCriterion("SCHOOL_AUDIT_STATUS_NAME <", value, "schoolAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_STATUS_NAME <=", value, "schoolAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusNameLike(String value) {
            addCriterion("SCHOOL_AUDIT_STATUS_NAME like", value, "schoolAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusNameNotLike(String value) {
            addCriterion("SCHOOL_AUDIT_STATUS_NAME not like", value, "schoolAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusNameIn(List<String> values) {
            addCriterion("SCHOOL_AUDIT_STATUS_NAME in", values, "schoolAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusNameNotIn(List<String> values) {
            addCriterion("SCHOOL_AUDIT_STATUS_NAME not in", values, "schoolAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusNameBetween(String value1, String value2) {
            addCriterion("SCHOOL_AUDIT_STATUS_NAME between", value1, value2, "schoolAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_AUDIT_STATUS_NAME not between", value1, value2, "schoolAuditStatusName");
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