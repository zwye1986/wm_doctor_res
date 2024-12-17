package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResProvinceFundExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResProvinceFundExample() {
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

        public Criteria andReportDateIsNull() {
            addCriterion("REPORT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andReportDateIsNotNull() {
            addCriterion("REPORT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andReportDateEqualTo(String value) {
            addCriterion("REPORT_DATE =", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotEqualTo(String value) {
            addCriterion("REPORT_DATE <>", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThan(String value) {
            addCriterion("REPORT_DATE >", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThanOrEqualTo(String value) {
            addCriterion("REPORT_DATE >=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThan(String value) {
            addCriterion("REPORT_DATE <", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThanOrEqualTo(String value) {
            addCriterion("REPORT_DATE <=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLike(String value) {
            addCriterion("REPORT_DATE like", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotLike(String value) {
            addCriterion("REPORT_DATE not like", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateIn(List<String> values) {
            addCriterion("REPORT_DATE in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotIn(List<String> values) {
            addCriterion("REPORT_DATE not in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateBetween(String value1, String value2) {
            addCriterion("REPORT_DATE between", value1, value2, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotBetween(String value1, String value2) {
            addCriterion("REPORT_DATE not between", value1, value2, "reportDate");
            return (Criteria) this;
        }

        public Criteria andInPlaceDateIsNull() {
            addCriterion("IN_PLACE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andInPlaceDateIsNotNull() {
            addCriterion("IN_PLACE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andInPlaceDateEqualTo(String value) {
            addCriterion("IN_PLACE_DATE =", value, "inPlaceDate");
            return (Criteria) this;
        }

        public Criteria andInPlaceDateNotEqualTo(String value) {
            addCriterion("IN_PLACE_DATE <>", value, "inPlaceDate");
            return (Criteria) this;
        }

        public Criteria andInPlaceDateGreaterThan(String value) {
            addCriterion("IN_PLACE_DATE >", value, "inPlaceDate");
            return (Criteria) this;
        }

        public Criteria andInPlaceDateGreaterThanOrEqualTo(String value) {
            addCriterion("IN_PLACE_DATE >=", value, "inPlaceDate");
            return (Criteria) this;
        }

        public Criteria andInPlaceDateLessThan(String value) {
            addCriterion("IN_PLACE_DATE <", value, "inPlaceDate");
            return (Criteria) this;
        }

        public Criteria andInPlaceDateLessThanOrEqualTo(String value) {
            addCriterion("IN_PLACE_DATE <=", value, "inPlaceDate");
            return (Criteria) this;
        }

        public Criteria andInPlaceDateLike(String value) {
            addCriterion("IN_PLACE_DATE like", value, "inPlaceDate");
            return (Criteria) this;
        }

        public Criteria andInPlaceDateNotLike(String value) {
            addCriterion("IN_PLACE_DATE not like", value, "inPlaceDate");
            return (Criteria) this;
        }

        public Criteria andInPlaceDateIn(List<String> values) {
            addCriterion("IN_PLACE_DATE in", values, "inPlaceDate");
            return (Criteria) this;
        }

        public Criteria andInPlaceDateNotIn(List<String> values) {
            addCriterion("IN_PLACE_DATE not in", values, "inPlaceDate");
            return (Criteria) this;
        }

        public Criteria andInPlaceDateBetween(String value1, String value2) {
            addCriterion("IN_PLACE_DATE between", value1, value2, "inPlaceDate");
            return (Criteria) this;
        }

        public Criteria andInPlaceDateNotBetween(String value1, String value2) {
            addCriterion("IN_PLACE_DATE not between", value1, value2, "inPlaceDate");
            return (Criteria) this;
        }

        public Criteria andStartUsingDateIsNull() {
            addCriterion("START_USING_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStartUsingDateIsNotNull() {
            addCriterion("START_USING_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStartUsingDateEqualTo(String value) {
            addCriterion("START_USING_DATE =", value, "startUsingDate");
            return (Criteria) this;
        }

        public Criteria andStartUsingDateNotEqualTo(String value) {
            addCriterion("START_USING_DATE <>", value, "startUsingDate");
            return (Criteria) this;
        }

        public Criteria andStartUsingDateGreaterThan(String value) {
            addCriterion("START_USING_DATE >", value, "startUsingDate");
            return (Criteria) this;
        }

        public Criteria andStartUsingDateGreaterThanOrEqualTo(String value) {
            addCriterion("START_USING_DATE >=", value, "startUsingDate");
            return (Criteria) this;
        }

        public Criteria andStartUsingDateLessThan(String value) {
            addCriterion("START_USING_DATE <", value, "startUsingDate");
            return (Criteria) this;
        }

        public Criteria andStartUsingDateLessThanOrEqualTo(String value) {
            addCriterion("START_USING_DATE <=", value, "startUsingDate");
            return (Criteria) this;
        }

        public Criteria andStartUsingDateLike(String value) {
            addCriterion("START_USING_DATE like", value, "startUsingDate");
            return (Criteria) this;
        }

        public Criteria andStartUsingDateNotLike(String value) {
            addCriterion("START_USING_DATE not like", value, "startUsingDate");
            return (Criteria) this;
        }

        public Criteria andStartUsingDateIn(List<String> values) {
            addCriterion("START_USING_DATE in", values, "startUsingDate");
            return (Criteria) this;
        }

        public Criteria andStartUsingDateNotIn(List<String> values) {
            addCriterion("START_USING_DATE not in", values, "startUsingDate");
            return (Criteria) this;
        }

        public Criteria andStartUsingDateBetween(String value1, String value2) {
            addCriterion("START_USING_DATE between", value1, value2, "startUsingDate");
            return (Criteria) this;
        }

        public Criteria andStartUsingDateNotBetween(String value1, String value2) {
            addCriterion("START_USING_DATE not between", value1, value2, "startUsingDate");
            return (Criteria) this;
        }

        public Criteria andStopUsingDateIsNull() {
            addCriterion("STOP_USING_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStopUsingDateIsNotNull() {
            addCriterion("STOP_USING_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStopUsingDateEqualTo(String value) {
            addCriterion("STOP_USING_DATE =", value, "stopUsingDate");
            return (Criteria) this;
        }

        public Criteria andStopUsingDateNotEqualTo(String value) {
            addCriterion("STOP_USING_DATE <>", value, "stopUsingDate");
            return (Criteria) this;
        }

        public Criteria andStopUsingDateGreaterThan(String value) {
            addCriterion("STOP_USING_DATE >", value, "stopUsingDate");
            return (Criteria) this;
        }

        public Criteria andStopUsingDateGreaterThanOrEqualTo(String value) {
            addCriterion("STOP_USING_DATE >=", value, "stopUsingDate");
            return (Criteria) this;
        }

        public Criteria andStopUsingDateLessThan(String value) {
            addCriterion("STOP_USING_DATE <", value, "stopUsingDate");
            return (Criteria) this;
        }

        public Criteria andStopUsingDateLessThanOrEqualTo(String value) {
            addCriterion("STOP_USING_DATE <=", value, "stopUsingDate");
            return (Criteria) this;
        }

        public Criteria andStopUsingDateLike(String value) {
            addCriterion("STOP_USING_DATE like", value, "stopUsingDate");
            return (Criteria) this;
        }

        public Criteria andStopUsingDateNotLike(String value) {
            addCriterion("STOP_USING_DATE not like", value, "stopUsingDate");
            return (Criteria) this;
        }

        public Criteria andStopUsingDateIn(List<String> values) {
            addCriterion("STOP_USING_DATE in", values, "stopUsingDate");
            return (Criteria) this;
        }

        public Criteria andStopUsingDateNotIn(List<String> values) {
            addCriterion("STOP_USING_DATE not in", values, "stopUsingDate");
            return (Criteria) this;
        }

        public Criteria andStopUsingDateBetween(String value1, String value2) {
            addCriterion("STOP_USING_DATE between", value1, value2, "stopUsingDate");
            return (Criteria) this;
        }

        public Criteria andStopUsingDateNotBetween(String value1, String value2) {
            addCriterion("STOP_USING_DATE not between", value1, value2, "stopUsingDate");
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