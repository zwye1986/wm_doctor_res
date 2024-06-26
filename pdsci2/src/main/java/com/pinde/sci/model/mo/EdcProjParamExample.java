package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EdcProjParamExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdcProjParamExample() {
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

        public Criteria andViewTypeIdIsNull() {
            addCriterion("VIEW_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andViewTypeIdIsNotNull() {
            addCriterion("VIEW_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andViewTypeIdEqualTo(String value) {
            addCriterion("VIEW_TYPE_ID =", value, "viewTypeId");
            return (Criteria) this;
        }

        public Criteria andViewTypeIdNotEqualTo(String value) {
            addCriterion("VIEW_TYPE_ID <>", value, "viewTypeId");
            return (Criteria) this;
        }

        public Criteria andViewTypeIdGreaterThan(String value) {
            addCriterion("VIEW_TYPE_ID >", value, "viewTypeId");
            return (Criteria) this;
        }

        public Criteria andViewTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("VIEW_TYPE_ID >=", value, "viewTypeId");
            return (Criteria) this;
        }

        public Criteria andViewTypeIdLessThan(String value) {
            addCriterion("VIEW_TYPE_ID <", value, "viewTypeId");
            return (Criteria) this;
        }

        public Criteria andViewTypeIdLessThanOrEqualTo(String value) {
            addCriterion("VIEW_TYPE_ID <=", value, "viewTypeId");
            return (Criteria) this;
        }

        public Criteria andViewTypeIdLike(String value) {
            addCriterion("VIEW_TYPE_ID like", value, "viewTypeId");
            return (Criteria) this;
        }

        public Criteria andViewTypeIdNotLike(String value) {
            addCriterion("VIEW_TYPE_ID not like", value, "viewTypeId");
            return (Criteria) this;
        }

        public Criteria andViewTypeIdIn(List<String> values) {
            addCriterion("VIEW_TYPE_ID in", values, "viewTypeId");
            return (Criteria) this;
        }

        public Criteria andViewTypeIdNotIn(List<String> values) {
            addCriterion("VIEW_TYPE_ID not in", values, "viewTypeId");
            return (Criteria) this;
        }

        public Criteria andViewTypeIdBetween(String value1, String value2) {
            addCriterion("VIEW_TYPE_ID between", value1, value2, "viewTypeId");
            return (Criteria) this;
        }

        public Criteria andViewTypeIdNotBetween(String value1, String value2) {
            addCriterion("VIEW_TYPE_ID not between", value1, value2, "viewTypeId");
            return (Criteria) this;
        }

        public Criteria andViewTypeNameIsNull() {
            addCriterion("VIEW_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andViewTypeNameIsNotNull() {
            addCriterion("VIEW_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andViewTypeNameEqualTo(String value) {
            addCriterion("VIEW_TYPE_NAME =", value, "viewTypeName");
            return (Criteria) this;
        }

        public Criteria andViewTypeNameNotEqualTo(String value) {
            addCriterion("VIEW_TYPE_NAME <>", value, "viewTypeName");
            return (Criteria) this;
        }

        public Criteria andViewTypeNameGreaterThan(String value) {
            addCriterion("VIEW_TYPE_NAME >", value, "viewTypeName");
            return (Criteria) this;
        }

        public Criteria andViewTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("VIEW_TYPE_NAME >=", value, "viewTypeName");
            return (Criteria) this;
        }

        public Criteria andViewTypeNameLessThan(String value) {
            addCriterion("VIEW_TYPE_NAME <", value, "viewTypeName");
            return (Criteria) this;
        }

        public Criteria andViewTypeNameLessThanOrEqualTo(String value) {
            addCriterion("VIEW_TYPE_NAME <=", value, "viewTypeName");
            return (Criteria) this;
        }

        public Criteria andViewTypeNameLike(String value) {
            addCriterion("VIEW_TYPE_NAME like", value, "viewTypeName");
            return (Criteria) this;
        }

        public Criteria andViewTypeNameNotLike(String value) {
            addCriterion("VIEW_TYPE_NAME not like", value, "viewTypeName");
            return (Criteria) this;
        }

        public Criteria andViewTypeNameIn(List<String> values) {
            addCriterion("VIEW_TYPE_NAME in", values, "viewTypeName");
            return (Criteria) this;
        }

        public Criteria andViewTypeNameNotIn(List<String> values) {
            addCriterion("VIEW_TYPE_NAME not in", values, "viewTypeName");
            return (Criteria) this;
        }

        public Criteria andViewTypeNameBetween(String value1, String value2) {
            addCriterion("VIEW_TYPE_NAME between", value1, value2, "viewTypeName");
            return (Criteria) this;
        }

        public Criteria andViewTypeNameNotBetween(String value1, String value2) {
            addCriterion("VIEW_TYPE_NAME not between", value1, value2, "viewTypeName");
            return (Criteria) this;
        }

        public Criteria andIsRandomIsNull() {
            addCriterion("IS_RANDOM is null");
            return (Criteria) this;
        }

        public Criteria andIsRandomIsNotNull() {
            addCriterion("IS_RANDOM is not null");
            return (Criteria) this;
        }

        public Criteria andIsRandomEqualTo(String value) {
            addCriterion("IS_RANDOM =", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomNotEqualTo(String value) {
            addCriterion("IS_RANDOM <>", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomGreaterThan(String value) {
            addCriterion("IS_RANDOM >", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomGreaterThanOrEqualTo(String value) {
            addCriterion("IS_RANDOM >=", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomLessThan(String value) {
            addCriterion("IS_RANDOM <", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomLessThanOrEqualTo(String value) {
            addCriterion("IS_RANDOM <=", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomLike(String value) {
            addCriterion("IS_RANDOM like", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomNotLike(String value) {
            addCriterion("IS_RANDOM not like", value, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomIn(List<String> values) {
            addCriterion("IS_RANDOM in", values, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomNotIn(List<String> values) {
            addCriterion("IS_RANDOM not in", values, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomBetween(String value1, String value2) {
            addCriterion("IS_RANDOM between", value1, value2, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsRandomNotBetween(String value1, String value2) {
            addCriterion("IS_RANDOM not between", value1, value2, "isRandom");
            return (Criteria) this;
        }

        public Criteria andIsVisitIsNull() {
            addCriterion("IS_VISIT is null");
            return (Criteria) this;
        }

        public Criteria andIsVisitIsNotNull() {
            addCriterion("IS_VISIT is not null");
            return (Criteria) this;
        }

        public Criteria andIsVisitEqualTo(String value) {
            addCriterion("IS_VISIT =", value, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitNotEqualTo(String value) {
            addCriterion("IS_VISIT <>", value, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitGreaterThan(String value) {
            addCriterion("IS_VISIT >", value, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitGreaterThanOrEqualTo(String value) {
            addCriterion("IS_VISIT >=", value, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitLessThan(String value) {
            addCriterion("IS_VISIT <", value, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitLessThanOrEqualTo(String value) {
            addCriterion("IS_VISIT <=", value, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitLike(String value) {
            addCriterion("IS_VISIT like", value, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitNotLike(String value) {
            addCriterion("IS_VISIT not like", value, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitIn(List<String> values) {
            addCriterion("IS_VISIT in", values, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitNotIn(List<String> values) {
            addCriterion("IS_VISIT not in", values, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitBetween(String value1, String value2) {
            addCriterion("IS_VISIT between", value1, value2, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitNotBetween(String value1, String value2) {
            addCriterion("IS_VISIT not between", value1, value2, "isVisit");
            return (Criteria) this;
        }

        public Criteria andRandomTypeIdIsNull() {
            addCriterion("RANDOM_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andRandomTypeIdIsNotNull() {
            addCriterion("RANDOM_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRandomTypeIdEqualTo(String value) {
            addCriterion("RANDOM_TYPE_ID =", value, "randomTypeId");
            return (Criteria) this;
        }

        public Criteria andRandomTypeIdNotEqualTo(String value) {
            addCriterion("RANDOM_TYPE_ID <>", value, "randomTypeId");
            return (Criteria) this;
        }

        public Criteria andRandomTypeIdGreaterThan(String value) {
            addCriterion("RANDOM_TYPE_ID >", value, "randomTypeId");
            return (Criteria) this;
        }

        public Criteria andRandomTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("RANDOM_TYPE_ID >=", value, "randomTypeId");
            return (Criteria) this;
        }

        public Criteria andRandomTypeIdLessThan(String value) {
            addCriterion("RANDOM_TYPE_ID <", value, "randomTypeId");
            return (Criteria) this;
        }

        public Criteria andRandomTypeIdLessThanOrEqualTo(String value) {
            addCriterion("RANDOM_TYPE_ID <=", value, "randomTypeId");
            return (Criteria) this;
        }

        public Criteria andRandomTypeIdLike(String value) {
            addCriterion("RANDOM_TYPE_ID like", value, "randomTypeId");
            return (Criteria) this;
        }

        public Criteria andRandomTypeIdNotLike(String value) {
            addCriterion("RANDOM_TYPE_ID not like", value, "randomTypeId");
            return (Criteria) this;
        }

        public Criteria andRandomTypeIdIn(List<String> values) {
            addCriterion("RANDOM_TYPE_ID in", values, "randomTypeId");
            return (Criteria) this;
        }

        public Criteria andRandomTypeIdNotIn(List<String> values) {
            addCriterion("RANDOM_TYPE_ID not in", values, "randomTypeId");
            return (Criteria) this;
        }

        public Criteria andRandomTypeIdBetween(String value1, String value2) {
            addCriterion("RANDOM_TYPE_ID between", value1, value2, "randomTypeId");
            return (Criteria) this;
        }

        public Criteria andRandomTypeIdNotBetween(String value1, String value2) {
            addCriterion("RANDOM_TYPE_ID not between", value1, value2, "randomTypeId");
            return (Criteria) this;
        }

        public Criteria andRandomTypeNameIsNull() {
            addCriterion("RANDOM_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRandomTypeNameIsNotNull() {
            addCriterion("RANDOM_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRandomTypeNameEqualTo(String value) {
            addCriterion("RANDOM_TYPE_NAME =", value, "randomTypeName");
            return (Criteria) this;
        }

        public Criteria andRandomTypeNameNotEqualTo(String value) {
            addCriterion("RANDOM_TYPE_NAME <>", value, "randomTypeName");
            return (Criteria) this;
        }

        public Criteria andRandomTypeNameGreaterThan(String value) {
            addCriterion("RANDOM_TYPE_NAME >", value, "randomTypeName");
            return (Criteria) this;
        }

        public Criteria andRandomTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("RANDOM_TYPE_NAME >=", value, "randomTypeName");
            return (Criteria) this;
        }

        public Criteria andRandomTypeNameLessThan(String value) {
            addCriterion("RANDOM_TYPE_NAME <", value, "randomTypeName");
            return (Criteria) this;
        }

        public Criteria andRandomTypeNameLessThanOrEqualTo(String value) {
            addCriterion("RANDOM_TYPE_NAME <=", value, "randomTypeName");
            return (Criteria) this;
        }

        public Criteria andRandomTypeNameLike(String value) {
            addCriterion("RANDOM_TYPE_NAME like", value, "randomTypeName");
            return (Criteria) this;
        }

        public Criteria andRandomTypeNameNotLike(String value) {
            addCriterion("RANDOM_TYPE_NAME not like", value, "randomTypeName");
            return (Criteria) this;
        }

        public Criteria andRandomTypeNameIn(List<String> values) {
            addCriterion("RANDOM_TYPE_NAME in", values, "randomTypeName");
            return (Criteria) this;
        }

        public Criteria andRandomTypeNameNotIn(List<String> values) {
            addCriterion("RANDOM_TYPE_NAME not in", values, "randomTypeName");
            return (Criteria) this;
        }

        public Criteria andRandomTypeNameBetween(String value1, String value2) {
            addCriterion("RANDOM_TYPE_NAME between", value1, value2, "randomTypeName");
            return (Criteria) this;
        }

        public Criteria andRandomTypeNameNotBetween(String value1, String value2) {
            addCriterion("RANDOM_TYPE_NAME not between", value1, value2, "randomTypeName");
            return (Criteria) this;
        }

        public Criteria andBlindTypeIdIsNull() {
            addCriterion("BLIND_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andBlindTypeIdIsNotNull() {
            addCriterion("BLIND_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBlindTypeIdEqualTo(String value) {
            addCriterion("BLIND_TYPE_ID =", value, "blindTypeId");
            return (Criteria) this;
        }

        public Criteria andBlindTypeIdNotEqualTo(String value) {
            addCriterion("BLIND_TYPE_ID <>", value, "blindTypeId");
            return (Criteria) this;
        }

        public Criteria andBlindTypeIdGreaterThan(String value) {
            addCriterion("BLIND_TYPE_ID >", value, "blindTypeId");
            return (Criteria) this;
        }

        public Criteria andBlindTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("BLIND_TYPE_ID >=", value, "blindTypeId");
            return (Criteria) this;
        }

        public Criteria andBlindTypeIdLessThan(String value) {
            addCriterion("BLIND_TYPE_ID <", value, "blindTypeId");
            return (Criteria) this;
        }

        public Criteria andBlindTypeIdLessThanOrEqualTo(String value) {
            addCriterion("BLIND_TYPE_ID <=", value, "blindTypeId");
            return (Criteria) this;
        }

        public Criteria andBlindTypeIdLike(String value) {
            addCriterion("BLIND_TYPE_ID like", value, "blindTypeId");
            return (Criteria) this;
        }

        public Criteria andBlindTypeIdNotLike(String value) {
            addCriterion("BLIND_TYPE_ID not like", value, "blindTypeId");
            return (Criteria) this;
        }

        public Criteria andBlindTypeIdIn(List<String> values) {
            addCriterion("BLIND_TYPE_ID in", values, "blindTypeId");
            return (Criteria) this;
        }

        public Criteria andBlindTypeIdNotIn(List<String> values) {
            addCriterion("BLIND_TYPE_ID not in", values, "blindTypeId");
            return (Criteria) this;
        }

        public Criteria andBlindTypeIdBetween(String value1, String value2) {
            addCriterion("BLIND_TYPE_ID between", value1, value2, "blindTypeId");
            return (Criteria) this;
        }

        public Criteria andBlindTypeIdNotBetween(String value1, String value2) {
            addCriterion("BLIND_TYPE_ID not between", value1, value2, "blindTypeId");
            return (Criteria) this;
        }

        public Criteria andBlindTypeNameIsNull() {
            addCriterion("BLIND_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBlindTypeNameIsNotNull() {
            addCriterion("BLIND_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBlindTypeNameEqualTo(String value) {
            addCriterion("BLIND_TYPE_NAME =", value, "blindTypeName");
            return (Criteria) this;
        }

        public Criteria andBlindTypeNameNotEqualTo(String value) {
            addCriterion("BLIND_TYPE_NAME <>", value, "blindTypeName");
            return (Criteria) this;
        }

        public Criteria andBlindTypeNameGreaterThan(String value) {
            addCriterion("BLIND_TYPE_NAME >", value, "blindTypeName");
            return (Criteria) this;
        }

        public Criteria andBlindTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("BLIND_TYPE_NAME >=", value, "blindTypeName");
            return (Criteria) this;
        }

        public Criteria andBlindTypeNameLessThan(String value) {
            addCriterion("BLIND_TYPE_NAME <", value, "blindTypeName");
            return (Criteria) this;
        }

        public Criteria andBlindTypeNameLessThanOrEqualTo(String value) {
            addCriterion("BLIND_TYPE_NAME <=", value, "blindTypeName");
            return (Criteria) this;
        }

        public Criteria andBlindTypeNameLike(String value) {
            addCriterion("BLIND_TYPE_NAME like", value, "blindTypeName");
            return (Criteria) this;
        }

        public Criteria andBlindTypeNameNotLike(String value) {
            addCriterion("BLIND_TYPE_NAME not like", value, "blindTypeName");
            return (Criteria) this;
        }

        public Criteria andBlindTypeNameIn(List<String> values) {
            addCriterion("BLIND_TYPE_NAME in", values, "blindTypeName");
            return (Criteria) this;
        }

        public Criteria andBlindTypeNameNotIn(List<String> values) {
            addCriterion("BLIND_TYPE_NAME not in", values, "blindTypeName");
            return (Criteria) this;
        }

        public Criteria andBlindTypeNameBetween(String value1, String value2) {
            addCriterion("BLIND_TYPE_NAME between", value1, value2, "blindTypeName");
            return (Criteria) this;
        }

        public Criteria andBlindTypeNameNotBetween(String value1, String value2) {
            addCriterion("BLIND_TYPE_NAME not between", value1, value2, "blindTypeName");
            return (Criteria) this;
        }

        public Criteria andRandomFileFlowIsNull() {
            addCriterion("RANDOM_FILE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRandomFileFlowIsNotNull() {
            addCriterion("RANDOM_FILE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRandomFileFlowEqualTo(String value) {
            addCriterion("RANDOM_FILE_FLOW =", value, "randomFileFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFileFlowNotEqualTo(String value) {
            addCriterion("RANDOM_FILE_FLOW <>", value, "randomFileFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFileFlowGreaterThan(String value) {
            addCriterion("RANDOM_FILE_FLOW >", value, "randomFileFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFileFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RANDOM_FILE_FLOW >=", value, "randomFileFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFileFlowLessThan(String value) {
            addCriterion("RANDOM_FILE_FLOW <", value, "randomFileFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFileFlowLessThanOrEqualTo(String value) {
            addCriterion("RANDOM_FILE_FLOW <=", value, "randomFileFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFileFlowLike(String value) {
            addCriterion("RANDOM_FILE_FLOW like", value, "randomFileFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFileFlowNotLike(String value) {
            addCriterion("RANDOM_FILE_FLOW not like", value, "randomFileFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFileFlowIn(List<String> values) {
            addCriterion("RANDOM_FILE_FLOW in", values, "randomFileFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFileFlowNotIn(List<String> values) {
            addCriterion("RANDOM_FILE_FLOW not in", values, "randomFileFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFileFlowBetween(String value1, String value2) {
            addCriterion("RANDOM_FILE_FLOW between", value1, value2, "randomFileFlow");
            return (Criteria) this;
        }

        public Criteria andRandomFileFlowNotBetween(String value1, String value2) {
            addCriterion("RANDOM_FILE_FLOW not between", value1, value2, "randomFileFlow");
            return (Criteria) this;
        }

        public Criteria andProjLockIsNull() {
            addCriterion("PROJ_LOCK is null");
            return (Criteria) this;
        }

        public Criteria andProjLockIsNotNull() {
            addCriterion("PROJ_LOCK is not null");
            return (Criteria) this;
        }

        public Criteria andProjLockEqualTo(String value) {
            addCriterion("PROJ_LOCK =", value, "projLock");
            return (Criteria) this;
        }

        public Criteria andProjLockNotEqualTo(String value) {
            addCriterion("PROJ_LOCK <>", value, "projLock");
            return (Criteria) this;
        }

        public Criteria andProjLockGreaterThan(String value) {
            addCriterion("PROJ_LOCK >", value, "projLock");
            return (Criteria) this;
        }

        public Criteria andProjLockGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_LOCK >=", value, "projLock");
            return (Criteria) this;
        }

        public Criteria andProjLockLessThan(String value) {
            addCriterion("PROJ_LOCK <", value, "projLock");
            return (Criteria) this;
        }

        public Criteria andProjLockLessThanOrEqualTo(String value) {
            addCriterion("PROJ_LOCK <=", value, "projLock");
            return (Criteria) this;
        }

        public Criteria andProjLockLike(String value) {
            addCriterion("PROJ_LOCK like", value, "projLock");
            return (Criteria) this;
        }

        public Criteria andProjLockNotLike(String value) {
            addCriterion("PROJ_LOCK not like", value, "projLock");
            return (Criteria) this;
        }

        public Criteria andProjLockIn(List<String> values) {
            addCriterion("PROJ_LOCK in", values, "projLock");
            return (Criteria) this;
        }

        public Criteria andProjLockNotIn(List<String> values) {
            addCriterion("PROJ_LOCK not in", values, "projLock");
            return (Criteria) this;
        }

        public Criteria andProjLockBetween(String value1, String value2) {
            addCriterion("PROJ_LOCK between", value1, value2, "projLock");
            return (Criteria) this;
        }

        public Criteria andProjLockNotBetween(String value1, String value2) {
            addCriterion("PROJ_LOCK not between", value1, value2, "projLock");
            return (Criteria) this;
        }

        public Criteria andDesignLockIsNull() {
            addCriterion("DESIGN_LOCK is null");
            return (Criteria) this;
        }

        public Criteria andDesignLockIsNotNull() {
            addCriterion("DESIGN_LOCK is not null");
            return (Criteria) this;
        }

        public Criteria andDesignLockEqualTo(String value) {
            addCriterion("DESIGN_LOCK =", value, "designLock");
            return (Criteria) this;
        }

        public Criteria andDesignLockNotEqualTo(String value) {
            addCriterion("DESIGN_LOCK <>", value, "designLock");
            return (Criteria) this;
        }

        public Criteria andDesignLockGreaterThan(String value) {
            addCriterion("DESIGN_LOCK >", value, "designLock");
            return (Criteria) this;
        }

        public Criteria andDesignLockGreaterThanOrEqualTo(String value) {
            addCriterion("DESIGN_LOCK >=", value, "designLock");
            return (Criteria) this;
        }

        public Criteria andDesignLockLessThan(String value) {
            addCriterion("DESIGN_LOCK <", value, "designLock");
            return (Criteria) this;
        }

        public Criteria andDesignLockLessThanOrEqualTo(String value) {
            addCriterion("DESIGN_LOCK <=", value, "designLock");
            return (Criteria) this;
        }

        public Criteria andDesignLockLike(String value) {
            addCriterion("DESIGN_LOCK like", value, "designLock");
            return (Criteria) this;
        }

        public Criteria andDesignLockNotLike(String value) {
            addCriterion("DESIGN_LOCK not like", value, "designLock");
            return (Criteria) this;
        }

        public Criteria andDesignLockIn(List<String> values) {
            addCriterion("DESIGN_LOCK in", values, "designLock");
            return (Criteria) this;
        }

        public Criteria andDesignLockNotIn(List<String> values) {
            addCriterion("DESIGN_LOCK not in", values, "designLock");
            return (Criteria) this;
        }

        public Criteria andDesignLockBetween(String value1, String value2) {
            addCriterion("DESIGN_LOCK between", value1, value2, "designLock");
            return (Criteria) this;
        }

        public Criteria andDesignLockNotBetween(String value1, String value2) {
            addCriterion("DESIGN_LOCK not between", value1, value2, "designLock");
            return (Criteria) this;
        }

        public Criteria andRandomLockIsNull() {
            addCriterion("RANDOM_LOCK is null");
            return (Criteria) this;
        }

        public Criteria andRandomLockIsNotNull() {
            addCriterion("RANDOM_LOCK is not null");
            return (Criteria) this;
        }

        public Criteria andRandomLockEqualTo(String value) {
            addCriterion("RANDOM_LOCK =", value, "randomLock");
            return (Criteria) this;
        }

        public Criteria andRandomLockNotEqualTo(String value) {
            addCriterion("RANDOM_LOCK <>", value, "randomLock");
            return (Criteria) this;
        }

        public Criteria andRandomLockGreaterThan(String value) {
            addCriterion("RANDOM_LOCK >", value, "randomLock");
            return (Criteria) this;
        }

        public Criteria andRandomLockGreaterThanOrEqualTo(String value) {
            addCriterion("RANDOM_LOCK >=", value, "randomLock");
            return (Criteria) this;
        }

        public Criteria andRandomLockLessThan(String value) {
            addCriterion("RANDOM_LOCK <", value, "randomLock");
            return (Criteria) this;
        }

        public Criteria andRandomLockLessThanOrEqualTo(String value) {
            addCriterion("RANDOM_LOCK <=", value, "randomLock");
            return (Criteria) this;
        }

        public Criteria andRandomLockLike(String value) {
            addCriterion("RANDOM_LOCK like", value, "randomLock");
            return (Criteria) this;
        }

        public Criteria andRandomLockNotLike(String value) {
            addCriterion("RANDOM_LOCK not like", value, "randomLock");
            return (Criteria) this;
        }

        public Criteria andRandomLockIn(List<String> values) {
            addCriterion("RANDOM_LOCK in", values, "randomLock");
            return (Criteria) this;
        }

        public Criteria andRandomLockNotIn(List<String> values) {
            addCriterion("RANDOM_LOCK not in", values, "randomLock");
            return (Criteria) this;
        }

        public Criteria andRandomLockBetween(String value1, String value2) {
            addCriterion("RANDOM_LOCK between", value1, value2, "randomLock");
            return (Criteria) this;
        }

        public Criteria andRandomLockNotBetween(String value1, String value2) {
            addCriterion("RANDOM_LOCK not between", value1, value2, "randomLock");
            return (Criteria) this;
        }

        public Criteria andInputLockIsNull() {
            addCriterion("INPUT_LOCK is null");
            return (Criteria) this;
        }

        public Criteria andInputLockIsNotNull() {
            addCriterion("INPUT_LOCK is not null");
            return (Criteria) this;
        }

        public Criteria andInputLockEqualTo(String value) {
            addCriterion("INPUT_LOCK =", value, "inputLock");
            return (Criteria) this;
        }

        public Criteria andInputLockNotEqualTo(String value) {
            addCriterion("INPUT_LOCK <>", value, "inputLock");
            return (Criteria) this;
        }

        public Criteria andInputLockGreaterThan(String value) {
            addCriterion("INPUT_LOCK >", value, "inputLock");
            return (Criteria) this;
        }

        public Criteria andInputLockGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_LOCK >=", value, "inputLock");
            return (Criteria) this;
        }

        public Criteria andInputLockLessThan(String value) {
            addCriterion("INPUT_LOCK <", value, "inputLock");
            return (Criteria) this;
        }

        public Criteria andInputLockLessThanOrEqualTo(String value) {
            addCriterion("INPUT_LOCK <=", value, "inputLock");
            return (Criteria) this;
        }

        public Criteria andInputLockLike(String value) {
            addCriterion("INPUT_LOCK like", value, "inputLock");
            return (Criteria) this;
        }

        public Criteria andInputLockNotLike(String value) {
            addCriterion("INPUT_LOCK not like", value, "inputLock");
            return (Criteria) this;
        }

        public Criteria andInputLockIn(List<String> values) {
            addCriterion("INPUT_LOCK in", values, "inputLock");
            return (Criteria) this;
        }

        public Criteria andInputLockNotIn(List<String> values) {
            addCriterion("INPUT_LOCK not in", values, "inputLock");
            return (Criteria) this;
        }

        public Criteria andInputLockBetween(String value1, String value2) {
            addCriterion("INPUT_LOCK between", value1, value2, "inputLock");
            return (Criteria) this;
        }

        public Criteria andInputLockNotBetween(String value1, String value2) {
            addCriterion("INPUT_LOCK not between", value1, value2, "inputLock");
            return (Criteria) this;
        }

        public Criteria andInspectLockIsNull() {
            addCriterion("INSPECT_LOCK is null");
            return (Criteria) this;
        }

        public Criteria andInspectLockIsNotNull() {
            addCriterion("INSPECT_LOCK is not null");
            return (Criteria) this;
        }

        public Criteria andInspectLockEqualTo(String value) {
            addCriterion("INSPECT_LOCK =", value, "inspectLock");
            return (Criteria) this;
        }

        public Criteria andInspectLockNotEqualTo(String value) {
            addCriterion("INSPECT_LOCK <>", value, "inspectLock");
            return (Criteria) this;
        }

        public Criteria andInspectLockGreaterThan(String value) {
            addCriterion("INSPECT_LOCK >", value, "inspectLock");
            return (Criteria) this;
        }

        public Criteria andInspectLockGreaterThanOrEqualTo(String value) {
            addCriterion("INSPECT_LOCK >=", value, "inspectLock");
            return (Criteria) this;
        }

        public Criteria andInspectLockLessThan(String value) {
            addCriterion("INSPECT_LOCK <", value, "inspectLock");
            return (Criteria) this;
        }

        public Criteria andInspectLockLessThanOrEqualTo(String value) {
            addCriterion("INSPECT_LOCK <=", value, "inspectLock");
            return (Criteria) this;
        }

        public Criteria andInspectLockLike(String value) {
            addCriterion("INSPECT_LOCK like", value, "inspectLock");
            return (Criteria) this;
        }

        public Criteria andInspectLockNotLike(String value) {
            addCriterion("INSPECT_LOCK not like", value, "inspectLock");
            return (Criteria) this;
        }

        public Criteria andInspectLockIn(List<String> values) {
            addCriterion("INSPECT_LOCK in", values, "inspectLock");
            return (Criteria) this;
        }

        public Criteria andInspectLockNotIn(List<String> values) {
            addCriterion("INSPECT_LOCK not in", values, "inspectLock");
            return (Criteria) this;
        }

        public Criteria andInspectLockBetween(String value1, String value2) {
            addCriterion("INSPECT_LOCK between", value1, value2, "inspectLock");
            return (Criteria) this;
        }

        public Criteria andInspectLockNotBetween(String value1, String value2) {
            addCriterion("INSPECT_LOCK not between", value1, value2, "inspectLock");
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