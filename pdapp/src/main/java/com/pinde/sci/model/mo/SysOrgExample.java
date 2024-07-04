package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SysOrgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysOrgExample() {
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

        public Criteria andOrgCodeIsNull() {
            addCriterion("ORG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNotNull() {
            addCriterion("ORG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeEqualTo(String value) {
            addCriterion("ORG_CODE =", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotEqualTo(String value) {
            addCriterion("ORG_CODE <>", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThan(String value) {
            addCriterion("ORG_CODE >", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CODE >=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThan(String value) {
            addCriterion("ORG_CODE <", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("ORG_CODE <=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLike(String value) {
            addCriterion("ORG_CODE like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotLike(String value) {
            addCriterion("ORG_CODE not like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIn(List<String> values) {
            addCriterion("ORG_CODE in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotIn(List<String> values) {
            addCriterion("ORG_CODE not in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeBetween(String value1, String value2) {
            addCriterion("ORG_CODE between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotBetween(String value1, String value2) {
            addCriterion("ORG_CODE not between", value1, value2, "orgCode");
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

        public Criteria andOrgProvIdIsNull() {
            addCriterion("ORG_PROV_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdIsNotNull() {
            addCriterion("ORG_PROV_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdEqualTo(String value) {
            addCriterion("ORG_PROV_ID =", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdNotEqualTo(String value) {
            addCriterion("ORG_PROV_ID <>", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdGreaterThan(String value) {
            addCriterion("ORG_PROV_ID >", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_PROV_ID >=", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdLessThan(String value) {
            addCriterion("ORG_PROV_ID <", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_PROV_ID <=", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdLike(String value) {
            addCriterion("ORG_PROV_ID like", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdNotLike(String value) {
            addCriterion("ORG_PROV_ID not like", value, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdIn(List<String> values) {
            addCriterion("ORG_PROV_ID in", values, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdNotIn(List<String> values) {
            addCriterion("ORG_PROV_ID not in", values, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdBetween(String value1, String value2) {
            addCriterion("ORG_PROV_ID between", value1, value2, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvIdNotBetween(String value1, String value2) {
            addCriterion("ORG_PROV_ID not between", value1, value2, "orgProvId");
            return (Criteria) this;
        }

        public Criteria andOrgProvNameIsNull() {
            addCriterion("ORG_PROV_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgProvNameIsNotNull() {
            addCriterion("ORG_PROV_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgProvNameEqualTo(String value) {
            addCriterion("ORG_PROV_NAME =", value, "orgProvName");
            return (Criteria) this;
        }

        public Criteria andOrgProvNameNotEqualTo(String value) {
            addCriterion("ORG_PROV_NAME <>", value, "orgProvName");
            return (Criteria) this;
        }

        public Criteria andOrgProvNameGreaterThan(String value) {
            addCriterion("ORG_PROV_NAME >", value, "orgProvName");
            return (Criteria) this;
        }

        public Criteria andOrgProvNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_PROV_NAME >=", value, "orgProvName");
            return (Criteria) this;
        }

        public Criteria andOrgProvNameLessThan(String value) {
            addCriterion("ORG_PROV_NAME <", value, "orgProvName");
            return (Criteria) this;
        }

        public Criteria andOrgProvNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_PROV_NAME <=", value, "orgProvName");
            return (Criteria) this;
        }

        public Criteria andOrgProvNameLike(String value) {
            addCriterion("ORG_PROV_NAME like", value, "orgProvName");
            return (Criteria) this;
        }

        public Criteria andOrgProvNameNotLike(String value) {
            addCriterion("ORG_PROV_NAME not like", value, "orgProvName");
            return (Criteria) this;
        }

        public Criteria andOrgProvNameIn(List<String> values) {
            addCriterion("ORG_PROV_NAME in", values, "orgProvName");
            return (Criteria) this;
        }

        public Criteria andOrgProvNameNotIn(List<String> values) {
            addCriterion("ORG_PROV_NAME not in", values, "orgProvName");
            return (Criteria) this;
        }

        public Criteria andOrgProvNameBetween(String value1, String value2) {
            addCriterion("ORG_PROV_NAME between", value1, value2, "orgProvName");
            return (Criteria) this;
        }

        public Criteria andOrgProvNameNotBetween(String value1, String value2) {
            addCriterion("ORG_PROV_NAME not between", value1, value2, "orgProvName");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdIsNull() {
            addCriterion("ORG_CITY_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdIsNotNull() {
            addCriterion("ORG_CITY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdEqualTo(String value) {
            addCriterion("ORG_CITY_ID =", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdNotEqualTo(String value) {
            addCriterion("ORG_CITY_ID <>", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdGreaterThan(String value) {
            addCriterion("ORG_CITY_ID >", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CITY_ID >=", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdLessThan(String value) {
            addCriterion("ORG_CITY_ID <", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_CITY_ID <=", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdLike(String value) {
            addCriterion("ORG_CITY_ID like", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdNotLike(String value) {
            addCriterion("ORG_CITY_ID not like", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdIn(List<String> values) {
            addCriterion("ORG_CITY_ID in", values, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdNotIn(List<String> values) {
            addCriterion("ORG_CITY_ID not in", values, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdBetween(String value1, String value2) {
            addCriterion("ORG_CITY_ID between", value1, value2, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdNotBetween(String value1, String value2) {
            addCriterion("ORG_CITY_ID not between", value1, value2, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameIsNull() {
            addCriterion("ORG_CITY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameIsNotNull() {
            addCriterion("ORG_CITY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameEqualTo(String value) {
            addCriterion("ORG_CITY_NAME =", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameNotEqualTo(String value) {
            addCriterion("ORG_CITY_NAME <>", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameGreaterThan(String value) {
            addCriterion("ORG_CITY_NAME >", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CITY_NAME >=", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameLessThan(String value) {
            addCriterion("ORG_CITY_NAME <", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_CITY_NAME <=", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameLike(String value) {
            addCriterion("ORG_CITY_NAME like", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameNotLike(String value) {
            addCriterion("ORG_CITY_NAME not like", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameIn(List<String> values) {
            addCriterion("ORG_CITY_NAME in", values, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameNotIn(List<String> values) {
            addCriterion("ORG_CITY_NAME not in", values, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameBetween(String value1, String value2) {
            addCriterion("ORG_CITY_NAME between", value1, value2, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameNotBetween(String value1, String value2) {
            addCriterion("ORG_CITY_NAME not between", value1, value2, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdIsNull() {
            addCriterion("ORG_AREA_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdIsNotNull() {
            addCriterion("ORG_AREA_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdEqualTo(String value) {
            addCriterion("ORG_AREA_ID =", value, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdNotEqualTo(String value) {
            addCriterion("ORG_AREA_ID <>", value, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdGreaterThan(String value) {
            addCriterion("ORG_AREA_ID >", value, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_AREA_ID >=", value, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdLessThan(String value) {
            addCriterion("ORG_AREA_ID <", value, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_AREA_ID <=", value, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdLike(String value) {
            addCriterion("ORG_AREA_ID like", value, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdNotLike(String value) {
            addCriterion("ORG_AREA_ID not like", value, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdIn(List<String> values) {
            addCriterion("ORG_AREA_ID in", values, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdNotIn(List<String> values) {
            addCriterion("ORG_AREA_ID not in", values, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdBetween(String value1, String value2) {
            addCriterion("ORG_AREA_ID between", value1, value2, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaIdNotBetween(String value1, String value2) {
            addCriterion("ORG_AREA_ID not between", value1, value2, "orgAreaId");
            return (Criteria) this;
        }

        public Criteria andOrgAreaNameIsNull() {
            addCriterion("ORG_AREA_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgAreaNameIsNotNull() {
            addCriterion("ORG_AREA_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAreaNameEqualTo(String value) {
            addCriterion("ORG_AREA_NAME =", value, "orgAreaName");
            return (Criteria) this;
        }

        public Criteria andOrgAreaNameNotEqualTo(String value) {
            addCriterion("ORG_AREA_NAME <>", value, "orgAreaName");
            return (Criteria) this;
        }

        public Criteria andOrgAreaNameGreaterThan(String value) {
            addCriterion("ORG_AREA_NAME >", value, "orgAreaName");
            return (Criteria) this;
        }

        public Criteria andOrgAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_AREA_NAME >=", value, "orgAreaName");
            return (Criteria) this;
        }

        public Criteria andOrgAreaNameLessThan(String value) {
            addCriterion("ORG_AREA_NAME <", value, "orgAreaName");
            return (Criteria) this;
        }

        public Criteria andOrgAreaNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_AREA_NAME <=", value, "orgAreaName");
            return (Criteria) this;
        }

        public Criteria andOrgAreaNameLike(String value) {
            addCriterion("ORG_AREA_NAME like", value, "orgAreaName");
            return (Criteria) this;
        }

        public Criteria andOrgAreaNameNotLike(String value) {
            addCriterion("ORG_AREA_NAME not like", value, "orgAreaName");
            return (Criteria) this;
        }

        public Criteria andOrgAreaNameIn(List<String> values) {
            addCriterion("ORG_AREA_NAME in", values, "orgAreaName");
            return (Criteria) this;
        }

        public Criteria andOrgAreaNameNotIn(List<String> values) {
            addCriterion("ORG_AREA_NAME not in", values, "orgAreaName");
            return (Criteria) this;
        }

        public Criteria andOrgAreaNameBetween(String value1, String value2) {
            addCriterion("ORG_AREA_NAME between", value1, value2, "orgAreaName");
            return (Criteria) this;
        }

        public Criteria andOrgAreaNameNotBetween(String value1, String value2) {
            addCriterion("ORG_AREA_NAME not between", value1, value2, "orgAreaName");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdIsNull() {
            addCriterion("ORG_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdIsNotNull() {
            addCriterion("ORG_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdEqualTo(String value) {
            addCriterion("ORG_TYPE_ID =", value, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdNotEqualTo(String value) {
            addCriterion("ORG_TYPE_ID <>", value, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdGreaterThan(String value) {
            addCriterion("ORG_TYPE_ID >", value, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_TYPE_ID >=", value, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdLessThan(String value) {
            addCriterion("ORG_TYPE_ID <", value, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_TYPE_ID <=", value, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdLike(String value) {
            addCriterion("ORG_TYPE_ID like", value, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdNotLike(String value) {
            addCriterion("ORG_TYPE_ID not like", value, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdIn(List<String> values) {
            addCriterion("ORG_TYPE_ID in", values, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdNotIn(List<String> values) {
            addCriterion("ORG_TYPE_ID not in", values, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdBetween(String value1, String value2) {
            addCriterion("ORG_TYPE_ID between", value1, value2, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIdNotBetween(String value1, String value2) {
            addCriterion("ORG_TYPE_ID not between", value1, value2, "orgTypeId");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNameIsNull() {
            addCriterion("ORG_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNameIsNotNull() {
            addCriterion("ORG_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNameEqualTo(String value) {
            addCriterion("ORG_TYPE_NAME =", value, "orgTypeName");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNameNotEqualTo(String value) {
            addCriterion("ORG_TYPE_NAME <>", value, "orgTypeName");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNameGreaterThan(String value) {
            addCriterion("ORG_TYPE_NAME >", value, "orgTypeName");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_TYPE_NAME >=", value, "orgTypeName");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNameLessThan(String value) {
            addCriterion("ORG_TYPE_NAME <", value, "orgTypeName");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_TYPE_NAME <=", value, "orgTypeName");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNameLike(String value) {
            addCriterion("ORG_TYPE_NAME like", value, "orgTypeName");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNameNotLike(String value) {
            addCriterion("ORG_TYPE_NAME not like", value, "orgTypeName");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNameIn(List<String> values) {
            addCriterion("ORG_TYPE_NAME in", values, "orgTypeName");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNameNotIn(List<String> values) {
            addCriterion("ORG_TYPE_NAME not in", values, "orgTypeName");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNameBetween(String value1, String value2) {
            addCriterion("ORG_TYPE_NAME between", value1, value2, "orgTypeName");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNameNotBetween(String value1, String value2) {
            addCriterion("ORG_TYPE_NAME not between", value1, value2, "orgTypeName");
            return (Criteria) this;
        }

        public Criteria andOrgAddressIsNull() {
            addCriterion("ORG_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andOrgAddressIsNotNull() {
            addCriterion("ORG_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAddressEqualTo(String value) {
            addCriterion("ORG_ADDRESS =", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotEqualTo(String value) {
            addCriterion("ORG_ADDRESS <>", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressGreaterThan(String value) {
            addCriterion("ORG_ADDRESS >", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ADDRESS >=", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressLessThan(String value) {
            addCriterion("ORG_ADDRESS <", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressLessThanOrEqualTo(String value) {
            addCriterion("ORG_ADDRESS <=", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressLike(String value) {
            addCriterion("ORG_ADDRESS like", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotLike(String value) {
            addCriterion("ORG_ADDRESS not like", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressIn(List<String> values) {
            addCriterion("ORG_ADDRESS in", values, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotIn(List<String> values) {
            addCriterion("ORG_ADDRESS not in", values, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressBetween(String value1, String value2) {
            addCriterion("ORG_ADDRESS between", value1, value2, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotBetween(String value1, String value2) {
            addCriterion("ORG_ADDRESS not between", value1, value2, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneIsNull() {
            addCriterion("ORG_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneIsNotNull() {
            addCriterion("ORG_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneEqualTo(String value) {
            addCriterion("ORG_PHONE =", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneNotEqualTo(String value) {
            addCriterion("ORG_PHONE <>", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneGreaterThan(String value) {
            addCriterion("ORG_PHONE >", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_PHONE >=", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneLessThan(String value) {
            addCriterion("ORG_PHONE <", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneLessThanOrEqualTo(String value) {
            addCriterion("ORG_PHONE <=", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneLike(String value) {
            addCriterion("ORG_PHONE like", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneNotLike(String value) {
            addCriterion("ORG_PHONE not like", value, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneIn(List<String> values) {
            addCriterion("ORG_PHONE in", values, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneNotIn(List<String> values) {
            addCriterion("ORG_PHONE not in", values, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneBetween(String value1, String value2) {
            addCriterion("ORG_PHONE between", value1, value2, "orgPhone");
            return (Criteria) this;
        }

        public Criteria andOrgPhoneNotBetween(String value1, String value2) {
            addCriterion("ORG_PHONE not between", value1, value2, "orgPhone");
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

        public Criteria andOrgZipIsNull() {
            addCriterion("ORG_ZIP is null");
            return (Criteria) this;
        }

        public Criteria andOrgZipIsNotNull() {
            addCriterion("ORG_ZIP is not null");
            return (Criteria) this;
        }

        public Criteria andOrgZipEqualTo(String value) {
            addCriterion("ORG_ZIP =", value, "orgZip");
            return (Criteria) this;
        }

        public Criteria andOrgZipNotEqualTo(String value) {
            addCriterion("ORG_ZIP <>", value, "orgZip");
            return (Criteria) this;
        }

        public Criteria andOrgZipGreaterThan(String value) {
            addCriterion("ORG_ZIP >", value, "orgZip");
            return (Criteria) this;
        }

        public Criteria andOrgZipGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ZIP >=", value, "orgZip");
            return (Criteria) this;
        }

        public Criteria andOrgZipLessThan(String value) {
            addCriterion("ORG_ZIP <", value, "orgZip");
            return (Criteria) this;
        }

        public Criteria andOrgZipLessThanOrEqualTo(String value) {
            addCriterion("ORG_ZIP <=", value, "orgZip");
            return (Criteria) this;
        }

        public Criteria andOrgZipLike(String value) {
            addCriterion("ORG_ZIP like", value, "orgZip");
            return (Criteria) this;
        }

        public Criteria andOrgZipNotLike(String value) {
            addCriterion("ORG_ZIP not like", value, "orgZip");
            return (Criteria) this;
        }

        public Criteria andOrgZipIn(List<String> values) {
            addCriterion("ORG_ZIP in", values, "orgZip");
            return (Criteria) this;
        }

        public Criteria andOrgZipNotIn(List<String> values) {
            addCriterion("ORG_ZIP not in", values, "orgZip");
            return (Criteria) this;
        }

        public Criteria andOrgZipBetween(String value1, String value2) {
            addCriterion("ORG_ZIP between", value1, value2, "orgZip");
            return (Criteria) this;
        }

        public Criteria andOrgZipNotBetween(String value1, String value2) {
            addCriterion("ORG_ZIP not between", value1, value2, "orgZip");
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

        public Criteria andOrgLevelIdIsNull() {
            addCriterion("ORG_LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdIsNotNull() {
            addCriterion("ORG_LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdEqualTo(String value) {
            addCriterion("ORG_LEVEL_ID =", value, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdNotEqualTo(String value) {
            addCriterion("ORG_LEVEL_ID <>", value, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdGreaterThan(String value) {
            addCriterion("ORG_LEVEL_ID >", value, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_LEVEL_ID >=", value, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdLessThan(String value) {
            addCriterion("ORG_LEVEL_ID <", value, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_LEVEL_ID <=", value, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdLike(String value) {
            addCriterion("ORG_LEVEL_ID like", value, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdNotLike(String value) {
            addCriterion("ORG_LEVEL_ID not like", value, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdIn(List<String> values) {
            addCriterion("ORG_LEVEL_ID in", values, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdNotIn(List<String> values) {
            addCriterion("ORG_LEVEL_ID not in", values, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdBetween(String value1, String value2) {
            addCriterion("ORG_LEVEL_ID between", value1, value2, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIdNotBetween(String value1, String value2) {
            addCriterion("ORG_LEVEL_ID not between", value1, value2, "orgLevelId");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNameIsNull() {
            addCriterion("ORG_LEVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNameIsNotNull() {
            addCriterion("ORG_LEVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNameEqualTo(String value) {
            addCriterion("ORG_LEVEL_NAME =", value, "orgLevelName");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNameNotEqualTo(String value) {
            addCriterion("ORG_LEVEL_NAME <>", value, "orgLevelName");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNameGreaterThan(String value) {
            addCriterion("ORG_LEVEL_NAME >", value, "orgLevelName");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_LEVEL_NAME >=", value, "orgLevelName");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNameLessThan(String value) {
            addCriterion("ORG_LEVEL_NAME <", value, "orgLevelName");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_LEVEL_NAME <=", value, "orgLevelName");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNameLike(String value) {
            addCriterion("ORG_LEVEL_NAME like", value, "orgLevelName");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNameNotLike(String value) {
            addCriterion("ORG_LEVEL_NAME not like", value, "orgLevelName");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNameIn(List<String> values) {
            addCriterion("ORG_LEVEL_NAME in", values, "orgLevelName");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNameNotIn(List<String> values) {
            addCriterion("ORG_LEVEL_NAME not in", values, "orgLevelName");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNameBetween(String value1, String value2) {
            addCriterion("ORG_LEVEL_NAME between", value1, value2, "orgLevelName");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNameNotBetween(String value1, String value2) {
            addCriterion("ORG_LEVEL_NAME not between", value1, value2, "orgLevelName");
            return (Criteria) this;
        }

        public Criteria andIsExamOrgIsNull() {
            addCriterion("IS_EXAM_ORG is null");
            return (Criteria) this;
        }

        public Criteria andIsExamOrgIsNotNull() {
            addCriterion("IS_EXAM_ORG is not null");
            return (Criteria) this;
        }

        public Criteria andIsExamOrgEqualTo(String value) {
            addCriterion("IS_EXAM_ORG =", value, "isExamOrg");
            return (Criteria) this;
        }

        public Criteria andIsExamOrgNotEqualTo(String value) {
            addCriterion("IS_EXAM_ORG <>", value, "isExamOrg");
            return (Criteria) this;
        }

        public Criteria andIsExamOrgGreaterThan(String value) {
            addCriterion("IS_EXAM_ORG >", value, "isExamOrg");
            return (Criteria) this;
        }

        public Criteria andIsExamOrgGreaterThanOrEqualTo(String value) {
            addCriterion("IS_EXAM_ORG >=", value, "isExamOrg");
            return (Criteria) this;
        }

        public Criteria andIsExamOrgLessThan(String value) {
            addCriterion("IS_EXAM_ORG <", value, "isExamOrg");
            return (Criteria) this;
        }

        public Criteria andIsExamOrgLessThanOrEqualTo(String value) {
            addCriterion("IS_EXAM_ORG <=", value, "isExamOrg");
            return (Criteria) this;
        }

        public Criteria andIsExamOrgLike(String value) {
            addCriterion("IS_EXAM_ORG like", value, "isExamOrg");
            return (Criteria) this;
        }

        public Criteria andIsExamOrgNotLike(String value) {
            addCriterion("IS_EXAM_ORG not like", value, "isExamOrg");
            return (Criteria) this;
        }

        public Criteria andIsExamOrgIn(List<String> values) {
            addCriterion("IS_EXAM_ORG in", values, "isExamOrg");
            return (Criteria) this;
        }

        public Criteria andIsExamOrgNotIn(List<String> values) {
            addCriterion("IS_EXAM_ORG not in", values, "isExamOrg");
            return (Criteria) this;
        }

        public Criteria andIsExamOrgBetween(String value1, String value2) {
            addCriterion("IS_EXAM_ORG between", value1, value2, "isExamOrg");
            return (Criteria) this;
        }

        public Criteria andIsExamOrgNotBetween(String value1, String value2) {
            addCriterion("IS_EXAM_ORG not between", value1, value2, "isExamOrg");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaIsNull() {
            addCriterion("IS_EXAM_TEA is null");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaIsNotNull() {
            addCriterion("IS_EXAM_TEA is not null");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaEqualTo(String value) {
            addCriterion("IS_EXAM_TEA =", value, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaNotEqualTo(String value) {
            addCriterion("IS_EXAM_TEA <>", value, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaGreaterThan(String value) {
            addCriterion("IS_EXAM_TEA >", value, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaGreaterThanOrEqualTo(String value) {
            addCriterion("IS_EXAM_TEA >=", value, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaLessThan(String value) {
            addCriterion("IS_EXAM_TEA <", value, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaLessThanOrEqualTo(String value) {
            addCriterion("IS_EXAM_TEA <=", value, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaLike(String value) {
            addCriterion("IS_EXAM_TEA like", value, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaNotLike(String value) {
            addCriterion("IS_EXAM_TEA not like", value, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaIn(List<String> values) {
            addCriterion("IS_EXAM_TEA in", values, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaNotIn(List<String> values) {
            addCriterion("IS_EXAM_TEA not in", values, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaBetween(String value1, String value2) {
            addCriterion("IS_EXAM_TEA between", value1, value2, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaNotBetween(String value1, String value2) {
            addCriterion("IS_EXAM_TEA not between", value1, value2, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andSendSchoolIdIsNull() {
            addCriterion("SEND_SCHOOL_ID is null");
            return (Criteria) this;
        }

        public Criteria andSendSchoolIdIsNotNull() {
            addCriterion("SEND_SCHOOL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSendSchoolIdEqualTo(String value) {
            addCriterion("SEND_SCHOOL_ID =", value, "sendSchoolId");
            return (Criteria) this;
        }

        public Criteria andSendSchoolIdNotEqualTo(String value) {
            addCriterion("SEND_SCHOOL_ID <>", value, "sendSchoolId");
            return (Criteria) this;
        }

        public Criteria andSendSchoolIdGreaterThan(String value) {
            addCriterion("SEND_SCHOOL_ID >", value, "sendSchoolId");
            return (Criteria) this;
        }

        public Criteria andSendSchoolIdGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_SCHOOL_ID >=", value, "sendSchoolId");
            return (Criteria) this;
        }

        public Criteria andSendSchoolIdLessThan(String value) {
            addCriterion("SEND_SCHOOL_ID <", value, "sendSchoolId");
            return (Criteria) this;
        }

        public Criteria andSendSchoolIdLessThanOrEqualTo(String value) {
            addCriterion("SEND_SCHOOL_ID <=", value, "sendSchoolId");
            return (Criteria) this;
        }

        public Criteria andSendSchoolIdLike(String value) {
            addCriterion("SEND_SCHOOL_ID like", value, "sendSchoolId");
            return (Criteria) this;
        }

        public Criteria andSendSchoolIdNotLike(String value) {
            addCriterion("SEND_SCHOOL_ID not like", value, "sendSchoolId");
            return (Criteria) this;
        }

        public Criteria andSendSchoolIdIn(List<String> values) {
            addCriterion("SEND_SCHOOL_ID in", values, "sendSchoolId");
            return (Criteria) this;
        }

        public Criteria andSendSchoolIdNotIn(List<String> values) {
            addCriterion("SEND_SCHOOL_ID not in", values, "sendSchoolId");
            return (Criteria) this;
        }

        public Criteria andSendSchoolIdBetween(String value1, String value2) {
            addCriterion("SEND_SCHOOL_ID between", value1, value2, "sendSchoolId");
            return (Criteria) this;
        }

        public Criteria andSendSchoolIdNotBetween(String value1, String value2) {
            addCriterion("SEND_SCHOOL_ID not between", value1, value2, "sendSchoolId");
            return (Criteria) this;
        }

        public Criteria andSendSchoolNameIsNull() {
            addCriterion("SEND_SCHOOL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSendSchoolNameIsNotNull() {
            addCriterion("SEND_SCHOOL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSendSchoolNameEqualTo(String value) {
            addCriterion("SEND_SCHOOL_NAME =", value, "sendSchoolName");
            return (Criteria) this;
        }

        public Criteria andSendSchoolNameNotEqualTo(String value) {
            addCriterion("SEND_SCHOOL_NAME <>", value, "sendSchoolName");
            return (Criteria) this;
        }

        public Criteria andSendSchoolNameGreaterThan(String value) {
            addCriterion("SEND_SCHOOL_NAME >", value, "sendSchoolName");
            return (Criteria) this;
        }

        public Criteria andSendSchoolNameGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_SCHOOL_NAME >=", value, "sendSchoolName");
            return (Criteria) this;
        }

        public Criteria andSendSchoolNameLessThan(String value) {
            addCriterion("SEND_SCHOOL_NAME <", value, "sendSchoolName");
            return (Criteria) this;
        }

        public Criteria andSendSchoolNameLessThanOrEqualTo(String value) {
            addCriterion("SEND_SCHOOL_NAME <=", value, "sendSchoolName");
            return (Criteria) this;
        }

        public Criteria andSendSchoolNameLike(String value) {
            addCriterion("SEND_SCHOOL_NAME like", value, "sendSchoolName");
            return (Criteria) this;
        }

        public Criteria andSendSchoolNameNotLike(String value) {
            addCriterion("SEND_SCHOOL_NAME not like", value, "sendSchoolName");
            return (Criteria) this;
        }

        public Criteria andSendSchoolNameIn(List<String> values) {
            addCriterion("SEND_SCHOOL_NAME in", values, "sendSchoolName");
            return (Criteria) this;
        }

        public Criteria andSendSchoolNameNotIn(List<String> values) {
            addCriterion("SEND_SCHOOL_NAME not in", values, "sendSchoolName");
            return (Criteria) this;
        }

        public Criteria andSendSchoolNameBetween(String value1, String value2) {
            addCriterion("SEND_SCHOOL_NAME between", value1, value2, "sendSchoolName");
            return (Criteria) this;
        }

        public Criteria andSendSchoolNameNotBetween(String value1, String value2) {
            addCriterion("SEND_SCHOOL_NAME not between", value1, value2, "sendSchoolName");
            return (Criteria) this;
        }

        public Criteria andIsSecondFlagIsNull() {
            addCriterion("IS_SECOND_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andIsSecondFlagIsNotNull() {
            addCriterion("IS_SECOND_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andIsSecondFlagEqualTo(String value) {
            addCriterion("IS_SECOND_FLAG =", value, "isSecondFlag");
            return (Criteria) this;
        }

        public Criteria andIsSecondFlagNotEqualTo(String value) {
            addCriterion("IS_SECOND_FLAG <>", value, "isSecondFlag");
            return (Criteria) this;
        }

        public Criteria andIsSecondFlagGreaterThan(String value) {
            addCriterion("IS_SECOND_FLAG >", value, "isSecondFlag");
            return (Criteria) this;
        }

        public Criteria andIsSecondFlagGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SECOND_FLAG >=", value, "isSecondFlag");
            return (Criteria) this;
        }

        public Criteria andIsSecondFlagLessThan(String value) {
            addCriterion("IS_SECOND_FLAG <", value, "isSecondFlag");
            return (Criteria) this;
        }

        public Criteria andIsSecondFlagLessThanOrEqualTo(String value) {
            addCriterion("IS_SECOND_FLAG <=", value, "isSecondFlag");
            return (Criteria) this;
        }

        public Criteria andIsSecondFlagLike(String value) {
            addCriterion("IS_SECOND_FLAG like", value, "isSecondFlag");
            return (Criteria) this;
        }

        public Criteria andIsSecondFlagNotLike(String value) {
            addCriterion("IS_SECOND_FLAG not like", value, "isSecondFlag");
            return (Criteria) this;
        }

        public Criteria andIsSecondFlagIn(List<String> values) {
            addCriterion("IS_SECOND_FLAG in", values, "isSecondFlag");
            return (Criteria) this;
        }

        public Criteria andIsSecondFlagNotIn(List<String> values) {
            addCriterion("IS_SECOND_FLAG not in", values, "isSecondFlag");
            return (Criteria) this;
        }

        public Criteria andIsSecondFlagBetween(String value1, String value2) {
            addCriterion("IS_SECOND_FLAG between", value1, value2, "isSecondFlag");
            return (Criteria) this;
        }

        public Criteria andIsSecondFlagNotBetween(String value1, String value2) {
            addCriterion("IS_SECOND_FLAG not between", value1, value2, "isSecondFlag");
            return (Criteria) this;
        }

        public Criteria andIdentifyNoIsNull() {
            addCriterion("IDENTIFY_NO is null");
            return (Criteria) this;
        }

        public Criteria andIdentifyNoIsNotNull() {
            addCriterion("IDENTIFY_NO is not null");
            return (Criteria) this;
        }

        public Criteria andIdentifyNoEqualTo(String value) {
            addCriterion("IDENTIFY_NO =", value, "identifyNo");
            return (Criteria) this;
        }

        public Criteria andIdentifyNoNotEqualTo(String value) {
            addCriterion("IDENTIFY_NO <>", value, "identifyNo");
            return (Criteria) this;
        }

        public Criteria andIdentifyNoGreaterThan(String value) {
            addCriterion("IDENTIFY_NO >", value, "identifyNo");
            return (Criteria) this;
        }

        public Criteria andIdentifyNoGreaterThanOrEqualTo(String value) {
            addCriterion("IDENTIFY_NO >=", value, "identifyNo");
            return (Criteria) this;
        }

        public Criteria andIdentifyNoLessThan(String value) {
            addCriterion("IDENTIFY_NO <", value, "identifyNo");
            return (Criteria) this;
        }

        public Criteria andIdentifyNoLessThanOrEqualTo(String value) {
            addCriterion("IDENTIFY_NO <=", value, "identifyNo");
            return (Criteria) this;
        }

        public Criteria andIdentifyNoLike(String value) {
            addCriterion("IDENTIFY_NO like", value, "identifyNo");
            return (Criteria) this;
        }

        public Criteria andIdentifyNoNotLike(String value) {
            addCriterion("IDENTIFY_NO not like", value, "identifyNo");
            return (Criteria) this;
        }

        public Criteria andIdentifyNoIn(List<String> values) {
            addCriterion("IDENTIFY_NO in", values, "identifyNo");
            return (Criteria) this;
        }

        public Criteria andIdentifyNoNotIn(List<String> values) {
            addCriterion("IDENTIFY_NO not in", values, "identifyNo");
            return (Criteria) this;
        }

        public Criteria andIdentifyNoBetween(String value1, String value2) {
            addCriterion("IDENTIFY_NO between", value1, value2, "identifyNo");
            return (Criteria) this;
        }

        public Criteria andIdentifyNoNotBetween(String value1, String value2) {
            addCriterion("IDENTIFY_NO not between", value1, value2, "identifyNo");
            return (Criteria) this;
        }

        public Criteria andHospitalPasswordIsNull() {
            addCriterion("HOSPITAL_PASSWORD is null");
            return (Criteria) this;
        }

        public Criteria andHospitalPasswordIsNotNull() {
            addCriterion("HOSPITAL_PASSWORD is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalPasswordEqualTo(String value) {
            addCriterion("HOSPITAL_PASSWORD =", value, "hospitalPassword");
            return (Criteria) this;
        }

        public Criteria andHospitalPasswordNotEqualTo(String value) {
            addCriterion("HOSPITAL_PASSWORD <>", value, "hospitalPassword");
            return (Criteria) this;
        }

        public Criteria andHospitalPasswordGreaterThan(String value) {
            addCriterion("HOSPITAL_PASSWORD >", value, "hospitalPassword");
            return (Criteria) this;
        }

        public Criteria andHospitalPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_PASSWORD >=", value, "hospitalPassword");
            return (Criteria) this;
        }

        public Criteria andHospitalPasswordLessThan(String value) {
            addCriterion("HOSPITAL_PASSWORD <", value, "hospitalPassword");
            return (Criteria) this;
        }

        public Criteria andHospitalPasswordLessThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_PASSWORD <=", value, "hospitalPassword");
            return (Criteria) this;
        }

        public Criteria andHospitalPasswordLike(String value) {
            addCriterion("HOSPITAL_PASSWORD like", value, "hospitalPassword");
            return (Criteria) this;
        }

        public Criteria andHospitalPasswordNotLike(String value) {
            addCriterion("HOSPITAL_PASSWORD not like", value, "hospitalPassword");
            return (Criteria) this;
        }

        public Criteria andHospitalPasswordIn(List<String> values) {
            addCriterion("HOSPITAL_PASSWORD in", values, "hospitalPassword");
            return (Criteria) this;
        }

        public Criteria andHospitalPasswordNotIn(List<String> values) {
            addCriterion("HOSPITAL_PASSWORD not in", values, "hospitalPassword");
            return (Criteria) this;
        }

        public Criteria andHospitalPasswordBetween(String value1, String value2) {
            addCriterion("HOSPITAL_PASSWORD between", value1, value2, "hospitalPassword");
            return (Criteria) this;
        }

        public Criteria andHospitalPasswordNotBetween(String value1, String value2) {
            addCriterion("HOSPITAL_PASSWORD not between", value1, value2, "hospitalPassword");
            return (Criteria) this;
        }

        public Criteria andOsceDoctorShowIsNull() {
            addCriterion("OSCE_DOCTOR_SHOW is null");
            return (Criteria) this;
        }

        public Criteria andOsceDoctorShowIsNotNull() {
            addCriterion("OSCE_DOCTOR_SHOW is not null");
            return (Criteria) this;
        }

        public Criteria andOsceDoctorShowEqualTo(String value) {
            addCriterion("OSCE_DOCTOR_SHOW =", value, "osceDoctorShow");
            return (Criteria) this;
        }

        public Criteria andOsceDoctorShowNotEqualTo(String value) {
            addCriterion("OSCE_DOCTOR_SHOW <>", value, "osceDoctorShow");
            return (Criteria) this;
        }

        public Criteria andOsceDoctorShowGreaterThan(String value) {
            addCriterion("OSCE_DOCTOR_SHOW >", value, "osceDoctorShow");
            return (Criteria) this;
        }

        public Criteria andOsceDoctorShowGreaterThanOrEqualTo(String value) {
            addCriterion("OSCE_DOCTOR_SHOW >=", value, "osceDoctorShow");
            return (Criteria) this;
        }

        public Criteria andOsceDoctorShowLessThan(String value) {
            addCriterion("OSCE_DOCTOR_SHOW <", value, "osceDoctorShow");
            return (Criteria) this;
        }

        public Criteria andOsceDoctorShowLessThanOrEqualTo(String value) {
            addCriterion("OSCE_DOCTOR_SHOW <=", value, "osceDoctorShow");
            return (Criteria) this;
        }

        public Criteria andOsceDoctorShowLike(String value) {
            addCriterion("OSCE_DOCTOR_SHOW like", value, "osceDoctorShow");
            return (Criteria) this;
        }

        public Criteria andOsceDoctorShowNotLike(String value) {
            addCriterion("OSCE_DOCTOR_SHOW not like", value, "osceDoctorShow");
            return (Criteria) this;
        }

        public Criteria andOsceDoctorShowIn(List<String> values) {
            addCriterion("OSCE_DOCTOR_SHOW in", values, "osceDoctorShow");
            return (Criteria) this;
        }

        public Criteria andOsceDoctorShowNotIn(List<String> values) {
            addCriterion("OSCE_DOCTOR_SHOW not in", values, "osceDoctorShow");
            return (Criteria) this;
        }

        public Criteria andOsceDoctorShowBetween(String value1, String value2) {
            addCriterion("OSCE_DOCTOR_SHOW between", value1, value2, "osceDoctorShow");
            return (Criteria) this;
        }

        public Criteria andOsceDoctorShowNotBetween(String value1, String value2) {
            addCriterion("OSCE_DOCTOR_SHOW not between", value1, value2, "osceDoctorShow");
            return (Criteria) this;
        }

        public Criteria andOsceTeacherShowIsNull() {
            addCriterion("OSCE_TEACHER_SHOW is null");
            return (Criteria) this;
        }

        public Criteria andOsceTeacherShowIsNotNull() {
            addCriterion("OSCE_TEACHER_SHOW is not null");
            return (Criteria) this;
        }

        public Criteria andOsceTeacherShowEqualTo(String value) {
            addCriterion("OSCE_TEACHER_SHOW =", value, "osceTeacherShow");
            return (Criteria) this;
        }

        public Criteria andOsceTeacherShowNotEqualTo(String value) {
            addCriterion("OSCE_TEACHER_SHOW <>", value, "osceTeacherShow");
            return (Criteria) this;
        }

        public Criteria andOsceTeacherShowGreaterThan(String value) {
            addCriterion("OSCE_TEACHER_SHOW >", value, "osceTeacherShow");
            return (Criteria) this;
        }

        public Criteria andOsceTeacherShowGreaterThanOrEqualTo(String value) {
            addCriterion("OSCE_TEACHER_SHOW >=", value, "osceTeacherShow");
            return (Criteria) this;
        }

        public Criteria andOsceTeacherShowLessThan(String value) {
            addCriterion("OSCE_TEACHER_SHOW <", value, "osceTeacherShow");
            return (Criteria) this;
        }

        public Criteria andOsceTeacherShowLessThanOrEqualTo(String value) {
            addCriterion("OSCE_TEACHER_SHOW <=", value, "osceTeacherShow");
            return (Criteria) this;
        }

        public Criteria andOsceTeacherShowLike(String value) {
            addCriterion("OSCE_TEACHER_SHOW like", value, "osceTeacherShow");
            return (Criteria) this;
        }

        public Criteria andOsceTeacherShowNotLike(String value) {
            addCriterion("OSCE_TEACHER_SHOW not like", value, "osceTeacherShow");
            return (Criteria) this;
        }

        public Criteria andOsceTeacherShowIn(List<String> values) {
            addCriterion("OSCE_TEACHER_SHOW in", values, "osceTeacherShow");
            return (Criteria) this;
        }

        public Criteria andOsceTeacherShowNotIn(List<String> values) {
            addCriterion("OSCE_TEACHER_SHOW not in", values, "osceTeacherShow");
            return (Criteria) this;
        }

        public Criteria andOsceTeacherShowBetween(String value1, String value2) {
            addCriterion("OSCE_TEACHER_SHOW between", value1, value2, "osceTeacherShow");
            return (Criteria) this;
        }

        public Criteria andOsceTeacherShowNotBetween(String value1, String value2) {
            addCriterion("OSCE_TEACHER_SHOW not between", value1, value2, "osceTeacherShow");
            return (Criteria) this;
        }

        public Criteria andIsTrainOrgIsNull() {
            addCriterion("IS_TRAIN_ORG is null");
            return (Criteria) this;
        }

        public Criteria andIsTrainOrgIsNotNull() {
            addCriterion("IS_TRAIN_ORG is not null");
            return (Criteria) this;
        }

        public Criteria andIsTrainOrgEqualTo(String value) {
            addCriterion("IS_TRAIN_ORG =", value, "isTrainOrg");
            return (Criteria) this;
        }

        public Criteria andIsTrainOrgNotEqualTo(String value) {
            addCriterion("IS_TRAIN_ORG <>", value, "isTrainOrg");
            return (Criteria) this;
        }

        public Criteria andIsTrainOrgGreaterThan(String value) {
            addCriterion("IS_TRAIN_ORG >", value, "isTrainOrg");
            return (Criteria) this;
        }

        public Criteria andIsTrainOrgGreaterThanOrEqualTo(String value) {
            addCriterion("IS_TRAIN_ORG >=", value, "isTrainOrg");
            return (Criteria) this;
        }

        public Criteria andIsTrainOrgLessThan(String value) {
            addCriterion("IS_TRAIN_ORG <", value, "isTrainOrg");
            return (Criteria) this;
        }

        public Criteria andIsTrainOrgLessThanOrEqualTo(String value) {
            addCriterion("IS_TRAIN_ORG <=", value, "isTrainOrg");
            return (Criteria) this;
        }

        public Criteria andIsTrainOrgLike(String value) {
            addCriterion("IS_TRAIN_ORG like", value, "isTrainOrg");
            return (Criteria) this;
        }

        public Criteria andIsTrainOrgNotLike(String value) {
            addCriterion("IS_TRAIN_ORG not like", value, "isTrainOrg");
            return (Criteria) this;
        }

        public Criteria andIsTrainOrgIn(List<String> values) {
            addCriterion("IS_TRAIN_ORG in", values, "isTrainOrg");
            return (Criteria) this;
        }

        public Criteria andIsTrainOrgNotIn(List<String> values) {
            addCriterion("IS_TRAIN_ORG not in", values, "isTrainOrg");
            return (Criteria) this;
        }

        public Criteria andIsTrainOrgBetween(String value1, String value2) {
            addCriterion("IS_TRAIN_ORG between", value1, value2, "isTrainOrg");
            return (Criteria) this;
        }

        public Criteria andIsTrainOrgNotBetween(String value1, String value2) {
            addCriterion("IS_TRAIN_ORG not between", value1, value2, "isTrainOrg");
            return (Criteria) this;
        }

        public Criteria andCreditCodeIsNull() {
            addCriterion("CREDIT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCreditCodeIsNotNull() {
            addCriterion("CREDIT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCreditCodeEqualTo(String value) {
            addCriterion("CREDIT_CODE =", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeNotEqualTo(String value) {
            addCriterion("CREDIT_CODE <>", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeGreaterThan(String value) {
            addCriterion("CREDIT_CODE >", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CREDIT_CODE >=", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeLessThan(String value) {
            addCriterion("CREDIT_CODE <", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeLessThanOrEqualTo(String value) {
            addCriterion("CREDIT_CODE <=", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeLike(String value) {
            addCriterion("CREDIT_CODE like", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeNotLike(String value) {
            addCriterion("CREDIT_CODE not like", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeIn(List<String> values) {
            addCriterion("CREDIT_CODE in", values, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeNotIn(List<String> values) {
            addCriterion("CREDIT_CODE not in", values, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeBetween(String value1, String value2) {
            addCriterion("CREDIT_CODE between", value1, value2, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeNotBetween(String value1, String value2) {
            addCriterion("CREDIT_CODE not between", value1, value2, "creditCode");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdIsNull() {
            addCriterion("IS_SUBMIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdIsNotNull() {
            addCriterion("IS_SUBMIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdEqualTo(String value) {
            addCriterion("IS_SUBMIT_ID =", value, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdNotEqualTo(String value) {
            addCriterion("IS_SUBMIT_ID <>", value, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdGreaterThan(String value) {
            addCriterion("IS_SUBMIT_ID >", value, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SUBMIT_ID >=", value, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdLessThan(String value) {
            addCriterion("IS_SUBMIT_ID <", value, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdLessThanOrEqualTo(String value) {
            addCriterion("IS_SUBMIT_ID <=", value, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdLike(String value) {
            addCriterion("IS_SUBMIT_ID like", value, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdNotLike(String value) {
            addCriterion("IS_SUBMIT_ID not like", value, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdIn(List<String> values) {
            addCriterion("IS_SUBMIT_ID in", values, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdNotIn(List<String> values) {
            addCriterion("IS_SUBMIT_ID not in", values, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdBetween(String value1, String value2) {
            addCriterion("IS_SUBMIT_ID between", value1, value2, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdNotBetween(String value1, String value2) {
            addCriterion("IS_SUBMIT_ID not between", value1, value2, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameIsNull() {
            addCriterion("IS_SUBMIT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameIsNotNull() {
            addCriterion("IS_SUBMIT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameEqualTo(String value) {
            addCriterion("IS_SUBMIT_NAME =", value, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameNotEqualTo(String value) {
            addCriterion("IS_SUBMIT_NAME <>", value, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameGreaterThan(String value) {
            addCriterion("IS_SUBMIT_NAME >", value, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SUBMIT_NAME >=", value, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameLessThan(String value) {
            addCriterion("IS_SUBMIT_NAME <", value, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameLessThanOrEqualTo(String value) {
            addCriterion("IS_SUBMIT_NAME <=", value, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameLike(String value) {
            addCriterion("IS_SUBMIT_NAME like", value, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameNotLike(String value) {
            addCriterion("IS_SUBMIT_NAME not like", value, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameIn(List<String> values) {
            addCriterion("IS_SUBMIT_NAME in", values, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameNotIn(List<String> values) {
            addCriterion("IS_SUBMIT_NAME not in", values, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameBetween(String value1, String value2) {
            addCriterion("IS_SUBMIT_NAME between", value1, value2, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameNotBetween(String value1, String value2) {
            addCriterion("IS_SUBMIT_NAME not between", value1, value2, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdIsNull() {
            addCriterion("CHECK_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdIsNotNull() {
            addCriterion("CHECK_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdEqualTo(String value) {
            addCriterion("CHECK_STATUS_ID =", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdNotEqualTo(String value) {
            addCriterion("CHECK_STATUS_ID <>", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdGreaterThan(String value) {
            addCriterion("CHECK_STATUS_ID >", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS_ID >=", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdLessThan(String value) {
            addCriterion("CHECK_STATUS_ID <", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdLessThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS_ID <=", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdLike(String value) {
            addCriterion("CHECK_STATUS_ID like", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdNotLike(String value) {
            addCriterion("CHECK_STATUS_ID not like", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdIn(List<String> values) {
            addCriterion("CHECK_STATUS_ID in", values, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdNotIn(List<String> values) {
            addCriterion("CHECK_STATUS_ID not in", values, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS_ID between", value1, value2, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdNotBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS_ID not between", value1, value2, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameIsNull() {
            addCriterion("CHECK_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameIsNotNull() {
            addCriterion("CHECK_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameEqualTo(String value) {
            addCriterion("CHECK_STATUS_NAME =", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameNotEqualTo(String value) {
            addCriterion("CHECK_STATUS_NAME <>", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameGreaterThan(String value) {
            addCriterion("CHECK_STATUS_NAME >", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS_NAME >=", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameLessThan(String value) {
            addCriterion("CHECK_STATUS_NAME <", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameLessThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS_NAME <=", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameLike(String value) {
            addCriterion("CHECK_STATUS_NAME like", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameNotLike(String value) {
            addCriterion("CHECK_STATUS_NAME not like", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameIn(List<String> values) {
            addCriterion("CHECK_STATUS_NAME in", values, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameNotIn(List<String> values) {
            addCriterion("CHECK_STATUS_NAME not in", values, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS_NAME between", value1, value2, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameNotBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS_NAME not between", value1, value2, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckReasonIsNull() {
            addCriterion("CHECK_REASON is null");
            return (Criteria) this;
        }

        public Criteria andCheckReasonIsNotNull() {
            addCriterion("CHECK_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andCheckReasonEqualTo(String value) {
            addCriterion("CHECK_REASON =", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonNotEqualTo(String value) {
            addCriterion("CHECK_REASON <>", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonGreaterThan(String value) {
            addCriterion("CHECK_REASON >", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_REASON >=", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonLessThan(String value) {
            addCriterion("CHECK_REASON <", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonLessThanOrEqualTo(String value) {
            addCriterion("CHECK_REASON <=", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonLike(String value) {
            addCriterion("CHECK_REASON like", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonNotLike(String value) {
            addCriterion("CHECK_REASON not like", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonIn(List<String> values) {
            addCriterion("CHECK_REASON in", values, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonNotIn(List<String> values) {
            addCriterion("CHECK_REASON not in", values, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonBetween(String value1, String value2) {
            addCriterion("CHECK_REASON between", value1, value2, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonNotBetween(String value1, String value2) {
            addCriterion("CHECK_REASON not between", value1, value2, "checkReason");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameIsNull() {
            addCriterion("OLD_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameIsNotNull() {
            addCriterion("OLD_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameEqualTo(String value) {
            addCriterion("OLD_ORG_NAME =", value, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameNotEqualTo(String value) {
            addCriterion("OLD_ORG_NAME <>", value, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameGreaterThan(String value) {
            addCriterion("OLD_ORG_NAME >", value, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("OLD_ORG_NAME >=", value, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameLessThan(String value) {
            addCriterion("OLD_ORG_NAME <", value, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameLessThanOrEqualTo(String value) {
            addCriterion("OLD_ORG_NAME <=", value, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameLike(String value) {
            addCriterion("OLD_ORG_NAME like", value, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameNotLike(String value) {
            addCriterion("OLD_ORG_NAME not like", value, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameIn(List<String> values) {
            addCriterion("OLD_ORG_NAME in", values, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameNotIn(List<String> values) {
            addCriterion("OLD_ORG_NAME not in", values, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameBetween(String value1, String value2) {
            addCriterion("OLD_ORG_NAME between", value1, value2, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameNotBetween(String value1, String value2) {
            addCriterion("OLD_ORG_NAME not between", value1, value2, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andBaseCodeIsNull() {
            addCriterion("BASE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andBaseCodeIsNotNull() {
            addCriterion("BASE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andBaseCodeEqualTo(String value) {
            addCriterion("BASE_CODE =", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeNotEqualTo(String value) {
            addCriterion("BASE_CODE <>", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeGreaterThan(String value) {
            addCriterion("BASE_CODE >", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_CODE >=", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeLessThan(String value) {
            addCriterion("BASE_CODE <", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeLessThanOrEqualTo(String value) {
            addCriterion("BASE_CODE <=", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeLike(String value) {
            addCriterion("BASE_CODE like", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeNotLike(String value) {
            addCriterion("BASE_CODE not like", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeIn(List<String> values) {
            addCriterion("BASE_CODE in", values, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeNotIn(List<String> values) {
            addCriterion("BASE_CODE not in", values, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeBetween(String value1, String value2) {
            addCriterion("BASE_CODE between", value1, value2, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeNotBetween(String value1, String value2) {
            addCriterion("BASE_CODE not between", value1, value2, "baseCode");
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