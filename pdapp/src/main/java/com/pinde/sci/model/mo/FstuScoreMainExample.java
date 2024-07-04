package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class FstuScoreMainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FstuScoreMainExample() {
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

        public Criteria andConfigRecordFlowIsNull() {
            addCriterion("CONFIG_RECORD_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andConfigRecordFlowIsNotNull() {
            addCriterion("CONFIG_RECORD_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andConfigRecordFlowEqualTo(String value) {
            addCriterion("CONFIG_RECORD_FLOW =", value, "configRecordFlow");
            return (Criteria) this;
        }

        public Criteria andConfigRecordFlowNotEqualTo(String value) {
            addCriterion("CONFIG_RECORD_FLOW <>", value, "configRecordFlow");
            return (Criteria) this;
        }

        public Criteria andConfigRecordFlowGreaterThan(String value) {
            addCriterion("CONFIG_RECORD_FLOW >", value, "configRecordFlow");
            return (Criteria) this;
        }

        public Criteria andConfigRecordFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CONFIG_RECORD_FLOW >=", value, "configRecordFlow");
            return (Criteria) this;
        }

        public Criteria andConfigRecordFlowLessThan(String value) {
            addCriterion("CONFIG_RECORD_FLOW <", value, "configRecordFlow");
            return (Criteria) this;
        }

        public Criteria andConfigRecordFlowLessThanOrEqualTo(String value) {
            addCriterion("CONFIG_RECORD_FLOW <=", value, "configRecordFlow");
            return (Criteria) this;
        }

        public Criteria andConfigRecordFlowLike(String value) {
            addCriterion("CONFIG_RECORD_FLOW like", value, "configRecordFlow");
            return (Criteria) this;
        }

        public Criteria andConfigRecordFlowNotLike(String value) {
            addCriterion("CONFIG_RECORD_FLOW not like", value, "configRecordFlow");
            return (Criteria) this;
        }

        public Criteria andConfigRecordFlowIn(List<String> values) {
            addCriterion("CONFIG_RECORD_FLOW in", values, "configRecordFlow");
            return (Criteria) this;
        }

        public Criteria andConfigRecordFlowNotIn(List<String> values) {
            addCriterion("CONFIG_RECORD_FLOW not in", values, "configRecordFlow");
            return (Criteria) this;
        }

        public Criteria andConfigRecordFlowBetween(String value1, String value2) {
            addCriterion("CONFIG_RECORD_FLOW between", value1, value2, "configRecordFlow");
            return (Criteria) this;
        }

        public Criteria andConfigRecordFlowNotBetween(String value1, String value2) {
            addCriterion("CONFIG_RECORD_FLOW not between", value1, value2, "configRecordFlow");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeIdIsNull() {
            addCriterion("FIRST_SCORE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeIdIsNotNull() {
            addCriterion("FIRST_SCORE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeIdEqualTo(String value) {
            addCriterion("FIRST_SCORE_TYPE_ID =", value, "firstScoreTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeIdNotEqualTo(String value) {
            addCriterion("FIRST_SCORE_TYPE_ID <>", value, "firstScoreTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeIdGreaterThan(String value) {
            addCriterion("FIRST_SCORE_TYPE_ID >", value, "firstScoreTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_SCORE_TYPE_ID >=", value, "firstScoreTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeIdLessThan(String value) {
            addCriterion("FIRST_SCORE_TYPE_ID <", value, "firstScoreTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeIdLessThanOrEqualTo(String value) {
            addCriterion("FIRST_SCORE_TYPE_ID <=", value, "firstScoreTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeIdLike(String value) {
            addCriterion("FIRST_SCORE_TYPE_ID like", value, "firstScoreTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeIdNotLike(String value) {
            addCriterion("FIRST_SCORE_TYPE_ID not like", value, "firstScoreTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeIdIn(List<String> values) {
            addCriterion("FIRST_SCORE_TYPE_ID in", values, "firstScoreTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeIdNotIn(List<String> values) {
            addCriterion("FIRST_SCORE_TYPE_ID not in", values, "firstScoreTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeIdBetween(String value1, String value2) {
            addCriterion("FIRST_SCORE_TYPE_ID between", value1, value2, "firstScoreTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeIdNotBetween(String value1, String value2) {
            addCriterion("FIRST_SCORE_TYPE_ID not between", value1, value2, "firstScoreTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeNameIsNull() {
            addCriterion("FIRST_SCORE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeNameIsNotNull() {
            addCriterion("FIRST_SCORE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeNameEqualTo(String value) {
            addCriterion("FIRST_SCORE_TYPE_NAME =", value, "firstScoreTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeNameNotEqualTo(String value) {
            addCriterion("FIRST_SCORE_TYPE_NAME <>", value, "firstScoreTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeNameGreaterThan(String value) {
            addCriterion("FIRST_SCORE_TYPE_NAME >", value, "firstScoreTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_SCORE_TYPE_NAME >=", value, "firstScoreTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeNameLessThan(String value) {
            addCriterion("FIRST_SCORE_TYPE_NAME <", value, "firstScoreTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeNameLessThanOrEqualTo(String value) {
            addCriterion("FIRST_SCORE_TYPE_NAME <=", value, "firstScoreTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeNameLike(String value) {
            addCriterion("FIRST_SCORE_TYPE_NAME like", value, "firstScoreTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeNameNotLike(String value) {
            addCriterion("FIRST_SCORE_TYPE_NAME not like", value, "firstScoreTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeNameIn(List<String> values) {
            addCriterion("FIRST_SCORE_TYPE_NAME in", values, "firstScoreTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeNameNotIn(List<String> values) {
            addCriterion("FIRST_SCORE_TYPE_NAME not in", values, "firstScoreTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeNameBetween(String value1, String value2) {
            addCriterion("FIRST_SCORE_TYPE_NAME between", value1, value2, "firstScoreTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreTypeNameNotBetween(String value1, String value2) {
            addCriterion("FIRST_SCORE_TYPE_NAME not between", value1, value2, "firstScoreTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeIdIsNull() {
            addCriterion("FIRST_PROJ_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeIdIsNotNull() {
            addCriterion("FIRST_PROJ_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeIdEqualTo(String value) {
            addCriterion("FIRST_PROJ_TYPE_ID =", value, "firstProjTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeIdNotEqualTo(String value) {
            addCriterion("FIRST_PROJ_TYPE_ID <>", value, "firstProjTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeIdGreaterThan(String value) {
            addCriterion("FIRST_PROJ_TYPE_ID >", value, "firstProjTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_PROJ_TYPE_ID >=", value, "firstProjTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeIdLessThan(String value) {
            addCriterion("FIRST_PROJ_TYPE_ID <", value, "firstProjTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeIdLessThanOrEqualTo(String value) {
            addCriterion("FIRST_PROJ_TYPE_ID <=", value, "firstProjTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeIdLike(String value) {
            addCriterion("FIRST_PROJ_TYPE_ID like", value, "firstProjTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeIdNotLike(String value) {
            addCriterion("FIRST_PROJ_TYPE_ID not like", value, "firstProjTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeIdIn(List<String> values) {
            addCriterion("FIRST_PROJ_TYPE_ID in", values, "firstProjTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeIdNotIn(List<String> values) {
            addCriterion("FIRST_PROJ_TYPE_ID not in", values, "firstProjTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeIdBetween(String value1, String value2) {
            addCriterion("FIRST_PROJ_TYPE_ID between", value1, value2, "firstProjTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeIdNotBetween(String value1, String value2) {
            addCriterion("FIRST_PROJ_TYPE_ID not between", value1, value2, "firstProjTypeId");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeNameIsNull() {
            addCriterion("FIRST_PROJ_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeNameIsNotNull() {
            addCriterion("FIRST_PROJ_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeNameEqualTo(String value) {
            addCriterion("FIRST_PROJ_TYPE_NAME =", value, "firstProjTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeNameNotEqualTo(String value) {
            addCriterion("FIRST_PROJ_TYPE_NAME <>", value, "firstProjTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeNameGreaterThan(String value) {
            addCriterion("FIRST_PROJ_TYPE_NAME >", value, "firstProjTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_PROJ_TYPE_NAME >=", value, "firstProjTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeNameLessThan(String value) {
            addCriterion("FIRST_PROJ_TYPE_NAME <", value, "firstProjTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeNameLessThanOrEqualTo(String value) {
            addCriterion("FIRST_PROJ_TYPE_NAME <=", value, "firstProjTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeNameLike(String value) {
            addCriterion("FIRST_PROJ_TYPE_NAME like", value, "firstProjTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeNameNotLike(String value) {
            addCriterion("FIRST_PROJ_TYPE_NAME not like", value, "firstProjTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeNameIn(List<String> values) {
            addCriterion("FIRST_PROJ_TYPE_NAME in", values, "firstProjTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeNameNotIn(List<String> values) {
            addCriterion("FIRST_PROJ_TYPE_NAME not in", values, "firstProjTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeNameBetween(String value1, String value2) {
            addCriterion("FIRST_PROJ_TYPE_NAME between", value1, value2, "firstProjTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstProjTypeNameNotBetween(String value1, String value2) {
            addCriterion("FIRST_PROJ_TYPE_NAME not between", value1, value2, "firstProjTypeName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemIdIsNull() {
            addCriterion("FIRST_SCORE_ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemIdIsNotNull() {
            addCriterion("FIRST_SCORE_ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemIdEqualTo(String value) {
            addCriterion("FIRST_SCORE_ITEM_ID =", value, "firstScoreItemId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemIdNotEqualTo(String value) {
            addCriterion("FIRST_SCORE_ITEM_ID <>", value, "firstScoreItemId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemIdGreaterThan(String value) {
            addCriterion("FIRST_SCORE_ITEM_ID >", value, "firstScoreItemId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_SCORE_ITEM_ID >=", value, "firstScoreItemId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemIdLessThan(String value) {
            addCriterion("FIRST_SCORE_ITEM_ID <", value, "firstScoreItemId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemIdLessThanOrEqualTo(String value) {
            addCriterion("FIRST_SCORE_ITEM_ID <=", value, "firstScoreItemId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemIdLike(String value) {
            addCriterion("FIRST_SCORE_ITEM_ID like", value, "firstScoreItemId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemIdNotLike(String value) {
            addCriterion("FIRST_SCORE_ITEM_ID not like", value, "firstScoreItemId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemIdIn(List<String> values) {
            addCriterion("FIRST_SCORE_ITEM_ID in", values, "firstScoreItemId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemIdNotIn(List<String> values) {
            addCriterion("FIRST_SCORE_ITEM_ID not in", values, "firstScoreItemId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemIdBetween(String value1, String value2) {
            addCriterion("FIRST_SCORE_ITEM_ID between", value1, value2, "firstScoreItemId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemIdNotBetween(String value1, String value2) {
            addCriterion("FIRST_SCORE_ITEM_ID not between", value1, value2, "firstScoreItemId");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemNameIsNull() {
            addCriterion("FIRST_SCORE_ITEM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemNameIsNotNull() {
            addCriterion("FIRST_SCORE_ITEM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemNameEqualTo(String value) {
            addCriterion("FIRST_SCORE_ITEM_NAME =", value, "firstScoreItemName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemNameNotEqualTo(String value) {
            addCriterion("FIRST_SCORE_ITEM_NAME <>", value, "firstScoreItemName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemNameGreaterThan(String value) {
            addCriterion("FIRST_SCORE_ITEM_NAME >", value, "firstScoreItemName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_SCORE_ITEM_NAME >=", value, "firstScoreItemName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemNameLessThan(String value) {
            addCriterion("FIRST_SCORE_ITEM_NAME <", value, "firstScoreItemName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemNameLessThanOrEqualTo(String value) {
            addCriterion("FIRST_SCORE_ITEM_NAME <=", value, "firstScoreItemName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemNameLike(String value) {
            addCriterion("FIRST_SCORE_ITEM_NAME like", value, "firstScoreItemName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemNameNotLike(String value) {
            addCriterion("FIRST_SCORE_ITEM_NAME not like", value, "firstScoreItemName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemNameIn(List<String> values) {
            addCriterion("FIRST_SCORE_ITEM_NAME in", values, "firstScoreItemName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemNameNotIn(List<String> values) {
            addCriterion("FIRST_SCORE_ITEM_NAME not in", values, "firstScoreItemName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemNameBetween(String value1, String value2) {
            addCriterion("FIRST_SCORE_ITEM_NAME between", value1, value2, "firstScoreItemName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreItemNameNotBetween(String value1, String value2) {
            addCriterion("FIRST_SCORE_ITEM_NAME not between", value1, value2, "firstScoreItemName");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionIdIsNull() {
            addCriterion("FIRST_EXECUTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionIdIsNotNull() {
            addCriterion("FIRST_EXECUTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionIdEqualTo(String value) {
            addCriterion("FIRST_EXECUTION_ID =", value, "firstExecutionId");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionIdNotEqualTo(String value) {
            addCriterion("FIRST_EXECUTION_ID <>", value, "firstExecutionId");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionIdGreaterThan(String value) {
            addCriterion("FIRST_EXECUTION_ID >", value, "firstExecutionId");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionIdGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_EXECUTION_ID >=", value, "firstExecutionId");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionIdLessThan(String value) {
            addCriterion("FIRST_EXECUTION_ID <", value, "firstExecutionId");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionIdLessThanOrEqualTo(String value) {
            addCriterion("FIRST_EXECUTION_ID <=", value, "firstExecutionId");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionIdLike(String value) {
            addCriterion("FIRST_EXECUTION_ID like", value, "firstExecutionId");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionIdNotLike(String value) {
            addCriterion("FIRST_EXECUTION_ID not like", value, "firstExecutionId");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionIdIn(List<String> values) {
            addCriterion("FIRST_EXECUTION_ID in", values, "firstExecutionId");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionIdNotIn(List<String> values) {
            addCriterion("FIRST_EXECUTION_ID not in", values, "firstExecutionId");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionIdBetween(String value1, String value2) {
            addCriterion("FIRST_EXECUTION_ID between", value1, value2, "firstExecutionId");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionIdNotBetween(String value1, String value2) {
            addCriterion("FIRST_EXECUTION_ID not between", value1, value2, "firstExecutionId");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionNameIsNull() {
            addCriterion("FIRST_EXECUTION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionNameIsNotNull() {
            addCriterion("FIRST_EXECUTION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionNameEqualTo(String value) {
            addCriterion("FIRST_EXECUTION_NAME =", value, "firstExecutionName");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionNameNotEqualTo(String value) {
            addCriterion("FIRST_EXECUTION_NAME <>", value, "firstExecutionName");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionNameGreaterThan(String value) {
            addCriterion("FIRST_EXECUTION_NAME >", value, "firstExecutionName");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionNameGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_EXECUTION_NAME >=", value, "firstExecutionName");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionNameLessThan(String value) {
            addCriterion("FIRST_EXECUTION_NAME <", value, "firstExecutionName");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionNameLessThanOrEqualTo(String value) {
            addCriterion("FIRST_EXECUTION_NAME <=", value, "firstExecutionName");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionNameLike(String value) {
            addCriterion("FIRST_EXECUTION_NAME like", value, "firstExecutionName");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionNameNotLike(String value) {
            addCriterion("FIRST_EXECUTION_NAME not like", value, "firstExecutionName");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionNameIn(List<String> values) {
            addCriterion("FIRST_EXECUTION_NAME in", values, "firstExecutionName");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionNameNotIn(List<String> values) {
            addCriterion("FIRST_EXECUTION_NAME not in", values, "firstExecutionName");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionNameBetween(String value1, String value2) {
            addCriterion("FIRST_EXECUTION_NAME between", value1, value2, "firstExecutionName");
            return (Criteria) this;
        }

        public Criteria andFirstExecutionNameNotBetween(String value1, String value2) {
            addCriterion("FIRST_EXECUTION_NAME not between", value1, value2, "firstExecutionName");
            return (Criteria) this;
        }

        public Criteria andFirstScoreIsNull() {
            addCriterion("FIRST_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andFirstScoreIsNotNull() {
            addCriterion("FIRST_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andFirstScoreEqualTo(String value) {
            addCriterion("FIRST_SCORE =", value, "firstScore");
            return (Criteria) this;
        }

        public Criteria andFirstScoreNotEqualTo(String value) {
            addCriterion("FIRST_SCORE <>", value, "firstScore");
            return (Criteria) this;
        }

        public Criteria andFirstScoreGreaterThan(String value) {
            addCriterion("FIRST_SCORE >", value, "firstScore");
            return (Criteria) this;
        }

        public Criteria andFirstScoreGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_SCORE >=", value, "firstScore");
            return (Criteria) this;
        }

        public Criteria andFirstScoreLessThan(String value) {
            addCriterion("FIRST_SCORE <", value, "firstScore");
            return (Criteria) this;
        }

        public Criteria andFirstScoreLessThanOrEqualTo(String value) {
            addCriterion("FIRST_SCORE <=", value, "firstScore");
            return (Criteria) this;
        }

        public Criteria andFirstScoreLike(String value) {
            addCriterion("FIRST_SCORE like", value, "firstScore");
            return (Criteria) this;
        }

        public Criteria andFirstScoreNotLike(String value) {
            addCriterion("FIRST_SCORE not like", value, "firstScore");
            return (Criteria) this;
        }

        public Criteria andFirstScoreIn(List<String> values) {
            addCriterion("FIRST_SCORE in", values, "firstScore");
            return (Criteria) this;
        }

        public Criteria andFirstScoreNotIn(List<String> values) {
            addCriterion("FIRST_SCORE not in", values, "firstScore");
            return (Criteria) this;
        }

        public Criteria andFirstScoreBetween(String value1, String value2) {
            addCriterion("FIRST_SCORE between", value1, value2, "firstScore");
            return (Criteria) this;
        }

        public Criteria andFirstScoreNotBetween(String value1, String value2) {
            addCriterion("FIRST_SCORE not between", value1, value2, "firstScore");
            return (Criteria) this;
        }

        public Criteria andFirstMaxScoreIsNull() {
            addCriterion("FIRST_MAX_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andFirstMaxScoreIsNotNull() {
            addCriterion("FIRST_MAX_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andFirstMaxScoreEqualTo(String value) {
            addCriterion("FIRST_MAX_SCORE =", value, "firstMaxScore");
            return (Criteria) this;
        }

        public Criteria andFirstMaxScoreNotEqualTo(String value) {
            addCriterion("FIRST_MAX_SCORE <>", value, "firstMaxScore");
            return (Criteria) this;
        }

        public Criteria andFirstMaxScoreGreaterThan(String value) {
            addCriterion("FIRST_MAX_SCORE >", value, "firstMaxScore");
            return (Criteria) this;
        }

        public Criteria andFirstMaxScoreGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_MAX_SCORE >=", value, "firstMaxScore");
            return (Criteria) this;
        }

        public Criteria andFirstMaxScoreLessThan(String value) {
            addCriterion("FIRST_MAX_SCORE <", value, "firstMaxScore");
            return (Criteria) this;
        }

        public Criteria andFirstMaxScoreLessThanOrEqualTo(String value) {
            addCriterion("FIRST_MAX_SCORE <=", value, "firstMaxScore");
            return (Criteria) this;
        }

        public Criteria andFirstMaxScoreLike(String value) {
            addCriterion("FIRST_MAX_SCORE like", value, "firstMaxScore");
            return (Criteria) this;
        }

        public Criteria andFirstMaxScoreNotLike(String value) {
            addCriterion("FIRST_MAX_SCORE not like", value, "firstMaxScore");
            return (Criteria) this;
        }

        public Criteria andFirstMaxScoreIn(List<String> values) {
            addCriterion("FIRST_MAX_SCORE in", values, "firstMaxScore");
            return (Criteria) this;
        }

        public Criteria andFirstMaxScoreNotIn(List<String> values) {
            addCriterion("FIRST_MAX_SCORE not in", values, "firstMaxScore");
            return (Criteria) this;
        }

        public Criteria andFirstMaxScoreBetween(String value1, String value2) {
            addCriterion("FIRST_MAX_SCORE between", value1, value2, "firstMaxScore");
            return (Criteria) this;
        }

        public Criteria andFirstMaxScoreNotBetween(String value1, String value2) {
            addCriterion("FIRST_MAX_SCORE not between", value1, value2, "firstMaxScore");
            return (Criteria) this;
        }

        public Criteria andFirstMiurAuditFlagIsNull() {
            addCriterion("FIRST_MIUR_AUDIT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andFirstMiurAuditFlagIsNotNull() {
            addCriterion("FIRST_MIUR_AUDIT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andFirstMiurAuditFlagEqualTo(String value) {
            addCriterion("FIRST_MIUR_AUDIT_FLAG =", value, "firstMiurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstMiurAuditFlagNotEqualTo(String value) {
            addCriterion("FIRST_MIUR_AUDIT_FLAG <>", value, "firstMiurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstMiurAuditFlagGreaterThan(String value) {
            addCriterion("FIRST_MIUR_AUDIT_FLAG >", value, "firstMiurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstMiurAuditFlagGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_MIUR_AUDIT_FLAG >=", value, "firstMiurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstMiurAuditFlagLessThan(String value) {
            addCriterion("FIRST_MIUR_AUDIT_FLAG <", value, "firstMiurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstMiurAuditFlagLessThanOrEqualTo(String value) {
            addCriterion("FIRST_MIUR_AUDIT_FLAG <=", value, "firstMiurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstMiurAuditFlagLike(String value) {
            addCriterion("FIRST_MIUR_AUDIT_FLAG like", value, "firstMiurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstMiurAuditFlagNotLike(String value) {
            addCriterion("FIRST_MIUR_AUDIT_FLAG not like", value, "firstMiurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstMiurAuditFlagIn(List<String> values) {
            addCriterion("FIRST_MIUR_AUDIT_FLAG in", values, "firstMiurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstMiurAuditFlagNotIn(List<String> values) {
            addCriterion("FIRST_MIUR_AUDIT_FLAG not in", values, "firstMiurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstMiurAuditFlagBetween(String value1, String value2) {
            addCriterion("FIRST_MIUR_AUDIT_FLAG between", value1, value2, "firstMiurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstMiurAuditFlagNotBetween(String value1, String value2) {
            addCriterion("FIRST_MIUR_AUDIT_FLAG not between", value1, value2, "firstMiurAuditFlag");
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

        public Criteria andUserCodeIsNull() {
            addCriterion("USER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andUserCodeIsNotNull() {
            addCriterion("USER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andUserCodeEqualTo(String value) {
            addCriterion("USER_CODE =", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotEqualTo(String value) {
            addCriterion("USER_CODE <>", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeGreaterThan(String value) {
            addCriterion("USER_CODE >", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeGreaterThanOrEqualTo(String value) {
            addCriterion("USER_CODE >=", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeLessThan(String value) {
            addCriterion("USER_CODE <", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeLessThanOrEqualTo(String value) {
            addCriterion("USER_CODE <=", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeLike(String value) {
            addCriterion("USER_CODE like", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotLike(String value) {
            addCriterion("USER_CODE not like", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeIn(List<String> values) {
            addCriterion("USER_CODE in", values, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotIn(List<String> values) {
            addCriterion("USER_CODE not in", values, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeBetween(String value1, String value2) {
            addCriterion("USER_CODE between", value1, value2, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotBetween(String value1, String value2) {
            addCriterion("USER_CODE not between", value1, value2, "userCode");
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

        public Criteria andUserDeptFlowIsNull() {
            addCriterion("USER_DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andUserDeptFlowIsNotNull() {
            addCriterion("USER_DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andUserDeptFlowEqualTo(String value) {
            addCriterion("USER_DEPT_FLOW =", value, "userDeptFlow");
            return (Criteria) this;
        }

        public Criteria andUserDeptFlowNotEqualTo(String value) {
            addCriterion("USER_DEPT_FLOW <>", value, "userDeptFlow");
            return (Criteria) this;
        }

        public Criteria andUserDeptFlowGreaterThan(String value) {
            addCriterion("USER_DEPT_FLOW >", value, "userDeptFlow");
            return (Criteria) this;
        }

        public Criteria andUserDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("USER_DEPT_FLOW >=", value, "userDeptFlow");
            return (Criteria) this;
        }

        public Criteria andUserDeptFlowLessThan(String value) {
            addCriterion("USER_DEPT_FLOW <", value, "userDeptFlow");
            return (Criteria) this;
        }

        public Criteria andUserDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("USER_DEPT_FLOW <=", value, "userDeptFlow");
            return (Criteria) this;
        }

        public Criteria andUserDeptFlowLike(String value) {
            addCriterion("USER_DEPT_FLOW like", value, "userDeptFlow");
            return (Criteria) this;
        }

        public Criteria andUserDeptFlowNotLike(String value) {
            addCriterion("USER_DEPT_FLOW not like", value, "userDeptFlow");
            return (Criteria) this;
        }

        public Criteria andUserDeptFlowIn(List<String> values) {
            addCriterion("USER_DEPT_FLOW in", values, "userDeptFlow");
            return (Criteria) this;
        }

        public Criteria andUserDeptFlowNotIn(List<String> values) {
            addCriterion("USER_DEPT_FLOW not in", values, "userDeptFlow");
            return (Criteria) this;
        }

        public Criteria andUserDeptFlowBetween(String value1, String value2) {
            addCriterion("USER_DEPT_FLOW between", value1, value2, "userDeptFlow");
            return (Criteria) this;
        }

        public Criteria andUserDeptFlowNotBetween(String value1, String value2) {
            addCriterion("USER_DEPT_FLOW not between", value1, value2, "userDeptFlow");
            return (Criteria) this;
        }

        public Criteria andUserDeptNameIsNull() {
            addCriterion("USER_DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserDeptNameIsNotNull() {
            addCriterion("USER_DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserDeptNameEqualTo(String value) {
            addCriterion("USER_DEPT_NAME =", value, "userDeptName");
            return (Criteria) this;
        }

        public Criteria andUserDeptNameNotEqualTo(String value) {
            addCriterion("USER_DEPT_NAME <>", value, "userDeptName");
            return (Criteria) this;
        }

        public Criteria andUserDeptNameGreaterThan(String value) {
            addCriterion("USER_DEPT_NAME >", value, "userDeptName");
            return (Criteria) this;
        }

        public Criteria andUserDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_DEPT_NAME >=", value, "userDeptName");
            return (Criteria) this;
        }

        public Criteria andUserDeptNameLessThan(String value) {
            addCriterion("USER_DEPT_NAME <", value, "userDeptName");
            return (Criteria) this;
        }

        public Criteria andUserDeptNameLessThanOrEqualTo(String value) {
            addCriterion("USER_DEPT_NAME <=", value, "userDeptName");
            return (Criteria) this;
        }

        public Criteria andUserDeptNameLike(String value) {
            addCriterion("USER_DEPT_NAME like", value, "userDeptName");
            return (Criteria) this;
        }

        public Criteria andUserDeptNameNotLike(String value) {
            addCriterion("USER_DEPT_NAME not like", value, "userDeptName");
            return (Criteria) this;
        }

        public Criteria andUserDeptNameIn(List<String> values) {
            addCriterion("USER_DEPT_NAME in", values, "userDeptName");
            return (Criteria) this;
        }

        public Criteria andUserDeptNameNotIn(List<String> values) {
            addCriterion("USER_DEPT_NAME not in", values, "userDeptName");
            return (Criteria) this;
        }

        public Criteria andUserDeptNameBetween(String value1, String value2) {
            addCriterion("USER_DEPT_NAME between", value1, value2, "userDeptName");
            return (Criteria) this;
        }

        public Criteria andUserDeptNameNotBetween(String value1, String value2) {
            addCriterion("USER_DEPT_NAME not between", value1, value2, "userDeptName");
            return (Criteria) this;
        }

        public Criteria andScoreNameIsNull() {
            addCriterion("SCORE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andScoreNameIsNotNull() {
            addCriterion("SCORE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andScoreNameEqualTo(String value) {
            addCriterion("SCORE_NAME =", value, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameNotEqualTo(String value) {
            addCriterion("SCORE_NAME <>", value, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameGreaterThan(String value) {
            addCriterion("SCORE_NAME >", value, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_NAME >=", value, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameLessThan(String value) {
            addCriterion("SCORE_NAME <", value, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameLessThanOrEqualTo(String value) {
            addCriterion("SCORE_NAME <=", value, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameLike(String value) {
            addCriterion("SCORE_NAME like", value, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameNotLike(String value) {
            addCriterion("SCORE_NAME not like", value, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameIn(List<String> values) {
            addCriterion("SCORE_NAME in", values, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameNotIn(List<String> values) {
            addCriterion("SCORE_NAME not in", values, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameBetween(String value1, String value2) {
            addCriterion("SCORE_NAME between", value1, value2, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameNotBetween(String value1, String value2) {
            addCriterion("SCORE_NAME not between", value1, value2, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNumberIsNull() {
            addCriterion("SCORE_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andScoreNumberIsNotNull() {
            addCriterion("SCORE_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andScoreNumberEqualTo(String value) {
            addCriterion("SCORE_NUMBER =", value, "scoreNumber");
            return (Criteria) this;
        }

        public Criteria andScoreNumberNotEqualTo(String value) {
            addCriterion("SCORE_NUMBER <>", value, "scoreNumber");
            return (Criteria) this;
        }

        public Criteria andScoreNumberGreaterThan(String value) {
            addCriterion("SCORE_NUMBER >", value, "scoreNumber");
            return (Criteria) this;
        }

        public Criteria andScoreNumberGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_NUMBER >=", value, "scoreNumber");
            return (Criteria) this;
        }

        public Criteria andScoreNumberLessThan(String value) {
            addCriterion("SCORE_NUMBER <", value, "scoreNumber");
            return (Criteria) this;
        }

        public Criteria andScoreNumberLessThanOrEqualTo(String value) {
            addCriterion("SCORE_NUMBER <=", value, "scoreNumber");
            return (Criteria) this;
        }

        public Criteria andScoreNumberLike(String value) {
            addCriterion("SCORE_NUMBER like", value, "scoreNumber");
            return (Criteria) this;
        }

        public Criteria andScoreNumberNotLike(String value) {
            addCriterion("SCORE_NUMBER not like", value, "scoreNumber");
            return (Criteria) this;
        }

        public Criteria andScoreNumberIn(List<String> values) {
            addCriterion("SCORE_NUMBER in", values, "scoreNumber");
            return (Criteria) this;
        }

        public Criteria andScoreNumberNotIn(List<String> values) {
            addCriterion("SCORE_NUMBER not in", values, "scoreNumber");
            return (Criteria) this;
        }

        public Criteria andScoreNumberBetween(String value1, String value2) {
            addCriterion("SCORE_NUMBER between", value1, value2, "scoreNumber");
            return (Criteria) this;
        }

        public Criteria andScoreNumberNotBetween(String value1, String value2) {
            addCriterion("SCORE_NUMBER not between", value1, value2, "scoreNumber");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("BEGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("BEGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(String value) {
            addCriterion("BEGIN_TIME =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(String value) {
            addCriterion("BEGIN_TIME <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(String value) {
            addCriterion("BEGIN_TIME >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(String value) {
            addCriterion("BEGIN_TIME >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(String value) {
            addCriterion("BEGIN_TIME <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(String value) {
            addCriterion("BEGIN_TIME <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLike(String value) {
            addCriterion("BEGIN_TIME like", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotLike(String value) {
            addCriterion("BEGIN_TIME not like", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<String> values) {
            addCriterion("BEGIN_TIME in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<String> values) {
            addCriterion("BEGIN_TIME not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(String value1, String value2) {
            addCriterion("BEGIN_TIME between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(String value1, String value2) {
            addCriterion("BEGIN_TIME not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(String value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(String value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(String value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(String value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(String value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLike(String value) {
            addCriterion("END_TIME like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotLike(String value) {
            addCriterion("END_TIME not like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<String> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<String> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(String value1, String value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(String value1, String value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andInstructionIsNull() {
            addCriterion("INSTRUCTION is null");
            return (Criteria) this;
        }

        public Criteria andInstructionIsNotNull() {
            addCriterion("INSTRUCTION is not null");
            return (Criteria) this;
        }

        public Criteria andInstructionEqualTo(String value) {
            addCriterion("INSTRUCTION =", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotEqualTo(String value) {
            addCriterion("INSTRUCTION <>", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionGreaterThan(String value) {
            addCriterion("INSTRUCTION >", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionGreaterThanOrEqualTo(String value) {
            addCriterion("INSTRUCTION >=", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionLessThan(String value) {
            addCriterion("INSTRUCTION <", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionLessThanOrEqualTo(String value) {
            addCriterion("INSTRUCTION <=", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionLike(String value) {
            addCriterion("INSTRUCTION like", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotLike(String value) {
            addCriterion("INSTRUCTION not like", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionIn(List<String> values) {
            addCriterion("INSTRUCTION in", values, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotIn(List<String> values) {
            addCriterion("INSTRUCTION not in", values, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionBetween(String value1, String value2) {
            addCriterion("INSTRUCTION between", value1, value2, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotBetween(String value1, String value2) {
            addCriterion("INSTRUCTION not between", value1, value2, "instruction");
            return (Criteria) this;
        }

        public Criteria andMyScoreIsNull() {
            addCriterion("MY_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andMyScoreIsNotNull() {
            addCriterion("MY_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andMyScoreEqualTo(String value) {
            addCriterion("MY_SCORE =", value, "myScore");
            return (Criteria) this;
        }

        public Criteria andMyScoreNotEqualTo(String value) {
            addCriterion("MY_SCORE <>", value, "myScore");
            return (Criteria) this;
        }

        public Criteria andMyScoreGreaterThan(String value) {
            addCriterion("MY_SCORE >", value, "myScore");
            return (Criteria) this;
        }

        public Criteria andMyScoreGreaterThanOrEqualTo(String value) {
            addCriterion("MY_SCORE >=", value, "myScore");
            return (Criteria) this;
        }

        public Criteria andMyScoreLessThan(String value) {
            addCriterion("MY_SCORE <", value, "myScore");
            return (Criteria) this;
        }

        public Criteria andMyScoreLessThanOrEqualTo(String value) {
            addCriterion("MY_SCORE <=", value, "myScore");
            return (Criteria) this;
        }

        public Criteria andMyScoreLike(String value) {
            addCriterion("MY_SCORE like", value, "myScore");
            return (Criteria) this;
        }

        public Criteria andMyScoreNotLike(String value) {
            addCriterion("MY_SCORE not like", value, "myScore");
            return (Criteria) this;
        }

        public Criteria andMyScoreIn(List<String> values) {
            addCriterion("MY_SCORE in", values, "myScore");
            return (Criteria) this;
        }

        public Criteria andMyScoreNotIn(List<String> values) {
            addCriterion("MY_SCORE not in", values, "myScore");
            return (Criteria) this;
        }

        public Criteria andMyScoreBetween(String value1, String value2) {
            addCriterion("MY_SCORE between", value1, value2, "myScore");
            return (Criteria) this;
        }

        public Criteria andMyScoreNotBetween(String value1, String value2) {
            addCriterion("MY_SCORE not between", value1, value2, "myScore");
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

        public Criteria andHeaderAuditStatusIdIsNull() {
            addCriterion("HEADER_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusIdIsNotNull() {
            addCriterion("HEADER_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusIdEqualTo(String value) {
            addCriterion("HEADER_AUDIT_STATUS_ID =", value, "headerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusIdNotEqualTo(String value) {
            addCriterion("HEADER_AUDIT_STATUS_ID <>", value, "headerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusIdGreaterThan(String value) {
            addCriterion("HEADER_AUDIT_STATUS_ID >", value, "headerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("HEADER_AUDIT_STATUS_ID >=", value, "headerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusIdLessThan(String value) {
            addCriterion("HEADER_AUDIT_STATUS_ID <", value, "headerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("HEADER_AUDIT_STATUS_ID <=", value, "headerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusIdLike(String value) {
            addCriterion("HEADER_AUDIT_STATUS_ID like", value, "headerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusIdNotLike(String value) {
            addCriterion("HEADER_AUDIT_STATUS_ID not like", value, "headerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusIdIn(List<String> values) {
            addCriterion("HEADER_AUDIT_STATUS_ID in", values, "headerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusIdNotIn(List<String> values) {
            addCriterion("HEADER_AUDIT_STATUS_ID not in", values, "headerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusIdBetween(String value1, String value2) {
            addCriterion("HEADER_AUDIT_STATUS_ID between", value1, value2, "headerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("HEADER_AUDIT_STATUS_ID not between", value1, value2, "headerAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusNameIsNull() {
            addCriterion("HEADER_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusNameIsNotNull() {
            addCriterion("HEADER_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusNameEqualTo(String value) {
            addCriterion("HEADER_AUDIT_STATUS_NAME =", value, "headerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusNameNotEqualTo(String value) {
            addCriterion("HEADER_AUDIT_STATUS_NAME <>", value, "headerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusNameGreaterThan(String value) {
            addCriterion("HEADER_AUDIT_STATUS_NAME >", value, "headerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("HEADER_AUDIT_STATUS_NAME >=", value, "headerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusNameLessThan(String value) {
            addCriterion("HEADER_AUDIT_STATUS_NAME <", value, "headerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("HEADER_AUDIT_STATUS_NAME <=", value, "headerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusNameLike(String value) {
            addCriterion("HEADER_AUDIT_STATUS_NAME like", value, "headerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusNameNotLike(String value) {
            addCriterion("HEADER_AUDIT_STATUS_NAME not like", value, "headerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusNameIn(List<String> values) {
            addCriterion("HEADER_AUDIT_STATUS_NAME in", values, "headerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusNameNotIn(List<String> values) {
            addCriterion("HEADER_AUDIT_STATUS_NAME not in", values, "headerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusNameBetween(String value1, String value2) {
            addCriterion("HEADER_AUDIT_STATUS_NAME between", value1, value2, "headerAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andHeaderAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("HEADER_AUDIT_STATUS_NAME not between", value1, value2, "headerAuditStatusName");
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