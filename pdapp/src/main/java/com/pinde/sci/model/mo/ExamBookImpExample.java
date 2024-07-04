package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ExamBookImpExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamBookImpExample() {
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

        public Criteria andStatusIdIsNull() {
            addCriterion("STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNotNull() {
            addCriterion("STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStatusIdEqualTo(String value) {
            addCriterion("STATUS_ID =", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotEqualTo(String value) {
            addCriterion("STATUS_ID <>", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThan(String value) {
            addCriterion("STATUS_ID >", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_ID >=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThan(String value) {
            addCriterion("STATUS_ID <", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThanOrEqualTo(String value) {
            addCriterion("STATUS_ID <=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLike(String value) {
            addCriterion("STATUS_ID like", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotLike(String value) {
            addCriterion("STATUS_ID not like", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdIn(List<String> values) {
            addCriterion("STATUS_ID in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotIn(List<String> values) {
            addCriterion("STATUS_ID not in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdBetween(String value1, String value2) {
            addCriterion("STATUS_ID between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotBetween(String value1, String value2) {
            addCriterion("STATUS_ID not between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusDescIsNull() {
            addCriterion("STATUS_DESC is null");
            return (Criteria) this;
        }

        public Criteria andStatusDescIsNotNull() {
            addCriterion("STATUS_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andStatusDescEqualTo(String value) {
            addCriterion("STATUS_DESC =", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescNotEqualTo(String value) {
            addCriterion("STATUS_DESC <>", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescGreaterThan(String value) {
            addCriterion("STATUS_DESC >", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_DESC >=", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescLessThan(String value) {
            addCriterion("STATUS_DESC <", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescLessThanOrEqualTo(String value) {
            addCriterion("STATUS_DESC <=", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescLike(String value) {
            addCriterion("STATUS_DESC like", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescNotLike(String value) {
            addCriterion("STATUS_DESC not like", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescIn(List<String> values) {
            addCriterion("STATUS_DESC in", values, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescNotIn(List<String> values) {
            addCriterion("STATUS_DESC not in", values, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescBetween(String value1, String value2) {
            addCriterion("STATUS_DESC between", value1, value2, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescNotBetween(String value1, String value2) {
            addCriterion("STATUS_DESC not between", value1, value2, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowIsNull() {
            addCriterion("IMP_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowIsNotNull() {
            addCriterion("IMP_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowEqualTo(String value) {
            addCriterion("IMP_USER_FLOW =", value, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowNotEqualTo(String value) {
            addCriterion("IMP_USER_FLOW <>", value, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowGreaterThan(String value) {
            addCriterion("IMP_USER_FLOW >", value, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("IMP_USER_FLOW >=", value, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowLessThan(String value) {
            addCriterion("IMP_USER_FLOW <", value, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowLessThanOrEqualTo(String value) {
            addCriterion("IMP_USER_FLOW <=", value, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowLike(String value) {
            addCriterion("IMP_USER_FLOW like", value, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowNotLike(String value) {
            addCriterion("IMP_USER_FLOW not like", value, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowIn(List<String> values) {
            addCriterion("IMP_USER_FLOW in", values, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowNotIn(List<String> values) {
            addCriterion("IMP_USER_FLOW not in", values, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowBetween(String value1, String value2) {
            addCriterion("IMP_USER_FLOW between", value1, value2, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowNotBetween(String value1, String value2) {
            addCriterion("IMP_USER_FLOW not between", value1, value2, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserNameIsNull() {
            addCriterion("IMP_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andImpUserNameIsNotNull() {
            addCriterion("IMP_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andImpUserNameEqualTo(String value) {
            addCriterion("IMP_USER_NAME =", value, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameNotEqualTo(String value) {
            addCriterion("IMP_USER_NAME <>", value, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameGreaterThan(String value) {
            addCriterion("IMP_USER_NAME >", value, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("IMP_USER_NAME >=", value, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameLessThan(String value) {
            addCriterion("IMP_USER_NAME <", value, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameLessThanOrEqualTo(String value) {
            addCriterion("IMP_USER_NAME <=", value, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameLike(String value) {
            addCriterion("IMP_USER_NAME like", value, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameNotLike(String value) {
            addCriterion("IMP_USER_NAME not like", value, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameIn(List<String> values) {
            addCriterion("IMP_USER_NAME in", values, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameNotIn(List<String> values) {
            addCriterion("IMP_USER_NAME not in", values, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameBetween(String value1, String value2) {
            addCriterion("IMP_USER_NAME between", value1, value2, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameNotBetween(String value1, String value2) {
            addCriterion("IMP_USER_NAME not between", value1, value2, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpTimeIsNull() {
            addCriterion("IMP_TIME is null");
            return (Criteria) this;
        }

        public Criteria andImpTimeIsNotNull() {
            addCriterion("IMP_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andImpTimeEqualTo(String value) {
            addCriterion("IMP_TIME =", value, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeNotEqualTo(String value) {
            addCriterion("IMP_TIME <>", value, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeGreaterThan(String value) {
            addCriterion("IMP_TIME >", value, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeGreaterThanOrEqualTo(String value) {
            addCriterion("IMP_TIME >=", value, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeLessThan(String value) {
            addCriterion("IMP_TIME <", value, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeLessThanOrEqualTo(String value) {
            addCriterion("IMP_TIME <=", value, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeLike(String value) {
            addCriterion("IMP_TIME like", value, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeNotLike(String value) {
            addCriterion("IMP_TIME not like", value, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeIn(List<String> values) {
            addCriterion("IMP_TIME in", values, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeNotIn(List<String> values) {
            addCriterion("IMP_TIME not in", values, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeBetween(String value1, String value2) {
            addCriterion("IMP_TIME between", value1, value2, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeNotBetween(String value1, String value2) {
            addCriterion("IMP_TIME not between", value1, value2, "impTime");
            return (Criteria) this;
        }

        public Criteria andCheckUserFlowIsNull() {
            addCriterion("CHECK_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andCheckUserFlowIsNotNull() {
            addCriterion("CHECK_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andCheckUserFlowEqualTo(String value) {
            addCriterion("CHECK_USER_FLOW =", value, "checkUserFlow");
            return (Criteria) this;
        }

        public Criteria andCheckUserFlowNotEqualTo(String value) {
            addCriterion("CHECK_USER_FLOW <>", value, "checkUserFlow");
            return (Criteria) this;
        }

        public Criteria andCheckUserFlowGreaterThan(String value) {
            addCriterion("CHECK_USER_FLOW >", value, "checkUserFlow");
            return (Criteria) this;
        }

        public Criteria andCheckUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_USER_FLOW >=", value, "checkUserFlow");
            return (Criteria) this;
        }

        public Criteria andCheckUserFlowLessThan(String value) {
            addCriterion("CHECK_USER_FLOW <", value, "checkUserFlow");
            return (Criteria) this;
        }

        public Criteria andCheckUserFlowLessThanOrEqualTo(String value) {
            addCriterion("CHECK_USER_FLOW <=", value, "checkUserFlow");
            return (Criteria) this;
        }

        public Criteria andCheckUserFlowLike(String value) {
            addCriterion("CHECK_USER_FLOW like", value, "checkUserFlow");
            return (Criteria) this;
        }

        public Criteria andCheckUserFlowNotLike(String value) {
            addCriterion("CHECK_USER_FLOW not like", value, "checkUserFlow");
            return (Criteria) this;
        }

        public Criteria andCheckUserFlowIn(List<String> values) {
            addCriterion("CHECK_USER_FLOW in", values, "checkUserFlow");
            return (Criteria) this;
        }

        public Criteria andCheckUserFlowNotIn(List<String> values) {
            addCriterion("CHECK_USER_FLOW not in", values, "checkUserFlow");
            return (Criteria) this;
        }

        public Criteria andCheckUserFlowBetween(String value1, String value2) {
            addCriterion("CHECK_USER_FLOW between", value1, value2, "checkUserFlow");
            return (Criteria) this;
        }

        public Criteria andCheckUserFlowNotBetween(String value1, String value2) {
            addCriterion("CHECK_USER_FLOW not between", value1, value2, "checkUserFlow");
            return (Criteria) this;
        }

        public Criteria andCheckUserNameIsNull() {
            addCriterion("CHECK_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCheckUserNameIsNotNull() {
            addCriterion("CHECK_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCheckUserNameEqualTo(String value) {
            addCriterion("CHECK_USER_NAME =", value, "checkUserName");
            return (Criteria) this;
        }

        public Criteria andCheckUserNameNotEqualTo(String value) {
            addCriterion("CHECK_USER_NAME <>", value, "checkUserName");
            return (Criteria) this;
        }

        public Criteria andCheckUserNameGreaterThan(String value) {
            addCriterion("CHECK_USER_NAME >", value, "checkUserName");
            return (Criteria) this;
        }

        public Criteria andCheckUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_USER_NAME >=", value, "checkUserName");
            return (Criteria) this;
        }

        public Criteria andCheckUserNameLessThan(String value) {
            addCriterion("CHECK_USER_NAME <", value, "checkUserName");
            return (Criteria) this;
        }

        public Criteria andCheckUserNameLessThanOrEqualTo(String value) {
            addCriterion("CHECK_USER_NAME <=", value, "checkUserName");
            return (Criteria) this;
        }

        public Criteria andCheckUserNameLike(String value) {
            addCriterion("CHECK_USER_NAME like", value, "checkUserName");
            return (Criteria) this;
        }

        public Criteria andCheckUserNameNotLike(String value) {
            addCriterion("CHECK_USER_NAME not like", value, "checkUserName");
            return (Criteria) this;
        }

        public Criteria andCheckUserNameIn(List<String> values) {
            addCriterion("CHECK_USER_NAME in", values, "checkUserName");
            return (Criteria) this;
        }

        public Criteria andCheckUserNameNotIn(List<String> values) {
            addCriterion("CHECK_USER_NAME not in", values, "checkUserName");
            return (Criteria) this;
        }

        public Criteria andCheckUserNameBetween(String value1, String value2) {
            addCriterion("CHECK_USER_NAME between", value1, value2, "checkUserName");
            return (Criteria) this;
        }

        public Criteria andCheckUserNameNotBetween(String value1, String value2) {
            addCriterion("CHECK_USER_NAME not between", value1, value2, "checkUserName");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNull() {
            addCriterion("CHECK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("CHECK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(String value) {
            addCriterion("CHECK_TIME =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(String value) {
            addCriterion("CHECK_TIME <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(String value) {
            addCriterion("CHECK_TIME >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_TIME >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(String value) {
            addCriterion("CHECK_TIME <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(String value) {
            addCriterion("CHECK_TIME <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLike(String value) {
            addCriterion("CHECK_TIME like", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotLike(String value) {
            addCriterion("CHECK_TIME not like", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<String> values) {
            addCriterion("CHECK_TIME in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<String> values) {
            addCriterion("CHECK_TIME not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(String value1, String value2) {
            addCriterion("CHECK_TIME between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(String value1, String value2) {
            addCriterion("CHECK_TIME not between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowIsNull() {
            addCriterion("AUDIT_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowIsNotNull() {
            addCriterion("AUDIT_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW =", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW <>", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowGreaterThan(String value) {
            addCriterion("AUDIT_USER_FLOW >", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW >=", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowLessThan(String value) {
            addCriterion("AUDIT_USER_FLOW <", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW <=", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowLike(String value) {
            addCriterion("AUDIT_USER_FLOW like", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotLike(String value) {
            addCriterion("AUDIT_USER_FLOW not like", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowIn(List<String> values) {
            addCriterion("AUDIT_USER_FLOW in", values, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotIn(List<String> values) {
            addCriterion("AUDIT_USER_FLOW not in", values, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_FLOW between", value1, value2, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_FLOW not between", value1, value2, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIsNull() {
            addCriterion("AUDIT_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIsNotNull() {
            addCriterion("AUDIT_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME =", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME <>", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameGreaterThan(String value) {
            addCriterion("AUDIT_USER_NAME >", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME >=", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLessThan(String value) {
            addCriterion("AUDIT_USER_NAME <", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME <=", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLike(String value) {
            addCriterion("AUDIT_USER_NAME like", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotLike(String value) {
            addCriterion("AUDIT_USER_NAME not like", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIn(List<String> values) {
            addCriterion("AUDIT_USER_NAME in", values, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotIn(List<String> values) {
            addCriterion("AUDIT_USER_NAME not in", values, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_NAME between", value1, value2, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_NAME not between", value1, value2, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNull() {
            addCriterion("AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNotNull() {
            addCriterion("AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeEqualTo(String value) {
            addCriterion("AUDIT_TIME =", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotEqualTo(String value) {
            addCriterion("AUDIT_TIME <>", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThan(String value) {
            addCriterion("AUDIT_TIME >", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_TIME >=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThan(String value) {
            addCriterion("AUDIT_TIME <", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_TIME <=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLike(String value) {
            addCriterion("AUDIT_TIME like", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotLike(String value) {
            addCriterion("AUDIT_TIME not like", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIn(List<String> values) {
            addCriterion("AUDIT_TIME in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotIn(List<String> values) {
            addCriterion("AUDIT_TIME not in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeBetween(String value1, String value2) {
            addCriterion("AUDIT_TIME between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotBetween(String value1, String value2) {
            addCriterion("AUDIT_TIME not between", value1, value2, "auditTime");
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