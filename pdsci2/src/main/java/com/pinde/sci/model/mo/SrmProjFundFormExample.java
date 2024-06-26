package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SrmProjFundFormExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmProjFundFormExample() {
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

        public Criteria andOrgFundIsNull() {
            addCriterion("ORG_FUND is null");
            return (Criteria) this;
        }

        public Criteria andOrgFundIsNotNull() {
            addCriterion("ORG_FUND is not null");
            return (Criteria) this;
        }

        public Criteria andOrgFundEqualTo(BigDecimal value) {
            addCriterion("ORG_FUND =", value, "orgFund");
            return (Criteria) this;
        }

        public Criteria andOrgFundNotEqualTo(BigDecimal value) {
            addCriterion("ORG_FUND <>", value, "orgFund");
            return (Criteria) this;
        }

        public Criteria andOrgFundGreaterThan(BigDecimal value) {
            addCriterion("ORG_FUND >", value, "orgFund");
            return (Criteria) this;
        }

        public Criteria andOrgFundGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ORG_FUND >=", value, "orgFund");
            return (Criteria) this;
        }

        public Criteria andOrgFundLessThan(BigDecimal value) {
            addCriterion("ORG_FUND <", value, "orgFund");
            return (Criteria) this;
        }

        public Criteria andOrgFundLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ORG_FUND <=", value, "orgFund");
            return (Criteria) this;
        }

        public Criteria andOrgFundIn(List<BigDecimal> values) {
            addCriterion("ORG_FUND in", values, "orgFund");
            return (Criteria) this;
        }

        public Criteria andOrgFundNotIn(List<BigDecimal> values) {
            addCriterion("ORG_FUND not in", values, "orgFund");
            return (Criteria) this;
        }

        public Criteria andOrgFundBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORG_FUND between", value1, value2, "orgFund");
            return (Criteria) this;
        }

        public Criteria andOrgFundNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORG_FUND not between", value1, value2, "orgFund");
            return (Criteria) this;
        }

        public Criteria andGoveFundIsNull() {
            addCriterion("GOVE_FUND is null");
            return (Criteria) this;
        }

        public Criteria andGoveFundIsNotNull() {
            addCriterion("GOVE_FUND is not null");
            return (Criteria) this;
        }

        public Criteria andGoveFundEqualTo(BigDecimal value) {
            addCriterion("GOVE_FUND =", value, "goveFund");
            return (Criteria) this;
        }

        public Criteria andGoveFundNotEqualTo(BigDecimal value) {
            addCriterion("GOVE_FUND <>", value, "goveFund");
            return (Criteria) this;
        }

        public Criteria andGoveFundGreaterThan(BigDecimal value) {
            addCriterion("GOVE_FUND >", value, "goveFund");
            return (Criteria) this;
        }

        public Criteria andGoveFundGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("GOVE_FUND >=", value, "goveFund");
            return (Criteria) this;
        }

        public Criteria andGoveFundLessThan(BigDecimal value) {
            addCriterion("GOVE_FUND <", value, "goveFund");
            return (Criteria) this;
        }

        public Criteria andGoveFundLessThanOrEqualTo(BigDecimal value) {
            addCriterion("GOVE_FUND <=", value, "goveFund");
            return (Criteria) this;
        }

        public Criteria andGoveFundIn(List<BigDecimal> values) {
            addCriterion("GOVE_FUND in", values, "goveFund");
            return (Criteria) this;
        }

        public Criteria andGoveFundNotIn(List<BigDecimal> values) {
            addCriterion("GOVE_FUND not in", values, "goveFund");
            return (Criteria) this;
        }

        public Criteria andGoveFundBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GOVE_FUND between", value1, value2, "goveFund");
            return (Criteria) this;
        }

        public Criteria andGoveFundNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GOVE_FUND not between", value1, value2, "goveFund");
            return (Criteria) this;
        }

        public Criteria andRealityGoveAmountIsNull() {
            addCriterion("REALITY_GOVE_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andRealityGoveAmountIsNotNull() {
            addCriterion("REALITY_GOVE_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andRealityGoveAmountEqualTo(BigDecimal value) {
            addCriterion("REALITY_GOVE_AMOUNT =", value, "realityGoveAmount");
            return (Criteria) this;
        }

        public Criteria andRealityGoveAmountNotEqualTo(BigDecimal value) {
            addCriterion("REALITY_GOVE_AMOUNT <>", value, "realityGoveAmount");
            return (Criteria) this;
        }

        public Criteria andRealityGoveAmountGreaterThan(BigDecimal value) {
            addCriterion("REALITY_GOVE_AMOUNT >", value, "realityGoveAmount");
            return (Criteria) this;
        }

        public Criteria andRealityGoveAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("REALITY_GOVE_AMOUNT >=", value, "realityGoveAmount");
            return (Criteria) this;
        }

        public Criteria andRealityGoveAmountLessThan(BigDecimal value) {
            addCriterion("REALITY_GOVE_AMOUNT <", value, "realityGoveAmount");
            return (Criteria) this;
        }

        public Criteria andRealityGoveAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("REALITY_GOVE_AMOUNT <=", value, "realityGoveAmount");
            return (Criteria) this;
        }

        public Criteria andRealityGoveAmountIn(List<BigDecimal> values) {
            addCriterion("REALITY_GOVE_AMOUNT in", values, "realityGoveAmount");
            return (Criteria) this;
        }

        public Criteria andRealityGoveAmountNotIn(List<BigDecimal> values) {
            addCriterion("REALITY_GOVE_AMOUNT not in", values, "realityGoveAmount");
            return (Criteria) this;
        }

        public Criteria andRealityGoveAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REALITY_GOVE_AMOUNT between", value1, value2, "realityGoveAmount");
            return (Criteria) this;
        }

        public Criteria andRealityGoveAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REALITY_GOVE_AMOUNT not between", value1, value2, "realityGoveAmount");
            return (Criteria) this;
        }

        public Criteria andRealityOrgAmountIsNull() {
            addCriterion("REALITY_ORG_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andRealityOrgAmountIsNotNull() {
            addCriterion("REALITY_ORG_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andRealityOrgAmountEqualTo(BigDecimal value) {
            addCriterion("REALITY_ORG_AMOUNT =", value, "realityOrgAmount");
            return (Criteria) this;
        }

        public Criteria andRealityOrgAmountNotEqualTo(BigDecimal value) {
            addCriterion("REALITY_ORG_AMOUNT <>", value, "realityOrgAmount");
            return (Criteria) this;
        }

        public Criteria andRealityOrgAmountGreaterThan(BigDecimal value) {
            addCriterion("REALITY_ORG_AMOUNT >", value, "realityOrgAmount");
            return (Criteria) this;
        }

        public Criteria andRealityOrgAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("REALITY_ORG_AMOUNT >=", value, "realityOrgAmount");
            return (Criteria) this;
        }

        public Criteria andRealityOrgAmountLessThan(BigDecimal value) {
            addCriterion("REALITY_ORG_AMOUNT <", value, "realityOrgAmount");
            return (Criteria) this;
        }

        public Criteria andRealityOrgAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("REALITY_ORG_AMOUNT <=", value, "realityOrgAmount");
            return (Criteria) this;
        }

        public Criteria andRealityOrgAmountIn(List<BigDecimal> values) {
            addCriterion("REALITY_ORG_AMOUNT in", values, "realityOrgAmount");
            return (Criteria) this;
        }

        public Criteria andRealityOrgAmountNotIn(List<BigDecimal> values) {
            addCriterion("REALITY_ORG_AMOUNT not in", values, "realityOrgAmount");
            return (Criteria) this;
        }

        public Criteria andRealityOrgAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REALITY_ORG_AMOUNT between", value1, value2, "realityOrgAmount");
            return (Criteria) this;
        }

        public Criteria andRealityOrgAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REALITY_ORG_AMOUNT not between", value1, value2, "realityOrgAmount");
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