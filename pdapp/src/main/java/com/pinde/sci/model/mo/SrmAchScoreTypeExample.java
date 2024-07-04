package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SrmAchScoreTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmAchScoreTypeExample() {
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

        public Criteria andTypeFlowIsNull() {
            addCriterion("TYPE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTypeFlowIsNotNull() {
            addCriterion("TYPE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTypeFlowEqualTo(String value) {
            addCriterion("TYPE_FLOW =", value, "typeFlow");
            return (Criteria) this;
        }

        public Criteria andTypeFlowNotEqualTo(String value) {
            addCriterion("TYPE_FLOW <>", value, "typeFlow");
            return (Criteria) this;
        }

        public Criteria andTypeFlowGreaterThan(String value) {
            addCriterion("TYPE_FLOW >", value, "typeFlow");
            return (Criteria) this;
        }

        public Criteria andTypeFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_FLOW >=", value, "typeFlow");
            return (Criteria) this;
        }

        public Criteria andTypeFlowLessThan(String value) {
            addCriterion("TYPE_FLOW <", value, "typeFlow");
            return (Criteria) this;
        }

        public Criteria andTypeFlowLessThanOrEqualTo(String value) {
            addCriterion("TYPE_FLOW <=", value, "typeFlow");
            return (Criteria) this;
        }

        public Criteria andTypeFlowLike(String value) {
            addCriterion("TYPE_FLOW like", value, "typeFlow");
            return (Criteria) this;
        }

        public Criteria andTypeFlowNotLike(String value) {
            addCriterion("TYPE_FLOW not like", value, "typeFlow");
            return (Criteria) this;
        }

        public Criteria andTypeFlowIn(List<String> values) {
            addCriterion("TYPE_FLOW in", values, "typeFlow");
            return (Criteria) this;
        }

        public Criteria andTypeFlowNotIn(List<String> values) {
            addCriterion("TYPE_FLOW not in", values, "typeFlow");
            return (Criteria) this;
        }

        public Criteria andTypeFlowBetween(String value1, String value2) {
            addCriterion("TYPE_FLOW between", value1, value2, "typeFlow");
            return (Criteria) this;
        }

        public Criteria andTypeFlowNotBetween(String value1, String value2) {
            addCriterion("TYPE_FLOW not between", value1, value2, "typeFlow");
            return (Criteria) this;
        }

        public Criteria andParentTypeFlowIsNull() {
            addCriterion("PARENT_TYPE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andParentTypeFlowIsNotNull() {
            addCriterion("PARENT_TYPE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andParentTypeFlowEqualTo(String value) {
            addCriterion("PARENT_TYPE_FLOW =", value, "parentTypeFlow");
            return (Criteria) this;
        }

        public Criteria andParentTypeFlowNotEqualTo(String value) {
            addCriterion("PARENT_TYPE_FLOW <>", value, "parentTypeFlow");
            return (Criteria) this;
        }

        public Criteria andParentTypeFlowGreaterThan(String value) {
            addCriterion("PARENT_TYPE_FLOW >", value, "parentTypeFlow");
            return (Criteria) this;
        }

        public Criteria andParentTypeFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_TYPE_FLOW >=", value, "parentTypeFlow");
            return (Criteria) this;
        }

        public Criteria andParentTypeFlowLessThan(String value) {
            addCriterion("PARENT_TYPE_FLOW <", value, "parentTypeFlow");
            return (Criteria) this;
        }

        public Criteria andParentTypeFlowLessThanOrEqualTo(String value) {
            addCriterion("PARENT_TYPE_FLOW <=", value, "parentTypeFlow");
            return (Criteria) this;
        }

        public Criteria andParentTypeFlowLike(String value) {
            addCriterion("PARENT_TYPE_FLOW like", value, "parentTypeFlow");
            return (Criteria) this;
        }

        public Criteria andParentTypeFlowNotLike(String value) {
            addCriterion("PARENT_TYPE_FLOW not like", value, "parentTypeFlow");
            return (Criteria) this;
        }

        public Criteria andParentTypeFlowIn(List<String> values) {
            addCriterion("PARENT_TYPE_FLOW in", values, "parentTypeFlow");
            return (Criteria) this;
        }

        public Criteria andParentTypeFlowNotIn(List<String> values) {
            addCriterion("PARENT_TYPE_FLOW not in", values, "parentTypeFlow");
            return (Criteria) this;
        }

        public Criteria andParentTypeFlowBetween(String value1, String value2) {
            addCriterion("PARENT_TYPE_FLOW between", value1, value2, "parentTypeFlow");
            return (Criteria) this;
        }

        public Criteria andParentTypeFlowNotBetween(String value1, String value2) {
            addCriterion("PARENT_TYPE_FLOW not between", value1, value2, "parentTypeFlow");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameIsNull() {
            addCriterion("SCORE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameIsNotNull() {
            addCriterion("SCORE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameEqualTo(String value) {
            addCriterion("SCORE_TYPE_NAME =", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameNotEqualTo(String value) {
            addCriterion("SCORE_TYPE_NAME <>", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameGreaterThan(String value) {
            addCriterion("SCORE_TYPE_NAME >", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_TYPE_NAME >=", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameLessThan(String value) {
            addCriterion("SCORE_TYPE_NAME <", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameLessThanOrEqualTo(String value) {
            addCriterion("SCORE_TYPE_NAME <=", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameLike(String value) {
            addCriterion("SCORE_TYPE_NAME like", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameNotLike(String value) {
            addCriterion("SCORE_TYPE_NAME not like", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameIn(List<String> values) {
            addCriterion("SCORE_TYPE_NAME in", values, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameNotIn(List<String> values) {
            addCriterion("SCORE_TYPE_NAME not in", values, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameBetween(String value1, String value2) {
            addCriterion("SCORE_TYPE_NAME between", value1, value2, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameNotBetween(String value1, String value2) {
            addCriterion("SCORE_TYPE_NAME not between", value1, value2, "scoreTypeName");
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