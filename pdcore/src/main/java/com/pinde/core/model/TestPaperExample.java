package com.pinde.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestPaperExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TestPaperExample() {
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

        public Criteria andPaperFlowIsNull() {
            addCriterion("PAPER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPaperFlowIsNotNull() {
            addCriterion("PAPER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPaperFlowEqualTo(String value) {
            addCriterion("PAPER_FLOW =", value, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowNotEqualTo(String value) {
            addCriterion("PAPER_FLOW <>", value, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowGreaterThan(String value) {
            addCriterion("PAPER_FLOW >", value, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_FLOW >=", value, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowLessThan(String value) {
            addCriterion("PAPER_FLOW <", value, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowLessThanOrEqualTo(String value) {
            addCriterion("PAPER_FLOW <=", value, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowLike(String value) {
            addCriterion("PAPER_FLOW like", value, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowNotLike(String value) {
            addCriterion("PAPER_FLOW not like", value, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowIn(List<String> values) {
            addCriterion("PAPER_FLOW in", values, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowNotIn(List<String> values) {
            addCriterion("PAPER_FLOW not in", values, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowBetween(String value1, String value2) {
            addCriterion("PAPER_FLOW between", value1, value2, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowNotBetween(String value1, String value2) {
            addCriterion("PAPER_FLOW not between", value1, value2, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperNameIsNull() {
            addCriterion("PAPER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPaperNameIsNotNull() {
            addCriterion("PAPER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPaperNameEqualTo(String value) {
            addCriterion("PAPER_NAME =", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotEqualTo(String value) {
            addCriterion("PAPER_NAME <>", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameGreaterThan(String value) {
            addCriterion("PAPER_NAME >", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_NAME >=", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameLessThan(String value) {
            addCriterion("PAPER_NAME <", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameLessThanOrEqualTo(String value) {
            addCriterion("PAPER_NAME <=", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameLike(String value) {
            addCriterion("PAPER_NAME like", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotLike(String value) {
            addCriterion("PAPER_NAME not like", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameIn(List<String> values) {
            addCriterion("PAPER_NAME in", values, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotIn(List<String> values) {
            addCriterion("PAPER_NAME not in", values, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameBetween(String value1, String value2) {
            addCriterion("PAPER_NAME between", value1, value2, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotBetween(String value1, String value2) {
            addCriterion("PAPER_NAME not between", value1, value2, "paperName");
            return (Criteria) this;
        }

        public Criteria andTotleScoreIsNull() {
            addCriterion("TOTLE_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andTotleScoreIsNotNull() {
            addCriterion("TOTLE_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andTotleScoreEqualTo(BigDecimal value) {
            addCriterion("TOTLE_SCORE =", value, "totleScore");
            return (Criteria) this;
        }

        public Criteria andTotleScoreNotEqualTo(BigDecimal value) {
            addCriterion("TOTLE_SCORE <>", value, "totleScore");
            return (Criteria) this;
        }

        public Criteria andTotleScoreGreaterThan(BigDecimal value) {
            addCriterion("TOTLE_SCORE >", value, "totleScore");
            return (Criteria) this;
        }

        public Criteria andTotleScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTLE_SCORE >=", value, "totleScore");
            return (Criteria) this;
        }

        public Criteria andTotleScoreLessThan(BigDecimal value) {
            addCriterion("TOTLE_SCORE <", value, "totleScore");
            return (Criteria) this;
        }

        public Criteria andTotleScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTLE_SCORE <=", value, "totleScore");
            return (Criteria) this;
        }

        public Criteria andTotleScoreIn(List<BigDecimal> values) {
            addCriterion("TOTLE_SCORE in", values, "totleScore");
            return (Criteria) this;
        }

        public Criteria andTotleScoreNotIn(List<BigDecimal> values) {
            addCriterion("TOTLE_SCORE not in", values, "totleScore");
            return (Criteria) this;
        }

        public Criteria andTotleScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTLE_SCORE between", value1, value2, "totleScore");
            return (Criteria) this;
        }

        public Criteria andTotleScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTLE_SCORE not between", value1, value2, "totleScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreIsNull() {
            addCriterion("PASS_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andPassScoreIsNotNull() {
            addCriterion("PASS_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andPassScoreEqualTo(BigDecimal value) {
            addCriterion("PASS_SCORE =", value, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreNotEqualTo(BigDecimal value) {
            addCriterion("PASS_SCORE <>", value, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreGreaterThan(BigDecimal value) {
            addCriterion("PASS_SCORE >", value, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PASS_SCORE >=", value, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreLessThan(BigDecimal value) {
            addCriterion("PASS_SCORE <", value, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PASS_SCORE <=", value, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreIn(List<BigDecimal> values) {
            addCriterion("PASS_SCORE in", values, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreNotIn(List<BigDecimal> values) {
            addCriterion("PASS_SCORE not in", values, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PASS_SCORE between", value1, value2, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PASS_SCORE not between", value1, value2, "passScore");
            return (Criteria) this;
        }

        public Criteria andTestTimeIsNull() {
            addCriterion("TEST_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTestTimeIsNotNull() {
            addCriterion("TEST_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTestTimeEqualTo(String value) {
            addCriterion("TEST_TIME =", value, "testTime");
            return (Criteria) this;
        }

        public Criteria andTestTimeNotEqualTo(String value) {
            addCriterion("TEST_TIME <>", value, "testTime");
            return (Criteria) this;
        }

        public Criteria andTestTimeGreaterThan(String value) {
            addCriterion("TEST_TIME >", value, "testTime");
            return (Criteria) this;
        }

        public Criteria andTestTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_TIME >=", value, "testTime");
            return (Criteria) this;
        }

        public Criteria andTestTimeLessThan(String value) {
            addCriterion("TEST_TIME <", value, "testTime");
            return (Criteria) this;
        }

        public Criteria andTestTimeLessThanOrEqualTo(String value) {
            addCriterion("TEST_TIME <=", value, "testTime");
            return (Criteria) this;
        }

        public Criteria andTestTimeLike(String value) {
            addCriterion("TEST_TIME like", value, "testTime");
            return (Criteria) this;
        }

        public Criteria andTestTimeNotLike(String value) {
            addCriterion("TEST_TIME not like", value, "testTime");
            return (Criteria) this;
        }

        public Criteria andTestTimeIn(List<String> values) {
            addCriterion("TEST_TIME in", values, "testTime");
            return (Criteria) this;
        }

        public Criteria andTestTimeNotIn(List<String> values) {
            addCriterion("TEST_TIME not in", values, "testTime");
            return (Criteria) this;
        }

        public Criteria andTestTimeBetween(String value1, String value2) {
            addCriterion("TEST_TIME between", value1, value2, "testTime");
            return (Criteria) this;
        }

        public Criteria andTestTimeNotBetween(String value1, String value2) {
            addCriterion("TEST_TIME not between", value1, value2, "testTime");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIdIsNull() {
            addCriterion("PAPER_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIdIsNotNull() {
            addCriterion("PAPER_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIdEqualTo(String value) {
            addCriterion("PAPER_TYPE_ID =", value, "paperTypeId");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIdNotEqualTo(String value) {
            addCriterion("PAPER_TYPE_ID <>", value, "paperTypeId");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIdGreaterThan(String value) {
            addCriterion("PAPER_TYPE_ID >", value, "paperTypeId");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_TYPE_ID >=", value, "paperTypeId");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIdLessThan(String value) {
            addCriterion("PAPER_TYPE_ID <", value, "paperTypeId");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIdLessThanOrEqualTo(String value) {
            addCriterion("PAPER_TYPE_ID <=", value, "paperTypeId");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIdLike(String value) {
            addCriterion("PAPER_TYPE_ID like", value, "paperTypeId");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIdNotLike(String value) {
            addCriterion("PAPER_TYPE_ID not like", value, "paperTypeId");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIdIn(List<String> values) {
            addCriterion("PAPER_TYPE_ID in", values, "paperTypeId");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIdNotIn(List<String> values) {
            addCriterion("PAPER_TYPE_ID not in", values, "paperTypeId");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIdBetween(String value1, String value2) {
            addCriterion("PAPER_TYPE_ID between", value1, value2, "paperTypeId");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIdNotBetween(String value1, String value2) {
            addCriterion("PAPER_TYPE_ID not between", value1, value2, "paperTypeId");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameIsNull() {
            addCriterion("PAPER_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameIsNotNull() {
            addCriterion("PAPER_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameEqualTo(String value) {
            addCriterion("PAPER_TYPE_NAME =", value, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameNotEqualTo(String value) {
            addCriterion("PAPER_TYPE_NAME <>", value, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameGreaterThan(String value) {
            addCriterion("PAPER_TYPE_NAME >", value, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_TYPE_NAME >=", value, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameLessThan(String value) {
            addCriterion("PAPER_TYPE_NAME <", value, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameLessThanOrEqualTo(String value) {
            addCriterion("PAPER_TYPE_NAME <=", value, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameLike(String value) {
            addCriterion("PAPER_TYPE_NAME like", value, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameNotLike(String value) {
            addCriterion("PAPER_TYPE_NAME not like", value, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameIn(List<String> values) {
            addCriterion("PAPER_TYPE_NAME in", values, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameNotIn(List<String> values) {
            addCriterion("PAPER_TYPE_NAME not in", values, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameBetween(String value1, String value2) {
            addCriterion("PAPER_TYPE_NAME between", value1, value2, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameNotBetween(String value1, String value2) {
            addCriterion("PAPER_TYPE_NAME not between", value1, value2, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperUserFlowIsNull() {
            addCriterion("PAPER_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPaperUserFlowIsNotNull() {
            addCriterion("PAPER_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPaperUserFlowEqualTo(String value) {
            addCriterion("PAPER_USER_FLOW =", value, "paperUserFlow");
            return (Criteria) this;
        }

        public Criteria andPaperUserFlowNotEqualTo(String value) {
            addCriterion("PAPER_USER_FLOW <>", value, "paperUserFlow");
            return (Criteria) this;
        }

        public Criteria andPaperUserFlowGreaterThan(String value) {
            addCriterion("PAPER_USER_FLOW >", value, "paperUserFlow");
            return (Criteria) this;
        }

        public Criteria andPaperUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_USER_FLOW >=", value, "paperUserFlow");
            return (Criteria) this;
        }

        public Criteria andPaperUserFlowLessThan(String value) {
            addCriterion("PAPER_USER_FLOW <", value, "paperUserFlow");
            return (Criteria) this;
        }

        public Criteria andPaperUserFlowLessThanOrEqualTo(String value) {
            addCriterion("PAPER_USER_FLOW <=", value, "paperUserFlow");
            return (Criteria) this;
        }

        public Criteria andPaperUserFlowLike(String value) {
            addCriterion("PAPER_USER_FLOW like", value, "paperUserFlow");
            return (Criteria) this;
        }

        public Criteria andPaperUserFlowNotLike(String value) {
            addCriterion("PAPER_USER_FLOW not like", value, "paperUserFlow");
            return (Criteria) this;
        }

        public Criteria andPaperUserFlowIn(List<String> values) {
            addCriterion("PAPER_USER_FLOW in", values, "paperUserFlow");
            return (Criteria) this;
        }

        public Criteria andPaperUserFlowNotIn(List<String> values) {
            addCriterion("PAPER_USER_FLOW not in", values, "paperUserFlow");
            return (Criteria) this;
        }

        public Criteria andPaperUserFlowBetween(String value1, String value2) {
            addCriterion("PAPER_USER_FLOW between", value1, value2, "paperUserFlow");
            return (Criteria) this;
        }

        public Criteria andPaperUserFlowNotBetween(String value1, String value2) {
            addCriterion("PAPER_USER_FLOW not between", value1, value2, "paperUserFlow");
            return (Criteria) this;
        }

        public Criteria andPaperUserCodeIsNull() {
            addCriterion("PAPER_USER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPaperUserCodeIsNotNull() {
            addCriterion("PAPER_USER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPaperUserCodeEqualTo(String value) {
            addCriterion("PAPER_USER_CODE =", value, "paperUserCode");
            return (Criteria) this;
        }

        public Criteria andPaperUserCodeNotEqualTo(String value) {
            addCriterion("PAPER_USER_CODE <>", value, "paperUserCode");
            return (Criteria) this;
        }

        public Criteria andPaperUserCodeGreaterThan(String value) {
            addCriterion("PAPER_USER_CODE >", value, "paperUserCode");
            return (Criteria) this;
        }

        public Criteria andPaperUserCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_USER_CODE >=", value, "paperUserCode");
            return (Criteria) this;
        }

        public Criteria andPaperUserCodeLessThan(String value) {
            addCriterion("PAPER_USER_CODE <", value, "paperUserCode");
            return (Criteria) this;
        }

        public Criteria andPaperUserCodeLessThanOrEqualTo(String value) {
            addCriterion("PAPER_USER_CODE <=", value, "paperUserCode");
            return (Criteria) this;
        }

        public Criteria andPaperUserCodeLike(String value) {
            addCriterion("PAPER_USER_CODE like", value, "paperUserCode");
            return (Criteria) this;
        }

        public Criteria andPaperUserCodeNotLike(String value) {
            addCriterion("PAPER_USER_CODE not like", value, "paperUserCode");
            return (Criteria) this;
        }

        public Criteria andPaperUserCodeIn(List<String> values) {
            addCriterion("PAPER_USER_CODE in", values, "paperUserCode");
            return (Criteria) this;
        }

        public Criteria andPaperUserCodeNotIn(List<String> values) {
            addCriterion("PAPER_USER_CODE not in", values, "paperUserCode");
            return (Criteria) this;
        }

        public Criteria andPaperUserCodeBetween(String value1, String value2) {
            addCriterion("PAPER_USER_CODE between", value1, value2, "paperUserCode");
            return (Criteria) this;
        }

        public Criteria andPaperUserCodeNotBetween(String value1, String value2) {
            addCriterion("PAPER_USER_CODE not between", value1, value2, "paperUserCode");
            return (Criteria) this;
        }

        public Criteria andPaperUserNameIsNull() {
            addCriterion("PAPER_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPaperUserNameIsNotNull() {
            addCriterion("PAPER_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPaperUserNameEqualTo(String value) {
            addCriterion("PAPER_USER_NAME =", value, "paperUserName");
            return (Criteria) this;
        }

        public Criteria andPaperUserNameNotEqualTo(String value) {
            addCriterion("PAPER_USER_NAME <>", value, "paperUserName");
            return (Criteria) this;
        }

        public Criteria andPaperUserNameGreaterThan(String value) {
            addCriterion("PAPER_USER_NAME >", value, "paperUserName");
            return (Criteria) this;
        }

        public Criteria andPaperUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_USER_NAME >=", value, "paperUserName");
            return (Criteria) this;
        }

        public Criteria andPaperUserNameLessThan(String value) {
            addCriterion("PAPER_USER_NAME <", value, "paperUserName");
            return (Criteria) this;
        }

        public Criteria andPaperUserNameLessThanOrEqualTo(String value) {
            addCriterion("PAPER_USER_NAME <=", value, "paperUserName");
            return (Criteria) this;
        }

        public Criteria andPaperUserNameLike(String value) {
            addCriterion("PAPER_USER_NAME like", value, "paperUserName");
            return (Criteria) this;
        }

        public Criteria andPaperUserNameNotLike(String value) {
            addCriterion("PAPER_USER_NAME not like", value, "paperUserName");
            return (Criteria) this;
        }

        public Criteria andPaperUserNameIn(List<String> values) {
            addCriterion("PAPER_USER_NAME in", values, "paperUserName");
            return (Criteria) this;
        }

        public Criteria andPaperUserNameNotIn(List<String> values) {
            addCriterion("PAPER_USER_NAME not in", values, "paperUserName");
            return (Criteria) this;
        }

        public Criteria andPaperUserNameBetween(String value1, String value2) {
            addCriterion("PAPER_USER_NAME between", value1, value2, "paperUserName");
            return (Criteria) this;
        }

        public Criteria andPaperUserNameNotBetween(String value1, String value2) {
            addCriterion("PAPER_USER_NAME not between", value1, value2, "paperUserName");
            return (Criteria) this;
        }

        public Criteria andPaperTimeIsNull() {
            addCriterion("PAPER_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPaperTimeIsNotNull() {
            addCriterion("PAPER_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPaperTimeEqualTo(String value) {
            addCriterion("PAPER_TIME =", value, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeNotEqualTo(String value) {
            addCriterion("PAPER_TIME <>", value, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeGreaterThan(String value) {
            addCriterion("PAPER_TIME >", value, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_TIME >=", value, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeLessThan(String value) {
            addCriterion("PAPER_TIME <", value, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeLessThanOrEqualTo(String value) {
            addCriterion("PAPER_TIME <=", value, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeLike(String value) {
            addCriterion("PAPER_TIME like", value, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeNotLike(String value) {
            addCriterion("PAPER_TIME not like", value, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeIn(List<String> values) {
            addCriterion("PAPER_TIME in", values, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeNotIn(List<String> values) {
            addCriterion("PAPER_TIME not in", values, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeBetween(String value1, String value2) {
            addCriterion("PAPER_TIME between", value1, value2, "paperTime");
            return (Criteria) this;
        }

        public Criteria andPaperTimeNotBetween(String value1, String value2) {
            addCriterion("PAPER_TIME not between", value1, value2, "paperTime");
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

    public static class SysSmsLog extends TeachingActivitySpeakerExample.MybatisObject {
        private String smsLogFlow;

        private String smsUserName;

        private String smsSendTime;

        private String smsMobile;

        private String smsContent;

        private BigDecimal smsReceiverCount;

        private String smsTempFlow;

        private BigDecimal tempId;

        private String statusCode;

        private String statusName;

        private String smsResponseMsg;

        private String relId;

        private String relType;

        private String remark;

        private String recordStatus;

        private String createTime;

        private String createUserFlow;

        private String modifyTime;

        private String modifyUserFlow;

        public String getSmsLogFlow() {
            return smsLogFlow;
        }

        public void setSmsLogFlow(String smsLogFlow) {
            this.smsLogFlow = smsLogFlow;
        }

        public String getSmsUserName() {
            return smsUserName;
        }

        public void setSmsUserName(String smsUserName) {
            this.smsUserName = smsUserName;
        }

        public String getSmsSendTime() {
            return smsSendTime;
        }

        public void setSmsSendTime(String smsSendTime) {
            this.smsSendTime = smsSendTime;
        }

        public String getSmsMobile() {
            return smsMobile;
        }

        public void setSmsMobile(String smsMobile) {
            this.smsMobile = smsMobile;
        }

        public String getSmsContent() {
            return smsContent;
        }

        public void setSmsContent(String smsContent) {
            this.smsContent = smsContent;
        }

        public BigDecimal getSmsReceiverCount() {
            return smsReceiverCount;
        }

        public void setSmsReceiverCount(BigDecimal smsReceiverCount) {
            this.smsReceiverCount = smsReceiverCount;
        }

        public String getSmsTempFlow() {
            return smsTempFlow;
        }

        public void setSmsTempFlow(String smsTempFlow) {
            this.smsTempFlow = smsTempFlow;
        }

        public BigDecimal getTempId() {
            return tempId;
        }

        public void setTempId(BigDecimal tempId) {
            this.tempId = tempId;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public String getSmsResponseMsg() {
            return smsResponseMsg;
        }

        public void setSmsResponseMsg(String smsResponseMsg) {
            this.smsResponseMsg = smsResponseMsg;
        }

        public String getRelId() {
            return relId;
        }

        public void setRelId(String relId) {
            this.relId = relId;
        }

        public String getRelType() {
            return relType;
        }

        public void setRelType(String relType) {
            this.relType = relType;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRecordStatus() {
            return recordStatus;
        }

        public void setRecordStatus(String recordStatus) {
            this.recordStatus = recordStatus;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateUserFlow() {
            return createUserFlow;
        }

        public void setCreateUserFlow(String createUserFlow) {
            this.createUserFlow = createUserFlow;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getModifyUserFlow() {
            return modifyUserFlow;
        }

        public void setModifyUserFlow(String modifyUserFlow) {
            this.modifyUserFlow = modifyUserFlow;
        }
    }
}