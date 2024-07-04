package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class PubAttributeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PubAttributeExample() {
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

        public Criteria andAttrFlowIsNull() {
            addCriterion("ATTR_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAttrFlowIsNotNull() {
            addCriterion("ATTR_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAttrFlowEqualTo(String value) {
            addCriterion("ATTR_FLOW =", value, "attrFlow");
            return (Criteria) this;
        }

        public Criteria andAttrFlowNotEqualTo(String value) {
            addCriterion("ATTR_FLOW <>", value, "attrFlow");
            return (Criteria) this;
        }

        public Criteria andAttrFlowGreaterThan(String value) {
            addCriterion("ATTR_FLOW >", value, "attrFlow");
            return (Criteria) this;
        }

        public Criteria andAttrFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_FLOW >=", value, "attrFlow");
            return (Criteria) this;
        }

        public Criteria andAttrFlowLessThan(String value) {
            addCriterion("ATTR_FLOW <", value, "attrFlow");
            return (Criteria) this;
        }

        public Criteria andAttrFlowLessThanOrEqualTo(String value) {
            addCriterion("ATTR_FLOW <=", value, "attrFlow");
            return (Criteria) this;
        }

        public Criteria andAttrFlowLike(String value) {
            addCriterion("ATTR_FLOW like", value, "attrFlow");
            return (Criteria) this;
        }

        public Criteria andAttrFlowNotLike(String value) {
            addCriterion("ATTR_FLOW not like", value, "attrFlow");
            return (Criteria) this;
        }

        public Criteria andAttrFlowIn(List<String> values) {
            addCriterion("ATTR_FLOW in", values, "attrFlow");
            return (Criteria) this;
        }

        public Criteria andAttrFlowNotIn(List<String> values) {
            addCriterion("ATTR_FLOW not in", values, "attrFlow");
            return (Criteria) this;
        }

        public Criteria andAttrFlowBetween(String value1, String value2) {
            addCriterion("ATTR_FLOW between", value1, value2, "attrFlow");
            return (Criteria) this;
        }

        public Criteria andAttrFlowNotBetween(String value1, String value2) {
            addCriterion("ATTR_FLOW not between", value1, value2, "attrFlow");
            return (Criteria) this;
        }

        public Criteria andAttrNameIsNull() {
            addCriterion("ATTR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAttrNameIsNotNull() {
            addCriterion("ATTR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAttrNameEqualTo(String value) {
            addCriterion("ATTR_NAME =", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotEqualTo(String value) {
            addCriterion("ATTR_NAME <>", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameGreaterThan(String value) {
            addCriterion("ATTR_NAME >", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_NAME >=", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameLessThan(String value) {
            addCriterion("ATTR_NAME <", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameLessThanOrEqualTo(String value) {
            addCriterion("ATTR_NAME <=", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameLike(String value) {
            addCriterion("ATTR_NAME like", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotLike(String value) {
            addCriterion("ATTR_NAME not like", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameIn(List<String> values) {
            addCriterion("ATTR_NAME in", values, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotIn(List<String> values) {
            addCriterion("ATTR_NAME not in", values, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameBetween(String value1, String value2) {
            addCriterion("ATTR_NAME between", value1, value2, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotBetween(String value1, String value2) {
            addCriterion("ATTR_NAME not between", value1, value2, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrCodeIsNull() {
            addCriterion("ATTR_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAttrCodeIsNotNull() {
            addCriterion("ATTR_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAttrCodeEqualTo(String value) {
            addCriterion("ATTR_CODE =", value, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeNotEqualTo(String value) {
            addCriterion("ATTR_CODE <>", value, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeGreaterThan(String value) {
            addCriterion("ATTR_CODE >", value, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_CODE >=", value, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeLessThan(String value) {
            addCriterion("ATTR_CODE <", value, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeLessThanOrEqualTo(String value) {
            addCriterion("ATTR_CODE <=", value, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeLike(String value) {
            addCriterion("ATTR_CODE like", value, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeNotLike(String value) {
            addCriterion("ATTR_CODE not like", value, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeIn(List<String> values) {
            addCriterion("ATTR_CODE in", values, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeNotIn(List<String> values) {
            addCriterion("ATTR_CODE not in", values, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeBetween(String value1, String value2) {
            addCriterion("ATTR_CODE between", value1, value2, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeNotBetween(String value1, String value2) {
            addCriterion("ATTR_CODE not between", value1, value2, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrVarNameIsNull() {
            addCriterion("ATTR_VAR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAttrVarNameIsNotNull() {
            addCriterion("ATTR_VAR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAttrVarNameEqualTo(String value) {
            addCriterion("ATTR_VAR_NAME =", value, "attrVarName");
            return (Criteria) this;
        }

        public Criteria andAttrVarNameNotEqualTo(String value) {
            addCriterion("ATTR_VAR_NAME <>", value, "attrVarName");
            return (Criteria) this;
        }

        public Criteria andAttrVarNameGreaterThan(String value) {
            addCriterion("ATTR_VAR_NAME >", value, "attrVarName");
            return (Criteria) this;
        }

        public Criteria andAttrVarNameGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_VAR_NAME >=", value, "attrVarName");
            return (Criteria) this;
        }

        public Criteria andAttrVarNameLessThan(String value) {
            addCriterion("ATTR_VAR_NAME <", value, "attrVarName");
            return (Criteria) this;
        }

        public Criteria andAttrVarNameLessThanOrEqualTo(String value) {
            addCriterion("ATTR_VAR_NAME <=", value, "attrVarName");
            return (Criteria) this;
        }

        public Criteria andAttrVarNameLike(String value) {
            addCriterion("ATTR_VAR_NAME like", value, "attrVarName");
            return (Criteria) this;
        }

        public Criteria andAttrVarNameNotLike(String value) {
            addCriterion("ATTR_VAR_NAME not like", value, "attrVarName");
            return (Criteria) this;
        }

        public Criteria andAttrVarNameIn(List<String> values) {
            addCriterion("ATTR_VAR_NAME in", values, "attrVarName");
            return (Criteria) this;
        }

        public Criteria andAttrVarNameNotIn(List<String> values) {
            addCriterion("ATTR_VAR_NAME not in", values, "attrVarName");
            return (Criteria) this;
        }

        public Criteria andAttrVarNameBetween(String value1, String value2) {
            addCriterion("ATTR_VAR_NAME between", value1, value2, "attrVarName");
            return (Criteria) this;
        }

        public Criteria andAttrVarNameNotBetween(String value1, String value2) {
            addCriterion("ATTR_VAR_NAME not between", value1, value2, "attrVarName");
            return (Criteria) this;
        }

        public Criteria andModuleCodeIsNull() {
            addCriterion("MODULE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andModuleCodeIsNotNull() {
            addCriterion("MODULE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andModuleCodeEqualTo(String value) {
            addCriterion("MODULE_CODE =", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeNotEqualTo(String value) {
            addCriterion("MODULE_CODE <>", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeGreaterThan(String value) {
            addCriterion("MODULE_CODE >", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_CODE >=", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeLessThan(String value) {
            addCriterion("MODULE_CODE <", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeLessThanOrEqualTo(String value) {
            addCriterion("MODULE_CODE <=", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeLike(String value) {
            addCriterion("MODULE_CODE like", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeNotLike(String value) {
            addCriterion("MODULE_CODE not like", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeIn(List<String> values) {
            addCriterion("MODULE_CODE in", values, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeNotIn(List<String> values) {
            addCriterion("MODULE_CODE not in", values, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeBetween(String value1, String value2) {
            addCriterion("MODULE_CODE between", value1, value2, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeNotBetween(String value1, String value2) {
            addCriterion("MODULE_CODE not between", value1, value2, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeIsNull() {
            addCriterion("ELEMENT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andElementCodeIsNotNull() {
            addCriterion("ELEMENT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andElementCodeEqualTo(String value) {
            addCriterion("ELEMENT_CODE =", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeNotEqualTo(String value) {
            addCriterion("ELEMENT_CODE <>", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeGreaterThan(String value) {
            addCriterion("ELEMENT_CODE >", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ELEMENT_CODE >=", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeLessThan(String value) {
            addCriterion("ELEMENT_CODE <", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeLessThanOrEqualTo(String value) {
            addCriterion("ELEMENT_CODE <=", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeLike(String value) {
            addCriterion("ELEMENT_CODE like", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeNotLike(String value) {
            addCriterion("ELEMENT_CODE not like", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeIn(List<String> values) {
            addCriterion("ELEMENT_CODE in", values, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeNotIn(List<String> values) {
            addCriterion("ELEMENT_CODE not in", values, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeBetween(String value1, String value2) {
            addCriterion("ELEMENT_CODE between", value1, value2, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeNotBetween(String value1, String value2) {
            addCriterion("ELEMENT_CODE not between", value1, value2, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementNameIsNull() {
            addCriterion("ELEMENT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andElementNameIsNotNull() {
            addCriterion("ELEMENT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andElementNameEqualTo(String value) {
            addCriterion("ELEMENT_NAME =", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotEqualTo(String value) {
            addCriterion("ELEMENT_NAME <>", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameGreaterThan(String value) {
            addCriterion("ELEMENT_NAME >", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameGreaterThanOrEqualTo(String value) {
            addCriterion("ELEMENT_NAME >=", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameLessThan(String value) {
            addCriterion("ELEMENT_NAME <", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameLessThanOrEqualTo(String value) {
            addCriterion("ELEMENT_NAME <=", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameLike(String value) {
            addCriterion("ELEMENT_NAME like", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotLike(String value) {
            addCriterion("ELEMENT_NAME not like", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameIn(List<String> values) {
            addCriterion("ELEMENT_NAME in", values, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotIn(List<String> values) {
            addCriterion("ELEMENT_NAME not in", values, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameBetween(String value1, String value2) {
            addCriterion("ELEMENT_NAME between", value1, value2, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotBetween(String value1, String value2) {
            addCriterion("ELEMENT_NAME not between", value1, value2, "elementName");
            return (Criteria) this;
        }

        public Criteria andDataTypeIdIsNull() {
            addCriterion("DATA_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDataTypeIdIsNotNull() {
            addCriterion("DATA_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDataTypeIdEqualTo(String value) {
            addCriterion("DATA_TYPE_ID =", value, "dataTypeId");
            return (Criteria) this;
        }

        public Criteria andDataTypeIdNotEqualTo(String value) {
            addCriterion("DATA_TYPE_ID <>", value, "dataTypeId");
            return (Criteria) this;
        }

        public Criteria andDataTypeIdGreaterThan(String value) {
            addCriterion("DATA_TYPE_ID >", value, "dataTypeId");
            return (Criteria) this;
        }

        public Criteria andDataTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_TYPE_ID >=", value, "dataTypeId");
            return (Criteria) this;
        }

        public Criteria andDataTypeIdLessThan(String value) {
            addCriterion("DATA_TYPE_ID <", value, "dataTypeId");
            return (Criteria) this;
        }

        public Criteria andDataTypeIdLessThanOrEqualTo(String value) {
            addCriterion("DATA_TYPE_ID <=", value, "dataTypeId");
            return (Criteria) this;
        }

        public Criteria andDataTypeIdLike(String value) {
            addCriterion("DATA_TYPE_ID like", value, "dataTypeId");
            return (Criteria) this;
        }

        public Criteria andDataTypeIdNotLike(String value) {
            addCriterion("DATA_TYPE_ID not like", value, "dataTypeId");
            return (Criteria) this;
        }

        public Criteria andDataTypeIdIn(List<String> values) {
            addCriterion("DATA_TYPE_ID in", values, "dataTypeId");
            return (Criteria) this;
        }

        public Criteria andDataTypeIdNotIn(List<String> values) {
            addCriterion("DATA_TYPE_ID not in", values, "dataTypeId");
            return (Criteria) this;
        }

        public Criteria andDataTypeIdBetween(String value1, String value2) {
            addCriterion("DATA_TYPE_ID between", value1, value2, "dataTypeId");
            return (Criteria) this;
        }

        public Criteria andDataTypeIdNotBetween(String value1, String value2) {
            addCriterion("DATA_TYPE_ID not between", value1, value2, "dataTypeId");
            return (Criteria) this;
        }

        public Criteria andDataTypeNameIsNull() {
            addCriterion("DATA_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDataTypeNameIsNotNull() {
            addCriterion("DATA_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDataTypeNameEqualTo(String value) {
            addCriterion("DATA_TYPE_NAME =", value, "dataTypeName");
            return (Criteria) this;
        }

        public Criteria andDataTypeNameNotEqualTo(String value) {
            addCriterion("DATA_TYPE_NAME <>", value, "dataTypeName");
            return (Criteria) this;
        }

        public Criteria andDataTypeNameGreaterThan(String value) {
            addCriterion("DATA_TYPE_NAME >", value, "dataTypeName");
            return (Criteria) this;
        }

        public Criteria andDataTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_TYPE_NAME >=", value, "dataTypeName");
            return (Criteria) this;
        }

        public Criteria andDataTypeNameLessThan(String value) {
            addCriterion("DATA_TYPE_NAME <", value, "dataTypeName");
            return (Criteria) this;
        }

        public Criteria andDataTypeNameLessThanOrEqualTo(String value) {
            addCriterion("DATA_TYPE_NAME <=", value, "dataTypeName");
            return (Criteria) this;
        }

        public Criteria andDataTypeNameLike(String value) {
            addCriterion("DATA_TYPE_NAME like", value, "dataTypeName");
            return (Criteria) this;
        }

        public Criteria andDataTypeNameNotLike(String value) {
            addCriterion("DATA_TYPE_NAME not like", value, "dataTypeName");
            return (Criteria) this;
        }

        public Criteria andDataTypeNameIn(List<String> values) {
            addCriterion("DATA_TYPE_NAME in", values, "dataTypeName");
            return (Criteria) this;
        }

        public Criteria andDataTypeNameNotIn(List<String> values) {
            addCriterion("DATA_TYPE_NAME not in", values, "dataTypeName");
            return (Criteria) this;
        }

        public Criteria andDataTypeNameBetween(String value1, String value2) {
            addCriterion("DATA_TYPE_NAME between", value1, value2, "dataTypeName");
            return (Criteria) this;
        }

        public Criteria andDataTypeNameNotBetween(String value1, String value2) {
            addCriterion("DATA_TYPE_NAME not between", value1, value2, "dataTypeName");
            return (Criteria) this;
        }

        public Criteria andDataLengthIsNull() {
            addCriterion("DATA_LENGTH is null");
            return (Criteria) this;
        }

        public Criteria andDataLengthIsNotNull() {
            addCriterion("DATA_LENGTH is not null");
            return (Criteria) this;
        }

        public Criteria andDataLengthEqualTo(String value) {
            addCriterion("DATA_LENGTH =", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthNotEqualTo(String value) {
            addCriterion("DATA_LENGTH <>", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthGreaterThan(String value) {
            addCriterion("DATA_LENGTH >", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_LENGTH >=", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthLessThan(String value) {
            addCriterion("DATA_LENGTH <", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthLessThanOrEqualTo(String value) {
            addCriterion("DATA_LENGTH <=", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthLike(String value) {
            addCriterion("DATA_LENGTH like", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthNotLike(String value) {
            addCriterion("DATA_LENGTH not like", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthIn(List<String> values) {
            addCriterion("DATA_LENGTH in", values, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthNotIn(List<String> values) {
            addCriterion("DATA_LENGTH not in", values, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthBetween(String value1, String value2) {
            addCriterion("DATA_LENGTH between", value1, value2, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthNotBetween(String value1, String value2) {
            addCriterion("DATA_LENGTH not between", value1, value2, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataDecimalLengthIsNull() {
            addCriterion("DATA_DECIMAL_LENGTH is null");
            return (Criteria) this;
        }

        public Criteria andDataDecimalLengthIsNotNull() {
            addCriterion("DATA_DECIMAL_LENGTH is not null");
            return (Criteria) this;
        }

        public Criteria andDataDecimalLengthEqualTo(String value) {
            addCriterion("DATA_DECIMAL_LENGTH =", value, "dataDecimalLength");
            return (Criteria) this;
        }

        public Criteria andDataDecimalLengthNotEqualTo(String value) {
            addCriterion("DATA_DECIMAL_LENGTH <>", value, "dataDecimalLength");
            return (Criteria) this;
        }

        public Criteria andDataDecimalLengthGreaterThan(String value) {
            addCriterion("DATA_DECIMAL_LENGTH >", value, "dataDecimalLength");
            return (Criteria) this;
        }

        public Criteria andDataDecimalLengthGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_DECIMAL_LENGTH >=", value, "dataDecimalLength");
            return (Criteria) this;
        }

        public Criteria andDataDecimalLengthLessThan(String value) {
            addCriterion("DATA_DECIMAL_LENGTH <", value, "dataDecimalLength");
            return (Criteria) this;
        }

        public Criteria andDataDecimalLengthLessThanOrEqualTo(String value) {
            addCriterion("DATA_DECIMAL_LENGTH <=", value, "dataDecimalLength");
            return (Criteria) this;
        }

        public Criteria andDataDecimalLengthLike(String value) {
            addCriterion("DATA_DECIMAL_LENGTH like", value, "dataDecimalLength");
            return (Criteria) this;
        }

        public Criteria andDataDecimalLengthNotLike(String value) {
            addCriterion("DATA_DECIMAL_LENGTH not like", value, "dataDecimalLength");
            return (Criteria) this;
        }

        public Criteria andDataDecimalLengthIn(List<String> values) {
            addCriterion("DATA_DECIMAL_LENGTH in", values, "dataDecimalLength");
            return (Criteria) this;
        }

        public Criteria andDataDecimalLengthNotIn(List<String> values) {
            addCriterion("DATA_DECIMAL_LENGTH not in", values, "dataDecimalLength");
            return (Criteria) this;
        }

        public Criteria andDataDecimalLengthBetween(String value1, String value2) {
            addCriterion("DATA_DECIMAL_LENGTH between", value1, value2, "dataDecimalLength");
            return (Criteria) this;
        }

        public Criteria andDataDecimalLengthNotBetween(String value1, String value2) {
            addCriterion("DATA_DECIMAL_LENGTH not between", value1, value2, "dataDecimalLength");
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

        public Criteria andIsViewNameIsNull() {
            addCriterion("IS_VIEW_NAME is null");
            return (Criteria) this;
        }

        public Criteria andIsViewNameIsNotNull() {
            addCriterion("IS_VIEW_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andIsViewNameEqualTo(String value) {
            addCriterion("IS_VIEW_NAME =", value, "isViewName");
            return (Criteria) this;
        }

        public Criteria andIsViewNameNotEqualTo(String value) {
            addCriterion("IS_VIEW_NAME <>", value, "isViewName");
            return (Criteria) this;
        }

        public Criteria andIsViewNameGreaterThan(String value) {
            addCriterion("IS_VIEW_NAME >", value, "isViewName");
            return (Criteria) this;
        }

        public Criteria andIsViewNameGreaterThanOrEqualTo(String value) {
            addCriterion("IS_VIEW_NAME >=", value, "isViewName");
            return (Criteria) this;
        }

        public Criteria andIsViewNameLessThan(String value) {
            addCriterion("IS_VIEW_NAME <", value, "isViewName");
            return (Criteria) this;
        }

        public Criteria andIsViewNameLessThanOrEqualTo(String value) {
            addCriterion("IS_VIEW_NAME <=", value, "isViewName");
            return (Criteria) this;
        }

        public Criteria andIsViewNameLike(String value) {
            addCriterion("IS_VIEW_NAME like", value, "isViewName");
            return (Criteria) this;
        }

        public Criteria andIsViewNameNotLike(String value) {
            addCriterion("IS_VIEW_NAME not like", value, "isViewName");
            return (Criteria) this;
        }

        public Criteria andIsViewNameIn(List<String> values) {
            addCriterion("IS_VIEW_NAME in", values, "isViewName");
            return (Criteria) this;
        }

        public Criteria andIsViewNameNotIn(List<String> values) {
            addCriterion("IS_VIEW_NAME not in", values, "isViewName");
            return (Criteria) this;
        }

        public Criteria andIsViewNameBetween(String value1, String value2) {
            addCriterion("IS_VIEW_NAME between", value1, value2, "isViewName");
            return (Criteria) this;
        }

        public Criteria andIsViewNameNotBetween(String value1, String value2) {
            addCriterion("IS_VIEW_NAME not between", value1, value2, "isViewName");
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