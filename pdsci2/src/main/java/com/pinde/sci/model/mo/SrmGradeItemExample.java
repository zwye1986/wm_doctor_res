package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SrmGradeItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmGradeItemExample() {
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

        public Criteria andItemFlowIsNull() {
            addCriterion("ITEM_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andItemFlowIsNotNull() {
            addCriterion("ITEM_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andItemFlowEqualTo(String value) {
            addCriterion("ITEM_FLOW =", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowNotEqualTo(String value) {
            addCriterion("ITEM_FLOW <>", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowGreaterThan(String value) {
            addCriterion("ITEM_FLOW >", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_FLOW >=", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowLessThan(String value) {
            addCriterion("ITEM_FLOW <", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowLessThanOrEqualTo(String value) {
            addCriterion("ITEM_FLOW <=", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowLike(String value) {
            addCriterion("ITEM_FLOW like", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowNotLike(String value) {
            addCriterion("ITEM_FLOW not like", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowIn(List<String> values) {
            addCriterion("ITEM_FLOW in", values, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowNotIn(List<String> values) {
            addCriterion("ITEM_FLOW not in", values, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowBetween(String value1, String value2) {
            addCriterion("ITEM_FLOW between", value1, value2, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowNotBetween(String value1, String value2) {
            addCriterion("ITEM_FLOW not between", value1, value2, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNull() {
            addCriterion("ITEM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNotNull() {
            addCriterion("ITEM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andItemNameEqualTo(String value) {
            addCriterion("ITEM_NAME =", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotEqualTo(String value) {
            addCriterion("ITEM_NAME <>", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThan(String value) {
            addCriterion("ITEM_NAME >", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_NAME >=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThan(String value) {
            addCriterion("ITEM_NAME <", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThanOrEqualTo(String value) {
            addCriterion("ITEM_NAME <=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLike(String value) {
            addCriterion("ITEM_NAME like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotLike(String value) {
            addCriterion("ITEM_NAME not like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameIn(List<String> values) {
            addCriterion("ITEM_NAME in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotIn(List<String> values) {
            addCriterion("ITEM_NAME not in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameBetween(String value1, String value2) {
            addCriterion("ITEM_NAME between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotBetween(String value1, String value2) {
            addCriterion("ITEM_NAME not between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowIsNull() {
            addCriterion("SCHEME_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowIsNotNull() {
            addCriterion("SCHEME_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowEqualTo(String value) {
            addCriterion("SCHEME_FLOW =", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowNotEqualTo(String value) {
            addCriterion("SCHEME_FLOW <>", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowGreaterThan(String value) {
            addCriterion("SCHEME_FLOW >", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCHEME_FLOW >=", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowLessThan(String value) {
            addCriterion("SCHEME_FLOW <", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowLessThanOrEqualTo(String value) {
            addCriterion("SCHEME_FLOW <=", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowLike(String value) {
            addCriterion("SCHEME_FLOW like", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowNotLike(String value) {
            addCriterion("SCHEME_FLOW not like", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowIn(List<String> values) {
            addCriterion("SCHEME_FLOW in", values, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowNotIn(List<String> values) {
            addCriterion("SCHEME_FLOW not in", values, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowBetween(String value1, String value2) {
            addCriterion("SCHEME_FLOW between", value1, value2, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowNotBetween(String value1, String value2) {
            addCriterion("SCHEME_FLOW not between", value1, value2, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andItemScoreIsNull() {
            addCriterion("ITEM_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andItemScoreIsNotNull() {
            addCriterion("ITEM_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andItemScoreEqualTo(Integer value) {
            addCriterion("ITEM_SCORE =", value, "itemScore");
            return (Criteria) this;
        }

        public Criteria andItemScoreNotEqualTo(Integer value) {
            addCriterion("ITEM_SCORE <>", value, "itemScore");
            return (Criteria) this;
        }

        public Criteria andItemScoreGreaterThan(Integer value) {
            addCriterion("ITEM_SCORE >", value, "itemScore");
            return (Criteria) this;
        }

        public Criteria andItemScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("ITEM_SCORE >=", value, "itemScore");
            return (Criteria) this;
        }

        public Criteria andItemScoreLessThan(Integer value) {
            addCriterion("ITEM_SCORE <", value, "itemScore");
            return (Criteria) this;
        }

        public Criteria andItemScoreLessThanOrEqualTo(Integer value) {
            addCriterion("ITEM_SCORE <=", value, "itemScore");
            return (Criteria) this;
        }

        public Criteria andItemScoreIn(List<Integer> values) {
            addCriterion("ITEM_SCORE in", values, "itemScore");
            return (Criteria) this;
        }

        public Criteria andItemScoreNotIn(List<Integer> values) {
            addCriterion("ITEM_SCORE not in", values, "itemScore");
            return (Criteria) this;
        }

        public Criteria andItemScoreBetween(Integer value1, Integer value2) {
            addCriterion("ITEM_SCORE between", value1, value2, "itemScore");
            return (Criteria) this;
        }

        public Criteria andItemScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("ITEM_SCORE not between", value1, value2, "itemScore");
            return (Criteria) this;
        }

        public Criteria andItemScoreTipIsNull() {
            addCriterion("ITEM_SCORE_TIP is null");
            return (Criteria) this;
        }

        public Criteria andItemScoreTipIsNotNull() {
            addCriterion("ITEM_SCORE_TIP is not null");
            return (Criteria) this;
        }

        public Criteria andItemScoreTipEqualTo(String value) {
            addCriterion("ITEM_SCORE_TIP =", value, "itemScoreTip");
            return (Criteria) this;
        }

        public Criteria andItemScoreTipNotEqualTo(String value) {
            addCriterion("ITEM_SCORE_TIP <>", value, "itemScoreTip");
            return (Criteria) this;
        }

        public Criteria andItemScoreTipGreaterThan(String value) {
            addCriterion("ITEM_SCORE_TIP >", value, "itemScoreTip");
            return (Criteria) this;
        }

        public Criteria andItemScoreTipGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_SCORE_TIP >=", value, "itemScoreTip");
            return (Criteria) this;
        }

        public Criteria andItemScoreTipLessThan(String value) {
            addCriterion("ITEM_SCORE_TIP <", value, "itemScoreTip");
            return (Criteria) this;
        }

        public Criteria andItemScoreTipLessThanOrEqualTo(String value) {
            addCriterion("ITEM_SCORE_TIP <=", value, "itemScoreTip");
            return (Criteria) this;
        }

        public Criteria andItemScoreTipLike(String value) {
            addCriterion("ITEM_SCORE_TIP like", value, "itemScoreTip");
            return (Criteria) this;
        }

        public Criteria andItemScoreTipNotLike(String value) {
            addCriterion("ITEM_SCORE_TIP not like", value, "itemScoreTip");
            return (Criteria) this;
        }

        public Criteria andItemScoreTipIn(List<String> values) {
            addCriterion("ITEM_SCORE_TIP in", values, "itemScoreTip");
            return (Criteria) this;
        }

        public Criteria andItemScoreTipNotIn(List<String> values) {
            addCriterion("ITEM_SCORE_TIP not in", values, "itemScoreTip");
            return (Criteria) this;
        }

        public Criteria andItemScoreTipBetween(String value1, String value2) {
            addCriterion("ITEM_SCORE_TIP between", value1, value2, "itemScoreTip");
            return (Criteria) this;
        }

        public Criteria andItemScoreTipNotBetween(String value1, String value2) {
            addCriterion("ITEM_SCORE_TIP not between", value1, value2, "itemScoreTip");
            return (Criteria) this;
        }

        public Criteria andItemDescIsNull() {
            addCriterion("ITEM_DESC is null");
            return (Criteria) this;
        }

        public Criteria andItemDescIsNotNull() {
            addCriterion("ITEM_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andItemDescEqualTo(String value) {
            addCriterion("ITEM_DESC =", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescNotEqualTo(String value) {
            addCriterion("ITEM_DESC <>", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescGreaterThan(String value) {
            addCriterion("ITEM_DESC >", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_DESC >=", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescLessThan(String value) {
            addCriterion("ITEM_DESC <", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescLessThanOrEqualTo(String value) {
            addCriterion("ITEM_DESC <=", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescLike(String value) {
            addCriterion("ITEM_DESC like", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescNotLike(String value) {
            addCriterion("ITEM_DESC not like", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescIn(List<String> values) {
            addCriterion("ITEM_DESC in", values, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescNotIn(List<String> values) {
            addCriterion("ITEM_DESC not in", values, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescBetween(String value1, String value2) {
            addCriterion("ITEM_DESC between", value1, value2, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescNotBetween(String value1, String value2) {
            addCriterion("ITEM_DESC not between", value1, value2, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andOrdinalIsNull() {
            addCriterion("ORDINAL is null");
            return (Criteria) this;
        }

        public Criteria andOrdinalIsNotNull() {
            addCriterion("ORDINAL is not null");
            return (Criteria) this;
        }

        public Criteria andOrdinalEqualTo(Integer value) {
            addCriterion("ORDINAL =", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotEqualTo(Integer value) {
            addCriterion("ORDINAL <>", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThan(Integer value) {
            addCriterion("ORDINAL >", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL >=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThan(Integer value) {
            addCriterion("ORDINAL <", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL <=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalIn(List<Integer> values) {
            addCriterion("ORDINAL in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotIn(List<Integer> values) {
            addCriterion("ORDINAL not in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL between", value1, value2, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL not between", value1, value2, "ordinal");
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

        public Criteria andScoreWeightIsNull() {
            addCriterion("SCORE_WEIGHT is null");
            return (Criteria) this;
        }

        public Criteria andScoreWeightIsNotNull() {
            addCriterion("SCORE_WEIGHT is not null");
            return (Criteria) this;
        }

        public Criteria andScoreWeightEqualTo(BigDecimal value) {
            addCriterion("SCORE_WEIGHT =", value, "scoreWeight");
            return (Criteria) this;
        }

        public Criteria andScoreWeightNotEqualTo(BigDecimal value) {
            addCriterion("SCORE_WEIGHT <>", value, "scoreWeight");
            return (Criteria) this;
        }

        public Criteria andScoreWeightGreaterThan(BigDecimal value) {
            addCriterion("SCORE_WEIGHT >", value, "scoreWeight");
            return (Criteria) this;
        }

        public Criteria andScoreWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SCORE_WEIGHT >=", value, "scoreWeight");
            return (Criteria) this;
        }

        public Criteria andScoreWeightLessThan(BigDecimal value) {
            addCriterion("SCORE_WEIGHT <", value, "scoreWeight");
            return (Criteria) this;
        }

        public Criteria andScoreWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SCORE_WEIGHT <=", value, "scoreWeight");
            return (Criteria) this;
        }

        public Criteria andScoreWeightIn(List<BigDecimal> values) {
            addCriterion("SCORE_WEIGHT in", values, "scoreWeight");
            return (Criteria) this;
        }

        public Criteria andScoreWeightNotIn(List<BigDecimal> values) {
            addCriterion("SCORE_WEIGHT not in", values, "scoreWeight");
            return (Criteria) this;
        }

        public Criteria andScoreWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SCORE_WEIGHT between", value1, value2, "scoreWeight");
            return (Criteria) this;
        }

        public Criteria andScoreWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SCORE_WEIGHT not between", value1, value2, "scoreWeight");
            return (Criteria) this;
        }

        public Criteria andItemTypeIsNull() {
            addCriterion("ITEM_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andItemTypeIsNotNull() {
            addCriterion("ITEM_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andItemTypeEqualTo(String value) {
            addCriterion("ITEM_TYPE =", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotEqualTo(String value) {
            addCriterion("ITEM_TYPE <>", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeGreaterThan(String value) {
            addCriterion("ITEM_TYPE >", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_TYPE >=", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLessThan(String value) {
            addCriterion("ITEM_TYPE <", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLessThanOrEqualTo(String value) {
            addCriterion("ITEM_TYPE <=", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLike(String value) {
            addCriterion("ITEM_TYPE like", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotLike(String value) {
            addCriterion("ITEM_TYPE not like", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeIn(List<String> values) {
            addCriterion("ITEM_TYPE in", values, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotIn(List<String> values) {
            addCriterion("ITEM_TYPE not in", values, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeBetween(String value1, String value2) {
            addCriterion("ITEM_TYPE between", value1, value2, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotBetween(String value1, String value2) {
            addCriterion("ITEM_TYPE not between", value1, value2, "itemType");
            return (Criteria) this;
        }

        public Criteria andSecondLevelFormIsNull() {
            addCriterion("SECOND_LEVEL_FORM is null");
            return (Criteria) this;
        }

        public Criteria andSecondLevelFormIsNotNull() {
            addCriterion("SECOND_LEVEL_FORM is not null");
            return (Criteria) this;
        }

        public Criteria andSecondLevelFormEqualTo(String value) {
            addCriterion("SECOND_LEVEL_FORM =", value, "secondLevelForm");
            return (Criteria) this;
        }

        public Criteria andSecondLevelFormNotEqualTo(String value) {
            addCriterion("SECOND_LEVEL_FORM <>", value, "secondLevelForm");
            return (Criteria) this;
        }

        public Criteria andSecondLevelFormGreaterThan(String value) {
            addCriterion("SECOND_LEVEL_FORM >", value, "secondLevelForm");
            return (Criteria) this;
        }

        public Criteria andSecondLevelFormGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_LEVEL_FORM >=", value, "secondLevelForm");
            return (Criteria) this;
        }

        public Criteria andSecondLevelFormLessThan(String value) {
            addCriterion("SECOND_LEVEL_FORM <", value, "secondLevelForm");
            return (Criteria) this;
        }

        public Criteria andSecondLevelFormLessThanOrEqualTo(String value) {
            addCriterion("SECOND_LEVEL_FORM <=", value, "secondLevelForm");
            return (Criteria) this;
        }

        public Criteria andSecondLevelFormLike(String value) {
            addCriterion("SECOND_LEVEL_FORM like", value, "secondLevelForm");
            return (Criteria) this;
        }

        public Criteria andSecondLevelFormNotLike(String value) {
            addCriterion("SECOND_LEVEL_FORM not like", value, "secondLevelForm");
            return (Criteria) this;
        }

        public Criteria andSecondLevelFormIn(List<String> values) {
            addCriterion("SECOND_LEVEL_FORM in", values, "secondLevelForm");
            return (Criteria) this;
        }

        public Criteria andSecondLevelFormNotIn(List<String> values) {
            addCriterion("SECOND_LEVEL_FORM not in", values, "secondLevelForm");
            return (Criteria) this;
        }

        public Criteria andSecondLevelFormBetween(String value1, String value2) {
            addCriterion("SECOND_LEVEL_FORM between", value1, value2, "secondLevelForm");
            return (Criteria) this;
        }

        public Criteria andSecondLevelFormNotBetween(String value1, String value2) {
            addCriterion("SECOND_LEVEL_FORM not between", value1, value2, "secondLevelForm");
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