package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EdcIeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdcIeExample() {
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

        public Criteria andIeFlowIsNull() {
            addCriterion("IE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andIeFlowIsNotNull() {
            addCriterion("IE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andIeFlowEqualTo(String value) {
            addCriterion("IE_FLOW =", value, "ieFlow");
            return (Criteria) this;
        }

        public Criteria andIeFlowNotEqualTo(String value) {
            addCriterion("IE_FLOW <>", value, "ieFlow");
            return (Criteria) this;
        }

        public Criteria andIeFlowGreaterThan(String value) {
            addCriterion("IE_FLOW >", value, "ieFlow");
            return (Criteria) this;
        }

        public Criteria andIeFlowGreaterThanOrEqualTo(String value) {
            addCriterion("IE_FLOW >=", value, "ieFlow");
            return (Criteria) this;
        }

        public Criteria andIeFlowLessThan(String value) {
            addCriterion("IE_FLOW <", value, "ieFlow");
            return (Criteria) this;
        }

        public Criteria andIeFlowLessThanOrEqualTo(String value) {
            addCriterion("IE_FLOW <=", value, "ieFlow");
            return (Criteria) this;
        }

        public Criteria andIeFlowLike(String value) {
            addCriterion("IE_FLOW like", value, "ieFlow");
            return (Criteria) this;
        }

        public Criteria andIeFlowNotLike(String value) {
            addCriterion("IE_FLOW not like", value, "ieFlow");
            return (Criteria) this;
        }

        public Criteria andIeFlowIn(List<String> values) {
            addCriterion("IE_FLOW in", values, "ieFlow");
            return (Criteria) this;
        }

        public Criteria andIeFlowNotIn(List<String> values) {
            addCriterion("IE_FLOW not in", values, "ieFlow");
            return (Criteria) this;
        }

        public Criteria andIeFlowBetween(String value1, String value2) {
            addCriterion("IE_FLOW between", value1, value2, "ieFlow");
            return (Criteria) this;
        }

        public Criteria andIeFlowNotBetween(String value1, String value2) {
            addCriterion("IE_FLOW not between", value1, value2, "ieFlow");
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

        public Criteria andIeNameIsNull() {
            addCriterion("IE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andIeNameIsNotNull() {
            addCriterion("IE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andIeNameEqualTo(String value) {
            addCriterion("IE_NAME =", value, "ieName");
            return (Criteria) this;
        }

        public Criteria andIeNameNotEqualTo(String value) {
            addCriterion("IE_NAME <>", value, "ieName");
            return (Criteria) this;
        }

        public Criteria andIeNameGreaterThan(String value) {
            addCriterion("IE_NAME >", value, "ieName");
            return (Criteria) this;
        }

        public Criteria andIeNameGreaterThanOrEqualTo(String value) {
            addCriterion("IE_NAME >=", value, "ieName");
            return (Criteria) this;
        }

        public Criteria andIeNameLessThan(String value) {
            addCriterion("IE_NAME <", value, "ieName");
            return (Criteria) this;
        }

        public Criteria andIeNameLessThanOrEqualTo(String value) {
            addCriterion("IE_NAME <=", value, "ieName");
            return (Criteria) this;
        }

        public Criteria andIeNameLike(String value) {
            addCriterion("IE_NAME like", value, "ieName");
            return (Criteria) this;
        }

        public Criteria andIeNameNotLike(String value) {
            addCriterion("IE_NAME not like", value, "ieName");
            return (Criteria) this;
        }

        public Criteria andIeNameIn(List<String> values) {
            addCriterion("IE_NAME in", values, "ieName");
            return (Criteria) this;
        }

        public Criteria andIeNameNotIn(List<String> values) {
            addCriterion("IE_NAME not in", values, "ieName");
            return (Criteria) this;
        }

        public Criteria andIeNameBetween(String value1, String value2) {
            addCriterion("IE_NAME between", value1, value2, "ieName");
            return (Criteria) this;
        }

        public Criteria andIeNameNotBetween(String value1, String value2) {
            addCriterion("IE_NAME not between", value1, value2, "ieName");
            return (Criteria) this;
        }

        public Criteria andIeVarNameIsNull() {
            addCriterion("IE_VAR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andIeVarNameIsNotNull() {
            addCriterion("IE_VAR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andIeVarNameEqualTo(String value) {
            addCriterion("IE_VAR_NAME =", value, "ieVarName");
            return (Criteria) this;
        }

        public Criteria andIeVarNameNotEqualTo(String value) {
            addCriterion("IE_VAR_NAME <>", value, "ieVarName");
            return (Criteria) this;
        }

        public Criteria andIeVarNameGreaterThan(String value) {
            addCriterion("IE_VAR_NAME >", value, "ieVarName");
            return (Criteria) this;
        }

        public Criteria andIeVarNameGreaterThanOrEqualTo(String value) {
            addCriterion("IE_VAR_NAME >=", value, "ieVarName");
            return (Criteria) this;
        }

        public Criteria andIeVarNameLessThan(String value) {
            addCriterion("IE_VAR_NAME <", value, "ieVarName");
            return (Criteria) this;
        }

        public Criteria andIeVarNameLessThanOrEqualTo(String value) {
            addCriterion("IE_VAR_NAME <=", value, "ieVarName");
            return (Criteria) this;
        }

        public Criteria andIeVarNameLike(String value) {
            addCriterion("IE_VAR_NAME like", value, "ieVarName");
            return (Criteria) this;
        }

        public Criteria andIeVarNameNotLike(String value) {
            addCriterion("IE_VAR_NAME not like", value, "ieVarName");
            return (Criteria) this;
        }

        public Criteria andIeVarNameIn(List<String> values) {
            addCriterion("IE_VAR_NAME in", values, "ieVarName");
            return (Criteria) this;
        }

        public Criteria andIeVarNameNotIn(List<String> values) {
            addCriterion("IE_VAR_NAME not in", values, "ieVarName");
            return (Criteria) this;
        }

        public Criteria andIeVarNameBetween(String value1, String value2) {
            addCriterion("IE_VAR_NAME between", value1, value2, "ieVarName");
            return (Criteria) this;
        }

        public Criteria andIeVarNameNotBetween(String value1, String value2) {
            addCriterion("IE_VAR_NAME not between", value1, value2, "ieVarName");
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

        public Criteria andTypeNameIsNull() {
            addCriterion("TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNotNull() {
            addCriterion("TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNameEqualTo(String value) {
            addCriterion("TYPE_NAME =", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotEqualTo(String value) {
            addCriterion("TYPE_NAME <>", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThan(String value) {
            addCriterion("TYPE_NAME >", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_NAME >=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThan(String value) {
            addCriterion("TYPE_NAME <", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThanOrEqualTo(String value) {
            addCriterion("TYPE_NAME <=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLike(String value) {
            addCriterion("TYPE_NAME like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotLike(String value) {
            addCriterion("TYPE_NAME not like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameIn(List<String> values) {
            addCriterion("TYPE_NAME in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotIn(List<String> values) {
            addCriterion("TYPE_NAME not in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameBetween(String value1, String value2) {
            addCriterion("TYPE_NAME between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotBetween(String value1, String value2) {
            addCriterion("TYPE_NAME not between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andInputTypeIdIsNull() {
            addCriterion("INPUT_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andInputTypeIdIsNotNull() {
            addCriterion("INPUT_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInputTypeIdEqualTo(String value) {
            addCriterion("INPUT_TYPE_ID =", value, "inputTypeId");
            return (Criteria) this;
        }

        public Criteria andInputTypeIdNotEqualTo(String value) {
            addCriterion("INPUT_TYPE_ID <>", value, "inputTypeId");
            return (Criteria) this;
        }

        public Criteria andInputTypeIdGreaterThan(String value) {
            addCriterion("INPUT_TYPE_ID >", value, "inputTypeId");
            return (Criteria) this;
        }

        public Criteria andInputTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_TYPE_ID >=", value, "inputTypeId");
            return (Criteria) this;
        }

        public Criteria andInputTypeIdLessThan(String value) {
            addCriterion("INPUT_TYPE_ID <", value, "inputTypeId");
            return (Criteria) this;
        }

        public Criteria andInputTypeIdLessThanOrEqualTo(String value) {
            addCriterion("INPUT_TYPE_ID <=", value, "inputTypeId");
            return (Criteria) this;
        }

        public Criteria andInputTypeIdLike(String value) {
            addCriterion("INPUT_TYPE_ID like", value, "inputTypeId");
            return (Criteria) this;
        }

        public Criteria andInputTypeIdNotLike(String value) {
            addCriterion("INPUT_TYPE_ID not like", value, "inputTypeId");
            return (Criteria) this;
        }

        public Criteria andInputTypeIdIn(List<String> values) {
            addCriterion("INPUT_TYPE_ID in", values, "inputTypeId");
            return (Criteria) this;
        }

        public Criteria andInputTypeIdNotIn(List<String> values) {
            addCriterion("INPUT_TYPE_ID not in", values, "inputTypeId");
            return (Criteria) this;
        }

        public Criteria andInputTypeIdBetween(String value1, String value2) {
            addCriterion("INPUT_TYPE_ID between", value1, value2, "inputTypeId");
            return (Criteria) this;
        }

        public Criteria andInputTypeIdNotBetween(String value1, String value2) {
            addCriterion("INPUT_TYPE_ID not between", value1, value2, "inputTypeId");
            return (Criteria) this;
        }

        public Criteria andInputTypeNameIsNull() {
            addCriterion("INPUT_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andInputTypeNameIsNotNull() {
            addCriterion("INPUT_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andInputTypeNameEqualTo(String value) {
            addCriterion("INPUT_TYPE_NAME =", value, "inputTypeName");
            return (Criteria) this;
        }

        public Criteria andInputTypeNameNotEqualTo(String value) {
            addCriterion("INPUT_TYPE_NAME <>", value, "inputTypeName");
            return (Criteria) this;
        }

        public Criteria andInputTypeNameGreaterThan(String value) {
            addCriterion("INPUT_TYPE_NAME >", value, "inputTypeName");
            return (Criteria) this;
        }

        public Criteria andInputTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_TYPE_NAME >=", value, "inputTypeName");
            return (Criteria) this;
        }

        public Criteria andInputTypeNameLessThan(String value) {
            addCriterion("INPUT_TYPE_NAME <", value, "inputTypeName");
            return (Criteria) this;
        }

        public Criteria andInputTypeNameLessThanOrEqualTo(String value) {
            addCriterion("INPUT_TYPE_NAME <=", value, "inputTypeName");
            return (Criteria) this;
        }

        public Criteria andInputTypeNameLike(String value) {
            addCriterion("INPUT_TYPE_NAME like", value, "inputTypeName");
            return (Criteria) this;
        }

        public Criteria andInputTypeNameNotLike(String value) {
            addCriterion("INPUT_TYPE_NAME not like", value, "inputTypeName");
            return (Criteria) this;
        }

        public Criteria andInputTypeNameIn(List<String> values) {
            addCriterion("INPUT_TYPE_NAME in", values, "inputTypeName");
            return (Criteria) this;
        }

        public Criteria andInputTypeNameNotIn(List<String> values) {
            addCriterion("INPUT_TYPE_NAME not in", values, "inputTypeName");
            return (Criteria) this;
        }

        public Criteria andInputTypeNameBetween(String value1, String value2) {
            addCriterion("INPUT_TYPE_NAME between", value1, value2, "inputTypeName");
            return (Criteria) this;
        }

        public Criteria andInputTypeNameNotBetween(String value1, String value2) {
            addCriterion("INPUT_TYPE_NAME not between", value1, value2, "inputTypeName");
            return (Criteria) this;
        }

        public Criteria andPassedValueIsNull() {
            addCriterion("PASSED_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andPassedValueIsNotNull() {
            addCriterion("PASSED_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andPassedValueEqualTo(String value) {
            addCriterion("PASSED_VALUE =", value, "passedValue");
            return (Criteria) this;
        }

        public Criteria andPassedValueNotEqualTo(String value) {
            addCriterion("PASSED_VALUE <>", value, "passedValue");
            return (Criteria) this;
        }

        public Criteria andPassedValueGreaterThan(String value) {
            addCriterion("PASSED_VALUE >", value, "passedValue");
            return (Criteria) this;
        }

        public Criteria andPassedValueGreaterThanOrEqualTo(String value) {
            addCriterion("PASSED_VALUE >=", value, "passedValue");
            return (Criteria) this;
        }

        public Criteria andPassedValueLessThan(String value) {
            addCriterion("PASSED_VALUE <", value, "passedValue");
            return (Criteria) this;
        }

        public Criteria andPassedValueLessThanOrEqualTo(String value) {
            addCriterion("PASSED_VALUE <=", value, "passedValue");
            return (Criteria) this;
        }

        public Criteria andPassedValueLike(String value) {
            addCriterion("PASSED_VALUE like", value, "passedValue");
            return (Criteria) this;
        }

        public Criteria andPassedValueNotLike(String value) {
            addCriterion("PASSED_VALUE not like", value, "passedValue");
            return (Criteria) this;
        }

        public Criteria andPassedValueIn(List<String> values) {
            addCriterion("PASSED_VALUE in", values, "passedValue");
            return (Criteria) this;
        }

        public Criteria andPassedValueNotIn(List<String> values) {
            addCriterion("PASSED_VALUE not in", values, "passedValue");
            return (Criteria) this;
        }

        public Criteria andPassedValueBetween(String value1, String value2) {
            addCriterion("PASSED_VALUE between", value1, value2, "passedValue");
            return (Criteria) this;
        }

        public Criteria andPassedValueNotBetween(String value1, String value2) {
            addCriterion("PASSED_VALUE not between", value1, value2, "passedValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueIsNull() {
            addCriterion("MAX_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andMaxValueIsNotNull() {
            addCriterion("MAX_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andMaxValueEqualTo(String value) {
            addCriterion("MAX_VALUE =", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueNotEqualTo(String value) {
            addCriterion("MAX_VALUE <>", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueGreaterThan(String value) {
            addCriterion("MAX_VALUE >", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueGreaterThanOrEqualTo(String value) {
            addCriterion("MAX_VALUE >=", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueLessThan(String value) {
            addCriterion("MAX_VALUE <", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueLessThanOrEqualTo(String value) {
            addCriterion("MAX_VALUE <=", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueLike(String value) {
            addCriterion("MAX_VALUE like", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueNotLike(String value) {
            addCriterion("MAX_VALUE not like", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueIn(List<String> values) {
            addCriterion("MAX_VALUE in", values, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueNotIn(List<String> values) {
            addCriterion("MAX_VALUE not in", values, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueBetween(String value1, String value2) {
            addCriterion("MAX_VALUE between", value1, value2, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueNotBetween(String value1, String value2) {
            addCriterion("MAX_VALUE not between", value1, value2, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMinValueIsNull() {
            addCriterion("MIN_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andMinValueIsNotNull() {
            addCriterion("MIN_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andMinValueEqualTo(String value) {
            addCriterion("MIN_VALUE =", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueNotEqualTo(String value) {
            addCriterion("MIN_VALUE <>", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueGreaterThan(String value) {
            addCriterion("MIN_VALUE >", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueGreaterThanOrEqualTo(String value) {
            addCriterion("MIN_VALUE >=", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueLessThan(String value) {
            addCriterion("MIN_VALUE <", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueLessThanOrEqualTo(String value) {
            addCriterion("MIN_VALUE <=", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueLike(String value) {
            addCriterion("MIN_VALUE like", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueNotLike(String value) {
            addCriterion("MIN_VALUE not like", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueIn(List<String> values) {
            addCriterion("MIN_VALUE in", values, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueNotIn(List<String> values) {
            addCriterion("MIN_VALUE not in", values, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueBetween(String value1, String value2) {
            addCriterion("MIN_VALUE between", value1, value2, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueNotBetween(String value1, String value2) {
            addCriterion("MIN_VALUE not between", value1, value2, "minValue");
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