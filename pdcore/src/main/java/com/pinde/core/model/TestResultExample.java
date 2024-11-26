package com.pinde.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestResultExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TestResultExample() {
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

        public Criteria andResultFlowIsNull() {
            addCriterion("RESULT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andResultFlowIsNotNull() {
            addCriterion("RESULT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andResultFlowEqualTo(String value) {
            addCriterion("RESULT_FLOW =", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowNotEqualTo(String value) {
            addCriterion("RESULT_FLOW <>", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowGreaterThan(String value) {
            addCriterion("RESULT_FLOW >", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RESULT_FLOW >=", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowLessThan(String value) {
            addCriterion("RESULT_FLOW <", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowLessThanOrEqualTo(String value) {
            addCriterion("RESULT_FLOW <=", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowLike(String value) {
            addCriterion("RESULT_FLOW like", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowNotLike(String value) {
            addCriterion("RESULT_FLOW not like", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowIn(List<String> values) {
            addCriterion("RESULT_FLOW in", values, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowNotIn(List<String> values) {
            addCriterion("RESULT_FLOW not in", values, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowBetween(String value1, String value2) {
            addCriterion("RESULT_FLOW between", value1, value2, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowNotBetween(String value1, String value2) {
            addCriterion("RESULT_FLOW not between", value1, value2, "resultFlow");
            return (Criteria) this;
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

        public Criteria andUserCodeIsNull() {
            addCriterion("USER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andUserCodeIsNotNull() {
            addCriterion("USER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andUserCodeEqualTo(String value) {
            addCriterion("USER_CODE =", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotEqualTo(String value) {
            addCriterion("USER_CODE <>", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeGreaterThan(String value) {
            addCriterion("USER_CODE >", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeGreaterThanOrEqualTo(String value) {
            addCriterion("USER_CODE >=", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeLessThan(String value) {
            addCriterion("USER_CODE <", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeLessThanOrEqualTo(String value) {
            addCriterion("USER_CODE <=", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeLike(String value) {
            addCriterion("USER_CODE like", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotLike(String value) {
            addCriterion("USER_CODE not like", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeIn(List<String> values) {
            addCriterion("USER_CODE in", values, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotIn(List<String> values) {
            addCriterion("USER_CODE not in", values, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeBetween(String value1, String value2) {
            addCriterion("USER_CODE between", value1, value2, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotBetween(String value1, String value2) {
            addCriterion("USER_CODE not between", value1, value2, "userCode");
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

        public Criteria andTestScoreIsNull() {
            addCriterion("TEST_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andTestScoreIsNotNull() {
            addCriterion("TEST_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andTestScoreEqualTo(BigDecimal value) {
            addCriterion("TEST_SCORE =", value, "testScore");
            return (Criteria) this;
        }

        public Criteria andTestScoreNotEqualTo(BigDecimal value) {
            addCriterion("TEST_SCORE <>", value, "testScore");
            return (Criteria) this;
        }

        public Criteria andTestScoreGreaterThan(BigDecimal value) {
            addCriterion("TEST_SCORE >", value, "testScore");
            return (Criteria) this;
        }

        public Criteria andTestScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TEST_SCORE >=", value, "testScore");
            return (Criteria) this;
        }

        public Criteria andTestScoreLessThan(BigDecimal value) {
            addCriterion("TEST_SCORE <", value, "testScore");
            return (Criteria) this;
        }

        public Criteria andTestScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TEST_SCORE <=", value, "testScore");
            return (Criteria) this;
        }

        public Criteria andTestScoreIn(List<BigDecimal> values) {
            addCriterion("TEST_SCORE in", values, "testScore");
            return (Criteria) this;
        }

        public Criteria andTestScoreNotIn(List<BigDecimal> values) {
            addCriterion("TEST_SCORE not in", values, "testScore");
            return (Criteria) this;
        }

        public Criteria andTestScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TEST_SCORE between", value1, value2, "testScore");
            return (Criteria) this;
        }

        public Criteria andTestScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TEST_SCORE not between", value1, value2, "testScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreIsNull() {
            addCriterion("ADD_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andAddScoreIsNotNull() {
            addCriterion("ADD_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andAddScoreEqualTo(BigDecimal value) {
            addCriterion("ADD_SCORE =", value, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreNotEqualTo(BigDecimal value) {
            addCriterion("ADD_SCORE <>", value, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreGreaterThan(BigDecimal value) {
            addCriterion("ADD_SCORE >", value, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ADD_SCORE >=", value, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreLessThan(BigDecimal value) {
            addCriterion("ADD_SCORE <", value, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ADD_SCORE <=", value, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreIn(List<BigDecimal> values) {
            addCriterion("ADD_SCORE in", values, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreNotIn(List<BigDecimal> values) {
            addCriterion("ADD_SCORE not in", values, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ADD_SCORE between", value1, value2, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ADD_SCORE not between", value1, value2, "addScore");
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

        public Criteria andPassFlagIsNull() {
            addCriterion("PASS_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andPassFlagIsNotNull() {
            addCriterion("PASS_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andPassFlagEqualTo(String value) {
            addCriterion("PASS_FLAG =", value, "passFlag");
            return (Criteria) this;
        }

        public Criteria andPassFlagNotEqualTo(String value) {
            addCriterion("PASS_FLAG <>", value, "passFlag");
            return (Criteria) this;
        }

        public Criteria andPassFlagGreaterThan(String value) {
            addCriterion("PASS_FLAG >", value, "passFlag");
            return (Criteria) this;
        }

        public Criteria andPassFlagGreaterThanOrEqualTo(String value) {
            addCriterion("PASS_FLAG >=", value, "passFlag");
            return (Criteria) this;
        }

        public Criteria andPassFlagLessThan(String value) {
            addCriterion("PASS_FLAG <", value, "passFlag");
            return (Criteria) this;
        }

        public Criteria andPassFlagLessThanOrEqualTo(String value) {
            addCriterion("PASS_FLAG <=", value, "passFlag");
            return (Criteria) this;
        }

        public Criteria andPassFlagLike(String value) {
            addCriterion("PASS_FLAG like", value, "passFlag");
            return (Criteria) this;
        }

        public Criteria andPassFlagNotLike(String value) {
            addCriterion("PASS_FLAG not like", value, "passFlag");
            return (Criteria) this;
        }

        public Criteria andPassFlagIn(List<String> values) {
            addCriterion("PASS_FLAG in", values, "passFlag");
            return (Criteria) this;
        }

        public Criteria andPassFlagNotIn(List<String> values) {
            addCriterion("PASS_FLAG not in", values, "passFlag");
            return (Criteria) this;
        }

        public Criteria andPassFlagBetween(String value1, String value2) {
            addCriterion("PASS_FLAG between", value1, value2, "passFlag");
            return (Criteria) this;
        }

        public Criteria andPassFlagNotBetween(String value1, String value2) {
            addCriterion("PASS_FLAG not between", value1, value2, "passFlag");
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

        public Criteria andTestTypeIdIsNull() {
            addCriterion("TEST_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTestTypeIdIsNotNull() {
            addCriterion("TEST_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTestTypeIdEqualTo(String value) {
            addCriterion("TEST_TYPE_ID =", value, "testTypeId");
            return (Criteria) this;
        }

        public Criteria andTestTypeIdNotEqualTo(String value) {
            addCriterion("TEST_TYPE_ID <>", value, "testTypeId");
            return (Criteria) this;
        }

        public Criteria andTestTypeIdGreaterThan(String value) {
            addCriterion("TEST_TYPE_ID >", value, "testTypeId");
            return (Criteria) this;
        }

        public Criteria andTestTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_TYPE_ID >=", value, "testTypeId");
            return (Criteria) this;
        }

        public Criteria andTestTypeIdLessThan(String value) {
            addCriterion("TEST_TYPE_ID <", value, "testTypeId");
            return (Criteria) this;
        }

        public Criteria andTestTypeIdLessThanOrEqualTo(String value) {
            addCriterion("TEST_TYPE_ID <=", value, "testTypeId");
            return (Criteria) this;
        }

        public Criteria andTestTypeIdLike(String value) {
            addCriterion("TEST_TYPE_ID like", value, "testTypeId");
            return (Criteria) this;
        }

        public Criteria andTestTypeIdNotLike(String value) {
            addCriterion("TEST_TYPE_ID not like", value, "testTypeId");
            return (Criteria) this;
        }

        public Criteria andTestTypeIdIn(List<String> values) {
            addCriterion("TEST_TYPE_ID in", values, "testTypeId");
            return (Criteria) this;
        }

        public Criteria andTestTypeIdNotIn(List<String> values) {
            addCriterion("TEST_TYPE_ID not in", values, "testTypeId");
            return (Criteria) this;
        }

        public Criteria andTestTypeIdBetween(String value1, String value2) {
            addCriterion("TEST_TYPE_ID between", value1, value2, "testTypeId");
            return (Criteria) this;
        }

        public Criteria andTestTypeIdNotBetween(String value1, String value2) {
            addCriterion("TEST_TYPE_ID not between", value1, value2, "testTypeId");
            return (Criteria) this;
        }

        public Criteria andTestTypeNameIsNull() {
            addCriterion("TEST_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTestTypeNameIsNotNull() {
            addCriterion("TEST_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTestTypeNameEqualTo(String value) {
            addCriterion("TEST_TYPE_NAME =", value, "testTypeName");
            return (Criteria) this;
        }

        public Criteria andTestTypeNameNotEqualTo(String value) {
            addCriterion("TEST_TYPE_NAME <>", value, "testTypeName");
            return (Criteria) this;
        }

        public Criteria andTestTypeNameGreaterThan(String value) {
            addCriterion("TEST_TYPE_NAME >", value, "testTypeName");
            return (Criteria) this;
        }

        public Criteria andTestTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_TYPE_NAME >=", value, "testTypeName");
            return (Criteria) this;
        }

        public Criteria andTestTypeNameLessThan(String value) {
            addCriterion("TEST_TYPE_NAME <", value, "testTypeName");
            return (Criteria) this;
        }

        public Criteria andTestTypeNameLessThanOrEqualTo(String value) {
            addCriterion("TEST_TYPE_NAME <=", value, "testTypeName");
            return (Criteria) this;
        }

        public Criteria andTestTypeNameLike(String value) {
            addCriterion("TEST_TYPE_NAME like", value, "testTypeName");
            return (Criteria) this;
        }

        public Criteria andTestTypeNameNotLike(String value) {
            addCriterion("TEST_TYPE_NAME not like", value, "testTypeName");
            return (Criteria) this;
        }

        public Criteria andTestTypeNameIn(List<String> values) {
            addCriterion("TEST_TYPE_NAME in", values, "testTypeName");
            return (Criteria) this;
        }

        public Criteria andTestTypeNameNotIn(List<String> values) {
            addCriterion("TEST_TYPE_NAME not in", values, "testTypeName");
            return (Criteria) this;
        }

        public Criteria andTestTypeNameBetween(String value1, String value2) {
            addCriterion("TEST_TYPE_NAME between", value1, value2, "testTypeName");
            return (Criteria) this;
        }

        public Criteria andTestTypeNameNotBetween(String value1, String value2) {
            addCriterion("TEST_TYPE_NAME not between", value1, value2, "testTypeName");
            return (Criteria) this;
        }

        public Criteria andTicketNumberIsNull() {
            addCriterion("TICKET_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andTicketNumberIsNotNull() {
            addCriterion("TICKET_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andTicketNumberEqualTo(String value) {
            addCriterion("TICKET_NUMBER =", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberNotEqualTo(String value) {
            addCriterion("TICKET_NUMBER <>", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberGreaterThan(String value) {
            addCriterion("TICKET_NUMBER >", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberGreaterThanOrEqualTo(String value) {
            addCriterion("TICKET_NUMBER >=", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberLessThan(String value) {
            addCriterion("TICKET_NUMBER <", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberLessThanOrEqualTo(String value) {
            addCriterion("TICKET_NUMBER <=", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberLike(String value) {
            addCriterion("TICKET_NUMBER like", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberNotLike(String value) {
            addCriterion("TICKET_NUMBER not like", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberIn(List<String> values) {
            addCriterion("TICKET_NUMBER in", values, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberNotIn(List<String> values) {
            addCriterion("TICKET_NUMBER not in", values, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberBetween(String value1, String value2) {
            addCriterion("TICKET_NUMBER between", value1, value2, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberNotBetween(String value1, String value2) {
            addCriterion("TICKET_NUMBER not between", value1, value2, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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