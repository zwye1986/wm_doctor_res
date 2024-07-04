package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ResDoctorRecruitExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResDoctorRecruitExample() {
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

        public Criteria andSwapFlagIsNull() {
            addCriterion("SWAP_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andSwapFlagIsNotNull() {
            addCriterion("SWAP_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andSwapFlagEqualTo(String value) {
            addCriterion("SWAP_FLAG =", value, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagNotEqualTo(String value) {
            addCriterion("SWAP_FLAG <>", value, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagGreaterThan(String value) {
            addCriterion("SWAP_FLAG >", value, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SWAP_FLAG >=", value, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagLessThan(String value) {
            addCriterion("SWAP_FLAG <", value, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagLessThanOrEqualTo(String value) {
            addCriterion("SWAP_FLAG <=", value, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagLike(String value) {
            addCriterion("SWAP_FLAG like", value, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagNotLike(String value) {
            addCriterion("SWAP_FLAG not like", value, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagIn(List<String> values) {
            addCriterion("SWAP_FLAG in", values, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagNotIn(List<String> values) {
            addCriterion("SWAP_FLAG not in", values, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagBetween(String value1, String value2) {
            addCriterion("SWAP_FLAG between", value1, value2, "swapFlag");
            return (Criteria) this;
        }

        public Criteria andSwapFlagNotBetween(String value1, String value2) {
            addCriterion("SWAP_FLAG not between", value1, value2, "swapFlag");
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

        public Criteria andRecruitDateIsNull() {
            addCriterion("RECRUIT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andRecruitDateIsNotNull() {
            addCriterion("RECRUIT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitDateEqualTo(String value) {
            addCriterion("RECRUIT_DATE =", value, "recruitDate");
            return (Criteria) this;
        }

        public Criteria andRecruitDateNotEqualTo(String value) {
            addCriterion("RECRUIT_DATE <>", value, "recruitDate");
            return (Criteria) this;
        }

        public Criteria andRecruitDateGreaterThan(String value) {
            addCriterion("RECRUIT_DATE >", value, "recruitDate");
            return (Criteria) this;
        }

        public Criteria andRecruitDateGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_DATE >=", value, "recruitDate");
            return (Criteria) this;
        }

        public Criteria andRecruitDateLessThan(String value) {
            addCriterion("RECRUIT_DATE <", value, "recruitDate");
            return (Criteria) this;
        }

        public Criteria andRecruitDateLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_DATE <=", value, "recruitDate");
            return (Criteria) this;
        }

        public Criteria andRecruitDateLike(String value) {
            addCriterion("RECRUIT_DATE like", value, "recruitDate");
            return (Criteria) this;
        }

        public Criteria andRecruitDateNotLike(String value) {
            addCriterion("RECRUIT_DATE not like", value, "recruitDate");
            return (Criteria) this;
        }

        public Criteria andRecruitDateIn(List<String> values) {
            addCriterion("RECRUIT_DATE in", values, "recruitDate");
            return (Criteria) this;
        }

        public Criteria andRecruitDateNotIn(List<String> values) {
            addCriterion("RECRUIT_DATE not in", values, "recruitDate");
            return (Criteria) this;
        }

        public Criteria andRecruitDateBetween(String value1, String value2) {
            addCriterion("RECRUIT_DATE between", value1, value2, "recruitDate");
            return (Criteria) this;
        }

        public Criteria andRecruitDateNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_DATE not between", value1, value2, "recruitDate");
            return (Criteria) this;
        }

        public Criteria andRetestFlagIsNull() {
            addCriterion("RETEST_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andRetestFlagIsNotNull() {
            addCriterion("RETEST_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andRetestFlagEqualTo(String value) {
            addCriterion("RETEST_FLAG =", value, "retestFlag");
            return (Criteria) this;
        }

        public Criteria andRetestFlagNotEqualTo(String value) {
            addCriterion("RETEST_FLAG <>", value, "retestFlag");
            return (Criteria) this;
        }

        public Criteria andRetestFlagGreaterThan(String value) {
            addCriterion("RETEST_FLAG >", value, "retestFlag");
            return (Criteria) this;
        }

        public Criteria andRetestFlagGreaterThanOrEqualTo(String value) {
            addCriterion("RETEST_FLAG >=", value, "retestFlag");
            return (Criteria) this;
        }

        public Criteria andRetestFlagLessThan(String value) {
            addCriterion("RETEST_FLAG <", value, "retestFlag");
            return (Criteria) this;
        }

        public Criteria andRetestFlagLessThanOrEqualTo(String value) {
            addCriterion("RETEST_FLAG <=", value, "retestFlag");
            return (Criteria) this;
        }

        public Criteria andRetestFlagLike(String value) {
            addCriterion("RETEST_FLAG like", value, "retestFlag");
            return (Criteria) this;
        }

        public Criteria andRetestFlagNotLike(String value) {
            addCriterion("RETEST_FLAG not like", value, "retestFlag");
            return (Criteria) this;
        }

        public Criteria andRetestFlagIn(List<String> values) {
            addCriterion("RETEST_FLAG in", values, "retestFlag");
            return (Criteria) this;
        }

        public Criteria andRetestFlagNotIn(List<String> values) {
            addCriterion("RETEST_FLAG not in", values, "retestFlag");
            return (Criteria) this;
        }

        public Criteria andRetestFlagBetween(String value1, String value2) {
            addCriterion("RETEST_FLAG between", value1, value2, "retestFlag");
            return (Criteria) this;
        }

        public Criteria andRetestFlagNotBetween(String value1, String value2) {
            addCriterion("RETEST_FLAG not between", value1, value2, "retestFlag");
            return (Criteria) this;
        }

        public Criteria andExamResultIsNull() {
            addCriterion("EXAM_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andExamResultIsNotNull() {
            addCriterion("EXAM_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andExamResultEqualTo(BigDecimal value) {
            addCriterion("EXAM_RESULT =", value, "examResult");
            return (Criteria) this;
        }

        public Criteria andExamResultNotEqualTo(BigDecimal value) {
            addCriterion("EXAM_RESULT <>", value, "examResult");
            return (Criteria) this;
        }

        public Criteria andExamResultGreaterThan(BigDecimal value) {
            addCriterion("EXAM_RESULT >", value, "examResult");
            return (Criteria) this;
        }

        public Criteria andExamResultGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("EXAM_RESULT >=", value, "examResult");
            return (Criteria) this;
        }

        public Criteria andExamResultLessThan(BigDecimal value) {
            addCriterion("EXAM_RESULT <", value, "examResult");
            return (Criteria) this;
        }

        public Criteria andExamResultLessThanOrEqualTo(BigDecimal value) {
            addCriterion("EXAM_RESULT <=", value, "examResult");
            return (Criteria) this;
        }

        public Criteria andExamResultIn(List<BigDecimal> values) {
            addCriterion("EXAM_RESULT in", values, "examResult");
            return (Criteria) this;
        }

        public Criteria andExamResultNotIn(List<BigDecimal> values) {
            addCriterion("EXAM_RESULT not in", values, "examResult");
            return (Criteria) this;
        }

        public Criteria andExamResultBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("EXAM_RESULT between", value1, value2, "examResult");
            return (Criteria) this;
        }

        public Criteria andExamResultNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("EXAM_RESULT not between", value1, value2, "examResult");
            return (Criteria) this;
        }

        public Criteria andAuditionResultIsNull() {
            addCriterion("AUDITION_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andAuditionResultIsNotNull() {
            addCriterion("AUDITION_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andAuditionResultEqualTo(BigDecimal value) {
            addCriterion("AUDITION_RESULT =", value, "auditionResult");
            return (Criteria) this;
        }

        public Criteria andAuditionResultNotEqualTo(BigDecimal value) {
            addCriterion("AUDITION_RESULT <>", value, "auditionResult");
            return (Criteria) this;
        }

        public Criteria andAuditionResultGreaterThan(BigDecimal value) {
            addCriterion("AUDITION_RESULT >", value, "auditionResult");
            return (Criteria) this;
        }

        public Criteria andAuditionResultGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AUDITION_RESULT >=", value, "auditionResult");
            return (Criteria) this;
        }

        public Criteria andAuditionResultLessThan(BigDecimal value) {
            addCriterion("AUDITION_RESULT <", value, "auditionResult");
            return (Criteria) this;
        }

        public Criteria andAuditionResultLessThanOrEqualTo(BigDecimal value) {
            addCriterion("AUDITION_RESULT <=", value, "auditionResult");
            return (Criteria) this;
        }

        public Criteria andAuditionResultIn(List<BigDecimal> values) {
            addCriterion("AUDITION_RESULT in", values, "auditionResult");
            return (Criteria) this;
        }

        public Criteria andAuditionResultNotIn(List<BigDecimal> values) {
            addCriterion("AUDITION_RESULT not in", values, "auditionResult");
            return (Criteria) this;
        }

        public Criteria andAuditionResultBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AUDITION_RESULT between", value1, value2, "auditionResult");
            return (Criteria) this;
        }

        public Criteria andAuditionResultNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AUDITION_RESULT not between", value1, value2, "auditionResult");
            return (Criteria) this;
        }

        public Criteria andOperResultIsNull() {
            addCriterion("OPER_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andOperResultIsNotNull() {
            addCriterion("OPER_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andOperResultEqualTo(BigDecimal value) {
            addCriterion("OPER_RESULT =", value, "operResult");
            return (Criteria) this;
        }

        public Criteria andOperResultNotEqualTo(BigDecimal value) {
            addCriterion("OPER_RESULT <>", value, "operResult");
            return (Criteria) this;
        }

        public Criteria andOperResultGreaterThan(BigDecimal value) {
            addCriterion("OPER_RESULT >", value, "operResult");
            return (Criteria) this;
        }

        public Criteria andOperResultGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("OPER_RESULT >=", value, "operResult");
            return (Criteria) this;
        }

        public Criteria andOperResultLessThan(BigDecimal value) {
            addCriterion("OPER_RESULT <", value, "operResult");
            return (Criteria) this;
        }

        public Criteria andOperResultLessThanOrEqualTo(BigDecimal value) {
            addCriterion("OPER_RESULT <=", value, "operResult");
            return (Criteria) this;
        }

        public Criteria andOperResultIn(List<BigDecimal> values) {
            addCriterion("OPER_RESULT in", values, "operResult");
            return (Criteria) this;
        }

        public Criteria andOperResultNotIn(List<BigDecimal> values) {
            addCriterion("OPER_RESULT not in", values, "operResult");
            return (Criteria) this;
        }

        public Criteria andOperResultBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OPER_RESULT between", value1, value2, "operResult");
            return (Criteria) this;
        }

        public Criteria andOperResultNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OPER_RESULT not between", value1, value2, "operResult");
            return (Criteria) this;
        }

        public Criteria andTotleResultIsNull() {
            addCriterion("TOTLE_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andTotleResultIsNotNull() {
            addCriterion("TOTLE_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andTotleResultEqualTo(BigDecimal value) {
            addCriterion("TOTLE_RESULT =", value, "totleResult");
            return (Criteria) this;
        }

        public Criteria andTotleResultNotEqualTo(BigDecimal value) {
            addCriterion("TOTLE_RESULT <>", value, "totleResult");
            return (Criteria) this;
        }

        public Criteria andTotleResultGreaterThan(BigDecimal value) {
            addCriterion("TOTLE_RESULT >", value, "totleResult");
            return (Criteria) this;
        }

        public Criteria andTotleResultGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTLE_RESULT >=", value, "totleResult");
            return (Criteria) this;
        }

        public Criteria andTotleResultLessThan(BigDecimal value) {
            addCriterion("TOTLE_RESULT <", value, "totleResult");
            return (Criteria) this;
        }

        public Criteria andTotleResultLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTLE_RESULT <=", value, "totleResult");
            return (Criteria) this;
        }

        public Criteria andTotleResultIn(List<BigDecimal> values) {
            addCriterion("TOTLE_RESULT in", values, "totleResult");
            return (Criteria) this;
        }

        public Criteria andTotleResultNotIn(List<BigDecimal> values) {
            addCriterion("TOTLE_RESULT not in", values, "totleResult");
            return (Criteria) this;
        }

        public Criteria andTotleResultBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTLE_RESULT between", value1, value2, "totleResult");
            return (Criteria) this;
        }

        public Criteria andTotleResultNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTLE_RESULT not between", value1, value2, "totleResult");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagIsNull() {
            addCriterion("ADMIT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagIsNotNull() {
            addCriterion("ADMIT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagEqualTo(String value) {
            addCriterion("ADMIT_FLAG =", value, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagNotEqualTo(String value) {
            addCriterion("ADMIT_FLAG <>", value, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagGreaterThan(String value) {
            addCriterion("ADMIT_FLAG >", value, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIT_FLAG >=", value, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagLessThan(String value) {
            addCriterion("ADMIT_FLAG <", value, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagLessThanOrEqualTo(String value) {
            addCriterion("ADMIT_FLAG <=", value, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagLike(String value) {
            addCriterion("ADMIT_FLAG like", value, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagNotLike(String value) {
            addCriterion("ADMIT_FLAG not like", value, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagIn(List<String> values) {
            addCriterion("ADMIT_FLAG in", values, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagNotIn(List<String> values) {
            addCriterion("ADMIT_FLAG not in", values, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagBetween(String value1, String value2) {
            addCriterion("ADMIT_FLAG between", value1, value2, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagNotBetween(String value1, String value2) {
            addCriterion("ADMIT_FLAG not between", value1, value2, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andRecruitFlagIsNull() {
            addCriterion("RECRUIT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andRecruitFlagIsNotNull() {
            addCriterion("RECRUIT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitFlagEqualTo(String value) {
            addCriterion("RECRUIT_FLAG =", value, "recruitFlag");
            return (Criteria) this;
        }

        public Criteria andRecruitFlagNotEqualTo(String value) {
            addCriterion("RECRUIT_FLAG <>", value, "recruitFlag");
            return (Criteria) this;
        }

        public Criteria andRecruitFlagGreaterThan(String value) {
            addCriterion("RECRUIT_FLAG >", value, "recruitFlag");
            return (Criteria) this;
        }

        public Criteria andRecruitFlagGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_FLAG >=", value, "recruitFlag");
            return (Criteria) this;
        }

        public Criteria andRecruitFlagLessThan(String value) {
            addCriterion("RECRUIT_FLAG <", value, "recruitFlag");
            return (Criteria) this;
        }

        public Criteria andRecruitFlagLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_FLAG <=", value, "recruitFlag");
            return (Criteria) this;
        }

        public Criteria andRecruitFlagLike(String value) {
            addCriterion("RECRUIT_FLAG like", value, "recruitFlag");
            return (Criteria) this;
        }

        public Criteria andRecruitFlagNotLike(String value) {
            addCriterion("RECRUIT_FLAG not like", value, "recruitFlag");
            return (Criteria) this;
        }

        public Criteria andRecruitFlagIn(List<String> values) {
            addCriterion("RECRUIT_FLAG in", values, "recruitFlag");
            return (Criteria) this;
        }

        public Criteria andRecruitFlagNotIn(List<String> values) {
            addCriterion("RECRUIT_FLAG not in", values, "recruitFlag");
            return (Criteria) this;
        }

        public Criteria andRecruitFlagBetween(String value1, String value2) {
            addCriterion("RECRUIT_FLAG between", value1, value2, "recruitFlag");
            return (Criteria) this;
        }

        public Criteria andRecruitFlagNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_FLAG not between", value1, value2, "recruitFlag");
            return (Criteria) this;
        }

        public Criteria andConfirmFlagIsNull() {
            addCriterion("CONFIRM_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andConfirmFlagIsNotNull() {
            addCriterion("CONFIRM_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmFlagEqualTo(String value) {
            addCriterion("CONFIRM_FLAG =", value, "confirmFlag");
            return (Criteria) this;
        }

        public Criteria andConfirmFlagNotEqualTo(String value) {
            addCriterion("CONFIRM_FLAG <>", value, "confirmFlag");
            return (Criteria) this;
        }

        public Criteria andConfirmFlagGreaterThan(String value) {
            addCriterion("CONFIRM_FLAG >", value, "confirmFlag");
            return (Criteria) this;
        }

        public Criteria andConfirmFlagGreaterThanOrEqualTo(String value) {
            addCriterion("CONFIRM_FLAG >=", value, "confirmFlag");
            return (Criteria) this;
        }

        public Criteria andConfirmFlagLessThan(String value) {
            addCriterion("CONFIRM_FLAG <", value, "confirmFlag");
            return (Criteria) this;
        }

        public Criteria andConfirmFlagLessThanOrEqualTo(String value) {
            addCriterion("CONFIRM_FLAG <=", value, "confirmFlag");
            return (Criteria) this;
        }

        public Criteria andConfirmFlagLike(String value) {
            addCriterion("CONFIRM_FLAG like", value, "confirmFlag");
            return (Criteria) this;
        }

        public Criteria andConfirmFlagNotLike(String value) {
            addCriterion("CONFIRM_FLAG not like", value, "confirmFlag");
            return (Criteria) this;
        }

        public Criteria andConfirmFlagIn(List<String> values) {
            addCriterion("CONFIRM_FLAG in", values, "confirmFlag");
            return (Criteria) this;
        }

        public Criteria andConfirmFlagNotIn(List<String> values) {
            addCriterion("CONFIRM_FLAG not in", values, "confirmFlag");
            return (Criteria) this;
        }

        public Criteria andConfirmFlagBetween(String value1, String value2) {
            addCriterion("CONFIRM_FLAG between", value1, value2, "confirmFlag");
            return (Criteria) this;
        }

        public Criteria andConfirmFlagNotBetween(String value1, String value2) {
            addCriterion("CONFIRM_FLAG not between", value1, value2, "confirmFlag");
            return (Criteria) this;
        }

        public Criteria andSwapSpeIdIsNull() {
            addCriterion("SWAP_SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSwapSpeIdIsNotNull() {
            addCriterion("SWAP_SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSwapSpeIdEqualTo(String value) {
            addCriterion("SWAP_SPE_ID =", value, "swapSpeId");
            return (Criteria) this;
        }

        public Criteria andSwapSpeIdNotEqualTo(String value) {
            addCriterion("SWAP_SPE_ID <>", value, "swapSpeId");
            return (Criteria) this;
        }

        public Criteria andSwapSpeIdGreaterThan(String value) {
            addCriterion("SWAP_SPE_ID >", value, "swapSpeId");
            return (Criteria) this;
        }

        public Criteria andSwapSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SWAP_SPE_ID >=", value, "swapSpeId");
            return (Criteria) this;
        }

        public Criteria andSwapSpeIdLessThan(String value) {
            addCriterion("SWAP_SPE_ID <", value, "swapSpeId");
            return (Criteria) this;
        }

        public Criteria andSwapSpeIdLessThanOrEqualTo(String value) {
            addCriterion("SWAP_SPE_ID <=", value, "swapSpeId");
            return (Criteria) this;
        }

        public Criteria andSwapSpeIdLike(String value) {
            addCriterion("SWAP_SPE_ID like", value, "swapSpeId");
            return (Criteria) this;
        }

        public Criteria andSwapSpeIdNotLike(String value) {
            addCriterion("SWAP_SPE_ID not like", value, "swapSpeId");
            return (Criteria) this;
        }

        public Criteria andSwapSpeIdIn(List<String> values) {
            addCriterion("SWAP_SPE_ID in", values, "swapSpeId");
            return (Criteria) this;
        }

        public Criteria andSwapSpeIdNotIn(List<String> values) {
            addCriterion("SWAP_SPE_ID not in", values, "swapSpeId");
            return (Criteria) this;
        }

        public Criteria andSwapSpeIdBetween(String value1, String value2) {
            addCriterion("SWAP_SPE_ID between", value1, value2, "swapSpeId");
            return (Criteria) this;
        }

        public Criteria andSwapSpeIdNotBetween(String value1, String value2) {
            addCriterion("SWAP_SPE_ID not between", value1, value2, "swapSpeId");
            return (Criteria) this;
        }

        public Criteria andSwapSpeNameIsNull() {
            addCriterion("SWAP_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSwapSpeNameIsNotNull() {
            addCriterion("SWAP_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSwapSpeNameEqualTo(String value) {
            addCriterion("SWAP_SPE_NAME =", value, "swapSpeName");
            return (Criteria) this;
        }

        public Criteria andSwapSpeNameNotEqualTo(String value) {
            addCriterion("SWAP_SPE_NAME <>", value, "swapSpeName");
            return (Criteria) this;
        }

        public Criteria andSwapSpeNameGreaterThan(String value) {
            addCriterion("SWAP_SPE_NAME >", value, "swapSpeName");
            return (Criteria) this;
        }

        public Criteria andSwapSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SWAP_SPE_NAME >=", value, "swapSpeName");
            return (Criteria) this;
        }

        public Criteria andSwapSpeNameLessThan(String value) {
            addCriterion("SWAP_SPE_NAME <", value, "swapSpeName");
            return (Criteria) this;
        }

        public Criteria andSwapSpeNameLessThanOrEqualTo(String value) {
            addCriterion("SWAP_SPE_NAME <=", value, "swapSpeName");
            return (Criteria) this;
        }

        public Criteria andSwapSpeNameLike(String value) {
            addCriterion("SWAP_SPE_NAME like", value, "swapSpeName");
            return (Criteria) this;
        }

        public Criteria andSwapSpeNameNotLike(String value) {
            addCriterion("SWAP_SPE_NAME not like", value, "swapSpeName");
            return (Criteria) this;
        }

        public Criteria andSwapSpeNameIn(List<String> values) {
            addCriterion("SWAP_SPE_NAME in", values, "swapSpeName");
            return (Criteria) this;
        }

        public Criteria andSwapSpeNameNotIn(List<String> values) {
            addCriterion("SWAP_SPE_NAME not in", values, "swapSpeName");
            return (Criteria) this;
        }

        public Criteria andSwapSpeNameBetween(String value1, String value2) {
            addCriterion("SWAP_SPE_NAME between", value1, value2, "swapSpeName");
            return (Criteria) this;
        }

        public Criteria andSwapSpeNameNotBetween(String value1, String value2) {
            addCriterion("SWAP_SPE_NAME not between", value1, value2, "swapSpeName");
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

        public Criteria andYetTrainYearIsNull() {
            addCriterion("YET_TRAIN_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andYetTrainYearIsNotNull() {
            addCriterion("YET_TRAIN_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andYetTrainYearEqualTo(String value) {
            addCriterion("YET_TRAIN_YEAR =", value, "yetTrainYear");
            return (Criteria) this;
        }

        public Criteria andYetTrainYearNotEqualTo(String value) {
            addCriterion("YET_TRAIN_YEAR <>", value, "yetTrainYear");
            return (Criteria) this;
        }

        public Criteria andYetTrainYearGreaterThan(String value) {
            addCriterion("YET_TRAIN_YEAR >", value, "yetTrainYear");
            return (Criteria) this;
        }

        public Criteria andYetTrainYearGreaterThanOrEqualTo(String value) {
            addCriterion("YET_TRAIN_YEAR >=", value, "yetTrainYear");
            return (Criteria) this;
        }

        public Criteria andYetTrainYearLessThan(String value) {
            addCriterion("YET_TRAIN_YEAR <", value, "yetTrainYear");
            return (Criteria) this;
        }

        public Criteria andYetTrainYearLessThanOrEqualTo(String value) {
            addCriterion("YET_TRAIN_YEAR <=", value, "yetTrainYear");
            return (Criteria) this;
        }

        public Criteria andYetTrainYearLike(String value) {
            addCriterion("YET_TRAIN_YEAR like", value, "yetTrainYear");
            return (Criteria) this;
        }

        public Criteria andYetTrainYearNotLike(String value) {
            addCriterion("YET_TRAIN_YEAR not like", value, "yetTrainYear");
            return (Criteria) this;
        }

        public Criteria andYetTrainYearIn(List<String> values) {
            addCriterion("YET_TRAIN_YEAR in", values, "yetTrainYear");
            return (Criteria) this;
        }

        public Criteria andYetTrainYearNotIn(List<String> values) {
            addCriterion("YET_TRAIN_YEAR not in", values, "yetTrainYear");
            return (Criteria) this;
        }

        public Criteria andYetTrainYearBetween(String value1, String value2) {
            addCriterion("YET_TRAIN_YEAR between", value1, value2, "yetTrainYear");
            return (Criteria) this;
        }

        public Criteria andYetTrainYearNotBetween(String value1, String value2) {
            addCriterion("YET_TRAIN_YEAR not between", value1, value2, "yetTrainYear");
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

        public Criteria andDoctorStrikeIdIsNull() {
            addCriterion("DOCTOR_STRIKE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeIdIsNotNull() {
            addCriterion("DOCTOR_STRIKE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeIdEqualTo(String value) {
            addCriterion("DOCTOR_STRIKE_ID =", value, "doctorStrikeId");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeIdNotEqualTo(String value) {
            addCriterion("DOCTOR_STRIKE_ID <>", value, "doctorStrikeId");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeIdGreaterThan(String value) {
            addCriterion("DOCTOR_STRIKE_ID >", value, "doctorStrikeId");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeIdGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_STRIKE_ID >=", value, "doctorStrikeId");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeIdLessThan(String value) {
            addCriterion("DOCTOR_STRIKE_ID <", value, "doctorStrikeId");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeIdLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_STRIKE_ID <=", value, "doctorStrikeId");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeIdLike(String value) {
            addCriterion("DOCTOR_STRIKE_ID like", value, "doctorStrikeId");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeIdNotLike(String value) {
            addCriterion("DOCTOR_STRIKE_ID not like", value, "doctorStrikeId");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeIdIn(List<String> values) {
            addCriterion("DOCTOR_STRIKE_ID in", values, "doctorStrikeId");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeIdNotIn(List<String> values) {
            addCriterion("DOCTOR_STRIKE_ID not in", values, "doctorStrikeId");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeIdBetween(String value1, String value2) {
            addCriterion("DOCTOR_STRIKE_ID between", value1, value2, "doctorStrikeId");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeIdNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_STRIKE_ID not between", value1, value2, "doctorStrikeId");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeNameIsNull() {
            addCriterion("DOCTOR_STRIKE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeNameIsNotNull() {
            addCriterion("DOCTOR_STRIKE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeNameEqualTo(String value) {
            addCriterion("DOCTOR_STRIKE_NAME =", value, "doctorStrikeName");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeNameNotEqualTo(String value) {
            addCriterion("DOCTOR_STRIKE_NAME <>", value, "doctorStrikeName");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeNameGreaterThan(String value) {
            addCriterion("DOCTOR_STRIKE_NAME >", value, "doctorStrikeName");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_STRIKE_NAME >=", value, "doctorStrikeName");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeNameLessThan(String value) {
            addCriterion("DOCTOR_STRIKE_NAME <", value, "doctorStrikeName");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_STRIKE_NAME <=", value, "doctorStrikeName");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeNameLike(String value) {
            addCriterion("DOCTOR_STRIKE_NAME like", value, "doctorStrikeName");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeNameNotLike(String value) {
            addCriterion("DOCTOR_STRIKE_NAME not like", value, "doctorStrikeName");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeNameIn(List<String> values) {
            addCriterion("DOCTOR_STRIKE_NAME in", values, "doctorStrikeName");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeNameNotIn(List<String> values) {
            addCriterion("DOCTOR_STRIKE_NAME not in", values, "doctorStrikeName");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_STRIKE_NAME between", value1, value2, "doctorStrikeName");
            return (Criteria) this;
        }

        public Criteria andDoctorStrikeNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_STRIKE_NAME not between", value1, value2, "doctorStrikeName");
            return (Criteria) this;
        }

        public Criteria andTrainNoIsNull() {
            addCriterion("TRAIN_NO is null");
            return (Criteria) this;
        }

        public Criteria andTrainNoIsNotNull() {
            addCriterion("TRAIN_NO is not null");
            return (Criteria) this;
        }

        public Criteria andTrainNoEqualTo(String value) {
            addCriterion("TRAIN_NO =", value, "trainNo");
            return (Criteria) this;
        }

        public Criteria andTrainNoNotEqualTo(String value) {
            addCriterion("TRAIN_NO <>", value, "trainNo");
            return (Criteria) this;
        }

        public Criteria andTrainNoGreaterThan(String value) {
            addCriterion("TRAIN_NO >", value, "trainNo");
            return (Criteria) this;
        }

        public Criteria andTrainNoGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_NO >=", value, "trainNo");
            return (Criteria) this;
        }

        public Criteria andTrainNoLessThan(String value) {
            addCriterion("TRAIN_NO <", value, "trainNo");
            return (Criteria) this;
        }

        public Criteria andTrainNoLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_NO <=", value, "trainNo");
            return (Criteria) this;
        }

        public Criteria andTrainNoLike(String value) {
            addCriterion("TRAIN_NO like", value, "trainNo");
            return (Criteria) this;
        }

        public Criteria andTrainNoNotLike(String value) {
            addCriterion("TRAIN_NO not like", value, "trainNo");
            return (Criteria) this;
        }

        public Criteria andTrainNoIn(List<String> values) {
            addCriterion("TRAIN_NO in", values, "trainNo");
            return (Criteria) this;
        }

        public Criteria andTrainNoNotIn(List<String> values) {
            addCriterion("TRAIN_NO not in", values, "trainNo");
            return (Criteria) this;
        }

        public Criteria andTrainNoBetween(String value1, String value2) {
            addCriterion("TRAIN_NO between", value1, value2, "trainNo");
            return (Criteria) this;
        }

        public Criteria andTrainNoNotBetween(String value1, String value2) {
            addCriterion("TRAIN_NO not between", value1, value2, "trainNo");
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

        public Criteria andPlaceIdIsNull() {
            addCriterion("PLACE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPlaceIdIsNotNull() {
            addCriterion("PLACE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceIdEqualTo(String value) {
            addCriterion("PLACE_ID =", value, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdNotEqualTo(String value) {
            addCriterion("PLACE_ID <>", value, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdGreaterThan(String value) {
            addCriterion("PLACE_ID >", value, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdGreaterThanOrEqualTo(String value) {
            addCriterion("PLACE_ID >=", value, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdLessThan(String value) {
            addCriterion("PLACE_ID <", value, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdLessThanOrEqualTo(String value) {
            addCriterion("PLACE_ID <=", value, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdLike(String value) {
            addCriterion("PLACE_ID like", value, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdNotLike(String value) {
            addCriterion("PLACE_ID not like", value, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdIn(List<String> values) {
            addCriterion("PLACE_ID in", values, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdNotIn(List<String> values) {
            addCriterion("PLACE_ID not in", values, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdBetween(String value1, String value2) {
            addCriterion("PLACE_ID between", value1, value2, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceIdNotBetween(String value1, String value2) {
            addCriterion("PLACE_ID not between", value1, value2, "placeId");
            return (Criteria) this;
        }

        public Criteria andPlaceNameIsNull() {
            addCriterion("PLACE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPlaceNameIsNotNull() {
            addCriterion("PLACE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceNameEqualTo(String value) {
            addCriterion("PLACE_NAME =", value, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameNotEqualTo(String value) {
            addCriterion("PLACE_NAME <>", value, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameGreaterThan(String value) {
            addCriterion("PLACE_NAME >", value, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameGreaterThanOrEqualTo(String value) {
            addCriterion("PLACE_NAME >=", value, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameLessThan(String value) {
            addCriterion("PLACE_NAME <", value, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameLessThanOrEqualTo(String value) {
            addCriterion("PLACE_NAME <=", value, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameLike(String value) {
            addCriterion("PLACE_NAME like", value, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameNotLike(String value) {
            addCriterion("PLACE_NAME not like", value, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameIn(List<String> values) {
            addCriterion("PLACE_NAME in", values, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameNotIn(List<String> values) {
            addCriterion("PLACE_NAME not in", values, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameBetween(String value1, String value2) {
            addCriterion("PLACE_NAME between", value1, value2, "placeName");
            return (Criteria) this;
        }

        public Criteria andPlaceNameNotBetween(String value1, String value2) {
            addCriterion("PLACE_NAME not between", value1, value2, "placeName");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlIsNull() {
            addCriterion("PROVE_FILE_URL is null");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlIsNotNull() {
            addCriterion("PROVE_FILE_URL is not null");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlEqualTo(String value) {
            addCriterion("PROVE_FILE_URL =", value, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlNotEqualTo(String value) {
            addCriterion("PROVE_FILE_URL <>", value, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlGreaterThan(String value) {
            addCriterion("PROVE_FILE_URL >", value, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlGreaterThanOrEqualTo(String value) {
            addCriterion("PROVE_FILE_URL >=", value, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlLessThan(String value) {
            addCriterion("PROVE_FILE_URL <", value, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlLessThanOrEqualTo(String value) {
            addCriterion("PROVE_FILE_URL <=", value, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlLike(String value) {
            addCriterion("PROVE_FILE_URL like", value, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlNotLike(String value) {
            addCriterion("PROVE_FILE_URL not like", value, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlIn(List<String> values) {
            addCriterion("PROVE_FILE_URL in", values, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlNotIn(List<String> values) {
            addCriterion("PROVE_FILE_URL not in", values, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlBetween(String value1, String value2) {
            addCriterion("PROVE_FILE_URL between", value1, value2, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andProveFileUrlNotBetween(String value1, String value2) {
            addCriterion("PROVE_FILE_URL not between", value1, value2, "proveFileUrl");
            return (Criteria) this;
        }

        public Criteria andCompleteCertNoIsNull() {
            addCriterion("COMPLETE_CERT_NO is null");
            return (Criteria) this;
        }

        public Criteria andCompleteCertNoIsNotNull() {
            addCriterion("COMPLETE_CERT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteCertNoEqualTo(String value) {
            addCriterion("COMPLETE_CERT_NO =", value, "completeCertNo");
            return (Criteria) this;
        }

        public Criteria andCompleteCertNoNotEqualTo(String value) {
            addCriterion("COMPLETE_CERT_NO <>", value, "completeCertNo");
            return (Criteria) this;
        }

        public Criteria andCompleteCertNoGreaterThan(String value) {
            addCriterion("COMPLETE_CERT_NO >", value, "completeCertNo");
            return (Criteria) this;
        }

        public Criteria andCompleteCertNoGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLETE_CERT_NO >=", value, "completeCertNo");
            return (Criteria) this;
        }

        public Criteria andCompleteCertNoLessThan(String value) {
            addCriterion("COMPLETE_CERT_NO <", value, "completeCertNo");
            return (Criteria) this;
        }

        public Criteria andCompleteCertNoLessThanOrEqualTo(String value) {
            addCriterion("COMPLETE_CERT_NO <=", value, "completeCertNo");
            return (Criteria) this;
        }

        public Criteria andCompleteCertNoLike(String value) {
            addCriterion("COMPLETE_CERT_NO like", value, "completeCertNo");
            return (Criteria) this;
        }

        public Criteria andCompleteCertNoNotLike(String value) {
            addCriterion("COMPLETE_CERT_NO not like", value, "completeCertNo");
            return (Criteria) this;
        }

        public Criteria andCompleteCertNoIn(List<String> values) {
            addCriterion("COMPLETE_CERT_NO in", values, "completeCertNo");
            return (Criteria) this;
        }

        public Criteria andCompleteCertNoNotIn(List<String> values) {
            addCriterion("COMPLETE_CERT_NO not in", values, "completeCertNo");
            return (Criteria) this;
        }

        public Criteria andCompleteCertNoBetween(String value1, String value2) {
            addCriterion("COMPLETE_CERT_NO between", value1, value2, "completeCertNo");
            return (Criteria) this;
        }

        public Criteria andCompleteCertNoNotBetween(String value1, String value2) {
            addCriterion("COMPLETE_CERT_NO not between", value1, value2, "completeCertNo");
            return (Criteria) this;
        }

        public Criteria andCompleteFileUrlIsNull() {
            addCriterion("COMPLETE_FILE_URL is null");
            return (Criteria) this;
        }

        public Criteria andCompleteFileUrlIsNotNull() {
            addCriterion("COMPLETE_FILE_URL is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteFileUrlEqualTo(String value) {
            addCriterion("COMPLETE_FILE_URL =", value, "completeFileUrl");
            return (Criteria) this;
        }

        public Criteria andCompleteFileUrlNotEqualTo(String value) {
            addCriterion("COMPLETE_FILE_URL <>", value, "completeFileUrl");
            return (Criteria) this;
        }

        public Criteria andCompleteFileUrlGreaterThan(String value) {
            addCriterion("COMPLETE_FILE_URL >", value, "completeFileUrl");
            return (Criteria) this;
        }

        public Criteria andCompleteFileUrlGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLETE_FILE_URL >=", value, "completeFileUrl");
            return (Criteria) this;
        }

        public Criteria andCompleteFileUrlLessThan(String value) {
            addCriterion("COMPLETE_FILE_URL <", value, "completeFileUrl");
            return (Criteria) this;
        }

        public Criteria andCompleteFileUrlLessThanOrEqualTo(String value) {
            addCriterion("COMPLETE_FILE_URL <=", value, "completeFileUrl");
            return (Criteria) this;
        }

        public Criteria andCompleteFileUrlLike(String value) {
            addCriterion("COMPLETE_FILE_URL like", value, "completeFileUrl");
            return (Criteria) this;
        }

        public Criteria andCompleteFileUrlNotLike(String value) {
            addCriterion("COMPLETE_FILE_URL not like", value, "completeFileUrl");
            return (Criteria) this;
        }

        public Criteria andCompleteFileUrlIn(List<String> values) {
            addCriterion("COMPLETE_FILE_URL in", values, "completeFileUrl");
            return (Criteria) this;
        }

        public Criteria andCompleteFileUrlNotIn(List<String> values) {
            addCriterion("COMPLETE_FILE_URL not in", values, "completeFileUrl");
            return (Criteria) this;
        }

        public Criteria andCompleteFileUrlBetween(String value1, String value2) {
            addCriterion("COMPLETE_FILE_URL between", value1, value2, "completeFileUrl");
            return (Criteria) this;
        }

        public Criteria andCompleteFileUrlNotBetween(String value1, String value2) {
            addCriterion("COMPLETE_FILE_URL not between", value1, value2, "completeFileUrl");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryIdIsNull() {
            addCriterion("CURR_DEGREE_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryIdIsNotNull() {
            addCriterion("CURR_DEGREE_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryIdEqualTo(String value) {
            addCriterion("CURR_DEGREE_CATEGORY_ID =", value, "currDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryIdNotEqualTo(String value) {
            addCriterion("CURR_DEGREE_CATEGORY_ID <>", value, "currDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryIdGreaterThan(String value) {
            addCriterion("CURR_DEGREE_CATEGORY_ID >", value, "currDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("CURR_DEGREE_CATEGORY_ID >=", value, "currDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryIdLessThan(String value) {
            addCriterion("CURR_DEGREE_CATEGORY_ID <", value, "currDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("CURR_DEGREE_CATEGORY_ID <=", value, "currDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryIdLike(String value) {
            addCriterion("CURR_DEGREE_CATEGORY_ID like", value, "currDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryIdNotLike(String value) {
            addCriterion("CURR_DEGREE_CATEGORY_ID not like", value, "currDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryIdIn(List<String> values) {
            addCriterion("CURR_DEGREE_CATEGORY_ID in", values, "currDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryIdNotIn(List<String> values) {
            addCriterion("CURR_DEGREE_CATEGORY_ID not in", values, "currDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryIdBetween(String value1, String value2) {
            addCriterion("CURR_DEGREE_CATEGORY_ID between", value1, value2, "currDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryIdNotBetween(String value1, String value2) {
            addCriterion("CURR_DEGREE_CATEGORY_ID not between", value1, value2, "currDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryNameIsNull() {
            addCriterion("CURR_DEGREE_CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryNameIsNotNull() {
            addCriterion("CURR_DEGREE_CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryNameEqualTo(String value) {
            addCriterion("CURR_DEGREE_CATEGORY_NAME =", value, "currDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryNameNotEqualTo(String value) {
            addCriterion("CURR_DEGREE_CATEGORY_NAME <>", value, "currDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryNameGreaterThan(String value) {
            addCriterion("CURR_DEGREE_CATEGORY_NAME >", value, "currDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("CURR_DEGREE_CATEGORY_NAME >=", value, "currDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryNameLessThan(String value) {
            addCriterion("CURR_DEGREE_CATEGORY_NAME <", value, "currDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("CURR_DEGREE_CATEGORY_NAME <=", value, "currDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryNameLike(String value) {
            addCriterion("CURR_DEGREE_CATEGORY_NAME like", value, "currDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryNameNotLike(String value) {
            addCriterion("CURR_DEGREE_CATEGORY_NAME not like", value, "currDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryNameIn(List<String> values) {
            addCriterion("CURR_DEGREE_CATEGORY_NAME in", values, "currDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryNameNotIn(List<String> values) {
            addCriterion("CURR_DEGREE_CATEGORY_NAME not in", values, "currDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryNameBetween(String value1, String value2) {
            addCriterion("CURR_DEGREE_CATEGORY_NAME between", value1, value2, "currDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andCurrDegreeCategoryNameNotBetween(String value1, String value2) {
            addCriterion("CURR_DEGREE_CATEGORY_NAME not between", value1, value2, "currDegreeCategoryName");
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

        public Criteria andAdminSwapFlagIsNull() {
            addCriterion("ADMIN_SWAP_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andAdminSwapFlagIsNotNull() {
            addCriterion("ADMIN_SWAP_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andAdminSwapFlagEqualTo(String value) {
            addCriterion("ADMIN_SWAP_FLAG =", value, "adminSwapFlag");
            return (Criteria) this;
        }

        public Criteria andAdminSwapFlagNotEqualTo(String value) {
            addCriterion("ADMIN_SWAP_FLAG <>", value, "adminSwapFlag");
            return (Criteria) this;
        }

        public Criteria andAdminSwapFlagGreaterThan(String value) {
            addCriterion("ADMIN_SWAP_FLAG >", value, "adminSwapFlag");
            return (Criteria) this;
        }

        public Criteria andAdminSwapFlagGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIN_SWAP_FLAG >=", value, "adminSwapFlag");
            return (Criteria) this;
        }

        public Criteria andAdminSwapFlagLessThan(String value) {
            addCriterion("ADMIN_SWAP_FLAG <", value, "adminSwapFlag");
            return (Criteria) this;
        }

        public Criteria andAdminSwapFlagLessThanOrEqualTo(String value) {
            addCriterion("ADMIN_SWAP_FLAG <=", value, "adminSwapFlag");
            return (Criteria) this;
        }

        public Criteria andAdminSwapFlagLike(String value) {
            addCriterion("ADMIN_SWAP_FLAG like", value, "adminSwapFlag");
            return (Criteria) this;
        }

        public Criteria andAdminSwapFlagNotLike(String value) {
            addCriterion("ADMIN_SWAP_FLAG not like", value, "adminSwapFlag");
            return (Criteria) this;
        }

        public Criteria andAdminSwapFlagIn(List<String> values) {
            addCriterion("ADMIN_SWAP_FLAG in", values, "adminSwapFlag");
            return (Criteria) this;
        }

        public Criteria andAdminSwapFlagNotIn(List<String> values) {
            addCriterion("ADMIN_SWAP_FLAG not in", values, "adminSwapFlag");
            return (Criteria) this;
        }

        public Criteria andAdminSwapFlagBetween(String value1, String value2) {
            addCriterion("ADMIN_SWAP_FLAG between", value1, value2, "adminSwapFlag");
            return (Criteria) this;
        }

        public Criteria andAdminSwapFlagNotBetween(String value1, String value2) {
            addCriterion("ADMIN_SWAP_FLAG not between", value1, value2, "adminSwapFlag");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteIsNull() {
            addCriterion("AVG_COMPLETE is null");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteIsNotNull() {
            addCriterion("AVG_COMPLETE is not null");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteEqualTo(String value) {
            addCriterion("AVG_COMPLETE =", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteNotEqualTo(String value) {
            addCriterion("AVG_COMPLETE <>", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteGreaterThan(String value) {
            addCriterion("AVG_COMPLETE >", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_COMPLETE >=", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteLessThan(String value) {
            addCriterion("AVG_COMPLETE <", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteLessThanOrEqualTo(String value) {
            addCriterion("AVG_COMPLETE <=", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteLike(String value) {
            addCriterion("AVG_COMPLETE like", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteNotLike(String value) {
            addCriterion("AVG_COMPLETE not like", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteIn(List<String> values) {
            addCriterion("AVG_COMPLETE in", values, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteNotIn(List<String> values) {
            addCriterion("AVG_COMPLETE not in", values, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBetween(String value1, String value2) {
            addCriterion("AVG_COMPLETE between", value1, value2, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteNotBetween(String value1, String value2) {
            addCriterion("AVG_COMPLETE not between", value1, value2, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgAuditIsNull() {
            addCriterion("AVG_AUDIT is null");
            return (Criteria) this;
        }

        public Criteria andAvgAuditIsNotNull() {
            addCriterion("AVG_AUDIT is not null");
            return (Criteria) this;
        }

        public Criteria andAvgAuditEqualTo(String value) {
            addCriterion("AVG_AUDIT =", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditNotEqualTo(String value) {
            addCriterion("AVG_AUDIT <>", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditGreaterThan(String value) {
            addCriterion("AVG_AUDIT >", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_AUDIT >=", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditLessThan(String value) {
            addCriterion("AVG_AUDIT <", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditLessThanOrEqualTo(String value) {
            addCriterion("AVG_AUDIT <=", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditLike(String value) {
            addCriterion("AVG_AUDIT like", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditNotLike(String value) {
            addCriterion("AVG_AUDIT not like", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditIn(List<String> values) {
            addCriterion("AVG_AUDIT in", values, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditNotIn(List<String> values) {
            addCriterion("AVG_AUDIT not in", values, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditBetween(String value1, String value2) {
            addCriterion("AVG_AUDIT between", value1, value2, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditNotBetween(String value1, String value2) {
            addCriterion("AVG_AUDIT not between", value1, value2, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andMaterialIdIsNull() {
            addCriterion("MATERIAL_ID is null");
            return (Criteria) this;
        }

        public Criteria andMaterialIdIsNotNull() {
            addCriterion("MATERIAL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialIdEqualTo(String value) {
            addCriterion("MATERIAL_ID =", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotEqualTo(String value) {
            addCriterion("MATERIAL_ID <>", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdGreaterThan(String value) {
            addCriterion("MATERIAL_ID >", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdGreaterThanOrEqualTo(String value) {
            addCriterion("MATERIAL_ID >=", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdLessThan(String value) {
            addCriterion("MATERIAL_ID <", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdLessThanOrEqualTo(String value) {
            addCriterion("MATERIAL_ID <=", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdLike(String value) {
            addCriterion("MATERIAL_ID like", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotLike(String value) {
            addCriterion("MATERIAL_ID not like", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdIn(List<String> values) {
            addCriterion("MATERIAL_ID in", values, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotIn(List<String> values) {
            addCriterion("MATERIAL_ID not in", values, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdBetween(String value1, String value2) {
            addCriterion("MATERIAL_ID between", value1, value2, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotBetween(String value1, String value2) {
            addCriterion("MATERIAL_ID not between", value1, value2, "materialId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdIsNull() {
            addCriterion("PRACTICING_SCOPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdIsNotNull() {
            addCriterion("PRACTICING_SCOPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_ID =", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdNotEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_ID <>", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdGreaterThan(String value) {
            addCriterion("PRACTICING_SCOPE_ID >", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdGreaterThanOrEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_ID >=", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdLessThan(String value) {
            addCriterion("PRACTICING_SCOPE_ID <", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdLessThanOrEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_ID <=", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdLike(String value) {
            addCriterion("PRACTICING_SCOPE_ID like", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdNotLike(String value) {
            addCriterion("PRACTICING_SCOPE_ID not like", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdIn(List<String> values) {
            addCriterion("PRACTICING_SCOPE_ID in", values, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdNotIn(List<String> values) {
            addCriterion("PRACTICING_SCOPE_ID not in", values, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdBetween(String value1, String value2) {
            addCriterion("PRACTICING_SCOPE_ID between", value1, value2, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdNotBetween(String value1, String value2) {
            addCriterion("PRACTICING_SCOPE_ID not between", value1, value2, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameIsNull() {
            addCriterion("PRACTICING_SCOPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameIsNotNull() {
            addCriterion("PRACTICING_SCOPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_NAME =", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameNotEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_NAME <>", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameGreaterThan(String value) {
            addCriterion("PRACTICING_SCOPE_NAME >", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_NAME >=", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameLessThan(String value) {
            addCriterion("PRACTICING_SCOPE_NAME <", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameLessThanOrEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_NAME <=", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameLike(String value) {
            addCriterion("PRACTICING_SCOPE_NAME like", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameNotLike(String value) {
            addCriterion("PRACTICING_SCOPE_NAME not like", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameIn(List<String> values) {
            addCriterion("PRACTICING_SCOPE_NAME in", values, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameNotIn(List<String> values) {
            addCriterion("PRACTICING_SCOPE_NAME not in", values, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameBetween(String value1, String value2) {
            addCriterion("PRACTICING_SCOPE_NAME between", value1, value2, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameNotBetween(String value1, String value2) {
            addCriterion("PRACTICING_SCOPE_NAME not between", value1, value2, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andCertificateUriIsNull() {
            addCriterion("CERTIFICATE_URI is null");
            return (Criteria) this;
        }

        public Criteria andCertificateUriIsNotNull() {
            addCriterion("CERTIFICATE_URI is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateUriEqualTo(String value) {
            addCriterion("CERTIFICATE_URI =", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriNotEqualTo(String value) {
            addCriterion("CERTIFICATE_URI <>", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriGreaterThan(String value) {
            addCriterion("CERTIFICATE_URI >", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_URI >=", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriLessThan(String value) {
            addCriterion("CERTIFICATE_URI <", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_URI <=", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriLike(String value) {
            addCriterion("CERTIFICATE_URI like", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriNotLike(String value) {
            addCriterion("CERTIFICATE_URI not like", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriIn(List<String> values) {
            addCriterion("CERTIFICATE_URI in", values, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriNotIn(List<String> values) {
            addCriterion("CERTIFICATE_URI not in", values, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_URI between", value1, value2, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_URI not between", value1, value2, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriIsNull() {
            addCriterion("QUALIFICATION_MATERIAL_URI is null");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriIsNotNull() {
            addCriterion("QUALIFICATION_MATERIAL_URI is not null");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI =", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriNotEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI <>", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriGreaterThan(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI >", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriGreaterThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI >=", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriLessThan(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI <", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriLessThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI <=", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriLike(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI like", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriNotLike(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI not like", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriIn(List<String> values) {
            addCriterion("QUALIFICATION_MATERIAL_URI in", values, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriNotIn(List<String> values) {
            addCriterion("QUALIFICATION_MATERIAL_URI not in", values, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_MATERIAL_URI between", value1, value2, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriNotBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_MATERIAL_URI not between", value1, value2, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriIsNull() {
            addCriterion("SPECIAL_CERTIFICATION_URI is null");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriIsNotNull() {
            addCriterion("SPECIAL_CERTIFICATION_URI is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriEqualTo(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI =", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriNotEqualTo(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI <>", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriGreaterThan(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI >", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriGreaterThanOrEqualTo(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI >=", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriLessThan(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI <", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriLessThanOrEqualTo(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI <=", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriLike(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI like", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriNotLike(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI not like", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriIn(List<String> values) {
            addCriterion("SPECIAL_CERTIFICATION_URI in", values, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriNotIn(List<String> values) {
            addCriterion("SPECIAL_CERTIFICATION_URI not in", values, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriBetween(String value1, String value2) {
            addCriterion("SPECIAL_CERTIFICATION_URI between", value1, value2, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriNotBetween(String value1, String value2) {
            addCriterion("SPECIAL_CERTIFICATION_URI not between", value1, value2, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeIsNull() {
            addCriterion("QUALIFICATION_MATERIAL_CODE is null");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeIsNotNull() {
            addCriterion("QUALIFICATION_MATERIAL_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE =", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeNotEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE <>", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeGreaterThan(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE >", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeGreaterThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE >=", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeLessThan(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE <", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeLessThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE <=", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeLike(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE like", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeNotLike(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE not like", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeIn(List<String> values) {
            addCriterion("QUALIFICATION_MATERIAL_CODE in", values, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeNotIn(List<String> values) {
            addCriterion("QUALIFICATION_MATERIAL_CODE not in", values, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_MATERIAL_CODE between", value1, value2, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeNotBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_MATERIAL_CODE not between", value1, value2, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNull() {
            addCriterion("TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(String value) {
            addCriterion("TYPE_ID =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(String value) {
            addCriterion("TYPE_ID <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(String value) {
            addCriterion("TYPE_ID >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_ID >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(String value) {
            addCriterion("TYPE_ID <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(String value) {
            addCriterion("TYPE_ID <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLike(String value) {
            addCriterion("TYPE_ID like", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotLike(String value) {
            addCriterion("TYPE_ID not like", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<String> values) {
            addCriterion("TYPE_ID in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<String> values) {
            addCriterion("TYPE_ID not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(String value1, String value2) {
            addCriterion("TYPE_ID between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(String value1, String value2) {
            addCriterion("TYPE_ID not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaIsNull() {
            addCriterion("REGISTE_MANUA is null");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaIsNotNull() {
            addCriterion("REGISTE_MANUA is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaEqualTo(String value) {
            addCriterion("REGISTE_MANUA =", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaNotEqualTo(String value) {
            addCriterion("REGISTE_MANUA <>", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaGreaterThan(String value) {
            addCriterion("REGISTE_MANUA >", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaGreaterThanOrEqualTo(String value) {
            addCriterion("REGISTE_MANUA >=", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaLessThan(String value) {
            addCriterion("REGISTE_MANUA <", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaLessThanOrEqualTo(String value) {
            addCriterion("REGISTE_MANUA <=", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaLike(String value) {
            addCriterion("REGISTE_MANUA like", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaNotLike(String value) {
            addCriterion("REGISTE_MANUA not like", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaIn(List<String> values) {
            addCriterion("REGISTE_MANUA in", values, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaNotIn(List<String> values) {
            addCriterion("REGISTE_MANUA not in", values, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaBetween(String value1, String value2) {
            addCriterion("REGISTE_MANUA between", value1, value2, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaNotBetween(String value1, String value2) {
            addCriterion("REGISTE_MANUA not between", value1, value2, "registeManua");
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

        public Criteria andRotationNameIsNull() {
            addCriterion("ROTATION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRotationNameIsNotNull() {
            addCriterion("ROTATION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRotationNameEqualTo(String value) {
            addCriterion("ROTATION_NAME =", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameNotEqualTo(String value) {
            addCriterion("ROTATION_NAME <>", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameGreaterThan(String value) {
            addCriterion("ROTATION_NAME >", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameGreaterThanOrEqualTo(String value) {
            addCriterion("ROTATION_NAME >=", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameLessThan(String value) {
            addCriterion("ROTATION_NAME <", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameLessThanOrEqualTo(String value) {
            addCriterion("ROTATION_NAME <=", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameLike(String value) {
            addCriterion("ROTATION_NAME like", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameNotLike(String value) {
            addCriterion("ROTATION_NAME not like", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameIn(List<String> values) {
            addCriterion("ROTATION_NAME in", values, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameNotIn(List<String> values) {
            addCriterion("ROTATION_NAME not in", values, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameBetween(String value1, String value2) {
            addCriterion("ROTATION_NAME between", value1, value2, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameNotBetween(String value1, String value2) {
            addCriterion("ROTATION_NAME not between", value1, value2, "rotationName");
            return (Criteria) this;
        }

        public Criteria andSpecialCertNoIsNull() {
            addCriterion("SPECIAL_CERT_NO is null");
            return (Criteria) this;
        }

        public Criteria andSpecialCertNoIsNotNull() {
            addCriterion("SPECIAL_CERT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialCertNoEqualTo(String value) {
            addCriterion("SPECIAL_CERT_NO =", value, "specialCertNo");
            return (Criteria) this;
        }

        public Criteria andSpecialCertNoNotEqualTo(String value) {
            addCriterion("SPECIAL_CERT_NO <>", value, "specialCertNo");
            return (Criteria) this;
        }

        public Criteria andSpecialCertNoGreaterThan(String value) {
            addCriterion("SPECIAL_CERT_NO >", value, "specialCertNo");
            return (Criteria) this;
        }

        public Criteria andSpecialCertNoGreaterThanOrEqualTo(String value) {
            addCriterion("SPECIAL_CERT_NO >=", value, "specialCertNo");
            return (Criteria) this;
        }

        public Criteria andSpecialCertNoLessThan(String value) {
            addCriterion("SPECIAL_CERT_NO <", value, "specialCertNo");
            return (Criteria) this;
        }

        public Criteria andSpecialCertNoLessThanOrEqualTo(String value) {
            addCriterion("SPECIAL_CERT_NO <=", value, "specialCertNo");
            return (Criteria) this;
        }

        public Criteria andSpecialCertNoLike(String value) {
            addCriterion("SPECIAL_CERT_NO like", value, "specialCertNo");
            return (Criteria) this;
        }

        public Criteria andSpecialCertNoNotLike(String value) {
            addCriterion("SPECIAL_CERT_NO not like", value, "specialCertNo");
            return (Criteria) this;
        }

        public Criteria andSpecialCertNoIn(List<String> values) {
            addCriterion("SPECIAL_CERT_NO in", values, "specialCertNo");
            return (Criteria) this;
        }

        public Criteria andSpecialCertNoNotIn(List<String> values) {
            addCriterion("SPECIAL_CERT_NO not in", values, "specialCertNo");
            return (Criteria) this;
        }

        public Criteria andSpecialCertNoBetween(String value1, String value2) {
            addCriterion("SPECIAL_CERT_NO between", value1, value2, "specialCertNo");
            return (Criteria) this;
        }

        public Criteria andSpecialCertNoNotBetween(String value1, String value2) {
            addCriterion("SPECIAL_CERT_NO not between", value1, value2, "specialCertNo");
            return (Criteria) this;
        }

        public Criteria andSpecialFileUrlIsNull() {
            addCriterion("SPECIAL_FILE_URL is null");
            return (Criteria) this;
        }

        public Criteria andSpecialFileUrlIsNotNull() {
            addCriterion("SPECIAL_FILE_URL is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialFileUrlEqualTo(String value) {
            addCriterion("SPECIAL_FILE_URL =", value, "specialFileUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialFileUrlNotEqualTo(String value) {
            addCriterion("SPECIAL_FILE_URL <>", value, "specialFileUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialFileUrlGreaterThan(String value) {
            addCriterion("SPECIAL_FILE_URL >", value, "specialFileUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialFileUrlGreaterThanOrEqualTo(String value) {
            addCriterion("SPECIAL_FILE_URL >=", value, "specialFileUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialFileUrlLessThan(String value) {
            addCriterion("SPECIAL_FILE_URL <", value, "specialFileUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialFileUrlLessThanOrEqualTo(String value) {
            addCriterion("SPECIAL_FILE_URL <=", value, "specialFileUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialFileUrlLike(String value) {
            addCriterion("SPECIAL_FILE_URL like", value, "specialFileUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialFileUrlNotLike(String value) {
            addCriterion("SPECIAL_FILE_URL not like", value, "specialFileUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialFileUrlIn(List<String> values) {
            addCriterion("SPECIAL_FILE_URL in", values, "specialFileUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialFileUrlNotIn(List<String> values) {
            addCriterion("SPECIAL_FILE_URL not in", values, "specialFileUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialFileUrlBetween(String value1, String value2) {
            addCriterion("SPECIAL_FILE_URL between", value1, value2, "specialFileUrl");
            return (Criteria) this;
        }

        public Criteria andSpecialFileUrlNotBetween(String value1, String value2) {
            addCriterion("SPECIAL_FILE_URL not between", value1, value2, "specialFileUrl");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdIsNull() {
            addCriterion("SECOND_SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdIsNotNull() {
            addCriterion("SECOND_SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdEqualTo(String value) {
            addCriterion("SECOND_SPE_ID =", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdNotEqualTo(String value) {
            addCriterion("SECOND_SPE_ID <>", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdGreaterThan(String value) {
            addCriterion("SECOND_SPE_ID >", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_SPE_ID >=", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdLessThan(String value) {
            addCriterion("SECOND_SPE_ID <", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdLessThanOrEqualTo(String value) {
            addCriterion("SECOND_SPE_ID <=", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdLike(String value) {
            addCriterion("SECOND_SPE_ID like", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdNotLike(String value) {
            addCriterion("SECOND_SPE_ID not like", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdIn(List<String> values) {
            addCriterion("SECOND_SPE_ID in", values, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdNotIn(List<String> values) {
            addCriterion("SECOND_SPE_ID not in", values, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdBetween(String value1, String value2) {
            addCriterion("SECOND_SPE_ID between", value1, value2, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdNotBetween(String value1, String value2) {
            addCriterion("SECOND_SPE_ID not between", value1, value2, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameIsNull() {
            addCriterion("SECOND_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameIsNotNull() {
            addCriterion("SECOND_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameEqualTo(String value) {
            addCriterion("SECOND_SPE_NAME =", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameNotEqualTo(String value) {
            addCriterion("SECOND_SPE_NAME <>", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameGreaterThan(String value) {
            addCriterion("SECOND_SPE_NAME >", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_SPE_NAME >=", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameLessThan(String value) {
            addCriterion("SECOND_SPE_NAME <", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameLessThanOrEqualTo(String value) {
            addCriterion("SECOND_SPE_NAME <=", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameLike(String value) {
            addCriterion("SECOND_SPE_NAME like", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameNotLike(String value) {
            addCriterion("SECOND_SPE_NAME not like", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameIn(List<String> values) {
            addCriterion("SECOND_SPE_NAME in", values, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameNotIn(List<String> values) {
            addCriterion("SECOND_SPE_NAME not in", values, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameBetween(String value1, String value2) {
            addCriterion("SECOND_SPE_NAME between", value1, value2, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameNotBetween(String value1, String value2) {
            addCriterion("SECOND_SPE_NAME not between", value1, value2, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateNoIsNull() {
            addCriterion("GRADUATION_CERTIFICATE_NO is null");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateNoIsNotNull() {
            addCriterion("GRADUATION_CERTIFICATE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateNoEqualTo(String value) {
            addCriterion("GRADUATION_CERTIFICATE_NO =", value, "graduationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateNoNotEqualTo(String value) {
            addCriterion("GRADUATION_CERTIFICATE_NO <>", value, "graduationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateNoGreaterThan(String value) {
            addCriterion("GRADUATION_CERTIFICATE_NO >", value, "graduationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateNoGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_CERTIFICATE_NO >=", value, "graduationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateNoLessThan(String value) {
            addCriterion("GRADUATION_CERTIFICATE_NO <", value, "graduationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateNoLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_CERTIFICATE_NO <=", value, "graduationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateNoLike(String value) {
            addCriterion("GRADUATION_CERTIFICATE_NO like", value, "graduationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateNoNotLike(String value) {
            addCriterion("GRADUATION_CERTIFICATE_NO not like", value, "graduationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateNoIn(List<String> values) {
            addCriterion("GRADUATION_CERTIFICATE_NO in", values, "graduationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateNoNotIn(List<String> values) {
            addCriterion("GRADUATION_CERTIFICATE_NO not in", values, "graduationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateNoBetween(String value1, String value2) {
            addCriterion("GRADUATION_CERTIFICATE_NO between", value1, value2, "graduationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateNoNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_CERTIFICATE_NO not between", value1, value2, "graduationCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateDateIsNull() {
            addCriterion("CERTIFICATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCertificateDateIsNotNull() {
            addCriterion("CERTIFICATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateDateEqualTo(String value) {
            addCriterion("CERTIFICATE_DATE =", value, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateNotEqualTo(String value) {
            addCriterion("CERTIFICATE_DATE <>", value, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateGreaterThan(String value) {
            addCriterion("CERTIFICATE_DATE >", value, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_DATE >=", value, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateLessThan(String value) {
            addCriterion("CERTIFICATE_DATE <", value, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_DATE <=", value, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateLike(String value) {
            addCriterion("CERTIFICATE_DATE like", value, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateNotLike(String value) {
            addCriterion("CERTIFICATE_DATE not like", value, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateIn(List<String> values) {
            addCriterion("CERTIFICATE_DATE in", values, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateNotIn(List<String> values) {
            addCriterion("CERTIFICATE_DATE not in", values, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_DATE between", value1, value2, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_DATE not between", value1, value2, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateTypeIsNull() {
            addCriterion("GRADUATION_CERTIFICATE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateTypeIsNotNull() {
            addCriterion("GRADUATION_CERTIFICATE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateTypeEqualTo(String value) {
            addCriterion("GRADUATION_CERTIFICATE_TYPE =", value, "graduationCertificateType");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateTypeNotEqualTo(String value) {
            addCriterion("GRADUATION_CERTIFICATE_TYPE <>", value, "graduationCertificateType");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateTypeGreaterThan(String value) {
            addCriterion("GRADUATION_CERTIFICATE_TYPE >", value, "graduationCertificateType");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_CERTIFICATE_TYPE >=", value, "graduationCertificateType");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateTypeLessThan(String value) {
            addCriterion("GRADUATION_CERTIFICATE_TYPE <", value, "graduationCertificateType");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateTypeLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_CERTIFICATE_TYPE <=", value, "graduationCertificateType");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateTypeLike(String value) {
            addCriterion("GRADUATION_CERTIFICATE_TYPE like", value, "graduationCertificateType");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateTypeNotLike(String value) {
            addCriterion("GRADUATION_CERTIFICATE_TYPE not like", value, "graduationCertificateType");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateTypeIn(List<String> values) {
            addCriterion("GRADUATION_CERTIFICATE_TYPE in", values, "graduationCertificateType");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateTypeNotIn(List<String> values) {
            addCriterion("GRADUATION_CERTIFICATE_TYPE not in", values, "graduationCertificateType");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateTypeBetween(String value1, String value2) {
            addCriterion("GRADUATION_CERTIFICATE_TYPE between", value1, value2, "graduationCertificateType");
            return (Criteria) this;
        }

        public Criteria andGraduationCertificateTypeNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_CERTIFICATE_TYPE not between", value1, value2, "graduationCertificateType");
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

        public Criteria andReturnBackFlagIsNull() {
            addCriterion("RETURN_BACK_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andReturnBackFlagIsNotNull() {
            addCriterion("RETURN_BACK_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andReturnBackFlagEqualTo(String value) {
            addCriterion("RETURN_BACK_FLAG =", value, "returnBackFlag");
            return (Criteria) this;
        }

        public Criteria andReturnBackFlagNotEqualTo(String value) {
            addCriterion("RETURN_BACK_FLAG <>", value, "returnBackFlag");
            return (Criteria) this;
        }

        public Criteria andReturnBackFlagGreaterThan(String value) {
            addCriterion("RETURN_BACK_FLAG >", value, "returnBackFlag");
            return (Criteria) this;
        }

        public Criteria andReturnBackFlagGreaterThanOrEqualTo(String value) {
            addCriterion("RETURN_BACK_FLAG >=", value, "returnBackFlag");
            return (Criteria) this;
        }

        public Criteria andReturnBackFlagLessThan(String value) {
            addCriterion("RETURN_BACK_FLAG <", value, "returnBackFlag");
            return (Criteria) this;
        }

        public Criteria andReturnBackFlagLessThanOrEqualTo(String value) {
            addCriterion("RETURN_BACK_FLAG <=", value, "returnBackFlag");
            return (Criteria) this;
        }

        public Criteria andReturnBackFlagLike(String value) {
            addCriterion("RETURN_BACK_FLAG like", value, "returnBackFlag");
            return (Criteria) this;
        }

        public Criteria andReturnBackFlagNotLike(String value) {
            addCriterion("RETURN_BACK_FLAG not like", value, "returnBackFlag");
            return (Criteria) this;
        }

        public Criteria andReturnBackFlagIn(List<String> values) {
            addCriterion("RETURN_BACK_FLAG in", values, "returnBackFlag");
            return (Criteria) this;
        }

        public Criteria andReturnBackFlagNotIn(List<String> values) {
            addCriterion("RETURN_BACK_FLAG not in", values, "returnBackFlag");
            return (Criteria) this;
        }

        public Criteria andReturnBackFlagBetween(String value1, String value2) {
            addCriterion("RETURN_BACK_FLAG between", value1, value2, "returnBackFlag");
            return (Criteria) this;
        }

        public Criteria andReturnBackFlagNotBetween(String value1, String value2) {
            addCriterion("RETURN_BACK_FLAG not between", value1, value2, "returnBackFlag");
            return (Criteria) this;
        }

        public Criteria andReturnBackRemarkIsNull() {
            addCriterion("RETURN_BACK_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andReturnBackRemarkIsNotNull() {
            addCriterion("RETURN_BACK_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andReturnBackRemarkEqualTo(String value) {
            addCriterion("RETURN_BACK_REMARK =", value, "returnBackRemark");
            return (Criteria) this;
        }

        public Criteria andReturnBackRemarkNotEqualTo(String value) {
            addCriterion("RETURN_BACK_REMARK <>", value, "returnBackRemark");
            return (Criteria) this;
        }

        public Criteria andReturnBackRemarkGreaterThan(String value) {
            addCriterion("RETURN_BACK_REMARK >", value, "returnBackRemark");
            return (Criteria) this;
        }

        public Criteria andReturnBackRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("RETURN_BACK_REMARK >=", value, "returnBackRemark");
            return (Criteria) this;
        }

        public Criteria andReturnBackRemarkLessThan(String value) {
            addCriterion("RETURN_BACK_REMARK <", value, "returnBackRemark");
            return (Criteria) this;
        }

        public Criteria andReturnBackRemarkLessThanOrEqualTo(String value) {
            addCriterion("RETURN_BACK_REMARK <=", value, "returnBackRemark");
            return (Criteria) this;
        }

        public Criteria andReturnBackRemarkLike(String value) {
            addCriterion("RETURN_BACK_REMARK like", value, "returnBackRemark");
            return (Criteria) this;
        }

        public Criteria andReturnBackRemarkNotLike(String value) {
            addCriterion("RETURN_BACK_REMARK not like", value, "returnBackRemark");
            return (Criteria) this;
        }

        public Criteria andReturnBackRemarkIn(List<String> values) {
            addCriterion("RETURN_BACK_REMARK in", values, "returnBackRemark");
            return (Criteria) this;
        }

        public Criteria andReturnBackRemarkNotIn(List<String> values) {
            addCriterion("RETURN_BACK_REMARK not in", values, "returnBackRemark");
            return (Criteria) this;
        }

        public Criteria andReturnBackRemarkBetween(String value1, String value2) {
            addCriterion("RETURN_BACK_REMARK between", value1, value2, "returnBackRemark");
            return (Criteria) this;
        }

        public Criteria andReturnBackRemarkNotBetween(String value1, String value2) {
            addCriterion("RETURN_BACK_REMARK not between", value1, value2, "returnBackRemark");
            return (Criteria) this;
        }

        public Criteria andIsSendIsNull() {
            addCriterion("IS_SEND is null");
            return (Criteria) this;
        }

        public Criteria andIsSendIsNotNull() {
            addCriterion("IS_SEND is not null");
            return (Criteria) this;
        }

        public Criteria andIsSendEqualTo(String value) {
            addCriterion("IS_SEND =", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotEqualTo(String value) {
            addCriterion("IS_SEND <>", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendGreaterThan(String value) {
            addCriterion("IS_SEND >", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SEND >=", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendLessThan(String value) {
            addCriterion("IS_SEND <", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendLessThanOrEqualTo(String value) {
            addCriterion("IS_SEND <=", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendLike(String value) {
            addCriterion("IS_SEND like", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotLike(String value) {
            addCriterion("IS_SEND not like", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendIn(List<String> values) {
            addCriterion("IS_SEND in", values, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotIn(List<String> values) {
            addCriterion("IS_SEND not in", values, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendBetween(String value1, String value2) {
            addCriterion("IS_SEND between", value1, value2, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotBetween(String value1, String value2) {
            addCriterion("IS_SEND not between", value1, value2, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsCertSendIsNull() {
            addCriterion("IS_CERT_SEND is null");
            return (Criteria) this;
        }

        public Criteria andIsCertSendIsNotNull() {
            addCriterion("IS_CERT_SEND is not null");
            return (Criteria) this;
        }

        public Criteria andIsCertSendEqualTo(String value) {
            addCriterion("IS_CERT_SEND =", value, "isCertSend");
            return (Criteria) this;
        }

        public Criteria andIsCertSendNotEqualTo(String value) {
            addCriterion("IS_CERT_SEND <>", value, "isCertSend");
            return (Criteria) this;
        }

        public Criteria andIsCertSendGreaterThan(String value) {
            addCriterion("IS_CERT_SEND >", value, "isCertSend");
            return (Criteria) this;
        }

        public Criteria andIsCertSendGreaterThanOrEqualTo(String value) {
            addCriterion("IS_CERT_SEND >=", value, "isCertSend");
            return (Criteria) this;
        }

        public Criteria andIsCertSendLessThan(String value) {
            addCriterion("IS_CERT_SEND <", value, "isCertSend");
            return (Criteria) this;
        }

        public Criteria andIsCertSendLessThanOrEqualTo(String value) {
            addCriterion("IS_CERT_SEND <=", value, "isCertSend");
            return (Criteria) this;
        }

        public Criteria andIsCertSendLike(String value) {
            addCriterion("IS_CERT_SEND like", value, "isCertSend");
            return (Criteria) this;
        }

        public Criteria andIsCertSendNotLike(String value) {
            addCriterion("IS_CERT_SEND not like", value, "isCertSend");
            return (Criteria) this;
        }

        public Criteria andIsCertSendIn(List<String> values) {
            addCriterion("IS_CERT_SEND in", values, "isCertSend");
            return (Criteria) this;
        }

        public Criteria andIsCertSendNotIn(List<String> values) {
            addCriterion("IS_CERT_SEND not in", values, "isCertSend");
            return (Criteria) this;
        }

        public Criteria andIsCertSendBetween(String value1, String value2) {
            addCriterion("IS_CERT_SEND between", value1, value2, "isCertSend");
            return (Criteria) this;
        }

        public Criteria andIsCertSendNotBetween(String value1, String value2) {
            addCriterion("IS_CERT_SEND not between", value1, value2, "isCertSend");
            return (Criteria) this;
        }

        public Criteria andQualifiedIdIsNull() {
            addCriterion("QUALIFIED_ID is null");
            return (Criteria) this;
        }

        public Criteria andQualifiedIdIsNotNull() {
            addCriterion("QUALIFIED_ID is not null");
            return (Criteria) this;
        }

        public Criteria andQualifiedIdEqualTo(String value) {
            addCriterion("QUALIFIED_ID =", value, "qualifiedId");
            return (Criteria) this;
        }

        public Criteria andQualifiedIdNotEqualTo(String value) {
            addCriterion("QUALIFIED_ID <>", value, "qualifiedId");
            return (Criteria) this;
        }

        public Criteria andQualifiedIdGreaterThan(String value) {
            addCriterion("QUALIFIED_ID >", value, "qualifiedId");
            return (Criteria) this;
        }

        public Criteria andQualifiedIdGreaterThanOrEqualTo(String value) {
            addCriterion("QUALIFIED_ID >=", value, "qualifiedId");
            return (Criteria) this;
        }

        public Criteria andQualifiedIdLessThan(String value) {
            addCriterion("QUALIFIED_ID <", value, "qualifiedId");
            return (Criteria) this;
        }

        public Criteria andQualifiedIdLessThanOrEqualTo(String value) {
            addCriterion("QUALIFIED_ID <=", value, "qualifiedId");
            return (Criteria) this;
        }

        public Criteria andQualifiedIdLike(String value) {
            addCriterion("QUALIFIED_ID like", value, "qualifiedId");
            return (Criteria) this;
        }

        public Criteria andQualifiedIdNotLike(String value) {
            addCriterion("QUALIFIED_ID not like", value, "qualifiedId");
            return (Criteria) this;
        }

        public Criteria andQualifiedIdIn(List<String> values) {
            addCriterion("QUALIFIED_ID in", values, "qualifiedId");
            return (Criteria) this;
        }

        public Criteria andQualifiedIdNotIn(List<String> values) {
            addCriterion("QUALIFIED_ID not in", values, "qualifiedId");
            return (Criteria) this;
        }

        public Criteria andQualifiedIdBetween(String value1, String value2) {
            addCriterion("QUALIFIED_ID between", value1, value2, "qualifiedId");
            return (Criteria) this;
        }

        public Criteria andQualifiedIdNotBetween(String value1, String value2) {
            addCriterion("QUALIFIED_ID not between", value1, value2, "qualifiedId");
            return (Criteria) this;
        }

        public Criteria andCertificateIssuingStatusIsNull() {
            addCriterion("CERTIFICATE_ISSUING_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andCertificateIssuingStatusIsNotNull() {
            addCriterion("CERTIFICATE_ISSUING_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateIssuingStatusEqualTo(String value) {
            addCriterion("CERTIFICATE_ISSUING_STATUS =", value, "certificateIssuingStatus");
            return (Criteria) this;
        }

        public Criteria andCertificateIssuingStatusNotEqualTo(String value) {
            addCriterion("CERTIFICATE_ISSUING_STATUS <>", value, "certificateIssuingStatus");
            return (Criteria) this;
        }

        public Criteria andCertificateIssuingStatusGreaterThan(String value) {
            addCriterion("CERTIFICATE_ISSUING_STATUS >", value, "certificateIssuingStatus");
            return (Criteria) this;
        }

        public Criteria andCertificateIssuingStatusGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_ISSUING_STATUS >=", value, "certificateIssuingStatus");
            return (Criteria) this;
        }

        public Criteria andCertificateIssuingStatusLessThan(String value) {
            addCriterion("CERTIFICATE_ISSUING_STATUS <", value, "certificateIssuingStatus");
            return (Criteria) this;
        }

        public Criteria andCertificateIssuingStatusLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_ISSUING_STATUS <=", value, "certificateIssuingStatus");
            return (Criteria) this;
        }

        public Criteria andCertificateIssuingStatusLike(String value) {
            addCriterion("CERTIFICATE_ISSUING_STATUS like", value, "certificateIssuingStatus");
            return (Criteria) this;
        }

        public Criteria andCertificateIssuingStatusNotLike(String value) {
            addCriterion("CERTIFICATE_ISSUING_STATUS not like", value, "certificateIssuingStatus");
            return (Criteria) this;
        }

        public Criteria andCertificateIssuingStatusIn(List<String> values) {
            addCriterion("CERTIFICATE_ISSUING_STATUS in", values, "certificateIssuingStatus");
            return (Criteria) this;
        }

        public Criteria andCertificateIssuingStatusNotIn(List<String> values) {
            addCriterion("CERTIFICATE_ISSUING_STATUS not in", values, "certificateIssuingStatus");
            return (Criteria) this;
        }

        public Criteria andCertificateIssuingStatusBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_ISSUING_STATUS between", value1, value2, "certificateIssuingStatus");
            return (Criteria) this;
        }

        public Criteria andCertificateIssuingStatusNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_ISSUING_STATUS not between", value1, value2, "certificateIssuingStatus");
            return (Criteria) this;
        }

        public Criteria andJointOrgNameIsNull() {
            addCriterion("JOINT_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andJointOrgNameIsNotNull() {
            addCriterion("JOINT_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andJointOrgNameEqualTo(String value) {
            addCriterion("JOINT_ORG_NAME =", value, "jointOrgName");
            return (Criteria) this;
        }

        public Criteria andJointOrgNameNotEqualTo(String value) {
            addCriterion("JOINT_ORG_NAME <>", value, "jointOrgName");
            return (Criteria) this;
        }

        public Criteria andJointOrgNameGreaterThan(String value) {
            addCriterion("JOINT_ORG_NAME >", value, "jointOrgName");
            return (Criteria) this;
        }

        public Criteria andJointOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("JOINT_ORG_NAME >=", value, "jointOrgName");
            return (Criteria) this;
        }

        public Criteria andJointOrgNameLessThan(String value) {
            addCriterion("JOINT_ORG_NAME <", value, "jointOrgName");
            return (Criteria) this;
        }

        public Criteria andJointOrgNameLessThanOrEqualTo(String value) {
            addCriterion("JOINT_ORG_NAME <=", value, "jointOrgName");
            return (Criteria) this;
        }

        public Criteria andJointOrgNameLike(String value) {
            addCriterion("JOINT_ORG_NAME like", value, "jointOrgName");
            return (Criteria) this;
        }

        public Criteria andJointOrgNameNotLike(String value) {
            addCriterion("JOINT_ORG_NAME not like", value, "jointOrgName");
            return (Criteria) this;
        }

        public Criteria andJointOrgNameIn(List<String> values) {
            addCriterion("JOINT_ORG_NAME in", values, "jointOrgName");
            return (Criteria) this;
        }

        public Criteria andJointOrgNameNotIn(List<String> values) {
            addCriterion("JOINT_ORG_NAME not in", values, "jointOrgName");
            return (Criteria) this;
        }

        public Criteria andJointOrgNameBetween(String value1, String value2) {
            addCriterion("JOINT_ORG_NAME between", value1, value2, "jointOrgName");
            return (Criteria) this;
        }

        public Criteria andJointOrgNameNotBetween(String value1, String value2) {
            addCriterion("JOINT_ORG_NAME not between", value1, value2, "jointOrgName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameIsNull() {
            addCriterion("EXAM_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameIsNotNull() {
            addCriterion("EXAM_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameEqualTo(String value) {
            addCriterion("EXAM_STATUS_NAME =", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameNotEqualTo(String value) {
            addCriterion("EXAM_STATUS_NAME <>", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameGreaterThan(String value) {
            addCriterion("EXAM_STATUS_NAME >", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_STATUS_NAME >=", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameLessThan(String value) {
            addCriterion("EXAM_STATUS_NAME <", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameLessThanOrEqualTo(String value) {
            addCriterion("EXAM_STATUS_NAME <=", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameLike(String value) {
            addCriterion("EXAM_STATUS_NAME like", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameNotLike(String value) {
            addCriterion("EXAM_STATUS_NAME not like", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameIn(List<String> values) {
            addCriterion("EXAM_STATUS_NAME in", values, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameNotIn(List<String> values) {
            addCriterion("EXAM_STATUS_NAME not in", values, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameBetween(String value1, String value2) {
            addCriterion("EXAM_STATUS_NAME between", value1, value2, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameNotBetween(String value1, String value2) {
            addCriterion("EXAM_STATUS_NAME not between", value1, value2, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdIsNull() {
            addCriterion("EXAM_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdIsNotNull() {
            addCriterion("EXAM_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdEqualTo(String value) {
            addCriterion("EXAM_STATUS_ID =", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdNotEqualTo(String value) {
            addCriterion("EXAM_STATUS_ID <>", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdGreaterThan(String value) {
            addCriterion("EXAM_STATUS_ID >", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_STATUS_ID >=", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdLessThan(String value) {
            addCriterion("EXAM_STATUS_ID <", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdLessThanOrEqualTo(String value) {
            addCriterion("EXAM_STATUS_ID <=", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdLike(String value) {
            addCriterion("EXAM_STATUS_ID like", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdNotLike(String value) {
            addCriterion("EXAM_STATUS_ID not like", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdIn(List<String> values) {
            addCriterion("EXAM_STATUS_ID in", values, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdNotIn(List<String> values) {
            addCriterion("EXAM_STATUS_ID not in", values, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdBetween(String value1, String value2) {
            addCriterion("EXAM_STATUS_ID between", value1, value2, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdNotBetween(String value1, String value2) {
            addCriterion("EXAM_STATUS_ID not between", value1, value2, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andArmyHospitalIsNull() {
            addCriterion("ARMY_HOSPITAL is null");
            return (Criteria) this;
        }

        public Criteria andArmyHospitalIsNotNull() {
            addCriterion("ARMY_HOSPITAL is not null");
            return (Criteria) this;
        }

        public Criteria andArmyHospitalEqualTo(String value) {
            addCriterion("ARMY_HOSPITAL =", value, "armyHospital");
            return (Criteria) this;
        }

        public Criteria andArmyHospitalNotEqualTo(String value) {
            addCriterion("ARMY_HOSPITAL <>", value, "armyHospital");
            return (Criteria) this;
        }

        public Criteria andArmyHospitalGreaterThan(String value) {
            addCriterion("ARMY_HOSPITAL >", value, "armyHospital");
            return (Criteria) this;
        }

        public Criteria andArmyHospitalGreaterThanOrEqualTo(String value) {
            addCriterion("ARMY_HOSPITAL >=", value, "armyHospital");
            return (Criteria) this;
        }

        public Criteria andArmyHospitalLessThan(String value) {
            addCriterion("ARMY_HOSPITAL <", value, "armyHospital");
            return (Criteria) this;
        }

        public Criteria andArmyHospitalLessThanOrEqualTo(String value) {
            addCriterion("ARMY_HOSPITAL <=", value, "armyHospital");
            return (Criteria) this;
        }

        public Criteria andArmyHospitalLike(String value) {
            addCriterion("ARMY_HOSPITAL like", value, "armyHospital");
            return (Criteria) this;
        }

        public Criteria andArmyHospitalNotLike(String value) {
            addCriterion("ARMY_HOSPITAL not like", value, "armyHospital");
            return (Criteria) this;
        }

        public Criteria andArmyHospitalIn(List<String> values) {
            addCriterion("ARMY_HOSPITAL in", values, "armyHospital");
            return (Criteria) this;
        }

        public Criteria andArmyHospitalNotIn(List<String> values) {
            addCriterion("ARMY_HOSPITAL not in", values, "armyHospital");
            return (Criteria) this;
        }

        public Criteria andArmyHospitalBetween(String value1, String value2) {
            addCriterion("ARMY_HOSPITAL between", value1, value2, "armyHospital");
            return (Criteria) this;
        }

        public Criteria andArmyHospitalNotBetween(String value1, String value2) {
            addCriterion("ARMY_HOSPITAL not between", value1, value2, "armyHospital");
            return (Criteria) this;
        }

        public Criteria andWorkUnitCodeIsNull() {
            addCriterion("WORK_UNIT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andWorkUnitCodeIsNotNull() {
            addCriterion("WORK_UNIT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andWorkUnitCodeEqualTo(String value) {
            addCriterion("WORK_UNIT_CODE =", value, "workUnitCode");
            return (Criteria) this;
        }

        public Criteria andWorkUnitCodeNotEqualTo(String value) {
            addCriterion("WORK_UNIT_CODE <>", value, "workUnitCode");
            return (Criteria) this;
        }

        public Criteria andWorkUnitCodeGreaterThan(String value) {
            addCriterion("WORK_UNIT_CODE >", value, "workUnitCode");
            return (Criteria) this;
        }

        public Criteria andWorkUnitCodeGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_UNIT_CODE >=", value, "workUnitCode");
            return (Criteria) this;
        }

        public Criteria andWorkUnitCodeLessThan(String value) {
            addCriterion("WORK_UNIT_CODE <", value, "workUnitCode");
            return (Criteria) this;
        }

        public Criteria andWorkUnitCodeLessThanOrEqualTo(String value) {
            addCriterion("WORK_UNIT_CODE <=", value, "workUnitCode");
            return (Criteria) this;
        }

        public Criteria andWorkUnitCodeLike(String value) {
            addCriterion("WORK_UNIT_CODE like", value, "workUnitCode");
            return (Criteria) this;
        }

        public Criteria andWorkUnitCodeNotLike(String value) {
            addCriterion("WORK_UNIT_CODE not like", value, "workUnitCode");
            return (Criteria) this;
        }

        public Criteria andWorkUnitCodeIn(List<String> values) {
            addCriterion("WORK_UNIT_CODE in", values, "workUnitCode");
            return (Criteria) this;
        }

        public Criteria andWorkUnitCodeNotIn(List<String> values) {
            addCriterion("WORK_UNIT_CODE not in", values, "workUnitCode");
            return (Criteria) this;
        }

        public Criteria andWorkUnitCodeBetween(String value1, String value2) {
            addCriterion("WORK_UNIT_CODE between", value1, value2, "workUnitCode");
            return (Criteria) this;
        }

        public Criteria andWorkUnitCodeNotBetween(String value1, String value2) {
            addCriterion("WORK_UNIT_CODE not between", value1, value2, "workUnitCode");
            return (Criteria) this;
        }

        public Criteria andWorkUnitIsNull() {
            addCriterion("WORK_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andWorkUnitIsNotNull() {
            addCriterion("WORK_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andWorkUnitEqualTo(String value) {
            addCriterion("WORK_UNIT =", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotEqualTo(String value) {
            addCriterion("WORK_UNIT <>", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitGreaterThan(String value) {
            addCriterion("WORK_UNIT >", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_UNIT >=", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitLessThan(String value) {
            addCriterion("WORK_UNIT <", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitLessThanOrEqualTo(String value) {
            addCriterion("WORK_UNIT <=", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitLike(String value) {
            addCriterion("WORK_UNIT like", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotLike(String value) {
            addCriterion("WORK_UNIT not like", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitIn(List<String> values) {
            addCriterion("WORK_UNIT in", values, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotIn(List<String> values) {
            addCriterion("WORK_UNIT not in", values, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitBetween(String value1, String value2) {
            addCriterion("WORK_UNIT between", value1, value2, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotBetween(String value1, String value2) {
            addCriterion("WORK_UNIT not between", value1, value2, "workUnit");
            return (Criteria) this;
        }

        public Criteria andJointOrgFlowIsNull() {
            addCriterion("JOINT_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andJointOrgFlowIsNotNull() {
            addCriterion("JOINT_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andJointOrgFlowEqualTo(String value) {
            addCriterion("JOINT_ORG_FLOW =", value, "jointOrgFlow");
            return (Criteria) this;
        }

        public Criteria andJointOrgFlowNotEqualTo(String value) {
            addCriterion("JOINT_ORG_FLOW <>", value, "jointOrgFlow");
            return (Criteria) this;
        }

        public Criteria andJointOrgFlowGreaterThan(String value) {
            addCriterion("JOINT_ORG_FLOW >", value, "jointOrgFlow");
            return (Criteria) this;
        }

        public Criteria andJointOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("JOINT_ORG_FLOW >=", value, "jointOrgFlow");
            return (Criteria) this;
        }

        public Criteria andJointOrgFlowLessThan(String value) {
            addCriterion("JOINT_ORG_FLOW <", value, "jointOrgFlow");
            return (Criteria) this;
        }

        public Criteria andJointOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("JOINT_ORG_FLOW <=", value, "jointOrgFlow");
            return (Criteria) this;
        }

        public Criteria andJointOrgFlowLike(String value) {
            addCriterion("JOINT_ORG_FLOW like", value, "jointOrgFlow");
            return (Criteria) this;
        }

        public Criteria andJointOrgFlowNotLike(String value) {
            addCriterion("JOINT_ORG_FLOW not like", value, "jointOrgFlow");
            return (Criteria) this;
        }

        public Criteria andJointOrgFlowIn(List<String> values) {
            addCriterion("JOINT_ORG_FLOW in", values, "jointOrgFlow");
            return (Criteria) this;
        }

        public Criteria andJointOrgFlowNotIn(List<String> values) {
            addCriterion("JOINT_ORG_FLOW not in", values, "jointOrgFlow");
            return (Criteria) this;
        }

        public Criteria andJointOrgFlowBetween(String value1, String value2) {
            addCriterion("JOINT_ORG_FLOW between", value1, value2, "jointOrgFlow");
            return (Criteria) this;
        }

        public Criteria andJointOrgFlowNotBetween(String value1, String value2) {
            addCriterion("JOINT_ORG_FLOW not between", value1, value2, "jointOrgFlow");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainIsNull() {
            addCriterion("IN_JOINT_ORG_TRAIN is null");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainIsNotNull() {
            addCriterion("IN_JOINT_ORG_TRAIN is not null");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainEqualTo(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN =", value, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainNotEqualTo(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN <>", value, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainGreaterThan(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN >", value, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainGreaterThanOrEqualTo(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN >=", value, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainLessThan(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN <", value, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainLessThanOrEqualTo(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN <=", value, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainLike(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN like", value, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainNotLike(String value) {
            addCriterion("IN_JOINT_ORG_TRAIN not like", value, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainIn(List<String> values) {
            addCriterion("IN_JOINT_ORG_TRAIN in", values, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainNotIn(List<String> values) {
            addCriterion("IN_JOINT_ORG_TRAIN not in", values, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainBetween(String value1, String value2) {
            addCriterion("IN_JOINT_ORG_TRAIN between", value1, value2, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andInJointOrgTrainNotBetween(String value1, String value2) {
            addCriterion("IN_JOINT_ORG_TRAIN not between", value1, value2, "inJointOrgTrain");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIsNull() {
            addCriterion("OPER_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIsNotNull() {
            addCriterion("OPER_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME =", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME <>", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameGreaterThan(String value) {
            addCriterion("OPER_STATUS_NAME >", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME >=", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLessThan(String value) {
            addCriterion("OPER_STATUS_NAME <", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLessThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME <=", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLike(String value) {
            addCriterion("OPER_STATUS_NAME like", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotLike(String value) {
            addCriterion("OPER_STATUS_NAME not like", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIn(List<String> values) {
            addCriterion("OPER_STATUS_NAME in", values, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotIn(List<String> values) {
            addCriterion("OPER_STATUS_NAME not in", values, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_NAME between", value1, value2, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_NAME not between", value1, value2, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIsNull() {
            addCriterion("OPER_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIsNotNull() {
            addCriterion("OPER_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdEqualTo(String value) {
            addCriterion("OPER_STATUS_ID =", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotEqualTo(String value) {
            addCriterion("OPER_STATUS_ID <>", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdGreaterThan(String value) {
            addCriterion("OPER_STATUS_ID >", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_ID >=", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLessThan(String value) {
            addCriterion("OPER_STATUS_ID <", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLessThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_ID <=", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLike(String value) {
            addCriterion("OPER_STATUS_ID like", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotLike(String value) {
            addCriterion("OPER_STATUS_ID not like", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIn(List<String> values) {
            addCriterion("OPER_STATUS_ID in", values, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotIn(List<String> values) {
            addCriterion("OPER_STATUS_ID not in", values, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_ID between", value1, value2, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_ID not between", value1, value2, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusNameIsNull() {
            addCriterion("AUDITION_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusNameIsNotNull() {
            addCriterion("AUDITION_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusNameEqualTo(String value) {
            addCriterion("AUDITION_STATUS_NAME =", value, "auditionStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusNameNotEqualTo(String value) {
            addCriterion("AUDITION_STATUS_NAME <>", value, "auditionStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusNameGreaterThan(String value) {
            addCriterion("AUDITION_STATUS_NAME >", value, "auditionStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUDITION_STATUS_NAME >=", value, "auditionStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusNameLessThan(String value) {
            addCriterion("AUDITION_STATUS_NAME <", value, "auditionStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusNameLessThanOrEqualTo(String value) {
            addCriterion("AUDITION_STATUS_NAME <=", value, "auditionStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusNameLike(String value) {
            addCriterion("AUDITION_STATUS_NAME like", value, "auditionStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusNameNotLike(String value) {
            addCriterion("AUDITION_STATUS_NAME not like", value, "auditionStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusNameIn(List<String> values) {
            addCriterion("AUDITION_STATUS_NAME in", values, "auditionStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusNameNotIn(List<String> values) {
            addCriterion("AUDITION_STATUS_NAME not in", values, "auditionStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusNameBetween(String value1, String value2) {
            addCriterion("AUDITION_STATUS_NAME between", value1, value2, "auditionStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusNameNotBetween(String value1, String value2) {
            addCriterion("AUDITION_STATUS_NAME not between", value1, value2, "auditionStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusIdIsNull() {
            addCriterion("AUDITION_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusIdIsNotNull() {
            addCriterion("AUDITION_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusIdEqualTo(String value) {
            addCriterion("AUDITION_STATUS_ID =", value, "auditionStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusIdNotEqualTo(String value) {
            addCriterion("AUDITION_STATUS_ID <>", value, "auditionStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusIdGreaterThan(String value) {
            addCriterion("AUDITION_STATUS_ID >", value, "auditionStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("AUDITION_STATUS_ID >=", value, "auditionStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusIdLessThan(String value) {
            addCriterion("AUDITION_STATUS_ID <", value, "auditionStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusIdLessThanOrEqualTo(String value) {
            addCriterion("AUDITION_STATUS_ID <=", value, "auditionStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusIdLike(String value) {
            addCriterion("AUDITION_STATUS_ID like", value, "auditionStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusIdNotLike(String value) {
            addCriterion("AUDITION_STATUS_ID not like", value, "auditionStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusIdIn(List<String> values) {
            addCriterion("AUDITION_STATUS_ID in", values, "auditionStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusIdNotIn(List<String> values) {
            addCriterion("AUDITION_STATUS_ID not in", values, "auditionStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusIdBetween(String value1, String value2) {
            addCriterion("AUDITION_STATUS_ID between", value1, value2, "auditionStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditionStatusIdNotBetween(String value1, String value2) {
            addCriterion("AUDITION_STATUS_ID not between", value1, value2, "auditionStatusId");
            return (Criteria) this;
        }

        public Criteria andIsRetrainIsNull() {
            addCriterion("IS_RETRAIN is null");
            return (Criteria) this;
        }

        public Criteria andIsRetrainIsNotNull() {
            addCriterion("IS_RETRAIN is not null");
            return (Criteria) this;
        }

        public Criteria andIsRetrainEqualTo(String value) {
            addCriterion("IS_RETRAIN =", value, "isRetrain");
            return (Criteria) this;
        }

        public Criteria andIsRetrainNotEqualTo(String value) {
            addCriterion("IS_RETRAIN <>", value, "isRetrain");
            return (Criteria) this;
        }

        public Criteria andIsRetrainGreaterThan(String value) {
            addCriterion("IS_RETRAIN >", value, "isRetrain");
            return (Criteria) this;
        }

        public Criteria andIsRetrainGreaterThanOrEqualTo(String value) {
            addCriterion("IS_RETRAIN >=", value, "isRetrain");
            return (Criteria) this;
        }

        public Criteria andIsRetrainLessThan(String value) {
            addCriterion("IS_RETRAIN <", value, "isRetrain");
            return (Criteria) this;
        }

        public Criteria andIsRetrainLessThanOrEqualTo(String value) {
            addCriterion("IS_RETRAIN <=", value, "isRetrain");
            return (Criteria) this;
        }

        public Criteria andIsRetrainLike(String value) {
            addCriterion("IS_RETRAIN like", value, "isRetrain");
            return (Criteria) this;
        }

        public Criteria andIsRetrainNotLike(String value) {
            addCriterion("IS_RETRAIN not like", value, "isRetrain");
            return (Criteria) this;
        }

        public Criteria andIsRetrainIn(List<String> values) {
            addCriterion("IS_RETRAIN in", values, "isRetrain");
            return (Criteria) this;
        }

        public Criteria andIsRetrainNotIn(List<String> values) {
            addCriterion("IS_RETRAIN not in", values, "isRetrain");
            return (Criteria) this;
        }

        public Criteria andIsRetrainBetween(String value1, String value2) {
            addCriterion("IS_RETRAIN between", value1, value2, "isRetrain");
            return (Criteria) this;
        }

        public Criteria andIsRetrainNotBetween(String value1, String value2) {
            addCriterion("IS_RETRAIN not between", value1, value2, "isRetrain");
            return (Criteria) this;
        }

        public Criteria andJointOrgAuditIsNull() {
            addCriterion("JOINT_ORG_AUDIT is null");
            return (Criteria) this;
        }

        public Criteria andJointOrgAuditIsNotNull() {
            addCriterion("JOINT_ORG_AUDIT is not null");
            return (Criteria) this;
        }

        public Criteria andJointOrgAuditEqualTo(String value) {
            addCriterion("JOINT_ORG_AUDIT =", value, "jointOrgAudit");
            return (Criteria) this;
        }

        public Criteria andJointOrgAuditNotEqualTo(String value) {
            addCriterion("JOINT_ORG_AUDIT <>", value, "jointOrgAudit");
            return (Criteria) this;
        }

        public Criteria andJointOrgAuditGreaterThan(String value) {
            addCriterion("JOINT_ORG_AUDIT >", value, "jointOrgAudit");
            return (Criteria) this;
        }

        public Criteria andJointOrgAuditGreaterThanOrEqualTo(String value) {
            addCriterion("JOINT_ORG_AUDIT >=", value, "jointOrgAudit");
            return (Criteria) this;
        }

        public Criteria andJointOrgAuditLessThan(String value) {
            addCriterion("JOINT_ORG_AUDIT <", value, "jointOrgAudit");
            return (Criteria) this;
        }

        public Criteria andJointOrgAuditLessThanOrEqualTo(String value) {
            addCriterion("JOINT_ORG_AUDIT <=", value, "jointOrgAudit");
            return (Criteria) this;
        }

        public Criteria andJointOrgAuditLike(String value) {
            addCriterion("JOINT_ORG_AUDIT like", value, "jointOrgAudit");
            return (Criteria) this;
        }

        public Criteria andJointOrgAuditNotLike(String value) {
            addCriterion("JOINT_ORG_AUDIT not like", value, "jointOrgAudit");
            return (Criteria) this;
        }

        public Criteria andJointOrgAuditIn(List<String> values) {
            addCriterion("JOINT_ORG_AUDIT in", values, "jointOrgAudit");
            return (Criteria) this;
        }

        public Criteria andJointOrgAuditNotIn(List<String> values) {
            addCriterion("JOINT_ORG_AUDIT not in", values, "jointOrgAudit");
            return (Criteria) this;
        }

        public Criteria andJointOrgAuditBetween(String value1, String value2) {
            addCriterion("JOINT_ORG_AUDIT between", value1, value2, "jointOrgAudit");
            return (Criteria) this;
        }

        public Criteria andJointOrgAuditNotBetween(String value1, String value2) {
            addCriterion("JOINT_ORG_AUDIT not between", value1, value2, "jointOrgAudit");
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

        public Criteria andOrgAuditIsNull() {
            addCriterion("ORG_AUDIT is null");
            return (Criteria) this;
        }

        public Criteria andOrgAuditIsNotNull() {
            addCriterion("ORG_AUDIT is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAuditEqualTo(String value) {
            addCriterion("ORG_AUDIT =", value, "orgAudit");
            return (Criteria) this;
        }

        public Criteria andOrgAuditNotEqualTo(String value) {
            addCriterion("ORG_AUDIT <>", value, "orgAudit");
            return (Criteria) this;
        }

        public Criteria andOrgAuditGreaterThan(String value) {
            addCriterion("ORG_AUDIT >", value, "orgAudit");
            return (Criteria) this;
        }

        public Criteria andOrgAuditGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_AUDIT >=", value, "orgAudit");
            return (Criteria) this;
        }

        public Criteria andOrgAuditLessThan(String value) {
            addCriterion("ORG_AUDIT <", value, "orgAudit");
            return (Criteria) this;
        }

        public Criteria andOrgAuditLessThanOrEqualTo(String value) {
            addCriterion("ORG_AUDIT <=", value, "orgAudit");
            return (Criteria) this;
        }

        public Criteria andOrgAuditLike(String value) {
            addCriterion("ORG_AUDIT like", value, "orgAudit");
            return (Criteria) this;
        }

        public Criteria andOrgAuditNotLike(String value) {
            addCriterion("ORG_AUDIT not like", value, "orgAudit");
            return (Criteria) this;
        }

        public Criteria andOrgAuditIn(List<String> values) {
            addCriterion("ORG_AUDIT in", values, "orgAudit");
            return (Criteria) this;
        }

        public Criteria andOrgAuditNotIn(List<String> values) {
            addCriterion("ORG_AUDIT not in", values, "orgAudit");
            return (Criteria) this;
        }

        public Criteria andOrgAuditBetween(String value1, String value2) {
            addCriterion("ORG_AUDIT between", value1, value2, "orgAudit");
            return (Criteria) this;
        }

        public Criteria andOrgAuditNotBetween(String value1, String value2) {
            addCriterion("ORG_AUDIT not between", value1, value2, "orgAudit");
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