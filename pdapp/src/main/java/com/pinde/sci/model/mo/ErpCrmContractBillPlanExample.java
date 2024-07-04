package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ErpCrmContractBillPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ErpCrmContractBillPlanExample() {
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

        public Criteria andBillPlanDateIsNull() {
            addCriterion("BILL_PLAN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBillPlanDateIsNotNull() {
            addCriterion("BILL_PLAN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBillPlanDateEqualTo(String value) {
            addCriterion("BILL_PLAN_DATE =", value, "billPlanDate");
            return (Criteria) this;
        }

        public Criteria andBillPlanDateNotEqualTo(String value) {
            addCriterion("BILL_PLAN_DATE <>", value, "billPlanDate");
            return (Criteria) this;
        }

        public Criteria andBillPlanDateGreaterThan(String value) {
            addCriterion("BILL_PLAN_DATE >", value, "billPlanDate");
            return (Criteria) this;
        }

        public Criteria andBillPlanDateGreaterThanOrEqualTo(String value) {
            addCriterion("BILL_PLAN_DATE >=", value, "billPlanDate");
            return (Criteria) this;
        }

        public Criteria andBillPlanDateLessThan(String value) {
            addCriterion("BILL_PLAN_DATE <", value, "billPlanDate");
            return (Criteria) this;
        }

        public Criteria andBillPlanDateLessThanOrEqualTo(String value) {
            addCriterion("BILL_PLAN_DATE <=", value, "billPlanDate");
            return (Criteria) this;
        }

        public Criteria andBillPlanDateLike(String value) {
            addCriterion("BILL_PLAN_DATE like", value, "billPlanDate");
            return (Criteria) this;
        }

        public Criteria andBillPlanDateNotLike(String value) {
            addCriterion("BILL_PLAN_DATE not like", value, "billPlanDate");
            return (Criteria) this;
        }

        public Criteria andBillPlanDateIn(List<String> values) {
            addCriterion("BILL_PLAN_DATE in", values, "billPlanDate");
            return (Criteria) this;
        }

        public Criteria andBillPlanDateNotIn(List<String> values) {
            addCriterion("BILL_PLAN_DATE not in", values, "billPlanDate");
            return (Criteria) this;
        }

        public Criteria andBillPlanDateBetween(String value1, String value2) {
            addCriterion("BILL_PLAN_DATE between", value1, value2, "billPlanDate");
            return (Criteria) this;
        }

        public Criteria andBillPlanDateNotBetween(String value1, String value2) {
            addCriterion("BILL_PLAN_DATE not between", value1, value2, "billPlanDate");
            return (Criteria) this;
        }

        public Criteria andBillPayFundIsNull() {
            addCriterion("BILL_PAY_FUND is null");
            return (Criteria) this;
        }

        public Criteria andBillPayFundIsNotNull() {
            addCriterion("BILL_PAY_FUND is not null");
            return (Criteria) this;
        }

        public Criteria andBillPayFundEqualTo(BigDecimal value) {
            addCriterion("BILL_PAY_FUND =", value, "billPayFund");
            return (Criteria) this;
        }

        public Criteria andBillPayFundNotEqualTo(BigDecimal value) {
            addCriterion("BILL_PAY_FUND <>", value, "billPayFund");
            return (Criteria) this;
        }

        public Criteria andBillPayFundGreaterThan(BigDecimal value) {
            addCriterion("BILL_PAY_FUND >", value, "billPayFund");
            return (Criteria) this;
        }

        public Criteria andBillPayFundGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BILL_PAY_FUND >=", value, "billPayFund");
            return (Criteria) this;
        }

        public Criteria andBillPayFundLessThan(BigDecimal value) {
            addCriterion("BILL_PAY_FUND <", value, "billPayFund");
            return (Criteria) this;
        }

        public Criteria andBillPayFundLessThanOrEqualTo(BigDecimal value) {
            addCriterion("BILL_PAY_FUND <=", value, "billPayFund");
            return (Criteria) this;
        }

        public Criteria andBillPayFundIn(List<BigDecimal> values) {
            addCriterion("BILL_PAY_FUND in", values, "billPayFund");
            return (Criteria) this;
        }

        public Criteria andBillPayFundNotIn(List<BigDecimal> values) {
            addCriterion("BILL_PAY_FUND not in", values, "billPayFund");
            return (Criteria) this;
        }

        public Criteria andBillPayFundBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BILL_PAY_FUND between", value1, value2, "billPayFund");
            return (Criteria) this;
        }

        public Criteria andBillPayFundNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BILL_PAY_FUND not between", value1, value2, "billPayFund");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusIdIsNull() {
            addCriterion("BILL_PLAN_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusIdIsNotNull() {
            addCriterion("BILL_PLAN_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusIdEqualTo(String value) {
            addCriterion("BILL_PLAN_STATUS_ID =", value, "billPlanStatusId");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusIdNotEqualTo(String value) {
            addCriterion("BILL_PLAN_STATUS_ID <>", value, "billPlanStatusId");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusIdGreaterThan(String value) {
            addCriterion("BILL_PLAN_STATUS_ID >", value, "billPlanStatusId");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("BILL_PLAN_STATUS_ID >=", value, "billPlanStatusId");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusIdLessThan(String value) {
            addCriterion("BILL_PLAN_STATUS_ID <", value, "billPlanStatusId");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusIdLessThanOrEqualTo(String value) {
            addCriterion("BILL_PLAN_STATUS_ID <=", value, "billPlanStatusId");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusIdLike(String value) {
            addCriterion("BILL_PLAN_STATUS_ID like", value, "billPlanStatusId");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusIdNotLike(String value) {
            addCriterion("BILL_PLAN_STATUS_ID not like", value, "billPlanStatusId");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusIdIn(List<String> values) {
            addCriterion("BILL_PLAN_STATUS_ID in", values, "billPlanStatusId");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusIdNotIn(List<String> values) {
            addCriterion("BILL_PLAN_STATUS_ID not in", values, "billPlanStatusId");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusIdBetween(String value1, String value2) {
            addCriterion("BILL_PLAN_STATUS_ID between", value1, value2, "billPlanStatusId");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusIdNotBetween(String value1, String value2) {
            addCriterion("BILL_PLAN_STATUS_ID not between", value1, value2, "billPlanStatusId");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusNameIsNull() {
            addCriterion("BILL_PLAN_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusNameIsNotNull() {
            addCriterion("BILL_PLAN_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusNameEqualTo(String value) {
            addCriterion("BILL_PLAN_STATUS_NAME =", value, "billPlanStatusName");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusNameNotEqualTo(String value) {
            addCriterion("BILL_PLAN_STATUS_NAME <>", value, "billPlanStatusName");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusNameGreaterThan(String value) {
            addCriterion("BILL_PLAN_STATUS_NAME >", value, "billPlanStatusName");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("BILL_PLAN_STATUS_NAME >=", value, "billPlanStatusName");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusNameLessThan(String value) {
            addCriterion("BILL_PLAN_STATUS_NAME <", value, "billPlanStatusName");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusNameLessThanOrEqualTo(String value) {
            addCriterion("BILL_PLAN_STATUS_NAME <=", value, "billPlanStatusName");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusNameLike(String value) {
            addCriterion("BILL_PLAN_STATUS_NAME like", value, "billPlanStatusName");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusNameNotLike(String value) {
            addCriterion("BILL_PLAN_STATUS_NAME not like", value, "billPlanStatusName");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusNameIn(List<String> values) {
            addCriterion("BILL_PLAN_STATUS_NAME in", values, "billPlanStatusName");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusNameNotIn(List<String> values) {
            addCriterion("BILL_PLAN_STATUS_NAME not in", values, "billPlanStatusName");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusNameBetween(String value1, String value2) {
            addCriterion("BILL_PLAN_STATUS_NAME between", value1, value2, "billPlanStatusName");
            return (Criteria) this;
        }

        public Criteria andBillPlanStatusNameNotBetween(String value1, String value2) {
            addCriterion("BILL_PLAN_STATUS_NAME not between", value1, value2, "billPlanStatusName");
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

        public Criteria andBillBalanceFundIsNull() {
            addCriterion("BILL_BALANCE_FUND is null");
            return (Criteria) this;
        }

        public Criteria andBillBalanceFundIsNotNull() {
            addCriterion("BILL_BALANCE_FUND is not null");
            return (Criteria) this;
        }

        public Criteria andBillBalanceFundEqualTo(BigDecimal value) {
            addCriterion("BILL_BALANCE_FUND =", value, "billBalanceFund");
            return (Criteria) this;
        }

        public Criteria andBillBalanceFundNotEqualTo(BigDecimal value) {
            addCriterion("BILL_BALANCE_FUND <>", value, "billBalanceFund");
            return (Criteria) this;
        }

        public Criteria andBillBalanceFundGreaterThan(BigDecimal value) {
            addCriterion("BILL_BALANCE_FUND >", value, "billBalanceFund");
            return (Criteria) this;
        }

        public Criteria andBillBalanceFundGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BILL_BALANCE_FUND >=", value, "billBalanceFund");
            return (Criteria) this;
        }

        public Criteria andBillBalanceFundLessThan(BigDecimal value) {
            addCriterion("BILL_BALANCE_FUND <", value, "billBalanceFund");
            return (Criteria) this;
        }

        public Criteria andBillBalanceFundLessThanOrEqualTo(BigDecimal value) {
            addCriterion("BILL_BALANCE_FUND <=", value, "billBalanceFund");
            return (Criteria) this;
        }

        public Criteria andBillBalanceFundIn(List<BigDecimal> values) {
            addCriterion("BILL_BALANCE_FUND in", values, "billBalanceFund");
            return (Criteria) this;
        }

        public Criteria andBillBalanceFundNotIn(List<BigDecimal> values) {
            addCriterion("BILL_BALANCE_FUND not in", values, "billBalanceFund");
            return (Criteria) this;
        }

        public Criteria andBillBalanceFundBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BILL_BALANCE_FUND between", value1, value2, "billBalanceFund");
            return (Criteria) this;
        }

        public Criteria andBillBalanceFundNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BILL_BALANCE_FUND not between", value1, value2, "billBalanceFund");
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