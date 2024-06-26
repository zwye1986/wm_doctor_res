package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class JsresPowerCfgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JsresPowerCfgExample() {
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

        public Criteria andCfgCodeIsNull() {
            addCriterion("CFG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCfgCodeIsNotNull() {
            addCriterion("CFG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCfgCodeEqualTo(String value) {
            addCriterion("CFG_CODE =", value, "cfgCode");
            return (Criteria) this;
        }

        public Criteria andCfgCodeNotEqualTo(String value) {
            addCriterion("CFG_CODE <>", value, "cfgCode");
            return (Criteria) this;
        }

        public Criteria andCfgCodeGreaterThan(String value) {
            addCriterion("CFG_CODE >", value, "cfgCode");
            return (Criteria) this;
        }

        public Criteria andCfgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CFG_CODE >=", value, "cfgCode");
            return (Criteria) this;
        }

        public Criteria andCfgCodeLessThan(String value) {
            addCriterion("CFG_CODE <", value, "cfgCode");
            return (Criteria) this;
        }

        public Criteria andCfgCodeLessThanOrEqualTo(String value) {
            addCriterion("CFG_CODE <=", value, "cfgCode");
            return (Criteria) this;
        }

        public Criteria andCfgCodeLike(String value) {
            addCriterion("CFG_CODE like", value, "cfgCode");
            return (Criteria) this;
        }

        public Criteria andCfgCodeNotLike(String value) {
            addCriterion("CFG_CODE not like", value, "cfgCode");
            return (Criteria) this;
        }

        public Criteria andCfgCodeIn(List<String> values) {
            addCriterion("CFG_CODE in", values, "cfgCode");
            return (Criteria) this;
        }

        public Criteria andCfgCodeNotIn(List<String> values) {
            addCriterion("CFG_CODE not in", values, "cfgCode");
            return (Criteria) this;
        }

        public Criteria andCfgCodeBetween(String value1, String value2) {
            addCriterion("CFG_CODE between", value1, value2, "cfgCode");
            return (Criteria) this;
        }

        public Criteria andCfgCodeNotBetween(String value1, String value2) {
            addCriterion("CFG_CODE not between", value1, value2, "cfgCode");
            return (Criteria) this;
        }

        public Criteria andCfgValueIsNull() {
            addCriterion("CFG_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andCfgValueIsNotNull() {
            addCriterion("CFG_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andCfgValueEqualTo(String value) {
            addCriterion("CFG_VALUE =", value, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueNotEqualTo(String value) {
            addCriterion("CFG_VALUE <>", value, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueGreaterThan(String value) {
            addCriterion("CFG_VALUE >", value, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueGreaterThanOrEqualTo(String value) {
            addCriterion("CFG_VALUE >=", value, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueLessThan(String value) {
            addCriterion("CFG_VALUE <", value, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueLessThanOrEqualTo(String value) {
            addCriterion("CFG_VALUE <=", value, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueLike(String value) {
            addCriterion("CFG_VALUE like", value, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueNotLike(String value) {
            addCriterion("CFG_VALUE not like", value, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueIn(List<String> values) {
            addCriterion("CFG_VALUE in", values, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueNotIn(List<String> values) {
            addCriterion("CFG_VALUE not in", values, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueBetween(String value1, String value2) {
            addCriterion("CFG_VALUE between", value1, value2, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueNotBetween(String value1, String value2) {
            addCriterion("CFG_VALUE not between", value1, value2, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgDescIsNull() {
            addCriterion("CFG_DESC is null");
            return (Criteria) this;
        }

        public Criteria andCfgDescIsNotNull() {
            addCriterion("CFG_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andCfgDescEqualTo(String value) {
            addCriterion("CFG_DESC =", value, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescNotEqualTo(String value) {
            addCriterion("CFG_DESC <>", value, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescGreaterThan(String value) {
            addCriterion("CFG_DESC >", value, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescGreaterThanOrEqualTo(String value) {
            addCriterion("CFG_DESC >=", value, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescLessThan(String value) {
            addCriterion("CFG_DESC <", value, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescLessThanOrEqualTo(String value) {
            addCriterion("CFG_DESC <=", value, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescLike(String value) {
            addCriterion("CFG_DESC like", value, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescNotLike(String value) {
            addCriterion("CFG_DESC not like", value, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescIn(List<String> values) {
            addCriterion("CFG_DESC in", values, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescNotIn(List<String> values) {
            addCriterion("CFG_DESC not in", values, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescBetween(String value1, String value2) {
            addCriterion("CFG_DESC between", value1, value2, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescNotBetween(String value1, String value2) {
            addCriterion("CFG_DESC not between", value1, value2, "cfgDesc");
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

        public Criteria andPowerStartTimeIsNull() {
            addCriterion("POWER_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPowerStartTimeIsNotNull() {
            addCriterion("POWER_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPowerStartTimeEqualTo(String value) {
            addCriterion("POWER_START_TIME =", value, "powerStartTime");
            return (Criteria) this;
        }

        public Criteria andPowerStartTimeNotEqualTo(String value) {
            addCriterion("POWER_START_TIME <>", value, "powerStartTime");
            return (Criteria) this;
        }

        public Criteria andPowerStartTimeGreaterThan(String value) {
            addCriterion("POWER_START_TIME >", value, "powerStartTime");
            return (Criteria) this;
        }

        public Criteria andPowerStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("POWER_START_TIME >=", value, "powerStartTime");
            return (Criteria) this;
        }

        public Criteria andPowerStartTimeLessThan(String value) {
            addCriterion("POWER_START_TIME <", value, "powerStartTime");
            return (Criteria) this;
        }

        public Criteria andPowerStartTimeLessThanOrEqualTo(String value) {
            addCriterion("POWER_START_TIME <=", value, "powerStartTime");
            return (Criteria) this;
        }

        public Criteria andPowerStartTimeLike(String value) {
            addCriterion("POWER_START_TIME like", value, "powerStartTime");
            return (Criteria) this;
        }

        public Criteria andPowerStartTimeNotLike(String value) {
            addCriterion("POWER_START_TIME not like", value, "powerStartTime");
            return (Criteria) this;
        }

        public Criteria andPowerStartTimeIn(List<String> values) {
            addCriterion("POWER_START_TIME in", values, "powerStartTime");
            return (Criteria) this;
        }

        public Criteria andPowerStartTimeNotIn(List<String> values) {
            addCriterion("POWER_START_TIME not in", values, "powerStartTime");
            return (Criteria) this;
        }

        public Criteria andPowerStartTimeBetween(String value1, String value2) {
            addCriterion("POWER_START_TIME between", value1, value2, "powerStartTime");
            return (Criteria) this;
        }

        public Criteria andPowerStartTimeNotBetween(String value1, String value2) {
            addCriterion("POWER_START_TIME not between", value1, value2, "powerStartTime");
            return (Criteria) this;
        }

        public Criteria andPowerEndTimeIsNull() {
            addCriterion("POWER_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPowerEndTimeIsNotNull() {
            addCriterion("POWER_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPowerEndTimeEqualTo(String value) {
            addCriterion("POWER_END_TIME =", value, "powerEndTime");
            return (Criteria) this;
        }

        public Criteria andPowerEndTimeNotEqualTo(String value) {
            addCriterion("POWER_END_TIME <>", value, "powerEndTime");
            return (Criteria) this;
        }

        public Criteria andPowerEndTimeGreaterThan(String value) {
            addCriterion("POWER_END_TIME >", value, "powerEndTime");
            return (Criteria) this;
        }

        public Criteria andPowerEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("POWER_END_TIME >=", value, "powerEndTime");
            return (Criteria) this;
        }

        public Criteria andPowerEndTimeLessThan(String value) {
            addCriterion("POWER_END_TIME <", value, "powerEndTime");
            return (Criteria) this;
        }

        public Criteria andPowerEndTimeLessThanOrEqualTo(String value) {
            addCriterion("POWER_END_TIME <=", value, "powerEndTime");
            return (Criteria) this;
        }

        public Criteria andPowerEndTimeLike(String value) {
            addCriterion("POWER_END_TIME like", value, "powerEndTime");
            return (Criteria) this;
        }

        public Criteria andPowerEndTimeNotLike(String value) {
            addCriterion("POWER_END_TIME not like", value, "powerEndTime");
            return (Criteria) this;
        }

        public Criteria andPowerEndTimeIn(List<String> values) {
            addCriterion("POWER_END_TIME in", values, "powerEndTime");
            return (Criteria) this;
        }

        public Criteria andPowerEndTimeNotIn(List<String> values) {
            addCriterion("POWER_END_TIME not in", values, "powerEndTime");
            return (Criteria) this;
        }

        public Criteria andPowerEndTimeBetween(String value1, String value2) {
            addCriterion("POWER_END_TIME between", value1, value2, "powerEndTime");
            return (Criteria) this;
        }

        public Criteria andPowerEndTimeNotBetween(String value1, String value2) {
            addCriterion("POWER_END_TIME not between", value1, value2, "powerEndTime");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdIsNull() {
            addCriterion("CHECK_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdIsNotNull() {
            addCriterion("CHECK_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdEqualTo(String value) {
            addCriterion("CHECK_STATUS_ID =", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdNotEqualTo(String value) {
            addCriterion("CHECK_STATUS_ID <>", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdGreaterThan(String value) {
            addCriterion("CHECK_STATUS_ID >", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS_ID >=", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdLessThan(String value) {
            addCriterion("CHECK_STATUS_ID <", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdLessThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS_ID <=", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdLike(String value) {
            addCriterion("CHECK_STATUS_ID like", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdNotLike(String value) {
            addCriterion("CHECK_STATUS_ID not like", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdIn(List<String> values) {
            addCriterion("CHECK_STATUS_ID in", values, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdNotIn(List<String> values) {
            addCriterion("CHECK_STATUS_ID not in", values, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS_ID between", value1, value2, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdNotBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS_ID not between", value1, value2, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameIsNull() {
            addCriterion("CHECK_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameIsNotNull() {
            addCriterion("CHECK_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameEqualTo(String value) {
            addCriterion("CHECK_STATUS_NAME =", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameNotEqualTo(String value) {
            addCriterion("CHECK_STATUS_NAME <>", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameGreaterThan(String value) {
            addCriterion("CHECK_STATUS_NAME >", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS_NAME >=", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameLessThan(String value) {
            addCriterion("CHECK_STATUS_NAME <", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameLessThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS_NAME <=", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameLike(String value) {
            addCriterion("CHECK_STATUS_NAME like", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameNotLike(String value) {
            addCriterion("CHECK_STATUS_NAME not like", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameIn(List<String> values) {
            addCriterion("CHECK_STATUS_NAME in", values, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameNotIn(List<String> values) {
            addCriterion("CHECK_STATUS_NAME not in", values, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS_NAME between", value1, value2, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameNotBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS_NAME not between", value1, value2, "checkStatusName");
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