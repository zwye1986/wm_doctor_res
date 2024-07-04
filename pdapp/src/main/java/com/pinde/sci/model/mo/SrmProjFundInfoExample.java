package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SrmProjFundInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmProjFundInfoExample() {
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

        public Criteria andProjSourceIdIsNull() {
            addCriterion("PROJ_SOURCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdIsNotNull() {
            addCriterion("PROJ_SOURCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdEqualTo(String value) {
            addCriterion("PROJ_SOURCE_ID =", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdNotEqualTo(String value) {
            addCriterion("PROJ_SOURCE_ID <>", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdGreaterThan(String value) {
            addCriterion("PROJ_SOURCE_ID >", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SOURCE_ID >=", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdLessThan(String value) {
            addCriterion("PROJ_SOURCE_ID <", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SOURCE_ID <=", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdLike(String value) {
            addCriterion("PROJ_SOURCE_ID like", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdNotLike(String value) {
            addCriterion("PROJ_SOURCE_ID not like", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdIn(List<String> values) {
            addCriterion("PROJ_SOURCE_ID in", values, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdNotIn(List<String> values) {
            addCriterion("PROJ_SOURCE_ID not in", values, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdBetween(String value1, String value2) {
            addCriterion("PROJ_SOURCE_ID between", value1, value2, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdNotBetween(String value1, String value2) {
            addCriterion("PROJ_SOURCE_ID not between", value1, value2, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameIsNull() {
            addCriterion("PROJ_SOURCE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameIsNotNull() {
            addCriterion("PROJ_SOURCE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameEqualTo(String value) {
            addCriterion("PROJ_SOURCE_NAME =", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameNotEqualTo(String value) {
            addCriterion("PROJ_SOURCE_NAME <>", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameGreaterThan(String value) {
            addCriterion("PROJ_SOURCE_NAME >", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SOURCE_NAME >=", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameLessThan(String value) {
            addCriterion("PROJ_SOURCE_NAME <", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SOURCE_NAME <=", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameLike(String value) {
            addCriterion("PROJ_SOURCE_NAME like", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameNotLike(String value) {
            addCriterion("PROJ_SOURCE_NAME not like", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameIn(List<String> values) {
            addCriterion("PROJ_SOURCE_NAME in", values, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameNotIn(List<String> values) {
            addCriterion("PROJ_SOURCE_NAME not in", values, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameBetween(String value1, String value2) {
            addCriterion("PROJ_SOURCE_NAME between", value1, value2, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_SOURCE_NAME not between", value1, value2, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusIdIsNull() {
            addCriterion("BUDGET_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusIdIsNotNull() {
            addCriterion("BUDGET_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusIdEqualTo(String value) {
            addCriterion("BUDGET_STATUS_ID =", value, "budgetStatusId");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusIdNotEqualTo(String value) {
            addCriterion("BUDGET_STATUS_ID <>", value, "budgetStatusId");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusIdGreaterThan(String value) {
            addCriterion("BUDGET_STATUS_ID >", value, "budgetStatusId");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("BUDGET_STATUS_ID >=", value, "budgetStatusId");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusIdLessThan(String value) {
            addCriterion("BUDGET_STATUS_ID <", value, "budgetStatusId");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusIdLessThanOrEqualTo(String value) {
            addCriterion("BUDGET_STATUS_ID <=", value, "budgetStatusId");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusIdLike(String value) {
            addCriterion("BUDGET_STATUS_ID like", value, "budgetStatusId");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusIdNotLike(String value) {
            addCriterion("BUDGET_STATUS_ID not like", value, "budgetStatusId");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusIdIn(List<String> values) {
            addCriterion("BUDGET_STATUS_ID in", values, "budgetStatusId");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusIdNotIn(List<String> values) {
            addCriterion("BUDGET_STATUS_ID not in", values, "budgetStatusId");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusIdBetween(String value1, String value2) {
            addCriterion("BUDGET_STATUS_ID between", value1, value2, "budgetStatusId");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusIdNotBetween(String value1, String value2) {
            addCriterion("BUDGET_STATUS_ID not between", value1, value2, "budgetStatusId");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusNameIsNull() {
            addCriterion("BUDGET_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusNameIsNotNull() {
            addCriterion("BUDGET_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusNameEqualTo(String value) {
            addCriterion("BUDGET_STATUS_NAME =", value, "budgetStatusName");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusNameNotEqualTo(String value) {
            addCriterion("BUDGET_STATUS_NAME <>", value, "budgetStatusName");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusNameGreaterThan(String value) {
            addCriterion("BUDGET_STATUS_NAME >", value, "budgetStatusName");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("BUDGET_STATUS_NAME >=", value, "budgetStatusName");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusNameLessThan(String value) {
            addCriterion("BUDGET_STATUS_NAME <", value, "budgetStatusName");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusNameLessThanOrEqualTo(String value) {
            addCriterion("BUDGET_STATUS_NAME <=", value, "budgetStatusName");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusNameLike(String value) {
            addCriterion("BUDGET_STATUS_NAME like", value, "budgetStatusName");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusNameNotLike(String value) {
            addCriterion("BUDGET_STATUS_NAME not like", value, "budgetStatusName");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusNameIn(List<String> values) {
            addCriterion("BUDGET_STATUS_NAME in", values, "budgetStatusName");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusNameNotIn(List<String> values) {
            addCriterion("BUDGET_STATUS_NAME not in", values, "budgetStatusName");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusNameBetween(String value1, String value2) {
            addCriterion("BUDGET_STATUS_NAME between", value1, value2, "budgetStatusName");
            return (Criteria) this;
        }

        public Criteria andBudgetStatusNameNotBetween(String value1, String value2) {
            addCriterion("BUDGET_STATUS_NAME not between", value1, value2, "budgetStatusName");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIsNull() {
            addCriterion("DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIsNotNull() {
            addCriterion("DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowEqualTo(String value) {
            addCriterion("DEPT_FLOW =", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotEqualTo(String value) {
            addCriterion("DEPT_FLOW <>", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThan(String value) {
            addCriterion("DEPT_FLOW >", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW >=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThan(String value) {
            addCriterion("DEPT_FLOW <", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW <=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLike(String value) {
            addCriterion("DEPT_FLOW like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotLike(String value) {
            addCriterion("DEPT_FLOW not like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIn(List<String> values) {
            addCriterion("DEPT_FLOW in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotIn(List<String> values) {
            addCriterion("DEPT_FLOW not in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW not between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andAmountFundIsNull() {
            addCriterion("AMOUNT_FUND is null");
            return (Criteria) this;
        }

        public Criteria andAmountFundIsNotNull() {
            addCriterion("AMOUNT_FUND is not null");
            return (Criteria) this;
        }

        public Criteria andAmountFundEqualTo(BigDecimal value) {
            addCriterion("AMOUNT_FUND =", value, "amountFund");
            return (Criteria) this;
        }

        public Criteria andAmountFundNotEqualTo(BigDecimal value) {
            addCriterion("AMOUNT_FUND <>", value, "amountFund");
            return (Criteria) this;
        }

        public Criteria andAmountFundGreaterThan(BigDecimal value) {
            addCriterion("AMOUNT_FUND >", value, "amountFund");
            return (Criteria) this;
        }

        public Criteria andAmountFundGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AMOUNT_FUND >=", value, "amountFund");
            return (Criteria) this;
        }

        public Criteria andAmountFundLessThan(BigDecimal value) {
            addCriterion("AMOUNT_FUND <", value, "amountFund");
            return (Criteria) this;
        }

        public Criteria andAmountFundLessThanOrEqualTo(BigDecimal value) {
            addCriterion("AMOUNT_FUND <=", value, "amountFund");
            return (Criteria) this;
        }

        public Criteria andAmountFundIn(List<BigDecimal> values) {
            addCriterion("AMOUNT_FUND in", values, "amountFund");
            return (Criteria) this;
        }

        public Criteria andAmountFundNotIn(List<BigDecimal> values) {
            addCriterion("AMOUNT_FUND not in", values, "amountFund");
            return (Criteria) this;
        }

        public Criteria andAmountFundBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMOUNT_FUND between", value1, value2, "amountFund");
            return (Criteria) this;
        }

        public Criteria andAmountFundNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AMOUNT_FUND not between", value1, value2, "amountFund");
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

        public Criteria andBudgetAmountIsNull() {
            addCriterion("BUDGET_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andBudgetAmountIsNotNull() {
            addCriterion("BUDGET_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andBudgetAmountEqualTo(BigDecimal value) {
            addCriterion("BUDGET_AMOUNT =", value, "budgetAmount");
            return (Criteria) this;
        }

        public Criteria andBudgetAmountNotEqualTo(BigDecimal value) {
            addCriterion("BUDGET_AMOUNT <>", value, "budgetAmount");
            return (Criteria) this;
        }

        public Criteria andBudgetAmountGreaterThan(BigDecimal value) {
            addCriterion("BUDGET_AMOUNT >", value, "budgetAmount");
            return (Criteria) this;
        }

        public Criteria andBudgetAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BUDGET_AMOUNT >=", value, "budgetAmount");
            return (Criteria) this;
        }

        public Criteria andBudgetAmountLessThan(BigDecimal value) {
            addCriterion("BUDGET_AMOUNT <", value, "budgetAmount");
            return (Criteria) this;
        }

        public Criteria andBudgetAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("BUDGET_AMOUNT <=", value, "budgetAmount");
            return (Criteria) this;
        }

        public Criteria andBudgetAmountIn(List<BigDecimal> values) {
            addCriterion("BUDGET_AMOUNT in", values, "budgetAmount");
            return (Criteria) this;
        }

        public Criteria andBudgetAmountNotIn(List<BigDecimal> values) {
            addCriterion("BUDGET_AMOUNT not in", values, "budgetAmount");
            return (Criteria) this;
        }

        public Criteria andBudgetAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BUDGET_AMOUNT between", value1, value2, "budgetAmount");
            return (Criteria) this;
        }

        public Criteria andBudgetAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BUDGET_AMOUNT not between", value1, value2, "budgetAmount");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowIsNull() {
            addCriterion("SCHEME_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowIsNotNull() {
            addCriterion("SCHEME_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowEqualTo(String value) {
            addCriterion("SCHEME_FLOW =", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowNotEqualTo(String value) {
            addCriterion("SCHEME_FLOW <>", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowGreaterThan(String value) {
            addCriterion("SCHEME_FLOW >", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCHEME_FLOW >=", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowLessThan(String value) {
            addCriterion("SCHEME_FLOW <", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowLessThanOrEqualTo(String value) {
            addCriterion("SCHEME_FLOW <=", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowLike(String value) {
            addCriterion("SCHEME_FLOW like", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowNotLike(String value) {
            addCriterion("SCHEME_FLOW not like", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowIn(List<String> values) {
            addCriterion("SCHEME_FLOW in", values, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowNotIn(List<String> values) {
            addCriterion("SCHEME_FLOW not in", values, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowBetween(String value1, String value2) {
            addCriterion("SCHEME_FLOW between", value1, value2, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowNotBetween(String value1, String value2) {
            addCriterion("SCHEME_FLOW not between", value1, value2, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andRealityAmountIsNull() {
            addCriterion("REALITY_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andRealityAmountIsNotNull() {
            addCriterion("REALITY_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andRealityAmountEqualTo(BigDecimal value) {
            addCriterion("REALITY_AMOUNT =", value, "realityAmount");
            return (Criteria) this;
        }

        public Criteria andRealityAmountNotEqualTo(BigDecimal value) {
            addCriterion("REALITY_AMOUNT <>", value, "realityAmount");
            return (Criteria) this;
        }

        public Criteria andRealityAmountGreaterThan(BigDecimal value) {
            addCriterion("REALITY_AMOUNT >", value, "realityAmount");
            return (Criteria) this;
        }

        public Criteria andRealityAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("REALITY_AMOUNT >=", value, "realityAmount");
            return (Criteria) this;
        }

        public Criteria andRealityAmountLessThan(BigDecimal value) {
            addCriterion("REALITY_AMOUNT <", value, "realityAmount");
            return (Criteria) this;
        }

        public Criteria andRealityAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("REALITY_AMOUNT <=", value, "realityAmount");
            return (Criteria) this;
        }

        public Criteria andRealityAmountIn(List<BigDecimal> values) {
            addCriterion("REALITY_AMOUNT in", values, "realityAmount");
            return (Criteria) this;
        }

        public Criteria andRealityAmountNotIn(List<BigDecimal> values) {
            addCriterion("REALITY_AMOUNT not in", values, "realityAmount");
            return (Criteria) this;
        }

        public Criteria andRealityAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REALITY_AMOUNT between", value1, value2, "realityAmount");
            return (Criteria) this;
        }

        public Criteria andRealityAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REALITY_AMOUNT not between", value1, value2, "realityAmount");
            return (Criteria) this;
        }

        public Criteria andYetPaymentAmountIsNull() {
            addCriterion("YET_PAYMENT_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andYetPaymentAmountIsNotNull() {
            addCriterion("YET_PAYMENT_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andYetPaymentAmountEqualTo(BigDecimal value) {
            addCriterion("YET_PAYMENT_AMOUNT =", value, "yetPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andYetPaymentAmountNotEqualTo(BigDecimal value) {
            addCriterion("YET_PAYMENT_AMOUNT <>", value, "yetPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andYetPaymentAmountGreaterThan(BigDecimal value) {
            addCriterion("YET_PAYMENT_AMOUNT >", value, "yetPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andYetPaymentAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("YET_PAYMENT_AMOUNT >=", value, "yetPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andYetPaymentAmountLessThan(BigDecimal value) {
            addCriterion("YET_PAYMENT_AMOUNT <", value, "yetPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andYetPaymentAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("YET_PAYMENT_AMOUNT <=", value, "yetPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andYetPaymentAmountIn(List<BigDecimal> values) {
            addCriterion("YET_PAYMENT_AMOUNT in", values, "yetPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andYetPaymentAmountNotIn(List<BigDecimal> values) {
            addCriterion("YET_PAYMENT_AMOUNT not in", values, "yetPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andYetPaymentAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("YET_PAYMENT_AMOUNT between", value1, value2, "yetPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andYetPaymentAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("YET_PAYMENT_AMOUNT not between", value1, value2, "yetPaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRealityBalanceIsNull() {
            addCriterion("REALITY_BALANCE is null");
            return (Criteria) this;
        }

        public Criteria andRealityBalanceIsNotNull() {
            addCriterion("REALITY_BALANCE is not null");
            return (Criteria) this;
        }

        public Criteria andRealityBalanceEqualTo(BigDecimal value) {
            addCriterion("REALITY_BALANCE =", value, "realityBalance");
            return (Criteria) this;
        }

        public Criteria andRealityBalanceNotEqualTo(BigDecimal value) {
            addCriterion("REALITY_BALANCE <>", value, "realityBalance");
            return (Criteria) this;
        }

        public Criteria andRealityBalanceGreaterThan(BigDecimal value) {
            addCriterion("REALITY_BALANCE >", value, "realityBalance");
            return (Criteria) this;
        }

        public Criteria andRealityBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("REALITY_BALANCE >=", value, "realityBalance");
            return (Criteria) this;
        }

        public Criteria andRealityBalanceLessThan(BigDecimal value) {
            addCriterion("REALITY_BALANCE <", value, "realityBalance");
            return (Criteria) this;
        }

        public Criteria andRealityBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("REALITY_BALANCE <=", value, "realityBalance");
            return (Criteria) this;
        }

        public Criteria andRealityBalanceIn(List<BigDecimal> values) {
            addCriterion("REALITY_BALANCE in", values, "realityBalance");
            return (Criteria) this;
        }

        public Criteria andRealityBalanceNotIn(List<BigDecimal> values) {
            addCriterion("REALITY_BALANCE not in", values, "realityBalance");
            return (Criteria) this;
        }

        public Criteria andRealityBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REALITY_BALANCE between", value1, value2, "realityBalance");
            return (Criteria) this;
        }

        public Criteria andRealityBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REALITY_BALANCE not between", value1, value2, "realityBalance");
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

        public Criteria andFundIncomeTimeIsNull() {
            addCriterion("FUND_INCOME_TIME is null");
            return (Criteria) this;
        }

        public Criteria andFundIncomeTimeIsNotNull() {
            addCriterion("FUND_INCOME_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andFundIncomeTimeEqualTo(String value) {
            addCriterion("FUND_INCOME_TIME =", value, "fundIncomeTime");
            return (Criteria) this;
        }

        public Criteria andFundIncomeTimeNotEqualTo(String value) {
            addCriterion("FUND_INCOME_TIME <>", value, "fundIncomeTime");
            return (Criteria) this;
        }

        public Criteria andFundIncomeTimeGreaterThan(String value) {
            addCriterion("FUND_INCOME_TIME >", value, "fundIncomeTime");
            return (Criteria) this;
        }

        public Criteria andFundIncomeTimeGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_INCOME_TIME >=", value, "fundIncomeTime");
            return (Criteria) this;
        }

        public Criteria andFundIncomeTimeLessThan(String value) {
            addCriterion("FUND_INCOME_TIME <", value, "fundIncomeTime");
            return (Criteria) this;
        }

        public Criteria andFundIncomeTimeLessThanOrEqualTo(String value) {
            addCriterion("FUND_INCOME_TIME <=", value, "fundIncomeTime");
            return (Criteria) this;
        }

        public Criteria andFundIncomeTimeLike(String value) {
            addCriterion("FUND_INCOME_TIME like", value, "fundIncomeTime");
            return (Criteria) this;
        }

        public Criteria andFundIncomeTimeNotLike(String value) {
            addCriterion("FUND_INCOME_TIME not like", value, "fundIncomeTime");
            return (Criteria) this;
        }

        public Criteria andFundIncomeTimeIn(List<String> values) {
            addCriterion("FUND_INCOME_TIME in", values, "fundIncomeTime");
            return (Criteria) this;
        }

        public Criteria andFundIncomeTimeNotIn(List<String> values) {
            addCriterion("FUND_INCOME_TIME not in", values, "fundIncomeTime");
            return (Criteria) this;
        }

        public Criteria andFundIncomeTimeBetween(String value1, String value2) {
            addCriterion("FUND_INCOME_TIME between", value1, value2, "fundIncomeTime");
            return (Criteria) this;
        }

        public Criteria andFundIncomeTimeNotBetween(String value1, String value2) {
            addCriterion("FUND_INCOME_TIME not between", value1, value2, "fundIncomeTime");
            return (Criteria) this;
        }

        public Criteria andBudgetGoveIsNull() {
            addCriterion("BUDGET_GOVE is null");
            return (Criteria) this;
        }

        public Criteria andBudgetGoveIsNotNull() {
            addCriterion("BUDGET_GOVE is not null");
            return (Criteria) this;
        }

        public Criteria andBudgetGoveEqualTo(BigDecimal value) {
            addCriterion("BUDGET_GOVE =", value, "budgetGove");
            return (Criteria) this;
        }

        public Criteria andBudgetGoveNotEqualTo(BigDecimal value) {
            addCriterion("BUDGET_GOVE <>", value, "budgetGove");
            return (Criteria) this;
        }

        public Criteria andBudgetGoveGreaterThan(BigDecimal value) {
            addCriterion("BUDGET_GOVE >", value, "budgetGove");
            return (Criteria) this;
        }

        public Criteria andBudgetGoveGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BUDGET_GOVE >=", value, "budgetGove");
            return (Criteria) this;
        }

        public Criteria andBudgetGoveLessThan(BigDecimal value) {
            addCriterion("BUDGET_GOVE <", value, "budgetGove");
            return (Criteria) this;
        }

        public Criteria andBudgetGoveLessThanOrEqualTo(BigDecimal value) {
            addCriterion("BUDGET_GOVE <=", value, "budgetGove");
            return (Criteria) this;
        }

        public Criteria andBudgetGoveIn(List<BigDecimal> values) {
            addCriterion("BUDGET_GOVE in", values, "budgetGove");
            return (Criteria) this;
        }

        public Criteria andBudgetGoveNotIn(List<BigDecimal> values) {
            addCriterion("BUDGET_GOVE not in", values, "budgetGove");
            return (Criteria) this;
        }

        public Criteria andBudgetGoveBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BUDGET_GOVE between", value1, value2, "budgetGove");
            return (Criteria) this;
        }

        public Criteria andBudgetGoveNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BUDGET_GOVE not between", value1, value2, "budgetGove");
            return (Criteria) this;
        }

        public Criteria andBudgetOrgIsNull() {
            addCriterion("BUDGET_ORG is null");
            return (Criteria) this;
        }

        public Criteria andBudgetOrgIsNotNull() {
            addCriterion("BUDGET_ORG is not null");
            return (Criteria) this;
        }

        public Criteria andBudgetOrgEqualTo(BigDecimal value) {
            addCriterion("BUDGET_ORG =", value, "budgetOrg");
            return (Criteria) this;
        }

        public Criteria andBudgetOrgNotEqualTo(BigDecimal value) {
            addCriterion("BUDGET_ORG <>", value, "budgetOrg");
            return (Criteria) this;
        }

        public Criteria andBudgetOrgGreaterThan(BigDecimal value) {
            addCriterion("BUDGET_ORG >", value, "budgetOrg");
            return (Criteria) this;
        }

        public Criteria andBudgetOrgGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BUDGET_ORG >=", value, "budgetOrg");
            return (Criteria) this;
        }

        public Criteria andBudgetOrgLessThan(BigDecimal value) {
            addCriterion("BUDGET_ORG <", value, "budgetOrg");
            return (Criteria) this;
        }

        public Criteria andBudgetOrgLessThanOrEqualTo(BigDecimal value) {
            addCriterion("BUDGET_ORG <=", value, "budgetOrg");
            return (Criteria) this;
        }

        public Criteria andBudgetOrgIn(List<BigDecimal> values) {
            addCriterion("BUDGET_ORG in", values, "budgetOrg");
            return (Criteria) this;
        }

        public Criteria andBudgetOrgNotIn(List<BigDecimal> values) {
            addCriterion("BUDGET_ORG not in", values, "budgetOrg");
            return (Criteria) this;
        }

        public Criteria andBudgetOrgBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BUDGET_ORG between", value1, value2, "budgetOrg");
            return (Criteria) this;
        }

        public Criteria andBudgetOrgNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BUDGET_ORG not between", value1, value2, "budgetOrg");
            return (Criteria) this;
        }

        public Criteria andYetPaymentGoveIsNull() {
            addCriterion("YET_PAYMENT_GOVE is null");
            return (Criteria) this;
        }

        public Criteria andYetPaymentGoveIsNotNull() {
            addCriterion("YET_PAYMENT_GOVE is not null");
            return (Criteria) this;
        }

        public Criteria andYetPaymentGoveEqualTo(BigDecimal value) {
            addCriterion("YET_PAYMENT_GOVE =", value, "yetPaymentGove");
            return (Criteria) this;
        }

        public Criteria andYetPaymentGoveNotEqualTo(BigDecimal value) {
            addCriterion("YET_PAYMENT_GOVE <>", value, "yetPaymentGove");
            return (Criteria) this;
        }

        public Criteria andYetPaymentGoveGreaterThan(BigDecimal value) {
            addCriterion("YET_PAYMENT_GOVE >", value, "yetPaymentGove");
            return (Criteria) this;
        }

        public Criteria andYetPaymentGoveGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("YET_PAYMENT_GOVE >=", value, "yetPaymentGove");
            return (Criteria) this;
        }

        public Criteria andYetPaymentGoveLessThan(BigDecimal value) {
            addCriterion("YET_PAYMENT_GOVE <", value, "yetPaymentGove");
            return (Criteria) this;
        }

        public Criteria andYetPaymentGoveLessThanOrEqualTo(BigDecimal value) {
            addCriterion("YET_PAYMENT_GOVE <=", value, "yetPaymentGove");
            return (Criteria) this;
        }

        public Criteria andYetPaymentGoveIn(List<BigDecimal> values) {
            addCriterion("YET_PAYMENT_GOVE in", values, "yetPaymentGove");
            return (Criteria) this;
        }

        public Criteria andYetPaymentGoveNotIn(List<BigDecimal> values) {
            addCriterion("YET_PAYMENT_GOVE not in", values, "yetPaymentGove");
            return (Criteria) this;
        }

        public Criteria andYetPaymentGoveBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("YET_PAYMENT_GOVE between", value1, value2, "yetPaymentGove");
            return (Criteria) this;
        }

        public Criteria andYetPaymentGoveNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("YET_PAYMENT_GOVE not between", value1, value2, "yetPaymentGove");
            return (Criteria) this;
        }

        public Criteria andYetPaymentOrgIsNull() {
            addCriterion("YET_PAYMENT_ORG is null");
            return (Criteria) this;
        }

        public Criteria andYetPaymentOrgIsNotNull() {
            addCriterion("YET_PAYMENT_ORG is not null");
            return (Criteria) this;
        }

        public Criteria andYetPaymentOrgEqualTo(BigDecimal value) {
            addCriterion("YET_PAYMENT_ORG =", value, "yetPaymentOrg");
            return (Criteria) this;
        }

        public Criteria andYetPaymentOrgNotEqualTo(BigDecimal value) {
            addCriterion("YET_PAYMENT_ORG <>", value, "yetPaymentOrg");
            return (Criteria) this;
        }

        public Criteria andYetPaymentOrgGreaterThan(BigDecimal value) {
            addCriterion("YET_PAYMENT_ORG >", value, "yetPaymentOrg");
            return (Criteria) this;
        }

        public Criteria andYetPaymentOrgGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("YET_PAYMENT_ORG >=", value, "yetPaymentOrg");
            return (Criteria) this;
        }

        public Criteria andYetPaymentOrgLessThan(BigDecimal value) {
            addCriterion("YET_PAYMENT_ORG <", value, "yetPaymentOrg");
            return (Criteria) this;
        }

        public Criteria andYetPaymentOrgLessThanOrEqualTo(BigDecimal value) {
            addCriterion("YET_PAYMENT_ORG <=", value, "yetPaymentOrg");
            return (Criteria) this;
        }

        public Criteria andYetPaymentOrgIn(List<BigDecimal> values) {
            addCriterion("YET_PAYMENT_ORG in", values, "yetPaymentOrg");
            return (Criteria) this;
        }

        public Criteria andYetPaymentOrgNotIn(List<BigDecimal> values) {
            addCriterion("YET_PAYMENT_ORG not in", values, "yetPaymentOrg");
            return (Criteria) this;
        }

        public Criteria andYetPaymentOrgBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("YET_PAYMENT_ORG between", value1, value2, "yetPaymentOrg");
            return (Criteria) this;
        }

        public Criteria andYetPaymentOrgNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("YET_PAYMENT_ORG not between", value1, value2, "yetPaymentOrg");
            return (Criteria) this;
        }

        public Criteria andSurplusFundIsNull() {
            addCriterion("SURPLUS_FUND is null");
            return (Criteria) this;
        }

        public Criteria andSurplusFundIsNotNull() {
            addCriterion("SURPLUS_FUND is not null");
            return (Criteria) this;
        }

        public Criteria andSurplusFundEqualTo(BigDecimal value) {
            addCriterion("SURPLUS_FUND =", value, "surplusFund");
            return (Criteria) this;
        }

        public Criteria andSurplusFundNotEqualTo(BigDecimal value) {
            addCriterion("SURPLUS_FUND <>", value, "surplusFund");
            return (Criteria) this;
        }

        public Criteria andSurplusFundGreaterThan(BigDecimal value) {
            addCriterion("SURPLUS_FUND >", value, "surplusFund");
            return (Criteria) this;
        }

        public Criteria andSurplusFundGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SURPLUS_FUND >=", value, "surplusFund");
            return (Criteria) this;
        }

        public Criteria andSurplusFundLessThan(BigDecimal value) {
            addCriterion("SURPLUS_FUND <", value, "surplusFund");
            return (Criteria) this;
        }

        public Criteria andSurplusFundLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SURPLUS_FUND <=", value, "surplusFund");
            return (Criteria) this;
        }

        public Criteria andSurplusFundIn(List<BigDecimal> values) {
            addCriterion("SURPLUS_FUND in", values, "surplusFund");
            return (Criteria) this;
        }

        public Criteria andSurplusFundNotIn(List<BigDecimal> values) {
            addCriterion("SURPLUS_FUND not in", values, "surplusFund");
            return (Criteria) this;
        }

        public Criteria andSurplusFundBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SURPLUS_FUND between", value1, value2, "surplusFund");
            return (Criteria) this;
        }

        public Criteria andSurplusFundNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SURPLUS_FUND not between", value1, value2, "surplusFund");
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

        public Criteria andAccountTypeIdIsNull() {
            addCriterion("ACCOUNT_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIdIsNotNull() {
            addCriterion("ACCOUNT_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIdEqualTo(String value) {
            addCriterion("ACCOUNT_TYPE_ID =", value, "accountTypeId");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIdNotEqualTo(String value) {
            addCriterion("ACCOUNT_TYPE_ID <>", value, "accountTypeId");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIdGreaterThan(String value) {
            addCriterion("ACCOUNT_TYPE_ID >", value, "accountTypeId");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_TYPE_ID >=", value, "accountTypeId");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIdLessThan(String value) {
            addCriterion("ACCOUNT_TYPE_ID <", value, "accountTypeId");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_TYPE_ID <=", value, "accountTypeId");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIdLike(String value) {
            addCriterion("ACCOUNT_TYPE_ID like", value, "accountTypeId");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIdNotLike(String value) {
            addCriterion("ACCOUNT_TYPE_ID not like", value, "accountTypeId");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIdIn(List<String> values) {
            addCriterion("ACCOUNT_TYPE_ID in", values, "accountTypeId");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIdNotIn(List<String> values) {
            addCriterion("ACCOUNT_TYPE_ID not in", values, "accountTypeId");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIdBetween(String value1, String value2) {
            addCriterion("ACCOUNT_TYPE_ID between", value1, value2, "accountTypeId");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIdNotBetween(String value1, String value2) {
            addCriterion("ACCOUNT_TYPE_ID not between", value1, value2, "accountTypeId");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNameIsNull() {
            addCriterion("ACCOUNT_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNameIsNotNull() {
            addCriterion("ACCOUNT_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNameEqualTo(String value) {
            addCriterion("ACCOUNT_TYPE_NAME =", value, "accountTypeName");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNameNotEqualTo(String value) {
            addCriterion("ACCOUNT_TYPE_NAME <>", value, "accountTypeName");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNameGreaterThan(String value) {
            addCriterion("ACCOUNT_TYPE_NAME >", value, "accountTypeName");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_TYPE_NAME >=", value, "accountTypeName");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNameLessThan(String value) {
            addCriterion("ACCOUNT_TYPE_NAME <", value, "accountTypeName");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNameLessThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_TYPE_NAME <=", value, "accountTypeName");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNameLike(String value) {
            addCriterion("ACCOUNT_TYPE_NAME like", value, "accountTypeName");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNameNotLike(String value) {
            addCriterion("ACCOUNT_TYPE_NAME not like", value, "accountTypeName");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNameIn(List<String> values) {
            addCriterion("ACCOUNT_TYPE_NAME in", values, "accountTypeName");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNameNotIn(List<String> values) {
            addCriterion("ACCOUNT_TYPE_NAME not in", values, "accountTypeName");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNameBetween(String value1, String value2) {
            addCriterion("ACCOUNT_TYPE_NAME between", value1, value2, "accountTypeName");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNameNotBetween(String value1, String value2) {
            addCriterion("ACCOUNT_TYPE_NAME not between", value1, value2, "accountTypeName");
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