package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ErpCrmContractBillBalanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ErpCrmContractBillBalanceExample() {
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

        public Criteria andContractFlowIsNull() {
            addCriterion("CONTRACT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andContractFlowIsNotNull() {
            addCriterion("CONTRACT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andContractFlowEqualTo(String value) {
            addCriterion("CONTRACT_FLOW =", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowNotEqualTo(String value) {
            addCriterion("CONTRACT_FLOW <>", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowGreaterThan(String value) {
            addCriterion("CONTRACT_FLOW >", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_FLOW >=", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowLessThan(String value) {
            addCriterion("CONTRACT_FLOW <", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_FLOW <=", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowLike(String value) {
            addCriterion("CONTRACT_FLOW like", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowNotLike(String value) {
            addCriterion("CONTRACT_FLOW not like", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowIn(List<String> values) {
            addCriterion("CONTRACT_FLOW in", values, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowNotIn(List<String> values) {
            addCriterion("CONTRACT_FLOW not in", values, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowBetween(String value1, String value2) {
            addCriterion("CONTRACT_FLOW between", value1, value2, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_FLOW not between", value1, value2, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andBillPlanFlowIsNull() {
            addCriterion("BILL_PLAN_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andBillPlanFlowIsNotNull() {
            addCriterion("BILL_PLAN_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andBillPlanFlowEqualTo(String value) {
            addCriterion("BILL_PLAN_FLOW =", value, "billPlanFlow");
            return (Criteria) this;
        }

        public Criteria andBillPlanFlowNotEqualTo(String value) {
            addCriterion("BILL_PLAN_FLOW <>", value, "billPlanFlow");
            return (Criteria) this;
        }

        public Criteria andBillPlanFlowGreaterThan(String value) {
            addCriterion("BILL_PLAN_FLOW >", value, "billPlanFlow");
            return (Criteria) this;
        }

        public Criteria andBillPlanFlowGreaterThanOrEqualTo(String value) {
            addCriterion("BILL_PLAN_FLOW >=", value, "billPlanFlow");
            return (Criteria) this;
        }

        public Criteria andBillPlanFlowLessThan(String value) {
            addCriterion("BILL_PLAN_FLOW <", value, "billPlanFlow");
            return (Criteria) this;
        }

        public Criteria andBillPlanFlowLessThanOrEqualTo(String value) {
            addCriterion("BILL_PLAN_FLOW <=", value, "billPlanFlow");
            return (Criteria) this;
        }

        public Criteria andBillPlanFlowLike(String value) {
            addCriterion("BILL_PLAN_FLOW like", value, "billPlanFlow");
            return (Criteria) this;
        }

        public Criteria andBillPlanFlowNotLike(String value) {
            addCriterion("BILL_PLAN_FLOW not like", value, "billPlanFlow");
            return (Criteria) this;
        }

        public Criteria andBillPlanFlowIn(List<String> values) {
            addCriterion("BILL_PLAN_FLOW in", values, "billPlanFlow");
            return (Criteria) this;
        }

        public Criteria andBillPlanFlowNotIn(List<String> values) {
            addCriterion("BILL_PLAN_FLOW not in", values, "billPlanFlow");
            return (Criteria) this;
        }

        public Criteria andBillPlanFlowBetween(String value1, String value2) {
            addCriterion("BILL_PLAN_FLOW between", value1, value2, "billPlanFlow");
            return (Criteria) this;
        }

        public Criteria andBillPlanFlowNotBetween(String value1, String value2) {
            addCriterion("BILL_PLAN_FLOW not between", value1, value2, "billPlanFlow");
            return (Criteria) this;
        }

        public Criteria andBillDateIsNull() {
            addCriterion("BILL_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBillDateIsNotNull() {
            addCriterion("BILL_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBillDateEqualTo(String value) {
            addCriterion("BILL_DATE =", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateNotEqualTo(String value) {
            addCriterion("BILL_DATE <>", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateGreaterThan(String value) {
            addCriterion("BILL_DATE >", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateGreaterThanOrEqualTo(String value) {
            addCriterion("BILL_DATE >=", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateLessThan(String value) {
            addCriterion("BILL_DATE <", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateLessThanOrEqualTo(String value) {
            addCriterion("BILL_DATE <=", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateLike(String value) {
            addCriterion("BILL_DATE like", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateNotLike(String value) {
            addCriterion("BILL_DATE not like", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateIn(List<String> values) {
            addCriterion("BILL_DATE in", values, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateNotIn(List<String> values) {
            addCriterion("BILL_DATE not in", values, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateBetween(String value1, String value2) {
            addCriterion("BILL_DATE between", value1, value2, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateNotBetween(String value1, String value2) {
            addCriterion("BILL_DATE not between", value1, value2, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillFundIsNull() {
            addCriterion("BILL_FUND is null");
            return (Criteria) this;
        }

        public Criteria andBillFundIsNotNull() {
            addCriterion("BILL_FUND is not null");
            return (Criteria) this;
        }

        public Criteria andBillFundEqualTo(BigDecimal value) {
            addCriterion("BILL_FUND =", value, "billFund");
            return (Criteria) this;
        }

        public Criteria andBillFundNotEqualTo(BigDecimal value) {
            addCriterion("BILL_FUND <>", value, "billFund");
            return (Criteria) this;
        }

        public Criteria andBillFundGreaterThan(BigDecimal value) {
            addCriterion("BILL_FUND >", value, "billFund");
            return (Criteria) this;
        }

        public Criteria andBillFundGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BILL_FUND >=", value, "billFund");
            return (Criteria) this;
        }

        public Criteria andBillFundLessThan(BigDecimal value) {
            addCriterion("BILL_FUND <", value, "billFund");
            return (Criteria) this;
        }

        public Criteria andBillFundLessThanOrEqualTo(BigDecimal value) {
            addCriterion("BILL_FUND <=", value, "billFund");
            return (Criteria) this;
        }

        public Criteria andBillFundIn(List<BigDecimal> values) {
            addCriterion("BILL_FUND in", values, "billFund");
            return (Criteria) this;
        }

        public Criteria andBillFundNotIn(List<BigDecimal> values) {
            addCriterion("BILL_FUND not in", values, "billFund");
            return (Criteria) this;
        }

        public Criteria andBillFundBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BILL_FUND between", value1, value2, "billFund");
            return (Criteria) this;
        }

        public Criteria andBillFundNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BILL_FUND not between", value1, value2, "billFund");
            return (Criteria) this;
        }

        public Criteria andBillNoIsNull() {
            addCriterion("BILL_NO is null");
            return (Criteria) this;
        }

        public Criteria andBillNoIsNotNull() {
            addCriterion("BILL_NO is not null");
            return (Criteria) this;
        }

        public Criteria andBillNoEqualTo(String value) {
            addCriterion("BILL_NO =", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoNotEqualTo(String value) {
            addCriterion("BILL_NO <>", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoGreaterThan(String value) {
            addCriterion("BILL_NO >", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoGreaterThanOrEqualTo(String value) {
            addCriterion("BILL_NO >=", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoLessThan(String value) {
            addCriterion("BILL_NO <", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoLessThanOrEqualTo(String value) {
            addCriterion("BILL_NO <=", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoLike(String value) {
            addCriterion("BILL_NO like", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoNotLike(String value) {
            addCriterion("BILL_NO not like", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoIn(List<String> values) {
            addCriterion("BILL_NO in", values, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoNotIn(List<String> values) {
            addCriterion("BILL_NO not in", values, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoBetween(String value1, String value2) {
            addCriterion("BILL_NO between", value1, value2, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoNotBetween(String value1, String value2) {
            addCriterion("BILL_NO not between", value1, value2, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillTrackeNumberIsNull() {
            addCriterion("BILL_TRACKE_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andBillTrackeNumberIsNotNull() {
            addCriterion("BILL_TRACKE_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andBillTrackeNumberEqualTo(String value) {
            addCriterion("BILL_TRACKE_NUMBER =", value, "billTrackeNumber");
            return (Criteria) this;
        }

        public Criteria andBillTrackeNumberNotEqualTo(String value) {
            addCriterion("BILL_TRACKE_NUMBER <>", value, "billTrackeNumber");
            return (Criteria) this;
        }

        public Criteria andBillTrackeNumberGreaterThan(String value) {
            addCriterion("BILL_TRACKE_NUMBER >", value, "billTrackeNumber");
            return (Criteria) this;
        }

        public Criteria andBillTrackeNumberGreaterThanOrEqualTo(String value) {
            addCriterion("BILL_TRACKE_NUMBER >=", value, "billTrackeNumber");
            return (Criteria) this;
        }

        public Criteria andBillTrackeNumberLessThan(String value) {
            addCriterion("BILL_TRACKE_NUMBER <", value, "billTrackeNumber");
            return (Criteria) this;
        }

        public Criteria andBillTrackeNumberLessThanOrEqualTo(String value) {
            addCriterion("BILL_TRACKE_NUMBER <=", value, "billTrackeNumber");
            return (Criteria) this;
        }

        public Criteria andBillTrackeNumberLike(String value) {
            addCriterion("BILL_TRACKE_NUMBER like", value, "billTrackeNumber");
            return (Criteria) this;
        }

        public Criteria andBillTrackeNumberNotLike(String value) {
            addCriterion("BILL_TRACKE_NUMBER not like", value, "billTrackeNumber");
            return (Criteria) this;
        }

        public Criteria andBillTrackeNumberIn(List<String> values) {
            addCriterion("BILL_TRACKE_NUMBER in", values, "billTrackeNumber");
            return (Criteria) this;
        }

        public Criteria andBillTrackeNumberNotIn(List<String> values) {
            addCriterion("BILL_TRACKE_NUMBER not in", values, "billTrackeNumber");
            return (Criteria) this;
        }

        public Criteria andBillTrackeNumberBetween(String value1, String value2) {
            addCriterion("BILL_TRACKE_NUMBER between", value1, value2, "billTrackeNumber");
            return (Criteria) this;
        }

        public Criteria andBillTrackeNumberNotBetween(String value1, String value2) {
            addCriterion("BILL_TRACKE_NUMBER not between", value1, value2, "billTrackeNumber");
            return (Criteria) this;
        }

        public Criteria andBillTrackeContentIsNull() {
            addCriterion("BILL_TRACKE_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andBillTrackeContentIsNotNull() {
            addCriterion("BILL_TRACKE_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andBillTrackeContentEqualTo(String value) {
            addCriterion("BILL_TRACKE_CONTENT =", value, "billTrackeContent");
            return (Criteria) this;
        }

        public Criteria andBillTrackeContentNotEqualTo(String value) {
            addCriterion("BILL_TRACKE_CONTENT <>", value, "billTrackeContent");
            return (Criteria) this;
        }

        public Criteria andBillTrackeContentGreaterThan(String value) {
            addCriterion("BILL_TRACKE_CONTENT >", value, "billTrackeContent");
            return (Criteria) this;
        }

        public Criteria andBillTrackeContentGreaterThanOrEqualTo(String value) {
            addCriterion("BILL_TRACKE_CONTENT >=", value, "billTrackeContent");
            return (Criteria) this;
        }

        public Criteria andBillTrackeContentLessThan(String value) {
            addCriterion("BILL_TRACKE_CONTENT <", value, "billTrackeContent");
            return (Criteria) this;
        }

        public Criteria andBillTrackeContentLessThanOrEqualTo(String value) {
            addCriterion("BILL_TRACKE_CONTENT <=", value, "billTrackeContent");
            return (Criteria) this;
        }

        public Criteria andBillTrackeContentLike(String value) {
            addCriterion("BILL_TRACKE_CONTENT like", value, "billTrackeContent");
            return (Criteria) this;
        }

        public Criteria andBillTrackeContentNotLike(String value) {
            addCriterion("BILL_TRACKE_CONTENT not like", value, "billTrackeContent");
            return (Criteria) this;
        }

        public Criteria andBillTrackeContentIn(List<String> values) {
            addCriterion("BILL_TRACKE_CONTENT in", values, "billTrackeContent");
            return (Criteria) this;
        }

        public Criteria andBillTrackeContentNotIn(List<String> values) {
            addCriterion("BILL_TRACKE_CONTENT not in", values, "billTrackeContent");
            return (Criteria) this;
        }

        public Criteria andBillTrackeContentBetween(String value1, String value2) {
            addCriterion("BILL_TRACKE_CONTENT between", value1, value2, "billTrackeContent");
            return (Criteria) this;
        }

        public Criteria andBillTrackeContentNotBetween(String value1, String value2) {
            addCriterion("BILL_TRACKE_CONTENT not between", value1, value2, "billTrackeContent");
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

        public Criteria andBillApplyUserFlowIsNull() {
            addCriterion("BILL_APPLY_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserFlowIsNotNull() {
            addCriterion("BILL_APPLY_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserFlowEqualTo(String value) {
            addCriterion("BILL_APPLY_USER_FLOW =", value, "billApplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserFlowNotEqualTo(String value) {
            addCriterion("BILL_APPLY_USER_FLOW <>", value, "billApplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserFlowGreaterThan(String value) {
            addCriterion("BILL_APPLY_USER_FLOW >", value, "billApplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("BILL_APPLY_USER_FLOW >=", value, "billApplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserFlowLessThan(String value) {
            addCriterion("BILL_APPLY_USER_FLOW <", value, "billApplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserFlowLessThanOrEqualTo(String value) {
            addCriterion("BILL_APPLY_USER_FLOW <=", value, "billApplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserFlowLike(String value) {
            addCriterion("BILL_APPLY_USER_FLOW like", value, "billApplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserFlowNotLike(String value) {
            addCriterion("BILL_APPLY_USER_FLOW not like", value, "billApplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserFlowIn(List<String> values) {
            addCriterion("BILL_APPLY_USER_FLOW in", values, "billApplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserFlowNotIn(List<String> values) {
            addCriterion("BILL_APPLY_USER_FLOW not in", values, "billApplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserFlowBetween(String value1, String value2) {
            addCriterion("BILL_APPLY_USER_FLOW between", value1, value2, "billApplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserFlowNotBetween(String value1, String value2) {
            addCriterion("BILL_APPLY_USER_FLOW not between", value1, value2, "billApplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserNameIsNull() {
            addCriterion("BILL_APPLY_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserNameIsNotNull() {
            addCriterion("BILL_APPLY_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserNameEqualTo(String value) {
            addCriterion("BILL_APPLY_USER_NAME =", value, "billApplyUserName");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserNameNotEqualTo(String value) {
            addCriterion("BILL_APPLY_USER_NAME <>", value, "billApplyUserName");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserNameGreaterThan(String value) {
            addCriterion("BILL_APPLY_USER_NAME >", value, "billApplyUserName");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("BILL_APPLY_USER_NAME >=", value, "billApplyUserName");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserNameLessThan(String value) {
            addCriterion("BILL_APPLY_USER_NAME <", value, "billApplyUserName");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserNameLessThanOrEqualTo(String value) {
            addCriterion("BILL_APPLY_USER_NAME <=", value, "billApplyUserName");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserNameLike(String value) {
            addCriterion("BILL_APPLY_USER_NAME like", value, "billApplyUserName");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserNameNotLike(String value) {
            addCriterion("BILL_APPLY_USER_NAME not like", value, "billApplyUserName");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserNameIn(List<String> values) {
            addCriterion("BILL_APPLY_USER_NAME in", values, "billApplyUserName");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserNameNotIn(List<String> values) {
            addCriterion("BILL_APPLY_USER_NAME not in", values, "billApplyUserName");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserNameBetween(String value1, String value2) {
            addCriterion("BILL_APPLY_USER_NAME between", value1, value2, "billApplyUserName");
            return (Criteria) this;
        }

        public Criteria andBillApplyUserNameNotBetween(String value1, String value2) {
            addCriterion("BILL_APPLY_USER_NAME not between", value1, value2, "billApplyUserName");
            return (Criteria) this;
        }

        public Criteria andHandleUserFlowIsNull() {
            addCriterion("HANDLE_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andHandleUserFlowIsNotNull() {
            addCriterion("HANDLE_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andHandleUserFlowEqualTo(String value) {
            addCriterion("HANDLE_USER_FLOW =", value, "handleUserFlow");
            return (Criteria) this;
        }

        public Criteria andHandleUserFlowNotEqualTo(String value) {
            addCriterion("HANDLE_USER_FLOW <>", value, "handleUserFlow");
            return (Criteria) this;
        }

        public Criteria andHandleUserFlowGreaterThan(String value) {
            addCriterion("HANDLE_USER_FLOW >", value, "handleUserFlow");
            return (Criteria) this;
        }

        public Criteria andHandleUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("HANDLE_USER_FLOW >=", value, "handleUserFlow");
            return (Criteria) this;
        }

        public Criteria andHandleUserFlowLessThan(String value) {
            addCriterion("HANDLE_USER_FLOW <", value, "handleUserFlow");
            return (Criteria) this;
        }

        public Criteria andHandleUserFlowLessThanOrEqualTo(String value) {
            addCriterion("HANDLE_USER_FLOW <=", value, "handleUserFlow");
            return (Criteria) this;
        }

        public Criteria andHandleUserFlowLike(String value) {
            addCriterion("HANDLE_USER_FLOW like", value, "handleUserFlow");
            return (Criteria) this;
        }

        public Criteria andHandleUserFlowNotLike(String value) {
            addCriterion("HANDLE_USER_FLOW not like", value, "handleUserFlow");
            return (Criteria) this;
        }

        public Criteria andHandleUserFlowIn(List<String> values) {
            addCriterion("HANDLE_USER_FLOW in", values, "handleUserFlow");
            return (Criteria) this;
        }

        public Criteria andHandleUserFlowNotIn(List<String> values) {
            addCriterion("HANDLE_USER_FLOW not in", values, "handleUserFlow");
            return (Criteria) this;
        }

        public Criteria andHandleUserFlowBetween(String value1, String value2) {
            addCriterion("HANDLE_USER_FLOW between", value1, value2, "handleUserFlow");
            return (Criteria) this;
        }

        public Criteria andHandleUserFlowNotBetween(String value1, String value2) {
            addCriterion("HANDLE_USER_FLOW not between", value1, value2, "handleUserFlow");
            return (Criteria) this;
        }

        public Criteria andHandleUserNameIsNull() {
            addCriterion("HANDLE_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHandleUserNameIsNotNull() {
            addCriterion("HANDLE_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHandleUserNameEqualTo(String value) {
            addCriterion("HANDLE_USER_NAME =", value, "handleUserName");
            return (Criteria) this;
        }

        public Criteria andHandleUserNameNotEqualTo(String value) {
            addCriterion("HANDLE_USER_NAME <>", value, "handleUserName");
            return (Criteria) this;
        }

        public Criteria andHandleUserNameGreaterThan(String value) {
            addCriterion("HANDLE_USER_NAME >", value, "handleUserName");
            return (Criteria) this;
        }

        public Criteria andHandleUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("HANDLE_USER_NAME >=", value, "handleUserName");
            return (Criteria) this;
        }

        public Criteria andHandleUserNameLessThan(String value) {
            addCriterion("HANDLE_USER_NAME <", value, "handleUserName");
            return (Criteria) this;
        }

        public Criteria andHandleUserNameLessThanOrEqualTo(String value) {
            addCriterion("HANDLE_USER_NAME <=", value, "handleUserName");
            return (Criteria) this;
        }

        public Criteria andHandleUserNameLike(String value) {
            addCriterion("HANDLE_USER_NAME like", value, "handleUserName");
            return (Criteria) this;
        }

        public Criteria andHandleUserNameNotLike(String value) {
            addCriterion("HANDLE_USER_NAME not like", value, "handleUserName");
            return (Criteria) this;
        }

        public Criteria andHandleUserNameIn(List<String> values) {
            addCriterion("HANDLE_USER_NAME in", values, "handleUserName");
            return (Criteria) this;
        }

        public Criteria andHandleUserNameNotIn(List<String> values) {
            addCriterion("HANDLE_USER_NAME not in", values, "handleUserName");
            return (Criteria) this;
        }

        public Criteria andHandleUserNameBetween(String value1, String value2) {
            addCriterion("HANDLE_USER_NAME between", value1, value2, "handleUserName");
            return (Criteria) this;
        }

        public Criteria andHandleUserNameNotBetween(String value1, String value2) {
            addCriterion("HANDLE_USER_NAME not between", value1, value2, "handleUserName");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIsNull() {
            addCriterion("HANDLE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIsNotNull() {
            addCriterion("HANDLE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andHandleTimeEqualTo(String value) {
            addCriterion("HANDLE_TIME =", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotEqualTo(String value) {
            addCriterion("HANDLE_TIME <>", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeGreaterThan(String value) {
            addCriterion("HANDLE_TIME >", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeGreaterThanOrEqualTo(String value) {
            addCriterion("HANDLE_TIME >=", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeLessThan(String value) {
            addCriterion("HANDLE_TIME <", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeLessThanOrEqualTo(String value) {
            addCriterion("HANDLE_TIME <=", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeLike(String value) {
            addCriterion("HANDLE_TIME like", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotLike(String value) {
            addCriterion("HANDLE_TIME not like", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIn(List<String> values) {
            addCriterion("HANDLE_TIME in", values, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotIn(List<String> values) {
            addCriterion("HANDLE_TIME not in", values, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeBetween(String value1, String value2) {
            addCriterion("HANDLE_TIME between", value1, value2, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotBetween(String value1, String value2) {
            addCriterion("HANDLE_TIME not between", value1, value2, "handleTime");
            return (Criteria) this;
        }

        public Criteria andTrackeUserFlowIsNull() {
            addCriterion("TRACKE_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTrackeUserFlowIsNotNull() {
            addCriterion("TRACKE_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTrackeUserFlowEqualTo(String value) {
            addCriterion("TRACKE_USER_FLOW =", value, "trackeUserFlow");
            return (Criteria) this;
        }

        public Criteria andTrackeUserFlowNotEqualTo(String value) {
            addCriterion("TRACKE_USER_FLOW <>", value, "trackeUserFlow");
            return (Criteria) this;
        }

        public Criteria andTrackeUserFlowGreaterThan(String value) {
            addCriterion("TRACKE_USER_FLOW >", value, "trackeUserFlow");
            return (Criteria) this;
        }

        public Criteria andTrackeUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TRACKE_USER_FLOW >=", value, "trackeUserFlow");
            return (Criteria) this;
        }

        public Criteria andTrackeUserFlowLessThan(String value) {
            addCriterion("TRACKE_USER_FLOW <", value, "trackeUserFlow");
            return (Criteria) this;
        }

        public Criteria andTrackeUserFlowLessThanOrEqualTo(String value) {
            addCriterion("TRACKE_USER_FLOW <=", value, "trackeUserFlow");
            return (Criteria) this;
        }

        public Criteria andTrackeUserFlowLike(String value) {
            addCriterion("TRACKE_USER_FLOW like", value, "trackeUserFlow");
            return (Criteria) this;
        }

        public Criteria andTrackeUserFlowNotLike(String value) {
            addCriterion("TRACKE_USER_FLOW not like", value, "trackeUserFlow");
            return (Criteria) this;
        }

        public Criteria andTrackeUserFlowIn(List<String> values) {
            addCriterion("TRACKE_USER_FLOW in", values, "trackeUserFlow");
            return (Criteria) this;
        }

        public Criteria andTrackeUserFlowNotIn(List<String> values) {
            addCriterion("TRACKE_USER_FLOW not in", values, "trackeUserFlow");
            return (Criteria) this;
        }

        public Criteria andTrackeUserFlowBetween(String value1, String value2) {
            addCriterion("TRACKE_USER_FLOW between", value1, value2, "trackeUserFlow");
            return (Criteria) this;
        }

        public Criteria andTrackeUserFlowNotBetween(String value1, String value2) {
            addCriterion("TRACKE_USER_FLOW not between", value1, value2, "trackeUserFlow");
            return (Criteria) this;
        }

        public Criteria andTrackeUserNameIsNull() {
            addCriterion("TRACKE_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTrackeUserNameIsNotNull() {
            addCriterion("TRACKE_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTrackeUserNameEqualTo(String value) {
            addCriterion("TRACKE_USER_NAME =", value, "trackeUserName");
            return (Criteria) this;
        }

        public Criteria andTrackeUserNameNotEqualTo(String value) {
            addCriterion("TRACKE_USER_NAME <>", value, "trackeUserName");
            return (Criteria) this;
        }

        public Criteria andTrackeUserNameGreaterThan(String value) {
            addCriterion("TRACKE_USER_NAME >", value, "trackeUserName");
            return (Criteria) this;
        }

        public Criteria andTrackeUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRACKE_USER_NAME >=", value, "trackeUserName");
            return (Criteria) this;
        }

        public Criteria andTrackeUserNameLessThan(String value) {
            addCriterion("TRACKE_USER_NAME <", value, "trackeUserName");
            return (Criteria) this;
        }

        public Criteria andTrackeUserNameLessThanOrEqualTo(String value) {
            addCriterion("TRACKE_USER_NAME <=", value, "trackeUserName");
            return (Criteria) this;
        }

        public Criteria andTrackeUserNameLike(String value) {
            addCriterion("TRACKE_USER_NAME like", value, "trackeUserName");
            return (Criteria) this;
        }

        public Criteria andTrackeUserNameNotLike(String value) {
            addCriterion("TRACKE_USER_NAME not like", value, "trackeUserName");
            return (Criteria) this;
        }

        public Criteria andTrackeUserNameIn(List<String> values) {
            addCriterion("TRACKE_USER_NAME in", values, "trackeUserName");
            return (Criteria) this;
        }

        public Criteria andTrackeUserNameNotIn(List<String> values) {
            addCriterion("TRACKE_USER_NAME not in", values, "trackeUserName");
            return (Criteria) this;
        }

        public Criteria andTrackeUserNameBetween(String value1, String value2) {
            addCriterion("TRACKE_USER_NAME between", value1, value2, "trackeUserName");
            return (Criteria) this;
        }

        public Criteria andTrackeUserNameNotBetween(String value1, String value2) {
            addCriterion("TRACKE_USER_NAME not between", value1, value2, "trackeUserName");
            return (Criteria) this;
        }

        public Criteria andTrackeTimeIsNull() {
            addCriterion("TRACKE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTrackeTimeIsNotNull() {
            addCriterion("TRACKE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTrackeTimeEqualTo(String value) {
            addCriterion("TRACKE_TIME =", value, "trackeTime");
            return (Criteria) this;
        }

        public Criteria andTrackeTimeNotEqualTo(String value) {
            addCriterion("TRACKE_TIME <>", value, "trackeTime");
            return (Criteria) this;
        }

        public Criteria andTrackeTimeGreaterThan(String value) {
            addCriterion("TRACKE_TIME >", value, "trackeTime");
            return (Criteria) this;
        }

        public Criteria andTrackeTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TRACKE_TIME >=", value, "trackeTime");
            return (Criteria) this;
        }

        public Criteria andTrackeTimeLessThan(String value) {
            addCriterion("TRACKE_TIME <", value, "trackeTime");
            return (Criteria) this;
        }

        public Criteria andTrackeTimeLessThanOrEqualTo(String value) {
            addCriterion("TRACKE_TIME <=", value, "trackeTime");
            return (Criteria) this;
        }

        public Criteria andTrackeTimeLike(String value) {
            addCriterion("TRACKE_TIME like", value, "trackeTime");
            return (Criteria) this;
        }

        public Criteria andTrackeTimeNotLike(String value) {
            addCriterion("TRACKE_TIME not like", value, "trackeTime");
            return (Criteria) this;
        }

        public Criteria andTrackeTimeIn(List<String> values) {
            addCriterion("TRACKE_TIME in", values, "trackeTime");
            return (Criteria) this;
        }

        public Criteria andTrackeTimeNotIn(List<String> values) {
            addCriterion("TRACKE_TIME not in", values, "trackeTime");
            return (Criteria) this;
        }

        public Criteria andTrackeTimeBetween(String value1, String value2) {
            addCriterion("TRACKE_TIME between", value1, value2, "trackeTime");
            return (Criteria) this;
        }

        public Criteria andTrackeTimeNotBetween(String value1, String value2) {
            addCriterion("TRACKE_TIME not between", value1, value2, "trackeTime");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowIsNull() {
            addCriterion("AUDIT_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowIsNotNull() {
            addCriterion("AUDIT_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW =", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW <>", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowGreaterThan(String value) {
            addCriterion("AUDIT_USER_FLOW >", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW >=", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowLessThan(String value) {
            addCriterion("AUDIT_USER_FLOW <", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW <=", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowLike(String value) {
            addCriterion("AUDIT_USER_FLOW like", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotLike(String value) {
            addCriterion("AUDIT_USER_FLOW not like", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowIn(List<String> values) {
            addCriterion("AUDIT_USER_FLOW in", values, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotIn(List<String> values) {
            addCriterion("AUDIT_USER_FLOW not in", values, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_FLOW between", value1, value2, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_FLOW not between", value1, value2, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIsNull() {
            addCriterion("AUDIT_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIsNotNull() {
            addCriterion("AUDIT_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME =", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME <>", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameGreaterThan(String value) {
            addCriterion("AUDIT_USER_NAME >", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME >=", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLessThan(String value) {
            addCriterion("AUDIT_USER_NAME <", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME <=", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLike(String value) {
            addCriterion("AUDIT_USER_NAME like", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotLike(String value) {
            addCriterion("AUDIT_USER_NAME not like", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIn(List<String> values) {
            addCriterion("AUDIT_USER_NAME in", values, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotIn(List<String> values) {
            addCriterion("AUDIT_USER_NAME not in", values, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_NAME between", value1, value2, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_NAME not between", value1, value2, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditReasonIsNull() {
            addCriterion("AUDIT_REASON is null");
            return (Criteria) this;
        }

        public Criteria andAuditReasonIsNotNull() {
            addCriterion("AUDIT_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andAuditReasonEqualTo(String value) {
            addCriterion("AUDIT_REASON =", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonNotEqualTo(String value) {
            addCriterion("AUDIT_REASON <>", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonGreaterThan(String value) {
            addCriterion("AUDIT_REASON >", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_REASON >=", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonLessThan(String value) {
            addCriterion("AUDIT_REASON <", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_REASON <=", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonLike(String value) {
            addCriterion("AUDIT_REASON like", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonNotLike(String value) {
            addCriterion("AUDIT_REASON not like", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonIn(List<String> values) {
            addCriterion("AUDIT_REASON in", values, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonNotIn(List<String> values) {
            addCriterion("AUDIT_REASON not in", values, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonBetween(String value1, String value2) {
            addCriterion("AUDIT_REASON between", value1, value2, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonNotBetween(String value1, String value2) {
            addCriterion("AUDIT_REASON not between", value1, value2, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNull() {
            addCriterion("AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNotNull() {
            addCriterion("AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeEqualTo(String value) {
            addCriterion("AUDIT_TIME =", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotEqualTo(String value) {
            addCriterion("AUDIT_TIME <>", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThan(String value) {
            addCriterion("AUDIT_TIME >", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_TIME >=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThan(String value) {
            addCriterion("AUDIT_TIME <", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_TIME <=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLike(String value) {
            addCriterion("AUDIT_TIME like", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotLike(String value) {
            addCriterion("AUDIT_TIME not like", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIn(List<String> values) {
            addCriterion("AUDIT_TIME in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotIn(List<String> values) {
            addCriterion("AUDIT_TIME not in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeBetween(String value1, String value2) {
            addCriterion("AUDIT_TIME between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotBetween(String value1, String value2) {
            addCriterion("AUDIT_TIME not between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNull() {
            addCriterion("STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNotNull() {
            addCriterion("STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStatusIdEqualTo(String value) {
            addCriterion("STATUS_ID =", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotEqualTo(String value) {
            addCriterion("STATUS_ID <>", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThan(String value) {
            addCriterion("STATUS_ID >", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_ID >=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThan(String value) {
            addCriterion("STATUS_ID <", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThanOrEqualTo(String value) {
            addCriterion("STATUS_ID <=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLike(String value) {
            addCriterion("STATUS_ID like", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotLike(String value) {
            addCriterion("STATUS_ID not like", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdIn(List<String> values) {
            addCriterion("STATUS_ID in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotIn(List<String> values) {
            addCriterion("STATUS_ID not in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdBetween(String value1, String value2) {
            addCriterion("STATUS_ID between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotBetween(String value1, String value2) {
            addCriterion("STATUS_ID not between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusNameIsNull() {
            addCriterion("STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStatusNameIsNotNull() {
            addCriterion("STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStatusNameEqualTo(String value) {
            addCriterion("STATUS_NAME =", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotEqualTo(String value) {
            addCriterion("STATUS_NAME <>", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameGreaterThan(String value) {
            addCriterion("STATUS_NAME >", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_NAME >=", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLessThan(String value) {
            addCriterion("STATUS_NAME <", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLessThanOrEqualTo(String value) {
            addCriterion("STATUS_NAME <=", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLike(String value) {
            addCriterion("STATUS_NAME like", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotLike(String value) {
            addCriterion("STATUS_NAME not like", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameIn(List<String> values) {
            addCriterion("STATUS_NAME in", values, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotIn(List<String> values) {
            addCriterion("STATUS_NAME not in", values, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameBetween(String value1, String value2) {
            addCriterion("STATUS_NAME between", value1, value2, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotBetween(String value1, String value2) {
            addCriterion("STATUS_NAME not between", value1, value2, "statusName");
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