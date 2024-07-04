package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EduAnswerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EduAnswerExample() {
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

        public Criteria andAnswerFlowIsNull() {
            addCriterion("ANSWER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAnswerFlowIsNotNull() {
            addCriterion("ANSWER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerFlowEqualTo(String value) {
            addCriterion("ANSWER_FLOW =", value, "answerFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerFlowNotEqualTo(String value) {
            addCriterion("ANSWER_FLOW <>", value, "answerFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerFlowGreaterThan(String value) {
            addCriterion("ANSWER_FLOW >", value, "answerFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ANSWER_FLOW >=", value, "answerFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerFlowLessThan(String value) {
            addCriterion("ANSWER_FLOW <", value, "answerFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerFlowLessThanOrEqualTo(String value) {
            addCriterion("ANSWER_FLOW <=", value, "answerFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerFlowLike(String value) {
            addCriterion("ANSWER_FLOW like", value, "answerFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerFlowNotLike(String value) {
            addCriterion("ANSWER_FLOW not like", value, "answerFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerFlowIn(List<String> values) {
            addCriterion("ANSWER_FLOW in", values, "answerFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerFlowNotIn(List<String> values) {
            addCriterion("ANSWER_FLOW not in", values, "answerFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerFlowBetween(String value1, String value2) {
            addCriterion("ANSWER_FLOW between", value1, value2, "answerFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerFlowNotBetween(String value1, String value2) {
            addCriterion("ANSWER_FLOW not between", value1, value2, "answerFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowIsNull() {
            addCriterion("QUESTION_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowIsNotNull() {
            addCriterion("QUESTION_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowEqualTo(String value) {
            addCriterion("QUESTION_FLOW =", value, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowNotEqualTo(String value) {
            addCriterion("QUESTION_FLOW <>", value, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowGreaterThan(String value) {
            addCriterion("QUESTION_FLOW >", value, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTION_FLOW >=", value, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowLessThan(String value) {
            addCriterion("QUESTION_FLOW <", value, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowLessThanOrEqualTo(String value) {
            addCriterion("QUESTION_FLOW <=", value, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowLike(String value) {
            addCriterion("QUESTION_FLOW like", value, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowNotLike(String value) {
            addCriterion("QUESTION_FLOW not like", value, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowIn(List<String> values) {
            addCriterion("QUESTION_FLOW in", values, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowNotIn(List<String> values) {
            addCriterion("QUESTION_FLOW not in", values, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowBetween(String value1, String value2) {
            addCriterion("QUESTION_FLOW between", value1, value2, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionFlowNotBetween(String value1, String value2) {
            addCriterion("QUESTION_FLOW not between", value1, value2, "questionFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerUserFlowIsNull() {
            addCriterion("ANSWER_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAnswerUserFlowIsNotNull() {
            addCriterion("ANSWER_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerUserFlowEqualTo(String value) {
            addCriterion("ANSWER_USER_FLOW =", value, "answerUserFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerUserFlowNotEqualTo(String value) {
            addCriterion("ANSWER_USER_FLOW <>", value, "answerUserFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerUserFlowGreaterThan(String value) {
            addCriterion("ANSWER_USER_FLOW >", value, "answerUserFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ANSWER_USER_FLOW >=", value, "answerUserFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerUserFlowLessThan(String value) {
            addCriterion("ANSWER_USER_FLOW <", value, "answerUserFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerUserFlowLessThanOrEqualTo(String value) {
            addCriterion("ANSWER_USER_FLOW <=", value, "answerUserFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerUserFlowLike(String value) {
            addCriterion("ANSWER_USER_FLOW like", value, "answerUserFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerUserFlowNotLike(String value) {
            addCriterion("ANSWER_USER_FLOW not like", value, "answerUserFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerUserFlowIn(List<String> values) {
            addCriterion("ANSWER_USER_FLOW in", values, "answerUserFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerUserFlowNotIn(List<String> values) {
            addCriterion("ANSWER_USER_FLOW not in", values, "answerUserFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerUserFlowBetween(String value1, String value2) {
            addCriterion("ANSWER_USER_FLOW between", value1, value2, "answerUserFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerUserFlowNotBetween(String value1, String value2) {
            addCriterion("ANSWER_USER_FLOW not between", value1, value2, "answerUserFlow");
            return (Criteria) this;
        }

        public Criteria andAnswerContentIsNull() {
            addCriterion("ANSWER_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andAnswerContentIsNotNull() {
            addCriterion("ANSWER_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerContentEqualTo(String value) {
            addCriterion("ANSWER_CONTENT =", value, "answerContent");
            return (Criteria) this;
        }

        public Criteria andAnswerContentNotEqualTo(String value) {
            addCriterion("ANSWER_CONTENT <>", value, "answerContent");
            return (Criteria) this;
        }

        public Criteria andAnswerContentGreaterThan(String value) {
            addCriterion("ANSWER_CONTENT >", value, "answerContent");
            return (Criteria) this;
        }

        public Criteria andAnswerContentGreaterThanOrEqualTo(String value) {
            addCriterion("ANSWER_CONTENT >=", value, "answerContent");
            return (Criteria) this;
        }

        public Criteria andAnswerContentLessThan(String value) {
            addCriterion("ANSWER_CONTENT <", value, "answerContent");
            return (Criteria) this;
        }

        public Criteria andAnswerContentLessThanOrEqualTo(String value) {
            addCriterion("ANSWER_CONTENT <=", value, "answerContent");
            return (Criteria) this;
        }

        public Criteria andAnswerContentLike(String value) {
            addCriterion("ANSWER_CONTENT like", value, "answerContent");
            return (Criteria) this;
        }

        public Criteria andAnswerContentNotLike(String value) {
            addCriterion("ANSWER_CONTENT not like", value, "answerContent");
            return (Criteria) this;
        }

        public Criteria andAnswerContentIn(List<String> values) {
            addCriterion("ANSWER_CONTENT in", values, "answerContent");
            return (Criteria) this;
        }

        public Criteria andAnswerContentNotIn(List<String> values) {
            addCriterion("ANSWER_CONTENT not in", values, "answerContent");
            return (Criteria) this;
        }

        public Criteria andAnswerContentBetween(String value1, String value2) {
            addCriterion("ANSWER_CONTENT between", value1, value2, "answerContent");
            return (Criteria) this;
        }

        public Criteria andAnswerContentNotBetween(String value1, String value2) {
            addCriterion("ANSWER_CONTENT not between", value1, value2, "answerContent");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeIsNull() {
            addCriterion("ANSWER_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeIsNotNull() {
            addCriterion("ANSWER_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeEqualTo(String value) {
            addCriterion("ANSWER_TIME =", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeNotEqualTo(String value) {
            addCriterion("ANSWER_TIME <>", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeGreaterThan(String value) {
            addCriterion("ANSWER_TIME >", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeGreaterThanOrEqualTo(String value) {
            addCriterion("ANSWER_TIME >=", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeLessThan(String value) {
            addCriterion("ANSWER_TIME <", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeLessThanOrEqualTo(String value) {
            addCriterion("ANSWER_TIME <=", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeLike(String value) {
            addCriterion("ANSWER_TIME like", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeNotLike(String value) {
            addCriterion("ANSWER_TIME not like", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeIn(List<String> values) {
            addCriterion("ANSWER_TIME in", values, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeNotIn(List<String> values) {
            addCriterion("ANSWER_TIME not in", values, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeBetween(String value1, String value2) {
            addCriterion("ANSWER_TIME between", value1, value2, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeNotBetween(String value1, String value2) {
            addCriterion("ANSWER_TIME not between", value1, value2, "answerTime");
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

        public Criteria andAnsTypeIdIsNull() {
            addCriterion("ANS_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andAnsTypeIdIsNotNull() {
            addCriterion("ANS_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAnsTypeIdEqualTo(String value) {
            addCriterion("ANS_TYPE_ID =", value, "ansTypeId");
            return (Criteria) this;
        }

        public Criteria andAnsTypeIdNotEqualTo(String value) {
            addCriterion("ANS_TYPE_ID <>", value, "ansTypeId");
            return (Criteria) this;
        }

        public Criteria andAnsTypeIdGreaterThan(String value) {
            addCriterion("ANS_TYPE_ID >", value, "ansTypeId");
            return (Criteria) this;
        }

        public Criteria andAnsTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ANS_TYPE_ID >=", value, "ansTypeId");
            return (Criteria) this;
        }

        public Criteria andAnsTypeIdLessThan(String value) {
            addCriterion("ANS_TYPE_ID <", value, "ansTypeId");
            return (Criteria) this;
        }

        public Criteria andAnsTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ANS_TYPE_ID <=", value, "ansTypeId");
            return (Criteria) this;
        }

        public Criteria andAnsTypeIdLike(String value) {
            addCriterion("ANS_TYPE_ID like", value, "ansTypeId");
            return (Criteria) this;
        }

        public Criteria andAnsTypeIdNotLike(String value) {
            addCriterion("ANS_TYPE_ID not like", value, "ansTypeId");
            return (Criteria) this;
        }

        public Criteria andAnsTypeIdIn(List<String> values) {
            addCriterion("ANS_TYPE_ID in", values, "ansTypeId");
            return (Criteria) this;
        }

        public Criteria andAnsTypeIdNotIn(List<String> values) {
            addCriterion("ANS_TYPE_ID not in", values, "ansTypeId");
            return (Criteria) this;
        }

        public Criteria andAnsTypeIdBetween(String value1, String value2) {
            addCriterion("ANS_TYPE_ID between", value1, value2, "ansTypeId");
            return (Criteria) this;
        }

        public Criteria andAnsTypeIdNotBetween(String value1, String value2) {
            addCriterion("ANS_TYPE_ID not between", value1, value2, "ansTypeId");
            return (Criteria) this;
        }

        public Criteria andAnsTypeNameIsNull() {
            addCriterion("ANS_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAnsTypeNameIsNotNull() {
            addCriterion("ANS_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAnsTypeNameEqualTo(String value) {
            addCriterion("ANS_TYPE_NAME =", value, "ansTypeName");
            return (Criteria) this;
        }

        public Criteria andAnsTypeNameNotEqualTo(String value) {
            addCriterion("ANS_TYPE_NAME <>", value, "ansTypeName");
            return (Criteria) this;
        }

        public Criteria andAnsTypeNameGreaterThan(String value) {
            addCriterion("ANS_TYPE_NAME >", value, "ansTypeName");
            return (Criteria) this;
        }

        public Criteria andAnsTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("ANS_TYPE_NAME >=", value, "ansTypeName");
            return (Criteria) this;
        }

        public Criteria andAnsTypeNameLessThan(String value) {
            addCriterion("ANS_TYPE_NAME <", value, "ansTypeName");
            return (Criteria) this;
        }

        public Criteria andAnsTypeNameLessThanOrEqualTo(String value) {
            addCriterion("ANS_TYPE_NAME <=", value, "ansTypeName");
            return (Criteria) this;
        }

        public Criteria andAnsTypeNameLike(String value) {
            addCriterion("ANS_TYPE_NAME like", value, "ansTypeName");
            return (Criteria) this;
        }

        public Criteria andAnsTypeNameNotLike(String value) {
            addCriterion("ANS_TYPE_NAME not like", value, "ansTypeName");
            return (Criteria) this;
        }

        public Criteria andAnsTypeNameIn(List<String> values) {
            addCriterion("ANS_TYPE_NAME in", values, "ansTypeName");
            return (Criteria) this;
        }

        public Criteria andAnsTypeNameNotIn(List<String> values) {
            addCriterion("ANS_TYPE_NAME not in", values, "ansTypeName");
            return (Criteria) this;
        }

        public Criteria andAnsTypeNameBetween(String value1, String value2) {
            addCriterion("ANS_TYPE_NAME between", value1, value2, "ansTypeName");
            return (Criteria) this;
        }

        public Criteria andAnsTypeNameNotBetween(String value1, String value2) {
            addCriterion("ANS_TYPE_NAME not between", value1, value2, "ansTypeName");
            return (Criteria) this;
        }

        public Criteria andAnsDirIsNull() {
            addCriterion("ANS_DIR is null");
            return (Criteria) this;
        }

        public Criteria andAnsDirIsNotNull() {
            addCriterion("ANS_DIR is not null");
            return (Criteria) this;
        }

        public Criteria andAnsDirEqualTo(String value) {
            addCriterion("ANS_DIR =", value, "ansDir");
            return (Criteria) this;
        }

        public Criteria andAnsDirNotEqualTo(String value) {
            addCriterion("ANS_DIR <>", value, "ansDir");
            return (Criteria) this;
        }

        public Criteria andAnsDirGreaterThan(String value) {
            addCriterion("ANS_DIR >", value, "ansDir");
            return (Criteria) this;
        }

        public Criteria andAnsDirGreaterThanOrEqualTo(String value) {
            addCriterion("ANS_DIR >=", value, "ansDir");
            return (Criteria) this;
        }

        public Criteria andAnsDirLessThan(String value) {
            addCriterion("ANS_DIR <", value, "ansDir");
            return (Criteria) this;
        }

        public Criteria andAnsDirLessThanOrEqualTo(String value) {
            addCriterion("ANS_DIR <=", value, "ansDir");
            return (Criteria) this;
        }

        public Criteria andAnsDirLike(String value) {
            addCriterion("ANS_DIR like", value, "ansDir");
            return (Criteria) this;
        }

        public Criteria andAnsDirNotLike(String value) {
            addCriterion("ANS_DIR not like", value, "ansDir");
            return (Criteria) this;
        }

        public Criteria andAnsDirIn(List<String> values) {
            addCriterion("ANS_DIR in", values, "ansDir");
            return (Criteria) this;
        }

        public Criteria andAnsDirNotIn(List<String> values) {
            addCriterion("ANS_DIR not in", values, "ansDir");
            return (Criteria) this;
        }

        public Criteria andAnsDirBetween(String value1, String value2) {
            addCriterion("ANS_DIR between", value1, value2, "ansDir");
            return (Criteria) this;
        }

        public Criteria andAnsDirNotBetween(String value1, String value2) {
            addCriterion("ANS_DIR not between", value1, value2, "ansDir");
            return (Criteria) this;
        }

        public Criteria andAnsGradeIsNull() {
            addCriterion("ANS_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andAnsGradeIsNotNull() {
            addCriterion("ANS_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andAnsGradeEqualTo(String value) {
            addCriterion("ANS_GRADE =", value, "ansGrade");
            return (Criteria) this;
        }

        public Criteria andAnsGradeNotEqualTo(String value) {
            addCriterion("ANS_GRADE <>", value, "ansGrade");
            return (Criteria) this;
        }

        public Criteria andAnsGradeGreaterThan(String value) {
            addCriterion("ANS_GRADE >", value, "ansGrade");
            return (Criteria) this;
        }

        public Criteria andAnsGradeGreaterThanOrEqualTo(String value) {
            addCriterion("ANS_GRADE >=", value, "ansGrade");
            return (Criteria) this;
        }

        public Criteria andAnsGradeLessThan(String value) {
            addCriterion("ANS_GRADE <", value, "ansGrade");
            return (Criteria) this;
        }

        public Criteria andAnsGradeLessThanOrEqualTo(String value) {
            addCriterion("ANS_GRADE <=", value, "ansGrade");
            return (Criteria) this;
        }

        public Criteria andAnsGradeLike(String value) {
            addCriterion("ANS_GRADE like", value, "ansGrade");
            return (Criteria) this;
        }

        public Criteria andAnsGradeNotLike(String value) {
            addCriterion("ANS_GRADE not like", value, "ansGrade");
            return (Criteria) this;
        }

        public Criteria andAnsGradeIn(List<String> values) {
            addCriterion("ANS_GRADE in", values, "ansGrade");
            return (Criteria) this;
        }

        public Criteria andAnsGradeNotIn(List<String> values) {
            addCriterion("ANS_GRADE not in", values, "ansGrade");
            return (Criteria) this;
        }

        public Criteria andAnsGradeBetween(String value1, String value2) {
            addCriterion("ANS_GRADE between", value1, value2, "ansGrade");
            return (Criteria) this;
        }

        public Criteria andAnsGradeNotBetween(String value1, String value2) {
            addCriterion("ANS_GRADE not between", value1, value2, "ansGrade");
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