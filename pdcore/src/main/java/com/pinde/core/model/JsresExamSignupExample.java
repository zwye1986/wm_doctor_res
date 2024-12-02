package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class JsresExamSignupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JsresExamSignupExample() {
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

        public Criteria andSignupFlowIsNull() {
            addCriterion("SIGNUP_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSignupFlowIsNotNull() {
            addCriterion("SIGNUP_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSignupFlowEqualTo(String value) {
            addCriterion("SIGNUP_FLOW =", value, "signupFlow");
            return (Criteria) this;
        }

        public Criteria andSignupFlowNotEqualTo(String value) {
            addCriterion("SIGNUP_FLOW <>", value, "signupFlow");
            return (Criteria) this;
        }

        public Criteria andSignupFlowGreaterThan(String value) {
            addCriterion("SIGNUP_FLOW >", value, "signupFlow");
            return (Criteria) this;
        }

        public Criteria andSignupFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SIGNUP_FLOW >=", value, "signupFlow");
            return (Criteria) this;
        }

        public Criteria andSignupFlowLessThan(String value) {
            addCriterion("SIGNUP_FLOW <", value, "signupFlow");
            return (Criteria) this;
        }

        public Criteria andSignupFlowLessThanOrEqualTo(String value) {
            addCriterion("SIGNUP_FLOW <=", value, "signupFlow");
            return (Criteria) this;
        }

        public Criteria andSignupFlowLike(String value) {
            addCriterion("SIGNUP_FLOW like", value, "signupFlow");
            return (Criteria) this;
        }

        public Criteria andSignupFlowNotLike(String value) {
            addCriterion("SIGNUP_FLOW not like", value, "signupFlow");
            return (Criteria) this;
        }

        public Criteria andSignupFlowIn(List<String> values) {
            addCriterion("SIGNUP_FLOW in", values, "signupFlow");
            return (Criteria) this;
        }

        public Criteria andSignupFlowNotIn(List<String> values) {
            addCriterion("SIGNUP_FLOW not in", values, "signupFlow");
            return (Criteria) this;
        }

        public Criteria andSignupFlowBetween(String value1, String value2) {
            addCriterion("SIGNUP_FLOW between", value1, value2, "signupFlow");
            return (Criteria) this;
        }

        public Criteria andSignupFlowNotBetween(String value1, String value2) {
            addCriterion("SIGNUP_FLOW not between", value1, value2, "signupFlow");
            return (Criteria) this;
        }

        public Criteria andSignupYearIsNull() {
            addCriterion("SIGNUP_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andSignupYearIsNotNull() {
            addCriterion("SIGNUP_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andSignupYearEqualTo(String value) {
            addCriterion("SIGNUP_YEAR =", value, "signupYear");
            return (Criteria) this;
        }

        public Criteria andSignupYearNotEqualTo(String value) {
            addCriterion("SIGNUP_YEAR <>", value, "signupYear");
            return (Criteria) this;
        }

        public Criteria andSignupYearGreaterThan(String value) {
            addCriterion("SIGNUP_YEAR >", value, "signupYear");
            return (Criteria) this;
        }

        public Criteria andSignupYearGreaterThanOrEqualTo(String value) {
            addCriterion("SIGNUP_YEAR >=", value, "signupYear");
            return (Criteria) this;
        }

        public Criteria andSignupYearLessThan(String value) {
            addCriterion("SIGNUP_YEAR <", value, "signupYear");
            return (Criteria) this;
        }

        public Criteria andSignupYearLessThanOrEqualTo(String value) {
            addCriterion("SIGNUP_YEAR <=", value, "signupYear");
            return (Criteria) this;
        }

        public Criteria andSignupYearLike(String value) {
            addCriterion("SIGNUP_YEAR like", value, "signupYear");
            return (Criteria) this;
        }

        public Criteria andSignupYearNotLike(String value) {
            addCriterion("SIGNUP_YEAR not like", value, "signupYear");
            return (Criteria) this;
        }

        public Criteria andSignupYearIn(List<String> values) {
            addCriterion("SIGNUP_YEAR in", values, "signupYear");
            return (Criteria) this;
        }

        public Criteria andSignupYearNotIn(List<String> values) {
            addCriterion("SIGNUP_YEAR not in", values, "signupYear");
            return (Criteria) this;
        }

        public Criteria andSignupYearBetween(String value1, String value2) {
            addCriterion("SIGNUP_YEAR between", value1, value2, "signupYear");
            return (Criteria) this;
        }

        public Criteria andSignupYearNotBetween(String value1, String value2) {
            addCriterion("SIGNUP_YEAR not between", value1, value2, "signupYear");
            return (Criteria) this;
        }

        public Criteria andSignupTypeIdIsNull() {
            addCriterion("SIGNUP_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSignupTypeIdIsNotNull() {
            addCriterion("SIGNUP_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSignupTypeIdEqualTo(String value) {
            addCriterion("SIGNUP_TYPE_ID =", value, "signupTypeId");
            return (Criteria) this;
        }

        public Criteria andSignupTypeIdNotEqualTo(String value) {
            addCriterion("SIGNUP_TYPE_ID <>", value, "signupTypeId");
            return (Criteria) this;
        }

        public Criteria andSignupTypeIdGreaterThan(String value) {
            addCriterion("SIGNUP_TYPE_ID >", value, "signupTypeId");
            return (Criteria) this;
        }

        public Criteria andSignupTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SIGNUP_TYPE_ID >=", value, "signupTypeId");
            return (Criteria) this;
        }

        public Criteria andSignupTypeIdLessThan(String value) {
            addCriterion("SIGNUP_TYPE_ID <", value, "signupTypeId");
            return (Criteria) this;
        }

        public Criteria andSignupTypeIdLessThanOrEqualTo(String value) {
            addCriterion("SIGNUP_TYPE_ID <=", value, "signupTypeId");
            return (Criteria) this;
        }

        public Criteria andSignupTypeIdLike(String value) {
            addCriterion("SIGNUP_TYPE_ID like", value, "signupTypeId");
            return (Criteria) this;
        }

        public Criteria andSignupTypeIdNotLike(String value) {
            addCriterion("SIGNUP_TYPE_ID not like", value, "signupTypeId");
            return (Criteria) this;
        }

        public Criteria andSignupTypeIdIn(List<String> values) {
            addCriterion("SIGNUP_TYPE_ID in", values, "signupTypeId");
            return (Criteria) this;
        }

        public Criteria andSignupTypeIdNotIn(List<String> values) {
            addCriterion("SIGNUP_TYPE_ID not in", values, "signupTypeId");
            return (Criteria) this;
        }

        public Criteria andSignupTypeIdBetween(String value1, String value2) {
            addCriterion("SIGNUP_TYPE_ID between", value1, value2, "signupTypeId");
            return (Criteria) this;
        }

        public Criteria andSignupTypeIdNotBetween(String value1, String value2) {
            addCriterion("SIGNUP_TYPE_ID not between", value1, value2, "signupTypeId");
            return (Criteria) this;
        }

        public Criteria andSignupTypeNameIsNull() {
            addCriterion("SIGNUP_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSignupTypeNameIsNotNull() {
            addCriterion("SIGNUP_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSignupTypeNameEqualTo(String value) {
            addCriterion("SIGNUP_TYPE_NAME =", value, "signupTypeName");
            return (Criteria) this;
        }

        public Criteria andSignupTypeNameNotEqualTo(String value) {
            addCriterion("SIGNUP_TYPE_NAME <>", value, "signupTypeName");
            return (Criteria) this;
        }

        public Criteria andSignupTypeNameGreaterThan(String value) {
            addCriterion("SIGNUP_TYPE_NAME >", value, "signupTypeName");
            return (Criteria) this;
        }

        public Criteria andSignupTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SIGNUP_TYPE_NAME >=", value, "signupTypeName");
            return (Criteria) this;
        }

        public Criteria andSignupTypeNameLessThan(String value) {
            addCriterion("SIGNUP_TYPE_NAME <", value, "signupTypeName");
            return (Criteria) this;
        }

        public Criteria andSignupTypeNameLessThanOrEqualTo(String value) {
            addCriterion("SIGNUP_TYPE_NAME <=", value, "signupTypeName");
            return (Criteria) this;
        }

        public Criteria andSignupTypeNameLike(String value) {
            addCriterion("SIGNUP_TYPE_NAME like", value, "signupTypeName");
            return (Criteria) this;
        }

        public Criteria andSignupTypeNameNotLike(String value) {
            addCriterion("SIGNUP_TYPE_NAME not like", value, "signupTypeName");
            return (Criteria) this;
        }

        public Criteria andSignupTypeNameIn(List<String> values) {
            addCriterion("SIGNUP_TYPE_NAME in", values, "signupTypeName");
            return (Criteria) this;
        }

        public Criteria andSignupTypeNameNotIn(List<String> values) {
            addCriterion("SIGNUP_TYPE_NAME not in", values, "signupTypeName");
            return (Criteria) this;
        }

        public Criteria andSignupTypeNameBetween(String value1, String value2) {
            addCriterion("SIGNUP_TYPE_NAME between", value1, value2, "signupTypeName");
            return (Criteria) this;
        }

        public Criteria andSignupTypeNameNotBetween(String value1, String value2) {
            addCriterion("SIGNUP_TYPE_NAME not between", value1, value2, "signupTypeName");
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

        public Criteria andTrainingYearsIsNull() {
            addCriterion("TRAINING_YEARS is null");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsIsNotNull() {
            addCriterion("TRAINING_YEARS is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsEqualTo(String value) {
            addCriterion("TRAINING_YEARS =", value, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsNotEqualTo(String value) {
            addCriterion("TRAINING_YEARS <>", value, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsGreaterThan(String value) {
            addCriterion("TRAINING_YEARS >", value, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_YEARS >=", value, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsLessThan(String value) {
            addCriterion("TRAINING_YEARS <", value, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_YEARS <=", value, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsLike(String value) {
            addCriterion("TRAINING_YEARS like", value, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsNotLike(String value) {
            addCriterion("TRAINING_YEARS not like", value, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsIn(List<String> values) {
            addCriterion("TRAINING_YEARS in", values, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsNotIn(List<String> values) {
            addCriterion("TRAINING_YEARS not in", values, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsBetween(String value1, String value2) {
            addCriterion("TRAINING_YEARS between", value1, value2, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsNotBetween(String value1, String value2) {
            addCriterion("TRAINING_YEARS not between", value1, value2, "trainingYears");
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

        public Criteria andChangeSpeIdIsNull() {
            addCriterion("CHANGE_SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdIsNotNull() {
            addCriterion("CHANGE_SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdEqualTo(String value) {
            addCriterion("CHANGE_SPE_ID =", value, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdNotEqualTo(String value) {
            addCriterion("CHANGE_SPE_ID <>", value, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdGreaterThan(String value) {
            addCriterion("CHANGE_SPE_ID >", value, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("CHANGE_SPE_ID >=", value, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdLessThan(String value) {
            addCriterion("CHANGE_SPE_ID <", value, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdLessThanOrEqualTo(String value) {
            addCriterion("CHANGE_SPE_ID <=", value, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdLike(String value) {
            addCriterion("CHANGE_SPE_ID like", value, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdNotLike(String value) {
            addCriterion("CHANGE_SPE_ID not like", value, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdIn(List<String> values) {
            addCriterion("CHANGE_SPE_ID in", values, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdNotIn(List<String> values) {
            addCriterion("CHANGE_SPE_ID not in", values, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdBetween(String value1, String value2) {
            addCriterion("CHANGE_SPE_ID between", value1, value2, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeIdNotBetween(String value1, String value2) {
            addCriterion("CHANGE_SPE_ID not between", value1, value2, "changeSpeId");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameIsNull() {
            addCriterion("CHANGE_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameIsNotNull() {
            addCriterion("CHANGE_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameEqualTo(String value) {
            addCriterion("CHANGE_SPE_NAME =", value, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameNotEqualTo(String value) {
            addCriterion("CHANGE_SPE_NAME <>", value, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameGreaterThan(String value) {
            addCriterion("CHANGE_SPE_NAME >", value, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHANGE_SPE_NAME >=", value, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameLessThan(String value) {
            addCriterion("CHANGE_SPE_NAME <", value, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameLessThanOrEqualTo(String value) {
            addCriterion("CHANGE_SPE_NAME <=", value, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameLike(String value) {
            addCriterion("CHANGE_SPE_NAME like", value, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameNotLike(String value) {
            addCriterion("CHANGE_SPE_NAME not like", value, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameIn(List<String> values) {
            addCriterion("CHANGE_SPE_NAME in", values, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameNotIn(List<String> values) {
            addCriterion("CHANGE_SPE_NAME not in", values, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameBetween(String value1, String value2) {
            addCriterion("CHANGE_SPE_NAME between", value1, value2, "changeSpeName");
            return (Criteria) this;
        }

        public Criteria andChangeSpeNameNotBetween(String value1, String value2) {
            addCriterion("CHANGE_SPE_NAME not between", value1, value2, "changeSpeName");
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

        public Criteria andAuditInfoIsNull() {
            addCriterion("AUDIT_INFO is null");
            return (Criteria) this;
        }

        public Criteria andAuditInfoIsNotNull() {
            addCriterion("AUDIT_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andAuditInfoEqualTo(String value) {
            addCriterion("AUDIT_INFO =", value, "auditInfo");
            return (Criteria) this;
        }

        public Criteria andAuditInfoNotEqualTo(String value) {
            addCriterion("AUDIT_INFO <>", value, "auditInfo");
            return (Criteria) this;
        }

        public Criteria andAuditInfoGreaterThan(String value) {
            addCriterion("AUDIT_INFO >", value, "auditInfo");
            return (Criteria) this;
        }

        public Criteria andAuditInfoGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_INFO >=", value, "auditInfo");
            return (Criteria) this;
        }

        public Criteria andAuditInfoLessThan(String value) {
            addCriterion("AUDIT_INFO <", value, "auditInfo");
            return (Criteria) this;
        }

        public Criteria andAuditInfoLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_INFO <=", value, "auditInfo");
            return (Criteria) this;
        }

        public Criteria andAuditInfoLike(String value) {
            addCriterion("AUDIT_INFO like", value, "auditInfo");
            return (Criteria) this;
        }

        public Criteria andAuditInfoNotLike(String value) {
            addCriterion("AUDIT_INFO not like", value, "auditInfo");
            return (Criteria) this;
        }

        public Criteria andAuditInfoIn(List<String> values) {
            addCriterion("AUDIT_INFO in", values, "auditInfo");
            return (Criteria) this;
        }

        public Criteria andAuditInfoNotIn(List<String> values) {
            addCriterion("AUDIT_INFO not in", values, "auditInfo");
            return (Criteria) this;
        }

        public Criteria andAuditInfoBetween(String value1, String value2) {
            addCriterion("AUDIT_INFO between", value1, value2, "auditInfo");
            return (Criteria) this;
        }

        public Criteria andAuditInfoNotBetween(String value1, String value2) {
            addCriterion("AUDIT_INFO not between", value1, value2, "auditInfo");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowIsNull() {
            addCriterion("AUDIT_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowIsNotNull() {
            addCriterion("AUDIT_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW =", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW <>", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowGreaterThan(String value) {
            addCriterion("AUDIT_USER_FLOW >", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW >=", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowLessThan(String value) {
            addCriterion("AUDIT_USER_FLOW <", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW <=", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowLike(String value) {
            addCriterion("AUDIT_USER_FLOW like", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotLike(String value) {
            addCriterion("AUDIT_USER_FLOW not like", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowIn(List<String> values) {
            addCriterion("AUDIT_USER_FLOW in", values, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotIn(List<String> values) {
            addCriterion("AUDIT_USER_FLOW not in", values, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_FLOW between", value1, value2, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_FLOW not between", value1, value2, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIsNull() {
            addCriterion("AUDIT_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIsNotNull() {
            addCriterion("AUDIT_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME =", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME <>", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameGreaterThan(String value) {
            addCriterion("AUDIT_USER_NAME >", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME >=", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLessThan(String value) {
            addCriterion("AUDIT_USER_NAME <", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME <=", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLike(String value) {
            addCriterion("AUDIT_USER_NAME like", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotLike(String value) {
            addCriterion("AUDIT_USER_NAME not like", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIn(List<String> values) {
            addCriterion("AUDIT_USER_NAME in", values, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotIn(List<String> values) {
            addCriterion("AUDIT_USER_NAME not in", values, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_NAME between", value1, value2, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_NAME not between", value1, value2, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNull() {
            addCriterion("AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNotNull() {
            addCriterion("AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeEqualTo(String value) {
            addCriterion("AUDIT_TIME =", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotEqualTo(String value) {
            addCriterion("AUDIT_TIME <>", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThan(String value) {
            addCriterion("AUDIT_TIME >", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_TIME >=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThan(String value) {
            addCriterion("AUDIT_TIME <", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_TIME <=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLike(String value) {
            addCriterion("AUDIT_TIME like", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotLike(String value) {
            addCriterion("AUDIT_TIME not like", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIn(List<String> values) {
            addCriterion("AUDIT_TIME in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotIn(List<String> values) {
            addCriterion("AUDIT_TIME not in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeBetween(String value1, String value2) {
            addCriterion("AUDIT_TIME between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotBetween(String value1, String value2) {
            addCriterion("AUDIT_TIME not between", value1, value2, "auditTime");
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