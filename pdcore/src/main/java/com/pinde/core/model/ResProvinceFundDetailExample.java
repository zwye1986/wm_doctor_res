package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResProvinceFundDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResProvinceFundDetailExample() {
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

        public Criteria andMainFlowIsNull() {
            addCriterion("MAIN_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andMainFlowIsNotNull() {
            addCriterion("MAIN_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andMainFlowEqualTo(String value) {
            addCriterion("MAIN_FLOW =", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowNotEqualTo(String value) {
            addCriterion("MAIN_FLOW <>", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowGreaterThan(String value) {
            addCriterion("MAIN_FLOW >", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowGreaterThanOrEqualTo(String value) {
            addCriterion("MAIN_FLOW >=", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowLessThan(String value) {
            addCriterion("MAIN_FLOW <", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowLessThanOrEqualTo(String value) {
            addCriterion("MAIN_FLOW <=", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowLike(String value) {
            addCriterion("MAIN_FLOW like", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowNotLike(String value) {
            addCriterion("MAIN_FLOW not like", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowIn(List<String> values) {
            addCriterion("MAIN_FLOW in", values, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowNotIn(List<String> values) {
            addCriterion("MAIN_FLOW not in", values, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowBetween(String value1, String value2) {
            addCriterion("MAIN_FLOW between", value1, value2, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowNotBetween(String value1, String value2) {
            addCriterion("MAIN_FLOW not between", value1, value2, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsIdIsNull() {
            addCriterion("SOURCES_OF_FUNDS_ID is null");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsIdIsNotNull() {
            addCriterion("SOURCES_OF_FUNDS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsIdEqualTo(String value) {
            addCriterion("SOURCES_OF_FUNDS_ID =", value, "sourcesOfFundsId");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsIdNotEqualTo(String value) {
            addCriterion("SOURCES_OF_FUNDS_ID <>", value, "sourcesOfFundsId");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsIdGreaterThan(String value) {
            addCriterion("SOURCES_OF_FUNDS_ID >", value, "sourcesOfFundsId");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsIdGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCES_OF_FUNDS_ID >=", value, "sourcesOfFundsId");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsIdLessThan(String value) {
            addCriterion("SOURCES_OF_FUNDS_ID <", value, "sourcesOfFundsId");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsIdLessThanOrEqualTo(String value) {
            addCriterion("SOURCES_OF_FUNDS_ID <=", value, "sourcesOfFundsId");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsIdLike(String value) {
            addCriterion("SOURCES_OF_FUNDS_ID like", value, "sourcesOfFundsId");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsIdNotLike(String value) {
            addCriterion("SOURCES_OF_FUNDS_ID not like", value, "sourcesOfFundsId");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsIdIn(List<String> values) {
            addCriterion("SOURCES_OF_FUNDS_ID in", values, "sourcesOfFundsId");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsIdNotIn(List<String> values) {
            addCriterion("SOURCES_OF_FUNDS_ID not in", values, "sourcesOfFundsId");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsIdBetween(String value1, String value2) {
            addCriterion("SOURCES_OF_FUNDS_ID between", value1, value2, "sourcesOfFundsId");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsIdNotBetween(String value1, String value2) {
            addCriterion("SOURCES_OF_FUNDS_ID not between", value1, value2, "sourcesOfFundsId");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsNameIsNull() {
            addCriterion("SOURCES_OF_FUNDS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsNameIsNotNull() {
            addCriterion("SOURCES_OF_FUNDS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsNameEqualTo(String value) {
            addCriterion("SOURCES_OF_FUNDS_NAME =", value, "sourcesOfFundsName");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsNameNotEqualTo(String value) {
            addCriterion("SOURCES_OF_FUNDS_NAME <>", value, "sourcesOfFundsName");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsNameGreaterThan(String value) {
            addCriterion("SOURCES_OF_FUNDS_NAME >", value, "sourcesOfFundsName");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsNameGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCES_OF_FUNDS_NAME >=", value, "sourcesOfFundsName");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsNameLessThan(String value) {
            addCriterion("SOURCES_OF_FUNDS_NAME <", value, "sourcesOfFundsName");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsNameLessThanOrEqualTo(String value) {
            addCriterion("SOURCES_OF_FUNDS_NAME <=", value, "sourcesOfFundsName");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsNameLike(String value) {
            addCriterion("SOURCES_OF_FUNDS_NAME like", value, "sourcesOfFundsName");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsNameNotLike(String value) {
            addCriterion("SOURCES_OF_FUNDS_NAME not like", value, "sourcesOfFundsName");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsNameIn(List<String> values) {
            addCriterion("SOURCES_OF_FUNDS_NAME in", values, "sourcesOfFundsName");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsNameNotIn(List<String> values) {
            addCriterion("SOURCES_OF_FUNDS_NAME not in", values, "sourcesOfFundsName");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsNameBetween(String value1, String value2) {
            addCriterion("SOURCES_OF_FUNDS_NAME between", value1, value2, "sourcesOfFundsName");
            return (Criteria) this;
        }

        public Criteria andSourcesOfFundsNameNotBetween(String value1, String value2) {
            addCriterion("SOURCES_OF_FUNDS_NAME not between", value1, value2, "sourcesOfFundsName");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsIdIsNull() {
            addCriterion("PROJECT_OF_FUNDS_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsIdIsNotNull() {
            addCriterion("PROJECT_OF_FUNDS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsIdEqualTo(String value) {
            addCriterion("PROJECT_OF_FUNDS_ID =", value, "projectOfFundsId");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsIdNotEqualTo(String value) {
            addCriterion("PROJECT_OF_FUNDS_ID <>", value, "projectOfFundsId");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsIdGreaterThan(String value) {
            addCriterion("PROJECT_OF_FUNDS_ID >", value, "projectOfFundsId");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJECT_OF_FUNDS_ID >=", value, "projectOfFundsId");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsIdLessThan(String value) {
            addCriterion("PROJECT_OF_FUNDS_ID <", value, "projectOfFundsId");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsIdLessThanOrEqualTo(String value) {
            addCriterion("PROJECT_OF_FUNDS_ID <=", value, "projectOfFundsId");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsIdLike(String value) {
            addCriterion("PROJECT_OF_FUNDS_ID like", value, "projectOfFundsId");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsIdNotLike(String value) {
            addCriterion("PROJECT_OF_FUNDS_ID not like", value, "projectOfFundsId");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsIdIn(List<String> values) {
            addCriterion("PROJECT_OF_FUNDS_ID in", values, "projectOfFundsId");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsIdNotIn(List<String> values) {
            addCriterion("PROJECT_OF_FUNDS_ID not in", values, "projectOfFundsId");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsIdBetween(String value1, String value2) {
            addCriterion("PROJECT_OF_FUNDS_ID between", value1, value2, "projectOfFundsId");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsIdNotBetween(String value1, String value2) {
            addCriterion("PROJECT_OF_FUNDS_ID not between", value1, value2, "projectOfFundsId");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsNameIsNull() {
            addCriterion("PROJECT_OF_FUNDS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsNameIsNotNull() {
            addCriterion("PROJECT_OF_FUNDS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsNameEqualTo(String value) {
            addCriterion("PROJECT_OF_FUNDS_NAME =", value, "projectOfFundsName");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsNameNotEqualTo(String value) {
            addCriterion("PROJECT_OF_FUNDS_NAME <>", value, "projectOfFundsName");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsNameGreaterThan(String value) {
            addCriterion("PROJECT_OF_FUNDS_NAME >", value, "projectOfFundsName");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJECT_OF_FUNDS_NAME >=", value, "projectOfFundsName");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsNameLessThan(String value) {
            addCriterion("PROJECT_OF_FUNDS_NAME <", value, "projectOfFundsName");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsNameLessThanOrEqualTo(String value) {
            addCriterion("PROJECT_OF_FUNDS_NAME <=", value, "projectOfFundsName");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsNameLike(String value) {
            addCriterion("PROJECT_OF_FUNDS_NAME like", value, "projectOfFundsName");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsNameNotLike(String value) {
            addCriterion("PROJECT_OF_FUNDS_NAME not like", value, "projectOfFundsName");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsNameIn(List<String> values) {
            addCriterion("PROJECT_OF_FUNDS_NAME in", values, "projectOfFundsName");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsNameNotIn(List<String> values) {
            addCriterion("PROJECT_OF_FUNDS_NAME not in", values, "projectOfFundsName");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsNameBetween(String value1, String value2) {
            addCriterion("PROJECT_OF_FUNDS_NAME between", value1, value2, "projectOfFundsName");
            return (Criteria) this;
        }

        public Criteria andProjectOfFundsNameNotBetween(String value1, String value2) {
            addCriterion("PROJECT_OF_FUNDS_NAME not between", value1, value2, "projectOfFundsName");
            return (Criteria) this;
        }

        public Criteria andAmountOfMoneyIsNull() {
            addCriterion("AMOUNT_OF_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andAmountOfMoneyIsNotNull() {
            addCriterion("AMOUNT_OF_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andAmountOfMoneyEqualTo(String value) {
            addCriterion("AMOUNT_OF_MONEY =", value, "amountOfMoney");
            return (Criteria) this;
        }

        public Criteria andAmountOfMoneyNotEqualTo(String value) {
            addCriterion("AMOUNT_OF_MONEY <>", value, "amountOfMoney");
            return (Criteria) this;
        }

        public Criteria andAmountOfMoneyGreaterThan(String value) {
            addCriterion("AMOUNT_OF_MONEY >", value, "amountOfMoney");
            return (Criteria) this;
        }

        public Criteria andAmountOfMoneyGreaterThanOrEqualTo(String value) {
            addCriterion("AMOUNT_OF_MONEY >=", value, "amountOfMoney");
            return (Criteria) this;
        }

        public Criteria andAmountOfMoneyLessThan(String value) {
            addCriterion("AMOUNT_OF_MONEY <", value, "amountOfMoney");
            return (Criteria) this;
        }

        public Criteria andAmountOfMoneyLessThanOrEqualTo(String value) {
            addCriterion("AMOUNT_OF_MONEY <=", value, "amountOfMoney");
            return (Criteria) this;
        }

        public Criteria andAmountOfMoneyLike(String value) {
            addCriterion("AMOUNT_OF_MONEY like", value, "amountOfMoney");
            return (Criteria) this;
        }

        public Criteria andAmountOfMoneyNotLike(String value) {
            addCriterion("AMOUNT_OF_MONEY not like", value, "amountOfMoney");
            return (Criteria) this;
        }

        public Criteria andAmountOfMoneyIn(List<String> values) {
            addCriterion("AMOUNT_OF_MONEY in", values, "amountOfMoney");
            return (Criteria) this;
        }

        public Criteria andAmountOfMoneyNotIn(List<String> values) {
            addCriterion("AMOUNT_OF_MONEY not in", values, "amountOfMoney");
            return (Criteria) this;
        }

        public Criteria andAmountOfMoneyBetween(String value1, String value2) {
            addCriterion("AMOUNT_OF_MONEY between", value1, value2, "amountOfMoney");
            return (Criteria) this;
        }

        public Criteria andAmountOfMoneyNotBetween(String value1, String value2) {
            addCriterion("AMOUNT_OF_MONEY not between", value1, value2, "amountOfMoney");
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

        public Criteria andIsSendIsNull() {
            addCriterion("IS_SEND is null");
            return (Criteria) this;
        }

        public Criteria andIsSendIsNotNull() {
            addCriterion("IS_SEND is not null");
            return (Criteria) this;
        }

        public Criteria andIsSendEqualTo(String value) {
            addCriterion("IS_SEND =", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotEqualTo(String value) {
            addCriterion("IS_SEND <>", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendGreaterThan(String value) {
            addCriterion("IS_SEND >", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SEND >=", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendLessThan(String value) {
            addCriterion("IS_SEND <", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendLessThanOrEqualTo(String value) {
            addCriterion("IS_SEND <=", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendLike(String value) {
            addCriterion("IS_SEND like", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotLike(String value) {
            addCriterion("IS_SEND not like", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendIn(List<String> values) {
            addCriterion("IS_SEND in", values, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotIn(List<String> values) {
            addCriterion("IS_SEND not in", values, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendBetween(String value1, String value2) {
            addCriterion("IS_SEND between", value1, value2, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotBetween(String value1, String value2) {
            addCriterion("IS_SEND not between", value1, value2, "isSend");
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