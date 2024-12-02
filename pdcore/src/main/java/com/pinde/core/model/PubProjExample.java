package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class PubProjExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PubProjExample() {
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

        public Criteria andProjYearIsNull() {
            addCriterion("PROJ_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andProjYearIsNotNull() {
            addCriterion("PROJ_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andProjYearEqualTo(String value) {
            addCriterion("PROJ_YEAR =", value, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearNotEqualTo(String value) {
            addCriterion("PROJ_YEAR <>", value, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearGreaterThan(String value) {
            addCriterion("PROJ_YEAR >", value, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_YEAR >=", value, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearLessThan(String value) {
            addCriterion("PROJ_YEAR <", value, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearLessThanOrEqualTo(String value) {
            addCriterion("PROJ_YEAR <=", value, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearLike(String value) {
            addCriterion("PROJ_YEAR like", value, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearNotLike(String value) {
            addCriterion("PROJ_YEAR not like", value, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearIn(List<String> values) {
            addCriterion("PROJ_YEAR in", values, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearNotIn(List<String> values) {
            addCriterion("PROJ_YEAR not in", values, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearBetween(String value1, String value2) {
            addCriterion("PROJ_YEAR between", value1, value2, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearNotBetween(String value1, String value2) {
            addCriterion("PROJ_YEAR not between", value1, value2, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjNameIsNull() {
            addCriterion("PROJ_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjNameIsNotNull() {
            addCriterion("PROJ_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjNameEqualTo(String value) {
            addCriterion("PROJ_NAME =", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotEqualTo(String value) {
            addCriterion("PROJ_NAME <>", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameGreaterThan(String value) {
            addCriterion("PROJ_NAME >", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_NAME >=", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameLessThan(String value) {
            addCriterion("PROJ_NAME <", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_NAME <=", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameLike(String value) {
            addCriterion("PROJ_NAME like", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotLike(String value) {
            addCriterion("PROJ_NAME not like", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameIn(List<String> values) {
            addCriterion("PROJ_NAME in", values, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotIn(List<String> values) {
            addCriterion("PROJ_NAME not in", values, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameBetween(String value1, String value2) {
            addCriterion("PROJ_NAME between", value1, value2, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_NAME not between", value1, value2, "projName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameIsNull() {
            addCriterion("PROJ_SHORT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjShortNameIsNotNull() {
            addCriterion("PROJ_SHORT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjShortNameEqualTo(String value) {
            addCriterion("PROJ_SHORT_NAME =", value, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameNotEqualTo(String value) {
            addCriterion("PROJ_SHORT_NAME <>", value, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameGreaterThan(String value) {
            addCriterion("PROJ_SHORT_NAME >", value, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SHORT_NAME >=", value, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameLessThan(String value) {
            addCriterion("PROJ_SHORT_NAME <", value, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SHORT_NAME <=", value, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameLike(String value) {
            addCriterion("PROJ_SHORT_NAME like", value, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameNotLike(String value) {
            addCriterion("PROJ_SHORT_NAME not like", value, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameIn(List<String> values) {
            addCriterion("PROJ_SHORT_NAME in", values, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameNotIn(List<String> values) {
            addCriterion("PROJ_SHORT_NAME not in", values, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameBetween(String value1, String value2) {
            addCriterion("PROJ_SHORT_NAME between", value1, value2, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjShortNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_SHORT_NAME not between", value1, value2, "projShortName");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerIsNull() {
            addCriterion("PROJ_DECLARER is null");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerIsNotNull() {
            addCriterion("PROJ_DECLARER is not null");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerEqualTo(String value) {
            addCriterion("PROJ_DECLARER =", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerNotEqualTo(String value) {
            addCriterion("PROJ_DECLARER <>", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerGreaterThan(String value) {
            addCriterion("PROJ_DECLARER >", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_DECLARER >=", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerLessThan(String value) {
            addCriterion("PROJ_DECLARER <", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerLessThanOrEqualTo(String value) {
            addCriterion("PROJ_DECLARER <=", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerLike(String value) {
            addCriterion("PROJ_DECLARER like", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerNotLike(String value) {
            addCriterion("PROJ_DECLARER not like", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerIn(List<String> values) {
            addCriterion("PROJ_DECLARER in", values, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerNotIn(List<String> values) {
            addCriterion("PROJ_DECLARER not in", values, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerBetween(String value1, String value2) {
            addCriterion("PROJ_DECLARER between", value1, value2, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerNotBetween(String value1, String value2) {
            addCriterion("PROJ_DECLARER not between", value1, value2, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerIsNull() {
            addCriterion("PROJ_SHORT_DECLARER is null");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerIsNotNull() {
            addCriterion("PROJ_SHORT_DECLARER is not null");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerEqualTo(String value) {
            addCriterion("PROJ_SHORT_DECLARER =", value, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerNotEqualTo(String value) {
            addCriterion("PROJ_SHORT_DECLARER <>", value, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerGreaterThan(String value) {
            addCriterion("PROJ_SHORT_DECLARER >", value, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SHORT_DECLARER >=", value, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerLessThan(String value) {
            addCriterion("PROJ_SHORT_DECLARER <", value, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SHORT_DECLARER <=", value, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerLike(String value) {
            addCriterion("PROJ_SHORT_DECLARER like", value, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerNotLike(String value) {
            addCriterion("PROJ_SHORT_DECLARER not like", value, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerIn(List<String> values) {
            addCriterion("PROJ_SHORT_DECLARER in", values, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerNotIn(List<String> values) {
            addCriterion("PROJ_SHORT_DECLARER not in", values, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerBetween(String value1, String value2) {
            addCriterion("PROJ_SHORT_DECLARER between", value1, value2, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjShortDeclarerNotBetween(String value1, String value2) {
            addCriterion("PROJ_SHORT_DECLARER not between", value1, value2, "projShortDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerFlowIsNull() {
            addCriterion("PROJ_DECLARER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerFlowIsNotNull() {
            addCriterion("PROJ_DECLARER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerFlowEqualTo(String value) {
            addCriterion("PROJ_DECLARER_FLOW =", value, "projDeclarerFlow");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerFlowNotEqualTo(String value) {
            addCriterion("PROJ_DECLARER_FLOW <>", value, "projDeclarerFlow");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerFlowGreaterThan(String value) {
            addCriterion("PROJ_DECLARER_FLOW >", value, "projDeclarerFlow");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_DECLARER_FLOW >=", value, "projDeclarerFlow");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerFlowLessThan(String value) {
            addCriterion("PROJ_DECLARER_FLOW <", value, "projDeclarerFlow");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerFlowLessThanOrEqualTo(String value) {
            addCriterion("PROJ_DECLARER_FLOW <=", value, "projDeclarerFlow");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerFlowLike(String value) {
            addCriterion("PROJ_DECLARER_FLOW like", value, "projDeclarerFlow");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerFlowNotLike(String value) {
            addCriterion("PROJ_DECLARER_FLOW not like", value, "projDeclarerFlow");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerFlowIn(List<String> values) {
            addCriterion("PROJ_DECLARER_FLOW in", values, "projDeclarerFlow");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerFlowNotIn(List<String> values) {
            addCriterion("PROJ_DECLARER_FLOW not in", values, "projDeclarerFlow");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerFlowBetween(String value1, String value2) {
            addCriterion("PROJ_DECLARER_FLOW between", value1, value2, "projDeclarerFlow");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerFlowNotBetween(String value1, String value2) {
            addCriterion("PROJ_DECLARER_FLOW not between", value1, value2, "projDeclarerFlow");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdIsNull() {
            addCriterion("PROJ_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdIsNotNull() {
            addCriterion("PROJ_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdEqualTo(String value) {
            addCriterion("PROJ_CATEGORY_ID =", value, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdNotEqualTo(String value) {
            addCriterion("PROJ_CATEGORY_ID <>", value, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdGreaterThan(String value) {
            addCriterion("PROJ_CATEGORY_ID >", value, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_CATEGORY_ID >=", value, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdLessThan(String value) {
            addCriterion("PROJ_CATEGORY_ID <", value, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("PROJ_CATEGORY_ID <=", value, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdLike(String value) {
            addCriterion("PROJ_CATEGORY_ID like", value, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdNotLike(String value) {
            addCriterion("PROJ_CATEGORY_ID not like", value, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdIn(List<String> values) {
            addCriterion("PROJ_CATEGORY_ID in", values, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdNotIn(List<String> values) {
            addCriterion("PROJ_CATEGORY_ID not in", values, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdBetween(String value1, String value2) {
            addCriterion("PROJ_CATEGORY_ID between", value1, value2, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdNotBetween(String value1, String value2) {
            addCriterion("PROJ_CATEGORY_ID not between", value1, value2, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameIsNull() {
            addCriterion("PROJ_CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameIsNotNull() {
            addCriterion("PROJ_CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameEqualTo(String value) {
            addCriterion("PROJ_CATEGORY_NAME =", value, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameNotEqualTo(String value) {
            addCriterion("PROJ_CATEGORY_NAME <>", value, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameGreaterThan(String value) {
            addCriterion("PROJ_CATEGORY_NAME >", value, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_CATEGORY_NAME >=", value, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameLessThan(String value) {
            addCriterion("PROJ_CATEGORY_NAME <", value, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_CATEGORY_NAME <=", value, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameLike(String value) {
            addCriterion("PROJ_CATEGORY_NAME like", value, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameNotLike(String value) {
            addCriterion("PROJ_CATEGORY_NAME not like", value, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameIn(List<String> values) {
            addCriterion("PROJ_CATEGORY_NAME in", values, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameNotIn(List<String> values) {
            addCriterion("PROJ_CATEGORY_NAME not in", values, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameBetween(String value1, String value2) {
            addCriterion("PROJ_CATEGORY_NAME between", value1, value2, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_CATEGORY_NAME not between", value1, value2, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjNoIsNull() {
            addCriterion("PROJ_NO is null");
            return (Criteria) this;
        }

        public Criteria andProjNoIsNotNull() {
            addCriterion("PROJ_NO is not null");
            return (Criteria) this;
        }

        public Criteria andProjNoEqualTo(String value) {
            addCriterion("PROJ_NO =", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoNotEqualTo(String value) {
            addCriterion("PROJ_NO <>", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoGreaterThan(String value) {
            addCriterion("PROJ_NO >", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_NO >=", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoLessThan(String value) {
            addCriterion("PROJ_NO <", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoLessThanOrEqualTo(String value) {
            addCriterion("PROJ_NO <=", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoLike(String value) {
            addCriterion("PROJ_NO like", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoNotLike(String value) {
            addCriterion("PROJ_NO not like", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoIn(List<String> values) {
            addCriterion("PROJ_NO in", values, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoNotIn(List<String> values) {
            addCriterion("PROJ_NO not in", values, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoBetween(String value1, String value2) {
            addCriterion("PROJ_NO between", value1, value2, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoNotBetween(String value1, String value2) {
            addCriterion("PROJ_NO not between", value1, value2, "projNo");
            return (Criteria) this;
        }

        public Criteria andCfdaNoIsNull() {
            addCriterion("CFDA_NO is null");
            return (Criteria) this;
        }

        public Criteria andCfdaNoIsNotNull() {
            addCriterion("CFDA_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCfdaNoEqualTo(String value) {
            addCriterion("CFDA_NO =", value, "cfdaNo");
            return (Criteria) this;
        }

        public Criteria andCfdaNoNotEqualTo(String value) {
            addCriterion("CFDA_NO <>", value, "cfdaNo");
            return (Criteria) this;
        }

        public Criteria andCfdaNoGreaterThan(String value) {
            addCriterion("CFDA_NO >", value, "cfdaNo");
            return (Criteria) this;
        }

        public Criteria andCfdaNoGreaterThanOrEqualTo(String value) {
            addCriterion("CFDA_NO >=", value, "cfdaNo");
            return (Criteria) this;
        }

        public Criteria andCfdaNoLessThan(String value) {
            addCriterion("CFDA_NO <", value, "cfdaNo");
            return (Criteria) this;
        }

        public Criteria andCfdaNoLessThanOrEqualTo(String value) {
            addCriterion("CFDA_NO <=", value, "cfdaNo");
            return (Criteria) this;
        }

        public Criteria andCfdaNoLike(String value) {
            addCriterion("CFDA_NO like", value, "cfdaNo");
            return (Criteria) this;
        }

        public Criteria andCfdaNoNotLike(String value) {
            addCriterion("CFDA_NO not like", value, "cfdaNo");
            return (Criteria) this;
        }

        public Criteria andCfdaNoIn(List<String> values) {
            addCriterion("CFDA_NO in", values, "cfdaNo");
            return (Criteria) this;
        }

        public Criteria andCfdaNoNotIn(List<String> values) {
            addCriterion("CFDA_NO not in", values, "cfdaNo");
            return (Criteria) this;
        }

        public Criteria andCfdaNoBetween(String value1, String value2) {
            addCriterion("CFDA_NO between", value1, value2, "cfdaNo");
            return (Criteria) this;
        }

        public Criteria andCfdaNoNotBetween(String value1, String value2) {
            addCriterion("CFDA_NO not between", value1, value2, "cfdaNo");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdIsNull() {
            addCriterion("PROJ_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdIsNotNull() {
            addCriterion("PROJ_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdEqualTo(String value) {
            addCriterion("PROJ_TYPE_ID =", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdNotEqualTo(String value) {
            addCriterion("PROJ_TYPE_ID <>", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdGreaterThan(String value) {
            addCriterion("PROJ_TYPE_ID >", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_TYPE_ID >=", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdLessThan(String value) {
            addCriterion("PROJ_TYPE_ID <", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdLessThanOrEqualTo(String value) {
            addCriterion("PROJ_TYPE_ID <=", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdLike(String value) {
            addCriterion("PROJ_TYPE_ID like", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdNotLike(String value) {
            addCriterion("PROJ_TYPE_ID not like", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdIn(List<String> values) {
            addCriterion("PROJ_TYPE_ID in", values, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdNotIn(List<String> values) {
            addCriterion("PROJ_TYPE_ID not in", values, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdBetween(String value1, String value2) {
            addCriterion("PROJ_TYPE_ID between", value1, value2, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdNotBetween(String value1, String value2) {
            addCriterion("PROJ_TYPE_ID not between", value1, value2, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameIsNull() {
            addCriterion("PROJ_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameIsNotNull() {
            addCriterion("PROJ_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameEqualTo(String value) {
            addCriterion("PROJ_TYPE_NAME =", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameNotEqualTo(String value) {
            addCriterion("PROJ_TYPE_NAME <>", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameGreaterThan(String value) {
            addCriterion("PROJ_TYPE_NAME >", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_TYPE_NAME >=", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameLessThan(String value) {
            addCriterion("PROJ_TYPE_NAME <", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_TYPE_NAME <=", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameLike(String value) {
            addCriterion("PROJ_TYPE_NAME like", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameNotLike(String value) {
            addCriterion("PROJ_TYPE_NAME not like", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameIn(List<String> values) {
            addCriterion("PROJ_TYPE_NAME in", values, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameNotIn(List<String> values) {
            addCriterion("PROJ_TYPE_NAME not in", values, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameBetween(String value1, String value2) {
            addCriterion("PROJ_TYPE_NAME between", value1, value2, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_TYPE_NAME not between", value1, value2, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdIsNull() {
            addCriterion("PROJ_SUB_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdIsNotNull() {
            addCriterion("PROJ_SUB_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdEqualTo(String value) {
            addCriterion("PROJ_SUB_TYPE_ID =", value, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdNotEqualTo(String value) {
            addCriterion("PROJ_SUB_TYPE_ID <>", value, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdGreaterThan(String value) {
            addCriterion("PROJ_SUB_TYPE_ID >", value, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SUB_TYPE_ID >=", value, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdLessThan(String value) {
            addCriterion("PROJ_SUB_TYPE_ID <", value, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SUB_TYPE_ID <=", value, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdLike(String value) {
            addCriterion("PROJ_SUB_TYPE_ID like", value, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdNotLike(String value) {
            addCriterion("PROJ_SUB_TYPE_ID not like", value, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdIn(List<String> values) {
            addCriterion("PROJ_SUB_TYPE_ID in", values, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdNotIn(List<String> values) {
            addCriterion("PROJ_SUB_TYPE_ID not in", values, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdBetween(String value1, String value2) {
            addCriterion("PROJ_SUB_TYPE_ID between", value1, value2, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeIdNotBetween(String value1, String value2) {
            addCriterion("PROJ_SUB_TYPE_ID not between", value1, value2, "projSubTypeId");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameIsNull() {
            addCriterion("PROJ_SUB_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameIsNotNull() {
            addCriterion("PROJ_SUB_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameEqualTo(String value) {
            addCriterion("PROJ_SUB_TYPE_NAME =", value, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameNotEqualTo(String value) {
            addCriterion("PROJ_SUB_TYPE_NAME <>", value, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameGreaterThan(String value) {
            addCriterion("PROJ_SUB_TYPE_NAME >", value, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SUB_TYPE_NAME >=", value, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameLessThan(String value) {
            addCriterion("PROJ_SUB_TYPE_NAME <", value, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SUB_TYPE_NAME <=", value, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameLike(String value) {
            addCriterion("PROJ_SUB_TYPE_NAME like", value, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameNotLike(String value) {
            addCriterion("PROJ_SUB_TYPE_NAME not like", value, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameIn(List<String> values) {
            addCriterion("PROJ_SUB_TYPE_NAME in", values, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameNotIn(List<String> values) {
            addCriterion("PROJ_SUB_TYPE_NAME not in", values, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameBetween(String value1, String value2) {
            addCriterion("PROJ_SUB_TYPE_NAME between", value1, value2, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSubTypeNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_SUB_TYPE_NAME not between", value1, value2, "projSubTypeName");
            return (Criteria) this;
        }

        public Criteria andProjStageIdIsNull() {
            addCriterion("PROJ_STAGE_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjStageIdIsNotNull() {
            addCriterion("PROJ_STAGE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjStageIdEqualTo(String value) {
            addCriterion("PROJ_STAGE_ID =", value, "projStageId");
            return (Criteria) this;
        }

        public Criteria andProjStageIdNotEqualTo(String value) {
            addCriterion("PROJ_STAGE_ID <>", value, "projStageId");
            return (Criteria) this;
        }

        public Criteria andProjStageIdGreaterThan(String value) {
            addCriterion("PROJ_STAGE_ID >", value, "projStageId");
            return (Criteria) this;
        }

        public Criteria andProjStageIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_STAGE_ID >=", value, "projStageId");
            return (Criteria) this;
        }

        public Criteria andProjStageIdLessThan(String value) {
            addCriterion("PROJ_STAGE_ID <", value, "projStageId");
            return (Criteria) this;
        }

        public Criteria andProjStageIdLessThanOrEqualTo(String value) {
            addCriterion("PROJ_STAGE_ID <=", value, "projStageId");
            return (Criteria) this;
        }

        public Criteria andProjStageIdLike(String value) {
            addCriterion("PROJ_STAGE_ID like", value, "projStageId");
            return (Criteria) this;
        }

        public Criteria andProjStageIdNotLike(String value) {
            addCriterion("PROJ_STAGE_ID not like", value, "projStageId");
            return (Criteria) this;
        }

        public Criteria andProjStageIdIn(List<String> values) {
            addCriterion("PROJ_STAGE_ID in", values, "projStageId");
            return (Criteria) this;
        }

        public Criteria andProjStageIdNotIn(List<String> values) {
            addCriterion("PROJ_STAGE_ID not in", values, "projStageId");
            return (Criteria) this;
        }

        public Criteria andProjStageIdBetween(String value1, String value2) {
            addCriterion("PROJ_STAGE_ID between", value1, value2, "projStageId");
            return (Criteria) this;
        }

        public Criteria andProjStageIdNotBetween(String value1, String value2) {
            addCriterion("PROJ_STAGE_ID not between", value1, value2, "projStageId");
            return (Criteria) this;
        }

        public Criteria andProjStageNameIsNull() {
            addCriterion("PROJ_STAGE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjStageNameIsNotNull() {
            addCriterion("PROJ_STAGE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjStageNameEqualTo(String value) {
            addCriterion("PROJ_STAGE_NAME =", value, "projStageName");
            return (Criteria) this;
        }

        public Criteria andProjStageNameNotEqualTo(String value) {
            addCriterion("PROJ_STAGE_NAME <>", value, "projStageName");
            return (Criteria) this;
        }

        public Criteria andProjStageNameGreaterThan(String value) {
            addCriterion("PROJ_STAGE_NAME >", value, "projStageName");
            return (Criteria) this;
        }

        public Criteria andProjStageNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_STAGE_NAME >=", value, "projStageName");
            return (Criteria) this;
        }

        public Criteria andProjStageNameLessThan(String value) {
            addCriterion("PROJ_STAGE_NAME <", value, "projStageName");
            return (Criteria) this;
        }

        public Criteria andProjStageNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_STAGE_NAME <=", value, "projStageName");
            return (Criteria) this;
        }

        public Criteria andProjStageNameLike(String value) {
            addCriterion("PROJ_STAGE_NAME like", value, "projStageName");
            return (Criteria) this;
        }

        public Criteria andProjStageNameNotLike(String value) {
            addCriterion("PROJ_STAGE_NAME not like", value, "projStageName");
            return (Criteria) this;
        }

        public Criteria andProjStageNameIn(List<String> values) {
            addCriterion("PROJ_STAGE_NAME in", values, "projStageName");
            return (Criteria) this;
        }

        public Criteria andProjStageNameNotIn(List<String> values) {
            addCriterion("PROJ_STAGE_NAME not in", values, "projStageName");
            return (Criteria) this;
        }

        public Criteria andProjStageNameBetween(String value1, String value2) {
            addCriterion("PROJ_STAGE_NAME between", value1, value2, "projStageName");
            return (Criteria) this;
        }

        public Criteria andProjStageNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_STAGE_NAME not between", value1, value2, "projStageName");
            return (Criteria) this;
        }

        public Criteria andProjStatusIdIsNull() {
            addCriterion("PROJ_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjStatusIdIsNotNull() {
            addCriterion("PROJ_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjStatusIdEqualTo(String value) {
            addCriterion("PROJ_STATUS_ID =", value, "projStatusId");
            return (Criteria) this;
        }

        public Criteria andProjStatusIdNotEqualTo(String value) {
            addCriterion("PROJ_STATUS_ID <>", value, "projStatusId");
            return (Criteria) this;
        }

        public Criteria andProjStatusIdGreaterThan(String value) {
            addCriterion("PROJ_STATUS_ID >", value, "projStatusId");
            return (Criteria) this;
        }

        public Criteria andProjStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_STATUS_ID >=", value, "projStatusId");
            return (Criteria) this;
        }

        public Criteria andProjStatusIdLessThan(String value) {
            addCriterion("PROJ_STATUS_ID <", value, "projStatusId");
            return (Criteria) this;
        }

        public Criteria andProjStatusIdLessThanOrEqualTo(String value) {
            addCriterion("PROJ_STATUS_ID <=", value, "projStatusId");
            return (Criteria) this;
        }

        public Criteria andProjStatusIdLike(String value) {
            addCriterion("PROJ_STATUS_ID like", value, "projStatusId");
            return (Criteria) this;
        }

        public Criteria andProjStatusIdNotLike(String value) {
            addCriterion("PROJ_STATUS_ID not like", value, "projStatusId");
            return (Criteria) this;
        }

        public Criteria andProjStatusIdIn(List<String> values) {
            addCriterion("PROJ_STATUS_ID in", values, "projStatusId");
            return (Criteria) this;
        }

        public Criteria andProjStatusIdNotIn(List<String> values) {
            addCriterion("PROJ_STATUS_ID not in", values, "projStatusId");
            return (Criteria) this;
        }

        public Criteria andProjStatusIdBetween(String value1, String value2) {
            addCriterion("PROJ_STATUS_ID between", value1, value2, "projStatusId");
            return (Criteria) this;
        }

        public Criteria andProjStatusIdNotBetween(String value1, String value2) {
            addCriterion("PROJ_STATUS_ID not between", value1, value2, "projStatusId");
            return (Criteria) this;
        }

        public Criteria andProjStatusNameIsNull() {
            addCriterion("PROJ_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjStatusNameIsNotNull() {
            addCriterion("PROJ_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjStatusNameEqualTo(String value) {
            addCriterion("PROJ_STATUS_NAME =", value, "projStatusName");
            return (Criteria) this;
        }

        public Criteria andProjStatusNameNotEqualTo(String value) {
            addCriterion("PROJ_STATUS_NAME <>", value, "projStatusName");
            return (Criteria) this;
        }

        public Criteria andProjStatusNameGreaterThan(String value) {
            addCriterion("PROJ_STATUS_NAME >", value, "projStatusName");
            return (Criteria) this;
        }

        public Criteria andProjStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_STATUS_NAME >=", value, "projStatusName");
            return (Criteria) this;
        }

        public Criteria andProjStatusNameLessThan(String value) {
            addCriterion("PROJ_STATUS_NAME <", value, "projStatusName");
            return (Criteria) this;
        }

        public Criteria andProjStatusNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_STATUS_NAME <=", value, "projStatusName");
            return (Criteria) this;
        }

        public Criteria andProjStatusNameLike(String value) {
            addCriterion("PROJ_STATUS_NAME like", value, "projStatusName");
            return (Criteria) this;
        }

        public Criteria andProjStatusNameNotLike(String value) {
            addCriterion("PROJ_STATUS_NAME not like", value, "projStatusName");
            return (Criteria) this;
        }

        public Criteria andProjStatusNameIn(List<String> values) {
            addCriterion("PROJ_STATUS_NAME in", values, "projStatusName");
            return (Criteria) this;
        }

        public Criteria andProjStatusNameNotIn(List<String> values) {
            addCriterion("PROJ_STATUS_NAME not in", values, "projStatusName");
            return (Criteria) this;
        }

        public Criteria andProjStatusNameBetween(String value1, String value2) {
            addCriterion("PROJ_STATUS_NAME between", value1, value2, "projStatusName");
            return (Criteria) this;
        }

        public Criteria andProjStatusNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_STATUS_NAME not between", value1, value2, "projStatusName");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIsNull() {
            addCriterion("APPLY_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIsNotNull() {
            addCriterion("APPLY_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW =", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW <>", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowGreaterThan(String value) {
            addCriterion("APPLY_USER_FLOW >", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW >=", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLessThan(String value) {
            addCriterion("APPLY_USER_FLOW <", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW <=", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLike(String value) {
            addCriterion("APPLY_USER_FLOW like", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotLike(String value) {
            addCriterion("APPLY_USER_FLOW not like", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIn(List<String> values) {
            addCriterion("APPLY_USER_FLOW in", values, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotIn(List<String> values) {
            addCriterion("APPLY_USER_FLOW not in", values, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowBetween(String value1, String value2) {
            addCriterion("APPLY_USER_FLOW between", value1, value2, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_FLOW not between", value1, value2, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIsNull() {
            addCriterion("APPLY_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIsNotNull() {
            addCriterion("APPLY_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameEqualTo(String value) {
            addCriterion("APPLY_USER_NAME =", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotEqualTo(String value) {
            addCriterion("APPLY_USER_NAME <>", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameGreaterThan(String value) {
            addCriterion("APPLY_USER_NAME >", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_NAME >=", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLessThan(String value) {
            addCriterion("APPLY_USER_NAME <", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_NAME <=", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLike(String value) {
            addCriterion("APPLY_USER_NAME like", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotLike(String value) {
            addCriterion("APPLY_USER_NAME not like", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIn(List<String> values) {
            addCriterion("APPLY_USER_NAME in", values, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotIn(List<String> values) {
            addCriterion("APPLY_USER_NAME not in", values, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameBetween(String value1, String value2) {
            addCriterion("APPLY_USER_NAME between", value1, value2, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_NAME not between", value1, value2, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowIsNull() {
            addCriterion("APPLY_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowIsNotNull() {
            addCriterion("APPLY_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW =", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW <>", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowGreaterThan(String value) {
            addCriterion("APPLY_ORG_FLOW >", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW >=", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowLessThan(String value) {
            addCriterion("APPLY_ORG_FLOW <", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW <=", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowLike(String value) {
            addCriterion("APPLY_ORG_FLOW like", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotLike(String value) {
            addCriterion("APPLY_ORG_FLOW not like", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowIn(List<String> values) {
            addCriterion("APPLY_ORG_FLOW in", values, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotIn(List<String> values) {
            addCriterion("APPLY_ORG_FLOW not in", values, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_FLOW between", value1, value2, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_FLOW not between", value1, value2, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameIsNull() {
            addCriterion("APPLY_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameIsNotNull() {
            addCriterion("APPLY_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME =", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME <>", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameGreaterThan(String value) {
            addCriterion("APPLY_ORG_NAME >", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME >=", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameLessThan(String value) {
            addCriterion("APPLY_ORG_NAME <", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameLessThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME <=", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameLike(String value) {
            addCriterion("APPLY_ORG_NAME like", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotLike(String value) {
            addCriterion("APPLY_ORG_NAME not like", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameIn(List<String> values) {
            addCriterion("APPLY_ORG_NAME in", values, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotIn(List<String> values) {
            addCriterion("APPLY_ORG_NAME not in", values, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_NAME between", value1, value2, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_NAME not between", value1, value2, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowIsNull() {
            addCriterion("APPLY_DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowIsNotNull() {
            addCriterion("APPLY_DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowEqualTo(String value) {
            addCriterion("APPLY_DEPT_FLOW =", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowNotEqualTo(String value) {
            addCriterion("APPLY_DEPT_FLOW <>", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowGreaterThan(String value) {
            addCriterion("APPLY_DEPT_FLOW >", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_DEPT_FLOW >=", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowLessThan(String value) {
            addCriterion("APPLY_DEPT_FLOW <", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_DEPT_FLOW <=", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowLike(String value) {
            addCriterion("APPLY_DEPT_FLOW like", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowNotLike(String value) {
            addCriterion("APPLY_DEPT_FLOW not like", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowIn(List<String> values) {
            addCriterion("APPLY_DEPT_FLOW in", values, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowNotIn(List<String> values) {
            addCriterion("APPLY_DEPT_FLOW not in", values, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowBetween(String value1, String value2) {
            addCriterion("APPLY_DEPT_FLOW between", value1, value2, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_DEPT_FLOW not between", value1, value2, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameIsNull() {
            addCriterion("APPLY_DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameIsNotNull() {
            addCriterion("APPLY_DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameEqualTo(String value) {
            addCriterion("APPLY_DEPT_NAME =", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameNotEqualTo(String value) {
            addCriterion("APPLY_DEPT_NAME <>", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameGreaterThan(String value) {
            addCriterion("APPLY_DEPT_NAME >", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_DEPT_NAME >=", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameLessThan(String value) {
            addCriterion("APPLY_DEPT_NAME <", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameLessThanOrEqualTo(String value) {
            addCriterion("APPLY_DEPT_NAME <=", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameLike(String value) {
            addCriterion("APPLY_DEPT_NAME like", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameNotLike(String value) {
            addCriterion("APPLY_DEPT_NAME not like", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameIn(List<String> values) {
            addCriterion("APPLY_DEPT_NAME in", values, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameNotIn(List<String> values) {
            addCriterion("APPLY_DEPT_NAME not in", values, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameBetween(String value1, String value2) {
            addCriterion("APPLY_DEPT_NAME between", value1, value2, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameNotBetween(String value1, String value2) {
            addCriterion("APPLY_DEPT_NAME not between", value1, value2, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowIsNull() {
            addCriterion("CHARGE_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowIsNotNull() {
            addCriterion("CHARGE_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowEqualTo(String value) {
            addCriterion("CHARGE_ORG_FLOW =", value, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowNotEqualTo(String value) {
            addCriterion("CHARGE_ORG_FLOW <>", value, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowGreaterThan(String value) {
            addCriterion("CHARGE_ORG_FLOW >", value, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_ORG_FLOW >=", value, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowLessThan(String value) {
            addCriterion("CHARGE_ORG_FLOW <", value, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_ORG_FLOW <=", value, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowLike(String value) {
            addCriterion("CHARGE_ORG_FLOW like", value, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowNotLike(String value) {
            addCriterion("CHARGE_ORG_FLOW not like", value, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowIn(List<String> values) {
            addCriterion("CHARGE_ORG_FLOW in", values, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowNotIn(List<String> values) {
            addCriterion("CHARGE_ORG_FLOW not in", values, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowBetween(String value1, String value2) {
            addCriterion("CHARGE_ORG_FLOW between", value1, value2, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowNotBetween(String value1, String value2) {
            addCriterion("CHARGE_ORG_FLOW not between", value1, value2, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameIsNull() {
            addCriterion("CHARGE_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameIsNotNull() {
            addCriterion("CHARGE_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameEqualTo(String value) {
            addCriterion("CHARGE_ORG_NAME =", value, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameNotEqualTo(String value) {
            addCriterion("CHARGE_ORG_NAME <>", value, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameGreaterThan(String value) {
            addCriterion("CHARGE_ORG_NAME >", value, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_ORG_NAME >=", value, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameLessThan(String value) {
            addCriterion("CHARGE_ORG_NAME <", value, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_ORG_NAME <=", value, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameLike(String value) {
            addCriterion("CHARGE_ORG_NAME like", value, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameNotLike(String value) {
            addCriterion("CHARGE_ORG_NAME not like", value, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameIn(List<String> values) {
            addCriterion("CHARGE_ORG_NAME in", values, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameNotIn(List<String> values) {
            addCriterion("CHARGE_ORG_NAME not in", values, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameBetween(String value1, String value2) {
            addCriterion("CHARGE_ORG_NAME between", value1, value2, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameNotBetween(String value1, String value2) {
            addCriterion("CHARGE_ORG_NAME not between", value1, value2, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeIsNull() {
            addCriterion("PROJ_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeIsNotNull() {
            addCriterion("PROJ_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeEqualTo(String value) {
            addCriterion("PROJ_START_TIME =", value, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeNotEqualTo(String value) {
            addCriterion("PROJ_START_TIME <>", value, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeGreaterThan(String value) {
            addCriterion("PROJ_START_TIME >", value, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_START_TIME >=", value, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeLessThan(String value) {
            addCriterion("PROJ_START_TIME <", value, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeLessThanOrEqualTo(String value) {
            addCriterion("PROJ_START_TIME <=", value, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeLike(String value) {
            addCriterion("PROJ_START_TIME like", value, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeNotLike(String value) {
            addCriterion("PROJ_START_TIME not like", value, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeIn(List<String> values) {
            addCriterion("PROJ_START_TIME in", values, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeNotIn(List<String> values) {
            addCriterion("PROJ_START_TIME not in", values, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeBetween(String value1, String value2) {
            addCriterion("PROJ_START_TIME between", value1, value2, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeNotBetween(String value1, String value2) {
            addCriterion("PROJ_START_TIME not between", value1, value2, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeIsNull() {
            addCriterion("PROJ_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeIsNotNull() {
            addCriterion("PROJ_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeEqualTo(String value) {
            addCriterion("PROJ_END_TIME =", value, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeNotEqualTo(String value) {
            addCriterion("PROJ_END_TIME <>", value, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeGreaterThan(String value) {
            addCriterion("PROJ_END_TIME >", value, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_END_TIME >=", value, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeLessThan(String value) {
            addCriterion("PROJ_END_TIME <", value, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeLessThanOrEqualTo(String value) {
            addCriterion("PROJ_END_TIME <=", value, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeLike(String value) {
            addCriterion("PROJ_END_TIME like", value, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeNotLike(String value) {
            addCriterion("PROJ_END_TIME not like", value, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeIn(List<String> values) {
            addCriterion("PROJ_END_TIME in", values, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeNotIn(List<String> values) {
            addCriterion("PROJ_END_TIME not in", values, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeBetween(String value1, String value2) {
            addCriterion("PROJ_END_TIME between", value1, value2, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeNotBetween(String value1, String value2) {
            addCriterion("PROJ_END_TIME not between", value1, value2, "projEndTime");
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

        public Criteria andBranchIdIsNull() {
            addCriterion("BRANCH_ID is null");
            return (Criteria) this;
        }

        public Criteria andBranchIdIsNotNull() {
            addCriterion("BRANCH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBranchIdEqualTo(String value) {
            addCriterion("BRANCH_ID =", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotEqualTo(String value) {
            addCriterion("BRANCH_ID <>", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdGreaterThan(String value) {
            addCriterion("BRANCH_ID >", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdGreaterThanOrEqualTo(String value) {
            addCriterion("BRANCH_ID >=", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdLessThan(String value) {
            addCriterion("BRANCH_ID <", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdLessThanOrEqualTo(String value) {
            addCriterion("BRANCH_ID <=", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdLike(String value) {
            addCriterion("BRANCH_ID like", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotLike(String value) {
            addCriterion("BRANCH_ID not like", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdIn(List<String> values) {
            addCriterion("BRANCH_ID in", values, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotIn(List<String> values) {
            addCriterion("BRANCH_ID not in", values, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdBetween(String value1, String value2) {
            addCriterion("BRANCH_ID between", value1, value2, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotBetween(String value1, String value2) {
            addCriterion("BRANCH_ID not between", value1, value2, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchNameIsNull() {
            addCriterion("BRANCH_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBranchNameIsNotNull() {
            addCriterion("BRANCH_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBranchNameEqualTo(String value) {
            addCriterion("BRANCH_NAME =", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotEqualTo(String value) {
            addCriterion("BRANCH_NAME <>", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameGreaterThan(String value) {
            addCriterion("BRANCH_NAME >", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameGreaterThanOrEqualTo(String value) {
            addCriterion("BRANCH_NAME >=", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLessThan(String value) {
            addCriterion("BRANCH_NAME <", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLessThanOrEqualTo(String value) {
            addCriterion("BRANCH_NAME <=", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLike(String value) {
            addCriterion("BRANCH_NAME like", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotLike(String value) {
            addCriterion("BRANCH_NAME not like", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameIn(List<String> values) {
            addCriterion("BRANCH_NAME in", values, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotIn(List<String> values) {
            addCriterion("BRANCH_NAME not in", values, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameBetween(String value1, String value2) {
            addCriterion("BRANCH_NAME between", value1, value2, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotBetween(String value1, String value2) {
            addCriterion("BRANCH_NAME not between", value1, value2, "branchName");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmpnumIsNull() {
            addCriterion("APPLY_USER_EMPNUM is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmpnumIsNotNull() {
            addCriterion("APPLY_USER_EMPNUM is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmpnumEqualTo(String value) {
            addCriterion("APPLY_USER_EMPNUM =", value, "applyUserEmpnum");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmpnumNotEqualTo(String value) {
            addCriterion("APPLY_USER_EMPNUM <>", value, "applyUserEmpnum");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmpnumGreaterThan(String value) {
            addCriterion("APPLY_USER_EMPNUM >", value, "applyUserEmpnum");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmpnumGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_EMPNUM >=", value, "applyUserEmpnum");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmpnumLessThan(String value) {
            addCriterion("APPLY_USER_EMPNUM <", value, "applyUserEmpnum");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmpnumLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_EMPNUM <=", value, "applyUserEmpnum");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmpnumLike(String value) {
            addCriterion("APPLY_USER_EMPNUM like", value, "applyUserEmpnum");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmpnumNotLike(String value) {
            addCriterion("APPLY_USER_EMPNUM not like", value, "applyUserEmpnum");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmpnumIn(List<String> values) {
            addCriterion("APPLY_USER_EMPNUM in", values, "applyUserEmpnum");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmpnumNotIn(List<String> values) {
            addCriterion("APPLY_USER_EMPNUM not in", values, "applyUserEmpnum");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmpnumBetween(String value1, String value2) {
            addCriterion("APPLY_USER_EMPNUM between", value1, value2, "applyUserEmpnum");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmpnumNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_EMPNUM not between", value1, value2, "applyUserEmpnum");
            return (Criteria) this;
        }

        public Criteria andAcceptNumberIsNull() {
            addCriterion("ACCEPT_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andAcceptNumberIsNotNull() {
            addCriterion("ACCEPT_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptNumberEqualTo(String value) {
            addCriterion("ACCEPT_NUMBER =", value, "acceptNumber");
            return (Criteria) this;
        }

        public Criteria andAcceptNumberNotEqualTo(String value) {
            addCriterion("ACCEPT_NUMBER <>", value, "acceptNumber");
            return (Criteria) this;
        }

        public Criteria andAcceptNumberGreaterThan(String value) {
            addCriterion("ACCEPT_NUMBER >", value, "acceptNumber");
            return (Criteria) this;
        }

        public Criteria andAcceptNumberGreaterThanOrEqualTo(String value) {
            addCriterion("ACCEPT_NUMBER >=", value, "acceptNumber");
            return (Criteria) this;
        }

        public Criteria andAcceptNumberLessThan(String value) {
            addCriterion("ACCEPT_NUMBER <", value, "acceptNumber");
            return (Criteria) this;
        }

        public Criteria andAcceptNumberLessThanOrEqualTo(String value) {
            addCriterion("ACCEPT_NUMBER <=", value, "acceptNumber");
            return (Criteria) this;
        }

        public Criteria andAcceptNumberLike(String value) {
            addCriterion("ACCEPT_NUMBER like", value, "acceptNumber");
            return (Criteria) this;
        }

        public Criteria andAcceptNumberNotLike(String value) {
            addCriterion("ACCEPT_NUMBER not like", value, "acceptNumber");
            return (Criteria) this;
        }

        public Criteria andAcceptNumberIn(List<String> values) {
            addCriterion("ACCEPT_NUMBER in", values, "acceptNumber");
            return (Criteria) this;
        }

        public Criteria andAcceptNumberNotIn(List<String> values) {
            addCriterion("ACCEPT_NUMBER not in", values, "acceptNumber");
            return (Criteria) this;
        }

        public Criteria andAcceptNumberBetween(String value1, String value2) {
            addCriterion("ACCEPT_NUMBER between", value1, value2, "acceptNumber");
            return (Criteria) this;
        }

        public Criteria andAcceptNumberNotBetween(String value1, String value2) {
            addCriterion("ACCEPT_NUMBER not between", value1, value2, "acceptNumber");
            return (Criteria) this;
        }

        public Criteria andSubjIdIsNull() {
            addCriterion("SUBJ_ID is null");
            return (Criteria) this;
        }

        public Criteria andSubjIdIsNotNull() {
            addCriterion("SUBJ_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSubjIdEqualTo(String value) {
            addCriterion("SUBJ_ID =", value, "subjId");
            return (Criteria) this;
        }

        public Criteria andSubjIdNotEqualTo(String value) {
            addCriterion("SUBJ_ID <>", value, "subjId");
            return (Criteria) this;
        }

        public Criteria andSubjIdGreaterThan(String value) {
            addCriterion("SUBJ_ID >", value, "subjId");
            return (Criteria) this;
        }

        public Criteria andSubjIdGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJ_ID >=", value, "subjId");
            return (Criteria) this;
        }

        public Criteria andSubjIdLessThan(String value) {
            addCriterion("SUBJ_ID <", value, "subjId");
            return (Criteria) this;
        }

        public Criteria andSubjIdLessThanOrEqualTo(String value) {
            addCriterion("SUBJ_ID <=", value, "subjId");
            return (Criteria) this;
        }

        public Criteria andSubjIdLike(String value) {
            addCriterion("SUBJ_ID like", value, "subjId");
            return (Criteria) this;
        }

        public Criteria andSubjIdNotLike(String value) {
            addCriterion("SUBJ_ID not like", value, "subjId");
            return (Criteria) this;
        }

        public Criteria andSubjIdIn(List<String> values) {
            addCriterion("SUBJ_ID in", values, "subjId");
            return (Criteria) this;
        }

        public Criteria andSubjIdNotIn(List<String> values) {
            addCriterion("SUBJ_ID not in", values, "subjId");
            return (Criteria) this;
        }

        public Criteria andSubjIdBetween(String value1, String value2) {
            addCriterion("SUBJ_ID between", value1, value2, "subjId");
            return (Criteria) this;
        }

        public Criteria andSubjIdNotBetween(String value1, String value2) {
            addCriterion("SUBJ_ID not between", value1, value2, "subjId");
            return (Criteria) this;
        }

        public Criteria andSubjNameIsNull() {
            addCriterion("SUBJ_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSubjNameIsNotNull() {
            addCriterion("SUBJ_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSubjNameEqualTo(String value) {
            addCriterion("SUBJ_NAME =", value, "subjName");
            return (Criteria) this;
        }

        public Criteria andSubjNameNotEqualTo(String value) {
            addCriterion("SUBJ_NAME <>", value, "subjName");
            return (Criteria) this;
        }

        public Criteria andSubjNameGreaterThan(String value) {
            addCriterion("SUBJ_NAME >", value, "subjName");
            return (Criteria) this;
        }

        public Criteria andSubjNameGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJ_NAME >=", value, "subjName");
            return (Criteria) this;
        }

        public Criteria andSubjNameLessThan(String value) {
            addCriterion("SUBJ_NAME <", value, "subjName");
            return (Criteria) this;
        }

        public Criteria andSubjNameLessThanOrEqualTo(String value) {
            addCriterion("SUBJ_NAME <=", value, "subjName");
            return (Criteria) this;
        }

        public Criteria andSubjNameLike(String value) {
            addCriterion("SUBJ_NAME like", value, "subjName");
            return (Criteria) this;
        }

        public Criteria andSubjNameNotLike(String value) {
            addCriterion("SUBJ_NAME not like", value, "subjName");
            return (Criteria) this;
        }

        public Criteria andSubjNameIn(List<String> values) {
            addCriterion("SUBJ_NAME in", values, "subjName");
            return (Criteria) this;
        }

        public Criteria andSubjNameNotIn(List<String> values) {
            addCriterion("SUBJ_NAME not in", values, "subjName");
            return (Criteria) this;
        }

        public Criteria andSubjNameBetween(String value1, String value2) {
            addCriterion("SUBJ_NAME between", value1, value2, "subjName");
            return (Criteria) this;
        }

        public Criteria andSubjNameNotBetween(String value1, String value2) {
            addCriterion("SUBJ_NAME not between", value1, value2, "subjName");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceIdIsNull() {
            addCriterion("PROJ_SECOND_SOURCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceIdIsNotNull() {
            addCriterion("PROJ_SECOND_SOURCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceIdEqualTo(String value) {
            addCriterion("PROJ_SECOND_SOURCE_ID =", value, "projSecondSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceIdNotEqualTo(String value) {
            addCriterion("PROJ_SECOND_SOURCE_ID <>", value, "projSecondSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceIdGreaterThan(String value) {
            addCriterion("PROJ_SECOND_SOURCE_ID >", value, "projSecondSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SECOND_SOURCE_ID >=", value, "projSecondSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceIdLessThan(String value) {
            addCriterion("PROJ_SECOND_SOURCE_ID <", value, "projSecondSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceIdLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SECOND_SOURCE_ID <=", value, "projSecondSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceIdLike(String value) {
            addCriterion("PROJ_SECOND_SOURCE_ID like", value, "projSecondSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceIdNotLike(String value) {
            addCriterion("PROJ_SECOND_SOURCE_ID not like", value, "projSecondSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceIdIn(List<String> values) {
            addCriterion("PROJ_SECOND_SOURCE_ID in", values, "projSecondSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceIdNotIn(List<String> values) {
            addCriterion("PROJ_SECOND_SOURCE_ID not in", values, "projSecondSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceIdBetween(String value1, String value2) {
            addCriterion("PROJ_SECOND_SOURCE_ID between", value1, value2, "projSecondSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceIdNotBetween(String value1, String value2) {
            addCriterion("PROJ_SECOND_SOURCE_ID not between", value1, value2, "projSecondSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceNameIsNull() {
            addCriterion("PROJ_SECOND_SOURCE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceNameIsNotNull() {
            addCriterion("PROJ_SECOND_SOURCE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceNameEqualTo(String value) {
            addCriterion("PROJ_SECOND_SOURCE_NAME =", value, "projSecondSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceNameNotEqualTo(String value) {
            addCriterion("PROJ_SECOND_SOURCE_NAME <>", value, "projSecondSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceNameGreaterThan(String value) {
            addCriterion("PROJ_SECOND_SOURCE_NAME >", value, "projSecondSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SECOND_SOURCE_NAME >=", value, "projSecondSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceNameLessThan(String value) {
            addCriterion("PROJ_SECOND_SOURCE_NAME <", value, "projSecondSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SECOND_SOURCE_NAME <=", value, "projSecondSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceNameLike(String value) {
            addCriterion("PROJ_SECOND_SOURCE_NAME like", value, "projSecondSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceNameNotLike(String value) {
            addCriterion("PROJ_SECOND_SOURCE_NAME not like", value, "projSecondSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceNameIn(List<String> values) {
            addCriterion("PROJ_SECOND_SOURCE_NAME in", values, "projSecondSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceNameNotIn(List<String> values) {
            addCriterion("PROJ_SECOND_SOURCE_NAME not in", values, "projSecondSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceNameBetween(String value1, String value2) {
            addCriterion("PROJ_SECOND_SOURCE_NAME between", value1, value2, "projSecondSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSecondSourceNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_SECOND_SOURCE_NAME not between", value1, value2, "projSecondSourceName");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneIsNull() {
            addCriterion("APPLY_USER_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneIsNotNull() {
            addCriterion("APPLY_USER_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneEqualTo(String value) {
            addCriterion("APPLY_USER_PHONE =", value, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneNotEqualTo(String value) {
            addCriterion("APPLY_USER_PHONE <>", value, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneGreaterThan(String value) {
            addCriterion("APPLY_USER_PHONE >", value, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_PHONE >=", value, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneLessThan(String value) {
            addCriterion("APPLY_USER_PHONE <", value, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_PHONE <=", value, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneLike(String value) {
            addCriterion("APPLY_USER_PHONE like", value, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneNotLike(String value) {
            addCriterion("APPLY_USER_PHONE not like", value, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneIn(List<String> values) {
            addCriterion("APPLY_USER_PHONE in", values, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneNotIn(List<String> values) {
            addCriterion("APPLY_USER_PHONE not in", values, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneBetween(String value1, String value2) {
            addCriterion("APPLY_USER_PHONE between", value1, value2, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_PHONE not between", value1, value2, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdIsNull() {
            addCriterion("PLAN_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdIsNotNull() {
            addCriterion("PLAN_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdEqualTo(String value) {
            addCriterion("PLAN_TYPE_ID =", value, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdNotEqualTo(String value) {
            addCriterion("PLAN_TYPE_ID <>", value, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdGreaterThan(String value) {
            addCriterion("PLAN_TYPE_ID >", value, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_TYPE_ID >=", value, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdLessThan(String value) {
            addCriterion("PLAN_TYPE_ID <", value, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdLessThanOrEqualTo(String value) {
            addCriterion("PLAN_TYPE_ID <=", value, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdLike(String value) {
            addCriterion("PLAN_TYPE_ID like", value, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdNotLike(String value) {
            addCriterion("PLAN_TYPE_ID not like", value, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdIn(List<String> values) {
            addCriterion("PLAN_TYPE_ID in", values, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdNotIn(List<String> values) {
            addCriterion("PLAN_TYPE_ID not in", values, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdBetween(String value1, String value2) {
            addCriterion("PLAN_TYPE_ID between", value1, value2, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIdNotBetween(String value1, String value2) {
            addCriterion("PLAN_TYPE_ID not between", value1, value2, "planTypeId");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameIsNull() {
            addCriterion("PLAN_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameIsNotNull() {
            addCriterion("PLAN_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameEqualTo(String value) {
            addCriterion("PLAN_TYPE_NAME =", value, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameNotEqualTo(String value) {
            addCriterion("PLAN_TYPE_NAME <>", value, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameGreaterThan(String value) {
            addCriterion("PLAN_TYPE_NAME >", value, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_TYPE_NAME >=", value, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameLessThan(String value) {
            addCriterion("PLAN_TYPE_NAME <", value, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameLessThanOrEqualTo(String value) {
            addCriterion("PLAN_TYPE_NAME <=", value, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameLike(String value) {
            addCriterion("PLAN_TYPE_NAME like", value, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameNotLike(String value) {
            addCriterion("PLAN_TYPE_NAME not like", value, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameIn(List<String> values) {
            addCriterion("PLAN_TYPE_NAME in", values, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameNotIn(List<String> values) {
            addCriterion("PLAN_TYPE_NAME not in", values, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameBetween(String value1, String value2) {
            addCriterion("PLAN_TYPE_NAME between", value1, value2, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNameNotBetween(String value1, String value2) {
            addCriterion("PLAN_TYPE_NAME not between", value1, value2, "planTypeName");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNull() {
            addCriterion("ACCOUNT_NO is null");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNotNull() {
            addCriterion("ACCOUNT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNoEqualTo(String value) {
            addCriterion("ACCOUNT_NO =", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotEqualTo(String value) {
            addCriterion("ACCOUNT_NO <>", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThan(String value) {
            addCriterion("ACCOUNT_NO >", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_NO >=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThan(String value) {
            addCriterion("ACCOUNT_NO <", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_NO <=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLike(String value) {
            addCriterion("ACCOUNT_NO like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotLike(String value) {
            addCriterion("ACCOUNT_NO not like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoIn(List<String> values) {
            addCriterion("ACCOUNT_NO in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotIn(List<String> values) {
            addCriterion("ACCOUNT_NO not in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoBetween(String value1, String value2) {
            addCriterion("ACCOUNT_NO between", value1, value2, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotBetween(String value1, String value2) {
            addCriterion("ACCOUNT_NO not between", value1, value2, "accountNo");
            return (Criteria) this;
        }

        public Criteria andIsEthicalFlagIsNull() {
            addCriterion("IS_ETHICAL_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andIsEthicalFlagIsNotNull() {
            addCriterion("IS_ETHICAL_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andIsEthicalFlagEqualTo(String value) {
            addCriterion("IS_ETHICAL_FLAG =", value, "isEthicalFlag");
            return (Criteria) this;
        }

        public Criteria andIsEthicalFlagNotEqualTo(String value) {
            addCriterion("IS_ETHICAL_FLAG <>", value, "isEthicalFlag");
            return (Criteria) this;
        }

        public Criteria andIsEthicalFlagGreaterThan(String value) {
            addCriterion("IS_ETHICAL_FLAG >", value, "isEthicalFlag");
            return (Criteria) this;
        }

        public Criteria andIsEthicalFlagGreaterThanOrEqualTo(String value) {
            addCriterion("IS_ETHICAL_FLAG >=", value, "isEthicalFlag");
            return (Criteria) this;
        }

        public Criteria andIsEthicalFlagLessThan(String value) {
            addCriterion("IS_ETHICAL_FLAG <", value, "isEthicalFlag");
            return (Criteria) this;
        }

        public Criteria andIsEthicalFlagLessThanOrEqualTo(String value) {
            addCriterion("IS_ETHICAL_FLAG <=", value, "isEthicalFlag");
            return (Criteria) this;
        }

        public Criteria andIsEthicalFlagLike(String value) {
            addCriterion("IS_ETHICAL_FLAG like", value, "isEthicalFlag");
            return (Criteria) this;
        }

        public Criteria andIsEthicalFlagNotLike(String value) {
            addCriterion("IS_ETHICAL_FLAG not like", value, "isEthicalFlag");
            return (Criteria) this;
        }

        public Criteria andIsEthicalFlagIn(List<String> values) {
            addCriterion("IS_ETHICAL_FLAG in", values, "isEthicalFlag");
            return (Criteria) this;
        }

        public Criteria andIsEthicalFlagNotIn(List<String> values) {
            addCriterion("IS_ETHICAL_FLAG not in", values, "isEthicalFlag");
            return (Criteria) this;
        }

        public Criteria andIsEthicalFlagBetween(String value1, String value2) {
            addCriterion("IS_ETHICAL_FLAG between", value1, value2, "isEthicalFlag");
            return (Criteria) this;
        }

        public Criteria andIsEthicalFlagNotBetween(String value1, String value2) {
            addCriterion("IS_ETHICAL_FLAG not between", value1, value2, "isEthicalFlag");
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