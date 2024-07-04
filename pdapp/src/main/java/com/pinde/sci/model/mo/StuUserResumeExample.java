package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class StuUserResumeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StuUserResumeExample() {
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

        public Criteria andResumeFlowIsNull() {
            addCriterion("RESUME_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andResumeFlowIsNotNull() {
            addCriterion("RESUME_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andResumeFlowEqualTo(String value) {
            addCriterion("RESUME_FLOW =", value, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowNotEqualTo(String value) {
            addCriterion("RESUME_FLOW <>", value, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowGreaterThan(String value) {
            addCriterion("RESUME_FLOW >", value, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RESUME_FLOW >=", value, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowLessThan(String value) {
            addCriterion("RESUME_FLOW <", value, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowLessThanOrEqualTo(String value) {
            addCriterion("RESUME_FLOW <=", value, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowLike(String value) {
            addCriterion("RESUME_FLOW like", value, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowNotLike(String value) {
            addCriterion("RESUME_FLOW not like", value, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowIn(List<String> values) {
            addCriterion("RESUME_FLOW in", values, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowNotIn(List<String> values) {
            addCriterion("RESUME_FLOW not in", values, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowBetween(String value1, String value2) {
            addCriterion("RESUME_FLOW between", value1, value2, "resumeFlow");
            return (Criteria) this;
        }

        public Criteria andResumeFlowNotBetween(String value1, String value2) {
            addCriterion("RESUME_FLOW not between", value1, value2, "resumeFlow");
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

        public Criteria andUserAgeIsNull() {
            addCriterion("USER_AGE is null");
            return (Criteria) this;
        }

        public Criteria andUserAgeIsNotNull() {
            addCriterion("USER_AGE is not null");
            return (Criteria) this;
        }

        public Criteria andUserAgeEqualTo(String value) {
            addCriterion("USER_AGE =", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeNotEqualTo(String value) {
            addCriterion("USER_AGE <>", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeGreaterThan(String value) {
            addCriterion("USER_AGE >", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeGreaterThanOrEqualTo(String value) {
            addCriterion("USER_AGE >=", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeLessThan(String value) {
            addCriterion("USER_AGE <", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeLessThanOrEqualTo(String value) {
            addCriterion("USER_AGE <=", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeLike(String value) {
            addCriterion("USER_AGE like", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeNotLike(String value) {
            addCriterion("USER_AGE not like", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeIn(List<String> values) {
            addCriterion("USER_AGE in", values, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeNotIn(List<String> values) {
            addCriterion("USER_AGE not in", values, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeBetween(String value1, String value2) {
            addCriterion("USER_AGE between", value1, value2, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeNotBetween(String value1, String value2) {
            addCriterion("USER_AGE not between", value1, value2, "userAge");
            return (Criteria) this;
        }

        public Criteria andTitleIdIsNull() {
            addCriterion("TITLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTitleIdIsNotNull() {
            addCriterion("TITLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTitleIdEqualTo(String value) {
            addCriterion("TITLE_ID =", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdNotEqualTo(String value) {
            addCriterion("TITLE_ID <>", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdGreaterThan(String value) {
            addCriterion("TITLE_ID >", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE_ID >=", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdLessThan(String value) {
            addCriterion("TITLE_ID <", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdLessThanOrEqualTo(String value) {
            addCriterion("TITLE_ID <=", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdLike(String value) {
            addCriterion("TITLE_ID like", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdNotLike(String value) {
            addCriterion("TITLE_ID not like", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdIn(List<String> values) {
            addCriterion("TITLE_ID in", values, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdNotIn(List<String> values) {
            addCriterion("TITLE_ID not in", values, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdBetween(String value1, String value2) {
            addCriterion("TITLE_ID between", value1, value2, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdNotBetween(String value1, String value2) {
            addCriterion("TITLE_ID not between", value1, value2, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleNameIsNull() {
            addCriterion("TITLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTitleNameIsNotNull() {
            addCriterion("TITLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTitleNameEqualTo(String value) {
            addCriterion("TITLE_NAME =", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameNotEqualTo(String value) {
            addCriterion("TITLE_NAME <>", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameGreaterThan(String value) {
            addCriterion("TITLE_NAME >", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE_NAME >=", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameLessThan(String value) {
            addCriterion("TITLE_NAME <", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameLessThanOrEqualTo(String value) {
            addCriterion("TITLE_NAME <=", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameLike(String value) {
            addCriterion("TITLE_NAME like", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameNotLike(String value) {
            addCriterion("TITLE_NAME not like", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameIn(List<String> values) {
            addCriterion("TITLE_NAME in", values, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameNotIn(List<String> values) {
            addCriterion("TITLE_NAME not in", values, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameBetween(String value1, String value2) {
            addCriterion("TITLE_NAME between", value1, value2, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameNotBetween(String value1, String value2) {
            addCriterion("TITLE_NAME not between", value1, value2, "titleName");
            return (Criteria) this;
        }

        public Criteria andPostIdIsNull() {
            addCriterion("POST_ID is null");
            return (Criteria) this;
        }

        public Criteria andPostIdIsNotNull() {
            addCriterion("POST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPostIdEqualTo(String value) {
            addCriterion("POST_ID =", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotEqualTo(String value) {
            addCriterion("POST_ID <>", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdGreaterThan(String value) {
            addCriterion("POST_ID >", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdGreaterThanOrEqualTo(String value) {
            addCriterion("POST_ID >=", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdLessThan(String value) {
            addCriterion("POST_ID <", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdLessThanOrEqualTo(String value) {
            addCriterion("POST_ID <=", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdLike(String value) {
            addCriterion("POST_ID like", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotLike(String value) {
            addCriterion("POST_ID not like", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdIn(List<String> values) {
            addCriterion("POST_ID in", values, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotIn(List<String> values) {
            addCriterion("POST_ID not in", values, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdBetween(String value1, String value2) {
            addCriterion("POST_ID between", value1, value2, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotBetween(String value1, String value2) {
            addCriterion("POST_ID not between", value1, value2, "postId");
            return (Criteria) this;
        }

        public Criteria andPostNameIsNull() {
            addCriterion("POST_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPostNameIsNotNull() {
            addCriterion("POST_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPostNameEqualTo(String value) {
            addCriterion("POST_NAME =", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameNotEqualTo(String value) {
            addCriterion("POST_NAME <>", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameGreaterThan(String value) {
            addCriterion("POST_NAME >", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameGreaterThanOrEqualTo(String value) {
            addCriterion("POST_NAME >=", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameLessThan(String value) {
            addCriterion("POST_NAME <", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameLessThanOrEqualTo(String value) {
            addCriterion("POST_NAME <=", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameLike(String value) {
            addCriterion("POST_NAME like", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameNotLike(String value) {
            addCriterion("POST_NAME not like", value, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameIn(List<String> values) {
            addCriterion("POST_NAME in", values, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameNotIn(List<String> values) {
            addCriterion("POST_NAME not in", values, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameBetween(String value1, String value2) {
            addCriterion("POST_NAME between", value1, value2, "postName");
            return (Criteria) this;
        }

        public Criteria andPostNameNotBetween(String value1, String value2) {
            addCriterion("POST_NAME not between", value1, value2, "postName");
            return (Criteria) this;
        }

        public Criteria andDeptIdIsNull() {
            addCriterion("DEPT_ID is null");
            return (Criteria) this;
        }

        public Criteria andDeptIdIsNotNull() {
            addCriterion("DEPT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDeptIdEqualTo(String value) {
            addCriterion("DEPT_ID =", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdNotEqualTo(String value) {
            addCriterion("DEPT_ID <>", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdGreaterThan(String value) {
            addCriterion("DEPT_ID >", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_ID >=", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdLessThan(String value) {
            addCriterion("DEPT_ID <", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdLessThanOrEqualTo(String value) {
            addCriterion("DEPT_ID <=", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdLike(String value) {
            addCriterion("DEPT_ID like", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdNotLike(String value) {
            addCriterion("DEPT_ID not like", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdIn(List<String> values) {
            addCriterion("DEPT_ID in", values, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdNotIn(List<String> values) {
            addCriterion("DEPT_ID not in", values, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdBetween(String value1, String value2) {
            addCriterion("DEPT_ID between", value1, value2, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdNotBetween(String value1, String value2) {
            addCriterion("DEPT_ID not between", value1, value2, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNull() {
            addCriterion("DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNotNull() {
            addCriterion("DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNameEqualTo(String value) {
            addCriterion("DEPT_NAME =", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotEqualTo(String value) {
            addCriterion("DEPT_NAME <>", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThan(String value) {
            addCriterion("DEPT_NAME >", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME >=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThan(String value) {
            addCriterion("DEPT_NAME <", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME <=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLike(String value) {
            addCriterion("DEPT_NAME like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotLike(String value) {
            addCriterion("DEPT_NAME not like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameIn(List<String> values) {
            addCriterion("DEPT_NAME in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotIn(List<String> values) {
            addCriterion("DEPT_NAME not in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameBetween(String value1, String value2) {
            addCriterion("DEPT_NAME between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotBetween(String value1, String value2) {
            addCriterion("DEPT_NAME not between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andJobYearIsNull() {
            addCriterion("JOB_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andJobYearIsNotNull() {
            addCriterion("JOB_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andJobYearEqualTo(String value) {
            addCriterion("JOB_YEAR =", value, "jobYear");
            return (Criteria) this;
        }

        public Criteria andJobYearNotEqualTo(String value) {
            addCriterion("JOB_YEAR <>", value, "jobYear");
            return (Criteria) this;
        }

        public Criteria andJobYearGreaterThan(String value) {
            addCriterion("JOB_YEAR >", value, "jobYear");
            return (Criteria) this;
        }

        public Criteria andJobYearGreaterThanOrEqualTo(String value) {
            addCriterion("JOB_YEAR >=", value, "jobYear");
            return (Criteria) this;
        }

        public Criteria andJobYearLessThan(String value) {
            addCriterion("JOB_YEAR <", value, "jobYear");
            return (Criteria) this;
        }

        public Criteria andJobYearLessThanOrEqualTo(String value) {
            addCriterion("JOB_YEAR <=", value, "jobYear");
            return (Criteria) this;
        }

        public Criteria andJobYearLike(String value) {
            addCriterion("JOB_YEAR like", value, "jobYear");
            return (Criteria) this;
        }

        public Criteria andJobYearNotLike(String value) {
            addCriterion("JOB_YEAR not like", value, "jobYear");
            return (Criteria) this;
        }

        public Criteria andJobYearIn(List<String> values) {
            addCriterion("JOB_YEAR in", values, "jobYear");
            return (Criteria) this;
        }

        public Criteria andJobYearNotIn(List<String> values) {
            addCriterion("JOB_YEAR not in", values, "jobYear");
            return (Criteria) this;
        }

        public Criteria andJobYearBetween(String value1, String value2) {
            addCriterion("JOB_YEAR between", value1, value2, "jobYear");
            return (Criteria) this;
        }

        public Criteria andJobYearNotBetween(String value1, String value2) {
            addCriterion("JOB_YEAR not between", value1, value2, "jobYear");
            return (Criteria) this;
        }

        public Criteria andCertifiedNoIsNull() {
            addCriterion("CERTIFIED_NO is null");
            return (Criteria) this;
        }

        public Criteria andCertifiedNoIsNotNull() {
            addCriterion("CERTIFIED_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCertifiedNoEqualTo(String value) {
            addCriterion("CERTIFIED_NO =", value, "certifiedNo");
            return (Criteria) this;
        }

        public Criteria andCertifiedNoNotEqualTo(String value) {
            addCriterion("CERTIFIED_NO <>", value, "certifiedNo");
            return (Criteria) this;
        }

        public Criteria andCertifiedNoGreaterThan(String value) {
            addCriterion("CERTIFIED_NO >", value, "certifiedNo");
            return (Criteria) this;
        }

        public Criteria andCertifiedNoGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFIED_NO >=", value, "certifiedNo");
            return (Criteria) this;
        }

        public Criteria andCertifiedNoLessThan(String value) {
            addCriterion("CERTIFIED_NO <", value, "certifiedNo");
            return (Criteria) this;
        }

        public Criteria andCertifiedNoLessThanOrEqualTo(String value) {
            addCriterion("CERTIFIED_NO <=", value, "certifiedNo");
            return (Criteria) this;
        }

        public Criteria andCertifiedNoLike(String value) {
            addCriterion("CERTIFIED_NO like", value, "certifiedNo");
            return (Criteria) this;
        }

        public Criteria andCertifiedNoNotLike(String value) {
            addCriterion("CERTIFIED_NO not like", value, "certifiedNo");
            return (Criteria) this;
        }

        public Criteria andCertifiedNoIn(List<String> values) {
            addCriterion("CERTIFIED_NO in", values, "certifiedNo");
            return (Criteria) this;
        }

        public Criteria andCertifiedNoNotIn(List<String> values) {
            addCriterion("CERTIFIED_NO not in", values, "certifiedNo");
            return (Criteria) this;
        }

        public Criteria andCertifiedNoBetween(String value1, String value2) {
            addCriterion("CERTIFIED_NO between", value1, value2, "certifiedNo");
            return (Criteria) this;
        }

        public Criteria andCertifiedNoNotBetween(String value1, String value2) {
            addCriterion("CERTIFIED_NO not between", value1, value2, "certifiedNo");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeIdIsNull() {
            addCriterion("CERTIFIED_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeIdIsNotNull() {
            addCriterion("CERTIFIED_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeIdEqualTo(String value) {
            addCriterion("CERTIFIED_TYPE_ID =", value, "certifiedTypeId");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeIdNotEqualTo(String value) {
            addCriterion("CERTIFIED_TYPE_ID <>", value, "certifiedTypeId");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeIdGreaterThan(String value) {
            addCriterion("CERTIFIED_TYPE_ID >", value, "certifiedTypeId");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFIED_TYPE_ID >=", value, "certifiedTypeId");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeIdLessThan(String value) {
            addCriterion("CERTIFIED_TYPE_ID <", value, "certifiedTypeId");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeIdLessThanOrEqualTo(String value) {
            addCriterion("CERTIFIED_TYPE_ID <=", value, "certifiedTypeId");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeIdLike(String value) {
            addCriterion("CERTIFIED_TYPE_ID like", value, "certifiedTypeId");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeIdNotLike(String value) {
            addCriterion("CERTIFIED_TYPE_ID not like", value, "certifiedTypeId");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeIdIn(List<String> values) {
            addCriterion("CERTIFIED_TYPE_ID in", values, "certifiedTypeId");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeIdNotIn(List<String> values) {
            addCriterion("CERTIFIED_TYPE_ID not in", values, "certifiedTypeId");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeIdBetween(String value1, String value2) {
            addCriterion("CERTIFIED_TYPE_ID between", value1, value2, "certifiedTypeId");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeIdNotBetween(String value1, String value2) {
            addCriterion("CERTIFIED_TYPE_ID not between", value1, value2, "certifiedTypeId");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeNameIsNull() {
            addCriterion("CERTIFIED_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeNameIsNotNull() {
            addCriterion("CERTIFIED_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeNameEqualTo(String value) {
            addCriterion("CERTIFIED_TYPE_NAME =", value, "certifiedTypeName");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeNameNotEqualTo(String value) {
            addCriterion("CERTIFIED_TYPE_NAME <>", value, "certifiedTypeName");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeNameGreaterThan(String value) {
            addCriterion("CERTIFIED_TYPE_NAME >", value, "certifiedTypeName");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFIED_TYPE_NAME >=", value, "certifiedTypeName");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeNameLessThan(String value) {
            addCriterion("CERTIFIED_TYPE_NAME <", value, "certifiedTypeName");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeNameLessThanOrEqualTo(String value) {
            addCriterion("CERTIFIED_TYPE_NAME <=", value, "certifiedTypeName");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeNameLike(String value) {
            addCriterion("CERTIFIED_TYPE_NAME like", value, "certifiedTypeName");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeNameNotLike(String value) {
            addCriterion("CERTIFIED_TYPE_NAME not like", value, "certifiedTypeName");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeNameIn(List<String> values) {
            addCriterion("CERTIFIED_TYPE_NAME in", values, "certifiedTypeName");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeNameNotIn(List<String> values) {
            addCriterion("CERTIFIED_TYPE_NAME not in", values, "certifiedTypeName");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeNameBetween(String value1, String value2) {
            addCriterion("CERTIFIED_TYPE_NAME between", value1, value2, "certifiedTypeName");
            return (Criteria) this;
        }

        public Criteria andCertifiedTypeNameNotBetween(String value1, String value2) {
            addCriterion("CERTIFIED_TYPE_NAME not between", value1, value2, "certifiedTypeName");
            return (Criteria) this;
        }

        public Criteria andSpeIdIsNull() {
            addCriterion("SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSpeIdIsNotNull() {
            addCriterion("SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSpeIdEqualTo(String value) {
            addCriterion("SPE_ID =", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotEqualTo(String value) {
            addCriterion("SPE_ID <>", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdGreaterThan(String value) {
            addCriterion("SPE_ID >", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_ID >=", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLessThan(String value) {
            addCriterion("SPE_ID <", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLessThanOrEqualTo(String value) {
            addCriterion("SPE_ID <=", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLike(String value) {
            addCriterion("SPE_ID like", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotLike(String value) {
            addCriterion("SPE_ID not like", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdIn(List<String> values) {
            addCriterion("SPE_ID in", values, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotIn(List<String> values) {
            addCriterion("SPE_ID not in", values, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdBetween(String value1, String value2) {
            addCriterion("SPE_ID between", value1, value2, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotBetween(String value1, String value2) {
            addCriterion("SPE_ID not between", value1, value2, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeNameIsNull() {
            addCriterion("SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSpeNameIsNotNull() {
            addCriterion("SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSpeNameEqualTo(String value) {
            addCriterion("SPE_NAME =", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotEqualTo(String value) {
            addCriterion("SPE_NAME <>", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameGreaterThan(String value) {
            addCriterion("SPE_NAME >", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_NAME >=", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLessThan(String value) {
            addCriterion("SPE_NAME <", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLessThanOrEqualTo(String value) {
            addCriterion("SPE_NAME <=", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLike(String value) {
            addCriterion("SPE_NAME like", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotLike(String value) {
            addCriterion("SPE_NAME not like", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameIn(List<String> values) {
            addCriterion("SPE_NAME in", values, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotIn(List<String> values) {
            addCriterion("SPE_NAME not in", values, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameBetween(String value1, String value2) {
            addCriterion("SPE_NAME between", value1, value2, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotBetween(String value1, String value2) {
            addCriterion("SPE_NAME not between", value1, value2, "speName");
            return (Criteria) this;
        }

        public Criteria andStuTimeIdIsNull() {
            addCriterion("STU_TIME_ID is null");
            return (Criteria) this;
        }

        public Criteria andStuTimeIdIsNotNull() {
            addCriterion("STU_TIME_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStuTimeIdEqualTo(String value) {
            addCriterion("STU_TIME_ID =", value, "stuTimeId");
            return (Criteria) this;
        }

        public Criteria andStuTimeIdNotEqualTo(String value) {
            addCriterion("STU_TIME_ID <>", value, "stuTimeId");
            return (Criteria) this;
        }

        public Criteria andStuTimeIdGreaterThan(String value) {
            addCriterion("STU_TIME_ID >", value, "stuTimeId");
            return (Criteria) this;
        }

        public Criteria andStuTimeIdGreaterThanOrEqualTo(String value) {
            addCriterion("STU_TIME_ID >=", value, "stuTimeId");
            return (Criteria) this;
        }

        public Criteria andStuTimeIdLessThan(String value) {
            addCriterion("STU_TIME_ID <", value, "stuTimeId");
            return (Criteria) this;
        }

        public Criteria andStuTimeIdLessThanOrEqualTo(String value) {
            addCriterion("STU_TIME_ID <=", value, "stuTimeId");
            return (Criteria) this;
        }

        public Criteria andStuTimeIdLike(String value) {
            addCriterion("STU_TIME_ID like", value, "stuTimeId");
            return (Criteria) this;
        }

        public Criteria andStuTimeIdNotLike(String value) {
            addCriterion("STU_TIME_ID not like", value, "stuTimeId");
            return (Criteria) this;
        }

        public Criteria andStuTimeIdIn(List<String> values) {
            addCriterion("STU_TIME_ID in", values, "stuTimeId");
            return (Criteria) this;
        }

        public Criteria andStuTimeIdNotIn(List<String> values) {
            addCriterion("STU_TIME_ID not in", values, "stuTimeId");
            return (Criteria) this;
        }

        public Criteria andStuTimeIdBetween(String value1, String value2) {
            addCriterion("STU_TIME_ID between", value1, value2, "stuTimeId");
            return (Criteria) this;
        }

        public Criteria andStuTimeIdNotBetween(String value1, String value2) {
            addCriterion("STU_TIME_ID not between", value1, value2, "stuTimeId");
            return (Criteria) this;
        }

        public Criteria andStuTimeNameIsNull() {
            addCriterion("STU_TIME_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStuTimeNameIsNotNull() {
            addCriterion("STU_TIME_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStuTimeNameEqualTo(String value) {
            addCriterion("STU_TIME_NAME =", value, "stuTimeName");
            return (Criteria) this;
        }

        public Criteria andStuTimeNameNotEqualTo(String value) {
            addCriterion("STU_TIME_NAME <>", value, "stuTimeName");
            return (Criteria) this;
        }

        public Criteria andStuTimeNameGreaterThan(String value) {
            addCriterion("STU_TIME_NAME >", value, "stuTimeName");
            return (Criteria) this;
        }

        public Criteria andStuTimeNameGreaterThanOrEqualTo(String value) {
            addCriterion("STU_TIME_NAME >=", value, "stuTimeName");
            return (Criteria) this;
        }

        public Criteria andStuTimeNameLessThan(String value) {
            addCriterion("STU_TIME_NAME <", value, "stuTimeName");
            return (Criteria) this;
        }

        public Criteria andStuTimeNameLessThanOrEqualTo(String value) {
            addCriterion("STU_TIME_NAME <=", value, "stuTimeName");
            return (Criteria) this;
        }

        public Criteria andStuTimeNameLike(String value) {
            addCriterion("STU_TIME_NAME like", value, "stuTimeName");
            return (Criteria) this;
        }

        public Criteria andStuTimeNameNotLike(String value) {
            addCriterion("STU_TIME_NAME not like", value, "stuTimeName");
            return (Criteria) this;
        }

        public Criteria andStuTimeNameIn(List<String> values) {
            addCriterion("STU_TIME_NAME in", values, "stuTimeName");
            return (Criteria) this;
        }

        public Criteria andStuTimeNameNotIn(List<String> values) {
            addCriterion("STU_TIME_NAME not in", values, "stuTimeName");
            return (Criteria) this;
        }

        public Criteria andStuTimeNameBetween(String value1, String value2) {
            addCriterion("STU_TIME_NAME between", value1, value2, "stuTimeName");
            return (Criteria) this;
        }

        public Criteria andStuTimeNameNotBetween(String value1, String value2) {
            addCriterion("STU_TIME_NAME not between", value1, value2, "stuTimeName");
            return (Criteria) this;
        }

        public Criteria andStuBatIdIsNull() {
            addCriterion("STU_BAT_ID is null");
            return (Criteria) this;
        }

        public Criteria andStuBatIdIsNotNull() {
            addCriterion("STU_BAT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStuBatIdEqualTo(String value) {
            addCriterion("STU_BAT_ID =", value, "stuBatId");
            return (Criteria) this;
        }

        public Criteria andStuBatIdNotEqualTo(String value) {
            addCriterion("STU_BAT_ID <>", value, "stuBatId");
            return (Criteria) this;
        }

        public Criteria andStuBatIdGreaterThan(String value) {
            addCriterion("STU_BAT_ID >", value, "stuBatId");
            return (Criteria) this;
        }

        public Criteria andStuBatIdGreaterThanOrEqualTo(String value) {
            addCriterion("STU_BAT_ID >=", value, "stuBatId");
            return (Criteria) this;
        }

        public Criteria andStuBatIdLessThan(String value) {
            addCriterion("STU_BAT_ID <", value, "stuBatId");
            return (Criteria) this;
        }

        public Criteria andStuBatIdLessThanOrEqualTo(String value) {
            addCriterion("STU_BAT_ID <=", value, "stuBatId");
            return (Criteria) this;
        }

        public Criteria andStuBatIdLike(String value) {
            addCriterion("STU_BAT_ID like", value, "stuBatId");
            return (Criteria) this;
        }

        public Criteria andStuBatIdNotLike(String value) {
            addCriterion("STU_BAT_ID not like", value, "stuBatId");
            return (Criteria) this;
        }

        public Criteria andStuBatIdIn(List<String> values) {
            addCriterion("STU_BAT_ID in", values, "stuBatId");
            return (Criteria) this;
        }

        public Criteria andStuBatIdNotIn(List<String> values) {
            addCriterion("STU_BAT_ID not in", values, "stuBatId");
            return (Criteria) this;
        }

        public Criteria andStuBatIdBetween(String value1, String value2) {
            addCriterion("STU_BAT_ID between", value1, value2, "stuBatId");
            return (Criteria) this;
        }

        public Criteria andStuBatIdNotBetween(String value1, String value2) {
            addCriterion("STU_BAT_ID not between", value1, value2, "stuBatId");
            return (Criteria) this;
        }

        public Criteria andStuBatNameIsNull() {
            addCriterion("STU_BAT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStuBatNameIsNotNull() {
            addCriterion("STU_BAT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStuBatNameEqualTo(String value) {
            addCriterion("STU_BAT_NAME =", value, "stuBatName");
            return (Criteria) this;
        }

        public Criteria andStuBatNameNotEqualTo(String value) {
            addCriterion("STU_BAT_NAME <>", value, "stuBatName");
            return (Criteria) this;
        }

        public Criteria andStuBatNameGreaterThan(String value) {
            addCriterion("STU_BAT_NAME >", value, "stuBatName");
            return (Criteria) this;
        }

        public Criteria andStuBatNameGreaterThanOrEqualTo(String value) {
            addCriterion("STU_BAT_NAME >=", value, "stuBatName");
            return (Criteria) this;
        }

        public Criteria andStuBatNameLessThan(String value) {
            addCriterion("STU_BAT_NAME <", value, "stuBatName");
            return (Criteria) this;
        }

        public Criteria andStuBatNameLessThanOrEqualTo(String value) {
            addCriterion("STU_BAT_NAME <=", value, "stuBatName");
            return (Criteria) this;
        }

        public Criteria andStuBatNameLike(String value) {
            addCriterion("STU_BAT_NAME like", value, "stuBatName");
            return (Criteria) this;
        }

        public Criteria andStuBatNameNotLike(String value) {
            addCriterion("STU_BAT_NAME not like", value, "stuBatName");
            return (Criteria) this;
        }

        public Criteria andStuBatNameIn(List<String> values) {
            addCriterion("STU_BAT_NAME in", values, "stuBatName");
            return (Criteria) this;
        }

        public Criteria andStuBatNameNotIn(List<String> values) {
            addCriterion("STU_BAT_NAME not in", values, "stuBatName");
            return (Criteria) this;
        }

        public Criteria andStuBatNameBetween(String value1, String value2) {
            addCriterion("STU_BAT_NAME between", value1, value2, "stuBatName");
            return (Criteria) this;
        }

        public Criteria andStuBatNameNotBetween(String value1, String value2) {
            addCriterion("STU_BAT_NAME not between", value1, value2, "stuBatName");
            return (Criteria) this;
        }

        public Criteria andClotherSizeIdIsNull() {
            addCriterion("CLOTHER_SIZE_ID is null");
            return (Criteria) this;
        }

        public Criteria andClotherSizeIdIsNotNull() {
            addCriterion("CLOTHER_SIZE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andClotherSizeIdEqualTo(String value) {
            addCriterion("CLOTHER_SIZE_ID =", value, "clotherSizeId");
            return (Criteria) this;
        }

        public Criteria andClotherSizeIdNotEqualTo(String value) {
            addCriterion("CLOTHER_SIZE_ID <>", value, "clotherSizeId");
            return (Criteria) this;
        }

        public Criteria andClotherSizeIdGreaterThan(String value) {
            addCriterion("CLOTHER_SIZE_ID >", value, "clotherSizeId");
            return (Criteria) this;
        }

        public Criteria andClotherSizeIdGreaterThanOrEqualTo(String value) {
            addCriterion("CLOTHER_SIZE_ID >=", value, "clotherSizeId");
            return (Criteria) this;
        }

        public Criteria andClotherSizeIdLessThan(String value) {
            addCriterion("CLOTHER_SIZE_ID <", value, "clotherSizeId");
            return (Criteria) this;
        }

        public Criteria andClotherSizeIdLessThanOrEqualTo(String value) {
            addCriterion("CLOTHER_SIZE_ID <=", value, "clotherSizeId");
            return (Criteria) this;
        }

        public Criteria andClotherSizeIdLike(String value) {
            addCriterion("CLOTHER_SIZE_ID like", value, "clotherSizeId");
            return (Criteria) this;
        }

        public Criteria andClotherSizeIdNotLike(String value) {
            addCriterion("CLOTHER_SIZE_ID not like", value, "clotherSizeId");
            return (Criteria) this;
        }

        public Criteria andClotherSizeIdIn(List<String> values) {
            addCriterion("CLOTHER_SIZE_ID in", values, "clotherSizeId");
            return (Criteria) this;
        }

        public Criteria andClotherSizeIdNotIn(List<String> values) {
            addCriterion("CLOTHER_SIZE_ID not in", values, "clotherSizeId");
            return (Criteria) this;
        }

        public Criteria andClotherSizeIdBetween(String value1, String value2) {
            addCriterion("CLOTHER_SIZE_ID between", value1, value2, "clotherSizeId");
            return (Criteria) this;
        }

        public Criteria andClotherSizeIdNotBetween(String value1, String value2) {
            addCriterion("CLOTHER_SIZE_ID not between", value1, value2, "clotherSizeId");
            return (Criteria) this;
        }

        public Criteria andClotherSizeNameIsNull() {
            addCriterion("CLOTHER_SIZE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andClotherSizeNameIsNotNull() {
            addCriterion("CLOTHER_SIZE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andClotherSizeNameEqualTo(String value) {
            addCriterion("CLOTHER_SIZE_NAME =", value, "clotherSizeName");
            return (Criteria) this;
        }

        public Criteria andClotherSizeNameNotEqualTo(String value) {
            addCriterion("CLOTHER_SIZE_NAME <>", value, "clotherSizeName");
            return (Criteria) this;
        }

        public Criteria andClotherSizeNameGreaterThan(String value) {
            addCriterion("CLOTHER_SIZE_NAME >", value, "clotherSizeName");
            return (Criteria) this;
        }

        public Criteria andClotherSizeNameGreaterThanOrEqualTo(String value) {
            addCriterion("CLOTHER_SIZE_NAME >=", value, "clotherSizeName");
            return (Criteria) this;
        }

        public Criteria andClotherSizeNameLessThan(String value) {
            addCriterion("CLOTHER_SIZE_NAME <", value, "clotherSizeName");
            return (Criteria) this;
        }

        public Criteria andClotherSizeNameLessThanOrEqualTo(String value) {
            addCriterion("CLOTHER_SIZE_NAME <=", value, "clotherSizeName");
            return (Criteria) this;
        }

        public Criteria andClotherSizeNameLike(String value) {
            addCriterion("CLOTHER_SIZE_NAME like", value, "clotherSizeName");
            return (Criteria) this;
        }

        public Criteria andClotherSizeNameNotLike(String value) {
            addCriterion("CLOTHER_SIZE_NAME not like", value, "clotherSizeName");
            return (Criteria) this;
        }

        public Criteria andClotherSizeNameIn(List<String> values) {
            addCriterion("CLOTHER_SIZE_NAME in", values, "clotherSizeName");
            return (Criteria) this;
        }

        public Criteria andClotherSizeNameNotIn(List<String> values) {
            addCriterion("CLOTHER_SIZE_NAME not in", values, "clotherSizeName");
            return (Criteria) this;
        }

        public Criteria andClotherSizeNameBetween(String value1, String value2) {
            addCriterion("CLOTHER_SIZE_NAME between", value1, value2, "clotherSizeName");
            return (Criteria) this;
        }

        public Criteria andClotherSizeNameNotBetween(String value1, String value2) {
            addCriterion("CLOTHER_SIZE_NAME not between", value1, value2, "clotherSizeName");
            return (Criteria) this;
        }

        public Criteria andIsPutupIsNull() {
            addCriterion("IS_PUTUP is null");
            return (Criteria) this;
        }

        public Criteria andIsPutupIsNotNull() {
            addCriterion("IS_PUTUP is not null");
            return (Criteria) this;
        }

        public Criteria andIsPutupEqualTo(String value) {
            addCriterion("IS_PUTUP =", value, "isPutup");
            return (Criteria) this;
        }

        public Criteria andIsPutupNotEqualTo(String value) {
            addCriterion("IS_PUTUP <>", value, "isPutup");
            return (Criteria) this;
        }

        public Criteria andIsPutupGreaterThan(String value) {
            addCriterion("IS_PUTUP >", value, "isPutup");
            return (Criteria) this;
        }

        public Criteria andIsPutupGreaterThanOrEqualTo(String value) {
            addCriterion("IS_PUTUP >=", value, "isPutup");
            return (Criteria) this;
        }

        public Criteria andIsPutupLessThan(String value) {
            addCriterion("IS_PUTUP <", value, "isPutup");
            return (Criteria) this;
        }

        public Criteria andIsPutupLessThanOrEqualTo(String value) {
            addCriterion("IS_PUTUP <=", value, "isPutup");
            return (Criteria) this;
        }

        public Criteria andIsPutupLike(String value) {
            addCriterion("IS_PUTUP like", value, "isPutup");
            return (Criteria) this;
        }

        public Criteria andIsPutupNotLike(String value) {
            addCriterion("IS_PUTUP not like", value, "isPutup");
            return (Criteria) this;
        }

        public Criteria andIsPutupIn(List<String> values) {
            addCriterion("IS_PUTUP in", values, "isPutup");
            return (Criteria) this;
        }

        public Criteria andIsPutupNotIn(List<String> values) {
            addCriterion("IS_PUTUP not in", values, "isPutup");
            return (Criteria) this;
        }

        public Criteria andIsPutupBetween(String value1, String value2) {
            addCriterion("IS_PUTUP between", value1, value2, "isPutup");
            return (Criteria) this;
        }

        public Criteria andIsPutupNotBetween(String value1, String value2) {
            addCriterion("IS_PUTUP not between", value1, value2, "isPutup");
            return (Criteria) this;
        }

        public Criteria andMaxEduIdIsNull() {
            addCriterion("MAX_EDU_ID is null");
            return (Criteria) this;
        }

        public Criteria andMaxEduIdIsNotNull() {
            addCriterion("MAX_EDU_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMaxEduIdEqualTo(String value) {
            addCriterion("MAX_EDU_ID =", value, "maxEduId");
            return (Criteria) this;
        }

        public Criteria andMaxEduIdNotEqualTo(String value) {
            addCriterion("MAX_EDU_ID <>", value, "maxEduId");
            return (Criteria) this;
        }

        public Criteria andMaxEduIdGreaterThan(String value) {
            addCriterion("MAX_EDU_ID >", value, "maxEduId");
            return (Criteria) this;
        }

        public Criteria andMaxEduIdGreaterThanOrEqualTo(String value) {
            addCriterion("MAX_EDU_ID >=", value, "maxEduId");
            return (Criteria) this;
        }

        public Criteria andMaxEduIdLessThan(String value) {
            addCriterion("MAX_EDU_ID <", value, "maxEduId");
            return (Criteria) this;
        }

        public Criteria andMaxEduIdLessThanOrEqualTo(String value) {
            addCriterion("MAX_EDU_ID <=", value, "maxEduId");
            return (Criteria) this;
        }

        public Criteria andMaxEduIdLike(String value) {
            addCriterion("MAX_EDU_ID like", value, "maxEduId");
            return (Criteria) this;
        }

        public Criteria andMaxEduIdNotLike(String value) {
            addCriterion("MAX_EDU_ID not like", value, "maxEduId");
            return (Criteria) this;
        }

        public Criteria andMaxEduIdIn(List<String> values) {
            addCriterion("MAX_EDU_ID in", values, "maxEduId");
            return (Criteria) this;
        }

        public Criteria andMaxEduIdNotIn(List<String> values) {
            addCriterion("MAX_EDU_ID not in", values, "maxEduId");
            return (Criteria) this;
        }

        public Criteria andMaxEduIdBetween(String value1, String value2) {
            addCriterion("MAX_EDU_ID between", value1, value2, "maxEduId");
            return (Criteria) this;
        }

        public Criteria andMaxEduIdNotBetween(String value1, String value2) {
            addCriterion("MAX_EDU_ID not between", value1, value2, "maxEduId");
            return (Criteria) this;
        }

        public Criteria andMaxEduNameIsNull() {
            addCriterion("MAX_EDU_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMaxEduNameIsNotNull() {
            addCriterion("MAX_EDU_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMaxEduNameEqualTo(String value) {
            addCriterion("MAX_EDU_NAME =", value, "maxEduName");
            return (Criteria) this;
        }

        public Criteria andMaxEduNameNotEqualTo(String value) {
            addCriterion("MAX_EDU_NAME <>", value, "maxEduName");
            return (Criteria) this;
        }

        public Criteria andMaxEduNameGreaterThan(String value) {
            addCriterion("MAX_EDU_NAME >", value, "maxEduName");
            return (Criteria) this;
        }

        public Criteria andMaxEduNameGreaterThanOrEqualTo(String value) {
            addCriterion("MAX_EDU_NAME >=", value, "maxEduName");
            return (Criteria) this;
        }

        public Criteria andMaxEduNameLessThan(String value) {
            addCriterion("MAX_EDU_NAME <", value, "maxEduName");
            return (Criteria) this;
        }

        public Criteria andMaxEduNameLessThanOrEqualTo(String value) {
            addCriterion("MAX_EDU_NAME <=", value, "maxEduName");
            return (Criteria) this;
        }

        public Criteria andMaxEduNameLike(String value) {
            addCriterion("MAX_EDU_NAME like", value, "maxEduName");
            return (Criteria) this;
        }

        public Criteria andMaxEduNameNotLike(String value) {
            addCriterion("MAX_EDU_NAME not like", value, "maxEduName");
            return (Criteria) this;
        }

        public Criteria andMaxEduNameIn(List<String> values) {
            addCriterion("MAX_EDU_NAME in", values, "maxEduName");
            return (Criteria) this;
        }

        public Criteria andMaxEduNameNotIn(List<String> values) {
            addCriterion("MAX_EDU_NAME not in", values, "maxEduName");
            return (Criteria) this;
        }

        public Criteria andMaxEduNameBetween(String value1, String value2) {
            addCriterion("MAX_EDU_NAME between", value1, value2, "maxEduName");
            return (Criteria) this;
        }

        public Criteria andMaxEduNameNotBetween(String value1, String value2) {
            addCriterion("MAX_EDU_NAME not between", value1, value2, "maxEduName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameIsNull() {
            addCriterion("SCHOOL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSchoolNameIsNotNull() {
            addCriterion("SCHOOL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolNameEqualTo(String value) {
            addCriterion("SCHOOL_NAME =", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotEqualTo(String value) {
            addCriterion("SCHOOL_NAME <>", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameGreaterThan(String value) {
            addCriterion("SCHOOL_NAME >", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_NAME >=", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameLessThan(String value) {
            addCriterion("SCHOOL_NAME <", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_NAME <=", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameLike(String value) {
            addCriterion("SCHOOL_NAME like", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotLike(String value) {
            addCriterion("SCHOOL_NAME not like", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameIn(List<String> values) {
            addCriterion("SCHOOL_NAME in", values, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotIn(List<String> values) {
            addCriterion("SCHOOL_NAME not in", values, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameBetween(String value1, String value2) {
            addCriterion("SCHOOL_NAME between", value1, value2, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_NAME not between", value1, value2, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolSpeNameIsNull() {
            addCriterion("SCHOOL_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSchoolSpeNameIsNotNull() {
            addCriterion("SCHOOL_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolSpeNameEqualTo(String value) {
            addCriterion("SCHOOL_SPE_NAME =", value, "schoolSpeName");
            return (Criteria) this;
        }

        public Criteria andSchoolSpeNameNotEqualTo(String value) {
            addCriterion("SCHOOL_SPE_NAME <>", value, "schoolSpeName");
            return (Criteria) this;
        }

        public Criteria andSchoolSpeNameGreaterThan(String value) {
            addCriterion("SCHOOL_SPE_NAME >", value, "schoolSpeName");
            return (Criteria) this;
        }

        public Criteria andSchoolSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_SPE_NAME >=", value, "schoolSpeName");
            return (Criteria) this;
        }

        public Criteria andSchoolSpeNameLessThan(String value) {
            addCriterion("SCHOOL_SPE_NAME <", value, "schoolSpeName");
            return (Criteria) this;
        }

        public Criteria andSchoolSpeNameLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_SPE_NAME <=", value, "schoolSpeName");
            return (Criteria) this;
        }

        public Criteria andSchoolSpeNameLike(String value) {
            addCriterion("SCHOOL_SPE_NAME like", value, "schoolSpeName");
            return (Criteria) this;
        }

        public Criteria andSchoolSpeNameNotLike(String value) {
            addCriterion("SCHOOL_SPE_NAME not like", value, "schoolSpeName");
            return (Criteria) this;
        }

        public Criteria andSchoolSpeNameIn(List<String> values) {
            addCriterion("SCHOOL_SPE_NAME in", values, "schoolSpeName");
            return (Criteria) this;
        }

        public Criteria andSchoolSpeNameNotIn(List<String> values) {
            addCriterion("SCHOOL_SPE_NAME not in", values, "schoolSpeName");
            return (Criteria) this;
        }

        public Criteria andSchoolSpeNameBetween(String value1, String value2) {
            addCriterion("SCHOOL_SPE_NAME between", value1, value2, "schoolSpeName");
            return (Criteria) this;
        }

        public Criteria andSchoolSpeNameNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_SPE_NAME not between", value1, value2, "schoolSpeName");
            return (Criteria) this;
        }

        public Criteria andMaxEduBdateIsNull() {
            addCriterion("MAX_EDU_BDATE is null");
            return (Criteria) this;
        }

        public Criteria andMaxEduBdateIsNotNull() {
            addCriterion("MAX_EDU_BDATE is not null");
            return (Criteria) this;
        }

        public Criteria andMaxEduBdateEqualTo(String value) {
            addCriterion("MAX_EDU_BDATE =", value, "maxEduBdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduBdateNotEqualTo(String value) {
            addCriterion("MAX_EDU_BDATE <>", value, "maxEduBdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduBdateGreaterThan(String value) {
            addCriterion("MAX_EDU_BDATE >", value, "maxEduBdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduBdateGreaterThanOrEqualTo(String value) {
            addCriterion("MAX_EDU_BDATE >=", value, "maxEduBdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduBdateLessThan(String value) {
            addCriterion("MAX_EDU_BDATE <", value, "maxEduBdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduBdateLessThanOrEqualTo(String value) {
            addCriterion("MAX_EDU_BDATE <=", value, "maxEduBdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduBdateLike(String value) {
            addCriterion("MAX_EDU_BDATE like", value, "maxEduBdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduBdateNotLike(String value) {
            addCriterion("MAX_EDU_BDATE not like", value, "maxEduBdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduBdateIn(List<String> values) {
            addCriterion("MAX_EDU_BDATE in", values, "maxEduBdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduBdateNotIn(List<String> values) {
            addCriterion("MAX_EDU_BDATE not in", values, "maxEduBdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduBdateBetween(String value1, String value2) {
            addCriterion("MAX_EDU_BDATE between", value1, value2, "maxEduBdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduBdateNotBetween(String value1, String value2) {
            addCriterion("MAX_EDU_BDATE not between", value1, value2, "maxEduBdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduEdateIsNull() {
            addCriterion("MAX_EDU_EDATE is null");
            return (Criteria) this;
        }

        public Criteria andMaxEduEdateIsNotNull() {
            addCriterion("MAX_EDU_EDATE is not null");
            return (Criteria) this;
        }

        public Criteria andMaxEduEdateEqualTo(String value) {
            addCriterion("MAX_EDU_EDATE =", value, "maxEduEdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduEdateNotEqualTo(String value) {
            addCriterion("MAX_EDU_EDATE <>", value, "maxEduEdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduEdateGreaterThan(String value) {
            addCriterion("MAX_EDU_EDATE >", value, "maxEduEdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduEdateGreaterThanOrEqualTo(String value) {
            addCriterion("MAX_EDU_EDATE >=", value, "maxEduEdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduEdateLessThan(String value) {
            addCriterion("MAX_EDU_EDATE <", value, "maxEduEdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduEdateLessThanOrEqualTo(String value) {
            addCriterion("MAX_EDU_EDATE <=", value, "maxEduEdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduEdateLike(String value) {
            addCriterion("MAX_EDU_EDATE like", value, "maxEduEdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduEdateNotLike(String value) {
            addCriterion("MAX_EDU_EDATE not like", value, "maxEduEdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduEdateIn(List<String> values) {
            addCriterion("MAX_EDU_EDATE in", values, "maxEduEdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduEdateNotIn(List<String> values) {
            addCriterion("MAX_EDU_EDATE not in", values, "maxEduEdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduEdateBetween(String value1, String value2) {
            addCriterion("MAX_EDU_EDATE between", value1, value2, "maxEduEdate");
            return (Criteria) this;
        }

        public Criteria andMaxEduEdateNotBetween(String value1, String value2) {
            addCriterion("MAX_EDU_EDATE not between", value1, value2, "maxEduEdate");
            return (Criteria) this;
        }

        public Criteria andIsComputerIsNull() {
            addCriterion("IS_COMPUTER is null");
            return (Criteria) this;
        }

        public Criteria andIsComputerIsNotNull() {
            addCriterion("IS_COMPUTER is not null");
            return (Criteria) this;
        }

        public Criteria andIsComputerEqualTo(String value) {
            addCriterion("IS_COMPUTER =", value, "isComputer");
            return (Criteria) this;
        }

        public Criteria andIsComputerNotEqualTo(String value) {
            addCriterion("IS_COMPUTER <>", value, "isComputer");
            return (Criteria) this;
        }

        public Criteria andIsComputerGreaterThan(String value) {
            addCriterion("IS_COMPUTER >", value, "isComputer");
            return (Criteria) this;
        }

        public Criteria andIsComputerGreaterThanOrEqualTo(String value) {
            addCriterion("IS_COMPUTER >=", value, "isComputer");
            return (Criteria) this;
        }

        public Criteria andIsComputerLessThan(String value) {
            addCriterion("IS_COMPUTER <", value, "isComputer");
            return (Criteria) this;
        }

        public Criteria andIsComputerLessThanOrEqualTo(String value) {
            addCriterion("IS_COMPUTER <=", value, "isComputer");
            return (Criteria) this;
        }

        public Criteria andIsComputerLike(String value) {
            addCriterion("IS_COMPUTER like", value, "isComputer");
            return (Criteria) this;
        }

        public Criteria andIsComputerNotLike(String value) {
            addCriterion("IS_COMPUTER not like", value, "isComputer");
            return (Criteria) this;
        }

        public Criteria andIsComputerIn(List<String> values) {
            addCriterion("IS_COMPUTER in", values, "isComputer");
            return (Criteria) this;
        }

        public Criteria andIsComputerNotIn(List<String> values) {
            addCriterion("IS_COMPUTER not in", values, "isComputer");
            return (Criteria) this;
        }

        public Criteria andIsComputerBetween(String value1, String value2) {
            addCriterion("IS_COMPUTER between", value1, value2, "isComputer");
            return (Criteria) this;
        }

        public Criteria andIsComputerNotBetween(String value1, String value2) {
            addCriterion("IS_COMPUTER not between", value1, value2, "isComputer");
            return (Criteria) this;
        }

        public Criteria andSendComNameIsNull() {
            addCriterion("SEND_COM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSendComNameIsNotNull() {
            addCriterion("SEND_COM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSendComNameEqualTo(String value) {
            addCriterion("SEND_COM_NAME =", value, "sendComName");
            return (Criteria) this;
        }

        public Criteria andSendComNameNotEqualTo(String value) {
            addCriterion("SEND_COM_NAME <>", value, "sendComName");
            return (Criteria) this;
        }

        public Criteria andSendComNameGreaterThan(String value) {
            addCriterion("SEND_COM_NAME >", value, "sendComName");
            return (Criteria) this;
        }

        public Criteria andSendComNameGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_COM_NAME >=", value, "sendComName");
            return (Criteria) this;
        }

        public Criteria andSendComNameLessThan(String value) {
            addCriterion("SEND_COM_NAME <", value, "sendComName");
            return (Criteria) this;
        }

        public Criteria andSendComNameLessThanOrEqualTo(String value) {
            addCriterion("SEND_COM_NAME <=", value, "sendComName");
            return (Criteria) this;
        }

        public Criteria andSendComNameLike(String value) {
            addCriterion("SEND_COM_NAME like", value, "sendComName");
            return (Criteria) this;
        }

        public Criteria andSendComNameNotLike(String value) {
            addCriterion("SEND_COM_NAME not like", value, "sendComName");
            return (Criteria) this;
        }

        public Criteria andSendComNameIn(List<String> values) {
            addCriterion("SEND_COM_NAME in", values, "sendComName");
            return (Criteria) this;
        }

        public Criteria andSendComNameNotIn(List<String> values) {
            addCriterion("SEND_COM_NAME not in", values, "sendComName");
            return (Criteria) this;
        }

        public Criteria andSendComNameBetween(String value1, String value2) {
            addCriterion("SEND_COM_NAME between", value1, value2, "sendComName");
            return (Criteria) this;
        }

        public Criteria andSendComNameNotBetween(String value1, String value2) {
            addCriterion("SEND_COM_NAME not between", value1, value2, "sendComName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdIsNull() {
            addCriterion("HOSPITAL_LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdIsNotNull() {
            addCriterion("HOSPITAL_LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL_ID =", value, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdNotEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL_ID <>", value, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdGreaterThan(String value) {
            addCriterion("HOSPITAL_LEVEL_ID >", value, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL_ID >=", value, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdLessThan(String value) {
            addCriterion("HOSPITAL_LEVEL_ID <", value, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdLessThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL_ID <=", value, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdLike(String value) {
            addCriterion("HOSPITAL_LEVEL_ID like", value, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdNotLike(String value) {
            addCriterion("HOSPITAL_LEVEL_ID not like", value, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdIn(List<String> values) {
            addCriterion("HOSPITAL_LEVEL_ID in", values, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdNotIn(List<String> values) {
            addCriterion("HOSPITAL_LEVEL_ID not in", values, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdBetween(String value1, String value2) {
            addCriterion("HOSPITAL_LEVEL_ID between", value1, value2, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelIdNotBetween(String value1, String value2) {
            addCriterion("HOSPITAL_LEVEL_ID not between", value1, value2, "hospitalLevelId");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameIsNull() {
            addCriterion("HOSPITAL_LEVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameIsNotNull() {
            addCriterion("HOSPITAL_LEVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL_NAME =", value, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameNotEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL_NAME <>", value, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameGreaterThan(String value) {
            addCriterion("HOSPITAL_LEVEL_NAME >", value, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL_NAME >=", value, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameLessThan(String value) {
            addCriterion("HOSPITAL_LEVEL_NAME <", value, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameLessThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_LEVEL_NAME <=", value, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameLike(String value) {
            addCriterion("HOSPITAL_LEVEL_NAME like", value, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameNotLike(String value) {
            addCriterion("HOSPITAL_LEVEL_NAME not like", value, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameIn(List<String> values) {
            addCriterion("HOSPITAL_LEVEL_NAME in", values, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameNotIn(List<String> values) {
            addCriterion("HOSPITAL_LEVEL_NAME not in", values, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameBetween(String value1, String value2) {
            addCriterion("HOSPITAL_LEVEL_NAME between", value1, value2, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andHospitalLevelNameNotBetween(String value1, String value2) {
            addCriterion("HOSPITAL_LEVEL_NAME not between", value1, value2, "hospitalLevelName");
            return (Criteria) this;
        }

        public Criteria andSendComAddressIsNull() {
            addCriterion("SEND_COM_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andSendComAddressIsNotNull() {
            addCriterion("SEND_COM_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andSendComAddressEqualTo(String value) {
            addCriterion("SEND_COM_ADDRESS =", value, "sendComAddress");
            return (Criteria) this;
        }

        public Criteria andSendComAddressNotEqualTo(String value) {
            addCriterion("SEND_COM_ADDRESS <>", value, "sendComAddress");
            return (Criteria) this;
        }

        public Criteria andSendComAddressGreaterThan(String value) {
            addCriterion("SEND_COM_ADDRESS >", value, "sendComAddress");
            return (Criteria) this;
        }

        public Criteria andSendComAddressGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_COM_ADDRESS >=", value, "sendComAddress");
            return (Criteria) this;
        }

        public Criteria andSendComAddressLessThan(String value) {
            addCriterion("SEND_COM_ADDRESS <", value, "sendComAddress");
            return (Criteria) this;
        }

        public Criteria andSendComAddressLessThanOrEqualTo(String value) {
            addCriterion("SEND_COM_ADDRESS <=", value, "sendComAddress");
            return (Criteria) this;
        }

        public Criteria andSendComAddressLike(String value) {
            addCriterion("SEND_COM_ADDRESS like", value, "sendComAddress");
            return (Criteria) this;
        }

        public Criteria andSendComAddressNotLike(String value) {
            addCriterion("SEND_COM_ADDRESS not like", value, "sendComAddress");
            return (Criteria) this;
        }

        public Criteria andSendComAddressIn(List<String> values) {
            addCriterion("SEND_COM_ADDRESS in", values, "sendComAddress");
            return (Criteria) this;
        }

        public Criteria andSendComAddressNotIn(List<String> values) {
            addCriterion("SEND_COM_ADDRESS not in", values, "sendComAddress");
            return (Criteria) this;
        }

        public Criteria andSendComAddressBetween(String value1, String value2) {
            addCriterion("SEND_COM_ADDRESS between", value1, value2, "sendComAddress");
            return (Criteria) this;
        }

        public Criteria andSendComAddressNotBetween(String value1, String value2) {
            addCriterion("SEND_COM_ADDRESS not between", value1, value2, "sendComAddress");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdIsNull() {
            addCriterion("STU_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdIsNotNull() {
            addCriterion("STU_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdEqualTo(String value) {
            addCriterion("STU_STATUS_ID =", value, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdNotEqualTo(String value) {
            addCriterion("STU_STATUS_ID <>", value, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdGreaterThan(String value) {
            addCriterion("STU_STATUS_ID >", value, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("STU_STATUS_ID >=", value, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdLessThan(String value) {
            addCriterion("STU_STATUS_ID <", value, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdLessThanOrEqualTo(String value) {
            addCriterion("STU_STATUS_ID <=", value, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdLike(String value) {
            addCriterion("STU_STATUS_ID like", value, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdNotLike(String value) {
            addCriterion("STU_STATUS_ID not like", value, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdIn(List<String> values) {
            addCriterion("STU_STATUS_ID in", values, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdNotIn(List<String> values) {
            addCriterion("STU_STATUS_ID not in", values, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdBetween(String value1, String value2) {
            addCriterion("STU_STATUS_ID between", value1, value2, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusIdNotBetween(String value1, String value2) {
            addCriterion("STU_STATUS_ID not between", value1, value2, "stuStatusId");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameIsNull() {
            addCriterion("STU_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameIsNotNull() {
            addCriterion("STU_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameEqualTo(String value) {
            addCriterion("STU_STATUS_NAME =", value, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameNotEqualTo(String value) {
            addCriterion("STU_STATUS_NAME <>", value, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameGreaterThan(String value) {
            addCriterion("STU_STATUS_NAME >", value, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("STU_STATUS_NAME >=", value, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameLessThan(String value) {
            addCriterion("STU_STATUS_NAME <", value, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameLessThanOrEqualTo(String value) {
            addCriterion("STU_STATUS_NAME <=", value, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameLike(String value) {
            addCriterion("STU_STATUS_NAME like", value, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameNotLike(String value) {
            addCriterion("STU_STATUS_NAME not like", value, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameIn(List<String> values) {
            addCriterion("STU_STATUS_NAME in", values, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameNotIn(List<String> values) {
            addCriterion("STU_STATUS_NAME not in", values, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameBetween(String value1, String value2) {
            addCriterion("STU_STATUS_NAME between", value1, value2, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andStuStatusNameNotBetween(String value1, String value2) {
            addCriterion("STU_STATUS_NAME not between", value1, value2, "stuStatusName");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIsNull() {
            addCriterion("TEACHER_ID is null");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIsNotNull() {
            addCriterion("TEACHER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherIdEqualTo(String value) {
            addCriterion("TEACHER_ID =", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotEqualTo(String value) {
            addCriterion("TEACHER_ID <>", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdGreaterThan(String value) {
            addCriterion("TEACHER_ID >", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_ID >=", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLessThan(String value) {
            addCriterion("TEACHER_ID <", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_ID <=", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLike(String value) {
            addCriterion("TEACHER_ID like", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotLike(String value) {
            addCriterion("TEACHER_ID not like", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIn(List<String> values) {
            addCriterion("TEACHER_ID in", values, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotIn(List<String> values) {
            addCriterion("TEACHER_ID not in", values, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdBetween(String value1, String value2) {
            addCriterion("TEACHER_ID between", value1, value2, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotBetween(String value1, String value2) {
            addCriterion("TEACHER_ID not between", value1, value2, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIsNull() {
            addCriterion("TEACHER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIsNotNull() {
            addCriterion("TEACHER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameEqualTo(String value) {
            addCriterion("TEACHER_NAME =", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotEqualTo(String value) {
            addCriterion("TEACHER_NAME <>", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameGreaterThan(String value) {
            addCriterion("TEACHER_NAME >", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_NAME >=", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLessThan(String value) {
            addCriterion("TEACHER_NAME <", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_NAME <=", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLike(String value) {
            addCriterion("TEACHER_NAME like", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotLike(String value) {
            addCriterion("TEACHER_NAME not like", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIn(List<String> values) {
            addCriterion("TEACHER_NAME in", values, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotIn(List<String> values) {
            addCriterion("TEACHER_NAME not in", values, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameBetween(String value1, String value2) {
            addCriterion("TEACHER_NAME between", value1, value2, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotBetween(String value1, String value2) {
            addCriterion("TEACHER_NAME not between", value1, value2, "teacherName");
            return (Criteria) this;
        }

        public Criteria andIsGraduationIsNull() {
            addCriterion("IS_GRADUATION is null");
            return (Criteria) this;
        }

        public Criteria andIsGraduationIsNotNull() {
            addCriterion("IS_GRADUATION is not null");
            return (Criteria) this;
        }

        public Criteria andIsGraduationEqualTo(String value) {
            addCriterion("IS_GRADUATION =", value, "isGraduation");
            return (Criteria) this;
        }

        public Criteria andIsGraduationNotEqualTo(String value) {
            addCriterion("IS_GRADUATION <>", value, "isGraduation");
            return (Criteria) this;
        }

        public Criteria andIsGraduationGreaterThan(String value) {
            addCriterion("IS_GRADUATION >", value, "isGraduation");
            return (Criteria) this;
        }

        public Criteria andIsGraduationGreaterThanOrEqualTo(String value) {
            addCriterion("IS_GRADUATION >=", value, "isGraduation");
            return (Criteria) this;
        }

        public Criteria andIsGraduationLessThan(String value) {
            addCriterion("IS_GRADUATION <", value, "isGraduation");
            return (Criteria) this;
        }

        public Criteria andIsGraduationLessThanOrEqualTo(String value) {
            addCriterion("IS_GRADUATION <=", value, "isGraduation");
            return (Criteria) this;
        }

        public Criteria andIsGraduationLike(String value) {
            addCriterion("IS_GRADUATION like", value, "isGraduation");
            return (Criteria) this;
        }

        public Criteria andIsGraduationNotLike(String value) {
            addCriterion("IS_GRADUATION not like", value, "isGraduation");
            return (Criteria) this;
        }

        public Criteria andIsGraduationIn(List<String> values) {
            addCriterion("IS_GRADUATION in", values, "isGraduation");
            return (Criteria) this;
        }

        public Criteria andIsGraduationNotIn(List<String> values) {
            addCriterion("IS_GRADUATION not in", values, "isGraduation");
            return (Criteria) this;
        }

        public Criteria andIsGraduationBetween(String value1, String value2) {
            addCriterion("IS_GRADUATION between", value1, value2, "isGraduation");
            return (Criteria) this;
        }

        public Criteria andIsGraduationNotBetween(String value1, String value2) {
            addCriterion("IS_GRADUATION not between", value1, value2, "isGraduation");
            return (Criteria) this;
        }

        public Criteria andGraduationDateIsNull() {
            addCriterion("GRADUATION_DATE is null");
            return (Criteria) this;
        }

        public Criteria andGraduationDateIsNotNull() {
            addCriterion("GRADUATION_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationDateEqualTo(String value) {
            addCriterion("GRADUATION_DATE =", value, "graduationDate");
            return (Criteria) this;
        }

        public Criteria andGraduationDateNotEqualTo(String value) {
            addCriterion("GRADUATION_DATE <>", value, "graduationDate");
            return (Criteria) this;
        }

        public Criteria andGraduationDateGreaterThan(String value) {
            addCriterion("GRADUATION_DATE >", value, "graduationDate");
            return (Criteria) this;
        }

        public Criteria andGraduationDateGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_DATE >=", value, "graduationDate");
            return (Criteria) this;
        }

        public Criteria andGraduationDateLessThan(String value) {
            addCriterion("GRADUATION_DATE <", value, "graduationDate");
            return (Criteria) this;
        }

        public Criteria andGraduationDateLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_DATE <=", value, "graduationDate");
            return (Criteria) this;
        }

        public Criteria andGraduationDateLike(String value) {
            addCriterion("GRADUATION_DATE like", value, "graduationDate");
            return (Criteria) this;
        }

        public Criteria andGraduationDateNotLike(String value) {
            addCriterion("GRADUATION_DATE not like", value, "graduationDate");
            return (Criteria) this;
        }

        public Criteria andGraduationDateIn(List<String> values) {
            addCriterion("GRADUATION_DATE in", values, "graduationDate");
            return (Criteria) this;
        }

        public Criteria andGraduationDateNotIn(List<String> values) {
            addCriterion("GRADUATION_DATE not in", values, "graduationDate");
            return (Criteria) this;
        }

        public Criteria andGraduationDateBetween(String value1, String value2) {
            addCriterion("GRADUATION_DATE between", value1, value2, "graduationDate");
            return (Criteria) this;
        }

        public Criteria andGraduationDateNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_DATE not between", value1, value2, "graduationDate");
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

        public Criteria andIsPublishIsNull() {
            addCriterion("IS_PUBLISH is null");
            return (Criteria) this;
        }

        public Criteria andIsPublishIsNotNull() {
            addCriterion("IS_PUBLISH is not null");
            return (Criteria) this;
        }

        public Criteria andIsPublishEqualTo(String value) {
            addCriterion("IS_PUBLISH =", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishNotEqualTo(String value) {
            addCriterion("IS_PUBLISH <>", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishGreaterThan(String value) {
            addCriterion("IS_PUBLISH >", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishGreaterThanOrEqualTo(String value) {
            addCriterion("IS_PUBLISH >=", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishLessThan(String value) {
            addCriterion("IS_PUBLISH <", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishLessThanOrEqualTo(String value) {
            addCriterion("IS_PUBLISH <=", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishLike(String value) {
            addCriterion("IS_PUBLISH like", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishNotLike(String value) {
            addCriterion("IS_PUBLISH not like", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishIn(List<String> values) {
            addCriterion("IS_PUBLISH in", values, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishNotIn(List<String> values) {
            addCriterion("IS_PUBLISH not in", values, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishBetween(String value1, String value2) {
            addCriterion("IS_PUBLISH between", value1, value2, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishNotBetween(String value1, String value2) {
            addCriterion("IS_PUBLISH not between", value1, value2, "isPublish");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowIsNull() {
            addCriterion("TEACHER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowIsNotNull() {
            addCriterion("TEACHER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowEqualTo(String value) {
            addCriterion("TEACHER_FLOW =", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowNotEqualTo(String value) {
            addCriterion("TEACHER_FLOW <>", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowGreaterThan(String value) {
            addCriterion("TEACHER_FLOW >", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_FLOW >=", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowLessThan(String value) {
            addCriterion("TEACHER_FLOW <", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_FLOW <=", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowLike(String value) {
            addCriterion("TEACHER_FLOW like", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowNotLike(String value) {
            addCriterion("TEACHER_FLOW not like", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowIn(List<String> values) {
            addCriterion("TEACHER_FLOW in", values, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowNotIn(List<String> values) {
            addCriterion("TEACHER_FLOW not in", values, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowBetween(String value1, String value2) {
            addCriterion("TEACHER_FLOW between", value1, value2, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowNotBetween(String value1, String value2) {
            addCriterion("TEACHER_FLOW not between", value1, value2, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andIsAuditIsNull() {
            addCriterion("IS_AUDIT is null");
            return (Criteria) this;
        }

        public Criteria andIsAuditIsNotNull() {
            addCriterion("IS_AUDIT is not null");
            return (Criteria) this;
        }

        public Criteria andIsAuditEqualTo(String value) {
            addCriterion("IS_AUDIT =", value, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditNotEqualTo(String value) {
            addCriterion("IS_AUDIT <>", value, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditGreaterThan(String value) {
            addCriterion("IS_AUDIT >", value, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditGreaterThanOrEqualTo(String value) {
            addCriterion("IS_AUDIT >=", value, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditLessThan(String value) {
            addCriterion("IS_AUDIT <", value, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditLessThanOrEqualTo(String value) {
            addCriterion("IS_AUDIT <=", value, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditLike(String value) {
            addCriterion("IS_AUDIT like", value, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditNotLike(String value) {
            addCriterion("IS_AUDIT not like", value, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditIn(List<String> values) {
            addCriterion("IS_AUDIT in", values, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditNotIn(List<String> values) {
            addCriterion("IS_AUDIT not in", values, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditBetween(String value1, String value2) {
            addCriterion("IS_AUDIT between", value1, value2, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAuditNotBetween(String value1, String value2) {
            addCriterion("IS_AUDIT not between", value1, value2, "isAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdmitedIsNull() {
            addCriterion("IS_ADMITED is null");
            return (Criteria) this;
        }

        public Criteria andIsAdmitedIsNotNull() {
            addCriterion("IS_ADMITED is not null");
            return (Criteria) this;
        }

        public Criteria andIsAdmitedEqualTo(String value) {
            addCriterion("IS_ADMITED =", value, "isAdmited");
            return (Criteria) this;
        }

        public Criteria andIsAdmitedNotEqualTo(String value) {
            addCriterion("IS_ADMITED <>", value, "isAdmited");
            return (Criteria) this;
        }

        public Criteria andIsAdmitedGreaterThan(String value) {
            addCriterion("IS_ADMITED >", value, "isAdmited");
            return (Criteria) this;
        }

        public Criteria andIsAdmitedGreaterThanOrEqualTo(String value) {
            addCriterion("IS_ADMITED >=", value, "isAdmited");
            return (Criteria) this;
        }

        public Criteria andIsAdmitedLessThan(String value) {
            addCriterion("IS_ADMITED <", value, "isAdmited");
            return (Criteria) this;
        }

        public Criteria andIsAdmitedLessThanOrEqualTo(String value) {
            addCriterion("IS_ADMITED <=", value, "isAdmited");
            return (Criteria) this;
        }

        public Criteria andIsAdmitedLike(String value) {
            addCriterion("IS_ADMITED like", value, "isAdmited");
            return (Criteria) this;
        }

        public Criteria andIsAdmitedNotLike(String value) {
            addCriterion("IS_ADMITED not like", value, "isAdmited");
            return (Criteria) this;
        }

        public Criteria andIsAdmitedIn(List<String> values) {
            addCriterion("IS_ADMITED in", values, "isAdmited");
            return (Criteria) this;
        }

        public Criteria andIsAdmitedNotIn(List<String> values) {
            addCriterion("IS_ADMITED not in", values, "isAdmited");
            return (Criteria) this;
        }

        public Criteria andIsAdmitedBetween(String value1, String value2) {
            addCriterion("IS_ADMITED between", value1, value2, "isAdmited");
            return (Criteria) this;
        }

        public Criteria andIsAdmitedNotBetween(String value1, String value2) {
            addCriterion("IS_ADMITED not between", value1, value2, "isAdmited");
            return (Criteria) this;
        }

        public Criteria andIsBackIsNull() {
            addCriterion("IS_BACK is null");
            return (Criteria) this;
        }

        public Criteria andIsBackIsNotNull() {
            addCriterion("IS_BACK is not null");
            return (Criteria) this;
        }

        public Criteria andIsBackEqualTo(String value) {
            addCriterion("IS_BACK =", value, "isBack");
            return (Criteria) this;
        }

        public Criteria andIsBackNotEqualTo(String value) {
            addCriterion("IS_BACK <>", value, "isBack");
            return (Criteria) this;
        }

        public Criteria andIsBackGreaterThan(String value) {
            addCriterion("IS_BACK >", value, "isBack");
            return (Criteria) this;
        }

        public Criteria andIsBackGreaterThanOrEqualTo(String value) {
            addCriterion("IS_BACK >=", value, "isBack");
            return (Criteria) this;
        }

        public Criteria andIsBackLessThan(String value) {
            addCriterion("IS_BACK <", value, "isBack");
            return (Criteria) this;
        }

        public Criteria andIsBackLessThanOrEqualTo(String value) {
            addCriterion("IS_BACK <=", value, "isBack");
            return (Criteria) this;
        }

        public Criteria andIsBackLike(String value) {
            addCriterion("IS_BACK like", value, "isBack");
            return (Criteria) this;
        }

        public Criteria andIsBackNotLike(String value) {
            addCriterion("IS_BACK not like", value, "isBack");
            return (Criteria) this;
        }

        public Criteria andIsBackIn(List<String> values) {
            addCriterion("IS_BACK in", values, "isBack");
            return (Criteria) this;
        }

        public Criteria andIsBackNotIn(List<String> values) {
            addCriterion("IS_BACK not in", values, "isBack");
            return (Criteria) this;
        }

        public Criteria andIsBackBetween(String value1, String value2) {
            addCriterion("IS_BACK between", value1, value2, "isBack");
            return (Criteria) this;
        }

        public Criteria andIsBackNotBetween(String value1, String value2) {
            addCriterion("IS_BACK not between", value1, value2, "isBack");
            return (Criteria) this;
        }

        public Criteria andIsRecruitIsNull() {
            addCriterion("IS_RECRUIT is null");
            return (Criteria) this;
        }

        public Criteria andIsRecruitIsNotNull() {
            addCriterion("IS_RECRUIT is not null");
            return (Criteria) this;
        }

        public Criteria andIsRecruitEqualTo(String value) {
            addCriterion("IS_RECRUIT =", value, "isRecruit");
            return (Criteria) this;
        }

        public Criteria andIsRecruitNotEqualTo(String value) {
            addCriterion("IS_RECRUIT <>", value, "isRecruit");
            return (Criteria) this;
        }

        public Criteria andIsRecruitGreaterThan(String value) {
            addCriterion("IS_RECRUIT >", value, "isRecruit");
            return (Criteria) this;
        }

        public Criteria andIsRecruitGreaterThanOrEqualTo(String value) {
            addCriterion("IS_RECRUIT >=", value, "isRecruit");
            return (Criteria) this;
        }

        public Criteria andIsRecruitLessThan(String value) {
            addCriterion("IS_RECRUIT <", value, "isRecruit");
            return (Criteria) this;
        }

        public Criteria andIsRecruitLessThanOrEqualTo(String value) {
            addCriterion("IS_RECRUIT <=", value, "isRecruit");
            return (Criteria) this;
        }

        public Criteria andIsRecruitLike(String value) {
            addCriterion("IS_RECRUIT like", value, "isRecruit");
            return (Criteria) this;
        }

        public Criteria andIsRecruitNotLike(String value) {
            addCriterion("IS_RECRUIT not like", value, "isRecruit");
            return (Criteria) this;
        }

        public Criteria andIsRecruitIn(List<String> values) {
            addCriterion("IS_RECRUIT in", values, "isRecruit");
            return (Criteria) this;
        }

        public Criteria andIsRecruitNotIn(List<String> values) {
            addCriterion("IS_RECRUIT not in", values, "isRecruit");
            return (Criteria) this;
        }

        public Criteria andIsRecruitBetween(String value1, String value2) {
            addCriterion("IS_RECRUIT between", value1, value2, "isRecruit");
            return (Criteria) this;
        }

        public Criteria andIsRecruitNotBetween(String value1, String value2) {
            addCriterion("IS_RECRUIT not between", value1, value2, "isRecruit");
            return (Criteria) this;
        }

        public Criteria andIssueCertificateIsNull() {
            addCriterion("ISSUE_CERTIFICATE is null");
            return (Criteria) this;
        }

        public Criteria andIssueCertificateIsNotNull() {
            addCriterion("ISSUE_CERTIFICATE is not null");
            return (Criteria) this;
        }

        public Criteria andIssueCertificateEqualTo(String value) {
            addCriterion("ISSUE_CERTIFICATE =", value, "issueCertificate");
            return (Criteria) this;
        }

        public Criteria andIssueCertificateNotEqualTo(String value) {
            addCriterion("ISSUE_CERTIFICATE <>", value, "issueCertificate");
            return (Criteria) this;
        }

        public Criteria andIssueCertificateGreaterThan(String value) {
            addCriterion("ISSUE_CERTIFICATE >", value, "issueCertificate");
            return (Criteria) this;
        }

        public Criteria andIssueCertificateGreaterThanOrEqualTo(String value) {
            addCriterion("ISSUE_CERTIFICATE >=", value, "issueCertificate");
            return (Criteria) this;
        }

        public Criteria andIssueCertificateLessThan(String value) {
            addCriterion("ISSUE_CERTIFICATE <", value, "issueCertificate");
            return (Criteria) this;
        }

        public Criteria andIssueCertificateLessThanOrEqualTo(String value) {
            addCriterion("ISSUE_CERTIFICATE <=", value, "issueCertificate");
            return (Criteria) this;
        }

        public Criteria andIssueCertificateLike(String value) {
            addCriterion("ISSUE_CERTIFICATE like", value, "issueCertificate");
            return (Criteria) this;
        }

        public Criteria andIssueCertificateNotLike(String value) {
            addCriterion("ISSUE_CERTIFICATE not like", value, "issueCertificate");
            return (Criteria) this;
        }

        public Criteria andIssueCertificateIn(List<String> values) {
            addCriterion("ISSUE_CERTIFICATE in", values, "issueCertificate");
            return (Criteria) this;
        }

        public Criteria andIssueCertificateNotIn(List<String> values) {
            addCriterion("ISSUE_CERTIFICATE not in", values, "issueCertificate");
            return (Criteria) this;
        }

        public Criteria andIssueCertificateBetween(String value1, String value2) {
            addCriterion("ISSUE_CERTIFICATE between", value1, value2, "issueCertificate");
            return (Criteria) this;
        }

        public Criteria andIssueCertificateNotBetween(String value1, String value2) {
            addCriterion("ISSUE_CERTIFICATE not between", value1, value2, "issueCertificate");
            return (Criteria) this;
        }

        public Criteria andIsPublishAccommodationIsNull() {
            addCriterion("IS_PUBLISH_ACCOMMODATION is null");
            return (Criteria) this;
        }

        public Criteria andIsPublishAccommodationIsNotNull() {
            addCriterion("IS_PUBLISH_ACCOMMODATION is not null");
            return (Criteria) this;
        }

        public Criteria andIsPublishAccommodationEqualTo(String value) {
            addCriterion("IS_PUBLISH_ACCOMMODATION =", value, "isPublishAccommodation");
            return (Criteria) this;
        }

        public Criteria andIsPublishAccommodationNotEqualTo(String value) {
            addCriterion("IS_PUBLISH_ACCOMMODATION <>", value, "isPublishAccommodation");
            return (Criteria) this;
        }

        public Criteria andIsPublishAccommodationGreaterThan(String value) {
            addCriterion("IS_PUBLISH_ACCOMMODATION >", value, "isPublishAccommodation");
            return (Criteria) this;
        }

        public Criteria andIsPublishAccommodationGreaterThanOrEqualTo(String value) {
            addCriterion("IS_PUBLISH_ACCOMMODATION >=", value, "isPublishAccommodation");
            return (Criteria) this;
        }

        public Criteria andIsPublishAccommodationLessThan(String value) {
            addCriterion("IS_PUBLISH_ACCOMMODATION <", value, "isPublishAccommodation");
            return (Criteria) this;
        }

        public Criteria andIsPublishAccommodationLessThanOrEqualTo(String value) {
            addCriterion("IS_PUBLISH_ACCOMMODATION <=", value, "isPublishAccommodation");
            return (Criteria) this;
        }

        public Criteria andIsPublishAccommodationLike(String value) {
            addCriterion("IS_PUBLISH_ACCOMMODATION like", value, "isPublishAccommodation");
            return (Criteria) this;
        }

        public Criteria andIsPublishAccommodationNotLike(String value) {
            addCriterion("IS_PUBLISH_ACCOMMODATION not like", value, "isPublishAccommodation");
            return (Criteria) this;
        }

        public Criteria andIsPublishAccommodationIn(List<String> values) {
            addCriterion("IS_PUBLISH_ACCOMMODATION in", values, "isPublishAccommodation");
            return (Criteria) this;
        }

        public Criteria andIsPublishAccommodationNotIn(List<String> values) {
            addCriterion("IS_PUBLISH_ACCOMMODATION not in", values, "isPublishAccommodation");
            return (Criteria) this;
        }

        public Criteria andIsPublishAccommodationBetween(String value1, String value2) {
            addCriterion("IS_PUBLISH_ACCOMMODATION between", value1, value2, "isPublishAccommodation");
            return (Criteria) this;
        }

        public Criteria andIsPublishAccommodationNotBetween(String value1, String value2) {
            addCriterion("IS_PUBLISH_ACCOMMODATION not between", value1, value2, "isPublishAccommodation");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyIdIsNull() {
            addCriterion("UNITPROPERTY_ID is null");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyIdIsNotNull() {
            addCriterion("UNITPROPERTY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyIdEqualTo(String value) {
            addCriterion("UNITPROPERTY_ID =", value, "unitpropertyId");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyIdNotEqualTo(String value) {
            addCriterion("UNITPROPERTY_ID <>", value, "unitpropertyId");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyIdGreaterThan(String value) {
            addCriterion("UNITPROPERTY_ID >", value, "unitpropertyId");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyIdGreaterThanOrEqualTo(String value) {
            addCriterion("UNITPROPERTY_ID >=", value, "unitpropertyId");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyIdLessThan(String value) {
            addCriterion("UNITPROPERTY_ID <", value, "unitpropertyId");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyIdLessThanOrEqualTo(String value) {
            addCriterion("UNITPROPERTY_ID <=", value, "unitpropertyId");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyIdLike(String value) {
            addCriterion("UNITPROPERTY_ID like", value, "unitpropertyId");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyIdNotLike(String value) {
            addCriterion("UNITPROPERTY_ID not like", value, "unitpropertyId");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyIdIn(List<String> values) {
            addCriterion("UNITPROPERTY_ID in", values, "unitpropertyId");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyIdNotIn(List<String> values) {
            addCriterion("UNITPROPERTY_ID not in", values, "unitpropertyId");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyIdBetween(String value1, String value2) {
            addCriterion("UNITPROPERTY_ID between", value1, value2, "unitpropertyId");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyIdNotBetween(String value1, String value2) {
            addCriterion("UNITPROPERTY_ID not between", value1, value2, "unitpropertyId");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyNameIsNull() {
            addCriterion("UNITPROPERTY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyNameIsNotNull() {
            addCriterion("UNITPROPERTY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyNameEqualTo(String value) {
            addCriterion("UNITPROPERTY_NAME =", value, "unitpropertyName");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyNameNotEqualTo(String value) {
            addCriterion("UNITPROPERTY_NAME <>", value, "unitpropertyName");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyNameGreaterThan(String value) {
            addCriterion("UNITPROPERTY_NAME >", value, "unitpropertyName");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyNameGreaterThanOrEqualTo(String value) {
            addCriterion("UNITPROPERTY_NAME >=", value, "unitpropertyName");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyNameLessThan(String value) {
            addCriterion("UNITPROPERTY_NAME <", value, "unitpropertyName");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyNameLessThanOrEqualTo(String value) {
            addCriterion("UNITPROPERTY_NAME <=", value, "unitpropertyName");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyNameLike(String value) {
            addCriterion("UNITPROPERTY_NAME like", value, "unitpropertyName");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyNameNotLike(String value) {
            addCriterion("UNITPROPERTY_NAME not like", value, "unitpropertyName");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyNameIn(List<String> values) {
            addCriterion("UNITPROPERTY_NAME in", values, "unitpropertyName");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyNameNotIn(List<String> values) {
            addCriterion("UNITPROPERTY_NAME not in", values, "unitpropertyName");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyNameBetween(String value1, String value2) {
            addCriterion("UNITPROPERTY_NAME between", value1, value2, "unitpropertyName");
            return (Criteria) this;
        }

        public Criteria andUnitpropertyNameNotBetween(String value1, String value2) {
            addCriterion("UNITPROPERTY_NAME not between", value1, value2, "unitpropertyName");
            return (Criteria) this;
        }

        public Criteria andUnitrankIdIsNull() {
            addCriterion("UNITRANK_ID is null");
            return (Criteria) this;
        }

        public Criteria andUnitrankIdIsNotNull() {
            addCriterion("UNITRANK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUnitrankIdEqualTo(String value) {
            addCriterion("UNITRANK_ID =", value, "unitrankId");
            return (Criteria) this;
        }

        public Criteria andUnitrankIdNotEqualTo(String value) {
            addCriterion("UNITRANK_ID <>", value, "unitrankId");
            return (Criteria) this;
        }

        public Criteria andUnitrankIdGreaterThan(String value) {
            addCriterion("UNITRANK_ID >", value, "unitrankId");
            return (Criteria) this;
        }

        public Criteria andUnitrankIdGreaterThanOrEqualTo(String value) {
            addCriterion("UNITRANK_ID >=", value, "unitrankId");
            return (Criteria) this;
        }

        public Criteria andUnitrankIdLessThan(String value) {
            addCriterion("UNITRANK_ID <", value, "unitrankId");
            return (Criteria) this;
        }

        public Criteria andUnitrankIdLessThanOrEqualTo(String value) {
            addCriterion("UNITRANK_ID <=", value, "unitrankId");
            return (Criteria) this;
        }

        public Criteria andUnitrankIdLike(String value) {
            addCriterion("UNITRANK_ID like", value, "unitrankId");
            return (Criteria) this;
        }

        public Criteria andUnitrankIdNotLike(String value) {
            addCriterion("UNITRANK_ID not like", value, "unitrankId");
            return (Criteria) this;
        }

        public Criteria andUnitrankIdIn(List<String> values) {
            addCriterion("UNITRANK_ID in", values, "unitrankId");
            return (Criteria) this;
        }

        public Criteria andUnitrankIdNotIn(List<String> values) {
            addCriterion("UNITRANK_ID not in", values, "unitrankId");
            return (Criteria) this;
        }

        public Criteria andUnitrankIdBetween(String value1, String value2) {
            addCriterion("UNITRANK_ID between", value1, value2, "unitrankId");
            return (Criteria) this;
        }

        public Criteria andUnitrankIdNotBetween(String value1, String value2) {
            addCriterion("UNITRANK_ID not between", value1, value2, "unitrankId");
            return (Criteria) this;
        }

        public Criteria andUnitrankNameIsNull() {
            addCriterion("UNITRANK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUnitrankNameIsNotNull() {
            addCriterion("UNITRANK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUnitrankNameEqualTo(String value) {
            addCriterion("UNITRANK_NAME =", value, "unitrankName");
            return (Criteria) this;
        }

        public Criteria andUnitrankNameNotEqualTo(String value) {
            addCriterion("UNITRANK_NAME <>", value, "unitrankName");
            return (Criteria) this;
        }

        public Criteria andUnitrankNameGreaterThan(String value) {
            addCriterion("UNITRANK_NAME >", value, "unitrankName");
            return (Criteria) this;
        }

        public Criteria andUnitrankNameGreaterThanOrEqualTo(String value) {
            addCriterion("UNITRANK_NAME >=", value, "unitrankName");
            return (Criteria) this;
        }

        public Criteria andUnitrankNameLessThan(String value) {
            addCriterion("UNITRANK_NAME <", value, "unitrankName");
            return (Criteria) this;
        }

        public Criteria andUnitrankNameLessThanOrEqualTo(String value) {
            addCriterion("UNITRANK_NAME <=", value, "unitrankName");
            return (Criteria) this;
        }

        public Criteria andUnitrankNameLike(String value) {
            addCriterion("UNITRANK_NAME like", value, "unitrankName");
            return (Criteria) this;
        }

        public Criteria andUnitrankNameNotLike(String value) {
            addCriterion("UNITRANK_NAME not like", value, "unitrankName");
            return (Criteria) this;
        }

        public Criteria andUnitrankNameIn(List<String> values) {
            addCriterion("UNITRANK_NAME in", values, "unitrankName");
            return (Criteria) this;
        }

        public Criteria andUnitrankNameNotIn(List<String> values) {
            addCriterion("UNITRANK_NAME not in", values, "unitrankName");
            return (Criteria) this;
        }

        public Criteria andUnitrankNameBetween(String value1, String value2) {
            addCriterion("UNITRANK_NAME between", value1, value2, "unitrankName");
            return (Criteria) this;
        }

        public Criteria andUnitrankNameNotBetween(String value1, String value2) {
            addCriterion("UNITRANK_NAME not between", value1, value2, "unitrankName");
            return (Criteria) this;
        }

        public Criteria andUnitlevelIdIsNull() {
            addCriterion("UNITLEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andUnitlevelIdIsNotNull() {
            addCriterion("UNITLEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUnitlevelIdEqualTo(String value) {
            addCriterion("UNITLEVEL_ID =", value, "unitlevelId");
            return (Criteria) this;
        }

        public Criteria andUnitlevelIdNotEqualTo(String value) {
            addCriterion("UNITLEVEL_ID <>", value, "unitlevelId");
            return (Criteria) this;
        }

        public Criteria andUnitlevelIdGreaterThan(String value) {
            addCriterion("UNITLEVEL_ID >", value, "unitlevelId");
            return (Criteria) this;
        }

        public Criteria andUnitlevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("UNITLEVEL_ID >=", value, "unitlevelId");
            return (Criteria) this;
        }

        public Criteria andUnitlevelIdLessThan(String value) {
            addCriterion("UNITLEVEL_ID <", value, "unitlevelId");
            return (Criteria) this;
        }

        public Criteria andUnitlevelIdLessThanOrEqualTo(String value) {
            addCriterion("UNITLEVEL_ID <=", value, "unitlevelId");
            return (Criteria) this;
        }

        public Criteria andUnitlevelIdLike(String value) {
            addCriterion("UNITLEVEL_ID like", value, "unitlevelId");
            return (Criteria) this;
        }

        public Criteria andUnitlevelIdNotLike(String value) {
            addCriterion("UNITLEVEL_ID not like", value, "unitlevelId");
            return (Criteria) this;
        }

        public Criteria andUnitlevelIdIn(List<String> values) {
            addCriterion("UNITLEVEL_ID in", values, "unitlevelId");
            return (Criteria) this;
        }

        public Criteria andUnitlevelIdNotIn(List<String> values) {
            addCriterion("UNITLEVEL_ID not in", values, "unitlevelId");
            return (Criteria) this;
        }

        public Criteria andUnitlevelIdBetween(String value1, String value2) {
            addCriterion("UNITLEVEL_ID between", value1, value2, "unitlevelId");
            return (Criteria) this;
        }

        public Criteria andUnitlevelIdNotBetween(String value1, String value2) {
            addCriterion("UNITLEVEL_ID not between", value1, value2, "unitlevelId");
            return (Criteria) this;
        }

        public Criteria andUnitlevelNameIsNull() {
            addCriterion("UNITLEVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUnitlevelNameIsNotNull() {
            addCriterion("UNITLEVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUnitlevelNameEqualTo(String value) {
            addCriterion("UNITLEVEL_NAME =", value, "unitlevelName");
            return (Criteria) this;
        }

        public Criteria andUnitlevelNameNotEqualTo(String value) {
            addCriterion("UNITLEVEL_NAME <>", value, "unitlevelName");
            return (Criteria) this;
        }

        public Criteria andUnitlevelNameGreaterThan(String value) {
            addCriterion("UNITLEVEL_NAME >", value, "unitlevelName");
            return (Criteria) this;
        }

        public Criteria andUnitlevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("UNITLEVEL_NAME >=", value, "unitlevelName");
            return (Criteria) this;
        }

        public Criteria andUnitlevelNameLessThan(String value) {
            addCriterion("UNITLEVEL_NAME <", value, "unitlevelName");
            return (Criteria) this;
        }

        public Criteria andUnitlevelNameLessThanOrEqualTo(String value) {
            addCriterion("UNITLEVEL_NAME <=", value, "unitlevelName");
            return (Criteria) this;
        }

        public Criteria andUnitlevelNameLike(String value) {
            addCriterion("UNITLEVEL_NAME like", value, "unitlevelName");
            return (Criteria) this;
        }

        public Criteria andUnitlevelNameNotLike(String value) {
            addCriterion("UNITLEVEL_NAME not like", value, "unitlevelName");
            return (Criteria) this;
        }

        public Criteria andUnitlevelNameIn(List<String> values) {
            addCriterion("UNITLEVEL_NAME in", values, "unitlevelName");
            return (Criteria) this;
        }

        public Criteria andUnitlevelNameNotIn(List<String> values) {
            addCriterion("UNITLEVEL_NAME not in", values, "unitlevelName");
            return (Criteria) this;
        }

        public Criteria andUnitlevelNameBetween(String value1, String value2) {
            addCriterion("UNITLEVEL_NAME between", value1, value2, "unitlevelName");
            return (Criteria) this;
        }

        public Criteria andUnitlevelNameNotBetween(String value1, String value2) {
            addCriterion("UNITLEVEL_NAME not between", value1, value2, "unitlevelName");
            return (Criteria) this;
        }

        public Criteria andIdentificationNumberIsNull() {
            addCriterion("IDENTIFICATION_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andIdentificationNumberIsNotNull() {
            addCriterion("IDENTIFICATION_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andIdentificationNumberEqualTo(String value) {
            addCriterion("IDENTIFICATION_NUMBER =", value, "identificationNumber");
            return (Criteria) this;
        }

        public Criteria andIdentificationNumberNotEqualTo(String value) {
            addCriterion("IDENTIFICATION_NUMBER <>", value, "identificationNumber");
            return (Criteria) this;
        }

        public Criteria andIdentificationNumberGreaterThan(String value) {
            addCriterion("IDENTIFICATION_NUMBER >", value, "identificationNumber");
            return (Criteria) this;
        }

        public Criteria andIdentificationNumberGreaterThanOrEqualTo(String value) {
            addCriterion("IDENTIFICATION_NUMBER >=", value, "identificationNumber");
            return (Criteria) this;
        }

        public Criteria andIdentificationNumberLessThan(String value) {
            addCriterion("IDENTIFICATION_NUMBER <", value, "identificationNumber");
            return (Criteria) this;
        }

        public Criteria andIdentificationNumberLessThanOrEqualTo(String value) {
            addCriterion("IDENTIFICATION_NUMBER <=", value, "identificationNumber");
            return (Criteria) this;
        }

        public Criteria andIdentificationNumberLike(String value) {
            addCriterion("IDENTIFICATION_NUMBER like", value, "identificationNumber");
            return (Criteria) this;
        }

        public Criteria andIdentificationNumberNotLike(String value) {
            addCriterion("IDENTIFICATION_NUMBER not like", value, "identificationNumber");
            return (Criteria) this;
        }

        public Criteria andIdentificationNumberIn(List<String> values) {
            addCriterion("IDENTIFICATION_NUMBER in", values, "identificationNumber");
            return (Criteria) this;
        }

        public Criteria andIdentificationNumberNotIn(List<String> values) {
            addCriterion("IDENTIFICATION_NUMBER not in", values, "identificationNumber");
            return (Criteria) this;
        }

        public Criteria andIdentificationNumberBetween(String value1, String value2) {
            addCriterion("IDENTIFICATION_NUMBER between", value1, value2, "identificationNumber");
            return (Criteria) this;
        }

        public Criteria andIdentificationNumberNotBetween(String value1, String value2) {
            addCriterion("IDENTIFICATION_NUMBER not between", value1, value2, "identificationNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateDateIsNull() {
            addCriterion("CERTIFICATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCertificateDateIsNotNull() {
            addCriterion("CERTIFICATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateDateEqualTo(String value) {
            addCriterion("CERTIFICATE_DATE =", value, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateNotEqualTo(String value) {
            addCriterion("CERTIFICATE_DATE <>", value, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateGreaterThan(String value) {
            addCriterion("CERTIFICATE_DATE >", value, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_DATE >=", value, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateLessThan(String value) {
            addCriterion("CERTIFICATE_DATE <", value, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_DATE <=", value, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateLike(String value) {
            addCriterion("CERTIFICATE_DATE like", value, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateNotLike(String value) {
            addCriterion("CERTIFICATE_DATE not like", value, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateIn(List<String> values) {
            addCriterion("CERTIFICATE_DATE in", values, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateNotIn(List<String> values) {
            addCriterion("CERTIFICATE_DATE not in", values, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_DATE between", value1, value2, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andCertificateDateNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_DATE not between", value1, value2, "certificateDate");
            return (Criteria) this;
        }

        public Criteria andReportDateIsNull() {
            addCriterion("REPORT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andReportDateIsNotNull() {
            addCriterion("REPORT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andReportDateEqualTo(String value) {
            addCriterion("REPORT_DATE =", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotEqualTo(String value) {
            addCriterion("REPORT_DATE <>", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThan(String value) {
            addCriterion("REPORT_DATE >", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThanOrEqualTo(String value) {
            addCriterion("REPORT_DATE >=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThan(String value) {
            addCriterion("REPORT_DATE <", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThanOrEqualTo(String value) {
            addCriterion("REPORT_DATE <=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLike(String value) {
            addCriterion("REPORT_DATE like", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotLike(String value) {
            addCriterion("REPORT_DATE not like", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateIn(List<String> values) {
            addCriterion("REPORT_DATE in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotIn(List<String> values) {
            addCriterion("REPORT_DATE not in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateBetween(String value1, String value2) {
            addCriterion("REPORT_DATE between", value1, value2, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotBetween(String value1, String value2) {
            addCriterion("REPORT_DATE not between", value1, value2, "reportDate");
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