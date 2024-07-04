package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ResDoctorRecruitLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResDoctorRecruitLogExample() {
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

        public Criteria andArchiveFlowIsNull() {
            addCriterion("ARCHIVE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowIsNotNull() {
            addCriterion("ARCHIVE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowEqualTo(String value) {
            addCriterion("ARCHIVE_FLOW =", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowNotEqualTo(String value) {
            addCriterion("ARCHIVE_FLOW <>", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowGreaterThan(String value) {
            addCriterion("ARCHIVE_FLOW >", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ARCHIVE_FLOW >=", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowLessThan(String value) {
            addCriterion("ARCHIVE_FLOW <", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowLessThanOrEqualTo(String value) {
            addCriterion("ARCHIVE_FLOW <=", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowLike(String value) {
            addCriterion("ARCHIVE_FLOW like", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowNotLike(String value) {
            addCriterion("ARCHIVE_FLOW not like", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowIn(List<String> values) {
            addCriterion("ARCHIVE_FLOW in", values, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowNotIn(List<String> values) {
            addCriterion("ARCHIVE_FLOW not in", values, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowBetween(String value1, String value2) {
            addCriterion("ARCHIVE_FLOW between", value1, value2, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowNotBetween(String value1, String value2) {
            addCriterion("ARCHIVE_FLOW not between", value1, value2, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorAuthIsNull() {
            addCriterion("DOCTOR_AUTH is null");
            return (Criteria) this;
        }

        public Criteria andDoctorAuthIsNotNull() {
            addCriterion("DOCTOR_AUTH is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorAuthEqualTo(String value) {
            addCriterion("DOCTOR_AUTH =", value, "doctorAuth");
            return (Criteria) this;
        }

        public Criteria andDoctorAuthNotEqualTo(String value) {
            addCriterion("DOCTOR_AUTH <>", value, "doctorAuth");
            return (Criteria) this;
        }

        public Criteria andDoctorAuthGreaterThan(String value) {
            addCriterion("DOCTOR_AUTH >", value, "doctorAuth");
            return (Criteria) this;
        }

        public Criteria andDoctorAuthGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_AUTH >=", value, "doctorAuth");
            return (Criteria) this;
        }

        public Criteria andDoctorAuthLessThan(String value) {
            addCriterion("DOCTOR_AUTH <", value, "doctorAuth");
            return (Criteria) this;
        }

        public Criteria andDoctorAuthLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_AUTH <=", value, "doctorAuth");
            return (Criteria) this;
        }

        public Criteria andDoctorAuthLike(String value) {
            addCriterion("DOCTOR_AUTH like", value, "doctorAuth");
            return (Criteria) this;
        }

        public Criteria andDoctorAuthNotLike(String value) {
            addCriterion("DOCTOR_AUTH not like", value, "doctorAuth");
            return (Criteria) this;
        }

        public Criteria andDoctorAuthIn(List<String> values) {
            addCriterion("DOCTOR_AUTH in", values, "doctorAuth");
            return (Criteria) this;
        }

        public Criteria andDoctorAuthNotIn(List<String> values) {
            addCriterion("DOCTOR_AUTH not in", values, "doctorAuth");
            return (Criteria) this;
        }

        public Criteria andDoctorAuthBetween(String value1, String value2) {
            addCriterion("DOCTOR_AUTH between", value1, value2, "doctorAuth");
            return (Criteria) this;
        }

        public Criteria andDoctorAuthNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_AUTH not between", value1, value2, "doctorAuth");
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