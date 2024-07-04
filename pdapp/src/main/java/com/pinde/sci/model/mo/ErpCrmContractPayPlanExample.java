package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ErpCrmContractPayPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ErpCrmContractPayPlanExample() {
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

        public Criteria andPlanFlowIsNull() {
            addCriterion("PLAN_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPlanFlowIsNotNull() {
            addCriterion("PLAN_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPlanFlowEqualTo(String value) {
            addCriterion("PLAN_FLOW =", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotEqualTo(String value) {
            addCriterion("PLAN_FLOW <>", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowGreaterThan(String value) {
            addCriterion("PLAN_FLOW >", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_FLOW >=", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowLessThan(String value) {
            addCriterion("PLAN_FLOW <", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowLessThanOrEqualTo(String value) {
            addCriterion("PLAN_FLOW <=", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowLike(String value) {
            addCriterion("PLAN_FLOW like", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotLike(String value) {
            addCriterion("PLAN_FLOW not like", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowIn(List<String> values) {
            addCriterion("PLAN_FLOW in", values, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotIn(List<String> values) {
            addCriterion("PLAN_FLOW not in", values, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowBetween(String value1, String value2) {
            addCriterion("PLAN_FLOW between", value1, value2, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotBetween(String value1, String value2) {
            addCriterion("PLAN_FLOW not between", value1, value2, "planFlow");
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

        public Criteria andPlanDateIsNull() {
            addCriterion("PLAN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPlanDateIsNotNull() {
            addCriterion("PLAN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPlanDateEqualTo(String value) {
            addCriterion("PLAN_DATE =", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateNotEqualTo(String value) {
            addCriterion("PLAN_DATE <>", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateGreaterThan(String value) {
            addCriterion("PLAN_DATE >", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_DATE >=", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateLessThan(String value) {
            addCriterion("PLAN_DATE <", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateLessThanOrEqualTo(String value) {
            addCriterion("PLAN_DATE <=", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateLike(String value) {
            addCriterion("PLAN_DATE like", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateNotLike(String value) {
            addCriterion("PLAN_DATE not like", value, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateIn(List<String> values) {
            addCriterion("PLAN_DATE in", values, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateNotIn(List<String> values) {
            addCriterion("PLAN_DATE not in", values, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateBetween(String value1, String value2) {
            addCriterion("PLAN_DATE between", value1, value2, "planDate");
            return (Criteria) this;
        }

        public Criteria andPlanDateNotBetween(String value1, String value2) {
            addCriterion("PLAN_DATE not between", value1, value2, "planDate");
            return (Criteria) this;
        }

        public Criteria andPayFundIsNull() {
            addCriterion("PAY_FUND is null");
            return (Criteria) this;
        }

        public Criteria andPayFundIsNotNull() {
            addCriterion("PAY_FUND is not null");
            return (Criteria) this;
        }

        public Criteria andPayFundEqualTo(BigDecimal value) {
            addCriterion("PAY_FUND =", value, "payFund");
            return (Criteria) this;
        }

        public Criteria andPayFundNotEqualTo(BigDecimal value) {
            addCriterion("PAY_FUND <>", value, "payFund");
            return (Criteria) this;
        }

        public Criteria andPayFundGreaterThan(BigDecimal value) {
            addCriterion("PAY_FUND >", value, "payFund");
            return (Criteria) this;
        }

        public Criteria andPayFundGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PAY_FUND >=", value, "payFund");
            return (Criteria) this;
        }

        public Criteria andPayFundLessThan(BigDecimal value) {
            addCriterion("PAY_FUND <", value, "payFund");
            return (Criteria) this;
        }

        public Criteria andPayFundLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PAY_FUND <=", value, "payFund");
            return (Criteria) this;
        }

        public Criteria andPayFundIn(List<BigDecimal> values) {
            addCriterion("PAY_FUND in", values, "payFund");
            return (Criteria) this;
        }

        public Criteria andPayFundNotIn(List<BigDecimal> values) {
            addCriterion("PAY_FUND not in", values, "payFund");
            return (Criteria) this;
        }

        public Criteria andPayFundBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PAY_FUND between", value1, value2, "payFund");
            return (Criteria) this;
        }

        public Criteria andPayFundNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PAY_FUND not between", value1, value2, "payFund");
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

        public Criteria andPlanStatusIdIsNull() {
            addCriterion("PLAN_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdIsNotNull() {
            addCriterion("PLAN_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdEqualTo(String value) {
            addCriterion("PLAN_STATUS_ID =", value, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdNotEqualTo(String value) {
            addCriterion("PLAN_STATUS_ID <>", value, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdGreaterThan(String value) {
            addCriterion("PLAN_STATUS_ID >", value, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_STATUS_ID >=", value, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdLessThan(String value) {
            addCriterion("PLAN_STATUS_ID <", value, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdLessThanOrEqualTo(String value) {
            addCriterion("PLAN_STATUS_ID <=", value, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdLike(String value) {
            addCriterion("PLAN_STATUS_ID like", value, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdNotLike(String value) {
            addCriterion("PLAN_STATUS_ID not like", value, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdIn(List<String> values) {
            addCriterion("PLAN_STATUS_ID in", values, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdNotIn(List<String> values) {
            addCriterion("PLAN_STATUS_ID not in", values, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdBetween(String value1, String value2) {
            addCriterion("PLAN_STATUS_ID between", value1, value2, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdNotBetween(String value1, String value2) {
            addCriterion("PLAN_STATUS_ID not between", value1, value2, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameIsNull() {
            addCriterion("PLAN_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameIsNotNull() {
            addCriterion("PLAN_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameEqualTo(String value) {
            addCriterion("PLAN_STATUS_NAME =", value, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameNotEqualTo(String value) {
            addCriterion("PLAN_STATUS_NAME <>", value, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameGreaterThan(String value) {
            addCriterion("PLAN_STATUS_NAME >", value, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_STATUS_NAME >=", value, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameLessThan(String value) {
            addCriterion("PLAN_STATUS_NAME <", value, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameLessThanOrEqualTo(String value) {
            addCriterion("PLAN_STATUS_NAME <=", value, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameLike(String value) {
            addCriterion("PLAN_STATUS_NAME like", value, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameNotLike(String value) {
            addCriterion("PLAN_STATUS_NAME not like", value, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameIn(List<String> values) {
            addCriterion("PLAN_STATUS_NAME in", values, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameNotIn(List<String> values) {
            addCriterion("PLAN_STATUS_NAME not in", values, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameBetween(String value1, String value2) {
            addCriterion("PLAN_STATUS_NAME between", value1, value2, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameNotBetween(String value1, String value2) {
            addCriterion("PLAN_STATUS_NAME not between", value1, value2, "planStatusName");
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

        public Criteria andBalanceFundIsNull() {
            addCriterion("BALANCE_FUND is null");
            return (Criteria) this;
        }

        public Criteria andBalanceFundIsNotNull() {
            addCriterion("BALANCE_FUND is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceFundEqualTo(BigDecimal value) {
            addCriterion("BALANCE_FUND =", value, "balanceFund");
            return (Criteria) this;
        }

        public Criteria andBalanceFundNotEqualTo(BigDecimal value) {
            addCriterion("BALANCE_FUND <>", value, "balanceFund");
            return (Criteria) this;
        }

        public Criteria andBalanceFundGreaterThan(BigDecimal value) {
            addCriterion("BALANCE_FUND >", value, "balanceFund");
            return (Criteria) this;
        }

        public Criteria andBalanceFundGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BALANCE_FUND >=", value, "balanceFund");
            return (Criteria) this;
        }

        public Criteria andBalanceFundLessThan(BigDecimal value) {
            addCriterion("BALANCE_FUND <", value, "balanceFund");
            return (Criteria) this;
        }

        public Criteria andBalanceFundLessThanOrEqualTo(BigDecimal value) {
            addCriterion("BALANCE_FUND <=", value, "balanceFund");
            return (Criteria) this;
        }

        public Criteria andBalanceFundIn(List<BigDecimal> values) {
            addCriterion("BALANCE_FUND in", values, "balanceFund");
            return (Criteria) this;
        }

        public Criteria andBalanceFundNotIn(List<BigDecimal> values) {
            addCriterion("BALANCE_FUND not in", values, "balanceFund");
            return (Criteria) this;
        }

        public Criteria andBalanceFundBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BALANCE_FUND between", value1, value2, "balanceFund");
            return (Criteria) this;
        }

        public Criteria andBalanceFundNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BALANCE_FUND not between", value1, value2, "balanceFund");
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