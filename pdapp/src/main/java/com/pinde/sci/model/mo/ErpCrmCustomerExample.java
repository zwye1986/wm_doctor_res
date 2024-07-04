package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ErpCrmCustomerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ErpCrmCustomerExample() {
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

        public Criteria andCustomerFlowIsNull() {
            addCriterion("CUSTOMER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowIsNotNull() {
            addCriterion("CUSTOMER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowEqualTo(String value) {
            addCriterion("CUSTOMER_FLOW =", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowNotEqualTo(String value) {
            addCriterion("CUSTOMER_FLOW <>", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowGreaterThan(String value) {
            addCriterion("CUSTOMER_FLOW >", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_FLOW >=", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowLessThan(String value) {
            addCriterion("CUSTOMER_FLOW <", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_FLOW <=", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowLike(String value) {
            addCriterion("CUSTOMER_FLOW like", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowNotLike(String value) {
            addCriterion("CUSTOMER_FLOW not like", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowIn(List<String> values) {
            addCriterion("CUSTOMER_FLOW in", values, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowNotIn(List<String> values) {
            addCriterion("CUSTOMER_FLOW not in", values, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowBetween(String value1, String value2) {
            addCriterion("CUSTOMER_FLOW between", value1, value2, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_FLOW not between", value1, value2, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNull() {
            addCriterion("CUSTOMER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("CUSTOMER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("CUSTOMER_NAME =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("CUSTOMER_NAME <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("CUSTOMER_NAME >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_NAME >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("CUSTOMER_NAME <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_NAME <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("CUSTOMER_NAME like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("CUSTOMER_NAME not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("CUSTOMER_NAME in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("CUSTOMER_NAME not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("CUSTOMER_NAME between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_NAME not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andAliasNameIsNull() {
            addCriterion("ALIAS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAliasNameIsNotNull() {
            addCriterion("ALIAS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAliasNameEqualTo(String value) {
            addCriterion("ALIAS_NAME =", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameNotEqualTo(String value) {
            addCriterion("ALIAS_NAME <>", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameGreaterThan(String value) {
            addCriterion("ALIAS_NAME >", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameGreaterThanOrEqualTo(String value) {
            addCriterion("ALIAS_NAME >=", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameLessThan(String value) {
            addCriterion("ALIAS_NAME <", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameLessThanOrEqualTo(String value) {
            addCriterion("ALIAS_NAME <=", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameLike(String value) {
            addCriterion("ALIAS_NAME like", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameNotLike(String value) {
            addCriterion("ALIAS_NAME not like", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameIn(List<String> values) {
            addCriterion("ALIAS_NAME in", values, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameNotIn(List<String> values) {
            addCriterion("ALIAS_NAME not in", values, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameBetween(String value1, String value2) {
            addCriterion("ALIAS_NAME between", value1, value2, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameNotBetween(String value1, String value2) {
            addCriterion("ALIAS_NAME not between", value1, value2, "aliasName");
            return (Criteria) this;
        }

        public Criteria andCustomerProvIdIsNull() {
            addCriterion("CUSTOMER_PROV_ID is null");
            return (Criteria) this;
        }

        public Criteria andCustomerProvIdIsNotNull() {
            addCriterion("CUSTOMER_PROV_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerProvIdEqualTo(String value) {
            addCriterion("CUSTOMER_PROV_ID =", value, "customerProvId");
            return (Criteria) this;
        }

        public Criteria andCustomerProvIdNotEqualTo(String value) {
            addCriterion("CUSTOMER_PROV_ID <>", value, "customerProvId");
            return (Criteria) this;
        }

        public Criteria andCustomerProvIdGreaterThan(String value) {
            addCriterion("CUSTOMER_PROV_ID >", value, "customerProvId");
            return (Criteria) this;
        }

        public Criteria andCustomerProvIdGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_PROV_ID >=", value, "customerProvId");
            return (Criteria) this;
        }

        public Criteria andCustomerProvIdLessThan(String value) {
            addCriterion("CUSTOMER_PROV_ID <", value, "customerProvId");
            return (Criteria) this;
        }

        public Criteria andCustomerProvIdLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_PROV_ID <=", value, "customerProvId");
            return (Criteria) this;
        }

        public Criteria andCustomerProvIdLike(String value) {
            addCriterion("CUSTOMER_PROV_ID like", value, "customerProvId");
            return (Criteria) this;
        }

        public Criteria andCustomerProvIdNotLike(String value) {
            addCriterion("CUSTOMER_PROV_ID not like", value, "customerProvId");
            return (Criteria) this;
        }

        public Criteria andCustomerProvIdIn(List<String> values) {
            addCriterion("CUSTOMER_PROV_ID in", values, "customerProvId");
            return (Criteria) this;
        }

        public Criteria andCustomerProvIdNotIn(List<String> values) {
            addCriterion("CUSTOMER_PROV_ID not in", values, "customerProvId");
            return (Criteria) this;
        }

        public Criteria andCustomerProvIdBetween(String value1, String value2) {
            addCriterion("CUSTOMER_PROV_ID between", value1, value2, "customerProvId");
            return (Criteria) this;
        }

        public Criteria andCustomerProvIdNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_PROV_ID not between", value1, value2, "customerProvId");
            return (Criteria) this;
        }

        public Criteria andCustomerProvNameIsNull() {
            addCriterion("CUSTOMER_PROV_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCustomerProvNameIsNotNull() {
            addCriterion("CUSTOMER_PROV_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerProvNameEqualTo(String value) {
            addCriterion("CUSTOMER_PROV_NAME =", value, "customerProvName");
            return (Criteria) this;
        }

        public Criteria andCustomerProvNameNotEqualTo(String value) {
            addCriterion("CUSTOMER_PROV_NAME <>", value, "customerProvName");
            return (Criteria) this;
        }

        public Criteria andCustomerProvNameGreaterThan(String value) {
            addCriterion("CUSTOMER_PROV_NAME >", value, "customerProvName");
            return (Criteria) this;
        }

        public Criteria andCustomerProvNameGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_PROV_NAME >=", value, "customerProvName");
            return (Criteria) this;
        }

        public Criteria andCustomerProvNameLessThan(String value) {
            addCriterion("CUSTOMER_PROV_NAME <", value, "customerProvName");
            return (Criteria) this;
        }

        public Criteria andCustomerProvNameLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_PROV_NAME <=", value, "customerProvName");
            return (Criteria) this;
        }

        public Criteria andCustomerProvNameLike(String value) {
            addCriterion("CUSTOMER_PROV_NAME like", value, "customerProvName");
            return (Criteria) this;
        }

        public Criteria andCustomerProvNameNotLike(String value) {
            addCriterion("CUSTOMER_PROV_NAME not like", value, "customerProvName");
            return (Criteria) this;
        }

        public Criteria andCustomerProvNameIn(List<String> values) {
            addCriterion("CUSTOMER_PROV_NAME in", values, "customerProvName");
            return (Criteria) this;
        }

        public Criteria andCustomerProvNameNotIn(List<String> values) {
            addCriterion("CUSTOMER_PROV_NAME not in", values, "customerProvName");
            return (Criteria) this;
        }

        public Criteria andCustomerProvNameBetween(String value1, String value2) {
            addCriterion("CUSTOMER_PROV_NAME between", value1, value2, "customerProvName");
            return (Criteria) this;
        }

        public Criteria andCustomerProvNameNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_PROV_NAME not between", value1, value2, "customerProvName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityIdIsNull() {
            addCriterion("CUSTOMER_CITY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCustomerCityIdIsNotNull() {
            addCriterion("CUSTOMER_CITY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerCityIdEqualTo(String value) {
            addCriterion("CUSTOMER_CITY_ID =", value, "customerCityId");
            return (Criteria) this;
        }

        public Criteria andCustomerCityIdNotEqualTo(String value) {
            addCriterion("CUSTOMER_CITY_ID <>", value, "customerCityId");
            return (Criteria) this;
        }

        public Criteria andCustomerCityIdGreaterThan(String value) {
            addCriterion("CUSTOMER_CITY_ID >", value, "customerCityId");
            return (Criteria) this;
        }

        public Criteria andCustomerCityIdGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_CITY_ID >=", value, "customerCityId");
            return (Criteria) this;
        }

        public Criteria andCustomerCityIdLessThan(String value) {
            addCriterion("CUSTOMER_CITY_ID <", value, "customerCityId");
            return (Criteria) this;
        }

        public Criteria andCustomerCityIdLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_CITY_ID <=", value, "customerCityId");
            return (Criteria) this;
        }

        public Criteria andCustomerCityIdLike(String value) {
            addCriterion("CUSTOMER_CITY_ID like", value, "customerCityId");
            return (Criteria) this;
        }

        public Criteria andCustomerCityIdNotLike(String value) {
            addCriterion("CUSTOMER_CITY_ID not like", value, "customerCityId");
            return (Criteria) this;
        }

        public Criteria andCustomerCityIdIn(List<String> values) {
            addCriterion("CUSTOMER_CITY_ID in", values, "customerCityId");
            return (Criteria) this;
        }

        public Criteria andCustomerCityIdNotIn(List<String> values) {
            addCriterion("CUSTOMER_CITY_ID not in", values, "customerCityId");
            return (Criteria) this;
        }

        public Criteria andCustomerCityIdBetween(String value1, String value2) {
            addCriterion("CUSTOMER_CITY_ID between", value1, value2, "customerCityId");
            return (Criteria) this;
        }

        public Criteria andCustomerCityIdNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_CITY_ID not between", value1, value2, "customerCityId");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameIsNull() {
            addCriterion("CUSTOMER_CITY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameIsNotNull() {
            addCriterion("CUSTOMER_CITY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameEqualTo(String value) {
            addCriterion("CUSTOMER_CITY_NAME =", value, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameNotEqualTo(String value) {
            addCriterion("CUSTOMER_CITY_NAME <>", value, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameGreaterThan(String value) {
            addCriterion("CUSTOMER_CITY_NAME >", value, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_CITY_NAME >=", value, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameLessThan(String value) {
            addCriterion("CUSTOMER_CITY_NAME <", value, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_CITY_NAME <=", value, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameLike(String value) {
            addCriterion("CUSTOMER_CITY_NAME like", value, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameNotLike(String value) {
            addCriterion("CUSTOMER_CITY_NAME not like", value, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameIn(List<String> values) {
            addCriterion("CUSTOMER_CITY_NAME in", values, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameNotIn(List<String> values) {
            addCriterion("CUSTOMER_CITY_NAME not in", values, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameBetween(String value1, String value2) {
            addCriterion("CUSTOMER_CITY_NAME between", value1, value2, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_CITY_NAME not between", value1, value2, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaIdIsNull() {
            addCriterion("CUSTOMER_AREA_ID is null");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaIdIsNotNull() {
            addCriterion("CUSTOMER_AREA_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaIdEqualTo(String value) {
            addCriterion("CUSTOMER_AREA_ID =", value, "customerAreaId");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaIdNotEqualTo(String value) {
            addCriterion("CUSTOMER_AREA_ID <>", value, "customerAreaId");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaIdGreaterThan(String value) {
            addCriterion("CUSTOMER_AREA_ID >", value, "customerAreaId");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaIdGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_AREA_ID >=", value, "customerAreaId");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaIdLessThan(String value) {
            addCriterion("CUSTOMER_AREA_ID <", value, "customerAreaId");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaIdLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_AREA_ID <=", value, "customerAreaId");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaIdLike(String value) {
            addCriterion("CUSTOMER_AREA_ID like", value, "customerAreaId");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaIdNotLike(String value) {
            addCriterion("CUSTOMER_AREA_ID not like", value, "customerAreaId");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaIdIn(List<String> values) {
            addCriterion("CUSTOMER_AREA_ID in", values, "customerAreaId");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaIdNotIn(List<String> values) {
            addCriterion("CUSTOMER_AREA_ID not in", values, "customerAreaId");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaIdBetween(String value1, String value2) {
            addCriterion("CUSTOMER_AREA_ID between", value1, value2, "customerAreaId");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaIdNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_AREA_ID not between", value1, value2, "customerAreaId");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaNameIsNull() {
            addCriterion("CUSTOMER_AREA_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaNameIsNotNull() {
            addCriterion("CUSTOMER_AREA_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaNameEqualTo(String value) {
            addCriterion("CUSTOMER_AREA_NAME =", value, "customerAreaName");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaNameNotEqualTo(String value) {
            addCriterion("CUSTOMER_AREA_NAME <>", value, "customerAreaName");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaNameGreaterThan(String value) {
            addCriterion("CUSTOMER_AREA_NAME >", value, "customerAreaName");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_AREA_NAME >=", value, "customerAreaName");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaNameLessThan(String value) {
            addCriterion("CUSTOMER_AREA_NAME <", value, "customerAreaName");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaNameLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_AREA_NAME <=", value, "customerAreaName");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaNameLike(String value) {
            addCriterion("CUSTOMER_AREA_NAME like", value, "customerAreaName");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaNameNotLike(String value) {
            addCriterion("CUSTOMER_AREA_NAME not like", value, "customerAreaName");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaNameIn(List<String> values) {
            addCriterion("CUSTOMER_AREA_NAME in", values, "customerAreaName");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaNameNotIn(List<String> values) {
            addCriterion("CUSTOMER_AREA_NAME not in", values, "customerAreaName");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaNameBetween(String value1, String value2) {
            addCriterion("CUSTOMER_AREA_NAME between", value1, value2, "customerAreaName");
            return (Criteria) this;
        }

        public Criteria andCustomerAreaNameNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_AREA_NAME not between", value1, value2, "customerAreaName");
            return (Criteria) this;
        }

        public Criteria andCustomerSiteIsNull() {
            addCriterion("CUSTOMER_SITE is null");
            return (Criteria) this;
        }

        public Criteria andCustomerSiteIsNotNull() {
            addCriterion("CUSTOMER_SITE is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerSiteEqualTo(String value) {
            addCriterion("CUSTOMER_SITE =", value, "customerSite");
            return (Criteria) this;
        }

        public Criteria andCustomerSiteNotEqualTo(String value) {
            addCriterion("CUSTOMER_SITE <>", value, "customerSite");
            return (Criteria) this;
        }

        public Criteria andCustomerSiteGreaterThan(String value) {
            addCriterion("CUSTOMER_SITE >", value, "customerSite");
            return (Criteria) this;
        }

        public Criteria andCustomerSiteGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_SITE >=", value, "customerSite");
            return (Criteria) this;
        }

        public Criteria andCustomerSiteLessThan(String value) {
            addCriterion("CUSTOMER_SITE <", value, "customerSite");
            return (Criteria) this;
        }

        public Criteria andCustomerSiteLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_SITE <=", value, "customerSite");
            return (Criteria) this;
        }

        public Criteria andCustomerSiteLike(String value) {
            addCriterion("CUSTOMER_SITE like", value, "customerSite");
            return (Criteria) this;
        }

        public Criteria andCustomerSiteNotLike(String value) {
            addCriterion("CUSTOMER_SITE not like", value, "customerSite");
            return (Criteria) this;
        }

        public Criteria andCustomerSiteIn(List<String> values) {
            addCriterion("CUSTOMER_SITE in", values, "customerSite");
            return (Criteria) this;
        }

        public Criteria andCustomerSiteNotIn(List<String> values) {
            addCriterion("CUSTOMER_SITE not in", values, "customerSite");
            return (Criteria) this;
        }

        public Criteria andCustomerSiteBetween(String value1, String value2) {
            addCriterion("CUSTOMER_SITE between", value1, value2, "customerSite");
            return (Criteria) this;
        }

        public Criteria andCustomerSiteNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_SITE not between", value1, value2, "customerSite");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressIsNull() {
            addCriterion("CUSTOMER_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressIsNotNull() {
            addCriterion("CUSTOMER_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressEqualTo(String value) {
            addCriterion("CUSTOMER_ADDRESS =", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotEqualTo(String value) {
            addCriterion("CUSTOMER_ADDRESS <>", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressGreaterThan(String value) {
            addCriterion("CUSTOMER_ADDRESS >", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_ADDRESS >=", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressLessThan(String value) {
            addCriterion("CUSTOMER_ADDRESS <", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_ADDRESS <=", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressLike(String value) {
            addCriterion("CUSTOMER_ADDRESS like", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotLike(String value) {
            addCriterion("CUSTOMER_ADDRESS not like", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressIn(List<String> values) {
            addCriterion("CUSTOMER_ADDRESS in", values, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotIn(List<String> values) {
            addCriterion("CUSTOMER_ADDRESS not in", values, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressBetween(String value1, String value2) {
            addCriterion("CUSTOMER_ADDRESS between", value1, value2, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_ADDRESS not between", value1, value2, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andZipCodeIsNull() {
            addCriterion("ZIP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andZipCodeIsNotNull() {
            addCriterion("ZIP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andZipCodeEqualTo(String value) {
            addCriterion("ZIP_CODE =", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotEqualTo(String value) {
            addCriterion("ZIP_CODE <>", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeGreaterThan(String value) {
            addCriterion("ZIP_CODE >", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ZIP_CODE >=", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLessThan(String value) {
            addCriterion("ZIP_CODE <", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLessThanOrEqualTo(String value) {
            addCriterion("ZIP_CODE <=", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLike(String value) {
            addCriterion("ZIP_CODE like", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotLike(String value) {
            addCriterion("ZIP_CODE not like", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeIn(List<String> values) {
            addCriterion("ZIP_CODE in", values, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotIn(List<String> values) {
            addCriterion("ZIP_CODE not in", values, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeBetween(String value1, String value2) {
            addCriterion("ZIP_CODE between", value1, value2, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotBetween(String value1, String value2) {
            addCriterion("ZIP_CODE not between", value1, value2, "zipCode");
            return (Criteria) this;
        }

        public Criteria andTelPhoneIsNull() {
            addCriterion("TEL_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andTelPhoneIsNotNull() {
            addCriterion("TEL_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andTelPhoneEqualTo(String value) {
            addCriterion("TEL_PHONE =", value, "telPhone");
            return (Criteria) this;
        }

        public Criteria andTelPhoneNotEqualTo(String value) {
            addCriterion("TEL_PHONE <>", value, "telPhone");
            return (Criteria) this;
        }

        public Criteria andTelPhoneGreaterThan(String value) {
            addCriterion("TEL_PHONE >", value, "telPhone");
            return (Criteria) this;
        }

        public Criteria andTelPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("TEL_PHONE >=", value, "telPhone");
            return (Criteria) this;
        }

        public Criteria andTelPhoneLessThan(String value) {
            addCriterion("TEL_PHONE <", value, "telPhone");
            return (Criteria) this;
        }

        public Criteria andTelPhoneLessThanOrEqualTo(String value) {
            addCriterion("TEL_PHONE <=", value, "telPhone");
            return (Criteria) this;
        }

        public Criteria andTelPhoneLike(String value) {
            addCriterion("TEL_PHONE like", value, "telPhone");
            return (Criteria) this;
        }

        public Criteria andTelPhoneNotLike(String value) {
            addCriterion("TEL_PHONE not like", value, "telPhone");
            return (Criteria) this;
        }

        public Criteria andTelPhoneIn(List<String> values) {
            addCriterion("TEL_PHONE in", values, "telPhone");
            return (Criteria) this;
        }

        public Criteria andTelPhoneNotIn(List<String> values) {
            addCriterion("TEL_PHONE not in", values, "telPhone");
            return (Criteria) this;
        }

        public Criteria andTelPhoneBetween(String value1, String value2) {
            addCriterion("TEL_PHONE between", value1, value2, "telPhone");
            return (Criteria) this;
        }

        public Criteria andTelPhoneNotBetween(String value1, String value2) {
            addCriterion("TEL_PHONE not between", value1, value2, "telPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIdIsNull() {
            addCriterion("CUSTOMER_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIdIsNotNull() {
            addCriterion("CUSTOMER_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIdEqualTo(String value) {
            addCriterion("CUSTOMER_TYPE_ID =", value, "customerTypeId");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIdNotEqualTo(String value) {
            addCriterion("CUSTOMER_TYPE_ID <>", value, "customerTypeId");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIdGreaterThan(String value) {
            addCriterion("CUSTOMER_TYPE_ID >", value, "customerTypeId");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_TYPE_ID >=", value, "customerTypeId");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIdLessThan(String value) {
            addCriterion("CUSTOMER_TYPE_ID <", value, "customerTypeId");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIdLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_TYPE_ID <=", value, "customerTypeId");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIdLike(String value) {
            addCriterion("CUSTOMER_TYPE_ID like", value, "customerTypeId");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIdNotLike(String value) {
            addCriterion("CUSTOMER_TYPE_ID not like", value, "customerTypeId");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIdIn(List<String> values) {
            addCriterion("CUSTOMER_TYPE_ID in", values, "customerTypeId");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIdNotIn(List<String> values) {
            addCriterion("CUSTOMER_TYPE_ID not in", values, "customerTypeId");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIdBetween(String value1, String value2) {
            addCriterion("CUSTOMER_TYPE_ID between", value1, value2, "customerTypeId");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeIdNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_TYPE_ID not between", value1, value2, "customerTypeId");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNameIsNull() {
            addCriterion("CUSTOMER_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNameIsNotNull() {
            addCriterion("CUSTOMER_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNameEqualTo(String value) {
            addCriterion("CUSTOMER_TYPE_NAME =", value, "customerTypeName");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNameNotEqualTo(String value) {
            addCriterion("CUSTOMER_TYPE_NAME <>", value, "customerTypeName");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNameGreaterThan(String value) {
            addCriterion("CUSTOMER_TYPE_NAME >", value, "customerTypeName");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_TYPE_NAME >=", value, "customerTypeName");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNameLessThan(String value) {
            addCriterion("CUSTOMER_TYPE_NAME <", value, "customerTypeName");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNameLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_TYPE_NAME <=", value, "customerTypeName");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNameLike(String value) {
            addCriterion("CUSTOMER_TYPE_NAME like", value, "customerTypeName");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNameNotLike(String value) {
            addCriterion("CUSTOMER_TYPE_NAME not like", value, "customerTypeName");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNameIn(List<String> values) {
            addCriterion("CUSTOMER_TYPE_NAME in", values, "customerTypeName");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNameNotIn(List<String> values) {
            addCriterion("CUSTOMER_TYPE_NAME not in", values, "customerTypeName");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNameBetween(String value1, String value2) {
            addCriterion("CUSTOMER_TYPE_NAME between", value1, value2, "customerTypeName");
            return (Criteria) this;
        }

        public Criteria andCustomerTypeNameNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_TYPE_NAME not between", value1, value2, "customerTypeName");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeIdIsNull() {
            addCriterion("HOSPITAL_GRADE_ID is null");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeIdIsNotNull() {
            addCriterion("HOSPITAL_GRADE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeIdEqualTo(String value) {
            addCriterion("HOSPITAL_GRADE_ID =", value, "hospitalGradeId");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeIdNotEqualTo(String value) {
            addCriterion("HOSPITAL_GRADE_ID <>", value, "hospitalGradeId");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeIdGreaterThan(String value) {
            addCriterion("HOSPITAL_GRADE_ID >", value, "hospitalGradeId");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeIdGreaterThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_GRADE_ID >=", value, "hospitalGradeId");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeIdLessThan(String value) {
            addCriterion("HOSPITAL_GRADE_ID <", value, "hospitalGradeId");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeIdLessThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_GRADE_ID <=", value, "hospitalGradeId");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeIdLike(String value) {
            addCriterion("HOSPITAL_GRADE_ID like", value, "hospitalGradeId");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeIdNotLike(String value) {
            addCriterion("HOSPITAL_GRADE_ID not like", value, "hospitalGradeId");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeIdIn(List<String> values) {
            addCriterion("HOSPITAL_GRADE_ID in", values, "hospitalGradeId");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeIdNotIn(List<String> values) {
            addCriterion("HOSPITAL_GRADE_ID not in", values, "hospitalGradeId");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeIdBetween(String value1, String value2) {
            addCriterion("HOSPITAL_GRADE_ID between", value1, value2, "hospitalGradeId");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeIdNotBetween(String value1, String value2) {
            addCriterion("HOSPITAL_GRADE_ID not between", value1, value2, "hospitalGradeId");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeNameIsNull() {
            addCriterion("HOSPITAL_GRADE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeNameIsNotNull() {
            addCriterion("HOSPITAL_GRADE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeNameEqualTo(String value) {
            addCriterion("HOSPITAL_GRADE_NAME =", value, "hospitalGradeName");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeNameNotEqualTo(String value) {
            addCriterion("HOSPITAL_GRADE_NAME <>", value, "hospitalGradeName");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeNameGreaterThan(String value) {
            addCriterion("HOSPITAL_GRADE_NAME >", value, "hospitalGradeName");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeNameGreaterThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_GRADE_NAME >=", value, "hospitalGradeName");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeNameLessThan(String value) {
            addCriterion("HOSPITAL_GRADE_NAME <", value, "hospitalGradeName");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeNameLessThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_GRADE_NAME <=", value, "hospitalGradeName");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeNameLike(String value) {
            addCriterion("HOSPITAL_GRADE_NAME like", value, "hospitalGradeName");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeNameNotLike(String value) {
            addCriterion("HOSPITAL_GRADE_NAME not like", value, "hospitalGradeName");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeNameIn(List<String> values) {
            addCriterion("HOSPITAL_GRADE_NAME in", values, "hospitalGradeName");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeNameNotIn(List<String> values) {
            addCriterion("HOSPITAL_GRADE_NAME not in", values, "hospitalGradeName");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeNameBetween(String value1, String value2) {
            addCriterion("HOSPITAL_GRADE_NAME between", value1, value2, "hospitalGradeName");
            return (Criteria) this;
        }

        public Criteria andHospitalGradeNameNotBetween(String value1, String value2) {
            addCriterion("HOSPITAL_GRADE_NAME not between", value1, value2, "hospitalGradeName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdIsNull() {
            addCriterion("HOSPITAL_LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdIsNotNull() {
            addCriterion("HOSPITAL_LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL_ID =", value, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdNotEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL_ID <>", value, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdGreaterThan(String value) {
            addCriterion("HOSPITAL_LEVEL_ID >", value, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL_ID >=", value, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdLessThan(String value) {
            addCriterion("HOSPITAL_LEVEL_ID <", value, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdLessThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL_ID <=", value, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdLike(String value) {
            addCriterion("HOSPITAL_LEVEL_ID like", value, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdNotLike(String value) {
            addCriterion("HOSPITAL_LEVEL_ID not like", value, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdIn(List<String> values) {
            addCriterion("HOSPITAL_LEVEL_ID in", values, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdNotIn(List<String> values) {
            addCriterion("HOSPITAL_LEVEL_ID not in", values, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdBetween(String value1, String value2) {
            addCriterion("HOSPITAL_LEVEL_ID between", value1, value2, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdNotBetween(String value1, String value2) {
            addCriterion("HOSPITAL_LEVEL_ID not between", value1, value2, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameIsNull() {
            addCriterion("HOSPITAL_LEVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameIsNotNull() {
            addCriterion("HOSPITAL_LEVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL_NAME =", value, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameNotEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL_NAME <>", value, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameGreaterThan(String value) {
            addCriterion("HOSPITAL_LEVEL_NAME >", value, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL_NAME >=", value, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameLessThan(String value) {
            addCriterion("HOSPITAL_LEVEL_NAME <", value, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameLessThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL_NAME <=", value, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameLike(String value) {
            addCriterion("HOSPITAL_LEVEL_NAME like", value, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameNotLike(String value) {
            addCriterion("HOSPITAL_LEVEL_NAME not like", value, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameIn(List<String> values) {
            addCriterion("HOSPITAL_LEVEL_NAME in", values, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameNotIn(List<String> values) {
            addCriterion("HOSPITAL_LEVEL_NAME not in", values, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameBetween(String value1, String value2) {
            addCriterion("HOSPITAL_LEVEL_NAME between", value1, value2, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameNotBetween(String value1, String value2) {
            addCriterion("HOSPITAL_LEVEL_NAME not between", value1, value2, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeIdIsNull() {
            addCriterion("HOSPITAL_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeIdIsNotNull() {
            addCriterion("HOSPITAL_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeIdEqualTo(String value) {
            addCriterion("HOSPITAL_TYPE_ID =", value, "hospitalTypeId");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeIdNotEqualTo(String value) {
            addCriterion("HOSPITAL_TYPE_ID <>", value, "hospitalTypeId");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeIdGreaterThan(String value) {
            addCriterion("HOSPITAL_TYPE_ID >", value, "hospitalTypeId");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_TYPE_ID >=", value, "hospitalTypeId");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeIdLessThan(String value) {
            addCriterion("HOSPITAL_TYPE_ID <", value, "hospitalTypeId");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeIdLessThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_TYPE_ID <=", value, "hospitalTypeId");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeIdLike(String value) {
            addCriterion("HOSPITAL_TYPE_ID like", value, "hospitalTypeId");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeIdNotLike(String value) {
            addCriterion("HOSPITAL_TYPE_ID not like", value, "hospitalTypeId");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeIdIn(List<String> values) {
            addCriterion("HOSPITAL_TYPE_ID in", values, "hospitalTypeId");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeIdNotIn(List<String> values) {
            addCriterion("HOSPITAL_TYPE_ID not in", values, "hospitalTypeId");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeIdBetween(String value1, String value2) {
            addCriterion("HOSPITAL_TYPE_ID between", value1, value2, "hospitalTypeId");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeIdNotBetween(String value1, String value2) {
            addCriterion("HOSPITAL_TYPE_ID not between", value1, value2, "hospitalTypeId");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeNameIsNull() {
            addCriterion("HOSPITAL_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeNameIsNotNull() {
            addCriterion("HOSPITAL_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeNameEqualTo(String value) {
            addCriterion("HOSPITAL_TYPE_NAME =", value, "hospitalTypeName");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeNameNotEqualTo(String value) {
            addCriterion("HOSPITAL_TYPE_NAME <>", value, "hospitalTypeName");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeNameGreaterThan(String value) {
            addCriterion("HOSPITAL_TYPE_NAME >", value, "hospitalTypeName");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_TYPE_NAME >=", value, "hospitalTypeName");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeNameLessThan(String value) {
            addCriterion("HOSPITAL_TYPE_NAME <", value, "hospitalTypeName");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeNameLessThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_TYPE_NAME <=", value, "hospitalTypeName");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeNameLike(String value) {
            addCriterion("HOSPITAL_TYPE_NAME like", value, "hospitalTypeName");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeNameNotLike(String value) {
            addCriterion("HOSPITAL_TYPE_NAME not like", value, "hospitalTypeName");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeNameIn(List<String> values) {
            addCriterion("HOSPITAL_TYPE_NAME in", values, "hospitalTypeName");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeNameNotIn(List<String> values) {
            addCriterion("HOSPITAL_TYPE_NAME not in", values, "hospitalTypeName");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeNameBetween(String value1, String value2) {
            addCriterion("HOSPITAL_TYPE_NAME between", value1, value2, "hospitalTypeName");
            return (Criteria) this;
        }

        public Criteria andHospitalTypeNameNotBetween(String value1, String value2) {
            addCriterion("HOSPITAL_TYPE_NAME not between", value1, value2, "hospitalTypeName");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdIsNull() {
            addCriterion("SCHOOL_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdIsNotNull() {
            addCriterion("SCHOOL_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdEqualTo(String value) {
            addCriterion("SCHOOL_TYPE_ID =", value, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdNotEqualTo(String value) {
            addCriterion("SCHOOL_TYPE_ID <>", value, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdGreaterThan(String value) {
            addCriterion("SCHOOL_TYPE_ID >", value, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_TYPE_ID >=", value, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdLessThan(String value) {
            addCriterion("SCHOOL_TYPE_ID <", value, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_TYPE_ID <=", value, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdLike(String value) {
            addCriterion("SCHOOL_TYPE_ID like", value, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdNotLike(String value) {
            addCriterion("SCHOOL_TYPE_ID not like", value, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdIn(List<String> values) {
            addCriterion("SCHOOL_TYPE_ID in", values, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdNotIn(List<String> values) {
            addCriterion("SCHOOL_TYPE_ID not in", values, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdBetween(String value1, String value2) {
            addCriterion("SCHOOL_TYPE_ID between", value1, value2, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_TYPE_ID not between", value1, value2, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeNameIsNull() {
            addCriterion("SCHOOL_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeNameIsNotNull() {
            addCriterion("SCHOOL_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeNameEqualTo(String value) {
            addCriterion("SCHOOL_TYPE_NAME =", value, "schoolTypeName");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeNameNotEqualTo(String value) {
            addCriterion("SCHOOL_TYPE_NAME <>", value, "schoolTypeName");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeNameGreaterThan(String value) {
            addCriterion("SCHOOL_TYPE_NAME >", value, "schoolTypeName");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_TYPE_NAME >=", value, "schoolTypeName");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeNameLessThan(String value) {
            addCriterion("SCHOOL_TYPE_NAME <", value, "schoolTypeName");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeNameLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_TYPE_NAME <=", value, "schoolTypeName");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeNameLike(String value) {
            addCriterion("SCHOOL_TYPE_NAME like", value, "schoolTypeName");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeNameNotLike(String value) {
            addCriterion("SCHOOL_TYPE_NAME not like", value, "schoolTypeName");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeNameIn(List<String> values) {
            addCriterion("SCHOOL_TYPE_NAME in", values, "schoolTypeName");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeNameNotIn(List<String> values) {
            addCriterion("SCHOOL_TYPE_NAME not in", values, "schoolTypeName");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeNameBetween(String value1, String value2) {
            addCriterion("SCHOOL_TYPE_NAME between", value1, value2, "schoolTypeName");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeNameNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_TYPE_NAME not between", value1, value2, "schoolTypeName");
            return (Criteria) this;
        }

        public Criteria andIsContractIsNull() {
            addCriterion("IS_CONTRACT is null");
            return (Criteria) this;
        }

        public Criteria andIsContractIsNotNull() {
            addCriterion("IS_CONTRACT is not null");
            return (Criteria) this;
        }

        public Criteria andIsContractEqualTo(String value) {
            addCriterion("IS_CONTRACT =", value, "isContract");
            return (Criteria) this;
        }

        public Criteria andIsContractNotEqualTo(String value) {
            addCriterion("IS_CONTRACT <>", value, "isContract");
            return (Criteria) this;
        }

        public Criteria andIsContractGreaterThan(String value) {
            addCriterion("IS_CONTRACT >", value, "isContract");
            return (Criteria) this;
        }

        public Criteria andIsContractGreaterThanOrEqualTo(String value) {
            addCriterion("IS_CONTRACT >=", value, "isContract");
            return (Criteria) this;
        }

        public Criteria andIsContractLessThan(String value) {
            addCriterion("IS_CONTRACT <", value, "isContract");
            return (Criteria) this;
        }

        public Criteria andIsContractLessThanOrEqualTo(String value) {
            addCriterion("IS_CONTRACT <=", value, "isContract");
            return (Criteria) this;
        }

        public Criteria andIsContractLike(String value) {
            addCriterion("IS_CONTRACT like", value, "isContract");
            return (Criteria) this;
        }

        public Criteria andIsContractNotLike(String value) {
            addCriterion("IS_CONTRACT not like", value, "isContract");
            return (Criteria) this;
        }

        public Criteria andIsContractIn(List<String> values) {
            addCriterion("IS_CONTRACT in", values, "isContract");
            return (Criteria) this;
        }

        public Criteria andIsContractNotIn(List<String> values) {
            addCriterion("IS_CONTRACT not in", values, "isContract");
            return (Criteria) this;
        }

        public Criteria andIsContractBetween(String value1, String value2) {
            addCriterion("IS_CONTRACT between", value1, value2, "isContract");
            return (Criteria) this;
        }

        public Criteria andIsContractNotBetween(String value1, String value2) {
            addCriterion("IS_CONTRACT not between", value1, value2, "isContract");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeIdIsNull() {
            addCriterion("CUSTOMER_GRADE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeIdIsNotNull() {
            addCriterion("CUSTOMER_GRADE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeIdEqualTo(String value) {
            addCriterion("CUSTOMER_GRADE_ID =", value, "customerGradeId");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeIdNotEqualTo(String value) {
            addCriterion("CUSTOMER_GRADE_ID <>", value, "customerGradeId");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeIdGreaterThan(String value) {
            addCriterion("CUSTOMER_GRADE_ID >", value, "customerGradeId");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeIdGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_GRADE_ID >=", value, "customerGradeId");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeIdLessThan(String value) {
            addCriterion("CUSTOMER_GRADE_ID <", value, "customerGradeId");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeIdLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_GRADE_ID <=", value, "customerGradeId");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeIdLike(String value) {
            addCriterion("CUSTOMER_GRADE_ID like", value, "customerGradeId");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeIdNotLike(String value) {
            addCriterion("CUSTOMER_GRADE_ID not like", value, "customerGradeId");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeIdIn(List<String> values) {
            addCriterion("CUSTOMER_GRADE_ID in", values, "customerGradeId");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeIdNotIn(List<String> values) {
            addCriterion("CUSTOMER_GRADE_ID not in", values, "customerGradeId");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeIdBetween(String value1, String value2) {
            addCriterion("CUSTOMER_GRADE_ID between", value1, value2, "customerGradeId");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeIdNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_GRADE_ID not between", value1, value2, "customerGradeId");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeNameIsNull() {
            addCriterion("CUSTOMER_GRADE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeNameIsNotNull() {
            addCriterion("CUSTOMER_GRADE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeNameEqualTo(String value) {
            addCriterion("CUSTOMER_GRADE_NAME =", value, "customerGradeName");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeNameNotEqualTo(String value) {
            addCriterion("CUSTOMER_GRADE_NAME <>", value, "customerGradeName");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeNameGreaterThan(String value) {
            addCriterion("CUSTOMER_GRADE_NAME >", value, "customerGradeName");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeNameGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_GRADE_NAME >=", value, "customerGradeName");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeNameLessThan(String value) {
            addCriterion("CUSTOMER_GRADE_NAME <", value, "customerGradeName");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeNameLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_GRADE_NAME <=", value, "customerGradeName");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeNameLike(String value) {
            addCriterion("CUSTOMER_GRADE_NAME like", value, "customerGradeName");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeNameNotLike(String value) {
            addCriterion("CUSTOMER_GRADE_NAME not like", value, "customerGradeName");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeNameIn(List<String> values) {
            addCriterion("CUSTOMER_GRADE_NAME in", values, "customerGradeName");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeNameNotIn(List<String> values) {
            addCriterion("CUSTOMER_GRADE_NAME not in", values, "customerGradeName");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeNameBetween(String value1, String value2) {
            addCriterion("CUSTOMER_GRADE_NAME between", value1, value2, "customerGradeName");
            return (Criteria) this;
        }

        public Criteria andCustomerGradeNameNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_GRADE_NAME not between", value1, value2, "customerGradeName");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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