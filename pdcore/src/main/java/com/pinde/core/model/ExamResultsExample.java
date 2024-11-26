package com.pinde.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExamResultsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamResultsExample() {
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

        public Criteria andResultsIdIsNull() {
            addCriterion("RESULTS_ID is null");
            return (Criteria) this;
        }

        public Criteria andResultsIdIsNotNull() {
            addCriterion("RESULTS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andResultsIdEqualTo(String value) {
            addCriterion("RESULTS_ID =", value, "resultsId");
            return (Criteria) this;
        }

        public Criteria andResultsIdNotEqualTo(String value) {
            addCriterion("RESULTS_ID <>", value, "resultsId");
            return (Criteria) this;
        }

        public Criteria andResultsIdGreaterThan(String value) {
            addCriterion("RESULTS_ID >", value, "resultsId");
            return (Criteria) this;
        }

        public Criteria andResultsIdGreaterThanOrEqualTo(String value) {
            addCriterion("RESULTS_ID >=", value, "resultsId");
            return (Criteria) this;
        }

        public Criteria andResultsIdLessThan(String value) {
            addCriterion("RESULTS_ID <", value, "resultsId");
            return (Criteria) this;
        }

        public Criteria andResultsIdLessThanOrEqualTo(String value) {
            addCriterion("RESULTS_ID <=", value, "resultsId");
            return (Criteria) this;
        }

        public Criteria andResultsIdLike(String value) {
            addCriterion("RESULTS_ID like", value, "resultsId");
            return (Criteria) this;
        }

        public Criteria andResultsIdNotLike(String value) {
            addCriterion("RESULTS_ID not like", value, "resultsId");
            return (Criteria) this;
        }

        public Criteria andResultsIdIn(List<String> values) {
            addCriterion("RESULTS_ID in", values, "resultsId");
            return (Criteria) this;
        }

        public Criteria andResultsIdNotIn(List<String> values) {
            addCriterion("RESULTS_ID not in", values, "resultsId");
            return (Criteria) this;
        }

        public Criteria andResultsIdBetween(String value1, String value2) {
            addCriterion("RESULTS_ID between", value1, value2, "resultsId");
            return (Criteria) this;
        }

        public Criteria andResultsIdNotBetween(String value1, String value2) {
            addCriterion("RESULTS_ID not between", value1, value2, "resultsId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andSoluIdIsNull() {
            addCriterion("SOLU_ID is null");
            return (Criteria) this;
        }

        public Criteria andSoluIdIsNotNull() {
            addCriterion("SOLU_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSoluIdEqualTo(String value) {
            addCriterion("SOLU_ID =", value, "soluId");
            return (Criteria) this;
        }

        public Criteria andSoluIdNotEqualTo(String value) {
            addCriterion("SOLU_ID <>", value, "soluId");
            return (Criteria) this;
        }

        public Criteria andSoluIdGreaterThan(String value) {
            addCriterion("SOLU_ID >", value, "soluId");
            return (Criteria) this;
        }

        public Criteria andSoluIdGreaterThanOrEqualTo(String value) {
            addCriterion("SOLU_ID >=", value, "soluId");
            return (Criteria) this;
        }

        public Criteria andSoluIdLessThan(String value) {
            addCriterion("SOLU_ID <", value, "soluId");
            return (Criteria) this;
        }

        public Criteria andSoluIdLessThanOrEqualTo(String value) {
            addCriterion("SOLU_ID <=", value, "soluId");
            return (Criteria) this;
        }

        public Criteria andSoluIdLike(String value) {
            addCriterion("SOLU_ID like", value, "soluId");
            return (Criteria) this;
        }

        public Criteria andSoluIdNotLike(String value) {
            addCriterion("SOLU_ID not like", value, "soluId");
            return (Criteria) this;
        }

        public Criteria andSoluIdIn(List<String> values) {
            addCriterion("SOLU_ID in", values, "soluId");
            return (Criteria) this;
        }

        public Criteria andSoluIdNotIn(List<String> values) {
            addCriterion("SOLU_ID not in", values, "soluId");
            return (Criteria) this;
        }

        public Criteria andSoluIdBetween(String value1, String value2) {
            addCriterion("SOLU_ID between", value1, value2, "soluId");
            return (Criteria) this;
        }

        public Criteria andSoluIdNotBetween(String value1, String value2) {
            addCriterion("SOLU_ID not between", value1, value2, "soluId");
            return (Criteria) this;
        }

        public Criteria andSoluScoreIsNull() {
            addCriterion("SOLU_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andSoluScoreIsNotNull() {
            addCriterion("SOLU_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andSoluScoreEqualTo(String value) {
            addCriterion("SOLU_SCORE =", value, "soluScore");
            return (Criteria) this;
        }

        public Criteria andSoluScoreNotEqualTo(String value) {
            addCriterion("SOLU_SCORE <>", value, "soluScore");
            return (Criteria) this;
        }

        public Criteria andSoluScoreGreaterThan(String value) {
            addCriterion("SOLU_SCORE >", value, "soluScore");
            return (Criteria) this;
        }

        public Criteria andSoluScoreGreaterThanOrEqualTo(String value) {
            addCriterion("SOLU_SCORE >=", value, "soluScore");
            return (Criteria) this;
        }

        public Criteria andSoluScoreLessThan(String value) {
            addCriterion("SOLU_SCORE <", value, "soluScore");
            return (Criteria) this;
        }

        public Criteria andSoluScoreLessThanOrEqualTo(String value) {
            addCriterion("SOLU_SCORE <=", value, "soluScore");
            return (Criteria) this;
        }

        public Criteria andSoluScoreLike(String value) {
            addCriterion("SOLU_SCORE like", value, "soluScore");
            return (Criteria) this;
        }

        public Criteria andSoluScoreNotLike(String value) {
            addCriterion("SOLU_SCORE not like", value, "soluScore");
            return (Criteria) this;
        }

        public Criteria andSoluScoreIn(List<String> values) {
            addCriterion("SOLU_SCORE in", values, "soluScore");
            return (Criteria) this;
        }

        public Criteria andSoluScoreNotIn(List<String> values) {
            addCriterion("SOLU_SCORE not in", values, "soluScore");
            return (Criteria) this;
        }

        public Criteria andSoluScoreBetween(String value1, String value2) {
            addCriterion("SOLU_SCORE between", value1, value2, "soluScore");
            return (Criteria) this;
        }

        public Criteria andSoluScoreNotBetween(String value1, String value2) {
            addCriterion("SOLU_SCORE not between", value1, value2, "soluScore");
            return (Criteria) this;
        }

        public Criteria andExamTimeIsNull() {
            addCriterion("EXAM_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExamTimeIsNotNull() {
            addCriterion("EXAM_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExamTimeEqualTo(String value) {
            addCriterion("EXAM_TIME =", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeNotEqualTo(String value) {
            addCriterion("EXAM_TIME <>", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeGreaterThan(String value) {
            addCriterion("EXAM_TIME >", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_TIME >=", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeLessThan(String value) {
            addCriterion("EXAM_TIME <", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeLessThanOrEqualTo(String value) {
            addCriterion("EXAM_TIME <=", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeLike(String value) {
            addCriterion("EXAM_TIME like", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeNotLike(String value) {
            addCriterion("EXAM_TIME not like", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeIn(List<String> values) {
            addCriterion("EXAM_TIME in", values, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeNotIn(List<String> values) {
            addCriterion("EXAM_TIME not in", values, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeBetween(String value1, String value2) {
            addCriterion("EXAM_TIME between", value1, value2, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeNotBetween(String value1, String value2) {
            addCriterion("EXAM_TIME not between", value1, value2, "examTime");
            return (Criteria) this;
        }

        public Criteria andWordUrlIsNull() {
            addCriterion("WORD_URL is null");
            return (Criteria) this;
        }

        public Criteria andWordUrlIsNotNull() {
            addCriterion("WORD_URL is not null");
            return (Criteria) this;
        }

        public Criteria andWordUrlEqualTo(String value) {
            addCriterion("WORD_URL =", value, "wordUrl");
            return (Criteria) this;
        }

        public Criteria andWordUrlNotEqualTo(String value) {
            addCriterion("WORD_URL <>", value, "wordUrl");
            return (Criteria) this;
        }

        public Criteria andWordUrlGreaterThan(String value) {
            addCriterion("WORD_URL >", value, "wordUrl");
            return (Criteria) this;
        }

        public Criteria andWordUrlGreaterThanOrEqualTo(String value) {
            addCriterion("WORD_URL >=", value, "wordUrl");
            return (Criteria) this;
        }

        public Criteria andWordUrlLessThan(String value) {
            addCriterion("WORD_URL <", value, "wordUrl");
            return (Criteria) this;
        }

        public Criteria andWordUrlLessThanOrEqualTo(String value) {
            addCriterion("WORD_URL <=", value, "wordUrl");
            return (Criteria) this;
        }

        public Criteria andWordUrlLike(String value) {
            addCriterion("WORD_URL like", value, "wordUrl");
            return (Criteria) this;
        }

        public Criteria andWordUrlNotLike(String value) {
            addCriterion("WORD_URL not like", value, "wordUrl");
            return (Criteria) this;
        }

        public Criteria andWordUrlIn(List<String> values) {
            addCriterion("WORD_URL in", values, "wordUrl");
            return (Criteria) this;
        }

        public Criteria andWordUrlNotIn(List<String> values) {
            addCriterion("WORD_URL not in", values, "wordUrl");
            return (Criteria) this;
        }

        public Criteria andWordUrlBetween(String value1, String value2) {
            addCriterion("WORD_URL between", value1, value2, "wordUrl");
            return (Criteria) this;
        }

        public Criteria andWordUrlNotBetween(String value1, String value2) {
            addCriterion("WORD_URL not between", value1, value2, "wordUrl");
            return (Criteria) this;
        }

        public Criteria andSoluNameIsNull() {
            addCriterion("SOLU_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSoluNameIsNotNull() {
            addCriterion("SOLU_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSoluNameEqualTo(String value) {
            addCriterion("SOLU_NAME =", value, "soluName");
            return (Criteria) this;
        }

        public Criteria andSoluNameNotEqualTo(String value) {
            addCriterion("SOLU_NAME <>", value, "soluName");
            return (Criteria) this;
        }

        public Criteria andSoluNameGreaterThan(String value) {
            addCriterion("SOLU_NAME >", value, "soluName");
            return (Criteria) this;
        }

        public Criteria andSoluNameGreaterThanOrEqualTo(String value) {
            addCriterion("SOLU_NAME >=", value, "soluName");
            return (Criteria) this;
        }

        public Criteria andSoluNameLessThan(String value) {
            addCriterion("SOLU_NAME <", value, "soluName");
            return (Criteria) this;
        }

        public Criteria andSoluNameLessThanOrEqualTo(String value) {
            addCriterion("SOLU_NAME <=", value, "soluName");
            return (Criteria) this;
        }

        public Criteria andSoluNameLike(String value) {
            addCriterion("SOLU_NAME like", value, "soluName");
            return (Criteria) this;
        }

        public Criteria andSoluNameNotLike(String value) {
            addCriterion("SOLU_NAME not like", value, "soluName");
            return (Criteria) this;
        }

        public Criteria andSoluNameIn(List<String> values) {
            addCriterion("SOLU_NAME in", values, "soluName");
            return (Criteria) this;
        }

        public Criteria andSoluNameNotIn(List<String> values) {
            addCriterion("SOLU_NAME not in", values, "soluName");
            return (Criteria) this;
        }

        public Criteria andSoluNameBetween(String value1, String value2) {
            addCriterion("SOLU_NAME between", value1, value2, "soluName");
            return (Criteria) this;
        }

        public Criteria andSoluNameNotBetween(String value1, String value2) {
            addCriterion("SOLU_NAME not between", value1, value2, "soluName");
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

        public Criteria andAddScoreEqualTo(String value) {
            addCriterion("ADD_SCORE =", value, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreNotEqualTo(String value) {
            addCriterion("ADD_SCORE <>", value, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreGreaterThan(String value) {
            addCriterion("ADD_SCORE >", value, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreGreaterThanOrEqualTo(String value) {
            addCriterion("ADD_SCORE >=", value, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreLessThan(String value) {
            addCriterion("ADD_SCORE <", value, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreLessThanOrEqualTo(String value) {
            addCriterion("ADD_SCORE <=", value, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreLike(String value) {
            addCriterion("ADD_SCORE like", value, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreNotLike(String value) {
            addCriterion("ADD_SCORE not like", value, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreIn(List<String> values) {
            addCriterion("ADD_SCORE in", values, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreNotIn(List<String> values) {
            addCriterion("ADD_SCORE not in", values, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreBetween(String value1, String value2) {
            addCriterion("ADD_SCORE between", value1, value2, "addScore");
            return (Criteria) this;
        }

        public Criteria andAddScoreNotBetween(String value1, String value2) {
            addCriterion("ADD_SCORE not between", value1, value2, "addScore");
            return (Criteria) this;
        }

        public Criteria andExamWnIsNull() {
            addCriterion("EXAM_WN is null");
            return (Criteria) this;
        }

        public Criteria andExamWnIsNotNull() {
            addCriterion("EXAM_WN is not null");
            return (Criteria) this;
        }

        public Criteria andExamWnEqualTo(String value) {
            addCriterion("EXAM_WN =", value, "examWn");
            return (Criteria) this;
        }

        public Criteria andExamWnNotEqualTo(String value) {
            addCriterion("EXAM_WN <>", value, "examWn");
            return (Criteria) this;
        }

        public Criteria andExamWnGreaterThan(String value) {
            addCriterion("EXAM_WN >", value, "examWn");
            return (Criteria) this;
        }

        public Criteria andExamWnGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_WN >=", value, "examWn");
            return (Criteria) this;
        }

        public Criteria andExamWnLessThan(String value) {
            addCriterion("EXAM_WN <", value, "examWn");
            return (Criteria) this;
        }

        public Criteria andExamWnLessThanOrEqualTo(String value) {
            addCriterion("EXAM_WN <=", value, "examWn");
            return (Criteria) this;
        }

        public Criteria andExamWnLike(String value) {
            addCriterion("EXAM_WN like", value, "examWn");
            return (Criteria) this;
        }

        public Criteria andExamWnNotLike(String value) {
            addCriterion("EXAM_WN not like", value, "examWn");
            return (Criteria) this;
        }

        public Criteria andExamWnIn(List<String> values) {
            addCriterion("EXAM_WN in", values, "examWn");
            return (Criteria) this;
        }

        public Criteria andExamWnNotIn(List<String> values) {
            addCriterion("EXAM_WN not in", values, "examWn");
            return (Criteria) this;
        }

        public Criteria andExamWnBetween(String value1, String value2) {
            addCriterion("EXAM_WN between", value1, value2, "examWn");
            return (Criteria) this;
        }

        public Criteria andExamWnNotBetween(String value1, String value2) {
            addCriterion("EXAM_WN not between", value1, value2, "examWn");
            return (Criteria) this;
        }

        public Criteria andProcessFlowIsNull() {
            addCriterion("PROCESS_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andProcessFlowIsNotNull() {
            addCriterion("PROCESS_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andProcessFlowEqualTo(String value) {
            addCriterion("PROCESS_FLOW =", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowNotEqualTo(String value) {
            addCriterion("PROCESS_FLOW <>", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowGreaterThan(String value) {
            addCriterion("PROCESS_FLOW >", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PROCESS_FLOW >=", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowLessThan(String value) {
            addCriterion("PROCESS_FLOW <", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowLessThanOrEqualTo(String value) {
            addCriterion("PROCESS_FLOW <=", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowLike(String value) {
            addCriterion("PROCESS_FLOW like", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowNotLike(String value) {
            addCriterion("PROCESS_FLOW not like", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowIn(List<String> values) {
            addCriterion("PROCESS_FLOW in", values, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowNotIn(List<String> values) {
            addCriterion("PROCESS_FLOW not in", values, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowBetween(String value1, String value2) {
            addCriterion("PROCESS_FLOW between", value1, value2, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowNotBetween(String value1, String value2) {
            addCriterion("PROCESS_FLOW not between", value1, value2, "processFlow");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNull() {
            addCriterion("SUBMIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNotNull() {
            addCriterion("SUBMIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeEqualTo(String value) {
            addCriterion("SUBMIT_TIME =", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotEqualTo(String value) {
            addCriterion("SUBMIT_TIME <>", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThan(String value) {
            addCriterion("SUBMIT_TIME >", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SUBMIT_TIME >=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThan(String value) {
            addCriterion("SUBMIT_TIME <", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThanOrEqualTo(String value) {
            addCriterion("SUBMIT_TIME <=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLike(String value) {
            addCriterion("SUBMIT_TIME like", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotLike(String value) {
            addCriterion("SUBMIT_TIME not like", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIn(List<String> values) {
            addCriterion("SUBMIT_TIME in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotIn(List<String> values) {
            addCriterion("SUBMIT_TIME not in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeBetween(String value1, String value2) {
            addCriterion("SUBMIT_TIME between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotBetween(String value1, String value2) {
            addCriterion("SUBMIT_TIME not between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andUnifyExamtimeIdIsNull() {
            addCriterion("UNIFY_EXAMTIME_ID is null");
            return (Criteria) this;
        }

        public Criteria andUnifyExamtimeIdIsNotNull() {
            addCriterion("UNIFY_EXAMTIME_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUnifyExamtimeIdEqualTo(String value) {
            addCriterion("UNIFY_EXAMTIME_ID =", value, "unifyExamtimeId");
            return (Criteria) this;
        }

        public Criteria andUnifyExamtimeIdNotEqualTo(String value) {
            addCriterion("UNIFY_EXAMTIME_ID <>", value, "unifyExamtimeId");
            return (Criteria) this;
        }

        public Criteria andUnifyExamtimeIdGreaterThan(String value) {
            addCriterion("UNIFY_EXAMTIME_ID >", value, "unifyExamtimeId");
            return (Criteria) this;
        }

        public Criteria andUnifyExamtimeIdGreaterThanOrEqualTo(String value) {
            addCriterion("UNIFY_EXAMTIME_ID >=", value, "unifyExamtimeId");
            return (Criteria) this;
        }

        public Criteria andUnifyExamtimeIdLessThan(String value) {
            addCriterion("UNIFY_EXAMTIME_ID <", value, "unifyExamtimeId");
            return (Criteria) this;
        }

        public Criteria andUnifyExamtimeIdLessThanOrEqualTo(String value) {
            addCriterion("UNIFY_EXAMTIME_ID <=", value, "unifyExamtimeId");
            return (Criteria) this;
        }

        public Criteria andUnifyExamtimeIdLike(String value) {
            addCriterion("UNIFY_EXAMTIME_ID like", value, "unifyExamtimeId");
            return (Criteria) this;
        }

        public Criteria andUnifyExamtimeIdNotLike(String value) {
            addCriterion("UNIFY_EXAMTIME_ID not like", value, "unifyExamtimeId");
            return (Criteria) this;
        }

        public Criteria andUnifyExamtimeIdIn(List<String> values) {
            addCriterion("UNIFY_EXAMTIME_ID in", values, "unifyExamtimeId");
            return (Criteria) this;
        }

        public Criteria andUnifyExamtimeIdNotIn(List<String> values) {
            addCriterion("UNIFY_EXAMTIME_ID not in", values, "unifyExamtimeId");
            return (Criteria) this;
        }

        public Criteria andUnifyExamtimeIdBetween(String value1, String value2) {
            addCriterion("UNIFY_EXAMTIME_ID between", value1, value2, "unifyExamtimeId");
            return (Criteria) this;
        }

        public Criteria andUnifyExamtimeIdNotBetween(String value1, String value2) {
            addCriterion("UNIFY_EXAMTIME_ID not between", value1, value2, "unifyExamtimeId");
            return (Criteria) this;
        }

        public Criteria andExamTestTypeIsNull() {
            addCriterion("EXAM_TEST_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andExamTestTypeIsNotNull() {
            addCriterion("EXAM_TEST_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andExamTestTypeEqualTo(String value) {
            addCriterion("EXAM_TEST_TYPE =", value, "examTestType");
            return (Criteria) this;
        }

        public Criteria andExamTestTypeNotEqualTo(String value) {
            addCriterion("EXAM_TEST_TYPE <>", value, "examTestType");
            return (Criteria) this;
        }

        public Criteria andExamTestTypeGreaterThan(String value) {
            addCriterion("EXAM_TEST_TYPE >", value, "examTestType");
            return (Criteria) this;
        }

        public Criteria andExamTestTypeGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_TEST_TYPE >=", value, "examTestType");
            return (Criteria) this;
        }

        public Criteria andExamTestTypeLessThan(String value) {
            addCriterion("EXAM_TEST_TYPE <", value, "examTestType");
            return (Criteria) this;
        }

        public Criteria andExamTestTypeLessThanOrEqualTo(String value) {
            addCriterion("EXAM_TEST_TYPE <=", value, "examTestType");
            return (Criteria) this;
        }

        public Criteria andExamTestTypeLike(String value) {
            addCriterion("EXAM_TEST_TYPE like", value, "examTestType");
            return (Criteria) this;
        }

        public Criteria andExamTestTypeNotLike(String value) {
            addCriterion("EXAM_TEST_TYPE not like", value, "examTestType");
            return (Criteria) this;
        }

        public Criteria andExamTestTypeIn(List<String> values) {
            addCriterion("EXAM_TEST_TYPE in", values, "examTestType");
            return (Criteria) this;
        }

        public Criteria andExamTestTypeNotIn(List<String> values) {
            addCriterion("EXAM_TEST_TYPE not in", values, "examTestType");
            return (Criteria) this;
        }

        public Criteria andExamTestTypeBetween(String value1, String value2) {
            addCriterion("EXAM_TEST_TYPE between", value1, value2, "examTestType");
            return (Criteria) this;
        }

        public Criteria andExamTestTypeNotBetween(String value1, String value2) {
            addCriterion("EXAM_TEST_TYPE not between", value1, value2, "examTestType");
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

        public Criteria andIsMarkedIsNull() {
            addCriterion("IS_MARKED is null");
            return (Criteria) this;
        }

        public Criteria andIsMarkedIsNotNull() {
            addCriterion("IS_MARKED is not null");
            return (Criteria) this;
        }

        public Criteria andIsMarkedEqualTo(String value) {
            addCriterion("IS_MARKED =", value, "isMarked");
            return (Criteria) this;
        }

        public Criteria andIsMarkedNotEqualTo(String value) {
            addCriterion("IS_MARKED <>", value, "isMarked");
            return (Criteria) this;
        }

        public Criteria andIsMarkedGreaterThan(String value) {
            addCriterion("IS_MARKED >", value, "isMarked");
            return (Criteria) this;
        }

        public Criteria andIsMarkedGreaterThanOrEqualTo(String value) {
            addCriterion("IS_MARKED >=", value, "isMarked");
            return (Criteria) this;
        }

        public Criteria andIsMarkedLessThan(String value) {
            addCriterion("IS_MARKED <", value, "isMarked");
            return (Criteria) this;
        }

        public Criteria andIsMarkedLessThanOrEqualTo(String value) {
            addCriterion("IS_MARKED <=", value, "isMarked");
            return (Criteria) this;
        }

        public Criteria andIsMarkedLike(String value) {
            addCriterion("IS_MARKED like", value, "isMarked");
            return (Criteria) this;
        }

        public Criteria andIsMarkedNotLike(String value) {
            addCriterion("IS_MARKED not like", value, "isMarked");
            return (Criteria) this;
        }

        public Criteria andIsMarkedIn(List<String> values) {
            addCriterion("IS_MARKED in", values, "isMarked");
            return (Criteria) this;
        }

        public Criteria andIsMarkedNotIn(List<String> values) {
            addCriterion("IS_MARKED not in", values, "isMarked");
            return (Criteria) this;
        }

        public Criteria andIsMarkedBetween(String value1, String value2) {
            addCriterion("IS_MARKED between", value1, value2, "isMarked");
            return (Criteria) this;
        }

        public Criteria andIsMarkedNotBetween(String value1, String value2) {
            addCriterion("IS_MARKED not between", value1, value2, "isMarked");
            return (Criteria) this;
        }

        public Criteria andSubjectiveScoreIsNull() {
            addCriterion("SUBJECTIVE_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andSubjectiveScoreIsNotNull() {
            addCriterion("SUBJECTIVE_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectiveScoreEqualTo(String value) {
            addCriterion("SUBJECTIVE_SCORE =", value, "subjectiveScore");
            return (Criteria) this;
        }

        public Criteria andSubjectiveScoreNotEqualTo(String value) {
            addCriterion("SUBJECTIVE_SCORE <>", value, "subjectiveScore");
            return (Criteria) this;
        }

        public Criteria andSubjectiveScoreGreaterThan(String value) {
            addCriterion("SUBJECTIVE_SCORE >", value, "subjectiveScore");
            return (Criteria) this;
        }

        public Criteria andSubjectiveScoreGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECTIVE_SCORE >=", value, "subjectiveScore");
            return (Criteria) this;
        }

        public Criteria andSubjectiveScoreLessThan(String value) {
            addCriterion("SUBJECTIVE_SCORE <", value, "subjectiveScore");
            return (Criteria) this;
        }

        public Criteria andSubjectiveScoreLessThanOrEqualTo(String value) {
            addCriterion("SUBJECTIVE_SCORE <=", value, "subjectiveScore");
            return (Criteria) this;
        }

        public Criteria andSubjectiveScoreLike(String value) {
            addCriterion("SUBJECTIVE_SCORE like", value, "subjectiveScore");
            return (Criteria) this;
        }

        public Criteria andSubjectiveScoreNotLike(String value) {
            addCriterion("SUBJECTIVE_SCORE not like", value, "subjectiveScore");
            return (Criteria) this;
        }

        public Criteria andSubjectiveScoreIn(List<String> values) {
            addCriterion("SUBJECTIVE_SCORE in", values, "subjectiveScore");
            return (Criteria) this;
        }

        public Criteria andSubjectiveScoreNotIn(List<String> values) {
            addCriterion("SUBJECTIVE_SCORE not in", values, "subjectiveScore");
            return (Criteria) this;
        }

        public Criteria andSubjectiveScoreBetween(String value1, String value2) {
            addCriterion("SUBJECTIVE_SCORE between", value1, value2, "subjectiveScore");
            return (Criteria) this;
        }

        public Criteria andSubjectiveScoreNotBetween(String value1, String value2) {
            addCriterion("SUBJECTIVE_SCORE not between", value1, value2, "subjectiveScore");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdIsNull() {
            addCriterion("MARK_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdIsNotNull() {
            addCriterion("MARK_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdEqualTo(String value) {
            addCriterion("MARK_USER_ID =", value, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdNotEqualTo(String value) {
            addCriterion("MARK_USER_ID <>", value, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdGreaterThan(String value) {
            addCriterion("MARK_USER_ID >", value, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("MARK_USER_ID >=", value, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdLessThan(String value) {
            addCriterion("MARK_USER_ID <", value, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdLessThanOrEqualTo(String value) {
            addCriterion("MARK_USER_ID <=", value, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdLike(String value) {
            addCriterion("MARK_USER_ID like", value, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdNotLike(String value) {
            addCriterion("MARK_USER_ID not like", value, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdIn(List<String> values) {
            addCriterion("MARK_USER_ID in", values, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdNotIn(List<String> values) {
            addCriterion("MARK_USER_ID not in", values, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdBetween(String value1, String value2) {
            addCriterion("MARK_USER_ID between", value1, value2, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkUserIdNotBetween(String value1, String value2) {
            addCriterion("MARK_USER_ID not between", value1, value2, "markUserId");
            return (Criteria) this;
        }

        public Criteria andMarkTimeIsNull() {
            addCriterion("MARK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andMarkTimeIsNotNull() {
            addCriterion("MARK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andMarkTimeEqualTo(String value) {
            addCriterion("MARK_TIME =", value, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeNotEqualTo(String value) {
            addCriterion("MARK_TIME <>", value, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeGreaterThan(String value) {
            addCriterion("MARK_TIME >", value, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeGreaterThanOrEqualTo(String value) {
            addCriterion("MARK_TIME >=", value, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeLessThan(String value) {
            addCriterion("MARK_TIME <", value, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeLessThanOrEqualTo(String value) {
            addCriterion("MARK_TIME <=", value, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeLike(String value) {
            addCriterion("MARK_TIME like", value, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeNotLike(String value) {
            addCriterion("MARK_TIME not like", value, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeIn(List<String> values) {
            addCriterion("MARK_TIME in", values, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeNotIn(List<String> values) {
            addCriterion("MARK_TIME not in", values, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeBetween(String value1, String value2) {
            addCriterion("MARK_TIME between", value1, value2, "markTime");
            return (Criteria) this;
        }

        public Criteria andMarkTimeNotBetween(String value1, String value2) {
            addCriterion("MARK_TIME not between", value1, value2, "markTime");
            return (Criteria) this;
        }

        public Criteria andIsSynIsNull() {
            addCriterion("IS_SYN is null");
            return (Criteria) this;
        }

        public Criteria andIsSynIsNotNull() {
            addCriterion("IS_SYN is not null");
            return (Criteria) this;
        }

        public Criteria andIsSynEqualTo(String value) {
            addCriterion("IS_SYN =", value, "isSyn");
            return (Criteria) this;
        }

        public Criteria andIsSynNotEqualTo(String value) {
            addCriterion("IS_SYN <>", value, "isSyn");
            return (Criteria) this;
        }

        public Criteria andIsSynGreaterThan(String value) {
            addCriterion("IS_SYN >", value, "isSyn");
            return (Criteria) this;
        }

        public Criteria andIsSynGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SYN >=", value, "isSyn");
            return (Criteria) this;
        }

        public Criteria andIsSynLessThan(String value) {
            addCriterion("IS_SYN <", value, "isSyn");
            return (Criteria) this;
        }

        public Criteria andIsSynLessThanOrEqualTo(String value) {
            addCriterion("IS_SYN <=", value, "isSyn");
            return (Criteria) this;
        }

        public Criteria andIsSynLike(String value) {
            addCriterion("IS_SYN like", value, "isSyn");
            return (Criteria) this;
        }

        public Criteria andIsSynNotLike(String value) {
            addCriterion("IS_SYN not like", value, "isSyn");
            return (Criteria) this;
        }

        public Criteria andIsSynIn(List<String> values) {
            addCriterion("IS_SYN in", values, "isSyn");
            return (Criteria) this;
        }

        public Criteria andIsSynNotIn(List<String> values) {
            addCriterion("IS_SYN not in", values, "isSyn");
            return (Criteria) this;
        }

        public Criteria andIsSynBetween(String value1, String value2) {
            addCriterion("IS_SYN between", value1, value2, "isSyn");
            return (Criteria) this;
        }

        public Criteria andIsSynNotBetween(String value1, String value2) {
            addCriterion("IS_SYN not between", value1, value2, "isSyn");
            return (Criteria) this;
        }

        public Criteria andResultSourceIsNull() {
            addCriterion("RESULT_SOURCE is null");
            return (Criteria) this;
        }

        public Criteria andResultSourceIsNotNull() {
            addCriterion("RESULT_SOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andResultSourceEqualTo(String value) {
            addCriterion("RESULT_SOURCE =", value, "resultSource");
            return (Criteria) this;
        }

        public Criteria andResultSourceNotEqualTo(String value) {
            addCriterion("RESULT_SOURCE <>", value, "resultSource");
            return (Criteria) this;
        }

        public Criteria andResultSourceGreaterThan(String value) {
            addCriterion("RESULT_SOURCE >", value, "resultSource");
            return (Criteria) this;
        }

        public Criteria andResultSourceGreaterThanOrEqualTo(String value) {
            addCriterion("RESULT_SOURCE >=", value, "resultSource");
            return (Criteria) this;
        }

        public Criteria andResultSourceLessThan(String value) {
            addCriterion("RESULT_SOURCE <", value, "resultSource");
            return (Criteria) this;
        }

        public Criteria andResultSourceLessThanOrEqualTo(String value) {
            addCriterion("RESULT_SOURCE <=", value, "resultSource");
            return (Criteria) this;
        }

        public Criteria andResultSourceLike(String value) {
            addCriterion("RESULT_SOURCE like", value, "resultSource");
            return (Criteria) this;
        }

        public Criteria andResultSourceNotLike(String value) {
            addCriterion("RESULT_SOURCE not like", value, "resultSource");
            return (Criteria) this;
        }

        public Criteria andResultSourceIn(List<String> values) {
            addCriterion("RESULT_SOURCE in", values, "resultSource");
            return (Criteria) this;
        }

        public Criteria andResultSourceNotIn(List<String> values) {
            addCriterion("RESULT_SOURCE not in", values, "resultSource");
            return (Criteria) this;
        }

        public Criteria andResultSourceBetween(String value1, String value2) {
            addCriterion("RESULT_SOURCE between", value1, value2, "resultSource");
            return (Criteria) this;
        }

        public Criteria andResultSourceNotBetween(String value1, String value2) {
            addCriterion("RESULT_SOURCE not between", value1, value2, "resultSource");
            return (Criteria) this;
        }

        public Criteria andTestCountIsNull() {
            addCriterion("TEST_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andTestCountIsNotNull() {
            addCriterion("TEST_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTestCountEqualTo(String value) {
            addCriterion("TEST_COUNT =", value, "testCount");
            return (Criteria) this;
        }

        public Criteria andTestCountNotEqualTo(String value) {
            addCriterion("TEST_COUNT <>", value, "testCount");
            return (Criteria) this;
        }

        public Criteria andTestCountGreaterThan(String value) {
            addCriterion("TEST_COUNT >", value, "testCount");
            return (Criteria) this;
        }

        public Criteria andTestCountGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_COUNT >=", value, "testCount");
            return (Criteria) this;
        }

        public Criteria andTestCountLessThan(String value) {
            addCriterion("TEST_COUNT <", value, "testCount");
            return (Criteria) this;
        }

        public Criteria andTestCountLessThanOrEqualTo(String value) {
            addCriterion("TEST_COUNT <=", value, "testCount");
            return (Criteria) this;
        }

        public Criteria andTestCountLike(String value) {
            addCriterion("TEST_COUNT like", value, "testCount");
            return (Criteria) this;
        }

        public Criteria andTestCountNotLike(String value) {
            addCriterion("TEST_COUNT not like", value, "testCount");
            return (Criteria) this;
        }

        public Criteria andTestCountIn(List<String> values) {
            addCriterion("TEST_COUNT in", values, "testCount");
            return (Criteria) this;
        }

        public Criteria andTestCountNotIn(List<String> values) {
            addCriterion("TEST_COUNT not in", values, "testCount");
            return (Criteria) this;
        }

        public Criteria andTestCountBetween(String value1, String value2) {
            addCriterion("TEST_COUNT between", value1, value2, "testCount");
            return (Criteria) this;
        }

        public Criteria andTestCountNotBetween(String value1, String value2) {
            addCriterion("TEST_COUNT not between", value1, value2, "testCount");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreIsNull() {
            addCriterion("THEORY_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreIsNotNull() {
            addCriterion("THEORY_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreEqualTo(BigDecimal value) {
            addCriterion("THEORY_SCORE =", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreNotEqualTo(BigDecimal value) {
            addCriterion("THEORY_SCORE <>", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreGreaterThan(BigDecimal value) {
            addCriterion("THEORY_SCORE >", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("THEORY_SCORE >=", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreLessThan(BigDecimal value) {
            addCriterion("THEORY_SCORE <", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("THEORY_SCORE <=", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreIn(List<BigDecimal> values) {
            addCriterion("THEORY_SCORE in", values, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreNotIn(List<BigDecimal> values) {
            addCriterion("THEORY_SCORE not in", values, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("THEORY_SCORE between", value1, value2, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("THEORY_SCORE not between", value1, value2, "theoryScore");
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