package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ExamQuestionBookExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamQuestionBookExample() {
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

        public Criteria andQuestionBookFlowIsNull() {
            addCriterion("QUESTION_BOOK_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andQuestionBookFlowIsNotNull() {
            addCriterion("QUESTION_BOOK_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionBookFlowEqualTo(String value) {
            addCriterion("QUESTION_BOOK_FLOW =", value, "questionBookFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionBookFlowNotEqualTo(String value) {
            addCriterion("QUESTION_BOOK_FLOW <>", value, "questionBookFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionBookFlowGreaterThan(String value) {
            addCriterion("QUESTION_BOOK_FLOW >", value, "questionBookFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionBookFlowGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTION_BOOK_FLOW >=", value, "questionBookFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionBookFlowLessThan(String value) {
            addCriterion("QUESTION_BOOK_FLOW <", value, "questionBookFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionBookFlowLessThanOrEqualTo(String value) {
            addCriterion("QUESTION_BOOK_FLOW <=", value, "questionBookFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionBookFlowLike(String value) {
            addCriterion("QUESTION_BOOK_FLOW like", value, "questionBookFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionBookFlowNotLike(String value) {
            addCriterion("QUESTION_BOOK_FLOW not like", value, "questionBookFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionBookFlowIn(List<String> values) {
            addCriterion("QUESTION_BOOK_FLOW in", values, "questionBookFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionBookFlowNotIn(List<String> values) {
            addCriterion("QUESTION_BOOK_FLOW not in", values, "questionBookFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionBookFlowBetween(String value1, String value2) {
            addCriterion("QUESTION_BOOK_FLOW between", value1, value2, "questionBookFlow");
            return (Criteria) this;
        }

        public Criteria andQuestionBookFlowNotBetween(String value1, String value2) {
            addCriterion("QUESTION_BOOK_FLOW not between", value1, value2, "questionBookFlow");
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

        public Criteria andQuestionCodeIsNull() {
            addCriterion("QUESTION_CODE is null");
            return (Criteria) this;
        }

        public Criteria andQuestionCodeIsNotNull() {
            addCriterion("QUESTION_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionCodeEqualTo(String value) {
            addCriterion("QUESTION_CODE =", value, "questionCode");
            return (Criteria) this;
        }

        public Criteria andQuestionCodeNotEqualTo(String value) {
            addCriterion("QUESTION_CODE <>", value, "questionCode");
            return (Criteria) this;
        }

        public Criteria andQuestionCodeGreaterThan(String value) {
            addCriterion("QUESTION_CODE >", value, "questionCode");
            return (Criteria) this;
        }

        public Criteria andQuestionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTION_CODE >=", value, "questionCode");
            return (Criteria) this;
        }

        public Criteria andQuestionCodeLessThan(String value) {
            addCriterion("QUESTION_CODE <", value, "questionCode");
            return (Criteria) this;
        }

        public Criteria andQuestionCodeLessThanOrEqualTo(String value) {
            addCriterion("QUESTION_CODE <=", value, "questionCode");
            return (Criteria) this;
        }

        public Criteria andQuestionCodeLike(String value) {
            addCriterion("QUESTION_CODE like", value, "questionCode");
            return (Criteria) this;
        }

        public Criteria andQuestionCodeNotLike(String value) {
            addCriterion("QUESTION_CODE not like", value, "questionCode");
            return (Criteria) this;
        }

        public Criteria andQuestionCodeIn(List<String> values) {
            addCriterion("QUESTION_CODE in", values, "questionCode");
            return (Criteria) this;
        }

        public Criteria andQuestionCodeNotIn(List<String> values) {
            addCriterion("QUESTION_CODE not in", values, "questionCode");
            return (Criteria) this;
        }

        public Criteria andQuestionCodeBetween(String value1, String value2) {
            addCriterion("QUESTION_CODE between", value1, value2, "questionCode");
            return (Criteria) this;
        }

        public Criteria andQuestionCodeNotBetween(String value1, String value2) {
            addCriterion("QUESTION_CODE not between", value1, value2, "questionCode");
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

        public Criteria andBookNameIsNull() {
            addCriterion("BOOK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBookNameIsNotNull() {
            addCriterion("BOOK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBookNameEqualTo(String value) {
            addCriterion("BOOK_NAME =", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameNotEqualTo(String value) {
            addCriterion("BOOK_NAME <>", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameGreaterThan(String value) {
            addCriterion("BOOK_NAME >", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameGreaterThanOrEqualTo(String value) {
            addCriterion("BOOK_NAME >=", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameLessThan(String value) {
            addCriterion("BOOK_NAME <", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameLessThanOrEqualTo(String value) {
            addCriterion("BOOK_NAME <=", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameLike(String value) {
            addCriterion("BOOK_NAME like", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameNotLike(String value) {
            addCriterion("BOOK_NAME not like", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameIn(List<String> values) {
            addCriterion("BOOK_NAME in", values, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameNotIn(List<String> values) {
            addCriterion("BOOK_NAME not in", values, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameBetween(String value1, String value2) {
            addCriterion("BOOK_NAME between", value1, value2, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameNotBetween(String value1, String value2) {
            addCriterion("BOOK_NAME not between", value1, value2, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookMemoIsNull() {
            addCriterion("BOOK_MEMO is null");
            return (Criteria) this;
        }

        public Criteria andBookMemoIsNotNull() {
            addCriterion("BOOK_MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andBookMemoEqualTo(String value) {
            addCriterion("BOOK_MEMO =", value, "bookMemo");
            return (Criteria) this;
        }

        public Criteria andBookMemoNotEqualTo(String value) {
            addCriterion("BOOK_MEMO <>", value, "bookMemo");
            return (Criteria) this;
        }

        public Criteria andBookMemoGreaterThan(String value) {
            addCriterion("BOOK_MEMO >", value, "bookMemo");
            return (Criteria) this;
        }

        public Criteria andBookMemoGreaterThanOrEqualTo(String value) {
            addCriterion("BOOK_MEMO >=", value, "bookMemo");
            return (Criteria) this;
        }

        public Criteria andBookMemoLessThan(String value) {
            addCriterion("BOOK_MEMO <", value, "bookMemo");
            return (Criteria) this;
        }

        public Criteria andBookMemoLessThanOrEqualTo(String value) {
            addCriterion("BOOK_MEMO <=", value, "bookMemo");
            return (Criteria) this;
        }

        public Criteria andBookMemoLike(String value) {
            addCriterion("BOOK_MEMO like", value, "bookMemo");
            return (Criteria) this;
        }

        public Criteria andBookMemoNotLike(String value) {
            addCriterion("BOOK_MEMO not like", value, "bookMemo");
            return (Criteria) this;
        }

        public Criteria andBookMemoIn(List<String> values) {
            addCriterion("BOOK_MEMO in", values, "bookMemo");
            return (Criteria) this;
        }

        public Criteria andBookMemoNotIn(List<String> values) {
            addCriterion("BOOK_MEMO not in", values, "bookMemo");
            return (Criteria) this;
        }

        public Criteria andBookMemoBetween(String value1, String value2) {
            addCriterion("BOOK_MEMO between", value1, value2, "bookMemo");
            return (Criteria) this;
        }

        public Criteria andBookMemoNotBetween(String value1, String value2) {
            addCriterion("BOOK_MEMO not between", value1, value2, "bookMemo");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowIsNull() {
            addCriterion("SUBJECT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowIsNotNull() {
            addCriterion("SUBJECT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowEqualTo(String value) {
            addCriterion("SUBJECT_FLOW =", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotEqualTo(String value) {
            addCriterion("SUBJECT_FLOW <>", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowGreaterThan(String value) {
            addCriterion("SUBJECT_FLOW >", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_FLOW >=", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowLessThan(String value) {
            addCriterion("SUBJECT_FLOW <", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_FLOW <=", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowLike(String value) {
            addCriterion("SUBJECT_FLOW like", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotLike(String value) {
            addCriterion("SUBJECT_FLOW not like", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowIn(List<String> values) {
            addCriterion("SUBJECT_FLOW in", values, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotIn(List<String> values) {
            addCriterion("SUBJECT_FLOW not in", values, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowBetween(String value1, String value2) {
            addCriterion("SUBJECT_FLOW between", value1, value2, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_FLOW not between", value1, value2, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSunjectNameIsNull() {
            addCriterion("SUNJECT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSunjectNameIsNotNull() {
            addCriterion("SUNJECT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSunjectNameEqualTo(String value) {
            addCriterion("SUNJECT_NAME =", value, "sunjectName");
            return (Criteria) this;
        }

        public Criteria andSunjectNameNotEqualTo(String value) {
            addCriterion("SUNJECT_NAME <>", value, "sunjectName");
            return (Criteria) this;
        }

        public Criteria andSunjectNameGreaterThan(String value) {
            addCriterion("SUNJECT_NAME >", value, "sunjectName");
            return (Criteria) this;
        }

        public Criteria andSunjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("SUNJECT_NAME >=", value, "sunjectName");
            return (Criteria) this;
        }

        public Criteria andSunjectNameLessThan(String value) {
            addCriterion("SUNJECT_NAME <", value, "sunjectName");
            return (Criteria) this;
        }

        public Criteria andSunjectNameLessThanOrEqualTo(String value) {
            addCriterion("SUNJECT_NAME <=", value, "sunjectName");
            return (Criteria) this;
        }

        public Criteria andSunjectNameLike(String value) {
            addCriterion("SUNJECT_NAME like", value, "sunjectName");
            return (Criteria) this;
        }

        public Criteria andSunjectNameNotLike(String value) {
            addCriterion("SUNJECT_NAME not like", value, "sunjectName");
            return (Criteria) this;
        }

        public Criteria andSunjectNameIn(List<String> values) {
            addCriterion("SUNJECT_NAME in", values, "sunjectName");
            return (Criteria) this;
        }

        public Criteria andSunjectNameNotIn(List<String> values) {
            addCriterion("SUNJECT_NAME not in", values, "sunjectName");
            return (Criteria) this;
        }

        public Criteria andSunjectNameBetween(String value1, String value2) {
            addCriterion("SUNJECT_NAME between", value1, value2, "sunjectName");
            return (Criteria) this;
        }

        public Criteria andSunjectNameNotBetween(String value1, String value2) {
            addCriterion("SUNJECT_NAME not between", value1, value2, "sunjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectMemoIsNull() {
            addCriterion("SUBJECT_MEMO is null");
            return (Criteria) this;
        }

        public Criteria andSubjectMemoIsNotNull() {
            addCriterion("SUBJECT_MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectMemoEqualTo(String value) {
            addCriterion("SUBJECT_MEMO =", value, "subjectMemo");
            return (Criteria) this;
        }

        public Criteria andSubjectMemoNotEqualTo(String value) {
            addCriterion("SUBJECT_MEMO <>", value, "subjectMemo");
            return (Criteria) this;
        }

        public Criteria andSubjectMemoGreaterThan(String value) {
            addCriterion("SUBJECT_MEMO >", value, "subjectMemo");
            return (Criteria) this;
        }

        public Criteria andSubjectMemoGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_MEMO >=", value, "subjectMemo");
            return (Criteria) this;
        }

        public Criteria andSubjectMemoLessThan(String value) {
            addCriterion("SUBJECT_MEMO <", value, "subjectMemo");
            return (Criteria) this;
        }

        public Criteria andSubjectMemoLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_MEMO <=", value, "subjectMemo");
            return (Criteria) this;
        }

        public Criteria andSubjectMemoLike(String value) {
            addCriterion("SUBJECT_MEMO like", value, "subjectMemo");
            return (Criteria) this;
        }

        public Criteria andSubjectMemoNotLike(String value) {
            addCriterion("SUBJECT_MEMO not like", value, "subjectMemo");
            return (Criteria) this;
        }

        public Criteria andSubjectMemoIn(List<String> values) {
            addCriterion("SUBJECT_MEMO in", values, "subjectMemo");
            return (Criteria) this;
        }

        public Criteria andSubjectMemoNotIn(List<String> values) {
            addCriterion("SUBJECT_MEMO not in", values, "subjectMemo");
            return (Criteria) this;
        }

        public Criteria andSubjectMemoBetween(String value1, String value2) {
            addCriterion("SUBJECT_MEMO between", value1, value2, "subjectMemo");
            return (Criteria) this;
        }

        public Criteria andSubjectMemoNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_MEMO not between", value1, value2, "subjectMemo");
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