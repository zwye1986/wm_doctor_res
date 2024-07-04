package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ZseyHrKqMonthExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ZseyHrKqMonthExample() {
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

        public Criteria andMonthFlowIsNull() {
            addCriterion("MONTH_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andMonthFlowIsNotNull() {
            addCriterion("MONTH_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andMonthFlowEqualTo(String value) {
            addCriterion("MONTH_FLOW =", value, "monthFlow");
            return (Criteria) this;
        }

        public Criteria andMonthFlowNotEqualTo(String value) {
            addCriterion("MONTH_FLOW <>", value, "monthFlow");
            return (Criteria) this;
        }

        public Criteria andMonthFlowGreaterThan(String value) {
            addCriterion("MONTH_FLOW >", value, "monthFlow");
            return (Criteria) this;
        }

        public Criteria andMonthFlowGreaterThanOrEqualTo(String value) {
            addCriterion("MONTH_FLOW >=", value, "monthFlow");
            return (Criteria) this;
        }

        public Criteria andMonthFlowLessThan(String value) {
            addCriterion("MONTH_FLOW <", value, "monthFlow");
            return (Criteria) this;
        }

        public Criteria andMonthFlowLessThanOrEqualTo(String value) {
            addCriterion("MONTH_FLOW <=", value, "monthFlow");
            return (Criteria) this;
        }

        public Criteria andMonthFlowLike(String value) {
            addCriterion("MONTH_FLOW like", value, "monthFlow");
            return (Criteria) this;
        }

        public Criteria andMonthFlowNotLike(String value) {
            addCriterion("MONTH_FLOW not like", value, "monthFlow");
            return (Criteria) this;
        }

        public Criteria andMonthFlowIn(List<String> values) {
            addCriterion("MONTH_FLOW in", values, "monthFlow");
            return (Criteria) this;
        }

        public Criteria andMonthFlowNotIn(List<String> values) {
            addCriterion("MONTH_FLOW not in", values, "monthFlow");
            return (Criteria) this;
        }

        public Criteria andMonthFlowBetween(String value1, String value2) {
            addCriterion("MONTH_FLOW between", value1, value2, "monthFlow");
            return (Criteria) this;
        }

        public Criteria andMonthFlowNotBetween(String value1, String value2) {
            addCriterion("MONTH_FLOW not between", value1, value2, "monthFlow");
            return (Criteria) this;
        }

        public Criteria andResTypeIsNull() {
            addCriterion("RES_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andResTypeIsNotNull() {
            addCriterion("RES_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andResTypeEqualTo(String value) {
            addCriterion("RES_TYPE =", value, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeNotEqualTo(String value) {
            addCriterion("RES_TYPE <>", value, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeGreaterThan(String value) {
            addCriterion("RES_TYPE >", value, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RES_TYPE >=", value, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeLessThan(String value) {
            addCriterion("RES_TYPE <", value, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeLessThanOrEqualTo(String value) {
            addCriterion("RES_TYPE <=", value, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeLike(String value) {
            addCriterion("RES_TYPE like", value, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeNotLike(String value) {
            addCriterion("RES_TYPE not like", value, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeIn(List<String> values) {
            addCriterion("RES_TYPE in", values, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeNotIn(List<String> values) {
            addCriterion("RES_TYPE not in", values, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeBetween(String value1, String value2) {
            addCriterion("RES_TYPE between", value1, value2, "resType");
            return (Criteria) this;
        }

        public Criteria andResTypeNotBetween(String value1, String value2) {
            addCriterion("RES_TYPE not between", value1, value2, "resType");
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

        public Criteria andUserCodeIsNull() {
            addCriterion("USER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andUserCodeIsNotNull() {
            addCriterion("USER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andUserCodeEqualTo(String value) {
            addCriterion("USER_CODE =", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotEqualTo(String value) {
            addCriterion("USER_CODE <>", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeGreaterThan(String value) {
            addCriterion("USER_CODE >", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeGreaterThanOrEqualTo(String value) {
            addCriterion("USER_CODE >=", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeLessThan(String value) {
            addCriterion("USER_CODE <", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeLessThanOrEqualTo(String value) {
            addCriterion("USER_CODE <=", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeLike(String value) {
            addCriterion("USER_CODE like", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotLike(String value) {
            addCriterion("USER_CODE not like", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeIn(List<String> values) {
            addCriterion("USER_CODE in", values, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotIn(List<String> values) {
            addCriterion("USER_CODE not in", values, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeBetween(String value1, String value2) {
            addCriterion("USER_CODE between", value1, value2, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotBetween(String value1, String value2) {
            addCriterion("USER_CODE not between", value1, value2, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIsNull() {
            addCriterion("DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIsNotNull() {
            addCriterion("DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowEqualTo(String value) {
            addCriterion("DEPT_FLOW =", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotEqualTo(String value) {
            addCriterion("DEPT_FLOW <>", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThan(String value) {
            addCriterion("DEPT_FLOW >", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW >=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThan(String value) {
            addCriterion("DEPT_FLOW <", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW <=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLike(String value) {
            addCriterion("DEPT_FLOW like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotLike(String value) {
            addCriterion("DEPT_FLOW not like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIn(List<String> values) {
            addCriterion("DEPT_FLOW in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotIn(List<String> values) {
            addCriterion("DEPT_FLOW not in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW not between", value1, value2, "deptFlow");
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

        public Criteria andInDateIsNull() {
            addCriterion("IN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andInDateIsNotNull() {
            addCriterion("IN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andInDateEqualTo(String value) {
            addCriterion("IN_DATE =", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateNotEqualTo(String value) {
            addCriterion("IN_DATE <>", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateGreaterThan(String value) {
            addCriterion("IN_DATE >", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateGreaterThanOrEqualTo(String value) {
            addCriterion("IN_DATE >=", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateLessThan(String value) {
            addCriterion("IN_DATE <", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateLessThanOrEqualTo(String value) {
            addCriterion("IN_DATE <=", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateLike(String value) {
            addCriterion("IN_DATE like", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateNotLike(String value) {
            addCriterion("IN_DATE not like", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateIn(List<String> values) {
            addCriterion("IN_DATE in", values, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateNotIn(List<String> values) {
            addCriterion("IN_DATE not in", values, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateBetween(String value1, String value2) {
            addCriterion("IN_DATE between", value1, value2, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateNotBetween(String value1, String value2) {
            addCriterion("IN_DATE not between", value1, value2, "inDate");
            return (Criteria) this;
        }

        public Criteria andIdNoIsNull() {
            addCriterion("ID_NO is null");
            return (Criteria) this;
        }

        public Criteria andIdNoIsNotNull() {
            addCriterion("ID_NO is not null");
            return (Criteria) this;
        }

        public Criteria andIdNoEqualTo(String value) {
            addCriterion("ID_NO =", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotEqualTo(String value) {
            addCriterion("ID_NO <>", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThan(String value) {
            addCriterion("ID_NO >", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThanOrEqualTo(String value) {
            addCriterion("ID_NO >=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThan(String value) {
            addCriterion("ID_NO <", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThanOrEqualTo(String value) {
            addCriterion("ID_NO <=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLike(String value) {
            addCriterion("ID_NO like", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotLike(String value) {
            addCriterion("ID_NO not like", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoIn(List<String> values) {
            addCriterion("ID_NO in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotIn(List<String> values) {
            addCriterion("ID_NO not in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoBetween(String value1, String value2) {
            addCriterion("ID_NO between", value1, value2, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotBetween(String value1, String value2) {
            addCriterion("ID_NO not between", value1, value2, "idNo");
            return (Criteria) this;
        }

        public Criteria andM01IsNull() {
            addCriterion("M01 is null");
            return (Criteria) this;
        }

        public Criteria andM01IsNotNull() {
            addCriterion("M01 is not null");
            return (Criteria) this;
        }

        public Criteria andM01EqualTo(String value) {
            addCriterion("M01 =", value, "m01");
            return (Criteria) this;
        }

        public Criteria andM01NotEqualTo(String value) {
            addCriterion("M01 <>", value, "m01");
            return (Criteria) this;
        }

        public Criteria andM01GreaterThan(String value) {
            addCriterion("M01 >", value, "m01");
            return (Criteria) this;
        }

        public Criteria andM01GreaterThanOrEqualTo(String value) {
            addCriterion("M01 >=", value, "m01");
            return (Criteria) this;
        }

        public Criteria andM01LessThan(String value) {
            addCriterion("M01 <", value, "m01");
            return (Criteria) this;
        }

        public Criteria andM01LessThanOrEqualTo(String value) {
            addCriterion("M01 <=", value, "m01");
            return (Criteria) this;
        }

        public Criteria andM01Like(String value) {
            addCriterion("M01 like", value, "m01");
            return (Criteria) this;
        }

        public Criteria andM01NotLike(String value) {
            addCriterion("M01 not like", value, "m01");
            return (Criteria) this;
        }

        public Criteria andM01In(List<String> values) {
            addCriterion("M01 in", values, "m01");
            return (Criteria) this;
        }

        public Criteria andM01NotIn(List<String> values) {
            addCriterion("M01 not in", values, "m01");
            return (Criteria) this;
        }

        public Criteria andM01Between(String value1, String value2) {
            addCriterion("M01 between", value1, value2, "m01");
            return (Criteria) this;
        }

        public Criteria andM01NotBetween(String value1, String value2) {
            addCriterion("M01 not between", value1, value2, "m01");
            return (Criteria) this;
        }

        public Criteria andM02IsNull() {
            addCriterion("M02 is null");
            return (Criteria) this;
        }

        public Criteria andM02IsNotNull() {
            addCriterion("M02 is not null");
            return (Criteria) this;
        }

        public Criteria andM02EqualTo(String value) {
            addCriterion("M02 =", value, "m02");
            return (Criteria) this;
        }

        public Criteria andM02NotEqualTo(String value) {
            addCriterion("M02 <>", value, "m02");
            return (Criteria) this;
        }

        public Criteria andM02GreaterThan(String value) {
            addCriterion("M02 >", value, "m02");
            return (Criteria) this;
        }

        public Criteria andM02GreaterThanOrEqualTo(String value) {
            addCriterion("M02 >=", value, "m02");
            return (Criteria) this;
        }

        public Criteria andM02LessThan(String value) {
            addCriterion("M02 <", value, "m02");
            return (Criteria) this;
        }

        public Criteria andM02LessThanOrEqualTo(String value) {
            addCriterion("M02 <=", value, "m02");
            return (Criteria) this;
        }

        public Criteria andM02Like(String value) {
            addCriterion("M02 like", value, "m02");
            return (Criteria) this;
        }

        public Criteria andM02NotLike(String value) {
            addCriterion("M02 not like", value, "m02");
            return (Criteria) this;
        }

        public Criteria andM02In(List<String> values) {
            addCriterion("M02 in", values, "m02");
            return (Criteria) this;
        }

        public Criteria andM02NotIn(List<String> values) {
            addCriterion("M02 not in", values, "m02");
            return (Criteria) this;
        }

        public Criteria andM02Between(String value1, String value2) {
            addCriterion("M02 between", value1, value2, "m02");
            return (Criteria) this;
        }

        public Criteria andM02NotBetween(String value1, String value2) {
            addCriterion("M02 not between", value1, value2, "m02");
            return (Criteria) this;
        }

        public Criteria andM03IsNull() {
            addCriterion("M03 is null");
            return (Criteria) this;
        }

        public Criteria andM03IsNotNull() {
            addCriterion("M03 is not null");
            return (Criteria) this;
        }

        public Criteria andM03EqualTo(String value) {
            addCriterion("M03 =", value, "m03");
            return (Criteria) this;
        }

        public Criteria andM03NotEqualTo(String value) {
            addCriterion("M03 <>", value, "m03");
            return (Criteria) this;
        }

        public Criteria andM03GreaterThan(String value) {
            addCriterion("M03 >", value, "m03");
            return (Criteria) this;
        }

        public Criteria andM03GreaterThanOrEqualTo(String value) {
            addCriterion("M03 >=", value, "m03");
            return (Criteria) this;
        }

        public Criteria andM03LessThan(String value) {
            addCriterion("M03 <", value, "m03");
            return (Criteria) this;
        }

        public Criteria andM03LessThanOrEqualTo(String value) {
            addCriterion("M03 <=", value, "m03");
            return (Criteria) this;
        }

        public Criteria andM03Like(String value) {
            addCriterion("M03 like", value, "m03");
            return (Criteria) this;
        }

        public Criteria andM03NotLike(String value) {
            addCriterion("M03 not like", value, "m03");
            return (Criteria) this;
        }

        public Criteria andM03In(List<String> values) {
            addCriterion("M03 in", values, "m03");
            return (Criteria) this;
        }

        public Criteria andM03NotIn(List<String> values) {
            addCriterion("M03 not in", values, "m03");
            return (Criteria) this;
        }

        public Criteria andM03Between(String value1, String value2) {
            addCriterion("M03 between", value1, value2, "m03");
            return (Criteria) this;
        }

        public Criteria andM03NotBetween(String value1, String value2) {
            addCriterion("M03 not between", value1, value2, "m03");
            return (Criteria) this;
        }

        public Criteria andM04IsNull() {
            addCriterion("M04 is null");
            return (Criteria) this;
        }

        public Criteria andM04IsNotNull() {
            addCriterion("M04 is not null");
            return (Criteria) this;
        }

        public Criteria andM04EqualTo(String value) {
            addCriterion("M04 =", value, "m04");
            return (Criteria) this;
        }

        public Criteria andM04NotEqualTo(String value) {
            addCriterion("M04 <>", value, "m04");
            return (Criteria) this;
        }

        public Criteria andM04GreaterThan(String value) {
            addCriterion("M04 >", value, "m04");
            return (Criteria) this;
        }

        public Criteria andM04GreaterThanOrEqualTo(String value) {
            addCriterion("M04 >=", value, "m04");
            return (Criteria) this;
        }

        public Criteria andM04LessThan(String value) {
            addCriterion("M04 <", value, "m04");
            return (Criteria) this;
        }

        public Criteria andM04LessThanOrEqualTo(String value) {
            addCriterion("M04 <=", value, "m04");
            return (Criteria) this;
        }

        public Criteria andM04Like(String value) {
            addCriterion("M04 like", value, "m04");
            return (Criteria) this;
        }

        public Criteria andM04NotLike(String value) {
            addCriterion("M04 not like", value, "m04");
            return (Criteria) this;
        }

        public Criteria andM04In(List<String> values) {
            addCriterion("M04 in", values, "m04");
            return (Criteria) this;
        }

        public Criteria andM04NotIn(List<String> values) {
            addCriterion("M04 not in", values, "m04");
            return (Criteria) this;
        }

        public Criteria andM04Between(String value1, String value2) {
            addCriterion("M04 between", value1, value2, "m04");
            return (Criteria) this;
        }

        public Criteria andM04NotBetween(String value1, String value2) {
            addCriterion("M04 not between", value1, value2, "m04");
            return (Criteria) this;
        }

        public Criteria andM05IsNull() {
            addCriterion("M05 is null");
            return (Criteria) this;
        }

        public Criteria andM05IsNotNull() {
            addCriterion("M05 is not null");
            return (Criteria) this;
        }

        public Criteria andM05EqualTo(String value) {
            addCriterion("M05 =", value, "m05");
            return (Criteria) this;
        }

        public Criteria andM05NotEqualTo(String value) {
            addCriterion("M05 <>", value, "m05");
            return (Criteria) this;
        }

        public Criteria andM05GreaterThan(String value) {
            addCriterion("M05 >", value, "m05");
            return (Criteria) this;
        }

        public Criteria andM05GreaterThanOrEqualTo(String value) {
            addCriterion("M05 >=", value, "m05");
            return (Criteria) this;
        }

        public Criteria andM05LessThan(String value) {
            addCriterion("M05 <", value, "m05");
            return (Criteria) this;
        }

        public Criteria andM05LessThanOrEqualTo(String value) {
            addCriterion("M05 <=", value, "m05");
            return (Criteria) this;
        }

        public Criteria andM05Like(String value) {
            addCriterion("M05 like", value, "m05");
            return (Criteria) this;
        }

        public Criteria andM05NotLike(String value) {
            addCriterion("M05 not like", value, "m05");
            return (Criteria) this;
        }

        public Criteria andM05In(List<String> values) {
            addCriterion("M05 in", values, "m05");
            return (Criteria) this;
        }

        public Criteria andM05NotIn(List<String> values) {
            addCriterion("M05 not in", values, "m05");
            return (Criteria) this;
        }

        public Criteria andM05Between(String value1, String value2) {
            addCriterion("M05 between", value1, value2, "m05");
            return (Criteria) this;
        }

        public Criteria andM05NotBetween(String value1, String value2) {
            addCriterion("M05 not between", value1, value2, "m05");
            return (Criteria) this;
        }

        public Criteria andM06IsNull() {
            addCriterion("M06 is null");
            return (Criteria) this;
        }

        public Criteria andM06IsNotNull() {
            addCriterion("M06 is not null");
            return (Criteria) this;
        }

        public Criteria andM06EqualTo(String value) {
            addCriterion("M06 =", value, "m06");
            return (Criteria) this;
        }

        public Criteria andM06NotEqualTo(String value) {
            addCriterion("M06 <>", value, "m06");
            return (Criteria) this;
        }

        public Criteria andM06GreaterThan(String value) {
            addCriterion("M06 >", value, "m06");
            return (Criteria) this;
        }

        public Criteria andM06GreaterThanOrEqualTo(String value) {
            addCriterion("M06 >=", value, "m06");
            return (Criteria) this;
        }

        public Criteria andM06LessThan(String value) {
            addCriterion("M06 <", value, "m06");
            return (Criteria) this;
        }

        public Criteria andM06LessThanOrEqualTo(String value) {
            addCriterion("M06 <=", value, "m06");
            return (Criteria) this;
        }

        public Criteria andM06Like(String value) {
            addCriterion("M06 like", value, "m06");
            return (Criteria) this;
        }

        public Criteria andM06NotLike(String value) {
            addCriterion("M06 not like", value, "m06");
            return (Criteria) this;
        }

        public Criteria andM06In(List<String> values) {
            addCriterion("M06 in", values, "m06");
            return (Criteria) this;
        }

        public Criteria andM06NotIn(List<String> values) {
            addCriterion("M06 not in", values, "m06");
            return (Criteria) this;
        }

        public Criteria andM06Between(String value1, String value2) {
            addCriterion("M06 between", value1, value2, "m06");
            return (Criteria) this;
        }

        public Criteria andM06NotBetween(String value1, String value2) {
            addCriterion("M06 not between", value1, value2, "m06");
            return (Criteria) this;
        }

        public Criteria andM07IsNull() {
            addCriterion("M07 is null");
            return (Criteria) this;
        }

        public Criteria andM07IsNotNull() {
            addCriterion("M07 is not null");
            return (Criteria) this;
        }

        public Criteria andM07EqualTo(String value) {
            addCriterion("M07 =", value, "m07");
            return (Criteria) this;
        }

        public Criteria andM07NotEqualTo(String value) {
            addCriterion("M07 <>", value, "m07");
            return (Criteria) this;
        }

        public Criteria andM07GreaterThan(String value) {
            addCriterion("M07 >", value, "m07");
            return (Criteria) this;
        }

        public Criteria andM07GreaterThanOrEqualTo(String value) {
            addCriterion("M07 >=", value, "m07");
            return (Criteria) this;
        }

        public Criteria andM07LessThan(String value) {
            addCriterion("M07 <", value, "m07");
            return (Criteria) this;
        }

        public Criteria andM07LessThanOrEqualTo(String value) {
            addCriterion("M07 <=", value, "m07");
            return (Criteria) this;
        }

        public Criteria andM07Like(String value) {
            addCriterion("M07 like", value, "m07");
            return (Criteria) this;
        }

        public Criteria andM07NotLike(String value) {
            addCriterion("M07 not like", value, "m07");
            return (Criteria) this;
        }

        public Criteria andM07In(List<String> values) {
            addCriterion("M07 in", values, "m07");
            return (Criteria) this;
        }

        public Criteria andM07NotIn(List<String> values) {
            addCriterion("M07 not in", values, "m07");
            return (Criteria) this;
        }

        public Criteria andM07Between(String value1, String value2) {
            addCriterion("M07 between", value1, value2, "m07");
            return (Criteria) this;
        }

        public Criteria andM07NotBetween(String value1, String value2) {
            addCriterion("M07 not between", value1, value2, "m07");
            return (Criteria) this;
        }

        public Criteria andM08IsNull() {
            addCriterion("M08 is null");
            return (Criteria) this;
        }

        public Criteria andM08IsNotNull() {
            addCriterion("M08 is not null");
            return (Criteria) this;
        }

        public Criteria andM08EqualTo(String value) {
            addCriterion("M08 =", value, "m08");
            return (Criteria) this;
        }

        public Criteria andM08NotEqualTo(String value) {
            addCriterion("M08 <>", value, "m08");
            return (Criteria) this;
        }

        public Criteria andM08GreaterThan(String value) {
            addCriterion("M08 >", value, "m08");
            return (Criteria) this;
        }

        public Criteria andM08GreaterThanOrEqualTo(String value) {
            addCriterion("M08 >=", value, "m08");
            return (Criteria) this;
        }

        public Criteria andM08LessThan(String value) {
            addCriterion("M08 <", value, "m08");
            return (Criteria) this;
        }

        public Criteria andM08LessThanOrEqualTo(String value) {
            addCriterion("M08 <=", value, "m08");
            return (Criteria) this;
        }

        public Criteria andM08Like(String value) {
            addCriterion("M08 like", value, "m08");
            return (Criteria) this;
        }

        public Criteria andM08NotLike(String value) {
            addCriterion("M08 not like", value, "m08");
            return (Criteria) this;
        }

        public Criteria andM08In(List<String> values) {
            addCriterion("M08 in", values, "m08");
            return (Criteria) this;
        }

        public Criteria andM08NotIn(List<String> values) {
            addCriterion("M08 not in", values, "m08");
            return (Criteria) this;
        }

        public Criteria andM08Between(String value1, String value2) {
            addCriterion("M08 between", value1, value2, "m08");
            return (Criteria) this;
        }

        public Criteria andM08NotBetween(String value1, String value2) {
            addCriterion("M08 not between", value1, value2, "m08");
            return (Criteria) this;
        }

        public Criteria andM09IsNull() {
            addCriterion("M09 is null");
            return (Criteria) this;
        }

        public Criteria andM09IsNotNull() {
            addCriterion("M09 is not null");
            return (Criteria) this;
        }

        public Criteria andM09EqualTo(String value) {
            addCriterion("M09 =", value, "m09");
            return (Criteria) this;
        }

        public Criteria andM09NotEqualTo(String value) {
            addCriterion("M09 <>", value, "m09");
            return (Criteria) this;
        }

        public Criteria andM09GreaterThan(String value) {
            addCriterion("M09 >", value, "m09");
            return (Criteria) this;
        }

        public Criteria andM09GreaterThanOrEqualTo(String value) {
            addCriterion("M09 >=", value, "m09");
            return (Criteria) this;
        }

        public Criteria andM09LessThan(String value) {
            addCriterion("M09 <", value, "m09");
            return (Criteria) this;
        }

        public Criteria andM09LessThanOrEqualTo(String value) {
            addCriterion("M09 <=", value, "m09");
            return (Criteria) this;
        }

        public Criteria andM09Like(String value) {
            addCriterion("M09 like", value, "m09");
            return (Criteria) this;
        }

        public Criteria andM09NotLike(String value) {
            addCriterion("M09 not like", value, "m09");
            return (Criteria) this;
        }

        public Criteria andM09In(List<String> values) {
            addCriterion("M09 in", values, "m09");
            return (Criteria) this;
        }

        public Criteria andM09NotIn(List<String> values) {
            addCriterion("M09 not in", values, "m09");
            return (Criteria) this;
        }

        public Criteria andM09Between(String value1, String value2) {
            addCriterion("M09 between", value1, value2, "m09");
            return (Criteria) this;
        }

        public Criteria andM09NotBetween(String value1, String value2) {
            addCriterion("M09 not between", value1, value2, "m09");
            return (Criteria) this;
        }

        public Criteria andM10IsNull() {
            addCriterion("M10 is null");
            return (Criteria) this;
        }

        public Criteria andM10IsNotNull() {
            addCriterion("M10 is not null");
            return (Criteria) this;
        }

        public Criteria andM10EqualTo(String value) {
            addCriterion("M10 =", value, "m10");
            return (Criteria) this;
        }

        public Criteria andM10NotEqualTo(String value) {
            addCriterion("M10 <>", value, "m10");
            return (Criteria) this;
        }

        public Criteria andM10GreaterThan(String value) {
            addCriterion("M10 >", value, "m10");
            return (Criteria) this;
        }

        public Criteria andM10GreaterThanOrEqualTo(String value) {
            addCriterion("M10 >=", value, "m10");
            return (Criteria) this;
        }

        public Criteria andM10LessThan(String value) {
            addCriterion("M10 <", value, "m10");
            return (Criteria) this;
        }

        public Criteria andM10LessThanOrEqualTo(String value) {
            addCriterion("M10 <=", value, "m10");
            return (Criteria) this;
        }

        public Criteria andM10Like(String value) {
            addCriterion("M10 like", value, "m10");
            return (Criteria) this;
        }

        public Criteria andM10NotLike(String value) {
            addCriterion("M10 not like", value, "m10");
            return (Criteria) this;
        }

        public Criteria andM10In(List<String> values) {
            addCriterion("M10 in", values, "m10");
            return (Criteria) this;
        }

        public Criteria andM10NotIn(List<String> values) {
            addCriterion("M10 not in", values, "m10");
            return (Criteria) this;
        }

        public Criteria andM10Between(String value1, String value2) {
            addCriterion("M10 between", value1, value2, "m10");
            return (Criteria) this;
        }

        public Criteria andM10NotBetween(String value1, String value2) {
            addCriterion("M10 not between", value1, value2, "m10");
            return (Criteria) this;
        }

        public Criteria andM11IsNull() {
            addCriterion("M11 is null");
            return (Criteria) this;
        }

        public Criteria andM11IsNotNull() {
            addCriterion("M11 is not null");
            return (Criteria) this;
        }

        public Criteria andM11EqualTo(String value) {
            addCriterion("M11 =", value, "m11");
            return (Criteria) this;
        }

        public Criteria andM11NotEqualTo(String value) {
            addCriterion("M11 <>", value, "m11");
            return (Criteria) this;
        }

        public Criteria andM11GreaterThan(String value) {
            addCriterion("M11 >", value, "m11");
            return (Criteria) this;
        }

        public Criteria andM11GreaterThanOrEqualTo(String value) {
            addCriterion("M11 >=", value, "m11");
            return (Criteria) this;
        }

        public Criteria andM11LessThan(String value) {
            addCriterion("M11 <", value, "m11");
            return (Criteria) this;
        }

        public Criteria andM11LessThanOrEqualTo(String value) {
            addCriterion("M11 <=", value, "m11");
            return (Criteria) this;
        }

        public Criteria andM11Like(String value) {
            addCriterion("M11 like", value, "m11");
            return (Criteria) this;
        }

        public Criteria andM11NotLike(String value) {
            addCriterion("M11 not like", value, "m11");
            return (Criteria) this;
        }

        public Criteria andM11In(List<String> values) {
            addCriterion("M11 in", values, "m11");
            return (Criteria) this;
        }

        public Criteria andM11NotIn(List<String> values) {
            addCriterion("M11 not in", values, "m11");
            return (Criteria) this;
        }

        public Criteria andM11Between(String value1, String value2) {
            addCriterion("M11 between", value1, value2, "m11");
            return (Criteria) this;
        }

        public Criteria andM11NotBetween(String value1, String value2) {
            addCriterion("M11 not between", value1, value2, "m11");
            return (Criteria) this;
        }

        public Criteria andM12IsNull() {
            addCriterion("M12 is null");
            return (Criteria) this;
        }

        public Criteria andM12IsNotNull() {
            addCriterion("M12 is not null");
            return (Criteria) this;
        }

        public Criteria andM12EqualTo(String value) {
            addCriterion("M12 =", value, "m12");
            return (Criteria) this;
        }

        public Criteria andM12NotEqualTo(String value) {
            addCriterion("M12 <>", value, "m12");
            return (Criteria) this;
        }

        public Criteria andM12GreaterThan(String value) {
            addCriterion("M12 >", value, "m12");
            return (Criteria) this;
        }

        public Criteria andM12GreaterThanOrEqualTo(String value) {
            addCriterion("M12 >=", value, "m12");
            return (Criteria) this;
        }

        public Criteria andM12LessThan(String value) {
            addCriterion("M12 <", value, "m12");
            return (Criteria) this;
        }

        public Criteria andM12LessThanOrEqualTo(String value) {
            addCriterion("M12 <=", value, "m12");
            return (Criteria) this;
        }

        public Criteria andM12Like(String value) {
            addCriterion("M12 like", value, "m12");
            return (Criteria) this;
        }

        public Criteria andM12NotLike(String value) {
            addCriterion("M12 not like", value, "m12");
            return (Criteria) this;
        }

        public Criteria andM12In(List<String> values) {
            addCriterion("M12 in", values, "m12");
            return (Criteria) this;
        }

        public Criteria andM12NotIn(List<String> values) {
            addCriterion("M12 not in", values, "m12");
            return (Criteria) this;
        }

        public Criteria andM12Between(String value1, String value2) {
            addCriterion("M12 between", value1, value2, "m12");
            return (Criteria) this;
        }

        public Criteria andM12NotBetween(String value1, String value2) {
            addCriterion("M12 not between", value1, value2, "m12");
            return (Criteria) this;
        }

        public Criteria andM13IsNull() {
            addCriterion("M13 is null");
            return (Criteria) this;
        }

        public Criteria andM13IsNotNull() {
            addCriterion("M13 is not null");
            return (Criteria) this;
        }

        public Criteria andM13EqualTo(String value) {
            addCriterion("M13 =", value, "m13");
            return (Criteria) this;
        }

        public Criteria andM13NotEqualTo(String value) {
            addCriterion("M13 <>", value, "m13");
            return (Criteria) this;
        }

        public Criteria andM13GreaterThan(String value) {
            addCriterion("M13 >", value, "m13");
            return (Criteria) this;
        }

        public Criteria andM13GreaterThanOrEqualTo(String value) {
            addCriterion("M13 >=", value, "m13");
            return (Criteria) this;
        }

        public Criteria andM13LessThan(String value) {
            addCriterion("M13 <", value, "m13");
            return (Criteria) this;
        }

        public Criteria andM13LessThanOrEqualTo(String value) {
            addCriterion("M13 <=", value, "m13");
            return (Criteria) this;
        }

        public Criteria andM13Like(String value) {
            addCriterion("M13 like", value, "m13");
            return (Criteria) this;
        }

        public Criteria andM13NotLike(String value) {
            addCriterion("M13 not like", value, "m13");
            return (Criteria) this;
        }

        public Criteria andM13In(List<String> values) {
            addCriterion("M13 in", values, "m13");
            return (Criteria) this;
        }

        public Criteria andM13NotIn(List<String> values) {
            addCriterion("M13 not in", values, "m13");
            return (Criteria) this;
        }

        public Criteria andM13Between(String value1, String value2) {
            addCriterion("M13 between", value1, value2, "m13");
            return (Criteria) this;
        }

        public Criteria andM13NotBetween(String value1, String value2) {
            addCriterion("M13 not between", value1, value2, "m13");
            return (Criteria) this;
        }

        public Criteria andM14IsNull() {
            addCriterion("M14 is null");
            return (Criteria) this;
        }

        public Criteria andM14IsNotNull() {
            addCriterion("M14 is not null");
            return (Criteria) this;
        }

        public Criteria andM14EqualTo(String value) {
            addCriterion("M14 =", value, "m14");
            return (Criteria) this;
        }

        public Criteria andM14NotEqualTo(String value) {
            addCriterion("M14 <>", value, "m14");
            return (Criteria) this;
        }

        public Criteria andM14GreaterThan(String value) {
            addCriterion("M14 >", value, "m14");
            return (Criteria) this;
        }

        public Criteria andM14GreaterThanOrEqualTo(String value) {
            addCriterion("M14 >=", value, "m14");
            return (Criteria) this;
        }

        public Criteria andM14LessThan(String value) {
            addCriterion("M14 <", value, "m14");
            return (Criteria) this;
        }

        public Criteria andM14LessThanOrEqualTo(String value) {
            addCriterion("M14 <=", value, "m14");
            return (Criteria) this;
        }

        public Criteria andM14Like(String value) {
            addCriterion("M14 like", value, "m14");
            return (Criteria) this;
        }

        public Criteria andM14NotLike(String value) {
            addCriterion("M14 not like", value, "m14");
            return (Criteria) this;
        }

        public Criteria andM14In(List<String> values) {
            addCriterion("M14 in", values, "m14");
            return (Criteria) this;
        }

        public Criteria andM14NotIn(List<String> values) {
            addCriterion("M14 not in", values, "m14");
            return (Criteria) this;
        }

        public Criteria andM14Between(String value1, String value2) {
            addCriterion("M14 between", value1, value2, "m14");
            return (Criteria) this;
        }

        public Criteria andM14NotBetween(String value1, String value2) {
            addCriterion("M14 not between", value1, value2, "m14");
            return (Criteria) this;
        }

        public Criteria andM15IsNull() {
            addCriterion("M15 is null");
            return (Criteria) this;
        }

        public Criteria andM15IsNotNull() {
            addCriterion("M15 is not null");
            return (Criteria) this;
        }

        public Criteria andM15EqualTo(String value) {
            addCriterion("M15 =", value, "m15");
            return (Criteria) this;
        }

        public Criteria andM15NotEqualTo(String value) {
            addCriterion("M15 <>", value, "m15");
            return (Criteria) this;
        }

        public Criteria andM15GreaterThan(String value) {
            addCriterion("M15 >", value, "m15");
            return (Criteria) this;
        }

        public Criteria andM15GreaterThanOrEqualTo(String value) {
            addCriterion("M15 >=", value, "m15");
            return (Criteria) this;
        }

        public Criteria andM15LessThan(String value) {
            addCriterion("M15 <", value, "m15");
            return (Criteria) this;
        }

        public Criteria andM15LessThanOrEqualTo(String value) {
            addCriterion("M15 <=", value, "m15");
            return (Criteria) this;
        }

        public Criteria andM15Like(String value) {
            addCriterion("M15 like", value, "m15");
            return (Criteria) this;
        }

        public Criteria andM15NotLike(String value) {
            addCriterion("M15 not like", value, "m15");
            return (Criteria) this;
        }

        public Criteria andM15In(List<String> values) {
            addCriterion("M15 in", values, "m15");
            return (Criteria) this;
        }

        public Criteria andM15NotIn(List<String> values) {
            addCriterion("M15 not in", values, "m15");
            return (Criteria) this;
        }

        public Criteria andM15Between(String value1, String value2) {
            addCriterion("M15 between", value1, value2, "m15");
            return (Criteria) this;
        }

        public Criteria andM15NotBetween(String value1, String value2) {
            addCriterion("M15 not between", value1, value2, "m15");
            return (Criteria) this;
        }

        public Criteria andM16IsNull() {
            addCriterion("M16 is null");
            return (Criteria) this;
        }

        public Criteria andM16IsNotNull() {
            addCriterion("M16 is not null");
            return (Criteria) this;
        }

        public Criteria andM16EqualTo(String value) {
            addCriterion("M16 =", value, "m16");
            return (Criteria) this;
        }

        public Criteria andM16NotEqualTo(String value) {
            addCriterion("M16 <>", value, "m16");
            return (Criteria) this;
        }

        public Criteria andM16GreaterThan(String value) {
            addCriterion("M16 >", value, "m16");
            return (Criteria) this;
        }

        public Criteria andM16GreaterThanOrEqualTo(String value) {
            addCriterion("M16 >=", value, "m16");
            return (Criteria) this;
        }

        public Criteria andM16LessThan(String value) {
            addCriterion("M16 <", value, "m16");
            return (Criteria) this;
        }

        public Criteria andM16LessThanOrEqualTo(String value) {
            addCriterion("M16 <=", value, "m16");
            return (Criteria) this;
        }

        public Criteria andM16Like(String value) {
            addCriterion("M16 like", value, "m16");
            return (Criteria) this;
        }

        public Criteria andM16NotLike(String value) {
            addCriterion("M16 not like", value, "m16");
            return (Criteria) this;
        }

        public Criteria andM16In(List<String> values) {
            addCriterion("M16 in", values, "m16");
            return (Criteria) this;
        }

        public Criteria andM16NotIn(List<String> values) {
            addCriterion("M16 not in", values, "m16");
            return (Criteria) this;
        }

        public Criteria andM16Between(String value1, String value2) {
            addCriterion("M16 between", value1, value2, "m16");
            return (Criteria) this;
        }

        public Criteria andM16NotBetween(String value1, String value2) {
            addCriterion("M16 not between", value1, value2, "m16");
            return (Criteria) this;
        }

        public Criteria andM17IsNull() {
            addCriterion("M17 is null");
            return (Criteria) this;
        }

        public Criteria andM17IsNotNull() {
            addCriterion("M17 is not null");
            return (Criteria) this;
        }

        public Criteria andM17EqualTo(String value) {
            addCriterion("M17 =", value, "m17");
            return (Criteria) this;
        }

        public Criteria andM17NotEqualTo(String value) {
            addCriterion("M17 <>", value, "m17");
            return (Criteria) this;
        }

        public Criteria andM17GreaterThan(String value) {
            addCriterion("M17 >", value, "m17");
            return (Criteria) this;
        }

        public Criteria andM17GreaterThanOrEqualTo(String value) {
            addCriterion("M17 >=", value, "m17");
            return (Criteria) this;
        }

        public Criteria andM17LessThan(String value) {
            addCriterion("M17 <", value, "m17");
            return (Criteria) this;
        }

        public Criteria andM17LessThanOrEqualTo(String value) {
            addCriterion("M17 <=", value, "m17");
            return (Criteria) this;
        }

        public Criteria andM17Like(String value) {
            addCriterion("M17 like", value, "m17");
            return (Criteria) this;
        }

        public Criteria andM17NotLike(String value) {
            addCriterion("M17 not like", value, "m17");
            return (Criteria) this;
        }

        public Criteria andM17In(List<String> values) {
            addCriterion("M17 in", values, "m17");
            return (Criteria) this;
        }

        public Criteria andM17NotIn(List<String> values) {
            addCriterion("M17 not in", values, "m17");
            return (Criteria) this;
        }

        public Criteria andM17Between(String value1, String value2) {
            addCriterion("M17 between", value1, value2, "m17");
            return (Criteria) this;
        }

        public Criteria andM17NotBetween(String value1, String value2) {
            addCriterion("M17 not between", value1, value2, "m17");
            return (Criteria) this;
        }

        public Criteria andM18IsNull() {
            addCriterion("M18 is null");
            return (Criteria) this;
        }

        public Criteria andM18IsNotNull() {
            addCriterion("M18 is not null");
            return (Criteria) this;
        }

        public Criteria andM18EqualTo(String value) {
            addCriterion("M18 =", value, "m18");
            return (Criteria) this;
        }

        public Criteria andM18NotEqualTo(String value) {
            addCriterion("M18 <>", value, "m18");
            return (Criteria) this;
        }

        public Criteria andM18GreaterThan(String value) {
            addCriterion("M18 >", value, "m18");
            return (Criteria) this;
        }

        public Criteria andM18GreaterThanOrEqualTo(String value) {
            addCriterion("M18 >=", value, "m18");
            return (Criteria) this;
        }

        public Criteria andM18LessThan(String value) {
            addCriterion("M18 <", value, "m18");
            return (Criteria) this;
        }

        public Criteria andM18LessThanOrEqualTo(String value) {
            addCriterion("M18 <=", value, "m18");
            return (Criteria) this;
        }

        public Criteria andM18Like(String value) {
            addCriterion("M18 like", value, "m18");
            return (Criteria) this;
        }

        public Criteria andM18NotLike(String value) {
            addCriterion("M18 not like", value, "m18");
            return (Criteria) this;
        }

        public Criteria andM18In(List<String> values) {
            addCriterion("M18 in", values, "m18");
            return (Criteria) this;
        }

        public Criteria andM18NotIn(List<String> values) {
            addCriterion("M18 not in", values, "m18");
            return (Criteria) this;
        }

        public Criteria andM18Between(String value1, String value2) {
            addCriterion("M18 between", value1, value2, "m18");
            return (Criteria) this;
        }

        public Criteria andM18NotBetween(String value1, String value2) {
            addCriterion("M18 not between", value1, value2, "m18");
            return (Criteria) this;
        }

        public Criteria andM19IsNull() {
            addCriterion("M19 is null");
            return (Criteria) this;
        }

        public Criteria andM19IsNotNull() {
            addCriterion("M19 is not null");
            return (Criteria) this;
        }

        public Criteria andM19EqualTo(String value) {
            addCriterion("M19 =", value, "m19");
            return (Criteria) this;
        }

        public Criteria andM19NotEqualTo(String value) {
            addCriterion("M19 <>", value, "m19");
            return (Criteria) this;
        }

        public Criteria andM19GreaterThan(String value) {
            addCriterion("M19 >", value, "m19");
            return (Criteria) this;
        }

        public Criteria andM19GreaterThanOrEqualTo(String value) {
            addCriterion("M19 >=", value, "m19");
            return (Criteria) this;
        }

        public Criteria andM19LessThan(String value) {
            addCriterion("M19 <", value, "m19");
            return (Criteria) this;
        }

        public Criteria andM19LessThanOrEqualTo(String value) {
            addCriterion("M19 <=", value, "m19");
            return (Criteria) this;
        }

        public Criteria andM19Like(String value) {
            addCriterion("M19 like", value, "m19");
            return (Criteria) this;
        }

        public Criteria andM19NotLike(String value) {
            addCriterion("M19 not like", value, "m19");
            return (Criteria) this;
        }

        public Criteria andM19In(List<String> values) {
            addCriterion("M19 in", values, "m19");
            return (Criteria) this;
        }

        public Criteria andM19NotIn(List<String> values) {
            addCriterion("M19 not in", values, "m19");
            return (Criteria) this;
        }

        public Criteria andM19Between(String value1, String value2) {
            addCriterion("M19 between", value1, value2, "m19");
            return (Criteria) this;
        }

        public Criteria andM19NotBetween(String value1, String value2) {
            addCriterion("M19 not between", value1, value2, "m19");
            return (Criteria) this;
        }

        public Criteria andM20IsNull() {
            addCriterion("M20 is null");
            return (Criteria) this;
        }

        public Criteria andM20IsNotNull() {
            addCriterion("M20 is not null");
            return (Criteria) this;
        }

        public Criteria andM20EqualTo(String value) {
            addCriterion("M20 =", value, "m20");
            return (Criteria) this;
        }

        public Criteria andM20NotEqualTo(String value) {
            addCriterion("M20 <>", value, "m20");
            return (Criteria) this;
        }

        public Criteria andM20GreaterThan(String value) {
            addCriterion("M20 >", value, "m20");
            return (Criteria) this;
        }

        public Criteria andM20GreaterThanOrEqualTo(String value) {
            addCriterion("M20 >=", value, "m20");
            return (Criteria) this;
        }

        public Criteria andM20LessThan(String value) {
            addCriterion("M20 <", value, "m20");
            return (Criteria) this;
        }

        public Criteria andM20LessThanOrEqualTo(String value) {
            addCriterion("M20 <=", value, "m20");
            return (Criteria) this;
        }

        public Criteria andM20Like(String value) {
            addCriterion("M20 like", value, "m20");
            return (Criteria) this;
        }

        public Criteria andM20NotLike(String value) {
            addCriterion("M20 not like", value, "m20");
            return (Criteria) this;
        }

        public Criteria andM20In(List<String> values) {
            addCriterion("M20 in", values, "m20");
            return (Criteria) this;
        }

        public Criteria andM20NotIn(List<String> values) {
            addCriterion("M20 not in", values, "m20");
            return (Criteria) this;
        }

        public Criteria andM20Between(String value1, String value2) {
            addCriterion("M20 between", value1, value2, "m20");
            return (Criteria) this;
        }

        public Criteria andM20NotBetween(String value1, String value2) {
            addCriterion("M20 not between", value1, value2, "m20");
            return (Criteria) this;
        }

        public Criteria andM21IsNull() {
            addCriterion("M21 is null");
            return (Criteria) this;
        }

        public Criteria andM21IsNotNull() {
            addCriterion("M21 is not null");
            return (Criteria) this;
        }

        public Criteria andM21EqualTo(String value) {
            addCriterion("M21 =", value, "m21");
            return (Criteria) this;
        }

        public Criteria andM21NotEqualTo(String value) {
            addCriterion("M21 <>", value, "m21");
            return (Criteria) this;
        }

        public Criteria andM21GreaterThan(String value) {
            addCriterion("M21 >", value, "m21");
            return (Criteria) this;
        }

        public Criteria andM21GreaterThanOrEqualTo(String value) {
            addCriterion("M21 >=", value, "m21");
            return (Criteria) this;
        }

        public Criteria andM21LessThan(String value) {
            addCriterion("M21 <", value, "m21");
            return (Criteria) this;
        }

        public Criteria andM21LessThanOrEqualTo(String value) {
            addCriterion("M21 <=", value, "m21");
            return (Criteria) this;
        }

        public Criteria andM21Like(String value) {
            addCriterion("M21 like", value, "m21");
            return (Criteria) this;
        }

        public Criteria andM21NotLike(String value) {
            addCriterion("M21 not like", value, "m21");
            return (Criteria) this;
        }

        public Criteria andM21In(List<String> values) {
            addCriterion("M21 in", values, "m21");
            return (Criteria) this;
        }

        public Criteria andM21NotIn(List<String> values) {
            addCriterion("M21 not in", values, "m21");
            return (Criteria) this;
        }

        public Criteria andM21Between(String value1, String value2) {
            addCriterion("M21 between", value1, value2, "m21");
            return (Criteria) this;
        }

        public Criteria andM21NotBetween(String value1, String value2) {
            addCriterion("M21 not between", value1, value2, "m21");
            return (Criteria) this;
        }

        public Criteria andM22IsNull() {
            addCriterion("M22 is null");
            return (Criteria) this;
        }

        public Criteria andM22IsNotNull() {
            addCriterion("M22 is not null");
            return (Criteria) this;
        }

        public Criteria andM22EqualTo(String value) {
            addCriterion("M22 =", value, "m22");
            return (Criteria) this;
        }

        public Criteria andM22NotEqualTo(String value) {
            addCriterion("M22 <>", value, "m22");
            return (Criteria) this;
        }

        public Criteria andM22GreaterThan(String value) {
            addCriterion("M22 >", value, "m22");
            return (Criteria) this;
        }

        public Criteria andM22GreaterThanOrEqualTo(String value) {
            addCriterion("M22 >=", value, "m22");
            return (Criteria) this;
        }

        public Criteria andM22LessThan(String value) {
            addCriterion("M22 <", value, "m22");
            return (Criteria) this;
        }

        public Criteria andM22LessThanOrEqualTo(String value) {
            addCriterion("M22 <=", value, "m22");
            return (Criteria) this;
        }

        public Criteria andM22Like(String value) {
            addCriterion("M22 like", value, "m22");
            return (Criteria) this;
        }

        public Criteria andM22NotLike(String value) {
            addCriterion("M22 not like", value, "m22");
            return (Criteria) this;
        }

        public Criteria andM22In(List<String> values) {
            addCriterion("M22 in", values, "m22");
            return (Criteria) this;
        }

        public Criteria andM22NotIn(List<String> values) {
            addCriterion("M22 not in", values, "m22");
            return (Criteria) this;
        }

        public Criteria andM22Between(String value1, String value2) {
            addCriterion("M22 between", value1, value2, "m22");
            return (Criteria) this;
        }

        public Criteria andM22NotBetween(String value1, String value2) {
            addCriterion("M22 not between", value1, value2, "m22");
            return (Criteria) this;
        }

        public Criteria andM23IsNull() {
            addCriterion("M23 is null");
            return (Criteria) this;
        }

        public Criteria andM23IsNotNull() {
            addCriterion("M23 is not null");
            return (Criteria) this;
        }

        public Criteria andM23EqualTo(String value) {
            addCriterion("M23 =", value, "m23");
            return (Criteria) this;
        }

        public Criteria andM23NotEqualTo(String value) {
            addCriterion("M23 <>", value, "m23");
            return (Criteria) this;
        }

        public Criteria andM23GreaterThan(String value) {
            addCriterion("M23 >", value, "m23");
            return (Criteria) this;
        }

        public Criteria andM23GreaterThanOrEqualTo(String value) {
            addCriterion("M23 >=", value, "m23");
            return (Criteria) this;
        }

        public Criteria andM23LessThan(String value) {
            addCriterion("M23 <", value, "m23");
            return (Criteria) this;
        }

        public Criteria andM23LessThanOrEqualTo(String value) {
            addCriterion("M23 <=", value, "m23");
            return (Criteria) this;
        }

        public Criteria andM23Like(String value) {
            addCriterion("M23 like", value, "m23");
            return (Criteria) this;
        }

        public Criteria andM23NotLike(String value) {
            addCriterion("M23 not like", value, "m23");
            return (Criteria) this;
        }

        public Criteria andM23In(List<String> values) {
            addCriterion("M23 in", values, "m23");
            return (Criteria) this;
        }

        public Criteria andM23NotIn(List<String> values) {
            addCriterion("M23 not in", values, "m23");
            return (Criteria) this;
        }

        public Criteria andM23Between(String value1, String value2) {
            addCriterion("M23 between", value1, value2, "m23");
            return (Criteria) this;
        }

        public Criteria andM23NotBetween(String value1, String value2) {
            addCriterion("M23 not between", value1, value2, "m23");
            return (Criteria) this;
        }

        public Criteria andM24IsNull() {
            addCriterion("M24 is null");
            return (Criteria) this;
        }

        public Criteria andM24IsNotNull() {
            addCriterion("M24 is not null");
            return (Criteria) this;
        }

        public Criteria andM24EqualTo(String value) {
            addCriterion("M24 =", value, "m24");
            return (Criteria) this;
        }

        public Criteria andM24NotEqualTo(String value) {
            addCriterion("M24 <>", value, "m24");
            return (Criteria) this;
        }

        public Criteria andM24GreaterThan(String value) {
            addCriterion("M24 >", value, "m24");
            return (Criteria) this;
        }

        public Criteria andM24GreaterThanOrEqualTo(String value) {
            addCriterion("M24 >=", value, "m24");
            return (Criteria) this;
        }

        public Criteria andM24LessThan(String value) {
            addCriterion("M24 <", value, "m24");
            return (Criteria) this;
        }

        public Criteria andM24LessThanOrEqualTo(String value) {
            addCriterion("M24 <=", value, "m24");
            return (Criteria) this;
        }

        public Criteria andM24Like(String value) {
            addCriterion("M24 like", value, "m24");
            return (Criteria) this;
        }

        public Criteria andM24NotLike(String value) {
            addCriterion("M24 not like", value, "m24");
            return (Criteria) this;
        }

        public Criteria andM24In(List<String> values) {
            addCriterion("M24 in", values, "m24");
            return (Criteria) this;
        }

        public Criteria andM24NotIn(List<String> values) {
            addCriterion("M24 not in", values, "m24");
            return (Criteria) this;
        }

        public Criteria andM24Between(String value1, String value2) {
            addCriterion("M24 between", value1, value2, "m24");
            return (Criteria) this;
        }

        public Criteria andM24NotBetween(String value1, String value2) {
            addCriterion("M24 not between", value1, value2, "m24");
            return (Criteria) this;
        }

        public Criteria andM25IsNull() {
            addCriterion("M25 is null");
            return (Criteria) this;
        }

        public Criteria andM25IsNotNull() {
            addCriterion("M25 is not null");
            return (Criteria) this;
        }

        public Criteria andM25EqualTo(String value) {
            addCriterion("M25 =", value, "m25");
            return (Criteria) this;
        }

        public Criteria andM25NotEqualTo(String value) {
            addCriterion("M25 <>", value, "m25");
            return (Criteria) this;
        }

        public Criteria andM25GreaterThan(String value) {
            addCriterion("M25 >", value, "m25");
            return (Criteria) this;
        }

        public Criteria andM25GreaterThanOrEqualTo(String value) {
            addCriterion("M25 >=", value, "m25");
            return (Criteria) this;
        }

        public Criteria andM25LessThan(String value) {
            addCriterion("M25 <", value, "m25");
            return (Criteria) this;
        }

        public Criteria andM25LessThanOrEqualTo(String value) {
            addCriterion("M25 <=", value, "m25");
            return (Criteria) this;
        }

        public Criteria andM25Like(String value) {
            addCriterion("M25 like", value, "m25");
            return (Criteria) this;
        }

        public Criteria andM25NotLike(String value) {
            addCriterion("M25 not like", value, "m25");
            return (Criteria) this;
        }

        public Criteria andM25In(List<String> values) {
            addCriterion("M25 in", values, "m25");
            return (Criteria) this;
        }

        public Criteria andM25NotIn(List<String> values) {
            addCriterion("M25 not in", values, "m25");
            return (Criteria) this;
        }

        public Criteria andM25Between(String value1, String value2) {
            addCriterion("M25 between", value1, value2, "m25");
            return (Criteria) this;
        }

        public Criteria andM25NotBetween(String value1, String value2) {
            addCriterion("M25 not between", value1, value2, "m25");
            return (Criteria) this;
        }

        public Criteria andM26IsNull() {
            addCriterion("M26 is null");
            return (Criteria) this;
        }

        public Criteria andM26IsNotNull() {
            addCriterion("M26 is not null");
            return (Criteria) this;
        }

        public Criteria andM26EqualTo(String value) {
            addCriterion("M26 =", value, "m26");
            return (Criteria) this;
        }

        public Criteria andM26NotEqualTo(String value) {
            addCriterion("M26 <>", value, "m26");
            return (Criteria) this;
        }

        public Criteria andM26GreaterThan(String value) {
            addCriterion("M26 >", value, "m26");
            return (Criteria) this;
        }

        public Criteria andM26GreaterThanOrEqualTo(String value) {
            addCriterion("M26 >=", value, "m26");
            return (Criteria) this;
        }

        public Criteria andM26LessThan(String value) {
            addCriterion("M26 <", value, "m26");
            return (Criteria) this;
        }

        public Criteria andM26LessThanOrEqualTo(String value) {
            addCriterion("M26 <=", value, "m26");
            return (Criteria) this;
        }

        public Criteria andM26Like(String value) {
            addCriterion("M26 like", value, "m26");
            return (Criteria) this;
        }

        public Criteria andM26NotLike(String value) {
            addCriterion("M26 not like", value, "m26");
            return (Criteria) this;
        }

        public Criteria andM26In(List<String> values) {
            addCriterion("M26 in", values, "m26");
            return (Criteria) this;
        }

        public Criteria andM26NotIn(List<String> values) {
            addCriterion("M26 not in", values, "m26");
            return (Criteria) this;
        }

        public Criteria andM26Between(String value1, String value2) {
            addCriterion("M26 between", value1, value2, "m26");
            return (Criteria) this;
        }

        public Criteria andM26NotBetween(String value1, String value2) {
            addCriterion("M26 not between", value1, value2, "m26");
            return (Criteria) this;
        }

        public Criteria andM27IsNull() {
            addCriterion("M27 is null");
            return (Criteria) this;
        }

        public Criteria andM27IsNotNull() {
            addCriterion("M27 is not null");
            return (Criteria) this;
        }

        public Criteria andM27EqualTo(String value) {
            addCriterion("M27 =", value, "m27");
            return (Criteria) this;
        }

        public Criteria andM27NotEqualTo(String value) {
            addCriterion("M27 <>", value, "m27");
            return (Criteria) this;
        }

        public Criteria andM27GreaterThan(String value) {
            addCriterion("M27 >", value, "m27");
            return (Criteria) this;
        }

        public Criteria andM27GreaterThanOrEqualTo(String value) {
            addCriterion("M27 >=", value, "m27");
            return (Criteria) this;
        }

        public Criteria andM27LessThan(String value) {
            addCriterion("M27 <", value, "m27");
            return (Criteria) this;
        }

        public Criteria andM27LessThanOrEqualTo(String value) {
            addCriterion("M27 <=", value, "m27");
            return (Criteria) this;
        }

        public Criteria andM27Like(String value) {
            addCriterion("M27 like", value, "m27");
            return (Criteria) this;
        }

        public Criteria andM27NotLike(String value) {
            addCriterion("M27 not like", value, "m27");
            return (Criteria) this;
        }

        public Criteria andM27In(List<String> values) {
            addCriterion("M27 in", values, "m27");
            return (Criteria) this;
        }

        public Criteria andM27NotIn(List<String> values) {
            addCriterion("M27 not in", values, "m27");
            return (Criteria) this;
        }

        public Criteria andM27Between(String value1, String value2) {
            addCriterion("M27 between", value1, value2, "m27");
            return (Criteria) this;
        }

        public Criteria andM27NotBetween(String value1, String value2) {
            addCriterion("M27 not between", value1, value2, "m27");
            return (Criteria) this;
        }

        public Criteria andM28IsNull() {
            addCriterion("M28 is null");
            return (Criteria) this;
        }

        public Criteria andM28IsNotNull() {
            addCriterion("M28 is not null");
            return (Criteria) this;
        }

        public Criteria andM28EqualTo(String value) {
            addCriterion("M28 =", value, "m28");
            return (Criteria) this;
        }

        public Criteria andM28NotEqualTo(String value) {
            addCriterion("M28 <>", value, "m28");
            return (Criteria) this;
        }

        public Criteria andM28GreaterThan(String value) {
            addCriterion("M28 >", value, "m28");
            return (Criteria) this;
        }

        public Criteria andM28GreaterThanOrEqualTo(String value) {
            addCriterion("M28 >=", value, "m28");
            return (Criteria) this;
        }

        public Criteria andM28LessThan(String value) {
            addCriterion("M28 <", value, "m28");
            return (Criteria) this;
        }

        public Criteria andM28LessThanOrEqualTo(String value) {
            addCriterion("M28 <=", value, "m28");
            return (Criteria) this;
        }

        public Criteria andM28Like(String value) {
            addCriterion("M28 like", value, "m28");
            return (Criteria) this;
        }

        public Criteria andM28NotLike(String value) {
            addCriterion("M28 not like", value, "m28");
            return (Criteria) this;
        }

        public Criteria andM28In(List<String> values) {
            addCriterion("M28 in", values, "m28");
            return (Criteria) this;
        }

        public Criteria andM28NotIn(List<String> values) {
            addCriterion("M28 not in", values, "m28");
            return (Criteria) this;
        }

        public Criteria andM28Between(String value1, String value2) {
            addCriterion("M28 between", value1, value2, "m28");
            return (Criteria) this;
        }

        public Criteria andM28NotBetween(String value1, String value2) {
            addCriterion("M28 not between", value1, value2, "m28");
            return (Criteria) this;
        }

        public Criteria andM29IsNull() {
            addCriterion("M29 is null");
            return (Criteria) this;
        }

        public Criteria andM29IsNotNull() {
            addCriterion("M29 is not null");
            return (Criteria) this;
        }

        public Criteria andM29EqualTo(String value) {
            addCriterion("M29 =", value, "m29");
            return (Criteria) this;
        }

        public Criteria andM29NotEqualTo(String value) {
            addCriterion("M29 <>", value, "m29");
            return (Criteria) this;
        }

        public Criteria andM29GreaterThan(String value) {
            addCriterion("M29 >", value, "m29");
            return (Criteria) this;
        }

        public Criteria andM29GreaterThanOrEqualTo(String value) {
            addCriterion("M29 >=", value, "m29");
            return (Criteria) this;
        }

        public Criteria andM29LessThan(String value) {
            addCriterion("M29 <", value, "m29");
            return (Criteria) this;
        }

        public Criteria andM29LessThanOrEqualTo(String value) {
            addCriterion("M29 <=", value, "m29");
            return (Criteria) this;
        }

        public Criteria andM29Like(String value) {
            addCriterion("M29 like", value, "m29");
            return (Criteria) this;
        }

        public Criteria andM29NotLike(String value) {
            addCriterion("M29 not like", value, "m29");
            return (Criteria) this;
        }

        public Criteria andM29In(List<String> values) {
            addCriterion("M29 in", values, "m29");
            return (Criteria) this;
        }

        public Criteria andM29NotIn(List<String> values) {
            addCriterion("M29 not in", values, "m29");
            return (Criteria) this;
        }

        public Criteria andM29Between(String value1, String value2) {
            addCriterion("M29 between", value1, value2, "m29");
            return (Criteria) this;
        }

        public Criteria andM29NotBetween(String value1, String value2) {
            addCriterion("M29 not between", value1, value2, "m29");
            return (Criteria) this;
        }

        public Criteria andM30IsNull() {
            addCriterion("M30 is null");
            return (Criteria) this;
        }

        public Criteria andM30IsNotNull() {
            addCriterion("M30 is not null");
            return (Criteria) this;
        }

        public Criteria andM30EqualTo(String value) {
            addCriterion("M30 =", value, "m30");
            return (Criteria) this;
        }

        public Criteria andM30NotEqualTo(String value) {
            addCriterion("M30 <>", value, "m30");
            return (Criteria) this;
        }

        public Criteria andM30GreaterThan(String value) {
            addCriterion("M30 >", value, "m30");
            return (Criteria) this;
        }

        public Criteria andM30GreaterThanOrEqualTo(String value) {
            addCriterion("M30 >=", value, "m30");
            return (Criteria) this;
        }

        public Criteria andM30LessThan(String value) {
            addCriterion("M30 <", value, "m30");
            return (Criteria) this;
        }

        public Criteria andM30LessThanOrEqualTo(String value) {
            addCriterion("M30 <=", value, "m30");
            return (Criteria) this;
        }

        public Criteria andM30Like(String value) {
            addCriterion("M30 like", value, "m30");
            return (Criteria) this;
        }

        public Criteria andM30NotLike(String value) {
            addCriterion("M30 not like", value, "m30");
            return (Criteria) this;
        }

        public Criteria andM30In(List<String> values) {
            addCriterion("M30 in", values, "m30");
            return (Criteria) this;
        }

        public Criteria andM30NotIn(List<String> values) {
            addCriterion("M30 not in", values, "m30");
            return (Criteria) this;
        }

        public Criteria andM30Between(String value1, String value2) {
            addCriterion("M30 between", value1, value2, "m30");
            return (Criteria) this;
        }

        public Criteria andM30NotBetween(String value1, String value2) {
            addCriterion("M30 not between", value1, value2, "m30");
            return (Criteria) this;
        }

        public Criteria andM31IsNull() {
            addCriterion("M31 is null");
            return (Criteria) this;
        }

        public Criteria andM31IsNotNull() {
            addCriterion("M31 is not null");
            return (Criteria) this;
        }

        public Criteria andM31EqualTo(String value) {
            addCriterion("M31 =", value, "m31");
            return (Criteria) this;
        }

        public Criteria andM31NotEqualTo(String value) {
            addCriterion("M31 <>", value, "m31");
            return (Criteria) this;
        }

        public Criteria andM31GreaterThan(String value) {
            addCriterion("M31 >", value, "m31");
            return (Criteria) this;
        }

        public Criteria andM31GreaterThanOrEqualTo(String value) {
            addCriterion("M31 >=", value, "m31");
            return (Criteria) this;
        }

        public Criteria andM31LessThan(String value) {
            addCriterion("M31 <", value, "m31");
            return (Criteria) this;
        }

        public Criteria andM31LessThanOrEqualTo(String value) {
            addCriterion("M31 <=", value, "m31");
            return (Criteria) this;
        }

        public Criteria andM31Like(String value) {
            addCriterion("M31 like", value, "m31");
            return (Criteria) this;
        }

        public Criteria andM31NotLike(String value) {
            addCriterion("M31 not like", value, "m31");
            return (Criteria) this;
        }

        public Criteria andM31In(List<String> values) {
            addCriterion("M31 in", values, "m31");
            return (Criteria) this;
        }

        public Criteria andM31NotIn(List<String> values) {
            addCriterion("M31 not in", values, "m31");
            return (Criteria) this;
        }

        public Criteria andM31Between(String value1, String value2) {
            addCriterion("M31 between", value1, value2, "m31");
            return (Criteria) this;
        }

        public Criteria andM31NotBetween(String value1, String value2) {
            addCriterion("M31 not between", value1, value2, "m31");
            return (Criteria) this;
        }

        public Criteria andKqDateIsNull() {
            addCriterion("KQ_DATE is null");
            return (Criteria) this;
        }

        public Criteria andKqDateIsNotNull() {
            addCriterion("KQ_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andKqDateEqualTo(String value) {
            addCriterion("KQ_DATE =", value, "kqDate");
            return (Criteria) this;
        }

        public Criteria andKqDateNotEqualTo(String value) {
            addCriterion("KQ_DATE <>", value, "kqDate");
            return (Criteria) this;
        }

        public Criteria andKqDateGreaterThan(String value) {
            addCriterion("KQ_DATE >", value, "kqDate");
            return (Criteria) this;
        }

        public Criteria andKqDateGreaterThanOrEqualTo(String value) {
            addCriterion("KQ_DATE >=", value, "kqDate");
            return (Criteria) this;
        }

        public Criteria andKqDateLessThan(String value) {
            addCriterion("KQ_DATE <", value, "kqDate");
            return (Criteria) this;
        }

        public Criteria andKqDateLessThanOrEqualTo(String value) {
            addCriterion("KQ_DATE <=", value, "kqDate");
            return (Criteria) this;
        }

        public Criteria andKqDateLike(String value) {
            addCriterion("KQ_DATE like", value, "kqDate");
            return (Criteria) this;
        }

        public Criteria andKqDateNotLike(String value) {
            addCriterion("KQ_DATE not like", value, "kqDate");
            return (Criteria) this;
        }

        public Criteria andKqDateIn(List<String> values) {
            addCriterion("KQ_DATE in", values, "kqDate");
            return (Criteria) this;
        }

        public Criteria andKqDateNotIn(List<String> values) {
            addCriterion("KQ_DATE not in", values, "kqDate");
            return (Criteria) this;
        }

        public Criteria andKqDateBetween(String value1, String value2) {
            addCriterion("KQ_DATE between", value1, value2, "kqDate");
            return (Criteria) this;
        }

        public Criteria andKqDateNotBetween(String value1, String value2) {
            addCriterion("KQ_DATE not between", value1, value2, "kqDate");
            return (Criteria) this;
        }

        public Criteria andKqDeptFlowIsNull() {
            addCriterion("KQ_DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andKqDeptFlowIsNotNull() {
            addCriterion("KQ_DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andKqDeptFlowEqualTo(String value) {
            addCriterion("KQ_DEPT_FLOW =", value, "kqDeptFlow");
            return (Criteria) this;
        }

        public Criteria andKqDeptFlowNotEqualTo(String value) {
            addCriterion("KQ_DEPT_FLOW <>", value, "kqDeptFlow");
            return (Criteria) this;
        }

        public Criteria andKqDeptFlowGreaterThan(String value) {
            addCriterion("KQ_DEPT_FLOW >", value, "kqDeptFlow");
            return (Criteria) this;
        }

        public Criteria andKqDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("KQ_DEPT_FLOW >=", value, "kqDeptFlow");
            return (Criteria) this;
        }

        public Criteria andKqDeptFlowLessThan(String value) {
            addCriterion("KQ_DEPT_FLOW <", value, "kqDeptFlow");
            return (Criteria) this;
        }

        public Criteria andKqDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("KQ_DEPT_FLOW <=", value, "kqDeptFlow");
            return (Criteria) this;
        }

        public Criteria andKqDeptFlowLike(String value) {
            addCriterion("KQ_DEPT_FLOW like", value, "kqDeptFlow");
            return (Criteria) this;
        }

        public Criteria andKqDeptFlowNotLike(String value) {
            addCriterion("KQ_DEPT_FLOW not like", value, "kqDeptFlow");
            return (Criteria) this;
        }

        public Criteria andKqDeptFlowIn(List<String> values) {
            addCriterion("KQ_DEPT_FLOW in", values, "kqDeptFlow");
            return (Criteria) this;
        }

        public Criteria andKqDeptFlowNotIn(List<String> values) {
            addCriterion("KQ_DEPT_FLOW not in", values, "kqDeptFlow");
            return (Criteria) this;
        }

        public Criteria andKqDeptFlowBetween(String value1, String value2) {
            addCriterion("KQ_DEPT_FLOW between", value1, value2, "kqDeptFlow");
            return (Criteria) this;
        }

        public Criteria andKqDeptFlowNotBetween(String value1, String value2) {
            addCriterion("KQ_DEPT_FLOW not between", value1, value2, "kqDeptFlow");
            return (Criteria) this;
        }

        public Criteria andKqDeptNameIsNull() {
            addCriterion("KQ_DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andKqDeptNameIsNotNull() {
            addCriterion("KQ_DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andKqDeptNameEqualTo(String value) {
            addCriterion("KQ_DEPT_NAME =", value, "kqDeptName");
            return (Criteria) this;
        }

        public Criteria andKqDeptNameNotEqualTo(String value) {
            addCriterion("KQ_DEPT_NAME <>", value, "kqDeptName");
            return (Criteria) this;
        }

        public Criteria andKqDeptNameGreaterThan(String value) {
            addCriterion("KQ_DEPT_NAME >", value, "kqDeptName");
            return (Criteria) this;
        }

        public Criteria andKqDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("KQ_DEPT_NAME >=", value, "kqDeptName");
            return (Criteria) this;
        }

        public Criteria andKqDeptNameLessThan(String value) {
            addCriterion("KQ_DEPT_NAME <", value, "kqDeptName");
            return (Criteria) this;
        }

        public Criteria andKqDeptNameLessThanOrEqualTo(String value) {
            addCriterion("KQ_DEPT_NAME <=", value, "kqDeptName");
            return (Criteria) this;
        }

        public Criteria andKqDeptNameLike(String value) {
            addCriterion("KQ_DEPT_NAME like", value, "kqDeptName");
            return (Criteria) this;
        }

        public Criteria andKqDeptNameNotLike(String value) {
            addCriterion("KQ_DEPT_NAME not like", value, "kqDeptName");
            return (Criteria) this;
        }

        public Criteria andKqDeptNameIn(List<String> values) {
            addCriterion("KQ_DEPT_NAME in", values, "kqDeptName");
            return (Criteria) this;
        }

        public Criteria andKqDeptNameNotIn(List<String> values) {
            addCriterion("KQ_DEPT_NAME not in", values, "kqDeptName");
            return (Criteria) this;
        }

        public Criteria andKqDeptNameBetween(String value1, String value2) {
            addCriterion("KQ_DEPT_NAME between", value1, value2, "kqDeptName");
            return (Criteria) this;
        }

        public Criteria andKqDeptNameNotBetween(String value1, String value2) {
            addCriterion("KQ_DEPT_NAME not between", value1, value2, "kqDeptName");
            return (Criteria) this;
        }

        public Criteria andKqUserCodeIsNull() {
            addCriterion("KQ_USER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andKqUserCodeIsNotNull() {
            addCriterion("KQ_USER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andKqUserCodeEqualTo(String value) {
            addCriterion("KQ_USER_CODE =", value, "kqUserCode");
            return (Criteria) this;
        }

        public Criteria andKqUserCodeNotEqualTo(String value) {
            addCriterion("KQ_USER_CODE <>", value, "kqUserCode");
            return (Criteria) this;
        }

        public Criteria andKqUserCodeGreaterThan(String value) {
            addCriterion("KQ_USER_CODE >", value, "kqUserCode");
            return (Criteria) this;
        }

        public Criteria andKqUserCodeGreaterThanOrEqualTo(String value) {
            addCriterion("KQ_USER_CODE >=", value, "kqUserCode");
            return (Criteria) this;
        }

        public Criteria andKqUserCodeLessThan(String value) {
            addCriterion("KQ_USER_CODE <", value, "kqUserCode");
            return (Criteria) this;
        }

        public Criteria andKqUserCodeLessThanOrEqualTo(String value) {
            addCriterion("KQ_USER_CODE <=", value, "kqUserCode");
            return (Criteria) this;
        }

        public Criteria andKqUserCodeLike(String value) {
            addCriterion("KQ_USER_CODE like", value, "kqUserCode");
            return (Criteria) this;
        }

        public Criteria andKqUserCodeNotLike(String value) {
            addCriterion("KQ_USER_CODE not like", value, "kqUserCode");
            return (Criteria) this;
        }

        public Criteria andKqUserCodeIn(List<String> values) {
            addCriterion("KQ_USER_CODE in", values, "kqUserCode");
            return (Criteria) this;
        }

        public Criteria andKqUserCodeNotIn(List<String> values) {
            addCriterion("KQ_USER_CODE not in", values, "kqUserCode");
            return (Criteria) this;
        }

        public Criteria andKqUserCodeBetween(String value1, String value2) {
            addCriterion("KQ_USER_CODE between", value1, value2, "kqUserCode");
            return (Criteria) this;
        }

        public Criteria andKqUserCodeNotBetween(String value1, String value2) {
            addCriterion("KQ_USER_CODE not between", value1, value2, "kqUserCode");
            return (Criteria) this;
        }

        public Criteria andKqUserNameIsNull() {
            addCriterion("KQ_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andKqUserNameIsNotNull() {
            addCriterion("KQ_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andKqUserNameEqualTo(String value) {
            addCriterion("KQ_USER_NAME =", value, "kqUserName");
            return (Criteria) this;
        }

        public Criteria andKqUserNameNotEqualTo(String value) {
            addCriterion("KQ_USER_NAME <>", value, "kqUserName");
            return (Criteria) this;
        }

        public Criteria andKqUserNameGreaterThan(String value) {
            addCriterion("KQ_USER_NAME >", value, "kqUserName");
            return (Criteria) this;
        }

        public Criteria andKqUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("KQ_USER_NAME >=", value, "kqUserName");
            return (Criteria) this;
        }

        public Criteria andKqUserNameLessThan(String value) {
            addCriterion("KQ_USER_NAME <", value, "kqUserName");
            return (Criteria) this;
        }

        public Criteria andKqUserNameLessThanOrEqualTo(String value) {
            addCriterion("KQ_USER_NAME <=", value, "kqUserName");
            return (Criteria) this;
        }

        public Criteria andKqUserNameLike(String value) {
            addCriterion("KQ_USER_NAME like", value, "kqUserName");
            return (Criteria) this;
        }

        public Criteria andKqUserNameNotLike(String value) {
            addCriterion("KQ_USER_NAME not like", value, "kqUserName");
            return (Criteria) this;
        }

        public Criteria andKqUserNameIn(List<String> values) {
            addCriterion("KQ_USER_NAME in", values, "kqUserName");
            return (Criteria) this;
        }

        public Criteria andKqUserNameNotIn(List<String> values) {
            addCriterion("KQ_USER_NAME not in", values, "kqUserName");
            return (Criteria) this;
        }

        public Criteria andKqUserNameBetween(String value1, String value2) {
            addCriterion("KQ_USER_NAME between", value1, value2, "kqUserName");
            return (Criteria) this;
        }

        public Criteria andKqUserNameNotBetween(String value1, String value2) {
            addCriterion("KQ_USER_NAME not between", value1, value2, "kqUserName");
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

        public Criteria andIsSubmitIsNull() {
            addCriterion("IS_SUBMIT is null");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIsNotNull() {
            addCriterion("IS_SUBMIT is not null");
            return (Criteria) this;
        }

        public Criteria andIsSubmitEqualTo(String value) {
            addCriterion("IS_SUBMIT =", value, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNotEqualTo(String value) {
            addCriterion("IS_SUBMIT <>", value, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitGreaterThan(String value) {
            addCriterion("IS_SUBMIT >", value, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SUBMIT >=", value, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitLessThan(String value) {
            addCriterion("IS_SUBMIT <", value, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitLessThanOrEqualTo(String value) {
            addCriterion("IS_SUBMIT <=", value, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitLike(String value) {
            addCriterion("IS_SUBMIT like", value, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNotLike(String value) {
            addCriterion("IS_SUBMIT not like", value, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIn(List<String> values) {
            addCriterion("IS_SUBMIT in", values, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNotIn(List<String> values) {
            addCriterion("IS_SUBMIT not in", values, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitBetween(String value1, String value2) {
            addCriterion("IS_SUBMIT between", value1, value2, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNotBetween(String value1, String value2) {
            addCriterion("IS_SUBMIT not between", value1, value2, "isSubmit");
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