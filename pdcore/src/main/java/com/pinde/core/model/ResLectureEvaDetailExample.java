package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResLectureEvaDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResLectureEvaDetailExample() {
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

        public Criteria andRecordFlowIsNull() {
            addCriterion("RECORD_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRecordFlowIsNotNull() {
            addCriterion("RECORD_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRecordFlowEqualTo(String value) {
            addCriterion("RECORD_FLOW =", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotEqualTo(String value) {
            addCriterion("RECORD_FLOW <>", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowGreaterThan(String value) {
            addCriterion("RECORD_FLOW >", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD_FLOW >=", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowLessThan(String value) {
            addCriterion("RECORD_FLOW <", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowLessThanOrEqualTo(String value) {
            addCriterion("RECORD_FLOW <=", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowLike(String value) {
            addCriterion("RECORD_FLOW like", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotLike(String value) {
            addCriterion("RECORD_FLOW not like", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowIn(List<String> values) {
            addCriterion("RECORD_FLOW in", values, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotIn(List<String> values) {
            addCriterion("RECORD_FLOW not in", values, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowBetween(String value1, String value2) {
            addCriterion("RECORD_FLOW between", value1, value2, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotBetween(String value1, String value2) {
            addCriterion("RECORD_FLOW not between", value1, value2, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowIsNull() {
            addCriterion("LECTURE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andLectureFlowIsNotNull() {
            addCriterion("LECTURE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andLectureFlowEqualTo(String value) {
            addCriterion("LECTURE_FLOW =", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowNotEqualTo(String value) {
            addCriterion("LECTURE_FLOW <>", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowGreaterThan(String value) {
            addCriterion("LECTURE_FLOW >", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_FLOW >=", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowLessThan(String value) {
            addCriterion("LECTURE_FLOW <", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_FLOW <=", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowLike(String value) {
            addCriterion("LECTURE_FLOW like", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowNotLike(String value) {
            addCriterion("LECTURE_FLOW not like", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowIn(List<String> values) {
            addCriterion("LECTURE_FLOW in", values, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowNotIn(List<String> values) {
            addCriterion("LECTURE_FLOW not in", values, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowBetween(String value1, String value2) {
            addCriterion("LECTURE_FLOW between", value1, value2, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowNotBetween(String value1, String value2) {
            addCriterion("LECTURE_FLOW not between", value1, value2, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowIsNull() {
            addCriterion("OPER_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowIsNotNull() {
            addCriterion("OPER_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowEqualTo(String value) {
            addCriterion("OPER_USER_FLOW =", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowNotEqualTo(String value) {
            addCriterion("OPER_USER_FLOW <>", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowGreaterThan(String value) {
            addCriterion("OPER_USER_FLOW >", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_USER_FLOW >=", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowLessThan(String value) {
            addCriterion("OPER_USER_FLOW <", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowLessThanOrEqualTo(String value) {
            addCriterion("OPER_USER_FLOW <=", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowLike(String value) {
            addCriterion("OPER_USER_FLOW like", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowNotLike(String value) {
            addCriterion("OPER_USER_FLOW not like", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowIn(List<String> values) {
            addCriterion("OPER_USER_FLOW in", values, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowNotIn(List<String> values) {
            addCriterion("OPER_USER_FLOW not in", values, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowBetween(String value1, String value2) {
            addCriterion("OPER_USER_FLOW between", value1, value2, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowNotBetween(String value1, String value2) {
            addCriterion("OPER_USER_FLOW not between", value1, value2, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserNameIsNull() {
            addCriterion("OPER_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOperUserNameIsNotNull() {
            addCriterion("OPER_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOperUserNameEqualTo(String value) {
            addCriterion("OPER_USER_NAME =", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotEqualTo(String value) {
            addCriterion("OPER_USER_NAME <>", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameGreaterThan(String value) {
            addCriterion("OPER_USER_NAME >", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_USER_NAME >=", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameLessThan(String value) {
            addCriterion("OPER_USER_NAME <", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameLessThanOrEqualTo(String value) {
            addCriterion("OPER_USER_NAME <=", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameLike(String value) {
            addCriterion("OPER_USER_NAME like", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotLike(String value) {
            addCriterion("OPER_USER_NAME not like", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameIn(List<String> values) {
            addCriterion("OPER_USER_NAME in", values, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotIn(List<String> values) {
            addCriterion("OPER_USER_NAME not in", values, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameBetween(String value1, String value2) {
            addCriterion("OPER_USER_NAME between", value1, value2, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotBetween(String value1, String value2) {
            addCriterion("OPER_USER_NAME not between", value1, value2, "operUserName");
            return (Criteria) this;
        }

        public Criteria andEvaContentIsNull() {
            addCriterion("EVA_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andEvaContentIsNotNull() {
            addCriterion("EVA_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andEvaContentEqualTo(String value) {
            addCriterion("EVA_CONTENT =", value, "evaContent");
            return (Criteria) this;
        }

        public Criteria andEvaContentNotEqualTo(String value) {
            addCriterion("EVA_CONTENT <>", value, "evaContent");
            return (Criteria) this;
        }

        public Criteria andEvaContentGreaterThan(String value) {
            addCriterion("EVA_CONTENT >", value, "evaContent");
            return (Criteria) this;
        }

        public Criteria andEvaContentGreaterThanOrEqualTo(String value) {
            addCriterion("EVA_CONTENT >=", value, "evaContent");
            return (Criteria) this;
        }

        public Criteria andEvaContentLessThan(String value) {
            addCriterion("EVA_CONTENT <", value, "evaContent");
            return (Criteria) this;
        }

        public Criteria andEvaContentLessThanOrEqualTo(String value) {
            addCriterion("EVA_CONTENT <=", value, "evaContent");
            return (Criteria) this;
        }

        public Criteria andEvaContentLike(String value) {
            addCriterion("EVA_CONTENT like", value, "evaContent");
            return (Criteria) this;
        }

        public Criteria andEvaContentNotLike(String value) {
            addCriterion("EVA_CONTENT not like", value, "evaContent");
            return (Criteria) this;
        }

        public Criteria andEvaContentIn(List<String> values) {
            addCriterion("EVA_CONTENT in", values, "evaContent");
            return (Criteria) this;
        }

        public Criteria andEvaContentNotIn(List<String> values) {
            addCriterion("EVA_CONTENT not in", values, "evaContent");
            return (Criteria) this;
        }

        public Criteria andEvaContentBetween(String value1, String value2) {
            addCriterion("EVA_CONTENT between", value1, value2, "evaContent");
            return (Criteria) this;
        }

        public Criteria andEvaContentNotBetween(String value1, String value2) {
            addCriterion("EVA_CONTENT not between", value1, value2, "evaContent");
            return (Criteria) this;
        }

        public Criteria andEvaScoreIsNull() {
            addCriterion("EVA_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andEvaScoreIsNotNull() {
            addCriterion("EVA_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andEvaScoreEqualTo(String value) {
            addCriterion("EVA_SCORE =", value, "evaScore");
            return (Criteria) this;
        }

        public Criteria andEvaScoreNotEqualTo(String value) {
            addCriterion("EVA_SCORE <>", value, "evaScore");
            return (Criteria) this;
        }

        public Criteria andEvaScoreGreaterThan(String value) {
            addCriterion("EVA_SCORE >", value, "evaScore");
            return (Criteria) this;
        }

        public Criteria andEvaScoreGreaterThanOrEqualTo(String value) {
            addCriterion("EVA_SCORE >=", value, "evaScore");
            return (Criteria) this;
        }

        public Criteria andEvaScoreLessThan(String value) {
            addCriterion("EVA_SCORE <", value, "evaScore");
            return (Criteria) this;
        }

        public Criteria andEvaScoreLessThanOrEqualTo(String value) {
            addCriterion("EVA_SCORE <=", value, "evaScore");
            return (Criteria) this;
        }

        public Criteria andEvaScoreLike(String value) {
            addCriterion("EVA_SCORE like", value, "evaScore");
            return (Criteria) this;
        }

        public Criteria andEvaScoreNotLike(String value) {
            addCriterion("EVA_SCORE not like", value, "evaScore");
            return (Criteria) this;
        }

        public Criteria andEvaScoreIn(List<String> values) {
            addCriterion("EVA_SCORE in", values, "evaScore");
            return (Criteria) this;
        }

        public Criteria andEvaScoreNotIn(List<String> values) {
            addCriterion("EVA_SCORE not in", values, "evaScore");
            return (Criteria) this;
        }

        public Criteria andEvaScoreBetween(String value1, String value2) {
            addCriterion("EVA_SCORE between", value1, value2, "evaScore");
            return (Criteria) this;
        }

        public Criteria andEvaScoreNotBetween(String value1, String value2) {
            addCriterion("EVA_SCORE not between", value1, value2, "evaScore");
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

        public Criteria andEvalFormScoreIsNull() {
            addCriterion("EVAL_FORM_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andEvalFormScoreIsNotNull() {
            addCriterion("EVAL_FORM_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andEvalFormScoreEqualTo(String value) {
            addCriterion("EVAL_FORM_SCORE =", value, "evalFormScore");
            return (Criteria) this;
        }

        public Criteria andEvalFormScoreNotEqualTo(String value) {
            addCriterion("EVAL_FORM_SCORE <>", value, "evalFormScore");
            return (Criteria) this;
        }

        public Criteria andEvalFormScoreGreaterThan(String value) {
            addCriterion("EVAL_FORM_SCORE >", value, "evalFormScore");
            return (Criteria) this;
        }

        public Criteria andEvalFormScoreGreaterThanOrEqualTo(String value) {
            addCriterion("EVAL_FORM_SCORE >=", value, "evalFormScore");
            return (Criteria) this;
        }

        public Criteria andEvalFormScoreLessThan(String value) {
            addCriterion("EVAL_FORM_SCORE <", value, "evalFormScore");
            return (Criteria) this;
        }

        public Criteria andEvalFormScoreLessThanOrEqualTo(String value) {
            addCriterion("EVAL_FORM_SCORE <=", value, "evalFormScore");
            return (Criteria) this;
        }

        public Criteria andEvalFormScoreLike(String value) {
            addCriterion("EVAL_FORM_SCORE like", value, "evalFormScore");
            return (Criteria) this;
        }

        public Criteria andEvalFormScoreNotLike(String value) {
            addCriterion("EVAL_FORM_SCORE not like", value, "evalFormScore");
            return (Criteria) this;
        }

        public Criteria andEvalFormScoreIn(List<String> values) {
            addCriterion("EVAL_FORM_SCORE in", values, "evalFormScore");
            return (Criteria) this;
        }

        public Criteria andEvalFormScoreNotIn(List<String> values) {
            addCriterion("EVAL_FORM_SCORE not in", values, "evalFormScore");
            return (Criteria) this;
        }

        public Criteria andEvalFormScoreBetween(String value1, String value2) {
            addCriterion("EVAL_FORM_SCORE between", value1, value2, "evalFormScore");
            return (Criteria) this;
        }

        public Criteria andEvalFormScoreNotBetween(String value1, String value2) {
            addCriterion("EVAL_FORM_SCORE not between", value1, value2, "evalFormScore");
            return (Criteria) this;
        }

        public Criteria andFileFlowIsNull() {
            addCriterion("FILE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFileFlowIsNotNull() {
            addCriterion("FILE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFileFlowEqualTo(String value) {
            addCriterion("FILE_FLOW =", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowNotEqualTo(String value) {
            addCriterion("FILE_FLOW <>", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowGreaterThan(String value) {
            addCriterion("FILE_FLOW >", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_FLOW >=", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowLessThan(String value) {
            addCriterion("FILE_FLOW <", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowLessThanOrEqualTo(String value) {
            addCriterion("FILE_FLOW <=", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowLike(String value) {
            addCriterion("FILE_FLOW like", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowNotLike(String value) {
            addCriterion("FILE_FLOW not like", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowIn(List<String> values) {
            addCriterion("FILE_FLOW in", values, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowNotIn(List<String> values) {
            addCriterion("FILE_FLOW not in", values, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowBetween(String value1, String value2) {
            addCriterion("FILE_FLOW between", value1, value2, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowNotBetween(String value1, String value2) {
            addCriterion("FILE_FLOW not between", value1, value2, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andScanRegistFlowIsNull() {
            addCriterion("SCAN_REGIST_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andScanRegistFlowIsNotNull() {
            addCriterion("SCAN_REGIST_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andScanRegistFlowEqualTo(String value) {
            addCriterion("SCAN_REGIST_FLOW =", value, "scanRegistFlow");
            return (Criteria) this;
        }

        public Criteria andScanRegistFlowNotEqualTo(String value) {
            addCriterion("SCAN_REGIST_FLOW <>", value, "scanRegistFlow");
            return (Criteria) this;
        }

        public Criteria andScanRegistFlowGreaterThan(String value) {
            addCriterion("SCAN_REGIST_FLOW >", value, "scanRegistFlow");
            return (Criteria) this;
        }

        public Criteria andScanRegistFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCAN_REGIST_FLOW >=", value, "scanRegistFlow");
            return (Criteria) this;
        }

        public Criteria andScanRegistFlowLessThan(String value) {
            addCriterion("SCAN_REGIST_FLOW <", value, "scanRegistFlow");
            return (Criteria) this;
        }

        public Criteria andScanRegistFlowLessThanOrEqualTo(String value) {
            addCriterion("SCAN_REGIST_FLOW <=", value, "scanRegistFlow");
            return (Criteria) this;
        }

        public Criteria andScanRegistFlowLike(String value) {
            addCriterion("SCAN_REGIST_FLOW like", value, "scanRegistFlow");
            return (Criteria) this;
        }

        public Criteria andScanRegistFlowNotLike(String value) {
            addCriterion("SCAN_REGIST_FLOW not like", value, "scanRegistFlow");
            return (Criteria) this;
        }

        public Criteria andScanRegistFlowIn(List<String> values) {
            addCriterion("SCAN_REGIST_FLOW in", values, "scanRegistFlow");
            return (Criteria) this;
        }

        public Criteria andScanRegistFlowNotIn(List<String> values) {
            addCriterion("SCAN_REGIST_FLOW not in", values, "scanRegistFlow");
            return (Criteria) this;
        }

        public Criteria andScanRegistFlowBetween(String value1, String value2) {
            addCriterion("SCAN_REGIST_FLOW between", value1, value2, "scanRegistFlow");
            return (Criteria) this;
        }

        public Criteria andScanRegistFlowNotBetween(String value1, String value2) {
            addCriterion("SCAN_REGIST_FLOW not between", value1, value2, "scanRegistFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowIsNull() {
            addCriterion("TARGET_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTargetFlowIsNotNull() {
            addCriterion("TARGET_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTargetFlowEqualTo(String value) {
            addCriterion("TARGET_FLOW =", value, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowNotEqualTo(String value) {
            addCriterion("TARGET_FLOW <>", value, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowGreaterThan(String value) {
            addCriterion("TARGET_FLOW >", value, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TARGET_FLOW >=", value, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowLessThan(String value) {
            addCriterion("TARGET_FLOW <", value, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowLessThanOrEqualTo(String value) {
            addCriterion("TARGET_FLOW <=", value, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowLike(String value) {
            addCriterion("TARGET_FLOW like", value, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowNotLike(String value) {
            addCriterion("TARGET_FLOW not like", value, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowIn(List<String> values) {
            addCriterion("TARGET_FLOW in", values, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowNotIn(List<String> values) {
            addCriterion("TARGET_FLOW not in", values, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowBetween(String value1, String value2) {
            addCriterion("TARGET_FLOW between", value1, value2, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetFlowNotBetween(String value1, String value2) {
            addCriterion("TARGET_FLOW not between", value1, value2, "targetFlow");
            return (Criteria) this;
        }

        public Criteria andTargetNameIsNull() {
            addCriterion("TARGET_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTargetNameIsNotNull() {
            addCriterion("TARGET_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTargetNameEqualTo(String value) {
            addCriterion("TARGET_NAME =", value, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameNotEqualTo(String value) {
            addCriterion("TARGET_NAME <>", value, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameGreaterThan(String value) {
            addCriterion("TARGET_NAME >", value, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameGreaterThanOrEqualTo(String value) {
            addCriterion("TARGET_NAME >=", value, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameLessThan(String value) {
            addCriterion("TARGET_NAME <", value, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameLessThanOrEqualTo(String value) {
            addCriterion("TARGET_NAME <=", value, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameLike(String value) {
            addCriterion("TARGET_NAME like", value, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameNotLike(String value) {
            addCriterion("TARGET_NAME not like", value, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameIn(List<String> values) {
            addCriterion("TARGET_NAME in", values, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameNotIn(List<String> values) {
            addCriterion("TARGET_NAME not in", values, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameBetween(String value1, String value2) {
            addCriterion("TARGET_NAME between", value1, value2, "targetName");
            return (Criteria) this;
        }

        public Criteria andTargetNameNotBetween(String value1, String value2) {
            addCriterion("TARGET_NAME not between", value1, value2, "targetName");
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