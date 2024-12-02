package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class RecruitLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RecruitLogExample() {
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

        public Criteria andApplyFlowIsNull() {
            addCriterion("APPLY_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyFlowIsNotNull() {
            addCriterion("APPLY_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyFlowEqualTo(String value) {
            addCriterion("APPLY_FLOW =", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotEqualTo(String value) {
            addCriterion("APPLY_FLOW <>", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowGreaterThan(String value) {
            addCriterion("APPLY_FLOW >", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_FLOW >=", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowLessThan(String value) {
            addCriterion("APPLY_FLOW <", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_FLOW <=", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowLike(String value) {
            addCriterion("APPLY_FLOW like", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotLike(String value) {
            addCriterion("APPLY_FLOW not like", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowIn(List<String> values) {
            addCriterion("APPLY_FLOW in", values, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotIn(List<String> values) {
            addCriterion("APPLY_FLOW not in", values, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowBetween(String value1, String value2) {
            addCriterion("APPLY_FLOW between", value1, value2, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_FLOW not between", value1, value2, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andChangeItemIsNull() {
            addCriterion("CHANGE_ITEM is null");
            return (Criteria) this;
        }

        public Criteria andChangeItemIsNotNull() {
            addCriterion("CHANGE_ITEM is not null");
            return (Criteria) this;
        }

        public Criteria andChangeItemEqualTo(String value) {
            addCriterion("CHANGE_ITEM =", value, "changeItem");
            return (Criteria) this;
        }

        public Criteria andChangeItemNotEqualTo(String value) {
            addCriterion("CHANGE_ITEM <>", value, "changeItem");
            return (Criteria) this;
        }

        public Criteria andChangeItemGreaterThan(String value) {
            addCriterion("CHANGE_ITEM >", value, "changeItem");
            return (Criteria) this;
        }

        public Criteria andChangeItemGreaterThanOrEqualTo(String value) {
            addCriterion("CHANGE_ITEM >=", value, "changeItem");
            return (Criteria) this;
        }

        public Criteria andChangeItemLessThan(String value) {
            addCriterion("CHANGE_ITEM <", value, "changeItem");
            return (Criteria) this;
        }

        public Criteria andChangeItemLessThanOrEqualTo(String value) {
            addCriterion("CHANGE_ITEM <=", value, "changeItem");
            return (Criteria) this;
        }

        public Criteria andChangeItemLike(String value) {
            addCriterion("CHANGE_ITEM like", value, "changeItem");
            return (Criteria) this;
        }

        public Criteria andChangeItemNotLike(String value) {
            addCriterion("CHANGE_ITEM not like", value, "changeItem");
            return (Criteria) this;
        }

        public Criteria andChangeItemIn(List<String> values) {
            addCriterion("CHANGE_ITEM in", values, "changeItem");
            return (Criteria) this;
        }

        public Criteria andChangeItemNotIn(List<String> values) {
            addCriterion("CHANGE_ITEM not in", values, "changeItem");
            return (Criteria) this;
        }

        public Criteria andChangeItemBetween(String value1, String value2) {
            addCriterion("CHANGE_ITEM between", value1, value2, "changeItem");
            return (Criteria) this;
        }

        public Criteria andChangeItemNotBetween(String value1, String value2) {
            addCriterion("CHANGE_ITEM not between", value1, value2, "changeItem");
            return (Criteria) this;
        }

        public Criteria andOldDataIsNull() {
            addCriterion("OLD_DATA is null");
            return (Criteria) this;
        }

        public Criteria andOldDataIsNotNull() {
            addCriterion("OLD_DATA is not null");
            return (Criteria) this;
        }

        public Criteria andOldDataEqualTo(String value) {
            addCriterion("OLD_DATA =", value, "oldData");
            return (Criteria) this;
        }

        public Criteria andOldDataNotEqualTo(String value) {
            addCriterion("OLD_DATA <>", value, "oldData");
            return (Criteria) this;
        }

        public Criteria andOldDataGreaterThan(String value) {
            addCriterion("OLD_DATA >", value, "oldData");
            return (Criteria) this;
        }

        public Criteria andOldDataGreaterThanOrEqualTo(String value) {
            addCriterion("OLD_DATA >=", value, "oldData");
            return (Criteria) this;
        }

        public Criteria andOldDataLessThan(String value) {
            addCriterion("OLD_DATA <", value, "oldData");
            return (Criteria) this;
        }

        public Criteria andOldDataLessThanOrEqualTo(String value) {
            addCriterion("OLD_DATA <=", value, "oldData");
            return (Criteria) this;
        }

        public Criteria andOldDataLike(String value) {
            addCriterion("OLD_DATA like", value, "oldData");
            return (Criteria) this;
        }

        public Criteria andOldDataNotLike(String value) {
            addCriterion("OLD_DATA not like", value, "oldData");
            return (Criteria) this;
        }

        public Criteria andOldDataIn(List<String> values) {
            addCriterion("OLD_DATA in", values, "oldData");
            return (Criteria) this;
        }

        public Criteria andOldDataNotIn(List<String> values) {
            addCriterion("OLD_DATA not in", values, "oldData");
            return (Criteria) this;
        }

        public Criteria andOldDataBetween(String value1, String value2) {
            addCriterion("OLD_DATA between", value1, value2, "oldData");
            return (Criteria) this;
        }

        public Criteria andOldDataNotBetween(String value1, String value2) {
            addCriterion("OLD_DATA not between", value1, value2, "oldData");
            return (Criteria) this;
        }

        public Criteria andNewDataIsNull() {
            addCriterion("NEW_DATA is null");
            return (Criteria) this;
        }

        public Criteria andNewDataIsNotNull() {
            addCriterion("NEW_DATA is not null");
            return (Criteria) this;
        }

        public Criteria andNewDataEqualTo(String value) {
            addCriterion("NEW_DATA =", value, "newData");
            return (Criteria) this;
        }

        public Criteria andNewDataNotEqualTo(String value) {
            addCriterion("NEW_DATA <>", value, "newData");
            return (Criteria) this;
        }

        public Criteria andNewDataGreaterThan(String value) {
            addCriterion("NEW_DATA >", value, "newData");
            return (Criteria) this;
        }

        public Criteria andNewDataGreaterThanOrEqualTo(String value) {
            addCriterion("NEW_DATA >=", value, "newData");
            return (Criteria) this;
        }

        public Criteria andNewDataLessThan(String value) {
            addCriterion("NEW_DATA <", value, "newData");
            return (Criteria) this;
        }

        public Criteria andNewDataLessThanOrEqualTo(String value) {
            addCriterion("NEW_DATA <=", value, "newData");
            return (Criteria) this;
        }

        public Criteria andNewDataLike(String value) {
            addCriterion("NEW_DATA like", value, "newData");
            return (Criteria) this;
        }

        public Criteria andNewDataNotLike(String value) {
            addCriterion("NEW_DATA not like", value, "newData");
            return (Criteria) this;
        }

        public Criteria andNewDataIn(List<String> values) {
            addCriterion("NEW_DATA in", values, "newData");
            return (Criteria) this;
        }

        public Criteria andNewDataNotIn(List<String> values) {
            addCriterion("NEW_DATA not in", values, "newData");
            return (Criteria) this;
        }

        public Criteria andNewDataBetween(String value1, String value2) {
            addCriterion("NEW_DATA between", value1, value2, "newData");
            return (Criteria) this;
        }

        public Criteria andNewDataNotBetween(String value1, String value2) {
            addCriterion("NEW_DATA not between", value1, value2, "newData");
            return (Criteria) this;
        }

        public Criteria andChangeTimeIsNull() {
            addCriterion("CHANGE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andChangeTimeIsNotNull() {
            addCriterion("CHANGE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andChangeTimeEqualTo(String value) {
            addCriterion("CHANGE_TIME =", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeNotEqualTo(String value) {
            addCriterion("CHANGE_TIME <>", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeGreaterThan(String value) {
            addCriterion("CHANGE_TIME >", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CHANGE_TIME >=", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeLessThan(String value) {
            addCriterion("CHANGE_TIME <", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeLessThanOrEqualTo(String value) {
            addCriterion("CHANGE_TIME <=", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeLike(String value) {
            addCriterion("CHANGE_TIME like", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeNotLike(String value) {
            addCriterion("CHANGE_TIME not like", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeIn(List<String> values) {
            addCriterion("CHANGE_TIME in", values, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeNotIn(List<String> values) {
            addCriterion("CHANGE_TIME not in", values, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeBetween(String value1, String value2) {
            addCriterion("CHANGE_TIME between", value1, value2, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeNotBetween(String value1, String value2) {
            addCriterion("CHANGE_TIME not between", value1, value2, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeUserFlowIsNull() {
            addCriterion("CHANGE_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andChangeUserFlowIsNotNull() {
            addCriterion("CHANGE_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andChangeUserFlowEqualTo(String value) {
            addCriterion("CHANGE_USER_FLOW =", value, "changeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChangeUserFlowNotEqualTo(String value) {
            addCriterion("CHANGE_USER_FLOW <>", value, "changeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChangeUserFlowGreaterThan(String value) {
            addCriterion("CHANGE_USER_FLOW >", value, "changeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChangeUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CHANGE_USER_FLOW >=", value, "changeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChangeUserFlowLessThan(String value) {
            addCriterion("CHANGE_USER_FLOW <", value, "changeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChangeUserFlowLessThanOrEqualTo(String value) {
            addCriterion("CHANGE_USER_FLOW <=", value, "changeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChangeUserFlowLike(String value) {
            addCriterion("CHANGE_USER_FLOW like", value, "changeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChangeUserFlowNotLike(String value) {
            addCriterion("CHANGE_USER_FLOW not like", value, "changeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChangeUserFlowIn(List<String> values) {
            addCriterion("CHANGE_USER_FLOW in", values, "changeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChangeUserFlowNotIn(List<String> values) {
            addCriterion("CHANGE_USER_FLOW not in", values, "changeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChangeUserFlowBetween(String value1, String value2) {
            addCriterion("CHANGE_USER_FLOW between", value1, value2, "changeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChangeUserFlowNotBetween(String value1, String value2) {
            addCriterion("CHANGE_USER_FLOW not between", value1, value2, "changeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChangeUserCodeIsNull() {
            addCriterion("CHANGE_USER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andChangeUserCodeIsNotNull() {
            addCriterion("CHANGE_USER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andChangeUserCodeEqualTo(String value) {
            addCriterion("CHANGE_USER_CODE =", value, "changeUserCode");
            return (Criteria) this;
        }

        public Criteria andChangeUserCodeNotEqualTo(String value) {
            addCriterion("CHANGE_USER_CODE <>", value, "changeUserCode");
            return (Criteria) this;
        }

        public Criteria andChangeUserCodeGreaterThan(String value) {
            addCriterion("CHANGE_USER_CODE >", value, "changeUserCode");
            return (Criteria) this;
        }

        public Criteria andChangeUserCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CHANGE_USER_CODE >=", value, "changeUserCode");
            return (Criteria) this;
        }

        public Criteria andChangeUserCodeLessThan(String value) {
            addCriterion("CHANGE_USER_CODE <", value, "changeUserCode");
            return (Criteria) this;
        }

        public Criteria andChangeUserCodeLessThanOrEqualTo(String value) {
            addCriterion("CHANGE_USER_CODE <=", value, "changeUserCode");
            return (Criteria) this;
        }

        public Criteria andChangeUserCodeLike(String value) {
            addCriterion("CHANGE_USER_CODE like", value, "changeUserCode");
            return (Criteria) this;
        }

        public Criteria andChangeUserCodeNotLike(String value) {
            addCriterion("CHANGE_USER_CODE not like", value, "changeUserCode");
            return (Criteria) this;
        }

        public Criteria andChangeUserCodeIn(List<String> values) {
            addCriterion("CHANGE_USER_CODE in", values, "changeUserCode");
            return (Criteria) this;
        }

        public Criteria andChangeUserCodeNotIn(List<String> values) {
            addCriterion("CHANGE_USER_CODE not in", values, "changeUserCode");
            return (Criteria) this;
        }

        public Criteria andChangeUserCodeBetween(String value1, String value2) {
            addCriterion("CHANGE_USER_CODE between", value1, value2, "changeUserCode");
            return (Criteria) this;
        }

        public Criteria andChangeUserCodeNotBetween(String value1, String value2) {
            addCriterion("CHANGE_USER_CODE not between", value1, value2, "changeUserCode");
            return (Criteria) this;
        }

        public Criteria andLogDescIsNull() {
            addCriterion("LOG_DESC is null");
            return (Criteria) this;
        }

        public Criteria andLogDescIsNotNull() {
            addCriterion("LOG_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andLogDescEqualTo(String value) {
            addCriterion("LOG_DESC =", value, "logDesc");
            return (Criteria) this;
        }

        public Criteria andLogDescNotEqualTo(String value) {
            addCriterion("LOG_DESC <>", value, "logDesc");
            return (Criteria) this;
        }

        public Criteria andLogDescGreaterThan(String value) {
            addCriterion("LOG_DESC >", value, "logDesc");
            return (Criteria) this;
        }

        public Criteria andLogDescGreaterThanOrEqualTo(String value) {
            addCriterion("LOG_DESC >=", value, "logDesc");
            return (Criteria) this;
        }

        public Criteria andLogDescLessThan(String value) {
            addCriterion("LOG_DESC <", value, "logDesc");
            return (Criteria) this;
        }

        public Criteria andLogDescLessThanOrEqualTo(String value) {
            addCriterion("LOG_DESC <=", value, "logDesc");
            return (Criteria) this;
        }

        public Criteria andLogDescLike(String value) {
            addCriterion("LOG_DESC like", value, "logDesc");
            return (Criteria) this;
        }

        public Criteria andLogDescNotLike(String value) {
            addCriterion("LOG_DESC not like", value, "logDesc");
            return (Criteria) this;
        }

        public Criteria andLogDescIn(List<String> values) {
            addCriterion("LOG_DESC in", values, "logDesc");
            return (Criteria) this;
        }

        public Criteria andLogDescNotIn(List<String> values) {
            addCriterion("LOG_DESC not in", values, "logDesc");
            return (Criteria) this;
        }

        public Criteria andLogDescBetween(String value1, String value2) {
            addCriterion("LOG_DESC between", value1, value2, "logDesc");
            return (Criteria) this;
        }

        public Criteria andLogDescNotBetween(String value1, String value2) {
            addCriterion("LOG_DESC not between", value1, value2, "logDesc");
            return (Criteria) this;
        }

        public Criteria andWsIdIsNull() {
            addCriterion("WS_ID is null");
            return (Criteria) this;
        }

        public Criteria andWsIdIsNotNull() {
            addCriterion("WS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWsIdEqualTo(String value) {
            addCriterion("WS_ID =", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdNotEqualTo(String value) {
            addCriterion("WS_ID <>", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdGreaterThan(String value) {
            addCriterion("WS_ID >", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdGreaterThanOrEqualTo(String value) {
            addCriterion("WS_ID >=", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdLessThan(String value) {
            addCriterion("WS_ID <", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdLessThanOrEqualTo(String value) {
            addCriterion("WS_ID <=", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdLike(String value) {
            addCriterion("WS_ID like", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdNotLike(String value) {
            addCriterion("WS_ID not like", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdIn(List<String> values) {
            addCriterion("WS_ID in", values, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdNotIn(List<String> values) {
            addCriterion("WS_ID not in", values, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdBetween(String value1, String value2) {
            addCriterion("WS_ID between", value1, value2, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdNotBetween(String value1, String value2) {
            addCriterion("WS_ID not between", value1, value2, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsNameIsNull() {
            addCriterion("WS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andWsNameIsNotNull() {
            addCriterion("WS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andWsNameEqualTo(String value) {
            addCriterion("WS_NAME =", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameNotEqualTo(String value) {
            addCriterion("WS_NAME <>", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameGreaterThan(String value) {
            addCriterion("WS_NAME >", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameGreaterThanOrEqualTo(String value) {
            addCriterion("WS_NAME >=", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameLessThan(String value) {
            addCriterion("WS_NAME <", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameLessThanOrEqualTo(String value) {
            addCriterion("WS_NAME <=", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameLike(String value) {
            addCriterion("WS_NAME like", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameNotLike(String value) {
            addCriterion("WS_NAME not like", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameIn(List<String> values) {
            addCriterion("WS_NAME in", values, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameNotIn(List<String> values) {
            addCriterion("WS_NAME not in", values, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameBetween(String value1, String value2) {
            addCriterion("WS_NAME between", value1, value2, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameNotBetween(String value1, String value2) {
            addCriterion("WS_NAME not between", value1, value2, "wsName");
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