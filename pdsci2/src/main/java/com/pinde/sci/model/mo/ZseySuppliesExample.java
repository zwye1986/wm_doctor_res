package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ZseySuppliesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ZseySuppliesExample() {
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

        public Criteria andSuppliesFlowIsNull() {
            addCriterion("SUPPLIES_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSuppliesFlowIsNotNull() {
            addCriterion("SUPPLIES_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSuppliesFlowEqualTo(String value) {
            addCriterion("SUPPLIES_FLOW =", value, "suppliesFlow");
            return (Criteria) this;
        }

        public Criteria andSuppliesFlowNotEqualTo(String value) {
            addCriterion("SUPPLIES_FLOW <>", value, "suppliesFlow");
            return (Criteria) this;
        }

        public Criteria andSuppliesFlowGreaterThan(String value) {
            addCriterion("SUPPLIES_FLOW >", value, "suppliesFlow");
            return (Criteria) this;
        }

        public Criteria andSuppliesFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPLIES_FLOW >=", value, "suppliesFlow");
            return (Criteria) this;
        }

        public Criteria andSuppliesFlowLessThan(String value) {
            addCriterion("SUPPLIES_FLOW <", value, "suppliesFlow");
            return (Criteria) this;
        }

        public Criteria andSuppliesFlowLessThanOrEqualTo(String value) {
            addCriterion("SUPPLIES_FLOW <=", value, "suppliesFlow");
            return (Criteria) this;
        }

        public Criteria andSuppliesFlowLike(String value) {
            addCriterion("SUPPLIES_FLOW like", value, "suppliesFlow");
            return (Criteria) this;
        }

        public Criteria andSuppliesFlowNotLike(String value) {
            addCriterion("SUPPLIES_FLOW not like", value, "suppliesFlow");
            return (Criteria) this;
        }

        public Criteria andSuppliesFlowIn(List<String> values) {
            addCriterion("SUPPLIES_FLOW in", values, "suppliesFlow");
            return (Criteria) this;
        }

        public Criteria andSuppliesFlowNotIn(List<String> values) {
            addCriterion("SUPPLIES_FLOW not in", values, "suppliesFlow");
            return (Criteria) this;
        }

        public Criteria andSuppliesFlowBetween(String value1, String value2) {
            addCriterion("SUPPLIES_FLOW between", value1, value2, "suppliesFlow");
            return (Criteria) this;
        }

        public Criteria andSuppliesFlowNotBetween(String value1, String value2) {
            addCriterion("SUPPLIES_FLOW not between", value1, value2, "suppliesFlow");
            return (Criteria) this;
        }

        public Criteria andSuppliesNameIsNull() {
            addCriterion("SUPPLIES_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSuppliesNameIsNotNull() {
            addCriterion("SUPPLIES_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSuppliesNameEqualTo(String value) {
            addCriterion("SUPPLIES_NAME =", value, "suppliesName");
            return (Criteria) this;
        }

        public Criteria andSuppliesNameNotEqualTo(String value) {
            addCriterion("SUPPLIES_NAME <>", value, "suppliesName");
            return (Criteria) this;
        }

        public Criteria andSuppliesNameGreaterThan(String value) {
            addCriterion("SUPPLIES_NAME >", value, "suppliesName");
            return (Criteria) this;
        }

        public Criteria andSuppliesNameGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPLIES_NAME >=", value, "suppliesName");
            return (Criteria) this;
        }

        public Criteria andSuppliesNameLessThan(String value) {
            addCriterion("SUPPLIES_NAME <", value, "suppliesName");
            return (Criteria) this;
        }

        public Criteria andSuppliesNameLessThanOrEqualTo(String value) {
            addCriterion("SUPPLIES_NAME <=", value, "suppliesName");
            return (Criteria) this;
        }

        public Criteria andSuppliesNameLike(String value) {
            addCriterion("SUPPLIES_NAME like", value, "suppliesName");
            return (Criteria) this;
        }

        public Criteria andSuppliesNameNotLike(String value) {
            addCriterion("SUPPLIES_NAME not like", value, "suppliesName");
            return (Criteria) this;
        }

        public Criteria andSuppliesNameIn(List<String> values) {
            addCriterion("SUPPLIES_NAME in", values, "suppliesName");
            return (Criteria) this;
        }

        public Criteria andSuppliesNameNotIn(List<String> values) {
            addCriterion("SUPPLIES_NAME not in", values, "suppliesName");
            return (Criteria) this;
        }

        public Criteria andSuppliesNameBetween(String value1, String value2) {
            addCriterion("SUPPLIES_NAME between", value1, value2, "suppliesName");
            return (Criteria) this;
        }

        public Criteria andSuppliesNameNotBetween(String value1, String value2) {
            addCriterion("SUPPLIES_NAME not between", value1, value2, "suppliesName");
            return (Criteria) this;
        }

        public Criteria andOneNumberIsNull() {
            addCriterion("ONE_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andOneNumberIsNotNull() {
            addCriterion("ONE_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andOneNumberEqualTo(String value) {
            addCriterion("ONE_NUMBER =", value, "oneNumber");
            return (Criteria) this;
        }

        public Criteria andOneNumberNotEqualTo(String value) {
            addCriterion("ONE_NUMBER <>", value, "oneNumber");
            return (Criteria) this;
        }

        public Criteria andOneNumberGreaterThan(String value) {
            addCriterion("ONE_NUMBER >", value, "oneNumber");
            return (Criteria) this;
        }

        public Criteria andOneNumberGreaterThanOrEqualTo(String value) {
            addCriterion("ONE_NUMBER >=", value, "oneNumber");
            return (Criteria) this;
        }

        public Criteria andOneNumberLessThan(String value) {
            addCriterion("ONE_NUMBER <", value, "oneNumber");
            return (Criteria) this;
        }

        public Criteria andOneNumberLessThanOrEqualTo(String value) {
            addCriterion("ONE_NUMBER <=", value, "oneNumber");
            return (Criteria) this;
        }

        public Criteria andOneNumberLike(String value) {
            addCriterion("ONE_NUMBER like", value, "oneNumber");
            return (Criteria) this;
        }

        public Criteria andOneNumberNotLike(String value) {
            addCriterion("ONE_NUMBER not like", value, "oneNumber");
            return (Criteria) this;
        }

        public Criteria andOneNumberIn(List<String> values) {
            addCriterion("ONE_NUMBER in", values, "oneNumber");
            return (Criteria) this;
        }

        public Criteria andOneNumberNotIn(List<String> values) {
            addCriterion("ONE_NUMBER not in", values, "oneNumber");
            return (Criteria) this;
        }

        public Criteria andOneNumberBetween(String value1, String value2) {
            addCriterion("ONE_NUMBER between", value1, value2, "oneNumber");
            return (Criteria) this;
        }

        public Criteria andOneNumberNotBetween(String value1, String value2) {
            addCriterion("ONE_NUMBER not between", value1, value2, "oneNumber");
            return (Criteria) this;
        }

        public Criteria andRepeatNumberIsNull() {
            addCriterion("REPEAT_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andRepeatNumberIsNotNull() {
            addCriterion("REPEAT_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andRepeatNumberEqualTo(String value) {
            addCriterion("REPEAT_NUMBER =", value, "repeatNumber");
            return (Criteria) this;
        }

        public Criteria andRepeatNumberNotEqualTo(String value) {
            addCriterion("REPEAT_NUMBER <>", value, "repeatNumber");
            return (Criteria) this;
        }

        public Criteria andRepeatNumberGreaterThan(String value) {
            addCriterion("REPEAT_NUMBER >", value, "repeatNumber");
            return (Criteria) this;
        }

        public Criteria andRepeatNumberGreaterThanOrEqualTo(String value) {
            addCriterion("REPEAT_NUMBER >=", value, "repeatNumber");
            return (Criteria) this;
        }

        public Criteria andRepeatNumberLessThan(String value) {
            addCriterion("REPEAT_NUMBER <", value, "repeatNumber");
            return (Criteria) this;
        }

        public Criteria andRepeatNumberLessThanOrEqualTo(String value) {
            addCriterion("REPEAT_NUMBER <=", value, "repeatNumber");
            return (Criteria) this;
        }

        public Criteria andRepeatNumberLike(String value) {
            addCriterion("REPEAT_NUMBER like", value, "repeatNumber");
            return (Criteria) this;
        }

        public Criteria andRepeatNumberNotLike(String value) {
            addCriterion("REPEAT_NUMBER not like", value, "repeatNumber");
            return (Criteria) this;
        }

        public Criteria andRepeatNumberIn(List<String> values) {
            addCriterion("REPEAT_NUMBER in", values, "repeatNumber");
            return (Criteria) this;
        }

        public Criteria andRepeatNumberNotIn(List<String> values) {
            addCriterion("REPEAT_NUMBER not in", values, "repeatNumber");
            return (Criteria) this;
        }

        public Criteria andRepeatNumberBetween(String value1, String value2) {
            addCriterion("REPEAT_NUMBER between", value1, value2, "repeatNumber");
            return (Criteria) this;
        }

        public Criteria andRepeatNumberNotBetween(String value1, String value2) {
            addCriterion("REPEAT_NUMBER not between", value1, value2, "repeatNumber");
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