package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EdcElementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdcElementExample() {
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

        public Criteria andElementFlowIsNull() {
            addCriterion("ELEMENT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andElementFlowIsNotNull() {
            addCriterion("ELEMENT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andElementFlowEqualTo(String value) {
            addCriterion("ELEMENT_FLOW =", value, "elementFlow");
            return (Criteria) this;
        }

        public Criteria andElementFlowNotEqualTo(String value) {
            addCriterion("ELEMENT_FLOW <>", value, "elementFlow");
            return (Criteria) this;
        }

        public Criteria andElementFlowGreaterThan(String value) {
            addCriterion("ELEMENT_FLOW >", value, "elementFlow");
            return (Criteria) this;
        }

        public Criteria andElementFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ELEMENT_FLOW >=", value, "elementFlow");
            return (Criteria) this;
        }

        public Criteria andElementFlowLessThan(String value) {
            addCriterion("ELEMENT_FLOW <", value, "elementFlow");
            return (Criteria) this;
        }

        public Criteria andElementFlowLessThanOrEqualTo(String value) {
            addCriterion("ELEMENT_FLOW <=", value, "elementFlow");
            return (Criteria) this;
        }

        public Criteria andElementFlowLike(String value) {
            addCriterion("ELEMENT_FLOW like", value, "elementFlow");
            return (Criteria) this;
        }

        public Criteria andElementFlowNotLike(String value) {
            addCriterion("ELEMENT_FLOW not like", value, "elementFlow");
            return (Criteria) this;
        }

        public Criteria andElementFlowIn(List<String> values) {
            addCriterion("ELEMENT_FLOW in", values, "elementFlow");
            return (Criteria) this;
        }

        public Criteria andElementFlowNotIn(List<String> values) {
            addCriterion("ELEMENT_FLOW not in", values, "elementFlow");
            return (Criteria) this;
        }

        public Criteria andElementFlowBetween(String value1, String value2) {
            addCriterion("ELEMENT_FLOW between", value1, value2, "elementFlow");
            return (Criteria) this;
        }

        public Criteria andElementFlowNotBetween(String value1, String value2) {
            addCriterion("ELEMENT_FLOW not between", value1, value2, "elementFlow");
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

        public Criteria andElementVarNameIsNull() {
            addCriterion("ELEMENT_VAR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andElementVarNameIsNotNull() {
            addCriterion("ELEMENT_VAR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andElementVarNameEqualTo(String value) {
            addCriterion("ELEMENT_VAR_NAME =", value, "elementVarName");
            return (Criteria) this;
        }

        public Criteria andElementVarNameNotEqualTo(String value) {
            addCriterion("ELEMENT_VAR_NAME <>", value, "elementVarName");
            return (Criteria) this;
        }

        public Criteria andElementVarNameGreaterThan(String value) {
            addCriterion("ELEMENT_VAR_NAME >", value, "elementVarName");
            return (Criteria) this;
        }

        public Criteria andElementVarNameGreaterThanOrEqualTo(String value) {
            addCriterion("ELEMENT_VAR_NAME >=", value, "elementVarName");
            return (Criteria) this;
        }

        public Criteria andElementVarNameLessThan(String value) {
            addCriterion("ELEMENT_VAR_NAME <", value, "elementVarName");
            return (Criteria) this;
        }

        public Criteria andElementVarNameLessThanOrEqualTo(String value) {
            addCriterion("ELEMENT_VAR_NAME <=", value, "elementVarName");
            return (Criteria) this;
        }

        public Criteria andElementVarNameLike(String value) {
            addCriterion("ELEMENT_VAR_NAME like", value, "elementVarName");
            return (Criteria) this;
        }

        public Criteria andElementVarNameNotLike(String value) {
            addCriterion("ELEMENT_VAR_NAME not like", value, "elementVarName");
            return (Criteria) this;
        }

        public Criteria andElementVarNameIn(List<String> values) {
            addCriterion("ELEMENT_VAR_NAME in", values, "elementVarName");
            return (Criteria) this;
        }

        public Criteria andElementVarNameNotIn(List<String> values) {
            addCriterion("ELEMENT_VAR_NAME not in", values, "elementVarName");
            return (Criteria) this;
        }

        public Criteria andElementVarNameBetween(String value1, String value2) {
            addCriterion("ELEMENT_VAR_NAME between", value1, value2, "elementVarName");
            return (Criteria) this;
        }

        public Criteria andElementVarNameNotBetween(String value1, String value2) {
            addCriterion("ELEMENT_VAR_NAME not between", value1, value2, "elementVarName");
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

        public Criteria andElementSerialIsNull() {
            addCriterion("ELEMENT_SERIAL is null");
            return (Criteria) this;
        }

        public Criteria andElementSerialIsNotNull() {
            addCriterion("ELEMENT_SERIAL is not null");
            return (Criteria) this;
        }

        public Criteria andElementSerialEqualTo(String value) {
            addCriterion("ELEMENT_SERIAL =", value, "elementSerial");
            return (Criteria) this;
        }

        public Criteria andElementSerialNotEqualTo(String value) {
            addCriterion("ELEMENT_SERIAL <>", value, "elementSerial");
            return (Criteria) this;
        }

        public Criteria andElementSerialGreaterThan(String value) {
            addCriterion("ELEMENT_SERIAL >", value, "elementSerial");
            return (Criteria) this;
        }

        public Criteria andElementSerialGreaterThanOrEqualTo(String value) {
            addCriterion("ELEMENT_SERIAL >=", value, "elementSerial");
            return (Criteria) this;
        }

        public Criteria andElementSerialLessThan(String value) {
            addCriterion("ELEMENT_SERIAL <", value, "elementSerial");
            return (Criteria) this;
        }

        public Criteria andElementSerialLessThanOrEqualTo(String value) {
            addCriterion("ELEMENT_SERIAL <=", value, "elementSerial");
            return (Criteria) this;
        }

        public Criteria andElementSerialLike(String value) {
            addCriterion("ELEMENT_SERIAL like", value, "elementSerial");
            return (Criteria) this;
        }

        public Criteria andElementSerialNotLike(String value) {
            addCriterion("ELEMENT_SERIAL not like", value, "elementSerial");
            return (Criteria) this;
        }

        public Criteria andElementSerialIn(List<String> values) {
            addCriterion("ELEMENT_SERIAL in", values, "elementSerial");
            return (Criteria) this;
        }

        public Criteria andElementSerialNotIn(List<String> values) {
            addCriterion("ELEMENT_SERIAL not in", values, "elementSerial");
            return (Criteria) this;
        }

        public Criteria andElementSerialBetween(String value1, String value2) {
            addCriterion("ELEMENT_SERIAL between", value1, value2, "elementSerial");
            return (Criteria) this;
        }

        public Criteria andElementSerialNotBetween(String value1, String value2) {
            addCriterion("ELEMENT_SERIAL not between", value1, value2, "elementSerial");
            return (Criteria) this;
        }

        public Criteria andColumnCountIsNull() {
            addCriterion("COLUMN_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andColumnCountIsNotNull() {
            addCriterion("COLUMN_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andColumnCountEqualTo(String value) {
            addCriterion("COLUMN_COUNT =", value, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountNotEqualTo(String value) {
            addCriterion("COLUMN_COUNT <>", value, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountGreaterThan(String value) {
            addCriterion("COLUMN_COUNT >", value, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountGreaterThanOrEqualTo(String value) {
            addCriterion("COLUMN_COUNT >=", value, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountLessThan(String value) {
            addCriterion("COLUMN_COUNT <", value, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountLessThanOrEqualTo(String value) {
            addCriterion("COLUMN_COUNT <=", value, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountLike(String value) {
            addCriterion("COLUMN_COUNT like", value, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountNotLike(String value) {
            addCriterion("COLUMN_COUNT not like", value, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountIn(List<String> values) {
            addCriterion("COLUMN_COUNT in", values, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountNotIn(List<String> values) {
            addCriterion("COLUMN_COUNT not in", values, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountBetween(String value1, String value2) {
            addCriterion("COLUMN_COUNT between", value1, value2, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountNotBetween(String value1, String value2) {
            addCriterion("COLUMN_COUNT not between", value1, value2, "columnCount");
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

        public Criteria andFixedWidthIsNull() {
            addCriterion("FIXED_WIDTH is null");
            return (Criteria) this;
        }

        public Criteria andFixedWidthIsNotNull() {
            addCriterion("FIXED_WIDTH is not null");
            return (Criteria) this;
        }

        public Criteria andFixedWidthEqualTo(String value) {
            addCriterion("FIXED_WIDTH =", value, "fixedWidth");
            return (Criteria) this;
        }

        public Criteria andFixedWidthNotEqualTo(String value) {
            addCriterion("FIXED_WIDTH <>", value, "fixedWidth");
            return (Criteria) this;
        }

        public Criteria andFixedWidthGreaterThan(String value) {
            addCriterion("FIXED_WIDTH >", value, "fixedWidth");
            return (Criteria) this;
        }

        public Criteria andFixedWidthGreaterThanOrEqualTo(String value) {
            addCriterion("FIXED_WIDTH >=", value, "fixedWidth");
            return (Criteria) this;
        }

        public Criteria andFixedWidthLessThan(String value) {
            addCriterion("FIXED_WIDTH <", value, "fixedWidth");
            return (Criteria) this;
        }

        public Criteria andFixedWidthLessThanOrEqualTo(String value) {
            addCriterion("FIXED_WIDTH <=", value, "fixedWidth");
            return (Criteria) this;
        }

        public Criteria andFixedWidthLike(String value) {
            addCriterion("FIXED_WIDTH like", value, "fixedWidth");
            return (Criteria) this;
        }

        public Criteria andFixedWidthNotLike(String value) {
            addCriterion("FIXED_WIDTH not like", value, "fixedWidth");
            return (Criteria) this;
        }

        public Criteria andFixedWidthIn(List<String> values) {
            addCriterion("FIXED_WIDTH in", values, "fixedWidth");
            return (Criteria) this;
        }

        public Criteria andFixedWidthNotIn(List<String> values) {
            addCriterion("FIXED_WIDTH not in", values, "fixedWidth");
            return (Criteria) this;
        }

        public Criteria andFixedWidthBetween(String value1, String value2) {
            addCriterion("FIXED_WIDTH between", value1, value2, "fixedWidth");
            return (Criteria) this;
        }

        public Criteria andFixedWidthNotBetween(String value1, String value2) {
            addCriterion("FIXED_WIDTH not between", value1, value2, "fixedWidth");
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