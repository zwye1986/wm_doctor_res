package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ResTeachPlanDoctorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResTeachPlanDoctorExample() {
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

        public Criteria andPlanFlowIsNull() {
            addCriterion("PLAN_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPlanFlowIsNotNull() {
            addCriterion("PLAN_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPlanFlowEqualTo(String value) {
            addCriterion("PLAN_FLOW =", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotEqualTo(String value) {
            addCriterion("PLAN_FLOW <>", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowGreaterThan(String value) {
            addCriterion("PLAN_FLOW >", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_FLOW >=", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowLessThan(String value) {
            addCriterion("PLAN_FLOW <", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowLessThanOrEqualTo(String value) {
            addCriterion("PLAN_FLOW <=", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowLike(String value) {
            addCriterion("PLAN_FLOW like", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotLike(String value) {
            addCriterion("PLAN_FLOW not like", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowIn(List<String> values) {
            addCriterion("PLAN_FLOW in", values, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotIn(List<String> values) {
            addCriterion("PLAN_FLOW not in", values, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowBetween(String value1, String value2) {
            addCriterion("PLAN_FLOW between", value1, value2, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotBetween(String value1, String value2) {
            addCriterion("PLAN_FLOW not between", value1, value2, "planFlow");
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

        public Criteria andDoctorCodeIsNull() {
            addCriterion("DOCTOR_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeIsNotNull() {
            addCriterion("DOCTOR_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeEqualTo(String value) {
            addCriterion("DOCTOR_CODE =", value, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeNotEqualTo(String value) {
            addCriterion("DOCTOR_CODE <>", value, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeGreaterThan(String value) {
            addCriterion("DOCTOR_CODE >", value, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_CODE >=", value, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeLessThan(String value) {
            addCriterion("DOCTOR_CODE <", value, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_CODE <=", value, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeLike(String value) {
            addCriterion("DOCTOR_CODE like", value, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeNotLike(String value) {
            addCriterion("DOCTOR_CODE not like", value, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeIn(List<String> values) {
            addCriterion("DOCTOR_CODE in", values, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeNotIn(List<String> values) {
            addCriterion("DOCTOR_CODE not in", values, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeBetween(String value1, String value2) {
            addCriterion("DOCTOR_CODE between", value1, value2, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_CODE not between", value1, value2, "doctorCode");
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

        public Criteria andDoctorRoleFlowIsNull() {
            addCriterion("DOCTOR_ROLE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleFlowIsNotNull() {
            addCriterion("DOCTOR_ROLE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleFlowEqualTo(String value) {
            addCriterion("DOCTOR_ROLE_FLOW =", value, "doctorRoleFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleFlowNotEqualTo(String value) {
            addCriterion("DOCTOR_ROLE_FLOW <>", value, "doctorRoleFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleFlowGreaterThan(String value) {
            addCriterion("DOCTOR_ROLE_FLOW >", value, "doctorRoleFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_ROLE_FLOW >=", value, "doctorRoleFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleFlowLessThan(String value) {
            addCriterion("DOCTOR_ROLE_FLOW <", value, "doctorRoleFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleFlowLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_ROLE_FLOW <=", value, "doctorRoleFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleFlowLike(String value) {
            addCriterion("DOCTOR_ROLE_FLOW like", value, "doctorRoleFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleFlowNotLike(String value) {
            addCriterion("DOCTOR_ROLE_FLOW not like", value, "doctorRoleFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleFlowIn(List<String> values) {
            addCriterion("DOCTOR_ROLE_FLOW in", values, "doctorRoleFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleFlowNotIn(List<String> values) {
            addCriterion("DOCTOR_ROLE_FLOW not in", values, "doctorRoleFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleFlowBetween(String value1, String value2) {
            addCriterion("DOCTOR_ROLE_FLOW between", value1, value2, "doctorRoleFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleFlowNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_ROLE_FLOW not between", value1, value2, "doctorRoleFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleNameIsNull() {
            addCriterion("DOCTOR_ROLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleNameIsNotNull() {
            addCriterion("DOCTOR_ROLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleNameEqualTo(String value) {
            addCriterion("DOCTOR_ROLE_NAME =", value, "doctorRoleName");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleNameNotEqualTo(String value) {
            addCriterion("DOCTOR_ROLE_NAME <>", value, "doctorRoleName");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleNameGreaterThan(String value) {
            addCriterion("DOCTOR_ROLE_NAME >", value, "doctorRoleName");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_ROLE_NAME >=", value, "doctorRoleName");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleNameLessThan(String value) {
            addCriterion("DOCTOR_ROLE_NAME <", value, "doctorRoleName");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_ROLE_NAME <=", value, "doctorRoleName");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleNameLike(String value) {
            addCriterion("DOCTOR_ROLE_NAME like", value, "doctorRoleName");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleNameNotLike(String value) {
            addCriterion("DOCTOR_ROLE_NAME not like", value, "doctorRoleName");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleNameIn(List<String> values) {
            addCriterion("DOCTOR_ROLE_NAME in", values, "doctorRoleName");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleNameNotIn(List<String> values) {
            addCriterion("DOCTOR_ROLE_NAME not in", values, "doctorRoleName");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_ROLE_NAME between", value1, value2, "doctorRoleName");
            return (Criteria) this;
        }

        public Criteria andDoctorRoleNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_ROLE_NAME not between", value1, value2, "doctorRoleName");
            return (Criteria) this;
        }

        public Criteria andAppearFlagIsNull() {
            addCriterion("APPEAR_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andAppearFlagIsNotNull() {
            addCriterion("APPEAR_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andAppearFlagEqualTo(String value) {
            addCriterion("APPEAR_FLAG =", value, "appearFlag");
            return (Criteria) this;
        }

        public Criteria andAppearFlagNotEqualTo(String value) {
            addCriterion("APPEAR_FLAG <>", value, "appearFlag");
            return (Criteria) this;
        }

        public Criteria andAppearFlagGreaterThan(String value) {
            addCriterion("APPEAR_FLAG >", value, "appearFlag");
            return (Criteria) this;
        }

        public Criteria andAppearFlagGreaterThanOrEqualTo(String value) {
            addCriterion("APPEAR_FLAG >=", value, "appearFlag");
            return (Criteria) this;
        }

        public Criteria andAppearFlagLessThan(String value) {
            addCriterion("APPEAR_FLAG <", value, "appearFlag");
            return (Criteria) this;
        }

        public Criteria andAppearFlagLessThanOrEqualTo(String value) {
            addCriterion("APPEAR_FLAG <=", value, "appearFlag");
            return (Criteria) this;
        }

        public Criteria andAppearFlagLike(String value) {
            addCriterion("APPEAR_FLAG like", value, "appearFlag");
            return (Criteria) this;
        }

        public Criteria andAppearFlagNotLike(String value) {
            addCriterion("APPEAR_FLAG not like", value, "appearFlag");
            return (Criteria) this;
        }

        public Criteria andAppearFlagIn(List<String> values) {
            addCriterion("APPEAR_FLAG in", values, "appearFlag");
            return (Criteria) this;
        }

        public Criteria andAppearFlagNotIn(List<String> values) {
            addCriterion("APPEAR_FLAG not in", values, "appearFlag");
            return (Criteria) this;
        }

        public Criteria andAppearFlagBetween(String value1, String value2) {
            addCriterion("APPEAR_FLAG between", value1, value2, "appearFlag");
            return (Criteria) this;
        }

        public Criteria andAppearFlagNotBetween(String value1, String value2) {
            addCriterion("APPEAR_FLAG not between", value1, value2, "appearFlag");
            return (Criteria) this;
        }

        public Criteria andAffirmFlagIsNull() {
            addCriterion("AFFIRM_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andAffirmFlagIsNotNull() {
            addCriterion("AFFIRM_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andAffirmFlagEqualTo(String value) {
            addCriterion("AFFIRM_FLAG =", value, "affirmFlag");
            return (Criteria) this;
        }

        public Criteria andAffirmFlagNotEqualTo(String value) {
            addCriterion("AFFIRM_FLAG <>", value, "affirmFlag");
            return (Criteria) this;
        }

        public Criteria andAffirmFlagGreaterThan(String value) {
            addCriterion("AFFIRM_FLAG >", value, "affirmFlag");
            return (Criteria) this;
        }

        public Criteria andAffirmFlagGreaterThanOrEqualTo(String value) {
            addCriterion("AFFIRM_FLAG >=", value, "affirmFlag");
            return (Criteria) this;
        }

        public Criteria andAffirmFlagLessThan(String value) {
            addCriterion("AFFIRM_FLAG <", value, "affirmFlag");
            return (Criteria) this;
        }

        public Criteria andAffirmFlagLessThanOrEqualTo(String value) {
            addCriterion("AFFIRM_FLAG <=", value, "affirmFlag");
            return (Criteria) this;
        }

        public Criteria andAffirmFlagLike(String value) {
            addCriterion("AFFIRM_FLAG like", value, "affirmFlag");
            return (Criteria) this;
        }

        public Criteria andAffirmFlagNotLike(String value) {
            addCriterion("AFFIRM_FLAG not like", value, "affirmFlag");
            return (Criteria) this;
        }

        public Criteria andAffirmFlagIn(List<String> values) {
            addCriterion("AFFIRM_FLAG in", values, "affirmFlag");
            return (Criteria) this;
        }

        public Criteria andAffirmFlagNotIn(List<String> values) {
            addCriterion("AFFIRM_FLAG not in", values, "affirmFlag");
            return (Criteria) this;
        }

        public Criteria andAffirmFlagBetween(String value1, String value2) {
            addCriterion("AFFIRM_FLAG between", value1, value2, "affirmFlag");
            return (Criteria) this;
        }

        public Criteria andAffirmFlagNotBetween(String value1, String value2) {
            addCriterion("AFFIRM_FLAG not between", value1, value2, "affirmFlag");
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

        public Criteria andPlanRemoveIsNull() {
            addCriterion("PLAN_REMOVE is null");
            return (Criteria) this;
        }

        public Criteria andPlanRemoveIsNotNull() {
            addCriterion("PLAN_REMOVE is not null");
            return (Criteria) this;
        }

        public Criteria andPlanRemoveEqualTo(String value) {
            addCriterion("PLAN_REMOVE =", value, "planRemove");
            return (Criteria) this;
        }

        public Criteria andPlanRemoveNotEqualTo(String value) {
            addCriterion("PLAN_REMOVE <>", value, "planRemove");
            return (Criteria) this;
        }

        public Criteria andPlanRemoveGreaterThan(String value) {
            addCriterion("PLAN_REMOVE >", value, "planRemove");
            return (Criteria) this;
        }

        public Criteria andPlanRemoveGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_REMOVE >=", value, "planRemove");
            return (Criteria) this;
        }

        public Criteria andPlanRemoveLessThan(String value) {
            addCriterion("PLAN_REMOVE <", value, "planRemove");
            return (Criteria) this;
        }

        public Criteria andPlanRemoveLessThanOrEqualTo(String value) {
            addCriterion("PLAN_REMOVE <=", value, "planRemove");
            return (Criteria) this;
        }

        public Criteria andPlanRemoveLike(String value) {
            addCriterion("PLAN_REMOVE like", value, "planRemove");
            return (Criteria) this;
        }

        public Criteria andPlanRemoveNotLike(String value) {
            addCriterion("PLAN_REMOVE not like", value, "planRemove");
            return (Criteria) this;
        }

        public Criteria andPlanRemoveIn(List<String> values) {
            addCriterion("PLAN_REMOVE in", values, "planRemove");
            return (Criteria) this;
        }

        public Criteria andPlanRemoveNotIn(List<String> values) {
            addCriterion("PLAN_REMOVE not in", values, "planRemove");
            return (Criteria) this;
        }

        public Criteria andPlanRemoveBetween(String value1, String value2) {
            addCriterion("PLAN_REMOVE between", value1, value2, "planRemove");
            return (Criteria) this;
        }

        public Criteria andPlanRemoveNotBetween(String value1, String value2) {
            addCriterion("PLAN_REMOVE not between", value1, value2, "planRemove");
            return (Criteria) this;
        }

        public Criteria andGradeIdIsNull() {
            addCriterion("GRADE_ID is null");
            return (Criteria) this;
        }

        public Criteria andGradeIdIsNotNull() {
            addCriterion("GRADE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGradeIdEqualTo(String value) {
            addCriterion("GRADE_ID =", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdNotEqualTo(String value) {
            addCriterion("GRADE_ID <>", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdGreaterThan(String value) {
            addCriterion("GRADE_ID >", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdGreaterThanOrEqualTo(String value) {
            addCriterion("GRADE_ID >=", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdLessThan(String value) {
            addCriterion("GRADE_ID <", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdLessThanOrEqualTo(String value) {
            addCriterion("GRADE_ID <=", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdLike(String value) {
            addCriterion("GRADE_ID like", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdNotLike(String value) {
            addCriterion("GRADE_ID not like", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdIn(List<String> values) {
            addCriterion("GRADE_ID in", values, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdNotIn(List<String> values) {
            addCriterion("GRADE_ID not in", values, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdBetween(String value1, String value2) {
            addCriterion("GRADE_ID between", value1, value2, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdNotBetween(String value1, String value2) {
            addCriterion("GRADE_ID not between", value1, value2, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeNameIsNull() {
            addCriterion("GRADE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGradeNameIsNotNull() {
            addCriterion("GRADE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGradeNameEqualTo(String value) {
            addCriterion("GRADE_NAME =", value, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameNotEqualTo(String value) {
            addCriterion("GRADE_NAME <>", value, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameGreaterThan(String value) {
            addCriterion("GRADE_NAME >", value, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameGreaterThanOrEqualTo(String value) {
            addCriterion("GRADE_NAME >=", value, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameLessThan(String value) {
            addCriterion("GRADE_NAME <", value, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameLessThanOrEqualTo(String value) {
            addCriterion("GRADE_NAME <=", value, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameLike(String value) {
            addCriterion("GRADE_NAME like", value, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameNotLike(String value) {
            addCriterion("GRADE_NAME not like", value, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameIn(List<String> values) {
            addCriterion("GRADE_NAME in", values, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameNotIn(List<String> values) {
            addCriterion("GRADE_NAME not in", values, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameBetween(String value1, String value2) {
            addCriterion("GRADE_NAME between", value1, value2, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameNotBetween(String value1, String value2) {
            addCriterion("GRADE_NAME not between", value1, value2, "gradeName");
            return (Criteria) this;
        }

        public Criteria andCertificateStartTimeIsNull() {
            addCriterion("CERTIFICATE_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCertificateStartTimeIsNotNull() {
            addCriterion("CERTIFICATE_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateStartTimeEqualTo(String value) {
            addCriterion("CERTIFICATE_START_TIME =", value, "certificateStartTime");
            return (Criteria) this;
        }

        public Criteria andCertificateStartTimeNotEqualTo(String value) {
            addCriterion("CERTIFICATE_START_TIME <>", value, "certificateStartTime");
            return (Criteria) this;
        }

        public Criteria andCertificateStartTimeGreaterThan(String value) {
            addCriterion("CERTIFICATE_START_TIME >", value, "certificateStartTime");
            return (Criteria) this;
        }

        public Criteria andCertificateStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_START_TIME >=", value, "certificateStartTime");
            return (Criteria) this;
        }

        public Criteria andCertificateStartTimeLessThan(String value) {
            addCriterion("CERTIFICATE_START_TIME <", value, "certificateStartTime");
            return (Criteria) this;
        }

        public Criteria andCertificateStartTimeLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_START_TIME <=", value, "certificateStartTime");
            return (Criteria) this;
        }

        public Criteria andCertificateStartTimeLike(String value) {
            addCriterion("CERTIFICATE_START_TIME like", value, "certificateStartTime");
            return (Criteria) this;
        }

        public Criteria andCertificateStartTimeNotLike(String value) {
            addCriterion("CERTIFICATE_START_TIME not like", value, "certificateStartTime");
            return (Criteria) this;
        }

        public Criteria andCertificateStartTimeIn(List<String> values) {
            addCriterion("CERTIFICATE_START_TIME in", values, "certificateStartTime");
            return (Criteria) this;
        }

        public Criteria andCertificateStartTimeNotIn(List<String> values) {
            addCriterion("CERTIFICATE_START_TIME not in", values, "certificateStartTime");
            return (Criteria) this;
        }

        public Criteria andCertificateStartTimeBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_START_TIME between", value1, value2, "certificateStartTime");
            return (Criteria) this;
        }

        public Criteria andCertificateStartTimeNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_START_TIME not between", value1, value2, "certificateStartTime");
            return (Criteria) this;
        }

        public Criteria andGainCertificateIdIsNull() {
            addCriterion("GAIN_CERTIFICATE_ID is null");
            return (Criteria) this;
        }

        public Criteria andGainCertificateIdIsNotNull() {
            addCriterion("GAIN_CERTIFICATE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGainCertificateIdEqualTo(String value) {
            addCriterion("GAIN_CERTIFICATE_ID =", value, "gainCertificateId");
            return (Criteria) this;
        }

        public Criteria andGainCertificateIdNotEqualTo(String value) {
            addCriterion("GAIN_CERTIFICATE_ID <>", value, "gainCertificateId");
            return (Criteria) this;
        }

        public Criteria andGainCertificateIdGreaterThan(String value) {
            addCriterion("GAIN_CERTIFICATE_ID >", value, "gainCertificateId");
            return (Criteria) this;
        }

        public Criteria andGainCertificateIdGreaterThanOrEqualTo(String value) {
            addCriterion("GAIN_CERTIFICATE_ID >=", value, "gainCertificateId");
            return (Criteria) this;
        }

        public Criteria andGainCertificateIdLessThan(String value) {
            addCriterion("GAIN_CERTIFICATE_ID <", value, "gainCertificateId");
            return (Criteria) this;
        }

        public Criteria andGainCertificateIdLessThanOrEqualTo(String value) {
            addCriterion("GAIN_CERTIFICATE_ID <=", value, "gainCertificateId");
            return (Criteria) this;
        }

        public Criteria andGainCertificateIdLike(String value) {
            addCriterion("GAIN_CERTIFICATE_ID like", value, "gainCertificateId");
            return (Criteria) this;
        }

        public Criteria andGainCertificateIdNotLike(String value) {
            addCriterion("GAIN_CERTIFICATE_ID not like", value, "gainCertificateId");
            return (Criteria) this;
        }

        public Criteria andGainCertificateIdIn(List<String> values) {
            addCriterion("GAIN_CERTIFICATE_ID in", values, "gainCertificateId");
            return (Criteria) this;
        }

        public Criteria andGainCertificateIdNotIn(List<String> values) {
            addCriterion("GAIN_CERTIFICATE_ID not in", values, "gainCertificateId");
            return (Criteria) this;
        }

        public Criteria andGainCertificateIdBetween(String value1, String value2) {
            addCriterion("GAIN_CERTIFICATE_ID between", value1, value2, "gainCertificateId");
            return (Criteria) this;
        }

        public Criteria andGainCertificateIdNotBetween(String value1, String value2) {
            addCriterion("GAIN_CERTIFICATE_ID not between", value1, value2, "gainCertificateId");
            return (Criteria) this;
        }

        public Criteria andGainCertificateNameIsNull() {
            addCriterion("GAIN_CERTIFICATE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGainCertificateNameIsNotNull() {
            addCriterion("GAIN_CERTIFICATE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGainCertificateNameEqualTo(String value) {
            addCriterion("GAIN_CERTIFICATE_NAME =", value, "gainCertificateName");
            return (Criteria) this;
        }

        public Criteria andGainCertificateNameNotEqualTo(String value) {
            addCriterion("GAIN_CERTIFICATE_NAME <>", value, "gainCertificateName");
            return (Criteria) this;
        }

        public Criteria andGainCertificateNameGreaterThan(String value) {
            addCriterion("GAIN_CERTIFICATE_NAME >", value, "gainCertificateName");
            return (Criteria) this;
        }

        public Criteria andGainCertificateNameGreaterThanOrEqualTo(String value) {
            addCriterion("GAIN_CERTIFICATE_NAME >=", value, "gainCertificateName");
            return (Criteria) this;
        }

        public Criteria andGainCertificateNameLessThan(String value) {
            addCriterion("GAIN_CERTIFICATE_NAME <", value, "gainCertificateName");
            return (Criteria) this;
        }

        public Criteria andGainCertificateNameLessThanOrEqualTo(String value) {
            addCriterion("GAIN_CERTIFICATE_NAME <=", value, "gainCertificateName");
            return (Criteria) this;
        }

        public Criteria andGainCertificateNameLike(String value) {
            addCriterion("GAIN_CERTIFICATE_NAME like", value, "gainCertificateName");
            return (Criteria) this;
        }

        public Criteria andGainCertificateNameNotLike(String value) {
            addCriterion("GAIN_CERTIFICATE_NAME not like", value, "gainCertificateName");
            return (Criteria) this;
        }

        public Criteria andGainCertificateNameIn(List<String> values) {
            addCriterion("GAIN_CERTIFICATE_NAME in", values, "gainCertificateName");
            return (Criteria) this;
        }

        public Criteria andGainCertificateNameNotIn(List<String> values) {
            addCriterion("GAIN_CERTIFICATE_NAME not in", values, "gainCertificateName");
            return (Criteria) this;
        }

        public Criteria andGainCertificateNameBetween(String value1, String value2) {
            addCriterion("GAIN_CERTIFICATE_NAME between", value1, value2, "gainCertificateName");
            return (Criteria) this;
        }

        public Criteria andGainCertificateNameNotBetween(String value1, String value2) {
            addCriterion("GAIN_CERTIFICATE_NAME not between", value1, value2, "gainCertificateName");
            return (Criteria) this;
        }

        public Criteria andCertificateNoIsNull() {
            addCriterion("CERTIFICATE_NO is null");
            return (Criteria) this;
        }

        public Criteria andCertificateNoIsNotNull() {
            addCriterion("CERTIFICATE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateNoEqualTo(String value) {
            addCriterion("CERTIFICATE_NO =", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotEqualTo(String value) {
            addCriterion("CERTIFICATE_NO <>", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoGreaterThan(String value) {
            addCriterion("CERTIFICATE_NO >", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_NO >=", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoLessThan(String value) {
            addCriterion("CERTIFICATE_NO <", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_NO <=", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoLike(String value) {
            addCriterion("CERTIFICATE_NO like", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotLike(String value) {
            addCriterion("CERTIFICATE_NO not like", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoIn(List<String> values) {
            addCriterion("CERTIFICATE_NO in", values, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotIn(List<String> values) {
            addCriterion("CERTIFICATE_NO not in", values, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_NO between", value1, value2, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_NO not between", value1, value2, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andSendCertificateIdIsNull() {
            addCriterion("SEND_CERTIFICATE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSendCertificateIdIsNotNull() {
            addCriterion("SEND_CERTIFICATE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSendCertificateIdEqualTo(String value) {
            addCriterion("SEND_CERTIFICATE_ID =", value, "sendCertificateId");
            return (Criteria) this;
        }

        public Criteria andSendCertificateIdNotEqualTo(String value) {
            addCriterion("SEND_CERTIFICATE_ID <>", value, "sendCertificateId");
            return (Criteria) this;
        }

        public Criteria andSendCertificateIdGreaterThan(String value) {
            addCriterion("SEND_CERTIFICATE_ID >", value, "sendCertificateId");
            return (Criteria) this;
        }

        public Criteria andSendCertificateIdGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_CERTIFICATE_ID >=", value, "sendCertificateId");
            return (Criteria) this;
        }

        public Criteria andSendCertificateIdLessThan(String value) {
            addCriterion("SEND_CERTIFICATE_ID <", value, "sendCertificateId");
            return (Criteria) this;
        }

        public Criteria andSendCertificateIdLessThanOrEqualTo(String value) {
            addCriterion("SEND_CERTIFICATE_ID <=", value, "sendCertificateId");
            return (Criteria) this;
        }

        public Criteria andSendCertificateIdLike(String value) {
            addCriterion("SEND_CERTIFICATE_ID like", value, "sendCertificateId");
            return (Criteria) this;
        }

        public Criteria andSendCertificateIdNotLike(String value) {
            addCriterion("SEND_CERTIFICATE_ID not like", value, "sendCertificateId");
            return (Criteria) this;
        }

        public Criteria andSendCertificateIdIn(List<String> values) {
            addCriterion("SEND_CERTIFICATE_ID in", values, "sendCertificateId");
            return (Criteria) this;
        }

        public Criteria andSendCertificateIdNotIn(List<String> values) {
            addCriterion("SEND_CERTIFICATE_ID not in", values, "sendCertificateId");
            return (Criteria) this;
        }

        public Criteria andSendCertificateIdBetween(String value1, String value2) {
            addCriterion("SEND_CERTIFICATE_ID between", value1, value2, "sendCertificateId");
            return (Criteria) this;
        }

        public Criteria andSendCertificateIdNotBetween(String value1, String value2) {
            addCriterion("SEND_CERTIFICATE_ID not between", value1, value2, "sendCertificateId");
            return (Criteria) this;
        }

        public Criteria andSendCertificateNameIsNull() {
            addCriterion("SEND_CERTIFICATE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSendCertificateNameIsNotNull() {
            addCriterion("SEND_CERTIFICATE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSendCertificateNameEqualTo(String value) {
            addCriterion("SEND_CERTIFICATE_NAME =", value, "sendCertificateName");
            return (Criteria) this;
        }

        public Criteria andSendCertificateNameNotEqualTo(String value) {
            addCriterion("SEND_CERTIFICATE_NAME <>", value, "sendCertificateName");
            return (Criteria) this;
        }

        public Criteria andSendCertificateNameGreaterThan(String value) {
            addCriterion("SEND_CERTIFICATE_NAME >", value, "sendCertificateName");
            return (Criteria) this;
        }

        public Criteria andSendCertificateNameGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_CERTIFICATE_NAME >=", value, "sendCertificateName");
            return (Criteria) this;
        }

        public Criteria andSendCertificateNameLessThan(String value) {
            addCriterion("SEND_CERTIFICATE_NAME <", value, "sendCertificateName");
            return (Criteria) this;
        }

        public Criteria andSendCertificateNameLessThanOrEqualTo(String value) {
            addCriterion("SEND_CERTIFICATE_NAME <=", value, "sendCertificateName");
            return (Criteria) this;
        }

        public Criteria andSendCertificateNameLike(String value) {
            addCriterion("SEND_CERTIFICATE_NAME like", value, "sendCertificateName");
            return (Criteria) this;
        }

        public Criteria andSendCertificateNameNotLike(String value) {
            addCriterion("SEND_CERTIFICATE_NAME not like", value, "sendCertificateName");
            return (Criteria) this;
        }

        public Criteria andSendCertificateNameIn(List<String> values) {
            addCriterion("SEND_CERTIFICATE_NAME in", values, "sendCertificateName");
            return (Criteria) this;
        }

        public Criteria andSendCertificateNameNotIn(List<String> values) {
            addCriterion("SEND_CERTIFICATE_NAME not in", values, "sendCertificateName");
            return (Criteria) this;
        }

        public Criteria andSendCertificateNameBetween(String value1, String value2) {
            addCriterion("SEND_CERTIFICATE_NAME between", value1, value2, "sendCertificateName");
            return (Criteria) this;
        }

        public Criteria andSendCertificateNameNotBetween(String value1, String value2) {
            addCriterion("SEND_CERTIFICATE_NAME not between", value1, value2, "sendCertificateName");
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

        public Criteria andSendCertificateTimeIsNull() {
            addCriterion("SEND_CERTIFICATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSendCertificateTimeIsNotNull() {
            addCriterion("SEND_CERTIFICATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSendCertificateTimeEqualTo(String value) {
            addCriterion("SEND_CERTIFICATE_TIME =", value, "sendCertificateTime");
            return (Criteria) this;
        }

        public Criteria andSendCertificateTimeNotEqualTo(String value) {
            addCriterion("SEND_CERTIFICATE_TIME <>", value, "sendCertificateTime");
            return (Criteria) this;
        }

        public Criteria andSendCertificateTimeGreaterThan(String value) {
            addCriterion("SEND_CERTIFICATE_TIME >", value, "sendCertificateTime");
            return (Criteria) this;
        }

        public Criteria andSendCertificateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_CERTIFICATE_TIME >=", value, "sendCertificateTime");
            return (Criteria) this;
        }

        public Criteria andSendCertificateTimeLessThan(String value) {
            addCriterion("SEND_CERTIFICATE_TIME <", value, "sendCertificateTime");
            return (Criteria) this;
        }

        public Criteria andSendCertificateTimeLessThanOrEqualTo(String value) {
            addCriterion("SEND_CERTIFICATE_TIME <=", value, "sendCertificateTime");
            return (Criteria) this;
        }

        public Criteria andSendCertificateTimeLike(String value) {
            addCriterion("SEND_CERTIFICATE_TIME like", value, "sendCertificateTime");
            return (Criteria) this;
        }

        public Criteria andSendCertificateTimeNotLike(String value) {
            addCriterion("SEND_CERTIFICATE_TIME not like", value, "sendCertificateTime");
            return (Criteria) this;
        }

        public Criteria andSendCertificateTimeIn(List<String> values) {
            addCriterion("SEND_CERTIFICATE_TIME in", values, "sendCertificateTime");
            return (Criteria) this;
        }

        public Criteria andSendCertificateTimeNotIn(List<String> values) {
            addCriterion("SEND_CERTIFICATE_TIME not in", values, "sendCertificateTime");
            return (Criteria) this;
        }

        public Criteria andSendCertificateTimeBetween(String value1, String value2) {
            addCriterion("SEND_CERTIFICATE_TIME between", value1, value2, "sendCertificateTime");
            return (Criteria) this;
        }

        public Criteria andSendCertificateTimeNotBetween(String value1, String value2) {
            addCriterion("SEND_CERTIFICATE_TIME not between", value1, value2, "sendCertificateTime");
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