package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GcpFundExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GcpFundExample() {
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

        public Criteria andFundNoIsNull() {
            addCriterion("FUND_NO is null");
            return (Criteria) this;
        }

        public Criteria andFundNoIsNotNull() {
            addCriterion("FUND_NO is not null");
            return (Criteria) this;
        }

        public Criteria andFundNoEqualTo(String value) {
            addCriterion("FUND_NO =", value, "fundNo");
            return (Criteria) this;
        }

        public Criteria andFundNoNotEqualTo(String value) {
            addCriterion("FUND_NO <>", value, "fundNo");
            return (Criteria) this;
        }

        public Criteria andFundNoGreaterThan(String value) {
            addCriterion("FUND_NO >", value, "fundNo");
            return (Criteria) this;
        }

        public Criteria andFundNoGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_NO >=", value, "fundNo");
            return (Criteria) this;
        }

        public Criteria andFundNoLessThan(String value) {
            addCriterion("FUND_NO <", value, "fundNo");
            return (Criteria) this;
        }

        public Criteria andFundNoLessThanOrEqualTo(String value) {
            addCriterion("FUND_NO <=", value, "fundNo");
            return (Criteria) this;
        }

        public Criteria andFundNoLike(String value) {
            addCriterion("FUND_NO like", value, "fundNo");
            return (Criteria) this;
        }

        public Criteria andFundNoNotLike(String value) {
            addCriterion("FUND_NO not like", value, "fundNo");
            return (Criteria) this;
        }

        public Criteria andFundNoIn(List<String> values) {
            addCriterion("FUND_NO in", values, "fundNo");
            return (Criteria) this;
        }

        public Criteria andFundNoNotIn(List<String> values) {
            addCriterion("FUND_NO not in", values, "fundNo");
            return (Criteria) this;
        }

        public Criteria andFundNoBetween(String value1, String value2) {
            addCriterion("FUND_NO between", value1, value2, "fundNo");
            return (Criteria) this;
        }

        public Criteria andFundNoNotBetween(String value1, String value2) {
            addCriterion("FUND_NO not between", value1, value2, "fundNo");
            return (Criteria) this;
        }

        public Criteria andFundAmountIsNull() {
            addCriterion("FUND_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andFundAmountIsNotNull() {
            addCriterion("FUND_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andFundAmountEqualTo(BigDecimal value) {
            addCriterion("FUND_AMOUNT =", value, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andFundAmountNotEqualTo(BigDecimal value) {
            addCriterion("FUND_AMOUNT <>", value, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andFundAmountGreaterThan(BigDecimal value) {
            addCriterion("FUND_AMOUNT >", value, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andFundAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FUND_AMOUNT >=", value, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andFundAmountLessThan(BigDecimal value) {
            addCriterion("FUND_AMOUNT <", value, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andFundAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FUND_AMOUNT <=", value, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andFundAmountIn(List<BigDecimal> values) {
            addCriterion("FUND_AMOUNT in", values, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andFundAmountNotIn(List<BigDecimal> values) {
            addCriterion("FUND_AMOUNT not in", values, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andFundAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FUND_AMOUNT between", value1, value2, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andFundAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FUND_AMOUNT not between", value1, value2, "fundAmount");
            return (Criteria) this;
        }

        public Criteria andFundDateIsNull() {
            addCriterion("FUND_DATE is null");
            return (Criteria) this;
        }

        public Criteria andFundDateIsNotNull() {
            addCriterion("FUND_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andFundDateEqualTo(String value) {
            addCriterion("FUND_DATE =", value, "fundDate");
            return (Criteria) this;
        }

        public Criteria andFundDateNotEqualTo(String value) {
            addCriterion("FUND_DATE <>", value, "fundDate");
            return (Criteria) this;
        }

        public Criteria andFundDateGreaterThan(String value) {
            addCriterion("FUND_DATE >", value, "fundDate");
            return (Criteria) this;
        }

        public Criteria andFundDateGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_DATE >=", value, "fundDate");
            return (Criteria) this;
        }

        public Criteria andFundDateLessThan(String value) {
            addCriterion("FUND_DATE <", value, "fundDate");
            return (Criteria) this;
        }

        public Criteria andFundDateLessThanOrEqualTo(String value) {
            addCriterion("FUND_DATE <=", value, "fundDate");
            return (Criteria) this;
        }

        public Criteria andFundDateLike(String value) {
            addCriterion("FUND_DATE like", value, "fundDate");
            return (Criteria) this;
        }

        public Criteria andFundDateNotLike(String value) {
            addCriterion("FUND_DATE not like", value, "fundDate");
            return (Criteria) this;
        }

        public Criteria andFundDateIn(List<String> values) {
            addCriterion("FUND_DATE in", values, "fundDate");
            return (Criteria) this;
        }

        public Criteria andFundDateNotIn(List<String> values) {
            addCriterion("FUND_DATE not in", values, "fundDate");
            return (Criteria) this;
        }

        public Criteria andFundDateBetween(String value1, String value2) {
            addCriterion("FUND_DATE between", value1, value2, "fundDate");
            return (Criteria) this;
        }

        public Criteria andFundDateNotBetween(String value1, String value2) {
            addCriterion("FUND_DATE not between", value1, value2, "fundDate");
            return (Criteria) this;
        }

        public Criteria andFundUsesIdIsNull() {
            addCriterion("FUND_USES_ID is null");
            return (Criteria) this;
        }

        public Criteria andFundUsesIdIsNotNull() {
            addCriterion("FUND_USES_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFundUsesIdEqualTo(String value) {
            addCriterion("FUND_USES_ID =", value, "fundUsesId");
            return (Criteria) this;
        }

        public Criteria andFundUsesIdNotEqualTo(String value) {
            addCriterion("FUND_USES_ID <>", value, "fundUsesId");
            return (Criteria) this;
        }

        public Criteria andFundUsesIdGreaterThan(String value) {
            addCriterion("FUND_USES_ID >", value, "fundUsesId");
            return (Criteria) this;
        }

        public Criteria andFundUsesIdGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_USES_ID >=", value, "fundUsesId");
            return (Criteria) this;
        }

        public Criteria andFundUsesIdLessThan(String value) {
            addCriterion("FUND_USES_ID <", value, "fundUsesId");
            return (Criteria) this;
        }

        public Criteria andFundUsesIdLessThanOrEqualTo(String value) {
            addCriterion("FUND_USES_ID <=", value, "fundUsesId");
            return (Criteria) this;
        }

        public Criteria andFundUsesIdLike(String value) {
            addCriterion("FUND_USES_ID like", value, "fundUsesId");
            return (Criteria) this;
        }

        public Criteria andFundUsesIdNotLike(String value) {
            addCriterion("FUND_USES_ID not like", value, "fundUsesId");
            return (Criteria) this;
        }

        public Criteria andFundUsesIdIn(List<String> values) {
            addCriterion("FUND_USES_ID in", values, "fundUsesId");
            return (Criteria) this;
        }

        public Criteria andFundUsesIdNotIn(List<String> values) {
            addCriterion("FUND_USES_ID not in", values, "fundUsesId");
            return (Criteria) this;
        }

        public Criteria andFundUsesIdBetween(String value1, String value2) {
            addCriterion("FUND_USES_ID between", value1, value2, "fundUsesId");
            return (Criteria) this;
        }

        public Criteria andFundUsesIdNotBetween(String value1, String value2) {
            addCriterion("FUND_USES_ID not between", value1, value2, "fundUsesId");
            return (Criteria) this;
        }

        public Criteria andFundUsesNameIsNull() {
            addCriterion("FUND_USES_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFundUsesNameIsNotNull() {
            addCriterion("FUND_USES_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFundUsesNameEqualTo(String value) {
            addCriterion("FUND_USES_NAME =", value, "fundUsesName");
            return (Criteria) this;
        }

        public Criteria andFundUsesNameNotEqualTo(String value) {
            addCriterion("FUND_USES_NAME <>", value, "fundUsesName");
            return (Criteria) this;
        }

        public Criteria andFundUsesNameGreaterThan(String value) {
            addCriterion("FUND_USES_NAME >", value, "fundUsesName");
            return (Criteria) this;
        }

        public Criteria andFundUsesNameGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_USES_NAME >=", value, "fundUsesName");
            return (Criteria) this;
        }

        public Criteria andFundUsesNameLessThan(String value) {
            addCriterion("FUND_USES_NAME <", value, "fundUsesName");
            return (Criteria) this;
        }

        public Criteria andFundUsesNameLessThanOrEqualTo(String value) {
            addCriterion("FUND_USES_NAME <=", value, "fundUsesName");
            return (Criteria) this;
        }

        public Criteria andFundUsesNameLike(String value) {
            addCriterion("FUND_USES_NAME like", value, "fundUsesName");
            return (Criteria) this;
        }

        public Criteria andFundUsesNameNotLike(String value) {
            addCriterion("FUND_USES_NAME not like", value, "fundUsesName");
            return (Criteria) this;
        }

        public Criteria andFundUsesNameIn(List<String> values) {
            addCriterion("FUND_USES_NAME in", values, "fundUsesName");
            return (Criteria) this;
        }

        public Criteria andFundUsesNameNotIn(List<String> values) {
            addCriterion("FUND_USES_NAME not in", values, "fundUsesName");
            return (Criteria) this;
        }

        public Criteria andFundUsesNameBetween(String value1, String value2) {
            addCriterion("FUND_USES_NAME between", value1, value2, "fundUsesName");
            return (Criteria) this;
        }

        public Criteria andFundUsesNameNotBetween(String value1, String value2) {
            addCriterion("FUND_USES_NAME not between", value1, value2, "fundUsesName");
            return (Criteria) this;
        }

        public Criteria andFundUsesOtherIsNull() {
            addCriterion("FUND_USES_OTHER is null");
            return (Criteria) this;
        }

        public Criteria andFundUsesOtherIsNotNull() {
            addCriterion("FUND_USES_OTHER is not null");
            return (Criteria) this;
        }

        public Criteria andFundUsesOtherEqualTo(String value) {
            addCriterion("FUND_USES_OTHER =", value, "fundUsesOther");
            return (Criteria) this;
        }

        public Criteria andFundUsesOtherNotEqualTo(String value) {
            addCriterion("FUND_USES_OTHER <>", value, "fundUsesOther");
            return (Criteria) this;
        }

        public Criteria andFundUsesOtherGreaterThan(String value) {
            addCriterion("FUND_USES_OTHER >", value, "fundUsesOther");
            return (Criteria) this;
        }

        public Criteria andFundUsesOtherGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_USES_OTHER >=", value, "fundUsesOther");
            return (Criteria) this;
        }

        public Criteria andFundUsesOtherLessThan(String value) {
            addCriterion("FUND_USES_OTHER <", value, "fundUsesOther");
            return (Criteria) this;
        }

        public Criteria andFundUsesOtherLessThanOrEqualTo(String value) {
            addCriterion("FUND_USES_OTHER <=", value, "fundUsesOther");
            return (Criteria) this;
        }

        public Criteria andFundUsesOtherLike(String value) {
            addCriterion("FUND_USES_OTHER like", value, "fundUsesOther");
            return (Criteria) this;
        }

        public Criteria andFundUsesOtherNotLike(String value) {
            addCriterion("FUND_USES_OTHER not like", value, "fundUsesOther");
            return (Criteria) this;
        }

        public Criteria andFundUsesOtherIn(List<String> values) {
            addCriterion("FUND_USES_OTHER in", values, "fundUsesOther");
            return (Criteria) this;
        }

        public Criteria andFundUsesOtherNotIn(List<String> values) {
            addCriterion("FUND_USES_OTHER not in", values, "fundUsesOther");
            return (Criteria) this;
        }

        public Criteria andFundUsesOtherBetween(String value1, String value2) {
            addCriterion("FUND_USES_OTHER between", value1, value2, "fundUsesOther");
            return (Criteria) this;
        }

        public Criteria andFundUsesOtherNotBetween(String value1, String value2) {
            addCriterion("FUND_USES_OTHER not between", value1, value2, "fundUsesOther");
            return (Criteria) this;
        }

        public Criteria andFundNoteIsNull() {
            addCriterion("FUND_NOTE is null");
            return (Criteria) this;
        }

        public Criteria andFundNoteIsNotNull() {
            addCriterion("FUND_NOTE is not null");
            return (Criteria) this;
        }

        public Criteria andFundNoteEqualTo(String value) {
            addCriterion("FUND_NOTE =", value, "fundNote");
            return (Criteria) this;
        }

        public Criteria andFundNoteNotEqualTo(String value) {
            addCriterion("FUND_NOTE <>", value, "fundNote");
            return (Criteria) this;
        }

        public Criteria andFundNoteGreaterThan(String value) {
            addCriterion("FUND_NOTE >", value, "fundNote");
            return (Criteria) this;
        }

        public Criteria andFundNoteGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_NOTE >=", value, "fundNote");
            return (Criteria) this;
        }

        public Criteria andFundNoteLessThan(String value) {
            addCriterion("FUND_NOTE <", value, "fundNote");
            return (Criteria) this;
        }

        public Criteria andFundNoteLessThanOrEqualTo(String value) {
            addCriterion("FUND_NOTE <=", value, "fundNote");
            return (Criteria) this;
        }

        public Criteria andFundNoteLike(String value) {
            addCriterion("FUND_NOTE like", value, "fundNote");
            return (Criteria) this;
        }

        public Criteria andFundNoteNotLike(String value) {
            addCriterion("FUND_NOTE not like", value, "fundNote");
            return (Criteria) this;
        }

        public Criteria andFundNoteIn(List<String> values) {
            addCriterion("FUND_NOTE in", values, "fundNote");
            return (Criteria) this;
        }

        public Criteria andFundNoteNotIn(List<String> values) {
            addCriterion("FUND_NOTE not in", values, "fundNote");
            return (Criteria) this;
        }

        public Criteria andFundNoteBetween(String value1, String value2) {
            addCriterion("FUND_NOTE between", value1, value2, "fundNote");
            return (Criteria) this;
        }

        public Criteria andFundNoteNotBetween(String value1, String value2) {
            addCriterion("FUND_NOTE not between", value1, value2, "fundNote");
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

        public Criteria andFundStatusIdIsNull() {
            addCriterion("FUND_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andFundStatusIdIsNotNull() {
            addCriterion("FUND_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFundStatusIdEqualTo(String value) {
            addCriterion("FUND_STATUS_ID =", value, "fundStatusId");
            return (Criteria) this;
        }

        public Criteria andFundStatusIdNotEqualTo(String value) {
            addCriterion("FUND_STATUS_ID <>", value, "fundStatusId");
            return (Criteria) this;
        }

        public Criteria andFundStatusIdGreaterThan(String value) {
            addCriterion("FUND_STATUS_ID >", value, "fundStatusId");
            return (Criteria) this;
        }

        public Criteria andFundStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_STATUS_ID >=", value, "fundStatusId");
            return (Criteria) this;
        }

        public Criteria andFundStatusIdLessThan(String value) {
            addCriterion("FUND_STATUS_ID <", value, "fundStatusId");
            return (Criteria) this;
        }

        public Criteria andFundStatusIdLessThanOrEqualTo(String value) {
            addCriterion("FUND_STATUS_ID <=", value, "fundStatusId");
            return (Criteria) this;
        }

        public Criteria andFundStatusIdLike(String value) {
            addCriterion("FUND_STATUS_ID like", value, "fundStatusId");
            return (Criteria) this;
        }

        public Criteria andFundStatusIdNotLike(String value) {
            addCriterion("FUND_STATUS_ID not like", value, "fundStatusId");
            return (Criteria) this;
        }

        public Criteria andFundStatusIdIn(List<String> values) {
            addCriterion("FUND_STATUS_ID in", values, "fundStatusId");
            return (Criteria) this;
        }

        public Criteria andFundStatusIdNotIn(List<String> values) {
            addCriterion("FUND_STATUS_ID not in", values, "fundStatusId");
            return (Criteria) this;
        }

        public Criteria andFundStatusIdBetween(String value1, String value2) {
            addCriterion("FUND_STATUS_ID between", value1, value2, "fundStatusId");
            return (Criteria) this;
        }

        public Criteria andFundStatusIdNotBetween(String value1, String value2) {
            addCriterion("FUND_STATUS_ID not between", value1, value2, "fundStatusId");
            return (Criteria) this;
        }

        public Criteria andFundStatusNameIsNull() {
            addCriterion("FUND_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFundStatusNameIsNotNull() {
            addCriterion("FUND_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFundStatusNameEqualTo(String value) {
            addCriterion("FUND_STATUS_NAME =", value, "fundStatusName");
            return (Criteria) this;
        }

        public Criteria andFundStatusNameNotEqualTo(String value) {
            addCriterion("FUND_STATUS_NAME <>", value, "fundStatusName");
            return (Criteria) this;
        }

        public Criteria andFundStatusNameGreaterThan(String value) {
            addCriterion("FUND_STATUS_NAME >", value, "fundStatusName");
            return (Criteria) this;
        }

        public Criteria andFundStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_STATUS_NAME >=", value, "fundStatusName");
            return (Criteria) this;
        }

        public Criteria andFundStatusNameLessThan(String value) {
            addCriterion("FUND_STATUS_NAME <", value, "fundStatusName");
            return (Criteria) this;
        }

        public Criteria andFundStatusNameLessThanOrEqualTo(String value) {
            addCriterion("FUND_STATUS_NAME <=", value, "fundStatusName");
            return (Criteria) this;
        }

        public Criteria andFundStatusNameLike(String value) {
            addCriterion("FUND_STATUS_NAME like", value, "fundStatusName");
            return (Criteria) this;
        }

        public Criteria andFundStatusNameNotLike(String value) {
            addCriterion("FUND_STATUS_NAME not like", value, "fundStatusName");
            return (Criteria) this;
        }

        public Criteria andFundStatusNameIn(List<String> values) {
            addCriterion("FUND_STATUS_NAME in", values, "fundStatusName");
            return (Criteria) this;
        }

        public Criteria andFundStatusNameNotIn(List<String> values) {
            addCriterion("FUND_STATUS_NAME not in", values, "fundStatusName");
            return (Criteria) this;
        }

        public Criteria andFundStatusNameBetween(String value1, String value2) {
            addCriterion("FUND_STATUS_NAME between", value1, value2, "fundStatusName");
            return (Criteria) this;
        }

        public Criteria andFundStatusNameNotBetween(String value1, String value2) {
            addCriterion("FUND_STATUS_NAME not between", value1, value2, "fundStatusName");
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