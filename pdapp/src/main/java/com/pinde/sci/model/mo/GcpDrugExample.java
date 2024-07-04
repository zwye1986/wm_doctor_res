package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class GcpDrugExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GcpDrugExample() {
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

        public Criteria andDrugFlowIsNull() {
            addCriterion("DRUG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDrugFlowIsNotNull() {
            addCriterion("DRUG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDrugFlowEqualTo(String value) {
            addCriterion("DRUG_FLOW =", value, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowNotEqualTo(String value) {
            addCriterion("DRUG_FLOW <>", value, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowGreaterThan(String value) {
            addCriterion("DRUG_FLOW >", value, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DRUG_FLOW >=", value, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowLessThan(String value) {
            addCriterion("DRUG_FLOW <", value, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowLessThanOrEqualTo(String value) {
            addCriterion("DRUG_FLOW <=", value, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowLike(String value) {
            addCriterion("DRUG_FLOW like", value, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowNotLike(String value) {
            addCriterion("DRUG_FLOW not like", value, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowIn(List<String> values) {
            addCriterion("DRUG_FLOW in", values, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowNotIn(List<String> values) {
            addCriterion("DRUG_FLOW not in", values, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowBetween(String value1, String value2) {
            addCriterion("DRUG_FLOW between", value1, value2, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowNotBetween(String value1, String value2) {
            addCriterion("DRUG_FLOW not between", value1, value2, "drugFlow");
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

        public Criteria andDrugNameIsNull() {
            addCriterion("DRUG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDrugNameIsNotNull() {
            addCriterion("DRUG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDrugNameEqualTo(String value) {
            addCriterion("DRUG_NAME =", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameNotEqualTo(String value) {
            addCriterion("DRUG_NAME <>", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameGreaterThan(String value) {
            addCriterion("DRUG_NAME >", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameGreaterThanOrEqualTo(String value) {
            addCriterion("DRUG_NAME >=", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameLessThan(String value) {
            addCriterion("DRUG_NAME <", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameLessThanOrEqualTo(String value) {
            addCriterion("DRUG_NAME <=", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameLike(String value) {
            addCriterion("DRUG_NAME like", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameNotLike(String value) {
            addCriterion("DRUG_NAME not like", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameIn(List<String> values) {
            addCriterion("DRUG_NAME in", values, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameNotIn(List<String> values) {
            addCriterion("DRUG_NAME not in", values, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameBetween(String value1, String value2) {
            addCriterion("DRUG_NAME between", value1, value2, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameNotBetween(String value1, String value2) {
            addCriterion("DRUG_NAME not between", value1, value2, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugTypeIdIsNull() {
            addCriterion("DRUG_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDrugTypeIdIsNotNull() {
            addCriterion("DRUG_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDrugTypeIdEqualTo(String value) {
            addCriterion("DRUG_TYPE_ID =", value, "drugTypeId");
            return (Criteria) this;
        }

        public Criteria andDrugTypeIdNotEqualTo(String value) {
            addCriterion("DRUG_TYPE_ID <>", value, "drugTypeId");
            return (Criteria) this;
        }

        public Criteria andDrugTypeIdGreaterThan(String value) {
            addCriterion("DRUG_TYPE_ID >", value, "drugTypeId");
            return (Criteria) this;
        }

        public Criteria andDrugTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("DRUG_TYPE_ID >=", value, "drugTypeId");
            return (Criteria) this;
        }

        public Criteria andDrugTypeIdLessThan(String value) {
            addCriterion("DRUG_TYPE_ID <", value, "drugTypeId");
            return (Criteria) this;
        }

        public Criteria andDrugTypeIdLessThanOrEqualTo(String value) {
            addCriterion("DRUG_TYPE_ID <=", value, "drugTypeId");
            return (Criteria) this;
        }

        public Criteria andDrugTypeIdLike(String value) {
            addCriterion("DRUG_TYPE_ID like", value, "drugTypeId");
            return (Criteria) this;
        }

        public Criteria andDrugTypeIdNotLike(String value) {
            addCriterion("DRUG_TYPE_ID not like", value, "drugTypeId");
            return (Criteria) this;
        }

        public Criteria andDrugTypeIdIn(List<String> values) {
            addCriterion("DRUG_TYPE_ID in", values, "drugTypeId");
            return (Criteria) this;
        }

        public Criteria andDrugTypeIdNotIn(List<String> values) {
            addCriterion("DRUG_TYPE_ID not in", values, "drugTypeId");
            return (Criteria) this;
        }

        public Criteria andDrugTypeIdBetween(String value1, String value2) {
            addCriterion("DRUG_TYPE_ID between", value1, value2, "drugTypeId");
            return (Criteria) this;
        }

        public Criteria andDrugTypeIdNotBetween(String value1, String value2) {
            addCriterion("DRUG_TYPE_ID not between", value1, value2, "drugTypeId");
            return (Criteria) this;
        }

        public Criteria andDrugTypeNameIsNull() {
            addCriterion("DRUG_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDrugTypeNameIsNotNull() {
            addCriterion("DRUG_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDrugTypeNameEqualTo(String value) {
            addCriterion("DRUG_TYPE_NAME =", value, "drugTypeName");
            return (Criteria) this;
        }

        public Criteria andDrugTypeNameNotEqualTo(String value) {
            addCriterion("DRUG_TYPE_NAME <>", value, "drugTypeName");
            return (Criteria) this;
        }

        public Criteria andDrugTypeNameGreaterThan(String value) {
            addCriterion("DRUG_TYPE_NAME >", value, "drugTypeName");
            return (Criteria) this;
        }

        public Criteria andDrugTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("DRUG_TYPE_NAME >=", value, "drugTypeName");
            return (Criteria) this;
        }

        public Criteria andDrugTypeNameLessThan(String value) {
            addCriterion("DRUG_TYPE_NAME <", value, "drugTypeName");
            return (Criteria) this;
        }

        public Criteria andDrugTypeNameLessThanOrEqualTo(String value) {
            addCriterion("DRUG_TYPE_NAME <=", value, "drugTypeName");
            return (Criteria) this;
        }

        public Criteria andDrugTypeNameLike(String value) {
            addCriterion("DRUG_TYPE_NAME like", value, "drugTypeName");
            return (Criteria) this;
        }

        public Criteria andDrugTypeNameNotLike(String value) {
            addCriterion("DRUG_TYPE_NAME not like", value, "drugTypeName");
            return (Criteria) this;
        }

        public Criteria andDrugTypeNameIn(List<String> values) {
            addCriterion("DRUG_TYPE_NAME in", values, "drugTypeName");
            return (Criteria) this;
        }

        public Criteria andDrugTypeNameNotIn(List<String> values) {
            addCriterion("DRUG_TYPE_NAME not in", values, "drugTypeName");
            return (Criteria) this;
        }

        public Criteria andDrugTypeNameBetween(String value1, String value2) {
            addCriterion("DRUG_TYPE_NAME between", value1, value2, "drugTypeName");
            return (Criteria) this;
        }

        public Criteria andDrugTypeNameNotBetween(String value1, String value2) {
            addCriterion("DRUG_TYPE_NAME not between", value1, value2, "drugTypeName");
            return (Criteria) this;
        }

        public Criteria andDoseIsNull() {
            addCriterion("DOSE is null");
            return (Criteria) this;
        }

        public Criteria andDoseIsNotNull() {
            addCriterion("DOSE is not null");
            return (Criteria) this;
        }

        public Criteria andDoseEqualTo(String value) {
            addCriterion("DOSE =", value, "dose");
            return (Criteria) this;
        }

        public Criteria andDoseNotEqualTo(String value) {
            addCriterion("DOSE <>", value, "dose");
            return (Criteria) this;
        }

        public Criteria andDoseGreaterThan(String value) {
            addCriterion("DOSE >", value, "dose");
            return (Criteria) this;
        }

        public Criteria andDoseGreaterThanOrEqualTo(String value) {
            addCriterion("DOSE >=", value, "dose");
            return (Criteria) this;
        }

        public Criteria andDoseLessThan(String value) {
            addCriterion("DOSE <", value, "dose");
            return (Criteria) this;
        }

        public Criteria andDoseLessThanOrEqualTo(String value) {
            addCriterion("DOSE <=", value, "dose");
            return (Criteria) this;
        }

        public Criteria andDoseLike(String value) {
            addCriterion("DOSE like", value, "dose");
            return (Criteria) this;
        }

        public Criteria andDoseNotLike(String value) {
            addCriterion("DOSE not like", value, "dose");
            return (Criteria) this;
        }

        public Criteria andDoseIn(List<String> values) {
            addCriterion("DOSE in", values, "dose");
            return (Criteria) this;
        }

        public Criteria andDoseNotIn(List<String> values) {
            addCriterion("DOSE not in", values, "dose");
            return (Criteria) this;
        }

        public Criteria andDoseBetween(String value1, String value2) {
            addCriterion("DOSE between", value1, value2, "dose");
            return (Criteria) this;
        }

        public Criteria andDoseNotBetween(String value1, String value2) {
            addCriterion("DOSE not between", value1, value2, "dose");
            return (Criteria) this;
        }

        public Criteria andDoseUnitIdIsNull() {
            addCriterion("DOSE_UNIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andDoseUnitIdIsNotNull() {
            addCriterion("DOSE_UNIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDoseUnitIdEqualTo(String value) {
            addCriterion("DOSE_UNIT_ID =", value, "doseUnitId");
            return (Criteria) this;
        }

        public Criteria andDoseUnitIdNotEqualTo(String value) {
            addCriterion("DOSE_UNIT_ID <>", value, "doseUnitId");
            return (Criteria) this;
        }

        public Criteria andDoseUnitIdGreaterThan(String value) {
            addCriterion("DOSE_UNIT_ID >", value, "doseUnitId");
            return (Criteria) this;
        }

        public Criteria andDoseUnitIdGreaterThanOrEqualTo(String value) {
            addCriterion("DOSE_UNIT_ID >=", value, "doseUnitId");
            return (Criteria) this;
        }

        public Criteria andDoseUnitIdLessThan(String value) {
            addCriterion("DOSE_UNIT_ID <", value, "doseUnitId");
            return (Criteria) this;
        }

        public Criteria andDoseUnitIdLessThanOrEqualTo(String value) {
            addCriterion("DOSE_UNIT_ID <=", value, "doseUnitId");
            return (Criteria) this;
        }

        public Criteria andDoseUnitIdLike(String value) {
            addCriterion("DOSE_UNIT_ID like", value, "doseUnitId");
            return (Criteria) this;
        }

        public Criteria andDoseUnitIdNotLike(String value) {
            addCriterion("DOSE_UNIT_ID not like", value, "doseUnitId");
            return (Criteria) this;
        }

        public Criteria andDoseUnitIdIn(List<String> values) {
            addCriterion("DOSE_UNIT_ID in", values, "doseUnitId");
            return (Criteria) this;
        }

        public Criteria andDoseUnitIdNotIn(List<String> values) {
            addCriterion("DOSE_UNIT_ID not in", values, "doseUnitId");
            return (Criteria) this;
        }

        public Criteria andDoseUnitIdBetween(String value1, String value2) {
            addCriterion("DOSE_UNIT_ID between", value1, value2, "doseUnitId");
            return (Criteria) this;
        }

        public Criteria andDoseUnitIdNotBetween(String value1, String value2) {
            addCriterion("DOSE_UNIT_ID not between", value1, value2, "doseUnitId");
            return (Criteria) this;
        }

        public Criteria andDoseUnitNameIsNull() {
            addCriterion("DOSE_UNIT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoseUnitNameIsNotNull() {
            addCriterion("DOSE_UNIT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoseUnitNameEqualTo(String value) {
            addCriterion("DOSE_UNIT_NAME =", value, "doseUnitName");
            return (Criteria) this;
        }

        public Criteria andDoseUnitNameNotEqualTo(String value) {
            addCriterion("DOSE_UNIT_NAME <>", value, "doseUnitName");
            return (Criteria) this;
        }

        public Criteria andDoseUnitNameGreaterThan(String value) {
            addCriterion("DOSE_UNIT_NAME >", value, "doseUnitName");
            return (Criteria) this;
        }

        public Criteria andDoseUnitNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOSE_UNIT_NAME >=", value, "doseUnitName");
            return (Criteria) this;
        }

        public Criteria andDoseUnitNameLessThan(String value) {
            addCriterion("DOSE_UNIT_NAME <", value, "doseUnitName");
            return (Criteria) this;
        }

        public Criteria andDoseUnitNameLessThanOrEqualTo(String value) {
            addCriterion("DOSE_UNIT_NAME <=", value, "doseUnitName");
            return (Criteria) this;
        }

        public Criteria andDoseUnitNameLike(String value) {
            addCriterion("DOSE_UNIT_NAME like", value, "doseUnitName");
            return (Criteria) this;
        }

        public Criteria andDoseUnitNameNotLike(String value) {
            addCriterion("DOSE_UNIT_NAME not like", value, "doseUnitName");
            return (Criteria) this;
        }

        public Criteria andDoseUnitNameIn(List<String> values) {
            addCriterion("DOSE_UNIT_NAME in", values, "doseUnitName");
            return (Criteria) this;
        }

        public Criteria andDoseUnitNameNotIn(List<String> values) {
            addCriterion("DOSE_UNIT_NAME not in", values, "doseUnitName");
            return (Criteria) this;
        }

        public Criteria andDoseUnitNameBetween(String value1, String value2) {
            addCriterion("DOSE_UNIT_NAME between", value1, value2, "doseUnitName");
            return (Criteria) this;
        }

        public Criteria andDoseUnitNameNotBetween(String value1, String value2) {
            addCriterion("DOSE_UNIT_NAME not between", value1, value2, "doseUnitName");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitIdIsNull() {
            addCriterion("PREPARATION_UNIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitIdIsNotNull() {
            addCriterion("PREPARATION_UNIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitIdEqualTo(String value) {
            addCriterion("PREPARATION_UNIT_ID =", value, "preparationUnitId");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitIdNotEqualTo(String value) {
            addCriterion("PREPARATION_UNIT_ID <>", value, "preparationUnitId");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitIdGreaterThan(String value) {
            addCriterion("PREPARATION_UNIT_ID >", value, "preparationUnitId");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitIdGreaterThanOrEqualTo(String value) {
            addCriterion("PREPARATION_UNIT_ID >=", value, "preparationUnitId");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitIdLessThan(String value) {
            addCriterion("PREPARATION_UNIT_ID <", value, "preparationUnitId");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitIdLessThanOrEqualTo(String value) {
            addCriterion("PREPARATION_UNIT_ID <=", value, "preparationUnitId");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitIdLike(String value) {
            addCriterion("PREPARATION_UNIT_ID like", value, "preparationUnitId");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitIdNotLike(String value) {
            addCriterion("PREPARATION_UNIT_ID not like", value, "preparationUnitId");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitIdIn(List<String> values) {
            addCriterion("PREPARATION_UNIT_ID in", values, "preparationUnitId");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitIdNotIn(List<String> values) {
            addCriterion("PREPARATION_UNIT_ID not in", values, "preparationUnitId");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitIdBetween(String value1, String value2) {
            addCriterion("PREPARATION_UNIT_ID between", value1, value2, "preparationUnitId");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitIdNotBetween(String value1, String value2) {
            addCriterion("PREPARATION_UNIT_ID not between", value1, value2, "preparationUnitId");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitNameIsNull() {
            addCriterion("PREPARATION_UNIT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitNameIsNotNull() {
            addCriterion("PREPARATION_UNIT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitNameEqualTo(String value) {
            addCriterion("PREPARATION_UNIT_NAME =", value, "preparationUnitName");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitNameNotEqualTo(String value) {
            addCriterion("PREPARATION_UNIT_NAME <>", value, "preparationUnitName");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitNameGreaterThan(String value) {
            addCriterion("PREPARATION_UNIT_NAME >", value, "preparationUnitName");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitNameGreaterThanOrEqualTo(String value) {
            addCriterion("PREPARATION_UNIT_NAME >=", value, "preparationUnitName");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitNameLessThan(String value) {
            addCriterion("PREPARATION_UNIT_NAME <", value, "preparationUnitName");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitNameLessThanOrEqualTo(String value) {
            addCriterion("PREPARATION_UNIT_NAME <=", value, "preparationUnitName");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitNameLike(String value) {
            addCriterion("PREPARATION_UNIT_NAME like", value, "preparationUnitName");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitNameNotLike(String value) {
            addCriterion("PREPARATION_UNIT_NAME not like", value, "preparationUnitName");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitNameIn(List<String> values) {
            addCriterion("PREPARATION_UNIT_NAME in", values, "preparationUnitName");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitNameNotIn(List<String> values) {
            addCriterion("PREPARATION_UNIT_NAME not in", values, "preparationUnitName");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitNameBetween(String value1, String value2) {
            addCriterion("PREPARATION_UNIT_NAME between", value1, value2, "preparationUnitName");
            return (Criteria) this;
        }

        public Criteria andPreparationUnitNameNotBetween(String value1, String value2) {
            addCriterion("PREPARATION_UNIT_NAME not between", value1, value2, "preparationUnitName");
            return (Criteria) this;
        }

        public Criteria andSpecIsNull() {
            addCriterion("SPEC is null");
            return (Criteria) this;
        }

        public Criteria andSpecIsNotNull() {
            addCriterion("SPEC is not null");
            return (Criteria) this;
        }

        public Criteria andSpecEqualTo(String value) {
            addCriterion("SPEC =", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotEqualTo(String value) {
            addCriterion("SPEC <>", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecGreaterThan(String value) {
            addCriterion("SPEC >", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecGreaterThanOrEqualTo(String value) {
            addCriterion("SPEC >=", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecLessThan(String value) {
            addCriterion("SPEC <", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecLessThanOrEqualTo(String value) {
            addCriterion("SPEC <=", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecLike(String value) {
            addCriterion("SPEC like", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotLike(String value) {
            addCriterion("SPEC not like", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecIn(List<String> values) {
            addCriterion("SPEC in", values, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotIn(List<String> values) {
            addCriterion("SPEC not in", values, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecBetween(String value1, String value2) {
            addCriterion("SPEC between", value1, value2, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotBetween(String value1, String value2) {
            addCriterion("SPEC not between", value1, value2, "spec");
            return (Criteria) this;
        }

        public Criteria andUsageIdIsNull() {
            addCriterion("USAGE_ID is null");
            return (Criteria) this;
        }

        public Criteria andUsageIdIsNotNull() {
            addCriterion("USAGE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUsageIdEqualTo(String value) {
            addCriterion("USAGE_ID =", value, "usageId");
            return (Criteria) this;
        }

        public Criteria andUsageIdNotEqualTo(String value) {
            addCriterion("USAGE_ID <>", value, "usageId");
            return (Criteria) this;
        }

        public Criteria andUsageIdGreaterThan(String value) {
            addCriterion("USAGE_ID >", value, "usageId");
            return (Criteria) this;
        }

        public Criteria andUsageIdGreaterThanOrEqualTo(String value) {
            addCriterion("USAGE_ID >=", value, "usageId");
            return (Criteria) this;
        }

        public Criteria andUsageIdLessThan(String value) {
            addCriterion("USAGE_ID <", value, "usageId");
            return (Criteria) this;
        }

        public Criteria andUsageIdLessThanOrEqualTo(String value) {
            addCriterion("USAGE_ID <=", value, "usageId");
            return (Criteria) this;
        }

        public Criteria andUsageIdLike(String value) {
            addCriterion("USAGE_ID like", value, "usageId");
            return (Criteria) this;
        }

        public Criteria andUsageIdNotLike(String value) {
            addCriterion("USAGE_ID not like", value, "usageId");
            return (Criteria) this;
        }

        public Criteria andUsageIdIn(List<String> values) {
            addCriterion("USAGE_ID in", values, "usageId");
            return (Criteria) this;
        }

        public Criteria andUsageIdNotIn(List<String> values) {
            addCriterion("USAGE_ID not in", values, "usageId");
            return (Criteria) this;
        }

        public Criteria andUsageIdBetween(String value1, String value2) {
            addCriterion("USAGE_ID between", value1, value2, "usageId");
            return (Criteria) this;
        }

        public Criteria andUsageIdNotBetween(String value1, String value2) {
            addCriterion("USAGE_ID not between", value1, value2, "usageId");
            return (Criteria) this;
        }

        public Criteria andUsageNameIsNull() {
            addCriterion("USAGE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUsageNameIsNotNull() {
            addCriterion("USAGE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUsageNameEqualTo(String value) {
            addCriterion("USAGE_NAME =", value, "usageName");
            return (Criteria) this;
        }

        public Criteria andUsageNameNotEqualTo(String value) {
            addCriterion("USAGE_NAME <>", value, "usageName");
            return (Criteria) this;
        }

        public Criteria andUsageNameGreaterThan(String value) {
            addCriterion("USAGE_NAME >", value, "usageName");
            return (Criteria) this;
        }

        public Criteria andUsageNameGreaterThanOrEqualTo(String value) {
            addCriterion("USAGE_NAME >=", value, "usageName");
            return (Criteria) this;
        }

        public Criteria andUsageNameLessThan(String value) {
            addCriterion("USAGE_NAME <", value, "usageName");
            return (Criteria) this;
        }

        public Criteria andUsageNameLessThanOrEqualTo(String value) {
            addCriterion("USAGE_NAME <=", value, "usageName");
            return (Criteria) this;
        }

        public Criteria andUsageNameLike(String value) {
            addCriterion("USAGE_NAME like", value, "usageName");
            return (Criteria) this;
        }

        public Criteria andUsageNameNotLike(String value) {
            addCriterion("USAGE_NAME not like", value, "usageName");
            return (Criteria) this;
        }

        public Criteria andUsageNameIn(List<String> values) {
            addCriterion("USAGE_NAME in", values, "usageName");
            return (Criteria) this;
        }

        public Criteria andUsageNameNotIn(List<String> values) {
            addCriterion("USAGE_NAME not in", values, "usageName");
            return (Criteria) this;
        }

        public Criteria andUsageNameBetween(String value1, String value2) {
            addCriterion("USAGE_NAME between", value1, value2, "usageName");
            return (Criteria) this;
        }

        public Criteria andUsageNameNotBetween(String value1, String value2) {
            addCriterion("USAGE_NAME not between", value1, value2, "usageName");
            return (Criteria) this;
        }

        public Criteria andSolutionIdIsNull() {
            addCriterion("SOLUTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andSolutionIdIsNotNull() {
            addCriterion("SOLUTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSolutionIdEqualTo(String value) {
            addCriterion("SOLUTION_ID =", value, "solutionId");
            return (Criteria) this;
        }

        public Criteria andSolutionIdNotEqualTo(String value) {
            addCriterion("SOLUTION_ID <>", value, "solutionId");
            return (Criteria) this;
        }

        public Criteria andSolutionIdGreaterThan(String value) {
            addCriterion("SOLUTION_ID >", value, "solutionId");
            return (Criteria) this;
        }

        public Criteria andSolutionIdGreaterThanOrEqualTo(String value) {
            addCriterion("SOLUTION_ID >=", value, "solutionId");
            return (Criteria) this;
        }

        public Criteria andSolutionIdLessThan(String value) {
            addCriterion("SOLUTION_ID <", value, "solutionId");
            return (Criteria) this;
        }

        public Criteria andSolutionIdLessThanOrEqualTo(String value) {
            addCriterion("SOLUTION_ID <=", value, "solutionId");
            return (Criteria) this;
        }

        public Criteria andSolutionIdLike(String value) {
            addCriterion("SOLUTION_ID like", value, "solutionId");
            return (Criteria) this;
        }

        public Criteria andSolutionIdNotLike(String value) {
            addCriterion("SOLUTION_ID not like", value, "solutionId");
            return (Criteria) this;
        }

        public Criteria andSolutionIdIn(List<String> values) {
            addCriterion("SOLUTION_ID in", values, "solutionId");
            return (Criteria) this;
        }

        public Criteria andSolutionIdNotIn(List<String> values) {
            addCriterion("SOLUTION_ID not in", values, "solutionId");
            return (Criteria) this;
        }

        public Criteria andSolutionIdBetween(String value1, String value2) {
            addCriterion("SOLUTION_ID between", value1, value2, "solutionId");
            return (Criteria) this;
        }

        public Criteria andSolutionIdNotBetween(String value1, String value2) {
            addCriterion("SOLUTION_ID not between", value1, value2, "solutionId");
            return (Criteria) this;
        }

        public Criteria andSolutionNameIsNull() {
            addCriterion("SOLUTION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSolutionNameIsNotNull() {
            addCriterion("SOLUTION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSolutionNameEqualTo(String value) {
            addCriterion("SOLUTION_NAME =", value, "solutionName");
            return (Criteria) this;
        }

        public Criteria andSolutionNameNotEqualTo(String value) {
            addCriterion("SOLUTION_NAME <>", value, "solutionName");
            return (Criteria) this;
        }

        public Criteria andSolutionNameGreaterThan(String value) {
            addCriterion("SOLUTION_NAME >", value, "solutionName");
            return (Criteria) this;
        }

        public Criteria andSolutionNameGreaterThanOrEqualTo(String value) {
            addCriterion("SOLUTION_NAME >=", value, "solutionName");
            return (Criteria) this;
        }

        public Criteria andSolutionNameLessThan(String value) {
            addCriterion("SOLUTION_NAME <", value, "solutionName");
            return (Criteria) this;
        }

        public Criteria andSolutionNameLessThanOrEqualTo(String value) {
            addCriterion("SOLUTION_NAME <=", value, "solutionName");
            return (Criteria) this;
        }

        public Criteria andSolutionNameLike(String value) {
            addCriterion("SOLUTION_NAME like", value, "solutionName");
            return (Criteria) this;
        }

        public Criteria andSolutionNameNotLike(String value) {
            addCriterion("SOLUTION_NAME not like", value, "solutionName");
            return (Criteria) this;
        }

        public Criteria andSolutionNameIn(List<String> values) {
            addCriterion("SOLUTION_NAME in", values, "solutionName");
            return (Criteria) this;
        }

        public Criteria andSolutionNameNotIn(List<String> values) {
            addCriterion("SOLUTION_NAME not in", values, "solutionName");
            return (Criteria) this;
        }

        public Criteria andSolutionNameBetween(String value1, String value2) {
            addCriterion("SOLUTION_NAME between", value1, value2, "solutionName");
            return (Criteria) this;
        }

        public Criteria andSolutionNameNotBetween(String value1, String value2) {
            addCriterion("SOLUTION_NAME not between", value1, value2, "solutionName");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitIdIsNull() {
            addCriterion("MIN_PACK_UNIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitIdIsNotNull() {
            addCriterion("MIN_PACK_UNIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitIdEqualTo(String value) {
            addCriterion("MIN_PACK_UNIT_ID =", value, "minPackUnitId");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitIdNotEqualTo(String value) {
            addCriterion("MIN_PACK_UNIT_ID <>", value, "minPackUnitId");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitIdGreaterThan(String value) {
            addCriterion("MIN_PACK_UNIT_ID >", value, "minPackUnitId");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitIdGreaterThanOrEqualTo(String value) {
            addCriterion("MIN_PACK_UNIT_ID >=", value, "minPackUnitId");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitIdLessThan(String value) {
            addCriterion("MIN_PACK_UNIT_ID <", value, "minPackUnitId");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitIdLessThanOrEqualTo(String value) {
            addCriterion("MIN_PACK_UNIT_ID <=", value, "minPackUnitId");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitIdLike(String value) {
            addCriterion("MIN_PACK_UNIT_ID like", value, "minPackUnitId");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitIdNotLike(String value) {
            addCriterion("MIN_PACK_UNIT_ID not like", value, "minPackUnitId");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitIdIn(List<String> values) {
            addCriterion("MIN_PACK_UNIT_ID in", values, "minPackUnitId");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitIdNotIn(List<String> values) {
            addCriterion("MIN_PACK_UNIT_ID not in", values, "minPackUnitId");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitIdBetween(String value1, String value2) {
            addCriterion("MIN_PACK_UNIT_ID between", value1, value2, "minPackUnitId");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitIdNotBetween(String value1, String value2) {
            addCriterion("MIN_PACK_UNIT_ID not between", value1, value2, "minPackUnitId");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitNameIsNull() {
            addCriterion("MIN_PACK_UNIT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitNameIsNotNull() {
            addCriterion("MIN_PACK_UNIT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitNameEqualTo(String value) {
            addCriterion("MIN_PACK_UNIT_NAME =", value, "minPackUnitName");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitNameNotEqualTo(String value) {
            addCriterion("MIN_PACK_UNIT_NAME <>", value, "minPackUnitName");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitNameGreaterThan(String value) {
            addCriterion("MIN_PACK_UNIT_NAME >", value, "minPackUnitName");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitNameGreaterThanOrEqualTo(String value) {
            addCriterion("MIN_PACK_UNIT_NAME >=", value, "minPackUnitName");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitNameLessThan(String value) {
            addCriterion("MIN_PACK_UNIT_NAME <", value, "minPackUnitName");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitNameLessThanOrEqualTo(String value) {
            addCriterion("MIN_PACK_UNIT_NAME <=", value, "minPackUnitName");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitNameLike(String value) {
            addCriterion("MIN_PACK_UNIT_NAME like", value, "minPackUnitName");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitNameNotLike(String value) {
            addCriterion("MIN_PACK_UNIT_NAME not like", value, "minPackUnitName");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitNameIn(List<String> values) {
            addCriterion("MIN_PACK_UNIT_NAME in", values, "minPackUnitName");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitNameNotIn(List<String> values) {
            addCriterion("MIN_PACK_UNIT_NAME not in", values, "minPackUnitName");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitNameBetween(String value1, String value2) {
            addCriterion("MIN_PACK_UNIT_NAME between", value1, value2, "minPackUnitName");
            return (Criteria) this;
        }

        public Criteria andMinPackUnitNameNotBetween(String value1, String value2) {
            addCriterion("MIN_PACK_UNIT_NAME not between", value1, value2, "minPackUnitName");
            return (Criteria) this;
        }

        public Criteria andMinPackAmountIsNull() {
            addCriterion("MIN_PACK_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andMinPackAmountIsNotNull() {
            addCriterion("MIN_PACK_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andMinPackAmountEqualTo(String value) {
            addCriterion("MIN_PACK_AMOUNT =", value, "minPackAmount");
            return (Criteria) this;
        }

        public Criteria andMinPackAmountNotEqualTo(String value) {
            addCriterion("MIN_PACK_AMOUNT <>", value, "minPackAmount");
            return (Criteria) this;
        }

        public Criteria andMinPackAmountGreaterThan(String value) {
            addCriterion("MIN_PACK_AMOUNT >", value, "minPackAmount");
            return (Criteria) this;
        }

        public Criteria andMinPackAmountGreaterThanOrEqualTo(String value) {
            addCriterion("MIN_PACK_AMOUNT >=", value, "minPackAmount");
            return (Criteria) this;
        }

        public Criteria andMinPackAmountLessThan(String value) {
            addCriterion("MIN_PACK_AMOUNT <", value, "minPackAmount");
            return (Criteria) this;
        }

        public Criteria andMinPackAmountLessThanOrEqualTo(String value) {
            addCriterion("MIN_PACK_AMOUNT <=", value, "minPackAmount");
            return (Criteria) this;
        }

        public Criteria andMinPackAmountLike(String value) {
            addCriterion("MIN_PACK_AMOUNT like", value, "minPackAmount");
            return (Criteria) this;
        }

        public Criteria andMinPackAmountNotLike(String value) {
            addCriterion("MIN_PACK_AMOUNT not like", value, "minPackAmount");
            return (Criteria) this;
        }

        public Criteria andMinPackAmountIn(List<String> values) {
            addCriterion("MIN_PACK_AMOUNT in", values, "minPackAmount");
            return (Criteria) this;
        }

        public Criteria andMinPackAmountNotIn(List<String> values) {
            addCriterion("MIN_PACK_AMOUNT not in", values, "minPackAmount");
            return (Criteria) this;
        }

        public Criteria andMinPackAmountBetween(String value1, String value2) {
            addCriterion("MIN_PACK_AMOUNT between", value1, value2, "minPackAmount");
            return (Criteria) this;
        }

        public Criteria andMinPackAmountNotBetween(String value1, String value2) {
            addCriterion("MIN_PACK_AMOUNT not between", value1, value2, "minPackAmount");
            return (Criteria) this;
        }

        public Criteria andSinglePackAmountIsNull() {
            addCriterion("SINGLE_PACK_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andSinglePackAmountIsNotNull() {
            addCriterion("SINGLE_PACK_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andSinglePackAmountEqualTo(String value) {
            addCriterion("SINGLE_PACK_AMOUNT =", value, "singlePackAmount");
            return (Criteria) this;
        }

        public Criteria andSinglePackAmountNotEqualTo(String value) {
            addCriterion("SINGLE_PACK_AMOUNT <>", value, "singlePackAmount");
            return (Criteria) this;
        }

        public Criteria andSinglePackAmountGreaterThan(String value) {
            addCriterion("SINGLE_PACK_AMOUNT >", value, "singlePackAmount");
            return (Criteria) this;
        }

        public Criteria andSinglePackAmountGreaterThanOrEqualTo(String value) {
            addCriterion("SINGLE_PACK_AMOUNT >=", value, "singlePackAmount");
            return (Criteria) this;
        }

        public Criteria andSinglePackAmountLessThan(String value) {
            addCriterion("SINGLE_PACK_AMOUNT <", value, "singlePackAmount");
            return (Criteria) this;
        }

        public Criteria andSinglePackAmountLessThanOrEqualTo(String value) {
            addCriterion("SINGLE_PACK_AMOUNT <=", value, "singlePackAmount");
            return (Criteria) this;
        }

        public Criteria andSinglePackAmountLike(String value) {
            addCriterion("SINGLE_PACK_AMOUNT like", value, "singlePackAmount");
            return (Criteria) this;
        }

        public Criteria andSinglePackAmountNotLike(String value) {
            addCriterion("SINGLE_PACK_AMOUNT not like", value, "singlePackAmount");
            return (Criteria) this;
        }

        public Criteria andSinglePackAmountIn(List<String> values) {
            addCriterion("SINGLE_PACK_AMOUNT in", values, "singlePackAmount");
            return (Criteria) this;
        }

        public Criteria andSinglePackAmountNotIn(List<String> values) {
            addCriterion("SINGLE_PACK_AMOUNT not in", values, "singlePackAmount");
            return (Criteria) this;
        }

        public Criteria andSinglePackAmountBetween(String value1, String value2) {
            addCriterion("SINGLE_PACK_AMOUNT between", value1, value2, "singlePackAmount");
            return (Criteria) this;
        }

        public Criteria andSinglePackAmountNotBetween(String value1, String value2) {
            addCriterion("SINGLE_PACK_AMOUNT not between", value1, value2, "singlePackAmount");
            return (Criteria) this;
        }

        public Criteria andProvisionAmountIsNull() {
            addCriterion("PROVISION_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andProvisionAmountIsNotNull() {
            addCriterion("PROVISION_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andProvisionAmountEqualTo(String value) {
            addCriterion("PROVISION_AMOUNT =", value, "provisionAmount");
            return (Criteria) this;
        }

        public Criteria andProvisionAmountNotEqualTo(String value) {
            addCriterion("PROVISION_AMOUNT <>", value, "provisionAmount");
            return (Criteria) this;
        }

        public Criteria andProvisionAmountGreaterThan(String value) {
            addCriterion("PROVISION_AMOUNT >", value, "provisionAmount");
            return (Criteria) this;
        }

        public Criteria andProvisionAmountGreaterThanOrEqualTo(String value) {
            addCriterion("PROVISION_AMOUNT >=", value, "provisionAmount");
            return (Criteria) this;
        }

        public Criteria andProvisionAmountLessThan(String value) {
            addCriterion("PROVISION_AMOUNT <", value, "provisionAmount");
            return (Criteria) this;
        }

        public Criteria andProvisionAmountLessThanOrEqualTo(String value) {
            addCriterion("PROVISION_AMOUNT <=", value, "provisionAmount");
            return (Criteria) this;
        }

        public Criteria andProvisionAmountLike(String value) {
            addCriterion("PROVISION_AMOUNT like", value, "provisionAmount");
            return (Criteria) this;
        }

        public Criteria andProvisionAmountNotLike(String value) {
            addCriterion("PROVISION_AMOUNT not like", value, "provisionAmount");
            return (Criteria) this;
        }

        public Criteria andProvisionAmountIn(List<String> values) {
            addCriterion("PROVISION_AMOUNT in", values, "provisionAmount");
            return (Criteria) this;
        }

        public Criteria andProvisionAmountNotIn(List<String> values) {
            addCriterion("PROVISION_AMOUNT not in", values, "provisionAmount");
            return (Criteria) this;
        }

        public Criteria andProvisionAmountBetween(String value1, String value2) {
            addCriterion("PROVISION_AMOUNT between", value1, value2, "provisionAmount");
            return (Criteria) this;
        }

        public Criteria andProvisionAmountNotBetween(String value1, String value2) {
            addCriterion("PROVISION_AMOUNT not between", value1, value2, "provisionAmount");
            return (Criteria) this;
        }

        public Criteria andStorageConditionIsNull() {
            addCriterion("STORAGE_CONDITION is null");
            return (Criteria) this;
        }

        public Criteria andStorageConditionIsNotNull() {
            addCriterion("STORAGE_CONDITION is not null");
            return (Criteria) this;
        }

        public Criteria andStorageConditionEqualTo(String value) {
            addCriterion("STORAGE_CONDITION =", value, "storageCondition");
            return (Criteria) this;
        }

        public Criteria andStorageConditionNotEqualTo(String value) {
            addCriterion("STORAGE_CONDITION <>", value, "storageCondition");
            return (Criteria) this;
        }

        public Criteria andStorageConditionGreaterThan(String value) {
            addCriterion("STORAGE_CONDITION >", value, "storageCondition");
            return (Criteria) this;
        }

        public Criteria andStorageConditionGreaterThanOrEqualTo(String value) {
            addCriterion("STORAGE_CONDITION >=", value, "storageCondition");
            return (Criteria) this;
        }

        public Criteria andStorageConditionLessThan(String value) {
            addCriterion("STORAGE_CONDITION <", value, "storageCondition");
            return (Criteria) this;
        }

        public Criteria andStorageConditionLessThanOrEqualTo(String value) {
            addCriterion("STORAGE_CONDITION <=", value, "storageCondition");
            return (Criteria) this;
        }

        public Criteria andStorageConditionLike(String value) {
            addCriterion("STORAGE_CONDITION like", value, "storageCondition");
            return (Criteria) this;
        }

        public Criteria andStorageConditionNotLike(String value) {
            addCriterion("STORAGE_CONDITION not like", value, "storageCondition");
            return (Criteria) this;
        }

        public Criteria andStorageConditionIn(List<String> values) {
            addCriterion("STORAGE_CONDITION in", values, "storageCondition");
            return (Criteria) this;
        }

        public Criteria andStorageConditionNotIn(List<String> values) {
            addCriterion("STORAGE_CONDITION not in", values, "storageCondition");
            return (Criteria) this;
        }

        public Criteria andStorageConditionBetween(String value1, String value2) {
            addCriterion("STORAGE_CONDITION between", value1, value2, "storageCondition");
            return (Criteria) this;
        }

        public Criteria andStorageConditionNotBetween(String value1, String value2) {
            addCriterion("STORAGE_CONDITION not between", value1, value2, "storageCondition");
            return (Criteria) this;
        }

        public Criteria andManufacturerIsNull() {
            addCriterion("MANUFACTURER is null");
            return (Criteria) this;
        }

        public Criteria andManufacturerIsNotNull() {
            addCriterion("MANUFACTURER is not null");
            return (Criteria) this;
        }

        public Criteria andManufacturerEqualTo(String value) {
            addCriterion("MANUFACTURER =", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotEqualTo(String value) {
            addCriterion("MANUFACTURER <>", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerGreaterThan(String value) {
            addCriterion("MANUFACTURER >", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerGreaterThanOrEqualTo(String value) {
            addCriterion("MANUFACTURER >=", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLessThan(String value) {
            addCriterion("MANUFACTURER <", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLessThanOrEqualTo(String value) {
            addCriterion("MANUFACTURER <=", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLike(String value) {
            addCriterion("MANUFACTURER like", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotLike(String value) {
            addCriterion("MANUFACTURER not like", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerIn(List<String> values) {
            addCriterion("MANUFACTURER in", values, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotIn(List<String> values) {
            addCriterion("MANUFACTURER not in", values, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerBetween(String value1, String value2) {
            addCriterion("MANUFACTURER between", value1, value2, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotBetween(String value1, String value2) {
            addCriterion("MANUFACTURER not between", value1, value2, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andRecipeUsageIsNull() {
            addCriterion("RECIPE_USAGE is null");
            return (Criteria) this;
        }

        public Criteria andRecipeUsageIsNotNull() {
            addCriterion("RECIPE_USAGE is not null");
            return (Criteria) this;
        }

        public Criteria andRecipeUsageEqualTo(String value) {
            addCriterion("RECIPE_USAGE =", value, "recipeUsage");
            return (Criteria) this;
        }

        public Criteria andRecipeUsageNotEqualTo(String value) {
            addCriterion("RECIPE_USAGE <>", value, "recipeUsage");
            return (Criteria) this;
        }

        public Criteria andRecipeUsageGreaterThan(String value) {
            addCriterion("RECIPE_USAGE >", value, "recipeUsage");
            return (Criteria) this;
        }

        public Criteria andRecipeUsageGreaterThanOrEqualTo(String value) {
            addCriterion("RECIPE_USAGE >=", value, "recipeUsage");
            return (Criteria) this;
        }

        public Criteria andRecipeUsageLessThan(String value) {
            addCriterion("RECIPE_USAGE <", value, "recipeUsage");
            return (Criteria) this;
        }

        public Criteria andRecipeUsageLessThanOrEqualTo(String value) {
            addCriterion("RECIPE_USAGE <=", value, "recipeUsage");
            return (Criteria) this;
        }

        public Criteria andRecipeUsageLike(String value) {
            addCriterion("RECIPE_USAGE like", value, "recipeUsage");
            return (Criteria) this;
        }

        public Criteria andRecipeUsageNotLike(String value) {
            addCriterion("RECIPE_USAGE not like", value, "recipeUsage");
            return (Criteria) this;
        }

        public Criteria andRecipeUsageIn(List<String> values) {
            addCriterion("RECIPE_USAGE in", values, "recipeUsage");
            return (Criteria) this;
        }

        public Criteria andRecipeUsageNotIn(List<String> values) {
            addCriterion("RECIPE_USAGE not in", values, "recipeUsage");
            return (Criteria) this;
        }

        public Criteria andRecipeUsageBetween(String value1, String value2) {
            addCriterion("RECIPE_USAGE between", value1, value2, "recipeUsage");
            return (Criteria) this;
        }

        public Criteria andRecipeUsageNotBetween(String value1, String value2) {
            addCriterion("RECIPE_USAGE not between", value1, value2, "recipeUsage");
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