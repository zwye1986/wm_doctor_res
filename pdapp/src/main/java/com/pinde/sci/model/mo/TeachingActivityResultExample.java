package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TeachingActivityResultExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TeachingActivityResultExample() {
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

        public Criteria andActivityFlowIsNull() {
            addCriterion("ACTIVITY_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andActivityFlowIsNotNull() {
            addCriterion("ACTIVITY_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andActivityFlowEqualTo(String value) {
            addCriterion("ACTIVITY_FLOW =", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowNotEqualTo(String value) {
            addCriterion("ACTIVITY_FLOW <>", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowGreaterThan(String value) {
            addCriterion("ACTIVITY_FLOW >", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_FLOW >=", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowLessThan(String value) {
            addCriterion("ACTIVITY_FLOW <", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowLessThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_FLOW <=", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowLike(String value) {
            addCriterion("ACTIVITY_FLOW like", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowNotLike(String value) {
            addCriterion("ACTIVITY_FLOW not like", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowIn(List<String> values) {
            addCriterion("ACTIVITY_FLOW in", values, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowNotIn(List<String> values) {
            addCriterion("ACTIVITY_FLOW not in", values, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowBetween(String value1, String value2) {
            addCriterion("ACTIVITY_FLOW between", value1, value2, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowNotBetween(String value1, String value2) {
            addCriterion("ACTIVITY_FLOW not between", value1, value2, "activityFlow");
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

        public Criteria andEvalScoreIsNull() {
            addCriterion("EVAL_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andEvalScoreIsNotNull() {
            addCriterion("EVAL_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andEvalScoreEqualTo(BigDecimal value) {
            addCriterion("EVAL_SCORE =", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreNotEqualTo(BigDecimal value) {
            addCriterion("EVAL_SCORE <>", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreGreaterThan(BigDecimal value) {
            addCriterion("EVAL_SCORE >", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("EVAL_SCORE >=", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreLessThan(BigDecimal value) {
            addCriterion("EVAL_SCORE <", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("EVAL_SCORE <=", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreIn(List<BigDecimal> values) {
            addCriterion("EVAL_SCORE in", values, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreNotIn(List<BigDecimal> values) {
            addCriterion("EVAL_SCORE not in", values, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("EVAL_SCORE between", value1, value2, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("EVAL_SCORE not between", value1, value2, "evalScore");
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

        public Criteria andIsRegiestIsNull() {
            addCriterion("IS_REGIEST is null");
            return (Criteria) this;
        }

        public Criteria andIsRegiestIsNotNull() {
            addCriterion("IS_REGIEST is not null");
            return (Criteria) this;
        }

        public Criteria andIsRegiestEqualTo(String value) {
            addCriterion("IS_REGIEST =", value, "isRegiest");
            return (Criteria) this;
        }

        public Criteria andIsRegiestNotEqualTo(String value) {
            addCriterion("IS_REGIEST <>", value, "isRegiest");
            return (Criteria) this;
        }

        public Criteria andIsRegiestGreaterThan(String value) {
            addCriterion("IS_REGIEST >", value, "isRegiest");
            return (Criteria) this;
        }

        public Criteria andIsRegiestGreaterThanOrEqualTo(String value) {
            addCriterion("IS_REGIEST >=", value, "isRegiest");
            return (Criteria) this;
        }

        public Criteria andIsRegiestLessThan(String value) {
            addCriterion("IS_REGIEST <", value, "isRegiest");
            return (Criteria) this;
        }

        public Criteria andIsRegiestLessThanOrEqualTo(String value) {
            addCriterion("IS_REGIEST <=", value, "isRegiest");
            return (Criteria) this;
        }

        public Criteria andIsRegiestLike(String value) {
            addCriterion("IS_REGIEST like", value, "isRegiest");
            return (Criteria) this;
        }

        public Criteria andIsRegiestNotLike(String value) {
            addCriterion("IS_REGIEST not like", value, "isRegiest");
            return (Criteria) this;
        }

        public Criteria andIsRegiestIn(List<String> values) {
            addCriterion("IS_REGIEST in", values, "isRegiest");
            return (Criteria) this;
        }

        public Criteria andIsRegiestNotIn(List<String> values) {
            addCriterion("IS_REGIEST not in", values, "isRegiest");
            return (Criteria) this;
        }

        public Criteria andIsRegiestBetween(String value1, String value2) {
            addCriterion("IS_REGIEST between", value1, value2, "isRegiest");
            return (Criteria) this;
        }

        public Criteria andIsRegiestNotBetween(String value1, String value2) {
            addCriterion("IS_REGIEST not between", value1, value2, "isRegiest");
            return (Criteria) this;
        }

        public Criteria andIsScanIsNull() {
            addCriterion("IS_SCAN is null");
            return (Criteria) this;
        }

        public Criteria andIsScanIsNotNull() {
            addCriterion("IS_SCAN is not null");
            return (Criteria) this;
        }

        public Criteria andIsScanEqualTo(String value) {
            addCriterion("IS_SCAN =", value, "isScan");
            return (Criteria) this;
        }

        public Criteria andIsScanNotEqualTo(String value) {
            addCriterion("IS_SCAN <>", value, "isScan");
            return (Criteria) this;
        }

        public Criteria andIsScanGreaterThan(String value) {
            addCriterion("IS_SCAN >", value, "isScan");
            return (Criteria) this;
        }

        public Criteria andIsScanGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SCAN >=", value, "isScan");
            return (Criteria) this;
        }

        public Criteria andIsScanLessThan(String value) {
            addCriterion("IS_SCAN <", value, "isScan");
            return (Criteria) this;
        }

        public Criteria andIsScanLessThanOrEqualTo(String value) {
            addCriterion("IS_SCAN <=", value, "isScan");
            return (Criteria) this;
        }

        public Criteria andIsScanLike(String value) {
            addCriterion("IS_SCAN like", value, "isScan");
            return (Criteria) this;
        }

        public Criteria andIsScanNotLike(String value) {
            addCriterion("IS_SCAN not like", value, "isScan");
            return (Criteria) this;
        }

        public Criteria andIsScanIn(List<String> values) {
            addCriterion("IS_SCAN in", values, "isScan");
            return (Criteria) this;
        }

        public Criteria andIsScanNotIn(List<String> values) {
            addCriterion("IS_SCAN not in", values, "isScan");
            return (Criteria) this;
        }

        public Criteria andIsScanBetween(String value1, String value2) {
            addCriterion("IS_SCAN between", value1, value2, "isScan");
            return (Criteria) this;
        }

        public Criteria andIsScanNotBetween(String value1, String value2) {
            addCriterion("IS_SCAN not between", value1, value2, "isScan");
            return (Criteria) this;
        }

        public Criteria andRegiestTimeIsNull() {
            addCriterion("REGIEST_TIME is null");
            return (Criteria) this;
        }

        public Criteria andRegiestTimeIsNotNull() {
            addCriterion("REGIEST_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andRegiestTimeEqualTo(String value) {
            addCriterion("REGIEST_TIME =", value, "regiestTime");
            return (Criteria) this;
        }

        public Criteria andRegiestTimeNotEqualTo(String value) {
            addCriterion("REGIEST_TIME <>", value, "regiestTime");
            return (Criteria) this;
        }

        public Criteria andRegiestTimeGreaterThan(String value) {
            addCriterion("REGIEST_TIME >", value, "regiestTime");
            return (Criteria) this;
        }

        public Criteria andRegiestTimeGreaterThanOrEqualTo(String value) {
            addCriterion("REGIEST_TIME >=", value, "regiestTime");
            return (Criteria) this;
        }

        public Criteria andRegiestTimeLessThan(String value) {
            addCriterion("REGIEST_TIME <", value, "regiestTime");
            return (Criteria) this;
        }

        public Criteria andRegiestTimeLessThanOrEqualTo(String value) {
            addCriterion("REGIEST_TIME <=", value, "regiestTime");
            return (Criteria) this;
        }

        public Criteria andRegiestTimeLike(String value) {
            addCriterion("REGIEST_TIME like", value, "regiestTime");
            return (Criteria) this;
        }

        public Criteria andRegiestTimeNotLike(String value) {
            addCriterion("REGIEST_TIME not like", value, "regiestTime");
            return (Criteria) this;
        }

        public Criteria andRegiestTimeIn(List<String> values) {
            addCriterion("REGIEST_TIME in", values, "regiestTime");
            return (Criteria) this;
        }

        public Criteria andRegiestTimeNotIn(List<String> values) {
            addCriterion("REGIEST_TIME not in", values, "regiestTime");
            return (Criteria) this;
        }

        public Criteria andRegiestTimeBetween(String value1, String value2) {
            addCriterion("REGIEST_TIME between", value1, value2, "regiestTime");
            return (Criteria) this;
        }

        public Criteria andRegiestTimeNotBetween(String value1, String value2) {
            addCriterion("REGIEST_TIME not between", value1, value2, "regiestTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeIsNull() {
            addCriterion("SCAN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andScanTimeIsNotNull() {
            addCriterion("SCAN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andScanTimeEqualTo(String value) {
            addCriterion("SCAN_TIME =", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeNotEqualTo(String value) {
            addCriterion("SCAN_TIME <>", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeGreaterThan(String value) {
            addCriterion("SCAN_TIME >", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SCAN_TIME >=", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeLessThan(String value) {
            addCriterion("SCAN_TIME <", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeLessThanOrEqualTo(String value) {
            addCriterion("SCAN_TIME <=", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeLike(String value) {
            addCriterion("SCAN_TIME like", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeNotLike(String value) {
            addCriterion("SCAN_TIME not like", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeIn(List<String> values) {
            addCriterion("SCAN_TIME in", values, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeNotIn(List<String> values) {
            addCriterion("SCAN_TIME not in", values, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeBetween(String value1, String value2) {
            addCriterion("SCAN_TIME between", value1, value2, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeNotBetween(String value1, String value2) {
            addCriterion("SCAN_TIME not between", value1, value2, "scanTime");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveIsNull() {
            addCriterion("IS_EFFECTIVE is null");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveIsNotNull() {
            addCriterion("IS_EFFECTIVE is not null");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveEqualTo(String value) {
            addCriterion("IS_EFFECTIVE =", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveNotEqualTo(String value) {
            addCriterion("IS_EFFECTIVE <>", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveGreaterThan(String value) {
            addCriterion("IS_EFFECTIVE >", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveGreaterThanOrEqualTo(String value) {
            addCriterion("IS_EFFECTIVE >=", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveLessThan(String value) {
            addCriterion("IS_EFFECTIVE <", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveLessThanOrEqualTo(String value) {
            addCriterion("IS_EFFECTIVE <=", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveLike(String value) {
            addCriterion("IS_EFFECTIVE like", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveNotLike(String value) {
            addCriterion("IS_EFFECTIVE not like", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveIn(List<String> values) {
            addCriterion("IS_EFFECTIVE in", values, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveNotIn(List<String> values) {
            addCriterion("IS_EFFECTIVE not in", values, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveBetween(String value1, String value2) {
            addCriterion("IS_EFFECTIVE between", value1, value2, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveNotBetween(String value1, String value2) {
            addCriterion("IS_EFFECTIVE not between", value1, value2, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsScan2IsNull() {
            addCriterion("IS_SCAN2 is null");
            return (Criteria) this;
        }

        public Criteria andIsScan2IsNotNull() {
            addCriterion("IS_SCAN2 is not null");
            return (Criteria) this;
        }

        public Criteria andIsScan2EqualTo(String value) {
            addCriterion("IS_SCAN2 =", value, "isScan2");
            return (Criteria) this;
        }

        public Criteria andIsScan2NotEqualTo(String value) {
            addCriterion("IS_SCAN2 <>", value, "isScan2");
            return (Criteria) this;
        }

        public Criteria andIsScan2GreaterThan(String value) {
            addCriterion("IS_SCAN2 >", value, "isScan2");
            return (Criteria) this;
        }

        public Criteria andIsScan2GreaterThanOrEqualTo(String value) {
            addCriterion("IS_SCAN2 >=", value, "isScan2");
            return (Criteria) this;
        }

        public Criteria andIsScan2LessThan(String value) {
            addCriterion("IS_SCAN2 <", value, "isScan2");
            return (Criteria) this;
        }

        public Criteria andIsScan2LessThanOrEqualTo(String value) {
            addCriterion("IS_SCAN2 <=", value, "isScan2");
            return (Criteria) this;
        }

        public Criteria andIsScan2Like(String value) {
            addCriterion("IS_SCAN2 like", value, "isScan2");
            return (Criteria) this;
        }

        public Criteria andIsScan2NotLike(String value) {
            addCriterion("IS_SCAN2 not like", value, "isScan2");
            return (Criteria) this;
        }

        public Criteria andIsScan2In(List<String> values) {
            addCriterion("IS_SCAN2 in", values, "isScan2");
            return (Criteria) this;
        }

        public Criteria andIsScan2NotIn(List<String> values) {
            addCriterion("IS_SCAN2 not in", values, "isScan2");
            return (Criteria) this;
        }

        public Criteria andIsScan2Between(String value1, String value2) {
            addCriterion("IS_SCAN2 between", value1, value2, "isScan2");
            return (Criteria) this;
        }

        public Criteria andIsScan2NotBetween(String value1, String value2) {
            addCriterion("IS_SCAN2 not between", value1, value2, "isScan2");
            return (Criteria) this;
        }

        public Criteria andScanTime2IsNull() {
            addCriterion("SCAN_TIME2 is null");
            return (Criteria) this;
        }

        public Criteria andScanTime2IsNotNull() {
            addCriterion("SCAN_TIME2 is not null");
            return (Criteria) this;
        }

        public Criteria andScanTime2EqualTo(String value) {
            addCriterion("SCAN_TIME2 =", value, "scanTime2");
            return (Criteria) this;
        }

        public Criteria andScanTime2NotEqualTo(String value) {
            addCriterion("SCAN_TIME2 <>", value, "scanTime2");
            return (Criteria) this;
        }

        public Criteria andScanTime2GreaterThan(String value) {
            addCriterion("SCAN_TIME2 >", value, "scanTime2");
            return (Criteria) this;
        }

        public Criteria andScanTime2GreaterThanOrEqualTo(String value) {
            addCriterion("SCAN_TIME2 >=", value, "scanTime2");
            return (Criteria) this;
        }

        public Criteria andScanTime2LessThan(String value) {
            addCriterion("SCAN_TIME2 <", value, "scanTime2");
            return (Criteria) this;
        }

        public Criteria andScanTime2LessThanOrEqualTo(String value) {
            addCriterion("SCAN_TIME2 <=", value, "scanTime2");
            return (Criteria) this;
        }

        public Criteria andScanTime2Like(String value) {
            addCriterion("SCAN_TIME2 like", value, "scanTime2");
            return (Criteria) this;
        }

        public Criteria andScanTime2NotLike(String value) {
            addCriterion("SCAN_TIME2 not like", value, "scanTime2");
            return (Criteria) this;
        }

        public Criteria andScanTime2In(List<String> values) {
            addCriterion("SCAN_TIME2 in", values, "scanTime2");
            return (Criteria) this;
        }

        public Criteria andScanTime2NotIn(List<String> values) {
            addCriterion("SCAN_TIME2 not in", values, "scanTime2");
            return (Criteria) this;
        }

        public Criteria andScanTime2Between(String value1, String value2) {
            addCriterion("SCAN_TIME2 between", value1, value2, "scanTime2");
            return (Criteria) this;
        }

        public Criteria andScanTime2NotBetween(String value1, String value2) {
            addCriterion("SCAN_TIME2 not between", value1, value2, "scanTime2");
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