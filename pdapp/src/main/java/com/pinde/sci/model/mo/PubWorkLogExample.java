package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class PubWorkLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PubWorkLogExample() {
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

        public Criteria andLogFlowIsNull() {
            addCriterion("LOG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andLogFlowIsNotNull() {
            addCriterion("LOG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andLogFlowEqualTo(String value) {
            addCriterion("LOG_FLOW =", value, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowNotEqualTo(String value) {
            addCriterion("LOG_FLOW <>", value, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowGreaterThan(String value) {
            addCriterion("LOG_FLOW >", value, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowGreaterThanOrEqualTo(String value) {
            addCriterion("LOG_FLOW >=", value, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowLessThan(String value) {
            addCriterion("LOG_FLOW <", value, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowLessThanOrEqualTo(String value) {
            addCriterion("LOG_FLOW <=", value, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowLike(String value) {
            addCriterion("LOG_FLOW like", value, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowNotLike(String value) {
            addCriterion("LOG_FLOW not like", value, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowIn(List<String> values) {
            addCriterion("LOG_FLOW in", values, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowNotIn(List<String> values) {
            addCriterion("LOG_FLOW not in", values, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowBetween(String value1, String value2) {
            addCriterion("LOG_FLOW between", value1, value2, "logFlow");
            return (Criteria) this;
        }

        public Criteria andLogFlowNotBetween(String value1, String value2) {
            addCriterion("LOG_FLOW not between", value1, value2, "logFlow");
            return (Criteria) this;
        }

        public Criteria andFillDateIsNull() {
            addCriterion("FILL_DATE is null");
            return (Criteria) this;
        }

        public Criteria andFillDateIsNotNull() {
            addCriterion("FILL_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andFillDateEqualTo(String value) {
            addCriterion("FILL_DATE =", value, "fillDate");
            return (Criteria) this;
        }

        public Criteria andFillDateNotEqualTo(String value) {
            addCriterion("FILL_DATE <>", value, "fillDate");
            return (Criteria) this;
        }

        public Criteria andFillDateGreaterThan(String value) {
            addCriterion("FILL_DATE >", value, "fillDate");
            return (Criteria) this;
        }

        public Criteria andFillDateGreaterThanOrEqualTo(String value) {
            addCriterion("FILL_DATE >=", value, "fillDate");
            return (Criteria) this;
        }

        public Criteria andFillDateLessThan(String value) {
            addCriterion("FILL_DATE <", value, "fillDate");
            return (Criteria) this;
        }

        public Criteria andFillDateLessThanOrEqualTo(String value) {
            addCriterion("FILL_DATE <=", value, "fillDate");
            return (Criteria) this;
        }

        public Criteria andFillDateLike(String value) {
            addCriterion("FILL_DATE like", value, "fillDate");
            return (Criteria) this;
        }

        public Criteria andFillDateNotLike(String value) {
            addCriterion("FILL_DATE not like", value, "fillDate");
            return (Criteria) this;
        }

        public Criteria andFillDateIn(List<String> values) {
            addCriterion("FILL_DATE in", values, "fillDate");
            return (Criteria) this;
        }

        public Criteria andFillDateNotIn(List<String> values) {
            addCriterion("FILL_DATE not in", values, "fillDate");
            return (Criteria) this;
        }

        public Criteria andFillDateBetween(String value1, String value2) {
            addCriterion("FILL_DATE between", value1, value2, "fillDate");
            return (Criteria) this;
        }

        public Criteria andFillDateNotBetween(String value1, String value2) {
            addCriterion("FILL_DATE not between", value1, value2, "fillDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(String value) {
            addCriterion("END_DATE =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(String value) {
            addCriterion("END_DATE <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(String value) {
            addCriterion("END_DATE >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("END_DATE >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(String value) {
            addCriterion("END_DATE <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(String value) {
            addCriterion("END_DATE <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLike(String value) {
            addCriterion("END_DATE like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotLike(String value) {
            addCriterion("END_DATE not like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<String> values) {
            addCriterion("END_DATE in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<String> values) {
            addCriterion("END_DATE not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(String value1, String value2) {
            addCriterion("END_DATE between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(String value1, String value2) {
            addCriterion("END_DATE not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andWeekNumIsNull() {
            addCriterion("WEEK_NUM is null");
            return (Criteria) this;
        }

        public Criteria andWeekNumIsNotNull() {
            addCriterion("WEEK_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andWeekNumEqualTo(Short value) {
            addCriterion("WEEK_NUM =", value, "weekNum");
            return (Criteria) this;
        }

        public Criteria andWeekNumNotEqualTo(Short value) {
            addCriterion("WEEK_NUM <>", value, "weekNum");
            return (Criteria) this;
        }

        public Criteria andWeekNumGreaterThan(Short value) {
            addCriterion("WEEK_NUM >", value, "weekNum");
            return (Criteria) this;
        }

        public Criteria andWeekNumGreaterThanOrEqualTo(Short value) {
            addCriterion("WEEK_NUM >=", value, "weekNum");
            return (Criteria) this;
        }

        public Criteria andWeekNumLessThan(Short value) {
            addCriterion("WEEK_NUM <", value, "weekNum");
            return (Criteria) this;
        }

        public Criteria andWeekNumLessThanOrEqualTo(Short value) {
            addCriterion("WEEK_NUM <=", value, "weekNum");
            return (Criteria) this;
        }

        public Criteria andWeekNumIn(List<Short> values) {
            addCriterion("WEEK_NUM in", values, "weekNum");
            return (Criteria) this;
        }

        public Criteria andWeekNumNotIn(List<Short> values) {
            addCriterion("WEEK_NUM not in", values, "weekNum");
            return (Criteria) this;
        }

        public Criteria andWeekNumBetween(Short value1, Short value2) {
            addCriterion("WEEK_NUM between", value1, value2, "weekNum");
            return (Criteria) this;
        }

        public Criteria andWeekNumNotBetween(Short value1, Short value2) {
            addCriterion("WEEK_NUM not between", value1, value2, "weekNum");
            return (Criteria) this;
        }

        public Criteria andLogMonthIsNull() {
            addCriterion("LOG_MONTH is null");
            return (Criteria) this;
        }

        public Criteria andLogMonthIsNotNull() {
            addCriterion("LOG_MONTH is not null");
            return (Criteria) this;
        }

        public Criteria andLogMonthEqualTo(Short value) {
            addCriterion("LOG_MONTH =", value, "logMonth");
            return (Criteria) this;
        }

        public Criteria andLogMonthNotEqualTo(Short value) {
            addCriterion("LOG_MONTH <>", value, "logMonth");
            return (Criteria) this;
        }

        public Criteria andLogMonthGreaterThan(Short value) {
            addCriterion("LOG_MONTH >", value, "logMonth");
            return (Criteria) this;
        }

        public Criteria andLogMonthGreaterThanOrEqualTo(Short value) {
            addCriterion("LOG_MONTH >=", value, "logMonth");
            return (Criteria) this;
        }

        public Criteria andLogMonthLessThan(Short value) {
            addCriterion("LOG_MONTH <", value, "logMonth");
            return (Criteria) this;
        }

        public Criteria andLogMonthLessThanOrEqualTo(Short value) {
            addCriterion("LOG_MONTH <=", value, "logMonth");
            return (Criteria) this;
        }

        public Criteria andLogMonthIn(List<Short> values) {
            addCriterion("LOG_MONTH in", values, "logMonth");
            return (Criteria) this;
        }

        public Criteria andLogMonthNotIn(List<Short> values) {
            addCriterion("LOG_MONTH not in", values, "logMonth");
            return (Criteria) this;
        }

        public Criteria andLogMonthBetween(Short value1, Short value2) {
            addCriterion("LOG_MONTH between", value1, value2, "logMonth");
            return (Criteria) this;
        }

        public Criteria andLogMonthNotBetween(Short value1, Short value2) {
            addCriterion("LOG_MONTH not between", value1, value2, "logMonth");
            return (Criteria) this;
        }

        public Criteria andLogTypeIdIsNull() {
            addCriterion("LOG_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andLogTypeIdIsNotNull() {
            addCriterion("LOG_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLogTypeIdEqualTo(String value) {
            addCriterion("LOG_TYPE_ID =", value, "logTypeId");
            return (Criteria) this;
        }

        public Criteria andLogTypeIdNotEqualTo(String value) {
            addCriterion("LOG_TYPE_ID <>", value, "logTypeId");
            return (Criteria) this;
        }

        public Criteria andLogTypeIdGreaterThan(String value) {
            addCriterion("LOG_TYPE_ID >", value, "logTypeId");
            return (Criteria) this;
        }

        public Criteria andLogTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("LOG_TYPE_ID >=", value, "logTypeId");
            return (Criteria) this;
        }

        public Criteria andLogTypeIdLessThan(String value) {
            addCriterion("LOG_TYPE_ID <", value, "logTypeId");
            return (Criteria) this;
        }

        public Criteria andLogTypeIdLessThanOrEqualTo(String value) {
            addCriterion("LOG_TYPE_ID <=", value, "logTypeId");
            return (Criteria) this;
        }

        public Criteria andLogTypeIdLike(String value) {
            addCriterion("LOG_TYPE_ID like", value, "logTypeId");
            return (Criteria) this;
        }

        public Criteria andLogTypeIdNotLike(String value) {
            addCriterion("LOG_TYPE_ID not like", value, "logTypeId");
            return (Criteria) this;
        }

        public Criteria andLogTypeIdIn(List<String> values) {
            addCriterion("LOG_TYPE_ID in", values, "logTypeId");
            return (Criteria) this;
        }

        public Criteria andLogTypeIdNotIn(List<String> values) {
            addCriterion("LOG_TYPE_ID not in", values, "logTypeId");
            return (Criteria) this;
        }

        public Criteria andLogTypeIdBetween(String value1, String value2) {
            addCriterion("LOG_TYPE_ID between", value1, value2, "logTypeId");
            return (Criteria) this;
        }

        public Criteria andLogTypeIdNotBetween(String value1, String value2) {
            addCriterion("LOG_TYPE_ID not between", value1, value2, "logTypeId");
            return (Criteria) this;
        }

        public Criteria andLogTypeNameIsNull() {
            addCriterion("LOG_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLogTypeNameIsNotNull() {
            addCriterion("LOG_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLogTypeNameEqualTo(String value) {
            addCriterion("LOG_TYPE_NAME =", value, "logTypeName");
            return (Criteria) this;
        }

        public Criteria andLogTypeNameNotEqualTo(String value) {
            addCriterion("LOG_TYPE_NAME <>", value, "logTypeName");
            return (Criteria) this;
        }

        public Criteria andLogTypeNameGreaterThan(String value) {
            addCriterion("LOG_TYPE_NAME >", value, "logTypeName");
            return (Criteria) this;
        }

        public Criteria andLogTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("LOG_TYPE_NAME >=", value, "logTypeName");
            return (Criteria) this;
        }

        public Criteria andLogTypeNameLessThan(String value) {
            addCriterion("LOG_TYPE_NAME <", value, "logTypeName");
            return (Criteria) this;
        }

        public Criteria andLogTypeNameLessThanOrEqualTo(String value) {
            addCriterion("LOG_TYPE_NAME <=", value, "logTypeName");
            return (Criteria) this;
        }

        public Criteria andLogTypeNameLike(String value) {
            addCriterion("LOG_TYPE_NAME like", value, "logTypeName");
            return (Criteria) this;
        }

        public Criteria andLogTypeNameNotLike(String value) {
            addCriterion("LOG_TYPE_NAME not like", value, "logTypeName");
            return (Criteria) this;
        }

        public Criteria andLogTypeNameIn(List<String> values) {
            addCriterion("LOG_TYPE_NAME in", values, "logTypeName");
            return (Criteria) this;
        }

        public Criteria andLogTypeNameNotIn(List<String> values) {
            addCriterion("LOG_TYPE_NAME not in", values, "logTypeName");
            return (Criteria) this;
        }

        public Criteria andLogTypeNameBetween(String value1, String value2) {
            addCriterion("LOG_TYPE_NAME between", value1, value2, "logTypeName");
            return (Criteria) this;
        }

        public Criteria andLogTypeNameNotBetween(String value1, String value2) {
            addCriterion("LOG_TYPE_NAME not between", value1, value2, "logTypeName");
            return (Criteria) this;
        }

        public Criteria andUserFlowIsNull() {
            addCriterion("USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andUserFlowIsNotNull() {
            addCriterion("USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andUserFlowEqualTo(String value) {
            addCriterion("USER_FLOW =", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotEqualTo(String value) {
            addCriterion("USER_FLOW <>", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowGreaterThan(String value) {
            addCriterion("USER_FLOW >", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("USER_FLOW >=", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLessThan(String value) {
            addCriterion("USER_FLOW <", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLessThanOrEqualTo(String value) {
            addCriterion("USER_FLOW <=", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLike(String value) {
            addCriterion("USER_FLOW like", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotLike(String value) {
            addCriterion("USER_FLOW not like", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowIn(List<String> values) {
            addCriterion("USER_FLOW in", values, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotIn(List<String> values) {
            addCriterion("USER_FLOW not in", values, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowBetween(String value1, String value2) {
            addCriterion("USER_FLOW between", value1, value2, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotBetween(String value1, String value2) {
            addCriterion("USER_FLOW not between", value1, value2, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
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