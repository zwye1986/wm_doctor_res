package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SrmAchScoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmAchScoreExample() {
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

        public Criteria andScoreFlowIsNull() {
            addCriterion("SCORE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andScoreFlowIsNotNull() {
            addCriterion("SCORE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andScoreFlowEqualTo(String value) {
            addCriterion("SCORE_FLOW =", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowNotEqualTo(String value) {
            addCriterion("SCORE_FLOW <>", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowGreaterThan(String value) {
            addCriterion("SCORE_FLOW >", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_FLOW >=", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowLessThan(String value) {
            addCriterion("SCORE_FLOW <", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowLessThanOrEqualTo(String value) {
            addCriterion("SCORE_FLOW <=", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowLike(String value) {
            addCriterion("SCORE_FLOW like", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowNotLike(String value) {
            addCriterion("SCORE_FLOW not like", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowIn(List<String> values) {
            addCriterion("SCORE_FLOW in", values, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowNotIn(List<String> values) {
            addCriterion("SCORE_FLOW not in", values, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowBetween(String value1, String value2) {
            addCriterion("SCORE_FLOW between", value1, value2, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowNotBetween(String value1, String value2) {
            addCriterion("SCORE_FLOW not between", value1, value2, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreNameIsNull() {
            addCriterion("SCORE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andScoreNameIsNotNull() {
            addCriterion("SCORE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andScoreNameEqualTo(String value) {
            addCriterion("SCORE_NAME =", value, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameNotEqualTo(String value) {
            addCriterion("SCORE_NAME <>", value, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameGreaterThan(String value) {
            addCriterion("SCORE_NAME >", value, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_NAME >=", value, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameLessThan(String value) {
            addCriterion("SCORE_NAME <", value, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameLessThanOrEqualTo(String value) {
            addCriterion("SCORE_NAME <=", value, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameLike(String value) {
            addCriterion("SCORE_NAME like", value, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameNotLike(String value) {
            addCriterion("SCORE_NAME not like", value, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameIn(List<String> values) {
            addCriterion("SCORE_NAME in", values, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameNotIn(List<String> values) {
            addCriterion("SCORE_NAME not in", values, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameBetween(String value1, String value2) {
            addCriterion("SCORE_NAME between", value1, value2, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreNameNotBetween(String value1, String value2) {
            addCriterion("SCORE_NAME not between", value1, value2, "scoreName");
            return (Criteria) this;
        }

        public Criteria andScoreDeptValueIsNull() {
            addCriterion("SCORE_DEPT_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andScoreDeptValueIsNotNull() {
            addCriterion("SCORE_DEPT_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andScoreDeptValueEqualTo(BigDecimal value) {
            addCriterion("SCORE_DEPT_VALUE =", value, "scoreDeptValue");
            return (Criteria) this;
        }

        public Criteria andScoreDeptValueNotEqualTo(BigDecimal value) {
            addCriterion("SCORE_DEPT_VALUE <>", value, "scoreDeptValue");
            return (Criteria) this;
        }

        public Criteria andScoreDeptValueGreaterThan(BigDecimal value) {
            addCriterion("SCORE_DEPT_VALUE >", value, "scoreDeptValue");
            return (Criteria) this;
        }

        public Criteria andScoreDeptValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SCORE_DEPT_VALUE >=", value, "scoreDeptValue");
            return (Criteria) this;
        }

        public Criteria andScoreDeptValueLessThan(BigDecimal value) {
            addCriterion("SCORE_DEPT_VALUE <", value, "scoreDeptValue");
            return (Criteria) this;
        }

        public Criteria andScoreDeptValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SCORE_DEPT_VALUE <=", value, "scoreDeptValue");
            return (Criteria) this;
        }

        public Criteria andScoreDeptValueIn(List<BigDecimal> values) {
            addCriterion("SCORE_DEPT_VALUE in", values, "scoreDeptValue");
            return (Criteria) this;
        }

        public Criteria andScoreDeptValueNotIn(List<BigDecimal> values) {
            addCriterion("SCORE_DEPT_VALUE not in", values, "scoreDeptValue");
            return (Criteria) this;
        }

        public Criteria andScoreDeptValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SCORE_DEPT_VALUE between", value1, value2, "scoreDeptValue");
            return (Criteria) this;
        }

        public Criteria andScoreDeptValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SCORE_DEPT_VALUE not between", value1, value2, "scoreDeptValue");
            return (Criteria) this;
        }

        public Criteria andScorePersonalValueIsNull() {
            addCriterion("SCORE_PERSONAL_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andScorePersonalValueIsNotNull() {
            addCriterion("SCORE_PERSONAL_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andScorePersonalValueEqualTo(BigDecimal value) {
            addCriterion("SCORE_PERSONAL_VALUE =", value, "scorePersonalValue");
            return (Criteria) this;
        }

        public Criteria andScorePersonalValueNotEqualTo(BigDecimal value) {
            addCriterion("SCORE_PERSONAL_VALUE <>", value, "scorePersonalValue");
            return (Criteria) this;
        }

        public Criteria andScorePersonalValueGreaterThan(BigDecimal value) {
            addCriterion("SCORE_PERSONAL_VALUE >", value, "scorePersonalValue");
            return (Criteria) this;
        }

        public Criteria andScorePersonalValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SCORE_PERSONAL_VALUE >=", value, "scorePersonalValue");
            return (Criteria) this;
        }

        public Criteria andScorePersonalValueLessThan(BigDecimal value) {
            addCriterion("SCORE_PERSONAL_VALUE <", value, "scorePersonalValue");
            return (Criteria) this;
        }

        public Criteria andScorePersonalValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SCORE_PERSONAL_VALUE <=", value, "scorePersonalValue");
            return (Criteria) this;
        }

        public Criteria andScorePersonalValueIn(List<BigDecimal> values) {
            addCriterion("SCORE_PERSONAL_VALUE in", values, "scorePersonalValue");
            return (Criteria) this;
        }

        public Criteria andScorePersonalValueNotIn(List<BigDecimal> values) {
            addCriterion("SCORE_PERSONAL_VALUE not in", values, "scorePersonalValue");
            return (Criteria) this;
        }

        public Criteria andScorePersonalValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SCORE_PERSONAL_VALUE between", value1, value2, "scorePersonalValue");
            return (Criteria) this;
        }

        public Criteria andScorePersonalValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SCORE_PERSONAL_VALUE not between", value1, value2, "scorePersonalValue");
            return (Criteria) this;
        }

        public Criteria andScoreRemarkIsNull() {
            addCriterion("SCORE_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andScoreRemarkIsNotNull() {
            addCriterion("SCORE_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andScoreRemarkEqualTo(String value) {
            addCriterion("SCORE_REMARK =", value, "scoreRemark");
            return (Criteria) this;
        }

        public Criteria andScoreRemarkNotEqualTo(String value) {
            addCriterion("SCORE_REMARK <>", value, "scoreRemark");
            return (Criteria) this;
        }

        public Criteria andScoreRemarkGreaterThan(String value) {
            addCriterion("SCORE_REMARK >", value, "scoreRemark");
            return (Criteria) this;
        }

        public Criteria andScoreRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_REMARK >=", value, "scoreRemark");
            return (Criteria) this;
        }

        public Criteria andScoreRemarkLessThan(String value) {
            addCriterion("SCORE_REMARK <", value, "scoreRemark");
            return (Criteria) this;
        }

        public Criteria andScoreRemarkLessThanOrEqualTo(String value) {
            addCriterion("SCORE_REMARK <=", value, "scoreRemark");
            return (Criteria) this;
        }

        public Criteria andScoreRemarkLike(String value) {
            addCriterion("SCORE_REMARK like", value, "scoreRemark");
            return (Criteria) this;
        }

        public Criteria andScoreRemarkNotLike(String value) {
            addCriterion("SCORE_REMARK not like", value, "scoreRemark");
            return (Criteria) this;
        }

        public Criteria andScoreRemarkIn(List<String> values) {
            addCriterion("SCORE_REMARK in", values, "scoreRemark");
            return (Criteria) this;
        }

        public Criteria andScoreRemarkNotIn(List<String> values) {
            addCriterion("SCORE_REMARK not in", values, "scoreRemark");
            return (Criteria) this;
        }

        public Criteria andScoreRemarkBetween(String value1, String value2) {
            addCriterion("SCORE_REMARK between", value1, value2, "scoreRemark");
            return (Criteria) this;
        }

        public Criteria andScoreRemarkNotBetween(String value1, String value2) {
            addCriterion("SCORE_REMARK not between", value1, value2, "scoreRemark");
            return (Criteria) this;
        }

        public Criteria andScoreStatusIdIsNull() {
            addCriterion("SCORE_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andScoreStatusIdIsNotNull() {
            addCriterion("SCORE_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andScoreStatusIdEqualTo(String value) {
            addCriterion("SCORE_STATUS_ID =", value, "scoreStatusId");
            return (Criteria) this;
        }

        public Criteria andScoreStatusIdNotEqualTo(String value) {
            addCriterion("SCORE_STATUS_ID <>", value, "scoreStatusId");
            return (Criteria) this;
        }

        public Criteria andScoreStatusIdGreaterThan(String value) {
            addCriterion("SCORE_STATUS_ID >", value, "scoreStatusId");
            return (Criteria) this;
        }

        public Criteria andScoreStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_STATUS_ID >=", value, "scoreStatusId");
            return (Criteria) this;
        }

        public Criteria andScoreStatusIdLessThan(String value) {
            addCriterion("SCORE_STATUS_ID <", value, "scoreStatusId");
            return (Criteria) this;
        }

        public Criteria andScoreStatusIdLessThanOrEqualTo(String value) {
            addCriterion("SCORE_STATUS_ID <=", value, "scoreStatusId");
            return (Criteria) this;
        }

        public Criteria andScoreStatusIdLike(String value) {
            addCriterion("SCORE_STATUS_ID like", value, "scoreStatusId");
            return (Criteria) this;
        }

        public Criteria andScoreStatusIdNotLike(String value) {
            addCriterion("SCORE_STATUS_ID not like", value, "scoreStatusId");
            return (Criteria) this;
        }

        public Criteria andScoreStatusIdIn(List<String> values) {
            addCriterion("SCORE_STATUS_ID in", values, "scoreStatusId");
            return (Criteria) this;
        }

        public Criteria andScoreStatusIdNotIn(List<String> values) {
            addCriterion("SCORE_STATUS_ID not in", values, "scoreStatusId");
            return (Criteria) this;
        }

        public Criteria andScoreStatusIdBetween(String value1, String value2) {
            addCriterion("SCORE_STATUS_ID between", value1, value2, "scoreStatusId");
            return (Criteria) this;
        }

        public Criteria andScoreStatusIdNotBetween(String value1, String value2) {
            addCriterion("SCORE_STATUS_ID not between", value1, value2, "scoreStatusId");
            return (Criteria) this;
        }

        public Criteria andScoreStatusNameIsNull() {
            addCriterion("SCORE_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andScoreStatusNameIsNotNull() {
            addCriterion("SCORE_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andScoreStatusNameEqualTo(String value) {
            addCriterion("SCORE_STATUS_NAME =", value, "scoreStatusName");
            return (Criteria) this;
        }

        public Criteria andScoreStatusNameNotEqualTo(String value) {
            addCriterion("SCORE_STATUS_NAME <>", value, "scoreStatusName");
            return (Criteria) this;
        }

        public Criteria andScoreStatusNameGreaterThan(String value) {
            addCriterion("SCORE_STATUS_NAME >", value, "scoreStatusName");
            return (Criteria) this;
        }

        public Criteria andScoreStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_STATUS_NAME >=", value, "scoreStatusName");
            return (Criteria) this;
        }

        public Criteria andScoreStatusNameLessThan(String value) {
            addCriterion("SCORE_STATUS_NAME <", value, "scoreStatusName");
            return (Criteria) this;
        }

        public Criteria andScoreStatusNameLessThanOrEqualTo(String value) {
            addCriterion("SCORE_STATUS_NAME <=", value, "scoreStatusName");
            return (Criteria) this;
        }

        public Criteria andScoreStatusNameLike(String value) {
            addCriterion("SCORE_STATUS_NAME like", value, "scoreStatusName");
            return (Criteria) this;
        }

        public Criteria andScoreStatusNameNotLike(String value) {
            addCriterion("SCORE_STATUS_NAME not like", value, "scoreStatusName");
            return (Criteria) this;
        }

        public Criteria andScoreStatusNameIn(List<String> values) {
            addCriterion("SCORE_STATUS_NAME in", values, "scoreStatusName");
            return (Criteria) this;
        }

        public Criteria andScoreStatusNameNotIn(List<String> values) {
            addCriterion("SCORE_STATUS_NAME not in", values, "scoreStatusName");
            return (Criteria) this;
        }

        public Criteria andScoreStatusNameBetween(String value1, String value2) {
            addCriterion("SCORE_STATUS_NAME between", value1, value2, "scoreStatusName");
            return (Criteria) this;
        }

        public Criteria andScoreStatusNameNotBetween(String value1, String value2) {
            addCriterion("SCORE_STATUS_NAME not between", value1, value2, "scoreStatusName");
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

        public Criteria andParentScoreFlowIsNull() {
            addCriterion("PARENT_SCORE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andParentScoreFlowIsNotNull() {
            addCriterion("PARENT_SCORE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andParentScoreFlowEqualTo(String value) {
            addCriterion("PARENT_SCORE_FLOW =", value, "parentScoreFlow");
            return (Criteria) this;
        }

        public Criteria andParentScoreFlowNotEqualTo(String value) {
            addCriterion("PARENT_SCORE_FLOW <>", value, "parentScoreFlow");
            return (Criteria) this;
        }

        public Criteria andParentScoreFlowGreaterThan(String value) {
            addCriterion("PARENT_SCORE_FLOW >", value, "parentScoreFlow");
            return (Criteria) this;
        }

        public Criteria andParentScoreFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_SCORE_FLOW >=", value, "parentScoreFlow");
            return (Criteria) this;
        }

        public Criteria andParentScoreFlowLessThan(String value) {
            addCriterion("PARENT_SCORE_FLOW <", value, "parentScoreFlow");
            return (Criteria) this;
        }

        public Criteria andParentScoreFlowLessThanOrEqualTo(String value) {
            addCriterion("PARENT_SCORE_FLOW <=", value, "parentScoreFlow");
            return (Criteria) this;
        }

        public Criteria andParentScoreFlowLike(String value) {
            addCriterion("PARENT_SCORE_FLOW like", value, "parentScoreFlow");
            return (Criteria) this;
        }

        public Criteria andParentScoreFlowNotLike(String value) {
            addCriterion("PARENT_SCORE_FLOW not like", value, "parentScoreFlow");
            return (Criteria) this;
        }

        public Criteria andParentScoreFlowIn(List<String> values) {
            addCriterion("PARENT_SCORE_FLOW in", values, "parentScoreFlow");
            return (Criteria) this;
        }

        public Criteria andParentScoreFlowNotIn(List<String> values) {
            addCriterion("PARENT_SCORE_FLOW not in", values, "parentScoreFlow");
            return (Criteria) this;
        }

        public Criteria andParentScoreFlowBetween(String value1, String value2) {
            addCriterion("PARENT_SCORE_FLOW between", value1, value2, "parentScoreFlow");
            return (Criteria) this;
        }

        public Criteria andParentScoreFlowNotBetween(String value1, String value2) {
            addCriterion("PARENT_SCORE_FLOW not between", value1, value2, "parentScoreFlow");
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