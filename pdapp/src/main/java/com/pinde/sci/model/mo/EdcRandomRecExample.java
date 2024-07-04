package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EdcRandomRecExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdcRandomRecExample() {
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

        public Criteria andProjFlowIsNull() {
            addCriterion("PROJ_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andProjFlowIsNotNull() {
            addCriterion("PROJ_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andProjFlowEqualTo(String value) {
            addCriterion("PROJ_FLOW =", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotEqualTo(String value) {
            addCriterion("PROJ_FLOW <>", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowGreaterThan(String value) {
            addCriterion("PROJ_FLOW >", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_FLOW >=", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowLessThan(String value) {
            addCriterion("PROJ_FLOW <", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowLessThanOrEqualTo(String value) {
            addCriterion("PROJ_FLOW <=", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowLike(String value) {
            addCriterion("PROJ_FLOW like", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotLike(String value) {
            addCriterion("PROJ_FLOW not like", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowIn(List<String> values) {
            addCriterion("PROJ_FLOW in", values, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotIn(List<String> values) {
            addCriterion("PROJ_FLOW not in", values, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowBetween(String value1, String value2) {
            addCriterion("PROJ_FLOW between", value1, value2, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotBetween(String value1, String value2) {
            addCriterion("PROJ_FLOW not between", value1, value2, "projFlow");
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

        public Criteria andOrdinalIsNull() {
            addCriterion("ORDINAL is null");
            return (Criteria) this;
        }

        public Criteria andOrdinalIsNotNull() {
            addCriterion("ORDINAL is not null");
            return (Criteria) this;
        }

        public Criteria andOrdinalEqualTo(Integer value) {
            addCriterion("ORDINAL =", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotEqualTo(Integer value) {
            addCriterion("ORDINAL <>", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThan(Integer value) {
            addCriterion("ORDINAL >", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL >=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThan(Integer value) {
            addCriterion("ORDINAL <", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL <=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalIn(List<Integer> values) {
            addCriterion("ORDINAL in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotIn(List<Integer> values) {
            addCriterion("ORDINAL not in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL between", value1, value2, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL not between", value1, value2, "ordinal");
            return (Criteria) this;
        }

        public Criteria andDrugGroupIsNull() {
            addCriterion("DRUG_GROUP is null");
            return (Criteria) this;
        }

        public Criteria andDrugGroupIsNotNull() {
            addCriterion("DRUG_GROUP is not null");
            return (Criteria) this;
        }

        public Criteria andDrugGroupEqualTo(String value) {
            addCriterion("DRUG_GROUP =", value, "drugGroup");
            return (Criteria) this;
        }

        public Criteria andDrugGroupNotEqualTo(String value) {
            addCriterion("DRUG_GROUP <>", value, "drugGroup");
            return (Criteria) this;
        }

        public Criteria andDrugGroupGreaterThan(String value) {
            addCriterion("DRUG_GROUP >", value, "drugGroup");
            return (Criteria) this;
        }

        public Criteria andDrugGroupGreaterThanOrEqualTo(String value) {
            addCriterion("DRUG_GROUP >=", value, "drugGroup");
            return (Criteria) this;
        }

        public Criteria andDrugGroupLessThan(String value) {
            addCriterion("DRUG_GROUP <", value, "drugGroup");
            return (Criteria) this;
        }

        public Criteria andDrugGroupLessThanOrEqualTo(String value) {
            addCriterion("DRUG_GROUP <=", value, "drugGroup");
            return (Criteria) this;
        }

        public Criteria andDrugGroupLike(String value) {
            addCriterion("DRUG_GROUP like", value, "drugGroup");
            return (Criteria) this;
        }

        public Criteria andDrugGroupNotLike(String value) {
            addCriterion("DRUG_GROUP not like", value, "drugGroup");
            return (Criteria) this;
        }

        public Criteria andDrugGroupIn(List<String> values) {
            addCriterion("DRUG_GROUP in", values, "drugGroup");
            return (Criteria) this;
        }

        public Criteria andDrugGroupNotIn(List<String> values) {
            addCriterion("DRUG_GROUP not in", values, "drugGroup");
            return (Criteria) this;
        }

        public Criteria andDrugGroupBetween(String value1, String value2) {
            addCriterion("DRUG_GROUP between", value1, value2, "drugGroup");
            return (Criteria) this;
        }

        public Criteria andDrugGroupNotBetween(String value1, String value2) {
            addCriterion("DRUG_GROUP not between", value1, value2, "drugGroup");
            return (Criteria) this;
        }

        public Criteria andDrugPackIsNull() {
            addCriterion("DRUG_PACK is null");
            return (Criteria) this;
        }

        public Criteria andDrugPackIsNotNull() {
            addCriterion("DRUG_PACK is not null");
            return (Criteria) this;
        }

        public Criteria andDrugPackEqualTo(String value) {
            addCriterion("DRUG_PACK =", value, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackNotEqualTo(String value) {
            addCriterion("DRUG_PACK <>", value, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackGreaterThan(String value) {
            addCriterion("DRUG_PACK >", value, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackGreaterThanOrEqualTo(String value) {
            addCriterion("DRUG_PACK >=", value, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackLessThan(String value) {
            addCriterion("DRUG_PACK <", value, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackLessThanOrEqualTo(String value) {
            addCriterion("DRUG_PACK <=", value, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackLike(String value) {
            addCriterion("DRUG_PACK like", value, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackNotLike(String value) {
            addCriterion("DRUG_PACK not like", value, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackIn(List<String> values) {
            addCriterion("DRUG_PACK in", values, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackNotIn(List<String> values) {
            addCriterion("DRUG_PACK not in", values, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackBetween(String value1, String value2) {
            addCriterion("DRUG_PACK between", value1, value2, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackNotBetween(String value1, String value2) {
            addCriterion("DRUG_PACK not between", value1, value2, "drugPack");
            return (Criteria) this;
        }

        public Criteria andBlockNumIsNull() {
            addCriterion("BLOCK_NUM is null");
            return (Criteria) this;
        }

        public Criteria andBlockNumIsNotNull() {
            addCriterion("BLOCK_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andBlockNumEqualTo(String value) {
            addCriterion("BLOCK_NUM =", value, "blockNum");
            return (Criteria) this;
        }

        public Criteria andBlockNumNotEqualTo(String value) {
            addCriterion("BLOCK_NUM <>", value, "blockNum");
            return (Criteria) this;
        }

        public Criteria andBlockNumGreaterThan(String value) {
            addCriterion("BLOCK_NUM >", value, "blockNum");
            return (Criteria) this;
        }

        public Criteria andBlockNumGreaterThanOrEqualTo(String value) {
            addCriterion("BLOCK_NUM >=", value, "blockNum");
            return (Criteria) this;
        }

        public Criteria andBlockNumLessThan(String value) {
            addCriterion("BLOCK_NUM <", value, "blockNum");
            return (Criteria) this;
        }

        public Criteria andBlockNumLessThanOrEqualTo(String value) {
            addCriterion("BLOCK_NUM <=", value, "blockNum");
            return (Criteria) this;
        }

        public Criteria andBlockNumLike(String value) {
            addCriterion("BLOCK_NUM like", value, "blockNum");
            return (Criteria) this;
        }

        public Criteria andBlockNumNotLike(String value) {
            addCriterion("BLOCK_NUM not like", value, "blockNum");
            return (Criteria) this;
        }

        public Criteria andBlockNumIn(List<String> values) {
            addCriterion("BLOCK_NUM in", values, "blockNum");
            return (Criteria) this;
        }

        public Criteria andBlockNumNotIn(List<String> values) {
            addCriterion("BLOCK_NUM not in", values, "blockNum");
            return (Criteria) this;
        }

        public Criteria andBlockNumBetween(String value1, String value2) {
            addCriterion("BLOCK_NUM between", value1, value2, "blockNum");
            return (Criteria) this;
        }

        public Criteria andBlockNumNotBetween(String value1, String value2) {
            addCriterion("BLOCK_NUM not between", value1, value2, "blockNum");
            return (Criteria) this;
        }

        public Criteria andDrugFactorIdIsNull() {
            addCriterion("DRUG_FACTOR_ID is null");
            return (Criteria) this;
        }

        public Criteria andDrugFactorIdIsNotNull() {
            addCriterion("DRUG_FACTOR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDrugFactorIdEqualTo(String value) {
            addCriterion("DRUG_FACTOR_ID =", value, "drugFactorId");
            return (Criteria) this;
        }

        public Criteria andDrugFactorIdNotEqualTo(String value) {
            addCriterion("DRUG_FACTOR_ID <>", value, "drugFactorId");
            return (Criteria) this;
        }

        public Criteria andDrugFactorIdGreaterThan(String value) {
            addCriterion("DRUG_FACTOR_ID >", value, "drugFactorId");
            return (Criteria) this;
        }

        public Criteria andDrugFactorIdGreaterThanOrEqualTo(String value) {
            addCriterion("DRUG_FACTOR_ID >=", value, "drugFactorId");
            return (Criteria) this;
        }

        public Criteria andDrugFactorIdLessThan(String value) {
            addCriterion("DRUG_FACTOR_ID <", value, "drugFactorId");
            return (Criteria) this;
        }

        public Criteria andDrugFactorIdLessThanOrEqualTo(String value) {
            addCriterion("DRUG_FACTOR_ID <=", value, "drugFactorId");
            return (Criteria) this;
        }

        public Criteria andDrugFactorIdLike(String value) {
            addCriterion("DRUG_FACTOR_ID like", value, "drugFactorId");
            return (Criteria) this;
        }

        public Criteria andDrugFactorIdNotLike(String value) {
            addCriterion("DRUG_FACTOR_ID not like", value, "drugFactorId");
            return (Criteria) this;
        }

        public Criteria andDrugFactorIdIn(List<String> values) {
            addCriterion("DRUG_FACTOR_ID in", values, "drugFactorId");
            return (Criteria) this;
        }

        public Criteria andDrugFactorIdNotIn(List<String> values) {
            addCriterion("DRUG_FACTOR_ID not in", values, "drugFactorId");
            return (Criteria) this;
        }

        public Criteria andDrugFactorIdBetween(String value1, String value2) {
            addCriterion("DRUG_FACTOR_ID between", value1, value2, "drugFactorId");
            return (Criteria) this;
        }

        public Criteria andDrugFactorIdNotBetween(String value1, String value2) {
            addCriterion("DRUG_FACTOR_ID not between", value1, value2, "drugFactorId");
            return (Criteria) this;
        }

        public Criteria andDrugFactorNameIsNull() {
            addCriterion("DRUG_FACTOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDrugFactorNameIsNotNull() {
            addCriterion("DRUG_FACTOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDrugFactorNameEqualTo(String value) {
            addCriterion("DRUG_FACTOR_NAME =", value, "drugFactorName");
            return (Criteria) this;
        }

        public Criteria andDrugFactorNameNotEqualTo(String value) {
            addCriterion("DRUG_FACTOR_NAME <>", value, "drugFactorName");
            return (Criteria) this;
        }

        public Criteria andDrugFactorNameGreaterThan(String value) {
            addCriterion("DRUG_FACTOR_NAME >", value, "drugFactorName");
            return (Criteria) this;
        }

        public Criteria andDrugFactorNameGreaterThanOrEqualTo(String value) {
            addCriterion("DRUG_FACTOR_NAME >=", value, "drugFactorName");
            return (Criteria) this;
        }

        public Criteria andDrugFactorNameLessThan(String value) {
            addCriterion("DRUG_FACTOR_NAME <", value, "drugFactorName");
            return (Criteria) this;
        }

        public Criteria andDrugFactorNameLessThanOrEqualTo(String value) {
            addCriterion("DRUG_FACTOR_NAME <=", value, "drugFactorName");
            return (Criteria) this;
        }

        public Criteria andDrugFactorNameLike(String value) {
            addCriterion("DRUG_FACTOR_NAME like", value, "drugFactorName");
            return (Criteria) this;
        }

        public Criteria andDrugFactorNameNotLike(String value) {
            addCriterion("DRUG_FACTOR_NAME not like", value, "drugFactorName");
            return (Criteria) this;
        }

        public Criteria andDrugFactorNameIn(List<String> values) {
            addCriterion("DRUG_FACTOR_NAME in", values, "drugFactorName");
            return (Criteria) this;
        }

        public Criteria andDrugFactorNameNotIn(List<String> values) {
            addCriterion("DRUG_FACTOR_NAME not in", values, "drugFactorName");
            return (Criteria) this;
        }

        public Criteria andDrugFactorNameBetween(String value1, String value2) {
            addCriterion("DRUG_FACTOR_NAME between", value1, value2, "drugFactorName");
            return (Criteria) this;
        }

        public Criteria andDrugFactorNameNotBetween(String value1, String value2) {
            addCriterion("DRUG_FACTOR_NAME not between", value1, value2, "drugFactorName");
            return (Criteria) this;
        }

        public Criteria andPatientFlowIsNull() {
            addCriterion("PATIENT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPatientFlowIsNotNull() {
            addCriterion("PATIENT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPatientFlowEqualTo(String value) {
            addCriterion("PATIENT_FLOW =", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowNotEqualTo(String value) {
            addCriterion("PATIENT_FLOW <>", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowGreaterThan(String value) {
            addCriterion("PATIENT_FLOW >", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_FLOW >=", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowLessThan(String value) {
            addCriterion("PATIENT_FLOW <", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_FLOW <=", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowLike(String value) {
            addCriterion("PATIENT_FLOW like", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowNotLike(String value) {
            addCriterion("PATIENT_FLOW not like", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowIn(List<String> values) {
            addCriterion("PATIENT_FLOW in", values, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowNotIn(List<String> values) {
            addCriterion("PATIENT_FLOW not in", values, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowBetween(String value1, String value2) {
            addCriterion("PATIENT_FLOW between", value1, value2, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowNotBetween(String value1, String value2) {
            addCriterion("PATIENT_FLOW not between", value1, value2, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientCodeIsNull() {
            addCriterion("PATIENT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPatientCodeIsNotNull() {
            addCriterion("PATIENT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPatientCodeEqualTo(String value) {
            addCriterion("PATIENT_CODE =", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeNotEqualTo(String value) {
            addCriterion("PATIENT_CODE <>", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeGreaterThan(String value) {
            addCriterion("PATIENT_CODE >", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_CODE >=", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeLessThan(String value) {
            addCriterion("PATIENT_CODE <", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_CODE <=", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeLike(String value) {
            addCriterion("PATIENT_CODE like", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeNotLike(String value) {
            addCriterion("PATIENT_CODE not like", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeIn(List<String> values) {
            addCriterion("PATIENT_CODE in", values, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeNotIn(List<String> values) {
            addCriterion("PATIENT_CODE not in", values, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeBetween(String value1, String value2) {
            addCriterion("PATIENT_CODE between", value1, value2, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeNotBetween(String value1, String value2) {
            addCriterion("PATIENT_CODE not between", value1, value2, "patientCode");
            return (Criteria) this;
        }

        public Criteria andRandomCodeIsNull() {
            addCriterion("RANDOM_CODE is null");
            return (Criteria) this;
        }

        public Criteria andRandomCodeIsNotNull() {
            addCriterion("RANDOM_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andRandomCodeEqualTo(String value) {
            addCriterion("RANDOM_CODE =", value, "randomCode");
            return (Criteria) this;
        }

        public Criteria andRandomCodeNotEqualTo(String value) {
            addCriterion("RANDOM_CODE <>", value, "randomCode");
            return (Criteria) this;
        }

        public Criteria andRandomCodeGreaterThan(String value) {
            addCriterion("RANDOM_CODE >", value, "randomCode");
            return (Criteria) this;
        }

        public Criteria andRandomCodeGreaterThanOrEqualTo(String value) {
            addCriterion("RANDOM_CODE >=", value, "randomCode");
            return (Criteria) this;
        }

        public Criteria andRandomCodeLessThan(String value) {
            addCriterion("RANDOM_CODE <", value, "randomCode");
            return (Criteria) this;
        }

        public Criteria andRandomCodeLessThanOrEqualTo(String value) {
            addCriterion("RANDOM_CODE <=", value, "randomCode");
            return (Criteria) this;
        }

        public Criteria andRandomCodeLike(String value) {
            addCriterion("RANDOM_CODE like", value, "randomCode");
            return (Criteria) this;
        }

        public Criteria andRandomCodeNotLike(String value) {
            addCriterion("RANDOM_CODE not like", value, "randomCode");
            return (Criteria) this;
        }

        public Criteria andRandomCodeIn(List<String> values) {
            addCriterion("RANDOM_CODE in", values, "randomCode");
            return (Criteria) this;
        }

        public Criteria andRandomCodeNotIn(List<String> values) {
            addCriterion("RANDOM_CODE not in", values, "randomCode");
            return (Criteria) this;
        }

        public Criteria andRandomCodeBetween(String value1, String value2) {
            addCriterion("RANDOM_CODE between", value1, value2, "randomCode");
            return (Criteria) this;
        }

        public Criteria andRandomCodeNotBetween(String value1, String value2) {
            addCriterion("RANDOM_CODE not between", value1, value2, "randomCode");
            return (Criteria) this;
        }

        public Criteria andAssignStatusIdIsNull() {
            addCriterion("ASSIGN_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andAssignStatusIdIsNotNull() {
            addCriterion("ASSIGN_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAssignStatusIdEqualTo(String value) {
            addCriterion("ASSIGN_STATUS_ID =", value, "assignStatusId");
            return (Criteria) this;
        }

        public Criteria andAssignStatusIdNotEqualTo(String value) {
            addCriterion("ASSIGN_STATUS_ID <>", value, "assignStatusId");
            return (Criteria) this;
        }

        public Criteria andAssignStatusIdGreaterThan(String value) {
            addCriterion("ASSIGN_STATUS_ID >", value, "assignStatusId");
            return (Criteria) this;
        }

        public Criteria andAssignStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("ASSIGN_STATUS_ID >=", value, "assignStatusId");
            return (Criteria) this;
        }

        public Criteria andAssignStatusIdLessThan(String value) {
            addCriterion("ASSIGN_STATUS_ID <", value, "assignStatusId");
            return (Criteria) this;
        }

        public Criteria andAssignStatusIdLessThanOrEqualTo(String value) {
            addCriterion("ASSIGN_STATUS_ID <=", value, "assignStatusId");
            return (Criteria) this;
        }

        public Criteria andAssignStatusIdLike(String value) {
            addCriterion("ASSIGN_STATUS_ID like", value, "assignStatusId");
            return (Criteria) this;
        }

        public Criteria andAssignStatusIdNotLike(String value) {
            addCriterion("ASSIGN_STATUS_ID not like", value, "assignStatusId");
            return (Criteria) this;
        }

        public Criteria andAssignStatusIdIn(List<String> values) {
            addCriterion("ASSIGN_STATUS_ID in", values, "assignStatusId");
            return (Criteria) this;
        }

        public Criteria andAssignStatusIdNotIn(List<String> values) {
            addCriterion("ASSIGN_STATUS_ID not in", values, "assignStatusId");
            return (Criteria) this;
        }

        public Criteria andAssignStatusIdBetween(String value1, String value2) {
            addCriterion("ASSIGN_STATUS_ID between", value1, value2, "assignStatusId");
            return (Criteria) this;
        }

        public Criteria andAssignStatusIdNotBetween(String value1, String value2) {
            addCriterion("ASSIGN_STATUS_ID not between", value1, value2, "assignStatusId");
            return (Criteria) this;
        }

        public Criteria andAssignStatusNameIsNull() {
            addCriterion("ASSIGN_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAssignStatusNameIsNotNull() {
            addCriterion("ASSIGN_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAssignStatusNameEqualTo(String value) {
            addCriterion("ASSIGN_STATUS_NAME =", value, "assignStatusName");
            return (Criteria) this;
        }

        public Criteria andAssignStatusNameNotEqualTo(String value) {
            addCriterion("ASSIGN_STATUS_NAME <>", value, "assignStatusName");
            return (Criteria) this;
        }

        public Criteria andAssignStatusNameGreaterThan(String value) {
            addCriterion("ASSIGN_STATUS_NAME >", value, "assignStatusName");
            return (Criteria) this;
        }

        public Criteria andAssignStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("ASSIGN_STATUS_NAME >=", value, "assignStatusName");
            return (Criteria) this;
        }

        public Criteria andAssignStatusNameLessThan(String value) {
            addCriterion("ASSIGN_STATUS_NAME <", value, "assignStatusName");
            return (Criteria) this;
        }

        public Criteria andAssignStatusNameLessThanOrEqualTo(String value) {
            addCriterion("ASSIGN_STATUS_NAME <=", value, "assignStatusName");
            return (Criteria) this;
        }

        public Criteria andAssignStatusNameLike(String value) {
            addCriterion("ASSIGN_STATUS_NAME like", value, "assignStatusName");
            return (Criteria) this;
        }

        public Criteria andAssignStatusNameNotLike(String value) {
            addCriterion("ASSIGN_STATUS_NAME not like", value, "assignStatusName");
            return (Criteria) this;
        }

        public Criteria andAssignStatusNameIn(List<String> values) {
            addCriterion("ASSIGN_STATUS_NAME in", values, "assignStatusName");
            return (Criteria) this;
        }

        public Criteria andAssignStatusNameNotIn(List<String> values) {
            addCriterion("ASSIGN_STATUS_NAME not in", values, "assignStatusName");
            return (Criteria) this;
        }

        public Criteria andAssignStatusNameBetween(String value1, String value2) {
            addCriterion("ASSIGN_STATUS_NAME between", value1, value2, "assignStatusName");
            return (Criteria) this;
        }

        public Criteria andAssignStatusNameNotBetween(String value1, String value2) {
            addCriterion("ASSIGN_STATUS_NAME not between", value1, value2, "assignStatusName");
            return (Criteria) this;
        }

        public Criteria andAssignLabelIdIsNull() {
            addCriterion("ASSIGN_LABEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andAssignLabelIdIsNotNull() {
            addCriterion("ASSIGN_LABEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAssignLabelIdEqualTo(String value) {
            addCriterion("ASSIGN_LABEL_ID =", value, "assignLabelId");
            return (Criteria) this;
        }

        public Criteria andAssignLabelIdNotEqualTo(String value) {
            addCriterion("ASSIGN_LABEL_ID <>", value, "assignLabelId");
            return (Criteria) this;
        }

        public Criteria andAssignLabelIdGreaterThan(String value) {
            addCriterion("ASSIGN_LABEL_ID >", value, "assignLabelId");
            return (Criteria) this;
        }

        public Criteria andAssignLabelIdGreaterThanOrEqualTo(String value) {
            addCriterion("ASSIGN_LABEL_ID >=", value, "assignLabelId");
            return (Criteria) this;
        }

        public Criteria andAssignLabelIdLessThan(String value) {
            addCriterion("ASSIGN_LABEL_ID <", value, "assignLabelId");
            return (Criteria) this;
        }

        public Criteria andAssignLabelIdLessThanOrEqualTo(String value) {
            addCriterion("ASSIGN_LABEL_ID <=", value, "assignLabelId");
            return (Criteria) this;
        }

        public Criteria andAssignLabelIdLike(String value) {
            addCriterion("ASSIGN_LABEL_ID like", value, "assignLabelId");
            return (Criteria) this;
        }

        public Criteria andAssignLabelIdNotLike(String value) {
            addCriterion("ASSIGN_LABEL_ID not like", value, "assignLabelId");
            return (Criteria) this;
        }

        public Criteria andAssignLabelIdIn(List<String> values) {
            addCriterion("ASSIGN_LABEL_ID in", values, "assignLabelId");
            return (Criteria) this;
        }

        public Criteria andAssignLabelIdNotIn(List<String> values) {
            addCriterion("ASSIGN_LABEL_ID not in", values, "assignLabelId");
            return (Criteria) this;
        }

        public Criteria andAssignLabelIdBetween(String value1, String value2) {
            addCriterion("ASSIGN_LABEL_ID between", value1, value2, "assignLabelId");
            return (Criteria) this;
        }

        public Criteria andAssignLabelIdNotBetween(String value1, String value2) {
            addCriterion("ASSIGN_LABEL_ID not between", value1, value2, "assignLabelId");
            return (Criteria) this;
        }

        public Criteria andAssignLabelNameIsNull() {
            addCriterion("ASSIGN_LABEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAssignLabelNameIsNotNull() {
            addCriterion("ASSIGN_LABEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAssignLabelNameEqualTo(String value) {
            addCriterion("ASSIGN_LABEL_NAME =", value, "assignLabelName");
            return (Criteria) this;
        }

        public Criteria andAssignLabelNameNotEqualTo(String value) {
            addCriterion("ASSIGN_LABEL_NAME <>", value, "assignLabelName");
            return (Criteria) this;
        }

        public Criteria andAssignLabelNameGreaterThan(String value) {
            addCriterion("ASSIGN_LABEL_NAME >", value, "assignLabelName");
            return (Criteria) this;
        }

        public Criteria andAssignLabelNameGreaterThanOrEqualTo(String value) {
            addCriterion("ASSIGN_LABEL_NAME >=", value, "assignLabelName");
            return (Criteria) this;
        }

        public Criteria andAssignLabelNameLessThan(String value) {
            addCriterion("ASSIGN_LABEL_NAME <", value, "assignLabelName");
            return (Criteria) this;
        }

        public Criteria andAssignLabelNameLessThanOrEqualTo(String value) {
            addCriterion("ASSIGN_LABEL_NAME <=", value, "assignLabelName");
            return (Criteria) this;
        }

        public Criteria andAssignLabelNameLike(String value) {
            addCriterion("ASSIGN_LABEL_NAME like", value, "assignLabelName");
            return (Criteria) this;
        }

        public Criteria andAssignLabelNameNotLike(String value) {
            addCriterion("ASSIGN_LABEL_NAME not like", value, "assignLabelName");
            return (Criteria) this;
        }

        public Criteria andAssignLabelNameIn(List<String> values) {
            addCriterion("ASSIGN_LABEL_NAME in", values, "assignLabelName");
            return (Criteria) this;
        }

        public Criteria andAssignLabelNameNotIn(List<String> values) {
            addCriterion("ASSIGN_LABEL_NAME not in", values, "assignLabelName");
            return (Criteria) this;
        }

        public Criteria andAssignLabelNameBetween(String value1, String value2) {
            addCriterion("ASSIGN_LABEL_NAME between", value1, value2, "assignLabelName");
            return (Criteria) this;
        }

        public Criteria andAssignLabelNameNotBetween(String value1, String value2) {
            addCriterion("ASSIGN_LABEL_NAME not between", value1, value2, "assignLabelName");
            return (Criteria) this;
        }

        public Criteria andAssignTypeIdIsNull() {
            addCriterion("ASSIGN_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andAssignTypeIdIsNotNull() {
            addCriterion("ASSIGN_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAssignTypeIdEqualTo(String value) {
            addCriterion("ASSIGN_TYPE_ID =", value, "assignTypeId");
            return (Criteria) this;
        }

        public Criteria andAssignTypeIdNotEqualTo(String value) {
            addCriterion("ASSIGN_TYPE_ID <>", value, "assignTypeId");
            return (Criteria) this;
        }

        public Criteria andAssignTypeIdGreaterThan(String value) {
            addCriterion("ASSIGN_TYPE_ID >", value, "assignTypeId");
            return (Criteria) this;
        }

        public Criteria andAssignTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ASSIGN_TYPE_ID >=", value, "assignTypeId");
            return (Criteria) this;
        }

        public Criteria andAssignTypeIdLessThan(String value) {
            addCriterion("ASSIGN_TYPE_ID <", value, "assignTypeId");
            return (Criteria) this;
        }

        public Criteria andAssignTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ASSIGN_TYPE_ID <=", value, "assignTypeId");
            return (Criteria) this;
        }

        public Criteria andAssignTypeIdLike(String value) {
            addCriterion("ASSIGN_TYPE_ID like", value, "assignTypeId");
            return (Criteria) this;
        }

        public Criteria andAssignTypeIdNotLike(String value) {
            addCriterion("ASSIGN_TYPE_ID not like", value, "assignTypeId");
            return (Criteria) this;
        }

        public Criteria andAssignTypeIdIn(List<String> values) {
            addCriterion("ASSIGN_TYPE_ID in", values, "assignTypeId");
            return (Criteria) this;
        }

        public Criteria andAssignTypeIdNotIn(List<String> values) {
            addCriterion("ASSIGN_TYPE_ID not in", values, "assignTypeId");
            return (Criteria) this;
        }

        public Criteria andAssignTypeIdBetween(String value1, String value2) {
            addCriterion("ASSIGN_TYPE_ID between", value1, value2, "assignTypeId");
            return (Criteria) this;
        }

        public Criteria andAssignTypeIdNotBetween(String value1, String value2) {
            addCriterion("ASSIGN_TYPE_ID not between", value1, value2, "assignTypeId");
            return (Criteria) this;
        }

        public Criteria andAssignTypeNameIsNull() {
            addCriterion("ASSIGN_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAssignTypeNameIsNotNull() {
            addCriterion("ASSIGN_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAssignTypeNameEqualTo(String value) {
            addCriterion("ASSIGN_TYPE_NAME =", value, "assignTypeName");
            return (Criteria) this;
        }

        public Criteria andAssignTypeNameNotEqualTo(String value) {
            addCriterion("ASSIGN_TYPE_NAME <>", value, "assignTypeName");
            return (Criteria) this;
        }

        public Criteria andAssignTypeNameGreaterThan(String value) {
            addCriterion("ASSIGN_TYPE_NAME >", value, "assignTypeName");
            return (Criteria) this;
        }

        public Criteria andAssignTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("ASSIGN_TYPE_NAME >=", value, "assignTypeName");
            return (Criteria) this;
        }

        public Criteria andAssignTypeNameLessThan(String value) {
            addCriterion("ASSIGN_TYPE_NAME <", value, "assignTypeName");
            return (Criteria) this;
        }

        public Criteria andAssignTypeNameLessThanOrEqualTo(String value) {
            addCriterion("ASSIGN_TYPE_NAME <=", value, "assignTypeName");
            return (Criteria) this;
        }

        public Criteria andAssignTypeNameLike(String value) {
            addCriterion("ASSIGN_TYPE_NAME like", value, "assignTypeName");
            return (Criteria) this;
        }

        public Criteria andAssignTypeNameNotLike(String value) {
            addCriterion("ASSIGN_TYPE_NAME not like", value, "assignTypeName");
            return (Criteria) this;
        }

        public Criteria andAssignTypeNameIn(List<String> values) {
            addCriterion("ASSIGN_TYPE_NAME in", values, "assignTypeName");
            return (Criteria) this;
        }

        public Criteria andAssignTypeNameNotIn(List<String> values) {
            addCriterion("ASSIGN_TYPE_NAME not in", values, "assignTypeName");
            return (Criteria) this;
        }

        public Criteria andAssignTypeNameBetween(String value1, String value2) {
            addCriterion("ASSIGN_TYPE_NAME between", value1, value2, "assignTypeName");
            return (Criteria) this;
        }

        public Criteria andAssignTypeNameNotBetween(String value1, String value2) {
            addCriterion("ASSIGN_TYPE_NAME not between", value1, value2, "assignTypeName");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowIsNull() {
            addCriterion("ASSIGN_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowIsNotNull() {
            addCriterion("ASSIGN_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowEqualTo(String value) {
            addCriterion("ASSIGN_USER_FLOW =", value, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowNotEqualTo(String value) {
            addCriterion("ASSIGN_USER_FLOW <>", value, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowGreaterThan(String value) {
            addCriterion("ASSIGN_USER_FLOW >", value, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ASSIGN_USER_FLOW >=", value, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowLessThan(String value) {
            addCriterion("ASSIGN_USER_FLOW <", value, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowLessThanOrEqualTo(String value) {
            addCriterion("ASSIGN_USER_FLOW <=", value, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowLike(String value) {
            addCriterion("ASSIGN_USER_FLOW like", value, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowNotLike(String value) {
            addCriterion("ASSIGN_USER_FLOW not like", value, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowIn(List<String> values) {
            addCriterion("ASSIGN_USER_FLOW in", values, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowNotIn(List<String> values) {
            addCriterion("ASSIGN_USER_FLOW not in", values, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowBetween(String value1, String value2) {
            addCriterion("ASSIGN_USER_FLOW between", value1, value2, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowNotBetween(String value1, String value2) {
            addCriterion("ASSIGN_USER_FLOW not between", value1, value2, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameIsNull() {
            addCriterion("ASSIGN_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameIsNotNull() {
            addCriterion("ASSIGN_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameEqualTo(String value) {
            addCriterion("ASSIGN_USER_NAME =", value, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameNotEqualTo(String value) {
            addCriterion("ASSIGN_USER_NAME <>", value, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameGreaterThan(String value) {
            addCriterion("ASSIGN_USER_NAME >", value, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("ASSIGN_USER_NAME >=", value, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameLessThan(String value) {
            addCriterion("ASSIGN_USER_NAME <", value, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameLessThanOrEqualTo(String value) {
            addCriterion("ASSIGN_USER_NAME <=", value, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameLike(String value) {
            addCriterion("ASSIGN_USER_NAME like", value, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameNotLike(String value) {
            addCriterion("ASSIGN_USER_NAME not like", value, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameIn(List<String> values) {
            addCriterion("ASSIGN_USER_NAME in", values, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameNotIn(List<String> values) {
            addCriterion("ASSIGN_USER_NAME not in", values, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameBetween(String value1, String value2) {
            addCriterion("ASSIGN_USER_NAME between", value1, value2, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameNotBetween(String value1, String value2) {
            addCriterion("ASSIGN_USER_NAME not between", value1, value2, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignTimeIsNull() {
            addCriterion("ASSIGN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAssignTimeIsNotNull() {
            addCriterion("ASSIGN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAssignTimeEqualTo(String value) {
            addCriterion("ASSIGN_TIME =", value, "assignTime");
            return (Criteria) this;
        }

        public Criteria andAssignTimeNotEqualTo(String value) {
            addCriterion("ASSIGN_TIME <>", value, "assignTime");
            return (Criteria) this;
        }

        public Criteria andAssignTimeGreaterThan(String value) {
            addCriterion("ASSIGN_TIME >", value, "assignTime");
            return (Criteria) this;
        }

        public Criteria andAssignTimeGreaterThanOrEqualTo(String value) {
            addCriterion("ASSIGN_TIME >=", value, "assignTime");
            return (Criteria) this;
        }

        public Criteria andAssignTimeLessThan(String value) {
            addCriterion("ASSIGN_TIME <", value, "assignTime");
            return (Criteria) this;
        }

        public Criteria andAssignTimeLessThanOrEqualTo(String value) {
            addCriterion("ASSIGN_TIME <=", value, "assignTime");
            return (Criteria) this;
        }

        public Criteria andAssignTimeLike(String value) {
            addCriterion("ASSIGN_TIME like", value, "assignTime");
            return (Criteria) this;
        }

        public Criteria andAssignTimeNotLike(String value) {
            addCriterion("ASSIGN_TIME not like", value, "assignTime");
            return (Criteria) this;
        }

        public Criteria andAssignTimeIn(List<String> values) {
            addCriterion("ASSIGN_TIME in", values, "assignTime");
            return (Criteria) this;
        }

        public Criteria andAssignTimeNotIn(List<String> values) {
            addCriterion("ASSIGN_TIME not in", values, "assignTime");
            return (Criteria) this;
        }

        public Criteria andAssignTimeBetween(String value1, String value2) {
            addCriterion("ASSIGN_TIME between", value1, value2, "assignTime");
            return (Criteria) this;
        }

        public Criteria andAssignTimeNotBetween(String value1, String value2) {
            addCriterion("ASSIGN_TIME not between", value1, value2, "assignTime");
            return (Criteria) this;
        }

        public Criteria andAssignEmailIsNull() {
            addCriterion("ASSIGN_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andAssignEmailIsNotNull() {
            addCriterion("ASSIGN_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andAssignEmailEqualTo(String value) {
            addCriterion("ASSIGN_EMAIL =", value, "assignEmail");
            return (Criteria) this;
        }

        public Criteria andAssignEmailNotEqualTo(String value) {
            addCriterion("ASSIGN_EMAIL <>", value, "assignEmail");
            return (Criteria) this;
        }

        public Criteria andAssignEmailGreaterThan(String value) {
            addCriterion("ASSIGN_EMAIL >", value, "assignEmail");
            return (Criteria) this;
        }

        public Criteria andAssignEmailGreaterThanOrEqualTo(String value) {
            addCriterion("ASSIGN_EMAIL >=", value, "assignEmail");
            return (Criteria) this;
        }

        public Criteria andAssignEmailLessThan(String value) {
            addCriterion("ASSIGN_EMAIL <", value, "assignEmail");
            return (Criteria) this;
        }

        public Criteria andAssignEmailLessThanOrEqualTo(String value) {
            addCriterion("ASSIGN_EMAIL <=", value, "assignEmail");
            return (Criteria) this;
        }

        public Criteria andAssignEmailLike(String value) {
            addCriterion("ASSIGN_EMAIL like", value, "assignEmail");
            return (Criteria) this;
        }

        public Criteria andAssignEmailNotLike(String value) {
            addCriterion("ASSIGN_EMAIL not like", value, "assignEmail");
            return (Criteria) this;
        }

        public Criteria andAssignEmailIn(List<String> values) {
            addCriterion("ASSIGN_EMAIL in", values, "assignEmail");
            return (Criteria) this;
        }

        public Criteria andAssignEmailNotIn(List<String> values) {
            addCriterion("ASSIGN_EMAIL not in", values, "assignEmail");
            return (Criteria) this;
        }

        public Criteria andAssignEmailBetween(String value1, String value2) {
            addCriterion("ASSIGN_EMAIL between", value1, value2, "assignEmail");
            return (Criteria) this;
        }

        public Criteria andAssignEmailNotBetween(String value1, String value2) {
            addCriterion("ASSIGN_EMAIL not between", value1, value2, "assignEmail");
            return (Criteria) this;
        }

        public Criteria andPromptStatusIdIsNull() {
            addCriterion("PROMPT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andPromptStatusIdIsNotNull() {
            addCriterion("PROMPT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPromptStatusIdEqualTo(String value) {
            addCriterion("PROMPT_STATUS_ID =", value, "promptStatusId");
            return (Criteria) this;
        }

        public Criteria andPromptStatusIdNotEqualTo(String value) {
            addCriterion("PROMPT_STATUS_ID <>", value, "promptStatusId");
            return (Criteria) this;
        }

        public Criteria andPromptStatusIdGreaterThan(String value) {
            addCriterion("PROMPT_STATUS_ID >", value, "promptStatusId");
            return (Criteria) this;
        }

        public Criteria andPromptStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROMPT_STATUS_ID >=", value, "promptStatusId");
            return (Criteria) this;
        }

        public Criteria andPromptStatusIdLessThan(String value) {
            addCriterion("PROMPT_STATUS_ID <", value, "promptStatusId");
            return (Criteria) this;
        }

        public Criteria andPromptStatusIdLessThanOrEqualTo(String value) {
            addCriterion("PROMPT_STATUS_ID <=", value, "promptStatusId");
            return (Criteria) this;
        }

        public Criteria andPromptStatusIdLike(String value) {
            addCriterion("PROMPT_STATUS_ID like", value, "promptStatusId");
            return (Criteria) this;
        }

        public Criteria andPromptStatusIdNotLike(String value) {
            addCriterion("PROMPT_STATUS_ID not like", value, "promptStatusId");
            return (Criteria) this;
        }

        public Criteria andPromptStatusIdIn(List<String> values) {
            addCriterion("PROMPT_STATUS_ID in", values, "promptStatusId");
            return (Criteria) this;
        }

        public Criteria andPromptStatusIdNotIn(List<String> values) {
            addCriterion("PROMPT_STATUS_ID not in", values, "promptStatusId");
            return (Criteria) this;
        }

        public Criteria andPromptStatusIdBetween(String value1, String value2) {
            addCriterion("PROMPT_STATUS_ID between", value1, value2, "promptStatusId");
            return (Criteria) this;
        }

        public Criteria andPromptStatusIdNotBetween(String value1, String value2) {
            addCriterion("PROMPT_STATUS_ID not between", value1, value2, "promptStatusId");
            return (Criteria) this;
        }

        public Criteria andPromptStatusNameIsNull() {
            addCriterion("PROMPT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPromptStatusNameIsNotNull() {
            addCriterion("PROMPT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPromptStatusNameEqualTo(String value) {
            addCriterion("PROMPT_STATUS_NAME =", value, "promptStatusName");
            return (Criteria) this;
        }

        public Criteria andPromptStatusNameNotEqualTo(String value) {
            addCriterion("PROMPT_STATUS_NAME <>", value, "promptStatusName");
            return (Criteria) this;
        }

        public Criteria andPromptStatusNameGreaterThan(String value) {
            addCriterion("PROMPT_STATUS_NAME >", value, "promptStatusName");
            return (Criteria) this;
        }

        public Criteria andPromptStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROMPT_STATUS_NAME >=", value, "promptStatusName");
            return (Criteria) this;
        }

        public Criteria andPromptStatusNameLessThan(String value) {
            addCriterion("PROMPT_STATUS_NAME <", value, "promptStatusName");
            return (Criteria) this;
        }

        public Criteria andPromptStatusNameLessThanOrEqualTo(String value) {
            addCriterion("PROMPT_STATUS_NAME <=", value, "promptStatusName");
            return (Criteria) this;
        }

        public Criteria andPromptStatusNameLike(String value) {
            addCriterion("PROMPT_STATUS_NAME like", value, "promptStatusName");
            return (Criteria) this;
        }

        public Criteria andPromptStatusNameNotLike(String value) {
            addCriterion("PROMPT_STATUS_NAME not like", value, "promptStatusName");
            return (Criteria) this;
        }

        public Criteria andPromptStatusNameIn(List<String> values) {
            addCriterion("PROMPT_STATUS_NAME in", values, "promptStatusName");
            return (Criteria) this;
        }

        public Criteria andPromptStatusNameNotIn(List<String> values) {
            addCriterion("PROMPT_STATUS_NAME not in", values, "promptStatusName");
            return (Criteria) this;
        }

        public Criteria andPromptStatusNameBetween(String value1, String value2) {
            addCriterion("PROMPT_STATUS_NAME between", value1, value2, "promptStatusName");
            return (Criteria) this;
        }

        public Criteria andPromptStatusNameNotBetween(String value1, String value2) {
            addCriterion("PROMPT_STATUS_NAME not between", value1, value2, "promptStatusName");
            return (Criteria) this;
        }

        public Criteria andPromptTimeIsNull() {
            addCriterion("PROMPT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPromptTimeIsNotNull() {
            addCriterion("PROMPT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPromptTimeEqualTo(String value) {
            addCriterion("PROMPT_TIME =", value, "promptTime");
            return (Criteria) this;
        }

        public Criteria andPromptTimeNotEqualTo(String value) {
            addCriterion("PROMPT_TIME <>", value, "promptTime");
            return (Criteria) this;
        }

        public Criteria andPromptTimeGreaterThan(String value) {
            addCriterion("PROMPT_TIME >", value, "promptTime");
            return (Criteria) this;
        }

        public Criteria andPromptTimeGreaterThanOrEqualTo(String value) {
            addCriterion("PROMPT_TIME >=", value, "promptTime");
            return (Criteria) this;
        }

        public Criteria andPromptTimeLessThan(String value) {
            addCriterion("PROMPT_TIME <", value, "promptTime");
            return (Criteria) this;
        }

        public Criteria andPromptTimeLessThanOrEqualTo(String value) {
            addCriterion("PROMPT_TIME <=", value, "promptTime");
            return (Criteria) this;
        }

        public Criteria andPromptTimeLike(String value) {
            addCriterion("PROMPT_TIME like", value, "promptTime");
            return (Criteria) this;
        }

        public Criteria andPromptTimeNotLike(String value) {
            addCriterion("PROMPT_TIME not like", value, "promptTime");
            return (Criteria) this;
        }

        public Criteria andPromptTimeIn(List<String> values) {
            addCriterion("PROMPT_TIME in", values, "promptTime");
            return (Criteria) this;
        }

        public Criteria andPromptTimeNotIn(List<String> values) {
            addCriterion("PROMPT_TIME not in", values, "promptTime");
            return (Criteria) this;
        }

        public Criteria andPromptTimeBetween(String value1, String value2) {
            addCriterion("PROMPT_TIME between", value1, value2, "promptTime");
            return (Criteria) this;
        }

        public Criteria andPromptTimeNotBetween(String value1, String value2) {
            addCriterion("PROMPT_TIME not between", value1, value2, "promptTime");
            return (Criteria) this;
        }

        public Criteria andPromptUserFlowIsNull() {
            addCriterion("PROMPT_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPromptUserFlowIsNotNull() {
            addCriterion("PROMPT_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPromptUserFlowEqualTo(String value) {
            addCriterion("PROMPT_USER_FLOW =", value, "promptUserFlow");
            return (Criteria) this;
        }

        public Criteria andPromptUserFlowNotEqualTo(String value) {
            addCriterion("PROMPT_USER_FLOW <>", value, "promptUserFlow");
            return (Criteria) this;
        }

        public Criteria andPromptUserFlowGreaterThan(String value) {
            addCriterion("PROMPT_USER_FLOW >", value, "promptUserFlow");
            return (Criteria) this;
        }

        public Criteria andPromptUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PROMPT_USER_FLOW >=", value, "promptUserFlow");
            return (Criteria) this;
        }

        public Criteria andPromptUserFlowLessThan(String value) {
            addCriterion("PROMPT_USER_FLOW <", value, "promptUserFlow");
            return (Criteria) this;
        }

        public Criteria andPromptUserFlowLessThanOrEqualTo(String value) {
            addCriterion("PROMPT_USER_FLOW <=", value, "promptUserFlow");
            return (Criteria) this;
        }

        public Criteria andPromptUserFlowLike(String value) {
            addCriterion("PROMPT_USER_FLOW like", value, "promptUserFlow");
            return (Criteria) this;
        }

        public Criteria andPromptUserFlowNotLike(String value) {
            addCriterion("PROMPT_USER_FLOW not like", value, "promptUserFlow");
            return (Criteria) this;
        }

        public Criteria andPromptUserFlowIn(List<String> values) {
            addCriterion("PROMPT_USER_FLOW in", values, "promptUserFlow");
            return (Criteria) this;
        }

        public Criteria andPromptUserFlowNotIn(List<String> values) {
            addCriterion("PROMPT_USER_FLOW not in", values, "promptUserFlow");
            return (Criteria) this;
        }

        public Criteria andPromptUserFlowBetween(String value1, String value2) {
            addCriterion("PROMPT_USER_FLOW between", value1, value2, "promptUserFlow");
            return (Criteria) this;
        }

        public Criteria andPromptUserFlowNotBetween(String value1, String value2) {
            addCriterion("PROMPT_USER_FLOW not between", value1, value2, "promptUserFlow");
            return (Criteria) this;
        }

        public Criteria andPromptUserNameIsNull() {
            addCriterion("PROMPT_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPromptUserNameIsNotNull() {
            addCriterion("PROMPT_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPromptUserNameEqualTo(String value) {
            addCriterion("PROMPT_USER_NAME =", value, "promptUserName");
            return (Criteria) this;
        }

        public Criteria andPromptUserNameNotEqualTo(String value) {
            addCriterion("PROMPT_USER_NAME <>", value, "promptUserName");
            return (Criteria) this;
        }

        public Criteria andPromptUserNameGreaterThan(String value) {
            addCriterion("PROMPT_USER_NAME >", value, "promptUserName");
            return (Criteria) this;
        }

        public Criteria andPromptUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROMPT_USER_NAME >=", value, "promptUserName");
            return (Criteria) this;
        }

        public Criteria andPromptUserNameLessThan(String value) {
            addCriterion("PROMPT_USER_NAME <", value, "promptUserName");
            return (Criteria) this;
        }

        public Criteria andPromptUserNameLessThanOrEqualTo(String value) {
            addCriterion("PROMPT_USER_NAME <=", value, "promptUserName");
            return (Criteria) this;
        }

        public Criteria andPromptUserNameLike(String value) {
            addCriterion("PROMPT_USER_NAME like", value, "promptUserName");
            return (Criteria) this;
        }

        public Criteria andPromptUserNameNotLike(String value) {
            addCriterion("PROMPT_USER_NAME not like", value, "promptUserName");
            return (Criteria) this;
        }

        public Criteria andPromptUserNameIn(List<String> values) {
            addCriterion("PROMPT_USER_NAME in", values, "promptUserName");
            return (Criteria) this;
        }

        public Criteria andPromptUserNameNotIn(List<String> values) {
            addCriterion("PROMPT_USER_NAME not in", values, "promptUserName");
            return (Criteria) this;
        }

        public Criteria andPromptUserNameBetween(String value1, String value2) {
            addCriterion("PROMPT_USER_NAME between", value1, value2, "promptUserName");
            return (Criteria) this;
        }

        public Criteria andPromptUserNameNotBetween(String value1, String value2) {
            addCriterion("PROMPT_USER_NAME not between", value1, value2, "promptUserName");
            return (Criteria) this;
        }

        public Criteria andPromptEmailIsNull() {
            addCriterion("PROMPT_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andPromptEmailIsNotNull() {
            addCriterion("PROMPT_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andPromptEmailEqualTo(String value) {
            addCriterion("PROMPT_EMAIL =", value, "promptEmail");
            return (Criteria) this;
        }

        public Criteria andPromptEmailNotEqualTo(String value) {
            addCriterion("PROMPT_EMAIL <>", value, "promptEmail");
            return (Criteria) this;
        }

        public Criteria andPromptEmailGreaterThan(String value) {
            addCriterion("PROMPT_EMAIL >", value, "promptEmail");
            return (Criteria) this;
        }

        public Criteria andPromptEmailGreaterThanOrEqualTo(String value) {
            addCriterion("PROMPT_EMAIL >=", value, "promptEmail");
            return (Criteria) this;
        }

        public Criteria andPromptEmailLessThan(String value) {
            addCriterion("PROMPT_EMAIL <", value, "promptEmail");
            return (Criteria) this;
        }

        public Criteria andPromptEmailLessThanOrEqualTo(String value) {
            addCriterion("PROMPT_EMAIL <=", value, "promptEmail");
            return (Criteria) this;
        }

        public Criteria andPromptEmailLike(String value) {
            addCriterion("PROMPT_EMAIL like", value, "promptEmail");
            return (Criteria) this;
        }

        public Criteria andPromptEmailNotLike(String value) {
            addCriterion("PROMPT_EMAIL not like", value, "promptEmail");
            return (Criteria) this;
        }

        public Criteria andPromptEmailIn(List<String> values) {
            addCriterion("PROMPT_EMAIL in", values, "promptEmail");
            return (Criteria) this;
        }

        public Criteria andPromptEmailNotIn(List<String> values) {
            addCriterion("PROMPT_EMAIL not in", values, "promptEmail");
            return (Criteria) this;
        }

        public Criteria andPromptEmailBetween(String value1, String value2) {
            addCriterion("PROMPT_EMAIL between", value1, value2, "promptEmail");
            return (Criteria) this;
        }

        public Criteria andPromptEmailNotBetween(String value1, String value2) {
            addCriterion("PROMPT_EMAIL not between", value1, value2, "promptEmail");
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