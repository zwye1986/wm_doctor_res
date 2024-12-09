package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ExamBookImpDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamBookImpDetailExample() {
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

        public Criteria andImpDetailFlowIsNull() {
            addCriterion("IMP_DETAIL_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andImpDetailFlowIsNotNull() {
            addCriterion("IMP_DETAIL_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andImpDetailFlowEqualTo(String value) {
            addCriterion("IMP_DETAIL_FLOW =", value, "impDetailFlow");
            return (Criteria) this;
        }

        public Criteria andImpDetailFlowNotEqualTo(String value) {
            addCriterion("IMP_DETAIL_FLOW <>", value, "impDetailFlow");
            return (Criteria) this;
        }

        public Criteria andImpDetailFlowGreaterThan(String value) {
            addCriterion("IMP_DETAIL_FLOW >", value, "impDetailFlow");
            return (Criteria) this;
        }

        public Criteria andImpDetailFlowGreaterThanOrEqualTo(String value) {
            addCriterion("IMP_DETAIL_FLOW >=", value, "impDetailFlow");
            return (Criteria) this;
        }

        public Criteria andImpDetailFlowLessThan(String value) {
            addCriterion("IMP_DETAIL_FLOW <", value, "impDetailFlow");
            return (Criteria) this;
        }

        public Criteria andImpDetailFlowLessThanOrEqualTo(String value) {
            addCriterion("IMP_DETAIL_FLOW <=", value, "impDetailFlow");
            return (Criteria) this;
        }

        public Criteria andImpDetailFlowLike(String value) {
            addCriterion("IMP_DETAIL_FLOW like", value, "impDetailFlow");
            return (Criteria) this;
        }

        public Criteria andImpDetailFlowNotLike(String value) {
            addCriterion("IMP_DETAIL_FLOW not like", value, "impDetailFlow");
            return (Criteria) this;
        }

        public Criteria andImpDetailFlowIn(List<String> values) {
            addCriterion("IMP_DETAIL_FLOW in", values, "impDetailFlow");
            return (Criteria) this;
        }

        public Criteria andImpDetailFlowNotIn(List<String> values) {
            addCriterion("IMP_DETAIL_FLOW not in", values, "impDetailFlow");
            return (Criteria) this;
        }

        public Criteria andImpDetailFlowBetween(String value1, String value2) {
            addCriterion("IMP_DETAIL_FLOW between", value1, value2, "impDetailFlow");
            return (Criteria) this;
        }

        public Criteria andImpDetailFlowNotBetween(String value1, String value2) {
            addCriterion("IMP_DETAIL_FLOW not between", value1, value2, "impDetailFlow");
            return (Criteria) this;
        }

        public Criteria andBookImpFlowIsNull() {
            addCriterion("BOOK_IMP_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andBookImpFlowIsNotNull() {
            addCriterion("BOOK_IMP_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andBookImpFlowEqualTo(String value) {
            addCriterion("BOOK_IMP_FLOW =", value, "bookImpFlow");
            return (Criteria) this;
        }

        public Criteria andBookImpFlowNotEqualTo(String value) {
            addCriterion("BOOK_IMP_FLOW <>", value, "bookImpFlow");
            return (Criteria) this;
        }

        public Criteria andBookImpFlowGreaterThan(String value) {
            addCriterion("BOOK_IMP_FLOW >", value, "bookImpFlow");
            return (Criteria) this;
        }

        public Criteria andBookImpFlowGreaterThanOrEqualTo(String value) {
            addCriterion("BOOK_IMP_FLOW >=", value, "bookImpFlow");
            return (Criteria) this;
        }

        public Criteria andBookImpFlowLessThan(String value) {
            addCriterion("BOOK_IMP_FLOW <", value, "bookImpFlow");
            return (Criteria) this;
        }

        public Criteria andBookImpFlowLessThanOrEqualTo(String value) {
            addCriterion("BOOK_IMP_FLOW <=", value, "bookImpFlow");
            return (Criteria) this;
        }

        public Criteria andBookImpFlowLike(String value) {
            addCriterion("BOOK_IMP_FLOW like", value, "bookImpFlow");
            return (Criteria) this;
        }

        public Criteria andBookImpFlowNotLike(String value) {
            addCriterion("BOOK_IMP_FLOW not like", value, "bookImpFlow");
            return (Criteria) this;
        }

        public Criteria andBookImpFlowIn(List<String> values) {
            addCriterion("BOOK_IMP_FLOW in", values, "bookImpFlow");
            return (Criteria) this;
        }

        public Criteria andBookImpFlowNotIn(List<String> values) {
            addCriterion("BOOK_IMP_FLOW not in", values, "bookImpFlow");
            return (Criteria) this;
        }

        public Criteria andBookImpFlowBetween(String value1, String value2) {
            addCriterion("BOOK_IMP_FLOW between", value1, value2, "bookImpFlow");
            return (Criteria) this;
        }

        public Criteria andBookImpFlowNotBetween(String value1, String value2) {
            addCriterion("BOOK_IMP_FLOW not between", value1, value2, "bookImpFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdIsNull() {
            addCriterion("QUESTION_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdIsNotNull() {
            addCriterion("QUESTION_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdEqualTo(String value) {
            addCriterion("QUESTION_TYPE_ID =", value, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdNotEqualTo(String value) {
            addCriterion("QUESTION_TYPE_ID <>", value, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdGreaterThan(String value) {
            addCriterion("QUESTION_TYPE_ID >", value, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTION_TYPE_ID >=", value, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdLessThan(String value) {
            addCriterion("QUESTION_TYPE_ID <", value, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdLessThanOrEqualTo(String value) {
            addCriterion("QUESTION_TYPE_ID <=", value, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdLike(String value) {
            addCriterion("QUESTION_TYPE_ID like", value, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdNotLike(String value) {
            addCriterion("QUESTION_TYPE_ID not like", value, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdIn(List<String> values) {
            addCriterion("QUESTION_TYPE_ID in", values, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdNotIn(List<String> values) {
            addCriterion("QUESTION_TYPE_ID not in", values, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdBetween(String value1, String value2) {
            addCriterion("QUESTION_TYPE_ID between", value1, value2, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIdNotBetween(String value1, String value2) {
            addCriterion("QUESTION_TYPE_ID not between", value1, value2, "questionTypeId");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNameIsNull() {
            addCriterion("QUESTION_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNameIsNotNull() {
            addCriterion("QUESTION_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNameEqualTo(String value) {
            addCriterion("QUESTION_TYPE_NAME =", value, "questionTypeName");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNameNotEqualTo(String value) {
            addCriterion("QUESTION_TYPE_NAME <>", value, "questionTypeName");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNameGreaterThan(String value) {
            addCriterion("QUESTION_TYPE_NAME >", value, "questionTypeName");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTION_TYPE_NAME >=", value, "questionTypeName");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNameLessThan(String value) {
            addCriterion("QUESTION_TYPE_NAME <", value, "questionTypeName");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNameLessThanOrEqualTo(String value) {
            addCriterion("QUESTION_TYPE_NAME <=", value, "questionTypeName");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNameLike(String value) {
            addCriterion("QUESTION_TYPE_NAME like", value, "questionTypeName");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNameNotLike(String value) {
            addCriterion("QUESTION_TYPE_NAME not like", value, "questionTypeName");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNameIn(List<String> values) {
            addCriterion("QUESTION_TYPE_NAME in", values, "questionTypeName");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNameNotIn(List<String> values) {
            addCriterion("QUESTION_TYPE_NAME not in", values, "questionTypeName");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNameBetween(String value1, String value2) {
            addCriterion("QUESTION_TYPE_NAME between", value1, value2, "questionTypeName");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNameNotBetween(String value1, String value2) {
            addCriterion("QUESTION_TYPE_NAME not between", value1, value2, "questionTypeName");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("MEMO is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("MEMO =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("MEMO <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("MEMO >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("MEMO >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("MEMO <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("MEMO <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("MEMO like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("MEMO not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("MEMO in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("MEMO not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("MEMO between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("MEMO not between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andQuestionNumIsNull() {
            addCriterion("QUESTION_NUM is null");
            return (Criteria) this;
        }

        public Criteria andQuestionNumIsNotNull() {
            addCriterion("QUESTION_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionNumEqualTo(Integer value) {
            addCriterion("QUESTION_NUM =", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumNotEqualTo(Integer value) {
            addCriterion("QUESTION_NUM <>", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumGreaterThan(Integer value) {
            addCriterion("QUESTION_NUM >", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("QUESTION_NUM >=", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumLessThan(Integer value) {
            addCriterion("QUESTION_NUM <", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumLessThanOrEqualTo(Integer value) {
            addCriterion("QUESTION_NUM <=", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumIn(List<Integer> values) {
            addCriterion("QUESTION_NUM in", values, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumNotIn(List<Integer> values) {
            addCriterion("QUESTION_NUM not in", values, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumBetween(Integer value1, Integer value2) {
            addCriterion("QUESTION_NUM between", value1, value2, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumNotBetween(Integer value1, Integer value2) {
            addCriterion("QUESTION_NUM not between", value1, value2, "questionNum");
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

        public Criteria andBookFlowIsNull() {
            addCriterion("BOOK_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andBookFlowIsNotNull() {
            addCriterion("BOOK_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andBookFlowEqualTo(String value) {
            addCriterion("BOOK_FLOW =", value, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowNotEqualTo(String value) {
            addCriterion("BOOK_FLOW <>", value, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowGreaterThan(String value) {
            addCriterion("BOOK_FLOW >", value, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowGreaterThanOrEqualTo(String value) {
            addCriterion("BOOK_FLOW >=", value, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowLessThan(String value) {
            addCriterion("BOOK_FLOW <", value, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowLessThanOrEqualTo(String value) {
            addCriterion("BOOK_FLOW <=", value, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowLike(String value) {
            addCriterion("BOOK_FLOW like", value, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowNotLike(String value) {
            addCriterion("BOOK_FLOW not like", value, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowIn(List<String> values) {
            addCriterion("BOOK_FLOW in", values, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowNotIn(List<String> values) {
            addCriterion("BOOK_FLOW not in", values, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowBetween(String value1, String value2) {
            addCriterion("BOOK_FLOW between", value1, value2, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowNotBetween(String value1, String value2) {
            addCriterion("BOOK_FLOW not between", value1, value2, "bookFlow");
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