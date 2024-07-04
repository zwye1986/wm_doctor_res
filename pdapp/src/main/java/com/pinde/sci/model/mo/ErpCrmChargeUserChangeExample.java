package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ErpCrmChargeUserChangeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ErpCrmChargeUserChangeExample() {
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

        public Criteria andChangeFlowIsNull() {
            addCriterion("CHANGE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andChangeFlowIsNotNull() {
            addCriterion("CHANGE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andChangeFlowEqualTo(String value) {
            addCriterion("CHANGE_FLOW =", value, "changeFlow");
            return (Criteria) this;
        }

        public Criteria andChangeFlowNotEqualTo(String value) {
            addCriterion("CHANGE_FLOW <>", value, "changeFlow");
            return (Criteria) this;
        }

        public Criteria andChangeFlowGreaterThan(String value) {
            addCriterion("CHANGE_FLOW >", value, "changeFlow");
            return (Criteria) this;
        }

        public Criteria andChangeFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CHANGE_FLOW >=", value, "changeFlow");
            return (Criteria) this;
        }

        public Criteria andChangeFlowLessThan(String value) {
            addCriterion("CHANGE_FLOW <", value, "changeFlow");
            return (Criteria) this;
        }

        public Criteria andChangeFlowLessThanOrEqualTo(String value) {
            addCriterion("CHANGE_FLOW <=", value, "changeFlow");
            return (Criteria) this;
        }

        public Criteria andChangeFlowLike(String value) {
            addCriterion("CHANGE_FLOW like", value, "changeFlow");
            return (Criteria) this;
        }

        public Criteria andChangeFlowNotLike(String value) {
            addCriterion("CHANGE_FLOW not like", value, "changeFlow");
            return (Criteria) this;
        }

        public Criteria andChangeFlowIn(List<String> values) {
            addCriterion("CHANGE_FLOW in", values, "changeFlow");
            return (Criteria) this;
        }

        public Criteria andChangeFlowNotIn(List<String> values) {
            addCriterion("CHANGE_FLOW not in", values, "changeFlow");
            return (Criteria) this;
        }

        public Criteria andChangeFlowBetween(String value1, String value2) {
            addCriterion("CHANGE_FLOW between", value1, value2, "changeFlow");
            return (Criteria) this;
        }

        public Criteria andChangeFlowNotBetween(String value1, String value2) {
            addCriterion("CHANGE_FLOW not between", value1, value2, "changeFlow");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptFlowIsNull() {
            addCriterion("OLD_SIGN_DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptFlowIsNotNull() {
            addCriterion("OLD_SIGN_DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptFlowEqualTo(String value) {
            addCriterion("OLD_SIGN_DEPT_FLOW =", value, "oldSignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptFlowNotEqualTo(String value) {
            addCriterion("OLD_SIGN_DEPT_FLOW <>", value, "oldSignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptFlowGreaterThan(String value) {
            addCriterion("OLD_SIGN_DEPT_FLOW >", value, "oldSignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("OLD_SIGN_DEPT_FLOW >=", value, "oldSignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptFlowLessThan(String value) {
            addCriterion("OLD_SIGN_DEPT_FLOW <", value, "oldSignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("OLD_SIGN_DEPT_FLOW <=", value, "oldSignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptFlowLike(String value) {
            addCriterion("OLD_SIGN_DEPT_FLOW like", value, "oldSignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptFlowNotLike(String value) {
            addCriterion("OLD_SIGN_DEPT_FLOW not like", value, "oldSignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptFlowIn(List<String> values) {
            addCriterion("OLD_SIGN_DEPT_FLOW in", values, "oldSignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptFlowNotIn(List<String> values) {
            addCriterion("OLD_SIGN_DEPT_FLOW not in", values, "oldSignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptFlowBetween(String value1, String value2) {
            addCriterion("OLD_SIGN_DEPT_FLOW between", value1, value2, "oldSignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptFlowNotBetween(String value1, String value2) {
            addCriterion("OLD_SIGN_DEPT_FLOW not between", value1, value2, "oldSignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptNameIsNull() {
            addCriterion("OLD_SIGN_DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptNameIsNotNull() {
            addCriterion("OLD_SIGN_DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptNameEqualTo(String value) {
            addCriterion("OLD_SIGN_DEPT_NAME =", value, "oldSignDeptName");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptNameNotEqualTo(String value) {
            addCriterion("OLD_SIGN_DEPT_NAME <>", value, "oldSignDeptName");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptNameGreaterThan(String value) {
            addCriterion("OLD_SIGN_DEPT_NAME >", value, "oldSignDeptName");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("OLD_SIGN_DEPT_NAME >=", value, "oldSignDeptName");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptNameLessThan(String value) {
            addCriterion("OLD_SIGN_DEPT_NAME <", value, "oldSignDeptName");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptNameLessThanOrEqualTo(String value) {
            addCriterion("OLD_SIGN_DEPT_NAME <=", value, "oldSignDeptName");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptNameLike(String value) {
            addCriterion("OLD_SIGN_DEPT_NAME like", value, "oldSignDeptName");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptNameNotLike(String value) {
            addCriterion("OLD_SIGN_DEPT_NAME not like", value, "oldSignDeptName");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptNameIn(List<String> values) {
            addCriterion("OLD_SIGN_DEPT_NAME in", values, "oldSignDeptName");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptNameNotIn(List<String> values) {
            addCriterion("OLD_SIGN_DEPT_NAME not in", values, "oldSignDeptName");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptNameBetween(String value1, String value2) {
            addCriterion("OLD_SIGN_DEPT_NAME between", value1, value2, "oldSignDeptName");
            return (Criteria) this;
        }

        public Criteria andOldSignDeptNameNotBetween(String value1, String value2) {
            addCriterion("OLD_SIGN_DEPT_NAME not between", value1, value2, "oldSignDeptName");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserFlowIsNull() {
            addCriterion("OLD_CHARGE_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserFlowIsNotNull() {
            addCriterion("OLD_CHARGE_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserFlowEqualTo(String value) {
            addCriterion("OLD_CHARGE_USER_FLOW =", value, "oldChargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserFlowNotEqualTo(String value) {
            addCriterion("OLD_CHARGE_USER_FLOW <>", value, "oldChargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserFlowGreaterThan(String value) {
            addCriterion("OLD_CHARGE_USER_FLOW >", value, "oldChargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("OLD_CHARGE_USER_FLOW >=", value, "oldChargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserFlowLessThan(String value) {
            addCriterion("OLD_CHARGE_USER_FLOW <", value, "oldChargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserFlowLessThanOrEqualTo(String value) {
            addCriterion("OLD_CHARGE_USER_FLOW <=", value, "oldChargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserFlowLike(String value) {
            addCriterion("OLD_CHARGE_USER_FLOW like", value, "oldChargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserFlowNotLike(String value) {
            addCriterion("OLD_CHARGE_USER_FLOW not like", value, "oldChargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserFlowIn(List<String> values) {
            addCriterion("OLD_CHARGE_USER_FLOW in", values, "oldChargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserFlowNotIn(List<String> values) {
            addCriterion("OLD_CHARGE_USER_FLOW not in", values, "oldChargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserFlowBetween(String value1, String value2) {
            addCriterion("OLD_CHARGE_USER_FLOW between", value1, value2, "oldChargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserFlowNotBetween(String value1, String value2) {
            addCriterion("OLD_CHARGE_USER_FLOW not between", value1, value2, "oldChargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserNameIsNull() {
            addCriterion("OLD_CHARGE_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserNameIsNotNull() {
            addCriterion("OLD_CHARGE_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserNameEqualTo(String value) {
            addCriterion("OLD_CHARGE_USER_NAME =", value, "oldChargeUserName");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserNameNotEqualTo(String value) {
            addCriterion("OLD_CHARGE_USER_NAME <>", value, "oldChargeUserName");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserNameGreaterThan(String value) {
            addCriterion("OLD_CHARGE_USER_NAME >", value, "oldChargeUserName");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("OLD_CHARGE_USER_NAME >=", value, "oldChargeUserName");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserNameLessThan(String value) {
            addCriterion("OLD_CHARGE_USER_NAME <", value, "oldChargeUserName");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserNameLessThanOrEqualTo(String value) {
            addCriterion("OLD_CHARGE_USER_NAME <=", value, "oldChargeUserName");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserNameLike(String value) {
            addCriterion("OLD_CHARGE_USER_NAME like", value, "oldChargeUserName");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserNameNotLike(String value) {
            addCriterion("OLD_CHARGE_USER_NAME not like", value, "oldChargeUserName");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserNameIn(List<String> values) {
            addCriterion("OLD_CHARGE_USER_NAME in", values, "oldChargeUserName");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserNameNotIn(List<String> values) {
            addCriterion("OLD_CHARGE_USER_NAME not in", values, "oldChargeUserName");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserNameBetween(String value1, String value2) {
            addCriterion("OLD_CHARGE_USER_NAME between", value1, value2, "oldChargeUserName");
            return (Criteria) this;
        }

        public Criteria andOldChargeUserNameNotBetween(String value1, String value2) {
            addCriterion("OLD_CHARGE_USER_NAME not between", value1, value2, "oldChargeUserName");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2FlowIsNull() {
            addCriterion("OLD_CHARGE_USER2_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2FlowIsNotNull() {
            addCriterion("OLD_CHARGE_USER2_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2FlowEqualTo(String value) {
            addCriterion("OLD_CHARGE_USER2_FLOW =", value, "oldChargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2FlowNotEqualTo(String value) {
            addCriterion("OLD_CHARGE_USER2_FLOW <>", value, "oldChargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2FlowGreaterThan(String value) {
            addCriterion("OLD_CHARGE_USER2_FLOW >", value, "oldChargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2FlowGreaterThanOrEqualTo(String value) {
            addCriterion("OLD_CHARGE_USER2_FLOW >=", value, "oldChargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2FlowLessThan(String value) {
            addCriterion("OLD_CHARGE_USER2_FLOW <", value, "oldChargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2FlowLessThanOrEqualTo(String value) {
            addCriterion("OLD_CHARGE_USER2_FLOW <=", value, "oldChargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2FlowLike(String value) {
            addCriterion("OLD_CHARGE_USER2_FLOW like", value, "oldChargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2FlowNotLike(String value) {
            addCriterion("OLD_CHARGE_USER2_FLOW not like", value, "oldChargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2FlowIn(List<String> values) {
            addCriterion("OLD_CHARGE_USER2_FLOW in", values, "oldChargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2FlowNotIn(List<String> values) {
            addCriterion("OLD_CHARGE_USER2_FLOW not in", values, "oldChargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2FlowBetween(String value1, String value2) {
            addCriterion("OLD_CHARGE_USER2_FLOW between", value1, value2, "oldChargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2FlowNotBetween(String value1, String value2) {
            addCriterion("OLD_CHARGE_USER2_FLOW not between", value1, value2, "oldChargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2NameIsNull() {
            addCriterion("OLD_CHARGE_USER2_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2NameIsNotNull() {
            addCriterion("OLD_CHARGE_USER2_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2NameEqualTo(String value) {
            addCriterion("OLD_CHARGE_USER2_NAME =", value, "oldChargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2NameNotEqualTo(String value) {
            addCriterion("OLD_CHARGE_USER2_NAME <>", value, "oldChargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2NameGreaterThan(String value) {
            addCriterion("OLD_CHARGE_USER2_NAME >", value, "oldChargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2NameGreaterThanOrEqualTo(String value) {
            addCriterion("OLD_CHARGE_USER2_NAME >=", value, "oldChargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2NameLessThan(String value) {
            addCriterion("OLD_CHARGE_USER2_NAME <", value, "oldChargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2NameLessThanOrEqualTo(String value) {
            addCriterion("OLD_CHARGE_USER2_NAME <=", value, "oldChargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2NameLike(String value) {
            addCriterion("OLD_CHARGE_USER2_NAME like", value, "oldChargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2NameNotLike(String value) {
            addCriterion("OLD_CHARGE_USER2_NAME not like", value, "oldChargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2NameIn(List<String> values) {
            addCriterion("OLD_CHARGE_USER2_NAME in", values, "oldChargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2NameNotIn(List<String> values) {
            addCriterion("OLD_CHARGE_USER2_NAME not in", values, "oldChargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2NameBetween(String value1, String value2) {
            addCriterion("OLD_CHARGE_USER2_NAME between", value1, value2, "oldChargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andOldChargeUser2NameNotBetween(String value1, String value2) {
            addCriterion("OLD_CHARGE_USER2_NAME not between", value1, value2, "oldChargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowIsNull() {
            addCriterion("SIGN_DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowIsNotNull() {
            addCriterion("SIGN_DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowEqualTo(String value) {
            addCriterion("SIGN_DEPT_FLOW =", value, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowNotEqualTo(String value) {
            addCriterion("SIGN_DEPT_FLOW <>", value, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowGreaterThan(String value) {
            addCriterion("SIGN_DEPT_FLOW >", value, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SIGN_DEPT_FLOW >=", value, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowLessThan(String value) {
            addCriterion("SIGN_DEPT_FLOW <", value, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("SIGN_DEPT_FLOW <=", value, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowLike(String value) {
            addCriterion("SIGN_DEPT_FLOW like", value, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowNotLike(String value) {
            addCriterion("SIGN_DEPT_FLOW not like", value, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowIn(List<String> values) {
            addCriterion("SIGN_DEPT_FLOW in", values, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowNotIn(List<String> values) {
            addCriterion("SIGN_DEPT_FLOW not in", values, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowBetween(String value1, String value2) {
            addCriterion("SIGN_DEPT_FLOW between", value1, value2, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptFlowNotBetween(String value1, String value2) {
            addCriterion("SIGN_DEPT_FLOW not between", value1, value2, "signDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameIsNull() {
            addCriterion("SIGN_DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameIsNotNull() {
            addCriterion("SIGN_DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameEqualTo(String value) {
            addCriterion("SIGN_DEPT_NAME =", value, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameNotEqualTo(String value) {
            addCriterion("SIGN_DEPT_NAME <>", value, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameGreaterThan(String value) {
            addCriterion("SIGN_DEPT_NAME >", value, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("SIGN_DEPT_NAME >=", value, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameLessThan(String value) {
            addCriterion("SIGN_DEPT_NAME <", value, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameLessThanOrEqualTo(String value) {
            addCriterion("SIGN_DEPT_NAME <=", value, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameLike(String value) {
            addCriterion("SIGN_DEPT_NAME like", value, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameNotLike(String value) {
            addCriterion("SIGN_DEPT_NAME not like", value, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameIn(List<String> values) {
            addCriterion("SIGN_DEPT_NAME in", values, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameNotIn(List<String> values) {
            addCriterion("SIGN_DEPT_NAME not in", values, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameBetween(String value1, String value2) {
            addCriterion("SIGN_DEPT_NAME between", value1, value2, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andSignDeptNameNotBetween(String value1, String value2) {
            addCriterion("SIGN_DEPT_NAME not between", value1, value2, "signDeptName");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowIsNull() {
            addCriterion("CHARGE_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowIsNotNull() {
            addCriterion("CHARGE_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowEqualTo(String value) {
            addCriterion("CHARGE_USER_FLOW =", value, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowNotEqualTo(String value) {
            addCriterion("CHARGE_USER_FLOW <>", value, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowGreaterThan(String value) {
            addCriterion("CHARGE_USER_FLOW >", value, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_USER_FLOW >=", value, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowLessThan(String value) {
            addCriterion("CHARGE_USER_FLOW <", value, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_USER_FLOW <=", value, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowLike(String value) {
            addCriterion("CHARGE_USER_FLOW like", value, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowNotLike(String value) {
            addCriterion("CHARGE_USER_FLOW not like", value, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowIn(List<String> values) {
            addCriterion("CHARGE_USER_FLOW in", values, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowNotIn(List<String> values) {
            addCriterion("CHARGE_USER_FLOW not in", values, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowBetween(String value1, String value2) {
            addCriterion("CHARGE_USER_FLOW between", value1, value2, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserFlowNotBetween(String value1, String value2) {
            addCriterion("CHARGE_USER_FLOW not between", value1, value2, "chargeUserFlow");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameIsNull() {
            addCriterion("CHARGE_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameIsNotNull() {
            addCriterion("CHARGE_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameEqualTo(String value) {
            addCriterion("CHARGE_USER_NAME =", value, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameNotEqualTo(String value) {
            addCriterion("CHARGE_USER_NAME <>", value, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameGreaterThan(String value) {
            addCriterion("CHARGE_USER_NAME >", value, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_USER_NAME >=", value, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameLessThan(String value) {
            addCriterion("CHARGE_USER_NAME <", value, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_USER_NAME <=", value, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameLike(String value) {
            addCriterion("CHARGE_USER_NAME like", value, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameNotLike(String value) {
            addCriterion("CHARGE_USER_NAME not like", value, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameIn(List<String> values) {
            addCriterion("CHARGE_USER_NAME in", values, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameNotIn(List<String> values) {
            addCriterion("CHARGE_USER_NAME not in", values, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameBetween(String value1, String value2) {
            addCriterion("CHARGE_USER_NAME between", value1, value2, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUserNameNotBetween(String value1, String value2) {
            addCriterion("CHARGE_USER_NAME not between", value1, value2, "chargeUserName");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowIsNull() {
            addCriterion("CHARGE_USER2_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowIsNotNull() {
            addCriterion("CHARGE_USER2_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowEqualTo(String value) {
            addCriterion("CHARGE_USER2_FLOW =", value, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowNotEqualTo(String value) {
            addCriterion("CHARGE_USER2_FLOW <>", value, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowGreaterThan(String value) {
            addCriterion("CHARGE_USER2_FLOW >", value, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_USER2_FLOW >=", value, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowLessThan(String value) {
            addCriterion("CHARGE_USER2_FLOW <", value, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_USER2_FLOW <=", value, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowLike(String value) {
            addCriterion("CHARGE_USER2_FLOW like", value, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowNotLike(String value) {
            addCriterion("CHARGE_USER2_FLOW not like", value, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowIn(List<String> values) {
            addCriterion("CHARGE_USER2_FLOW in", values, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowNotIn(List<String> values) {
            addCriterion("CHARGE_USER2_FLOW not in", values, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowBetween(String value1, String value2) {
            addCriterion("CHARGE_USER2_FLOW between", value1, value2, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2FlowNotBetween(String value1, String value2) {
            addCriterion("CHARGE_USER2_FLOW not between", value1, value2, "chargeUser2Flow");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameIsNull() {
            addCriterion("CHARGE_USER2_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameIsNotNull() {
            addCriterion("CHARGE_USER2_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameEqualTo(String value) {
            addCriterion("CHARGE_USER2_NAME =", value, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameNotEqualTo(String value) {
            addCriterion("CHARGE_USER2_NAME <>", value, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameGreaterThan(String value) {
            addCriterion("CHARGE_USER2_NAME >", value, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_USER2_NAME >=", value, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameLessThan(String value) {
            addCriterion("CHARGE_USER2_NAME <", value, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_USER2_NAME <=", value, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameLike(String value) {
            addCriterion("CHARGE_USER2_NAME like", value, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameNotLike(String value) {
            addCriterion("CHARGE_USER2_NAME not like", value, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameIn(List<String> values) {
            addCriterion("CHARGE_USER2_NAME in", values, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameNotIn(List<String> values) {
            addCriterion("CHARGE_USER2_NAME not in", values, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameBetween(String value1, String value2) {
            addCriterion("CHARGE_USER2_NAME between", value1, value2, "chargeUser2Name");
            return (Criteria) this;
        }

        public Criteria andChargeUser2NameNotBetween(String value1, String value2) {
            addCriterion("CHARGE_USER2_NAME not between", value1, value2, "chargeUser2Name");
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

        public Criteria andStatusNameIsNull() {
            addCriterion("STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStatusNameIsNotNull() {
            addCriterion("STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStatusNameEqualTo(String value) {
            addCriterion("STATUS_NAME =", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotEqualTo(String value) {
            addCriterion("STATUS_NAME <>", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameGreaterThan(String value) {
            addCriterion("STATUS_NAME >", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_NAME >=", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLessThan(String value) {
            addCriterion("STATUS_NAME <", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLessThanOrEqualTo(String value) {
            addCriterion("STATUS_NAME <=", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLike(String value) {
            addCriterion("STATUS_NAME like", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotLike(String value) {
            addCriterion("STATUS_NAME not like", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameIn(List<String> values) {
            addCriterion("STATUS_NAME in", values, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotIn(List<String> values) {
            addCriterion("STATUS_NAME not in", values, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameBetween(String value1, String value2) {
            addCriterion("STATUS_NAME between", value1, value2, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotBetween(String value1, String value2) {
            addCriterion("STATUS_NAME not between", value1, value2, "statusName");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIsNull() {
            addCriterion("APPLY_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIsNotNull() {
            addCriterion("APPLY_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW =", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW <>", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowGreaterThan(String value) {
            addCriterion("APPLY_USER_FLOW >", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW >=", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLessThan(String value) {
            addCriterion("APPLY_USER_FLOW <", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW <=", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLike(String value) {
            addCriterion("APPLY_USER_FLOW like", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotLike(String value) {
            addCriterion("APPLY_USER_FLOW not like", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIn(List<String> values) {
            addCriterion("APPLY_USER_FLOW in", values, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotIn(List<String> values) {
            addCriterion("APPLY_USER_FLOW not in", values, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowBetween(String value1, String value2) {
            addCriterion("APPLY_USER_FLOW between", value1, value2, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_FLOW not between", value1, value2, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIsNull() {
            addCriterion("APPLY_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIsNotNull() {
            addCriterion("APPLY_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameEqualTo(String value) {
            addCriterion("APPLY_USER_NAME =", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotEqualTo(String value) {
            addCriterion("APPLY_USER_NAME <>", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameGreaterThan(String value) {
            addCriterion("APPLY_USER_NAME >", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_NAME >=", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLessThan(String value) {
            addCriterion("APPLY_USER_NAME <", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_NAME <=", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLike(String value) {
            addCriterion("APPLY_USER_NAME like", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotLike(String value) {
            addCriterion("APPLY_USER_NAME not like", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIn(List<String> values) {
            addCriterion("APPLY_USER_NAME in", values, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotIn(List<String> values) {
            addCriterion("APPLY_USER_NAME not in", values, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameBetween(String value1, String value2) {
            addCriterion("APPLY_USER_NAME between", value1, value2, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_NAME not between", value1, value2, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIsNull() {
            addCriterion("APPLY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIsNotNull() {
            addCriterion("APPLY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyTimeEqualTo(String value) {
            addCriterion("APPLY_TIME =", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotEqualTo(String value) {
            addCriterion("APPLY_TIME <>", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeGreaterThan(String value) {
            addCriterion("APPLY_TIME >", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_TIME >=", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeLessThan(String value) {
            addCriterion("APPLY_TIME <", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeLessThanOrEqualTo(String value) {
            addCriterion("APPLY_TIME <=", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeLike(String value) {
            addCriterion("APPLY_TIME like", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotLike(String value) {
            addCriterion("APPLY_TIME not like", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIn(List<String> values) {
            addCriterion("APPLY_TIME in", values, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotIn(List<String> values) {
            addCriterion("APPLY_TIME not in", values, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeBetween(String value1, String value2) {
            addCriterion("APPLY_TIME between", value1, value2, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotBetween(String value1, String value2) {
            addCriterion("APPLY_TIME not between", value1, value2, "applyTime");
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