package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ExamBookComposInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamBookComposInfoExample() {
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

        public Criteria andComposFlowIsNull() {
            addCriterion("COMPOS_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andComposFlowIsNotNull() {
            addCriterion("COMPOS_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andComposFlowEqualTo(String value) {
            addCriterion("COMPOS_FLOW =", value, "composFlow");
            return (Criteria) this;
        }

        public Criteria andComposFlowNotEqualTo(String value) {
            addCriterion("COMPOS_FLOW <>", value, "composFlow");
            return (Criteria) this;
        }

        public Criteria andComposFlowGreaterThan(String value) {
            addCriterion("COMPOS_FLOW >", value, "composFlow");
            return (Criteria) this;
        }

        public Criteria andComposFlowGreaterThanOrEqualTo(String value) {
            addCriterion("COMPOS_FLOW >=", value, "composFlow");
            return (Criteria) this;
        }

        public Criteria andComposFlowLessThan(String value) {
            addCriterion("COMPOS_FLOW <", value, "composFlow");
            return (Criteria) this;
        }

        public Criteria andComposFlowLessThanOrEqualTo(String value) {
            addCriterion("COMPOS_FLOW <=", value, "composFlow");
            return (Criteria) this;
        }

        public Criteria andComposFlowLike(String value) {
            addCriterion("COMPOS_FLOW like", value, "composFlow");
            return (Criteria) this;
        }

        public Criteria andComposFlowNotLike(String value) {
            addCriterion("COMPOS_FLOW not like", value, "composFlow");
            return (Criteria) this;
        }

        public Criteria andComposFlowIn(List<String> values) {
            addCriterion("COMPOS_FLOW in", values, "composFlow");
            return (Criteria) this;
        }

        public Criteria andComposFlowNotIn(List<String> values) {
            addCriterion("COMPOS_FLOW not in", values, "composFlow");
            return (Criteria) this;
        }

        public Criteria andComposFlowBetween(String value1, String value2) {
            addCriterion("COMPOS_FLOW between", value1, value2, "composFlow");
            return (Criteria) this;
        }

        public Criteria andComposFlowNotBetween(String value1, String value2) {
            addCriterion("COMPOS_FLOW not between", value1, value2, "composFlow");
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

        public Criteria andComposUserFlowIsNull() {
            addCriterion("COMPOS_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andComposUserFlowIsNotNull() {
            addCriterion("COMPOS_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andComposUserFlowEqualTo(String value) {
            addCriterion("COMPOS_USER_FLOW =", value, "composUserFlow");
            return (Criteria) this;
        }

        public Criteria andComposUserFlowNotEqualTo(String value) {
            addCriterion("COMPOS_USER_FLOW <>", value, "composUserFlow");
            return (Criteria) this;
        }

        public Criteria andComposUserFlowGreaterThan(String value) {
            addCriterion("COMPOS_USER_FLOW >", value, "composUserFlow");
            return (Criteria) this;
        }

        public Criteria andComposUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("COMPOS_USER_FLOW >=", value, "composUserFlow");
            return (Criteria) this;
        }

        public Criteria andComposUserFlowLessThan(String value) {
            addCriterion("COMPOS_USER_FLOW <", value, "composUserFlow");
            return (Criteria) this;
        }

        public Criteria andComposUserFlowLessThanOrEqualTo(String value) {
            addCriterion("COMPOS_USER_FLOW <=", value, "composUserFlow");
            return (Criteria) this;
        }

        public Criteria andComposUserFlowLike(String value) {
            addCriterion("COMPOS_USER_FLOW like", value, "composUserFlow");
            return (Criteria) this;
        }

        public Criteria andComposUserFlowNotLike(String value) {
            addCriterion("COMPOS_USER_FLOW not like", value, "composUserFlow");
            return (Criteria) this;
        }

        public Criteria andComposUserFlowIn(List<String> values) {
            addCriterion("COMPOS_USER_FLOW in", values, "composUserFlow");
            return (Criteria) this;
        }

        public Criteria andComposUserFlowNotIn(List<String> values) {
            addCriterion("COMPOS_USER_FLOW not in", values, "composUserFlow");
            return (Criteria) this;
        }

        public Criteria andComposUserFlowBetween(String value1, String value2) {
            addCriterion("COMPOS_USER_FLOW between", value1, value2, "composUserFlow");
            return (Criteria) this;
        }

        public Criteria andComposUserFlowNotBetween(String value1, String value2) {
            addCriterion("COMPOS_USER_FLOW not between", value1, value2, "composUserFlow");
            return (Criteria) this;
        }

        public Criteria andComposUserNameIsNull() {
            addCriterion("COMPOS_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andComposUserNameIsNotNull() {
            addCriterion("COMPOS_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andComposUserNameEqualTo(String value) {
            addCriterion("COMPOS_USER_NAME =", value, "composUserName");
            return (Criteria) this;
        }

        public Criteria andComposUserNameNotEqualTo(String value) {
            addCriterion("COMPOS_USER_NAME <>", value, "composUserName");
            return (Criteria) this;
        }

        public Criteria andComposUserNameGreaterThan(String value) {
            addCriterion("COMPOS_USER_NAME >", value, "composUserName");
            return (Criteria) this;
        }

        public Criteria andComposUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("COMPOS_USER_NAME >=", value, "composUserName");
            return (Criteria) this;
        }

        public Criteria andComposUserNameLessThan(String value) {
            addCriterion("COMPOS_USER_NAME <", value, "composUserName");
            return (Criteria) this;
        }

        public Criteria andComposUserNameLessThanOrEqualTo(String value) {
            addCriterion("COMPOS_USER_NAME <=", value, "composUserName");
            return (Criteria) this;
        }

        public Criteria andComposUserNameLike(String value) {
            addCriterion("COMPOS_USER_NAME like", value, "composUserName");
            return (Criteria) this;
        }

        public Criteria andComposUserNameNotLike(String value) {
            addCriterion("COMPOS_USER_NAME not like", value, "composUserName");
            return (Criteria) this;
        }

        public Criteria andComposUserNameIn(List<String> values) {
            addCriterion("COMPOS_USER_NAME in", values, "composUserName");
            return (Criteria) this;
        }

        public Criteria andComposUserNameNotIn(List<String> values) {
            addCriterion("COMPOS_USER_NAME not in", values, "composUserName");
            return (Criteria) this;
        }

        public Criteria andComposUserNameBetween(String value1, String value2) {
            addCriterion("COMPOS_USER_NAME between", value1, value2, "composUserName");
            return (Criteria) this;
        }

        public Criteria andComposUserNameNotBetween(String value1, String value2) {
            addCriterion("COMPOS_USER_NAME not between", value1, value2, "composUserName");
            return (Criteria) this;
        }

        public Criteria andComposTimeIsNull() {
            addCriterion("COMPOS_TIME is null");
            return (Criteria) this;
        }

        public Criteria andComposTimeIsNotNull() {
            addCriterion("COMPOS_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andComposTimeEqualTo(String value) {
            addCriterion("COMPOS_TIME =", value, "composTime");
            return (Criteria) this;
        }

        public Criteria andComposTimeNotEqualTo(String value) {
            addCriterion("COMPOS_TIME <>", value, "composTime");
            return (Criteria) this;
        }

        public Criteria andComposTimeGreaterThan(String value) {
            addCriterion("COMPOS_TIME >", value, "composTime");
            return (Criteria) this;
        }

        public Criteria andComposTimeGreaterThanOrEqualTo(String value) {
            addCriterion("COMPOS_TIME >=", value, "composTime");
            return (Criteria) this;
        }

        public Criteria andComposTimeLessThan(String value) {
            addCriterion("COMPOS_TIME <", value, "composTime");
            return (Criteria) this;
        }

        public Criteria andComposTimeLessThanOrEqualTo(String value) {
            addCriterion("COMPOS_TIME <=", value, "composTime");
            return (Criteria) this;
        }

        public Criteria andComposTimeLike(String value) {
            addCriterion("COMPOS_TIME like", value, "composTime");
            return (Criteria) this;
        }

        public Criteria andComposTimeNotLike(String value) {
            addCriterion("COMPOS_TIME not like", value, "composTime");
            return (Criteria) this;
        }

        public Criteria andComposTimeIn(List<String> values) {
            addCriterion("COMPOS_TIME in", values, "composTime");
            return (Criteria) this;
        }

        public Criteria andComposTimeNotIn(List<String> values) {
            addCriterion("COMPOS_TIME not in", values, "composTime");
            return (Criteria) this;
        }

        public Criteria andComposTimeBetween(String value1, String value2) {
            addCriterion("COMPOS_TIME between", value1, value2, "composTime");
            return (Criteria) this;
        }

        public Criteria andComposTimeNotBetween(String value1, String value2) {
            addCriterion("COMPOS_TIME not between", value1, value2, "composTime");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowIsNull() {
            addCriterion("SIGN_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowIsNotNull() {
            addCriterion("SIGN_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowEqualTo(String value) {
            addCriterion("SIGN_USER_FLOW =", value, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowNotEqualTo(String value) {
            addCriterion("SIGN_USER_FLOW <>", value, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowGreaterThan(String value) {
            addCriterion("SIGN_USER_FLOW >", value, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SIGN_USER_FLOW >=", value, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowLessThan(String value) {
            addCriterion("SIGN_USER_FLOW <", value, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowLessThanOrEqualTo(String value) {
            addCriterion("SIGN_USER_FLOW <=", value, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowLike(String value) {
            addCriterion("SIGN_USER_FLOW like", value, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowNotLike(String value) {
            addCriterion("SIGN_USER_FLOW not like", value, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowIn(List<String> values) {
            addCriterion("SIGN_USER_FLOW in", values, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowNotIn(List<String> values) {
            addCriterion("SIGN_USER_FLOW not in", values, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowBetween(String value1, String value2) {
            addCriterion("SIGN_USER_FLOW between", value1, value2, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserFlowNotBetween(String value1, String value2) {
            addCriterion("SIGN_USER_FLOW not between", value1, value2, "signUserFlow");
            return (Criteria) this;
        }

        public Criteria andSignUserNameIsNull() {
            addCriterion("SIGN_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSignUserNameIsNotNull() {
            addCriterion("SIGN_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSignUserNameEqualTo(String value) {
            addCriterion("SIGN_USER_NAME =", value, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameNotEqualTo(String value) {
            addCriterion("SIGN_USER_NAME <>", value, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameGreaterThan(String value) {
            addCriterion("SIGN_USER_NAME >", value, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("SIGN_USER_NAME >=", value, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameLessThan(String value) {
            addCriterion("SIGN_USER_NAME <", value, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameLessThanOrEqualTo(String value) {
            addCriterion("SIGN_USER_NAME <=", value, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameLike(String value) {
            addCriterion("SIGN_USER_NAME like", value, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameNotLike(String value) {
            addCriterion("SIGN_USER_NAME not like", value, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameIn(List<String> values) {
            addCriterion("SIGN_USER_NAME in", values, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameNotIn(List<String> values) {
            addCriterion("SIGN_USER_NAME not in", values, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameBetween(String value1, String value2) {
            addCriterion("SIGN_USER_NAME between", value1, value2, "signUserName");
            return (Criteria) this;
        }

        public Criteria andSignUserNameNotBetween(String value1, String value2) {
            addCriterion("SIGN_USER_NAME not between", value1, value2, "signUserName");
            return (Criteria) this;
        }

        public Criteria andProblemIsNull() {
            addCriterion("PROBLEM is null");
            return (Criteria) this;
        }

        public Criteria andProblemIsNotNull() {
            addCriterion("PROBLEM is not null");
            return (Criteria) this;
        }

        public Criteria andProblemEqualTo(String value) {
            addCriterion("PROBLEM =", value, "problem");
            return (Criteria) this;
        }

        public Criteria andProblemNotEqualTo(String value) {
            addCriterion("PROBLEM <>", value, "problem");
            return (Criteria) this;
        }

        public Criteria andProblemGreaterThan(String value) {
            addCriterion("PROBLEM >", value, "problem");
            return (Criteria) this;
        }

        public Criteria andProblemGreaterThanOrEqualTo(String value) {
            addCriterion("PROBLEM >=", value, "problem");
            return (Criteria) this;
        }

        public Criteria andProblemLessThan(String value) {
            addCriterion("PROBLEM <", value, "problem");
            return (Criteria) this;
        }

        public Criteria andProblemLessThanOrEqualTo(String value) {
            addCriterion("PROBLEM <=", value, "problem");
            return (Criteria) this;
        }

        public Criteria andProblemLike(String value) {
            addCriterion("PROBLEM like", value, "problem");
            return (Criteria) this;
        }

        public Criteria andProblemNotLike(String value) {
            addCriterion("PROBLEM not like", value, "problem");
            return (Criteria) this;
        }

        public Criteria andProblemIn(List<String> values) {
            addCriterion("PROBLEM in", values, "problem");
            return (Criteria) this;
        }

        public Criteria andProblemNotIn(List<String> values) {
            addCriterion("PROBLEM not in", values, "problem");
            return (Criteria) this;
        }

        public Criteria andProblemBetween(String value1, String value2) {
            addCriterion("PROBLEM between", value1, value2, "problem");
            return (Criteria) this;
        }

        public Criteria andProblemNotBetween(String value1, String value2) {
            addCriterion("PROBLEM not between", value1, value2, "problem");
            return (Criteria) this;
        }

        public Criteria andSolutionIsNull() {
            addCriterion("SOLUTION is null");
            return (Criteria) this;
        }

        public Criteria andSolutionIsNotNull() {
            addCriterion("SOLUTION is not null");
            return (Criteria) this;
        }

        public Criteria andSolutionEqualTo(String value) {
            addCriterion("SOLUTION =", value, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionNotEqualTo(String value) {
            addCriterion("SOLUTION <>", value, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionGreaterThan(String value) {
            addCriterion("SOLUTION >", value, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionGreaterThanOrEqualTo(String value) {
            addCriterion("SOLUTION >=", value, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionLessThan(String value) {
            addCriterion("SOLUTION <", value, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionLessThanOrEqualTo(String value) {
            addCriterion("SOLUTION <=", value, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionLike(String value) {
            addCriterion("SOLUTION like", value, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionNotLike(String value) {
            addCriterion("SOLUTION not like", value, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionIn(List<String> values) {
            addCriterion("SOLUTION in", values, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionNotIn(List<String> values) {
            addCriterion("SOLUTION not in", values, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionBetween(String value1, String value2) {
            addCriterion("SOLUTION between", value1, value2, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionNotBetween(String value1, String value2) {
            addCriterion("SOLUTION not between", value1, value2, "solution");
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