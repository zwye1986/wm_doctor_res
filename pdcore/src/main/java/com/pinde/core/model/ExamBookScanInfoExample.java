package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ExamBookScanInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamBookScanInfoExample() {
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

        public Criteria andScanFlowIsNull() {
            addCriterion("SCAN_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andScanFlowIsNotNull() {
            addCriterion("SCAN_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andScanFlowEqualTo(String value) {
            addCriterion("SCAN_FLOW =", value, "scanFlow");
            return (Criteria) this;
        }

        public Criteria andScanFlowNotEqualTo(String value) {
            addCriterion("SCAN_FLOW <>", value, "scanFlow");
            return (Criteria) this;
        }

        public Criteria andScanFlowGreaterThan(String value) {
            addCriterion("SCAN_FLOW >", value, "scanFlow");
            return (Criteria) this;
        }

        public Criteria andScanFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCAN_FLOW >=", value, "scanFlow");
            return (Criteria) this;
        }

        public Criteria andScanFlowLessThan(String value) {
            addCriterion("SCAN_FLOW <", value, "scanFlow");
            return (Criteria) this;
        }

        public Criteria andScanFlowLessThanOrEqualTo(String value) {
            addCriterion("SCAN_FLOW <=", value, "scanFlow");
            return (Criteria) this;
        }

        public Criteria andScanFlowLike(String value) {
            addCriterion("SCAN_FLOW like", value, "scanFlow");
            return (Criteria) this;
        }

        public Criteria andScanFlowNotLike(String value) {
            addCriterion("SCAN_FLOW not like", value, "scanFlow");
            return (Criteria) this;
        }

        public Criteria andScanFlowIn(List<String> values) {
            addCriterion("SCAN_FLOW in", values, "scanFlow");
            return (Criteria) this;
        }

        public Criteria andScanFlowNotIn(List<String> values) {
            addCriterion("SCAN_FLOW not in", values, "scanFlow");
            return (Criteria) this;
        }

        public Criteria andScanFlowBetween(String value1, String value2) {
            addCriterion("SCAN_FLOW between", value1, value2, "scanFlow");
            return (Criteria) this;
        }

        public Criteria andScanFlowNotBetween(String value1, String value2) {
            addCriterion("SCAN_FLOW not between", value1, value2, "scanFlow");
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

        public Criteria andScanUserFlowIsNull() {
            addCriterion("SCAN_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andScanUserFlowIsNotNull() {
            addCriterion("SCAN_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andScanUserFlowEqualTo(String value) {
            addCriterion("SCAN_USER_FLOW =", value, "scanUserFlow");
            return (Criteria) this;
        }

        public Criteria andScanUserFlowNotEqualTo(String value) {
            addCriterion("SCAN_USER_FLOW <>", value, "scanUserFlow");
            return (Criteria) this;
        }

        public Criteria andScanUserFlowGreaterThan(String value) {
            addCriterion("SCAN_USER_FLOW >", value, "scanUserFlow");
            return (Criteria) this;
        }

        public Criteria andScanUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCAN_USER_FLOW >=", value, "scanUserFlow");
            return (Criteria) this;
        }

        public Criteria andScanUserFlowLessThan(String value) {
            addCriterion("SCAN_USER_FLOW <", value, "scanUserFlow");
            return (Criteria) this;
        }

        public Criteria andScanUserFlowLessThanOrEqualTo(String value) {
            addCriterion("SCAN_USER_FLOW <=", value, "scanUserFlow");
            return (Criteria) this;
        }

        public Criteria andScanUserFlowLike(String value) {
            addCriterion("SCAN_USER_FLOW like", value, "scanUserFlow");
            return (Criteria) this;
        }

        public Criteria andScanUserFlowNotLike(String value) {
            addCriterion("SCAN_USER_FLOW not like", value, "scanUserFlow");
            return (Criteria) this;
        }

        public Criteria andScanUserFlowIn(List<String> values) {
            addCriterion("SCAN_USER_FLOW in", values, "scanUserFlow");
            return (Criteria) this;
        }

        public Criteria andScanUserFlowNotIn(List<String> values) {
            addCriterion("SCAN_USER_FLOW not in", values, "scanUserFlow");
            return (Criteria) this;
        }

        public Criteria andScanUserFlowBetween(String value1, String value2) {
            addCriterion("SCAN_USER_FLOW between", value1, value2, "scanUserFlow");
            return (Criteria) this;
        }

        public Criteria andScanUserFlowNotBetween(String value1, String value2) {
            addCriterion("SCAN_USER_FLOW not between", value1, value2, "scanUserFlow");
            return (Criteria) this;
        }

        public Criteria andScanUserNameIsNull() {
            addCriterion("SCAN_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andScanUserNameIsNotNull() {
            addCriterion("SCAN_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andScanUserNameEqualTo(String value) {
            addCriterion("SCAN_USER_NAME =", value, "scanUserName");
            return (Criteria) this;
        }

        public Criteria andScanUserNameNotEqualTo(String value) {
            addCriterion("SCAN_USER_NAME <>", value, "scanUserName");
            return (Criteria) this;
        }

        public Criteria andScanUserNameGreaterThan(String value) {
            addCriterion("SCAN_USER_NAME >", value, "scanUserName");
            return (Criteria) this;
        }

        public Criteria andScanUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCAN_USER_NAME >=", value, "scanUserName");
            return (Criteria) this;
        }

        public Criteria andScanUserNameLessThan(String value) {
            addCriterion("SCAN_USER_NAME <", value, "scanUserName");
            return (Criteria) this;
        }

        public Criteria andScanUserNameLessThanOrEqualTo(String value) {
            addCriterion("SCAN_USER_NAME <=", value, "scanUserName");
            return (Criteria) this;
        }

        public Criteria andScanUserNameLike(String value) {
            addCriterion("SCAN_USER_NAME like", value, "scanUserName");
            return (Criteria) this;
        }

        public Criteria andScanUserNameNotLike(String value) {
            addCriterion("SCAN_USER_NAME not like", value, "scanUserName");
            return (Criteria) this;
        }

        public Criteria andScanUserNameIn(List<String> values) {
            addCriterion("SCAN_USER_NAME in", values, "scanUserName");
            return (Criteria) this;
        }

        public Criteria andScanUserNameNotIn(List<String> values) {
            addCriterion("SCAN_USER_NAME not in", values, "scanUserName");
            return (Criteria) this;
        }

        public Criteria andScanUserNameBetween(String value1, String value2) {
            addCriterion("SCAN_USER_NAME between", value1, value2, "scanUserName");
            return (Criteria) this;
        }

        public Criteria andScanUserNameNotBetween(String value1, String value2) {
            addCriterion("SCAN_USER_NAME not between", value1, value2, "scanUserName");
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