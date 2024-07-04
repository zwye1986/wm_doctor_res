package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SrmProjFundDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmProjFundDetailExample() {
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

        public Criteria andFundDetailFlowIsNull() {
            addCriterion("FUND_DETAIL_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowIsNotNull() {
            addCriterion("FUND_DETAIL_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowEqualTo(String value) {
            addCriterion("FUND_DETAIL_FLOW =", value, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowNotEqualTo(String value) {
            addCriterion("FUND_DETAIL_FLOW <>", value, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowGreaterThan(String value) {
            addCriterion("FUND_DETAIL_FLOW >", value, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_DETAIL_FLOW >=", value, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowLessThan(String value) {
            addCriterion("FUND_DETAIL_FLOW <", value, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowLessThanOrEqualTo(String value) {
            addCriterion("FUND_DETAIL_FLOW <=", value, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowLike(String value) {
            addCriterion("FUND_DETAIL_FLOW like", value, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowNotLike(String value) {
            addCriterion("FUND_DETAIL_FLOW not like", value, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowIn(List<String> values) {
            addCriterion("FUND_DETAIL_FLOW in", values, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowNotIn(List<String> values) {
            addCriterion("FUND_DETAIL_FLOW not in", values, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowBetween(String value1, String value2) {
            addCriterion("FUND_DETAIL_FLOW between", value1, value2, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowNotBetween(String value1, String value2) {
            addCriterion("FUND_DETAIL_FLOW not between", value1, value2, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowIsNull() {
            addCriterion("FUND_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFundFlowIsNotNull() {
            addCriterion("FUND_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFundFlowEqualTo(String value) {
            addCriterion("FUND_FLOW =", value, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowNotEqualTo(String value) {
            addCriterion("FUND_FLOW <>", value, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowGreaterThan(String value) {
            addCriterion("FUND_FLOW >", value, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_FLOW >=", value, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowLessThan(String value) {
            addCriterion("FUND_FLOW <", value, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowLessThanOrEqualTo(String value) {
            addCriterion("FUND_FLOW <=", value, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowLike(String value) {
            addCriterion("FUND_FLOW like", value, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowNotLike(String value) {
            addCriterion("FUND_FLOW not like", value, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowIn(List<String> values) {
            addCriterion("FUND_FLOW in", values, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowNotIn(List<String> values) {
            addCriterion("FUND_FLOW not in", values, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowBetween(String value1, String value2) {
            addCriterion("FUND_FLOW between", value1, value2, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowNotBetween(String value1, String value2) {
            addCriterion("FUND_FLOW not between", value1, value2, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowIsNull() {
            addCriterion("ITEM_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andItemFlowIsNotNull() {
            addCriterion("ITEM_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andItemFlowEqualTo(String value) {
            addCriterion("ITEM_FLOW =", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowNotEqualTo(String value) {
            addCriterion("ITEM_FLOW <>", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowGreaterThan(String value) {
            addCriterion("ITEM_FLOW >", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_FLOW >=", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowLessThan(String value) {
            addCriterion("ITEM_FLOW <", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowLessThanOrEqualTo(String value) {
            addCriterion("ITEM_FLOW <=", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowLike(String value) {
            addCriterion("ITEM_FLOW like", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowNotLike(String value) {
            addCriterion("ITEM_FLOW not like", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowIn(List<String> values) {
            addCriterion("ITEM_FLOW in", values, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowNotIn(List<String> values) {
            addCriterion("ITEM_FLOW not in", values, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowBetween(String value1, String value2) {
            addCriterion("ITEM_FLOW between", value1, value2, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowNotBetween(String value1, String value2) {
            addCriterion("ITEM_FLOW not between", value1, value2, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andFundTypeIdIsNull() {
            addCriterion("FUND_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andFundTypeIdIsNotNull() {
            addCriterion("FUND_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFundTypeIdEqualTo(String value) {
            addCriterion("FUND_TYPE_ID =", value, "fundTypeId");
            return (Criteria) this;
        }

        public Criteria andFundTypeIdNotEqualTo(String value) {
            addCriterion("FUND_TYPE_ID <>", value, "fundTypeId");
            return (Criteria) this;
        }

        public Criteria andFundTypeIdGreaterThan(String value) {
            addCriterion("FUND_TYPE_ID >", value, "fundTypeId");
            return (Criteria) this;
        }

        public Criteria andFundTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_TYPE_ID >=", value, "fundTypeId");
            return (Criteria) this;
        }

        public Criteria andFundTypeIdLessThan(String value) {
            addCriterion("FUND_TYPE_ID <", value, "fundTypeId");
            return (Criteria) this;
        }

        public Criteria andFundTypeIdLessThanOrEqualTo(String value) {
            addCriterion("FUND_TYPE_ID <=", value, "fundTypeId");
            return (Criteria) this;
        }

        public Criteria andFundTypeIdLike(String value) {
            addCriterion("FUND_TYPE_ID like", value, "fundTypeId");
            return (Criteria) this;
        }

        public Criteria andFundTypeIdNotLike(String value) {
            addCriterion("FUND_TYPE_ID not like", value, "fundTypeId");
            return (Criteria) this;
        }

        public Criteria andFundTypeIdIn(List<String> values) {
            addCriterion("FUND_TYPE_ID in", values, "fundTypeId");
            return (Criteria) this;
        }

        public Criteria andFundTypeIdNotIn(List<String> values) {
            addCriterion("FUND_TYPE_ID not in", values, "fundTypeId");
            return (Criteria) this;
        }

        public Criteria andFundTypeIdBetween(String value1, String value2) {
            addCriterion("FUND_TYPE_ID between", value1, value2, "fundTypeId");
            return (Criteria) this;
        }

        public Criteria andFundTypeIdNotBetween(String value1, String value2) {
            addCriterion("FUND_TYPE_ID not between", value1, value2, "fundTypeId");
            return (Criteria) this;
        }

        public Criteria andFundTypeNameIsNull() {
            addCriterion("FUND_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFundTypeNameIsNotNull() {
            addCriterion("FUND_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFundTypeNameEqualTo(String value) {
            addCriterion("FUND_TYPE_NAME =", value, "fundTypeName");
            return (Criteria) this;
        }

        public Criteria andFundTypeNameNotEqualTo(String value) {
            addCriterion("FUND_TYPE_NAME <>", value, "fundTypeName");
            return (Criteria) this;
        }

        public Criteria andFundTypeNameGreaterThan(String value) {
            addCriterion("FUND_TYPE_NAME >", value, "fundTypeName");
            return (Criteria) this;
        }

        public Criteria andFundTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_TYPE_NAME >=", value, "fundTypeName");
            return (Criteria) this;
        }

        public Criteria andFundTypeNameLessThan(String value) {
            addCriterion("FUND_TYPE_NAME <", value, "fundTypeName");
            return (Criteria) this;
        }

        public Criteria andFundTypeNameLessThanOrEqualTo(String value) {
            addCriterion("FUND_TYPE_NAME <=", value, "fundTypeName");
            return (Criteria) this;
        }

        public Criteria andFundTypeNameLike(String value) {
            addCriterion("FUND_TYPE_NAME like", value, "fundTypeName");
            return (Criteria) this;
        }

        public Criteria andFundTypeNameNotLike(String value) {
            addCriterion("FUND_TYPE_NAME not like", value, "fundTypeName");
            return (Criteria) this;
        }

        public Criteria andFundTypeNameIn(List<String> values) {
            addCriterion("FUND_TYPE_NAME in", values, "fundTypeName");
            return (Criteria) this;
        }

        public Criteria andFundTypeNameNotIn(List<String> values) {
            addCriterion("FUND_TYPE_NAME not in", values, "fundTypeName");
            return (Criteria) this;
        }

        public Criteria andFundTypeNameBetween(String value1, String value2) {
            addCriterion("FUND_TYPE_NAME between", value1, value2, "fundTypeName");
            return (Criteria) this;
        }

        public Criteria andFundTypeNameNotBetween(String value1, String value2) {
            addCriterion("FUND_TYPE_NAME not between", value1, value2, "fundTypeName");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("MONEY is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(BigDecimal value) {
            addCriterion("MONEY =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(BigDecimal value) {
            addCriterion("MONEY <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(BigDecimal value) {
            addCriterion("MONEY >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MONEY >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(BigDecimal value) {
            addCriterion("MONEY <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MONEY <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<BigDecimal> values) {
            addCriterion("MONEY in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<BigDecimal> values) {
            addCriterion("MONEY not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MONEY between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MONEY not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("CONTENT =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("CONTENT <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("CONTENT >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("CONTENT >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("CONTENT <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("CONTENT <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("CONTENT like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("CONTENT not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("CONTENT in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("CONTENT not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("CONTENT between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("CONTENT not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIsNull() {
            addCriterion("OPER_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIsNotNull() {
            addCriterion("OPER_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdEqualTo(String value) {
            addCriterion("OPER_STATUS_ID =", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotEqualTo(String value) {
            addCriterion("OPER_STATUS_ID <>", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdGreaterThan(String value) {
            addCriterion("OPER_STATUS_ID >", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_ID >=", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLessThan(String value) {
            addCriterion("OPER_STATUS_ID <", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLessThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_ID <=", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLike(String value) {
            addCriterion("OPER_STATUS_ID like", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotLike(String value) {
            addCriterion("OPER_STATUS_ID not like", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIn(List<String> values) {
            addCriterion("OPER_STATUS_ID in", values, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotIn(List<String> values) {
            addCriterion("OPER_STATUS_ID not in", values, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_ID between", value1, value2, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_ID not between", value1, value2, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIsNull() {
            addCriterion("OPER_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIsNotNull() {
            addCriterion("OPER_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME =", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME <>", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameGreaterThan(String value) {
            addCriterion("OPER_STATUS_NAME >", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME >=", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLessThan(String value) {
            addCriterion("OPER_STATUS_NAME <", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLessThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME <=", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLike(String value) {
            addCriterion("OPER_STATUS_NAME like", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotLike(String value) {
            addCriterion("OPER_STATUS_NAME not like", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIn(List<String> values) {
            addCriterion("OPER_STATUS_NAME in", values, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotIn(List<String> values) {
            addCriterion("OPER_STATUS_NAME not in", values, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_NAME between", value1, value2, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_NAME not between", value1, value2, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andProvideOrgIsNull() {
            addCriterion("PROVIDE_ORG is null");
            return (Criteria) this;
        }

        public Criteria andProvideOrgIsNotNull() {
            addCriterion("PROVIDE_ORG is not null");
            return (Criteria) this;
        }

        public Criteria andProvideOrgEqualTo(String value) {
            addCriterion("PROVIDE_ORG =", value, "provideOrg");
            return (Criteria) this;
        }

        public Criteria andProvideOrgNotEqualTo(String value) {
            addCriterion("PROVIDE_ORG <>", value, "provideOrg");
            return (Criteria) this;
        }

        public Criteria andProvideOrgGreaterThan(String value) {
            addCriterion("PROVIDE_ORG >", value, "provideOrg");
            return (Criteria) this;
        }

        public Criteria andProvideOrgGreaterThanOrEqualTo(String value) {
            addCriterion("PROVIDE_ORG >=", value, "provideOrg");
            return (Criteria) this;
        }

        public Criteria andProvideOrgLessThan(String value) {
            addCriterion("PROVIDE_ORG <", value, "provideOrg");
            return (Criteria) this;
        }

        public Criteria andProvideOrgLessThanOrEqualTo(String value) {
            addCriterion("PROVIDE_ORG <=", value, "provideOrg");
            return (Criteria) this;
        }

        public Criteria andProvideOrgLike(String value) {
            addCriterion("PROVIDE_ORG like", value, "provideOrg");
            return (Criteria) this;
        }

        public Criteria andProvideOrgNotLike(String value) {
            addCriterion("PROVIDE_ORG not like", value, "provideOrg");
            return (Criteria) this;
        }

        public Criteria andProvideOrgIn(List<String> values) {
            addCriterion("PROVIDE_ORG in", values, "provideOrg");
            return (Criteria) this;
        }

        public Criteria andProvideOrgNotIn(List<String> values) {
            addCriterion("PROVIDE_ORG not in", values, "provideOrg");
            return (Criteria) this;
        }

        public Criteria andProvideOrgBetween(String value1, String value2) {
            addCriterion("PROVIDE_ORG between", value1, value2, "provideOrg");
            return (Criteria) this;
        }

        public Criteria andProvideOrgNotBetween(String value1, String value2) {
            addCriterion("PROVIDE_ORG not between", value1, value2, "provideOrg");
            return (Criteria) this;
        }

        public Criteria andProvideDateTimeIsNull() {
            addCriterion("PROVIDE_DATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andProvideDateTimeIsNotNull() {
            addCriterion("PROVIDE_DATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andProvideDateTimeEqualTo(String value) {
            addCriterion("PROVIDE_DATE_TIME =", value, "provideDateTime");
            return (Criteria) this;
        }

        public Criteria andProvideDateTimeNotEqualTo(String value) {
            addCriterion("PROVIDE_DATE_TIME <>", value, "provideDateTime");
            return (Criteria) this;
        }

        public Criteria andProvideDateTimeGreaterThan(String value) {
            addCriterion("PROVIDE_DATE_TIME >", value, "provideDateTime");
            return (Criteria) this;
        }

        public Criteria andProvideDateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("PROVIDE_DATE_TIME >=", value, "provideDateTime");
            return (Criteria) this;
        }

        public Criteria andProvideDateTimeLessThan(String value) {
            addCriterion("PROVIDE_DATE_TIME <", value, "provideDateTime");
            return (Criteria) this;
        }

        public Criteria andProvideDateTimeLessThanOrEqualTo(String value) {
            addCriterion("PROVIDE_DATE_TIME <=", value, "provideDateTime");
            return (Criteria) this;
        }

        public Criteria andProvideDateTimeLike(String value) {
            addCriterion("PROVIDE_DATE_TIME like", value, "provideDateTime");
            return (Criteria) this;
        }

        public Criteria andProvideDateTimeNotLike(String value) {
            addCriterion("PROVIDE_DATE_TIME not like", value, "provideDateTime");
            return (Criteria) this;
        }

        public Criteria andProvideDateTimeIn(List<String> values) {
            addCriterion("PROVIDE_DATE_TIME in", values, "provideDateTime");
            return (Criteria) this;
        }

        public Criteria andProvideDateTimeNotIn(List<String> values) {
            addCriterion("PROVIDE_DATE_TIME not in", values, "provideDateTime");
            return (Criteria) this;
        }

        public Criteria andProvideDateTimeBetween(String value1, String value2) {
            addCriterion("PROVIDE_DATE_TIME between", value1, value2, "provideDateTime");
            return (Criteria) this;
        }

        public Criteria andProvideDateTimeNotBetween(String value1, String value2) {
            addCriterion("PROVIDE_DATE_TIME not between", value1, value2, "provideDateTime");
            return (Criteria) this;
        }

        public Criteria andRealityTypeIdIsNull() {
            addCriterion("REALITY_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andRealityTypeIdIsNotNull() {
            addCriterion("REALITY_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRealityTypeIdEqualTo(String value) {
            addCriterion("REALITY_TYPE_ID =", value, "realityTypeId");
            return (Criteria) this;
        }

        public Criteria andRealityTypeIdNotEqualTo(String value) {
            addCriterion("REALITY_TYPE_ID <>", value, "realityTypeId");
            return (Criteria) this;
        }

        public Criteria andRealityTypeIdGreaterThan(String value) {
            addCriterion("REALITY_TYPE_ID >", value, "realityTypeId");
            return (Criteria) this;
        }

        public Criteria andRealityTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("REALITY_TYPE_ID >=", value, "realityTypeId");
            return (Criteria) this;
        }

        public Criteria andRealityTypeIdLessThan(String value) {
            addCriterion("REALITY_TYPE_ID <", value, "realityTypeId");
            return (Criteria) this;
        }

        public Criteria andRealityTypeIdLessThanOrEqualTo(String value) {
            addCriterion("REALITY_TYPE_ID <=", value, "realityTypeId");
            return (Criteria) this;
        }

        public Criteria andRealityTypeIdLike(String value) {
            addCriterion("REALITY_TYPE_ID like", value, "realityTypeId");
            return (Criteria) this;
        }

        public Criteria andRealityTypeIdNotLike(String value) {
            addCriterion("REALITY_TYPE_ID not like", value, "realityTypeId");
            return (Criteria) this;
        }

        public Criteria andRealityTypeIdIn(List<String> values) {
            addCriterion("REALITY_TYPE_ID in", values, "realityTypeId");
            return (Criteria) this;
        }

        public Criteria andRealityTypeIdNotIn(List<String> values) {
            addCriterion("REALITY_TYPE_ID not in", values, "realityTypeId");
            return (Criteria) this;
        }

        public Criteria andRealityTypeIdBetween(String value1, String value2) {
            addCriterion("REALITY_TYPE_ID between", value1, value2, "realityTypeId");
            return (Criteria) this;
        }

        public Criteria andRealityTypeIdNotBetween(String value1, String value2) {
            addCriterion("REALITY_TYPE_ID not between", value1, value2, "realityTypeId");
            return (Criteria) this;
        }

        public Criteria andRealityTypeNameIsNull() {
            addCriterion("REALITY_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRealityTypeNameIsNotNull() {
            addCriterion("REALITY_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRealityTypeNameEqualTo(String value) {
            addCriterion("REALITY_TYPE_NAME =", value, "realityTypeName");
            return (Criteria) this;
        }

        public Criteria andRealityTypeNameNotEqualTo(String value) {
            addCriterion("REALITY_TYPE_NAME <>", value, "realityTypeName");
            return (Criteria) this;
        }

        public Criteria andRealityTypeNameGreaterThan(String value) {
            addCriterion("REALITY_TYPE_NAME >", value, "realityTypeName");
            return (Criteria) this;
        }

        public Criteria andRealityTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("REALITY_TYPE_NAME >=", value, "realityTypeName");
            return (Criteria) this;
        }

        public Criteria andRealityTypeNameLessThan(String value) {
            addCriterion("REALITY_TYPE_NAME <", value, "realityTypeName");
            return (Criteria) this;
        }

        public Criteria andRealityTypeNameLessThanOrEqualTo(String value) {
            addCriterion("REALITY_TYPE_NAME <=", value, "realityTypeName");
            return (Criteria) this;
        }

        public Criteria andRealityTypeNameLike(String value) {
            addCriterion("REALITY_TYPE_NAME like", value, "realityTypeName");
            return (Criteria) this;
        }

        public Criteria andRealityTypeNameNotLike(String value) {
            addCriterion("REALITY_TYPE_NAME not like", value, "realityTypeName");
            return (Criteria) this;
        }

        public Criteria andRealityTypeNameIn(List<String> values) {
            addCriterion("REALITY_TYPE_NAME in", values, "realityTypeName");
            return (Criteria) this;
        }

        public Criteria andRealityTypeNameNotIn(List<String> values) {
            addCriterion("REALITY_TYPE_NAME not in", values, "realityTypeName");
            return (Criteria) this;
        }

        public Criteria andRealityTypeNameBetween(String value1, String value2) {
            addCriterion("REALITY_TYPE_NAME between", value1, value2, "realityTypeName");
            return (Criteria) this;
        }

        public Criteria andRealityTypeNameNotBetween(String value1, String value2) {
            addCriterion("REALITY_TYPE_NAME not between", value1, value2, "realityTypeName");
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

        public Criteria andFundOperatorIsNull() {
            addCriterion("FUND_OPERATOR is null");
            return (Criteria) this;
        }

        public Criteria andFundOperatorIsNotNull() {
            addCriterion("FUND_OPERATOR is not null");
            return (Criteria) this;
        }

        public Criteria andFundOperatorEqualTo(String value) {
            addCriterion("FUND_OPERATOR =", value, "fundOperator");
            return (Criteria) this;
        }

        public Criteria andFundOperatorNotEqualTo(String value) {
            addCriterion("FUND_OPERATOR <>", value, "fundOperator");
            return (Criteria) this;
        }

        public Criteria andFundOperatorGreaterThan(String value) {
            addCriterion("FUND_OPERATOR >", value, "fundOperator");
            return (Criteria) this;
        }

        public Criteria andFundOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_OPERATOR >=", value, "fundOperator");
            return (Criteria) this;
        }

        public Criteria andFundOperatorLessThan(String value) {
            addCriterion("FUND_OPERATOR <", value, "fundOperator");
            return (Criteria) this;
        }

        public Criteria andFundOperatorLessThanOrEqualTo(String value) {
            addCriterion("FUND_OPERATOR <=", value, "fundOperator");
            return (Criteria) this;
        }

        public Criteria andFundOperatorLike(String value) {
            addCriterion("FUND_OPERATOR like", value, "fundOperator");
            return (Criteria) this;
        }

        public Criteria andFundOperatorNotLike(String value) {
            addCriterion("FUND_OPERATOR not like", value, "fundOperator");
            return (Criteria) this;
        }

        public Criteria andFundOperatorIn(List<String> values) {
            addCriterion("FUND_OPERATOR in", values, "fundOperator");
            return (Criteria) this;
        }

        public Criteria andFundOperatorNotIn(List<String> values) {
            addCriterion("FUND_OPERATOR not in", values, "fundOperator");
            return (Criteria) this;
        }

        public Criteria andFundOperatorBetween(String value1, String value2) {
            addCriterion("FUND_OPERATOR between", value1, value2, "fundOperator");
            return (Criteria) this;
        }

        public Criteria andFundOperatorNotBetween(String value1, String value2) {
            addCriterion("FUND_OPERATOR not between", value1, value2, "fundOperator");
            return (Criteria) this;
        }

        public Criteria andFundRetypeIsNull() {
            addCriterion("FUND_RETYPE is null");
            return (Criteria) this;
        }

        public Criteria andFundRetypeIsNotNull() {
            addCriterion("FUND_RETYPE is not null");
            return (Criteria) this;
        }

        public Criteria andFundRetypeEqualTo(String value) {
            addCriterion("FUND_RETYPE =", value, "fundRetype");
            return (Criteria) this;
        }

        public Criteria andFundRetypeNotEqualTo(String value) {
            addCriterion("FUND_RETYPE <>", value, "fundRetype");
            return (Criteria) this;
        }

        public Criteria andFundRetypeGreaterThan(String value) {
            addCriterion("FUND_RETYPE >", value, "fundRetype");
            return (Criteria) this;
        }

        public Criteria andFundRetypeGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_RETYPE >=", value, "fundRetype");
            return (Criteria) this;
        }

        public Criteria andFundRetypeLessThan(String value) {
            addCriterion("FUND_RETYPE <", value, "fundRetype");
            return (Criteria) this;
        }

        public Criteria andFundRetypeLessThanOrEqualTo(String value) {
            addCriterion("FUND_RETYPE <=", value, "fundRetype");
            return (Criteria) this;
        }

        public Criteria andFundRetypeLike(String value) {
            addCriterion("FUND_RETYPE like", value, "fundRetype");
            return (Criteria) this;
        }

        public Criteria andFundRetypeNotLike(String value) {
            addCriterion("FUND_RETYPE not like", value, "fundRetype");
            return (Criteria) this;
        }

        public Criteria andFundRetypeIn(List<String> values) {
            addCriterion("FUND_RETYPE in", values, "fundRetype");
            return (Criteria) this;
        }

        public Criteria andFundRetypeNotIn(List<String> values) {
            addCriterion("FUND_RETYPE not in", values, "fundRetype");
            return (Criteria) this;
        }

        public Criteria andFundRetypeBetween(String value1, String value2) {
            addCriterion("FUND_RETYPE between", value1, value2, "fundRetype");
            return (Criteria) this;
        }

        public Criteria andFundRetypeNotBetween(String value1, String value2) {
            addCriterion("FUND_RETYPE not between", value1, value2, "fundRetype");
            return (Criteria) this;
        }

        public Criteria andRealmoneyIsNull() {
            addCriterion("REALMONEY is null");
            return (Criteria) this;
        }

        public Criteria andRealmoneyIsNotNull() {
            addCriterion("REALMONEY is not null");
            return (Criteria) this;
        }

        public Criteria andRealmoneyEqualTo(BigDecimal value) {
            addCriterion("REALMONEY =", value, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyNotEqualTo(BigDecimal value) {
            addCriterion("REALMONEY <>", value, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyGreaterThan(BigDecimal value) {
            addCriterion("REALMONEY >", value, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("REALMONEY >=", value, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyLessThan(BigDecimal value) {
            addCriterion("REALMONEY <", value, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("REALMONEY <=", value, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyIn(List<BigDecimal> values) {
            addCriterion("REALMONEY in", values, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyNotIn(List<BigDecimal> values) {
            addCriterion("REALMONEY not in", values, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REALMONEY between", value1, value2, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REALMONEY not between", value1, value2, "realmoney");
            return (Criteria) this;
        }

        public Criteria andFundFormNumIsNull() {
            addCriterion("FUND_FORM_NUM is null");
            return (Criteria) this;
        }

        public Criteria andFundFormNumIsNotNull() {
            addCriterion("FUND_FORM_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andFundFormNumEqualTo(Short value) {
            addCriterion("FUND_FORM_NUM =", value, "fundFormNum");
            return (Criteria) this;
        }

        public Criteria andFundFormNumNotEqualTo(Short value) {
            addCriterion("FUND_FORM_NUM <>", value, "fundFormNum");
            return (Criteria) this;
        }

        public Criteria andFundFormNumGreaterThan(Short value) {
            addCriterion("FUND_FORM_NUM >", value, "fundFormNum");
            return (Criteria) this;
        }

        public Criteria andFundFormNumGreaterThanOrEqualTo(Short value) {
            addCriterion("FUND_FORM_NUM >=", value, "fundFormNum");
            return (Criteria) this;
        }

        public Criteria andFundFormNumLessThan(Short value) {
            addCriterion("FUND_FORM_NUM <", value, "fundFormNum");
            return (Criteria) this;
        }

        public Criteria andFundFormNumLessThanOrEqualTo(Short value) {
            addCriterion("FUND_FORM_NUM <=", value, "fundFormNum");
            return (Criteria) this;
        }

        public Criteria andFundFormNumIn(List<Short> values) {
            addCriterion("FUND_FORM_NUM in", values, "fundFormNum");
            return (Criteria) this;
        }

        public Criteria andFundFormNumNotIn(List<Short> values) {
            addCriterion("FUND_FORM_NUM not in", values, "fundFormNum");
            return (Criteria) this;
        }

        public Criteria andFundFormNumBetween(Short value1, Short value2) {
            addCriterion("FUND_FORM_NUM between", value1, value2, "fundFormNum");
            return (Criteria) this;
        }

        public Criteria andFundFormNumNotBetween(Short value1, Short value2) {
            addCriterion("FUND_FORM_NUM not between", value1, value2, "fundFormNum");
            return (Criteria) this;
        }

        public Criteria andFundFormFlowIsNull() {
            addCriterion("FUND_FORM_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFundFormFlowIsNotNull() {
            addCriterion("FUND_FORM_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFundFormFlowEqualTo(String value) {
            addCriterion("FUND_FORM_FLOW =", value, "fundFormFlow");
            return (Criteria) this;
        }

        public Criteria andFundFormFlowNotEqualTo(String value) {
            addCriterion("FUND_FORM_FLOW <>", value, "fundFormFlow");
            return (Criteria) this;
        }

        public Criteria andFundFormFlowGreaterThan(String value) {
            addCriterion("FUND_FORM_FLOW >", value, "fundFormFlow");
            return (Criteria) this;
        }

        public Criteria andFundFormFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_FORM_FLOW >=", value, "fundFormFlow");
            return (Criteria) this;
        }

        public Criteria andFundFormFlowLessThan(String value) {
            addCriterion("FUND_FORM_FLOW <", value, "fundFormFlow");
            return (Criteria) this;
        }

        public Criteria andFundFormFlowLessThanOrEqualTo(String value) {
            addCriterion("FUND_FORM_FLOW <=", value, "fundFormFlow");
            return (Criteria) this;
        }

        public Criteria andFundFormFlowLike(String value) {
            addCriterion("FUND_FORM_FLOW like", value, "fundFormFlow");
            return (Criteria) this;
        }

        public Criteria andFundFormFlowNotLike(String value) {
            addCriterion("FUND_FORM_FLOW not like", value, "fundFormFlow");
            return (Criteria) this;
        }

        public Criteria andFundFormFlowIn(List<String> values) {
            addCriterion("FUND_FORM_FLOW in", values, "fundFormFlow");
            return (Criteria) this;
        }

        public Criteria andFundFormFlowNotIn(List<String> values) {
            addCriterion("FUND_FORM_FLOW not in", values, "fundFormFlow");
            return (Criteria) this;
        }

        public Criteria andFundFormFlowBetween(String value1, String value2) {
            addCriterion("FUND_FORM_FLOW between", value1, value2, "fundFormFlow");
            return (Criteria) this;
        }

        public Criteria andFundFormFlowNotBetween(String value1, String value2) {
            addCriterion("FUND_FORM_FLOW not between", value1, value2, "fundFormFlow");
            return (Criteria) this;
        }

        public Criteria andReimburseIdIsNull() {
            addCriterion("REIMBURSE_ID is null");
            return (Criteria) this;
        }

        public Criteria andReimburseIdIsNotNull() {
            addCriterion("REIMBURSE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andReimburseIdEqualTo(String value) {
            addCriterion("REIMBURSE_ID =", value, "reimburseId");
            return (Criteria) this;
        }

        public Criteria andReimburseIdNotEqualTo(String value) {
            addCriterion("REIMBURSE_ID <>", value, "reimburseId");
            return (Criteria) this;
        }

        public Criteria andReimburseIdGreaterThan(String value) {
            addCriterion("REIMBURSE_ID >", value, "reimburseId");
            return (Criteria) this;
        }

        public Criteria andReimburseIdGreaterThanOrEqualTo(String value) {
            addCriterion("REIMBURSE_ID >=", value, "reimburseId");
            return (Criteria) this;
        }

        public Criteria andReimburseIdLessThan(String value) {
            addCriterion("REIMBURSE_ID <", value, "reimburseId");
            return (Criteria) this;
        }

        public Criteria andReimburseIdLessThanOrEqualTo(String value) {
            addCriterion("REIMBURSE_ID <=", value, "reimburseId");
            return (Criteria) this;
        }

        public Criteria andReimburseIdLike(String value) {
            addCriterion("REIMBURSE_ID like", value, "reimburseId");
            return (Criteria) this;
        }

        public Criteria andReimburseIdNotLike(String value) {
            addCriterion("REIMBURSE_ID not like", value, "reimburseId");
            return (Criteria) this;
        }

        public Criteria andReimburseIdIn(List<String> values) {
            addCriterion("REIMBURSE_ID in", values, "reimburseId");
            return (Criteria) this;
        }

        public Criteria andReimburseIdNotIn(List<String> values) {
            addCriterion("REIMBURSE_ID not in", values, "reimburseId");
            return (Criteria) this;
        }

        public Criteria andReimburseIdBetween(String value1, String value2) {
            addCriterion("REIMBURSE_ID between", value1, value2, "reimburseId");
            return (Criteria) this;
        }

        public Criteria andReimburseIdNotBetween(String value1, String value2) {
            addCriterion("REIMBURSE_ID not between", value1, value2, "reimburseId");
            return (Criteria) this;
        }

        public Criteria andReimburseNameIsNull() {
            addCriterion("REIMBURSE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andReimburseNameIsNotNull() {
            addCriterion("REIMBURSE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andReimburseNameEqualTo(String value) {
            addCriterion("REIMBURSE_NAME =", value, "reimburseName");
            return (Criteria) this;
        }

        public Criteria andReimburseNameNotEqualTo(String value) {
            addCriterion("REIMBURSE_NAME <>", value, "reimburseName");
            return (Criteria) this;
        }

        public Criteria andReimburseNameGreaterThan(String value) {
            addCriterion("REIMBURSE_NAME >", value, "reimburseName");
            return (Criteria) this;
        }

        public Criteria andReimburseNameGreaterThanOrEqualTo(String value) {
            addCriterion("REIMBURSE_NAME >=", value, "reimburseName");
            return (Criteria) this;
        }

        public Criteria andReimburseNameLessThan(String value) {
            addCriterion("REIMBURSE_NAME <", value, "reimburseName");
            return (Criteria) this;
        }

        public Criteria andReimburseNameLessThanOrEqualTo(String value) {
            addCriterion("REIMBURSE_NAME <=", value, "reimburseName");
            return (Criteria) this;
        }

        public Criteria andReimburseNameLike(String value) {
            addCriterion("REIMBURSE_NAME like", value, "reimburseName");
            return (Criteria) this;
        }

        public Criteria andReimburseNameNotLike(String value) {
            addCriterion("REIMBURSE_NAME not like", value, "reimburseName");
            return (Criteria) this;
        }

        public Criteria andReimburseNameIn(List<String> values) {
            addCriterion("REIMBURSE_NAME in", values, "reimburseName");
            return (Criteria) this;
        }

        public Criteria andReimburseNameNotIn(List<String> values) {
            addCriterion("REIMBURSE_NAME not in", values, "reimburseName");
            return (Criteria) this;
        }

        public Criteria andReimburseNameBetween(String value1, String value2) {
            addCriterion("REIMBURSE_NAME between", value1, value2, "reimburseName");
            return (Criteria) this;
        }

        public Criteria andReimburseNameNotBetween(String value1, String value2) {
            addCriterion("REIMBURSE_NAME not between", value1, value2, "reimburseName");
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

        public Criteria andItemIdIsNull() {
            addCriterion("ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(String value) {
            addCriterion("ITEM_ID =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(String value) {
            addCriterion("ITEM_ID <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(String value) {
            addCriterion("ITEM_ID >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_ID >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(String value) {
            addCriterion("ITEM_ID <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(String value) {
            addCriterion("ITEM_ID <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLike(String value) {
            addCriterion("ITEM_ID like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotLike(String value) {
            addCriterion("ITEM_ID not like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<String> values) {
            addCriterion("ITEM_ID in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<String> values) {
            addCriterion("ITEM_ID not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(String value1, String value2) {
            addCriterion("ITEM_ID between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(String value1, String value2) {
            addCriterion("ITEM_ID not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNull() {
            addCriterion("ITEM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNotNull() {
            addCriterion("ITEM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andItemNameEqualTo(String value) {
            addCriterion("ITEM_NAME =", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotEqualTo(String value) {
            addCriterion("ITEM_NAME <>", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThan(String value) {
            addCriterion("ITEM_NAME >", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_NAME >=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThan(String value) {
            addCriterion("ITEM_NAME <", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThanOrEqualTo(String value) {
            addCriterion("ITEM_NAME <=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLike(String value) {
            addCriterion("ITEM_NAME like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotLike(String value) {
            addCriterion("ITEM_NAME not like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameIn(List<String> values) {
            addCriterion("ITEM_NAME in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotIn(List<String> values) {
            addCriterion("ITEM_NAME not in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameBetween(String value1, String value2) {
            addCriterion("ITEM_NAME between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotBetween(String value1, String value2) {
            addCriterion("ITEM_NAME not between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemPidIsNull() {
            addCriterion("ITEM_PID is null");
            return (Criteria) this;
        }

        public Criteria andItemPidIsNotNull() {
            addCriterion("ITEM_PID is not null");
            return (Criteria) this;
        }

        public Criteria andItemPidEqualTo(String value) {
            addCriterion("ITEM_PID =", value, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidNotEqualTo(String value) {
            addCriterion("ITEM_PID <>", value, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidGreaterThan(String value) {
            addCriterion("ITEM_PID >", value, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_PID >=", value, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidLessThan(String value) {
            addCriterion("ITEM_PID <", value, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidLessThanOrEqualTo(String value) {
            addCriterion("ITEM_PID <=", value, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidLike(String value) {
            addCriterion("ITEM_PID like", value, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidNotLike(String value) {
            addCriterion("ITEM_PID not like", value, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidIn(List<String> values) {
            addCriterion("ITEM_PID in", values, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidNotIn(List<String> values) {
            addCriterion("ITEM_PID not in", values, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidBetween(String value1, String value2) {
            addCriterion("ITEM_PID between", value1, value2, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidNotBetween(String value1, String value2) {
            addCriterion("ITEM_PID not between", value1, value2, "itemPid");
            return (Criteria) this;
        }

        public Criteria andMaxLimitIsNull() {
            addCriterion("MAX_LIMIT is null");
            return (Criteria) this;
        }

        public Criteria andMaxLimitIsNotNull() {
            addCriterion("MAX_LIMIT is not null");
            return (Criteria) this;
        }

        public Criteria andMaxLimitEqualTo(String value) {
            addCriterion("MAX_LIMIT =", value, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitNotEqualTo(String value) {
            addCriterion("MAX_LIMIT <>", value, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitGreaterThan(String value) {
            addCriterion("MAX_LIMIT >", value, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitGreaterThanOrEqualTo(String value) {
            addCriterion("MAX_LIMIT >=", value, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitLessThan(String value) {
            addCriterion("MAX_LIMIT <", value, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitLessThanOrEqualTo(String value) {
            addCriterion("MAX_LIMIT <=", value, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitLike(String value) {
            addCriterion("MAX_LIMIT like", value, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitNotLike(String value) {
            addCriterion("MAX_LIMIT not like", value, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitIn(List<String> values) {
            addCriterion("MAX_LIMIT in", values, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitNotIn(List<String> values) {
            addCriterion("MAX_LIMIT not in", values, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitBetween(String value1, String value2) {
            addCriterion("MAX_LIMIT between", value1, value2, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitNotBetween(String value1, String value2) {
            addCriterion("MAX_LIMIT not between", value1, value2, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andAllocateMoneyIsNull() {
            addCriterion("ALLOCATE_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andAllocateMoneyIsNotNull() {
            addCriterion("ALLOCATE_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andAllocateMoneyEqualTo(BigDecimal value) {
            addCriterion("ALLOCATE_MONEY =", value, "allocateMoney");
            return (Criteria) this;
        }

        public Criteria andAllocateMoneyNotEqualTo(BigDecimal value) {
            addCriterion("ALLOCATE_MONEY <>", value, "allocateMoney");
            return (Criteria) this;
        }

        public Criteria andAllocateMoneyGreaterThan(BigDecimal value) {
            addCriterion("ALLOCATE_MONEY >", value, "allocateMoney");
            return (Criteria) this;
        }

        public Criteria andAllocateMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ALLOCATE_MONEY >=", value, "allocateMoney");
            return (Criteria) this;
        }

        public Criteria andAllocateMoneyLessThan(BigDecimal value) {
            addCriterion("ALLOCATE_MONEY <", value, "allocateMoney");
            return (Criteria) this;
        }

        public Criteria andAllocateMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ALLOCATE_MONEY <=", value, "allocateMoney");
            return (Criteria) this;
        }

        public Criteria andAllocateMoneyIn(List<BigDecimal> values) {
            addCriterion("ALLOCATE_MONEY in", values, "allocateMoney");
            return (Criteria) this;
        }

        public Criteria andAllocateMoneyNotIn(List<BigDecimal> values) {
            addCriterion("ALLOCATE_MONEY not in", values, "allocateMoney");
            return (Criteria) this;
        }

        public Criteria andAllocateMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ALLOCATE_MONEY between", value1, value2, "allocateMoney");
            return (Criteria) this;
        }

        public Criteria andAllocateMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ALLOCATE_MONEY not between", value1, value2, "allocateMoney");
            return (Criteria) this;
        }

        public Criteria andMatchingMoneyIsNull() {
            addCriterion("MATCHING_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andMatchingMoneyIsNotNull() {
            addCriterion("MATCHING_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andMatchingMoneyEqualTo(BigDecimal value) {
            addCriterion("MATCHING_MONEY =", value, "matchingMoney");
            return (Criteria) this;
        }

        public Criteria andMatchingMoneyNotEqualTo(BigDecimal value) {
            addCriterion("MATCHING_MONEY <>", value, "matchingMoney");
            return (Criteria) this;
        }

        public Criteria andMatchingMoneyGreaterThan(BigDecimal value) {
            addCriterion("MATCHING_MONEY >", value, "matchingMoney");
            return (Criteria) this;
        }

        public Criteria andMatchingMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MATCHING_MONEY >=", value, "matchingMoney");
            return (Criteria) this;
        }

        public Criteria andMatchingMoneyLessThan(BigDecimal value) {
            addCriterion("MATCHING_MONEY <", value, "matchingMoney");
            return (Criteria) this;
        }

        public Criteria andMatchingMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MATCHING_MONEY <=", value, "matchingMoney");
            return (Criteria) this;
        }

        public Criteria andMatchingMoneyIn(List<BigDecimal> values) {
            addCriterion("MATCHING_MONEY in", values, "matchingMoney");
            return (Criteria) this;
        }

        public Criteria andMatchingMoneyNotIn(List<BigDecimal> values) {
            addCriterion("MATCHING_MONEY not in", values, "matchingMoney");
            return (Criteria) this;
        }

        public Criteria andMatchingMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MATCHING_MONEY between", value1, value2, "matchingMoney");
            return (Criteria) this;
        }

        public Criteria andMatchingMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MATCHING_MONEY not between", value1, value2, "matchingMoney");
            return (Criteria) this;
        }

        public Criteria andItemPnameIsNull() {
            addCriterion("ITEM_PNAME is null");
            return (Criteria) this;
        }

        public Criteria andItemPnameIsNotNull() {
            addCriterion("ITEM_PNAME is not null");
            return (Criteria) this;
        }

        public Criteria andItemPnameEqualTo(String value) {
            addCriterion("ITEM_PNAME =", value, "itemPname");
            return (Criteria) this;
        }

        public Criteria andItemPnameNotEqualTo(String value) {
            addCriterion("ITEM_PNAME <>", value, "itemPname");
            return (Criteria) this;
        }

        public Criteria andItemPnameGreaterThan(String value) {
            addCriterion("ITEM_PNAME >", value, "itemPname");
            return (Criteria) this;
        }

        public Criteria andItemPnameGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_PNAME >=", value, "itemPname");
            return (Criteria) this;
        }

        public Criteria andItemPnameLessThan(String value) {
            addCriterion("ITEM_PNAME <", value, "itemPname");
            return (Criteria) this;
        }

        public Criteria andItemPnameLessThanOrEqualTo(String value) {
            addCriterion("ITEM_PNAME <=", value, "itemPname");
            return (Criteria) this;
        }

        public Criteria andItemPnameLike(String value) {
            addCriterion("ITEM_PNAME like", value, "itemPname");
            return (Criteria) this;
        }

        public Criteria andItemPnameNotLike(String value) {
            addCriterion("ITEM_PNAME not like", value, "itemPname");
            return (Criteria) this;
        }

        public Criteria andItemPnameIn(List<String> values) {
            addCriterion("ITEM_PNAME in", values, "itemPname");
            return (Criteria) this;
        }

        public Criteria andItemPnameNotIn(List<String> values) {
            addCriterion("ITEM_PNAME not in", values, "itemPname");
            return (Criteria) this;
        }

        public Criteria andItemPnameBetween(String value1, String value2) {
            addCriterion("ITEM_PNAME between", value1, value2, "itemPname");
            return (Criteria) this;
        }

        public Criteria andItemPnameNotBetween(String value1, String value2) {
            addCriterion("ITEM_PNAME not between", value1, value2, "itemPname");
            return (Criteria) this;
        }

        public Criteria andFundSourceIdIsNull() {
            addCriterion("FUND_SOURCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andFundSourceIdIsNotNull() {
            addCriterion("FUND_SOURCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFundSourceIdEqualTo(String value) {
            addCriterion("FUND_SOURCE_ID =", value, "fundSourceId");
            return (Criteria) this;
        }

        public Criteria andFundSourceIdNotEqualTo(String value) {
            addCriterion("FUND_SOURCE_ID <>", value, "fundSourceId");
            return (Criteria) this;
        }

        public Criteria andFundSourceIdGreaterThan(String value) {
            addCriterion("FUND_SOURCE_ID >", value, "fundSourceId");
            return (Criteria) this;
        }

        public Criteria andFundSourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_SOURCE_ID >=", value, "fundSourceId");
            return (Criteria) this;
        }

        public Criteria andFundSourceIdLessThan(String value) {
            addCriterion("FUND_SOURCE_ID <", value, "fundSourceId");
            return (Criteria) this;
        }

        public Criteria andFundSourceIdLessThanOrEqualTo(String value) {
            addCriterion("FUND_SOURCE_ID <=", value, "fundSourceId");
            return (Criteria) this;
        }

        public Criteria andFundSourceIdLike(String value) {
            addCriterion("FUND_SOURCE_ID like", value, "fundSourceId");
            return (Criteria) this;
        }

        public Criteria andFundSourceIdNotLike(String value) {
            addCriterion("FUND_SOURCE_ID not like", value, "fundSourceId");
            return (Criteria) this;
        }

        public Criteria andFundSourceIdIn(List<String> values) {
            addCriterion("FUND_SOURCE_ID in", values, "fundSourceId");
            return (Criteria) this;
        }

        public Criteria andFundSourceIdNotIn(List<String> values) {
            addCriterion("FUND_SOURCE_ID not in", values, "fundSourceId");
            return (Criteria) this;
        }

        public Criteria andFundSourceIdBetween(String value1, String value2) {
            addCriterion("FUND_SOURCE_ID between", value1, value2, "fundSourceId");
            return (Criteria) this;
        }

        public Criteria andFundSourceIdNotBetween(String value1, String value2) {
            addCriterion("FUND_SOURCE_ID not between", value1, value2, "fundSourceId");
            return (Criteria) this;
        }

        public Criteria andFundSourceNameIsNull() {
            addCriterion("FUND_SOURCE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFundSourceNameIsNotNull() {
            addCriterion("FUND_SOURCE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFundSourceNameEqualTo(String value) {
            addCriterion("FUND_SOURCE_NAME =", value, "fundSourceName");
            return (Criteria) this;
        }

        public Criteria andFundSourceNameNotEqualTo(String value) {
            addCriterion("FUND_SOURCE_NAME <>", value, "fundSourceName");
            return (Criteria) this;
        }

        public Criteria andFundSourceNameGreaterThan(String value) {
            addCriterion("FUND_SOURCE_NAME >", value, "fundSourceName");
            return (Criteria) this;
        }

        public Criteria andFundSourceNameGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_SOURCE_NAME >=", value, "fundSourceName");
            return (Criteria) this;
        }

        public Criteria andFundSourceNameLessThan(String value) {
            addCriterion("FUND_SOURCE_NAME <", value, "fundSourceName");
            return (Criteria) this;
        }

        public Criteria andFundSourceNameLessThanOrEqualTo(String value) {
            addCriterion("FUND_SOURCE_NAME <=", value, "fundSourceName");
            return (Criteria) this;
        }

        public Criteria andFundSourceNameLike(String value) {
            addCriterion("FUND_SOURCE_NAME like", value, "fundSourceName");
            return (Criteria) this;
        }

        public Criteria andFundSourceNameNotLike(String value) {
            addCriterion("FUND_SOURCE_NAME not like", value, "fundSourceName");
            return (Criteria) this;
        }

        public Criteria andFundSourceNameIn(List<String> values) {
            addCriterion("FUND_SOURCE_NAME in", values, "fundSourceName");
            return (Criteria) this;
        }

        public Criteria andFundSourceNameNotIn(List<String> values) {
            addCriterion("FUND_SOURCE_NAME not in", values, "fundSourceName");
            return (Criteria) this;
        }

        public Criteria andFundSourceNameBetween(String value1, String value2) {
            addCriterion("FUND_SOURCE_NAME between", value1, value2, "fundSourceName");
            return (Criteria) this;
        }

        public Criteria andFundSourceNameNotBetween(String value1, String value2) {
            addCriterion("FUND_SOURCE_NAME not between", value1, value2, "fundSourceName");
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